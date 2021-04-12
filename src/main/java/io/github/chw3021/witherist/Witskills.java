package io.github.chw3021.witherist;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.party.PartyData;

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
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WitherSkull;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Witskills implements Listener, Serializable {
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = -5657446578866501591L;
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> stcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private WitSkillsData wsd = new WitSkillsData(WitSkillsData.loadData(path +"/plugins/RPGskills/WitSkillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		WitSkillsData w = new WitSkillsData(WitSkillsData.loadData(path +"/plugins/RPGskills/WitSkillsData.data"));
		wsd = w;
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
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Witskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				WitSkillsData w = new WitSkillsData(WitSkillsData.loadData(path +"/plugins/RPGskills/WitSkillsData.data"));
				wsd = w;
			}
			
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		WitSkillsData w = new WitSkillsData(WitSkillsData.loadData(path +"/plugins/RPGskills/WitSkillsData.data"));
		wsd = w;
		
	}
	
	@EventHandler
	public void Roses(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 6;
        
		
		
		if(playerclass.get(p.getUniqueId()) == 13)
		{
			if((p.getInventory().getItemInMainHand().getType().name().contains("HOE")) && (p.isSneaking()))
			{
				ev.setCancelled(true);
				
                Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation();
				
				if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Roses").create());
	                }
	                else // if timer is done
	                {
	                    sdcooldown.remove(p.getName()); // removing player from HashMap
						ItemStack rose = new ItemStack(Material.WITHER_ROSE, 15);	
		        		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 100,3,3,3, Material.WITHER_ROSE.createBlockData());
		        		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 100,3,3,3, Material.OBSIDIAN.createBlockData());
						p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1.0f, 0f);
						p.playSound(p.getLocation(), Sound.ITEM_NETHER_WART_PLANT, 1.0f, 0f);
						Item roses = p.getWorld().dropItemNaturally(l, rose);
						roses.setPickupDelay(5000);
						roses.setInvulnerable(true);
						roses.setThrower(p.getUniqueId());
						roses.setMetadata("wrose of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						for(Entity e: roses.getNearbyEntities(4.5, 4.5, 4.5)) {
                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)e;
									{
										if (le instanceof Player) 
            							{
            								PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
            								Player p1 = (Player) le;
            								try {
            								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
            								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
            									{
            										continue;
            									}
            								}}
            								catch(NullPointerException ne) {
            									continue;
            								}
            							}
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
											enar.setDamage(player_damage.get(p.getName())*0.511+wsd.Roses.get(p.getUniqueId())*0.35);								
										}
										p.setSprinting(true);
					                    le.damage(0,p);
										le.damage(player_damage.get(p.getName())*0.511+wsd.Roses.get(p.getUniqueId())*0.35, p);
										le.teleport(roses);
										Holding.holding(p, le, 20l);
										le.teleport(roses);
										le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 2, false, false));
										le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2, false, false));
					                    le.playEffect(EntityEffect.HURT_EXPLOSION);
										p.setSprinting(false);
											
									}
							}
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			   
			                {
			                	Stream<Entity> wr = p.getWorld().getEntities().stream().filter(i -> i.hasMetadata("wrose of"+p.getName()));
			                	wr.forEach(i -> i.remove());
			                }
						}, 100);
						sdcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	ItemStack rose = new ItemStack(Material.WITHER_ROSE, 15);	
	        		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 100,3,3,3, Material.WITHER_ROSE.createBlockData());
	        		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 100,3,3,3, Material.OBSIDIAN.createBlockData());
					p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1.0f, 0f);
					p.playSound(p.getLocation(), Sound.ITEM_NETHER_WART_PLANT, 1.0f, 0f);
					Item roses = p.getWorld().dropItemNaturally(l, rose);
					roses.setPickupDelay(5000);
					roses.setInvulnerable(true);
					roses.setThrower(p.getUniqueId());
					roses.setMetadata("wrose of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					for(Entity e: roses.getNearbyEntities(4.5, 4.5, 4.5)) {
                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
						{
							LivingEntity le = (LivingEntity)e;
								{
									if (le instanceof Player) 
        							{
        								PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
        								Player p1 = (Player) le;
        								try {
        								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
        								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
        									{
        										continue;
        									}
        								}}
        								catch(NullPointerException ne) {
        									continue;
        								}
        							}
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
										enar.setDamage(player_damage.get(p.getName())*0.511+wsd.Roses.get(p.getUniqueId())*0.35);								
									}
									p.setSprinting(true);
				                    le.damage(0,p);
									le.damage(player_damage.get(p.getName())*0.511+wsd.Roses.get(p.getUniqueId())*0.35, p);
									le.teleport(roses);
									Holding.holding(p, le, 20l);
									le.teleport(roses);
									le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 2, false, false));
									le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2, false, false));
				                    le.playEffect(EntityEffect.HURT_EXPLOSION);
									p.setSprinting(false);
										
								}
						}
					}
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		   
		                {
		                	Stream<Entity> wr = p.getWorld().getEntities().stream().filter(i -> i.hasMetadata("wrose of"+p.getName()));
		                	wr.forEach(i -> i.remove());
		                }
					}, 100);
	                sdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
		}
		}

	@EventHandler
	public void ReapingHook(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 6;
		
		if(playerclass.get(p.getUniqueId()) == 13 && wsd.ReapingHook.get(p.getUniqueId())>=1) {
		if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("HOE"))
			{

				if(gdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (gdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use ReapingHook").create());
	                }
	                else // if timer is done
	                {
	                    gdcooldown.remove(p.getName()); // removing player from HashMap
	                    HashMap<Item, LivingEntity> hooked = new HashMap<>();
						ItemStack reap = p.getInventory().getItemInMainHand();	
						p.playSound(p.getLocation(), Sound.ENTITY_FISHING_BOBBER_THROW, 1.0f, 0f);
						p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SKELETON_AMBIENT, 1.0f, 0f);
						Item hook = p.getWorld().dropItem(p.getLocation(), reap);
						hook.setVelocity(hook.getVelocity().zero());
						hook.setGlowing(true);
						hook.setPickupDelay(9999);
						hook.setInvulnerable(true);
						hook.setOwner(p.getUniqueId());
						hook.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						hook.setMetadata("reap of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    ArrayList<Location> line = new ArrayList<Location>();
	                    AtomicInteger j = new AtomicInteger(0);
	                    AtomicInteger a = new AtomicInteger(0);
	                    AtomicInteger b = new AtomicInteger(0);
	                    for(double d = 0.1; d <= 15; d += 0.1) {
		                    Location pl = p.getLocation();
							pl.add(pl.getDirection().normalize().multiply(d));
							line.add(pl);
	                    }
	                    if(line.stream().filter(o -> o.getWorld().getNearbyEntities(o,1.3,1.3,1.3).stream().filter(en -> en instanceof LivingEntity && en!=p&& !(en.hasMetadata("fake"))).findFirst().isPresent()).findFirst().isPresent()) 
	                    {
		                    Location block =line.stream().filter(o -> o.getWorld().getNearbyEntities(o,1.3,1.3,1.3).stream().filter(en -> en instanceof LivingEntity && en!=p&& !(en.hasMetadata("fake"))).findFirst().isPresent()).findFirst().get();
		                    for(int k=0; k<line.indexOf(block); k++) {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
     									hook.teleport(line.get(a.getAndIncrement()));
    			             			p.getWorld().spawnParticle(Particle.SPELL_WITCH ,line.get(a.get()), 3, 0.2,0.2,0.2,0);
    			             			
						            }
			                	   }, j.getAndIncrement()/20); 
							 }
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
					             			LivingEntity le = (LivingEntity) hook.getNearbyEntities(2.3, 2.3, 2.3).stream().filter(en -> en instanceof LivingEntity && en!=p&& !(en.hasMetadata("fake"))).findFirst().get();
		         							if (le instanceof Player) 
		        							{
		        								PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
					             			hooked.put(hook, le);
							            }
				                	   }, j.getAndIncrement()/20); 
		                    for(int k=line.indexOf(block)-1; k>0; k--) {
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
			             					LivingEntity le = hooked.get(hook);
	             							hook.teleport(line.get(a.decrementAndGet()));
	             							Holding.superholding(p, le, 10l);
	             							le.teleport(hook);
	             							p.setSprinting(true);
	             							p.playSound(p.getLocation(), Sound.ITEM_HOE_TILL, 0.6f, 0f);
	             							le.damage(0,p);
	             							p.setSprinting(false);
							            }
				                	   }, j.getAndIncrement()/20); 
								 }
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
			             					hook.remove();
							            }
				                	   }, j.getAndIncrement()/20+1); 
		                    }
	                    else {
	                    	line.forEach(i -> {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
     									hook.teleport(i);
    			             			p.getWorld().spawnParticle(Particle.SPELL_WITCH ,i, 3, 0.2,0.2,0.2,0);
						            }
			                	   }, j.getAndIncrement()/20); 
							}); 
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
			             					hook.remove();
							            }
				                	   }, j.getAndIncrement()/20+1); 
	                    }

						gdcooldown.put(p.getName(), System.currentTimeMillis());  
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	HashMap<Item, LivingEntity> hooked = new HashMap<>();
					ItemStack reap = p.getInventory().getItemInMainHand();	
					p.playSound(p.getLocation(), Sound.ENTITY_FISHING_BOBBER_THROW, 1.0f, 0f);
					p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SKELETON_AMBIENT, 1.0f, 0f);
					Item hook = p.getWorld().dropItem(p.getLocation(), reap);
					hook.setVelocity(hook.getVelocity().zero());
					hook.setGlowing(true);
					hook.setPickupDelay(9999);
					hook.setInvulnerable(true);
					hook.setOwner(p.getUniqueId());
					hook.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					hook.setMetadata("reap of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    ArrayList<Location> line = new ArrayList<Location>();
                    AtomicInteger j = new AtomicInteger(0);
                    AtomicInteger a = new AtomicInteger(0);
                    AtomicInteger b = new AtomicInteger(0);
                    for(double d = 0.1; d <= 15; d += 0.1) {
	                    Location pl = p.getLocation();
						pl.add(pl.getDirection().normalize().multiply(d));
						line.add(pl);
                    }
                    if(line.stream().filter(o -> o.getWorld().getNearbyEntities(o,1.3,1.3,1.3).stream().filter(en -> en instanceof LivingEntity && en!=p&& !(en.hasMetadata("fake"))).findFirst().isPresent()).findFirst().isPresent()) 
                    {
	                    Location block =line.stream().filter(o -> o.getWorld().getNearbyEntities(o,1.3,1.3,1.3).stream().filter(en -> en instanceof LivingEntity && en!=p&& !(en.hasMetadata("fake"))).findFirst().isPresent()).findFirst().get();
	                    for(int k=0; k<line.indexOf(block); k++) {
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
			                	public void run() 
				                {	
 									hook.teleport(line.get(a.getAndIncrement()));
			             			p.getWorld().spawnParticle(Particle.SPELL_WITCH ,line.get(a.get()), 3, 0.2,0.2,0.2,0);
			             			
					            }
		                	   }, j.getAndIncrement()/20); 
						 }
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
				             			LivingEntity le = (LivingEntity) hook.getNearbyEntities(2.3, 2.3, 2.3).stream().filter(en -> en instanceof LivingEntity && en!=p&& !(en.hasMetadata("fake"))).findFirst().get();
	         							if (le instanceof Player) 
	        							{
	        								PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
				             			hooked.put(hook, le);
						            }
			                	   }, j.getAndIncrement()/20); 
	                    for(int k=line.indexOf(block)-1; k>0; k--) {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
		             					LivingEntity le = hooked.get(hook);
             							hook.teleport(line.get(a.decrementAndGet()));
             							Holding.superholding(p, le, 10l);
             							le.teleport(hook);
             							p.setSprinting(true);
             							p.playSound(p.getLocation(), Sound.ITEM_HOE_TILL, 0.6f, 0f);
             							le.damage(0,p);
             							p.setSprinting(false);
						            }
			                	   }, j.getAndIncrement()/20); 
							 }
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
		             					hook.remove();
						            }
			                	   }, j.getAndIncrement()/20+1); 
	                    }
                    else {
                    	line.forEach(i -> {
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
			                	public void run() 
				                {	
 									hook.teleport(i);
			             			p.getWorld().spawnParticle(Particle.SPELL_WITCH ,i, 3, 0.2,0.2,0.2,0);
					            }
		                	   }, j.getAndIncrement()/20); 
						}); 
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
		             					hook.remove();
						            }
			                	   }, j.getAndIncrement()/20+1); 
                    }
					gdcooldown.put(p.getName(), System.currentTimeMillis());  
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	
	
	@EventHandler
	public void WitherSkull(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		int sec = 2;

        
		
		
		
		if(playerclass.get(p.getUniqueId()) == 13) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("HOE")) && !p.isSneaking())
			{
				ev.setCancelled(true);
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (cdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use WitherSkull").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap
							p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1, 1);
		                    WitherSkull ws = (WitherSkull) p.launchProjectile(WitherSkull.class);
		                    ws.setYield(0.0f);
		                    ws.setBounce(false);
		                    ws.setShooter(p);
		                    ws.setVelocity(p.getLocation().getDirection().normalize().multiply(2));
		                    ws.setIsIncendiary(false);
		                    if(p.isFlying()) {
		                    	ws.setCharged(true);
		                    }
							ws.setMetadata("ws of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		                    
							cdcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
						p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1, 1);
	                    WitherSkull ws = (WitherSkull) p.launchProjectile(WitherSkull.class);
	                    ws.setYield(0.0f);
	                    ws.setBounce(false);
	                    ws.setShooter(p);
	                    ws.setVelocity(p.getLocation().getDirection().normalize().multiply(2));
	                    ws.setIsIncendiary(false);
	                    if(p.isFlying()) {
	                    	ws.setCharged(true);
	                    }
						ws.setMetadata("ws of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
		}
	
	}
	

	@EventHandler
	public void WitherSkull(EntityExplodeEvent ev) 
	{
        
		
		if(ev.getEntity() instanceof WitherSkull) {
			WitherSkull fb = (WitherSkull)ev.getEntity();
			if(fb.getShooter() instanceof Player) {
				Player p = (Player) fb.getShooter();
				if(fb.hasMetadata("ws of"+p.getName())) {
					ev.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, ev.getLocation(), 2);
					p.playSound(ev.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
					for(Entity n : ev.getLocation().getWorld().getNearbyEntities(ev.getLocation(), 4, 4, 4)) {
						if(n!=p && n instanceof LivingEntity&& !(n.hasMetadata("fake"))) {
							LivingEntity le = (LivingEntity)n;
							p.setSprinting(true);
							if(fb.isCharged()) {
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
									enar.setDamage(player_damage.get(p.getName())*0.4+wsd.WitherSkull.get(p.getUniqueId())*0.4);								
								}
								le.damage(0,p);
								le.damage(player_damage.get(p.getName())*0.4+wsd.WitherSkull.get(p.getUniqueId())*0.4,p);	
							}
							else {
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
									enar.setDamage(player_damage.get(p.getName())*0.24+wsd.WitherSkull.get(p.getUniqueId())*0.24);								
								}
								le.damage(0,p);
								le.damage(player_damage.get(p.getName())*0.24+wsd.WitherSkull.get(p.getUniqueId())*0.24,p);	
							}
							p.setSprinting(false);			
						}
					}
					ev.setCancelled(true);
				}
			}
			
		}
	}
	
	@EventHandler
	public void Curse(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 6;

        
		
		
		if(playerclass.get(p.getUniqueId()) == 13) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("HOE"))
			{
				

				if(frcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (frcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Curse").create());
	                }
	                else // if timer is done
	                {
	                    frcooldown.remove(p.getName()); // removing player from HashMap
						p.getWorld().spawnParticle(Particle.BLOCK_DUST, p.getLocation(), 555, 6,3,6,0.3, Material.CRYING_OBSIDIAN.createBlockData()); 
						p.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, p.getLocation(), 555, 6,3,6); 
						for (Entity a : p.getWorld().getNearbyEntities(p.getLocation(), 6, 6, 6))
						{
							if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)a;
								if (le instanceof Player) 
    							{
    								Player p1 = (Player) le;
    								try {
    								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
    								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
    									{
    										continue;
    									}
    								}}
    								catch(NullPointerException ne) {
    									continue;
    								}
    							}
								Holding.holding(p, le, 20l);
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
									enar.setDamage(player_damage.get(p.getName())*0.649+wsd.Curse.get(p.getUniqueId())*0.43);								
								}
								p.setSprinting(true);
								le.damage(0, p);
			                    le.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 180, 100, false, false));
								le.damage(player_damage.get(p.getName())*0.649+wsd.Curse.get(p.getUniqueId())*0.43, p);
								
								p.setSprinting(false);
									
							}
						}
						p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.69f, 2f);

						frcooldown.put(p.getName(), System.currentTimeMillis());  
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	p.getWorld().spawnParticle(Particle.BLOCK_DUST, p.getLocation(), 555, 6,3,6,0.3, Material.CRYING_OBSIDIAN.createBlockData()); 
					p.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, p.getLocation(), 555, 6,3,6); 
					for (Entity a : p.getWorld().getNearbyEntities(p.getLocation(), 6, 6, 6))
					{
						if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
						{
							LivingEntity le = (LivingEntity)a;
							if (le instanceof Player) 
							{
								PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
								Player p1 = (Player) le;
								try {
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
									{
										continue;
									}
								}}
								catch(NullPointerException ne) {
									continue;
								}
							}
							Holding.holding(p, le, 20l);
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
								enar.setDamage(player_damage.get(p.getName())*0.649+wsd.Curse.get(p.getUniqueId())*0.43);								
							}
							p.setSprinting(true);
							le.damage(0, p);
		                    le.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 180, 100, false, false));
							le.damage(player_damage.get(p.getName())*0.649+wsd.Curse.get(p.getUniqueId())*0.43, p);
							
							p.setSprinting(false);
								
						}
					}
					p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.69f, 2f);


					frcooldown.put(p.getName(), System.currentTimeMillis());  
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	
	@EventHandler
	public void WitherScythe(EntityDamageByEntityEvent d) 
	{
		int sec = 7;
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
		final Location el =le.getLocation();
        

		
		
		if(playerclass.get(p.getUniqueId()) == 13) {
			
				if((p.getInventory().getItemInMainHand().getType().name().contains("HOE")) && (p.isSneaking()) && !(p.isSprinting()))
				{
				if(stcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            long timer = (stcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
		            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use WitherScythe").create());
			        }
		            else // if timer is done
		            {
		                stcooldown.remove(p.getName()); // removing player from HashMap
		                Holding.superholding(p, le, 60l);
     					le.teleport(el);
						p.playSound(p.getLocation(), Sound.ENTITY_WITHER_BREAK_BLOCK, 1.0f, 0f);
						p.getWorld().spawnParticle(Particle.SPELL_WITCH, el, 50, 1,1,1);
						for(int i = 0; i<25; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
			             					p.setSprinting(true);
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
	        									enar.setDamage(player_damage.get(p.getName())*0.181+wsd.WitherScythe.get(p.getUniqueId())*0.07);								
	        								}
			             					le.damage(0, p);
			             					le.damage(player_damage.get(p.getName())*0.18+wsd.WitherScythe.get(p.getUniqueId())*0.07, p);
			             					p.setSprinting(false);
			        						p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 0.8f, 2f);
			        						p.getWorld().spawnParticle(Particle.FALLING_OBSIDIAN_TEAR, el, 10, 0.5,0.5,0.5);
			        						p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, el, 1, 0.5,0.5,0.5);
			             					for(Entity e: le.getNearbyEntities(2.5, 2.5, 2.5)) {
			             						if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))) {
			             							LivingEntity le = (LivingEntity)e;
			             							if (le instanceof Player) 
			            							{
			            								new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
			            								Player p1 = (Player) le;
			            								try {
			            								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
			            								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
			            									{
			            										continue;
			            									}
			            								}}
			            								catch(NullPointerException ne) {
			            									continue;
			            								}
			            							}
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
			        									enar.setDamage(player_damage.get(p.getName())*0.18+wsd.WitherScythe.get(p.getUniqueId())*0.07);								
			        								}
					             					p.setSprinting(true);
					             					le.teleport(el);
					             					le.damage(0, p);
					             					le.damage(player_damage.get(p.getName())*0.18+wsd.WitherScythe.get(p.getUniqueId())*0.07, p);
					             					p.setSprinting(false);
					        						p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1.0f, 2f);
					        						p.getWorld().spawnParticle(Particle.FALLING_OBSIDIAN_TEAR, el, 10, 0.5,0.5,0.5);
					        						p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, el, 10, 0.5,0.5,0.5);
			             							
			             						}
			             					}
							            }
				                	   }, i*2+10); 
							
						}
			            stcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
		        	Holding.superholding(p, le, 60l);
 					le.teleport(el);
					p.playSound(p.getLocation(), Sound.ENTITY_WITHER_BREAK_BLOCK, 1.0f, 0f);
					p.getWorld().spawnParticle(Particle.SPELL_WITCH, el, 50, 1,1,1);
					for(int i = 0; i<25; i++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
		             					p.setSprinting(true);
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
        									enar.setDamage(player_damage.get(p.getName())*0.181+wsd.WitherScythe.get(p.getUniqueId())*0.07);								
        								}
		             					le.damage(0, p);
		             					le.damage(player_damage.get(p.getName())*0.181+wsd.WitherScythe.get(p.getUniqueId())*0.07, p);
		             					p.setSprinting(false);
		        						p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 0.8f, 2f);
		        						p.getWorld().spawnParticle(Particle.FALLING_OBSIDIAN_TEAR, el, 10, 0.5,0.5,0.5);
		        						p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, el, 1, 0.5,0.5,0.5);
		             					for(Entity e: le.getNearbyEntities(2.5, 2.5, 2.5)) {
		             						if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))) {
		             							LivingEntity le = (LivingEntity)e;
		             							if (le instanceof Player) 
		            							{
		            								PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
		            								Player p1 = (Player) le;
		            								try {
		            								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
		            								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
		            									{
		            										continue;
		            									}
		            								}}
		            								catch(NullPointerException ne) {
		            									continue;
		            								}
		            							}
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
		        									enar.setDamage(player_damage.get(p.getName())*0.18+wsd.WitherScythe.get(p.getUniqueId())*0.07);								
		        								}
				             					p.setSprinting(true);
				             					le.teleport(el);
				             					le.damage(0, p);
				             					le.damage(player_damage.get(p.getName())*0.18+wsd.WitherScythe.get(p.getUniqueId())*0.07, p);
				             					p.setSprinting(false);
				        						p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1.0f, 2f);
				        						p.getWorld().spawnParticle(Particle.FALLING_OBSIDIAN_TEAR, el, 10, 0.5,0.5,0.5);
				        						p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, el, 10, 0.5,0.5,0.5);
		             							
		             						}
		             					}
						            }
			                	   }, i*2+10); 
						
					}
		            stcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
		}
		}
	}
	

	@EventHandler
	public void Hover(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 12;

        
		
		
		
		if(playerclass.get(p.getUniqueId()) == 13 && wsd.Hover.get(p.getUniqueId())>=1) {
			if((ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK)&& ac != Action.RIGHT_CLICK_AIR&& ac != Action.RIGHT_CLICK_BLOCK)
			{
				if((p.getInventory().getItemInMainHand().getType().name().contains("HOE")) && !(p.isSneaking()) && !(p.isOnGround()) && p.getGameMode()!=GameMode.CREATIVE)
					{
					ev.setCancelled(true);
					if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            long timer = (smcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Hover").create());
				        }
			            else // if timer is done
			            {
			                smcooldown.remove(p.getName()); // removing player from HashMapLocation dam = p.getLocation();
			                p.setAllowFlight(true);
							p.playSound(p.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 1.0f, 2f);
							p.getWorld().spawnParticle(Particle.DRAGON_BREATH, p.getLocation(), 200, 6,3,6); 
							for(int i=0;i<100;i++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
										p.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, p.getLocation(), 10, 0.3,0.3,0.3); 
					                }
					            }, i*2); 
								
							}
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 140, 1, false, false));
									p.setAllowFlight(false);
				                    p.setFlying(false);
				                }
				            }, 200); 
				            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
		                p.setAllowFlight(true);
						p.playSound(p.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 1.0f, 2f);
						p.getWorld().spawnParticle(Particle.DRAGON_BREATH, p.getLocation(), 200, 6,3,6); 
						for(int i=0;i<100;i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									p.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, p.getLocation(), 10, 0.3,0.3,0.3); 
				                }
				            }, i*2); 
							
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 140, 1, false, false));
								p.setAllowFlight(false);
			                    p.setFlying(false);
			                }
			            }, 200); 
			            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			        }
				}
			}
			
		}
	}
	
	@EventHandler
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		final Location l = p.getLocation();
    	final GameMode pgm = p.getGameMode();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
        
		
		
		
		
			if(playerclass.get(p.getUniqueId()) == 13 && ((is.getType().name().contains("HOE"))) && p.isSneaking())
			{
				ev.setCancelled(true);
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (sultcooldown.get(p.getName())/1000 + 80) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Overcome").create());
	                }
	                else // if timer is done
	                {
	                    sultcooldown.remove(p.getName()); // removing player from HashMap
	                    p.playSound(l, Sound.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 1,2);
						p.sendTitle(ChatColor.MAGIC + "SWORDSTORM", null);
						p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, p.getLocation(), 200, 6,3,6); 
						p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, p.getLocation(), 200, 6,3,6); 
						p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getLocation(), 200, 6,3,6); 
						p.getWorld().spawnParticle(Particle.WARPED_SPORE, p.getLocation(), 200, 6,3,6); 
						for(Entity e: p.getWorld().getNearbyEntities(l,10, 30, 10)) {
							if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p) {
								LivingEntity le = (LivingEntity)e;
								if (le instanceof Player) 
    							{
    								Player p1 = (Player) le;
    								try {
    								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
    								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
    									{
    										continue;
    									}
    								}}
    								catch(NullPointerException ne) {
    									continue;
    								}
    							}
								le.setRemoveWhenFarAway(false);
								Holding.superholding(p, le, 80l);
							}
						}
						p.teleport(p.getLocation().add(0, 300, 0));
	                    p.playSound(l, Sound.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 1,2);
	                    p.playSound(l, Sound.AMBIENT_SOUL_SAND_VALLEY_LOOP, 1,2);
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100, 255, false, false));
	                	Wither overcome = (Wither) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().multiply(3)), EntityType.WITHER);
	                    overcome.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                	overcome.setAI(false);
	                	overcome.getBossBar().setVisible(false);
	                	overcome.setGlowing(true);
						for(int i=0;i<30;i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                    p.playSound(l, Sound.PARTICLE_SOUL_ESCAPE, 1,0);
				                    p.playSound(l, Sound.ENTITY_WITHER_HURT, 1,0);
									p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, p.getLocation(), 10, 2,2,2); 
									p.getWorld().spawnParticle(Particle.TOWN_AURA, p.getLocation(), 10, 2,2,2); 
									p.getWorld().spawnParticle(Particle.BLOCK_DUST, p.getLocation(), 10, 2,2,2,1,Material.CHISELED_QUARTZ_BLOCK.createBlockData()); 
									p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, overcome.getLocation(), 10, 2,2,2); 
									p.getWorld().spawnParticle(Particle.WHITE_ASH, overcome.getLocation(), 10, 2,2,2); 
									p.getWorld().spawnParticle(Particle.WARPED_SPORE, overcome.getLocation(), 10, 2,2,2); 
				                }
				            }, i*2); 
							
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	p.setGameMode(GameMode.SPECTATOR);
								p.setSpectatorTarget(overcome);			                	
			                }
			            }, 30); 
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                    p.playSound(overcome.getLocation(), Sound.ENTITY_WITHER_DEATH, 1,1);
			                    p.playSound(overcome.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.8f,1);
								p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, overcome.getLocation(), 1, 1,1,1); 
								p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, overcome.getLocation(), 200, 6,3,6); 
								p.getWorld().spawnParticle(Particle.WHITE_ASH, overcome.getLocation(), 200, 6,3,6); 
								p.getWorld().spawnParticle(Particle.WARPED_SPORE, overcome.getLocation(), 200, 6,3,6); 
								p.setSpectatorTarget(null);
								p.setGameMode(pgm);
			                	overcome.remove();
								p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 200, 6,3,6); 
			                	
			                }
			            }, 60); 
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 200, 6,3,6); 
			                    p.playSound(l, Sound.ENTITY_GENERIC_EXPLODE, 0.8f,1);
								p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, l, 1, 1,1,1); 
								p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, l, 200, 6,3,6); 
								p.getWorld().spawnParticle(Particle.WHITE_ASH, l, 200, 6,3,6); 
								p.getWorld().spawnParticle(Particle.WARPED_SPORE, l, 200, 6,3,6); 
			                	p.teleport(l);
								for(Entity e: p.getWorld().getNearbyEntities(l,10, 30, 10)) {
									if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
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
        									enar.setDamage(player_damage.get(p.getName())*5);								
        								}
					                	p.setSprinting(true);
					                	le.damage(0, p);
					                	le.damage(player_damage.get(p.getName())*5, p);
					                	p.setSprinting(false);
									}
								}
			                	
			                }
			            }, 65); 
	                    
		                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	p.playSound(l, Sound.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 1,2);
					p.sendTitle(ChatColor.MAGIC + "SWORDSTORM", null);
					p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, p.getLocation(), 200, 6,3,6); 
					p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, p.getLocation(), 200, 6,3,6); 
					p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getLocation(), 200, 6,3,6); 
					p.getWorld().spawnParticle(Particle.WARPED_SPORE, p.getLocation(), 200, 6,3,6); 
					for(Entity e: p.getWorld().getNearbyEntities(l,10, 30, 10)) {
						if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p) {
							LivingEntity le = (LivingEntity)e;
							if (le instanceof Player) 
							{
								Player p1 = (Player) le;
								try {
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
									{
										continue;
									}
								}}
								catch(NullPointerException ne) {
									continue;
								}
							}
							le.setRemoveWhenFarAway(false);
							Holding.superholding(p, le, 80l);
						}
					}
					p.teleport(p.getLocation().add(0, 300, 0));
                    p.playSound(l, Sound.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 1,2);
                    p.playSound(l, Sound.AMBIENT_SOUL_SAND_VALLEY_LOOP, 1,2);
                	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100, 255, false, false));
                	Wither overcome = (Wither) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().multiply(3)), EntityType.WITHER);
                	overcome.setAI(false);
                	overcome.getBossBar().setVisible(false);
                	overcome.setGlowing(true);
                    overcome.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					for(int i=0;i<30;i++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                    p.playSound(l, Sound.PARTICLE_SOUL_ESCAPE, 1,0);
			                    p.playSound(l, Sound.ENTITY_WITHER_HURT, 1,0);
								p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, p.getLocation(), 10, 2,2,2); 
								p.getWorld().spawnParticle(Particle.TOWN_AURA, p.getLocation(), 10, 2,2,2); 
								p.getWorld().spawnParticle(Particle.BLOCK_DUST, p.getLocation(), 10, 2,2,2,1,Material.CHISELED_QUARTZ_BLOCK.createBlockData()); 
								p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, overcome.getLocation(), 10, 2,2,2); 
								p.getWorld().spawnParticle(Particle.WHITE_ASH, overcome.getLocation(), 10, 2,2,2); 
								p.getWorld().spawnParticle(Particle.WARPED_SPORE, overcome.getLocation(), 10, 2,2,2); 
			                }
			            }, i*2); 
						
					}
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	p.setGameMode(GameMode.SPECTATOR);
							p.setSpectatorTarget(overcome);			                	
		                }
		            }, 30); 
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                    p.playSound(overcome.getLocation(), Sound.ENTITY_WITHER_DEATH, 1,1);
		                    p.playSound(overcome.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.8f,1);
							p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, overcome.getLocation(), 1, 1,1,1); 
							p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, overcome.getLocation(), 200, 6,3,6); 
							p.getWorld().spawnParticle(Particle.WHITE_ASH, overcome.getLocation(), 200, 6,3,6); 
							p.getWorld().spawnParticle(Particle.WARPED_SPORE, overcome.getLocation(), 200, 6,3,6); 
							p.setSpectatorTarget(null);
							p.setGameMode(pgm);
		                	overcome.remove();
							p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 200, 6,3,6); 
		                	
		                }
		            }, 60); 
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
							p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 200, 6,3,6); 
		                    p.playSound(l, Sound.ENTITY_GENERIC_EXPLODE, 0.8f,1);
							p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, l, 1, 1,1,1); 
							p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, l, 200, 6,3,6); 
							p.getWorld().spawnParticle(Particle.WHITE_ASH, l, 200, 6,3,6); 
							p.getWorld().spawnParticle(Particle.WARPED_SPORE, l, 200, 6,3,6); 
		                	p.teleport(l);
							for(Entity e: p.getWorld().getNearbyEntities(l,10, 30, 10)) {
								if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
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
    									enar.setDamage(player_damage.get(p.getName())*5);								
    								}
				                	p.setSprinting(true);
				                	le.damage(0, p);
				                	le.damage(player_damage.get(p.getName())*2, p);
				                	p.setSprinting(false);
								}
							}
		                	
		                }
		            }, 65); 
					
	                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();
        

		
		
		if(playerclass.get(p.getUniqueId()) == 13)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("HOE") )
			{
					e.setCancelled(true);
			}
		}
		
	}
	
	
	@EventHandler
	public void Witherize(EntityDamageByEntityEvent d) 
	{
		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && d.getDamage()>0) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
        

		
		
		if(playerclass.get(p.getUniqueId()) == 13) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("HOE"))
				{
					
					if(le.getType().name().contains("WITHER")) {
						d.setDamage(d.getDamage()*2.5*(1+wsd.Witherize.get(p.getUniqueId())*0.03));
						p.getWorld().spawnParticle(Particle.LANDING_OBSIDIAN_TEAR, le.getLocation(), 10, 1, 1, 1, 0);
						if(p.hasPotionEffect(PotionEffectType.WITHER)) {
							if(p.hasPotionEffect(PotionEffectType.WITHER)) {
								p.removePotionEffect(PotionEffectType.WITHER);
							}
						}
					}
					else {
						d.setDamage(d.getDamage()*1.25*(1+wsd.Witherize.get(p.getUniqueId())*0.03));
						p.getWorld().spawnParticle(Particle.LANDING_OBSIDIAN_TEAR, le.getLocation(), 10, 1, 1, 1, 0);
						le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 1, false, false));	
						if(p.hasPotionEffect(PotionEffectType.WITHER)) {
							if(p.hasPotionEffect(PotionEffectType.WITHER)) {
								p.removePotionEffect(PotionEffectType.WITHER);
							}
						}
					}
					
				}
		}
		}
		

		else if(d.getDamager() instanceof WitherSkull && d.getEntity() instanceof LivingEntity) 
		{
		WitherSkull ws = (WitherSkull)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
        
        if(ws.getShooter() instanceof Player) {
        	Player p = (Player)ws.getShooter();
			
			
			if(playerclass.get(p.getUniqueId()) == 13 && ws.hasMetadata("ws of"+p.getName())) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("HOE"))
				{
					
					d.setCancelled(true);
 					p.setSprinting(true);
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
						enar.setDamage(player_damage.get(p.getName())*0.4+wsd.WitherSkull.get(p.getUniqueId())*0.4);								
					}
					le.damage(0,p);
					le.damage(player_damage.get(p.getName())*0.4+wsd.WitherSkull.get(p.getUniqueId())*0.4,p);	
 					p.setSprinting(false);
					
				}
			}
        }
		}
		else if(d.getEntity() instanceof Player && d.getDamager().getType().name().contains("WITHER")) 
		{
		Player p = (Player)d.getEntity();
        
		
		
			if(playerclass.get(p.getUniqueId()) == 13) 
			{	
				d.setDamage(d.getDamage()*0.25);
				if(p.hasPotionEffect(PotionEffectType.WITHER)) {
					p.removePotionEffect(PotionEffectType.WITHER);
				}
			}	
		}	
		else if(d.getEntity() instanceof Player) 
		{
		Player p = (Player)d.getEntity();
        
		
		
			if(playerclass.get(p.getUniqueId()) == 13) 
			{	
				if(p.hasPotionEffect(PotionEffectType.WITHER)) {
					p.removePotionEffect(PotionEffectType.WITHER);
				}
			}	
		}	

		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof EnderDragon) 
		{
			Arrow ar = (Arrow)d.getDamager();
			EnderDragon le = (EnderDragon)d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player)ar.getShooter();
		        

				
				
				if(playerclass.get(p.getUniqueId()) == 13) {
						if(p.getInventory().getItemInMainHand().getType().name().contains("HOE"))
						{
							
							if(le.getType().name().contains("WITHER")) {
								d.setDamage(d.getDamage()*2.5*(1+wsd.Witherize.get(p.getUniqueId())*0.02));
								p.getWorld().spawnParticle(Particle.LANDING_OBSIDIAN_TEAR, le.getLocation(), 10, 1, 1, 1, 0);
								if(p.hasPotionEffect(PotionEffectType.WITHER)) {
									if(p.hasPotionEffect(PotionEffectType.WITHER)) {
										p.removePotionEffect(PotionEffectType.WITHER);
									}
								}
							}
							else {
								d.setDamage(d.getDamage()*1.25*(1+wsd.Witherize.get(p.getUniqueId())*0.02));
								p.getWorld().spawnParticle(Particle.LANDING_OBSIDIAN_TEAR, le.getLocation(), 10, 1, 1, 1, 0);
								le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 1, false, false));	
								if(p.hasPotionEffect(PotionEffectType.WITHER)) {
									if(p.hasPotionEffect(PotionEffectType.WITHER)) {
										p.removePotionEffect(PotionEffectType.WITHER);
									}
								}
							}
							
						}
				}
			}
		}
	}

	@EventHandler
	public void Witherized(EntityDamageEvent d) 
	{		
        if (!(d.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) d.getEntity();
        
		
		
		if(d.getCause().equals(DamageCause.WITHER)) 
		{
	        if(playerclass.get(p.getUniqueId()) == 13) {
	        	d.setCancelled(true);
	        }
		}
	}
	@EventHandler
	public void Witherized(EntityPotionEffectEvent d) 
	{
		if(!(d.getEntity() instanceof Player)) {return;
		}
		
		else if(d.getEntity() instanceof Player) {
			if(d.getNewEffect() != null) {
				Player p = (Player)d.getEntity();
		        
				
				
					if(playerclass.get(p.getUniqueId()) == 13) 
					{	
						if(d.getNewEffect().getType() == PotionEffectType.WITHER) {
						d.setCancelled(true);}
						if(p.hasPotionEffect(PotionEffectType.WITHER)) {
							p.removePotionEffect(PotionEffectType.WITHER);
						}
					}	
			}
		}
	}

	@EventHandler
	public void Damagegetter(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 13) {
				if(p.getInventory().getItemInMainHand().getType()==Material.IRON_HOE)
				{
						player_damage.put(p.getName(), 6 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);

						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if( p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_HOE)
				{
						player_damage.put(p.getName(), 7 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);

						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_HOE)
				{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);

						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.STONE_HOE)
				{
						player_damage.put(p.getName(), 4 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_HOE)
				{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_HOE)
				{
						player_damage.put(p.getName(), 9 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
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
        

		
		
		if(playerclass.get(p.getUniqueId()) == 13) {
			if(p.getInventory().getItemInMainHand().getType()==Material.IRON_HOE)
			{
					player_damage.put(p.getName(), 6 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			
			if( p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_HOE)
			{
					player_damage.put(p.getName(), 7 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_HOE)
			{
					player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.STONE_HOE)
			{
					player_damage.put(p.getName(), 4 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_HOE)
			{
					player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_HOE)
			{
					player_damage.put(p.getName(), 9 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			    
				
				
				if(playerclass.get(p.getUniqueId()) == 13) {
					if(p.getInventory().getItemInMainHand().getType()==Material.IRON_HOE)
					{
							player_damage.put(p.getName(), 6 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
					
					if( p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_HOE)
					{
							player_damage.put(p.getName(), 7 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_HOE)
					{
							player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.STONE_HOE)
					{
							player_damage.put(p.getName(), 4 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_HOE)
					{
							player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_HOE)
					{
							player_damage.put(p.getName(), 9 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
}



