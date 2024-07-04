package com.haralanov.barriers;

import java.io.File;

import org.bukkit.util.config.Configuration;

import static org.bukkit.Bukkit.getLogger;

public class BarriersConfig extends Configuration {

    public BarriersConfig(File file, String NAME) {
        super(file);
        if (!file.exists()) {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                getLogger().severe(String.format("[%s] Failed to create directories for config: %s", NAME, file.getAbsolutePath()));
            }
            write();
        }
        load();
    }

    private void write() {
        generate();
        save();
    }

    private void generate() {
        String header = "# 'placementBlock' - Enter the ID of the block to be used for barriers placement.";
        this.setHeader(header);
        if (this.getProperty("placementBlock") == null) {
            this.setProperty("placementBlock", 49);
        }
    }

    public int getPlacementBlock(String key, int defaultValue) {
        Object value = this.getProperty(key);
        if (value instanceof Integer) {
            return (Integer) value;
        }
        return defaultValue;
    }
}
