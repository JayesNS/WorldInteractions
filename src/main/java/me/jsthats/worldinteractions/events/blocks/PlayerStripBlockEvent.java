package me.jsthats.worldinteractions.events.blocks;

import org.jetbrains.annotations.NotNull;
import org.bukkit.event.block.BlockPlaceEvent;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;

public class PlayerStripBlockEvent extends CustomEvent {
    protected final BlockPlaceEvent sourceEvent;

    public PlayerStripBlockEvent(@NotNull BlockPlaceEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.BLOCK_STRIP.getPermission(),
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
