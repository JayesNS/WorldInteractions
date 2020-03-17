/*
 * WorldInteractions - Spigot permission ruleset plugin
 * Copyright (C) 2019 t3hk0d3 Jayes
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.jsthats.worldinteractions.handlers;

import me.jsthats.worldinteractions.helpers.GenericListener;
import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.helpers.MaterialUtils;
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

import java.util.Arrays;

/**
 * @author t3hk0d3 Jayes
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

		if (material != Material.AIR
			&& !doesPlayerHavePermission(player, Permissions.BUCKET_FILL, material)) {
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
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		Entity clickedEntity = event.getRightClicked();
		ItemStack heldItem = player.getInventory().getItemInMainHand();

		if (MaterialUtils.isSpawnEgg(heldItem.getType())
			&& !doesPlayerHavePermission(player, Permissions.SPAWN, heldItem)) {
			event.setCancelled(true);
			return;
		}

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
		ItemStack heldItem = event.getItem();
		Block clickedBlock = event.getClickedBlock();

		if (heldItem != null && clickedBlock != null) {
			// Left clicking block with item in hand
			/*if (action == Action.LEFT_CLICK_BLOCK
				&& config.shouldCheckItemsUse()
				&& config.getLeftClickItemsUse().contains(heldItem.getType())
				&& !doesPlayerHavePermission(player, Permissions.USE, heldItem, "on", clickedBlock)) {
				event.setCancelled(true);
				return;
			}*/

			// Right clicking block with item in hand
			if (action == Action.RIGHT_CLICK_BLOCK
					&& config.shouldCheckItemsUse()
					&& config.getRightClickItemsUse().contains(heldItem.getType())
					&& !doesPlayerHavePermission(player, Permissions.USE, heldItem, "on", clickedBlock)) {
				event.setCancelled(true);
				return;
			}

			if (action == Action.RIGHT_CLICK_BLOCK
				&& MaterialUtils.isVehicle(heldItem.getType())
				&& !doesPlayerHavePermission(player, Permissions.VEHICLE_PLACE, heldItem)) {
				event.setCancelled(true);
				return;
			}

			if ((action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR)
				&& MaterialUtils.isSpawnEgg(heldItem.getType())
				&& !doesPlayerHavePermission(player, Permissions.SPAWN, heldItem)) {
				event.setCancelled(true);
				return;
			}
		}

		if (clickedBlock != null) {
			// Handle triggering redstone mechanisms
			if (action == Action.PHYSICAL && !doesPlayerHavePermission(player, Permissions.INTERACT, clickedBlock)) {
				event.setCancelled(true);
				return;
			}
			// Interactable blocks
			if (clickedBlock.getType().isInteractable()
				&& action == Action.RIGHT_CLICK_BLOCK
				&& !doesPlayerHavePermission(player, Permissions.INTERACT, clickedBlock)) {
				event.setCancelled(true);
				return;
			}
		}

		if (heldItem != null) {
			// Check use of throwable items
			if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
				Material[] throwableItems = {
						Material.EGG,
						Material.ENDER_EYE,
						Material.EXPERIENCE_BOTTLE,
						Material.LINGERING_POTION,
						Material.SPLASH_POTION,
						Material.SNOWBALL,
						Material.TRIDENT
				};
				boolean isThrowableItem = Arrays.asList(throwableItems).contains(heldItem.getType());
				if (isThrowableItem && !doesPlayerHavePermission(player, Permissions.THROW, heldItem)) {
					event.setCancelled(true);
					return;
				}
			}

			// Left clicking air with item
/*			if (action == Action.LEFT_CLICK_AIR) {
				if (config.shouldCheckItemsUse()
					&& config.getLeftClickItemsUse().contains(heldItem.getType())
					&& !doesPlayerHavePermission(player, Permissions.USE, heldItem)) {
					event.setCancelled(true);
				}
			}*/
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

		if (event.getSlotType() == InventoryType.SlotType.OUTSIDE) {
			return;
		}

		if (item != null && Arrays.asList(furnaces).contains(inventory.getType())) {
			if (item.getType() != Material.AIR
				&& event.getSlotType() == InventoryType.SlotType.CRAFTING
			) {
				if (!doesPlayerHavePermission(player, Permissions.SMELT, item)) {
					event.setCancelled(true);
				}
			} else if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY
				&& event.getCurrentItem().getType() != Material.AIR
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
		    && !doesPlayerHavePermission(player, Permissions.CRAFT, event.getCurrentItem())
		) {
			event.setCancelled(true);
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
}
