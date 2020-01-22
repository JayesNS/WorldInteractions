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

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Jayes
 */
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

    @NotNull
    public String getDefaultMessage() {
        String message = this.config.getString("default_message");
        return message != null ? message : "No message";
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
