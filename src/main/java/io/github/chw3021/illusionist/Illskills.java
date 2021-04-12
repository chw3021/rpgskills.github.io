package io.github.chw3021.illusionist;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.firemage.FireSkillsData;
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
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Illskills implements Listener, Serializable {
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 8919713269801972990L;
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> stcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private IllSkillsData isd = new IllSkillsData(IllSkillsData.loadData(path +"/plugins/RPGskills/IllSkillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		IllSkillsData i = new IllSkillsData(IllSkillsData.loadData(path +"/plugins/RPGskills/IllSkillsData.data"));
		isd = i;
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
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Illskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				IllSkillsData i = new IllSkillsData(IllSkillsData.loadData(path +"/plugins/RPGskills/IllSkillsData.data"));
				isd = i;
			}
			
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		IllSkillsData i = new IllSkillsData(IllSkillsData.loadData(path +"/plugins/RPGskills/IllSkillsData.data"));
		isd = i;
		
	}
	
	@EventHandler
	public void JackoLantern(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 6;
        
		
		
		if(playerclass.get(p.getUniqueId()) == 11)
		{
			if((p.getInventory().getItemInMainHand().getType()==Material.STICK || p.getInventory().getItemInMainHand().getType()==Material.BOOK || p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && !(p.isSneaking()))
			{
				ev.setCancelled(true);
				
                Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
				
				if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Jack O'Lantern").create());
	                }
	                else // if timer is done
	                {
	                    sdcooldown.remove(p.getName()); // removing player from HashMap
						ItemStack jack = new ItemStack(Material.JACK_O_LANTERN, 1);	
						Item jo = p.getWorld().dropItemNaturally(l, jack);
						jo.setGlowing(true);
						jo.setPickupDelay(5000);
						jo.setInvulnerable(true);
						jo.setThrower(p.getUniqueId());
						jo.setMetadata("jo of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	Stream<Entity> jos = p.getWorld().getEntities().stream().filter(i -> i.hasMetadata("jo of"+p.getName()));
			                	jos.forEach(i -> i.remove());
			                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
				        		p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, l, 1,1,1,1);
			                	for (Entity e : p.getWorld().getNearbyEntities(l, 4, 4, 4))
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
													enar.setDamage(player_damage.get(p.getName())*0.68*(1+isd.JackoLantern.get(p.getUniqueId())*0.062));								
												}
												p.setSprinting(true);
												le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
							                    le.damage(0,p);
												le.damage(player_damage.get(p.getName())*0.68*(1+isd.JackoLantern.get(p.getUniqueId())*0.062), p);
												le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
							                    le.playEffect(EntityEffect.HURT_EXPLOSION);
												p.setSprinting(false);
													
											}
									}
		                    		else if (e instanceof Player) 
									{
										PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
								}
			                }
                	   }, 7); 
	                    
		                sdcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	ItemStack jack = new ItemStack(Material.JACK_O_LANTERN, 1);	
					Item jo = p.getWorld().dropItemNaturally(l, jack);
					jo.setGlowing(true);
					jo.setPickupDelay(5000);
					jo.setInvulnerable(true);
					jo.setThrower(p.getUniqueId());
					jo.setMetadata("jo of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	Stream<Entity> jos = p.getWorld().getEntities().stream().filter(i -> i.hasMetadata("jo of"+p.getName()));
		                	jos.forEach(i -> i.remove());
		                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
			        		p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, l, 1,1,1,1);
		                	for (Entity e : p.getWorld().getNearbyEntities(l, 4, 4, 4))
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
												enar.setDamage(player_damage.get(p.getName())*0.68*(1+isd.JackoLantern.get(p.getUniqueId())*0.062));								
											}
											p.setSprinting(true);
											le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
						                    le.damage(0,p);
											le.damage(player_damage.get(p.getName())*0.68*(1+isd.JackoLantern.get(p.getUniqueId())*0.062), p);
						                    le.playEffect(EntityEffect.HURT_EXPLOSION);
						                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, player_damage.get(p.getName())*0.8+0.5));
											p.setSprinting(false);
												
										}
								}
	                    		else if (e instanceof Player) 
								{
									PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
							}
		                }
            	   }, 7); 
                    
	                sdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
		}
		}
	
	@EventHandler
	public void Paradox(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 13;
        

		
		
		if(playerclass.get(p.getUniqueId()) == 11) {
		if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR||ac==Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType()==Material.STICK || p.getInventory().getItemInMainHand().getType()==Material.BOOK || p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD)
			{
				

				if(swcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (swcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Paradox").create());
	                }
	                else // if timer is done
	                {
	                    swcooldown.remove(p.getName()); // removing player from HashMap
	                    p.playSound(p.getLocation(), Sound.BLOCK_CHEST_LOCKED, 1,2);
	                    p.playSound(p.getLocation(), Sound.ITEM_LODESTONE_COMPASS_LOCK, 1,2);
	                    p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1,2);
						p.sendTitle(ChatColor.MAGIC + "AAAAAA", null);
						p.setHealth(p.getMaxHealth());
	                	swcooldown.remove(p.getName());
	                	sdcooldown.remove(p.getName());
	                	cdcooldown.remove(p.getName());
	                	frcooldown.remove(p.getName());
	                	smcooldown.remove(p.getName());
	                	stcooldown.remove(p.getName());
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
	                	
	                    
		                swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		                
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	p.sendTitle(ChatColor.MAGIC + "AAAAAA", null);
                    p.playSound(p.getLocation(), Sound.BLOCK_CHEST_LOCKED, 1,2);
                    p.playSound(p.getLocation(), Sound.ITEM_LODESTONE_COMPASS_LOCK, 1,2);
                    p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1,2);
					p.setHealth(p.getMaxHealth());
                	swcooldown.remove(p.getName());
                	sdcooldown.remove(p.getName());
                	cdcooldown.remove(p.getName());
                	frcooldown.remove(p.getName());
                	smcooldown.remove(p.getName());
                	stcooldown.remove(p.getName());
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
	                swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	                
	            }
			}
		}
		}
	}

	
	@EventHandler
	public void FakeDoll(PlayerArmorStandManipulateEvent ev)
	{
		if(ev.getRightClicked().hasMetadata("fake")){
			ev.setCancelled(true);
		}
	}
	
	@EventHandler
	public void FakeDoll(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		int sec = 5;

        
		
		
		
		if(playerclass.get(p.getUniqueId()) == 11 && isd.FakeDoll.get(p.getUniqueId())>=1) {
			if((p.getInventory().getItemInMainHand().getType()==Material.STICK || p.getInventory().getItemInMainHand().getType()==Material.BOOK || p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && p.isSneaking())
			{
				ev.setCancelled(true);
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (cdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use FakeDoll").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap
		                    ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
		                    as.setCustomName(p.getName());
		                    as.setArms(true);
		                    as.setBasePlate(false);
		                    as.setInvulnerable(true);
		                    as.setInvisible(true);
		                    as.setBoots(p.getInventory().getBoots());
		                    as.setHelmet(p.getInventory().getHelmet());
		                    as.setChestplate(p.getInventory().getChestplate());
		                    as.setLeggings(p.getInventory().getLeggings());
		                    as.setCanPickupItems(false);
		                    as.setItemInHand(p.getInventory().getItemInMainHand());
		                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		                    as.setArrowsInBody(p.getArrowsInBody());
		                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 110, 1, false, false));
		                    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 110, 1, false, false));
							p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1, 2);
		                    for(Entity e : as.getNearbyEntities(10, 10, 10)) {
		                    	if(e instanceof Mob) {
		                    		Mob le = (Mob)e;
		                    		le.setTarget(as);
		                    	}
		                    }
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	as.remove();
				                }
	                	   }, 100);
		                    
							cdcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
	                    as.setCustomName(p.getName());
	                    as.setArms(true);
	                    as.setBasePlate(false);
	                    as.setInvulnerable(true);
	                    as.setInvisible(true);
	                    as.setBoots(p.getInventory().getBoots());
	                    as.setHelmet(p.getInventory().getHelmet());
	                    as.setChestplate(p.getInventory().getChestplate());
	                    as.setLeggings(p.getInventory().getLeggings());
	                    as.setCanPickupItems(false);
	                    as.setItemInHand(p.getInventory().getItemInMainHand());
	                    as.setArrowsInBody(p.getArrowsInBody());
	                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 110, 1, false, false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 110, 1, false, false));
						p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1, 2);
	                    for(Entity e : as.getNearbyEntities(10, 10, 10)) {
	                    	if(e instanceof Mob) {
	                    		Mob le = (Mob)e;
	                    		le.setTarget(as);
	                    	}
	                    }
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	as.remove();
			                }
                	   }, 100); 
	                    
		                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
		}
	
	}
	
	@EventHandler
	public void Distortion(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 13;

        
		
		
		if(playerclass.get(p.getUniqueId()) == 11) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType()==Material.STICK || p.getInventory().getItemInMainHand().getType()==Material.BOOK || p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD)
			{
				

				if(frcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (frcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Distortion").create());
	                }
	                else // if timer is done
	                {
	                    frcooldown.remove(p.getName()); // removing player from HashMap
						p.damage(p.getHealth()*0.4);
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30, 4, false, false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 2, false, false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 80, 80, false, false));
						p.getWorld().spawnParticle(Particle.SPELL_INSTANT, p.getLocation(), 555, 6,3,6); 
						for (Entity a : p.getWorld().getNearbyEntities(p.getLocation(), 6, 6, 6))
						{
                    		if (a instanceof Player) 
							{
								Player p1 = (Player) a;
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
									{
									continue;
									}
								}
							}
							if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)a;
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
									enar.setDamage(player_damage.get(p.getName())*1.634*(1+isd.Distortion.get(p.getUniqueId())*0.0839));								
								}
								le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
								p.setSprinting(true);
								le.damage(0, p);
			                    le.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 180, 100, false, false));
								le.damage(player_damage.get(p.getName())*1.634*(1+isd.Distortion.get(p.getUniqueId())*0.0839), p);
								le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
								
								p.setSprinting(false);
									
							}
						}
						p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 1.0f, 2f);

						frcooldown.put(p.getName(), System.currentTimeMillis());  
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
					p.damage(p.getHealth()*0.4);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30, 4, false, false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 2, false, false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 80, 80, false, false));
					p.getWorld().spawnParticle(Particle.SPELL_INSTANT, p.getLocation(), 555, 6,3,6); 
					for (Entity a : p.getWorld().getNearbyEntities(p.getLocation(), 6, 6, 6))
					{
                		if (a instanceof Player) 
						{
							Player p1 = (Player) a;
							if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
							if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
								{
								continue;
								}
							}
						}
						if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
						{
							LivingEntity le = (LivingEntity)a;
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
								enar.setDamage(player_damage.get(p.getName())*1.634*(1+isd.Distortion.get(p.getUniqueId())*0.0839));								
							}
							le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
							p.setSprinting(true);
							le.damage(0, p);
		                    le.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 180, 100, false, false));
							le.damage(player_damage.get(p.getName())*1.634*(1+isd.Distortion.get(p.getUniqueId())*0.0839), p);
							le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
							
							p.setSprinting(false);
								
						}
					}
					p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 1.0f, 2f);

					frcooldown.put(p.getName(), System.currentTimeMillis());  
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	
	@EventHandler
	public void Switch(EntityDamageByEntityEvent d) 
	{
		int sec = 2;
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
		Location l = p.getLocation();
		Location el =le.getLocation();
        
		

		
		
		if(playerclass.get(p.getUniqueId()) == 11) {
				if((p.getInventory().getItemInMainHand().getType()==Material.STICK || p.getInventory().getItemInMainHand().getType()==Material.BOOK || p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && (p.isSneaking()) && !(p.isSprinting()))
				{
				if(stcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            long timer = (stcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
		            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Switch").create());
			        }
		            else // if timer is done
		            {
		                stcooldown.remove(p.getName()); // removing player from HashMap
						le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
						d.setDamage(d.getDamage()*1.64*(1+isd.Switch.get(p.getUniqueId())*0.07));
						p.setInvulnerable(true);
						p.teleport(el);
						le.teleport(l);	
						p.setInvulnerable(false);
						le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
						p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 1.0f, 2f);
						p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 0f);
			            stcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
					le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
					d.setDamage(d.getDamage()*1.64*(1+isd.Switch.get(p.getUniqueId())*0.07));
					p.setInvulnerable(true);
					p.teleport(el);
					le.teleport(l);	
					p.setInvulnerable(false);
					le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
					p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 1.0f, 2f);
					p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 0f);
		            stcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
		}
		}
	}
	

	@EventHandler
	public void Trick(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 6;

        
		
		
		if(playerclass.get(p.getUniqueId()) == 11) {
			final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
			if(!l.getBlock().isPassable()) {
            l.setY(l.getY()+1);}
			l.setDirection(p.getLocation().getDirection());
			if(l.getBlock().isPassable()) {
			if((ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK)&& ac != Action.RIGHT_CLICK_AIR&& ac != Action.RIGHT_CLICK_BLOCK)
			{
                Location dam = p.getLocation();
				
				if((p.getInventory().getItemInMainHand().getType()==Material.STICK || p.getInventory().getItemInMainHand().getType()==Material.BOOK || p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && !(p.isSneaking()) && !(p.isOnGround()) )
					{
					ev.setCancelled(true);
					if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            long timer = (smcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Trick").create());
				        }
			            else // if timer is done
			            {
			                smcooldown.remove(p.getName()); // removing player from HashMapLocation dam = p.getLocation();
							p.swingMainHand();
							p.swingOffHand();
							p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, dam, 2, 1, 1, 1);
							p.teleport(l);
							p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
		                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 40, 1, false, false));
							for (Entity e : p.getWorld().getNearbyEntities(dam, 4, 5, 4))
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
												enar.setDamage(player_damage.get(p.getName())*0.66*(1+isd.Trick.get(p.getUniqueId())*0.0535));								
											}
											p.setSprinting(true);
											le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
											le.damage(0, p);
											le.damage(player_damage.get(p.getName())*0.66*(1+isd.Trick.get(p.getUniqueId())*0.0535), p);
											le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
											p.setSprinting(false);
											le.playEffect(org.bukkit.EntityEffect.HURT_EXPLOSION);
										}
								}
							}
				            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
						p.swingMainHand();
						p.swingOffHand();
						p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, dam, 2, 1, 1, 1);
						p.teleport(l);
						p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 40, 1, false, false));
						for (Entity e : p.getWorld().getNearbyEntities(dam, 4, 5, 4))
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
											enar.setDamage(player_damage.get(p.getName())*0.66*(1+isd.Trick.get(p.getUniqueId())*0.0535));								
										}
										p.setSprinting(true);
										le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
										le.damage(0, p);
										le.damage(player_damage.get(p.getName())*0.66*(1+isd.Trick.get(p.getUniqueId())*0.0535), p);
										le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
										p.setSprinting(false);
										le.playEffect(org.bukkit.EntityEffect.HURT_EXPLOSION);
									}
							}
						}
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
		Item i = ev.getItemDrop();
		ItemStack is = i.getItemStack();
        
		
		
		
		
			if(playerclass.get(p.getUniqueId()) == 11 && ((is.getType().name().contains("STICK"))||(is.getType().name().contains("ROD"))||(is.getType().name().contains("BOOK"))) && p.isSneaking())
			{
				ev.setCancelled(true);
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (sultcooldown.get(p.getName())/1000 + 80) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use The Void").create());
	                }
	                else // if timer is done
	                {
	                    sultcooldown.remove(p.getName()); // removing player from HashMap
						p.sendTitle(ChatColor.MAGIC + "AAAAAA", null);
						p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_BLINDNESS, 1, 0);
		        		p.getWorld().spawnParticle(Particle.ASH, p.getLocation(), 1000,6,6,6,0);
	                    for(Entity e : p.getNearbyEntities(6.5, 6.5, 6.5)) {
		                    if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)e;
								{
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
									ArrayList<Location> line = new ArrayList<Location>();
				                    AtomicInteger j = new AtomicInteger(0);
				                    AtomicInteger b = new AtomicInteger(0);
				                    for(double d = 1.1; d > 0; d -= 0.05) {
					                    Location lel = le.getLocation();
					                    Location lel2 = le.getLocation();
										lel.add(0,d,0);
										lel2.add(0, -d, 0);
										line.add(lel2);
										line.add(lel);
				                    }
									line.forEach(l -> {
					                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() 
								                {
								                	le.teleport(line.get(b.getAndIncrement()));
													p.playSound(l, Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 1.0f, 2f);
									        		p.getWorld().spawnParticle(Particle.WARPED_SPORE, l, 10,1,1,1,0);
									        		p.getWorld().spawnParticle(Particle.PORTAL, l, 10,1,1,1,0);
									        		p.getWorld().spawnParticle(Particle.FALLING_NECTAR, l, 10,1,1,1,0);
								                }
					                	   }, j.incrementAndGet()); 
									}	);

				                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
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
													enar.setDamage(player_damage.get(p.getName())*9);								
												}
							                	p.setSprinting(true);
												le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
							                	le.damage(0,p);
												le.damage(player_damage.get(p.getName())*9, p);
												le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
							                	p.setSprinting(false);
							                }
				                	   }, j.incrementAndGet()); 
								}
							}
	                    }
		                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
					p.sendTitle(ChatColor.MAGIC + "AAAAAA", null);
					p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_BLINDNESS, 1, 0);
	        		p.getWorld().spawnParticle(Particle.ASH, p.getLocation(), 1000,6,6,6,0);
	            	for(Entity e : p.getNearbyEntities(6.5, 6.5, 6.5)) {
	                    if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
						{
							LivingEntity le = (LivingEntity)e;
							{
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
								ArrayList<Location> line = new ArrayList<Location>();
			                    AtomicInteger j = new AtomicInteger(0);
			                    AtomicInteger b = new AtomicInteger(0);
			                    for(double d = 1.1; d > 0; d -= 0.05) {
				                    Location lel = le.getLocation();
				                    Location lel2 = le.getLocation();
									lel.add(0,d,0);
									lel2.add(0, -d, 0);
									line.add(lel2);
									line.add(lel);
			                    }
								line.forEach(l -> {
				                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
							                	le.teleport(line.get(b.getAndIncrement()));
												p.playSound(l, Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 1.0f, 2f);
								        		p.getWorld().spawnParticle(Particle.WARPED_SPORE, l, 10,1,1,1,0);
								        		p.getWorld().spawnParticle(Particle.PORTAL, l, 10,1,1,1,0);
								        		p.getWorld().spawnParticle(Particle.FALLING_NECTAR, l, 10,1,1,1,0);
							                }
				                	   }, j.incrementAndGet()); 
								}	);

			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
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
												enar.setDamage(player_damage.get(p.getName())*9);								
											}
						                	p.setSprinting(true);
											le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
						                	le.damage(0,p);
											le.damage(player_damage.get(p.getName())*9, p);
											le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.MAGIC, p.getLevel()));
						                	p.setSprinting(false);
						                }
			                	   }, j.incrementAndGet()); 
							}
						}
                    }
	                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();
        

		
		
		if(playerclass.get(p.getUniqueId()) == 11)
		{
			if(p.getInventory().getItemInMainHand().getType()==Material.STICK || p.getInventory().getItemInMainHand().getType()==Material.BOOK || p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD )
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
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 11) {
				if(p.getInventory().getItemInMainHand().getType()==Material.STICK)
				{
						player_damage.put(p.getName(), 6 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.BOOK)
				{
						player_damage.put(p.getName(), 8 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				if(p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD)
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
			LivingEntity le = (LivingEntity)d.getEntity();
	        
	
			
			
			if(playerclass.get(p.getUniqueId()) == 11) {
				
				if(p.getInventory().getItemInMainHand().getType()==Material.STICK)
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
						if(p.hasPotionEffect(PotionEffectType.INVISIBILITY)) 
						{
							d.setDamage(d.getDamage()*1.23* (1+player_damage.get(p.getName())*0.0115)*(1+isd.Surprise.get(p.getUniqueId())*0.041));
							p.getWorld().spawnParticle(Particle.CRIT_MAGIC, le.getLocation(), 10, 1, 1, 1, 0);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.BOOK)
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
						if(p.hasPotionEffect(PotionEffectType.INVISIBILITY)) 
						{
							d.setDamage(d.getDamage()*1.23* (1+player_damage.get(p.getName())*0.0115)*(1+isd.Surprise.get(p.getUniqueId())*0.041));
							p.getWorld().spawnParticle(Particle.CRIT_MAGIC, le.getLocation(), 10, 1, 1, 1, 0);
						}
				}
				if(p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD)
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
						if(p.hasPotionEffect(PotionEffectType.INVISIBILITY)) 
						{
							d.setDamage(d.getDamage()*1.23* (1+player_damage.get(p.getName())*0.0315)*(1+isd.Surprise.get(p.getUniqueId())*0.041));
							p.getWorld().spawnParticle(Particle.CRIT_MAGIC, le.getLocation(), 10, 1, 1, 1, 0);
						}
				}
			}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
			Arrow ar = (Arrow)d.getDamager();
			Entity e = d.getEntity();
			LivingEntity le = (LivingEntity)d.getEntity();
	
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
			    
				
				
				if(playerclass.get(p.getUniqueId()) == 11) {
					
					if(p.getInventory().getItemInMainHand().getType()==Material.STICK)
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
							if(p.hasPotionEffect(PotionEffectType.INVISIBILITY)) 
							{
								d.setDamage(d.getDamage()*1.23* (1+player_damage.get(p.getName())*0.0315)*(1+isd.Surprise.get(p.getUniqueId())*0.041));
								p.getWorld().spawnParticle(Particle.CRIT_MAGIC, le.getLocation(), 10, 1, 1, 1, 0);
							}
					}
					
					if(p.getInventory().getItemInMainHand().getType()==Material.BOOK)
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
							if(p.hasPotionEffect(PotionEffectType.INVISIBILITY)) 
							{
								d.setDamage(d.getDamage()*1.23* (1+player_damage.get(p.getName())*0.0315)*(1+isd.Surprise.get(p.getUniqueId())*0.041));
								p.getWorld().spawnParticle(Particle.CRIT_MAGIC, le.getLocation(), 10, 1, 1, 1, 0);
							}
					}
					if(p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD)
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
							if(p.hasPotionEffect(PotionEffectType.INVISIBILITY)) 
							{
								d.setDamage(d.getDamage()*1.23* (1+player_damage.get(p.getName())*0.0315)*(1+isd.Surprise.get(p.getUniqueId())*0.041));
								p.getWorld().spawnParticle(Particle.CRIT_MAGIC, le.getLocation(), 10, 1, 1, 1, 0);
							}
					}
				}
			}
		}
	}
}



