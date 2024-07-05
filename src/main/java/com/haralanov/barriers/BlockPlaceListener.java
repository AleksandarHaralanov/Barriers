package com.haralanov.barriers;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    private final BarriersCommand commandExecutor;

    public BlockPlaceListener(BarriersCommand commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event) {
        if (event.getBlockPlaced().getTypeId() == commandExecutor.getPlacementID()
                && commandExecutor.getToggle().contains(event.getPlayer().getDisplayName())
                && event.getPlayer().hasPermission("barriers.place")) {
            event.getBlockPlaced().setTypeIdAndData(67, (byte) 4, false);
        }
    }
}
