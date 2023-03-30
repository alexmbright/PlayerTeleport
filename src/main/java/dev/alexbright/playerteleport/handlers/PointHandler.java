package dev.alexbright.playerteleport.handlers;

import dev.alexbright.playerteleport.PlayerTeleport;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PointHandler {

    private static ConfigFile data = PlayerTeleport.getData();

    public static List<Point> getPoints(Player p) {
        List<Point> points = new ArrayList<>();
        String uuid = p.getUniqueId().toString();
        if (PlayerHandler.playerExists(p) && data.getConfig().getConfigurationSection("players." + uuid).contains("points")) {
            ConfigurationSection configPoints = data.getConfig().getConfigurationSection("players." + uuid + ".points");
            for (String pointName : configPoints.getKeys(false)) {
                String world = configPoints.getConfigurationSection(pointName).getString("world");
                List<Integer> coords = configPoints.getConfigurationSection(pointName).getIntegerList("coords");
                Point point = new Point(pointName, world, coords.get(0), coords.get(1), coords.get(2));
                points.add(point);
            }
        }
        return points;
    }

    public static Point getPoint(Player p, String name) {
        String uuid = p.getUniqueId().toString();
        for (Point point : getPoints(p)) {
            if (point.getName().equals(name)) return point;
        }
        return null;
    }

    public static void setPoint(Player p, String name, Location location) {
        String uuid = p.getUniqueId().toString();
        if (getPoint(p, name) != null) {
            p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Teleport point '" + name + "' already exists.");
            return;
        }
        if (!PlayerHandler.playerExists())
    }

}
