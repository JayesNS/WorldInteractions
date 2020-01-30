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

package me.jsthats.worldinteractions.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.*;

/**
 * @author t3hk0d3 Jayes
 */
public enum EntityCategory {
	PLAYER("player", Player.class),
	DROP("drop", Item.class, ExperienceOrb.class),
	FISH("fish", Fish.class),
	ANIMAL("animal", Animals.class, Squid.class),
	MONSTER("monster", Monster.class, Slime.class, EnderDragon.class, Ghast.class),
	PROJECTILE("projectile", Projectile.class);
	
	private String name;
	private Class<? extends Entity>[] classes;
	
	private final static Map<Class<? extends Entity>, EntityCategory> map = new HashMap<>();
	
	static {
		Arrays.stream(EntityCategory.values())
			.forEach(category -> Arrays.stream(category.getClasses())
				.forEach(categoryClass -> map.put(categoryClass, category)));
	}
	
	@SafeVarargs
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
