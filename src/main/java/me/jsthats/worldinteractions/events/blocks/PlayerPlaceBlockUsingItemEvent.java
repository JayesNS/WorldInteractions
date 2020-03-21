package me.jsthats.worldinteractions.events.blocks;

import me.jsthats.worldinteractions.enums.Permissions;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerPlaceBlockUsingItemEvent extends PlayerPlaceBlockEvent {
    protected final ItemStack item;

    public PlayerPlaceBlockUsingItemEvent(@NotNull Player player, @NotNull Block block, @NotNull ItemStack item) {
        super(player, block);

        this.item = item;
    }

    @NotNull
    public ItemStack getItem() {
        return item;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.BLOCK_PLACE_USING.getPermission(),
            this.block.getType().name(),
            this.item.getType().name()
        );
    }
}
