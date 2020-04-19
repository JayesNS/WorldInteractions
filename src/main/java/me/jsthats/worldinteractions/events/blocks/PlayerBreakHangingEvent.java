package me.jsthats.worldinteractions.events.blocks;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerBreakHangingEvent extends CustomEvent {
    protected final HangingBreakByEntityEvent sourceEvent;

    public PlayerBreakHangingEvent(@NotNull HangingBreakByEntityEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    public Player getPlayer() {
        return (Player) sourceEvent.getRemover();
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.BLOCK_BREAK.getPermission(),
            getPermissionParameters()
        );
    }

    @Override
    public String[] getPermissionParameters() {
        return new String[] {
            sourceEvent.getEntity().getType().name()
        };
    }
}
