package io.github.chw3021.monsters.red;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Stray;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpellCastEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.OverworldRaids;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.chat.ComponentBuilder;



public class RedSkills extends Summoned{

	/**
	 * 
	 */
	private transient static final long serialVersionUID = -1890425414216855902L;
	Holding hold = Holding.getInstance();
	private HashMap<UUID, Long> rb1cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb2cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb5cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb6cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb8cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> chcooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Integer> redcharge = new HashMap<UUID, Integer>();
	
	private HashMap<UUID, Boolean> ordealable = new HashMap<UUID, Boolean>();
	private HashMap<UUID, Boolean> dazable = new HashMap<UUID, Boolean>();
	private HashMap<UUID, Boolean> backable = new HashMap<UUID, Boolean>();
	private HashMap<UUID, Boolean> stab = new HashMap<UUID, Boolean>();
	private HashMap<UUID, Boolean> chargable = new HashMap<UUID, Boolean>();
	
	private static HashMap<UUID, Integer> fbt = new HashMap<UUID, Integer>();
	
	private static final RedSkills instance = new RedSkills ();
	public static RedSkills getInstance()
	{
		return instance;
	}

	public void Fire(EntityDamageByEntityEvent d) 
	{
		if((d.getDamager() instanceof LivingEntity) && d.getEntity() instanceof LivingEntity &&d.getDamager().hasMetadata("red")&& !d.isCancelled()) 
		{
			d.getEntity().setFireTicks(d.getEntity().getFireTicks()+60);
		}
	}
	
	
	
