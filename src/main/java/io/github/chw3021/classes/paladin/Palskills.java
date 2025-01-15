package io.github.chw3021.classes.paladin;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import com.google.common.collect.ArrayListMultimap;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.HorseJumpEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Palskills extends Pak {
	

	private HashMap<String, Long> rscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> prcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> jmcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> thcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> pncooldown = new HashMap<String, Long>();
	private HashMap<String, Long> eccooldown = new HashMap<String, Long>();
	private HashMap<String, Long> aultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> ault2cooldown = new HashMap<String, Long>();


	private HashMap<UUID, Integer> chargingt = new HashMap<UUID, Integer>();
	private ArrayListMultimap<UUID, LivingEntity> ars = ArrayListMultimap.create();
	
	private HashMap<UUID, Integer> shieldt = new HashMap<UUID, Integer>();


	private HashMap<UUID, Integer> illm = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> illmt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> doom = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> doomt = new HashMap<UUID, Integer>();
	

	private HashMap<UUID, Integer> aria = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> ariat = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> scry = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> scryt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> aspg = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> aspgt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> grff = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> grfft = new HashMap<UUID, Integer>();

	private HashMap<UUID, Horse> griffon = new HashMap<UUID, Horse>();

	private HashMap<UUID, Integer> hlsm = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> hlsmt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> pltl = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> pltlt = new HashMap<UUID, Integer>();
	
	
	private HashMap<UUID, Integer> judge = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> judget = new HashMap<UUID, Integer>();
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private PalSkillsData psd;

	

	private static final Palskills instance = new Palskills ();
	public static Palskills getInstance()
	{
		return instance;
	}

		
	public void er(PluginEnableEvent ev) 
	{
		PalSkillsData p = new PalSkillsData(PalSkillsData.loadData(path +"/plugins/RPGskills/PalSkillsData.data"));
		psd = p;
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
                if(judget.containsKey(p.getUniqueId())) {
                	Bukkit.getServer().getScheduler().cancelTask(judget.get(p.getUniqueId()));
                }
                judge.remove(p.getUniqueId());
			}
			
		}
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Palskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				PalSkillsData p = new PalSkillsData(PalSkillsData.loadData(path +"/plugins/RPGskills/PalSkillsData.data"));
				psd = p;
			}
			
		}
		if(e.getClickedInventory() != null&&e.getClickedInventory().getHolder() != null&& e.getClickedInventory().getHolder() instanceof Horse) {
			Horse h = (Horse) e.getClickedInventory().getHolder();
			if(h.hasMetadata("griffon")) {
				e.setCancelled(true);
			}
		}
	}

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		PalSkillsData p = new PalSkillsData(PalSkillsData.loadData(path +"/plugins/RPGskills/PalSkillsData.data"));
		psd = p;
		
	}
	
	final private Boolean weaponCheck(Player p) {
		return (p.getInventory().getItemInMainHand().getType().name().contains("_AXE") || p.getInventory().getItemInMainHand().getType() == Material.MACE) && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD");
	}
	
	public void Restraint(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && psd.Restraint.getOrDefault(p.getUniqueId(), 0)>=1) {
			double sec =8*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
			if(weaponCheck(p) && p.isBlocking() && p.isSneaking())
			{
				
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("결박")
						.ename("Restraint")
						.slot(1)
						.hm(rscooldown)
						.skillUse(() -> {

		                	if(illmt.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(illmt.get(p.getUniqueId()));
		                		illmt.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							illm.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						illm.remove(p.getUniqueId());
		    	                }
		    	            }, 45); 
		                	illmt.put(p.getUniqueId(), task);
		                	
		                	
		                    Location l = p.getLocation();
		                    
		                    final Location tl = gettargetblock(p,2).clone().add(0,2,0);
							
							p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 12, 1, 1, 1);
							p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 1.6f);
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.8f);
							for (Entity e : p.getWorld().getNearbyEntities(l, 4, 4, 4))
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
								if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
								{
									final LivingEntity le = (LivingEntity)e;
									le.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 20, 4, false, false));
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
						                	

											atks(0.7, psd.Restraint.get(p.getUniqueId())*0.69, p, le,14);
											p.playSound(p.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 1.0f, 2f);
											p.getWorld().spawnParticle(Particle.ASH, le.getLocation(), 12, 1, 1, 1);
											le.teleport(tl);
						                	
						                }
						            }, 10); 
								}
							}
						});
				bd.execute();
			}
		}
	}

	
	public void Illumination(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && illm.containsKey(p.getUniqueId())) {
			if(weaponCheck(p) && p.isBlocking() && p.isSneaking())
			{
				ev.setCancelled(true);

            	if(illmt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(illmt.get(p.getUniqueId()));
            		illmt.remove(p.getUniqueId());
            	}
				illm.remove(p.getUniqueId());

            	if(doomt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(doomt.get(p.getUniqueId()));
            		doomt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							doom.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						doom.remove(p.getUniqueId());
	                }
	            }, 45); 
            	doomt.put(p.getUniqueId(), task);
            	final Location l = p.getLocation().clone().add(p.getLocation().getDirection().clone().normalize().multiply(2.2));

            	HashSet<Location> cross = new HashSet<>();

            	for (int i = 0; i < 5; i++) {
            	    cross.add(l.clone().add(0, i, 0));
            	}
            	for (int i = -2; i < 2; i++) {
            	    cross.add(l.clone().add(i, 3, 0));
            	}


            	int totalHits = 8; // 총 히트 수
            	int delay = 7; // 각 히트 간의 지연 시간 (틱)

            	for (int i = 0; i < totalHits; i++) {
            	    final int hitIndex = i; // 람다식에서 사용할 수 있도록 인덱스를 final로 선언
            	    Bukkit.getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
            	        @Override
            	        public void run() {

                        	cross.forEach(cl -> {
                        	    p.getWorld().spawnParticle(Particle.FLASH, cl, 5, 0.25, 0.25, 0.25, 0);
                        	    p.getWorld().spawnParticle(Particle.COMPOSTER, cl, 100, 0.25, 0.25, 0.25, 0);
                        	    p.getWorld().spawnParticle(Particle.WHITE_ASH, cl, 100, 0.25, 0.25, 0.25, 0);
                        	    p.getWorld().spawnParticle(Particle.BLOCK, cl, 100, 0.25, 0.25, 0.25, 0, Material.CHISELED_QUARTZ_BLOCK.createBlockData());
                        	});
                        	p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 10, 4, 2, 4, 0);

                        	p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 0.8f, 0f);
                        	p.playSound(p.getLocation(), Sound.ENTITY_GLOW_SQUID_AMBIENT, 0.7f, 0.2f);
                        	
            	            for (Entity e : p.getWorld().getNearbyEntities(l, 4, 4, 4)) {
            	                if (e instanceof Player) {
            	                    Player p1 = (Player) e;
            	                    if (Party.hasParty(p) && Party.hasParty(p1)) {
            	                        if (Party.getParty(p).equals(Party.getParty(p1))) {
            	                            continue;
            	                        }
            	                    }
            	                }
            	                if (!(e == p) && e instanceof LivingEntity && !(e.hasMetadata("fake")) && !(e.hasMetadata("portal"))) {
            	                    final LivingEntity le = (LivingEntity) e;
            	                    le.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 20, 4, false, false));
            	                    atks(0.12, psd.Restraint.get(p.getUniqueId()) * 0.12, p, le, 9);
            	                    Holding.holding(p, le, 30L);
            	                }
            	            }
            	        }
            	    }, hitIndex * delay); // 각 히트마다 지연 시간 계산
            	}
				
			}
		}
			
	}

	
	public void Doom(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && doom.containsKey(p.getUniqueId())) {
			if(weaponCheck(p) && p.isBlocking() && p.isSneaking())
			{
				ev.setCancelled(true);

            	if(doomt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(doomt.get(p.getUniqueId()));
            		doomt.remove(p.getUniqueId());
            	}
				doom.remove(p.getUniqueId());

            	Location ttl = p.getLocation().clone().add(p.getLocation().getDirection().clone().normalize().multiply(3));
            	
            	HashSet<LivingEntity> les = new HashSet<>();

				for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 4, 4, 4))
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
					if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
					{
						final LivingEntity le = (LivingEntity)e;
						les.add(le);
						Holding.holding(p, le, 30l);
					}
				}
            	

				ArrayList<Location> air = new ArrayList<Location>();
				ArrayList<Location> sight = new ArrayList<Location>();
                AtomicInteger j = new AtomicInteger(0);
                AtomicInteger k = new AtomicInteger(0);
                for(double i=Math.PI/180; i > -Math.PI; i -= Math.PI/180) {
                	Location eye = p.getEyeLocation().clone();
                	eye.setDirection(eye.clone().getDirection().multiply(-1));
                	Vector eyev = eye.getDirection().clone().rotateAroundY(Math.PI/2);
                	Location a = eye.clone().setDirection(eye.getDirection().rotateAroundAxis(eyev, i).normalize());
                	Location b = eye.clone().add(eye.getDirection().rotateAroundAxis(eyev, i).normalize().multiply(3));
                	sight.add(a);
                	air.add(b);
                }
                air.forEach(l -> {
                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
    						p.getWorld().spawnParticle(Particle.BLOCK, l, 5, 0.3,0.3,0.3,0 ,Material.QUARTZ_PILLAR.createBlockData());
    						p.getWorld().spawnParticle(Particle.FLASH, l, 2, 0.3,0.3,0.3,0);
		                    p.playSound(p.getLocation(), Sound.ENTITY_GLOW_SQUID_AMBIENT, 0.01f, 2f);
		                    
		                    for(LivingEntity le: les) {
	    							le.teleport(l);
							}
		                }
		            }, j.incrementAndGet()/20);
                });
                sight.forEach(l -> {
                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
    						p.teleport(l);
		                }
		            }, k.incrementAndGet()/20);
                });
                

            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	                	
	    				HashSet<Location> cross = new HashSet<>();
	    				
	    				for(int i = -1; i<7; i++) {
	    					cross.add(ttl.clone().add(0, i, 0));
	    				}
	    				
	    				for(int i = -3; i<3; i++) {
	    					cross.add(ttl.clone().add(i, 4, 0));
	    					cross.add(ttl.clone().add(0, 4, i));
	    				}
	    				
	    				ttl.getWorld().strikeLightningEffect(ttl);

	                    
	                	cross.forEach(tl -> {
	        				p.getWorld().spawnParticle(Particle.ENCHANTED_HIT, tl, 300, 0.5, 0.5, 0.5);
	        				p.getWorld().spawnParticle(Particle.WAX_ON, tl, 300, 0.5, 0.5, 0.5,0);
	        				p.getWorld().spawnParticle(Particle.INSTANT_EFFECT, tl, 300, 0.5, 0.5, 0.5,0);
	        				p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, tl, 300, 1, 1, 1,0.5);
	                	});
	    				p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE,0.3f, 0.3f);
	    				
	    				les.forEach(le ->{
	    					atks(1.5, psd.Restraint.get(p.getUniqueId())*1.68, p, le,9);
	    					le.teleport(ttl);
	    					Holding.holding(p, le, 30l);
	    				});
	                }
	            }, j.incrementAndGet()/20);
            	

				
			}
		}
			
	}
	
	@SuppressWarnings("deprecation")
	public void Pray(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec = 10*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);;
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && psd.Pray.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(weaponCheck(p) && p.isSneaking() && !p.isBlocking())
			{
				
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("기도")
						.ename("Pray")
						.slot(5)
						.hm(prcooldown)
						.skillUse(() -> {

			            	p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_AMBIENT, 1.0f, 0.1f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 0.1f);
			        		p.getWorld().spawnParticle(Particle.ENCHANT, p.getLocation(), 100, 1, 1, 1);
			        		if(p.getHealth()+1 + p.getLevel() *0.5* (1+ psd.Pray.get(p.getUniqueId())*0.1) <= p.getMaxHealth())
			        		{
				        		p.setHealth(p.getHealth()+1 + p.getLevel() *0.5* (1+ psd.Pray.get(p.getUniqueId())*0.1));
			        		}
			        		else
			        		{
			        			p.setHealth(p.getMaxHealth());
			        		}
			        		if(Proficiency.getpro(p)>=1) {
			        			Holding.invur(p, psd.Pray.get(p.getUniqueId())*4l);
			        		}
			        		if(Proficiency.getpro(p)>=2) {
			        			p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 2, 2, false, false));
			        		}
							p.playEffect(org.bukkit.EntityEffect.VILLAGER_HEART);
			        		cleans(p);
							if(Party.hasParty(p)) {
								for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),6, 5, 6))
								{
									if (e instanceof Player) 
									{
										Player le = (Player) e;
										if(Party.isInSameParty(p, le))
										{
											if(le.getHealth()+1 + p.getLevel() *0.5* (1+ psd.Pray.get(p.getUniqueId())*0.1) <= le.getMaxHealth())
							        		{
								        		le.setHealth(le.getHealth()+1 + p.getLevel() *0.5* (1+ psd.Pray.get(p.getUniqueId())*0.1));
							        		}
							        		else
							        		{
							        			le.setHealth(le.getMaxHealth());
							        		}
							        		if(Proficiency.getpro(p)>=1) {
							        			Holding.invur(le, psd.Pray.get(p.getUniqueId())*4l);
							        		}
							        		if(Proficiency.getpro(p)>=2) {
							        			le.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 2, 2, false, false));
							        		}
											le.playEffect(org.bukkit.EntityEffect.VILLAGER_HEART);
							        		cleans(le);
										}
									}
								} 
							} 
						});
				bd.execute();
			}
		}
	}
	
	public void Encourage(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec = 16*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);;
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && psd.Encourge.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(weaponCheck(p) && !p.isSneaking() && !p.isBlocking())
			{
				ev.setCancelled(true);

				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("격려")
						.ename("Encourage")
						.slot(4)
						.hm(eccooldown)
						.skillUse(() -> {

		                	if(ariat.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(ariat.get(p.getUniqueId()));
		                		ariat.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							aria.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						aria.remove(p.getUniqueId());
		    	                }
		    	            }, 45); 
		                	ariat.put(p.getUniqueId(), task);
		                	
			                Location l = gettargetblock(p,3);
		                    p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1.0f, 0f);
			        		p.getWorld().spawnParticle(Particle.END_ROD, l, 400, 2, 2, 2);
			        		p.getWorld().spawnParticle(Particle.GLOW, l, 100, 4, 2, 4);
							p.setAbsorptionAmount(p.getAbsorptionAmount()+2 + psd.Encourge.get(p.getUniqueId())*0.5);
							for (Entity e : p.getWorld().getNearbyEntities(l ,4, 5, 4))
							{
								if (e instanceof Player) 
								{
									
									Player p1 = (Player) e;
									if(Party.hasParty(p) && Party.hasParty(p1))	{
									if(Party.getParty(p).equals(Party.getParty(p1)))
										{
										p1.setAbsorptionAmount(p1.getAbsorptionAmount()+2 +psd.Encourge.get(p.getUniqueId())*0.5);
										}
									}
									continue;
								}
								if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
								{
									LivingEntity le = (LivingEntity) e;
									atks(0.8, psd.Encourge.get(p.getUniqueId())*0.8, p, le,14);
								}
							}
						});
				bd.execute();
			}
		}
	}
	
	public void Aria(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && aria.containsKey(p.getUniqueId())) {
			if(weaponCheck(p) && !p.isSneaking() && !p.isBlocking())
			{
				ev.setCancelled(true);
                
            	if(ariat.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(ariat.get(p.getUniqueId()));
            		ariat.remove(p.getUniqueId());
            	}
				aria.remove(p.getUniqueId());
				


            	if(scryt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(scryt.get(p.getUniqueId()));
            		scryt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							scry.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 5); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						scry.remove(p.getUniqueId());
	                }
	            }, 50); 
            	scryt.put(p.getUniqueId(), task);

            	
            	final Location l = gettargetblock(p,3);
                p.playNote(l, Instrument.CHIME, Note.sharp(1, Tone.C));
                p.playNote(l, Instrument.CHIME, Note.sharp(1, Tone.G));
                p.playNote(l, Instrument.CHIME, Note.natural(1, Tone.E));;
        		p.getWorld().spawnParticle(Particle.ENCHANT, l, 400, 4, 4, 4);
        		p.getWorld().spawnParticle(Particle.WAX_ON, l, 400, 4, 4, 4);
        		p.getWorld().spawnParticle(Particle.HAPPY_VILLAGER, l, 100, 2, 2, 2);
				for (Entity e : p.getWorld().getNearbyEntities(l ,5, 5, 5))
				{
					if (e instanceof Player) 
					{
						if(e==p) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 2, false, false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 30, 2, false, false));
    						continue;
						}
						
						Player p1 = (Player) e;
						if(Party.hasParty(p) && Party.hasParty(p1))	{
						if(Party.getParty(p).equals(Party.getParty(p1)))
							{
								p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 2, false, false));
								p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 30, 2, false, false));
							}
						}
						continue;
					}
					if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
					{
						LivingEntity le = (LivingEntity) e;
						atks(0.5, psd.Encourge.get(p.getUniqueId())*0.5, p, le,14);
						Holding.holding(p, le, 20l);
					}
				}

        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	                    p.playNote(l, Instrument.CHIME, Note.natural(1, Tone.A));
	                    p.playNote(l, Instrument.CHIME, Note.natural(1, Tone.E));
	                    p.playNote(l, Instrument.CHIME, Note.sharp(1, Tone.C));
	            		p.getWorld().spawnParticle(Particle.ENCHANT, l, 400, 4, 0, 4);
	            		p.getWorld().spawnParticle(Particle.WAX_ON, l, 400, 4, 0, 4);
	            		p.getWorld().spawnParticle(Particle.NOTE, l, 100, 2, 2, 2);
	    				for (Entity e : p.getWorld().getNearbyEntities(l ,5, 5, 5))
	    				{
	    					if (e instanceof Player) 
	    					{

	    						if(e==p) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 2, false, false));
									p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 30, 2, false, false));
		    						continue;
	    						}
	    						Player p1 = (Player) e;
	    						if(Party.hasParty(p) && Party.hasParty(p1))	{
	    						if(Party.getParty(p).equals(Party.getParty(p1)))
	    							{
									p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 2, false, false));
									p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 30, 2, false, false));
	    							}
	    						}
	    						continue;
	    					}
	    					if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
	    					{
	    						LivingEntity le = (LivingEntity) e;
	    						atks(0.5, psd.Encourge.get(p.getUniqueId())*0.5, p, le,14);
	    					}
	    				}
	                	
	                }
	            }, 10); 
        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	                    p.playNote(l, Instrument.CHIME, Note.natural(1, Tone.E));
	                    p.playNote(l, Instrument.CHIME, Note.natural(1, Tone.B));
	                    p.playNote(l, Instrument.CHIME, Note.sharp(1, Tone.G));
	            		p.getWorld().spawnParticle(Particle.ENCHANT, l, 400, 4, 0, 4);
	            		p.getWorld().spawnParticle(Particle.WAX_ON, l, 400, 4, 0, 4);
	            		p.getWorld().spawnParticle(Particle.EFFECT, l, 100, 2, 2, 2);
	    				for (Entity e : p.getWorld().getNearbyEntities(l ,5, 5, 5))
	    				{
	    					if (e instanceof Player) 
	    					{

	    						if(e==p) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 2, false, false));
									p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 30, 2, false, false));
		    						continue;
	    						}
	    						Player p1 = (Player) e;
	    						if(Party.hasParty(p) && Party.hasParty(p1))	{
	    						if(Party.getParty(p).equals(Party.getParty(p1)))
	    							{
									p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 2, false, false));
									p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 30, 2, false, false));
	    							}
	    						}
	    						continue;
	    					}
	    					if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
	    					{
	    						LivingEntity le = (LivingEntity) e;
	    						atks(0.5, psd.Encourge.get(p.getUniqueId())*0.5, p, le,14);
	    					}
	    				}
	                	
	                }
	            }, 20); 
			}
		}
	}

	public void Sanctuary(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && scry.containsKey(p.getUniqueId())) {
			if(weaponCheck(p) && !p.isSneaking() && !p.isBlocking())
			{
				ev.setCancelled(true);

            	if(scryt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(scryt.get(p.getUniqueId()));
            		scryt.remove(p.getUniqueId());
            	}
				scry.remove(p.getUniqueId());

                
				final Location l = gettargetblock(p,3).clone().add(0, -0.786, 0);
				
                HashSet<ArmorStand> remove = new HashSet<>();
                for(double vi = -1; vi<4; vi+=0.5) {
                    ArmorStand v1 = p.getWorld().spawn(l.clone().add(0, vi, 0), ArmorStand.class);
                    v1.setCollidable(false);
                    v1.setGravity(false);
                    v1.setCanPickupItems(false);
                    v1.setSmall(true);
                    v1.setInvisible(true);
                    v1.getEquipment().setHelmet(new ItemStack(Material.QUARTZ_PILLAR));
                    v1.getEquipment().setChestplate(new ItemStack(Material.QUARTZ_PILLAR));
                    v1.getEquipment().setBoots(new ItemStack(Material.QUARTZ_PILLAR));
                    v1.getEquipment().setLeggings(new ItemStack(Material.QUARTZ_PILLAR));
                    v1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    v1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    v1.setBasePlate(false);
                    remove.add(v1);
                	
                }
                for(double vi = -1; vi<=1; vi+=0.5) {
                    ArmorStand v1 = p.getWorld().spawn(l.clone().add(vi, 2.5, 0), ArmorStand.class);
                    v1.setCollidable(false);
                    v1.setGravity(false);
                    v1.setCanPickupItems(false);
                    v1.setSmall(true);
                    v1.setInvisible(true);
                    v1.getEquipment().setHelmet(new ItemStack(Material.QUARTZ_PILLAR));
                    v1.getEquipment().setChestplate(new ItemStack(Material.QUARTZ_PILLAR));
                    v1.getEquipment().setBoots(new ItemStack(Material.QUARTZ_PILLAR));
                    v1.getEquipment().setLeggings(new ItemStack(Material.QUARTZ_PILLAR));
                    v1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    v1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    v1.setBasePlate(false);
                    remove.add(v1);
                	
                }

				for(int co = 0 ; co<10; co++) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                    p.playNote(l, Instrument.CHIME, Note.sharp(1, Tone.D));
		                    p.playNote(l, Instrument.CHIME, Note.natural(1, Tone.B));
		                    p.playNote(l, Instrument.CHIME, Note.sharp(1, Tone.F));
		            		p.getWorld().spawnParticle(Particle.DUST_PLUME, l, 400, 4, 0, 4);
		            		p.getWorld().spawnParticle(Particle.BLOCK_MARKER, l, 100, 4, 0, 4,Material.CHISELED_QUARTZ_BLOCK.createBlockData());
		    				for (Entity e : l.getWorld().getNearbyEntities(l ,4, 5, 4))
		    				{
		    					if (e instanceof Player) 
		    					{
		    						if(e==p) {
		    							p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 0, false, false));
			    						continue;
		    						}
		    						Player p1 = (Player) e;
		    						if(Party.hasParty(p) && Party.hasParty(p1))	{
		    						if(Party.getParty(p).equals(Party.getParty(p1)))
		    							{
		    							p1.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 0, false, false));
		    							}
		    						}
		    						continue;
		    					}
		    					if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
		    					{
		    						LivingEntity le = (LivingEntity) e;
		    						atks(0.55, psd.Encourge.get(p.getUniqueId())*0.68, p, le,14);
		    						Holding.holding(p, le, 11l);
		    					}
		    				}
						}
		            }, co*10); 
				}
            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            		@Override
                	public void run() 
	                {	
            			remove.forEach(r -> r.remove());
     				}
            	}, 110);
				
			}
		}
	}
	
	
	public void Judgement(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec = 10*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);;
        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && psd.Judgement.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(weaponCheck(p)  && p.isSneaking() && !p.isBlocking()) 
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK) && !p.hasCooldown(CAREFUL))
				{
					ev.setCancelled(true);
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("심판")
							.ename("Judgement")
							.slot(2)
							.hm(jmcooldown)
							.skillUse(() -> {

			                	if(aspgt.containsKey(p.getUniqueId())) {
			                		Bukkit.getScheduler().cancelTask(aspgt.get(p.getUniqueId()));
			                		aspgt.remove(p.getUniqueId());
			                	}

			    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			    	                @Override
			    	                public void run() {
			    						if(Proficiency.getpro(p)>=1) {
			    							aspg.putIfAbsent(p.getUniqueId(), 0);
			    						}
			    	                }
			    	            }, 3); 

			            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			    	                @Override
			    	                public void run() {
			    						aspg.remove(p.getUniqueId());
			    	                }
			    	            }, 45); 
			                	aspgt.put(p.getUniqueId(), task);
			                	
			                	

			                    Location l = gettargetblock(p,2);				
			                    p.getWorld().spawnParticle(Particle.ENCHANTED_HIT, l, 400, 4, 0, 4);
			                    final ArmorStand as = p.getWorld().spawn(l, ArmorStand.class);
			                    as.setArms(true);
			                    as.setLeftArmPose(new EulerAngle(0 , 0, Math.toRadians( -90 )));
			                    as.setRightArmPose(new EulerAngle(0 , 0, Math.toRadians( 90 )));
			                    as.setMarker(true);
			                    as.setGravity(false);
			                    as.setCanPickupItems(false);
			                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			                    as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 1.0f, 2.0f);

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					        			if(judge.containsKey(p.getUniqueId())) {
					        				if(judge.get(p.getUniqueId())<=psd.Judgement.get(p.getUniqueId())) {
							        			judge.put(p.getUniqueId(), psd.Judgement.get(p.getUniqueId()));
					        				}
					        			}
					        			judge.putIfAbsent(p.getUniqueId(), psd.Judgement.get(p.getUniqueId()));
					        			p.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
					        			p.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
					                	for (Entity a : l.getWorld().getNearbyEntities(l, 4, 4, 4))
										{
											if (a instanceof Player) 
											{
												
												Player le = (Player)a;
												if(Party.hasParty(p)&&Party.hasParty(le)) {
												if(Party.getParty(p).equals(Party.getParty(le))) {
									        			le.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
									        			le.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
									                    if(judget.containsKey(le.getUniqueId())) {
									                    	Bukkit.getServer().getScheduler().cancelTask(judget.get(le.getUniqueId()));
									                    }
									        			if(judge.containsKey(le.getUniqueId())) {
									        				if(judge.get(le.getUniqueId())<=psd.Judgement.get(p.getUniqueId())) {
											        			judge.put(le.getUniqueId(), psd.Judgement.get(p.getUniqueId()));
									        				}
									        			}
									        			judge.putIfAbsent(le.getUniqueId(), psd.Judgement.get(p.getUniqueId()));
														int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											                @Override
											                public void run() {
																judge.remove(le.getUniqueId());
											                }
											            }, 210); 
														judget.put(le.getUniqueId(), task);
													}
												}
												continue;
											}
											if ((!(a == p))&& a instanceof LivingEntity && a!=as&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
											{
												final LivingEntity le = (LivingEntity)a;
												atks(0.8, psd.Judgement.get(p.getUniqueId())*0.8, p, le);
														p.getWorld().strikeLightningEffect(l);
														p.getWorld().strikeLightningEffect(le.getLocation());
														 
											}
										}
										as.remove();
					                }
					            }, 20);
							});
					bd.execute();
					
				} 
			}
		}
	}

	
	public void Asperges(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3&& aspg.containsKey(p.getUniqueId())) {
			if(weaponCheck(p)  && p.isSneaking() && !p.isBlocking()) 
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK) && !p.hasCooldown(CAREFUL))
				{
					ev.setCancelled(true);

	            	if(aspgt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(aspgt.get(p.getUniqueId()));
	            		aspgt.remove(p.getUniqueId());
	            	}
					aspg.remove(p.getUniqueId());
					


	            	if(grfft.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(grfft.get(p.getUniqueId()));
	            		grfft.remove(p.getUniqueId());
	            	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=2) {
								grff.putIfAbsent(p.getUniqueId(), 0);
							}
		                }
		            }, 3); 

	        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							grff.remove(p.getUniqueId());
		                }
		            }, 45); 
	            	grfft.put(p.getUniqueId(), task);
	            	
	            	
	            	final Location l = gettargetblock(p,3);					
                    

	        		l.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 200,1,1,1);
					ItemStack jack = new ItemStack(Material.CHISELED_QUARTZ_BLOCK, 1);	
					Item jo = p.getWorld().dropItemNaturally(l, jack);
					jo.setGlowing(true);
					jo.setPickupDelay(5000);
					jo.setInvulnerable(true);
					jo.setThrower(p.getUniqueId());
					jo.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					jo.setMetadata("jo of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					jo.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                    p.playSound(p.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1.0f, 2f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 1.0f, 2f);
		                    p.playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 2f);
			        		jo.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 200,1,1,1);
			        		jo.getWorld().spawnParticle(Particle.SPLASH, l, 200,1,1,1);
			        		jo.getWorld().spawnParticle(Particle.FLASH, l, 50,2,2,2);
			        		jo.getWorld().spawnParticle(Particle.BLOCK, l, 600,4,3,4,Material.CHISELED_QUARTZ_BLOCK.createBlockData());
		                	for (Entity e : jo.getWorld().getNearbyEntities(l, 4, 4, 4))
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
	                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
								{
									LivingEntity le = (LivingEntity)e;
									atks(1.1, psd.Judgement.get(p.getUniqueId())*1.2, p, le,9);
									Holding.holding(p, le, 10l);
								}
							}
		                	Stream<Entity> jos = p.getWorld().getEntities().stream().filter(i -> i.hasMetadata("jo of"+p.getName()));
		                	jos.forEach(i -> i.remove());
		                }
            	   }, 13); 
					
				}
			}
		}
	}

	
	public void Griffon(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && grff.containsKey(p.getUniqueId())) {
			if(weaponCheck(p)  && p.isSneaking() && !p.isBlocking()) 
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK) && !p.hasCooldown(CAREFUL))
				{
					ev.setCancelled(true);
					
	            	if(grfft.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(grfft.get(p.getUniqueId()));
	            		grfft.remove(p.getUniqueId());
	            	}
					grff.remove(p.getUniqueId());

					
	                if(griffon.containsKey(p.getUniqueId())) {
	                	Holding.ale(griffon.get(p.getUniqueId())).remove();
	                	griffon.remove(p.getUniqueId());
	                	
	                }
                    p.playSound(p.getLocation(), Sound.ENTITY_HORSE_AMBIENT, 1.0f, 1f);
	        		p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p.getLocation(), 200,1,1,1);
                    p.getWorld().spawn(p.getLocation(), Horse.class, h ->{
	                    h.setDomestication(h.getMaxDomestication());
						h.setTamed(true);
						h.setOwner(p);
	                    h.setCustomName(p.getName());
						h.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
						h.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), p.getName()));	
						h.setMetadata("griffon", new FixedMetadataValue(RMain.getInstance(), p.getName()));	
						h.setMetadata("portal", new FixedMetadataValue(RMain.getInstance(), p.getName()));	
						h.setLeashHolder(p);
						h.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 999999, 5, false, false));
						h.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 999999, 5, false, false));
						h.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 5, false, false));
						h.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 2, false, false));
						h.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 999999, 0, false, false));
						h.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 2, false, false));
						h.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 2, false, false));
						h.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 2, false, false));
						h.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 999999, 2, false, false));
						h.getAttribute(Attribute.SCALE).setBaseValue(1.5);
						h.getAttribute(Attribute.STEP_HEIGHT).setBaseValue(5);
						h.setInvulnerable(true);
						h.setCollidable(false);
						h.setColor(Color.WHITE);
						h.setStyle(Style.WHITE);
						h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
						h.getInventory().setArmor(new ItemStack(Material.DIAMOND_HORSE_ARMOR));
						h.setLootTable(null);
						griffon.put(p.getUniqueId(), h);
						
                    });
				}
			}
		}
	}

	
	public void Griffon(VehicleExitEvent ev) 
	{
		if(ev.getVehicle() instanceof Horse && ev.getExited() instanceof Player) {
			Player p = (Player) ev.getExited();
            if(griffon.containsKey(p.getUniqueId())) {
            	Holding.ale(griffon.get(p.getUniqueId())).remove();
            	griffon.remove(p.getUniqueId());
            	
            }
		}
	}

	public void Griffon(HorseJumpEvent ev) 
	{
            if(griffon.containsValue(ev.getEntity())) {
            	Horse h = (Horse) ev.getEntity();
            	Player p = (Player) h.getOwner();
            	h.getWorld().spawnParticle(Particle.WHITE_ASH, h.getLocation(), 100,2,2,2);
            	p.playSound(h.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.5f, 2);
            	p.playSound(h.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 0.5f, 2);
            	if(ev.getPower()>=0.7) {
                	h.setVelocity(BlockFace.UP.getDirection().normalize().multiply(2.5));
            	}
            	for (Entity e : h.getWorld().getNearbyEntities(h.getLocation(), 4, 4, 4))
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
            		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
					{
						LivingEntity le = (LivingEntity)e;
						atks(1.3, psd.Judgement.get(p.getUniqueId())*1.3*ev.getPower(), p, le,9);
		            	le.getWorld().spawnParticle(Particle.FLASH, le.getLocation(), 1);
					}
				}
            	
            }
	}
	
	final private void thrust(Player p){

    	if(hlsmt.containsKey(p.getUniqueId())) {
    		Bukkit.getScheduler().cancelTask(hlsmt.get(p.getUniqueId()));
    		hlsmt.remove(p.getUniqueId());
    	}

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
				if(Proficiency.getpro(p)>=1) {
					hlsm.putIfAbsent(p.getUniqueId(), 0);
				}
            }
        }, 3); 

		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
				hlsm.remove(p.getUniqueId());
            }
        }, 45); 
    	hlsmt.put(p.getUniqueId(), task);

    	p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,2,25,false,false));

    	if(griffon.containsKey(p.getUniqueId())) {
    		Horse h = griffon.get(p.getUniqueId());
        	h.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,2,25,false,false));
    		
    	}
    	
        int q =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
            	Holding.invur(p, 10l);
            	p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 0.2f, 0.5f);
            	p.setVelocity(p.getEyeLocation().getDirection().clone().normalize().multiply(0.98));
				p.getWorld().spawnParticle(Particle.END_ROD, p.getLocation().clone(), 50, 1, 1, 1);

		    	if(griffon.containsKey(p.getUniqueId())) {
		    		Horse h = griffon.get(p.getUniqueId());
	            	h.setVelocity(p.getEyeLocation().getDirection().clone().normalize().multiply(1.38));
		    		
		    	}
            	Location tl = p.getLocation().clone().add(p.getEyeLocation().getDirection().clone().normalize().multiply(0.98));
            	if(tl.getWorld().getNearbyEntities(tl, 1.2,1.2,1.2).stream().anyMatch(e -> (!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))) {

            		p.setVelocity(p.getVelocity().zero());
    		    	if(griffon.containsKey(p.getUniqueId())) {
    		    		Horse h = griffon.get(p.getUniqueId());
    		    		h.setVelocity(h.getVelocity().zero());
    		    	}
					p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 0.6f, 0.6f);
					p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 0.7f, 0.1f);
					p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 0.1f, 0.53f);
					p.getWorld().spawnParticle(Particle.END_ROD, tl, 50, 0, 0, 0);
					p.getWorld().spawnParticle(Particle.WHITE_ASH, tl, 300, 3, 0, 3);
					
                	if(chargingt.containsKey(p.getUniqueId())) {
                		Bukkit.getScheduler().cancelTask(chargingt.get(p.getUniqueId()));
                		chargingt.remove(p.getUniqueId());
                	}
                	
                    for (Entity e : tl.getWorld().getNearbyEntities(tl, 2.5, 2.5, 2.5))
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
                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
    					{
                			LivingEntity le = (LivingEntity)e;
							atks(0.5, psd.Thrust.get(p.getUniqueId())*0.485,p, le,14);
							le.teleport(tl);
		                	Holding.holding(p, le, 20l);
                			
    					}
    				}
            	}
            }
    	 }, 0,1);
        chargingt.put(p.getUniqueId(), q);
        
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
            	if(chargingt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(chargingt.get(p.getUniqueId()));
            		chargingt.remove(p.getUniqueId());
            	}
            }
        }, 8); 
	}
	
	public void Thrust(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec =4*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && psd.Thrust.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(weaponCheck(p)&& !p.isSneaking() && p.isBlocking())
			{
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("진압")
						.ename("Thrust")
						.slot(0)
						.hm(thcooldown)
						.skillUse(() -> {
		                    thrust(p);
						});
				bd.execute();
			}
		}
	}

	public void HolySmash(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && hlsm.containsKey(p.getUniqueId())) {
			if(weaponCheck(p)&& !p.isSneaking() && p.isBlocking())
			{
				ev.setCancelled(true);
				
            	if(hlsmt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(hlsmt.get(p.getUniqueId()));
            		hlsmt.remove(p.getUniqueId());
            	}
				hlsm.remove(p.getUniqueId());
				


            	if(pltlt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(pltlt.get(p.getUniqueId()));
            		pltlt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							pltl.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						pltl.remove(p.getUniqueId());
	                }
	            }, 45); 
            	pltlt.put(p.getUniqueId(), task);

            	final Location tl = p.getLocation().clone().add(p.getLocation().getDirection().clone().normalize().multiply(3.2));

            	ArrayList<Location> line = new ArrayList<Location>();
            	AtomicInteger j = new AtomicInteger();
            	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.6f, 0.5f);
            	p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 0.6f, 1.5f);

            	// 십자가 형태의 파티클을 생성합니다.
            	double length = 4; // 십자가의 길이
            	int particleCount = 20; // 각 방향에 생성할 파티클 수

            	// 수평 방향 파티클
            	for (int i = -particleCount; i <= particleCount; i++) {
            	    Location pl = p.getEyeLocation().clone();
            	    pl.add(pl.getDirection().rotateAroundY(0).normalize().multiply(length * i / particleCount)); // 수평 방향
            	    line.add(pl);
            	}

            	// 수직 방향 파티클
            	for (int i = -particleCount; i <= particleCount; i++) {
            	    Location pl = p.getEyeLocation().clone();
            	    pl.add(pl.getDirection().rotateAroundX(Math.PI / 2).normalize().multiply(length * i / particleCount)); // 수직 방향
            	    line.add(pl);
            	}

            	line.forEach(l -> {
            	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            	        @Override
            	        public void run() {
            	            p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 1, 1, 1, 1, 0);
            	            p.getWorld().spawnParticle(Particle.BLOCK_CRUMBLE, l, 10, 1, 1, 1, 0, Material.CHISELED_QUARTZ_BLOCK.createBlockData());
            	            p.getWorld().spawnParticle(Particle.WHITE_ASH, l, 10, 2, 2, 2, 0.1);
            	        }
            	    }, j.incrementAndGet() / 900);
            	});

        		p.getWorld().spawnParticle(Particle.GLOW, tl,200,2,2,2,0);

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
						for (Entity a : p.getWorld().getNearbyEntities(tl, 3.8, 2.65, 3.8))
						{
                    		if (a instanceof Player) 
							{
								Player p1 = (Player) a;
								if(Party.hasParty(p) && Party.hasParty(p1))	{
								if(Party.getParty(p).equals(Party.getParty(p1)))
									{
									continue;
									}
								}
							}
							if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
							{
								LivingEntity le = (LivingEntity)a;
								atks(0.75, psd.Thrust.get(p.getUniqueId())*0.485,p, le,14);
								le.teleport(tl);
							}
						}
	                    
	                }
				}, j.incrementAndGet()/900); 
            	
				
			}
		}
	}

	public void HolyPile(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 3&&pltl.containsKey(p.getUniqueId())) {
			if(weaponCheck(p)&& !p.isSneaking() && p.isBlocking())
			{
				ev.setCancelled(true);

            	if(pltlt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(pltlt.get(p.getUniqueId()));
            		pltlt.remove(p.getUniqueId());
            	}
				pltl.remove(p.getUniqueId());


            	final Location tl = p.getLocation().clone().add(p.getLocation().getDirection().clone().normalize().multiply(3.2));

                
                ItemStack head = new ItemStack(Material.QUARTZ_PILLAR);
                HashSet<ArmorStand> ash = new HashSet<>();

                // 중앙 위치를 기준으로 십자가 모양으로 배치
                for (double d = -3; d <= 1.5; d += 0.5) {
                    // 수직 방향 (Y축)
                    ArmorStand asVertical = (ArmorStand) p.getWorld().spawnEntity(tl.clone().add(0, d, 0), EntityType.ARMOR_STAND);
                    asVertical.setBasePlate(false);
                    asVertical.setGravity(true);
                    asVertical.setCollidable(false);
                    asVertical.setInvulnerable(true);
                    asVertical.setInvisible(true);
                    asVertical.getEquipment().setHelmet(head);
                    asVertical.setCanPickupItems(false);
                    asVertical.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    asVertical.setMetadata("rob" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    asVertical.getAttribute(Attribute.SCALE).setBaseValue(2);
                    ash.add(asVertical);
                }

                // 수평 방향 (X축)
                for (int i = -1; i <= 1; i++) {
                    ArmorStand asHorizontal = (ArmorStand) p.getWorld().spawnEntity(tl.clone().add(i, 0, 0), EntityType.ARMOR_STAND);
                    asHorizontal.setBasePlate(false);
                    asHorizontal.setGravity(true);
                    asHorizontal.setInvulnerable(true);
                    asHorizontal.setCollidable(false);
                    asHorizontal.setInvisible(true);
                    asHorizontal.setMarker(true);
                    asHorizontal.getEquipment().setHelmet(head);
                    asHorizontal.setCanPickupItems(false);
                    asHorizontal.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    asHorizontal.setMetadata("rob" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    asHorizontal.getAttribute(Attribute.SCALE).setBaseValue(2);
                    ash.add(asHorizontal);
                }


				for(int co = 0 ; co<4; co++) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                    ash.forEach(r -> r.setVelocity(r.getVelocity().add(new Vector(0,0.1,0))));
		                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 0.3f, 0.78f);
		                    p.playSound(p.getLocation(), Sound.BLOCK_COPPER_BREAK, 1.0f, 0.3f); 
		            		p.getWorld().spawnParticle(Particle.BLOCK, tl,100,1,1,1, Material.END_ROD.createBlockData());	   
		            		p.getWorld().spawnParticle(Particle.ITEM, tl,200,2,1,2, new ItemStack(Material.END_ROD));	  
		    				for (Entity a : p.getWorld().getNearbyEntities(tl, 4, 4, 4))
		    				{
		                		if (a instanceof Player) 
		    					{
		    						Player p1 = (Player) a;
		    						if(Party.hasParty(p) && Party.hasParty(p1))	{
		    						if(Party.getParty(p).equals(Party.getParty(p1)))
		    							{
		    							continue;
		    							}
		    						}
		    					}
		    					if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
		    					{
		    						LivingEntity le = (LivingEntity)a;
		    						atks(0.4, psd.Thrust.get(p.getUniqueId())*0.455,p, le,14);
		    						le.teleport(tl);
		    						Holding.superholding(p, le, 20l);
		    					}
		    				}
						}
		            }, co*2); 
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                    ash.forEach(r -> r.remove());
	                }
				}, 40); 
				
			}
		}
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void Punish(EntityDamageByEntityEvent d) 
	{
		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&& !d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
		if((le.hasMetadata("fake")) || (le.hasMetadata("portal"))) {
			return;
		}
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
	    
		
		
			if(ClassData.pc.get(p.getUniqueId()) == 3 && psd.Punish.getOrDefault(p.getUniqueId(), 0)>=1 && (!griffon.containsKey(p.getUniqueId()) || (griffon.containsKey(p.getUniqueId()) && p.getVehicle() != griffon.get(p.getUniqueId())))) {				
				double sec =5*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				if(p.getAttackCooldown()==1) 
				{
					
					if(weaponCheck(p)&& !(p.hasCooldown(MELEE)) && !(p.isSneaking()) && !(p.isOnGround()) && !(p.hasCooldown(MELEE)))
						{
						SkillBuilder bd = new SkillBuilder()
								.player(p)
								.cooldown(sec)
								.kname("징벌")
								.ename("Punish")
								.slot(5)
								.hm(pncooldown)
								.skillUse(() -> {
									d.setDamage(d.getDamage()*1.5 + psd.Punish.get(p.getUniqueId())*1.5 + player_damage.get(p.getName())*0.5);
									if(Proficiency.getpro(p) >=1) {
										Holding.superholding(p, le, 20l);
									}
				                	p.getWorld().strikeLightningEffect(le.getLocation());
								});
						bd.execute();
						
						}
				}
			}
		}
	}
		

	
	public void Punish(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec = 5*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);;
        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && psd.Punish.getOrDefault(p.getUniqueId(), 0)>=1 && griffon.containsKey(p.getUniqueId())) {
			if(weaponCheck(p)  && !p.isSneaking() && !p.isBlocking()) 
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK) && !p.hasCooldown(CAREFUL))
				{
					ev.setCancelled(true);

					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("징벌")
							.ename("Punish")
							.slot(3)
							.hm(pncooldown)
							.skillUse(() -> {
				                Location l = p.getEyeLocation().add(p.getEyeLocation().clone().getDirection().normalize().multiply(1.5));
				                AtomicInteger j = new AtomicInteger();
				                
			    				for(int co = 0 ; co<3; co++) {
			    					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			    		                @Override
			    		                public void run() {
			    		                	l.add(l.clone().getDirection().multiply(j.incrementAndGet()));
			    		                	
			    		                	l.getWorld().strikeLightningEffect(l);
			    		                	l.getWorld().strikeLightningEffect(l.clone().add(l.clone().getDirection().rotateAroundY(Math.PI/2).normalize().multiply(2)));
			    		                	l.getWorld().strikeLightningEffect(l.clone().add(l.clone().getDirection().rotateAroundY(-Math.PI/2).normalize().multiply(2)));
			    		    				for (Entity e : l.getWorld().getNearbyEntities(l ,4, 5, 4))
			    		    				{
			    		    					if (e instanceof Player) 
			    		    					{
			    		    						
			    		    						Player p1 = (Player) e;
			    		    						if(Party.hasParty(p) && Party.hasParty(p1))	{
			    		    						if(Party.getParty(p).equals(Party.getParty(p1)))
			    		    							{
			    		    							p1.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 0, false, false));
			    		    							}
			    		    						}
			    		    						continue;
			    		    					}
			    		    					if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
			    		    					{
			    		    						LivingEntity le = (LivingEntity) e;
			    		    						atk1(0.8*(1+ psd.Punish.get(p.getUniqueId())*0.05),p, le,9);
													Holding.superholding(p, le, 20l);
			    		    					}
			    		    				}
			    						}
			    		            }, co*10); 
			    				}
							});
					bd.execute();
					
				} 
			}
		}
	}

	
	

	public void ULT(PlayerItemHeldEvent ev)
    {
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3&& ev.getNewSlot()==3  && weaponCheck(p)  && p.isSneaking() &&Proficiency.getpro(p)>=1)
			{
				ev.setCancelled(true);
				p.setCooldown(CAREFUL, 1);
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(56/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
						.kname("최후의 심판")
						.ename("FinalJudgement")
						.slot(6)
						.hm(aultcooldown)
						.skillUse(() -> {

		                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1.0f, 0.5f);
		                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0f, 0f);
							p.getWorld().spawnParticle(Particle.ITEM_SNOWBALL, p.getLocation(), 500, 10,1,10);
							p.getWorld().spawnParticle(Particle.TOTEM_OF_UNDYING, p.getLocation(), 500, 4,1,4);
							
							for(int co = 0 ; co<10; co++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                    for (double angle = 0; angle < 360; angle += 5) {
					                        double radians = Math.toRadians(angle);
					                        double x = 10 * Math.cos(radians);
					                        double z = 10 * Math.sin(radians);
					                        p.getWorld().spawnParticle(Particle.DUST_PLUME, p.getLocation().add(x, 0, z), 10, 0.2, 0.5, 0.2, 0);
					                    }
										for (Entity e : p.getNearbyEntities(10, 10, 10))
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
											if ((e!=p)&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
											{
												final LivingEntity le = (LivingEntity)e; 	
							                	Holding.superholding(p, le, 15l);
												atk1(1.5, p, le,9);
												le.getWorld().strikeLightningEffect(le.getLocation());
											}
										}
									}
					            }, co*10+10); 
							}
							
						});
				bd.execute();
	            
			}
    }
	

	
	final private void ULT2(Location il){
    	ArrayList<Location> line = new ArrayList<Location>();
        for(int d = 0; d <= 30; d += 1) {
            Location pl = il.clone();
			pl.add(il.getDirection().normalize().multiply(d));
			line.add(pl);
        }
        final World w = il.getWorld();
    	w.spawnParticle(Particle.FLASH, il, 30,2,2,2);
        line.forEach(l -> {
        	w.spawnParticle(Particle.FLASH, l, 30,1,1,1);
        	w.spawnParticle(Particle.FALLING_DUST, l, 200,0.1,0.1,0.1,0, Material.QUARTZ_BLOCK.createBlockData());
        	w.spawnParticle(Particle.WHITE_ASH, l, 200,1,1,1,0.05);
        });
	}
	

	
	final private void ult2(Player p){
		final Location tl = gettargetblock(p,6);
		
        p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1.0f, 0.5f);
        p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1.0f, 0f);
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 1.0f, 0f);
		Holding.invur(p, 120l);
		p.teleport(p.getLocation().add(0, 6, 0));
		
		HashSet<LivingEntity> les = new HashSet<>();
		
		
		for (Entity e : p.getNearbyEntities(10, 10, 10))
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
			if ((e!=p)&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
			{
				final LivingEntity le = (LivingEntity)e; 	
            	le.teleport(tl);
			}
		}
		
		HashSet<Location> cross = new HashSet<>();
		
		for(int i = 0; i<10; i++) {
			cross.add(tl.clone().add(0, i, 0));
		}
		for(int i = -4; i<4; i++) {
			cross.add(tl.clone().add(i, 6, 0));
		}
		for(int i = -6; i<6; i++) {
			cross.add(tl.clone().add(i, 1, 0));
			cross.add(tl.clone().add(0, 1, i));
		}
		
		for(int co = 0 ; co<30; co++) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	
                	if(p.getEyeLocation().clone().add(0,0.5,0).getBlock().isPassable()) {
                		Location pl = p.getLocation().clone().add(0, 0.25, 0);
                		pl.setDirection(tl.clone().toVector().subtract(pl.clone().toVector()));
                		p.teleport(pl);
                	}
					
                    p.playSound(p.getLocation(), Sound.BLOCK_SOUL_SOIL_BREAK, 0.5f, 0f);
                    p.playSound(p.getLocation(), Sound.BLOCK_SOUL_SAND_BREAK, 0.1f, 0f);
                    p.playSound(p.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 0.4f, 1.8f);
                	cross.forEach(cl -> {
	    				cl.getWorld().spawnParticle(Particle.CLOUD, cl, 100, 0.5,0.5,0.5,0);
	    				cl.getWorld().spawnParticle(Particle.DUST_PLUME, cl, 100, 0.5,0.5,0.5,0);
	    				cl.getWorld().spawnParticle(Particle.BLOCK, cl, 100, 0.5,0.5,0.5,0, Material.WHITE_GLAZED_TERRACOTTA.createBlockData());
	    				cl.getWorld().spawnParticle(Particle.BLOCK, cl, 100, 0.5,0.5,0.5,0, Material.CHISELED_QUARTZ_BLOCK.createBlockData());
                	});
					for (Entity e : tl.getWorld().getNearbyEntities(tl,10, 10, 10))
					{
						if(e==p) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 1, false, false));
    						continue;
						}
                		if (e instanceof Player) 
						{
							Player p1 = (Player) e;
							if(Party.hasParty(p) && Party.hasParty(p1))	{
							if(Party.getParty(p).equals(Party.getParty(p1)))
								{
									p1.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 1, false, false));
									continue;
								}
							}
						}
						if ((e!=p)&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
						{
							final LivingEntity le = (LivingEntity)e; 	
		                	Holding.superholding(p, le, 15l);
		                	les.add(le);
						}
					}
				}
            }, co*1); 
		}

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
            	Holding.holding(p, p, 40l);
				tl.getWorld().strikeLightningEffect(tl);
				tl.getWorld().spawnParticle(Particle.FLASH, tl, 300, 10,10,10);
				tl.getWorld().spawnParticle(Particle.WHITE_ASH, tl, 1000, 10,10,10);
				
                p.playSound(tl, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 0f);
			}
        }, 30); 
		
		
		for(int co = 0 ; co<10; co++) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
					
                	ULT2(p.getLocation().clone().add(p.getLocation().clone().getDirection().normalize().multiply(3)).add(0, -2, 0));
            		Holding.invur(p, 50l);
                    p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 2f);
                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.2f, 0f);
					for (LivingEntity le : les)
					{
	                	Holding.superholding(p, le, 15l);
						atk1(3d, p, le,9);
        				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
        				{
						@Override
        		                public void run() 
    	         				{	

		    						p.setCooldown(Material.GLISTERING_MELON_SLICE, 1);
		    						atk0(0d, le.getAttribute(Attribute.MAX_HEALTH).getValue()*(0.01), p, le);
    				            }
        	            }, 3);
					}
				}
            }, co*2+50); 
		}
	}
	

	
	public void ULT2(PlayerItemHeldEvent ev)
	{
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && ev.getNewSlot()==4  &&weaponCheck(p) && p.isSneaking() &&Proficiency.getpro(p)>=2)
			{
				ev.setCancelled(true);
				p.setCooldown(CAREFUL, 1);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(65*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
						.kname("참회")
						.ename("Penance")
						.slot(7)
						.hm(ault2cooldown)
						.skillUse(() -> {
		                    ult2(p);
						});
				bd.execute();
			}
    }
	

	
	@SuppressWarnings("deprecation")
	public void ThrowCancel(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
		
			if(ClassData.pc.get(p.getUniqueId()) == 3 && ((is.getType().name().contains("_AXE") || is.getType().name().contains("SHIELD"))) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
			}
	
    }
	
	public EulerAngle conv(float pit) {

	    float angle = (float)Math.toRadians(pit);
		if(pit<0) {
			angle = (float)Math.toRadians(360+pit);
		}
	    
	    return new EulerAngle(angle,0,0);
	}
	public float conav(float pit) {

	    float angle = (float)Math.toRadians(pit);
		if(pit<0) {
			angle = (float)Math.toRadians(360+pit);
		}
	    
	    return angle;
	}
	
	final private void giantShield(Player p) {



    	if(ars.containsKey(p.getUniqueId())) {
        	ars.get(p.getUniqueId()).forEach(ar -> Holding.ale(ar).remove());
        	ars.removeAll(p.getUniqueId());
    	}
    	if(shieldt.containsKey(p.getUniqueId())) {
    		Bukkit.getScheduler().cancelTask(shieldt.get(p.getUniqueId()));
    		shieldt.remove(p.getUniqueId());
    	}

    	final World pw = p.getWorld();
    	
    	p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, 0.2f, 0.5f);
    	

    	final Location pfl = p.getLocation().clone().add(0, 0.8, 0).clone().add(p.getEyeLocation().getDirection().clone().normalize().multiply(0.9));
    	final Location pel = p.getEyeLocation().clone();
    	final Location pelr = pfl.clone().setDirection(pfl.clone().getDirection().normalize().multiply(-1));
    	final Vector rr = p.getFacing().getDirection().clone().rotateAroundY(-Math.PI/2);
    	final Vector rot = pel.clone().getDirection().getCrossProduct(rr);
    	final Vector pl1 = pel.clone().getDirection().normalize().rotateAroundAxis(rot.clone(),-Math.PI/2);
    	final Vector pl2 = pel.clone().clone().getDirection().normalize().rotateAroundAxis(rot.clone(),Math.PI/2);
    	
    	ArrayList<Location> lhs = new ArrayList<>();
    	ArrayList<Location> crs = new ArrayList<>();
    	ArrayList<Location> rev = new ArrayList<>();
		for(double y=0.5; y<4;y+=0.5) {
			for(double x=0.5; x<4;x+=0.5) {
    			lhs.add(pfl.clone().add(pl1.clone().multiply(x)).add(rot.clone().multiply(y)));
    			lhs.add(pfl.clone().add(pl2.clone().multiply(x)).add(rot.clone().multiply(y)));
    			lhs.add(pfl.clone().add(pl1.clone().multiply(x)).add(rot.clone().multiply(-y)));
    			lhs.add(pfl.clone().add(pl2.clone().multiply(x)).add(rot.clone().multiply(-y)));
    			rev.add(pelr.clone().add(pl1.clone().multiply(x)).add(rot.clone().multiply(y)));
    			rev.add(pelr.clone().add(pl2.clone().multiply(x)).add(rot.clone().multiply(y)));
    			rev.add(pelr.clone().add(pl1.clone().multiply(x)).add(rot.clone().multiply(-y)));
    			rev.add(pelr.clone().add(pl2.clone().multiply(x)).add(rot.clone().multiply(-y)));
        	}
    	}
    	for(double x=0.5; x<4;x+=0.5) {
			crs.add(pfl.clone().add(pl1.clone().multiply(x)));
			crs.add(pfl.clone().add(pl2.clone().multiply(x)));
			crs.add(pfl.clone().add(rot.clone().multiply(-x)));
			crs.add(pfl.clone().add(rot.clone().multiply(x)));
			rev.add(pelr.clone().add(pl1.clone().multiply(x)));
			rev.add(pelr.clone().add(pl2.clone().multiply(x)));
			rev.add(pelr.clone().add(rot.clone().multiply(-x)));
			rev.add(pelr.clone().add(rot.clone().multiply(x)));
    	}
    	lhs.forEach(l ->{
    		pw.spawn(l, ArmorStand.class, ar ->{
    			ar.setGravity(false);
    			ar.setInvisible(true);
    			ar.setCollidable(false);
    			ar.setMarker(true);
    			ar.setBasePlate(false);
    			ar.setInvulnerable(true);
    			ar.setSmall(true);
    			ar.getEquipment().setHelmet(new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
				ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));	
				ar.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				ar.setMetadata("giantshield", new FixedMetadataValue(RMain.getInstance(), p.getName()));
				ar.setHeadPose(conv(pel.getPitch()));
				ars.put(p.getUniqueId(), ar);
    		});
    	});
    	crs.forEach(l ->{
    		pw.spawn(l, ArmorStand.class, ar ->{
    			ar.setGravity(false);
    			ar.setInvisible(true);
    			ar.setCollidable(false);
    			ar.setMarker(true);
    			ar.setBasePlate(false);
    			ar.setInvulnerable(true);
    			ar.setSmall(true);
    			ar.getEquipment().setHelmet(new ItemStack(Material.BLUE_STAINED_GLASS_PANE));
				ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));	
				ar.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				ar.setMetadata("giantshield", new FixedMetadataValue(RMain.getInstance(), p.getName()));
				ar.setHeadPose(conv(pel.getPitch()));
				ars.put(p.getUniqueId(), ar);
    		});
    	});
    	rev.forEach(l ->{
    		pw.spawn(l, ArmorStand.class, ar ->{
    			ar.setGravity(false);
    			ar.setInvisible(true);
    			ar.setCollidable(false);
    			ar.setMarker(true);
    			ar.setBasePlate(false);
    			ar.setInvulnerable(true);
    			ar.setSmall(true);
				ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));	
				ar.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				ar.setMetadata("giantshieldrev", new FixedMetadataValue(RMain.getInstance(), p.getName()));
				ar.setHeadPose(conv(pel.getPitch()));
				ars.put(p.getUniqueId(), ar);
    		});
    	});

		pw.spawn(pelr.clone(), ArmorStand.class, ar ->{
			ar.setGravity(false);
			ar.setInvisible(true);
			ar.setCollidable(false);
			ar.setMarker(true);
			ar.setBasePlate(false);
			ar.setInvulnerable(true);
			ar.setSmall(true);
			ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));	
			ar.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			ar.setMetadata("giantshieldrev", new FixedMetadataValue(RMain.getInstance(), p.getName()));
			ar.setMetadata("giantcenterr", new FixedMetadataValue(RMain.getInstance(), p.getName()));
			ar.setHeadPose(conv(pel.getPitch()));
			ars.put(p.getUniqueId(), ar);
		});
    	
		pw.spawn(pfl.clone(), ArmorStand.class, ar ->{
			ar.setGravity(false);
			ar.setInvisible(true);
			ar.setCollidable(false);
			ar.setMarker(true);
			ar.setBasePlate(false);
			ar.setInvulnerable(true);
			ar.setSmall(true);
			ar.getEquipment().setHelmet(new ItemStack(Material.GLASS_PANE));
			ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));	
			ar.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			ar.setMetadata("giantshield", new FixedMetadataValue(RMain.getInstance(), p.getName()));
			ar.setMetadata("giantcenter", new FixedMetadataValue(RMain.getInstance(), p.getName()));
			ar.setHeadPose(conv(pel.getPitch()));
			ars.put(p.getUniqueId(), ar);
		});
    	

		
        int q =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
            	final Location pfl = p.getLocation().clone().add(0, 0.8, 0).clone().add(p.getEyeLocation().getDirection().clone().normalize().multiply(1.1));
            	final Location pel = p.getEyeLocation().clone();
            	final Location pelr = pfl.clone().setDirection(pfl.clone().getDirection().normalize().multiply(-1));
            	final Vector rr = p.getFacing().getDirection().clone().rotateAroundY(-Math.PI/2);
            	final Vector rot = pel.clone().getDirection().getCrossProduct(rr);
            	final Vector pl1 = pel.clone().getDirection().normalize().rotateAroundAxis(rot.clone(),-Math.PI/2);
            	final Vector pl2 = pel.clone().clone().getDirection().normalize().rotateAroundAxis(rot.clone(),Math.PI/2);
            	
            	ArrayList<Location> lhs = new ArrayList<>();
            	ArrayList<Location> crs = new ArrayList<>();
            	ArrayList<Location> rev = new ArrayList<>();
        		for(double y=0.5; y<4;y+=0.5) {
        			for(double x=0.5; x<4;x+=0.5) {
            			lhs.add(pfl.clone().add(pl1.clone().multiply(x)).add(rot.clone().multiply(y)));
            			lhs.add(pfl.clone().add(pl2.clone().multiply(x)).add(rot.clone().multiply(y)));
            			lhs.add(pfl.clone().add(pl1.clone().multiply(x)).add(rot.clone().multiply(-y)));
            			lhs.add(pfl.clone().add(pl2.clone().multiply(x)).add(rot.clone().multiply(-y)));
            			rev.add(pelr.clone().add(pl1.clone().multiply(x)).add(rot.clone().multiply(y)));
            			rev.add(pelr.clone().add(pl2.clone().multiply(x)).add(rot.clone().multiply(y)));
            			rev.add(pelr.clone().add(pl1.clone().multiply(x)).add(rot.clone().multiply(-y)));
            			rev.add(pelr.clone().add(pl2.clone().multiply(x)).add(rot.clone().multiply(-y)));
                	}
            	}
            	for(double x=0.5; x<4;x+=0.5) {
        			crs.add(pfl.clone().add(pl1.clone().multiply(x)));
        			crs.add(pfl.clone().add(pl2.clone().multiply(x)));
        			crs.add(pfl.clone().add(rot.clone().multiply(-x)));
        			crs.add(pfl.clone().add(rot.clone().multiply(x)));
        			rev.add(pelr.clone().add(pl1.clone().multiply(x)));
        			rev.add(pelr.clone().add(pl2.clone().multiply(x)));
        			rev.add(pelr.clone().add(rot.clone().multiply(-x)));
        			rev.add(pelr.clone().add(rot.clone().multiply(x)));
            	}
            	AtomicInteger j  = new AtomicInteger();
            	
            	lhs.forEach(l ->{
            		ars.get(p.getUniqueId()).get(j.getAndIncrement()).teleport(l);
            	});
            	crs.forEach(l ->{
            		ars.get(p.getUniqueId()).get(j.getAndIncrement()).teleport(l);
            	});
            	rev.forEach(l ->{
            		ars.get(p.getUniqueId()).get(j.getAndIncrement()).teleport(l);
            	});

        		ars.get(p.getUniqueId()).get(j.getAndIncrement()).teleport(pelr);
        		ars.get(p.getUniqueId()).get(j.get()).teleport(pfl);
        		
        		ars.get(p.getUniqueId()).forEach(ar ->{
        			((ArmorStand) ar).setHeadPose(conv(pel.getPitch()));
    				
        			ar.getNearbyEntities(0.6, 0.6, 0.6).forEach(ne ->{
        				if(ne instanceof Projectile) {
        					Projectile pr = (Projectile) ne;
        					if(pr.getShooter() instanceof Player) {
        						Player st = (Player) pr.getShooter();
        						if(Party.isInSameParty(p, st)) {
        							return;
        						}
        					}
        					pr.remove();
        					p.playSound(pr.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 0.35f, 1.76f);
        					pw.spawnParticle(Particle.FLASH, pr.getLocation(), 1);
        					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
        			            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE +"[수호의 방벽]").create());
        					}
        					else {
        			            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE +"[Barrier of Tutelary]").create());
        					}
        				
        				}
        			});
        		});
            	

				if(!p.isBlocking()) {
					if(ars.containsKey(p.getUniqueId())) {
						ars.get(p.getUniqueId()).forEach(ar -> Holding.ale(ar).remove());
						ars.removeAll(p.getUniqueId());
					}

					if(shieldt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(shieldt.get(p.getUniqueId()));
						shieldt.remove(p.getUniqueId());
					}
				}
            }

       	 }, 0,1);
        shieldt.put(p.getUniqueId(), q);
    	
    	
	}
	
	final private boolean protectalbe(Entity tar, Player par, Player pal) {

		final World pw = par.getWorld();
		Boolean revtar = false;
		Boolean revpar = false;
		Boolean fronttar = false;
		Boolean frontpar = false;
		for(LivingEntity ar : ars.get(pal.getUniqueId())) {
			final Location arl = ar.getEyeLocation().clone();
			if(pw.rayTraceEntities(arl, arl.getDirection(), 60, he -> !he.hasMetadata("fake")&&!he.hasMetadata("portal")&&!he.hasMetadata("din")) !=null) {
				Entity he = pw.rayTraceEntities(arl, arl.getDirection(), 60, hae -> !hae.hasMetadata("fake")).getHitEntity();
				
				if(ar.hasMetadata("giantshieldrev")) {
					if(he == par) {
						revpar = true;
					}
					if(he == tar) {
						revtar = true;	
					}
				}
				else if(ar.hasMetadata("giantshield")) {
					if(he == tar) {
						fronttar = true;	
					}
					if(he == par) {
						frontpar = true;
					}
				}
			}
		}
		

		Boolean returnbool = (revtar&&frontpar)||(revpar&&fronttar);
		if(returnbool) {
			pw.spawnParticle(Particle.FLASH, tar.getLocation(), 1);
			par.playSound(par.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 0.35f, 1.76f);
			if(par.getLocale().equalsIgnoreCase("ko_kr")) {
	            par.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE +"[수호의 방벽]").create());
			}
			else {
	            par.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE +"[Barrier of Tutelary]").create());
			}
		}
		return returnbool;
	}

	public void Protection(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3) {
			if(weaponCheck(p)) 
			{
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
			        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			            @Override
			            public void run() {
							giantShield(p);
			            }
			        }, 6); 
					
				}
			}
		}
	}
	public void Protection(EntityDamageEvent d) 
	{
		if(d.getEntity().hasMetadata("griffon") && d.getCause()!=DamageCause.VOID) {
			d.setCancelled(true);
		}
	}
	public void Protection(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity() instanceof Player&&!d.isCancelled()) 
		{
			Player par = (Player)d.getEntity();
			   
			if(Party.hasParty(par))	{
					if(Party.getPlayerMembers(Party.getParty(par)).stream().anyMatch(pal -> ClassData.pc.get(pal.getUniqueId())==3 && pal.isBlocking())) {
						par.playSound(par.getLocation(), Sound.ITEM_SHIELD_BLOCK, 0.6f, 1.46f);
						d.setDamage(d.getDamage()*0.5);
						if(par.getLocale().equalsIgnoreCase("ko_kr")) {
		                    par.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.YELLOW +"[보호]").create());
						}
						else {
		                    par.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.YELLOW +"[Protection]").create());
						}

						Stream<Player> pals = Party.getPlayerMembers(Party.getParty(par)).stream().filter(pal -> ClassData.pc.get(pal.getUniqueId())==3 && pal.isBlocking());
						pals.forEach(pal ->{
							if(Proficiency.getpro(pal)<1) {
								return;
							}
							if(protectalbe(d.getDamager(),par,pal)) {
								d.setCancelled(true);
							}
						});
					}
			}   
			if(ClassData.pc.get(par.getUniqueId()) == 3 && par.isBlocking())	{
				d.setDamage(d.getDamage()*0.5);
				par.playSound(par.getLocation(), Sound.ITEM_SHIELD_BLOCK, 0.6f, 1.46f);
				if(par.getLocale().equalsIgnoreCase("ko_kr")) {
                    par.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.YELLOW +"[보호]").create());
				}
				else {
                    par.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.YELLOW +"[Protection]").create());
				}

				if(Proficiency.getpro(par)<1) {
					return;
				}
				if(protectalbe(d.getDamager(),par,par)) {
					d.setCancelled(true);
				}
			}
		}
	}
	
	
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();

	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE") && p.getInventory().getItemInOffHand().getType()== Material.SHIELD)
			{
				e.setCancelled(true);
			}
		}
		
	}
	
	
	public void Faith(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
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
		    
			
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 3) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE") && p.getInventory().getItemInOffHand().getType()== Material.SHIELD)	{
					d.setDamage(d.getDamage()*1.3+psd.Protection.get(p.getUniqueId())*0.4383);
				}
				
			}
			if(judge.containsKey(p.getUniqueId())) {
				d.setDamage(d.getDamage()*(1+0.01*judge.get(p.getUniqueId())));
			}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity) 
		{
			Projectile ar = (Projectile)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
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
			    
				
				
				
				if(ClassData.pc.get(p.getUniqueId()) == 3) {		
					if(p.getInventory().getItemInMainHand().getType().name().contains("_AXE") && p.getInventory().getItemInOffHand().getType()== Material.SHIELD)	{
						d.setDamage(d.getDamage()*1.3+psd.Protection.get(p.getUniqueId())*0.4383);
					}
					
				}
				if(judge.containsKey(p.getUniqueId())) {
					d.setDamage(d.getDamage()*(1+0.01*judge.get(p.getUniqueId())));
				}
			}
		}
	}
	

}