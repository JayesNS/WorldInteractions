package me.jsthats.worldinteractions.helpers;

import me.jsthats.worldinteractions.enums.EntityCategory;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ComplexEntityPart;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

/**
 * @author Jayes
 */
public abstract class ObjectDescriber {
    public static String describe(Object object) {
        return describe(object, false);
    }

    public static String describe(Object obj, boolean prettify) {
        String name;
        if (obj instanceof Block) {
            name =  describe((Block) obj);
        } else if (obj instanceof ComplexEntityPart) {
            name =  describe((ComplexEntityPart) obj);
        } else if (obj instanceof Item) {
            name =  describe((Item) obj);
        } else if (obj instanceof ItemStack) {
            name =  describe((ItemStack) obj);
        } else if (obj instanceof Entity) {
            name =  describe((Entity) obj, prettify);
        } else if (obj instanceof Material) {
            name =  describe((Material) obj);
        } else if (obj instanceof EntityDamageEvent.DamageCause) {
            name =  describe((EntityDamageEvent.DamageCause) obj);
        } else if (obj instanceof InventoryType) {
            name =  describe((InventoryType) obj);
        } else if (obj instanceof Vehicle) {
            name =  describe((Vehicle) obj);
        } else {
            name =  obj.toString();
        }
        return formatName(name, prettify);
    }

    private static String describe(ComplexEntityPart entityPart) {
        return describe(entityPart.getParent());
    }

    private static String describe(Entity entity) {
        return describe(entity, false);
    }
    private static String describe(Entity entity, boolean onlyName) {
        EntityCategory category = EntityCategory.fromEntity(entity);
        String entityName = entity.getType().name();

        return onlyName || category == null
            ? entityName
            : category.getName() + "." + entityName;
    }

    private static String describe(Item item) {
        return describe(item.getItemStack().getType());
    }

    private static String describe(ItemStack itemStack) {
        return describe(itemStack.getType());
    }

    private static String describe(InventoryType inventoryType) {
        return inventoryType.name();
    }

    private static String describe(EntityDamageEvent.DamageCause damageCause) {
        return damageCause.name();
    }

    private static String describe(Material material) {
        if (MaterialUtils.isSpawnEgg(material)) {
            return material.name().replace("_SPAWN_EGG", "");
        }
        if (material.name().contains("bucket")) {
            return material.name().replace("bucket", "");
        }
        return material.name();
    }

    private static String describe(Vehicle vehicle) {
        return describe((Entity) vehicle);
    }

    private static String describe(Block block) {
        return describe(block.getType());
    }

    private static String formatName(String name, boolean prettify) {
        String lowercaseName = name.toLowerCase();
        return prettify
            ? WordUtils.capitalizeFully(lowercaseName.replaceAll("_", " "))
            : lowercaseName.replaceAll("_" , "");
    }
}
