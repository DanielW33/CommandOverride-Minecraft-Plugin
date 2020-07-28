package me.MuchDan.CommandOverride.CommandExecutors;

import me.MuchDan.CommandOverride.CommandOverride;
import me.MuchDan.CommandOverride.ConfigManager.BlackListedCommandsManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class onCommandCommandOverride implements CommandExecutor {
    private CommandOverride plugin;
    private BlackListedCommandsManager Commands;
    public onCommandCommandOverride(CommandOverride plugin){
        this.plugin = plugin;
        Commands = this.plugin.getCommands();
    }
    @Override
    public boolean onCommand(CommandSender Sender, Command cmd, String label, String[] args){
        if(label.equalsIgnoreCase("CommandOverride")){
            if(args.length < 1){
                Sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Use &b/CommandOverride reload &7to reload the config."));
                return true;
            }
            else if(args[0].equalsIgnoreCase("reload")){
                Sender.sendMessage(ChatColor.GREEN + "Reloading ChatOverride Config.");
                Commands.reloadConfig();
                return true;
            }
        }
        return false;
    }
}
