package io.github.chw3021.items.armors;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.chw3021.classes.ClassData;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.party.Party;
import io.github.chw3021.rmain.RMain;


public class ArmorSetEffects implements Listener{

	private HashMap<UUID, Long> stuncool = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> invcool = new HashMap<UUID, Long>();

	@EventHandler
	public void EarthSet(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled() && d.getDamage()>0) {
			Player p = (Player) d.getDamager();
			if(Party.hasParty(p))	{
				if(Party.getMembers(Party.getParty(p)).anyMatch(pu -> ArmorSet.setnum(Bukkit.getPlayer(pu)) == 5)) {
					d.setDamage(d.getDamage()*1.15);
				}
			}
			if(ArmorSet.setnum(p) == 14) {
				if(!Party.hasParty(p))	{
					d.setDamage(d.getDamage()*1.15);
				}
			}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity && !d.isCancelled()) {
			Projectile pr = (Projectile) d.getDamager();
			if(pr.getShooter() instanceof Player) {
				Player p = (Player) pr.getShooter();
				if(Party.hasParty(p))	{
					if(Party.getMembers(Party.getParty(p)).anyMatch(pu -> ArmorSet.setnum(Bukkit.getPlayer(pu)) == 5)) {
						d.setDamage(d.getDamage()*1.15);
					}
				}
				if(ArmorSet.setnum(p) == 14) {
					if(!Party.hasParty(p))	{
						d.setDamage(d.getDamage()*1.15);
					}
				}
			}
		}
	}
	@EventHandler
	public void FrostSet(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled() && d.getDamage()>0) {
			Player p = (Player) d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			if(ArmorSet.setnum(p) == 6) {

                if(45+le.getFreezeTicks()>le.getMaxFreezeTicks()) {
                	le.setFreezeTicks(le.getMaxFreezeTicks());
                }
                else {
	                le.setFreezeTicks(45+le.getFreezeTicks());
                }
                
				if(stuncool.containsKey(le.getUniqueId())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (stuncool.get(le.getUniqueId())/1000 + 5*(1-p.getAttribute(Attribute.LUCK).getValue()/1024)) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                }
	                else // if timer is done
	                {
	                	stuncool.remove(le.getUniqueId()); // removing player from HashMap
	                	p.playSound(p.getLocation(), Sound.BLOCK_DRIPSTONE_BLOCK_PLACE, 1f, 0f);
	            		Holding.holding(p, le, (22l));
	                	stuncool.put(le.getUniqueId(), System.currentTimeMillis()); 
                	}
	                	
	            }
	            else // if cooldown doesn't have players name in it
	            {
                	p.playSound(p.getLocation(), Sound.BLOCK_DRIPSTONE_BLOCK_PLACE, 1f, 0f);
            		Holding.holding(p, le, (22l));
                	stuncool.put(le.getUniqueId(), System.currentTimeMillis()); 
	            }
            }
			
		}
		
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity && !d.isCancelled()) {
			Projectile pr = (Projectile) d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			if(pr.getShooter() instanceof Player) {
				Player p = (Player) pr.getShooter();
				if(ArmorSet.setnum(p) == 6) {

	                if(45+le.getFreezeTicks()>le.getMaxFreezeTicks()) {
	                	le.setFreezeTicks(le.getMaxFreezeTicks());
	                }
	                else {
		                le.setFreezeTicks(45+le.getFreezeTicks());
	                }
	                
					
					if(stuncool.containsKey(le.getUniqueId())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (stuncool.get(le.getUniqueId())/1000 + 5*(1-p.getAttribute(Attribute.LUCK).getValue()/1024)) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                }
		                else // if timer is done
		                {
		                	stuncool.remove(le.getUniqueId()); // removing player from HashMap
		                	p.playSound(p.getLocation(), Sound.BLOCK_DRIPSTONE_BLOCK_PLACE, 1f, 0f);
		            		Holding.holding(p, le, (22l));
		                	stuncool.put(le.getUniqueId(), System.currentTimeMillis()); 
	                	}
		                	
		                }
			            else // if cooldown doesn't have players name in it
			            {
		                	p.playSound(p.getLocation(), Sound.BLOCK_DRIPSTONE_BLOCK_PLACE, 1f, 0f);
		            		Holding.holding(p, le, (22l));
		                	stuncool.put(le.getUniqueId(), System.currentTimeMillis()); 
			            }
	            }
				
			}
		}
	}		
	
	@EventHandler
	public void OceanSet(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) {
			Player p = (Player) d.getDamager();
			if(ArmorSet.setnum(p) == 7) {
				if(ClassData.pc.get(p.getUniqueId()) == 19 || ClassData.pc.get(p.getUniqueId()) == 20 ||ClassData.pc.get(p.getUniqueId()) == 21 ||ClassData.pc.get(p.getUniqueId()) == 22) {

					d.setDamage(d.getDamage()*1.35);
				}
			}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity && !d.isCancelled()) {
			Projectile pr = (Projectile) d.getDamager();
			if(pr.getShooter() instanceof Player) {
				Player p = (Player) pr.getShooter();
				if(ArmorSet.setnum(p) == 7) {
					if(ClassData.pc.get(p.getUniqueId()) == 19 || ClassData.pc.get(p.getUniqueId()) == 20 ||ClassData.pc.get(p.getUniqueId()) == 21 ||ClassData.pc.get(p.getUniqueId()) == 22) {

						d.setDamage(d.getDamage()*1.35);
					}
				}
			}
		}
	}		

	@EventHandler
	public void Armor(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager().hasMetadata("fake") || d.getDamager()==d.getEntity()) {
			return;
		}
		
		if(d.getEntity() instanceof Player && !d.isCancelled()) {
			Player p = (Player) d.getEntity();
			if(ArmorSet.setnum(p) == 8) {
				Random ran = new Random();
				if(ran.nextDouble()<=0.05) {
					d.setCancelled(true);
					Holding.invur(p, 40l);
                    p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 1, 0);
					p.getWorld().spawnParticle(Particle.FALLING_OBSIDIAN_TEAR, p.getLocation(), 10,1,1,1);
				}
			}
			if(ArmorSet.setnum(p) == 11) {
				if(d.getDamager() instanceof Projectile) {
					d.setDamage(d.getDamage()*0.5);
				}
				if(d.getDamager().getLocation().distance(p.getLocation())>12) {
					d.setDamage(d.getDamage()*0.5);
				}
			}
		}
	}	

	@EventHandler
	public void DarkAndPoisonSet(EntityPotionEffectEvent d) 
	{
		if(!(d.getEntity() instanceof Player)) {return;
		}
		
		else if(d.getEntity() instanceof Player) {
			Player p = (Player)d.getEntity();
			if(ArmorSet.setnum(p) == 8) 
			{
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
		        		if(p.hasPotionEffect(PotionEffectType.BLINDNESS))
		        		{
		        			p.removePotionEffect(PotionEffectType.BLINDNESS);
		        		}
		        		if(p.hasPotionEffect(PotionEffectType.DARKNESS))
		        		{
		        			p.removePotionEffect(PotionEffectType.DARKNESS);
		        		}
	                }
				}, 10); 
			}
			if(ArmorSet.setnum(p) == 11) 
			{
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
		        		if(p.hasPotionEffect(PotionEffectType.POISON))
		        		{
		        			p.removePotionEffect(PotionEffectType.POISON);
		        		}
	                }
				}, 10); 
			}
		
		}
	
	}


	@EventHandler
	public void Critical(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && !d.isCancelled()) {
			Player p = (Player) d.getDamager();
			if(ArmorSet.setnum(p) == 11) {
				Random ran = new Random();
				if(ran.nextDouble()<=0.2) {
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1, 2);
                    d.setDamage(d.getDamage()*1.5);
				}
			}
		}
	}	
	
	
	
}
