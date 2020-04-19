package me.jsthats.worldinteractions.events.blocks;

import me.jsthats.worldinteractions.enums.Permissions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerBreakHangingWithItemEvent extends PlayerBreakHangingEvent {
    public PlayerBreakHangingWithItemEvent(@NotNull HangingBreakByEntityEvent sourceEvent) {
        super(sourceEvent);
    }

    public ItemStack getItem() {
        return getPlayer().getItemOnCursor();
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.BLOCK_BREAK_WITH.getPermission(),
            getPermissionParameters()
        );
    }

    @Override
    public String[] getPermissionParameters() {
        return new String[] {
            sourceEvent.getEntity().getType().name(),
            getItem().getType().name()
        };
    }
}
