package me.jsthats.worldinteractions.helpers;

import org.bukkit.entity.*;

import java.util.*;
import java.util.stream.Collectors;

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
