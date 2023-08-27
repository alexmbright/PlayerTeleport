package dev.alexbright.playerteleport.commands;

import dev.alexbright.playerteleport.PlayerTeleport;
import dev.alexbright.playerteleport.handlers.PointHandler;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class SetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute commands.");
            return false;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Please specify a name");
            p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Usage: /" + label.toLowerCase() + " <point name>");
            return false;
        }

        if (args.length == 1) {
            String reqName = args[0].toLowerCase();
            HashMap<String, Location> points = PointHandler.getPoints(p);
            if (PointHandler.getPoints(p).containsKey(reqName)) {
                p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Teleport point " + ChatColor.BOLD + reqName + ChatColor.RESET + ChatColor.RED + " already exists...");
                p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "To update, use /updatetp <point name>");
                return false;
            }
            Location point = points.get(reqName);
            if (!PointHandler.setPoint(p, reqName, p.getLocation(), false)) {
                p.sendMessage(PlayerTeleport.prefix + ChatColor.RED + "Unknown error occurred... please try again");
                return false;
            }
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Set teleport point " + ChatColor.BOLD + reqName + ChatColor.RESET + ChatColor.GREEN + " at current location"));
            //p.sendMessage(PlayerTeleport.prefix + ChatColor.GREEN + "Set teleport point " + ChatColor.BOLD + reqName + ChatColor.RESET + ChatColor.GREEN + " at current location");
        }

        return true;
    }
}
