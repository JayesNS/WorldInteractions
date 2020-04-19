package me.jsthats.worldinteractions.events.entity;

import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTameEvent;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;

public class PlayerTameEntityEvent extends CustomEvent {
    protected final EntityTameEvent sourceEvent;

    public PlayerTameEntityEvent(@NotNull EntityTameEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @NotNull
    public Player getPlayer() {
        return (Player) sourceEvent.getOwner();
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.TAME.getPermission(),
            sourceEvent.getEntity().getType().name()
        );
    }
}
