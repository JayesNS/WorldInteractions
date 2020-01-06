package me.jsthats.worldinteractions.enums;

/**
 * @author g4rnek
 */
public enum Permissions {
    INTERACT("interact"),
    USE("use"),
    ENCHANT("enchant"),
    THROW("throw"),
    EAT("eat"),
    CRAFT("craft"),
    HOLD("hold"),
    DROP("drop"),
    CHAT("chat"),
    USE_BEDS("use-beds"),
    BUCKET_EMPTY("bucket.empty"),
    BUCKET_FILL("bucket.fill"),
    BLOCK_BREAK("blocks.break"),
    BLOCK_PLACE("blocks.place"),
    HANGING_BREAK("hanging.break"),
    HANGING_PLACE("hanging.place"),
    DAMAGE_DEAL("damage.deal"),
    DAMAGE_TAKE("damage.take"),
    TAME("tame"),
    SHOOT("shoot"),
    PICKUP("pickup"),
    TRANSPORT_BREAK("transport.break"),
    TRANSPORT_ENTER("transport.enter"),
    TRANSPORT_COLLIDE("transport.collide"),
    TARGET_BY("target-by"),
    HAVE("have"),
    SMELT("smelt"),
    BREED("breed");

    private String permission;

    public String getPermission() {
        return permission;
    }

    Permissions(String permission) {
        this.permission = permission;
    }
}
