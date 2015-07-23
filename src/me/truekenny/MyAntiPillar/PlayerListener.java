package me.truekenny.MyAntiPillar;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PlayerListener implements Listener {
    /**
     * Экземпляр главного класса плагина
     */
    private final MyAntiPillar plugin;

    private static final Set<String> spamBlocks = new HashSet<String>(Arrays.asList(
            new String[]{"DIRT", "SAND", "COBBLESTONE"}
    ));

    private final double deltaY = 0.0001;

    public PlayerListener(MyAntiPillar instance) {
        plugin = instance;
        plugin.log("PlayerListener has been enabled!");
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        World world = player.getWorld();
        Location location = block.getLocation();

        Location testLocation;
        Block testBlock;


        if (!spamBlocks.contains(block.getType().toString())) {

            return;
        }

        if (location.getBlockY() < 65) {

            return;
        }

        if(Math.abs(player.getLocation().getY() - Math.round(player.getLocation().getY())) < deltaY) {

            return;
        }

        /*
        world.spawnEntity(player.getLocation(), EntityType.SILVERFISH);
        world.spawnEntity(player.getLocation(), EntityType.CREEPER);
        world.spawnEntity(player.getLocation(), EntityType.ZOMBIE);
        */
        event.setCancelled(true);

        plugin.log(plugin.ANSI_RED + player.getName().toString() + " pillar blocked ("+ deltaY +")");
    }
}

