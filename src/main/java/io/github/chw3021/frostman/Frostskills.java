package io.github.chw3021.frostman;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.hunter.HunSkillsData;
import io.github.chw3021.nobility.NobSkillsData;
import io.github.chw3021.party.PartyData;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Frostskills implements Listener, Serializable {
	

	/**
	 * 
	 */
	private static transient final long serialVersionUID = -4738740919433352298L;
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<Player, Item> crystal = new HashMap<Player, Item>();
	private HashMap<UUID, Integer> frost = new HashMap<UUID, Integer>();
	private HashMap<UUID, Long> frostcooldown = new HashMap<UUID, Long>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private FrostSkillsData bsd = new FrostSkillsData(FrostSkillsData.loadData(path +"/plugins/RPGskills/FrostSkillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		FrostSkillsData b = new FrostSkillsData(FrostSkillsData.loadData(path +"/plugins/RPGskills/FrostSkillsData.data"));
		bsd = b;
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
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("FrostSkills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				FrostSkillsData b = new FrostSkillsData(FrostSkillsData.loadData(path +"/plugins/RPGskills/FrostSkillsData.data"));
				bsd = b;
			}
			
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		FrostSkillsData b = new FrostSkillsData(FrostSkillsData.loadData(path +"/plugins/RPGskills/FrostSkillsData.data"));
		bsd = b;
		
	}
	
	@EventHandler
	public void FrozenCrystal(PlayerSwapHandItemsEvent ev) 
	{
	    
		Player p = ev.getPlayer();
		int sec = 3;
		
		
		Holding hold = Holding.getInstance();
		if(playerclass.get(p.getUniqueId()) == 21 && p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD && !(p.isSneaking()) && !crystal.containsKey(p))
		{
			
				p.setSneaking(true);
				ev.setCancelled(true);
				p.setSneaking(false);
				if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Crystal").create());
	                }
	                else // if timer is done
	                {
	                    sdcooldown.remove(p.getName()); // removing player from HashMap
	                    p.setCooldown(Material.PRISMARINE_SHARD, 10);
		            	p.playSound(p.getLocation(), Sound.BLOCK_SNOW_PLACE, 1.0f, 2f);
		            	p.playSound(p.getLocation(), Sound.ENTITY_SNOWBALL_THROW, 1.0f, 0f);
						ItemStack is = new ItemStack(Material.PACKED_ICE);
						Item solid = p.getWorld().dropItem(p.getEyeLocation(), is);
						solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						solid.setMetadata("crystal of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						solid.setPickupDelay(9999);
	                    solid.setVelocity(p.getLocation().getDirection().normalize().multiply(0.5));
	                    solid.setGravity(false);
						crystal.put(p, solid);
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	if(crystal.containsKey(p)) {
				                    p.getWorld().spawnParticle(Particle.BLOCK_CRACK, solid.getLocation(), 300,3,3,3,0.1,Material.PACKED_ICE.createBlockData());
				                    p.getWorld().spawnParticle(Particle.BLOCK_CRACK, solid.getLocation(), 300,3,3,3,0.1,Material.ICE.createBlockData());
				                    p.playSound(solid.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 0);
				                    p.playSound(solid.getLocation(), Sound.BLOCK_SNOW_BREAK, 1, 0);
				                    p.playSound(crystal.get(p).getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 2);
				                    for (Entity e : solid.getNearbyEntities(3.5, 3.5, 3.5))
									{
			                    		if (e instanceof Player) 
										{
											PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
												enar.setDamage(player_damage.get(p.getName())*0.765+bsd.FrozenCrystal.get(p.getUniqueId())*0.55);
											}	
											Holding.holding(p, le, 10l);
						                    le.damage(0,p);
						                    le.damage(player_damage.get(p.getName())*0.765+bsd.FrozenCrystal.get(p.getUniqueId())*0.55,p);
										}
									}
				                    p.getWorld().getEntities().stream().filter(en -> en.hasMetadata("crystal of"+p.getName())).forEach(n -> n.remove());
					                sdcooldown.put(p.getName(), System.currentTimeMillis());
									crystal.remove(p);		                		
			                	}
			                }
	                    }, 40); 
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    p.setCooldown(Material.PRISMARINE_SHARD, 10);
	            	p.playSound(p.getLocation(), Sound.BLOCK_SNOW_PLACE, 1.0f, 2f);
	            	p.playSound(p.getLocation(), Sound.ENTITY_SNOWBALL_THROW, 1.0f, 0f);
					ItemStack is = new ItemStack(Material.PACKED_ICE);
					Item solid = p.getWorld().dropItem(p.getEyeLocation(), is);
					solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					solid.setMetadata("crystal of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					solid.setPickupDelay(9999);
                    solid.setVelocity(p.getLocation().getDirection().normalize().multiply(0.5));
                    solid.setGravity(false);
					crystal.put(p, solid);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	if(crystal.containsKey(p)) {
			                    p.getWorld().spawnParticle(Particle.BLOCK_CRACK, solid.getLocation(), 300,3,3,3,0.1,Material.PACKED_ICE.createBlockData());
			                    p.getWorld().spawnParticle(Particle.BLOCK_CRACK, solid.getLocation(), 300,3,3,3,0.1,Material.ICE.createBlockData());
			                    p.playSound(solid.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 0);
			                    p.playSound(solid.getLocation(), Sound.BLOCK_SNOW_BREAK, 1, 0);
			                    p.playSound(crystal.get(p).getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 2);
			                    for (Entity e : solid.getNearbyEntities(3.5, 3.5, 3.5))
								{
		                    		if (e instanceof Player) 
									{
										PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
											enar.setDamage(player_damage.get(p.getName())*0.765+bsd.FrozenCrystal.get(p.getUniqueId())*0.55);
										}	
										Holding.holding(p, le, 10l);
					                    le.damage(0,p);
					                    le.damage(player_damage.get(p.getName())*0.765+bsd.FrozenCrystal.get(p.getUniqueId())*0.55,p);
									}
								}
			                    p.getWorld().getEntities().stream().filter(en -> en.hasMetadata("crystal of"+p.getName())).forEach(n -> n.remove());
				                sdcooldown.put(p.getName(), System.currentTimeMillis());
								crystal.remove(p);
		                	}
		                }
                    }, 40); 
	            }
		}
	}


	@EventHandler
	public void FrozenCrystalbreak(PlayerSwapHandItemsEvent ev) 
	{
	    
		Player p = ev.getPlayer();
		Holding hold = Holding.getInstance();
		
		
		if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD && !(p.isSneaking()) && playerclass.get(p.getUniqueId()) == 21)
		{
			ev.setCancelled(true);
			
			if(crystal.containsKey(p) && p.getCooldown(Material.PRISMARINE_SHARD)<=0)
			{
                p.getWorld().spawnParticle(Particle.BLOCK_CRACK, crystal.get(p).getLocation(), 300,3,3,3,0.1,Material.PACKED_ICE.createBlockData());
                p.getWorld().spawnParticle(Particle.BLOCK_CRACK, crystal.get(p).getLocation(), 300,3,3,3,0.1,Material.ICE.createBlockData());
                p.playSound(crystal.get(p).getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 0);
                p.playSound(crystal.get(p).getLocation(), Sound.BLOCK_SNOW_BREAK, 1, 0);
                p.playSound(crystal.get(p).getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 2);
                for (Entity e : crystal.get(p).getNearbyEntities(3.5, 3.5, 3.5))
				{
            		if (e instanceof Player) 
					{
						PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
							enar.setDamage(player_damage.get(p.getName())*0.765+bsd.FrozenCrystal.get(p.getUniqueId())*0.55);
						}	
						Holding.holding(p, le, 10l);
	                    le.damage(0,p);
	                    le.damage(player_damage.get(p.getName())*0.765+bsd.FrozenCrystal.get(p.getUniqueId())*0.55,p);
					}
				}
                p.getWorld().getEntities().stream().filter(en -> en.hasMetadata("crystal of"+p.getName())).forEach(n -> n.remove());
                sdcooldown.put(p.getName(), System.currentTimeMillis());
				crystal.remove(p);
			}
		}
	}
	
	@EventHandler
	public void Hailstones(PlayerSwapHandItemsEvent ev) 
	{
	    
	    Player p = ev.getPlayer();
		int sec = 9;
        
		if(playerclass.get(p.getUniqueId()) == 21) {
	        final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR)), 6).getLocation().setDirection(p.getLocation().getDirection());
	        final Location el = new Location(p.getWorld(), l.getX(), l.getY()+5.5, l.getZ());
			if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD && p.isSneaking())
			{
				ev.setCancelled(true);
				
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (cdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Hailstones").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap
		                    ArrayList<Location> des = new ArrayList<>();
		                    AtomicInteger j = new AtomicInteger();
		                    for(int i=0; i<20; i++) {
		                    	Random random=new Random();
		                    	double number = random.nextDouble() * 1.5 * (random.nextBoolean() ? -1 : 1);
		                    	double number2 = random.nextDouble() * 1.5 * (random.nextBoolean() ? -1 : 1);
		                    	des.add(el.clone().add(number, 0.5, number2));
		                    }
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 1, false, false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60, 1, false, false));
		                	p.getWorld().spawnParticle(Particle.CLOUD, el, 100, 2.5, 2.4, 2.4, 0.2);
		                	p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, el, 100, 2.4, 2.4, 2.4, 0.2);
							p.playSound(el.add(0,4,0), Sound.WEATHER_RAIN, 1f, 2f);
		                    Snowball firstarrow = p.launchProjectile(Snowball.class);
		                    firstarrow.setItem(new ItemStack(Material.FROSTED_ICE));
		                    firstarrow.remove();
							des.forEach(dl ->{
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	p.getWorld().spawnParticle(Particle.CLOUD, el, 10, 2.5, 2.4, 2.4, 0.2);
				                    	Random random=new Random();
					                	int ri = random.nextInt(2);
					                	Material type = null;
					                	if(ri == 0) {
					                		type = Material.ICE;
					                	}
					                	else if(ri == 1) {
					                		type = Material.SNOW_BLOCK;
					                	}
					                	else if(ri == 2) {
					                		type = Material.PACKED_ICE;
					                	}
					                	else if(ri == 3) {
					                		type = Material.PRISMARINE_CRYSTALS;
					                	}
					                	Item hail = p.getWorld().dropItem(dl, new ItemStack(Material.ICE));
					                	hail.setItemStack(new ItemStack(type));
					                	hail.setPickupDelay(9999);
					                	hail.setOwner(p.getUniqueId());
					                	hail.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					                	hail.setMetadata("hail of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					                	p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, el, 2, 2.4, 2.4, 2.4, 0.2);
										p.playSound(hail.getLocation(), Sound.BLOCK_SNOW_FALL, 0.8f, 2f);
										p.playSound(hail.getLocation(), Sound.BLOCK_SNOW_PLACE, 0.8f, 2f);
										p.playSound(hail.getLocation(), Sound.ENTITY_GENERIC_BIG_FALL, 0.8f, 2f);
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
							                	hail.remove();
							                }
										}, 60);
					                	for(Entity e : p.getWorld().getNearbyEntities(l, 4.5, 4.5, 4.5)) {
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
					                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p) {
					                			LivingEntity le = (LivingEntity)e;
					    						p.setSprinting(true);
												if(le instanceof EnderDragon) {
													Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
														a.setShooter(p);
														a.setCritical(false);
														a.setSilent(true);
														a.setPickupStatus(PickupStatus.DISALLOWED);
														a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
													});
													enar.setDamage(player_damage.get(p.getName())*0.16+bsd.Hailstones.get(p.getUniqueId())*0.18);								
												}
					    						le.damage(0, p);
					    						le.damage(player_damage.get(p.getName())*0.16+bsd.Hailstones.get(p.getUniqueId())*0.18, p);
					    						p.setSprinting(false);
					                		}
					                	}
					                }
		                	   }, j.incrementAndGet()*3+10);
							});
							cdcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	ArrayList<Location> des = new ArrayList<>();
	                    AtomicInteger j = new AtomicInteger();
	                    for(int i=0; i<20; i++) {
	                    	Random random=new Random();
	                    	double number = random.nextDouble() * 1.5 * (random.nextBoolean() ? -1 : 1);
	                    	double number2 = random.nextDouble() * 1.5 * (random.nextBoolean() ? -1 : 1);
	                    	des.add(el.clone().add(number, 0.5, number2));
	                    }
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 1, false, false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60, 1, false, false));
	                	p.getWorld().spawnParticle(Particle.CLOUD, el, 100, 2.5, 2.4, 2.4, 0.2);
	                	p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, el, 100, 2.4, 2.4, 2.4, 0.2);
						p.playSound(el.add(0,4,0), Sound.WEATHER_RAIN, 1f, 2f);
	                    Snowball firstarrow = p.launchProjectile(Snowball.class);
	                    firstarrow.setItem(new ItemStack(Material.FROSTED_ICE));
	                    firstarrow.remove();
						des.forEach(dl ->{
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	p.getWorld().spawnParticle(Particle.CLOUD, el, 10, 2.5, 2.4, 2.4, 0.2);
			                    	Random random=new Random();
				                	int ri = random.nextInt(2);
				                	Material type = null;
				                	if(ri == 0) {
				                		type = Material.ICE;
				                	}
				                	else if(ri == 1) {
				                		type = Material.SNOW_BLOCK;
				                	}
				                	else if(ri == 2) {
				                		type = Material.PACKED_ICE;
				                	}
				                	else if(ri == 3) {
				                		type = Material.PRISMARINE_CRYSTALS;
				                	}
				                	Item hail = p.getWorld().dropItem(dl, new ItemStack(Material.ICE));
				                	hail.setItemStack(new ItemStack(type));
				                	hail.setPickupDelay(9999);
				                	hail.setOwner(p.getUniqueId());
				                	hail.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				                	hail.setMetadata("hail of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				                	p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, el, 2, 2.4, 2.4, 2.4, 0.2);
									p.playSound(hail.getLocation(), Sound.BLOCK_SNOW_FALL, 0.8f, 2f);
									p.playSound(hail.getLocation(), Sound.BLOCK_SNOW_PLACE, 0.8f, 2f);
									p.playSound(hail.getLocation(), Sound.ENTITY_GENERIC_BIG_FALL, 0.8f, 2f);
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                	hail.remove();
						                }
									}, 60);
				                	for(Entity e : p.getWorld().getNearbyEntities(l, 4.5, 4.5, 4.5)) {
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
				                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p) {
				                			LivingEntity le = (LivingEntity)e;
				    						p.setSprinting(true);
											if(le instanceof EnderDragon) {
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
													a.setShooter(p);
													a.setCritical(false);
													a.setSilent(true);
													a.setPickupStatus(PickupStatus.DISALLOWED);
													a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*0.16+bsd.Hailstones.get(p.getUniqueId())*0.18);								
											}
				    						le.damage(0, p);
				    						le.damage(player_damage.get(p.getName())*0.16+bsd.Hailstones.get(p.getUniqueId())*0.18, p);
				    						p.setSprinting(false);
				                		}
				                	}
				                }
	                	   }, j.incrementAndGet()*3+10);
						});
		                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
		}
	
	}
	
	@EventHandler
	public void IceSpikes(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
       Action ac = ev.getAction();
		int sec = 8;
		if(playerclass.get(p.getUniqueId()) == 21) {
		final Location el = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 6).getLocation().add(0, -4, 0);
		if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD)
			{
				

				if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (smcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use IceSpikes").create());
	                }
	                else // if timer is done
	                {
	                	smcooldown.remove(p.getName()); // removing player from HashMap
		            	AtomicInteger j = new AtomicInteger();
						p.playSound(el.clone().add(0,4,0), Sound.BLOCK_SNOW_FALL, 0.8f, 2f);
						p.playSound(el.clone().add(0,4,0), Sound.AMBIENT_UNDERWATER_ENTER, 0.8f, 0f);
	            		p.getWorld().spawnParticle(Particle.WATER_WAKE, el.clone().add(0, 4, 0), 100, 2, 2, 2);
	            		p.getWorld().spawnParticle(Particle.WHITE_ASH, el.clone().add(0, 4, 0), 100, 2, 2, 2);
	            		p.getWorld().spawnParticle(Particle.SNOWBALL, el.clone().add(0, 4, 0), 100, 2, 2, 2);
						for(int d=0; d<30; d++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									p.playSound(el.clone().add(0,4,0), Sound.BLOCK_GLASS_BREAK, 0.35f, 2f);
									p.playSound(el.clone().add(0,4,0), Sound.BLOCK_LANTERN_BREAK, 0.35f, 2f);
									p.playSound(el.clone().add(0,4,0), Sound.ITEM_TRIDENT_THROW, 0.35f, 2f);
				            		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.incrementAndGet()/5, 0), 10, 1, 2, 1, Material.PACKED_ICE.createBlockData());
				            		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.incrementAndGet()/5, 0), 10, 0.5, 2, 0.5, Material.ICE.createBlockData());
				            		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.incrementAndGet()/5+0.1, 0), 10, 0.1, 2, 0.1, Material.BLUE_ICE.createBlockData());
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+1, 0), 10, 0.3, 2, 0.3, Material.PACKED_ICE.createBlockData());
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+1, 0), 10, 0.2, 2, 0.2, Material.ICE.createBlockData());
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+1.1, 0), 10, 0.1, 2, 0.1, Material.BLUE_ICE.createBlockData());
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+2, 0), 10, 0.3, 2, 0.3, Material.PACKED_ICE.createBlockData());
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+2, 0), 10, 0.2, 2, 0.2, Material.ICE.createBlockData());
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+2.1, 0), 10, 0.1, 2, 0.1, Material.BLUE_ICE.createBlockData());
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+3, 0), 10, 0.3, 2, 0.3, Material.PACKED_ICE.createBlockData());
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+3, 0), 10, 0.2, 2, 0.2, Material.ICE.createBlockData());
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+3.1, 0), 10, 0.1, 2, 0.1, Material.BLUE_ICE.createBlockData());
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+4, 0), 10, 0.2, 2, 0.2, Material.PACKED_ICE.createBlockData());
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+4, 0), 10, 0.1, 2, 0.1, Material.ICE.createBlockData());
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+4.1, 0), 10, 0.05, 2, 0.05, Material.BLUE_ICE.createBlockData());
				                	for(Entity e : p.getWorld().getNearbyEntities(el.clone(), 2, 15, 2)) {
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
				                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p) {
				                			final LivingEntity le = (LivingEntity)e;
				    						p.setSprinting(true);
											if(le instanceof EnderDragon) {
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
													a.setShooter(p);
													a.setCritical(false);
													a.setSilent(true);
													a.setPickupStatus(PickupStatus.DISALLOWED);
													a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*0.2 + bsd.IceSpikes.get(p.getUniqueId())*0.23);								
											}
				    						le.damage(0, p);
				    						le.damage(player_damage.get(p.getName())*0.2+ bsd.IceSpikes.get(p.getUniqueId())*0.23, p);
				    						p.setSprinting(false);
				                		}
				                	}
				                }
	                	   }, j.incrementAndGet()/2+10);
	                    }
	                	smcooldown.put(p.getName(), System.currentTimeMillis()); 
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	AtomicInteger j = new AtomicInteger();
					p.playSound(el.clone().add(0,4,0), Sound.BLOCK_SNOW_FALL, 0.8f, 2f);
					p.playSound(el.clone().add(0,4,0), Sound.AMBIENT_UNDERWATER_ENTER, 0.8f, 0f);
            		p.getWorld().spawnParticle(Particle.WATER_WAKE, el.clone().add(0, 4, 0), 100, 2, 2, 2);
            		p.getWorld().spawnParticle(Particle.WHITE_ASH, el.clone().add(0, 4, 0), 100, 2, 2, 2);
            		p.getWorld().spawnParticle(Particle.SNOWBALL, el.clone().add(0, 4, 0), 100, 2, 2, 2);
					for(int d=0; d<30; d++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								p.playSound(el.clone().add(0,4,0), Sound.BLOCK_GLASS_BREAK, 0.35f, 2f);
								p.playSound(el.clone().add(0,4,0), Sound.BLOCK_LANTERN_BREAK, 0.35f, 2f);
								p.playSound(el.clone().add(0,4,0), Sound.ITEM_TRIDENT_THROW, 0.35f, 2f);
			            		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.incrementAndGet()/5, 0), 10, 1, 2, 1, Material.PACKED_ICE.createBlockData());
			            		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.incrementAndGet()/5, 0), 10, 0.5, 2, 0.5, Material.ICE.createBlockData());
			            		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.incrementAndGet()/5+0.1, 0), 10, 0.1, 2, 0.1, Material.BLUE_ICE.createBlockData());
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+1, 0), 10, 0.3, 2, 0.3, Material.PACKED_ICE.createBlockData());
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+1, 0), 10, 0.2, 2, 0.2, Material.ICE.createBlockData());
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+1.1, 0), 10, 0.1, 2, 0.1, Material.BLUE_ICE.createBlockData());
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+2, 0), 10, 0.3, 2, 0.3, Material.PACKED_ICE.createBlockData());
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+2, 0), 10, 0.2, 2, 0.2, Material.ICE.createBlockData());
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+2.1, 0), 10, 0.1, 2, 0.1, Material.BLUE_ICE.createBlockData());
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+3, 0), 10, 0.3, 2, 0.3, Material.PACKED_ICE.createBlockData());
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+3, 0), 10, 0.2, 2, 0.2, Material.ICE.createBlockData());
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+3.1, 0), 10, 0.1, 2, 0.1, Material.BLUE_ICE.createBlockData());
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+4, 0), 10, 0.2, 2, 0.2, Material.PACKED_ICE.createBlockData());
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+4, 0), 10, 0.1, 2, 0.1, Material.ICE.createBlockData());
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+4.1, 0), 10, 0.05, 2, 0.05, Material.BLUE_ICE.createBlockData());
			                	for(Entity e : p.getWorld().getNearbyEntities(el.clone(), 2, 15, 2)) {
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
			                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p) {
			                			final LivingEntity le = (LivingEntity)e;
			    						p.setSprinting(true);
										if(le instanceof EnderDragon) {
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
												a.setShooter(p);
												a.setCritical(false);
												a.setSilent(true);
												a.setPickupStatus(PickupStatus.DISALLOWED);
												a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*0.2 + bsd.IceSpikes.get(p.getUniqueId())*0.23);								
										}
			    						le.damage(0, p);
			    						le.damage(player_damage.get(p.getName())*0.2+ bsd.IceSpikes.get(p.getUniqueId())*0.23, p);
			    						p.setSprinting(false);
			                		}
			                	}
			                }
                	   }, j.incrementAndGet()/2+10);
                    }
	            	smcooldown.put(p.getName(), System.currentTimeMillis());
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}

	@EventHandler
	public void SnowBreeze(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 5;
        if(playerclass.get(p.getUniqueId()) == 21) {
    		final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR)), 3).getLocation().setDirection(p.getLocation().getDirection());
			if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD &&!p.isSneaking()&& !p.isOnGround()) 
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					if(gdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (gdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use SnowBreeze").create());
		                }
		                else // if timer is done
		                {
		                	gdcooldown.remove(p.getName()); // removing player from HashMap
		                	ArrayList<Location> br = new ArrayList<>();
		                	AtomicInteger j = new AtomicInteger();
		                	for(double d = -3; d<6 ; d+=0.1) {
		                		br.add(p.getLocation().clone().add(p.getLocation().getDirection().clone().normalize().multiply(d)));
		                	}
		                	br.forEach(bl -> {

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
				                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, bl,30, 3,0.1,3,0,Material.SNOW.createBlockData());
					                }
								}, j.incrementAndGet()/30);
		                	});
		                	p.playSound(p.getLocation(), Sound.ENTITY_SNOW_GOLEM_AMBIENT, 1f, 0f);
		                	p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1f, 0f);
		                	p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1f, 0f);
		                	for(Entity e : p.getNearbyEntities(4, 3, 4)) {
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
		                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p) {
		                			LivingEntity le = (LivingEntity)e;
		    						p.setSprinting(true);
									if(le instanceof EnderDragon) {
										Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
											a.setShooter(p);
											a.setCritical(false);
											a.setSilent(true);
											a.setPickupStatus(PickupStatus.DISALLOWED);
											a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
										});
										enar.setDamage(player_damage.get(p.getName())*0.2);								
									}
		    						le.damage(0, p);
		    						le.damage(player_damage.get(p.getName())*0.2, p);
		    						p.setSprinting(false);
		    						for(double d = 0; d<l.distance(le.getLocation()); d+=0.1) {
		    							if(le.getLocation().clone().add(p.getLocation().getDirection().normalize().multiply(d)).getBlock().isPassable()){
				    						le.teleport(le.getLocation().add(p.getLocation().getDirection().normalize().multiply(d)));
		    							}
		    						}
		                		}
		                	}
		                    gdcooldown.put(p.getName(), System.currentTimeMillis());  
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
	                	ArrayList<Location> br = new ArrayList<>();
	                	AtomicInteger j = new AtomicInteger();
	                	for(double d = -3; d<6 ; d+=0.1) {
	                		br.add(p.getLocation().clone().add(p.getLocation().getDirection().clone().normalize().multiply(d)));
	                	}
	                	br.forEach(bl -> {

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
			                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, bl,30, 3,0.1,3,0,Material.SNOW.createBlockData());
				                }
							}, j.incrementAndGet()/30);
	                	});
	                	p.playSound(p.getLocation(), Sound.ENTITY_SNOW_GOLEM_AMBIENT, 1f, 0f);
	                	p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1f, 0f);
	                	p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1f, 0f);
		            	for(Entity e : p.getNearbyEntities(4, 3, 4)) {
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
	                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p) {
	                			LivingEntity le = (LivingEntity)e;
	    						p.setSprinting(true);
								if(le instanceof EnderDragon) {
									Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
										a.setShooter(p);
										a.setCritical(false);
										a.setSilent(true);
										a.setPickupStatus(PickupStatus.DISALLOWED);
										a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
									});
									enar.setDamage(player_damage.get(p.getName())*0.2);								
								}
	    						le.damage(0, p);
	    						le.damage(player_damage.get(p.getName())*0.2, p);
	    						p.setSprinting(false);
	    						for(double d = 0; d<l.distance(le.getLocation()); d+=0.1) {
	    							if(le.getLocation().clone().add(p.getLocation().getDirection().normalize().multiply(d)).getBlock().isPassable()){
			    						le.teleport(le.getLocation().add(p.getLocation().getDirection().normalize().multiply(d)));
	    							}
	    						}
	                		}
	                	}
	                    gdcooldown.put(p.getName(), System.currentTimeMillis());  
					}
		    					
				} 
			}
		
		}
	}
	
	@EventHandler
	public void IcicleShot(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 2;

		if(playerclass.get(p.getUniqueId()) == 21) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD)
			{
				

				if(frcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (frcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use IcicleShot").create());
	                }
	                else // if timer is done
	                {
	                    frcooldown.remove(p.getName()); // removing player from HashMap
	                	p.playSound(p.getLocation(), Sound.ENTITY_SNOW_GOLEM_AMBIENT, 1f, 2f);
	                	p.playSound(p.getLocation(), Sound.BLOCK_SNOW_STEP, 1f, 2f);
	                	p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_QUICK_CHARGE_3, 1f, 1f);
	                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    for(int i =0; i<3; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	p.playSound(p.getLocation(), Sound.BLOCK_GLASS_HIT, 1f, 2f);
					                	p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 0.5f, 2f);
					                	p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_HIT_GROUND, 1f, 2f);
					                	ArrayList<Location> line = new ArrayList<Location>();
					                    for(double d = 0.1; d <= 6.1; d += 0.2) {
						                    Location pl = p.getEyeLocation().clone();
											pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
											line.add(pl);
					                    }
					                    for(Location l : line) {
					                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l.add(0, -0.289, 0),5, 0.05,0.05,0.05,0,Material.ICE.createBlockData());
					                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l.add(0, -0.289, 0),5, 0.01,0.01,0.01,0,Material.PACKED_ICE.createBlockData());
					                    	p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, l.add(0, -0.289, 0),1, 0.05,0.05,0.05,0.5);

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
					                }
		                	   }, i+20); 
						}
	                    for(int i =0; i<3; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	for(LivingEntity le : les) {
											if(le instanceof EnderDragon) {
							                    Arrow firstarrow = p.launchProjectile(Arrow.class);
							                    firstarrow.setDamage(0);
							                    firstarrow.setPickupStatus(PickupStatus.DISALLOWED);
							                    firstarrow.remove();
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
													ar.setShooter(p);
													ar.setCritical(false);
													ar.setSilent(true);
													ar.setPickupStatus(PickupStatus.DISALLOWED);
													ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*0.32+bsd.IcicleShot.get(p.getUniqueId())*0.31);								
											}
											p.setSprinting(true);
											le.damage(0,p);
											le.damage(player_damage.get(p.getName())*0.32+bsd.IcicleShot.get(p.getUniqueId())*0.31,p);
											p.setSprinting(false);	
					                	}
					                	
					                }
		                	   }, i+1/10+20); 
	   					}
						frcooldown.put(p.getName(), System.currentTimeMillis()); 
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                	p.playSound(p.getLocation(), Sound.ENTITY_SNOW_GOLEM_AMBIENT, 1f, 2f);
                	p.playSound(p.getLocation(), Sound.BLOCK_SNOW_STEP, 1f, 2f);
                	p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_QUICK_CHARGE_3, 1f, 1f);
                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                    for(int i =0; i<3; i++) {
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	p.playSound(p.getLocation(), Sound.BLOCK_GLASS_HIT, 1f, 2f);
				                	p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 0.5f, 2f);
				                	p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_HIT_GROUND, 1f, 2f);
				                	ArrayList<Location> line = new ArrayList<Location>();
				                    for(double d = 0.1; d <= 6.1; d += 0.2) {
					                    Location pl = p.getEyeLocation().clone();
										pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
										line.add(pl);
				                    }
				                    for(Location l : line) {
				                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l.add(0, -0.289, 0),5, 0.05,0.05,0.05,0,Material.ICE.createBlockData());
				                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l.add(0, -0.289, 0),5, 0.01,0.01,0.01,0,Material.PACKED_ICE.createBlockData());
				                    	p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, l.add(0, -0.289, 0),1, 0.05,0.05,0.05,0.5);

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
				                }
	                	   }, i+20); 
					}
                    for(int i =0; i<3; i++) {
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	for(LivingEntity le : les) {
										if(le instanceof EnderDragon) {
							            	Arrow firstarrow = p.launchProjectile(Arrow.class);
						                    firstarrow.setDamage(0);
						                    firstarrow.setPickupStatus(PickupStatus.DISALLOWED);
						                    firstarrow.remove();
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
												ar.setShooter(p);
												ar.setCritical(false);
												ar.setSilent(true);
												ar.setPickupStatus(PickupStatus.DISALLOWED);
												ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*0.32+bsd.IcicleShot.get(p.getUniqueId())*0.31);								
										}
										p.setSprinting(true);
										le.damage(0,p);
										le.damage(player_damage.get(p.getName())*0.32+bsd.IcicleShot.get(p.getUniqueId())*0.31,p);
										p.setSprinting(false);	
				                	}
				                	
				                }
	                	   }, i+1/10+20); 
   					}
					frcooldown.put(p.getName(), System.currentTimeMillis());
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	

	@EventHandler
	public void Crack(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 5;
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&&!d.isCancelled() && d.getDamage()>0) 
		{
		Player p = (Player)d.getDamager();
		final LivingEntity le = (LivingEntity)d.getEntity();
		
		
		Holding hold = Holding.getInstance();
		if(playerclass.get(p.getUniqueId()) == 21 &&p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD && frostcooldown.containsKey(le.getUniqueId()))
		{
			
				if(p.isSneaking() && !(p.isSprinting()))
				{
						if(swcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
				        {
				            long timer = (swcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
				            if(!(timer < 0)) // if timer is still more then 0 or 0
				            {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Crack").create());
				            }
				            else // if timer is done
				            {
				                swcooldown.remove(p.getName()); // removing player from HashMap
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
								le.damage(0);
								d.setDamage(d.getDamage() + player_damage.get(p.getName())*2.5 + bsd.Crack.get(p.getUniqueId())*2.6);
								p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 1.0f);
								p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1.0f, 0f);
								p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 2.0f);
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 5, 1, 0, 1, Material.ICE.createBlockData());
								Holding.holding(p, le, (long) 20);
					            swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
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
							le.damage(0);
							d.setDamage(d.getDamage() + player_damage.get(p.getName())*2.5 + bsd.Crack.get(p.getUniqueId())*2.6);
							p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 1.0f);
							p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1.0f, 0f);
							p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 2.0f);
							p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 5, 1, 0, 1, Material.ICE.createBlockData());
							Holding.holding(p, le, (long) 20);
				            swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        }
				}
			}
		}
	}
	
	@EventHandler
	public void Frostbite(EntityDamageByEntityEvent d) 
	{
	    
		Holding hold = Holding.getInstance();
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
		if(playerclass.get(p.getUniqueId()) == 21 && (d.getDamage() > 0)) {
			if (le instanceof Player) 
			{
				Player p1 = (Player) le;
				if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
					if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
					{
						return;
					}
				}
			}
			
				if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD)
				{	
					d.setDamage(d.getDamage()*1.15*(1+bsd.Frostbite.get(p.getUniqueId())*0.035));
					if(frostcooldown.containsKey(le.getUniqueId())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (frostcooldown.get(le.getUniqueId())/1000 + 3) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                }
		                else // if timer is done
		                {
		                	frostcooldown.remove(le.getUniqueId()); // removing player from HashMap
		                	frost.computeIfPresent(le.getUniqueId(), (k,v) -> v+1);
		                	frost.putIfAbsent(le.getUniqueId(), 0);
		                	p.playSound(p.getLocation(), Sound.BLOCK_GLASS_HIT, 1f, 2f);
			                le.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 1,1,1,Material.ICE.createBlockData());
		                	if(frost.get(le.getUniqueId())>=3) {
			                	p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1f, 2f);
		                		Holding.holding(p, le, 45l);
		                        for(int i =0; i<15; i++) {
		 	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		 				                @Override
		 				                public void run() 
		 				                {
		 				                	le.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 1,1,1,Material.ICE.createBlockData());
		 				                }
		 	                	   }, i*3); 
		    					}
		                		frost.remove(le.getUniqueId());
			                	frostcooldown.put(le.getUniqueId(), System.currentTimeMillis()); 
		                	}
		                	
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
	                	frost.computeIfPresent(le.getUniqueId(), (k,v) -> v+1);
	                	frost.putIfAbsent(le.getUniqueId(), 0);
	                	p.playSound(p.getLocation(), Sound.BLOCK_GLASS_HIT, 1f, 2f);
		                le.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 1,1,1,Material.ICE.createBlockData());
	                	if(frost.get(le.getUniqueId())>=3) {
		                	p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1f, 2f);
	                		Holding.holding(p, le, 45l);
	                        for(int i =0; i<15; i++) {
	 	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	 				                @Override
	 				                public void run() 
	 				                {
	 				                	le.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 1,1,1,Material.ICE.createBlockData());
	 				                }
	 	                	   }, i*3); 
	    					}
	                		frost.remove(le.getUniqueId());
		                	frostcooldown.put(le.getUniqueId(), System.currentTimeMillis()); 
	                	}
					}
				}
		}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof EnderDragon) 
		{
			Arrow ar = (Arrow)d.getDamager();
			EnderDragon le = (EnderDragon)d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
				
				
				if(playerclass.get(p.getUniqueId()) == 21 && d.getDamage() >0) {
					if (le instanceof Player) 
					{
						Player p1 = (Player) le;
						if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
							if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
							{
								return;
							}
						}
					}
					
						if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD)
						{
							d.setDamage(d.getDamage()*1.15*(1+bsd.Frostbite.get(p.getUniqueId())*0.035));
							if(frostcooldown.containsKey(le.getUniqueId())) // if cooldown has players name in it (on first trow cooldown is empty)
				            {
				                long timer = (frostcooldown.get(le.getUniqueId())/1000 + 3) - System.currentTimeMillis()/1000; // geting time in seconds
				                if(!(timer < 0)) // if timer is still more then 0 or 0
				                {
				                }
				                else // if timer is done
				                {
				                	frostcooldown.remove(le.getUniqueId()); // removing player from HashMap
				                	frost.computeIfPresent(le.getUniqueId(), (k,v) -> v+1);
				                	frost.putIfAbsent(le.getUniqueId(), 0);
				                	p.playSound(p.getLocation(), Sound.BLOCK_GLASS_HIT, 1f, 2f);
					                le.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 1,1,1,Material.ICE.createBlockData());
				                	if(frost.get(le.getUniqueId())>=3) {
				                		le.damage(0,p);
				                		le.damage(player_damage.get(p.getName())*0.3,p);
					                	p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1f, 2f);
				                		Holding.holding(p, le, 45l);
				                        for(int i =0; i<15; i++) {
				 	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				 				                @Override
				 				                public void run() 
				 				                {
				 				                	le.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 1,1,1,Material.ICE.createBlockData());
				 				                }
				 	                	   }, i*3); 
				    					}
				                		frost.remove(le.getUniqueId());
					                	frostcooldown.put(le.getUniqueId(), System.currentTimeMillis()); 
				                	}
				                	
				                }
				            }
				            else // if cooldown doesn't have players name in it
				            {
			                	frost.computeIfPresent(le.getUniqueId(), (k,v) -> v+1);
			                	frost.putIfAbsent(le.getUniqueId(), 0);
			                	p.playSound(p.getLocation(), Sound.BLOCK_GLASS_HIT, 1f, 2f);
				                le.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 1,1,1,Material.ICE.createBlockData());
			                	if(frost.get(le.getUniqueId())>=3) {
			                		le.damage(0,p);
			                		le.damage(player_damage.get(p.getName())*0.3,p);
				                	p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1f, 2f);
			                		Holding.holding(p, le, 45l);
			                        for(int i =0; i<15; i++) {
			 	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			 				                @Override
			 				                public void run() 
			 				                {
			 				                	le.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 1,1,1,Material.ICE.createBlockData());
			 				                }
			 	                	   }, i*3); 
			    					}
			                		frost.remove(le.getUniqueId());
				                	frostcooldown.put(le.getUniqueId(), System.currentTimeMillis()); 
			                	}
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
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
			if(playerclass.get(p.getUniqueId()) == 21 && (is.getType() == Material.PRISMARINE_SHARD) && p.isSneaking())
			{
				final Location l = p.getLocation();
		        final Location el = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR)), 6).getLocation().setDirection(p.getLocation().getDirection());
				ev.setCancelled(true);
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (sultcooldown.get(p.getName())/1000 + 50) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Blizzard").create());
	                }
	                else // if timer is done
	                {
	                    sultcooldown.remove(p.getName()); // removing player from HashMap
	                    ArrayList<Location> desl = new ArrayList<>();
	                    AtomicInteger j = new AtomicInteger();
	                    for(int i=0; i<60; i++) {
	                    	Random random=new Random();
	                    	double number = random.nextDouble() * 6.5 * (random.nextBoolean() ? -1 : 1);
	                    	double number2 = random.nextDouble() * 6.5 * (random.nextBoolean() ? -1 : 1);
	                    	double number3 = random.nextDouble() * 6.5;
	                    	desl.add(el.clone().add(number, number3, number2));
	                    }
	                    Snowball firstarrow = p.launchProjectile(Snowball.class);
	                    firstarrow.remove();
	                    Snowman as = (Snowman)p.getWorld().spawnEntity(el, EntityType.SNOWMAN);
	                    ItemStack right = new ItemStack(Material.ICE);
	                    ItemStack left = new ItemStack(Material.BLUE_ICE);
	                    as.setCustomName(p.getName());
	                    as.setCollidable(false);
	                    as.setInvulnerable(true);
	                    as.getEquipment().setItemInMainHand(left);
	                    as.getEquipment().setItemInOffHand(right);
	                    as.setCanPickupItems(false);
	                    as.setAI(false);
	                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						p.playSound(p.getLocation(), Sound.WEATHER_RAIN, 1, 0);
						p.setInvulnerable(true);
						if(p.isDead()) {
							as.remove();
						}
						desl.forEach(dl ->{
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
			                    	Random random=new Random();
				                	int ri = random.nextInt(2);
				                	Material type = null;
				                	if(ri == 0) {
				                		type = Material.SNOW_BLOCK;
				                	}
				                	else if(ri == 1) {
				                		type = Material.SNOWBALL;
				                	}
				                	else if(ri == 2) {
				                		type = Material.BLUE_ICE;
				                	}
				                	Item des = p.getWorld().dropItem(dl, new ItemStack(Material.SNOWBALL));
				                	des.setItemStack(new ItemStack(type));
				                	des.setPickupDelay(9999);
				                	des.setOwner(p.getUniqueId());
				                	des.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				                	des.setMetadata("des of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				                	p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, el, 100, 6.4, 6.4, 6.4, 0.2);
				                	p.getWorld().spawnParticle(Particle.WATER_SPLASH, el, 100, 6.4, 6.4, 6.4, 0.2);
									p.playSound(des.getLocation(), Sound.ENTITY_SNOW_GOLEM_SHEAR, 0.8f, 2f);
									p.playSound(des.getLocation(), Sound.ENTITY_SNOW_GOLEM_AMBIENT, 0.8f, 2f);
									p.playSound(des.getLocation(), Sound.ENTITY_HORSE_BREATHE, 0.8f, 0f);
									p.playSound(des.getLocation(), Sound.ENTITY_PLAYER_BREATH, 0.8f, 2f);
									for(int i = 0; i<20; i++) {
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
							                	desl.get(random.nextInt(60));
							                }
										}, i*2);
									}
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                	des.remove();
						                }
									}, 120);
				                	for(Entity e : as.getWorld().getNearbyEntities(as.getLocation(), 6.4, 6.4, 6.4)) {
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
				                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p) {
				                			LivingEntity le = (LivingEntity)e;
				    						p.setSprinting(true);
											if(le instanceof EnderDragon) {
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
													a.setShooter(p);
													a.setCritical(false);
													a.setSilent(true);
													a.setPickupStatus(PickupStatus.DISALLOWED);
													a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*0.2*(1));								
											}
											le.teleport(le.getLocation().add(as.getLocation().toVector().subtract(le.getLocation().toVector()).normalize().multiply(0.92)));
				    						le.damage(0, p);
				    						le.damage(player_damage.get(p.getName())*0.2*(1), p);
				    						p.setSprinting(false);
				                		}
				                	}
				                }
	                	   }, j.incrementAndGet()*2);
						});
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	as.remove();
								p.setInvulnerable(false);
			                }
	            	   }, 126);		
		                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	ArrayList<Location> desl = new ArrayList<>();
                    AtomicInteger j = new AtomicInteger();
                    for(int i=0; i<60; i++) {
                    	Random random=new Random();
                    	double number = random.nextDouble() * 6.5 * (random.nextBoolean() ? -1 : 1);
                    	double number2 = random.nextDouble() * 6.5 * (random.nextBoolean() ? -1 : 1);
                    	double number3 = random.nextDouble() * 6.5;
                    	desl.add(el.clone().add(number, number3, number2));
                    }
                    Snowball firstarrow = p.launchProjectile(Snowball.class);
                    firstarrow.remove();
                    Snowman as = (Snowman)p.getWorld().spawnEntity(el, EntityType.SNOWMAN);
                    ItemStack right = new ItemStack(Material.ICE);
                    ItemStack left = new ItemStack(Material.BLUE_ICE);
                    as.setCustomName(p.getName());
                    as.setCollidable(false);
                    as.setInvulnerable(true);
                    as.getEquipment().setItemInMainHand(left);
                    as.getEquipment().setItemInOffHand(right);
                    as.setCanPickupItems(false);
                    as.setAI(false);
                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					p.playSound(p.getLocation(), Sound.WEATHER_RAIN, 1, 0);
					p.setInvulnerable(true);
					if(p.isDead()) {
						as.remove();
					}
					desl.forEach(dl ->{
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
		                    	Random random=new Random();
			                	int ri = random.nextInt(2);
			                	Material type = null;
			                	if(ri == 0) {
			                		type = Material.SNOW_BLOCK;
			                	}
			                	else if(ri == 1) {
			                		type = Material.SNOWBALL;
			                	}
			                	else if(ri == 2) {
			                		type = Material.BLUE_ICE;
			                	}
			                	Item des = p.getWorld().dropItem(dl, new ItemStack(Material.SNOWBALL));
			                	des.setItemStack(new ItemStack(type));
			                	des.setPickupDelay(9999);
			                	des.setOwner(p.getUniqueId());
			                	des.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			                	des.setMetadata("des of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			                	p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, el, 100, 6.4, 6.4, 6.4, 0.2);
			                	p.getWorld().spawnParticle(Particle.WATER_SPLASH, el, 100, 6.4, 6.4, 6.4, 0.2);
								p.playSound(des.getLocation(), Sound.ENTITY_SNOW_GOLEM_SHEAR, 0.8f, 2f);
								p.playSound(des.getLocation(), Sound.ENTITY_SNOW_GOLEM_AMBIENT, 0.8f, 2f);
								p.playSound(des.getLocation(), Sound.ENTITY_HORSE_BREATHE, 0.8f, 0f);
								p.playSound(des.getLocation(), Sound.ENTITY_PLAYER_BREATH, 0.8f, 2f);
								for(int i = 0; i<20; i++) {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                	desl.get(random.nextInt(60));
						                }
									}, i*2);
								}
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	des.remove();
					                }
								}, 120);
			                	for(Entity e : as.getWorld().getNearbyEntities(as.getLocation(), 6.4, 6.4, 6.4)) {
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
			                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p) {
			                			LivingEntity le = (LivingEntity)e;
			    						p.setSprinting(true);
										if(le instanceof EnderDragon) {
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
												a.setShooter(p);
												a.setCritical(false);
												a.setSilent(true);
												a.setPickupStatus(PickupStatus.DISALLOWED);
												a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*0.2*(1));								
										}
										le.teleport(le.getLocation().add(as.getLocation().toVector().subtract(le.getLocation().toVector()).normalize().multiply(0.92)));
			    						le.damage(0, p);
			    						le.damage(player_damage.get(p.getName())*0.2*(1), p);
			    						p.setSprinting(false);
			                		}
			                	}
			                }
                	   }, j.incrementAndGet()*2);
					});
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	as.remove();
							p.setInvulnerable(false);
		                }
            	   }, 126);		
	                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
	    
		Player p = e.getPlayer();

		
		
		if(playerclass.get(p.getUniqueId()) == 21)
		{
			if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
			{
					e.setCancelled(true);
			}
		}
		
	}
	
	
	
	@EventHandler
	public void dyeCancel(SheepDyeWoolEvent e) 
	{
		e.setCancelled(true);
	}

	@EventHandler
	public void Damagegetter(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 21) {
				if(playerclass.get(p.getUniqueId()) == 21) {
					if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
					{
							player_damage.put(p.getName(), 4 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
							if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
							{
								player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
							}
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
	
			
			
				if(playerclass.get(p.getUniqueId()) == 21) {
					if(!p.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
					}
					if(!p.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
					}
					if(!p.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 3, false, false));
					}
					if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
					{
							player_damage.put(p.getName(), 4+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
			Arrow a = (Arrow)d.getDamager();
			Entity e = d.getEntity();
	
			
			
			if(a.getShooter() instanceof Player) {
				Player p = (Player) a.getShooter();
				if(playerclass.get(p.getUniqueId()) == 21) {
					if(!p.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
					}
					if(!p.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
					}
					if(!p.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 3, false, false));
					}
					if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
					{
							player_damage.put(p.getName(), 4 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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



