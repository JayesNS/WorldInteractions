/*
 * WorldInteractions - Permission ruleset plugin for Spigot
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
package me.jsthats.worldinteractions.helpers;

import me.jsthats.worldinteractions.enums.EntityCategory;
import me.jsthats.worldinteractions.enums.Permissions;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author jayes
 */
public abstract class GenericListener implements Listener {

	protected PlayerNotifier notifier;
	protected PluginConfig config;
	protected final String BASE_PERMISSION = "worldinteractions";
	protected Plugin plugin;

	public GenericListener(Plugin plugin, PluginConfig config, PlayerNotifier notifier) {
		this.notifier = notifier;
		this.config = config;
		this.plugin = plugin;

		this.registerEvents(plugin);
	}

	protected boolean doesPlayerHavePermission(Player player, Permissions permissions, Object... objectsToDescribe) {
		String basePermission = BASE_PERMISSION + "." + permissions.getPermission();
		String permission = buildPermission(basePermission, objectsToDescribe);
		boolean isAllowed = player.hasPermission(permission);
		boolean shouldNotify = config.shouldShowMessageFor(basePermission);

		if (shouldNotify && !isAllowed) {
			this.notifier.informPlayer(player, permission, objectsToDescribe);
		}
		if (shouldNotify && config.isDebugModeEnabled()) {
			plugin.getLogger().info(String.format( "%s is %s for %s", permission, isAllowed ? "allowed" : "forbidden", player.getName()));
		}
		return isAllowed;
	}

	private String buildPermission(String basePermission, Object... arguments) {
		List<String> argumentStrings = Arrays.asList(arguments).stream()
			.filter(Objects::nonNull)
			.map(argument -> ObjectDescriber.describe(argument))
			.collect(Collectors.toList());
		List<String> permissionNodes = Stream.of(basePermission).collect(Collectors.toList());

		if (argumentStrings != null) {
			permissionNodes.addAll(argumentStrings);
		}

		return String.join(".", permissionNodes);
	}

	private void registerEvents(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
}
