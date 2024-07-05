package com.haralanov.barriers;

import com.haralanov.utilities.UpdateUtil;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;

import static org.bukkit.Bukkit.getLogger;

public class Barriers extends JavaPlugin {

    private static String NAME;
    private static String VERSION;
    private static String AUTHOR;
    private static String SOURCE;
    private static BarriersConfig config;

    @Override
    public void onEnable() {
        final PluginDescriptionFile pdf = this.getDescription();

        VERSION = pdf.getVersion();
        NAME = pdf.getName();
        AUTHOR = pdf.getAuthors().get(0);
        SOURCE = pdf.getWebsite();

        config = new BarriersConfig(new File(getDataFolder(), "config.yml"), NAME);

        UpdateUtil.checkForUpdates(NAME, VERSION, "https://api.github.com/repos/AleksandarHaralanov/Barriers/releases/latest");

        final BarriersCommand barriersCommand = new BarriersCommand(NAME, VERSION, AUTHOR, SOURCE);
        getCommand("barrier").setExecutor(barriersCommand);
        getCommand("barriers").setExecutor(barriersCommand);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(barriersCommand), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPistonListener(), this);

        getLogger().info(String.format("[%s] v%s Enabled.", NAME, VERSION));
    }

    @Override
    public void onDisable() {
        getLogger().info(String.format("[%s] v%s Disabled.", NAME, VERSION));
    }

    public static BarriersConfig getConfig() {
        return config;
    }
}