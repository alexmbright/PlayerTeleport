package dev.alexbright.playerteleport.commands;

import dev.alexbright.playerteleport.PlayerTeleport;
import dev.alexbright.playerteleport.handlers.Point;
import dev.alexbright.playerteleport.handlers.PointHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeleportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute commands.");
            return false;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Please specify a teleport point.");
            p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Usage: /playertp <point name>");
            return false;
        }

        if (args.length == 1) {
            String reqPoint = args[0].toLowerCase();
            Point point = PointHandler.getPoint(p, reqPoint);
            if (point == null) {
                p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Teleport point '" + reqPoint + "' not found.");
                return false;
            }
            if (!p.teleport(point.getLocation())) {
                p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Unknown error! Could not teleport...");
                return false;
            }
            p.sendMessage(PlayerTeleport.prefix + "Teleported to '" + point.getName() + "'");
        }

        return false;
    }

}
