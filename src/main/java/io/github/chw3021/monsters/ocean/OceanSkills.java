package io.github.chw3021.monsters.ocean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Breeze;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Shulker;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.entity.Trident;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.OverworldRaids;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.chat.ComponentBuilder;



public class OceanSkills extends Summoned{

	/**
	 * 
	 */
	private transient static final long serialVersionUID = -8250326581644953664L;
	Holding hold = Holding.getInstance();
	private HashMap<UUID, Long> rb1cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb5cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb2cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb4cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Boolean> ordealable = new HashMap<UUID, Boolean>();
	
	private Multimap<String, Integer> ript = ArrayListMultimap.create();
	

	
	private static final OceanSkills instance = new OceanSkills ();
	public static OceanSkills getInstance()
	{
		return instance;
	}


	public void Mimic(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity() instanceof Breeze) {
			Breeze p = (Breeze) d.getEntity();
			if(p.hasMetadata("mimic") && !p.hasAI() && p.isInvisible()) {
				p.removeMetadata("fake", RMain.getInstance());
				d.setCancelled(false);
				p.setAI(true);
				p.setInvisible(false);
				ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
				LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
				chm.setColor(Color.ORANGE);
				chest.setItemMeta(chm);
				ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
				LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
				lem.setColor(Color.ORANGE);
				leg.setItemMeta(lem);
				ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
				bom.setColor(Color.ORANGE);
				boots.setItemMeta(bom);
				boots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 3);
				ItemStack main = new ItemStack(Material.PRISMARINE_SHARD);
				main.addUnsafeEnchantment(Enchantment.SHARPNESS, 3);
				
				EntityEquipment ee = p.getEquipment();
				ee.setChestplate(chest);
				ee.setLeggings(leg);
				ee.setBoots(boots);
				ee.setItemInMainHand(main);
				ee.setItemInOffHand(main);
				p.removeMetadata("mimic", RMain.getInstance());
			}
		}
	}
	    
		
	

	public void shulker(ProjectileLaunchEvent d) 
	{
	    
		if(d.getEntity() instanceof ShulkerBullet) 
		{
			ShulkerBullet po = (ShulkerBullet)d.getEntity();
			if(po.getShooter() instanceof Shulker) {
				Shulker p = (Shulker) po.getShooter();
				if(p.hasMetadata("ocean")) {

					po.setTarget(null);
					po.setVelocity(p.getEyeLocation().getDirection().clone().normalize().multiply(1.5));
				}
			}
		}					
	}
	
	final private void Shoot(LivingEntity p, Player tl) {
		if(rb1cooldown.containsKey(p.getUniqueId()))
        {
            long timer = (rb1cooldown.get(p.getUniqueId())/1000 + 10) - System.currentTimeMillis()/1000; 
            if(!(timer < 0))
            {
            }
            else 
            {
            	rb1cooldown.remove(p.getUniqueId()); 
				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SHULKER_SHOOT, 1.0f, 0.6f);
				p.getWorld().spawnParticle(Particle.BUBBLE_POP, p.getLocation(), 100, 1,1,1);
				p.getWorld().spawnParticle(Particle.SQUID_INK, p.getLocation(), 100, 1,1,1);
				
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
	                {	
        				ShulkerBullet fr = p.launchProjectile( ShulkerBullet.class);
        				fr.setShooter(p);
        				fr.setTarget(tl);
		            }
        	   	}, 10);
                rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
            }
        }
        else 
        {
			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SHULKER_SHOOT, 1.0f, 0.6f);
			p.getWorld().spawnParticle(Particle.BUBBLE_POP, p.getLocation(), 100, 1,1,1);
			p.getWorld().spawnParticle(Particle.SQUID_INK, p.getLocation(), 100, 1,1,1);
			
			
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
            	public void run() 
                {	
    				ShulkerBullet fr = p.launchProjectile( ShulkerBullet.class);
    				fr.setShooter(p);
    				fr.setTarget(tl);
	            }
    	   	}, 10);
            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
        }
		
	}

	@SuppressWarnings("unchecked")
	public void Shoot(EntityDamageByEntityEvent d) 
	{
	   
		if(d.getEntity() instanceof ElderGuardian && d.getEntity().hasMetadata("oceanboss") ) 
		{
			final ElderGuardian p = (ElderGuardian)d.getEntity();
			if(p.hasMetadata("raid")) {
				if(!OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")) {
					return;
				}

				final Player tl = OverworldRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get();
				Shoot(p,tl);
			}
			else if(p.hasMetadata("summoned")) {
				final Object hero = getherotype(gethero(p));
				if(hero instanceof Player) {
					final Player hp = (Player)hero;
					Shoot(p,hp);
				}
				else if(hero instanceof HashSet) {
					HashSet<Player> par = (HashSet<Player>) hero;
					Shoot(p,par.stream().findAny().get());
				}

			}
		}					
	}


	final private void Curse(LivingEntity p, Location tl) {
		if(rb5cooldown.containsKey(p.getUniqueId()))
        {
            long timer = (rb5cooldown.get(p.getUniqueId())/1000 + 7) - System.currentTimeMillis()/1000; 
            if(!(timer < 0))
            {
            }
            else 
            {
            	rb5cooldown.remove(p.getUniqueId()); 

    			p.getWorld().spawnParticle(Particle.BUBBLE_COLUMN_UP, tl, 200, 1,1,1);
    			p.getWorld().spawnParticle(Particle.NAUTILUS, tl, 200, 1,1,1);
    			p.getWorld().playSound(tl, Sound.BLOCK_CONDUIT_ACTIVATE, 0.6f, 0.3f);
    			p.getWorld().playSound(tl, Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 0.2f, 2f);
    			Holding.holding(null, p, 40l);
    			Holding.invur(p, 5l);
    			
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                    @Override
                    public void run() {
            			p.getWorld().spawnParticle(Particle.SQUID_INK, tl, 200, 1,1,1);
            			p.getWorld().spawnParticle(Particle.ELDER_GUARDIAN, tl,1);
        				p.getWorld().playSound(tl, Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.4f, 1f);
        				p.getWorld().playSound(tl, Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 0.85f, 2f);
    					for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),2, 2, 2)) {
    						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
    							LivingEntity le = (LivingEntity)e;
    							le.damage(8);
    		                    Holding.holding(null, le, 40l);
    						}
    					}
                    }
                }, 40);
				rb5cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
            }
        }
        else 
        {

			p.getWorld().spawnParticle(Particle.BUBBLE_COLUMN_UP, tl, 200, 1,1,1);
			p.getWorld().spawnParticle(Particle.NAUTILUS, tl, 200, 1,1,1);
			p.getWorld().playSound(tl, Sound.BLOCK_CONDUIT_ACTIVATE, 0.6f, 2f);
			p.getWorld().playSound(tl, Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 0.2f, 2f);
			Holding.holding(null, p, 40l);
			Holding.invur(p, 5l);
			
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
        			p.getWorld().spawnParticle(Particle.SQUID_INK, tl, 200, 1,1,1);
    				p.getWorld().playSound(tl, Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.4f, 1f);
    				p.getWorld().playSound(tl, Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 0.85f, 2f);
					for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),2, 2, 2)) {
						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
							LivingEntity le = (LivingEntity)e;
							le.damage(8);
		                    Holding.holding(null, le, 40l);
						}
					}
                }
            }, 40);
			rb5cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
        }
		
	}

	@SuppressWarnings("unchecked")
	public void Curse(EntityDamageByEntityEvent d) 
	{
	   
		if(d.getEntity() instanceof ElderGuardian && d.getEntity().hasMetadata("oceanboss") ) 
		{
			final ElderGuardian p = (ElderGuardian)d.getEntity();
			if(p.hasMetadata("raid")) {
				if(!OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")) {
					return;
				}

				final Location tl = OverworldRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get().getLocation().clone().add(0,0.2,0);
				Curse(p,tl);
			}
			else if(p.hasMetadata("summoned")) {
				final Object hero = getherotype(gethero(p));
				if(hero instanceof Player) {
					final Player hp = (Player)hero;
					Curse(p,hp.getLocation());
				}
				else if(hero instanceof HashSet) {
					HashSet<Player> par = (HashSet<Player>) hero;
					Curse(p,par.stream().findAny().get().getLocation());
				}

			}
		}					
	}


	final private HashSet<Vector> Tridents(final Location fl, ElderGuardian p) {
		HashSet<Vector> vs = new HashSet<>();
		
		final Vector side = fl.clone().getDirection().clone().normalize();
		final Vector somer = side.clone().rotateAroundY(Math.PI/2).clone().normalize();
		Table<Double, Double, Double> angles = HashBasedTable.create();
		
		for(double an1 = -Math.PI; an1 < Math.PI; an1 += Math.PI/4) {
			for(double an2 = -Math.PI; an2 < Math.PI; an2 += Math.PI/4) {
				for(double an3 = -Math.PI; an3 < Math.PI; an3 += Math.PI/4) {
					if(angles.containsRow(an1) && angles.containsColumn(an2) && angles.containsValue(an3)) {
						continue;
					}
					angles.put(an1, an2, an3);
					vs.add(fl.clone().getDirection().clone().rotateAroundY(an3).rotateAroundAxis(somer, an2).rotateAroundAxis(side, an1).normalize().multiply(1.8));
				}
			}
		}
		return vs;
	}

	
	public void Tridents(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 10;
		if(d.getEntity() instanceof ElderGuardian&& !d.isCancelled() && d.getEntity().hasMetadata("oceanboss")) 
		{
			ElderGuardian p = (ElderGuardian)d.getEntity();
			final Location tl = p.getLocation().clone();
			if(p.hasMetadata("failed")) {
				return;
			}
				if(rb4cooldown.containsKey(p.getUniqueId()))
		        {
		            long timer = (rb4cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		            if(!(timer < 0))
		            {
		            }
		            else 
		            {
		            	rb4cooldown.remove(p.getUniqueId()); // removing player from HashMap

		    			p.getWorld().spawnParticle(Particle.NAUTILUS, tl, 200, 1,1,1);
		    			p.getWorld().spawnParticle(Particle.FLASH, tl, 10, 1,1,1);
		    			p.getWorld().playSound(tl, Sound.BLOCK_CONDUIT_ACTIVATE, 0.6f, 2f);
    	    			p.getWorld().playSound(tl, Sound.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 1f, 0.1f);
		    			p.getWorld().playSound(tl, Sound.ITEM_CROSSBOW_LOADING_END, 0.9f, 0.5f);
		    			p.getWorld().playSound(tl, Sound.ITEM_TRIDENT_THUNDER, 0.2f, 2f);
		    			Holding.holding(null, p, 30l);
		    			Holding.invur(p, 30l);
		    			String rn = gethero(p);

                    	Tridents(tl.clone(),p).forEach(v ->{
                    		for(int i = 0; i<25; i++) {
        		    			p.getWorld().spawnParticle(Particle.GLOW_SQUID_INK, tl.clone().add(v.clone().normalize().multiply(i)), 10, 0.3,0.3,0.3,0);
                    		}
    		                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    		                    @Override
    		                    public void run() {
    	                    		Trident t = p.launchProjectile(Trident.class, v);
    	                    		t.setDamage(5);
    	                    		t.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(),true));
    	    		                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	    		                    @Override
    	    		                    public void run() {
    	    		                    	t.remove();
    	    		                    }
    	    		                }, 60);
    		    	    			p.getWorld().playSound(tl, Sound.ENTITY_DROWNED_SHOOT, 0.1f, 0.1f);
    		                    }
    		                }, 30);
                    	});
		                rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else 
		        {


	    			p.getWorld().spawnParticle(Particle.NAUTILUS, tl, 200, 1,1,1);
	    			p.getWorld().spawnParticle(Particle.FLASH, tl, 10, 1,1,1);
	    			p.getWorld().playSound(tl, Sound.BLOCK_CONDUIT_ACTIVATE, 0.6f, 2f);
	    			p.getWorld().playSound(tl, Sound.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 1f, 0.1f);
	    			p.getWorld().playSound(tl, Sound.ITEM_CROSSBOW_LOADING_END, 0.9f, 0.5f);
	    			p.getWorld().playSound(tl, Sound.ITEM_TRIDENT_THUNDER, 0.2f, 2f);
	    			Holding.holding(null, p, 30l);
	    			Holding.invur(p, 30l);
	    			String rn = gethero(p);

                	Tridents(tl.clone(),p).forEach(v ->{
                		for(int i = 0; i<25; i++) {
    		    			p.getWorld().spawnParticle(Particle.GLOW_SQUID_INK, tl.clone().add(v.clone().normalize().multiply(i)), 10, 0.3,0.3,0.3,0);
                		}
		                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                    @Override
		                    public void run() {
	                    		Trident t = p.launchProjectile(Trident.class, v);
	                    		t.setDamage(5);
	                    		t.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(),true));
	    		                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    		                    @Override
	    		                    public void run() {
	    		                    	t.remove();
	    		                    }
	    		                }, 60);
		    	    			p.getWorld().playSound(tl, Sound.ENTITY_DROWNED_SHOOT, 0.1f, 0.1f);
		                    }
		                }, 30);
                	});
	                rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
	}
	
	
	final private ArrayList<Location> Spike(final Location fl, Integer a, ElderGuardian p) {
		ArrayList<Location> ls = new ArrayList<>();
		String rn = p.getMetadata("raid").get(0).asString();
		for(int i = 0; i<35; i++) {
			if(a%4 == 0 || a%4==3) {
				ls.add(fl.clone().add(0, i, 0));
				ls.add(fl.clone().add(i, 0, 0));
				ls.add(fl.clone().add(0, 0, i));
				ls.add(fl.clone().add(0, -i, 0));
				ls.add(fl.clone().add(-i, 0, 0));
				ls.add(fl.clone().add(0, 0, -i));
			}
			if(a%4 == 1 || a%4==3) {
				ls.add(fl.clone().add(i, i, i));
				ls.add(fl.clone().add(-i, i, i));
				ls.add(fl.clone().add(i, -i, i));
				ls.add(fl.clone().add(i, i, -i));
				ls.add(fl.clone().add(-i, -i, -i));
				ls.add(fl.clone().add(i, -i, -i));
				ls.add(fl.clone().add(-i, i, -i));
				ls.add(fl.clone().add(-i, -i, i));
			}
			if(a%4 == 2 || a%4==3) {
				ls.add(fl.clone().add(i, i, 0));
				ls.add(fl.clone().add(0, i, i));
				ls.add(fl.clone().add(i, 0, i));
				ls.add(fl.clone().add(-i, -i, 0));
				ls.add(fl.clone().add(0, -i, -i));
				ls.add(fl.clone().add(-i, 0, -i));
				ls.add(fl.clone().add(-i, i, 0));
				ls.add(fl.clone().add(0, -i, i));
				ls.add(fl.clone().add(-i, 0, i));
				ls.add(fl.clone().add(i, -i, 0));
				ls.add(fl.clone().add(0, i, -i));
				ls.add(fl.clone().add(i, 0, -i));
			}
			ls.add(fl.clone().add(i, i, i));
		}
		
		ls.forEach(l ->{
			l.getWorld().spawnParticle(Particle.BUBBLE, l, 15,0.5,0.5,0.5,0.1);
			l.getWorld().playSound(l, Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 0.05f, 2f);
	        int i1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() {
	    			l.getWorld().spawnParticle(Particle.NAUTILUS, l, 15,0.5,0.5,0.5,0.1);
	    			l.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, l, 15,0.5,0.5,0.5,0.1);
	    			l.getWorld().playSound(l, Sound.ITEM_TRIDENT_THROW, 0.05f, 0f);
	    			l.getWorld().getNearbyEntities(l, 1, 1, 1).forEach(e -> {
						if(p!=e && e instanceof Player) {
							LivingEntity le = (LivingEntity)e;
							le.damage(10,p);	
						}
	    			});
	            }
	        }, 10);
			ordt.put(rn, i1);  
		});
		
		return ls;
	}


	@SuppressWarnings("deprecation")
	public void Spikes(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 25;
		if(d.getEntity() instanceof ElderGuardian&& !d.isCancelled() && d.getEntity().hasMetadata("oceanboss") && d.getEntity().hasMetadata("ruined")) 
		{
			ElderGuardian p = (ElderGuardian)d.getEntity();
			if(p.hasMetadata("failed")) {
				return;
			}
			if((p.getHealth() - d.getDamage() <= p.getMaxHealth()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getMaxHealth()*0.2);
                ordealable.put(p.getUniqueId(), true);
                d.setCancelled(true);
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
		        		String rn = p.getMetadata("raid").get(0).asString();
		                d.setCancelled(true);
		                Holding.invur(p, 20l);
		                Holding.untouchable(p, 20l);
		                for(Entity e : OverworldRaids.getheroes(p)) {
		                	if(e instanceof Player) {
		                		Player pe = (Player) e;
		    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
			                		pe.sendMessage(ChatColor.BOLD+"엘더가디언: 허영은 파멸을 부를 것이다.");
		    					}
		    					else {
			                		pe.sendMessage(ChatColor.BOLD+"ElderGuardian: The pomps and vanities will bring the destruction.");
		    					}
		                	}
		                }
		    			p.getWorld().spawnParticle(Particle.NAUTILUS, p.getLocation().clone(), 200,2,2,2,0.1);
		    			p.getWorld().spawnParticle(Particle.DOLPHIN, p.getLocation().clone(), 200,2,2,2,0.1);
		                AtomicInteger j = new AtomicInteger();
		                for(int i = 0; i<10; i++) {
		                    int i1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	Spike(p.getLocation().clone(), j.getAndIncrement(), p);
				                }
				            }, i*15+20);
							ordt.put(rn, i1);  
		                }
	                    rb2cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else 
		        {
	        		String rn = p.getMetadata("raid").get(0).asString();
	                d.setCancelled(true);
	                Holding.invur(p, 20l);
	                Holding.untouchable(p, 20l);
	                for(Entity e : OverworldRaids.getheroes(p)) {
	                	if(e instanceof Player) {
	                		Player pe = (Player) e;
	    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
		                		pe.sendMessage(ChatColor.BOLD+"엘더가디언: 허영은 파멸을 부를 것이다.");
	    					}
	    					else {
		                		pe.sendMessage(ChatColor.BOLD+"ElderGuardian: The pomps and vanities will bring the destruction.");
	    					}
	                	}
	                }
	    			p.getWorld().spawnParticle(Particle.NAUTILUS, p.getLocation().clone(), 200,2,2,2,0.1);
	    			p.getWorld().spawnParticle(Particle.DOLPHIN, p.getLocation().clone(), 200,2,2,2,0.1);
	                AtomicInteger j = new AtomicInteger();
	                for(int i = 0; i<10; i++) {
	                    int i1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	Spike(p.getLocation().clone(), j.getAndIncrement(), p);
			                }
			            }, i*15+20);
						ordt.put(rn, i1);  
	                }
		            rb2cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
	}

	public static HashMap<UUID, UUID> ordeal = new HashMap<UUID, UUID>();//가디언 uuid 저장

	public static HashMap<UUID, Integer> count = new HashMap<UUID, Integer>();//가디언 uuid 저장
	public HashMap<UUID, Integer> guardiant = new HashMap<UUID, Integer>();//가디언 텔포 태스크 저장


	final private List<Location> markCreate(ElderGuardian p) {
		List<Location> ls = new ArrayList<>();
	    List<UUID> summonedGuardians = new ArrayList<>();
		String rn = p.getMetadata("raid").get(0).asString();

		count.put(p.getUniqueId(), 6);
		
		Location pfl = p.getLocation();
		
		ls.add(pfl.clone().add(0, 1.5, 0));
		ls.add(pfl.clone().add(1.5, 0, 0));
		ls.add(pfl.clone().add(0, 0, 1.5));
		ls.add(pfl.clone().add(0, -1.5, 0));
		ls.add(pfl.clone().add(-1.5, 0, 0));
		ls.add(pfl.clone().add(0, 0, -1.5));
		
		ls.forEach(l ->{
			l.getWorld().spawnParticle(Particle.BUBBLE, l, 15,0.5,0.5,0.5,0.1);
			l.getWorld().playSound(l, Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 0.05f, 2f);

    		l.getWorld().spawn(l, Guardian.class, newmob ->{
        		newmob.setCustomNameVisible(false);
        		newmob.setGlowing(true);
        		newmob.setInvulnerable(true);
        		newmob.setRemoveWhenFarAway(false);
        		newmob.setGravity(false);
        		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), rn));
        		newmob.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
        		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
        		newmob.setMetadata("mark", new FixedMetadataValue(RMain.getInstance(), rn));
        		newmob.setMetadata("mark"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
        		newmob.setAI(false);
                summonedGuardians.add(newmob.getUniqueId());
            	ordeal.put(newmob.getUniqueId(), p.getUniqueId());
    		});
    		
		});

    	Location el = p.getLocation();
    	
    	AtomicInteger j = new AtomicInteger();
    	for (int i = 0; i < summonedGuardians.size(); i++) {
            Entity guardian = Bukkit.getEntity(summonedGuardians.get(j.get()));
            int i1 =Bukkit.getServer().getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if(guardian == null) {
                    	return;
                    }
            		Location pfl = p.getLocation().clone();
                    Location offset = ls.get(j.getAndIncrement()).clone().subtract(pfl);
                    Location newLoc = el.clone().add(offset);  
                    guardian.teleport(newLoc);  
                }
            }, 0,1).getTaskId();
    		ordt.put(rn, i1); 
    		guardiant.put(guardian.getUniqueId(), i1); 
        }
    	

		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
				if(ript.containsKey(rn)) {
					ript.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
				}
				Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("mark"+rn)).forEach(e -> {
					ordeal.remove(e.getUniqueId());
		    		Bukkit.getScheduler().cancelTask(guardiant.get(e.getUniqueId()));
					e.remove();
				});

        		if(ordt.containsKey(rn)) {
        			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        		}
                for(Entity e1 : OverworldRaids.getheroes(p)) {
                	if(e1 instanceof Player) {
                		Player pe = (Player) e1;
                		pe.setHealth(0);
    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
	                		pe.sendMessage(ChatColor.BOLD+"엘더가디언: 모든것은 그분 뜻대로...");
    					}
    					else {
	                		pe.sendMessage(ChatColor.BOLD+"ElderGuardian: Everything is up to him..");
    					}
                	}
                }
                return;
            }
        }, 300);
		ordt.put(rn, task);
    	
		return ls;
	}
	
	public void markHit(EntityDamageByEntityEvent d) {

		if(!ordeal.containsKey(d.getEntity().getUniqueId())) {
			return;
		}
		if(d.getDamager() instanceof ElderGuardian) {
			return;
		}
		if(d.getDamager() instanceof Player) {
			Player dp = (Player) d.getDamager();
			if(dp.hasCooldown(Material.YELLOW_TERRACOTTA)) {
				return;
			}
		}
		Guardian g = (Guardian) d.getEntity();
		ElderGuardian p = (ElderGuardian) Bukkit.getEntity(ordeal.get(g.getUniqueId()));
		String rn = gethero(p);

		if(g.hasMetadata("mark") && g.isValid()) {
    		ordeal.remove(ordeal.get(g.getUniqueId()));
    		Bukkit.getScheduler().cancelTask(guardiant.get(g.getUniqueId()));
    		guardiant.remove(g.getUniqueId());
    		g.remove();
			p.getWorld().playSound(g.getLocation(), Sound.ENTITY_GUARDIAN_HURT, 1, 0);
			p.getWorld().spawnParticle(Particle.BUBBLE, g.getLocation(), 60,1,1,1);
			count.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);

			if(count.getOrDefault(p.getUniqueId(), 0)>1) {
                for(Player pe : OverworldRaids.getheroes(p)) {
            		pe.sendMessage(ChatColor.BLUE+ "["+(6-count.getOrDefault(p.getUniqueId(), 0))+ "/6]");
                }
			}
			else {
    			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1, 0);
    			p.getWorld().spawnParticle(Particle.BUBBLE, p.getLocation(), 400,1,1,1);
    			p.getWorld().spawnParticle(Particle.DRAGON_BREATH, p.getLocation(), 400,1,1,1);

            	p.playHurtAnimation(0);
				Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("mark"+rn)).forEach(e -> e.remove());
        		ordeal.remove(p.getUniqueId());

        		if(ordt.containsKey(rn)) {
        			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        		}

				if(ript.containsKey(rn)) {
					ript.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
				}
				p.playHurtAnimation(0);
            	Holding.ale(p).setMetadata("failed", new FixedMetadataValue(RMain.getInstance(),true));
        		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
            	Holding.untouchable.remove(p.getUniqueId());
            	Holding.holding(null, Holding.ale(p), 300l);
                for(Entity e1 : OverworldRaids.getheroes(p)) {
                	if(e1 instanceof Player) {
                		Player pe = (Player) e1;
	                	Holding.superholding(pe, Holding.ale(p), 300l);
    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
	                		pe.sendMessage(ChatColor.BOLD+"엘더가디언: 이런 건방진!..");
    					}
    					else {
	                		pe.sendMessage(ChatColor.BOLD+"ElderGuardian: How dare are You!...");
    					}
                	}
                }
                rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	            int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	                	p.removeMetadata("failed", RMain.getInstance());
                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
	                }
	            }, 300);
				ordt.put(rn, t3);
				return;
			}
		}
	}

	
	final private void Riptide(ElderGuardian p, Player pe) {
		String rn = p.getMetadata("raid").get(0).asString();
		if(p.isDead() || pe.isDead() || p.getWorld() != pe.getWorld()) {
			if(ript.containsKey(rn)) {
				ript.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
			}
			return;
		}
		final Location eldl = p.getLocation().clone();
		final Location pl = pe.getLocation().clone();
		Vector v = pl.toVector().subtract(eldl.toVector()).normalize();
		ArrayList<Location> ls = new ArrayList<>();
		for(int i = 1; i<eldl.distance(pl)+3;i++ ) {
			ls.add(eldl.clone().add(v.clone().multiply(i)));
		}

        AtomicInteger j = new AtomicInteger();
		ls.forEach(l -> {
			l.getWorld().spawnParticle(Particle.BUBBLE, l, 15,0.5,0.5,0.5,0.1);
			l.getWorld().playSound(l, Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, 0.2f, 2);
            int i1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	p.teleport(l);
	    			l.getWorld().getNearbyEntities(l, 1, 1, 1).forEach(e -> {
		    			l.getWorld().playSound(l, Sound.ITEM_TRIDENT_RIPTIDE_1, 0.1f, 0f);
						if(p!=e && e == pe) {
							LivingEntity le = (LivingEntity)e;
							le.damage(3,p);
						}
	    			});
                }
            }, j.incrementAndGet()/15+15);
            ript.put(rn, i1); 
		});
	}


	@SuppressWarnings("deprecation")
	public void Riptide(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 100;
		if(d.getEntity() instanceof ElderGuardian&& !d.isCancelled() && d.getEntity().hasMetadata("oceanboss") && d.getEntity().hasMetadata("ruined")&& !d.getEntity().hasMetadata("failed")) 
		{
			ElderGuardian p = (ElderGuardian)d.getEntity();
			if(p.hasMetadata("failed")) {
				return;
			}
			if(!(p.getHealth() - d.getDamage() <= p.getMaxHealth()*0.2)|| !ordealable.containsKey(p.getUniqueId())) {
				return;
			}
				if(rb3cooldown.containsKey(p.getUniqueId()))
		        {
		            long timer = (rb3cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		            if(!(timer < 0))
		            {
		            	p.spigot().sendMessage(new ComponentBuilder("You have to wait for " + timer + " seconds to use Riptide").create());
		            }
		            else 
		            {
		            	rb3cooldown.remove(p.getUniqueId()); // removing player from HashMap
		        		String rn = p.getMetadata("raid").get(0).asString();
		        		final Player tar = OverworldRaids.getheroes(p).stream().findAny().get();
		                d.setCancelled(true);
		                Holding.invur(p, 40l);
		                Holding.holding(null, p, 40l);
		                Holding.untouchable(p, 40l);
		                for(Entity e : OverworldRaids.getheroes(p)) {
		                	Holding.invur(p, 20l);
		                	if(e instanceof Player) {
		                		Player pe = (Player) e;
		    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
			                		pe.sendMessage(ChatColor.BOLD+"엘더가디언: "+tar.getName()+"! 네놈부터 처리해주겠다!");
		    					}
		    					else {
			                		pe.sendMessage(ChatColor.BOLD+"ElderGuardian: "+tar.getName()+"! I'll kill you first!");
		    					}
		                	}
		                }
		                markCreate(p);
		    			p.getWorld().spawnParticle(Particle.NAUTILUS, p.getLocation().clone(), 200,2,2,2,0.1);
		    			p.getWorld().spawnParticle(Particle.DOLPHIN, p.getLocation().clone(), 200,2,2,2,0.1);
	                    int i1 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
				                Holding.invur(p, 40l);
				                Holding.untouchable(p, 40l);
			                	Riptide(p, tar);
			                }
			            }, 40, 25);
						ordt.put(rn, i1);  
						ript.put(rn, i1);  
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
	                	Holding.invur(p, 20l);
	                	if(e instanceof Player) {
	                		Player pe = (Player) e;
	    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
		                		pe.sendMessage(ChatColor.BOLD+"엘더가디언: "+tar.getName()+"! 네놈부터 처리해주겠다!");
	    					}
	    					else {
		                		pe.sendMessage(ChatColor.BOLD+"ElderGuardian: "+tar.getName()+"! I'll kill you first!");
	    					}
	                	}
	                }
	                markCreate(p);
	    			p.getWorld().spawnParticle(Particle.NAUTILUS, p.getLocation().clone(), 200,2,2,2,0.1);
	    			p.getWorld().spawnParticle(Particle.DOLPHIN, p.getLocation().clone(), 200,2,2,2,0.1);
                    int i1 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
			                Holding.invur(p, 40l);
			                Holding.untouchable(p, 40l);
		                	Riptide(p, tar);
		                }
		            }, 40, 25);
					ordt.put(rn, i1);  
					ript.put(rn, i1);  
		        }
			}
	}

	
}