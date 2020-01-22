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

import org.bukkit.entity.*;

import java.util.Arrays;
import java.util.List;
import java.util.MissingFormatArgumentException;

/**
 * @author t3hk0d3 Jayes
 */
public class PlayerNotifier {

	PluginConfig config;

	public PlayerNotifier(PluginConfig config) {
		this.config = config;
	}

	// TODO: Refactor needed
	public void informPlayer(Player player, String permission, Object... args) {
		if (!config.shouldInformPlayer()) {
			return;
		}

		String message = config.getDefaultMessage();
        List<String> permissionNodes = Arrays.asList(permission.split("\\."));

        int index = permissionNodes.size();
        while (index > 0) {
            String messageKey = String.join(".", permissionNodes
					.subList(0, index--));
            if (config.getMessages().getString(messageKey) != null) {
                message = config.getMessages().getString(messageKey);
                break;
            }
        }

        String[] objects = Arrays.stream(args)
			.filter(object -> !(object instanceof String))
			.map(object -> ObjectDescriber.describe(object, true)).toArray(String[]::new);

		String filledMessage = config.getDefaultMessage();
        try {
			filledMessage = String.format(message, objects);
		} catch (MissingFormatArgumentException e) {}

		player.sendMessage(config.getMessagePrefix().concat(filledMessage).replaceAll("&([a-z0-9])", "\u00A7$1"));
	}
}
