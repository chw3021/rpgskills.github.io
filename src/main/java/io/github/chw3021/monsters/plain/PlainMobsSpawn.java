package io.github.chw3021.monsters.plain;

import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.rmain.RMain;



public class PlainMobsSpawn extends Mobs implements Listener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -781915509020560604L;
	private static final PlainMobsSpawn instance = new PlainMobsSpawn ();
	public static PlainMobsSpawn getInstance()
	{
		return instance;
	}
	final private LivingEntity Default(LivingEntity le) {
		LivingEntity newmob = Mobspawn(le, trans(le), le.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue(),
				le.getEquipment().getHelmet(), le.getEquipment().getChestplate(),
				le.getEquipment().getLeggings(), le.getEquipment().getBoots(),
				le.getEquipment().getItemInMainHand(), le.getEquipment().getItemInOffHand(),
				le.getType());
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("unmodified", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("plain", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	
	final public void Spawn(LivingEntity le,Biome b) {
		if (!((b.name().contains("PLAINS")
				|| b.name().contains("RIVER")
				|| b.name().contains("BEACH"))&& !b.name().contains("SNOWY")&& !b.name().contains("FROZEN")
				&& le.getLocation().getWorld().getEnvironment() == Environment.NORMAL)) {
			return;
		}
		Default(le);
	}
	

}
