package io.github.chw3021.hunter;




import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.chw3021.classes.ClassData;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.cook.CookSkillsData;
import io.github.chw3021.party.PartyData;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Hunskills implements Serializable, Listener {

	/**
	 * 
	 */
	private static transient final long serialVersionUID = -6374689169024724861L;
	private HashMap<String, Long> tbcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> chcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> djcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> hucooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> lccooldown = new HashMap<String, Long>();
	private HashMap<String, Long> aultcooldown = new HashMap<String, Long>();
	private static HashMap<String, Integer> hunting = new HashMap<String, Integer>();
	private static HashMap<String, Integer> huntt = new HashMap<String, Integer>();
	private static HashMap<String, Integer> huntofft = new HashMap<String, Integer>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private static HashMap<String, Integer> climb = new HashMap<String, Integer>();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	Holding hold = Holding.getInstance();
	private HunSkillsData hsd = new HunSkillsData(HunSkillsData.loadData(path +"/plugins/RPGskills/HunSkillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		HunSkillsData h = new HunSkillsData(HunSkillsData.loadData(path +"/plugins/RPGskills/HunSkillsData.data"));
		hsd = h;
	}
	
	@EventHandler
	public void classinv(InventoryClickEvent e)
	{
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Classes"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
				playerclass = cdata.playerclass;
			}
			
		}
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Hunskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				HunSkillsData h = new HunSkillsData(HunSkillsData.loadData(path +"/plugins/RPGskills/HunSkillsData.data"));
				hsd = h;
			}
			
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		HunSkillsData h = new HunSkillsData(HunSkillsData.loadData(path +"/plugins/RPGskills/HunSkillsData.data"));
		hsd = h;
		
	}

	@EventHandler
	public void Webthrow(PlayerInteractEvent ev) 
	{
	    
		Player p = ev.getPlayer();
		Action a = ev.getAction();
		int sec = 6;
		
		
		if(playerclass.get(p.getUniqueId()) == 2) {
			
		if(((a==Action.RIGHT_CLICK_AIR || a==Action.RIGHT_CLICK_BLOCK)) && (p.isSneaking()) && hsd.Webthrow.get(p.getUniqueId())>=1)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE")&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK"))
			{
				if(tbcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (tbcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Webthrow").create());
	                }
	                else // if timer is done
	                {
		        		
	                	tbcooldown.remove(p.getName());

	            		Snowball sn = p.launchProjectile(Snowball.class);
	            		sn.setItem(new ItemStack(Material.COBWEB));
	            		sn.setVelocity(sn.getVelocity().multiply(0.68));
	            		sn.setMetadata("web", new FixedMetadataValue(RMain.getInstance(), true));
	            		sn.setShooter(p);
	            		sn.setGlowing(true);
		                tbcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {;

	        		Snowball sn = p.launchProjectile(Snowball.class);
	        		sn.setItem(new ItemStack(Material.COBWEB));
	        		sn.setVelocity(sn.getVelocity().multiply(0.68));
	        		sn.setMetadata("web", new FixedMetadataValue(RMain.getInstance(), true));
	        		sn.setShooter(p);
	        		sn.setGlowing(true);
	                tbcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
		}
			
		}
	}

	
	@EventHandler
	public void Webthrow(ProjectileHitEvent ev) 
	{
		if(ev.getEntity().hasMetadata("web")) {
			Projectile pr = ev.getEntity();
			if(pr.getShooter() instanceof Player && ev.getHitEntity() instanceof LivingEntity) {
				Player p = (Player) pr.getShooter();
				LivingEntity e = (LivingEntity) ev.getHitEntity();
        		if (e instanceof Player) 
				{
        			new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
					Player p1 = (Player) e;
					if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
					if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
						{
						return;
						}
					}
				}
        		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
				{
					LivingEntity le = (LivingEntity)e;
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 50, 1,1,1, 0.21, Material.COBWEB.createBlockData());
					p.playSound(le.getLocation(), Sound.BLOCK_WEEPING_VINES_BREAK, 1, 0);
					p.playSound(le.getLocation(), Sound.ENTITY_SPIDER_HURT, 1, 0);
					hold.slow(p, le, 30l, 75d);
				}
			}
		}
	}
	@EventHandler
	public void Chain(PlayerInteractAtEntityEvent ev) 
	{
			Player p = ev.getPlayer();
			final Entity e = ev.getRightClicked();
			int sec = 2;
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 2)	{
				
				if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") &&!p.isSneaking()  && hsd.Chain.get(p.getUniqueId())>=1)
				{

					if(chcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (chcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Chain").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    chcooldown.remove(p.getName()); // removing player from HashMap
		                    p.playSound(p.getLocation(), Sound.ENTITY_FISHING_BOBBER_RETRIEVE, 1, 2);
		                    if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
							{
								if (e instanceof Player) 
								{
									Player p1 = (Player) e;
									try {
									if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
									if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
										{
											return;
										}
									}}
									catch(NullPointerException ne) {
										return;
									}
								}
								LivingEntity le = (LivingEntity)e;
								le.getWorld().spawnParticle(Particle.SQUID_INK, e.getLocation(), 2);
								le.setAI(false);
								le.setVelocity(p.getLocation().getDirection());
								e.teleport(p.getLocation());
								le.setAI(true);
					                
							}
			                chcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
	                    p.playSound(p.getLocation(), Sound.ENTITY_FISHING_BOBBER_RETRIEVE, 1, 2);
						if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
						{
							if (e instanceof Player) 
							{
								Player p1 = (Player) e;
								try {
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
									{
										return;
									}
								}}
								catch(NullPointerException ne) {
									return;
								}
							}
							LivingEntity le = (LivingEntity)e;
							le.getWorld().spawnParticle(Particle.SQUID_INK, e.getLocation(), 2);
							le.setAI(false);
							le.setVelocity(p.getLocation().getDirection());
							e.teleport(p.getLocation());
							le.setAI(true);
				                
						}
		                chcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
					
				}
			}
	}
	
	@EventHandler
	public void Dodge(PlayerSwapHandItemsEvent ev) 
	{
			Player p = ev.getPlayer();
			int sec = 3;
			
			if(playerclass.get(p.getUniqueId()) == 2 && !p.isSneaking())	{
				
				if(p.getInventory().getItemInMainHand().getType().name().contains("AXE")&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK") && hsd.Dodge.get(p.getUniqueId())>=1)
				{
					ev.setCancelled(true);
                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 6).getLocation();
					l.setY(l.getY()+1);
					l.setDirection(p.getLocation().getDirection());
					if(l.getBlock().isPassable()) {
					if(djcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (djcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Dodge").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    djcooldown.remove(p.getName()); // removing player from HashMap
							p.teleport(l);
							p.playSound(p.getLocation(), Sound.ENTITY_SPIDER_STEP, 1.0f, 1.5f);
			                djcooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
						p.teleport(l);
						p.playSound(p.getLocation(), Sound.ENTITY_SPIDER_STEP, 1.0f, 1.5f);
		                djcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }}
					
				}
			}
	}


	@EventHandler
	public void Climb(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 0;
		
		if(playerclass.get(p.getUniqueId()) == 2 && p.isSneaking())	{
			
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE")&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK") && hsd.Bleeding.get(p.getUniqueId())>=1)
			{
				ev.setCancelled(true);
                Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 6).getLocation();
				l.setY(l.getY()+1);
				l.setDirection(p.getLocation().getDirection());
				if(l.getBlock().isPassable()) {
					if(djcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (djcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    djcooldown.remove(p.getName()); // removing player from HashMap
							if(climb.containsKey(p.getName())) {
								climb.remove(p.getName());
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Climbing off").color(ChatColor.DARK_GREEN).create());
							}
							else {
								climb.put(p.getName(),1);
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Climbing on").color(ChatColor.DARK_GREEN).create());
							}
			                djcooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
						if(climb.containsKey(p.getName())) {
							climb.remove(p.getName());
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Climbing off").color(ChatColor.DARK_RED).create());
						}
						else {
							climb.put(p.getName(),1);
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Climbing on").color(ChatColor.DARK_RED).create());
						}
		                djcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
				
			}
		}
	}
	

	@EventHandler
	public void Climb(PlayerMoveEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(playerclass.get(p.getUniqueId()) == 2 && climb.containsKey(p.getName()))	{
			if(!p.getTargetBlock(null, 1).isPassable()) {
				p.setGravity(false);
				p.setVelocity(p.getLocation().clone().add(0, 1, 0).toVector().subtract(p.getLocation().toVector()).normalize().multiply(0.35));
			}
			else {
				p.setGravity(true);
			}
		}
	}
	
	@EventHandler
	public void Dodge(EntityDamageEvent d) 
	{		
        if (!(d.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) d.getEntity();
        
		
		
		
		if(d.getCause().equals(DamageCause.FALL)) 
		{
	        if(playerclass.get(p.getUniqueId()) == 2&& hsd.Dodge.get(p.getUniqueId())>=1) {
	        	if(d.getDamage()<=1.5) {
	        		d.setCancelled(true);
					p.playSound(p.getLocation(), Sound.ENTITY_SPIDER_STEP, 1.0f, 1.5f);
	        	}
	        	else{
		        	d.setDamage(d.getDamage()*0.1);
					p.playSound(p.getLocation(), Sound.ENTITY_SPIDER_STEP, 1.0f, 1.5f);
	        	}
	        }
		}
	}
	public void HuntingEffectadd(Player p)
	{
		if(hunting.containsKey(p.getName()))
		{
				Bukkit.getServer().getScheduler().cancelTask(huntt.get(p.getName()));
				Bukkit.getServer().getScheduler().cancelTask(huntofft.get(p.getName()));
				p.setInvulnerable(false);
				hunting.remove(p.getName());
				huntt.remove(p.getName());
				huntofft.remove(p.getName());
		}
			p.setInvulnerable(true);
			hunting.computeIfPresent(p.getName(), (k,v)->v+1);
			hunting.putIfAbsent(p.getName(), 0);
		    int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		        @Override
		        public void run() {
		    		p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 3, 0, false, false));
		    		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3, 1, false, false));
		    		p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 3, 1, false, false));
		    		p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 3, 0, false, false));
		        }
		    }, 1,1); 
		    huntt.put(p.getName(), task);
	}
	
	public void HuntingEffectrmv(Player p)
	{
		Bukkit.getServer().getScheduler().cancelTask(huntt.get(p.getName()));
		Bukkit.getServer().getScheduler().cancelTask(huntofft.get(p.getName()));
		p.setInvulnerable(false);
		hunting.remove(p.getName());
		huntt.remove(p.getName());
		huntofft.remove(p.getName());
		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Hunting off").color(ChatColor.DARK_RED).create());
	}
	

	@EventHandler
	public void Huntingoff(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity) d.getEntity();
		
		if(playerclass.get(p.getUniqueId()) == 2)
		{
			
			if(hunting.containsKey(p.getName()))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("AXE"))
				{
					d.setDamage(d.getDamage()*1.15 *(1+ hsd.HuntingStart.get(p.getUniqueId())*0.09));
				}
				HuntingEffectrmv(p);
				le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20,20,false,false));
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() { 
	    				if(le.isDead() || le.getHealth()<=0) {
	    	                hucooldown.remove(p.getName());
	    				}
	    				else {
	        		        hucooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	    				}
	                }
	            }, 1/5);
			}	
		}
		}
	}

	@EventHandler
	public void HuntingStart(PlayerInteractEvent ev) 
	{
	    
		Player p = ev.getPlayer();
		Action a = ev.getAction();
		int sec = 6;
		
		
		if(playerclass.get(p.getUniqueId()) == 2) {
		if(((a==Action.RIGHT_CLICK_AIR || a==Action.RIGHT_CLICK_BLOCK)) && !(p.isOnGround()) && !(p.isSneaking()))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && !p.getInventory().getItemInMainHand().getType().name().contains("PICK")&& !hunting.containsKey(p.getName()))
			{
				if(hucooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (hucooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Hunting Start").create());
	                }
	                else // if timer is done
	                {
		        		
	                    hucooldown.remove(p.getName());
		        		p.playSound(p.getLocation(), Sound.BLOCK_PORTAL_AMBIENT, 1.0f, 1.5f);
		        		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 3);
		        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{HuntingStart}").color(ChatColor.DARK_RED).create());
		                HuntingEffectadd(p);
		                int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	if(hunting.containsKey(p.getName()))
			    				HuntingEffectrmv(p);
			    				huntofft.remove(p.getName());
			    		        hucooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			                }
			            }, 110); 
		                huntofft.put(p.getName(), task);
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	        		p.playSound(p.getLocation(), Sound.BLOCK_PORTAL_AMBIENT, 1.0f, 1.5f);
	        		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 3);
	        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{HuntingStart}").color(ChatColor.DARK_RED).create());
	                HuntingEffectadd(p);
	                int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	if(hunting.containsKey(p.getName()))
		    				HuntingEffectrmv(p);
		    				huntofft.remove(p.getName());
		    		        hucooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		                }
		            }, 110); 
	                huntofft.put(p.getName(), task);
	            }
			}
		}
			
		}
	}
	
	@EventHandler
	public void HuntingOFF(PlayerInteractEvent ev) 
	{
	    
		Player p = ev.getPlayer();
		Action a = ev.getAction();
		
		
		if(playerclass.get(p.getUniqueId()) == 2) {
		if(((a==Action.LEFT_CLICK_BLOCK)))
		{
			if(hunting.containsKey(p.getName()))
			{
				HuntingEffectrmv(p);
		        hucooldown.put(p.getName(), System.currentTimeMillis()-2500); // adding players name + current system time in miliseconds
			}
		}
		}
		
	}
	
	
	@EventHandler
	public void Daze(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 6;
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&&!d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		final LivingEntity le = (LivingEntity)d.getEntity();
		
		
		if(playerclass.get(p.getUniqueId()) == 2)	
		{
				if(p.getInventory().getItemInMainHand().getType().name().contains("AXE")&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK") && p.isSneaking() && !(p.isSprinting()))
				{
					
					if(p.getAttackCooldown()==1) 
					{
						if(lccooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
				        {
				            long timer = (lccooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
				            if(!(timer < 0)) // if timer is still more then 0 or 0
				            {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Daze").create());
				            }
				            else // if timer is done
				            {
				                lccooldown.remove(p.getName()); // removing player from HashMap
								if (le instanceof Player) 
								{
									Player p1 = (Player) le;
									try {
									if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
									if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
										{
											return;
										}
									}}
									catch(NullPointerException ne) {
										return;
									}
								}
								le.setVelocity(p.getLocation().getDirection());
								d.setDamage(d.getDamage()*1.8 *(1+ hsd.Daze.get(p.getUniqueId())*0.035));
								p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0f, 2.0f);
								p.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, le.getLocation(), 5, 1, 0, 1);
								hold.holding(p, le, (long) 10);
					            lccooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				            }
				        }
				        else // if cooldown doesn't have players name in it
				        {

							if (le instanceof Player) 
							{
								Player p1 = (Player) le;
								try {
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
									{
										return;
									}
								}}
								catch(NullPointerException ne) {
									return;
								}
							}
							le.setVelocity(p.getLocation().getDirection());
							d.setDamage(d.getDamage()*1.8 *(1+ hsd.Daze.get(p.getUniqueId())*0.035));
							p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0f, 2.0f);
							p.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, le.getLocation(), 5, 1, 0, 1);
							hold.holding(p, le, (long) 10);
				            lccooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        }
							
					}
						
				}
		}
		}
	}
	
	@EventHandler
	public void SkullCrusher(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 6;
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&&!d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
		
		
		if(playerclass.get(p.getUniqueId()) == 2)	{
			if(p.getAttackCooldown()==1) 
			{	
				
				if(p.getInventory().getItemInMainHand().getType().name().contains("AXE")&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK") && !(p.isOnGround()) && !p.isSprinting())
					{
					if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            long timer = (smcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use skullcrusher").create());
			            }
			            else // if timer is done
			            {
			                smcooldown.remove(p.getName()); // removing player from HashMap
							if (le instanceof Player) 
							{
								Player p1 = (Player) le;
								try {
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
									{
										return;
									}
								}}
								catch(NullPointerException ne) {
									return;
								}
							}
							d.setDamage(d.getDamage()*2.5 *(1+ hsd.SkullCrusher.get(p.getUniqueId())*0.05));
							p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getEyeLocation(), 200, 1.2, 1.2, 1.2, Material.BONE_BLOCK.createBlockData());
							p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getEyeLocation(), 200, 1.2, 1.2, 1.2, Material.DEAD_BRAIN_CORAL_BLOCK.createBlockData());
							p.playSound(p.getLocation(), Sound.BLOCK_BONE_BLOCK_BREAK, 1.0f, 2f);
							p.playSound(p.getLocation(), Sound.BLOCK_NETHER_ORE_BREAK, 1.0f, 0f);
							p.playSound(p.getLocation(), Sound.BLOCK_NETHER_ORE_BREAK, 1.0f, 2f);
							p.playSound(p.getLocation(), Sound.BLOCK_BONE_BLOCK_BREAK, 1.0f, 0f);
				            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
						if (le instanceof Player) 
						{
							Player p1 = (Player) le;
							try {
							if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
							if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
								{
									return;
								}
							}}
							catch(NullPointerException ne) {
								return;
							}
						}
						d.setDamage(d.getDamage()*2.5 *(1+ hsd.SkullCrusher.get(p.getUniqueId())*0.05));
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getEyeLocation(), 200, 1.2, 1.2, 1.2, Material.BONE_BLOCK.createBlockData());
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getEyeLocation(), 200, 1.2, 1.2, 1.2, Material.DEAD_BRAIN_CORAL_BLOCK.createBlockData());
						p.playSound(p.getLocation(), Sound.BLOCK_BONE_BLOCK_BREAK, 1.0f, 2f);
						p.playSound(p.getLocation(), Sound.BLOCK_NETHER_ORE_BREAK, 1.0f, 0f);
						p.playSound(p.getLocation(), Sound.BLOCK_NETHER_ORE_BREAK, 1.0f, 2f);
						p.playSound(p.getLocation(), Sound.BLOCK_BONE_BLOCK_BREAK, 1.0f, 0f);
			            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			        }
					}
			}
		}
		}
	}

	
	
	
	@EventHandler
	public void ULT(PlayerDropItemEvent ev)        
    {
	    
		Player p = (Player)ev.getPlayer();
		final Location l = p.getLocation();
		Item i = ev.getItemDrop();
		ItemStack is = i.getItemStack();
		
		
		if(playerclass.get(p.getUniqueId()) == 2 && (is.getType().name().contains("AXE"))&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK") && p.isSneaking())
			{
				ev.setCancelled(true);
				if(aultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (aultcooldown.get(p.getName())/1000 + 60) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Rage").create());
		            }
	                else // if timer is done
	                {
	                    aultcooldown.remove(p.getName()); // removing player from HashMap
	                    p.playSound(p.getLocation(), Sound.AMBIENT_CAVE, 1.0f, 1.8f);
						p.getWorld().spawnParticle(Particle.DRAGON_BREATH, p.getLocation(), 100, 2, 2, 2);
						p.sendTitle(ChatColor.MAGIC + "RAGE", null);
						if(p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100+p.getLevel(), p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()+3, false, false));
						}
						else {
							p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100+p.getLevel(), 3, false, false));
						}
						if(p.hasPotionEffect(PotionEffectType.ABSORPTION)) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100+p.getLevel(), p.getPotionEffect(PotionEffectType.ABSORPTION).getAmplifier()+3, false, false));
						}
						else {
							p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100+p.getLevel(), 3, false, false));
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100+p.getLevel(), 3, false, false));
						for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 15, 10, 15))
						{
							if (e instanceof Player) 
							{
								Player p1 = (Player) e;
								try {
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
									{
										continue;
									}
								}}
								catch(NullPointerException ne) {
									continue;
								}
							}
							if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))) 
							{
								final LivingEntity le = (LivingEntity)e;
								{
									hold.holding(p, le, 20l);
									le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100+p.getLevel(), 3, false, false));
									le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100+p.getLevel(), 3, false, false));
								}
							}
						}
						
		                aultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	p.playSound(p.getLocation(), Sound.AMBIENT_CAVE, 1.0f, 1.8f);
					p.getWorld().spawnParticle(Particle.DRAGON_BREATH, p.getLocation(), 100, 2, 2, 2);
					p.sendTitle(ChatColor.MAGIC + "RAGE", null);
					if(p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100+p.getLevel(), p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()+3, false, false));
					}
					else {
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100+p.getLevel(), 3, false, false));
					}
					if(p.hasPotionEffect(PotionEffectType.ABSORPTION)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100+p.getLevel(), p.getPotionEffect(PotionEffectType.ABSORPTION).getAmplifier()+3, false, false));
					}
					else {
						p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100+p.getLevel(), 3, false, false));
					}
					p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100+p.getLevel(), 3, false, false));
					for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 15, 10, 15))
					{
						if (e instanceof Player) 
						{
							Player p1 = (Player) e;
							try {
							if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
							if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
								{
									continue;
								}
							}}
							catch(NullPointerException ne) {
								continue;
							}
						}
						if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))) 
						{
							final LivingEntity le = (LivingEntity)e;
							{
								hold.holding(p, le, 20l);
								le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100+p.getLevel(), 3, false, false));
								le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100+p.getLevel(), 3, false, false));
							}
						}
					}
	                aultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}	
			
			
    }

	@EventHandler
	public void Hunger(EntityPotionEffectEvent d) 
	{
		if(!(d.getEntity() instanceof Player)) {return;
		}
		
		else if(d.getEntity() instanceof Player) {
			if(d.getNewEffect() != null) {
				Player p = (Player)d.getEntity();
					if(playerclass.get(p.getUniqueId()) == 2) 
					{	
						if(d.getNewEffect().getType() == PotionEffectType.HUNGER) {
							p.removePotionEffect(PotionEffectType.HUNGER);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									p.removePotionEffect(PotionEffectType.HUNGER);
				                }
							}, 10); 
						}
						if(d.getModifiedType() == PotionEffectType.HUNGER) {
							p.removePotionEffect(PotionEffectType.HUNGER);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									p.removePotionEffect(PotionEffectType.HUNGER);
				                }
							}, 10); 
						}
						if(p.hasPotionEffect(PotionEffectType.HUNGER)) {
							p.removePotionEffect(PotionEffectType.HUNGER);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									p.removePotionEffect(PotionEffectType.HUNGER);
				                }
							}, 10); 
						}
					}	
			}
		}
	}
	
	
	@EventHandler
	public void Backattack(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&&!d.isCancelled()) 
		{
			Player p = (Player)d.getDamager();
			LivingEntity e = (LivingEntity)d.getEntity();
			if (e instanceof Player) 
			{
				Player p1 = (Player) e;
				if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
					if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
					{
						return;
					}
				}
			}
		
		
			if(playerclass.get(p.getUniqueId()) == 2)	
			{
				
				if(p.getInventory().getItemInMainHand().getType().name().contains("AXE")&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK") && hsd.Backattack.get(p.getUniqueId()) >= 1)
				{
					if(p.getAttackCooldown()==1) 
					{
							
						if (Math.abs(e.getLocation().getDirection().angle(p.getLocation().getDirection())) <= Math.PI/3+p.getLevel()/10) 
						{
							d.setDamage(d.getDamage()*1.5);
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("BackAttack").bold(true).create());
						}
							
						
					}
				}
			}
		}
	}
	@EventHandler(priority = EventPriority.MONITOR)
	public void Atrocity(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&&!d.isCancelled()) 
		{
			Player p = (Player)d.getDamager();
			LivingEntity e = (LivingEntity)d.getEntity();
			if (e instanceof Player) 
			{
				Player p1 = (Player) e;
				if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
					if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
					{
						return;
					}
				}
			}
		
		
			if(playerclass.get(p.getUniqueId()) == 2)	
			{
				
				if(p.getInventory().getItemInMainHand().getType().name().contains("AXE")&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK"))
				{
					if(p.getAttackCooldown()==1) 
					{
							d.setDamage(d.getDamage()+ e.getMaxHealth()*(0.05+hsd.Atrocity.get(p.getUniqueId())*0.0002));
							ArrayList<Location> line = new ArrayList<Location>();
		                    AtomicInteger j = new AtomicInteger();
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 1.5f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0.5f);
		                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0f, 1.5f);
		                    for(double an = Math.PI/6; an>-Math.PI/6; an-=Math.PI/180) {
		                    	Location pl = p.getEyeLocation();
		                    	pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(5));
		                    	line.add(pl);
		                    }
		                    line.forEach(l -> {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
						        		p.getWorld().spawnParticle(Particle.CRIT, l,5,0.1,0.1,0.1,0);
						        		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l,3,0.1,0.1,0.1,0, Material.NETHER_QUARTZ_ORE.createBlockData());
					                }
								}, j.incrementAndGet()/900); 
		                    	
		                    });
					}
				}
			}
		}
	}
	
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
	    
		Player p = e.getPlayer();

		
		
		if(playerclass.get(p.getUniqueId()) == 2)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && !p.getInventory().getItemInOffHand().getType().name().contains("SHIELD"))
			{
					e.setCancelled(true);
			}
		}
		
	}
	
	@EventHandler
	public void Damagegetter(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity e = (LivingEntity) d.getEntity();
		
		
		if (e instanceof Player) 
		{
			Player p1 = (Player) e;
			try {
			if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
			if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
				{
				d.setCancelled(true);
					return;
				}
			}}
			catch(NullPointerException ne) {
				d.setCancelled(true);
				return;
			}
		}
		if(playerclass.get(p.getUniqueId()) == 2)	
		{
			if(p.getInventory().getItemInMainHand().getType()==Material.IRON_AXE)
			{
					player_damage.put(p.getName(), 8 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					if (e.getType() == EntityType.ZOMBIE || e.getType() == EntityType.ZOMBIE_HORSE || e.getType() ==EntityType.ZOMBIE_VILLAGER || e.getType() == EntityType.ZOMBIFIED_PIGLIN|| e.getType() == EntityType.SKELETON || e.getType() == EntityType.SKELETON_HORSE || e.getType() == EntityType.WITHER_SKELETON || e.getType() == EntityType.HUSK || e.getType() == EntityType.WITHER || e.getType() == EntityType.STRAY || e.getType() == EntityType.PHANTOM || e.getType() == EntityType.DROWNED)
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD)*2.5);
					}
					if (e.getType() == EntityType.SPIDER || e.getType() == EntityType.CAVE_SPIDER || e.getType() == EntityType.BEE || e.getType() == EntityType.SILVERFISH || e.getType() == EntityType.ENDERMITE)
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS)*2.5);
												}
					if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
					}
					
					if(p.getAttackCooldown() <1) {
						d.setDamage(d.getDamage() * (1+player_damage.get(p.getName())*0.02) * (1+hsd.Atrocity.get(p.getUniqueId())*0.02));
					}
					else {
						d.setDamage(d.getDamage() * (1+player_damage.get(p.getName())*0.04) * (1+hsd.Atrocity.get(p.getUniqueId())*0.04) );
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, e.getEyeLocation(), 30, 1, 1, 1, Material.BLACK_GLAZED_TERRACOTTA.createBlockData());
					}
			}
			
			if(p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_AXE)
			{
					player_damage.put(p.getName(), 9 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
					if (e.getType() == EntityType.ZOMBIE || e.getType() == EntityType.ZOMBIE_HORSE || e.getType() ==EntityType.ZOMBIE_VILLAGER || e.getType() == EntityType.ZOMBIFIED_PIGLIN|| e.getType() == EntityType.SKELETON || e.getType() == EntityType.SKELETON_HORSE || e.getType() == EntityType.WITHER_SKELETON || e.getType() == EntityType.HUSK || e.getType() == EntityType.WITHER || e.getType() == EntityType.STRAY || e.getType() == EntityType.PHANTOM || e.getType() == EntityType.DROWNED)
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD)*2.5);
					}
					if (e.getType() == EntityType.SPIDER || e.getType() == EntityType.CAVE_SPIDER || e.getType() == EntityType.BEE || e.getType() == EntityType.SILVERFISH || e.getType() == EntityType.ENDERMITE)
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS)*2.5);
						
					}
					if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
					}
					
					if(p.getAttackCooldown() <1) {
						d.setDamage(d.getDamage() * (1+player_damage.get(p.getName())*0.02) * (1+hsd.Atrocity.get(p.getUniqueId())*0.02));
					}
					else {
						d.setDamage(d.getDamage() * (1+player_damage.get(p.getName())*0.04) * (1+hsd.Atrocity.get(p.getUniqueId())*0.04) );
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, e.getEyeLocation(), 30, 1, 1, 1, Material.BLACK_GLAZED_TERRACOTTA.createBlockData());
					}
			}
			
			if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_AXE)
			{
					player_damage.put(p.getName(), 6 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
					if (e.getType() == EntityType.ZOMBIE || e.getType() == EntityType.ZOMBIE_HORSE || e.getType() ==EntityType.ZOMBIE_VILLAGER || e.getType() == EntityType.ZOMBIFIED_PIGLIN|| e.getType() == EntityType.SKELETON || e.getType() == EntityType.SKELETON_HORSE || e.getType() == EntityType.WITHER_SKELETON || e.getType() == EntityType.HUSK || e.getType() == EntityType.WITHER || e.getType() == EntityType.STRAY || e.getType() == EntityType.PHANTOM || e.getType() == EntityType.DROWNED)
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD)*2.5);
					}
					if (e.getType() == EntityType.SPIDER || e.getType() == EntityType.CAVE_SPIDER || e.getType() == EntityType.BEE || e.getType() == EntityType.SILVERFISH || e.getType() == EntityType.ENDERMITE)
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS)*2.5);
						
					}
					if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
					}
					
					if(p.getAttackCooldown() <1) {
						d.setDamage(d.getDamage() * (1+player_damage.get(p.getName())*0.02) * (1+hsd.Atrocity.get(p.getUniqueId())*0.02));
					}
					else {
						d.setDamage(d.getDamage() * (1+player_damage.get(p.getName())*0.04) * (1+hsd.Atrocity.get(p.getUniqueId())*0.04) );
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, e.getEyeLocation(), 30, 1, 1, 1, Material.BLACK_GLAZED_TERRACOTTA.createBlockData());
					}
			}
			
			if(p.getInventory().getItemInMainHand().getType()==Material.STONE_AXE)
			{
					player_damage.put(p.getName(), 7 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
					if (e.getType() == EntityType.ZOMBIE || e.getType() == EntityType.ZOMBIE_HORSE || e.getType() ==EntityType.ZOMBIE_VILLAGER || e.getType() == EntityType.ZOMBIFIED_PIGLIN|| e.getType() == EntityType.SKELETON || e.getType() == EntityType.SKELETON_HORSE || e.getType() == EntityType.WITHER_SKELETON || e.getType() == EntityType.HUSK || e.getType() == EntityType.WITHER || e.getType() == EntityType.STRAY || e.getType() == EntityType.PHANTOM || e.getType() == EntityType.DROWNED)
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD)*2.5);
					}
					if (e.getType() == EntityType.SPIDER || e.getType() == EntityType.CAVE_SPIDER || e.getType() == EntityType.BEE || e.getType() == EntityType.SILVERFISH || e.getType() == EntityType.ENDERMITE)
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS)*2.5);
						
					}
					if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
					}
					
					if(p.getAttackCooldown() <1) {
						d.setDamage(d.getDamage() * (1+player_damage.get(p.getName())*0.02) * (1+hsd.Atrocity.get(p.getUniqueId())*0.02));
					}
					else {
						d.setDamage(d.getDamage() * (1+player_damage.get(p.getName())*0.04) * (1+hsd.Atrocity.get(p.getUniqueId())*0.04) );
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, e.getEyeLocation(), 30, 1, 1, 1, Material.BLACK_GLAZED_TERRACOTTA.createBlockData());
					}
			}
			
			if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_AXE)
			{
					player_damage.put(p.getName(), 5 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
					if (e.getType() == EntityType.ZOMBIE || e.getType() == EntityType.ZOMBIE_HORSE || e.getType() ==EntityType.ZOMBIE_VILLAGER || e.getType() == EntityType.ZOMBIFIED_PIGLIN|| e.getType() == EntityType.SKELETON || e.getType() == EntityType.SKELETON_HORSE || e.getType() == EntityType.WITHER_SKELETON || e.getType() == EntityType.HUSK || e.getType() == EntityType.WITHER || e.getType() == EntityType.STRAY || e.getType() == EntityType.PHANTOM || e.getType() == EntityType.DROWNED)
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD)*2.5);
					}
					if (e.getType() == EntityType.SPIDER || e.getType() == EntityType.CAVE_SPIDER || e.getType() == EntityType.BEE || e.getType() == EntityType.SILVERFISH || e.getType() == EntityType.ENDERMITE)
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS)*2.5);
						
					}
					if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
					}
					
					if(p.getAttackCooldown() <1) {
						d.setDamage(d.getDamage() * (1+player_damage.get(p.getName())*0.02) * (1+hsd.Atrocity.get(p.getUniqueId())*0.02));
					}
					else {
						d.setDamage(d.getDamage() * (1+player_damage.get(p.getName())*0.04) * (1+hsd.Atrocity.get(p.getUniqueId())*0.04) );
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, e.getEyeLocation(), 30, 1, 1, 1, Material.BLACK_GLAZED_TERRACOTTA.createBlockData());
					}
			}
			
			if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_AXE)
			{
					player_damage.put(p.getName(), 11 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
					if (e.getType() == EntityType.ZOMBIE || e.getType() == EntityType.ZOMBIE_HORSE || e.getType() ==EntityType.ZOMBIE_VILLAGER || e.getType() == EntityType.ZOMBIFIED_PIGLIN|| e.getType() == EntityType.SKELETON || e.getType() == EntityType.SKELETON_HORSE || e.getType() == EntityType.WITHER_SKELETON || e.getType() == EntityType.HUSK || e.getType() == EntityType.WITHER || e.getType() == EntityType.STRAY || e.getType() == EntityType.PHANTOM || e.getType() == EntityType.DROWNED)
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD)*2.5);
					}
					if (e.getType() == EntityType.SPIDER || e.getType() == EntityType.CAVE_SPIDER || e.getType() == EntityType.BEE || e.getType() == EntityType.SILVERFISH || e.getType() == EntityType.ENDERMITE)
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS)*2.5);
						
					}
					if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
					}
					
					if(p.getAttackCooldown() <1) {
						d.setDamage(d.getDamage() * (1+player_damage.get(p.getName())*0.02) * (1+hsd.Atrocity.get(p.getUniqueId())*0.02));
					}
					else {
						d.setDamage(d.getDamage() * (1+player_damage.get(p.getName())*0.04) * (1+hsd.Atrocity.get(p.getUniqueId())*0.04));
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, e.getEyeLocation(), 30, 1, 1, 1, Material.BLACK_GLAZED_TERRACOTTA.createBlockData());
					}
			}

		}
		}
	}
}



