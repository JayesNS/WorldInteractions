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
import me.jsthats.worldinteractions.helpers.ObjectGroups;
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
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import me.jsthats.worldinteractions.helpers.PluginConfig;

/**
 * @author t3hk0d3 Jayes
 */
public class BlockListener extends GenericListener {
	PluginConfig config;

	public BlockListener(Plugin plugin, PluginConfig config, PlayerNotifier notifier, ObjectGroups objectGroups) {
		super(plugin, config, notifier, objectGroups);

		this.config = config;
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		ItemStack itemInHand = player.getInventory().getItemInMainHand();

		if (itemInHand == null) {
			return;
		}

		CustomEvent.call(new PlayerBreakBlockWithItemEvent(event), event);
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onBlockPlace(BlockPlaceEvent event) {
		Block block = event.getBlock();
		Block blockAgainst = event.getBlockAgainst();

		if (blockAgainst == null) {
			return;
		}

		if (block.getType() == Material.FARMLAND || block.getType() == Material.GRASS_PATH) {
			CustomEvent.call(new PlayerPlaceBlockUsingItemEvent(event), event);
		} else {
			CustomEvent.call(new PlayerPlaceBlockAgainstBlockEvent(event), event);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onHangingBreakByEntity(HangingBreakByEntityEvent event) {
		if (event.getRemover() instanceof Player) {
			CustomEvent.call(new PlayerBreakHangingWithItemEvent(event), event);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onHangingPlace(HangingPlaceEvent event) {
		Player player = event.getPlayer();

		if (player == null) {
			return;
		}

		CustomEvent.call(new PlayerPlaceHangingAgainstBlockEvent(event), event);
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
		CustomEvent.call(new PlayerEmptyBucketEvent(event), event);
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerBucketFill(PlayerBucketFillEvent event) {
		CustomEvent.call(new PlayerFillBucketEvent(event), event);
	}
}
