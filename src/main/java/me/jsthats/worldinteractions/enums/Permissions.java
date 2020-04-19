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
    // General
    CHAT("chat"),
    USE_BEDS("use-beds"),
    FISH("fish.%s"),
    // Weapons
    SHOOT("shoot"),
    THROW("throw"),
    // Entities
    INTERACT("interact"),
    TARGET_BY("target-by"),
    DAMAGE_WITH("damage.%s.with.%s"),
    TAME("tame.%s"),
    BREED("breed.%s"),
    MILK("milk.%s"),
    SHEAR("shear.%s"),
    SPAWN("mob.spawn"),
    RIDE("mob.ride"),
    // Items
    USE("item.use"),
    ENCHANT_ITEM("item.enchant.%s"),
    ENCHANT_ITEM_WITH("item.enchant.%s.with.%s.%s"),
    EAT("item.eat"),
    SMELT("item.smelt"),
    CRAFT("item.craft.%s"),
    DROP("item.drop.%s"),
    PICKUP("item.pickup.%s"),
    HOLD("item.hold.%s"),
    DISCOVER_RECIPE("item.discover.%s"),
    // Blocks
    BUCKET_EMPTY("bucket.empty.%s"),
    BUCKET_FILL_WITH("bucket.fill.with.%s"),
    BLOCK_PLACE("block.place.%s"),
    BLOCK_PLACE_AGAINST("block.place.%s.against.%s"),
    BLOCK_PLACE_USING("block.place.%s.using.%s"),
    BLOCK_BREAK("block.break.%s"),
    BLOCK_BREAK_WITH("block.break.%s.with.%s"),
    BLOCK_STRIP("block.strip.%s.with.%s"),
    // Mount
    VEHICLE_ENTER("vehicle.enter.%s"),
    VEHICLE_PLACE("vehicle.place.%s"),
    VEHICLE_BREAK("vehicle.break.%s"),
    VEHICLE_BREAK_WITH("vehicle.break.%s.with.%s"),
    VEHICLE_PUSH("vehicle.push.%s"),
    DYE("dye.%s.%s"),
    TRADE("trade.%s"),
    MOUNT("mount.%s"),
    EQUIP_ENTITY_WITH_ITEM("equip.%s.with.%s"),
    SIT_COMMAND("sit.%s"),
    CATCH_FISH("catch.%s");
    private String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return "worldinteractions." + permission;
    }

    public String getPermission(String ...args) {
        return String.format(getPermission(), args);
    }

}
