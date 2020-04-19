/*
 * WorldInteractions - Spigot permission ruleset plugin
 * Copyright (C) 2019 t3hk0d3 Jayes
 *
 * This program is free software, you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https,//www.gnu.org/licenses/>.
 */

package me.jsthats.worldinteractions.helpers;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jayes
 */
public abstract class ObjectUtils {
    public static boolean isBoat(Material material) {
        final List<Material> BOATS = Arrays.asList(
            Material.ACACIA_BOAT,
            Material.BIRCH_BOAT,
            Material.DARK_OAK_BOAT,
            Material.OAK_BOAT,
            Material.JUNGLE_BOAT,
            Material.SPRUCE_BOAT
        );
        return BOATS.contains(material);
    }

    public static boolean isHead(Material material) {
        final List<Material> HEADS = Arrays.asList(
            Material.CARVED_PUMPKIN,
            Material.PLAYER_HEAD,
            Material.CREEPER_HEAD,
            Material.DRAGON_HEAD,
            Material.ZOMBIE_HEAD
        );
        return HEADS.contains(material);
    }

    public static boolean isArmor(Material material) {
        final List<Material> ARMOR = Arrays.asList(
            Material.LEATHER_HELMET,
            Material.LEATHER_CHESTPLATE,
            Material.LEATHER_LEGGINGS,
            Material.LEATHER_BOOTS,
            Material.IRON_HELMET,
            Material.IRON_CHESTPLATE,
            Material.IRON_LEGGINGS,
            Material.IRON_BOOTS,
            Material.CHAINMAIL_HELMET,
            Material.CHAINMAIL_CHESTPLATE,
            Material.CHAINMAIL_LEGGINGS,
            Material.CHAINMAIL_BOOTS,
            Material.GOLDEN_HELMET,
            Material.GOLDEN_CHESTPLATE,
            Material.GOLDEN_LEGGINGS,
            Material.GOLDEN_BOOTS,
            Material.DIAMOND_HELMET,
            Material.DIAMOND_CHESTPLATE,
            Material.DIAMOND_LEGGINGS,
            Material.DIAMOND_BOOTS,
            Material.ELYTRA
        );
        return ARMOR.contains(material);
    }

    public static boolean isMinecart(Material material) {
        final List<Material> MINECARTS = Arrays.asList(
            Material.MINECART,
            Material.TNT_MINECART,
            Material.CHEST_MINECART,
            Material.HOPPER_MINECART,
            Material.FURNACE_MINECART,
            Material.COMMAND_BLOCK_MINECART
        );
        return MINECARTS.contains(material);
    }

    public static Material getMaterialFromNamespacedKey(NamespacedKey namespacedKey) {
        return Material.valueOf(namespacedKey.getKey().replace("_from.*", "").toUpperCase());
    }

    public static EntityType getEntityTypeOfMaterial(Material material) {
        Map<Material, EntityType> materialToEntityMap = new HashMap<Material, EntityType>() {{
            put(Material.ACACIA_BOAT, EntityType.BOAT);
            put(Material.BIRCH_BOAT, EntityType.BOAT);
            put(Material.DARK_OAK_BOAT, EntityType.BOAT);
            put(Material.JUNGLE_BOAT, EntityType.BOAT);
            put(Material.OAK_BOAT, EntityType.BOAT);
            put(Material.SPRUCE_BOAT, EntityType.BOAT);
            put(Material.MINECART, EntityType.MINECART);
            put(Material.CHEST_MINECART, EntityType.MINECART_CHEST);
            put(Material.COMMAND_BLOCK_MINECART, EntityType.MINECART_COMMAND);
            put(Material.FURNACE_MINECART, EntityType.MINECART_FURNACE);
            put(Material.HOPPER_MINECART, EntityType.MINECART_HOPPER);
            put(Material.TNT_MINECART, EntityType.MINECART_TNT);
        }};
        return materialToEntityMap.get(material);
    }

    public static boolean isVehicle(Material material) {
        return isBoat(material) || isMinecart(material);
    }

    public static boolean isVehicle(Entity entity) {
        return (entity instanceof Minecart || entity instanceof Boat);
    }

