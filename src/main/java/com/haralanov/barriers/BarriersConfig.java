package com.haralanov.barriers;

import java.io.File;

import org.bukkit.util.config.Configuration;

import static org.bukkit.Bukkit.getLogger;

public class BarriersConfig extends Configuration {

    public BarriersConfig(File file, String NAME) {
        super(file);
        if (!file.exists()) {
            final File parent = file.getParentFile();
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
        final String header = String.join("\n",
                "# 'placementID' - Enter the ID of the block to be used for barriers placement.",
                "# The ID must be between 1 and 255. Otherwise, the plugin will default to 49, which corresponds to Obsidian.");
        this.setHeader(header);
        if (this.getProperty("placementID") == null) {
            this.setProperty("placementID", 49);
        }
    }

    public int getPlacementID() {
        final Object value = this.getProperty("placementID");
        if ((Integer) value >= 1 && (Integer) value <= 255) {
            return (Integer) value;
        }
        return 49;
    }
}
