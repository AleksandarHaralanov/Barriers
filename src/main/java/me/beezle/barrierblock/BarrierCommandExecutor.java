package me.beezle.barrierblock;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.ArrayList;

public class BarrierCommandExecutor implements CommandExecutor {

    List<String> toggle = new ArrayList<>();

    public List<String> getToggle() {
        return toggle;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("[BarrierBlock v1.0.0] You cannot run this command from the terminal.");
            return true;
        }

        if (!(commandSender.hasPermission("barrierblock.place") || !commandSender.isOp()) && (strings[0].equalsIgnoreCase("barrier") || strings[0].equalsIgnoreCase("b") || strings[0].equalsIgnoreCase("bb") || strings[0].equalsIgnoreCase("bar"))) {
            commandSender.sendMessage(ChatColor.RED + "You do not have permissions to place barriers.");
            return true;
        }

        if (toggle.contains(((Player) commandSender).getDisplayName())) {
            commandSender.sendMessage(ChatColor.RED + "Barrier placement toggled OFF.");
            toggle.remove(((Player) commandSender).getDisplayName());
        }
        else {
            commandSender.sendMessage(ChatColor.GREEN + "Barrier placement toggled ON.");
            toggle.add(((Player) commandSender).getDisplayName());
        }

        return true;
    }
}
