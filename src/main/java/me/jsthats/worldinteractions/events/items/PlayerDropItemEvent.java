package me.jsthats.worldinteractions.events.items;

import org.jetbrains.annotations.NotNull;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;

public class PlayerDropItemEvent extends CustomEvent {
    protected final org.bukkit.event.player.PlayerDropItemEvent sourceEvent;

    public PlayerDropItemEvent(@NotNull org.bukkit.event.player.PlayerDropItemEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.DROP.getPermission(),
            sourceEvent.getItemDrop().getItemStack().getType().name()
        );
    }
}
