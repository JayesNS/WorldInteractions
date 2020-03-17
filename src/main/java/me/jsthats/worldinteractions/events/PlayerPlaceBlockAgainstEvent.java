package me.jsthats.worldinteractions.events;

import me.jsthats.worldinteractions.enums.Permissions;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class PlayerPlaceBlockAgainstEvent extends CustomEvent {
    private Player player;
    private Block block;
    private Block blockAgainst;

    public PlayerPlaceBlockAgainstEvent(Player player, Block block, Block blockAgainst) {
        this.player = player;
        this.block = block;
        this.blockAgainst = blockAgainst;
    }

    public Player getPlayer() {
        return player;
    }

    public Block getBlock() {
        return block;
    }

    public Block getBlockAgainst() {
        return blockAgainst;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.BLOCK_PLACE_AGAINST.getPermission(),
            this.block.getType().name(),
            this.blockAgainst.getType().name()
        );
    }
}
