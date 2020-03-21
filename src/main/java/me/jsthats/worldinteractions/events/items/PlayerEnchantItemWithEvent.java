package me.jsthats.worldinteractions.events.items;

import me.jsthats.worldinteractions.enums.Permissions;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class PlayerEnchantItemWithEvent extends PlayerEnchantItemEvent {
    private final Map<Enchantment, Integer> enchantments;

    public PlayerEnchantItemWithEvent(@NotNull Player player, @NotNull ItemStack item, @NotNull Map<Enchantment, Integer> enchantments) {
        super(player, item);
        this.enchantments = enchantments;
    }

    @NotNull
    public Map<Enchantment, Integer> getEnchantments() {
        return enchantments;
    }

    public String getPermission(@NotNull Map.Entry<Enchantment, Integer> enchantment) {
        return String.format(
            Permissions.ENCHANT_ITEM_WITH.getPermission(),
            this.item.getType().name(),
            enchantment.getKey().getKey().getKey(),
            enchantment.getValue().toString()
        );
    }
}
