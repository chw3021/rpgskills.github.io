package io.github.chw3021.tamer;



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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Cat;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderDragonPart;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Panda.Gene;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Tamskills implements Listener, Serializable {
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = -4588210529761002140L;
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private HashMap<Player, LivingEntity> attention = new HashMap<Player, LivingEntity>();
	private Multimap<Player, Mob> tamed = ArrayListMultimap.create();
	private HashMap<Player, LivingEntity> wolfm = new HashMap<Player, LivingEntity>();
	private HashMap<Player, LivingEntity> catm = new HashMap<Player, LivingEntity>();
	private HashMap<Player, LivingEntity> parm = new HashMap<Player, LivingEntity>();
	private HashMap<Player, LivingEntity> pandam = new HashMap<Player, LivingEntity>();
	static private HashMap<UUID, Integer> pandat = new HashMap<UUID, Integer>();
	private HashMap<UUID, UUID> pandao = new HashMap<UUID, UUID>();
	private HashMap<Creeper, Player> creeper = new HashMap<Creeper, Player>();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private TamSkillsData tsd = new TamSkillsData(TamSkillsData.loadData(path +"/plugins/RPGskills/TamSkillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		TamSkillsData t = new TamSkillsData(TamSkillsData.loadData(path +"/plugins/RPGskills/TamSkillsData.data"));
		tsd = t;
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

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		TamSkillsData t = new TamSkillsData(TamSkillsData.loadData(path +"/plugins/RPGskills/TamSkillsData.data"));
		tsd = t;
		
	}
	
	@EventHandler
	public void PressTheAttack(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 5;
        
		
		
		if(playerclass.get(p.getUniqueId()) == 9)
		{
			
			
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && !(p.isSneaking()) && tsd.PressTheAttack.get(p.getUniqueId())>=1)
			{
				ev.setCancelled(true);
				if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (sdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use PressTheAttack").create());
	                }
	                else // if timer is done
	                {
	                    sdcooldown.remove(p.getName()); // removing player from HashMap
	                    Arrow sn = (Arrow) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.ARROW);
	                	HashMap<Player, Double> damage = new HashMap<Player, Double>();
	                	damage.put(p,(double) 0);
						if(tamed.containsKey(p)) {
							tamed.get(p).forEach(t -> {
								if(t.getType() != EntityType.PARROT && t.isValid() && !t.isDead()) {
									damage.compute(p, (k,v) -> v+t.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()) ;
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
	            else // if cooldown doesn't have players name in it
	            {
                    Arrow sn = (Arrow) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.ARROW);
                	HashMap<Player, Double> damage = new HashMap<Player, Double>();
                	damage.put(p,(double) 0);
					if(tamed.containsKey(p)) {
						tamed.get(p).forEach(t -> {
							if(t.getType() != EntityType.PARROT) {
								damage.compute(p, (k,v) -> v+t.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()) ;
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
		
	@EventHandler
	public void PressTheAttack(ProjectileHitEvent ev) 
	{
		Projectile j = (Projectile)ev.getEntity();
		Entity e = (Entity)ev.getHitEntity();
		if(j.getShooter() instanceof Player && e instanceof LivingEntity && j instanceof Arrow && !(e.hasMetadata("fake"))) {
			Player p = (Player) ev.getEntity().getShooter();
			LivingEntity le = (LivingEntity)e;
	        
			
			
			
			if(playerclass.get(p.getUniqueId()) == 9) {
				if (le instanceof Player) 
				{
					Player p1 = (Player) le;
					if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
					if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
						{
							return;
						}
					}
				}
				if(j.hasMetadata("Target") && !le.hasMetadata("cs of "+p.getName())&& !le.hasMetadata("pet of "+p.getName())&& !le.hasMetadata("bees of "+p.getName())&& !le.hasMetadata("pd of "+p.getName())&& !le.hasMetadata("cp of "+p.getName())&& !le.hasMetadata("ig of "+p.getName())) {
					attention.put(p, le);
	                sdcooldown.put(p.getName(), System.currentTimeMillis());
					if(tamed.containsKey(p)) {
						if(ev.getHitEntity() instanceof Wither) {
							Wither w =(Wither) ev.getHitEntity();
							Arrow ar = (Arrow) ev.getEntity();
							{
		                		if (w.getHealth()<=w.getMaxHealth()/2) 
								{
        		                    w.damage(ar.getDamage()/4, p);
								}
							}
						}
						if(ev.getHitEntity() instanceof Enderman) {
							Enderman en =(Enderman) ev.getHitEntity();
							Arrow ar = (Arrow) ev.getEntity();
							{
    		                    en.damage(ar.getDamage()/4, p);
							}
						}
						HashSet<Mob> remove = new HashSet<Mob>();
						tamed.get(p).forEach(tameds -> {
							tameds.teleport(ev.getHitEntity().getLocation());
							tameds.teleport(le);
							tameds.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 120, 4, false, false));
							if(tameds instanceof Creeper) {
								Creeper ct = (Creeper) tameds;
								remove.add(ct);
							}
							else if(tameds instanceof CaveSpider) {
								CaveSpider cs = (CaveSpider) tameds;
								cs.getNearbyEntities(3, 3, 3).forEach(e1 ->{
             						if (e1 instanceof Player) 
									{
										Player p1 = (Player) e1;
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
			                		if(e1 instanceof LivingEntity&& !(e1.hasMetadata("fake")) && e1!=p) {
			                			LivingEntity le1 = (LivingEntity)e1;
			    						if(le1 instanceof EnderDragon) {
			    		                    Arrow firstarrow = p.launchProjectile(Arrow.class);
			    		                    firstarrow.setDamage(0);
			    		                    firstarrow.remove();
			    							Arrow enar = (Arrow) p.getWorld().spawn(le1.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
			    								ar.setShooter(p);
			    								ar.setCritical(false);
			    								ar.setSilent(true);
			    								ar.setPickupStatus(PickupStatus.DISALLOWED);
			    								ar.setVelocity(le1.getLocation().clone().add(0, -1, 0).toVector().subtract(le1.getLocation().toVector()).normalize().multiply(2.6));
			    							});
			    							enar.setDamage(player_damage.get(p.getName())*0.56*(1+tsd.Spidey.get(p.getUniqueId())*0.03)*(1+tsd.Taming.get(p.getUniqueId())*0.02));								
			    						}
			                			p.setSprinting(true);
			                			le1.damage(0,p);
			                			le1.damage(player_damage.get(p.getName())*0.56*(1+tsd.Spidey.get(p.getUniqueId())*0.03)*(1+tsd.Taming.get(p.getUniqueId())*0.02),p);
		             					p.getWorld().playSound(cs.getLocation(), Sound.ENTITY_SPIDER_STEP, 1, 2f);
			                			p.setSprinting(false);
			                			
			                		}
             					});
             					p.getWorld().spawnParticle(Particle.ITEM_CRACK, cs.getLocation(), 50,1,1,1,0.5, new ItemStack(Material.SPIDER_EYE));
             					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, cs.getLocation(), 50,1,1,1,0.5,Material.COBWEB.createBlockData());
             					p.getWorld().playSound(cs.getLocation(), Sound.ENTITY_SPIDER_AMBIENT, 1, 0.8f);
             					remove.add(cs);
             					cs.remove();
							}
						});
						remove.forEach(r -> {
							if(r instanceof Creeper) {
								Creeper ct = (Creeper) r;
								ct.explode();
							}
							else {
             					tamed.remove(p, r);
							}
							
						});
					}
				}
			}
		}
		if(j.getShooter() instanceof Player && e instanceof EnderDragonPart && j instanceof Arrow && !(e.hasMetadata("fake"))) {
			Player p = (Player) ev.getEntity().getShooter();
			EnderDragonPart le = (EnderDragonPart)e;
	        
			
			
			
			if(playerclass.get(p.getUniqueId()) == 9) {
				if(j.hasMetadata("Target")) {
					if(tamed.containsKey(p)) {
		                sdcooldown.put(p.getName(), System.currentTimeMillis());
						HashSet<Mob> remove = new HashSet<Mob>();
						tamed.get(p).forEach(tameds -> {
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	             		@Override
	    			                	public void run() 
	    				                {	
			    							tameds.teleport(ev.getHitEntity().getLocation());
			    							tameds.attack(le);
			    							tameds.teleport(le);
			    							tameds.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 3, false, false));
			    							tameds.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 120, 4, false, false));
	    					            }
	    		                	   }, 1); 
							if(tameds instanceof Creeper) {
								Creeper ct = (Creeper) tameds;
								remove.add(ct);
							}
							else if(tameds instanceof CaveSpider) {
								CaveSpider cs = (CaveSpider) tameds;
								cs.getNearbyEntities(3, 3, 3).forEach(e1 ->{
             						if (e1 instanceof Player) 
									{
										Player p1 = (Player) e1;
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
			                		if(e1 instanceof LivingEntity&& !(e1.hasMetadata("fake")) && e1!=p) {
			                			LivingEntity le1 = (LivingEntity)e1;
			    						if(le1 instanceof EnderDragon) {
			    		                    Arrow firstarrow = p.launchProjectile(Arrow.class);
			    		                    firstarrow.setDamage(0);
			    		                    firstarrow.remove();
			    							Arrow enar = (Arrow) p.getWorld().spawn(le1.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
			    								ar.setShooter(p);
			    								ar.setCritical(false);
			    								ar.setSilent(true);
			    								ar.setPickupStatus(PickupStatus.DISALLOWED);
			    								ar.setVelocity(le1.getLocation().clone().add(0, -1, 0).toVector().subtract(le1.getLocation().toVector()).normalize().multiply(2.6));
			    							});
			    							enar.setDamage(player_damage.get(p.getName())*0.56*(1+tsd.Spidey.get(p.getUniqueId())*0.03)*(1+tsd.Taming.get(p.getUniqueId())*0.02));								
			    						}
			                			p.setSprinting(true);
			                			le1.damage(0,p);
			                			le1.damage(player_damage.get(p.getName())*0.56*(1+tsd.Spidey.get(p.getUniqueId())*0.03)*(1+tsd.Taming.get(p.getUniqueId())*0.02),p);
		             					p.getWorld().playSound(cs.getLocation(), Sound.ENTITY_SPIDER_STEP, 1, 2f);
			                			p.setSprinting(false);
			                			
			                		}
             					});
             					p.getWorld().spawnParticle(Particle.ITEM_CRACK, cs.getLocation(), 50,1,1,1,0.5, new ItemStack(Material.SPIDER_EYE));
             					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, cs.getLocation(), 50,1,1,1,0.5,Material.COBWEB.createBlockData());
             					p.getWorld().playSound(cs.getLocation(), Sound.ENTITY_SPIDER_AMBIENT, 1, 0.8f);
             					remove.add(cs);
             					cs.remove();
							}
						});
						remove.forEach(r -> {
							if(r instanceof Creeper) {
								Creeper ct = (Creeper) r;
								ct.explode();
							}
							else {
             					tamed.remove(p, r);
							}
							
						});
					}
				}
			}
		}
	}	

	@EventHandler
	public void PressTheAttack(EntityDamageByEntityEvent d) 
	{
	
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) {

			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
	        
			
			
			if(playerclass.get(p.getUniqueId()) == 9 && !le.hasMetadata("cs of "+p.getName())&& !le.hasMetadata("pet of "+p.getName())&& !le.hasMetadata("bees of "+p.getName())&& !le.hasMetadata("pd of "+p.getName())&& !le.hasMetadata("cp of "+p.getName())&& !le.hasMetadata("ig of "+p.getName())) {
				if(!le.hasMetadata("untargetable")&& !(le.hasMetadata("fake")) && !d.isCancelled()) {
					if (le instanceof Player) 
					{
						Player p1 = (Player) le;
						if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
						if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
							{
								d.setCancelled(true);
								return;
							}
						}
					}
					attention.put(p, le);
					if(tamed.containsKey(p)) {
						for(Mob tameds : tamed.get(p)) {
							tameds.setTarget(le);
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void Spidey(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 7;
        

		
		
		if((ac == Action.RIGHT_CLICK_AIR|| ac == Action.RIGHT_CLICK_BLOCK) && (p.isSneaking()) && !p.isSprinting())
		{
			if(playerclass.get(p.getUniqueId()) == 9) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
			{
				

				if(swcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (swcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Spidey").create());
	                }
	                else // if timer is done
	                {
	                    swcooldown.remove(p.getName()); // removing player from HashMap
	                    p.playSound(p.getLocation(), Sound.ENTITY_PHANTOM_BITE, 1, 0);
						CaveSpider cs = (CaveSpider) p.getWorld().spawnEntity(p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation(), EntityType.CAVE_SPIDER);
						cs.setInvulnerable(true);
						cs.setCustomName(p.getName());
						cs.setMetadata("cs of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
						cs.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), true));
						cs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						cs.setSilent(true);
						tamed.put(p, cs);
	                    if(attention.containsKey(p)) {
	                    	cs.setTarget(attention.get(p));
	                    }
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
		                	public void run() 
			                {	
             					if(tamed.containsEntry(p, cs)) {
             					cs.getNearbyEntities(3, 3, 3).forEach(e ->{
             						if (e instanceof Player) 
									{
										PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
			                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p) {
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
			    							enar.setDamage(player_damage.get(p.getName())*0.56*(1+tsd.Spidey.get(p.getUniqueId())*0.03)*(1+tsd.Taming.get(p.getUniqueId())*0.02));								
			    						}
			                			p.setSprinting(true);
			                			le.damage(0,p);
			                			le.damage(player_damage.get(p.getName())*0.56*(1+tsd.Spidey.get(p.getUniqueId())*0.03)*(1+tsd.Taming.get(p.getUniqueId())*0.02),p);
		             					p.getWorld().playSound(cs.getLocation(), Sound.ENTITY_SPIDER_STEP, 1, 2f);
			                			p.setSprinting(false);
			                			
			                		}
             					});
             					p.getWorld().spawnParticle(Particle.ITEM_CRACK, cs.getLocation(), 50,1,1,1,0.5, new ItemStack(Material.SPIDER_EYE));
             					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, cs.getLocation(), 50,1,1,1,0.5,Material.COBWEB.createBlockData());
             					p.getWorld().playSound(cs.getLocation(), Sound.ENTITY_SPIDER_AMBIENT, 1, 0.8f);
                 					tamed.remove(p, cs);       
		                    	cs.remove();      						
             					}
				            }
						}, 60); 
		                swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		                
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	p.playSound(p.getLocation(), Sound.ENTITY_PHANTOM_BITE, 1, 0);
                    p.playSound(p.getLocation(), Sound.ENTITY_FOX_BITE, 1, 0);
                    p.playSound(p.getLocation(), Sound.ENTITY_PANDA_BITE, 1, 0);
					CaveSpider cs = (CaveSpider) p.getWorld().spawnEntity(p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation(), EntityType.CAVE_SPIDER);
					cs.setInvulnerable(true);
					cs.setCustomName(p.getName());
					cs.setMetadata("cs of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
					cs.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), true));
					cs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					cs.setSilent(true);
					tamed.put(p, cs);
                    if(attention.containsKey(p)) {
                    	cs.setTarget(attention.get(p));
                    }
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
	                	public void run() 
		                {	
         					if(tamed.containsEntry(p, cs)) {
         					cs.getNearbyEntities(3, 3, 3).forEach(e ->{
         						if (e instanceof Player) 
								{
									PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
		                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p) {
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
		    							enar.setDamage(player_damage.get(p.getName())*0.56*(1+tsd.Spidey.get(p.getUniqueId())*0.03)*(1+tsd.Taming.get(p.getUniqueId())*0.02));								
		    						}
		                			p.setSprinting(true);
		                			le.damage(0,p);
		                			le.damage(player_damage.get(p.getName())*0.56*(1+tsd.Spidey.get(p.getUniqueId())*0.03)*(1+tsd.Taming.get(p.getUniqueId())*0.02),p);
	             					p.getWorld().playSound(cs.getLocation(), Sound.ENTITY_SPIDER_STEP, 1, 2f);
		                			p.setSprinting(false);
		                			
		                		}
         					});
         					p.getWorld().spawnParticle(Particle.ITEM_CRACK, cs.getLocation(), 50,1,1,1,0.5, new ItemStack(Material.SPIDER_EYE));
         					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, cs.getLocation(), 50,1,1,1,0.5,Material.COBWEB.createBlockData());
         					p.getWorld().playSound(cs.getLocation(), Sound.ENTITY_SPIDER_AMBIENT, 1, 0.8f);
             				tamed.remove(p, cs);       
	                    	cs.remove();      						
         					}
			            }
					}, 60); 
	                swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	                
	            }
			}
		}
		}
	}

	
	@EventHandler
	public void Pets(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 30;

        
		
		
		if(playerclass.get(p.getUniqueId()) == 9) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && p.isSneaking())
			{
				ev.setCancelled(true);
				
				
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (cdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Pets").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
		                    if(wolfm.containsKey(p)) {
		                    	wolfm.get(p).remove();
		                    }
		                    Wolf w = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
		                    w.setTamed(true);
		                    w.setOwner(p);
		                    w.setAdult();
		                    w.setBreed(false);
		                    w.setAgeLock(true);
							w.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
							w.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(w.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()*(1+tsd.Pets.get(p.getUniqueId())*0.05)*(1+tsd.Taming.get(p.getUniqueId())*0.1));
							w.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(w.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue()*(1+tsd.Taming.get(p.getUniqueId())*0.015));
							w.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(w.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*(2+tsd.Pets.get(p.getUniqueId())*0.055)*(1+tsd.Taming.get(p.getUniqueId())*0.045));
							w.setHealth(w.getMaxHealth());
							w.setMetadata("pet of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
							w.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), true));
		                    w.setCustomName(p.getName());
		                    w.setSilent(true);
		                    if(catm.containsKey(p)) {
		                    	catm.get(p).remove();
		                    }
		                    Cat cat = (Cat) p.getWorld().spawnEntity(p.getLocation(), EntityType.CAT);
		                    cat.setTamed(true);
		                    cat.setOwner(p);
		                    cat.setAdult();
		                    cat.setBreed(false);
		                    cat.setAgeLock(true);
							cat.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(cat.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()*(1+tsd.Pets.get(p.getUniqueId())*0.05)*(1+tsd.Taming.get(p.getUniqueId())*0.1));
							cat.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(cat.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue()*(1+tsd.Taming.get(p.getUniqueId())*0.01));
							cat.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(cat.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*(2+tsd.Pets.get(p.getUniqueId())*0.03)*(1+tsd.Taming.get(p.getUniqueId())*0.035));
							cat.setHealth(cat.getMaxHealth());
							cat.setMetadata("pet of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
							cat.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), true));
		                    cat.setCustomName(p.getName());
		                    cat.setSilent(true);
		                    if(parm.containsKey(p)) {
		                    	parm.get(p).remove();
		                    }
							Parrot par = (Parrot) p.getWorld().spawnEntity(p.getLocation(), EntityType.PARROT);
		                    par.setTamed(true);
		                    par.setOwner(p);
		                    par.setAdult();
		                    par.setBreed(false);
		                    par.setAgeLock(true);
							par.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(par.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()*(1+tsd.Pets.get(p.getUniqueId())*0.05)*(1+tsd.Taming.get(p.getUniqueId())*0.1));
							par.getAttribute(Attribute.GENERIC_FLYING_SPEED).setBaseValue(par.getAttribute(Attribute.GENERIC_FLYING_SPEED).getBaseValue()*(1+tsd.Taming.get(p.getUniqueId())*0.01));
							par.setHealth(par.getMaxHealth());
							par.setMetadata("pet of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
							par.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), true));
		                    par.setCustomName(p.getName());
		                    par.setSilent(true);
		                    wolfm.put(p, w);
		                    catm.put(p,cat);
		                    parm.put(p, par);
		                    tamed.put(p, w);
		                    tamed.put(p, cat);
		                    tamed.put(p, par);
							cdcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
	                    if(wolfm.containsKey(p)) {
	                    	wolfm.get(p).remove();
	                    }
	                    Wolf w = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
	                    w.setTamed(true);
	                    w.setOwner(p);
	                    w.setAdult();
	                    w.setBreed(false);
	                    w.setAgeLock(true);
						w.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
						w.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(w.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()*(1+tsd.Pets.get(p.getUniqueId())*0.05)*(1+tsd.Taming.get(p.getUniqueId())*0.1));
						w.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(w.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue()*(1+tsd.Taming.get(p.getUniqueId())*0.015));
						w.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(w.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*(2+tsd.Pets.get(p.getUniqueId())*0.055)*(1+tsd.Taming.get(p.getUniqueId())*0.045));
						w.setHealth(w.getMaxHealth());
						w.setMetadata("pet of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
						w.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), true));
	                    w.setCustomName(p.getName());
	                    w.setSilent(true);
	                    if(catm.containsKey(p)) {
	                    	catm.get(p).remove();
	                    }
	                    Cat cat = (Cat) p.getWorld().spawnEntity(p.getLocation(), EntityType.CAT);
	                    cat.setTamed(true);
	                    cat.setOwner(p);
	                    cat.setAdult();
	                    cat.setBreed(false);
	                    cat.setAgeLock(true);
						cat.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(cat.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()*(1+tsd.Pets.get(p.getUniqueId())*0.05)*(1+tsd.Taming.get(p.getUniqueId())*0.1));
						cat.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(cat.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue()*(1+tsd.Taming.get(p.getUniqueId())*0.01));
						cat.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(cat.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*(2+tsd.Pets.get(p.getUniqueId())*0.03)*(1+tsd.Taming.get(p.getUniqueId())*0.035));
						cat.setHealth(cat.getMaxHealth());
						cat.setMetadata("pet of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
						cat.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), true));
	                    cat.setCustomName(p.getName());
	                    cat.setSilent(true);
	                    if(parm.containsKey(p)) {
	                    	parm.get(p).remove();
	                    }
						Parrot par = (Parrot) p.getWorld().spawnEntity(p.getLocation(), EntityType.PARROT);
	                    par.setTamed(true);
	                    par.setOwner(p);
	                    par.setAdult();
	                    par.setBreed(false);
	                    par.setAgeLock(true);
						par.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(par.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()*(1+tsd.Pets.get(p.getUniqueId())*0.05)*(1+tsd.Taming.get(p.getUniqueId())*0.1));
						par.getAttribute(Attribute.GENERIC_FLYING_SPEED).setBaseValue(par.getAttribute(Attribute.GENERIC_FLYING_SPEED).getBaseValue()*(1+tsd.Taming.get(p.getUniqueId())*0.01));
						par.setHealth(par.getMaxHealth());
						par.setMetadata("pet of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
						par.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), true));
	                    par.setCustomName(p.getName());
	                    par.setSilent(true);
	                    wolfm.put(p, w);
	                    catm.put(p,cat);
	                    parm.put(p, par);
	                    tamed.put(p, w);
	                    tamed.put(p, cat);
	                    tamed.put(p, par);
		                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
		}
	
	}
	
	@EventHandler
	public void BeeHive(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 12;

        
		
		
		if(playerclass.get(p.getUniqueId()) == 9) {
		if(!(p.isSneaking())&& !p.isOnGround() && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
			{
				ev.setCancelled(true);
				
				if(frcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (frcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use BeeHive").create());
	                }
	                else // if timer is done
	                {
	                    frcooldown.remove(p.getName()); // removing player from HashMap
	                    ArrayList<Location> line = new ArrayList<Location>();
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
	                    Location i = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, i, 3, 0.2,0.2,0.2,0 ,Material.BEEHIVE.createBlockData());
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, i, 3, 0.2,0.2,0.2,0 ,Material.BEE_NEST.createBlockData());
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, i, 3, 0.2,0.2,0.2,0 ,Material.HONEYCOMB_BLOCK.createBlockData());
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2.0f);
	                    for(int b =0; b<6; b++) {
							Bee bees = (Bee) p.getWorld().spawnEntity(i, EntityType.BEE);
							bees.setAdult();
							bees.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(bees.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*(1+tsd.BeeHive.get(p.getUniqueId())*0.05)*(1+tsd.Taming.get(p.getUniqueId())*0.03));
							bees.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(bees.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()*(1+tsd.BeeHive.get(p.getUniqueId())*0.05)*(1+tsd.Taming.get(p.getUniqueId())*0.1));
							bees.getAttribute(Attribute.GENERIC_FLYING_SPEED).setBaseValue(bees.getAttribute(Attribute.GENERIC_FLYING_SPEED).getBaseValue()*(1+tsd.Taming.get(p.getUniqueId())*0.01));
							bees.setHealth(bees.getMaxHealth());
							bees.setMetadata("bees of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
							bees.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), true));	
							bees.setAnger(300);
							bees.setSilent(true);
							bees.setCustomName(p.getName());                  	
		                    tamed.put(p, bees);
		                    if(attention.containsKey(p)) {
		                    	bees.setTarget(attention.get(p));
		                    }
	                    }     
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	             		@Override
    			                	public void run() 
    				                {	
    			                    	p.getWorld().getEntities().stream().filter(b -> b.hasMetadata("bees of " + p.getName())).forEach(b -> b.remove());
    					            }
    		                	   }, 220); 
						frcooldown.put(p.getName(), System.currentTimeMillis()); 
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	ArrayList<Location> line = new ArrayList<Location>();
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
                    AtomicInteger j = new AtomicInteger(0);
                    Location i = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, i, 3, 0.2,0.2,0.2,0 ,Material.BEEHIVE.createBlockData());
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, i, 3, 0.2,0.2,0.2,0 ,Material.BEE_NEST.createBlockData());
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, i, 3, 0.2,0.2,0.2,0 ,Material.HONEYCOMB_BLOCK.createBlockData());
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2.0f);
                    for(int b =0; b<6; b++) {
						Bee bees = (Bee) p.getWorld().spawnEntity(i, EntityType.BEE);
						bees.setAdult();
						bees.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(bees.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*(1+tsd.BeeHive.get(p.getUniqueId())*0.05)*(1+tsd.Taming.get(p.getUniqueId())*0.03));
						bees.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(bees.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()*(1+tsd.BeeHive.get(p.getUniqueId())*0.05)*(1+tsd.Taming.get(p.getUniqueId())*0.1));
						bees.getAttribute(Attribute.GENERIC_FLYING_SPEED).setBaseValue(bees.getAttribute(Attribute.GENERIC_FLYING_SPEED).getBaseValue()*(1+tsd.Taming.get(p.getUniqueId())*0.01));
						bees.setHealth(bees.getMaxHealth());
						bees.setMetadata("bees of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
						bees.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), true));	
						bees.setAnger(300);
						bees.setSilent(true);
						bees.setCustomName(p.getName());                    	
	                    tamed.put(p, bees);
	                    if(attention.containsKey(p)) {
	                    	bees.setTarget(attention.get(p));
	                    	bees.attack(attention.get(p));
	                    	bees.setTarget(attention.get(p));
	                    }
                    }     
                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
			                	public void run() 
				                {	
			                    	p.getWorld().getEntities().stream().filter(b -> b.hasMetadata("bees of " + p.getName())).forEach(b -> b.remove());
					            }
		                	   }, 220); 
					frcooldown.put(p.getName(), System.currentTimeMillis());
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	
	@EventHandler
	public void ThornSuit(EntityDamageByEntityEvent d) 
	{
		 
		 
		 
			if(d.getEntity() instanceof Player&& d.getDamager() instanceof LivingEntity) 
			{
			Player p = (Player)d.getEntity();
			LivingEntity le = (LivingEntity) d.getDamager();
			if(le==p) {d.setCancelled(true);}
			PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
			if(PartyData.hasParty(p))	{
			PartyData.getMembers(PartyData.getParty(p)).filter(m -> playerclass.get(Bukkit.getPlayer(m).getUniqueId())==9).forEach(p1 ->{
			

				if(tamed.containsEntry(p1, le)) {
					d.setCancelled(true);
				}
			});
			}
			if(playerclass.get(p.getUniqueId()) == 9) {
					if(tamed.containsEntry(p, le)) {
						d.setCancelled(true);
					}
					else {
						if(tamed.containsKey(p)) {
							for(Mob m : tamed.get(p)) {
								m.setTarget(le);
							}
						}
					}
				}	
			}
			
			if(d.getEntity() instanceof Panda&& d.getDamager() instanceof LivingEntity) 
			{
			Panda pd = (Panda)d.getEntity();
			LivingEntity le = (LivingEntity) d.getDamager();
			if(tamed.containsEntry(pd.getUniqueId(), Bukkit.getPlayer(pd.getName()).getUniqueId())) {
				Player p = Bukkit.getPlayer(pd.getName());
					if(tamed.containsEntry(p, le)) {
						d.setCancelled(true);
					}
					else {
						if(tamed.containsKey(p)) {
							d.setDamage(d.getDamage()*0.01);
							for(Mob m : tamed.get(p)) {
								m.setTarget(le);
							}
						}
					}
						
				}	
			}	
			if(d.getEntity() instanceof Panda&& d.getDamager() instanceof Projectile) 
			{
			Panda pd = (Panda)d.getEntity();
			Projectile pr = (Projectile) d.getDamager();
			if(pr.getShooter() instanceof LivingEntity) {
				LivingEntity le = (LivingEntity) pr.getShooter();
				if(tamed.containsEntry(pd.getUniqueId(), Bukkit.getPlayer(pd.getName()).getUniqueId())) {
					Player p = Bukkit.getPlayer(pd.getName());
						if(tamed.containsEntry(p, le)) {
							d.setCancelled(true);
						}
						else {
							if(tamed.containsKey(p)) {
								d.setDamage(d.getDamage()*0.01);
								for(Mob m : tamed.get(p)) {
									m.setTarget(le);
								}
							}
						}
							
					}
			}	
			}	
			if(d.getEntity() instanceof Player&& d.getDamager() instanceof Projectile) 
			{
			Player p = (Player)d.getEntity();
			Projectile pr = (Projectile) d.getDamager();
				if(pr.getShooter() instanceof LivingEntity) {
					LivingEntity le = (LivingEntity) pr.getShooter();
					if(le==p) {d.setCancelled(true);}
					if(PartyData.hasParty(p))	{
						PartyData.getMembers(PartyData.getParty(p)).filter(m -> playerclass.get(Bukkit.getPlayer(m).getUniqueId())==9).forEach(p1 ->{
						
								if(tamed.containsEntry(p1, le)) {
									d.setCancelled(true);
								}
						});
					}
					if(playerclass.get(p.getUniqueId()) == 9) {
						if(tamed.containsEntry(p, le)) {
							d.setCancelled(true);
						}
						else {
							if(tamed.containsKey(p)) {
								for(Mob m : tamed.get(p)) {
									m.setTarget(le);
								}
							}
						}
								
					}
				}	
			}	
			
			if(d.getDamager() instanceof Projectile&& d.getEntity() instanceof LivingEntity) 
			{
			Projectile pr = (Projectile)d.getDamager();
			if(pr.getShooter() instanceof Player) {
				Player p = (Player) pr.getShooter();
				LivingEntity le = (LivingEntity) d.getEntity();
				if(PartyData.hasParty(p))	{
				PartyData.getMembers(PartyData.getParty(p)).filter(m -> playerclass.get(Bukkit.getPlayer(m).getUniqueId())==9).forEach(p1 ->{
				

					if(tamed.containsEntry(p1, le)) {
						d.setCancelled(true);
					}
				});
				}
			
				if(playerclass.get(p.getUniqueId()) == 9) {
				
					
						if(tamed.containsEntry(p, le)) {
							d.setCancelled(true);
						}
						else {
							if(tamed.containsKey(p)) {
								for(Mob m : tamed.get(p)) {
									m.setTarget(le);
								}
							}
						}
							
					}	
			}
			}	
	}

	@EventHandler
	public void Attention(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();

        
		
		
		if(PartyData.hasParty(p))	{
		PartyData.getMembers(PartyData.getParty(p)).filter(m -> playerclass.get(Bukkit.getPlayer(m).getUniqueId())==9).forEach(p1 ->{
		
				if(le.hasMetadata("bees of " + p1)) {
					d.setCancelled(true);
				}
				else if(le.hasMetadata("cs of " + p1)) {
					d.setCancelled(true);
				}
				else if(le.hasMetadata("pet of " + p1)) {
					d.setCancelled(true);
				}
				else if(le.hasMetadata("cp of " + p1)) {
					d.setCancelled(true);
				}
				else if(le.hasMetadata("pd of " + p1)) {
					d.setCancelled(true);
				}
				else if(le.hasMetadata("ig of " + p1)) {
					d.setCancelled(true);
				}
		});
		}
		if(playerclass.get(p.getUniqueId()) == 9) {

			if(le.hasMetadata("bees of " + p.getName())) {
				d.setCancelled(true);
			}
			else if(le.hasMetadata("cs of " + p.getName())) {
				d.setCancelled(true);
			}
			else if(le.hasMetadata("pet of " + p.getName())) {
				d.setCancelled(true);
			}
			else if(le.hasMetadata("cp of " + p.getName())) {
				d.setCancelled(true);
			}
			else if(le.hasMetadata("pd of " + p.getName())) {
				d.setCancelled(true);
			}
			else if(le.hasMetadata("ig of " + p.getName())) {
				d.setCancelled(true);
			}
					
				}
		
		}
	}
	
	@EventHandler
	public void Friends(EntityDamageByEntityEvent d) 
	{
		
		
		if(d.getEntity() instanceof LivingEntity && d.getDamager() instanceof LivingEntity && !(d.getDamager() instanceof Player)) 
		{
			LivingEntity c = (LivingEntity)d.getEntity();
			LivingEntity p = (LivingEntity)d.getDamager();
			try {
				if(p.getCustomName().equals(c.getCustomName())) {
					d.setCancelled(true);
				}
				if(PartyData.hasParty(Bukkit.getPlayer(p.getCustomName()))&&PartyData.hasParty(Bukkit.getPlayer(c.getCustomName()))){
					if(PartyData.getParty(Bukkit.getPlayer(p.getCustomName()))==PartyData.getParty(Bukkit.getPlayer(c.getCustomName()))) {
						d.setCancelled(true);
					}
				}
			}
			catch(NullPointerException ne) {return;}
			if(p.hasMetadata("untargetable")) {
				if(c instanceof EnderDragon) {
                    Arrow firstarrow = Bukkit.getPlayer(p.getCustomName()).launchProjectile(Arrow.class);
                    firstarrow.setDamage(0);
                    firstarrow.remove();
					Arrow enar = (Arrow) p.getWorld().spawn(c.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
						ar.setShooter(Bukkit.getPlayer(p.getCustomName()));
						ar.setCritical(false);
						ar.setSilent(true);
						ar.setPickupStatus(PickupStatus.DISALLOWED);
						ar.setVelocity(c.getLocation().clone().add(0, -1, 0).toVector().subtract(c.getLocation().toVector()).normalize().multiply(2.6));
					});
					enar.setDamage(d.getDamage()*1.2*(1+player_damage.get(p.getCustomName())*0.055));								
				}
				c.damage(0, Bukkit.getPlayer(p.getCustomName()));
				c.damage(d.getDamage()*1.2*(1+player_damage.get(p.getCustomName())*0.055), Bukkit.getPlayer(p.getCustomName()));
				d.setCancelled(true);
			}
		}
		if(d.getEntity() instanceof EnderDragonPart && d.getDamager() instanceof LivingEntity && !(d.getDamager() instanceof Player)) 
		{
			EnderDragonPart c = (EnderDragonPart)d.getEntity();
			LivingEntity p = (LivingEntity)d.getDamager();
			if(p.hasMetadata("untargetable")) {
				c.damage(0, Bukkit.getPlayer(p.getCustomName()));
				c.damage(d.getDamage()*1.2*(1+player_damage.get(p.getCustomName())*0.05), Bukkit.getPlayer(p.getCustomName()));
				d.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void Friends(EntityTargetEvent d) 
	{
		if(d.getEntity() instanceof Creeper && d.getTarget() instanceof Player) 
		{
			Creeper c = (Creeper)d.getEntity();
			Player p = (Player)d.getTarget();
			if(c.hasMetadata("cp of " + p.getName())){
				if(attention.containsKey(p)) {
					c.setTarget(attention.get(p));
				}
				else {
				d.setCancelled(true);
				}
			}
		}		
		if(d.getEntity() instanceof CaveSpider && d.getTarget() instanceof Player) 
		{
			CaveSpider c = (CaveSpider)d.getEntity();
			Player p = (Player)d.getTarget();
			if(c.hasMetadata("cs of " + p.getName())){
				if(attention.containsKey(p)) {
					c.setTarget(attention.get(p));
				}
				else {
				d.setCancelled(true);
				}
			}
		}	
		if(d.getEntity() instanceof Bee && d.getTarget() instanceof Player) 
		{
			Bee c = (Bee)d.getEntity();
			Player p = (Player)d.getTarget();
			if(c.hasMetadata("bees of " + p.getName())){
				if(attention.containsKey(p)) {
					c.setTarget(attention.get(p));
				}
				else {
				d.setCancelled(true);
				}
			}
		}		
		if(d.getEntity() instanceof Panda && d.getTarget() instanceof Player) 
		{
			Panda c = (Panda)d.getEntity();
			Player p = (Player)d.getTarget();
			if(c.hasMetadata("pd of " + p.getName())){
				if(attention.containsKey(p)) {
					c.setTarget(attention.get(p));
				}
				else {
					d.setCancelled(true);
				}
			}
		}				
		if(d.getEntity() instanceof IronGolem && d.getTarget() instanceof Player) 
		{
			IronGolem c = (IronGolem)d.getEntity();
			Player p = (Player)d.getTarget();
			if(c.hasMetadata("ig of " + p.getName())){
				if(attention.containsKey(p)) {
					c.setTarget(attention.get(p));
				}
				else {
				d.setCancelled(true);
				}
			}
		}	
		if(d.getEntity() instanceof LivingEntity && d.getTarget() instanceof LivingEntity && !(d.getTarget() instanceof Player)) 
		{
			LivingEntity c = (LivingEntity)d.getEntity();
			LivingEntity p = (LivingEntity)d.getTarget();
			try {
				if(p.getCustomName().equals(c.getCustomName())) {
					d.setCancelled(true);
				}
			}
			catch(NullPointerException ne) {
				if(p.getName().equals(c.getName())) {
					d.setCancelled(true);
				}
			}
		}	
	}

	@EventHandler
	public void Targeting(EntityTargetEvent d) 
	{
		if(d.getEntity() instanceof Mob && (d.getTarget() instanceof Panda)) 
		{
			Mob c = (Mob)d.getEntity();
			Panda pd = (Panda)d.getTarget();
			if(pandao.containsKey(pd.getUniqueId())) {
				Player p = Bukkit.getPlayer(pandao.get(pd.getUniqueId()));
				if(tamed.containsKey(p) && playerclass.get(p.getUniqueId()) == 9) {
					tamed.get(p).forEach(t->t.setTarget(c));
				}
				
			}
		}
	}
	@EventHandler
	public void delete(EntityDeathEvent d) 
	{
		if(d.getEntity() instanceof Player) {
			Player p = (Player) d.getEntity();
			if(tamed.containsKey(p)) {
				for(LivingEntity le : tamed.get(p)) {
					le.remove();
				}
				tamed.clear();
			}
        	if(pandat.containsKey(p.getUniqueId())) {
            	Bukkit.getScheduler().cancelTask(pandat.get(p.getUniqueId()));
        	}
        	pandat.remove(p.getUniqueId());
		}
	}
	@EventHandler
	public void delete(PlayerQuitEvent d) 
	{
		Player p = d.getPlayer();
		if(tamed.containsKey(p)) {
			for(LivingEntity le : tamed.get(p)) {
				le.remove();
			}
			tamed.clear();
		}
    	if(pandat.containsKey(p.getUniqueId())) {
        	Bukkit.getScheduler().cancelTask(pandat.get(p.getUniqueId()));
    	}
    	pandat.remove(p.getUniqueId());
	}
	
	@EventHandler
	public void Creepey(ExplosionPrimeEvent d) 
	{
		Entity e = d.getEntity();
		
		
		if(e instanceof Creeper) {
			Creeper c = (Creeper)e;
			if(c.hasMetadata("untargetable")) {
				d.setRadius(0);
				Player p = Bukkit.getPlayer(c.getCustomName());
				c.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, c.getLocation(), 2);
				for(Entity n : c.getNearbyEntities(6, 6, 6)) {
					if(n instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
						LivingEntity le = (LivingEntity)n;
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
							enar.setDamage(player_damage.get(p.getName())*0.85*(1+tsd.CreepBomb.get(creeper.get(c).getUniqueId())*0.08)*(1+tsd.Taming.get(p.getUniqueId())*0.02));								
						}
						le.damage(0,p);
						le.damage(player_damage.get(p.getName())*0.85*(1+tsd.CreepBomb.get(creeper.get(c).getUniqueId())*0.08)*(1+tsd.Taming.get(p.getUniqueId())*0.02), p);
					}
				}
				tamed.remove(p, c);
			}
		}
	}
	
	@EventHandler
	public void CreepBomb(EntityDamageByEntityEvent d) 
	{
		int sec = 7;
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
			
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
		
		
		
		if(playerclass.get(p.getUniqueId()) == 9) {
		if(p.getAttackCooldown()==1) 
			{	
				if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.isSneaking()) && !(p.isSprinting()))
					{
					if(gdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            long timer = (gdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use CreepBomb").create());
				        }
			            else // if timer is done
			            {
			                gdcooldown.remove(p.getName()); // removing player from HashMap
			                p.swingOffHand();
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
		                    Creeper cp = (Creeper) p.getWorld().spawnEntity(le.getLocation(), EntityType.CREEPER);
		                    cp.setPowered(true);
		                    cp.setMaxFuseTicks(3);
		                    creeper.put(cp, p);
		                    cp.setCustomName(p.getName());
							cp.setMetadata("cp of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
							cp.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), true));
							tamed.put(p, cp);
		                    if(attention.containsKey(p)) {
		                    	cp.setTarget(attention.get(p));
		                    }
		                    else {
			                    cp.setTarget(le);		                    	
		                    }
				            gdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
			        	p.swingOffHand();
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
	                    Creeper cp = (Creeper) p.getWorld().spawnEntity(le.getLocation(), EntityType.CREEPER);
	                    cp.setPowered(true);
	                    cp.setMaxFuseTicks(3);
	                    creeper.put(cp, p);
	                    cp.setCustomName(p.getName());
						cp.setMetadata("cp of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
						cp.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), true));
						tamed.put(p, cp);
	                    if(attention.containsKey(p)) {
	                    	cp.setTarget(attention.get(p));
	                    }
	                    else {
		                    cp.setTarget(le);		                    	
	                    }
			            gdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			        }
					}
			}
		}}
	}

	@EventHandler
	public void PandaSweep(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action a = ev.getAction();
		int sec = 10;
        
		
		
		
		if(playerclass.get(p.getUniqueId()) == 9) {	
			if((a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK)&&(a!= Action.RIGHT_CLICK_AIR)&&(a!= Action.RIGHT_CLICK_AIR)&& !p.isSneaking() && !p.isSprinting()  && !p.isOnGround())
			{	
				if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
				{
					ev.setCancelled(true);
					if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            long timer = (smcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use PandaSweep").create());
				        }
			            else // if timer is done
			            {
			                smcooldown.remove(p.getName()); // removing player from HashMap
			                if(pandam.containsKey(p)) {
			                	pandam.get(p).remove();
			                	if(pandat.containsKey(p.getUniqueId())) {
				                	Bukkit.getScheduler().cancelTask(pandat.get(p.getUniqueId()));
			                	}
			                	pandat.remove(p.getUniqueId());
			                }
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
		                    Panda pd = (Panda) p.getWorld().spawnEntity(p.getLocation(), EntityType.PANDA);
							pd.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
							pd.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(pd.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()*(10+tsd.PandaSweep.get(p.getUniqueId())*10)*(1+tsd.Taming.get(p.getUniqueId())));
							pd.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(pd.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue()*(1+tsd.Taming.get(p.getUniqueId())*0.01));
							pd.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(pd.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*(1+tsd.PandaSweep.get(p.getUniqueId())*0.07)*(1+tsd.Taming.get(p.getUniqueId())*0.065));
		                    pd.setAdult();
		                    pd.setBreed(false);
		                    pd.setCustomName(p.getName());
							pd.setMetadata("pd of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
							pd.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), true));
							pd.setSilent(true);
							pandam.put(p, pd);
							pandao.put(pd.getUniqueId(), p.getUniqueId());
							tamed.put(p, pd);			
		                    pd.setMainGene(Gene.AGGRESSIVE);

		                    int task = 
                    		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
    	             			@Override
			                	public void run() 
				                {	
    			                    for(Entity e : pd.getNearbyEntities(10, 10, 10)) {
    			        				if(PartyData.hasParty(Bukkit.getPlayer(p.getName()))&&PartyData.hasParty(Bukkit.getPlayer(e.getName()))){
    			        					if(PartyData.getParty(Bukkit.getPlayer(p.getName()))==PartyData.getParty(Bukkit.getPlayer(e.getName()))) {
    			        						continue;
    			        					}
    			        				}
    			                    	if(e instanceof Mob && e.getName() != pd.getName()) {
    			                    		Mob le = (Mob)e;
    			                    		if(!tamed.containsEntry(p, le)) {
        			                    		le.setTarget(pd);
    			                    		}
    			                    	}
    			                    }
					            }
                    		}, 10,10); 
		                    pandat.put(p.getUniqueId(), task);
				            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
		                if(pandam.containsKey(p)) {
		                	pandam.get(p).remove();
		                	if(pandat.containsKey(p.getUniqueId())) {
			                	Bukkit.getScheduler().cancelTask(pandat.get(p.getUniqueId()));
		                	}
		                	pandat.remove(p.getUniqueId());
		                }
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
	                    Panda pd = (Panda) p.getWorld().spawnEntity(p.getLocation(), EntityType.PANDA);
						pd.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
						pd.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(pd.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()*(10+tsd.PandaSweep.get(p.getUniqueId())*10)*(1+tsd.Taming.get(p.getUniqueId())));
						pd.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(pd.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue()*(1+tsd.Taming.get(p.getUniqueId())*0.01));
						pd.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(pd.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*(1+tsd.PandaSweep.get(p.getUniqueId())*0.07)*(1+tsd.Taming.get(p.getUniqueId())*0.065));
	                    pd.setAdult();
	                    pd.setBreed(false);
	                    pd.setCustomName(p.getName());
						pd.setMetadata("pd of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
						pd.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), true));
						pd.setSilent(true);
						pandam.put(p, pd);
						pandao.put(pd.getUniqueId(), p.getUniqueId());
						tamed.put(p, pd);			
	                    pd.setMainGene(Gene.AGGRESSIVE);
	                    
	                    int task = 
                		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	             			@Override
		                	public void run() 
			                {	
			                    for(Entity e : pd.getNearbyEntities(10, 10, 10)) {
			        				if(PartyData.hasParty(Bukkit.getPlayer(p.getName()))&&PartyData.hasParty(Bukkit.getPlayer(e.getName()))){
			        					if(PartyData.getParty(Bukkit.getPlayer(p.getName()))==PartyData.getParty(Bukkit.getPlayer(e.getName()))) {
			        						continue;
			        					}
			        				}
			                    	if(e instanceof Mob && e.getName() != pd.getName()) {
			                    		Mob le = (Mob)e;
			                    		if(!tamed.containsEntry(p, le)) {
    			                    		le.setTarget(pd);
			                    		}
			                    	}
			                    }
				            }
                		}, 10,10); 
	                    pandat.put(p.getUniqueId(), task);
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
		Item ii = ev.getItemDrop();
		ItemStack is = ii.getItemStack();
        
		
		
		
		
			if(playerclass.get(p.getUniqueId()) == 9 && (is.getType().name().contains("NUGGET")) && p.isSneaking())
			{
				ev.setCancelled(true);
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (sultcooldown.get(p.getName())/1000 + 65) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use IronGolem").create());
	                }
	                else // if timer is done
	                {
	                    sultcooldown.remove(p.getName()); // removing player from HashMap
	                    Location i = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2.0f);
						IronGolem ig = (IronGolem) p.getWorld().spawnEntity(i, EntityType.IRON_GOLEM);
						ig.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
						ig.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(ig.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()*(1+p.getLevel()*0.25));
						ig.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(ig.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue()*(1+p.getLevel()*0.01));
						ig.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(ig.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*(1+p.getLevel()*0.1));
	                    ig.setPlayerCreated(true);
	                    ig.setHealth(ig.getMaxHealth());
	                    ig.setSilent(true);
						ig.setMetadata("ig of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
						ig.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), true));	
						ig.setCustomName(p.getName());
	                    p.swingMainHand();    		   
	                    ig.setCustomName(p.getName());                 	
	                    tamed.put(p, ig);
	                    for(Entity e : ig.getNearbyEntities(15, 15, 15)) {
	        				if(PartyData.hasParty(Bukkit.getPlayer(p.getName()))&&PartyData.hasParty(Bukkit.getPlayer(e.getName()))){
	        					if(PartyData.getParty(Bukkit.getPlayer(p.getName()))==PartyData.getParty(Bukkit.getPlayer(e.getName()))) {
	        						continue;
	        					}
	        				}
	                    	if(e instanceof Mob && e.getName() != p.getName()) {
	                    		Mob le = (Mob)e;
	                    		le.setTarget(ig);
	                    	}
	                    }
	                    for(Mob m : tamed.get(p)) {
							m.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 500, 0, false, false));
							m.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 500, 0, false, false));
	                    }
	                    if(attention.containsKey(p)) {
	                    	ig.setTarget(attention.get(p));
		                    }
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	             		@Override
    			                	public void run() 
    				                {	
    			                    	p.getWorld().getEntities().stream().filter(b -> b.hasMetadata("ig of " + p.getName())).forEach(b -> b.remove());
    					            }
    		                	   }, 500); 
		                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
                    Location i = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2.0f);
					IronGolem ig = (IronGolem) p.getWorld().spawnEntity(i, EntityType.IRON_GOLEM);
					ig.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
					ig.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(ig.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()*(1+p.getLevel()*0.25));
					ig.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(ig.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue()*(1+p.getLevel()*0.01));
					ig.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(ig.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*(1+p.getLevel()*0.1));
					ig.setPlayerCreated(true);		
                    ig.setHealth(ig.getMaxHealth());	
                    ig.setSilent(true);			
					ig.setMetadata("ig of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
					ig.setMetadata("untargetable", new FixedMetadataValue(RMain.getInstance(), true));	
					ig.setCustomName(p.getName());
                    p.swingMainHand();    		      
                    ig.setCustomName(p.getName());                  	
                    tamed.put(p, ig);
                    for(Entity e : ig.getNearbyEntities(15, 15, 15)) {
        				if(PartyData.hasParty(Bukkit.getPlayer(p.getName()))&&PartyData.hasParty(Bukkit.getPlayer(e.getName()))){
        					if(PartyData.getParty(Bukkit.getPlayer(p.getName()))==PartyData.getParty(Bukkit.getPlayer(e.getName()))) {
        						continue;
        					}
        				}
                    	if(e instanceof Mob && e.getName() != p.getName()) {
                    		Mob le = (Mob)e;
                    		le.setTarget(ig);
                    	}
                    }
                    for(Mob m : tamed.get(p)) {
						m.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 500, 0, false, false));
						m.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 500, 0, false, false));
                    }
                    if(attention.containsKey(p)) {
                    	ig.setTarget(attention.get(p));
	                    }
                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
			                	public void run() 
				                {	
			                    	p.getWorld().getEntities().stream().filter(b -> b.hasMetadata("ig of " + p.getName())).forEach(b -> b.remove());
					            }
		                	   }, 500); 
	                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();
		
		if(playerclass.get(p.getUniqueId()) == 9)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
			{
					e.setCancelled(true);
			}
		}
		
	}
		
	@EventHandler
	public void dyeCancel(SheepDyeWoolEvent e) 
	{
		e.setCancelled(true);
	}

	@EventHandler
	public void Damagegetter(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 9) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
				{
						player_damage.put(p.getName(), 4+ p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
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

	        
			
			
				if(playerclass.get(p.getUniqueId()) == 9) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
					{
							player_damage.put(p.getName(), 4+ p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
			    
				
				
				if(playerclass.get(p.getUniqueId()) == 9) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
					{
							player_damage.put(p.getName(), 4+ p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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



