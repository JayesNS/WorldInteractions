package me.jsthats.worldinteractions.listeners.custom;

import me.jsthats.worldinteractions.events.*;
import me.jsthats.worldinteractions.events.blocks.*;
import me.jsthats.worldinteractions.events.items.PlayerEnchantItemWithEvent;
import me.jsthats.worldinteractions.events.vehicles.PlayerBreakVehicleWithEvent;
import me.jsthats.worldinteractions.helpers.PlayerNotifier;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

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
                lastEvent.getPermissionMessage(),
                arguments
            );
            lastEvent.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerBreakBlockWith(PlayerBreakBlockWithItemEvent event) {
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
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerBreakBlockWith(PlayerBreakBlockEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        checkPlayerPermissions(
            player,
            Arrays.asList(event),
            block.getType().name()
        );
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerPlaceBlockAgainstBlock(PlayerPlaceBlockAgainstBlockEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Block blockAgainst = event.getBlockAgainst();

        PlayerPlaceBlockEvent playerPlaceBlockEvent = new PlayerPlaceBlockEvent(player, block);

        checkPlayerPermissions(
            player,
            Arrays.asList(playerPlaceBlockEvent, event),
            block.getType().name(),
            blockAgainst.getType().name()
        );
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerPlaceBlockUsingItem(PlayerPlaceBlockUsingItemEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack itemInHand = event.getItem();

        PlayerPlaceBlockEvent playerPlaceBlockEvent = new PlayerPlaceBlockEvent(player, block);

        checkPlayerPermissions(
            player,
            Arrays.asList(playerPlaceBlockEvent, event),
            block.getType().name(),
            itemInHand.getType().name()
        );
    }

    // Player Break Hanging
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerBreakHangingWith(PlayerBreakHangingWithItemEvent event) {
        Player player = event.getPlayer();
        Entity hanging = event.getHanging();
        ItemStack itemInHand = event.getItem();

        PlayerBreakHangingEvent playerBreakHangingEvent = new PlayerBreakHangingEvent(player, hanging);

        checkPlayerPermissions(
            player,
            Arrays.asList(playerBreakHangingEvent, event),
            hanging.getType().name(),
            itemInHand.getType().name()
        );
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerBreakHanging(PlayerBreakHangingEvent event) {
        Player player = event.getPlayer();
        Entity hanging = event.getHanging();

        checkPlayerPermissions(
            player,
            Arrays.asList(event),
            hanging.getType().name()
        );
    }

    // Player Place Hanging
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerPlaceHangingAgainst(PlayerPlaceHangingAgainstBlockEvent event) {
        Player player = event.getPlayer();
        Entity hanging = event.getHanging();
        Block blockAgainst = event.getBlock();

        PlayerPlaceHangingEvent playerPlaceHangingEvent = new PlayerPlaceHangingEvent(player, hanging);

        checkPlayerPermissions(
            player,
            Arrays.asList(playerPlaceHangingEvent, event),
            hanging.getType().name(),
            blockAgainst.getType().name()
        );
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerPlaceHanging(PlayerPlaceHangingEvent event) {
        Player player = event.getPlayer();
        Entity hanging = event.getHanging();

        checkPlayerPermissions(
                player,
                Arrays.asList(event),
                hanging.getType().name()
        );
    }

    // Player Break Vehicle
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerBreakVehicleWith(PlayerBreakVehicleWithEvent event) {
        Player player = event.getPlayer();
        Vehicle vehicle = event.getVehicle();
        ItemStack item = event.getItem();

        checkPlayerPermissions(
            player,
            Arrays.asList(event),
            vehicle.getName(),
            item.getType().name()
        );
    }

    // Needs special treatment because of multiple enchantments
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerEnchantItemWith(PlayerEnchantItemWithEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Map<Enchantment, Integer> enchantments = event.getEnchantments();

        List<Map.Entry<Enchantment, Integer>> forbiddenEnchantments = enchantments.entrySet()
            .stream()
            .filter(entry -> !player.hasPermission(event.getPermission(entry)))
            .collect(Collectors.toList());

        if (forbiddenEnchantments.size() > 0) {
            Map.Entry<Enchantment, Integer> firstForbiddenEnchantment = forbiddenEnchantments.get(0);
            this.notifier.informPlayer(
                player,
                event.getPermission(firstForbiddenEnchantment),
                item.getType().name(),
                firstForbiddenEnchantment.getKey().getKey().getKey().toUpperCase(),
                firstForbiddenEnchantment.getValue().toString()
            );
            event.setCancelled(true);
        }
    }
}
