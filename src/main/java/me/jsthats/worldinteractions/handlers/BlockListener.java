package me.jsthats.worldinteractions.handlers;

import me.jsthats.worldinteractions.helpers.GenericListener;
import me.jsthats.worldinteractions.enums.Permissions;
import me.jsthats.worldinteractions.helpers.PlayerNotifier;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import me.jsthats.worldinteractions.helpers.PluginConfig;

/**
 *
 * @author t3hk0d3 g4rnek
 */
public class BlockListener extends GenericListener {
	PluginConfig config;

	public BlockListener(Plugin plugin, PluginConfig config, PlayerNotifier notifier) {
		super(plugin, config, notifier);

		this.config = config;
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onItemEnchant(EnchantItemEvent event) {
		Player player = event.getEnchanter();
		ItemStack enchantedItem = event.getItem();

		if (!doesPlayerHavePermission(player, Permissions.ENCHANT, enchantedItem)) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block brokenBlock = event.getBlock();
		ItemStack itemInHand = player.getInventory().getItemInMainHand();

		if (config.shouldCheckItemsUse()
			&& config.getLeftClickItemsUse().contains(itemInHand.getType())
			&& !doesPlayerHavePermission(player, Permissions.USE, itemInHand, "on", brokenBlock)) {
			event.setCancelled(true);
			return;
		}

		if (!doesPlayerHavePermission(player, Permissions.BLOCK_BREAK, brokenBlock)) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Block placedBlock = event.getBlock();
		ItemStack itemInHand = player.getInventory().getItemInMainHand();

		if (config.shouldCheckItemsUse()
			&& config.getRightClickItemsUse().contains(itemInHand.getType())
			&& !doesPlayerHavePermission(player, Permissions.USE, itemInHand, "on", event.getBlockAgainst())) {
			event.setCancelled(true);
			return;
		}

		if (!placedBlock.getType().equals(event.getBlockReplacedState().getType())
			&& !doesPlayerHavePermission(player, Permissions.BLOCK_PLACE, placedBlock)) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onHangingBreakByEntity(HangingBreakByEntityEvent event) {
		if (event.getRemover() instanceof Player) {
			Player player = (Player) event.getRemover();
			Entity destroyedObject = event.getEntity();
			ItemStack itemInHand = player.getInventory().getItemInMainHand();

			if (config.shouldCheckItemsUse()
				&& config.getLeftClickItemsUse().contains(itemInHand.getType())
				&& !doesPlayerHavePermission(player, Permissions.USE, itemInHand, "on", destroyedObject)) {
				event.setCancelled(true);
				return;
			}

			if (!doesPlayerHavePermission(player, Permissions.HANGING_BREAK, destroyedObject)) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPaintingPlace(HangingPlaceEvent event) {
		Player player = event.getPlayer();
		Entity placedObject = event.getEntity();

		if (player != null
			&& !doesPlayerHavePermission(player, Permissions.HANGING_PLACE, placedObject)) {
			event.setCancelled(true);
		}
	}
}
