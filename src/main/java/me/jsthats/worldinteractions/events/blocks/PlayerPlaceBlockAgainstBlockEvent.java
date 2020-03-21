package me.jsthats.worldinteractions.events.blocks;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerPlaceBlockAgainstBlockEvent extends PlayerPlaceBlockEvent {
    protected final Block blockAgainst;

    public PlayerPlaceBlockAgainstBlockEvent(@NotNull Player player, @NotNull Block block, @NotNull Block blockAgainst) {
        super(player, block);
        this.blockAgainst = blockAgainst;
    }

    @NotNull
    public Block getBlockAgainst() {
        return blockAgainst;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.BLOCK_PLACE_AGAINST.getPermission(),
            this.block.getType().name(),
            this.blockAgainst.getType().name()
        );
    }
}
