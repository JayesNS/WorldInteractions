package me.jsthats.worldinteractions.events.items;

import org.jetbrains.annotations.NotNull;
import org.bukkit.event.entity.EntityPickupItemEvent;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;

public class PlayerPickupItemEvent extends CustomEvent {
    protected final EntityPickupItemEvent sourceEvent;

    public PlayerPickupItemEvent(@NotNull EntityPickupItemEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.PICKUP.getPermission(),
            sourceEvent.getItem().getType().name()
        );
    }
}
