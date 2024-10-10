//package io.github.chw3021.classes.illusionist;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.UUID;
//
//import org.bukkit.Bukkit;
//import org.bukkit.Difficulty;
//import org.bukkit.GameMode;
//import org.bukkit.GameRule;
//import org.bukkit.Location;
//import org.bukkit.Material;
//import org.bukkit.Particle;
//import org.bukkit.Sound;
//import org.bukkit.World;
//import org.bukkit.World.Environment;
//import org.bukkit.WorldCreator;
//import org.bukkit.entity.ArmorStand;
//import org.bukkit.entity.Entity;
//import org.bukkit.entity.EntityType;
//import org.bukkit.entity.Item;
//import org.bukkit.entity.LivingEntity;
//import org.bukkit.entity.Player;
//import org.bukkit.event.entity.EntityDamageByEntityEvent;
//import org.bukkit.event.player.PlayerDropItemEvent;
//import org.bukkit.event.player.PlayerJoinEvent;
//import org.bukkit.event.player.PlayerQuitEvent;
//import org.bukkit.event.player.PlayerRespawnEvent;
//import org.bukkit.event.player.PlayerTeleportEvent;
//import org.bukkit.event.server.PluginDisableEvent;
//import org.bukkit.event.server.PluginEnableEvent;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.metadata.FixedMetadataValue;
//import org.bukkit.potion.PotionEffect;
//import org.bukkit.potion.PotionEffectType;
//
//import com.google.common.collect.HashMultimap;
//
//import io.github.chw3021.classes.ClassData;
//import io.github.chw3021.classes.Proficiency;
//import io.github.chw3021.commons.Holding;
//import io.github.chw3021.commons.Pak;
//import io.github.chw3021.monsters.worldgen.IllChunkGenerator;
//import io.github.chw3021.obtains.Obtained;
//import io.github.chw3021.party.Party;
//import io.github.chw3021.rmain.RMain;
//import net.md_5.bungee.api.ChatColor;
//import net.md_5.bungee.api.ChatMessageType;
//import net.md_5.bungee.api.chat.ComponentBuilder;
//
//public class Fakedimensiondummy extends Pak{
//
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 4491104812811379356L;
//	private HashMap<UUID, ArmorStand> dimdoll = new HashMap<UUID, ArmorStand>();
//	private HashMap<UUID, Location> dimfirstloc = new HashMap<UUID, Location>();
//	private HashMultimap<UUID, UUID> dimles = HashMultimap.create();
//	private HashMap<UUID, Double> dimdolldmg = new HashMap<UUID, Double>();
//	private HashMap<UUID, Integer> dimtask = new HashMap<UUID, Integer>();
//	private HashMap<UUID, Integer> dimcount = new HashMap<UUID, Integer>();
//	private HashMap<String, Double> sult2cooldown = new HashMap<String, Double>();
//	
//	public void ULT2(PlayerDropItemEvent ev)        
//	{
//		Player p = (Player)ev.getPlayer();
//		Item i = ev.getItemDrop();
//		ItemStack is = i.getItemStack();
//	    
//		
//			if(ClassData.pc.get(p.getUniqueId()) == 11 && (is.getType() == Material.BLAZE_ROD) && !p.isSneaking()&& p.isSprinting()&& Proficiency.getpro(p) >=2 && !p.hasCooldown(Material.STICK)) 
//			{
//				p.setCooldown(Material.WARPED_BUTTON, 2);
//				ev.setCancelled(true);
//				if(sult2cooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
//	            {
//	                double timer = (sult2cooldown.get(p.getName())/1000d + 80*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d)) - System.currentTimeMillis()/1000d; // geting time in seconds
//	                if(!(timer < 0)) // if timer is still more then 0 or 0
//	                {
//	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
//	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("���۵� ���� ���� ���ð��� " + String.valueOf(Math.round(timer*10)/10.0) + "�� ���ҽ��ϴ�").create());
//	                	}
//	                	else {
//		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to Warp Fake Dimension").create());
//	                	}
//	                }
//	                else // if timer is done
//	                {
//	                    sult2cooldown.remove(p.getName()); // removing player from HashMap
//	
//	                    final Location pfl = p.getLocation();
//	                    dimfirstloc.put(p.getUniqueId(), pfl);
//	                    
//		        		p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation(), 100,3,3,3,0);
//	                	p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 140, 1, false, false));
//	                    
//		            	for(Entity e : p.getNearbyEntities(7, 7, 7)) {
//							if (e instanceof Player) 
//							{
//								Player p1 = (Player) e;
//								if(Party.hasParty(p) && Party.hasParty(p1))	{
//								if(Party.getParty(p).equals(Party.getParty(p1)))
//									{
//										return;
//									}
//								}
//							}
//		                    if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
//							{ 
//								LivingEntity le = (LivingEntity)e;
//								le.setRemoveWhenFarAway(false);
//								dimles.put(p.getUniqueId(), le.getUniqueId());
//							}
//	                    }
//		                Holding.fly(p, 100l);
//		    	        
//		            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
//			                @Override
//			                public void run() 
//			                {
//								World fkw = Bukkit.getWorld("FakeDimension");
//								Location fkl = new Location(fkw, pfl.getX(), pfl.getY(), pfl.getZ());
//								p.teleport(fkl);
//				                Holding.fly(p, 100l);
//								p.playSound(p.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 0.5f, 2f);
//	
//								Holding.invur(p,130l);
//			                    ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
//			                    if(p.getLocale().equalsIgnoreCase("ko_kr")) {
//				                    as.setCustomName(ChatColor.BOLD + p.getName()+"�� ���۵����� ����");
//			                    }
//			                    else {
//				                    as.setCustomName(ChatColor.BOLD + p.getName()+"'s FakeDimension Doll");
//			                    }
//			                    as.setArms(true);
//			                    as.setBasePlate(false);
//			                    as.setGlowing(true);
//			                    as.setGravity(false);
//			                    as.setCanPickupItems(false);
//			                    as.setMetadata("dimdoll", new FixedMetadataValue(RMain.getInstance(), p.getName()));
//			    	            as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
//			                    as.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), p.getName()));
//			                    dimdoll.put(p.getUniqueId(), as);
//	
//								int task3 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
//				                    Integer time = 6;
//					                @Override
//					                public void run() 
//					                {
//					                    if(p.getLocale().equalsIgnoreCase("ko_kr")) {
//						                    p.sendTitle(ChatColor.GOLD+"�����ð�" , ChatColor.GOLD+""+time, 20, 20, 20) ;
//					                    }
//					                    else {
//						                    p.sendTitle(ChatColor.GOLD+"TimeLeft" , ChatColor.GOLD+""+time, 20, 20, 20) ;
//					                    }
//					                    time--;
//					                }
//					            }, 0,20); 
//								dimcount.put(p.getUniqueId(), task3);
//								
//								int task2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
//					                @Override
//					                public void run() 
//					                {
//					                	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 120, 1, false, false));
//						                p.setAllowFlight(true);
//										p.setFlying(false);
//					                    if(p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
//							                p.setAllowFlight(false);
//					                    }
//					                	Holding.ale(dimdoll.get(p.getUniqueId())).remove();
//						        		p.teleport(dimfirstloc.get(p.getUniqueId()));
//										p.playSound(p.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 0.5f, 2f);
//						        		p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation(), 50,3,3,3,0);
//					                	Bukkit.getScheduler().cancelTask(dimcount.get(p.getUniqueId()));
//					                	dimcount.remove(p.getUniqueId());
//					                	dimfirstloc.remove(p.getUniqueId());
//						        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
//							                @Override
//							                public void run() 
//							                {
//							                	dimtask.remove(p.getUniqueId());
//							                	dimles.get(p.getUniqueId()).forEach(leu -> {
//							                		if(Bukkit.getEntity(leu) != null) {
//							                			LivingEntity le = (LivingEntity) Bukkit.getEntity(leu);
//	
//														atk2(dimdolldmg.get(p.getUniqueId()),0.005, p, le);
//							                		}
//							                	});
//							                	
//							                	dimdoll.remove(p.getUniqueId());
//							                	dimles.removeAll(p.getUniqueId());
//							                	dimdolldmg.remove(p.getUniqueId());
//							                }
//							            }, 5); 
//					                }
//					            }, 120); 
//								dimtask.put(p.getUniqueId(), task2);
//			                }
//		            	}, 5); 
//		                sult2cooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
//		            
//	                }
//	            }
//	            else // if cooldown doesn't have players name in it
//	            {
//	                final Location pfl = p.getLocation();
//	                dimfirstloc.put(p.getUniqueId(), pfl);
//	                
//	        		p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation(), 100,3,3,3,0);
//	            	p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 140, 1, false, false));
//	                
//	            	for(Entity e : p.getNearbyEntities(7, 7, 7)) {
//						if (e instanceof Player) 
//						{
//							Player p1 = (Player) e;
//							if(Party.hasParty(p) && Party.hasParty(p1))	{
//							if(Party.getParty(p).equals(Party.getParty(p1)))
//								{
//									return;
//								}
//							}
//						}
//	                    if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
//						{ 
//							LivingEntity le = (LivingEntity)e;
//							le.setRemoveWhenFarAway(false);
//							dimles.put(p.getUniqueId(), le.getUniqueId());
//						}
//	                }
//	                Holding.fly(p, 100l);
//	
//	            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
//		                @Override
//		                public void run() 
//		                {
//							World fkw = Bukkit.getWorld("FakeDimension");
//							Location fkl = new Location(fkw, pfl.getX(), pfl.getY(), pfl.getZ());
//							p.teleport(fkl);
//			                Holding.fly(p, 100l);
//							p.playSound(p.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 0.5f, 2f);
//	
//							Holding.invur(p,130l);
//		                    ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
//		                    if(p.getLocale().equalsIgnoreCase("ko_kr")) {
//			                    as.setCustomName(ChatColor.BOLD + p.getName()+"�� ���۵����� ����");
//		                    }
//		                    else {
//			                    as.setCustomName(ChatColor.BOLD + p.getName()+"'s FakeDimension Doll");
//		                    }
//		                    as.setArms(true);
//		                    as.setBasePlate(false);
//		                    as.setGlowing(true);
//		                    as.setGravity(false);
//		                    as.setCanPickupItems(false);
//		                    as.setMetadata("dimdoll", new FixedMetadataValue(RMain.getInstance(), p.getName()));
//		    	            as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
//		                    as.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), p.getName()));
//		                    dimdoll.put(p.getUniqueId(), as);
//	
//							int task3 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
//			                    Integer time = 6;
//				                @Override
//				                public void run() 
//				                {
//				                    if(p.getLocale().equalsIgnoreCase("ko_kr")) {
//					                    p.sendTitle(ChatColor.GOLD+"�����ð�" , ChatColor.GOLD+""+time, 20, 20, 20) ;
//				                    }
//				                    else {
//					                    p.sendTitle(ChatColor.GOLD+"TimeLeft" , ChatColor.GOLD+""+time, 20, 20, 20) ;
//				                    }
//				                    time--;
//				                }
//				            }, 0,20); 
//							dimcount.put(p.getUniqueId(), task3);
//							
//							int task2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
//				                @Override
//				                public void run() 
//				                {
//				                	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 120, 1, false, false));
//					                p.setAllowFlight(true);
//									p.setFlying(false);
//				                    if(p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
//						                p.setAllowFlight(false);
//				                    }
//				                	Holding.ale(dimdoll.get(p.getUniqueId())).remove();
//					        		p.teleport(dimfirstloc.get(p.getUniqueId()));
//									p.playSound(p.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 0.5f, 2f);
//					        		p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation(), 50,3,3,3,0);
//				                	Bukkit.getScheduler().cancelTask(dimcount.get(p.getUniqueId()));
//				                	dimcount.remove(p.getUniqueId());
//				                	dimfirstloc.remove(p.getUniqueId());
//					        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
//						                @Override
//						                public void run() 
//						                {
//						                	dimtask.remove(p.getUniqueId());
//						                	dimles.get(p.getUniqueId()).forEach(leu -> {
//						                		if(Bukkit.getEntity(leu) != null) {
//						                			LivingEntity le = (LivingEntity) Bukkit.getEntity(leu);
//	
//													atk2(dimdolldmg.get(p.getUniqueId()),0.005, p, le);
//						                		}
//						                	});
//						                	
//						                	dimdoll.remove(p.getUniqueId());
//						                	dimles.removeAll(p.getUniqueId());
//						                	dimdolldmg.remove(p.getUniqueId());
//						                }
//						            }, 5); 
//				                }
//				            }, 120); 
//							dimtask.put(p.getUniqueId(), task2);
//		                }
//	            	}, 5); 
//	                sult2cooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
//	            }
//			}
//			
//	}
//
//	public void FakeDimension(PluginEnableEvent ev)        
//	{
//	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
//	        @Override
//	        public void run() {
//	        	if(Bukkit.getServer().getWorld("FakeDimension") == null) {
//					WorldCreator rwc = new WorldCreator("FakeDimension");
//					rwc.environment(Environment.THE_END);
//					rwc.generateStructures(false);
//	                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
//		                @Override
//		                public void run() {
//					        rwc.generator(new IllChunkGenerator()); 
//							World rw = rwc.createWorld();
//							rw.setMetadata("rpgraidworld", new FixedMetadataValue(RMain.getInstance(),true));
//	        				rw.setDifficulty(Difficulty.HARD);
//	        				rw.setTime(20000);
//	        				rw.setAutoSave(false);
//	        				rw.setKeepSpawnInMemory(false);
//	        				rw.setPVP(false);
//	        				rw.setSpawnFlags(false, false);
//	        				rw.setGameRule(GameRule.KEEP_INVENTORY, true);
//	        				rw.setGameRule(GameRule.DO_INSOMNIA, false);
//	        				rw.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
//	        				rw.setGameRule(GameRule.DISABLE_RAIDS, true);
//	        				rw.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
//	        				rw.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
//	        				rw.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
//	        				rw.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
//	        				rw.setGameRule(GameRule.DO_TILE_DROPS, false);
//	        				rw.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
//	        				rw.setGameRule(GameRule.DO_ENTITY_DROPS, false);
//	        				rw.setGameRule(GameRule.DO_MOB_LOOT, false);
//	        				rw.setGameRule(GameRule.SPAWN_RADIUS, 0);
//		                }
//		            }, 10); 
//	        	}
//	        }
//	    }, 20); 
//		List<World> worlds = Bukkit.getServer().getWorlds();
//		worlds.forEach(w -> w.getPlayers().forEach(b -> {
//				Player p = (Player) b;
//				if(!dimtask.containsKey(p.getUniqueId()) && p.getWorld().getName().contains("FakeDimension")) {
//					p.teleport(p.getBedSpawnLocation());
//				}
//		}));
//	}
//
//	public void FakeDimensionDoll(EntityDamageByEntityEvent d) 
//	{
//		if(d.getEntity() instanceof ArmorStand && d.getEntity().hasMetadata("dimdoll")) 
//		{
//			if(d.getDamager() instanceof Player  && dimdoll.containsKey(d.getDamager().getUniqueId())) {
//				Player p = (Player) d.getDamager();
//				ArmorStand as = (ArmorStand) d.getEntity();
//				if(as.getMetadata("dimdoll").get(0).asString().equals(p.getName())) {
//					dimdolldmg.computeIfPresent(p.getUniqueId(), (k,v) -> v + d.getDamage());
//					dimdolldmg.putIfAbsent(p.getUniqueId(), d.getDamage());
//					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(""+dimdolldmg.get(p.getUniqueId())).create());
//				}
//	        }
//			d.setCancelled(true);
//		}
//	}
//
//	public void FakeDimension(PlayerTeleportEvent e)
//	{
//		Player p = e.getPlayer();
//		if(!dimfirstloc.containsKey(p.getUniqueId()) && e.getTo().getWorld().getName().contains("FakeDimension") && !e.getFrom().getWorld().getName().contains("FakeDimension")) {
//			e.setCancelled(true);
//		}
//	}
//
//	public void FakeDimension(PlayerQuitEvent ev) 
//	{
//		Player p = ev.getPlayer();
//		if(dimtask.containsKey(p.getUniqueId())) {
//	    	Holding.ale(dimdoll.get(p.getUniqueId())).remove();
//			p.setAllowFlight(false);
//	        if(p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
//	            p.setAllowFlight(false);
//	        }
//	    	p.teleport(dimfirstloc.get(p.getUniqueId()));
//	    	Bukkit.getScheduler().cancelTask(dimcount.get(p.getUniqueId()));
//	    	dimcount.remove(p.getUniqueId());
//	    	Bukkit.getScheduler().cancelTask(dimtask.get(p.getUniqueId()));
//	    	
//	    	dimdoll.remove(p.getUniqueId());
//	    	dimles.removeAll(p.getUniqueId());
//	    	dimdolldmg.remove(p.getUniqueId());
//	    	dimfirstloc.remove(p.getUniqueId());
//	    	dimtask.remove(p.getUniqueId());
//		}
//		
//	}
//
//	public void FakeDimension(PlayerJoinEvent ev) 
//	{
//		Player p = ev.getPlayer();
//		if(!dimtask.containsKey(p.getUniqueId()) && p.getWorld().getName().contains("FakeDimension")) {
//			p.teleport(p.getBedSpawnLocation());
//		}
//	}
//
//	public void FakeDimension(PluginDisableEvent ev) 
//	{
//		List<World> worlds = Bukkit.getServer().getWorlds();
//		worlds.forEach(w -> w.getPlayers().forEach(b -> {
//				Player p = (Player) b;
//				if(dimtask.containsKey(p.getUniqueId())) {
//			    	Holding.ale(dimdoll.get(p.getUniqueId())).remove();
//					p.setAllowFlight(false);
//		            if(p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
//		                p.setAllowFlight(false);
//		            }
//			    	p.teleport(dimfirstloc.get(p.getUniqueId()));
//	            	Bukkit.getScheduler().cancelTask(dimcount.get(p.getUniqueId()));
//	            	dimcount.remove(p.getUniqueId());
//			    	Bukkit.getScheduler().cancelTask(dimtask.get(p.getUniqueId()));
//			    	
//			    	dimdoll.remove(p.getUniqueId());
//			    	dimles.removeAll(p.getUniqueId());
//			    	dimdolldmg.remove(p.getUniqueId());
//			    	dimfirstloc.remove(p.getUniqueId());
//			    	dimtask.remove(p.getUniqueId());
//				}
//		}));
//	}
//
//	public void FakeDimension(PlayerRespawnEvent ev) 
//	{
//		Player p = ev.getPlayer();
//		if(dimtask.containsKey(p.getUniqueId())) {
//	    	Holding.ale(dimdoll.get(p.getUniqueId())).remove();
//			p.setAllowFlight(false);
//	        if(p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
//	            p.setAllowFlight(false);
//	        }
//	    	p.teleport(dimfirstloc.get(p.getUniqueId()));
//	    	Bukkit.getScheduler().cancelTask(dimcount.get(p.getUniqueId()));
//	    	dimcount.remove(p.getUniqueId());
//	    	Bukkit.getScheduler().cancelTask(dimtask.get(p.getUniqueId()));
//	    	
//	    	dimdoll.remove(p.getUniqueId());
//	    	dimles.removeAll(p.getUniqueId());
//	    	dimdolldmg.remove(p.getUniqueId());
//	    	dimfirstloc.remove(p.getUniqueId());
//	    	dimtask.remove(p.getUniqueId());
//		}
//	}
//
//}
