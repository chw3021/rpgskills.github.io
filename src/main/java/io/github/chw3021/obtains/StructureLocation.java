package io.github.chw3021.obtains;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.util.Vector;

import com.google.common.collect.HashMultimap;

public class StructureLocation {
    private final Object key;
    private final String type;
    private final Location actualLocation;


	public StructureLocation(BlockInventoryHolder chest) {
        this.key = chest;
        this.type = "Location";
        this.actualLocation = chest.getInventory().getLocation();
    }

    public StructureLocation(Entity entity) {
        this.key = entity;
        this.type = "Entity";
        this.actualLocation = entity.getLocation();
        entity.setPersistent(true);
        entity.setInvulnerable(true);
        entity.setGravity(false);
        entity.setVelocity(new Vector(0,0,0));
    }

    public Object getKey() {
        return key;
    }

    public Object getType() {
        return type;
    }
    public Location getActualLocation() {
		return actualLocation;
	}
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StructureLocation that = (StructureLocation) obj;
        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }
    
    public static boolean containsChest(HashMultimap<StructureLocation, String> map, BlockInventoryHolder chest) {
        return map.containsKey(new StructureLocation(chest));
    }

    public static boolean containsEntity(HashMultimap<StructureLocation, String> map, Entity entity) {
        return map.containsKey(new StructureLocation(entity));
    }
}
