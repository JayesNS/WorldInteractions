package me.jsthats.worldinteractions.events.blocks;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerPlaceHangingEvent extends CustomEvent {
    protected final HangingPlaceEvent sourceEvent;

    public PlayerPlaceHangingEvent(@NotNull HangingPlaceEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.BLOCK_PLACE.getPermission(),
            sourceEvent.getEntity().getType().name()
        );
    }
}
