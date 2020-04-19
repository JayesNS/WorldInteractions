package me.jsthats.worldinteractions.events.entity;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;
import me.jsthats.worldinteractions.events.vehicles.PlayerEnterVehicleEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Tameable;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerMountEntityEvent extends CustomEvent {
    protected final VehicleEnterEvent sourceEvent;

    public PlayerMountEntityEvent(@NotNull VehicleEnterEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @Override
    public String getPermission() {
        Tameable entity = (Tameable) sourceEvent.getVehicle();
        return String.format(
            Permissions.MOUNT.getPermission(),
            entity.getType().name(),
            entity.getOwner().equals(sourceEvent.getEntered()) ? "own" : "wild"
        );
    }
}
