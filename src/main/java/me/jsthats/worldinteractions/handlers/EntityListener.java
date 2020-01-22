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
import me.jsthats.worldinteractions.helpers.PlayerNotifier;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import me.jsthats.worldinteractions.helpers.PluginConfig;

/**
 * @author t3hk0d3 Jayes
 */
public class EntityListener extends GenericListener {
	PluginConfig config;

	public EntityListener(Plugin plugin, PluginConfig config, PlayerNotifier notifier) {
		super(plugin, config, notifier);

		this.config = config;
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			Entity damagedEntity = event.getEntity();
			ItemStack itemInHand = player.getInventory().getItemInMainHand();

			if (config.shouldCheckItemsUse()
				&& config.getLeftClickItemsUse().contains(itemInHand.getType())
				&& !doesPlayerHavePermission(player, Permissions.USE, itemInHand, "on", damagedEntity)) {
				event.setCancelled(true);
				return; // Stop checking further if player cannot use item
			}
			if (!doesPlayerHavePermission(player, Permissions.DAMAGE_DEAL, damagedEntity)) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onEntityTame(EntityTameEvent event) {
		if (!(event.getOwner() instanceof Player)) {
			return;
		}

		Player player = (Player) event.getOwner();
		Entity tamedEntity = event.getEntity();

		if (!doesPlayerHavePermission(player, Permissions.TAME, tamedEntity)) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onEntityBreed(EntityBreedEvent event) {
		if (event.getBreeder() instanceof Player) {
			Player player = (Player) event.getBreeder();
			LivingEntity animal = event.getEntity();
			Animals yourMum = (Animals) event.getMother();
			Animals yourDad = (Animals) event.getFather();
			if (!doesPlayerHavePermission(player, Permissions.BREED, animal)) {
				event.setCancelled(true);
				yourMum.setLoveModeTicks(0);
				yourDad.setLoveModeTicks(0);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onEntityShootBow(EntityShootBowEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			ItemStack bow = event.getBow();
			Entity projectile = event.getProjectile();

			if (config.shouldCheckItemsUse()
				&& !doesPlayerHavePermission(player, Permissions.USE, bow)) {
				event.setCancelled(true);
				return;
			}

			if (!doesPlayerHavePermission(player, Permissions.SHOOT, bow, projectile)) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onEntityTarget(EntityTargetEvent event) {
		if (event.getTarget() instanceof Player) {
			Player player = (Player) event.getTarget();
			Entity targetingEntity = event.getEntity();

			if (!doesPlayerHavePermission(player, Permissions.TARGET_BY, targetingEntity)) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onEntityPickup(EntityPickupItemEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			ItemStack item = event.getItem().getItemStack();

			if (!doesPlayerHavePermission(player, Permissions.PICKUP, item)) {
				event.setCancelled(true);
			}
		}
	}
}
