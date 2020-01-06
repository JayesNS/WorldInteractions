/*
 * WorldInteractions - permission ruleset plugin for Spigot server
 * Copyright (C) 2019 Jayes http://www.jsthats.me
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package me.jsthats.worldinteractions.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.*;

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
	
	public static EntityCategory fromEntity(Entity entity) {
		for (Class<? extends Entity> entityClass : map.keySet()) {
			if (entityClass.isAssignableFrom(entity.getClass())) {
				return map.get(entityClass);
			}
		}
		
		return null;
	}
	
}
