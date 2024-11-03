package io.github.chw3021.monsters.plain;

import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.entity.LivingEntity;
import org.bukkit.metadata.FixedMetadataValue;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.rmain.RMain;



public class PlainMobsSpawn extends Mobs {

	private static final PlainMobsSpawn instance = new PlainMobsSpawn ();
	public static PlainMobsSpawn getInstance()
	{
		return instance;
	}
	final private LivingEntity Default(LivingEntity le) {
		LivingEntity newmob = Mobspawn(le, trans(le), le.getAttribute(Attribute.MAX_HEALTH).getValue(),
				le.getEquipment().getHelmet(), le.getEquipment().getChestplate(),
				le.getEquipment().getLeggings(), le.getEquipment().getBoots(),
				le.getEquipment().getItemInMainHand(), le.getEquipment().getItemInOffHand(),
				le.getType());
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), "plain"));
		newmob.setMetadata("unmodified", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("plain", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	
	final public void Spawn(LivingEntity le, Biome b) {
		if ((b.toString().contains("PLAINS") || b.toString().contains("RIVER")|| b.toString().contains("MEADOW")
				|| b.toString().contains("BEACH")) && !b.toString().contains("SNOWY") && !b.toString().contains("FROZEN")
				&& le.getLocation().getWorld().getEnvironment() != Environment.NORMAL) {
			Default(le);
		}
	}
	

}