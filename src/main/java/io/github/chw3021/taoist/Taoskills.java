package io.github.chw3021.taoist;



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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityCategory;
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
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Taoskills implements Listener, Serializable {
	

	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 8481741046360618590L;
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Integer> stance = new HashMap<String, Integer>();
	private HashMap<String, Long> aura1 = new HashMap<String, Long>();
	private HashMap<String, Long> aura2 = new HashMap<String, Long>();
	private HashMap<String, Long> aura3 = new HashMap<String, Long>();
	private HashMap<String, Long> aura4 = new HashMap<String, Long>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private TaoSkillsData tsd = new TaoSkillsData(TaoSkillsData.loadData(path +"/plugins/RPGskills/TaoSkillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		TaoSkillsData t = new TaoSkillsData(TaoSkillsData.loadData(path +"/plugins/RPGskills/TaoSkillsData.data"));
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

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		TaoSkillsData t = new TaoSkillsData(TaoSkillsData.loadData(path +"/plugins/RPGskills/TaoSkillsData.data"));
		tsd = t;
		
	}
	
	@EventHandler
	public void StanceChange(PlayerSwapHandItemsEvent ev) 
	{
	    
		Player p = ev.getPlayer();
		
		
		if(playerclass.get(p.getUniqueId()) == 10)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && !(p.isSneaking()))
			{
				ev.setCancelled(true);
                    try {
    	        		switch (stance.get(p.getName()))
    					{
    						case 0:
    							stance.replace(p.getName(), 1);
    							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("BlueDragon Stance").color(ChatColor.AQUA).create());
    							break;
    						case 1:
    							stance.replace(p.getName(), 2);
    							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("RedBird Stance").color(ChatColor.RED).create());
    							break;
    						case 2:
    							stance.replace(p.getName(), 3);
    							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("WhiteTiger Stance").color(ChatColor.WHITE).create());
    							break;
    						case 3:
    							stance.replace(p.getName(), 0);
    							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("BlackTortoise Stance").color(ChatColor.GRAY).create());
    							break;
    					}
    	        	}
    	        	catch(NullPointerException e) {
    	        		stance.put(p.getName(), 0);
    	        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("BlackTortoise Stance").color(ChatColor.GRAY).create());
    	        		ev.setCancelled(true);
    	        	}
	              
	            
			}}
		}
		
	@EventHandler
	public void StanceManagement(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
			Player p = (Player) d.getDamager();
		LivingEntity e = (LivingEntity)d.getEntity();
			Location el =e.getLocation();
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 10)			
			{
				
					if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
					{
						try {
						if(stance.get(p.getName()) == 0)
						{
							if (e instanceof Player) 
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
							else {
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el, 5, 1,1,1,0 ,Material.ICE.createBlockData());
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el, 5, 1,1,1,0 ,Material.FROSTED_ICE.createBlockData());
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el, 5, 1,1,1,0 ,Material.CRYING_OBSIDIAN.createBlockData());
							e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 0, false, false));
							e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20, 0, false, false));
								if(e.getType() == EntityType.BLAZE || e.getType() == EntityType.ENDERMAN || e.getType() == EntityType.GHAST || e.getType() == EntityType.MAGMA_CUBE || e.getType() == EntityType.WITCH || e.getType() == EntityType.EVOKER || e.getType() == EntityType.PILLAGER || e.getType() == EntityType.PIGLIN_BRUTE || e.getType() == EntityType.RAVAGER || e.getType() == EntityType.PLAYER)
								{
									d.setDamage(d.getDamage()*(1+tsd.StanceChange.get(p.getUniqueId())*0.6));
								}
								else
								{
									d.setDamage(d.getDamage()*(1));
								}
							}
							
						}
						if(stance.get(p.getName()) == 1)
						{
							if (e instanceof Player) 
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
							else {
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el, 5, 1,1,1,0 ,Material.STRIPPED_WARPED_STEM.createBlockData());
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el, 5, 1,1,1,0 ,Material.SPRUCE_LEAVES.createBlockData());
								p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
								d.setDamage(d.getDamage()*(1));
							}
							
						}
						if(stance.get(p.getName()) == 2)
						{
							if (e instanceof Player) 
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
							else {
								p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, el, 5, 1, 1, 1);
							p.getWorld().spawnParticle(Particle.FLAME, el, 5, 1, 1, 1, 0);
							e.setFireTicks(40);
								if(e.getType().name().contains("SPIDER") || e.getType().name().contains("SILVER") || e.getType().name().contains("GUARDIAN") || e.getType() == EntityType.BAT || e.getType() == EntityType.BEE  || e.getType() == EntityType.ENDERMITE)
								{
									d.setDamage(d.getDamage()*(1+tsd.StanceChange.get(p.getUniqueId())*0.6));
								}
								else
								{
									d.setDamage(d.getDamage()*(1));
								}
							}
							
						}
						if(stance.get(p.getName()) == 3)
						{
							if (e instanceof Player) 
							{
								
								Player p1 = (Player) e;
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
									{
										return;
									}
								}
							}
							else {
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el, 5, 1,1,1,0 ,Material.IRON_BLOCK.createBlockData());
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el, 5, 1,1,1,0 ,Material.GOLD_BLOCK.createBlockData());
								e.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
								if(e.getCategory().equals(EntityCategory.UNDEAD))
								{
									d.setCancelled(false);
									d.setDamage(d.getDamage()*(1+tsd.StanceChange.get(p.getUniqueId())*0.6));
								}
							
								else
								{
									d.setDamage(d.getDamage()*(1));
								}
							}
						}
						}
						catch(NullPointerException e1)
						{
							d.setDamage(d.getDamage()*(1));
							p.sendMessage("You should Choose Stance First");
						}
					}
			}
			}
		
	}
	@EventHandler
	public void Aura(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 6;

	    
		
		
		if(playerclass.get(p.getUniqueId()) == 10) {
		if((p.isSneaking())&& (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&&(ac!= Action.LEFT_CLICK_AIR)&&(ac!= Action.LEFT_CLICK_AIR))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
			{
				

				ev.setCancelled(true);
				if(stance.containsKey(p.getName())) {
				if(swcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (swcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Aura").create());
	                }
	                else // if timer is done
	                {
	                    swcooldown.remove(p.getName()); // removing player from HashMap
	                	Location l = p.getLocation();
	                	int auratime = 30 + tsd.Aura.get(p.getUniqueId())*10;
	                    p.playSound(l, Sound.BLOCK_BEACON_AMBIENT, 1f, 2f);
	                    p.playSound(l, Sound.BLOCK_BEACON_POWER_SELECT, 1f, 0f);
	                    if(stance.get(p.getName()) == 0)
						{
							p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 300, 6,6,6,2 ,Material.GRAY_GLAZED_TERRACOTTA.createBlockData());
							p.getWorld().spawnParticle(Particle.TOWN_AURA, l, 300, 6,6,6,0);
							if(aura1.containsKey(p.getName())) {
								if((aura1.get(p.getName())/1000 + auratime) - System.currentTimeMillis()/1000 < 0) {
									aura1.replace(p.getName(), System.currentTimeMillis());
									if(p.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, p.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getAmplifier()+2, false, false));
									}
									else {
										p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, 2, false, false));
									}
								}
								else {
									if(p.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, p.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getAmplifier(), false, false));
									}
									else {
										p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, 2, false, false));
									}									
								}														
							}
							else {
								if(p.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, p.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getAmplifier()+2, false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, 2, false, false));
								}
								aura1.put(p.getName(), System.currentTimeMillis());
							}
						}
	                    if(stance.get(p.getName()) == 1)
						{
							p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 300, 6,6,6,2 ,Material.BLUE_GLAZED_TERRACOTTA.createBlockData());
							p.getWorld().spawnParticle(Particle.TOWN_AURA, l, 300, 6,6,6,0);
							if(aura2.containsKey(p.getName())) {
								if((aura2.get(p.getName())/1000 + auratime) - System.currentTimeMillis()/1000 < 0) {
									aura2.replace(p.getName(), System.currentTimeMillis());
									if(p.hasPotionEffect(PotionEffectType.ABSORPTION)) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, p.getPotionEffect(PotionEffectType.ABSORPTION).getAmplifier()+1, false, false));
									}
									else {
										p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, 1, false, false));
									}
									if(p.hasPotionEffect(PotionEffectType.REGENERATION)) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, p.getPotionEffect(PotionEffectType.REGENERATION).getAmplifier()+1, false, false));
									}
									else {
										p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, 1, false, false));
									}
								}
								else {
									if(p.hasPotionEffect(PotionEffectType.ABSORPTION)) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, p.getPotionEffect(PotionEffectType.ABSORPTION).getAmplifier(), false, false));
									}
									else {
										p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, 1, false, false));
									}
									if(p.hasPotionEffect(PotionEffectType.REGENERATION)) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, p.getPotionEffect(PotionEffectType.REGENERATION).getAmplifier(), false, false));
									}
									else {
										p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, 1, false, false));
									}								
								}														
							}
							else {
								if(p.hasPotionEffect(PotionEffectType.ABSORPTION)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, p.getPotionEffect(PotionEffectType.ABSORPTION).getAmplifier()+1, false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, 1, false, false));
								}
								if(p.hasPotionEffect(PotionEffectType.REGENERATION)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, p.getPotionEffect(PotionEffectType.REGENERATION).getAmplifier()+1, false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, 1, false, false));
								}
								aura2.put(p.getName(), System.currentTimeMillis());
							}
						}
						if(stance.get(p.getName()) == 2)
						{
							p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 300, 6,6,6,2 ,Material.RED_GLAZED_TERRACOTTA.createBlockData());
							p.getWorld().spawnParticle(Particle.TOWN_AURA, l, 300, 6,6,6,0);
							if(aura3.containsKey(p.getName())) {
								if((aura3.get(p.getName())/1000 + auratime) - System.currentTimeMillis()/1000 < 0) {
									aura3.replace(p.getName(), System.currentTimeMillis());
									if(p.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, p.getPotionEffect(PotionEffectType.FIRE_RESISTANCE).getAmplifier()+1, false, false));
									}
									else {
										p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, 1, false, false));
									}
									if(p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()+1, false, false));
									}
									else {
										p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, 1, false, false));
									}
								}
								else {
									if(p.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, p.getPotionEffect(PotionEffectType.FIRE_RESISTANCE).getAmplifier(), false, false));
									}
									else {
										p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, 1, false, false));
									}
									if(p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier(), false, false));
									}
									else {
										p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, 1, false, false));
									}								
								}														
							}
							else {
								if(p.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, p.getPotionEffect(PotionEffectType.FIRE_RESISTANCE).getAmplifier()+1, false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, 1, false, false));
								}
								if(p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()+1, false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, 1, false, false));
								}
								aura3.put(p.getName(), System.currentTimeMillis());
							}
						}
						if(stance.get(p.getName()) == 3)
						{
							p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 300, 6,6,6,2 ,Material.WHITE_GLAZED_TERRACOTTA.createBlockData());
							p.getWorld().spawnParticle(Particle.TOWN_AURA, l, 300, 6,6,6,0);
							if(aura4.containsKey(p.getName())) {
								if((aura4.get(p.getName())/1000 + auratime) - System.currentTimeMillis()/1000 < 0) {
									aura4.replace(p.getName(), System.currentTimeMillis());
									if(p.hasPotionEffect(PotionEffectType.SPEED)) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, p.getPotionEffect(PotionEffectType.SPEED).getAmplifier()+1, false, false));
									}
									else {
										p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, 1, false, false));
									}
									if(p.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, p.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier()+1, false, false));
									}
									else {
										p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, 1, false, false));
									}
									if(p.hasPotionEffect(PotionEffectType.JUMP)) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, p.getPotionEffect(PotionEffectType.JUMP).getAmplifier()+1, false, false));
									}
									else {
										p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, 1, false, false));
									}
								}
								else {
									if(p.hasPotionEffect(PotionEffectType.SPEED)) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, p.getPotionEffect(PotionEffectType.SPEED).getAmplifier(), false, false));
									}
									else {
										p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, 1, false, false));
									}
									if(p.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, p.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier(), false, false));
									}
									else {
										p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, 1, false, false));
									}
									if(p.hasPotionEffect(PotionEffectType.JUMP)) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, p.getPotionEffect(PotionEffectType.JUMP).getAmplifier(), false, false));
									}
									else {
										p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, 1, false, false));
									}						
								}														
							}
							else {
								if(p.hasPotionEffect(PotionEffectType.SPEED)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, p.getPotionEffect(PotionEffectType.SPEED).getAmplifier()+1, false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, 1, false, false));
								}
								if(p.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, p.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier()+1, false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, 1, false, false));
								}
								if(p.hasPotionEffect(PotionEffectType.JUMP)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, p.getPotionEffect(PotionEffectType.JUMP).getAmplifier()+1, false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, 1, false, false));
								}
								aura4.put(p.getName(), System.currentTimeMillis());
							}
							
						}
	                	for (Entity e : p.getNearbyEntities(6, 6, 6))
						{
                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)e;
									{
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
											enar.setDamage(player_damage.get(p.getName())*0.45 + tsd.Aura.get(p.getUniqueId())*0.3);								
										}
										p.setSprinting(true);
										le.damage(0, p);
										le.damage(player_damage.get(p.getName())*0.45 + tsd.Aura.get(p.getUniqueId())*0.3, p);
										
										p.getWorld().spawnParticle(Particle.ASH, e.getLocation(), 20, 1, 1, 1);
										p.setSprinting(false);
											
									}
									if(stance.get(p.getName()) == 0)
									{
										if (le instanceof Player) 
										{
											
											Player p1 = (Player) le;
											if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
											if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
												{
												if(aura1.containsKey(p1.getName())) {
													if((aura1.get(p1.getName())/1000 + auratime) - System.currentTimeMillis()/1000 < 0) {
														aura1.replace(p1.getName(), System.currentTimeMillis());
														if(p1.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, p1.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getAmplifier()+2, false, false));
														}
														else {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, 2, false, false));
														}
													}
													else {
														if(p1.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, p1.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getAmplifier(), false, false));
														}
														else {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, 2, false, false));
														}									
													}														
												}
												else {
													if(p1.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, p1.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getAmplifier()+2, false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, 2, false, false));
													}
													aura1.put(p1.getName(), System.currentTimeMillis());
												}
												}
											}
										}
										
									}
									if(stance.get(p.getName()) == 1)
									{
										if (le instanceof Player) 
										{
											
											Player p1 = (Player) le;
											if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
											if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
												{
												if(aura2.containsKey(p1.getName())) {
													if((aura2.get(p1.getName())/1000 + auratime) - System.currentTimeMillis()/1000 < 0) {
														aura2.replace(p1.getName(), System.currentTimeMillis());
														if(p1.hasPotionEffect(PotionEffectType.ABSORPTION)) {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, p1.getPotionEffect(PotionEffectType.ABSORPTION).getAmplifier()+1, false, false));
														}
														else {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, 1, false, false));
														}
														if(p1.hasPotionEffect(PotionEffectType.REGENERATION)) {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, p1.getPotionEffect(PotionEffectType.REGENERATION).getAmplifier()+1, false, false));
														}
														else {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, 1, false, false));
														}
													}
													else {
														if(p1.hasPotionEffect(PotionEffectType.ABSORPTION)) {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, p1.getPotionEffect(PotionEffectType.ABSORPTION).getAmplifier(), false, false));
														}
														else {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, 1, false, false));
														}
														if(p1.hasPotionEffect(PotionEffectType.REGENERATION)) {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, p1.getPotionEffect(PotionEffectType.REGENERATION).getAmplifier(), false, false));
														}
														else {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, 1, false, false));
														}								
													}														
												}
												else {
													if(p1.hasPotionEffect(PotionEffectType.ABSORPTION)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, p1.getPotionEffect(PotionEffectType.ABSORPTION).getAmplifier()+1, false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, 1, false, false));
													}
													if(p1.hasPotionEffect(PotionEffectType.REGENERATION)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, p1.getPotionEffect(PotionEffectType.REGENERATION).getAmplifier()+1, false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, 1, false, false));
													}
													aura2.put(p1.getName(), System.currentTimeMillis());
												}
												}
											}
										}
										
									}
									if(stance.get(p.getName()) == 2)
									{
										if (le instanceof Player) 
										{
											
											Player p1 = (Player) le;
											if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
											if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
												{
												if(aura3.containsKey(p1.getName())) {
													if((aura3.get(p1.getName())/1000 + auratime) - System.currentTimeMillis()/1000 < 0) {
														aura3.replace(p1.getName(), System.currentTimeMillis());
														if(p1.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, p1.getPotionEffect(PotionEffectType.FIRE_RESISTANCE).getAmplifier()+1, false, false));
														}
														else {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, 1, false, false));
														}
														if(p1.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, p1.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()+1, false, false));
														}
														else {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, 1, false, false));
														}
													}
													else {
														if(p1.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, p1.getPotionEffect(PotionEffectType.FIRE_RESISTANCE).getAmplifier(), false, false));
														}
														else {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, 1, false, false));
														}
														if(p1.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, p1.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier(), false, false));
														}
														else {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, 1, false, false));
														}								
													}														
												}
												else {
													if(p1.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, p1.getPotionEffect(PotionEffectType.FIRE_RESISTANCE).getAmplifier()+1, false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, 1, false, false));
													}
													if(p1.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, p1.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()+1, false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, 1, false, false));
													}
													aura3.put(p1.getName(), System.currentTimeMillis());
												}
												}
											}
										}
										
									}
									if(stance.get(p.getName()) == 3)
									{
										if (le instanceof Player) 
										{
											
											Player p1 = (Player) le;
											if(PartyData.hasParty(p) && PartyData.hasParty(p1) && p1!=p)	{
											if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
												{
												if(aura4.containsKey(p1.getName())) {
													if((aura4.get(p1.getName())/1000 + auratime) - System.currentTimeMillis()/1000 < 0) {
														aura4.replace(p1.getName(), System.currentTimeMillis());
														if(p1.hasPotionEffect(PotionEffectType.SPEED)) {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, p1.getPotionEffect(PotionEffectType.SPEED).getAmplifier()+1, false, false));
														}
														else {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, 1, false, false));
														}
														if(p1.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, p1.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier()+1, false, false));
														}
														else {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, 1, false, false));
														}
														if(p1.hasPotionEffect(PotionEffectType.JUMP)) {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, p1.getPotionEffect(PotionEffectType.JUMP).getAmplifier()+1, false, false));
														}
														else {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, 1, false, false));
														}
													}
													else {
														if(p1.hasPotionEffect(PotionEffectType.SPEED)) {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, p1.getPotionEffect(PotionEffectType.SPEED).getAmplifier(), false, false));
														}
														else {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, 1, false, false));
														}
														if(p1.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, p1.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier(), false, false));
														}
														else {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, 1, false, false));
														}
														if(p1.hasPotionEffect(PotionEffectType.JUMP)) {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, p1.getPotionEffect(PotionEffectType.JUMP).getAmplifier(), false, false));
														}
														else {
															p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, 1, false, false));
														}						
													}														
												}
												else {
													if(p1.hasPotionEffect(PotionEffectType.SPEED)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, p1.getPotionEffect(PotionEffectType.SPEED).getAmplifier()+1, false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, 1, false, false));
													}
													if(p1.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, p1.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier()+1, false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, 1, false, false));
													}
													if(p1.hasPotionEffect(PotionEffectType.JUMP)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, p1.getPotionEffect(PotionEffectType.JUMP).getAmplifier()+1, false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, 1, false, false));
													}
													aura4.put(p1.getName(), System.currentTimeMillis());
												}
												}
											}
										}
									}
							}
						}
					swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		                
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	Location l = p.getLocation();
                	int auratime = 30 + tsd.Aura.get(p.getUniqueId())*10;
                    p.playSound(l, Sound.BLOCK_BEACON_AMBIENT, 1f, 2f);
                    p.playSound(l, Sound.BLOCK_BEACON_POWER_SELECT, 1f, 0f);
                    if(stance.get(p.getName()) == 0)
					{
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 300, 6,6,6,2 ,Material.GRAY_GLAZED_TERRACOTTA.createBlockData());
						p.getWorld().spawnParticle(Particle.TOWN_AURA, l, 300, 6,6,6,0);
						if(aura1.containsKey(p.getName())) {
							if((aura1.get(p.getName())/1000 + auratime) - System.currentTimeMillis()/1000 < 0) {
								aura1.replace(p.getName(), System.currentTimeMillis());
								if(p.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, p.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getAmplifier()+2, false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, 2, false, false));
								}
							}
							else {
								if(p.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, p.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getAmplifier(), false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, 2, false, false));
								}									
							}														
						}
						else {
							if(p.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, p.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getAmplifier()+2, false, false));
							}
							else {
								p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, 2, false, false));
							}
							aura1.put(p.getName(), System.currentTimeMillis());
						}
					}
                    if(stance.get(p.getName()) == 1)
					{
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 300, 6,6,6,2 ,Material.BLUE_GLAZED_TERRACOTTA.createBlockData());
						p.getWorld().spawnParticle(Particle.TOWN_AURA, l, 300, 6,6,6,0);
						if(aura2.containsKey(p.getName())) {
							if((aura2.get(p.getName())/1000 + auratime) - System.currentTimeMillis()/1000 < 0) {
								aura2.replace(p.getName(), System.currentTimeMillis());
								if(p.hasPotionEffect(PotionEffectType.ABSORPTION)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, p.getPotionEffect(PotionEffectType.ABSORPTION).getAmplifier()+1, false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, 1, false, false));
								}
								if(p.hasPotionEffect(PotionEffectType.REGENERATION)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, p.getPotionEffect(PotionEffectType.REGENERATION).getAmplifier()+1, false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, 1, false, false));
								}
							}
							else {
								if(p.hasPotionEffect(PotionEffectType.ABSORPTION)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, p.getPotionEffect(PotionEffectType.ABSORPTION).getAmplifier(), false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, 1, false, false));
								}
								if(p.hasPotionEffect(PotionEffectType.REGENERATION)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, p.getPotionEffect(PotionEffectType.REGENERATION).getAmplifier(), false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, 1, false, false));
								}								
							}														
						}
						else {
							if(p.hasPotionEffect(PotionEffectType.ABSORPTION)) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, p.getPotionEffect(PotionEffectType.ABSORPTION).getAmplifier()+1, false, false));
							}
							else {
								p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, 1, false, false));
							}
							if(p.hasPotionEffect(PotionEffectType.REGENERATION)) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, p.getPotionEffect(PotionEffectType.REGENERATION).getAmplifier()+1, false, false));
							}
							else {
								p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, 1, false, false));
							}
							aura2.put(p.getName(), System.currentTimeMillis());
						}
					}
					if(stance.get(p.getName()) == 2)
					{
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 300, 6,6,6,2 ,Material.RED_GLAZED_TERRACOTTA.createBlockData());
						p.getWorld().spawnParticle(Particle.TOWN_AURA, l, 300, 6,6,6,0);
						if(aura3.containsKey(p.getName())) {
							if((aura3.get(p.getName())/1000 + auratime) - System.currentTimeMillis()/1000 < 0) {
								aura3.replace(p.getName(), System.currentTimeMillis());
								if(p.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, p.getPotionEffect(PotionEffectType.FIRE_RESISTANCE).getAmplifier()+1, false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, 1, false, false));
								}
								if(p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()+1, false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, 1, false, false));
								}
							}
							else {
								if(p.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, p.getPotionEffect(PotionEffectType.FIRE_RESISTANCE).getAmplifier(), false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, 1, false, false));
								}
								if(p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier(), false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, 1, false, false));
								}								
							}														
						}
						else {
							if(p.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, p.getPotionEffect(PotionEffectType.FIRE_RESISTANCE).getAmplifier()+1, false, false));
							}
							else {
								p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, 1, false, false));
							}
							if(p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()+1, false, false));
							}
							else {
								p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, 1, false, false));
							}
							aura3.put(p.getName(), System.currentTimeMillis());
						}
					}
					if(stance.get(p.getName()) == 3)
					{
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 300, 6,6,6,2 ,Material.WHITE_GLAZED_TERRACOTTA.createBlockData());
						p.getWorld().spawnParticle(Particle.TOWN_AURA, l, 300, 6,6,6,0);
						if(aura4.containsKey(p.getName())) {
							if((aura4.get(p.getName())/1000 + auratime) - System.currentTimeMillis()/1000 < 0) {
								aura4.replace(p.getName(), System.currentTimeMillis());
								if(p.hasPotionEffect(PotionEffectType.SPEED)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, p.getPotionEffect(PotionEffectType.SPEED).getAmplifier()+1, false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, 1, false, false));
								}
								if(p.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, p.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier()+1, false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, 1, false, false));
								}
								if(p.hasPotionEffect(PotionEffectType.JUMP)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, p.getPotionEffect(PotionEffectType.JUMP).getAmplifier()+1, false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, 1, false, false));
								}
							}
							else {
								if(p.hasPotionEffect(PotionEffectType.SPEED)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, p.getPotionEffect(PotionEffectType.SPEED).getAmplifier(), false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, 1, false, false));
								}
								if(p.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, p.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier(), false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, 1, false, false));
								}
								if(p.hasPotionEffect(PotionEffectType.JUMP)) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, p.getPotionEffect(PotionEffectType.JUMP).getAmplifier(), false, false));
								}
								else {
									p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, 1, false, false));
								}						
							}														
						}
						else {
							if(p.hasPotionEffect(PotionEffectType.SPEED)) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, p.getPotionEffect(PotionEffectType.SPEED).getAmplifier()+1, false, false));
							}
							else {
								p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, 1, false, false));
							}
							if(p.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, p.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier()+1, false, false));
							}
							else {
								p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, 1, false, false));
							}
							if(p.hasPotionEffect(PotionEffectType.JUMP)) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, p.getPotionEffect(PotionEffectType.JUMP).getAmplifier()+1, false, false));
							}
							else {
								p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, 1, false, false));
							}
							aura4.put(p.getName(), System.currentTimeMillis());
						}
						
					}
                	for (Entity e : p.getNearbyEntities(6, 6, 6))
					{
                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
						{
							LivingEntity le = (LivingEntity)e;
								{
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
										enar.setDamage(player_damage.get(p.getName())*0.45 + tsd.Aura.get(p.getUniqueId())*0.3);								
									}
									p.setSprinting(true);
									le.damage(0, p);
									le.damage(player_damage.get(p.getName())*0.45 + tsd.Aura.get(p.getUniqueId())*0.3, p);
									
									p.getWorld().spawnParticle(Particle.ASH, e.getLocation(), 20, 1, 1, 1);
									p.setSprinting(false);
										
								}
								if(stance.get(p.getName()) == 0)
								{
									if (le instanceof Player) 
									{
										
										Player p1 = (Player) le;
										if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
											{
											if(aura1.containsKey(p1.getName())) {
												if((aura1.get(p1.getName())/1000 + auratime) - System.currentTimeMillis()/1000 < 0) {
													aura1.replace(p1.getName(), System.currentTimeMillis());
													if(p1.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, p1.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getAmplifier()+2, false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, 2, false, false));
													}
												}
												else {
													if(p1.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, p1.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getAmplifier(), false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, 2, false, false));
													}									
												}														
											}
											else {
												if(p1.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
													p1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, p1.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getAmplifier()+2, false, false));
												}
												else {
													p1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, auratime, 2, false, false));
												}
												aura1.put(p1.getName(), System.currentTimeMillis());
											}
											}
										}
									}
									
								}
								if(stance.get(p.getName()) == 1)
								{
									if (le instanceof Player) 
									{
										
										Player p1 = (Player) le;
										if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
											{
											if(aura2.containsKey(p1.getName())) {
												if((aura2.get(p1.getName())/1000 + auratime) - System.currentTimeMillis()/1000 < 0) {
													aura2.replace(p1.getName(), System.currentTimeMillis());
													if(p1.hasPotionEffect(PotionEffectType.ABSORPTION)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, p1.getPotionEffect(PotionEffectType.ABSORPTION).getAmplifier()+1, false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, 1, false, false));
													}
													if(p1.hasPotionEffect(PotionEffectType.REGENERATION)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, p1.getPotionEffect(PotionEffectType.REGENERATION).getAmplifier()+1, false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, 1, false, false));
													}
												}
												else {
													if(p1.hasPotionEffect(PotionEffectType.ABSORPTION)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, p1.getPotionEffect(PotionEffectType.ABSORPTION).getAmplifier(), false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, 1, false, false));
													}
													if(p1.hasPotionEffect(PotionEffectType.REGENERATION)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, p1.getPotionEffect(PotionEffectType.REGENERATION).getAmplifier(), false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, 1, false, false));
													}								
												}														
											}
											else {
												if(p1.hasPotionEffect(PotionEffectType.ABSORPTION)) {
													p1.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, p1.getPotionEffect(PotionEffectType.ABSORPTION).getAmplifier()+1, false, false));
												}
												else {
													p1.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, auratime, 1, false, false));
												}
												if(p1.hasPotionEffect(PotionEffectType.REGENERATION)) {
													p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, p1.getPotionEffect(PotionEffectType.REGENERATION).getAmplifier()+1, false, false));
												}
												else {
													p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, auratime, 1, false, false));
												}
												aura2.put(p1.getName(), System.currentTimeMillis());
											}
											}
										}
									}
									
								}
								if(stance.get(p.getName()) == 2)
								{
									if (le instanceof Player) 
									{
										
										Player p1 = (Player) le;
										if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
											{
											if(aura3.containsKey(p1.getName())) {
												if((aura3.get(p1.getName())/1000 + auratime) - System.currentTimeMillis()/1000 < 0) {
													aura3.replace(p1.getName(), System.currentTimeMillis());
													if(p1.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, p1.getPotionEffect(PotionEffectType.FIRE_RESISTANCE).getAmplifier()+1, false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, 1, false, false));
													}
													if(p1.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, p1.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()+1, false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, 1, false, false));
													}
												}
												else {
													if(p1.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, p1.getPotionEffect(PotionEffectType.FIRE_RESISTANCE).getAmplifier(), false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, 1, false, false));
													}
													if(p1.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, p1.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier(), false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, 1, false, false));
													}								
												}														
											}
											else {
												if(p1.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
													p1.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, p1.getPotionEffect(PotionEffectType.FIRE_RESISTANCE).getAmplifier()+1, false, false));
												}
												else {
													p1.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, auratime, 1, false, false));
												}
												if(p1.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
													p1.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, p1.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()+1, false, false));
												}
												else {
													p1.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, auratime, 1, false, false));
												}
												aura3.put(p1.getName(), System.currentTimeMillis());
											}
											}
										}
									}
									
								}
								if(stance.get(p.getName()) == 3)
								{
									if (le instanceof Player) 
									{
										
										Player p1 = (Player) le;
										if(PartyData.hasParty(p) && PartyData.hasParty(p1) && p1!=p)	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
											{
											if(aura4.containsKey(p1.getName())) {
												if((aura4.get(p1.getName())/1000 + auratime) - System.currentTimeMillis()/1000 < 0) {
													aura4.replace(p1.getName(), System.currentTimeMillis());
													if(p1.hasPotionEffect(PotionEffectType.SPEED)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, p1.getPotionEffect(PotionEffectType.SPEED).getAmplifier()+1, false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, 1, false, false));
													}
													if(p1.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, p1.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier()+1, false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, 1, false, false));
													}
													if(p1.hasPotionEffect(PotionEffectType.JUMP)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, p1.getPotionEffect(PotionEffectType.JUMP).getAmplifier()+1, false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, 1, false, false));
													}
												}
												else {
													if(p1.hasPotionEffect(PotionEffectType.SPEED)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, p1.getPotionEffect(PotionEffectType.SPEED).getAmplifier(), false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, 1, false, false));
													}
													if(p1.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, p1.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier(), false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, 1, false, false));
													}
													if(p1.hasPotionEffect(PotionEffectType.JUMP)) {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, p1.getPotionEffect(PotionEffectType.JUMP).getAmplifier(), false, false));
													}
													else {
														p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, 1, false, false));
													}						
												}														
											}
											else {
												if(p1.hasPotionEffect(PotionEffectType.SPEED)) {
													p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, p1.getPotionEffect(PotionEffectType.SPEED).getAmplifier()+1, false, false));
												}
												else {
													p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, auratime, 1, false, false));
												}
												if(p1.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
													p1.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, p1.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier()+1, false, false));
												}
												else {
													p1.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, auratime, 1, false, false));
												}
												if(p1.hasPotionEffect(PotionEffectType.JUMP)) {
													p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, p1.getPotionEffect(PotionEffectType.JUMP).getAmplifier()+1, false, false));
												}
												else {
													p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, auratime, 1, false, false));
												}
												aura4.put(p1.getName(), System.currentTimeMillis());
											}
											}
										}
									}
								}
						}
					}
	                swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	                
	            }
				}
				else {p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You should choose stance first").create());}
			}
		}
		}
	}

	@EventHandler
	public void Charm(PlayerSwapHandItemsEvent ev) 
	{
	    
		Player p = ev.getPlayer();
		int sec = 12;

		
		
		if(playerclass.get(p.getUniqueId()) == 10) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && p.isSneaking())
			{
				ev.setCancelled(true);
				if(stance.containsKey(p.getName())) {
				
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (cdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Charm").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap
		                    p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0f, 2f);
		                    Location pl = p.getLocation();
		                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 6).getLocation();
							ItemStack TortoiseCharm = new ItemStack(Material.GRAY_BANNER, 1);									
							ItemStack DragonCharm = new ItemStack(Material.BLUE_BANNER, 1); 
							ItemStack BirdCharm = new ItemStack(Material.RED_BANNER, 1); 
							ItemStack TigerCharm = new ItemStack(Material.WHITE_BANNER, 1); 
		                    if(stance.get(p.getName()) == 0)
							{ 
								Item charm = p.getWorld().dropItem(p.getLocation(), TortoiseCharm);
								charm.setPickupDelay(5000);
								charm.setInvulnerable(true);
								charm.setThrower(p.getUniqueId());
								charm.teleport(l);
								charm.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								charm.setGlowing(true);
							}
		                    if(stance.get(p.getName()) == 1)
							{
								Item charm = p.getWorld().dropItem(p.getLocation(), DragonCharm);
								charm.setPickupDelay(5000);
								charm.setInvulnerable(true);
								charm.setThrower(p.getUniqueId());
								charm.teleport(l);
								charm.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								charm.setGlowing(true);
							}
							if(stance.get(p.getName()) == 2)
							{
								Item charm = p.getWorld().dropItem(p.getLocation(), BirdCharm);
								charm.setPickupDelay(5000);
								charm.setInvulnerable(true);
								charm.setThrower(p.getUniqueId());
								charm.teleport(l);
								charm.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								charm.setGlowing(true);
							}
							if(stance.get(p.getName()) == 3)
							{
								Item charm = p.getWorld().dropItem(p.getLocation(), TigerCharm);
								charm.setPickupDelay(5000);
								charm.setInvulnerable(true);
								charm.setThrower(p.getUniqueId());
								charm.teleport(l);
								charm.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								charm.setGlowing(true);
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
													if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
													if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
														{
														continue;
														}
													}
												}
					                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
												{
													LivingEntity le = (LivingEntity)e;
														{
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
																enar.setDamage(player_damage.get(p.getName())*0.16+tsd.Charm.get(p.getUniqueId())*0.05);								
															}
															p.setSprinting(true);
										                    le.teleport(l);
										                    le.damage(0,p);
										                    le.damage(player_damage.get(p.getName())*0.16+tsd.Charm.get(p.getUniqueId())*0.05,p);
															p.setSprinting(false);
																
														}
												}
											}
						                }
			                	   }, i*10); 
							}
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	Stream<Entity> charms = p.getWorld().getEntities().stream().filter(i -> i.hasMetadata("charm of"+p.getName()));
				                	charms.forEach(i -> i.remove());
				                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
					        		p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, l, 1,1,1,1);
				                	for (Entity e : p.getWorld().getNearbyEntities(l, 4, 4, 4))
									{
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
			                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
										{
											LivingEntity le = (LivingEntity)e;
												{
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
														enar.setDamage(player_damage.get(p.getName())*0.6+tsd.Charm.get(p.getUniqueId())*0.4);								
													}
													p.setSprinting(true);
								                    le.damage(0,p);
								                    le.damage(player_damage.get(p.getName())*0.6+tsd.Charm.get(p.getUniqueId())*0.4,p);
								                    le.playEffect(EntityEffect.HURT_EXPLOSION);
								                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())*0.8+0.5));
													p.setSprinting(false);
														
												}
										}
									}
				                }
	                	   }, 70); 
							cdcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0f, 2f);
	                    Location pl = p.getLocation();
	                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 6).getLocation();
						ItemStack TortoiseCharm = new ItemStack(Material.GRAY_BANNER, 1);									
						ItemStack DragonCharm = new ItemStack(Material.BLUE_BANNER, 1); 
						ItemStack BirdCharm = new ItemStack(Material.RED_BANNER, 1); 
						ItemStack TigerCharm = new ItemStack(Material.WHITE_BANNER, 1); 
	                    if(stance.get(p.getName()) == 0)
						{ 
							Item charm = p.getWorld().dropItem(p.getLocation(), TortoiseCharm);
							charm.setPickupDelay(5000);
							charm.setInvulnerable(true);
							charm.setThrower(p.getUniqueId());
							charm.teleport(l);
							charm.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							charm.setGlowing(true);
						}
	                    if(stance.get(p.getName()) == 1)
						{
							Item charm = p.getWorld().dropItem(p.getLocation(), DragonCharm);
							charm.setPickupDelay(5000);
							charm.setInvulnerable(true);
							charm.setThrower(p.getUniqueId());
							charm.teleport(l);
							charm.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							charm.setGlowing(true);
						}
						if(stance.get(p.getName()) == 2)
						{
							Item charm = p.getWorld().dropItem(p.getLocation(), BirdCharm);
							charm.setPickupDelay(5000);
							charm.setInvulnerable(true);
							charm.setThrower(p.getUniqueId());
							charm.teleport(l);
							charm.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							charm.setGlowing(true);
						}
						if(stance.get(p.getName()) == 3)
						{
							Item charm = p.getWorld().dropItem(p.getLocation(), TigerCharm);
							charm.setPickupDelay(5000);
							charm.setInvulnerable(true);
							charm.setThrower(p.getUniqueId());
							charm.teleport(l);
							charm.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							charm.setGlowing(true);
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
												if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
												if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
													{
													continue;
													}
												}
											}
				                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
											{
												LivingEntity le = (LivingEntity)e;
													{
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
															enar.setDamage(player_damage.get(p.getName())*0.16+tsd.Charm.get(p.getUniqueId())*0.05);								
														}
														p.setSprinting(true);
									                    le.teleport(l);
									                    le.damage(0,p);
									                    le.damage(player_damage.get(p.getName())*0.16+tsd.Charm.get(p.getUniqueId())*0.05,p);
														p.setSprinting(false);
															
													}
											}
										}
					                }
		                	   }, i*10); 
						}
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	Stream<Entity> charms = p.getWorld().getEntities().stream().filter(i -> i.hasMetadata("charm of"+p.getName()));
			                	charms.forEach(i -> i.remove());
			                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
				        		p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, l, 1,1,1,1);
			                	for (Entity e : p.getWorld().getNearbyEntities(l, 4, 4, 4))
								{
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
		                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
									{
										LivingEntity le = (LivingEntity)e;
											{
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
													enar.setDamage(player_damage.get(p.getName())*0.6+tsd.Charm.get(p.getUniqueId())*0.4);								
												}
												p.setSprinting(true);
							                    le.damage(0,p);
							                    le.damage(player_damage.get(p.getName())*0.6+tsd.Charm.get(p.getUniqueId())*0.4,p);
							                    le.playEffect(EntityEffect.HURT_EXPLOSION);
							                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())*0.8+0.5));
												p.setSprinting(false);
													
											}
									}
								}
			                }
                    	}, 70); 
		                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
				else {p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You should choose stance first").create());}
				}
		}
	
	}
	
	@EventHandler
	public void Wave(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 3;

	    
		
		
		if(playerclass.get(p.getUniqueId()) == 10) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
			{
				if(stance.containsKey(p.getName())) {
				
				ev.setCancelled(true);
				if(frcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (frcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Wave").create());
	                }
	                else // if timer is done
	                {
	                    frcooldown.remove(p.getName()); // removing player from HashMap
	                    ArrayList<Location> line = new ArrayList<Location>();
	                    p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_3, 1.0f, 2f);
	                    AtomicInteger j = new AtomicInteger(0);
	                    AtomicInteger a = new AtomicInteger(0);
	                    AtomicInteger b = new AtomicInteger(0);
	                    for(double d = 0.1; d <= 5; d += 0.05) {
		                    Location pl = p.getEyeLocation();
							pl.add(pl.getDirection().normalize().multiply(d));
							line.add(pl);
	                    }
	                    if(line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().isPresent()) {
		                    Location block =line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().get();
		                    for(int k=0; k<line.indexOf(block)-1; k++) {
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
			             			a.incrementAndGet();
			             			if(stance.get(p.getName()) == 0)
									{ 
										p.getWorld().spawnParticle(Particle.BLOCK_CRACK, line.get(b.incrementAndGet()), 4, 0.1,0.1,0.1,0 ,Material.GRAY_STAINED_GLASS.createBlockData());
									}
				                    if(stance.get(p.getName()) == 1)
									{
										p.getWorld().spawnParticle(Particle.BLOCK_CRACK, line.get(b.incrementAndGet()), 4, 0.1,0.1,0.1,0 ,Material.BLUE_STAINED_GLASS.createBlockData());
									}
									if(stance.get(p.getName()) == 2)
									{
										p.getWorld().spawnParticle(Particle.BLOCK_CRACK, line.get(b.incrementAndGet()), 4, 0.1,0.1,0.1,0 ,Material.RED_STAINED_GLASS.createBlockData());
									}
									if(stance.get(p.getName()) == 3)
									{
										p.getWorld().spawnParticle(Particle.BLOCK_CRACK, line.get(b.incrementAndGet()), 4, 0.1,0.1,0.1,0 ,Material.WHITE_STAINED_GLASS.createBlockData());
									}
					                    	for (Entity e : p.getNearbyEntities(2, 2, 2))
											{
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
					                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
												{
													LivingEntity le = (LivingEntity)e;									
									             	le.teleport(line.get(b.get()));		
												}
											}
							            }
				                	   }, j.incrementAndGet()/20); 
								 }}
	                    else {
		                    	line.forEach(i -> {
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {			b.getAndIncrement();
			             			if(stance.get(p.getName()) == 0)
									{ 
										p.getWorld().spawnParticle(Particle.BLOCK_CRACK, i, 4, 0.1,0.1,0.1,0 ,Material.GRAY_STAINED_GLASS.createBlockData());
									}
				                    if(stance.get(p.getName()) == 1)
									{
										p.getWorld().spawnParticle(Particle.BLOCK_CRACK, i, 4, 0.1,0.1,0.1,0 ,Material.BLUE_STAINED_GLASS.createBlockData());
									}
									if(stance.get(p.getName()) == 2)
									{
										p.getWorld().spawnParticle(Particle.BLOCK_CRACK, i, 4, 0.1,0.1,0.1,0 ,Material.RED_STAINED_GLASS.createBlockData());
									}
									if(stance.get(p.getName()) == 3)
									{
										p.getWorld().spawnParticle(Particle.BLOCK_CRACK, i, 4, 0.1,0.1,0.1,0 ,Material.WHITE_STAINED_GLASS.createBlockData());
									}
			             			for (Entity e : p.getNearbyEntities(2, 2, 2))
									{
			                    		if (e instanceof Player) 
										{
											
											Player p1 = (Player) e;
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
			                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
										{
											LivingEntity le = (LivingEntity)e;											
							             	le.teleport(i);
										}
									}
							            }
				                	   }, j.incrementAndGet()/20); 
								}); 
	                    	}
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {		
								p.getWorld().spawnParticle(Particle.FLASH, line.get(line.size()-1), 1, 1,1,1);
		             			if(stance.get(p.getName()) == 0)
								{ 
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, line.get(line.size()-1), 50, 1,1,1,1 ,Material.GRAY_GLAZED_TERRACOTTA.createBlockData());
								}
			                    if(stance.get(p.getName()) == 1)
								{
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, line.get(line.size()-1), 50, 1,1,1,1 ,Material.BLUE_GLAZED_TERRACOTTA.createBlockData());
								}
								if(stance.get(p.getName()) == 2)
								{
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, line.get(line.size()-1), 50, 1,1,1,1 ,Material.RED_GLAZED_TERRACOTTA.createBlockData());
								}
								if(stance.get(p.getName()) == 3)
								{
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, line.get(line.size()-1), 50, 1,1,1,1 ,Material.WHITE_GLAZED_TERRACOTTA.createBlockData());
								}
			                	for (Entity e : p.getWorld().getNearbyEntities(line.get(line.size()-1), 3, 3, 3))
								{
		                    		if (e instanceof Player) 
									{
										
										Player p1 = (Player) e;
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
		                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
									{
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
											enar.setDamage(player_damage.get(p.getName())*0.333+tsd.Wave.get(p.getUniqueId())*0.25);								
										}
										p.setSprinting(true);
						        		p.getWorld().spawnParticle(Particle.CRIT, line.get(line.size()-1), 10,1,1,1);
					                    le.damage(0,p);
					                    le.damage(player_damage.get(p.getName())*0.333+tsd.Wave.get(p.getUniqueId())*0.25,p);
					                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())/2+1));
										p.setSprinting(false);
									}
								}
			                }
	            	   }, j.incrementAndGet()/20+5); 
						frcooldown.put(p.getName(), System.currentTimeMillis()); 
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	ArrayList<Location> line = new ArrayList<Location>();
                    p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_3, 1.0f, 2f);
                    AtomicInteger j = new AtomicInteger(0);
                    AtomicInteger a = new AtomicInteger(0);
                    AtomicInteger b = new AtomicInteger(0);
                    for(double d = 0.1; d <= 5; d += 0.05) {
	                    Location pl = p.getEyeLocation();
						pl.add(pl.getDirection().normalize().multiply(d));
						line.add(pl);
                    }
                    if(line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().isPresent()) {
	                    Location block =line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().get();
	                    for(int k=0; k<line.indexOf(block)-1; k++) {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
		             			a.incrementAndGet();
		             			if(stance.get(p.getName()) == 0)
								{ 
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, line.get(b.incrementAndGet()), 4, 0.1,0.1,0.1,0 ,Material.GRAY_STAINED_GLASS.createBlockData());
								}
			                    if(stance.get(p.getName()) == 1)
								{
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, line.get(b.incrementAndGet()), 4, 0.1,0.1,0.1,0 ,Material.BLUE_STAINED_GLASS.createBlockData());
								}
								if(stance.get(p.getName()) == 2)
								{
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, line.get(b.incrementAndGet()), 4, 0.1,0.1,0.1,0 ,Material.RED_STAINED_GLASS.createBlockData());
								}
								if(stance.get(p.getName()) == 3)
								{
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, line.get(b.incrementAndGet()), 4, 0.1,0.1,0.1,0 ,Material.WHITE_STAINED_GLASS.createBlockData());
								}
				                    	for (Entity e : p.getNearbyEntities(2, 2, 2))
										{
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
				                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
											{
												LivingEntity le = (LivingEntity)e;									
								             	le.teleport(line.get(b.get()));		
											}
										}
						            }
			                	   }, j.incrementAndGet()/20); 
							 }}
                    else {
	                    	line.forEach(i -> {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {			b.getAndIncrement();
		             			if(stance.get(p.getName()) == 0)
								{ 
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, i, 4, 0.1,0.1,0.1,0 ,Material.GRAY_STAINED_GLASS.createBlockData());
								}
			                    if(stance.get(p.getName()) == 1)
								{
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, i, 4, 0.1,0.1,0.1,0 ,Material.BLUE_STAINED_GLASS.createBlockData());
								}
								if(stance.get(p.getName()) == 2)
								{
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, i, 4, 0.1,0.1,0.1,0 ,Material.RED_STAINED_GLASS.createBlockData());
								}
								if(stance.get(p.getName()) == 3)
								{
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, i, 4, 0.1,0.1,0.1,0 ,Material.WHITE_STAINED_GLASS.createBlockData());
								}
		             			for (Entity e : p.getNearbyEntities(2, 2, 2))
								{
		                    		if (e instanceof Player) 
									{
										
										Player p1 = (Player) e;
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
		                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
									{
										LivingEntity le = (LivingEntity)e;											
						             	le.teleport(i);
									}
								}
						            }
			                	   }, j.incrementAndGet()/20); 
							}); 
                    	}
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {		
							p.getWorld().spawnParticle(Particle.FLASH, line.get(line.size()-1), 1, 1,1,1);
	             			if(stance.get(p.getName()) == 0)
							{ 
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, line.get(line.size()-1), 50, 1,1,1,1 ,Material.GRAY_GLAZED_TERRACOTTA.createBlockData());
							}
		                    if(stance.get(p.getName()) == 1)
							{
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, line.get(line.size()-1), 50, 1,1,1,1 ,Material.BLUE_GLAZED_TERRACOTTA.createBlockData());
							}
							if(stance.get(p.getName()) == 2)
							{
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, line.get(line.size()-1), 50, 1,1,1,1 ,Material.RED_GLAZED_TERRACOTTA.createBlockData());
							}
							if(stance.get(p.getName()) == 3)
							{
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, line.get(line.size()-1), 50, 1,1,1,1 ,Material.WHITE_GLAZED_TERRACOTTA.createBlockData());
							}
		                	for (Entity e : p.getWorld().getNearbyEntities(line.get(line.size()-1), 3, 3, 3))
							{
	                    		if (e instanceof Player) 
								{
									
									Player p1 = (Player) e;
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
	                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
								{
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
										enar.setDamage(player_damage.get(p.getName())*0.333+tsd.Wave.get(p.getUniqueId())*0.25);								
									}
									p.setSprinting(true);
					        		p.getWorld().spawnParticle(Particle.CRIT, line.get(line.size()-1), 10,1,1,1);
				                    le.damage(0,p);
				                    le.damage(player_damage.get(p.getName())*0.333+tsd.Wave.get(p.getUniqueId())*0.25,p);
				                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())/2+1));
									p.setSprinting(false);
								}
							}
		                }
            	   }, j.incrementAndGet()/20+5); 
					frcooldown.put(p.getName(), System.currentTimeMillis());
				}
	            
			}

				else {p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You should choose stance first").create());}
			}
		}}
	}
	
	@EventHandler
	public void KiVibe(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();

		
		
		if(playerclass.get(p.getUniqueId()) == 10) {
			
				if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
				{
					d.setDamage(d.getDamage()+tsd.Vibe.get(p.getUniqueId())*0.6);
				}
			}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof EnderDragon) 
		{
			Arrow ar = (Arrow)d.getDamager();
			EnderDragon le = (EnderDragon)d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player)ar.getShooter();
				Location el =le.getLocation();
				el.setY(el.getY()+1);

				
				
				if(playerclass.get(p.getUniqueId()) == 10) {
					
						if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
						{
							d.setDamage(d.getDamage()+tsd.Vibe.get(p.getUniqueId())*0.6);
						}
					}
			}
		}
	}
	
	@EventHandler
	public void Flip(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action a = ev.getAction();
		int sec = 10;
        
		
		
		if(playerclass.get(p.getUniqueId()) == 10) {	
			if((a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK)&&(a!= Action.RIGHT_CLICK_AIR)&&(a!= Action.RIGHT_CLICK_AIR)&& !p.isSneaking() && !p.isSprinting()  && !p.isOnGround())
			{	
				if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
				{
					if(stance.containsKey(p.getName())) {
					ev.setCancelled(true);
					

					if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
	                    
		                double timer = ((sdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000); // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + (int)timer + " seconds to use Flip").create());
		                }
		                else // if timer is done
		                {
		                    sdcooldown.remove(p.getName()); // removing player from HashMap		                   
		                    Location tl = p.getLocation();
		                    tl.setDirection(tl.getDirection().normalize().rotateAroundY(Math.PI));
		                    tl.setDirection(tl.getDirection().normalize().rotateAroundZ(Math.PI/3));
		                    tl.add(tl.getDirection().normalize().multiply(4));
		                    tl.setDirection(p.getLocation().getDirection());
		                    if(!tl.getBlock().isPassable())
		                    {tl.add(0, 1, 0);}
		                    if(tl.getBlock().isPassable())
		                    {p.teleport(tl);}
		                    p.playSound(p.getLocation(), Sound.ITEM_ELYTRA_FLYING, 1.0f, 2f);
		                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 60, 3, false, false));
		                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
							ItemStack TortoiseCharm = new ItemStack(Material.GRAY_BANNER, 1);									
							ItemStack DragonCharm = new ItemStack(Material.BLUE_BANNER, 1); 
							ItemStack BirdCharm = new ItemStack(Material.RED_BANNER, 1); 
							ItemStack TigerCharm = new ItemStack(Material.WHITE_BANNER, 1); 
							for(int i=0; i<6; i++) {
			                    p.playSound(p.getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 1.0f, 2f);
		                    if(stance.get(p.getName()) == 0)
								{ 
									Item charm = p.getWorld().dropItemNaturally(l, TortoiseCharm);
									charm.setPickupDelay(5000);
									charm.setInvulnerable(true);
									charm.setThrower(p.getUniqueId());
									charm.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
									charm.setGlowing(true);
								}
			                    if(stance.get(p.getName()) == 1)
								{
									Item charm = p.getWorld().dropItemNaturally(l, DragonCharm);
									charm.setPickupDelay(5000);
									charm.setInvulnerable(true);
									charm.setThrower(p.getUniqueId());
									charm.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
									charm.setGlowing(true);
								}
								if(stance.get(p.getName()) == 2)
								{
									Item charm = p.getWorld().dropItemNaturally(l, BirdCharm);
									charm.setPickupDelay(5000);
									charm.setInvulnerable(true);
									charm.setThrower(p.getUniqueId());
									charm.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
									charm.setGlowing(true);
								}
								if(stance.get(p.getName()) == 3)
								{
									Item charm = p.getWorld().dropItemNaturally(l, TigerCharm);
									charm.setPickupDelay(5000);
									charm.setInvulnerable(true);
									charm.setThrower(p.getUniqueId());
									charm.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
									charm.setGlowing(true);
								}
							}
							for(int i=0; i<6; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                 		@Override
			    		                	public void run() 
			    			                {	Stream<Entity> charms = p.getWorld().getEntities().stream().filter(i -> i.hasMetadata("charm of"+p.getName()));
						                		charms.forEach(i -> i.remove());
							                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 2f);
			                 					p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, l, 2,1,1,1);
			                 					p.getWorld().spawnParticle(Particle.FLASH, l, 20,5,5,5);
			                 					for(Entity e : p.getWorld().getNearbyEntities(l, 5, 5, 5)) {
			                 						if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))) {
			                 							LivingEntity le = (LivingEntity) e;
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
															enar.setDamage(player_damage.get(p.getName())*0.36+tsd.Flip.get(p.getUniqueId())*0.275);								
														}
														p.setSprinting(true);
			                 							le.damage(0, p);
			                 							le.damage(player_damage.get(p.getName())*0.36+tsd.Flip.get(p.getUniqueId())*0.275, p);
														p.setSprinting(false);
			                 						}
			                 					}
			    				            }
			    	                	   }, i*5); 
							}
			                sdcooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	Location tl = p.getLocation();
	                    tl.setDirection(tl.getDirection().normalize().rotateAroundY(Math.PI));
	                    tl.setDirection(tl.getDirection().normalize().rotateAroundZ(Math.PI/3));
	                    tl.add(tl.getDirection().normalize().multiply(4));
	                    tl.setDirection(p.getLocation().getDirection());
	                    if(!tl.getBlock().isPassable())
	                    {tl.add(0, 1, 0);}
	                    if(tl.getBlock().isPassable())
	                    {p.teleport(tl);}
	                    p.playSound(p.getLocation(), Sound.ITEM_ELYTRA_FLYING, 1.0f, 2f);
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 60, 3, false, false));
	                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
						ItemStack TortoiseCharm = new ItemStack(Material.GRAY_BANNER, 1);									
						ItemStack DragonCharm = new ItemStack(Material.BLUE_BANNER, 1); 
						ItemStack BirdCharm = new ItemStack(Material.RED_BANNER, 1); 
						ItemStack TigerCharm = new ItemStack(Material.WHITE_BANNER, 1); 
						for(int i=0; i<6; i++) {
		                    p.playSound(p.getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 1.0f, 2f);
	                    if(stance.get(p.getName()) == 0)
							{ 
								Item charm = p.getWorld().dropItemNaturally(l, TortoiseCharm);
								charm.setPickupDelay(5000);
								charm.setInvulnerable(true);
								charm.setThrower(p.getUniqueId());
								charm.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								charm.setGlowing(true);
							}
		                    if(stance.get(p.getName()) == 1)
							{
								Item charm = p.getWorld().dropItemNaturally(l, DragonCharm);
								charm.setPickupDelay(5000);
								charm.setInvulnerable(true);
								charm.setThrower(p.getUniqueId());
								charm.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								charm.setGlowing(true);
							}
							if(stance.get(p.getName()) == 2)
							{
								Item charm = p.getWorld().dropItemNaturally(l, BirdCharm);
								charm.setPickupDelay(5000);
								charm.setInvulnerable(true);
								charm.setThrower(p.getUniqueId());
								charm.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								charm.setGlowing(true);
							}
							if(stance.get(p.getName()) == 3)
							{
								Item charm = p.getWorld().dropItemNaturally(l, TigerCharm);
								charm.setPickupDelay(5000);
								charm.setInvulnerable(true);
								charm.setThrower(p.getUniqueId());
								charm.setMetadata("charm of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								charm.setGlowing(true);
							}
						}
						for(int i=0; i<6; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                 		@Override
		    		                	public void run() 
		    			                {	Stream<Entity> charms = p.getWorld().getEntities().stream().filter(i -> i.hasMetadata("charm of"+p.getName()));
					                		charms.forEach(i -> i.remove());
						                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 2f);
		                 					p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, l, 2,1,1,1);
		                 					p.getWorld().spawnParticle(Particle.FLASH, l, 20,5,5,5);
		                 					for(Entity e : p.getWorld().getNearbyEntities(l, 5, 5, 5)) {
		                 						if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))) {
		                 							LivingEntity le = (LivingEntity) e;
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
														enar.setDamage(player_damage.get(p.getName())*0.36+tsd.Flip.get(p.getUniqueId())*0.275);								
													}
													p.setSprinting(true);
		                 							le.damage(0, p);
		                 							le.damage(player_damage.get(p.getName())*0.36+tsd.Flip.get(p.getUniqueId())*0.275, p);
													p.setSprinting(false);
		                 						}
		                 					}
		    				            }
		    	                	   }, i*5); 
						}
		                sdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
					else {p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You should choose stance first").create());}
				}
			}
			}
	}
	


	@EventHandler
	public void CombustInside(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 6;
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&& !(d.getEntity().hasMetadata("fake"))) 
		{
		Player p = (Player)d.getDamager();
		final LivingEntity le = (LivingEntity)d.getEntity();
		
		
		Holding hold = Holding.getInstance();
		if(playerclass.get(p.getUniqueId()) == 10)	
		{
				if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && p.isSneaking() && !(p.isSprinting())&&!d.isCancelled())
				{
					if(stance.containsKey(p.getName())) {
						
					if(p.getAttackCooldown()==1) 
					{
						if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
				        {
				            long timer = (smcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
				            if(!(timer < 0)) // if timer is still more then 0 or 0
				            {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use CombustInside").create());
				            }
				            else // if timer is done
				            {
				                smcooldown.remove(p.getName()); // removing player from HashMap
									if (le instanceof Player) 
									{
										
										Player p1 = (Player) le;
										if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
											{
												return;
											}
										}
										else {
											p.playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1.0f, 2.0f);
											p.getWorld().spawnParticle(Particle.ASH, le.getLocation(), 100, 1, 1, 1);
											hold.superholding(p, le, (long) 40);
									            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									                @Override
									                public void run() {
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
															enar.setDamage(player_damage.get(p.getName())*1.35+tsd.CombustInside.get(p.getUniqueId())*0.6);								
														}
									                	p.setSprinting(true);
														le.damage(0,p);
														le.damage(player_damage.get(p.getName())*1.35+tsd.CombustInside.get(p.getUniqueId())*0.6,p);	
									                	p.setSprinting(false);													
														p.playSound(p.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 1.0f, 0f);
														p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 2f);
														p.getWorld().spawnParticle(Particle.SOUL, le.getLocation(), 100, 1, 1, 1);
									                }
									            }, 40); 
											}
										}
									else if (le instanceof LivingEntity) {
										p.playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1.0f, 2.0f);
										p.getWorld().spawnParticle(Particle.ASH, le.getLocation(), 100, 1, 1, 1);
										hold.superholding(p, le, (long) 40);
								            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
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
														enar.setDamage(player_damage.get(p.getName())*1.35+tsd.CombustInside.get(p.getUniqueId())*0.6);								
													}
								                	p.setSprinting(true);
													le.damage(0,p);
													le.damage(player_damage.get(p.getName())*1.35+tsd.CombustInside.get(p.getUniqueId())*0.6,p);	
								                	p.setSprinting(false);																							
													p.playSound(p.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 1.0f, 0f);
													p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 2f);
													p.getWorld().spawnParticle(Particle.SOUL, le.getLocation(), 100, 1, 1, 1);
								                }
								            }, 40); 
										
									}
					            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				            }
				        }
				        else // if cooldown doesn't have players name in it
				        {
				        	if (le instanceof Player) 
							{
								
								Player p1 = (Player) le;
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
									{
										return;
									}
								}
								else {
									p.playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1.0f, 2.0f);
									p.getWorld().spawnParticle(Particle.ASH, le.getLocation(), 100, 1, 1, 1);
									hold.superholding(p, le, (long) 40);
							            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
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
													enar.setDamage(player_damage.get(p.getName())*1.35+tsd.CombustInside.get(p.getUniqueId())*0.6);								
												}
							                	p.setSprinting(true);
												le.damage(0,p);
												le.damage(player_damage.get(p.getName())*1.35+tsd.CombustInside.get(p.getUniqueId())*0.6,p);	
							                	p.setSprinting(false);																					
												p.playSound(p.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 1.0f, 0f);
												p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 2f);
												p.getWorld().spawnParticle(Particle.SOUL, le.getLocation(), 100, 1, 1, 1);
							                }
							            }, 40); 
									}
								}
							else if (le instanceof LivingEntity) {
								p.playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1.0f, 2.0f);
								p.getWorld().spawnParticle(Particle.ASH, le.getLocation(), 100, 1, 1, 1);
								hold.superholding(p, le, (long) 40);
						            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
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
												enar.setDamage(player_damage.get(p.getName())*1.35+tsd.CombustInside.get(p.getUniqueId())*0.6);								
											}
						                	p.setSprinting(true);
											le.damage(0,p);
											le.damage(player_damage.get(p.getName())*1.35+tsd.CombustInside.get(p.getUniqueId())*0.6,p);	
						                	p.setSprinting(false);																							
											p.playSound(p.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 1.0f, 0f);
											p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 2f);
											p.getWorld().spawnParticle(Particle.SOUL, le.getLocation(), 100, 1, 1, 1);
						                }
						            }, 40); 
								
							}
				            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        }
							
					}
					}

					else {p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You should choose stance first").create());}
				}
		}
		}
	}
	
	@EventHandler
	public void ULT(PlayerDropItemEvent ev)        
    {
	    
		Player p = (Player)ev.getPlayer();
		final Location sl = p.getLocation();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
		
		
		
		
			if(playerclass.get(p.getUniqueId()) == 10 && (is.getType().name().contains("NUGGET")) && p.isSneaking())
			{
				if(stance.containsKey(p.getName())){
				ev.setCancelled(true);
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (sultcooldown.get(p.getName())/1000 + 75) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Numinous").create());
	                }
	                else // if timer is done
	                {
	                    sultcooldown.remove(p.getName()); // removing player from HashMap
	                    p.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 1.0f, 1f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 0f);
	                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_MOOD, 1.0f, 2f);
						p.getWorld().spawnParticle(Particle.TOTEM, p.getLocation(), 100, 3, 3, 3);
	                    p.setGlowing(true);
	                    p.setInvulnerable(true);
	                    p.setCollidable(false);
						p.setGravity(false);
	                    p.teleport(p.getLocation().add(0, 1, 0));
	                    for(int i=0; i<60; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                 		@Override
		    		                	public void run() 
		    			                {	
											p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
    										p.getWorld().spawnParticle(Particle.TOTEM, p.getLocation(), 20, 3, 3, 3);
		                 					for (Entity e : p.getNearbyEntities(5, 5, 5))
		            						{
		                                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
		            							{
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
																enar.setDamage(player_damage.get(p.getName())*0.21);								
															}
		            										p.setSprinting(true);
		            										le.damage(0, p);
		            										le.damage(player_damage.get(p.getName())*0.21, p);
		            										p.setSprinting(false);
		            										
		            										if (le instanceof Player) 
		            										{
		            											
		            											Player p1 = (Player) le;
		            											if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
		            											if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
		            												{
		            													p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
		            												}
		            											}
		            										}
		            										
		            									
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
	                 					p.setVelocity(p.getVelocity().zero());
										p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 60, 0, false, false));
			    	                    p.setGlowing(false);
			    	                    p.setInvulnerable(false);
			    	                    p.setCollidable(true);
	                 					p.setVelocity(p.getVelocity().zero());
	            						
	    				            }
	                    }, 130); 
	                    
	                    sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	p.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 1.0f, 1f);
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 0f);
                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_MOOD, 1.0f, 2f);
					p.getWorld().spawnParticle(Particle.TOTEM, p.getLocation(), 100, 3, 3, 3);
                    p.setGlowing(true);
                    p.setInvulnerable(true);
                    p.setCollidable(false);
					p.setGravity(false);
                    p.teleport(p.getLocation().add(0, 1, 0));
                    for(int i=0; i<60; i++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                 		@Override
	    		                	public void run() 
	    			                {	
										p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
										p.getWorld().spawnParticle(Particle.TOTEM, p.getLocation(), 20, 3, 3, 3);
	                 					for (Entity e : p.getNearbyEntities(5, 5, 5))
	            						{
	                                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
	            							{
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
															enar.setDamage(player_damage.get(p.getName())*0.21);								
														}
	            										p.setSprinting(true);
	            										le.damage(0, p);
	            										le.damage(player_damage.get(p.getName())*0.21, p);
	            										p.setSprinting(false);
	            										
	            										if (le instanceof Player) 
	            										{
	            											
	            											Player p1 = (Player) le;
	            											if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
	            											if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
	            												{
	            													p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
	            												}
	            											}
	            										}
	            										
	            									
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
                 					p.setVelocity(p.getVelocity().zero());
									p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 60, 0, false, false));
		    	                    p.setGlowing(false);
		    	                    p.setInvulnerable(false);
		    	                    p.setCollidable(true);
                 					p.setVelocity(p.getVelocity().zero());
            						
    				            }
                    }, 130); 
                    
	                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
				}

				else {p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You should choose stance first").create());}
				
			}
    }
	
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
	    
		Player p = e.getPlayer();

		
		
		if(playerclass.get(p.getUniqueId()) == 10)
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
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 10) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
				{
						player_damage.put(p.getName(), 4 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
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
	
			
			
				if(playerclass.get(p.getUniqueId()) == 10) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
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
				}
		}

		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
			Arrow ar = (Arrow)d.getDamager();
			Entity e = d.getEntity();
	
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
				
				
				if(playerclass.get(p.getUniqueId()) == 10) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
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
				}
			}
		}
	}
}



