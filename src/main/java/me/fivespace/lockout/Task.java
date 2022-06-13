package me.fivespace.lockout;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Task {

    public Lockout plugin;
    public String name;
    public ItemStack display;

    public boolean any;

    public List<Object> attr;
    public TaskType type;

    public Task(Lockout plugin, String name, Material display, TaskType type) {
        this.plugin = plugin;
        this.name = name;
        this.display = new ItemStack(display);
        this.any = false;

        this.type = type;
        if (type == TaskType.CUSTOM) {
            this.attr = null;
        } else {
            this.attr = Collections.singletonList(display);
        }
    }

    public Task(Lockout plugin, String name, Material display, TaskType type, Object... attr) {
        this.plugin = plugin;
        this.name = name;
        this.display = new ItemStack(display);
        this.any = false;

        this.type = type;
        this.attr = Arrays.asList(attr);
    }

    public Task(Lockout plugin, String name, ItemStack display, TaskType type, Object... attr) {
        this.plugin = plugin;
        this.name = name;
        this.display = display;
        this.any = false;

        this.type = type;
        this.attr = Arrays.asList(attr);
    }

    public Task allowAny() {
        this.any = true;
        return this;
    }

    public void fire(Player player) {
        if (plugin.playing) {
            for (int i = 0; i < 25; i++) {
                if (plugin.tasks[i].equals(this)) {
                    plugin.earnPlayer(i, player);
                }
            }
        }
    }

    public void fire(int team) {
        if (plugin.playing) {
            for (int i = 0; i < 25; i++) {
                if (plugin.tasks[i].equals(this)) {
                    plugin.earnTeam(i, team);
                }
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Task)) return false;
        // assume tasks have unique names
        Task task = (Task) other;
        return name.equals(task.name);
    }
}
