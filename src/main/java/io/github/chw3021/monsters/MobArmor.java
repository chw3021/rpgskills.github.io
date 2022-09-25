package io.github.chw3021.monsters;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;


public class MobArmor implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void MobDam(EntityDamageEvent d) 
	{
		if(d.getEntity().hasMetadata("quest")) {
			d.setDamage(d.getDamage()*0.8);
		}
		if(!d.getEntity().hasMetadata("raid")) {
			if(d.getEntity().hasMetadata("mountains")) {
				d.setDamage(d.getDamage()*0.95);
			}
			if(d.getEntity().hasMetadata("frost")) {
				d.setDamage(d.getDamage()*0.93);
			}
			if(d.getEntity().hasMetadata("ocean")) {
				d.setDamage(d.getDamage()*1.1);
			}
			if(d.getEntity().hasMetadata("dark")) {
				d.setDamage(d.getDamage()*0.9);
			}
			if(d.getEntity().hasMetadata("hyper")) {
				d.setDamage(d.getDamage()*0.85);
			}
			if(d.getEntity().hasMetadata("red")) {
				d.setDamage(d.getDamage()*0.8);
			}
			if(d.getEntity().hasMetadata("swamp")) {
				d.setDamage(d.getDamage()*0.75);
			}
			if(d.getEntity().hasMetadata("wild")) {
				d.setDamage(d.getDamage()*0.7);
			}
		}
		if(d.getEntity().hasMetadata("raid")) {
			d.setDamage(d.getDamage()*0.7);
		}
		if(d.getEntity().hasMetadata("leader")) {
			d.setDamage(d.getDamage()*0.35);
		}
		if(d.getEntity().hasMetadata("boss")) {
			d.setDamage(d.getDamage()*0.2);
		}
		if(d.getEntity().hasMetadata("lostleader")) {
			d.setDamage(d.getDamage()*2.2);
		}
		if(d.getEntity().hasMetadata("failed")) {
			d.setDamage(d.getDamage()*2.2);
		}
		if(d.getEntity().hasMetadata("nether")) {
			d.setDamage(d.getDamage()*0.25);
		} 
		if(d.getEntity().hasMetadata("ender")) {
			d.setDamage(d.getDamage()*0.2);
		}
		if(d.getEntity().hasMetadata("void")) {
			d.setDamage(d.getDamage()*0.2);
		}
		if(d.getEntity().hasMetadata("wither")) {
			LivingEntity le = (LivingEntity) d.getEntity();
			d.setDamage(d.getDamage()*0.2);
			if(d.getDamage() >= le.getMaxHealth()*0.15) {
				d.setDamage(le.getMaxHealth()*0.15);
			}
		} 
		if(d.getEntity().hasMetadata("sandbag")) {
			d.setDamage(d.getDamage()*0.1);
		}
	}
}
