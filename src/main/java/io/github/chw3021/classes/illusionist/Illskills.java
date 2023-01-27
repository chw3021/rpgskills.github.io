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
import com.google.common.util.concurrent.AtomicDouble;

import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
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
import org.bukkit.block.BlockFace;
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
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> stcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sult2cooldown = new HashMap<String, Long>();
	

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
	
	private HashMap<UUID, Integer> finstack = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> finstate = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> fint = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> fineft = new HashMap<UUID, Integer>();

	
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
				
                Location l = gettargetblock(p,4);
				
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
	                    
	                    stacking(p);

						if(Proficiency.getpro(p)>=1) {
							Holding.invur(p, 6l);
						}

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
		                sdcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    stacking(p);

					if(Proficiency.getpro(p)>=1) {
						Holding.invur(p, 6l);
					}

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
                    
	                sdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
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
                stacking(p);
            	if(magnif.get(p.getUniqueId()).isValid()) {
            		magnif.get(p.getUniqueId()).remove();
	            	final Location ml = magnif.get(p.getUniqueId()).getLocation();

					if(Proficiency.getpro(p)>=1) {
						Holding.invur(p, 6l);
					}

					
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

                stacking(p);
				
				if(Proficiency.getpro(p)>=1) {
					Holding.invur(p, 6l);
				}


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
		                	sn1.teleport(l);
		                	sn2.teleport(line.get(li));
		                	sn3.teleport(line.get(li2));
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
        
		if(finstate.containsKey(p.getUniqueId())) {
			sec = sec*0.2;
		}
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 11 && isd.Paradox.getOrDefault(p.getUniqueId(), 0)>=1) {
		if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR||ac==Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD)
			{
				
				ev.setCancelled(true);
				
				skilluse(()->{

                    stacking(p);

    				Holding.invur(p, 21l);
                    p.playSound(p.getLocation(), Sound.BLOCK_CHEST_LOCKED, 1,2);
                    p.playSound(p.getLocation(), Sound.ITEM_LODESTONE_COMPASS_LOCK, 1,2);
                    p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1,2);
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
                    
				}, p, sec, "역설", "Paradox", swcooldown);
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

		                    stacking(p);
		                    
		                    Holding.invur(p, 11l);
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
		                    
							cdcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
	                    stacking(p);
	                    Holding.invur(p, 11l);
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
	                    
		                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
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
                stacking(p);
                
                Holding.invur(p, 11l);
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
	

	final private void dit(Location el, Player p) {
		HashSet<Location> line = new HashSet<>();
		final Vector l1 = BlockFace.EAST.getDirection().clone().normalize();
		final Vector l2 = BlockFace.SOUTH.getDirection().clone().normalize();
		final Vector l3 = BlockFace.WEST.getDirection().clone().normalize();
		final Vector l4 = BlockFace.NORTH.getDirection().clone().normalize();

		AtomicDouble k = new AtomicDouble(1);
		
    	for(double an = 0; an<Math.PI*3; an +=Math.PI/45) {
    		line.add(el.clone().add(l1.clone().multiply(k.addAndGet(0.02)).rotateAroundY(an).rotateAroundAxis(l1.clone(), an)));
    		line.add(el.clone().add(l2.clone().multiply(k.get()).rotateAroundY(an).rotateAroundAxis(l2.clone(), an)));
    		line.add(el.clone().add(l3.clone().multiply(k.get()).rotateAroundY(an).rotateAroundAxis(l3.clone(), an)));
    		line.add(el.clone().add(l4.clone().multiply(k.get()).rotateAroundY(an).rotateAroundAxis(l4.clone(), an)));
    	}
    	
    	final World w = el.getWorld();
    	
        line.forEach(l -> {
			w.spawnParticle(Particle.SPELL, l, 1);
			w.spawnParticle(Particle.TOWN_AURA, l, 1);
			w.spawnParticle(Particle.SPELL_INSTANT, l, 1);
        	
        });
	}
	
	public void Distortion(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec = 10*((1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d));

        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 11 && isd.Distortion.getOrDefault(p.getUniqueId(), 0)>=1) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			p.setCooldown(Material.WARPED_BUTTON, 2);
			if(p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD)
			{
				

				ev.setCancelled(true);
				
				skilluse(()->{
                    stacking(p);

                	if(shufft.containsKey(p.getUniqueId())) {
                		Bukkit.getScheduler().cancelTask(shufft.get(p.getUniqueId()));
                		shufft.remove(p.getUniqueId());
                	}
					if(Proficiency.getpro(p)>=1) {
						Holding.invur(p, 6l);
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
                	

					p.setCooldown(Material.WARPED_BUTTON, 2);
					p.damage(p.getHealth()*0.4);
                    Holding.invur(p, 2l);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30, 4, false, false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 2, false, false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 80, 80, false, false));
                    dit(p.getEyeLocation().clone(),p);
                    
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

				}, p, sec, "왜곡", "Distortion", frcooldown);
				
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	

	final private void shf(Location el, Player p) {
		HashSet<Location> line = new HashSet<>();
		final Vector l1 = BlockFace.EAST.getDirection().clone().normalize();
		final Vector l2 = BlockFace.SOUTH.getDirection().clone().normalize();
		final Vector l3 = BlockFace.WEST.getDirection().clone().normalize();
		final Vector l4 = BlockFace.NORTH.getDirection().clone().normalize();

		AtomicDouble k = new AtomicDouble();
		
    	for(double an = 0; an<Math.PI*2; an +=Math.PI/45) {
    		line.add(el.clone().add(l1.clone().multiply(k.addAndGet(0.03)).rotateAroundY(an).rotateAroundNonUnitAxis(l1.clone(), an)));
    		line.add(el.clone().add(l2.clone().multiply(k.get()).rotateAroundY(an).rotateAroundNonUnitAxis(l2.clone(), an)));
    		line.add(el.clone().add(l3.clone().multiply(k.get()).rotateAroundY(an).rotateAroundNonUnitAxis(l3.clone(), an)));
    		line.add(el.clone().add(l4.clone().multiply(k.get()).rotateAroundY(an).rotateAroundNonUnitAxis(l4.clone(), an)));
    	}
    	
    	final World w = el.getWorld();
    	
        line.forEach(l -> {
			w.spawnParticle(Particle.SCULK_CHARGE, l, 1,1f);
        	
        });
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
                stacking(p);
				Holding.invur(p, 6l);
				
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

            	shf(p.getLocation().clone(),p);

	                    HashSet<LivingEntity> les = new HashSet<>();
	                    ArrayList<Location> lesl = new ArrayList<>();
	                    AtomicInteger j = new AtomicInteger();

						p.setCooldown(Material.WARPED_BUTTON, 2);
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


	final private void mdc(Location el, Player p) {
		HashSet<Location> line = new HashSet<>();
		final Vector l1 = BlockFace.EAST.getDirection().clone().normalize();
		final Vector l2 = BlockFace.SOUTH.getDirection().clone().normalize();
		final Vector l3 = BlockFace.WEST.getDirection().clone().normalize();
		final Vector l4 = BlockFace.NORTH.getDirection().clone().normalize();
		final Vector up = BlockFace.UP.getDirection().clone().normalize();

		AtomicDouble k = new AtomicDouble();
		
    	for(double an = 0; an<Math.PI*4; an +=Math.PI/45) {
    		line.add(el.clone().add(l1.clone().multiply(3)).clone().add(up.clone().multiply(k.addAndGet(0.02)).rotateAroundAxis(l1, an)));
    		line.add(el.clone().add(l2.clone().multiply(3)).clone().add(up.clone().multiply(k.get()).rotateAroundAxis(l2, an)));
    		line.add(el.clone().add(l3.clone().multiply(3)).clone().add(up.clone().multiply(k.get()).rotateAroundAxis(l3, an)));
    		line.add(el.clone().add(l4.clone().multiply(3)).clone().add(up.clone().multiply(k.get()).rotateAroundAxis(l4, an)));
    	}
    	
    	final World w = el.getWorld();
    	
        line.forEach(l -> {
			w.spawnParticle(Particle.SOUL_FIRE_FLAME, l, 1);
			w.spawnParticle(Particle.WAX_ON, l, 1);
			w.spawnParticle(Particle.SMALL_FLAME, l, 1);
        	
        });
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
                stacking(p);

				Holding.invur(p, 25l);
            	if(minct.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(minct.get(p.getUniqueId()));
            		minct.remove(p.getUniqueId());
            	}
				minc.remove(p.getUniqueId());

				mdc(p.getLocation().clone(),p);
                HashSet<LivingEntity> les = new HashSet<>();

				p.setCooldown(Material.WARPED_BUTTON, 2);
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
				p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 0.21f, 0f);
	            
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
	                    stacking(p);
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
			            stcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
                    stacking(p);
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
		            stcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
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
			final Location l = gettargetblock(p,4);
			if(!l.getBlock().isPassable()) {
            l.setY(l.getY()+1);}
			l.setDirection(p.getLocation().getDirection());
			if(l.getBlock().isPassable()) {
			if((ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK) && !p.hasCooldown(Material.WARPED_BUTTON))
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
			                smcooldown.remove(p.getName()); 
		                    stacking(p);
							if(Proficiency.getpro(p)>=1) {
								Holding.invur(p, 6l);
							}
							p.setCooldown(Material.WARPED_BUTTON, 2);
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
		                	
				            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
	                    stacking(p);
						if(Proficiency.getpro(p)>=1) {
							Holding.invur(p, 6l);
						}
						p.setCooldown(Material.WARPED_BUTTON, 1);
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
	                	
			            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
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
			if((ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK)&& !p.hasCooldown(Material.WARPED_BUTTON))
			{
                final Location dam = encore.get(p.getUniqueId());
				
				if((p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && !(p.isSneaking()) && !(p.isOnGround()) )
					{
					ev.setCancelled(true);

                    stacking(p);
					if(Proficiency.getpro(p)>=1) {
						Holding.invur(p, 6l);
					}
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
			if((ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK)&& !p.hasCooldown(Material.WARPED_BUTTON))
			{
				
				if((p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && !(p.isSneaking()) && !(p.isOnGround()) )
					{
						ev.setCancelled(true);
	                    stacking(p);
						if(Proficiency.getpro(p)>=1) {
							Holding.invur(p, 6l);
						}
						
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
				p.setCooldown(Material.WARPED_BUTTON, 2);
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
		                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
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
	                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }

	
	final private ArrayList<Location> draw(Player p){

		ArrayList<Location> draw = new ArrayList<Location>();
    	Location pl = p.getLocation().clone().add(0, 1, 0);
    	Vector pv = pl.clone().add(1, 0, 0).toVector().subtract(pl.clone().toVector());
    	
        for(double an = 0; an<Math.PI*2; an+=Math.PI/15) {
        	draw.add(pl.clone().add(pv.rotateAroundY(an).normalize().multiply(6)));
        }
        return draw;
	}
	
	public void ULT2(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item i = ev.getItemDrop();
		ItemStack is = i.getItemStack();
        
		
			if(ClassData.pc.get(p.getUniqueId()) == 11 && (is.getType() == Material.BLAZE_ROD) && !p.isSneaking()&& p.isSprinting()&& Proficiency.getpro(p) >=2 && !p.hasCooldown(Material.STICK)) 
			{
				p.setCooldown(Material.WARPED_BUTTON, 2);
				ev.setCancelled(true);
            	if(fineft.containsKey(p.getUniqueId())) {
                	Bukkit.getScheduler().cancelTask(fineft.remove(p.getUniqueId()));
            	}
				
				
				skilluse(()->{
					
                    AtomicInteger j = new AtomicInteger();
                    
                    ArrayList<Location> dr = draw(p);
                    
                    dr.forEach(l -> {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {

				                Firework fr = (Firework) p.getWorld().spawnEntity(l, EntityType.FIREWORK);
			                    fr.setShotAtAngle(true);
			                    fr.setVelocity(BlockFace.UP.getDirection().normalize());
			                    fr.setShooter(p);
			                    fr.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			                    
			                	int ri =  dr.indexOf(l)%5;
			                    
			                	Type frt = Type.STAR;
			                	if(ri==1) {
			                		frt = Type.CREEPER;
			                	}
			                	else if (ri ==2) {
			                		frt = Type.BALL;
			                	}
			                	else if (ri == 3) {
			                		frt = Type.BALL_LARGE;
			                	}
			                	else if(ri==4) {
			                		frt = Type.BURST;
			                	}
			                	

			                    Random rand = new Random();
			                    Color rc1 = Color.fromRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
			                    Color rc2 = Color.fromRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
			                    Color rc3 = Color.fromRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

			                    Color f1 = Color.fromRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
			                    Color f2 = Color.fromRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
			                    Color f3 = Color.fromRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
			        			FireworkEffect effect = FireworkEffect.builder()
			                                .with(frt)
			                                .withColor(rc1, rc2, rc3)
			                                .withFade(f1, f2, f3)
			                                .flicker(true)
			                                .trail(true)
			                                .build();
			                    FireworkMeta meta = fr.getFireworkMeta();
			                    meta.setPower(1);
			                    meta.addEffect(effect);
			                    fr.setFireworkMeta(meta);
			                    
			                }
						}, j.incrementAndGet()/2); 
                    	
                    });
					
					finstate.put(p.getUniqueId(), 1);
					finstack.put(p.getUniqueId(), 0);

					int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	 draw(p).forEach(l -> {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	p.playSound(p, Sound.ENTITY_GLOW_SQUID_AMBIENT, 0.1f, 1.5f);
					                	
					                    Random rand = new Random();
					                    Color rc1 = Color.fromRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
					                    Color rc2 = Color.fromRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));			
					                    l.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, l, 3,0.1,0.1,0.1, new Particle.DustTransition(rc1, rc2, 2));
					                    if(finstack.getOrDefault(p.getUniqueId(), 0)>=8) {

							                Firework fr = (Firework) p.getWorld().spawnEntity(l, EntityType.FIREWORK);
						                    fr.setShotAtAngle(true);
						                    fr.setVelocity(BlockFace.UP.getDirection().normalize());
						                    fr.setShooter(p);
						                    fr.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						                    
						                	int ri =  dr.indexOf(l)%5;
						                    
						                	Type frt = Type.STAR;
						                	if(ri==1) {
						                		frt = Type.CREEPER;
						                	}
						                	else if (ri ==2) {
						                		frt = Type.BALL;
						                	}
						                	else if (ri == 3) {
						                		frt = Type.BALL_LARGE;
						                	}
						                	else if(ri==4) {
						                		frt = Type.BURST;
						                	}
						                    Color rc3 = Color.fromRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

						                    Color f1 = Color.fromRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
						                    Color f2 = Color.fromRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
						                    Color f3 = Color.fromRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
						        			FireworkEffect effect = FireworkEffect.builder()
						                                .with(frt)
						                                .withColor(rc1, rc2, rc3)
						                                .withFade(f1, f2, f3)
						                                .flicker(true)
						                                .trail(true)
						                                .build();
						                    FireworkMeta meta = fr.getFireworkMeta();
						                    meta.setPower(1);
						                    meta.addEffect(effect);
						                    fr.setFireworkMeta(meta);
					                    }
					                }
								}, j.incrementAndGet()/2); 
		                    	
		                    });
		                }
					}, 0, 20); 
		         	fineft.put(p.getUniqueId(), task);
					
		         	int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	finstate.remove(p.getUniqueId());
		                	finstack.remove(p.getUniqueId());
		                	if(fineft.containsKey(p.getUniqueId())) {
			                	Bukkit.getScheduler().cancelTask(fineft.remove(p.getUniqueId()));
		                	}
		                }
		         	}, 200);
		         	fint.put(p.getUniqueId(), t);
		         	
				}, p, 80d*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d), "피날레", "Finale", sult2cooldown);
			}
			
    }


	final private void stacking(Player p) {
		finstack.computeIfPresent(p.getUniqueId(), (k,v)->v+1);	                	
		StringBuffer sb = new StringBuffer();
		if(finstack.getOrDefault(p.getUniqueId(), 0)>8) {
			sb.append(ChatColor.MAGIC+(ChatColor.LIGHT_PURPLE+"★☆★☆★☆★☆★"));
		}
		else {
			for(int i = 0; i<finstack.getOrDefault(p.getUniqueId(),0);i++)
			{
				sb.append(ChatColor.MAGIC+(ChatColor.LIGHT_PURPLE+"★"));
			}
		}
		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(sb.toString()).create());

	}
	
	
	public void FakeDimensionDoll(EntityDamageByEntityEvent d) {

		if(d.getDamager() instanceof Player && finstack.getOrDefault(d.getDamager().getUniqueId(),0)>=8 && d.getEntity() instanceof LivingEntity) {
			Player p = (Player) d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			final Double damage = d.getDamage();
			atkab0(0d,damage*(0.04+Pak.player_damage.get(p.getName())*0.0001),p,le);
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
				d.setDamage(d.getDamage()*1.56);
			}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof Player) 
		{
			Projectile ar = (Projectile)d.getDamager();
	
			if(ar.getShooter() instanceof LivingEntity) {
				if(ClassData.pc.get(d.getEntity().getUniqueId()) == 11) {
					d.setDamage(d.getDamage()*1.56);
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



