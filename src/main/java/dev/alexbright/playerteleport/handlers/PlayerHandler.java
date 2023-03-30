package dev.alexbright.playerteleport.handlers;

import dev.alexbright.playerteleport.PlayerTeleport;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerHandler implements Listener {

    private static ConfigFile data = PlayerTeleport.getData();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        synchronizeName(p);
    }

    public static boolean addPlayer(Player p) {
        String uuid = p.getUniqueId().toString();
        if (playerExists(p)) {
            data.getConfig().set("players." + uuid + ".name", p.getName());
            data.getConfig().getConfigurationSection("players." + uuid).createSection("points");
            data.save();
            return true;
        }
        return false;
    }

    public static boolean playerExists(Player p) {
        String uuid = p.getUniqueId().toString();
        return data.getConfig().getConfigurationSection("players").contains(uuid);
    }

    private static void synchronizeName(Player p) {
        if (playerExists(p)) {
            String uuid = p.getUniqueId().toString();
            data.getConfig().set("players." + uuid + ".name", p.getName());
            data.save();
        }
    }

}
