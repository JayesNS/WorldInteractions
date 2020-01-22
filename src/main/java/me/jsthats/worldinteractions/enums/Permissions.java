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
    // Entities,
    INTERACT("interact"),
    TARGET_BY("target-by"),
    TAME("tame"),
    BREED("breed"),
    DAMAGE_DEAL("damage.deal"),
    SPAWN("spawn"),
    RIDE("ride"),
    // Items,
    USE("use"),
    ENCHANT("enchant"),
    THROW("throw"),
    SHOOT("shoot"),
    EAT("eat"),
    SMELT("smelt"),
    CRAFT("craft"),
    DROP("drop"),
    PICKUP("pickup"),
    HOLD("hold"),
    // Blocks,
    BUCKET_EMPTY("bucket.empty"),
    BUCKET_FILL("bucket.fill"),
    BLOCK_BREAK("blocks.break"),
    BLOCK_PLACE("blocks.place"),
    HANGING_BREAK("hanging.break"),
    HANGING_PLACE("hanging.place"),
    // Mount,
    VEHICLE_BREAK("vehicle.break"),
    VEHICLE_ENTER("vehicle.enter"),
    VEHICLE_COLLIDE("vehicle.collide"),
    VEHICLE_PLACE("vehicle.place");

    private String permission;

    public String getPermission() {
        return permission;
    }

    Permissions(String permission) {
        this.permission = permission;
    }
    }
