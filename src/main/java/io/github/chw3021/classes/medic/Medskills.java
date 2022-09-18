package io.github.chw3021.classes.medic;




import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.util.EulerAngle;
import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.classes.witchdoctor.Wdcskills;
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
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Medskills extends Pak implements Serializable, Listener {
	/**
	 * 
	 */
	private static transient final long serialVersionUID = -4137605187075743825L;
	private HashMap<String, Long> rrcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> wrcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> arcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> dpcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> bultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> bult2cooldown = new HashMap<String, Long>();
	//private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	



	private HashMap<UUID, Integer> spf = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> spft = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> msstm = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> msstmt = new HashMap<UUID, Integer>();
	

	private HashMap<UUID, Integer> brr = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> brrt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> hlgp = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> hlgpt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> fnndl = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> fnndlt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> utrlnbz = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> utrlnbzt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> wing = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> wingt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> winget = new HashMap<UUID, Integer>();

	private static HashMap<UUID, Integer> stretcht = new HashMap<UUID, Integer>();

	private static HashMap<UUID, Integer> inhancert = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> inhancer = new HashMap<UUID, Integer>();
	
	private static HashMap<UUID, Integer> aed = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> aedt = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Double> decayed = new HashMap<UUID, Double>();
	private HashMap<Player, Integer> sz = new HashMap<Player, Integer>();
	private HashMap<UUID, Integer> hideoutarmor = new HashMap<>();
	
	
	private String path = new File("").getAbsolutePath();
	private MedSkillsData ssd ;

	private static final Medskills instance = new Medskills ();
	public static Medskills getInstance()
	{
		return instance;
	}


		
	public void er(PluginEnableEvent ev) 
	{
		MedSkillsData s = new MedSkillsData(MedSkillsData.loadData(path +"/plugins/RPGskills/MedskillsData.data"));
		ssd =s;
	}
	
	
	public void classinv(InventoryClickEvent e)
	{
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Medskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
				Player p = (Player) e.getWhoClicked();
            	if(winget.containsKey(p.getUniqueId())) {
                	Bukkit.getServer().getScheduler().cancelTask(winget.get(p.getUniqueId()));
                	winget.remove(p.getUniqueId());
            	}
            	if(wingt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(wingt.get(p.getUniqueId()));
            		wingt.remove(p.getUniqueId());
            	}
            	
			}
			else
			{	
				MedSkillsData s = new MedSkillsData(MedSkillsData.loadData(path +"/plugins/RPGskills/MedskillsData.data"));
				ssd =s;
			}
			
		}
	}

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		MedSkillsData s = new MedSkillsData(MedSkillsData.loadData(path +"/plugins/RPGskills/MedskillsData.data"));
		ssd =s;
		
	}
	
	
	public void RemedyingRocket(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 61 && ssd.RemedyingRocket.getOrDefault(p.getUniqueId(), 1) >=1 && !p.hasCooldown(Material.ARROW)) {	
			if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK) && !p.isSneaking())
			{	
				
				
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
					ev.setCancelled(true);
					List<ItemStack> arrows = new ArrayList<ItemStack>();
					ItemStack heal = new ItemStack(Material.FIREWORK_ROCKET);
					FireworkMeta healm = (FireworkMeta) heal.getItemMeta();
					healm.setPower(1);
					healm.setDisplayName("Remedying Rocket");
					FireworkEffect fwe = FireworkEffect.builder()
							.withColor(Color.AQUA)
							.withFade(Color.FUCHSIA)
							.with(Type.STAR)
							.withFlicker()
							.withTrail()
							.build();
					healm.addEffect(fwe);
					heal.setItemMeta(healm);
					ItemStack cb = p.getInventory().getItemInMainHand();
					CrossbowMeta cbm = (CrossbowMeta) cb.getItemMeta();
					if(!cbm.hasChargedProjectiles()) {
						arrows.add(heal);
						cbm.setChargedProjectiles(arrows);
						cb.setItemMeta(cbm);
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA + "치유로켓 장전됨").create());
						}
						else {
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA + "Remedying Rocket Loaded").create());
						}
					}
					else if(cbm.hasChargedProjectiles()) {
						cbm.setChargedProjectiles(null);
						cb.setItemMeta(cbm);
					}
				}
			}
		}
	}


	
	public void RemedyingRocket(EntityShootBowEvent ev) 
	{
		
		if(ev.getEntity() instanceof Player)
		{
			Player p = (Player) ev.getEntity();
			double sec = 6*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

			if(wing.containsKey(p.getUniqueId())) {
				sec=0;
			}
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 61) {
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
					if(ev.getProjectile().getType() == EntityType.FIREWORK)
					{
						Firework ar = (Firework) ev.getProjectile();
						if(ar.getFireworkMeta().getDisplayName().equals("Remedying Rocket")) {
							if(rrcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
				            {
			                    
				                double timer = (rrcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
				                if(!(timer < 0)) // if timer is still more then 0 or 0
				                {
				                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("치유로켓 발사 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
				                	}
				                	else {
					                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to shoot RemedyingRocket").create());
				                	}
				                    ev.setCancelled(true);
				                    ev.setConsumeItem(false);
				                }
				                else // if timer is done
				                {
				                    rrcooldown.remove(p.getName()); // removing player from HashMap
				                    ar.setMetadata("Remedying Rocket", new FixedMetadataValue(RMain.getInstance(), true));
				                	rrcooldown.put(p.getName(), System.currentTimeMillis());
				                }
				            }
				            else // if cooldown doesn't have players name in it
				            {
			                    ar.setMetadata("Remedying Rocket", new FixedMetadataValue(RMain.getInstance(), true));
			                	rrcooldown.put(p.getName(), System.currentTimeMillis());
				            }
						}
					}
				}
			}
		}
	}

	
	public void RemedyingRocket(FireworkExplodeEvent f) 
	{
		if(f.getEntity().getShooter() instanceof Player && f.getEntity().hasMetadata("Remedying Rocket")) {
			Firework fr = f.getEntity();
			Player p = (Player) f.getEntity().getShooter();
			final Location frl = fr.getLocation();
	        
			
			fr.getWorld().spawnParticle(Particle.GLOW_SQUID_INK,frl, 100,1,1,1);
			fr.getWorld().spawnParticle(Particle.SCRAPE,frl, 100,1,1,1);
			fr.getWorld().playSound(frl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
			fr.remove();
			for(Entity e : frl.getWorld().getNearbyEntities(frl, 4,4, 4)) {
        		if (e instanceof Player&& e!=p) 
				{
					Player p1 = (Player) e;
					if(Party.hasParty(p) && Party.hasParty(p1))	{
						if(Party.getParty(p).equals(Party.getParty(p1)))
							{
								p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1, 0, false, false));
								if(p1.hasPotionEffect(PotionEffectType.REGENERATION)) {
									p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1+p1.getPotionEffect(PotionEffectType.REGENERATION).getAmplifier(), false, false));
								}
								else {
									p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1, false, false));
								}
								p1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 2, false, false));
								if(Proficiency.getpro(p)>=2) {
									Holding.invur(p1, 15l);
								}
								continue;
							}
						}
				}
        		if (e==p) 
				{
					p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1, 0, false, false));
					if(p.hasPotionEffect(PotionEffectType.REGENERATION)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1+p.getPotionEffect(PotionEffectType.REGENERATION).getAmplifier(), false, false));
					}
					else {
						p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1, false, false));
					}
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 0, false, false));
					if(Proficiency.getpro(p)>=2) {
						Holding.invur(p, 15l);
					}
					continue;	
				}
				if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
					LivingEntity le = (LivingEntity)e;
					atk0(0.32, ssd.RemedyingRocket.get(p.getUniqueId())*0.316, p, le,6);
					if(Proficiency.getpro(p)>=1) {
						fr.getWorld().spawnParticle(Particle.FLASH,frl, 1);
						fr.getWorld().playSound(frl, Sound.ENTITY_SHULKER_TELEPORT, 1, 2);
						atk0(0.4, ssd.RemedyingRocket.get(p.getUniqueId())*0.4, p, le,5);
						le.teleport(frl);
					}
					if(Proficiency.getpro(p)>=2) {
						Holding.holding(p,le, 15l);
					}
				}
			}
		}
	}

	
	public void RemedyingRocket(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Firework && d.getEntity() instanceof LivingEntity) 
		{
			Firework fw = (Firework) d.getDamager();
		    if (fw.hasMetadata("Remedying Rocket")) {
		        d.setCancelled(true);
		    }
		}
	}
	
	@SuppressWarnings("deprecation")
	public void ArrowClinic(ProjectileHitEvent ev) 
	{
		
		if(ev.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)ev.getEntity().getShooter();
		    
			
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 61 && ssd.ArrowClinic.getOrDefault(p.getUniqueId(), 1) >=1) {
				if(ev.getHitEntity()!=null && ev.getEntity() instanceof Arrow) {
					Entity e =ev.getHitEntity();
					{
                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
						{
                    		if (e instanceof Player) 
    						{
                    			
    							Player p1 = (Player) e;
    							if(Party.hasParty(p) && Party.hasParty(p1))	{
    							if(Party.getParty(p).equals(Party.getParty(p1)))
    								{
    									if(p1.getHealth()+p1.getMaxHealth()*(0.05+ssd.ArrowClinic.get(p.getUniqueId())*0.005+ssd.Medicine.get(p.getUniqueId())*0.004)>=p1.getMaxHealth()) {
    										p1.setHealth(p1.getMaxHealth());
    										p1.playSound(p1.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 2);
    										ev.getEntity().remove();
    									}
    									else {
    										p1.setHealth(p1.getHealth() + p1.getMaxHealth()*(0.05+ssd.ArrowClinic.get(p.getUniqueId())*0.005+ssd.Medicine.get(p.getUniqueId())*0.004));
    										p1.playSound(p1.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 2);
    										ev.getEntity().remove();
    									}
    									if(Proficiency.getpro(p)>=1) {
    										if(p1.hasPotionEffect(PotionEffectType.SPEED)) {
    											p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20+p1.getPotionEffect(PotionEffectType.SPEED).getDuration(), p1.getPotionEffect(PotionEffectType.SPEED).getAmplifier(), false, false));
    										}
    										else {
    											p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 0, false, false));
    										}	
    										if(p1.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
    											p1.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20+p1.getPotionEffect(PotionEffectType.FAST_DIGGING).getDuration(), p1.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier(), false, false));
    										}
    										else {
    											p1.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20, 0, false, false));
    										}
    									}
    									return;
    								}
    							}
    						}
                			LivingEntity le = (LivingEntity)e;
    						decayed.computeIfPresent(le.getUniqueId(), (k,v) -> v+0.03*ssd.ArrowClinic.get(p.getUniqueId()));			
    						decayed.putIfAbsent(le.getUniqueId(), 0d);
                        	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                        		@Override
    		                	public void run() 
    			                {	
                        			decayed.computeIfPresent(le.getUniqueId(), (k,v) -> v-0.03*ssd.ArrowClinic.get(p.getUniqueId()));
                 					if(decayed.getOrDefault(le.getUniqueId(),-1d)<0) {
                 						decayed.remove(le.getUniqueId());
                 					}
                 				}
    	                	}, 200);
                        	
						}
					}
				}
			}			
		}
	}
	

	
	public void Decay(EntityDamageEvent d) 
	{
        
		
		if(d.getEntity() instanceof LivingEntity) 
		{
		LivingEntity le = (LivingEntity)d.getEntity();
			if(decayed.containsKey(le.getUniqueId())) {
				if(1+decayed.get(le.getUniqueId())>=1.9) {
					d.setDamage(d.getDamage()*(1.9));				
				}
				else {
					d.setDamage(d.getDamage()*(1+decayed.get(le.getUniqueId())));
					
				}
			}
		}
	}
	
	
	public void swcancle(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 61) {
		if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
		{
        	ev.setCancelled(true);
		}
		}
	}
	
	
	public void Decontamination(PlayerSwapHandItemsEvent ev) 
	{
			Player p = ev.getPlayer();
			double sec = 11*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	        
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 61 && ssd.Decontamination.getOrDefault(p.getUniqueId(), 1) >=1) {
			if(((p.isSneaking()) && !(p.isSprinting())))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
		        	ev.setCancelled(true);
					
					if(arcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
	                    
		                double timer = (arcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("제독 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
		                	}
		                	else {
			                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Decontamination").create());
		                	}

		                }
		                else // if timer is done
		                {
		                    arcooldown.remove(p.getName()); // removing player from HashMap
		                    
		                	if(brrt.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(brrt.get(p.getUniqueId()));
		                		brrt.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							brr.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						brr.remove(p.getUniqueId());
		    	                }
		    	            }, 25); 
		                	brrt.put(p.getUniqueId(), task);
		                	
		                    ItemStack is = new ItemStack(Material.SPLASH_POTION);
		                    ItemMeta imeta = is.getItemMeta();
		                    PotionMeta pometa = (PotionMeta)imeta;
		                    pometa.setColor(Color.FUCHSIA);
		                    is.setItemMeta(pometa);
		                    ThrownPotion thr = (ThrownPotion) p.launchProjectile(ThrownPotion.class);
		                    thr.setShooter(p);
		                    thr.setBounce(false);
		                    thr.setItem(is);
		                    thr.setMetadata("Decon of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		                	arcooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
	                    
	                	if(brrt.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(brrt.get(p.getUniqueId()));
	                		brrt.remove(p.getUniqueId());
	                	}

	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						if(Proficiency.getpro(p)>=1) {
	    							brr.putIfAbsent(p.getUniqueId(), 0);
	    						}
	    	                }
	    	            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						brr.remove(p.getUniqueId());
	    	                }
	    	            }, 25); 
	                	brrt.put(p.getUniqueId(), task);
	                	
	                    ItemStack is = new ItemStack(Material.SPLASH_POTION);
	                    ItemMeta imeta = is.getItemMeta();
	                    PotionMeta pometa = (PotionMeta)imeta;
	                    pometa.setColor(Color.FUCHSIA);
	                    is.setItemMeta(pometa);
	                    ThrownPotion thr = (ThrownPotion) p.launchProjectile(ThrownPotion.class);
	                    thr.setShooter(p);
	                    thr.setBounce(false);
	                    thr.setItem(is);
	                    thr.setMetadata("Decon of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                	arcooldown.put(p.getName(), System.currentTimeMillis());
		            }
				}
			}
			}
	}

	
	public void Throw(ProjectileHitEvent ev) 
	{
		
		if(ev.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)ev.getEntity().getShooter();
		    
			
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 61 && ev.getEntity().hasMetadata("Decon of"+p.getName())) {
					for(int count = 0 ; count <11; count++) {
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
		                	public void run() 
			                {	
			                    AreaEffectCloud cloud = (AreaEffectCloud) p.getWorld().spawnEntity(ev.getEntity().getLocation(), EntityType.AREA_EFFECT_CLOUD);
								cloud.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			                    cloud.setRadius(5f);
			                    cloud.setSource(p);
			                    cloud.setSilent(false);
			                    cloud.setColor(Color.FUCHSIA);
			                    cloud.setRadiusPerTick(2.5f);
			                    cloud.setDuration(3);

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
				        		if(Proficiency.getpro(p)>=1) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
				        		}
								for (Entity e : p.getWorld().getNearbyEntities(ev.getEntity().getLocation(), 5, 5, 5))
								{
			                		if (e instanceof Player) 
									{
			                			
										Player p1 = (Player) e;
										if(Party.hasParty(p) && Party.hasParty(p1))	{
										if(Party.getParty(p).equals(Party.getParty(p1)))
											{
						                	if(p1.hasPotionEffect(PotionEffectType.BAD_OMEN))
							        		{
							        			p1.removePotionEffect(PotionEffectType.BAD_OMEN);
							        		}
							        		if(p1.hasPotionEffect(PotionEffectType.BLINDNESS))
							        		{
							        			p1.removePotionEffect(PotionEffectType.BLINDNESS);
							        		}
							        		if(p1.hasPotionEffect(PotionEffectType.CONFUSION))
							        		{
							        			p1.removePotionEffect(PotionEffectType.CONFUSION);
							        		}
							        		if(p1.hasPotionEffect(PotionEffectType.HUNGER))
							        		{
							        			p1.removePotionEffect(PotionEffectType.HUNGER);
							        		}
							        		if(p1.hasPotionEffect(PotionEffectType.POISON))
							        		{
							        			p1.removePotionEffect(PotionEffectType.POISON);
							        		}
							        		if(p1.hasPotionEffect(PotionEffectType.SLOW))
							        		{
							        			p1.removePotionEffect(PotionEffectType.SLOW);
							        		}
							        		if(p1.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
							        		{
							        			p1.removePotionEffect(PotionEffectType.SLOW_DIGGING);
							        		}
							        		if(p1.hasPotionEffect(PotionEffectType.UNLUCK))
							        		{
							        			p1.removePotionEffect(PotionEffectType.UNLUCK);
							        		}
							        		if(p1.hasPotionEffect(PotionEffectType.WEAKNESS))
							        		{
							        			p1.removePotionEffect(PotionEffectType.WEAKNESS);
							        		}
							        		if(p1.hasPotionEffect(PotionEffectType.WITHER))
							        		{
							        			p1.removePotionEffect(PotionEffectType.WITHER);
							        		}
							        		if(Proficiency.getpro(p)>=1) {
												p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
							        		}
											continue;
											}
										}
									}
			                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
									{
										LivingEntity le = (LivingEntity)e;
										atk0(0.15, ssd.Decontamination.get(p.getUniqueId())*0.16, p, le,6);
						        		if(Proficiency.getpro(p)>=1) {
											Holding.holding(p, le, 10l);
						        		}
									}
								}
				                }
	                	   }, count*3); 
						
					}
				
			}
			
		}
	}


	public void Barrier(PlayerSwapHandItemsEvent ev) 
	{
			Player p = ev.getPlayer();
			
			if(ClassData.pc.get(p.getUniqueId()) == 61 && brr.containsKey(p.getUniqueId())) {
			if(((p.isSneaking()) && !(p.isSprinting())))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
		        	ev.setCancelled(true);

		            final Location ptl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation().clone();
		            final World ptlw = ptl.getWorld();
		            
	            	if(brrt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(brrt.get(p.getUniqueId()));
	            		brrt.remove(p.getUniqueId());
	            	}
					brr.remove(p.getUniqueId());
					


	            	if(hlgpt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(hlgpt.get(p.getUniqueId()));
	            		hlgpt.remove(p.getUniqueId());
	            	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=2) {
								hlgp.putIfAbsent(p.getUniqueId(), 0);
							}
		                }
		            }, 3); 

	        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							hlgp.remove(p.getUniqueId());
		                }
		            }, 25); 
	            	hlgpt.put(p.getUniqueId(), task);

	            	HashSet<Location> lss = new HashSet<>();
	            	HashSet<Location> lss2 = new HashSet<>();
	            	HashSet<Location> sph = new HashSet<>();
	            	
	            	for(int ix =-4; ix<4; ix+=1) {
	            		lss.add(ptl.clone().add(ix, 0, 4));
	            		lss.add(ptl.clone().add(ix, 0, -4));
	            		lss.add(ptl.clone().add(-4, 0, ix));
	            		lss.add(ptl.clone().add(4, 0, ix));
	            	}

	            	for(int ix =-4; ix<4; ix+=1) {
	            		lss2.add(ptl.clone().add(ix, -0.5, 4));
	            		lss2.add(ptl.clone().add(ix, -0.5, -4));
	            		lss2.add(ptl.clone().add(-4, -0.5, ix));
	            		lss2.add(ptl.clone().add(4, -0.5, ix));
	            	}

	                
	        		for(int ix = -6; ix<=6; ix+=1) {
	        			for(int iy = -6; iy<=6; iy+=1) {
	        				for(int iz = -6; iz<=6; iz+=1) {
	        					if(ix*ix + iy*iy + iz*iz <= 36 && ix*ix + iy*iy + iz*iz >= 34){
	        						sph.add(ptl.clone().add(ix, iy, iz));
	        					}
	        				}
	        			}
	        		}
	            	
	            	lss.forEach(l -> {
	            		ArmorStand as = l.getWorld().spawn(l, ArmorStand.class);
	            		as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	            		as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	            		as.setInvisible(true);
	            		as.setMarker(true);
	            		as.getEquipment().setHelmet(new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS));
	            		as.setInvulnerable(true);
	            		as.setHeadPose(new EulerAngle(Math.PI/60 , 0, 0));
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                		@Override
		                	public void run() 
			                {	
             					as.remove();
			                }
                    	},80);
	            	});
					p.playSound(p.getLocation(), Sound.ENTITY_BEE_STING, 0.5f, 0);
					p.playSound(p.getLocation(), Sound.ENTITY_PUFFER_FISH_BLOW_OUT, 1, 0);
	            	
	            	lss2.forEach(l -> {
	            		ArmorStand as = l.getWorld().spawn(l, ArmorStand.class);
	            		as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	            		as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	            		as.setInvisible(true);
	            		as.setMarker(true);
	            		as.getEquipment().setHelmet(new ItemStack(Material.CYAN_STAINED_GLASS));
	            		as.setInvulnerable(true);
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                		@Override
		                	public void run() 
			                {	
             					as.remove();
			                }
                    	},80);
	            	});
					for (Entity e : ptl.getWorld().getNearbyEntities(ptl.clone(), 6, 6, 6))
					{
                		if (e instanceof Player) 
						{
                			
							Player p1 = (Player) e;
							if(Party.hasParty(p) && Party.hasParty(p1))	{
							if(Party.getParty(p).equals(Party.getParty(p1)))
								{

			                    Holding.invur(p1, 10l);
								continue;
								}
							}
						}
                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
						{
							LivingEntity le = (LivingEntity)e;
							atk0(1.0, ssd.Decontamination.get(p.getUniqueId())*1.1, p, le,6);
							Holding.holding(p, le, 10l);
						}
					}
					for(int count = 0 ; count <40; count++) {
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
		                	public void run() 
             				{	
             					sph.forEach(l ->{
        							ptlw.spawnParticle(Particle.SCRAPE, l, 30,0.5, 0.5, 0.5,0);
             					});
             					
			                    Holding.invur(p, 10l);
			                    p.playSound(ptl, Sound.BLOCK_SCULK_SENSOR_CLICKING, 0.03f, 1.6f);
								for (Entity e : ptl.getWorld().getNearbyEntities(ptl.clone(), 6, 6, 6))
								{
			                		if (e instanceof Player) 
									{
			                			
										Player p1 = (Player) e;
										if(Party.hasParty(p) && Party.hasParty(p1))	{
										if(Party.getParty(p).equals(Party.getParty(p1)))
											{

						                    Holding.invur(p1, 10l);
											continue;
											}
										}
									}
								}
				                }
	                	   }, count*2);
					}
					
				}
			}
			}
	}
	

	
	public void HealingPump(PlayerSwapHandItemsEvent ev) 
	{
			Player p = ev.getPlayer();
			
			if(ClassData.pc.get(p.getUniqueId()) == 61 && hlgp.containsKey(p.getUniqueId())) {
			if(((p.isSneaking()) && !(p.isSprinting())))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
		        	ev.setCancelled(true);

	            	if(hlgpt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(hlgpt.get(p.getUniqueId()));
	            		hlgpt.remove(p.getUniqueId());
	            	}
					hlgp.remove(p.getUniqueId());

		            Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation();

            		ArmorStand as = tl.getWorld().spawn(tl, ArmorStand.class);
            		as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
            		as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
            		as.setInvisible(true);
            		as.setMarker(true);
            		as.getEquipment().setHelmet(new ItemStack(Material.WHITE_SHULKER_BOX));
            		as.setInvulnerable(true);
                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            		@Override
         				public void run() 
		                {	
         					as.remove();
		                }
                	},60);
					for(int count = 0 ; count <5; count++) {
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                    		@Override
		                	public void run() 
             				{	
                    			p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
								p.playSound(p.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 0.5f, 2);
								p.playSound(p.getLocation(), Sound.ENTITY_PUFFER_FISH_BLOW_UP, 1, 0);
    							tl.getWorld().spawnParticle(Particle.HEART, tl.clone(), 100,3, 3, 3);
    							tl.getWorld().spawnParticle(Particle.CLOUD, tl.clone(), 50,1, 1, 1);
    							tl.getWorld().spawnParticle(Particle.SCRAPE, tl.clone(), 100,1, 1, 1);
    							tl.getWorld().spawnParticle(Particle.WAX_ON, tl.clone(), 200,1, 1, 1);
								for (Entity e : tl.getWorld().getNearbyEntities(tl.clone(), 6, 6, 6))
								{
			                		if (e instanceof Player) 
									{
			                			
										Player p1 = (Player) e;
										if(Party.hasParty(p) && Party.hasParty(p1))	{
										if(Party.getParty(p).equals(Party.getParty(p1)))
											{
			                    			p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
											continue;
											}
										}
									}
			                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
									{
										LivingEntity le = (LivingEntity)e;
										atk0(0.8, ssd.Decontamination.get(p.getUniqueId())*0.8, p, le,6);
										le.teleport(as);
									}
								}
				                }
	                	   }, count*10);
					}
				}
			}
			}
	}
	
	
	
	
	@SuppressWarnings("deprecation")
	public void SupplyCart(PlayerSwapHandItemsEvent ev) 
	{
			Player p = ev.getPlayer();
			double sec = 22*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	        
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 61 && ssd.SupplyCart.getOrDefault(p.getUniqueId(), 1) >=1 && p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW) {
			if(!p.isOnGround()&& !p.isSneaking())
			{
	            Location ptl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
					if(dpcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {

						ev.setCancelled(true);
		                double timer = ((dpcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d); // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("보급카트 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
		                	}
		                	else {
			                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use SupplyCart").create());
		                	}

		                }
		                else // if timer is done
		                {
		                    dpcooldown.remove(p.getName()); // removing player from HashMap

		                	if(spft.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(spft.get(p.getUniqueId()));
		                		spft.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							spf.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						spf.remove(p.getUniqueId());
		    	                }
		    	            }, 25); 
		                	spft.put(p.getUniqueId(), task);
		                	
		                    
		                    p.playSound(ptl, Sound.ENTITY_MINECART_RIDING, 1, 2);
		                    Location cl = p.getEyeLocation().add(0, 1, 0);
		                    Minecart supply = (Minecart) p.getWorld().spawnEntity(cl, EntityType.MINECART_CHEST);
		                    supply.setInvulnerable(true);
		                    supply.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		                    supply.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		                    supply.setFlyingVelocityMod(ptl.toVector().subtract(cl.toVector()).normalize().multiply(0.6));
							ItemStack pot = new ItemStack(Material.POTION);
							PotionMeta potm = (PotionMeta) pot.getItemMeta();
							potm.setColor(Color.FUCHSIA);
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								potm.setDisplayName(ChatColor.LIGHT_PURPLE + p.getName() + " 의 활력제");
							}
							else {
								potm.setDisplayName(ChatColor.LIGHT_PURPLE + p.getName() + " 's Inhancer");
							}
							potm.setLocalizedName(p.getName());
							potm.setBasePotionData(new PotionData(PotionType.UNCRAFTABLE));
							pot.setItemMeta(potm);
							p.getInventory().addItem(pot);
		                    p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
		                    for (Entity e : supply.getNearbyEntities(6, 6, 6))
							{
	                    		if (e instanceof Player && e!=p) 
								{
									
									Player p1 = (Player) e;
									if(Party.hasParty(p) && Party.hasParty(p1))	{
										if(Party.getParty(p).equals(Party.getParty(p1)))
										{
											p1.getInventory().addItem(pot);
						                    p1.playSound(p1.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
										}
									}
								}
							}
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
			                	public void run() 
				                {
	             					supply.remove();
	             				}
		                	}, 100);
			                dpcooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
	                	if(spft.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(spft.get(p.getUniqueId()));
	                		spft.remove(p.getUniqueId());
	                	}

	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						if(Proficiency.getpro(p)>=1) {
	    							spf.putIfAbsent(p.getUniqueId(), 0);
	    						}
	    	                }
	    	            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						spf.remove(p.getUniqueId());
	    	                }
	    	            }, 25); 
	                	spft.put(p.getUniqueId(), task);
	                	
	                    p.playSound(ptl, Sound.ENTITY_MINECART_RIDING, 1, 2);
	                    Location cl = p.getEyeLocation().add(0, 1, 0);
	                    Minecart supply = (Minecart) p.getWorld().spawnEntity(cl, EntityType.MINECART_CHEST);
	                    supply.setInvulnerable(true);
	                    supply.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    supply.setFlyingVelocityMod(ptl.toVector().subtract(cl.toVector()).normalize().multiply(0.6));
						ItemStack pot = new ItemStack(Material.POTION);
						PotionMeta potm = (PotionMeta) pot.getItemMeta();
						potm.setColor(Color.FUCHSIA);
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							potm.setDisplayName(ChatColor.LIGHT_PURPLE + p.getName() + " 의 활력제");
						}
						else {
							potm.setDisplayName(ChatColor.LIGHT_PURPLE + p.getName() + " 's Inhancer");
						}
						potm.setLocalizedName(p.getName());
						potm.setBasePotionData(new PotionData(PotionType.UNCRAFTABLE));
						pot.setItemMeta(potm);
						p.getInventory().addItem(pot);
	                    p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
	                    for (Entity e : supply.getNearbyEntities(6, 6, 6))
						{
                    		if (e instanceof Player && e!=p) 
							{
								
								Player p1 = (Player) e;
								if(Party.hasParty(p) && Party.hasParty(p1))	{
									if(Party.getParty(p).equals(Party.getParty(p1)))
									{
										p1.getInventory().addItem(pot);
					                    p1.playSound(p1.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
									}
								}
							}
						}
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
		                	public void run() 
			                {	
             					supply.remove();
             				}
	                	}, 100);
		                dpcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				
			}
			}
	}
	
	
	public void Inhancer(PlayerItemConsumeEvent d) 
	{
		final ItemStack is = d.getItem();
		final ItemMeta ism = is.getItemMeta();
		if(ism.getDisplayName().contains("Inhancer") || ism.getDisplayName().contains("활력제") ) {
			ItemStack b = new ItemStack(Material.GLASS_BOTTLE,1);
        	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
                    	public void run() 
                        {	
         					d.getPlayer().getInventory().removeItem(b);
         				}
                	}, 1/5);
			if(Bukkit.getPlayerExact(d.getItem().getItemMeta().getLocalizedName())==null) {
				return;
			}
			
			Player m = Bukkit.getPlayer(d.getItem().getItemMeta().getLocalizedName());
			Player p = d.getPlayer();
			if(Party.hasParty(p) && Party.hasParty(m))	{
				if(Party.getParty(p).equals(Party.getParty(m)))
				{
					if(inhancert.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(inhancert.get(p.getUniqueId()));
	 					inhancert.remove(p.getUniqueId());
						
					}
					if(p.hasPotionEffect(PotionEffectType.JUMP) && !inhancer.containsKey(p.getUniqueId())) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 50+50*ssd.SupplyCart.get(m.getUniqueId())+p.getPotionEffect(PotionEffectType.JUMP).getDuration(), 1+p.getPotionEffect(PotionEffectType.JUMP).getAmplifier(), false, false));
					}
					else {
						p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 50+50*ssd.SupplyCart.get(m.getUniqueId()), 1, false, false));
					}
					if(p.hasPotionEffect(PotionEffectType.FAST_DIGGING)&& !inhancer.containsKey(p.getUniqueId())) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 50+50*ssd.SupplyCart.get(m.getUniqueId())+p.getPotionEffect(PotionEffectType.FAST_DIGGING).getDuration(), 1+ssd.SupplyCart.get(m.getUniqueId())/2+p.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier(), false, false));
					}
					else {
						p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 50+50*ssd.SupplyCart.get(m.getUniqueId()), 1+ssd.SupplyCart.get(m.getUniqueId())/2, false, false));
					}
					if(p.hasPotionEffect(PotionEffectType.SPEED)&& !inhancer.containsKey(p.getUniqueId())) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 50+50*ssd.SupplyCart.get(m.getUniqueId())+p.getPotionEffect(PotionEffectType.SPEED).getDuration(), 1+p.getPotionEffect(PotionEffectType.SPEED).getAmplifier(), false, false));
					}
					else {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 50+50*ssd.SupplyCart.get(m.getUniqueId()), 1, false, false));
					}
					if(p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)&& !inhancer.containsKey(p.getUniqueId())) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 50+50*ssd.SupplyCart.get(m.getUniqueId())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getDuration(), 1+ssd.SupplyCart.get(m.getUniqueId())/2+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier(), false, false));
					}
					else {
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 50+50*ssd.SupplyCart.get(m.getUniqueId()), 1+ssd.SupplyCart.get(m.getUniqueId())/2, false, false));
					}
					inhancer.put(p.getUniqueId(), 1);
		        	int in = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		 		@Override
		            	public void run() 
		                {	
		 					inhancer.remove(p.getUniqueId());
		 					inhancert.remove(p.getUniqueId());
		 				}
		        	}, 50+50*ssd.SupplyCart.get(m.getUniqueId()));
		        	inhancert.put(p.getUniqueId(), in);
				}
			}
			else if(p == m) {

				if(inhancert.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(inhancert.get(p.getUniqueId()));
 					inhancert.remove(p.getUniqueId());
				}
				if(p.hasPotionEffect(PotionEffectType.JUMP) && !inhancer.containsKey(p.getUniqueId())) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 50+50*ssd.SupplyCart.get(m.getUniqueId())+p.getPotionEffect(PotionEffectType.JUMP).getDuration(), 1+p.getPotionEffect(PotionEffectType.JUMP).getAmplifier(), false, false));
				}
				else {
					p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 50+50*ssd.SupplyCart.get(m.getUniqueId()), 1, false, false));
				}
				if(p.hasPotionEffect(PotionEffectType.FAST_DIGGING)&& !inhancer.containsKey(p.getUniqueId())) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 50+50*ssd.SupplyCart.get(m.getUniqueId())+p.getPotionEffect(PotionEffectType.FAST_DIGGING).getDuration(), 1+ssd.SupplyCart.get(m.getUniqueId())/2+p.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier(), false, false));
				}
				else {
					p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 50+50*ssd.SupplyCart.get(m.getUniqueId()), 1+ssd.SupplyCart.get(m.getUniqueId())/2, false, false));
				}
				if(p.hasPotionEffect(PotionEffectType.SPEED)&& !inhancer.containsKey(p.getUniqueId())) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 50+50*ssd.SupplyCart.get(m.getUniqueId())+p.getPotionEffect(PotionEffectType.SPEED).getDuration(), 1+p.getPotionEffect(PotionEffectType.SPEED).getAmplifier(), false, false));
				}
				else {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 50+50*ssd.SupplyCart.get(m.getUniqueId()), 1, false, false));
				}
				if(p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)&& !inhancer.containsKey(p.getUniqueId())) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 50+50*ssd.SupplyCart.get(m.getUniqueId())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getDuration(), 1+ssd.SupplyCart.get(m.getUniqueId())/2+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier(), false, false));
				}
				else {
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 50+50*ssd.SupplyCart.get(m.getUniqueId()), 1+ssd.SupplyCart.get(m.getUniqueId())/2, false, false));
				}
				inhancer.put(p.getUniqueId(), 1);
	        	int in = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	 		@Override
	            	public void run() 
	                {	
	 					inhancer.remove(p.getUniqueId());
	 					inhancert.remove(p.getUniqueId());
	 				}
	        	}, 50+50*ssd.SupplyCart.get(m.getUniqueId()));
	        	inhancert.put(p.getUniqueId(), in);
			}
		}
	}
	

	
	@SuppressWarnings("deprecation")
	public void SupportFire(PlayerSwapHandItemsEvent ev) 
	{
			Player p = ev.getPlayer();
			
			if(ClassData.pc.get(p.getUniqueId()) == 61 && p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW && spf.containsKey(p.getUniqueId())) {
			if(!p.isOnGround()&& !p.isSneaking())
			{
				ev.setCancelled(true);
				
            	if(spft.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(spft.get(p.getUniqueId()));
            		spft.remove(p.getUniqueId());
            	}
				spf.remove(p.getUniqueId());
				


            	if(msstmt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(msstmt.get(p.getUniqueId()));
            		msstmt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							msstm.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						msstm.remove(p.getUniqueId());
	                }
	            }, 25); 
            	msstmt.put(p.getUniqueId(), task);

	            Location ptl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();

				for(int count = 0 ; count <8; count++) {
                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
	                	public void run() 
		                {	
         					ptl.getWorld().spawnParticle(Particle.ITEM_CRACK, ptl, 100,5,5,5, new ItemStack(Material.IRON_BARS));
         					p.playSound(ptl, Sound.BLOCK_CHAIN_HIT, 1, 2);
         					p.playSound(ptl, Sound.ENTITY_ARROW_HIT, 1, 2);
         					
							for (Entity e : p.getWorld().getNearbyEntities(ptl, 5, 5, 5))
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
									atk0(1.1, ssd.SupplyCart.get(p.getUniqueId())*3.5, p, le,5);
									Holding.holding(p, le, 10l);
								}
							}
			                }
			    	   }, count*3); 
		
					}
	            
				}
				
			}
	}
	

	
	@SuppressWarnings("deprecation")
	public void MassTreatment(PlayerSwapHandItemsEvent ev) 
	{
			Player p = ev.getPlayer();
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 61 && p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW && msstm.containsKey(p.getUniqueId())) {
			if(!p.isOnGround()&& !p.isSneaking())
			{
				ev.setCancelled(true);

            	if(msstmt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(msstmt.get(p.getUniqueId()));
            		msstmt.remove(p.getUniqueId());
            	}
				msstm.remove(p.getUniqueId());

	            final Location ptl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();


				
				for(int count = 0 ; count <5; count++) {
                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
	                	public void run() 
		                {	
         					p.playSound(ptl, Sound.BLOCK_CONDUIT_ACTIVATE, 0.5f, 2);
         					ptl.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, ptl.clone(), 1000, 7, 7, 7, new Particle.DustTransition(Color.RED, Color.GREEN, 3));
							p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 2, 0, false, false));
							ptl.getWorld().spawnParticle(Particle.COMPOSTER, ptl.clone(), 300, 7, 7, 7);
							ptl.getWorld().spawnParticle(Particle.SPELL, ptl.clone(), 300, 7, 7, 7);
         					
							for (Entity e : p.getWorld().getNearbyEntities(ptl, 7, 6, 7))
							{
			            		if (e instanceof Player) 
								{
			            			
									Player p1 = (Player) e;
									if(Party.hasParty(p) && Party.hasParty(p1))	{
									if(Party.getParty(p).equals(Party.getParty(p1)))
										{
											p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 2, 0, false, false));
											continue;
										}
									}
								}
			            		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
								{
									LivingEntity le = (LivingEntity)e;
									atk0(1.5, ssd.SupplyCart.get(p.getUniqueId())*7.1, p, le,5);
									
									Holding.holding(p, le, 10l);
								}
							}
			                }
			    	   }, count*35); 
		
					}
	            
			}
				
			}
	}
	
	
	
	
	@SuppressWarnings("deprecation")
	public void AED(PlayerInteractEvent ev) 
	{

		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 61 && ssd.AED.getOrDefault(p.getUniqueId(), 1) >=1 && !p.hasCooldown(Material.ARROW)) {
			if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK) && p.isSneaking())
			{	
				double sec =3*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				if(p.isSneaking())
				{
					if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
					{
					    if(cscooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
				        {
				            double timer = (cscooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
				            if(!(timer < 0)) // if timer is still more then 0 or 0
				            {
				            	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				            		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("자동제세동기 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
				            	}
				            	else {
				                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use AED").create());
				            	}
				            }
				            else // if timer is done
				            {
				            	cscooldown.remove(p.getName()); // removing player from HashMap

			                	if(fnndlt.containsKey(p.getUniqueId())) {
			                		Bukkit.getScheduler().cancelTask(fnndlt.get(p.getUniqueId()));
			                		fnndlt.remove(p.getUniqueId());
			                	}

			    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			    	                @Override
			    	                public void run() {
			    						if(Proficiency.getpro(p)>=1) {
			    							fnndl.putIfAbsent(p.getUniqueId(), 0);
			    						}
			    	                }
			    	            }, 3); 

			            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			    	                @Override
			    	                public void run() {
			    						fnndl.remove(p.getUniqueId());
			    	                }
			    	            }, 25); 
			                	fnndlt.put(p.getUniqueId(), task);
			                	
				            	
				            	Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation();
				        		
				            	p.getWorld().spawnParticle(Particle.FLASH,l,5,1,1,1);
								p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 2);
								
								for(Entity e: p.getWorld().getNearbyEntities(l,2.2,2.2,2.2)) {

			                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
										{
				                    		if (e instanceof Player) 
											{
												
												Player p1 = (Player) e;
												if(Party.hasParty(p) && Party.hasParty(p1) && aed.containsKey(p1.getUniqueId()))	{
													if(Party.getParty(p).equals(Party.getParty(p1)))
														{
											        		if(Proficiency.getpro(p)>=1) {
																p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
											        		}
															Bukkit.getScheduler().cancelTask(aedt.get(p1.getUniqueId()));
															aedt.remove(p1.getUniqueId());
															aed.remove(p1.getUniqueId());
						             						p1.setGlowing(false);
															p1.removePotionEffect(PotionEffectType.CONFUSION);
															p1.removePotionEffect(PotionEffectType.BLINDNESS);
															p1.removePotionEffect(PotionEffectType.SLOW);
															p1.removePotionEffect(PotionEffectType.SLOW_DIGGING);
															p1.removePotionEffect(PotionEffectType.WEAKNESS);
															p1.removePotionEffect(PotionEffectType.UNLUCK);
															p1.removePotionEffect(PotionEffectType.HUNGER);
															p1.setHealth(p1.getMaxHealth());
															p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 100, 100, false, false));
															if(p.getLocale().equalsIgnoreCase("ko_kr")) {
																p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.GREEN + "구조 성공!").create());
															}
															else {
																p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.GREEN + "Rescue Success!").create());
															}
															p.playNote(p.getLocation(), Instrument.CHIME, Note.natural(1, Tone.G));
															p.playNote(p.getLocation(), Instrument.BELL, Note.natural(1, Tone.C));
															continue;
														}
													}
											}
											LivingEntity le = (LivingEntity)e;
											atk0(0.3, ssd.AED.get(p.getUniqueId())*0.4, p, le,5);
				                			if(Proficiency.getpro(p)>=1) {
												Holding.superholding(p, le, 10l);
							        		}
				                			
										}
								}
				                cscooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				            }
				        }
				        else // if cooldown doesn't have players name in it
				        {

		                	if(fnndlt.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(fnndlt.get(p.getUniqueId()));
		                		fnndlt.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							fnndl.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						fnndl.remove(p.getUniqueId());
		    	                }
		    	            }, 25); 
		                	fnndlt.put(p.getUniqueId(), task);
		                	
				        	
				        	Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation();
			        		
			            	p.getWorld().spawnParticle(Particle.FLASH,l,5,1,1,1);
							p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 2);
							
							for(Entity e: p.getWorld().getNearbyEntities(l,2.2,2.2,2.2)) {

		                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
									{
			                    		if (e instanceof Player) 
										{
											
											Player p1 = (Player) e;
											if(Party.hasParty(p) && Party.hasParty(p1) && aed.containsKey(p1.getUniqueId()))	{
												if(Party.getParty(p).equals(Party.getParty(p1)))
													{
														if(Proficiency.getpro(p)>=1) {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
										        		}
														Bukkit.getScheduler().cancelTask(aedt.get(p1.getUniqueId()));
														aedt.remove(p1.getUniqueId());
														aed.remove(p1.getUniqueId());
					             						p1.setGlowing(false);
														p1.removePotionEffect(PotionEffectType.CONFUSION);
														p1.removePotionEffect(PotionEffectType.BLINDNESS);
														p1.removePotionEffect(PotionEffectType.SLOW);
														p1.removePotionEffect(PotionEffectType.SLOW_DIGGING);
														p1.removePotionEffect(PotionEffectType.WEAKNESS);
														p1.removePotionEffect(PotionEffectType.UNLUCK);
														p1.removePotionEffect(PotionEffectType.HUNGER);
														p1.setHealth(p1.getMaxHealth());
														p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 100, 100, false, false));
														if(p.getLocale().equalsIgnoreCase("ko_kr")) {
															p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.GREEN + "구조 성공!").create());
														}
														else {
															p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.GREEN + "Rescue Success!").create());
														}
														p.playNote(p.getLocation(), Instrument.CHIME, Note.natural(1, Tone.G));
														p.playNote(p.getLocation(), Instrument.BELL, Note.natural(1, Tone.C));
														continue;
													}
												}
										}
										LivingEntity le = (LivingEntity)e;
										atk0(0.3, ssd.AED.get(p.getUniqueId())*0.4, p, le,5);
			                			if(Proficiency.getpro(p)>=1) {
											Holding.superholding(p, le, 10l);
						        		}
			                			
									}
							}
				            cscooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        }
					}
						
				}
		}
		}
	}

	
	
	public void FineNeedles(PlayerInteractEvent ev) 
	{

		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 61&& fnndl.containsKey(p.getUniqueId())&& !p.hasCooldown(Material.ARROW)) {
			if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK) && p.isSneaking())
			{	
				if(p.isSneaking())
				{
					if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
					{

		            	if(fnndlt.containsKey(p.getUniqueId())) {
		            		Bukkit.getScheduler().cancelTask(fnndlt.get(p.getUniqueId()));
		            		fnndlt.remove(p.getUniqueId());
		            	}
						fnndl.remove(p.getUniqueId());
						


		            	if(utrlnbzt.containsKey(p.getUniqueId())) {
		            		Bukkit.getScheduler().cancelTask(utrlnbzt.get(p.getUniqueId()));
		            		utrlnbzt.remove(p.getUniqueId());
		            	}

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								if(Proficiency.getpro(p)>=2) {
									utrlnbz.putIfAbsent(p.getUniqueId(), 0);
								}
			                }
			            }, 3); 

		        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								utrlnbz.remove(p.getUniqueId());
			                }
			            }, 25); 
		            	utrlnbzt.put(p.getUniqueId(), task);
		            	

			        	Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
		        		
						p.playSound(p.getLocation(), Sound.ENTITY_PUFFER_FISH_STING, 1, 1.6f);
						
						for(Entity e: p.getWorld().getNearbyEntities(l,3.5,3.5,3.5)) {

	                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
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
									LivingEntity le = (LivingEntity)e;
		    						decayed.computeIfPresent(le.getUniqueId(), (k,v) -> v+0.03*ssd.ArrowClinic.get(p.getUniqueId()));			
		    						decayed.putIfAbsent(le.getUniqueId(), 0d);
		                        	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                        		@Override
		    		                	public void run() 
		    			                {	
		                        			decayed.computeIfPresent(le.getUniqueId(), (k,v) -> v-0.03*ssd.ArrowClinic.get(p.getUniqueId()));
		                 					if(decayed.getOrDefault(le.getUniqueId(),-1d)<0) {
		                 						decayed.remove(le.getUniqueId());
		                 					}
		                 				}
		    	                	}, 200);
	    		                    final Location pl = p.getEyeLocation().clone();
	    	                        Snowball ar = p.getWorld().spawn(pl, Snowball.class);
	    	                        ar.setShooter(p);
	    	                        ar.setVelocity(le.getEyeLocation().clone().toVector().subtract(pl.clone().toVector()).normalize().multiply(0.6));
	    	                        ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	    	                        ar.setItem(new ItemStack(Material.SPECTRAL_ARROW));
	    	                        ar.setRotation(pl.getYaw(), pl.getPitch());

									atk0(0.43, ssd.AED.get(p.getUniqueId())*0.4, p, le,5);
									Holding.superholding(p, le, 10l);
		                			
								}
						}
		            	
					}
				}
			}
		}
	}

	
	public void UltrasonicNebulizer(PlayerInteractEvent ev) 
	{

		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 61 && utrlnbz.containsKey(p.getUniqueId())&& !p.hasCooldown(Material.ARROW)) {
			if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK) && p.isSneaking())
			{	
				if(p.isSneaking())
				{
					if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
					{
						
		            	if(utrlnbzt.containsKey(p.getUniqueId())) {
		            		Bukkit.getScheduler().cancelTask(utrlnbzt.get(p.getUniqueId()));
		            		utrlnbzt.remove(p.getUniqueId());
		            	}
						utrlnbz.remove(p.getUniqueId());



	                	final Location tl = p.getEyeLocation().clone().add(0, -0.65, 0);
			        	Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();

						p.getWorld().spawnParticle(Particle.WHITE_ASH, tl.clone().add(tl.getDirection().normalize().multiply(1.2)), 200, 1,1,1,0.5);
						p.getWorld().spawnParticle(Particle.WHITE_ASH, tl.clone().add(tl.getDirection().normalize().multiply(2)), 400, 1.4,1.4,1.4,0.5);
						p.getWorld().spawnParticle(Particle.WHITE_ASH, tl.clone().add(tl.getDirection().normalize().multiply(3)), 600, 1.8,1.8,1.8,0.5);
						p.getWorld().spawnParticle(Particle.WHITE_ASH, tl.clone().add(tl.getDirection().normalize().multiply(4)), 800, 2.2,2.2,2.2,0.5);
						
						p.getWorld().spawnParticle(Particle.GLOW_SQUID_INK, tl.clone().add(tl.getDirection().normalize().multiply(1.2)), 200, 1,1,1,0.5);
						p.getWorld().spawnParticle(Particle.GLOW_SQUID_INK, tl.clone().add(tl.getDirection().normalize().multiply(2)), 400, 1.4,1.4,1.4,0.5);
						p.getWorld().spawnParticle(Particle.GLOW_SQUID_INK, tl.clone().add(tl.getDirection().normalize().multiply(3)), 600, 1.8,1.8,1.8,0.5);
						p.getWorld().spawnParticle(Particle.GLOW_SQUID_INK, tl.clone().add(tl.getDirection().normalize().multiply(4)), 800, 2.2,2.2,2.2,0.5);
						
						p.playSound(p.getLocation(), Sound.ITEM_INK_SAC_USE, 1, 2);
						p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE, 1, 2);
		            	p.getWorld().spawnParticle(Particle.BLOCK_DUST,l,200,3,3,3, Material.AMETHYST_BLOCK.createBlockData());

						p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));

						for(int count = 0 ; count <5; count++) {
		                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		         		@Override
			                	public void run() 
				                {	
									for(Entity e: p.getWorld().getNearbyEntities(l,4,4,4)) {
			
				                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
											{
					                    		if (e instanceof Player) 
												{
													
													Player p1 = (Player) e;
													if(Party.hasParty(p) && Party.hasParty(p1))	{
														if(Party.getParty(p).equals(Party.getParty(p1)))
															{
																p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
																continue;
															}
														}
												}
												LivingEntity le = (LivingEntity)e;
					    						decayed.computeIfPresent(le.getUniqueId(), (k,v) -> v+0.03*ssd.ArrowClinic.get(p.getUniqueId()));			
					    						decayed.putIfAbsent(le.getUniqueId(), 0d);
					                        	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                        		@Override
					    		                	public void run() 
					    			                {	
					                        			decayed.computeIfPresent(le.getUniqueId(), (k,v) -> v-0.03*ssd.ArrowClinic.get(p.getUniqueId()));
					                 					if(decayed.getOrDefault(le.getUniqueId(),-1d)<0) {
					                 						decayed.remove(le.getUniqueId());
					                 					}
					                 				}
					    	                	}, 200);
												atk0(0.35, ssd.AED.get(p.getUniqueId())*0.45, p, le,5);
												le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100, 100, false, false));
												Holding.superholding(p, le, 10l);
					                			
											}
									}
				                }
		                	}, count*10); 
				
						}
					}
				}
			}
		}
	}

	

	
	public void Stretcher(PlayerInteractEntityEvent ev) 
	{
			Player p = ev.getPlayer();
			Entity e = ev.getRightClicked();
		    if(e instanceof Player && p.isSneaking() && p.getInventory().getItemInMainHand().getType().isAir()){
		    	Player p1 = (Player) ev.getRightClicked();

				if(Party.hasParty(p) && Party.hasParty(p1))	{
					if(Party.getParty(p).equals(Party.getParty(p1)) && ClassData.pc.getOrDefault(p1.getUniqueId(), -1) == 61)
						{
							if(stretcht.containsKey(p.getUniqueId())) {
								Bukkit.getScheduler().cancelTask(stretcht.get(p.getUniqueId()));
							}
							int task  = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	                    		@Override
				                	public void run() 
					                {	
	                    				p1.addPassenger(p);
										p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20, 0, false, false));
		             				}
			                	}, 5,5);
							stretcht.put(p.getUniqueId(), task);
						}
					}
		    }
	}

	
	public void Stretcher(PlayerInteractEvent ev) 
	{
			Player p = (Player) ev.getPlayer();

			if(stretcht.containsKey(p.getUniqueId())) {
				p.leaveVehicle();
				Bukkit.getScheduler().cancelTask(stretcht.get(p.getUniqueId()));
				stretcht.remove(p.getUniqueId());
			}
	}

	
	public void Stretcher(PlayerQuitEvent ev) 
	{
		Player p = (Player) ev.getPlayer();
		if(stretcht.containsKey(p.getUniqueId())) {
			p.leaveVehicle();
			Bukkit.getScheduler().cancelTask(stretcht.get(p.getUniqueId()));
			stretcht.remove(p.getUniqueId());
		}
		if(!p.getPassengers().isEmpty()) {
			p.getPassengers().forEach(p1 -> {
				if(stretcht.containsKey(p1.getUniqueId())) {
					p1.leaveVehicle();
					Bukkit.getScheduler().cancelTask(stretcht.get(p1.getUniqueId()));
					stretcht.remove(p1.getUniqueId());
				}
			});
		}
    	if(winget.containsKey(p.getUniqueId())) {
        	Bukkit.getServer().getScheduler().cancelTask(winget.get(p.getUniqueId()));
        	winget.remove(p.getUniqueId());
    	}
    	if(wingt.containsKey(p.getUniqueId())) {
    		Bukkit.getScheduler().cancelTask(wingt.get(p.getUniqueId()));
    		wingt.remove(p.getUniqueId());
    	}
	}

	
	public void Stretcher(PlayerDeathEvent ev) 
	{
		Player p = (Player) ev.getEntity();
		if(stretcht.containsKey(p.getUniqueId())) {
			p.leaveVehicle();
			Bukkit.getScheduler().cancelTask(stretcht.get(p.getUniqueId()));
		}
		if(!p.getPassengers().isEmpty()) {
			p.getPassengers().forEach(p1 -> {
				if(stretcht.containsKey(p1.getUniqueId())) {
					p1.leaveVehicle();
					Bukkit.getScheduler().cancelTask(stretcht.get(p1.getUniqueId()));
					stretcht.remove(p1.getUniqueId());
				}
			});
		}
	}
	
	public void Stretcher(VehicleExitEvent ev) 
	{
			if(ev.getExited() instanceof Player) {
				Player p = (Player) ev.getExited();

				if(stretcht.containsKey(p.getUniqueId())) {
					p.leaveVehicle();
					Bukkit.getScheduler().cancelTask(stretcht.get(p.getUniqueId()));
					stretcht.remove(p.getUniqueId());
				}
			}
	}
	
	
	
	public void Grogi(EntityDamageEvent d) 
	{
		if(!(d.isCancelled())) {

			if(d.getEntity() instanceof Player&&!d.isCancelled()) 
			{
				Player p = (Player)d.getEntity();
				if((p.getHealth()-d.getFinalDamage())<=0 && !Wdcskills.Baron.containsKey(p.getUniqueId()) && !Wdcskills.res.containsKey(p) && !aed.containsKey(p.getUniqueId())&&!d.isCancelled()) {
				   
					if(Party.hasParty(p) &&!d.isCancelled())	{
						if(Party.getMembers(Party.getParty(p)).anyMatch(par -> ClassData.pc.get(Bukkit.getPlayer(par).getUniqueId())==61)&&!d.isCancelled())
						{
						    if(wrcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
					        {
					            double timer = (wrcooldown.get(p.getName())/1000d + 30*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024)) - System.currentTimeMillis()/1000d; // geting time in seconds
					            if(!(timer < 0)) // if timer is still more then 0 or 0
					            {
					            }
					            else // if timer is done
					            {
					            	wrcooldown.remove(p.getName()); // removing player from HashMap
					            	d.setCancelled(true);

					            	Party.getMembers(Party.getParty(p)).filter(par -> ClassData.pc.get(Bukkit.getPlayer(par).getUniqueId())==61).forEach(m -> {
					            		final Player med = Bukkit.getPlayer(m);
					            		if(med.getLocale().equalsIgnoreCase("ko_kr")) {
						            		med.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.RED + "환자 발생!").create());
					            		}
					            		else {
						            		med.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.RED + "Patient Occurrence!").create());
					            		}
					            		if(Proficiency.getpro(med)>=1) {
					            			p.teleport(med);
											p.playSound(p.getLocation(), Sound.ENTITY_MINECART_RIDING, 1, 2);
						                    Minecart supply = (Minecart) p.getWorld().spawnEntity(p.getLocation(), EntityType.MINECART);
						                    supply.setInvulnerable(true);
						                    supply.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						                    supply.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						                    supply.addPassenger(p);

					                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                    		@Override
							                	public void run() 
								                {
					             					supply.remove();
					             				}
						                	}, 100);
					            		}
										p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 3, false, false));
										p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 3, false, false));
										p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100, 3, false, false));
										p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 100, 3, false, false));
										p.playNote(p.getLocation(), Instrument.CHIME, Note.natural(1, Tone.A));
										p.playNote(p.getLocation(), Instrument.DIDGERIDOO, Note.natural(1, Tone.D));
					            	});

			                    	int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                    		@Override
					                	public void run() 
						                {	
											p.setHealth(1);
											p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 30, 100, false, false));
											p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 100, false, false));
											p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 100, false, false));
											p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 30, 100, false, false));
											p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 30, 100, false, false));
											p.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 30, 100, false, false));
											p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 30, 100, false, false));
											p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 30, 100, false, false));
											p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.2f, 0);
			             				}
				                	}, 10,10);
			                    	aedt.put(p.getUniqueId(), task);
			                    	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.DARK_GRAY +"빈사 상태").create());
			                    	}
			                    	else {
					                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.DARK_GRAY +"Groggy").create());
			                    	}
									aed.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
									aed.putIfAbsent(p.getUniqueId(), 0);

			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
			             					aed.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
			             					if(aed.containsKey(p.getUniqueId()) && aed.get(p.getUniqueId())<0) {
			             						aed.remove(p.getUniqueId());;
			    								p.setHealth(0);
			             					}
			             					if(aedt.containsKey(p.getUniqueId())) {
												Bukkit.getScheduler().cancelTask(aedt.get(p.getUniqueId()));
												aedt.remove(p.getUniqueId());
			             					}
			             				}
				                	}, 80);
					            	
					                wrcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
					            }
					        }
					        else // if cooldown doesn't have players name in it
					        {
				            	d.setCancelled(true);
				            	
				            	Party.getMembers(Party.getParty(p)).filter(par -> ClassData.pc.get(Bukkit.getPlayer(par).getUniqueId())==61).forEach(m -> {
				            		final Player med = Bukkit.getPlayer(m);
				            		if(med.getLocale().equalsIgnoreCase("ko_kr")) {
					            		med.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.RED + "환자 발생!").create());
				            		}
				            		else {
					            		med.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.RED + "Patient Occurrence!").create());
				            		}
				            		if(Proficiency.getpro(med)>=1) {
				            			p.teleport(med);
										p.playSound(p.getLocation(), Sound.ENTITY_MINECART_RIDING, 1, 2);
					                    Minecart supply = (Minecart) p.getWorld().spawnEntity(p.getLocation(), EntityType.MINECART);
					                    supply.setInvulnerable(true);
					                    supply.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					                    supply.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					                    supply.addPassenger(p);

				                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                    		@Override
						                	public void run() 
							                {
				             					supply.remove();
				             				}
					                	}, 100);
				            		}
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 3, false, false));
									p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 3, false, false));
									p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100, 3, false, false));
									p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 100, 3, false, false));
									p.playNote(p.getLocation(), Instrument.CHIME, Note.natural(1, Tone.A));
									p.playNote(p.getLocation(), Instrument.DIDGERIDOO, Note.natural(1, Tone.D));
				            	});

		                    	int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	                    		@Override
				                	public void run() 
					                {	
										p.setHealth(1);
										p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 30, 100, false, false));
										p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 100, false, false));
										p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 100, false, false));
										p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 30, 100, false, false));
										p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 30, 100, false, false));
										p.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 30, 100, false, false));
										p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 30, 100, false, false));
										p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 30, 100, false, false));
										p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.2f, 0);
		             				}
			                	}, 10,10);
		                    	aedt.put(p.getUniqueId(), task);
		                    	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.DARK_GRAY +"빈사 상태").create());
		                    	}
		                    	else {
				                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.DARK_GRAY +"Groggy").create());
		                    	}
								if(aed.containsKey(p.getUniqueId())) {
									aed.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
								}
								else {
									aed.put(p.getUniqueId(), 0);
								}

		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
		             					aed.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
		             					if(aed.containsKey(p.getUniqueId()) && aed.get(p.getUniqueId())<0) {
		             						aed.remove(p.getUniqueId());
		    								p.setHealth(0);
		             					}
		             					if(aedt.containsKey(p.getUniqueId())) {
											Bukkit.getScheduler().cancelTask(aedt.get(p.getUniqueId()));
											aedt.remove(p.getUniqueId());
		             					}
		             				}
			                	}, 80);
					            wrcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
					        }
						}
					}
				}
			}
		}
	}
	
	
	public void Grogi2(EntityDamageEvent d) 
	{
			if(d.getEntity() instanceof Player) 
			{
				Player p = (Player)d.getEntity();
				if(aed.containsKey(p.getUniqueId())) {
					d.setCancelled(true);
				}
				if(hideoutarmor.containsKey(p.getUniqueId())) {
					d.setDamage(d.getDamage()*0.3);
				}
			}
	}
	
	
	public void Hideout(PlayerToggleSneakEvent ev) 
	{
		
		Player p = (Player)ev.getPlayer();
        
		
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 61 && ssd.Hideout.getOrDefault(p.getUniqueId(),0)>=1)			{	
			
				if(ev.isSneaking()&& p.getInventory().getItemInMainHand().getType().name().contains("CROSSBOW"))
				{
					int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
							int r = Proficiency.getpro(p)>=2 ? 3:0;
							p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 4,3,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 4,0+r,false,false));
							if(Proficiency.getpro(p)>=1) {
								hideoutarmor.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
								hideoutarmor.putIfAbsent(p.getUniqueId(), 0);
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
		             					hideoutarmor.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
		             					if(hideoutarmor.getOrDefault(p,-1)<0) {
		             						hideoutarmor.remove(p.getUniqueId());
		             					}
		             				}
			                	}, 4);
							}
							for(Entity e: p.getWorld().getNearbyEntities(p.getLocation(),3+r,3+r,3+r)) {
								if (e instanceof Player) 
								{
									
									Player p1 = (Player) e;
									if(Party.hasParty(p) && Party.hasParty(p1))	{
										if(Party.getParty(p).equals(Party.getParty(p1)))
										{
											p1.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 4,3,false,false));
											p1.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 4,0+r,false,false));
											if(Proficiency.getpro(p)>=1) {
												hideoutarmor.computeIfPresent(p1.getUniqueId(), (k,v) -> v+1);
												hideoutarmor.putIfAbsent(p1.getUniqueId(), 0);
						                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						             		@Override
								                	public void run() 
									                {	
						             					hideoutarmor.computeIfPresent(p1.getUniqueId(), (k,v) -> v-1);
						             					if(hideoutarmor.getOrDefault(p1,-1)<0) {
						             						hideoutarmor.remove(p1.getUniqueId());
						             					}
						             				}
							                	}, 4);
											}
										}
									}
								}
							}
		                }
					}, 0, 3); 
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
			
	}
	
	public void Hideout(PlayerItemHeldEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 61 && p.getInventory().getItemInMainHand().getType().name().contains("CROSSBOW") && sz.containsKey(p)){

			Bukkit.getServer().getScheduler().cancelTask(sz.get(p));
			sz.remove(p);
		}
	}
	
	
	public void Grogi(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
		Projectile a = (Projectile) d.getDamager();
		if(a.getShooter() instanceof Player)
			{
		        

				
				Player p = (Player) a.getShooter();
				
				
				if(ClassData.pc.get(p.getUniqueId()) == 61) {
				if(aed.containsKey(p.getUniqueId())) {
					d.setCancelled(true);
				
				}
				}
			}
		}
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
			Player p = (Player) d.getDamager();
			if(aed.containsKey(p.getUniqueId())) {
				d.setCancelled(true);
			}
		}
	}

	
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();
        

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 61)
		{
			if(p.getInventory().getItemInMainHand().getType().name().equals("CROSSBOW"))
			{
					e.setCancelled(true);
			}
		}
		
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		final ItemStack is = it.getItemStack();
		final ItemMeta ism = is.getItemMeta();        
		
		

		if(ism.getDisplayName().contains("Inhancer") || ism.getDisplayName().contains("활력제") ) {
			ev.setCancelled(true);
			is.setAmount(0);
			return;
		}
			if(ClassData.pc.get(p.getUniqueId()) == 61 && (is.getType().name().equals("CROSSBOW")) && p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
				ev.setCancelled(true);
				p.setCooldown(Material.ARROW, 1);
				if(bultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (bultcooldown.get(p.getName())/1000d + 90/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d) ) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("카드세우스 탑 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
	                	}
	                	else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Caduceus Tower").create());
	                	}
			        }
	                else // if timer is done
	                {
	                    bultcooldown.remove(p.getName()); // removing player from HashMap
	                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
		            	HashSet<ArmorStand> remove = new HashSet<>();
	                    p.getWorld().spawnParticle(Particle.END_ROD, l.clone().add(0, 6, 0), 400, 4, 4, 4);
	                    for(int vi = -1; vi<4; vi+=1) {
		                    ArmorStand v1 = p.getWorld().spawn(l.clone().add(0, vi, 0), ArmorStand.class);
		                    v1.setCollidable(false);
		                    v1.setGravity(false);
		                    v1.setCanPickupItems(false);
		                    v1.setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		                    v1.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		                    v1.setBoots(new ItemStack(Material.IRON_BOOTS));
		                    v1.setMetadata("Caduceus of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		                    v1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		                    v1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		                    v1.setBasePlate(false);
		                    remove.add(v1);
	                    	
	                    }
	                    ArmorStand ball = p.getWorld().spawn(l.clone().add(0, 4, 0), ArmorStand.class);
	                    ball.setCollidable(false);
	                    ball.setInvisible(true);
	                    ball.setGravity(false);
	                    ball.setCanPickupItems(false);
	                    ball.setHelmet(new ItemStack(Material.BEACON));
	                    ball.setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
	                    ball.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
	                    ball.setBoots(new ItemStack(Material.IRON_BOOTS));
	                    ball.setMetadata("Caduceus of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    ball.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    ball.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    ball.setBasePlate(false);
		                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 1.0f, 2.0f);
	                    ArmorStand wing1 = p.getWorld().spawn(l.clone().add(1, 3, 0), ArmorStand.class);
	                    wing1.setCollidable(false);
	                    wing1.setInvisible(true);
	                    wing1.setGravity(false);
	                    wing1.setCanPickupItems(false);
	                    wing1.setHelmet(new ItemStack(Material.SMOOTH_QUARTZ));
	                    wing1.setChestplate(new ItemStack(Material.ELYTRA));
	                    wing1.setMetadata("Caduceus of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    wing1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    wing1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    wing1.setBasePlate(false);
	                    ArmorStand wing2 = p.getWorld().spawn(l.clone().add(-1, 3, 0), ArmorStand.class);
	                    wing2.setCollidable(false);
	                    wing2.setInvisible(true);
	                    wing2.setGravity(false);
	                    wing2.setCanPickupItems(false);
	                    wing2.setHelmet(new ItemStack(Material.SMOOTH_QUARTZ));
	                    wing2.setChestplate(new ItemStack(Material.ELYTRA));
	                    wing2.setMetadata("Caduceus of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    wing2.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    wing2.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    wing2.setBasePlate(false);
	                    ArmorStand wing3 = p.getWorld().spawn(l.clone().add(2, 3.5, 0), ArmorStand.class);
	                    wing3.setCollidable(false);
	                    wing3.setInvisible(true);
	                    wing3.setGravity(false);
	                    wing3.setCanPickupItems(false);
	                    wing3.setHelmet(new ItemStack(Material.SMOOTH_QUARTZ));
	                    wing3.setChestplate(new ItemStack(Material.ELYTRA));
	                    wing3.setMetadata("Caduceus of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    wing3.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    wing3.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    wing3.setBasePlate(false);
	                    ArmorStand wing4 = p.getWorld().spawn(l.clone().add(-2, 3.5, 0), ArmorStand.class);
	                    wing4.setCollidable(false);
	                    wing4.setInvisible(true);
	                    wing4.setGravity(false);
	                    wing4.setCanPickupItems(false);
	                    wing4.setHelmet(new ItemStack(Material.SMOOTH_QUARTZ));
	                    wing4.setChestplate(new ItemStack(Material.ELYTRA));
	                    wing4.setMetadata("Caduceus of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    wing4.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    wing4.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    wing4.setBasePlate(false);
	                    ArmorStand wing5 = p.getWorld().spawn(l.clone().add(3, 4, 0), ArmorStand.class);
	                    wing5.setCollidable(false);
	                    wing5.setInvisible(true);
	                    wing5.setGravity(false);
	                    wing5.setCanPickupItems(false);
	                    wing5.setHelmet(new ItemStack(Material.SMOOTH_QUARTZ));
	                    wing5.setChestplate(new ItemStack(Material.ELYTRA));
	                    wing5.setMetadata("Caduceus of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    wing5.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    wing5.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    wing5.setBasePlate(false);
	                    ArmorStand wing6 = p.getWorld().spawn(l.clone().add(-3, 4, 0), ArmorStand.class);
	                    wing6.setCollidable(false);
	                    wing6.setInvisible(true);
	                    wing6.setGravity(false);
	                    wing6.setCanPickupItems(false);
	                    wing6.setHelmet(new ItemStack(Material.SMOOTH_QUARTZ));
	                    wing6.setChestplate(new ItemStack(Material.ELYTRA));
	                    wing6.setMetadata("Caduceus of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    wing6.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    wing6.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    wing6.setBasePlate(false);
	                    remove.add(wing1);
	                    remove.add(wing2);
	                    remove.add(wing3);
	                    remove.add(wing4);
	                    remove.add(wing5);
	                    remove.add(wing6);
	                    remove.add(ball);
		                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 1.0f, 2.0f);
		                long taskt = (20-ssd.Medicine.get(p.getUniqueId())/3);
		                if(20-ssd.Medicine.get(p.getUniqueId())*0.3 <=0) {
		                	taskt = 1/10;
		                }
		                int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                    p.getWorld().spawnParticle(Particle.END_ROD, l.clone().add(0, 6, 0), 20, 4, 4, 4);
								p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1,0,false,false));
								p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 4,0,false,false));
								for(Entity e: p.getWorld().getNearbyEntities(p.getLocation(),5,5,5)) {
									if (e instanceof Player) 
									{
										
										Player p1 = (Player) e;
										if(Party.hasParty(p) && Party.hasParty(p1))	{
											if(Party.getParty(p).equals(Party.getParty(p1)))
											{
												p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1,0,false,false));
												p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
											}
										}
									}
								}
			                }
						}, 0, taskt); 
	                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                		@Override
		                	public void run() 
			                {	
	                			remove.forEach(r -> r.remove());
	    	        			Bukkit.getServer().getScheduler().cancelTask(task);
	         				}
	                	}, 120 + p.getLevel());
						bultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		                
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
	            	HashSet<ArmorStand> remove = new HashSet<>();
                    p.getWorld().spawnParticle(Particle.END_ROD, l.clone().add(0, 6, 0), 400, 4, 4, 4);
                    for(int vi = -1; vi<4; vi+=1) {
	                    ArmorStand v1 = p.getWorld().spawn(l.clone().add(0, vi, 0), ArmorStand.class);
	                    v1.setCollidable(false);
	                    v1.setGravity(false);
	                    v1.setCanPickupItems(false);
	                    v1.setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
	                    v1.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
	                    v1.setBoots(new ItemStack(Material.IRON_BOOTS));
	                    v1.setMetadata("Caduceus of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    v1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    v1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    v1.setBasePlate(false);
	                    remove.add(v1);
                    	
                    }
                    ArmorStand ball = p.getWorld().spawn(l.clone().add(0, 4, 0), ArmorStand.class);
                    ball.setCollidable(false);
                    ball.setInvisible(true);
                    ball.setGravity(false);
                    ball.setCanPickupItems(false);
                    ball.setHelmet(new ItemStack(Material.BEACON));
                    ball.setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                    ball.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                    ball.setBoots(new ItemStack(Material.IRON_BOOTS));
                    ball.setMetadata("Caduceus of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    ball.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    ball.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    ball.setBasePlate(false);
	                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 1.0f, 2.0f);
                    ArmorStand wing1 = p.getWorld().spawn(l.clone().add(1, 3, 0), ArmorStand.class);
                    wing1.setCollidable(false);
                    wing1.setInvisible(true);
                    wing1.setGravity(false);
                    wing1.setCanPickupItems(false);
                    wing1.setHelmet(new ItemStack(Material.SMOOTH_QUARTZ));
                    wing1.setChestplate(new ItemStack(Material.ELYTRA));
                    wing1.setMetadata("Caduceus of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    wing1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    wing1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    wing1.setBasePlate(false);
                    ArmorStand wing2 = p.getWorld().spawn(l.clone().add(-1, 3, 0), ArmorStand.class);
                    wing2.setCollidable(false);
                    wing2.setInvisible(true);
                    wing2.setGravity(false);
                    wing2.setCanPickupItems(false);
                    wing2.setHelmet(new ItemStack(Material.SMOOTH_QUARTZ));
                    wing2.setChestplate(new ItemStack(Material.ELYTRA));
                    wing2.setMetadata("Caduceus of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    wing2.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    wing2.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    wing2.setBasePlate(false);
                    ArmorStand wing3 = p.getWorld().spawn(l.clone().add(2, 3.5, 0), ArmorStand.class);
                    wing3.setCollidable(false);
                    wing3.setInvisible(true);
                    wing3.setGravity(false);
                    wing3.setCanPickupItems(false);
                    wing3.setHelmet(new ItemStack(Material.SMOOTH_QUARTZ));
                    wing3.setChestplate(new ItemStack(Material.ELYTRA));
                    wing3.setMetadata("Caduceus of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    wing3.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    wing3.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    wing3.setBasePlate(false);
                    ArmorStand wing4 = p.getWorld().spawn(l.clone().add(-2, 3.5, 0), ArmorStand.class);
                    wing4.setCollidable(false);
                    wing4.setInvisible(true);
                    wing4.setGravity(false);
                    wing4.setCanPickupItems(false);
                    wing4.setHelmet(new ItemStack(Material.SMOOTH_QUARTZ));
                    wing4.setChestplate(new ItemStack(Material.ELYTRA));
                    wing4.setMetadata("Caduceus of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    wing4.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    wing4.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    wing4.setBasePlate(false);
                    ArmorStand wing5 = p.getWorld().spawn(l.clone().add(3, 4, 0), ArmorStand.class);
                    wing5.setCollidable(false);
                    wing5.setInvisible(true);
                    wing5.setGravity(false);
                    wing5.setCanPickupItems(false);
                    wing5.setHelmet(new ItemStack(Material.SMOOTH_QUARTZ));
                    wing5.setChestplate(new ItemStack(Material.ELYTRA));
                    wing5.setMetadata("Caduceus of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    wing5.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    wing5.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    wing5.setBasePlate(false);
                    ArmorStand wing6 = p.getWorld().spawn(l.clone().add(-3, 4, 0), ArmorStand.class);
                    wing6.setCollidable(false);
                    wing6.setInvisible(true);
                    wing6.setGravity(false);
                    wing6.setCanPickupItems(false);
                    wing6.setHelmet(new ItemStack(Material.SMOOTH_QUARTZ));
                    wing6.setChestplate(new ItemStack(Material.ELYTRA));
                    wing6.setMetadata("Caduceus of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    wing6.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    wing6.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    wing6.setBasePlate(false);
                    remove.add(wing1);
                    remove.add(wing2);
                    remove.add(wing3);
                    remove.add(wing4);
                    remove.add(wing5);
                    remove.add(wing6);
                    remove.add(ball);
	                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 1.0f, 2.0f);
	                long taskt = (20-ssd.Medicine.get(p.getUniqueId())/3);
	                if(20-ssd.Medicine.get(p.getUniqueId())*0.3 <=0) {
	                	taskt = 1/10;
	                }
	                int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                    p.getWorld().spawnParticle(Particle.END_ROD, l.clone().add(0, 6, 0), 20, 4, 4, 4);
							p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1,0,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 4,0,false,false));
							for(Entity e: p.getWorld().getNearbyEntities(p.getLocation(),5,5,5)) {
								if (e instanceof Player) 
								{
									
									Player p1 = (Player) e;
									if(Party.hasParty(p) && Party.hasParty(p1))	{
										if(Party.getParty(p).equals(Party.getParty(p1)))
										{
											p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1,0,false,false));
											p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
										}
									}
								}
							}
		                }
					}, 0, taskt); 
                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                		@Override
	                	public void run() 
		                {	
                			remove.forEach(r -> r.remove());
    	        			Bukkit.getServer().getScheduler().cancelTask(task);
         				}
                	}, 120 + p.getLevel());
					bultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
					
	                
	            }
			}	
			
			
	}
	


	
	public void ULT2(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
        
		
		
		
			
			if(ClassData.pc.get(p.getUniqueId()) == 61 && (is.getType().name().equals("CROSSBOW")) && !p.isSneaking()&& p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
				p.setCooldown(Material.ARROW, 1);
				if(bult2cooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (bult2cooldown.get(p.getName())/1000d + 60*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d) ) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("날개슈트 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
	                	}
	                	else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Wing Suit").create());
	                	}
			        }
	                else // if timer is done
	                {
	                    bult2cooldown.remove(p.getName()); // removing player from HashMap
	                    
	                    wing.put(p.getUniqueId(), 2);
		                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1.0f, 0f);
		                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1.0f, 2f);
		                
		            	if(wingt.containsKey(p.getUniqueId())) {
		            		Bukkit.getScheduler().cancelTask(wingt.get(p.getUniqueId()));
		            		wingt.remove(p.getUniqueId());
		            	}
		            	
						int task1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
				                p.playSound(p.getLocation(), Sound.ENTITY_GLOW_SQUID_AMBIENT, 0.1f, 2f);
			                	p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getLocation(),10,1,1,1,1);
			                	p.getWorld().spawnParticle(Particle.GLOW, p.getLocation(),10,1,1,1,1);
			                }
			            }, 0,3);
						winget.put(p.getUniqueId(), task1);

						int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	if(winget.containsKey(p.getUniqueId())) {
				                	Bukkit.getServer().getScheduler().cancelTask(winget.get(p.getUniqueId()));
				                	winget.remove(p.getUniqueId());
			                	}
								wing.remove(p.getUniqueId());
								p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 200,0,false,false));
			                }
			            }, 500);
						wingt.put(p.getUniqueId(), task);
						
						
						Holding.fly(p, 500l);
						
						bult2cooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		                
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    wing.put(p.getUniqueId(), 2);
	                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1.0f, 0f);
	                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1.0f, 2f);
	                
	            	if(wingt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(wingt.get(p.getUniqueId()));
	            		wingt.remove(p.getUniqueId());
	            	}
	            	
					int task1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
			                p.playSound(p.getLocation(), Sound.ENTITY_GLOW_SQUID_AMBIENT, 0.1f, 2f);
		                	p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getLocation(),10,1,1,1,1);
		                	p.getWorld().spawnParticle(Particle.GLOW, p.getLocation(),10,1,1,1,1);
		                }
		            }, 0,3);
					winget.put(p.getUniqueId(), task1);

					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	if(winget.containsKey(p.getUniqueId())) {
			                	Bukkit.getServer().getScheduler().cancelTask(winget.get(p.getUniqueId()));
			                	winget.remove(p.getUniqueId());
		                	}
							wing.remove(p.getUniqueId());
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 200,0,false,false));
		                }
		            }, 500);
					wingt.put(p.getUniqueId(), task);
					
					
					Holding.fly(p, 500l);
					
					bult2cooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}	
			
			
	}
	

	
	@SuppressWarnings("deprecation")
	public void ThrowCancel(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
		
			if(ClassData.pc.get(p.getUniqueId()) == 61 && (is.getType().name().contains("CROSSBOW")) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
			}
	
    }
	
	
	public void Damagegetter(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
		Projectile a = (Projectile)d.getDamager();
			if(a.getShooter() instanceof Player) {
			Player p = (Player)a.getShooter();
			LivingEntity le = (LivingEntity) d.getEntity();
				
				
				
				if(ClassData.pc.get(p.getUniqueId()) == 61) {
						if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
						{
							if(d.getDamage()>0) {
								dset2(d, p, 1d, le, 14);
								d.setDamage(d.getDamage()+ssd.Medicine.get(p.getUniqueId())*0.65);
								if(wing.containsKey(p.getUniqueId())) {
									dset1(d, p, 1.0, 0.007);
								}
							}
						}
				}
			}
		}
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
			{
			Player p = (Player) d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 61) {
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
				{
					if(d.getDamage()>0) {
						dset2(d, p, 1d, le, 14);
						d.setDamage(d.getDamage()+ssd.Medicine.get(p.getUniqueId())*0.65);
						if(wing.containsKey(p.getUniqueId())) {
							dset1(d, p, 1.0, 0.007);
						}
					}
				}
				}
			}
		}
		
	}
}



