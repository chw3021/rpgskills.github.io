package io.github.chw3021.chemist;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.party.PartyData;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Cheskills implements Listener, Serializable {
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 2619374943549160864L;
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> stcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private Multimap<Player, Integer> extracted = ArrayListMultimap.create();
	private HashMap<Player, Integer> cloudh = new HashMap<Player, Integer>();
	private HashMap<Player, Integer> cloudt = new HashMap<Player, Integer>();
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private CheSkillsData csd;
	

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
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Cheskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				CheSkillsData c = new CheSkillsData(CheSkillsData.loadData(path +"/plugins/RPGskills/CheSkillsData.data"));
				csd = c;
			}
			
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		CheSkillsData c = new CheSkillsData(CheSkillsData.loadData(path +"/plugins/RPGskills/CheSkillsData.data"));
		csd = c;
		
	}

	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		CheSkillsData c = new CheSkillsData(CheSkillsData.loadData(path +"/plugins/RPGskills/CheSkillsData.data"));
		csd = c;
	}
	
	@EventHandler
	public void SlimeBall(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		if(playerclass.get(p.getUniqueId()) == 15 && p.isSneaking())
		{
			int sec = 8;
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")))
			{
				ev.setCancelled(true);
				
				
				if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use SlimeBall").create());
	                }
	                else // if timer is done
	                {
	                    sdcooldown.remove(p.getName()); // removing player from HashMap
						p.playSound(p.getLocation(), Sound.ENTITY_FISHING_BOBBER_THROW, 1.0f, 0f);
						p.playSound(p.getLocation(), Sound.ENTITY_SLIME_JUMP, 1.0f, 0f);
						Item slime = p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack(Material.SLIME_BALL));
						slime.setPickupDelay(5555);
						slime.setVelocity(p.getLocation().getDirection().normalize().multiply(2));
						slime.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    ArrayList<Location> line = new ArrayList<Location>();
	                    AtomicInteger j = new AtomicInteger(0);
	                    AtomicInteger a = new AtomicInteger(0);
	                    AtomicInteger b = new AtomicInteger(0);
	                    for(double d = 0.1; d <= 5; d += 0.1) {
		                    Location pl = p.getLocation();
							pl.add(pl.getDirection().normalize().multiply(d));
							line.add(pl);
	                    }
	                    if(line.stream().filter(o -> o.getWorld().getNearbyEntities(o,1.3,1.3,1.3).stream().filter(en -> en instanceof LivingEntity && en!=p).findFirst().isPresent()).findFirst().isPresent()) 
	                    {
		                    Location block =line.stream().filter(o -> o.getWorld().getNearbyEntities(o,1.3,1.3,1.3).stream().filter(en -> en instanceof LivingEntity && en!=p).findFirst().isPresent()).findFirst().get();
		                    for(int k=0; k<line.indexOf(block); k++) {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
     									slime.teleport(line.get(a.getAndIncrement()));
    			             			p.getWorld().spawnParticle(Particle.SLIME ,line.get(a.get()), 3, 1,1,1,0);
						            }
			                	   }, j.getAndIncrement()/20); 
							 }
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
		             					for(Entity e: slime.getNearbyEntities(3, 3, 3)) {
		             						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")) {
		             							LivingEntity le = (LivingEntity)e;
		             							if (le instanceof Player) 
		            							{
		            								PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
		            								Player p1 = (Player) le;
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
													enar.setDamage(player_damage.get(p.getName())*0.45*(1+csd.SlimeBall.get(p.getUniqueId())*0.02));
												}		             			
		             							le.damage(0,p);
		             							le.damage(player_damage.get(p.getName())*0.45*(1+csd.SlimeBall.get(p.getUniqueId())*0.02),p);
		             							hold.holding(p, le, 20l);
		             							le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2, false, false));
		             							le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 2, false, false));
		             						}
		             					}
		             					slime.getWorld().spawnParticle(Particle.SLIME, slime.getLocation(), 460, 3,3,3);
		             					slime.getWorld().playSound(block, Sound.ENTITY_SLIME_SQUISH, 1, 0);
		             					slime.getWorld().playSound(block, Sound.ENTITY_SLIME_HURT, 1, 0);
		             					slime.getWorld().playSound(block, Sound.ENTITY_SLIME_DEATH, 1, 0);
		             					slime.getWorld().playSound(block, Sound.BLOCK_SLIME_BLOCK_BREAK, 1, 0);
		             					slime.remove();
						            }
	                    	}, j.getAndIncrement()/20+1);
		                }
	                    else {
	                    	line.forEach(i -> {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
     									slime.teleport(i);
    			             			p.getWorld().spawnParticle(Particle.SLIME ,line.get(a.get()), 3, 1,1,1,0);
						            }
			                	   }, j.getAndIncrement()/20); 
							}); 
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
					             			for(Entity e: slime.getNearbyEntities(3, 3, 3)) {
			             						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")) {
			             							LivingEntity le = (LivingEntity)e;
			             							if (le instanceof Player) 
			            							{
			            								PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
			            								Player p1 = (Player) le;
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
			             							le.damage(0,p);
			             							le.damage(player_damage.get(p.getName())*0.45*(1+csd.SlimeBall.get(p.getUniqueId())*0.02),p);
			             							hold.holding(p, le, 20l);
			             							le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2, false, false));
			             							le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 2, false, false));       							
			             						}
			             					}
			             					slime.getWorld().spawnParticle(Particle.SLIME, slime.getLocation(), 460, 3,3,3);
			             					slime.getWorld().playSound(slime.getLocation(), Sound.ENTITY_SLIME_SQUISH, 1, 0);
			             					slime.getWorld().playSound(slime.getLocation(), Sound.ENTITY_SLIME_HURT, 1, 0);
			             					slime.getWorld().playSound(slime.getLocation(), Sound.ENTITY_SLIME_DEATH, 1, 0);
			             					slime.getWorld().playSound(slime.getLocation(), Sound.BLOCK_SLIME_BLOCK_BREAK, 1, 0);
			             					slime.remove();
							            }
				                	   }, j.getAndIncrement()/20+1); 
	                    }
						sdcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
					p.playSound(p.getLocation(), Sound.ENTITY_FISHING_BOBBER_THROW, 1.0f, 0f);
					p.playSound(p.getLocation(), Sound.ENTITY_SLIME_JUMP, 1.0f, 0f);
					Item slime = p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack(Material.SLIME_BALL));
					slime.setPickupDelay(5555);
					slime.setVelocity(p.getLocation().getDirection().normalize().multiply(2));
					slime.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    ArrayList<Location> line = new ArrayList<Location>();
                    AtomicInteger j = new AtomicInteger(0);
                    AtomicInteger a = new AtomicInteger(0);
                    AtomicInteger b = new AtomicInteger(0);
                    for(double d = 0.1; d <= 5; d += 0.1) {
	                    Location pl = p.getLocation();
						pl.add(pl.getDirection().normalize().multiply(d));
						line.add(pl);
                    }
                    if(line.stream().filter(o -> o.getWorld().getNearbyEntities(o,1.3,1.3,1.3).stream().filter(en -> en instanceof LivingEntity && en!=p).findFirst().isPresent()).findFirst().isPresent()) 
                    {
	                    Location block =line.stream().filter(o -> o.getWorld().getNearbyEntities(o,1.3,1.3,1.3).stream().filter(en -> en instanceof LivingEntity && en!=p).findFirst().isPresent()).findFirst().get();
	                    for(int k=0; k<line.indexOf(block); k++) {
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
			                	public void run() 
				                {	
 									slime.teleport(line.get(a.getAndIncrement()));
			             			p.getWorld().spawnParticle(Particle.SLIME ,line.get(a.get()), 3, 1,1,1,0);
					            }
		                	   }, j.getAndIncrement()/20); 
						 }
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
			                	public void run() 
				                {	
	             					for(Entity e: slime.getNearbyEntities(3, 3, 3)) {
	             						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")) {
	             							LivingEntity le = (LivingEntity)e;
	             							if (le instanceof Player) 
	            							{
	            								PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
	            								Player p1 = (Player) le;
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
												enar.setDamage(player_damage.get(p.getName())*0.45*(1+csd.SlimeBall.get(p.getUniqueId())*0.02));
											}		
	             							le.damage(0,p);
	             							le.damage(player_damage.get(p.getName())*0.45*(1+csd.SlimeBall.get(p.getUniqueId())*0.02),p);
	             							hold.holding(p, le, 20l);
	             							le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2, false, false));
	             							le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 2, false, false));        							
	             						}
	             					}
	             					slime.getWorld().spawnParticle(Particle.SLIME, slime.getLocation(), 460, 3,3,3);
	             					slime.getWorld().playSound(block, Sound.ENTITY_SLIME_SQUISH, 1, 0);
	             					slime.getWorld().playSound(block, Sound.ENTITY_SLIME_HURT, 1, 0);
	             					slime.getWorld().playSound(block, Sound.ENTITY_SLIME_DEATH, 1, 0);
	             					slime.getWorld().playSound(block, Sound.BLOCK_SLIME_BLOCK_BREAK, 1, 0);
	             					slime.remove();
					            }
                    	}, j.getAndIncrement()/20+1);
	                }
                    else {
                    	line.forEach(i -> {
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
			                	public void run() 
				                {	
 									slime.teleport(i);
			             			p.getWorld().spawnParticle(Particle.SLIME ,line.get(a.get()), 3, 1,1,1,0);
					            }
		                	   }, j.getAndIncrement()/20); 
						}); 
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
				             			for(Entity e: slime.getNearbyEntities(3, 3, 3)) {
		             						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")) {
		             							LivingEntity le = (LivingEntity)e;
		             							if (le instanceof Player) 
		            							{
		            								PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
		            								Player p1 = (Player) le;
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
													enar.setDamage(player_damage.get(p.getName())*0.45*(1+csd.SlimeBall.get(p.getUniqueId())*0.02));
												}		            		
		             							le.damage(0,p);
		             							le.damage(player_damage.get(p.getName())*0.45*(1+csd.SlimeBall.get(p.getUniqueId())*0.02),p);
		             							hold.holding(p, le, 20l);
		             							le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2, false, false));
		             							le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 2, false, false));           							
		             						}
		             					}
		             					slime.getWorld().spawnParticle(Particle.SLIME, slime.getLocation(), 460, 3,3,3);
		             					slime.getWorld().playSound(slime.getLocation(), Sound.ENTITY_SLIME_SQUISH, 1, 0);
		             					slime.getWorld().playSound(slime.getLocation(), Sound.ENTITY_SLIME_HURT, 1, 0);
		             					slime.getWorld().playSound(slime.getLocation(), Sound.ENTITY_SLIME_DEATH, 1, 0);
		             					slime.getWorld().playSound(slime.getLocation(), Sound.BLOCK_SLIME_BLOCK_BREAK, 1, 0);
		             					slime.remove();
						            }
			                	   }, j.getAndIncrement()/20+1); 
                    }
	                sdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
		}
	}

	@EventHandler
	public void AcidCloud(PlayerInteractEvent d) 
	{
		Player p = d.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
		{
			Action ac = d.getAction();
	        
			
			
			
			if(playerclass.get(p.getUniqueId()) == 15) {
				if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
				{
				if(gdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            double timer = (gdcooldown.get(p.getName())/1000 + 0.25) - System.currentTimeMillis()/1000; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
			        }
		            else // if timer is done
		            {
		                gdcooldown.remove(p.getName()); // removing player from HashMap
						if(cloudh.containsKey(p)) {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("AcidCloud off").create());
		                		cloudh.remove(p);
		                		if(cloudt.containsKey(p)) {
		                			Bukkit.getServer().getScheduler().cancelTask(cloudt.get(p));
		                		}
						}
						else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("AcidCloud on").create());
							cloudh.put(p, 1);
							int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	if(!cloudh.containsKey(p)) return;
				                	final Location fl = p.getLocation(); 
				                    AreaEffectCloud cloud = (AreaEffectCloud) p.getWorld().spawnEntity(fl, EntityType.AREA_EFFECT_CLOUD);
									cloud.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				                    cloud.setRadius(1.5f);
				                    cloud.setSource(p);
				                    cloud.setSilent(false);
				                    cloud.setColor(Color.LIME);
				                    cloud.setRadiusPerTick(0.05f);
				                    cloud.setDuration(12);
				                    p.playSound(fl, Sound.BLOCK_BREWING_STAND_BREW, 0.1f, 0.2f);
									for(int k=0; k<3; k++) {
				                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				             		@Override
						                	public void run() 
							                {	
							                	for (Entity e : cloud.getNearbyEntities(1.5, 1.5, 1.5))
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
						                				if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
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
																enar.setDamage(player_damage.get(p.getName())*0.07*(1+csd.AcidCloud.get(p.getUniqueId())*0.032));
															}		 
															le.damage(0, p);
															le.damage(player_damage.get(p.getName())*0.07*(1+csd.AcidCloud.get(p.getUniqueId())*0.032), p);
						                				}
													}
												}
								            }
			                    		},	 k*5); 
									}
				                }
							}, 1, 3); 
							cloudt.put(p,task);
						}
			            gdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
		        	if(cloudh.containsKey(p)) {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("AcidCloud off").create());
	                		cloudh.remove(p);
	                		if(cloudt.containsKey(p)) {
	                			Bukkit.getServer().getScheduler().cancelTask(cloudt.get(p));
	                		}
					}
					else {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("AcidCloud on").create());
						cloudh.put(p, 1);
						int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	if(!cloudh.containsKey(p)) return;
			                	final Location fl = p.getLocation(); 
			                    AreaEffectCloud cloud = (AreaEffectCloud) p.getWorld().spawnEntity(fl, EntityType.AREA_EFFECT_CLOUD);
								cloud.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			                    cloud.setRadius(1.5f);
			                    cloud.setSource(p);
			                    cloud.setSilent(false);
			                    cloud.setColor(Color.LIME);
			                    cloud.setRadiusPerTick(0.05f);
			                    cloud.setDuration(12);
			                    p.playSound(fl, Sound.BLOCK_BREWING_STAND_BREW, 0.1f, 0.2f);
								for(int k=0; k<3; k++) {
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
						                	for (Entity e : cloud.getNearbyEntities(1.5, 1.5, 1.5))
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
					                				if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
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
															enar.setDamage(player_damage.get(p.getName())*0.07*(1+csd.AcidCloud.get(p.getUniqueId())*0.022));
														}		  
														le.damage(0, p);
														le.damage(player_damage.get(p.getName())*0.07*(1+csd.AcidCloud.get(p.getUniqueId())*0.022), p);
					                				}
												}
											}
							            }
		                    		},	 k*5); 
								}
			                }
						}, 1, 3); 
						cloudt.put(p,task);
					}
		            gdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
		}
		}
	}
	

	@EventHandler
	public void Cloud(PlayerQuitEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
    	if(cloudh.containsKey(p)) {
        		cloudh.remove(p);
        		if(cloudt.containsKey(p)) {
        			Bukkit.getServer().getScheduler().cancelTask(cloudt.get(p));
        		}
		}
	}

	@EventHandler
	public void Cloud(PlayerDeathEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getEntity();
    	if(cloudh.containsKey(p)) {
        	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("cloud off").create());
        		cloudh.remove(p);
        		if(cloudt.containsKey(p)) {
        			Bukkit.getServer().getScheduler().cancelTask(cloudt.get(p));
        		}
		}
		
	}
	
	@EventHandler
	public void Napalm(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")))
		{
			int sec = 8;
	
	        
			
			
			
			if(playerclass.get(p.getUniqueId()) == 15 && !p.isSneaking()) {
				ev.setCancelled(true);
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (cdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Napalm").create());
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap
		                    p.playSound(p.getLocation(), Sound.ENTITY_TNT_PRIMED, 1.0f, 2f);
			            	p.playSound(p.getLocation(), Sound.BLOCK_FIRE_AMBIENT, 1.0f, 2f);
		                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
							ItemStack is = new ItemStack(Material.MAGMA_BLOCK);
							Item solid = p.getWorld().dropItem(p.getEyeLocation(), is);
							solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							solid.setMetadata("napalm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							solid.setGlowing(true);
							solid.setPickupDelay(9999);
		                    solid.setVelocity(p.getLocation().getDirection().normalize().multiply(1.6));
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                    p.getWorld().spawnParticle(Particle.FLAME, solid.getLocation(), 300,1,1,1,1);
				                    p.getWorld().spawnParticle(Particle.LAVA, solid.getLocation(), 30,1,1,1,1);
				                    p.getWorld().spawnParticle(Particle.BLOCK_CRACK, solid.getLocation(), 300,6,6,6,1,Material.MAGMA_BLOCK.createBlockData());
				                    p.playSound(solid.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 0);
				                    p.playSound(solid.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 0);
				                    for (Entity e : solid.getNearbyEntities(4.5, 4.5, 4.5))
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
												{
													le.setFireTicks(150);
													for(int n = 0; n<10; n++) {
									                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											                @Override
											                public void run() 
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
																	enar.setDamage(player_damage.get(p.getName())*0.035*(1+csd.Coagulation.get(p.getUniqueId())*0.035));
																}	
											                    le.damage(0,p);
											                    le.damage(player_damage.get(p.getName())*0.035*(1+csd.Coagulation.get(p.getUniqueId())*0.035),p);
											                }
									                	 }, n*15); 														
													}
												}
										}
									}
				                    p.getWorld().getEntities().stream().filter(en -> en.hasMetadata("napalm of"+p.getName())).forEach(n -> n.remove());
				                }
		                    }, 40); 
							cdcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	p.playSound(p.getLocation(), Sound.ENTITY_TNT_PRIMED, 1.0f, 2f);
		            	p.playSound(p.getLocation(), Sound.BLOCK_FIRE_AMBIENT, 1.0f, 2f);
	                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
						ItemStack is = new ItemStack(Material.MAGMA_BLOCK);
						Item solid = p.getWorld().dropItem(p.getEyeLocation(), is);
						solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						solid.setMetadata("napalm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						solid.setGlowing(true);
						solid.setPickupDelay(9999);
	                    solid.setVelocity(p.getLocation().getDirection().normalize().multiply(1.6));
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                    p.getWorld().spawnParticle(Particle.FLAME, solid.getLocation(), 300,1,1,1,1);
			                    p.getWorld().spawnParticle(Particle.LAVA, solid.getLocation(), 30,1,1,1,1);
			                    p.getWorld().spawnParticle(Particle.BLOCK_CRACK, solid.getLocation(), 300,6,6,6,1,Material.MAGMA_BLOCK.createBlockData());
			                    p.playSound(solid.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 0);
			                    p.playSound(solid.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 0);
			                    for (Entity e : solid.getNearbyEntities(4.5, 4.5, 4.5))
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
											{
												le.setFireTicks(150);
												for(int n = 0; n<10; n++) {
								                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										                @Override
										                public void run() 
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
																enar.setDamage(player_damage.get(p.getName())*0.035*(1+csd.Coagulation.get(p.getUniqueId())*0.03));
															}	
										                    le.damage(0,p);
										                    le.damage(player_damage.get(p.getName())*0.035*(1+csd.Coagulation.get(p.getUniqueId())*0.03),p);
										                }
								                	 }, n*15); 														
												}
											}
									}
								}
			                    p.getWorld().getEntities().stream().filter(en -> en.hasMetadata("napalm of"+p.getName())).forEach(n -> n.remove());
			                }
                	   }, 40); 
		                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
		}
	
	}
	
	
	@EventHandler
	public void Injection(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
		{
			Action ac = ev.getAction();
			int sec = 1;
	
	        
			
			
			if(playerclass.get(p.getUniqueId()) == 15) {
			if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				

				if(frcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (frcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Injection").create());
	                }
	                else // if timer is done
	                {
	                    frcooldown.remove(p.getName()); // removing player from HashMap
	                    p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_ATTACK_TARGET, 1.0f, 2f);
						p.playSound(p.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 1.0f, 1f);
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200,1,false,true));
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200,1,false,true));
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200,1,false,true));
		                if(!extracted.containsKey(p)) {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You Should Extract First").create());
			            	sec = 0;
		                }
		                if (extracted.containsEntry(p, 0)) {
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 200,0,false,true));
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 50,0,false,true));
		                }
		                if (extracted.containsEntry(p, 1)) {
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200,1,false,true));
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200,1,false,true));
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 50,0,false,true));
		                }
		                if (extracted.containsEntry(p, 2)) {
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200,1,false,true));
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 200,1,false,true));
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 200,1,false,true));
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 50,0,false,true));
		                }
		                if (extracted.containsEntry(p, 3)) {
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200,1,false,true));
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 200,1,false,true));
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 50,0,false,true));
		                }
		                if (extracted.containsEntry(p, 4)) {
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,1,false,false));
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200,1,false,true));
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 50,0,false,true));
		                }
						frcooldown.put(p.getName(), System.currentTimeMillis());  
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_ATTACK_TARGET, 1.0f, 2f);
					p.playSound(p.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 1.0f, 1f);
                	p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200,1,false,true));
                	p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200,1,false,true));
                	p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200,1,false,true));
	                if(!extracted.containsKey(p)) {
		            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You Should Extract First").create());
		            	sec = 0;
	                }
	                if (extracted.containsEntry(p, 0)) {
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 200,0,false,true));
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 50,0,false,true));
	                }
	                if (extracted.containsEntry(p, 1)) {
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200,1,false,true));
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200,1,false,true));
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 50,0,false,true));
	                }
	                if (extracted.containsEntry(p, 2)) {
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200,1,false,true));
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 200,1,false,true));
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 200,1,false,true));
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 50,0,false,true));
	                }
	                if (extracted.containsEntry(p, 3)) {
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200,1,false,true));
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 200,1,false,true));
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 50,0,false,true));
	                }
	                if (extracted.containsEntry(p, 4)) {
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,1,false,false));
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200,1,false,true));
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 50,0,false,true));
	                }
					frcooldown.put(p.getName(), System.currentTimeMillis());  
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	
	@EventHandler
	public void Extraction(PlayerInteractEntityEvent d) 
	{
		int sec = 1;
		if(d.getPlayer() instanceof Player && d.getRightClicked() instanceof LivingEntity) 
		{
		Player p = d.getPlayer();
		if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")))
		{
			LivingEntity le = (LivingEntity)d.getRightClicked();
	        
	        d.setCancelled(true);
			
	
			
			
			if(playerclass.get(p.getUniqueId()) == 15 && (p.isSneaking()) && !(p.isSprinting())) {
					d.setCancelled(true);
				if(stcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            long timer = (stcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
		            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Extraction").create());
			        }
		            else // if timer is done
		            {
		                stcooldown.remove(p.getName()); // removing player from HashMap
		                le.playEffect(EntityEffect.HURT_BERRY_BUSH);
		                le.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200,2,false,false));
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_WEAK, 1.0f, 0f);
						p.playSound(p.getLocation(), Sound.ENTITY_COW_MILK, 1.0f, 2f);
						if(le.getCategory() == EntityCategory.ARTHROPOD) {
							extracted.put(p, 0);
						}
						else if(le.getCategory() == EntityCategory.UNDEAD) {
							extracted.put(p, 1);
						}
						else if(le.getCategory() == EntityCategory.WATER) {
							extracted.put(p, 2);
						}
						else if(le.getType() == EntityType.ENDERMAN) {
							extracted.put(p, 3);
						}
						else {
							extracted.put(p, 4);
						}
			            stcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
	                le.playEffect(EntityEffect.HURT_BERRY_BUSH);
	                le.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200,2,false,false));
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_WEAK, 1.0f, 0f);
					p.playSound(p.getLocation(), Sound.ENTITY_COW_MILK, 1.0f, 2f);
					if(le.getCategory() == EntityCategory.ARTHROPOD) {
						extracted.put(p, 0);
					}
					else if(le.getCategory() == EntityCategory.UNDEAD) {
						extracted.put(p, 1);
					}
					else if(le.getCategory() == EntityCategory.WATER) {
						extracted.put(p, 2);
					}
					else if(le.getType() == EntityType.ENDERMAN) {
						extracted.put(p, 3);
					}
					else {
						extracted.put(p, 4);
					}
		            stcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
		}
		}
	}
	

	@EventHandler
	public void MolotovCocktail(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) {
		Action ac = ev.getAction();
		int sec = 6;

        
		
		
		
			if(playerclass.get(p.getUniqueId()) == 15 && (!p.isSneaking())&& !p.isOnGround() && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK) &&(ac!= Action.RIGHT_CLICK_AIR)&&(ac!= Action.RIGHT_CLICK_AIR))
			{
				ev.setCancelled(true);
				if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            long timer = (smcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
		            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use MolotovCocktail").create());
			        }
		            else // if timer is done
		            {
		                smcooldown.remove(p.getName()); // removing player from HashMapLocation dam = p.getLocation();
	                    ItemStack is = new ItemStack(Material.SPLASH_POTION);
	                    ItemMeta imeta = is.getItemMeta();
	                    PotionMeta pometa = (PotionMeta)imeta;
	                    pometa.setColor(Color.GREEN);
	                    is.setItemMeta(pometa);
	                    for(double an = -Math.PI/3; an<=Math.PI/3; an += Math.PI/9) {
		                    ThrownPotion thr = (ThrownPotion) p.launchProjectile(ThrownPotion.class);
		                    thr.setShooter(p);
		                    thr.setBounce(false);
		                    thr.setItem(is);
		                    thr.setVelocity(p.getLocation().getDirection().rotateAroundY(an));
		                    thr.setFireTicks(55);
		                    thr.setMetadata("thrown of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	                    	
	                    }
			            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
                    ItemStack is = new ItemStack(Material.SPLASH_POTION);
                    ItemMeta imeta = is.getItemMeta();
                    PotionMeta pometa = (PotionMeta)imeta;
                    pometa.setColor(Color.GREEN);
                    is.setItemMeta(pometa);
                    for(double an = -Math.PI/3; an<=Math.PI/3; an += Math.PI/9) {
	                    ThrownPotion thr = (ThrownPotion) p.launchProjectile(ThrownPotion.class);
	                    thr.setShooter(p);
	                    thr.setBounce(false);
	                    thr.setItem(is);
	                    thr.setVelocity(p.getLocation().getDirection().rotateAroundY(an));
	                    thr.setFireTicks(55);
	                    thr.setMetadata("thrown of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	                    	
                    }
		            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
		}
	}

	@EventHandler
	public void Throw(ProjectileHitEvent ev) 
	{
		
		if(ev.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)ev.getEntity().getShooter();
		    
			
			
			
			if(playerclass.get(p.getUniqueId()) == 15 && ev.getEntity().hasMetadata("thrown of"+p.getName())) {
				if(ev.getHitEntity()!=null) {
					ev.getHitEntity().getWorld().spawnParticle(Particle.FLAME, ev.getHitEntity().getLocation(), 30,2,2,2,1);
					for (Entity e : p.getWorld().getNearbyEntities(ev.getHitEntity().getLocation(), 3, 3, 3))
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
								{
				                    le.setFireTicks(55);
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
										enar.setDamage(player_damage.get(p.getName())*0.69*(1+csd.MolotovCocktail.get(p.getUniqueId())*0.0435));
									}	
				                    le.damage(0,p);
				                    le.damage(player_damage.get(p.getName())*0.69*(1+csd.MolotovCocktail.get(p.getUniqueId())*0.0435),p);
								}
						}
					}
				}
				else if(ev.getHitBlock()!=null) {
					ev.getHitBlock().getWorld().spawnParticle(Particle.FLAME, ev.getHitBlock().getLocation(), 30,2,2,2,1);
					for (Entity e : p.getWorld().getNearbyEntities(ev.getHitBlock().getLocation(), 3, 3, 3))
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
								{
				                    le.setFireTicks(55);
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
										enar.setDamage(player_damage.get(p.getName())*0.69*(1+csd.MolotovCocktail.get(p.getUniqueId())*0.0435));
									}	
				                    le.damage(0,p);
				                    le.damage(player_damage.get(p.getName())*0.69*(1+csd.MolotovCocktail.get(p.getUniqueId())*0.0435),p);
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
        
		
		
		
		
			if(playerclass.get(p.getUniqueId()) == 15 && ((is.getType().name().contains("PICKAXE"))) && p.isSneaking())
			{
				ev.setCancelled(true);
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (sultcooldown.get(p.getName())/1000 + 50) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use VX").create());
	                }
	                else // if timer is done
	                {
	                    sultcooldown.remove(p.getName()); // removing player from HashMap
	                    p.setInvulnerable(true);
	                    for(int i = 0; i<10; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {
			             			p.playSound(p.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 0.5f, 0.5f);
			             					p.getWorld().spawnParticle(Particle.REDSTONE, p.getLocation(), 1000, 10,2,10,0, new DustOptions(Color.GREEN, 2));
			             					p.getWorld().spawnParticle(Particle.REDSTONE, p.getLocation(), 1000, 10,2,10,0, new DustOptions(Color.LIME, 2));
			             					p.getWorld().spawnParticle(Particle.REDSTONE, p.getLocation(), 1000, 10,2,10,0, new DustOptions(Color.FUCHSIA, 2));
							            }
				                	   }, i*10); 
	                    }
	                    for(int i = 0; i<10; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {
					             			for(Entity e: p.getNearbyEntities(10,10,10)) {
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
			             						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")) {
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
														enar.setDamage(player_damage.get(p.getName())/3);
													}	
			             							p.setSprinting(true);
			             							le.damage(0, p);
			             							le.damage(player_damage.get(p.getName())/3, p);
			             		                    le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,200,1,false,false));
			             							p.setSprinting(false);	
			             						}
			             					}
							            }
				                	   }, i*10); 
	                    }
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
		                	public void run() 
			                {
			                    p.setInvulnerable(false);
				            }
	                	   }, 101); 
		                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    p.setInvulnerable(true);
                    for(int i = 0; i<10; i++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {
		             			p.playSound(p.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 0.5f, 0.5f);
             					p.getWorld().spawnParticle(Particle.REDSTONE, p.getLocation(), 1000, 10,2,10,0, new DustOptions(Color.GREEN, 2));
             					p.getWorld().spawnParticle(Particle.REDSTONE, p.getLocation(), 1000, 10,2,10,0, new DustOptions(Color.LIME, 2));
             					p.getWorld().spawnParticle(Particle.REDSTONE, p.getLocation(), 1000, 10,2,10,0, new DustOptions(Color.FUCHSIA, 2));
						            }
			                	   }, i*10); 
                    }
                    for(int i = 0; i<10; i++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {
				             			for(Entity e: p.getNearbyEntities(10,10,10)) {
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
		             						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")) {
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
													enar.setDamage(player_damage.get(p.getName())/3);
												}	
		             							p.setSprinting(true);
		             							le.damage(0, p);
		             							le.damage(player_damage.get(p.getName())/3, p);
		             		                    le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,200,1,false,false));
		             							p.setSprinting(false);	
		             						}
		             					}
						            }
			                	   }, i*10); 
                    }
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
	                	public void run() 
		                {
		                    p.setInvulnerable(false);
			            }
                	   }, 101); 
	                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();
        

		
		
		if(playerclass.get(p.getUniqueId()) == 15)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") )
			{
					e.setCancelled(true);
			}
		}
		
	}
	
	@EventHandler
	public void Poisonous(EntityDamageByEntityEvent d) 
	{		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) {
			LivingEntity le = (LivingEntity)d.getEntity();
	        
			
			
			if(playerclass.get(p.getUniqueId()) == 15 && !le.hasMetadata("fake")) {
					d.setDamage(d.getDamage()*(1+csd.Poisonous.get(p.getUniqueId())*0.031));
				}
			}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof EnderDragon) 
		{
			Arrow ar = (Arrow)d.getDamager();
			EnderDragon le = (EnderDragon)d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
		        
				

				
				
				if(playerclass.get(p.getUniqueId()) == 15 && !le.hasMetadata("fake")) {
						if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) {

							if(playerclass.get(p.getUniqueId()) == 15 && !le.hasMetadata("fake")) {
									d.setDamage(d.getDamage()*(1+csd.Poisonous.get(p.getUniqueId())*0.031));
								
							}
						}
					}
			}
		}
	}

	@EventHandler
	public void Poisonous(EntityDamageEvent d) 
	{		
        if (!(d.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) d.getEntity();
        
		
		
		if(d.getCause().equals(DamageCause.POISON)) 
		{
	        if(playerclass.get(p.getUniqueId()) == 15) {
	        	d.setCancelled(true);
	        }
		}
	}
	
	@EventHandler
	public void Damagegetter(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 15) {
				if(p.getInventory().getItemInMainHand().getType()==Material.IRON_PICKAXE)
				{
						player_damage.put(p.getName(), 7 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if( p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_PICKAXE)
				{
						player_damage.put(p.getName(), 8 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_PICKAXE)
				{
						player_damage.put(p.getName(), 4 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.STONE_PICKAXE)
				{
						player_damage.put(p.getName(), 5 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_PICKAXE)
				{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_PICKAXE)
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
        

		
		
		if(playerclass.get(p.getUniqueId()) == 15) {
			if(p.getInventory().getItemInMainHand().getType()==Material.IRON_PICKAXE)
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
			
			if( p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_PICKAXE)
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_PICKAXE)
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.STONE_PICKAXE)
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
			}
			
			if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_PICKAXE)
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_PICKAXE)
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
			    
				
				
				if(playerclass.get(p.getUniqueId()) == 15) {
					if(p.getInventory().getItemInMainHand().getType()==Material.IRON_PICKAXE)
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
					
					if( p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_PICKAXE)
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_PICKAXE)
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.STONE_PICKAXE)
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
					}
					
					if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_PICKAXE)
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_PICKAXE)
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



