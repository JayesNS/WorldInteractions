package me.jsthats.worldinteractions.enums;

import org.bukkit.entity.Player;

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
    // Blocks
    BUCKET_EMPTY("bucket.empty"),
    BUCKET_FILL("bucket.fill"),
    BLOCK_BREAK("blocks.break"),
    BLOCK_PLACE("blocks.place"),
    HANGING_BREAK("hanging.break"),
    HANGING_PLACE("hanging.place"),
    // Mount
    MOUNT_BREAK("mount.break"),
    MOUNT_ENTER("mount.enter"),
    MOUNT_COLLIDE("mount.collide");

    private String permission;

    public String getPermission() {
        return permission;
    }

    Permissions(String permission) {
        this.permission = permission;
    }
    }
