package me.jsthats.worldinteractions.events.blocks;

import org.jetbrains.annotations.NotNull;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;

public class PlayerEmptyBucketEvent extends CustomEvent {
    protected final PlayerBucketEmptyEvent sourceEvent;

    public PlayerEmptyBucketEvent(@NotNull PlayerBucketEmptyEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.BUCKET_EMPTY.getPermission(),
            getPermissionParameters()
        );
    }

    @Override
    public String[] getPermissionParameters() {
        return new String[] {
            sourceEvent.getItemStack().getType().name()
        };
    }
}
