package io.github.chw3021.berserker;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.party.PartyData;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
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
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
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

public class Berskills implements Listener, Serializable {
	

	/**
	 * 
	 */
	private static transient final long serialVersionUID = -6205130125239201504L;
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> drcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> lncooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cacooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<Player, Boolean> ulton = new HashMap<Player, Boolean>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private BerSkillsData bsd;

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
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Classes"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				BerSkillsData b = new BerSkillsData(BerSkillsData.loadData(path +"/plugins/RPGskills/BerSkillsData.data"));
				bsd = b;
			}
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;

		BerSkillsData b = new BerSkillsData(BerSkillsData.loadData(path +"/plugins/RPGskills/BerSkillsData.data"));
		bsd = b;
	}

	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		BerSkillsData b = new BerSkillsData(BerSkillsData.loadData(path +"/plugins/RPGskills/BerSkillsData.data"));
		bsd = b;
	}
	@EventHandler
	public void Swipe(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("HOE"))
		{
		Action ac = ev.getAction();
		int sec = 8;

	    
		
		
		new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
			if(playerclass.get(p.getUniqueId()) == 1) {
			if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				
				
				ev.setCancelled(true);
				if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Swipe").create());
	                }
	                else // if timer is done
	                {
	                    sdcooldown.remove(p.getName()); // removing player from HashMap
	                    ArrayList<Location> line = new ArrayList<Location>();
	                    ArrayList<Location> pie = new ArrayList<Location>();
	                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    new AtomicInteger(0);
	                    for(double d = -Math.PI/6; d<= Math.PI/6; d += Math.PI/180) {
		                    Location l = p.getLocation();
		                    l.setDirection(l.getDirection().normalize().rotateAroundY(d));
		                    l.add(l.getDirection().normalize().multiply(7.1));
		                    line.add(l);
	                    	
	                    }
	                    for(double d = 0.1; d <= 7.1; d += 1) {
	                    	  for (Location l : line){
			                    Location pl = p.getLocation();
								Vector ltr = l.toVector().subtract(pl.toVector());
								pl.add(ltr.normalize().multiply(d));
								pie.add(pl);
								}
	                    }
	                    
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
	                    pie.forEach(i -> {
								p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, i, 1, 0.5,0.5,0.5,0);
						}); 
	                    for(Location l : pie) {

	                    	for (Entity a : p.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5))
							{
	                    		if (a instanceof Player) 
								{
									
									Player p1 = (Player) a;
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
								if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake")) ) 
								{
									LivingEntity le = (LivingEntity)a;
									les.add(le);
								}
							}
	                    }
	                    for(LivingEntity le: les) 
						{
							p.setSprinting(true);
							le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
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
								enar.setDamage((player_damage.get(p.getName())*0.43 + bsd.Swipe.get(p.getUniqueId())*0.43));								
							}
							le.damage(0, p);
							le.damage(player_damage.get(p.getName())*0.43 + bsd.Swipe.get(p.getUniqueId())*0.43, p);
							le.teleport(p.getLocation());
							
							p.setSprinting(false);
							Holding.holding(p, le, 9l);
						}
		                sdcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	ArrayList<Location> line = new ArrayList<Location>();
                    ArrayList<Location> pie = new ArrayList<Location>();
                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                    new AtomicInteger(0);
                    for(double d = -Math.PI/6; d<= Math.PI/6; d += Math.PI/180) {
	                    Location l = p.getLocation();
	                    l.setDirection(l.getDirection().normalize().rotateAroundY(d));
	                    l.add(l.getDirection().normalize().multiply(7.1));
	                    line.add(l);
                    	
                    }
                    for(double d = 0.1; d <= 7.1; d += 1) {
                    	  for (Location l : line){
		                    Location pl = p.getLocation();
							Vector ltr = l.toVector().subtract(pl.toVector());
							pl.add(ltr.normalize().multiply(d));
							pie.add(pl);
							}
                    }
                    
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
                    pie.forEach(i -> {
							p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, i, 1, 0.5,0.5,0.5,0);
					}); 
                    for(Location l : pie) {

                    	for (Entity a : p.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5))
						{
                    		if (a instanceof Player) 
							{
								
								Player p1 = (Player) a;
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
							if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)a;
								les.add(le);
							}
						}
                    }
                    for(LivingEntity le: les) 
					{
						p.setSprinting(true);
						le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
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
							enar.setDamage((player_damage.get(p.getName())*0.43 + bsd.Swipe.get(p.getUniqueId())*0.43));	
						}
						le.damage(0, p);
						le.damage(player_damage.get(p.getName())*0.43 + bsd.Swipe.get(p.getUniqueId())*0.43, p);
						
						le.teleport(p.getLocation());
						
						p.setSprinting(false);
						Holding.holding(p, le, 9l);
					}
	                sdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
		}
		}
	}

	@EventHandler
	public void Drain(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		if(p.getInventory().getItemInMainHand().getType().name().contains("HOE"))
		{
		LivingEntity e = (LivingEntity)d.getEntity();
        
        int sec = 9;

		
		
		if(playerclass.get(p.getUniqueId()) == 1) {
			if((p.isSneaking()) && !p.isSprinting())
			{
					

					if(swcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (swcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Drain").create());
		                }
		                else // if timer is done
		                {
		                    swcooldown.remove(p.getName()); // removing player from HashMap
		                    if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)e;
								{
									p.swingMainHand();
									le.teleport(le.getLocation().add(0, 1, 0));
									le.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 50, 255, false, false));
									for(int i =0; i<8; i++) {
					                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() 
								                {
								                	le.setVelocity(le.getVelocity().zero());
													p.setSprinting(true);
													le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
													p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 1f);
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
														enar.setDamage((player_damage.get(p.getName())*0.07+bsd.Drain.get(p.getUniqueId())*0.07));
													}
													le.damage(0, p);
													le.damage(player_damage.get(p.getName())*0.07+bsd.Drain.get(p.getUniqueId())*0.07, p);
													Holding.holding(p, le, (long) 24);
													p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
													p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1, 0, false, false));
													le.playEffect(org.bukkit.EntityEffect.HURT_EXPLOSION);
													p.setSprinting(false);
								                }
								            }, i*2); 
					                   }
								}
								
							}

							else if (e instanceof Player) 
							{
								
								Player p1 = (Player) e;
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
			                swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			                
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	 if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)e;
								{
									p.swingMainHand();
									le.teleport(le.getLocation().add(0, 1, 0));
									le.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 50, 255, false, false));
									for(int i =0; i<8; i++) {
					                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() 
								                {
								                	le.setVelocity(le.getVelocity().zero());
													p.setSprinting(true);
													le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
													p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 1f);
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
														enar.setDamage((player_damage.get(p.getName())*0.07+bsd.Drain.get(p.getUniqueId())*0.07));
													}
													le.damage(0, p);
													le.damage(player_damage.get(p.getName())*0.07+bsd.Drain.get(p.getUniqueId())*0.07, p);
													Holding.holding(p, le, (long) 24);
													p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
													p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1, 0, false, false));
													le.playEffect(org.bukkit.EntityEffect.HURT_EXPLOSION);
													p.setSprinting(false);
								                }
								            }, i*2); 
					                   }
								}
							}

							else if (e instanceof Player) 
							{
								
								Player p1 = (Player) e;
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
		                swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		                
		            }
				}
			}
			}
		}
	}
	
	@EventHandler
	public void Spray(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD"))
		{
		int sec = 3;

	    
		
		
		if(playerclass.get(p.getUniqueId()) == 1) {
			if(p.isSneaking())
			{
				ev.setCancelled(true);
					
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (cdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Spray").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                	cdcooldown.remove(p.getName()); // removing player from HashMap
		                	p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 0.1f);
		                	p.playSound(p.getLocation(), Sound.ENTITY_FOX_SPIT, 1.0f, 0.1f);
		                    p.damage(6);
		                    ArrayList<Location> line = new ArrayList<Location>();
		                    ArrayList<Location> pie = new ArrayList<Location>();
		                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
		                    AtomicInteger j = new AtomicInteger(0);
		                    for(double d = -Math.PI/6; d<= Math.PI/6; d += Math.PI/180) {
			                    Location l = p.getLocation();
			                    l.setDirection(l.getDirection().normalize().rotateAroundY(d));
			                    l.add(l.getDirection().normalize().multiply(10));
			                    line.add(l);
		                    	
		                    }
		                    for(double d = 0.1; d <= 10; d += 0.3) {
		                    	  for (Location l : line){
				                    Location pl = p.getLocation();
									Vector ltr = l.toVector().subtract(pl.toVector());
									pl.add(ltr.normalize().multiply(d));
									pie.add(pl);
									}
		                    }
		                    

		                    pie.forEach(i -> {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                    		@Override
				                	public void run() 
					                {	
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, i, 2, 0.5,0.5,0.5,0 ,Material.CRIMSON_NYLIUM.createBlockData());
										if(ulton.containsKey(p)) {
											p.getWorld().spawnParticle(Particle.DRIP_LAVA, i, 2, 1, 1, 1);
											}
						                }
			                	   }, j.getAndIncrement()/230); 
							}); 
		                    for(Location l : pie) {

		                    	for (Entity a : p.getWorld().getNearbyEntities(l, 1, 1, 1))
								{
									if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
									{
										LivingEntity le = (LivingEntity)a;
										les.add(le);
									}
								}
		                    }
		                    for(LivingEntity le: les) 
							{
							p.setSprinting(true);
							le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
							if(le instanceof EnderDragon) {
			                    Arrow firstarrow = p.launchProjectile(Arrow.class);
			                    firstarrow.setDamage(0);
			                    firstarrow.remove();
								Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
									a.setShooter(p);
									a.setPickupStatus(PickupStatus.DISALLOWED);
									a.setCritical(false);
									a.setSilent(true);
									a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
								});
								enar.setDamage((player_damage.get(p.getName())*1.2766 + bsd.Spray.get(p.getUniqueId())*0.9853));
							}
							le.damage(0, p);
							le.damage(player_damage.get(p.getName())*1.2766 + bsd.Spray.get(p.getUniqueId())*0.9853, p);
							
							p.setSprinting(false);
							}
							cdcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
	                	p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 0.1f);
	                	p.playSound(p.getLocation(), Sound.ENTITY_FOX_SPIT, 1.0f, 0.1f);
	                    p.damage(6);
	                    ArrayList<Location> line = new ArrayList<Location>();
	                    ArrayList<Location> pie = new ArrayList<Location>();
	                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    AtomicInteger j = new AtomicInteger(0);
	                    for(double d = -Math.PI/6; d<= Math.PI/6; d += Math.PI/180) {
		                    Location l = p.getLocation();
		                    l.setDirection(l.getDirection().normalize().rotateAroundY(d));
		                    l.add(l.getDirection().normalize().multiply(10));
		                    line.add(l);
	                    	
	                    }
	                    for(double d = 0.1; d <= 10; d += 0.3) {
	                    	  for (Location l : line){
			                    Location pl = p.getLocation();
								Vector ltr = l.toVector().subtract(pl.toVector());
								pl.add(ltr.normalize().multiply(d));
								pie.add(pl);
								}
	                    }
	                    

	                    pie.forEach(i -> {
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                    		@Override
			                	public void run() 
				                {	
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, i, 2, 0.5,0.5,0.5,0 ,Material.CRIMSON_NYLIUM.createBlockData());
									if(ulton.containsKey(p)) {
										p.getWorld().spawnParticle(Particle.DRIP_LAVA, i, 2, 1, 1, 1);
										}
					                }
		                	   }, j.getAndIncrement()/230); 
						}); 
	                    for(Location l : pie) {

	                    	for (Entity a : p.getWorld().getNearbyEntities(l, 1, 1, 1))
							{
								if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
								{
									LivingEntity le = (LivingEntity)a;
									les.add(le);
								}
							}
	                    }
	                    for(LivingEntity le: les) 
						{
						p.setSprinting(true);
						le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
						if(le instanceof EnderDragon) {
		                    Arrow firstarrow = p.launchProjectile(Arrow.class);
		                    firstarrow.setDamage(0);
		                    firstarrow.remove();
							Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
								a.setShooter(p);
								a.setPickupStatus(PickupStatus.DISALLOWED);
								a.setCritical(false);
								a.setSilent(true);
								a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
							});
							enar.setDamage((player_damage.get(p.getName())*1.2766 + bsd.Spray.get(p.getUniqueId())*0.9853));
						}
						le.damage(0, p);
						le.damage(player_damage.get(p.getName())*1.2766 + bsd.Spray.get(p.getUniqueId())*0.9853, p);
						
						p.setSprinting(false);
						}
	                    
		                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}}
		}
	}
	
	
	@EventHandler
	public void Dread(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 6;

		if(p.getInventory().getItemInMainHand().getType().name().contains("HOE") && p.isSneaking())
		{
		Holding hold = Holding.getInstance();
	    
		new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
		
		
		
			if(playerclass.get(p.getUniqueId()) == 1 && bsd.Dread.get(p.getUniqueId())>=1) {
				ev.setCancelled(true);
					if(drcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (drcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Dread").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                	drcooldown.remove(p.getName()); // removing player from HashMap
		                	p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 0.1f);
		                	p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 2f);
		                	p.getWorld().spawnParticle(Particle.CURRENT_DOWN, p.getLocation(), 350, 4, 4, 4);
		                	p.getWorld().spawnParticle(Particle.DRAGON_BREATH, p.getLocation(), 350, 4, 4, 4);
	                    	for (Entity a : p.getWorld().getNearbyEntities(p.getLocation(), 6, 6, 6))
							{
								if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
								{
									LivingEntity le = (LivingEntity)a;
										{
										p.setSprinting(true);
										le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
										if(le instanceof EnderDragon) {
						                    Arrow firstarrow = p.launchProjectile(Arrow.class);
						                    firstarrow.setDamage(0);
						                    firstarrow.remove();
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
												ar.setShooter(p);
												ar.setPickupStatus(PickupStatus.DISALLOWED);
												ar.setCritical(false);
												ar.setSilent(true);
												ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage((player_damage.get(p.getName())*0.3));
										}
										le.damage(0, p);
										le.damage(player_damage.get(p.getName())*0.3, p);
										p.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, le.getLocation(), 10);
										Holding.holding(p, le, (long) 25);
										le.setSwimming(true);
										le.playEffect(org.bukkit.EntityEffect.VILLAGER_ANGRY);
										p.setSprinting(false);
										}
								}

								else if (a instanceof Player) 
								{
									
									Player p1 = (Player) a;
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
							}
		                    
							drcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 0.1f);
	                	p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 2f);
	                	p.getWorld().spawnParticle(Particle.CURRENT_DOWN, p.getLocation(), 350, 4, 4, 4);
	                	p.getWorld().spawnParticle(Particle.DRAGON_BREATH, p.getLocation(), 350, 4, 4, 4);
                    	for (Entity a : p.getWorld().getNearbyEntities(p.getLocation(), 6, 6, 6))
						{
							if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)a;
									{
									p.setSprinting(true);
									le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
									if(le instanceof EnderDragon) {
					                    Arrow firstarrow = p.launchProjectile(Arrow.class);
					                    firstarrow.setDamage(0);
					                    firstarrow.remove();
										Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
											ar.setShooter(p);
											ar.setPickupStatus(PickupStatus.DISALLOWED);
											ar.setCritical(false);
											ar.setSilent(true);
											ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
										});
										enar.setDamage((player_damage.get(p.getName())*0.3));
									}
									le.damage(0, p);
									le.damage(player_damage.get(p.getName())*0.3, p);
									p.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, le.getLocation(), 10);
									Holding.holding(p, le, (long) 25);
									le.setSwimming(true);
									le.playEffect(org.bukkit.EntityEffect.VILLAGER_ANGRY);
									p.setSprinting(false);
									}
							}

							else if (a instanceof Player) 
							{
								
								Player p1 = (Player) a;
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
						}
		                drcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
		}
		}

	@EventHandler
	public void Inhale(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("HOE"))
		{
		final Location pl = p.getLocation();
		Action ac = ev.getAction();
		int sec = 6;

	    
		new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
		
		
		if(playerclass.get(p.getUniqueId()) == 1) {
		if(!(p.isSneaking()) && !p.isOnGround() && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{

				
				if(frcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (frcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Inhale").create());
	                }
	                else // if timer is done
	                {
	                    frcooldown.remove(p.getName()); // removing player from HashMap
	                    p.swingMainHand();
						p.playSound(pl, Sound.BLOCK_PORTAL_TRAVEL, 0.5f, 2f);
						for (Entity a : p.getWorld().getNearbyEntities(pl, 5, 5, 5))
						{
                    		if (a instanceof Player) 
							{
								
								Player p1 = (Player) a;
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
							if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")) 
							{
								LivingEntity le = (LivingEntity)a;
								Holding.holding(p, le, (long) 16);
								for(int i =0; i<8; i++) {
				                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
												le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
												le.teleport(pl);
												p.getWorld().spawnParticle(Particle.BLOCK_CRACK, pl, 100, 1,1,1,1,Material.CRIMSON_NYLIUM.createBlockData()); 
							                }
							            }, i*2); 
				                   }								
							}
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, pl, 666, 1,1,1,5,Material.CRIMSON_NYLIUM.createBlockData()); 
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, pl, 255, 6,3,6,Material.POTTED_CRIMSON_ROOTS.createBlockData()); 
								if(ulton.containsKey(p)) {
									p.getWorld().spawnParticle(Particle.LAVA, pl, 50, 6, 3, 6);
									p.getWorld().spawnParticle(Particle.FALLING_LAVA, pl, 50, 6, 3, 6);
								}
								p.playSound(pl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1.0f, 0f);
								p.playSound(pl, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 0f);
							for (Entity a : p.getWorld().getNearbyEntities(pl, 6, 6, 6))
							{
	                    		if (a instanceof Player) 
								{
									
									Player p1 = (Player) a;
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
								if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")) 
								{
									
											LivingEntity le = (LivingEntity)a;
											p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
											p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1, 0, false, false));
											p.setSprinting(true);
											if(le instanceof EnderDragon) {
							                    Arrow firstarrow = p.launchProjectile(Arrow.class);
							                    firstarrow.setDamage(0);
							                    firstarrow.remove();
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
													ar.setShooter(p);
													ar.setPickupStatus(PickupStatus.DISALLOWED);
													ar.setCritical(false);
													ar.setSilent(true);
													ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage((player_damage.get(p.getName())*0.65 + bsd.Inhale.get(p.getUniqueId())*0.635));
											}
											le.damage(0, p);
											le.damage(player_damage.get(p.getName())*0.65 + bsd.Inhale.get(p.getUniqueId())*0.635, p);
											p.setSprinting(false);
						                }							
								}
							}
			            }, 16); 	

						frcooldown.put(p.getName(), System.currentTimeMillis());  
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	p.swingMainHand();
					p.playSound(pl, Sound.BLOCK_PORTAL_TRAVEL, 0.5f, 2f);
					for (Entity a : p.getWorld().getNearbyEntities(pl, 5, 5, 5))
					{
                		if (a instanceof Player) 
						{
							
							Player p1 = (Player) a;
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
						if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")) 
						{
							LivingEntity le = (LivingEntity)a;
							Holding.holding(p, le, (long) 16);
							for(int i =0; i<8; i++) {
			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
											le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
											le.teleport(pl);
											p.getWorld().spawnParticle(Particle.BLOCK_CRACK, pl, 100, 1,1,1,1,Material.CRIMSON_NYLIUM.createBlockData()); 
						                }
						            }, i*2); 
			                   }								
						}
					}
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
							p.getWorld().spawnParticle(Particle.BLOCK_CRACK, pl, 666, 1,1,1,5,Material.CRIMSON_NYLIUM.createBlockData()); 
							p.getWorld().spawnParticle(Particle.BLOCK_CRACK, pl, 255, 6,3,6,Material.POTTED_CRIMSON_ROOTS.createBlockData()); 
							if(ulton.containsKey(p)) {
								p.getWorld().spawnParticle(Particle.LAVA, pl, 50, 6, 3, 6);
								p.getWorld().spawnParticle(Particle.FALLING_LAVA, pl, 50, 6, 3, 6);
							}
							p.playSound(pl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1.0f, 0f);
							p.playSound(pl, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 0f);
						for (Entity a : p.getWorld().getNearbyEntities(pl, 6, 6, 6))
						{
                    		if (a instanceof Player) 
							{
								
								Player p1 = (Player) a;
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
							if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")) 
							{
								
										LivingEntity le = (LivingEntity)a;
										p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
										p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1, 0, false, false));
										p.setSprinting(true);
										if(le instanceof EnderDragon) {
						                    Arrow firstarrow = p.launchProjectile(Arrow.class);
						                    firstarrow.setDamage(0);
						                    firstarrow.remove();
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
												ar.setShooter(p);
												ar.setPickupStatus(PickupStatus.DISALLOWED);
												ar.setCritical(false);
												ar.setSilent(true);
												ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage((player_damage.get(p.getName())*0.65 + bsd.Inhale.get(p.getUniqueId())*0.635));
										}
										le.damage(0, p);
										le.damage(player_damage.get(p.getName())*0.65 + bsd.Inhale.get(p.getUniqueId())*0.635, p);
										p.setSprinting(false);
					                }							
							}
						}
		            }, 16); 	
					frcooldown.put(p.getName(), System.currentTimeMillis());  
				}
	            
			} // adding players name + current system time in miliseconds
            
		}
		}
	}

	@EventHandler
	public void CrimsonAdvance(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD"))
		{
		Action ac = ev.getAction();
		int sec = 3;

	    
		new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
		
		
		if(playerclass.get(p.getUniqueId()) == 1) {
		if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
				

				if(cacooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (cacooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use CrimsonAdvance").create());
	                }
	                else // if timer is done
	                {
	                	cacooldown.remove(p.getName()); // removing player from HashMap
						for(int i =0; i<3; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                    p.swingMainHand();
					                	ArrayList<Location> line = new ArrayList<Location>();
					                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
					                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
					                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0f);
					                    AtomicInteger j = new AtomicInteger(0);
					                    AtomicInteger b = new AtomicInteger(0);
					                    for(double d = 0; d <= 2.5; d += 0.1) {
						                    Location pl = p.getEyeLocation();
											pl.add(pl.clone().getDirection().normalize().multiply(d));
											line.add(pl);
					                    }
					                    if(line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().isPresent()) {
						                    Location block =line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().get();
						                    for(int k=0; k<line.indexOf(block); k++) {
							                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							             		@Override
									                	public void run() 
										                {	
									                    	p.teleport(line.get(b.getAndIncrement()));
															p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 20, 3,3,3,0);
															p.getWorld().spawnParticle(Particle.CRIMSON_SPORE, p.getLocation(), 20, 3,3,3,0);
									                    	for (Entity e : p.getNearbyEntities(3, 3, 3))
															{
									                    		if ((!(e == p))&& e instanceof LivingEntity) 
																{
																	LivingEntity le = (LivingEntity)e;
																	les.add(le);		                    			
																}
															}
											            }
								                	   }, j.incrementAndGet()/50); 
												 }

					                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						             		@Override
								                	public void run() 
									                {	

						             				for(LivingEntity le: les) {
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
															enar.setDamage(player_damage.get(p.getName())*0.65 + bsd.Undying.get(p.getUniqueId())*0.53);								
														}
						             					p.setSprinting(true);
														le.damage(0,p);
														le.damage(player_damage.get(p.getName())*0.65+ bsd.Undying.get(p.getUniqueId())*0.53,p);
						             					p.setSprinting(false);
						             				}

										            }
					                	   }, j.incrementAndGet()/50); 
						                }
					                    
					                    else {
						                    	line.forEach(i -> {
							                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							             		@Override
									                	public void run() 
										                {	
									             			p.teleport(i);
															p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 20, 3,3,3,0);
															p.getWorld().spawnParticle(Particle.CRIMSON_SPORE, p.getLocation(), 20, 3,3,3,0);
									                    	for (Entity e : p.getNearbyEntities(3, 3, 3))
															{
									                    		if ((!(e == p))&& e instanceof LivingEntity) 
																{
																	LivingEntity le = (LivingEntity)e;
																	les.add(le);		                    			
																}
															}
											            }
								                	   }, j.incrementAndGet()/50); 
												 });
						                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							             		@Override
									                	public void run() 
										                {	

							             				for(LivingEntity le: les) {
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
																enar.setDamage(player_damage.get(p.getName())*0.65 + bsd.Undying.get(p.getUniqueId())*0.53);								
															}
							             					p.setSprinting(true);
															le.damage(0,p);
															le.damage(player_damage.get(p.getName())*0.65+ bsd.Undying.get(p.getUniqueId())*0.53,p);
							             					p.setSprinting(false);
							             				}

											            }
						                	   }, j.incrementAndGet()/50); 
					                    	}
					                }
					            }, i*5); 
		                }

						cacooldown.put(p.getName(), System.currentTimeMillis());  
	                }
	            }
				
	            else // if cooldown doesn't have players name in it
	            {
	            	for(int i =0; i<3; i++) {
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                    p.swingMainHand();
				                	ArrayList<Location> line = new ArrayList<Location>();
				                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0f);
				                    AtomicInteger j = new AtomicInteger(0);
				                    AtomicInteger b = new AtomicInteger(0);
				                    for(double d = 0; d <= 2.5; d += 0.1) {
					                    Location pl = p.getEyeLocation();
										pl.add(pl.clone().getDirection().normalize().multiply(d));
										line.add(pl);
				                    }
				                    if(line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().isPresent()) {
					                    Location block =line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().get();
					                    for(int k=0; k<line.indexOf(block); k++) {
						                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						             		@Override
								                	public void run() 
									                {	
								                    	p.teleport(line.get(b.getAndIncrement()));
														p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 20, 3,3,3,0);
														p.getWorld().spawnParticle(Particle.CRIMSON_SPORE, p.getLocation(), 20, 3,3,3,0);
								                    	for (Entity e : p.getNearbyEntities(3, 3, 3))
														{
								                    		if ((!(e == p))&& e instanceof LivingEntity) 
															{
																LivingEntity le = (LivingEntity)e;
																les.add(le);		                    			
															}
														}
										            }
							                	   }, j.incrementAndGet()/50); 
											 }

				                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					             		@Override
							                	public void run() 
								                {	

					             				for(LivingEntity le: les) {
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
														enar.setDamage(player_damage.get(p.getName())*0.65 + bsd.Undying.get(p.getUniqueId())*0.53);								
													}
					             					p.setSprinting(true);
													le.damage(0,p);
													le.damage(player_damage.get(p.getName())*0.65+ bsd.Undying.get(p.getUniqueId())*0.53,p);
					             					p.setSprinting(false);
					             				}

									            }
				                	   }, j.incrementAndGet()/50); 
					                }
				                    
				                    else {
					                    	line.forEach(i -> {
						                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						             		@Override
								                	public void run() 
									                {	
								             			p.teleport(i);
														p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 20, 3,3,3,0);
														p.getWorld().spawnParticle(Particle.CRIMSON_SPORE, p.getLocation(), 20, 3,3,3,0);
								                    	for (Entity e : p.getNearbyEntities(3, 3, 3))
														{
								                    		if ((!(e == p))&& e instanceof LivingEntity) 
															{
																LivingEntity le = (LivingEntity)e;
																les.add(le);		                    			
															}
														}
										            }
							                	   }, j.incrementAndGet()/50); 
											 });
					                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						             		@Override
								                	public void run() 
									                {	

						             				for(LivingEntity le: les) {
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
															enar.setDamage(player_damage.get(p.getName())*0.65 + bsd.Undying.get(p.getUniqueId())*0.53);								
														}
						             					p.setSprinting(true);
														le.damage(0,p);
														le.damage(player_damage.get(p.getName())*0.65+ bsd.Undying.get(p.getUniqueId())*0.53,p);
						             					p.setSprinting(false);
						             				}

										            }
					                	   }, j.incrementAndGet()/50); 
				                    	}
				                }
				            }, i*5); 
	                }
	            	cacooldown.put(p.getName(), System.currentTimeMillis());  
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	
	@EventHandler
	public void Frenzy(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD"))
		{
		Action ac = ev.getAction();
		int sec = 9;

	    
		new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
		
		
		if(playerclass.get(p.getUniqueId()) == 1) {
			final Location pl = p.getLocation();
		if(!(p.isSneaking()) && !p.isOnGround() && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
				

				if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (smcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Frenzy").create());
	                }
	                else // if timer is done
	                {
	                    smcooldown.remove(p.getName()); // removing player from HashMap
						for(int i =0; i<8; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	p.setNoDamageTicks(0);
					                	p.teleport(pl);
										p.damage(1);
					                    p.swingMainHand();
										p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.5f, 2f);
										p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 222, 3,3,3, 0); 
										for (Entity a : p.getWorld().getNearbyEntities(p.getLocation(), 3.5, 3.5, 3.5))
										{
				                    		if (a instanceof Player) 
											{
												
												Player p1 = (Player) a;
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
											if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")) 
											{
												LivingEntity le = (LivingEntity)a;
												le.playEffect(EntityEffect.HURT_BERRY_BUSH);
												le.teleport(le.getLocation().add(pl.toVector().subtract(le.getLocation().clone().toVector()).normalize().multiply(1.4)));
												p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
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
													enar.setDamage(player_damage.get(p.getName())*0.443 + bsd.Burstout.get(p.getUniqueId())*0.436);								
												}
												p.setSprinting(true);
												le.damage(0, p);
												le.damage(player_damage.get(p.getName())*0.443 + bsd.Burstout.get(p.getUniqueId())*0.436, p);
												p.setSprinting(false);
											}
										}
					                }
					            }, i*2); 
		                }

						smcooldown.put(p.getName(), System.currentTimeMillis());  
	                }
	            }
				
	            else // if cooldown doesn't have players name in it
	            {
	            	for(int i =0; i<8; i++) {
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	p.setNoDamageTicks(0);
				                	p.teleport(pl);
									p.damage(1);
				                    p.swingMainHand();
									p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.5f, 2f);
									p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 222, 3,3,3, 0); 
									for (Entity a : p.getWorld().getNearbyEntities(p.getLocation(), 3.5, 3.5, 3.5))
									{
			                    		if (a instanceof Player) 
										{
											
											Player p1 = (Player) a;
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
										if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")) 
										{
											LivingEntity le = (LivingEntity)a;
											le.playEffect(EntityEffect.HURT_BERRY_BUSH);
											le.teleport(le.getLocation().add(pl.toVector().subtract(le.getLocation().clone().toVector()).normalize().multiply(1.4)));
											p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
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
												enar.setDamage(player_damage.get(p.getName())*0.443 + bsd.Burstout.get(p.getUniqueId())*0.436);								
											}
											p.setSprinting(true);
											le.damage(0, p);
											le.damage(player_damage.get(p.getName())*0.443 + bsd.Burstout.get(p.getUniqueId())*0.436, p);
											p.setSprinting(false);
										}
									}
				                }
				            }, i*2); 
	                }	
					smcooldown.put(p.getName(), System.currentTimeMillis());  
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	
	@EventHandler
	public void Lunacy(EntityDamageEvent d) 
	{

	    
		
		
		if(d.getEntity() instanceof Player && !d.isCancelled()) 
		{
			Player p1 = (Player)d.getEntity();
			if(p1.getInventory().getItemInMainHand().getType().name().contains("SWORD") || p1.getInventory().getItemInMainHand().getType().name().contains("HOE"))
			{
			int sec = 70;
			if(playerclass.get(p1.getUniqueId()) == 1) {
						
						
						if((p1.getHealth()-d.getDamage())<=p1.getMaxHealth()*0.05) {
							if(lncooldown.containsKey(p1.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
					        {
					            long timer = (lncooldown.get(p1.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
					            if(!(timer < 0)) // if timer is still more then 0 or 0
					            {
					            	p1.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to Undying").create());
					            }
					            else // if timer is done
					            {
					                lncooldown.remove(p1.getName()); // removing player from HashMap
					                d.setCancelled(true);
									p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 4, false, false));
									p1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 4, false, false));
									p1.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[UNDYING]").color(ChatColor.BOLD).color(ChatColor.RED).create());
									lncooldown.put(p1.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
						        
					            }
					        }
					        else // if cooldown doesn't have players name in it
					        {
				                d.setCancelled(true);
								p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 4, false, false));
								p1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 4, false, false));
								p1.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[UNDYING]").color(ChatColor.BOLD).color(ChatColor.RED).create());
								lncooldown.put(p1.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
					        }
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
		Item i = ev.getItemDrop();
		ItemStack is = i.getItemStack();
	    
		
		
		
		
			if(playerclass.get(p.getUniqueId()) == 1 && (is.getType().name().contains("SWORD")||is.getType().name().contains("HOE")) && p.isSneaking())
			{
				ev.setCancelled(true);
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (sultcooldown.get(p.getName())/1000 + 60) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use BloodBoil").create());
	                }
	                else // if timer is done
	                {
	                    sultcooldown.remove(p.getName()); // removing player from HashMap
	                    p.playSound(p.getLocation(), Sound.BLOCK_FIRE_AMBIENT, 1.0f, 0.1f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1.0f, 0.1f);
						p.getWorld().spawnParticle(Particle.FLASH, l, 10, 8, 2, 8);
						p.getWorld().spawnParticle(Particle.LAVA, l, 10, 8, 2, 8);
						p.getWorld().spawnParticle(Particle.DRIP_LAVA, l, 10, 8, 2, 8);
						p.getWorld().spawnParticle(Particle.FALLING_LAVA, l, 10, 8, 2, 8);
						p.sendTitle(ChatColor.MAGIC + "SWORDSTORM", null);
						ulton.put(p, true);
						p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, 4, false, false));
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	ulton.remove(p);
			                }
			            }, 200); 
		                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	p.playSound(p.getLocation(), Sound.BLOCK_FIRE_AMBIENT, 1.0f, 0.1f);
                    p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1.0f, 0.1f);
					p.getWorld().spawnParticle(Particle.FLASH, l, 10, 8, 2, 8);
					p.getWorld().spawnParticle(Particle.LAVA, l, 10, 8, 2, 8);
					p.getWorld().spawnParticle(Particle.DRIP_LAVA, l, 10, 8, 2, 8);
					p.getWorld().spawnParticle(Particle.FALLING_LAVA, l, 10, 8, 2, 8);
					p.sendTitle(ChatColor.MAGIC + "SWORDSTORM", null);
					ulton.put(p, true);
					p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, 4, false, false));
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	ulton.remove(p);
		                }
		            }, 200); 
	                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();

		if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") || (p.getInventory().getItemInMainHand().getType().name().contains("HOE")))
		{
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 1)
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
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 1) {
				if(p.getInventory().getItemInMainHand().getType()==Material.IRON_SWORD || p.getInventory().getItemInMainHand().getType()==Material.IRON_HOE)
				{
						player_damage.put(p.getName(), 7 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_SWORD || p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_HOE)
				{
						player_damage.put(p.getName(), 8 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_SWORD|| p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_HOE)
				{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.STONE_SWORD|| p.getInventory().getItemInMainHand().getType()==Material.STONE_HOE)
				{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_SWORD|| p.getInventory().getItemInMainHand().getType()==Material.WOODEN_HOE)
				{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_SWORD|| p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_HOE)
				{
						player_damage.put(p.getName(), 10 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
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
			LivingEntity le = (LivingEntity)e;
	
		    
			
			
			
			if(playerclass.get(p.getUniqueId()) == 1) {
				p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
				p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(30);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 1, false, false));
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 40, 1, false, false));
				d.setDamage(d.getDamage()*(1+bsd.Lunacy.get(p.getUniqueId())*0.036));		
				if(ulton.containsKey(p))
				{
					le.setFireTicks(100);
					p.getWorld().spawnParticle(Particle.LAVA, le.getLocation(), 30, 1, 1, 1);
					d.setDamage((d.getDamage()*(1+player_damage.get(p.getName())*0.02)));
					p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
				}
				if(p.getInventory().getItemInMainHand().getType()==Material.IRON_SWORD || p.getInventory().getItemInMainHand().getType()==Material.IRON_HOE)
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
				
				if(p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_SWORD || p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_HOE)
				{
						player_damage.put(p.getName(), 8 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
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
				
				if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_SWORD|| p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_HOE)
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
				
				if(p.getInventory().getItemInMainHand().getType()==Material.STONE_SWORD|| p.getInventory().getItemInMainHand().getType()==Material.STONE_HOE)
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
				
				if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_SWORD|| p.getInventory().getItemInMainHand().getType()==Material.WOODEN_HOE)
				{
						player_damage.remove(p.getName());
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
				
				if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_SWORD|| p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_HOE)
				{
						player_damage.remove(p.getName());
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
			LivingEntity le = (LivingEntity)e;
			
		    
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
				
				
				
				
				if(playerclass.get(p.getUniqueId()) == 1) {
					p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
					p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(30);
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 1, false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 40, 1, false, false));
					d.setDamage((d.getDamage()*(1+bsd.Lunacy.get(p.getUniqueId())*0.04)));		
					if(ulton.containsKey(p))
					{
						le.setFireTicks(100);
						p.getWorld().spawnParticle(Particle.LAVA, p.getLocation(), 70, 6, 3, 6);
						d.setDamage(d.getDamage()*(1+player_damage.get(p.getName())*0.06));
						p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
					}
					if(p.getInventory().getItemInMainHand().getType()==Material.IRON_SWORD || p.getInventory().getItemInMainHand().getType()==Material.IRON_HOE)
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_SWORD || p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_HOE)
					{
							player_damage.put(p.getName(), 8 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_SWORD|| p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_HOE)
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.STONE_SWORD|| p.getInventory().getItemInMainHand().getType()==Material.STONE_HOE)
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_SWORD|| p.getInventory().getItemInMainHand().getType()==Material.WOODEN_HOE)
					{
							player_damage.remove(p.getName());
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_SWORD|| p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_HOE)
					{
							player_damage.remove(p.getName());
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
	
	
}



