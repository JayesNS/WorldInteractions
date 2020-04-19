package me.jsthats.worldinteractions.listeners.custom;

import me.jsthats.worldinteractions.events.*;
import me.jsthats.worldinteractions.events.blocks.*;
import me.jsthats.worldinteractions.events.entity.PlayerBreedEntitiesEvent;
import me.jsthats.worldinteractions.events.entity.PlayerTameEntityEvent;
import me.jsthats.worldinteractions.events.items.*;
import me.jsthats.worldinteractions.events.entity.PlayerDamageEntityWithEvent;
import me.jsthats.worldinteractions.events.player.PlayerFishEntityEvent;
import me.jsthats.worldinteractions.events.player.PlayerShootBowEvent;
import me.jsthats.worldinteractions.events.player.PlayerUseBedEvent;
import me.jsthats.worldinteractions.events.vehicles.*;
import me.jsthats.worldinteractions.helpers.PlayerNotifier;
import me.jsthats.worldinteractions.helpers.PluginConfig;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class EventListeners implements Listener {

    private final PluginConfig config;
    private final PlayerNotifier notifier;

    public EventListeners(PlayerNotifier notifier, PluginConfig config) {
        this.notifier = notifier;
        this.config = config;
    }

    private boolean checkPlayerPermissions(Player player, CustomEvent event) {
        String permission = event.getPermission();
        boolean hasPermission = false;
        while (permission.contains(".")) {
            hasPermission = player.hasPermission(permission);
            if (hasPermission) {
                break;
            }
            permission = permission.contains(".") ? permission.substring(0, permission.lastIndexOf('.')) : "";
        }

        if (!hasPermission) {
            this.notifier.informPlayer(
                player,
                event.getPermissionMessage(),
                event.getPermissionParameters()
            );
            event.setCancelled(true);
            return false;
        }
        return true;
    }

    /*@EventHandler(priority = EventPriority.LOW)
    public void onPlayerBreakBlockWithItem(PlayerBreakBlockWithItemEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack item = event.getItemInHand();

        checkPlayerPermissions(
            player,
            event,
            block.getType().name(),
            item.getType().name()
        );

    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerPlaceBlockAgainstBlock(PlayerPlaceBlockAgainstBlockEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Block blockAgainst = event.getBlockAgainst();

        checkPlayerPermissions(
            player,
            event,
            block.getType().name(),
            blockAgainst.getType().name()
        );
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerPlaceBlockUsingItem(PlayerPlaceBlockUsingItemEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack itemInHand = event.getItem();

        checkPlayerPermissions(
            player,
            event,
            block.getType().name(),
            itemInHand.getType().name()
        );
    }

    // Player Break Hanging
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerBreakHangingWithItem(PlayerBreakHangingWithItemEvent event) {
        Player player = event.getPlayer();
        Entity hanging = event.getHanging();
        ItemStack itemInHand = event.getItem();

        checkPlayerPermissions(
            player,
            event,
            hanging.getType().name(),
            itemInHand.getType().name()
        );
    }

    // Player Place Hanging
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerPlaceHangingAgainstBlock(PlayerPlaceHangingAgainstBlockEvent event) {
        Player player = event.getPlayer();
        Entity hanging = event.getHanging();
        Block blockAgainst = event.getBlock();

        checkPlayerPermissions(
            player,
            event,
            hanging.getType().name(),
            blockAgainst.getType().name()
        );
    }

    // Player Break Vehicle
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerBreakVehicleWithItem(PlayerBreakVehicleWithItemEvent event) {
        Player player = event.getPlayer();
        Vehicle vehicle = event.getVehicle();
        ItemStack item = event.getItem();

        checkPlayerPermissions(
            player,
            event,
            vehicle.getType().name(),
            item.getType().name()
        );
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerPlaceVehicle(PlayerPlaceVehicleEvent event) {
        Player player = event.getPlayer();
        ItemStack creationItem = event.getCreationItem();

        checkPlayerPermissions(
            player,
            event,
            creationItem.getType().name()
        );
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerPushVehicle(PlayerPushVehicleEvent event) {
        Player player = event.getPlayer();
        Vehicle vehicle = event.getVehicle();

        checkPlayerPermissions(
            player,
            event,
            vehicle.getType().name()
        );
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerEnterVehicle(PlayerEnterVehicleEvent event) {
        Player player = event.getPlayer();
        Vehicle vehicle = event.getVehicle();

        checkPlayerPermissions(
            player,
            event,
            vehicle.getType().name()
        );
    }

    // Player Enchant Item

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

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerFillBucket(PlayerFillBucketEvent event) {
        Player player = event.getPlayer();
        Block liquid = event.getLiquid();

        checkPlayerPermissions(
            player,
            event,
            liquid.getType().name()
        );
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerEmptyBucket(PlayerEmptyBucketEvent event) {
        Player player = event.getPlayer();
        ItemStack bucket = event.getBucket();

        checkPlayerPermissions(
            player,
            event,
            bucket.getType().name()
        );
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerCraftItem(PlayerCraftItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        checkPlayerPermissions(
            player,
            event,
            item.getType().name()
        );
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerDiscoverRecipe(PlayerDiscoverRecipeEvent event) {
        Player player = event.getPlayer();
        Material item = event.getItem();

        checkPlayerPermissions(
            player,
            event,
            item.name()
        );
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerFishEntity(PlayerFishEntityEvent event) {
        Player player = event.getPlayer();
        Entity caughtEntity = event.getCaughtEntity();

        checkPlayerPermissions(
            player,
            event,
            caughtEntity.getType().name()
        );
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerUseBed(PlayerUseBedEvent event) {
        Player player = event.getPlayer();

        checkPlayerPermissions(
            player,
            event
        );
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        checkPlayerPermissions(
            player,
            event,
            item.getType().name()
        );
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerDamageEntityWith(PlayerDamageEntityWithEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getEntity();
        ItemStack itemInHand = event.getItemInHand();

        checkPlayerPermissions(
            player,
            event,
            entity.getType().name(),
            itemInHand.getType().name()
        );
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerTameEntity(PlayerTameEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getEntity();

        checkPlayerPermissions(
            player,
            event,
            entity.getType().name()
        );
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerBreedEntities(PlayerBreedEntitiesEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getEntity();

        checkPlayerPermissions(
            player,
            event,
            entity.getType().name()
        );
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerShootBow(PlayerShootBowEvent event) {
        Player player = event.getPlayer();

        checkPlayerPermissions(
            player,
            event
        );
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        checkPlayerPermissions(
            player,
            event,
            item.getType().name()
        );
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        checkPlayerPermissions(
            player,
            event,
            item.getType().name()
        );
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerHoldItem(PlayerHoldItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        Inventory inventory = player.getInventory();

        if (!checkPlayerPermissions(player, event, item.getType().name())) {
            // Drop restricted items
            if (config.shouldDropForbiddenItems()) {
                inventory.remove(item);
                player.getWorld().dropItemNaturally(player.getLocation(), item);
            } else {
                int freeSlot = inventory.firstEmpty();

                if (freeSlot >= 9) { // Move item to another slot
                    inventory.remove(item);
                    inventory.setItem(freeSlot, item);
                    player.getInventory().setHeldItemSlot(inventory.firstEmpty());
                } else if (freeSlot < 0) { // Not enough space in inventory, drop item
                    inventory.remove(item);
                    player.getWorld().dropItemNaturally(player.getLocation(), item);
                } else { // Change held item
                    player.getInventory().setHeldItemSlot(freeSlot);
                }
            }
        }
    }*/
}
