package io.github.chw3021.monsters.dark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Item;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.Witch;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
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

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.party.Party;
import io.github.chw3021.monsters.raids.OverworldRaids;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.chat.ComponentBuilder;



public class DarkSkills extends Summoned{

	/**
	 * 
	 */
	private transient static final long serialVersionUID = 3862591169683563580L;
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
	
	private HashMap<UUID, Boolean> ordealable = new HashMap<UUID, Boolean>();


	static public Multimap<String, Integer> ordt = ArrayListMultimap.create();
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
            line.forEach(l -> {

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
            			if(p.isDead()) {
            				return;
            			}
		        		p.getWorld().spawnParticle(Particle.SPELL_WITCH, l,4,0.1,0.3,0.1);
		        		

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


	public void Spell(EntitySpellCastEvent ev) 
	{

		if(ev.getEntity() instanceof Evoker  && ev.getEntity().hasMetadata("dark")) 
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
			le.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 6,6,false,false));
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
                ws.setBounce(true);
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

		    				p.getWorld().spawnParticle(Particle.SPELL_WITCH, p.getLocation(), 100,3,2,3,0);
		    				for(Entity e : p.getNearbyEntities(3, 2, 3)) {
								if(p!=e && e instanceof Player) {
									LivingEntity le = (LivingEntity)e;
									le.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 6,6,false,false));
								}
		    				}
		                }
		            }, i*3); 
				}
			}
		}
	}


	public void witherskullthrow(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 4;
		if(d.getEntity() instanceof Skeleton && d.getEntity().hasMetadata("darkboss") && throwable.containsKey(d.getEntity().getUniqueId())) 
		{
			Skeleton p = (Skeleton)d.getEntity();
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
			            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
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
		            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
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
				p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, po.getLocation(), 4, 2,2,2);
        		for(Entity e : p.getWorld().getNearbyEntities(po.getLocation(), 3.5, 3.5, 3.5)) {
					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
						LivingEntity le = (LivingEntity)e;
						if(po.hasMetadata("darkbossthrow")) {
							le.damage(3.25,p);
						}
						else {
							d.setCancelled(true);
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


	@SuppressWarnings("unchecked")
	public void reapingHook(EntityDamageByEntityEvent ev) 
	{
		if(ev.getEntity().hasMetadata("darkboss") && !unreapable.containsKey(ev.getEntity().getUniqueId())) 
		{
			final Skeleton p = (Skeleton)ev.getEntity();
	        

			if(p.hasMetadata("raid")) {
				if(!OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")) {
					return;
				}

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
	         			p.getWorld().spawnParticle(Particle.SPELL_WITCH ,pl, 10, 0.2,0.2,0.2,0);
	         			p.getWorld().spawnParticle(Particle.SPELL_INSTANT ,pl, 10, 0.2,0.2,0.2,0);
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
         			p.getWorld().spawnParticle(Particle.SPELL_WITCH ,pl, 10, 0.2,0.2,0.2,0);
         			p.getWorld().spawnParticle(Particle.SPELL_INSTANT ,pl, 10, 0.2,0.2,0.2,0);
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
		if(d.getEntity() instanceof Player && d.getDamager().hasMetadata("darkboss"))
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

	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
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
                    	});

                		final Location el =le.getLocation();

						for(int i = 0; i<5; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
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
	        						p.getWorld().spawnParticle(Particle.SPELL_WITCH, el, 50, 1,1,1);
					            }
	                	   }, i*2+j.getAndIncrement()+1); 
							
						}
				}
		}
	}
	

	public void cage(EntityDamageByEntityEvent d) 
	{
		if((d.getEntity() instanceof Skeleton) && d.getEntity().hasMetadata("darkboss")) 
		{
			Skeleton p = (Skeleton)d.getEntity();
			int sec = 8;
	        

			if(p.getTarget() == null|| !(p.getTarget() instanceof Player)||p.hasMetadata("failed") || !cageable.containsKey(p.getUniqueId())) {
				return;
			}
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

			        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
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
										ptl.getWorld().spawnParticle(Particle.BLOCK_CRACK, ptl, 300, 1.5,1.5,1.5,Material.CRYING_OBSIDIAN.createBlockData()); 
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

					        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
						                	Bukkit.getScheduler().cancelTask(bolt);
						                	cageable.remove(p.getUniqueId());
						                }
						            }, 80); 
				                }
				            }, 35); 
		                    
			        		
	             			
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

		        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
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
									ptl.getWorld().spawnParticle(Particle.BLOCK_CRACK, ptl, 300, 1.5,1.5,1.5,Material.CRYING_OBSIDIAN.createBlockData()); 
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

				        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	Bukkit.getScheduler().cancelTask(bolt);
					                	cageable.remove(p.getUniqueId());
					                }
					            }, 80); 
			                }
			            }, 35); 
	                    
		        		
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
	
	final private Location BlackSpins(Location pl, AtomicInteger j) {

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
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
			{
	     	@Override
            public void run() 
 				{	
	            	pl.getWorld().spawnParticle(Particle.ASH,l, 3,0,0,0,0); 
					pl.getWorld().spawnParticle(Particle.SQUID_INK,l, 4, 0.1,0.1,0.1,0.05); 
	            }
	        }, k.incrementAndGet()/90); 
		});
		return l1;
	}



	final private void darkcircle(Skeleton p, Location tl) {
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

        		p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 30, 10, false, false));
 				p.getWorld().playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1f, 0);
            	p.getWorld().spawnParticle(Particle.ASH,p.getEyeLocation(), 400,1,1,1,0); 
            	
            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
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
    				pl.setDirection(tl.clone().toVector().subtract(pl.clone().toVector()).normalize());
					
					final Integer dis = (int) tl.clone().distance(pl.clone())*2-1;
    				
    				for(int i = 0; i<dis; i++) {
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                 		@Override
    	                	public void run() 
    		                {	

                 			
    	             			Location ptl = BlackSpins(pl.clone(),c).clone();
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
    					
    				}
	                }
            	},35);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                    	public void run() 
                        {	
             				unreapable.remove(p.getUniqueId());
        	            }
                    }, 80); 
				rb7cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
	        }
	    }
	    else 
	    {

    		p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 30, 10, false, false));
				p.getWorld().playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1f, 0);
        	p.getWorld().spawnParticle(Particle.ASH,p.getEyeLocation(), 400,1,1,1,0); 
        	
        	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
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
				pl.setDirection(tl.clone().toVector().subtract(pl.clone().toVector()).normalize());

				final Integer dis = (int) tl.clone().distance(pl.clone())*2-1;
				
				for(int i = 0; i<dis; i++) {
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
	                	public void run() 
		                {	

             			
	             			Location ptl = BlackSpins(pl.clone(),c).clone();
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
                    }, i*3); 
					
				}
                }
        	},35);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
            	public void run() 
                {	
     				unreapable.remove(p.getUniqueId());
	            }
            }, 80); 
			rb7cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		}
	}


	public void darkcircle(EntityDamageByEntityEvent ev) 
	{
		if(ev.getEntity().hasMetadata("darkboss")) 
		{
			final Skeleton p = (Skeleton)ev.getEntity();
	        

			if(p.hasMetadata("raid") && p.hasMetadata("ruined")) {
				if(!OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")) {
					return;
				}

				final Location tl = OverworldRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get().getLocation().clone().add(0,0.2,0);
				darkcircle(p,tl);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void nightMare(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 100;
		if(d.getEntity().hasMetadata("darkboss") && d.getEntity() instanceof Skeleton&& !d.isCancelled() && d.getEntity().hasMetadata("ruined")&& !d.getEntity().hasMetadata("failed")) 
		{
			Skeleton p = (Skeleton)d.getEntity();
			if(p.hasMetadata("failed")) {
				return;
			}
			if(!(p.getHealth() - d.getDamage() <= p.getMaxHealth()*0.2)|| !ordealable.containsKey(p.getUniqueId())) {
				return;
			}
			if(rb5cooldown.containsKey(p.getUniqueId()))
	        {
	            long timer = (rb5cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
	            if(!(timer < 0))
	            {
	            	p.spigot().sendMessage(new ComponentBuilder("You have to wait for " + timer + " seconds to use Riptide").create());
	            }
	            else 
	            {
	            	rb5cooldown.remove(p.getUniqueId()); // removing player from HashMap
	        		String rn = gethero(p);
	        		
	        		final Player tar = OverworldRaids.getheroes(p).stream().findAny().get();
	                d.setCancelled(true);
	                Holding.invur(p, 40l);
	                Holding.holding(null, p, 40l);
	                Holding.untouchable(p, 40l);
	                for(Entity e : OverworldRaids.getheroes(p)) {
	                	if(e instanceof Player) {
	                		Player pe = (Player) e;
	    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
		                		pe.sendMessage(ChatColor.BOLD+"�Ǹ�������: ����� �����ض�...");
	    					}
	    					else {
		                		pe.sendMessage(ChatColor.BOLD+"NightMare: Embrace the darkness...");
	    					}
	                	}
	                }
	    			p.getWorld().spawnParticle(Particle.NAUTILUS, p.getLocation().clone(), 200,2,2,2,0.1);
	    			p.getWorld().spawnParticle(Particle.WATER_WAKE, p.getLocation().clone(), 200,2,2,2,0.1);
                    int i1 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
			                Holding.invur(p, 30l);
			                Holding.untouchable(p, 30l);
		                }
		            }, 40, 20);
					ordt.put(rn, i1); 
	            }
	        }
	        else 
	        {
        		String rn = p.getMetadata("raid").get(0).asString();
        		final Player tar = OverworldRaids.getheroes(p).stream().findAny().get();
                d.setCancelled(true);
                Holding.invur(p, 40l);
                Holding.holding(null, p, 40l);
                Holding.untouchable(p, 40l);
                for(Entity e : OverworldRaids.getheroes(p)) {
                	if(e instanceof Player) {
                		Player pe = (Player) e;
    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
	                		pe.sendMessage(ChatColor.BOLD+"���������: "+tar.getName()+"! �׳���� ó�����ְڴ�!");
    					}
    					else {
	                		pe.sendMessage(ChatColor.BOLD+"ElderGuardian: "+tar.getName()+"! I'll kill you first!");
    					}
                	}
                }
    			p.getWorld().spawnParticle(Particle.NAUTILUS, p.getLocation().clone(), 200,2,2,2,0.1);
    			p.getWorld().spawnParticle(Particle.WATER_WAKE, p.getLocation().clone(), 200,2,2,2,0.1);
                int i1 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
		                Holding.invur(p, 30l);
		                Holding.untouchable(p, 30l);
	                }
	            }, 40, 20);
				ordt.put(rn, i1); 
	        }
		
		}
	}
	
}