    public static boolean isSpawnEgg(Material material) {
        final List<Material> list = Arrays.asList(
            Material.BAT_SPAWN_EGG,
            Material.BLAZE_SPAWN_EGG,
            Material.CAVE_SPIDER_SPAWN_EGG,
            Material.CHICKEN_SPAWN_EGG,
            Material.COD_SPAWN_EGG,
            Material.COW_SPAWN_EGG,
            Material.CREEPER_SPAWN_EGG,
            Material.DOLPHIN_SPAWN_EGG,
            Material.DONKEY_SPAWN_EGG,
            Material.DROWNED_SPAWN_EGG,
            Material.ELDER_GUARDIAN_SPAWN_EGG,
            Material.ENDERMAN_SPAWN_EGG,
            Material.ENDERMITE_SPAWN_EGG,
            Material.EVOKER_SPAWN_EGG,
            Material.GHAST_SPAWN_EGG,
            Material.GUARDIAN_SPAWN_EGG,
            Material.HORSE_SPAWN_EGG,
            Material.HUSK_SPAWN_EGG,
            Material.LLAMA_SPAWN_EGG,
            Material.MAGMA_CUBE_SPAWN_EGG,
            Material.MOOSHROOM_SPAWN_EGG,
            Material.MULE_SPAWN_EGG,
            Material.OCELOT_SPAWN_EGG,
            Material.PARROT_SPAWN_EGG,
            Material.PHANTOM_SPAWN_EGG,
            Material.PIG_SPAWN_EGG,
            Material.POLAR_BEAR_SPAWN_EGG,
            Material.PUFFERFISH_SPAWN_EGG,
            Material.RABBIT_SPAWN_EGG,
            Material.SALMON_SPAWN_EGG,
            Material.SHEEP_SPAWN_EGG,
            Material.SHULKER_SPAWN_EGG,
            Material.SILVERFISH_SPAWN_EGG,
            Material.SKELETON_SPAWN_EGG,
            Material.SKELETON_HORSE_SPAWN_EGG,
            Material.SLIME_SPAWN_EGG,
            Material.SPIDER_SPAWN_EGG,
            Material.SQUID_SPAWN_EGG,
            Material.STRAY_SPAWN_EGG,
            Material.TROPICAL_FISH_SPAWN_EGG,
            Material.TURTLE_SPAWN_EGG,
            Material.VEX_SPAWN_EGG,
            Material.VILLAGER_SPAWN_EGG,
            Material.VINDICATOR_SPAWN_EGG,
            Material.WITCH_SPAWN_EGG,
            Material.WITHER_SKELETON_SPAWN_EGG,
            Material.WOLF_SPAWN_EGG,
            Material.ZOMBIE_SPAWN_EGG,
            Material.ZOMBIE_HORSE_SPAWN_EGG,
            Material.ZOMBIE_PIGMAN_SPAWN_EGG,
            Material.ZOMBIE_VILLAGER_SPAWN_EGG,
            Material.CAT_SPAWN_EGG,
            Material.FOX_SPAWN_EGG,
            Material.PANDA_SPAWN_EGG,
            Material.PILLAGER_SPAWN_EGG,
            Material.RAVAGER_SPAWN_EGG,
            Material.TRADER_LLAMA_SPAWN_EGG,
            Material.WANDERING_TRADER_SPAWN_EGG
        );
        return list.contains(material);
    }

    public static boolean canBeCured(Entity entity) {
        final List<EntityType> list = Arrays.asList(
            EntityType.ZOMBIE_VILLAGER
        );

        return list.contains(entity.getType());
    }

    public static boolean canBeTamed(Entity entity) {
        final List<EntityType> list = Arrays.asList(
            EntityType.CAT,
            EntityType.DONKEY,
            EntityType.HORSE,
            EntityType.MULE,
            EntityType.OCELOT,
            EntityType.PARROT,
            EntityType.LLAMA,
            EntityType.WOLF
        );

        return list.contains(entity.getType());
    }

    public static boolean canBeMounted(Entity entity) {
        final List<EntityType> list = Arrays.asList(
            EntityType.DONKEY,
            EntityType.HORSE,
            EntityType.MULE,
            EntityType.PIG,
            EntityType.SKELETON_HORSE,
            EntityType.LLAMA,
            EntityType.TRADER_LLAMA
        );

        return list.contains(entity.getType());
    }

    public static boolean isDye(Material material) {
        final List<Material> list = Arrays.asList(
            Material.BLACK_DYE,
                Material.BLUE_DYE,
                Material.BROWN_DYE,
                Material.CYAN_DYE,
                Material.GRAY_DYE,
                Material.GREEN_DYE,
                Material.LIGHT_BLUE_DYE,
                Material.LIGHT_GRAY_DYE,
                Material.LIME_DYE,
                Material.MAGENTA_DYE,
                Material.ORANGE_DYE,
                Material.PINK_DYE,
                Material.PURPLE_DYE,
                Material.RED_DYE,
                Material.WHITE_DYE,
                Material.YELLOW_DYE
        );

        return list.contains(material);
    }

    public static boolean canBeDyed(Entity entity) {
        final List<EntityType> list = Arrays.asList(
            EntityType.CAT,
            EntityType.SHEEP,
            EntityType.WOLF
        );

        return list.contains(entity.getType());
    }

    public static boolean canTradeWith(Entity entity) {
        final List<EntityType> list = Arrays.asList(
            EntityType.VILLAGER,
            EntityType.WANDERING_TRADER
        );

        return list.contains(entity.getType());
    }

    public static boolean canBeMilked(Entity entity) {
        final List<EntityType> list = Arrays.asList(
            EntityType.COW,
            EntityType.MUSHROOM_COW
        );

        return list.contains(entity.getType());
    }

    public static boolean canBeIgnited(Entity entity) {
        final List<EntityType> list = Arrays.asList(
            EntityType.CREEPER
        );

        return list.contains(entity.getType());
    }
}
