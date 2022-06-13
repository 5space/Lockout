package me.fivespace.lockout;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandStart implements CommandExecutor {

    public Lockout plugin;

    public CommandStart(Lockout plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!plugin.playing) {
            sender.sendMessage("Started a new Lockout game");
            plugin.startGame();
            return true;
        } else {
            sender.sendMessage("§cThere is already an ongoing game of Lockout");
            return false;
        }
    }
}
