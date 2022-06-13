package me.fivespace.lockout;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class LockoutDisplay implements Listener {

    public Lockout plugin;
    public Inventory inv;

    public LockoutDisplay(Lockout plugin) {
        this.plugin = plugin;
        inv = Bukkit.createInventory(null, 45, "Lockout");
    }

    private ItemStack getSkull(UUID id) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(id));
        item.setItemMeta(meta);
        return item;
    }

    public void update() {
        inv.clear();
        int[] slotsA = {0, 9, 18, 27, 36, 1, 10, 19, 28, 37};
        int[] slotsB = {8, 17, 26, 35, 44, 7, 16, 25, 34, 43};
        for (int i = 0; i < 25; i++) {
            int j = 9 * (i / 5) + i % 5 + 2;

            ItemStack item;
            if (plugin.beaten[i] == 0) {
                item = plugin.tasks[i].display;
            } else if (plugin.beaten[i] == 1) {
                item = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
            } else {
                item = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
            }
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§r§a" + plugin.tasks[i].name);
            item.setItemMeta(meta);
            inv.setItem(j, item);
        }

        for (int i = 0; i < plugin.teamA.size(); i++)
            inv.setItem(slotsA[i], getSkull(plugin.teamA.get(i).getUniqueId()));
        for (int i = 0; i < plugin.teamB.size(); i++)
            inv.setItem(slotsB[i], getSkull(plugin.teamB.get(i).getUniqueId()));
    }

    public void showToAll() {
        for (Player player : plugin.teamA)
            if (player.isOnline()) showTo(player);
        for (Player player : plugin.teamB)
            if (player.isOnline()) showTo(player);
    }

    public void showTo(Player player) {
        player.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inv))
            e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryDragEvent e) {
        if (e.getInventory().equals(inv))
            e.setCancelled(true);
    }
}
