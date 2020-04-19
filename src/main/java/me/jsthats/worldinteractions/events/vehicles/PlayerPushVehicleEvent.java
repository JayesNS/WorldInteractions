package me.jsthats.worldinteractions.events.vehicles;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.jetbrains.annotations.NotNull;

public class PlayerPushVehicleEvent extends CustomEvent {
    protected final Player player;
    protected final Vehicle vehicle;

    public PlayerPushVehicleEvent(@NotNull Player player, @NotNull Vehicle vehicle) {
        this.player = player;
        this.vehicle = vehicle;
    }

    @NotNull
    public Player getPlayer() {
        return player;
    }

    @NotNull
    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.VEHICLE_PUSH.getPermission(),
            this.vehicle.getType().name()
        );
    }
}
