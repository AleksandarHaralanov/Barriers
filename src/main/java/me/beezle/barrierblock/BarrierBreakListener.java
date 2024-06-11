package me.beezle.barrierblock;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class BarrierBreakListener implements Listener {

    private BarrierCommandExecutor commandExecutor;

    public BarrierBreakListener(BarrierCommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @EventHandler
    public void onBarrierBreak(final BlockBreakEvent event) {
        byte value = 5;
        if (event.getBlock().getTypeId() == 67
                && event.getBlock().getData() == value
                && !event.getPlayer().hasPermission("barrierblock.break")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You do not have permissions to break barrier blocks!");
        }
        else if (event.getBlock().getTypeId() == 67
                && event.getBlock().getData() == value
                && event.getPlayer().hasPermission("barrierblock.break")) {
            event.setCancelled(true);
            event.getBlock().setType(Material.AIR);
        }
    }
}
