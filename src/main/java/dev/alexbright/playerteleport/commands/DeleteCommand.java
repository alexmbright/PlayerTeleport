package dev.alexbright.playerteleport.commands;

import dev.alexbright.playerteleport.PlayerTeleport;
import dev.alexbright.playerteleport.handlers.PlayerHandler;
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
            p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Please specify a teleport point");
            p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Usage: /" + label.toLowerCase() + " <point name>");
            return false;
        }

        if (args.length == 1) {
            if (!PlayerHandler.playerExists(p)) {
                p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "You do not have any teleport points");
                return false;
            }
            String reqName = args[0].toLowerCase();
            if (!PointHandler.getPoints(p).containsKey(reqName)) {
                p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Teleport point " + ChatColor.BOLD + reqName + ChatColor.RESET + ChatColor.RED + " not found");
                return false;
            }
            if (!PointHandler.deletePoint(p, reqName)) {
                p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Unknown error occurred... please try again");
                return false;
            }
            p.sendMessage(PlayerTeleport.prefix + ChatColor.GREEN + "Deleted teleport point " + ChatColor.BOLD + reqName + ChatColor.RESET + ChatColor.GREEN);
        }

        return true;
    }
}
