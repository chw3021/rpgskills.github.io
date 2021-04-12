package io.github.chw3021.medic;




import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import io.github.chw3021.classes.ClassData;
import io.github.chw3021.party.PartyData;
import io.github.chw3021.rmain.RMain;
import io.github.chw3021.witchdoctor.Wdcskills;
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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Medskills implements Serializable, Listener {
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
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private HashMap<Player, Arrow> arrow = new HashMap<Player, Arrow>();
	private HashMap<Projectile, Player> shooter = new HashMap<Projectile, Player>();
	private static HashMap<Player, Integer> aed = new HashMap<Player, Integer>();
	private static HashMap<Player, Integer> aedt = new HashMap<Player, Integer>();
	private static HashMap<LivingEntity, Double> decayed = new HashMap<LivingEntity, Double>();
	private HashMap<Player, Integer> sz = new HashMap<Player, Integer>();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private MedSkillsData ssd = new MedSkillsData(MedSkillsData.loadData(path +"/plugins/RPGskills/MedskillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		MedSkillsData s = new MedSkillsData(MedSkillsData.loadData(path +"/plugins/RPGskills/MedskillsData.data"));
		ssd =s;
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
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Medskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				MedSkillsData s = new MedSkillsData(MedSkillsData.loadData(path +"/plugins/RPGskills/MedskillsData.data"));
				ssd =s;
			}
			
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		MedSkillsData s = new MedSkillsData(MedSkillsData.loadData(path +"/plugins/RPGskills/MedskillsData.data"));
		ssd =s;
		
	}
	
	@EventHandler
	public void RemedyingRocket(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
        
		
		
		if(playerclass.get(p.getUniqueId()) == 61) {	
			if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK) && !p.isSneaking())
			{	
				new MedSkillsData(MedSkillsData.loadData(path +"/plugins/RPGskills/MedskillsData.data"));
				
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
					ev.setCancelled(true);
					List<ItemStack> arrows = new ArrayList<ItemStack>();
					ItemStack heal = new ItemStack(Material.FIREWORK_ROCKET);
					ItemStack cb = p.getInventory().getItemInMainHand();
					ItemStack arcon = new ItemStack(Material.ARROW,1);
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
					CrossbowMeta cbm = (CrossbowMeta) cb.getItemMeta();
					if(!cbm.hasChargedProjectiles()) {
							arrows.add(heal);
							cbm.setChargedProjectiles(arrows);
							cb.setItemMeta(cbm);
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA + "Remedying Rocket Loaded").create());
					}
					else if(cbm.hasChargedProjectiles()) {
						cbm.setChargedProjectiles(null);
						cb.setItemMeta(cbm);
					}
				}
			}
		}
	}


	@EventHandler
	public void RemedyingRocket(EntityShootBowEvent ev) 
	{
		
		if(ev.getEntity() instanceof Player)
		{
			Player p = (Player) ev.getEntity();
			int sec = 10;
	        
			
			
			if(playerclass.get(p.getUniqueId()) == 61) {
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
					if(!player_damage.containsKey(p.getName()))
					{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.PIERCING)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName()) + p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
					}
					if(ev.getProjectile().getType() == EntityType.FIREWORK)
					{
						Firework ar = (Firework) ev.getProjectile();
						if(ar.getFireworkMeta().getDisplayName().equals("Remedying Rocket")) {
							if(rrcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
				            {
			                    
				                long timer = (rrcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
				                if(!(timer < 0)) // if timer is still more then 0 or 0
				                {
				                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to shoot RemedyingRocket").create());
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

	@EventHandler
	public void RemedyingRocket(FireworkExplodeEvent f) 
	{
		if(f.getEntity().getShooter() instanceof Player && f.getEntity().hasMetadata("Remedying Rocket")) {
			Firework fr = f.getEntity();
			Player p = (Player) f.getEntity().getShooter();
			Location frl = fr.getLocation();
	        
			
			fr.getWorld().spawnParticle(Particle.EXPLOSION_LARGE,frl, 1,1,1,1);
			fr.getWorld().playSound(frl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
			fr.remove();
			for(Entity e : frl.getWorld().getNearbyEntities(frl, 4,4, 4)) {
        		if (e instanceof Player&& e!=p) 
				{
					Player p1 = (Player) e;
					if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
						if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
							{
								p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1, 0, false, false));
								if(p1.hasPotionEffect(PotionEffectType.REGENERATION)) {
									p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200+p1.getPotionEffect(PotionEffectType.REGENERATION).getDuration(), 1+p1.getPotionEffect(PotionEffectType.REGENERATION).getAmplifier(), false, false));
								}
								else {
									p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1, false, false));
								}
								if(p1.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
									p1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200+p1.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getDuration(), p1.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getAmplifier(), false, false));
								}
								else {
									p1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 0, false, false));
								}
							}
						}
				}
        		if (e==p) 
				{
					p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1, 0, false, false));
					if(p.hasPotionEffect(PotionEffectType.REGENERATION)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200+p.getPotionEffect(PotionEffectType.REGENERATION).getDuration(), 1+p.getPotionEffect(PotionEffectType.REGENERATION).getAmplifier(), false, false));
					}
					else {
						p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1, false, false));
					}
					if(p.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200+p.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getDuration(), p.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getAmplifier(), false, false));
					}
					else {
						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 0, false, false));
					}			
				}
				if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))) {
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
						enar.setDamage(player_damage.get(p.getName())*0.2+ssd.RemedyingRocket.get(p.getUniqueId())*0.16);								
					}
					p.setSprinting(true);
					le.damage(0, p);
					le.damage(player_damage.get(p.getName())*0.2+ssd.RemedyingRocket.get(p.getUniqueId())*0.16, p);
					p.setSprinting(false);
				}
			}
		}
	}

	@EventHandler
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
	@EventHandler
	public void HealingArrow(ProjectileHitEvent ev) 
	{
		
		if(ev.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)ev.getEntity().getShooter();
		    
			
			
			
			if(playerclass.get(p.getUniqueId()) == 61) {
				if(ev.getHitEntity()!=null && ev.getEntity() instanceof Arrow) {
					Entity e =ev.getHitEntity();
					{
                		if (e instanceof Player) 
						{
                			new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
							Player p1 = (Player) e;
							if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
							if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
								{
									if(p1.getHealth()+p1.getMaxHealth()*(0.05+ssd.ArrowClinic.get(p.getUniqueId())*0.005+ssd.Medicine.get(p.getUniqueId())*0.004)>=p1.getMaxHealth()) {
										p1.setHealth(p1.getMaxHealth());
										p1.playSound(p1.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 2);
									}
									else {
										p1.setHealth(p1.getHealth() + p1.getMaxHealth()*(0.05+ssd.ArrowClinic.get(p.getUniqueId())*0.005+ssd.Medicine.get(p.getUniqueId())*0.004));
										p1.playSound(p1.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 2);
									}
								}
							}
						}
					}
				}
			}			
		}
	}
	@EventHandler
	public void Arrowshootmanagement(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
		Projectile a = (Projectile) d.getDamager();
		LivingEntity e = (LivingEntity)d.getEntity();
		if(a.getShooter() instanceof Player)
			{
			Player p = (Player) a.getShooter();
			e.getLocation();
	        
			
			
			if(playerclass.get(p.getUniqueId()) == 61)			
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
					player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.ARROW_DAMAGE)*0.5 + p.getLevel()/10);
				}
			}
			}
		}
	}
	@EventHandler
	public void swcancle(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
        
		
		
		if(playerclass.get(p.getUniqueId()) == 61) {
		if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
		{
        	ev.setCancelled(true);
		}
		}
	}
	
	@EventHandler
	public void Decontamination(PlayerSwapHandItemsEvent ev) 
	{
			Player p = ev.getPlayer();
			int sec = 16;
	        
			
			
			if(playerclass.get(p.getUniqueId()) == 61) {
			if(((p.isSneaking()) && !(p.isSprinting())))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
		        	ev.setCancelled(true);
					new MedSkillsData(MedSkillsData.loadData(path +"/plugins/RPGskills/MedskillsData.data"));
					if(arcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
	                    
		                long timer = (arcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Decontamination").create());
		                }
		                else // if timer is done
		                {
		                    arcooldown.remove(p.getName()); // removing player from HashMap
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

	@EventHandler
	public void Throw(ProjectileHitEvent ev) 
	{
		
		if(ev.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)ev.getEntity().getShooter();
		    
			
			
			
			if(playerclass.get(p.getUniqueId()) == 61 && ev.getEntity().hasMetadata("Decon of"+p.getName())) {
				if(ev.getHitEntity()!=null) {
					for(int count = 0 ; count <11; count++) {
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
		                	public void run() 
			                {	
			                    AreaEffectCloud cloud = (AreaEffectCloud) p.getWorld().spawnEntity(ev.getHitEntity().getLocation(), EntityType.AREA_EFFECT_CLOUD);
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
								for (Entity e : p.getWorld().getNearbyEntities(ev.getHitEntity().getLocation(), 5, 5, 5))
								{
			                		if (e instanceof Player) 
									{
			                			new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
										Player p1 = (Player) e;
										if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
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
													enar.setDamage(player_damage.get(p.getName())*0.15+ssd.Decontamination.get(p.getUniqueId())*0.16);
												}	
												p.setSprinting(true);
							                    le.damage(0,p);
							                    le.damage(player_damage.get(p.getName())*0.15+ssd.Decontamination.get(p.getUniqueId())*0.16,p);
												p.setSprinting(false);
											}
									}
								}
				                }
	                	   }, count*3); 
						
					}
				}
				else if(ev.getHitBlock()!=null) {
					for(int count = 0 ; count <11; count++) {
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
		                	public void run() 
			                {	
			                    AreaEffectCloud cloud = (AreaEffectCloud) p.getWorld().spawnEntity(ev.getHitBlock().getLocation(), EntityType.AREA_EFFECT_CLOUD);
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
								for (Entity e : p.getWorld().getNearbyEntities(ev.getHitBlock().getLocation(), 5, 5, 5))
								{
			                		if (e instanceof Player) 
									{
			                			new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
										Player p1 = (Player) e;
										if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
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
													enar.setDamage(player_damage.get(p.getName())*0.15+ssd.Decontamination.get(p.getUniqueId())*0.16);
												}	
												p.setSprinting(true);
							                    le.damage(0,p);
							                    le.damage(player_damage.get(p.getName())*0.15+ssd.Decontamination.get(p.getUniqueId())*0.16,p);
												p.setSprinting(false);
											}
									}
								}
				                }
	                	   }, count*3); 
						
					}
				}
			}
			
		}
	}

	
	@EventHandler
	public void SupplyCart(PlayerSwapHandItemsEvent ev) 
	{
			Player p = ev.getPlayer();
            Location ptl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
			int sec = 30;
	        
			
			
			if(playerclass.get(p.getUniqueId()) == 61 && ptl.getBlock().isPassable()) {
			if(!p.isOnGround()&& !p.isSneaking())
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
					new MedSkillsData(MedSkillsData.loadData(path +"/plugins/RPGskills/MedskillsData.data"));
					if(dpcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {

						ev.setCancelled(true);
		                double timer = ((dpcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000); // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + (int)timer + " seconds to use SupplyCart").create());
		                }
		                else // if timer is done
		                {
		                    dpcooldown.remove(p.getName()); // removing player from HashMap
		                    p.playSound(ptl, Sound.ENTITY_MINECART_RIDING, 1, 2);
		                    Location cl = p.getEyeLocation().add(0, 1, 0);
		                    Minecart supply = (Minecart) p.getWorld().spawnEntity(cl, EntityType.MINECART_CHEST);
		                    supply.setInvulnerable(true);
		                    supply.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		                    supply.setFlyingVelocityMod(ptl.toVector().subtract(cl.toVector()).normalize().multiply(0.6));
							ItemStack pot = new ItemStack(Material.POTION);
							PotionMeta potm = (PotionMeta) pot.getItemMeta();
							potm.setColor(Color.FUCHSIA);
							potm.setDisplayName("Inhancer");
							potm.setLocalizedName(p.getName());
							potm.setBasePotionData(new PotionData(PotionType.UNCRAFTABLE));
							pot.setItemMeta(potm);
							p.getInventory().addItem(pot);
		                    p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
		                    for (Entity e : supply.getNearbyEntities(6, 6, 6))
							{
	                    		if (e instanceof Player && e!=p) 
								{
									new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
									Player p1 = (Player) e;
									if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
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
	                    p.playSound(ptl, Sound.ENTITY_MINECART_RIDING, 1, 2);
	                    Location cl = p.getEyeLocation().add(0, 1, 0);
	                    Minecart supply = (Minecart) p.getWorld().spawnEntity(cl, EntityType.MINECART_CHEST);
	                    supply.setInvulnerable(true);
	                    supply.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    supply.setFlyingVelocityMod(ptl.toVector().subtract(cl.toVector()).normalize().multiply(0.6));
						ItemStack pot = new ItemStack(Material.POTION);
						PotionMeta potm = (PotionMeta) pot.getItemMeta();
						potm.setColor(Color.FUCHSIA);
						potm.setDisplayName("Inhancer");
						potm.setLocalizedName(p.getName());
						potm.setBasePotionData(new PotionData(PotionType.UNCRAFTABLE));
						pot.setItemMeta(potm);
						p.getInventory().addItem(pot);
	                    p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
	                    for (Entity e : supply.getNearbyEntities(6, 6, 6))
						{
                    		if (e instanceof Player && e!=p) 
							{
								new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
								Player p1 = (Player) e;
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
									if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
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
	}
	
	@EventHandler
	public void Inhancer(PlayerItemConsumeEvent d) 
	{
		if(d.getItem().getItemMeta().getDisplayName().equals("Inhancer") && Bukkit.getPlayerExact(d.getItem().getItemMeta().getLocalizedName())!=null) {
			ItemStack b = new ItemStack(Material.GLASS_BOTTLE,1);
			
			Player p = Bukkit.getPlayerExact(d.getItem().getItemMeta().getLocalizedName());
			if(p.hasPotionEffect(PotionEffectType.JUMP)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 50+50*ssd.SupplyCart.get(p.getUniqueId())+p.getPotionEffect(PotionEffectType.JUMP).getDuration(), 1+p.getPotionEffect(PotionEffectType.JUMP).getAmplifier(), false, false));
			}
			else {
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 50+50*ssd.SupplyCart.get(p.getUniqueId()), 1, false, false));
			}
			if(p.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 50+50*ssd.SupplyCart.get(p.getUniqueId())+p.getPotionEffect(PotionEffectType.FAST_DIGGING).getDuration(), 1+ssd.SupplyCart.get(p.getUniqueId())/2+p.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier(), false, false));
			}
			else {
				p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 50+50*ssd.SupplyCart.get(p.getUniqueId()), 1+ssd.SupplyCart.get(p.getUniqueId())/2, false, false));
			}
			if(p.hasPotionEffect(PotionEffectType.SPEED)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 50+50*ssd.SupplyCart.get(p.getUniqueId())+p.getPotionEffect(PotionEffectType.SPEED).getDuration(), 1+p.getPotionEffect(PotionEffectType.SPEED).getAmplifier(), false, false));
			}
			else {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 50+50*ssd.SupplyCart.get(p.getUniqueId()), 1, false, false));
			}
			if(p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 50+50*ssd.SupplyCart.get(p.getUniqueId())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getDuration(), 1+ssd.SupplyCart.get(p.getUniqueId())/2+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier(), false, false));
			}
			else {
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 50+50*ssd.SupplyCart.get(p.getUniqueId()), 1+ssd.SupplyCart.get(p.getUniqueId())/2, false, false));
			}
        	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
 		@Override
            	public void run() 
                {	
 					d.getPlayer().getInventory().removeItem(b);
 				}
        	}, 1);
		}
	}
	
	
	@EventHandler
	public void AED(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 5;
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
		
		
		
		if(playerclass.get(p.getUniqueId()) == 61) {
				if(p.isSneaking() && !(p.isSprinting()))
				{
					if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
					{
					    if(cscooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
				        {
				            long timer = (cscooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
				            if(!(timer < 0)) // if timer is still more then 0 or 0
				            {
			                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use AED").create());
				            }
				            else // if timer is done
				            {
				            	cscooldown.remove(p.getName()); // removing player from HashMap
				            	p.getWorld().spawnParticle(Particle.FLASH, le.getLocation(),2);
								p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 2);
								if (le instanceof Player) 
								{
									new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
									Player p1 = (Player) le;
									if(PartyData.hasParty(p) && PartyData.hasParty(p1) && aed.containsKey(p1))	{
									if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
										{
											Bukkit.getScheduler().cancelTask(aedt.get(p1));
											aedt.remove(p1);
											aed.remove(p1);
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
										}
									}
								}
		                		if(!(le.hasMetadata("fake")) && le!=p) {
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
										enar.setDamage(player_damage.get(p.getName())*0.3+ssd.AED.get(p.getUniqueId())*0.3);
									}		 
		                			p.setSprinting(true);
		                			le.damage(0,p);
		                			le.damage(player_damage.get(p.getName())*0.3+ssd.AED.get(p.getUniqueId())*0.3, p);
		                			p.setSprinting(false);
		                		}
				                cscooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				            }
				        }
				        else // if cooldown doesn't have players name in it
				        {
				        	p.getWorld().spawnParticle(Particle.FLASH, le.getLocation(),2);
							p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 2);
							if (le instanceof Player) 
							{
								new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
								Player p1 = (Player) le;
								if(PartyData.hasParty(p) && PartyData.hasParty(p1) && aed.containsKey(p1))	{
								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
									{
										Bukkit.getScheduler().cancelTask(aedt.get(p1));
										aedt.remove(p1);
										aed.remove(p1);
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
									}
								}
							}
	                		if(!(le.hasMetadata("fake")) && le!=p) {
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
									enar.setDamage(player_damage.get(p.getName())*0.3+ssd.AED.get(p.getUniqueId())*0.3);
								}		 
	                			p.setSprinting(true);
	                			le.damage(0,p);
	                			le.damage(player_damage.get(p.getName())*0.3+ssd.AED.get(p.getUniqueId())*0.3, p);
	                			p.setSprinting(false);
	                		}
				            cscooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        }
					}
						
				}
		}
		}
	}
	

	@EventHandler
	public void Grogi(EntityDamageEvent d) 
	{
		if(!(d.isCancelled())) {

			int sec = 30;
		    
			
			
			Wdcskills ws = Wdcskills.getInstance();
			if(d.getEntity() instanceof Player&&!d.isCancelled()) 
			{
				Player p = (Player)d.getEntity();
				if((p.getHealth()-d.getFinalDamage())<=0 && !ws.Baron.containsKey(p) && !ws.res.containsKey(p) && !aed.containsKey(p)&&!d.isCancelled()) {
				   
					if(PartyData.hasParty(p) &&!d.isCancelled())	{
						if(PartyData.getMembers(PartyData.getParty(p)).anyMatch(par -> playerclass.get(Bukkit.getPlayer(par).getUniqueId())==61)&&!d.isCancelled())
						{
						    if(wrcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
					        {
					            long timer = (wrcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
					            if(!(timer < 0)) // if timer is still more then 0 or 0
					            {
					            }
					            else // if timer is done
					            {
					            	wrcooldown.remove(p.getName()); // removing player from HashMap

					            	PartyData.getMembers(PartyData.getParty(p)).filter(par -> playerclass.get(Bukkit.getPlayer(par).getUniqueId())==61).forEach(m -> {
					            		Bukkit.getPlayer(m).spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.RED + "Patient Occurrence!").create());
										p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 3, false, false));
										p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 3, false, false));
										p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100, 3, false, false));
										p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 100, 3, false, false));
										p.playNote(p.getLocation(), Instrument.CHIME, Note.natural(1, Tone.D));
										p.playNote(p.getLocation(), Instrument.DIDGERIDOO, Note.natural(1, Tone.D));
					            	});
					            	d.setCancelled(true);

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
			                    	aedt.put(p, task);
				                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.DARK_GRAY +"Groggy").create());
									if(aed.containsKey(p)) {
										aed.computeIfPresent(p, (k,v) -> v+1);
									}
									else {
										aed.put(p, 0);
									}

			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
			             					aed.computeIfPresent(p, (k,v) -> v-1);
			             					if(aed.containsKey(p) && aed.get(p)<0) {
			             						aed.remove(p);;
			    								p.setHealth(0);
			             					}
			             					if(aedt.containsKey(p)) {
												Bukkit.getScheduler().cancelTask(aedt.get(p));
												aedt.remove(p);
			             					}
			             				}
				                	}, 80);
					            	
					                wrcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
					            }
					        }
					        else // if cooldown doesn't have players name in it
					        {
				            	PartyData.getMembers(PartyData.getParty(p)).filter(par -> playerclass.get(Bukkit.getPlayer(par).getUniqueId())==61).forEach(m -> {
				            		Bukkit.getPlayer(m).spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.RED + "Patient Occurrence!").create());
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 3, false, false));
									p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 3, false, false));
									p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100, 3, false, false));
									p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 100, 3, false, false));
									p.playNote(p.getLocation(), Instrument.CHIME, Note.natural(1, Tone.D));
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
		                    	aedt.put(p, task);
			                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.DARK_GRAY +"Groggy").create());
								if(aed.containsKey(p)) {
									aed.computeIfPresent(p, (k,v) -> v+1);
								}
								else {
									aed.put(p, 0);
								}

		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
		             					aed.computeIfPresent(p, (k,v) -> v-1);
		             					if(aed.containsKey(p) && aed.get(p)<0) {
		             						aed.remove(p);
		    								p.setHealth(0);
		             					}
		             					if(aedt.containsKey(p)) {
											Bukkit.getScheduler().cancelTask(aedt.get(p));
											aedt.remove(p);
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
	
	@EventHandler
	public void Grogi2(EntityDamageEvent d) 
	{
			if(d.getEntity() instanceof Player) 
			{
				Player p = (Player)d.getEntity();
				if(aed.containsKey(p)) {
					d.setCancelled(true);
				}
			}
	}
	
	@EventHandler
	public void Hideout(PlayerToggleSneakEvent ev) 
	{
		
		Player p = (Player)ev.getPlayer();
		p.getLocation();
        
		
		
		
		if(playerclass.get(p.getUniqueId()) == 61 && ssd.Hideout.get(p.getUniqueId())>=1)			{	
			new MedSkillsData(MedSkillsData.loadData(path +"/plugins/RPGskills/MedskillsData.data"));
			if(p.getInventory().getItemInMainHand().getType().name().contains("CROSSBOW"))
			{
				if(ev.isSneaking())
				{
					int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
							p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 4,3,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 4,0,false,false));
							for(Entity e: p.getWorld().getNearbyEntities(p.getLocation(),3,3,3)) {
								if (e instanceof Player) 
								{
									new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
									Player p1 = (Player) e;
									if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
										{
											p1.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 4,3,false,false));
											p1.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 4,0,false,false));
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
			
	}
	
	@EventHandler
	public void DecayAndGrogi(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
		Projectile a = (Projectile) d.getDamager();
		LivingEntity le = (LivingEntity) d.getEntity();
		if(a.getShooter() instanceof Player)
			{
		        

				
				Player p = (Player) a.getShooter();
				
				
				if(playerclass.get(p.getUniqueId()) == 61) {
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
					{
						new MedSkillsData(MedSkillsData.loadData(path +"/plugins/RPGskills/MedskillsData.data"));		
						decayed.computeIfPresent(le, (k,v) -> v+0.1*ssd.ArrowClinic.get(p.getUniqueId()));			
						decayed.putIfAbsent(le, 0d);
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                    		@Override
		                	public void run() 
			                {	
                    			decayed.computeIfPresent(le, (k,v) -> v-0.1*ssd.ArrowClinic.get(p.getUniqueId()));
             					if(decayed.get(le)<0) {
             						decayed.remove(le);
             					}
             				}
	                	}, 200);
					}	
				}
				if(aed.containsKey(p)) {
					d.setCancelled(true);
				}
			}
		}
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
			Player p = (Player) d.getDamager();
			if(aed.containsKey(p)) {
				d.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void Decay(EntityDamageEvent d) 
	{
        
		
		if(d.getEntity() instanceof LivingEntity) 
		{
		LivingEntity le = (LivingEntity)d.getEntity();
			if(decayed.containsKey(le)) {
				if(1+decayed.get(le)*0.3>=2.5) {
					d.setDamage(d.getDamage()*(2.5));
					le.getWorld().spawnParticle(Particle.WARPED_SPORE, le.getLocation(), 10*6, 1,1,1,2);					
				}
				else {
					d.setDamage(d.getDamage()*(1+decayed.get(le)*0.3));
					le.getWorld().spawnParticle(Particle.WARPED_SPORE, le.getLocation(), (int) (10*decayed.get(le)), 1,1,1,2);
					
				}
			}
		}
	}
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();
        

		
		
		if(playerclass.get(p.getUniqueId()) == 61)
		{
			if(p.getInventory().getItemInMainHand().getType().name().equals("CROSSBOW"))
			{
					e.setCancelled(true);
			}
		}
		
	}
	
	
	@EventHandler
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
        
		
		
		
			
			if(playerclass.get(p.getUniqueId()) == 61 && (is.getType().name().equals("CROSSBOW")) && p.isSneaking())
			{
				ev.setCancelled(true);
				if(bultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (bultcooldown.get(p.getName())/1000 + 90) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Caduceus Tower").create());
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
										new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
										Player p1 = (Player) e;
										if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
											if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
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
									new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
									Player p1 = (Player) e;
									if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
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
	
	
	@EventHandler
	public void Arrowshoot(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();
	        
			
			
			if(playerclass.get(p.getUniqueId()) == 61) {
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
					if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
					{
					player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.PIERCING)*0.5 + p.getLevel()/10);
					
					if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
					{
						player_damage.put(p.getName(),player_damage.get(p.getName()) + p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
					}
					}
					shooter.put(e.getEntity(), p);
					if(e.getEntity().getType() == EntityType.ARROW)
					{
						Arrow ar = (Arrow) e.getEntity();
						if(ar.isShotFromCrossbow()) {
							arrow.put(p, ar);
						
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void Damagegetter(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
		Projectile a = (Projectile)d.getDamager();
			if(a.getShooter() instanceof Player) {
			Player p = (Player)a.getShooter();
		        
				
				
				
				if(playerclass.get(p.getUniqueId()) == 61) {
						player_damage.remove(p.getName());
						if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
						{
							player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.PIERCING)*0.5 + p.getLevel()/10);
							
							if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
							{
								player_damage.put(p.getName(),player_damage.get(p.getName()) + p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
							}
						}
						d.setDamage(d.getDamage()+ssd.Medicine.get(p.getUniqueId())*0.45);
				}
			}
		}
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
			{
			Player p = (Player) d.getDamager();
	        
			
			
			
			if(playerclass.get(p.getUniqueId()) == 61) {
					if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
					{
					player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.PIERCING)*0.5 + p.getLevel()/10);
					
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName()) + p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
					}
					d.setDamage(d.getDamage()+ssd.Medicine.get(p.getUniqueId())*0.45);
				}
			}
		}
		
	}
}



