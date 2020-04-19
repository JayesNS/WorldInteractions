package me.jsthats.worldinteractions.events.vehicles;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;
import me.jsthats.worldinteractions.helpers.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerPlaceVehicleEvent extends CustomEvent {
    protected final Player player;
    protected final ItemStack creationItem;

    public PlayerPlaceVehicleEvent(@NotNull Player player, @NotNull ItemStack creationItem) {
        this.player = player;
        this.creationItem = creationItem;
    }

    @NotNull
    public Player getPlayer() {
        return player;
    }

    @NotNull
    public ItemStack getCreationItem() {
        return creationItem;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.VEHICLE_PLACE.getPermission(),
            ObjectUtils.getEntityTypeOfMaterial(this.creationItem.getType())
        );
    }
}
