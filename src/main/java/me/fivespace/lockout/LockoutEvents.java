package me.fivespace.lockout;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.bukkit.event.world.TimeSkipEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;
import org.spigotmc.event.entity.EntityMountEvent;

public class LockoutEvents implements Listener {

    public Lockout plugin;

    private static final List<Material> musicDiscs = Arrays.asList(
        Material.MUSIC_DISC_13,
        Material.MUSIC_DISC_CAT,
        Material.MUSIC_DISC_BLOCKS,
        Material.MUSIC_DISC_CHIRP,
        Material.MUSIC_DISC_FAR,
        Material.MUSIC_DISC_MALL,
        Material.MUSIC_DISC_MELLOHI,
        Material.MUSIC_DISC_STAL,
        Material.MUSIC_DISC_STRAD,
        Material.MUSIC_DISC_WARD,
        Material.MUSIC_DISC_11,
        Material.MUSIC_DISC_WAIT,
        Material.MUSIC_DISC_OTHERSIDE,
        Material.MUSIC_DISC_PIGSTEP
    );

    public LockoutEvents(Lockout plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClickCompass(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        if (item != null && item.getType() == Material.COMPASS
                && item.getItemMeta().getDisplayName().equals("§6§lLockout")) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (plugin.playing) {
                    plugin.display.showTo(e.getPlayer());
                }
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player player = e.getPlayer();
        if (plugin.playing && (plugin.teamA.contains(player) || plugin.teamB.contains(player))) {
            plugin.giveCompass(e.getPlayer());
        }
    }

    private void checkPlayer(Player player, TaskType type, Object... attr) {
        List<Object> args = Arrays.asList(attr);
        if (plugin.teamA.contains(player) || plugin.teamB.contains(player)) {
            for (int i = 0; i < 25; i++) {
                if (plugin.beaten[i] == 0) {
                    Task task = plugin.tasks[i];
                    if (task.type == type && args.equals(task.attr)) {
                        plugin.earnPlayer(i, player);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEffectChange(EntityPotionEffectEvent e) {
        if (plugin.playing && e.getEntity() instanceof Player) {
            checkPlayer((Player) e.getEntity(), TaskType.EFFECT, e.getModifiedType());
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (!plugin.playing) return;

        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (plugin.teamA.contains(player)) {
                LockoutTasks.OPPONENT_DIE.fire(2);
            } else if (plugin.teamB.contains(player)) {
                LockoutTasks.OPPONENT_DIE.fire(1);
            }
        }

        if (e.getEntity().getKiller() != null) {
            checkPlayer(e.getEntity().getKiller(), TaskType.KILL, e.getEntityType());
        }
    }

    @EventHandler
    public void onEntityDamageByBlock(EntityDamageByBlockEvent e) {
        if (plugin.playing && e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (player.getHealth() - e.getFinalDamage() <= 0) {
                checkPlayer(player, TaskType.DIE, e.getDamager().getType());
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (plugin.playing && e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (player.getHealth() - e.getFinalDamage() <= 0) {
                checkPlayer(player, TaskType.DIE, e.getDamager().getType());
            }
        }
    }

    @EventHandler
    public void onTame(EntityTameEvent e) {
        if (plugin.playing && e.getOwner() instanceof Player) {
            checkPlayer((Player) e.getOwner(), TaskType.TAME, e.getEntityType());
        }
    }

    @EventHandler
    public void onRide(EntityMountEvent e) {
        if (plugin.playing && e.getEntity() instanceof Player) {
            checkPlayer((Player) e.getEntity(), TaskType.RIDE, e.getMount().getType());
        }
    }

    @EventHandler
    public void onBreed(EntityBreedEvent e) {
        if (plugin.playing && e.getBreeder() instanceof Player) {
            checkPlayer((Player) e.getBreeder(), TaskType.BREED, e.getEntityType());
        }
    }

    @EventHandler
    public void onMine(BlockBreakEvent e) {
        if (plugin.playing) {
            checkPlayer(e.getPlayer(), TaskType.MINE, e.getBlock().getType());
        }
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        if (plugin.playing) {
            checkPlayer(e.getPlayer(), TaskType.EAT, e.getItem().getType());
        }
    }

    @EventHandler
    public void onExp(PlayerExpChangeEvent e) {
        if (plugin.playing && e.getPlayer().getLevel() >= 15) {
            LockoutTasks.LEVEL_15.fire(e.getPlayer());
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (plugin.playing && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock().getType() == Material.JUKEBOX && musicDiscs.contains(e.getItem().getType())) {
                LockoutTasks.MUSIC_DISC.fire(e.getPlayer());
            } else if ((e.getClickedBlock().getType() == Material.CRIMSON_SIGN || e.getClickedBlock().getType() == Material.CRIMSON_WALL_SIGN) && e.getItem().getType() == Material.GLOW_INK_SAC) {
                LockoutTasks.CRIMSON_SIGN_GLOW.fire(e.getPlayer());
            } else if (e.getClickedBlock().getType() == Material.COMPOSTER && e.getClickedBlock().getBlockData().getAsString().equals("minecraft:composter[level=8]")) {
                LockoutTasks.COMPOSTER.fire(e.getPlayer());
            }
        }
    }

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent e) {
        if (plugin.playing) {
            checkPlayer(e.getPlayer(), TaskType.DIMENSION, e.getFrom().getEnvironment(), e.getPlayer().getWorld().getEnvironment());
        }
    }

    @EventHandler
    public void onInventory(InventoryClickEvent e) {
        if (plugin.playing && e.getSlotType() == SlotType.RESULT && e.getCursor().getType() == Material.AIR && e.getCurrentItem().getType() != Material.AIR) {
            if (e.getWhoClicked() instanceof Player) {
                Player player = (Player) e.getWhoClicked();
                checkPlayer(player, TaskType.CRAFT, e.getInventory().getType());
            }
        }
    }

    @EventHandler
    public void onSleep(TimeSkipEvent e) {
        if (plugin.playing && e.getSkipReason() == TimeSkipEvent.SkipReason.NIGHT_SKIP) {
            for (Player player : plugin.teamA) {
                if (player.isSleeping()) {
                    LockoutTasks.SLEEP.fire(player);
                    return;
                }
            }
            for (Player player : plugin.teamB) {
                if (player.isSleeping()) {
                    LockoutTasks.SLEEP.fire(player);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onStatistic(PlayerStatisticIncrementEvent e) {
        if (plugin.playing && e.getStatistic() == Statistic.JUMP) {
            Player player = e.getPlayer();
            if (plugin.teamA.contains(player)) {
                LockoutTasks.OPPONENT_JUMP.fire(2);
            } else if (plugin.teamB.contains(player)) {
                LockoutTasks.OPPONENT_JUMP.fire(1);
            }
        }
    }

    @EventHandler
    public void onStarve(FoodLevelChangeEvent e) {
        if (plugin.playing && e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (player.getFoodLevel() <= 0) {
                LockoutTasks.EMPTY_HUNGER.fire(player);
            }
        }
    }

    @EventHandler
    public void onEgg(PlayerEggThrowEvent e) {
        if (plugin.playing && e.isHatching()) {
            LockoutTasks.SPAWN_CHICKEN.fire(e.getPlayer());
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        if (plugin.playing) {
            checkPlayer((Player) e.getWhoClicked(), TaskType.CRAFT, e.getView().getType());
        }
    }

    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent e) {
        if (plugin.playing) {
            checkPlayer(e.getPlayer(), TaskType.ADVANCEMENT, e.getAdvancement().getKey().toString());
        }
    }

    @EventHandler
    public void onProjectile(ProjectileHitEvent e) {
        if (plugin.playing && e.getHitEntity() != null && e.getHitEntity() instanceof Player) {
            Player player = (Player) e.getHitEntity();
            if (plugin.teamA.contains(player)) {
                LockoutTasks.OPPONENT_SNOWBALL.fire(2);
            } else if (plugin.teamB.contains(player)) {
                LockoutTasks.OPPONENT_SNOWBALL.fire(1);
            }
        }
    }

    public void tickPlayer(Player player) {
        for (int i = 0; i < 25; i++) {
            if (plugin.beaten[i] != 0) continue;

            Task task = plugin.tasks[i];
            if (task.type == TaskType.ITEM) {
                if (task.any) {
                    for (Object mat : task.attr) {
                        if (player.getInventory().contains((Material) mat)) {
                            plugin.earnPlayer(i, player);
                            break;
                        }
                    }
                } else {
                    boolean flag = true;
                    for (Object mat : task.attr) {
                        if (!player.getInventory().contains((Material) mat)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) plugin.earnPlayer(i, player);
                }
            } else if (task.type == TaskType.BREW) {
                for (ItemStack item : player.getInventory().getContents()) {
                    if (item != null && item.getItemMeta() instanceof PotionMeta) {
                        PotionMeta meta = (PotionMeta) item.getItemMeta();
                        if (meta.getBasePotionData().getType() == (PotionType) task.attr.get(0)) {
                            plugin.earnPlayer(i, player);
                            break;
                        }
                    }
                }
            }
        }

        checkPlayer(player, TaskType.BIOME, player.getLocation().getBlock().getBiome());

        if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.BEDROCK) {
            LockoutTasks.BEDROCK.fire(player);
        }

        if (player.getLocation().getBlockY() >= 318) {
            LockoutTasks.SKY_LIMIT.fire(player);
        }

        int other;
        if (plugin.teamA.contains(player)) other = 2;
        else if (plugin.teamB.contains(player)) other = 1;
        else return;

        if (player.getInventory().contains(Material.OBSIDIAN)) LockoutTasks.OPPONENT_OBSIDIAN.fire(other);
        if (player.getInventory().contains(Material.WHEAT_SEEDS)) LockoutTasks.OPPONENT_SEEDS.fire(other);
        if (player.getInventory().contains(Material.CRAFTING_TABLE)) LockoutTasks.OPPONENT_CRAFTING_TABLE.fire(other);
    }
}
