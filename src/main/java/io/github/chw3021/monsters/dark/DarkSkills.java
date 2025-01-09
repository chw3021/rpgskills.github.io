package io.github.chw3021.monsters.dark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Item;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.Witch;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpellCastEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import com.google.common.collect.HashMultimap;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.OverworldRaids;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;



public class DarkSkills extends Summoned{

	Holding hold = Holding.getInstance();
	private HashMap<UUID, Long> rb1cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb4cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb5cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb8cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb7cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Integer> throwable = new HashMap<UUID, Integer>();

	private HashMap<UUID, Boolean> unreapable = new HashMap<UUID, Boolean>();
	private HashMap<UUID, Boolean> cageable = new HashMap<UUID, Boolean>();
	

	private static HashMap<UUID, Boolean> counterable = new HashMap<UUID, Boolean>();
	
	private HashMap<UUID, Integer> count = new HashMap<UUID, Integer>();

	public static HashMap<UUID, Integer> countt = new HashMap<UUID, Integer>();
	
	private static final DarkSkills instance = new DarkSkills ();
	public static DarkSkills getInstance()
	{
		return instance;
	}
	

	final private ArrayList<Location> RayBow(Location il){
    	ArrayList<Location> line = new ArrayList<Location>();
        for(double d = 0.1; d <= 13.1; d += 0.2) {
            Location pl = il.clone();
			pl.add(il.getDirection().normalize().multiply(d));
			line.add(pl);
        }
        return line;
	}
	
