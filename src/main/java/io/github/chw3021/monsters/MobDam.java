package io.github.chw3021.monsters;

import org.bukkit.Material;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.potion.PotionEffectType;

import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Pak;


public class MobDam extends Pak implements Listener {


	final private void dammul(LivingEntity le, LivingEntity de, EntityDamageByEntityEvent d) {
		if(!le.isValid()) {
			d.setCancelled(true);
			return;
		}
		if (le.hasMetadata("quest")) {
			d.setDamage(d.getDamage() * 2);
		}
		if (!le.hasMetadata("raid")) {
			if (le.hasMetadata("plain")) {
				mdset(d, le, 1.0, de, 14);
			}
			if (le.hasMetadata("mountains")) {
				mdset(d, le, 1.35, de, 5);
			}
			if (le.hasMetadata("frost")) {
				mdset(d, le, 1.7, de, 6);
			}
			if (le.hasMetadata("ocean")) {
				mdset(d, le, 2.1, de, 7);
			}
			if (le.hasMetadata("dark")) {
				mdset(d, le, 2.5, de, 8);
			}
			if (le.hasMetadata("hyper")) {
				mdset(d, le, 2.9, de, 9);
			}
			if (le.hasMetadata("red")) {
				mdset(d, le, 3.3, de, 10);
			}
			if (le.hasMetadata("poison")) {
				mdset(d, le, 3.8, de, 11);
			}
			if (le.hasMetadata("wild")) {
				mdset(d, le, 4.0, de, 14);
				mdset(d, le, 1.0, de, 5);
				mdset(d, le, 1.0, de, 9);
			}
		}
		else if (le.hasMetadata("raid")) {
			if (le.hasMetadata("plain")) {
				mdset(d, le, 4.5, de, 14);
			}
			if (le.hasMetadata("mountains")) {
				mdset(d, le, 4.5, de, 5);
			}
			if (le.hasMetadata("frost")) {
				mdset(d, le, 4.5, de, 6);
			}
			if (le.hasMetadata("ocean")) {
				mdset(d, le, 4.5, de, 7);
			}
			if (le.hasMetadata("dark")) {
				mdset(d, le, 4.5, de, 8);
			}
			if (le.hasMetadata("hyper")) {
				mdset(d, le, 4.5, de, 9);
			}
			if (le.hasMetadata("red")) {
				mdset(d, le, 4.5, de, 10);
			}
			if (le.hasMetadata("poison")) {
				mdset(d, le, 4.5, de, 11);
			}
			if (le.hasMetadata("wild")) {
				mdset(d, le, 4.5, de, 14);
				mdset(d, le, 1.0, de, 5);
				mdset(d, le, 1.0, de, 9);
			}
		}
		if (le.hasMetadata("summoned")) {
			d.setDamage(d.getDamage() * 0.9);
		}
		if (le.hasMetadata("leader")) {
			d.setDamage(d.getDamage() * 1.5);
		}
		if (le.hasMetadata("nether")) {
			mdset(d, le, 5.13, de, 10);
			mdset(d, le, 1.0, de, 8);
		}
		if (le.hasMetadata("ender")) {
			mdset(d, le, 6.2, de, 6);
			mdset(d, le, 1.0, de, 8);
		}
		if (le.hasMetadata("void")) {
			mdset(d, le, 7.3, de, 5);
			mdset(d, le, 1.0, de, 14);
		}
		if (le.hasMetadata("boss")) {
			d.setDamage(d.getDamage() * 2.5);
		}
	}


	@EventHandler
	public void mobDam(EntityDamageByEntityEvent d) {
		if (d.getDamager() instanceof LivingEntity && d.getEntity() instanceof LivingEntity) {
			final LivingEntity le = (LivingEntity)d.getDamager();
			final LivingEntity de = (LivingEntity)d.getEntity();
			dammul(le,de,d);
			
		}
		if (d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity) {
			final Projectile pr = (Projectile) d.getDamager();
			if (pr.getShooter() instanceof LivingEntity) {
				final LivingEntity le = (LivingEntity) pr.getShooter();
				final LivingEntity de = (LivingEntity)d.getEntity();
				dammul(le,de,d);
			}
		}

	}

	@EventHandler
	public void pDam(EntityDamageByEntityEvent d) {
		if (d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) {
			Player p = (Player) d.getDamager();
			if (p.getCooldown(Material.GLISTERING_MELON_SLICE) <= 0) {
				d.setDamage(d.getDamage() * (1 + Proficiency.getpro(p)*0.25));
			}
			if(!d.getEntity().hasMetadata("raid") && d.getEntity() instanceof Mob && !p.hasMetadata("fake")) {
				Mob m = (Mob) d.getEntity();
				m.setTarget(p);
			}
		}
		if (d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity) {
			Projectile pr = (Projectile) d.getDamager();
			if (pr.getShooter() instanceof Player) {
				Player p = (Player) pr.getShooter();
				if (p.getCooldown(Material.GLISTERING_MELON_SLICE) <= 0) {
					d.setDamage(d.getDamage() * (1 + Proficiency.getpro(p)*0.25));
				}
				if(!d.getEntity().hasMetadata("raid") && d.getEntity() instanceof Mob && !p.hasMetadata("fake")) {
					Mob m = (Mob) d.getEntity();
					m.setTarget(p);
				}
			}
		}
		if (d.getCause() == DamageCause.THORNS && d.getEntity() instanceof Player && (d.getDamager() instanceof Guardian||d.getDamager() instanceof ElderGuardian) ) {
			if(d.getDamager().hasMetadata("fake")) {
				d.setCancelled(true);
			}
			Player p = (Player) d.getEntity();
			if(ClassData.pc.get(p.getUniqueId()) == 19 || p.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
				d.setCancelled(true);
			}
		}

	}

	@EventHandler
	public void PlayerArmor(EntityDamageEvent d) {
		if (d.getEntity() instanceof Player) {
			Player p = (Player) d.getEntity();
			if (Proficiency.getpro(p) >= 2) {
				d.setDamage(d.getDamage() * 0.75);
			}
		}
	}
}
