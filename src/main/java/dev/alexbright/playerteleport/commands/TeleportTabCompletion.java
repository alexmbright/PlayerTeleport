package dev.alexbright.playerteleport.commands;

import dev.alexbright.playerteleport.handlers.PointHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeleportTabCompletion implements TabCompleter {

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return null;

        if (label.equalsIgnoreCase("settp") || label.equalsIgnoreCase("setteleport") || label.equalsIgnoreCase("stp")) return Collections.emptyList();

        Player p = (Player) sender;
        if (args.length == 1) {
            return new ArrayList<>(PointHandler.getPoints(p).keySet());
        }
        return null;
    }
}
