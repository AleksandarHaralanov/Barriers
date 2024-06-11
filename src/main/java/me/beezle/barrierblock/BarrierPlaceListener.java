package me.beezle.barrierblock;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

public class BarrierPlaceListener implements Listener {

    private BarrierCommandExecutor commandExecutor;

    public BarrierPlaceListener(BarrierCommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @EventHandler
    public void onBarrierPlace(final BlockPlaceEvent event) {
        if (event.getBlockPlaced().getTypeId() == 49
                && commandExecutor.getToggle().contains(event.getPlayer().getDisplayName())
                && event.getPlayer().hasPermission("barrierblock.place")) {
            byte value = 5;
            event.getBlockPlaced().setTypeIdAndData(67, value, false);
        }
    }
}