	public void darkBow(EntityShootBowEvent ev) 
	{
		if(ev.getEntity().hasMetadata("dark") && ev.getEntity().hasMetadata("NightArcher")){

			ev.setCancelled(true);
			
		    LivingEntity p = ev.getEntity();
        	final ArrayList<Location> line = RayBow(p.getEyeLocation().clone());

            AtomicInteger j = new AtomicInteger();
        	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1f, 2f);
            
            if(p.hasMetadata("boss")) {
                
            	p.swingMainHand();
            	
                line.forEach(l -> {

    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() 
    	                {
                			if(p.isDead()) {
                				return;
                			}
    		        		p.getWorld().spawnParticle(Particle.WITCH, l,18,0.5,0.8,0.5);
    		        		

                        	for (Entity e : p.getWorld().getNearbyEntities(l, 0.7, 0.7, 0.7))
            				{
            					if(p!=e && e instanceof LivingEntity) {
            						LivingEntity le = (LivingEntity)e;
            						le.damage(1.2,p);
            					}
            				}
    	                }
    				}, j.incrementAndGet()/3); 
    				
                });
            }
            else {
                line.forEach(l -> {

    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() 
    	                {
                			if(p.isDead()) {
                				return;
                			}
    		        		p.getWorld().spawnParticle(Particle.WITCH, l,4,0.1,0.3,0.1);
    		        		

                        	for (Entity e : p.getWorld().getNearbyEntities(l, 0.25, 0.25, 0.25))
            				{
            					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
            						LivingEntity le = (LivingEntity)e;
            						le.damage(0.5,p);
            					}
            				}
    	                }
    				}, j.incrementAndGet()/2); 
    				
                });
            }
            

		 }
	}


	public void Spell(EntitySpellCastEvent ev) 
	{

		if(ev.getEntity() instanceof Evoker  && ev.getEntity().hasMetadata("vexLocked")) 
		{
			Evoker p = (Evoker)ev.getEntity();
			
			if(ev.getSpell() == org.bukkit.entity.Spellcaster.Spell.SUMMON_VEX) {
				ev.setCancelled(true);
				p.setSpell(org.bukkit.entity.Spellcaster.Spell.FANGS);
			}
		}
	}

	public void darkness(EntityDamageByEntityEvent d) 
	{
		if((d.getDamager() instanceof LivingEntity) && d.getEntity() instanceof LivingEntity &&d.getDamager().hasMetadata("dark")&& !d.isCancelled()) 
		{
			LivingEntity le = (LivingEntity) d.getEntity();
			le.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 16,16,false,false));
		}
	}
	
	
	public void darkwitch(ProjectileLaunchEvent d) 
	{
		if(d.getEntity().getShooter() instanceof Witch && d.getEntity() instanceof ThrownPotion) {
			Witch p = (Witch) d.getEntity().getShooter();
			if(p.hasMetadata("DarkWitch")) {
        		p.swingMainHand();
				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1, 1);
                WitherSkull ws = (WitherSkull) p.launchProjectile(WitherSkull.class);
                ws.setYield(0.0f);
                ws.setGravity(true);
                ws.setShooter(p);
                ws.setVelocity(p.getLocation().getDirection().normalize().multiply(0.25));
                ws.setIsIncendiary(false);
            	ws.setCharged(false);
				ws.setMetadata("darkmobthrow", new FixedMetadataValue(RMain.getInstance(), true));
				ws.setMetadata("darkwitchthrow", new FixedMetadataValue(RMain.getInstance(), true));
				d.setCancelled(true);
				
			}
		}
	}

	public void darkcreep(ExplosionPrimeEvent d) 
	{
		Entity cc = d.getEntity();
		
		if(cc instanceof Creeper) {
			Creeper p = (Creeper)cc;
			if(p.hasMetadata("dark")) {
				for(int i = 0 ; i < 10; i++) {
	        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {

		    				p.getWorld().spawnParticle(Particle.WITCH, p.getLocation(), 100,3,2,3,0);
		    				for(Entity e : p.getNearbyEntities(3, 2, 3)) {
								if(p!=e && e instanceof Player) {
									LivingEntity le = (LivingEntity)e;
									le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 6,6,false,false));
								}
		    				}
		                }
		            }, i*3); 
				}
			}
		}
	}


	@SuppressWarnings("deprecation")
	public void witherskullthrow(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 4;
		if(d.getEntity() instanceof Skeleton&& !d.isCancelled() && d.getEntity().hasMetadata("darkboss") && throwable.containsKey(d.getEntity().getUniqueId())) 
		{
			Skeleton p = (Skeleton)d.getEntity();
			String rn = gethero(p);

            if (checkAndApplyCharge(p, d)) return;
			if(ordeal.containsKey(p.getUniqueId()) || p.hasMetadata("failed")) {
				return;
			}
			if(rb3cooldown.containsKey(p.getUniqueId()))
	        {
	            long timer = (rb3cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
	            if(!(timer < 0))
	            {
	            }
	            else 
	            {
	            	rb3cooldown.remove(p.getUniqueId()); 

	            	throwable.remove(p.getUniqueId());
	                Holding.holding(null, p, 10l);
	                
	                if(p.hasMetadata("ruined")) {
	            		p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, 3, false, false));
	                }

		            for(int i = 0; i<3; i++) {
			            int ta = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
				            	cageable.put(p.getUniqueId(), true);
			            		p.swingMainHand();
								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1, 1);
			                    WitherSkull ws = (WitherSkull) p.launchProjectile(WitherSkull.class);
			                    ws.setYield(0.0f);
			                    ws.setBounce(false);
			                    ws.setShooter(p);
			                    ws.setVelocity(p.getLocation().getDirection().normalize().multiply(2));
			                    ws.setIsIncendiary(false);
			                	ws.setCharged(true);
								ws.setMetadata("darkmobthrow", new FixedMetadataValue(RMain.getInstance(), true));
								ws.setMetadata("darkbossthrow", new FixedMetadataValue(RMain.getInstance(), true));
			                }
			            }, 11+i*4); 
		                ordt.put(rn, ta);
		            }
					rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {
            	throwable.remove(p.getUniqueId());

                Holding.holding(null, p, 10l);

                if(p.hasMetadata("ruined")) {
            		p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, 3, false, false));
                }
	            for(int i = 0; i<3; i++) {
		            int ta = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
			            	cageable.put(p.getUniqueId(), true);
		            		p.swingMainHand();
							p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1, 1);
		                    WitherSkull ws = (WitherSkull) p.launchProjectile(WitherSkull.class);
		                    ws.setYield(0.0f);
		                    ws.setBounce(false);
		                    ws.setShooter(p);
		                    ws.setVelocity(p.getLocation().getDirection().normalize().multiply(2));
		                    ws.setIsIncendiary(false);
		                	ws.setCharged(true);
							ws.setMetadata("darkmobthrow", new FixedMetadataValue(RMain.getInstance(), true));
							ws.setMetadata("darkbossthrow", new FixedMetadataValue(RMain.getInstance(), true));
		                }
		            }, 11+i*4); 
	                ordt.put(rn, ta);
	            }
				rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	        }
		}					
	}
	

	public void witherskullthrow(ProjectileHitEvent d) 
	{
		if(d.getEntity() instanceof WitherSkull) 
		{
			WitherSkull po = (WitherSkull)d.getEntity();
			if(po.hasMetadata("darkmobthrow") && po.getShooter() instanceof LivingEntity) {
				LivingEntity p = (LivingEntity) po.getShooter();
    			if(p.isDead()) {
    				return;
    			}
				po.getWorld().playSound(po.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.4f, 1.6f);
				p.getWorld().spawnParticle(Particle.EXPLOSION, po.getLocation(), 4, 2,2,2);
        		for(Entity e : p.getWorld().getNearbyEntities(po.getLocation(), 3.5, 3.5, 3.5)) {
					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
						LivingEntity le = (LivingEntity)e;
						if(po.hasMetadata("darkbossthrow")) {
							le.damage(3.25,p);
						}
						else {
							le.damage(1.3,p);
						}
					}
            	}
				
			}
			
		}
	}
	public void witherskullthrow(EntityExplodeEvent ev) 
	{
	    
		
		if(ev.getEntity() instanceof WitherSkull) {
			WitherSkull fb = (WitherSkull)ev.getEntity();
			if(fb.hasMetadata("darkmobthrow")) {
				ev.setCancelled(true);
			}
			
		}
	}


	@SuppressWarnings({ "unchecked" })
	public void reapingHook(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("darkboss") && !unreapable.containsKey(d.getEntity().getUniqueId())) 
		{
			final Skeleton p = (Skeleton)d.getEntity();
	        

			if(p.hasMetadata("raid")) {
				if(!OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")|| ordeal.containsKey(p.getUniqueId())) {
					return;
				}

	            if (checkAndApplyCharge(p, d)) return;
				final Location tl = OverworldRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get().getLocation().clone().add(0,0.2,0);
				reapingHook(p,tl);
			}
			else if(p.hasMetadata("summoned")) {
				final Object hero = getherotype(gethero(p));
				if(hero instanceof Player) {
					final Player hp = (Player)hero;
					reapingHook(p,hp.getLocation().clone().add(0,0.2,0));
				}
				else if(hero instanceof HashSet) {
					HashSet<Player> par = (HashSet<Player>) hero;
					reapingHook(p,par.stream().findAny().get().getLocation().clone().add(0,0.2,0));
				}

			}
		}
	}


	private HashMap<UUID, Item> hooki = new HashMap<UUID, Item>();
	private HashMap<UUID, Integer> hookt1 = new HashMap<UUID, Integer>();
	private HashMap<UUID, LivingEntity> hookl = new HashMap<UUID, LivingEntity>();
	
	final private void reapingHook(Skeleton p, Location tl) {
		if(cageable.containsKey(p.getUniqueId())) {
			return;
		}
		if(rb8cooldown.containsKey(p.getUniqueId()))
        {
            long timer = (rb8cooldown.get(p.getUniqueId())/1000 + 9) - System.currentTimeMillis()/1000; 
            if(!(timer < 0))
            {
            }
            else 
            {
            	rb8cooldown.remove(p.getUniqueId()); // removing player from HashMap

                if(p.hasMetadata("ruined")) {
     				unreapable.putIfAbsent(p.getUniqueId(), true);
            		p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, 3, false, false));
                }
                final Location pfl = p.getLocation().clone();
                
                final Vector pv = tl.clone().toVector().subtract(pfl.clone().toVector()).normalize();
                

        		p.swingMainHand();
				ItemStack reap = new ItemStack(Material.NETHERITE_HOE);
				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_FISHING_BOBBER_THROW, 1.0f, 0f);
				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SKELETON_AMBIENT, 1.0f, 0f);
     			p.getWorld().spawnParticle(Particle.SCULK_SOUL ,p.getLocation(), 200, 0.2,0.2,0.2);
     			Holding.holding(null, p, 10l);

				Location pl = pfl.clone();

				
				Item hook = p.getWorld().dropItem(p.getLocation(), reap);
				hook.setPickupDelay(9999);
				hook.setGravity(false);
				hook.setInvulnerable(true);
				hook.setOwner(p.getUniqueId());
				hook.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				hook.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
        		String rn = gethero(p);
				hook.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
				hook.setVelocity(pl.getDirection().clone().normalize().multiply(1.2));
				hooki.put(p.getUniqueId(), hook);

	        	int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	        		@Override
	            	public void run() 
	                {	
            			if(p.isDead()) {
            				return;
            			}
	        			pl.add(pv.clone().normalize().multiply(1.2));
						hook.teleport(pl);
	         			p.getWorld().spawnParticle(Particle.WITCH ,pl, 10, 0.2,0.2,0.2,0);
	         			p.getWorld().spawnParticle(Particle.INSTANT_EFFECT ,pl, 10, 0.2,0.2,0.2,0);
	         			p.getWorld().spawnParticle(Particle.SCULK_SOUL ,pl, 10, 0.2,0.2,0.2,0);
	                    for(Entity e : pl.getWorld().getNearbyEntities(pl, 1.2,1.2,1.2)) {
							if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
								Player le = (Player)e;
								le.damage(3.5,p);
								hookl.put(p.getUniqueId(), le);
							}
	                    }
	        			
	                }
	        	},10,1);
                hookt1.put(p.getUniqueId(), task);
                ordt.put(rn, task);
                
            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            		@Override
                	public void run() 
	                {
            			if(hookt1.containsKey(p.getUniqueId())) {
            				Bukkit.getScheduler().cancelTask(hookt1.get(p.getUniqueId()));
            				hookt1.remove(p.getUniqueId());
            			}
		            }
            	},45);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
	                {	
     					hook.remove();
             			throwable.put(p.getUniqueId(), 1);
		            }
                }, 46); 
                
				rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
            }
        }
        else 
        {

            if(p.hasMetadata("ruined")) {
 				unreapable.putIfAbsent(p.getUniqueId(), true);
        		p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, 3, false, false));
            }

            final Location pfl = p.getLocation().clone();
            
            final Vector pv = tl.clone().toVector().subtract(pfl.clone().toVector()).normalize();
            

    		p.swingMainHand();
			ItemStack reap = new ItemStack(Material.WITHER_SKELETON_SKULL);
			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_FISHING_BOBBER_THROW, 1.0f, 0f);
			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SKELETON_AMBIENT, 1.0f, 0f);
 			p.getWorld().spawnParticle(Particle.SCULK_SOUL ,p.getLocation(), 200, 0.2,0.2,0.2);
 			Holding.holding(null, p, 10l);

			Location pl = pfl.clone();
			
			Item hook = p.getWorld().dropItem(p.getLocation(), reap);
			hook.setPickupDelay(9999);
			hook.setGravity(false);
			hook.setInvulnerable(true);
			hook.setOwner(p.getUniqueId());
			hook.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			hook.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
    		String rn = gethero(p);
			hook.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
			hook.setVelocity(pl.getDirection().clone().normalize().multiply(1.2));
			hooki.put(p.getUniqueId(), hook);

        	int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
        		@Override
            	public void run() 
                {	
        			if(p.isDead()) {
        				return;
        			}
        			pl.add(pv.clone().normalize().multiply(1.2));
					hook.teleport(pl);
         			p.getWorld().spawnParticle(Particle.WITCH ,pl, 10, 0.2,0.2,0.2,0);
         			p.getWorld().spawnParticle(Particle.INSTANT_EFFECT ,pl, 10, 0.2,0.2,0.2,0);
         			p.getWorld().spawnParticle(Particle.SCULK_SOUL ,pl, 10, 0.2,0.2,0.2,0);
                    for(Entity e : pl.getWorld().getNearbyEntities(pl, 1.2,1.2,1.2)) {
						if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
							Player le = (Player)e;
							le.damage(3.5,p);
							hookl.put(p.getUniqueId(), le);
						}
                    }
        			
                }
        	},10,1);
            hookt1.put(p.getUniqueId(), task);
            ordt.put(rn, task);
            
        	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
        		@Override
            	public void run() 
                {
        			if(hookt1.containsKey(p.getUniqueId())) {
        				Bukkit.getScheduler().cancelTask(hookt1.get(p.getUniqueId()));
        				hookt1.remove(p.getUniqueId());
        			}
	            }
        	},45);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
            	public void run() 
                {	
 					hook.remove();
         			throwable.put(p.getUniqueId(), 1);
	            }
            }, 46); 
            
			rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		}
	}
	
	public void ReapingHook(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity() instanceof Player&& !d.isCancelled() && d.getDamager().hasMetadata("darkboss"))
		{
			Skeleton p = (Skeleton)d.getDamager();
			Player le = (Player)d.getEntity();
				if(hookl.containsKey(p.getUniqueId()) && hookl.getOrDefault(p.getUniqueId(), p)==le) {

        			if(hookt1.containsKey(p.getUniqueId())) {
        				Bukkit.getScheduler().cancelTask(hookt1.get(p.getUniqueId()));
        				hookt1.remove(p.getUniqueId());
        			}
        			p.setTarget(le);
	                    AtomicInteger j = new AtomicInteger(0);
        				Location hl = le.getLocation();
        				Location pal = p.getLocation().clone().add(p.getLocation().clone().getDirection().normalize().multiply(0.5));
        				
        				String rn = gethero(p);
	                    ArrayList<Location> line = new ArrayList<Location>();
                        for(double da = 0.1; da < pal.distance(hl); da += 0.1) {
    	                    Location hlc = hl.clone();
    	                    hlc.add(pal.clone().toVector().subtract(hlc.clone().toVector()).normalize().multiply(da));
    						line.add(hlc);
                        }
    					hookl.remove(p.getUniqueId());
						if(hooki.containsKey(p.getUniqueId())) {
							hooki.get(p.getUniqueId()).setVelocity(pal.clone().toVector().subtract(hl.clone().toVector()).normalize().multiply(1.1));
						}
                    	line.forEach(l -> {

	                    	int ta = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                    		@Override
			                	public void run() 
				                {	
                    			if(p.isDead()) {
                    				return;
                    			}
         							le.teleport(l);
         							l.getWorld().playSound(l, Sound.ENTITY_EVOKER_FANGS_ATTACK, 0.1f, 2f);
         							Holding.holding(null, le, 10l);
     		                        for(Entity e : le.getWorld().getNearbyEntities(le.getLocation(), 2.5,2.5,2.5)) {
         	    						if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
         	    							Player le = (Player)e;
         	    							le.teleport(l);
         	    						}
         	                        }
         							
				                }
	                    	}, j.getAndIncrement()/20); 
	                    	
	                    	ordt.put(rn, ta);
                    	});

                		final Location el =le.getLocation();

						for(int i = 0; i<5; i++) {
		                    int ta = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
			                	public void run() 
				                {	
	                    			if(p.isDead()) {
	                    				return;
	                    			}
	             					le.damage(1.5,p);
	        		                Holding.holding(null, le, 2l);
	        		                p.swingMainHand();
	        		                p.teleport(p);
	        						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 0.15f, 2f);
	        						p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, el, 1, 0.5,0.5,0.5);
	        						p.getWorld().spawnParticle(Particle.WITCH, el, 50, 1,1,1);
					            }
	                	   }, i*2+j.getAndIncrement()+1); 

	                    	ordt.put(rn, ta);
						}
				}
		}
	}
	

	public void cage(EntityDamageByEntityEvent d) 
	{
		if((d.getEntity() instanceof Skeleton)&& !d.isCancelled() && d.getEntity().hasMetadata("darkboss")) 
		{
			Skeleton p = (Skeleton)d.getEntity();
			int sec = 8;
	        

			if(p.getTarget() == null|| !(p.getTarget() instanceof Player)||p.hasMetadata("failed") || !cageable.containsKey(p.getUniqueId()) || ordeal.containsKey(p.getUniqueId())) {
				return;
			}
            if (checkAndApplyCharge(p, d)) return;
			final Player tar = (Player) p.getTarget();
			final Location ptl = tar.getLocation().clone();
					if(rb4cooldown.containsKey(p.getUniqueId()))
		            {
		                long timer = (rb4cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		                if(!(timer < 0))
		                {
		                }
		                else 
		                {
		                	rb4cooldown.remove(p.getUniqueId()); // removing player from HashMap
			        		String rn = gethero(p);
		                	
		                    Holding.holding(null, p, 34l);
		                    
							ptl.getWorld().spawnParticle(Particle.PORTAL, ptl, 500, 4,2,4,0); 
							ptl.getWorld().spawnParticle(Particle.SOUL, ptl, 500, 4,1.5,4,0); 
							ptl.getWorld().spawnParticle(Particle.SQUID_INK, ptl, 500, 4,1.5,4,0); 
		     				ptl.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SKELETON_AMBIENT, 1f, 0f);
		     				ptl.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 1f, 2f);

			        		int ta = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {

					            	HashSet<Location> lss = new HashSet<>();
					            	for(int ix =-2; ix<=2; ix+=1) {
					            		lss.add(ptl.clone().add(ix, 0, 2));
					            		lss.add(ptl.clone().add(ix, 0, -2));
					            		lss.add(ptl.clone().add(-2, 0, ix));
					            		lss.add(ptl.clone().add(2, 0, ix));
					            	}
				     				ptl.getWorld().playSound(ptl, Sound.BLOCK_CHAIN_PLACE, 0.3f, 2);
				     				ptl.getWorld().playSound(ptl, Sound.BLOCK_CHAIN_PLACE, 0.3f, 0);
				     				
				            		ArmorStand afs = ptl.getWorld().spawn(ptl, ArmorStand.class);
				            		afs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				            		afs.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				            		afs.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
				            		afs.setInvisible(true);
				            		afs.setCollidable(false);
				            		afs.getEquipment().setHelmet(new ItemStack(Material.CRYING_OBSIDIAN));
				            		afs.setInvulnerable(true);
				            		afs.setHeadPose(new EulerAngle(Math.PI/60 , 0, 0));
				            		afs.setGravity(false);


					            	lss.forEach(l -> {
					            		ArmorStand as = l.getWorld().spawn(l, ArmorStand.class);
					            		as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					            		as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					            		as.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
					            		as.setInvisible(true);
					            		as.setCollidable(false);
					            		as.getEquipment().setHelmet(new ItemStack(Material.CRYING_OBSIDIAN));
					            		as.setInvulnerable(true);
					            		as.setHeadPose(new EulerAngle(Math.PI/60 , 0, 0));
					            		as.setGravity(false);

					            		ArmorStand as1 = l.getWorld().spawn(l.clone().add(0,1,0), ArmorStand.class);
					            		as1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					            		as1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					            		as1.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
					            		as1.setInvisible(true);
					            		as1.setCollidable(false);
					            		as1.getEquipment().setHelmet(new ItemStack(Material.CRACKED_POLISHED_BLACKSTONE_BRICKS));
					            		as1.setInvulnerable(true);
					            		as1.setGravity(false);
					            		
				                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                		@Override
						                	public void run() 
							                {	
				                				Holding.ale(as).remove();
				                				Holding.ale(as1).remove();
				                				Holding.ale(afs).remove();
							                }
				                    	},80);
					            	});

					                int bolt = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
					         		@Override
					                	public void run() 
						                {	
										ptl.getWorld().spawnParticle(Particle.PORTAL, ptl, 200, 3,1.5,3); 
										ptl.getWorld().spawnParticle(Particle.BLOCK, ptl, 300, 1.5,1.5,1.5,Material.CRYING_OBSIDIAN.createBlockData()); 
					     				ptl.getWorld().playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 0.3f, 0);
					     				ptl.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_HURT, 0.2f, 2f);
											for (Entity e : ptl.getWorld().getNearbyEntities(ptl.clone(), 4, 4, 4))
											{
		         	    						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
		         	    							LivingEntity le = (LivingEntity)e;
		         	    							le.damage(3.5,p);
		         	    							le.teleport(ptl);
		         	    							Holding.holding(null, le, 20l);
		         	    						}
											}
					         			
							            }
					                }, 0,10);
			                    	ordt.put(rn, bolt);

					        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
						                	Bukkit.getScheduler().cancelTask(bolt);
						                	cageable.remove(p.getUniqueId());
						                }
						            }, 80); 
				                }
				            }, 35); 
	                    	ordt.put(rn, ta);
		                    
			        		
	             			
							rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {
		        		String rn = gethero(p);
	                	
	                    Holding.holding(null, p, 34l);
	                    
						ptl.getWorld().spawnParticle(Particle.PORTAL, ptl, 500, 4,2,4,0); 
						ptl.getWorld().spawnParticle(Particle.SOUL, ptl, 500, 4,1.5,4,0); 
						ptl.getWorld().spawnParticle(Particle.SQUID_INK, ptl, 500, 4,1.5,4,0); 
	     				ptl.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SKELETON_AMBIENT, 1f, 0f);
	     				ptl.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 1f, 2f);

		        		int ta = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {

				            	HashSet<Location> lss = new HashSet<>();
				            	for(int ix =-2; ix<=2; ix+=1) {
				            		lss.add(ptl.clone().add(ix, 0, 2));
				            		lss.add(ptl.clone().add(ix, 0, -2));
				            		lss.add(ptl.clone().add(-2, 0, ix));
				            		lss.add(ptl.clone().add(2, 0, ix));
				            	}
			     				ptl.getWorld().playSound(ptl, Sound.BLOCK_CHAIN_PLACE, 0.3f, 2);
			     				ptl.getWorld().playSound(ptl, Sound.BLOCK_CHAIN_PLACE, 0.3f, 0);
			     				
			            		ArmorStand afs = ptl.getWorld().spawn(ptl, ArmorStand.class);
			            		afs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			            		afs.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			            		afs.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
			            		afs.setInvisible(true);
			            		afs.setCollidable(false);
			            		afs.getEquipment().setHelmet(new ItemStack(Material.CRYING_OBSIDIAN));
			            		afs.setInvulnerable(true);
			            		afs.setHeadPose(new EulerAngle(Math.PI/60 , 0, 0));
			            		afs.setGravity(false);


				            	lss.forEach(l -> {
				            		ArmorStand as = l.getWorld().spawn(l, ArmorStand.class);
				            		as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				            		as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				            		as.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
				            		as.setInvisible(true);
				            		as.setCollidable(false);
				            		as.getEquipment().setHelmet(new ItemStack(Material.CRYING_OBSIDIAN));
				            		as.setInvulnerable(true);
				            		as.setHeadPose(new EulerAngle(Math.PI/60 , 0, 0));
				            		as.setGravity(false);

				            		ArmorStand as1 = l.getWorld().spawn(l.clone().add(0,1,0), ArmorStand.class);
				            		as1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				            		as1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				            		as1.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
				            		as1.setInvisible(true);
				            		as1.setCollidable(false);
				            		as1.getEquipment().setHelmet(new ItemStack(Material.CRACKED_POLISHED_BLACKSTONE_BRICKS));
				            		as1.setInvulnerable(true);
				            		as1.setGravity(false);
				            		
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                		@Override
					                	public void run() 
						                {	
			                				Holding.ale(as).remove();
			                				Holding.ale(as1).remove();
			                				Holding.ale(afs).remove();
						                }
			                    	},80);
				            	});

				                int bolt = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
				         		@Override
				                	public void run() 
					                {	
									ptl.getWorld().spawnParticle(Particle.PORTAL, ptl, 200, 3,1.5,3); 
									ptl.getWorld().spawnParticle(Particle.BLOCK, ptl, 300, 1.5,1.5,1.5,Material.CRYING_OBSIDIAN.createBlockData()); 
				     				ptl.getWorld().playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 0.3f, 0);
				     				ptl.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_HURT, 0.2f, 2f);
										for (Entity e : ptl.getWorld().getNearbyEntities(ptl.clone(), 4, 4, 4))
										{
	         	    						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
	         	    							LivingEntity le = (LivingEntity)e;
	         	    							le.damage(3.5,p);
	         	    							le.teleport(ptl);
	         	    							Holding.holding(null, le, 20l);
	         	    						}
										}
				         			
						            }
				                }, 0,10);
		                    	ordt.put(rn, bolt);

				        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	Bukkit.getScheduler().cancelTask(bolt);
					                	cageable.remove(p.getUniqueId());
					                }
					            }, 80); 
			                }
			            }, 35); 
                    	ordt.put(rn, ta);
	                    
						rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}


	
	public void Fly(EntityTargetEvent d)
	{
		if((d.getEntity() instanceof Skeleton)  &&d.getEntity().hasMetadata("ruined")  &&d.getEntity().hasMetadata("darkboss")) 
		{
			int sec =2;
			Skeleton p = (Skeleton)d.getEntity();
			if(rb1cooldown.containsKey(p.getUniqueId()))
	        {
	            long timer = (rb1cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
	            if(!(timer < 0))
	            {
	            }
	            else 
	            {
	                rb1cooldown.remove(p.getUniqueId()); // removing player from HashMap

            		p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, 2, false, false));

		            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	            }
	        }
	        else 
	        {

        		p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, 2, false, false));
        		
	            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	        }
		}
		
	}
	
	final private Location BlackSpins(String rn, Location pl, AtomicInteger j) {

		ArrayList<Location> swing = new ArrayList<Location>();
		AtomicInteger k = new AtomicInteger(0);
		final double dis = j.getAndIncrement()*0.5+0.12;
		final Location l1 = pl.clone().add(pl.clone().getDirection().normalize().multiply(dis));
		final Vector v = l1.clone().toVector().subtract(l1.clone().add(0, 0, 1).toVector());
		for(double i = 0; i<Math.PI*2; i += Math.PI/90) {
			Location s = l1.clone().setDirection(v.clone().rotateAroundY(i).normalize());
			s.add(s.clone().getDirection().multiply(4.5));
			swing.add(s);
		}
		swing.forEach(l ->{
			int ta =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
			{
	     	@Override
            public void run() 
 				{	
					pl.getWorld().spawnParticle(Particle.SQUID_INK,l, 3, 0.1,0.1,0.1,0.05); 
	            }
	        }, k.incrementAndGet()/90); 
            ordt.put(rn, ta);
		});
		return l1;
	}


	final private void darkcirclemot(Skeleton p, Player tp) {

		p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 12, false, false));
			p.getWorld().playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1f, 0);
    	p.getWorld().spawnParticle(Particle.ASH,p.getEyeLocation(), 400,1,1,1,0); 
    	String rn = gethero(p);
    	
    	int ta = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		@Override
        	public void run() 
            {	
    		p.swingMainHand();
            
			ArrayList<Location> eye = new ArrayList<Location>();
			for(double i = 0; i<Math.PI*2; i += Math.PI/90) {
				Location l2 = p.getEyeLocation().clone();
				Location e = l2.setDirection(l2.getDirection().rotateAroundY(i).normalize());
				eye.add(e);
			}


			for(int i = 0; i<5; i++) {
                int ta =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
                	public void run() 
	                {	

    				AtomicInteger j = new AtomicInteger(0);
    				AtomicInteger c = new AtomicInteger(0);

     				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, 0);
     				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1f, 2);
    				eye.forEach(l ->{
    					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
    					{
    			     	@Override
    	                public void run() 
    	     				{	
    	                    	p.teleport(l);
    			            }
    			        }, j.incrementAndGet()/150); 
    				});

    				Location pl = p.getLocation().clone().add(0, 0.2, 0);
    				Location tl = tp.getLocation().clone().add(0, 0.2, 0);
    				if(!pl.getWorld().equals(tl.getWorld())) {
    					return;
    				}
    				
    				pl.setDirection(tl.clone().toVector().subtract(pl.clone().toVector()).normalize());
					
					final Integer dis = (int) tl.clone().distance(pl.clone())*2-1;
    				for(int i = 0; i<dis; i++) {
                        int ta = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                 		@Override
    	                	public void run() 
    		                {	

                 			
    	             			Location ptl = BlackSpins(rn,pl.clone(),c).clone();
    	             			ptl.getWorld().playSound(ptl, Sound.ENTITY_WITHER_SKELETON_STEP, 0.1f, 0);
    	         				
    	             			for (Entity e : ptl.getWorld().getNearbyEntities(ptl.clone(), 4, 4, 4))
    							{
     	    						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
     	    							LivingEntity le = (LivingEntity)e;
     	    							le.damage(3.5,p);
     	    							le.teleport(ptl);
     	    							Holding.holding(null, le, 20l);
     	    						}
    							}
    			            }
                        }, i*2); 
                        ordt.put(rn, ta);
    					
    				}
    				
		            }
                }, i*6); 
                ordt.put(rn, ta);
				
			}
            }
    	},35);
        ordt.put(rn, ta);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
            	public void run() 
                {	
     				unreapable.remove(p.getUniqueId());
	            }
            }, 80); 
	}

	final private void darkcircle(Skeleton p, Player tp) {
		if(cageable.containsKey(p.getUniqueId()) || throwable.containsKey(p.getUniqueId()) || !unreapable.containsKey(p.getUniqueId())) {
			return;
		}
		if(rb7cooldown.containsKey(p.getUniqueId()))
	    {
	        long timer = (rb7cooldown.get(p.getUniqueId())/1000 + 9) - System.currentTimeMillis()/1000; 
	        if(!(timer < 0))
	        {
	        }
	        else 
	        {
	        	rb7cooldown.remove(p.getUniqueId()); // removing player from HashMap
	        	darkcirclemot(p, tp);
				rb7cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
	        }
	    }
	    else 
	    {
        	darkcirclemot(p, tp);
			rb7cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		}
	}


	public void darkcircle(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("darkboss")&& !d.isCancelled()) 
		{
			final Skeleton p = (Skeleton)d.getEntity();

            if (checkAndApplyCharge(p, d)) return;
			if(p.hasMetadata("raid") && p.hasMetadata("ruined")) {
				if(!OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")|| ordeal.containsKey(p.getUniqueId())) {
					return;
				}

				final Player tp = OverworldRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get();
				darkcircle(p,tp);
			}
		}
	}
	
	final private void nightmare(LivingEntity p, Location rl, String rn) {
        try {
            Player tpe = OverworldRaids.getheroes(p).stream().filter(pe ->!pe.isDead()&&pe.getWorld().equals(p.getWorld())).findAny().get();
            final Location tpel = tpe.getLocation().clone();
            final Location tl = tpel.clone().add(tpel.clone().getDirection().normalize().multiply(-7)).add(0, 2, 0);
            p.teleport(tl);
            OverworldRaids.getheroes(p).forEach(pe ->{
            	pe.playSound(pe, Sound.ENTITY_WITHER_AMBIENT, 1f, 0.26f);
    			pe.spawnParticle(Particle.SOUL, pe.getLocation().clone(), 200,2,2,2,0.1);
            });
        	p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 25,10,false,false));
            int i1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	tpe.playSound(tpe, Sound.ENTITY_PHANTOM_SWOOP, 1f, 1.5f);
                	nightswoop(p,tl,tpel);
                	p.swingMainHand();
                }
            }, 20);
    		ordt.put(rn, i1); 
            int i11 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	counterable.put(p.getUniqueId(), true);
                }
            }, 30);
    		ordt.put(rn, i11); 

            int i2 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
        			p.getWorld().spawnParticle(Particle.DRAGON_BREATH, p.getLocation().clone(), 200,2,2,2,0.1);
                	p.swingMainHand();
                	tpe.playSound(tpe, Sound.ENTITY_WITHER_BREAK_BLOCK, 1f, 1.26f);
                	if(tpe.getWorld().equals(p.getWorld())) {
                    	tpe.damage(6.66,p);
                	}
                	counterable.remove(p.getUniqueId());
                	p.teleport(rl.clone().add(0, 10, 0));
                }
            }, 42);
    		ordt.put(rn, i2); 
    		countt.put(p.getUniqueId(), i2);
        }
        catch(NullPointerException | NoSuchElementException | IllegalArgumentException e) {
        	return;
        }
	}

	final private void nightswoop(LivingEntity p, Location pl, Location tl) {
	    String rn = gethero(p);
		
		List<Location> line = new ArrayList<>();
	    
	    
	    final Vector vec = tl.clone().toVector().subtract(pl.clone().toVector());
	
	    for (double an = 0; an < tl.distance(pl) - 0.1; an += 0.1) {
	        line.add(pl.clone().add(vec.clone().normalize().multiply(an)));
	    }
	
	    AtomicInteger currentIndex = new AtomicInteger(0);
	    AtomicInteger taskId = new AtomicInteger();
	    taskId.set(Bukkit.getScheduler().runTaskTimer(RMain.getInstance(), () -> {
	        int index = currentIndex.getAndIncrement();
	
	        if (index >= line.size()) {
	            Bukkit.getScheduler().cancelTask(taskId.get());
	            nightMareTask.remove(rn, taskId.get()); 
	            return;
	        }
	
	        Location l = line.get(index);
	        Vector dir = vec.clone().normalize();
	        float yaw = (float) Math.toDegrees(Math.atan2(-dir.getX(), dir.getZ()));
	        float pitch = (float) Math.toDegrees(-Math.asin(dir.getY()));
	
	        // 엔티티 이동 및 효과
	        p.setRotation(yaw, pitch);
	        p.setGliding(true);
	        p.getWorld().spawnParticle(Particle.SOUL, l, 3, 1, 1, 1);
	        p.teleport(l);
	
	    }, 0, 4).getTaskId());
	
	    nightMareTask.put(rn, taskId.get());
	}

	public void nightCounter(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("darkboss") && d.getEntity() instanceof Skeleton&& !d.isCancelled() && d.getEntity().hasMetadata("ruined")) 
		{
			Skeleton p = (Skeleton)d.getEntity();
			String rn = gethero(p);
			
			if(ordeal.containsKey(p.getUniqueId())) {
				d.setCancelled(true);
				if(counterable.containsKey(p.getUniqueId())) {
	    			p.getWorld().spawnParticle(Particle.FLASH, p.getLocation().clone(), 1000,5,2,5);

	    			
					count.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
					count.putIfAbsent(p.getUniqueId(), 1);
					
	                Location rl = OverworldRaids.getraidloc(p).clone();
			        p.playHurtAnimation(0);
			        Bukkit.getScheduler().cancelTask(countt.get(p.getUniqueId()));
                	p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20,10,false,false));
                	counterable.remove(p.getUniqueId());
	                p.teleport(rl);

			        OverworldRaids.getheroes(p).forEach(pe ->{
			        	pe.playSound(pe, Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1, 1.1f);
			        	pe.sendMessage(ChatColor.AQUA + "[" + count.get(p.getUniqueId()) + "/5]");
			        	Random random1=new Random();
			        	double number1 = (random1.nextDouble()+1) * 5 * (random1.nextBoolean() ? -1 : 1);
			        	double number21 = (random1.nextDouble()+1) * 5 * (random1.nextBoolean() ? -1 : 1);
			        	Location esl1 = rl.clone().add(number1, 0.5, number21);
			        	pe.teleport(esl1);
			        });
			        
			        if(count.get(p.getUniqueId()) >= 5) {
		                p.teleport(rl);
			        	
			        	p.setGlowing(true);
			        	ordeal.remove(p.getUniqueId());

		        		if(ordt.containsKey(rn)) {
		        			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
		        		}

                		Holding.reset(p);
                		Holding.ale(p).setInvisible(false);
	                	Holding.ale(p).setMetadata("failed", new FixedMetadataValue(RMain.getInstance(),true));
                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
                		Holding.ale(p).setInvulnerable(false);
                		Holding.holding(null, Holding.ale(p), 300l);
    		            rb5cooldown.put(p.getUniqueId(), System.currentTimeMillis());
    		            
		            	Holding.ale(p).setMetadata("failed", new FixedMetadataValue(RMain.getInstance(),true));
		                for(Player pe : OverworldRaids.getheroes(p)) {
							if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
		                		pe.sendMessage(ChatColor.BLUE+"악몽의 형상: 내 어둠이..");
							}
							else {
		                		pe.sendMessage(ChatColor.BLUE+"NightMare: My Darkness..");
							}
							pe.removePotionEffect(PotionEffectType.BLINDNESS);
		            		Holding.holding(pe, p, 300l);
		                }
						
			            int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	p.removeMetadata("failed", RMain.getInstance());
		                		Holding.ale(p).setInvisible(false);
		                		Holding.ale(p).setInvulnerable(false);
		                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
			                }
			            }, 300);
						ordt.put(rn, t3);
			        }
				}
				else {

                    if(nightMareTask.containsKey(rn)) {
                    	nightMareTask.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
                    }
			        Bukkit.getScheduler().cancelTask(countt.remove(p.getUniqueId()));
                    Location rl = OverworldRaids.getraidloc(p).clone();
                    int i1 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            OverworldRaids.getheroes(p).forEach(pe ->{
                            	pe.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 150,10,false,false));
                            	pe.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 150,10,false,false));
                            });
                            nightmare(Holding.ale(p),rl,rn);
                        }
                    }, 30, 100);
                    nightMareTask.put(rn, i1); 
	                for(Player pe : OverworldRaids.getheroes(p)) {
                		if(pe.getWorld().equals(p.getWorld()) && pe.getWorld() == p.getWorld()) {
                			count.remove(p.getUniqueId());
	    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
		                		pe.sendMessage(ChatColor.BOLD+"악몽의 형상: 어리석군.");
	    					}
	    					else {
		                		pe.sendMessage(ChatColor.BOLD+"NightMare: Stupid.");
	    					}
	            			p.getWorld().playSound(pe.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 0.5f, 2);
	            			pe.setVelocity(new Vector(0,-6,0));
	            			pe.damage(27, p);
                		}
	                }
				}
			}
		}
	}
	    
		
	HashMultimap<String,Integer> nightMareTask = HashMultimap.create();
	
	final private void nightmareevent(LivingEntity p, EntityDamageByEntityEvent d) {

    	ordeal.put(p.getUniqueId(), true);
		String rn = gethero(p);
        Location rl = OverworldRaids.getraidloc(p).clone();

        if(ordt.containsKey(rn)) {
        	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        	ordt.removeAll(rn);
        }
        d.setCancelled(true);
        p.teleport(rl.clone().add(0, 50, 0));
        Holding.holding(null, p, 1500l);
        Holding.invur(p, 100l);
        Holding.untouchable(p, 1500l);
        p.setGlowing(false);
        p.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
        
        OverworldRaids.getheroes(p).forEach(pe ->{
        	Holding.invur(pe, 60l);
			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		pe.sendTitle(ChatColor.DARK_GRAY + (ChatColor.MAGIC + "123456"), ChatColor.DARK_GRAY + "어둠을 맞이해라...", 20, 35, 20);
 				Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {	
                		pe.sendMessage(ChatColor.BOLD+"타이밍에 맞춰 적을 공격하세요");
	                }
	            }, 20); 
			}
			else {
        		pe.sendTitle(ChatColor.DARK_GRAY + (ChatColor.MAGIC + "123456"), ChatColor.DARK_GRAY + "Embrace the darkness..", 20, 35, 20);
 				Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {	
                		pe.sendMessage(ChatColor.BOLD+"Attack the enemy at the right time");
	                }
	            }, 20); 
			}
        	pe.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 150,10,false,false));
        	pe.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 150,10,false,false));
        });
        
		p.getWorld().spawnParticle(Particle.SQUID_INK, p.getLocation().clone(), 200,2,2,2,0.1);
		p.getWorld().spawnParticle(Particle.DRAGON_BREATH, p.getLocation().clone(), 200,2,2,2,0.1);
        int i1 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
                OverworldRaids.getheroes(p).forEach(pe ->{
                	pe.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 150,10,false,false));
                	pe.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 150,10,false,false));
                });
                nightmare(Holding.ale(p),rl,rn);
            }
        }, 100, 100);
        nightMareTask.put(rn, i1);
		ordt.put(rn, i1); 

			int i2 = Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {	

                if(ordt.containsKey(rn)) {
                	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
                	ordt.removeAll(rn);
                }
    			count.remove(p.getUniqueId());
                for(Player pe : OverworldRaids.getheroes(p)) {
            		if(pe.getWorld().equals(p.getWorld()) && pe.getWorld() == p.getWorld()) {
    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
	                		pe.sendMessage(ChatColor.BOLD+"악몽의 형상: 빛은 어둠을 이길 수 없다..");
    					}
    					else {
	                		pe.sendMessage(ChatColor.BOLD+"NightMare: Light can't beat darkness..");
    					}
            			p.getWorld().playSound(pe.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 1, 0);
                		Holding.invur(pe, 10l);
			        	p.setGlowing(true);
	            		pe.teleport(p);
						pe.removePotionEffect(PotionEffectType.BLINDNESS);
	            		pe.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 60, 255 ,false,false));
	            		pe.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 60, 255 ,false,false));
            		}
                }
 				Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {	
	                	ordeal.remove(p.getUniqueId());
	                	rb5cooldown.remove(p.getUniqueId()); 
                		p.removeMetadata("fake", RMain.getInstance());
                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
    	                p.setInvulnerable(false);
    	                Holding.ale(p).setInvulnerable(false);
						p.getWorld().spawnParticle(Particle.SQUID_INK, p.getLocation(), 3000, 10,10,10);	
	                    for(Player pe : OverworldRaids.getheroes(p)) {
	                		p.removeMetadata("fake", RMain.getInstance());
	                		pe.setHealth(0);
	                    }
	                }
	            }, 5); 
            }
        }, 1505); 
		ordt.put(rn, i2); 
		
	}
	
	@SuppressWarnings("deprecation")
	public void nightMare(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 100;
		if(d.getEntity().hasMetadata("darkboss") && d.getEntity() instanceof Skeleton&& !d.isCancelled() && d.getEntity().hasMetadata("ruined")) 
		{
			Skeleton p = (Skeleton)d.getEntity();
			if(p.hasMetadata("failed")) {
				return;
			}
			if(!(p.getHealth() - d.getDamage() <= p.getMaxHealth()*0.2)|| !ordealable.containsKey(p.getUniqueId())|| ordeal.containsKey(p.getUniqueId()) || !OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))) {
				return;
			}
			if(rb5cooldown.containsKey(p.getUniqueId()))
	        {
	            long timer = (rb5cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
	            if(!(timer < 0))
	            {
	            }
	            else 
	            {
	            	rb5cooldown.remove(p.getUniqueId()); // removing player from HashMap
	            	nightmareevent(p,d);
		            rb5cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	            }
	        }
	        else 
	        {

	        	nightmareevent(p,d);
	            rb5cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	        }
		
		}
	}
	
}