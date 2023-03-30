package dev.alexbright.playerteleport.handlers;

import dev.alexbright.playerteleport.PlayerTeleport;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PointHandler {

    private static ConfigFile data = PlayerTeleport.getData();

    public static HashMap<String, Location> getPoints(Player p) {
        HashMap<String, Location> points = new HashMap<>();
        String uuid = p.getUniqueId().toString();
        if (PlayerHandler.playerExists(p) && data.getConfig().getConfigurationSection("players." + uuid).contains("points")) {
            ConfigurationSection configPoints = data.getConfig().getConfigurationSection("players." + uuid + ".points");
            for (String pointName : configPoints.getKeys(false)) {
                String world = configPoints.getConfigurationSection(pointName).getString("world");
                List<Integer> coords = configPoints.getConfigurationSection(pointName).getIntegerList("coords");
                Location location = new Location(Bukkit.getWorld(world), coords.get(0), coords.get(1), coords.get(2));
                points.put(pointName, location);
            }
        }
        return points;
    }

    public static boolean setPoint(Player p, String name, Location location, boolean update) {
        String uuid = p.getUniqueId().toString();

        // if not updating and point already exists, return false
        if (!update && getPoints(p).containsKey(name)) return false;

        // if player doesn't exist and can't be added for some reason, return false
        if (!PlayerHandler.playerExists(p) && !PlayerHandler.addPlayer(p)) return false;

        ConfigurationSection configSection = data.getConfig().getConfigurationSection("players." + uuid + ".points");

        // if point doesn't exist already, create section
        if (!configSection.contains(name)) configSection.createSection(name);

        // add point details
        configSection = configSection.getConfigurationSection(name);
        configSection.set("world", location.getWorld().getName());
        List<Integer> coordsList = Arrays.asList(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        configSection.set("coords", coordsList);
        data.save();

        // successful
        return true;
    }

    public static boolean deletePoint(Player p, String name) {
        String uuid = p.getUniqueId().toString();
        if (!getPoints(p).containsKey(name)) return false;
        if (!PlayerHandler.playerExists(p)) return false;

        ConfigurationSection configSection = data.getConfig().getConfigurationSection("players." + uuid + ".points");
        configSection.set(name, null);
        data.save();
        return true;
    }

}
