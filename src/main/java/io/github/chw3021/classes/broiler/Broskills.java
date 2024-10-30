package io.github.chw3021.classes.broiler;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.party.Party;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Broskills extends Pak {

	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> stcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	//private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private static HashMap<UUID, LivingEntity> oneonly = new HashMap<UUID, LivingEntity>();
	private HashMap<UUID, Integer> tart = new HashMap<UUID, Integer>();
	private HashMap<UUID, Item> tar = new HashMap<UUID, Item>();
	private static HashMap<UUID, Integer> cactust = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Item> cactus = new HashMap<UUID, Item>();
	private static HashMap<UUID, LivingEntity> cactusl = new HashMap<UUID, LivingEntity>();
	private HashMap<UUID, Item> hooki = new HashMap<UUID, Item>();
	private HashMap<UUID, Integer> hookt1 = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> hookt2 = new HashMap<UUID, Integer>();
	private static HashMap<UUID, LivingEntity> hookl = new HashMap<UUID, LivingEntity>();
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private BroSkillsData wsd = new BroSkillsData(BroSkillsData.loadData(path +"/plugins/RPGskills/BroSkillsData.data"));

	private static final Broskills instance = new Broskills ();
	public static Broskills getInstance()
	{
		return instance;
	}

	
	public void er(PluginEnableEvent ev)
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		BroSkillsData w = new BroSkillsData(BroSkillsData.loadData(path +"/plugins/RPGskills/BroSkillsData.data"));
		wsd = w;
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
				if(oneonly.containsKey(p.getUniqueId())){
					oneonly.remove(p.getUniqueId());
					Bukkit.getServer().getScheduler().cancelTask(tart.get(p.getUniqueId()));
					tar.get(p.getUniqueId()).remove();
					tar.remove(p.getUniqueId());
					tart.remove(p.getUniqueId());
				}
				ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
				playerclass = cdata.playerclass;
			}

		}
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Broskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				BroSkillsData w = new BroSkillsData(BroSkillsData.loadData(path +"/plugins/RPGskills/BroSkillsData.data"));
				wsd = w;
			}

		}
	}

	
	public void nepreventer(PlayerJoinEvent ev)
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		BroSkillsData w = new BroSkillsData(BroSkillsData.loadData(path +"/plugins/RPGskills/BroSkillsData.data"));
		wsd = w;

	}

	
	public void TimeBomb(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();
		double sec = 6*(1-p.getAttribute(Attribute.LUCK).getValue()/1024);



		if(playerclass.get(p.getUniqueId()) == 25)
		{
			if((p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL")) && (p.isSneaking()))
			{
				ev.setCancelled(true);


				if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sdcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use TimeBomb").create());
	                }
	                else // if timer is done
	                {
	                    sdcooldown.remove(p.getName()); // removing player from HashMap
	                    if(oneonly.containsKey(p.getUniqueId())) {

	                    	LivingEntity le = oneonly.get(p.getUniqueId());
	    					p.getWorld().spawnParticle(Particle.ITEM, le.getLocation(), 50, 1,1,1, 0.21, new ItemStack(Material.CLOCK));
	    					p.playSound(le.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1, 0);
	    					p.playSound(le.getLocation(), Sound.ENTITY_TNT_PRIMED, 1, 2);
	                    	for(int i = 0 ; i <12; i ++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run()
					                {
										p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 0.6f, 2f);
										p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 0.6f, 2f);
					                }
								}, i*5);
	                    	}
	    					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    		                @Override
	    		                public void run()
	    		                {
	    							p.getWorld().spawnParticle(Particle.EXPLOSION, le.getLocation(), 50, 1,1,1, 0.21);
	    							p.playSound(le.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 2);
	             					
	    							atk0(0.5, wsd.TimeBomb.get(p.getUniqueId())*0.5, p, le);
	             					
	    		                }
	                	 	 }, 60);
	    					Holding.holding(p, le, 60l);
	                    }
	                    else {
		            		Snowball sn = p.launchProjectile(Snowball.class);
		            		sn.setItem(new ItemStack(Material.CLOCK));
		            		sn.setVelocity(sn.getVelocity().multiply(0.68));
		            		sn.setMetadata("timebomb", new FixedMetadataValue(RMain.getInstance(), true));
		            		sn.setShooter(p);
		            		sn.setGlowing(true);
	                    }
						sdcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	if(oneonly.containsKey(p.getUniqueId())) {

                    	LivingEntity le = oneonly.get(p.getUniqueId());
    					p.getWorld().spawnParticle(Particle.ITEM, le.getLocation(), 50, 1,1,1, 0.21, new ItemStack(Material.CLOCK));
    					p.playSound(le.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1, 0);
    					p.playSound(le.getLocation(), Sound.ENTITY_TNT_PRIMED, 1, 2);
                    	for(int i = 0 ; i <12; i ++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run()
				                {
									p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 0.6f, 2f);
									p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 0.6f, 2f);
				                }
							}, i*5);
                    	}
    					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    		                @Override
    		                public void run()
    		                {
    							p.getWorld().spawnParticle(Particle.EXPLOSION, le.getLocation(), 50, 1,1,1, 0.21);
    							p.playSound(le.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 2);

    							atk0(0.5, wsd.TimeBomb.get(p.getUniqueId())*0.5, p, le);
             					
    		                }
                	 	 }, 60);
    					Holding.holding(p, le, 60l);
                    }
                    else {
	            		Snowball sn = p.launchProjectile(Snowball.class);
	            		sn.setItem(new ItemStack(Material.CLOCK));
	            		sn.setVelocity(sn.getVelocity().multiply(0.68));
	            		sn.setMetadata("timebomb", new FixedMetadataValue(RMain.getInstance(), true));
	            		sn.setShooter(p);
	            		sn.setGlowing(true);
                    }
	                sdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
		}
	}


	
	public void TimeBomb(ProjectileHitEvent ev)
	{
		if(ev.getEntity().hasMetadata("timebomb")) {
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
        		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake")) && !(e.hasMetadata("portal")))
				{
					LivingEntity le = (LivingEntity)e;
					p.getWorld().spawnParticle(Particle.ITEM, le.getLocation(), 50, 1,1,1, 0.21, new ItemStack(Material.CLOCK));
					p.playSound(le.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1, 0);
					p.playSound(le.getLocation(), Sound.ENTITY_TNT_PRIMED, 1, 2);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run()
		                {
							p.getWorld().spawnParticle(Particle.EXPLOSION, le.getLocation(), 50, 1,1,1, 0.21);
							p.playSound(le.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 2);

							atk0(0.5, wsd.TimeBomb.get(p.getUniqueId())*0.5, p, le);
         					
		                }
            	 	 }, 60);
					Holding.holding(p, le, 60l);
				}
			}
		}
	}


	
	public void CactusTrap(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec = 6*(1-p.getAttribute(Attribute.LUCK).getValue()/1024);

		if(playerclass.get(p.getUniqueId()) == 25) {
		if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL"))
			{

				if(gdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (gdcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use CactusTrap").create());
	                }
	                else // if timer is done
	                {
	                    gdcooldown.remove(p.getName()); // removing player from HashMap
	                    p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_THROW, 1.0f, 2f);
						p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 1.0f, 0f);
						p.playSound(p.getLocation(), Sound.BLOCK_GRAVEL_PLACE, 1.0f, 2f);
						if(cactus.containsKey(p.getUniqueId())) {
							cactus.get(p.getUniqueId()).remove();
						}
						if(cactust.containsKey(p.getUniqueId())) {
							Bukkit.getServer().getScheduler().cancelTask(cactust.get(p.getUniqueId()));
						}
	                    if(oneonly.containsKey(p.getUniqueId())) {
	                    	LivingEntity le = oneonly.get(p.getUniqueId());
	                    	for(int i = 0 ; i <10; i ++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run()
					                {
										p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 0.1f, 2f);
					                	le.getWorld().spawnParticle(Particle.BLOCK, le.getLocation(), 10,Material.CHAIN.createBlockData());
					                	le.getWorld().spawnParticle(Particle.BLOCK, le.getLocation(), 100,Material.CACTUS.createBlockData());
		    							atk0(0.35, wsd.CactusTrap.get(p.getUniqueId())*0.3, p, le);
						            	Holding.holding(p, le, 50l);
										
					                }
								}, i*2);
	                    	}
	                    }
	                    else {
							Item hook = p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.CACTUS));
							hook.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(1));
							hook.setPickupDelay(999999);
							hook.setInvulnerable(true);
							hook.setOwner(p.getUniqueId());
							hook.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							cactus.put(p.getUniqueId(), hook);
							int task1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run()
				                {
									p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 0.1f, 2f);
				                	hook.getWorld().spawnParticle(Particle.BLOCK, hook.getLocation(), 1,Material.CHAIN.createBlockData());
				                	for(Entity e: hook.getNearbyEntities(1.2, 1.2, 1.2)) {

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
			                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake")) && !(e.hasMetadata("portal")))
										{
											LivingEntity le = (LivingEntity)e;
											cactusl.put(p.getUniqueId(), le);
			    							atk0(0.6, wsd.CactusTrap.get(p.getUniqueId())*0.6, p, le);
							            	Holding.holding(p, le, 50l);
											
											break;
										}
				                	}
				                }
							}, 0, 1);
							cactust.put(p.getUniqueId(),task1);
	                    }

						gdcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_THROW, 1.0f, 2f);
					p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 1.0f, 0f);
					p.playSound(p.getLocation(), Sound.BLOCK_GRAVEL_PLACE, 1.0f, 2f);
					if(cactus.containsKey(p.getUniqueId())) {
						cactus.get(p.getUniqueId()).remove();
					}
					if(cactust.containsKey(p.getUniqueId())) {
						Bukkit.getServer().getScheduler().cancelTask(cactust.get(p.getUniqueId()));
					}
                    if(oneonly.containsKey(p.getUniqueId())) {
                    	LivingEntity le = oneonly.get(p.getUniqueId());
                    	for(int i = 0 ; i <10; i ++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run()
				                {
									p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 0.1f, 2f);
				                	le.getWorld().spawnParticle(Particle.BLOCK, le.getLocation(), 10,Material.CHAIN.createBlockData());
				                	le.getWorld().spawnParticle(Particle.BLOCK, le.getLocation(), 100,Material.CACTUS.createBlockData());
	    							atk0(0.35, wsd.CactusTrap.get(p.getUniqueId())*0.3, p, le);
					            	Holding.holding(p, le, 50l);
									
				                }
							}, i*2);
                    	}
                    }
                    else {
						Item hook = p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.CACTUS));
						hook.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(1));
						hook.setPickupDelay(999999);
						hook.setInvulnerable(true);
						hook.setOwner(p.getUniqueId());
						hook.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						cactus.put(p.getUniqueId(), hook);
						int task1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run()
			                {
								p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 0.1f, 2f);
			                	hook.getWorld().spawnParticle(Particle.BLOCK, hook.getLocation(), 1,Material.CHAIN.createBlockData());
			                	for(Entity e: hook.getNearbyEntities(1.2, 1.2, 1.2)) {

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
		                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake")) && !(e.hasMetadata("portal")))
									{
										LivingEntity le = (LivingEntity)e;
										cactusl.put(p.getUniqueId(), le);
		    							atk0(0.6, wsd.CactusTrap.get(p.getUniqueId())*0.6, p, le);
						            	Holding.holding(p, le, 50l);
										
										break;
									}
			                	}
			                }
						}, 0, 1);
						cactust.put(p.getUniqueId(),task1);
                    }

					gdcooldown.put(p.getName(), System.currentTimeMillis());
				}

			} // adding players name + current system time in miliseconds

		}}
	}


	
	public void CactusTrap(EntityDamageByEntityEvent d)
	{

		if(d.getEntity() instanceof LivingEntity && d.getDamager() instanceof Player)
		{
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
				if(cactusl.containsKey(p.getUniqueId()) && cactusl.getOrDefault(p.getUniqueId(), p)==le) {
					cactusl.remove(p.getUniqueId());
					cactus.get(p.getUniqueId()).remove();
	            	Bukkit.getServer().getScheduler().cancelTask(cactust.get(p.getUniqueId()));
				}
		}
	}

	
	public void ChainHook(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		double sec = 6*(1-p.getAttribute(Attribute.LUCK).getValue()/1024);





		if(playerclass.get(p.getUniqueId()) == 25) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL")) && !p.isSneaking() && wsd.ChainHook.get(p.getUniqueId())>=1)
			{
				ev.setCancelled(true);
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (cdcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use ChainHook").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap
		                    p.setCooldown(Material.CHAIN, 5);
							p.playSound(p.getLocation(), Sound.ENTITY_FISHING_BOBBER_THROW, 1.0f, 0f);
							p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 1.0f, 0f);
							p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 1.0f, 2f);
							p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1.0f, 2f);
		                    if(oneonly.containsKey(p.getUniqueId())) {
		                    	LivingEntity le = oneonly.get(p.getUniqueId());
		                    	
    							atk0(1.0, 0d, p, le);
		                    	Holding.superholding(p, le, 30l);
		                    	
		                    	le.teleport(p);
		                    	le.getWorld().spawnParticle(Particle.BLOCK, le.getLocation(), 100,1,1,1,Material.CHAIN.createBlockData());
		                    	
		                    	
		                    }
		                    else {
								Item hook = p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.CHAIN));
								hook.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(2.3));
								hook.setGlowing(true);
								hook.setPickupDelay(9999);
								hook.setInvulnerable(true);
								hook.setOwner(p.getUniqueId());
								hook.setGravity(false);
								hook.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
								hooki.put(p.getUniqueId(), hook);
								int task1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run()
					                {
										p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 1.0f, 2f);
					                	hook.getWorld().spawnParticle(Particle.BLOCK, hook.getLocation(), 2,Material.CHAIN.createBlockData());
					                	for(Entity e: hook.getNearbyEntities(0.5, 0.5, 0.5)) {

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
				                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake")) && !(e.hasMetadata("portal")))
											{
												LivingEntity le = (LivingEntity)e;
				    							atk0(1.0, 0d, p, le);
						                    	
												le.teleport(p);
												hookl.put(p.getUniqueId(), le);
								            	Holding.superholding(p, le, 20l);
												break;
											}
					                	}
					                }
								}, 0, 1/5);
								int task2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run()
					                {
					                	Bukkit.getServer().getScheduler().cancelTask(task1);
					                	hook.remove();
					                }
			            	 	 }, 30);
								hookt1.put(p.getUniqueId(),task1);
								hookt2.put(p.getUniqueId(), task2);
		                    }
			                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	 p.setCooldown(Material.CHAIN, 5);
							p.playSound(p.getLocation(), Sound.ENTITY_FISHING_BOBBER_THROW, 1.0f, 0f);
							p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 1.0f, 0f);
							p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 1.0f, 2f);
							p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1.0f, 2f);
		                    if(oneonly.containsKey(p.getUniqueId())) {
		                    	LivingEntity le = oneonly.get(p.getUniqueId());
		                    	
 							atk0(1.0, 0d, p, le);
		                    	Holding.superholding(p, le, 30l);
		                    	
		                    	le.teleport(p);
		                    	le.getWorld().spawnParticle(Particle.BLOCK, le.getLocation(), 100,1,1,1,Material.CHAIN.createBlockData());
		                    	
		                    	
		                    }
		                    else {
								Item hook = p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.CHAIN));
								hook.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(2.3));
								hook.setGlowing(true);
								hook.setPickupDelay(9999);
								hook.setInvulnerable(true);
								hook.setOwner(p.getUniqueId());
								hook.setGravity(false);
								hook.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
								hooki.put(p.getUniqueId(), hook);
								int task1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run()
					                {
										p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 1.0f, 2f);
					                	hook.getWorld().spawnParticle(Particle.BLOCK, hook.getLocation(), 2,Material.CHAIN.createBlockData());
					                	for(Entity e: hook.getNearbyEntities(0.5, 0.5, 0.5)) {

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
				                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake")) && !(e.hasMetadata("portal")))
											{
												LivingEntity le = (LivingEntity)e;
				    							atk0(1.0, 0d, p, le);
						                    	
												le.teleport(p);
												hookl.put(p.getUniqueId(), le);
								            	Holding.superholding(p, le, 20l);
												break;
											}
					                	}
					                }
								}, 0, 1/5);
								int task2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run()
					                {
					                	Bukkit.getServer().getScheduler().cancelTask(task1);
					                	hook.remove();
					                }
			            	 	 }, 30);
								hookt1.put(p.getUniqueId(),task1);
								hookt2.put(p.getUniqueId(), task2);
		                    }
		                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
		}

	}

	
	public void ChainHook(EntityDamageByEntityEvent d)
	{

		if(d.getEntity() instanceof LivingEntity && d.getDamager() instanceof Player)
		{
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
				if(hookl.containsKey(p.getUniqueId()) && hookl.getOrDefault(p.getUniqueId(), p)==le) {
					hookl.remove(p.getUniqueId());
					hooki.get(p.getUniqueId()).remove();
	            	Bukkit.getServer().getScheduler().cancelTask(hookt1.get(p.getUniqueId()));
	            	Bukkit.getServer().getScheduler().cancelTask(hookt2.get(p.getUniqueId()));
				}
		}
	}


	
	public void ChainHookmove(PlayerSwapHandItemsEvent ev)
	{

		Player p = ev.getPlayer();


		if(!oneonly.containsKey(p.getUniqueId()) && p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL") && !(p.isSneaking()) && playerclass.get(p.getUniqueId()) == 25 && cdcooldown.containsKey(p.getName()))
		{
			ev.setCancelled(true);

			if(!hookl.containsKey(p.getUniqueId()) && hooki.get(p.getUniqueId()).isValid() && p.getCooldown(Material.CHAIN)<=0)
			{
                p.teleport(hooki.get(p.getUniqueId()));
				hooki.get(p.getUniqueId()).remove();
				p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 0.8f, 0f);
				p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 0.8f, 2f);
				p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 0.8f, 2f);
				p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 0.8f, 0f);
				p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_HIT, 0.8f, 2f);
				p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_HIT, 0.8f, 0f);
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 60, 1, false,false));
			}
		}
	}


	
	public void DustEyes(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec = 6*(1-p.getAttribute(Attribute.LUCK).getValue()/1024);




		if(playerclass.get(p.getUniqueId()) == 25) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL"))
			{


				if(frcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (frcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use DustEyes").create());
	                }
	                else // if timer is done
	                {
	                    frcooldown.remove(p.getName()); // removing player from HashMap

	                    if(oneonly.containsKey(p.getUniqueId())) {

							p.playSound(p.getLocation(), Sound.BLOCK_SAND_BREAK, 1.0f, 0f);
							p.playSound(p.getLocation(), Sound.BLOCK_SAND_FALL, 1.0f, 0f);
							p.playSound(p.getLocation(), Sound.BLOCK_SAND_PLACE, 1.0f, 0f);
							LivingEntity le = oneonly.get(p.getUniqueId());
							
							atk0(0.46, wsd.DustEyes.get(p.getUniqueId())*0.436, p, le);
							
 							Holding.holding(p, le, 35l);
 							le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60,1,false,false));

							
	             			p.getWorld().spawnParticle(Particle.ASH ,le.getLocation(), 300, 0.2,0.2,0.2,0);
	             			p.getWorld().spawnParticle(Particle.SMOKE ,le.getLocation(), 300, 0.2,0.2,0.2,0);
	             			p.getWorld().spawnParticle(Particle.DUST ,le.getLocation(), 300, 0.2,0.2,0.2,0, new DustOptions(Color.GRAY, 2f));
	                    }
	                    else {
							p.playSound(p.getLocation(), Sound.BLOCK_SAND_BREAK, 1.0f, 0f);
							p.playSound(p.getLocation(), Sound.BLOCK_SAND_FALL, 1.0f, 0f);
							p.playSound(p.getLocation(), Sound.BLOCK_SAND_PLACE, 1.0f, 0f);
		                    ArrayList<Location> line = new ArrayList<Location>();
		                    for(double d = 0.1; d <= 7; d += 0.1) {
			                    Location pl = p.getEyeLocation();
								pl.add(pl.getDirection().normalize().multiply(d));
								line.add(pl);
		                    }
		                    for(Location i : line) {

		             			p.getWorld().spawnParticle(Particle.ASH ,i, 3, 0.2,0.2,0.2,0);
		             			p.getWorld().spawnParticle(Particle.SMOKE ,i, 3, 0.2,0.2,0.2,0);
		             			p.getWorld().spawnParticle(Particle.DUST ,i, 3, 0.2,0.2,0.2,0, new DustOptions(Color.GRAY, 2f));
		             			if(i.getWorld().getNearbyEntities(i,1,1,1).stream().filter(en -> en instanceof LivingEntity && en!=p&& !(en.hasMetadata("fake")) && !(en.hasMetadata("portal"))).findFirst().isPresent()) {

			             			LivingEntity le = (LivingEntity) i.getWorld().getNearbyEntities(i,1,1,1).stream().filter(en -> en instanceof LivingEntity && en!=p&& !(en.hasMetadata("fake")) && !(en.hasMetadata("portal"))).findFirst().get();
	     							if (le instanceof Player)
	    							{
	    								Player p1 = (Player) le;
	    								if(Party.hasParty(p) && Party.hasParty(p1))	{
	    								if(Party.getParty(p).equals(Party.getParty(p1)))
	    									{
	    										continue;
	    									}
	    								}
	    							}
	    							atk0(0.46, wsd.DustEyes.get(p.getUniqueId())*0.436, p, le);
	    							
	     							Holding.holding(p, le, 20l);
	     							le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60,1,false,false));
	     							
	     							
	     							break;
		             			}

		                    }
	                    }
						frcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {

                    if(oneonly.containsKey(p.getUniqueId())) {

						p.playSound(p.getLocation(), Sound.BLOCK_SAND_BREAK, 1.0f, 0f);
						p.playSound(p.getLocation(), Sound.BLOCK_SAND_FALL, 1.0f, 0f);
						p.playSound(p.getLocation(), Sound.BLOCK_SAND_PLACE, 1.0f, 0f);
						LivingEntity le = oneonly.get(p.getUniqueId());
						
						atk0(0.46, wsd.DustEyes.get(p.getUniqueId())*0.436, p, le);
						
							Holding.holding(p, le, 35l);
							le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60,1,false,false));

						
             			p.getWorld().spawnParticle(Particle.ASH ,le.getLocation(), 300, 0.2,0.2,0.2,0);
             			p.getWorld().spawnParticle(Particle.SMOKE ,le.getLocation(), 300, 0.2,0.2,0.2,0);
             			p.getWorld().spawnParticle(Particle.DUST ,le.getLocation(), 300, 0.2,0.2,0.2,0, new DustOptions(Color.GRAY, 2f));
                    }
                    else {
						p.playSound(p.getLocation(), Sound.BLOCK_SAND_BREAK, 1.0f, 0f);
						p.playSound(p.getLocation(), Sound.BLOCK_SAND_FALL, 1.0f, 0f);
						p.playSound(p.getLocation(), Sound.BLOCK_SAND_PLACE, 1.0f, 0f);
	                    ArrayList<Location> line = new ArrayList<Location>();
	                    for(double d = 0.1; d <= 7; d += 0.1) {
		                    Location pl = p.getEyeLocation();
							pl.add(pl.getDirection().normalize().multiply(d));
							line.add(pl);
	                    }
	                    for(Location i : line) {

	             			p.getWorld().spawnParticle(Particle.ASH ,i, 3, 0.2,0.2,0.2,0);
	             			p.getWorld().spawnParticle(Particle.SMOKE ,i, 3, 0.2,0.2,0.2,0);
	             			p.getWorld().spawnParticle(Particle.DUST ,i, 3, 0.2,0.2,0.2,0, new DustOptions(Color.GRAY, 2f));
	             			if(i.getWorld().getNearbyEntities(i,1,1,1).stream().filter(en -> en instanceof LivingEntity && en!=p&& !(en.hasMetadata("fake")) && !(en.hasMetadata("portal"))).findFirst().isPresent()) {

		             			LivingEntity le = (LivingEntity) i.getWorld().getNearbyEntities(i,1,1,1).stream().filter(en -> en instanceof LivingEntity && en!=p&& !(en.hasMetadata("fake")) && !(en.hasMetadata("portal"))).findFirst().get();
     							if (le instanceof Player)
    							{
    								Player p1 = (Player) le;
    								if(Party.hasParty(p) && Party.hasParty(p1))	{
    								if(Party.getParty(p).equals(Party.getParty(p1)))
    									{
    										continue;
    									}
    								}
    							}
    							atk0(0.46, wsd.DustEyes.get(p.getUniqueId())*0.436, p, le);
    							
     							Holding.holding(p, le, 20l);
     							le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60,1,false,false));
     							
     							
     							break;
	             			}

	                    }
                    }
					frcooldown.put(p.getName(), System.currentTimeMillis());
				}

			} // adding players name + current system time in miliseconds

		}}
	}


	
	public void GlassBreak(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled())
		{
		Player p = (Player)d.getDamager();
		double sec = 5*(1-p.getAttribute(Attribute.LUCK).getValue()/1024);
		LivingEntity le = (LivingEntity)d.getEntity();
		final Location el =le.getLocation();




		if(playerclass.get(p.getUniqueId()) == 25) {

				if((p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL")) && (p.isSneaking()) && !(p.hasCooldown(Material.YELLOW_TERRACOTTA)))
				{
				if(stcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            double timer = (stcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
		            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use GlassBreak").create());
			        }
		            else // if timer is done
		            {
		                stcooldown.remove(p.getName()); // removing player from HashMap
		                Holding.holding(p, le, 40l);
     					le.teleport(el);
						p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 1f);
						p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 0f);
						p.getWorld().spawnParticle(Particle.BLOCK, el, 320, 1,1,1,Material.GLASS.createBlockData());
						if(oneonly.getOrDefault(p.getUniqueId(), p) == le) {
							for(int i = 0; i<10; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				             		@Override
						                	public void run()
							                {
				             			
				    							atk0(0.18, wsd.GlassBreak.get(p.getUniqueId())*0.168, p, le);
						    		        	Holding.holding(p, le, 40l);
						    		        	
				        						p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_HIT, 0.8f, 2f);
				        						le.getWorld().spawnParticle(Particle.BLOCK, le.getLocation(), 50, 0.51, 1, 0.51, Material.REDSTONE_BLOCK.createBlockData());
								            }
					                	   }, i*5);

							}
						}
						else {
							for(int i = 0; i<3; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				             		@Override
						                	public void run()
							                {

				             					atk0(0.18, wsd.GlassBreak.get(p.getUniqueId())*0.168, p, le);
				             					
				        						p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_HIT, 0.8f, 2f);
				        						le.getWorld().spawnParticle(Particle.BLOCK, le.getLocation(), 50, 0.51, 1, 0.51, Material.REDSTONE_BLOCK.createBlockData());
								            }
					                	   }, i*20);

							}
						}
			            stcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
		        	Holding.holding(p, le, 40l);
 					le.teleport(el);
					p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 1f);
					p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 0f);
					p.getWorld().spawnParticle(Particle.BLOCK, el, 320, 1,1,1,Material.GLASS.createBlockData());
					if(oneonly.getOrDefault(p.getUniqueId(), p) == le) {
						for(int i = 0; i<10; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run()
						                {
	             					atk0(0.18, wsd.GlassBreak.get(p.getUniqueId())*0.168, p, le);
			    		        	Holding.holding(p, le, 40l);
			             					
			        						p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_HIT, 0.8f, 2f);
			        						p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 1f);
							            }
				                	   }, i*2);

						}
					}
					else {
						for(int i = 0; i<3; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run()
						                {

	             					atk0(0.18, wsd.GlassBreak.get(p.getUniqueId())*0.168, p, le);
			             					
			        						p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_HIT, 0.8f, 2f);
			        						p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 1f);
							            }
				                	   }, i*20);

						}
					}
		            stcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
		}
		}
	}


	
	public void Crack(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =3*(1-p.getAttribute(Attribute.LUCK).getValue()/1024);





		if(playerclass.get(p.getUniqueId()) == 25) {
			if((ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK)&& ac != Action.RIGHT_CLICK_AIR&& ac != Action.RIGHT_CLICK_BLOCK)
			{
				if((p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL")) && !(p.isSneaking()) && !(p.isOnGround()))
					{
					ev.setCancelled(true);
					if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            double timer = (smcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Crack").create());
				        }
			            else // if timer is done
			            {
			                smcooldown.remove(p.getName()); // removing player from HashMapLocation dam = p.getLocation();

		                    if(oneonly.containsKey(p.getUniqueId())) {
		                    	LivingEntity le = oneonly.get(p.getUniqueId());
		    					p.getWorld().spawnParticle(Particle.ITEM, le.getLocation(), 50, 1,1,1, 0.21, p.getInventory().getItemInMainHand());
		    					p.playSound(le.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1, 0);
		    					p.playSound(le.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 0);
		    					p.playSound(le.getLocation(), Sound.BLOCK_NETHER_BRICKS_BREAK, 1, 0);
		    					p.playSound(le.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1, 2);
		    					p.playSound(le.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 2);
		    					p.playSound(le.getLocation(), Sound.BLOCK_NETHER_BRICKS_BREAK, 1, 2);
		    					
             					atk0(0.6, wsd.Crack.get(p.getUniqueId())*0.6, p, le);
		    					Holding.holding(p, le, 60l);
		                    }
		                    else {
			            		Snowball sn = p.launchProjectile(Snowball.class);
			            		sn.setItem(p.getInventory().getItemInMainHand());
			            		sn.setVelocity(sn.getVelocity().multiply(1.5));
			            		sn.setMetadata("brick", new FixedMetadataValue(RMain.getInstance(), true));
			            		sn.setShooter(p);
			            		sn.setGlowing(true);
		                    }
				            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {

	                    if(oneonly.containsKey(p.getUniqueId())) {
	                    	LivingEntity le = oneonly.get(p.getUniqueId());
	    					p.getWorld().spawnParticle(Particle.ITEM, le.getLocation(), 50, 1,1,1, 0.21, p.getInventory().getItemInMainHand());
	    					p.playSound(le.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1, 0);
	    					p.playSound(le.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 0);
	    					p.playSound(le.getLocation(), Sound.BLOCK_NETHER_BRICKS_BREAK, 1, 0);
	    					p.playSound(le.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1, 2);
	    					p.playSound(le.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 2);
	    					p.playSound(le.getLocation(), Sound.BLOCK_NETHER_BRICKS_BREAK, 1, 2);
         					atk0(0.6, wsd.Crack.get(p.getUniqueId())*0.6, p, le);
	    					Holding.holding(p, le, 60l);
	     					
	                    }
	                    else {
		            		Snowball sn = p.launchProjectile(Snowball.class);
		            		sn.setItem(p.getInventory().getItemInMainHand());
		            		sn.setVelocity(sn.getVelocity().multiply(1.5));
		            		sn.setMetadata("brick", new FixedMetadataValue(RMain.getInstance(), true));
		            		sn.setShooter(p);
		            		sn.setGlowing(true);
	                    }
			            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			        }
				}
			}

		}
	}


	
	public void Crack(ProjectileHitEvent ev)
	{
		if(ev.getEntity().hasMetadata("brick")) {
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
        		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake")) && !(e.hasMetadata("portal")))
				{
					LivingEntity le = (LivingEntity)e;
					p.getWorld().spawnParticle(Particle.ITEM, le.getLocation(), 50, 1,1,1, 0.21, p.getInventory().getItemInMainHand());
					p.playSound(le.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1, 0);
					p.playSound(le.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 0);
					p.playSound(le.getLocation(), Sound.BLOCK_NETHER_BRICKS_BREAK, 1, 0);
					p.playSound(le.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1, 2);
					p.playSound(le.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 2);
					p.playSound(le.getLocation(), Sound.BLOCK_NETHER_BRICKS_BREAK, 1, 2);
					
 					atk0(0.6, wsd.Crack.get(p.getUniqueId())*0.6, p, le);
					Holding.holding(p, le, 60l);
				}
			}
		}
	}



	
	public void ULT(PlayerDropItemEvent ev)
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();





			if(playerclass.get(p.getUniqueId()) == 25 && ((is.getType().name().contains("SHOVEL"))) && p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
				ev.setCancelled(true);
				if(oneonly.containsKey(p.getUniqueId())) {
					if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (sultcooldown.get(p.getName())/1000d + 80) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use UnfairMatch").create());
		                }
		                else // if timer is done
		                {
		                    sultcooldown.remove(p.getName()); // removing player from HashMap
		                    LivingEntity le = oneonly.get(p.getUniqueId());
		                    p.playSound(p.getLocation(), Sound.BLOCK_REDSTONE_TORCH_BURNOUT, 1.0f, 0.1f);
							p.getWorld().spawnParticle(Particle.HAPPY_VILLAGER, p.getLocation(), 30, 1, 1, 1);
							p.sendTitle(ChatColor.RED + "Match Start", null);
							le.sendMessage(ChatColor.RED + "Match Start");
							p.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, le.getLocation(), 30, 1, 1, 1);
		                    p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, p.getLevel()*2, 2, false,false));
		                    p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, p.getLevel()*2, 2, false,false));
		                    p.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, p.getLevel()*2, 2, false,false));
		                    p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, p.getLevel()*2, 2, false,false));
		                    p.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, p.getLevel()*2, 2, false,false));
		                    p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, p.getLevel()*2, 2, false,false));
		                    p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, p.getLevel()*2, 2, false,false));
		                    p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, p.getLevel()*2, 2, false,false));
		                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, p.getLevel()*2, 2, false,false));
		                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, p.getLevel()*2, 2, false,false));
		                    le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, p.getLevel()*2, 2, false,false));
		                    le.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, p.getLevel()*2, 2, false,false));
		                    le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, p.getLevel()*2, 2, false,false));
		                    le.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, p.getLevel()*2, 2, false,false));
		                    le.addPotionEffect(new PotionEffect(PotionEffectType.POISON, p.getLevel()*2, 2, false,false));
		                    le.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, p.getLevel()*2, 2, false,false));
		                    le.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, p.getLevel()*2, 2, false,false));
		                    le.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, p.getLevel()*2, 2, false,false));
		                    le.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, p.getLevel()*2, 2, false,false));
			                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds

		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
	                    LivingEntity le = oneonly.get(p.getUniqueId());
	                    p.playSound(p.getLocation(), Sound.BLOCK_REDSTONE_TORCH_BURNOUT, 1.0f, 0.1f);
						p.getWorld().spawnParticle(Particle.HAPPY_VILLAGER, p.getLocation(), 30, 1, 1, 1);
						p.sendTitle(ChatColor.RED + "Match Start", null);
						le.sendMessage(ChatColor.RED + "Match Start");
						p.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, le.getLocation(), 30, 1, 1, 1);
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, p.getLevel()*2, 2, false,false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, p.getLevel()*2, 2, false,false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, p.getLevel()*2, 2, false,false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, p.getLevel()*2, 2, false,false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, p.getLevel()*2, 2, false,false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, p.getLevel()*2, 2, false,false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, p.getLevel()*2, 2, false,false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, p.getLevel()*2, 2, false,false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, p.getLevel()*2, 2, false,false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, p.getLevel()*2, 2, false,false));
	                    le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, p.getLevel()*2, 2, false,false));
	                    le.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, p.getLevel()*2, 2, false,false));
	                    le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, p.getLevel()*2, 2, false,false));
	                    le.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, p.getLevel()*2, 2, false,false));
	                    le.addPotionEffect(new PotionEffect(PotionEffectType.POISON, p.getLevel()*2, 2, false,false));
	                    le.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, p.getLevel()*2, 2, false,false));
	                    le.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, p.getLevel()*2, 2, false,false));
	                    le.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, p.getLevel()*2, 2, false,false));
	                    le.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, p.getLevel()*2, 2, false,false));
		                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
				else {
                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You should Set target First").create());
				}
			}
    }

	
	public void Equipment(PlayerItemDamageEvent e)
	{
		Player p = e.getPlayer();




		if(playerclass.get(p.getUniqueId()) == 25)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL") )
			{
					e.setCancelled(true);
			}
		}

	}

	
	public void OneOnly(EntityDamageByEntityEvent d)
	{

		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && d.getDamage()>0)
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
			if(playerclass.get(p.getUniqueId()) == 25) {
				if(!tar.containsKey(p.getUniqueId())) {
					Item t = p.getWorld().dropItem(le.getEyeLocation(), new ItemStack(Material.CHAIN));
					t.setGlowing(true);
					t.setInvulnerable(true);
					t.setPickupDelay(999999);
					t.setCustomName(ChatColor.BOLD+ p.getName()+"'s Target");
					t.setCustomNameVisible(true);
					t.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
					t.setGravity(false);
					t.setVelocity(t.getVelocity().zero());
					tar.put(p.getUniqueId(), t);
					int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run()
		                {
		    				t.teleport(oneonly.get(p.getUniqueId()).getEyeLocation());
		                }
					}, 0, 1);
					tart.put(p.getUniqueId(), task);
				}

				oneonly.put(p.getUniqueId(), le);
				if(oneonly.containsKey(p.getUniqueId()))
				{
					d.setDamage(d.getDamage()*(2+wsd.OneOnly.get(p.getUniqueId())*0.16));
				}
			}
		}


		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof EnderDragon)
		{
			Projectile ar = (Projectile)d.getDamager();
			EnderDragon le = (EnderDragon)d.getEntity();

			if(ar.getShooter() instanceof Player) {
				Player p = (Player)ar.getShooter();
				if(playerclass.get(p.getUniqueId()) == 25) {
					if(!tar.containsKey(p.getUniqueId())) {
						Item t = p.getWorld().dropItem(le.getEyeLocation(), new ItemStack(Material.CHAIN));
						t.setGlowing(true);
						t.setInvulnerable(true);
						t.setPickupDelay(999999);
						t.setCustomName(ChatColor.BOLD+ p.getName()+"'s Target");
						t.setCustomNameVisible(true);
						t.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
						t.setGravity(false);
						t.setVelocity(t.getVelocity().zero());
						tar.put(p.getUniqueId(), t);
						int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run()
			                {
			    				t.teleport(oneonly.get(p.getUniqueId()).getEyeLocation());
			                }
						}, 0, 1);
						tart.put(p.getUniqueId(), task);
					}
					oneonly.put(p.getUniqueId(), le);
					if(oneonly.containsKey(p.getUniqueId()))
					{
						d.setDamage(d.getDamage()*(2+wsd.OneOnly.get(p.getUniqueId())*0.16));
					}
				}
			}
		}

		if(d.getDamager() instanceof LivingEntity && d.getEntity() instanceof Player && d.getDamage()>0)
		{
		Player p = (Player)d.getEntity();
		LivingEntity le = (LivingEntity)d.getDamager();
			if(playerclass.get(p.getUniqueId()) == 25) {
				if(oneonly.containsKey(p.getUniqueId()) && oneonly.get(p.getUniqueId())!=le)
				{
					d.setDamage(d.getDamage()*(1-wsd.OneOnly.get(p.getUniqueId())*0.05));
				}
			}
		}


		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof Player)
		{
			Projectile ar = (Projectile)d.getDamager();
			Player p = (Player)d.getEntity();
			if(ar.getShooter() instanceof LivingEntity) {
				LivingEntity le = (LivingEntity)ar.getShooter();
				if(playerclass.get(p.getUniqueId()) == 25) {
					if(oneonly.containsKey(p.getUniqueId()) && oneonly.get(p.getUniqueId())!=le)
					{
						d.setDamage(d.getDamage()*(1-wsd.OneOnly.get(p.getUniqueId())*0.05));
					}
				}
			}
		}
	}




	
	public void OneOnly(PlayerDeathEvent ev)
	{
		if(oneonly.containsValue(ev.getEntity())) {
			Set<UUID> re = new HashSet<>();
			oneonly.keySet().stream().filter(k -> (oneonly.get(k) == ev.getEntity())).forEach(k -> {
				re.add(k);
				Bukkit.getServer().getScheduler().cancelTask(tart.get(k));
				tar.get(k).remove();
				tar.remove(k);
				tart.remove(k);
				Player p = Bukkit.getPlayer(k);
				cdcooldown.remove(p.getName());
            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.RED+"[Target Killed]").create());
                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.1f);
				p.getWorld().spawnParticle(Particle.ASH, p.getLocation(), 30, 1, 1, 1);

			});
			re.forEach(k -> oneonly.remove(k));
		}
	}

	
	public void OneOnly(PlayerItemHeldEvent ev)
	{
		Player p = ev.getPlayer();
		if(playerclass.get(p.getUniqueId()) == 25 &&oneonly.containsKey(p.getUniqueId())){
			oneonly.remove(p.getUniqueId());
			Bukkit.getServer().getScheduler().cancelTask(tart.get(p.getUniqueId()));
			tar.get(p.getUniqueId()).remove();
			tar.remove(p.getUniqueId());
			tart.remove(p.getUniqueId());
        	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"[Untarget]").create());
		}
	}

}



