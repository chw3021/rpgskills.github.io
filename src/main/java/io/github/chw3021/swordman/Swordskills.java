package io.github.chw3021.swordman;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.party.PartyData;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

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
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;

public class Swordskills implements Listener, Serializable {
	

	/**
	 * 
	 */
	private static transient final long serialVersionUID = -4553653005963571208L;
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> rscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<Player, Long> reset = new HashMap<Player, Long>();	
	private HashMap<UUID, Integer> guard = new HashMap<UUID, Integer>();	
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private SwordSkillsData ssd = new SwordSkillsData(SwordSkillsData.loadData(path +"/plugins/RPGskills/SwordSkillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		SwordSkillsData s = new SwordSkillsData(SwordSkillsData.loadData(path +"/plugins/RPGskills/SwordSkillsData.data"));
		ssd = s;
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
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Swordskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				SwordSkillsData s = new SwordSkillsData(SwordSkillsData.loadData(path +"/plugins/RPGskills/SwordSkillsData.data"));
				ssd = s;
			}
			
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		SwordSkillsData s = new SwordSkillsData(SwordSkillsData.loadData(path +"/plugins/RPGskills/SwordSkillsData.data"));
		ssd = s;
		
	}
	
	@EventHandler
	public void SwordDrive(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 5;
        
		
		
		if(playerclass.get(p.getUniqueId()) == 0)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && !(p.isSneaking()))
			{
				ev.setCancelled(true);
				
                final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
				if(!l.getBlock().isPassable()) {
                l.setY(l.getY()+1);}
				l.setDirection(p.getLocation().getDirection());
				if(l.getBlock().isPassable()) {
				if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Drive").create());
	                }
	                else // if timer is done
	                {
	                    sdcooldown.remove(p.getName()); // removing player from HashMap
						p.teleport(l);
                    	p.setFallDistance(0);
						p.playSound(p.getLocation(), Sound.ENTITY_HOSTILE_BIG_FALL, 1.0f, 0.1f);
						p.playSound(p.getLocation(), Sound.BLOCK_BONE_BLOCK_BREAK, 1.0f, 0f);
						p.getWorld().spawnParticle(Particle.CRIT, l, 90, 3, 2, 3);
						p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 40, 1, false, false));
						for (Entity e : p.getWorld().getNearbyEntities(l, 3, 2, 3))
						{
							if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)e;
									{

										if(le instanceof EnderDragon) {
						                    Arrow firstarrow = p.launchProjectile(Arrow.class);
						                    firstarrow.setDamage(0);
						                    firstarrow.remove();
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
												a.setShooter(p);
												a.setCritical(false);
												a.setSilent(true);
												a.setPickupStatus(PickupStatus.DISALLOWED);
												a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*0.65*(1 + ssd.SwordDrive.get(p.getUniqueId())*0.36));								
										}
										p.setSprinting(true);
										le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
										le.damage(0, p);
										le.damage(player_damage.get(p.getName())*0.65*(1 + ssd.SwordDrive.get(p.getUniqueId())*0.036), p);
										p.setSprinting(false);
									}
							}
						}
		                sdcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
					p.teleport(l);
                	p.setFallDistance(0);
					p.playSound(p.getLocation(), Sound.ENTITY_HOSTILE_BIG_FALL, 1.0f, 0.1f);
					p.playSound(p.getLocation(), Sound.BLOCK_BONE_BLOCK_BREAK, 1.0f, 0f);
					p.getWorld().spawnParticle(Particle.CRIT, l, 90, 3, 2, 3);
					p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 40, 1, false, false));
					for (Entity e : p.getWorld().getNearbyEntities(l, 3, 2, 3))
					{
						if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
						{
							LivingEntity le = (LivingEntity)e;
								{

									if(le instanceof EnderDragon) {
					                    Arrow firstarrow = p.launchProjectile(Arrow.class);
					                    firstarrow.setDamage(0);
					                    firstarrow.remove();
										Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
											a.setShooter(p);
											a.setCritical(false);
											a.setSilent(true);
											a.setPickupStatus(PickupStatus.DISALLOWED);
											a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
										});
										enar.setDamage(player_damage.get(p.getName())*0.65*(1 + ssd.SwordDrive.get(p.getUniqueId())*0.36));								
									}
									p.setSprinting(true);
									le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
									le.damage(0, p);
									le.damage(player_damage.get(p.getName())*0.65*(1 + ssd.SwordDrive.get(p.getUniqueId())*0.036), p);
									p.setSprinting(false);
								}
						}
					}
	                sdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}}
		}
		}

	@EventHandler
	public void Swoop(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity e = (LivingEntity)d.getEntity();
		int sec =6;
		
		if(playerclass.get(p.getUniqueId()) == 0) {
			if((p.isSneaking()) && !p.isSprinting())
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD"))
				{
					

					if(swcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (swcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Swoop").create());
		                }
		                else // if timer is done
		                {
		                    swcooldown.remove(p.getName()); // removing player from HashMap
		                    AtomicInteger j = new AtomicInteger();
		                    if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)e;
								{
									if (le instanceof Player) 
									{
										PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
										Player p1 = (Player) le;
										if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
											{
												return;
											}
										}
									}
									p.setSprinting(true);
									p.swingMainHand();
									Location l = le.getLocation();
									l.setDirection(p.getLocation().getDirection());
									p.teleport(l);
									if(le instanceof EnderDragon) {
					                    Arrow firstarrow = p.launchProjectile(Arrow.class);
					                    firstarrow.setDamage(0);
					                    firstarrow.remove();
										Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
											a.setShooter(p);
											a.setCritical(false);
											a.setSilent(true);
											a.setPickupStatus(PickupStatus.DISALLOWED);
											a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
										});
										enar.setDamage(player_damage.get(p.getName())*1.3+ssd.Swoop.get(p.getUniqueId())/4);								
									}
									le.damage(ssd.Swoop.get(p.getUniqueId())/4, p);
									le.damage(player_damage.get(p.getName())*1.3, p);
									p.setSprinting(false);
									for (Entity e1 : p.getWorld().getNearbyEntities(l, 5, 5, 5))
									{
										if ((!(e1 == p))&& e1 instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
										{
											LivingEntity le1 = (LivingEntity)e1;
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() 
								                {
													p.setInvulnerable(true);
								                	p.teleport(le1);
								                	p.swingMainHand();
													p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, le1.getLocation(), 1);
													if(le instanceof EnderDragon) {
									                    Arrow firstarrow = p.launchProjectile(Arrow.class);
									                    firstarrow.setDamage(0);
									                    firstarrow.remove();
														Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
															a.setShooter(p);
															a.setCritical(false);
															a.setSilent(true);
															a.setPickupStatus(PickupStatus.DISALLOWED);
															a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
														});
														enar.setDamage(player_damage.get(p.getName())*0.8*(1+ssd.Swoop.get(p.getUniqueId())*0.063));								
													}
													p.setSprinting(true);
													le1.damage(0, p);
													le1.damage(player_damage.get(p.getName())*0.8*(1+ssd.Swoop.get(p.getUniqueId())*0.063), p);
													p.setSprinting(false);
													p.getWorld().spawnParticle(Particle.FLASH, e.getLocation(), 2);
													p.playSound(le1.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.5f);
													p.setInvulnerable(false);
								                }
								                
					                	   }, j.incrementAndGet()*2); 
										}
									}
									p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.7f, 5.0f);
									p.getWorld().spawnParticle(Particle.FLASH, e.getLocation(), 2);
									p.getWorld().strikeLightningEffect(l);
								}
							}
			                swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			                
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	AtomicInteger j = new AtomicInteger();
	                    if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
						{
							LivingEntity le = (LivingEntity)e;
							{
								if (le instanceof Player) 
								{
									PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
									Player p1 = (Player) le;
									if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
									if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
										{
											return;
										}
									}
								}
								p.setSprinting(true);
								p.swingMainHand();
								Location l = le.getLocation();
								l.setDirection(p.getLocation().getDirection());
								p.teleport(l);
								if(le instanceof EnderDragon) {
				                    Arrow firstarrow = p.launchProjectile(Arrow.class);
				                    firstarrow.setDamage(0);
				                    firstarrow.remove();
									Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
										a.setShooter(p);
										a.setCritical(false);
										a.setSilent(true);
										a.setPickupStatus(PickupStatus.DISALLOWED);
										a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
									});
									enar.setDamage(player_damage.get(p.getName())*1.3+ssd.Swoop.get(p.getUniqueId())/4);								
								}
								le.damage(ssd.Swoop.get(p.getUniqueId())/4, p);
								le.damage(player_damage.get(p.getName())*1.3, p);
								p.setSprinting(false);
								for (Entity e1 : p.getWorld().getNearbyEntities(l, 5, 5, 5))
								{
									if ((!(e1 == p))&& e1 instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
									{
										LivingEntity le1 = (LivingEntity)e1;
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
												p.setInvulnerable(true);
							                	p.teleport(le1);
							                	p.swingMainHand();
												p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, le1.getLocation(), 1);
												if(le instanceof EnderDragon) {
								                    Arrow firstarrow = p.launchProjectile(Arrow.class);
								                    firstarrow.setDamage(0);
								                    firstarrow.remove();
													Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
														a.setShooter(p);
														a.setCritical(false);
														a.setSilent(true);
														a.setPickupStatus(PickupStatus.DISALLOWED);
														a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
													});
													enar.setDamage(player_damage.get(p.getName())*0.8*(1+ssd.Swoop.get(p.getUniqueId())*0.063));								
												}
												p.setSprinting(true);
												le1.damage(0, p);
												le1.damage(player_damage.get(p.getName())*0.8*(1+ssd.Swoop.get(p.getUniqueId())*0.063), p);
												p.setSprinting(false);
												p.getWorld().spawnParticle(Particle.FLASH, e.getLocation(), 2);
												p.playSound(le1.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.5f);
												p.setInvulnerable(false);
							                }
							                
				                	   }, j.incrementAndGet()*2); 
									}
								}
								p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.7f, 5.0f);
								p.getWorld().spawnParticle(Particle.FLASH, e.getLocation(), 2);
								p.getWorld().strikeLightningEffect(l);
							}
						}
		                swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		                
		            }
				}
			}
		}
		}
	}
	@EventHandler
	public void RisingBlades(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 8;

		
		if(playerclass.get(p.getUniqueId()) == 0) {
		if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD"))
			{
				

				if(rscooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (rscooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use RisingBlades").create());
	                }
	                else // if timer is done
	                {
	                    rscooldown.remove(p.getName()); // removing player from HashMap
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 120, 0, false,false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 50, 3, false,false));
	                    for(int i =0; i<6; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                    p.swingMainHand();
					                	ArrayList<Location> line = new ArrayList<Location>();
					                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
					                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0f);
					                    AtomicInteger jj = new AtomicInteger(0);
					                    AtomicInteger b = new AtomicInteger(0);
					                    for(double d = 0; d <= 2.5; d += 0.1) {
						                    Location pl = p.getEyeLocation();
											pl.add(pl.clone().getDirection().normalize().multiply(d));
											line.add(pl.add(0, d*0.05, 0));
					                    }
					                    if(line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().isPresent()) {
						                    Location block =line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().get();
						                    for(int k=0; k<line.indexOf(block); k++) {
							                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							             		@Override
									                	public void run() 
										                {	
									                    	p.teleport(line.get(b.getAndIncrement()));
											            }
								                	   }, jj.incrementAndGet()/50); 
												 }

						                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.1f);
						                    ArrayList<Location> draw = new ArrayList<Location>();
						                    AtomicInteger j = new AtomicInteger();
						                    for(double an = 0; an<Math.PI*2; an+=Math.PI/90) {
						                    	Location pl = p.getLocation().clone();
						                    	pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(3.6));
						                    	draw.add(pl);
						                    }
						                    draw.forEach(pl -> {
												Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									                @Override
									                public void run() 
									                {
										        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, pl,1,1,1,1);
									                }
												}, j.incrementAndGet()/900); 
						                    	
						                    });
											for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),3.5, 3.5, 3.5))
											{
												if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
												{
													LivingEntity le = (LivingEntity)e;
													if(le instanceof EnderDragon) {
									                    Arrow firstarrow = p.launchProjectile(Arrow.class);
									                    firstarrow.setDamage(0);
									                    firstarrow.remove();
														Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
															a.setShooter(p);
															a.setCritical(false);
															a.setSilent(true);
															a.setPickupStatus(PickupStatus.DISALLOWED);
															a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
														});
														enar.setDamage(player_damage.get(p.getName())*0.23*(1+ ssd.Rising.get(p.getUniqueId())*0.026));								
													}
													p.setSprinting(true);
													p.setCooldown(p.getInventory().getItemInMainHand().getType(), 0);
													le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
													le.teleport(p);
													le.damage(0, p);
													le.damage(player_damage.get(p.getName())*0.23*(1+ ssd.Rising.get(p.getUniqueId())*0.026), p);
													p.setSprinting(false);
														
												}
											}
						                }
					                    
					                    else {
						                    	line.forEach(i -> {
							                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							             		@Override
									                	public void run() 
										                {	
									             			p.teleport(i);
											            }
								                	   }, jj.incrementAndGet()/50); 
												 });
							                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.1f);
							                    ArrayList<Location> draw = new ArrayList<Location>();
							                    AtomicInteger j = new AtomicInteger();
							                    for(double an = 0; an<Math.PI*2; an+=Math.PI/90) {
							                    	Location pl = p.getLocation().clone();
							                    	pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(3.6));
							                    	draw.add(pl);
							                    }
							                    draw.forEach(pl -> {
													Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										                @Override
										                public void run() 
										                {
											        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, pl,1,1,1,1);
										                }
													}, j.incrementAndGet()/900); 
							                    	
							                    });
												for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),3.5, 3.5, 3.5))
												{
													if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
													{
														LivingEntity le = (LivingEntity)e;
														if(le instanceof EnderDragon) {
										                    Arrow firstarrow = p.launchProjectile(Arrow.class);
										                    firstarrow.setDamage(0);
										                    firstarrow.remove();
															Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
																a.setShooter(p);
																a.setCritical(false);
																a.setSilent(true);
																a.setPickupStatus(PickupStatus.DISALLOWED);
																a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
															});
															enar.setDamage(player_damage.get(p.getName())*0.23*(1+ ssd.Rising.get(p.getUniqueId())*0.026));								
														}
														p.setSprinting(true);
														p.setCooldown(p.getInventory().getItemInMainHand().getType(), 0);
														le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
														le.teleport(p);
														le.damage(0, p);
														le.damage(player_damage.get(p.getName())*0.23*(1+ ssd.Rising.get(p.getUniqueId())*0.026), p);
														p.setSprinting(false);
															
													}
												}
					                    	}
					                }
					            }, i*2); 
		                }

						rscooldown.put(p.getName(), System.currentTimeMillis());  
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 120, 0, false,false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 50, 3, false,false));
	            	for(int i =0; i<6; i++) {
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                    p.swingMainHand();
				                	ArrayList<Location> line = new ArrayList<Location>();
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0f);
				                    AtomicInteger jj = new AtomicInteger(0);
				                    AtomicInteger b = new AtomicInteger(0);
				                    for(double d = 0; d <= 2.5; d += 0.1) {
					                    Location pl = p.getEyeLocation();
										pl.add(pl.clone().getDirection().normalize().multiply(d));
										line.add(pl.add(0, d*0.05, 0));
				                    }
				                    if(line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().isPresent()) {
					                    Location block =line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().get();
					                    for(int k=0; k<line.indexOf(block); k++) {
						                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						             		@Override
								                	public void run() 
									                {	
								                    	p.teleport(line.get(b.getAndIncrement()));
										            }
							                	   }, jj.incrementAndGet()/50); 
											 }

					                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.1f);
					                    ArrayList<Location> draw = new ArrayList<Location>();
					                    AtomicInteger j = new AtomicInteger();
					                    for(double an = 0; an<Math.PI*2; an+=Math.PI/90) {
					                    	Location pl = p.getLocation().clone();
					                    	pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(3.6));
					                    	draw.add(pl);
					                    }
					                    draw.forEach(pl -> {
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() 
								                {
									        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, pl,1,1,1,1);
								                }
											}, j.incrementAndGet()/900); 
					                    	
					                    });
										for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),3.5, 3.5, 3.5))
										{
											if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
											{
												LivingEntity le = (LivingEntity)e;
												if(le instanceof EnderDragon) {
								                    Arrow firstarrow = p.launchProjectile(Arrow.class);
								                    firstarrow.setDamage(0);
								                    firstarrow.remove();
													Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
														a.setShooter(p);
														a.setCritical(false);
														a.setSilent(true);
														a.setPickupStatus(PickupStatus.DISALLOWED);
														a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
													});
													enar.setDamage(player_damage.get(p.getName())*0.23*(1+ ssd.Rising.get(p.getUniqueId())*0.026));								
												}
												p.setSprinting(true);
												p.setCooldown(p.getInventory().getItemInMainHand().getType(), 0);
												le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
												le.teleport(p);
												le.damage(0, p);
												le.damage(player_damage.get(p.getName())*0.23*(1+ ssd.Rising.get(p.getUniqueId())*0.026), p);
												p.setSprinting(false);
													
											}
										}
					                }
				                    
				                    else {
					                    	line.forEach(i -> {
						                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						             		@Override
								                	public void run() 
									                {	
								             			p.teleport(i);
										            }
							                	   }, jj.incrementAndGet()/50); 
											 });
						                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.1f);
						                    ArrayList<Location> draw = new ArrayList<Location>();
						                    AtomicInteger j = new AtomicInteger();
						                    for(double an = 0; an<Math.PI*2; an+=Math.PI/90) {
						                    	Location pl = p.getLocation().clone();
						                    	pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(3.6));
						                    	draw.add(pl);
						                    }
						                    draw.forEach(pl -> {
												Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									                @Override
									                public void run() 
									                {
										        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, pl,1,1,1,1);
									                }
												}, j.incrementAndGet()/900); 
						                    	
						                    });
											for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),3.5, 3.5, 3.5))
											{
												if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
												{
													LivingEntity le = (LivingEntity)e;
													if(le instanceof EnderDragon) {
									                    Arrow firstarrow = p.launchProjectile(Arrow.class);
									                    firstarrow.setDamage(0);
									                    firstarrow.remove();
														Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
															a.setShooter(p);
															a.setCritical(false);
															a.setSilent(true);
															a.setPickupStatus(PickupStatus.DISALLOWED);
															a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
														});
														enar.setDamage(player_damage.get(p.getName())*0.23*(1+ ssd.Rising.get(p.getUniqueId())*0.026));								
													}
													p.setSprinting(true);
													p.setCooldown(p.getInventory().getItemInMainHand().getType(), 0);
													le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
													le.teleport(p);
													le.damage(0, p);
													le.damage(player_damage.get(p.getName())*0.23*(1+ ssd.Rising.get(p.getUniqueId())*0.026), p);
													p.setSprinting(false);
														
												}
											}
				                    	}
				                }
				            }, i*2); 
	                }
    				rscooldown.put(p.getName(), System.currentTimeMillis());  
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}

	@EventHandler
	public void crit_draw(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 8;
		if(playerclass.get(p.getUniqueId()) == 0) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && p.isSneaking())
			{
				ev.setCancelled(true);
				if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD"))
				{
					
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (cdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use crit_draw").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.1f);
		                    ArrayList<Location> draw = new ArrayList<Location>();
		                    AtomicInteger j = new AtomicInteger();
		                    for(double an = 0; an<Math.PI*2; an+=Math.PI/90) {
		                    	Location pl = p.getLocation().clone();
		                    	pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(6));
		                    	draw.add(pl);
		                    }
		                    draw.forEach(pl -> {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
						        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, pl,1);
					                }
								}, j.incrementAndGet()/900); 
		                    	
		                    });
							for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),6, 2, 6))
							{
								if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
								{
									LivingEntity le = (LivingEntity)e;
										{
											if(le instanceof EnderDragon) {
							                    Arrow firstarrow = p.launchProjectile(Arrow.class);
							                    firstarrow.setDamage(0);
							                    firstarrow.remove();
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
													a.setShooter(p);
													a.setCritical(false);
													a.setSilent(true);
													a.setPickupStatus(PickupStatus.DISALLOWED);
													a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*0.9*(1+ ssd.CriticalDraw.get(p.getUniqueId())*0.05));								
											}
											p.setSprinting(true);
											p.setCooldown(p.getInventory().getItemInMainHand().getType(), 0);
											le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
											le.damage(0, p);
											le.damage(player_damage.get(p.getName())*0.9*(1+ ssd.CriticalDraw.get(p.getUniqueId())*0.05), p);
											p.setSprinting(false);
										
										}
								}
							}
							cdcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		        		p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.1f);
	                    ArrayList<Location> draw = new ArrayList<Location>();
	                    AtomicInteger j = new AtomicInteger();
	                    for(double an = 0; an<Math.PI*2; an+=Math.PI/90) {
	                    	Location pl = p.getLocation().clone();
	                    	pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(6));
	                    	draw.add(pl);
	                    }
	                    draw.forEach(pl -> {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
					        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, pl,1);
				                }
							}, j.incrementAndGet()/900); 
	                    	
	                    });
						for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),6, 2, 6))
						{
							if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)e;
									{
										if(le instanceof EnderDragon) {
						                    Arrow firstarrow = p.launchProjectile(Arrow.class);
						                    firstarrow.setDamage(0);
						                    firstarrow.remove();
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
												a.setShooter(p);
												a.setCritical(false);
												a.setSilent(true);
												a.setPickupStatus(PickupStatus.DISALLOWED);
												a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*0.9*(1+ ssd.CriticalDraw.get(p.getUniqueId())*0.067));								
										}
										p.setSprinting(true);
										p.setCooldown(p.getInventory().getItemInMainHand().getType(), 0);
										le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
										le.damage(0, p);
										le.damage(player_damage.get(p.getName())*0.9*(1+ ssd.CriticalDraw.get(p.getUniqueId())*0.067), p);
										p.setSprinting(false);
									}
							}
						}
		                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}}
		}
	
	}
	
	@EventHandler
	public void flashyRush(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 4;

        
		
		
		if(playerclass.get(p.getUniqueId()) == 0) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD"))
			{
				

				if(frcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (frcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use flashyRush").create());
	                }
	                else // if timer is done
	                {
	                    frcooldown.remove(p.getName()); // removing player from HashMap
	                    ArrayList<Location> line = new ArrayList<Location>();
	                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 0f);
	                    AtomicInteger j = new AtomicInteger(0);
	                    AtomicInteger b = new AtomicInteger(0);
	                    for(double d = 0; d <= 5; d += 0.1) {
		                    Location pl = p.getEyeLocation();
							pl.add(pl.clone().getDirection().normalize().multiply(d));
							line.add(pl);
	                    }
	                    if(line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().isPresent()) {
		                    Location block =line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().get();
		                    for(int k=0; k<line.indexOf(block); k++) {
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
					                    	p.teleport(line.get(b.getAndIncrement()));
											p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 6, 0.1,0.1,0.1,0);
					                    	for (Entity e : p.getNearbyEntities(2, 2, 2))
											{
					                    		if ((!(e == p))&& e instanceof LivingEntity) 
												{
													LivingEntity le = (LivingEntity)e;
													les.add(le);		                    			
												}
											}
					                    	p.setFallDistance(0);
							            }
				                	   }, j.incrementAndGet()/50); 
								 }

	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	

		             				for(LivingEntity le: les) {
										if(le instanceof EnderDragon) {
						                    Arrow firstarrow = p.launchProjectile(Arrow.class);
						                    firstarrow.setDamage(0);
						                    firstarrow.remove();
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
												a.setShooter(p);
												a.setCritical(false);
												a.setSilent(true);
												a.setPickupStatus(PickupStatus.DISALLOWED);
												a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*0.73 *(1+ ssd.FlashyRush.get(p.getUniqueId())*0.042));								
										}
		             					p.setSprinting(true);
										le.damage(0,p);
										le.damage(player_damage.get(p.getName())*0.73 *(1+ ssd.FlashyRush.get(p.getUniqueId())*0.042),p);
										if(le.isDead() && !reset.containsKey(p)) {
											reset.put(p, 1l);
										}
		             					p.setSprinting(false);
		             				}

						            }
	                	   }, j.incrementAndGet()/50); 
		                }
	                    
	                    else {
		                    	line.forEach(i -> {
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
					             			p.teleport(i);
											p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 6, 0.1,0.1,0.1,0);
					                    	for (Entity e : p.getWorld().getNearbyEntities(i,1.5, 1.5, 1.5))
											{
					                    		if ((!(e == p))&& e instanceof LivingEntity && !(e.hasMetadata("fake"))) 
												{
													LivingEntity le = (LivingEntity)e;
													les.add(le);
												}
											}
					                    	p.setFallDistance(0);
							            }
				                	   }, j.incrementAndGet()/50); 
								 });
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	

			             				for(LivingEntity le: les) {
											if(le instanceof EnderDragon) {
							                    Arrow firstarrow = p.launchProjectile(Arrow.class);
							                    firstarrow.setDamage(0);
							                    firstarrow.remove();
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
													a.setShooter(p);
													a.setCritical(false);
													a.setSilent(true);
													a.setPickupStatus(PickupStatus.DISALLOWED);
													a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*0.73 *(1+ ssd.FlashyRush.get(p.getUniqueId())*0.042));								
											}
			             					p.setSprinting(true);
											le.damage(0,p);
											le.damage(player_damage.get(p.getName())*0.73 *(1+ ssd.FlashyRush.get(p.getUniqueId())*0.042),p);
											if(le.isDead() && !reset.containsKey(p)) {
												reset.put(p, 1l);
											}
			             					p.setSprinting(false);
			             				}

							            }
		                	   }, j.incrementAndGet()/50); 
	                    	}

                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
			                	public void run() 
				                {	

									 if(reset.containsKey(p)) {
						                    frcooldown.remove(p.getName()); // removing player from HashMap
						                    reset.remove(p);
									 }

					            }
                	   }, j.incrementAndGet()/50); 
						frcooldown.put(p.getName(), System.currentTimeMillis());  
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    ArrayList<Location> line = new ArrayList<Location>();
                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2f);
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 0f);
                    AtomicInteger j = new AtomicInteger(0);
                    AtomicInteger b = new AtomicInteger(0);
                    for(double d = 0; d <= 5; d += 0.1) {
	                    Location pl = p.getEyeLocation();
						pl.add(pl.clone().getDirection().normalize().multiply(d));
						line.add(pl);
                    }
                    if(line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().isPresent()) {
	                    Location block =line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().get();
	                    for(int k=0; k<line.indexOf(block); k++) {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
				                    	p.teleport(line.get(b.getAndIncrement()));
										p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 6, 0.1,0.1,0.1,0);
				                    	for (Entity e : p.getNearbyEntities(2, 2, 2))
										{
				                    		if ((!(e == p))&& e instanceof LivingEntity) 
											{
												LivingEntity le = (LivingEntity)e;
												les.add(le);		                    			
											}
										}
				                    	p.setFallDistance(0);
						            }
			                	   }, j.incrementAndGet()/50); 
							 }

                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
			                	public void run() 
				                {	

	             				for(LivingEntity le: les) {
									if(le instanceof EnderDragon) {
					                    Arrow firstarrow = p.launchProjectile(Arrow.class);
					                    firstarrow.setDamage(0);
					                    firstarrow.remove();
										Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
											a.setShooter(p);
											a.setCritical(false);
											a.setSilent(true);
											a.setPickupStatus(PickupStatus.DISALLOWED);
											a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
										});
										enar.setDamage(player_damage.get(p.getName())*0.73 *(1+ ssd.FlashyRush.get(p.getUniqueId())*0.042));								
									}
	             					p.setSprinting(true);
									le.damage(0,p);
									le.damage(player_damage.get(p.getName())*0.73 *(1+ ssd.FlashyRush.get(p.getUniqueId())*0.042),p);
									if(le.isDead() && !reset.containsKey(p)) {
										reset.put(p, 1l);
									}
	             					p.setSprinting(false);
	             				}

					            }
                	   }, j.incrementAndGet()/50); 
	                }
                    
                    else {
	                    	line.forEach(i -> {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
				             			p.teleport(i);
										p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 6, 0.1,0.1,0.1,0);
				                    	for (Entity e : p.getWorld().getNearbyEntities(i,1.5, 1.5, 1.5))
										{
				                    		if ((!(e == p))&& e instanceof LivingEntity && !(e.hasMetadata("fake"))) 
											{
												LivingEntity le = (LivingEntity)e;
												les.add(le);
											}
										}
				                    	p.setFallDistance(0);
						            }
			                	   }, j.incrementAndGet()/50); 
							 });
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	

		             				for(LivingEntity le: les) {
										if(le instanceof EnderDragon) {
						                    Arrow firstarrow = p.launchProjectile(Arrow.class);
						                    firstarrow.setDamage(0);
						                    firstarrow.remove();
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
												a.setShooter(p);
												a.setCritical(false);
												a.setSilent(true);
												a.setPickupStatus(PickupStatus.DISALLOWED);
												a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*0.73 *(1+ ssd.FlashyRush.get(p.getUniqueId())*0.042));								
										}
		             					p.setSprinting(true);
										le.damage(0,p);
										le.damage(player_damage.get(p.getName())*0.73 *(1+ ssd.FlashyRush.get(p.getUniqueId())*0.042),p);
										if(le.isDead() && !reset.containsKey(p)) {
											reset.put(p, 1l);
										}
		             					p.setSprinting(false);
		             				}

						            }
	                	   }, j.incrementAndGet()/50); 
                    	}

                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
		                	public void run() 
			                {	

								 if(reset.containsKey(p)) {
					                    frcooldown.remove(p.getName()); // removing player from HashMap
					                    reset.remove(p);
								 }

				            }
            	   }, j.incrementAndGet()/50); 
    				frcooldown.put(p.getName(), System.currentTimeMillis());  
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	

	@EventHandler
	public void strike(EntityDamageByEntityEvent d) 
	{
		int sec = 3;
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
        

		
		
		if(playerclass.get(p.getUniqueId()) == 0) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && !(p.isSneaking()) && !(p.isOnGround()) && !(p.isSprinting()))
			{
					
					if(p.getAttackCooldown()==1) 
					{
						if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			            {
			                long timer = (smcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			                if(!(timer < 0)) // if timer is still more then 0 or 0
			                {
			                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Strike").create());
			                }
			                else // if timer is done
			                {
			                    smcooldown.remove(p.getName()); // removing player from HashMap
			                    ArrayList<Location> line = new ArrayList<Location>();
			                    ArrayList<Location> fill = new ArrayList<Location>();
			                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
			                    AtomicInteger j = new AtomicInteger();
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.5f);
			                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 1.5f);
			                    for(double an = Math.PI/3; an>-Math.PI/3; an-=Math.PI/180) {
			                    	Location pl = p.getLocation();
			                    	Vector v = pl.clone().getDirection().rotateAroundY(Math.PI/2);
			                    	pl.add(pl.getDirection().rotateAroundAxis(v,an).normalize().multiply(5));
			                    	line.add(pl);
			                    }
			                    line.forEach(l -> {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
							        		p.getWorld().spawnParticle(Particle.FLASH, l,5,0.1,0.1,0.1,0);
							        		p.getWorld().spawnParticle(Particle.CRIT, l,5,0.1,0.1,0.1,0);
							        		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l,5,0.1,0.1,0.1,0, Material.GLOWSTONE.createBlockData());
						                    for(double i = 0; i<5;i+=0.1) {
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
					                    	for(Entity e: l.getWorld().getNearbyEntities(l,1,1,1)) {
												if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
												{
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
											if(le instanceof EnderDragon) {
							                    Arrow firstarrow = p.launchProjectile(Arrow.class);
							                    firstarrow.setDamage(0);
							                    firstarrow.remove();
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
													a.setShooter(p);
													a.setCritical(false);
													a.setSilent(true);
													a.setPickupStatus(PickupStatus.DISALLOWED);
													a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*1.2 *(1+ ssd.Strike.get(p.getUniqueId())*0.045));								
											}
											p.setSprinting(true);
											le.damage(0, p);
											le.damage(player_damage.get(p.getName())*1.2 *(1+ ssd.Strike.get(p.getUniqueId())*0.045), p);
											p.setSprinting(false);
										}
					                    
					                }
								}, j.incrementAndGet()/900); 
								smcooldown.put(p.getName(), System.currentTimeMillis());  
			                }
			            }
			            else // if cooldown doesn't have players name in it
			            {
		                    ArrayList<Location> line = new ArrayList<Location>();
		                    ArrayList<Location> fill = new ArrayList<Location>();
		                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
		                    AtomicInteger j = new AtomicInteger();
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.5f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 1.5f);
		                    for(double an = Math.PI/3; an>-Math.PI/3; an-=Math.PI/180) {
		                    	Location pl = p.getLocation();
		                    	Vector v = pl.clone().getDirection().rotateAroundY(Math.PI/2);
		                    	pl.add(pl.getDirection().rotateAroundAxis(v,an).normalize().multiply(5));
		                    	line.add(pl);
		                    }
		                    line.forEach(l -> {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
						        		p.getWorld().spawnParticle(Particle.FLASH, l,5,0.1,0.1,0.1,0);
						        		p.getWorld().spawnParticle(Particle.CRIT, l,5,0.1,0.1,0.1,0);
						        		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l,5,0.1,0.1,0.1,0, Material.GLOWSTONE.createBlockData());
					                    for(double i = 0; i<5;i+=0.1) {
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
				                    	for(Entity e: l.getWorld().getNearbyEntities(l,1,1,1)) {
											if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
											{
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
										if(le instanceof EnderDragon) {
						                    Arrow firstarrow = p.launchProjectile(Arrow.class);
						                    firstarrow.setDamage(0);
						                    firstarrow.remove();
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
												a.setShooter(p);
												a.setCritical(false);
												a.setSilent(true);
												a.setPickupStatus(PickupStatus.DISALLOWED);
												a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*1.2 *(1+ ssd.Strike.get(p.getUniqueId())*0.045));								
										}
										p.setSprinting(true);
										le.damage(0, p);
										le.damage(player_damage.get(p.getName())*1.2 *(1+ ssd.Strike.get(p.getUniqueId())*0.045), p);
										p.setSprinting(false);
									}
				                    
				                }
							}, j.incrementAndGet()/900); 
							smcooldown.put(p.getName(), System.currentTimeMillis());  
						}
					 
					
					}
				}
		}
		}
	}
	@EventHandler
	public void strike(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 3;

        
		
		
		if(playerclass.get(p.getUniqueId()) == 0) {
		if(p.getAttackCooldown()==1) 
			{	
			
				if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && !(p.isSneaking()) && !(p.isOnGround()))
					{
					if((ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK))
					{
						ev.setCancelled(true);
						if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			            {
			                long timer = (smcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			                if(!(timer < 0)) // if timer is still more then 0 or 0
			                {
			                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Strike").create());
			                }
			                else // if timer is done
			                {
			                    smcooldown.remove(p.getName()); // removing player from HashMap
			                    ArrayList<Location> line = new ArrayList<Location>();
			                    ArrayList<Location> fill = new ArrayList<Location>();
			                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
			                    AtomicInteger j = new AtomicInteger();
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.5f);
			                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 1.5f);
			                    for(double an = Math.PI/3; an>-Math.PI/3; an-=Math.PI/180) {
			                    	Location pl = p.getLocation();
			                    	Vector v = pl.clone().getDirection().rotateAroundY(Math.PI/2);
			                    	pl.add(pl.getDirection().rotateAroundAxis(v,an).normalize().multiply(5));
			                    	line.add(pl);
			                    }
			                    line.forEach(l -> {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
							        		p.getWorld().spawnParticle(Particle.FLASH, l,5,0.1,0.1,0.1,0);
							        		p.getWorld().spawnParticle(Particle.CRIT, l,5,0.1,0.1,0.1,0);
							        		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l,5,0.1,0.1,0.1,0, Material.GLOWSTONE.createBlockData());
						                    for(double i = 0; i<5;i+=0.1) {
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
					                    	for(Entity e: l.getWorld().getNearbyEntities(l,1,1,1)) {
												if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
												{
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
											if(le instanceof EnderDragon) {
							                    Arrow firstarrow = p.launchProjectile(Arrow.class);
							                    firstarrow.setDamage(0);
							                    firstarrow.remove();
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
													a.setShooter(p);
													a.setCritical(false);
													a.setSilent(true);
													a.setPickupStatus(PickupStatus.DISALLOWED);
													a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*1.2 *(1+ ssd.Strike.get(p.getUniqueId())*0.045));								
											}
											p.setSprinting(true);
											le.damage(0, p);
											le.damage(player_damage.get(p.getName())*1.2 *(1+ ssd.Strike.get(p.getUniqueId())*0.045), p);
											p.setSprinting(false);
										}
					                    
					                }
								}, j.incrementAndGet()/900); 
								smcooldown.put(p.getName(), System.currentTimeMillis());  
			                }
			            }
			            else // if cooldown doesn't have players name in it
			            {
		                    ArrayList<Location> line = new ArrayList<Location>();
		                    ArrayList<Location> fill = new ArrayList<Location>();
		                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
		                    AtomicInteger j = new AtomicInteger();
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.5f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 1.5f);
		                    for(double an = Math.PI/3; an>-Math.PI/3; an-=Math.PI/180) {
		                    	Location pl = p.getLocation();
		                    	Vector v = pl.clone().getDirection().rotateAroundY(Math.PI/2);
		                    	pl.add(pl.getDirection().rotateAroundAxis(v,an).normalize().multiply(5));
		                    	line.add(pl);
		                    }
		                    line.forEach(l -> {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
						        		p.getWorld().spawnParticle(Particle.FLASH, l,5,0.1,0.1,0.1,0);
						        		p.getWorld().spawnParticle(Particle.CRIT, l,5,0.1,0.1,0.1,0);
						        		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l,5,0.1,0.1,0.1,0, Material.GLOWSTONE.createBlockData());
					                    for(double i = 0; i<5;i+=0.1) {
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
				                    	for(Entity e: l.getWorld().getNearbyEntities(l,1,1,1)) {
											if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
											{
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
										if(le instanceof EnderDragon) {
						                    Arrow firstarrow = p.launchProjectile(Arrow.class);
						                    firstarrow.setDamage(0);
						                    firstarrow.remove();
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
												a.setShooter(p);
												a.setCritical(false);
												a.setSilent(true);
												a.setPickupStatus(PickupStatus.DISALLOWED);
												a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*1.2 *(1+ ssd.Strike.get(p.getUniqueId())*0.045));								
										}
										p.setSprinting(true);
										le.damage(0, p);
										le.damage(player_damage.get(p.getName())*1.2 *(1+ ssd.Strike.get(p.getUniqueId())*0.045), p);
										p.setSprinting(false);
									}
				                    
				                }
							}, j.incrementAndGet()/900); 
							smcooldown.put(p.getName(), System.currentTimeMillis());  
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
        
		
		
		
		
			if(playerclass.get(p.getUniqueId()) == 0 && (is.getType().name().contains("SWORD")) && p.isSneaking())
			{
				ev.setCancelled(true);
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (sultcooldown.get(p.getName())/1000 + 45) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Swordstorm").create());
	                }
	                else // if timer is done
	                {
	                    sultcooldown.remove(p.getName()); // removing player from HashMap
	                    AtomicInteger j = new AtomicInteger();
	                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 0.1f);
						p.getWorld().spawnParticle(Particle.CLOUD, l, 10, 8, 2, 8);
						p.sendTitle(ChatColor.BLUE + "SWORDSTORM", null);
						final Location pl = p.getLocation();
						pl.setY(pl.getY()+3);
						for (Entity e : p.getWorld().getNearbyEntities(l, 15, 15, 15))
						{
							if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)e;
									{
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
											                	p.teleport(le);
																p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, le.getLocation(), 1);
																p.playSound(le.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.5f);
																p.setInvulnerable(true);
							                }
										}, j.incrementAndGet()); 
									}
							}
								
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								p.teleport(pl);
								p.setInvulnerable(false);
			                	for (Entity e : p.getWorld().getNearbyEntities(l, 15, 15, 15))
								{
									if ((!(e instanceof Player))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
									{
										LivingEntity le = (LivingEntity)e;
											{

												if(le instanceof EnderDragon) {
								                    Arrow firstarrow = p.launchProjectile(Arrow.class);
								                    firstarrow.setDamage(0);
								                    firstarrow.remove();
													Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
														a.setShooter(p);
														a.setCritical(false);
														a.setSilent(true);
														a.setPickupStatus(PickupStatus.DISALLOWED);
														a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
													});
													enar.setDamage(player_damage.get(p.getName())*7);								
												}
												le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
												p.setSprinting(true);
												le.damage(0, p);
												le.damage(player_damage.get(p.getName())*7, p);
												p.setSprinting(false);
												le.playEffect(org.bukkit.EntityEffect.HURT_EXPLOSION);
												p.getWorld().spawnParticle(Particle.FLASH, le.getLocation(), 1);
												p.playSound(le.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 0.8f);
											}
									}
								}
			                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 1f);
			                }
			            }, j.incrementAndGet()+10); 
		                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	AtomicInteger j = new AtomicInteger();
                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 0.1f);
					p.getWorld().spawnParticle(Particle.CLOUD, l, 10, 8, 2, 8);
					p.sendTitle(ChatColor.BLUE + "SWORDSTORM", null);
					final Location pl = p.getLocation();
					pl.setY(pl.getY()+3);
					for (Entity e : p.getWorld().getNearbyEntities(l, 15, 15, 15))
					{
						if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
						{
							LivingEntity le = (LivingEntity)e;
								{
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
										                	p.teleport(le);
															p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, le.getLocation(), 1);
															p.playSound(le.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.5f);
															p.setInvulnerable(true);
						                }
									}, j.incrementAndGet()); 
								}
						}
							
					}
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
							p.teleport(pl);
							p.setInvulnerable(false);
		                	for (Entity e : p.getWorld().getNearbyEntities(l, 15, 15, 15))
							{
								if ((!(e instanceof Player))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
								{
									LivingEntity le = (LivingEntity)e;
										{

											if(le instanceof EnderDragon) {
							                    Arrow firstarrow = p.launchProjectile(Arrow.class);
							                    firstarrow.setDamage(0);
							                    firstarrow.remove();
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
													a.setShooter(p);
													a.setCritical(false);
													a.setSilent(true);
													a.setPickupStatus(PickupStatus.DISALLOWED);
													a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*7);								
											}
											le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
											p.setSprinting(true);
											le.damage(0, p);
											le.damage(player_damage.get(p.getName())*7, p);
											p.setSprinting(false);
											le.playEffect(org.bukkit.EntityEffect.HURT_EXPLOSION);
											p.getWorld().spawnParticle(Particle.FLASH, le.getLocation(), 1);
											p.playSound(le.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 0.8f);
										}
								}
							}
		                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 1f);
		                }
		            }, j.incrementAndGet()+10); 
					
	                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();
        

		
		
		if(playerclass.get(p.getUniqueId()) == 0)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && (p.getInventory().getItemInOffHand().getType().name().contains("SWORD")))
			{
					e.setCancelled(true);
			}
		}
		
	}
		
	@EventHandler
	public void guard(EntityDamageEvent d) 
	{
		int sec = 5;
		if(d.getEntity() instanceof Player) 
		{
		Player p = (Player)d.getEntity();
        

		
		
		if(playerclass.get(p.getUniqueId()) == 0) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD")&& (p.isSneaking()))
					{
					
					if(gdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            long timer = (gdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to Guard").create());
			            }
			            else // if timer is done
			            {
			                gdcooldown.remove(p.getName()); // removing player from HashMap
			                guard.computeIfPresent(p.getUniqueId(), (k,v)->v+1);
			                guard.putIfAbsent(p.getUniqueId(), 0);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
					            	guard.computeIfPresent(p.getUniqueId(), (k,v)->v-1);
					            	if(guard.get(p.getUniqueId())<0) {
					            		guard.remove(p.getUniqueId());
					            	}
				                }
							}, 30); 
							gdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
		                guard.computeIfPresent(p.getUniqueId(), (k,v)->v+1);
		                guard.putIfAbsent(p.getUniqueId(), 0);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
				            	guard.computeIfPresent(p.getUniqueId(), (k,v)->v-1);
				            	if(guard.get(p.getUniqueId())<0) {
				            		guard.remove(p.getUniqueId());
				            	}
			                }
						}, 30); 
						gdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			        }
	                if(guard.containsKey(p.getUniqueId())) {
						d.setDamage(d.getDamage()*(0.2 - ssd.Guard.get(p.getUniqueId())*0.02));
						p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 0.6f, 1.3f);
						p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1.0f, 0.3f);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Guard]").color(ChatColor.BOLD).color(ChatColor.BLUE).create());
	                }
					
			}
		}}
	}
	
	@EventHandler
	public void Dualbladed(EntityDamageByEntityEvent d) 
	{
		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
	        
	
			
			
			if(playerclass.get(p.getUniqueId()) == 0) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && (p.getInventory().getItemInOffHand().getType().name().contains("SWORD")))
					{
						
							d.setDamage(d.getDamage()*(1.5+ssd.Dualbladed.get(p.getUniqueId())*0.0336));
			            	p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 2);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
					            	p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, le.getLocation(), 2);
					        		p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 0.6f, 2f);
					        		p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.6f, 2f);
				                }
							}, 6); 
					}
			}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof EnderDragon) 
		{
			Arrow ar = (Arrow)d.getDamager();
			EnderDragon le = (EnderDragon)d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
		        

				
				

				if(playerclass.get(p.getUniqueId()) == 0) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && (p.getInventory().getItemInOffHand().getType().name().contains("SWORD")))
					{
						
						d.setDamage(d.getDamage()*(1.5+ssd.Dualbladed.get(p.getUniqueId())*0.0336));
		            	p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 2);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
				            	p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, le.getLocation(), 2);
				        		p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 0.6f, 2f);
				        		p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.6f, 2f);
			                }
						}, 6); 
					}
			}
			}
		}
	}

	@EventHandler
	public void Damagegetter(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 0) {
	            
				if(p.getInventory().getItemInMainHand().getType()==Material.IRON_SWORD)
				{
						player_damage.put(p.getName(), 7 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_SWORD)
				{
						player_damage.put(p.getName(), 8 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_SWORD)
				{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.STONE_SWORD)
				{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_SWORD)
				{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_SWORD)
				{
						player_damage.put(p.getName(), 10 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
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
        

		
		
		if(playerclass.get(p.getUniqueId()) == 0) {
            
			if(p.getInventory().getItemInMainHand().getType()==Material.IRON_SWORD)
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
			}
			
			if(p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_SWORD)
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
			}
			
			if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_SWORD)
			{
					player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.STONE_SWORD)
			{
					player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_SWORD)
			{
					player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_SWORD)
			{
					player_damage.put(p.getName(), 10 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			    
				
				

				if(playerclass.get(p.getUniqueId()) == 0) {
		            
					if(p.getInventory().getItemInMainHand().getType()==Material.IRON_SWORD)
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
					}
					
					if(p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_SWORD)
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
					}
					
					if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_SWORD)
					{
							player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.STONE_SWORD)
					{
							player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_SWORD)
					{
							player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_SWORD)
					{
							player_damage.put(p.getName(), 10 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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



