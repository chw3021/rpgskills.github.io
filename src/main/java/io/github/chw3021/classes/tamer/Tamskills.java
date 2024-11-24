package io.github.chw3021.classes.tamer;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.classes.witchdoctor.Wdcskills;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.commons.SkillUseEvent;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Breeze;
import org.bukkit.entity.Cat;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderDragon.Phase;
import org.bukkit.entity.EnderDragonPart;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fox;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Panda.Gene;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Tamskills extends Pak {
	
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sult2cooldown = new HashMap<String, Long>();


	private HashMap<UUID, Integer> stomp = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> stompt = new HashMap<UUID, Integer>();
	
	private HashMap<UUID, Integer> scrh = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> scrht = new HashMap<UUID, Integer>();
	

	private HashMultimap<UUID, Mob> disr = HashMultimap.create();
	private HashMap<UUID, Integer> disrt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> chcn = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> chcnt = new HashMap<UUID, Integer>();

	private HashMap<UUID, CaveSpider> websr = new HashMap<UUID, CaveSpider>();
	private HashMap<UUID, Integer> websrt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> btswp = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> btswpt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> dt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> ddt = new HashMap<UUID, Integer>();

	
	private HashMap<UUID, LivingEntity> attention = new HashMap<UUID, LivingEntity>();
	private Multimap<UUID, Mob> tamed = HashMultimap.create();

	private Multimap<UUID, Mob> irong = HashMultimap.create();
	
	private HashMap<UUID, Integer> spidt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> batt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> crt = new HashMap<UUID, Integer>();
	
	private HashMultimap<UUID, Mob> pets = HashMultimap.create();
	private HashMap<UUID, LivingEntity> pandam = new HashMap<UUID, LivingEntity>();
	static private HashMap<UUID, Integer> pandat = new HashMap<UUID, Integer>();
	private HashMap<UUID, UUID> pandao = new HashMap<UUID, UUID>();
	private HashMap<Creeper, Player> creeper = new HashMap<Creeper, Player>();
	private String path = new File("").getAbsolutePath();
	private TamSkillsData tsd;


	private static final Tamskills instance = new Tamskills ();
	public static Tamskills getInstance()
	{
		return instance;
	}


	
		
	public void er(PluginEnableEvent ev) 
	{
		TamSkillsData t = new TamSkillsData(TamSkillsData.loadData(path +"/plugins/RPGskills/TamSkillsData.data"));
		tsd = t;
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
				if(e.getWhoClicked() instanceof Player) {
					Player p = (Player) e.getWhoClicked();
					if(tamed.containsKey(p.getUniqueId())) {
						tamed.get(p.getUniqueId()).forEach(le -> {
							le.remove();
						});
						tamed.removeAll(p.getUniqueId());
					}
					if(pets.containsKey(p.getUniqueId())) {
						pets.get(p.getUniqueId()).forEach(le -> {
							le.remove();
						});
						pets.removeAll(p.getUniqueId());
					}
					if(pandam.containsKey(p.getUniqueId())) {
						pandam.remove(p.getUniqueId());
					}
			    	if(pandat.containsKey(p.getUniqueId())) {
			        	Bukkit.getScheduler().cancelTask(pandat.get(p.getUniqueId()));
			        	pandat.remove(p.getUniqueId());
			    	}
				}
			}
			
		}
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("TamSkills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				TamSkillsData t = new TamSkillsData(TamSkillsData.loadData(path +"/plugins/RPGskills/TamSkillsData.data"));
				tsd = t;
			}
			
		}
	}

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		TamSkillsData t = new TamSkillsData(TamSkillsData.loadData(path +"/plugins/RPGskills/TamSkillsData.data"));
		tsd = t;
		
	}
	
	
	public void PressTheAttack(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 9 && tsd.PressTheAttack.getOrDefault(p.getUniqueId(), 0)>=1)
		{
			double sec =5*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && !(p.isSneaking()) && tsd.PressTheAttack.get(p.getUniqueId())>=1)
			{
				ev.setCancelled(true);
				if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sdcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("집중공격 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
	                	}
	                	else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use PressTheAttack").create());
	                	}
	                }
	                else // if timer is done
	                {
	                    sdcooldown.remove(p.getName()); // removing player from HashMap
						AbstractArrow sn = (AbstractArrow) p.launchProjectile(AbstractArrow.class);
	                	HashMap<Player, Double> damage = new HashMap<Player, Double>();
	                	damage.put(p,(double) 0);
						if(tamed.containsKey(p.getUniqueId())) {
							tamed.get(p.getUniqueId()).forEach(t -> {
								if(t.getType() != EntityType.PARROT && t.getType() != EntityType.BAT&& t.getType() != EntityType.IRON_GOLEM&& t.getType() != EntityType.ENDER_DRAGON &&t.isValid() && !t.isDead()) {
									damage.compute(p, (k,v) -> v+t.getAttribute(Attribute.ATTACK_DAMAGE).getBaseValue()) ;
								}
							});
						}
	                    sn.setDamage(damage.get(p)*0.05);
	                    sn.setShooter(p);
	                    sn.setVelocity(sn.getVelocity().add(p.getLocation().getDirection().normalize().multiply(50)));
	                    sn.setMetadata("Target", new FixedMetadataValue(RMain.getInstance(), true));
	                    sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						sn.setPickupStatus(PickupStatus.DISALLOWED);
	                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
		                	public void run() 
			                {	
		                    	sn.remove();
				            }
	                	}, 20); 
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
					AbstractArrow sn = (AbstractArrow) p.launchProjectile(AbstractArrow.class);
                	HashMap<Player, Double> damage = new HashMap<Player, Double>();
                	damage.put(p,(double) 0);
					if(tamed.containsKey(p.getUniqueId())) {
						tamed.get(p.getUniqueId()).forEach(t -> {
							if(t.getType() != EntityType.PARROT && t.getType() != EntityType.BAT&& t.getType() != EntityType.IRON_GOLEM&& t.getType() != EntityType.ENDER_DRAGON &&t.isValid() && !t.isDead()) {
								damage.compute(p, (k,v) -> v+t.getAttribute(Attribute.ATTACK_DAMAGE).getBaseValue()) ;
							}
						});
					}
                    sn.setDamage(damage.get(p)/20);
                    sn.setShooter(p);
                    sn.setVelocity(sn.getVelocity().add(p.getLocation().getDirection().normalize().multiply(50)));
                    sn.setMetadata("Target", new FixedMetadataValue(RMain.getInstance(), true));
                    sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					sn.setPickupStatus(PickupStatus.DISALLOWED);
                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
	                	public void run() 
		                {	
	                    	sn.remove();
			            }
                	}, 20); 
	            }
			}
			
		}
	}

	final private void pta(Player p, LivingEntity le) {
		HashSet<Mob> remove = new HashSet<Mob>();
		tamed.get(p.getUniqueId()).forEach(tameds -> {
			tameds.teleport(le.getLocation());
			tameds.setTarget(le);
			tameds.teleport(le);
			tameds.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 120, 4, false, false));
			if(tameds instanceof Creeper) {
				Creeper ct = (Creeper) tameds;
				remove.add(ct);
			}
			else if(tameds instanceof CaveSpider) {
				CaveSpider cs = (CaveSpider) tameds;

 				if(spidt.containsKey(p.getUniqueId())) {
     				Bukkit.getScheduler().cancelTask(spidt.get(p.getUniqueId()));
     				spidt.remove(p.getUniqueId());
 				}
            	cs.setTarget(attention.get(p.getUniqueId()));
            	Spidey(p,cs,attention.get(p.getUniqueId()));
            	
			}
			else if(tameds instanceof Bat) {
				Bat cs = (Bat) tameds;

 				if(batt.containsKey(p.getUniqueId())) {
     				Bukkit.getScheduler().cancelTask(batt.get(p.getUniqueId()));
     				batt.remove(p.getUniqueId());
 				}
            	cs.setTarget(attention.get(p.getUniqueId()));
            	
			}
		});
		remove.forEach(r -> {
			if(r instanceof Creeper) {
				Creeper ct = (Creeper) r;
				ct.explode();
			}
			else {
					tamed.remove(p.getUniqueId(), r);
			}
			
		});
	}
	
	
	@SuppressWarnings("deprecation")
	public void PressTheAttack(ProjectileHitEvent ev) 
	{
		Projectile j = (Projectile)ev.getEntity();
		if(j.getShooter() instanceof Player && j instanceof Arrow) {
			
			if(ev.getHitEntity() !=null && ev.getHitBlock() == null) {

				Entity e = (Entity)ev.getHitEntity();
				if(e instanceof LivingEntity && !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
					Player p = (Player) ev.getEntity().getShooter();
					LivingEntity le = (LivingEntity)e;
			        
					
					if(ClassData.pc.get(p.getUniqueId()) == 9 && !sdcooldown.containsKey(p.getName())) {
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
						if(j.hasMetadata("Target")) {
							if(le.hasMetadata("untargetable")) {
								if(p.getName().equals(le.getMetadata("untargetable").get(0).asString())) {
									return;
								}
							}
							attention.put(p.getUniqueId(), le);
			                sdcooldown.put(p.getName(), System.currentTimeMillis());
		                    Bukkit.getPluginManager().callEvent(new SkillUseEvent(p,5*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d),5,"집중공격","PressTheAttack"));
			                if(Proficiency.getpro(p)>=1) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 2, 0, false, false));
			                }
			                if(Proficiency.getpro(p)>=2) {
								Holding.holding(p, le, 20l);
			                }
							if(tamed.containsKey(p.getUniqueId())) {
								if(ev.getHitEntity() instanceof Wither) {
									Wither w =(Wither) ev.getHitEntity();
									Arrow ar = (Arrow) ev.getEntity();
									{
				                		if (w.getHealth()<=w.getMaxHealth()/2) 
										{
		        		                    w.damage(bbArrow(ar), p);
										}
									}
								}
								if(ev.getHitEntity() instanceof Enderman || ev.getHitEntity() instanceof Breeze) {
									Entity en = ev.getHitEntity();
									Arrow ar = (Arrow) ev.getEntity();
									{
		    		                    ((Damageable) en).damage(bbArrow(ar), p);
									}
								}
								pta(p,le);
								
							}
						}
					}
				}
			}
		}
	}	
	final private int Spidey(Player p, CaveSpider cs, LivingEntity le) {

		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			cs.teleport(le.getLocation());
    			atk1(0.2*(1+tsd.Spidey.get(p.getUniqueId())*0.02)*(1+tsd.Taming.get(p.getUniqueId())*0.058), p, le,11);
     			Holding.holding(p, le, 5l);
				p.getWorld().playSound(cs.getLocation(), Sound.ENTITY_SPIDER_AMBIENT, 0.2f, 2f);
				p.getWorld().spawnParticle(Particle.ITEM, cs.getLocation(), 10,1,1,1,0.5, new ItemStack(Material.SPIDER_EYE));
     			if(le.isDead() || !cs.isValid()) {
     				if(spidt.containsKey(p.getUniqueId())) {
         				Bukkit.getScheduler().cancelTask(spidt.get(p.getUniqueId()));
         				spidt.remove(p.getUniqueId());
     				}
     			}
            }
		},0,14);
		spidt.put(p.getUniqueId(), task);
		return task;
	}

	
	
	public void Spidey(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
        

		
		if(ClassData.pc.get(p.getUniqueId()) == 9&& tsd.Spidey.getOrDefault(p.getUniqueId(), 0)>=1) {
		
		if((ac == Action.RIGHT_CLICK_AIR|| ac == Action.RIGHT_CLICK_BLOCK) && (p.isSneaking()))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
			{
				p.setCooldown(CAREFUL, 2);
				
				double sec =7*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("스파이디")
						.ename("Spidey")
						.slot(0)
						.hm(swcooldown)
						.skillUse(() -> {
							p.playSound(p.getLocation(), Sound.ENTITY_PHANTOM_BITE, 1, 0);
							CaveSpider cs = (CaveSpider) p.getWorld().spawnEntity(gettargetblock(p,5), EntityType.CAVE_SPIDER);
							cs.setInvulnerable(true);
							cs.setCustomName(p.getName());
							cs.setMetadata("cs of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							cs.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), p.getName()));
							cs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							cs.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							cs.setSilent(true);
							tamed.put(p.getUniqueId(), cs);
		                    if(attention.containsKey(p.getUniqueId())) {
		         				if(spidt.containsKey(p.getUniqueId())) {
		             				Bukkit.getScheduler().cancelTask(spidt.get(p.getUniqueId()));
		             				spidt.remove(p.getUniqueId());
		         				}
		                    	cs.setTarget(attention.get(p.getUniqueId()));
		                    	Spidey(p,cs,attention.get(p.getUniqueId()));
		                    }
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
			                	public void run() 
				                {	
			         				if(spidt.containsKey(p.getUniqueId())) {
			             				Bukkit.getScheduler().cancelTask(spidt.get(p.getUniqueId()));
			             				spidt.remove(p.getUniqueId());
			         				}
	             					if(tamed.containsEntry(p.getUniqueId(), cs)) {
	             					tamed.remove(p.getUniqueId(), cs);       
	             					}
			                    	cs.remove();      						
					            }
							}, 60); 
			                
		                	if(websrt.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(websrt.get(p.getUniqueId()));
		                		websrt.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							websr.putIfAbsent(p.getUniqueId(), cs);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						websr.remove(p.getUniqueId());
		    	                }
		    	            }, 40); 
		                	websrt.put(p.getUniqueId(), task);
						});
						bd.execute();
						
			}
		}
		}
	}

	
	public void WebSpread(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
			if(ClassData.pc.get(p.getUniqueId()) == 9 && websr.containsKey(p.getUniqueId())) {
			if((ac == Action.RIGHT_CLICK_AIR|| ac == Action.RIGHT_CLICK_BLOCK) && (p.isSneaking()) && !p.isSprinting())
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
				{
					ev.setCancelled(true);
					
	            	CaveSpider cs = (CaveSpider) websr.get(p.getUniqueId());

					p.getWorld().playSound(cs.getLocation(), Sound.BLOCK_WEEPING_VINES_BREAK,1f, 2f);
					p.getWorld().playSound(cs.getLocation(), Sound.ENTITY_SPIDER_HURT,1f, 2f);
					p.getWorld().spawnParticle(Particle.ITEM, cs.getLocation(), 100,3,3,3,0.5, new ItemStack(Material.COBWEB));
					p.getWorld().spawnParticle(Particle.BLOCK, cs.getLocation(), 100,3,3,3,0.5, Material.COBWEB.createBlockData());
					
					
					for(Entity e : cs.getNearbyEntities(3, 3, 3)) {
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
						if(e !=p &&e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
                    		if(!tamed.containsValue(e)) {
    							LivingEntity le = (LivingEntity)e;
    							p.getWorld().playSound(cs.getLocation(), Sound.ENTITY_SPIDER_AMBIENT, 0.2f, 2f);
    							p.getWorld().spawnParticle(Particle.ITEM, cs.getLocation(), 10,1,1,1,0.5, new ItemStack(Material.SPIDER_EYE));
    							atk1(0.6*(1+tsd.Spidey.get(p.getUniqueId())*0.04)*(1+tsd.Taming.get(p.getUniqueId())*0.058), p, le,11);
    							Holding.holding(p, le, 40l);
                    		}
						}
					}
					
	            	if(websrt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(websrt.get(p.getUniqueId()));
	            		websrt.remove(p.getUniqueId());
	            	}
					websr.remove(p.getUniqueId());
					


	            	if(btswpt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(btswpt.get(p.getUniqueId()));
	            		btswpt.remove(p.getUniqueId());
	            	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=2) {
								btswp.putIfAbsent(p.getUniqueId(), 0);
							}
		                }
		            }, 3); 

	        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							btswp.remove(p.getUniqueId());
		                }
		            }, 25); 
	            	btswpt.put(p.getUniqueId(), task);
				}
			}
		}
	}

	/*public int BatsSwoop(Player p, Bat b, LivingEntity le) {

		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			Random ran = new Random();
     			b.teleport(le.getLocation().clone().add(ran.nextInt(2), ran.nextInt(2), ran.nextInt(2)));
				p.getWorld().playSound(b.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 0.5f, 0.5f);
				p.getWorld().spawnParticle(Particle.ITEM_CRACK, b.getLocation(), 100,3,3,3,0.5, new ItemStack(Material.BAT_SPAWN_EGG));
				for(Entity e : b.getNearbyEntities(3, 3, 3)) {
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
					if(e != p &&e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
	            		if(!tamed.containsValue(e)) {
							LivingEntity le = (LivingEntity)e;
							atk1(0.53*(1+tsd.Spidey.get(p.getUniqueId())*0.03)*(1+tsd.Taming.get(p.getUniqueId())*0.058), p, le);
							Holding.holding(p, le, 40l);
	            		}
					}
				}
     			if(le.isDead() || !b.isValid()) {
     				if(batt.containsKey(p.getUniqueId())) {
         				Bukkit.getScheduler().cancelTask(batt.get(p.getUniqueId()));
         				batt.remove(p.getUniqueId());
     				}
     			}
            }
		},5,14);
		batt.put(p.getUniqueId(), task);
		return task;
	}

	
	public void BatsSwoop(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
			if((ac == Action.RIGHT_CLICK_AIR|| ac == Action.RIGHT_CLICK_BLOCK) && (p.isSneaking()) && !p.isSprinting())
			{
				if(ClassData.pc.get(p.getUniqueId()) == 9 && btswp.containsKey(p.getUniqueId())) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
				{
					ev.setCancelled(true);
					
	            	if(btswpt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(btswpt.get(p.getUniqueId()));
	            		btswpt.remove(p.getUniqueId());
	            	}
					btswp.remove(p.getUniqueId());

					p.getWorld().playSound(p.getLocation(), Sound.BLOCK_WEEPING_VINES_BREAK,1f, 2f);
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SPIDER_HURT,1f, 2f);
					

                    for(int b =0; b<3; b++) {
						Bat bees = (Bat) p.getWorld().spawnEntity(p.getEyeLocation().clone().add(0, 1, 0), EntityType.BAT);
						bees.setInvulnerable(true);
						bees.setHealth(bees.getMaxHealth());
						bees.setMetadata("bees of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
						bees.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), p.getName()));	
						bees.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						bees.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
						bees.setCollidable(false);
						bees.setSilent(true);
						bees.setCustomName(p.getName());                  	
	                    tamed.put(p.getUniqueId(), bees);
	                    if(attention.containsKey(p.getUniqueId())) {
	         				if(batt.containsKey(p.getUniqueId())) {
	             				Bukkit.getScheduler().cancelTask(batt.get(p.getUniqueId()));
	             				batt.remove(p.getUniqueId());
	         				}
	                    	BatsSwoop(p,bees,attention.get(p.getUniqueId()));
	                    }
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	             		@Override
		                	public void run() 
			                {	
             					bees.remove();
             					tamed.remove(p.getUniqueId(), bees);
                 				if(batt.containsKey(p.getUniqueId())) {
                     				Bukkit.getScheduler().cancelTask(batt.get(p.getUniqueId()));
                     				batt.remove(p.getUniqueId());
                 				}
				            }
                	   }, 60); 
                    }
				}
			}
		}
	}*/

	final private void Leap(Player p, Mob b) {

		if(attention.containsKey(p.getUniqueId())) {
			LivingEntity le = (LivingEntity)attention.get(p.getUniqueId());
			b.teleport(le);
			atk1(0.53*(1+tsd.Pets.get(p.getUniqueId())*0.03)*(1+tsd.Taming.get(p.getUniqueId())*0.058), p, le);
			Holding.holding(p, b, 5l);
			p.getWorld().playSound(b.getLocation(), Sound.ENTITY_RABBIT_JUMP, 1f, 1.5f);
			p.getWorld().spawnParticle(Particle.CRIT, b.getLocation(), 100,2,2,2,0.5);
		}
		else {
			b.teleport(b.getLocation().clone().add(p.getEyeLocation().getDirection().clone().normalize().multiply(3.5)).add(0, 2, 0));
			Holding.holding(p, b, 5l);
			p.getWorld().playSound(b.getLocation(), Sound.ENTITY_RABBIT_JUMP, 1f, 1.5f);
			p.getWorld().spawnParticle(Particle.CRIT, b.getLocation(), 100,2,2,2,0.5);
			for(Entity e : b.getNearbyEntities(3, 4, 3)) {
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
				if(e != p &&e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
					LivingEntity le = (LivingEntity)e;
					b.teleport(le);
					atk1(0.3*(1+tsd.Pets.get(p.getUniqueId())*0.03)*(1+tsd.Taming.get(p.getUniqueId())*0.058), p, le,14);
					break;
				}
			}
		}
		cdcooldown.put(p.getName(), System.currentTimeMillis()); 
	}
	
	
	@SuppressWarnings("deprecation")
	public void Pets(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 9&& tsd.Pets.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && p.isSneaking())
			{
				ev.setCancelled(true);
				double sec = 5*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("반려동물")
						.ename("Pets")
						.slot(1)
						.hm(cdcooldown)
						.skillUse(() -> {
		                    if(pets.containsKey(p.getUniqueId())) {
		                    	if(Proficiency.getpro(p)>=1) {

		                        	if(scrht.containsKey(p.getUniqueId())) {
		                        		Bukkit.getScheduler().cancelTask(scrht.get(p.getUniqueId()));
		                        		scrht.remove(p.getUniqueId());
		                        	}

		            				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		            	                @Override
		            	                public void run() {
		            						if(Proficiency.getpro(p)>=2) {
		            							scrh.putIfAbsent(p.getUniqueId(), 0);
		            						}
		            	                }
		            	            }, 3); 

		                    		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		            	                @Override
		            	                public void run() {
		            						scrh.remove(p.getUniqueId());
		            	                }
		            	            }, 25); 
		                        	scrht.put(p.getUniqueId(), task);
		                        	
			                    	pets.get(p.getUniqueId()).forEach(m -> {
			                    		Leap(p,m);
			                    		return;
			                    	});
		                    	}
		                    	pets.get(p.getUniqueId()).forEach(m -> {
		                    		m.remove();
		                    		tamed.remove(p.getUniqueId(), m);
		                    	});
		                    }
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
		                    Wolf w = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
		                    w.setTamed(true);
		                    w.setOwner(p);
		                    w.setAdult();
		                    w.setBreed(false);
		                    w.setAgeLock(true);
							w.getAttribute(Attribute.KNOCKBACK_RESISTANCE).setBaseValue(1);
							w.getAttribute(Attribute.MAX_HEALTH).setBaseValue(w.getAttribute(Attribute.MAX_HEALTH).getBaseValue()*(1+tsd.Pets.getOrDefault(p.getUniqueId(),0)*0.05)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.1));
							w.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(w.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue()*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.015));
							w.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(5*(1+tsd.Pets.getOrDefault(p.getUniqueId(),0)*0.02)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.015));
							w.setHealth(w.getMaxHealth());
							w.setMetadata("pet of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
							w.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), p.getName()));
		                    w.setCustomName(p.getName());
		                    w.setSilent(true);
		                    w.setRemoveWhenFarAway(false);
		                    w.getEquipment().setChestplate(new ItemStack(Material.WOLF_ARMOR));
		                    Cat cat = (Cat) p.getWorld().spawnEntity(p.getLocation(), EntityType.CAT);
		                    cat.setTamed(true);
		                    cat.setOwner(p);
		                    cat.setAdult();
		                    cat.setBreed(false);
		                    cat.setAgeLock(true);
							cat.getAttribute(Attribute.MAX_HEALTH).setBaseValue(cat.getAttribute(Attribute.MAX_HEALTH).getBaseValue()*(1+tsd.Pets.getOrDefault(p.getUniqueId(),0)*0.05)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.1));
							cat.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(cat.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue()*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.01));
							cat.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(3*(1+tsd.Pets.getOrDefault(p.getUniqueId(),0)*0.02)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.01));
							cat.setHealth(cat.getMaxHealth());
							cat.setMetadata("pet of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
							cat.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), p.getName()));
		                    cat.setCustomName(p.getName());
		                    cat.setSilent(true);
		                    cat.setRemoveWhenFarAway(false);
							Parrot par = (Parrot) p.getWorld().spawnEntity(p.getLocation(), EntityType.PARROT);
		                    par.setTamed(true);
		                    par.setOwner(p);
		                    par.setAdult();
		                    par.setBreed(false);
		                    par.setAgeLock(true);
							par.getAttribute(Attribute.MAX_HEALTH).setBaseValue(par.getAttribute(Attribute.MAX_HEALTH).getBaseValue()*(1+tsd.Pets.getOrDefault(p.getUniqueId(),0)*0.05)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.1));
							par.getAttribute(Attribute.FLYING_SPEED).setBaseValue(3*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.01));
							par.setHealth(par.getMaxHealth());
							par.setMetadata("pet of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
							par.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), p.getName()));
							par.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), p.getName()));
							par.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), p.getName()));
							par.setMetadata("rob", new FixedMetadataValue(RMain.getInstance(), p.getName()));
		                    par.setCustomName(p.getName());
		                    par.setSilent(true);
		                    par.setRemoveWhenFarAway(false);
		                    pets.put(p.getUniqueId(), w);
		                    pets.put(p.getUniqueId(), cat);
		                    pets.put(p.getUniqueId(), par);
		                    tamed.put(p.getUniqueId(), w);
		                    tamed.put(p.getUniqueId(), cat);
		                    tamed.put(p.getUniqueId(), par);
	                    	if(Proficiency.getpro(p)>=1) {
	                    		
	                    		
			                    Fox fox = (Fox) p.getWorld().spawnEntity(p.getLocation(), EntityType.FOX);
			                    fox.setFirstTrustedPlayer(p);
			                    fox.setSecondTrustedPlayer(p);
			                    fox.setAdult();
			                    fox.setBreed(false);
			                    fox.setAgeLock(true);
			                    fox.getAttribute(Attribute.MAX_HEALTH).setBaseValue(fox.getAttribute(Attribute.MAX_HEALTH).getBaseValue()*(1+tsd.Pets.getOrDefault(p.getUniqueId(),0)*0.05)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.1));
			                    fox.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(fox.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue()*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.01));
			                    fox.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(4*(1+tsd.Pets.getOrDefault(p.getUniqueId(),0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.02));
			                    fox.setHealth(fox.getMaxHealth());
								fox.setMetadata("pet of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
								fox.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), p.getName()));
								fox.setCustomName(p.getName());
								fox.setSilent(true);
								fox.setRemoveWhenFarAway(false);
								Ocelot oce = (Ocelot) p.getWorld().spawnEntity(p.getLocation(), EntityType.OCELOT);
			                    oce.setTrusting(true);
			                    oce.setAdult();
			                    oce.setBreed(false);
			                    oce.setAgeLock(true);
			                    oce.getAttribute(Attribute.MAX_HEALTH).setBaseValue(oce.getAttribute(Attribute.MAX_HEALTH).getBaseValue()*(1+tsd.Pets.getOrDefault(p.getUniqueId(),0)*0.05)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.1));
			                    oce.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(oce.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue()*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.01));
			                    oce.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(4*(1+tsd.Pets.getOrDefault(p.getUniqueId(),0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.02));
			                    oce.setHealth(oce.getMaxHealth());
								oce.setMetadata("pet of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
								oce.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), p.getName()));
			                    oce.setCustomName(p.getName());
			                    oce.setSilent(true);
			                    oce.setRemoveWhenFarAway(false);
			                    pets.put(p.getUniqueId(), oce);
			                    pets.put(p.getUniqueId(), fox);
			                    tamed.put(p.getUniqueId(), fox);
			                    tamed.put(p.getUniqueId(), oce);
	                    	}
						});
				bd.execute();
			}
		}
	
	}

	
	public void Scratch(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 9&& scrh.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && p.isSneaking())
			{
				ev.setCancelled(true);

            	if(scrht.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(scrht.get(p.getUniqueId()));
            		scrht.remove(p.getUniqueId());
            	}
				scrh.remove(p.getUniqueId());


				if(attention.containsKey(p.getUniqueId())) {
					LivingEntity at = (LivingEntity)attention.get(p.getUniqueId());
					pets.get(p.getUniqueId()).forEach(b ->{
						b.teleport(at);
						Holding.holding(p, b, 5l);
						for(Entity e : b.getNearbyEntities(3, 3, 3)) {
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
							if(e != p &&e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
								LivingEntity le = (LivingEntity)e;
								atk1(0.5*(1+tsd.Pets.getOrDefault(p.getUniqueId(),0)*0.035)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.058), p, le,14);
							}
						}
						p.getWorld().playSound(b.getLocation(), Sound.ENTITY_WOLF_SHAKE, 1f, 1.5f);
						p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, b.getLocation(), 10,2,2,2,0.5);
					});
				}
				else {
					pets.get(p.getUniqueId()).forEach(b ->{
						for(Entity e : b.getNearbyEntities(3, 3, 3)) {
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
							if(e != p &&e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
								LivingEntity le = (LivingEntity)e;
								atk1(0.5*(1+tsd.Pets.getOrDefault(p.getUniqueId(),0)*0.035)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.058), p, le,14);
							}
						}
						p.getWorld().playSound(b.getLocation(), Sound.ENTITY_WOLF_SHAKE, 1f, 1.5f);
						p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, b.getLocation(), 10,2,2,2,0.5);
					});
				}
			}
		}
	
	}
	
	
	@SuppressWarnings("deprecation")
	public void BeeHive(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();

		
		if(ClassData.pc.get(p.getUniqueId()) == 9&& tsd.BeeHive.getOrDefault(p.getUniqueId(), 0)>=1) {
		if(!(p.isSneaking())&& !p.isOnGround() && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
		double sec = 10*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
			{
				p.setCooldown(CAREFUL, 2);
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("벌집")
						.ename("BeeHive")
						.slot(2)
						.hm(frcooldown)
						.skillUse(() -> {

		                	HashSet<Mob> bh = new HashSet<>();
		                    p.playSound(p.getLocation(), Sound.BLOCK_HONEY_BLOCK_SLIDE, 1.0f, 0f);
		                    Location i = gettargetblock(p,4);
							p.getWorld().spawnParticle(Particle.BLOCK, i, 3, 0.2,0.2,0.2,0 ,Material.BEEHIVE.createBlockData());
							p.getWorld().spawnParticle(Particle.BLOCK, i, 3, 0.2,0.2,0.2,0 ,Material.BEE_NEST.createBlockData());
							p.getWorld().spawnParticle(Particle.BLOCK, i, 3, 0.2,0.2,0.2,0 ,Material.HONEYCOMB_BLOCK.createBlockData());
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2.0f);
		                    for(int b =0; b<6; b++) {
								Bee bees = (Bee) p.getWorld().spawnEntity(i, EntityType.BEE);
								bees.setAdult();
								bees.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(3*(1+tsd.BeeHive.getOrDefault(p.getUniqueId(),0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.023));
								bees.getAttribute(Attribute.FLYING_SPEED).setBaseValue(bees.getAttribute(Attribute.FLYING_SPEED).getBaseValue()*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.01));
								bees.setInvulnerable(true);
								bees.setHealth(bees.getMaxHealth());
								bees.setMetadata("bees of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
								bees.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), p.getName()));	
								bees.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
								bees.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
								bees.setAnger(300);
								bees.setSilent(true);
								bees.setCustomName(p.getName());       
								bees.setMemory(MemoryKey.LIKED_PLAYER, p.getUniqueId());  
								bh.add(bees);
			                    tamed.put(p.getUniqueId(), bees);
			                    if(attention.containsKey(p.getUniqueId())) {
			                    	bees.setTarget(attention.get(p.getUniqueId()));
			                    }
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	             		@Override
				                	public void run() 
					                {	
		             					bees.remove();
		             					tamed.remove(p.getUniqueId(), bees);
						            }
		                	   }, 220); 
		                    }
		                    
		                	if(disrt.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(disrt.get(p.getUniqueId()));
		                		disrt.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							disr.putAll(p.getUniqueId(), bh);
		    						}
		    	                }
		    	            }, 5); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						disr.removeAll(p.getUniqueId());
		    	                }
		    	            }, 440); 
		                	disrt.put(p.getUniqueId(), task);
						});
				bd.execute();
			}		
		}
		}
	}

	
	@SuppressWarnings("deprecation")
	public void Disruption(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 9 && disr.containsKey(p.getUniqueId())) {
			if(!(p.isSneaking())&& !p.isOnGround() && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
				{
					ev.setCancelled(true);

                	Set<Mob> bh = disr.get(p.getUniqueId());


    				if(attention.containsKey(p.getUniqueId())) {
    					LivingEntity at = (LivingEntity)attention.get(p.getUniqueId());
    					bh.forEach(b ->{
    						b.teleport(at);
    						Holding.holding(p, b, 5l);
    						for(int i = 0 ; i < 5; i++) {

    			        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    				                @Override
    				                public void run() {
    		    						for(Entity e : b.getNearbyEntities(3, 3, 3)) {
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
    		    							if(e != p &&e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
    		    								LivingEntity le = (LivingEntity)e;
    		    								atk1(0.2*(1+tsd.BeeHive.getOrDefault(p.getUniqueId(),0)*0.01)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.058), p, le,11);
    		    							}
    		    						}
    				                }
    				            }, i*5); 
    						}
    						p.getWorld().playSound(b.getLocation(), Sound.BLOCK_BEEHIVE_WORK, 1f, 1.5f);
    						p.getWorld().playSound(b.getLocation(), Sound.ENTITY_BEE_LOOP_AGGRESSIVE, 1f, 1.5f);
    						p.getWorld().playSound(b.getLocation(), Sound.ENTITY_BEE_STING, 1f, 1.5f);
    						p.getWorld().spawnParticle(Particle.DRIPPING_HONEY, b.getLocation(), 100,3,3,3,0.5);
    						p.getWorld().spawnParticle(Particle.FALLING_HONEY, b.getLocation(), 100,3,3,3,0.5);
    						p.getWorld().spawnParticle(Particle.FALLING_NECTAR, b.getLocation(), 100,3,3,3,0.5);
    					});
    				}
    				else {
    					bh.forEach(b ->{
    						for(int i = 0 ; i < 5; i++) {

    			        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    				                @Override
    				                public void run() {
    		    						for(Entity e : b.getNearbyEntities(3, 3, 3)) {
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
    		    							if(e != p &&e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
    		    								LivingEntity le = (LivingEntity)e;
    		    								atk1(0.2*(1+tsd.BeeHive.getOrDefault(p.getUniqueId(),0)*0.01)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.058), p, le,11);
    		    							}
    		    						}
    				                }
    				            }, i*5); 
    						}
    						p.getWorld().playSound(b.getLocation(), Sound.BLOCK_BEEHIVE_WORK, 1f, 1.5f);
    						p.getWorld().playSound(b.getLocation(), Sound.ENTITY_BEE_LOOP_AGGRESSIVE, 1f, 1.5f);
    						p.getWorld().playSound(b.getLocation(), Sound.ENTITY_BEE_STING, 1f, 1.5f);
    						p.getWorld().spawnParticle(Particle.DRIPPING_HONEY, b.getLocation(), 100,3,3,3,0.5);
    						p.getWorld().spawnParticle(Particle.FALLING_HONEY, b.getLocation(), 100,3,3,3,0.5);
    						p.getWorld().spawnParticle(Particle.FALLING_NECTAR, b.getLocation(), 100,3,3,3,0.5);
    					});
    				}
					
					
					
	            	if(disrt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(disrt.get(p.getUniqueId()));
	            		disrt.remove(p.getUniqueId());
	            	}
					disr.removeAll(p.getUniqueId());
					


	            	if(chcnt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(chcnt.get(p.getUniqueId()));
	            		chcnt.remove(p.getUniqueId());
	            	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=2) {
								chcn.putIfAbsent(p.getUniqueId(), 0);
							}
		                }
		            }, 6); 

	        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							chcn.remove(p.getUniqueId());
		                }
		            }, 50); 
	            	chcnt.put(p.getUniqueId(), task);
	            	
				}
			}
		}
	}

	
	@SuppressWarnings("deprecation")
	public void Carry(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 9 && chcn.containsKey(p.getUniqueId())) {
			if(!(p.isSneaking())&& !p.isOnGround() && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
				{
					ev.setCancelled(true);
					
	            	if(chcnt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(chcnt.get(p.getUniqueId()));
	            		chcnt.remove(p.getUniqueId());
	            	}
	            	chcn.remove(p.getUniqueId());

					Chicken bees = (Chicken) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.CHICKEN);
					bees.setAdult();
					bees.setInvulnerable(true);
					bees.setHealth(bees.getMaxHealth());
					bees.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), p.getName()));	
					bees.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					bees.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					bees.setSilent(true);
					bees.setCustomName(p.getName());         

					for(int i = 0 ; i < 30; i++) {

		        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								p.addPassenger(bees);
			                }
			            }, i*2); 
					}
					p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 60, 3, false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 120, 3, false, false));
	        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							bees.remove();
		                }
		            }, 61); 
	            	
				}
			}
		}
	}
	
	
	public void Creepey(ExplosionPrimeEvent d) 
	{
		Entity cc = d.getEntity();
		
		if(cc instanceof Creeper) {
			Creeper c = (Creeper)cc;
			if(c.hasMetadata("untargetable")) {
				d.setRadius(0);
				Player p = Bukkit.getPlayer(c.getCustomName());
				if(crt.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(crt.get(p.getUniqueId()));
				}
				c.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, c.getLocation(), 2);
				for(Entity e : c.getNearbyEntities(5, 5, 5)) {
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
					if(e !=p &&e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
						LivingEntity le = (LivingEntity)e;
						atk1(0.85*(1+tsd.CreepBomb.getOrDefault(creeper.get(c).getUniqueId(),0)*0.08)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.058), p, le);
					}
				}
                if(Proficiency.getpro(p)>=2) {

					for(int i = 0 ; i < 10; i++) {
		        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {

			    				c.getWorld().spawnParticle(Particle.DUST, c.getLocation(), 100,5,2,5,0, new Particle.DustOptions(Color.LIME, 3f));
			    				for(Entity e : c.getNearbyEntities(5, 5, 5)) {
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
			    					if(e !=p &&e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
			                    		if(!tamed.containsValue(e)) {
				    						LivingEntity le = (LivingEntity)e;
				    						atk1(0.08*(1+tsd.CreepBomb.getOrDefault(creeper.get(c).getUniqueId(),0)*0.07)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.058), p, le);
				    						le.teleport(c);
				    						Holding.holding(p, le, 10l);
			                    		}
			    					}
			    				}
			                }
			            }, i*3); 
					}
                }
				tamed.remove(p.getUniqueId(), c);
			}
		}
	}

	
	public void CreepBomb(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action a = ev.getAction();
	    
		if(ClassData.pc.get(p.getUniqueId()) == 9&& tsd.CreepBomb.getOrDefault(p.getUniqueId(), 0)>=1 && !p.hasCooldown(CAREFUL)) {	
			if((a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK)&&(a!= Action.RIGHT_CLICK_AIR)&&(a!= Action.RIGHT_CLICK_AIR)&& p.isSneaking())
			{	
				double sec =10*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
					{
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("크리퍼폭탄")
							.ename("CreepBomb")
							.slot(3)
							.hm(gdcooldown)
							.skillUse(() -> {
								p.playSound(p.getLocation(), Sound.ENTITY_CREEPER_PRIMED , 1.0f, 2.0f);
			                    Creeper cp = (Creeper) p.getWorld().spawnEntity(p.getEyeLocation().clone().add(p.getEyeLocation().getDirection().clone().normalize().multiply(3)), EntityType.CREEPER);
			                    cp.setPowered(true);
			                    cp.setMaxFuseTicks(3);
			                    cp.setMemory(MemoryKey.LIKED_PLAYER, p.getUniqueId());
			                    cp.setCollidable(false);
			                    creeper.put(cp, p);
			                    cp.setCustomName(p.getName());
								cp.setMetadata("cp of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
								cp.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), p.getName()));
								tamed.put(p.getUniqueId(), cp);
			                    if(attention.containsKey(p.getUniqueId())) {
			                    	cp.setTarget(attention.get(p.getUniqueId()));
			                    }
			                    if(Proficiency.getpro(p)>=1) {

			                		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                     		@Override
			                        	public void run() 
			                            {
			                				for(Entity e : cp.getNearbyEntities(6, 6, 6)) {
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
			                					if(e !=p &&e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
			                						LivingEntity le = (LivingEntity)e;
						                    		if(!tamed.containsValue(e)) {
				                						le.teleport(cp);
						                    		}
			                					}
			                				}
			                            }
			                		},10,10);
			                		crt.put(p.getUniqueId(), task);
			                    }
							});
					bd.execute();
					}
			}
		}
	}


	final private void PandaSweep(Player p, Mob b) {

		if(attention.containsKey(p.getUniqueId())) {
			LivingEntity t = (LivingEntity)attention.get(p.getUniqueId());
			b.teleport(t);
			Holding.holding(p, b, 5l);
			b.swingMainHand();
			p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PANDA_BITE, 1f, 1.5f);
			p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, 1.5f);
			p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, b.getLocation(), 50,5,5,5,0.5);
			for(Entity e : b.getNearbyEntities(5, 5, 5)) {
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
				if(e != p &&e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
            		if(!tamed.containsValue(e)) {
    					LivingEntity le = (LivingEntity)e;
    					atk1(0.6*(1+tsd.PandaSweep.get(p.getUniqueId())*0.03)*(1+tsd.Taming.get(p.getUniqueId())*0.058), p, le,14);
    					le.teleport(b);
            		}
				}
			}
		}
		else {
			b.swingMainHand();
			p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PANDA_BITE, 1f, 1.5f);
			p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, 1.5f);
			p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, b.getLocation(), 50,5,5,5,0.5);
			for(Entity e : b.getNearbyEntities(5, 5, 5)) {
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
				if(e != p &&e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
            		if(!tamed.containsValue(e)) {
    					LivingEntity le = (LivingEntity)e;
    					atk1(0.6*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(),0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.058), p, le,14);
    					le.teleport(b);
            		}
				}
			}
			if(p.getLocation().distance(b.getLocation()) > 35) {
				b.teleport(p);
			}
		}
        smcooldown.put(p.getName(), System.currentTimeMillis());
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void Panda(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action a = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 9&& tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0)>=1 && !p.hasCooldown(CAREFUL)) {	
			if((a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK)&& !p.isSneaking() && !p.isOnGround())
			{	
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
				{
					double sec = 7*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
					ev.setCancelled(true);
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("판다")
							.ename("Panda")
							.slot(4)
							.hm(smcooldown)
							.skillUse(() -> {

				                if(pandam.containsKey(p.getUniqueId())) {
			                    	if(Proficiency.getpro(p)>=1) {

			                        	if(stompt.containsKey(p.getUniqueId())) {
			                        		Bukkit.getScheduler().cancelTask(stompt.get(p.getUniqueId()));
			                        		stompt.remove(p.getUniqueId());
			                        	}

			            				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			            	                @Override
			            	                public void run() {
			            						if(Proficiency.getpro(p)>=2) {
			            							stomp.putIfAbsent(p.getUniqueId(), 0);
			            						}
			            	                }
			            	            }, 3); 

			                    		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			            	                @Override
			            	                public void run() {
			            						stomp.remove(p.getUniqueId());
			            	                }
			            	            }, 25); 
			                        	stompt.put(p.getUniqueId(), task);

			                    		PandaSweep(p,(Mob) pandam.get(p.getUniqueId()));
			                    		return;
			                    	}
				                	tamed.remove(p.getUniqueId(), pandam.get(p.getUniqueId()));
				                	pandam.get(p.getUniqueId()).remove();
				                }
			                	if(pandat.containsKey(p.getUniqueId())) {
				                	Bukkit.getScheduler().cancelTask(pandat.get(p.getUniqueId()));
				                	pandat.remove(p.getUniqueId());
			                	}
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
			                    Panda pd = (Panda) p.getWorld().spawnEntity(p.getLocation(), EntityType.PANDA);
								pd.getAttribute(Attribute.KNOCKBACK_RESISTANCE).setBaseValue(1);
								pd.getAttribute(Attribute.MAX_HEALTH).setBaseValue(pd.getAttribute(Attribute.MAX_HEALTH).getBaseValue()*(10+tsd.PandaSweep.getOrDefault(p.getUniqueId(),0)*10)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)));
								pd.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(pd.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue()*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.01));
								pd.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(8*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(),0)*0.04)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.02));
			                    pd.setAdult();
			                    pd.setBreed(false);
			                    pd.setCustomName(p.getName());
								pd.setMetadata("pd of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
								pd.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), p.getName()));
								pd.setSilent(true);
								pd.setRemoveWhenFarAway(false);
								pandam.put(p.getUniqueId(), pd);
								pandao.put(pd.getUniqueId(), p.getUniqueId());
								tamed.put(p.getUniqueId(), pd);			
			                    pd.setMainGene(Gene.PLAYFUL);
			                    pd.setHiddenGene(Gene.BROWN);
			                    pd.setRolling(true);
			                    pd.setOnBack(false);
			                    pd.setMemory(MemoryKey.LIKED_PLAYER, p.getUniqueId());
		
			                    int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			             			@Override
				                	public void run() 
					                {	
					                    for(Entity e : pd.getNearbyEntities(10, 10, 10)) {
					        				if(Party.hasParty(Bukkit.getPlayer(p.getName()))&&Party.hasParty(Bukkit.getPlayer(e.getName()))){
					        					if(Party.getParty(Bukkit.getPlayer(p.getName()))==Party.getParty(Bukkit.getPlayer(e.getName()))) {
					        						continue;
					        					}
					        				}
					                    	if(e instanceof Mob && e != pd) {
					                    		Mob le = (Mob)e;
					                    		if(!tamed.containsValue(e)) {
		    			                    		le.setTarget(pd);
					                    		}
						                    	if(le.isEmpty() && p.getWorld() == pd.getWorld()) {
				    			                    if(p.getLocation().distance(Holding.ale(pd).getLocation()) >=35) {
				    			                    	pd.teleport(p);
				    			                    }
						                    	}
					                    	}
					                    }
						            }
		                		}, 0,200); 
			                    pandat.put(p.getUniqueId(), task);
							});
					bd.execute();
				
				}
			}
			}
	}


	
	@SuppressWarnings("deprecation")
	public void Stomp(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action a = ev.getAction();
		if(ClassData.pc.get(p.getUniqueId()) == 9 && !p.hasCooldown(CAREFUL) && stomp.containsKey(p.getUniqueId())) {	
			
			if((a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK)&&(a!= Action.RIGHT_CLICK_AIR)&&(a!= Action.RIGHT_CLICK_AIR)&& !p.isSneaking() && !p.isOnGround())
			{	
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
				{
					ev.setCancelled(true);

	            	if(stompt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(stompt.get(p.getUniqueId()));
	            		stompt.remove(p.getUniqueId());
	            	}
	            	stomp.remove(p.getUniqueId());

        			Panda b = (Panda) pandam.get(p.getUniqueId());
        			
	        		if(attention.containsKey(p.getUniqueId())) {
	        			LivingEntity t = (LivingEntity)attention.get(p.getUniqueId());
	        			b.teleport(t);
	        			Holding.holding(p, b, 5l);
	        			p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PANDA_BITE, 1f, 1.5f);
	        			p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PANDA_AGGRESSIVE_AMBIENT, 1f, 2f);
	        			p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PANDA_STEP, 1f, 1.5f);
	        			p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PANDA_STEP, 1f, 0.5f);
	        			p.getWorld().spawnParticle(Particle.WHITE_ASH, b.getLocation(), 100,5,2,5,0.5);
	        			p.getWorld().spawnParticle(Particle.CRIT, b.getLocation(), 100,5,2,5,0.5);
	        			p.getWorld().spawnParticle(Particle.FALLING_DUST, b.getLocation(), 100,5,2,5,0.5, Material.SAND.createBlockData());
	        			for(Entity e : b.getNearbyEntities(5, 5, 5)) {
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
	        				if(e != p &&e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
	                    		if(!tamed.containsValue(e)) {
		        					LivingEntity le = (LivingEntity)e;
		        					atk1(0.6*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(),0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.058), p, le,14);
		    	        			le.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, le.getLocation(), 2,1,1,1,0.5);
		        					Holding.holding(p, le, 40l);
	                    		}
	        				}
	        			}
	        		}
	        		else {
	        			p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PANDA_BITE, 1f, 1.5f);
	        			p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PANDA_AGGRESSIVE_AMBIENT, 1f, 2f);
	        			p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PANDA_STEP, 1f, 1.5f);
	        			p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PANDA_STEP, 1f, 0.5f);
	        			p.getWorld().spawnParticle(Particle.WHITE_ASH, b.getLocation(), 100,5,2,5,0.5);
	        			p.getWorld().spawnParticle(Particle.CRIT, b.getLocation(), 100,5,2,5,0.5);
	        			p.getWorld().spawnParticle(Particle.FALLING_DUST, b.getLocation(), 100,5,2,5,0.5, Material.SAND.createBlockData());
	        			for(Entity e : b.getNearbyEntities(5, 5, 5)) {
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
	        				if(e != p &&e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
	                    		if(!tamed.containsValue(e)) {
		        					LivingEntity le = (LivingEntity)e;
		        					atk1(0.6*(1+tsd.PandaSweep.get(p.getUniqueId())*0.03)*(1+tsd.Taming.get(p.getUniqueId())*0.058), p, le,14);
		    	        			le.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, le.getLocation(), 2,1,1,1,0.5);
		        					Holding.holding(p, le, 40l);
	                    		}
	        				}
	        			}
	        		}
				}
			}
		}
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void ULT(PlayerItemHeldEvent ev)        
	{
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		ItemStack is = p.getInventory().getItemInMainHand();
	    
		
			if(ClassData.pc.get(p.getUniqueId()) == 9  && (is.getType().name().contains("BANNER_PATTERN"))&& (is.hasItemMeta()) && ev.getNewSlot()==3 &&is.getItemMeta().hasCustomModelData()  && p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
				ev.setCancelled(true);
				p.setCooldown(CAREFUL, 2);
				double sec = 50/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("철골렘")
						.ename("IronGolem")
						.slot(6)
						.hm(sultcooldown)
						.skillUse(() -> {
		                    Location i = gettargetblock(p,4);
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2.0f);
							IronGolem ig = (IronGolem) p.getWorld().spawnEntity(i, EntityType.IRON_GOLEM);
							ig.getAttribute(Attribute.KNOCKBACK_RESISTANCE).setBaseValue(1);
							ig.getAttribute(Attribute.MAX_HEALTH).setBaseValue(ig.getAttribute(Attribute.MAX_HEALTH).getBaseValue()*(1+p.getLevel()*0.25)*(1+tsd.Taming.get(p.getUniqueId())));
							ig.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(ig.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue()*(1+p.getLevel()*0.01));
							ig.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(20*(1+tsd.Taming.get(p.getUniqueId())*0.08));
		                    ig.setPlayerCreated(true);
		                    ig.setHealth(ig.getMaxHealth());
		                    ig.setSilent(true);
		                    ig.setMemory(MemoryKey.LIKED_PLAYER, p.getUniqueId());
							ig.setMetadata("ig of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
							ig.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), p.getName()));	
							ig.setCustomName(p.getName());
		                    p.swingMainHand();       
		                    
		                    irong.put(p.getUniqueId(), ig);
		                    
		                    tamed.put(p.getUniqueId(), ig);
		                    for(Mob m : tamed.get(p.getUniqueId())) {
								m.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 500, 2, false, false));
								m.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 500, 2, false, false));
								m.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 500, 2, false, false));
		                    }
		                    if(attention.containsKey(p.getUniqueId())) {
		                    	ig.setTarget(attention.get(p.getUniqueId()));
		                    }
		                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
			                	public void run() 
				                {	
			                    	tamed.remove(p.getUniqueId(), ig);
			                    	irong.remove(p.getUniqueId(), ig);
			                    	ig.remove();
					            }
		                	}, 500); 
		                	
						});
				bd.execute();
			}
	}

	
	public void Smash(PlayerItemHeldEvent ev)        
	{
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		ItemStack is = p.getInventory().getItemInMainHand();
	    
		
			if(ClassData.pc.get(p.getUniqueId()) == 9 && (is.getType().name().contains("BANNER_PATTERN"))&&ev.getNewSlot()==3 && (is.hasItemMeta()) && is.getItemMeta().hasCustomModelData()  && p.isSneaking() &&
					Proficiency.getpro(p) >=1)
			{
				ev.setCancelled(true);
				p.setCooldown(CAREFUL, 2);
				if(!irong.containsKey(p.getUniqueId())) {
					return;
				}
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(10*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
						.kname("골렘강타")
						.ename("GolemSmash")
						.slot(6)
						.hm(sultscooldown)
						.skillUse(() -> {
		                    irong.get(p.getUniqueId()).forEach(b -> {

			            		if(attention.containsKey(p.getUniqueId())) {
			            			LivingEntity t = (LivingEntity)attention.get(p.getUniqueId());
			            			b.teleport(t);
			            			Holding.holding(p, b, 5l);
			            			b.swingMainHand();
			            			p.getWorld().playSound(b.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 1f, 0.8f);
			            			p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, 0.6f);
			            			p.getWorld().spawnParticle(Particle.BLOCK, b.getLocation(), 100,5,5,5,0.5, Material.IRON_BLOCK.createBlockData());
			            			p.getWorld().spawnParticle(Particle.CRIT, b.getLocation(), 50,5,5,5,0.5);
			            			for(Entity e : b.getNearbyEntities(5, 5, 5)) {
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
			            				if(e != p &&e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
			                        		if(!tamed.containsValue(e)) {
			                					LivingEntity le = (LivingEntity)e;
			                					atk1(10*(1+tsd.Taming.get(p.getUniqueId())*0.058), p, le,14);
			                					le.teleport(b);
			                        		}
			            				}
			            			}
			            		}
			            		else {
			            			b.swingMainHand();
			            			p.getWorld().playSound(b.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 1f, 0.8f);
			            			p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, 0.6f);
			            			p.getWorld().spawnParticle(Particle.BLOCK, b.getLocation(), 100,5,5,5,0.5, Material.IRON_BLOCK.createBlockData());
			            			p.getWorld().spawnParticle(Particle.CRIT, b.getLocation(), 50,5,5,5,0.5);
			            			for(Entity e : b.getNearbyEntities(5, 5, 5)) {
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
			            				if(e != p &&e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
			                        		if(!tamed.containsValue(e)) {
			                					LivingEntity le = (LivingEntity)e;
			                					atk1(10*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.058), p, le,14);
			                					le.teleport(b);
			                        		}
			            				}
			            			}
			            			if(p.getLocation().distance(b.getLocation()) > 35) {
			            				b.teleport(p);
			            			}
			            		}
		                    });
		                    
		                    
		                    
						});
				bd.execute();
			}
	}
	public HashMap<UUID, Boolean> kbr = new HashMap<UUID, Boolean>();
	public HashMap<UUID, Integer> kbrt = new HashMap<UUID, Integer>();
	
	final private void knockback(Entity le) {

		if(kbrt.containsKey(le.getUniqueId())) {
			Bukkit.getScheduler().cancelTask(kbrt.get(le.getUniqueId()));
			kbrt.remove(le.getUniqueId());
		}
		kbr.putIfAbsent(le.getUniqueId(), true);
		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
				kbr.remove(le.getUniqueId());
				kbrt.remove(le.getUniqueId());
            }
		}, 20); 
		kbrt.put(le.getUniqueId(), task);
	}
	
	@SuppressWarnings("deprecation")
	final private void ult2(Player p) {

        
    	if(ddt.containsKey(p.getUniqueId())) {
        	Bukkit.getScheduler().cancelTask(ddt.get(p.getUniqueId()));
        	ddt.remove(p.getUniqueId());
    	}
        
        Location i = p.getEyeLocation().clone().add(0, 2.12, 0);
		i.setDirection(i.clone().getDirection().normalize().multiply(-1));
        p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 0.6f, 2.0f);
		EnderDragon ig = (EnderDragon) p.getWorld().spawnEntity(i, EntityType.ENDER_DRAGON);
		for (Entity e : ig.getNearbyEntities(20, 20, 20)) {
		    e.setVelocity(new Vector(0, 0, 0));
		}
		ig.getAttribute(Attribute.KNOCKBACK_RESISTANCE).setBaseValue(1);
		ig.getAttribute(Attribute.ATTACK_KNOCKBACK).setBaseValue(0);
		ig.getAttribute(Attribute.MAX_HEALTH).setBaseValue(ig.getAttribute(Attribute.MAX_HEALTH).getBaseValue()*(1+p.getLevel()*0.25)*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)));
		ig.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0);

		ig.setCollidable(false);
        ig.setHealth(ig.getMaxHealth());
        ig.setSilent(true);
		ig.setMetadata("rob of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
		ig.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), p.getName()));	
		ig.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), p.getName()));
		ig.setCustomName(p.getName());
        ig.setPhase(Phase.HOVER);
        ig.getParts().forEach(par -> {
			
			par.setGlowing(true);
        	par.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), p.getName()));	
        	par.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), p.getName()));
        	par.setGravity(false);
            par.setSilent(true);
        });
		
        Holding.invur(p, 160l);
        Holding.fly(p, 160l);
        
        tamed.put(p.getUniqueId(), ig);
        for(Mob m : tamed.get(p.getUniqueId())) {
            Holding.invur(m, 160l);
        }
        if(attention.containsKey(p.getUniqueId())) {
        	ig.setTarget(attention.get(p.getUniqueId()));
        }
        int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
 			@Override
        	public void run() 
            {
 				p.setLeashHolder(ig);
				final Location tl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(3));
    			tl.getWorld().spawnParticle(Particle.DRAGON_BREATH, tl, 100,3,3,3,0.5);
                ig.setPhase(Phase.HOVER);
 				Location pl = p.getEyeLocation().clone().add(0, 2.12, 0);
 				pl.setDirection(pl.clone().getDirection().normalize().multiply(-1));
                ig.teleport(pl);
    			for(Entity e : ig.getNearbyEntities(15, 15, 15)) {
    				knockback(e);
            		if(tamed.containsValue(e)) {
            			continue;
            		}
    	    		if (e instanceof Player) 
    				{
    					Player p1 = (Player) e;
    					if(Party.hasParty(p) && Party.hasParty(p1))	{
    					if(Party.getParty(p).equals(Party.getParty(p1)))
    						{
    				        	Holding.invur(p1, 160l);
    							continue;
    						}
    					}
    				}
    				if(e != p &&e instanceof LivingEntity&& !e.hasMetadata("fake")&& !e.hasMetadata("portal")) {
    					LivingEntity le = (LivingEntity)e;
    					atk1(0.1*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.058), p, le,14);
    					le.teleport(tl);
    					Holding.holding(p, le, 10l);
    					le.setVelocity(new Vector(0, 0, 0));
    				}
    			}
            }
		}, 0,1); 
        dt.put(p.getUniqueId(), task);
        ig.setCollidable(false);
        ig.setAware(false);
        ig.setGravity(false);
    	int task1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
 		@Override
        	public void run() 
            {	
            	tamed.remove(p.getUniqueId(), ig);
            	ig.remove();
	        	if(dt.containsKey(p.getUniqueId())) {
	            	Bukkit.getScheduler().cancelTask(dt.get(p.getUniqueId()));
		        	dt.remove(p.getUniqueId());
	        	}
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 120, 3, false, false));
            }
    	}, 150); 
    	ddt.put(p.getUniqueId(), task1);
    	
    	
	}


	public void ULT2(PlayerItemHeldEvent ev)        
	{
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		ItemStack is = p.getInventory().getItemInMainHand();
	    
		
			if(ClassData.pc.get(p.getUniqueId()) == 9  && ev.getNewSlot()==4 && (is.getType().name().contains("BANNER_PATTERN"))&& (is.hasItemMeta()) && is.getItemMeta().hasCustomModelData()  && p.isSneaking() && Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
				p.setCooldown(CAREFUL, 2);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(80*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
						.kname("길들여진 드래곤")
						.ename("Tamed Dragon")
						.slot(7)
						.hm(sult2cooldown)
						.skillUse(() -> {
		                    ult2(p);
		                	
						});
				bd.execute();
			}
	}
	public void TamedDragon(EntityExplodeEvent ev) 
	{
		if(ev.getEntity() instanceof EnderDragon){
			EnderDragon ed = (EnderDragon) ev.getEntity();
	        if(ed.hasMetadata("untargetable")){
	        	ev.setCancelled(true);
	        }
		 }
		if(ev.getEntity() instanceof EnderDragonPart){
			EnderDragonPart p = (EnderDragonPart) ev.getEntity();
			EnderDragon ed = p.getParent();
	        if(ed.hasMetadata("untargetable")){
	        	ev.setCancelled(true);
	        }
		 }
	}
	

	public void TamedDragon(EntityChangeBlockEvent ev) 
	{
		if(ev.getEntity() instanceof EnderDragon){
			EnderDragon ed = (EnderDragon) ev.getEntity();
	        if(ed.hasMetadata("untargetable")){
	        	ev.setCancelled(true);
	        }
		 }
		if(ev.getEntity() instanceof EnderDragonPart){
			EnderDragonPart p = (EnderDragonPart) ev.getEntity();
			EnderDragon ed = p.getParent();
	        if(ed.hasMetadata("untargetable")){
	        	ev.setCancelled(true);
	        }
		 }
	}
	
	@SuppressWarnings("deprecation")
	public void ThrowCancel(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
		
			if(ClassData.pc.get(p.getUniqueId()) == 9 && (is.getType().name().contains("BANNER_PATTERN"))&& (is.hasItemMeta()) && is.getItemMeta().hasCustomModelData() && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
			}
	
    }
	
	
	
	public void DamageCancel(EntityDamageByEntityEvent d) 
	{
		
		/*if(d.getDamager() instanceof EnderDragonPart) {

			EnderDragonPart pa = (EnderDragonPart)d.getDamager();
			if(pa.hasMetadata("untargetable")) {
				Player ow = Bukkit.getPlayer(pa.getMetadata("untargetable").get(0).asString());
				ow.sendMessage(d.getDamager().toString() + d.getEntity().toString() + d.getCause());
				d.setCancelled(true);
				return;
			}
			EnderDragon le = pa.getParent();
			if(le.hasMetadata("untargetable")) {
				Player ow = Bukkit.getPlayer(le.getMetadata("untargetable").get(0).asString());
				ow.sendMessage(d.getDamager().toString() + d.getEntity().toString() + d.getCause());
				d.setCancelled(true);
				return;
			}
		}*/
		if(d.getDamager() instanceof EnderDragon) {

			EnderDragon le = (EnderDragon) d.getDamager();
			if(le.hasMetadata("untargetable")) {
				d.setCancelled(true);
				return;
			}
		}
		if(d.getEntity() instanceof Player) {
			Player p = (Player)d.getEntity();
			if(d.getDamager() instanceof LivingEntity) {

				LivingEntity le = (LivingEntity)d.getDamager();
				if(le.hasMetadata("untargetable")) {
					Player ow = Bukkit.getPlayer(le.getMetadata("untargetable").get(0).asString());
					if(ow.getName().equals(p.getName())) {
						d.setCancelled(true);
					}
					if(Party.hasParty(p) && Party.hasParty(ow)) {
						if(Party.getParty(p).equals(Party.getParty(ow))) {
							d.setCancelled(true);
						}
					}
				}
				else {
					 if(!attention.containsKey(p.getUniqueId())) {
						 tamed.get(p.getUniqueId()).forEach(m -> m.setTarget(le));
					 }
					 Wdcskills.zombies.get(p.getUniqueId()).forEach(m -> m.setTarget(le));
				}
			 }
			 if(d.getDamager() instanceof Projectile) {

					Projectile pr = (Projectile)d.getDamager();
					if(pr.getShooter() instanceof LivingEntity) {
						LivingEntity le = (LivingEntity) pr.getShooter();
						if(le.hasMetadata("untargetable")) {
							Player ow = Bukkit.getPlayer(le.getMetadata("untargetable").get(0).asString());
							if(ow == p) {
								d.setCancelled(true);
							}
							if(Party.hasParty(p) && Party.hasParty(ow)) {
								if(Party.getParty(p).equals(Party.getParty(ow))) {
									d.setCancelled(true);
								}
							}
						}
						else {
								if(!attention.containsKey(p.getUniqueId())) {
								 tamed.get(p.getUniqueId()).forEach(m -> m.setTarget(le));
								}
								Wdcskills.zombies.get(p.getUniqueId()).forEach(m -> m.setTarget(le));
						}
					}
			 }
			 if(d.getDamager() instanceof AreaEffectCloud) {

				 AreaEffectCloud ep = (AreaEffectCloud)d.getDamager();
				 ProjectileSource ps = ep.getSource();
				 if(ps instanceof LivingEntity) {
					 LivingEntity le = (LivingEntity) ps;
					 if(le.hasMetadata("untargetable")) {
						 Player ow = Bukkit.getPlayer(le.getMetadata("untargetable").get(0).asString());
					 	if(ow == p) {
							d.setCancelled(true);
						}
						if(Party.hasParty(p) && Party.hasParty(ow)) {
							if(Party.getParty(p).equals(Party.getParty(ow))) {
								d.setCancelled(true);
							}
						}
					}
					else {
							if(!attention.containsKey(p.getUniqueId())) {
							 tamed.get(p.getUniqueId()).forEach(m -> m.setTarget(le));
							}
							Wdcskills.zombies.get(p.getUniqueId()).forEach(m -> m.setTarget(le));
					}
				 }
			 }
		}
		 if(d.getEntity() instanceof LivingEntity && d.getDamager() instanceof Player) {

			 Player p = (Player)d.getDamager();
			 LivingEntity le = (LivingEntity)d.getEntity();
			 if(le.hasMetadata("untargetable")) {
				 Player ow = Bukkit.getPlayer(le.getMetadata("untargetable").get(0).asString());
				 if(tamed.containsEntry(p.getUniqueId(), le) || le.hasMetadata("griffon")) {
					 d.setCancelled(true);
				 }
				 if(Party.hasParty(p) && Party.hasParty(ow)) {
					 if(Party.getParty(p).equals(Party.getParty(ow))) {
						 d.setCancelled(true);
					 }
				 }
				 else if(!tamed.containsEntry(p.getUniqueId(), le)){
					 if(!p.hasCooldown(Material.YELLOW_TERRACOTTA)) {
							attention.put(p.getUniqueId(), le);

							if(tamed.containsKey(p.getUniqueId())) {
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
	        					if(!le.hasMetadata("fake") && !le.hasMetadata("portal")) {
	    							pta(p,le);
	        					}
							}
					 }
					 else {
						 if(!attention.containsKey(p.getUniqueId())) {
							 tamed.get(p.getUniqueId()).forEach(m -> m.setTarget(le));
						 }
						 else {
							 tamed.get(p.getUniqueId()).forEach(m -> m.setTarget(attention.get(p.getUniqueId())));
						 }
					 }
				 }
			 }
			 else {
				 if(!p.hasCooldown(Material.YELLOW_TERRACOTTA)) {
						attention.put(p.getUniqueId(), le);
						if(tamed.containsKey(p.getUniqueId())) {
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
        					if(!le.hasMetadata("fake") && !le.hasMetadata("portal")) {
    							pta(p,le);
        					}
						}
				 }
				 else {
					 Wdcskills.zombies.get(p.getUniqueId()).forEach(m -> m.setTarget(le));
					 if(!attention.containsKey(p.getUniqueId())) {
						 tamed.get(p.getUniqueId()).forEach(m -> m.setTarget(le));
					 }
					 else {
						 tamed.get(p.getUniqueId()).forEach(m -> m.setTarget(attention.get(p.getUniqueId())));
					 }
				 }
			 }
		 }
		 
		 if(d.getEntity() instanceof LivingEntity && d.getDamager() instanceof Projectile) {

			 Projectile pr = (Projectile)d.getDamager();
			 LivingEntity le = (LivingEntity)d.getEntity();
			 if(pr.getShooter() instanceof Player) {
				 Player p = (Player) pr.getShooter();
				 if(le.hasMetadata("untargetable")) {
					 Player ow = Bukkit.getPlayer(le.getMetadata("untargetable").get(0).asString());
					 if(tamed.containsEntry(p.getUniqueId(), le)) {
						 d.setCancelled(true);
					 }
						
					 if(Party.hasParty(p) && Party.hasParty(ow)) {
						 if(Party.getParty(p).equals(Party.getParty(ow))) {
							 d.setCancelled(true);
						 }
					 }
				 }
			 }
		 }

		 if(!(d.getEntity() instanceof Player)&& d.getEntity() instanceof LivingEntity) {
			 LivingEntity le = (LivingEntity)d.getEntity();
			 if(d.getDamager() instanceof AreaEffectCloud) {

				 AreaEffectCloud ep = (AreaEffectCloud)d.getDamager();
				 ProjectileSource ps = ep.getSource();
				 if(ps instanceof LivingEntity) {
					 LivingEntity dger = (LivingEntity) ps;
					 if(dger.hasMetadata("untargetable")&&le.hasMetadata("untargetable")) {
						 if(dger.getMetadata("untargetable").get(0).asString().equals(le.getMetadata("untargetable").get(0).asString())) {
							 d.setCancelled(true);
						 }
					 }
					 else if(dger.hasMetadata("untargetable") && !le.hasMetadata("untargetable")) {
						 Player p = Bukkit.getPlayer(dger.getMetadata("untargetable").get(0).asString());
						 if(dger instanceof CaveSpider || dger instanceof Bee) {
							 atk1(d.getDamage()*0.68, p, le,11);
						 }
						 else {
							 atk1(d.getDamage()*0.68, p, le,14);
						 }
						 d.setCancelled(true);
					 }
					 else if(!dger.hasMetadata("untargetable") && le.hasMetadata("untargetable")) {
						 Player p = Bukkit.getPlayer(le.getMetadata("untargetable").get(0).asString());
						 if(le.getType() == EntityType.PANDA) {
							 if(!attention.containsKey(p.getUniqueId())) {
								 tamed.get(p.getUniqueId()).forEach(m -> m.setTarget(le));
							 }
						 }
					 }
				 }
			 }
			 if(d.getDamager() instanceof Projectile) {

				 Projectile ep = (Projectile)d.getDamager();
				 ProjectileSource ps = ep.getShooter();
				 if(ps instanceof LivingEntity) {
					 LivingEntity dger = (LivingEntity) ps;
					 if(dger.hasMetadata("untargetable")&&le.hasMetadata("untargetable")) {
						 if(dger.getMetadata("untargetable").get(0).asString().equals(le.getMetadata("untargetable").get(0).asString())) {
							 d.setCancelled(true);
						 }
					 }
					 else if(dger.hasMetadata("untargetable") && !le.hasMetadata("untargetable")) {
						 Player p = Bukkit.getPlayer(dger.getMetadata("untargetable").get(0).asString());
						 if(dger instanceof CaveSpider || dger instanceof Bee) {
							 atk1(d.getDamage()*0.68, p, le,11);
						 }
						 else {
							 atk1(d.getDamage()*0.68, p, le,14);
						 }
						 d.setCancelled(true);
					 }
					 else if(!dger.hasMetadata("untargetable") && le.hasMetadata("untargetable")) {
						 Player p = Bukkit.getPlayer(le.getMetadata("untargetable").get(0).asString());
						 if(le.getType() == EntityType.PANDA) {
							 if(!attention.containsKey(p.getUniqueId())) {
								 tamed.get(p.getUniqueId()).forEach(m -> m.setTarget(le));
							 }
						 }
					 }
				 }
			 }
			 if(d.getDamager() instanceof Mob) {

				 Mob dger = (Mob)d.getDamager();
				 if(dger.hasMetadata("untargetable")&&le.hasMetadata("untargetable")) {
					 if(dger.getMetadata("untargetable").get(0).asString().equals(le.getMetadata("untargetable").get(0).asString())) {
						 d.setCancelled(true);
					 }
				 }
				 else if(dger.hasMetadata("untargetable") && !le.hasMetadata("untargetable")) {
					 Player p = Bukkit.getPlayer(dger.getMetadata("untargetable").get(0).asString());
					 if(dger instanceof CaveSpider || dger instanceof Bee) {
						 atk1(d.getDamage()*0.68, p, le,11);
					 }
					 else {
						 atk1(d.getDamage()*0.68, p, le,14);
					 }
					 d.setCancelled(true);
				 }
				 else if(!dger.hasMetadata("untargetable") && le.hasMetadata("untargetable")) {
					 Player p = Bukkit.getPlayer(le.getMetadata("untargetable").get(0).asString());
					 if(le.getType() == EntityType.PANDA) {
						 if(!attention.containsKey(p.getUniqueId())) {
							 tamed.get(p.getUniqueId()).forEach(m -> m.setTarget(le));
						 }
					 }
				 }
			 }
		 }
	}

	
	
	public void Target(EntityTargetEvent d) 
	{
		 if(d.getTarget() instanceof Player && d.getEntity() instanceof LivingEntity) {

			 Player p = (Player)d.getTarget();
			 LivingEntity le = (LivingEntity)d.getEntity();
			 if(le.hasMetadata("untargetable")) {
				 Mob m = (Mob)le;
				 Player ow = Bukkit.getPlayer(le.getMetadata("untargetable").get(0).asString());
				 if(ow == p) {
					 d.setCancelled(true);
					 if(attention.containsKey(p.getUniqueId())) {
						 m.setTarget(attention.get(p.getUniqueId()));
					 }
				 }
				 else if(Party.hasParty(p) && Party.hasParty(ow)) {
					 if(Party.getParty(p).equals(Party.getParty(ow))) {
						 d.setCancelled(true);
						 if(attention.containsKey(p.getUniqueId())) {
							 m.setTarget(attention.get(p.getUniqueId()));
						 }
					 }
				 }
			 }
			 else {
				 Wdcskills.zombies.get(p.getUniqueId()).forEach(m -> m.setTarget(le));
				 if(attention.containsKey(p.getUniqueId())) {
					 tamed.get(p.getUniqueId()).forEach(m -> m.setTarget(attention.get(p.getUniqueId())));
				 }
				 else {
					 tamed.get(p.getUniqueId()).forEach(m -> m.setTarget(le));
				 }
			 }
		 }
		 else if(d.getTarget() instanceof LivingEntity && d.getEntity() instanceof LivingEntity) {

			 LivingEntity le = (LivingEntity)d.getEntity();
			 LivingEntity tar = (LivingEntity)d.getTarget();
			 if(tar.hasMetadata("untargetable")&&le.hasMetadata("untargetable")) {
				 if(tar.getMetadata("untargetable").get(0).asString().equals(le.getMetadata("untargetable").get(0).asString())) {
					 Mob m = (Mob)le;
					 Mob mt = (Mob)tar;
					 d.setCancelled(true);
					 Player ow = Bukkit.getPlayer(le.getMetadata("untargetable").get(0).asString());
					 if(attention.containsKey(ow.getUniqueId())) {
						 m.setTarget(attention.get(ow.getUniqueId()));
						 mt.setTarget(attention.get(ow.getUniqueId()));
					 }
				 }
			 }
			 else if(tar.hasMetadata("untargetable") && !le.hasMetadata("untargetable")) {
				 Mob tm = (Mob) tar;
				 Player p = Bukkit.getPlayer(tar.getMetadata("untargetable").get(0).asString());
				 if(!attention.containsKey(p.getUniqueId())) {
					 tm.setTarget(le);
				 }
				 
			 }
		 }
	}


	public void ArmorIncrease(EntityDamageEvent d) 
	{

		if(kbr.containsKey(d.getEntity().getUniqueId())) {
			final Entity e = d.getEntity();
			e.setVelocity(e.getVelocity().clone().zero());
		}
		if(d.getEntity() instanceof LivingEntity) 
		{
			LivingEntity le = (LivingEntity)d.getEntity();
			if(le.hasMetadata("untargetable")) {
				Player p = Bukkit.getPlayer(le.getMetadata("untargetable").get(0).asString());
				if(ClassData.pc.get(p.getUniqueId()) == 9) {
					if((le.getType() == EntityType.PANDA || Proficiency.getpro(p)>=1) && d.getCause() != DamageCause.VOID) {
						d.setDamage(0);
						le.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 500, 0, false, false));
					}
				}
			}
		}
	}

	public void ArmorIncrease(EntityPotionEffectEvent d) 
	{
		if(d.getEntity() instanceof LivingEntity) 
		{
			LivingEntity p = (LivingEntity)d.getEntity();
			if(p.hasMetadata("untargetable")) {
				Player o = Bukkit.getPlayer(p.getMetadata("untargetable").get(0).asString());
				if(o!=null && ClassData.pc.get(o.getUniqueId()) == 9) {
					if(Proficiency.getpro(o)>=1) {

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {	
			                	cleans(p);
				                	
			                }
						}, 5); 
					}
				}
			}
		}
		
	}
	
	
	
	public void delete(PlayerDeathEvent d) 
	{
		if(d.getEntity() instanceof Player) {
			Player p = (Player) d.getEntity();
			if(tamed.containsKey(p.getUniqueId())) {
				tamed.get(p.getUniqueId()).forEach(le -> {
					le.remove();
				});
				tamed.removeAll(p.getUniqueId());
			}
			if(pets.containsKey(p.getUniqueId())) {
				pets.get(p.getUniqueId()).forEach(le -> {
					le.remove();
				});
				pets.removeAll(p.getUniqueId());
			}
			if(pandam.containsKey(p.getUniqueId())) {
				pandam.remove(p.getUniqueId());
			}
        	if(pandat.containsKey(p.getUniqueId())) {
        		Bukkit.getScheduler().cancelTask(pandat.get(p.getUniqueId()));
        		pandat.remove(p.getUniqueId());
        	}
        	if(dt.containsKey(p.getUniqueId())) {
            	Bukkit.getScheduler().cancelTask(dt.get(p.getUniqueId()));
	        	dt.remove(p.getUniqueId());
        	}
		}
	}
	
	
	public void delete(EntityDeathEvent d) 
	{
		if(d.getEntity() instanceof Mob) {
			Mob le = (Mob) d.getEntity();

			if(le.hasMetadata("untargetable")) {
				Player p = Bukkit.getPlayer(le.getMetadata("untargetable").get(0).asString());
				if(tamed.containsEntry(p.getUniqueId(),le)) {
					tamed.remove(p.getUniqueId(), le);
				}
				if(pets.containsEntry(p.getUniqueId(),le)) {
					pets.remove(p.getUniqueId(), le);
				}
				if(pandam.containsKey(p.getUniqueId())) {
					pandam.remove(p.getUniqueId(), le);
				}
	        	if(pandat.containsKey(p.getUniqueId())) {
	            	Bukkit.getScheduler().cancelTask(pandat.get(p.getUniqueId()));
		        	pandat.remove(p.getUniqueId());
	        	}
	        	if(dt.containsKey(p.getUniqueId())) {
	            	Bukkit.getScheduler().cancelTask(dt.get(p.getUniqueId()));
		        	dt.remove(p.getUniqueId());
	        	}
			}
			if(attention.containsValue(le)){
				UUID pu = attention.keySet().stream().filter(k -> attention.get(k) == le).findFirst().get();
				attention.remove(pu);
				
			}
		}
	}
	
	
	public void delete(PlayerQuitEvent d) 
	{
		Player p = d.getPlayer();
		if(tamed.containsKey(p.getUniqueId())) {
			tamed.get(p.getUniqueId()).forEach(le -> {
				le.remove();
			});
			tamed.removeAll(p.getUniqueId());
		}
		if(pets.containsKey(p.getUniqueId())) {
			pets.get(p.getUniqueId()).forEach(le -> {
				le.remove();
			});
			pets.removeAll(p.getUniqueId());
		}
		if(pandam.containsKey(p.getUniqueId())) {
			pandam.remove(p.getUniqueId());
		}
    	if(pandat.containsKey(p.getUniqueId())) {
        	Bukkit.getScheduler().cancelTask(pandat.get(p.getUniqueId()));
        	pandat.remove(p.getUniqueId());
    	}
    	if(dt.containsKey(p.getUniqueId())) {
        	Bukkit.getScheduler().cancelTask(dt.get(p.getUniqueId()));
        	dt.remove(p.getUniqueId());
    	}
	}
	
	public void delete(PlayerTeleportEvent d) 
	{
		Player p = (Player) d.getPlayer();
		if(tamed.containsKey(p.getUniqueId())) {
			if(d.getFrom().getWorld() != d.getTo().getWorld()) {
				if(tamed.containsKey(p.getUniqueId())) {
					tamed.get(p.getUniqueId()).forEach(le -> {
						le.remove();
					});
					tamed.removeAll(p.getUniqueId());
				}
				if(pets.containsKey(p.getUniqueId())) {
					pets.get(p.getUniqueId()).forEach(le -> {
						le.remove();
					});
					pets.removeAll(p.getUniqueId());
				}
				if(pandam.containsKey(p.getUniqueId())) {
					pandam.remove(p.getUniqueId());
				}
		    	if(pandat.containsKey(p.getUniqueId())) {
		        	Bukkit.getScheduler().cancelTask(pandat.get(p.getUniqueId()));
		        	pandat.remove(p.getUniqueId());
		    	}
	        	if(dt.containsKey(p.getUniqueId())) {
	            	Bukkit.getScheduler().cancelTask(dt.get(p.getUniqueId()));
		        	dt.remove(p.getUniqueId());
	        	}
			}
		}
	}
	
	
	
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 9)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
			{
					e.setCancelled(true);
			}
		}
		
	}
		
	
	public void ArmorDecrease(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity() instanceof Player && d.getDamager() instanceof LivingEntity) 
		{
			if(ClassData.pc.get(d.getEntity().getUniqueId()) == 9) {
				d.setDamage(d.getDamage()*1.25);
			}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof Player) 
		{
			Arrow ar = (Arrow)d.getDamager();
	
			if(ar.getShooter() instanceof LivingEntity) {
				if(ClassData.pc.get(d.getEntity().getUniqueId()) == 9) {
					d.setDamage(d.getDamage()*1.25);
				}
			}
		}
	}
	
	
	public void Damagegetter(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
			Player p = (Player)d.getDamager();
			Entity e = d.getEntity();

	        
			
			
				if(ClassData.pc.get(p.getUniqueId()) == 9) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
					{
						if(e.hasMetadata("leader") || e.hasMetadata("boss")) {
							d.setDamage(d.getDamage()*2.2);
						}
					}
				}
		}

		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
			Arrow ar = (Arrow)d.getDamager();
	
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
				LivingEntity e = (LivingEntity) d.getEntity();
			    
				
				
				if(ClassData.pc.get(p.getUniqueId()) == 9) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
					{
						if(e.hasMetadata("leader") || e.hasMetadata("boss")) {
							dset2(d, p, 2.2, e, 5);
						}
					}
				}
			}
		}
	}
	

	public void Recall(PlayerItemHeldEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 9 && !p.hasCooldown(Material.PANDA_SPAWN_EGG) && p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() &&  p.isSneaking() && tamed.containsKey(p.getUniqueId())){
			ev.setCancelled(true);
			p.setCooldown(Material.PANDA_SPAWN_EGG, 3);
			
			if(ev.getNewSlot() == 3 || ev.getNewSlot() == 4) {
				return;
			}
			
			if(ev.getPreviousSlot()==0) {
				if(ev.getNewSlot() < 8) {
					if(tamed.containsKey(p.getUniqueId())) {
						tamed.get(p.getUniqueId()).forEach(le -> {
							le.remove();
						});
						tamed.removeAll(p.getUniqueId());
					}
					if(pets.containsKey(p.getUniqueId())) {
						pets.get(p.getUniqueId()).forEach(le -> {
							le.remove();
						});
						pets.removeAll(p.getUniqueId());
					}
					if(pandam.containsKey(p.getUniqueId())) {
						pandam.remove(p.getUniqueId());
					}
		        	if(pandat.containsKey(p.getUniqueId())) {
		        		Bukkit.getScheduler().cancelTask(pandat.get(p.getUniqueId()));
		        		pandat.remove(p.getUniqueId());
		        	}
		        	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"동물들을 모두 되돌려 보냈습니다").create());
		        	}
		        	else {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"All Animals Returned").create());
		        	}
                	return;
					
				}
				else {
					if(tamed.containsKey(p.getUniqueId())) {
						tamed.get(p.getUniqueId()).forEach(le -> {
							le.teleport(p);
						});
					}
					if(attention.containsKey(p.getUniqueId())) {
						attention.remove(p.getUniqueId());
					}
                	return;
				}
			}
			else if(ev.getPreviousSlot()==8) {
				if(ev.getNewSlot()==0) {
					if(tamed.containsKey(p.getUniqueId())) {
						tamed.get(p.getUniqueId()).forEach(le -> {
							le.remove();
						});
						tamed.removeAll(p.getUniqueId());
					}
					if(pets.containsKey(p.getUniqueId())) {
						pets.get(p.getUniqueId()).forEach(le -> {
							le.remove();
						});
						pets.removeAll(p.getUniqueId());
					}
					if(pandam.containsKey(p.getUniqueId())) {
						pandam.remove(p.getUniqueId());
					}
		        	if(pandat.containsKey(p.getUniqueId())) {
		        		Bukkit.getScheduler().cancelTask(pandat.get(p.getUniqueId()));
		        		pandat.remove(p.getUniqueId());
		        	}
		        	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"동물들을 모두 되돌려 보냈습니다").create());
		        	}
		        	else {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"All Animals Returned").create());
		        	}
                	return;
					
				}
				else {
					if(tamed.containsKey(p.getUniqueId())) {
						tamed.get(p.getUniqueId()).forEach(le -> {
							le.teleport(p);
						});
					}
					if(attention.containsKey(p.getUniqueId())) {
						attention.remove(p.getUniqueId());
					}
                	return;
				}
			}
			else {
				if(ev.getNewSlot()>ev.getPreviousSlot()) {
					if(tamed.containsKey(p.getUniqueId())) {
						tamed.get(p.getUniqueId()).forEach(le -> {
							le.remove();
						});
						tamed.removeAll(p.getUniqueId());
					}
					if(pets.containsKey(p.getUniqueId())) {
						pets.get(p.getUniqueId()).forEach(le -> {
							le.remove();
						});
						pets.removeAll(p.getUniqueId());
					}
					if(pandam.containsKey(p.getUniqueId())) {
						pandam.remove(p.getUniqueId());
					}
		        	if(pandat.containsKey(p.getUniqueId())) {
		        		Bukkit.getScheduler().cancelTask(pandat.get(p.getUniqueId()));
		        		pandat.remove(p.getUniqueId());
		        	}
		        	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"동물들을 모두 되돌려 보냈습니다").create());
		        	}
		        	else {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"All Animals Returned").create());
		        	}
                	return;
					
				}
				else if(ev.getNewSlot()<ev.getPreviousSlot()){
					if(tamed.containsKey(p.getUniqueId())) {
						tamed.get(p.getUniqueId()).forEach(le -> {
							le.teleport(p);
						});
					}
					if(attention.containsKey(p.getUniqueId())) {
						attention.remove(p.getUniqueId());
					}
                	return;
				}
			}
			
		}
	}
	
	
}