package io.github.chw3021.obtains;

import java.io.Serializable;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import com.google.common.collect.HashMultimap;

public class StructureLocation implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4423090718556522183L;
	private final Object key;
    private final String type;
    private final Location actualLocation;


	public StructureLocation(Location chest) {
        this.key = chest;
        this.type = "Location";
        this.actualLocation = chest;
    }

    public StructureLocation(Entity entity) {
        this.key = entity.getUniqueId();
        this.type = "Entity";
        this.actualLocation = entity.getLocation();
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
    
    @Override
	public String toString() {
		return "StructureLocation [actualLocation=" + actualLocation + "]";
	}

	public static boolean containsChest(HashMultimap<StructureLocation, String> map, Location chest) {
		chest.setZ(chest.getZ()+1);
        return map.containsKey(new StructureLocation(chest));
    }

    public static boolean containsEntity(HashMultimap<StructureLocation, String> map, Entity entity) {
        return map.containsKey(new StructureLocation(entity));
    }
}
