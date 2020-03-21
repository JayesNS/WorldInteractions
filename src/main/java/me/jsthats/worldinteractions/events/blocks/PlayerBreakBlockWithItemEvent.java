package me.jsthats.worldinteractions.events.blocks;

import me.jsthats.worldinteractions.enums.Permissions;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerBreakBlockWithItemEvent extends PlayerBreakBlockEvent {
    protected final ItemStack itemInHand;

    public PlayerBreakBlockWithItemEvent(@NotNull Player player, @NotNull Block block, @NotNull ItemStack itemInHand) {
        super(player, block);
        this.itemInHand = itemInHand;
    }

    @NotNull
    public ItemStack getItemInHand() {
        return itemInHand;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.BLOCK_BREAK_WITH.getPermission(),
            this.block.getType().name(),
            this.itemInHand.getType().name()
        );
    }
}
