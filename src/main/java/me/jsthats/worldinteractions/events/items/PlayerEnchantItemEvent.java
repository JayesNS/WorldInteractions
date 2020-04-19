package me.jsthats.worldinteractions.events.items;

import org.bukkit.event.enchantment.EnchantItemEvent;
import org.jetbrains.annotations.NotNull;

import me.jsthats.worldinteractions.events.CustomEvent;

public class PlayerEnchantItemEvent extends CustomEvent {
    protected final EnchantItemEvent sourceEvent;

    public PlayerEnchantItemEvent(@NotNull EnchantItemEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @Override
    public String getPermission() {
        return null;
    }
}
