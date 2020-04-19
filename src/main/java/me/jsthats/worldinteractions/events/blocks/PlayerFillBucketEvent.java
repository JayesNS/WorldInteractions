package me.jsthats.worldinteractions.events.blocks;

import org.jetbrains.annotations.NotNull;
import org.bukkit.event.player.PlayerBucketFillEvent;

import me.jsthats.worldinteractions.events.CustomEvent;
import me.jsthats.worldinteractions.enums.Permissions;

public class PlayerFillBucketEvent extends CustomEvent {
    protected final PlayerBucketFillEvent sourceEvent;

    public PlayerFillBucketEvent(@NotNull PlayerBucketFillEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.BUCKET_FILL_WITH.getPermission(),
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
