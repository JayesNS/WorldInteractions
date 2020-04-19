package me.jsthats.worldinteractions.events.blocks;

import org.jetbrains.annotations.NotNull;
import org.bukkit.event.block.BlockPlaceEvent;

import me.jsthats.worldinteractions.enums.Permissions;

public class PlayerPlaceBlockAgainstBlockEvent extends PlayerPlaceBlockEvent {
    public PlayerPlaceBlockAgainstBlockEvent(@NotNull BlockPlaceEvent sourceEvent) {
        super(sourceEvent);
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.BLOCK_PLACE_AGAINST.getPermission(),
            getPermissionParameters()
        );
    }

    @Override
    public String[] getPermissionParameters() {
        return new String[] {
            sourceEvent.getBlock().getType().name(),
            sourceEvent.getBlockAgainst().getType().name()
        };
    }
}
