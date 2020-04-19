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
import me.jsthats.worldinteractions.events.vehicles.*;
import me.jsthats.worldinteractions.helpers.*;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

/**
 * @author t3hk0d3 Jayes
 */
public class VehicleListener extends GenericListener {
	PluginConfig config;

	public VehicleListener(Plugin plugin, PluginConfig config, PlayerNotifier notifier, ObjectGroups objectGroups) {
		super(plugin, config, notifier, objectGroups);

		this.config = config;
	}

	// FIXME: PlayerBreakVehicleWithItem calls both PlayerBreakVehicleWithItem and PlayerBreakVehicle
    @EventHandler(priority = EventPriority.LOW)
	public void onVehicleDamage(VehicleDamageEvent event) {
		if (event.getAttacker() instanceof Player) {
			Player player = (Player) event.getAttacker();
			Vehicle vehicle = event.getVehicle();
			ItemStack itemInHand = player.getInventory().getItemInMainHand();

			if (player == null) {
				return;
			}

			if (itemInHand.getType() != Material.AIR) {
				CustomEvent.call(new PlayerBreakVehicleWithItemEvent(player, vehicle, itemInHand), event);
			}/* else if (itemInHand.getType() == Material.AIR) {
				CustomEvent.callCustomEvent(new PlayerBreakVehicleEvent(player, vehicle), event);
			}*/
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onVehicleEnter(VehicleEnterEvent event) {
		if (!(event.getEntered() instanceof Player)) {
			return;
		}

		Player player = (Player) event.getEntered();
		Vehicle vehicle = event.getVehicle();

		// FIXME: Entering boat triggers event twice
		if (ObjectUtils.isVehicle(vehicle)) {
			CustomEvent.call(new PlayerEnterVehicleEvent(player, vehicle), event);
		}
	}

    @EventHandler(priority = EventPriority.LOW)
	public void onVehicleEntityCollision(VehicleEntityCollisionEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();
		Vehicle vehicle = event.getVehicle();

		CustomEvent.call(new PlayerPushVehicleEvent(player, vehicle), event);
	}

	// FIXME: Unable to get wood type of placed boat
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack itemInHand = event.getItem();

		if (itemInHand == null) {
			return;
		}

		if (event.getBlockFace() == BlockFace.UP
			&& event.getAction() == Action.RIGHT_CLICK_BLOCK
			&& ObjectUtils.isVehicle(itemInHand.getType())) {
			CustomEvent.call(new PlayerPlaceVehicleEvent(player, itemInHand), event);
		}
	}
}