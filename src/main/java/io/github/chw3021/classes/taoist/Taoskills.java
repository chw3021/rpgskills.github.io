package io.github.chw3021.classes.taoist;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.commons.SkillUseEvent;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
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
import org.bukkit.metadata.FixedMetadataValue;

public class Taoskills extends Pak implements Listener, Serializable {
	

	/**
	 * 
	 */
	private static transient final long serialVersionUID = -5624671500897916444L;
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sult2cooldown = new HashMap<String, Long>();
	

	private HashMap<UUID, Integer> chsl = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> chslt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> sprsr = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> sprsrt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> bls = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> blst = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> acsn = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> acsnt = new HashMap<UUID, Integer>();
	
	private HashMap<UUID, Integer> sern = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> sernt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> man = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> mant = new HashMap<UUID, Integer>();
	
	private HashMap<UUID, Integer> ergs = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> ergst = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> karb = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> karbt = new HashMap<UUID, Integer>();
	

	private HashMap<UUID, Integer> shp = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> shpt = new HashMap<UUID, Integer>();
	
	
	private HashMap<String, Integer> auras = new HashMap<String, Integer>();
	private HashMap<String, Integer> aurat = new HashMap<String, Integer>();
	
	private HashMap<String, Integer> aurapositive = new HashMap<String, Integer>();
	private HashMap<String, Integer> auranegetive = new HashMap<String, Integer>();
	private HashMap<String, Integer> aurapositivetask = new HashMap<String, Integer>();
	private HashMap<String, Integer> auranegetivetask = new HashMap<String, Integer>();
	
	private HashMap<String, Integer> amplify = new HashMap<String, Integer>();
	private HashMap<String, Integer> amplifyt = new HashMap<String, Integer>();
	
	static private HashMap<String, Integer> mantra = new HashMap<String, Integer>();
	static private HashMap<String, Integer> mantrat = new HashMap<String, Integer>();
	
	private static String path = new File("").getAbsolutePath();
	private TaoSkillsData tsd ;

