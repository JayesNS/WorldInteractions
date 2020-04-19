package me.jsthats.worldinteractions.events.blocks;

import org.jetbrains.annotations.NotNull;
import org.bukkit.event.block.BlockPlaceEvent;

import me.jsthats.worldinteractions.enums.Permissions;

public class PlayerPlaceBlockUsingItemEvent extends PlayerPlaceBlockEvent {
    public PlayerPlaceBlockUsingItemEvent(@NotNull BlockPlaceEvent sourceEvent) {
        super(sourceEvent);
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.BLOCK_PLACE_USING.getPermission(),
            getPermissionParameters()
        );
    }

    @Override
    public String[] getPermissionParameters() {
        return new String[] {
            sourceEvent.getBlock().getType().name(),
            sourceEvent.getPlayer().getItemOnCursor().getType().name()
        };
    }
}
