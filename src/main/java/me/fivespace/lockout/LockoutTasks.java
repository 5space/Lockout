package me.fivespace.lockout;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class LockoutTasks {

    public static ArrayList<Task> allTasks;

    public static Task COMPOSTER;
    public static Task OPPONENT_DIE;
    public static Task OPPONENT_FALL;
    public static Task OPPONENT_FIRE;
    public static Task OPPONENT_CRAFTING_TABLE;
    public static Task OPPONENT_OBSIDIAN;
    public static Task OPPONENT_SEEDS;
    public static Task OPPONENT_JUMP;
    public static Task OPPONENT_SNOWBALL;
    public static Task SLEEP;
    public static Task MUSIC_DISC;
    public static Task SPAWN_CHICKEN;
    public static Task EMPTY_HUNGER;
    public static Task BEDROCK;
    public static Task SKY_LIMIT;
    public static Task LEVEL_15;
    public static Task CRIMSON_SIGN_GLOW;

    private static ItemStack getPotionWithEffect(PotionType type) {
        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.setBasePotionData(new PotionData(type));
        potion.setItemMeta(meta);
        return potion;
    }

    public static void init(Lockout plugin) {
        allTasks = new ArrayList<>();
        allTasks.add(new Task(plugin, "Obtain Respawn Anchor", Material.RESPAWN_ANCHOR, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Red Nether Brick Stairs", Material.RED_NETHER_BRICK_STAIRS, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Sea Lantern", Material.SEA_LANTERN, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Bookshelf", Material.BOOKSHELF, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Mossy Stone Brick Wall", Material.MOSSY_STONE_BRICK_WALL, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Scaffolding", Material.SCAFFOLDING, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain End Crystal", Material.END_CRYSTAL, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Bell", Material.BELL, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Bottle o' Enchanting", Material.EXPERIENCE_BOTTLE, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Slime Block", Material.SLIME_BLOCK, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Soul Lantern", Material.SOUL_LANTERN, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Honey Bottle", Material.HONEY_BOTTLE, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Ancient Debris", Material.ANCIENT_DEBRIS, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Cake", Material.CAKE, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Ender Chest", Material.ENDER_CHEST, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Heart of the Sea", Material.HEART_OF_THE_SEA, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Wither Skeleton Skull", Material.WITHER_SKELETON_SKULL, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Lodestone", Material.LODESTONE, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain End Rod", Material.END_ROD, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Sponge", Material.SPONGE, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Mushroom Stem", Material.MUSHROOM_STEM, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Dragon Egg", Material.DRAGON_EGG, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Every Type of Sapling", Material.OAK_SAPLING, TaskType.ITEM, Material.OAK_SAPLING, Material.SPRUCE_SAPLING, Material.BIRCH_SAPLING, Material.ACACIA_SAPLING, Material.JUNGLE_SAPLING, Material.DARK_OAK_SAPLING));
        allTasks.add(new Task(plugin, "Obtain Black Glazed Terracotta", Material.BLACK_GLAZED_TERRACOTTA, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Blue Glazed Terracotta", Material.BLUE_GLAZED_TERRACOTTA, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Brown Glazed Terracotta", Material.BROWN_GLAZED_TERRACOTTA, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Cyan Glazed Terracotta", Material.CYAN_GLAZED_TERRACOTTA, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Gray Glazed Terracotta", Material.GRAY_GLAZED_TERRACOTTA, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Green Glazed Terracotta", Material.GREEN_GLAZED_TERRACOTTA, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Light Blue Glazed Terracotta", Material.LIGHT_BLUE_GLAZED_TERRACOTTA, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Light Gray Glazed Terracotta", Material.LIGHT_GRAY_GLAZED_TERRACOTTA, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Lime Glazed Terracotta", Material.LIME_GLAZED_TERRACOTTA, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Magenta Glazed Terracotta", Material.MAGENTA_GLAZED_TERRACOTTA, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Orange Glazed Terracotta", Material.ORANGE_GLAZED_TERRACOTTA, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Pink Glazed Terracotta", Material.PINK_GLAZED_TERRACOTTA, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Purple Glazed Terracotta", Material.PURPLE_GLAZED_TERRACOTTA, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Red Glazed Terracotta", Material.RED_GLAZED_TERRACOTTA, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain White Glazed Terracotta", Material.WHITE_GLAZED_TERRACOTTA, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Yellow Glazed Terracotta", Material.YELLOW_GLAZED_TERRACOTTA, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Bucket of Tropical Fish", Material.TROPICAL_FISH_BUCKET, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Powdered Snow Bucket", Material.POWDER_SNOW_BUCKET, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain Flowering Azalea", Material.FLOWERING_AZALEA, TaskType.ITEM));
        allTasks.add(new Task(plugin, "Obtain All Horse Armor", Material.IRON_HORSE_ARMOR, TaskType.ITEM, Material.LEATHER_HORSE_ARMOR, Material.IRON_HORSE_ARMOR, Material.GOLDEN_HORSE_ARMOR, Material.DIAMOND_HORSE_ARMOR));
        allTasks.add(new Task(plugin, "Full Set of Wooden Tools", Material.WOODEN_PICKAXE, TaskType.ITEM, Material.WOODEN_PICKAXE, Material.WOODEN_AXE, Material.WOODEN_HOE, Material.WOODEN_SHOVEL, Material.WOODEN_SWORD));
        allTasks.add(new Task(plugin, "Full Set of Stone Tools", Material.STONE_PICKAXE, TaskType.ITEM, Material.STONE_PICKAXE, Material.STONE_AXE, Material.STONE_HOE, Material.STONE_SHOVEL, Material.STONE_SWORD));
        allTasks.add(new Task(plugin, "Full Set of Golden Tools", Material.GOLDEN_PICKAXE, TaskType.ITEM, Material.GOLDEN_PICKAXE, Material.GOLDEN_AXE, Material.GOLDEN_HOE, Material.GOLDEN_SHOVEL, Material.GOLDEN_SWORD));
        allTasks.add(new Task(plugin, "Full Set of Iron Tools", Material.IRON_PICKAXE, TaskType.ITEM, Material.IRON_PICKAXE, Material.IRON_AXE, Material.IRON_HOE, Material.IRON_SHOVEL, Material.IRON_SWORD));
        allTasks.add(new Task(plugin, "Full Set of Diamond Tools", Material.DIAMOND_PICKAXE, TaskType.ITEM, Material.DIAMOND_PICKAXE, Material.DIAMOND_AXE, Material.DIAMOND_HOE, Material.DIAMOND_SHOVEL, Material.DIAMOND_SWORD));
        allTasks.add(new Task(plugin, "Full Set of Leather Armor", Material.LEATHER_CHESTPLATE, TaskType.ITEM, Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS));
        allTasks.add(new Task(plugin, "Full Set of Golden Armor", Material.GOLDEN_CHESTPLATE, TaskType.ITEM, Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS));
        allTasks.add(new Task(plugin, "Full Set of Iron Armor", Material.IRON_CHESTPLATE, TaskType.ITEM, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS));
        allTasks.add(new Task(plugin, "Full Set of Diamond Armor", Material.DIAMOND_CHESTPLATE, TaskType.ITEM, Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS));
        allTasks.add(new Task(plugin, "Any Netherite Armor", Material.NETHERITE_HELMET, TaskType.ITEM, Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS).allowAny());
        allTasks.add(new Task(plugin, "Any Chainmail Armor", Material.CHAINMAIL_HELMET, TaskType.ITEM, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS).allowAny());
        allTasks.add(new Task(plugin, "Write a Book", Material.WRITABLE_BOOK, TaskType.ITEM, Material.WRITTEN_BOOK));

        allTasks.add(new Task(plugin, "Find an Ice Spike Biome", Material.PACKED_ICE, TaskType.BIOME, Biome.ICE_SPIKES));
        allTasks.add(new Task(plugin, "Find a Mushroom Biome", Material.MYCELIUM, TaskType.BIOME, Biome.MUSHROOM_FIELDS));
        allTasks.add(new Task(plugin, "Find a Badlands Biome", Material.RED_SAND, TaskType.BIOME, Biome.BADLANDS));

        allTasks.add(new Task(plugin, "Get Nausea", Material.PUFFERFISH, TaskType.EFFECT, PotionEffectType.CONFUSION));
        allTasks.add(new Task(plugin, "Get Jump Boost", Material.RABBIT_FOOT, TaskType.EFFECT, PotionEffectType.JUMP));
        allTasks.add(new Task(plugin, "Get Absorption", Material.GOLDEN_APPLE, TaskType.EFFECT, PotionEffectType.ABSORPTION));
        allTasks.add(new Task(plugin, "Get Levitation", Material.SHULKER_SHELL, TaskType.EFFECT, PotionEffectType.LEVITATION));
        allTasks.add(new Task(plugin, "Get Glowing", Material.SPECTRAL_ARROW, TaskType.EFFECT, PotionEffectType.GLOWING));
        allTasks.add(new Task(plugin, "Get Mining Fatigue", Material.PRISMARINE, TaskType.EFFECT, PotionEffectType.SLOW_DIGGING));
        allTasks.add(new Task(plugin, "Get Bad Omen", Material.CROSSBOW, TaskType.EFFECT, PotionEffectType.BAD_OMEN));

        allTasks.add(new Task(plugin, "Brew Potion of Healing", getPotionWithEffect(PotionType.INSTANT_HEAL), TaskType.BREW, PotionType.INSTANT_HEAL));
        allTasks.add(new Task(plugin, "Brew Potion of Leaping", getPotionWithEffect(PotionType.JUMP), TaskType.BREW, PotionType.JUMP));
        allTasks.add(new Task(plugin, "Brew Potion of Swiftness", getPotionWithEffect(PotionType.SPEED), TaskType.BREW, PotionType.SPEED));
        allTasks.add(new Task(plugin, "Brew Potion of Invisibility", getPotionWithEffect(PotionType.INVISIBILITY), TaskType.BREW, PotionType.INVISIBILITY));
        allTasks.add(new Task(plugin, "Brew Potion of Water Breathing", getPotionWithEffect(PotionType.WATER_BREATHING), TaskType.BREW, PotionType.WATER_BREATHING));
        allTasks.add(new Task(plugin, "Brew Lingering Potion", Material.LINGERING_POTION, TaskType.ITEM, Material.LINGERING_POTION));

        allTasks.add(new Task(plugin, "Die by Bee Sting", Material.HONEYCOMB, TaskType.DIE, EntityType.BEE));
        allTasks.add(new Task(plugin, "Die by Llama", Material.LEATHER, TaskType.DIE, EntityType.LLAMA_SPIT));
        allTasks.add(new Task(plugin, "Die by Cactus", Material.CACTUS, TaskType.DIE, Material.CACTUS));
        allTasks.add(new Task(plugin, "Die by Berry Bush", Material.SWEET_BERRIES, TaskType.DIE, Material.SWEET_BERRY_BUSH));
        allTasks.add(new Task(plugin, "Die by Anvil", Material.ANVIL, TaskType.DIE, EntityType.FALLING_BLOCK));
        allTasks.add(new Task(plugin, "Die by Firework Rocket", Material.FIREWORK_ROCKET, TaskType.DIE, EntityType.FIREWORK));
        allTasks.add(new Task(plugin, "Die by Magic", Material.SPLASH_POTION, TaskType.DIE, EntityType.SPLASH_POTION));
        allTasks.add(new Task(plugin, "Die by Stalactite", Material.POINTED_DRIPSTONE, TaskType.DIE, Material.POINTED_DRIPSTONE));

        allTasks.add(new Task(plugin, "Mine Diamond Ore", Material.DIAMOND_ORE, TaskType.MINE, Material.DIAMOND_ORE));
        allTasks.add(new Task(plugin, "Mine Emerald Ore", Material.EMERALD_ORE, TaskType.MINE, Material.EMERALD_ORE));
        allTasks.add(new Task(plugin, "Mine Mob Spawner", Material.SPAWNER, TaskType.MINE, Material.SPAWNER));
        allTasks.add(new Task(plugin, "Mine Turtle Egg", Material.TURTLE_EGG, TaskType.MINE, Material.TURTLE_EGG));

        allTasks.add(new Task(plugin, "Breed Cow", Material.COW_SPAWN_EGG, TaskType.BREED, EntityType.COW));
        allTasks.add(new Task(plugin, "Breed Sheep", Material.SHEEP_SPAWN_EGG, TaskType.BREED, EntityType.SHEEP));
        allTasks.add(new Task(plugin, "Breed Chicken", Material.CHICKEN_SPAWN_EGG, TaskType.BREED, EntityType.CHICKEN));
        allTasks.add(new Task(plugin, "Breed Pig", Material.PIG_SPAWN_EGG, TaskType.BREED, EntityType.PIG));
        allTasks.add(new Task(plugin, "Breed Horse", Material.HORSE_SPAWN_EGG, TaskType.BREED, EntityType.HORSE));
        allTasks.add(new Task(plugin, "Breed Hoglin", Material.HOGLIN_SPAWN_EGG, TaskType.BREED, EntityType.HOGLIN));

        allTasks.add(new Task(plugin, "Ride Strider", Material.WARPED_FUNGUS_ON_A_STICK, TaskType.RIDE, EntityType.STRIDER));
        allTasks.add(new Task(plugin, "Ride Pig", Material.CARROT_ON_A_STICK, TaskType.RIDE, EntityType.PIG));
        allTasks.add(new Task(plugin, "Ride Horse", Material.SADDLE, TaskType.RIDE, EntityType.HORSE));
        allTasks.add(new Task(plugin, "Ride Minecart", Material.MINECART, TaskType.RIDE, EntityType.MINECART));

        allTasks.add(new Task(plugin, "Tame Cat", Material.COD, TaskType.TAME, EntityType.CAT));
        allTasks.add(new Task(plugin, "Tame Horse", Material.SADDLE, TaskType.TAME, EntityType.HORSE));
        allTasks.add(new Task(plugin, "Tame Parrot", Material.FEATHER, TaskType.TAME, EntityType.PARROT));
        allTasks.add(new Task(plugin, "Tame Wolf", Material.BONE, TaskType.TAME, EntityType.WOLF));

        allTasks.add(new Task(plugin, "Kill Witch", Material.WITCH_SPAWN_EGG, TaskType.KILL, EntityType.WITCH));
        allTasks.add(new Task(plugin, "Kill Zombie Villager", Material.ZOMBIE_VILLAGER_SPAWN_EGG, TaskType.KILL, EntityType.ZOMBIE_VILLAGER));
        allTasks.add(new Task(plugin, "Kill Stray", Material.STRAY_SPAWN_EGG, TaskType.KILL, EntityType.STRAY));
        allTasks.add(new Task(plugin, "Kill Zoglin", Material.ZOGLIN_SPAWN_EGG, TaskType.KILL, EntityType.ZOGLIN));
        allTasks.add(new Task(plugin, "Kill Silverfish", Material.SILVERFISH_SPAWN_EGG, TaskType.KILL, EntityType.SILVERFISH));
        allTasks.add(new Task(plugin, "Kill Guardian", Material.GUARDIAN_SPAWN_EGG, TaskType.KILL, EntityType.GUARDIAN));
        allTasks.add(new Task(plugin, "Kill Ghast", Material.GHAST_SPAWN_EGG, TaskType.KILL, EntityType.GHAST));
        allTasks.add(new Task(plugin, "Kill Snow Golem", Material.SNOW_BLOCK, TaskType.KILL, EntityType.SNOWMAN));
        allTasks.add(new Task(plugin, "Kill Player", Material.PLAYER_HEAD, TaskType.KILL, EntityType.PLAYER));

        allTasks.add(new Task(plugin, "Eat Pumpkin Pie", Material.PUMPKIN_PIE, TaskType.EAT, Material.PUMPKIN_PIE));
        allTasks.add(new Task(plugin, "Eat Rabbit Stew", Material.RABBIT_STEW, TaskType.EAT, Material.RABBIT_STEW));
        allTasks.add(new Task(plugin, "Eat Suspicious Stew", Material.SUSPICIOUS_STEW, TaskType.EAT, Material.SUSPICIOUS_STEW));
        allTasks.add(new Task(plugin, "Eat Cookie", Material.COOKIE, TaskType.EAT, Material.COOKIE));
        allTasks.add(new Task(plugin, "Eat Chorus Fruit", Material.CHORUS_FRUIT, TaskType.EAT, Material.CHORUS_FRUIT));
        allTasks.add(new Task(plugin, "Eat Poisonous Potato", Material.POISONOUS_POTATO, TaskType.EAT, Material.POISONOUS_POTATO));
        allTasks.add(new Task(plugin, "Eat Glow Berries", Material.GLOW_BERRIES, TaskType.EAT, Material.GLOW_BERRIES));

        allTasks.add(new Task(plugin, "Enter Nether", Material.NETHERRACK, TaskType.DIMENSION, World.Environment.NORMAL, World.Environment.NETHER));
        allTasks.add(new Task(plugin, "Enter End", Material.NETHERRACK, TaskType.DIMENSION, World.Environment.NORMAL, World.Environment.THE_END));
        allTasks.add(new Task(plugin, "Leave End", Material.NETHERRACK, TaskType.DIMENSION, World.Environment.THE_END, World.Environment.NORMAL));

        allTasks.add(new Task(plugin, "Find a Fortress", Material.NETHER_BRICKS, TaskType.ADVANCEMENT, "minecraft:nether/find_fortress"));
        allTasks.add(new Task(plugin, "Find a Bastion", Material.POLISHED_BLACKSTONE_BRICKS, TaskType.ADVANCEMENT, "minecraft:nether/find_bastion"));
        allTasks.add(new Task(plugin, "Find a Stronghold", Material.STONE_BRICKS, TaskType.ADVANCEMENT, "minecraft:story/follow_ender_eye"));
        allTasks.add(new Task(plugin, "Find an End City", Material.PURPUR_BLOCK, TaskType.ADVANCEMENT, "minecraft:end/find_end_city"));
        allTasks.add(new Task(plugin, "Trade with a Villager", Material.EMERALD, TaskType.ADVANCEMENT, "minecraft:adventure/trade"));
        allTasks.add(new Task(plugin, "Distract a Piglin with Gold", Material.GOLD_INGOT, TaskType.ADVANCEMENT, "minecraft:nether/distract_piglin"));
        allTasks.add(new Task(plugin, "Look at a Ghast with your Spyglass", Material.SPYGLASS, TaskType.ADVANCEMENT, "minecraft:adventure/spyglass_at_ghast"));
        allTasks.add(new Task(plugin, "Use an Enchanting Table", Material.ENCHANTING_TABLE, TaskType.ADVANCEMENT, "minecraft:story/enchant_item"));

        allTasks.add(new Task(plugin, "Use a Smithing Table", Material.SMITHING_TABLE, TaskType.CRAFT, InventoryType.SMITHING));
        allTasks.add(new Task(plugin, "Use an Anvil", Material.ANVIL, TaskType.CRAFT, InventoryType.ANVIL));
        allTasks.add(new Task(plugin, "Use a Loom", Material.LOOM, TaskType.CRAFT, InventoryType.LOOM));

        COMPOSTER = new Task(plugin, "Use a Composter", Material.COMPOSTER, TaskType.CUSTOM);
        OPPONENT_DIE = new Task(plugin, "The Opponent Dies", Material.SKELETON_SKULL, TaskType.CUSTOM);
        OPPONENT_FALL = new Task(plugin, "The Opponent Takes Fall Damage", Material.LEATHER_BOOTS, TaskType.CUSTOM);
        OPPONENT_FIRE = new Task(plugin, "The Opponent Catches on Fire", Material.FLINT_AND_STEEL, TaskType.CUSTOM);
        OPPONENT_CRAFTING_TABLE = new Task(plugin, "The Opponent Obtains a Crafting Table", Material.CRAFTING_TABLE, TaskType.CUSTOM);
        OPPONENT_OBSIDIAN = new Task(plugin, "The Opponent Obtains Obsidian", Material.OBSIDIAN, TaskType.CUSTOM);
        OPPONENT_SEEDS = new Task(plugin, "The Opponent Obtains Seeds", Material.WHEAT_SEEDS, TaskType.CUSTOM);
        OPPONENT_JUMP = new Task(plugin, "The Opponent Jumps", Material.SLIME_BLOCK, TaskType.CUSTOM);
        OPPONENT_SNOWBALL = new Task(plugin, "Other team is hit by Snowball", Material.SNOWBALL, TaskType.CUSTOM);
        SLEEP = new Task(plugin, "Sleep Alone in the Overworld", Material.RED_BED, TaskType.CUSTOM);
        MUSIC_DISC = new Task(plugin, "Play a Music Disc", Material.MUSIC_DISC_OTHERSIDE, TaskType.CUSTOM);
        SPAWN_CHICKEN = new Task(plugin, "Spawn a Chicken with an Egg", Material.EGG, TaskType.CUSTOM);
        EMPTY_HUNGER = new Task(plugin, "Empty Hunger Bar", Material.ROTTEN_FLESH, TaskType.CUSTOM);
        BEDROCK = new Task(plugin, "Reach Bedrock", Material.BEDROCK, TaskType.CUSTOM);
        SKY_LIMIT = new Task(plugin, "Reach Sky Limit", Material.LIGHT_BLUE_STAINED_GLASS, TaskType.CUSTOM);
        LEVEL_15 = new Task(plugin, "Reach Level 15", Material.EXPERIENCE_BOTTLE, TaskType.CUSTOM);
        CRIMSON_SIGN_GLOW = new Task(plugin, "Use glow ink on a crimson sign", Material.CRIMSON_SIGN, TaskType.CUSTOM);

        allTasks.add(COMPOSTER);
        allTasks.add(OPPONENT_DIE);
        allTasks.add(OPPONENT_FALL);
        allTasks.add(OPPONENT_FIRE);
        allTasks.add(OPPONENT_CRAFTING_TABLE);
        allTasks.add(OPPONENT_OBSIDIAN);
        allTasks.add(OPPONENT_SEEDS);
        allTasks.add(OPPONENT_JUMP);
        allTasks.add(OPPONENT_SNOWBALL);
        allTasks.add(SLEEP);
        allTasks.add(MUSIC_DISC);
        allTasks.add(SPAWN_CHICKEN);
        allTasks.add(EMPTY_HUNGER);
        allTasks.add(BEDROCK);
        allTasks.add(SKY_LIMIT);
        allTasks.add(LEVEL_15);
        allTasks.add(CRIMSON_SIGN_GLOW);
    }
}
