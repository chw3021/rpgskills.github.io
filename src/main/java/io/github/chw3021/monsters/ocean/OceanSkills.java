package io.github.chw3021.monsters.ocean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Shulker;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.entity.Stray;
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
		if(d.getEntity() instanceof Stray) {
			Stray p = (Stray) d.getEntity();
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
				main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
				
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
            			p.getWorld().spawnParticle(Particle.MOB_APPEARANCE, tl,1);
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

                    	Tridents(tl.clone(),p).forEach(v ->{
                    		for(int i = 0; i<25; i++) {
        		    			p.getWorld().spawnParticle(Particle.GLOW_SQUID_INK, tl.clone().add(v.clone().normalize().multiply(i)), 10, 0.3,0.3,0.3,0);
                    		}
    		                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    		                    @Override
    		                    public void run() {
    	                    		Trident t = p.launchProjectile(Trident.class, v);
    	                    		t.setDamage(5);
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

	    			p.getWorld().spawnParticle(Particle.BUBBLE_COLUMN_UP, tl, 200, 1,1,1);
	    			p.getWorld().spawnParticle(Particle.NAUTILUS, tl, 200, 1,1,1);
	    			p.getWorld().spawnParticle(Particle.GLOW_SQUID_INK, tl, 200, 1,1,1);
	    			p.getWorld().spawnParticle(Particle.FLASH, tl, 10, 1,1,1);
	    			p.getWorld().playSound(tl, Sound.BLOCK_CONDUIT_ACTIVATE, 0.6f, 2f);
	    			p.getWorld().playSound(tl, Sound.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 1f, 0.1f);
	    			p.getWorld().playSound(tl, Sound.ITEM_CROSSBOW_LOADING_END, 0.9f, 0.5f);
	    			p.getWorld().playSound(tl, Sound.ITEM_TRIDENT_THUNDER, 0.2f, 2f);
	    			Holding.holding(null, p, 30l);
	    			Holding.invur(p, 30l);

                	Tridents(tl.clone(),p).forEach(v ->{
                		for(int i = 0; i<25; i++) {
    		    			p.getWorld().spawnParticle(Particle.GLOW_SQUID_INK, tl.clone().add(v.clone().normalize().multiply(i)), 10, 0.3,0.3,0.3,0);
                		}
		                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                    @Override
		                    public void run() {
	                    		Trident t = p.launchProjectile(Trident.class, v);
	                    		t.setDamage(5);
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
			l.getWorld().spawnParticle(Particle.WATER_BUBBLE, l, 15,0.5,0.5,0.5,0.1);
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
		    			p.getWorld().spawnParticle(Particle.WATER_WAKE, p.getLocation().clone(), 200,2,2,2,0.1);
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
	    			p.getWorld().spawnParticle(Particle.WATER_WAKE, p.getLocation().clone(), 200,2,2,2,0.1);
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
			l.getWorld().spawnParticle(Particle.WATER_BUBBLE, l, 15,0.5,0.5,0.5,0.1);
			l.getWorld().playSound(l, Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, 0.2f, 2);
            int i1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	p.teleport(l);
	    			l.getWorld().getNearbyEntities(l, 1, 1, 1).forEach(e -> {
		    			l.getWorld().playSound(l, Sound.ITEM_TRIDENT_RIPTIDE_1, 0.1f, 0f);
						if(p!=e && e != pe && e instanceof LivingEntity){
							if(ript.containsKey(rn)) {
								ript.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
							}
		                	p.playEffect(EntityEffect.HURT);
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
						if(p!=e && e == pe) {
							LivingEntity le = (LivingEntity)e;
							le.setHealth(0);
							if(ript.containsKey(rn)) {
								ript.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
							}
			                for(Entity e1 : OverworldRaids.getheroes(p)) {
			                	if(e1 instanceof Player) {
			                		Player pe = (Player) e1;
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
	    			});
                }
            }, j.incrementAndGet()/20+12);
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
		    			p.getWorld().spawnParticle(Particle.NAUTILUS, p.getLocation().clone(), 200,2,2,2,0.1);
		    			p.getWorld().spawnParticle(Particle.WATER_WAKE, p.getLocation().clone(), 200,2,2,2,0.1);
	                    int i1 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
				                Holding.invur(p, 40l);
				                Holding.untouchable(p, 40l);
			                	Riptide(p, tar);
			                }
			            }, 40, 20);
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
	    			p.getWorld().spawnParticle(Particle.NAUTILUS, p.getLocation().clone(), 200,2,2,2,0.1);
	    			p.getWorld().spawnParticle(Particle.WATER_WAKE, p.getLocation().clone(), 200,2,2,2,0.1);
                    int i1 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
			                Holding.invur(p, 40l);
			                Holding.untouchable(p, 40l);
		                	Riptide(p, tar);
		                }
		            }, 40, 20);
					ordt.put(rn, i1);  
					ript.put(rn, i1);  
		        }
			}
	}

	
	/*
	public void Ring(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 5;
		if((d.getDamager() instanceof Skeleton||d.getDamager() instanceof ElderGuardian) && d.getEntity() instanceof LivingEntity&& !d.isCancelled() &&d.getDamager().hasMetadata("redboss")) 
		{
			LivingEntity p = (LivingEntity)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			final Location tl = le.getLocation().clone();
						if(rb1cooldown.containsKey(p.getUniqueId()))
				        {
				            long timer = (rb1cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
				            if(!(timer < 0))
				            {
				            }
				            else 
				            {
				                rb1cooldown.remove(p.getUniqueId()); // removing player from HashMap
				                p.getWorld().strikeLightningEffect(le.getLocation());
								for(int i = 0; i <10; i++) {
				                    AtomicInteger j = new AtomicInteger();	
				                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
							            	ArrayList<Location> ring = new ArrayList<Location>();
											p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
						                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/90) {
						                    	Location one = tl.clone();
						                    	one.setDirection(one.getDirection().rotateAroundY(angle));
						                    	one.add(one.getDirection().normalize().multiply(3.5));
						                    	ring.add(one);
						                	} 
						                	ring.forEach(l -> {
						                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									                @Override
									                public void run() {
														p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 6, 0.5,0.5,0.5,0);		
									                }
									            }, j.incrementAndGet()/60); 
						                		
						                	});
						                }
						            }, i*5); 	                    	
			                    }
								for(int i = 0; i <10; i++) {
				                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											for(Entity e : p.getWorld().getNearbyEntities(tl,3.5, 3.5, 3.5)) {
												if(p!=e && e instanceof Player) {
													LivingEntity le = (LivingEntity)e;
													le.damage(3,p);	
													if(le.getLocation().distance(tl)>=3.4) {
														le.teleport(le.getLocation());
													}	
												}
											}
						                }
						            }, i*5); 	                    	
			                    }
					            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				            }
				        }
				        else 
				        {
				        	p.getWorld().strikeLightningEffect(le.getLocation());
							for(int i = 0; i <10; i++) {
			                    AtomicInteger j = new AtomicInteger();	
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
						            	ArrayList<Location> ring = new ArrayList<Location>();
										p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
					                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/90) {
					                    	Location one = tl.clone();
					                    	one.setDirection(one.getDirection().rotateAroundY(angle));
					                    	one.add(one.getDirection().normalize().multiply(3.5));
					                    	ring.add(one);
					                	} 
					                	ring.forEach(l -> {
					                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
													p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 6, 0.5,0.5,0.5,0);				                    
								                }
								            }, j.incrementAndGet()/60); 
					                		
					                	});
					                }
					            }, i*5); 	                    	
		                    }
							for(int i = 0; i <10; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										for(Entity e : p.getWorld().getNearbyEntities(tl,3.5, 3.5, 3.5)) {
											if(p!=e && e instanceof Player) {
												LivingEntity le = (LivingEntity)e;
												le.damage(3,p);	
												if(le.getLocation().distance(tl)>=3.4) {
													le.teleport(le.getLocation());
												}	
											}
										}
					                }
					            }, i*5); 	                    	
		                    }
				            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        }
		}					
	}
	
	
	public void Charge(EntityTargetEvent ev) 
	{
		if((ev.getEntity() instanceof Skeleton) && ev.getEntity().hasMetadata("redboss")  && ev.getTarget() instanceof Player) 
		{
			LivingEntity p = (LivingEntity)ev.getEntity();
			Player le = (Player)ev.getTarget();
			int sec = 8;

			if(!rb3cooldown.containsKey(p.getUniqueId())) {
				return;
			}
			
			
				final Location tl = le.getEyeLocation().clone();
				final Location pl = p.getEyeLocation().clone();
					
					if(chcooldown.containsKey(p.getUniqueId()))
		            {
		                long timer = (chcooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		                if(!(timer < 0))
		                {
		                }
		                else 
		                {
		                	chcooldown.remove(p.getUniqueId());

		                    Holding.holding(null, p, 20l);
		                    Holding.invur(p, 20l);
			            	HashSet<Location> line = new HashSet<Location>();
		                    le.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 1.0f, 2f);
		                    le.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
		                    for(double d = 0; d <= pl.clone().distance(tl)+3; d += 0.5) {
		                    	Location al = pl.clone().add(tl.toVector().subtract(pl.toVector()).normalize().multiply(d));
		                    	if(al.getBlock().isPassable()) {
		                    		line.add(al);
		                    	}
		                    }
		                    line.forEach(l ->  {
	             				p.getWorld().spawnParticle(Particle.FLAME, l, 60, 1.5,1.5,1.5,0);
	    	                    p.getWorld().playSound(l, Sound.ITEM_FIRECHARGE_USE, 0.1f, 1.1f);
		                    });
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                		@Override
			                	public void run() 
				                {	
	    		                    line.forEach(l ->  {	
	    	    	                    p.getWorld().playSound(l, Sound.ENTITY_SKELETON_AMBIENT, 0.1f, 1.5f);
			             				p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5, 1.5,1.5,1.5);
			             				p.getWorld().spawnParticle(Particle.FLAME, l, 30, 1,1,1);
										for(Entity e : p.getWorld().getNearbyEntities(l,1.5, 1.5, 1.5)) {
											if(e instanceof LivingEntity&&  !(e.hasMetadata("portal"))) {
												LivingEntity le = (LivingEntity)e;
					                    		if (le==p && p.getLocation().add(0, 0.5, 0).getBlock().isPassable()) 
												{
							                    	p.teleport(p.getLocation().clone().add(0, 0.5, 0));	
							                    	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 40,1,false,false));
							                    	continue;
												}
												le.damage(1,p);		
											}
										}
	    		                    	p.teleport(l);
	    		                    });
					            }
	                    	}, 20); 
							ev.setCancelled(true);
							chcooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {
	                    Holding.holding(null, p, 20l);
	                    Holding.invur(p, 20l);
		            	HashSet<Location> line = new HashSet<Location>();
	                    le.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 1.0f, 2f);
	                    le.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
	                    for(double d = 0; d <= pl.clone().distance(tl)+3; d += 0.5) {
	                    	Location al = pl.clone().add(tl.toVector().subtract(pl.toVector()).normalize().multiply(d));
	                    	if(al.getBlock().isPassable()) {
	                    		line.add(al);
	                    	}
	                    }
	                    line.forEach(l ->  {	
             				p.getWorld().spawnParticle(Particle.FLAME, l, 60, 1.5,1.5,1.5,0);
    	                    p.getWorld().playSound(l, Sound.ITEM_FIRECHARGE_USE, 0.1f, 1.1f);
	                    });
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                		@Override
		                	public void run() 
			                {	
    		                    line.forEach(l ->  {	
    	    	                    p.getWorld().playSound(l, Sound.ENTITY_SKELETON_AMBIENT, 0.1f, 1.5f);
		             				p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5, 1.5,1.5,1.5);
		             				p.getWorld().spawnParticle(Particle.FLAME, l, 30, 1,1,1);
									for(Entity e : p.getWorld().getNearbyEntities(l,1.5, 1.5, 1.5)) {
										if(e instanceof LivingEntity&&  !(e.hasMetadata("portal"))) {
											LivingEntity le = (LivingEntity)e;
				                    		if (le==p && p.getLocation().add(0, 0.5, 0).getBlock().isPassable()) 
											{
						                    	p.teleport(p.getLocation().clone().add(0, 0.5, 0));	
						                    	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 40,1,false,false));
						                    	continue;
											}
											le.damage(1,p);		
										}
									}
    		                    	p.teleport(l);
    		                    });
				            }
                    	}, 20); 
						ev.setCancelled(true);
						chcooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}
	


	
	public void Eruption(EntityTargetEvent ev) 
	{
		if((ev.getEntity() instanceof Skeleton||ev.getEntity() instanceof ElderGuardian) && ev.getEntity().hasMetadata("redboss")  && ev.getTarget() instanceof Player) 
		{
			LivingEntity p = (LivingEntity)ev.getEntity();
			Player le = (Player)ev.getTarget();
			int sec = 9;
	        
			
			
				final Location tl = le.getLocation();
					
					if(rb3cooldown.containsKey(p.getUniqueId()))
		            {
		                long timer = (rb3cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		                if(!(timer < 0))
		                {
		                }
		                else 
		                {
		                    rb3cooldown.remove(p.getUniqueId()); // removing player from HashMap
			            	ArrayList<Location> line = new ArrayList<Location>();
		                    le.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 1.0f, 2f);
		                    le.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
		                    for(double d = 0.1; d <= 2.5; d += 0.2) {
			                    for(double an = 0; an <= Math.PI*2; an += Math.PI/90) {
				                    Location pl = tl.clone();
									pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(d));
									line.add(pl);
			                    }
		                    }
		                    line.forEach(l ->  {	
	             				p.getWorld().spawnParticle(Particle.SOUL, l, 1, 0.1,0.1,0.1,0);
		                    });
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                		@Override
			                	public void run() 
				                {	
	    		                    line.forEach(l ->  {	
			             				p.getWorld().spawnParticle(Particle.LAVA, l, 1, 1,1,1,5);
	    		                    });
		    	                    p.getWorld().playSound(p.getLocation(), Sound.ITEM_BUCKET_FILL_LAVA, 1.0f, 0f);
		    	                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_MAGMA_CUBE_SQUISH, 1.0f, 0f);
		    	                    p.getWorld().playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 0f);
		    	                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 1.0f, 0f);
					            }
	                    	}, 30); 

							for(int i = 0; i <8; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										for(Entity e : p.getWorld().getNearbyEntities(tl,3, 3, 3)) {
											if(e instanceof LivingEntity&&  !(e.hasMetadata("portal"))) {
												LivingEntity le = (LivingEntity)e;
						                    	le.teleport(le.getLocation().clone().add(0, 0.5, 0));
												le.damage(1,p);		
											}
										}
					                }
					            }, i*2+30);
		                    }
							ev.setCancelled(true);
							rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {
		            	ArrayList<Location> line = new ArrayList<Location>();
	                    le.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 1.0f, 2f);
	                    le.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
	                    for(double d = 0.1; d <= 2.5; d += 0.2) {
		                    for(double an = 0; an <= Math.PI*2; an += Math.PI/90) {
			                    Location pl = tl.clone();
								pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(d));
								line.add(pl);
		                    }
	                    }
	                    line.forEach(l ->  {	
             				p.getWorld().spawnParticle(Particle.SOUL, l, 1, 0.1,0.1,0.1,0);
	                    });
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                		@Override
		                	public void run() 
			                {	
    		                    line.forEach(l ->  {	
		             				p.getWorld().spawnParticle(Particle.LAVA, l, 1, 1,1,1,5);
    		                    });
	    	                    p.getWorld().playSound(p.getLocation(), Sound.ITEM_BUCKET_FILL_LAVA, 1.0f, 0f);
	    	                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_MAGMA_CUBE_SQUISH, 1.0f, 0f);
	    	                    p.getWorld().playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 0f);
	    	                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 1.0f, 0f);
				            }
                    	}, 30); 

						for(int i = 0; i <8; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									for(Entity e : p.getWorld().getNearbyEntities(tl,3, 3, 3)) {
										if(e instanceof LivingEntity&&  !(e.hasMetadata("portal"))) {
											LivingEntity le = (LivingEntity)e;
					                    	le.teleport(le.getLocation().clone().add(0, 0.5, 0));	
											le.damage(1,p);		
										}
									}
				                }
				            }, i*2+30);
	                    }
						ev.setCancelled(true);
						rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}
	

	

	
	public void Fireball(EntityTargetEvent ev) 
	{
		if((ev.getEntity() instanceof Skeleton||ev.getEntity() instanceof ElderGuardian) && ev.getEntity().hasMetadata("redboss") && ev.getTarget() instanceof Player) 
		{
			LivingEntity p = (LivingEntity)ev.getEntity();
			int sec = 4;

		
			
					
					if(rb4cooldown.containsKey(p.getUniqueId()))
		            {
		                long timer = (rb4cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		                if(!(timer < 0))
		                {
		                }
		                else 
		                {
		                    rb4cooldown.remove(p.getUniqueId()); // removing player from HashMap
		                    if(fbt.containsKey(p.getUniqueId())) {
		                    	Bukkit.getScheduler().cancelTask(fbt.get(p.getUniqueId()));
		                    }
							int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	        	                @Override
	        	                public void run() 
	        	                {
	        	                	if(p.isValid()) {
		        	                    Fireball fb = (Fireball) p.launchProjectile(Fireball.class);
		        	                    fb.setYield(0.1f);
		        	                    fb.setGlowing(true);
		        	                    fb.setBounce(false);
		        	                    fb.setShooter(p);
		        	                    fb.setVelocity(p.getLocation().getDirection().normalize().multiply(1.3));
		        	                    fb.setIsIncendiary(false);
		        						fb.setMetadata("redbfb", new FixedMetadataValue(RMain.getInstance(), true));
	        	                	}
	        	                }
	        				}, 0, 40);
							fbt.put(p.getUniqueId(), task);
							rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {
	                    if(fbt.containsKey(p.getUniqueId())) {
	                    	Bukkit.getScheduler().cancelTask(fbt.get(p.getUniqueId()));
	                    }
						int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
        	                @Override
        	                public void run() 
        	                {
        	                	if(p.isValid()) {
	        	                    Fireball fb = (Fireball) p.launchProjectile(Fireball.class);
	        	                    fb.setYield(0.1f);
	        	                    fb.setGlowing(true);
	        	                    fb.setBounce(false);
	        	                    fb.setShooter(p);
	        	                    fb.setVelocity(p.getLocation().getDirection().normalize().multiply(1.3));
	        	                    fb.setIsIncendiary(false);
	        						fb.setMetadata("redbfb", new FixedMetadataValue(RMain.getInstance(), true));
        	                	}
        	                }
        				}, 0, 40);
						fbt.put(p.getUniqueId(), task);
						
						rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}

	
	public void MageFB(EntitySpellCastEvent ev) 
	{
		if(ev.getEntity() instanceof Illusioner && ev.getEntity().hasMetadata("red") && ev.getEntity().hasMetadata("mage")) 
		{
			Illusioner p = (Illusioner)ev.getEntity();
			
			ev.setCancelled(true);
            Fireball fb = (Fireball) p.launchProjectile(Fireball.class);
            fb.setYield(0.1f);
            fb.setGlowing(true);
            fb.setShooter(p);
            fb.setVelocity(p.getLocation().getDirection().normalize().multiply(1.5));
            fb.setIsIncendiary(false);
			fb.setMetadata("redfb", new FixedMetadataValue(RMain.getInstance(), true));
							
		}
		if(ev.getEntity() instanceof Evoker && ev.getEntity().hasMetadata("red") && ev.getEntity().hasMetadata("mage")) 
		{
			Evoker p = (Evoker)ev.getEntity();
			
			ev.setCancelled(true);
        	if(p.hasMetadata("leader")) {
                Fireball fb = (Fireball) p.launchProjectile(Fireball.class);
                fb.setYield(0.1f);
                fb.setGlowing(true);
                fb.setShooter(p);
                fb.setVelocity(p.getLocation().getDirection().normalize().multiply(3));
                fb.setIsIncendiary(false);
				fb.setMetadata("redfb", new FixedMetadataValue(RMain.getInstance(), true));
        	}
            Fireball fb = (Fireball) p.launchProjectile(Fireball.class);
            fb.setYield(0.1f);
            fb.setGlowing(true);
            fb.setShooter(p);
            fb.setVelocity(p.getLocation().getDirection().normalize().multiply(1.5));
            fb.setIsIncendiary(false);
			fb.setMetadata("redfb", new FixedMetadataValue(RMain.getInstance(), true));
							
		}
	}
	
	
	public void Fireball(EntityExplodeEvent ev) 
	{
        
		
		if(ev.getEntity() instanceof Fireball) {
			Fireball fb = (Fireball)ev.getEntity();
			if(fb.getShooter() instanceof LivingEntity) {
				LivingEntity p = (LivingEntity) fb.getShooter();
				if(fb.hasMetadata("redfb")) {
					ev.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, ev.getLocation(), 2,1,1,1);
					p.getWorld().playSound(ev.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
					for(Entity n : fb.getNearbyEntities(2.3, 2.3, 2.3)) {
						if(n!=p && n instanceof LivingEntity&& !(n.hasMetadata("raid"))) {
							LivingEntity le = (LivingEntity)n;
							le.damage(3,p);	
						}
					}
					ev.setCancelled(true);
				}
				if(fb.hasMetadata("redbfb")) {
					ev.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, ev.getLocation(), 2,1,1,1);
					p.getWorld().playSound(ev.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
					for(Entity n : fb.getNearbyEntities(3, 3, 3)) {
						if(n!=p && n instanceof LivingEntity&& !(n.hasMetadata("raid"))) {
							LivingEntity le = (LivingEntity)n;
							le.damage(3,p);	
						}
					}
					ev.setCancelled(true);
				}
			}
			
		}
	}

	
	public void Fireball(EntityDeathEvent d) 
	{		
		if(d.getEntity().hasMetadata("raid") && d.getEntity().hasMetadata("redboss")) {
			if(fbt.containsKey(d.getEntity().getUniqueId())) {
				Bukkit.getScheduler().cancelTask(fbt.get(d.getEntity().getUniqueId()));
			}
		}
	}
	

	
	public void Breath(EntityTargetEvent ev) 
	{
		if(ev.getEntity() instanceof ElderGuardian && ev.getEntity().hasMetadata("redboss") && ev.getEntity().hasMetadata("ruined") && ev.getTarget() instanceof Player) 
		{
			ElderGuardian p = (ElderGuardian)ev.getEntity();
			int sec = 2;
	        
			
					if(rb5cooldown.containsKey(p.getUniqueId()))
		            {
		                long timer = (rb5cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		                if(!(timer < 0))
		                {
		                }
		                else 
		                {
		                    rb5cooldown.remove(p.getUniqueId()); // removing player from HashMap


							p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 3, false, false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2, false, false));
							for(int i = 0; i <6; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                    ArrayList<Location> cir = new ArrayList<Location>();
					                    AtomicInteger j = new AtomicInteger();
					                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
				                    	for(double angley= -Math.PI/5; angley<Math.PI/5; angley += Math.PI/45) {
				                        	Location one = p.getLocation();
					                    	one.setDirection(one.getDirection().rotateAroundY(angley));
						                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/45) {
						                    	one.setDirection(one.getDirection().rotateAroundAxis(p.getLocation().getDirection(),angle));
					                    		for(double i = 0.1; i<4.4;i+=0.5) {
					                    			Location two = one.clone();
						                    		two.add(two.getDirection().normalize().multiply(i));
							                    	cir.add(two);		                    			
					                    		}
						                    }
				                    	}	
										p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 1.2f);
										p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 1, 0);
					                	cir.forEach(l -> {
					                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
													p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l.add(0, -0.3, 0), 1, 0.2,0.2,0.2,0.5);
													for(Entity e : p.getWorld().getNearbyEntities(l,1, 1, 1)) {
														if(p!=e && e instanceof LivingEntity&&  !(e.hasMetadata("portal"))) {
															LivingEntity le = (LivingEntity)e;
															les.add(le);	
														}
													}
								                }
								            }, j.incrementAndGet()/2000); 
					                	});
				                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
							                    for(LivingEntity le: les) {
													le.damage(3,p);			                    	
							                    }
							                }
							            }, j.incrementAndGet()/2000); 
					                }
					            }, i*5); 	                    	
		                    }
							ev.setCancelled(true);
							rb5cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {


						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 3, false, false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 3, false, false));
						for(int i = 0; i <6; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                    ArrayList<Location> cir = new ArrayList<Location>();
				                    AtomicInteger j = new AtomicInteger();
				                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
			                    	for(double angley= -Math.PI/5; angley<Math.PI/5; angley += Math.PI/45) {
			                        	Location one = p.getLocation();
				                    	one.setDirection(one.getDirection().rotateAroundY(angley));
					                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/45) {
					                    	one.setDirection(one.getDirection().rotateAroundAxis(p.getLocation().getDirection(),angle));
				                    		for(double i = 0.1; i<4.4;i+=0.5) {
				                    			Location two = one.clone();
					                    		two.add(two.getDirection().normalize().multiply(i));
						                    	cir.add(two);		                    			
				                    		}
					                    }
			                    	}	
									p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 1.2f);
									p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 1, 0);
				                	cir.forEach(l -> {
				                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
												p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l.add(0, -0.3, 0), 1, 0.2,0.2,0.2,0.5);
												for(Entity e : p.getWorld().getNearbyEntities(l,1, 1, 1)) {
													if(p!=e && e instanceof LivingEntity&&  !(e.hasMetadata("portal"))) {
														LivingEntity le = (LivingEntity)e;
														les.add(le);	
													}
												}
							                }
							            }, j.incrementAndGet()/2000); 
				                	});
			                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
						                    for(LivingEntity le: les) {
												le.damage(3,p);			                    	
						                    }
						                }
						            }, j.incrementAndGet()/2000); 
				                }
				            }, i*5); 	                    	
	                    }
						ev.setCancelled(true);
						rb5cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}
	
	@SuppressWarnings("deprecation")
	
	public void Meteor(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 35;
		if(d.getEntity() instanceof ElderGuardian&& !d.isCancelled() && d.getEntity().hasMetadata("redboss") && d.getEntity().hasMetadata("ruined")) 
		{
			ElderGuardian p = (ElderGuardian)d.getEntity();
			if(!rb2cooldown.containsKey(p.getUniqueId()) ||(p.getHealth() - d.getDamage() <= p.getMaxHealth()*0.2)) {
				return;
			}
			if(p.hasMetadata("failed")) {
				return;
			}
				if(rb7cooldown.containsKey(p.getUniqueId()))
		        {
		            long timer = (rb7cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		            if(!(timer < 0))
		            {
		            }
		            else 
		            {
		                rb7cooldown.remove(p.getUniqueId()); // removing player from HashMap
			        	d.setCancelled(true);
	                    if(fbt.containsKey(p.getUniqueId())) {
	                    	Bukkit.getScheduler().cancelTask(fbt.get(p.getUniqueId()));
	                    }
		                Location rl = OverworldRaids.getraidloc(p).clone();
		    			String rn = p.getMetadata("raid").get(0).asString();
	                    p.teleport(rl.add(0, 1, 0));
	                    final Location tl = p.getLocation();
		                
		                for(Entity e : OverworldRaids.getheroes(p)) {
		                	if(e instanceof Player && !e.hasMetadata("fake")) {
		                		Player pe = (Player) e;
		                		Holding.invur(pe, 40l);
		                		pe.sendMessage("RedKnight: You can't change your destiny.");
								pe.getWorld().playSound(pe.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 0.3f, 2);
								if(pe.getWorld() == p.getWorld()) {
									pe.teleport(tl);
								}
		                	}
		                }
	                    AtomicInteger j = new AtomicInteger();	
						for(int i = 0; i <5; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
					            	ArrayList<Location> ring = new ArrayList<Location>();
									p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);

				                	for(double an = 0; an<Math.PI*2; an +=Math.PI/180) {
				                		ring.add(tl.clone().add(tl.getDirection().normalize().rotateAroundY(an).multiply(an)).add(0, an+j.get(), 0));
				                	}
				                	ring.forEach(l -> {
										tl.getWorld().spawnParticle(Particle.ASH, l, 5, 0.5,0.5,0.5,0);
										tl.getWorld().spawnParticle(Particle.SMOKE_NORMAL, l, 2, 0.5,0.5,0.5,0);
										tl.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 2, 0.5,0.5,0.5,0);
				                		
				                	});
				                	j.incrementAndGet();
				                }
				            }, i*5); 	                    	
	                    }
						ArrayList<Location> mls1 = new ArrayList<>();
						ArrayList<Location> mls2 = new ArrayList<>();
						ArrayList<Location> mls3 = new ArrayList<>();
						ArrayList<Location> mls4 = new ArrayList<>();
						for(int i = 0; i<15; i++) {
							mls1.add(rl.clone().add(i, 7, 0));
							mls2.add(rl.clone().add(-i, 7, 0));
							mls3.add(rl.clone().add(0, 7, i));
							mls4.add(rl.clone().add(0, 7, -i));
						}

						for(int i = 0; i<15; i++) {
							mls1.add(rl.clone().add(i, 7, i));
							mls2.add(rl.clone().add(-i, 7, i));
							mls3.add(rl.clone().add(-i, 7, -i));
							mls4.add(rl.clone().add(i, 7, -i));
						}


						for(int i = 0; i<15; i++) {
		                	Random r1 = new Random();
		                	Random r2 = new Random();
		                	Random r3 = new Random();
		                	Random r4 = new Random();
		                	double rd1 = r1.nextDouble()*20 *(r3.nextBoolean()?1:-1);
		                	double rd2 = r2.nextDouble()*20*(r4.nextBoolean()?1:-1);
							mls1.add(rl.clone().add(rd1, 7, rd2));
							mls2.add(rl.clone().add(rd1, 7, rd2));
							mls3.add(rl.clone().add(rd1, 7, rd2));
							mls4.add(rl.clone().add(rd1, 7, rd2));
						}
		                Holding.holding(null, p, 60l);
		                Holding.invur(p, 60l);
						
						int t1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
				                ordealable.put(p.getUniqueId(), true);
				                AtomicInteger j1 = new AtomicInteger();
				                AtomicInteger j2 = new AtomicInteger();
				                AtomicInteger j3 = new AtomicInteger();
				                AtomicInteger j4 = new AtomicInteger();
			                	mls1.forEach(l -> {
			                		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.RED_GLAZED_TERRACOTTA.createBlockData());
											fallingb.setInvulnerable(true);
											fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setMetadata("redknightmagma", new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setMetadata("redknightmagma"+rn, new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setVisualFire(true);
											fallingb.setDropItem(true);
											fallingb.setHurtEntities(true);
											magma.put(fallingb.getUniqueId(), p);
						                }
			                		}, j1.getAndIncrement()*4);
									ordt.put(rn, t1);
			                	});
			                	mls2.forEach(l -> {
			                		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.RED_GLAZED_TERRACOTTA.createBlockData());
											fallingb.setInvulnerable(true);
											fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setMetadata("redknightmagma", new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setMetadata("redknightmagma"+rn, new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setVisualFire(true);
											fallingb.setDropItem(true);
											fallingb.setHurtEntities(true);
											magma.put(fallingb.getUniqueId(), p);
						                }
			                		},j2.getAndIncrement()*4);
									ordt.put(rn, t1);
			                	});
			                	mls3.forEach(l -> {
			                		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.RED_GLAZED_TERRACOTTA.createBlockData());
											fallingb.setInvulnerable(true);
											fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setMetadata("redknightmagma", new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setMetadata("redknightmagma"+rn, new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setVisualFire(true);
											fallingb.setDropItem(true);
											fallingb.setHurtEntities(true);
											magma.put(fallingb.getUniqueId(), p);
						                }
			                		},j3.getAndIncrement()*4);
									ordt.put(rn, t1);
			                	});
			                	mls4.forEach(l -> {
			                		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.RED_GLAZED_TERRACOTTA.createBlockData());
											fallingb.setInvulnerable(true);
											fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setMetadata("redknightmagma", new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setMetadata("redknightmagma"+rn, new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setVisualFire(true);
											fallingb.setDropItem(true);
											fallingb.setHurtEntities(true);
											magma.put(fallingb.getUniqueId(), p);
						                }
			                		},j4.getAndIncrement()*4);
									ordt.put(rn, t1);
			                	});
								for(int i = 0; i <45; i++) {
				                    int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
							                for(Player pe : OverworldRaids.getheroes(p)) {
						                		if(pe.getWorld().equals(p.getWorld()) && rl.clone().distance(p.getLocation()) > 20 && pe.getWorld() == p.getWorld()) {
						                    		pe.sendMessage(ChatColor.DARK_RED+"You Can't Get Away!");
						                    		pe.teleport(rl);
						                		}
							                }
							            	ArrayList<Location> ring = new ArrayList<Location>();
							            	HashSet<Player> hp = new HashSet<>();
						                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/180) {
						                    	Location one = rl.setDirection(rl.getDirection()).clone();
						                    	one.setDirection(one.getDirection().rotateAroundY(angle));
						                    	one.add(one.getDirection().normalize().multiply(15));
						                    	ring.add(one);
						                	} 
						                	ring.forEach(l -> {
												p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 15, 0.5,0.7,0.5,0);
												for(Entity e : p.getWorld().getNearbyEntities(l,0.5, 0.67, 0.5)) {
													if(e instanceof Player && e!=p) {
														Player pe = (Player)e;
														hp.add(pe);
													}
												}
						                		
						                	});
											hp.forEach(pe -> pe.damage(5,p));
						                }
						            }, i*4); 	 
									ordt.put(rn, t1);                   	
			                    }
			                }
			            }, 40);
						ordt.put(rn, t1);  
	                    rb7cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else 
		        {
		        	d.setCancelled(true);
                    if(fbt.containsKey(p.getUniqueId())) {
                    	Bukkit.getScheduler().cancelTask(fbt.get(p.getUniqueId()));
                    }
	                Location rl = OverworldRaids.getraidloc(p).clone();
	    			String rn = p.getMetadata("raid").get(0).asString();
                    p.teleport(rl.add(0, 1, 0));
                    final Location tl = p.getLocation();

	                for(Entity e : OverworldRaids.getheroes(p)) {
	                	if(e instanceof Player && !e.hasMetadata("fake")) {
	                		Player pe = (Player) e;
	                		Holding.invur(pe, 40l);
	                		pe.sendMessage("RedKnight: You can't change your destiny.");
							pe.getWorld().playSound(pe.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 0.3f, 2);
							if(pe.getWorld() == p.getWorld()) {
								
							pe.teleport(tl);
							}
	                	}
	                }
                    AtomicInteger j = new AtomicInteger();	
					for(int i = 0; i <5; i++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
				            	ArrayList<Location> ring = new ArrayList<Location>();
								p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);

			                	for(double an = 0; an<Math.PI*2; an +=Math.PI/180) {
			                		ring.add(tl.clone().add(tl.getDirection().normalize().rotateAroundY(an).multiply(an)).add(0, an+j.get(), 0));
			                	}
			                	ring.forEach(l -> {
									tl.getWorld().spawnParticle(Particle.ASH, l, 5, 0.5,0.5,0.5,0);
									tl.getWorld().spawnParticle(Particle.SMOKE_NORMAL, l, 2, 0.5,0.5,0.5,0);
									tl.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 2, 0.5,0.5,0.5,0);
			                		
			                	});
			                	j.incrementAndGet();
			                }
			            }, i*5); 	                    	
                    }
					ArrayList<Location> mls1 = new ArrayList<>();
					ArrayList<Location> mls2 = new ArrayList<>();
					ArrayList<Location> mls3 = new ArrayList<>();
					ArrayList<Location> mls4 = new ArrayList<>();
					for(int i = 0; i<15; i++) {
						mls1.add(rl.clone().add(i, 7, 0));
						mls2.add(rl.clone().add(-i, 7, 0));
						mls3.add(rl.clone().add(0, 7, i));
						mls4.add(rl.clone().add(0, 7, -i));
					}

					for(int i = 0; i<15; i++) {
						mls1.add(rl.clone().add(i, 7, i));
						mls2.add(rl.clone().add(-i, 7, i));
						mls3.add(rl.clone().add(-i, 7, -i));
						mls4.add(rl.clone().add(i, 7, -i));
					}


					for(int i = 0; i<15; i++) {
	                	Random r1 = new Random();
	                	Random r2 = new Random();
	                	Random r3 = new Random();
	                	Random r4 = new Random();
	                	double rd1 = r1.nextDouble()*20 *(r3.nextBoolean()?1:-1);
	                	double rd2 = r2.nextDouble()*20*(r4.nextBoolean()?1:-1);
						mls1.add(rl.clone().add(rd1, 7, rd2));
						mls2.add(rl.clone().add(rd1, 7, rd2));
						mls3.add(rl.clone().add(rd1, 7, rd2));
						mls4.add(rl.clone().add(rd1, 7, rd2));
					}
	                Holding.holding(null, p, 60l);
	                Holding.invur(p, 60l);
					
					int t1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
			                ordealable.put(p.getUniqueId(), true);
			                AtomicInteger j1 = new AtomicInteger();
			                AtomicInteger j2 = new AtomicInteger();
			                AtomicInteger j3 = new AtomicInteger();
			                AtomicInteger j4 = new AtomicInteger();
		                	mls1.forEach(l -> {
		                		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.RED_GLAZED_TERRACOTTA.createBlockData());
										fallingb.setInvulnerable(true);
										fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setMetadata("redknightmagma", new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setMetadata("redknightmagma"+rn, new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setVisualFire(true);
										fallingb.setDropItem(true);
										fallingb.setHurtEntities(true);
										magma.put(fallingb.getUniqueId(), p);
					                }
		                		}, j1.getAndIncrement()*4);
								ordt.put(rn, t1);
		                	});
		                	mls2.forEach(l -> {
		                		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.RED_GLAZED_TERRACOTTA.createBlockData());
										fallingb.setInvulnerable(true);
										fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setMetadata("redknightmagma", new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setMetadata("redknightmagma"+rn, new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setVisualFire(true);
										fallingb.setDropItem(true);
										fallingb.setHurtEntities(true);
										magma.put(fallingb.getUniqueId(), p);
					                }
		                		},j2.getAndIncrement()*4);
								ordt.put(rn, t1);
		                	});
		                	mls3.forEach(l -> {
		                		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.RED_GLAZED_TERRACOTTA.createBlockData());
										fallingb.setInvulnerable(true);
										fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setMetadata("redknightmagma", new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setMetadata("redknightmagma"+rn, new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setVisualFire(true);
										fallingb.setDropItem(true);
										fallingb.setHurtEntities(true);
										magma.put(fallingb.getUniqueId(), p);
					                }
		                		},j3.getAndIncrement()*4);
								ordt.put(rn, t1);
		                	});
		                	mls4.forEach(l -> {
		                		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.RED_GLAZED_TERRACOTTA.createBlockData());
										fallingb.setInvulnerable(true);
										fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setMetadata("redknightmagma", new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setMetadata("redknightmagma"+rn, new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setVisualFire(true);
										fallingb.setDropItem(true);
										fallingb.setHurtEntities(true);
										magma.put(fallingb.getUniqueId(), p);
					                }
		                		},j4.getAndIncrement()*4);
								ordt.put(rn, t1);
		                	});
							for(int i = 0; i <45; i++) {
			                    int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
						                for(Player pe : OverworldRaids.getheroes(p)) {
					                		if(pe.getWorld().equals(p.getWorld()) && rl.clone().distance(p.getLocation()) > 20 && pe.getWorld() == p.getWorld()) {
					                    		pe.sendMessage(ChatColor.DARK_RED+"You Can't Get Away!");
					                    		pe.teleport(rl);
					                		}
						                }
						            	ArrayList<Location> ring = new ArrayList<Location>();
						            	HashSet<Player> hp = new HashSet<>();
					                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/180) {
					                    	Location one = rl.setDirection(rl.getDirection()).clone();
					                    	one.setDirection(one.getDirection().rotateAroundY(angle));
					                    	one.add(one.getDirection().normalize().multiply(15));
					                    	ring.add(one);
					                	} 
					                	ring.forEach(l -> {
											p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 15, 0.5,0.7,0.5,0);
											for(Entity e : p.getWorld().getNearbyEntities(l,0.5, 0.67, 0.5)) {
												if(e instanceof Player && e!=p) {
													Player pe = (Player)e;
													hp.add(pe);
												}
											}
					                		
					                	});
										hp.forEach(pe -> pe.damage(5,p));
					                }
					            }, i*4); 	 
								ordt.put(rn, t1);                   	
		                    }
		                }
		            }, 40);
					ordt.put(rn, t1);  
		            rb7cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
	}

	
	public void MagmaBlock(EntityDropItemEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(magma.containsKey(fallingb.getUniqueId())){
	        	ev.setCancelled(true);
				LivingEntity p = (LivingEntity) Holding.ale(magma.get(fallingb.getUniqueId()));
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, tl, 1);

				for (Entity e : p.getWorld().getNearbyEntities(tl, 3, 3, 3))
				{
					if(p!=e && e instanceof Player) {
						Player le = (Player)e;
                		le.damage(6, p);
					}
					
				}
				p.getWorld().playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 0);
				fallingb.remove();
	        }
		 }
	}



	
	public void MagmaBlock(EntityDamageByEntityEvent ev) 
	{
		if(ev.getDamager() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getDamager();
	        if(magma.containsKey(fallingb.getUniqueId())){
	        	ev.setCancelled(true);
				LivingEntity p = (LivingEntity) Holding.ale(magma.get(fallingb.getUniqueId()));
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, tl, 1);

				for (Entity e : p.getWorld().getNearbyEntities(tl, 3, 3, 3))
				{
					if(p!=e && e instanceof Player) {
						Player le = (Player)e;
                		le.damage(6, p);
					}
					
				}
				p.getWorld().playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 0);
				fallingb.remove();
	        }
		 }
	}


	
	public void MagmaBlock(EntityChangeBlockEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(magma.containsKey(fallingb.getUniqueId())){
	        	ev.setCancelled(true);
				LivingEntity p = (LivingEntity) Holding.ale(magma.get(fallingb.getUniqueId()));
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, tl, 1);

				for (Entity e : p.getWorld().getNearbyEntities(tl, 3, 3, 3))
				{
					if(p!=e && e instanceof Player) {
						Player le = (Player)e;
                		le.damage(6, p);
					}
					
				}
				p.getWorld().playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 0);
				fallingb.remove();
	        }
		 }
	}
	
	
	

	@SuppressWarnings("deprecation")
	
	public void Ordeal(EntityDamageByEntityEvent d) 
	{
	    
		int sec =70;
		if(d.getEntity() instanceof ElderGuardian && !d.isCancelled() && d.getEntity().hasMetadata("redboss") && d.getEntity().hasMetadata("ruined")&& !d.getEntity().hasMetadata("failed")) 
		{
			ElderGuardian p = (ElderGuardian)d.getEntity();
			if(!(p.getHealth() - d.getDamage() <= p.getMaxHealth()*0.2) || !rb2cooldown.containsKey(p.getUniqueId())|| !rb7cooldown.containsKey(p.getUniqueId())|| !ordealable.containsKey(p.getUniqueId())) {
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
	                    if(fbt.containsKey(p.getUniqueId())) {
	                    	Bukkit.getScheduler().cancelTask(fbt.get(p.getUniqueId()));
	                    }
		    			String rn = p.getMetadata("raid").get(0).asString();
		                Location rl = OverworldRaids.getraidloc(p).clone();
						p.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						p.setHealth(p.getMaxHealth()*0.2);
		                d.setCancelled(true);
	                	p.teleport(rl.clone().add(0, 0, 1));
		                Holding.holding(null, p, 1000l);
		                Holding.invur(p, 1000l);
		                for(Player pe : OverworldRaids.getheroes(p)) {
	                		pe.sendMessage(ChatColor.DARK_RED+"Time To Ordeal.");
	                		pe.teleport(rl);
	                		Holding.invur(pe, 40l);
		                }
	                    int t1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {

								for(int i = 0; i <10; i++) {
				                    int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
						                	p.teleport(rl.clone().add(0, 0.5, 1));
											p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
						                    AtomicInteger j = new AtomicInteger(10);
											for(int i = 0; i <20; i++) {
												int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									                @Override
									                public void run() {
									                	Random r1 = new Random();
									                	Random r2 = new Random();
									                	Random r3 = new Random();
									                	Random r4 = new Random();
									                	double rd1 = r1.nextDouble()*10 *(r3.nextBoolean()?1:-1);
									                	double rd2 = r2.nextDouble()*10*(r4.nextBoolean()?1:-1);
									                	Location frl = new Location(p.getWorld(), p.getLocation().getX()+rd1, p.getEyeLocation().getY(), p.getLocation().getZ()+rd2);
									                	
									                	Item sn = p.getWorld().dropItem(frl, new ItemStack(Material.FIRE_CORAL_BLOCK));
									                	sn.setMetadata("redknightcharge", new FixedMetadataValue(RMain.getInstance(), true));
									                	sn.setMetadata("redknightcharge"+rn, new FixedMetadataValue(RMain.getInstance(), true));
									                	sn.setGlowing(true);
									                	sn.setGravity(false);
									                	sn.setVisualFire(true);
									                	sn.setVelocity(p.getLocation().toVector().subtract(frl.toVector()).normalize().multiply(0.18));

										                for(Player pe : OverworldRaids.getheroes(p)) {
									                		if(pe.getWorld().equals(p.getWorld()) && rl.clone().distance(p.getLocation()) > 20 && pe.getWorld() == p.getWorld()) {
									                    		pe.sendMessage(ChatColor.DARK_RED+"You Can't Get Away!");
									                    		pe.teleport(rl);
									                		}
										                }
										            	ArrayList<Location> ring = new ArrayList<Location>();
										            	HashSet<Player> hp = new HashSet<>();
									                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/180) {
									                    	Location one = rl.setDirection(rl.getDirection()).clone();
									                    	one.setDirection(one.getDirection().rotateAroundY(angle));
									                    	one.add(one.getDirection().normalize().multiply(j.get()*2));
									                    	ring.add(one);
									                	} 
														j.getAndDecrement();
														if(j.get()<=0) {
															j.set(10);
														}
									                	ring.forEach(l -> {
															p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 15, 0.7,1.1,0.7,0);
															for(Entity e : p.getWorld().getNearbyEntities(l,0.7, 1.3, 0.7)) {
																if(e instanceof Player && e!=p) {
																	Player pe = (Player)e;
																	hp.add(pe);
																}
															}
									                		
									                	});
														hp.forEach(pe -> pe.damage(1,p));
									                }
									            }, i*10); 	 
												ordt.put(rn, t1);
						                    }
											for(int i = 0; i <100; i++) {
												int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									                @Override
									                public void run() {
									                	for(Entity e : p.getNearbyEntities(1, 1, 1)) {
									                		if(e.hasMetadata("redknightcharge"+rn)) {
									                			Item sn = (Item) e;
									                			Bukkit.getServer().getPluginManager().callEvent(new EntityPickupItemEvent(p,sn, 1));
									                			sn.remove();
									                			
									                		}
									                	}
									                }
									            }, i*2); 	 
												ordt.put(rn, t1);
						                    }
						                }
						            }, i*40); 	 
									ordt.put(rn, t1);                   	
			                    }
			                }
			            }, 20);
						ordt.put(rn, t1);
	                    int t2 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	if(redcharge.containsKey(p.getUniqueId())) {
				                	redcharge.remove(p.getUniqueId());
			                	}
			    				p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("redknightcharge"+rn)).forEach(e -> e.remove());
				                for(Player pe : OverworldRaids.getheroes(p)) {

									for(int i = 0; i <30; i++) {
										int t2 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
												if(pe.getWorld() == p.getWorld()) {
								                	final Location pel = pe.getLocation();
								                    p.getWorld().playSound(pel, Sound.BLOCK_LAVA_POP, 1.0f, 2f);
								                    p.getWorld().playSound(pel, Sound.BLOCK_LAVA_POP, 1.0f, 0f);
						             				p.getWorld().spawnParticle(Particle.LANDING_LAVA, pel, 500, 1.5,0.1,1.5,0);
						             				int t2 =Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											                @Override
											                public void run() {	
											                		p.getWorld().playSound(pel, Sound.ITEM_BUCKET_FILL_LAVA, 1.0f, 0f);
											                		p.getWorld().playSound(pel, Sound.ENTITY_PLAYER_SPLASH, 1.0f, 0f);
												                    p.getWorld().playSound(pel, Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
																	p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, pel, 200, 1.5,1,1.5,0);	
																	p.getWorld().spawnParticle(Particle.LAVA, pel, 200, 1.5,1,1.5,0);	
																for(Entity e : p.getWorld().getNearbyEntities(pel,1.5, 1.5, 1.5)) {
																	if(e instanceof Player && e!=p) {
																		Player pe = (Player)e;
																		pe.damage(15,p);
																	}
																}
											                }
											            }, 10); 
						        					ordt.put(rn, t2);
												}
							                }
							            }, i*10); 	 
										ordt.put(rn, t2);               	
				                    }
				                }
			                }
			            }, 650);
						ordt.put(rn, t2);
	                    int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	p.playEffect(EntityEffect.HURT);
			                	Holding.ale(p).setMetadata("failed", new FixedMetadataValue(RMain.getInstance(),true));
		                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
				                for(Player pe : OverworldRaids.getheroes(p)) {
			                		pe.sendMessage("How dare you!..");
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
			            }, 1000);
						ordt.put(rn, t3);
			            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		            }
		        }
		        else 
		        {
                    if(fbt.containsKey(p.getUniqueId())) {
                    	Bukkit.getScheduler().cancelTask(fbt.get(p.getUniqueId()));
                    }
	    			String rn = p.getMetadata("raid").get(0).asString();
	                Location rl = OverworldRaids.getraidloc(p).clone();
					p.setHealth(p.getMaxHealth()*0.2);
					p.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                d.setCancelled(true);
                	p.teleport(rl.clone().add(0, 0, 1));
	                Holding.holding(null, p, 1000l);
	                Holding.invur(p, 1000l);
	                for(Player pe : OverworldRaids.getheroes(p)) {
                		pe.sendMessage(ChatColor.DARK_RED+"Time To Ordeal.");
                		pe.teleport(rl);
                		Holding.invur(pe, 40l);
	                }
                    int t1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {

							for(int i = 0; i <10; i++) {
			                    int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	p.teleport(rl.clone().add(0, 0.5, 1));
										p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
					                    AtomicInteger j = new AtomicInteger(20);
										for(int i = 0; i <20; i++) {
											int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
								                	Random r1 = new Random();
								                	Random r2 = new Random();
								                	Random r3 = new Random();
								                	Random r4 = new Random();
								                	double rd1 = r1.nextDouble()*10 *(r3.nextBoolean()?1:-1);
								                	double rd2 = r2.nextDouble()*10*(r4.nextBoolean()?1:-1);
								                	Location frl = new Location(p.getWorld(), p.getLocation().getX()+rd1, p.getEyeLocation().getY(), p.getLocation().getZ()+rd2);
								                	
								                	Item sn = p.getWorld().dropItem(frl, new ItemStack(Material.FIRE_CORAL_BLOCK));
								                	sn.setMetadata("redknightcharge", new FixedMetadataValue(RMain.getInstance(), true));
								                	sn.setMetadata("redknightcharge"+rn, new FixedMetadataValue(RMain.getInstance(), true));
								                	sn.setGlowing(true);
								                	sn.setGravity(false);
								                	sn.setVisualFire(true);
								                	sn.setVelocity(p.getLocation().toVector().subtract(frl.toVector()).normalize().multiply(0.18));
								                	
									                for(Player pe : OverworldRaids.getheroes(p)) {
								                		if(pe.getWorld().equals(p.getWorld()) && rl.clone().distance(p.getLocation()) > 20 && pe.getWorld() == p.getWorld()) {
								                    		pe.sendMessage(ChatColor.DARK_RED+"You Can't Get Away!");
								                    		pe.teleport(rl);
								                		}
									                }
									            	ArrayList<Location> ring = new ArrayList<Location>();
									            	HashSet<Player> hp = new HashSet<>();
								                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/180) {
								                    	Location one = rl.setDirection(rl.getDirection()).clone();
								                    	one.setDirection(one.getDirection().rotateAroundY(angle));
								                    	one.add(one.getDirection().normalize().multiply(j.get()*2));
								                    	ring.add(one);
								                	} 
													j.getAndDecrement();
													if(j.get()<=0) {
														j.set(10);
													}
								                	ring.forEach(l -> {
														p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 15, 0.7,1.1,0.7,0);
														for(Entity e : p.getWorld().getNearbyEntities(l,0.7, 1.3, 0.7)) {
															if(e instanceof Player && e!=p) {
																Player pe = (Player)e;
																hp.add(pe);
															}
														}
								                		
								                	});
													hp.forEach(pe -> pe.damage(1,p));
								                }
								            }, i*10); 	 
											ordt.put(rn, t1);
					                    }
										for(int i = 0; i <100; i++) {
											int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
								                	for(Entity e : p.getNearbyEntities(1, 1, 1)) {
								                		if(e.hasMetadata("redknightcharge"+rn)) {
								                			Item sn = (Item) e;
								                			Bukkit.getServer().getPluginManager().callEvent(new EntityPickupItemEvent(p,sn, 1));
								                			sn.remove();
								            				p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 8, false, false));
								                		}
								                	}
								                }
								            }, i*2); 	 
											ordt.put(rn, t1);
					                    }
					                }
					            }, i*40); 	 
								ordt.put(rn, t1);                   	
		                    }
		                }
		            }, 20);
					ordt.put(rn, t1);
                    int t2 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	if(redcharge.containsKey(p.getUniqueId())) {
			                	redcharge.remove(p.getUniqueId());
		                	}
		    				p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("redknightcharge"+rn)).forEach(e -> e.remove());
			                for(Player pe : OverworldRaids.getheroes(p)) {

								for(int i = 0; i <30; i++) {
									int t2 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											if(pe.getWorld() == p.getWorld()) {
							                	final Location pel = pe.getLocation();
							                    p.getWorld().playSound(pel, Sound.BLOCK_LAVA_POP, 1.0f, 2f);
							                    p.getWorld().playSound(pel, Sound.BLOCK_LAVA_POP, 1.0f, 0f);
					             				p.getWorld().spawnParticle(Particle.LANDING_LAVA, pel, 500, 1.5,0.1,1.5,0);
					             				int t2 =Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										                @Override
										                public void run() {	
										                		p.getWorld().playSound(pel, Sound.ITEM_BUCKET_FILL_LAVA, 1.0f, 0f);
										                		p.getWorld().playSound(pel, Sound.ENTITY_PLAYER_SPLASH, 1.0f, 0f);
											                    p.getWorld().playSound(pel, Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
																p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, pel, 200, 1.5,1,1.5,0);	
																p.getWorld().spawnParticle(Particle.LAVA, pel, 200, 1.5,1,1.5,0);	
															for(Entity e : p.getWorld().getNearbyEntities(pel,1.5, 1.5, 1.5)) {
																if(e instanceof Player && e!=p) {
																	Player pe = (Player)e;
																	pe.damage(15,p);
																}
															}
										                }
										            }, 10); 
					        					ordt.put(rn, t2);
											}
						                }
						            }, i*10); 	 
									ordt.put(rn, t2);               	
			                    }
			                }
		                }
		            }, 650);
					ordt.put(rn, t2);
                    int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	p.playEffect(EntityEffect.HURT);
		                	Holding.ale(p).setMetadata("failed", new FixedMetadataValue(RMain.getInstance(),true));
	                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
			                for(Player pe : OverworldRaids.getheroes(p)) {
		                		pe.sendMessage("How dare you!..");
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
		            }, 1000);
					ordt.put(rn, t3);
		            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		        }
			}
	}
	

	
	public void Ordeal(EntityPickupItemEvent d) 
	{
			if(d.getItem().hasMetadata("redknightcharge")) {

				if((d.getEntity().hasMetadata("redboss"))) {
					d.setCancelled(true);
					LivingEntity p = (LivingEntity) d.getEntity();
	    			String rn = p.getMetadata("raid").get(0).asString();
	    			
					redcharge.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
					redcharge.putIfAbsent(p.getUniqueId(), 1);
					p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
		            for(Player pe : OverworldRaids.getheroes(p)) {
		        		pe.sendMessage(ChatColor.DARK_RED+"RedKnight's Charge (" + redcharge.get(p.getUniqueId()) + " / 20)");
		            }
		            if(redcharge.get(p.getUniqueId()) >= 20) {
		            	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
		            	redcharge.remove(p.getUniqueId());
		            	Bukkit.getWorld("RedKnightRaid").getEntities().stream().filter(e -> e.hasMetadata("redknightcharge"+rn)).forEach(e -> e.remove());
						Bukkit.getWorld("RedKnightRaid").getEntities().stream().filter(e -> e.hasMetadata("redknightmagma"+rn)).forEach(e -> e.remove());
		            	Holding.ale(p).setAI(true);
		                rb6cooldown.remove(p.getUniqueId());
		                for(Player pe : OverworldRaids.getheroes(p)) {
		            		pe.sendMessage(ChatColor.DARK_RED+"You Can Never Beat Me!");
	                		Holding.invur(pe, 60l);
		            		pe.teleport(p);
		            		pe.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 255 ,false,false));
		            		pe.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 60, 255 ,false,false));
		                }
		 				Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {	
		                		p.removeMetadata("fake", RMain.getInstance());
		                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
		    	                p.setInvulnerable(false);
		    	                Holding.ale(p).setInvulnerable(false);
								p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p.getLocation(), 3000, 10,10,10);	
								p.getWorld().spawnParticle(Particle.FLAME, p.getLocation(), 3000, 10,10,10);	
			                    for(Player pe : OverworldRaids.getheroes(p)) {
			                		p.removeMetadata("fake", RMain.getInstance());
			            			p.getWorld().playSound(pe.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
			                		pe.setHealth(0);
			                    }
			                }
			            }, 60); 
		            }
				}
				else if(d.getEntity() instanceof Player) {
					
					d.setCancelled(true);
					d.getEntity().getWorld().playSound(d.getEntity().getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1, 0);
					d.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1 ,false,false));
					d.getItem().remove();
				}
			}
	}*/
}