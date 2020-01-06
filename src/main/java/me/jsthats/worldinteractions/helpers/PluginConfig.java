package me.jsthats.worldinteractions.helpers;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PluginConfig {
    FileConfiguration config;

    public PluginConfig(FileConfiguration config) {
        this.config = config;
    }

    public boolean isDebugModeEnabled() {
        return this.config.getBoolean("debug");
    }

    public boolean shouldInformPlayer() {
        return this.config.getBoolean("inform_players");
    }

    public boolean shouldDropForbiddenItems() {
        return this.config.getBoolean("drop_forbidden_items");
    }

    public String getDefaultMessage() {
        return this.config.getString("default_message");
    }

    public String getMessagePrefix() {
        return this.config.getString("message_prefix");
    }

    public ConfigurationSection getMessages() {
        return this.config.getConfigurationSection("messages");
    }

    public boolean shouldShowMessageFor(String permission) {
        return !this.config.getStringList("do_not_show_message_for").contains(permission);
    }

    public ConfigurationSection getItemsUse() {
        return this.config.getConfigurationSection("items_use");
    }

    public boolean shouldCheckItemsUse() {
        return getItemsUse().getBoolean("check_items_use");
    }

    public List<Material> getLeftClickItemsUse() {
        return getMaterialList(getItemsUse().getStringList("left_click_items"));
    }

    public List<Material> getRightClickItemsUse() {
        return getMaterialList(getItemsUse().getStringList("right_click_items"));
    }

    List<Material> getMaterialList(List<String> nameList) {
        return nameList.stream()
            .map(Material::valueOf)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }
}
