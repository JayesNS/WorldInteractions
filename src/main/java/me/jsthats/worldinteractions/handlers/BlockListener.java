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

import me.jsthats.worldinteractions.events.*;
import me.jsthats.worldinteractions.helpers.GenericListener;
import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.helpers.PlayerNotifier;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import me.jsthats.worldinteractions.helpers.PluginConfig;

/**
 * @author t3hk0d3 Jayes
 */
public class BlockListener extends GenericListener {
	PluginConfig config;

	public BlockListener(Plugin plugin, PluginConfig config, PlayerNotifier notifier) {
		super(plugin, config, notifier);

		this.config = config;
	}

	public void callCustomEvent(CustomEvent customEvent, Cancellable bukkitEvent) {
		Bukkit.getServer().getPluginManager().callEvent(customEvent);
		if (customEvent.isCancelled()) {
			bukkitEvent.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onItemEnchant(EnchantItemEvent event) {
		Player player = event.getEnchanter();
		ItemStack enchantedItem = event.getItem();

		if (!doesPlayerHavePermission(player, Permissions.ENCHANT, enchantedItem)) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		ItemStack itemInHand = player.getInventory().getItemInMainHand();

		if (itemInHand != null && itemInHand.getType() != Material.AIR) {
			callCustomEvent(new PlayerBreakBlockWithEvent(player, block, itemInHand), event);
		} else if (itemInHand != null && itemInHand.getType() == Material.AIR) {
			callCustomEvent(new PlayerBreakBlockEvent(player, block), event);
		}

	}

	@EventHandler(priority = EventPriority.LOW)
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		Block blockAgainst = event.getBlockAgainst();

		if (blockAgainst != null) {
			callCustomEvent(new PlayerPlaceBlockAgainstEvent(player, block, blockAgainst), event);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onHangingBreakByEntity(HangingBreakByEntityEvent event) {
		if (event.getRemover() instanceof Player) {
			Player player = (Player) event.getRemover();
			Entity destroyedObject = event.getEntity();
			ItemStack itemInHand = player.getInventory().getItemInMainHand();

			if (config.shouldCheckItemsUse()
				&& config.getLeftClickItemsUse().contains(itemInHand.getType())
				&& !doesPlayerHavePermission(player, Permissions.USE, itemInHand, "on", destroyedObject)) {
				event.setCancelled(true);
				return;
			}

			if (!doesPlayerHavePermission(player, Permissions.BLOCK_BREAK, destroyedObject)) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPaintingPlace(HangingPlaceEvent event) {
		Player player = event.getPlayer();
		Entity placedObject = event.getEntity();

		if (player != null
			&& !doesPlayerHavePermission(player, Permissions.BLOCK_BREAK, placedObject)) {
			event.setCancelled(true);
		}
	}
}