	public void HotCreep(ExplosionPrimeEvent d) 
	{
		Entity cc = d.getEntity();
		
		if(cc instanceof Creeper) {
			Creeper p = (Creeper)cc;
			if(p.hasMetadata("red")) {
				for(int i = 0 ; i < 10; i++) {
	        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {

		    				p.getWorld().spawnParticle(Particle.FLAME, p.getLocation(), 100,3,2,3,0);
		    				for(Entity e : p.getNearbyEntities(3, 2, 3)) {
								if(p!=e && e instanceof Player) {
									LivingEntity le = (LivingEntity)e;
									le.damage(2.5,p);
								}
		    				}
		                }
		            }, i*3); 
				}
			}
		}
	}


	public void HeadThrow(EntityTargetEvent d) 
	{
		if((d.getTarget() instanceof LivingEntity) && d.getEntity() instanceof Husk && d.getEntity().hasMetadata("headthrower") && !d.isCancelled()) 
		{
			Husk p = (Husk)d.getEntity();
			LivingEntity pe = (LivingEntity)d.getTarget();
            if(fbt.containsKey(p.getUniqueId())) {
            	Bukkit.getScheduler().cancelTask(fbt.get(p.getUniqueId()));
            }
			int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
                	if(!pe.isValid() || pe.isDead() || !pe.getWorld().equals(p.getWorld()) || pe.getLocation().distance(p.getLocation()) >20) {
            			if(fbt.containsKey(d.getEntity().getUniqueId())) {
            				Bukkit.getScheduler().cancelTask(fbt.get(d.getEntity().getUniqueId()));
            				fbt.remove(d.getEntity().getUniqueId());
            			}
                	}
                	if(p.isValid() && p.hasAI()) {
    	    			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_HUSK_CONVERTED_TO_ZOMBIE, 1, 2);
    	                Snowball fb = (Snowball) p.launchProjectile(Snowball.class);
    	                fb.setItem(p.getEquipment().getHelmet());
    	                fb.setShooter(p);
    	                try {
        	                fb.setVelocity(pe.getEyeLocation().toVector().subtract(p.getEyeLocation().toVector()).normalize().multiply(0.8));
    	                }
    	                catch(IllegalArgumentException e) {
        	                fb.setVelocity(pe.getEyeLocation().getDirection().clone().normalize().multiply(0.8));
    	                }
    	    			fb.setMetadata("headthrow", new FixedMetadataValue(RMain.getInstance(), true));
                	}
                }
			}, 0, 40);
			fbt.put(p.getUniqueId(), task);
		}
	}


	public void HeadThrow(ProjectileHitEvent ev) 
	{

		if(ev.getEntity() instanceof Snowball) {
			Snowball fb = (Snowball)ev.getEntity();
			if(fb.getShooter() instanceof Husk) {
				Husk p = (Husk) fb.getShooter();
				if(fb.hasMetadata("headthrow")) {
					fb.getWorld().spawnParticle(Particle.FALLING_DUST, fb.getLocation(), 100,1,1,1, fb.getItem().getType().createBlockData());
					p.getWorld().playSound(fb.getLocation(), Sound.BLOCK_SAND_BREAK, 1, 1);
					p.getWorld().playSound(fb.getLocation(), Sound.BLOCK_SAND_BREAK, 1, 2);
					for(Entity e : fb.getNearbyEntities(1.3, 1.3, 1.3)) {
						if(p!=e && e instanceof Player) {
							LivingEntity le = (LivingEntity)e;
							le.damage(2,p);
						}
					}
				}
			}
			
		}
	}
	public void Ring(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 5;
		if((d.getDamager() instanceof Skeleton||d.getDamager() instanceof Stray) && d.getEntity() instanceof LivingEntity&& !d.isCancelled() &&d.getDamager().hasMetadata("redboss")) 
		{
			LivingEntity p = (LivingEntity)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			if(rb6cooldown.containsKey(p.getUniqueId())) {
				return;
			}
			
			final Location tl = le.getLocation().clone();
						if(rb1cooldown.containsKey(p.getUniqueId()))
				        {
				            long timer = (rb1cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
				            if(!(timer < 0))
				            {
				            }
				            else 
				            {
				                rb1cooldown.remove(p.getUniqueId()); // removing player from HashMap
				                p.getWorld().strikeLightningEffect(le.getLocation());
								for(int i = 0; i <10; i++) {
				                    AtomicInteger j = new AtomicInteger();	
				                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
							            	ArrayList<Location> ring = new ArrayList<Location>();
											p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
						                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/90) {
						                    	Location one = tl.clone();
						                    	one.setDirection(one.getDirection().rotateAroundY(angle));
						                    	one.add(one.getDirection().normalize().multiply(3.5));
						                    	ring.add(one);
						                	} 
						                	ring.forEach(l -> {
						                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									                @Override
									                public void run() {
														p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 6, 0.5,0.5,0.5,0);		
									                }
									            }, j.incrementAndGet()/60); 
						                		
						                	});
						                }
						            }, i*5); 	                    	
			                    }
								for(int i = 0; i <10; i++) {
				                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											for(Entity e : tl.getWorld().getNearbyEntities(tl,3.5, 3.5, 3.5)) {
												if(p!=e && e instanceof Player) {
													LivingEntity le = (LivingEntity)e;
													le.damage(1,p);	
													if(le.getLocation().distance(tl)>=3.4) {
														le.teleport(le.getLocation());
													}	
												}
											}
						                }
						            }, i*5); 	                    	
			                    }
					            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				            }
				        }
				        else 
				        {
				        	p.getWorld().strikeLightningEffect(le.getLocation());
							for(int i = 0; i <10; i++) {
			                    AtomicInteger j = new AtomicInteger();	
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
						            	ArrayList<Location> ring = new ArrayList<Location>();
										p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
					                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/90) {
					                    	Location one = tl.clone();
					                    	one.setDirection(one.getDirection().rotateAroundY(angle));
					                    	one.add(one.getDirection().normalize().multiply(3.5));
					                    	ring.add(one);
					                	} 
					                	ring.forEach(l -> {
					                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
													p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 6, 0.5,0.5,0.5,0);				                    
								                }
								            }, j.incrementAndGet()/60); 
					                		
					                	});
					                }
					            }, i*5); 	                    	
		                    }
							for(int i = 0; i <10; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										for(Entity e : tl.getWorld().getNearbyEntities(tl,3.5, 3.5, 3.5)) {
											if(p!=e && e instanceof Player) {
												LivingEntity le = (LivingEntity)e;
												le.damage(1,p);	
												if(le.getLocation().distance(tl)>=3.4) {
													le.teleport(le.getLocation());
												}	
											}
										}
					                }
					            }, i*5); 	                    	
		                    }
				            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        }
		}					
	}
	
	
	public void Jump(EntityTargetEvent ev) 
	{
		if((ev.getEntity() instanceof Skeleton) && ev.getEntity().hasMetadata("jumper") && ev.getEntity().hasMetadata("red")  && ev.getTarget() instanceof LivingEntity) 
		{

			final Skeleton p = (Skeleton)ev.getEntity();
			final LivingEntity pe = (LivingEntity) ev.getTarget();
			
			final Location pl = p.getEyeLocation().clone();
			final Location pel = pe.getEyeLocation().clone();
			
			p.teleport(pl.setDirection(pel.toVector().subtract(pl.clone().toVector()).normalize()));

			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_HORSE_JUMP_WATER, 1f, 1.5f);
			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_STEP, 1f, 2f);
			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 4,15,false,false));
			p.setVelocity(pel.toVector().subtract(pl.clone().toVector()).normalize().multiply(0.8+pl.distance(pel)*0.07));
		}
	}
	
	final private void Charge(Skeleton p, Location tl) {

		final Location pl = p.getEyeLocation().clone().add(0, 0.3, 0);
		final Vector v = pl.getDirection().clone();
		
		if(chcooldown.containsKey(p.getUniqueId()))
        {
            long timer = (chcooldown.get(p.getUniqueId())/1000 + 8) - System.currentTimeMillis()/1000; 
            if(!(timer < 0))
            {
            }
            else 
            {
            	chcooldown.remove(p.getUniqueId());
                Holding.holding(null, p, 20l);
                Holding.invur(p, 20l);
            	HashSet<Location> line = new HashSet<Location>();
                tl.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 1.0f, 2f);
                tl.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
                for(double d = 0; d <= pl.clone().distance(tl)+4.5; d += 0.5) {
                	Location al = pl.clone().add(v.clone().normalize().multiply(d));
                	if(al.getBlock().isPassable()) {
                		line.add(al);
                	}
                }
                line.forEach(l ->  {
     				p.getWorld().spawnParticle(Particle.FLAME, l, 60, 1.5,1.5,1.5,0);
                    p.getWorld().playSound(l, Sound.ITEM_FIRECHARGE_USE, 0.1f, 1.1f);
                });
            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
        		@Override
                	public void run() 
	                {	
	                    line.forEach(l ->  {	
    	                    p.getWorld().playSound(l, Sound.ENTITY_SKELETON_AMBIENT, 0.1f, 1.5f);
             				p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5, 1.5,1.5,1.5);
             				p.getWorld().spawnParticle(Particle.FLAME, l, 30, 1,1,1);
							for(Entity e : p.getWorld().getNearbyEntities(l,1.5, 1.5, 1.5)) {
								if(e instanceof LivingEntity&&  !(e.hasMetadata("portal")) && e!=p) {
									LivingEntity le = (LivingEntity)e;
									le.damage(1,p);		
								}
							}
	                    	p.teleport(l.add(0, 0.5, 0));
	                    });
	                	chargable.remove(p.getUniqueId());
	                	dazable.put(p.getUniqueId(), true);
		            }
            	}, 21); 
				chcooldown.put(p.getUniqueId(), System.currentTimeMillis());  
            }
        }
        else 
        {
            Holding.holding(null, p, 20l);
            Holding.invur(p, 20l);
        	HashSet<Location> line = new HashSet<Location>();
            tl.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 1.0f, 2f);
            tl.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
            for(double d = 0; d <= pl.clone().distance(tl)+4.5; d += 0.5) {
            	Location al = pl.clone().add(v.clone().normalize().multiply(d));
            	if(al.getBlock().isPassable()) {
            		line.add(al);
            	}
            }
            line.forEach(l ->  {	
 				p.getWorld().spawnParticle(Particle.FLAME, l, 60, 1.5,1.5,1.5,0);
                p.getWorld().playSound(l, Sound.ITEM_FIRECHARGE_USE, 0.1f, 1.1f);
            });
        	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    		@Override
            	public void run() 
                {	
                line.forEach(l ->  {	
                    p.getWorld().playSound(l, Sound.ENTITY_SKELETON_AMBIENT, 0.1f, 1.5f);
     				p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5, 1.5,1.5,1.5);
     				p.getWorld().spawnParticle(Particle.FLAME, l, 30, 1,1,1);
					for(Entity e : p.getWorld().getNearbyEntities(l,1.5, 1.5, 1.5)) {
						if(e instanceof LivingEntity&&  !(e.hasMetadata("portal")) && e!=p) {
							LivingEntity le = (LivingEntity)e;
							le.damage(1,p);		
						}
					}
                	p.teleport(l.add(0, 0.5, 0));
                });
            	chargable.remove(p.getUniqueId());
            	dazable.put(p.getUniqueId(), true);
	            }
        	}, 21); 
			chcooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		}
	}
	
	@SuppressWarnings("unchecked")
	public void Charge(EntityDamageByEntityEvent d) 
	{
		if((d.getEntity() instanceof Skeleton) && d.getEntity().hasMetadata("redboss")) 
		{
			final Skeleton p = (Skeleton)d.getEntity();

			if(p.hasMetadata("raid")) {
				if((p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
					p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2);
	                d.setCancelled(true);
	                ordealable.put(p.getUniqueId(), true);
					return;
				}
				if(!OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| backable.containsKey(p.getUniqueId()) || !rb3cooldown.containsKey(p.getUniqueId()) || !chargable.containsKey(p.getUniqueId())) {
					return;
				}
				final Location tl = OverworldRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get().getLocation().clone().add(0, 0.3, 0);
				Charge(p,tl);
			}
			else if(p.hasMetadata("summoned")) {
				final Object hero = getherotype(gethero(p));
				if(hero instanceof Player) {
					final Player hp = (Player)hero;
					Charge(p,hp.getLocation());
				}
				else if(hero instanceof HashSet) {
					HashSet<Player> par = (HashSet<Player>) hero;
					Charge(p,par.stream().findAny().get().getLocation());
				}
			}
		}
	}
	

	final private void Eruption(LivingEntity p, Location tl) {
		if(rb3cooldown.containsKey(p.getUniqueId()))
        {
            long timer = (rb3cooldown.get(p.getUniqueId())/1000 + 5) - System.currentTimeMillis()/1000; 
            if(!(timer < 0))
            {
            }
            else 
            {
                rb3cooldown.remove(p.getUniqueId()); // removing player from HashMap
            	ArrayList<Location> line = new ArrayList<Location>();
                tl.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 1.0f, 2f);
                tl.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
                for(double d = 0.1; d <= 2.5; d += 0.2) {
                    for(double an = 0; an <= Math.PI*2; an += Math.PI/90) {
	                    Location pl = tl.clone();
						pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(d));
						line.add(pl);
                    }
                }
                line.forEach(l ->  {	
     				p.getWorld().spawnParticle(Particle.SOUL, l, 1, 0.1,0.1,0.1,0);
                });
            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
        		@Override
                	public void run() 
	                {	
	                    line.forEach(l ->  {	
             				p.getWorld().spawnParticle(Particle.LAVA, l, 1, 1,1,1,5);
	                    });
	                    p.getWorld().playSound(p.getLocation(), Sound.ITEM_BUCKET_FILL_LAVA, 1.0f, 0f);
	                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_MAGMA_CUBE_SQUISH, 1.0f, 0f);
	                    p.getWorld().playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 0f);
	                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 1.0f, 0f);
		            }
            	}, 30); 

				for(int i = 0; i <8; i++) {
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							for(Entity e : p.getWorld().getNearbyEntities(tl,3, 3, 3)) {
								if(e instanceof LivingEntity&&  !(e.hasMetadata("portal")) && e!=p) {
									LivingEntity le = (LivingEntity)e;
									le.damage(1,p);		
								}
							}
		                }
		            }, i*2+30);
                }
            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
        		@Override
                	public void run() 
	                {	
        				backable.putIfAbsent(p.getUniqueId(), true);
		            }
            	}, 80);
				rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
            }
        }
        else 
        {
        	ArrayList<Location> line = new ArrayList<Location>();
            tl.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 1.0f, 2f);
            tl.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
            for(double d = 0.1; d <= 2.5; d += 0.2) {
                for(double an = 0; an <= Math.PI*2; an += Math.PI/90) {
                    Location pl = tl.clone();
					pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(d));
					line.add(pl);
                }
            }
            line.forEach(l ->  {	
 				p.getWorld().spawnParticle(Particle.SOUL, l, 1, 0.1,0.1,0.1,0);
            });
        	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    		@Override
            	public void run() 
                {	
                    line.forEach(l ->  {	
         				p.getWorld().spawnParticle(Particle.LAVA, l, 1, 1,1,1,5);
                    });
                    p.getWorld().playSound(p.getLocation(), Sound.ITEM_BUCKET_FILL_LAVA, 1.0f, 0f);
                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_MAGMA_CUBE_SQUISH, 1.0f, 0f);
                    p.getWorld().playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 0f);
                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 1.0f, 0f);
	            }
        	}, 30); 

			for(int i = 0; i <8; i++) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						for(Entity e : p.getWorld().getNearbyEntities(tl,3, 3, 3)) {
							if(e instanceof LivingEntity&&  !(e.hasMetadata("portal")) && e!=p) {
								LivingEntity le = (LivingEntity)e;
								le.damage(1,p);		
							}
						}
	                }
	            }, i*2+30);
            }
        	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    		@Override
            	public void run() 
                {	
				backable.putIfAbsent(p.getUniqueId(), true);
	            }
        	}, 80);
			rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		}
	}
	
	@SuppressWarnings("unchecked")
	public void Eruption(EntityDamageByEntityEvent ev) 
	{
		if(ev.getEntity().hasMetadata("redboss")) 
		{
			final LivingEntity p = (LivingEntity)ev.getEntity();
	        

			if(p.hasMetadata("raid")) {
				if(!OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")) {
					return;
				}

				final Location tl = OverworldRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get().getLocation().clone().add(0,0.2,0);
				Eruption(p,tl);
			}
			else if(p.hasMetadata("summoned")) {
				final Object hero = getherotype(gethero(p));
				if(hero instanceof Player) {
					final Player hp = (Player)hero;
					Eruption(p,hp.getLocation());
				}
				else if(hero instanceof HashSet) {
					HashSet<Player> par = (HashSet<Player>) hero;
					Eruption(p,par.stream().findAny().get().getLocation());
				}

			}
		}
	}
	

	public void BackStab(EntityDamageEvent d) 
	{
		if((d.getEntity() instanceof Skeleton) && d.getCause() == DamageCause.FALL && d.getEntity().hasMetadata("redboss")) 
		{
			Skeleton p = (Skeleton)d.getEntity();
			if(stab.containsKey(p.getUniqueId())) {
				d.setCancelled(true);
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_STEP, 1.0f, 0f);
                p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
                p.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1.0f, 0f);
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_CONVERTED_TO_STRAY, 1.0f, 0f);
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0f);
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_HOSTILE_BIG_FALL, 1.0f, 0f);
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_HOSTILE_BIG_FALL, 1.0f, 2f);
 				p.getWorld().spawnParticle(Particle.LAVA, p.getLocation(), 50, 2,2,2);
 				p.getWorld().spawnParticle(Particle.FLAME, p.getLocation(), 200, 0.5,0.5,0.5,1.5);
 				p.getWorld().spawnParticle(Particle.FLAME, p.getLocation(), 200, 2.5,2.5,2.5,0.25);
 				p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 50, 2.5,2.5,2.5,0.25);
				for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),2.5, 2.5, 2.5)) {
					if(e instanceof LivingEntity&&  !(e.hasMetadata("portal")) && e!=p) {
						LivingEntity le = (LivingEntity)e;
						le.damage(5,p);		
					}
				}
			}
		}
		
	}

	
	public void BackStab(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("redboss") && (d.getEntity() instanceof Mob)) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 8;
	        

			if(p.getTarget() == null|| !(p.getTarget() instanceof Player)||p.hasMetadata("failed") || !backable.containsKey(p.getUniqueId())) {
				return;
			}
			final Player tar = (Player) p.getTarget();
					if(rb8cooldown.containsKey(p.getUniqueId()))
		            {
		                long timer = (rb8cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		                if(!(timer < 0))
		                {
		                }
		                else 
		                {
		                	rb8cooldown.remove(p.getUniqueId()); // removing player from HashMap
		                    Holding.holding(null, p, 24l);
	    	    			Location pl = p.getEyeLocation().clone();
							p.getWorld().playSound(pl, Sound.ENTITY_SKELETON_AMBIENT, 1.0f, 0f);
							p.getWorld().playSound(pl, Sound.ENTITY_SKELETON_HORSE_AMBIENT, 1.0f, 0f);
							p.getWorld().playSound(pl, Sound.ITEM_ARMOR_EQUIP_NETHERITE, 1.0f, 0f);
							p.getWorld().spawnParticle(Particle.FLASH, pl, 20, 2,2,2);
							for(int i = 0; i<3; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				             		@Override
				                	public void run() 
					                {	
					                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
					                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_CONVERTED_TO_STRAY, 1.0f, 2f);
					                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_STEP, 1.0f, 0f);
					                    p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0.5f);
				    	    			final Location pel = tar.getLocation().clone();
										for(int i = 0; i<4; i++) {
						                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							             		@Override
							                	public void run() 
								                {	
							    	    			Location pl = p.getLocation().clone();
							    	    			try {
									        			p.teleport(pl.setDirection(pel.toVector().subtract(pl.clone().toVector()).normalize()));
							    	    			}
							    	    			catch(IllegalArgumentException e){
									        			p.teleport(p.getLocation());
							    	    			}
							             			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 1,0,false,false));
								                	p.setVelocity(p.getEyeLocation().clone().getDirection().clone().normalize().multiply(0.8));
						             				p.getWorld().spawnParticle(Particle.FLAME, pl, 30, 1,1,1);
													for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),1.5, 1.5, 1.5)) {
														if(e instanceof LivingEntity&&  !(e.hasMetadata("portal")) && e!=p) {
															LivingEntity le = (LivingEntity)e;
															le.damage(1,p);
														}
													}
								                	
									            }
					                	   	}, i);
										}
					                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						             		@Override
						                	public void run() 
							                {
												Holding.holding(null, p, 2l);
								            }
				                	   	}, 5);
			             				p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, pl, 20, 1.5,1.5,1.5);
					                	
						            }
		                	   	}, i*6+25);
							}
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
			                	public void run() 
				                {	
				                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_HORSE_JUMP_WATER, 1.0f, 0f);
				                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_STEP, 1.0f, 0f);
				        			p.teleport(tar.getLocation().clone().add(0, 6, 0));
				        			p.setFallDistance(15);
				        			stab.put(p.getUniqueId(), true);
				                	backable.remove(p.getUniqueId());
					            }
	                	   	}, 45);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
			                	public void run() 
				                {	
			             			chargable.putIfAbsent(p.getUniqueId(), true);
					            }
	                	   	}, 80);
							rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {
    	    			Location pl = p.getEyeLocation().clone();
						p.getWorld().playSound(pl, Sound.ENTITY_SKELETON_AMBIENT, 1.0f, 0f);
						p.getWorld().playSound(pl, Sound.ENTITY_SKELETON_HORSE_AMBIENT, 1.0f, 0f);
						p.getWorld().playSound(pl, Sound.ITEM_ARMOR_EQUIP_NETHERITE, 1.0f, 0f);
						p.getWorld().spawnParticle(Particle.FLASH, pl, 20, 2,2,2);
	                    Holding.holding(null, p, 24l);
						for(int i = 0; i<3; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
			                	public void run() 
				                {	
				                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
				                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_CONVERTED_TO_STRAY, 1.0f, 2f);
				                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_STEP, 1.0f, 0f);
				                    p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0.5f);
			    	    			final Location pel = tar.getLocation().clone();
									for(int i = 0; i<4; i++) {
					                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						             		@Override
						                	public void run() 
							                {	
						    	    			Location pl = p.getLocation().clone();
						    	    			try {
								        			p.teleport(pl.setDirection(pel.toVector().subtract(pl.clone().toVector()).normalize()));
						    	    			}
						    	    			catch(IllegalArgumentException e){
								        			p.teleport(p.getLocation());
						    	    			}
						             			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 1,0,false,false));
							                	p.setVelocity(p.getEyeLocation().clone().getDirection().clone().normalize().multiply(0.8));
					             				p.getWorld().spawnParticle(Particle.FLAME, pl, 30, 1,1,1);
												for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),1.5, 1.5, 1.5)) {
													if(e instanceof LivingEntity&&  !(e.hasMetadata("portal")) && e!=p) {
														LivingEntity le = (LivingEntity)e;
														le.damage(1,p);
													}
												}
								            }
				                	   	}, i);
									}
				                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					             		@Override
					                	public void run() 
						                {
											Holding.holding(null, p, 2l);
							            }
			                	   	}, 5);
		             				p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, pl, 20, 1.5,1.5,1.5);
				                	
					            }
	                	   	}, i*6+25);
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
		                	public void run() 
			                {	
			                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_HORSE_JUMP_WATER, 1.0f, 0f);
			                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_STEP, 1.0f, 0f);
			        			p.teleport(tar.getLocation().clone().add(0, 6, 0));
			        			p.setFallDistance(15);
			        			stab.put(p.getUniqueId(), true);
			                	backable.remove(p.getUniqueId());
				            }
                	   	}, 45);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
		                	public void run() 
			                {	
		             			chargable.putIfAbsent(p.getUniqueId(), true);
				            }
                	   	}, 80);
						rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}

	
	
	public void MageFB(EntitySpellCastEvent ev) 
	{
		if(ev.getEntity() instanceof Illusioner && ev.getEntity().hasMetadata("red") && ev.getEntity().hasMetadata("mage")) 
		{
			Illusioner p = (Illusioner)ev.getEntity();
			
			ev.setCancelled(true);
            Fireball fb = (Fireball) p.launchProjectile(Fireball.class);
            fb.setYield(0.1f);
            fb.setGlowing(true);
            fb.setShooter(p);
            fb.setVelocity(p.getLocation().getDirection().normalize().multiply(1.5));
            fb.setIsIncendiary(false);
			fb.setMetadata("redfb", new FixedMetadataValue(RMain.getInstance(), true));
							
		}
		if(ev.getEntity() instanceof Evoker && ev.getEntity().hasMetadata("red") && ev.getEntity().hasMetadata("mage")) 
		{
			Evoker p = (Evoker)ev.getEntity();
			
			ev.setCancelled(true);
        	if(p.hasMetadata("leader")) {
                Fireball fb = (Fireball) p.launchProjectile(Fireball.class);
                fb.setYield(0.1f);
                fb.setGlowing(true);
                fb.setShooter(p);
                fb.setVelocity(p.getLocation().getDirection().normalize().multiply(3));
                fb.setIsIncendiary(false);
				fb.setMetadata("redfb", new FixedMetadataValue(RMain.getInstance(), true));
        	}
            Fireball fb = (Fireball) p.launchProjectile(Fireball.class);
            fb.setYield(0.1f);
            fb.setGlowing(true);
            fb.setShooter(p);
            fb.setVelocity(p.getLocation().getDirection().normalize().multiply(1.5));
            fb.setIsIncendiary(false);
			fb.setMetadata("redfb", new FixedMetadataValue(RMain.getInstance(), true));
							
		}
	}
	
	
	public void Fireball(EntityExplodeEvent ev) 
	{
        
		
		if(ev.getEntity() instanceof Fireball) {
			Fireball fb = (Fireball)ev.getEntity();
			if(fb.getShooter() instanceof LivingEntity) {
				if(fb.hasMetadata("redfb")) {
					ev.setCancelled(true);
				}
				if(fb.hasMetadata("redbfb")) {
					ev.setCancelled(true);
				}
			}
			
		}
	}

	public void Fireball(ProjectileHitEvent ev) 
	{

		if(ev.getEntity() instanceof Fireball) {
			Fireball fb = (Fireball)ev.getEntity();
			if(fb.getShooter() instanceof LivingEntity) {
				LivingEntity p = (LivingEntity) fb.getShooter();
				if(fb.hasMetadata("redfb")) {
					fb.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, fb.getLocation(), 2,1,1,1);
					p.getWorld().playSound(fb.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
					for(Entity n : fb.getNearbyEntities(2.3, 2.3, 2.3)) {
						if(n!=p && n instanceof LivingEntity&& !(n.hasMetadata("raid"))) {
							LivingEntity le = (LivingEntity)n;
							le.damage(3,p);	
						}
					}
				}
				if(fb.hasMetadata("redbfb")) {
					fb.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, fb.getLocation(), 2,1,1,1);
					p.getWorld().playSound(fb.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
					for(Entity n : fb.getNearbyEntities(3, 3, 3)) {
						if(n!=p && n instanceof LivingEntity&& !(n.hasMetadata("raid"))) {
							LivingEntity le = (LivingEntity)n;
							le.damage(3,p);	
						}
					}
				}
			}
			
		}
	}
        
	

	
	public void Fireball(EntityDeathEvent d) 
	{		
		if(d.getEntity().hasMetadata("red")) {
			if(fbt.containsKey(d.getEntity().getUniqueId())) {
				Bukkit.getScheduler().cancelTask(fbt.get(d.getEntity().getUniqueId()));
			}
		}
	}
	

	
	public void Breath(EntityTargetEvent ev) 
	{
		if(ev.getEntity() instanceof Stray && ev.getEntity().hasMetadata("redboss") && ev.getEntity().hasMetadata("ruined") && ev.getTarget() instanceof Player) 
		{
			Stray p = (Stray)ev.getEntity();
			int sec = 2;
	        
			
					if(rb5cooldown.containsKey(p.getUniqueId()))
		            {
		                long timer = (rb5cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		                if(!(timer < 0))
		                {
		                }
		                else 
		                {
		                    rb5cooldown.remove(p.getUniqueId()); // removing player from HashMap


							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 40, 2, false, false));
							for(int i = 0; i <6; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                    ArrayList<Location> cir = new ArrayList<Location>();
					                    AtomicInteger j = new AtomicInteger();
					                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
				                    	for(double angley= -Math.PI/5; angley<Math.PI/5; angley += Math.PI/45) {
				                        	Location one = p.getLocation();
					                    	one.setDirection(one.getDirection().rotateAroundY(angley));
						                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/45) {
						                    	one.setDirection(one.getDirection().rotateAroundAxis(p.getLocation().getDirection(),angle));
					                    		for(double i = 0.1; i<4.4;i+=0.5) {
					                    			Location two = one.clone();
						                    		two.add(two.getDirection().normalize().multiply(i));
							                    	cir.add(two);		                    			
					                    		}
						                    }
				                    	}	
										p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 1.2f);
										p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 1, 0);
					                	cir.forEach(l -> {
					                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
													p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l.add(0, -0.3, 0), 1, 0.2,0.2,0.2,0.5);
													for(Entity e : p.getWorld().getNearbyEntities(l,1, 1, 1)) {
														if(p!=e && e instanceof LivingEntity&&  !(e.hasMetadata("portal"))) {
															LivingEntity le = (LivingEntity)e;
															les.add(le);	
														}
													}
								                }
								            }, j.incrementAndGet()/2000); 
					                	});
				                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
							                    for(LivingEntity le: les) {
													le.damage(3,p);			                    	
							                    }
							                }
							            }, j.incrementAndGet()/2000); 
					                }
					            }, i*5); 	                    	
		                    }
							ev.setCancelled(true);
							rb5cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {


						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 40, 3, false, false));
						for(int i = 0; i <6; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                    ArrayList<Location> cir = new ArrayList<Location>();
				                    AtomicInteger j = new AtomicInteger();
				                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
			                    	for(double angley= -Math.PI/5; angley<Math.PI/5; angley += Math.PI/45) {
			                        	Location one = p.getLocation();
				                    	one.setDirection(one.getDirection().rotateAroundY(angley));
					                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/45) {
					                    	one.setDirection(one.getDirection().rotateAroundAxis(p.getLocation().getDirection(),angle));
				                    		for(double i = 0.1; i<4.4;i+=0.5) {
				                    			Location two = one.clone();
					                    		two.add(two.getDirection().normalize().multiply(i));
						                    	cir.add(two);		                    			
				                    		}
					                    }
			                    	}	
									p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 1.2f);
									p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 1, 0);
				                	cir.forEach(l -> {
				                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
												p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l.add(0, -0.3, 0), 1, 0.2,0.2,0.2,0.5);
												for(Entity e : p.getWorld().getNearbyEntities(l,1, 1, 1)) {
													if(p!=e && e instanceof LivingEntity&&  !(e.hasMetadata("portal"))) {
														LivingEntity le = (LivingEntity)e;
														les.add(le);	
													}
												}
							                }
							            }, j.incrementAndGet()/2000); 
				                	});
			                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
						                    for(LivingEntity le: les) {
												le.damage(3,p);			                    	
						                    }
						                }
						            }, j.incrementAndGet()/2000); 
				                }
				            }, i*5); 	                    	
	                    }
						ev.setCancelled(true);
						rb5cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}
	
	public void Daze(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 40;
		if(d.getEntity() instanceof Stray && d.getEntity().hasMetadata("redboss") && d.getEntity().hasMetadata("ruined")) 
		{
			Stray p = (Stray)d.getEntity();
			if((p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2);
                d.setCancelled(true);
                ordealable.put(p.getUniqueId(), true);
				return;
			}
			if(p.hasMetadata("failed") || !dazable.containsKey(p.getUniqueId())) {
				return;
			}
				if(rb2cooldown.containsKey(p.getUniqueId()))
		        {
		            long timer = (rb2cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		            if(!(timer < 0))
		            {
		            	p.spigot().sendMessage(new ComponentBuilder("You have to wait for " + timer + " seconds to use Daze").create());
		            }
		            else 
		            {
		                rb2cooldown.remove(p.getUniqueId()); // removing player from HashMap
		                dazable.remove(p.getUniqueId());
	                    if(fbt.containsKey(p.getUniqueId())) {
	                    	Bukkit.getScheduler().cancelTask(fbt.get(p.getUniqueId()));
	                    }
		                Holding.holding(null, p, 100l);
		                Holding.invur(p, 100l);
		                for(Entity e : OverworldRaids.getheroes(p)) {
		                	if(e instanceof Player && !e.hasMetadata("fake")) {
		                		Player pe = (Player) e;
		    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
			                		pe.sendMessage(ChatColor.BOLD+"붉은기사: 내눈을 봐라....");
		    					}
		    					else {
			                		pe.sendMessage(ChatColor.BOLD+"RedKnight: Look At My Eyes....");
		    					}
		                	}
		                }
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
				                for(Entity e : OverworldRaids.getheroes(p)) {
				                	if(e instanceof Player && !e.hasMetadata("fake")) {
				                		Player pe = (Player) e;
										if(p.hasLineOfSight(pe) && pe.getLocation().getDirection().angle(p.getLocation().getDirection()) <Math.PI+Math.PI/9 && pe.getLocation().getDirection().angle(p.getLocation().getDirection()) >Math.PI-Math.PI/9) {
											pe.setHealth(1);
											p.getWorld().playSound(pe.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
											pe.getWorld().spawnParticle(Particle.SOUL, pe.getLocation(), 100,2,2,2,1);
										}
				                	}
				                }
			                }
			            }, 100);
	                    rb2cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else 
		        {
	                dazable.remove(p.getUniqueId());
	                if(fbt.containsKey(p.getUniqueId())) {
	                	Bukkit.getScheduler().cancelTask(fbt.get(p.getUniqueId()));
	                }
	                Holding.holding(null, p, 100l);
	                Holding.invur(p, 100l);
	                for(Entity e : OverworldRaids.getheroes(p)) {
	                	if(e instanceof Player && !e.hasMetadata("fake")) {
	                		Player pe = (Player) e;
	    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
		                		pe.sendMessage(ChatColor.BOLD+"붉은기사: 내눈을 봐라....");
	    					}
	    					else {
		                		pe.sendMessage(ChatColor.BOLD+"RedKnight: Look At My Eyes....");
	    					}
	                	}
	                }
	                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
			                for(Entity e : OverworldRaids.getheroes(p)) {
			                	if(e instanceof Player && !e.hasMetadata("fake")) {
			                		Player pe = (Player) e;
									if(p.hasLineOfSight(pe) && pe.getLocation().getDirection().angle(p.getLocation().getDirection()) <Math.PI+Math.PI/9 && pe.getLocation().getDirection().angle(p.getLocation().getDirection()) >Math.PI-Math.PI/9) {
										pe.setHealth(1);
										p.getWorld().playSound(pe.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
										pe.getWorld().spawnParticle(Particle.SOUL, pe.getLocation(), 100,2,2,2,1);
									}
			                	}
			                }
		                }
		            }, 100);
		            rb2cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
	}

	public void Sweep(EntityShootBowEvent ev) 
	{
		if(ev.getEntity().hasMetadata("redboss")){

			ev.setCancelled(true);
			
		    LivingEntity p = ev.getEntity();
		    
		    final Vector v = ev.getProjectile().getVelocity().normalize().clone();
            
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.5f);
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_STEP, 1.0f, 0f);
            p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0.5f);
			for(int i = 0; i<4; i++) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
	                {	
    	    			Location pl = p.getLocation().clone();
    					p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, pl, 10, 1.5,1.5,1.5);
             			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 1,0,false,false));
	                	p.setVelocity(v.clone().multiply(1.2));
         				p.getWorld().spawnParticle(Particle.FLAME, pl, 30, 1,1,1);
						for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),1.5, 1.5, 1.5)) {
							if(e instanceof LivingEntity&&  !(e.hasMetadata("portal")) && e!=p) {
								LivingEntity le = (LivingEntity)e;
								le.damage(1,p);
								Holding.holding(null, le, 10l);
							}
						}
	                	
		            }
        	   	}, i);
			}

		 }
	}



	final private void ordeal(LivingEntity p, EntityDamageByEntityEvent d) {
		if(fbt.containsKey(p.getUniqueId())) {
        	Bukkit.getScheduler().cancelTask(fbt.get(p.getUniqueId()));
        }
		String rn = p.getMetadata("raid").get(0).asString();
        if(ordt.containsKey(rn)) {
        	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        	ordt.removeAll(rn);
        }
        Location rl = OverworldRaids.getraidloc(p).clone();
		p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2);
        d.setCancelled(true);
    	p.teleport(rl.clone().add(0, 0, 1));
        Holding.holding(null, p, 651l);
        Holding.untouchable(p, 651l);
        for(Player pe : OverworldRaids.getheroes(p)) {
			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		pe.sendMessage(ChatColor.BOLD+"붉은기사: 시련의 시간이다.");
			}
			else {
        		pe.sendMessage(ChatColor.BOLD+"RedKnight: Time To Ordeal.");
			}
    		pe.teleport(rl);
    		Holding.invur(pe, 40l);
        }
        int t1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {

				for(int i = 0; i <10; i++) {
                    int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	p.teleport(rl.clone().add(0, 0.5, 1));
							p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
							for(int i = 0; i <20; i++) {
								int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
						                Holding.holding(null, p, 40l);
						                Holding.invur(p, 40l);
					                	Random r1 = new Random();
					                	Random r2 = new Random();
					                	Random r3 = new Random();
					                	Random r4 = new Random();
					                	double rd1 = r1.nextDouble()*10 *(r3.nextBoolean()?1:-1);
					                	double rd2 = r2.nextDouble()*10*(r4.nextBoolean()?1:-1);
					                	Location frl = new Location(p.getWorld(), p.getLocation().getX()+rd1, p.getEyeLocation().getY(), p.getLocation().getZ()+rd2);
					                	
					                	Item sn = p.getWorld().dropItem(frl, new ItemStack(Material.FIRE_CORAL_BLOCK));
					                	sn.setMetadata("redknightcharge", new FixedMetadataValue(RMain.getInstance(), true));
					                	sn.setMetadata("redknightcharge"+rn, new FixedMetadataValue(RMain.getInstance(), true));
					                	sn.setGlowing(true);
					                	sn.setGravity(false);
					                	sn.setVisualFire(true);
					                	sn.setVelocity(p.getLocation().toVector().subtract(frl.toVector()).normalize().multiply(0.135));

						                for(Player pe : OverworldRaids.getheroes(p)) {
					                		if(pe.getWorld().equals(p.getWorld()) && rl.clone().distance(p.getLocation()) > 20 && pe.getWorld() == p.getWorld()) {
						    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
							                		pe.sendMessage(ChatColor.BOLD+"붉은기사: 원위치!");
						    					}
						    					else {
							                		pe.sendMessage(ChatColor.BOLD+"RedKnight: You Can't Get Away!");
						    					}
					                    		pe.teleport(rl);
					                		}
						                }
						                for(Player pe : OverworldRaids.getheroes(p)) {

											if(pe.getWorld() == p.getWorld()) {
							                	final Location pel = pe.getLocation();
							                    p.getWorld().playSound(pel, Sound.BLOCK_LAVA_POP, 1.0f, 2f);
							                    p.getWorld().playSound(pel, Sound.BLOCK_LAVA_POP, 1.0f, 0f);
					             				p.getWorld().spawnParticle(Particle.LANDING_LAVA, pel, 500, 1.5,0.1,1.5,0);
					             				int t2 =Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										                @Override
										                public void run() {	
										                		p.getWorld().playSound(pel, Sound.ITEM_BUCKET_FILL_LAVA, 1.0f, 0f);
										                		p.getWorld().playSound(pel, Sound.ENTITY_PLAYER_SPLASH, 1.0f, 0f);
											                    p.getWorld().playSound(pel, Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
																p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, pel, 200, 1.5,1,1.5,0);	
																p.getWorld().spawnParticle(Particle.LAVA, pel, 200, 1.5,1,1.5,0);	
															for(Entity e : p.getWorld().getNearbyEntities(pel,1.3, 1.5, 1.3)) {
																if(e instanceof Player && e!=p) {
																	Player pe = (Player)e;
																	pe.damage(15,p);
																}
															}
										                }
										            }, 12); 
					        					ordt.put(rn, t2);
											}
						                    
						                }
					                }
					            }, i*10); 	 
								ordt.put(rn, t1);
		                    }
							for(int i = 0; i <100; i++) {
								int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	for(Entity e : p.getNearbyEntities(1, 1, 1)) {
					                		if(e.hasMetadata("redknightcharge"+rn)) {
					                			Item sn = (Item) e;
					                			Bukkit.getServer().getPluginManager().callEvent(new EntityPickupItemEvent(p,sn, 1));
					                			sn.remove();
					                			
					                		}
					                	}
					                }
					            }, i*2); 	 
								ordt.put(rn, t1);
		                    }
		                }
		            }, i*40); 	 
					ordt.put(rn, t1);                   	
                }
            }
        }, 20);
		ordt.put(rn, t1);
		
        int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
            	redknightfailed(p,rn);
            }
        }, 651);
		ordt.put(rn, t3);
		
		return;
	}

	final private void redknightfailed(LivingEntity p, String rn) {
    	p.playHurtAnimation(0);
    	Holding.reset(Holding.ale(p));
    	Holding.ale(p).setMetadata("failed", new FixedMetadataValue(RMain.getInstance(),true));
		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
		Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("redknightcharge"+rn)).forEach(e -> e.remove());
		Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("redknightmagma"+rn)).forEach(e -> e.remove());
        for(Player pe : OverworldRaids.getheroes(p)) {
			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		pe.sendMessage(ChatColor.BOLD+"붉은기사: 힘이 빠지는군...");
			}
			else {
        		pe.sendMessage(ChatColor.BOLD+"RedKnight: I think it's time to go...");
			}
    		Holding.holding(pe, p, 300l);
    		p.removeMetadata("fake", RMain.getInstance());
    		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
        }
        rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
        int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
            	p.removeMetadata("failed", RMain.getInstance());
        		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
            }
        }, 300);
		ordt.put(rn, t3);
	}
	
	public void Ordeal(EntityDamageByEntityEvent d) 
	{
	    
		int sec =70;
		if(d.getEntity() instanceof Stray && d.getEntity().hasMetadata("redboss") && d.getEntity().hasMetadata("ruined")&& !d.getEntity().hasMetadata("failed")) 
		{
			Stray p = (Stray)d.getEntity();
			if(!(p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2)|| !ordealable.containsKey(p.getUniqueId())) {
				return;
			}
				if(rb6cooldown.containsKey(p.getUniqueId()))
		        {
		            long timer = (rb6cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		            if(!(timer < 0))
		            {
		            }
		            else 
		            {
		                rb6cooldown.remove(p.getUniqueId()); // removing player from HashMap
		                ordeal(p,d);
			            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		            }
		        }
		        else 
		        {
	                ordeal(p,d);
		            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		        }
			}
	}
	

	
	public void Ordeal(EntityPickupItemEvent d) 
	{
			if(d.getItem().hasMetadata("redknightcharge")) {

				if((d.getEntity().hasMetadata("redboss"))) {
					d.setCancelled(true);
					LivingEntity p = (LivingEntity) d.getEntity();
	    			String rn = p.getMetadata("raid").get(0).asString();
	    			
					redcharge.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
					redcharge.putIfAbsent(p.getUniqueId(), 1);
					p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
		            for(Player pe : OverworldRaids.getheroes(p)) {
    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
	                		pe.sendMessage(ChatColor.BOLD+"붉은기사의 정수 (" + redcharge.get(p.getUniqueId()) + " / 20)");
    					}
    					else {
	                		pe.sendMessage(ChatColor.BOLD+"RedKnight's Soul (" + redcharge.get(p.getUniqueId()) + " / 20)");
    					}
		            }
		            if(redcharge.get(p.getUniqueId()) >= 20) {
	                    if(ordt.containsKey(rn)) {
	                    	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
	                    	ordt.removeAll(rn);
	                    }
		            	redcharge.remove(p.getUniqueId());
	                	Holding.reset(Holding.ale(p));
		            	Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("redknightcharge"+rn)).forEach(e -> e.remove());
						Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("redknightmagma"+rn)).forEach(e -> e.remove());
		            	Holding.ale(p).setAI(true);
		                rb6cooldown.remove(p.getUniqueId());
		                for(Player pe : OverworldRaids.getheroes(p)) {
	    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
		                		pe.sendMessage(ChatColor.BOLD+"붉은기사: 네놈들은 절대 이길수 없다.");
	    					}
	    					else {
		                		pe.sendMessage(ChatColor.BOLD+"RedKnight: You Can Never Beat Me!");
	    					}
	                		Holding.invur(pe, 60l);
		            		pe.teleport(p);
		            		pe.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 60, 255 ,false,false));
		            		pe.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 60, 255 ,false,false));
		                }
		 				Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {	
		                		p.removeMetadata("fake", RMain.getInstance());
		                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
		    	                p.setInvulnerable(false);
		    	                Holding.ale(p).setInvulnerable(false);
								p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p.getLocation(), 3000, 10,10,10);	
								p.getWorld().spawnParticle(Particle.FLAME, p.getLocation(), 3000, 10,10,10);	
			                    for(Player pe : OverworldRaids.getheroes(p)) {
			                		p.removeMetadata("fake", RMain.getInstance());
			            			p.getWorld().playSound(pe.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
			                		pe.setHealth(0);
			                    }
			                }
			            }, 60); 
		            }
				}
				else if(d.getEntity() instanceof Player) {
					
					d.setCancelled(true);
					d.getEntity().getWorld().playSound(d.getEntity().getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1, 0);
					d.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 1 ,false,false));
					d.getItem().remove();
				}
			}
	}
}