package me.jsthats.worldinteractions.events.items;

import org.jetbrains.annotations.NotNull;
import org.bukkit.event.inventory.CraftItemEvent;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;

public class PlayerCraftItemEvent extends CustomEvent {
    protected final CraftItemEvent sourceEvent;

    public PlayerCraftItemEvent(@NotNull CraftItemEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.CRAFT.getPermission(),
            sourceEvent.getCurrentItem().getType().name()
        );
    }
}
