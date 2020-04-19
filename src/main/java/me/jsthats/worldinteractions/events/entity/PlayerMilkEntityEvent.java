package me.jsthats.worldinteractions.events.entity;

import org.jetbrains.annotations.NotNull;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;

public class PlayerMilkEntityEvent extends CustomEvent {
    protected final PlayerInteractEntityEvent sourceEvent;

    public PlayerMilkEntityEvent(@NotNull PlayerInteractEntityEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.MILK.getPermission(),
            sourceEvent.getRightClicked().getType().name()
        );
    }
}
