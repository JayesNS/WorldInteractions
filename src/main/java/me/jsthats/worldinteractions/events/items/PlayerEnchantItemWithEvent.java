package me.jsthats.worldinteractions.events.items;

import org.jetbrains.annotations.NotNull;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.enchantment.EnchantItemEvent;

import me.jsthats.worldinteractions.enums.Permissions;

import java.util.Map;

public class PlayerEnchantItemWithEvent extends PlayerEnchantItemEvent {
    public PlayerEnchantItemWithEvent(@NotNull EnchantItemEvent sourceEvent) {
        super(sourceEvent);
    }

    public String getPermission(@NotNull Map.Entry<Enchantment, Integer> enchantment) {
        return String.format(
            Permissions.ENCHANT_ITEM_WITH.getPermission(),
            sourceEvent.getItem().getType().name(),
            enchantment.getKey().getKey().getKey(),
            enchantment.getValue().toString()
        );
    }
}
