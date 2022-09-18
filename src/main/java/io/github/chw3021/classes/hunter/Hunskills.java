package io.github.chw3021.classes.hunter;




import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.party.Party;
import io.github.chw3021.obtains.Obtained;
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
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
//import org.bukkit.entity.ArmorStand;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Hunskills extends Pak implements Serializable, Listener {

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
	private HashMap<String, Long> ault2cooldown = new HashMap<String, Long>();
	private HashMap<String, Integer> ult2t = new HashMap<String, Integer>();
	
	private static HashMap<String, Integer> hunting = new HashMap<String, Integer>();
	private static HashMap<String, Integer> huntt = new HashMap<String, Integer>();
	private static HashMap<String, Integer> huntofft = new HashMap<String, Integer>();

	private static HashMap<String, Integer> posed = new HashMap<String, Integer>();
	
	//private HashMap<String, Double> player_damage = new HashMap<String, Double>();

	private HashMap<Player, Integer> sz = new HashMap<Player, Integer>();
	//private HashMap<Player, Integer> skj = new HashMap<Player, Integer>();
	
	private static HashMap<String, Integer> climb = new HashMap<String, Integer>();
	private Multimap<UUID, LivingEntity> rage = ArrayListMultimap.create();
	private static HashMap<UUID, Integer> raget = new HashMap<UUID, Integer>();
	private String path = new File("").getAbsolutePath();
	
	Holding hold = Holding.getInstance();
	private HunSkillsData hsd;

	private static final Hunskills instance = new Hunskills ();
	public static Hunskills getInstance()
	{
		return instance;
	}

		
	public void er(PluginEnableEvent ev) 
	{
		HunSkillsData h = new HunSkillsData(HunSkillsData.loadData(path +"/plugins/RPGskills/HunSkillsData.data"));
		hsd = h;
	}
	
	
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
				Player p = (Player) e.getWhoClicked();
				if(climb.containsKey(p.getName())) {
					climb.remove(p.getName());
				}
				HuntingEffectrmv(p);
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

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		HunSkillsData h = new HunSkillsData(HunSkillsData.loadData(path +"/plugins/RPGskills/HunSkillsData.data"));
		hsd = h;
		
	}

	

	

	
	public void Webthrow(PlayerInteractEvent ev) 
	{
	    
		Player p = ev.getPlayer();
		Action a = ev.getAction();
		double sec =6*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 2) {
			
		if(((a==Action.RIGHT_CLICK_AIR || a==Action.RIGHT_CLICK_BLOCK)) && (p.isSneaking()) && hsd.Webthrow.getOrDefault(p.getUniqueId(),0)>=1)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE")&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK"))
			{
				if(tbcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (tbcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("그물투척 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
						}
						else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Webthrow").create());
						}
	                }
	                else // if timer is done
	                {
		        		
	                	tbcooldown.remove(p.getName());
	                	if(Proficiency.getpro(p)>=2) {

		                    ArrayList<Location> line = new ArrayList<Location>();
		                    for(double dou = -Math.PI/12; dou<= Math.PI/12; dou += Math.PI/60) {
			                    Location l = p.getEyeLocation().clone();
			                    l.setDirection(l.getDirection().rotateAroundY(dou));
			                    l.add(l.getDirection().normalize().multiply(25.1));
			                    line.add(l);
		                    }
							line.forEach(l -> {
			            		Snowball sn = p.launchProjectile(Snowball.class);
			            		sn.setItem(new ItemStack(Material.COBWEB));
			            		sn.setVelocity(l.getDirection().multiply(0.68));
			            		sn.setMetadata("web", new FixedMetadataValue(RMain.getInstance(), true));
			            		sn.setShooter(p);
			            		sn.setGlowing(true);
							});
	                	}
		            	else {
			        		Snowball sn = p.launchProjectile(Snowball.class);
			        		sn.setItem(new ItemStack(Material.COBWEB));
			        		sn.setVelocity(sn.getVelocity().multiply(0.68));
			        		sn.setMetadata("web", new FixedMetadataValue(RMain.getInstance(), true));
			        		sn.setShooter(p);
			        		sn.setGlowing(true);
		            	}
		                tbcooldown.put(p.getName(), System.currentTimeMillis()); 
	                }
	            }
	            else 
	            {

	            	if(Proficiency.getpro(p)>=2) {
	
	                    ArrayList<Location> line = new ArrayList<Location>();
	                    for(double dou = -Math.PI/12; dou<= Math.PI/12; dou += Math.PI/60) {
		                    Location l = p.getEyeLocation().clone();
		                    l.setDirection(l.getDirection().rotateAroundY(dou));
		                    l.add(l.getDirection().normalize().multiply(25.1));
		                    line.add(l);
	                    }
						line.forEach(l -> {
		            		Snowball sn = p.launchProjectile(Snowball.class);
		            		sn.setItem(new ItemStack(Material.COBWEB));
		            		sn.setVelocity(l.getDirection().multiply(0.68));
		            		sn.setMetadata("web", new FixedMetadataValue(RMain.getInstance(), true));
		            		sn.setShooter(p);
		            		sn.setGlowing(true);
						});
						
	            	}
	            	else {
		        		Snowball sn = p.launchProjectile(Snowball.class);
		        		sn.setItem(new ItemStack(Material.COBWEB));
		        		sn.setVelocity(sn.getVelocity().multiply(0.68));
		        		sn.setMetadata("web", new FixedMetadataValue(RMain.getInstance(), true));
		        		sn.setShooter(p);
		        		sn.setGlowing(true);
	            	}
	                tbcooldown.put(p.getName(), System.currentTimeMillis()); 
	            }
			}
		}
			
		}
	}

	
	
	public void Webthrow(ProjectileHitEvent ev) 
	{
		if(ev.getEntity().hasMetadata("web")) {
			Projectile pr = ev.getEntity();
			if(pr.getShooter() instanceof Player && ev.getHitEntity() instanceof LivingEntity) {
				Player p = (Player) pr.getShooter();
				LivingEntity e = (LivingEntity) ev.getHitEntity();
        		if (e instanceof Player) 
				{
        			
					Player p1 = (Player) e;
					if(Party.hasParty(p) && Party.hasParty(p1))	{
					if(Party.getParty(p).equals(Party.getParty(p1)))
						{
						return;
						}
					}
				}
        		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
				{
					LivingEntity le = (LivingEntity)e;
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 50, 1,1,1, 0.21, Material.COBWEB.createBlockData());
					p.playSound(le.getLocation(), Sound.BLOCK_WEEPING_VINES_BREAK, 1, 0);
					p.playSound(le.getLocation(), Sound.ENTITY_SPIDER_HURT, 1, 0);
					Holding.holding(p, le, 2l);
					le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 3, false, false));
					le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 3, false, false));
					if(Proficiency.getpro(p)>=1) {
						Holding.holding(p, le, 40l);
					}
				}
			}
		}
	}
	
	
	public void TurnOver(PlayerInteractEntityEvent ev) 
	{
			Player p = ev.getPlayer();
			final Entity e = ev.getRightClicked();
			double sec =2*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		    
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 2)	{
				
				if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE") &&!p.isSneaking()  && hsd.Chain.getOrDefault(p.getUniqueId(),0)>=1)
				{
					ev.setCancelled(true);
					if(chcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (chcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("뒤집기 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
							}
							else {
			                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use TurnOver").create());
							}
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    chcooldown.remove(p.getName());
		                    p.playSound(p.getLocation(), Sound.ENTITY_FISHING_BOBBER_RETRIEVE, 1, 2);
		                    if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
							{
								if (e instanceof Player) 
								{
									Player p1 = (Player) e;
									if(Party.hasParty(p) && Party.hasParty(p1))	{
									if(Party.getParty(p).equals(Party.getParty(p1)))
										{
											return;
										}
									}
								}
								LivingEntity le = (LivingEntity)e;
								le.getWorld().spawnParticle(Particle.SQUID_INK, e.getLocation(), 2);
								le.setVelocity(p.getLocation().getDirection());
					            Holding.superholding(p, le, 3l);
					            if(Proficiency.getpro(p)>=1) {
						            Holding.superholding(p, le, 10l);
					            }
								e.teleport(p.getLocation());
							}
			                chcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else 
		            {
	                    p.playSound(p.getLocation(), Sound.ENTITY_FISHING_BOBBER_RETRIEVE, 1, 2);
						if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
						{
							if (e instanceof Player) 
							{
								Player p1 = (Player) e;
								if(Party.hasParty(p) && Party.hasParty(p1))	{
								if(Party.getParty(p).equals(Party.getParty(p1)))
									{
										return;
									}
								}
							}
							LivingEntity le = (LivingEntity)e;
							le.getWorld().spawnParticle(Particle.SQUID_INK, e.getLocation(), 2);
							le.setVelocity(p.getLocation().getDirection());
				            Holding.superholding(p, le, 3l);
				            if(Proficiency.getpro(p)>=1) {
					            Holding.superholding(p, le, 10l);
				            }
							e.teleport(p.getLocation());
						}
		                chcooldown.put(p.getName(), System.currentTimeMillis()); 
		            }
					
				}
			}
	}
	
	
	public void Climb(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec =0*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

		if(p.getInventory().getItemInMainHand().getType().name().contains("RAIL") && p.getInventory().getItemInOffHand().getType().name().contains("RAIL") && !p.isOnGround()) {
			ev.setCancelled(true);
			p.setFallDistance(0);
			p.setGliding(true);
			ArmorStand ar = p.getWorld().spawn(p.getLocation(), ArmorStand.class);
			ar.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
			ar.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(),true));
			ar.setGlowing(true);
			ar.getEquipment().setArmorContents(p.getEquipment().getArmorContents());
			ar.setCustomName(p.getDisplayName());
			ar.setCustomNameVisible(true);
		}
		if(ClassData.pc.get(p.getUniqueId()) == 2 && hsd.Climb.getOrDefault(p.getUniqueId(),0)>=1 && p.isSneaking())	{
			
			if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE")&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK") && hsd.Climb.get(p.getUniqueId())>=1)
			{
				ev.setCancelled(true);
					if(djcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (djcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    djcooldown.remove(p.getName());
							if(climb.containsKey(p.getName())) {
								p.setGravity(true);
								climb.remove(p.getName());
								if(p.getLocale().equalsIgnoreCase("ko_kr")) {
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.DARK_GREEN+"등반 비활성화").color(ChatColor.DARK_GREEN).create());
								}
								else {
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.DARK_GREEN+"Climbing off").color(ChatColor.DARK_GREEN).create());
								}
							}
							else {
								climb.put(p.getName(),1);
								if(p.getLocale().equalsIgnoreCase("ko_kr")) {
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.DARK_GREEN+"등반 활성화").color(ChatColor.DARK_GREEN).create());
								}
								else {
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.DARK_GREEN+"Climbing on").color(ChatColor.DARK_GREEN).create());
								}
							}
			                djcooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else 
		            {
						if(climb.containsKey(p.getName())) {
							p.setGravity(true);
							climb.remove(p.getName());
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.DARK_GREEN+"등반 비활성화").color(ChatColor.DARK_GREEN).create());
							}
							else {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.DARK_GREEN+"Climbing off").color(ChatColor.DARK_GREEN).create());
							}
						}
						else {
							climb.put(p.getName(),1);
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.DARK_GREEN+"등반 활성화").color(ChatColor.DARK_GREEN).create());
							}
							else {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.DARK_GREEN+"Climbing on").color(ChatColor.DARK_GREEN).create());
							}
						}
		                djcooldown.put(p.getName(), System.currentTimeMillis()); 
		            }
				
			}
		}
	}
	

	
	public void Climb(PlayerMoveEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.getOrDefault(p.getUniqueId(),0) == 2 && climb.containsKey(p.getName()))	{
			if(!p.getTargetBlock(null, 1).isPassable()) {
				p.setGravity(false);
				p.setVelocity(p.getLocation().clone().add(0, 1, 0).toVector().subtract(p.getLocation().toVector()).normalize().multiply(0.35));
			}
			else {
				p.setGravity(true);
			}
		}
	}
	

	
	
	
	public void SuperJump(PlayerToggleSneakEvent e) 
	{
		
		Player p = (Player)e.getPlayer();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 2 )			{
			
			if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE") && Proficiency.getpro(p)>=1 && e.isSneaking()&& climb.containsKey(p.getName()))
			{
					int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
							p.getWorld().spawnParticle(Particle.BLOCK_DUST, p.getLocation(), 10, 1, 1, 1, Material.DIRT.createBlockData());
							if(p.hasPotionEffect(PotionEffectType.JUMP)) {
								if(p.getPotionEffect(PotionEffectType.JUMP).getAmplifier()+1>10) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 30, 10, false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 30, p.getPotionEffect(PotionEffectType.JUMP).getAmplifier()+1, false, false));
								}
		                	}
							else {
								p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 30, 0, false, false));
							}
		                }
					}, 0, 5); 
					sz.put(p, task);
			}
			else
			{
        		if(sz.containsKey(p)) {
        			Bukkit.getServer().getScheduler().cancelTask(sz.get(p));
        			sz.remove(p);
        		}
				
			}
		}
		if(e.isSneaking() && p.getInventory().getItemInMainHand().getType().name().contains("RAIL") && p.getInventory().getItemInOffHand().getType().name().contains("RAIL")) {

			int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
					if(p.hasPotionEffect(PotionEffectType.SPEED)) {
						if(p.getPotionEffect(PotionEffectType.SPEED).getAmplifier()+5>255) {
							p.playSound(p.getLocation(), Sound.ENTITY_MINECART_RIDING, 0.01f, 2);
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2, 255, false, false));
						}
						else {
							p.playSound(p.getLocation(), Sound.ENTITY_MINECART_RIDING, 0.01f, 0.00784f*p.getPotionEffect(PotionEffectType.SPEED).getAmplifier());
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2, p.getPotionEffect(PotionEffectType.SPEED).getAmplifier()+5, false, false));
						}
                	}
					else {
						p.playSound(p.getLocation(), Sound.ENTITY_MINECART_RIDING, 0.01f, 0);
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2, 0, false, false));
					}
                }
			}, 0, 1/5); 
			skj.put(p, task);
		}
		else {
    		if(skj.containsKey(p)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5, p.getPotionEffect(PotionEffectType.SPEED).getAmplifier()+1, false, false));
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 3,  p.getPotionEffect(PotionEffectType.SPEED).getAmplifier()/4, false, false));
				p.setGliding(true);
				p.playSound(p.getLocation(), Sound.ENTITY_SKELETON_HORSE_JUMP_WATER, 1f, 1.6f);
				p.playSound(p.getLocation(), Sound.ENTITY_GOAT_LONG_JUMP, 1f, 0);
    			Bukkit.getServer().getScheduler().cancelTask(skj.get(p));
    			skj.remove(p);
    		}
		}
	}

	HashMap<Player, Integer> skj = new HashMap<>();
	
	
	public void SuperJump(PlayerItemHeldEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 2 && p.getInventory().getItemInMainHand().getType().name().contains("_AXE") && sz.containsKey(p)){

			Bukkit.getServer().getScheduler().cancelTask(sz.get(p));
			sz.remove(p);
		}
	}
	
	
	
	
	
	public void Dodge(PlayerSwapHandItemsEvent ev) 
	{
			Player p = ev.getPlayer();
			double sec =2*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
			
			if(ClassData.pc.get(p.getUniqueId()) == 2 && hsd.Dodge.get(p.getUniqueId())>=1 && !p.isSneaking())	{
				
				if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE")&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK"))
				{
					ev.setCancelled(true);
					
	                Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 6).getLocation();
					l.setY(l.getY()+1);
					l.setDirection(p.getLocation().getDirection());
					if(l.getBlock().isPassable()) {
					if(djcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (djcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("회피 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
							}
							else {
			                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Dodge").create());
							}
		                }
		                else // if timer is done
		                {
		                    djcooldown.remove(p.getName());
							p.teleport(l);
							p.playSound(p.getLocation(), Sound.ENTITY_SPIDER_STEP, 1.0f, 1.5f);
							if(Proficiency.getpro(p)>=2) {
								posed.put(p.getName(),1);
							}
			                djcooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else 
		            {
						if(Proficiency.getpro(p)>=2) {
							posed.put(p.getName(),1);
						}
						p.teleport(l);
						p.playSound(p.getLocation(), Sound.ENTITY_SPIDER_STEP, 1.0f, 1.5f);
		                djcooldown.put(p.getName(), System.currentTimeMillis()); 
		            }}
					
				}
			}
	}

	
	public void Dodge(EntityDamageEvent d) 
	{		
        if (!(d.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) d.getEntity();
        
		
		
		
		if(d.getCause().equals(DamageCause.FALL)) 
		{
	        if(ClassData.pc.get(p.getUniqueId()) == 2&& hsd.Dodge.get(p.getUniqueId())>=1) {
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
	final private void HuntingEffectadd(Player p)
	{

		if(huntt.containsKey(p.getName())) {
			Bukkit.getServer().getScheduler().cancelTask(huntt.get(p.getName()));
			huntt.remove(p.getName());
		}
			hunting.put(p.getName(), 0);
		    int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		        @Override
		        public void run() {
					Holding.invur(p, 3l);
		    		p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 3, 0, false, false));
		    		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3, 1, false, false));
		    		p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 3, 0, false, false));
		        }
		    }, 1,1); 
		    huntt.put(p.getName(), task);
	}
	
	final private void HuntingEffectrmv(Player p)
	{
		if(huntt.containsKey(p.getName())) {
			Bukkit.getServer().getScheduler().cancelTask(huntt.get(p.getName()));
			huntt.remove(p.getName());
		}
		if(hunting.containsKey(p.getName()))
		{
			hunting.remove(p.getName());
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{사냥 종료}").color(ChatColor.DARK_RED).create());
			}
			else {
        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Hunting Quit").color(ChatColor.DARK_RED).create());
			}
		}
	}
	

	
	@SuppressWarnings("deprecation")
	public void HuntingStart(PlayerInteractEvent ev) 
	{
	    
		Player p = ev.getPlayer();
		Action a = ev.getAction();
		double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 2 && hsd.HuntingStart.get(p.getUniqueId())>=1) {
		if(((a==Action.RIGHT_CLICK_AIR || a==Action.RIGHT_CLICK_BLOCK)) && !(p.isOnGround()) && !(p.isSneaking()))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE") && !p.getInventory().getItemInMainHand().getType().name().contains("PICK")&& !hunting.containsKey(p.getName())&& Proficiency.getpro(p)<2)
			{
				if(hucooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (hucooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {

						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("사냥 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
						}
						else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Hunting").create());
						}
					}
	                else // if timer is done
	                {
		        		
	                    hucooldown.remove(p.getName());
		        		p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.5f);
		        		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 3);
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{사냥 시작}").color(ChatColor.DARK_RED).create());
						}
						else {
			        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{Hunting Start}").color(ChatColor.DARK_RED).create());
						}
		                HuntingEffectadd(p);
		                int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	if(hunting.containsKey(p.getName())) {
			    				HuntingEffectrmv(p);
			    				huntofft.remove(p.getName());
			    		        hucooldown.put(p.getName(), System.currentTimeMillis()); 
			    		        }
			                }
			            }, 70); 
		                huntofft.put(p.getName(), task);
	                }
	            }
	            else 
	            {
	        		p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.5f);
	        		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 3);
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{사냥 시작}").color(ChatColor.DARK_RED).create());
					}
					else {
		        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{Hunting Start}").color(ChatColor.DARK_RED).create());
					}
	                HuntingEffectadd(p);
    				if(Proficiency.getpro(p)>=2) {
	                	if(hunting.containsKey(p.getName())) {
	    				HuntingEffectrmv(p);
	                	}
	                	else {
			                HuntingEffectadd(p);
	                	}
    				}
	                int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	if(hunting.containsKey(p.getName())) {
		    				HuntingEffectrmv(p);
		    				huntofft.remove(p.getName());
		    		        hucooldown.put(p.getName(), System.currentTimeMillis()); 
		    		        }
		                }
		            }, 70); 
	                huntofft.put(p.getName(), task);
	            }
			}
		}
			
		}
	}

	
	@SuppressWarnings("deprecation")
	public void HuntingStart2(PlayerInteractEvent ev) 
	{
	    
		Player p = ev.getPlayer();
		Action a = ev.getAction();
		double sec =6*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 2) {
		if(((a==Action.RIGHT_CLICK_AIR || a==Action.RIGHT_CLICK_BLOCK)) && !(p.isOnGround()) && !(p.isSneaking()))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE") && !p.getInventory().getItemInMainHand().getType().name().contains("PICK")&& Proficiency.getpro(p)>=2 && !p.hasCooldown(Material.STRUCTURE_VOID))
			{
				if(hucooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (hucooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("사냥 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
						}
						else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Hunting").create());
						}
					}
	                else // if timer is done
	                {
		        		
	                    hucooldown.remove(p.getName());
	                    p.setCooldown(Material.STRUCTURE_VOID, 10);
	    				if(Proficiency.getpro(p)>=2) {
		                	if(hunting.containsKey(p.getName())) {
		    				HuntingEffectrmv(p);
			        		p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, 1.0f, 0f);
			        		p.getWorld().spawnParticle(Particle.ASH, p.getLocation(), 30,1,1,1);
		                	}
		                	else {
				                HuntingEffectadd(p);
				        		p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.5f);
				        		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 30,1,1,1);
								if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{사냥 시작}").color(ChatColor.DARK_RED).create());
								}
								else {
					        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{Hunting Start}").color(ChatColor.DARK_RED).create());
								}
		                	}
	    				}
	                }
	            }
	            else 
	            {
                    p.setCooldown(Material.STRUCTURE_VOID, 10);
    				if(Proficiency.getpro(p)>=2) {
	                	if(hunting.containsKey(p.getName())) {
	    				HuntingEffectrmv(p);
		        		p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, 1.0f, 0f);
		        		p.getWorld().spawnParticle(Particle.ASH, p.getLocation(), 30,1,1,1);
	                	}
	                	else {
			                HuntingEffectadd(p);
			        		p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.5f);
			        		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 30,1,1,1);
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{사냥 시작}").color(ChatColor.DARK_RED).create());
							}
							else {
				        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{Hunting Start}").color(ChatColor.DARK_RED).create());
							}
	                	}
    				}
	            }
			}
		}
			
		}
	}
	/*
	
	public void HuntingOFF(PlayerInteractEvent ev) 
	{
	    
		Player p = ev.getPlayer();
		Action a = ev.getAction();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 2) {
		if(((a==Action.LEFT_CLICK_BLOCK)))
		{
			if(hunting.containsKey(p.getName()))
			{
				HuntingEffectrmv(p);
		        hucooldown.put(p.getName(), System.currentTimeMillis()-2500); 
			}
		}
		}
		
	}
	*/
	
	
	public void Daze(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&&!d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		final LivingEntity le = (LivingEntity)d.getEntity();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 2 && hsd.Daze.get(p.getUniqueId())>=1&& p.getCooldown(Material.GLISTERING_MELON_SLICE)<=0)	
		{
				if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE")&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK") && p.isSneaking())
				{

					if((p.getAttackCooldown()==1|| ult2t.containsKey(p.getName())) && !le.hasMetadata("fake")&& !le.hasMetadata("portal")) 
					{
						if(lccooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
				        {
				            double timer = (lccooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
				            if(!(timer < 0)) // if timer is still more then 0 or 0
				            {

if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("참격 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
}
else {
	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Daze").create());
}
				            }
				            else // if timer is done
				            {
				                lccooldown.remove(p.getName());
								if (le instanceof Player) 
								{
									Player p1 = (Player) le;
									if(Party.hasParty(p) && Party.hasParty(p1))	{
									if(Party.getParty(p).equals(Party.getParty(p1)))
										{
											return;
										}
									}
								}
								le.setVelocity(p.getLocation().getDirection());
								d.setDamage(d.getDamage()*1.5 + hsd.Daze.get(p.getUniqueId())*1.68);
								p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0f, 2.0f);
								p.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, le.getLocation(), 5, 1, 0, 1);
								p.getWorld().spawnParticle(Particle.ASH, le.getLocation(), 200, 0.1, 1, 0.1);
								Holding.holding(p, le, (long) 10);
								if(Proficiency.getpro(p)>=1) {
			    					for(Entity e : le.getWorld().getNearbyEntities(le.getLocation(),2, 2, 2)) {
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
			    							LivingEntity le1 = (LivingEntity)e;
											Holding.holding(p, le1, (long) 10);
			    							
			    						}
			    						
			    					}
								}
								if(ult2t.containsKey(p.getName())) {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
								            lccooldown.put(p.getName(), System.currentTimeMillis()); 
						                }
									}, 5); 
								}
								else {
						            lccooldown.put(p.getName(), System.currentTimeMillis()); 
								}
				            }
				        }
				        else 
				        {
							
							
							if (le instanceof Player) 
							{
								Player p1 = (Player) le;
								if(Party.hasParty(p) && Party.hasParty(p1))	{
								if(Party.getParty(p).equals(Party.getParty(p1)))
									{
										return;
									}
								}
							}
							le.setVelocity(p.getLocation().getDirection());
							d.setDamage(d.getDamage()*1.5 + hsd.Daze.get(p.getUniqueId())*1.68);
							p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0f, 2.0f);
							p.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, le.getLocation(), 5, 1, 0, 1);
							p.getWorld().spawnParticle(Particle.ASH, le.getLocation(), 200, 1, 1, 1);
							Holding.holding(p, le, (long) 10);
							if(Proficiency.getpro(p)>=1) {
		    					for(Entity e : le.getWorld().getNearbyEntities(le.getLocation(),2, 2, 2)) {
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
		    							LivingEntity le1 = (LivingEntity)e;
										Holding.holding(p, le1, (long) 10);
		    							
		    						}
		    						
		    					}
							}
							if(ult2t.containsKey(p.getName())) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
							            lccooldown.put(p.getName(), System.currentTimeMillis()); 
					                }
								}, 5); 
							}
							else {
					            lccooldown.put(p.getName(), System.currentTimeMillis()); 
							}
				        }
							
					}
						
				}
		}
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public void SkullCrusher(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&&!d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		double sec =10*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		LivingEntity le = (LivingEntity)d.getEntity();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 2 && hsd.SkullCrusher.get(p.getUniqueId())>=1&& p.getCooldown(Material.GLISTERING_MELON_SLICE)<=0 && !p.isOnGround())	{
			if((p.getAttackCooldown()==1|| ult2t.containsKey(p.getName())) && !le.hasMetadata("fake")&& !le.hasMetadata("portal")) 
			{	
				
				if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE")&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK"))
					{
					
					if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            double timer = (smcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {

if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("두개골분쇄 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
}
else {
	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use SkullCrusher").create());
}			            }
			            else // if timer is done
			            {
			                smcooldown.remove(p.getName());
							if (le instanceof Player) 
							{
								Player p1 = (Player) le;
								if(Party.hasParty(p) && Party.hasParty(p1))	{
								if(Party.getParty(p).equals(Party.getParty(p1)))
									{
										return;
									}
								}
							}
							d.setDamage(d.getDamage()*2.5 + hsd.SkullCrusher.get(p.getUniqueId())*2.5);
							p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getEyeLocation(), 250, 1.52, 1.2, 1.52, Material.BONE_BLOCK.createBlockData());
							p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, le.getEyeLocation(), 5, 1.52, 1.2, 1.52);
							p.playSound(p.getLocation(), Sound.BLOCK_BONE_BLOCK_BREAK, 1.0f, 2f);
							p.playSound(p.getLocation(), Sound.BLOCK_NETHER_ORE_BREAK, 1.0f, 0f);
							p.playSound(p.getLocation(), Sound.BLOCK_NETHER_ORE_BREAK, 1.0f, 2f);
							p.playSound(p.getLocation(), Sound.BLOCK_BONE_BLOCK_BREAK, 1.0f, 0f);
							if(Proficiency.getpro(p)>=1) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() { 
					    				if(le.isDead() || le.getHealth()<=0) {
					    					for(Entity e : le.getWorld().getNearbyEntities(le.getLocation(),3, 3, 3)) {
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
													Holding.holding(p, le, (long) 25);
					    							
					    						}
					    						
					    					}
					    				}
					                }
					            }, 1/5);
							}
							if(ult2t.containsKey(p.getName())) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
							            smcooldown.put(p.getName(), System.currentTimeMillis()); 
					                }
								}, 5); 
							}
							else {
					            smcooldown.put(p.getName(), System.currentTimeMillis()); 
							}
			            }
			        }
			        else 
			        {
						if (le instanceof Player) 
						{
							Player p1 = (Player) le;
							if(Party.hasParty(p) && Party.hasParty(p1))	{
							if(Party.getParty(p).equals(Party.getParty(p1)))
								{
									return;
								}
							}
						}
						d.setDamage(d.getDamage()*2.5 + hsd.SkullCrusher.get(p.getUniqueId())*2.5);
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getEyeLocation(), 250, 1.52, 1.2, 1.52, Material.BONE_BLOCK.createBlockData());
						p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, le.getEyeLocation(), 5, 1.52, 1.2, 1.52);
						p.playSound(p.getLocation(), Sound.BLOCK_BONE_BLOCK_BREAK, 1.0f, 2f);
						p.playSound(p.getLocation(), Sound.BLOCK_NETHER_ORE_BREAK, 1.0f, 0f);
						p.playSound(p.getLocation(), Sound.BLOCK_NETHER_ORE_BREAK, 1.0f, 2f);
						p.playSound(p.getLocation(), Sound.BLOCK_BONE_BLOCK_BREAK, 1.0f, 0f);
						if(Proficiency.getpro(p)>=1) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() { 
				    				if(le.isDead() || le.getHealth()<=0) {
				    					for(Entity e : le.getWorld().getNearbyEntities(le.getLocation(),3, 3, 3)) {
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
												Holding.holding(p, le, (long) 25);
				    							
				    						}
				    						
				    					}
				    				}
				                }
				            }, 1/5);
						}
						if(ult2t.containsKey(p.getName())) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
						            smcooldown.put(p.getName(), System.currentTimeMillis()); 
				                }
							}, 5); 
						}
						else {
				            smcooldown.put(p.getName(), System.currentTimeMillis()); 
						}
			        }
					}
			}
		}
		}
	}


	
	public void ArmorDecrease(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity() instanceof Player && d.getDamager() instanceof LivingEntity) 
		{
			if(ClassData.pc.get(d.getEntity().getUniqueId()) == 2) {
				d.setDamage(d.getDamage()*1.6);
			}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof Player) 
		{
			Arrow ar = (Arrow)d.getDamager();
	
			if(ar.getShooter() instanceof LivingEntity) {
				if(ClassData.pc.get(d.getEntity().getUniqueId()) == 2) {
					d.setDamage(d.getDamage()*1.6);
				}
			}
		}
	}
	
	
	
	
	public void ULT(PlayerDropItemEvent ev)        
    {
	    
		Player p = (Player)ev.getPlayer();
		Item i = ev.getItemDrop();
		ItemStack is = i.getItemStack();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 2 && (is.getType().name().contains("_AXE"))&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK") && p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
				ev.setCancelled(true);
				if(aultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (aultcooldown.get(p.getName())/1000d + 50/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d)) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("갈망 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
	                	}
	                	else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Rage").create());
	                	}
		            }
	                else // if timer is done
	                {
	                    aultcooldown.remove(p.getName());
	                    smcooldown.remove(p.getName());
	                    lccooldown.remove(p.getName());
	                    p.playSound(p.getLocation(), Sound.AMBIENT_CAVE, 1.0f, 1.8f);
						p.getWorld().spawnParticle(Particle.DRAGON_BREATH, p.getLocation(), 100, 2, 2, 2);
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 2, false, false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 2, false, false));
						for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 8, 7, 8))
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
							if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
							{
								final LivingEntity le = (LivingEntity)e;
								{
									Holding.holding(p, le, 100l);
									le.getWorld().spawnParticle(Particle.REVERSE_PORTAL, le.getLocation(), 60,1,1,1);
									le.getWorld().spawnParticle(Particle.ASH, le.getLocation(), 60,1,1,1);
									le.getWorld().spawnParticle(Particle.SUSPENDED, le.getLocation(), 60,1,1,1);
									le.getWorld().spawnParticle(Particle.SOUL, le.getLocation(), 60,1,1,1);
									le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100, 2, false, false));
									rage.put(p.getUniqueId(), le);
								}
							}
						}
						int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								rage.removeAll(p.getUniqueId());
			                }
						}, 100); 
						raget.put(p.getUniqueId(),task);
						
		                aultcooldown.put(p.getName(), System.currentTimeMillis()); 
		            
	                }
	            }
	            else 
	            {
                    smcooldown.remove(p.getName());
                    lccooldown.remove(p.getName());
	            	p.playSound(p.getLocation(), Sound.AMBIENT_CAVE, 1.0f, 1.8f);
					p.getWorld().spawnParticle(Particle.DRAGON_BREATH, p.getLocation(), 100, 2, 2, 2);
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 2, false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 2, false, false));
					for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 8, 7, 8))
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
						if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
						{
							final LivingEntity le = (LivingEntity)e;
							{
								Holding.holding(p, le, 100l);
								le.getWorld().spawnParticle(Particle.REVERSE_PORTAL, le.getLocation(), 60,1,1,1);
								le.getWorld().spawnParticle(Particle.ASH, le.getLocation(), 60,1,1,1);
								le.getWorld().spawnParticle(Particle.SUSPENDED, le.getLocation(), 60,1,1,1);
								le.getWorld().spawnParticle(Particle.SOUL, le.getLocation(), 60,1,1,1);
								le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100, 2, false, false));
								rage.put(p.getUniqueId(), le);
							}
						}
					}
					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
							rage.removeAll(p.getUniqueId());
		                }
					}, 100); 
					raget.put(p.getUniqueId(),task);
	                aultcooldown.put(p.getName(), System.currentTimeMillis()); 
	            }
			}	
    }
	

	
	public void Ult(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&&!d.isCancelled()) 
		{
			Player p = (Player)d.getDamager();
			LivingEntity e = (LivingEntity)d.getEntity();
			if (e instanceof Player) 
			{
				Player p1 = (Player) e;
				if(Party.hasParty(p) && Party.hasParty(p1))	{
					if(Party.getParty(p).equals(Party.getParty(p1)))
					{
						return;
					}
				}
			}
		

			if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
			{
				if(ClassData.pc.get(p.getUniqueId()) == 2&& p.getCooldown(Material.GLISTERING_MELON_SLICE)<=0)	
				{
					if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE")&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK") && rage.containsEntry(p.getUniqueId(), e))
					{
						dset1(d, p, 1.35, 0.13);
						p.sendTitle(ChatColor.MAGIC + "RAGE", ChatColor.MAGIC + "RAGE",20,20,20);
						p.playSound(p.getLocation(), Sound.ITEM_NETHER_WART_PLANT, 1.0f, 0f);
						Bukkit.getServer().getScheduler().cancelTask(raget.get(p.getUniqueId()));
						if(ult2t.containsKey(p.getName())) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									rage.removeAll(p.getUniqueId());
									rage.get(p.getUniqueId()).forEach(le -> Holding.unhold(le));
				                }
							}, 2); 
						}
						else {
							rage.removeAll(p.getUniqueId());
							rage.get(p.getUniqueId()).forEach(le -> Holding.unhold(le));
						}
					}
				}
			}
		}
	}

	static private HashSet<Location> ULT2(Player p) {

		HashSet<Location> line = new HashSet<Location>();
    	HashSet<Location> fill = new HashSet<Location>();
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.5f);
    	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);

		p.getWorld().spawnParticle(Particle.SOUL, p.getLocation(), 400,8,5,8);
        for(double an = Math.PI/2.5; an>-Math.PI/2.5; an-=Math.PI/60) {
        	Location pl = p.getLocation();
        	pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(10));
        	line.add(pl);
        }
        line.forEach(l -> {
    		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l,5,0.1,0.1,0.1,0);
    		p.getWorld().spawnParticle(Particle.CRIT, l,5,0.1,0.1,0.1,0);
    		p.getWorld().spawnParticle(Particle.CRIT_MAGIC, l,5,0.1,0.1,0.1,0);
            for(int i = 0; i<10;i+=1) {
            	Location pl = p.getLocation();
            	Vector v = l.clone().toVector().subtract(pl.toVector()).normalize();
            	fill.add(pl.add(v.multiply(i)));
            }			          
        	
        });
        return fill;
	}
	
	
	public void ULT2(PlayerDropItemEvent ev)        
    {
	    
		Player p = (Player)ev.getPlayer();
		Item i = ev.getItemDrop();
		ItemStack is = i.getItemStack();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 2 && (is.getType().name().contains("_AXE"))&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK") && !p.isSneaking()&& p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
				if(ault2cooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (ault2cooldown.get(p.getName())/1000d + 25*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d)) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("집행 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
	                	}
	                	else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Execute").create());
	                	}
		            }
	                else // if timer is done
	                {
	                    ault2cooldown.remove(p.getName());

	                    smcooldown.remove(p.getName());
	                    lccooldown.remove(p.getName());
	                    final GameMode pgm = p.getGameMode();
		            	p.playSound(p.getLocation(), Sound.AMBIENT_CAVE, 1.0f, 1.8f);
		            	p.playSound(p.getLocation(), Sound.ENTITY_PHANTOM_SWOOP, 1.0f, 0f);
						p.getWorld().spawnParticle(Particle.DRAGON_BREATH, p.getLocation(), 100, 2, 2, 2);
						p.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, p.getLocation(), 100, 2, 2, 2);
	                    p.setGameMode(GameMode.SPECTATOR);
	                    Holding.invur(p, 80l);
						int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	HashSet<Location> fill = ULT2(p);
			                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
			                    fill.forEach(l ->{
			                    	for(Entity e: l.getWorld().getNearbyEntities(l,3,8,3)) {
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
											les.add(le);
										}
			                    	}
			                    });
								les.forEach(le ->
								{
									atk1(1.6, p, le);
									
								});
								
								p.setGameMode(pgm);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	ult2t.remove(p.getName());
					                }
								},3);
			                }
						}, 40); 
						ult2t.put(p.getName(),task);
						
		                ault2cooldown.put(p.getName(), System.currentTimeMillis()); 
		            
	                }
	            }
	            else 
	            {
                    smcooldown.remove(p.getName());
                    lccooldown.remove(p.getName());
                    final GameMode pgm = p.getGameMode();
	            	p.playSound(p.getLocation(), Sound.AMBIENT_CAVE, 1.0f, 1.8f);
	            	p.playSound(p.getLocation(), Sound.ENTITY_PHANTOM_SWOOP, 1.0f, 0f);
					p.getWorld().spawnParticle(Particle.DRAGON_BREATH, p.getLocation(), 100, 2, 2, 2);
					p.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, p.getLocation(), 100, 2, 2, 2);
                    p.setGameMode(GameMode.SPECTATOR);
                    Holding.invur(p, 80l);
					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	HashSet<Location> fill = ULT2(p);
		                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
		                    fill.forEach(l ->{
		                    	for(Entity e: l.getWorld().getNearbyEntities(l,3,8,3)) {
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
										les.add(le);
									}
		                    	}
		                    });
							les.forEach(le ->
							{
								atk1(1.6, p, le);
								
							});
							
							p.setGameMode(pgm);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	ult2t.remove(p.getName());
				                }
							},3);
		                }
					}, 40); 
					ult2t.put(p.getName(),task);
					
	                ault2cooldown.put(p.getName(), System.currentTimeMillis()); 
	            }
			}	
    }

	
	@SuppressWarnings("deprecation")
	public void ThrowCancel(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
		
			if(ClassData.pc.get(p.getUniqueId()) == 2 && ((is.getType().name().contains("_AXE"))) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
			}
	
    }
	
	
	public void Hunger(EntityPotionEffectEvent d) 
	{
		if(!(d.getEntity() instanceof Player)) {return;
		}
		
		else if(d.getEntity() instanceof Player) {
				Player p = (Player)d.getEntity();
					if(ClassData.pc.get(p.getUniqueId()) == 2) 
					{	
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {	
				                	if(p.hasPotionEffect(PotionEffectType.HUNGER)) {
										p.removePotionEffect(PotionEffectType.HUNGER);
				                	}
				                	if(Proficiency.getpro(p)>=2) {

					                	if(p.hasPotionEffect(PotionEffectType.BAD_OMEN))
						        		{
						        			p.removePotionEffect(PotionEffectType.BAD_OMEN);
						        		}
						        		if(p.hasPotionEffect(PotionEffectType.BLINDNESS))
						        		{
						        			p.removePotionEffect(PotionEffectType.BLINDNESS);
						        		}
						        		if(p.hasPotionEffect(PotionEffectType.CONFUSION))
						        		{
						        			p.removePotionEffect(PotionEffectType.CONFUSION);
						        		}
						        		if(p.hasPotionEffect(PotionEffectType.HUNGER))
						        		{
						        			p.removePotionEffect(PotionEffectType.HUNGER);
						        		}
						        		if(p.hasPotionEffect(PotionEffectType.POISON))
						        		{
						        			p.removePotionEffect(PotionEffectType.POISON);
						        		}
						        		if(p.hasPotionEffect(PotionEffectType.SLOW))
						        		{
						        			p.removePotionEffect(PotionEffectType.SLOW);
						        		}
						        		if(p.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
						        		{
						        			p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
						        		}
						        		if(p.hasPotionEffect(PotionEffectType.UNLUCK))
						        		{
						        			p.removePotionEffect(PotionEffectType.UNLUCK);
						        		}
						        		if(p.hasPotionEffect(PotionEffectType.WEAKNESS))
						        		{
						        			p.removePotionEffect(PotionEffectType.WEAKNESS);
						        		}
						        		if(p.hasPotionEffect(PotionEffectType.WITHER))
						        		{
						        			p.removePotionEffect(PotionEffectType.WITHER);
						        		}
						        		p.setFireTicks(0);
					                	
				                	}
				                }
							}, 5); 
					}	
		}
	}
	
	
	
	
	
	public void Atrocity(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&&!d.isCancelled() && d.getDamage()>0) 
		{
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			if (le instanceof Player) 
			{
				Player p1 = (Player) le;
				if(Party.hasParty(p) && Party.hasParty(p1))	{
					if(Party.getParty(p).equals(Party.getParty(p1)))
					{
						return;
					}
				}
			}
		
		
			if(ClassData.pc.get(p.getUniqueId()) == 2)	
			{
				
				if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE")&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK") && !ult2t.containsKey(p.getName()))
				{
					if(p.getAttackCooldown()==1 && p.getCooldown(Material.GLISTERING_MELON_SLICE)<=0&& !le.hasMetadata("fake")&& !le.hasMetadata("portal")) 
					{
        				Location el = le.getLocation().add(0, 0.1, 0);
        				if(!el.getBlock().isPassable()) {
        					el.add(0, 1.1, 0);
        				}
        				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
        				{
        	         	@SuppressWarnings("deprecation")
						@Override
        		                public void run() 
    	         				{	

		    						p.setCooldown(Material.GLISTERING_MELON_SLICE, 3);
		    						atk0(0d, le.getMaxHealth()*(0.05+hsd.Atrocity.get(p.getUniqueId())*0.0002), p, le);
    				            }
        	            }, 3);
							ArrayList<Location> line = new ArrayList<Location>();
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 1.5f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0.5f);
		                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0f, 1.5f);
		                    for(double an = Math.PI/6; an>-Math.PI/6; an-=Math.PI/180) {
		                    	Location pl = p.getEyeLocation();
		                    	pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(3));
		                    	line.add(pl);
		                    }
		                    line.forEach(l -> {
				        		p.getWorld().spawnParticle(Particle.CRIT, l,5,0.1,0.1,0.1,0);
				        		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l,3,0.1,0.1,0.1,0, Material.NETHER_QUARTZ_ORE.createBlockData());
		                    	
		                    });
					}
				}
			}
		}
	}

	
	public void Backattack(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&&!d.isCancelled()) 
		{
			Player p = (Player)d.getDamager();
			LivingEntity e = (LivingEntity)d.getEntity();
			if (e instanceof Player) 
			{
				Player p1 = (Player) e;
				if(Party.hasParty(p) && Party.hasParty(p1))	{
					if(Party.getParty(p).equals(Party.getParty(p1)))
					{
						return;
					}
				}
			}
		
		
			if(ClassData.pc.get(p.getUniqueId()) == 2 && hsd.Backattack.get(p.getUniqueId()) >= 1&& !e.hasMetadata("fake")&& !e.hasMetadata("portal"))	
			{
				
				if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE")&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK"))
				{
					if(p.getAttackCooldown()==1) 
					{
							
						if (Math.abs(e.getLocation().getDirection().angle(p.getLocation().getDirection())) <= Math.PI/3+p.getLevel()/10 || Proficiency.getpro(p)>=2) 
						{
							d.setDamage(d.getDamage()*1.5);
							p.getWorld().spawnParticle(Particle.SPELL_WITCH, e.getLocation(), 100, 0.1, 1, 0.1);
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("암습").bold(true).create());
							}
							else {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("BackAttack").bold(true).create());
							}
						}
							
						
					}
				}
			}
		}
	}
