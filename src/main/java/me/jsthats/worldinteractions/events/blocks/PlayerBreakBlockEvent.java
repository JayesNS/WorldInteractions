package me.jsthats.worldinteractions.events.blocks;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerBreakBlockEvent extends CustomEvent {
    protected final Player player;
    protected final Block block;

    public PlayerBreakBlockEvent(@NotNull Player player, @NotNull Block block) {
        this.player = player;
        this.block = block;
    }

    @NotNull
    public Player getPlayer() {
        return player;
    }

    @NotNull
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