	private static final Taoskills instance = new Taoskills ();
	public static Taoskills getInstance()
	{
		return instance;
	}

	
	

		
	public void er(PluginEnableEvent ev) 
	{
		TaoSkillsData t = new TaoSkillsData(TaoSkillsData.loadData(path +"/plugins/RPGskills/TaoSkillsData.data"));
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
				final Player p = (Player) e.getWhoClicked();
				if(aurat.containsKey(p.getName())) {
					Bukkit.getScheduler().cancelTask(aurat.get(p.getName()));
					aurat.remove(p.getName());
				}
				if(auranegetivetask.containsKey(p.getName())) {
					Bukkit.getScheduler().cancelTask(auranegetivetask.get(p.getName()));
					auranegetivetask.remove(p.getName());
				}
				if(aurapositivetask.containsKey(p.getName())) {
					Bukkit.getScheduler().cancelTask(aurapositivetask.get(p.getName()));
					aurapositivetask.remove(p.getName());
				}
            	auranegetive.remove(p.getName());
            	aurapositive.remove(p.getName());
				
				e.getWhoClicked().setCollidable(true);
			}
		}
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("TaoSkills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				TaoSkillsData t = new TaoSkillsData(TaoSkillsData.loadData(path +"/plugins/RPGskills/TaoSkillsData.data"));
				tsd = t;
			}
		}
	}

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		TaoSkillsData t = new TaoSkillsData(TaoSkillsData.loadData(path +"/plugins/RPGskills/TaoSkillsData.data"));
		tsd = t;
		
	}
	
	final private void Aura(final Player p, Integer in) {
		
		
		final Integer proi = Proficiency.getpro(p)>=1 ? 1:0;
		if(in == 0) {
			
			if(auranegetivetask.containsKey(p.getName())) {
				Bukkit.getScheduler().cancelTask(auranegetivetask.get(p.getName()));
				auranegetivetask.remove(p.getName());
			}
			
			if(auranegetive.getOrDefault(p.getName(),0) <= (proi) +1) {
				auranegetive.put(p.getName(), proi +1);
			}
			
			int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() 
	            {
	            	auranegetive.remove(p.getName());
	            }
			}, 10+mantra.getOrDefault(p.getName(),0)); 
			auranegetivetask.put(p.getName(), task);
			
			
			
			int amp = amplify.getOrDefault(p.getName(), 0);
			p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 100*(1+amp), 5+amp,1,5+amp ,Material.WHITE_STAINED_GLASS.createBlockData());
			p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 100*(1+amp), 5+amp,1,5+amp ,Material.BLACK_STAINED_GLASS.createBlockData());

        	if(amplify.containsKey(p.getName())) {
            	p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1,0, false,false));
    			p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 100*(1+amp), 5+amp,1,5+amp ,Material.IRON_BLOCK.createBlockData());
    			p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 100*(1+amp), 5+amp,1,5+amp ,Material.BLUE_WOOL.createBlockData());
        	}
        	for (Entity e : p.getNearbyEntities(5+amp, 5+amp, 5+amp))
			{
            	if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
				{
					if (e instanceof Player) 
					{
						
						Player p1 = (Player) e;
						if(Party.hasParty(p) && Party.hasParty(p1))	{
						if(Party.getParty(p).equals(Party.getParty(p1)))
							{

							if(auranegetivetask.containsKey(p1.getName())) {
								Bukkit.getScheduler().cancelTask(auranegetivetask.get(p1.getName()));
								auranegetivetask.remove(p1.getName());
							}
							if(auranegetive.getOrDefault(p1.getName(),0) <= (proi +1)) {
								auranegetive.put(p1.getName(), proi +1);
							}
				        	auranegetive.computeIfPresent(p1.getName(), (k,v) -> v+1);
				        	auranegetive.putIfAbsent(p1.getName(), 0);
							int task1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					            @Override
					            public void run() 
					            {
					            	auranegetive.remove(p1.getName());
					            }
							}, 10+mantra.getOrDefault(p.getName(),0)); 
							auranegetivetask.put(p1.getName(), task1);
							
				        	if(amplify.containsKey(p1.getName())) {
				            	p1.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1,0, false,false));
				        	}
							continue;
							}
						}
					}
					
				}
			}
        	return;
		}
		else if(in == 1) {

			if(aurapositivetask.containsKey(p.getName())) {
				Bukkit.getScheduler().cancelTask(aurapositivetask.get(p.getName()));
				aurapositivetask.remove(p.getName());
			}
			if(aurapositive.getOrDefault(p.getName(),0) <= (proi +1)) {
				aurapositive.put(p.getName(), proi +1);
			}
        	
			int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() 
	            {
	            	aurapositive.remove(p.getName());
	            }
			}, 10+mantra.getOrDefault(p.getName(),0)); 
			aurapositivetask.put(p.getName(), task);
			
			
			int amp = amplify.getOrDefault(p.getName(), 0);
			p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 100*(1+amp), 5+amp,1,5+amp ,Material.RED_STAINED_GLASS.createBlockData());
			p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 100*(1+amp), 5+amp,1,5+amp ,Material.BLUE_STAINED_GLASS.createBlockData());


        	if(amplify.containsKey(p.getName())) {
    			p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 100*(1+amp), 5+amp,1,5+amp ,Material.JUNGLE_LEAVES.createBlockData());
    			p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 100*(1+amp), 5+amp,1,5+amp ,Material.MAGMA_BLOCK.createBlockData());
        	}
        	
        	p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10+mantra.getOrDefault(p.getName(),0),2, false,false));
        	for (Entity e : p.getNearbyEntities(5+amp, 5+amp, 5+amp))
			{
            	if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
				{
					if (e instanceof Player) 
					{
						
						Player p1 = (Player) e;
						if(Party.hasParty(p) && Party.hasParty(p1))	{
						if(Party.getParty(p).equals(Party.getParty(p1)))
							{

							if(aurapositive.getOrDefault(p1.getName(),0) <= (proi +1)) {
								aurapositive.put(p1.getName(), proi +1);
							}
							if(aurapositivetask.containsKey(p1.getName())) {
								Bukkit.getScheduler().cancelTask(aurapositivetask.get(p1.getName()));
								aurapositivetask.remove(p1.getName());
							}
							int task1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					            @Override
					            public void run() 
					            {
					            	aurapositive.remove(p1.getName());
					            }
							}, 10+mantra.getOrDefault(p.getName(),0)); 
							aurapositivetask.put(p1.getName(), task1);

			            	p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10+mantra.getOrDefault(p.getName(),0),2, false,false));
							continue;
							}
						}
					}
					LivingEntity le = (LivingEntity)e;
		        	if(amplify.containsKey(p.getName())) {
		            	atk0(0.2, tsd.Amplify.get(p.getUniqueId())*0.2, p, le);
		        	}
					
				}
			}
        	return;
		}
	}

	public void Aura(PlayerItemHeldEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 10 && tsd.Aura.get(p.getUniqueId())>=1){
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN")&& p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() &&  p.isSneaking()) {

				ev.setCancelled(true);
				if(!p.hasCooldown(Material.CANDLE)) {
					p.playSound(p.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 0.8f, 0.6f);
					p.setCooldown(Material.CANDLE, 2);
					if(Proficiency.getpro(p) >=2) {
						p.setCollidable(false);
					}
					auras.putIfAbsent(p.getName(), 0);
                    Bukkit.getPluginManager().callEvent(new SkillUseEvent(p,0.1d,5,"기운","Aura"));

					if(auras.getOrDefault(p.getName(), 0) == 0) {
						auras.put(p.getName(), 1);
						if(aurat.containsKey(p.getName())) {
							Bukkit.getScheduler().cancelTask(aurat.get(p.getName()));
							aurat.remove(p.getName());
						}
						int task2 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	Aura(p, 1);
			                }
						}, 0, 5); 
						aurat.put(p.getName(), task2);
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("양의 기운").color(ChatColor.YELLOW).create());
						}
						else {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Positive Aura").color(ChatColor.YELLOW).create());
						}

		        		return;
					}
					else if(auras.getOrDefault(p.getName(), 0) == 1) {
						auras.put(p.getName(), 0);
						if(aurat.containsKey(p.getName())) {
							Bukkit.getScheduler().cancelTask(aurat.get(p.getName()));
							aurat.remove(p.getName());
						}
						int task3 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	Aura(p, 0);
			                }
						}, 0, 5); 
						aurat.put(p.getName(), task3);
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("음의 기운").color(ChatColor.BLUE).create());
						}
						else {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Negetive Aura").color(ChatColor.BLUE).create());
						}

		        		return;
					}
				}
			}
			else {
				if(aurat.containsKey(p.getName())) {
					Bukkit.getScheduler().cancelTask(aurat.get(p.getName()));
					aurat.remove(p.getName());
				}
				Bukkit.getWorlds().forEach(w -> w.getEntities().stream().filter(e -> e.hasMetadata("rob"+p.getName())).forEach(e-> e.remove()));
				if(auras.containsKey(p.getName())) {
					auras.remove(p.getName());
				}
			}
		}
	}
	

	public void Aura(EntityDamageEvent d) 
	{
		if(d.getEntity() instanceof Player && d.getCause() != DamageCause.SUICIDE&& d.getCause() != DamageCause.VOID && d.getCause() != DamageCause.STARVATION) {
			Player p = (Player) d.getEntity();
			if(auranegetive.containsKey(p.getName())) {
				d.setDamage(d.getDamage()*(1 - 0.3*auranegetive.get(p.getName())));
			}
			
		}
	}

	public void Aura(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) {
			Player p = (Player) d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			if(ClassData.pc.get(p.getUniqueId()) == 10)
			{
				if(auranegetive.containsKey(p.getName())) {
	            	p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1,0, false,false));
	            	if(Party.hasParty(p)) {
	            		Party.getMembers(Party.getParty(p)).forEach(pu -> {
	            			Player p1 = Bukkit.getPlayer(pu);
	            			if(p1 != p) {
		    	            	p1.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1,0, false,false));
	            			}
	            		});
	            	}
				}
				if(auras.getOrDefault(p.getName(), -1) == 0) {
					dset2(d, p, 1d, le, 6);
					dset2(d, p, 1d, le, 8);
				}
				else if(auras.getOrDefault(p.getName(), -1) == 1) {
					dset2(d, p, 1d, le, 14);
					dset2(d, p, 1d, le, 10);
				}
			}
			if(aurapositive.containsKey(p.getName())) {
				d.setDamage(d.getDamage()*(1 + 0.3*aurapositive.get(p.getName())));
			}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity) {
			Projectile pr = (Projectile) d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			if(pr.getShooter() instanceof Player) {
				Player p = (Player) pr.getShooter();
				if(ClassData.pc.get(p.getUniqueId()) == 10)
				{
					if(auranegetive.containsKey(p.getName())) {
		            	p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1,0, false,false));
		            	if(Party.hasParty(p)) {
		            		Party.getMembers(Party.getParty(p)).forEach(pu -> {
		            			Player p1 = Bukkit.getPlayer(pu);
		            			if(p1 != p) {
			    	            	p1.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1,0, false,false));
		            			}
		            		});
		            	}
					}
					if(auras.getOrDefault(p.getName(), -1) == 0) {
						dset2(d, p, 1d, le, 6);
						dset2(d, p, 1d, le, 8);
					}
					else if(auras.getOrDefault(p.getName(), -1) == 1) {
						dset2(d, p, 1d, le, 5);
						dset2(d, p, 1d, le, 10);
					}
				}
				if(aurapositive.containsKey(p.getName())) {
					d.setDamage(d.getDamage()*(1 + 0.3*aurapositive.get(p.getName())));
				}
			}
		}
	}
	
	public void Aura(PlayerDeathEvent d) 
	{
		Player p = (Player) d.getEntity();
		if(aurat.containsKey(p.getName())) {
			Bukkit.getScheduler().cancelTask(aurat.get(p.getName()));
			aurat.remove(p.getName());
		}
		if(auras.containsKey(p.getName())) {
			auras.remove(p.getName());
		}

	}
	
	public void Aura(PlayerQuitEvent d) 
	{
		Player p = d.getPlayer();
		if(aurat.containsKey(p.getName())) {
			Bukkit.getScheduler().cancelTask(aurat.get(p.getName()));
			aurat.remove(p.getName());
		}
		if(auras.containsKey(p.getName())) {
			auras.remove(p.getName());
		}

		
	}
	

	final private void Imagery(Player p, Location il, int in) {


        ArrayList<Location> water = new ArrayList<Location>();
        ArrayList<Location> metal = new ArrayList<Location>();

    	for(double angley= 0; angley<Math.PI*2; angley += Math.PI/90) {
        	water.add(il.clone().add(il.clone().getDirection().normalize().rotateAroundY(angley).multiply(2)).add(0, angley, 0));
    	}
    	for(double d= 0; d<4; d+=0.5) {
        	metal.add(il.clone().add(0, d, 0));
    	}
    	
    	if(in == 0) {
    		p.playSound(p.getLocation(), Sound.BLOCK_METAL_HIT, 1, 0.5f);
    		p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 1, 0.5f);
    		p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 1, 1.5f);
    		water.forEach(l -> {
    			l.getWorld().spawnParticle(Particle.DRIPPING_WATER,l,3,0.05,0.05,0.05,0);
    		});
    		metal.forEach(l -> {
                ArmorStand v1 = p.getWorld().spawn(l, ArmorStand.class);
                v1.setCollidable(false);
                v1.setGravity(false);
                v1.setCanPickupItems(false);
                v1.setInvisible(true);
                v1.getEquipment().setHelmet(new ItemStack(Material.DEEPSLATE_COAL_ORE));
                v1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                v1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                v1.setBasePlate(false);
            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            		@Override
                	public void run() 
	                {	
            			v1.remove();
     				}
            	}, 40);
    		});
			il.getWorld().spawnParticle(Particle.END_ROD, il.clone().add(0, 4, 0),400,4,1,4,0);
    	}
    	if(in == 1) {
    		p.playSound(p.getLocation(), Sound.BLOCK_FIRE_AMBIENT, 1, 1.5f);
    		p.playSound(p.getLocation(), Sound.BLOCK_AZALEA_LEAVES_FALL, 1, 0.5f);
    		water.forEach(l -> {
    			l.getWorld().spawnParticle(Particle.FLAME,l,3,0.05,0.05,0.05,0);
    		});
    		metal.forEach(l -> {
                ArmorStand v1 = p.getWorld().spawn(l, ArmorStand.class);
                v1.setCollidable(false);
                v1.setGravity(false);
                v1.setCanPickupItems(false);
                v1.setInvisible(true);
                v1.getEquipment().setHelmet(new ItemStack(Material.FLOWERING_AZALEA_LEAVES));
                v1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                v1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                v1.setBasePlate(false);
            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            		@Override
                	public void run() 
	                {	
            			v1.remove();
     				}
            	}, 40);
    		});
			il.getWorld().spawnParticle(Particle.SPORE_BLOSSOM_AIR, il.clone().add(0, 4, 0),400,4,1,4,0);
			il.getWorld().spawnParticle(Particle.COMPOSTER, il.clone().add(0, 4, 0),400,4,1,4,0);
    	}
    	return;
	}

	
	
	public void Imagery(PlayerSwapHandItemsEvent ev) 
	{
	    
		Player p = ev.getPlayer();

		
		if(ClassData.pc.get(p.getUniqueId()) == 10&& tsd.Imagery.get(p.getUniqueId())>=1)
		{
		double sec = 5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && !(p.isSneaking()))
			{
				ev.setCancelled(true);
				if(!auras.containsKey(p.getName())) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("먼저 기운을 활성화 시켜야됩니다").create());
					}
					else {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You Should Activate Aura").create());
					}
                    return;
				}
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("연상")
						.ename("Imagery")
						.slot(0)
						.hm(gdcooldown)
						.skillUse(() -> {
		                	if(blst.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(blst.get(p.getUniqueId()));
		                		blst.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							bls.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						bls.remove(p.getUniqueId());
		    	                }
		    	            }, 35); 
		                	blst.put(p.getUniqueId(), task);
		                    
		                	final Location tl = gettargetblock(p,3).setDirection(p.getLocation().clone().getDirection());
		    				
	                    	Imagery(p, tl,auras.getOrDefault(p.getName(), -1));
	                    	

		    				p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_BLINDNESS, 0.5f, 2f);
		    				
		    				for(Entity e : p.getWorld().getNearbyEntities(tl,4, 3, 4)) {
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
		    					if(e!=p && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
		    						LivingEntity le = (LivingEntity)e;
									atk0(0.7, tsd.Imagery.get(p.getUniqueId())*0.9, p, le);
		    	                    le.teleport(tl);
		    						Holding.superholding(p, le, 5l);
		    					}
		    				}
		    				
						});
				bd.execute();
	            
			}
		}
	}
	

	
	public void Blast(PlayerSwapHandItemsEvent ev) 
	{
	    
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 10 &&bls.containsKey(p.getUniqueId()))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && !(p.isSneaking()))
			{
				ev.setCancelled(true);
				
            	if(blst.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(blst.get(p.getUniqueId()));
            		blst.remove(p.getUniqueId());
            	}
				bls.remove(p.getUniqueId());


            	if(acsnt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(acsnt.get(p.getUniqueId()));
            		acsnt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=1) {
							acsn.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						acsn.remove(p.getUniqueId());
	                }
	            }, 35); 
            	acsnt.put(p.getUniqueId(), task);
	            

				final Location el = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(2.5));
				

				p.playSound(el, Sound.ENTITY_EVOKER_PREPARE_SUMMON, 0.3f, 1.6f);
				p.getWorld().spawnParticle(Particle.CLOUD, el, 50, 1, 1, 1);
				p.getWorld().spawnParticle(Particle.ASH, el, 50, 1, 1, 1);
				

        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	                    if(auras.getOrDefault(p.getName() ,-1) == 0)
	    				{
	    					el.getWorld().spawnParticle(Particle.WHITE_ASH, el, 200);
	    					el.getWorld().spawnParticle(Particle.RAIN, el, 40, 2, 2, 2);
	    					el.getWorld().spawnParticle(Particle.DRIPPING_WATER, el, 40, 2, 2, 2);
	    					el.getWorld().spawnParticle(Particle.SNOWFLAKE, el, 300);
	    					el.getWorld().spawnParticle(Particle.END_ROD, el, 300);
	    				}
	                    if(auras.getOrDefault(p.getName() ,-1) == 1)
	    				{
	    					el.getWorld().spawnParticle(Particle.SPORE_BLOSSOM_AIR, el, 30, 2, 2, 2);
	    					el.getWorld().spawnParticle(Particle.FALLING_SPORE_BLOSSOM, el, 30, 2, 2, 2);
	    					el.getWorld().spawnParticle(Particle.FLAME, el, 300);
	    					el.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, el, 300);
	    					el.getWorld().spawnParticle(Particle.LAVA, el, 40, 2, 2, 2, 0);
	    				}
	    				p.playSound(el, Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 1);
	    				p.playSound(el, Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.6f, 0.8f);
	    				for(Entity e : p.getWorld().getNearbyEntities(el,5, 3, 5)) {
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
	    					if(e!=p && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
	    						LivingEntity le = (LivingEntity)e;
	    						atk0(1.4, tsd.Imagery.get(p.getUniqueId())*1.5, p, le);
	    					}
	    				}
	                }
	            }, 10); 
				
			}
		}
	}


	final private void Ascension(Player p, Location il, int in) {


        ArrayList<Location> a1 = new ArrayList<Location>();
        ArrayList<Location> a2 = new ArrayList<Location>();
        ArrayList<Location> a3 = new ArrayList<Location>();

    	for(double angley= 0; angley<Math.PI*2; angley += Math.PI/90) {
        	a1.add(il.clone().add(il.clone().getDirection().normalize().rotateAroundY(angley).multiply(3.5)).add(0, angley, 0));
    	}
    	for(double angley= 0; angley<Math.PI*2; angley += Math.PI/90) {
        	a2.add(il.clone().add(il.clone().getDirection().normalize().rotateAroundY(-angley).multiply(3.5)).add(0, angley, 0));
    	}
    	for(int d= 0; d<6; d++) {
        	a3.add(il.clone().add(0, d, 0));
    	}
		p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1, 0f);
		p.playSound(p.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 1, 2f);
    	
    	if(in == 0) {
    		a1.forEach(l -> {
    			l.getWorld().spawnParticle(Particle.BLOCK, l,3,0.1,0.1,0.1,0 ,Material.BLACK_TERRACOTTA.createBlockData());
    			l.getWorld().spawnParticle(Particle.DRIPPING_WATER, l,3,0.1,0.1,0.1,0);
    		});
    		a2.forEach(l -> {
    			l.getWorld().spawnParticle(Particle.BLOCK, l,3,0.1,0.1,0.1,0 ,Material.WHITE_GLAZED_TERRACOTTA.createBlockData());
    			l.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, l,3,0.1,0.1,0.1,0);
    		});
    		a3.forEach(l -> {
    			l.getWorld().spawnParticle(Particle.BLOCK, l,100,1,1,1,0 ,Material.LIGHT_GRAY_GLAZED_TERRACOTTA.createBlockData());
    			l.getWorld().spawnParticle(Particle.BLOCK, l,100,1,1,1,0 ,Material.GRAY_GLAZED_TERRACOTTA.createBlockData());
    		});
        	return;
    	}
    	if(in == 1) {
    		p.playSound(p.getLocation(), Sound.BLOCK_FIRE_AMBIENT, 1, 1.5f);
    		p.playSound(p.getLocation(), Sound.BLOCK_AZALEA_LEAVES_FALL, 1, 0.5f);
    		a1.forEach(l -> {
    			l.getWorld().spawnParticle(Particle.BLOCK, l,3,0.1,0.1,0.1,0 ,Material.RED_TERRACOTTA.createBlockData());
    			l.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l,3,0.1,0.1,0.1,0);
    		});
    		a2.forEach(l -> {
    			l.getWorld().spawnParticle(Particle.BLOCK, l,3,0.1,0.1,0.1,0 ,Material.BLUE_TERRACOTTA.createBlockData());
    			l.getWorld().spawnParticle(Particle.DRIPPING_LAVA, l,3,0.1,0.1,0.1,0);
    		});
    		a3.forEach(l -> {
    			l.getWorld().spawnParticle(Particle.BLOCK, l,100,1,1,1,0 ,Material.BLUE_GLAZED_TERRACOTTA.createBlockData());
    			l.getWorld().spawnParticle(Particle.BLOCK, l,100,1,1,1,0 ,Material.RED_GLAZED_TERRACOTTA.createBlockData());
    		});
        	return;
    	}
	}


	
	public void Ascension(PlayerSwapHandItemsEvent ev) 
	{
	    
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 10 &&acsn.containsKey(p.getUniqueId()))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && !(p.isSneaking()))
			{
				ev.setCancelled(true);

	        	if(acsnt.containsKey(p.getUniqueId())) {
	        		Bukkit.getScheduler().cancelTask(acsnt.get(p.getUniqueId()));
	        		acsnt.remove(p.getUniqueId());
	        	}
				acsn.remove(p.getUniqueId());


            	final Location tl = gettargetblock(p,3).setDirection(p.getLocation().clone().getDirection());
            	
				p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, 1, 2f);
				
				for(int i = 0; i <10; i++) {
					p.teleport(p.getLocation().clone().add(0, 0.1, 0));
					if(!p.getEyeLocation().getBlock().isPassable()) {
						break;
					}
					p.setFallDistance(0);
				}
				Ascension(p,tl,auras.getOrDefault(p.getName(), -1));
				
				for(Entity e : p.getWorld().getNearbyEntities(tl,5, 5, 5)) {
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
					if(e!=p && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
						LivingEntity le = (LivingEntity)e;
						atk0(1.2, tsd.Imagery.get(p.getUniqueId())*1.3, p, le);
						le.teleport(le.getLocation().add(0, 1, 0));
						Holding.holding(p, le, 30l);
					}
				}
				
			}
		}
	}
	
	
	
	public void Amplify(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 10&& tsd.Amplify.get(p.getUniqueId())>=1) {
			if((p.isSneaking())&& (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&&(ac!= Action.LEFT_CLICK_AIR)&&(ac!= Action.LEFT_CLICK_AIR))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
				{
					double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

					p.setCooldown(CAREFUL, 2);
					ev.setCancelled(true);
					if(!auras.containsKey(p.getName())) {
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("먼저 기운을 활성화 시켜야됩니다").create());
						}
						else {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You Should Activate Aura").create());
						}
	                    return;
					}
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("증폭")
							.ename("Amplify")
							.slot(1)
							.hm(swcooldown)
							.skillUse(() -> {
			                	if(sernt.containsKey(p.getUniqueId())) {
			                		Bukkit.getScheduler().cancelTask(sernt.get(p.getUniqueId()));
			                		sernt.remove(p.getUniqueId());
			                	}

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										if(Proficiency.getpro(p)>=1) {
											sern.putIfAbsent(p.getUniqueId(), 0);
										}
					                }
					            }, 3); 

			            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										sern.remove(p.getUniqueId());
					                }
					            }, 40);
			                	sernt.put(p.getUniqueId(), task);
			                    
			                    
			        			p.getWorld().spawnParticle(Particle.COMPOSTER, p.getLocation(),300,1,1,1);
			    				p.playSound(p.getLocation(), Sound.ITEM_BOTTLE_FILL_DRAGONBREATH, 0.5f, 0f);
			                    
			        			if(amplifyt.containsKey(p.getName())) {
			        				Bukkit.getScheduler().cancelTask(amplifyt.get(p.getName()));
			        			}
			        			amplify.put(p.getName(), 2);
			        			int task1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			        	            @Override
			        	            public void run() 
			        	            {
			        	            	amplify.remove(p.getName());
			        	            }
			        			}, tsd.Amplify.get(p.getUniqueId())*2); 
			        			amplifyt.put(p.getName(), task1);
			        			
			        			
							});
					bd.execute();
				}
			}
		}
	}

	
	
	
	public void Serenity(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 10 &&sern.containsKey(p.getUniqueId())) {
			if((p.isSneaking())&& (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&&(ac!= Action.LEFT_CLICK_AIR)&&(ac!= Action.LEFT_CLICK_AIR))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
				{

					p.setCooldown(CAREFUL, 2);
					ev.setCancelled(true);
					
	            	if(sernt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(sernt.get(p.getUniqueId()));
	            		sernt.remove(p.getUniqueId());
	            	}
					sern.remove(p.getUniqueId());

	            	if(mant.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(mant.get(p.getUniqueId()));
	            		mant.remove(p.getUniqueId());
	            	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=2) {
								man.putIfAbsent(p.getUniqueId(), 0);
							}
		                }
		            }, 3); 

	        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							man.remove(p.getUniqueId());
		                }
		            }, 40);
	            	mant.put(p.getUniqueId(), task);
	            	
	            	
        			p.getWorld().spawnParticle(Particle.CLOUD, p.getLocation(),500,1,1,1);
    				p.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 0.5f, 1.5f);

    				cleans(p);

					if(auras.getOrDefault(p.getName(), -1) == 0) {
						Holding.invur(p, tsd.Amplify.get(p.getUniqueId())*1l);
					}
	    			int amp = amplify.getOrDefault(p.getName(), 0);
	            	for (Entity e : p.getNearbyEntities(5+amp, 5+amp, 5+amp))
	    			{
	                	if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
	    				{
	    					if (e instanceof Player) 
	    					{
	    						
	    						Player p1 = (Player) e;
	    						if(Party.hasParty(p) && Party.hasParty(p1))	{
	    						if(Party.getParty(p).equals(Party.getParty(p1)))
	    							{

	    		    				cleans(p1);
	    							if(auras.getOrDefault(p.getName(), -1) == 0) {
	    								Holding.invur(p1, tsd.Amplify.get(p.getUniqueId())*1l);
	    							}
	    							continue;
	    							}
	    						}
	    					}
	    					LivingEntity le = (LivingEntity)e;
							if(auras.getOrDefault(p.getName(), -1) == 1) {
	    		            	atk0(0.6, tsd.Amplify.get(p.getUniqueId())*0.8, p, le);
								Holding.holding(p, le, tsd.Amplify.get(p.getUniqueId())*1l);
							}
	    					
	    				}
	    			}
				}
			}
		}
	}

	
	public void Mantra(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 10 &&man.containsKey(p.getUniqueId())) {
			if((p.isSneaking())&& (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&&(ac!= Action.LEFT_CLICK_AIR)&&(ac!= Action.LEFT_CLICK_AIR))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
				{

					p.setCooldown(CAREFUL, 2);
					ev.setCancelled(true);

	            	if(mant.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(mant.get(p.getUniqueId()));
	            		mant.remove(p.getUniqueId());
	            	}
					man.remove(p.getUniqueId());
					

                    
        			p.getWorld().spawnParticle(Particle.WAX_OFF, p.getLocation(),300,1,1,1);
        			p.getWorld().spawnParticle(Particle.WAX_ON, p.getLocation(),300,1,1,1);
        			
    				p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 0.7f, 1.16f);
	            	p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,1, false,false));
                    
        			if(mantrat.containsKey(p.getName())) {
        				Bukkit.getScheduler().cancelTask(mantrat.get(p.getName()));
        				mantrat.remove(p.getName());
        			}
        			mantra.put(p.getName(), tsd.Amplify.get(p.getUniqueId())*2);
        			int task1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
        	            @Override
        	            public void run() 
        	            {
        	            	mantra.remove(p.getName());
        	            }
        			}, 100);
        			mantrat.put(p.getName(), task1);

	    			int amp = amplify.getOrDefault(p.getName(), 0);
	            	for (Entity e : p.getNearbyEntities(5+amp, 5+amp, 5+amp))
	    			{
	                	if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
	    				{
	    					if (e instanceof Player) 
	    					{
	    						
	    						Player p1 = (Player) e;
	    						if(Party.hasParty(p) && Party.hasParty(p1))	{
	    						if(Party.getParty(p).equals(Party.getParty(p1)))
	    							{
			    	            	p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,1, false,false));
	    								continue;
	    							}
	    						}
	    					}
	    					LivingEntity le = (LivingEntity)e;
    		            	atk0(0.8, tsd.Amplify.get(p.getUniqueId())*0.9, p, le);
	    					
	    				}
	    			}
				}
			}
		}
	}
	
	
	public void Charm(PlayerSwapHandItemsEvent ev) 
	{
	    
		Player p = ev.getPlayer();

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 10&& tsd.Charm.get(p.getUniqueId())>=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && p.isSneaking())
			{
				double sec = 7.5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				ev.setCancelled(true);
				if(!auras.containsKey(p.getName())) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("먼저 기운을 활성화 시켜야됩니다").create());
					}
					else {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You Should Activate Aura").create());
					}
                    return;
				}
				

				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("부적")
						.ename("Charm")
						.slot(2)
						.hm(cdcooldown)
						.skillUse(() -> {
		                	if(ergst.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(ergst.get(p.getUniqueId()));
		                		ergst.remove(p.getUniqueId());
		                	}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									if(Proficiency.getpro(p)>=1) {
										ergs.putIfAbsent(p.getUniqueId(), 0);
									}
				                }
				            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									ergs.remove(p.getUniqueId());
				                }
				            }, 40);
		                	ergst.put(p.getUniqueId(), task);
		                	
		                    
		                    p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0f, 2f);
		                    p.getLocation();
		                    Location l = gettargetblock(p,5);
							ItemStack TortoiseCharm = new ItemStack(Material.GRAY_BANNER, 1);									
							ItemStack DragonCharm = new ItemStack(Material.BLUE_BANNER, 1); 
							ItemStack BirdCharm = new ItemStack(Material.RED_BANNER, 1); 
							ItemStack TigerCharm = new ItemStack(Material.WHITE_BANNER, 1); 
		                    if(auras.get(p.getName()) == 0)
							{ 
								Item charm = p.getWorld().dropItem(p.getLocation(), TortoiseCharm);
								charm.setPickupDelay(5000);
								charm.setInvulnerable(true);
								charm.setThrower(p.getUniqueId());
								charm.teleport(l);
								charm.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								charm.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								charm.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
								charm.setGlowing(true);
								Item charm1 = p.getWorld().dropItem(p.getLocation(), TigerCharm);
								charm1.setPickupDelay(5000);
								charm1.setInvulnerable(true);
								charm1.setThrower(p.getUniqueId());
								charm1.teleport(l);
								charm1.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								charm1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								charm1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
								charm1.setGlowing(true);
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	charm.remove();
					                	charm1.remove();
					                }
		                	   }, 70); 
							}
		                    if(auras.get(p.getName()) == 1)
							{
								Item charm = p.getWorld().dropItem(p.getLocation(), DragonCharm);
								charm.setPickupDelay(5000);
								charm.setInvulnerable(true);
								charm.setThrower(p.getUniqueId());
								charm.teleport(l);
								charm.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								charm.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								charm.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
								charm.setGlowing(true);
								Item charm1 = p.getWorld().dropItem(p.getLocation(), BirdCharm);
								charm1.setPickupDelay(5000);
								charm1.setInvulnerable(true);
								charm1.setThrower(p.getUniqueId());
								charm1.teleport(l);
								charm1.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								charm1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								charm1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
								charm1.setGlowing(true);
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	charm.remove();
					                	charm1.remove();
					                }
		                	   }, 70); 
							}
	                    	for(int i =1; i<6; i++) {
			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                    p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1.0f, 2.0f);
							        		p.getWorld().spawnParticle(Particle.CLOUD, l, 40,4,4,4);
											p.getWorld().spawnParticle(Particle.SOUL, l, 300, 4,4,4,0);
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
													LivingEntity le = (LivingEntity)e;
													atk0(0.315, tsd.Charm.get(p.getUniqueId())*0.3455, p, le);
								                    le.teleport(l);
												}
											}
						                }
			                	   }, i*10); 
							}
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
					        		p.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, l, 1,1,1,1);
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
											LivingEntity le = (LivingEntity)e;
											atk0(1.37, tsd.Charm.get(p.getUniqueId())*1.1, p, le);
										}
									}
				                }
	                	   }, 70); 
						});
				bd.execute();
				
				}
		}
	}
	
	

	
	public void BindingFormation(PlayerSwapHandItemsEvent ev) 
	{
	    
		Player p = ev.getPlayer();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 10 &&ergs.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && p.isSneaking())
			{
				ev.setCancelled(true);

				
            	if(ergst.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(ergst.get(p.getUniqueId()));
            		ergst.remove(p.getUniqueId());
            	}
				ergs.remove(p.getUniqueId());
				

            	if(karbt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(karbt.get(p.getUniqueId()));
            		karbt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							karb.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						karb.remove(p.getUniqueId());
	                }
	            }, 40); 
            	karbt.put(p.getUniqueId(), task);
            	
                final Location l = gettargetblock(p,4);
                AtomicInteger j = new AtomicInteger(1);
                
            	for(int i =0; i<10; i++) {
            		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	double a = j.getAndIncrement()/2d;
		                    p.playSound(p.getLocation(), Sound.BLOCK_ROOTS_PLACE, 1f, j.get()*0.2f);
		                    
		        			p.getWorld().spawnParticle(Particle.ENCHANT, l,200*j.get(),2+a,0.5,2+a);
		        			p.getWorld().spawnParticle(Particle.TRIAL_SPAWNER_DETECTION_OMINOUS, l,200*j.get(),2+a,0.5,2+a);
			        		
			        		
		                	for (Entity e : p.getWorld().getNearbyEntities(l, 2+a , 2+a, 2+a))
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
									atk0(0.115, tsd.Charm.get(p.getUniqueId())*0.2455, p, le);
				                    le.teleport(l);
				                    Holding.superholding(p, le, 5l);
								}
							}
		                }
            	   }, i*5); 
				}
			}
		}
	}

	
	public void EnergyBlast(PlayerSwapHandItemsEvent ev) 
	{
	    
		Player p = ev.getPlayer();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 10 &&karb.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && p.isSneaking())
			{
				ev.setCancelled(true);
				
            	if(karbt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(karbt.get(p.getUniqueId()));
            		karbt.remove(p.getUniqueId());
            	}
				karb.remove(p.getUniqueId());

                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 0.6f);
            	p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(),6, 2,1,2);

                for(int i = 0; i<12; i++) {
    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() 
    	                {
    	                	for(int i = 0; i <10; i ++) {
        	                	Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation().clone().add(0, -0.5, 0), p.getEyeLocation().clone().getDirection().normalize().multiply(2.5), 1, 20);
        	                	ar.remove();
        	                	
        	                	Item it = p.getWorld().dropItem(p.getEyeLocation().clone().add(0, -0.5, 0), new ItemStack(Material.ENCHANTED_BOOK));
        	                	it.setVelocity(ar.getVelocity());
        	                	it.setThrower(p.getUniqueId());
        	                	it.setOwner(p.getUniqueId());
        	                	it.setPickupDelay(999999);
        	        			it.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
        	        			it.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), p.getName()));
        	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
        	    	                @Override
        	    	                public void run() 
        	    	                {
        	    	                	it.remove();
        	    	                }
        	    				}, 15);
    	                	}
    	    				
    						p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
    		            	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
    	                    ArrayList<Location> line = new ArrayList<Location>();
    	                    for(double d = 0.1; d <= 16.1; d += 0.2) {
    		                    Location pl = p.getEyeLocation().clone().add(0, -0.5, 0);
    							pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
    							line.add(pl);
    	                    }
    	                    p.playSound(p.getLocation(), Sound.BLOCK_SAND_BREAK, 0.5f, 1.8f);
    	                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.25f, 1.9f);
    	                    line.forEach(l -> {

    	                    	p.getWorld().spawnParticle(Particle.FLASH, l,1, 0.1,0.1,0.1,0);
    	                    	p.getWorld().spawnParticle(Particle.SCULK_SOUL, l,10, 2,2,2,0);
    	                    	for (Entity a : p.getWorld().getNearbyEntities(l, 2.5, 2.5, 2.5))
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
    	                    });
    						les.forEach(le -> {
    							p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
    							atk0(0.1, tsd.Charm.get(p.getUniqueId())*0.12, p, le);
    		                    Holding.superholding(p, le, 36l);
    							
    						}	);
    									
    	                }
    				}, i*2+ 6);
                }
			}
		}
	}
	
	
	
	
	public void Wave(PlayerInteractEvent ev) 
	{
		final Player p = ev.getPlayer();
		Action ac = ev.getAction();

	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 10&& tsd.Wave.get(p.getUniqueId())>=1) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
			{

				double sec =2.5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				p.setCooldown(CAREFUL, 2);
				ev.setCancelled(true);
				if(!auras.containsKey(p.getName())) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("먼저 기운을 활성화 시켜야됩니다").create());
					}
					else {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You Should Activate Aura").create());
					}
                    return;
				}
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("기공권")
						.ename("Wave")
						.slot(2)
						.hm(frcooldown)
						.skillUse(() -> {
		                	if(chslt.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(chslt.get(p.getUniqueId()));
		                		chslt.remove(p.getUniqueId());
		                	}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									if(Proficiency.getpro(p)>=1) {
										chsl.putIfAbsent(p.getUniqueId(), 0);
									}
				                }
				            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									chsl.remove(p.getUniqueId());
				                }
				            }, 32); 
		                	chslt.put(p.getUniqueId(), task);
		                    
		                    ArrayList<Location> line = new ArrayList<Location>();
		                    p.playSound(p.getLocation(), Sound.ENTITY_BREEZE_SHOOT, 0.7f, 2f);
		                    AtomicInteger j = new AtomicInteger(0);
		                    for(double d = 0.1; d <= 5; d += 0.05) {
			                    Location pl = p.getEyeLocation();
								pl.add(pl.getDirection().normalize().multiply(d));
								if(pl.getBlock().isPassable()) {
									line.add(pl);
								}
								else {
									break;
								}
		                    }
	                    	line.forEach(i -> {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {
				             			if(auras.get(p.getName()) == 0)
										{ 
											p.getWorld().spawnParticle(Particle.BLOCK, i, 4, 0.1,0.1,0.1,0 ,Material.BLACK_STAINED_GLASS.createBlockData());
											p.getWorld().spawnParticle(Particle.BLOCK, i, 4, 0.1,0.1,0.1,0 ,Material.WHITE_STAINED_GLASS.createBlockData());
										}
					                    if(auras.get(p.getName()) == 1)
										{
											p.getWorld().spawnParticle(Particle.BLOCK, i, 4, 0.1,0.1,0.1,0 ,Material.BLUE_STAINED_GLASS.createBlockData());
											p.getWorld().spawnParticle(Particle.BLOCK, i, 4, 0.1,0.1,0.1,0 ,Material.RED_STAINED_GLASS.createBlockData());
										}
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
								             	le.teleport(i);
											}
										}
						            }
			                	   }, j.incrementAndGet()/20); 
							}); 
		                    	
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                    p.playSound(line.get(line.size()-1), Sound.ENTITY_BREEZE_WIND_BURST, 0.7f, 2f);
									p.getWorld().spawnParticle(Particle.FLASH, line.get(line.size()-1), 1, 1,1,1);
					        		p.getWorld().spawnParticle(Particle.ENCHANTED_HIT, line.get(line.size()-1), 10,1,1,1);
			             			if(auras.get(p.getName()) == 0)
									{ 
										p.getWorld().spawnParticle(Particle.BLOCK, line.get(line.size()-1), 50, 1,1,1,1 ,Material.GRAY_GLAZED_TERRACOTTA.createBlockData());
										p.getWorld().spawnParticle(Particle.BLOCK, line.get(line.size()-1), 50, 1,1,1,1 ,Material.WHITE_GLAZED_TERRACOTTA.createBlockData());
									}
				                    if(auras.get(p.getName()) == 1)
									{
										p.getWorld().spawnParticle(Particle.BLOCK, line.get(line.size()-1), 50, 1,1,1,1 ,Material.BLUE_GLAZED_TERRACOTTA.createBlockData());
										p.getWorld().spawnParticle(Particle.BLOCK, line.get(line.size()-1), 50, 1,1,1,1 ,Material.RED_GLAZED_TERRACOTTA.createBlockData());
									}
				                	for (Entity e : p.getWorld().getNearbyEntities(line.get(line.size()-1), 3, 3, 3))
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
											atk0(0.67, tsd.Wave.get(p.getUniqueId())*0.64, p, le);
											
										}
									}
				                }
		                    }, j.incrementAndGet()/20+5); 
						});
				bd.execute();
			
			}
		}}
	}
	


	final private ArrayList<Location> CharmSlash1(Player p, Location il, int in) {

        ArrayList<Location> line = new ArrayList<Location>();
        AtomicInteger j = new AtomicInteger();
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.8f, 1.5f);
        p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 0.8f, 1.5f);
        p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 0.8f, 0.5f);
        for(double an = Math.PI/4; an>-Math.PI/4; an-=Math.PI/180) {
        	Location pl = p.getEyeLocation();
        	pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(4.5));
        	line.add(pl);
        }
        line.forEach(l -> {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
            		if(in == 0) {
            			l.getWorld().spawnParticle(Particle.BLOCK, l,4,0.1,0.1,0.1,0 ,Material.BLACK_STAINED_GLASS.createBlockData());
            		}
            		if(in == 1) {
            			l.getWorld().spawnParticle(Particle.BLOCK, l,4,0.1,0.1,0.1,0 ,Material.RED_STAINED_GLASS.createBlockData());
            		}
                }
			}, j.incrementAndGet()/900); 
        	
        });
	    return line;
	}


	final private ArrayList<Location> CharmSlash2(Player p, Location il, int in) {

        ArrayList<Location> line = new ArrayList<Location>();
        AtomicInteger j = new AtomicInteger();
        p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 0.8f,2f);
        p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 0.8f, 1.5f);
        for(double an = -Math.PI/4; an<Math.PI/4; an+=Math.PI/180) {
        	Location pl = p.getEyeLocation();
        	pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(4.5));
        	line.add(pl);
        }
        line.forEach(l -> {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
            		if(in == 0) {
            			l.getWorld().spawnParticle(Particle.BLOCK, l,4,0.1,0.1,0.1,0 ,Material.WHITE_STAINED_GLASS.createBlockData());
            			l.getWorld().spawnParticle(Particle.SMALL_FLAME, l,2,0.5,0.5,0.5,0);
            		}
            		if(in == 1) {
            			l.getWorld().spawnParticle(Particle.BLOCK, l,4,0.1,0.1,0.1,0 ,Material.BLUE_STAINED_GLASS.createBlockData());
            			l.getWorld().spawnParticle(Particle.SMALL_FLAME, l,2,0.5,0.5,0.5,0);
            		}
                }
			}, j.incrementAndGet()/900); 
        	
        });
	    return line;
	}
	
	
	public void CharmSlash(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 10 &&chsl.containsKey(p.getUniqueId())) {
			if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
				{
					p.setCooldown(CAREFUL, 2);
					ev.setCancelled(true);
					
	            	if(chslt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(chslt.get(p.getUniqueId()));
	            		chslt.remove(p.getUniqueId());
	            	}
					chsl.remove(p.getUniqueId());

	            	if(sprsrt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(sprsrt.get(p.getUniqueId()));
	            		sprsrt.remove(p.getUniqueId());
	            	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=2) {
								sprsr.putIfAbsent(p.getUniqueId(), 0);
							}
		                }
		            }, 6); 

	        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							sprsr.remove(p.getUniqueId());
		                }
		            }, 35); 
	            	sprsrt.put(p.getUniqueId(), task);

	            	Location tl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(2.5));
	            	CharmSlash1(p,p.getEyeLocation().clone(),auras.getOrDefault(p.getName(), -1));
					for(Entity e : p.getWorld().getNearbyEntities(tl,5, 3, 5)) {
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
							atk0(0.68, tsd.Wave.get(p.getUniqueId())*0.76, p, le);
							le.teleport(tl);
						}
					}
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
			            	CharmSlash2(p,p.getEyeLocation().clone(),auras.getOrDefault(p.getName(), -1));
							for(Entity e : p.getWorld().getNearbyEntities(tl,5, 3, 5)) {
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
									atk0(0.68, tsd.Wave.get(p.getUniqueId())*0.76, p, le);
								}
							}
		                }
		            }, 4); 
				}
			}
		}
	}



	final private ArrayList<Location> SpiritStorm1(Location il, int in) {


        ArrayList<Location> cir = new ArrayList<Location>();
        for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/90) {
        	Location one = il.clone();
        	one.add(one.getDirection().clone().rotateAroundY(Math.PI/5).rotateAroundAxis(il.clone().getDirection(),angle).normalize().multiply(angle));
        	cir.add(one);
        	Location one1 = il.clone();
        	one1.add(one1.getDirection().clone().rotateAroundY(-Math.PI/5).rotateAroundAxis(il.clone().getDirection(),angle).normalize().multiply(angle));
        	cir.add(one1);
        }
    	cir.forEach(l -> {
    		if(in == 0) {
    			l.getWorld().spawnParticle(Particle.BLOCK, l,2,0.1,0.1,0.1,0 ,Material.BLACK_STAINED_GLASS.createBlockData());
    			l.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, l,1,0.1,0.1,0.1,0.3);
    		}
    		if(in == 1) {
    			l.getWorld().spawnParticle(Particle.BLOCK, l,2,0.1,0.1,0.1,0 ,Material.RED_STAINED_GLASS.createBlockData());
    			l.getWorld().spawnParticle(Particle.DRIPPING_LAVA, l,1,0.1,0.1,0.1,0.3);
    		}
	    });
	    return cir;
	}


	final private ArrayList<Location> SpiritStorm2(Location il, int in) {


        ArrayList<Location> cir = new ArrayList<Location>();
        for(double angle=0.1; angle>-Math.PI*2; angle -= Math.PI/90) {
        	Location one = il.clone();
        	one.add(one.getDirection().clone().rotateAroundY(-Math.PI/5).rotateAroundAxis(il.clone().getDirection(),angle).normalize().multiply(-angle));
        	cir.add(one);
        	Location one1 = il.clone();
        	one1.add(one1.getDirection().clone().rotateAroundY(Math.PI/5).rotateAroundAxis(il.clone().getDirection(),angle).normalize().multiply(-angle));
        	cir.add(one1);
        }
    	cir.forEach(l -> {
    		if(in == 0) {
    			l.getWorld().spawnParticle(Particle.BLOCK, l,2,0.1,0.1,0.1,0 ,Material.WHITE_STAINED_GLASS.createBlockData());
    			l.getWorld().spawnParticle(Particle.END_ROD, l,1,0.1,0.1,0.1,0.3);
    		}
    		if(in == 1) {
    			l.getWorld().spawnParticle(Particle.BLOCK, l,2,0.1,0.1,0.1,0 ,Material.GREEN_STAINED_GLASS.createBlockData());
    			l.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l,1,0.1,0.1,0.1,0.3);
    		}
	    });
	    return cir;
	}
	
	
	
	
	
	public void SpiritStorm(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 10 &&sprsr.containsKey(p.getUniqueId())) {
			if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
				{
					p.setCooldown(CAREFUL, 2);
					ev.setCancelled(true);
					
	            	if(sprsrt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(sprsrt.get(p.getUniqueId()));
	            		sprsrt.remove(p.getUniqueId());
	            	}
					sprsr.remove(p.getUniqueId());

                    
					final Location tl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(3));
	                
					
	            	for(int i =1; i<5; i++) {
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				        			tl.getWorld().spawnParticle(Particle.FLASH, tl,5,2,2,2);
				                    p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 0.7f, 0.8f);
				                    p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 0.7f, 1.8f);
				                    p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 0.7f, 1.7f);
									SpiritStorm1(p.getLocation().clone(), auras.getOrDefault(p.getName(), -1));
									SpiritStorm2(p.getLocation().clone(), auras.getOrDefault(p.getName(), -1));
				                	for (Entity e : p.getWorld().getNearbyEntities(tl, 6 , 6, 6))
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
											atk0(0.115, tsd.Charm.get(p.getUniqueId())*0.2455, p, le);
						                    le.teleport(tl);
										}
									}
				                }
	                	   }, i*2); 
					}
					
					
				}
			}
		}
	}
	
	
	public void KiVibe(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 10&& d.getDamage()>0) {
			
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
				{
					d.setDamage(d.getDamage()+tsd.Vibe.get(p.getUniqueId())*0.6);
				}
			}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity) 
		{
			Projectile ar = (Projectile)d.getDamager();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player)ar.getShooter();

				
				
				if(ClassData.pc.get(p.getUniqueId()) == 10 && d.getDamage()>0) {
					
						if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
						{
							d.setDamage(d.getDamage()+tsd.Vibe.get(p.getUniqueId())*0.6);
						}
					}
			}
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public void Flip(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action a = ev.getAction();
        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 10&& tsd.Flip.get(p.getUniqueId())>=1 && !p.hasCooldown(CAREFUL)) {	
			if((a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK)&&(a!= Action.RIGHT_CLICK_AIR)&&(a!= Action.RIGHT_CLICK_AIR)&& !p.isSneaking()&& !p.isOnGround())
			{	
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
				{
					double sec =7*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
					ev.setCancelled(true);

					if(!auras.containsKey(p.getName())) {
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("먼저 기운을 활성화 시켜야됩니다").create());
						}
						else {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You Should Activate Aura").create());
						}
	                    return;
					}
					

					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("공중제비")
							.ename("Flip")
							.slot(3)
							.hm(sdcooldown)
							.skillUse(() -> {
			                	if(shpt.containsKey(p.getUniqueId())) {
			                		Bukkit.getScheduler().cancelTask(shpt.get(p.getUniqueId()));
			                		shpt.remove(p.getUniqueId());
			                	}

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										if(Proficiency.getpro(p)>=1) {
											shp.putIfAbsent(p.getUniqueId(), 0);
										}
					                }
					            }, 3); 

			            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										shp.remove(p.getUniqueId());
					                }
					            }, 40); 
			                	shpt.put(p.getUniqueId(), task);
			                    
			                    final Location tl = p.getLocation().setDirection(p.getEyeLocation().clone().getDirection().normalize().multiply(-1));
			                    for(int i = 0; i<25; i++) {
			                    	Location tpl = tl.clone().add(tl.clone().getDirection().normalize().multiply(0.2*i));
			                    	tpl.setDirection(p.getEyeLocation().getDirection());
			                    	if(tpl.getBlock().isPassable()) {
			                    		p.teleport(tpl);
			                    	}
			                    	else {
			                    		break;
			                    	}
			                    }
			                    p.playSound(p.getLocation(), Sound.ITEM_ELYTRA_FLYING, 0.3f, 2f);
			                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 60, 3, false, false));
			                    
								ItemStack TortoiseCharm = new ItemStack(Material.GRAY_BANNER, 1);									
								ItemStack DragonCharm = new ItemStack(Material.BLUE_BANNER, 1); 
								ItemStack BirdCharm = new ItemStack(Material.RED_BANNER, 1); 
								ItemStack TigerCharm = new ItemStack(Material.WHITE_BANNER, 1); 
			                    p.playSound(p.getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 1.0f, 2f);
			                    
			                    if(auras.get(p.getName()) == 0)
								{ 
									Snowball charm = p.launchProjectile(Snowball.class);
									charm.setShooter(p);
									charm.setInvulnerable(true);
									charm.setGlowing(true);
									charm.setMetadata("charm", new FixedMetadataValue(RMain.getInstance(), true));
									charm.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
									charm.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
									charm.setItem(TortoiseCharm);
									charm.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(1.8));

									Snowball charm1 = p.launchProjectile(Snowball.class);
									charm1.setShooter(p);
									charm1.setInvulnerable(true);
									charm1.setGlowing(true);
									charm1.setMetadata("charm", new FixedMetadataValue(RMain.getInstance(), true));
									charm1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
									charm1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
									charm1.setItem(TigerCharm);
									charm1.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(1.6));
								}
			                    if(auras.get(p.getName()) == 1)
								{
									Snowball charm = p.launchProjectile(Snowball.class);
									charm.setShooter(p);
									charm.setInvulnerable(true);
									charm.setGlowing(true);
									charm.setMetadata("charm", new FixedMetadataValue(RMain.getInstance(), true));
									charm.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
									charm.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
									charm.setItem(DragonCharm);
									charm.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(1.8));

									Snowball charm1 = p.launchProjectile(Snowball.class);
									charm1.setShooter(p);
									charm1.setInvulnerable(true);
									charm1.setGlowing(true);
									charm1.setMetadata("charm", new FixedMetadataValue(RMain.getInstance(), true));
									charm1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
									charm1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
									charm1.setItem(BirdCharm);
									charm1.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(1.6));
								}
							});
					bd.execute();
				}
			}
			}
	}
	

	public void Flip(ProjectileHitEvent d) 
	{
		if(d.getEntity().hasMetadata("charm")) {
			Player p = (Player) d.getEntity().getShooter();
			Location l = d.getEntity().getLocation();
			p.getWorld().spawnParticle(Particle.COMPOSTER, d.getEntity().getLocation(), 200, 2,2,2,1);
			p.getWorld().spawnParticle(Particle.WARPED_SPORE, d.getEntity().getLocation(), 200, 2,2,2,1);
			p.playSound(d.getEntity().getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 0.85f, 1.15f);

			for(int i=0; i<3; i++) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
	                {	
	                    p.playSound(l, Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.69f, 2f);
     					l.getWorld().spawnParticle(Particle.EXPLOSION, l, 5,2,2,2);
     					l.getWorld().spawnParticle(Particle.FLASH, l, 15,4,4,4);
     					for(Entity e : p.getWorld().getNearbyEntities(l, 4, 4, 4)) {
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
     						if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
     							LivingEntity le = (LivingEntity) e;
								atk0(0.34, tsd.Flip.get(p.getUniqueId())*0.34, p, le);
								Holding.holding(p, le, 6l);
     						}
     					}
		            }
            	   }, i*5); 
			}
		}
	}
	

	
	@SuppressWarnings("deprecation")
	public void Shunpo(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action a = ev.getAction();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 10 && !p.hasCooldown(CAREFUL) && shp.containsKey(p.getUniqueId())) {	
			if((a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK)&&(a!= Action.RIGHT_CLICK_AIR)&&(a!= Action.RIGHT_CLICK_AIR)&& !p.isSneaking()&& !p.isOnGround())
			{	
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
				{
					ev.setCancelled(true);
					
	            	if(shpt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(shpt.get(p.getUniqueId()));
	            		shpt.remove(p.getUniqueId());
	            	}
					shp.remove(p.getUniqueId());

		        	p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 10, 1, 2, 1);
					for(int h=0; h<50; h++) {
						if(p.getLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(0.1)).getBlock().isPassable()) {
				        	p.getWorld().spawnParticle(Particle.FALLING_SPORE_BLOSSOM, p.getLocation(), 3, 1, 2, 1);
							p.teleport(p.getLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(0.1)));
						}
						else {
							break;
						}
					}
	                p.playSound(p.getLocation(), Sound.BLOCK_BIG_DRIPLEAF_STEP, 1, 2);
	                p.playSound(p.getLocation(), Sound.BLOCK_AZALEA_LEAVES_STEP, 1, 2);
	                p.playSound(p.getLocation(), Sound.BLOCK_BIG_DRIPLEAF_STEP, 1, 0);
	                p.playSound(p.getLocation(), Sound.BLOCK_AZALEA_LEAVES_STEP, 1, 0);
 					for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 3, 3, 3)) {
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
 						if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
 							LivingEntity le = (LivingEntity) e;
							atk0(0.9, tsd.Flip.get(p.getUniqueId())*0.9, p, le);
							Holding.holding(p, le, 3l);
 						}
 					}
					
					
				}
			}
			}
	}
	
	

	
	public void CombustInside(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&& !(d.getEntity().hasMetadata("fake"))&& !(d.getEntity().hasMetadata("portal"))) 
		{
		Player p = (Player)d.getDamager();
		final LivingEntity le = (LivingEntity)d.getEntity();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 10&& tsd.CombustInside.get(p.getUniqueId())>=1 && !p.hasCooldown(Material.YELLOW_TERRACOTTA))	
		{
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
		    
			double sec =6*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && p.isSneaking()&&!d.isCancelled())
				{
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("잠해소비")
							.ename("CombustInside")
							.slot(4)
							.hm(smcooldown)
							.skillUse(() -> {
								p.playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1.0f, 2.0f);
								p.getWorld().spawnParticle(Particle.ASH, le.getLocation(), 100, 1, 1, 1);
								Holding.superholding(p, le, (long) 40);
						            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
        									atk0(1.8, tsd.CombustInside.get(p.getUniqueId())*1.8, p, le);								
											p.playSound(le.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 0.6f, 0f);
											p.playSound(le.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 0.6f, 2f);
											p.getWorld().spawnParticle(Particle.SOUL, le.getLocation(), 100, 1, 1, 1);
											if(Proficiency.getpro(p)>=1) {
							 					for(Entity e : le.getWorld().getNearbyEntities(le.getLocation(), 2.5, 2.5, 2.5)) {
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
							 						if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
							 							LivingEntity le1 = (LivingEntity) e;
			        									atk0(1.8, tsd.CombustInside.get(p.getUniqueId())*1.8, p, le1);
							 						}
							 					}
											}
						                }
						            }, 40); 
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
		ItemStack is = p.getInventory().getItemInMainHand();
			if(ClassData.pc.get(p.getUniqueId()) == 10 && ev.getNewSlot()==3  && is.getType().name().contains("BANNER_PATTERN") && is.hasItemMeta() && is.getItemMeta().hasCustomModelData() && p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
				ev.setCancelled(true);
				p.setCooldown(CAREFUL, 2);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(75/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
						.kname("명상")
						.ename("Meditation")
						.slot(6)
						.hm(sultcooldown)
						.skillUse(() -> {

		                    p.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 1.0f, 1f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 0f);
		                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_MOOD, 1.0f, 2f);
							p.getWorld().spawnParticle(Particle.TOTEM_OF_UNDYING, p.getLocation(), 100, 3, 3, 3);
							p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 130, 0, false, false));
		                    Holding.invur(p, 136l);
							p.setGravity(false);
		                    p.teleport(p.getLocation().add(0, 1, 0));
		                    for(int i=0; i<60; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                 		@Override
			    		                	public void run() 
			    			                {	
												p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 0, false, false));
	    										p.getWorld().spawnParticle(Particle.TOTEM_OF_UNDYING, p.getLocation(), 20, 3, 3, 3);
			                 					for (Entity e : p.getNearbyEntities(5, 5, 5))
			            						{
			                                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
			            							{
			            								LivingEntity le = (LivingEntity)e;
	            										if (le instanceof Player) 
	            										{
	            											
	            											Player p1 = (Player) le;
	            											if(Party.hasParty(p) && Party.hasParty(p1))	{
	            											if(Party.getParty(p).equals(Party.getParty(p1)))
	            												{
	            													p1.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 0, false, false));
	            													continue;
	            												}
	            											}
	            										}
			        									atk1(0.3, p, le);	
			            									
			            							}
			            						}
			    				            }
			    	                	   }, i*2); 
							}
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                 		@Override
		    		                	public void run() 
		    			                {	
		    						
		                 					p.setGravity(true);
											p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 60, 0, false, false));
		            						
		    				            }
		                    }, 130); 
		                    
						});
				bd.execute();
			}
    }
	


	final private HashSet<Location> ULTup1(Location il) {


        HashSet<Location> up = new HashSet<Location>();
        for(double d = 0; d<6; d+= 0.5) {
            for(double angle=Math.PI; angle<Math.PI*2; angle += Math.PI/45) {
            	Location one = il.clone();
            	one.add(one.getDirection().clone().rotateAroundY(angle).normalize().multiply(d));
            	up.add(one);
            }
        }
        return up;
	}


	final private HashSet<Location> ULTup2(Location il) {


        HashSet<Location> up = ULTup1(il.clone());
        for(double d = 0; d<3; d+= 0.5) {
            for(double angle=0; angle<Math.PI*2; angle += Math.PI/45) {
            	Location one = il.clone().add(il.clone().getDirection().normalize().multiply(3));
            	one.add(one.getDirection().clone().rotateAroundY(angle).normalize().multiply(d));
            	up.add(one);
            }
        }
        return up;
	}

	final private HashSet<Location> ULTup3(Location il) {


        HashSet<Location> up = ULTup2(il.clone());
        for(double d = 0; d<3; d+= 0.5) {
            for(double angle=0; angle<Math.PI*2; angle += Math.PI/45) {
            	Location one = il.clone().add(il.clone().getDirection().normalize().multiply(-3));
            	one.add(one.getDirection().clone().rotateAroundY(angle).normalize().multiply(d));
            	up.removeIf(l -> l.distance(one)<=0.25);
            }
        }
        return up;
	}


	static private  HashSet<Location> ULTdown1(Location il) {


        HashSet<Location> down = new HashSet<Location>();
        for(double d = 0; d<6; d+= 0.5 ) {
            for(double angle=-Math.PI; angle>-Math.PI*2; angle -= Math.PI/45) {
            	Location one = il.clone();
            	one.add(one.getDirection().clone().rotateAroundY(angle).normalize().multiply(d));
            	down.add(one);
            }
        }
        return down;
	}


	static private  HashSet<Location> ULTdown2(Location il) {


        HashSet<Location> down = ULTdown1(il.clone());
        for(double d = 0; d<3; d+= 0.5 ) {
            for(double angle=0; angle<Math.PI*2; angle += Math.PI/45) {
            	Location one = il.clone().add(il.clone().getDirection().normalize().multiply(-3));
            	one.add(one.getDirection().clone().rotateAroundY(angle).normalize().multiply(d));
            	down.add(one);
            }
        }
        return down;
	}

	static private  HashSet<Location> ULTdown3(Location il) {


        HashSet<Location> down = ULTdown2(il.clone());
        for(double d = 0; d<3; d+= 0.5 ) {
            for(double angle=0; angle<Math.PI*2; angle += Math.PI/45) {
            	Location one = il.clone().add(il.clone().getDirection().normalize().multiply(3));
            	one.add(one.getDirection().clone().rotateAroundY(angle).normalize().multiply(d));
            	down.removeIf(l -> l.distance(one)<=0.25);
            }
        }
        return down;
	}
	final private void ULT2cir(Location il) {


        HashSet<Location> up = ULTup3(il.clone());
        HashSet<Location> down = ULTdown3(il.clone());
        up.forEach(l -> {
        	l.getWorld().spawnParticle(Particle.DRIPPING_LAVA, l, 4,0.2,0.2,0.2,0);
        });
        il.getWorld().spawnParticle(Particle.DRIPPING_LAVA, il.clone().add(-3, 0.3, 0), 400,0.7,0.25,0.7,0);
        down.forEach(l -> {
        	l.getWorld().spawnParticle(Particle.DRIPPING_WATER, l, 4,0.2,0.2,0.2,0);
        });
        il.getWorld().spawnParticle(Particle.DRIPPING_WATER, il.clone().add(3, 0.3, 0), 400,0.7,0.25,0.7,0);
    	return;
	}

	final private HashSet<Location> Sky(Location il) {

        HashSet<Location> line = new HashSet<>();
    	Location one = il.clone().add(0,0,10);
        for(double d = -2; d<2; d+= 0.25 ) {
        	line.add(one.clone().add(d,0,0));
        	line.add(one.clone().add(d,0,-1));
        	line.add(one.clone().add(d,0,-2));
        }
        return line;
		
	}

	final private HashSet<Location> Lake(Location il) {

        HashSet<Location> line = new HashSet<>();
    	Location one = il.clone().add(Math.sqrt(50),0,Math.sqrt(50));
        for(double d = -2; d<2; d+= 0.25 ) {
        	if(Math.abs(d)>0.5) {
            	line.add(one.clone().add(-d,0,d));
        	}
        	line.add(one.clone().add(-d-0.5,0,d-0.5));
        	line.add(one.clone().add(-d-1,0,d-1));
        }
        return line;
		
	}
	final private HashSet<Location> Fire(Location il) {

        HashSet<Location> line = new HashSet<>();
    	Location one = il.clone().add(10,0,0);
        for(double d = -2; d<2; d+= 0.25 ) {
        	line.add(one.clone().add(0,0,d));
        	if(Math.abs(d)>0.5) {
            	line.add(one.clone().add(-1,0,d));
        	}
        	line.add(one.clone().add(-2,0,d));
        }
        return line;
		
	}
	final private HashSet<Location> Thunder(Location il) {

        HashSet<Location> line = new HashSet<>();
    	Location one = il.clone().add(Math.sqrt(50),0,-Math.sqrt(50));
        for(double d = -2; d<2; d+= 0.25 ) {
        	if(Math.abs(d)>0.5) {
            	line.add(one.clone().add(-d,0,-d));
            	line.add(one.clone().add(-d-0.5,0,-d+0.5));
        	}
        	line.add(one.clone().add(-d-1,0,-d+1));
        }
        return line;
		
	}
	final private HashSet<Location> Wind(Location il) {

        HashSet<Location> line = new HashSet<>();
    	Location one = il.clone().add(-Math.sqrt(50),0,Math.sqrt(50));
        for(double d = -2; d<2; d+= 0.25 ) {
        	line.add(one.clone().add(-d,0,-d));
        	line.add(one.clone().add(-d+0.5,0,-d-0.5));
        	if(Math.abs(d)>0.5) {
            	line.add(one.clone().add(-d+1,0,-d-1));
        	}
        }
        return line;
		
	}
	final private HashSet<Location> Water(Location il) {

        HashSet<Location> line = new HashSet<>();
    	Location one = il.clone().add(-10,0,0);
        for(double d = -2; d<2; d+= 0.25 ) {
        	line.add(one.clone().add(1,0,d));
        	if(Math.abs(d)>0.5) {
            	line.add(one.clone().add(0,0,d));
            	line.add(one.clone().add(2,0,d));
        	}
        }
        return line;
		
	}
	final private HashSet<Location> Mountain(Location il) {

        HashSet<Location> line = new HashSet<>();
    	Location one = il.clone().add(-Math.sqrt(50),0,-Math.sqrt(50));
        for(double d = -2; d<2; d+= 0.25 ) {
        	line.add(one.clone().add(-d,0,d));
        	if(Math.abs(d)>0.5) {
            	line.add(one.clone().add(-d+0.5,0,d+0.5));
            	line.add(one.clone().add(-d+1,0,d+1));
        	}
        }
        return line;
		
	}
	final private HashSet<Location> Earth(Location il) {

        HashSet<Location> line = new HashSet<>();
    	Location one = il.clone().add(0,0,-10);
        for(double d = -2; d<2; d+= 0.25 ) {
        	if(Math.abs(d)>0.5) {
            	line.add(one.clone().add(d,0,0));
            	line.add(one.clone().add(d,0,1));
            	line.add(one.clone().add(d,0,2));
        	}
        }
        return line;
		
	}

	final private void Eight(Location il, int a) {

        HashSet<Location> sky = Sky(il.clone());
        HashSet<Location> lake = Lake(il.clone());
        HashSet<Location> fire = Fire(il.clone());
        HashSet<Location> thun = Thunder(il.clone());
        HashSet<Location> wind = Wind(il.clone());
        HashSet<Location> water = Water(il.clone());
        HashSet<Location> mount= Mountain(il.clone());
        HashSet<Location> earth = Earth(il.clone());
        if(a == 0 || a ==1) {
            sky.forEach(l -> {
            	l.getWorld().spawnParticle(Particle.SCRAPE, l,3,0.05,0.05,0.05,0);
            });
        }
        if(a == 0 || a ==2) {
            lake.forEach(l -> {
            	l.getWorld().spawnParticle(Particle.CLOUD, l,3,0.05,0.05,0.05,0);
            });
        }
        if(a == 0 || a ==3) {
            fire.forEach(l -> {
            	l.getWorld().spawnParticle(Particle.FLAME, l,3,0.05,0.05,0.05,0);
            });
        }
        if(a == 0 || a ==4) {
            thun.forEach(l -> {
            	l.getWorld().spawnParticle(Particle.END_ROD, l,3,0.05,0.05,0.05,0);
            });
        }
        if(a == 0 || a ==5) {
            wind.forEach(l -> {
            	l.getWorld().spawnParticle(Particle.WAX_ON, l,3,0.05,0.05,0.05,0);
            });
        }
        if(a == 0 || a ==6) {
            water.forEach(l -> {
            	l.getWorld().spawnParticle(Particle.DOLPHIN, l, 3,0.05,0.05,0.05,0);
            });
        }
        if(a == 0 || a ==7) {
            mount.forEach(l -> {
            	l.getWorld().spawnParticle(Particle.DRAGON_BREATH, l, 3,0.05,0.05,0.05,0);
            });
        }
        if(a == 0 || a ==8) {
            earth.forEach(l -> {
            	l.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, l, 3,0.05,0.05,0.05,0);
            });
        }
    	return;
	}
	

	
	public void ULT2(PlayerItemHeldEvent ev)
	{
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}

		ItemStack is = p.getInventory().getItemInMainHand();
		
			if(ClassData.pc.get(p.getUniqueId()) == 10 && ev.getNewSlot()==4 && is.getType().name().contains("BANNER_PATTERN") && is.hasItemMeta() && is.getItemMeta().hasCustomModelData() && !p.isSneaking() && p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
				p.setCooldown(CAREFUL, 2);
                final Location cl = p.getLocation().clone().setDirection(p.getLocation().clone().add(1, 0, 0).toVector().subtract(p.getLocation().toVector())).add(0, 1.5, 0);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(71*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
						.kname("음양팔괘진")
						.ename("Eight Trigrams Formation")
						.slot(7)
						.hm(sult2cooldown)
						.skillUse(() -> {

		                    Location fl = p.getLocation().clone();
		                    Location tel = fl.clone().add(0, 15, 0);
		                    tel.setDirection(fl.clone().toVector().subtract(tel.clone().toVector()));
	     					ULT2cir(cl);
	     					p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 1, 0);
	     					p.playSound(p.getLocation(), Sound.BLOCK_LARGE_AMETHYST_BUD_PLACE, 1, 0);
	     					
	     					
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                 		@Override
		    		                	public void run() 
		    			                {	
				    	                    p.teleport(tel);
				    	                    Holding.superholding(p, p, 70l);
				    	                    AtomicInteger j = new AtomicInteger();

				    	                    for(int i=0; i<8; i++) {
				    		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				    		                 		@Override
				    		    		                	public void run() 
				    		    			                {	
				    		                 					p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 1, 1);
				    		                 					p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_HIT, 1, 1);
				    		                 					Eight(cl, j.incrementAndGet());
				    		                 					
				    		                 					for (Entity e : cl.getWorld().getNearbyEntities(cl,15, 15, 15))
				    		            						{
				    		                                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
				    		            							{
				    		            								LivingEntity le = (LivingEntity)e;
				                										if (le instanceof Player) 
				                										{
				                											
				                											Player p1 = (Player) le;
				                											if(Party.hasParty(p) && Party.hasParty(p1))	{
				                											if(Party.getParty(p).equals(Party.getParty(p1)))
				                												{
			                													p1.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 1, false, false));
			                													Holding.invur(p1, 40l);
				                													continue;
				                												}
				                											}
				                										}
				    		        									atk1(1.5, p, le);	
				    		        									Holding.holding(p, le, 10l);
				    		            									
				    		            							}
				    		            						}
				    		    				            }
				    		    	                	   }, i*5+10); 
				    						}
			    		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			    		                 		@Override
			    		    		                	public void run() 
			    		    			                {	
			    		                 					p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 1, 1);
			    		                 					p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_HIT, 1, 1);
			    		                 					p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 1);
			    		                 					p.playSound(p.getLocation(), Sound.BLOCK_LARGE_AMETHYST_BUD_BREAK, 1, 1);
			    		                 					Eight(cl, 0);
			    		                 					ULT2cir(cl);
			    		                 					
			    		                 					for (Entity e : cl.getWorld().getNearbyEntities(cl,15, 15, 15))
			    		            						{
			    		                                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
			    		            							{
			    		            								LivingEntity le = (LivingEntity)e;
			                										if (le instanceof Player) 
			                										{
			                											
			                											Player p1 = (Player) le;
			                											if(Party.hasParty(p) && Party.hasParty(p1))	{
			                											if(Party.getParty(p).equals(Party.getParty(p1)))
			                												{
			                													p1.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 0, false, false));
			                													continue;
			                												}
			                											}
			                										}
			    		        									atk1(3.5, p, le);	
			    		        									le.teleport(cl);
			    		        									Holding.holding(p, le, 10l);
			    		            									
			    		            							}
			    		            						}
			    		    				            }
			    		    	                	   }, 60); 
			    		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			    		                 		@Override
			    		    		                	public void run() 
			    		    			                {	
													p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 0, false, false));
													p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2, false, false));
													p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 200, 2, false, false));
													p.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 200, 2, false, false));
													p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 40, 2, false, false));
													p.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200, 2, false, false));
													p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 2, false, false));
													p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 200, 2, false, false));
													p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 2, false, false));
			    		                 					p.teleport(fl);
			    		                 					p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 1, 1);
			    		                 					p.playSound(p.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
			    		                 	            	cl.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, cl, 10,2,2,2,0);
			    		                 					
			    		                 					for (Entity e : cl.getWorld().getNearbyEntities(cl,15, 15, 15))
			    		            						{
			    		                                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
			    		            							{
			    		            								LivingEntity le = (LivingEntity)e;
			                										if (le instanceof Player) 
			                										{
			                											
			                											Player p1 = (Player) le;
			                											if(Party.hasParty(p) && Party.hasParty(p1))	{
			                											if(Party.getParty(p).equals(Party.getParty(p1)))
			                												{
			                													p1.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 0, false, false));
			                													p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2, false, false));
			                													p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 200, 2, false, false));
			                													p1.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 200, 2, false, false));
			                													p1.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 40, 2, false, false));
			                													p1.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200, 2, false, false));
			                													p1.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 2, false, false));
			                													p1.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 200, 2, false, false));
			                													p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 2, false, false));
			                													continue;
			                												}
			                											}
			                										}
			    		        									atk1(12.5, p, le);	
			    		        									Holding.holding(p, le, 10l);
			    		            									
			    		            							}
			    		            						}
			    		    				            }
			    		    	                	   }, 70); 
		    				            }
		                    }, 2); 
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
		
			if(ClassData.pc.get(p.getUniqueId()) == 10 && (is.getType().name().contains("BANNER_PATTERN"))&& (is.hasItemMeta()) && is.getItemMeta().hasCustomModelData() && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
			}
	
    }
	
	
	
	
	public void Equipment(PlayerItemDamageEvent e) 
	{
	    
		Player p = e.getPlayer();

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 10)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
			{
					e.setCancelled(true);
			}
		}
		
	}

}