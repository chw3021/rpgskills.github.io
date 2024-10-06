package io.github.chw3021.classes.oceanknight;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.google.common.util.concurrent.AtomicDouble;

import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Trident;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRiptideEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Oceskills extends Pak implements Serializable {
	

	/**
	 * 
	 */
	
	private static transient final long serialVersionUID = 1343715876136938259L;
	private HashMap<String, Long> rscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> prcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> jmcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> thcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> pncooldown = new HashMap<String, Long>();
	private HashMap<String, Long> eccooldown = new HashMap<String, Long>();
	private HashMap<String, Long> aultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> ault2cooldown = new HashMap<String, Long>();
	
	

	private HashMap<UUID, Location> trrn = new HashMap<UUID, Location>();
	private HashMap<UUID, Integer> trrnt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> trdv = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> trdvt = new HashMap<UUID, Integer>();
	

	private HashMap<UUID, Integer> bckwsh = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> bckwsht = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> clve = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> clvet = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> drll = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> drllt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> fld = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> fldt = new HashMap<UUID, Integer>();


	private HashMap<UUID, Integer> shldsm = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> shldsmt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> imple = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> implet = new HashMap<UUID, Integer>();
	
	//private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	static private HashMap<String, ItemStack> shield = new HashMap<String, ItemStack>();
	static private HashMap<String, Integer> shieldt = new HashMap<String, Integer>();
	private String path = new File("").getAbsolutePath();
	
	private OceSkillsData fsd;

	private static final Oceskills instance = new Oceskills ();
	public static Oceskills getInstance()
	{
		return instance;
	}



		
	public void er(PluginEnableEvent ev) 
	{
		OceSkillsData f = new OceSkillsData(OceSkillsData.loadData(path +"/plugins/RPGskills/OceSkillsData.data"));
		fsd = f;
	}
	
	
	public void classinv(InventoryClickEvent e)
	{
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Oceskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				OceSkillsData f = new OceSkillsData(OceSkillsData.loadData(path +"/plugins/RPGskills/OceSkillsData.data"));
				fsd = f;
			}
			
		}
	}

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		OceSkillsData f = new OceSkillsData(OceSkillsData.loadData(path +"/plugins/RPGskills/OceSkillsData.data"));
		fsd = f;
		
	}
	
	
	public void WaterBarrier(EntityDamageEvent d) 
	{
		if(d.getEntity() instanceof Player) 
		{
		Player p = (Player)d.getEntity();
			if(ClassData.pc.get(p.getUniqueId()) == 20) {
				if(Proficiency.getpro(p)>=1) {
					d.setDamage(d.getDamage()*0.45);
					if(p.isBlocking()) {
						d.setDamage(d.getDamage()*0.1);
	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						p.setCooldown(Material.SHIELD, 0);
	    	                }
	    	            }, 2); 
					}
				}
				if(p.getLocation().getBlock().hasMetadata("wtb of "+p.getName()) || p.getLocation().add(0,1,0).getBlock().hasMetadata("wtb of "+p.getName())|| p.getLocation().add(0,-1,0).getBlock().hasMetadata("wtb of "+p.getName()))
					{
						d.setCancelled(true);
						p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_RETURN, 0.2f, 0.35f);
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[물의방벽]").color(ChatColor.BOLD).color(ChatColor.AQUA).create());
						}
						else {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Barrior]").color(ChatColor.BOLD).color(ChatColor.AQUA).create());
						}

					
					}
			}
		}
	}
	
	
	public void WaterBarrier(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 20 && fsd.WaterBarrier.getOrDefault(p.getUniqueId(),0)>=1) {
		double sec =7*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
			if(!p.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
			}
			if(!p.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
			}
			if(!p.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 30, false, false));
			}
			if(p.isBlocking() && p.isSneaking() && !p.isRiptiding())
			{
				
				ev.setCancelled(true);
				final Location pll = p.getLocation().clone();
				final Location l  = pll.clone().add(0, 1, 0);
				final Material m  = l.getBlock().getType();
				final Location l1  = pll.clone().add(1, 1, 0);
				final Material m1  = l1.getBlock().getType();
				final Location l2  = pll.clone().add(0, 1, 1);
				final Material m2  = l2.getBlock().getType();
				final Location l3  = pll.clone().add(0, 1, -1);
				final Material m3  = l3.getBlock().getType();
				final Location l4  = pll.clone().add(-1, 1, 0);
				final Material m4  = l4.getBlock().getType();
				final Location l5  = pll.clone().add(-1, 1, -1);
				final Material m5  = l5.getBlock().getType();
				final Location l6  = pll.clone().add(-1, 1, 1);
				final Material m6  = l6.getBlock().getType();
				final Location l7  = pll.clone().add(1, 1, 1);
				final Material m7  = l7.getBlock().getType();
				final Location l8  = pll.clone().add(1, 1, -1);
				final Material m8  = l8.getBlock().getType();
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("물의방벽")
						.ename("WaterBarrier")
						.slot(0)
						.hm(rscooldown)
						.skillUse(() -> {
		                	if(trdvt.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(trdvt.get(p.getUniqueId()));
		                		trdvt.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							trdv.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						trdv.remove(p.getUniqueId());
		    	                }
		    	            }, 40); 
		                	trdvt.put(p.getUniqueId(), task);
		                	
			            	ArrayList<Location> line = new ArrayList<Location>();
		                    AtomicInteger j = new AtomicInteger();
							p.playSound(p.getLocation(), Sound.ENTITY_BOAT_PADDLE_WATER, 1, 0);
							p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 1, 0);
		    				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2, false, false));
		                	Location pl = p.getLocation();
		                    for(double i = 0.1; i<6;i+=0.5) {
		                    	for(double an = 0; an<=2*Math.PI; an+=Math.PI/180) {
				                    	line.add(pl.clone().add(pl.getDirection().normalize().rotateAroundY(an).multiply(4.5)).add(0, i, 0));
			                    }
		                    }
		                    	
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                    line.forEach(l ->{
						        		p.getWorld().spawnParticle(Particle.FALLING_WATER, l,5,0.1,0.1,0.1);
				                    });
				                }
							}, j.incrementAndGet()/50); 

		                	for(Entity e: p.getNearbyEntities(3.6,6,3.6)) {
								if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
								{
		                    		if (e instanceof Player) 
									{
										
										Player p1 = (Player) e;
										if(Party.hasParty(p) && Party.hasParty(p1))	{
										if(Party.getParty(p).equals(Party.getParty(p1)))
											{
												continue;
											}
										}
									}
									LivingEntity le = (LivingEntity)e;
				        			atk1(0.65*(1+fsd.WaterBarrier.get(p.getUniqueId())*0.015), p, le);
									
								}
		                	}

		    				if(l.getBlock().isPassable()) {
		                        l.getBlock().setType(Material.WATER,false);
		                        l.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	l.getBlock().setType(Material.VOID_AIR);
					                	l.getBlock().setType(m);
			                            l.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
					                }
					            }, 80); 
		    				}
		    				if(l1.getBlock().isPassable()) {
		                        l1.getBlock().setType(Material.WATER,false);
		                        l1.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	l1.getBlock().setType(Material.VOID_AIR);
					                	l1.getBlock().setType(m1);
			                            l1.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
					                }
					            }, 80); 
		    				}
		    				if(l2.getBlock().isPassable()) {
		                        l2.getBlock().setType(Material.WATER,false);
		                        l2.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	l2.getBlock().setType(Material.VOID_AIR);
					                	l2.getBlock().setType(m2);
			                            l2.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
					                }
					            }, 80); 
		    				}
		    				if(l3.getBlock().isPassable()) {
		                        l3.getBlock().setType(Material.WATER,false);
		                        l3.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	l3.getBlock().setType(Material.VOID_AIR);
					                	l3.getBlock().setType(m3);
			                            l3.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
					                }
					            }, 80); 
		    				}
		    				if(l4.getBlock().isPassable()) {
		                        l4.getBlock().setType(Material.WATER,false);
		                        l4.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	l4.getBlock().setType(Material.VOID_AIR);
					                	l4.getBlock().setType(m4);
			                            l4.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
					                }
					            }, 80); 
		    				}
		    				if(l5.getBlock().isPassable()) {
		                        l5.getBlock().setType(Material.WATER,false);
		                        l5.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	l5.getBlock().setType(Material.VOID_AIR);
					                	l5.getBlock().setType(m5);
			                            l5.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
					                }
					            }, 80); 
		    				}
		    				if(l6.getBlock().isPassable()) {
		                        l6.getBlock().setType(Material.WATER,false);
		                        l6.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	l6.getBlock().setType(Material.VOID_AIR);
					                	l6.getBlock().setType(m6);
			                            l6.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
					                }
					            }, 80); 
		    				}
		    				if(l7.getBlock().isPassable()) {
		                        l7.getBlock().setType(Material.WATER,false);
		                        l7.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	l7.getBlock().setType(Material.VOID_AIR);
					                	l7.getBlock().setType(m7);
			                            l7.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
					                }
					            }, 80); 
		    				}
		    				if(l8.getBlock().isPassable()) {
		                        l8.getBlock().setType(Material.WATER,false);
		                        l8.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	l8.getBlock().setType(Material.VOID_AIR);
					                	l8.getBlock().setType(m8);
			                            l8.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
					                }
					            }, 80); 
		    				}
						});
				bd.execute();
			}
		}
	}
	


	
	
	public void ShieldSmite(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 20 && trdv.containsKey(p.getUniqueId())) {
			if(p.isBlocking() && p.isSneaking() && !p.isRiptiding())
			{
				ev.setCancelled(true);

            	if(trdvt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(trdvt.get(p.getUniqueId()));
            		trdvt.remove(p.getUniqueId());
            	}
				trdv.remove(p.getUniqueId());
            	p.setCooldown(CAREFUL, 3);
				p.swingMainHand();
				p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_HIT_GROUND, 1, 0);
				p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_RETURN, 1, 0);
				p.playSound(p.getLocation(), Sound.BLOCK_METAL_HIT, 1, 0);
        		p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(),300,5,2,5, Material.PRISMARINE.createBlockData());
        		p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(),300,5,2,5, Material.DARK_PRISMARINE.createBlockData());
        		p.getWorld().spawnParticle(Particle.ITEM, p.getLocation(),300,5,2,5, new ItemStack(Material.TRIDENT));

	            Location tl = gettargetblock(p,3).clone();

        		p.getWorld().spawnParticle(Particle.SPLASH, tl,50,1,1,1);
        		p.getWorld().spawnParticle(Particle.ENCHANTED_HIT, tl,50,1,1,1);
        		
            	for(Entity e: p.getNearbyEntities(5,5,5)) {
					if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
					{
                		if (e instanceof Player) 
						{
							
							Player p1 = (Player) e;
							if(Party.hasParty(p) && Party.hasParty(p1))	{
							if(Party.getParty(p).equals(Party.getParty(p1)))
								{
									continue;
								}
							}
						}
						LivingEntity le = (LivingEntity)e;
	        			atk1(0.65*(1+fsd.WaterBarrier.get(p.getUniqueId())*0.045), p, le);
						le.teleport(tl);
						Holding.superholding(p, le, 5l);
					}
            	}
				
            	
            	
            	

			}
		}
	}

	
	
	public void WetSwing(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec =6*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 20 && fsd.WetSwing.getOrDefault(p.getUniqueId(),0)>=1) {
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT && shield.containsKey(p.getName()))
			{
				ev.setCancelled(true);
				p.getInventory().getItemInOffHand().setType(Material.SHIELD);
                p.getInventory().getItemInOffHand().setItemMeta(shield.get(p.getName()).getItemMeta());
                shield.remove(p.getName());
                Bukkit.getScheduler().cancelTask(shieldt.get(p.getName()));
			}
			else if(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT && shield.containsKey(p.getName()))
			{
				ev.setCancelled(true);
				p.getInventory().getItemInMainHand().setType(Material.SHIELD);
                p.getInventory().getItemInMainHand().setItemMeta(shield.get(p.getName()).getItemMeta());
                shield.remove(p.getName());
                Bukkit.getScheduler().cancelTask(shieldt.get(p.getName()));
			}
			if((p.getInventory().getItemInMainHand().getType()==Material.TRIDENT || p.getInventory().getItemInMainHand().getType()==Material.SHIELD) && p.isSneaking()&& !p.isRiptiding() && !p.isBlocking())
			{
				
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("해풍참")
						.ename("WetSwing")
						.slot(1)
						.hm(prcooldown)
						.skillUse(() -> {
		                	if(bckwsht.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(bckwsht.get(p.getUniqueId()));
		                		bckwsht.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							bckwsh.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						bckwsh.remove(p.getUniqueId());
		    	                }
		    	            }, 40); 
		                	bckwsht.put(p.getUniqueId(), task);
		                	
		                    ArrayList<Location> line = new ArrayList<Location>();
		                    ArrayList<Location> fill = new ArrayList<Location>();
		                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
		                    AtomicInteger j = new AtomicInteger();
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.6f, 0.5f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_SHOOT, 0.6f, 0.5f);
			            	p.setCooldown(CAREFUL, 3);
							p.swingMainHand();
		                    for(double an = Math.PI/2.5; an>-Math.PI/2.5; an-=Math.PI/90) {
		                    	Location pl = p.getEyeLocation();
		                    	pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(6.5));
		                    	line.add(pl);
		                    }
		                    line.forEach(l -> {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
						        		p.getWorld().spawnParticle(Particle.SPLASH, l,5,0.1,0.1,0.1,0);
						        		p.getWorld().spawnParticle(Particle.FALLING_WATER, l,5,0.1,0.1,0.1,0);
						        		p.getWorld().spawnParticle(Particle.BUBBLE, l,5,0.1,0.1,0.1,0);
					                    for(double i = 0; i<6.5;i+=0.1) {
					                    	Location pl = p.getLocation();
					                    	Vector v = l.clone().toVector().subtract(pl.toVector()).normalize();
					                    	fill.add(pl.add(v.multiply(i)));
					                    }			          
					                }
								}, j.incrementAndGet()/900); 
		                    	
		                    });
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                    fill.forEach(l ->{
				                    	for(Entity e: l.getWorld().getNearbyEntities(l,1,2,1)) {
					    					if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
					    					{
					                    		if (e instanceof Player) 
					    						{
					    							
					    							Player p1 = (Player) e;
					    							if(Party.hasParty(p) && Party.hasParty(p1))	{
					    							if(Party.getParty(p).equals(Party.getParty(p1)))
					    								{
					    									continue;
					    								}
					    							}
					    						}
												LivingEntity le = (LivingEntity)e;
												les.add(le);
											}
				                    	}
				                    });
				                }
							}, j.incrementAndGet()/900); 

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									for (LivingEntity le : les)
									{
			    	        			atk1(0.935*(1+fsd.WetSwing.get(p.getUniqueId())*0.0433), p, le);
												
									}
				                    
				                }
							}, j.incrementAndGet()/900); 
						});
				bd.execute();
				}
		}
	}
	

	
	
	public void Backwash(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 20 && bckwsh.containsKey(p.getUniqueId())) {
			if((p.getInventory().getItemInMainHand().getType()==Material.TRIDENT || p.getInventory().getItemInMainHand().getType()==Material.SHIELD) && p.isSneaking()&& !p.isRiptiding() && !p.isBlocking())
			{
				ev.setCancelled(true);
                
            	if(bckwsht.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(bckwsht.get(p.getUniqueId()));
            		bckwsht.remove(p.getUniqueId());
            	}
				bckwsh.remove(p.getUniqueId());
				


            	if(clvet.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(clvet.get(p.getUniqueId()));
            		clvet.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							clve.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 5); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						clve.remove(p.getUniqueId());
	                }
	            }, 30); 
            	clvet.put(p.getUniqueId(), task);


				ArrayList<Location> s1 = new ArrayList<Location>();
				ArrayList<Location> s2 = new ArrayList<Location>();
                for(double angle= 0; angle<Math.PI*2; angle += Math.PI/90) {
                		Location pl = p.getEyeLocation().clone();
                		s1.add(pl.add(p.getLocation().getDirection().normalize().rotateAroundY(angle).multiply(7)));   
                }
                for(double an = Math.PI/2.5; an>-Math.PI/2.5; an-=Math.PI/180) {
                		Location pl = p.getEyeLocation().clone();
                		s2.add(pl.add(p.getLocation().getDirection().normalize().rotateAroundY(an).multiply(7)));   
                }

	            Location tl = gettargetblock(p,3).clone();
					
				p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 0);
                p.playSound(p.getLocation(), Sound.ENTITY_HOSTILE_SPLASH, 1.0f, 0f);
				p.playSound(p.getLocation(), Sound.ENTITY_SHULKER_TELEPORT, 1, 2);
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.6f, 2f);
                p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_SHOOT, 0.6f, 1.5f);
                s1.forEach(l ->{
					p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5, 2,2,2);
					p.teleport(p.getLocation().clone().setDirection(l.clone().toVector().subtract(p.getLocation().clone().toVector())));
                });
				for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),8, 5, 8)) {
					if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
					{
                		if (e instanceof Player) 
						{
							Player p1 = (Player) e;
							if(Party.hasParty(p) && Party.hasParty(p1))	{
							if(Party.getParty(p).equals(Party.getParty(p1)))
								{
									continue;
								}
							}
						}
						LivingEntity le = (LivingEntity)e;
	        			atk1(0.535*(1+fsd.WetSwing.get(p.getUniqueId())*0.0433), p, le);
						le.teleport(tl);
						Holding.holding(p, le, 5l);
					}
				}
					
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 0);
		                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.6f, 0.5f);
		                p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_SHOOT, 0.6f, 0.7f);
	                    s2.forEach(l ->{
							p.getWorld().spawnParticle(Particle.NAUTILUS, l, 1, 1,1,1,0);
							p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 1, 1,1,1,0.1);
				                    
	                    });
	    				for(Entity e : tl.getWorld().getNearbyEntities(tl,5, 5, 5)) {
	    					if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
	    					{
	                    		if (e instanceof Player) 
	    						{
	    							Player p1 = (Player) e;
	    							if(Party.hasParty(p) && Party.hasParty(p1))	{
	    							if(Party.getParty(p).equals(Party.getParty(p1)))
	    								{
	    									continue;
	    								}
	    							}
	    						}
	    						LivingEntity le = (LivingEntity)e;
	    	        			atk1(0.535*(1+fsd.WetSwing.get(p.getUniqueId())*0.0433), p, le);
	    					}
	    				}
	    					
	                }
	            }, 4);
			}
		}
	}

	
	
	public void Cleave(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 20&& clve.containsKey(p.getUniqueId())) {
			if((p.getInventory().getItemInMainHand().getType()==Material.TRIDENT || p.getInventory().getItemInMainHand().getType()==Material.SHIELD) && p.isSneaking()&& !p.isRiptiding() && !p.isBlocking())
			{
				ev.setCancelled(true);

            	if(clvet.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(clvet.get(p.getUniqueId()));
            		clvet.remove(p.getUniqueId());
            	}
				clve.remove(p.getUniqueId());

	            Location tl = gettargetblock(p,4).clone();

				p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 0.5f, 0);
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.5f, 0f);
                p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_SHOOT, 0.6f, 0.1f);
                p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_BIG_FALL, 0.5f, 0f);
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 0.5f, 0f);
                p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 0.5f, 0f);
				ArrayList<Location> lls = new ArrayList<Location>();
				p.setGliding(true);
                for(int i = 0; i<10; i++) {
                	lls.add(p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(i)));
                }
                

				ArrayList<Location> sight = new ArrayList<Location>();
                for(double i=Math.PI/180; i > -Math.PI; i -= Math.PI/180) {
                	Location eye = p.getLocation().clone();
                	eye.setDirection(eye.clone().getDirection().multiply(-1));
                	Vector eyev = p.getLocation().getDirection().clone().rotateAroundY(Math.PI/2);
                	Location a = eye.clone().setDirection(eye.getDirection().rotateAroundAxis(eyev, i).normalize());
                	sight.add(a);
                }
                sight.forEach(l -> {
						p.teleport(l);
                });

                lls.forEach(l -> {
                	l.getWorld().spawnParticle(Particle.ENCHANTED_HIT, l, 100,0.2,0.2,0.2,0.1);
                	l.getWorld().spawnParticle(Particle.CRIT, l, 100,0.1,0.1,0.1,0.1);
                	l.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 100,0.3,0.3,0.3);
                	l.getWorld().spawnParticle(Particle.FIREWORK, l, 20,0.2,0.2,0.2,0.1);
                	l.getWorld().spawnParticle(Particle.WHITE_ASH, l, 20,1,0,1);
                	l.getWorld().spawnParticle(Particle.FALLING_WATER, l, 2000,6,1,6);
                });

            	p.setCooldown(CAREFUL, 3);
				p.swingMainHand();
				for (Entity e : p.getWorld().getNearbyEntities(tl, 7, 6, 7))
				{
            		if (e instanceof Player) 
					{
						
						Player p1 = (Player) e;
						if(Party.hasParty(p) && Party.hasParty(p1))	{
						if(Party.getParty(p).equals(Party.getParty(p1)))
							{
								continue;
							}
						}
					}
					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
						LivingEntity le = (LivingEntity)e;
	        			atk1(1.235*(1+fsd.WetSwing.get(p.getUniqueId())*0.123), p, le);
	        			le.teleport(tl);
						Holding.holding(p, le, 5l);
					}
					
				}
			}
		}
	}
	
	
	
	public void RipCurrent(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec =2*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	    
		
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 20 && fsd.RipCurrent.getOrDefault(p.getUniqueId(),0)>=1) {
			if(p.isRiptiding())
			{
				ev.setCancelled(true);

				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("역조")
						.ename("RipCurrent")
						.slot(2)
						.hm(jmcooldown)
						.skillUse(() -> {
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.5f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_HURT_WATER, 1.0f, 1.5f);
	                    	for(Entity e: p.getWorld().getNearbyEntities(p.getLocation(),3,3,3)) {

	                    		if (e instanceof Player) 
								{
									
									Player p1 = (Player) e;
									if(Party.hasParty(p) && Party.hasParty(p1))	{
									if(Party.getParty(p).equals(Party.getParty(p1)))
										{
											continue;
										}
									}
								}
								if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
								{
									LivingEntity le = (LivingEntity)e;
									
									le.teleport(p.getLocation().add(p.getVelocity().normalize().multiply(5)));
									if(Proficiency.getpro(p)<1) {
										break;
									}
									Holding.superholding(p, le, 15l);
								}
	                    	}
						});
				bd.execute();
				}
		}
	}

	
	
	@SuppressWarnings("deprecation")
	public void Javelin(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =8*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
        
		
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 20 && fsd.Javelin.getOrDefault(p.getUniqueId(),0)>=1&&!p.hasCooldown(CAREFUL)) {
			if((p.getInventory().getItemInMainHand().getType()==Material.TRIDENT || p.getInventory().getItemInMainHand().getType()==Material.SHIELD)&&!p.isSneaking()&& !p.isOnGround() &&!p.isBlocking()) 
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("투창")
							.ename("Javelin")
							.slot(3)
							.hm(eccooldown)
							.skillUse(() -> {
				            	Trident firstarrow = p.launchProjectile(Trident.class);
			                    firstarrow.setPickupStatus(PickupStatus.ALLOWED);
			                    firstarrow.setMetadata("jav of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			                    firstarrow.setGlowing(true);
			                    firstarrow.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			                    firstarrow.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			                    if(Proficiency.getpro(p)>=1)
			                    {
				                    firstarrow.setMetadata("crisp of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			                    }
			                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
			                	p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_THROW, 1f, 0.1f);
			                	p.playSound(p.getLocation(), Sound.ENTITY_SQUID_HURT, 0.8f, 2f);
			                	ArrayList<Location> line = new ArrayList<Location>();
			                    for(double d = 0.1; d <= 13; d += 0.2) {
				                    Location pl = p.getEyeLocation().clone();
									pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
									line.add(pl);
			                    }
			                    for(Location l : line) {
			                    	p.getWorld().spawnParticle(Particle.DOLPHIN, l.add(0, -0.289, 0),5, 0.05,0.05,0.05,0);
			                    	p.getWorld().spawnParticle(Particle.SPLASH, l.add(0, -0.289, 0),5, 0.05,0.05,0.05,0);

			                    	for (Entity a : p.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5))
									{
										if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
										{
											if (a instanceof Player) 
											{
												Player p1 = (Player) a;
												if(Party.hasParty(p) && Party.hasParty(p1))	{
												if(Party.getParty(p).equals(Party.getParty(p1)))
													{
														continue;
													}
												}
											}
											LivingEntity le = (LivingEntity)a;
											les.add(le);										
										}
									}
			                    }
			                	for(LivingEntity le : les) {
				        			atk1(0.635*(1+fsd.Javelin.get(p.getUniqueId())*0.0453), p, le);
										
			                	}
		                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {

					                    firstarrow.remove();
					                    
					                }
					            }, Math.round(sec)*18); 
							                	
							});
					bd.execute();
				}
			}
		}
	}


	
	
	@SuppressWarnings("deprecation")
	public void Javelin(PlayerPickupArrowEvent ev) 
	{
		Player p = ev.getPlayer();
			if(ev.getArrow().hasMetadata("jav of "+p.getName())) {
					eccooldown.computeIfPresent(p.getName(), (k,v) -> v-4000);
					p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_RETURN, 1f, 2f);
					ev.getArrow().remove();
					ev.setCancelled(true);
			}
			else if (ev.getArrow().getShooter() != p && ev.getArrow().hasMetadata("fake")){
				ev.setCancelled(true);
			}
	}

	
	public void Crisp(ProjectileHitEvent d) 
	{
		if(d.getEntity() instanceof Trident) 
		{
			Trident t = (Trident)d.getEntity();
	        
			
			if(t.getShooter() instanceof Player) {
				Player p = (Player) t.getShooter();
				if(t.hasMetadata("crisp of "+p.getName())) {

                	if(trrnt.containsKey(p.getUniqueId())) {
                		Bukkit.getScheduler().cancelTask(trrnt.get(p.getUniqueId()));
                		trrnt.remove(p.getUniqueId());
                	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=2) {
								trrn.putIfAbsent(p.getUniqueId(), t.getLocation());
							}
		                }
		            }, 3); 

            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							trrn.remove(p.getUniqueId());
		                }
		            }, 35); 
                	trrnt.put(p.getUniqueId(), task);
                	
					
					t.removeMetadata("crisp of "+p.getName(), RMain.getInstance());
                    for(int i =0; i<6; i++) {
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
			                		t.getWorld().spawnParticle(Particle.BUBBLE, t.getLocation(), 20,1,1,1);
			                		t.getWorld().spawnParticle(Particle.SPLASH, t.getLocation(), 20,1,1,1);
			                		t.getWorld().spawnParticle(Particle.GUST, t.getLocation(), 10,1,1,1);
				                	p.playSound(t.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_INSIDE, 0.8f, 2f);
									for(Entity e: t.getWorld().getNearbyEntities(t.getLocation(), 2.5,2.5,2.5)) {

					            		if (e instanceof Player) 
										{
											Player p1 = (Player) e;
											if(Party.hasParty(p) && Party.hasParty(p1))	{
											if(Party.getParty(p).equals(Party.getParty(p1)))
												{
												continue;
												}
											}
										}
					            		if ((!(e == p))&& e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) 
										{
											LivingEntity le = (LivingEntity)e;
						        			atk1(0.4*(1+fsd.Javelin.get(p.getUniqueId())*0.0353), p, le);
											le.teleport(t.getLocation());
											Holding.holding(p, le, 5l);
										}
									}
				                }
	                	   }, i*3); 
					}
				}
			}
		}
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void Torrent(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 20&&trrn.containsKey(p.getUniqueId())) {
			if((p.getInventory().getItemInMainHand().getType()==Material.TRIDENT || p.getInventory().getItemInMainHand().getType()==Material.SHIELD)&&!p.isSneaking()&& !p.isOnGround() &&!p.isBlocking()) 
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					Vector pv = p.getEyeLocation().getDirection();
					p.teleport(trrn.get(p.getUniqueId()).setDirection(pv));
            		p.getWorld().spawnParticle(Particle.CRIT, p.getLocation(), 20,2,2,2);
            		p.getWorld().spawnParticle(Particle.SPLASH, p.getLocation(), 20,1,1,1);
            		p.getWorld().spawnParticle(Particle.ENCHANTED_HIT, p.getLocation(), 100,2,2,2);
                	p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_THROW, 0.8f, 2f);
                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 0.8f, 2f);
					for(Entity e: p.getWorld().getNearbyEntities(p.getLocation(), 3,3,3)) {

	            		if (e instanceof Player) 
						{
							Player p1 = (Player) e;
							if(Party.hasParty(p) && Party.hasParty(p1))	{
							if(Party.getParty(p).equals(Party.getParty(p1)))
								{
								continue;
								}
							}
						}
	            		if ((!(e == p))&& e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) 
						{
							LivingEntity le = (LivingEntity)e;
		        			atk1(1.1*(1+fsd.Javelin.get(p.getUniqueId())*0.0753), p, le);
							Holding.holding(p, le, 5l);
						}
					}
					
					
	            	if(trrnt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(trrnt.get(p.getUniqueId()));
	            		trrnt.remove(p.getUniqueId());
	            	}
					trrn.remove(p.getUniqueId());

					
					
					
				}
			}
		}
	}
	
	
	public void WaterSpear(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec =7*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 20 && fsd.WaterSpear.getOrDefault(p.getUniqueId(),0)>=1) {
			if(!p.isSneaking() && p.isBlocking()&& !p.isRiptiding())
			{
				ev.setCancelled(true);

				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("바다창술")
						.ename("WaterSpear")
						.slot(4)
						.hm(thcooldown)
						.skillUse(() -> {
		                	if(drllt.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(drllt.get(p.getUniqueId()));
		                		drllt.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							drll.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						drll.remove(p.getUniqueId());
		    	                }
		    	            }, 40); 
		                	drllt.put(p.getUniqueId(), task);
		                	

		                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
		                    for(int i =0; i<6; i++) {
			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
											p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 3, 1, false, false));
						                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 0.3f, 1.1f);
						                	p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_THROW, 0.6f, 1.4f);
						                	ArrayList<Location> line = new ArrayList<Location>();
						                    for(double d = 0.1; d <= 6.1; d += 0.2) {
							                    Location pl = p.getEyeLocation().clone();
												pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
												line.add(pl);
						                    }
						                    line.forEach(l ->{
						                    	p.getWorld().spawnParticle(Particle.DOLPHIN, l.add(0, -0.289, 0),5, 0.05,0.05,0.05,0);
						                    	p.getWorld().spawnParticle(Particle.SPLASH, l.add(0, -0.289, 0),5, 0.05,0.05,0.05,0);
						                    	p.getWorld().spawnParticle(Particle.CRIT, l.add(0, -0.289, 0),1, 0.05,0.05,0.05,0);

						                    	for (Entity a : p.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5))
												{
													if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
													{
														if (a instanceof Player) 
														{
															Player p1 = (Player) a;
															if(Party.hasParty(p) && Party.hasParty(p1))	{
															if(Party.getParty(p).equals(Party.getParty(p1)))
																{
																	continue;
																}
															}
														}
														LivingEntity le = (LivingEntity)a;
														les.add(le);										
													}
												}
						                    });
						                	for(LivingEntity le : les) {
							        			atk1(0.353*(1+fsd.WaterSpear.get(p.getUniqueId())*0.038), p, le);
													
						                	}
						                }
			                	   }, i*3); 
			            	}

						});
				bd.execute();
			
			}
		}
	}
	

	
	public void Diffraction(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 20&& drll.containsKey(p.getUniqueId())) {
			if(!p.isSneaking() && p.isBlocking()&& !p.isRiptiding())
			{
				ev.setCancelled(true);

            	if(drllt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(drllt.get(p.getUniqueId()));
            		drllt.remove(p.getUniqueId());
            	}
				drll.remove(p.getUniqueId());
				


            	if(fldt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(fldt.get(p.getUniqueId()));
            		fldt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							fld.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						fld.remove(p.getUniqueId());
	                }
	            }, 40); 
            	fldt.put(p.getUniqueId(), task);
            	

                Arrow firstarrow = p.launchProjectile(Arrow.class);
                firstarrow.setDamage(0);
                firstarrow.remove();
            	p.setSwimming(true);
            	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                    for(int i =0; i<4; i++) {
                    	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    			                @Override
    			                public void run() 
    			                {
    			                	p.teleport(p.getLocation());
    			                	p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_3, 0.6f, 0.9f);
    			                	p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_THROW, 0.7f, 0.8f);
    			                	p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1f, 2f);
    			                	
    			                	HashSet<Location> line = new HashSet<Location>();
    			                	AtomicDouble j = new AtomicDouble(1.91);
    			                    for(double d = 1; d <= 7.5; d += 0.5) {
    				                    Location pl = p.getEyeLocation().clone();
    									pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
    									line.add(pl);
    			                    }
    			                    line.forEach(l ->{
    			                    	p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l.clone().add(0, -0.35, 0),15, j.addAndGet(0.027),j.get(),j.get());
    			                    	p.getWorld().spawnParticle(Particle.SPLASH, l.clone().add(0, -0.35, 0),80, j.addAndGet(0.027),j.get(),j.get());

    			                    	for (Entity a : p.getWorld().getNearbyEntities(l, 3, 3, 3))
    									{
    										if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
    										{
    											if (a instanceof Player) 
    											{
    												
    												Player p1 = (Player) a;
    												if(Party.hasParty(p) && Party.hasParty(p1))	{
    												if(Party.getParty(p).equals(Party.getParty(p1)))
    													{
    														continue;
    													}
    												}
    											}
    											LivingEntity le = (LivingEntity)a;
    											les.add(le);
    											if(l.getBlock().isPassable() && le.hasAI()) {
    												le.teleport(l);
    											}
    											Holding.holding(p, le, 7l);
    										}
    									}
    			                    });
    			                	les.forEach(le ->{
					        			atk1(0.353*(1+fsd.WaterSpear.get(p.getUniqueId())*0.04), p, le);
    										
    			                	});
    			                }
                    	   }, i*2); 
                    }
			}
		}
	}
	
	
	final private Location Flood(final Location tl, Integer j, Player p) {
		

		final World aw = tl.getWorld();
    	Location tal = tl.clone().add(0, j*0.2, 0);

    	aw.spawnParticle(Particle.DOLPHIN, tal, 200, 1,1,1,0);
    	aw.spawnParticle(Particle.NAUTILUS, tal, 100, 0.5,0.5,0.5,0);
		
    	Location al = tl.clone().add(tl.getDirection().normalize().rotateAroundY(j*0.25).multiply(3)).add(0, j*0.2, 0);
		p.playSound(al, Sound.ENTITY_DROWNED_SHOOT, 0.3f, 1.6f);
		p.playSound(al, Sound.ENTITY_DOLPHIN_SPLASH, 0.3f, 1.6f);

		aw.spawnParticle(Particle.SPLASH, al, 300, 2,2,2,0);
		aw.spawnParticle(Particle.SWEEP_ATTACK, al, 10, 0.5,0.5,0.5,0);
		
		return al;
    		
	}

	

	
	public void Flood(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 20 && fld.containsKey(p.getUniqueId())) {
			if(!p.isSneaking() && p.isBlocking()&& !p.isRiptiding())
			{
				ev.setCancelled(true);
				
            	if(fldt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(fldt.get(p.getUniqueId()));
            		fldt.remove(p.getUniqueId());
            	}
				fld.remove(p.getUniqueId());
	            Location tl = gettargetblock(p,3).clone();

            	p.playSound(p.getLocation(), Sound.ENTITY_BOAT_PADDLE_WATER, 1f, 0f);
            	p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_SWIM, 1f, 0f);
            	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1f, 0);
            	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 1f, 0);
            	p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1f, 0);
            	p.setCooldown(CAREFUL, 3);
				p.swingMainHand();

				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 3, false, false));
				
                AtomicInteger j = new AtomicInteger();	
                
				for(int i = 0; i <30; i++) {
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	final Location al = Flood(tl.clone(),j.getAndIncrement(), p);
							for(Entity e : tl.getWorld().getNearbyEntities(tl,4.5, 6, 4.5)) {
								if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
									LivingEntity le = (LivingEntity)e;
		                    		if (e instanceof Player) 
									{
										
										Player p1 = (Player) e;
										if(Party.hasParty(p) && Party.hasParty(p1))	{
										if(Party.getParty(p).equals(Party.getParty(p1)))
											{
												continue;
											}
										}
									}
		                    		atk1(0.05*(1+fsd.WaterSpear.get(p.getUniqueId())*0.022), p, le);
		                    		
									le.teleport(al);
										
								}
							}
		                }
		            }, i); 	                    	
                }
			
			}
		}
	}

	public void OceanCharge(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =8*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
        
		
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 20 && fsd.OceanCharge.getOrDefault(p.getUniqueId(),0)>=1&&!p.hasCooldown(CAREFUL)) {
			if((p.getInventory().getItemInMainHand().getType()==Material.TRIDENT || p.getInventory().getItemInMainHand().getType()==Material.SHIELD)&&p.isSneaking() &&!p.isBlocking()) 
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK))
				{
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("꿰뚫기")
							.ename("OceanCharge")
							.slot(5)
							.hm(pncooldown)
							.skillUse(() -> {

								ArrayList<Location> line = new ArrayList<Location>();
			                    p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 0.3f, 0.5f);
			                    p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_2, 0.3f, 0.5f);
			                    p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_3, 0.3f, 0.5f);
			                    
			                	if(shldsmt.containsKey(p.getUniqueId())) {
			                		Bukkit.getScheduler().cancelTask(shldsmt.get(p.getUniqueId()));
			                		shldsmt.remove(p.getUniqueId());
			                	}

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										if(Proficiency.getpro(p)>=1) {
											shldsm.putIfAbsent(p.getUniqueId(), 0);
										}
					                }
					            }, 3); 

			            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	shldsm.remove(p.getUniqueId());
					                }
					            }, 30); 
			            		shldsmt.put(p.getUniqueId(), task);
			                	
			                    AtomicInteger j = new AtomicInteger(0);
			                    for(double d = 0.1; d <= 3; d += 0.1) {
				                    Location pl = p.getEyeLocation();
									pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
									if(!pl.getBlock().isPassable()) {
										break;
									}
									line.add(pl);
			                    }
		                    	line.forEach(i -> {
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
				         					p.getWorld().spawnParticle(Particle.DRIPPING_WATER,i, 2,1,1,1);
				         					p.getWorld().spawnParticle(Particle.BUBBLE,i, 2,1,1,1);
				         					if(i.getBlock().isPassable()) {
					                    	p.teleport(i);
				         					}
				        					p.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 5, 255, false, false));
				        					p.setGliding(true);
							            }
			                    	}, j.incrementAndGet()/17); 
								}); 
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	Location pel = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(1.8));
					                	
					                	for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 2, 2, 2))
										{
				                    		if (e instanceof Player) 
											{
												
												Player p1 = (Player) e;
												if(Party.hasParty(p) && Party.hasParty(p1))	{
												if(Party.getParty(p).equals(Party.getParty(p1)))
													{
														continue;
													}
												}
											}
				                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
											{
												LivingEntity le = (LivingEntity)e;
												atk1(0.6*(1+fsd.OceanCharge.get(p.getUniqueId())*0.055), p, le);
												le.teleport(pel.clone());
												Holding.superholding(p, le, 20l);
												Holding.holding(null, p, 20l);
											}
										}
			         					p.getWorld().spawnParticle(Particle.BUBBLE_POP,50, 2,1,1,1);
			         					p.getWorld().spawnParticle(Particle.SPLASH,50, 2,1,1,1);
					                    p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_SHOOT, 0.35f, 2.0f);
					                    p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_HIT, 0.5f, 0.0f);
					                }
			                    }, j.incrementAndGet()/17); 
							});
					bd.execute();
					
				}
			}
		}
	}

	
	public void TridentExplosion(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		
			if(ClassData.pc.get(p.getUniqueId()) == 20 && shldsm.containsKey(p.getUniqueId())) {
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK))
				{
					if((p.getInventory().getItemInMainHand().getType()==Material.TRIDENT || p.getInventory().getItemInMainHand().getType()==Material.SHIELD) && (p.isSneaking()))
					{
		            	if(shldsmt.containsKey(p.getUniqueId())) {
		            		Bukkit.getScheduler().cancelTask(shldsmt.get(p.getUniqueId()));
		            		shldsmt.remove(p.getUniqueId());
		            	}
						shldsm.remove(p.getUniqueId());
						


		            	if(implet.containsKey(p.getUniqueId())) {
		            		Bukkit.getScheduler().cancelTask(implet.get(p.getUniqueId()));
		            		implet.remove(p.getUniqueId());
		            	}

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								if(Proficiency.getpro(p)>=2) {
									imple.putIfAbsent(p.getUniqueId(), 0);
								}
			                }
			            }, 3); 

		        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								imple.remove(p.getUniqueId());
			                }
			            }, 40); 
		            	implet.put(p.getUniqueId(), task);
		            	

		            	p.setCooldown(CAREFUL, 3);
						p.swingOffHand();
	                	Location pel = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(1.8));
	                	
	                	for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 3, 3, 3))
						{
	                		if (e instanceof Player) 
							{
								
								Player p1 = (Player) e;
								if(Party.hasParty(p) && Party.hasParty(p1))	{
								if(Party.getParty(p).equals(Party.getParty(p1)))
									{
										continue;
									}
								}
							}
	                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
							{
								LivingEntity le = (LivingEntity)e;
								atk1(0.9*(1+fsd.OceanCharge.get(p.getUniqueId())*0.065), p, le);
							}
						}
	 					p.getWorld().spawnParticle(Particle.BUBBLE_POP, pel,50, 1,1,1,1);
	 					p.getWorld().spawnParticle(Particle.SPLASH,pel,50, 1,1,1,1);
	 					p.getWorld().spawnParticle(Particle.BUBBLE_POP, pel,50, 1);
	 					p.getWorld().spawnParticle(Particle.SPLASH,pel,50, 1);
	                    p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_CONVERTED_TO_DROWNED, 0.35f, 2.0f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_BREEZE_WIND_BURST, 0.5f, 0.0f);
					}
					
				}
			}
		
	}
	

	public void Impale(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		
			if(ClassData.pc.get(p.getUniqueId()) == 20 && imple.containsKey(p.getUniqueId())) {
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK))
				{
					if((p.getInventory().getItemInMainHand().getType()==Material.TRIDENT || p.getInventory().getItemInMainHand().getType()==Material.SHIELD) && (p.isSneaking()))
					{
		            	if(implet.containsKey(p.getUniqueId())) {
		            		Bukkit.getScheduler().cancelTask(implet.get(p.getUniqueId()));
		            		implet.remove(p.getUniqueId());
		            	}
						imple.remove(p.getUniqueId());

	                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();

						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_IRON, 0.8f, 0f);
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 5, 255, false, false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 5, 255, false, false));
		            	Holding.invur(p, 10l);
						
						
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_SHOOT, 0.5f, 0.9f);
			                	p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_THROW,0.5f, 0.6f);
			                	p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_HIT, 0.3f, 0.8f);
			                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.4f, 0.8f);
				            	p.setCooldown(CAREFUL, 3);
								p.swingOffHand();
			                	
			                	ArrayList<Location> line = new ArrayList<Location>();
			                    for(double dou = 0.1; dou <= 4.1; dou += 0.2) {
				                    Location pl = p.getEyeLocation().clone();
									pl.add(p.getEyeLocation().getDirection().normalize().multiply(dou));
									line.add(pl);
			                    }
			                    line.forEach(l ->{
			                    	p.getWorld().spawnParticle(Particle.DRIPPING_DRIPSTONE_WATER, l.add(0, -0.289, 0),5, 0.05,0.05,0.05,0);
			                    	p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l.add(0, -0.289, 0),5, 0.5,0.5,0.5,0);
			                    	p.getWorld().spawnParticle(Particle.CRIT, l.add(0, -0.289, 0),3);

			                    	for (Entity a : p.getWorld().getNearbyEntities(l, 3, 3, 3))
									{
										if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
										{
											if (a instanceof Player) 
											{
												Player p1 = (Player) a;
												if(Party.hasParty(p) && Party.hasParty(p1))	{
												if(Party.getParty(p).equals(Party.getParty(p1)))
													{
														continue;
													}
												}
											}
											LivingEntity le = (LivingEntity)a;
											les.add(le);
											if(l.getBlock().isPassable()) {
												le.teleport(l);
											}
										}
									}
			                    });
			                	for(LivingEntity le : les) {
			            			atk1(1.1*(1+fsd.OceanCharge.get(p.getUniqueId())*0.0834), p, le);
					            	Holding.superholding(p, le, 10l);
										
			                	}
			                }
			            }, 5); 
					}
					
				}
			}
		
	}
	
	
	
	final private ArrayList<Location> ult(Player p, Integer in){
		ArrayList<Location> s = new ArrayList<Location>();
		if(in == 1) {
            for(double angle= -Math.PI/5; angle<Math.PI/5; angle += Math.PI/90) {
            	for(double dou = 0.1; dou <13.1; dou+=0.2) {
            		Location pl = p.getLocation().clone().add(0, -0.5, 0);
            		s.add(pl.add(p.getLocation().getDirection().normalize().rotateAroundY(angle).multiply(dou)));   
            	}
            }
		}
		else {
            for(double angle= Math.PI/5; angle>-Math.PI/5; angle -= Math.PI/90) {
            	for(double dou = 0.1; dou <13.1; dou+=0.2) {
            		Location pl = p.getLocation().clone().add(0, -0.5, 0);
            		s.add(pl.add(p.getLocation().getDirection().normalize().rotateAroundY(angle).multiply(dou)));   
            	}
            }
		}
		return s;
	}
	

	
	public void ULT(PlayerItemHeldEvent ev)
    {
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		ItemStack is = p.getInventory().getItemInMainHand();
		
		if(ClassData.pc.get(p.getUniqueId()) == 20 && ev.getNewSlot()==3 && ((is.getType()==Material.TRIDENT)||(is.getType()==Material.SHIELD))&& p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
				ev.setCancelled(true);
				p.setCooldown(CAREFUL, 3);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(55/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
						.kname("해일")
						.ename("Grand Waves")
						.slot(6)
						.hm(aultcooldown)
						.skillUse(() -> {

		                    p.playSound(p.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, 1.0f, 2f);
							Holding.invur(p, 100l);
							ArrayList<Location> s1 = ult(p,1);
							ArrayList<Location> s2 = ult(p,2);
		                    AtomicInteger j = new AtomicInteger();	
		                    AtomicInteger k = new AtomicInteger();	
		                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
		                    
							for(int i = 0; i <2; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
						            	p.setCooldown(CAREFUL, 3);
										p.swingMainHand();
										p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 1, 255, false, false));
										p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 0.5f, 0);
					                    p.playSound(p.getLocation(), Sound.ENTITY_HOSTILE_SPLASH, 0.5f, 0f);
										p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 0.5f, 2);
					                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.5f, 1.6f);
										p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_SHOOT, 0.5f, 1.67f);
					                    s1.forEach(l ->{
					                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
													p.getWorld().spawnParticle(Particle.FALLING_WATER, l, 2, 1,1,1,0);
													p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 1, 1,1,1,0.1);
														for(Entity e : p.getWorld().getNearbyEntities(l,2, 2, 2)) {
															if(p!=e && e instanceof LivingEntity) {
																LivingEntity le = (LivingEntity)e;
																les.add(le);
															}
														}
								                    
								                }
								            }, j.incrementAndGet()/4000);  
					                    });
				                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
							                    for(LivingEntity le: les) {
							            			atk1(2.5, p, le);
																		                    	
							                    }
							                    
							                }
							            }, j.incrementAndGet()/4000);  
					                }
					            }, i*8);
			                    j.set(0);
		                    }
							for(int i = 0; i <2; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
						            	p.setCooldown(CAREFUL, 3);
										p.swingOffHand();
										p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 1, 255, false, false));
										p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 0.5f, 0);
					                    p.playSound(p.getLocation(), Sound.ENTITY_HOSTILE_SPLASH, 0.5f, 0f);
										p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 0.5f, 2);
					                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.5f, 0.38f);
										p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_SHOOT, 0.5f, 0.56f);
					                    s2.forEach(l ->{
					                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
													p.getWorld().spawnParticle(Particle.FALLING_WATER, l, 2, 1,1,1,0);
													p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 1, 1,1,1,0.1);
														for(Entity e : p.getWorld().getNearbyEntities(l,2, 2, 2)) {
															if(p!=e && e instanceof LivingEntity) {
																LivingEntity le = (LivingEntity)e;
																les.add(le);
															}
														}
								                    
								                }
								            }, k.incrementAndGet()/4000);  
					                    });
				                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
							                    for(LivingEntity le: les) {
							            			atk1(2.5, p, le);
																		                    	
							                    }
							                    
							                }
							            }, k.incrementAndGet()/4000);  
					                }
					            }, i*8 +4);
			                    k.set(0);
		                    }
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
					            	p.setCooldown(CAREFUL, 3);
									p.swingMainHand();
									p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 10, 255, false, false));
									p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_THROW, 0.5f, 0.2f);
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.6f, 2f);
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.6f, 0f);
									p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_SHOOT, 0.7f, 0.16f);
									p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_SHOOT, 0.7f, 1.8f);
			                    	for(double dou = 0.1; dou <13.1; dou+=0.2) {
			                    		Location l = p.getLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(dou));
				                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
												p.getWorld().spawnParticle(Particle.DRIPPING_WATER, l, 50, 1,1,1,0);
												p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 1, 1,1,1,0.1);
													for(Entity e : p.getWorld().getNearbyEntities(l,3.5, 3.5, 3.5)) {
														if(p!=e && e instanceof LivingEntity) {
															LivingEntity le = (LivingEntity)e;
															les.add(le);
			    											if(l.getBlock().isPassable() && le.hasAI()) {
			    												le.teleport(l);
			    											}
			    											Holding.holding(p, le, 40l);
														}
													}
							                    
							                }
							            }, j.incrementAndGet()/4000);  
			                    	}
			                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
						                    for(LivingEntity le: les) {
						            			atk1(9.8, p, le);
																	                    	
						                    }
						                    
						                }
						            }, k.incrementAndGet()/4000);  
									
				                }
				            }, 22);
						});
				bd.execute();
			}			
    }


	public void ULT2(PlayerItemHeldEvent ev)
	{
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}

		ItemStack is = p.getInventory().getItemInMainHand();
		
		if(ClassData.pc.get(p.getUniqueId()) == 20 && ((is.getType()==Material.TRIDENT)||(is.getType()==Material.SHIELD))&& p.isSneaking() && ev.getNewSlot()==4 && Proficiency.getpro(p) >=2)
			{
            Location tl = gettargetblock(p,5).clone();

				ev.setCancelled(true);
				p.setCooldown(CAREFUL, 3);
				
				double sec =63*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d);

				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("바다의분노")
						.ename("Wrath Of Sea")
						.slot(7)
						.hm(ault2cooldown)
						.skillUse(() -> {

							Holding.invur(p, 120l);
		                    
							ArrayList<Location> lhs = new ArrayList<>();
							
							lhs.add(tl.clone().add(5, 3, 12));
							lhs.add(tl.clone().add(-10, 8, 7));
							lhs.add(tl.clone().add(10, 2, -13));
							lhs.add(tl.clone().add(-8, 6, 8));
							lhs.add(tl.clone().add(-3, 1, -9));
							lhs.add(tl.clone().add(11, 7, -8));
							lhs.add(tl.clone().add(-9, 2, 10));
							lhs.add(tl.clone().add(4, 10, 4));

		                    for(int i=0; i<12; i++) {
		                    	Random random=new Random();
		                    	double number = random.nextDouble() * 11 * (random.nextBoolean() ? -1 : 1);
		                    	double number2 = random.nextDouble() * 11 * (random.nextBoolean() ? -1 : 1);
		                    	double number3 = random.nextDouble() * 11;
		                    	lhs.add(tl.clone().add(number, number3, number2));
		                    }
							
							Trident ft = tl.getWorld().spawn(tl.clone().add(0, 1, 0), Trident.class);
							ft.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							ft.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							ft.setDamage(0);
							ft.setShooter(p);
							ft.setPickupStatus(PickupStatus.DISALLOWED);
		                    AtomicInteger j = new AtomicInteger();	

		                    p.playSound(tl, Sound.AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE, 1.0f, 2f);
		                    p.playSound(tl, Sound.ITEM_TRIDENT_HIT_GROUND, 0.8f, 0f);
		                    p.playSound(tl, Sound.ITEM_TRIDENT_RETURN, 0.8f, 0f);
		                    lhs.forEach(l -> {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	Vector pv = tl.clone().toVector().subtract(l.clone().toVector());
					                	l.setDirection(pv);
					                	p.teleport(l);
					                	for(int i = 0; i <l.distance(tl); i++) {
						                	l.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l.clone().add(pv.clone().normalize().multiply(i)), 20,1,1,1);
						                	l.getWorld().spawnParticle(Particle.DRIPPING_WATER, l.clone().add(pv.clone().normalize().multiply(i)), 100,1,1,1);
						                	l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(pv.clone().normalize().multiply(i)), 100,1,1,1,0);
						                	l.getWorld().spawnParticle(Particle.ENCHANTED_HIT, l.clone().add(pv.clone().normalize().multiply(i)), 100,1,1,1,0);
					                	}
					                    p.playSound(l, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.4f, 2f);
					                    p.playSound(l, Sound.ITEM_TRIDENT_HIT_GROUND, 0.5f, 0f);
					                    p.playSound(l, Sound.ITEM_TRIDENT_RIPTIDE_1, 0.6f, 0.3f);
					                    p.playSound(l, Sound.ITEM_TRIDENT_RIPTIDE_2, 0.6f, 1.1f);
					                    p.playSound(l, Sound.ITEM_TRIDENT_RIPTIDE_3, 0.6f, 1.8f);
					                    
					                	for(Entity e: tl.getWorld().getNearbyEntities(tl,12,12,12)) {
					    					if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
					    					{
					                    		if (e instanceof Player) 
					    						{
					    							
					    							Player p1 = (Player) e;
					    							if(Party.hasParty(p) && Party.hasParty(p1))	{
					    							if(Party.getParty(p).equals(Party.getParty(p1)))
					    								{
					    									continue;
					    								}
					    							}
					    						}
					    						LivingEntity le = (LivingEntity)e;
					    						atk1(1.2, p, le);
					    						le.teleport(tl);
					    						Holding.superholding(p, le, 11l);
					    					}
					                	}
					                }
								},j.incrementAndGet()*2);
		                    	
		                    });
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	ft.remove();
				                    p.playSound(tl, Sound.BLOCK_BEACON_ACTIVATE, 1f,0f);
				                    p.playSound(tl, Sound.BLOCK_CONDUIT_ACTIVATE, 1f,0f);
				                    Holding.holding(p, p, 20l);
				                    for(int i = 0; i<10; i++) {
					                	tl.getWorld().spawnParticle(Particle.DRIPPING_WATER, tl.clone().add(0, i, 0), 500,0.5,0.5,0.5);
					                	tl.getWorld().spawnParticle(Particle.NAUTILUS, tl.clone().add(0, i, 0), 500,0.3,0.3,0.3);
				                    }
				                    for(int h = 6; h<9; h++) {
					                	tl.getWorld().spawnParticle(Particle.DRIPPING_WATER, tl.clone().add(0, h, 3), 300,0.21,0.21,0.21);
					                	tl.getWorld().spawnParticle(Particle.DRIPPING_WATER, tl.clone().add(-3, h, 0), 300,0.21,0.21,0.21);
					                	tl.getWorld().spawnParticle(Particle.DRIPPING_WATER, tl.clone().add(0, h, -3), 300,0.21,0.21,0.21);
					                	tl.getWorld().spawnParticle(Particle.DRIPPING_WATER, tl.clone().add(3, h, 0), 300,0.21,0.21,0.21);
				                    }
				                	tl.getWorld().spawnParticle(Particle.DRIPPING_WATER, tl.clone().add(0, 6, 0), 500,3,1,3);
				                	for(Entity e: tl.getWorld().getNearbyEntities(tl,12,12,12)) {
				    					if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
				    					{
				                    		if (e instanceof Player) 
				    						{
				    							
				    							Player p1 = (Player) e;
				    							if(Party.hasParty(p) && Party.hasParty(p1))	{
				    							if(Party.getParty(p).equals(Party.getParty(p1)))
				    								{
				    									continue;
				    								}
				    							}
				    						}
				    						LivingEntity le = (LivingEntity)e;
				    						Holding.superholding(p, le, 30l);
				    					}
				                	}
				                }
				            }, 41); 

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	p.setCooldown(CAREFUL, 3);
				                	p.swingMainHand();
				                	Vector pv = tl.clone().toVector().subtract(p.getLocation().clone().toVector());
				                	for(int i = 0; i <p.getLocation().distance(tl); i++) {
				                		p.getLocation().getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation().clone().add(pv.clone().normalize().multiply(i)), 20,1,1,1);
				                		p.getLocation().getWorld().spawnParticle(Particle.DRIPPING_WATER, p.getLocation().clone().add(pv.clone().normalize().multiply(i)), 100,1,1,1);
				                		p.getLocation().getWorld().spawnParticle(Particle.CRIT, p.getLocation().clone().add(pv.clone().normalize().multiply(i)), 100,1,1,1,0);
				                		p.getLocation().getWorld().spawnParticle(Particle.ENCHANTED_HIT, p.getLocation().clone().add(pv.clone().normalize().multiply(i)), 100,1,1,1,0);
				                	}
				                	p.teleport(tl);
				                    p.playSound(tl, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.7f, 0f);
				                    p.playSound(tl, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.7f, 2f);
				                    p.playSound(tl, Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 0.7f, 2f);
				                    p.playSound(tl, Sound.ITEM_TRIDENT_HIT_GROUND, 0.7f, 0f);
				                    p.playSound(tl, Sound.ITEM_TRIDENT_RIPTIDE_2, 0.8f, 2f);
				                    p.playSound(tl, Sound.ITEM_TRIDENT_RIPTIDE_3, 0.6f, 2f);
				                    p.playSound(tl, Sound.ENTITY_GENERIC_SPLASH, 1f, 2f);
				                    p.playSound(tl, Sound.ENTITY_GENERIC_SPLASH, 1f, 0f);
				                	p.getWorld().spawnParticle(Particle.FALLING_WATER, tl, 1000,5,5,5);
				                	p.getWorld().spawnParticle(Particle.ENCHANTED_HIT, tl, 1000,5,5,5);
				                	p.getWorld().spawnParticle(Particle.SCRAPE, tl, 1000,5,5,5);
				                	p.getWorld().strikeLightningEffect(tl);
				                	for(Entity e: tl.getWorld().getNearbyEntities(tl, 12,12,12)) {
				    					if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
				    					{
				                    		if (e instanceof Player) 
				    						{
				    							
				    							Player p1 = (Player) e;
				    							if(Party.hasParty(p) && Party.hasParty(p1))	{
				    							if(Party.getParty(p).equals(Party.getParty(p1)))
				    								{
				    									continue;
				    								}
				    							}
				    						}
				    						LivingEntity le = (LivingEntity)e;
				    						atk1(12.5, p, le);
				    					}
				                	}
				                }
				            }, 61); 
							
						});
				bd.execute();
			}
		
	}

	
	
	@SuppressWarnings("deprecation")
	public void ThrowCancel(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
		
			if(ClassData.pc.get(p.getUniqueId()) == 20  && ((is.getType()==Material.TRIDENT)||(is.getType()==Material.SHIELD)) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
			}
	
    }

	
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 20)
		{
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT || p.getInventory().getItemInMainHand().getType()==Material.SHIELD)
			{
					e.setCancelled(true);
			}
		}
		
	}


	public void Shield(PlayerRiptideEvent e) 
	{
		Player p = e.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 20)
		{
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT&& p.getInventory().getItemInOffHand().getType()==Material.SHIELD)
			{
				final ItemStack is = p.getInventory().getItemInOffHand();
				shield.put(p.getName(), is.clone());
				p.getInventory().getItemInOffHand().setType(Material.PRISMARINE_SHARD);
				int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	    				p.getInventory().getItemInOffHand().setType(Material.SHIELD);
	                    p.getInventory().getItemInOffHand().setItemMeta(shield.get(p.getName()).getItemMeta());
	                    shield.remove(p.getName());
	                }
	            }, 30);  
        		shieldt.put(p.getName(), task);
			}
			else if(p.getInventory().getItemInMainHand().getType()==Material.SHIELD&& p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)
			{
				final ItemStack is = p.getInventory().getItemInMainHand();
				shield.put(p.getName(), is.clone());
				p.getInventory().getItemInOffHand().setType(Material.PRISMARINE_SHARD);
        		int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	    				p.getInventory().getItemInMainHand().setType(Material.SHIELD);
	                    p.getInventory().getItemInMainHand().setItemMeta(shield.get(p.getName()).getItemMeta());
	                    shield.remove(p.getName());
	                }
	            }, 30);  
        		shieldt.put(p.getName(), task);
			}
		}
		
	}
	
	
	public void Shield(PlayerItemHeldEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 20 ){
			if(p.isRiptiding()) {
				if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT && shield.containsKey(p.getName()))
				{
					ev.setCancelled(true);
    				p.getInventory().getItemInOffHand().setType(Material.SHIELD);
	                p.getInventory().getItemInOffHand().setItemMeta(shield.get(p.getName()).getItemMeta());
                    shield.remove(p.getName());
	                Bukkit.getScheduler().cancelTask(shieldt.get(p.getName()));
				}
				else if(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT && shield.containsKey(p.getName()))
				{
					ev.setCancelled(true);
    				p.getInventory().getItemInMainHand().setType(Material.SHIELD);
	                p.getInventory().getItemInMainHand().setItemMeta(shield.get(p.getName()).getItemMeta());
                    shield.remove(p.getName());
	                Bukkit.getScheduler().cancelTask(shieldt.get(p.getName()));
				}
			}
		}
	}

	
	public void Shield(PlayerQuitEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 20 ){
			if(p.isRiptiding()) {
				if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT && shield.containsKey(p.getName()))
				{
    				p.getInventory().getItemInOffHand().setType(Material.SHIELD);
	                p.getInventory().getItemInOffHand().setItemMeta(shield.get(p.getName()).getItemMeta());
                    shield.remove(p.getName());
	                Bukkit.getScheduler().cancelTask(shieldt.get(p.getName()));
				}
				else if(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT && shield.containsKey(p.getName()))
				{
    				p.getInventory().getItemInMainHand().setType(Material.SHIELD);
	                p.getInventory().getItemInMainHand().setItemMeta(shield.get(p.getName()).getItemMeta());
                    shield.remove(p.getName());
	                Bukkit.getScheduler().cancelTask(shieldt.get(p.getName()));
				}
			}
		}
	}

	
	public void Splash(EntityDamageEvent d) 
	{		
	    if (!(d.getEntity() instanceof Player)) {
	        return;
	    }
	    Player p = (Player) d.getEntity();
	    
		
		
		if(d.getCause().equals(DamageCause.FALL)) 
		{
	        if(ClassData.pc.get(p.getUniqueId()) == 20 && (p.getInventory().getItemInMainHand().getType()==Material.TRIDENT || p.getInventory().getItemInMainHand().getType()==Material.SHIELD)) {
	        	p.getWorld().spawnParticle(Particle.SPLASH, p.getLocation(), 65*(int)p.getFallDistance(), p.getFallDistance(), 1, p.getFallDistance());
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1, 0);
	        	for(Entity e: p.getNearbyEntities(p.getFallDistance(), 2, p.getFallDistance())) {
	        		if(e instanceof LivingEntity) {
	        			LivingEntity le = (LivingEntity)e;
						atk1(0.1*(1+fsd.Splash.get(p.getUniqueId())*0.03415), p, le);
	        			
	        		}
	        	}
	        	d.setCancelled(true);
	        }
		}
	}




	public void Splash(EntityDamageByEntityEvent d)
	{
		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
        
		

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 20) {
			if(p.isRiptiding()) {
				if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT && shield.containsKey(p.getName()))
				{
    				p.getInventory().getItemInOffHand().setType(Material.SHIELD);
	                p.getInventory().getItemInOffHand().setItemMeta(shield.get(p.getName()).getItemMeta());
	                Bukkit.getScheduler().cancelTask(shieldt.get(p.getName()));
				}
				else if(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT && shield.containsKey(p.getName()))
				{
    				p.getInventory().getItemInMainHand().setType(Material.SHIELD);
	                p.getInventory().getItemInMainHand().setItemMeta(shield.get(p.getName()).getItemMeta());
	                Bukkit.getScheduler().cancelTask(shieldt.get(p.getName()));
				}
			}
			if(p==le) {d.setCancelled(true);}
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT) {
				if(!p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.RIPTIDE)){
					p.getInventory().getItemInMainHand().addEnchantment(Enchantment.RIPTIDE, 3);
				}
				if(p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOYALTY)) {
					p.getInventory().getItemInMainHand().removeEnchantment(Enchantment.LOYALTY);
					p.getInventory().getItemInMainHand().addEnchantment(Enchantment.RIPTIDE, 3);
					if(p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.CHANNELING)) {
						p.getInventory().getItemInMainHand().removeEnchantment(Enchantment.CHANNELING);
					}
				}
			}
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT|| p.getInventory().getItemInMainHand().getType()==Material.SHIELD)
			{
				dset2(d, p, 1.1*(1+fsd.Splash.get(p.getUniqueId())*0.03415), le, 7);
				if(p.getCooldown(Material.STRUCTURE_VOID)<=0) {
    				p.setCooldown(Material.STRUCTURE_VOID, 3);
    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						for(Entity e: le.getNearbyEntities(2.5, 2.5, 2.5)) {
    	                		if (e instanceof Player) 
    							{
    								
    								Player p1 = (Player) e;
    								if(Party.hasParty(p) && Party.hasParty(p1))	{
    								if(Party.getParty(p).equals(Party.getParty(p1)))
    									{
    										continue;
    									}
    								}
    							}
    		            		if ((!(e == p))&& le != e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
    							{
    		            			LivingEntity lle = (LivingEntity)e;
    								atk1(0.1*(1+fsd.Splash.get(p.getUniqueId())*0.03415), p, lle);
    							}
    							
    						}
    	                }
    	            }, 1); 
				}
			}
			}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity) 
		{
		Projectile ar = (Projectile)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
        
		

		
		
		if(ar.getShooter() instanceof Player) {
			Player p = (Player) ar.getShooter();
			if(ClassData.pc.get(p.getUniqueId()) == 20) {
				if(p==le) {d.setCancelled(true);}
				if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT) {
					if(p.getInventory().getItemInMainHand().getEnchantments() == null) {
						p.getInventory().getItemInMainHand().addEnchantment(Enchantment.RIPTIDE, 3);
					}
					else if(p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOYALTY)) {
						p.getInventory().getItemInMainHand().removeEnchantment(Enchantment.LOYALTY);
						p.getInventory().getItemInMainHand().addEnchantment(Enchantment.RIPTIDE, 3);
						if(p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.CHANNELING)) {
							p.getInventory().getItemInMainHand().removeEnchantment(Enchantment.CHANNELING);
						}
					}
				}
				if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT|| p.getInventory().getItemInMainHand().getType()==Material.SHIELD)
				{
					if(ar.hasMetadata("jav of "+p.getName())) {
						dset0(d, p, 0.635*(1+fsd.Javelin.get(p.getUniqueId())*0.0453), le, 7);
					}
					dset2(d, p, 1.1*(1+fsd.Splash.get(p.getUniqueId())*0.03415), le, 7);
					if(p.getCooldown(Material.STRUCTURE_VOID)<=0) {
	    				p.setCooldown(Material.STRUCTURE_VOID, 3);

	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						for(Entity e: le.getNearbyEntities(2.5, 2.5, 2.5)) {
	    	                		if (e instanceof Player) 
	    							{
	    								
	    								Player p1 = (Player) e;
	    								if(Party.hasParty(p) && Party.hasParty(p1))	{
	    								if(Party.getParty(p).equals(Party.getParty(p1)))
	    									{
	    										continue;
	    									}
	    								}
	    							}
	    		            		if ((!(e == p))&& le != e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
	    							{
	    		            			LivingEntity lle = (LivingEntity)e;
	    								atk1(0.1*(1+fsd.Splash.get(p.getUniqueId())*0.03415), p, lle);
	    							}
	    							
	    						}
	    	                }
	    	            }, 1); 
					}
				}
				}
		}
		}
	}
}