/*
 * WorldInteractions - permission ruleset plugin for Spigot server
 * Copyright (C) 2019 Jayes http://www.jsthats.me
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package me.jsthats.worldinteractions.handlers;

import java.util.Arrays;

import me.jsthats.worldinteractions.helpers.GenericListener;
import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.helpers.PlayerNotifier;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;
import org.bukkit.plugin.Plugin;
import me.jsthats.worldinteractions.helpers.PluginConfig;

/**
 * @author t3hk0d3 g4rnek
 */
public class PlayerListener extends GenericListener {

	PluginConfig config;

	public PlayerListener(Plugin plugin, PluginConfig config, PlayerNotifier notifier) {
		super(plugin, config, notifier);

		this.config = config;
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerBedEnter(PlayerBedEnterEvent event) {
		Player player = event.getPlayer();

		if (!doesPlayerHavePermission(player, Permissions.USE_BEDS)) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
		Player player = event.getPlayer();
		Material bucket = event.getBucket();

		if (!doesPlayerHavePermission(player, Permissions.BUCKET_EMPTY, bucket)) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerBucketFill(PlayerBucketFillEvent event) {
		Player player = event.getPlayer();
		Material material = event.getBlockClicked().getType();

		if (!doesPlayerHavePermission(player, Permissions.BUCKET_FILL, material)) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();

		if (!doesPlayerHavePermission(player, Permissions.CHAT)) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		Item[] objectsToDescribe = {event.getItemDrop()};

		if (!doesPlayerHavePermission(player, Permissions.DROP, objectsToDescribe)) {
			event.setCancelled(true);
		}
		this.checkPlayerInventory(player);
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onItemHeldChange(PlayerItemHeldEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItem(event.getNewSlot());

		if (item == null) {
			return;
		}

		Material[] ignoredItems = {Material.AIR};
		boolean isIgnoredItem = Arrays.asList(ignoredItems).contains(item.getType());

		if (!isIgnoredItem
			&& !doesPlayerHavePermission(player, Permissions.HOLD, item)
		) {
			Inventory inventory = player.getInventory();

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
			event.setCancelled(true);
		}

		this.checkPlayerInventory(player);
	}

	/*@EventHandler(priority = EventPriority.LOW)
	public void onPlayerInventoryClick(InventoryClickEvent event) {
		InventoryHolder holder = event.getInventory().getHolder();

		if (holder instanceof Player || // do not track inter-inventory stuff
				event.getRawSlot() >= event.getView().getTopInventory().getSize() || // top inventory only
				event.getSlotType() == InventoryType.SlotType.OUTSIDE ||  // do not track drop
				event.getSlot() == -999) { // temporary fix for bukkit bug (BUKKIT-2768)
			return;
		}

		ItemStack take = event.getCurrentItem();

		String action;
		ItemStack item;

		if (take == null) {
			action = "put";
			item = event.getCursor();
		} else {
			action = "take";
			item = take;
		}

		Player player = (Player) event.getWhoClicked();

		if (permissionDenied(player, "modifyworld.items", action, item, "of", event.getInventory().getType())) {
			event.setCancelled(true);
		}
	}*/

	/*@EventHandler(priority = EventPriority.LOW)
	public void onPlayerInventoryEvent(InventoryClickEvent event) {
		ItemStack item = event.getCursor();

		if (item == null || item.getType() == Material.AIR || event.getSlotType() != InventoryType.SlotType.QUICKBAR) {
			return;
		}

		Player player = (Player) event.getWhoClicked();

		int targetSlot = player.getInventory().getHeldItemSlot();

		if (event.getSlot() == targetSlot && permissionDenied(player, "modifyworld.items.hold", item)) {
			event.setCancelled(true);
		}
	}*/

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		Entity clickedEntity = event.getRightClicked();
		ItemStack heldItem = player.getInventory().getItemInMainHand();

		if (config.shouldCheckItemsUse()
			&& config.getRightClickItemsUse().contains(heldItem.getType())
			&& !doesPlayerHavePermission(player, Permissions.USE, heldItem, "on", clickedEntity)) {
			event.setCancelled(true);
			return;
		}

		if (!doesPlayerHavePermission(player, Permissions.INTERACT, clickedEntity)) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerInteract(PlayerInteractEvent event) {
		Action action = event.getAction();
		Player player = event.getPlayer();
		ItemStack heldItem = event.getPlayer().getInventory().getItemInMainHand();
		Block clickedBlock = event.getClickedBlock();

		Material[] throwableItems = {
			Material.EGG,
			Material.SNOWBALL,
			Material.EXPERIENCE_BOTTLE,
			Material.LINGERING_POTION,
			Material.SPLASH_POTION,
			Material.TRIDENT
		};

		if (heldItem != null && (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)) {
			boolean isThrowableItem = Arrays.asList(throwableItems).contains(heldItem.getType());
			if (isThrowableItem && !doesPlayerHavePermission(player, Permissions.THROW, heldItem)) {
				event.setCancelled(true);
			}
		}

		// Handle triggering redstone mechanisms
		if (clickedBlock != null
			&& action == Action.PHYSICAL
			&& !doesPlayerHavePermission(player, Permissions.INTERACT, clickedBlock)) {
			event.setCancelled(true);
			return;
		}

		if (clickedBlock != null && clickedBlock.getType().isInteractable()
			&& event.getAction() == Action.RIGHT_CLICK_BLOCK
			&& !doesPlayerHavePermission(player, Permissions.INTERACT, clickedBlock)) {
			event.setCancelled(true);
		}

		if (heldItem != null
			&& action == Action.LEFT_CLICK_AIR) {
			if (config.shouldCheckItemsUse()
				&& config.getLeftClickItemsUse().contains(heldItem.getType())
				&& !doesPlayerHavePermission(player, Permissions.USE, heldItem)) {
				event.setCancelled(true);
				return;
			}
		}

		if (heldItem != null && clickedBlock != null && action == Action.LEFT_CLICK_BLOCK) {
			if (config.shouldCheckItemsUse()
				&& config.getLeftClickItemsUse().contains(heldItem.getType())
				&& !doesPlayerHavePermission(player, Permissions.USE, heldItem, "on", clickedBlock)) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onInventory(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		Inventory inventory = event.getInventory();
		ItemStack item = event.getCursor();

		InventoryType[] furnaces = {
			InventoryType.FURNACE,
			InventoryType.SMOKER,
			InventoryType.BLAST_FURNACE
		};
		InventoryType[] workstations = {
			InventoryType.CARTOGRAPHY,
			InventoryType.GRINDSTONE,
			InventoryType.LOOM,
			InventoryType.STONECUTTER,
			InventoryType.CRAFTING,
			InventoryType.WORKBENCH
		};

		if (Arrays.asList(furnaces).contains(inventory.getType())) {
			if (item.getType() != Material.AIR
				&& event.getSlotType() == InventoryType.SlotType.CRAFTING
			) {
				if (!doesPlayerHavePermission(player, Permissions.SMELT, item)) {
					event.setCancelled(true);
				}
			} else if (event.getCurrentItem().getType() != Material.AIR
				&& event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY
				&& (event.getSlotType() == InventoryType.SlotType.CONTAINER
					|| event.getSlotType() == InventoryType.SlotType.QUICKBAR)
			) {
				if (!doesPlayerHavePermission(player, Permissions.SMELT, event.getCurrentItem())) {
					event.setCancelled(true);
				}
			}
		} else if (event.getCurrentItem() != null
			&& Arrays.asList(workstations).contains(inventory.getType())
			&& event.getCurrentItem().getType() != Material.AIR
			&& event.getSlotType() == InventoryType.SlotType.RESULT
		) {
			if (!doesPlayerHavePermission(player, Permissions.CRAFT, event.getCurrentItem())) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerRecipeDiscover(PlayerRecipeDiscoverEvent event) {
		Player player = event.getPlayer();
		String materialName = event.getRecipe().getKey().replaceAll("_from.*", "").toUpperCase();
		Material material = Material.getMaterial(materialName);
		Object[] arguments = {material};
		if (!doesPlayerHavePermission(player, Permissions.CRAFT, arguments)) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerFish(PlayerFishEvent event) {
		Player player = event.getPlayer();
		ItemStack fishingRod = player.getInventory().getItemInMainHand();
		if (config.shouldCheckItemsUse()
			&& config.getRightClickItemsUse().contains(fishingRod.getType())
			&& event.getState() == PlayerFishEvent.State.FISHING
			&& !doesPlayerHavePermission(player, Permissions.USE, Material.FISHING_ROD)
		) {
			event.setCancelled(true);
		}
	}

//	@EventHandler(priority = EventPriority.LOW)
//	public void onItemCraft(CraftItemEvent event) {
//		Player player = (Player) event.getWhoClicked();
//		ItemStack craftedItem = event.getRecipe().getResult();
//
//		if (!doesPlayerHavePermission(player, Permissions.CRAFT, craftedItem)) {
//			event.setCancelled(true);
//		}
//	}

	@EventHandler(priority = EventPriority.LOW)
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			ItemStack foodSource = event.getItem();

			if (foodSource != null
				&& !doesPlayerHavePermission(player, Permissions.EAT, foodSource)
			) {
				event.setCancelled(true);

				// Return food to player
				foodSource.setAmount(1);
				player.getInventory().addItem(foodSource);
			}
		}
	}

	protected void checkPlayerInventory(Player player) {
		/*if (!this.checkInventory) {
			return;
		}

		Inventory inventory = player.getInventory();
		Arrays.asList(inventory.getContents()).forEach(item -> {
			if (item != null
				&& !doesPlayerHavePermission(player, Permissions.HAVE, item)
			) {
				inventory.remove(item);

				if (this.alwaysDropRestrictedItem) {
					player.getWorld().dropItemNaturally(player.getLocation(), item);
				}
			}
		});*/
	}
}
