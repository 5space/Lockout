package me.fivespace.lockout;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTeam implements CommandExecutor {

    Lockout plugin;

    public CommandTeam(Lockout plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        if (args.length == 0 || (!args[0].equalsIgnoreCase("a")
                && !args[0].equalsIgnoreCase("b"))) {
            sender.sendMessage("§cInvalid syntax");
            return false;
        }
        Player player = (Player) sender;
        if (args[0].equalsIgnoreCase("a")) {
            plugin.teamB.remove(player);
            if (plugin.teamA.contains(player)) {
                Bukkit.broadcastMessage("§6" + player.getDisplayName() + "§r§6 has left team A.");
                plugin.teamA.remove(player);
            } else {
                Bukkit.broadcastMessage("§6" + player.getDisplayName() + "§r§6 has joined team A.");
                plugin.teamA.add(player);
                if (plugin.playing) plugin.giveCompass(player);
            }
        } else {
            plugin.teamA.remove(player);
            if (plugin.teamB.contains(player)) {
                Bukkit.broadcastMessage("§9" + player.getDisplayName() + "§r§6 has left team B.");
                plugin.teamB.remove(player);
            } else {
                Bukkit.broadcastMessage("§9" + player.getDisplayName() + "§r§9 has joined team B.");
                plugin.teamB.add(player);
                if (plugin.playing) plugin.giveCompass(player);
            }
        }
        return true;
    }
}
