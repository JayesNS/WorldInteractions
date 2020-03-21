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
import me.jsthats.worldinteractions.events.items.PlayerEnchantItemWithEvent;
import me.jsthats.worldinteractions.helpers.GenericListener;
import me.jsthats.worldinteractions.helpers.PlayerNotifier;
import me.jsthats.worldinteractions.helpers.PluginConfig;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Map;

/**
 * @author Jayes
 */
public class ItemListener extends GenericListener {
	PluginConfig config;

	public ItemListener(Plugin plugin, PluginConfig config, PlayerNotifier notifier) {
		super(plugin, config, notifier);

		this.config = config;
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onItemEnchant(EnchantItemEvent event) {
		Player player = event.getEnchanter();
		ItemStack item = event.getItem();
		Map<Enchantment, Integer> enchantments = event.getEnchantsToAdd();

		CustomEvent.callCustomEvent(new PlayerEnchantItemWithEvent(player, item, enchantments), event);
	}

}
