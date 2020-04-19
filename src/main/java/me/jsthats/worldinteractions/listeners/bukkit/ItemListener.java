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

import me.jsthats.worldinteractions.events.CustomEvent;
import me.jsthats.worldinteractions.events.items.*;
import me.jsthats.worldinteractions.helpers.*;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerRecipeDiscoverEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Map;

/**
 * @author Jayes
 */
public class ItemListener extends GenericListener {
	PluginConfig config;

	public ItemListener(Plugin plugin, PluginConfig config, PlayerNotifier notifier, ObjectGroups objectGroups) {
		super(plugin, config, notifier, objectGroups);

		this.config = config;
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onItemEnchant(EnchantItemEvent event) {
		CustomEvent.call(new PlayerEnchantItemWithEvent(event), event);
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onCraftItem(CraftItemEvent event) {
		ItemStack craftedItem = event.getCurrentItem();

		if (craftedItem == null) {
			return;
		}
		CustomEvent.call(new PlayerCraftItemEvent(event), event);
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerRecipeDiscover(PlayerRecipeDiscoverEvent event) {
		CustomEvent.call(new PlayerDiscoverRecipeEvent(event), event);
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerDropItem(org.bukkit.event.player.PlayerDropItemEvent event) {
		CustomEvent.call(new PlayerDropItemEvent(event), event);
	}

//	@EventHandler(priority = EventPriority.LOW)
//	public void onEntityPickup(EntityPickupItemEvent event) {
//		if (event.getEntity() instanceof Player) {
//			Player player = (Player) event.getEntity();
//			ItemStack item = event.getItem().getItemStack();
//
//			if (player.getInventory().getItemInMainHand().getType() == item.getType()) {
//				CustomEvent.call(new PlayerHoldItemEvent(player, item), event);
//			}
//
//			CustomEvent.call(new PlayerPickupItemEvent(player, item), event);
//		}
//	}

	@EventHandler(priority = EventPriority.LOW)
	public void onItemHeldChange(PlayerItemHeldEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItem(event.getNewSlot());

		if (item == null || item.getType() == Material.AIR) {
			return;
		}

		CustomEvent.call(new PlayerHoldItemEvent(event), event);
	}
}
