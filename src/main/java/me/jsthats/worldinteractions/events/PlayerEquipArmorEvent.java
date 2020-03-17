package me.jsthats.worldinteractions.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerEquipArmorEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Material armorPiece;

    public PlayerEquipArmorEvent(Player player, Material armorPiece) {
        this.player = player;
        this.armorPiece = armorPiece;
    }

    public Player getPlayer() {
        return player;
    }

    public Material getArmorPiece() {
        return armorPiece;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
