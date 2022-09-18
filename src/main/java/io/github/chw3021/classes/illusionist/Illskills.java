package io.github.chw3021.classes.illusionist;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.party.Party;
import io.github.chw3021.monsters.worldgen.IllChunkGenerator;
import io.github.chw3021.obtains.Obtained;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.google.common.collect.HashMultimap;
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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Difficulty;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Illskills extends Pak implements Serializable {
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 8919713269801972990L;
	private HashMap<String, Double> swcooldown = new HashMap<String, Double>();
	private HashMap<String, Double> sdcooldown = new HashMap<String, Double>();
	private HashMap<String, Double> cdcooldown = new HashMap<String, Double>();
	private HashMap<String, Double> frcooldown = new HashMap<String, Double>();
	private HashMap<String, Double> smcooldown = new HashMap<String, Double>();
	private HashMap<String, Double> stcooldown = new HashMap<String, Double>();
	private HashMap<String, Double> sultcooldown = new HashMap<String, Double>();
	private HashMap<String, Double> sult2cooldown = new HashMap<String, Double>();
	

	private HashMap<UUID, UUID> fkdl = new HashMap<UUID, UUID>();
	private HashMap<UUID, UUID> gm = new HashMap<UUID, UUID>();

	private HashMap<UUID, Item> magnif = new HashMap<UUID, Item>();
	private HashMap<UUID, Integer> magnift = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> juggl = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> jugglt = new HashMap<UUID, Integer>();
	

	private HashMap<UUID, Integer> shuff = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> shufft = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> minc = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> minct = new HashMap<UUID, Integer>();

	private HashMap<UUID, Location> encore = new HashMap<UUID, Location>();
	private HashMap<UUID, Integer> encoret = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> pentr = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> pentrt = new HashMap<UUID, Integer>();

	private HashMultimap<UUID, Integer> pts = HashMultimap.create();
	
	
	private HashMultimap<UUID, UUID> hypnosis = HashMultimap.create();
	private HashMap<UUID, Integer> hypnosist = new HashMap<UUID, Integer>();

	private HashMap<UUID, ArmorStand> dimdoll = new HashMap<UUID, ArmorStand>();
	private HashMap<UUID, Location> dimfirstloc = new HashMap<UUID, Location>();
	private HashMultimap<UUID, UUID> dimles = HashMultimap.create();
	private HashMap<UUID, Double> dimdolldmg = new HashMap<UUID, Double>();
	private HashMap<UUID, Integer> dimtask = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> dimcount = new HashMap<UUID, Integer>();

	
	private String path = new File("").getAbsolutePath();
	
	private IllSkillsData isd ;

	private static final Illskills instance = new Illskills ();
	public static Illskills getInstance()
	{
		return instance;
	}


	

		
	public void er(PluginEnableEvent ev) 
	{
		IllSkillsData i = new IllSkillsData(IllSkillsData.loadData(path +"/plugins/RPGskills/IllSkillsData.data"));
		isd = i;
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
			}
			
		}
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Illskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				IllSkillsData i = new IllSkillsData(IllSkillsData.loadData(path +"/plugins/RPGskills/IllSkillsData.data"));
				isd = i;
			}
			
		}
	}

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		IllSkillsData i = new IllSkillsData(IllSkillsData.loadData(path +"/plugins/RPGskills/IllSkillsData.data"));
		isd = i;
		
	}
	
	
	public void JackoLantern(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 11 && isd.JackoLantern.getOrDefault(p.getUniqueId(), 0)>=1)
		{
			if((p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && !(p.isSneaking()))
			{
				ev.setCancelled(true);
				
                Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
				
				if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sdcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("잭오랜턴 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
	                	}
	                	else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Jack O'Lantern").create());
	                	}
	                }
	                else // if timer is done
	                {
	                    sdcooldown.remove(p.getName()); // removing player from HashMap
	                    

	                	
						ItemStack jack = new ItemStack(Material.JACK_O_LANTERN, 1);	
						Item jo = p.getWorld().dropItemNaturally(l, jack);
						jo.setGlowing(true);
						jo.setPickupDelay(5000);
						jo.setInvulnerable(true);
						jo.setThrower(p.getUniqueId());
						jo.setMetadata("jo of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						jo.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
				        		jo.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, l, 1,1,1,1);
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
										atk1(0.68*(1+isd.JackoLantern.get(p.getUniqueId())*0.062), p, le);
									}
								}
			                	Stream<Entity> jos = p.getWorld().getEntities().stream().filter(i -> i.hasMetadata("jo of"+p.getName()));
			                	jos.forEach(i -> i.remove());
			                }
                	   }, 13); 

	                	if(magnift.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(magnift.get(p.getUniqueId()));
	                		magnift.remove(p.getUniqueId());
	                	}

	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						if(Proficiency.getpro(p)>=1) {
	    							magnif.putIfAbsent(p.getUniqueId(), jo);
	    						}
	    	                }
	    	            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						magnif.remove(p.getUniqueId());
	    	                }
	    	            }, 35); 
	                	magnift.put(p.getUniqueId(), task);
		                sdcooldown.put(p.getName(), System.currentTimeMillis()*1.0);
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {

	            	ItemStack jack = new ItemStack(Material.JACK_O_LANTERN, 1);	
					Item jo = p.getWorld().dropItemNaturally(l, jack);
					jo.setGlowing(true);
					jo.setPickupDelay(5000);
					jo.setInvulnerable(true);
					jo.setThrower(p.getUniqueId());
					jo.setMetadata("jo of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					jo.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					jo.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
			        		jo.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, l, 1,1,1,1);
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
									atk1(0.68*(1+isd.JackoLantern.get(p.getUniqueId())*0.062), p, le);
								}
							}
		                	Stream<Entity> jos = p.getWorld().getEntities().stream().filter(i -> i.hasMetadata("jo of"+p.getName()));
		                	jos.forEach(i -> i.remove());
		                }
            	   }, 13); 

                	if(magnift.containsKey(p.getUniqueId())) {
                		Bukkit.getScheduler().cancelTask(magnift.get(p.getUniqueId()));
                		magnift.remove(p.getUniqueId());
                	}

    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						if(Proficiency.getpro(p)>=1) {
    							magnif.putIfAbsent(p.getUniqueId(), jo);
    						}
    	                }
    	            }, 3); 

            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						magnif.remove(p.getUniqueId());
    	                }
    	            }, 35); 
                	magnift.put(p.getUniqueId(), task);
                    
	                sdcooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
	            }
			}
		}
	}
	

	
	
	public void Magnify(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 11&& magnif.containsKey(p.getUniqueId()))
		{
			if((p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && !(p.isSneaking()))
			{
				ev.setCancelled(true);
            	if(magnif.get(p.getUniqueId()).isValid()) {
            		magnif.get(p.getUniqueId()).remove();
	            	final Location ml = magnif.get(p.getUniqueId()).getLocation();
	
					
					p.playSound(ml, Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1f,2);
					p.playSound(ml, Sound.BLOCK_PISTON_EXTEND, 1f,0);
	
	
					FallingBlock fallingb = p.getWorld().spawnFallingBlock(ml, Material.JACK_O_LANTERN.createBlockData());
					fallingb.setInvulnerable(true);
					fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
					fallingb.setMetadata("joblock", new FixedMetadataValue(RMain.getInstance(),p.getName()));
					fallingb.setMetadata("jo of"+p.getName(), new FixedMetadataValue(RMain.getInstance(),p.getName()));
					fallingb.setVisualFire(true);
					fallingb.setDropItem(true);
					fallingb.setHurtEntities(true);
					fallingb.setGravity(false);

					fallingb.getWorld().spawnParticle(Particle.COMPOSTER, ml, 100,5,5,5);
					fallingb.getWorld().spawnParticle(Particle.GLOW, ml, 100,5,5,5);
					
                	for (Entity e : ml.getWorld().getNearbyEntities(ml, 5, 5, 5))
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
							atk1(0.7*(1+isd.JackoLantern.get(p.getUniqueId())*0.062), p, le);
							Holding.holding(p, le, 10l);
						}
					}

            	}

            	if(jugglt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(jugglt.get(p.getUniqueId()));
            		jugglt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							juggl.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						juggl.remove(p.getUniqueId());
	                }
	            }, 35); 
            	jugglt.put(p.getUniqueId(), task);

            	if(magnift.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(magnift.get(p.getUniqueId()));
            		magnift.remove(p.getUniqueId());
            	}
				magnif.remove(p.getUniqueId());
				

            	
			}
	                    
		}
	}
	
	public void Magnify(EntityDropItemEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(fallingb.hasMetadata("joblock")){
	        	ev.setCancelled(true);
	        }
		 }
	}


	
	public void Magnify(EntityDamageByEntityEvent ev) 
	{
		if(ev.getDamager() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getDamager();
	        if(fallingb.hasMetadata("joblock")){
	        	ev.setCancelled(true);
	        }
		 }
	}

	
	public void Magnify(EntityChangeBlockEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(fallingb.hasMetadata("joblock")){
	        	ev.setCancelled(true);
	        }
		 }
	}
	
	

	
	public void Jugglling(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 11 && juggl.containsKey(p.getUniqueId()))
		{
			if((p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && !(p.isSneaking()))
			{
				ev.setCancelled(true);

            	if(jugglt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(jugglt.get(p.getUniqueId()));
            		jugglt.remove(p.getUniqueId());
            	}
				juggl.remove(p.getUniqueId());


				final Location tl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(3));
				final Vector tv = tl.getDirection();
				
				ArrayList<Location> line = new ArrayList<>();

				for(double an = 0; an <Math.PI*4; an += Math.PI/5.25) {
					Location cl = tl.clone().add(tl.clone().getDirection().rotateAroundY(Math.PI/2).normalize().rotateAroundAxis(tv, an).multiply(2.6));
					line.add(cl);
				}

				p.playSound(tl, Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1f,2);
				
				
				Snowball sn1 = p.getWorld().spawn(tl, Snowball.class);
				Snowball sn2 = p.getWorld().spawn(tl, Snowball.class);
				Snowball sn3 = p.getWorld().spawn(tl, Snowball.class);

	            sn1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	            sn1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	            sn1.setShooter(p);
	            sn1.setGravity(false);
	            sn1.setItem(new ItemStack(Material.CARVED_PUMPKIN));

	            sn2.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	            sn2.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	            sn2.setShooter(p);
	            sn2.setGravity(false);
	            sn2.setItem(new ItemStack(Material.PUMPKIN));

	            sn3.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	            sn3.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	            sn3.setShooter(p);
	            sn3.setGravity(false);
	            sn3.setItem(new ItemStack(Material.JACK_O_LANTERN));
	            
	            /*
				for(double an = 0; an <Math.PI*4; an += Math.PI/5) {
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	Location aal = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().clone().multiply(3));
		                    Vector aa = aal.getDirection();
		                    Vector w = p.getEyeLocation().getDirection().clone().rotateAroundAxis(aa.clone(),atd.getAndAdd(Math.PI/90));
		                	Vector r1 = aa.clone().rotateAroundY(Math.PI/2).normalize().multiply(2).rotateAroundAxis(aa.clone(),atd.get()).normalize().multiply(2);
		                    Vector v1 = w.clone().multiply(r1.clone());
		                    sn1.setVelocity(v1);
		                	Vector r2 = aa.clone().rotateAroundY(-Math.PI/2).normalize().multiply(2).rotateAroundAxis(aa.clone(),atd.get()).normalize().multiply(2);
		                    Vector v2 = w.clone().multiply(r2.clone());
		                    sn2.setVelocity(v2);
		                	Vector r3 = aa.clone().rotateAroundY(Math.PI/2).normalize().multiply(1).rotateAroundAxis(aa.clone(),atd.get()).normalize().multiply(1);
		                    Vector v3 = w.clone().multiply(r3.clone());
		                    sn3.setVelocity(v3);
		                }
		            }, k.incrementAndGet()); 	                    	
                }*/

				AtomicInteger j = new AtomicInteger();
				line.forEach(l ->{
					
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {	
		                	
		                	int li =line.indexOf(l)+7;
		                	int li2 =line.indexOf(l)+14;
		                	if(li >= line.size()) {
		                		li = li-line.size();
		                	}
		                	if(li2 >= line.size()) {
		                		li2 = li2-line.size();
		                	}
		                	
							l.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, l, 10,0.1,0.1,0.1, new Particle.DustTransition(Color.LIME, Color.MAROON, 2));
							l.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, line.get(li), 10,0.1,0.1,0.1, new Particle.DustTransition(Color.RED, Color.BLUE, 2));
							l.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, line.get(li2), 10,0.1,0.1,0.1, new Particle.DustTransition(Color.BLACK, Color.WHITE, 2));
							p.playSound(l, Sound.ENTITY_DROWNED_SHOOT,0.2f, 1.6f);
							
							for(Entity e : tl.getWorld().getNearbyEntities(tl,5, 5, 5)) {
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
									atk1(0.15*(1+isd.JackoLantern.get(p.getUniqueId())*0.022), p, le);
									le.teleport(l);
									Holding.holding(p, le, 5l);
								}
							}
		                }
		            }, j.incrementAndGet()); 	                    	
                });
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
		            	sn1.remove();
		            	sn2.remove();
		            	sn3.remove();
	                }
	            }, 35); 	 
				
			}
	                    
		}
	}
	
	
	
	
	@SuppressWarnings("deprecation")
	public void Paradox(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec = 13*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
        

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 11 && isd.Paradox.getOrDefault(p.getUniqueId(), 0)>=1) {
		if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR||ac==Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD)
			{
				
				ev.setCancelled(true);
				if(swcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (swcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("역설 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
	                	}
	                	else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Paradox").create());
	                	}
	                }
	                else // if timer is done
	                {
	                    swcooldown.remove(p.getName()); // removing player from HashMap
	                    
	                    p.playSound(p.getLocation(), Sound.BLOCK_CHEST_LOCKED, 1,2);
	                    p.playSound(p.getLocation(), Sound.ITEM_LODESTONE_COMPASS_LOCK, 1,2);
	                    p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1,2);
						p.sendTitle(ChatColor.MAGIC + "AAAAAA", null);
						p.setHealth(p.getMaxHealth());
	                	swcooldown.remove(p.getName());
	                	sdcooldown.remove(p.getName());
	                	cdcooldown.remove(p.getName());
	                	frcooldown.remove(p.getName());
	                	smcooldown.remove(p.getName());
	                	stcooldown.remove(p.getName());
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
		        		p.setFireTicks(0);
	                	
		        		if(gm.containsKey(p.getUniqueId())) {
		        			if(Bukkit.getEntity(gm.get(p.getUniqueId())) != null) {
		        				ArmorStand as = (ArmorStand) Bukkit.getEntity(gm.get(p.getUniqueId()));

								as.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, as.getLocation(), 2, 1, 1, 1);
								p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
								
								for (Entity e : as.getWorld().getNearbyEntities(as.getLocation(), 4, 5, 4))
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
										atk1(0.66*(1+isd.Trick.get(p.getUniqueId())*0.0535), p, le);
										p.setCooldown(Material.YELLOW_TERRACOTTA, 1);
					                    if(Proficiency.getpro(p)>=2 && le.isValid() && !le.isDead()) {
					                    	if(hypnosist.containsKey(le.getUniqueId())) {
					                    		Bukkit.getScheduler().cancelTask(hypnosist.get(le.getUniqueId()));
					        	        		hypnosist.remove(le.getUniqueId());
					                    	}
											int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() 
								                {
								                	Holding.holding(p, le, 10l);
									        		le.getWorld().spawnParticle(Particle.SNEEZE, le.getEyeLocation(), 10,1,1,1);
									        		if(le.isDead() || !le.isValid()) {
								                    	if(hypnosist.containsKey(le.getUniqueId())) {
								                    		Bukkit.getScheduler().cancelTask(hypnosist.get(le.getUniqueId()));
								        	        		hypnosist.remove(le.getUniqueId());
								                    	}
									        		}
								                }
											}, 0, 10); 
											hypnosist.put(le.getUniqueId(), task);
											hypnosis.put(p.getUniqueId(), le.getUniqueId());
					                    }
									}
								}
		        				as.remove();
		        				gm.remove(p.getUniqueId());
		        			}
		        		}
	                    
		                swcooldown.put(p.getName(), (double) System.currentTimeMillis()); // adding players name + current system time in miliseconds
		                
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {

                    p.playSound(p.getLocation(), Sound.BLOCK_CHEST_LOCKED, 1,2);
                    p.playSound(p.getLocation(), Sound.ITEM_LODESTONE_COMPASS_LOCK, 1,2);
                    p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1,2);
					p.sendTitle(ChatColor.MAGIC + "AAAAAA", null);
					p.setHealth(p.getMaxHealth());
                	swcooldown.remove(p.getName());
                	sdcooldown.remove(p.getName());
                	cdcooldown.remove(p.getName());
                	frcooldown.remove(p.getName());
                	smcooldown.remove(p.getName());
                	stcooldown.remove(p.getName());
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
	        		p.setFireTicks(0);
                	
	        		if(gm.containsKey(p.getUniqueId())) {
	        			if(Bukkit.getEntity(gm.get(p.getUniqueId())) != null) {
	        				ArmorStand as = (ArmorStand) Bukkit.getEntity(gm.get(p.getUniqueId()));

							as.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, as.getLocation(), 2, 1, 1, 1);
							p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
							
							for (Entity e : as.getWorld().getNearbyEntities(as.getLocation(), 4, 5, 4))
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
									atk1(0.66*(1+isd.Trick.get(p.getUniqueId())*0.0535), p, le);
									p.setCooldown(Material.YELLOW_TERRACOTTA, 1);
				                    if(Proficiency.getpro(p)>=2 && le.isValid() && !le.isDead()) {
				                    	
				                    	if(hypnosist.containsKey(le.getUniqueId())) {
				                    		Bukkit.getScheduler().cancelTask(hypnosist.get(le.getUniqueId()));
				        	        		hypnosist.remove(le.getUniqueId());
				                    	}
										int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
							                	Holding.holding(p, le, 10l);
								        		le.getWorld().spawnParticle(Particle.SNEEZE, le.getEyeLocation(), 10,1,1,1);
						                    	if(hypnosist.containsKey(le.getUniqueId())) {
						                    		Bukkit.getScheduler().cancelTask(hypnosist.get(le.getUniqueId()));
						        	        		hypnosist.remove(le.getUniqueId());
						                    	}
							                }
										}, 0, 10); 
										hypnosist.put(le.getUniqueId(), task);
										hypnosis.put(p.getUniqueId(), le.getUniqueId());
				                    }
								}
							}
	        				as.remove();
	        				gm.remove(p.getUniqueId());
	        			}
	        		}
	                swcooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
	                
	            }
			}
		}
		}
	}

	
	public void Hypnosis(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
			Player p = (Player) d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			if(ClassData.pc.get(p.getUniqueId()) == 11) {
	        	if(hypnosist.containsKey(le.getUniqueId())) {
	        		Bukkit.getScheduler().cancelTask(hypnosist.get(le.getUniqueId()));
	        		hypnosist.remove(le.getUniqueId());
	        	}
				if(hypnosis.containsEntry(p.getUniqueId(), le.getUniqueId())) {
					d.setDamage(d.getDamage()*1.15);
					hypnosis.remove(p.getUniqueId(), le.getUniqueId());
					
				}
			}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity) 
		{
			Projectile ar = (Projectile)d.getDamager();
	
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
				LivingEntity le = (LivingEntity) d.getEntity();
				if(ClassData.pc.get(p.getUniqueId()) == 11) {
		        	if(hypnosist.containsKey(le.getUniqueId())) {
		        		Bukkit.getScheduler().cancelTask(hypnosist.get(le.getUniqueId()));
		        		hypnosist.remove(le.getUniqueId());
		        	}
					if(hypnosis.containsEntry(p.getUniqueId(), le.getUniqueId())) {
						d.setDamage(d.getDamage()*1.15);
						hypnosis.remove(p.getUniqueId(), le.getUniqueId());
						
					}
				}
			}
		}
	}
	
	
	public void FakeDoll(PlayerInteractEntityEvent ev)
	{
		if(ev.getRightClicked() instanceof ArmorStand && ev.getRightClicked().hasMetadata("fake")){
			ev.setCancelled(true);
			return;
		}
	}

	public void FakeDoll(PlayerArmorStandManipulateEvent ev)
	{
		if(ev.getRightClicked() instanceof ArmorStand && ev.getRightClicked().hasMetadata("fake")){
			ev.setCancelled(true);
			return;
		}
	}
	
	@SuppressWarnings("deprecation")
	public void FakeDoll(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

        
		if(ClassData.pc.get(p.getUniqueId()) == 11 && isd.FakeDoll.getOrDefault(p.getUniqueId(),0)>=1) {
			if((p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && p.isSneaking())
			{
				ev.setCancelled(true);
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (cdcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("허수아비 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
		                	}
		                	else {
			                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use FakeDoll").create());
		                	}
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap
		                    ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
		                    as.setCustomName(p.getName());
		                    as.setArms(true);
		                    as.setBasePlate(false);
		                    as.setInvulnerable(true);
		                    as.setInvisible(true);
		                    as.setGravity(false);
		                    as.setBoots(p.getInventory().getBoots());
		                    as.setHelmet(p.getInventory().getHelmet());
		                    as.setChestplate(p.getInventory().getChestplate());
		                    as.setLeggings(p.getInventory().getLeggings());
		                    as.setCanPickupItems(false);
		                    as.setItemInHand(p.getInventory().getItemInMainHand());
		                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		    	            as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		                    as.setArrowsInBody(p.getArrowsInBody());
		                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 110, 1, false, false));
		                    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 110, 1, false, false));
							p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1, 2);
		                    for(Entity e : as.getNearbyEntities(10, 10, 10)) {

								if(!(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
			                    	if(e instanceof Mob) {
			                    		Mob le = (Mob)e;
			                    		le.setTarget(as);
			                    	}
								}
		                    }
		                    if(Proficiency.getpro(p)>=1) {
			                    p.setCooldown(Material.ARMOR_STAND, 3);
			                    fkdl.put(p.getUniqueId(), as.getUniqueId());
			                    gm.put(p.getUniqueId(), as.getUniqueId());
		                    }
		                    
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	as.remove();
				                }
	                	   }, 100);
		                    
							cdcooldown.put(p.getName(), System.currentTimeMillis()*1.0); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
	                    as.setCustomName(p.getName());
	                    as.setArms(true);
	                    as.setBasePlate(false);
	                    as.setInvulnerable(true);
	                    as.setInvisible(true);
	                    as.setGravity(false);
	                    as.setBoots(p.getInventory().getBoots());
	                    as.setHelmet(p.getInventory().getHelmet());
	                    as.setChestplate(p.getInventory().getChestplate());
	                    as.setLeggings(p.getInventory().getLeggings());
	                    as.setCanPickupItems(false);
	                    as.setItemInHand(p.getInventory().getItemInMainHand());
	                    as.setArrowsInBody(p.getArrowsInBody());
	                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	    	            as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 110, 1, false, false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 110, 1, false, false));
						p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1, 2);
	                    for(Entity e : as.getNearbyEntities(10, 10, 10)) {

							if(!(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
		                    	if(e instanceof Mob) {
		                    		Mob le = (Mob)e;
		                    		le.setTarget(as);
		                    	}
							}
	                    }
	                    if(Proficiency.getpro(p)>=1) {
		                    p.setCooldown(Material.ARMOR_STAND, 3);
		                    fkdl.put(p.getUniqueId(), as.getUniqueId());
		                    gm.put(p.getUniqueId(), as.getUniqueId());
	                    }
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	as.remove();
			                }
						}, 100); 
	                    
		                cdcooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
		            }
				}
		}
	
	}

	
	
	public void Change(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 11 && isd.FakeDoll.get(p.getUniqueId())>=1 && fkdl.containsKey(p.getUniqueId()) && cdcooldown.containsKey(p.getName())) {
			if((p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && p.isSneaking() && (Bukkit.getEntity(fkdl.get(p.getUniqueId())) != null) && !p.hasCooldown(Material.ARMOR_STAND))
			{
				ev.setCancelled(true);
				if(pts.containsKey(p.getUniqueId())) {
					pts.get(p.getUniqueId()).forEach(t -> Bukkit.getScheduler().cancelTask(t));
					pts.removeAll(p.getUniqueId());
				}
				p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1, 2);
				Location pl = p.getLocation();
				ArmorStand as = (ArmorStand) Bukkit.getEntity(fkdl.get(p.getUniqueId()));
				Location al = as.getLocation();
				
				p.teleport(al);
				as.teleport(pl);
                p.setCooldown(Material.ARMOR_STAND, 3);
                fkdl.remove(p.getUniqueId());
			}
		                    
		}
	
	}
	
	
	public void Distortion(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec = 10*((1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d));

        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 11 && isd.Distortion.getOrDefault(p.getUniqueId(), 0)>=1) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD)
			{
				

				ev.setCancelled(true);
				if(frcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (frcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("왜곡 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
	                	}
	                	else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Distortion").create());
	                	}
	                }
	                else // if timer is done
	                {
	                    frcooldown.remove(p.getName()); // removing player from HashMap
	                    
	                	if(shufft.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(shufft.get(p.getUniqueId()));
	                		shufft.remove(p.getUniqueId());
	                	}

	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						if(Proficiency.getpro(p)>=1) {
	    							shuff.putIfAbsent(p.getUniqueId(), 0);
	    						}
	    	                }
	    	            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						shuff.remove(p.getUniqueId());
	    	                }
	    	            }, 35); 
	                	shufft.put(p.getUniqueId(), task);
	                	
	                	
						p.damage(p.getHealth()*0.4);
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30, 4, false, false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 2, false, false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 80, 80, false, false));
						p.getWorld().spawnParticle(Particle.SPELL_INSTANT, p.getLocation(), 555, 4,3,4); 
						p.getWorld().spawnParticle(Particle.SPELL, p.getLocation(), 200, 2, 2, 2);
						p.getWorld().spawnParticle(Particle.TOWN_AURA, p.getLocation(), 200, 2, 2, 2);
						for (Entity a : p.getWorld().getNearbyEntities(p.getLocation(), 4, 4, 4))
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
								atk1(1.626*(1+isd.Distortion.get(p.getUniqueId())*0.0835), p, le);
			                    le.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 180, 100, false, false));
								
									
							}
						}
						p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 1.0f, 2f);

						frcooldown.put(p.getName(), System.currentTimeMillis()*1.0);  
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    
                	if(shufft.containsKey(p.getUniqueId())) {
                		Bukkit.getScheduler().cancelTask(shufft.get(p.getUniqueId()));
                		shufft.remove(p.getUniqueId());
                	}

    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						if(Proficiency.getpro(p)>=1) {
    							shuff.putIfAbsent(p.getUniqueId(), 0);
    						}
    	                }
    	            }, 3); 

            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						shuff.remove(p.getUniqueId());
    	                }
    	            }, 35); 
                	shufft.put(p.getUniqueId(), task);
                	
                	
					p.damage(p.getHealth()*0.4);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30, 4, false, false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 2, false, false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 80, 80, false, false));
					p.getWorld().spawnParticle(Particle.SPELL_INSTANT, p.getLocation(), 555, 6,3,6); 
					p.getWorld().spawnParticle(Particle.SPELL, p.getLocation(), 200, 2, 2, 2);
					p.getWorld().spawnParticle(Particle.TOWN_AURA, p.getLocation(), 200, 2, 2, 2);
					for (Entity a : p.getWorld().getNearbyEntities(p.getLocation(), 4, 4, 4))
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
							atk1(1.626*(1+isd.Distortion.get(p.getUniqueId())*0.0835), p, le);
		                    le.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 180, 100, false, false));
								
						}
					}
					p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 1.0f, 2f);

					frcooldown.put(p.getName(), System.currentTimeMillis()*1.0);  
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	

	
	
	public void Shuffle(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 11 && shuff.containsKey(p.getUniqueId())) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD)
			{
				ev.setCancelled(true);
				
            	if(shufft.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(shufft.get(p.getUniqueId()));
            		shufft.remove(p.getUniqueId());
            	}
				shuff.remove(p.getUniqueId());
				


            	if(minct.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(minct.get(p.getUniqueId()));
            		minct.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							minc.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						minc.remove(p.getUniqueId());
	                }
	            }, 35); 
            	minct.put(p.getUniqueId(), task);

						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 555, 4,3,4, Material.BLACK_GLAZED_TERRACOTTA.createBlockData()); 
						p.getWorld().spawnParticle(Particle.SPELL_MOB_AMBIENT, p.getLocation(), 555, 4,3,4); 

	                    HashSet<LivingEntity> les = new HashSet<>();
	                    ArrayList<Location> lesl = new ArrayList<>();
	                    AtomicInteger j = new AtomicInteger();
	                    
		            	for(Entity e : p.getNearbyEntities(4, 4, 4)) {
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
		                    if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
							{
								LivingEntity le = (LivingEntity)e;
								final Location lel = le.getLocation();
								les.add(le);
								lesl.add(lel);
							}
	                    }
						les.forEach(e -> {
							LivingEntity le = (LivingEntity)e;
							atk1(1.1*(1+isd.Distortion.get(p.getUniqueId())*0.05), p, le);
		                    le.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 180, 100, false, false));
		                    
							if(j.get()+1>=lesl.size()) {
								le.teleport(lesl.get(0));
							}
							else {
								le.teleport(lesl.get(j.incrementAndGet()));
							}
							Holding.holding(p, le, 10l);
						});
						p.playSound(p.getLocation(), Sound.ENTITY_PUFFER_FISH_BLOW_OUT, 1.0f, 0f);
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}

	
	
	public void MindControl(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 11 && minc.containsKey(p.getUniqueId())) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD)
			{
				ev.setCancelled(true);
				
            	if(minct.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(minct.get(p.getUniqueId()));
            		minct.remove(p.getUniqueId());
            	}
				minc.remove(p.getUniqueId());

				p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p.getLocation(), 600, 4,3,4); 
				p.getWorld().spawnParticle(Particle.WAX_ON, p.getLocation(), 150, 2,1,2); 
				p.getWorld().spawnParticle(Particle.SMALL_FLAME, p.getLocation(), 600, 3,2,3); 

                HashSet<LivingEntity> les = new HashSet<>();
                
            	for(Entity e : p.getNearbyEntities(7, 7, 7)) {
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
                    if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
					{
                    	LivingEntity le = (LivingEntity)e;
						les.add(le);
					}
                }
				les.forEach(e -> {
					LivingEntity le = (LivingEntity)e;
					atk1(1.1*(1+isd.Distortion.get(p.getUniqueId())*0.05), p, le);
                    le.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 180, 100, false, false));
					if(le instanceof Mob) {
						Mob m = (Mob)le;
						if(les.iterator().hasNext()) {
							m.setTarget((Mob) les.iterator().next());
						}
					}
					
				});
				p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 1.0f, 0f);
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	
	
	public void Switch(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
		double sec =2*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		Location l = p.getLocation();
		Location el =le.getLocation();
        
		

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 11 && isd.Switch.getOrDefault(p.getUniqueId(), 0)>=1 && !le.hasMetadata("fake") && !le.hasMetadata("portal")) {
				if((p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && (p.isSneaking()) && !(p.hasCooldown(Material.YELLOW_TERRACOTTA)))
				{
				if(stcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            double timer = (stcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
		            	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("바꿔치기 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
		            	}
		            	else {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Switch").create());
		            	}
			        }
		            else // if timer is done
		            {
		                stcooldown.remove(p.getName()); // removing player from HashMap
						if(Proficiency.getpro(p)>=1) {
							Holding.superholding(p, le, 15l);
						}
						if(Proficiency.getpro(p)>=2) {
		                    for(Entity e : le.getNearbyEntities(5, 5, 5)) {

								if(!(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
			                    	if(e instanceof Mob) {
			                    		Mob m = (Mob)e;
			                    		m.setTarget(le);
			                    	}
								}
		                    }
						}
						Holding.invur(p, 5l);
						p.teleport(el);
						le.teleport(l);	
						p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 1.0f, 2f);
						p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 0f);
			            stcooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
					if(Proficiency.getpro(p)>=1) {
						Holding.superholding(p, le, 15l);
					}
					if(Proficiency.getpro(p)>=2) {
	                    for(Entity e : le.getNearbyEntities(5, 5, 5)) {

							if(!(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
		                    	if(e instanceof Mob) {
		                    		Mob m = (Mob)e;
		                    		m.setTarget(le);
		                    	}
							}
	                    }
					}
					Holding.invur(p, 5l);
					p.teleport(el);
					le.teleport(l);	
					p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 1.0f, 2f);
					p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 0f);
		            stcooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
		        }
			}
		}
		}
	}
	

	
	@SuppressWarnings("deprecation")
	public void Trick(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 11 && isd.Trick.getOrDefault(p.getUniqueId(), 0)>=1) {
			final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
			if(!l.getBlock().isPassable()) {
            l.setY(l.getY()+1);}
			l.setDirection(p.getLocation().getDirection());
			if(l.getBlock().isPassable()) {
			if((ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK)&& ac != Action.RIGHT_CLICK_AIR&& ac != Action.RIGHT_CLICK_BLOCK && !p.hasCooldown(Material.WARPED_BUTTON))
			{
                Location dam = p.getLocation();
				
				if((p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && !(p.isSneaking()) && !(p.isOnGround()) )
					{
					ev.setCancelled(true);
					if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            double timer = (smcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("속임수 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
			            	}
			            	else {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Trick").create());
			            	}
				        }
			            else // if timer is done
			            {
			                smcooldown.remove(p.getName()); // removing player from HashMapLocation dam = p.getLocation();
							p.setCooldown(Material.STICK, 1);
							p.swingMainHand();
							p.swingOffHand();
							p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, dam, 2, 1, 1, 1);
							p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
							p.teleport(l);
		                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 40, 1, false, false));
							for (Entity e : p.getWorld().getNearbyEntities(dam, 4, 5, 4))
							{
								if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
								{
									LivingEntity le = (LivingEntity)e;
									atk1(0.66*(1+isd.Trick.get(p.getUniqueId())*0.0535), p, le);
								}
							}
			                
		                	if(encoret.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(encoret.get(p.getUniqueId()));
		                		encoret.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							encore.putIfAbsent(p.getUniqueId(), l);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						encore.remove(p.getUniqueId());
		    	                }
		    	            }, 35); 
		                	encoret.put(p.getUniqueId(), task);
		                	
				            smcooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
						p.setCooldown(Material.STICK, 1);
						p.swingMainHand();
						p.swingOffHand();
						p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, dam, 2, 1, 1, 1);
						p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
						p.teleport(l);
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 40, 1, false, false));
						for (Entity e : p.getWorld().getNearbyEntities(dam, 4, 5, 4))
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
								atk1(0.66*(1+isd.Trick.get(p.getUniqueId())*0.0535), p, le);
							}
						}
		                
	                	if(encoret.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(encoret.get(p.getUniqueId()));
	                		encoret.remove(p.getUniqueId());
	                	}

	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						if(Proficiency.getpro(p)>=1) {
	    							encore.putIfAbsent(p.getUniqueId(), l);
	    						}
	    	                }
	    	            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						encore.remove(p.getUniqueId());
	    	                }
	    	            }, 35); 
	                	encoret.put(p.getUniqueId(), task);
	                	
			            smcooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
			        }
				}
			}
			}
		}
	}
	


	
	@SuppressWarnings("deprecation")
	public void Encore(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 11 && encore.containsKey(p.getUniqueId())) {
			if((ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK)&& ac != Action.RIGHT_CLICK_AIR&& ac != Action.RIGHT_CLICK_BLOCK&& !p.hasCooldown(Material.WARPED_BUTTON))
			{
                final Location dam = encore.get(p.getUniqueId());
				
				if((p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && !(p.isSneaking()) && !(p.isOnGround()) )
					{
					ev.setCancelled(true);
					
	            	if(encoret.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(encoret.get(p.getUniqueId()));
	            		encoret.remove(p.getUniqueId());
	            	}
					encore.remove(p.getUniqueId());


	            	if(pentrt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(pentrt.get(p.getUniqueId()));
	            		pentrt.remove(p.getUniqueId());
	            	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=2) {
								pentr.putIfAbsent(p.getUniqueId(), 0);
							}
		                }
		            }, 3); 

	        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							pentr.remove(p.getUniqueId());
		                }
		            }, 35); 
	            	pentrt.put(p.getUniqueId(), task);

	                Firework fr = (Firework) p.getWorld().spawnEntity(dam, EntityType.FIREWORK);
                    fr.setShotAtAngle(true);
                    fr.setVelocity(fr.getVelocity().zero());
                    fr.setShooter(p);
                    fr.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
        			FireworkEffect effect = FireworkEffect.builder()
                                .with(Type.STAR)
                                .withColor(Color.TEAL, Color.FUCHSIA, Color.AQUA)
                                .flicker(true)
                                .trail(true)
                                .build();
                    FireworkMeta meta = fr.getFireworkMeta();
                    meta.setPower(0);
                    meta.addEffect(effect);
                    fr.setFireworkMeta(meta);
                    fr.detonate();
                    
							p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, dam, 2, 1, 1, 1);
							p.getWorld().spawnParticle(Particle.SPELL, dam, 200, 2, 2, 2);
							p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 0.6f, 0.6f);
							p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 0.6f, 1.1f);
		                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 40, 1, false, false));
							for (Entity e : dam.getWorld().getNearbyEntities(dam, 4, 5, 4))
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
									atk1(0.8*(1+isd.Trick.get(p.getUniqueId())*0.0535), p, le);
								}
							}
					}
			}
		}
	}
	

	
	@SuppressWarnings("deprecation")
	public void Penetration(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 11&& pentr.containsKey(p.getUniqueId())) {
			if((ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK)&& ac != Action.RIGHT_CLICK_AIR&& ac != Action.RIGHT_CLICK_BLOCK&& !p.hasCooldown(Material.WARPED_BUTTON))
			{
				
				if((p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && !(p.isSneaking()) && !(p.isOnGround()) )
					{
						ev.setCancelled(true);
						
		            	if(pentrt.containsKey(p.getUniqueId())) {
		            		Bukkit.getScheduler().cancelTask(pentrt.get(p.getUniqueId()));
		            		pentrt.remove(p.getUniqueId());
		            	}
						pentr.remove(p.getUniqueId());

						if(pts.containsKey(p.getUniqueId())) {
							pts.get(p.getUniqueId()).forEach(t -> Bukkit.getScheduler().cancelTask(t));
							pts.removeAll(p.getUniqueId());
						}
						
						ArrayList<Location> line = new ArrayList<Location>();
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 1.0f, 0f);
	                    
	                    AtomicInteger j = new AtomicInteger();
	                    
	                    for(double d = 0.1; d <= 5; d += 0.23) {
							Location pl = p.getEyeLocation().clone().add(p.getEyeLocation().getDirection().normalize().multiply(d));
							if(!pl.getBlock().isPassable()) {
								break;
							}
							line.add(pl);
	                    }
	                    line.forEach(l ->  {
	                    	int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                    p.playSound(p.getLocation(), Sound.ENTITY_FOX_TELEPORT, 0.12f, 2f);
		             				p.getWorld().spawnParticle(Particle.SPELL_MOB, l, 10,1,1,1);
		             				p.getWorld().spawnParticle(Particle.SPELL_WITCH, l, 10,2,2,2);
		             				p.getWorld().spawnParticle(Particle.PORTAL, l, 10,2,2,2);
	             					p.teleport(l);
									for(Entity e : l.getWorld().getNearbyEntities(l,4, 4.5, 4)) {
										if(e != p&&e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
											LivingEntity le = (LivingEntity)e;
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
											atk1(0.24*(1+isd.Trick.get(p.getUniqueId())*0.035), p, le);
			             					Holding.superholding(p, le, 15l);
										}
									}
				                }
                    		}, j.incrementAndGet()/2); 
	                    	pts.put(p.getUniqueId(), task);
	                    });
					}
			}
		}
	}
	
	
	
	
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item i = ev.getItemDrop();
		ItemStack is = i.getItemStack();
		
			if(ClassData.pc.get(p.getUniqueId()) == 11 && (is.getType() == Material.BLAZE_ROD) && p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
				p.setCooldown(Material.WARPED_BUTTON, 3);
				ev.setCancelled(true);
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sultcooldown.get(p.getName())/1000d + 75/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d)) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("공허의 환상 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
	                	}
	                	else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use The Void").create());
	                	}
	                }
	                else // if timer is done
	                {
	                    sultcooldown.remove(p.getName()); // removing player from HashMap
						p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_BLINDNESS, 1, 0);
		        		p.getWorld().spawnParticle(Particle.ASH, p.getLocation(), 1000,6,6,6,0);
		        		Holding.invur(p, 100l);
	                    for(Entity e : p.getNearbyEntities(6.5, 6.5, 6.5)) {
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
												return;
											}
										}
									}
									ArrayList<Location> line = new ArrayList<Location>();
				                    AtomicInteger j = new AtomicInteger(0);
				                    AtomicInteger b = new AtomicInteger(0);
				                    for(double d = 1.1; d > 0; d -= 0.05) {
					                    Location lel = le.getLocation();
					                    Location lel2 = le.getLocation();
										lel.add(0,d,0);
										lel2.add(0, -d, 0);
										line.add(lel2);
										line.add(lel);
				                    }
									line.forEach(l -> {
					                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() 
								                {
								                	le.teleport(line.get(b.getAndIncrement()));
													p.playSound(l, Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 1.0f, 2f);
									        		p.getWorld().spawnParticle(Particle.WARPED_SPORE, l, 10,1,1,1,0);
									        		p.getWorld().spawnParticle(Particle.PORTAL, l, 10,1,1,1,0);
									        		p.getWorld().spawnParticle(Particle.FALLING_NECTAR, l, 10,1,1,1,0);
								                }
					                	   }, j.incrementAndGet()); 
									}	);

				                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
												atk1(15.6, p, le);
							                }
				                	   }, j.incrementAndGet()); 
								}
							}
	                    }
		                sultcooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
					p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_BLINDNESS, 1, 0);
	        		p.getWorld().spawnParticle(Particle.ASH, p.getLocation(), 1000,6,6,6,0);
	        		Holding.invur(p, 100l);
	            	for(Entity e : p.getNearbyEntities(6.5, 6.5, 6.5)) {
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
											return;
										}
									}
								}
								ArrayList<Location> line = new ArrayList<Location>();
			                    AtomicInteger j = new AtomicInteger(0);
			                    AtomicInteger b = new AtomicInteger(0);
			                    for(double d = 1.1; d > 0; d -= 0.05) {
				                    Location lel = le.getLocation();
				                    Location lel2 = le.getLocation();
									lel.add(0,d,0);
									lel2.add(0, -d, 0);
									line.add(lel2);
									line.add(lel);
			                    }
								line.forEach(l -> {
				                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
							                	le.teleport(line.get(b.getAndIncrement()));
												p.playSound(l, Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 1.0f, 2f);
								        		p.getWorld().spawnParticle(Particle.WARPED_SPORE, l, 10,1,1,1,0);
								        		p.getWorld().spawnParticle(Particle.PORTAL, l, 10,1,1,1,0);
								        		p.getWorld().spawnParticle(Particle.FALLING_NECTAR, l, 10,1,1,1,0);
							                }
				                	   }, j.incrementAndGet()); 
								}	);

			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
											atk1(15.6, p, le);
						                }
			                	   }, j.incrementAndGet()); 
							}
						}
                    }
	                sultcooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
	            }
			}
    }

	
	
	public void ULT2(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item i = ev.getItemDrop();
		ItemStack is = i.getItemStack();
        
		
			if(ClassData.pc.get(p.getUniqueId()) == 11 && (is.getType() == Material.BLAZE_ROD) && !p.isSneaking()&& p.isSprinting()&& Proficiency.getpro(p) >=2 && !p.hasCooldown(Material.STICK)) 
			{
				p.setCooldown(Material.WARPED_BUTTON, 3);
				ev.setCancelled(true);
				if(sult2cooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sult2cooldown.get(p.getName())/1000d + 80*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d)) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("조작된 차원 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
	                	}
	                	else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to Warp Fake Dimension").create());
	                	}
	                }
	                else // if timer is done
	                {
	                    sult2cooldown.remove(p.getName()); // removing player from HashMap

	                    final Location pfl = p.getLocation();
	                    dimfirstloc.put(p.getUniqueId(), pfl);
	                    
		        		p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation(), 100,3,3,3,0);
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 140, 1, false, false));
	                    
		            	for(Entity e : p.getNearbyEntities(7, 7, 7)) {
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
		                    if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
							{ 
								LivingEntity le = (LivingEntity)e;
								le.setRemoveWhenFarAway(false);
								dimles.put(p.getUniqueId(), le.getUniqueId());
							}
	                    }
		                Holding.fly(p, 100l);
		    	        
		            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								World fkw = Bukkit.getWorld("FakeDimension");
								Location fkl = new Location(fkw, pfl.getX(), pfl.getY(), pfl.getZ());
								p.teleport(fkl);
				                Holding.fly(p, 100l);
								p.playSound(p.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 0.5f, 2f);

								Holding.invur(p,130l);
			                    ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
			                    if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				                    as.setCustomName(ChatColor.BOLD + p.getName()+"의 조작된차원 인형");
			                    }
			                    else {
				                    as.setCustomName(ChatColor.BOLD + p.getName()+"'s FakeDimension Doll");
			                    }
			                    as.setArms(true);
			                    as.setBasePlate(false);
			                    as.setGlowing(true);
			                    as.setGravity(false);
			                    as.setCanPickupItems(false);
			                    as.setMetadata("dimdoll", new FixedMetadataValue(RMain.getInstance(), p.getName()));
			    	            as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			                    as.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), p.getName()));
			                    dimdoll.put(p.getUniqueId(), as);

								int task3 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
				                    Integer time = 6;
					                @Override
					                public void run() 
					                {
					                    if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						                    p.sendTitle(ChatColor.GOLD+"남은시간" , ChatColor.GOLD+""+time, 20, 20, 20) ;
					                    }
					                    else {
						                    p.sendTitle(ChatColor.GOLD+"TimeLeft" , ChatColor.GOLD+""+time, 20, 20, 20) ;
					                    }
					                    time--;
					                }
					            }, 0,20); 
								dimcount.put(p.getUniqueId(), task3);
								
								int task2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 120, 1, false, false));
						                p.setAllowFlight(true);
										p.setFlying(false);
					                    if(p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
							                p.setAllowFlight(false);
					                    }
					                	Holding.ale(dimdoll.get(p.getUniqueId())).remove();
						        		p.teleport(dimfirstloc.get(p.getUniqueId()));
										p.playSound(p.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 0.5f, 2f);
						        		p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation(), 50,3,3,3,0);
						        		p.getWorld().spawnParticle(Particle.SPELL, p.getLocation(), 50,3,3,3,0);
						        		p.getWorld().spawnParticle(Particle.SPELL_INSTANT, p.getLocation(), 50,3,3,3,0);
					                	Bukkit.getScheduler().cancelTask(dimcount.get(p.getUniqueId()));
					                	dimcount.remove(p.getUniqueId());
					                	dimfirstloc.remove(p.getUniqueId());
						        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
							                	dimtask.remove(p.getUniqueId());
							                	dimles.get(p.getUniqueId()).forEach(leu -> {
							                		if(Bukkit.getEntity(leu) != null) {
							                			LivingEntity le = (LivingEntity) Bukkit.getEntity(leu);

														atk2(dimdolldmg.get(p.getUniqueId()),0.005, p, le);
							                		}
							                	});
							                	
							                	dimdoll.remove(p.getUniqueId());
							                	dimles.removeAll(p.getUniqueId());
							                	dimdolldmg.remove(p.getUniqueId());
							                }
							            }, 5); 
					                }
					            }, 120); 
								dimtask.put(p.getUniqueId(), task2);
			                }
		            	}, 5); 
		                sult2cooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    final Location pfl = p.getLocation();
                    dimfirstloc.put(p.getUniqueId(), pfl);
                    
	        		p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation(), 100,3,3,3,0);
                	p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 140, 1, false, false));
                    
	            	for(Entity e : p.getNearbyEntities(7, 7, 7)) {
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
	                    if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
						{ 
							LivingEntity le = (LivingEntity)e;
							le.setRemoveWhenFarAway(false);
							dimles.put(p.getUniqueId(), le.getUniqueId());
						}
                    }
	                Holding.fly(p, 100l);

	            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
							World fkw = Bukkit.getWorld("FakeDimension");
							Location fkl = new Location(fkw, pfl.getX(), pfl.getY(), pfl.getZ());
							p.teleport(fkl);
			                Holding.fly(p, 100l);
							p.playSound(p.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 0.5f, 2f);

							Holding.invur(p,130l);
		                    ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
		                    if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			                    as.setCustomName(ChatColor.BOLD + p.getName()+"의 조작된차원 인형");
		                    }
		                    else {
			                    as.setCustomName(ChatColor.BOLD + p.getName()+"'s FakeDimension Doll");
		                    }
		                    as.setArms(true);
		                    as.setBasePlate(false);
		                    as.setGlowing(true);
		                    as.setGravity(false);
		                    as.setCanPickupItems(false);
		                    as.setMetadata("dimdoll", new FixedMetadataValue(RMain.getInstance(), p.getName()));
		    	            as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		                    as.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), p.getName()));
		                    dimdoll.put(p.getUniqueId(), as);

							int task3 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                    Integer time = 6;
				                @Override
				                public void run() 
				                {
				                    if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					                    p.sendTitle(ChatColor.GOLD+"남은시간" , ChatColor.GOLD+""+time, 20, 20, 20) ;
				                    }
				                    else {
					                    p.sendTitle(ChatColor.GOLD+"TimeLeft" , ChatColor.GOLD+""+time, 20, 20, 20) ;
				                    }
				                    time--;
				                }
				            }, 0,20); 
							dimcount.put(p.getUniqueId(), task3);
							
							int task2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 120, 1, false, false));
					                p.setAllowFlight(true);
									p.setFlying(false);
				                    if(p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
						                p.setAllowFlight(false);
				                    }
				                	Holding.ale(dimdoll.get(p.getUniqueId())).remove();
					        		p.teleport(dimfirstloc.get(p.getUniqueId()));
									p.playSound(p.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 0.5f, 2f);
					        		p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation(), 50,3,3,3,0);
					        		p.getWorld().spawnParticle(Particle.SPELL, p.getLocation(), 50,3,3,3,0);
					        		p.getWorld().spawnParticle(Particle.SPELL_INSTANT, p.getLocation(), 50,3,3,3,0);
				                	Bukkit.getScheduler().cancelTask(dimcount.get(p.getUniqueId()));
				                	dimcount.remove(p.getUniqueId());
				                	dimfirstloc.remove(p.getUniqueId());
					        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                	dimtask.remove(p.getUniqueId());
						                	dimles.get(p.getUniqueId()).forEach(leu -> {
						                		if(Bukkit.getEntity(leu) != null) {
						                			LivingEntity le = (LivingEntity) Bukkit.getEntity(leu);

													atk2(dimdolldmg.get(p.getUniqueId()),0.005, p, le);
						                		}
						                	});
						                	
						                	dimdoll.remove(p.getUniqueId());
						                	dimles.removeAll(p.getUniqueId());
						                	dimdolldmg.remove(p.getUniqueId());
						                }
						            }, 5); 
				                }
				            }, 120); 
							dimtask.put(p.getUniqueId(), task2);
		                }
	            	}, 5); 
	                sult2cooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
	            }
			}
			
    }

	
	public void FakeDimension(PluginEnableEvent ev)        
    {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
            	if(Bukkit.getServer().getWorld("FakeDimension") == null) {
					WorldCreator rwc = new WorldCreator("FakeDimension");
					rwc.environment(Environment.THE_END);
					rwc.generateStructures(false);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
					        rwc.generator(new IllChunkGenerator()); 
							World rw = rwc.createWorld();
							rw.setMetadata("rpgraidworld", new FixedMetadataValue(RMain.getInstance(),true));
	        				rw.setDifficulty(Difficulty.HARD);
	        				rw.setTime(20000);
	        				rw.setAutoSave(false);
	        				rw.setKeepSpawnInMemory(false);
	        				rw.setPVP(false);
	        				rw.setSpawnFlags(false, false);
	        				rw.setGameRule(GameRule.KEEP_INVENTORY, true);
	        				rw.setGameRule(GameRule.DO_INSOMNIA, false);
	        				rw.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
	        				rw.setGameRule(GameRule.DISABLE_RAIDS, true);
	        				rw.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
	        				rw.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
	        				rw.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
	        				rw.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
	        				rw.setGameRule(GameRule.DO_TILE_DROPS, false);
	        				rw.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
	        				rw.setGameRule(GameRule.DO_ENTITY_DROPS, false);
	        				rw.setGameRule(GameRule.DO_MOB_LOOT, false);
	        				rw.setGameRule(GameRule.SPAWN_RADIUS, 0);
		                }
		            }, 10); 
            	}
            }
        }, 20); 
		List<World> worlds = Bukkit.getServer().getWorlds();
		worlds.forEach(w -> w.getPlayers().forEach(b -> {
				Player p = (Player) b;
				if(!dimtask.containsKey(p.getUniqueId()) && p.getWorld().getName().contains("FakeDimension")) {
					p.teleport(p.getBedSpawnLocation());
				}
		}));
    }

	
	public void FakeDimensionDoll(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity() instanceof ArmorStand && d.getEntity().hasMetadata("dimdoll")) 
		{
			if(d.getDamager() instanceof Player  && dimdoll.containsKey(d.getDamager().getUniqueId())) {
				Player p = (Player) d.getDamager();
				ArmorStand as = (ArmorStand) d.getEntity();
				if(as.getMetadata("dimdoll").get(0).asString().equals(p.getName())) {
					dimdolldmg.computeIfPresent(p.getUniqueId(), (k,v) -> v + d.getDamage());
					dimdolldmg.putIfAbsent(p.getUniqueId(), d.getDamage());
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(""+dimdolldmg.get(p.getUniqueId())).create());
				}
            }
			d.setCancelled(true);
		}
	}


	public void FakeDimension(PlayerTeleportEvent e)
	{
		Player p = e.getPlayer();
		if(!dimfirstloc.containsKey(p.getUniqueId()) && e.getTo().getWorld().getName().contains("FakeDimension") && !e.getFrom().getWorld().getName().contains("FakeDimension")) {
			e.setCancelled(true);
		}
	}

	
	public void FakeDimension(PlayerQuitEvent ev) 
	{
		Player p = ev.getPlayer();
		if(dimtask.containsKey(p.getUniqueId())) {
	    	Holding.ale(dimdoll.get(p.getUniqueId())).remove();
			p.setAllowFlight(false);
            if(p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
                p.setAllowFlight(false);
            }
	    	p.teleport(dimfirstloc.get(p.getUniqueId()));
        	Bukkit.getScheduler().cancelTask(dimcount.get(p.getUniqueId()));
        	dimcount.remove(p.getUniqueId());
	    	Bukkit.getScheduler().cancelTask(dimtask.get(p.getUniqueId()));
	    	
	    	dimdoll.remove(p.getUniqueId());
	    	dimles.removeAll(p.getUniqueId());
	    	dimdolldmg.remove(p.getUniqueId());
	    	dimfirstloc.remove(p.getUniqueId());
	    	dimtask.remove(p.getUniqueId());
		}
		
	}
	

	
	public void FakeDimension(PlayerJoinEvent ev) 
	{
		Player p = ev.getPlayer();
		if(!dimtask.containsKey(p.getUniqueId()) && p.getWorld().getName().contains("FakeDimension")) {
			p.teleport(p.getBedSpawnLocation());
		}
	}

	
	public void FakeDimension(PluginDisableEvent ev) 
	{
		List<World> worlds = Bukkit.getServer().getWorlds();
		worlds.forEach(w -> w.getPlayers().forEach(b -> {
				Player p = (Player) b;
				if(dimtask.containsKey(p.getUniqueId())) {
			    	Holding.ale(dimdoll.get(p.getUniqueId())).remove();
					p.setAllowFlight(false);
		            if(p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
		                p.setAllowFlight(false);
		            }
			    	p.teleport(dimfirstloc.get(p.getUniqueId()));
                	Bukkit.getScheduler().cancelTask(dimcount.get(p.getUniqueId()));
                	dimcount.remove(p.getUniqueId());
			    	Bukkit.getScheduler().cancelTask(dimtask.get(p.getUniqueId()));
			    	
			    	dimdoll.remove(p.getUniqueId());
			    	dimles.removeAll(p.getUniqueId());
			    	dimdolldmg.remove(p.getUniqueId());
			    	dimfirstloc.remove(p.getUniqueId());
			    	dimtask.remove(p.getUniqueId());
				}
		}));
	}

	
	public void FakeDimension(PlayerRespawnEvent ev) 
	{
		Player p = ev.getPlayer();
		if(dimtask.containsKey(p.getUniqueId())) {
	    	Holding.ale(dimdoll.get(p.getUniqueId())).remove();
			p.setAllowFlight(false);
            if(p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
                p.setAllowFlight(false);
            }
	    	p.teleport(dimfirstloc.get(p.getUniqueId()));
        	Bukkit.getScheduler().cancelTask(dimcount.get(p.getUniqueId()));
        	dimcount.remove(p.getUniqueId());
	    	Bukkit.getScheduler().cancelTask(dimtask.get(p.getUniqueId()));
	    	
	    	dimdoll.remove(p.getUniqueId());
	    	dimles.removeAll(p.getUniqueId());
	    	dimdolldmg.remove(p.getUniqueId());
	    	dimfirstloc.remove(p.getUniqueId());
	    	dimtask.remove(p.getUniqueId());
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public void ThrowCancel(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
		
			if(ClassData.pc.get(p.getUniqueId()) == 11  && ((is.getType()==Material.BLAZE_ROD)) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
			}
	
    }
	
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();
        

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 11)
		{
			if(p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD )
			{
					e.setCancelled(true);
			}
		}
		
	}
	

	
	public void ArmorDecrease(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity() instanceof Player && d.getDamager() instanceof LivingEntity) 
		{
			if(ClassData.pc.get(d.getEntity().getUniqueId()) == 11) {
				d.setDamage(d.getDamage()*1.6);
			}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof Player) 
		{
			Projectile ar = (Projectile)d.getDamager();
	
			if(ar.getShooter() instanceof LivingEntity) {
				if(ClassData.pc.get(d.getEntity().getUniqueId()) == 11) {
					d.setDamage(d.getDamage()*1.6);
				}
			}
		}
	}

	
	
	public void Damagegetter(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
			Player p = (Player)d.getDamager();
			LivingEntity e = (LivingEntity) d.getEntity();
	
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 11) {

				if(p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
				{
						if(p.hasPotionEffect(PotionEffectType.INVISIBILITY) || Proficiency.getpro(p)>=2) {
							dset2(d, p, (1+isd.Surprise.get(p.getUniqueId())*0.056), e, 5);
							if(e.hasMetadata("leader") || e.hasMetadata("boss")) {
								d.setDamage(d.getDamage()*2.1);
							}
						}
				}
			}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
			Arrow ar = (Arrow)d.getDamager();
			LivingEntity e = (LivingEntity) d.getEntity();
	
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
			    
				
				
				if(ClassData.pc.get(p.getUniqueId()) == 11) {

					if(p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
					{
							if(p.hasPotionEffect(PotionEffectType.INVISIBILITY) || Proficiency.getpro(p)>=2) {
								dset2(d, p, (1+isd.Surprise.get(p.getUniqueId())*0.056), e, 5);
								if(e.hasMetadata("leader") || e.hasMetadata("boss")) {
									d.setDamage(d.getDamage()*2.1);
								}
							}
					}
				}
			}
		}
	}
}



