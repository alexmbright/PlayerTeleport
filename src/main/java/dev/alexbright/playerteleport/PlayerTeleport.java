package dev.alexbright.playerteleport;

import dev.alexbright.playerteleport.commands.CommandHandler;
import dev.alexbright.playerteleport.handlers.ConfigFile;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;


public final class PlayerTeleport extends JavaPlugin {

    private static PlayerTeleport instance;
    private static ConfigFile data;
    public static String prefix = ChatColor.AQUA + "[PlayerTeleport] " + ChatColor.WHITE;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("playertp").setExecutor(new CommandHandler());
        getCommand("playertp").setAliases(Arrays.asList("ptp", "pteleport"));
        getCommand("settp").setExecutor(new CommandHandler());
        getCommand("settp").setAliases(Arrays.asList("setteleport", "stp"));
        getCommand("updatetp").setExecutor(new CommandHandler());
        getCommand("updatetp").setAliases(Arrays.asList("updateteleport", "utp"));
        data = new ConfigFile("data.yml");
        if (!data.getConfig().contains("players")) {
            data.getConfig().createSection("players");
            data.save();
        }
    }

    @Override
    public void onDisable() {
        // do nothing
    }

    public static PlayerTeleport getInstance() {
        return instance;
    }

    public static ConfigFile getData() {
        return data;
    }

}
