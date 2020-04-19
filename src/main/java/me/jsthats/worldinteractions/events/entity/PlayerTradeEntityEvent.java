package me.jsthats.worldinteractions.events.entity;

import org.jetbrains.annotations.NotNull;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;

public class PlayerTradeEntityEvent extends CustomEvent {
    protected final PlayerInteractEntityEvent sourceEvent;

    public PlayerTradeEntityEvent(@NotNull PlayerInteractEntityEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @NotNull
    public PlayerInteractEntityEvent getSourceEvent() {
        return this.sourceEvent;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.TRADE.getPermission(),
            sourceEvent.getRightClicked().getName()
        );
    }
}
