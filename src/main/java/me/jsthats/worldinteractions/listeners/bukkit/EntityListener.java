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

import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.events.CustomEvent;
import me.jsthats.worldinteractions.events.entity.*;
import me.jsthats.worldinteractions.events.player.PlayerShootBowEvent;
import me.jsthats.worldinteractions.helpers.*;
import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

/**
 * @author t3hk0d3 Jayes
 */
public class EntityListener extends GenericListener {
	PluginConfig config;

	public EntityListener(Plugin plugin, PluginConfig config, PlayerNotifier notifier, ObjectGroups objectGroups) {
		super(plugin, config, notifier, objectGroups);

		this.config = config;
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onEntityTame(EntityTameEvent event) {
		if (event.getOwner() instanceof Player) {
			CustomEvent.call(new PlayerTameEntityEvent(event), event);
		}

	}

	@EventHandler(priority = EventPriority.LOW)
	public void onEntityBreed(EntityBreedEvent event) {
		if (event.getBreeder() instanceof Player) {

			if (CustomEvent.call(new PlayerBreedEntitiesEvent(event), event)) {
				// Reset breeding state of parents
				((Animals) event.getFather()).setLoveModeTicks(0);
				((Animals) event.getMother()).setLoveModeTicks(0);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onEntityShootBow(EntityShootBowEvent event) {
		if (event.getEntity() instanceof Player) {
			CustomEvent.call(new PlayerShootBowEvent(event), event);
		}
	}

	@Deprecated
	@EventHandler(priority = EventPriority.LOW)
	public void onEntityTarget(EntityTargetEvent event) {
/*		if (event.getTarget() instanceof Player) {
			Player player = (Player) event.getTarget();
			Entity targetingEntity = event.getEntity();

			if (!doesPlayerHavePermission(player, Permissions.TARGET_BY, targetingEntity)) {
				event.setCancelled(true);
			}
		}*/
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		Entity entity = event.getRightClicked();
		ItemStack heldItem = player.getInventory().getItemInMainHand();

		if (objectGroups.isInGroup(heldItem.getType(), ObjectGroups.GroupName.SPAWN_EGGS)) {
			//event.setCancelled(true);
			return;
		}

		if (objectGroups.isInGroup(heldItem.getType(), ObjectGroups.GroupName.DYES)
				&& objectGroups.isInGroup(entity, ObjectGroups.GroupName.CAN_DYE_ENTITY)) {
			CustomEvent.call(new PlayerDyeEntityEvent(event), event);
		}

		if (heldItem.getType() == Material.SHEARS
				&& objectGroups.isInGroup(entity, ObjectGroups.GroupName.CAN_SHEAR_ENTITY)) {
			// Shear event
			CustomEvent.call(new PlayerShearEntityEvent(event), event);
		}

		if ((heldItem.getType() == Material.BOWL && entity.getType() == EntityType.MUSHROOM_COW)
				|| (heldItem.getType() == Material.BUCKET && entity.getType() == EntityType.MUSHROOM_COW)
				|| (heldItem.getType() == Material.BUCKET && entity.getType() == EntityType.COW)) {
			CustomEvent.call(new PlayerMilkEntityEvent(event), event);
		}

		if (ObjectUtils.canBeMounted(entity)) {
			// Mount event
		}
		// Feed entity
		// Leash entity
		// Cure entity (zombiefication)

		/*if (config.shouldCheckItemsUse()
			&& config.getRightClickItemsUse().contains(heldItem.getType())
			&& !doesPlayerHavePermission(player, Permissions.USE, heldItem, "on", entity)) {
			event.setCancelled(true);
			return;
		}*/

		/*if (!doesPlayerHavePermission(player, Permissions.INTERACT, clickedEntity)) {
			event.setCancelled(true);
		}*/
	}
}
