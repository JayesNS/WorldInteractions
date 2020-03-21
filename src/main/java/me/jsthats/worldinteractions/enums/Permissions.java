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

package me.jsthats.worldinteractions.enums;

/**
 * @author Jayes
 */
public enum Permissions {
    // General,
    CHAT("chat"),
    USE_BEDS("use-beds"),
    // Weapons
    SHOOT("shoot"),
    THROW("throw"),
    // Entities,
    INTERACT("interact"),
    TARGET_BY("target-by"),
    DAMAGE_DEAL("damage.deal"),
    TAME("mob.tame"),
    BREED("mob.breed"),
    SPAWN("mob.spawn"),
    RIDE("mob.ride"),
    // Items
    USE("item.use"),
    ENCHANT_ITEM("item.enchant.%s"),
    ENCHANT_ITEM_WITH("item.enchant.%s.with.%s.%s"),
    EAT("item.eat"),
    SMELT("item.smelt"),
    CRAFT("item.craft"),
    DROP("item.drop"),
    PICKUP("item.pickup"),
    HOLD("item.hold"),
    // Blocks
    BUCKET_EMPTY("bucket.empty"),
    BUCKET_FILL("bucket.fill"),
    BLOCK_PLACE("block.place.%s"),
    BLOCK_PLACE_AGAINST("block.place.%s.against.%s"),
    BLOCK_PLACE_USING("block.place.%s.using.%s"),
    BLOCK_BREAK("block.break.%s"),
    BLOCK_BREAK_WITH("block.break.%s.with.%s"),
    // Mount
    VEHICLE_ENTER("vehicle.enter"),
    VEHICLE_PLACE("vehicle.place"),
    VEHICLE_BREAK("vehicle.break.%s"),
    VEHICLE_BREAK_WITH("vehicle.break.%s.with.%s"),
    VEHICLE_COLLIDE("vehicle.collide");

    private String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return "worldinteractions." + permission;
    }

}
