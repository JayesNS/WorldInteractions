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

import me.jsthats.worldinteractions.helpers.GenericListener;
import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.helpers.PlayerNotifier;
import me.jsthats.worldinteractions.helpers.PluginConfig;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author t3hk0d3 g4rnek
 */
public class VehicleListener extends GenericListener {
	PluginConfig config;

	public VehicleListener(Plugin plugin, PluginConfig config, PlayerNotifier notifier) {
		super(plugin, config, notifier);

		this.config = config;
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onVehicleDamage(VehicleDamageEvent event) {
		if (event.getAttacker() instanceof Player) {
			Player player = (Player) event.getAttacker();
			Vehicle vehicle = event.getVehicle();
			ItemStack itemInHand = player.getInventory().getItemInMainHand();

			if (config.shouldCheckItemsUse()
				&& config.getLeftClickItemsUse().contains(itemInHand.getType())
				&& !doesPlayerHavePermission(player, Permissions.USE, itemInHand, "on", vehicle)) {
				event.setCancelled(true);
				return;
			}
			if (!doesPlayerHavePermission(player, Permissions.TRANSPORT_BREAK, vehicle)) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onVehicleEnter(VehicleEnterEvent event) {
		if (!(event.getEntered() instanceof Player)) {
			return;
		}

		Player player = (Player) event.getEntered();
		if (!doesPlayerHavePermission(player, Permissions.TRANSPORT_ENTER, event.getVehicle())) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onVehicleEntityCollision(VehicleEntityCollisionEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			Vehicle vehicle = event.getVehicle();
			if (vehicle != null
				&& !doesPlayerHavePermission(player, Permissions.TRANSPORT_COLLIDE, vehicle)
			) {
				event.setCancelled(true);
				event.setCollisionCancelled(true);
				event.setPickupCancelled(true);
			}
		}

	}
}