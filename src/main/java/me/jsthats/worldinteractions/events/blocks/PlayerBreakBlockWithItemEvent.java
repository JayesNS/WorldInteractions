package me.jsthats.worldinteractions.events.blocks;

import me.jsthats.worldinteractions.enums.Permissions;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerBreakBlockWithItemEvent extends PlayerBreakBlockEvent {
    public PlayerBreakBlockWithItemEvent(@NotNull BlockBreakEvent sourceEvent) {
        super(sourceEvent);
    }

    public ItemStack getItem() {
        return sourceEvent.getPlayer().getInventory().getItemInMainHand();
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
            sourceEvent.getBlock().getType().name(),
            getItem().getType().name()
        };
    }
}
