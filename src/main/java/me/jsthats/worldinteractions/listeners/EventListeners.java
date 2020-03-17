package me.jsthats.worldinteractions.listeners;

import me.jsthats.worldinteractions.events.*;
import me.jsthats.worldinteractions.helpers.PlayerNotifier;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class EventListeners implements Listener {

    PlayerNotifier notifier;

    public EventListeners(PlayerNotifier notifier) {
        this.notifier = notifier;
    }

    private void checkPlayerPermissions(Player player, List<CustomEvent> events, String ...arguments) {
        int lastEventIndex = Math.max(0, events.size() - 1);
        CustomEvent lastEvent = events.get(lastEventIndex);
        if (events.stream().noneMatch(event -> player.hasPermission(event.getPermission()))) {
            this.notifier.informPlayer(
                player,
                lastEvent.getPermission(),
                arguments
            );
            lastEvent.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerBreakBlockWith(PlayerBreakBlockWithEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack item = event.getItemInHand();

        PlayerBreakBlockEvent playerBreakBlockEvent = new PlayerBreakBlockEvent(player, block);

        checkPlayerPermissions(
            player,
            Arrays.asList(playerBreakBlockEvent, event),
            block.getType().name(),
            item.getType().name()
        );

    }

    @EventHandler
    public void onPlayerBreakBlockWith(PlayerBreakBlockEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        checkPlayerPermissions(
            player,
            Arrays.asList(event),
            block.getType().name()
        );
    }

    @EventHandler
    public void onPlayerPlaceBlockAgainst(PlayerPlaceBlockAgainstEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Block blockAgainst = event.getBlockAgainst();

        checkPlayerPermissions(
            player,
            Arrays.asList(event),
            block.getType().name(),
            blockAgainst.getType().name()
        );
    }
}
