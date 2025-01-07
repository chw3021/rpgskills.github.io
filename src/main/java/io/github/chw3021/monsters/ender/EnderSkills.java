package io.github.chw3021.monsters.ender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
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
import org.bukkit.block.BlockFace;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
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
import io.github.chw3021.monsters.raids.EndercoreRaids;
import io.github.chw3021.monsters.raids.NethercoreRaids;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;



public class EnderSkills extends EndercoreRaids{

	
	Holding hold = Holding.getInstance();
	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb4cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb6cooldown = new HashMap<UUID, Long>();
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
				l.getWorld().spawnParticle(Particle.END_ROD, l, 100);
				l.getWorld().playSound(l, Sound.ENTITY_SHULKER_TELEPORT, 1f, 1.5f);

        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						l.getWorld().spawnParticle(Particle.PORTAL, l, 300);
						l.getWorld().playSound(l, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1f, 1.5f);
	            		for(Entity e : l.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5)) {
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
	

	Material[] cookeds = new Material[] {Material.END_CRYSTAL, 
			Material.END_ROD,
			Material.ENDER_PEARL
	};
	
	public void bowshoot(EntityShootBowEvent ev) 
	{
		if(ev.getEntity().hasMetadata("enderboss")){

			ev.setCancelled(true);
			
		    LivingEntity p = ev.getEntity();
		    
        	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_SHOOT, 0.5f, 2f);

			p.getWorld().spawnParticle(Particle.END_ROD, p.getLocation(), 10);
			AtomicInteger j = new AtomicInteger();



			for(Material c : cookeds) {
        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	                	p.swingMainHand();
	                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 1f, 1.2f);
	                    EnderPearl ws = (EnderPearl) p.launchProjectile(EnderPearl.class);
	                    ws.setItem(new ItemStack(c));
	                    ws.setShooter(p);
	                    ws.setGlowing(true);
	                    ws.setVelocity(p.getLocation().getDirection().normalize().multiply(1.3));
	        			ws.setMetadata("enderbossPearl", new FixedMetadataValue(RMain.getInstance(), true));
	                }
	            }, j.getAndIncrement()*4+5); 
			}


		 }
	}
	
	private void teleportExplosion(LivingEntity p, final Location tl, final Location fl) {
    	p.getWorld().playSound(tl, Sound.ENTITY_EVOKER_CAST_SPELL, 0.5f, 2f);
		p.getWorld().spawnParticle(Particle.END_ROD, tl, 20);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
        		p.getWorld().spawnParticle(Particle.PORTAL, tl, 20);
            	p.getWorld().playSound(tl, Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 1.5f);
            	p.teleport(tl);
            	for(Entity e : p.getWorld().getNearbyEntities(tl, 2,2,2)) {
            		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
            			LivingEntity le = (LivingEntity)e;
            			le.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA,100,100,false,false));
            			le.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,100,100,false,false));
						
            		}
            	}
            }
		}, 13);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
        		p.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, tl,1);
            	p.getWorld().playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.5f, 0.5f);
            	p.teleport(fl);
            	for(Entity e : p.getWorld().getNearbyEntities(tl, 3,3,3)) {
            		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
            			LivingEntity le = (LivingEntity)e;
						le.damage(4.5, p);
						
            		}
            	}
            }
		}, 26);
	}



	private HashMap<UUID, Integer> grillable = new HashMap<UUID, Integer>();
	public void grilled(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 4;
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

	            	grillable.remove(p.getUniqueId());
	                Holding.holding(null, p, 25l);

	                String rn = gethero(p);
					
	                Location tl = gettargetblock(p,5).clone();
	                
					p.getWorld().playSound(tl, Sound.BLOCK_TRIAL_SPAWNER_OMINOUS_ACTIVATE, 1.1f, 0f);
                	p.getWorld().spawnParticle(Particle.DRAGON_BREATH, tl, 500, 4, 1, 4, 0);
                	p.getWorld().spawnParticle(Particle.PORTAL, tl, 500, 4, 1, 4, 0);
                	p.getWorld().spawnParticle(Particle.INSTANT_EFFECT, tl, 500, 4, 1, 4, 0);
                	
                	
                    ArrayList<Location> meats = new ArrayList<>();
                    AtomicInteger j = new AtomicInteger();
                    for(int i=0; i<41; i++) {
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
			                	teleportExplosion(p, l, tl);
			                }
						}, j.incrementAndGet()*2+25);
	                	ordt.put(rn, t);
                    });
					rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {


            	grillable.remove(p.getUniqueId());
                Holding.holding(null, p, 25l);

                String rn = gethero(p);
				
                Location tl = gettargetblock(p,5).clone();
                
				p.getWorld().playSound(tl, Sound.BLOCK_TRIAL_SPAWNER_OMINOUS_ACTIVATE, 1.1f, 0f);
            	p.getWorld().spawnParticle(Particle.DRAGON_BREATH, tl, 500, 4, 1, 4, 0);
            	p.getWorld().spawnParticle(Particle.PORTAL, tl, 500, 4, 1, 4, 0);
            	p.getWorld().spawnParticle(Particle.INSTANT_EFFECT, tl, 500, 4, 1, 4, 0);
            	
            	
                ArrayList<Location> meats = new ArrayList<>();
                AtomicInteger j = new AtomicInteger();
                for(int i=0; i<41; i++) {
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
		                	teleportExplosion(p, l, tl);
		                }
					}, j.incrementAndGet()*2+25);
                	ordt.put(rn, t);
                });
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
            stand.getAttribute(Attribute.SCALE).setBaseValue(4);
        });
    }
    

	
    private void summonWoodPillar(LivingEntity boss, Location startLoc) {
        World world = startLoc.getWorld();
        String rn = gethero(boss);

        List<ArmorStand> pillars = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Location pillarLoc = startLoc.clone().add(0, i * 0.5, 0);
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
                    removeFingers(pillars,boss);
                    this.cancel();
                    return;
                }

                movePillarTowardsPlayer(pillars, target.getLocation());

                for (Entity e : world.getNearbyEntities(pillars.get(0).getLocation(), 2.5, 2.5, 2.5)) {
                    if (e instanceof Player && e != boss) {
                        Player player = (Player) e;
                        player.damage(2, boss);
                        player.setVelocity(player.getVelocity().multiply(0.1));
                        boss.setVelocity(boss.getVelocity().multiply(2));
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
        	block.getWorld().spawnParticle(Particle.PORTAL, block.getLocation(), 10, 1.5, 1.5, 1.5, 0.1);
        	block.getWorld().playSound(block.getLocation(), Sound.BLOCK_CHEST_LOCKED, 0.1f, 2f);
            Vector moveDirection = targetLoc.toVector().subtract(block.getLocation().toVector());
            double distance = moveDirection.length();

            if (distance > 0.5) {
                double speed = Math.log(distance + 0.5) * 0.1;
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
        	for(Entity e : pillar.getWorld().getNearbyEntities(tl, 3,3,3)) {
        		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
        			LivingEntity le = (LivingEntity)e;
					le.damage(4.5, p);
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
		Location pl = gettargetblock(p,6);
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
     			chargable.putIfAbsent(p.getUniqueId(), true);
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

	public void teleportAndScatterEnderPearls(LivingEntity boss) {
	    List<Player> heroes = NethercoreRaids.getheroes(boss).stream()
	            .filter(hero -> hero.getWorld().equals(boss.getWorld())) // 같은 월드의 플레이어만 필터링
	            .collect(Collectors.toList());

	    if (heroes.isEmpty()) return;

	    List<Location> teleportLocations = new ArrayList<>();
	    Random random = new Random();

	    // 플레이어들의 위치에 랜덤 오프셋 추가하여 8개의 위치 생성
	    for (int i = 0; i < 6; i++) {
	        Player randomPlayer = heroes.get(random.nextInt(heroes.size()));
	        Location baseLocation = randomPlayer.getLocation();
	        double offsetX = random.nextDouble() * 10 - 5; // -5 ~ 5 사이의 랜덤값
	        double offsetZ = random.nextDouble() * 10 - 5;
	        double offsetY = random.nextDouble() * 3; // 약간의 높이 차이
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

	            for (int j = 0; j < 6; j++) {
	                EnderPearl enderPearl = (EnderPearl) world.spawnEntity(teleportLocation, EntityType.ENDER_PEARL);
	                enderPearl.setShooter(boss);
	                enderPearl.setGlowing(true);
	                enderPearl.setVelocity(new Vector(
	                        random.nextDouble() * 2 - 1, // X 방향 랜덤
	                        random.nextDouble() * 0.5 + 0.5, // Y 방향 위로 상승
	                        random.nextDouble() * 2 - 1 // Z 방향 랜덤
	                ));
	                enderPearl.setMetadata("enderbossPearl", new FixedMetadataValue(RMain.getInstance(), true));
	            }

	            // 다음 위치로 이동
	            currentIndex++;
	        }
	    }, 20L, 10L).getTaskId(); // 10틱(0.5초)마다 실행
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
			int sec = 7;

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
	            if(!NethercoreRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld())) || p.hasMetadata("failed")) {
	                return;
	            }

	            final Location tl = NethercoreRaids.getheroes(p).stream()
	                    .filter(pe -> pe.getWorld().equals(p.getWorld()))
	                    .findAny().get().getLocation().clone().add(0, 0.2, 0);
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
	            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 0.1f, 2f);
	    	    p.getWorld().spawnParticle(Particle.ENTITY_EFFECT, cl, 500, 4, 1, 4, 1, Color.PURPLE);


	            for(Entity e : cl.getWorld().getNearbyEntities(cl, 4, 4, 4)) {
	                if(p != e && e instanceof LivingEntity && !(e.hasMetadata("fake")) && !(e.hasMetadata("portal"))) {
	                    LivingEntity le = (LivingEntity)e;
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
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
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
	            rb8cooldown.remove(p.getUniqueId()); // 쿨타임 끝났으면
	            illusionStart(p, tl);
	            rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	        }
	    } else {
	        illusionStart(p, tl);
	        rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	    }
	}
	


	private HashMap<UUID, Boolean> smkable = new HashMap<UUID, Boolean>();
	
	final private void smoker(Location ptl, LivingEntity p, Integer dur) {
		
		String rn = gethero(p);
		World w = ptl.getWorld();
		p.getWorld().playSound(ptl, Sound.BLOCK_SMOKER_SMOKE, 1.0f, 0f);
    	p.getWorld().playSound(ptl, Sound.BLOCK_FIRE_AMBIENT, 1.0f, 2f);
    	
    	w.spawnParticle(Particle.DUST_PILLAR, ptl, 300,2,1,2, getBd(Material.WHITE_WOOL));
    	
        int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
                AreaEffectCloud cloud = (AreaEffectCloud) w.spawnEntity(ptl, EntityType.AREA_EFFECT_CLOUD);
                cloud.setParticle(Particle.WHITE_SMOKE);
                cloud.addCustomEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 1, false, false, false), false);
        		cloud.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
        		cloud.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
        		cloud.setMetadata("piglincloud", new FixedMetadataValue(RMain.getInstance(), rn));
                cloud.setRadius(2f);
                cloud.setSource(p);
                cloud.setSilent(false);
                cloud.setColor(Color.WHITE);
                cloud.setDuration(dur);

            	smkable.remove(p.getUniqueId());
            }
        }, 18); 
        ordt.put(gethero(p), t);
	}
	
	public void cloudApplied(AreaEffectCloudApplyEvent ev) {
		if(ev.getEntity().hasMetadata("piglincloud")) {
			 AreaEffectCloud cloud = ev.getEntity();
			 LivingEntity p = (LivingEntity) cloud.getSource();

             for(Entity e : cloud.getWorld().getNearbyEntities(cloud.getLocation().clone(),2,2,2)) {
						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
							LivingEntity le = (LivingEntity)e;
							le.damage(3.5,p);
						}
                 }
			 
		}
	}
	
	public void smoker(EntityDamageByEntityEvent d) 
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
		                	
			                Holding.holding(null, p, 10l);

			                smoker(ptl, p,300);
		                    
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

	                	
		                Holding.holding(null, p, 10l);

		                smoker(ptl, p,300);
	                    
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

	public static HashMap<UUID, Integer> count = new HashMap<UUID, Integer>();

	public HashMap<UUID, Integer> ast = new HashMap<UUID, Integer>();//ArmorStands damage 태스크 저장
	public HashMap<UUID, Integer> asdt = new HashMap<UUID, Integer>();//ArmorStands remove 태스크 저장
	
	final private void asSpawn(Player pe, String rn, LivingEntity p, final Location fl, int a) {

		final World w = fl.getWorld();
		
		if(a%4==0) {
			FallingBlock newmob = w.spawnFallingBlock(fl, getBd(Material.YELLOW_CANDLE_CAKE));
    		newmob.setMetadata("yellowcake"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setGravity(false);
    		newmob.setCustomNameVisible(false);
    		newmob.setInvulnerable(true);
    		newmob.setMetadata("cake"+rn, new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
			int t2 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() {
	            	
	            	Location pel = Holding.ale(pe).getLocation();
	            	Location arl = Holding.ale(newmob).getLocation();
	            	
	            	if(pel.getWorld().equals(arl.getWorld())) {
	            		Vector v = pel.clone().toVector().subtract(arl.clone().toVector()).clone().normalize().multiply(0.025);
	            		if(pel.distance(arl)>10) {
	            			v.multiply(10);
	            		}
	            		Holding.ale(newmob).setVelocity(v);
	            	}
	            	
	        		for(Entity e : newmob.getWorld().getNearbyEntities(newmob.getLocation().clone(), 1, 5, 1)) {
						if(p!=e && e instanceof Player) {
							if(ast.containsKey(newmob.getUniqueId())) {
								Bukkit.getScheduler().cancelTask(ast.remove(newmob.getUniqueId()));
							}
		                	Holding.ale(newmob).remove();
		                	newmob.getWorld().spawnParticle(Particle.HEART, newmob.getLocation(), 20,1,1,1);
		                	pe.getWorld().playSound(pe.getLocation(), Sound.ENTITY_GENERIC_EAT, 1, 2);
		                	pe.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 20, 20, false,false,false));
		                	return;
						}
	            	}
	            }
	        }, 0,3);
			ordt.put(rn, t2);
			ast.put(newmob.getUniqueId(), t2);
		}
		else {
			

			FallingBlock newmob = w.spawnFallingBlock(fl, getBd(Material.RED_CANDLE_CAKE));

			newmob.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
			newmob.setGravity(false);
			newmob.setCustomNameVisible(false);
			newmob.setInvulnerable(true);
			newmob.setMetadata("cake"+rn, new FixedMetadataValue(RMain.getInstance(), true));
			newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), rn));
			newmob.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
			newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
			
			int t2 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() {
	            	
	            	Location pel = Holding.ale(pe).getLocation();
	            	Location arl = Holding.ale(newmob).getLocation();

	            	if(pel.getWorld().equals(arl.getWorld())) {
	            		Vector v = pel.clone().toVector().subtract(arl.clone().toVector()).clone().normalize().multiply(0.04);
	            		if(pel.distance(arl)>5) {
	            			v.multiply(5);
	            		}
	            		Holding.ale(newmob).setVelocity(v);
	            	}
	            	
	            	
	        		for(Entity e : newmob.getWorld().getNearbyEntities(newmob.getLocation().clone(), 1, 5, 1)) {
						if(p!=e && e instanceof Player) {
							Player le = (Player)e;
							le.damage(3,p);
						}
	            	}
	            }
	        }, 0,3);
			ordt.put(rn, t2);
			ast.put(newmob.getUniqueId(), t2);
		}
		
		
	}

	final private void cake(LivingEntity p, int a) {
		String rn = p.getMetadata("raid").get(0).asString();
        for(Player pe : NethercoreRaids.getheroes(p)) {
        	
        	if(!pe.getWorld().equals(p.getWorld()) || pe.isDead()) {
        		continue;
        	}
        	
            smoker(pe.getLocation(), p, 40);
            
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
		Boolean bool = p.getWorld().getEntities().stream().anyMatch(e -> e.hasMetadata("yellowcake"+rn));
		if(bool) {
    		Holding.invur(p, 20l);
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
		p.setHealth(p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2);
        d.setCancelled(true);
        
        Long ordealTime = 480L;
        
        
    	p.teleport(rl.clone().add(0, 1, 0));
        Holding.holding(null, p, ordealTime);
        Holding.untouchable(p, ordealTime);
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
        }, ordealTime);
		ordt.put(rn, t3);
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
		if(d.getEntity().hasMetadata("enderboss") && d.getEntity().hasMetadata("ruined")&& !d.getEntity().hasMetadata("failed")) 
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