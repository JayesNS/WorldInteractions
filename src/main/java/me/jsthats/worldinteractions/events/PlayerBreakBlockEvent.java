package me.jsthats.worldinteractions.events;

import me.jsthats.worldinteractions.enums.Permissions;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class PlayerBreakBlockEvent extends CustomEvent {
    private final Player player;
    private final Block block;

    public PlayerBreakBlockEvent(Player player, Block block) {
        this.player = player;
        this.block = block;
    }

    public Player getPlayer() {
        return player;
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.BLOCK_BREAK.getPermission(),
            this.block.getType().name()
        );
    }
}
