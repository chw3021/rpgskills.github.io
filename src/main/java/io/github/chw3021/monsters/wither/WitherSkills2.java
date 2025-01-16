package io.github.chw3021.monsters.wither;

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
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.NethercoreRaids;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;



public class WitherSkills2 extends Summoned{

	
	Holding hold = Holding.getInstance();
	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb4cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb6cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb8cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> shcooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> aicooldown = new HashMap<UUID, Long>();
	

	
	private static final WitherSkills2 instance = new WitherSkills2 ();
	public static WitherSkills2 getInstance()
	{
		return instance;
	}
	
	
	public void hit(ProjectileHitEvent d) 
	{
		if(d.getEntity() instanceof Projectile) 
		{
			Projectile po = (Projectile)d.getEntity();
			if(po.getShooter() instanceof LivingEntity) {
				LivingEntity p = (LivingEntity) po.getShooter();
				if(po.hasMetadata("")) {
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
	

	
	public void bowshoot(EntityShootBowEvent ev) 
	{
		if(ev.getEntity().hasMetadata("witherboss")){

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
    		mmf.setCustomModelData(3008);
    		mainf.setItemMeta(mmf);

    		sendItemChange(p, getherotype(rn), mainf);
    		
			ev.setCancelled(true);
		    p.swingMainHand();
		    p.playEffect(EntityEffect.ZOGLIN_ATTACK);
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SKELETON_STEP, 1.0f, 0.2f);
            int sweep = 6;
            
			for(int i = 0; i<sweep; i++) {
                ordt.put(rn, Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
	                {	
            			for(int i = 0; i<6; i++) {
                            ordt.put(rn, Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                         		@Override
                            	public void run() 
            	                {	
                         			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 5,0,false,false));
            	                	p.setVelocity(v.clone().multiply(0.666));
            						for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),2, 2, 2)) {
            							if(e instanceof LivingEntity&&  !(e.hasMetadata("portal")) && e!=p) {
            								LivingEntity le = (LivingEntity)e;
            								le.damage(6,p);
            								Holding.holding(null, le, 5l);
            							}
            						}
            	                	
            		            }
                    	   	}, i));
            			}
    	    			Location pl = p.getLocation().clone();
                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 0.6f, 1.4f);
    					p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, pl, 15, 2,1,2);
         				p.getWorld().spawnParticle(Particle.SQUID_INK, pl, 30);
         				p.swingMainHand();
	                	
		            }
        	   	}, i*10+6));
			}

		 }
	}



	private void burst(LivingEntity p) {

       

        Location ptl = gettargetblock(p,4).clone();
        
		p.getWorld().playSound(ptl, Sound.BLOCK_LAVA_AMBIENT, 1f, 2f);
    	p.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, ptl, 100, 4, 1, 4, 0, new Particle.DustTransition(Color.ORANGE, Color.RED, 5f));

        
		ArrayList<Location> line = new ArrayList<Location>();
        for(int d = 0; d <= 7; d += 1) {
				line.add(ptl.clone().add(0, d, 0).setDirection(BlockFace.UP.getDirection()));
        }
        
        String rn = gethero(p);

		ItemStack mainf = new ItemStack(Material.BLAZE_ROD);
		ItemMeta mmf = mainf.getItemMeta();
		mmf.setCustomModelData(9008);
		mainf.setItemMeta(mmf);

		sendItemChange(p, getherotype(rn), mainf);
		
    	World w = ptl.getWorld();
		

    	
        Holding.holding(null, p, 34l);
        
		w.spawnParticle(Particle.PORTAL, ptl, 500, 4,2,4,0); 
		w.spawnParticle(Particle.SOUL, ptl, 500, 4,1.5,4,0); 
		w.spawnParticle(Particle.SQUID_INK, ptl, 500, 4,1.5,4,0); 
		w.playSound(p.getLocation(), Sound.ENTITY_WITHER_SKELETON_AMBIENT, 1f, 0f);
		w.playSound(p.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 1f, 2f);

		int ta = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {

            	HashSet<Location> lss = new HashSet<>();
            	for(int ix =-3; ix<=3; ix+=1) {
            		lss.add(ptl.clone().add(ix, 0, 3));
            		lss.add(ptl.clone().add(ix, 0, -3));
            		lss.add(ptl.clone().add(-3, 0, ix));
            		lss.add(ptl.clone().add(3, 0, ix));
            	}
 				w.playSound(ptl, Sound.BLOCK_CHAIN_PLACE, 0.3f, 2);
 				w.playSound(ptl, Sound.BLOCK_CHAIN_PLACE, 0.3f, 0);
 				
        		ArmorStand afs = w.spawn(ptl, ArmorStand.class);
        		afs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
        		afs.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
        		afs.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
        		afs.setInvisible(true);
        		afs.setCollidable(false);
        		afs.getEquipment().setHelmet(new ItemStack(Material.WITHER_SKELETON_SKULL));
        		afs.setInvulnerable(true);
        		afs.getAttribute(Attribute.SCALE).setBaseValue(2.5);
        		afs.setHeadPose(new EulerAngle(Math.PI/60 , 0, 0));
        		afs.setGravity(false);


            	lss.forEach(l -> {
            		ArmorStand as = w.spawn(l, ArmorStand.class);
            		as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
            		as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
            		as.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
            		as.setInvisible(true);
            		as.setCollidable(false);
            		as.getEquipment().setHelmet(new ItemStack(Material.GILDED_BLACKSTONE));
            		as.setInvulnerable(true);
            		as.getAttribute(Attribute.SCALE).setBaseValue(1.5);
            		as.setHeadPose(new EulerAngle(Math.PI/60 , 0, 0));
            		as.setGravity(false);

            		ArmorStand as1 = w.spawn(l.clone().add(0,1,0), ArmorStand.class);
            		as1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
            		as1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
            		as1.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
            		as1.setInvisible(true);
            		as1.setCollidable(false);
            		as1.getEquipment().setHelmet(new ItemStack(Material.CRACKED_POLISHED_BLACKSTONE_BRICKS));
            		as1.setInvulnerable(true);
            		as1.getAttribute(Attribute.SCALE).setBaseValue(1.5);
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
					w.spawnParticle(Particle.PORTAL, ptl, 200, 3,1.5,3); 
					w.spawnParticle(Particle.SCULK_SOUL, ptl, 50, 1.5,1.5,1.5,Material.CRYING_OBSIDIAN.createBlockData()); 
     				w.playSound(ptl, Sound.BLOCK_CHAIN_PLACE, 0.2f, 0);
     				w.playSound(ptl, Sound.ENTITY_WITHER_HURT, 0.2f, 2f);
						for (LivingEntity e : w.getLivingEntities().stream().filter(le -> le.getLocation().distance(ptl)<4.5).toList())
						{
	    						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
	    							LivingEntity le = (LivingEntity)e;
	    							le.damage(6.6,p);
	    							le.teleport(ptl);
	    						}
						}
         			
		            }
                }, 0,10);
            	ordt.put(rn, bolt);

        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	     				w.playSound(ptl, Sound.ENTITY_WITHER_HURT, 1f, 0f);
	                	Bukkit.getScheduler().cancelTask(bolt);
	                	cursable.remove(p.getUniqueId());
	                    line.forEach(l ->  {
    						w.spawnParticle(Particle.DUST_PILLAR, l, 100, 4,0.5,4,0 ,getBd(Material.GILDED_BLACKSTONE));
        					w.spawnParticle(Particle.DUST_PILLAR, l, 100, 4,0.5,4,0 ,getBd(Material.BLACK_GLAZED_TERRACOTTA));
	                    });

	                    for (Entity e : w.getNearbyEntities(ptl, 4.5, 8, 4.5)) {
    						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
    							LivingEntity le = (LivingEntity)e;
    							le.damage(16.6,p);
    						}
						}
	                }
	            }, 80); 
            }
        }, 45); 
    	ordt.put(rn, ta);
	}
	
	private HashMap<UUID, Integer> cursable = new HashMap<UUID, Integer>();
	public void curse(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 9;
		if(d.getEntity().hasMetadata("witherboss") && cursable.containsKey(d.getEntity().getUniqueId())) 
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
	
	


	final private void Ray(LivingEntity te, LivingEntity p) {
		final World snw = p.getWorld();
		final Location lel = te.getEyeLocation().clone();
		final Location snl = p.getLocation().clone();
		final Vector v = lel.clone().toVector().subtract(snl.clone().toVector());
		final double dis = lel.distance(snl);
		
		HashSet<Location> line = new HashSet<>();
		for(int d = 0; d<dis+5; d++) {
			line.add(snl.clone().add(v.clone().normalize().multiply(d)));
		}

		line.forEach(l -> {
			snw.spawnParticle(Particle.END_ROD, l, 6, 1,1,1,0);
			snw.spawnParticle(Particle.BLOCK, l, 30, 2,2,2,0,getBd(Material.BLACK_STAINED_GLASS));
		});
		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
            	snw.playSound(p, Sound.ENTITY_WITHER_BREAK_BLOCK, 0.6f, 1.6f);
        		line.forEach(l -> {
        			snw.spawnParticle(Particle.WITCH, l, 30, 2,2,2,0);
        			snw.spawnParticle(Particle.BLOCK, l, 30, 2,2,2,0,getBd(Material.BLACK_GLAZED_TERRACOTTA));
                    for (Entity e : snw.getNearbyEntities(l, 2, 2, 2)) {
						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
							LivingEntity le = (LivingEntity)e;
							le.damage(6,p);
						}
					}
        		});
            }
        }, 16); 
        ordt.put(gethero(p), task);
	}
	
    private void shot(LivingEntity boss, Location startLoc) {
        World world = startLoc.getWorld();
        String rn = gethero(boss);

		ItemStack mainf = new ItemStack(Material.NETHERITE_PICKAXE);
		ItemMeta mmf = mainf.getItemMeta();
		mmf.setCustomModelData(7008);
		mainf.setItemMeta(mmf);

		sendItemChange(boss, getherotype(rn), mainf);
		
        BukkitTask bt = Bukkit.getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
            int tick = 0;

            @Override
            public void run() {
                Player target = NethercoreRaids.getheroes(boss).stream()
                        .filter(p -> p.isValid() && p.getWorld().equals(world))
                        .max((p1, p2) -> Double.compare(p1.getLocation().distance(startLoc), p2.getLocation().distance(startLoc)))
                        .orElse(null);

                if (target == null || tick >= 6) {
                    this.cancel();
                    return;
                }
                world.playSound(boss, Sound.BLOCK_BEACON_ACTIVATE, 0.8f, 1.6f);
                world.playSound(target, Sound.BLOCK_BEACON_ACTIVATE, 0.8f, 1.6f);
                Ray(target, boss);

                tick = tick++;
            }

            private void cancel() {
                Bukkit.getScheduler().cancelTask(this.hashCode());
            }
        }, 0L, 6L); 

        ordt.put(rn, bt.getTaskId());
    }

	
	
	private HashMap<UUID, Boolean> handable = new HashMap<UUID, Boolean>();
	
	final private void hand(LivingEntity p) {

    	final World w = p.getWorld();
		Location pl = p.getEyeLocation().clone();
		w.playSound(pl, Sound.ITEM_ARMOR_EQUIP_GOLD, 1.0f, 0f);
		w.spawnParticle(Particle.ASH, pl, 150, 2,2,2);
		w.spawnParticle(Particle.REVERSE_PORTAL, pl, 150, 2,2,2);
		

		shot(p,pl);
	   	
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
		if(d.getEntity().hasMetadata("witherboss")) 
		{
			Mob p = (Mob)d.getEntity();
			
			int sec = 9;
	
	
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
	final private void phantom(LivingEntity boss) {

		World w = boss.getWorld();
		
		final Location pl = boss.getEyeLocation().clone();
        String rn = gethero(boss);

		ItemStack mainf = new ItemStack(Material.GLOBE_BANNER_PATTERN);
		ItemMeta mmf = mainf.getItemMeta();
		mmf.setCustomModelData(1008);
		mainf.setItemMeta(mmf);

		sendItemChange(boss, getherotype(rn), mainf);

        final Player target = NethercoreRaids.getheroes(boss).stream()
                .filter(p -> p.isValid() && p.getWorld().equals(w))
                .max((p1, p2) -> Double.compare(p1.getLocation().distance(pl), p2.getLocation().distance(pl)))
                .orElse(null);
        w.playSound(boss.getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, 1.2f, 0.2f);
		boss.getWorld().spawnParticle(Particle.CRIT, pl, 100, 2,2,2);
		boss.getWorld().spawnParticle(Particle.ENCHANTED_HIT, pl, 100, 2,2,2);
        int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {
    	        Vector pv = target.getLocation().clone().toVector().subtract(boss.getLocation().clone().toVector()).normalize();
     			Location tel = boss.getLocation().clone().add(pv.clone().multiply(0.7));
     			if(tel.getBlock().isPassable()) {
        			boss.teleport(tel);
     			}
            	boss.swingMainHand();
            	w.playSound(boss.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.2f, 0f);
                w.playSound(boss.getLocation(), Sound.ENTITY_WITHER_SKELETON_STEP, 0.1f, 1.5f);
 				w.spawnParticle(Particle.SWEEP_ATTACK, pl, 25, 2,2,2);
 				w.spawnParticle(Particle.WITCH, pl, 25, 1.5,1.5,1.5);

            	for(Entity e : boss.getWorld().getNearbyEntities(tel, 2,2,2)) {
            		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=boss) {
            			LivingEntity le = (LivingEntity)e;

						le.damage(2, boss);
						if(tel.getBlock().isPassable()) {
							le.teleport(tel);
						}
						
            		}
            	}
            	
            }
	   	}, 25,6);
        ordt.put(gethero(boss), task);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			Bukkit.getScheduler().cancelTask(task);
     			boss.teleport(target.getLocation().clone().add(0, 5, 0));
    	        Vector pv = new Vector(0,-0.5,0);

                int task2 = new BukkitRunnable() {
                	int tick = 0;
                    @Override
                    public void run() {
                    	if(tick++>8) {
                    		this.cancel();
                    	}
             			Location tel = boss.getLocation().clone().add(pv);
             			if(tel.getBlock().isPassable()) {
                			boss.teleport(tel);
             			}
                    	boss.swingMainHand();
                    	w.playSound(boss.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.3f, 0.6f);
                        w.playSound(boss.getLocation(), Sound.ENTITY_WITHER_SKELETON_STEP, 0.1f, 1.5f);
         				w.spawnParticle(Particle.SWEEP_ATTACK, pl, 25, 1,1,1);
         				w.spawnParticle(Particle.WITCH, pl, 25, 1.5,1.5,1.5);
                    	for(Entity e : boss.getWorld().getNearbyEntities(boss.getLocation(), 1,1,1)) {
                    		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=boss) {
                    			LivingEntity le = (LivingEntity)e;

        						le.damage(6, boss);
        						
                    		}
                    	}
                    }
                }.runTaskTimer(RMain.getInstance(), 20,5).getTaskId();
                ordt.put(gethero(boss), task2);
     			phantomable.remove(boss.getUniqueId());
            }
	   	}, 63);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			cursable.putIfAbsent(boss.getUniqueId(), 1);
            }
	   	}, 100);
	}
	
	public void phantom(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("witherboss")) 
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
		if(d.getEntity().hasMetadata("witherboss")) 
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
	
	final private void waveStart(LivingEntity boss, Location tl) {
		
        String rn = gethero(boss);

		ItemStack mainf = new ItemStack(Material.BOW);
		ItemMeta mmf = mainf.getItemMeta();
		mmf.setCustomModelData(2008);
		mainf.setItemMeta(mmf);

		sendItemChange(boss, getherotype(rn), mainf);
		
		World w = boss.getWorld();
		
		w.playSound(boss.getLocation(), Sound.ITEM_CROSSBOW_LOADING_START, 1.0f, 0.1f);
		w.playSound(boss.getLocation(), Sound.ITEM_ARMOR_EQUIP_IRON, 1.0f, 0.1f);
		w.spawnParticle(Particle.CLOUD, boss.getLocation(), 200, 1,1,1);

        final Location fl = boss.getLocation().clone();
        AtomicInteger j = new AtomicInteger(0);
        Holding.holding(null, boss, 25l);
        
    	j.set(Bukkit.getServer().getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
    		
	    	int tick = 0;
			@Override
        	public void run() 
            {	
				if(tick++>=12) {
					Bukkit.getScheduler().cancelTask(j.get());
				}
				for(int i = 0; i<6; i++) {
					Arrow arrow = w.spawnArrow(boss.getEyeLocation(), boss.getEyeLocation().getDirection(), 1.6f, 6f);
					arrow.setColor(Color.BLACK);
					arrow.setBasePotionType(PotionType.WEAKNESS);
					
				}
            }
    	}, 25, 6).getTaskId());
    	ordt.put(gethero(boss), j.get());
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			waveable.remove(boss.getUniqueId());
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
		if((d.getEntity() instanceof Mob) && d.getEntity().hasMetadata("witherboss")) 
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

	public boolean checkAndApplyCharge(LivingEntity p, EntityDamageByEntityEvent d) {
		if(!p.hasMetadata("witherboss")) {
			return true;
		}
	    if (!p.hasMetadata("ruined")
	            && p.hasMetadata("ejected")) {
		    return false;
	    }
	    return true;
	}

	
}