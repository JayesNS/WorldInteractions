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

package me.jsthats.worldinteractions.listeners.bukkit;

import me.jsthats.worldinteractions.events.*;
import me.jsthats.worldinteractions.events.blocks.*;
import me.jsthats.worldinteractions.helpers.GenericListener;
import me.jsthats.worldinteractions.helpers.PlayerNotifier;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
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

	@EventHandler(priority = EventPriority.LOW)
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		ItemStack itemInHand = player.getInventory().getItemInMainHand();

		if (itemInHand == null) {
			return;
		}

		if (itemInHand.getType() == Material.AIR) {
			CustomEvent.callCustomEvent(new PlayerBreakBlockEvent(player, block), event);
		} else if (itemInHand.getType() != Material.AIR) {
			CustomEvent.callCustomEvent(new PlayerBreakBlockWithItemEvent(player, block, itemInHand), event);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		Block blockAgainst = event.getBlockAgainst();
		ItemStack itemInHand = event.getItemInHand();

		if (blockAgainst == null) {
			return;
		}

		if (blockAgainst.getType() == Material.AIR) {
			CustomEvent.callCustomEvent(new PlayerPlaceBlockEvent(player, block), event);
		} else if (block.getType() == Material.FARMLAND || block.getType() == Material.GRASS_PATH) {
			CustomEvent.callCustomEvent(new PlayerPlaceBlockUsingItemEvent(player, block, itemInHand), event);
		} else if (blockAgainst.getType() != Material.AIR) {
			CustomEvent.callCustomEvent(new PlayerPlaceBlockAgainstBlockEvent(player, block, blockAgainst), event);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onHangingBreakByEntity(HangingBreakByEntityEvent event) {
		if (event.getRemover() instanceof Player) {
			Player player = (Player) event.getRemover();
			Entity hanging = event.getEntity();
			ItemStack itemInHand = player.getInventory().getItemInMainHand();

			if (itemInHand.getType() == Material.AIR) {
				CustomEvent.callCustomEvent(new PlayerBreakHangingEvent(player, hanging), event);
			} else if (itemInHand.getType() != Material.AIR) {
				CustomEvent.callCustomEvent(new PlayerBreakHangingWithItemEvent(player, hanging, itemInHand), event);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onHangingPlace(HangingPlaceEvent event) {
		Player player = event.getPlayer();
		Entity hanging = event.getEntity();
		Block blockAgainst = event.getBlock();

		if (player == null) {
			return;
		}

		if (blockAgainst.getType() == Material.AIR) {
			CustomEvent.callCustomEvent(new PlayerPlaceHangingEvent(player, hanging), event);
		} else if (blockAgainst.getType() != Material.AIR) {
			CustomEvent.callCustomEvent(new PlayerPlaceHangingAgainstBlockEvent(player, hanging, blockAgainst), event);
		}
	}
}
