package me.MuchDan.CommandOverride;

import me.MuchDan.CommandOverride.CommandExecutors.onCommandCommandOverride;
import me.MuchDan.CommandOverride.ConfigManager.BlackListedCommandsManager;
import me.MuchDan.CommandOverride.EventListeners.PreProcessCommandEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandOverride extends JavaPlugin {
    private BlackListedCommandsManager commands;
    @Override
    public void onEnable(){
        this.commands = new BlackListedCommandsManager(this);
        this.commands.getConfig().options().copyDefaults(false);
        this.commands.saveDefaultConfig();

        this.getCommand("CommandOverride").setExecutor(new onCommandCommandOverride(this));
        this.getServer().getPluginManager().registerEvents(new PreProcessCommandEventListener(this), this);
    }
    public BlackListedCommandsManager getCommands(){
        return commands;
    }
}
