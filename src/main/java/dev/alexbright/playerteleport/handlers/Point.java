package dev.alexbright.playerteleport.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Point {

    private String name;
    private Location location;

    public Point(String name, String world, int x, int y, int z) {
        this.name = name;
        this.location = new Location(Bukkit.getWorld(world), x, y, z);
    }

    public void setCoords(String world, int x, int y, int z) {
        location = new Location(Bukkit.getWorld(world), x, y, z);
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public World getWorld() {
        return location.getWorld();
    }

    public int getX() {
        return location.getBlockX();
    }

    public int getY() {
        return location.getBlockY();
    }

    public int getZ() {
        return location.getBlockZ();
    }

    public void setX(int x) {
        location.setX(x);
    }

    public void setY(int y) {
        location.setY(y);
    }

    public void setZ(int z) {
        location.setZ(z);
    }
}
