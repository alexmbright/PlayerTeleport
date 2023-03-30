package dev.alexbright.playerteleport;

import dev.alexbright.playerteleport.commands.DeleteCommand;
import dev.alexbright.playerteleport.commands.SetCommand;
import dev.alexbright.playerteleport.commands.TeleportCommand;
import dev.alexbright.playerteleport.commands.UpdateCommand;
import dev.alexbright.playerteleport.handlers.ConfigFile;
import dev.alexbright.playerteleport.handlers.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class PlayerTeleport extends JavaPlugin {

    private static PlayerTeleport instance;
    private static ConfigFile data;
    public static String prefix = ChatColor.GRAY + "[PlayerTP] " + ChatColor.WHITE;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("playertp").setExecutor(new TeleportCommand());
        getCommand("playertp").setAliases(Arrays.asList("ptp", "pteleport"));
        getCommand("settp").setExecutor(new SetCommand());
        getCommand("settp").setAliases(Arrays.asList("setteleport", "stp"));
        getCommand("updatetp").setExecutor(new UpdateCommand());
        getCommand("updatetp").setAliases(Arrays.asList("updateteleport", "utp"));
        getCommand("deletetp").setExecutor(new DeleteCommand());
        getCommand("deletetp").setAliases(Arrays.asList("deleteteleport", "deltp"));
        if (!getDataFolder().exists()) getDataFolder().mkdirs();
        data = new ConfigFile("data.yml");
        if (!data.getConfig().contains("players")) {
            data.getConfig().createSection("players");
            data.save();
        }
        Bukkit.getPluginManager().registerEvents(new PlayerHandler(), this);
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
