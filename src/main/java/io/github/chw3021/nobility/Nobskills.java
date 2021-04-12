package io.github.chw3021.nobility;



import io.github.chw3021.classes.ClassData;
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
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Cod;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.PufferFish;
import org.bukkit.entity.Salmon;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Trident;
import org.bukkit.entity.TropicalFish;
import org.bukkit.entity.Turtle;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityToggleSwimEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Nobskills implements Serializable, Listener {
	

	/**
	 * 
	 */
	private static transient final long serialVersionUID = 1343715876136938259L;
	private HashMap<String, Long> rscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> prcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> jmcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> thcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> pncooldown = new HashMap<String, Long>();
	private HashMap<String, Long> eccooldown = new HashMap<String, Long>();
	private HashMap<String, Long> aultcooldown = new HashMap<String, Long>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private Multimap<UUID, LivingEntity> marked = ArrayListMultimap.create();
	private Multimap<UUID, Mob> gs = ArrayListMultimap.create();
	private Multimap<UUID, Mob> as = ArrayListMultimap.create();
	private static HashMap<UUID, Integer> gst = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> ot = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> markt = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> dolt = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Dolphin> dol = new HashMap<UUID, Dolphin>();

	private static HashMap<Player, Integer> pin = new HashMap<Player, Integer>();
	
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private NobSkillsData fsd = new NobSkillsData(NobSkillsData.loadData(path +"/plugins/RPGskills/NobSkillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		NobSkillsData f = new NobSkillsData(NobSkillsData.loadData(path +"/plugins/RPGskills/NobSkillsData.data"));
		fsd = f;
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
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Nobskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				NobSkillsData f = new NobSkillsData(NobSkillsData.loadData(path +"/plugins/RPGskills/NobSkillsData.data"));
				fsd = f;
			}
			
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		NobSkillsData f = new NobSkillsData(NobSkillsData.loadData(path +"/plugins/RPGskills/NobSkillsData.data"));
		fsd = f;
		
	}

	@EventHandler
	public void Transition(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player && e.getEntity() instanceof Trident)
		{
			Player p = (Player)e.getEntity().getShooter();
			int sec = 2;
			Trident t = (Trident) e.getEntity();

			if(!p.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
			}
			if(!p.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
			}
			if(!p.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 3, false, false));
			}
			
			
			if(playerclass.get(p.getUniqueId()) == 19 && p.isSneaking()) {

				if(rscooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            long timer = (rscooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
		            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Transition").create());
			        }
		            else // if timer is done
		            {
		            	rscooldown.remove(p.getName()); // removing player from HashMap
		                t.setMetadata("tras of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
		            	rscooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
	                t.setMetadata("tras of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
		        	rscooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
			
		}
	}


	@EventHandler
	public void Transition(ProjectileHitEvent d) 
	{

		if(d.getEntity() instanceof Trident && d.getHitEntity() instanceof LivingEntity && d.getHitBlock() ==null) 
		{
			Trident t = (Trident)d.getEntity();
			LivingEntity hle = (LivingEntity) d.getHitEntity();
	        
			
			if(t.getShooter() instanceof Player) {
				Player p = (Player) t.getShooter();
				if(p==hle) {
					return;
				}
				if(t.getMetadata("inv").stream().filter(v->v.value().equals(p.getUniqueId())).findFirst().isPresent()) {
					t.removeMetadata("inv", RMain.getInstance());
					p.setInvulnerable(true);
					if(!pin.containsKey(p)) {
						pin.put(p, 0);
					}
					else {
						pin.computeIfPresent(p, (k,v) -> v+1);
					}
				}
				if(t.hasMetadata("tras of "+p.getName())) {
					AtomicInteger j = new AtomicInteger();
					for(Entity e: hle.getWorld().getNearbyEntities(hle.getLocation(), 5,5,5)) {

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
	            		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
						{
								LivingEntity le = (LivingEntity)e;
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
				                		le.getWorld().spawnParticle(Particle.FLASH, le.getLocation(), 2,1,1,1);
					                	t.getWorld().playSound(le.getLocation(), Sound.ITEM_TRIDENT_RETURN, 1, 1);
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
											enar.setDamage(player_damage.get(p.getName())*0.61*(1+fsd.Transition.get(p.getUniqueId())*0.06));
										}		  
										le.damage(0, p);
										le.damage(player_damage.get(p.getName())*0.61*(1+fsd.Transition.get(p.getUniqueId())*0.06), p);	
										
					                }
					            }, j.incrementAndGet()*3);	
						}
					}
				}
			}
		}
		if(d.getEntity() instanceof Trident && d.getHitEntity() == null && d.getHitBlock() !=null) 
		{
			Trident t = (Trident)d.getEntity();
			Block hle = (Block) d.getHitBlock();
	        
			
			if(t.getShooter() instanceof Player) {
				Player p = (Player) t.getShooter();
				if(t.getMetadata("inv").stream().filter(v->v.value().equals(p.getUniqueId())).findFirst().isPresent()) {
					t.removeMetadata("inv", RMain.getInstance());
					p.setInvulnerable(true);
					if(!pin.containsKey(p)) {
						pin.put(p, 0);
					}
					else {
						pin.computeIfPresent(p, (k,v) -> v+1);
					}
				}
				if(t.hasMetadata("tras of "+p.getName())) {
					AtomicInteger j = new AtomicInteger();
					for(Entity e: hle.getWorld().getNearbyEntities(hle.getLocation(), 5,5,5)) {

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
	            		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
						{
								LivingEntity le = (LivingEntity)e;
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
				                		le.getWorld().spawnParticle(Particle.FLASH, le.getLocation(), 2,1,1,1);
					                	t.getWorld().playSound(le.getLocation(), Sound.ITEM_TRIDENT_RETURN, 1, 1);
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
											enar.setDamage(player_damage.get(p.getName())*0.61*(1+fsd.Transition.get(p.getUniqueId())*0.06));
										}		  
										le.damage(0, p);
										le.damage(player_damage.get(p.getName())*0.61*(1+fsd.Transition.get(p.getUniqueId())*0.06), p);	
										
					                }
					            }, j.incrementAndGet()*3);	
						}
					}
				}
			}
			
		}
			
	}

	@EventHandler
	public void Owner(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player && e.getEntity() instanceof Trident)
		{
			Player p = (Player)e.getEntity().getShooter();
			Trident t = (Trident) e.getEntity();
		    
			
			
			
			if(playerclass.get(p.getUniqueId()) == 19) {
				t.setMetadata("Towner", new FixedMetadataValue(RMain.getInstance(), p.getUniqueId()));
				t.setMetadata("inv", new FixedMetadataValue(RMain.getInstance(), p.getUniqueId()));
				if(!ot.containsKey(p.getUniqueId())) {
					int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	if(t.getLocation().getY()<=-60) {
		                		t.teleport(p);
		                	}
		                }
					},0,1);
					ot.put(p.getUniqueId(), task);					
				}
			}
			
		}
	}
	@EventHandler
	public void Owner(PlayerPickupArrowEvent ev) 
	{
		Player p = ev.getPlayer();
			if(ev.getArrow().getMetadata("Towner").stream().filter(v->v.value().equals(p.getUniqueId())).findFirst().isPresent()) {
				pin.computeIfPresent(p, (k,v) -> v-1);
				if(pin.get(p)<0) {
					p.setInvulnerable(false);
					pin.remove(p);
				}
				if(ot.containsKey(p.getUniqueId())) {
					Bukkit.getServer().getScheduler().cancelTask(ot.get(p.getUniqueId()));
					ot.remove(p.getUniqueId());
				}
			}
	}
	@EventHandler
	public void Owner(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
	    
		
		
		
		if(playerclass.get(p.getUniqueId()) == 19 && fsd.Owner.get(p.getUniqueId())>=1) {
	
			if(p.getInventory().getItemInMainHand().getType().isAir() && p.isSneaking()) 
			{
				if(ac == Action.LEFT_CLICK_AIR)
				{
					p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_RETURN, 1, 0);
		    		p.getServer().getWorlds().forEach(w -> w.getEntities().stream().filter(e-> e.getMetadata("Towner").stream().filter(v->v.value().equals(p.getUniqueId())).findFirst().isPresent()).forEach(t -> {
		    			t.setVelocity(t.getVelocity().zero());
		    			t.teleport(p.getEyeLocation());
		    		}));
				} 
			}
		}
	}


	@EventHandler
	public void GuardianSupport(PlayerItemHeldEvent ev) 
	{
		Player p = ev.getPlayer();
		if(playerclass.get(p.getUniqueId()) == 19 && p.getInventory().getItemInMainHand().getType()==Material.TRIDENT && gst.containsKey(p.getUniqueId())){
			if(gs.containsKey(p.getUniqueId())) {
				for(LivingEntity le : gs.get(p.getUniqueId())) {
					le.remove();
				}
				gs.removeAll(p.getUniqueId());
				Bukkit.getServer().getScheduler().cancelTask(gst.get(p.getUniqueId()));
			}
		}
	}
	
	@EventHandler
	public void GuardianSupport(EntityDamageByEntityEvent d) 
	{
		 
		 
		 
			if(d.getDamager() instanceof Projectile&& d.getEntity() instanceof LivingEntity && !d.getEntity().hasMetadata("fake") && !d.isCancelled()) 
			{
				Projectile pr = (Projectile)d.getDamager();
				if(pr.getShooter() instanceof Player) {
					Player p = (Player) pr.getShooter();
					LivingEntity le = (LivingEntity) d.getEntity();
					if(playerclass.get(p.getUniqueId()) == 19) {
						 
						if(!gs.containsKey(p.getUniqueId())) {

		                	Guardian cs = (Guardian) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(-Math.PI/2).multiply(1.5)), EntityType.GUARDIAN);
							cs.setInvulnerable(true);
							cs.setAI(false);
							cs.setCustomName(p.getName());
							cs.setMetadata("gd of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
							cs.setMetadata("gds", new FixedMetadataValue(RMain.getInstance(), true));
							cs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							cs.setGravity(false);
							cs.setCollidable(false);
							gs.put(p.getUniqueId(), cs);
		                    Guardian cs2 = (Guardian) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(Math.PI/2).multiply(1.5)), EntityType.GUARDIAN);
							cs2.setInvulnerable(true);
							cs2.setAI(false);
							cs2.setCustomName(p.getName());
							cs2.setMetadata("gd of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
							cs2.setMetadata("gds", new FixedMetadataValue(RMain.getInstance(), true));
							cs2.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							cs2.setGravity(false);
							cs2.setCollidable(false);
							gs.put(p.getUniqueId(), cs2);
							int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
			             			cs.teleport(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(-Math.PI/2).multiply(1.5)));
			             			cs2.teleport(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(Math.PI/2).multiply(1.5)));
				                }
							},2,2);
							gst.put(p.getUniqueId(), task);
						}
						d.setDamage(d.getDamage() + player_damage.get(p.getName())*0.5*(1+fsd.GuardianSupport.get(p.getUniqueId())*0.025));
						for(int i = 0; i <2; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
			                	public void run() 
			                	{
			             			le.playEffect(EntityEffect.HURT);
			             			p.playSound(le.getEyeLocation(), Sound.ENTITY_GUARDIAN_FLOP, 0.8f, 2f);
			             			p.playSound(p.getEyeLocation(), Sound.ENTITY_GUARDIAN_ATTACK, 0.8f, 1f);
					            }
							}, i*2);
						}
							
					}		
				}
			}
			if(d.getDamager() instanceof Player&& d.getEntity() instanceof LivingEntity && !d.getEntity().hasMetadata("fake") && !d.isCancelled()) 
			{
					Player p = (Player) d.getDamager();
					LivingEntity le = (LivingEntity) d.getEntity();
					if(playerclass.get(p.getUniqueId()) == 19) {
						 
						if(!gs.containsKey(p.getUniqueId())) {
		                	Guardian cs = (Guardian) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(-Math.PI/2).multiply(1.5)), EntityType.GUARDIAN);
							cs.setInvulnerable(true);
							cs.setAI(false);
							cs.setCustomName(p.getName());
							cs.setMetadata("gd of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
							cs.setMetadata("gds", new FixedMetadataValue(RMain.getInstance(), true));
							cs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							cs.setGravity(false);
							cs.setCollidable(false);
							gs.put(p.getUniqueId(), cs);
		                    Guardian cs2 = (Guardian) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(Math.PI/2).multiply(1.5)), EntityType.GUARDIAN);
							cs2.setInvulnerable(true);
							cs2.setAI(false);
							cs2.setCustomName(p.getName());
							cs2.setMetadata("gd of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
							cs2.setMetadata("gds", new FixedMetadataValue(RMain.getInstance(), true));
							cs2.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							cs2.setGravity(false);
							cs2.setCollidable(false);
							gs.put(p.getUniqueId(), cs2);
							int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
			             			cs.teleport(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(-Math.PI/2).multiply(1.5)));
			             			cs2.teleport(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(Math.PI/2).multiply(1.5)));
				                }
							},2,2);
							gst.put(p.getUniqueId(), task);
						}
						d.setDamage(d.getDamage() + player_damage.get(p.getName())*0.5*(1+fsd.GuardianSupport.get(p.getUniqueId())*0.025));
						for(int i = 0; i <2; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
			                	public void run() 
			                	{
			             			le.playEffect(EntityEffect.HURT);
			             			p.playSound(le.getEyeLocation(), Sound.ENTITY_GUARDIAN_FLOP, 0.8f, 2f);
			             			p.playSound(p.getEyeLocation(), Sound.ENTITY_GUARDIAN_ATTACK, 0.8f, 1f);
					            }
							}, i*2);
						}
							
					}		
			}
			if(d.getDamager() instanceof LivingEntity&& d.getEntity() instanceof Player && !d.getEntity().hasMetadata("fake") && !d.isCancelled()) 
			{
					Player p = (Player) d.getEntity();
					LivingEntity le = (LivingEntity) d.getDamager();
					if(playerclass.get(p.getUniqueId()) == 19) {
						 
						le.damage(0,p);
						le.damage(player_damage.get(p.getName())*0.1*(1+fsd.GuardianSupport.get(p.getUniqueId())*0.025),p);
						for(int i = 0; i <2; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
			                	public void run() 
			                	{
			             			p.playSound(le.getEyeLocation(), Sound.ENTITY_GUARDIAN_FLOP, 0.8f, 2f);
			             			p.playSound(p.getEyeLocation(), Sound.ENTITY_GUARDIAN_ATTACK, 0.8f, 1f);
					            }
							}, i*2);
						}
							
					}
			}
			if(d.getDamager() instanceof Projectile&& d.getEntity() instanceof Player && !d.getEntity().hasMetadata("fake") && !d.isCancelled()) 
			{
					Player p = (Player) d.getEntity();
					Projectile pr = (Projectile) d.getDamager();
					if(playerclass.get(p.getUniqueId()) == 19 && pr.getShooter() instanceof LivingEntity) {
						 
						LivingEntity le = (LivingEntity) pr.getShooter();
						le.damage(0,p);
						le.damage(player_damage.get(p.getName())*0.1*(1+fsd.GuardianSupport.get(p.getUniqueId())*0.025),p);
						for(int i = 0; i <2; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
			                	public void run() 
			                	{
			             			p.playSound(le.getEyeLocation(), Sound.ENTITY_GUARDIAN_FLOP, 0.8f, 2f);
			             			p.playSound(p.getEyeLocation(), Sound.ENTITY_GUARDIAN_ATTACK, 0.8f, 1f);
					            }
							}, i*2);
						}
							
					}		
			}
	}


	@EventHandler
	public void delete(EntityDeathEvent d) 
	{
		if(d.getEntity() instanceof Player) {
			Player p = (Player) d.getEntity();
			if(gs.containsKey(p.getUniqueId())) {
				for(LivingEntity le : gs.get(p.getUniqueId())) {
					le.remove();
				}
				gs.removeAll(p.getUniqueId());
				Bukkit.getServer().getScheduler().cancelTask(gst.get(p.getUniqueId()));
			}
			if(as.containsKey(p.getUniqueId())) {
				for(LivingEntity le : as.get(p.getUniqueId())) {
					le.remove();
				}
				as.removeAll(p.getUniqueId());
			}
		}
	}
	@EventHandler
	public void delete(PlayerQuitEvent d) 
	{
		Player p = d.getPlayer();
		if(gs.containsKey(p.getUniqueId())) {
			for(LivingEntity le : gs.get(p.getUniqueId())) {
				le.remove();
			}
			gs.removeAll(p.getUniqueId());
			Bukkit.getServer().getScheduler().cancelTask(gst.get(p.getUniqueId()));
		}
		if(as.containsKey(p.getUniqueId())) {
			for(LivingEntity le : as.get(p.getUniqueId())) {
				le.remove();
			}
			as.removeAll(p.getUniqueId());
		}
	}
	@EventHandler
	public void Dolphinleft(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
	    
		
		
		if((ac == Action.RIGHT_CLICK_AIR || ac==Action.RIGHT_CLICK_BLOCK) &&dolt.containsKey(p.getUniqueId()) && dol.containsKey(p.getUniqueId()) && playerclass.get(p.getUniqueId()) == 19) {

			 
			Bukkit.getServer().getScheduler().cancelTask(dolt.get(p.getUniqueId()));
			dol.get(p.getUniqueId()).remove();
			dol.remove(p.getUniqueId());
			dolt.remove(p.getUniqueId());
				p.getWorld().spawnParticle(Particle.WATER_SPLASH, p.getLocation(), 60,3,3,3);
				for(Entity e: p.getWorld().getNearbyEntities(p.getLocation(), 5,5,5)) {
					
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
							enar.setDamage(player_damage.get(p.getName())*0.56*(1+fsd.DolphinSurf.get(p.getUniqueId())*0.03));
						}		  
						le.damage(0, p);
						le.damage(player_damage.get(p.getName())*0.56*(1+fsd.DolphinSurf.get(p.getUniqueId())*0.03), p);	
								
				}
			}
		}
	}
	
	@EventHandler
	public void DolphinSurf(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 7;
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 19) {
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT && !p.isSneaking())
			{
				
				ev.setCancelled(true);
				{
					if(jmcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (jmcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use DolphinSurf").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {							
		                	jmcooldown.remove(p.getName()); // removing player from HashMap

		                    Dolphin cs = (Dolphin) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.DOLPHIN);
							cs.setInvulnerable(true);
							cs.setAI(false);
							cs.setCustomName(p.getName());
							cs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 135, 0, false,false));
							int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
			             			if(cs.isValid()) {
				             			if(cs.getLocation().add(p.getEyeLocation().getDirection().normalize().multiply(1.1)).getBlock().isPassable()){
					             			cs.eject();
					                    	cs.teleport(cs.getLocation().add(p.getEyeLocation().getDirection().normalize().multiply(1.1)));
					                    	cs.addPassenger(p);
				             			}
				             			else {
				             				p.getWorld().spawnParticle(Particle.WATER_SPLASH, p.getLocation(), 60,3,3,3);
				             				for(Entity e: p.getWorld().getNearbyEntities(p.getLocation(), 5,5,5)) {

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
		        											enar.setDamage(player_damage.get(p.getName())*0.56*(1+fsd.DolphinSurf.get(p.getUniqueId())*0.03));
		        										}		  
		        										le.damage(0, p);
		        										le.damage(player_damage.get(p.getName())*0.56*(1+fsd.DolphinSurf.get(p.getUniqueId())*0.03), p);	
				        										
				        						}
				        					}
					                    	cs.remove(); 
				             			}
			             			}
				                    p.playSound(p.getLocation(), Sound.ENTITY_BOAT_PADDLE_WATER, 1.0f, 2f);
				                	
				                
				                }
							},2,2);
							dolt.put(p.getUniqueId(), task);
							dol.put(p.getUniqueId(), cs);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
			                	public void run() 
				                {	      
			                    	Bukkit.getServer().getScheduler().cancelTask(task);
			                    	cs.remove();   
			                    	dolt.remove(p.getUniqueId());
			                    	dol.remove(p.getUniqueId());
					            }
							}, 61);
							jmcooldown.put(p.getName(), System.currentTimeMillis());
						}
		            }
		            else // if cooldown doesn't have players name in it
		            {

	                    Dolphin cs = (Dolphin) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.DOLPHIN);
						cs.setInvulnerable(true);
						cs.setAI(false);
						cs.setCustomName(p.getName());
						cs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 135, 0, false,false));
						int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
		             			if(cs.isValid()) {
			             			if(cs.getLocation().add(p.getEyeLocation().getDirection().normalize().multiply(1.1)).getBlock().isPassable()){
				             			cs.eject();
				                    	cs.teleport(cs.getLocation().add(p.getEyeLocation().getDirection().normalize().multiply(1.1)));
				                    	cs.addPassenger(p);
			             			}
			             			else {
			             				p.getWorld().spawnParticle(Particle.WATER_SPLASH, p.getLocation(), 60,3,3,3);
			             				for(Entity e: p.getWorld().getNearbyEntities(p.getLocation(), 5,5,5)) {

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
	        											enar.setDamage(player_damage.get(p.getName())*0.56*(1+fsd.DolphinSurf.get(p.getUniqueId())*0.03));
	        										}		  
	        										le.damage(0, p);
	        										le.damage(player_damage.get(p.getName())*0.56*(1+fsd.DolphinSurf.get(p.getUniqueId())*0.03), p);	
			        										
			        						}
			        					}
				                    	cs.remove(); 
			             			}
		             			}
			                    p.playSound(p.getLocation(), Sound.ENTITY_BOAT_PADDLE_WATER, 1.0f, 2f);
			                	
			                
			                }
						},2,2);
						dolt.put(p.getUniqueId(), task);
						dol.put(p.getUniqueId(), cs);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
		                	public void run() 
			                {	      
		                    	Bukkit.getServer().getScheduler().cancelTask(task);
		                    	cs.remove();   
		                    	dolt.remove(p.getUniqueId());
		                    	dol.remove(p.getUniqueId());
				            }
						}, 61);
						jmcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
					}
				}}
		}
	}

	@EventHandler
	public void Assault(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 11;
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 19) {
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT && p.isSneaking())
			{
				
				ev.setCancelled(true);
				{
					if(thcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (thcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Assault").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {							
		                	thcooldown.remove(p.getName()); // removing player from HashMap

		                	PufferFish cs = (PufferFish) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(-Math.PI/6).multiply(1.5)), EntityType.PUFFERFISH);
							cs.setInvulnerable(true);
							cs.setAI(false);
							cs.setCustomName(p.getName());
							cs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							as.put(p.getUniqueId(), cs);
		                	TropicalFish cs1 = (TropicalFish) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(Math.PI/6).multiply(1.5)), EntityType.TROPICAL_FISH);
							cs1.setInvulnerable(true);
							cs1.setAI(false);
							cs1.setCustomName(p.getName());
							cs1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							as.put(p.getUniqueId(), cs1);
							Cod cs2 = (Cod) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(Math.PI/2).multiply(1.5)), EntityType.COD);
							cs2.setInvulnerable(true);
							cs2.setAI(false);
							cs2.setCustomName(p.getName());
							cs2.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							as.put(p.getUniqueId(), cs2);
							Salmon cs3 = (Salmon) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(-Math.PI/2).multiply(1.5)), EntityType.SALMON);
							cs3.setInvulnerable(true);
							cs3.setAI(false);
							cs3.setCustomName(p.getName());
							cs3.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							as.put(p.getUniqueId(), cs3);
		                	Turtle cs4 = (Turtle) p.getWorld().spawnEntity(p.getLocation().add(p.getLocation().getDirection().multiply(1.5)), EntityType.TURTLE);
							cs4.setInvulnerable(true);
							cs4.setAI(false);
							cs4.setCustomName(p.getName());
							cs4.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							as.put(p.getUniqueId(), cs4);
		                	Squid cs5 = (Squid) p.getWorld().spawnEntity(p.getEyeLocation().add(0, 2, 0), EntityType.SQUID);
							cs5.setInvulnerable(true);
							cs5.setGravity(false);
							cs5.setAI(false);
							cs5.setCustomName(p.getName());
							cs5.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							as.put(p.getUniqueId(), cs5);
							
							p.playSound(p.getLocation(), Sound.AMBIENT_UNDERWATER_ENTER, 1, 2);
	                    	for(int dou = 0; dou <10; dou+=1) {
		                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	for(Mob m: as.get(p.getUniqueId())) {
					                		m.teleport(m.getLocation().setDirection(p.getLocation().getDirection()).add(p.getLocation().getDirection().normalize().multiply(2)));
					                		m.getLocation().setDirection(p.getLocation().getDirection());
											p.getWorld().spawnParticle(Particle.FALLING_WATER, m.getLocation(), 20, 1,1,1,0);
											p.getWorld().spawnParticle(Particle.WATER_BUBBLE, m.getLocation(), 10, 1,1,1,0.1);
											p.getWorld().spawnParticle(Particle.WATER_SPLASH, m.getLocation(), 10, 1,1,1,0.1);
											p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
											p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_TURTLE, 1, 0);
					                	}
											for(Entity e : cs5.getWorld().getNearbyEntities(cs5.getLocation(),5, 5, 5)) {

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
				        	            		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
				        						{
				        								LivingEntity le = (LivingEntity)e;
				        								le.teleport(le.getLocation().add(0, 0.02, 0));
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
			    											enar.setDamage(player_damage.get(p.getName())*0.2*(1+fsd.Assault.get(p.getUniqueId())*0.01));
			    										}		  
			    										le.damage(0, p);
			    										le.damage(player_damage.get(p.getName())*0.2*(1+fsd.Assault.get(p.getUniqueId())*0.01), p);	
				        										
				        						}
											}
					                    
					                }
					            }, dou*10);  
	                    	}
	                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {

				                	for(Mob m: as.get(p.getUniqueId())) {
				                		m.remove();
				                	}
				                	as.removeAll(p.getUniqueId());
				                    
				                }
				            }, 101); 
		                	thcooldown.put(p.getName(), System.currentTimeMillis());
						}
		            }
		            else // if cooldown doesn't have players name in it
		            {

	                	PufferFish cs = (PufferFish) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(-Math.PI/6).multiply(1.5)), EntityType.PUFFERFISH);
						cs.setInvulnerable(true);
						cs.setAI(false);
						cs.setCustomName(p.getName());
						cs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						as.put(p.getUniqueId(), cs);
	                	TropicalFish cs1 = (TropicalFish) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(Math.PI/6).multiply(1.5)), EntityType.TROPICAL_FISH);
						cs1.setInvulnerable(true);
						cs1.setAI(false);
						cs1.setCustomName(p.getName());
						cs1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						as.put(p.getUniqueId(), cs1);
						Cod cs2 = (Cod) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(Math.PI/2).multiply(1.5)), EntityType.COD);
						cs2.setInvulnerable(true);
						cs2.setAI(false);
						cs2.setCustomName(p.getName());
						cs2.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						as.put(p.getUniqueId(), cs2);
						Salmon cs3 = (Salmon) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(-Math.PI/2).multiply(1.5)), EntityType.SALMON);
						cs3.setInvulnerable(true);
						cs3.setAI(false);
						cs3.setCustomName(p.getName());
						cs3.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						as.put(p.getUniqueId(), cs3);
	                	Turtle cs4 = (Turtle) p.getWorld().spawnEntity(p.getLocation().add(p.getLocation().getDirection().multiply(1.5)), EntityType.TURTLE);
						cs4.setInvulnerable(true);
						cs4.setAI(false);
						cs4.setCustomName(p.getName());
						cs4.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						as.put(p.getUniqueId(), cs4);
	                	Squid cs5 = (Squid) p.getWorld().spawnEntity(p.getEyeLocation().add(0, 2, 0), EntityType.SQUID);
						cs5.setInvulnerable(true);
						cs5.setAI(false);
						cs5.setGravity(false);
						cs5.setCustomName(p.getName());
						cs5.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						as.put(p.getUniqueId(), cs5);
						
						p.playSound(p.getLocation(), Sound.AMBIENT_UNDERWATER_ENTER, 1, 2);
                    	for(int dou = 0; dou <10; dou+=1) {
	                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	for(Mob m: as.get(p.getUniqueId())) {
				                		m.teleport(m.getLocation().setDirection(p.getLocation().getDirection()).add(p.getLocation().getDirection().normalize().multiply(2)));
										p.getWorld().spawnParticle(Particle.FALLING_WATER, m.getLocation(), 20, 1,1,1,0);
										p.getWorld().spawnParticle(Particle.WATER_BUBBLE, m.getLocation(), 10, 1,1,1,0.1);
										p.getWorld().spawnParticle(Particle.WATER_SPLASH, m.getLocation(), 10, 1,1,1,0.1);
										p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
										p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_TURTLE, 1, 0);
				                	}
										for(Entity e : cs5.getWorld().getNearbyEntities(cs5.getLocation(),5, 5, 5)) {

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
			        	            		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
			        						{
			        								LivingEntity le = (LivingEntity)e;
			        								le.teleport(le.getLocation().add(0, 0.02, 0));
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
		    											enar.setDamage(player_damage.get(p.getName())*0.2*(1+fsd.Assault.get(p.getUniqueId())*0.01));
		    										}		  
		    										le.damage(0, p);
		    										le.damage(player_damage.get(p.getName())*0.2*(1+fsd.Assault.get(p.getUniqueId())*0.01), p);	
			        						}
										}
				                    
				                }
				            }, dou*10);  
                    	}
                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {

			                	for(Mob m: as.get(p.getUniqueId())) {
			                		m.remove();
			                	}
			                	as.removeAll(p.getUniqueId());
			                    
			                }
			            }, 101); 
		            	thcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
					}
				}}
		}
	}
	@EventHandler
	public void Storm(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 2;
        
		
		
		
		if(playerclass.get(p.getUniqueId()) == 19) {
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT &&!p.isSneaking()&& !p.isOnGround()) 
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					if(eccooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (eccooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Storm").create());
		                }
		                else // if timer is done
		                {
		                	eccooldown.remove(p.getName()); // removing player from HashMap
		                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 0);
		                	HashSet<LivingEntity> les = new HashSet<>();
		                	marked.get(p.getUniqueId()).forEach(e -> les.add(e));
		                	les.stream().filter(le -> !le.isDead()).forEach(le ->{
		                		le.getWorld().strikeLightningEffect(le.getLocation());
		                		le.getWorld().spawnParticle(Particle.CLOUD, le.getLocation().add(0, 5, 0), 10,1,1,1);
		                		le.getWorld().spawnParticle(Particle.FALLING_WATER, le.getLocation().add(0, 5, 0), 80,1,1,1);
		                		le.getWorld().spawnParticle(Particle.FLASH, le.getLocation().add(0, 1, 0), 10,1,1,1);
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
									enar.setDamage(player_damage.get(p.getName())*0.5*(1+fsd.Storm.get(p.getUniqueId())*0.02));
								}		  
		                		le.damage(0,p);
		                		le.damage(player_damage.get(p.getName())*0.5*(1+fsd.Storm.get(p.getUniqueId())*0.02),p);
		                	});
		                    eccooldown.put(p.getName(), System.currentTimeMillis());  
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
	                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 0);
	                	HashSet<LivingEntity> les = new HashSet<>();
	                	marked.get(p.getUniqueId()).forEach(e -> les.add(e));
	                	les.stream().filter(le -> !le.isDead()).forEach(le ->{
	                		le.getWorld().strikeLightningEffect(le.getLocation());
	                		le.getWorld().spawnParticle(Particle.CLOUD, le.getLocation().add(0, 5, 0), 10,1,1,1);
	                		le.getWorld().spawnParticle(Particle.FALLING_WATER, le.getLocation().add(0, 5, 0), 80,1,1,1);
	                		le.getWorld().spawnParticle(Particle.FLASH, le.getLocation().add(0, 1, 0), 10,1,1,1);
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
								enar.setDamage(player_damage.get(p.getName())*0.5*(1+fsd.Storm.get(p.getUniqueId())*0.02));
							}		  
	                		le.damage(0,p);
	                		le.damage(player_damage.get(p.getName())*0.5*(1+fsd.Storm.get(p.getUniqueId())*0.02),p);
	                	});
	                    eccooldown.put(p.getName(), System.currentTimeMillis());  
					}
		    					
				} 
			}
		
		}
	}

	
		
	@EventHandler
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		final Location pl = p.getLocation();
		final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 11).getLocation();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 19 && ((is.getType()==Material.TRIDENT))&& p.isSneaking())
			{
				ev.setCancelled(true);
				if(aultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (aultcooldown.get(p.getName())/1000 + 75) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Whirlpool").create());
		            }
	                else // if timer is done
	                {
	                    aultcooldown.remove(p.getName()); // removing player from HashMap
	                    p.setInvulnerable(true);
						if(!pin.containsKey(p)) {
							pin.put(p, 0);
						}
						else {
							pin.computeIfPresent(p, (k,v) -> v+1);
						}
	                	ArrayList<Location> wh = new ArrayList<>();
	                	AtomicInteger j = new AtomicInteger();
	                	AtomicInteger k = new AtomicInteger();
	                	for(int i = 0; i<10; i++) {
		                	for(double an = 0; an<Math.PI*2; an +=Math.PI/180) {
	                			wh.add(tl.clone().add(pl.getDirection().normalize().rotateAroundY(an+i).multiply(an)).add(0, an, 0));
		                	}
	                	}
	                	for(int i = 0; i<10; i++) {
	                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	p.getWorld().playSound(tl, Sound.ENTITY_WITHER_AMBIENT, 1, 0);
				                	wh.forEach(l -> {
				                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
												p.getWorld().spawnParticle(Particle.WATER_WAKE, l, 2, 1,1,1,0);
												p.getWorld().spawnParticle(Particle.CLOUD, l, 2, 0.1,0.1,0.1,0);
							                }
							            }, j.incrementAndGet()/3000);  
				                	}); 

			                		for(Entity e: p.getWorld().getNearbyEntities(tl, 9,9,9)) {
	
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
		        	            		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
		        						{
		        								LivingEntity le = (LivingEntity)e;
		        								le.teleport(le.getLocation().add(0, 0.02, 0));
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
	    											enar.setDamage(player_damage.get(p.getName())*0.43*(1));
	    										}		  
	    										le.damage(0, p);
	    										le.damage(player_damage.get(p.getName())*0.43*(1), p);	
		        										
		        						}
		        					}
				                }
				            }, i*3); 
	                	}
    					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    		                @Override
    		                public void run() {
    		    				pin.computeIfPresent(p, (k,v) -> v-1);
    		    				if(pin.get(p)<0) {
    		    					p.setInvulnerable(false);
    		    					pin.remove(p);
    		    				}
    		                }
    		            }, 33);
		                aultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	p.setInvulnerable(true);
					if(!pin.containsKey(p)) {
						pin.put(p, 0);
					}
					else {
						pin.computeIfPresent(p, (k,v) -> v+1);
					}
                	ArrayList<Location> wh = new ArrayList<>();
                	AtomicInteger j = new AtomicInteger();
                	AtomicInteger k = new AtomicInteger();
                	for(int i = 0; i<10; i++) {
	                	for(double an = 0; an<Math.PI*2; an +=Math.PI/180) {
                			wh.add(tl.clone().add(pl.getDirection().normalize().rotateAroundY(an+i).multiply(an)).add(0, an, 0));
	                	}
                	}
                	for(int i = 0; i<10; i++) {
                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	p.getWorld().playSound(tl, Sound.ENTITY_WITHER_AMBIENT, 1, 0);
			                	wh.forEach(l -> {
			                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											p.getWorld().spawnParticle(Particle.WATER_WAKE, l, 2, 1,1,1,0);
											p.getWorld().spawnParticle(Particle.CLOUD, l, 2, 0.1,0.1,0.1,0);
						                }
						            }, j.incrementAndGet()/3000);  
			                	}); 

		                		for(Entity e: p.getWorld().getNearbyEntities(tl, 9,9,9)) {

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
	        	            		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
	        						{
	        								LivingEntity le = (LivingEntity)e;
	        								le.teleport(le.getLocation().add(0, 0.02, 0));
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
    											enar.setDamage(player_damage.get(p.getName())*0.43*(1));
    										}		  
    										le.damage(0, p);
    										le.damage(player_damage.get(p.getName())*0.43*(1), p);	
	        										
	        						}
	        					}
			                }
			            }, i*3); 
                	}
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		    				pin.computeIfPresent(p, (k,v) -> v-1);
		    				if(pin.get(p)<0) {
		    					p.setInvulnerable(false);
		    					pin.remove(p);
		    				}
		                }
		            }, 33);
	                aultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}	
			
			
    }


	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();

	    
		
		
		if(playerclass.get(p.getUniqueId()) == 19)
		{
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT)
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
		    
			
			
			
			if(playerclass.get(p.getUniqueId()) == 19) {
				if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT )
				{
						player_damage.put(p.getName(),10 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
			}
			
		}
	}

	@EventHandler
	public void Damagegetter(EntityToggleSwimEvent e) 
	{
		
		if(e.getEntity() instanceof Player)
		{
			Player p = (Player)e.getEntity();
		    
			
			
			
			if(playerclass.get(p.getUniqueId()) == 19 || playerclass.get(p.getUniqueId()) == 20) {
				if(!p.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 255, false, false));
				}
				if(!p.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
				}
				if(!p.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
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
        

		
		
		
		if(playerclass.get(p.getUniqueId()) == 19) {
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT)
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
			    
				
				
				
				if(playerclass.get(p.getUniqueId()) == 19) {

					if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT)
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
	@EventHandler
	public void MarkOfSea(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.getEntity().hasMetadata("fake")&& d.getDamage()>0) 
		{
	        
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			
	
			
			
			if(playerclass.get(p.getUniqueId()) == 19) {

				if(le == p) {
					d.setCancelled(true);
					return;
				}
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
					p.getWorld().spawnParticle(Particle.BUBBLE_COLUMN_UP, le.getLocation(), 30, 3,3,3,0);
					le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 120, 0, false,false));
					d.setDamage(d.getDamage()*1.1*(1+player_damage.get(p.getName())*0.013)*(1+fsd.MarkOfSea.get(p.getUniqueId())*0.0436));
					marked.put(p.getUniqueId(), le);
					if(markt.containsKey(p.getUniqueId())) {
						Bukkit.getServer().getScheduler().cancelTask(markt.get(p.getUniqueId()));
					}
					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							marked.remove(p.getUniqueId(), le);
		                }
		            }, 120);
					markt.put(p.getUniqueId(),task);
					if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT) {
						if(p.getInventory().getItemInMainHand().getEnchantments().isEmpty()) {
							p.getInventory().getItemInMainHand().addEnchantment(Enchantment.LOYALTY, 3);
							p.getInventory().getItemInMainHand().addEnchantment(Enchantment.CHANNELING, 1);
						}
					}
				}
				
		}
		
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity && !d.getEntity().hasMetadata("fake") && d.getDamage()>0) 
		{
		Projectile ar = (Projectile)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
        
		

		
		
		if(ar.getShooter() instanceof Player) {
			Player p = (Player) ar.getShooter();
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
			if(playerclass.get(p.getUniqueId()) == 19) {
				if(le == p) {
					d.setCancelled(true);
					return;
				}
					p.getWorld().spawnParticle(Particle.WATER_SPLASH, le.getLocation(), 50, 3,3,3,0);
					d.setDamage(d.getDamage()*1.1*(1+player_damage.get(p.getName())*0.013)*(1+fsd.MarkOfSea.get(p.getUniqueId())*0.0436));
					le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 120, 0, false,false));
					marked.put(p.getUniqueId(), le);
					if(markt.containsKey(p.getUniqueId())) {
						Bukkit.getServer().getScheduler().cancelTask(markt.get(p.getUniqueId()));
					}
					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							marked.remove(p.getUniqueId(), le);
		                }
		            }, 120);
					markt.put(p.getUniqueId(),task);
			}
		}
		}
	}
}



