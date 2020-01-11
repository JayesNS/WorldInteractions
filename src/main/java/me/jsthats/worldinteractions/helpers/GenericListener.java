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
 * @author Jayes t3hk0d3
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
