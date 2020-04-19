package me.jsthats.worldinteractions.events.blocks;

import org.jetbrains.annotations.NotNull;
import org.bukkit.event.hanging.HangingPlaceEvent;

import me.jsthats.worldinteractions.enums.Permissions;

public class PlayerPlaceHangingAgainstBlockEvent extends PlayerPlaceHangingEvent {
    public PlayerPlaceHangingAgainstBlockEvent(@NotNull HangingPlaceEvent sourceEvent) {
        super(sourceEvent);
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.BLOCK_PLACE_AGAINST.getPermission(),
            sourceEvent.getEntity().getType().name(),
            sourceEvent.getBlock().getType().name()
        );
    }
}
