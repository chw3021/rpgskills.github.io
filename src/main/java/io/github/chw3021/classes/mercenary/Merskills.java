//package io.github.chw3021.classes.mercenary;
//
//
//
//import io.github.chw3021.classes.ClassData;
//import io.github.chw3021.classes.Proficiency;
//import io.github.chw3021.commons.Holding;
//import io.github.chw3021.commons.Pak;
//import io.github.chw3021.obtains.Obtained;
//import io.github.chw3021.party.Party;
//
//import org.bukkit.potion.PotionEffect;
//import org.bukkit.potion.PotionEffectType;
//import org.bukkit.util.Vector;
//
//import io.github.chw3021.rmain.RMain;
//import net.md_5.bungee.api.ChatColor;
//import net.md_5.bungee.api.ChatMessageType;
//import net.md_5.bungee.api.chat.ComponentBuilder;
//
//import java.io.File;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.UUID;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import org.bukkit.Bukkit;
//import org.bukkit.Location;
//import org.bukkit.Material;
//import org.bukkit.Particle;
//import org.bukkit.Sound;
//import org.bukkit.World;
//import org.bukkit.attribute.Attribute;
//import org.bukkit.entity.Arrow;
//import org.bukkit.entity.Entity;
//import org.bukkit.entity.Item;
//import org.bukkit.entity.LivingEntity;
//import org.bukkit.entity.Player;
//import org.bukkit.event.Listener;
//import org.bukkit.event.block.Action;
//import org.bukkit.event.entity.EntityDamageByEntityEvent;
//import org.bukkit.event.entity.EntityDamageEvent;
//import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
//import org.bukkit.event.entity.EntityPickupItemEvent;
//import org.bukkit.event.inventory.InventoryClickEvent;
//import org.bukkit.event.player.PlayerDropItemEvent;
//import org.bukkit.event.player.PlayerInteractEvent;
//import org.bukkit.event.player.PlayerItemDamageEvent;
//import org.bukkit.event.player.PlayerItemHeldEvent;
//import org.bukkit.event.player.PlayerJoinEvent;
//import org.bukkit.event.player.PlayerSwapHandItemsEvent;
//import org.bukkit.event.server.PluginEnableEvent;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.metadata.FixedMetadataValue;
//
//public class Merskills extends Pak implements Listener, Serializable {
//	
//
//	/**
//	 * 
//	 */
//	private static transient final long serialVersionUID = -6205130125239201504L;
//	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
//	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
//	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
//	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
//	private HashMap<String, Long> lncooldown = new HashMap<String, Long>();
//	private HashMap<String, Long> cacooldown = new HashMap<String, Long>();
//	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
//	private HashMap<String, Long> sult2cooldown = new HashMap<String, Long>();
//	private HashMap<String, Integer> weapontype = new HashMap<String, Integer>();
//	private HashMap<UUID, Boolean> ulton = new HashMap<UUID, Boolean>();
//	private HashMap<UUID, Integer> ultont = new HashMap<UUID, Integer>();
//	//private HashMap<String, Double> player_damage = new HashMap<String, Double>();
//	
//	private HashMap<UUID, Integer> squirt = new HashMap<UUID, Integer>();
//	private HashMap<UUID, Integer> squirtt = new HashMap<UUID, Integer>();
//	private HashMap<UUID, Integer> rave = new HashMap<UUID, Integer>();
//	private HashMap<UUID, Integer> ravet = new HashMap<UUID, Integer>();
//	
//	private HashMap<UUID, Integer> smite = new HashMap<UUID, Integer>();
//	private HashMap<UUID, Integer> smitet = new HashMap<UUID, Integer>();
//	private HashMap<UUID, Integer> onsl = new HashMap<UUID, Integer>();
//	private HashMap<UUID, Integer> onslt = new HashMap<UUID, Integer>();
//
//	private HashMap<UUID, Integer> scratch = new HashMap<UUID, Integer>();
//	private HashMap<UUID, Integer> scratcht = new HashMap<UUID, Integer>();
//
//	private HashMap<UUID, Integer> nom = new HashMap<UUID, Integer>();
//	private HashMap<UUID, Integer> nomt = new HashMap<UUID, Integer>();
//	private HashMap<UUID, Integer> crs = new HashMap<UUID, Integer>();
//	private HashMap<UUID, Integer> crst = new HashMap<UUID, Integer>();
//	
//	private HashMap<UUID, Location> burst = new HashMap<UUID, Location>();
//	private HashMap<UUID, Integer> burstt = new HashMap<UUID, Integer>();
//
//	private HashMap<UUID, Integer> conv = new HashMap<UUID, Integer>();
//	
//	Holding hold = Holding.getInstance();
//	private String path = new File("").getAbsolutePath();
//	private MerSkillsData bsd;
//
//
//	private static final Merskills instance = new Merskills ();
//	public static Merskills getInstance()
//	{
//		return instance;
//	}
//	
//	
//	
//	public void classinv(InventoryClickEvent e)
//	{
//		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Classes"))
//		{
//			e.setCancelled(true);
//			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
//			{
//				e.setCancelled(false);
//			}
//			else
//			{
//				Player p = (Player) e.getWhoClicked();
//				if(ultont.containsKey(p.getUniqueId())) {
//					Bukkit.getScheduler().cancelTask(ultont.get(p.getUniqueId()));
//					ultont.remove(p.getUniqueId());
//				}
//				ulton.remove(p.getUniqueId());
//			}
//			
//		}
//		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Berskills"))
//		{
//			e.setCancelled(true);
//			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
//			{
//				e.setCancelled(false);
//			}
//			else
//			{	
//				MerSkillsData b = new MerSkillsData(MerSkillsData.loadData(path +"/plugins/RPGskills/BerSkillsData.data"));
//				bsd = b;
//			}
//		}
//	}
//
//		
//	public void nepreventer(PlayerJoinEvent ev) 
//	{
//		MerSkillsData b = new MerSkillsData(MerSkillsData.loadData(path +"/plugins/RPGskills/BerSkillsData.data"));
//		bsd = b;
//	}
//
//		
//	public void er(PluginEnableEvent ev) 
//	{
//		MerSkillsData b = new MerSkillsData(MerSkillsData.loadData(path +"/plugins/RPGskills/BerSkillsData.data"));
//		bsd = b;
//	}
//	
//	final private void weaponChange(Player p, Integer in, Integer prof) {
//		Location pl = p.getLocation();
//		if(in == 1) {
//	
//			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
//	
//	    		switch (weapontype.getOrDefault(p.getName(),0))
//				{
//					case 0:
//						weapontype.put(p.getName(), 1);
//						p.playSound(p, Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0.6f);
//						p.getWorld().spawnParticle(Particle.DUST, pl, 20, 1d, 1d, 1d, 0.1, new Particle.DustOptions(org.bukkit.Color.ORANGE, 1f));
//						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder( ChatColor.BOLD + "���⺯��" + ChatColor.GOLD +  "[����]").create());
//						break;
//					case 1:
//						weapontype.put(p.getName(), 2);
//						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder( ChatColor.BOLD + "���⺯��" + ChatColor.AQUA +  "[������]").create());
//						break;
//					case 2:
//						weapontype.put(p.getName(), 0);
//						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder( ChatColor.BOLD + "���⺯��" + ChatColor.RED +  "[�罽��]").create());
//						break;
//				}			
//	    	}
//			else {
//	    		switch (weapontype.getOrDefault(p.getName(),0))
//				{
//				case 0:
//					weapontype.put(p.getName(), 1);
//					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder( ChatColor.BOLD + "WeaponChange" + ChatColor.GOLD +  "[SHOTGUN]").create());
//					break;
//				case 1:
//					weapontype.put(p.getName(), 2);
//					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder( ChatColor.BOLD + "WeaponChange" + ChatColor.AQUA +  "[REVOLVER]").create());
//					break;
//				case 2:
//					weapontype.put(p.getName(), 0);
//					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder( ChatColor.BOLD + "WeaponChange" + ChatColor.RED +  "[CHAINED SWORD]").create());
//					break;
//				}
//			}
//		}
//		else {
//	
//			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
//	    		switch (weapontype.getOrDefault(p.getName(),0))
//				{
//				case 0:
//					weapontype.put(p.getName(), 2);
//					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder( ChatColor.BOLD + "���⺯��" + ChatColor.AQUA +  "[������]").create());
//					break;
//				case 1:
//					weapontype.put(p.getName(), 0);
//					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder( ChatColor.BOLD + "���⺯��" + ChatColor.RED +  "[�罽��]").create());
//					break;
//				case 2:
//					weapontype.put(p.getName(), 1);
//					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder( ChatColor.BOLD + "���⺯��" + ChatColor.GOLD +  "[����]").create());
//					break;
//				}
//			}
//			else {
//	    		switch (weapontype.getOrDefault(p.getName(),0))
//				{
//				case 0:
//					weapontype.put(p.getName(), 2);
//					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder( ChatColor.BOLD + "WeaponChange" + ChatColor.AQUA +  "[REVOLVER]").create());
//					break;
//				case 1:
//					weapontype.put(p.getName(), 2);
//					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder( ChatColor.BOLD + "WeaponChange" + ChatColor.RED +  "[CHAINED SWORD]").create());
//					break;
//				case 2:
//					weapontype.put(p.getName(), 1);
//					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder( ChatColor.BOLD + "WeaponChange" + ChatColor.GOLD +  "[SHOTGUN]").create());
//					break;
//				}
//			}
//		}
//	}
//
//
//
//	public void weaponChange(PlayerItemHeldEvent ev) 
//	{
//		Player p = ev.getPlayer();
//		if(ClassData.pc.get(p.getUniqueId()) == 23 && (p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && p.isSneaking()) && !p.hasCooldown(Material.ARROW)){
//			ev.setCancelled(true);
//			p.setCooldown(Material.ARROW, 2);
//			final int prof = Proficiency.getpro(p);
//				if(ev.getPreviousSlot()==0) {
//					if(ev.getNewSlot()!=8) {
//			        		weaponChange(p,1,prof);
//			        		return;
//					}
//					else {
//		        		weaponChange(p,0,prof);
//	                	return;
//					}
//				}
//				else if(ev.getPreviousSlot()==8) {
//					if(ev.getNewSlot()==0) {
//		        		weaponChange(p,0,prof);
//	                	return;
//						
//					}
//					else {
//		        		weaponChange(p,1,prof);
//	                	return;
//					}
//				}
//				else {
//					if(ev.getNewSlot()>ev.getPreviousSlot()) {
//		        		weaponChange(p,1,prof);
//	                	return;
//						
//					}
//					else if(ev.getNewSlot()<ev.getPreviousSlot()){
//		        		weaponChange(p,0,prof);
//	                	return;
//					}
//				}
//		}
//	}
//	
//	
//}
//
//
//
