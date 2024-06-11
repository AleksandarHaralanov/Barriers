package me.beezle.barrierblock;

import org.bukkit.plugin.java.JavaPlugin;

public class BarrierBlock extends JavaPlugin {

    @Override
    public void onEnable() {
        BarrierCommandExecutor commandExecutor = new BarrierCommandExecutor();
        getCommand("barrier").setExecutor(commandExecutor);
        getServer().getPluginManager().registerEvents(new BarrierPlaceListener(commandExecutor), this);
        getServer().getPluginManager().registerEvents(new BarrierBreakListener(commandExecutor), this);
        System.out.print("[BarrierBlock v1.0.0] Enabled.");
    }

    @Override
    public void onDisable() {
        System.out.print("[BarrierBlock v1.0.0] Disabled.");
    }
}