package dev.alexbright.playerteleport.commands;

import dev.alexbright.playerteleport.PlayerTeleport;
import dev.alexbright.playerteleport.handlers.PointHandler;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

public class TeleportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute commands.");
            return false;
        }

        Player p = (Player) sender;

        if (args.length == 0) {

            p.sendMessage(PlayerTeleport.prefix + ChatColor.BOLD + "Your current teleport points:");
            HashMap<String, Location> points = PointHandler.getPoints(p);

            // view teleports

            p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Please specify a teleport point.");
            p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Usage: /" + label.toLowerCase() + " <point name>");
            return false;
        }

        if (args.length == 1) {
            String reqName = args[0].toLowerCase();
            HashMap<String, Location> points = PointHandler.getPoints(p);
            if (points.containsKey(reqName)) {
                p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Teleport point " + ChatColor.ITALIC + reqName + ChatColor.RESET + ChatColor.RED + " not found.");
                return false;
            }
            Location point = points.get(reqName);
            if (!p.teleport(point)) {
                p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Unknown error occurred... please try again.");
                return false;
            }
            p.sendMessage(PlayerTeleport.prefix + ChatColor.YELLOW + "Teleported to " + ChatColor.ITALIC + reqName);
        }

        return false;
    }

}
