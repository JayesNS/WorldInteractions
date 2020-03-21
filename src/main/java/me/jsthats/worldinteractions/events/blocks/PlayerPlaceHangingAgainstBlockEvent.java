package me.jsthats.worldinteractions.events.blocks;

import me.jsthats.worldinteractions.enums.Permissions;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerPlaceHangingAgainstBlockEvent extends PlayerPlaceHangingEvent {
    protected final Block block;

    public PlayerPlaceHangingAgainstBlockEvent(@NotNull Player player, @NotNull Entity hanging, @NotNull Block block) {
        super(player, hanging);
        this.block = block;
    }

    @NotNull
    public Block getBlock() {
        return block;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.BLOCK_PLACE_AGAINST.getPermission(),
            this.hanging.getType().name(),
            this.block.getType().name()
        );
    }
}
