package io.github.chw3021.classes.chemist;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.EntityEffect;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Tag;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Cheskills extends Pak {
	
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> stcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sult2cooldown = new HashMap<String, Long>();
	//private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private Multimap<Player, Integer> extracted = ArrayListMultimap.create();
	
	private HashMap<UUID, Integer> cloudh = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> cloudt = new HashMap<UUID, Integer>();
	
	
	
	private HashMap<UUID, Integer> chargingt = new HashMap<UUID, Integer>();
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private CheSkillsData csd;

	
	private HashMap<UUID, Integer> wp = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> wpt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> mg = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> mgt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> hg = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> hgt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> gb = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> gbt = new HashMap<UUID, Integer>();

	static private HashMap<UUID, Integer> acidstorming = new HashMap<UUID, Integer>();
	static private HashMap<UUID, Integer> acidstormingt = new HashMap<UUID, Integer>();
	
	
	private HashMap<UUID, Integer> acdstrm = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> acdstrmt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> acdbmb = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> acdbmbt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> vx = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> vxt = new HashMap<UUID, Integer>();

	
	private static final Cheskills instance = new Cheskills ();
	public static Cheskills getInstance()
	{
		return instance;
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
        		cloudh.remove(p.getUniqueId());
        		if(cloudt.containsKey(p.getUniqueId())) {
        			Bukkit.getServer().getScheduler().cancelTask(cloudt.remove(p.getUniqueId()));
        		}
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

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		CheSkillsData c = new CheSkillsData(CheSkillsData.loadData(path +"/plugins/RPGskills/CheSkillsData.data"));
		csd = c;
		
	}

		
	public void er(PluginEnableEvent ev) 
	{
		CheSkillsData c = new CheSkillsData(CheSkillsData.loadData(path +"/plugins/RPGskills/CheSkillsData.data"));
		csd = c;
	}
	
	
	public void SlimeBall(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 15&& csd.SlimeBall.getOrDefault(p.getUniqueId(), 0)>=1 && p.isSneaking())
		{
			double sec =8*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")))
			{
				ev.setCancelled(true);
				
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("슬라임볼")
						.ename("SlimeBall")
						.slot(0)
						.hm(sdcooldown)
						.skillUse(() -> {
						
	                	if(mgt.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(mgt.get(p.getUniqueId()));
	                		mgt.remove(p.getUniqueId());
	                	}

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								if(Proficiency.getpro(p)>=1) {
									mg.putIfAbsent(p.getUniqueId(), 0);
								}
			                }
			            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								mg.remove(p.getUniqueId());
			                }
			            }, 25); 
	                	mgt.put(p.getUniqueId(), task);
	                	
						p.playSound(p.getLocation(), Sound.ENTITY_FISHING_BOBBER_THROW, 1.0f, 0f);
						p.playSound(p.getLocation(), Sound.ENTITY_SLIME_JUMP, 1.0f, 0f);
	                    Snowball thr = (Snowball) p.launchProjectile(Snowball.class);
	                    thr.setShooter(p);
	                    thr.setItem(new ItemStack(Material.SLIME_BALL));
	                    thr.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(3));
	                    thr.setMetadata("slimeball"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	 
	                    thr.setMetadata("fake"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
						sdcooldown.put(p.getName(), System.currentTimeMillis());
						});
				bd.execute();
				
			}
		}
	}
	


	
	public void MagmaBall(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 15) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && p.isSneaking() &&mg.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
				
            	if(mgt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(mgt.get(p.getUniqueId()));
            		mgt.remove(p.getUniqueId());
            	}
				mg.remove(p.getUniqueId());
				


            	if(gbt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(gbt.get(p.getUniqueId()));
            		gbt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							gb.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						gb.remove(p.getUniqueId());
	                }
	            }, 25); 
            	gbt.put(p.getUniqueId(), task);
				

            	
				p.playSound(p.getLocation(), Sound.ENTITY_MAGMA_CUBE_JUMP, 1.0f, 0f);
                Snowball thr = (Snowball) p.launchProjectile(Snowball.class);
                thr.setShooter(p);
                thr.setItem(new ItemStack(Material.MAGMA_CREAM));
                thr.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(3));
                thr.setMetadata("magmaball"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	 
                thr.setMetadata("fake"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							
			}
		
		}
	}
	

	
	public void GlowingBall(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 15) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && p.isSneaking() &&gb.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
				
            	if(gbt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(gbt.get(p.getUniqueId()));
            		gbt.remove(p.getUniqueId());
            	}
				gb.remove(p.getUniqueId());

				p.playSound(p.getLocation(), Sound.ENTITY_GLOW_SQUID_SQUIRT, 0.6f, 0f);
				p.playSound(p.getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 0.8f, 0f);
                Snowball thr = (Snowball) p.launchProjectile(Snowball.class);
                thr.setShooter(p);
                thr.setItem(new ItemStack(Material.GLOWSTONE));
                thr.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(3));
                thr.setMetadata("glowingball"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	 
                thr.setMetadata("fake"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			}
		
		}
	}

	final private void AcidStorm(Location tl) {
    	ArrayList<Location> ring = new ArrayList<Location>();

    	for(double an = 0; an<Math.PI*4; an +=Math.PI/60) {
    		ring.add(tl.clone().add(tl.getDirection().normalize().rotateAroundY(an).multiply(an*0.56)).add(0, an*0.6, 0));
    	}
    	ring.forEach(l -> {
			tl.getWorld().spawnParticle(Particle.ITEM_SLIME, l, 5, 0.5,0.5,0.5,0);
			tl.getWorld().spawnParticle(Particle.SPORE_BLOSSOM_AIR, l, 3, 0.5,0.5,0.5,0);
    		
    	});
	}

	final private void AcidCloud(Player p) {
		if(cloudh.containsKey(p.getUniqueId())) {
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.GREEN+"[산성구름 비활성화]").create());
		    }
    		else {
            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.GREEN+"[AcidCloud off]").create());
    		}
        		cloudh.remove(p.getUniqueId());
        		if(cloudt.containsKey(p.getUniqueId())) {
        			Bukkit.getServer().getScheduler().cancelTask(cloudt.get(p.getUniqueId()));
        			cloudt.remove(p.getUniqueId());
        		}
		}
		else {
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.GREEN+"[산성구름 활성화]").create());
		    }
    		else {
            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.GREEN+"[AcidCloud on]").create());
    		}
			cloudh.put(p.getUniqueId(), 1);
			int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
                	if(!cloudh.containsKey(p.getUniqueId())) return;
                	final Location fl = p.getLocation(); 
                    AreaEffectCloud cloud = (AreaEffectCloud) p.getWorld().spawnEntity(fl, EntityType.AREA_EFFECT_CLOUD);
					cloud.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    cloud.setRadius((float) (1.5f+Proficiency.getpro(p)));
                    cloud.setSource(p);
                    cloud.setSilent(false);
                    cloud.setColor(Color.LIME);
                    cloud.setRadiusPerTick(0.05f);
					if(acidstorming.containsKey(p.getUniqueId())) {
						Holding.invur(p, 35l);
						AcidStorm(fl.clone().add(0, -0.65, 0));
						p.getWorld().spawnParticle(Particle.OMINOUS_SPAWNING, fl, 200,3,3,3);
	                    p.playSound(fl, Sound.ENTITY_SPLASH_POTION_THROW, 0.08f, 1.6f);
					}
                    if(vx.containsKey(p.getUniqueId())) {
	                    cloud.setRadius((float) (1.5f+Proficiency.getpro(p)) + 3);
	                    cloud.setColor(Color.GREEN);
                    }
                    cloud.setDuration(12);
                    p.playSound(fl, Sound.BLOCK_BREWING_STAND_BREW, 0.1f, 0.2f);
                    for(int i = 0; i<3; i++) {
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                		@Override
		                	public void run() 
			                {	
		                    	for (Entity e : cloud.getNearbyEntities(1.5+Proficiency.getpro(p) + (vx.containsKey(p.getUniqueId()) ? 3 : 0), 1.5+Proficiency.getpro(p)+ (vx.containsKey(p.getUniqueId()) ? 3 : 0), 1.5+Proficiency.getpro(p)+ (vx.containsKey(p.getUniqueId()) ? 3 : 0)))
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
		                				if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
		                				{
		    								LivingEntity le = (LivingEntity)e;
		    								atk3(0.31*(1+csd.AcidCloud.get(p.getUniqueId())*0.04), (vx.containsKey(p.getUniqueId()) ? 0.5 : 0), p, le);
		    								if(acidstorming.containsKey(p.getUniqueId())) {
		    									le.teleport(fl.clone().add(0, -0.6, 0));
		    									Holding.superholding(p, le, 10l);
		    				                	atk1(0.58*(1+csd.Charge.get(p.getUniqueId())*0.07), p, le);
		    								}
		    								if(Proficiency.getpro(p)>=2) {
		    									atkab0(0.2*(1+csd.AcidCloud.get(p.getUniqueId())*0.035), (vx.containsKey(p.getUniqueId()) ? 0.5 : 0)*player_damage.get(p.getName()), p, le);
		    								}
		                				}
		    						}
		    					}
				            }
	                	   }, i*4); 
                    }
                }
			}, 1, 3); 
			cloudt.put(p.getUniqueId(),task);
		}
	}

	
	public void AcidCloud(PlayerInteractEvent d) 
	{
		Player p = d.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
		{
			Action ac = d.getAction();
	        
			
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 15&& csd.AcidCloud.getOrDefault(p.getUniqueId(), 0)>=1) {
				if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
				{
					Double sec = 1d;
					p.setCooldown(CAREFUL,3);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("산성구름")
						.ename("AcidCloud")
						.slot(1)
						.hm(gdcooldown)
						.skillUse(() -> {
		                AcidCloud(p);
						});
				bd.execute();
			}
		}
		}
	}
	


	
	public void Cloud(PlayerItemHeldEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
    	if(cloudh.containsKey(p.getUniqueId())) {
        		cloudh.remove(p.getUniqueId());
        		if(cloudt.containsKey(p.getUniqueId())) {
        			Bukkit.getServer().getScheduler().cancelTask(cloudt.get(p.getUniqueId()));
        		}
		}
    	if(chargingt.containsKey(p.getUniqueId())) {
    		Bukkit.getScheduler().cancelTask(chargingt.get(p.getUniqueId()));
    		chargingt.remove(p.getUniqueId());
    	}
	}
	
	
	public void Cloud(PlayerQuitEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
    	if(cloudh.containsKey(p.getUniqueId())) {
        		cloudh.remove(p.getUniqueId());
        		if(cloudt.containsKey(p.getUniqueId())) {
        			Bukkit.getServer().getScheduler().cancelTask(cloudt.get(p.getUniqueId()));
        		}
		}
    	if(chargingt.containsKey(p.getUniqueId())) {
    		Bukkit.getScheduler().cancelTask(chargingt.get(p.getUniqueId()));
    		chargingt.remove(p.getUniqueId());
    	}
	}

	
	public void Cloud(PlayerDeathEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getEntity();
    	if(cloudh.containsKey(p.getUniqueId())) {
        		cloudh.remove(p.getUniqueId());
        		if(cloudt.containsKey(p.getUniqueId())) {
        			Bukkit.getServer().getScheduler().cancelTask(cloudt.get(p.getUniqueId()));
        		}
		}
    	if(chargingt.containsKey(p.getUniqueId())) {
    		Bukkit.getScheduler().cancelTask(chargingt.get(p.getUniqueId()));
    		chargingt.remove(p.getUniqueId());
    	}
		
	}

		
	public void Napalm(PlayerDeathEvent ev) 
	{
		Player p = ev.getEntity();
		p.getServer().getWorlds().forEach(w -> {
	        w.getEntities().stream().filter(en -> en.hasMetadata("napalm of"+p.getName())).forEach(n -> n.remove());
	        w.getEntities().stream().filter(en -> en.hasMetadata("wp of"+p.getName())).forEach(n -> n.remove());
	        w.getEntities().stream().filter(en -> en.hasMetadata("hg of"+p.getName())).forEach(n -> n.remove());
		});
	}
	
	public void Napalm(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 15&& csd.AcidCloud.getOrDefault(p.getUniqueId(), 0)>=1 && !p.isSneaking()) {
		if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")))
		{
			double sec =8*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	
	        
			
			
			
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("네이팜")
						.ename("Napalm")
						.slot(2)
						.hm(cdcooldown)
						.skillUse(() -> {
						
		                	if(wpt.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(wpt.get(p.getUniqueId()));
		                		wpt.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=2) {
		    							wp.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						wp.remove(p.getUniqueId());
		    	                }
		    	            }, 25); 
		                	wpt.put(p.getUniqueId(), task);
		                	
		                    p.playSound(p.getLocation(), Sound.ENTITY_TNT_PRIMED, 1.0f, 2f);
			            	p.playSound(p.getLocation(), Sound.BLOCK_FIRE_AMBIENT, 1.0f, 2f);
							ItemStack is = new ItemStack(Material.MAGMA_BLOCK);
							Item solid = p.getWorld().dropItem(p.getEyeLocation(), is);
							solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							solid.setMetadata("napalm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							solid.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							
							solid.setPickupDelay(9999);
		                    solid.setVelocity(p.getLocation().getDirection().normalize().multiply(1.6));
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
					                Firework fr = (Firework) p.getWorld().spawnEntity(solid.getLocation(), EntityType.FIREWORK_ROCKET);
				                    fr.setShotAtAngle(true);
				                    fr.setShooter(p);
				                    fr.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						        	ev.setCancelled(true);
				            	
				        			FireworkEffect effect = FireworkEffect.builder()
				                                .with(Type.BURST)
				                                .withColor(Color.RED, Color.ORANGE, Color.BLACK)
				                                .flicker(true)
				                                .trail(true)
				                                .build();
				                    FireworkMeta meta = fr.getFireworkMeta();
				                    meta.setPower(0);
				                    meta.addEffect(effect);
				                    fr.setFireworkMeta(meta);
				                    fr.detonate();
				                    
				                	solid.getWorld().spawnParticle(Particle.FLAME, solid.getLocation(), 300,1,1,1,1);
				                	solid.getWorld().spawnParticle(Particle.LAVA, solid.getLocation(), 30,1,1,1,1);
				                	solid.getWorld().spawnParticle(Particle.BLOCK, solid.getLocation(), 300,6,6,6,1,Material.MAGMA_BLOCK.createBlockData());
				                    p.playSound(solid.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 0);
				                    p.playSound(solid.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 0);
				                    for (Entity e : solid.getNearbyEntities(4.5, 4.5, 4.5))
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
												{
													le.setFireTicks(150);
													for(int n = 0; n<10; n++) {
									                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											                @Override
											                public void run() 
											                {
											                	atk1(0.085*(1+csd.Coagulation.get(p.getUniqueId())*0.065), p, le);
											                }
									                	 }, n*3); 														
													}
												}
										}
									}
				                    Bukkit.getWorlds().forEach(w -> w.getEntities().stream().filter(en -> en.hasMetadata("napalm of"+p.getName())).forEach(n -> n.remove()));
				                }
		                    }, 10); 
						});
				bd.execute();
				}
		}
	
	}
	


	
	public void WP(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 15) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && !p.isSneaking() &&wp.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
				
            	if(wpt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(wpt.get(p.getUniqueId()));
            		wpt.remove(p.getUniqueId());
            	}
				wp.remove(p.getUniqueId());
				


            	if(hgt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(hgt.get(p.getUniqueId()));
            		hgt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							hg.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						hg.remove(p.getUniqueId());
	                }
	            }, 25); 
            	hgt.put(p.getUniqueId(), task);
				

            	p.playSound(p.getLocation(), Sound.ENTITY_TNT_PRIMED, 1.0f, 2f);
            	p.playSound(p.getLocation(), Sound.ITEM_GLOW_INK_SAC_USE, 1.0f, 2f);
            	p.playSound(p.getLocation(), Sound.ITEM_GLOW_INK_SAC_USE, 1.0f, 0f);
				ItemStack is = new ItemStack(Material.WHITE_GLAZED_TERRACOTTA);
				Item solid = p.getWorld().dropItem(p.getEyeLocation(), is);
				solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				solid.setMetadata("wp of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				solid.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				
				solid.setPickupDelay(9999);
                solid.setVelocity(p.getLocation().getDirection().normalize().multiply(1.6));
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
		                Firework fr = (Firework) p.getWorld().spawnEntity(solid.getLocation(), EntityType.FIREWORK_ROCKET);
	                    fr.setShotAtAngle(true);
	                    fr.setShooter(p);
	                    fr.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			        	ev.setCancelled(true);
	            	
	        			FireworkEffect effect = FireworkEffect.builder()
	                                .with(Type.BURST)
	                                .withColor(Color.WHITE, Color.TEAL, Color.SILVER)
	                                .flicker(true)
	                                .trail(true)
	                                .build();
	                    FireworkMeta meta = fr.getFireworkMeta();
	                    meta.setPower(0);
	                    meta.addEffect(effect);
	                    fr.setFireworkMeta(meta);
	                    fr.detonate();
	                    
	                	solid.getWorld().spawnParticle(Particle.GLOW, solid.getLocation(), 300,1,1,1,1);
	                	solid.getWorld().spawnParticle(Particle.LAVA, solid.getLocation(), 30,1,1,1,1);
	                    solid.getWorld().spawnParticle(Particle.ASH, solid.getLocation(), 100,1,1,1,1);
	                    solid.getWorld().spawnParticle(Particle.BLOCK, solid.getLocation(), 300,6,6,6,1,Material.WHITE_GLAZED_TERRACOTTA.createBlockData());
	                    p.playSound(solid.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 0);
	                    p.playSound(solid.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 0);
	                    for (Entity e : solid.getNearbyEntities(4.5, 4.5, 4.5))
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
									{
										le.setFireTicks(150);
										le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 150,1,false,false));
										for(int n = 0; n<15; n++) {
						                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() 
								                {
								                	atk1(0.29*(1+csd.Coagulation.get(p.getUniqueId())*0.06), p, le);
													Holding.holding(p, le, 5l);
								                }
						                	 }, n*3); 														
										}
									}
							}
						}
	                    Bukkit.getWorlds().forEach(w -> w.getEntities().stream().filter(en -> en.hasMetadata("wp of"+p.getName())).forEach(n -> n.remove()));
	                }
        	   }, 10); 
							
			}
		
		}
	}
	

	
	public void Hallucinogen(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 15) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && !p.isSneaking() &&hg.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
				
            	if(hgt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(hgt.get(p.getUniqueId()));
            		hgt.remove(p.getUniqueId());
            	}
				hg.remove(p.getUniqueId());
				
            	p.playSound(p.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 1.0f, 2f);
            	p.playSound(p.getLocation(), Sound.ITEM_GLOW_INK_SAC_USE, 1.0f, 2f);
            	p.playSound(p.getLocation(), Sound.ITEM_GLOW_INK_SAC_USE, 1.0f, 0f);
				ItemStack is = new ItemStack(Material.YELLOW_GLAZED_TERRACOTTA);
				Item solid = p.getWorld().dropItem(p.getEyeLocation(), is);
				solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				solid.setMetadata("hg of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				solid.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				
				solid.setPickupDelay(9999);
                solid.setVelocity(p.getLocation().getDirection().normalize().multiply(1.6));
                

                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
		                Firework fr = (Firework) p.getWorld().spawnEntity(solid.getLocation(), EntityType.FIREWORK_ROCKET);
	                    fr.setShotAtAngle(true);
	                    fr.setShooter(p);
	                    fr.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			        	ev.setCancelled(true);
	            	
	        			FireworkEffect effect = FireworkEffect.builder()
	                                .with(Type.CREEPER)
	                                .withColor(Color.AQUA)
	                                .flicker(true)
	                                .trail(true)
	                                .build();
	                    FireworkMeta meta = fr.getFireworkMeta();
	                    meta.setPower(0);
	                    meta.addEffect(effect);
	                    fr.setFireworkMeta(meta);
	                    fr.detonate();
	                    
	                    solid.setVelocity(solid.getVelocity().zero());
	                    solid.setGravity(false);
	                	solid.getWorld().spawnParticle(Particle.END_ROD, solid.getLocation(), 100,1,1,1,1);
	                	solid.getWorld().spawnParticle(Particle.BLOCK, solid.getLocation(), 300,6,6,6,1,Material.YELLOW_GLAZED_TERRACOTTA.createBlockData());

						for(int n = 0; n<20; n++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                    for (Entity e : solid.getNearbyEntities(5.5, 5.5, 5.5))
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
				    							{
													Holding.holding(p, le, 10l);
				    								le.setFireTicks(150);
				    								le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 150,1,false,false));
				    							}
				    					}
				    				}
				                	solid.getWorld().spawnParticle(Particle.END_ROD, solid.getLocation(), 10,1,1,1,1);
									solid.getWorld().spawnParticle(Particle.BLOCK, solid.getLocation(), 10,3,3,3,1,Material.YELLOW_GLAZED_TERRACOTTA.createBlockData());
				                }
		                	 }, n*3); 														
						}
	                }
        	   }, 10); 
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                    Bukkit.getWorlds().forEach(w -> w.getEntities().stream().filter(en -> en.hasMetadata("hg of"+p.getName())).forEach(n -> n.remove()));
	                }
        	   }, 60); 
							
			}
		
		}
	}
	
	
	public void Charge(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 15&& csd.Charge.getOrDefault(p.getUniqueId(), 0)>=1) {
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
		{
			Action ac = ev.getAction();
			double sec =11*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	
	        
			
			
			if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				

            	if(chargingt.containsKey(p.getUniqueId()) && !p.hasCooldown(Material.APPLE)) {
                    
                	if(acdstrmt.containsKey(p.getUniqueId())) {
                		Bukkit.getScheduler().cancelTask(acdstrmt.get(p.getUniqueId()));
                		acdstrmt.remove(p.getUniqueId());
                	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=1) {
								acdstrm.putIfAbsent(p.getUniqueId(), 0);
							}
		                }
		            }, 6); 

            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							acdstrm.remove(p.getUniqueId());
		                }
		            }, 50); 
                	acdstrmt.put(p.getUniqueId(), task);
                	
            		Bukkit.getScheduler().cancelTask(chargingt.get(p.getUniqueId()));
            		chargingt.remove(p.getUniqueId());
                	Location tl = p.getLocation().clone().add(p.getEyeLocation().getDirection().clone().normalize().multiply(0.78));
					tl.getWorld().spawnParticle(Particle.BLOCK, tl, 200,3,3,3,1,Material.DIRT_PATH.createBlockData());
					tl.getWorld().spawnParticle(Particle.CRIT, tl, 200,3,3,3);
					tl.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, 100,3,3,3);
                	p.playSound(p.getLocation(), Sound.ENTITY_RAVAGER_STUNNED, 1f, 0.2f);
                	p.playSound(p.getLocation(), Sound.ENTITY_RAVAGER_STUNNED, 1f, 1.9f);
                    for (Entity e : tl.getWorld().getNearbyEntities(tl, 4, 4, 4))
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
		                	atk1(0.79*(1+csd.Charge.get(p.getUniqueId())*0.078), p, le);
		                	Holding.holding(p, le, 20l);
                			
    					}
    				}
            	}
            	p.setCooldown(Material.APPLE, 3);
            	
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("돌진")
						.ename("Charge")
						.slot(3)
						.hm(frcooldown)
						.skillUse(() -> {
	                    int q =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	Holding.invur(p, 10l);
			                	p.playSound(p.getLocation(), Sound.ENTITY_RAVAGER_STEP, 0.1f, 0.5f);
			                	p.setVelocity(p.getEyeLocation().getDirection().clone().normalize().multiply(0.78));
			                	
								p.getWorld().spawnParticle(Particle.DUST, p.getLocation(), 20,1,1,1, new Particle.DustOptions(Color.GREEN,2));
								
			                	Location tl = p.getLocation().clone().add(p.getEyeLocation().getDirection().clone().normalize().multiply(0.78));
			                	if(tl.getWorld().getNearbyEntities(tl, 1.2,1.2,1.2).stream().anyMatch(e -> (!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))) {

									tl.getWorld().spawnParticle(Particle.BLOCK, tl, 200,3,3,3,1,Material.DIRT_PATH.createBlockData());
									tl.getWorld().spawnParticle(Particle.CRIT, tl, 200,3,3,3,0.1);
									tl.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, tl, 100,3,3,3);
				                	p.playSound(p.getLocation(), Sound.ENTITY_RAVAGER_STUNNED, 1f, 0.2f);
				                	p.playSound(p.getLocation(), Sound.ENTITY_RAVAGER_STUNNED, 1f, 1.9f);
				                	if(chargingt.containsKey(p.getUniqueId())) {
				                		Bukkit.getScheduler().cancelTask(chargingt.get(p.getUniqueId()));
				                		chargingt.remove(p.getUniqueId());
				                	}
			                        
			                    	if(acdstrmt.containsKey(p.getUniqueId())) {
			                    		Bukkit.getScheduler().cancelTask(acdstrmt.get(p.getUniqueId()));
			                    		acdstrmt.remove(p.getUniqueId());
			                    	}

			    					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			    		                @Override
			    		                public void run() {
			    							if(Proficiency.getpro(p)>=1) {
			    								acdstrm.putIfAbsent(p.getUniqueId(), 0);
			    							}
			    		                }
			    		            }, 6); 

			                		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			    		                @Override
			    		                public void run() {
			    							acdstrm.remove(p.getUniqueId());
			    		                }
			    		            }, 50); 
			                    	acdstrmt.put(p.getUniqueId(), task);
			                    	
				                    for (Entity e : tl.getWorld().getNearbyEntities(tl, 4, 4, 4))
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
						                	atk1(0.79*(1+csd.Charge.get(p.getUniqueId())*0.078), p, le);
						                	Holding.holding(p, le, 20l);
				                			
				    					}
				    				}
			                	}
			                }
	                	 }, 2,2);
	                    chargingt.put(p.getUniqueId(), q);
	                    
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	if(chargingt.containsKey(p.getUniqueId())) {
			                		Bukkit.getScheduler().cancelTask(chargingt.get(p.getUniqueId()));
			                		chargingt.remove(p.getUniqueId());
				                    
				                	if(acdstrmt.containsKey(p.getUniqueId())) {
				                		Bukkit.getScheduler().cancelTask(acdstrmt.get(p.getUniqueId()));
				                		acdstrmt.remove(p.getUniqueId());
				                	}

									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											if(Proficiency.getpro(p)>=1) {
												acdstrm.putIfAbsent(p.getUniqueId(), 0);
											}
						                }
						            }, 6); 

				            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											acdstrm.remove(p.getUniqueId());
						                }
						            }, 50); 
				                	acdstrmt.put(p.getUniqueId(), task);
			                	}
			                }
			            }, 60); 
						});
				bd.execute();
			} 
            
		}}
	}
	

	public void AcidStorm(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") &&acdstrm.containsKey(p.getUniqueId()))
		{
			Action ac = ev.getAction();
			
			if(ClassData.pc.get(p.getUniqueId()) == 15) {
				if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
				{
					ev.setCancelled(true);

	            	if(acdstrmt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(acdstrmt.get(p.getUniqueId()));
	            		acdstrmt.remove(p.getUniqueId());
	            	}
					acdstrm.remove(p.getUniqueId());

	            	if(acdbmbt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(acdbmbt.get(p.getUniqueId()));
	            		acdbmbt.remove(p.getUniqueId());
	            	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=2) {
								acdbmb.putIfAbsent(p.getUniqueId(), 0);
							}
		                }
		            }, 6); 

	        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							acdbmb.remove(p.getUniqueId());
		                }
		            }, 45); 
	        		acdbmbt.put(p.getUniqueId(), task);
					
	        		

                    p.playSound(p.getLocation(), Sound.WEATHER_RAIN_ABOVE, 1, 2);
                    p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 0);
					p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getLocation(), 200,2,2,2);
					p.getWorld().spawnParticle(Particle.ENTITY_EFFECT, p.getLocation(), 200,1,1,1);
					p.getWorld().spawnParticle(Particle.EFFECT, p.getLocation(), 200,1,1,1);
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 40,0,false,false));

	            	if(acidstormingt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(acidstormingt.get(p.getUniqueId()));
	            		acidstormingt.remove(p.getUniqueId());
	            	}
	        		acidstorming.put(p.getUniqueId(), 1);
					int t =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							acidstorming.remove(p.getUniqueId());
		            		acidstormingt.remove(p.getUniqueId());
		                }
		            }, 40); 
					acidstormingt.put(p.getUniqueId(), t);
		            
				}
            
			}
		}
	}

	public void AcidBomb(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") &&acdbmb.containsKey(p.getUniqueId()))
		{
			Action ac = ev.getAction();
			
			if(ClassData.pc.get(p.getUniqueId()) == 15) {
				if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
				{
					ev.setCancelled(true);

	            	if(acdbmbt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(acdbmbt.get(p.getUniqueId()));
	            		acdbmbt.remove(p.getUniqueId());
	            	}
					acdbmb.remove(p.getUniqueId());

                    p.playSound(p.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 1, 2);
					
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {

		                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1.5f);
		                    p.playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 2);
		                    
			                Firework fr = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK_ROCKET);
		                    fr.setShotAtAngle(true);
		                    fr.setShooter(p);
		                    fr.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				        	ev.setCancelled(true);
		            	
		        			FireworkEffect effect = FireworkEffect.builder()
		                                .with(Type.BURST)
		                                .withColor(Color.LIME)
		                                .flicker(true)
		                                .trail(true)
		                                .build();
		                    FireworkMeta meta = fr.getFireworkMeta();
		                    meta.setPower(0);
		                    meta.addEffect(effect);
		                    fr.setFireworkMeta(meta);
		                    fr.detonate();
		                    
							p.getWorld().spawnParticle(Particle.EXPLOSION, p.getLocation(), 100,3,1,3);
							p.getWorld().spawnParticle(Particle.EXPLOSION, p.getLocation().clone().add(0, 2.5, 0), 100,1,5,1);
							p.getWorld().spawnParticle(Particle.EXPLOSION, p.getLocation().clone().add(0, 5, 0), 100,4,1,4);
							p.getWorld().spawnParticle(Particle.SNEEZE, p.getLocation(), 1000,3,1,3,0);
							p.getWorld().spawnParticle(Particle.SNEEZE, p.getLocation().clone().add(0, 2.5, 0), 1000,1,5,1,0);
							p.getWorld().spawnParticle(Particle.SNEEZE, p.getLocation().clone().add(0, 5, 0), 1000,4,1,4,0);
		                    for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 5, 5, 5))
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
									for(int n = 0; n<8; n++) {
					                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
							                	atk1(0.5*(1+csd.Charge.get(p.getUniqueId())*0.05), p, le);
							                	Holding.holding(p, le, 15l);
							                }
					                	 }, n*3); 														
									}
		                			
		    					}
		    				}
		                }
		            }, 20); 
				}
            
			}
		}
	}

	
	public void Extraction(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
		{
			Action ac = ev.getAction();
			double sec =2*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	
	        
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 15 && csd.Extraction.getOrDefault(p.getUniqueId(),0)>=1) {
			if((p.isSneaking()) && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK) && !p.hasCooldown(CAREFUL))
			{
				
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("추출")
						.ename("Extraction")
						.slot(4)
						.hm(stcooldown)
						.skillUse(() -> {
						for (Entity e : p.getNearbyEntities(2, 2, 2))
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
									{
						                le.playEffect(EntityEffect.HURT_BERRY_BUSH);
						                le.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 200,2,false,false));
										p.playSound(le.getLocation(), Sound.ENTITY_COW_MILK, 0.2f, 2f);
										if(Tag.ENTITY_TYPES_SENSITIVE_TO_BANE_OF_ARTHROPODS.isTagged(le.getType())) {
											extracted.put(p, 0);
										}
										else if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType())) {
											extracted.put(p, 1);
										}
										else if(Tag.ENTITY_TYPES_SENSITIVE_TO_IMPALING.isTagged(le.getType())) {
											extracted.put(p, 2);
										}
										else if(le.getType() == EntityType.ENDERMAN) {
											extracted.put(p, 3);
										}
										else {
											extracted.put(p, 4);
										}
										if(Proficiency.getpro(p)>=2) {
											Holding.superholding(p, le, 5l);
										}
									}
							}
						}
	                    p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_ATTACK_TARGET, 1.0f, 2f);
						p.playSound(p.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 1.0f, 1f);
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 200,1,false,true));
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200,1,false,true));
	                	if(Proficiency.getpro(p)<1) {
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 50,0,false,true));
	                	}
	                	if(Proficiency.getpro(p)>=1) {
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200,1,false,true));
	                	}
		                if(!extracted.containsKey(p)) {
			        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("먼저 추출을 해야합니다").create());
						    }
			        		else {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You Should Extract First").create());
			        		}
		                }
		                if (extracted.containsEntry(p, 0)) {
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 200,0,false,true));
		                	if(Proficiency.getpro(p)>=1) {
			                	p.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 200,1,false,true));
		                	}
		                }
		                if (extracted.containsEntry(p, 1)) {
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 200,1,false,true));
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200,1,false,true));
		                	if(Proficiency.getpro(p)>=1) {
			                	p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 200,3,false,true));
		                	}
		                }
		                if (extracted.containsEntry(p, 2)) {
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200,1,false,true));
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 200,1,false,true));
		                	if(Proficiency.getpro(p)>=1) {
			                	p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 200,1,false,true));
		                	}
		                }
		                if (extracted.containsEntry(p, 3)) {
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200,1,false,true));
		                	if(Proficiency.getpro(p)>=1) {
			                	p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 200,1,false,true));
		                	}
		                }
		                if (extracted.containsEntry(p, 4)) {
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200,1,false,true));
		                	if(Proficiency.getpro(p)>=1) {
			                	p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,1,false,false));
		                	}
		                }
						});
				bd.execute();

			} 
            
		}}
	}
	
	
	@SuppressWarnings("deprecation")
	
	public void MolotovCocktail(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 15&& csd.MolotovCocktail.getOrDefault(p.getUniqueId(), 0)>=1 && p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) {
		Action ac = ev.getAction();
		double sec =6*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

        
		
		
		
			if((!p.isSneaking())&& !p.isOnGround() && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK) && !p.hasCooldown(CAREFUL))
			{
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("화염병")
						.ename("MolotovCocktail")
						.slot(5)
						.hm(smcooldown)
						.skillUse(() -> {
						ItemStack is = new ItemStack(Material.SPLASH_POTION);
	                    ItemMeta imeta = is.getItemMeta();
	                    PotionMeta pometa = (PotionMeta)imeta;
	                    pometa.setColor(Color.GREEN);
	                    is.setItemMeta(pometa);
	                    for(double an = -Math.PI/3; an<=Math.PI/3 + (Proficiency.getpro(p)>=2 ? Math.PI/3*4 : 0); an += Math.PI/9) {
		                    ThrownPotion thr = (ThrownPotion) p.launchProjectile(ThrownPotion.class);
		                    thr.setShooter(p);
		                    thr.setBounce(false);
		                    thr.setItem(is);
		                    thr.setVelocity(p.getLocation().getDirection().rotateAroundY(an));
		                    thr.setFireTicks(55);
		                    thr.setMetadata("thrown of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	                    	
	                    }
						});
				bd.execute();
			}
		}
	}

	
	public void Throw(ProjectileHitEvent ev) 
	{
		
		if(ev.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)ev.getEntity().getShooter();
		    
			if(ClassData.pc.get(p.getUniqueId()) != 15) {
				return;
			}

			if(ev.getEntity().hasMetadata("slimeball"+p.getName())) {
				Snowball slime = (Snowball) ev.getEntity();
					for(Entity e: slime.getNearbyEntities(3, 3, 3)) {
 						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
 							LivingEntity le = (LivingEntity)e;
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
 							atk1(0.45*(1+csd.SlimeBall.get(p.getUniqueId())*0.035), p, le);
 							Holding.holding(p, le, 20l);
 							le.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 40, 2, false, false));
 							le.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 40, 2, false, false));
 						}
 					}
 					slime.getWorld().spawnParticle(Particle.ITEM_SLIME, slime.getLocation(), 460, 3,3,3);
 					slime.getWorld().playSound(slime.getLocation(), Sound.ENTITY_SLIME_SQUISH, 1, 0);
 					slime.getWorld().playSound(slime.getLocation(), Sound.ENTITY_SLIME_HURT, 1, 0);
 					slime.getWorld().playSound(slime.getLocation(), Sound.ENTITY_SLIME_DEATH, 1, 0);
 					slime.getWorld().playSound(slime.getLocation(), Sound.BLOCK_SLIME_BLOCK_BREAK, 1, 0);
 					slime.remove();
			}
			

			if(ev.getEntity().hasMetadata("magmaball"+p.getName())) {
				Snowball slime = (Snowball) ev.getEntity();
					for(Entity e: slime.getNearbyEntities(3.5, 3.5, 3.5)) {
 						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
 							LivingEntity le = (LivingEntity)e;
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
 							atk1(0.9*(1+csd.SlimeBall.get(p.getUniqueId())*0.07), p, le);
 							le.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 40, 2, false, false));
 							le.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 40, 2, false, false));
 							le.setFireTicks(40);
 						}
 					}
 					slime.getWorld().spawnParticle(Particle.LAVA, slime.getLocation(), 130, 3,3,3);
 					slime.getWorld().playSound(slime.getLocation(), Sound.ENTITY_MAGMA_CUBE_SQUISH, 1, 0);
 					slime.getWorld().playSound(slime.getLocation(), Sound.ENTITY_MAGMA_CUBE_HURT, 1, 0);
 					slime.getWorld().playSound(slime.getLocation(), Sound.ENTITY_MAGMA_CUBE_DEATH, 1, 0);
 					slime.getWorld().playSound(slime.getLocation(), Sound.ENTITY_MAGMA_CUBE_JUMP, 1, 0);
 					slime.remove();
			}

			if(ev.getEntity().hasMetadata("glowingball"+p.getName())) {
				Snowball slime = (Snowball) ev.getEntity();
					for(Entity e: slime.getNearbyEntities(2, 2, 2)) {
 						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
 							LivingEntity le = (LivingEntity)e;
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
 							atk1(0.8*(1+csd.SlimeBall.get(p.getUniqueId())*0.08), p, le);
 							le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 2, false, false));
 						}
 					}
 					slime.getWorld().spawnParticle(Particle.GLOW, slime.getLocation(), 130, 3,3,3);
 					slime.getWorld().spawnParticle(Particle.GLOW_SQUID_INK, slime.getLocation(), 130, 3,3,3);
 					slime.getWorld().spawnParticle(Particle.WAX_ON, slime.getLocation(), 130, 3,3,3);
 					slime.getWorld().playSound(slime.getLocation(), Sound.ITEM_GLOW_INK_SAC_USE, 1, 0);
 					slime.getWorld().playSound(slime.getLocation(), Sound.ENTITY_GLOW_SQUID_HURT, 1, 0);
 					slime.getWorld().playSound(slime.getLocation(), Sound.ENTITY_GLOW_SQUID_DEATH, 1, 0);
 					slime.getWorld().playSound(slime.getLocation(), Sound.ENTITY_GLOW_SQUID_AMBIENT, 1, 0);
 					slime.remove();
			}
			if(ev.getEntity().hasMetadata("thrown of"+p.getName())) {
				if(ev.getHitEntity()!=null) {
					ev.getHitEntity().getWorld().spawnParticle(Particle.FLAME, ev.getHitEntity().getLocation(), 30,2,2,2,1);
					for (Entity e : p.getWorld().getNearbyEntities(ev.getHitEntity().getLocation(), 3, 3, 3))
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
								{
				                    le.setFireTicks(55);
				                	atk1(0.569*(1+csd.MolotovCocktail.get(p.getUniqueId())*0.0435), p, le);
				                    if(Proficiency.getpro(p)>=1) {
				                    	Holding.holding(p, le, 30l);
				                    }
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
								{
				                    le.setFireTicks(55);
				                	atk1(0.569*(1+csd.MolotovCocktail.get(p.getUniqueId())*0.0435), p, le);
				                    if(Proficiency.getpro(p)>=1) {
				                    	Holding.holding(p, le, 30l);
				                    }
								}
						}
					}
				}
			}
			
		}
	}


	
	@SuppressWarnings("deprecation")
	public void ThrowCancel(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
		
			if(ClassData.pc.get(p.getUniqueId()) == 15 && ((is.getType().name().contains("PICKAXE"))) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
			}
	
    }
	
	
	public void ULT(PlayerItemHeldEvent ev)
    {
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		ItemStack is = p.getInventory().getItemInMainHand();
			if(ClassData.pc.get(p.getUniqueId()) == 15 && ev.getNewSlot()==3 && ((is.getType().name().contains("PICKAXE"))) && p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
				p.setCooldown(CAREFUL,3);
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(45/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
						.kname("신경독")
						.ename("VX")
						.slot(6)
						.hm(sultcooldown)
						.skillUse(() -> {
						if(vxt.containsKey(p.getUniqueId())) {
	                    	Bukkit.getScheduler().cancelTask(vxt.get(p.getUniqueId()));
	                    }
	                    Holding.invur(p, 100l);
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 200,3,false,true));
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200,3,false,true));

             			p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 0f, 1f);
             			p.playSound(p.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 0f, 1f);
             			p.playSound(p.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 0f, 1f);
             			
	                    vx.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
	                    vx.putIfAbsent(p.getUniqueId(), 0);

		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.sendTitle(ChatColor.DARK_GREEN + "신경독 활성화", ChatColor.DARK_GREEN +"산성구름이 강화됩니다", 20, 30, 20);
		        		}
		        		else {
							p.sendTitle(ChatColor.DARK_GREEN + "VX Activate", ChatColor.DARK_GREEN +"Acid Cloud Enhanced", 20, 30, 20);
		        		}
	                    int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {
			                    	vx.remove(p.getUniqueId());
						            }
			                	   }, 200); 
	                    vxt.put(p.getUniqueId(), task);
						});
				bd.execute();
				
			}
    }
	
	

	
	public void ULT2(PlayerItemHeldEvent ev)
	{
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}

		ItemStack is = p.getInventory().getItemInMainHand();
			if(ClassData.pc.get(p.getUniqueId()) == 15 && ev.getNewSlot()==4 && ((is.getType().name().contains("PICKAXE"))) && p.isSneaking()&& Proficiency.getpro(p) >=2)
			{
				p.setCooldown(CAREFUL,3);
				ev.setCancelled(true);
				p.setCooldown(CAREFUL,3);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(80*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
						.kname("아마겟돈")
						.ename("Armageddon")
						.slot(7)
						.hm(sult2cooldown)
						.skillUse(() -> {

	                	final Location tl =gettargetblock(p,3).clone();
	                    TNTPrimed tp = tl.getWorld().spawn(tl, TNTPrimed.class);
	                    tp.setCustomName("NUKE");
	                    tp.setYield(0);
	                    tp.setFuseTicks(80);
	                    tp.setFireTicks(80);
	                    tp.setGlowing(true);
	                    tp.setInvulnerable(true);
	                    tp.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    tp.setIsIncendiary(false);
	                    tp.setSource(p);
	                    p.playSound(tp.getLocation(), Sound.ENTITY_TNT_PRIMED, 1f, 0);
						for(int n = 0; n<25; n++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                    p.playSound(tp.getLocation(), Sound.BLOCK_CAVE_VINES_STEP, 0.15f, 0);
				                    p.playSound(tp.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 0.15f, 0);
									p.getWorld().spawnParticle(Particle.DUST, tp.getLocation(), 100,2,2,2, Material.YELLOW_CONCRETE_POWDER.createBlockData());
									p.getWorld().spawnParticle(Particle.BLOCK, tp.getLocation(), 100,3,3,3, Material.YELLOW_GLAZED_TERRACOTTA.createBlockData());
									p.getWorld().spawnParticle(Particle.BLOCK, tp.getLocation(), 100,4,4,4, Material.RAW_GOLD_BLOCK.createBlockData());
									p.getWorld().spawnParticle(Particle.BLOCK, tp.getLocation(), 100,3,3,3, Material.BLACK_GLAZED_TERRACOTTA.createBlockData());
									p.getWorld().spawnParticle(Particle.BLOCK, tp.getLocation(), 100,2,2,2, Material.OBSIDIAN.createBlockData());
									p.getWorld().spawnParticle(Particle.BLOCK, tp.getLocation(), 100,2,2,2, Material.RED_GLAZED_TERRACOTTA.createBlockData());
				                }
		                	 }, n*4); 														
						}
	                    Holding.invur(p, 100l);
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {
		             			p.playSound(tp.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 0.5f, 0.5f);
			                    p.playSound(tp.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 0);
			                    p.playSound(tp.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 0);
			                    p.playSound(tp.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 0);
			                    p.playSound(tp.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 0);
			                    tp.getWorld().spawnParticle(Particle.SMOKE, tp.getLocation(), 2000, 15,15,15,0);
			                    tp.getWorld().spawnParticle(Particle.FLAME, tp.getLocation(), 2000, 15,15,15,0);
			                    tp.getWorld().spawnParticle(Particle.GLOW, tp.getLocation(), 2000, 15,15,15,0);
			                    tp.getWorld().spawnParticle(Particle.LAVA, tp.getLocation(), 100, 15,15,15,0);
			                    tp.getWorld().spawnParticle(Particle.EXPLOSION, tp.getLocation(), 1000,12,1,12,0);
			                    tp.getWorld().spawnParticle(Particle.EXPLOSION, tp.getLocation(), 1000, 3,10,3,0);
			                    tp.getWorld().spawnParticle(Particle.EXPLOSION, tp.getLocation().clone().add(0, 10, 0), 1000, 15,1,15,0);
			                    tp.getWorld().spawnParticle(Particle.CLOUD, tp.getLocation(), 2000, 12,1,12,0);
			                    tp.getWorld().spawnParticle(Particle.CLOUD, tp.getLocation(), 2000, 2,10,2,0);
			                    tp.getWorld().spawnParticle(Particle.CLOUD, tp.getLocation().clone().add(0, 10, 0), 2000, 15,1,15,0);
			             			for(Entity e: tp.getWorld().getNearbyEntities(tp.getLocation(),20,15,20)) {
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
		             						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
		             							LivingEntity le = (LivingEntity)e;
		    				                	atk1(19.0, p, le);
		             							le.setFireTicks(60);
		             		                    Holding.holding(p, le, 60l);
		             		                    le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,200,1,false,false));
		             							
												for(int n = 0; n<10; n++) {
								                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										                @Override
										                public void run() 
										                {
					    				                	atk1(0.6, p, le);
										                }
								                	 }, n*6); 														
												}
		             						}
		             					}
				             			
						            }
	                    }, 80); 
						});
				bd.execute();
				
			}
    }

	
	public void Nuke(ExplosionPrimeEvent d) 
	{
		Entity e = d.getEntity();
		
		
		if(e instanceof TNTPrimed) {
			if(e.hasMetadata("fake")) {
				d.setRadius(0);
				d.setCancelled(true);
			}
		}
	}
	
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();
        

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 15)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") )
			{
					e.setCancelled(true);
			}
		}
		
	}
	
	
	public void Poisonous(EntityDamageByEntityEvent d) 
	{		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) {
			LivingEntity le = (LivingEntity)d.getEntity();
	        
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 15 && !le.hasMetadata("fake")&& !(le.hasMetadata("portal"))) {
					d.setDamage(d.getDamage()*(1+csd.Poisonous.get(p.getUniqueId())*0.0336));
				}
			}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof EnderDragon) 
		{
			Arrow ar = (Arrow)d.getDamager();
			EnderDragon le = (EnderDragon)d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
		        
				

				
				
				if(ClassData.pc.get(p.getUniqueId()) == 15 && !le.hasMetadata("fake")&& !(le.hasMetadata("portal"))) {
						if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) {

							if(ClassData.pc.get(p.getUniqueId()) == 15 && !le.hasMetadata("fake")) {
								d.setDamage(d.getDamage()*(1+csd.Poisonous.get(p.getUniqueId())*0.0336));
								
							}
						}
					}
			}
		}
	}

	
	public void Poisonous(EntityDamageEvent d) 
	{		
        if (!(d.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) d.getEntity();
        

        if(ClassData.pc.get(p.getUniqueId()) == 15) {
    		if(d.getCause().equals(DamageCause.POISON)) 
    		{
    			d.setCancelled(true);
    		}
    		d.setDamage(d.getDamage()* (0.8 - (p.getActivePotionEffects().size() * 0.05))  );
		}
	}
	/*
	
	public void Damagegetter(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();
		    
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 15) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
				{
						player_damage.put(p.getName(), p.getAttribute(Attribute.ATTACK_DAMAGE).getValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				else {
					player_damage.put(p.getName(), 0d);
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
        

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 15) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
			{
					player_damage.put(p.getName(), p.getAttribute(Attribute.ATTACK_DAMAGE).getValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			else {
				player_damage.put(p.getName(), 0d);
			}
			
		}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
			Arrow ar = (Arrow)d.getDamager();
			Entity e = d.getEntity();
	
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
			    
				
				
				if(ClassData.pc.get(p.getUniqueId()) == 15) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
					{
							player_damage.put(p.getName(), p.getAttribute(Attribute.ATTACK_DAMAGE).getValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
					else {
						player_damage.put(p.getName(), 0d);
					}
					
				}
			}
		}
	}*/
}



