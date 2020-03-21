package me.jsthats.worldinteractions.events.blocks;

import me.jsthats.worldinteractions.enums.Permissions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerBreakHangingWithItemEvent extends PlayerBreakHangingEvent {
    protected final ItemStack item;

    public PlayerBreakHangingWithItemEvent(@NotNull Player player, @NotNull Entity hanging, @NotNull ItemStack item) {
        super(player, hanging);
        this.item = item;
    }

    @NotNull
    public ItemStack getItem() {
        return item;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.BLOCK_BREAK_WITH.getPermission(),
            this.hanging.getType().name(),
            this.item.getType().name()
        );
    }
}
