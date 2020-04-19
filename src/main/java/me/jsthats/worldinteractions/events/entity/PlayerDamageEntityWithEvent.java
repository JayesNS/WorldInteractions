package me.jsthats.worldinteractions.events.entity;

import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;

public class PlayerDamageEntityWithEvent extends CustomEvent {
    protected final EntityDamageByEntityEvent sourceEvent;

    public PlayerDamageEntityWithEvent(@NotNull EntityDamageByEntityEvent sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @NotNull
    public Player getPlayer() {
        return (Player) sourceEvent.getDamager();
    }

    @Override
    public String getPermission() {
        return String.format(
            Permissions.DAMAGE_WITH.getPermission(),
            sourceEvent.getEntity().getType().name(),
            getPlayer().getInventory().getItemInMainHand().getType().name()
        );
    }
}
