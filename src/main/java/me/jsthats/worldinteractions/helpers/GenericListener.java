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

package me.jsthats.worldinteractions.helpers;

import me.jsthats.worldinteractions.enums.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author t3hk0d3 Jayes
 */
public abstract class GenericListener implements Listener {

	protected PlayerNotifier notifier;
	protected PluginConfig config;
	protected Plugin plugin;

	public GenericListener(Plugin plugin, PluginConfig config, PlayerNotifier notifier) {
		this.notifier = notifier;
		this.config = config;
		this.plugin = plugin;

		this.registerEvents(plugin);
	}

	protected boolean doesPlayerHavePermission(Player player, Permissions permissions, Object... objectsToDescribe) {
		String basePermission = permissions.getPermission();
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
		List<String> argumentStrings = Arrays.stream(arguments)
			.filter(Objects::nonNull)
			.map(ObjectDescriber::describe)
			.collect(Collectors.toList());
		List<String> permissionNodes = Stream.of(basePermission).collect(Collectors.toList());

		permissionNodes.addAll(argumentStrings);

		return String.join(".", permissionNodes);
	}

	private void registerEvents(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
}
