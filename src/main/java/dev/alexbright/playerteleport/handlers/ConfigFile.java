package dev.alexbright.playerteleport.handlers;

import dev.alexbright.playerteleport.PlayerTeleport;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class ConfigFile {

    private PlayerTeleport plugin = PlayerTeleport.getInstance();
    private File file;
    private FileConfiguration config;

    public ConfigFile(String fileName) {
        file = new File(plugin.getDataFolder(), fileName);
        createFile();
    }

    private void createFile() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
