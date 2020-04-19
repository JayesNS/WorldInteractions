package me.jsthats.worldinteractions.events.entity;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerSitCommandEvent extends CustomEvent {
    protected final PlayerInteractEntityEvent sourceEvent;

    public PlayerSitCommandEvent(@NotNull PlayerInteractEntityEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @NotNull
    public PlayerInteractEntityEvent getSourceEvent() {
        return this.sourceEvent;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.SIT_COMMAND.getPermission(),
            sourceEvent.getRightClicked().getName()
        );
    }
}
