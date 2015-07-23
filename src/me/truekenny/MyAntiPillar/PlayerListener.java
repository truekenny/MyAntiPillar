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


        // plugin.log(player.getName() + " place " + block);

        if (!spamBlocks.contains(block.getType().toString())) {

            return;
        }

        if (location.getBlockY() < 65) {

            return;
        }

        int countBlock = 0;

        int halfSize = 2;

        for (int x = location.getBlockX() - 1; x <= location.getBlockX() + 1; x++) {
            for (int z = location.getBlockZ() - 1; z <= location.getBlockZ() + 1; z++) {
                for (int y = location.getBlockY() - 1 - halfSize * 2; y <= location.getBlockY() + 1 + halfSize * 2; y++) {
                    testLocation = new Location(world, x, y, z);
                    testBlock = testLocation.getBlock();

                    // plugin.log("" + testBlock);
                    // plugin.log(testBlock.getType().toString());

                    if (!testBlock.getType().toString().equals("AIR")) {
                        countBlock++;

                        if (countBlock >= plugin.minimumQuantity) {

                            return;
                        }
                    } // test !AIR
                } // y
            } // z
        } // x

        // player.sendMessage(ChatColor.RED + plugin.config.getString("reason"));
        // event.setCancelled(true);
        world.spawnEntity(player.getLocation(), EntityType.SILVERFISH);
        world.spawnEntity(player.getLocation(), EntityType.CREEPER);
        world.spawnEntity(player.getLocation(), EntityType.ZOMBIE);


        plugin.log(plugin.ANSI_RED + player.getName().toString() + " pillar blocked");
    } // function
}

