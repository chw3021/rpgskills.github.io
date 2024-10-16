package io.github.chw3021.monsters.nether;

import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.rmain.RMain;



public class NetherMobsSpawn extends Mobs implements Listener {

	/**
	 * 
	 */
	private static transient final long serialVersionUID = 6020250166279728279L;
	/**
	 * 
	 */
	
	private static final NetherMobsSpawn instance = new NetherMobsSpawn ();
	public static NetherMobsSpawn getInstance()
	{
		return instance;
	}
	final private LivingEntity Default(LivingEntity le) {
		LivingEntity newmob = Mobspawn(le, trans(le), 45000.0, le.getEquipment().getHelmet(),
				le.getEquipment().getChestplate(), le.getEquipment().getLeggings(),
				le.getEquipment().getBoots(), le.getEquipment().getItemInMainHand(),
				le.getEquipment().getItemInOffHand(), le.getType());
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("unmodified", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("nether", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final public void Spawn(LivingEntity le,Biome b) {
		if (le.getLocation().getWorld().getEnvironment() != Environment.NETHER) {
			return;
		}
		Default(le);
	}
	

}