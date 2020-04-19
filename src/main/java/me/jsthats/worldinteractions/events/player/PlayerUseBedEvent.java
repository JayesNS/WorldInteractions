package me.jsthats.worldinteractions.events.player;

import org.jetbrains.annotations.NotNull;
import org.bukkit.event.player.PlayerBedEnterEvent;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;

public class PlayerUseBedEvent extends CustomEvent {
    protected final PlayerBedEnterEvent sourceEvent;

    public PlayerUseBedEvent(@NotNull PlayerBedEnterEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.USE_BEDS.getPermission()
        );
    }
}
