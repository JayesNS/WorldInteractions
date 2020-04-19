package me.jsthats.worldinteractions.events.entity;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerEquipEntityWithItemEvent extends CustomEvent {
    protected final PlayerInteractEntityEvent sourceEvent;

    public PlayerEquipEntityWithItemEvent(@NotNull PlayerInteractEntityEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.EQUIP_ENTITY_WITH_ITEM.getPermission(),
            sourceEvent.getRightClicked().getName(),
            sourceEvent.getPlayer().getItemOnCursor()
        );
    }
}
