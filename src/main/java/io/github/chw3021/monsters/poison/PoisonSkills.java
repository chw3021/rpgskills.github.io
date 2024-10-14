package io.github.chw3021.monsters.poison;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
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
import org.bukkit.entity.Mob;
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

import com.google.common.collect.HashMultimap;
import com.google.common.util.concurrent.AtomicDouble;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.OverworldRaids;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;



public class PoisonSkills extends Summoned{

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
	private HashMap<UUID, Long> rb6cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb8cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> shcooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> aicooldown = new HashMap<UUID, Long>();
	

	
	private static final PoisonSkills instance = new PoisonSkills ();
	public static PoisonSkills getInstance()
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
					po.getWorld().spawnParticle(Particle.EXPLOSION, po.getLocation(), 5,2,2,2);
					po.getWorld().spawnParticle(Particle.ITEM_SLIME, po.getLocation(), 200,2,2,2);
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
					po.getWorld().spawnParticle(Particle.EXPLOSION, po.getLocation(), 5,2,2,2);
					for(int n = 0; n<8; n++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								po.getWorld().spawnParticle(Particle.ITEM_SLIME, po.getLocation(), 100,2,2,2);
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
					po.getWorld().spawnParticle(Particle.ITEM_SLIME, po.getLocation(), 100,2,2,2);
					for(int n = 0; n<8; n++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								po.getWorld().spawnParticle(Particle.FLAME, po.getLocation(), 100,2,2,2);
								po.getWorld().spawnParticle(Particle.ITEM_SLIME, po.getLocation(), 100,2,2,2);
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
								p.getWorld().spawnParticle(Particle.ITEM_SLIME, p.getLocation(), 6, 0.1,0.1,0.1,0);
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
            cloud.addCustomEffect(new PotionEffect(PotionEffectType.NAUSEA,60,2,false,false), false);
            cloud.addCustomEffect(new PotionEffect(PotionEffectType.SLOWNESS,60,2,false,false), false);
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


	private HashMap<UUID, Integer> throwable = new HashMap<UUID, Integer>();
	public void motovthrow(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 4;
		if(d.getEntity() instanceof Skeleton && d.getEntity().hasMetadata("poisonboss") && throwable.containsKey(d.getEntity().getUniqueId())) 
		{
			Skeleton p = (Skeleton)d.getEntity();
			if((p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2);
                d.setCancelled(true);
                ordealable.put(p.getUniqueId(), true);
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
	                Holding.holding(null, p, 20l);

                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1.0f, 2f);
                    
                    int t =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                    ItemStack is = new ItemStack(Material.SPLASH_POTION);
		                    ItemMeta imeta = is.getItemMeta();
		                    PotionMeta pometa = (PotionMeta)imeta;
		                    pometa.setColor(Color.GREEN);
		                    is.setItemMeta(pometa);
		                    
		                    if(p.hasMetadata("ruined")) {
		                    	AtomicDouble ad = new AtomicDouble();
								for(int i = 0; i<54; i++) {
				                    int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					             		@Override
					                	public void run() 
						                {
					                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SPLASH_POTION_THROW, 0.7f, 0f);
						                    ThrownPotion thr = (ThrownPotion) p.launchProjectile(ThrownPotion.class);
						                    thr.setMetadata("stuff"+gethero(p), new FixedMetadataValue(RMain.getInstance(), true));
						                    thr.setShooter(p);
						                    thr.setItem(is);
						                    thr.setVelocity(p.getLocation().getDirection().rotateAroundY(ad.getAndAdd(Math.PI/9)));
						                    thr.setFireTicks(55);
						                    thr.setMetadata("PoisonGrenadier", new FixedMetadataValue(RMain.getInstance(), true));	 
							            }
			                	   	}, i);
				                    ordt.put(gethero(p), t);
								}
		                    }
		                    
		                    for(double an = -Math.PI/3; an<=Math.PI/3; an += Math.PI/9) {
			                    ThrownPotion thr = (ThrownPotion) p.launchProjectile(ThrownPotion.class);
			                    thr.setMetadata("stuff"+gethero(p), new FixedMetadataValue(RMain.getInstance(), true));
			                    thr.setShooter(p);
			                    thr.setItem(is);
			                    thr.setVelocity(p.getLocation().getDirection().rotateAroundY(an));
			                    thr.setFireTicks(55);
			                    thr.setMetadata("PoisonGrenadier", new FixedMetadataValue(RMain.getInstance(), true));	                    	
		                    }
		                    npable.put(p.getUniqueId(), true);
		                }
                    }, 20); 
                    ordt.put(gethero(p), t);
					rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {

            	throwable.remove(p.getUniqueId());
                Holding.holding(null, p, 20l);

                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1.0f, 2f);
                
                int t =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                    ItemStack is = new ItemStack(Material.SPLASH_POTION);
	                    ItemMeta imeta = is.getItemMeta();
	                    PotionMeta pometa = (PotionMeta)imeta;
	                    pometa.setColor(Color.GREEN);
	                    is.setItemMeta(pometa);
	                    
	                    if(p.hasMetadata("ruined")) {
	                    	AtomicDouble ad = new AtomicDouble();
							for(int i = 0; i<54; i++) {
			                    int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				             		@Override
				                	public void run() 
					                {
				                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SPLASH_POTION_THROW, 0.7f, 0f);
					                    ThrownPotion thr = (ThrownPotion) p.launchProjectile(ThrownPotion.class);
					                    thr.setMetadata("stuff"+gethero(p), new FixedMetadataValue(RMain.getInstance(), true));
					                    thr.setShooter(p);
					                    thr.setItem(is);
					                    thr.setVelocity(p.getLocation().getDirection().rotateAroundY(ad.getAndAdd(Math.PI/9)));
					                    thr.setFireTicks(55);
					                    thr.setMetadata("PoisonGrenadier", new FixedMetadataValue(RMain.getInstance(), true));	 
						            }
		                	   	}, i);
			                    ordt.put(gethero(p), t);
							}
	                    }
	                    
	                    for(double an = -Math.PI/3; an<=Math.PI/3; an += Math.PI/9) {
		                    ThrownPotion thr = (ThrownPotion) p.launchProjectile(ThrownPotion.class);
		                    thr.setMetadata("stuff"+gethero(p), new FixedMetadataValue(RMain.getInstance(), true));
		                    thr.setShooter(p);
		                    thr.setItem(is);
		                    thr.setVelocity(p.getLocation().getDirection().rotateAroundY(an));
		                    thr.setFireTicks(55);
		                    thr.setMetadata("PoisonGrenadier", new FixedMetadataValue(RMain.getInstance(), true));	                    	
	                    }
	                    npable.put(p.getUniqueId(), true);
	                }
                }, 20); 
                ordt.put(gethero(p), t);
				rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	        }
		}					
	}

	private HashMap<UUID, Boolean> shotable = new HashMap<UUID, Boolean>();
	
	final private void multiShot(LivingEntity p) {

    	final World w = p.getWorld();
        Holding.holding(null, p, 40l);
		Location pl = p.getEyeLocation().clone();
		w.playSound(pl, Sound.BLOCK_SCULK_CHARGE, 1.0f, 2f);
		w.playSound(pl, Sound.BLOCK_PISTON_CONTRACT, 1.0f, 0f);
		w.playSound(pl, Sound.ITEM_ARMOR_EQUIP_NETHERITE, 1.0f, 0f);
		w.spawnParticle(Particle.LARGE_SMOKE, pl, 150, 2,2,2);
		w.spawnParticle(Particle.GUST, pl, 150, 2,2,2);
		int i = 0;
		int count = p.hasMetadata("ruined") ? 100 : 50;
		for(; i<count; i++) {
            int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
            	public void run() 
                {
                	w.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 0.8f, 0f);
                	w.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 0.8f, 2f);
					int count = p.hasMetadata("ruined") ? 80 : 30;

                	for(int a = 0; a <count; a ++) {
	                	
	                	Arrow ar = w.spawnArrow(pl.clone(), pl.clone().getDirection(), 1.85f, 15);
	                	Vector arv = ar.getVelocity().clone();
	                	ar.remove();
	                	
	                    Snowball ws = (Snowball) p.launchProjectile(Snowball.class);
	                    ws.setItem(new ItemStack(Material.GREEN_DYE));
	                    ws.setMetadata("stuff"+gethero(p), new FixedMetadataValue(RMain.getInstance(), true));
	                    ws.setShooter(p);
	                    ws.setVelocity(arv);
	        			ws.setMetadata("Rifleman", new FixedMetadataValue(RMain.getInstance(), true));
                	}
                	
	            }
    	   	}, i+40);
            ordt.put(gethero(p), t);
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
            	shotable.remove(p.getUniqueId());
            }
	   	}, 60+i);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			chargable.putIfAbsent(p.getUniqueId(), true);
            }
	   	}, 80+i);
	}
	

	public void multiShot(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("poisonboss") && (d.getEntity() instanceof Mob)) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 8;
	
	
			if(p.hasMetadata("failed") || !shotable.containsKey(p.getUniqueId())) {
				return;
			}
			if((p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2);
	            d.setCancelled(true);
	            ordealable.put(p.getUniqueId(), true);
				return;
			}
					if(shcooldown.containsKey(p.getUniqueId()))
		            {
		                long timer = (shcooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		                if(!(timer < 0))
		                {
		                }
		                else 
		                {
		                	shcooldown.remove(p.getUniqueId()); // removing player from HashMap
		                	multiShot(p);
		                	shcooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {
		            	multiShot(p);
		            	shcooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}


	private HashMap<UUID, Boolean> aimable = new HashMap<UUID, Boolean>();
	
	final private void aiming(LivingEntity p) {

    	final World w = p.getWorld();
        Holding.holding(null, p, 40l);
		Location pfl = p.getEyeLocation().clone();
		w.playSound(pfl, Sound.BLOCK_SCULK_SHRIEKER_SHRIEK, 0.5f, 0f);
		w.playSound(pfl, Sound.BLOCK_PISTON_CONTRACT, 1.0f, 0f);
		w.playSound(pfl, Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 0f);
		w.spawnParticle(Particle.WHITE_ASH, pfl, 150, 2,2,2);
		w.spawnParticle(Particle.WHITE_SMOKE, pfl, 150, 2,2,2);
		w.spawnParticle(Particle.PORTAL, pfl, 150, 2,2,2);
		

        for(Player pe : OverworldRaids.getheroes(p)) {
    		pe.hideEntity(RMain.getInstance(), Holding.ale(p));
        }
		
        p.teleport(p.getLocation().clone().add(20, 0.3, 20));
        
		int t =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
                final ArrayList<Location> line1 = new ArrayList<Location>();
                final Location pl = p.getEyeLocation().clone();
                final Location pel = OverworldRaids.getheroes(p).stream().findAny().get().getEyeLocation().clone();
                
                final Vector v = pel.toVector().subtract(pl.toVector()).clone().normalize();
                
                for(double d = 0.1; d <= pl.distance(pel)*1.2; d += 0.5) {
        			line1.add(pl.clone().add(v.multiply(d)));
                }

                for(final Location l : line1) {
                	p.getWorld().spawnParticle(Particle.FLASH, l, 1,0.1,0.1,0.1,0);
                	p.getWorld().playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1, 2);
                }
                
        		int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                    @Override
                    public void run() 
                    {
                        for(Player pe : OverworldRaids.getheroes(p)) {
                    		pe.showEntity(RMain.getInstance(), Holding.ale(p));
                        }
                		
                    	p.getWorld().playSound(p.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 1, 0);
                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 2);

                        for(final Location l : line1) {
                        	p.getWorld().spawnParticle(Particle.BLOCK, l,10, 0.5,0.5,0.5,0, getBd(Material.DEEPSLATE_IRON_ORE));

        					for(Entity e : p.getWorld().getNearbyEntities(l,0.8, 0.8, 0.8)) {
        						if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
        							LivingEntity le = (LivingEntity)e;
        							le.damage(10,p);
        						}
        					}
                        }
                    }
        		}, 10);
                ordt.put(gethero(p), t);
            }
		}, 40);
        ordt.put(gethero(p), t);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
            	aimable.remove(p.getUniqueId());
            }
	   	}, 60);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			throwable.putIfAbsent(p.getUniqueId(), 1);
            }
	   	}, 80);
	}
	
	public void aiming(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("poisonboss") && (d.getEntity() instanceof Mob)) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 8;


			if(p.hasMetadata("failed") || !aimable.containsKey(p.getUniqueId())) {
				return;
			}
			if((p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2);
                d.setCancelled(true);
                ordealable.put(p.getUniqueId(), true);
				return;
			}
					if(aicooldown.containsKey(p.getUniqueId()))
		            {
		                long timer = (aicooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		                if(!(timer < 0))
		                {
		                }
		                else 
		                {
		                	aicooldown.remove(p.getUniqueId()); // removing player from HashMap
		                	aiming(p);
		                	aicooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {

		            	aiming(p);
	                	aicooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}

	

	private HashMap<UUID, Boolean> chargable = new HashMap<UUID, Boolean>();
	@SuppressWarnings("unchecked")
	public void charge(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("poisonboss")) 
		{
			final Skeleton p = (Skeleton)d.getEntity();

			if((p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2);
                d.setCancelled(true);
                ordealable.put(p.getUniqueId(), true);
				return;
			}

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
	
	final private void chargeStart(Skeleton p, Location tl) {

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
    			cloud.setMetadata("stuff"+gethero(p), new FixedMetadataValue(RMain.getInstance(), true));
                cloud.setRadius(2.5f);
                cloud.setSource(p);
                cloud.setSilent(false);
                cloud.setColor(Color.OLIVE);
                cloud.setRadiusPerTick(0.05f);
                cloud.addCustomEffect(new PotionEffect(PotionEffectType.NAUSEA,60,2,false,false), false);
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
        ordt.put(gethero(p), task);
        
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
     			aimable.put(p.getUniqueId(), true);
            }
        }, 46); 
	}
	
	final private void charge(Skeleton p, Location tl) {

		if(p.hasMetadata("failed") || !chargable.containsKey(p.getUniqueId())) {
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
            	chargeStart(p, tl);
                
				rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
            }
        }
        else 
        {
        	chargeStart(p, tl);
			rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		}
	}

	private HashMap<UUID, Boolean> npable = new HashMap<UUID, Boolean>();
	
	final private void napalm(Location ptl, LivingEntity p) {
		p.getWorld().playSound(ptl, Sound.ENTITY_TNT_PRIMED, 1.0f, 2f);
    	p.getWorld().playSound(ptl, Sound.BLOCK_FIRE_AMBIENT, 1.0f, 2f);
		ItemStack is = new ItemStack(Material.GREEN_GLAZED_TERRACOTTA);
		Item solid = ptl.getWorld().dropItem(ptl, is);
		solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		solid.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		solid.setMetadata("stuff"+gethero(p), new FixedMetadataValue(RMain.getInstance(), true));
		solid.setGlowing(true);
		solid.setPickupDelay(9999);
        int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
                Firework fr = (Firework) p.getWorld().spawnEntity(solid.getLocation(), EntityType.FIREWORK_ROCKET);
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
            	solid.getWorld().spawnParticle(Particle.ITEM_SLIME, solid.getLocation(), 50,1,1,1,1);
            	solid.getWorld().spawnParticle(Particle.BLOCK, solid.getLocation(), 600,4.5,4.5,4.5,1,getBd(Material.GREEN_GLAZED_TERRACOTTA));
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
        ordt.put(gethero(p), t);
	}
	
	public void napalm(EntityDamageByEntityEvent d) 
	{
		if((d.getEntity() instanceof Skeleton) && d.getEntity().hasMetadata("poisonboss")) 
		{
			Skeleton p = (Skeleton)d.getEntity();
			int sec = 8;
	        

			if((p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2);
                d.setCancelled(true);
                ordealable.put(p.getUniqueId(), true);
				return;
			}
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

			                napalm(ptl, p);
		                    
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                 		@Override
		                    	public void run() 
		                        {	
		                 			shotable.put(p.getUniqueId(), true);
		        	            }
		                    }, 46); 
		                    
	             			
							rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {

	                	
		                Holding.holding(null, p, 10l);

		                napalm(ptl, p);
	                    
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                 		@Override
	                    	public void run() 
	                        {	
	                 			shotable.put(p.getUniqueId(), true);
	        	            }
	                    }, 46); 
	                    
						rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}

	public static HashMap<UUID, Integer> count = new HashMap<UUID, Integer>();
	private HashMap<UUID, Boolean> ordealable = new HashMap<UUID, Boolean>();
	public static HashMap<UUID, Boolean> ordeal = new HashMap<UUID, Boolean>();//poisonBoss 패턴 시작 여부 저장

	private HashMultimap<UUID, UUID> pair = HashMultimap.create();//ArmorStands uuid 저장
	public HashMap<UUID, Integer> ast = new HashMap<UUID, Integer>();//ArmorStands damage 태스크 저장
	
	final private void asSpawn(Player pe, String rn, LivingEntity p) {
		HashSet<Location> ls = new HashSet<>();
		final Location fl = pe.getLocation().clone();
		ls.add(fl.clone().add(0, 2.5, 0));
		ls.add(fl.clone().add(0, -1, 0));
		ls.add(fl.clone().add(1, 0, 0));
		ls.add(fl.clone().add(-1, 0, 0));
		ls.add(fl.clone().add(0, 0, 1));
		ls.add(fl.clone().add(0, 0, -1));
		
		final World w = fl.getWorld();
		
		ls.forEach(l ->{
			w.spawn(l, ArmorStand.class, newmob -> {

				newmob.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
				newmob.setGravity(false);
        		newmob.setCustomNameVisible(false);
        		newmob.setInvulnerable(true);
        		newmob.setRemoveWhenFarAway(false);
        		newmob.setGravity(false);
        		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), rn));
        		newmob.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
        		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
        		newmob.setAI(false);
            	pair.put(pe.getUniqueId(), newmob.getUniqueId());
				
    			int t2 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
                    @Override
                    public void run() {
	            		
	            		if(Holding.ale(pe).isDead()) {
	            			pair.get(pe.getUniqueId()).forEach(as ->{
	                        	Bukkit.getScheduler().cancelTask(ast.get(as));
	                        	ast.remove(as);
	                        	if(Bukkit.getEntity(as) != null) {
		                        	Bukkit.getEntity(as).remove();
	                        	}
	            			});
	            		}
	            		
	            		for(Entity e : p.getWorld().getNearbyEntities(newmob.getLocation().clone(), 1, 1, 1)) {
							if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
								Player le = (Player)e;
								le.setHealth(0);
							}
	                	}
                    }
                }, 0,1);
    			ordt.put(rn, t2);
    			ast.put(newmob.getUniqueId(), t2);
    			
    			int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                    @Override
                    public void run() {

                    	Bukkit.getScheduler().cancelTask(ast.get(newmob.getUniqueId()));
                    	ast.remove(newmob.getUniqueId());
                    	Holding.ale(newmob).remove();
                    }
                }, 50); 
    			ordt.put(rn, t1);
			});
		});
		
	}

	final private void countDown(LivingEntity p) {
		String rn = p.getMetadata("raid").get(0).asString();
		Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());
        for(Player pe : OverworldRaids.getheroes(p)) {
            napalm(pe.getLocation(), p);
            
			for(int i = 0; i <3; i++) {
				AtomicInteger j = new AtomicInteger(3);
                int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	                	pe.playSound(p, Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 0.3f);
	            		pe.sendTitle(ChatColor.DARK_GREEN+""+j.getAndDecrement(),ChatColor.BOLD+"",10,20, 10);
	                }
	            }, i*20); 	 
				ordt.put(rn, t1);                   	
            }
			int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	pe.playSound(p, Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 0);
            		pe.sendTitle(ChatColor.DARK_RED+"■",ChatColor.BOLD+"",10,20, 10);
            		asSpawn(pe, rn, p);
                }
            }, 65); 
			ordt.put(rn, t1);  
        }
	}

	final private void ordeal(LivingEntity p, EntityDamageByEntityEvent d) {
		String rn = p.getMetadata("raid").get(0).asString();
        if(ordt.containsKey(rn)) {
        	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        	ordt.removeAll(rn);
        }
        ordeal.put(p.getUniqueId(), true);
        Location rl = OverworldRaids.getraidloc(p).clone();
		p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2);
        d.setCancelled(true);
    	p.teleport(rl.clone().add(0, 1, 1));
        Holding.holding(null, p, 671l);
        Holding.untouchable(p, 671l);
        for(Player pe : OverworldRaids.getheroes(p)) {
			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		pe.sendMessage(ChatColor.BOLD+"종말론자: 게임을 시작한다.");
			}
			else {
        		pe.sendMessage(ChatColor.BOLD+"TheApocalyptic: Let's Play The Game.");
			}
    		pe.teleport(rl.clone().add(35, 2, 35));
    		Holding.invur(pe, 40l);
        }
        int t1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {

				for(int i = 0; i <5; i++) {
                    int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	countDown(p);
		                }
		            }, i*120); 	 
					ordt.put(rn, t1);                   	
                }
            }
        }, 20);
		ordt.put(rn, t1);
		
        int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
				Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(newmob -> {
                	Bukkit.getScheduler().cancelTask(ast.get(newmob.getUniqueId()));
                	ast.remove(newmob.getUniqueId());
                	Holding.ale(newmob).remove();
				});

        		if(ordt.containsKey(rn)) {
        			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        		}
        		
        		ordeal.remove(p.getUniqueId());
                for(Player pe : OverworldRaids.getheroes(p)) {
        			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
                		pe.sendMessage(ChatColor.BOLD+"종말론자: 게임 오버!!!");
        			}
        			else {
                		pe.sendMessage(ChatColor.BOLD+"TheApocalyptic: GAME OVER!!!");
        			}
            		Holding.invur(pe, 60l);
        			p.getWorld().playSound(pe.getLocation(), Sound.ENTITY_TNT_PRIMED, 1, 0);
            		pe.setHealth(0);
            	}
                rb6cooldown.remove(p.getUniqueId());
            }
        }, 671);
		ordt.put(rn, t3);
	}
	

	final private void bossfailed(LivingEntity p, String rn) {
		if(ordt.containsKey(rn)) {
			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        	ordt.removeAll(rn);
		}
		ordeal.remove(p.getUniqueId());
    	p.playHurtAnimation(0);
    	Holding.reset(Holding.ale(p));
    	Holding.ale(p).setMetadata("failed", new FixedMetadataValue(RMain.getInstance(),true));
		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
		Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());
        for(Player pe : OverworldRaids.getheroes(p)) {
			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		pe.sendMessage(ChatColor.BOLD+"종말론자: 게임 오버...");
			}
			else {
        		pe.sendMessage(ChatColor.BOLD+"TheApocalyptic: Game Over...");
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
	
	public void playerSuccess(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("poisonboss") && d.getEntity().hasMetadata("ruined")) {

			Skeleton p = (Skeleton)d.getEntity();
			if(ordeal.containsKey(p.getUniqueId())) {
				bossfailed(p, gethero(p));
			}
		}
	}
	    
	
	public void Ordeal(EntityDamageByEntityEvent d) 
	{
	    
		int sec =70;
		if(d.getEntity().hasMetadata("poisonboss") && d.getEntity().hasMetadata("ruined")&& !d.getEntity().hasMetadata("failed")) 
		{
			Skeleton p = (Skeleton)d.getEntity();
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
	
	
	
	
}