package me.jsthats.worldinteractions.events.items;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class PlayerEnchantItemEvent extends CustomEvent {
    protected final Player player;
    protected final ItemStack item;

    public PlayerEnchantItemEvent(@NotNull Player player, @NotNull ItemStack item) {
        this.player = player;
        this.item = item;
    }

    @NotNull
    public Player getPlayer() {
        return player;
    }

    @NotNull
    public ItemStack getItem() {
        return item;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.ENCHANT_ITEM.getPermission(),
            this.item.getType().name()
        );
    }
}
