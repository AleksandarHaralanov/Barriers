package com.haralanov.barriers;

import java.util.List;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class BarriersCommand implements CommandExecutor {

    private final String NAME;
    private final String VERSION;
    private final String AUTHOR;
    private final String SOURCE;

    private static final List<String> isToggled = new ArrayList<>();

    public BarriersCommand(String NAME, String VERSION, String AUTHOR, String SOURCE) {
        this.NAME = NAME;
        this.VERSION = VERSION;
        this.AUTHOR = AUTHOR;
        this.SOURCE = SOURCE;
    }

    public List<String> getToggle() {
        return isToggled;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (commandSender instanceof Player) ? (Player) commandSender : null;

        if (command.getName().equalsIgnoreCase("barriers")) {
            if (player != null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        String.format("&e%s v%s &bby &e%s", NAME, VERSION, AUTHOR)));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        String.format("&bSource: &e%s", SOURCE)));
            } else {
                getLogger().info(String.format("%s v%s by %s", NAME, VERSION, AUTHOR));
                getLogger().info(String.format("Source: %s", SOURCE));
            }
        } else {
            if (player != null) {
                if (!(player.hasPermission("barriers.place") || player.isOp())) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            "&cYou do not have permission to place barriers."));
                } else {
                    toggleBarriers(player);
                }
            } else {
                getLogger().info("Terminals cannot place barriers.");
            }
        }

        return true;
    }

    private void toggleBarriers(Player player) {
        if (isToggled.contains(player.getDisplayName())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&bBarrier placement toggled &cOFF&b."));
            isToggled.remove(player.getDisplayName());
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&bBarrier placement toggled &aON&b."));
            isToggled.add(player.getDisplayName());
        }
    }
}
