package dev.alexbright.playerteleport.commands;

import dev.alexbright.playerteleport.PlayerTeleport;
import dev.alexbright.playerteleport.handlers.PointHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DeleteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute commands.");
            return false;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Please specify a point.");
            p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Usage: /deletetp <point name>");
            return false;
        }

        if (args.length == 1) {
            String reqName = args[0].toLowerCase();
            if (PointHandler.getPoint(p, reqName) == null) {
                p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Teleport point '" + reqName + "' not found.");
                return false;
            }
        }

        return true;
    }
}
