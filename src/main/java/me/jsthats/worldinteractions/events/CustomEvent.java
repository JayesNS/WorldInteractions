package me.jsthats.worldinteractions.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class CustomEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;

    public HandlerList getHandlers() {
        return handlers;
    }

    public static void callCustomEvent(CustomEvent customEvent, Cancellable bukkitEvent) {
        Bukkit.getServer().getPluginManager().callEvent(customEvent);
        if (customEvent.isCancelled()) {
            bukkitEvent.setCancelled(true);
        }
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public abstract String getPermission();

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
