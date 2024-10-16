package io.github.chw3021.monsters.raids;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;


import org.bukkit.event.entity.EntityTargetEvent;

public class BloodNight {
	
	private static final BloodNight instance = new BloodNight ();
	public static BloodNight getInstance()
	{
		return instance;
	}

	public void SummonedTarget(EntityTargetEvent d) 
	{	
		
		if(d.getEntity() instanceof LivingEntity) {
			LivingEntity le = (LivingEntity) d.getEntity();
			if(le.getWorld().getTime()>=12000 && le.getWorld().getTime()<=24000) {
				Bukkit.getPluginManager().callEvent(new CreatureSpawnEvent((LivingEntity) d.getEntity(), SpawnReason.NATURAL));
			}
		}
		
	}
}
