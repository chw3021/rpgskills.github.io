package io.github.chw3021.archer;




import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import io.github.chw3021.classes.ClassData;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.rmain.RMain;
import io.github.chw3021.swordman.SwordSkillsData;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.FixedMetadataValue;

public class Archskills implements Serializable, Listener {

	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = -7821279144455667112L;
	private HashMap<String, Integer> sa = new HashMap<String, Integer>();
	private HashMap<String, Long> hscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> arcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gacooldown = new HashMap<String, Long>();
	private HashMap<String, Long> dpcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> bultcooldown = new HashMap<String, Long>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private HashMap<Player, Projectile> arrow = new HashMap<Player, Projectile>();
	private HashMap<Projectile, Player> shooter = new HashMap<Projectile, Player>();
	private HashMap<Player, LivingEntity> grabbed = new HashMap<Player, LivingEntity>();
	private HashMap<Player, Integer> indure = new HashMap<Player, Integer>();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private ArchSkillsData asd;

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
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Archskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				ArchSkillsData a = new ArchSkillsData(ArchSkillsData.loadData(path +"/plugins/RPGskills/ArchSkillsData.data"));
				asd = a;
			}
			
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		ArchSkillsData a = new ArchSkillsData(ArchSkillsData.loadData(path +"/plugins/RPGskills/ArchSkillsData.data"));
		asd = a;
		
	}

	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		ArchSkillsData a = new ArchSkillsData(ArchSkillsData.loadData(path +"/plugins/RPGskills/ArchSkillsData.data"));
		asd = a;
	}
	
	@EventHandler
	public void Arrowshoot(EntityShootBowEvent a) 
	{
		
		if(a.getEntity() instanceof Player)
		{
			Player p = (Player) a.getEntity();
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
					if(playerclass.get(p.getUniqueId()) == 6) {
					if(a.getProjectile().getType() == EntityType.ARROW)
					{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.ARROW_DAMAGE)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName()) + p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
						Arrow far = (Arrow)a.getProjectile();
						arrow.put(p, far);
						shooter.put(arrow.get(p), p);
						far.setDamage(far.getDamage()*(1+ player_damage.get(p.getName())*0.01));
						for(int i =0; i<2; i++) {
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 2, 2);
				                	ar.setShooter(p);
				                	ar.setBounce(false);
									shooter.put(ar, p);
									p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
				                	ar.setCritical(true);
				                	ar.setDamage(far.getDamage()*0.62*(1+ player_damage.get(p.getName())*0.01) * (1+asd.TripleShot.get(p.getUniqueId())*0.022));
				                    ar.setPickupStatus(PickupStatus.DISALLOWED);
				                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                	ar.remove();
						                }
				                    }, 20); 
				                }
				            }, i*2); 
		                }
						if(grabbed.containsKey(p))
						{
							Shotmob(p);
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Shot!").color(ChatColor.DARK_RED).create());
						}
						
					}
				}
			}
		}
	}
		
	@EventHandler
	public void Arrowshoot(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();
			if(playerclass.get(p.getUniqueId()) == 6) {
				player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.ARROW_DAMAGE)*0.5 + p.getLevel()/10);
				
				if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
				{
					player_damage.put(p.getName(),player_damage.get(p.getName()) + p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
				}
			}
			
		}
	}
	

	@EventHandler
	public void SpreadingArrows(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();

		if(playerclass.get(p.getUniqueId()) == 6 && p.getAttackCooldown()>=1)	{	
			if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK) && !p.isSneaking()) 
				{
					if(!sa.containsKey(p.getName())) {
	            		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	if(sa.get(p.getName())<3) {
				                    sa.computeIfPresent(p.getName(), (k,v) -> v+1);
				    				p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"SpreadingArrows "+sa.get(p.getName()) + "/3").create());
			                	}
			                }
						}, 100, 100);
						sa.putIfAbsent(p.getName(), 3);
	                    Arrow firstarrow = p.launchProjectile(Arrow.class);
	                    firstarrow.setDamage(0);
	                    firstarrow.remove();
						ArrayList<Location> line = new ArrayList<Location>();
	                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 0f);
	                    AtomicInteger j = new AtomicInteger(0);
	                    AtomicInteger b = new AtomicInteger(0);
	                    for(double d = 0; d <= 3; d += 0.1) {
		                    Location pl = p.getEyeLocation();
							pl.add(pl.clone().getDirection().normalize().multiply(d));
							line.add(pl);
	                        Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getLocation().getDirection().normalize().multiply(-1), 1, 15);
	                        ar.setCritical(false);
	                        ar.setDamage(0);
	                        ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                        ar.setPickupStatus(PickupStatus.DISALLOWED);
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
		             					ar.remove();
		             				}

	                	   }, 20); 
	                    }
	                    if(line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().isPresent()) {
		                    Location block =line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().get();
		                    for(int k=0; k<line.indexOf(block); k++) {
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
					                    	p.teleport(line.get(b.getAndIncrement()));
											p.getWorld().spawnParticle(Particle.CRIT, p.getLocation(), 6, 0.1,0.1,0.1,0);
					                    	for (Entity e : p.getNearbyEntities(1.5, 1.5, 1.5))
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
											enar.setDamage(player_damage.get(p.getName())*0.5 *(1));								
										}
		             					p.setSprinting(true);
										le.damage(0,p);
										le.damage(player_damage.get(p.getName())*0.5 *(1),p);
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
											p.getWorld().spawnParticle(Particle.CRIT, p.getLocation(), 6, 0.1,0.1,0.1,0);
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
												enar.setDamage(player_damage.get(p.getName())*0.5 *(1));								
											}
			             					p.setSprinting(true);
											le.damage(0,p);
											le.damage(player_damage.get(p.getName())*0.5 *(1),p);
			             					p.setSprinting(false);
			             				}

							            }
		                	   }, j.incrementAndGet()/50); 
	                    	}
	                    sa.computeIfPresent(p.getName(), (k,v) -> v-1);
	    				p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"SpreadingArrows "+sa.get(p.getName()) + "/3").create());
					}
					else if(sa.get(p.getName())>0) {
						Arrow firstarrow = p.launchProjectile(Arrow.class);
	                    firstarrow.setDamage(0);
	                    firstarrow.remove();
						ArrayList<Location> line = new ArrayList<Location>();
	                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 0f);
	                    AtomicInteger j = new AtomicInteger(0);
	                    AtomicInteger b = new AtomicInteger(0);
	                    for(double d = 0; d <= 3; d += 0.1) {
		                    Location pl = p.getEyeLocation();
							pl.add(pl.clone().getDirection().normalize().multiply(d));
							line.add(pl);
	                        Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getLocation().getDirection().normalize().multiply(-1), 1, 15);
	                        ar.setCritical(false);
	                        ar.setDamage(0);
	                        ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                        ar.setPickupStatus(PickupStatus.DISALLOWED);
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
		             					ar.remove();
		             				}

	                	   }, 20); 
	                    }
	                    if(line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().isPresent()) {
		                    Location block =line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().get();
		                    for(int k=0; k<line.indexOf(block); k++) {
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
					                    	p.teleport(line.get(b.getAndIncrement()));
											p.getWorld().spawnParticle(Particle.CRIT, p.getLocation(), 6, 0.1,0.1,0.1,0);
					                    	for (Entity e : p.getNearbyEntities(1.5, 1.5, 1.5))
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
											enar.setDamage(player_damage.get(p.getName())*0.5 *(1));								
										}
		             					p.setSprinting(true);
										le.damage(0,p);
										le.damage(player_damage.get(p.getName())*0.5 *(1),p);
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
											p.getWorld().spawnParticle(Particle.CRIT, p.getLocation(), 6, 0.1,0.1,0.1,0);
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
												enar.setDamage(player_damage.get(p.getName())*0.5 *(1));								
											}
			             					p.setSprinting(true);
											le.damage(0,p);
											le.damage(player_damage.get(p.getName())*0.5 *(1),p);
			             					p.setSprinting(false);
			             				}

							            }
		                	   }, j.incrementAndGet()/50); 
	                    	}
	                    sa.computeIfPresent(p.getName(), (k,v) -> v-1);
	    				p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"SpreadingArrows "+sa.get(p.getName()) + "/3").create());
					}
		                	
				}
			}
		}
	}
	
	@EventHandler
	public void Indure(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		if(playerclass.get(p.getUniqueId()) == 6)	{	
			if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
			{
				if(ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK) 
				{
					indure.computeIfPresent(p, (k,v) -> v+1);
					indure.putIfAbsent(p, 0);
                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
							indure.computeIfPresent(p, (k,v) -> v-1);
							if(indure.get(p)<0) {
								indure.remove(p);
							}
		                }
		            }, 20);
				}
			}
		}
	}
	
	@EventHandler
	public void Indure(EntityDamageEvent d) 
	{
		if(d.getEntity() instanceof Player) 
		{
			Player p = (Player)d.getEntity();
			if(playerclass.get(p.getUniqueId()) == 6 && indure.containsKey(p)) {
				d.setDamage(d.getDamage()*0.68);
				p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"[Indure]").create());
			}
		}
	}
	

	@EventHandler
	public void Retrieve(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 3;
		if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
		{
		if(playerclass.get(p.getUniqueId()) == 6) {
		if(!p.isSneaking())
		{
					ev.setCancelled(true);
					
					HashMap<UUID, Integer> Retrieve = asd.getArcherdata().Retrieve;
					if(gacooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
	                    
		                long timer = (gacooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Retrieve").create());
			            }
		                else // if timer is done
		                {
		                    gacooldown.remove(p.getName()); // removing player from HashMap
		                    Location l = p.getLocation();
			            	Arrow ar = p.launchProjectile(Arrow.class);
			            	ar.setShooter(p);
			            	ar.setDamage(0);
							for (Entity e : p.getWorld().getNearbyEntities(l, 15, 15, 15))
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
												enar.setDamage(le.getArrowsInBody()*(2*asd.Retrieve.get(p.getUniqueId())*2));								
											}
											p.setSprinting(true);
											le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
											le.damage(le.getArrowsInBody()*(2*Retrieve.get(p.getUniqueId())*2), p);
											le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
											le.setArrowsInBody(0);
											p.setSprinting(false);
										}
								}
								if (e instanceof AbstractArrow) 
								{
									AbstractArrow le = (AbstractArrow)e;
									if(le.getShooter() == shooter.get(le))
										{
											le.teleport(p);
										}
								}
							}
							ar.remove();
			                gacooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	Location l = p.getLocation();
		            	Arrow ar = p.launchProjectile(Arrow.class);
		            	ar.setShooter(p);
		            	ar.setDamage(0);
		            	
		            	for (Entity e : p.getWorld().getNearbyEntities(l, 15, 15, 15))
						{
							if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake")) )
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
											enar.setDamage(le.getArrowsInBody()*2*Retrieve.get(p.getUniqueId())*2);								
										}
										p.setSprinting(true);
										le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
										le.damage(le.getArrowsInBody()*2*Retrieve.get(p.getUniqueId())*2, p);
										le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
										le.setArrowsInBody(0);
										p.setSprinting(false);
									}
							}
							if (e instanceof AbstractArrow) 
							{
								AbstractArrow le = (AbstractArrow)e;
								if(le.getShooter() == shooter.get(le))
									{
										le.teleport(p);
									}
							}
						}
						ar.remove();
		                gacooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
		}
		}
	}
	

	@EventHandler
	public void swcancle(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		if(playerclass.get(p.getUniqueId()) == 6) {
		if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
		{
        	ev.setCancelled(true);
		}}

	}
	@EventHandler
	public void RapidFire(PlayerSwapHandItemsEvent ev) 
	{
			Player p = ev.getPlayer();
			int sec = 9;
		    
			
			
			if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
			{
			if(playerclass.get(p.getUniqueId()) == 6) {
			if((p.isSneaking()))
			{
					
					HashMap<UUID, Integer> RapidFire = asd.getArcherdata().RapidFire;
		        	ev.setCancelled(true);
					if(arcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
	                    
		                long timer = (arcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use RapidFire").create());
		                }
		                else // if timer is done
		                {
		                    arcooldown.remove(p.getName()); // removing player from HashMap
		                    Arrow firstarrow = p.launchProjectile(Arrow.class);
		                    firstarrow.setDamage(0);
		                    firstarrow.remove();
		                    ArrayList<Arrow> arar = new ArrayList<Arrow>();
		                    for(int i =0; i<10; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	if(arrow.containsKey(p)) {
						                	Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 10, 1, ((Arrow)arrow.get(p)).getClass());
						                	ar.setShooter(p);
						                	ar.setKnockbackStrength(0);
						                	ar.setDamage(player_damage.get(p.getName())*0.034*(1+RapidFire.get(p.getUniqueId())*0.006));
											shooter.put(ar, p);
											p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
						                	arar.add(ar);
						                	ar.setPickupStatus(PickupStatus.DISALLOWED);
					                	}
					                	else {
						                	Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 10, 1);
						                	ar.setShooter(p);
						                	ar.setKnockbackStrength(0);
						                	ar.setDamage(player_damage.get(p.getName())*0.034*(1+RapidFire.get(p.getUniqueId())*0.006));
											shooter.put(ar, p);
											p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
						                	arar.add(ar);
						                	ar.setPickupStatus(PickupStatus.DISALLOWED);
					                	}
					                }
					            }, i*3); 
		                    }	
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	for(Arrow a : arar)
				                	{
				                		a.remove();
				                	}
				                }
				            }, 31); 
											
			                arcooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
	                    Arrow firstarrow = p.launchProjectile(Arrow.class);
	                    firstarrow.setDamage(0);
	                    firstarrow.remove();
		            	ArrayList<Arrow> arar = new ArrayList<Arrow>();
	                    for(int i =0; i<10; i++) {
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	if(arrow.containsKey(p)) {
					                	Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 10, 1, ((Arrow)arrow.get(p)).getClass());
					                	ar.setShooter(p);
					                	ar.setKnockbackStrength(0);
					                	ar.setDamage(player_damage.get(p.getName())*0.034*(1+RapidFire.get(p.getUniqueId())*0.006));
										shooter.put(ar, p);
										p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
					                	arar.add(ar);
					                	ar.setPickupStatus(PickupStatus.DISALLOWED);
				                	}
				                	else {
					                	Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 10, 1);
					                	ar.setShooter(p);
					                	ar.setKnockbackStrength(0);
					                	ar.setDamage(player_damage.get(p.getName())*0.034*(1+RapidFire.get(p.getUniqueId())*0.006));
										shooter.put(ar, p);
										p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
					                	arar.add(ar);
					                	ar.setPickupStatus(PickupStatus.DISALLOWED);
				                	}
				                }
				            }, i*3); 
	                    }	
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	for(Arrow a : arar)
			                	{
			                		a.remove();
			                	}
			                }
			            }, 31); 
									
		                arcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
					ev.setCancelled(true);
				}
			}}
	}
	

	@EventHandler
	public void MultiShot(EntityShootBowEvent a) 
	{
		int sec = 3;
		
		if(a.getEntity() instanceof Player)
		{
			Player p = (Player) a.getEntity();
		    
			
			
			HashMap<UUID, Integer> MultiShot = asd.getArcherdata().MultiShot;
			
			if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
			{
			if(playerclass.get(p.getUniqueId()) == 6 && p.isSneaking()) {
					if(a.getProjectile().getType() == EntityType.ARROW)
					{
							if(dpcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
				            {
			                    
				                double timer = ((dpcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000); // geting time in seconds
				                if(!(timer < 0)) // if timer is still more then 0 or 0
				                {
				                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + (int)timer + " seconds to use MultiShot").create());
				                }
				                else // if timer is done
				                {
				                	dpcooldown.remove(p.getName());
				                    Arrow firstarrow = p.launchProjectile(Arrow.class);
				                    firstarrow.setDamage(0);
				                    firstarrow.remove();
				                    Arrow fa = (Arrow) a.getProjectile();
				                    ArrayList<Arrow> arar = new ArrayList<Arrow>();
				                	for(int i = 0; i<25; i++) {
					                	Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 4, 20, fa.getClass());
					                	ar.setShooter(p);
					                	ar.setKnockbackStrength(0);
					                	ar.setCritical(true);
					                	ar.setBounce(false);
					                	ar.setDamage(player_damage.get(p.getName())*0.01*(1+MultiShot.get(p.getUniqueId())*0.0065));
										shooter.put(ar, p);
										p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 0.1f, 1.6f);
										p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 0.1f, 1.6f);
					                	arar.add(ar);
					                	ar.setPickupStatus(PickupStatus.DISALLOWED);
				                	}
				                	
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                	for(Arrow a : arar)
						                	{
						                		a.remove();
						                	}
						                }
						            }, 10); 
					                dpcooldown.put(p.getName(), System.currentTimeMillis());
				                }
				            }
				            else // if cooldown doesn't have players name in it
				            {
			                    Arrow firstarrow = p.launchProjectile(Arrow.class);
			                    firstarrow.setDamage(0);
			                    firstarrow.remove();
				            	Arrow fa = (Arrow) a.getProjectile();
			                    ArrayList<Arrow> arar = new ArrayList<Arrow>();
			                	for(int i = 0; i<25; i++) {
				                	Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 4, 20, fa.getClass());
				                	ar.setShooter(p);
				                	ar.setKnockbackStrength(0);
				                	ar.setCritical(true);
				                	ar.setBounce(false);
				                	ar.setDamage(player_damage.get(p.getName())*0.01*(1+MultiShot.get(p.getUniqueId())*0.0065));
									shooter.put(ar, p);
									p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 0.1f, 1.6f);
									p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 0.1f, 1.6f);
				                	arar.add(ar);
				                	ar.setPickupStatus(PickupStatus.DISALLOWED);
			                	}
			                	
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	for(Arrow a : arar)
					                	{
					                		a.remove();
					                	}
					                }
					            }, 10); 
			                	dpcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				            }
						
					}
				}
			}
		}
	}
	
	@EventHandler
	public void HookAndShot(EntityDamageByEntityEvent d) 
	{
		int sec = 4;
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{	
			Player p = (Player)d.getDamager();
		    
			
			
			if(p.getInventory().getItemInMainHand().getType() == Material.BOW )
			{
			if(playerclass.get(p.getUniqueId()) == 6&& (p.isSneaking()) && !(p.isSprinting())) {
				
				HashMap<UUID, Integer> HookAndShot = asd.getArcherdata().HookAndShot;
				if(hscooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            long timer = (hscooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
	                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use HookAndShot").create());
		            }
		            else // if timer is done
		            {
		                hscooldown.remove(p.getName()); // removing player from HashMap
	                    Arrow firstarrow = p.launchProjectile(Arrow.class);
	                    firstarrow.setDamage(0);
	                    firstarrow.remove();
		                LivingEntity e = (LivingEntity) d.getEntity();
		                d.setDamage(d.getDamage()*1.4 *(1 + HookAndShot.get(p.getUniqueId())*0.1));
			            Grabmob(p, e);
			            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	if(grabbed.containsKey(p))
			    				Shotmob(p);
			                }
			            }, 40); 
		                
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
                    Arrow firstarrow = p.launchProjectile(Arrow.class);
                    firstarrow.setDamage(0);
                    firstarrow.remove();
		        	LivingEntity e = (LivingEntity) d.getEntity();
		        	d.setDamage(d.getDamage()*1.4 *(1 + HookAndShot.get(p.getUniqueId())*0.1));
		            Grabmob(p, e);
		            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	if(grabbed.containsKey(p))
		    				Shotmob(p);
		                }
		            }, 40); 
		        }
				}	
			}
		}
	}
	
	public void Grabmob(Player p, LivingEntity e)
	{
	    
	    
		Holding hold = Holding.getInstance();
		
		if(playerclass.get(p.getUniqueId()) == 6) {
			grabbed.put(p, e);
			e.teleport(p.getLocation());
			p.getWorld().spawnParticle(Particle.SQUID_INK, e.getLocation(), 50, 2,2,2);
			p.playSound(e.getLocation(), Sound.ENTITY_ARROW_HIT, 1, 1);
			p.playSound(e.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1, 0);
			Holding.superholding(p, e, (long) 40);
			p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Hold And...").color(ChatColor.DARK_RED).create());
		}
	}
	
	public void Shotmob(Player p)
	{
		if(grabbed.containsKey(p))
		{
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 6) {			
				
				HashMap<UUID, Integer> HookAndShot = asd.getArcherdata().HookAndShot;
				LivingEntity e = grabbed.get(p);
				p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 1, false, false));
				e.setCollidable(true);
				Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 40, 1);
	        	ar.setShooter(p);
				shooter.put(ar, p);
				p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
				ar.setCritical(true);
				ar.setKnockbackStrength(6);
				ar.setDamage(HookAndShot.get(p.getUniqueId())*0.1);
				grabbed.remove(p);
		        hscooldown.put(p.getName(), System.currentTimeMillis());
	        }
		}
		
	}
	
	
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();

	    
		
		
		if(p.getInventory().getItemInMainHand().getType().name().equals("BOW"))
		{
			if(playerclass.get(p.getUniqueId()) == 6)
			{
					e.setCancelled(true);
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
	    
		
		
			
			if(playerclass.get(p.getUniqueId()) == 6 && (is.getType().name().equals("BOW")) && p.isSneaking())
			{
				ev.setCancelled(true);
				if(bultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (bultcooldown.get(p.getName())/1000 + 40) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use CrazyArrows").create());
			        }
	                else // if timer is done
	                {
	                    bultcooldown.remove(p.getName()); // removing player from HashMap
	                    Arrow firstarrow = p.launchProjectile(Arrow.class);
	                    firstarrow.setDamage(0);
	                    firstarrow.remove();
						p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 0.3f, 0.3f);
						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 70, 3, false,false));
	                    ArrayList<Arrow> arar = new ArrayList<Arrow>();
	                    for(int i1 =0; i1<20; i1++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {					               
					                	for(int i = 0; i<30; i++) 
					                	{
					                	Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 9, 48);
					                	ar.setShooter(p);
					                	ar.setKnockbackStrength(0);
					                	ar.setPickupStatus(PickupStatus.DISALLOWED);
					                	ar.setCritical(true);
					                	ar.setBounce(false);
										shooter.put(ar, p);
										p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 0.05f, 1.6f);
					                	ar.setDamage(player_damage.get(p.getName())*0.05);
					                	arar.add(ar);
					                	}
					                }
					            }, i1*3); 
		                   }
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {					               
			                	for(Arrow a : arar)
			                	{
			                		a.remove();
			                	}
			                }
			            }, 65); 
						bultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
						
		                
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {

                    Arrow firstarrow = p.launchProjectile(Arrow.class);
                    firstarrow.setDamage(0);
                    firstarrow.remove();
						p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 1f, 0.3f);
						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 70, 3, false,false));
	                    ArrayList<Arrow> arar = new ArrayList<Arrow>();
	                    for(int i1 =0; i1<20; i1++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {					               
					                	for(int i = 0; i<30; i++) 
					                	{
					                	Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 9, 48);
					                	ar.setShooter(p);
					                	ar.setKnockbackStrength(0);
					                	ar.setPickupStatus(PickupStatus.DISALLOWED);
					                	ar.setCritical(true);
					                	ar.setBounce(false);
										shooter.put(ar, p);
										p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 0.05f, 1.6f);
					                	ar.setDamage(player_damage.get(p.getName())*0.05);
					                	arar.add(ar);
					                	}
					                }
					            }, i1*3); 
		                   }
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {					               
			                	for(Arrow a : arar)
			                	{
			                		a.remove();
			                	}
			                }
			            }, 65); 
					bultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
					
	                
	            }
				
			}	
			
			
		}
	
	@EventHandler
	public void Damagegetter(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity) 
		{
			Projectile a = (Projectile)d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			if(a.getShooter() instanceof Player) {
				Player p = (Player)a.getShooter();
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
				    
					
					
					if(playerclass.get(p.getUniqueId()) == 6)			
					{	
						
						HashMap<UUID, Integer> Archery = asd.getArcherdata().Archery;
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.ARROW_DAMAGE)*0.5 + p.getLevel()/10);
					
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName()) + p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
						d.setDamage(d.getDamage()*1.35*(1+Archery.get(p.getUniqueId())*0.035));
						le.setArrowsInBody(le.getArrowsInBody()+1);
						le.setArrowCooldown(600);
					}
				}
			}
		}
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && d.getDamage()>0) 
		{
			{
				Player p = (Player)d.getDamager();
				LivingEntity le = (LivingEntity) d.getEntity();
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
				    
					
					
					if(playerclass.get(p.getUniqueId()) == 6)			
					{	
						
						HashMap<UUID, Integer> Archery = asd.getArcherdata().Archery;
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.ARROW_DAMAGE)*0.5 + p.getLevel()/10);
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName()) + p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
						if(d.getDamage()>0) {
							le.setArrowsInBody(le.getArrowsInBody()+1);
							le.setArrowCooldown(600);
							p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, le.getLocation(), 2, 1,1,1);
							d.setDamage(d.getDamage()+player_damage.get(p.getName())*(1+Archery.get(p.getUniqueId())*0.05));
						}
					}
				}
			}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof Player) 
		{
			{
				Arrow a = (Arrow)d.getDamager();
				Player p = (Player)d.getEntity();
				if(a.getShooter() == p)
				{
					d.setCancelled(true);
				}
			}
		}
	}
}



