package io.github.chw3021.forger;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.party.PartyData;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

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
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Forskills implements Listener, Serializable {
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 6779511048929362121L;
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> stcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private HashMap<Player, Integer> mga = new HashMap<Player, Integer>();
	private HashMap<Player, Integer> mgar = new HashMap<Player, Integer>();
	private HashMap<Player, Integer> mgat = new HashMap<Player, Integer>();
	private Multimap<Player, LivingEntity> hesht = ArrayListMultimap.create();
	private HashMap<LivingEntity, Integer> heshc = new HashMap<LivingEntity, Integer>();
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private ForSkillsData fsd = new ForSkillsData(ForSkillsData.loadData(path +"/plugins/RPGskills/ForSkillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		ForSkillsData f = new ForSkillsData(ForSkillsData.loadData(path +"/plugins/RPGskills/ForSkillsData.data"));
		fsd =f;
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
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Forskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				ForSkillsData f = new ForSkillsData(ForSkillsData.loadData(path +"/plugins/RPGskills/ForSkillsData.data"));
				fsd =f;
			}
			
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		ForSkillsData f = new ForSkillsData(ForSkillsData.loadData(path +"/plugins/RPGskills/ForSkillsData.data"));
		fsd =f;
		
	}

	@EventHandler
	public void MachineGun(PlayerItemHeldEvent ev) 
	{
		Player p = ev.getPlayer();
		if(playerclass.get(p.getUniqueId()) == 16 && (p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") && p.isSneaking())){
			ev.setCancelled(true);
			if(mgat.containsKey(p)) {
				if(ev.getNewSlot()>ev.getPreviousSlot()) {
					mgat.put(p, 0);
                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"Arrow Selected").create());
                	return;
					
				}
				else if(ev.getNewSlot()<ev.getPreviousSlot()){
					mgat.put(p, 1);
                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"Bullet Selected").create());
                	return;
				}
			}
			else {
				mgat.put(p, 0);
            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"Arrow Selected").create());
            	return;
			}
		}
	}

	@EventHandler
	public void MachineGun(ProjectileHitEvent ev) 
	{
		
		if(ev.getEntity().hasMetadata("mgbul") && ev.getHitEntity() instanceof LivingEntity)
		{
			Projectile sn = ev.getEntity();
			Player p = (Player) sn.getShooter();
			LivingEntity le = (LivingEntity) ev.getHitEntity();
			
			p.setSprinting(true);
        	le.damage(player_damage.get(p.getName())*0.05*(1+fsd.MachineGun.get(p.getUniqueId())*0.09), p);
			p.setSprinting(false);
			
		}
	}

	@EventHandler
	public void MachineGun(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && p.isSneaking())
		{
        
		
		
		
		if(playerclass.get(p.getUniqueId()) == 16)
		{
				ev.setCancelled(true);
                if(mga.containsKey(p)) {
                	mga.compute(p, (k,v) -> --v);
                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("MachineGun("+mga.get(p).toString()+"/200)").create());
                	p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.76f, 1.6f);
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,20,3,false,false));
					if(!mgat.containsKey(p) || mgat.get(p) == 0) {
	                	Arrow sn = (Arrow) p.launchProjectile(Arrow.class);
	                	sn.setDamage(player_damage.get(p.getName())*0.05*(1+fsd.MachineGun.get(p.getUniqueId())*0.0067)*0.02);
	                	sn.setShooter(p);
	                	sn.setKnockbackStrength(0);
	                	sn.setPickupStatus(PickupStatus.DISALLOWED);
	                	sn.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(100));
						sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						sn.setMetadata("mg of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						sn.setInvulnerable(true);
						sn.setPierceLevel(3);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	sn.remove();
			                }
	            	   }, 5);
					}
					else if(mgat.get(p) == 1) {
	                	Snowball sn = (Snowball) p.launchProjectile(Snowball.class);
	                	sn.setItem(new ItemStack(Material.IRON_NUGGET));
	                	sn.setShooter(p);
	                	sn.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(90));
						sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						sn.setMetadata("mgbul", new FixedMetadataValue(RMain.getInstance(), true));
						sn.setInvulnerable(true);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	sn.remove();
			                }
	            	   }, 5);
					}
                	if(mga.get(p)<=0) {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("MachineGun Reloading...").create());
                		mga.remove(p);
                		mgar.put(p, 1);
	                	p.playSound(p.getLocation(), Sound.BLOCK_TRIPWIRE_CLICK_ON, 0.76f, 1.6f);
	                	p.playSound(p.getLocation(), Sound.BLOCK_TRIPWIRE_CLICK_ON, 1f, 0f);
	                	p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_START, 1f, 0f);
    					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    		                @Override
    		                public void run() 
    		                {
			                	p.playSound(p.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 1f, 2f);
			                	p.playSound(p.getLocation(), Sound.BLOCK_TRIPWIRE_CLICK_OFF, 1f, 0f);
			                	p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_END, 1f, 0f);
    		                	mgar.remove(p);
			                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Reloaded!").create());
    		                }
                	   }, 75-fsd.Development.get(p.getUniqueId())/2);
                	}
                }
                else if(!mga.containsKey(p)&&mgar.containsKey(p)){
                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("MachineGun Reloading...").create());
                }
                else if(!mga.containsKey(p)&&!mgar.containsKey(p)){
                	mga.put(p, 200);
                }
	        }
		}
	}



	@EventHandler
	public void LightningCannon(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
		{
		Action ac = ev.getAction();
		int sec = 10;
		
		if(playerclass.get(p.getUniqueId()) == 16) {
		if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{

				if(gdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (gdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use LightningCannon").create());
	                }
	                else // if timer is done
	                {
	                    gdcooldown.remove(p.getName()); // removing player from HashMap
	                    p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 2);

	                    ArrayList<Location> line1 = new ArrayList<Location>();
	                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    for(double d = 0.1; d <= 45.1; d += 0.2) {
		                    Location pl = p.getEyeLocation().clone();
							pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
							line1.add(pl);
	                    }
	                    for(Location l : line1) {
	                    	p.getWorld().spawnParticle(Particle.FLASH, l, 1,0.1,0.1,0.1,0);
	                    }
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								p.setSprinting(true);
			                    ArrayList<Location> line = new ArrayList<Location>();
			                    for(double d = 0.1; d <= 45.1; d += 0.2) {
				                    Location pl = p.getEyeLocation().clone();
									pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
									line.add(pl);
			                    }
			                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 2);
			                    for(Location l : line) {
			                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l,5, 0.25,0.25,0.25,0, Material.WHITE_GLAZED_TERRACOTTA.createBlockData());
			                    	p.getWorld().spawnParticle(Particle.SNOWBALL, l,5, 0.25,0.25,0.25,0);

			                    	for (Entity a : p.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5))
									{
										if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
										{
											if (a instanceof Player) 
											{
												
												Player p1 = (Player) a;
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
											LivingEntity le = (LivingEntity)a;
											les.add(le);
										}
									}
			                    }
								p.setSprinting(false);			
			                }
						}, 10);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								for(LivingEntity le: les) {
									if(le instanceof EnderDragon) {
					                    Arrow firstarrow = p.launchProjectile(Arrow.class);
					                    firstarrow.setDamage(0);
					                    firstarrow.remove();
										Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
											ar.setShooter(p);
											ar.setCritical(false);
											ar.setSilent(true);
											ar.setPickupStatus(PickupStatus.DISALLOWED);
											ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
										});
										enar.setDamage(player_damage.get(p.getName())*0.66*(1+fsd.LightningCannon.get(p.getUniqueId())*0.032));								
									}
									p.setSprinting(true);
									p.getWorld().strikeLightningEffect(le.getLocation());
									le.damage(0,p);
									le.damage(player_damage.get(p.getName())*0.66*(1+fsd.LightningCannon.get(p.getUniqueId())*0.032),p);
									le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,60,1,false,false));
									p.setSprinting(false);			
								}	
			                }
						}, 10);
						gdcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 2);

                    ArrayList<Location> line1 = new ArrayList<Location>();
                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                    for(double d = 0.1; d <= 45.1; d += 0.2) {
	                    Location pl = p.getEyeLocation().clone();
						pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
						line1.add(pl);
                    }
                    for(Location l : line1) {
                    	p.getWorld().spawnParticle(Particle.FLASH, l, 1,0.1,0.1,0.1,0);
                    }
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
							p.setSprinting(true);
		                    ArrayList<Location> line = new ArrayList<Location>();
		                    for(double d = 0.1; d <= 45.1; d += 0.2) {
			                    Location pl = p.getEyeLocation().clone();
								pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
								line.add(pl);
		                    }
		                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 2);
		                    for(Location l : line) {
		                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l,5, 0.25,0.25,0.25,0, Material.WHITE_GLAZED_TERRACOTTA.createBlockData());
		                    	p.getWorld().spawnParticle(Particle.SNOWBALL, l,5, 0.25,0.25,0.25,0);

		                    	for (Entity a : p.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5))
								{
									if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
									{
										if (a instanceof Player) 
										{
											
											Player p1 = (Player) a;
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
										LivingEntity le = (LivingEntity)a;
										les.add(le);
									}
								}
		                    }
							p.setSprinting(false);			
		                }
					}, 10);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
							for(LivingEntity le: les) {
								if(le instanceof EnderDragon) {
				                    Arrow firstarrow = p.launchProjectile(Arrow.class);
				                    firstarrow.setDamage(0);
				                    firstarrow.remove();
									Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
										ar.setShooter(p);
										ar.setCritical(false);
										ar.setSilent(true);
										ar.setPickupStatus(PickupStatus.DISALLOWED);
										ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
									});
									enar.setDamage(player_damage.get(p.getName())*0.66*(1+fsd.LightningCannon.get(p.getUniqueId())*0.032));								
								}
								p.setSprinting(true);
								p.getWorld().strikeLightningEffect(le.getLocation());
								le.damage(0,p);
								le.damage(player_damage.get(p.getName())*0.66*(1+fsd.LightningCannon.get(p.getUniqueId())*0.032),p);
								le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,60,1,false,false));
								p.setSprinting(false);			
							}	
		                }
					}, 10);
					gdcooldown.put(p.getName(), System.currentTimeMillis());  
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}

	
	@EventHandler
	public void TNTLauncher(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && !p.isSneaking())
		{
		int sec = 13;

        
		
		
		
		if(playerclass.get(p.getUniqueId()) == 16&& fsd.TNTLauncher.get(p.getUniqueId())>=1) {
				ev.setCancelled(true);
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (cdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use TNTLauncher").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap
							ItemStack is = new ItemStack(Material.TNT);
	                    	for(int i =1; i<5; i++) {
			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                	Location knockback = p.getEyeLocation().add(p.getLocation().getDirection().normalize().multiply(-0.5));
						                	if(knockback.getBlock().isPassable()) {
						                		p.teleport(knockback);
						                	}
						                	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 80, 100, false, false));
							            	p.playSound(p.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1.0f, 2f);
							            	p.playSound(p.getLocation(), Sound.ENTITY_TNT_PRIMED, 1.0f, 0f);
											Item solid = p.getWorld().dropItem(p.getEyeLocation(), is);
											solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
											solid.setMetadata("tnt of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
											solid.setGlowing(true);
											solid.setPickupDelay(9999);
						                    solid.setVelocity(p.getLocation().getDirection().normalize().multiply(3));
						                }
			                	   }, i*2); 
	                    	}
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("tnt of"+p.getName())).forEach(t ->{
				                    	p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, t.getLocation(), 1,0.1,0.1,0.1,0);
						            	p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 0f);
										for(Entity e : t.getNearbyEntities(5, 5, 5)) {
				                    		if (e instanceof Player) 
											{
												
												Player p1 = (Player) e;
												if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
												if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
													{
													continue;
													}
												}
											}
				                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
											{
												LivingEntity le = (LivingEntity)e;
													{
														if(le instanceof EnderDragon) {
										                    Arrow firstarrow = p.launchProjectile(Arrow.class);
										                    firstarrow.setDamage(0);
										                    firstarrow.remove();
															Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
																ar.setShooter(p);
																ar.setCritical(false);
																ar.setSilent(true);
																ar.setPickupStatus(PickupStatus.DISALLOWED);
																ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
															});
															enar.setDamage(player_damage.get(p.getName())*0.3*(1+fsd.TNTLauncher.get(p.getUniqueId())*0.0212));								
														}
														p.setSprinting(true);
									                    le.damage(0,p);
									                    le.damage(player_damage.get(p.getName())*0.3*(1+fsd.TNTLauncher.get(p.getUniqueId())*0.0212),p);
														p.setSprinting(false);
													}
											}
											
										}
										t.remove();
									});
				                }
	                    	}, 12); 
							cdcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
						ItemStack is = new ItemStack(Material.TNT);
                    	for(int i =1; i<5; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	Location knockback = p.getEyeLocation().add(p.getLocation().getDirection().normalize().multiply(-0.5));
					                	if(knockback.getBlock().isPassable()) {
					                		p.teleport(knockback);
					                	}
					                	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 80, 100, false, false));
						            	p.playSound(p.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1.0f, 2f);
						            	p.playSound(p.getLocation(), Sound.ENTITY_TNT_PRIMED, 1.0f, 0f);
										Item solid = p.getWorld().dropItem(p.getEyeLocation(), is);
										solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
										solid.setMetadata("tnt of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
										solid.setGlowing(true);
										solid.setPickupDelay(9999);
					                    solid.setVelocity(p.getLocation().getDirection().normalize().multiply(3));
					                }
		                	   }, i*2); 
                    	}
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("tnt of"+p.getName())).forEach(t ->{
			                    	p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, t.getLocation(), 1,0.1,0.1,0.1,0);
					            	p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 0f);
									for(Entity e : t.getNearbyEntities(5, 5, 5)) {
			                    		if (e instanceof Player) 
										{
											
											Player p1 = (Player) e;
											if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
											if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
												{
												continue;
												}
											}
										}
			                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
										{
											LivingEntity le = (LivingEntity)e;
												{
													if(le instanceof EnderDragon) {
									                    Arrow firstarrow = p.launchProjectile(Arrow.class);
									                    firstarrow.setDamage(0);
									                    firstarrow.remove();
														Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
															ar.setShooter(p);
															ar.setCritical(false);
															ar.setSilent(true);
															ar.setPickupStatus(PickupStatus.DISALLOWED);
															ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
														});
														enar.setDamage(player_damage.get(p.getName())*0.3*(1+fsd.TNTLauncher.get(p.getUniqueId())*0.0212));								
													}
													p.setSprinting(true);
								                    le.damage(0,p);
								                    le.damage(player_damage.get(p.getName())*0.3*(1+fsd.TNTLauncher.get(p.getUniqueId())*0.0212),p);
													p.setSprinting(false);
												}
										}
										
									}
									t.remove();
								});
			                }
                    	}, 12); 
		                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
		}
	
	}
	
	
	@EventHandler
	public void RailSMG(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
		{
		Action ac = ev.getAction();
		int sec = 5;

        
		
		
		if(playerclass.get(p.getUniqueId()) == 16) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
				

				if(frcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (frcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use RailSMG").create());
	                }
	                else // if timer is done
	                {
	                    frcooldown.remove(p.getName()); // removing player from HashMap

	                    Arrow firstarrow = p.launchProjectile(Arrow.class);
	                    firstarrow.setDamage(0);
	                    firstarrow.remove();
		            	p.getLocation();
	                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    for(int i =1; i<10; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1f, 2f);
					                	p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1f, 2f);
					                	ArrayList<Location> line = new ArrayList<Location>();
					                    for(double d = 0.1; d <= 26.1; d += 0.2) {
						                    Location pl = p.getEyeLocation().clone();
											pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
											line.add(pl);
					                    }
					                    for(Location l : line) {
					                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l.add(0, -0.289, 0),5, 0.005,0.005,0.005,0, Material.LIGHT_BLUE_GLAZED_TERRACOTTA.createBlockData());

					                    	for (Entity a : p.getWorld().getNearbyEntities(l, 0.5, 0.5, 0.5))
											{
												if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
												{
													if (a instanceof Player) 
													{
														
														Player p1 = (Player) a;
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
													LivingEntity le = (LivingEntity)a;
													les.add(le);										
												}
											}
					                    }
					                }
		                	   }, i); 
						}
	                    for(int i =1; i<10; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	for(LivingEntity le : les) {
											if(le instanceof EnderDragon) {
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
													ar.setShooter(p);
													ar.setCritical(false);
													ar.setSilent(true);
													ar.setPickupStatus(PickupStatus.DISALLOWED);
													ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*0.073*(1+fsd.RailSMG.get(p.getUniqueId())*0.008));								
											}
											p.setSprinting(true);
											le.damage(0,p);
											le.damage(player_damage.get(p.getName())*0.073*(1+fsd.RailSMG.get(p.getUniqueId())*0.008),p);
											le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,15,1,false,false));
											p.setSprinting(false);	
					                	}
					                	
					                }
		                	   }, i+1/10); 
	   					}

						frcooldown.put(p.getName(), System.currentTimeMillis());  
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    Arrow firstarrow = p.launchProjectile(Arrow.class);
                    firstarrow.setDamage(0);
                    firstarrow.remove();
	            	p.getLocation();
                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                    for(int i =1; i<10; i++) {
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1f, 2f);
				                	p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1f, 2f);
				                	ArrayList<Location> line = new ArrayList<Location>();
				                    for(double d = 0.1; d <= 26.1; d += 0.2) {
					                    Location pl = p.getEyeLocation().clone();
										pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
										line.add(pl);
				                    }
				                    for(Location l : line) {
				                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l.add(0, -0.289, 0),5, 0.005,0.005,0.005,0, Material.LIGHT_BLUE_GLAZED_TERRACOTTA.createBlockData());

				                    	for (Entity a : p.getWorld().getNearbyEntities(l, 0.5, 0.5, 0.5))
										{
											if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
											{
												if (a instanceof Player) 
												{
													
													Player p1 = (Player) a;
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
												LivingEntity le = (LivingEntity)a;
												les.add(le);										
											}
										}
				                    }
				                }
	                	   }, i); 
					}
                    for(int i =1; i<10; i++) {
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	for(LivingEntity le : les) {
										if(le instanceof EnderDragon) {
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
												ar.setShooter(p);
												ar.setCritical(false);
												ar.setSilent(true);
												ar.setPickupStatus(PickupStatus.DISALLOWED);
												ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*0.073*(1+fsd.RailSMG.get(p.getUniqueId())*0.008));								
										}
											p.setSprinting(true);
											le.damage(0,p);
											le.damage(player_damage.get(p.getName())*0.073*(1+fsd.RailSMG.get(p.getUniqueId())*0.008),p);
											le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,15,1,false,false));
											p.setSprinting(false);	
				                	}
				                	
				                }
	                	   }, i+1/10); 
   					}
					frcooldown.put(p.getName(), System.currentTimeMillis());  
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	/*
	@EventHandler
	public void Bullet(ProjectileHitEvent ev) 
	{
		
		if(ev.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)ev.getEntity().getShooter();
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 16 && ev.getEntity().hasMetadata("mg of"+p.getName())) {
			
				if(ev.getHitEntity()!=null && ev.getHitBlock()==null) {
					Entity e =ev.getHitEntity();
					{
                		if (e instanceof Player) 
						{
							Player p1 = (Player) e;
							if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
							if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
								{
								return;
								}
							}
							if(((Player) e).isBlocking()) {
								return;
							}
						}
                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
						{
							LivingEntity le = (LivingEntity)e;
								{
										if(le instanceof EnderDragon) {
						                    Arrow firstarrow = p.launchProjectile(Arrow.class);
						                    firstarrow.setDamage(0);
						                    firstarrow.remove();
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
												ar.setShooter(p);
												ar.setCritical(false);
												ar.setSilent(true);
												ar.setPickupStatus(PickupStatus.DISALLOWED);
												ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*0.05*(1+fsd.MachineGun.get(p.getUniqueId())*0.0067));								
										}
										p.setSprinting(true);
										le.damage(0,p);
					                    le.damage(player_damage.get(p.getName())*0.05*(1+fsd.MachineGun.get(p.getUniqueId())*0.0067), p);
										p.setSprinting(false);
										
								}
						}
					}
				}
			}			
		}
	}
*/
	
	@EventHandler
	public void Shockwave(EntityDamageByEntityEvent d) 
	{
		int sec = 7;
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		d.getEntity();
        
		

		
		
		if(playerclass.get(p.getUniqueId()) == 16 && fsd.DoubleBarrel.get(p.getUniqueId())>=1) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && (p.isSneaking()) && !(p.isSprinting()))
				{
				if(stcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            long timer = (stcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
		            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Shockwave").create());
			        }
		            else // if timer is done
		            {
		                stcooldown.remove(p.getName()); // removing player from HashMap
	                    ArrayList<Location> line = new ArrayList<Location>();
	                    ArrayList<Location> pie = new ArrayList<Location>();
	                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    for(double dou = -Math.PI/6; dou<= Math.PI/6; dou += Math.PI/180) {
		                    Location l = p.getLocation();
		                    l.setDirection(l.getDirection().normalize().rotateAroundY(dou));
		                    l.add(l.getDirection().normalize().multiply(5.1));
		                    line.add(l);
	                    	
	                    }
	                    for(double dou = 0.1; dou <= 5.1; dou += 1) {
	                    	  for (Location l : line){
			                    Location pl = p.getLocation();
								Vector ltr = l.toVector().subtract(pl.toVector());
								pl.add(ltr.normalize().multiply(dou));
								pie.add(pl);
								}
	                    }
						for(int i = 0; i<2; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
				                	public void run() 
					                {
			                    	  	for (Location l : line){
			                    	  		Item barrel = p.getWorld().dropItemNaturally(p.getEyeLocation(), new ItemStack(Material.STRUCTURE_VOID));
			                    	  		barrel.setVelocity(l.toVector().subtract(p.getEyeLocation().toVector()));
			                    	  		barrel.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			                    	  		barrel.setMetadata("barrel of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			                    	  		barrel.setGlowing(true);
			                    	  		barrel.setPickupDelay(9999);
			                    	  	}
										p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 0.8f, 0.2f);
										p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 0.8f, 1.4f);
										p.playSound(p.getLocation(), Sound.ENTITY_SQUID_HURT, 1.0f, 1.1f);
										p.playSound(p.getLocation(), Sound.ENTITY_SQUID_SQUIRT, 1.0f, 1.1f);
										pie.forEach(t -> {
											for(Entity e: t.getWorld().getNearbyEntities(t,1,1,1)) {
					                    		if (e instanceof Player) 
												{
													
													Player p1 = (Player) e;
													if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
													if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
														{
														continue;
														}
													}
												}
					                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
												{
													LivingEntity le = (LivingEntity)e;
													les.add(le);
												}
												
											}
										});
						            }
				            }, i*5); 
							
						}
						for(int i = 0; i<2; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
				                	public void run() 
					                {
			             				for(LivingEntity le: les) {
				    						le.teleport(le.getLocation().add(p.getLocation().getDirection().normalize().multiply(5)));
												if(le instanceof EnderDragon) {
								                    Arrow firstarrow = p.launchProjectile(Arrow.class);
								                    firstarrow.setDamage(0);
								                    firstarrow.remove();
													Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
														ar.setShooter(p);
														ar.setCritical(false);
														ar.setSilent(true);
														ar.setPickupStatus(PickupStatus.DISALLOWED);
														ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
													});
													enar.setDamage(player_damage.get(p.getName())*0.135);								
												}
				             					p.setSprinting(true);
				             					le.damage(0, p);
				             					le.damage(player_damage.get(p.getName())*0.135, p);
				             					p.setSprinting(false);
			             					
			             				}
						            }
				            }, i*5); 
							
						}
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {
		             					p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("barrel of"+p.getName())).forEach(t ->t.remove());
						            }
			             }, 13); 
			            stcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
		        	ArrayList<Location> line = new ArrayList<Location>();
                    ArrayList<Location> pie = new ArrayList<Location>();
                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                    for(double dou = -Math.PI/6; dou<= Math.PI/6; dou += Math.PI/180) {
	                    Location l = p.getLocation();
	                    l.setDirection(l.getDirection().normalize().rotateAroundY(dou));
	                    l.add(l.getDirection().normalize().multiply(5.1));
	                    line.add(l);
                    	
                    }
                    for(double dou = 0.1; dou <= 5.1; dou += 1) {
                    	  for (Location l : line){
		                    Location pl = p.getLocation();
							Vector ltr = l.toVector().subtract(pl.toVector());
							pl.add(ltr.normalize().multiply(dou));
							pie.add(pl);
							}
                    }
					for(int i = 0; i<2; i++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
			                	public void run() 
				                {
		                    	  	for (Location l : line){
		                    	  		Item barrel = p.getWorld().dropItemNaturally(p.getEyeLocation(), new ItemStack(Material.STRUCTURE_VOID));
		                    	  		barrel.setVelocity(l.toVector().subtract(p.getEyeLocation().toVector()));
		                    	  		barrel.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		                    	  		barrel.setMetadata("barrel of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		                    	  		barrel.setGlowing(true);
		                    	  		barrel.setPickupDelay(9999);
		                    	  	}
									p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 0.8f, 0.2f);
									p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 0.8f, 1.4f);
									p.playSound(p.getLocation(), Sound.ENTITY_SQUID_HURT, 1.0f, 1.1f);
									p.playSound(p.getLocation(), Sound.ENTITY_SQUID_SQUIRT, 1.0f, 1.1f);
									pie.forEach(t -> {
										for(Entity e: t.getWorld().getNearbyEntities(t,1,1,1)) {
				                    		if (e instanceof Player) 
											{
												
												Player p1 = (Player) e;
												if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
												if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
													{
													continue;
													}
												}
											}
				                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
											{
												LivingEntity le = (LivingEntity)e;
												les.add(le);
											}
											
										}
									});
					            }
			            }, i*5); 
						
					}
					for(int i = 0; i<2; i++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
			                	public void run() 
				                {
		             				for(LivingEntity le: les) {
				    						le.teleport(le.getLocation().add(p.getLocation().getDirection().normalize().multiply(5)));
											if(le instanceof EnderDragon) {
							                    Arrow firstarrow = p.launchProjectile(Arrow.class);
							                    firstarrow.setDamage(0);
							                    firstarrow.remove();
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
													ar.setShooter(p);
													ar.setCritical(false);
													ar.setSilent(true);
													ar.setPickupStatus(PickupStatus.DISALLOWED);
													ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*0.135);								
											}
			             					p.setSprinting(true);
			             					le.damage(0, p);
			             					le.damage(player_damage.get(p.getName())*0.135, p);
			             					p.setSprinting(false);
		             					
		             				}
					            }
			            }, i*5); 
						
					}
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
			                	public void run() 
				                {
	             					p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("barrel of"+p.getName())).forEach(t ->t.remove());
					            }
		             }, 13); 
		            stcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
		}
		}
	}
	

	@EventHandler
	public void HoneyMissile(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 20;

        
		
		
		new ForSkillsData(ForSkillsData.loadData(path +"/plugins/RPGskills/ForSkillsData.data"));
		if(playerclass.get(p.getUniqueId()) == 16 && p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) {
			if((!p.isSneaking())&& !p.isOnGround() && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK) &&(ac!= Action.RIGHT_CLICK_AIR)&&(ac!= Action.RIGHT_CLICK_AIR))
			{
				ev.setCancelled(true);
				if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            long timer = (smcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
		            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use HoneyMissile").create());
			        }
		            else // if timer is done
		            {
		                smcooldown.remove(p.getName()); // removing player from HashMapLocation dam = p.getLocation();
	                    ArrayList<Location> line = new ArrayList<Location>();
	                    for(double dou = -Math.PI/12; dou<= Math.PI/12; dou += Math.PI/60) {
		                    Location l = p.getEyeLocation().clone();
		                    l.setDirection(l.getDirection().rotateAroundY(dou));
		                    l.add(l.getDirection().normalize().multiply(25.1));
		                    line.add(l);
	                    }
						line.forEach(l -> {
							Firework hesh = (Firework) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.FIREWORK);
	     					hesh.setShotAtAngle(true);
							hesh.setMetadata("hesh of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							hesh.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							hesh.setShooter(p);
		                	hesh.setVelocity(l.toVector().subtract(p.getLocation().toVector()).normalize().multiply(1));
							FireworkMeta fm = hesh.getFireworkMeta();
			            	
		        			FireworkEffect effect = FireworkEffect.builder()
		                                .with(Type.BURST)
		                                .withColor(Color.YELLOW)
		                                .flicker(true)
		                                .trail(true)
		                                .build();
							fm.setPower(3);
							fm.addEffect(effect);
							hesh.setFireworkMeta(fm);
						});
			            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
                    ArrayList<Location> line = new ArrayList<Location>();
                    for(double dou = -Math.PI/12; dou<= Math.PI/12; dou += Math.PI/60) {
	                    Location l = p.getEyeLocation().clone();
	                    l.setDirection(l.getDirection().rotateAroundY(dou));
	                    l.add(l.getDirection().normalize().multiply(25.1));
	                    line.add(l);
                    }
					line.forEach(l -> {
						Firework hesh = (Firework) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.FIREWORK);
     					hesh.setShotAtAngle(true);
						hesh.setMetadata("hesh of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						hesh.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						hesh.setShooter(p);
	                	hesh.setVelocity(l.toVector().subtract(p.getLocation().toVector()).normalize().multiply(1));
						FireworkMeta fm = hesh.getFireworkMeta();
		            	
	        			FireworkEffect effect = FireworkEffect.builder()
	                                .with(Type.BURST)
	                                .withColor(Color.YELLOW)
	                                .flicker(true)
	                                .trail(true)
	                                .build();
						fm.setPower(3);
						fm.addEffect(effect);
						hesh.setFireworkMeta(fm);
					});
		            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
		}
	}
	

	@EventHandler
	public void HESH(EntityDamageByEntityEvent d) 
	{
        
		
		
		if(d.getDamager() instanceof Firework && d.getEntity() instanceof LivingEntity) 
		{
			Firework fw = (Firework) d.getDamager();
			if(fw.getShooter() instanceof Player) {
				Player p = (Player) fw.getShooter();
			    if (fw.hasMetadata("hesh of"+p.getName())) {
			        d.setCancelled(true);
			    }
			}
		}
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && d.getDamage()>=0) 
		{
			Player p = (Player) d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			if(hesht.containsEntry(p, le)) {
				p.getWorld().playSound(le.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1, 0);
				le.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, le.getLocation(), 1,0.1,0.1,0.1);
		    	le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 35, 2,2,2,3);
				d.setDamage(d.getDamage()+player_damage.get(p.getName())*0.085*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.01));
				heshc.computeIfPresent(le, (k,v) -> --v);
				if(heshc.containsKey(le) && heshc.get(le)<=0) {
					p.getWorld().playSound(le.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
					d.setDamage(d.getDamage()+player_damage.get(p.getName())*0.53*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.032));
 					le.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, le.getLocation(), 1,0.1,0.1,0.1);
 			    	le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 100, 2,2,2,3);
 			    	heshc.remove(le);
 			    	hesht.remove(p, le);
				}
				if(d.getDamage()>=le.getHealth()) {
					p.getWorld().playSound(le.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
					d.setDamage(d.getDamage()+player_damage.get(p.getName())*0.53*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.032));
 					le.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, le.getLocation(), 1,0.1,0.1,0.1);
 			    	le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 100, 2,2,2,3);
 			    	heshc.remove(le);
 			    	hesht.remove(p, le);
					
				}
			}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof EnderDragon&& d.getDamage()>=0) 
		{
			Arrow arrow = (Arrow) d.getDamager();
			EnderDragon le = (EnderDragon) d.getEntity();
			if(arrow.getShooter() instanceof Player) {
				Player p = (Player) arrow.getShooter();
				if(hesht.containsEntry(p, le)) {
					p.getWorld().playSound(le.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1, 0);
					le.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, le.getLocation(), 1,0.1,0.1,0.1);
			    	le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 35, 2,2,2,3);
					d.setDamage(d.getDamage()+player_damage.get(p.getName())*0.085*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.01));
					heshc.computeIfPresent(le, (k,v) -> --v);
					if(heshc.containsKey(le) && heshc.get(le)<=0) {
						p.getWorld().playSound(le.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
						d.setDamage(d.getDamage()+player_damage.get(p.getName())*0.53*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.032));
	 					le.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, le.getLocation(), 1,0.1,0.1,0.1);
	 			    	le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 100, 2,2,2,3);
	 			    	heshc.remove(le);
	 			    	hesht.remove(p, le);
					}
					if(d.getDamage()>=le.getHealth()) {
						p.getWorld().playSound(le.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
						d.setDamage(d.getDamage()+player_damage.get(p.getName())*0.53*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.032));
	 					le.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, le.getLocation(), 1,0.1,0.1,0.1);
	 			    	le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 100, 2,2,2,3);
	 			    	heshc.remove(le);
	 			    	hesht.remove(p, le);
						
					}
				}
				
			}
		}
	}
	
	@EventHandler
	public void HESH(FireworkExplodeEvent f) 
	{
        
		
		
		if(f.getEntity().getShooter() instanceof Player) {
			Firework fw = f.getEntity();
			Player p = (Player) fw.getShooter();
		    if (fw.hasMetadata("hesh of"+p.getName())) {
		    	fw.getWorld().spawnParticle(Particle.LANDING_HONEY, fw.getLocation(), 100, 2,2,2,3);
		    	for(Entity e : fw.getNearbyEntities(4.5, 4.5, 4.5)) {
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
	        		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
					{
						LivingEntity le = (LivingEntity)e;
     					le.damage(0, p);
						hesht.put(p, le);
						heshc.put(le, 20);
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
			                	public void run() 
				                {
		             				if(heshc.containsKey(hesht)) {
										if(le instanceof EnderDragon) {
						                    Arrow firstarrow = p.launchProjectile(Arrow.class);
						                    firstarrow.setDamage(0);
						                    firstarrow.remove();
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
												ar.setShooter(p);
												ar.setCritical(false);
												ar.setSilent(true);
												ar.setPickupStatus(PickupStatus.DISALLOWED);
												ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*0.53*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.032));								
										}
		            					p.setSprinting(true);
		             					le.damage(0, p);
		             					le.damage(player_damage.get(p.getName())*0.53*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.032), p);
		            					p.setSprinting(false);
		        						p.getWorld().playSound(le.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
		             					le.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, le.getLocation(), 1,0.1,0.1,0.1);
		             			    	le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 100, 2,2,2,3);
		             			    	heshc.remove(hesht);
		             				}
		             				hesht.remove(p, le);
					            }
		               	}, 300); 
						le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,100,1,false,false));
					}
		    		
		    	}
		    }
			
		}
	}
	

	@EventHandler
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
        
		
		
		
		
			if(playerclass.get(p.getUniqueId()) == 16 && ((is.getType().name().contains("PICKAXE"))) && p.isSneaking())
			{
				ev.setCancelled(true);
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (sultcooldown.get(p.getName())/1000 + 50) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use DragonBreather").create());
	                }
	                else // if timer is done
	                {
	                    sultcooldown.remove(p.getName()); // removing player from HashMap
	                    AtomicInteger j = new AtomicInteger();
		     	        HashSet<LivingEntity> les = new HashSet<>();
	                	mga.put(p, 200);
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 1, 2);
						p.setInvulnerable(true);
	                    Arrow firstarrow = p.launchProjectile(Arrow.class);
	                    firstarrow.setDamage(0);
	                    firstarrow.remove();
	 					for(int c=0;c<25;c++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
				                	public void run() 
					                {
		     	                    ArrayList<Location> line = new ArrayList<>();
		     	                	for(double point = 0.1; point<60.1; point+=0.5) {
		     	                		line.add(p.getEyeLocation().add(p.getLocation().getDirection().normalize().multiply(point)));
		     	                	}
			    					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 2);
			    					
				                    line.forEach(l -> {
				                    	for(Entity e: l.getWorld().getNearbyEntities(l,1,1,1)) {
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
		             						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")) {
		             							LivingEntity le = (LivingEntity)e;
												les.add(le);
		             						}
		             					}
					                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						             		@Override
						             			public void run() 
								                {
					             					l.getWorld().spawnParticle(Particle.DRAGON_BREATH, l.add(0, -1, 0), 5, 0.3,0.3,0.3,0.1);
					             					l.getWorld().spawnParticle(Particle.END_ROD, l.add(0, -1, 0), 5, 0.3,0.3,0.3,0);
					             					l.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l.add(0, -1, 0), 5, 0.3,0.3,0.3,0.1);

								                }
					                    	}, j.incrementAndGet()/600); 
				                    });
						            }
			               	}, c*2+20); 
	 					}

	 					for(int c=0;c<25;c++) {
	 	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	 		             		@Override
	 		             			public void run() 
	 				                {

	 	             					for(LivingEntity le : les) {
	 											if(le instanceof EnderDragon) {
	 												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
	 													ar.setShooter(p);
	 													ar.setCritical(false);
	 													ar.setSilent(true);
	 													ar.setPickupStatus(PickupStatus.DISALLOWED);
	 													ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
	 												});
	 												enar.setDamage(player_damage.get(p.getName())/3);								
	 											}
	 	             							p.setSprinting(true);
	 	             							le.damage(0, p);
	 	             							le.damage(player_damage.get(p.getName())/3, p);
	 	             							le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 2, false, false));
	 	             							p.setSprinting(false);
	 	             					}
	 				                }
	 	                    	}, c*2+21); 
	 					}
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
		             			public void run() 
				                {
								p.setInvulnerable(false);
					            }
	                    	}, 80); 
		                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    AtomicInteger j = new AtomicInteger();
	     	        HashSet<LivingEntity> les = new HashSet<>();
                	mga.put(p, 200);
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 1, 2);
					p.setInvulnerable(true);
                    Arrow firstarrow = p.launchProjectile(Arrow.class);
                    firstarrow.setDamage(0);
                    firstarrow.remove();
 					for(int c=0;c<25;c++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
			                	public void run() 
				                {
	     	                    ArrayList<Location> line = new ArrayList<>();
	     	                	for(double point = 0.1; point<60.1; point+=0.5) {
	     	                		line.add(p.getEyeLocation().add(p.getLocation().getDirection().normalize().multiply(point)));
	     	                	}
		    					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 2);
		    					
			                    line.forEach(l -> {
			                    	for(Entity e: l.getWorld().getNearbyEntities(l,1,1,1)) {
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
	             						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")) {
	             							LivingEntity le = (LivingEntity)e;
											les.add(le);
	             						}
	             					}
				                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					             		@Override
					             			public void run() 
							                {
				             					l.getWorld().spawnParticle(Particle.DRAGON_BREATH, l.add(0, -1, 0), 5, 0.3,0.3,0.3,0.1);
				             					l.getWorld().spawnParticle(Particle.END_ROD, l.add(0, -1, 0), 5, 0.3,0.3,0.3,0);
				             					l.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l.add(0, -1, 0), 5, 0.3,0.3,0.3,0.1);

							                }
				                    	}, j.incrementAndGet()/600); 
			                    });
					            }
		               	}, c*2+20); 
 					}

 					for(int c=0;c<25;c++) {
 	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
 		             		@Override
 		             			public void run() 
 				                {

 	             					for(LivingEntity le : les) {
 											if(le instanceof EnderDragon) {
 												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
 													ar.setShooter(p);
 													ar.setCritical(false);
 													ar.setSilent(true);
 													ar.setPickupStatus(PickupStatus.DISALLOWED);
 													ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
 												});
 												enar.setDamage(player_damage.get(p.getName())/3);								
 											}
 	             							p.setSprinting(true);
 	             							le.damage(0, p);
 	             							le.damage(player_damage.get(p.getName())/3, p);
 	             							le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 2, false, false));
 	             							p.setSprinting(false);
 	             					}
 				                }
 	                    	}, c*2+21); 
 					}
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
	             			public void run() 
			                {
							p.setInvulnerable(false);
				            }
                    	}, 80); 
	                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") )
		{
	        
	
			
			
			if(playerclass.get(p.getUniqueId()) == 16)
			{
					e.setCancelled(true);
			}
		}
		
	}

	@EventHandler
	public void Damagegetter(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();
		    
			
			
			

			if(playerclass.get(p.getUniqueId()) == 16) {
				if(p.getInventory().getItemInMainHand().getType()==Material.IRON_PICKAXE)
				{
						player_damage.put(p.getName(), (7 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10)*(1+fsd.Development.get(p.getUniqueId())*0.05));
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if( p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_PICKAXE)
				{
					player_damage.put(p.getName(), (8 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10)*(1+fsd.Development.get(p.getUniqueId())*0.05));
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_PICKAXE)
				{
					player_damage.put(p.getName(), (4 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10)*(1+fsd.Development.get(p.getUniqueId())*0.05));
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.STONE_PICKAXE)
				{
					player_damage.put(p.getName(), (5 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10)*(1+fsd.Development.get(p.getUniqueId())*0.05));
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_PICKAXE)
				{
					player_damage.put(p.getName(), (4 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10)*(1+fsd.Development.get(p.getUniqueId())*0.05));
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_PICKAXE)
				{
					player_damage.put(p.getName(), (10 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10)*(1+fsd.Development.get(p.getUniqueId())*0.05));
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
			}
		}
	}
	
	@EventHandler
	public void Damagegetter(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		Entity e = d.getEntity();
        
		

		
		
		if(playerclass.get(p.getUniqueId()) == 16) {
			if(p.getInventory().getItemInMainHand().getType()==Material.IRON_PICKAXE)
			{
					player_damage.put(p.getName(), (7 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10)*(1+fsd.Development.get(p.getUniqueId())*0.36));
					
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
			}
			
			if( p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_PICKAXE)
			{
					player_damage.put(p.getName(), (8 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10)*(1+fsd.Development.get(p.getUniqueId())*0.36));
					
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
			}
			
			if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_PICKAXE)
			{
				player_damage.put(p.getName(), (4 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10)*(1+fsd.Development.get(p.getUniqueId())*0.36));
					
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
			}
			
			if(p.getInventory().getItemInMainHand().getType()==Material.STONE_PICKAXE)
			{
				player_damage.put(p.getName(), (5 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10)*(1+fsd.Development.get(p.getUniqueId())*0.36));
					
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
			}
			
			if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_PICKAXE)
			{
				player_damage.put(p.getName(), (4 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10)*(1+fsd.Development.get(p.getUniqueId())*0.36));
					
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
			}
			
			if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_PICKAXE)
			{
				player_damage.put(p.getName(), (10 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10)*(1+fsd.Development.get(p.getUniqueId())*0.36));
					
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
			}
		}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
			Arrow ar = (Arrow)d.getDamager();
			Entity e = d.getEntity();
	
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
			    
				
				
				

				if(playerclass.get(p.getUniqueId()) == 16) {
					if(p.getInventory().getItemInMainHand().getType()==Material.IRON_PICKAXE)
					{
							player_damage.put(p.getName(), (7 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10)*(1+fsd.Development.get(p.getUniqueId())*0.36));
							
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
					}
					
					if( p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_PICKAXE)
					{
						player_damage.put(p.getName(), (8 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10)*(1+fsd.Development.get(p.getUniqueId())*0.36));
							
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
					}
					
					if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_PICKAXE)
					{
						player_damage.put(p.getName(), (4 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10)*(1+fsd.Development.get(p.getUniqueId())*0.36));
							
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
					}
					
					if(p.getInventory().getItemInMainHand().getType()==Material.STONE_PICKAXE)
					{
						player_damage.put(p.getName(), (5 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10)*(1+fsd.Development.get(p.getUniqueId())*0.36));
							
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
					}
					
					if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_PICKAXE)
					{
						player_damage.put(p.getName(), (4 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10)*(1+fsd.Development.get(p.getUniqueId())*0.36));
							
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
					}
					
					if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_PICKAXE)
					{
						player_damage.put(p.getName(), (10 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10)*(1+fsd.Development.get(p.getUniqueId())*0.36));
							
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
					}
				}
			}
		}
	}
}



