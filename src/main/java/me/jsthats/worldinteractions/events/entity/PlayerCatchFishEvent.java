package me.jsthats.worldinteractions.events.entity;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerCatchFishEvent extends CustomEvent {
    protected final PlayerInteractEntityEvent sourceEvent;

    public PlayerCatchFishEvent(@NotNull PlayerInteractEntityEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @NotNull
    public PlayerInteractEntityEvent getSourceEvent() {
        return this.sourceEvent;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.CATCH_FISH.getPermission(),
            sourceEvent.getRightClicked().getName()
        );
    }
}
