package me.jsthats.worldinteractions.events.blocks;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerPlaceHangingEvent extends CustomEvent {
    protected final Player player;
    protected final Entity hanging;

    public PlayerPlaceHangingEvent(@NotNull Player player, @NotNull Entity hanging) {
        this.player = player;
        this.hanging = hanging;
    }

    @NotNull
    public Player getPlayer() {
        return player;
    }

    @NotNull
    public Entity getHanging() {
        return hanging;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.BLOCK_PLACE.getPermission(),
            this.hanging.getType().name()
        );
    }
}
