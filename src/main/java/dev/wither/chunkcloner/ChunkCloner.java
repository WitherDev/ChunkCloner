package dev.wither.chunkcloner;

import dev.wither.chunkcloner.core.BundleListener;
import dev.wither.chunkcloner.core.ChunkChangerCommand;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChunkCloner extends JavaPlugin {

    @Getter private static ChunkCloner plugin;
    @Getter @Setter private static boolean enabledCC;
    @Getter @Setter private static boolean messageCC;
    @Getter @Setter private static int distanceCC;

    @Override
    public void onEnable() {

        plugin = this;

        /* Edit Settings for the Plugin

        enabled = turns the plugin on/off
        message = turns the block-count message on/off
        radius = # of chunks in each direction that get mirrored

         */
        enabledCC = false;
        messageCC = false;
        distanceCC = 8;


        new ChunkChangerCommand();

        Bukkit.getPluginManager().registerEvents(new BundleListener(), this);

    }

    @Override
    public void onDisable() {



    }

}
