package io.github.chw3021.monsters.wild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.Item;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpellCastEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.OverworldRaids;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;



public class WildSkills extends Summoned{

	/**
	 * 
	 */
	private static transient final long serialVersionUID = -2543380479388196924L;
	/**
	 * 
	 */
	
	Holding hold = Holding.getInstance();
	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb4cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb8cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Integer> throwable = new HashMap<UUID, Integer>();
	static public Multimap<String, Integer> ordt = ArrayListMultimap.create();
	
	private HashMap<UUID, Boolean> npable = new HashMap<UUID, Boolean>();

	
	private static final WildSkills instance = new WildSkills ();
	public static WildSkills getInstance()
	{
		return instance;
	}
	
	public void slimesplit(SlimeSplitEvent d) 
	{
		if(d.getEntity().hasMetadata("GiantSlime")) {
			d.setCancelled(true);
		}
	}
	
	

	public void spell(EntitySpellCastEvent ev) 
	{

		if(ev.getEntity() instanceof Evoker  && ev.getEntity().hasMetadata("Arsonist")) 
		{
			Evoker p = (Evoker)ev.getEntity();
			
			ev.setCancelled(true);

        	p.getWorld().spawnParticle(Particle.ASH, p.getLocation(), 40, 1,1,1);
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_TNT_PRIMED, 1, 2);
            p.getWorld().playSound(p.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1, 0);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
                	p.getWorld().spawnParticle(Particle.FLAME, p.getLocation(), 300, 2.5,2.5,2.5);
                    p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 2);
                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_BLAZE_BURN, 1, 2);

					for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),2.5, 2.5, 2.5)) {
						if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
							LivingEntity le = (LivingEntity)e;
							le.damage(3,p);
							le.setFireTicks(30);
						}
					}
								
                }
			}, 25);
		}
		
		if(ev.getEntity() instanceof Illusioner  && ev.getEntity().hasMetadata("GasSprayer")) 
		{
			Illusioner p = (Illusioner)ev.getEntity();
			
			ev.setCancelled(true);

        	p.getWorld().spawnParticle(Particle.SPIT, p.getLocation(), 40, 1,1,1);
            p.getWorld().playSound(p.getLocation(), Sound.BLOCK_END_PORTAL_FRAME_FILL, 1, 2);
            p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 1, 0);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
					for(int n = 0; n<3; n++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	p.getWorld().spawnParticle(Particle.SNEEZE, p.getLocation(), 300, 2.7,2.7,2.7);
			                    p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 1, 2);
			                    p.getWorld().playSound(p.getLocation(), Sound.BLOCK_CANDLE_EXTINGUISH, 1, 0);

								for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),2.7, 2.7, 2.7)) {
									if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
										LivingEntity le = (LivingEntity)e;
										le.damage(2,p);
									}
								}
			                }
	                	 }, n*3); 														
					}
								
                }
			}, 40);
		}
	}

	final private ArrayList<Location> RayBow(Location il){
    	ArrayList<Location> line = new ArrayList<Location>();
        for(double d = 0.1; d <= 3.1; d += 0.3) {
            Location pl = il.clone();
			pl.add(il.getDirection().normalize().multiply(d));
			line.add(pl);
        }
        return line;
	}
	
	public void launch(ProjectileLaunchEvent d) 
	{
		if(d.getEntity().getShooter() instanceof Witch && d.getEntity() instanceof ThrownPotion) {
			Witch p = (Witch) d.getEntity().getShooter();
			if(!p.hasMetadata("poison")) {
				return;
			}
			if(p.hasMetadata("Grenadier")) {
				Snowball fr = p.getWorld().spawn(p.getEyeLocation(), Snowball.class);
				fr.setShooter(p);
                fr.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(0.68));
				fr.setMetadata("grenadier", new FixedMetadataValue(RMain.getInstance(), true));
                fr.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                fr.setItem(new ItemStack(Material.GREEN_TERRACOTTA));
				d.setCancelled(true);
				
			}
			else if(p.hasMetadata("PoisonGrenadier")) {
				Snowball fr = p.getWorld().spawn(p.getEyeLocation(), Snowball.class);
				fr.setShooter(p);
                fr.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(0.68));
				fr.setMetadata("PoisonGrenadier", new FixedMetadataValue(RMain.getInstance(), true));
                fr.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                fr.setItem(new ItemStack(Material.GREEN_GLAZED_TERRACOTTA));
				d.setCancelled(true);
			}
		}
		else if(d.getEntity().hasMetadata("GasSprayer")) {
			Illusioner p = (Illusioner) d.getEntity().getShooter();
        	d.setCancelled(true);
        	final ArrayList<Location> line = RayBow(p.getEyeLocation().clone());
            for(double an = -Math.PI/3; an<=Math.PI/3; an += Math.PI/6) {
	        	line.addAll(RayBow(p.getEyeLocation().clone().add(p.getEyeLocation().getDirection().clone().normalize().rotateAroundY(an))));
            }
        	p.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1f, 2f);

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 0.1f, 2f);
                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 0.1f, 0f);
                	
                    line.forEach(l -> {
                    	p.getWorld().spawnParticle(Particle.SNEEZE, l.add(0, -0.289, 0),5, 0.1,0.1,0.1,0.1);

                    	for (Entity e : p.getWorld().getNearbyEntities(l, 0.25, 0.25, 0.25))
        				{
        					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
        						LivingEntity le = (LivingEntity)e;
        						le.damage(0.3,p);
        						le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40,1,false,false));
        					}
        				}
                    });
                }
            }, 10); 
			
		}
	}
	public void hit(ProjectileHitEvent d) 
	{
		if(d.getEntity() instanceof Snowball) 
		{
			Snowball po = (Snowball)d.getEntity();
			if(po.getShooter() instanceof LivingEntity) {
				LivingEntity p = (LivingEntity) po.getShooter();
				if(po.hasMetadata("grenadier")) {
					po.getWorld().playSound(po.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1f, 1f);
					po.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, po.getLocation(), 5,2,2,2);
					po.getWorld().spawnParticle(Particle.SLIME, po.getLocation(), 200,2,2,2);
            		for(Entity e : p.getWorld().getNearbyEntities(po.getLocation(), 2.5, 2.5, 2.5)) {
						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
							LivingEntity le = (LivingEntity)e;
							le.damage(4,p);
						}
                	}
				}
				else if(po.hasMetadata("PoisonGrenadier")) {
					po.getWorld().playSound(po.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1f, 1f);
					po.getWorld().playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 2);
					po.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, po.getLocation(), 5,2,2,2);
					for(int n = 0; n<8; n++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								po.getWorld().spawnParticle(Particle.SLIME, po.getLocation(), 100,2,2,2);
			            		for(Entity e : p.getWorld().getNearbyEntities(po.getLocation(), 2.5, 2.5, 2.5)) {
									if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
										LivingEntity le = (LivingEntity)e;
										le.damage(4,p);
									}
			                	}
			                }
	                	 }, n*3); 														
					}
				}
				else if(po.hasMetadata("Rifleman") && d.getHitEntity()!=null) {
            		Entity e = d.getHitEntity();
					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
						LivingEntity le = (LivingEntity)e;
						le.damage(3.1,p);
					}
				}
				else if(po.hasMetadata("bossmotov")) {
					po.getWorld().playSound(po.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1f, 0.8f);
					po.getWorld().playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 2);
					po.getWorld().spawnParticle(Particle.FLAME, po.getLocation(), 100,2,2,2);
					po.getWorld().spawnParticle(Particle.SLIME, po.getLocation(), 100,2,2,2);
					for(int n = 0; n<8; n++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								po.getWorld().spawnParticle(Particle.FLAME, po.getLocation(), 100,2,2,2);
								po.getWorld().spawnParticle(Particle.SLIME, po.getLocation(), 100,2,2,2);
			            		for(Entity e : p.getWorld().getNearbyEntities(po.getLocation(), 2.5, 2.5, 2.5)) {
									if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
										LivingEntity le = (LivingEntity)e;
										le.damage(2.5,p);
										Holding.holding(null, le, 5l);
									}
			                	}
			                }
	                	 }, n*3); 														
					}
				}
			}
			
		}
	}
	

	public void bowshoot(EntityShootBowEvent ev) 
	{
		if(!ev.getEntity().hasMetadata("poison")) {
			return;
		}
		if(ev.getEntity().hasMetadata("Rifleman")){

			ev.setCancelled(true);
			
		    LivingEntity p = ev.getEntity();
		    
        	p.getWorld().playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_END, 1f, 2f);
			p.getWorld().spawnParticle(Particle.ASH, p.getLocation(), 10);

			for(int i = 0 ; i < 3; i++) {
        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {

	                	p.getWorld().playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1f, 2f);
	                	p.getWorld().playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1f, 2f);
	                	
	                    Snowball ws = (Snowball) p.launchProjectile(Snowball.class);
	                    ws.setItem(new ItemStack(Material.GREEN_DYE));
	                    ws.setShooter(p);
	                    ws.setVelocity(p.getLocation().getDirection().normalize().multiply(1.85));
	    				ws.setMetadata("Rifleman", new FixedMetadataValue(RMain.getInstance(), true));
	                	
	                }
	            }, i*2+10); 
			}

		 }
		else if(ev.getEntity().hasMetadata("MachineGunman")){
			ev.setCancelled(true);
			
		    LivingEntity p = ev.getEntity();
		    Holding.holding(null, p, 80l);
        	p.getWorld().playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_END, 1f, 2f);
        	p.getWorld().playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1f, 2f);
			p.getWorld().spawnParticle(Particle.ASH, p.getLocation(), 30);
			p.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, p.getLocation(), 30);

			for(int i = 0 ; i < 20; i++) {
        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {

	                	p.getWorld().playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1f, 0f);
	                	p.getWorld().playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1f, 0f);
	                	
	                    Snowball ws = (Snowball) p.launchProjectile(Snowball.class);
	                    ws.setItem(new ItemStack(Material.GREEN_DYE));
	                    ws.setShooter(p);
	                    ws.setVelocity(p.getLocation().getDirection().normalize().multiply(1.85));
	    				ws.setMetadata("Rifleman", new FixedMetadataValue(RMain.getInstance(), true));
	                	
	                }
	            }, i+30); 
			}
		}
		else if(ev.getEntity().hasMetadata("poisonboss")){

			ev.setCancelled(true);
			
		    LivingEntity p = ev.getEntity();
		    
        	p.getWorld().playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_END, 1f, 2f);
			p.getWorld().spawnParticle(Particle.ASH, p.getLocation(), 10);
			ArrayList<Location> line = new ArrayList<Location>();
			final Location fl = p.getEyeLocation().clone();



			for(int i = 0 ; i < 6; i++) {
        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	                	p.getWorld().playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1f, 2f);
	                	p.getWorld().playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1f, 2f);
	                    Snowball ws = (Snowball) p.launchProjectile(Snowball.class);
	                    ws.setItem(new ItemStack(Material.GREEN_DYE));
	                    ws.setShooter(p);
	                    ws.setVelocity(p.getLocation().getDirection().normalize().multiply(1.85));
	        			ws.setMetadata("Rifleman", new FixedMetadataValue(RMain.getInstance(), true));
	                }
	            }, i+2); 
			}


	    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			@Override
	        	public void run() 
	            {	
					Location sl = p.getEyeLocation().clone();
					Vector pv = sl.clone().toVector().subtract(fl.toVector()).normalize();
					try {
						pv.checkFinite();
					}
					catch(IllegalArgumentException e) {
						pv = fl.getDirection();
					}

			        for(double d = 0.1; d <= 3; d += 0.1) {
			            Location pl = fl.clone();
						pl.add(pv.clone().normalize().multiply(d));
						if(!pl.getBlock().isPassable()) {
							break;
						}
						line.add(pl);
			        }
			        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2f);
			        
					
			        AtomicInteger j = new AtomicInteger(0);
			    	line.forEach(i -> {
			        	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			 		@Override
			            	public void run() 
			                {	
			         			try {
				         			p.teleport(i);
			    				}
			    				catch(IllegalArgumentException e) {
			    					Location tl = p.getLocation().clone().add(fl.clone().getDirection().multiply(0.1));
			    					if(tl.getBlock().isPassable()) {
					         			p.teleport(tl);
			    					}
			    				}
								p.getWorld().spawnParticle(Particle.SLIME, p.getLocation(), 6, 0.1,0.1,0.1,0);
			                	p.setFallDistance(0);
				            }
			        	   }, j.incrementAndGet()/50); 
					 });

	            }
	    	   }, 9); 

		 }
	}


	public void drug(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity() instanceof Zombie &&d.getDamager().hasMetadata("poison")&& !d.isCancelled()) 
		{
			Zombie p = (Zombie) d.getEntity();
            AreaEffectCloud cloud = (AreaEffectCloud) p.getWorld().spawnEntity(p.getLocation(), EntityType.AREA_EFFECT_CLOUD);
			cloud.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
            cloud.setRadius(2.5f);
            cloud.setSource(p);
            cloud.setSilent(false);
            cloud.setColor(Color.OLIVE);
            cloud.setRadiusPerTick(0.05f);
            cloud.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION,60,2,false,false), false);
            cloud.addCustomEffect(new PotionEffect(PotionEffectType.GLOWING,60,2,false,false), false);
            cloud.setDuration(40);
		}
	}
	
	

	public void poisoness(EntityDamageByEntityEvent d) 
	{
		if((d.getDamager() instanceof LivingEntity) && d.getEntity() instanceof LivingEntity &&d.getDamager().hasMetadata("poison")&& !d.isCancelled()) 
		{
			LivingEntity le = (LivingEntity) d.getEntity();
			le.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 40,1,false,false));
		}
	}
	

	public void poisoncreep(EntityTargetEvent d) 
	{
		Entity cc = d.getEntity();
		
		if(cc instanceof Creeper) {
			Creeper p = (Creeper)cc;
			if(p.hasMetadata("poison")) {
				for(int i = 0 ; i < 10; i++) {
	        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {

		    				p.teleport(p.getLocation().clone().add(p.getLocation().clone().getDirection().normalize().multiply(0.2)));
		                }
		            }, i); 
				}
			}
		}
		if(cc instanceof Evoker) {
			Evoker p = (Evoker)cc;
			if(p.hasMetadata("poison")) {
				for(int i = 0 ; i < 10; i++) {
	        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {

		    				p.teleport(p.getLocation().clone().add(p.getLocation().clone().getDirection().normalize().multiply(0.2)));
		                }
		            }, i); 
				}
			}
		}
	}


	public void motovthrow(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 4;
		if(d.getEntity() instanceof Skeleton && d.getEntity().hasMetadata("poisonboss") && throwable.containsKey(d.getEntity().getUniqueId())) 
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
	                Holding.holding(null, p, 20l);

                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1.0f, 2f);
                    
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                    ItemStack is = new ItemStack(Material.SPLASH_POTION);
		                    ItemMeta imeta = is.getItemMeta();
		                    PotionMeta pometa = (PotionMeta)imeta;
		                    pometa.setColor(Color.GREEN);
		                    is.setItemMeta(pometa);
		                    for(double an = -Math.PI/3; an<=Math.PI/3; an += Math.PI/9) {
			                    ThrownPotion thr = (ThrownPotion) p.launchProjectile(ThrownPotion.class);
			                    thr.setShooter(p);
			                    thr.setBounce(false);
			                    thr.setItem(is);
			                    thr.setVelocity(p.getLocation().getDirection().rotateAroundY(an));
			                    thr.setFireTicks(55);
			                    thr.setMetadata("PoisonGrenadier", new FixedMetadataValue(RMain.getInstance(), true));	                    	
		                    }
		                    npable.put(p.getUniqueId(), true);
		                }
                    }, 20); 
					rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {

            	throwable.remove(p.getUniqueId());
                Holding.holding(null, p, 20l);

                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1.0f, 2f);
                
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                    ItemStack is = new ItemStack(Material.SPLASH_POTION);
	                    ItemMeta imeta = is.getItemMeta();
	                    PotionMeta pometa = (PotionMeta)imeta;
	                    pometa.setColor(Color.GREEN);
	                    is.setItemMeta(pometa);
	                    for(double an = -Math.PI/3; an<=Math.PI/3; an += Math.PI/9) {
		                    ThrownPotion thr = (ThrownPotion) p.launchProjectile(ThrownPotion.class);
		                    thr.setShooter(p);
		                    thr.setBounce(false);
		                    thr.setItem(is);
		                    thr.setVelocity(p.getLocation().getDirection().rotateAroundY(an));
		                    thr.setFireTicks(55);
		                    thr.setMetadata("poisonboss throw"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	                    	
	                    }
	                    npable.put(p.getUniqueId(), true);
	                }
                }, 20); 
				rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	        }
		}					
	}
	

	@SuppressWarnings("unchecked")
	public void charge(EntityDamageByEntityEvent ev) 
	{
		if(ev.getEntity().hasMetadata("poisonboss")) 
		{
			final Skeleton p = (Skeleton)ev.getEntity();
	        

			if(p.hasMetadata("raid")) {
				if(!OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")) {
					return;
				}

				final Location tl = OverworldRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get().getLocation().clone().add(0,0.2,0);
				charge(p,tl);
			}
			else if(p.hasMetadata("summoned")) {
				final Object hero = getherotype(gethero(p));
				if(hero instanceof Player) {
					final Player hp = (Player)hero;
					charge(p,hp.getLocation().clone().add(0,0.2,0));
				}
				else if(hero instanceof HashSet) {
					HashSet<Player> par = (HashSet<Player>) hero;
					charge(p,par.stream().findAny().get().getLocation().clone().add(0,0.2,0));
				}

			}
		}
	}


	private HashMap<UUID, Integer> hookt1 = new HashMap<UUID, Integer>();
	
	final private void charge(Skeleton p, Location tl) {
		if(npable.containsKey(p.getUniqueId())) {
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

                final Location pfl = p.getLocation().clone();
                
                final Vector pv = tl.clone().toVector().subtract(pfl.clone().toVector()).normalize();
                

        		p.swingMainHand();
				p.getWorld().playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, 1.0f, 0f);
				p.getWorld().playSound(p.getLocation(), Sound.BLOCK_SCULK_CHARGE, 1.0f, 0f);
	 			p.getWorld().spawnParticle(Particle.SCULK_CHARGE ,p.getLocation(), 200, 0.2,0.2,0.2,1,0.5f);
     			Holding.holding(null, p, 10l);

				Location pl = pfl.clone();

	        	int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	        		@Override
	            	public void run() 
	                {	
            			if(p.isDead()) {
            				return;
            			}
            			p.getWorld().playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 0f);
                        AreaEffectCloud cloud = (AreaEffectCloud) p.getWorld().spawnEntity(p.getLocation(), EntityType.AREA_EFFECT_CLOUD);
            			cloud.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                        cloud.setRadius(2.5f);
                        cloud.setSource(p);
                        cloud.setSilent(false);
                        cloud.setColor(Color.OLIVE);
                        cloud.setRadiusPerTick(0.05f);
                        cloud.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION,60,2,false,false), false);
                        cloud.setDuration(40);
                        
                        final Location cl = cloud.getLocation().clone();
                        
	        			pl.add(pv.clone().normalize().multiply(0.65));
	        			
	        			if(pl.getBlock().isPassable()) {
							p.teleport(pl);
	        			}

	                    for(int i = 0; i<4; i++) {
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                		@Override
			                	public void run() 
					                {	
	    	                    for(Entity e : cloud.getWorld().getNearbyEntities(cl,2,2,2)) {
		    							if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
		    								LivingEntity le = (LivingEntity)e;
		    								le.damage(3.5,p);
		    								Holding.holding(null, le, 20l);
		    							}
		    	                    }
					            }
		                	   }, i*10); 
	                    }
	        			
	                }
	        	},10,2);
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
             			throwable.put(p.getUniqueId(), 1);
		            }
                }, 46); 
                
				rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
            }
        }
        else 
        {

            final Location pfl = p.getLocation().clone();
            
            final Vector pv = tl.clone().toVector().subtract(pfl.clone().toVector()).normalize();
            

    		p.swingMainHand();
			p.getWorld().playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, 1.0f, 0f);
			p.getWorld().playSound(p.getLocation(), Sound.BLOCK_SCULK_CHARGE, 1.0f, 0f);
 			p.getWorld().spawnParticle(Particle.SCULK_CHARGE ,p.getLocation(), 200, 0.2,0.2,0.2,1,0.5f);
 			Holding.holding(null, p, 10l);

			Location pl = pfl.clone();

        	int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
        		@Override
            	public void run() 
                {	
        			if(p.isDead()) {
        				return;
        			}
        			p.getWorld().playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 0f);
                    AreaEffectCloud cloud = (AreaEffectCloud) p.getWorld().spawnEntity(p.getLocation(), EntityType.AREA_EFFECT_CLOUD);
        			cloud.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    cloud.setRadius(2.5f);
                    cloud.setSource(p);
                    cloud.setSilent(false);
                    cloud.setColor(Color.OLIVE);
                    cloud.setRadiusPerTick(0.05f);
                    cloud.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION,60,2,false,false), false);
                    cloud.setDuration(40);

                    final Location cl = cloud.getLocation().clone();
                    
        			pl.add(pv.clone().normalize().multiply(0.65));
        			
        			if(pl.getBlock().isPassable()) {
						p.teleport(pl);
        			}

                    for(int i = 0; i<4; i++) {
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                		@Override
		                	public void run() 
				                {	
    	                    for(Entity e : cloud.getWorld().getNearbyEntities(cl,2,2,2)) {
	    							if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
	    								LivingEntity le = (LivingEntity)e;
	    								le.damage(3.5,p);
	    								Holding.holding(null, le, 20l);
	    							}
	    	                    }
				            }
	                	   }, i*10); 
                    }
        			
                }
        	},10,2);
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
         			throwable.put(p.getUniqueId(), 1);
	            }
            }, 46); 
			rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		}
	}
	
	public void napalm(EntityDamageByEntityEvent d) 
	{
		if((d.getEntity() instanceof Skeleton) && d.getEntity().hasMetadata("poisonboss")) 
		{
			Skeleton p = (Skeleton)d.getEntity();
			int sec = 8;
	        

			if(p.getTarget() == null|| !(p.getTarget() instanceof Player)||p.hasMetadata("failed") || !npable.containsKey(p.getUniqueId())) {
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
		                	
			                Holding.holding(null, p, 10l);

		                    p.getWorld().playSound(ptl, Sound.ENTITY_TNT_PRIMED, 1.0f, 2f);
			            	p.getWorld().playSound(ptl, Sound.BLOCK_FIRE_AMBIENT, 1.0f, 2f);
							ItemStack is = new ItemStack(Material.GREEN_GLAZED_TERRACOTTA);
							Item solid = ptl.getWorld().dropItem(ptl, is);
							solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							solid.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							solid.setGlowing(true);
							solid.setPickupDelay(9999);
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
					                Firework fr = (Firework) p.getWorld().spawnEntity(solid.getLocation(), EntityType.FIREWORK);
				                    fr.setShotAtAngle(true);
				                    fr.setShooter(p);
				                    fr.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				            	
				        			FireworkEffect effect = FireworkEffect.builder()
				                                .with(Type.BALL_LARGE)
				                                .withColor(Color.GREEN, Color.RED, Color.LIME)
				                                .flicker(true)
				                                .trail(true)
				                                .build();
				                    FireworkMeta meta = fr.getFireworkMeta();
				                    meta.setPower(0);
				                    meta.addEffect(effect);
				                    fr.setFireworkMeta(meta);
				                    fr.detonate();
				                    
				                	solid.getWorld().spawnParticle(Particle.FLAME, solid.getLocation(), 50,1,1,1,1);
				                	solid.getWorld().spawnParticle(Particle.SNEEZE, solid.getLocation(), 50,1,1,1,1);
				                	solid.getWorld().spawnParticle(Particle.SLIME, solid.getLocation(), 50,1,1,1,1);
				                	solid.getWorld().spawnParticle(Particle.BLOCK_CRACK, solid.getLocation(), 600,4.5,4.5,4.5,1,Material.GREEN_GLAZED_TERRACOTTA.createBlockData());
				                    p.getWorld().playSound(solid.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 0);
				                    p.getWorld().playSound(solid.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 0);
				            		for(Entity e : p.getWorld().getNearbyEntities(solid.getLocation(), 4.5, 4.5, 4.5)) {
										if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
											LivingEntity le = (LivingEntity)e;
											le.damage(7,p);
											le.setFireTicks(50);
										}
				                	}
				                    solid.remove();

				                	npable.remove(p.getUniqueId());
				                }
		                    }, 35); 
		                    
	             			
							rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {

	                	
		                Holding.holding(null, p, 10l);

	                    p.getWorld().playSound(ptl, Sound.ENTITY_TNT_PRIMED, 1.0f, 2f);
		            	p.getWorld().playSound(ptl, Sound.BLOCK_FIRE_AMBIENT, 1.0f, 2f);
						ItemStack is = new ItemStack(Material.GREEN_GLAZED_TERRACOTTA);
						Item solid = ptl.getWorld().dropItem(ptl, is);
						solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						solid.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						solid.setGlowing(true);
						solid.setPickupDelay(9999);
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
				                Firework fr = (Firework) p.getWorld().spawnEntity(solid.getLocation(), EntityType.FIREWORK);
			                    fr.setShotAtAngle(true);
			                    fr.setShooter(p);
			                    fr.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			            	
			        			FireworkEffect effect = FireworkEffect.builder()
			                                .with(Type.BALL_LARGE)
			                                .withColor(Color.GREEN, Color.RED, Color.LIME)
			                                .flicker(true)
			                                .trail(true)
			                                .build();
			                    FireworkMeta meta = fr.getFireworkMeta();
			                    meta.setPower(0);
			                    meta.addEffect(effect);
			                    fr.setFireworkMeta(meta);
			                    fr.detonate();
			                    
			                	solid.getWorld().spawnParticle(Particle.FLAME, solid.getLocation(), 50,1,1,1,1);
			                	solid.getWorld().spawnParticle(Particle.SNEEZE, solid.getLocation(), 50,1,1,1,1);
			                	solid.getWorld().spawnParticle(Particle.SLIME, solid.getLocation(), 50,1,1,1,1);
			                	solid.getWorld().spawnParticle(Particle.BLOCK_CRACK, solid.getLocation(), 600,4.5,4.5,4.5,1,Material.GREEN_GLAZED_TERRACOTTA.createBlockData());
			                    p.getWorld().playSound(solid.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 0);
			                    p.getWorld().playSound(solid.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 0);
			            		for(Entity e : p.getWorld().getNearbyEntities(solid.getLocation(), 4.5, 4.5, 4.5)) {
									if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
										LivingEntity le = (LivingEntity)e;
										le.damage(7,p);
										le.setFireTicks(50);
									}
			                	}
			                    solid.remove();

			                	npable.remove(p.getUniqueId());
			                }
	                    }, 35); 
	                    
						rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}

	
	
}
