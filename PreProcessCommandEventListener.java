package me.MuchDan.CommandOverride.EventListeners;

import me.MuchDan.CommandOverride.CommandOverride;
import me.MuchDan.CommandOverride.ConfigManager.BlackListedCommandsManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.List;

public class PreProcessCommandEventListener implements Listener {
    private CommandOverride plugin;
    private BlackListedCommandsManager commands;

    public PreProcessCommandEventListener(CommandOverride plugin){
        this.plugin = plugin;
        commands = plugin.getCommands();
    }
    @EventHandler
    public void PreProcessedCommand(PlayerCommandPreprocessEvent Event){
        this.commands.getConfig().getConfigurationSection("Permissions").getKeys(false).forEach(Node ->{
            List<String> BlackListed = new ArrayList<>();
            BlackListed = this.commands.getConfig().getStringList("Permissions." + Node + ".BlackListed");
            String[] args = Event.getMessage().split(" ");
            for(String cmd : BlackListed) {
                if (args[0].equalsIgnoreCase(cmd)){
                    String NodeM = Node.replace(":", ".");
                    if(!Event.getPlayer().hasPermission(NodeM)){
                        Event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
                                this.commands.getConfig().getString("Permissions." + Node + ".DenyMessage")));
                        Event.setCancelled(true);
                    }
                }
            }
        });
    }
}
