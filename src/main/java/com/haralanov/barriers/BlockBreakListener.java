package com.haralanov.barriers;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.ChatColor;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        if (event.getBlock().getTypeId() == 67 && event.getBlock().getData() == (byte) 4) {
            event.setCancelled(true);
            if (event.getPlayer().hasPermission("barriers.break")) {
                event.getBlock().setTypeId(0);
            } else {
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
                        "&cYou do not have permission to break barrier blocks."));
            }
        }
    }
}
