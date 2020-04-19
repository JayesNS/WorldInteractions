package me.jsthats.worldinteractions.events.entity;

import org.jetbrains.annotations.NotNull;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;

public class PlayerDyeEntityEvent extends CustomEvent {
    protected final PlayerInteractEntityEvent sourceEvent;

    public PlayerDyeEntityEvent(@NotNull PlayerInteractEntityEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @NotNull
    public PlayerInteractEntityEvent getSourceEvent() {
        return this.sourceEvent;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.DYE.getPermission(),
            sourceEvent.getRightClicked().getName(),
            sourceEvent.getPlayer().getInventory().getItemInMainHand().getType().name()
        );
    }
}
