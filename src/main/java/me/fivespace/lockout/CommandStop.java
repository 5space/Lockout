package me.fivespace.lockout;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandStop implements CommandExecutor {

    public Lockout plugin;

    public CommandStop(Lockout plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (plugin.playing) {
            sender.sendMessage("Stopped the Lockout game");
            plugin.endGame(0);
            return true;
        } else {
            sender.sendMessage("§cThere is no Lockout game to stop");
            return false;
        }
    }
}
