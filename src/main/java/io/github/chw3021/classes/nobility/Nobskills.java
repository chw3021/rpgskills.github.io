package io.github.chw3021.classes.nobility;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Axolotl.Variant;
import org.bukkit.entity.Cod;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.GlowSquid;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.PufferFish;
import org.bukkit.entity.Salmon;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Trident;
import org.bukkit.entity.TropicalFish;
import org.bukkit.entity.Turtle;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Nobskills extends Pak implements Serializable, Listener {
	

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
	private HashMap<String, Long> ault2cooldown = new HashMap<String, Long>();

	private HashMap<UUID,  Multimap<UUID, Mob>> trrn = new HashMap<UUID,  Multimap<UUID, Mob>>();
	private HashMap<UUID, Integer> trrnt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> ppcrs = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> ppcrst = new HashMap<UUID, Integer>();
	

	private HashMap<UUID, Integer> bckwsh = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> bckwsht = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> clve = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> clvet = new HashMap<UUID, Integer>();
	

	private HashMap<UUID, Double> damage = new HashMap<UUID, Double>();
	

	private Multimap<UUID, Mob> axs = HashMultimap.create();
	
	
	
	private Multimap<UUID, LivingEntity> marked = HashMultimap.create();
	private Multimap<UUID, Mob> gs = HashMultimap.create();
	private Multimap<UUID, Mob> as = LinkedHashMultimap.create();
	private static HashMap<UUID, Integer> gst = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> ot = new HashMap<UUID, Integer>();
	private static HashMap<UUID, UUID> trc = new HashMap<UUID, UUID>();
	private static HashMap<UUID, UUID> trci = new HashMap<UUID, UUID>();

	private static HashMap<UUID, Integer> ast = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> asmt = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> asrt = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> asmrt = new HashMap<UUID, Integer>();
	
	
	private static HashMap<UUID, Integer> markt = new HashMap<UUID, Integer>();

	private static HashMap<UUID, Integer> dolrt = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> dolt = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Dolphin> dol = new HashMap<UUID, Dolphin>();
	private static HashMap<UUID, Snowball> dolh = new HashMap<UUID, Snowball>();

	private static HashMap<UUID, Integer> pint = new HashMap<UUID, Integer>();
	
	private String path = new File("").getAbsolutePath();
	private NobSkillsData fsd;

	private static final Nobskills instance = new Nobskills ();
	public static Nobskills getInstance()
	{
		return instance;
	}


		
	public void er(PluginEnableEvent ev) 
	{
		NobSkillsData f = new NobSkillsData(NobSkillsData.loadData(path +"/plugins/RPGskills/NobSkillsData.data"));
		fsd = f;
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
				if(pint.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(pint.get(p.getUniqueId()));
					pint.remove(p.getUniqueId());
				}
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

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		NobSkillsData f = new NobSkillsData(NobSkillsData.loadData(path +"/plugins/RPGskills/NobSkillsData.data"));
		fsd = f;
		Player p = ev.getPlayer();
		if(pint.containsKey(p.getUniqueId())) {
			Bukkit.getServer().getScheduler().cancelTask(pint.get(p.getUniqueId()));
			pint.remove(p.getUniqueId());
		}
		if(ot.containsKey(p.getUniqueId())) {
			Bukkit.getServer().getScheduler().cancelTask(ot.get(p.getUniqueId()));
			ot.remove(p.getUniqueId());
		}
	}

	public void Transition(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player && e.getEntity() instanceof Trident)
		{
			Player p = (Player)e.getEntity().getShooter();
			double sec =4*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
			Trident t = (Trident) e.getEntity();

			
			if(ClassData.pc.get(p.getUniqueId()) == 19 && fsd.Transition.getOrDefault(p.getUniqueId(), 0)>=1) {
				if(Proficiency.getpro(p)>=1) {
					t.setVelocity(t.getVelocity().clone().multiply(2.5));
				}
				if(p.isSneaking()) {
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("전이")
							.ename("Transition")
							.slot(0)
							.hm(rscooldown)
							.skillUse(() -> {
				                t.setMetadata("tras of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
				                ar1(t, p, 0.7*(1+fsd.Transition.get(p.getUniqueId())*0.03));
				                
							});
					bd.execute();
				}
			}
			
		}
	}

	final private boolean isTridentLaunched(Player p) {
		
		if(!damage.containsKey(p.getUniqueId())) {
			return false;
		}
		
		if(p.getInventory().getItemInMainHand()==null || p.getInventory().getItemInMainHand().getType() != Material.TRIDENT) {
			
			player_damage.put(p.getName(), damage.get(p.getUniqueId()));
			return true;
		}
		return false;
	}
	
	private void spawnAxolotl(Player p, Location l, Variant variant, Vector offset) {
	    Axolotl axolotl = (Axolotl) p.getWorld().spawnEntity(l.clone().add(offset), EntityType.AXOLOTL);
	    axolotl.setInvulnerable(true);
	    axolotl.setCustomName(p.getName());
	    axolotl.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), p.getName()));
	    axolotl.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	    axolotl.setMetadata("rob" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	    axolotl.setAdult();
	    axolotl.setBreed(false);
	    axolotl.setVariant(variant);
	    axolotl.setCollidable(false);
	    axs.put(p.getUniqueId(), axolotl);
	}
	
	public void Transition(ProjectileHitEvent d) 
	{

		if(d.getEntity() instanceof Trident) 
		{
			Trident t = (Trident)d.getEntity();
	        
			
			if(t.getShooter() instanceof Player) {
				Player p = (Player) t.getShooter();
				if(ClassData.pc.get(p.getUniqueId()) == 19) {
					t.setVelocity(t.getVelocity().zero());
				}
				if(t.hasMetadata("tras of "+p.getName())) {
					t.removeMetadata("tras of "+p.getName(), RMain.getInstance());
					AtomicInteger j = new AtomicInteger();
					Location l = t.getLocation();
					if(d.getHitBlock() !=null) {
						l = d.getHitBlock().getLocation();
					}
					else if(d.getHitEntity() !=null) {
						l = d.getHitEntity().getLocation();
					}
					for(Entity e: t.getWorld().getNearbyEntities(l, 4.3,3,4.3)) {

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
	            		if ((!(e == p))&& e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) 
						{
								LivingEntity le = (LivingEntity)e;
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
				                		le.getWorld().spawnParticle(Particle.FLASH, le.getLocation(), 2,1,1,1);
					                	t.getWorld().playSound(le.getLocation(), Sound.ITEM_TRIDENT_RETURN, 1, 1);
					                	
					                	atk0(0d, t.getDamage(), p, le);
										
					                }
					            }, j.incrementAndGet()*3);	
						}
					}
					if(Proficiency.getpro(p)>=1) {
						
						spawnAxolotl(p, l, Variant.WILD, new Vector(0, 0, 0));
						spawnAxolotl(p, l, Variant.BLUE, new Vector(1, 1, 1));
						spawnAxolotl(p, l, Variant.CYAN, new Vector(-1, 1, 1));
						spawnAxolotl(p, l, Variant.GOLD, new Vector(1, 1, -1));
						spawnAxolotl(p, l, Variant.LUCY, new Vector(-1, 1, -1));
						
                		List<Entity> ce =  t.getWorld().getNearbyEntities(l,6, 6, 6).stream().filter(e -> e instanceof LivingEntity)
                		   .sorted(Comparator.comparingDouble(e -> ((LivingEntity) e).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()))
                		   .toList();
                		
	                	for(Mob m: axs.get(p.getUniqueId())) {
							for(Entity e : ce) {

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
        	            		if ((!(e == p))&& e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) 
        						{
    								LivingEntity le = (LivingEntity)e;
    		                    	for(int dou = 0; dou <6; dou+=1) {
    			                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    						                @Override
    						                public void run() {
	        								m.teleport(le);
					                		le.getWorld().spawnParticle(Particle.DRIPPING_WATER, le.getLocation(), 10,1,1,1);
						                	m.getWorld().playSound(le.getLocation(), Sound.ENTITY_AXOLOTL_ATTACK, 1, 1);
						                	m.attack(le);
						                	if(le instanceof Mob) {
							                	m.setTarget(le);
						                	}
	        			                	atk1(0.05*(1+fsd.Transition.get(p.getUniqueId())*0.016), p, le);
	        			                	Holding.holding(p, le, 5l);
    					                	}
    					                    
    					                }
    		                    		, dou*3);  
									}
    								break;
    		                    	
        						}
    						}
                    	}
                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {

			                	for(Mob m: axs.get(p.getUniqueId())) {
			                		m.remove();
			                	}
			                	axs.removeAll(p.getUniqueId());
			                    
			                }
			            }, 40); 
					}

					if(Proficiency.getpro(p)>=2) {
						
                    	for(int dou = 0; dou <6; dou+=1) {
	                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									Location l = t.getLocation();
									if(d.getHitBlock() !=null) {
										l = d.getHitBlock().getLocation();
									}
									else if(d.getHitEntity() !=null) {
										l = d.getHitEntity().getLocation();
									}
			                		t.getWorld().spawnParticle(Particle.DRIPPING_WATER, l.clone().add(0, 5, 0), 500,2,1,2);
			                		t.getWorld().spawnParticle(Particle.CLOUD, l.clone().add(0, 5, 0), 200,2,1,2,0);
			                		t.getWorld().spawnParticle(Particle.FALLING_WATER, l.clone().add(0, 5, 0), 300,2,1,2);
										for(Entity e : t.getWorld().getNearbyEntities(l,6, 6, 6)) {

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
			        	            		if ((!(e == p))&& e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) 
			        						{
			        								LivingEntity le = (LivingEntity)e;
			        			                	atk1(0.2*(1+fsd.Transition.get(p.getUniqueId())*0.026), p, le);
			        			                	Holding.holding(p, le, 5l);
			        						}
										}
				                    
				                }
				            }, dou*4);  
                    	}
					}
				}
			}
		}
			
	}

	
	@SuppressWarnings("deprecation")
	public void ThrowCancel(PlayerDropItemEvent e)        
    {
		Player p = (Player)e.getPlayer();
		Item t = (Item) e.getItemDrop();
		ItemStack is = t.getItemStack();
		if(ClassData.pc.get(p.getUniqueId()) == 19 && e.getItemDrop().getItemStack().getType() == Material.TRIDENT) {
			if(!trci.containsKey(p.getUniqueId())) {
				trci.put(p.getUniqueId(), t.getUniqueId());
				t.setOwner(p.getUniqueId());
			}
		}
		if(ClassData.pc.get(p.getUniqueId()) == 19 && (is.getType()==Material.TRIDENT) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
		{
			e.setCancelled(true);
		}
	
    }

	
	public void Owner(EntityDropItemEvent e) 
	{
		if(e.getEntity() instanceof Trident) {
			Trident t = (Trident) e.getEntity();
			if(trc.containsValue(t.getUniqueId())) {
				Player p = (Player) t.getShooter();
				trci.put(p.getUniqueId(), e.getItemDrop().getUniqueId());
				e.getItemDrop().setOwner(p.getUniqueId());
				if(p.getRespawnLocation() !=null) {
		    		t.teleport(p.getRespawnLocation());
		    		e.getItemDrop().teleport(p.getRespawnLocation());
				}
				else {
	    			t.teleport(Bukkit.getWorlds().stream().filter(w -> w.getEnvironment() == Environment.NORMAL).findFirst().get().getSpawnLocation());
	    			e.getItemDrop().teleport(Bukkit.getWorlds().stream().filter(w -> w.getEnvironment() == Environment.NORMAL).findFirst().get().getSpawnLocation());
				}
				trc.remove(t.getUniqueId());
			}
		}
	}
	

	public void Owner(EntityPickupItemEvent ev) 
	{
		if(ev.getEntity() instanceof Player) {
			Player p = (Player) ev.getEntity();

			if(damage.containsKey(p.getUniqueId())) {
				damage.remove(p.getUniqueId());
			}
			if(ot.containsKey(p.getUniqueId()) && ev.getItem().getItemStack().getType() != Material.TRIDENT) {
				ev.setCancelled(true);
			}
			if(trci.containsKey(p.getUniqueId()) && ev.getItem().getUniqueId().equals(trci.get(p.getUniqueId()))) {
				trci.remove(p.getUniqueId());
			}
		
		}
	}
	
	public void Owner(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player && e.getEntity() instanceof Trident)
		{
			Player p = (Player)e.getEntity().getShooter();
			Trident t = (Trident) e.getEntity();
			
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 19 && !t.hasMetadata("fake")) {
				

				DamageGetter(p);
				damage.put(p.getUniqueId(), Pak.player_damage.getOrDefault(p.getName(), 0d));
				
			    t.setInvulnerable(true);
			    t.setPersistent(true);
				t.setMetadata("Towner", new FixedMetadataValue(RMain.getInstance(), p.getUniqueId()));
				
				trc.put(p.getUniqueId(), t.getUniqueId());
				
				if(pint.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(pint.get(p.getUniqueId()));
					pint.remove(p.getUniqueId());
				}
				int task1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
                		Holding.invur(p, 10l);
	                }
	            },0,5);	
				pint.put(p.getUniqueId(), task1);

				
				if(ot.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(ot.get(p.getUniqueId()));
					ot.remove(p.getUniqueId());
				}
					int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	if(t.getLocation().getWorld() != p.getWorld()) {
		                		t.teleport(p.getEyeLocation());
		                	}
		                	else {
			                	if(t.getLocation().getY()<=-110) {
			                		t.teleport(p.getEyeLocation());
			                	}
			                	if(t.getLocation().distance(p.getLocation()) > 150) {
			                		t.teleport(p.getEyeLocation());
			                	}
		                	}
		                	if(p.getLocation().getY()<= -110) {
		                		p.teleport(p.getLocation().add(0, 49, 0));
		                    	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 135, 0, false,false));
		                		t.teleport(p.getEyeLocation());
		                	}
		                }
					},0,1);
					ot.put(p.getUniqueId(), task);	
			}
			
		}
	}

	
	public void Owner(PlayerDeathEvent e) 
	{
		Player p = e.getEntity();
		if(trc.containsKey(p.getUniqueId())) {
			Trident t = (Trident) Bukkit.getEntity(trc.get(p.getUniqueId()));
			if(t == null) {
				return;
			}
			if(p.getRespawnLocation() !=null) {
	    		t.teleport(p.getRespawnLocation());
			}
			else {
    			t.teleport(Bukkit.getWorlds().stream().filter(w -> w.getEnvironment() == Environment.NORMAL).findFirst().get().getSpawnLocation());
			}
		}
	}
	

	
	public void Owner(PlayerRespawnEvent d) 
	{
		
		if(d.getPlayer() instanceof Player)
		{
			Player p = (Player)d.getPlayer();
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 19) {
				if(trci.containsKey(p.getUniqueId())) {
					if(Bukkit.getEntity(trci.get(p.getUniqueId())) != null) {
						Item tri = (Item) Bukkit.getEntity(trci.get(p.getUniqueId()));
						p.getInventory().addItem(tri.getItemStack());
						tri.remove();
					}
					trci.remove(p.getUniqueId());
				}
				if(pint.containsKey(p.getUniqueId())) {
					Bukkit.getServer().getScheduler().cancelTask(pint.get(p.getUniqueId()));
					pint.remove(p.getUniqueId());
				}
				if(ot.containsKey(p.getUniqueId())) {
					Bukkit.getServer().getScheduler().cancelTask(ot.get(p.getUniqueId()));
					ot.remove(p.getUniqueId());
				}
			}
			
		}
	}
	
	
	public void Owner(PlayerPickupArrowEvent ev) 
	{
		Player p = ev.getPlayer();
			if(ev.getArrow().getMetadata("Towner").stream().filter(v->v.value().equals(p.getUniqueId())).findFirst().isPresent()) {
				if(pint.containsKey(p.getUniqueId())) {
					Bukkit.getServer().getScheduler().cancelTask(pint.get(p.getUniqueId()));
					pint.remove(p.getUniqueId());
				}
				if(ot.containsKey(p.getUniqueId())) {
					Bukkit.getServer().getScheduler().cancelTask(ot.get(p.getUniqueId()));
					ot.remove(p.getUniqueId());
				}
				if(trc.containsKey(p.getUniqueId())) {
					trc.remove(p.getUniqueId());
				}
			}
	}
	
	
	public void Owner(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
	    
		
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 19) {
	
			if(p.getInventory().getItemInMainHand().getType().isAir() && p.isSneaking()) 
			{
				if(ac == Action.LEFT_CLICK_AIR)
				{
					p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_RETURN, 1, 0);
		    		p.getServer().getWorlds().forEach(w -> w.getEntities().stream().filter(e-> e.getMetadata("Towner").stream().filter(v->v.value().equals(p.getUniqueId())).findFirst().isPresent()).forEach(t -> {
		    			t.setVelocity(t.getVelocity().zero());
		    			t.teleport(p.getEyeLocation());
		    		}));
					if(trci.containsKey(p.getUniqueId())) {
						if(Bukkit.getEntity(trci.get(p.getUniqueId())) != null) {
							Item tri = (Item) Bukkit.getEntity(trci.get(p.getUniqueId()));
							p.getInventory().addItem(tri.getItemStack());
							tri.remove();
						}
						trci.remove(p.getUniqueId());
					}
				} 
			}
		}
	}


	
	public void GuardianSupport(PlayerItemHeldEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 19 && p.getInventory().getItemInMainHand().getType()==Material.TRIDENT && !p.isSneaking() && gst.containsKey(p.getUniqueId())){
			if(gs.containsKey(p.getUniqueId())) {
				for(LivingEntity le : gs.get(p.getUniqueId())) {
					le.remove();
				}
				gs.removeAll(p.getUniqueId());
				Bukkit.getServer().getScheduler().cancelTask(gst.get(p.getUniqueId()));
			}
		}
	}
	
	
	public void GuardianSupport2(EntityDamageByEntityEvent d) 
	{

			if(d.getDamager() instanceof LivingEntity&& d.getEntity() instanceof Player && !d.getEntity().hasMetadata("fake")&& !d.getEntity().hasMetadata("portal") && !d.isCancelled()) 
			{
					Player p = (Player) d.getEntity();
					LivingEntity le = (LivingEntity) d.getDamager();
					if(ClassData.pc.get(p.getUniqueId()) == 19 && fsd.GuardianSupport.getOrDefault(p.getUniqueId(), 0)>=1 && le!=p && !p.hasCooldown(Material.GUARDIAN_SPAWN_EGG)) {
						p.setCooldown(Material.GUARDIAN_SPAWN_EGG, 1);
						atk0(0.1d, fsd.GuardianSupport.get(p.getUniqueId())*1d, p, le);
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
			if(d.getDamager() instanceof Projectile&& d.getEntity() instanceof Player && !d.getEntity().hasMetadata("fake")&& !d.getEntity().hasMetadata("portal") && !d.isCancelled()) 
			{
					Player p = (Player) d.getEntity();
					Projectile pr = (Projectile) d.getDamager();
					if(ClassData.pc.get(p.getUniqueId()) == 19 && fsd.GuardianSupport.getOrDefault(p.getUniqueId(), 0)>=1  && pr.getShooter() instanceof LivingEntity) {
						 
						LivingEntity le = (LivingEntity) pr.getShooter();
						if(le !=p&& !p.hasCooldown(Material.GUARDIAN_SPAWN_EGG)) {
							p.setCooldown(Material.GUARDIAN_SPAWN_EGG, 1);
							atk0(0.1d, fsd.GuardianSupport.get(p.getUniqueId())*1d, p, le);
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
	}
	
	
	public void GuardianSupport(EntityDamageByEntityEvent d) 
	{
		 
			if(d.getDamager() instanceof Projectile&& d.getEntity() instanceof LivingEntity && !d.getEntity().hasMetadata("portal") &&!d.getEntity().hasMetadata("fake") && !d.isCancelled()) 
			{
				Projectile pr = (Projectile)d.getDamager();
				if(pr.getShooter() instanceof Player) {
					Player p = (Player) pr.getShooter();
					LivingEntity le = (LivingEntity) d.getEntity();
					if(ClassData.pc.get(p.getUniqueId()) == 19 && fsd.GuardianSupport.getOrDefault(p.getUniqueId(), 0)>=1  && p.getCooldown(Material.GUARDIAN_SPAWN_EGG)<=0) {
	
						if(!gs.containsKey(p.getUniqueId())) {
							p.setCooldown(Material.GUARDIAN_SPAWN_EGG, 1);
							if(Proficiency.getpro(p)>=1) {
			                	ElderGuardian cs = (ElderGuardian) p.getWorld().spawnEntity(p.getEyeLocation().add(0,1.56,0), EntityType.ELDER_GUARDIAN);
								cs.setInvulnerable(true);
								cs.setAI(false);
								cs.setSilent(true);
								cs.setCustomName(p.getName());
								cs.setMetadata("gd of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
								cs.setMetadata("gds", new FixedMetadataValue(RMain.getInstance(), true));
								cs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
								cs.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								cs.setGravity(false);
								cs.setCollidable(false);
								gs.put(p.getUniqueId(), cs);
								p.getCollidableExemptions().add(cs.getUniqueId());
								int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
				             			cs.teleport(p.getEyeLocation().clone().add(0,1.56,0));
					                }
								},2,2);
								gst.put(p.getUniqueId(), task);
							}
							else {
			                	Guardian cs = (Guardian) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(-Math.PI/2).multiply(1.5)), EntityType.GUARDIAN);
								cs.setInvulnerable(true);
								cs.setAI(false);
								cs.setSilent(true);
								cs.setCustomName(p.getName());
								cs.setMetadata("gd of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
								cs.setMetadata("gds", new FixedMetadataValue(RMain.getInstance(), true));
								cs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
								cs.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								cs.setGravity(false);
								cs.setCollidable(false);
								gs.put(p.getUniqueId(), cs);
			                    Guardian cs2 = (Guardian) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(Math.PI/2).multiply(1.5)), EntityType.GUARDIAN);
								cs2.setInvulnerable(true);
								cs2.setSilent(true);
								cs2.setAI(false);
								cs2.setCustomName(p.getName());
								cs2.setMetadata("gd of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
								cs2.setMetadata("gds", new FixedMetadataValue(RMain.getInstance(), true));
								cs2.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
								cs2.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								cs2.setGravity(false);
								cs2.setCollidable(false);
								gs.put(p.getUniqueId(), cs2);
								p.getCollidableExemptions().add(cs.getUniqueId());
								p.getCollidableExemptions().add(cs2.getUniqueId());
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
						}
						else {
							if(d.getDamage()>0 && d.getCause() != DamageCause.MAGIC && d.getCause() != DamageCause.THORNS) {
								if(Proficiency.getpro(p)>=1) {
									dset0(d, p, 0.8*(1+fsd.GuardianSupport.get(p.getUniqueId())*0.06));
								}
								else {
									dset0(d, p, 0.56*(1+fsd.GuardianSupport.get(p.getUniqueId())*0.06));
								}
								for(int i = 0; i <2; i++) {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					             		@Override
					                	public void run() 
					                	{
					             			le.playHurtAnimation(0);
					             			p.playSound(le.getEyeLocation(), Sound.ENTITY_GUARDIAN_FLOP, 0.8f, 2f);
							            }
									}, i*2);
								}
							}
						}
							
					}		
				}
			}
			if(d.getDamager() instanceof Player&& d.getEntity() instanceof LivingEntity && !d.getEntity().hasMetadata("fake")&& !d.getEntity().hasMetadata("portal") && !d.isCancelled()) 
			{
					Player p = (Player) d.getDamager();
					LivingEntity le = (LivingEntity) d.getEntity();
					if(ClassData.pc.get(p.getUniqueId()) == 19 && fsd.GuardianSupport.getOrDefault(p.getUniqueId(), 0)>=1  && p.getCooldown(Material.GUARDIAN_SPAWN_EGG)<=0) {
	
						if(!gs.containsKey(p.getUniqueId())) {
							if(Proficiency.getpro(p)>=1) {
			                	ElderGuardian cs = (ElderGuardian) p.getWorld().spawnEntity(p.getEyeLocation().add(0,1.56,0), EntityType.ELDER_GUARDIAN);
								cs.setInvulnerable(true);
								cs.setSilent(true);
								cs.setAI(false);
								cs.setCustomName(p.getName());
								cs.setMetadata("gd of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
								cs.setMetadata("gds", new FixedMetadataValue(RMain.getInstance(), true));
								cs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
								cs.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								cs.setGravity(false);
								cs.setCollidable(false);
								gs.put(p.getUniqueId(), cs);
								p.getCollidableExemptions().add(cs.getUniqueId());
								int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
				             			cs.teleport(p.getEyeLocation().clone().add(0,1.56,0));
					                }
								},2,2);
								gst.put(p.getUniqueId(), task);
							}
							else {
			                	Guardian cs = (Guardian) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(-Math.PI/2).multiply(1.5)), EntityType.GUARDIAN);
								cs.setInvulnerable(true);
								cs.setSilent(true);
								cs.setAI(false);
								cs.setCustomName(p.getName());
								cs.setMetadata("gd of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
								cs.setMetadata("gds", new FixedMetadataValue(RMain.getInstance(), true));
								cs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
								cs.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								cs.setGravity(false);
								cs.setCollidable(false);
								gs.put(p.getUniqueId(), cs);
			                    Guardian cs2 = (Guardian) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(Math.PI/2).multiply(1.5)), EntityType.GUARDIAN);
								cs2.setInvulnerable(true);
								cs2.setSilent(true);
								cs2.setAI(false);
								cs2.setCustomName(p.getName());
								cs2.setMetadata("gd of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
								cs2.setMetadata("gds", new FixedMetadataValue(RMain.getInstance(), true));
								cs2.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
								cs2.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								cs2.setGravity(false);
								cs2.setCollidable(false);
								gs.put(p.getUniqueId(), cs2);
								p.getCollidableExemptions().add(cs.getUniqueId());
								p.getCollidableExemptions().add(cs2.getUniqueId());
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
						}
						else {
							if(d.getDamage()>0 && d.getCause() != DamageCause.MAGIC && d.getCause() != DamageCause.THORNS) {
								if(Proficiency.getpro(p)>=1) {
									dset0(d, p, 0.8*(1+fsd.GuardianSupport.get(p.getUniqueId())*0.06));
								}
								else {
									dset0(d, p, 0.56*(1+fsd.GuardianSupport.get(p.getUniqueId())*0.06));
								}
								for(int i = 0; i <2; i++) {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					             		@Override
					                	public void run() 
					                	{
					             			le.playHurtAnimation(0);
					             			p.playSound(le.getEyeLocation(), Sound.ENTITY_GUARDIAN_FLOP, 0.8f, 2f);
							            }
									}, i*2);
								}
							}
						}
							
					}
			}
	}

	
	public void GuardianCurse(PlayerItemHeldEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 19 && p.getInventory().getItemInMainHand().getType()==Material.TRIDENT &&  p.isSneaking() && gs.containsKey(p.getUniqueId())){
			ev.setCancelled(true);
			double sec = 6*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
            Location fl = gettargetblock(p,20).clone();
			if(p.rayTraceBlocks(40) != null) {
				fl = p.rayTraceBlocks(40).getHitPosition().toLocation(p.getWorld());
            }
            final Location l = fl.clone();
			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(sec)
					.kname("가디언의저주")
					.ename("GuardianCurse")
					.slot(5)
					.hm(prcooldown)
					.skillUse(() -> {
	         			p.playSound(p.getEyeLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.5f, 1f);
	    				l.getWorld().spawnParticle(Particle.NAUTILUS, l, 60,3,3,3);
	    				l.getWorld().spawnParticle(Particle.BLOCK, l, 60,3,3,3, Material.DARK_PRISMARINE.createBlockData());
						for (Entity e : p.getWorld().getNearbyEntities(l, 6,6, 6))
						{
							if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
							{
								LivingEntity le = (LivingEntity)e;
									{
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
										gs.get(p.getUniqueId()).forEach(g -> {
											g.playEffect(EntityEffect.GUARDIAN_TARGET);
											((Guardian)g).setLaser(true);
										});
					                	atk1(0.33*(1+fsd.GuardianSupport.get(p.getUniqueId())*0.056), p, le);
										le.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 30, 2, false,false));
										le.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 30, 2, false,false));
									}
							}
						}
					});
			bd.execute();
		}
	}
	
	
	
	
	public void delete(PlayerDeathEvent d) 
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
		if(trc.containsKey(p.getUniqueId())) {
			Trident t = (Trident) Bukkit.getEntity(trc.get(p.getUniqueId()));
			if(t == null) {
				return;
			}
			if(p.getRespawnLocation() !=null) {
	    		t.teleport(p.getRespawnLocation());
			}
			else {
    			t.teleport(Bukkit.getWorlds().stream().filter(w -> w.getEnvironment() == Environment.NORMAL).findFirst().get().getSpawnLocation());
			}
		}
		if(trci.containsKey(p.getUniqueId())) {
			if(Bukkit.getEntity(trci.get(p.getUniqueId())) != null) {
				Item tri = (Item) Bukkit.getEntity(trci.get(p.getUniqueId()));
				p.getInventory().addItem(tri.getItemStack());
				tri.remove();
			}
			trci.remove(p.getUniqueId());
		}
		if(pint.containsKey(p.getUniqueId())) {
			Bukkit.getServer().getScheduler().cancelTask(pint.get(p.getUniqueId()));
			pint.remove(p.getUniqueId());
		}
		if(ot.containsKey(p.getUniqueId())) {
			Bukkit.getServer().getScheduler().cancelTask(ot.get(p.getUniqueId()));
			ot.remove(p.getUniqueId());
		}
	}
	
	final private void Dolphinleft(Player p) {

    	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100, 0, false,false));
		p.getWorld().spawnParticle(Particle.SPLASH, dol.get(p.getUniqueId()).getLocation(), 60,3,3,3);
		p.leaveVehicle();
		for(Entity e: dol.get(p.getUniqueId()).getWorld().getNearbyEntities(dol.get(p.getUniqueId()).getLocation(), 5,5,5)) {
			isTridentLaunched(p);
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
    		if ((!(e == p))&& e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) 
			{
					LivingEntity le = (LivingEntity)e;
                	atk1(0.36*(1+fsd.DolphinSurf.get(p.getUniqueId())*0.03), p, le);	
					
			}
		}
		if(Proficiency.getpro(p)>=1) {
			Location doll = dol.get(p.getUniqueId()).getLocation();
            GlowSquid cs = (GlowSquid) p.getWorld().spawnEntity(doll, EntityType.GLOW_SQUID);
			cs.setInvulnerable(true);
			cs.setAI(false);
			cs.setGravity(false);
			cs.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(0.5));
			cs.setCustomName(p.getName());
			cs.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			cs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			cs.setCollidable(false);
			for(int i = 0 ; i <3; i ++) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
	                {	
            			isTridentLaunched(p);
        				doll.getWorld().spawnParticle(Particle.GLOW, doll, 60,3,3,3);
	                    p.playSound(doll, Sound.ENTITY_GLOW_SQUID_SQUIRT, 1.0f, 2f);
    					for(Entity e: doll.getWorld().getNearbyEntities(doll, 5,5,5)) {
    						
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
    		        		if ((!(e == p))&& e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) 
    						{
    								LivingEntity le = (LivingEntity)e;
    			                	atk1(0.36*(1+fsd.DolphinSurf.get(p.getUniqueId())*0.03), p, le);
    								Holding.holding(p, le, 10l);
    								
    						}
    					}
		            }
				}, i*10+10);
 			}
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
            	public void run() 
                {	
    				cs.remove();
	            }
			},50);
		}
			Bukkit.getScheduler().cancelTask(dolrt.get(p.getUniqueId()));
			Bukkit.getScheduler().cancelTask(dolt.get(p.getUniqueId()));
    	dol.get(p.getUniqueId()).remove();   
    	dolh.get(p.getUniqueId()).remove();   
    	dolrt.remove(p.getUniqueId());
    	dolt.remove(p.getUniqueId());
    	dolh.remove(p.getUniqueId());
    	dol.remove(p.getUniqueId());
	}
	
	public void Dolphinleft(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
	    
		
		
		if((ac == Action.RIGHT_CLICK_AIR || ac==Action.RIGHT_CLICK_BLOCK) &&dolt.containsKey(p.getUniqueId()) && dol.containsKey(p.getUniqueId()) && ClassData.pc.get(p.getUniqueId()) == 19) {

			 Dolphinleft(p);
		}
	}

	
	@SuppressWarnings("deprecation")
	public void DolphinSurf(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =11*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
        
		
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 19 && fsd.DolphinSurf.getOrDefault(p.getUniqueId(), 0)>=1 ) {
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT &&!p.isSneaking()&& !p.isOnGround()&& !p.hasCooldown(CAREFUL)) 
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("돌고래타기")
							.ename("DolphinSurf")
							.slot(1)
							.hm(jmcooldown)
							.skillUse(() -> {
								p.setCooldown(Material.DOLPHIN_SPAWN_EGG,5);
								Snowball ds = (Snowball) p.getWorld().spawnEntity(p.getLocation(), EntityType.SNOWBALL);
								ds.setSilent(true);
								ds.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
								ds.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								ds.setInvulnerable(true);
								ds.setGravity(false);
								ds.setVisibleByDefault(false);
								ds.setItem(new ItemStack(Material.HEART_OF_THE_SEA));
								ds.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(0.5));
								ds.addPassenger(p);
								Location dslf = ds.getLocation().clone().add(0, 0.1, 0);
								dslf.setDirection(p.getEyeLocation().clone().getDirection());
								dslf.add(dslf.getDirection().clone().normalize().multiply(3));
			                    Dolphin cs = (Dolphin) p.getWorld().spawnEntity(dslf, EntityType.DOLPHIN);
								cs.setInvulnerable(true);
								cs.setAI(false);
								cs.setGravity(false);
								cs.setCollidable(false);
								cs.setCustomName(p.getName());
								cs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
								cs.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								cs.setCollidable(false);
								dol.put(p.getUniqueId(), cs);
								dolh.put(p.getUniqueId(), ds);
								int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
				             			if(cs.isValid()) {
					             			if(ds.getLocation().add(ds.getVelocity().normalize().multiply(1)).getBlock().isPassable()){
												ds.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(1));
												Location dsl = ds.getLocation().clone();
												dsl.setDirection(p.getEyeLocation().clone().getDirection());
												dsl.add(dsl.getDirection().clone().normalize().multiply(3));
												cs.teleport(dsl);
					             			}
					             			else {

					             				 Dolphinleft(p);
					             			}
				             			}
					                    p.playSound(p.getLocation(), Sound.ENTITY_BOAT_PADDLE_WATER, 1.0f, 2f);
					                	
					                
					                }
								},1,1);
								int task2= Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				             		@Override
				                	public void run() 
					                {	      
				           			 Dolphinleft(p);
						            }
								}, 61);
								dolrt.put(p.getUniqueId(), task2);
								dolt.put(p.getUniqueId(), task);
								dol.put(p.getUniqueId(), cs);
								dolh.put(p.getUniqueId(), ds);
							});
					bd.execute();
		    					
				} 
			}
		
		}
	}
	private void spawnEntity(Player p, Location baseLocation, EntityType entityType, Vector offset) {
	    Mob entity = (Mob) p.getWorld().spawnEntity(baseLocation.add(offset), entityType);
	    entity.setInvulnerable(true);
	    entity.setAI(false);
	    entity.setCollidable(false);
	    entity.setCustomName(p.getName());
	    entity.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), p.getName()));
	    entity.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	    entity.setMetadata("rob" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	    as.put(p.getUniqueId(), entity);
	}

	public void Assault(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec = 11*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 19 && fsd.Assault.getOrDefault(p.getUniqueId(), 0)>=1 ) {
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT && p.isSneaking())
			{
				
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("돌격")
						.ename("Assault")
						.slot(2)
						.hm(thcooldown)
						.skillUse(() -> {

						spawnEntity(p, p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(-Math.PI/6).multiply(1.5)), EntityType.PUFFERFISH, new Vector(0, 0, 0));
						spawnEntity(p, p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(Math.PI/6).multiply(1.5)), EntityType.TROPICAL_FISH, new Vector(1, 1, 1));
						spawnEntity(p, p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(Math.PI/2).multiply(1.5)), EntityType.COD, new Vector(-1, 1, 1));
						spawnEntity(p, p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(-Math.PI/2).multiply(1.5)), EntityType.SALMON, new Vector(1, 1, -1));
						spawnEntity(p, p.getLocation().add(p.getLocation().getDirection().multiply(1.5)), EntityType.TURTLE, new Vector(-1, 1, -1));
						spawnEntity(p, p.getEyeLocation().add(0, 2, 0), EntityType.SQUID, new Vector(0, 2, 0));
							
							p.playSound(p.getLocation(), Sound.AMBIENT_UNDERWATER_ENTER, 1, 2);
	                		int task1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	
				        			isTridentLaunched(p);
				        			Mob cs = as.get(p.getUniqueId()).iterator().next();
				        			
				        			
									cs.getWorld().spawnParticle(Particle.FALLING_WATER, cs.getLocation(), 20, 1,1,1,0);
									cs.getWorld().spawnParticle(Particle.BUBBLE, cs.getLocation(), 10, 1,1,1,0.1);
									cs.getWorld().spawnParticle(Particle.SPLASH, cs.getLocation(), 10, 1,1,1,0.1);
									p.playSound(cs.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
									p.playSound(cs.getLocation(), Sound.ITEM_ARMOR_EQUIP_TURTLE, 1, 0);
										for(Entity e : cs.getWorld().getNearbyEntities(cs.getLocation(),5, 5, 5)) {

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
			        	            		if ((!(e == p))&& e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) 
			        						{
			        								LivingEntity le = (LivingEntity)e;
			        								le.teleport(le.getLocation().add(0, 0.02, 0));
			        			                	atk1(0.35*(1+fsd.Assault.get(p.getUniqueId())*0.036), p, le);
			        										
			        						}
										}
				                    
				                }
				            }, 0,10);  
	                		int task3 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	for(Mob m: as.get(p.getUniqueId())) {
				                		m.teleport(m.getLocation().setDirection(p.getLocation().getDirection()).add(p.getLocation().getDirection().normalize().multiply(2)));
				                		m.getLocation().setDirection(p.getLocation().getDirection());
				                	}
				                    
				                }
				            }, 0,10);  
	                		int task2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	
				                	Bukkit.getScheduler().cancelTask(ast.get(p.getUniqueId()));
				                	ast.remove(p.getUniqueId());
				                	asrt.remove(p.getUniqueId());
				                	for(Mob m: as.get(p.getUniqueId())) {
				                		m.remove();
				                	}
				                	as.removeAll(p.getUniqueId());
				                    
				                }
				            }, 101); 
	                		int task4 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	
				                	Bukkit.getScheduler().cancelTask(asmt.get(p.getUniqueId()));
				                	asmt.remove(p.getUniqueId());
				                	asmrt.remove(p.getUniqueId());
				                    
				                }
				            }, 101); 
	                		ast.put(p.getUniqueId(), task1);
	                		asrt.put(p.getUniqueId(), task2);
	                		asmt.put(p.getUniqueId(), task3);
	                		asmrt.put(p.getUniqueId(), task4);
	                		
		                	if(trrnt.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(trrnt.get(p.getUniqueId()));
		                		trrnt.remove(p.getUniqueId());
		                	}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									if(Proficiency.getpro(p)>=1) {
										trrn.putIfAbsent(p.getUniqueId(),as);
									}
				                }
				            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									trrn.remove(p.getUniqueId());
				                }
				            }, 100); 
		                	trrnt.put(p.getUniqueId(), task);
						});
				bd.execute();
				}
		}
	}
	

	
	public void HoldPosition(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 19&&trrn.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT && p.isSneaking())
			{
				ev.setCancelled(true);
            	if(trrnt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(trrnt.get(p.getUniqueId()));
            		trrnt.remove(p.getUniqueId());
            	}
				trrn.remove(p.getUniqueId());

            	if(ppcrst.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(ppcrst.get(p.getUniqueId()));
            		ppcrst.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							ppcrs.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						ppcrs.remove(p.getUniqueId());
	                }
	            }, 25); 
            	ppcrst.put(p.getUniqueId(), task);
            	
            	Bukkit.getScheduler().cancelTask(asmt.get(p.getUniqueId()));
            	asmt.remove(p.getUniqueId());
            	Bukkit.getScheduler().cancelTask(asmrt.get(p.getUniqueId()));
            	asmrt.remove(p.getUniqueId());
				p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_HIT_GROUND, 1, 0);
				AtomicInteger j = new AtomicInteger();
				
            	for(Mob m: as.get(p.getUniqueId())) {

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		        			isTridentLaunched(p);
		    				p.getWorld().spawnParticle(Particle.CRIT, m.getLocation(), 60, 2,2,2,0.1);
		    				p.getWorld().spawnParticle(Particle.DOLPHIN, m.getLocation(), 300, 5,1,5,0.1);
		    				p.playSound(m.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1, 2);
		    				for(Entity e : m.getWorld().getNearbyEntities(m.getLocation(),5, 5, 5)) {

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
		                		if ((!(e == p))&& e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) 
		    					{
		    							LivingEntity le = (LivingEntity)e;
		    		                	atk1(0.1*(1+fsd.Assault.get(p.getUniqueId())*0.015), p, le);
		    		                	Holding.holding(p, le, 10l);
		    							
		    					}
		    				}
		                }
		            }, j.incrementAndGet()*3); 
            	}
				
			}
		}
	}

	
	public void SprayAttack(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 19&&ppcrs.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT && p.isSneaking())
			{
				ev.setCancelled(true);

            	if(ppcrst.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(ppcrst.get(p.getUniqueId()));
            		ppcrst.remove(p.getUniqueId());
            	}
				ppcrs.remove(p.getUniqueId());

				p.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 1, 2);
				

				AtomicInteger j = new AtomicInteger();
            	for(Mob m: as.get(p.getUniqueId())) {

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		        			isTridentLaunched(p);
		    				m.getWorld().spawnParticle(Particle.GLOW_SQUID_INK, m.getLocation(), 20, 2,2,2,0.1);
		    				m.getWorld().spawnParticle(Particle.SQUID_INK, m.getLocation(), 40, 2,2,2,0.1);
		    				m.getWorld().spawnParticle(Particle.SPLASH, m.getLocation(), 40, 2,2,2,0.1);
		    				p.playSound(m.getLocation(), Sound.ITEM_INK_SAC_USE, 1, 0);
		    				p.playSound(m.getLocation(), Sound.ENTITY_GLOW_SQUID_SQUIRT, 1, 0);
		    				p.playSound(m.getLocation(), Sound.ENTITY_GLOW_SQUID_SQUIRT, 1, 2);
		    				for(Entity e : m.getWorld().getNearbyEntities(m.getLocation(),5, 5, 5)) {

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
		                		if ((!(e == p))&& e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) 
		    					{
		    							LivingEntity le = (LivingEntity)e;
		    		                	atk1(0.2*(1+fsd.Assault.get(p.getUniqueId())*0.015), p, le);
		    		                	Holding.holding(p, le, 10l);
		    					}
		    				}
		                }
		            }, j.incrementAndGet()*3); 
            	}
				
			}
		}
	}
	
	static private ArrayList<Location> WaterWheel(Location il, int a) {

		ArrayList<Location> line = new ArrayList<Location>();
        for(double an=0; an > -Math.PI*2; an -= Math.PI/180) {
        	Location eye = il.clone().add(il.clone().getDirection().multiply(a*2));
        	Vector eyev = eye.getDirection().clone().rotateAroundY(Math.PI/2);
        	Location al = eye.clone().setDirection(eye.getDirection().rotateAroundAxis(eyev, an).normalize());
        	line.add(al.add(al.getDirection().clone().normalize().multiply(2)));
        }
        line.forEach(l -> {
    		l.getWorld().spawnParticle(Particle.CRIT, l,1,0.1,0.1,0.1,0);
    		l.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l,1,0.1,0.1,0.1,0);
    		l.getWorld().spawnParticle(Particle.DOLPHIN, l,3,0.5,0.5,0.5,0);
        	
        });
        return line;
	}


	public void WaterWheel(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec =10*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 19 && fsd.WaterWheel.getOrDefault(p.getUniqueId(), 0)>=1 ) {
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT && !p.isSneaking())
			{
				

				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("물바퀴")
						.ename("WaterWheel")
						.slot(3)
						.hm(pncooldown)
						.skillUse(() -> {

		                	if(bckwsht.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(bckwsht.get(p.getUniqueId()));
		                		bckwsht.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							bckwsh.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 20); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						bckwsh.remove(p.getUniqueId());
		    	                }
		    	            }, 80); 
		                	bckwsht.put(p.getUniqueId(), task);
			            	
	                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 2);

	                        final Location pl = p.getEyeLocation().clone();

	                        ArrayList<Location> line = new ArrayList<Location>();
	                    	AtomicInteger j = new AtomicInteger();
	                    	for(int i =0; i<15; i++) {
	    	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    				                @Override
	    				                public void run() 
	    				                {
	    				        			isTridentLaunched(p);
	    				                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	    				                	WaterWheel(pl,j.incrementAndGet()).forEach(bl -> line.add(bl));
	    				                    for(Location l : line) {

	    				                    	for (Entity a : l.getWorld().getNearbyEntities(l, 1, 3, 1))
	    										{
	    											if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
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
	    												LivingEntity le = (LivingEntity)a;
	    												les.add(le);
	    											}
	    										}
	    				                    }
	    									for(LivingEntity le: les) {
	    										p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
	    										atk1(0.15*(1+fsd.WaterWheel.get(p.getUniqueId())*0.02), p, le);
	    									}
	    				                }
	    	                	   }, i*2); 
	                    	}
						});
				bd.execute();
				}
		}
	}

	

	
	public void Release(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 19 && bckwsh.containsKey(p.getUniqueId())&& !p.hasCooldown(CAREFUL)) {
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT &&!p.isSneaking()) 
			{
					ev.setCancelled(true);
		            Location fl = gettargetblock(p,20).clone();
	                final Location tl = fl.clone();
	                
	            	if(bckwsht.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(bckwsht.get(p.getUniqueId()));
	            		bckwsht.remove(p.getUniqueId());
	            	}
					bckwsh.remove(p.getUniqueId());
					


	            	if(clvet.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(clvet.get(p.getUniqueId()));
	            		clvet.remove(p.getUniqueId());
	            	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=2) {
								clve.putIfAbsent(p.getUniqueId(), 0);
							}
		                }
		            }, 5); 

	        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							clve.remove(p.getUniqueId());
		                }
		            }, 30); 
	            	clvet.put(p.getUniqueId(), task);

                    p.playSound(tl, Sound.ITEM_TRIDENT_THUNDER, 0.5f, 1.5f);
                    
                    HashSet<Trident> ths = new HashSet<>();
                    
	            	for(int i = 0; i <8; i++) {
	            		Random ran = new Random();
	            		double ri = (ran.nextDouble()+2) * (ran.nextBoolean() ? 1:-1);
                        Trident cs = (Trident) p.getWorld().spawnEntity(tl.clone().add(ri, 3, ri), EntityType.TRIDENT);
    					cs.setInvulnerable(true);
    					cs.setGravity(false);
    					cs.setGlowing(true);
    					cs.setGravity(false);
    					cs.setWeapon(new ItemStack(Material.CROSSBOW));
    					cs.setCustomName(p.getName());
    					cs.setShooter(p);
    					cs.setDamage(0);
    					cs.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
    					cs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
    					cs.setPickupStatus(PickupStatus.DISALLOWED);

						ths.add(cs);
	            	}

	            	AtomicInteger j = new AtomicInteger();
	            	ths.forEach(t -> {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {

			        			isTridentLaunched(p);
			                	t.setGravity(true);
			                    p.playSound(tl, Sound.ITEM_TRIDENT_THROW, 0.1f, 0);
			                    p.playSound(tl, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.1f, 2);
								tl.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 50, 5,5,5,0);
								tl.getWorld().spawnParticle(Particle.SPLASH, tl, 200, 5,5,5,0);
								tl.getWorld().spawnParticle(Particle.ENCHANTED_HIT, tl, 20, 5,5,5,0);
								for (Entity e : tl.getWorld().getNearbyEntities(tl, 6, 6, 6))
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
									if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
										LivingEntity le = (LivingEntity)e;
										t.teleport(le);
					        			atk1(0.16*(1+fsd.WaterWheel.get(p.getUniqueId())*0.05), p, le);
										Holding.holding(p, le, 5l);
									}
								}

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	t.remove();
					                }
								},15);
			                }
			            }, j.incrementAndGet()*3+20); 
	            	});
			}
		
		}
	}

	
	
	public void EyesOfSea(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 19 && clve.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT &&!p.isSneaking() && !p.hasCooldown(CAREFUL)) 
			{
					ev.setCancelled(true);

		            Location fl = gettargetblock(p,20).clone();
	                final Location tl = fl.clone().add(0, 1.6, 0);
	                
	            	if(clvet.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(clvet.get(p.getUniqueId()));
	            		clvet.remove(p.getUniqueId());
	            	}
					clve.remove(p.getUniqueId());

                    HashSet<Location> cir = new HashSet<>();
                    HashSet<Location> eye = new HashSet<>();
                    HashSet<LivingEntity> les = new HashSet<>();

            		
            		for(int ix = -4; ix<4; ix++) {
            			for(int iy = -3; iy<3; iy++) {
            				for(int iz = -4; iz<4; iz++) {
            					if((ix*ix + iy*iy + iz*iz<=16)){
            						cir.add(tl.clone().add(ix, iy, iz));
            					}
            				}
            			}
            		}
            		for(int ix = -4; ix<4; ix++) {
            			for(int iy = -2; iy<2; iy++) {
            				for(int iz = -2; iz<2; iz++) {
            					if((ix*ix/16 + iy*iy/4 + iz*iz/4<=1)){
            						eye.add(tl.clone().add(ix, iy, iz));
            					}
            				}
            			}
            		}
                    p.playSound(tl, Sound.ENTITY_ELDER_GUARDIAN_HURT, 0.5f, 0);
                    p.playSound(tl, Sound.BLOCK_CONDUIT_AMBIENT, 0.5f, 2);
                	cir.forEach(l -> {
						p.getWorld().spawnParticle(Particle.DRIPPING_DRIPSTONE_WATER, l, 50, 1,1,1,0);
						for(Entity e : p.getWorld().getNearbyEntities(l,3.5, 3.5, 3.5)) {
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
							if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
								LivingEntity le = (LivingEntity)e;
								les.add(le);	
								le.teleport(tl);
							}
						}
                	});

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		        			isTridentLaunched(p);
		                    p.playSound(tl, Sound.BLOCK_CONDUIT_ACTIVATE, 1, 0);
		                    p.playSound(tl, Sound.BLOCK_CONDUIT_ATTACK_TARGET, 1, 0);


		                	eye.forEach(l -> {
								tl.getWorld().spawnParticle(Particle.SQUID_INK, tl, 60, 0.3,0.3,0.3,0);
		                	});
							tl.getWorld().spawnParticle(Particle.GLOW_SQUID_INK, tl, 300, 0.6,0.6,0.6,0.05);
							tl.getWorld().spawnParticle(Particle.NAUTILUS, tl, 100, 1,1,1,0.05);
							tl.getWorld().spawnParticle(Particle.FLASH, tl, 10, 0.1,0.1,0.1,0);
							tl.getWorld().spawnParticle(Particle.GLOW, tl, 200, 1,1,1,0);
		                    for(LivingEntity le: les) {
	                    		atk1(0.95*(1+fsd.WaterWheel.get(p.getUniqueId())*0.52), p, le);
								Holding.holding(p, le, 15l);
											                    	
		                    }
		                }
					},20);
					
				
			}
		
		}
	}
	
	
	
	public void Storm(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =5.5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024)/(Proficiency.getpro(p)>=2 ? 2:1);
        
		
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 19 && fsd.Storm.getOrDefault(p.getUniqueId(), 0)>=1 ) {
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT &&p.isSneaking()&& !p.hasCooldown(CAREFUL)) 
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("폭풍")
							.ename("Storm")
							.slot(4)
							.hm(eccooldown)
							.skillUse(() -> {

								if(!marked.containsKey(p.getUniqueId())) {
									return;
								}
			                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.5f, 0);
			                	ArrayList<LivingEntity> les = new ArrayList<>();
			                	marked.get(p.getUniqueId()).forEach(e -> les.add(e));
			                	les.stream().filter(le -> !le.isDead()).forEach(le ->{
			                		
			                		final World lw = le.getWorld();
			                		final Location lel = le.getLocation().clone();
				                	p.getWorld().playSound(lel, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.5f, 0.9f);
			                		lw.strikeLightningEffect(lel);
			                		lw.spawnParticle(Particle.DOLPHIN, lel, 200,1,1,1);
			                		lw.spawnParticle(Particle.DRIPPING_WATER, lel, 200,1,1,1);
			                		lw.spawnParticle(Particle.WHITE_ASH, lel, 200,1,1,1);
			                		lw.spawnParticle(Particle.FALLING_WATER, lel.clone().add(0, 2, 0), 200,1,1,1);
			                		lw.spawnParticle(Particle.CLOUD, lel.clone().add(0, 2, 0), 60,1.5,1.5,1.5,0);
				                	atk1(0.96*(1+fsd.Storm.get(p.getUniqueId())*0.0443), p, le);
			                	});
			                	if(Proficiency.getpro(p) >=1) {

			                		Turtle cs = (Turtle) p.getWorld().spawnEntity(p.getEyeLocation().add(0,1.5,0), EntityType.TURTLE);
			    					cs.setInvulnerable(true);
			    					cs.setAI(false);
			    					cs.setGravity(false);
			    					cs.setCollidable(false);
			    					cs.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(0.5));
			    					cs.setCustomName(p.getName());
			    					cs.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			    					cs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    					cs.setCollidable(false);
			    					cs.setGlowing(true);
			    					AtomicInteger j = new AtomicInteger();
			    					LinkedHashSet<LivingEntity> s1 = new LinkedHashSet<LivingEntity>(les);
			    					s1.stream().filter(le -> !le.isDead()).forEach(le -> {

			        					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			        	             		@Override
			        	                	public void run() 
			        		                {	
			        	            			isTridentLaunched(p);
			        	        				p.getWorld().spawnParticle(Particle.GLOW, cs.getLocation(), 60,3,3,3);
			        		                    p.playSound(p.getLocation(), Sound.ENTITY_TURTLE_SHAMBLE_BABY, 0.6f, 1f);
			    								cs.teleport(le);
			        		                    p.playSound(cs.getLocation(), Sound.ENTITY_TURTLE_EGG_CRACK, 0.6f, 1f);
			    			                	atk1(0.3*(1+fsd.Storm.get(p.getUniqueId())*0.03), p, le);
			    								Holding.holding(p, le, 10l);
			        			            }
			        					},j.incrementAndGet()*2);
			    					});
			    					Collections.reverse(les);
			    					LinkedHashSet<LivingEntity> s2 = new LinkedHashSet<LivingEntity>(les);
			    					s2.stream().filter(le -> !le.isDead()).forEach(le -> {

			        					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			        	             		@Override
			        	                	public void run() 
			        		                {	
			        	            			isTridentLaunched(p);
			        	        				p.getWorld().spawnParticle(Particle.GLOW, cs.getLocation(), 60,3,3,3);
			        		                    p.playSound(p.getLocation(), Sound.ENTITY_TURTLE_SHAMBLE_BABY, 0.6f, 1f);
			    								cs.teleport(le);
			        		                    p.playSound(cs.getLocation(), Sound.ENTITY_TURTLE_EGG_CRACK, 0.6f, 1f);
			    			                	atk1(0.3*(1+fsd.Storm.get(p.getUniqueId())*0.03), p, le);
			    								Holding.holding(p, le, 10l);
			        			            }
			        					},j.incrementAndGet()*2);
			    					});
			    					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			    	             		@Override
			    	                	public void run() 
			    		                {	
			    	        				cs.remove();
			    			            }
			    					},j.get()+30);
			                	}
			                	marked.removeAll(p.getUniqueId());
							});
					bd.execute();
							
					
		    					
				} 
			}
		
		}
	}

	
	final private void whp(Location pl, Location tl, final World w) {
    	ArrayList<Location> wh = new ArrayList<>();
    	AtomicInteger j = new AtomicInteger();
    	for(int i = 0; i<10; i++) {
        	for(double an = 0; an<Math.PI*2; an +=Math.PI/90) {
    			wh.add(tl.clone().add(pl.getDirection().normalize().rotateAroundY(an+i).multiply(an)).add(0, an, 0));
    			wh.add(tl.clone().add(0, an*i*0.5, 0));
        	}
    	}
    	wh.forEach(l -> {
    		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
					w.spawnParticle(Particle.DOLPHIN, l, 2, 1,1,1,0);
					w.spawnParticle(Particle.CLOUD, l, 2, 0.1,0.1,0.1,0);
                }
            }, j.incrementAndGet()/1500);  
    	}); 
	}
		
	
	public void ULT(PlayerItemHeldEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		ItemStack is = p.getInventory().getItemInMainHand();
		
		if(ClassData.pc.get(p.getUniqueId()) == 19 && is.getType()==Material.TRIDENT && ev.getNewSlot()==3 && p.isSneaking() && Proficiency.getpro(p) >=1)
			{
				ev.setCancelled(true);
				p.setCooldown(CAREFUL, 2);
				final Location pl = p.getLocation();
	            Location tl = gettargetblock(p,11).clone();
				final World pw = p.getWorld();

				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(70/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
						.kname("바다소용돌이")
						.ename("Whirlpool")
						.slot(6)
						.hm(aultcooldown)
						.skillUse(() -> {
		                    Holding.invur(p, 100l);
		                    
		                    whp(pl,tl,pw);
		                	for(int i = 0; i<10; i++) {
		                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	p.playSound(tl, Sound.ENTITY_WITHER_AMBIENT, 0.5f, 0);

				                		for(Entity e: p.getWorld().getNearbyEntities(tl, 9,9,9)) {

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
			        	            		if ((!(e == p))&& e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) 
			        						{
			        	            			isTridentLaunched(p);
			        								LivingEntity le = (LivingEntity)e;
			        			                	atk1(0.46, p, le);
			        								le.teleport(le.getLocation().add(0, 0.02, 0));
			        			                	Holding.holding(p, le, 40l);
			        										
			        						}
			        					}
					                }
					            }, i*3); 
		                	}

		            		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	p.playSound(tl, Sound.ITEM_TRIDENT_THUNDER, 0.5f, 0);
									pw.spawnParticle(Particle.FLASH, tl.clone().add(0,6,0), 50, 3,3,3,0);

				                    for(int i = 3; i<11; i++) {
				        				FallingBlock fallingb = pw.spawnFallingBlock(tl.clone().add(0,6+i,0),  Material.PRISMARINE.createBlockData());
				        				fallingb.setInvulnerable(true);
				        				fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
				        				fallingb.setMetadata("whpt", new FixedMetadataValue(RMain.getInstance(),p.getName()));
				        				fallingb.setDropItem(true);
				        				fallingb.setHurtEntities(true);
				                    }
				                    for(int h = 6; h<9; h++) {
				        				FallingBlock fallingb = pw.spawnFallingBlock(tl.clone().add(1.5, h, 1.5),  Material.SEA_LANTERN.createBlockData());
				        				fallingb.setInvulnerable(true);
				        				fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
				        				fallingb.setMetadata("whpt", new FixedMetadataValue(RMain.getInstance(),p.getName()));
				        				fallingb.setVisualFire(true);
				        				fallingb.setDropItem(true);
				        				fallingb.setHurtEntities(true);
				        				FallingBlock fallingb1 = pw.spawnFallingBlock(tl.clone().add(1.5, h, -1.5),  Material.SEA_LANTERN.createBlockData());
				        				fallingb1.setInvulnerable(true);
				        				fallingb1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
				        				fallingb1.setMetadata("whpt", new FixedMetadataValue(RMain.getInstance(),p.getName()));
				        				fallingb1.setVisualFire(true);
				        				fallingb1.setDropItem(true);
				        				fallingb1.setHurtEntities(true);
				        				FallingBlock fallingb2 = pw.spawnFallingBlock(tl.clone().add(-1.5, h, 1.5),  Material.SEA_LANTERN.createBlockData());
				        				fallingb2.setInvulnerable(true);
				        				fallingb2.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
				        				fallingb2.setMetadata("whpt", new FixedMetadataValue(RMain.getInstance(),p.getName()));
				        				fallingb2.setVisualFire(true);
				        				fallingb2.setDropItem(true);
				        				fallingb2.setHurtEntities(true);
				        				FallingBlock fallingb3 = pw.spawnFallingBlock(tl.clone().add(-1.5, h, -1.5),  Material.SEA_LANTERN.createBlockData());
				        				fallingb3.setInvulnerable(true);
				        				fallingb3.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
				        				fallingb3.setMetadata("whpt", new FixedMetadataValue(RMain.getInstance(),p.getName()));
				        				fallingb3.setVisualFire(true);
				        				fallingb3.setDropItem(true);
				        				fallingb3.setHurtEntities(true);
				        				FallingBlock fallingb4 = pw.spawnFallingBlock(tl.clone().add(0, h, 0),  Material.SEA_LANTERN.createBlockData());
				        				fallingb4.setInvulnerable(true);
				        				fallingb4.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
				        				fallingb4.setMetadata("whpt", new FixedMetadataValue(RMain.getInstance(),p.getName()));
				        				fallingb4.setVisualFire(true);
				        				fallingb4.setDropItem(true);
				        				fallingb4.setHurtEntities(true);
				                    }
				                }
		            		}, 40); 
						});
				bd.execute();
				
				
			}	
			
    }

	public void ULT1(EntityDropItemEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(fallingb.hasMetadata("whpt")){
	        	ev.setCancelled(true);
				Player p = Bukkit.getPlayer(fallingb.getMetadata("whpt").get(0).asString());
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.SPLASH, tl, 150,5,5,5);
				tl.getWorld().spawnParticle(Particle.CLOUD, tl, 20,2,2,2);

				isTridentLaunched(p);
	
				for (Entity e : p.getWorld().getNearbyEntities(tl, 6, 6, 6))
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
					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
						LivingEntity le = (LivingEntity)e;
	                	atk1(0.5, p, le);
					}
					
				}
				p.playSound(tl, Sound.ENTITY_HOSTILE_SPLASH, 0.5f, 0);
				p.playSound(tl, Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 0.3f, 0);
				p.playSound(tl, Sound.ITEM_TRIDENT_RETURN, 0.4f, 0);
				fallingb.remove();
	        }
		 }
	}



	public void ULT1(EntityDamageByEntityEvent ev) 
	{
		if(ev.getDamager() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getDamager();
	        if(fallingb.hasMetadata("whpt")){
	        	ev.setCancelled(true);
				Player p = Bukkit.getPlayer(fallingb.getMetadata("whpt").get(0).asString());
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.SPLASH, tl, 150,5,5,5);
				tl.getWorld().spawnParticle(Particle.CLOUD, tl, 20,2,2,2);

				isTridentLaunched(p);
	
				for (Entity e : p.getWorld().getNearbyEntities(tl, 6, 6, 6))
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
					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
						LivingEntity le = (LivingEntity)e;
	                	atk1(0.5, p, le);
					}
					
				}
				p.playSound(tl, Sound.ENTITY_HOSTILE_SPLASH, 0.5f, 0);
				p.playSound(tl, Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 0.3f, 0);
				p.playSound(tl, Sound.ITEM_TRIDENT_RETURN, 0.4f, 0);
				fallingb.remove();
	        }
		 }
	}



	public void ULT1(EntityChangeBlockEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(fallingb.hasMetadata("whpt")){
	        	ev.setCancelled(true);
				Player p = Bukkit.getPlayer(fallingb.getMetadata("whpt").get(0).asString());
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.SPLASH, tl, 150,5,5,5);
				tl.getWorld().spawnParticle(Particle.CLOUD, tl, 20,2,2,2);

				isTridentLaunched(p);
	
				for (Entity e : p.getWorld().getNearbyEntities(tl, 6, 6, 6))
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
					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
						LivingEntity le = (LivingEntity)e;
	                	atk1(0.5, p, le);
					}
					
				}
				p.playSound(tl, Sound.ENTITY_HOSTILE_SPLASH, 0.5f, 0);
				p.playSound(tl, Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 0.3f, 0);
				p.playSound(tl, Sound.ITEM_TRIDENT_RETURN, 0.4f, 0);
				fallingb.remove();
	        }
		 }
	}



	public void ULT2(PlayerItemHeldEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		ItemStack is = p.getInventory().getItemInMainHand();

        Location tl = gettargetblock(p,1).clone();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 19 && ((is.getType()==Material.TRIDENT))&&ev.getNewSlot()==4 && p.isSneaking() && Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
				p.setCooldown(CAREFUL, 2);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(75*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
						.kname("바다대행진")
						.ename("OceanMarch")
						.slot(7)
						.hm(ault2cooldown)
						.skillUse(() -> {

			            	Holding.invur(p, 40l);

		                    p.playSound(tl, Sound.ITEM_TRIDENT_THUNDER, 0.5f, 0);
		                	p.playSound(tl, Sound.BLOCK_WATER_AMBIENT, 0.5f, 0);
		                	p.playSound(tl, Sound.BLOCK_CONDUIT_AMBIENT, 0.5f, 1);
		                	
							tl.getWorld().spawnParticle(Particle.NAUTILUS, tl, 500, 5,5,5,0.2);
							tl.getWorld().spawnParticle(Particle.PORTAL, tl, 200, 2,2,2,0);
							tl.getWorld().spawnParticle(Particle.DRIPPING_WATER, tl, 600, 5,5,5,0.05);
							
							
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
			                	public void run() 
				                {	    

			                        ArrayList<LivingEntity> march = new ArrayList<>();
			                        
			                    	PufferFish cs = (PufferFish) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(-Math.PI/6).multiply(2.5)), EntityType.PUFFERFISH);
			    					cs.setInvulnerable(true);
			    					cs.setAI(false);
			    					cs.setGravity(false);
			    					cs.setCustomName(p.getName());
			    					cs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    					cs.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			    					cs.setPuffState(10);
			    					march.add(cs);
			                    	TropicalFish cs1 = (TropicalFish) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(Math.PI/6).multiply(2.5)), EntityType.TROPICAL_FISH);
			    					cs1.setInvulnerable(true);
			    					cs1.setAI(false);
			    					cs1.setGravity(false);
			    					cs1.setCustomName(p.getName());
			    					cs1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    					cs1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			    					march.add(cs1);
			    					Cod cs2 = (Cod) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(Math.PI/2).multiply(2.5)), EntityType.COD);
			    					cs2.setInvulnerable(true);
			    					cs2.setAI(false);
			    					cs2.setGravity(false);
			    					cs2.setCustomName(p.getName());
			    					cs2.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    					cs2.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			    					march.add(cs2);
			    					Salmon cs3 = (Salmon) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(-Math.PI/2).multiply(2.5)), EntityType.SALMON);
			    					cs3.setInvulnerable(true);
			    					cs3.setAI(false);
			    					cs3.setGravity(false);
			    					cs3.setCustomName(p.getName());
			    					cs3.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    					cs3.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			    					march.add(cs3);
			                    	Turtle cs4 = (Turtle) p.getWorld().spawnEntity(p.getLocation().add(p.getLocation().getDirection().multiply(1.5)), EntityType.TURTLE);
			    					cs4.setInvulnerable(true);
			    					cs4.setAI(false);
			    					cs4.setGravity(false);
			    					cs4.setCustomName(p.getName());
			    					cs4.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    					cs4.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			    					march.add(cs4);
			                    	Squid cs5 = (Squid) p.getWorld().spawnEntity(p.getEyeLocation().add(0, 2, 0), EntityType.SQUID);
			    					cs5.setInvulnerable(true);
			    					cs5.setGravity(false);
			    					cs5.setAI(false);
			    					cs5.setGravity(false);
			    					cs5.setCustomName(p.getName());
			    					cs5.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    					cs5.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			    					march.add(cs5);
			                    	Guardian cs6 = (Guardian) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(-Math.PI/2).multiply(1.5)), EntityType.GUARDIAN);
			    					cs6.setInvulnerable(true);
			    					cs6.setAI(false);
			    					cs6.setGravity(false);
			    					cs6.setCustomName(p.getName());
			    					cs6.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    					cs6.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			    					cs6.setGravity(false);
			    					cs6.setCollidable(false);
			    					march.add(cs6);
			                        Guardian cs7 = (Guardian) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(Math.PI/2).multiply(1.5)), EntityType.GUARDIAN);
			    					cs7.setInvulnerable(true);
			    					cs7.setAI(false);
			    					cs7.setGravity(false);
			    					cs7.setCustomName(p.getName());
			    					cs7.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    					cs7.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			    					cs7.setGravity(false);
			    					cs7.setCollidable(false);
			    					march.add(cs7);
			                    	ElderGuardian cs8 = (ElderGuardian) p.getWorld().spawnEntity(p.getEyeLocation().add(0,1,0), EntityType.ELDER_GUARDIAN);
			    					cs8.setInvulnerable(true);
			    					cs8.setAI(false);
			    					cs8.setGravity(false);
			    					cs8.setCustomName(p.getName());
			    					cs8.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    					cs8.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			    					cs8.setGravity(false);
			    					cs8.setCollidable(false);
			    					march.add(cs8);
			                        Dolphin cs9 = (Dolphin) p.getWorld().spawnEntity(tl.add(p.getEyeLocation().getDirection().normalize().multiply(2)).add(0, 2, 0), EntityType.DOLPHIN);
			    					cs9.setInvulnerable(true);
			    					cs9.setAI(false);
			    					cs9.setGravity(false);
			    					cs9.setCustomName(p.getName());
			    					cs9.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    					cs9.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			    					cs9.setCollidable(false);
			    					march.add(cs9);
			                        GlowSquid cs10 = (GlowSquid) p.getWorld().spawnEntity(p.getEyeLocation().add(0,-1,0), EntityType.GLOW_SQUID);
			                        cs10.setInvulnerable(true);
			                        cs10.setAI(false);
			                        cs10.setGravity(false);
			                        cs10.setCustomName(p.getName());
			    					cs10.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    					cs10.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			    					cs10.setCollidable(false);
			    					march.add(cs10);
			                    	Turtle cs11 = (Turtle) p.getWorld().spawnEntity(p.getLocation().add(p.getLocation().getDirection().multiply(1.5)), EntityType.TURTLE);
			    					cs11.setInvulnerable(true);
			    					cs11.setGravity(false);
			    					cs11.setAI(false);
			    					cs11.setCustomName(p.getName());
			    					cs11.setBaby();
			    					cs11.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    					cs11.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			    					march.add(cs11);
			    					Axolotl cs12 = (Axolotl) p.getWorld().spawnEntity(tl, EntityType.AXOLOTL);
			    					cs12.setInvulnerable(true);
			    					cs12.setGravity(false);
			    					cs12.setAI(false);
			    					cs12.setCustomName(p.getName());
			    					cs12.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    					cs12.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			    					cs12.setAdult();
			    					cs12.setBreed(false);
			    					cs12.setVariant(Variant.WILD);
			    					march.add(cs12);
			    					Axolotl cs13 = (Axolotl) tl.getWorld().spawnEntity(tl.clone().add(1, 0, 1), EntityType.AXOLOTL);
			    					cs13.setInvulnerable(true);
			    					cs13.setGravity(false);
			    					cs13.setAI(false);
			    					cs13.setCustomName(p.getName());
			    					cs13.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    					cs13.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			    					cs13.setAdult();
			    					cs13.setBreed(false);
			    					cs13.setVariant(Variant.BLUE);
			    					march.add(cs13);
			    					Axolotl cs14 = (Axolotl) tl.getWorld().spawnEntity(tl.clone().add(-1, 0, 1), EntityType.AXOLOTL);
			    					cs14.setInvulnerable(true);
			    					cs14.setGravity(false);
			    					cs14.setAI(false);
			    					cs14.setCustomName(p.getName());
			    					cs14.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    					cs14.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			    					cs14.setAdult();
			    					cs14.setBreed(false);
			    					cs14.setVariant(Variant.CYAN);
			    					march.add(cs14);
			    					Axolotl cs15 = (Axolotl) tl.getWorld().spawnEntity(tl.clone().add(1, 0, -1), EntityType.AXOLOTL);
			    					cs15.setInvulnerable(true);
			    					cs15.setGravity(false);
			    					cs15.setAI(false);
			    					cs15.setCustomName(p.getName());
			    					cs15.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    					cs15.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			    					cs15.setAdult();
			    					cs15.setBreed(false);
			    					cs15.setVariant(Variant.GOLD);
			    					march.add(cs15);
			    					Axolotl cs16 = (Axolotl) tl.getWorld().spawnEntity(tl.clone().add(-1, 0, -1), EntityType.AXOLOTL);
			    					cs16.setInvulnerable(true);
			    					cs16.setGravity(false);
			    					cs16.setAI(false);
			    					cs16.setCustomName(p.getName());
			    					cs16.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    					cs16.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			    					cs16.setAdult();
			    					cs16.setBreed(false);
			    					cs16.setVariant(Variant.LUCY);
			    					march.add(cs16);
			    					
			    					march.forEach(m -> {
			    						m.setCollidable(false);
			    						Location arrange = m.getLocation().clone();
			    						arrange.setDirection(p.getEyeLocation().getDirection());
			    						m.teleport(arrange);
				    					m.setGravity(false);
			    					});
			    					int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			    		                @Override
			    		                public void run() 
			    		                {
			    		        			isTridentLaunched(p);
			    		                	march.forEach(m->{
			    		    					m.setGravity(false);
			    								Location arrange = m.getLocation().clone();
			    								arrange.setDirection(p.getEyeLocation().getDirection());
			    								if(cs8.getLocation().clone().add(cs8.getLocation().getDirection().normalize().multiply(0.2)).getBlock().isPassable()) {
				    		                		m.teleport(arrange.clone().add(arrange.getDirection().normalize().multiply(0.2)));
			    								}
			    		                	});
			    		                	cs8.getWorld().playSound(cs8.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 0.3f, 0);
			    		                	cs8.getWorld().playSound(cs8.getLocation(), Sound.ENTITY_AXOLOTL_SPLASH, 0.3f, 0);
			    		                	cs8.getWorld().playSound(cs8.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 0.3f, 0);
			    		                	cs8.getWorld().spawnParticle(Particle.SPLASH, cs8.getLocation(), 150, 6,6,6);
			    		                	cs8.getWorld().spawnParticle(Particle.SWEEP_ATTACK, cs8.getLocation(), 50, 6,6,6);

			    	                		for(Entity e: cs8.getWorld().getNearbyEntities(cs8.getLocation(), 9,9,9)) {

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
			            	            		if ((!(e == p))&& e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) 
			            						{
			            								LivingEntity le = (LivingEntity)e;
			            			                	atk1(0.2, p, le);
			            								le.teleport(cs8);
			            			                	Holding.holding(p, le, 10l);
			            										
			            						}
			            					
			    		                	}
			    		                }
			    					},0,1);
			    					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			    	             		@Override
			    	                	public void run() 
			    		                {	    
			    	             			Bukkit.getScheduler().cancelTask(task);
			    	             			march.forEach(r -> r.remove());
			    			            }
			    					}, 200);
					            }
							}, 20);
						});
				bd.execute();
			}	
			
    }

	
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();

	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 19)
		{
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT)
			{
					e.setCancelled(true);
			}
		}
		
	}

	public void ArmorDecrease(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity() instanceof Player && d.getDamager() instanceof LivingEntity) 
		{
			Player p = (Player) d.getEntity();
			if(ClassData.pc.get(d.getEntity().getUniqueId()) == 19 && Proficiency.getpro(p)<1) {
				d.setDamage(d.getDamage()*1.5);
			}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof Player) 
		{
			Projectile ar = (Projectile)d.getDamager();
	
			if(ar.getShooter() instanceof LivingEntity) {
				Player p = (Player) d.getEntity();
				if(ClassData.pc.get(d.getEntity().getUniqueId()) == 19 && Proficiency.getpro(p)<1) {
					d.setDamage(d.getDamage()*1.5);
				}
			}
		}
	}

	
	public void Damagegetter(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
		if(ClassData.pc.get(p.getUniqueId()) == 19) {

			if((p.getInventory().getItemInMainHand().getType()==Material.TRIDENT ||isTridentLaunched(p))&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
			{
				dset2(d, p, 1.15*(1+fsd.MarkOfSea.get(p.getUniqueId())*0.0636), le, 7);
			}
		}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity) 
		{
			Projectile ar = (Projectile)d.getDamager();
	
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
				LivingEntity le = (LivingEntity)d.getEntity();
			    
				
				
				
				if(ClassData.pc.get(p.getUniqueId()) == 19) {

					if((p.getInventory().getItemInMainHand().getType()==Material.TRIDENT ||isTridentLaunched(p))&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
					{
						dset2(d, p, 1.15*(1+fsd.MarkOfSea.get(p.getUniqueId())*0.0636), le, 7);
					}
				}
			}
		}
	}
	
	public void MarkOfSea(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.getEntity().hasMetadata("fake")&& !d.getEntity().hasMetadata("portal")) 
		{
	        
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			
	
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 19) {
				if(le == p) {
					d.setCancelled(true);
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
					le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 120, 0, false,false));
					marked.put(p.getUniqueId(), le);
					if(markt.containsKey(le.getUniqueId())) {
						Bukkit.getServer().getScheduler().cancelTask(markt.get(le.getUniqueId()));
					}
					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							marked.remove(p.getUniqueId(), le);
		                }
		            }, 120);
					markt.put(le.getUniqueId(),task);
					if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT) {
						if(!p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOYALTY)) {
							p.getInventory().getItemInMainHand().addEnchantment(Enchantment.LOYALTY, 3);
							p.getInventory().getItemInMainHand().addEnchantment(Enchantment.CHANNELING, 1);
						}
						if(p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.RIPTIDE)) {
							p.getInventory().getItemInMainHand().removeEnchantment(Enchantment.RIPTIDE);
							p.getInventory().getItemInMainHand().addEnchantment(Enchantment.LOYALTY, 3);
							p.getInventory().getItemInMainHand().addEnchantment(Enchantment.CHANNELING, 1);
						}
					}
					if(le.hasMetadata("leader") || le.hasMetadata("boss")) {
						d.setDamage(d.getDamage()*2.7);
					}
				}
				
		}
		
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity && !d.getEntity().hasMetadata("fake")&& !d.getEntity().hasMetadata("portal")) 
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
			if(ClassData.pc.get(p.getUniqueId()) == 19) {
				if(le == p) {
					d.setCancelled(true);
					return;
				}
					le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 120, 0, false,false));
					marked.put(p.getUniqueId(), le);
					if(markt.containsKey(le.getUniqueId())) {
						Bukkit.getServer().getScheduler().cancelTask(markt.get(le.getUniqueId()));
					}
					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							marked.remove(p.getUniqueId(), le);
		                }
		            }, 120);
					markt.put(le.getUniqueId(),task);
					if(le.hasMetadata("leader") || le.hasMetadata("boss")) {
						d.setDamage(d.getDamage()*2.7);
					}
			}
		}
		}
	}


	
}