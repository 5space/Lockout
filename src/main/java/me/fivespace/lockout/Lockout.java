package me.fivespace.lockout;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class Lockout extends JavaPlugin {

    public boolean playing;

    public Task[] tasks;
    public int[] beaten;

    public ArrayList<Player> teamA;
    public ArrayList<Player> teamB;

    public LockoutDisplay display;

    @Override
    public void onEnable() {
        playing = false;
        teamA = new ArrayList<>();
        teamB = new ArrayList<>();

        LockoutTasks.init(this);

        display = new LockoutDisplay(this);
        LockoutEvents events = new LockoutEvents(this);
        getServer().getPluginManager().registerEvents(display, this);
        getServer().getPluginManager().registerEvents(events, this);

        this.getCommand("lostart").setExecutor(new CommandStart(this));
        this.getCommand("lostop").setExecutor(new CommandStop(this));
        this.getCommand("loteam").setExecutor(new CommandTeam(this));
        this.getCommand("lofire").setExecutor(new CommandFire(this));

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (!playing) return;
                for (Player player : teamA) {
                    if (player.isOnline()) events.tickPlayer(player);
                }
                for (Player player : teamB) {
                    if (player.isOnline()) events.tickPlayer(player);
                }
            }
        }, 0, 20);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void earnPlayer(int index, Player player) {
        if (beaten[index] != 0 || !playing) return;
        if (teamA.contains(player)) {
            beaten[index] = 1;
            Bukkit.broadcastMessage("§6" + player.getDisplayName() + "§r§6 just got " + tasks[index].name + "!");
            display.update();
            checkWin();
        } else if (teamB.contains(player)) {
            beaten[index] = 2;
            Bukkit.broadcastMessage("§9" + player.getDisplayName() + "§r§9 just got " + tasks[index].name + "!");
            display.update();
            checkWin();
        }
    }

    public void earnTeam(int index, int team) {
        if (beaten[index] != 0 || !playing) return;
        if (team == 1) {
            beaten[index] = 1;
            Bukkit.broadcastMessage("§6Team A just got " + tasks[index].name + "!");
            display.update();
            checkWin();
        } else if (team == 2) {
            beaten[index] = 2;
            Bukkit.broadcastMessage("§9Team B just got " + tasks[index].name + "!");
            display.update();
            checkWin();
        }
    }

    public void checkWin() {
        int amtA = 0, amtB = 0;
        for (int i = 0; i < 25; i++) {
            if (beaten[i] == 1) {
                amtA += 1;
            } else if (beaten[i] == 2) {
                amtB += 1;
            }
        }
        if (amtA >= 13) {
            endGame(1);
        } else if (amtB >= 13) {
            endGame(2);
        }
    }

    public void startGame() {
        playing = true;
        beaten = new int[25];

        Collections.shuffle(LockoutTasks.allTasks);

        tasks = new Task[25];
        for (int i = 0; i < 25; i++) {
            tasks[i] = LockoutTasks.allTasks.get(i);
        }

        display.update();
        display.showToAll();

        for (Player player : teamA) {
            if (player.isOnline()) giveCompass(player);
        }
        for (Player player : teamB) {
            if (player.isOnline()) giveCompass(player);
        }
    }

    public void endGame(int winner) {
        playing = false;
        if (winner == 0) {
            Bukkit.broadcastMessage("§aThe Lockout game ended with no winner.");
        } else if (winner == 1) {
            Bukkit.broadcastMessage("§aThe Lockout game ended, and §6team A §awas the winner!");
        } else {
            Bukkit.broadcastMessage("§aThe Lockout game ended, and §9team B §awas the winner!");
        }
    }

    public void giveCompass(Player player) {
        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6§lLockout");
        item.setItemMeta(meta);
        player.getInventory().addItem(item);
    }
}
