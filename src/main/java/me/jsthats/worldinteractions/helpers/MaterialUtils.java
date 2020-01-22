/*
 * WorldInteractions - Spigot permission ruleset plugin
 * Copyright (C) 2019 t3hk0d3 Jayes
 *
 * This program is free software: you can redistribute it and/or modify
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
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.jsthats.worldinteractions.helpers;

import org.bukkit.Material;

/**
 * @author Jayes
 */
public abstract class MaterialUtils {
    public static boolean isBoat(Material material) {
        switch (material) {
            case ACACIA_BOAT:
            case BIRCH_BOAT:
            case DARK_OAK_BOAT:
            case OAK_BOAT:
            case JUNGLE_BOAT:
            case SPRUCE_BOAT:
                return true;
        }
        return false;
    }

    public static boolean isMinecart(Material material) {
        switch (material) {
            case MINECART:
            case TNT_MINECART:
            case CHEST_MINECART:
            case HOPPER_MINECART:
            case FURNACE_MINECART:
            case COMMAND_BLOCK_MINECART:
                return true;
        }
        return false;
    }

    public static boolean isVehicle(Material material) {
        return isBoat(material) || isMinecart(material);
    }

    public static boolean isSpawnEgg(Material material) {
        switch (material) {
            case BAT_SPAWN_EGG:
            case BLAZE_SPAWN_EGG:
            case CAVE_SPIDER_SPAWN_EGG:
            case CHICKEN_SPAWN_EGG:
            case COD_SPAWN_EGG:
            case COW_SPAWN_EGG:
            case CREEPER_SPAWN_EGG:
            case DOLPHIN_SPAWN_EGG:
            case DONKEY_SPAWN_EGG:
            case DROWNED_SPAWN_EGG:
            case ELDER_GUARDIAN_SPAWN_EGG:
            case ENDERMAN_SPAWN_EGG:
            case ENDERMITE_SPAWN_EGG:
            case EVOKER_SPAWN_EGG:
            case GHAST_SPAWN_EGG:
            case GUARDIAN_SPAWN_EGG:
            case HORSE_SPAWN_EGG:
            case HUSK_SPAWN_EGG:
            case LLAMA_SPAWN_EGG:
            case MAGMA_CUBE_SPAWN_EGG:
            case MOOSHROOM_SPAWN_EGG:
            case MULE_SPAWN_EGG:
            case OCELOT_SPAWN_EGG:
            case PARROT_SPAWN_EGG:
            case PHANTOM_SPAWN_EGG:
            case PIG_SPAWN_EGG:
            case POLAR_BEAR_SPAWN_EGG:
            case PUFFERFISH_SPAWN_EGG:
            case RABBIT_SPAWN_EGG:
            case SALMON_SPAWN_EGG:
            case SHEEP_SPAWN_EGG:
            case SHULKER_SPAWN_EGG:
            case SILVERFISH_SPAWN_EGG:
            case SKELETON_SPAWN_EGG:
            case SKELETON_HORSE_SPAWN_EGG:
            case SLIME_SPAWN_EGG:
            case SPIDER_SPAWN_EGG:
            case SQUID_SPAWN_EGG:
            case STRAY_SPAWN_EGG:
            case TROPICAL_FISH_SPAWN_EGG:
            case TURTLE_SPAWN_EGG:
            case VEX_SPAWN_EGG:
            case VILLAGER_SPAWN_EGG:
            case VINDICATOR_SPAWN_EGG:
            case WITCH_SPAWN_EGG:
            case WITHER_SKELETON_SPAWN_EGG:
            case WOLF_SPAWN_EGG:
            case ZOMBIE_SPAWN_EGG:
            case ZOMBIE_HORSE_SPAWN_EGG:
            case ZOMBIE_PIGMAN_SPAWN_EGG:
            case ZOMBIE_VILLAGER_SPAWN_EGG:
            case CAT_SPAWN_EGG:
            case FOX_SPAWN_EGG:
            case PANDA_SPAWN_EGG:
            case PILLAGER_SPAWN_EGG:
            case RAVAGER_SPAWN_EGG:
            case TRADER_LLAMA_SPAWN_EGG:
            case WANDERING_TRADER_SPAWN_EGG:
                return true;
        }
        return false;
    }
}
