package me.jsthats.worldinteractions.events.player;

import org.jetbrains.annotations.NotNull;
import org.bukkit.event.entity.EntityShootBowEvent;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;

public class PlayerShootBowEvent extends CustomEvent {
    protected final EntityShootBowEvent sourceEvent;

    public PlayerShootBowEvent(@NotNull EntityShootBowEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.SHOOT.getPermission()
        );
    }
}
