package me.fivespace.lockout;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFire implements CommandExecutor {

    public Lockout plugin;

    public CommandFire(Lockout plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!plugin.playing) {
            sender.sendMessage("§cThere is no ongoing game of Lockout");
            return false;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("§cPlayer not found");
            return false;
        }
        int i;
        try {
            i = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage("§cInvalid syntax");
            return false;
        }
        plugin.earnPlayer(i, target);
        return true;
    }
}
