package me.jsthats.worldinteractions.events.vehicles;

import me.jsthats.worldinteractions.enums.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerBreakVehicleWithEvent extends PlayerBreakVehicleEvent {
    protected final ItemStack item;

    public PlayerBreakVehicleWithEvent(@NotNull Player player, @NotNull Vehicle vehicle, @NotNull ItemStack item) {
        super(player, vehicle);
        this.item = item;
    }

    @NotNull
    public ItemStack getItem() {
        return item;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.VEHICLE_BREAK_WITH.getPermission(),
            this.vehicle.getType().name(),
            this.item.getType().name()
        );
    }
}
