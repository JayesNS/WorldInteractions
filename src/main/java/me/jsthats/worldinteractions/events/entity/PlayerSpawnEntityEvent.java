package me.jsthats.worldinteractions.events.entity;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerSpawnEntityEvent extends CustomEvent {
    protected final PlayerInteractEvent sourceEvent;

    public PlayerSpawnEntityEvent(@NotNull PlayerInteractEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.SPAWN.getPermission(),
            sourceEvent.getPlayer().getItemOnCursor().getType().name().replace("_SPAWN_EGG", "")
        );
    }
}
