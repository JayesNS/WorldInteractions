package me.jsthats.worldinteractions.helpers;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ObjectGroups {
    private final PluginFile objectGroups;

    public ObjectGroups(PluginFile objectGroups) {
        this.objectGroups = objectGroups;
    }

    public Group getGroup(GroupName groupName) {
        return new Group(groupName.name());
    }

    public boolean isInGroup(Material material, GroupName groupName) {
        return getGroup(groupName).contains(material);
    }

    public boolean isInGroup(Entity entity, GroupName groupName) {
        boolean res = getGroup(groupName).contains(entity);
        return res;
    }

    public enum GroupName {
        DYES, SPAWN_EGGS, WOOD, LOGS, CAN_SHEAR_ENTITY, CAN_DYE_ENTITY
    }

    public class Group {
        @Nullable
        private final List<String> objects;

        public Group(String groupName) {
            if (objectGroups.contains(groupName)) {
                objects = objectGroups.getStringList(groupName);
            } else {
                objects = null;
            }
            return;
        }

        public boolean contains(Material material) {
            return objects.contains(material.name());
        }

        public boolean contains(Entity entity) {
            return objects.contains(entity.getType().name());
        }
    }
}
