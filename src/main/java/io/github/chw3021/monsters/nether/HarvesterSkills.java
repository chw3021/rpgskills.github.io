package io.github.chw3021.monsters.nether;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.EvokerFangs;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Spellcaster.Spell;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpellCastEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.NethercoreRaids;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;



public class HarvesterSkills extends Summoned{

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
	

	
	private static final HarvesterSkills instance = new HarvesterSkills ();
	public static HarvesterSkills getInstance()
	{
		return instance;
	}
	
	
	final private ArrayList<Location> raytrace(Location il, Double dis){
    	ArrayList<Location> line = new ArrayList<Location>();
        for(double d = 0.1; d <= dis; d += 0.3) {
            Location pl = il.clone();
			pl.add(il.getDirection().normalize().multiply(d));
			line.add(pl);
        }
        return line;
	}
	
	public void hit(ProjectileHitEvent d) 
	{
		if(d.getEntity() instanceof Snowball) 
		{
			Snowball po = (Snowball)d.getEntity();
			if(po.getShooter() instanceof LivingEntity) {
				LivingEntity p = (LivingEntity) po.getShooter();
				if(po.hasMetadata("cookedFoods")) {
            		final Location l = d.getHitEntity() != null ? d.getHitEntity().getLocation() : d.getHitBlock().getLocation();

					l.getWorld().spawnParticle(Particle.SMALL_FLAME, l, 50);
					l.getWorld().spawnParticle(Particle.BLOCK, l, 200,2.5,2.5,2.5,getBd(Material.FIRE_CORAL_BLOCK));
					l.getWorld().playSound(l, Sound.BLOCK_CORAL_BLOCK_BREAK, 1f, 1.5f);
					
            		for(Entity e : l.getWorld().getNearbyEntities(l, 2.5, 2.5, 2.5)) {
						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
							LivingEntity le = (LivingEntity)e;
							le.damage(2.5,p);
							Holding.holding(null, le, 5l);
						}
                	}
				}
			}
			
		}
	}
	

	Material[] cookeds = new Material[] {Material.ROTTEN_FLESH, 
			Material.SPIDER_EYE,
			Material.RABBIT_FOOT,
			Material.NETHER_WART, 
			Material.MAGMA_CREAM,
			Material.PHANTOM_MEMBRANE,
			Material.GLISTERING_MELON_SLICE
	};
	
	public void bowshoot(EntityShootBowEvent ev) 
	{
		if(ev.getEntity().hasMetadata("soulboss")){

			ev.setCancelled(true);
			
		    LivingEntity p = ev.getEntity();
		    
        	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_EVOKER_FANGS_ATTACK, 1f, 0f);

			p.getWorld().spawnParticle(Particle.SOUL, p.getLocation(), 10);
			AtomicInteger j = new AtomicInteger();
			
			Location tl = gettargetblock(p,15);
			if(((Mob)p).getTarget() != null) {
				tl = ((Mob)p).getTarget().getLocation();
			}
            String rn = gethero(p);
            
			raytrace(p.getLocation(),tl.distance(p.getLocation())).forEach(l ->{
        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	                	p.swingMainHand();
	                	EvokerFangs ef = (EvokerFangs)p.getWorld().spawnEntity(l, EntityType.EVOKER_FANGS);
	                	ef.setVelocity(l.getDirection().normalize().multiply(1.5));
	                    ef.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    ef.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
	                    ef.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), true));
	                    ef.setMetadata("soul", new FixedMetadataValue(RMain.getInstance(), true));
	                	ef.setOwner(p);
	                	ef.setAttackDelay(0);
	                	ef.setInvulnerable(true);
	                	
	                }
	            }, j.getAndIncrement()*2); 
			});


		 }
	}



	private HashMap<UUID, Integer> cursable = new HashMap<UUID, Integer>();
	public void cursed(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 4;
		if(d.getEntity().hasMetadata("soulboss") && cursable.containsKey(d.getEntity().getUniqueId())) 
		{
			LivingEntity p = (LivingEntity)d.getEntity();
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

	            	cursable.remove(p.getUniqueId());
	                Holding.holding(null, p, 20l);

	                String rn = gethero(p);
					
	                Location tl = gettargetblock(p,4).clone();
	                
					p.getWorld().playSound(tl, Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1f, 0f);
                	p.getWorld().spawnParticle(Particle.FLAME, tl, 500, 4, 1, 4, 0);
                	p.getWorld().spawnParticle(Particle.SMOKE, tl, 500, 4, 1, 4, 0);
                	
                	
                    ArrayList<Location> meats = new ArrayList<>();
                    AtomicInteger j = new AtomicInteger();
                    for(int i=0; i<30; i++) {
			            Random random=new Random();
                    	double number = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
                    	double number2 = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
                    	meats.add(tl.clone().add(number, 1, number2));
                    }
                    
                    meats.forEach(l ->{
						int t= Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	EvokerFangs ef = (EvokerFangs)p.getWorld().spawnEntity(l, EntityType.EVOKER_FANGS);
			                	ef.setVelocity(l.getDirection().normalize().multiply(1.5));
			                    ef.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			                    ef.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
			                    ef.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), true));
			                    ef.setMetadata("soul", new FixedMetadataValue(RMain.getInstance(), true));
			                	ef.setOwner(p);
			                	ef.setAttackDelay(1);
			                	ef.setInvulnerable(true);
			                	p.getWorld().spawnParticle(Particle.SOUL, l, 10, 4, 0.2, 4, 0.2);
			                	p.getWorld().spawnParticle(Particle.ASH, l, 50, 4, 0.2, 4, 0);
			                	p.getWorld().spawnParticle(Particle.LANDING_OBSIDIAN_TEAR, l, 50, 4, 0.2, 4, 0.2);
								p.getWorld().playSound(p.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 0.1f, 2f);
			                	for(Entity e : p.getWorld().getNearbyEntities(l, 4,4,4)) {
			                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
			                			LivingEntity le = (LivingEntity)e;
			                			le.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 200,1,false,false,false));
			                			le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200,2,false,false,false));
			                			le.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 200,2,false,false,false));
										le.damage(3, p);
										
			    						
			                		}
			                	}
			                }
						}, j.incrementAndGet()*2+40);
	                	ordt.put(rn, t);
                    });
					rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {


            	cursable.remove(p.getUniqueId());
                Holding.holding(null, p, 20l);

                String rn = gethero(p);
				
                Location tl = gettargetblock(p,4).clone();
                
				p.getWorld().playSound(tl, Sound.BLOCK_FURNACE_FIRE_CRACKLE, 1f, 0f);
            	p.getWorld().spawnParticle(Particle.FLAME, tl, 500, 4, 1, 4, 0);
            	p.getWorld().spawnParticle(Particle.SMOKE, tl, 500, 4, 1, 4, 0);
            	
            	
                ArrayList<Location> meats = new ArrayList<>();
                AtomicInteger j = new AtomicInteger();
                for(int i=0; i<30; i++) {
		            Random random=new Random();
                	double number = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
                	double number2 = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
                	meats.add(tl.clone().add(number, 1, number2));
                }
                
                meats.forEach(l ->{
					int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	Random random=new Random();
		                	int ri = random.nextInt(7);
		                	Item meat = p.getWorld().dropItemNaturally(l, new ItemStack(Material.RABBIT_FOOT));
		                	meat.setPickupDelay(9999);
		                	meat.setItemStack(new ItemStack(cookeds[ri]));
		                	meat.setOwner(p.getUniqueId());
		                	meat.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
		                	meat.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
		                	meat.setMetadata("stuff", new FixedMetadataValue(RMain.getInstance(), rn));
		                	p.getWorld().spawnParticle(Particle.FLAME, l, 50, 4, 4, 4, 0.2);
		                	p.getWorld().spawnParticle(Particle.LANDING_OBSIDIAN_TEAR, l, 50, 4, 4, 4, 0.2);
							p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 2f);
							p.getWorld().playSound(meat.getLocation(), Sound.BLOCK_FURNACE_FIRE_CRACKLE, 0.8f, 2f);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	meat.remove();
				                }
							}, 10);
		                	for(Entity e : p.getWorld().getNearbyEntities(l, 4,4,4)) {
		                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
		                			LivingEntity le = (LivingEntity)e;

									le.damage(4, p);
									le.setFireTicks(100);
		    						
		                		}
		                	}
		                }
					}, j.incrementAndGet()*2+40);
                	ordt.put(rn, t);
                });
				rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	        }
		}					
	}

	private HashMap<UUID, LivingEntity> blockToPiglin = new HashMap<>();
	private HashMap<UUID, Integer> blockt = new HashMap<>();
	
	public void createHand(Location startLoc, LivingEntity p) {
	    World world = startLoc.getWorld();


	    List<BlockDisplay> thumb = new ArrayList<>();
	    List<BlockDisplay> indexFinger = new ArrayList<>();
	    List<BlockDisplay> middleFinger = new ArrayList<>();
	    List<BlockDisplay> ringFinger = new ArrayList<>();
	    List<BlockDisplay> pinkyFinger = new ArrayList<>();

	    for (int i = 0; i < 5; i++) {
	    	if(i<3) {
		        thumb.add(world.spawn(startLoc.clone().add(0.3, i * 0.2, 0), BlockDisplay.class, bd ->{
		        	bd.setBlock(getBd(Material.SOUL_FIRE));
		        }));
		        pinkyFinger.add(world.spawn(startLoc.clone().add(-1.2, i * 0.2, 0), BlockDisplay.class, bd ->{
		        	bd.setBlock(getBd(Material.SOUL_FIRE));
		        }));
	    	}
	    	if(i<4) {
	    		indexFinger.add(world.spawn(startLoc.clone().add(-0.3, i * 0.2, 0), BlockDisplay.class, bd ->{
		        	bd.setBlock(getBd(Material.SOUL_FIRE));
		        }));
	    		ringFinger.add(world.spawn(startLoc.clone().add(-0.9, i * 0.2, 0), BlockDisplay.class, bd ->{
		        	bd.setBlock(getBd(Material.SOUL_FIRE));
		        }));
	    	}
	    	middleFinger.add(world.spawn(startLoc.clone().add(-0.6, i * 0.2, 0), BlockDisplay.class, bd ->{
	        	bd.setBlock(getBd(Material.SOUL_FIRE));
	        }));
	    }
	    
	    Bukkit.getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
	        int step = 0;

	        @Override
	        public void run() {
	            moveFingerToCenter(thumb, startLoc);
	            moveFingerToCenter(indexFinger, startLoc);
	            moveFingerToCenter(middleFinger, startLoc);
	            moveFingerToCenter(ringFinger, startLoc);
	            moveFingerToCenter(pinkyFinger, startLoc);

	            if (step++ > 10) {
	                removeFingers(thumb);
	                removeFingers(indexFinger);
	                removeFingers(middleFinger);
	                removeFingers(ringFinger);
	                removeFingers(pinkyFinger);

	                for(Entity e : cl.getWorld().getNearbyEntities(cl,2,2,2)) {
						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
							LivingEntity le = (LivingEntity)e;
							le.damage(7,p);
							le.teleport(p);
							Holding.holding(null, le, 50l);
						}
	                }
	                target.damage(7.0);
	                target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 2));
	                target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
	            }
	        }
	    }, 0L, 2L);

	    
	}
	private void moveFingerToCenter(List<BlockDisplay> finger, Location center) {
	    for (BlockDisplay block : finger) {
	        Vector moveDirection = center.toVector().subtract(block.getLocation().toVector()).normalize().multiply(0.1);
	        block.teleport(block.getLocation().add(moveDirection)); // 블록 이동
	    }
	}

	// 손가락 블록 제거 메서드
	private void removeFingers(List<BlockDisplay> finger) {
	    for (BlockDisplay block : finger) {
	        block.remove();
	    }
	}
	
	
	private HashMap<UUID, Boolean> furable = new HashMap<UUID, Boolean>();
	
	final private void furnace(LivingEntity p) {

    	final World w = p.getWorld();
        Holding.holding(null, p, 25l);
		Location pl = p.getEyeLocation().clone();
		w.playSound(pl, Sound.BLOCK_SMOKER_SMOKE, 1.0f, 2f);
		w.playSound(pl, Sound.ITEM_ARMOR_EQUIP_NETHERITE, 1.0f, 0f);
		w.spawnParticle(Particle.LARGE_SMOKE, pl, 150, 2,2,2);
		w.spawnParticle(Particle.GUST, pl, 150, 2,2,2);
		
		createFallingRod(p,p.getEyeLocation(),gettargetblock(p,5));
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
            	furable.remove(p.getUniqueId());
            }
	   	}, 60);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			chargable.putIfAbsent(p.getUniqueId(), true);
            }
	   	}, 80);
	}
	

	public void furnace(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("soulboss") && (d.getEntity() instanceof Mob)) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 8;
	
	
			if(p.hasMetadata("failed") || !furable.containsKey(p.getUniqueId())) {
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
		                	furnace(p);
		                	shcooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {
		            	furnace(p);
		            	shcooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}


	private HashMap<UUID, Boolean> porkable = new HashMap<UUID, Boolean>();
	
	final private void porkchop(LivingEntity p) {

    	final World w = p.getWorld();
        Holding.holding(null, p, 40l);
		Location pfl = p.getEyeLocation().clone();
		w.playSound(pfl, Sound.ENTITY_PIGLIN_BRUTE_DEATH, 1.0f, 0f);
		w.playSound(pfl, Sound.ENTITY_PIGLIN_BRUTE_DEATH, 1.0f, 0f);
		w.spawnParticle(Particle.SMOKE, pfl, 150, 2,2,2);
		w.spawnParticle(Particle.BLOCK_MARKER, pfl, 20,1,1,1, getBd(Material.FURNACE));
		

		
        p.teleport(p.getLocation().clone().add(20, 0.3, 20));
        
		int t =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {

        		w.playSound(pfl, Sound.ENTITY_ZOMBIFIED_PIGLIN_HURT, 1.0f, 0f);
        		w.playSound(pfl, Sound.ENTITY_PIG_DEATH, 1.0f, 0f);
		        for(Player pe : NethercoreRaids.getheroes(p)) {
					for(int i = 0; i <25; i++) {
						Arrow ar =p.getWorld().spawnArrow(pe.getLocation(), BlockFace.UP.getDirection() , 0.5f, 60);
						ar.setShooter(p);
	                    Snowball ws = (Snowball) p.launchProjectile(Snowball.class);
	                    ws.setItem(new ItemStack(Material.COOKED_PORKCHOP));
	                    ws.setShooter(p);
	                    ws.setVelocity(ar.getVelocity());
	        			ws.setMetadata("cookedFoods", new FixedMetadataValue(RMain.getInstance(), true));
						ar.remove();
					}
		        }
		        
            }
		}, 40);
        ordt.put(gethero(p), t);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
            	porkable.remove(p.getUniqueId());
            }
	   	}, 60);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			cursable.putIfAbsent(p.getUniqueId(), 1);
            }
	   	}, 80);
	}
	
	public void porkchop(EntitySpellCastEvent d) 
	{
		if(d.getEntity().hasMetadata("soulboss") && (d.getEntity() instanceof Mob)) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 8;
			
			if(d.getSpell() != Spell.BLINDNESS) {
				return;
			}

			d.setCancelled(true);

			if(p.hasMetadata("failed") || !porkable.containsKey(p.getUniqueId())) {
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
		                	porkchop(p);
		                	aicooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {

		            	porkchop(p);
	                	aicooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}

	

	private HashMap<UUID, Boolean> chargable = new HashMap<UUID, Boolean>();
	public void charge(EntitySpellCastEvent d) 
	{
		if(d.getEntity().hasMetadata("soulboss")) 
		{
			final LivingEntity p = (LivingEntity)d.getEntity();
			if(d.getSpell() != Spell.DISAPPEAR) {
				return;
			}
			d.setCancelled(true);

			if(p.hasMetadata("raid")) {
				if(!NethercoreRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")) {
					return;
				}

				final Location tl = NethercoreRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get().getLocation().clone().add(0,0.2,0);
				charge(p,tl);
			}
		}
	}


	private HashMap<UUID, Integer> hookt1 = new HashMap<UUID, Integer>();
	
	final private void chargeStart(LivingEntity p, Location tl) {

        final Location pfl = p.getLocation().clone();
        
        final Vector pv = tl.clone().toVector().subtract(pfl.clone().toVector()).normalize();
        

		p.swingMainHand();
		p.getWorld().playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, 1.0f, 0f);
		p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BLASTFURNACE_FIRE_CRACKLE, 1.0f, 0f);
			p.getWorld().spawnParticle(Particle.ASH ,p.getLocation(), 200, 0.2,1,0.2,1,0.5f);
			p.getWorld().spawnParticle(Particle.LAVA ,p.getEyeLocation(), 10);
			Holding.holding(null, p, 10l);

		Location pl = pfl.clone();

    	int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
    		@Override
        	public void run() 
            {
    			if(p.isDead()) {
    				return;
    			}
    			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PIGLIN_ANGRY, 0.1f, 0f);
    			p.getWorld().spawnParticle(Particle.TRIAL_SPAWNER_DETECTION_OMINOUS ,p.getLocation(), 50, 2,2,2,1);
                
                final Location cl = p.getLocation().clone();
                
    			pl.add(pv.clone().normalize().multiply(1.25));
    			
    			if(pl.getBlock().isPassable()) {
					p.teleport(pl);
    			}

                for(Entity e : cl.getWorld().getNearbyEntities(cl,2,2,2)) {
					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
						LivingEntity le = (LivingEntity)e;
						le.damage(3.5,p);
						le.teleport(p);
					}
                }
    			
            }
    	},10,1);
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
    	},20);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			porkable.put(p.getUniqueId(), true);
            }
        }, 150); 
	}
	
	final private void charge(LivingEntity p, Location tl) {

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

	private HashMap<UUID, Boolean> smkable = new HashMap<UUID, Boolean>();
	
	final private void smoker(Location ptl, LivingEntity p, Integer dur) {
		
		String rn = gethero(p);
		World w = ptl.getWorld();
		p.getWorld().playSound(ptl, Sound.BLOCK_SMOKER_SMOKE, 1.0f, 0f);
    	p.getWorld().playSound(ptl, Sound.BLOCK_FIRE_AMBIENT, 1.0f, 2f);
    	
    	w.spawnParticle(Particle.BLOCK_MARKER, ptl, 1, getBd(Material.SMOKER));
    	
        int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
                AreaEffectCloud cloud = (AreaEffectCloud) w.spawnEntity(ptl, EntityType.AREA_EFFECT_CLOUD);
                cloud.setParticle(Particle.SMOKE);
                cloud.addCustomEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 1, false, false, false), false);
        		cloud.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
        		cloud.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
        		cloud.setMetadata("piglincloud", new FixedMetadataValue(RMain.getInstance(), rn));
                cloud.setRadius(4f);
                cloud.setSource(p);
                cloud.setSilent(false);
                cloud.setColor(Color.RED);
                cloud.setDuration(dur);

            	smkable.remove(p.getUniqueId());
            }
        }, 35); 
        ordt.put(gethero(p), t);
	}
	
	public void cloudApplied(AreaEffectCloudApplyEvent ev) {
		if(ev.getEntity().hasMetadata("piglincloud")) {
			 AreaEffectCloud cloud = ev.getEntity();
			 LivingEntity p = (LivingEntity) cloud.getSource();

             for(Entity e : cloud.getWorld().getNearbyEntities(cloud.getLocation().clone(),4,4,4)) {
						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
							LivingEntity le = (LivingEntity)e;
							le.damage(3.5,p);
						}
                 }
			 
		}
	}
	
	public void smoker(EntityDamageByEntityEvent d) 
	{
		if((d.getEntity() instanceof Mob) && d.getEntity().hasMetadata("soulboss")) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 4;
	        

			if((p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2);
                d.setCancelled(true);
                ordealable.put(p.getUniqueId(), true);
				return;
			}
			if(p.getTarget() == null|| !(p.getTarget() instanceof Player)||p.hasMetadata("failed")) {
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

			                smoker(ptl, p,300);
		                    
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                 		@Override
		                    	public void run() 
		                        {	
		                 			furable.put(p.getUniqueId(), true);
		        	            }
		                    }, 46); 
		                    
	             			
							rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {

	                	
		                Holding.holding(null, p, 10l);

		                smoker(ptl, p,300);
	                    
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                 		@Override
	                    	public void run() 
	                        {	
	                 			furable.put(p.getUniqueId(), true);
	        	            }
	                    }, 46); 
	                    
						rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}

	public static HashMap<UUID, Integer> count = new HashMap<UUID, Integer>();
	private HashMap<UUID, Boolean> ordealable = new HashMap<UUID, Boolean>();
	public static HashMap<UUID, Boolean> ordeal = new HashMap<UUID, Boolean>();//soulboss 패턴 시작 여부 저장

	public HashMap<UUID, Integer> ast = new HashMap<UUID, Integer>();//ArmorStands damage 태스크 저장
	public HashMap<UUID, Integer> asdt = new HashMap<UUID, Integer>();//ArmorStands remove 태스크 저장
	
	final private void asSpawn(Player pe, String rn, LivingEntity p, final Location fl, int a) {

		final World w = fl.getWorld();
		

		w.spawn(fl, ArmorStand.class, newmob -> {

			newmob.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
			newmob.setGravity(false);
    		newmob.setCustomNameVisible(false);
    		newmob.setInvulnerable(true);
    		newmob.setRemoveWhenFarAway(false);
    		newmob.setGravity(false);
    		newmob.setMarker(true);
    		newmob.setSmall(true);
    		newmob.setInvisible(true);
    		newmob.setCollidable(false);
			newmob.setMetadata("cake"+rn, new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setAI(false);
    		newmob.getEquipment().setHelmet(new ItemStack(Material.RED_CANDLE_CAKE));
    		if(a%4==0) {
        		newmob.getEquipment().setHelmet(new ItemStack(Material.YELLOW_CANDLE_CAKE));
        		newmob.setMetadata("yellowcake"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
    		}
    		newmob.getEquipment().setItemInMainHand(new ItemStack(Material.COOKIE));
    		newmob.getEquipment().setItemInOffHand(new ItemStack(Material.SUGAR));
			
			int t2 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	
                	Location pel = Holding.ale(pe).getLocation();
                	Location arl = Holding.ale(newmob).getLocation();
                	
                	if(pel.getWorld().equals(arl.getWorld())) {
                		Vector v = pel.clone().toVector().subtract(arl.clone().toVector()).clone().normalize().multiply(0.4);
                		if(pel.distance(arl)>6) {
                			v.multiply(10);
                		}
                		Holding.ale(newmob).setVelocity(v);
                	}
                	
            		for(Entity e : newmob.getWorld().getNearbyEntities(newmob.getLocation().clone(), 1, 5, 1)) {
						if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
							Player le = (Player)e;
							if(Holding.ale(newmob).hasMetadata("yellowcake"+rn)) {
								Bukkit.getScheduler().cancelTask(ast.get(newmob.getUniqueId()));
			                	ast.remove(newmob.getUniqueId());
			                	Holding.ale(newmob).remove();
			                	newmob.getWorld().spawnParticle(Particle.HEART, newmob.getLocation(), 50,1,1,1);
			                	pe.getWorld().playSound(pe.getLocation(), Sound.ENTITY_GENERIC_EAT, 1, 2);
			                	pe.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 20, 20, false,false,false));
			                	return;
							}
							
							le.damage(5,p);
						}
                	}
                }
            }, 0,3);
			ordt.put(rn, t2);
			ast.put(newmob.getUniqueId(), t2);
		});
		
	}

	final private void cake(LivingEntity p, int a) {
		String rn = p.getMetadata("raid").get(0).asString();
		Bukkit.getWorld("NethercoreRaid").getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());
        for(Player pe : NethercoreRaids.getheroes(p)) {
        	
        	if(!pe.getWorld().equals(p.getWorld()) || pe.isDead()) {
        		continue;
        	}
        	
            smoker(pe.getLocation(), p, 500);
            
            final Location fl = pe.getLocation().clone();

			p.getWorld().playSound(pe.getLocation(), Sound.BLOCK_SMOKER_SMOKE, 1, 0);
			p.getWorld().playSound(pe.getLocation(), Sound.BLOCK_HONEY_BLOCK_BREAK, 1, 0);
			p.getWorld().spawnParticle(Particle.FALLING_HONEY, fl, 50,1,1,1);
            
			int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	pe.playSound(p, Sound.BLOCK_CAKE_ADD_CANDLE, 1, 0);
            		asSpawn(pe, rn, p, fl, a);
                }
            }, 20); 
			ordt.put(rn, t1);  
        }
	}
	
	final private boolean judge(LivingEntity p, String rn) {
		Boolean bool = Bukkit.getWorld("NethercoreRaid").getEntities().stream().anyMatch(e -> e.hasMetadata("yellowcake"+rn));
		if(bool) {
            for(Player pe : NethercoreRaids.getheroes(p)) {
    			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
            		pe.sendMessage(ChatColor.BOLD+"피글린요리사: 그렇게는 배불리 먹지 못해!!!");
    			}
    			else {
            		pe.sendMessage(ChatColor.BOLD+"PiglinCook: That won't fill you up!!!");
    			}
        		Holding.invur(pe, 60l);
    			p.getWorld().playSound(pe.getLocation(), Sound.ENTITY_TNT_PRIMED, 1, 0);
        	}
	        int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() {
					Bukkit.getWorld("NethercoreRaid").getEntities().stream().filter(e -> e.hasMetadata("cake"+rn)).forEach(newmob -> {
	                	Bukkit.getScheduler().cancelTask(ast.get(newmob.getUniqueId()));
	                	ast.remove(newmob.getUniqueId());
	                	Holding.ale(newmob).remove();
					});

	        		if(ordt.containsKey(rn)) {
	        			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
	        		}
	        		
	        		ordeal.remove(p.getUniqueId());
	                for(Player pe : NethercoreRaids.getheroes(p)) {
	            		pe.setHealth(0);
	            	}
	                rb6cooldown.remove(p.getUniqueId());
	            }
	        }, 20);
			ordt.put(rn, t3);
		}
		else {
			bossfailed(p,rn);
		}
		return bool;
	}

	final private void ordeal(LivingEntity p, EntityDamageByEntityEvent d) {
		String rn = p.getMetadata("raid").get(0).asString();
        if(ordt.containsKey(rn)) {
        	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        	ordt.removeAll(rn);
        }
        ordeal.put(p.getUniqueId(), true);
        Location rl = NethercoreRaids.getraidloc(p).clone();
		p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2);
        d.setCancelled(true);
    	p.teleport(rl.clone().add(30, 1, 25));
        Holding.holding(null, p, 450l);
        Holding.untouchable(p, 450l);
        for(Player pe : NethercoreRaids.getheroes(p)) {
			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		pe.sendMessage(ChatColor.BOLD+"피글린요리사: 후식 시간이다!");
			}
			else {
        		pe.sendMessage(ChatColor.BOLD+"PiglinCook: Dessert Time!");
			}
    		pe.teleport(rl.clone().add(0, 1.5, 0));
    		Holding.invur(pe, 40l);
        }
        
        AtomicInteger j = new AtomicInteger(1);
        int t1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
            	cake(p, j.getAndIncrement());
            }
        }, 20, 20);
		ordt.put(rn, t1);
		
        int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
            	judge(p,rn);
            }
        }, 450);
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
		Bukkit.getWorld("NethercoreRaid").getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());
        for(Player pe : NethercoreRaids.getheroes(p)) {
			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		pe.sendMessage(ChatColor.BOLD+"피글린요리사: 계산은... 필요 없다...");
			}
			else {
        		pe.sendMessage(ChatColor.BOLD+"PiglinCook: No need for payment... it's on the house...");
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
		if(d.getEntity().hasMetadata("soulboss") && d.getEntity().hasMetadata("ruined")&& !d.getEntity().hasMetadata("failed")) 
		{
			LivingEntity p = (LivingEntity)d.getEntity();
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