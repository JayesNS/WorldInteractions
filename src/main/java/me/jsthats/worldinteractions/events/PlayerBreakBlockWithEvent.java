package me.jsthats.worldinteractions.events;

import me.jsthats.worldinteractions.enums.Permissions;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerBreakBlockWithEvent extends CustomEvent {
    private Player player;
    private Block block;
    private ItemStack itemInHand;

    public PlayerBreakBlockWithEvent(Player player, Block block, ItemStack itemInHand) {
        this.player = player;
        this.block = block;
        this.itemInHand = itemInHand;
    }

    public Player getPlayer() {
        return player;
    }

    public Block getBlock() {
        return block;
    }

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
