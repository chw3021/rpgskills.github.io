package io.github.chw3021.monsters.ender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.EndercoreRaids;
import io.github.chw3021.rmain.RMain;



public class EnderSkills extends EndercoreRaids{

	
	Holding hold = Holding.getInstance();
	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb4cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb5cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb8cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> shcooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> aicooldown = new HashMap<UUID, Long>();
	

	
	private static final EnderSkills instance = new EnderSkills ();
	public static EnderSkills getInstance()
	{
		return instance;
	}
	
	
	public void hit(ProjectileHitEvent d) 
	{
		Projectile po = d.getEntity();
		if(po.getShooter() instanceof LivingEntity) {
			LivingEntity p = (LivingEntity) po.getShooter();
			if(po.hasMetadata("enderbossPearl")) {
        		final Location l = d.getHitEntity() != null ? d.getHitEntity().getLocation() : d.getHitBlock().getLocation();

				l.getWorld().spawnParticle(Particle.REVERSE_PORTAL, l, 100);
				l.getWorld().playSound(l, Sound.ENTITY_SHULKER_TELEPORT, 1f, 1.5f);

        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						l.getWorld().spawnParticle(Particle.DRAGON_BREATH, l, 300,2,2,2,0.05);
						l.getWorld().playSound(l, Sound.ENTITY_ENDER_DRAGON_SHOOT, 1f, 0.2f);
	            		for(Entity e : l.getWorld().getNearbyEntities(l, 2, 2, 2)) {
							if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("portal"))) {
								LivingEntity le = (LivingEntity)e;
								le.damage(4.3,p);
								Holding.holding(null, le, 23l);
							}
	                	}
	                }
	            }, 15); 
			}
		}
	}
	

	
	public void bowshoot(EntityShootBowEvent ev) 
	{
		if(ev.getEntity().hasMetadata("enderboss")){

			ev.setCancelled(true);
			
		    LivingEntity p = ev.getEntity();
		    
        	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_SHOOT, 0.5f, 2f);

			p.getWorld().spawnParticle(Particle.END_ROD, p.getLocation(), 10);

    		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	p.swingMainHand();
                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 1f, 1.2f);
                    EnderPearl ws = (EnderPearl) p.launchProjectile(EnderPearl.class);
                    ws.setShooter(p);
                    ws.setGlowing(true);
                    ws.setVelocity(p.getLocation().getDirection().normalize().multiply(1.3));
        			ws.setMetadata("enderbossPearl", new FixedMetadataValue(RMain.getInstance(), true));
                }
            }, 10); 


		 }
	}
	
	private void Explosion(LivingEntity p, final Location tl, final Location fl) {
    	p.getWorld().playSound(tl, Sound.ENTITY_EVOKER_CAST_SPELL, 0.5f, 2f);
		p.getWorld().spawnParticle(Particle.ENCHANT, tl, 200);
		
		String rn = gethero(p);
		
		ordt.put(rn, Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
        		p.getWorld().spawnParticle(Particle.PORTAL, tl, 150);
            	p.getWorld().playSound(tl, Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 1.5f);
            }
		}, 13));
		
		ordt.put(rn, Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
        		p.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, tl,1);
            	p.getWorld().playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.5f, 0.5f);
            	for(Entity e : p.getWorld().getNearbyEntities(tl, 3,3,3)) {
            		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
            			LivingEntity le = (LivingEntity)e;
            			le.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA,100,100,false,false));
            			le.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,100,100,false,false));
						le.damage(4.5, p);
						
            		}
            	}
            }
		}, 26));
	}


	
	private void tExplosion(LivingEntity p, final Location tl) {
    	p.getWorld().playSound(tl, Sound.ENTITY_EVOKER_CAST_SPELL, 0.5f, 2f);
		p.getWorld().spawnParticle(Particle.ENCHANT, tl, 200);
		
		String rn = gethero(p);
		
		ordt.put(rn, Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
            	p.teleport(tl);
        		p.getWorld().spawnParticle(Particle.PORTAL, tl, 150);
            	p.getWorld().playSound(tl, Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 1.5f);
            	for(Entity e : p.getWorld().getNearbyEntities(tl, 2,2,2)) {
            		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
            			LivingEntity le = (LivingEntity)e;
            			le.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA,100,100,false,false));
            			le.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,100,100,false,false));
						
            		}
            	}
            }
		}, 13));
		
		ordt.put(rn, Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
        		p.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, tl,1);
            	p.getWorld().playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.5f, 0.5f);
            	for(Entity e : p.getWorld().getNearbyEntities(tl, 3,3,3)) {
            		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
            			LivingEntity le = (LivingEntity)e;
						le.damage(4.5, p);
						
            		}
            	}
            }
		}, 26));
	}

	private void tExplosionChain(LivingEntity p) {

    	grillable.remove(p.getUniqueId());
        Holding.holding(null, p, 25l);

        String rn = gethero(p);
		
        final Location tl = gettargetblock(p,5).clone();
        final Location fl = p.getLocation().clone();
        
		p.getWorld().playSound(tl, Sound.BLOCK_TRIAL_SPAWNER_OMINOUS_ACTIVATE, 1.1f, 0f);
    	p.getWorld().spawnParticle(Particle.DRAGON_BREATH, tl, 500, 4, 1, 4, 0);
    	p.getWorld().spawnParticle(Particle.PORTAL, tl, 500, 4, 1, 4, 0);
    	p.getWorld().spawnParticle(Particle.INSTANT_EFFECT, tl, 500, 4, 1, 4, 0);
    	
    	
        ArrayList<Location> meats = new ArrayList<>();
        AtomicInteger j = new AtomicInteger();
        for(int i=0; i<20; i++) {
            Random random=new Random();
        	double number = random.nextDouble() * 3 * (random.nextBoolean() ? -1 : 1);
        	double number2 = random.nextDouble() * 3 * (random.nextBoolean() ? -1 : 1);
        	double number3 = random.nextDouble() * 7;
        	meats.add(tl.clone().add(number, number3, number2));
        }
        
        meats.forEach(l ->{
			int t= Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
                    Holding.holding(null, p, 10l);
                	tExplosion(p, l);
                }
			}, j.incrementAndGet()*4+25);
        	ordt.put(rn, t);
        });
		int t= Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
            	p.teleport(fl);
            }
		}, j.incrementAndGet()*4+30);
    	ordt.put(rn, t);
	}
	
	

	private HashMap<UUID, Integer> grillable = new HashMap<UUID, Integer>();
	public void grilled(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 9;
		if(d.getEntity().hasMetadata("enderboss") && grillable.containsKey(d.getEntity().getUniqueId())) 
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
	            	tExplosionChain(p);
					rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {


            	tExplosionChain(p);
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
            stand.getEquipment().setHelmet(new ItemStack(Material.CLOCK));
            stand.getEquipment().setChestplate(new ItemStack(Material.CLOCK));
            stand.getEquipment().setItemInMainHand(new ItemStack(Material.CLOCK));
            stand.getEquipment().setItemInOffHand(new ItemStack(Material.CLOCK));
            stand.getAttribute(Attribute.SCALE).setBaseValue(5);
        });
    }
    

	
    private void summonWoodPillar(LivingEntity boss, Location startLoc) {
        World world = startLoc.getWorld();
        String rn = gethero(boss);

        List<ArmorStand> pillars = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Location pillarLoc = startLoc.clone().add(0, i * 2, 0);
            pillars.add(spawnArmorStand(world, pillarLoc, rn));
        }

        BukkitTask bt = Bukkit.getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
            int tick = 0;

            @Override
            public void run() {
                Player target = EndercoreRaids.getheroes(boss).stream()
                        .filter(p -> p.isValid() && p.getWorld().equals(world))
                        .min((p1, p2) -> Double.compare(p1.getLocation().distance(startLoc), p2.getLocation().distance(startLoc)))
                        .orElse(null);

                if (target == null || tick >= 100) {
                    removeFingers(pillars,boss);
                    this.cancel();
                    return;
                }

                movePillarTowardsPlayer(pillars, target.getLocation());

                for (Entity e : world.getNearbyEntities(pillars.get(0).getLocation(), 2.5, 2.5, 2.5)) {
                    if (e instanceof Player && e != boss) {
                        Player player = (Player) e;
                        player.setVelocity(new Vector());
                        boss.setVelocity(boss.getVelocity().multiply(2.5));
                    }
                }

                tick = tick +2;
            }

            private void cancel() {
                Bukkit.getScheduler().cancelTask(blockt.remove(boss.getUniqueId()));
            }
        }, 0L, 2L); // 0틱 지연, 2틱 간격으로 실행

        blockt.put(boss.getUniqueId(), bt.getTaskId());
        ordt.put(rn, bt.getTaskId());
    }

    private void movePillarTowardsPlayer(List<ArmorStand> pillars, Location targetLoc) {
        for (ArmorStand block : pillars) {
        	block.getWorld().spawnParticle(Particle.PORTAL, block.getLocation(), 10, 1.5, 1.5, 1.5, 0.1);
        	block.getWorld().playSound(block.getLocation(), Sound.BLOCK_CHEST_LOCKED, 0.1f, 2f);
            Vector moveDirection = targetLoc.toVector().subtract(block.getLocation().toVector());
            double distance = moveDirection.length();

            if (distance > 0.6) {
                double speed = Math.log(distance + 0.6) * 0.2;
                moveDirection.normalize().multiply(speed);
                block.teleport(block.getLocation().add(moveDirection));
            }
        }
    }

    private void removeFingers(List<ArmorStand> pillars, LivingEntity p) {
        for (ArmorStand pillar : pillars) {
            pillar.remove();
            Location tl = pillar.getLocation().clone();
    		p.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, tl,1);
        	p.getWorld().playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.5f, 0.5f);
        	for(Entity e : pillar.getWorld().getNearbyEntities(tl, 4,4,4)) {
        		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
        			LivingEntity le = (LivingEntity)e;
					le.damage(6.5, p);
					le.teleport(tl);
        		}
        	}
        }
    }

	
	
	private HashMap<UUID, Boolean> handable = new HashMap<UUID, Boolean>();
	
	final private void hand(LivingEntity p) {
		p.getWorld().playSound(p, Sound.ENTITY_BREEZE_CHARGE, 0.6f, 0.6f);
		p.getWorld().playSound(p, Sound.BLOCK_END_PORTAL_SPAWN, 0.3f, 1.5f);

    	final World w = p.getWorld();
		final Location pl = gettargetblock(p,6).clone().add(0,-5,0);
		w.playSound(pl, Sound.ENTITY_SHULKER_TELEPORT, 1.0f, 0f);
		w.spawnParticle(Particle.END_ROD, pl, 150, 2,2,2);
		w.spawnParticle(Particle.REVERSE_PORTAL, pl, 150, 2,2,2);
		w.spawnParticle(Particle.ENCHANT, pl, 150, 2,2,2);
		

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			summonWoodPillar(p,pl);
            }
	   	}, 35);
	   	
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
     			chargable.putIfAbsent(p.getUniqueId(), true);
            }
	   	}, 80);
	}
	

	public void hand(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("enderboss")) 
		{
			Mob p = (Mob)d.getEntity();
			
			int sec = 10;
	
	
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

	public void teleportAndScatterEnderPearls(LivingEntity boss) {
	    List<Player> heroes = EndercoreRaids.getheroes(boss).stream()
	            .filter(hero -> hero.getWorld().equals(boss.getWorld())) // 같은 월드의 플레이어만 필터링
	            .collect(Collectors.toList());

	    if (heroes.isEmpty()) return;

	    List<Location> teleportLocations = new ArrayList<>();
	    Random random = new Random();

	    // 플레이어들의 위치에 랜덤 오프셋 추가하여 8개의 위치 생성
	    for (int i = 0; i < 7; i++) {
	        Player randomPlayer = heroes.get(random.nextInt(heroes.size()));
	        Location baseLocation = randomPlayer.getLocation();
	        double offsetX = random.nextDouble() * 10 - 5; // -5 ~ 5 사이의 랜덤값
	        double offsetZ = random.nextDouble() * 10 - 5;
	        double offsetY = random.nextDouble() * 2.5; // 약간의 높이 차이
	        Location randomLocation = baseLocation.clone().add(offsetX, offsetY, offsetZ);
	        teleportLocations.add(randomLocation);
	    }

	    // 텔레포트 및 엔더펄 뿌리기
	    int t = Bukkit.getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
	        private int currentIndex = 0;

	        @Override
	        public void run() {
	            World world = boss.getWorld();
	            if (currentIndex >= teleportLocations.size()) {
	                Bukkit.getScheduler().cancelTask(this.hashCode());
	                return;
	            }

	            Location teleportLocation = teleportLocations.get(currentIndex);
	            boss.teleport(teleportLocation); // 보스 텔레포트
	            world.playSound(teleportLocation, Sound.ENTITY_SHULKER_TELEPORT, 1.0f, 0f);
	            world.spawnParticle(Particle.END_ROD, teleportLocation, 150, 2,2,2);

	            for (int j = 0; j < 4; j++) {
	                DragonFireball enderPearl = (DragonFireball) world.spawnEntity(teleportLocation, EntityType.DRAGON_FIREBALL);
	                enderPearl.setShooter(boss);
	                enderPearl.setGlowing(true);
	                enderPearl.setGravity(true);
	                enderPearl.setYield(-1);
	                enderPearl.setIsIncendiary(false);
	                enderPearl.setVelocity(new Vector(
	                        random.nextDouble() * 1.5 - 0.8, // X 방향 랜덤
	                        random.nextDouble() * -0.1 +0.5, // Y 방향 위로 상승
	                        random.nextDouble() * 1.5 - 0.8 // Z 방향 랜덤
	                ));
	                enderPearl.setMetadata("enderbossPearl", new FixedMetadataValue(RMain.getInstance(), true));
	                enderPearl.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
	                	public void run() 
	                    {	
	    	                enderPearl.setVelocity(new Vector(0,-2,0));
	                    }
	        	   	}, 13);
	            }

	            // 다음 위치로 이동
	            currentIndex++;
	        }
	    }, 20L, 12L).getTaskId(); // 10틱(0.5초)마다 실행
        ordt.put(gethero(boss), t);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
            	porkable.remove(boss.getUniqueId());
            }
	   	}, 60);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			grillable.putIfAbsent(boss.getUniqueId(), 1);
            }
	   	}, 80);
	}

	private HashMap<UUID, Boolean> porkable = new HashMap<UUID, Boolean>();
	
	
	public void teleportAndScatterEnderPearls(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("enderboss") && (d.getEntity() instanceof Mob)) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 13;

			if(ordeal.containsKey(p.getUniqueId())) {
				return;
			}

			if(p.hasMetadata("failed") || !porkable.containsKey(p.getUniqueId())) {
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
		                	teleportAndScatterEnderPearls(p);
		                	aicooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {

		            	teleportAndScatterEnderPearls(p);
	                	aicooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}

	

	private HashMap<UUID, Boolean> chargable = new HashMap<UUID, Boolean>();
	private HashMap<UUID, Integer> illusionTask = new HashMap<UUID, Integer>();

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
	public void illusionCharge(EntityDamageByEntityEvent d) {
	    if(d.getEntity().hasMetadata("enderboss")) {
	        final LivingEntity p = (LivingEntity)d.getEntity();

	        if (checkAndApplyCharge(p, d)) return;

	        if(p.hasMetadata("raid")) {
	            Optional<Player> hero = EndercoreRaids.getheroes(p).stream()
	                .filter(pe -> pe.getWorld().equals(p.getWorld()))
	                .findAny();

	            if(!hero.isPresent() || p.hasMetadata("failed")) {
	                return;
	            }

	            final Location tl = hero.get().getLocation().clone().add(0, 0.2, 0);
	            illusionCharge(p, tl);
	        }
	    }
	}

	private void illusionStart(LivingEntity p, final Location tl) {
	    final Location cl = tl.clone();
	    final World w = cl.getWorld();

	    p.swingMainHand();
	    p.getWorld().playSound(cl, Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 1.0f, 0f);
	    p.getWorld().playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 2f);
	    p.playEffect(EntityEffect.WARDEN_TENDRIL_SHAKE);
	    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 65, 1));
	    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 65, 3));
	    p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 65, 1));
	    p.getWorld().spawnParticle(Particle.ENTITY_EFFECT, cl, 500, 5, 1, 5, 1, Color.PURPLE);
	    p.getWorld().spawnParticle(Particle.EFFECT, cl, 1000, 5, 1, 5, 1);

	    HashSet<Location> particleLocations = calculateParticleLocations(cl, 4, 80);

	    int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	        @Override
	        public void run() {
	            if(p.isDead()) {
	                return;
	            }

	            for (Location particleLocation : particleLocations) {
	                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(255, 105, 180), 1.5f); // 진한 분홍색
	                w.spawnParticle(Particle.DUST, particleLocation, 1, dustOptions);
	            }

	            p.getWorld().spawnParticle(Particle.ENTITY_EFFECT, cl, 500, 4, 1, 4, 1, Color.PURPLE);

                double offsetX = (Math.random() - 0.5) * 6; // -3 ~ 3 범위의 x좌표
                double offsetY = (Math.random() - 0.5) * 6; // -3 ~ 3 범위의 y좌표
                double offsetZ = (Math.random() - 0.5) * 6; // -3 ~ 3 범위의 z좌표

                Location randomLocation = cl.clone().add(offsetX, offsetY, offsetZ);
	            p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, randomLocation, 10);
	            p.getWorld().playSound(randomLocation, Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 0.1f, 2f);
	            // 4범위 내 적들에게 랜덤한 위치로 순간이동 및 피해 적용
	            for(Entity e : cl.getWorld().getNearbyEntities(cl, 4, 4, 4)) {
	                if(p != e && e instanceof LivingEntity && !(e.hasMetadata("fake")) && !(e.hasMetadata("portal"))) {
	                    LivingEntity le = (LivingEntity)e;

	                    // 적을 랜덤한 위치로 순간이동
	                    le.teleport(randomLocation);

	                    // 순간이동 후 피해
	                    le.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 60, 1));
	                    le.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 60, 1));
	                    le.damage(3.0, p);
	                }
	            }
	        }
	    }, 40, 3);

	    illusionTask.put(p.getUniqueId(), task);

	    // 일정 시간이 지나면 환각 효과와 스킬을 종료
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	        @Override
	        public void run() {
	            if(illusionTask.containsKey(p.getUniqueId())) {
	                Bukkit.getScheduler().cancelTask(illusionTask.get(p.getUniqueId()));
	                illusionTask.remove(p.getUniqueId());
	            }
	            chargable.remove(p.getUniqueId());
	        }
	    }, 85);

	    // 일정 시간 후 복원 가능 상태로 변경
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	        @Override
	        public void run() {
	            porkable.put(p.getUniqueId(), true);
	        }
	    }, 180);
	}


	private void illusionCharge(LivingEntity p, Location tl) {
	    if(ordeal.containsKey(p.getUniqueId())) {
	        return;
	    }
	    if(p.hasMetadata("failed") || !chargable.containsKey(p.getUniqueId())) {
	        return;
	    }
	    if(rb8cooldown.containsKey(p.getUniqueId())) {
	        long timer = (rb8cooldown.get(p.getUniqueId()) / 1000 + 8) - System.currentTimeMillis() / 1000;
	        if (timer < 0) {
	            rb8cooldown.remove(p.getUniqueId()); 
	            illusionStart(p, tl);
	            rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	        }
	    } else {
	        illusionStart(p, tl);
	        rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	    }
	}
	


	
	final private void teleportEx(Location ptl, LivingEntity p, Integer dur) {
		
		final Location fl = p.getLocation().clone();
    	
    	Explosion(p, ptl, fl);
	}
	public void teleportEx(EntityDamageByEntityEvent d) 
	{
		if((d.getEntity() instanceof Mob) && d.getEntity().hasMetadata("enderboss")) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 4;
	        

			if(ordeal.containsKey(p.getUniqueId())) {
				return;
			}
            if (checkAndApplyCharge(p, d)) return;
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
		                	

			                teleportEx(ptl, p,300);
		                    
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                 		@Override
		                    	public void run() 
		                        {	
		                 			handable.put(p.getUniqueId(), true);
		        	            }
		                    }, 46); 
		                    
	             			
							rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {


		                teleportEx(ptl, p,300);
	                    
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                 		@Override
	                    	public void run() 
	                        {	
	                 			handable.put(p.getUniqueId(), true);
	        	            }
	                    }, 46); 
	                    
						rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}

	private static HashMap<UUID, Boolean> counterable = new HashMap<UUID, Boolean>();
	
	private HashMap<UUID, Integer> count = new HashMap<UUID, Integer>();

	public static HashMap<UUID, Integer> countt = new HashMap<UUID, Integer>();
	

	final private void nightmare(LivingEntity p, Location rl, String rn, Boolean j) {
        try {
        	if(!j) {

        		for(int i = 0; i<3; i++) {

                    int i1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            Player tpe = EndercoreRaids.getheroes(p).stream().filter(pe ->!pe.isDead()&&pe.getWorld().equals(p.getWorld())).findAny().get();
                            final Location tpel = tpe.getLocation().clone();
                            final Location tl = tpel.clone().add(tpel.clone().getDirection().normalize().multiply(-2.3)).add(1, 0.5, 1);
                            p.teleport(tl);
                			p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation().clone(), 50,0.5,0.5,0.5,0.02);
                            EndercoreRaids.getheroes(p).forEach(pe ->{
                            	pe.playSound(p, Sound.ENTITY_SHULKER_TELEPORT, 1f, 1.26f);
                            });
                            int i1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                                @Override
                                public void run() {
                                	tpe.playSound(p, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, 1.5f);
                                	nightswoop(p,tl,tpel,j);
                                	p.swingMainHand();
                                }
                            }, 10);
                    		ordt.put(rn, i1);
                        }
                    }, i*18);
            		ordt.put(rn, i1); 
        		}
        		
        	}
        	else {

                Player tpe = EndercoreRaids.getheroes(p).stream().filter(pe ->!pe.isDead()&&pe.getWorld().equals(p.getWorld())).findAny().get();
                final Location tpel = tpe.getLocation().clone();
                final Location tl = tpel.clone().add(tpel.clone().getDirection().normalize().multiply(-2.2)).add(1, 0.5, 0);
                p.teleport(tl);
    			p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation().clone(), 50,0.5,0.5,0.5,0.02);
                EndercoreRaids.getheroes(p).forEach(pe ->{
                	pe.playSound(p, Sound.ENTITY_SHULKER_TELEPORT, 0.6f, 1.26f);
                	pe.playSound(p, Sound.BLOCK_VAULT_ACTIVATE, 1.2f, 0.9f);
                });
                int i1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                    	tpe.playSound(p, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, 2f);
                    	nightswoop(p,tl,tpel,j);
                    	p.swingMainHand();
                    }
                }, 25);
        		ordt.put(rn, i1); 
                int i11 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                    	tpe.playSound(p, Sound.ENTITY_ENDER_DRAGON_FLAP, 1f, 1.5f);
            			p.getWorld().spawnParticle(Particle.FLASH, p.getLocation().clone(), 3,0.2,0.2,0.2);
                    	counterable.put(p.getUniqueId(), true);
                    }
                }, 12);
        		ordt.put(rn, i11); 

                int i2 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                    @Override
                    public void run() {

                		for(int i = 0; i<5; i++) {

                            int i1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                                @Override
                                public void run() {
                        			p.getWorld().spawnParticle(Particle.SMALL_GUST, p.getLocation().clone(), 30,1,1,1,0.1);
                                	p.swingMainHand();
                                	tpe.playSound(tpe, Sound.ENTITY_ZOGLIN_ATTACK, 0.5f, 1.87f);
                                	if(tpe.getWorld().equals(p.getWorld())) {
                                    	p.teleport(tpe);
                                    	tpe.damage(2,p);
                                	}
                                }
                            }, i*4);
                    		ordt.put(rn, i1); 
                		}
                    	counterable.remove(p.getUniqueId());
                		
                    }
                }, 35);
        		ordt.put(rn, i2); 
        		countt.put(p.getUniqueId(), i2);
            
        	}
        }
        catch(NullPointerException | NoSuchElementException | IllegalArgumentException e) {
        	return;
        }
	}

	final private void nightswoop(LivingEntity p, Location pl, Location tl, Boolean j) 
	{

        List<Location> line = new ArrayList<>();
        final Vector vec = tl.clone().toVector().subtract(pl.clone().toVector());

        double increasement = 0.1;
        if(j) {
        	increasement = 0.2;
        }
        for(double an =0; an<tl.distance(pl)+0.3; an+=increasement) {
        	line.add(pl.clone().add(vec.clone().normalize().multiply(an)));
        }

    	AtomicInteger u = new AtomicInteger();
        line.forEach(l -> {
    		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
                	p.setRotation(l.getYaw(),l.getPitch());
                	p.setGliding(true);
    				p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 3, 1, 1, 1);
    				p.teleport(l);
    	            for(Entity e : l.getWorld().getNearbyEntities(l, 1, 1, 1)) {
    	                if(p != e && e instanceof Player && !(e.hasMetadata("fake")) && !(e.hasMetadata("portal"))) {
    	                	Player le = (Player)e;

    	                    le.damage(3.0, p);
    	                }
    	            }
                }
    		},u.getAndIncrement()/5);
    	});
	}
	
	private void killAll(LivingEntity p, String rn) {

		

        if(ordt.containsKey(rn)) {
        	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        	ordt.removeAll(rn);
        }
    	ordeal.remove(p.getUniqueId());
    	rb5cooldown.remove(p.getUniqueId()); 
        for(Player pe : EndercoreRaids.getheroes(p)) {
    		if(pe.getWorld().equals(p.getWorld()) && pe.getWorld() == p.getWorld()) {
    			count.remove(p.getUniqueId());
				if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
					pe.sendMessage(ChatColor.BOLD + "차원파괴자: " + ChatColor.RED + "결국 모든 것은 무의미했다.");
				}
				else {
					pe.sendMessage(ChatColor.BOLD + "Destroyer: " + ChatColor.RED + "In the end, it was all meaningless.");
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
        		p.removeMetadata("fake", RMain.getInstance());
        		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
                p.setInvulnerable(false);
                Holding.ale(p).setInvulnerable(false);
				p.getWorld().spawnParticle(Particle.SQUID_INK, p.getLocation(), 3000, 10,10,10);	
                for(Player pe : EndercoreRaids.getheroes(p)) {
            		p.removeMetadata("fake", RMain.getInstance());
            		pe.setHealth(0);
                }
            }
        }, 5); 
	}
	
	public void nightCounter(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("enderboss") && d.getEntity() instanceof LivingEntity&& d.getEntity().hasMetadata("ruined")) 
		{
			LivingEntity p = (LivingEntity)d.getEntity();
			String rn = gethero(p);
			
			if(ordeal.containsKey(p.getUniqueId())) {
				d.setCancelled(true);
				if(counterable.containsKey(p.getUniqueId())) {
	    			p.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, p.getLocation().clone(), 150,2,2,2);

	    			
					count.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
					count.putIfAbsent(p.getUniqueId(), 1);
					
	                Location rl = EndercoreRaids.getraidloc(p).clone();
			        p.playHurtAnimation(0);
			        Bukkit.getScheduler().cancelTask(countt.get(p.getUniqueId()));
                	counterable.remove(p.getUniqueId());
	                p.teleport(rl);

			        EndercoreRaids.getheroes(p).forEach(pe ->{
			        	pe.playSound(pe, Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1, 1.1f);
			        	pe.sendMessage(ChatColor.AQUA + "[" + count.get(p.getUniqueId()) + "/5]");
			        	Random random1=new Random();
			        	double number1 = (random1.nextDouble()+1) * 5 * (random1.nextBoolean() ? -1 : 1);
			        	double number21 = (random1.nextDouble()+1) * 5 * (random1.nextBoolean() ? -1 : 1);
			        	Location esl1 = rl.clone().add(number1, 0.5, number21);
			        	pe.teleport(esl1);
			        });
			        
			        if(count.get(p.getUniqueId()) == 5) {
		                p.teleport(rl);
			        	
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
		                for(Player pe : EndercoreRaids.getheroes(p)) {
							if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
								pe.sendMessage(ChatColor.BOLD + "차원파괴자: " + ChatColor.GRAY + "이 정도라면, 너희를 인정하지 않을 수 없군.");
							}
							else {
								pe.sendMessage(ChatColor.BOLD + "Destroyer: " + ChatColor.GRAY + "At this level, I can’t help but acknowledge you.");
							}
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
					killAll(p, rn);
				}
			}
		}
	}
	    
	final long ORDEALTIME = 1000;
		
	final private void nightmareevent(LivingEntity p, EntityDamageByEntityEvent d) {

    	ordeal.put(p.getUniqueId(), true);
		String rn = gethero(p);
        Location rl = EndercoreRaids.getraidloc(p).clone();

        if(ordt.containsKey(rn)) {
        	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        	ordt.removeAll(rn);
        }
		p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());
        d.setCancelled(true);
        Holding.holding(null, p, ORDEALTIME);
        Holding.invur(p, 100l);
        Holding.untouchable(p, ORDEALTIME);
        p.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
        
        EndercoreRaids.getheroes(p).forEach(pe ->{
        	Holding.invur(pe, 60l);
        	AtomicReference<String> msg = new AtomicReference<String>(ChatColor.BOLD+"Attack the enemy at the right time");
        	if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		msg.set(ChatColor.BOLD+"타이밍에 맞춰 적을 공격하세요");
			}
    		pe.sendTitle(ChatColor.DARK_PURPLE + (ChatColor.MAGIC + "123456"), ChatColor.DARK_PURPLE + (ChatColor.MAGIC + "123456"), 20, 35, 20);
				Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {	
            		pe.sendMessage(msg.get());
                }
            }, 20);
        });
        
		p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation().clone(), 200,2,2,2,0.1);
		p.getWorld().spawnParticle(Particle.DRAGON_BREATH, p.getLocation().clone(), 200,2,2,2,0.1);
        int i1 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
        	Boolean j = false;
            @Override
            public void run() {
                nightmare(Holding.ale(p),rl,rn, j);
                j = !j;
            }
        }, 100, 70);
		ordt.put(rn, i1); 

			int i2 = Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {	
				killAll(p, rn);
            }
        }, ORDEALTIME+1); 
		ordt.put(rn, i2); 
		
	}
	
	public void nightMare(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 70;
		if(d.getEntity().hasMetadata("enderboss") && d.getEntity() instanceof LivingEntity&& !d.isCancelled() && d.getEntity().hasMetadata("ruined")) 
		{
			LivingEntity p = (LivingEntity)d.getEntity();
			if(p.hasMetadata("failed")) {
				return;
			}
			if(!(p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2)|| !ordealable.containsKey(p.getUniqueId())) {
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