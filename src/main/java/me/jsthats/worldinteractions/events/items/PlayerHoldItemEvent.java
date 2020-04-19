package me.jsthats.worldinteractions.events.items;

import org.jetbrains.annotations.NotNull;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;

public class PlayerHoldItemEvent extends CustomEvent {
    protected final PlayerItemHeldEvent sourceEvent;

    public PlayerHoldItemEvent(@NotNull PlayerItemHeldEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    public ItemStack getItem() {
        return sourceEvent.getPlayer().getInventory().getItem(sourceEvent.getNewSlot());
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.HOLD.getPermission(),
            getItem().getType().name()
        );
    }
}
