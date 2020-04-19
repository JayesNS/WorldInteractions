package me.jsthats.worldinteractions.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class CustomEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;

    /**
     * Handles custom event calling
     * @param customEvent WorldInteraction event
     * @param bukkitEvent Original Bukkit event
     * @return Returns true if event has been cancelled
     */
    public static boolean call(CustomEvent customEvent, Cancellable bukkitEvent) {
        Bukkit.getServer().getConsoleSender().sendMessage(
            ChatColor.GREEN + customEvent.getEventName() + ChatColor.RESET + " has been called!".replaceAll("&([a-z0-9])", "\u00A7$1"));
        Bukkit.getServer().getPluginManager().callEvent(customEvent);
        if (customEvent.isCancelled()) {
            bukkitEvent.setCancelled(true);
        }
        return customEvent.isCancelled();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }

    public abstract String getPermission();
    public String[] getPermissionParameters() {
        return new String[] {};
    };

    public String getPermissionMessage() {
        return getPermission();
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }
    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
