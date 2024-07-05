package com.haralanov.barriers;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.block.Block;

public class BlockPistonListener implements Listener {

    @EventHandler
    public void onPistonExtend(final BlockPistonExtendEvent event) {
        for (Block block : event.getBlocks()) {
            if (block.getTypeId() == 67 && block.getData() == (byte) 4) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPistonRetract(final BlockPistonRetractEvent event) {
        if (event.getRetractLocation().getBlock().getTypeId() == 67
                && event.getRetractLocation().getBlock().getData() == (byte) 4
                && event.isSticky()) {
            event.setCancelled(true);
        }
    }
}