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
