package me.jsthats.worldinteractions.events.items;

import org.jetbrains.annotations.NotNull;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerRecipeDiscoverEvent;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;
import me.jsthats.worldinteractions.helpers.ObjectUtils;

public class PlayerDiscoverRecipeEvent extends CustomEvent {
    protected final PlayerRecipeDiscoverEvent sourceEvent;

    public PlayerDiscoverRecipeEvent(@NotNull PlayerRecipeDiscoverEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.CRAFT.getPermission(),
            getItem().name()
        );
    }

    public Material getItem() {
        return ObjectUtils.getMaterialFromNamespacedKey(sourceEvent.getRecipe());
    }

    @Override
    public String getPermissionMessage() {
        return String.format(
            Permissions.DISCOVER_RECIPE.getPermission(),
            getItem().name()
        );
    }
}
