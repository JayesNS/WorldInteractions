package me.jsthats.worldinteractions.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.*;

/**
 * @author Jayes t3hk0d3
 */
public enum EntityCategory {
	PLAYER("player", Player.class),
	DROP("drop", Item.class, ExperienceOrb.class),
	FISH("fish", Fish.class),
	ANIMAL("animal", Animals.class, Squid.class),
	MONSTER("monster", Monster.class, Slime.class, EnderDragon.class, Ghast.class),
	PROJECTILE("projectile", Projectile.class);
	
	private String name;
	private Class<? extends Entity> classes[];
	
	private final static Map<Class<? extends Entity>, EntityCategory> map = new HashMap<Class<? extends Entity>, EntityCategory>();
	
	static {
		Arrays.asList(EntityCategory.values()).stream()
			.forEach(category -> Arrays.asList(category.getClasses()).stream()
				.forEach(categoryClass -> map.put(categoryClass, category)));
	}
	
	EntityCategory(String name, Class<? extends Entity>... classes) {
		this.name = name;
		this.classes = classes;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Class<? extends Entity>[] getClasses() {
		return this.classes;
	}

	public static boolean isCategory(Entity entity, EntityCategory category) {
		if (fromEntity(entity) == null) {
			return false;
		}
		return fromEntity(entity).getName().equals(category.getName());
	}
	
	public static EntityCategory fromEntity(Entity entity) {
		for (Class<? extends Entity> entityClass : map.keySet()) {
			if (entityClass.isAssignableFrom(entity.getClass())) {
				return map.get(entityClass);
			}
		}
		return null;
	}
	
}
