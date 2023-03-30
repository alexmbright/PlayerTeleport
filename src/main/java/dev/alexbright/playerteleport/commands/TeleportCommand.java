package dev.alexbright.playerteleport.commands;

import dev.alexbright.playerteleport.PlayerTeleport;
import dev.alexbright.playerteleport.handlers.PlayerHandler;
import dev.alexbright.playerteleport.handlers.PointHandler;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TeleportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute commands.");
            return false;
        }

        Player p = (Player) sender;

        if (args.length == 0) {

            HashMap<String, Location> points = PointHandler.getPoints(p);
            if (points.isEmpty()) {
                p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "No teleport points found");
                p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "To set a point, use /settp <point name>");
            } else {
                p.sendMessage(PlayerTeleport.prefix + ChatColor.YELLOW + "Your current teleport points:");
                List<String> keys = new ArrayList<>(points.keySet());
                Collections.sort(keys);
                for (String pointName : keys) {
                    Location loc = points.get(pointName);
                    p.sendMessage(PlayerTeleport.prefix + ChatColor.YELLOW + ChatColor.BOLD + pointName +
                            ChatColor.RESET + " (" + loc.getWorld().getName() +
                            ", " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + ")");
                }
                p.sendMessage(PlayerTeleport.prefix + ChatColor.YELLOW + ChatColor.ITALIC + "To visit a point, use /" + label.toLowerCase() + " <point name>");
            }
            return true;
        }

        if (args.length == 1) {
            if (!PlayerHandler.playerExists(p)) {
                p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "You do not have any teleport points");
                return false;
            }
            String reqName = args[0].toLowerCase();
            HashMap<String, Location> points = PointHandler.getPoints(p);
            if (!points.containsKey(reqName)) {
                p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Teleport point " + ChatColor.BOLD + reqName + ChatColor.RESET + ChatColor.RED + " not found");
                return false;
            }
            Location point = points.get(reqName);
            if (!p.teleport(point)) {
                p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Unknown error occurred... please try again");
                return false;
            }
            p.sendMessage(PlayerTeleport.prefix + ChatColor.YELLOW + "Teleported to " + ChatColor.BOLD + reqName);
        }

        return false;
    }

}
