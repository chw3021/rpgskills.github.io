package io.github.chw3021.classes.witchdoctor;



import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.EvokerFangs;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Vex;
import org.bukkit.entity.Zoglin;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.google.common.collect.HashMultimap;

import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.CommonEvents;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.party.Party;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class Wdcskills extends Pak implements Serializable, Listener {
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 2112330324524706135L;
	private HashMap<String, Long> rscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> prcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> jmcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> thcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> pncooldown = new HashMap<String, Long>();
	private HashMap<String, Long> eccooldown = new HashMap<String, Long>();
	private HashMap<String, Long> lncooldown = new HashMap<String, Long>();
	private HashMap<String, Long> aultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> ault2cooldown = new HashMap<String, Long>();

	private HashMap<UUID, Integer> hart = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> phtsw = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> phtswt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> sacrf = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> sacrft = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> zglc = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> zglct = new HashMap<UUID, Integer>();
	private HashMap<UUID, Zoglin> fangsr = new HashMap<UUID, Zoglin>();
	private HashMap<UUID, Integer> fangsrt = new HashMap<UUID, Integer>();
	
	private HashMap<UUID, Integer> forbhx = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> forbhxt = new HashMap<UUID, Integer>();
	
	private HashMap<UUID, Integer> vgfspr = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> vgfsprt = new HashMap<UUID, Integer>();

	public static HashMultimap<UUID, Mob> zombies = HashMultimap.create();

	private static HashMap<UUID, GameMode> astrgm = new HashMap<UUID, GameMode>();
	private static HashMap<UUID, Integer> astrt = new HashMap<UUID, Integer>();
	
	
	
	private static HashMap<UUID, Integer> bosou = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> bosout = new HashMap<UUID, Integer>();

	
	static public HashMap<UUID, Integer> Baron = new HashMap<UUID, Integer>();
	static public HashMap<UUID, Integer> Baront = new HashMap<UUID, Integer>();

	static private HashMap<UUID, UUID> deathp = new HashMap<UUID, UUID>();
	static private HashMap<UUID, Integer> deathpt = new HashMap<UUID, Integer>();
	static private HashMap<UUID, HashMap<UUID, Double>> deathdamager = new HashMap<UUID, HashMap<UUID, Double>>();
	
	private static HashMap<UUID, UUID> Hex = new HashMap<UUID, UUID>();
	private static HashMap<UUID, Integer> Hext = new HashMap<UUID, Integer>();
	
	static private HashMap<LivingEntity, Player> chickdam = new HashMap<LivingEntity, Player>();
	static private HashMap<LivingEntity, LivingEntity> chickenget = new HashMap<LivingEntity, LivingEntity>();
	static private HashMap<UUID, Location> chickenloc = new HashMap<UUID, Location>();
	
	
	static public HashMap<Player, Integer> res = new HashMap<Player, Integer>();
	Holding hold = Holding.getInstance();


	

	private static final Wdcskills instance = new Wdcskills ();
	public static Wdcskills getInstance(){
		return instance;		
	}
	
	private String path = new File("").getAbsolutePath();
	private WdcSkillsData wsd;


		
	public void er(PluginEnableEvent ev) 
	{
		WdcSkillsData w = new WdcSkillsData(WdcSkillsData.loadData(path +"/plugins/RPGskills/WdcSkillsData.data"));
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
				if(bosout.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(bosout.get(p.getUniqueId()));
					bosout.remove(p.getUniqueId());
				}
            	bosou.remove(p.getUniqueId());

				if(Baront.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(Baront.get(p.getUniqueId()));
					Baront.remove(p.getUniqueId());
				}
            	Baron.remove(p.getUniqueId());
			}
		}
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Wdcskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				WdcSkillsData w = new WdcSkillsData(WdcSkillsData.loadData(path +"/plugins/RPGskills/WdcSkillsData.data"));
				wsd = w;
			}
		}
	}

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		WdcSkillsData w = new WdcSkillsData(WdcSkillsData.loadData(path +"/plugins/RPGskills/WdcSkillsData.data"));
		wsd = w;
		
	}
	


	final private ArrayList<Location> Fangs(Player p, Location il) {
		ArrayList<Location> las = new ArrayList<>();
		for(int i = 0; i<5; i++) {
			Location fl = il.clone().add(il.clone().getDirection().normalize().multiply(i));
			las.add(fl);
		}
		return las;
	}

		
	
	
	public void Fangs(PlayerSwapHandItemsEvent ev) 
	{

		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 14 && wsd.Fangs.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("HOE") && p.getInventory().getItemInOffHand().getType().name().contains("TOTEM") && p.isSneaking())
			{
				
				double sec =3*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				ev.setCancelled(true);


				skilluse(()->{

                	if(zglct.containsKey(p.getUniqueId())) {
                		Bukkit.getScheduler().cancelTask(zglct.get(p.getUniqueId()));
                		zglct.remove(p.getUniqueId());
                	}

    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						if(Proficiency.getpro(p)>=1) {
    							zglc.putIfAbsent(p.getUniqueId(), 0);
    						}
    	                }
    	            }, 6); 

            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						zglc.remove(p.getUniqueId());
    	                }
    	            }, 35); 
                	zglct.put(p.getUniqueId(), task);
                    
                    
                    AtomicInteger j = new AtomicInteger();

                    ArrayList<Location> las = Fangs(p,p.getLocation().clone());
                    las.forEach(l -> {
                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	EvokerFangs ef = (EvokerFangs)p.getWorld().spawnEntity(l, EntityType.EVOKER_FANGS);
			                    ef.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			                    ef.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			                    ef.setMetadata("wdcfang", new FixedMetadataValue(RMain.getInstance(), true));
			                	ef.setOwner(p);
			                	ef.setInvulnerable(true);
			                }
			            }, j.getAndIncrement());   
                    });
				}, p, sec, "독사의송곳니", "Fangs",rscooldown);
			}
		}
	}

	
	public void Fangs(EntityDamageByEntityEvent d) 
	{
        

		if(d.getDamager() instanceof EvokerFangs && d.getEntity() instanceof LivingEntity) 
		{
			EvokerFangs f = (EvokerFangs)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
	
			if(f.hasMetadata("fakefang")) {
				d.setCancelled(true);
				return;
			}
			
			
			if(f.getOwner() instanceof Player) {
				Player p = (Player) f.getOwner();
				if(ClassData.pc.get(p.getUniqueId()) == 14) {
						d.setCancelled(true);
						if(p.getInventory().getItemInMainHand().getType().name().contains("HOE")||p.getInventory().getItemInMainHand().getType().name().contains("TOTEM"))
						{
							if(p==le) {
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
							
							atk0(0.4, wsd.Fangs.get(p.getUniqueId())*0.3, p, le,11);
						}
				}
			}
		}
	}

	final private Location ZoglinCharge(Player p, Zoglin h) 
	{
		if(h.isValid()) {
	    	h.remove();
	        p.playSound(h.getLocation(), Sound.ENTITY_ZOGLIN_ATTACK, 1.0f, 1f);
	        p.playSound(h.getLocation(), Sound.ENTITY_ZOMBIE_DESTROY_EGG, 1.0f, 0f);
			h.getWorld().spawnParticle(Particle.CRIT, h.getLocation(), 200, 2, 2, 2);
			h.getWorld().spawnParticle(Particle.SPELL_INSTANT, h.getLocation(), 200, 2, 2, 2);
			h.getWorld().spawnParticle(Particle.SOUL, h.getLocation(), 200, 2, 2, 2);
			h.getWorld().spawnParticle(Particle.ASH, h.getLocation(), 200, 2, 2, 2);
	    	for(Entity e: h.getNearbyEntities(3, 3, 3)) {
	    		if(e instanceof LivingEntity && p!=e && !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
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
	        		atk0(0.7, wsd.Fangs.get(p.getUniqueId())*0.8, p, le,11);
	        		Holding.holding(p, le, 20l);
	    		}
	    	}
		}
		return h.getLocation();
	}
	
	public void ZoglinCharge(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 14 &&zglc.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("HOE") && p.getInventory().getItemInOffHand().getType().name().contains("TOTEM") && p.isSneaking())
			{
				ev.setCancelled(true);
            	
                Zoglin h = (Zoglin)p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.ZOGLIN);
        		h.setAdult();
        		h.setAI(false);
        		h.setInvulnerable(true);
                h.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                h.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                p.playSound(p.getLocation(), Sound.ENTITY_ZOGLIN_ANGRY, 1.0f, 1f);
                
                
				for(int i = 0; i <10; i++) {
	        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	if(h.getLocation().add(p.getEyeLocation().clone().getDirection().normalize().multiply(0.8)).getBlock().isPassable()) {
				        		h.teleport(h.getLocation().add(p.getEyeLocation().clone().getDirection().normalize().multiply(0.8)));
				    			h.getWorld().spawnParticle(Particle.SOUL, h.getLocation(), 10, 2, 2, 2);
				    			h.getWorld().spawnParticle(Particle.ASH, h.getLocation(), 10, 2, 2, 2);
		                	}
		                }
		            }, i*2);
            	}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
                		ZoglinCharge(p,h);
	                }
				}, 20);
				
            	if(zglct.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(zglct.get(p.getUniqueId()));
            		zglct.remove(p.getUniqueId());
            	}
				zglc.remove(p.getUniqueId());


            	if(fangsrt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(fangsrt.get(p.getUniqueId()));
            		fangsrt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=1) {
							fangsr.putIfAbsent(p.getUniqueId(), h);
						}
	                }
	            }, 6);

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						fangsr.remove(p.getUniqueId());
	                }
	            }, 35);
            	fangsrt.put(p.getUniqueId(), task);
			}
		}
	}

	
	public void FangsRush(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 14 &&fangsr.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("HOE") && p.getInventory().getItemInOffHand().getType().name().contains("TOTEM") && p.isSneaking())
			{
				ev.setCancelled(true);

        		Location tl = ZoglinCharge(p,fangsr.get(p.getUniqueId()));
        		

                ArrayList<Location> las =new ArrayList<>();
                AtomicInteger j = new AtomicInteger();
                
                for(int i =0; i<10; i++) {
                	Random ran = new Random();
                	double rd = ran.nextDouble()*2 *(ran.nextBoolean() ? 1:-1);
                	
                	las.add(tl.clone().add(rd, 0, rd));
                }

                p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, 1.0f, 2f);
                
                las.forEach(l -> {
            		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		        	    	for(Entity e: tl.getWorld().getNearbyEntities(tl,3, 3, 3)) {
		        	    		if(e instanceof LivingEntity && p!=e && !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
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
		        	        		atk0(0.7, wsd.Fangs.get(p.getUniqueId())*0.8, p, le,11);
		        	        		Holding.holding(p, le, 5l);
		        	    		}
		        	    	}
		                	EvokerFangs ef = (EvokerFangs)p.getWorld().spawnEntity(l, EntityType.EVOKER_FANGS);
		                    ef.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		                    ef.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		                    ef.setMetadata("fakefang", new FixedMetadataValue(RMain.getInstance(), true));
		                	ef.setOwner(p);
		                	ef.setInvulnerable(true);
		                	
		                	ef.getWorld().spawnParticle(Particle.SOUL, ef.getLocation(), 100,0.1,2.5,0.1);
		                	ef.getWorld().spawnParticle(Particle.REVERSE_PORTAL, ef.getLocation(), 100,0.1,2.5,0.1);
		                }
		            }, j.getAndIncrement()*3);   
                });

				if(zombies.containsKey(p.getUniqueId())) {
					zombies.get(p.getUniqueId()).forEach(v -> {
	                	v.teleport(tl);
						
					});
				}
        		
	        	if(fangsrt.containsKey(p.getUniqueId())) {
	        		Bukkit.getScheduler().cancelTask(fangsrt.get(p.getUniqueId()));
	        		fangsrt.remove(p.getUniqueId());
	        	}
	        	
				fangsr.remove(p.getUniqueId());
				
			}
		}
	}
	
	
	final private void Bosou(Player p, Location il) {


		for (Entity e : il.getWorld().getNearbyEntities(il,5, 5, 5))
		{
			if (e instanceof Player) 
			{

				if(bosout.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(bosout.get(p.getUniqueId()));
					bosout.remove(p.getUniqueId());
				}
				
				if(bosou.getOrDefault(p.getName(),0) <= wsd.Bosou.get(p.getUniqueId())) {
					bosou.put(p.getUniqueId(), wsd.Bosou.get(p.getUniqueId()));
				}
				
				
				int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		            @Override
		            public void run() 
		            {
		            	bosou.remove(p.getUniqueId());
		            }
				}, 10); 
				
				bosout.put(p.getUniqueId(), task);
				p.getWorld().spawnParticle(Particle.SPELL, il, 40, 4, 2, 4,0);
				p.getWorld().spawnParticle(Particle.LANDING_OBSIDIAN_TEAR, il, 500, 4, 0.2, 4);
				p.getWorld().spawnParticle(Particle.CRIT_MAGIC, il, 40, 4, 2, 4,0);
				
				Player p1 = (Player) e;
				if(Party.hasParty(p) && Party.hasParty(p1))	{
				if(Party.getParty(p).equals(Party.getParty(p1)))
					{
						if(bosout.containsKey(p1.getUniqueId())) {
							Bukkit.getScheduler().cancelTask(bosout.get(p1.getUniqueId()));
							bosout.remove(p1.getUniqueId());
						}
						
						if(bosou.getOrDefault(p1.getName(),0) <= wsd.Bosou.get(p.getUniqueId())) {
							bosou.put(p1.getUniqueId(), wsd.Bosou.get(p.getUniqueId()));
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	bosou.remove(p1.getUniqueId());
			                }
			    	   }, 10);
					}
				}
			}
		}
	}

	
	public void Bosou(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 14 && wsd.Bosou.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("TOTEM") && p.getInventory().getItemInOffHand().getType().name().contains("HOE") && p.isSneaking())
			{
				
		double sec =9*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				ev.setCancelled(true);
				{
					if(prcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (prcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("수호의로아:보수 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
		                	}
		                	else {
			                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Bosou").create());
		                	}
		                }
		                else // if timer is done
		                {
		                    prcooldown.remove(p.getName()); // removing player from HashMap



		                	if(forbhxt.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(forbhxt.get(p.getUniqueId()));
		                		forbhxt.remove(p.getUniqueId());
		                	}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									if(Proficiency.getpro(p)>=1) {
										forbhx.putIfAbsent(p.getUniqueId(), 0);
									}
				                }
				            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									forbhx.remove(p.getUniqueId());
				                }
				            }, 40); 
		                	forbhxt.put(p.getUniqueId(), task);
		                	
		                    
		                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
		                    p.playSound(p.getLocation(), Sound.ENTITY_HOGLIN_AMBIENT, 1.0f, 0f);
			        		p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 400, 4, 2, 4);
			        		p.getWorld().spawnParticle(Particle.ASH, l, 400, 4, 2, 4);
			        		Hoglin h = (Hoglin)p.getWorld().spawnEntity(l, EntityType.HOGLIN);
			        		h.setAdult();
			        		h.setAgeLock(true);
			        		h.setImmuneToZombification(true);
			        		h.setAI(false);
			        		h.setInvulnerable(true);
		                    h.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		                    h.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							for(int i = 0; i <3.6*wsd.Bosou.get(p.getUniqueId()); i++) {
			                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
							        		Bosou(p,h.getLocation().clone());
						                }
						            }, i*5); 
		                	}
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	h.remove();
				                }
							}, (long) (3.6*wsd.Bosou.get(p.getUniqueId())*5));
							prcooldown.put(p.getName(), System.currentTimeMillis());
						}
		            }
		            else // if cooldown doesn't have players name in it
		            {

	                	if(forbhxt.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(forbhxt.get(p.getUniqueId()));
	                		forbhxt.remove(p.getUniqueId());
	                	}

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								if(Proficiency.getpro(p)>=1) {
									forbhx.putIfAbsent(p.getUniqueId(), 0);
								}
			                }
			            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								forbhx.remove(p.getUniqueId());
			                }
			            }, 40); 
	                	forbhxt.put(p.getUniqueId(), task);
	                	
	                	
	                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
	                    p.playSound(p.getLocation(), Sound.ENTITY_HOGLIN_AMBIENT, 1.0f, 0f);
		        		p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 400, 4, 2, 4);
		        		p.getWorld().spawnParticle(Particle.ASH, l, 400, 4, 2, 4);
		        		Hoglin h = (Hoglin)p.getWorld().spawnEntity(l, EntityType.HOGLIN);
		        		h.setAdult();
		        		h.setAgeLock(true);
		        		h.setImmuneToZombification(true);
		        		h.setAI(false);
		        		h.setInvulnerable(true);
	                    h.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    h.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						for(int i = 0; i <3.6*wsd.Bosou.get(p.getUniqueId()); i++) {
		                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
						        		Bosou(p,h.getLocation().clone());
					                }
					            }, i*5); 
	                	}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	h.remove();
			                }
						}, (long) (3.6*wsd.Bosou.get(p.getUniqueId())*5));
						prcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
					}
				}
			}
		}
	}


	
	public void ForbiddenHex(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 14&& forbhx.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("TOTEM") && p.getInventory().getItemInOffHand().getType().name().contains("HOE") && p.isSneaking())
			{
				ev.setCancelled(true);

            	if(forbhxt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(forbhxt.get(p.getUniqueId()));
            		forbhxt.remove(p.getUniqueId());
            	}
				forbhx.remove(p.getUniqueId());
				p.playSound(p.getLocation(), Sound.ENTITY_VEX_CHARGE, 0.8f, 1.5f);
        		p.getWorld().spawnParticle(Particle.SPELL_INSTANT, p.getLocation(), 400, 1, 1, 1);
				p.setHealth(p.getHealth()*0.8);
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 60, false, false));
				Holding.invur(p, (long) (9*wsd.Bosou.get(p.getUniqueId())));
				
				if(Party.hasParty(p)) {
					Party.getMembers(Party.getParty(p)).forEach(pu -> {
						Player p1 = Bukkit.getPlayer(pu);
						p1.playSound(p1.getLocation(), Sound.ENTITY_VEX_CHARGE, 0.8f, 1.5f);
		        		p.getWorld().spawnParticle(Particle.SPELL_INSTANT, p1.getLocation(), 400, 1, 1, 1);
						p1.setHealth(p.getHealth()*0.8);
						p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 60, false, false));
						Holding.invur(p1, (long) (9*wsd.Bosou.get(p.getUniqueId())));
					});
				}
			}
		}
	}
	
	
	final private void Harvest(Player p, LivingEntity le) {

		if(hart.containsKey(le.getUniqueId())) {
			Bukkit.getScheduler().cancelTask(hart.get(le.getUniqueId()));
			hart.remove(le.getUniqueId());
			p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1, false, false));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1, 1, false, false));
		}
		Location sc = le.getLocation().clone().add(0, 0.1, 0);
		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
            	Vector spv = p.getLocation().clone().toVector().subtract(sc.clone().toVector());
        		sc.add(spv.clone().normalize().multiply(0.25));
            	sc.getWorld().spawnParticle(Particle.SOUL, sc, 1,0.2,0.2,0.2,0);
            	sc.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, sc, 3,0.25,0.25,0.25,0);
            	if(!sc.getWorld().equals(p.getWorld())) {
            		if(hart.containsKey(le.getUniqueId())) {
            			Bukkit.getScheduler().cancelTask(hart.get(le.getUniqueId()));
            			hart.remove(le.getUniqueId());
            		}
            	}
            	Double spd = p.getLocation().clone().distance(sc.clone());
            	if(spd<0.1 || spd>50 ||le.isDead() || p.isDead() || !le.isValid() || !p.isValid()) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1, false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1, 1, false, false));
                	p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 0.1f, 2);
            		if(hart.containsKey(le.getUniqueId())) {
            			Bukkit.getScheduler().cancelTask(hart.get(le.getUniqueId()));
            			hart.remove(le.getUniqueId());
            		}
            	}
            }
		}, 0,1/20);
		hart.put(le.getUniqueId(), task);
	}
	
	
	public void Harvest(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 14 && wsd.Harvest.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("HOE") && p.getInventory().getItemInOffHand().getType().name().contains("TOTEM")  &&!p.isSneaking()) 
			{
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
					double sec =3*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
					ev.setCancelled(true);
					
					if(jmcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (jmcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("수확 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
		                	}
		                	else {
			                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Harvest").create());
		                	}
		                }
		                else // if timer is done
		                {
		                    jmcooldown.remove(p.getName()); // removing player from HashMap
							
		                	if(vgfsprt.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(vgfsprt.get(p.getUniqueId()));
		                		vgfsprt.remove(p.getUniqueId());
		                	}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									if(Proficiency.getpro(p)>=1) {
										vgfspr.putIfAbsent(p.getUniqueId(), 0);
									}
				                }
				            }, 4); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									vgfspr.remove(p.getUniqueId());
				                }
				            }, 40);
		                	vgfsprt.put(p.getUniqueId(), task);
		                	
    	                	p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 0.6f, 0.5f);
		                	p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p.getLocation(), 10,2,2,2,0);
							if(Proficiency.getpro(p)>=2) {
			                	p.getWorld().spawnParticle(Particle.DRAGON_BREATH, p.getLocation(), 200,2,2,2,1);
			                	p.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, p.getLocation(), 200,2,2,2,1);
							}
		                    for(Entity e: p.getNearbyEntities(4, 4, 4)) {
		                    	if(e instanceof LivingEntity && !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
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
		                    		Harvest(p,le);
		                    		atk0(0.5, wsd.Harvest.get(p.getUniqueId())*0.56, p, le,14);
									if(Proficiency.getpro(p)>=2) {
										Holding.holding(p, le, 25l);
									}
		                    		
		                    		if(Hext.containsKey(le.getUniqueId())) {
		                    			Bukkit.getScheduler().cancelTask(Hext.get(le.getUniqueId()));
		                    		}
		                    		Hex.put(le.getUniqueId(), p.getUniqueId());
									int ht = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                	Hex.remove(le.getUniqueId());
						                }
									}, 100);
									Hext.put(le.getUniqueId(), ht);
		                    	}
		                    }
							jmcooldown.put(p.getName(), System.currentTimeMillis());  
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {

						
	                	if(vgfsprt.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(vgfsprt.get(p.getUniqueId()));
	                		vgfsprt.remove(p.getUniqueId());
	                	}

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								if(Proficiency.getpro(p)>=1) {
									vgfspr.putIfAbsent(p.getUniqueId(), 0);
								}
			                }
			            }, 4); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								vgfspr.remove(p.getUniqueId());
			                }
			            }, 40);
	                	vgfsprt.put(p.getUniqueId(), task);

	                	p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 0.6f, 0.5f);
	                	p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p.getLocation(), 10,2,2,2,0);
						if(Proficiency.getpro(p)>=2) {
		                	p.getWorld().spawnParticle(Particle.DRAGON_BREATH, p.getLocation(), 200,2,2,2,1);
		                	p.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, p.getLocation(), 200,2,2,2,1);
						}
	                    for(Entity e: p.getNearbyEntities(4, 4, 4)) {
	                    	if(e instanceof LivingEntity && !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
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
	                    		Harvest(p,le);
	                    		atk0(0.5, wsd.Harvest.get(p.getUniqueId())*0.56, p, le,14);
								if(Proficiency.getpro(p)>=2) {
									Holding.holding(p, le, 25l);
								}
	                    		
	                    		if(Hext.containsKey(le.getUniqueId())) {
	                    			Bukkit.getScheduler().cancelTask(Hext.get(le.getUniqueId()));
	                    		}
	                    		Hex.put(le.getUniqueId(), p.getUniqueId());
								int ht = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	Hex.remove(le.getUniqueId());
					                }
								}, 100);
								Hext.put(le.getUniqueId(), ht);
	                    	}
	                    }
						jmcooldown.put(p.getName(), System.currentTimeMillis());  
					}
				} 
				}
		}
	}
	

	
	public void VengefulSpirit(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 14 && vgfspr.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("HOE") && p.getInventory().getItemInOffHand().getType().name().contains("TOTEM")  &&!p.isSneaking()) 
			{
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					
	            	if(vgfsprt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(vgfsprt.get(p.getUniqueId()));
	            		vgfsprt.remove(p.getUniqueId());
	            	}
					vgfspr.remove(p.getUniqueId());

            		ArrayList<Location> souls = new ArrayList<Location>();
            		HashMap<Location,LivingEntity> les = new HashMap<>();
            		AtomicInteger ai = new AtomicInteger();
                	p.playSound(p.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 1f, 0);
                	p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 1f, 2);
                	p.getWorld().spawnParticle(Particle.SOUL, p.getLocation(), 100,1,1,1);
                    for(Entity e: p.getNearbyEntities(4.5, 4.5, 4.5)) {
                    	if(e instanceof LivingEntity && !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
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
                    		souls.add(le.getLocation());
                    		les.put(le.getLocation(), le);
                    		
                    	}
                    }
                    souls.forEach(s -> {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	p.playSound(s, Sound.PARTICLE_SOUL_ESCAPE, 0.2f, 2);
		                    	Vector spv = s.clone().toVector().subtract(p.getLocation().clone().toVector());
		                    	Double spd = p.getLocation().distance(s);
		                    	for(double di = 0; di < spd; di += 0.25) {
		                    		Location sc = p.getLocation().clone();
		                    		sc.add(spv.clone().normalize().multiply(di));
    			                	sc.getWorld().spawnParticle(Particle.SOUL, sc, 25,0.25,0.25,0.25,0);
    			                	sc.getWorld().spawnParticle(Particle.DRAGON_BREATH, sc, 50,0.3,0.3,0.3,0);
		                    	}
		                    	
			                }
						}, ai.incrementAndGet()/15);	
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
		                		atk0(0.7, wsd.Harvest.get(p.getUniqueId())*0.76, p, les.get(s),14);
			                }
						}, ai.incrementAndGet()/5+1);	

                    });
	            	
				}
			}
		}
	}
	
	final private void Wraith(Player p) {

		for(int i = 0; i <5; i++) {
	    	Random random=new Random();
	    	double number = random.nextDouble() * 1.5 * (random.nextBoolean() ? -1 : 1);
	    	double number2 = random.nextDouble() * 1.5 * (random.nextBoolean() ? -1 : 1);
	    	Location dl = p.getLocation().clone().add(number2, 2.2, number);

	    	int ri = random.nextInt(5);
	    	Material type = null;
	    	if(ri == 0) {
	    		type = Material.ZOMBIE_HEAD;
	    	}
	    	else if(ri == 1) {
	    		type = Material.CREEPER_HEAD;
	    	}
	    	else if(ri == 2) {
	    		type = Material.SKELETON_SKULL;
	    	}
	    	else if(ri == 3) {
	    		type = Material.BONE;
	    	}
	    	else if(ri == 4) {
	    		type = Material.SOUL_TORCH;
	    	}
	    	Snowball des = p.getWorld().spawn(dl, Snowball.class);
	    	des.setItem(new ItemStack(type));
	    	des.setShooter(p);
	    	des.setGravity(true);
	    	des.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	    	des.setMetadata("rob of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		}
    	
	}
	
	public void Wraith(PlayerInteractEvent ev) 
	{		
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 14 && wsd.Wraith.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("HOE") && p.getInventory().getItemInOffHand().getType().name().contains("TOTEM")  && p.isSneaking()) 
			{
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					double sec =6*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
					
					

					skilluse(()->{

	                	if(phtswt.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(phtswt.get(p.getUniqueId()));
	                		phtswt.remove(p.getUniqueId());
	                	}

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								if(Proficiency.getpro(p)>=1) {
									phtsw.putIfAbsent(p.getUniqueId(), 0);
								}
			                }
			            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								phtsw.remove(p.getUniqueId());
			                }
			            }, 40);
	                	phtswt.put(p.getUniqueId(), task);
	                	
	                    Vex v = (Vex)p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.VEX);
		        		v.setCharging(true);
		        		v.setAI(false);
		        		v.setInvulnerable(true);
	                    v.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    v.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		        		for(int i =0; i<20; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	Wraith(p);
					        		v.teleport(p.getLocation().add(p.getLocation().getDirection().normalize().multiply(0.3)));
				                	v.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, v.getLocation(), 20,1,1,1);
				                	v.getWorld().spawnParticle(Particle.FLAME, v.getLocation(), 20,1,1,1);
				                	p.playSound(v.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 0.1f, 2);
				                	for(Entity e: v.getNearbyEntities(4, 4, 4)) {
				                		if(e instanceof LivingEntity && p!=e && !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
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
				                    		atk0(0.2, wsd.Wraith.get(p.getUniqueId())*0.2, p, le,10);
				                		}
				                	}
				                }
				    	   }, i*3);				        			
		        		}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	v.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, v.getLocation(), 50,1,1,1);
			                	v.getWorld().spawnParticle(Particle.SOUL, v.getLocation(), 50,1,1,1);
			                	p.playSound(v.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 1, 0);
			                	v.remove();
			                }
						}, 31);	
					}, p, sec, "망령", "Wraith",thcooldown);
				} 
			}
		}
	}
	

	
	public void PhantomSwoop(PlayerInteractEvent ev) 
	{		
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 14 &&phtsw.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("HOE") && p.getInventory().getItemInOffHand().getType().name().contains("TOTEM")  && p.isSneaking()) 
			{
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					
	            	if(phtswt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(phtswt.get(p.getUniqueId()));
	            		phtswt.remove(p.getUniqueId());
	            	}
					phtsw.remove(p.getUniqueId());

	            	if(sacrft.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(sacrft.get(p.getUniqueId()));
	            		sacrft.remove(p.getUniqueId());
	            	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=2) {
								sacrf.putIfAbsent(p.getUniqueId(), 0);
							}
		                }
		            }, 6); 

	        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							sacrf.remove(p.getUniqueId());
		                }
		            }, 40); 
	            	sacrft.put(p.getUniqueId(), task);

	            	final Vector pv = p.getEyeLocation().getDirection();
                    Phantom v = (Phantom)p.getWorld().spawnEntity(p.getEyeLocation().clone().add(0, 1, 0), EntityType.PHANTOM);
	        		v.setAI(false);
	        		v.setSize(6);
	        		v.setInvulnerable(true);
                    v.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    v.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    v.setVelocity(pv);
	        		for(int i =0; i<20; i++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                    Phantom v1 = (Phantom)p.getWorld().spawnEntity(p.getEyeLocation().clone().add(0, 1, 0), EntityType.PHANTOM);
			                	p.playSound(v1.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 0.1f, 1.5f);
				        		v1.setAI(false);
				        		v1.setSize(2);
				        		v1.setInvulnerable(true);
			                    v1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			                    v1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			                    v1.setVelocity(pv.clone().normalize().multiply(3));
			                    
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	v1.remove();
					                }
								}, 10);	
								
			                	v.getWorld().spawnParticle(Particle.SOUL, v.getLocation(), 5,1,1,1);
			                	v.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, v.getLocation(), 100,1,3,1,0.1);
			                	v.getWorld().spawnParticle(Particle.SPORE_BLOSSOM_AIR, v.getLocation(), 50,2,2,2);
			                	p.playSound(v.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 0.2f, 2);
			                	for(Entity e: v.getNearbyEntities(3, 3, 3)) {
			                		if(e instanceof LivingEntity && p!=e && !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
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
			                    		atk0(0.2, wsd.Wraith.get(p.getUniqueId())*0.2, p, le,11);
			                    		Holding.holding(p, le, 3l);
			                		}
			                	}
			                }
			    	   }, i*2);	

	        		}
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	v.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, v.getLocation(), 50,1,1,1);
		                	v.getWorld().spawnParticle(Particle.SOUL, v.getLocation(), 50,1,1,1);
		                	p.playSound(v.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 1, 0);
		                	v.remove();
		                }
					}, 40);	
				}
			}
		}
	}
	

	
	public void Sacrifice(PlayerInteractEvent ev) 
	{		
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 14 &&sacrf.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("HOE") && p.getInventory().getItemInOffHand().getType().name().contains("TOTEM")  && p.isSneaking()) 
			{
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					
	            	if(sacrft.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(sacrft.get(p.getUniqueId()));
	            		sacrft.remove(p.getUniqueId());
	            	}
					sacrf.remove(p.getUniqueId());
					
					if(zombies.containsKey(p.getUniqueId())) {
						final Set<Mob> zs = zombies.get(p.getUniqueId());
						zs.forEach(v -> {
		                	v.getWorld().spawnParticle(Particle.CRIMSON_SPORE, v.getLocation(), 300,1,1,1);
		                	p.playSound(v.getLocation(), Sound.ENTITY_ZOMBIE_DEATH, 1f, 0);
		                	p.playSound(v.getLocation(), Sound.ENTITY_ZOMBIE_CONVERTED_TO_DROWNED, 1f, 2f);
		                	for(Entity e: v.getNearbyEntities(3.5, 3.5, 3.5)) {
		                		if(e instanceof LivingEntity && p!=e && !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
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
		                    		atk0(1.8, wsd.Wraith.get(p.getUniqueId())*2.5, p, le,11);
		                		}
		                	}
		                	v.remove();
							
						});
						zombies.removeAll(p.getUniqueId());
					}
					else {
						for(int i = 0; i<6; i++) {
							ZombieSpawn(p,p);
						}
					}
				}
			}
		}
	}
	
	
	public void AstralProjection(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
        
		
		if(ClassData.pc.get(p.getUniqueId()) == 14 && wsd.AstralProjection.getOrDefault(p.getUniqueId(),0)>=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("TOTEM") && p.getInventory().getItemInOffHand().getType().name().contains("HOE") && !p.isSneaking()) 
			{
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
					double sec =8*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
					ev.setCancelled(true);
					if(pncooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (pncooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("유체이탈 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
		                	}
		                	else {
			                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use AstralProjection").create());
		                	}
		                }
		                else // if timer is done
		                {
		                    pncooldown.remove(p.getName()); // removing player from HashMap

			        		if(astrt.containsKey(p.getUniqueId())) {
			        			Bukkit.getScheduler().cancelTask(astrt.get(p.getUniqueId()));
			        		}
			        		GameMode gm = p.getGameMode();
			        		astrgm.put(p.getUniqueId(), gm);
		                    Enderman v = (Enderman)p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.ENDERMAN);
		                	p.playSound(v.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 1, 2);
			        		v.setAI(false);
			        		v.setInvulnerable(true);
			        		v.setSilent(true);
			        		v.setInvisible(true);
		                    v.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		                    v.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			        		p.setGameMode(GameMode.SPECTATOR);
			        		p.setSpectatorTarget(v);
							int task=Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
						        		p.setSpectatorTarget(v);
					                	v.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, v.getLocation(), 10,1,1,1,0);
					                	p.playSound(v.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.3f, 0);
					                	//if(p.getLocation().add(p.getLocation().getDirection().normalize().multiply(1.2)).getBlock().isPassable()) {
					                	v.teleport(p.getLocation().add(p.getLocation().getDirection().normalize().multiply(1.2)));
					                	//}
					                }
					    	   }, 2,2);
							astrt.put(p.getUniqueId(), task);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
					        		AstralProjection(p,gm,v);
				                }
							}, 51);	
							if(Proficiency.getpro(p)>=2) {
				                lncooldown.remove(p.getName());
							}
							pncooldown.put(p.getName(), System.currentTimeMillis());  
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		        		if(astrt.containsKey(p.getUniqueId())) {
		        			Bukkit.getScheduler().cancelTask(astrt.get(p.getUniqueId()));
		        		}
		        		GameMode gm = p.getGameMode();
		        		astrgm.put(p.getUniqueId(), gm);
	                    Enderman v = (Enderman)p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.ENDERMAN);
	                	p.playSound(v.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 1, 2);
		        		v.setAI(false);
		        		v.setInvulnerable(true);
		        		v.setSilent(true);
		        		v.setInvisible(true);
	                    v.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    v.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		        		p.setGameMode(GameMode.SPECTATOR);
		        		p.setSpectatorTarget(v);
						int task=Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
					        		p.setSpectatorTarget(v);
				                	v.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, v.getLocation(), 10,1,1,1,0);
				                	p.playSound(v.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.3f, 0);
				                	//if(p.getLocation().add(p.getLocation().getDirection().normalize().multiply(1.2)).getBlock().isPassable()) {
				                	v.teleport(p.getLocation().add(p.getLocation().getDirection().normalize().multiply(1.2)));
				                	//}
				                }
				    	   }, 2,2);
						astrt.put(p.getUniqueId(), task);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
				        		AstralProjection(p,gm,v);
			                }
						}, 51);	

						if(Proficiency.getpro(p)>=2) {
			                lncooldown.remove(p.getName());
						}
						pncooldown.put(p.getName(), System.currentTimeMillis());  
					}
				} 
				}
		}
	}
	

	final private void AstralProjection(Player p, GameMode gm, Enderman v) {
		if(astrt.containsKey(p.getUniqueId())) {
			Bukkit.getScheduler().cancelTask(astrt.get(p.getUniqueId()));
	    	v.remove();
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 60, 1, false, false));
	    	p.setGameMode(gm);
	    	if(Proficiency.getpro(p)>=1) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,60, 2, false, false));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 2, false, false));
	    	}
			astrt.remove(p.getUniqueId());
		}
		if(astrgm.containsKey(p.getUniqueId())) {
			astrgm.remove(p.getUniqueId());
		}
    	return;
	}
	
	
	public void AstralProjection(PlayerToggleSneakEvent ev) 
	{
			Player p = (Player) ev.getPlayer();
			if(astrgm.containsKey(p.getUniqueId())) {

				Enderman v = (Enderman) p.getSpectatorTarget();
        		AstralProjection(p,astrgm.get(p.getUniqueId()),v);
			}
	}
	
	
	
	
	@SuppressWarnings("deprecation")
	public void Incantation(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 14 && wsd.Incantation.getOrDefault(p.getUniqueId(),0)>=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("TOTEM") && p.getInventory().getItemInOffHand().getType().name().contains("HOE")  &&  p.isSneaking()) 
			{
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
					double sec = 13*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
					ev.setCancelled(true);
					if(eccooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (eccooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("주술 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
		                	}
		                	else {
			                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Incantation").create());
		                	}
		                }
		                else // if timer is done
		                {
		                    eccooldown.remove(p.getName()); // removing player from HashMap
			            	ArmorStand v = (ArmorStand)p.getWorld().spawnEntity(p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation(), EntityType.ARMOR_STAND);
		                	p.playSound(v.getLocation(), Sound.ENTITY_WITCH_CELEBRATE, 1, 0);
			        		ItemStack head = new ItemStack(Material.SKELETON_SKULL);
			        		v.setHelmet(head);
			        		v.setInvulnerable(true);
			        		v.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
			        		v.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
    						if(Proficiency.getpro(p)>=1) {
    							p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 2, 2, false, false));
        					}
    						if(Proficiency.getpro(p)>=2) {
    							p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 2, 2, false, false));
        					}
			        		for(Entity e: v.getNearbyEntities(5, 5, 5)) {
			        			if(e instanceof LivingEntity && e!=p && !e.hasMetadata("chickened") && !e.hasMetadata("enderdragondamager")&& !deathp.containsKey(e.getUniqueId()) && !e.hasMetadata("untargetable") && !e.hasMetadata("fake") && !e.hasMetadata("portal") & e!=v&& !e.isDead()) {
			        				final LivingEntity le = (LivingEntity)e;
			        				chickenspawn(le,p);
			        			}
			        		}
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
					        		for(Entity e: p.getWorld().getEntities()) {
					        			if(e.hasMetadata("chickened"+p.getName()) && e instanceof LivingEntity) {
					        				LivingEntity ch = (LivingEntity)e;
					        				chickenfin(ch,ch.getMaxHealth()-ch.getHealth());
					        			}
					        		}
					        		v.remove();
				                }
							}, 60);	
							eccooldown.put(p.getName(), System.currentTimeMillis());  
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	ArmorStand v = (ArmorStand)p.getWorld().spawnEntity(p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation(), EntityType.ARMOR_STAND);
	                	p.playSound(v.getLocation(), Sound.ENTITY_WITCH_CELEBRATE, 1, 0);
		        		ItemStack head = new ItemStack(Material.SKELETON_SKULL);
		        		v.setHelmet(head);
		        		v.setInvulnerable(true);
		        		v.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
		        		v.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
						if(Proficiency.getpro(p)>=1) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 2, 2, false, false));
    					}
						if(Proficiency.getpro(p)>=2) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 2, 2, false, false));
    					}
		        		for(Entity e: v.getNearbyEntities(5, 5, 5)) {
		        			if(e instanceof LivingEntity && e!=p && !e.hasMetadata("chickened") && !e.hasMetadata("enderdragondamager")&& !deathp.containsKey(e.getUniqueId()) && !e.hasMetadata("untargetable") && !e.hasMetadata("fake") && !e.hasMetadata("portal") & e!=v&& !e.isDead()) {
		        				final LivingEntity le = (LivingEntity)e;
		        				chickenspawn(le,p);
		        			}
		        		}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
				        		for(Entity e: p.getWorld().getEntities()) {
				        			if(e.hasMetadata("chickened"+p.getName()) && e instanceof LivingEntity) {
				        				LivingEntity ch = (LivingEntity)e;
				        				chickenfin(ch,ch.getMaxHealth()-ch.getHealth());
				        			}
				        		}
				        		v.remove();
			                }
						}, 60);	
						eccooldown.put(p.getName(), System.currentTimeMillis());  
					}
				} 
				}
		}
	}

	@SuppressWarnings("deprecation")
	final private void chickenspawn(LivingEntity le, Player p) {
		if (le instanceof Player) 
		{
			
			Player p1 = (Player) le;
			if(Party.hasParty(p) && Party.hasParty(p1))	{
				if(Party.getParty(p).equals(Party.getParty(p1)))
				{
					if(Proficiency.getpro(p)>=1) {
						p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 2, 2, false, false));
					}
					if(Proficiency.getpro(p)>=2) {
						p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 2, 2, false, false));
					}

					return;
				}
			}
			Holding.holding(p, le, 60l);
			return;
		}
		
		Location sl = le.getLocation().clone();
		
        Chicken ch = (Chicken)p.getWorld().spawnEntity(le.getLocation(), EntityType.CHICKEN);
        if(CommonEvents.damaged.containsKey(le.getUniqueId())){
            ch.setCustomName(CommonEvents.damaged.get(le.getUniqueId()));ch.setCustomNameVisible(false);
        }
        else{
            ch.setCustomName(le.getName());ch.setCustomNameVisible(false);
        }
        ch.setAI(false);
        ch.setAdult();
        ch.setMaxHealth(le.getHealth());
        ch.setHealth(le.getHealth());
        ch.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
        ch.setMetadata("chickened", new FixedMetadataValue(RMain.getInstance(), true));
        ch.setMetadata("chickened"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
        Holding.holding(p, ch, 60l);
        for(PotionEffect potioneffect: le.getActivePotionEffects()) {
        	ch.addPotionEffect(potioneffect);
        }
		chickdam.put(ch, p);	
		le.setPersistent(true);
		if(le.hasMetadata("rmf")) {
			le.setRemoveWhenFarAway(false);
		}
		chickenget.put(ch, le);
		Holding.untouchable(le, 60l);
		le.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,60, 2, false, false));
		le.setGravity(false);
		chickenloc.put(le.getUniqueId(), sl.clone());

		sl.setY(-50);
		le.teleport(sl);
		return;
	}
	
	final private void chickenfin(final LivingEntity ch, Double d) {
		ch.setInvisible(true);
		final LivingEntity le = Holding.ale(chickenget.get(ch));
		if(Hex.containsKey(ch.getUniqueId())) {
			Hex.put(le.getUniqueId(), chickdam.get(ch).getUniqueId());
		}
		if(deathdamager.containsKey(ch.getUniqueId())){
			deathdamager.put(le.getUniqueId(), deathdamager.get(ch.getUniqueId()));
		}
		if(le.hasMetadata("rmf")) {
			le.setRemoveWhenFarAway(true);
		}
		le.removeMetadata("fake", RMain.getInstance());
		le.teleport(chickenloc.get(le.getUniqueId()));
		le.setFallDistance(0);
		le.setInvulnerable(false);
		le.setInvisible(false);
		le.setGravity(true);
        for(PotionEffect potioneffect: ch.getActivePotionEffects()) {
        	le.addPotionEffect(potioneffect);
        }
		atk0(0d, d, chickdam.get(ch), le);
		ch.remove();
	}
	
	
	@SuppressWarnings("deprecation")
	public void Incantation(EntityDeathEvent ev)        
    {
		if(ev.getEntity().hasMetadata("chickened")) {
			LivingEntity ch = ev.getEntity();
			ev.setDroppedExp(0);
			ev.getDrops().clear();
			chickenfin(ch,ch.getMaxHealth());
		}
    }

	

	final private void ULT(Player p, Location il) {

		p.getWorld().spawnParticle(Particle.DRAGON_BREATH, il, 400, 9, 1, 9,0);
		p.getWorld().spawnParticle(Particle.ASH, il, 300, 9, 2, 9,0);

		for (Entity e : il.getWorld().getNearbyEntities(il,9, 9, 9))
		{
			if (e instanceof Player) 
			{

				if(Baront.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(Baront.get(p.getUniqueId()));
					Baront.remove(p.getUniqueId());
				}
				
				if(Baron.getOrDefault(p.getName(),0) <= wsd.Bosou.get(p.getUniqueId())) {
					Baron.put(p.getUniqueId(), wsd.Bosou.get(p.getUniqueId()));
				}
				
				
				int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		            @Override
		            public void run() 
		            {
		            	Baron.remove(p.getUniqueId());
		            }
				}, 10); 
				
				Baront.put(p.getUniqueId(), task);
				
				Player p1 = (Player) e;
				if(Party.hasParty(p) && Party.hasParty(p1))	{
				if(Party.getParty(p).equals(Party.getParty(p1)))
					{
						if(Baront.containsKey(p1.getUniqueId())) {
							Bukkit.getScheduler().cancelTask(Baront.get(p1.getUniqueId()));
							Baront.remove(p1.getUniqueId());
						}
						
						if(Baron.getOrDefault(p1.getName(),0) <= p.getLevel()) {
							Baron.put(p1.getUniqueId(), p.getLevel());
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	Baron.remove(p1.getUniqueId());
			                }
			    	   }, 10);
					}
				}
			}
		}
    	return;
	}
	
	
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		final Location l = p.getLocation();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 14 && (is.getType().name().contains("HOE") || is.getType().name().contains("TOTEM")) && p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
				ev.setCancelled(true);
				if(aultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (aultcooldown.get(p.getName())/1000d + 80/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d)) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("바롱사메디 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
	                	}
	                	else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Baron Samedi").create());
	                	}
		            }
	                else // if timer is done
	                {
	                    aultcooldown.remove(p.getName()); // removing player from HashMap
	                    
	                    ItemStack whel = new ItemStack(Material.LEATHER_HELMET);
	                    LeatherArmorMeta whelm = (LeatherArmorMeta) whel.getItemMeta();
	                   	whelm.setColor(Color.BLACK);
	                   	whel.setItemMeta(whelm);
	                    ItemStack wb = new ItemStack(Material.LEATHER_BOOTS);
	                    LeatherArmorMeta wbm = (LeatherArmorMeta) wb.getItemMeta();
	                   	wbm.setColor(Color.WHITE);
	                   	wb.setItemMeta(wbm);
	                    ItemStack wc = new ItemStack(Material.LEATHER_CHESTPLATE);
	                    LeatherArmorMeta wcm = (LeatherArmorMeta) wc.getItemMeta();
	                   	wcm.setColor(Color.fromRGB(80, 0, 68));
	                   	wc.setItemMeta(wcm);
	                    ItemStack wl = new ItemStack(Material.LEATHER_LEGGINGS);
	                    LeatherArmorMeta wlm = (LeatherArmorMeta) wl.getItemMeta();
	                   	wlm.setColor(Color.PURPLE);
	                   	wl.setItemMeta(wlm);
	                    ItemStack wmh = new ItemStack(Material.DEBUG_STICK);
	                    ItemStack wmo = new ItemStack(Material.DRAGON_BREATH);
	                    
	                    Skeleton w = (Skeleton)p.getWorld().spawnEntity(p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation().add(0, -2, 0), EntityType.SKELETON);
	                    w.setAI(false);
	                    w.getEquipment().clear();
	                    w.setGlowing(true);
	                    w.setRemoveWhenFarAway(false);
	                    w.setAbsorptionAmount(100);
						w.getEquipment().setHelmet(whel);
						w.getEquipment().setLeggings(wl);
						w.getEquipment().setBoots(wb);
						w.getEquipment().setChestplate(wc);
						w.getEquipment().setItemInOffHand(wmo);
						w.getEquipment().setItemInMainHand(wmh);
						w.getEquipment().setBootsDropChance(0);
						w.getEquipment().setChestplateDropChance(0);
						w.getEquipment().setHelmetDropChance(0);
						w.getEquipment().setItemInMainHandDropChance(0);
						w.getEquipment().setItemInOffHandDropChance(0);
						w.getEquipment().setLeggingsDropChance(0);
						w.setInvulnerable(true);
	                    w.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    w.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						p.playSound(l, Sound.AMBIENT_WARPED_FOREST_ADDITIONS, 1, 0);
						p.playSound(l, Sound.ENTITY_SKELETON_AMBIENT, 1, 0);
						p.playSound(l, Sound.ENTITY_VINDICATOR_CELEBRATE, 1, 0);
						for(int dou = 0; dou < 10; dou++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									w.teleport(w.getLocation().add(w.getLocation().getDirection().rotateAroundX(-Math.PI/2).normalize().multiply(0.1)));
									p.playSound(l, Sound.ENTITY_WITHER_SKELETON_STEP, 0.8f, 0);
				                }
							}, dou);
						}
						for(int i = 0; i <40; i++) {
	                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									ULT(p,w.getLocation().clone());
				                }
				            }, i*5+3); 
                	}
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	w.remove();
		                }
					}, 204);
		            aultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    
                    ItemStack whel = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta whelm = (LeatherArmorMeta) whel.getItemMeta();
                   	whelm.setColor(Color.BLACK);
                   	whel.setItemMeta(whelm);
                    ItemStack wb = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta wbm = (LeatherArmorMeta) wb.getItemMeta();
                   	wbm.setColor(Color.WHITE);
                   	wb.setItemMeta(wbm);
                    ItemStack wc = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta wcm = (LeatherArmorMeta) wc.getItemMeta();
                   	wcm.setColor(Color.fromRGB(80, 0, 68));
                   	wc.setItemMeta(wcm);
                    ItemStack wl = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta wlm = (LeatherArmorMeta) wl.getItemMeta();
                   	wlm.setColor(Color.PURPLE);
                   	wl.setItemMeta(wlm);
                    ItemStack wmh = new ItemStack(Material.DEBUG_STICK);
                    ItemStack wmo = new ItemStack(Material.DRAGON_BREATH);
                    
                    Skeleton w = (Skeleton)p.getWorld().spawnEntity(p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation().add(0, -2, 0), EntityType.SKELETON);
                    w.setAI(false);
                    w.getEquipment().clear();
                    w.setGlowing(true);
                    w.setRemoveWhenFarAway(false);
                    w.setAbsorptionAmount(100);
					w.getEquipment().setHelmet(whel);
					w.getEquipment().setLeggings(wl);
					w.getEquipment().setBoots(wb);
					w.getEquipment().setChestplate(wc);
					w.getEquipment().setItemInOffHand(wmo);
					w.getEquipment().setItemInMainHand(wmh);
					w.getEquipment().setBootsDropChance(0);
					w.getEquipment().setChestplateDropChance(0);
					w.getEquipment().setHelmetDropChance(0);
					w.getEquipment().setItemInMainHandDropChance(0);
					w.getEquipment().setItemInOffHandDropChance(0);
					w.getEquipment().setLeggingsDropChance(0);
					w.setInvulnerable(true);
                    w.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    w.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					p.playSound(l, Sound.AMBIENT_WARPED_FOREST_ADDITIONS, 1, 0);
					p.playSound(l, Sound.ENTITY_SKELETON_AMBIENT, 1, 0);
					p.playSound(l, Sound.ENTITY_VINDICATOR_CELEBRATE, 1, 0);
					for(int dou = 0; dou < 10; dou++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								w.teleport(w.getLocation().add(w.getLocation().getDirection().rotateAroundX(-Math.PI/2).normalize().multiply(0.1)));
								p.playSound(l, Sound.ENTITY_WITHER_SKELETON_STEP, 0.8f, 0);
			                }
						}, dou);
					}
					for(int i = 0; i <40; i++) {
                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								ULT(p,w.getLocation().clone());
			                }
			            }, i*5+3); 
            	}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                	w.remove();
	                }
				}, 204);
	                aultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}	
    }

	
	public void ULT(EntityDamageEvent d) 
	{		
		if(d.getEntity() instanceof Player) 
		{
			Player p = (Player)d.getEntity();
			if((p.getHealth()-d.getDamage())<=1 && Baron.containsKey(p.getUniqueId())) {
									
	                d.setCancelled(true);
	                p.setHealth(1);
	        		p.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, p.getLocation(), 100, 4, 2, 4);
	        		p.getWorld().spawnParticle(Particle.SOUL, p.getLocation(), 100, 4, 2, 4);
	        		p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 1, 0);
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[바롱사메디]").color(ChatColor.BOLD).color(ChatColor.DARK_PURPLE).create());
	        		}
	        		else {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Baron Samedi]").color(ChatColor.BOLD).color(ChatColor.DARK_PURPLE).create());
	        		}
			}
		}
	}

	
	public void ULT2(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 14 && (is.getType().name().contains("HOE") || is.getType().name().contains("TOTEM")) && p.isSprinting()&& !p.isSneaking()&&Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
				if(ault2cooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (ault2cooldown.get(p.getName())/1000d + 90*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d)) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("죽음의표식 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
	                	}
	                	else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use MarkOfDeath").create());
	                	}
		            }
	                else // if timer is done
	                {
	                    ault2cooldown.remove(p.getName()); // removing player from HashMap

	                    Location l = p.getLocation().clone();
	                    
						p.playSound(l, Sound.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 1, 2);
						p.playSound(l, Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 1, 0);
						p.playSound(l, Sound.ENTITY_HOGLIN_CONVERTED_TO_ZOMBIFIED, 1, 0);
						
		        		p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, l, 500, 4, 2, 4);
		        		p.getWorld().spawnParticle(Particle.SUSPENDED, l, 600, 7, 2, 7,0);
		        		p.getWorld().spawnParticle(Particle.SUSPENDED_DEPTH, l, 600, 7, 2, 7,0);
		        		p.getWorld().spawnParticle(Particle.LANDING_OBSIDIAN_TEAR, l, 600, 7, 2, 7,0);
						
	                    for(Entity e: p.getNearbyEntities(8, 8, 8)) {
		        			if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake") && !e.hasMetadata("portal") && !e.isDead()) {
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
	                    		
		        				Holding.superholding(p, le, 80l);
		        				
		        				if(deathp.containsKey(le.getUniqueId())) {
		        					return;
		        				}
		        				
		    					Item t = p.getWorld().dropItem(le.getEyeLocation().clone().add(0, 1, 0), new ItemStack(Material.TOTEM_OF_UNDYING));
		    					t.setGlowing(true);
		    					t.setInvulnerable(true);
		    					t.setPickupDelay(999999);
		    					t.setCustomName(ChatColor.DARK_RED+ "DEATH PRISONED");
		    					t.setCustomNameVisible(true);
		    					t.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
		    					t.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
		    					t.setGravity(false);
		    					t.setVelocity(t.getVelocity().zero());

		        				if(le.hasMetadata("chickened")&&!le.hasMetadata("enderdragondamager")) {
		        					LivingEntity tar = chickenget.get(le);
		        					Holding.superholding(p,tar,80l);
			        				deathp.put(tar.getUniqueId(), p.getUniqueId());
			        				int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			        	                @Override
			        	                public void run() 
			        	                {
			        	                	t.remove();
			        		        		p.getWorld().spawnParticle(Particle.WHITE_ASH, tar.getLocation(), 100, 1, 1, 1);

					        				deathp.remove(tar.getUniqueId());
			        		        		if(deathdamager.containsKey(tar.getUniqueId())) {
				        	    				if(Party.hasParty(p)) {
				        	    					Party.getMembers(Party.getParty(p)).forEach(pu -> {
				        	    						Player p1 = Bukkit.getPlayer(pu);
				        	    						atk0(0d, deathdamager.get(tar.getUniqueId()).get(p1.getUniqueId())*(1+ p.getLevel()*0.001), p1, tar);
				        	    					});
				        	    				}
				        	    				else {
			        	    						atk0(0d, deathdamager.get(tar.getUniqueId()).get(p.getUniqueId())*(1+ p.getLevel()*0.001), p, tar);
				        	    				}
			        		        		}
			        	                }
			        				}, 80);
			        				deathpt.put(tar.getUniqueId(), task);
		        				}
		        				else {
			        				deathp.put(le.getUniqueId(), p.getUniqueId());
			        				
			        				int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			        	                @Override
			        	                public void run() 
			        	                {
			        	                	deathp.remove(le.getUniqueId());
			        	                	t.remove();
			        		        		p.getWorld().spawnParticle(Particle.WHITE_ASH, le.getLocation(), 100, 1, 1, 1);

			        		        		if(deathdamager.containsKey(le.getUniqueId())) {
				        	    				if(Party.hasParty(p)) {
				        	    					Party.getMembers(Party.getParty(p)).forEach(pu -> {
				        	    						Player p1 = Bukkit.getPlayer(pu);
				        	    						atk0(0d, deathdamager.get(le.getUniqueId()).get(p1.getUniqueId())*(1+ p.getLevel()*0.001), p1, le);
				        	    					});
				        	    				}
				        	    				else {
			        	    						atk0(0d, deathdamager.get(le.getUniqueId()).get(p.getUniqueId())*(1+ p.getLevel()*0.001), p, le);
				        	    				}
			        		        		}
			        	                }
			        				}, 80);
			        				deathpt.put(le.getUniqueId(), task);
		        				}
		        			}
		        		}
	                    ault2cooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    
                    Location l = p.getLocation().clone();
                    
					p.playSound(l, Sound.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 1, 2);
					p.playSound(l, Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 1, 0);
					p.playSound(l, Sound.ENTITY_HOGLIN_CONVERTED_TO_ZOMBIFIED, 1, 0);
					
	        		p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, l, 500, 4, 2, 4);
	        		p.getWorld().spawnParticle(Particle.SUSPENDED, l, 600, 7, 2, 7,0);
	        		p.getWorld().spawnParticle(Particle.SUSPENDED_DEPTH, l, 600, 7, 2, 7,0);
	        		p.getWorld().spawnParticle(Particle.LANDING_OBSIDIAN_TEAR, l, 600, 7, 2, 7,0);
					
                    for(Entity e: p.getNearbyEntities(8, 8, 8)) {
	        			if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake") && !e.hasMetadata("portal") && !e.isDead()) {
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
                    		
	        				Holding.superholding(p, le, 80l);
	        				
	        				if(deathp.containsKey(le.getUniqueId())) {
	        					return;
	        				}
	        				
	    					Item t = p.getWorld().dropItem(le.getEyeLocation(), new ItemStack(Material.TOTEM_OF_UNDYING));
	    					t.setGlowing(true);
	    					t.setInvulnerable(true);
	    					t.setPickupDelay(999999);
	    					t.setCustomName(ChatColor.DARK_RED+ "DEATH PRISONED");
	    					t.setCustomNameVisible(true);
	    					t.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
	    					t.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
	    					t.setGravity(false);
	    					t.setVelocity(t.getVelocity().zero());

	        				if(le.hasMetadata("chickened")&&!le.hasMetadata("enderdragondamager")) {
	        					LivingEntity tar = chickenget.get(le);
		        				deathp.put(tar.getUniqueId(), p.getUniqueId());
	        					Holding.superholding(p,tar,80l);
		        				int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		        	                @Override
		        	                public void run() 
		        	                {
		        	                	t.remove();
		        		        		p.getWorld().spawnParticle(Particle.WHITE_ASH, tar.getLocation(), 100, 1, 1, 1);

				        				deathp.remove(tar.getUniqueId());
		        		        		if(deathdamager.containsKey(tar.getUniqueId())) {
			        	    				if(Party.hasParty(p)) {
			        	    					Party.getMembers(Party.getParty(p)).forEach(pu -> {
			        	    						Player p1 = Bukkit.getPlayer(pu);
			        	    						atk0(0d, deathdamager.get(tar.getUniqueId()).get(p1.getUniqueId())*(1+ p.getLevel()*0.001), p1, tar);
			        	    					});
			        	    				}
			        	    				else {
		        	    						atk0(0d, deathdamager.get(tar.getUniqueId()).get(p.getUniqueId())*(1+ p.getLevel()*0.001), p, tar);
			        	    				}
		        		        		}
		        	                }
		        				}, 80);
		        				deathpt.put(tar.getUniqueId(), task);
	        				}
	        				else {
		        				deathp.put(le.getUniqueId(), p.getUniqueId());
		        				
		        				int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		        	                @Override
		        	                public void run() 
		        	                {
		        	                	deathp.remove(le.getUniqueId());
		        	                	t.remove();
		        		        		p.getWorld().spawnParticle(Particle.WHITE_ASH, le.getLocation(), 100, 1, 1, 1);

		        		        		if(deathdamager.containsKey(le.getUniqueId())) {
			        	    				if(Party.hasParty(p)) {
			        	    					Party.getMembers(Party.getParty(p)).forEach(pu -> {
			        	    						Player p1 = Bukkit.getPlayer(pu);
			        	    						atk0(0d, deathdamager.get(le.getUniqueId()).get(p1.getUniqueId())*(1+ p.getLevel()*0.001), p1, le);
			        	    					});
			        	    				}
			        	    				else {
		        	    						atk0(0d, deathdamager.get(le.getUniqueId()).get(p.getUniqueId())*(1+ p.getLevel()*0.001), p, le);
			        	    				}
		        		        		}
		        	                }
		        				}, 80);
		        				deathpt.put(le.getUniqueId(), task);
	        				}
	        			}
	        		}
	                ault2cooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}	
    }

	
	
	
	
	@SuppressWarnings("deprecation")
	public void ThrowCancel(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
		
			if(ClassData.pc.get(p.getUniqueId()) == 14 && (is.getType().name().contains("HOE") || is.getType().name().contains("TOTEM")) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
			}
	
    }
	
	

	
	public void Legba(EntityDamageByEntityEvent d) 
	{
        
		
		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && d.getDamage()>0) 
		{
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
				
				if(ClassData.pc.get(p.getUniqueId()) == 14) {
						if(p.getInventory().getItemInMainHand().getType().name().contains("HOE")||p.getInventory().getItemInMainHand().getType().name().contains("TOTEM"))
						{
							if(p==le) {d.setCancelled(true);}
							d.setDamage(d.getDamage()+wsd.Legba.get(p.getUniqueId())*0.585);
						}
				}
				
				if(bosou.containsKey(p.getUniqueId())) {
					d.setDamage(d.getDamage()*1.05*(1+bosou.get(p.getUniqueId())*0.04));
				}
				if(Baron.containsKey(p.getUniqueId())) {
					d.setDamage(d.getDamage()*1.23+Baron.get(p.getUniqueId())*0.5);
				}
				if(deathp.containsKey(le.getUniqueId())) {
					if(deathdamager.containsKey(le.getUniqueId())) {
						deathdamager.get(le.getUniqueId()).computeIfPresent(p.getUniqueId(), (k,v) -> v+d.getDamage());
					}
					deathdamager.putIfAbsent(le.getUniqueId(),new HashMap<UUID, Double>(){private static final long serialVersionUID = 1L;{put(p.getUniqueId(),d.getDamage());}});
					d.setCancelled(true);
				}
		}

		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity&& d.getDamage()>0) 
		{
			Arrow ar = (Arrow)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
				
				
					if(ClassData.pc.get(p.getUniqueId()) == 14) {
							if(p.getInventory().getItemInMainHand().getType().name().contains("HOE")||p.getInventory().getItemInMainHand().getType().name().contains("TOTEM"))
							{
								if(p==le) {d.setCancelled(true);}
								d.setDamage(d.getDamage()+wsd.Legba.get(p.getUniqueId())*0.585);
							}
					}
					
					if(bosou.containsKey(p.getUniqueId())) {
						d.setDamage(d.getDamage()*1.05*(1+bosou.get(p.getUniqueId())*0.04));
					}
					if(Baron.containsKey(p.getUniqueId())) {
						d.setDamage(d.getDamage()*1.23+Baron.get(p.getUniqueId())*0.5);
					}
					if(deathp.containsKey(le.getUniqueId())) {
						if(deathdamager.containsKey(le.getUniqueId())) {
							deathdamager.get(le.getUniqueId()).computeIfPresent(p.getUniqueId(), (k,v) -> v+d.getDamage());
						}
						deathdamager.putIfAbsent(le.getUniqueId(),new HashMap<UUID, Double>(){private static final long serialVersionUID = 1L;{put(p.getUniqueId(),d.getDamage());}});
						d.setCancelled(true);
					}
			}
		}
		
		if(d.getEntity() instanceof Player) 
		{
		Player p = (Player)d.getEntity();

		if(bosou.containsKey(p.getUniqueId())) {
			d.setDamage(d.getDamage()*(0.7-bosou.get(p.getUniqueId())*0.045));
			p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p.getLocation(), 5, 1,1,1,0);
		}
		}
	}
	
	
	public void Legba2(EntityDamageEvent d) 
	{
		if(d.getEntity() instanceof LivingEntity) 
		{
		LivingEntity le = (LivingEntity)d.getEntity();
			if(Hex.containsKey(le.getUniqueId())) {
				d.setDamage(d.getDamage()*1.15);
			}
		}
	}

	final private void ZombieSpawn(Player p, LivingEntity le) {
		if(zombies.containsKey(p.getUniqueId()) && zombies.get(p.getUniqueId()).size() >5) {
			return;
		}
		Zombie zom = le.getWorld().spawn(le.getLocation(), Zombie.class);
		zom.setAdult();
		zom.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), p.getName()));
		zom.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), p.getName()));
		zom.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), p.getName()));
		zom.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(wsd.Legba.get(p.getUniqueId())*0.03);
		zom.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(wsd.Legba.get(p.getUniqueId())*100d);
		zom.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(wsd.Legba.get(p.getUniqueId()));
		zom.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(wsd.Legba.get(p.getUniqueId()));
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			zom.setCustomName(p.getName()+"의 좀비");
		}
		else {
			zom.setCustomName(p.getName()+"'s Zombie");
		}
		zom.setCollidable(false);
		zom.setCustomNameVisible(true);
		zombies.put(p.getUniqueId(), zom);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
    			p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, zom.getLocation(),50);
            	zom.remove();
            	zombies.remove(p.getUniqueId(), zom);
            }
		}, 200);
	}
	
	public void Zombies(EntityDeathEvent d) 
	{
		if(d.getEntity() instanceof LivingEntity) 
		{
			LivingEntity le = (LivingEntity)d.getEntity();
			if(Hex.containsKey(le.getUniqueId()) && !le.hasMetadata("chickened")) {
				Player p = Bukkit.getPlayer(Hex.get(le.getUniqueId()));
				if(Proficiency.getpro(p)<1) {
					return;
				}
				ZombieSpawn(p,le);
			}
		}
	}

	
	public void delete(PlayerDeathEvent d) 
	{
		if(d.getEntity() instanceof Player) {
			Player p = (Player) d.getEntity();
			if(zombies.containsKey(p.getUniqueId())) {
				zombies.get(p.getUniqueId()).forEach(le -> {
					le.remove();
				});
				zombies.removeAll(p.getUniqueId());
			}
		}
	}
	
	
	public void delete(EntityDeathEvent d) 
	{
		if(d.getEntity() instanceof Mob) {
			Mob le = (Mob) d.getEntity();

			if(le.hasMetadata("untargetable")) {
				Player p = Bukkit.getPlayer(le.getMetadata("untargetable").get(0).asString());
				if(zombies.containsEntry(p.getUniqueId(),le)) {
					zombies.remove(p.getUniqueId(), le);
				}
			}
		}
	}
	
	
	public void delete(PlayerQuitEvent d) 
	{
		Player p = d.getPlayer();
		if(zombies.containsKey(p.getUniqueId())) {
			zombies.get(p.getUniqueId()).forEach(le -> {
				le.remove();
			});
			zombies.removeAll(p.getUniqueId());
		}
	}
	
	public void delete(PlayerTeleportEvent d) 
	{
		Player p = (Player) d.getPlayer();
		if(zombies.containsKey(p.getUniqueId())) {
			if(d.getFrom().getWorld() != d.getTo().getWorld()) {
				if(zombies.containsKey(p.getUniqueId())) {
					zombies.get(p.getUniqueId()).forEach(le -> {
						le.remove();
					});
					zombies.removeAll(p.getUniqueId());
				}
			}
		}
	}
	
	

	
	public void Resurrect(EntityDamageEvent d) 
	{
	    
		
		
		if(d.getEntity() instanceof Player) 
		{
			Player p = (Player)d.getEntity();
			double sec = 25;
			if((p.getHealth()-d.getDamage())<=0 && !Baron.containsKey(p.getUniqueId()) && d.getCause() != DamageCause.VOID) {
				
				if(Party.hasParty(p) && ClassData.pc.get(p.getUniqueId()) != 14)	{
					if(Party.getMembers(Party.getParty(p)).anyMatch(par -> ClassData.pc.get(Bukkit.getPlayer(par).getUniqueId())==14))
						{
							if(lncooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
					        {
					            double timer = (lncooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
					            if(!(timer < 0)) // if timer is still more then 0 or 0
					            {
					            	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					            		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("소생 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
					            	}
					            	else {
						            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to Resurrect").create());
					            	}
					            }
					            else // if timer is done
					            {
					                lncooldown.remove(p.getName()); // removing player from HashMap
					                res.computeIfAbsent(p, k -> 0);
					                res.computeIfPresent(p, (k,v) -> v+1);
					                d.setCancelled(true);
									p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 3, false, false));
									p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 4, false, false));
									p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 4, false, false));
									p.playEffect(EntityEffect.TOTEM_RESURRECT);
									if(p.getLocale().equalsIgnoreCase("ko_kr")) {
										p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[소생]").color(ChatColor.BOLD).color(ChatColor.GREEN).create());
									}
									else {
										p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Resurrect]").color(ChatColor.BOLD).color(ChatColor.GREEN).create());
									}
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                     		@Override
	        		                	public void run() 
	        			                {	
	                     					res.computeIfPresent(p, (k,v) -> v-1);
	                     					if(res.get(p)<0) {
	                     						res.remove(p);
	                     					}
	                     				}
	        	                	}, 80);
									lncooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
						        
					            }
					        }
					        else // if cooldown doesn't have players name in it
					        {
				                res.computeIfAbsent(p, k -> 0);
				                res.computeIfPresent(p, (k,v) -> v+1);
				                d.setCancelled(true);
								p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 3, false, false));
								p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 4, false, false));
								p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 4, false, false));
								p.playEffect(EntityEffect.TOTEM_RESURRECT);
								if(p.getLocale().equalsIgnoreCase("ko_kr")) {
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[소생]").color(ChatColor.BOLD).color(ChatColor.GREEN).create());
								}
								else {
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Resurrect]").color(ChatColor.BOLD).color(ChatColor.GREEN).create());
								}
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                     		@Override
        		                	public void run() 
        			                {	
                     					res.computeIfPresent(p, (k,v) -> v-1);
                     					if(res.get(p)<0) {
                     						res.remove(p);
                     					}
                     				}
		        	            }, 80);
								lncooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
					        }
						}
					}
				
				
				if(ClassData.pc.get(p.getUniqueId()) == 14) {
					if(lncooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            double timer = (lncooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("소생 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
			            	}
			            	else {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to Resurrect").create());
			            	}
			            }
			            else // if timer is done
			            {
			                lncooldown.remove(p.getName());
			                d.setCancelled(true);
							p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 3, false, false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 4, false, false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 4, false, false));
							p.playEffect(EntityEffect.TOTEM_RESURRECT);
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[소생]").color(ChatColor.BOLD).color(ChatColor.GREEN).create());
							}
							else {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Resurrect]").color(ChatColor.BOLD).color(ChatColor.GREEN).create());
							}
							lncooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
		                d.setCancelled(true);
						p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 3, false, false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 4, false, false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 4, false, false));
						p.playEffect(EntityEffect.TOTEM_RESURRECT);
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[소생]").color(ChatColor.BOLD).color(ChatColor.GREEN).create());
						}
						else {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Resurrect]").color(ChatColor.BOLD).color(ChatColor.GREEN).create());
						}
						lncooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			        }
				}
			}
		}
	}
	
	
	public void Resurrect(EntityResurrectEvent e) 
	{
		if(e.getEntity() instanceof Player) {
			Player p = (Player)e.getEntity();
			
			if(ClassData.pc.get(p.getUniqueId()) == 14)
			{
				e.setCancelled(true);
			}
		}
		
	}

	
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();

	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 14)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("HOE") || p.getInventory().getItemInMainHand().getType()== Material.TOTEM_OF_UNDYING)
			{
					e.setCancelled(true);
			}
		}
		
	}


	
}