/*
	
	public void Swing(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity e = (LivingEntity) d.getEntity();
		
		
		if (e instanceof Player) 
		{
			Player p1 = (Player) e;
			 {
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
		if(ClassData.pc.get(p.getUniqueId()) == 2 && p.getCooldown(Material.GLISTERING_MELON_SLICE)<=0)	
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE")&& !p.getInventory().getItemInMainHand().getType().name().contains("SHIELD")&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD)) {
				
				if(Proficiency.getpro(p)>=2&& !p.hasCooldown(Material.YELLOW_TERRACOTTA)) {
					p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
					e.damage(0,p);
					for(Entity e1 : e.getNearbyEntities(2, 2, 2)) {
	            		if (e1 instanceof Player) 
						{
							
							Player p1 = (Player) e1;
							if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
							if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
								{
								continue;
								}
							}
						}
	            		if ((!(e1 == p))&& !(e1 == e)&&e1 instanceof LivingEntity&& !(e1.hasMetadata("fake"))&& !(e1.hasMetadata("portal"))) 
						{
							LivingEntity le1 = (LivingEntity)e1;
							if(le1 instanceof EnderDragon) {
			                    Arrow firstarrow = p.launchProjectile(Arrow.class);
			                    firstarrow.setDamage(0);
			                    firstarrow.remove();
								Arrow enar = (Arrow) p.getWorld().spawn(le1.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
									ar.setShooter(p);
									ar.setCritical(false);
									ar.setSilent(true);
									ar.setPickupStatus(PickupStatus.DISALLOWED);
									ar.setVelocity(le1.getLocation().clone().add(0, -1, 0).toVector().subtract(le1.getLocation().toVector()).normalize().multiply(2.6));
								});
								enar.setDamage(d.getDamage());
							}
							le1.damage(d.getDamage(),p);
						}
					}
				}
			}

		}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
			Arrow a = (Arrow)d.getDamager();
			LivingEntity e = (LivingEntity) d.getEntity();
	
			
			
			if(a.getShooter() instanceof Player) {
				Player p = (Player) a.getShooter();
				if(ClassData.pc.get(p.getUniqueId()) == 2 && p.getCooldown(Material.GLISTERING_MELON_SLICE)<=0) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE")&& p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
					{

						if(Proficiency.getpro(p)>=2&& !p.hasCooldown(Material.YELLOW_TERRACOTTA)) {
							p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
							e.damage(0,p);
							for(Entity e1 : e.getNearbyEntities(2, 2, 2)) {
			            		if (e1 instanceof Player) 
								{
									
									Player p1 = (Player) e1;
									if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
									if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
										{
										continue;
										}
									}
								}
			            		if ((!(e1 == p))&& !(e1 == e)&&e1 instanceof LivingEntity&& !(e1.hasMetadata("fake"))&& !(e1.hasMetadata("portal"))) 
								{
									LivingEntity le1 = (LivingEntity)e1;
									if(le1 instanceof EnderDragon) {
					                    Arrow firstarrow = p.launchProjectile(Arrow.class);
					                    firstarrow.setDamage(0);
					                    firstarrow.remove();
										Arrow enar = (Arrow) p.getWorld().spawn(le1.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
											ar.setShooter(p);
											ar.setCritical(false);
											ar.setSilent(true);
											ar.setPickupStatus(PickupStatus.DISALLOWED);
											ar.setVelocity(le1.getLocation().clone().add(0, -1, 0).toVector().subtract(le1.getLocation().toVector()).normalize().multiply(2.6));
										});
										enar.setDamage(d.getDamage());
									}
									le1.damage(d.getDamage(),p);
								}
							}
						}
					}
				}
			}
		}
	}
	*/
	
	
	public void Equipment(PlayerItemDamageEvent e) 
	{
	    
		Player p = e.getPlayer();

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 2)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE") && !p.getInventory().getItemInOffHand().getType().name().contains("SHIELD")&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
			{
					e.setCancelled(true);
			}
		}
		
	}

	
	public void Damagegetter(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity) d.getEntity();
		
		
		if (le instanceof Player) 
		{
			Player p1 = (Player) le;
			if(Party.hasParty(p) && Party.hasParty(p1))	{
			if(Party.getParty(p).equals(Party.getParty(p1)))
				{
				d.setCancelled(true);
					return;
				}
			}
		}
		if(ClassData.pc.get(p.getUniqueId()) == 2 && p.getCooldown(Material.GLISTERING_MELON_SLICE)<=0&& !le.hasMetadata("fake")&& !le.hasMetadata("portal"))	
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE")&& !p.getInventory().getItemInMainHand().getType().name().contains("SHIELD")&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD)) {

				dset2(d, p, 1d, le, 5);
				if(le.hasMetadata("leader") || le.hasMetadata("boss")) {
					d.setDamage(d.getDamage()*2.5);
				}
				if(hunting.containsKey(p.getName()))
				{
					if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE"))
					{
						
						if(p.getAttackCooldown() >=1 || ult2t.containsKey(p.getName())) {

							dset1(d, p, 1.15 *(1+ hsd.HuntingStart.get(p.getUniqueId())*0.04)* (1+hsd.Atrocity.get(p.getUniqueId())*0.05) , 0.01);
						}
						else {
							dset1(d, p, (1+ hsd.HuntingStart.get(p.getUniqueId())*0.04)* (1+hsd.Atrocity.get(p.getUniqueId())*0.015) , 0.005);
						}
					}
					if(ult2t.containsKey(p.getName())) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								HuntingEffectrmv(p);
								le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20,20,false,false));
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() { 
				    					hucooldown.remove(p.getName());
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() { 
							    				if(le.isDead() || le.getHealth()<=0) {
								    				if(Proficiency.getpro(p)>=2) {
								    					hucooldown.remove(p.getName());
										                HuntingEffectadd(p);
										        		p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.5f);
										        		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 30,1,1,1);
														if(p.getLocale().equalsIgnoreCase("ko_kr")) {
											        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{사냥 시작}").color(ChatColor.DARK_RED).create());
														}
														else {
											        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{Hunting Start}").color(ChatColor.DARK_RED).create());
														}
								    				}
							    				}
							                }
							            }, 1/5);
					                }
					            }, 1/5);
			                }
						}, 3); 
					}
					else {
						HuntingEffectrmv(p);
						le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20,20,false,false));
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() { 
			    				if(le.isDead() || le.getHealth()<=0) {
			    					hucooldown.put(p.getName(), System.currentTimeMillis()-2000);
				    				if(Proficiency.getpro(p)>=2) {
				    					hucooldown.remove(p.getName());
						                HuntingEffectadd(p);
						        		p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.5f);
						        		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 30,1,1,1);
										if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{사냥 시작}").color(ChatColor.DARK_RED).create());
										}
										else {
							        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{Hunting Start}").color(ChatColor.DARK_RED).create());
										}
				    				}
			    				}
			    				else {
				    				if(Proficiency.getpro(p)>=1) {
				    					hucooldown.put(p.getName(), System.currentTimeMillis()-2000);
				    				}
				    				else {
				        		        hucooldown.put(p.getName(), System.currentTimeMillis()); 
				    				}
			    				}
			                }
			            }, 1/5);
					}
				}	
				if(posed.containsKey(p.getName())) {
					d.setDamage(d.getDamage()*1.5);
					
					if(ult2t.containsKey(p.getName())) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								posed.remove(p.getName());
			                }
						}, 3); 
					}
					else {
						posed.remove(p.getName());
					}
				}
				if(Proficiency.getpro(p)>=1) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() { 
		    				if(le.isDead() || le.getHealth()<=0) {
		    					djcooldown.remove(p.getName());
		    				}
		                }
		            }, 1/5);
				}
				if(Proficiency.getpro(p)>=2) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() { 
		    				if(le.isDead() || le.getHealth()<=0) {
		    					smcooldown.remove(p.getName());
		    					lccooldown.remove(p.getName());
		    				}
		                }
		            }, 1/5);
				}
			}
			else {

				if(hunting.containsKey(p.getName()))
				{
					if(ult2t.containsKey(p.getName())) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								HuntingEffectrmv(p);
								le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20,20,false,false));
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() { 
				    					hucooldown.remove(p.getName());
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() { 
							    				if(le.isDead() || le.getHealth()<=0) {
								    				if(Proficiency.getpro(p)>=2) {
								    					hucooldown.remove(p.getName());
										                HuntingEffectadd(p);
										        		p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.5f);
										        		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 30,1,1,1);
														if(p.getLocale().equalsIgnoreCase("ko_kr")) {
											        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{사냥 시작}").color(ChatColor.DARK_RED).create());
														}
														else {
											        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{Hunting Start}").color(ChatColor.DARK_RED).create());
														}
								    				}
							    				}
							                }
							            }, 1/5);
					                }
					            }, 1/5);
			                }
						}, 3); 
					}
					else {
						HuntingEffectrmv(p);
						le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20,20,false,false));
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() { 
			    				if(le.isDead() || le.getHealth()<=0) {
			    					hucooldown.put(p.getName(), System.currentTimeMillis()-2000);
				    				if(Proficiency.getpro(p)>=2) {
				    					hucooldown.remove(p.getName());
						                HuntingEffectadd(p);
						        		p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.5f);
						        		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 30,1,1,1);
										if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{사냥 시작}").color(ChatColor.DARK_RED).create());
										}
										else {
							        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{Hunting Start}").color(ChatColor.DARK_RED).create());
										}
				    				}
			    				}
			    				else {
				    				if(Proficiency.getpro(p)>=1) {
				    					hucooldown.put(p.getName(), System.currentTimeMillis()-2000);
				    				}
				    				else {
				        		        hucooldown.put(p.getName(), System.currentTimeMillis()); 
				    				}
			    				}
			                }
			            }, 1/5);
					}
				}	
			}

		}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
			Arrow a = (Arrow)d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
	
			
			
			if(a.getShooter() instanceof Player) {
				Player p = (Player) a.getShooter();
				if(ClassData.pc.get(p.getUniqueId()) == 2 && p.getCooldown(Material.GLISTERING_MELON_SLICE)<=0&& !le.hasMetadata("fake")&& !le.hasMetadata("portal")) {
					dset2(d, p, 1d, le, 5);
					if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD&& p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
					{
							if(le.hasMetadata("leader") || le.hasMetadata("boss")) {
								d.setDamage(d.getDamage()*2.5);
							}
							if(hunting.containsKey(p.getName()))
							{
								if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE"))
								{

									if(p.getAttackCooldown() >=1 || ult2t.containsKey(p.getName())) {

										dset1(d, p, 1.15 *(1+ hsd.HuntingStart.get(p.getUniqueId())*0.04)* (1+hsd.Atrocity.get(p.getUniqueId())*0.05) , 0.01);
									}
									else {
										dset1(d, p, (1+ hsd.HuntingStart.get(p.getUniqueId())*0.04)* (1+hsd.Atrocity.get(p.getUniqueId())*0.015) , 0.005);
									}
								}
								if(ult2t.containsKey(p.getName())) {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
											HuntingEffectrmv(p);
											le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20,20,false,false));
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() { 
							    					hucooldown.remove(p.getName());
													Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										                @Override
										                public void run() { 
										    				if(le.isDead() || le.getHealth()<=0) {
										    					hucooldown.put(p.getName(), System.currentTimeMillis()-2000);
											    				if(Proficiency.getpro(p)>=2) {
											    					hucooldown.remove(p.getName());
													                HuntingEffectadd(p);
													        		p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.5f);
													        		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 30,1,1,1);
																	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
														        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{사냥 시작}").color(ChatColor.DARK_RED).create());
																	}
																	else {
														        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{Hunting Start}").color(ChatColor.DARK_RED).create());
																	}
											    				}
										    				}
										    				else {
											    				if(Proficiency.getpro(p)>=1) {
											    					hucooldown.put(p.getName(), System.currentTimeMillis()-2000);
											    				}
											    				else {
											        		        hucooldown.put(p.getName(), System.currentTimeMillis()); 
											    				}
										    				}
										                }
										            }, 1/5);
								                }
								            }, 1/5);
						                }
									}, 3); 
								}
								else {
									HuntingEffectrmv(p);
									le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20,20,false,false));
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() { 
						    				if(le.isDead() || le.getHealth()<=0) {
						    					hucooldown.put(p.getName(), System.currentTimeMillis()-2000);
							    				if(Proficiency.getpro(p)>=2) {
							    					hucooldown.remove(p.getName());
									                HuntingEffectadd(p);
									        		p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.5f);
									        		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 30,1,1,1);
													if(p.getLocale().equalsIgnoreCase("ko_kr")) {
										        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{사냥 시작}").color(ChatColor.DARK_RED).create());
													}
													else {
										        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{Hunting Start}").color(ChatColor.DARK_RED).create());
													}
							    				}
						    				}
						    				else {
							    				if(Proficiency.getpro(p)>=1) {
							    					hucooldown.put(p.getName(), System.currentTimeMillis()-2000);
							    				}
							    				else {
							        		        hucooldown.put(p.getName(), System.currentTimeMillis()); 
							    				}
						    				}
						                }
						            }, 1/5);
								}
							}	
							if(posed.containsKey(p.getName())) {
								d.setDamage(d.getDamage()*1.5);
								if(ult2t.containsKey(p.getName())) {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
											posed.remove(p.getName());
						                }
									}, 3); 
								}
								else {
									posed.remove(p.getName());
								}
							}
							if(Proficiency.getpro(p)>=1) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() { 
					    				if(le.isDead() || le.getHealth()<=0) {
					    					djcooldown.remove(p.getName());
					    				}
					                }
					            }, 1/5);
							}
							if(Proficiency.getpro(p)>=2) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() { 
					    				if(le.isDead() || le.getHealth()<=0) {
					    					smcooldown.remove(p.getName());
					    					lccooldown.remove(p.getName());
					    				}
					                }
					            }, 1/5);
							}
						}
						else {
							d.setDamage(d.getDamage());

							if(hunting.containsKey(p.getName()))
							{
								if(ult2t.containsKey(p.getName())) {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
											HuntingEffectrmv(p);
											le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20,20,false,false));
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() { 
								    				if(le.isDead() || le.getHealth()<=0) {
								    					hucooldown.put(p.getName(), System.currentTimeMillis()-2000);
									    				if(Proficiency.getpro(p)>=2) {
									    					hucooldown.remove(p.getName());
											                HuntingEffectadd(p);
											        		p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.5f);
											        		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 30,1,1,1);
															if(p.getLocale().equalsIgnoreCase("ko_kr")) {
												        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{사냥 시작}").color(ChatColor.DARK_RED).create());
															}
															else {
												        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{Hunting Start}").color(ChatColor.DARK_RED).create());
															}
									    				}
								    				}
								    				else {
									    				if(Proficiency.getpro(p)>=1) {
									    					hucooldown.put(p.getName(), System.currentTimeMillis()-2000);
									    				}
									    				else {
									        		        hucooldown.put(p.getName(), System.currentTimeMillis()); 
									    				}
								    				}
								                }
								            }, 1/5);
						                }
									}, 3); 
								}
								else {
									HuntingEffectrmv(p);
									le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20,20,false,false));
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() { 
						    				if(le.isDead() || le.getHealth()<=0) {
						    					hucooldown.put(p.getName(), System.currentTimeMillis()-2000);
							    				if(Proficiency.getpro(p)>=2) {
							    					hucooldown.remove(p.getName());
									                HuntingEffectadd(p);
									        		p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.5f);
									        		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 30,1,1,1);
													if(p.getLocale().equalsIgnoreCase("ko_kr")) {
										        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{사냥 시작}").color(ChatColor.DARK_RED).create());
													}
													else {
										        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{Hunting Start}").color(ChatColor.DARK_RED).create());
													}
							    				}
						    				}
						    				else {
							    				if(Proficiency.getpro(p)>=1) {
							    					hucooldown.put(p.getName(), System.currentTimeMillis()-2000);
							    				}
							    				else {
							        		        hucooldown.put(p.getName(), System.currentTimeMillis()); 
							    				}
						    				}
						                }
						            }, 1/5);
								}
							}	
						}
				}
			}
		}
	}
	
	
}



