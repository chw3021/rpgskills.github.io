package io.github.chw3021.monsters.nether;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.NethercoreRaids;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;



public class CrimsonSkills extends Summoned{

	
	Holding hold = Holding.getInstance();
	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb4cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb6cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb8cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> shcooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> aicooldown = new HashMap<UUID, Long>();
	

	
	private static final CrimsonSkills instance = new CrimsonSkills ();
	public static CrimsonSkills getInstance()
	{
		return instance;
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
					l.getWorld().spawnParticle(Particle.BLOCK, l, 200,1.5,1.5,1.5,getBd(Material.FIRE_CORAL_BLOCK));
					l.getWorld().playSound(l, Sound.BLOCK_CORAL_BLOCK_BREAK, 1f, 1.5f);
					
            		for(Entity e : l.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5)) {
						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
							LivingEntity le = (LivingEntity)e;
							le.damage(2.5,p);
							Holding.holding(null, le, 1l);
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
		if(ev.getEntity().hasMetadata("crimsonboss")){

		    LivingEntity p = ev.getEntity();
		    
		    String rn = gethero(p);
		    
		    Vector velocity = p.getEyeLocation().getDirection();
		    final Vector v;

		    // 벡터의 길이가 0인지 확인
		    if (velocity.lengthSquared() > 0) {
		        v = velocity.normalize().clone();
		    } else {
		        v = new Vector(0, 0, 0); // 기본값으로 설정 (이동이 발생하지 않도록)
		    }

    		ItemStack mainf = new ItemStack(Material.NETHERITE_SWORD);
    		ItemMeta mmf = mainf.getItemMeta();
    		mmf.setCustomModelData(3010);
    		mainf.setItemMeta(mmf);


    		final Object ht = getherotype(rn);
			if(ht instanceof Player) {
				Player p1 = (Player) ht;
				p1.sendEquipmentChange(p, EquipmentSlot.HAND, mainf);
			}
			else if(getherotype(rn) instanceof HashSet){
				@SuppressWarnings("unchecked")
				HashSet<Player> par = (HashSet<Player>) ht;
	    		par.forEach(p1 -> {
	    			p1.sendEquipmentChange(p, EquipmentSlot.HAND, mainf);
	    		});
			}
			ev.setCancelled(true);
		    p.swingMainHand();
		    p.playEffect(EntityEffect.ZOGLIN_ATTACK);
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_STEP, 1.0f, 0.2f);
            int sweep = 3;
            if(p.hasMetadata("ruined")) {
            	sweep = 6;
            }
            
			for(int i = 0; i<sweep; i++) {
                ordt.put(rn, Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
	                {	
            			for(int i = 0; i<3; i++) {
                            ordt.put(rn, Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                         		@Override
                            	public void run() 
            	                {	
                         			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 5,0,false,false));
            	                	p.setVelocity(v.clone().multiply(0.6));
            						for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),2, 2, 2)) {
            							if(e instanceof LivingEntity&&  !(e.hasMetadata("portal")) && e!=p) {
            								LivingEntity le = (LivingEntity)e;
            								le.damage(3,p);
            								Holding.holding(null, le, 5l);
            							}
            						}
            	                	
            		            }
                    	   	}, i));
            			}
    	    			Location pl = p.getLocation().clone();
                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_DROWNED_SHOOT, 0.6f, 0.4f);
    					p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, pl, 15, 2,1,2);
         				p.getWorld().spawnParticle(Particle.CRIMSON_SPORE, pl, 30);
         				p.swingMainHand();
	                	
		            }
        	   	}, i*3+5));
			}

		 }
	}



	private void burst(LivingEntity p) {

       

        Location tl = gettargetblock(p,4).clone();
        
		p.getWorld().playSound(tl, Sound.BLOCK_LAVA_AMBIENT, 1f, 2f);
    	p.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, tl, 100, 4, 1, 4, 0, new Particle.DustTransition(Color.ORANGE, Color.RED, 5f));

        
		ArrayList<Location> line = new ArrayList<Location>();
        AtomicInteger j = new AtomicInteger(0);
        for(double d = 0.1; d <= 7; d += 0.3) {
				line.add(tl.clone().add(0, d, 0).setDirection(BlockFace.UP.getDirection()));
        }
        
        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1.0f, 0f);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0f);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2f);
		for(int i = 0; i <7; i++) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                    line.forEach(l ->  {	
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                		@Override
                        	public void run() 
                            {	
        						tl.getWorld().spawnParticle(Particle.DUST_PILLAR, l, 100, 4,0.5,4,0 ,getBd(Material.CRIMSON_PLANKS));
            					tl.getWorld().spawnParticle(Particle.DUST_PILLAR, l, 100, 4,0.5,4,0 ,getBd(Material.CRIMSON_STEM));
                            }
                	   }, j.getAndIncrement()/230); 
                    });
                	for(Entity e : p.getWorld().getNearbyEntities(tl, 4,7,4)) {
                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
                			LivingEntity le = (LivingEntity)e;

							le.damage(4, p);
							le.setVelocity(BlockFace.UP.getDirection());
    						
                		}
                	}
                }
            }, i*2+30);
        }
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
            	cursable.remove(p.getUniqueId());
            }
	   	}, 60);
	}
	
	private HashMap<UUID, Integer> cursable = new HashMap<UUID, Integer>();
	public void curse(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 8;
		if(d.getEntity().hasMetadata("crimsonboss") && cursable.containsKey(d.getEntity().getUniqueId())) 
		{
			LivingEntity p = (LivingEntity)d.getEntity();
			if(ordeal.containsKey(p.getUniqueId()) || p.hasMetadata("failed")) {
				return;
			}
            if (checkAndApplyCharge(p, d)) return;
			if(rb3cooldown.containsKey(p.getUniqueId()))
	        {
	            long timer = (rb3cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
	            if(!(timer < 0))
	            {
	            }
	            else 
	            {
	            	rb3cooldown.remove(p.getUniqueId());
	            	burst(p);
					rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {

            	burst(p);
				rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	        }
		}					
	}
	
	

	private HashMap<UUID, Integer> blockt = new HashMap<>();

    private ArmorStand spawnArmorStand(World world, Location location, String rn) {
        return world.spawn(location, ArmorStand.class, stand -> {
            stand.setGravity(false);
            stand.setInvulnerable(true);
            stand.setInvisible(true);
            stand.setBasePlate(false);
            stand.setCollidable(false);
            stand.setMetadata("stuff" + rn, new FixedMetadataValue(RMain.getInstance(), rn));
            stand.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
            stand.getEquipment().setHelmet(new ItemStack(Material.STRIPPED_CRIMSON_STEM));
            stand.getAttribute(Attribute.SCALE).setBaseValue(3);
        });
    }
    

	final private void Ray(Location as, LivingEntity le) {
		final World snw = as.getWorld();
		final Location lel = le.getEyeLocation().clone();
		final Location snl = as.clone();
		final Vector v = lel.clone().toVector().subtract(snl.clone().toVector());
		final double dis = lel.distance(snl);
		
		HashSet<Location> line = new HashSet<>();
		for(double d = 0; d<dis; d += 0.1) {
			line.add(snl.clone().add(v.clone().normalize().multiply(d)));
		}
		line.forEach(l -> {
			snw.spawnParticle(Particle.BLOCK, l, 3, 0.01,0.01,0.01,0, Material.NETHERRACK.createBlockData());
		});
	}
	
    private void summonWoodPillar(LivingEntity boss, Location startLoc) {
        World world = startLoc.getWorld();
        String rn = gethero(boss);

        List<ArmorStand> pillars = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Location pillarLoc = startLoc.clone().add(0, i * 0.5, 0); // 기둥 블록 간격 1
            pillars.add(spawnArmorStand(world, pillarLoc, rn));
        }

        BukkitTask bt = Bukkit.getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
            int tick = 0;

            @Override
            public void run() {
                Player target = NethercoreRaids.getheroes(boss).stream()
                        .filter(p -> p.isValid() && p.getWorld().equals(world))
                        .min((p1, p2) -> Double.compare(p1.getLocation().distance(startLoc), p2.getLocation().distance(startLoc)))
                        .orElse(null);

                if (target == null || tick >= 100) {
                    removeFingers(pillars);
                    this.cancel();
                    return;
                }

                movePillarTowardsPlayer(pillars, target.getLocation());

                for (Entity e : world.getNearbyEntities(pillars.get(0).getLocation(), 2, 2, 2)) {
                    if (e instanceof Player && e != boss) {
                        Player player = (Player) e;
                        player.damage(2, boss);
                        player.setVelocity(player.getVelocity().multiply(0.25));
                        player.playSound(player.getLocation(), Sound.ENTITY_WITCH_DRINK, 0.05f, 0.1f);
						Ray(pillars.get(0).getLocation(),player);
                        boss.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE, 5,5,false,false));
                    }
                }

                tick = tick +2;
            }

            private void cancel() {
                Bukkit.getScheduler().cancelTask(this.hashCode());
            }
        }, 0L, 2L); // 0틱 지연, 2틱 간격으로 실행

        blockt.put(boss.getUniqueId(), bt.getTaskId());
        ordt.put(rn, bt.getTaskId());
    }

    private void movePillarTowardsPlayer(List<ArmorStand> pillars, Location targetLoc) {
        for (ArmorStand block : pillars) {
        	block.getWorld().spawnParticle(Particle.CRIMSON_SPORE, block.getLocation(), 10, 1.5, 1.5, 1.5, 0.1);
        	block.getWorld().playSound(block.getLocation(), Sound.ENTITY_PLAYER_TELEPORT, 0.05f, 2f);
            Vector moveDirection = targetLoc.toVector().subtract(block.getLocation().toVector());
            double distance = moveDirection.length();

            if (distance > 0.5) {
                double speed = Math.log(distance + 0.5) * 0.1;
                moveDirection.normalize().multiply(speed);
                block.teleport(block.getLocation().add(moveDirection));
            }
        }
    }

    private void removeFingers(List<ArmorStand> pillars) {
        for (ArmorStand pillar : pillars) {
            pillar.remove();
        }
    }

	
	
	private HashMap<UUID, Boolean> handable = new HashMap<UUID, Boolean>();
	
	final private void hand(LivingEntity p) {
		p.getWorld().playSound(p, Sound.ENTITY_BREEZE_CHARGE, 0.6f, 0.6f);
		p.getWorld().playSound(p, Sound.BLOCK_END_PORTAL_SPAWN, 0.3f, 1.5f);

    	final World w = p.getWorld();
		Location pl = gettargetblock(p,6);
		w.playSound(pl, Sound.ENTITY_SHULKER_TELEPORT, 1.0f, 0f);
		w.spawnParticle(Particle.FLAME, pl, 150, 2,2,2);
		w.spawnParticle(Particle.REVERSE_PORTAL, pl, 150, 2,2,2);
		w.spawnParticle(Particle.RAID_OMEN, pl, 150, 2,2,2);
		

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			summonWoodPillar(p,pl);
            }
	   	}, 25);
	   	
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
            	handable.remove(p.getUniqueId());
            }
	   	}, 60);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			waveable.putIfAbsent(p.getUniqueId(), true);
            }
	   	}, 80);
	}
	

	public void hand(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("crimsonboss")) 
		{
			Mob p = (Mob)d.getEntity();
			
			int sec = 15;
	
	
			if(p.hasMetadata("failed")|| ordeal.containsKey(p.getUniqueId()) || !handable.containsKey(p.getUniqueId())) {
				return;
			}
            if (checkAndApplyCharge(p, d)) return;
					if(shcooldown.containsKey(p.getUniqueId()))
		            {
		                long timer = (shcooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		                if(!(timer < 0))
		                {
		                }
		                else 
		                {
		                	shcooldown.remove(p.getUniqueId()); // removing player from HashMap
		                	hand(p);
		                	shcooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {
		            	hand(p);
		            	shcooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}



	private HashMap<UUID, Boolean> phantomable = new HashMap<UUID, Boolean>();
	final private void phantom(LivingEntity p) {

		World w = p.getWorld();
		
		final Location pl = p.getEyeLocation().clone();

        w.playSound(p.getLocation(), Sound.ENTITY_ZOGLIN_ANGRY, 1.2f, 0.5f);
		p.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, pl, 100, 2,2,2);
		for(int i = 0; i<10; i++) {
            int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
            	public void run() 
                {	
                	Location tl = p.getLocation().clone().add(p.getLocation().clone().getDirection().normalize().multiply(1.8));
	    	        Vector pv = pl.getDirection().clone();
         			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 5,0,false,false));
         			Location tel = p.getLocation().clone().add(pv.clone().multiply(0.7));
         			if(tel.getBlock().isPassable()) {
            			p.teleport(tel);
         			}
                	p.swingMainHand();
                	w.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.2f, 0f);
                    w.playSound(p.getLocation(), Sound.ENTITY_HUSK_STEP, 0.1f, 1.5f);
     				w.spawnParticle(Particle.SWEEP_ATTACK, pl, 25, 4,4,4);
     				w.spawnParticle(Particle.CRIMSON_SPORE, pl, 25, 1.5,1.5,1.5);

                	for(Entity e : p.getWorld().getNearbyEntities(tl, 4,4,4)) {
                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
                			LivingEntity le = (LivingEntity)e;

							le.damage(2, p);
							if(tl.getBlock().isPassable()) {
								le.teleport(tl);
							}
    						
                		}
                	}
                	
	            }
    	   	}, i*2+25);
            ordt.put(gethero(p), task);
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			phantomable.remove(p.getUniqueId());
            }
	   	}, 80);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			cursable.putIfAbsent(p.getUniqueId(), 1);
            }
	   	}, 100);
	}
	
	public void phantom(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("crimsonboss")) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 8;
			

			if(p.hasMetadata("failed")|| ordeal.containsKey(p.getUniqueId()) || !phantomable.containsKey(p.getUniqueId())) {
				return;
			}
            if (checkAndApplyCharge(p, d)) return;
					if(aicooldown.containsKey(p.getUniqueId()))
		            {
		                long timer = (aicooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		                if(!(timer < 0))
		                {
		                }
		                else 
		                {
		                	aicooldown.remove(p.getUniqueId()); // removing player from HashMap
		                	phantom(p);
		                	aicooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {

		            	phantom(p);
	                	aicooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}

	

	private HashMap<UUID, Boolean> waveable = new HashMap<UUID, Boolean>();
	public void wave(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("crimsonboss")) 
		{
			final LivingEntity p = (LivingEntity)d.getEntity();

			if(p.hasMetadata("raid")) {
				if(!NethercoreRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")) {
					return;
				}

				final Location tl = NethercoreRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get().getLocation().clone().add(0,0.2,0);
				wave(p,tl);
			}
		}
	}
	
	final private void waveStart(LivingEntity p, Location tl) {
		p.getWorld().playSound(p.getLocation(), Sound.ENTITY_LLAMA_SPIT, 1.0f, 0.1f);
		p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PIGLIN_ANGRY, 1.0f, 0.1f);
		p.getWorld().spawnParticle(Particle.ITEM, p.getLocation(), 100, 2,1,2,0 , new ItemStack(Material.PLAYER_HEAD));
		p.getWorld().spawnParticle(Particle.BLOCK_CRUMBLE, p.getLocation(), 200, 2,1,2,0 ,getBd(Material.BRAIN_CORAL_BLOCK));
		p.getWorld().spawnParticle(Particle.DUST_PILLAR, p.getLocation(), 100, 1,1,1,0 ,getBd(Material.BRAIN_CORAL_BLOCK));

        final Location fl = p.getLocation().clone();
        AtomicInteger j = new AtomicInteger(0);
        Holding.holding(null, p, 20l);
        
    	j.set(Bukkit.getServer().getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
    		
	    	int tick = 0;
			@Override
        	public void run() 
            {	
		        HashSet<Location> line = new HashSet<Location>();
				if(tick++>=65) {
					Bukkit.getScheduler().cancelTask(j.get());
				}
    	        for(double d = -Math.PI/6; d<= Math.PI/6; d += Math.PI/90) {
    	        	Location l = fl.clone();
    	            l.setDirection(l.getDirection().normalize().rotateAroundY(d));
    	            l.add(l.getDirection().normalize().multiply((tick)*0.15));
    	            line.add(l);
    	        }
    	        line.forEach(l ->{
					p.getWorld().spawnParticle(Particle.BLOCK, l, 2, 0.5,0.5,0.5,0 ,Material.BRAIN_CORAL_BLOCK.createBlockData());

		        	for (Entity a : p.getWorld().getNearbyEntities(l, 1, 1, 1))
					{
						if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
						{
							LivingEntity le = (LivingEntity)a;
							le.damage(0.5, p);
						}
					}
    	        });
			
            }
    	}, 30, 1).getTaskId());
    	ordt.put(gethero(p), j.get());
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			waveable.remove(p.getUniqueId());
            }
	   	}, 70);
	}
	

	final private void wave(LivingEntity p, Location tl) {
	    if (p.hasMetadata("failed") || ordeal.containsKey(p.getUniqueId()) || !waveable.containsKey(p.getUniqueId()) || cursable.containsKey(p.getUniqueId())) {
	        return;
	    }

	    if (rb8cooldown.containsKey(p.getUniqueId())) {
	        long timer = (rb8cooldown.get(p.getUniqueId()) / 1000 + 4) - System.currentTimeMillis() / 1000;
	        if (timer < 0) {
	            rb8cooldown.remove(p.getUniqueId());
	            waveStart(p, tl);
	            rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	        }
	    } else {
	        waveStart(p, tl);
	        rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	    }
	}
	
	final private void jumpAndHit(final Location tl, LivingEntity p) {
		
		Holding.holding(null, p, 22l);
	    Location fl = p.getLocation().clone(); // 시작 위치
	    Location jl = tl.clone().add(0, 4, 0); // 목표 도약 위치
	    World world = p.getWorld();
        world.spawnParticle(Particle.WHITE_SMOKE, tl, 20, 2, 0.2, 2, 0);
	    world.playSound(tl, Sound.ENTITY_ENDER_DRAGON_GROWL, 1f, 2f);

	    // 초기 이동 설정
	    double totalTicks = 7; // 도약에 걸리는 전체 시간 (tick 단위)
	    double tickInterval = 1; // 각 tick 간격 (1tick = 50ms)
	    AtomicInteger currentTick = new AtomicInteger(0); // 현재 진행 중인 tick

	    // 방향 벡터 계산
	    Vector horizontalDirection = jl.toVector().subtract(fl.toVector()).normalize(); // 수평 이동 방향
	    double totalDistance = fl.distance(jl); // 총 이동 거리
	    double speed = totalDistance / totalTicks; // 수평 속도
	    

	    AtomicInteger j = new AtomicInteger();
	    j.set(Bukkit.getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	        @Override
	        public void run() {
	            int tick = currentTick.getAndIncrement();
	            if (tick > totalTicks) {
	                // 도착 후 공격 동작 실행
	                performAttack(tl, p);
	                Bukkit.getScheduler().cancelTask(j.get());
	                return;
	            }

	            // 로그 곡선 기반 높이 계산
	            double progress = (double) tick / totalTicks; // 진행 비율 (0 ~ 1)
	            double height = 4 * Math.log10(1 + 9 * progress); // 높이 값 (로그 함수: log10(1 + 9x))

	            // 새로운 위치 계산
	            Location newLocation = fl.clone().add(horizontalDirection.clone().multiply(speed * tick)); // 수평 이동
	            newLocation.setY(fl.getY() + height); // 높이 적용

	            // 보스몹 이동
	            p.teleport(newLocation);

	            // 이동 중 파티클 효과
	            world.spawnParticle(Particle.CRIMSON_SPORE, newLocation, 5, 0.2, 0.2, 0.2, 0);
	        }
	    }, 22L, (long) tickInterval)); 

	    // 태스크 저장 (필요 시 추가 관리)
	    ordt.put(gethero(p), j.get());
	}

	private void performAttack(Location tl, LivingEntity p) {
	    World world = p.getWorld();

	    p.teleport(tl);
	    // 내려찍기 공격 효과
	    world.playSound(tl, Sound.ENTITY_ELDER_GUARDIAN_HURT, 2.0f, 0f);
	    world.spawnParticle(Particle.EXPLOSION, tl, 10, 3, 1, 3, 0);

	    // 피해 판정
	    for (Entity entity : world.getNearbyEntities(tl, 3, 3, 3)) {
	        if (entity instanceof LivingEntity && entity != p) {
	            LivingEntity target = (LivingEntity) entity;
	            target.damage(6.0, p);
	            target.setVelocity(BlockFace.DOWN.getDirection().multiply(5));
	        }
	    }
	}

	
	
	public void storm(EntityDamageByEntityEvent d) 
	{
		if((d.getEntity() instanceof Mob) && d.getEntity().hasMetadata("crimsonboss")) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 5;
	        

            if (checkAndApplyCharge(p, d)) return;
			if(p.getTarget() == null|| !(p.getTarget() instanceof Player) || phantomable.containsKey(p.getUniqueId()) || waveable.containsKey(p.getUniqueId()) || cursable.containsKey(p.getUniqueId())|| ordeal.containsKey(p.getUniqueId())||p.hasMetadata("failed")) {
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

			                jumpAndHit(ptl, p);
		                    
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                 		@Override
		                    	public void run() 
		                        {	
		                 			handable.put(p.getUniqueId(), true);
		        	            }
		                    }, 46); 

		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                 		@Override
		                    	public void run() 
		                        {	
		                 			phantomable.put(p.getUniqueId(), true);
		        	            }
		                    }, 50); 
	             			
							rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {


		                Holding.holding(null, p, 10l);

		                jumpAndHit(ptl, p);
	                    
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                 		@Override
	                    	public void run() 
	                        {	
	                 			handable.put(p.getUniqueId(), true);
	        	            }
	                    }, 46); 

	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                 		@Override
	                    	public void run() 
	                        {	
	                 			phantomable.put(p.getUniqueId(), true);
	        	            }
	                    }, 50); 
             			
						rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}


	final private boolean judge(LivingEntity p, String rn) {
		long remainingAltars = p.getWorld().getEntitiesByClass(ArmorStand.class).stream()
                .filter(e -> e.hasMetadata("stuff" + rn)&&e.hasMetadata("bloodAltar")).count();

		Boolean bool = remainingAltars > 1; //플레이어들이 패턴 파훼 실패시 true, 성공시 false

		p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(newmob -> {
        	if(ast.containsKey(newmob.getUniqueId())) {
				Bukkit.getScheduler().cancelTask(ast.remove(newmob.getUniqueId()));
        	}
			if(getbossbyalter.containsKey(newmob.getUniqueId())) {
				getbossbyalter.remove(newmob.getUniqueId());
			}
        	Holding.ale(newmob).remove();
		});
		
		if(bool) {
    		Holding.invur(p, 20l);
            for(Player pe : NethercoreRaids.getheroes(p)) {
    			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
            		pe.sendMessage(ChatColor.BOLD+"진홍빛학살자: 죽어라 이제.");
    			}
    			else {
            		pe.sendMessage(ChatColor.BOLD+"CrimsonSavager: This is the end for you.");
    			}
        		Holding.invur(pe, 60l);
    			p.getWorld().playSound(pe.getLocation(), Sound.ENTITY_TNT_PRIMED, 1, 0);
        	}
	        int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() {
	            	
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

	private HashMap<UUID,UUID> getbossbyalter = new HashMap<>();
	
	public void breakBloodAltar(EntityDamageByEntityEvent d) 
	{
		if((d.getEntity() instanceof ArmorStand as) && d.getEntity().hasMetadata("bloodAltar")) 
		{
			d.setCancelled(true);

			if(d.getDamager() instanceof Player hp && !hp.hasCooldown(Material.YELLOW_TERRACOTTA)) 
			{
				LivingEntity p = (LivingEntity) Bukkit.getEntity(getbossbyalter.get(as.getUniqueId()));
				
				Boolean isFar = false;
				final Location asl = as.getLocation().clone();

		        for(Player pe : NethercoreRaids.getheroes(p)) {
		        	if(pe.getLocation().distance(asl) >3) {
		        		isFar = true;
		        	}
		        }
		        if(isFar) {

			        for(Player pe : NethercoreRaids.getheroes(p)) {
						if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
			        		pe.sendMessage(ChatColor.BOLD+"진홍빛학살자: 의리없는 것들..");
						}
						else {
			        		pe.sendMessage(ChatColor.BOLD+"CrimsonSavager: Traitors.. you'll pay for this.");
						}
				        pe.damage(12,p);
					    Holding.holding(null, pe, 40l);
			        }
			        
		        }
		        else {
		    		as.getWorld().playSound(as.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1, 2);
		    		as.getWorld().spawnParticle(Particle.CRIMSON_SPORE, as.getLocation(),50);
		    		getbossbyalter.remove(as.getUniqueId());
		    		if(ast.containsKey(as.getUniqueId())) {
		    			Bukkit.getScheduler().cancelTask(ast.remove(as.getUniqueId()));
		    		}
		    		as.remove();
		        }
			}
		}
	}
	

	public HashMap<UUID, Integer> ast = new HashMap<UUID, Integer>();//ArmorStands damage 태스크 저장
	
	final private void asSpawn(String rn, LivingEntity p, final Location fl) {
	    final World w = fl.getWorld();

	    // 소환 위치 계산
	    Random random = new Random();
	    double distance = 2 + (random.nextDouble() * (5 - 2)); // 2 이상 5 미만
	    double angle = random.nextDouble() * 2 * Math.PI; // 0에서 360도 랜덤 방향
	    double xOffset = Math.cos(angle) * distance;
	    double zOffset = Math.sin(angle) * distance;

	    Location spawnLocation = fl.clone().add(xOffset, 0, zOffset);
	    spawnLocation.setY(w.getHighestBlockYAt(spawnLocation) + 1); // 지형에 맞게 Y 좌표 조정
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SKELETON_STEP, 1.9f, 0);

	    ArmorStand newmob = w.spawn(spawnLocation, ArmorStand.class);
	    newmob.setMetadata("stuff" + rn, new FixedMetadataValue(RMain.getInstance(), true));
	    newmob.setGravity(false);
	    newmob.setCollidable(false);
	    newmob.setCustomNameVisible(false);
	    newmob.setVisible(false);
	    newmob.getEquipment().setHelmet(new ItemStack(Material.STRIPPED_CRIMSON_HYPHAE));
	    newmob.setInvulnerable(false);
	    newmob.setMetadata("bloodAltar", new FixedMetadataValue(RMain.getInstance(), rn));
	    newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), rn));
	    newmob.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
	    newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
	    getbossbyalter.put(newmob.getUniqueId(), p.getUniqueId());

	    // 파티클 위치를 미리 계산하여 저장
	    HashSet<Location> particleLocations = calculateParticleLocations(newmob.getLocation(), 3, 50);

	    int t2 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	        @Override
	        public void run() {
	            AtomicBoolean isLightColor = new AtomicBoolean(true);
	            for (Location particleLocation : particleLocations) {
	                Particle.DustOptions dustOptions = isLightColor.get()
	                    ? new Particle.DustOptions(Color.fromRGB(255, 182, 193), 1.5f) // 연한 분홍색
	                    : new Particle.DustOptions(Color.fromRGB(255, 105, 180), 1.5f); // 진한 분홍색
	                w.spawnParticle(Particle.DUST, particleLocation, 1, dustOptions);

	                isLightColor.set(!isLightColor.get());
	            }
	        }
	    }, 0, 5);

	    ordt.put(rn, t2);
	    ast.put(newmob.getUniqueId(), t2);
	}

	// 원형 파티클 위치를 미리 계산하는 메서드
	private HashSet<Location> calculateParticleLocations(Location center, double radius, int particleCount) {
	    HashSet<Location> locations = new HashSet<>();
	    World world = center.getWorld();
	    for (int i = 0; i < particleCount; i++) {
	        double angle = (2 * Math.PI / particleCount) * i;
	        double x = center.getX() + (radius * Math.cos(angle));
	        double z = center.getZ() + (radius * Math.sin(angle));
	        locations.add(new Location(world, x, center.getY() + 0.5, z));
	    }
	    return locations;
	}
	
	final private void ordeal(LivingEntity p, EntityDamageByEntityEvent d) {
	    String rn = p.getMetadata("raid").get(0).asString();
	    if (ordt.containsKey(rn)) {
	        ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
	        ordt.removeAll(rn);
	    }

	    ordeal.put(p.getUniqueId(), true);
	    final Location rl = NethercoreRaids.getraidloc(p).clone();
	    p.setHealth(p.getAttribute(Attribute.MAX_HEALTH).getValue() * 0.2); // 체력 20%
	    d.setCancelled(true);

	    Long ordealTime = 620l;

	    p.teleport(rl.clone().add(-1, 2, -1));
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_CONVERTED_TO_STRAY, 1.1f, 0);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 1.1f, 0);
	    Holding.holding(null, p, ordealTime);
	    Holding.untouchable(p, ordealTime);
        for(Player pe : NethercoreRaids.getheroes(p)) {
			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		pe.sendMessage(ChatColor.BOLD+"진홍빛학살자: 전부 찢어죽인다");
			}
			else {
        		pe.sendMessage(ChatColor.BOLD+"CrimsonSavager: I’ll tear you all to pieces!");
			}
		    pe.teleport(rl.clone().add(3, 1, 3));
        }
	 // 패턴 효과: 보스 회전 및 주변 공격
        AtomicInteger angle = new AtomicInteger();
	    int rotateTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), () -> {
	        Player target = NethercoreRaids.getheroes(p).stream()
	                .min((player1, player2) -> (int) (p.getLocation().distance(player1.getLocation())
	                        - p.getLocation().distance(player2.getLocation())))
	                .orElse(null);

	        if (target != null) {
	            Location bossLocation = p.getLocation();
	            Location targetLocation = target.getLocation().clone();
	            Double distance = bossLocation.distance(targetLocation);

	            // 보스가 타겟 방향으로 이동
	            Vector direction = targetLocation.subtract(bossLocation).toVector().normalize().multiply(0.015*(distance)+0.11);
	            bossLocation.add(direction); // 보스 위치를 타겟 방향으로 이동
	            bossLocation.setDirection(direction); // 보스 방향 설정
	            p.teleport(bossLocation);

	            // 빠르게 회전시키기 (1틱마다 방향 변경)
	            bossLocation.setDirection(bossLocation.clone().getDirection().normalize().rotateAroundY(Math.toRadians(angle.addAndGet(75))));
	            p.teleport(bossLocation);
	            p.swingOffHand();

	            double radius = 3; // 원 반지름
	            int points = 18; // 원의 점 개수
	            for (int i = 0; i < points; i++) {
	                double radians = Math.toRadians(i * (360.0 / points) + angle.get()); // 각도 계산
	                double x = bossLocation.getX() + radius * Math.cos(radians); // x 좌표
	                double z = bossLocation.getZ() + radius * Math.sin(radians); // z 좌표
	                Location particleLocation = new Location(bossLocation.getWorld(), x, bossLocation.getY() + 1, z); // 파티클 위치

	                bossLocation.getWorld().spawnParticle(Particle.SWEEP_ATTACK, particleLocation, 1, 0, 0, 0, 0.1);
	            }
	            // 범위 공격
	            double attackRadius = 2.8; 

	            // 범위 내의 엔티티들에게 피해 입히기
	            bossLocation.getWorld().getNearbyEntities(bossLocation, attackRadius, attackRadius, attackRadius).stream()
	                    .filter(entity -> entity instanceof LivingEntity && entity != p && !entity.hasMetadata("fake"))
	                    .forEach(entity -> {
	                        LivingEntity victim = (LivingEntity) entity;
	                        victim.damage(5.0, p); // 5의 피해를 입힘
	                    });

	            // 효과음 추가
	            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 0.1f, 1.23f);
	        }
	    }, 40, 1); // 1틱마다 실행 (매우 빠르게)


	    ordt.put(rn, rotateTask);

	    // 제단 소환
	    int size = NethercoreRaids.getheroes(p).size();
	    int altarTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), () -> {
	        asSpawn(rn, p, rl);
	    }, 0, size>1 ? 100:60); // 100틱마다 실행

	    ordt.put(rn, altarTask);

	    // 제한시간 후 결과 판단
	    int endTask = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), () -> {
	        judge(p, rn);
	    }, ordealTime);

	    ordt.put(rn, endTask);
	}

	final private void bossfailed(LivingEntity p, String rn) {
		if(ordt.containsKey(rn)) {
			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        	ordt.removeAll(rn);
		}
		ordeal.remove(p.getUniqueId());
    	p.playHurtAnimation(0);
		p.getWorld().playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1, 2);
		p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 10, 2,2,2);
    	Holding.reset(Holding.ale(p));
    	Holding.ale(p).setMetadata("failed", new FixedMetadataValue(RMain.getInstance(),true));
		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
		Bukkit.getWorld("NethercoreRaid").getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());

		p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(newmob -> {
        	if(ast.containsKey(newmob.getUniqueId())) {
				Bukkit.getScheduler().cancelTask(ast.remove(newmob.getUniqueId()));
        	}
			if(getbossbyalter.containsKey(newmob.getUniqueId())) {
				getbossbyalter.remove(newmob.getUniqueId());
			}
        	Holding.ale(newmob).remove();
		});
        for(Player pe : NethercoreRaids.getheroes(p)) {
			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		pe.sendMessage(ChatColor.BOLD+"진홍빛학살자: 제법이군...");
			}
			else {
        		pe.sendMessage(ChatColor.BOLD+"CrimsonSavager: Impressive…");
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
		if(d.getEntity().hasMetadata("crimsonboss") && d.getEntity().hasMetadata("ruined")&& !d.getEntity().hasMetadata("failed")) 
		{
			LivingEntity p = (LivingEntity)d.getEntity();
			if(!(p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2)|| !ordealable.containsKey(p.getUniqueId())) {
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