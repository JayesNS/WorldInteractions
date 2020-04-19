package me.jsthats.worldinteractions.events.blocks;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerBreakBlockEvent extends CustomEvent {
    protected final BlockBreakEvent sourceEvent;

    public PlayerBreakBlockEvent(@NotNull BlockBreakEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
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
            sourceEvent.getBlock().getType().name()
        };
    }
}
