package me.jsthats.worldinteractions.events.player;

import org.jetbrains.annotations.NotNull;
import org.bukkit.event.player.PlayerFishEvent;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;

public class PlayerFishEntityEvent extends CustomEvent {
    protected final PlayerFishEvent sourceEvent;

    public PlayerFishEntityEvent(@NotNull PlayerFishEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.FISH.getPermission(),
            sourceEvent.getCaught().getType().name()
        );
    }
}
