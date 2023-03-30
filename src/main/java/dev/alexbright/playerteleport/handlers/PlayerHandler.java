package dev.alexbright.playerteleport.handlers;

import dev.alexbright.playerteleport.PlayerTeleport;
import org.bukkit.configuration.ConfigurationSection;
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
        if (playerExists(p)) return false;
        ConfigurationSection section = data.getConfig().createSection("players." + uuid);
        section.set("name", p.getName());
        section.createSection("points");
        data.save();
        return true;
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

    public static boolean deletePlayer(Player p) {
        String uuid = p.getUniqueId().toString();
        if (!playerExists(p)) return false;
        ConfigurationSection section = data.getConfig().getConfigurationSection("players");
        section.set(uuid, null);
        data.save();
        return true;
    }

}
