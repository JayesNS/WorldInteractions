package me.jsthats.worldinteractions.events.entity;

import org.jetbrains.annotations.NotNull;
import org.bukkit.event.entity.EntityBreedEvent;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;

public class PlayerBreedEntitiesEvent extends CustomEvent {
    protected final EntityBreedEvent sourceEvent;

    public PlayerBreedEntitiesEvent(@NotNull EntityBreedEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @Override
    public String getPermission() {
        return Permissions.BREED.getPermission(
            sourceEvent.getEntity().getType().name()
        );
    }
}
