package dev.wither.chunkcloner.core;

import dev.wither.chunkcloner.ChunkCloner;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class BundleListener implements Listener {

    /*

    Just a small back-end feature that to let the creator know that the server is using his plugin :)

     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        if (event.getPlayer().getUniqueId().toString().equalsIgnoreCase("ba064172-f566-4589-9326-a4d6e500c1a0")) {

            event.getPlayer().sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Hey! " + ChatColor.RESET + "" + ChatColor.AQUA + "You joined a server running ChunkChanger!");

        }

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        if (ChunkCloner.isEnabledCC()) {
            Location loc = event.getBlockPlaced().getLocation();

            List<Location> newLocations = getLocations(loc);

            for (Location location : newLocations) {

                location.getBlock().setType(event.getBlockPlaced().getType(), true);
                //event.getPlayer().sendMessage("Loc: " + location.getBlockX() +  " - " + location.getBlockY() + " - " + location.getBlockZ());


            }

            event.getPlayer().sendMessage(ChatColor.GOLD + "You just placed " + newLocations.size() + " extra blocks!");
        }

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        if (ChunkCloner.isEnabledCC()) {
            Location loc = event.getBlock().getLocation();

            List<Location> newLocations = getLocations(loc);

            for (Location location : newLocations) {

                location.getBlock().setType(Material.AIR, true);

            }

            event.getPlayer().sendMessage(ChatColor.GOLD + "You just broke " + newLocations.size() + " extra blocks!");
        }

    }

    public List<Location> getLocations(Location location) {

        List<Location> output = new ArrayList<>();

        World world = location.getWorld();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        int r = ChunkCloner.getDistanceCC();

        int i = 0;

        assert world != null;
        while (i != r) {

            int v = 1;
            Location plusX = location.clone().add(16*i, 0, 0);
            output.add(plusX);

            while (v != r) {

                Location plusZ = plusX.clone().add(0, 0, 16*v);
                output.add(plusZ);
                Location minusZ = plusX.clone().add(0, 0, -16*v);
                output.add(minusZ);
                v++;

            }

            Location minusX = location.clone().add(-16*i, 0, 0);
            output.add(minusX);

            v = 1;
            while (v != 20) {

                Location plusZ = minusX.clone().add(0, 0, 16*v);
                output.add(plusZ);
                Location minusZ = minusX.clone().add(0, 0, -16*v);
                output.add(minusZ);
                v++;

            }

            i++;

        }

        return output;

    }

}
