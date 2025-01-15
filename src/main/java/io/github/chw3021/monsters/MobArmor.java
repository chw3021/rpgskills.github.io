package io.github.chw3021.monsters;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;


public class MobArmor implements Listener {

	@EventHandler
	public void MobDam(EntityDamageEvent d) 
	{
		if(d.getEntity().hasMetadata("treasurepig")) {
			if(d.getCause() != DamageCause.ENTITY_ATTACK && d.getCause() != DamageCause.VOID) {
				d.setCancelled(true);
				return;
			}
			d.setDamage(0.1);
			Entity entity = d.getEntity();
			entity.teleport(entity.getLocation().add(1, 0.5, -1));
		}
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
			if(d.getEntity().hasMetadata("poison")) {
				d.setDamage(d.getDamage()*0.7);
			}
			if(d.getEntity().hasMetadata("wild")) {
				d.setDamage(d.getDamage()*0.6);
			}
			if(d.getEntity().hasMetadata("nether")) {
				d.setDamage(d.getDamage()*0.32);
			} 
			if(d.getEntity().hasMetadata("ender")) {
				d.setDamage(d.getDamage()*0.16);
			}
		}
		if(d.getEntity().hasMetadata("raid")) {
			d.setDamage(d.getDamage()*0.56);
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
			d.setDamage(d.getDamage()*0.8);
		} 
		if(d.getEntity().hasMetadata("ender")) {
			d.setDamage(d.getDamage()*0.5);
		}
		if(d.getEntity().hasMetadata("wither")) {
			LivingEntity le = (LivingEntity) d.getEntity();
			d.setDamage(d.getDamage()*0.2);
			if(d.getDamage() >= le.getAttribute(Attribute.MAX_HEALTH).getValue()*0.15) {
				d.setDamage(le.getAttribute(Attribute.MAX_HEALTH).getValue()*0.15);
			}
		} 
		if(d.getEntity().hasMetadata("sandbag")) {
			d.setDamage(d.getDamage()*0.1);
		}
	}
}
