package io.github.chw3021.obtains;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;

import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.angler.AngSkillsData;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

@SuppressWarnings("unused")
public class Obtained implements Serializable, Listener {

	/**
	 * 
	 */
	private static transient final long serialVersionUID = -8734003197215830656L;
	/**
	 * 
	 */
	private String path = new File("").getAbsolutePath();
	private Obtained ob;
	public static HashMap<UUID, Double> ucd = new HashMap<UUID, Double>();
	public static HashMap<UUID, Double> ncd = new HashMap<UUID, Double>();
	
	public final HashMap<UUID, Integer> Mineshaft;
	public final HashMap<UUID, Integer> BuriedTreasure;
	public final HashMap<UUID, Integer> Igloo;
	public final HashMap<UUID, Integer> OceanRuins;
	public final HashMap<UUID, Integer> WoodlandMansion;
	public final HashMap<UUID, Integer> Shipwreck;
	public final HashMap<UUID, Integer> OceanMonument;
	public final HashMap<UUID, Integer> JungleTemple;
	public final HashMap<UUID, Integer> PillagerOutpost;
	public final HashMap<UUID, Integer> DesertPyramid;
	public final HashMap<UUID, Integer> RuinedPortal;
	public final HashMap<UUID, Integer> Stronghold;
	public final HashMap<UUID, Integer> SwampHut;
	public final HashMap<UUID, Integer> Village;
	public final HashMap<UUID, Integer> NetherFortress;
	public final HashMap<UUID, Integer> BastionRemnant;
	public final HashMap<UUID, Integer> EndCity;
	public final HashMap<UUID, Integer> EnderDragon;
	// Can be used for saving


    @EventHandler
	public void er(PluginEnableEvent ev) 
	{
		ob = loadData(path +"/plugins/RPGskills/Obtained.data");
		Bukkit.getOnlinePlayers().forEach(p ->{
			double ultc = 1 * (1 - (ob.JungleTemple.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (ob.RuinedPortal.getOrDefault(p.getUniqueId(), 0)>=4 ? 0.1:0))* (1 - (ob.EnderDragon.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0));
			double norc = 1 * (1 - (ob.DesertPyramid.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (ob.Stronghold.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (ob.Village.getOrDefault(p.getUniqueId(), 0)>=5 ? 0.1:0)) * (1 - (ob.BastionRemnant.getOrDefault(p.getUniqueId(), 0)>=2 ? 0.06:0));
			ucd.put(p.getUniqueId(), (ultc));
			ncd.put(p.getUniqueId(), norc);
		});
		
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
				Player p = (Player) e.getWhoClicked();
				p.setCooldown(Material.STRUCTURE_VOID, 1);
				ob = loadData(path +"/plugins/RPGskills/Obtained.data");
				double ultc = 1 * (1 - (ob.JungleTemple.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (ob.RuinedPortal.getOrDefault(p.getUniqueId(), 0)>=4 ? 0.1:0))* (1 - (ob.EnderDragon.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0));
				double norc = 1 * (1 - (ob.DesertPyramid.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (ob.Stronghold.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (ob.Village.getOrDefault(p.getUniqueId(), 0)>=5 ? 0.1:0)) * (1 - (ob.BastionRemnant.getOrDefault(p.getUniqueId(), 0)>=2 ? 0.06:0));
				ucd.put(p.getUniqueId(), (ultc));
				ncd.put(p.getUniqueId(), norc);
			}
			
		}
		if(ChatColor.stripColor(e.getView().getTitle()).contains("skill"))
		{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			p.setCooldown(Material.STRUCTURE_VOID, 1);
			ob = loadData(path +"/plugins/RPGskills/Obtained.data");
			double ultc = 1 * (1 - (ob.JungleTemple.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (ob.RuinedPortal.getOrDefault(p.getUniqueId(), 0)>=4 ? 0.1:0))* (1 - (ob.EnderDragon.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0));
			double norc = 1 * (1 - (ob.DesertPyramid.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (ob.Stronghold.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (ob.Village.getOrDefault(p.getUniqueId(), 0)>=5 ? 0.1:0)) * (1 - (ob.BastionRemnant.getOrDefault(p.getUniqueId(), 0)>=2 ? 0.06:0));
			ucd.put(p.getUniqueId(), (ultc));
			ncd.put(p.getUniqueId(), norc);
		}
	}


    @EventHandler
	public void nepreventer(PlayerJoinEvent ev) 
	{
		Player p = ev.getPlayer();
		ob = loadData(path +"/plugins/RPGskills/Obtained.data");
		double ultc = 1 * (1 - (ob.JungleTemple.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (ob.RuinedPortal.getOrDefault(p.getUniqueId(), 0)>=4 ? 0.1:0))* (1 - (ob.EnderDragon.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0));
		double norc = 1 * (1 - (ob.DesertPyramid.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (ob.Stronghold.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (ob.Village.getOrDefault(p.getUniqueId(), 0)>=5 ? 0.1:0)) * (1 - (ob.BastionRemnant.getOrDefault(p.getUniqueId(), 0)>=2 ? 0.06:0));
		ucd.put(p.getUniqueId(), (ultc));
		ncd.put(p.getUniqueId(), norc);
		
	}


	
	
	
	
	public static void saver(Player p, Integer f, Integer add) {
		String path = new File("").getAbsolutePath();
		Obtained d = new Obtained(Obtained.loadData(path + "/plugins/RPGskills/Obtained.data"));
		HashMap<UUID, Integer> Mineshaft = d.Mineshaft;
		HashMap<UUID, Integer> BuriedTreasure = d.BuriedTreasure;
		HashMap<UUID, Integer> Igloo = d.Igloo;
		HashMap<UUID, Integer> OceanRuins = d.OceanRuins;
		HashMap<UUID, Integer> WoodlandMansion = d.WoodlandMansion;
		HashMap<UUID, Integer> Shipwreck = d.Shipwreck;
		HashMap<UUID, Integer> OceanMonument = d.OceanMonument;
		HashMap<UUID, Integer> JungleTemple = d.JungleTemple;
		HashMap<UUID, Integer> PillagerOutpost = d.PillagerOutpost;
		HashMap<UUID, Integer> DesertPyramid = d.DesertPyramid;
		HashMap<UUID, Integer> Stronghold = d.Stronghold;
		HashMap<UUID, Integer> SwampHut = d.SwampHut;
		HashMap<UUID, Integer> Village = d.Village;
		HashMap<UUID, Integer> RuinedPortal = d.RuinedPortal;
		HashMap<UUID, Integer> NetherFortress = d.NetherFortress;
		HashMap<UUID, Integer> BastionRemnant = d.BastionRemnant;
		HashMap<UUID, Integer> EndCity = d.EndCity;
		HashMap<UUID, Integer> EnderDragon = d.EnderDragon;
		if (f == 0) {
			if (Mineshaft.getOrDefault(p.getUniqueId(), 0) < 2) {
				Mineshaft.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				Mineshaft.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f == 1) {
			if (BuriedTreasure.getOrDefault(p.getUniqueId(), 0) < 2) {
				BuriedTreasure.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				BuriedTreasure.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f == 2) {
			if (Igloo.getOrDefault(p.getUniqueId(), 0) < 2) {
				Igloo.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				Igloo.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f == 3) {
			if (OceanRuins.getOrDefault(p.getUniqueId(), 0) < 3) {
				OceanRuins.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				OceanRuins.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f == 4) {
			if (WoodlandMansion.getOrDefault(p.getUniqueId(), 0) < 1) {
				WoodlandMansion.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				WoodlandMansion.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f == 5) {
			if (Shipwreck.getOrDefault(p.getUniqueId(), 0) < 3) {
				Shipwreck.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				Shipwreck.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f == 6) {
			if (OceanMonument.getOrDefault(p.getUniqueId(), 0) < 1) {
				OceanMonument.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				OceanMonument.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f == 7) {
			if (JungleTemple.getOrDefault(p.getUniqueId(), 0) < 1) {
				JungleTemple.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				JungleTemple.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f == 8) {
			if (PillagerOutpost.getOrDefault(p.getUniqueId(), 0) < 2) {
				PillagerOutpost.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				PillagerOutpost.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f == 9) {
			if (DesertPyramid.getOrDefault(p.getUniqueId(), 0) < 1) {
				DesertPyramid.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				DesertPyramid.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f == 10) {
			if (Stronghold.getOrDefault(p.getUniqueId(), 0) < 1) {
				Stronghold.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				Stronghold.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f == 11) {
			if (SwampHut.getOrDefault(p.getUniqueId(), 0) < 2) {
				SwampHut.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				SwampHut.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f == 12) {
			if (Village.getOrDefault(p.getUniqueId(), 0) < 5) {
				Village.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				Village.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f == 13) {
			if (RuinedPortal.getOrDefault(p.getUniqueId(), 0) < 4) {
				RuinedPortal.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				RuinedPortal.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f == 14) {
			if (NetherFortress.getOrDefault(p.getUniqueId(), 0) < 2) {
				NetherFortress.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				NetherFortress.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f == 15) {
			if (BastionRemnant.getOrDefault(p.getUniqueId(), 0) < 2) {
				BastionRemnant.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				BastionRemnant.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f == 16) {
			if (EnderDragon.getOrDefault(p.getUniqueId(), 0) < 1) {
				EnderDragon.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				EnderDragon.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f == 17) {
			if (EndCity.getOrDefault(p.getUniqueId(), 0) < 1) {
				EndCity.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				EndCity.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		}
		

		double ultc = 1 * (1 - (JungleTemple.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (RuinedPortal.getOrDefault(p.getUniqueId(), 0)>=4 ? 0.1:0))* (1 - (EnderDragon.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0));
		double norc = 1 * (1 - (DesertPyramid.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (Stronghold.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (Village.getOrDefault(p.getUniqueId(), 0)>=5 ? 0.1:0)) * (1 - (BastionRemnant.getOrDefault(p.getUniqueId(), 0)>=2 ? 0.06:0));
		ucd.put(p.getUniqueId(), (ultc));
		ncd.put(p.getUniqueId(), norc);
		
		
		new Obtained(Mineshaft, BuriedTreasure, Igloo, OceanRuins, WoodlandMansion, Shipwreck, OceanMonument,
				JungleTemple, PillagerOutpost, DesertPyramid, Stronghold, SwampHut, Village, RuinedPortal,
				NetherFortress, BastionRemnant, EndCity, EnderDragon)
						.saveData(path + "/plugins/RPGskills/Obtained.data");
		
	}


	public static void saver(Player p, String f, Integer add) {
		String path = new File("").getAbsolutePath();
		Obtained d = new Obtained(Obtained.loadData(path + "/plugins/RPGskills/Obtained.data"));
		HashMap<UUID, Integer> Mineshaft = d.Mineshaft;
		HashMap<UUID, Integer> BuriedTreasure = d.BuriedTreasure;
		HashMap<UUID, Integer> Igloo = d.Igloo;
		HashMap<UUID, Integer> OceanRuins = d.OceanRuins;
		HashMap<UUID, Integer> WoodlandMansion = d.WoodlandMansion;
		HashMap<UUID, Integer> Shipwreck = d.Shipwreck;
		HashMap<UUID, Integer> OceanMonument = d.OceanMonument;
		HashMap<UUID, Integer> JungleTemple = d.JungleTemple;
		HashMap<UUID, Integer> PillagerOutpost = d.PillagerOutpost;
		HashMap<UUID, Integer> DesertPyramid = d.DesertPyramid;
		HashMap<UUID, Integer> Stronghold = d.Stronghold;
		HashMap<UUID, Integer> SwampHut = d.SwampHut;
		HashMap<UUID, Integer> Village = d.Village;
		HashMap<UUID, Integer> RuinedPortal = d.RuinedPortal;
		HashMap<UUID, Integer> NetherFortress = d.NetherFortress;
		HashMap<UUID, Integer> BastionRemnant = d.BastionRemnant;
		HashMap<UUID, Integer> EndCity = d.EndCity;
		HashMap<UUID, Integer> EnderDragon = d.EnderDragon;
		if (f.contains("mine")) {
			if (Mineshaft.getOrDefault(p.getUniqueId(), 0) < 2) {
				Mineshaft.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				Mineshaft.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("treasure")) {
			if (BuriedTreasure.getOrDefault(p.getUniqueId(), 0) < 2) {
				BuriedTreasure.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				BuriedTreasure.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("igloo")) {
			if (Igloo.getOrDefault(p.getUniqueId(), 0) < 2) {
				Igloo.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				Igloo.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("ruins")) {
			if (OceanRuins.getOrDefault(p.getUniqueId(), 0) < 3) {
				OceanRuins.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				OceanRuins.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("mansion")) {
			if (WoodlandMansion.getOrDefault(p.getUniqueId(), 0) < 1) {
				WoodlandMansion.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				WoodlandMansion.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("ship")) {
			if (Shipwreck.getOrDefault(p.getUniqueId(), 0) < 3) {
				Shipwreck.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				Shipwreck.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("monument")) {
			if (OceanMonument.getOrDefault(p.getUniqueId(), 0) < 1) {
				OceanMonument.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				OceanMonument.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("jungle")) {
			if (JungleTemple.getOrDefault(p.getUniqueId(), 0) < 1) {
				JungleTemple.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				JungleTemple.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("pill")) {
			if (PillagerOutpost.getOrDefault(p.getUniqueId(), 0) < 2) {
				PillagerOutpost.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				PillagerOutpost.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("desert")) {
			if (DesertPyramid.getOrDefault(p.getUniqueId(), 0) < 1) {
				DesertPyramid.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				DesertPyramid.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("strong")) {
			if (Stronghold.getOrDefault(p.getUniqueId(), 0) < 1) {
				Stronghold.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				Stronghold.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("swamp")) {
			if (SwampHut.getOrDefault(p.getUniqueId(), 0) < 2) {
				SwampHut.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				SwampHut.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("village")) {
			if (Village.getOrDefault(p.getUniqueId(), 0) < 5) {
				Village.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				Village.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("portal")) {
			if (RuinedPortal.getOrDefault(p.getUniqueId(), 0) < 4) {
				RuinedPortal.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				RuinedPortal.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("fortress")) {
			if (NetherFortress.getOrDefault(p.getUniqueId(), 0) < 2) {
				NetherFortress.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				NetherFortress.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("bastion")) {
			if (BastionRemnant.getOrDefault(p.getUniqueId(), 0) < 2) {
				BastionRemnant.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				BastionRemnant.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("dragon")) {
			if (EnderDragon.getOrDefault(p.getUniqueId(), 0) < 1) {
				EnderDragon.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				EnderDragon.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("endcity")) {
			if (EndCity.getOrDefault(p.getUniqueId(), 0) < 1) {
				EndCity.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				EndCity.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ش� �������� ����ǰ�� ��� ȹ���߽��ϴ�").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		}
		
	
		double ultc = 1 * (1 - (JungleTemple.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (RuinedPortal.getOrDefault(p.getUniqueId(), 0)>=4 ? 0.1:0))* (1 - (EnderDragon.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0));
		double norc = 1 * (1 - (DesertPyramid.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (Stronghold.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (Village.getOrDefault(p.getUniqueId(), 0)>=5 ? 0.1:0)) * (1 - (BastionRemnant.getOrDefault(p.getUniqueId(), 0)>=2 ? 0.06:0));
		ucd.put(p.getUniqueId(), (ultc));
		ncd.put(p.getUniqueId(), norc);
		
		
		new Obtained(Mineshaft, BuriedTreasure, Igloo, OceanRuins, WoodlandMansion, Shipwreck, OceanMonument,
				JungleTemple, PillagerOutpost, DesertPyramid, Stronghold, SwampHut, Village, RuinedPortal,
				NetherFortress, BastionRemnant, EndCity, EnderDragon)
						.saveData(path + "/plugins/RPGskills/Obtained.data");
		
	}


	public static void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc,
			Inventory inv) {
		ItemStack item = new ItemStack(ID, stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}


	public static void itemset(String display, ItemStack is, int data, int stack, List<String> Lore, int loc,
			Inventory inv) {
		ItemStack item = is;
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}


	public static void itemset(Player p, Inventory inv) {
		String path = new File("").getAbsolutePath();
		Obtained od = new Obtained(Obtained.loadData(path + "/plugins/RPGskills/Obtained.data"));
	
		ItemStack mp = new ItemStack(Material.CHEST);
		mp.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
	
		ItemStack ms = new ItemStack(Material.POWERED_RAIL);
		ms.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
		ItemStack zh = new ItemStack(Material.ZOMBIE_HEAD);
		zh.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
		ItemStack sb = new ItemStack(Material.POWDER_SNOW_BUCKET);
		sb.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
		ItemStack pc = new ItemStack(Material.PRISMARINE_CRYSTALS);
		pc.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
		ItemStack ds = new ItemStack(Material.DARK_OAK_SIGN);
		ds.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
		ItemStack bb = new ItemStack(Material.BIRCH_BOAT);
		ds.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
		ItemStack hs = new ItemStack(Material.HEART_OF_THE_SEA);
		hs.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
		ItemStack jp = new ItemStack(Material.MOSSY_STONE_BRICKS);
		jp.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
		ItemStack pe = new ItemStack(Material.WHITE_BANNER);
		BannerMeta pem = (BannerMeta) pe.getItemMeta();
		pem.addPattern(new Pattern(DyeColor.WHITE, PatternType.BASE));
		pem.addPattern(new Pattern(DyeColor.CYAN, PatternType.RHOMBUS_MIDDLE));
		pem.addPattern(new Pattern(DyeColor.LIGHT_GRAY, PatternType.STRIPE_BOTTOM));
		pem.addPattern(new Pattern(DyeColor.GRAY, PatternType.STRIPE_CENTER));
		pem.addPattern(new Pattern(DyeColor.LIGHT_GRAY, PatternType.CURLY_BORDER));
		pem.addPattern(new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE));
		pem.addPattern(new Pattern(DyeColor.LIGHT_GRAY, PatternType.HALF_HORIZONTAL));
		pem.addPattern(new Pattern(DyeColor.LIGHT_GRAY, PatternType.CIRCLE_MIDDLE));
		pem.addPattern(new Pattern(DyeColor.BLACK, PatternType.BORDER));
		pe.setItemMeta(pem);
		ItemStack cs = new ItemStack(Material.CHISELED_SANDSTONE);
		cs.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
		ItemStack ef = new ItemStack(Material.END_PORTAL_FRAME);
		ef.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
		ItemStack my = new ItemStack(Material.MYCELIUM);
		my.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
		ItemStack be = new ItemStack(Material.BELL);
		be.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
		ItemStack co = new ItemStack(Material.CRYING_OBSIDIAN);
		co.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
		ItemStack nq = new ItemStack(Material.NETHER_QUARTZ_ORE);
		nq.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
		ItemStack ba = new ItemStack(Material.BASALT);
		ba.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
		ItemStack ed = new ItemStack(Material.END_STONE_BRICKS);
		ed.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
		ItemStack ph = new ItemStack(Material.DRAGON_HEAD);
		ph.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
	
		if (p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset(ChatColor.GOLD + "��������", new ItemStack(Material.COMPOSTER), 0, 1, Arrays.asList("Ŭ���� ���������� ���ϴ�"),
					30, inv);
			itemset(ChatColor.GOLD + "������� ����", new ItemStack(Material.AMETHYST_SHARD), 0, 1, Arrays.asList("Ŭ���� ������� ���� â�� ���ϴ�"),
					31, inv);
			itemset(ChatColor.GOLD + "��Ŀ ����", new ItemStack(Material.SHULKER_BOX), 0, 1, Arrays.asList("[��ũ���� + ��ġ]�� ��Ŀ���ڸ� ����ó�� ����Ҽ� �ֽ��ϴ�"),
					32, inv);
			itemset(ChatColor.GOLD + "�賶", new ItemStack(Material.BARREL), 0, 1, Arrays.asList("Ŭ���� �賶�� ���ϴ�"),
					33, inv);
			
			if (od.Mineshaft.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "�䱤 ����ǰ", ms, 0, 1, Arrays.asList("���ݷ°� ������ 2% �����մϴ�", "����ġ ȹ�淮�� 5% �����մϴ�"),
						36, inv);
			} else {
				itemset(ChatColor.GOLD + "�䱤 ����ǰ(���� ��� ȹ������ ����)", Material.POWERED_RAIL, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.Mineshaft.getOrDefault(p.getUniqueId(), 0) + "/2 ȹ����"),
						36, inv);
			}
	
			
			if (od.Mineshaft.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "�䱤 ����ǰ", ms, 0, 1, Arrays.asList("���ݷ°� ������ 2% �����մϴ�", "����ġ ȹ�淮�� 5% �����մϴ�"),
						36, inv);
			} else {
				itemset(ChatColor.GOLD + "�䱤 ����ǰ(���� ��� ȹ������ ����)", Material.POWERED_RAIL, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.Mineshaft.getOrDefault(p.getUniqueId(), 0) + "/2 ȹ����"),
						36, inv);
			}
	
			if (od.BuriedTreasure.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "�Ĺ��� ���� ����ǰ", mp, 0, 1,
						Arrays.asList("���ݷ°� ������ 3% �����մϴ�", "����ġ ȹ�淮�� 5% �����մϴ�"), 37, inv);
			} else {
				itemset(ChatColor.GOLD + "�Ĺ��� ���� ����ǰ(���� ��� ȹ������ ����)", Material.CHEST, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.BuriedTreasure.getOrDefault(p.getUniqueId(), 0) + "/2 ȹ����"),
						37, inv);
			}
	
			if (od.Igloo.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "�̱۷� ����ǰ", sb, 0, 1,
						Arrays.asList("���ݷ°� ������ 2.5% �����մϴ�", "����ġ ȹ�淮�� 5% �����մϴ�", "��ȭ�� �鿪�� �˴ϴ�"), 38, inv);
			} else {
				itemset(ChatColor.GOLD + "�̱۷� ����ǰ(���� ��� ȹ������ ����)", Material.POWDER_SNOW_BUCKET, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.Igloo.getOrDefault(p.getUniqueId(), 0) + "/2 ȹ����"), 38,
						inv);
			}
	
			if (od.OceanRuins.getOrDefault(p.getUniqueId(), 0) >= 3) {
				itemset(ChatColor.GOLD + "���� ���� ����ǰ", pc, 0, 1, Arrays.asList("������ 5% �����մϴ�", "�ͻ翡 �鿪�� �˴ϴ�"), 39, inv);
			} else {
				itemset(ChatColor.GOLD + "���� ���� ����ǰ(���� ��� ȹ������ ����)", Material.PRISMARINE_CRYSTALS, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.OceanRuins.getOrDefault(p.getUniqueId(), 0) + "/3 ȹ����"),
						39, inv);
			}
	
			if (od.WoodlandMansion.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "�︲ ������ ����ǰ", ds, 0, 1, Arrays.asList("���ݷ��� 3% �����մϴ�", "������ 5% �����մϴ�"), 40,
						inv);
			} else {
				itemset(ChatColor.GOLD + "�︲ ������ ����ǰ(���� ��� ȹ������ ����)", Material.DARK_OAK_SIGN, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.WoodlandMansion.getOrDefault(p.getUniqueId(), 0) + "/3 ȹ����"),
						40, inv);
			}
	
			if (od.Shipwreck.getOrDefault(p.getUniqueId(), 0) >= 3) {
				itemset(ChatColor.GOLD + "���ļ� ����ǰ", bb, 0, 1, Arrays.asList("���ݷ°� ������ 3% �����մϴ�", "����ġ ȹ�淮�� 5% �����մϴ�"),
						41, inv);
			} else {
				itemset(ChatColor.GOLD + "���ļ� ����ǰ(���� ��� ȹ������ ����)", Material.BIRCH_BOAT, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.Shipwreck.getOrDefault(p.getUniqueId(), 0) + "/3 ȹ����"),
						41, inv);
			}
	
			if (od.OceanMonument.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "���� ���� ����ǰ", hs, 0, 1, Arrays.asList("���ݷ°� ������ 3% �����մϴ�", "������ ����ü�� ���� ����ϴ�"),
						42, inv);
			} else {
				itemset(ChatColor.GOLD + "���� ���� ����ǰ(���� ��� ȹ������ ����)", Material.HEART_OF_THE_SEA, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.OceanMonument.getOrDefault(p.getUniqueId(), 0) + "/1 ȹ����"),
						42, inv);
			}
	
			if (od.JungleTemple.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "���� ��� ����ǰ", jp, 0, 1,
						Arrays.asList("���ݷ°� ������ 4% �����մϴ�", "�ñر��� ���� ���ð��� 10% �����մϴ�"), 43, inv);
			} else {
				itemset(ChatColor.GOLD + "���� ��� ����ǰ(���� ��� ȹ������ ����)", Material.MOSSY_STONE_BRICKS, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.JungleTemple.getOrDefault(p.getUniqueId(), 0) + "/1 ȹ����"),
						43, inv);
			}
	
			if (od.PillagerOutpost.getOrDefault(p.getUniqueId(), 0) >= 2) {
				pe.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
				itemset(ChatColor.GOLD + "��Ż�� ���ʱ��� ����ǰ", pe, 0, 1, Arrays.asList("������ 6% �����մϴ�"), 44, inv);
			} else {
				itemset(ChatColor.GOLD + "��Ż�� ���ʱ��� ����ǰ(���� ��� ȹ������ ����)", pe, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.PillagerOutpost.getOrDefault(p.getUniqueId(), 0) + "/2 ȹ����"),
						44, inv);
			}
	
			if (od.DesertPyramid.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "�縷 �Ƕ�̵� ����ǰ", cs, 0, 1,
						Arrays.asList("���ݷ��� 2% �����մϴ�", "�Ϲݱ������ ���� ���ð��� 10% �����մϴ�"), 45, inv);
			} else {
				itemset(ChatColor.GOLD + "�縷 �Ƕ�̵� ����ǰ(���� ��� ȹ������ ����)", Material.CHISELED_SANDSTONE, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.DesertPyramid.getOrDefault(p.getUniqueId(), 0) + "/1 ȹ����"),
						45, inv);
			}
	
			if (od.Stronghold.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "�ٰ��� ����ǰ", ef, 0, 1,
						Arrays.asList("���ݷ°� ������ 3% �����մϴ�", "�Ϲݱ������ ���� ���ð��� 10% �����մϴ�"), 46, inv);
			} else {
				itemset(ChatColor.GOLD + "�ٰ��� ����ǰ(���� ��� ȹ������ ����)", Material.END_PORTAL_FRAME, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.Stronghold.getOrDefault(p.getUniqueId(), 0) + "/1 ȹ����"),
						46, inv);
			}
	
			if (od.SwampHut.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "���� ���θ�", my, 0, 1, Arrays.asList("������ 8% �����մϴ�", "���� �鿪�� �˴ϴ�"), 47, inv);
			} else {
				itemset(ChatColor.GOLD + "���� ���θ�(���� ��� ȹ������ ����)", Material.MYCELIUM, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.SwampHut.getOrDefault(p.getUniqueId(), 0) + "/2 ȹ����"),
						47, inv);
			}
	
			if (od.Village.getOrDefault(p.getUniqueId(), 0) >= 5) {
				itemset(ChatColor.GOLD + "���� ����ǰ", be, 0, 1,
						Arrays.asList("���ݷ°� ������ 2.5% �����մϴ�", "����ġ ȹ�淮�� 10% �����մϴ�", "�Ϲݱ������ ���� ���ð��� 10% �����մϴ�"), 48,
						inv);
			} else {
				itemset(ChatColor.GOLD + "���� ����ǰ(���� ��� ȹ������ ����)", Material.BELL, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.Village.getOrDefault(p.getUniqueId(), 0) + "/5 ȹ����"), 48,
						inv);
			}
	
			if (od.RuinedPortal.getOrDefault(p.getUniqueId(), 0) >= 4) {
				itemset(ChatColor.GOLD + "������ ������ ����ǰ", co, 0, 1,
						Arrays.asList("���ݷ°� ������ 5% �����մϴ�", "�ñر��� ���� ���ð��� 10% �����մϴ�"), 49, inv);
			} else {
				itemset(ChatColor.GOLD + "������ ������ ����ǰ(���� ��� ȹ������ ����)", Material.CRYING_OBSIDIAN, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.RuinedPortal.getOrDefault(p.getUniqueId(), 0) + "/4 ȹ����"),
						49, inv);
			}
	
			if (od.NetherFortress.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "�״� ��� ����ǰ", nq, 0, 1, Arrays.asList("���ݷ°� ������ 8% �����մϴ�"), 50, inv);
			} else {
				itemset(ChatColor.GOLD + "�״� ��� ����ǰ(���� ��� ȹ������ ����)", Material.NETHER_QUARTZ_ORE, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.NetherFortress.getOrDefault(p.getUniqueId(), 0) + "/2 ȹ����"),
						50, inv);
			}
	
			if (od.BastionRemnant.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "���� ���� ����ǰ", ba, 0, 1,
						Arrays.asList("���ݷ°� ������ 6% �����մϴ�", "�Ϲݱ������ ���� ���ð��� 6% �����մϴ�"), 51, inv);
			} else {
				itemset(ChatColor.GOLD + "���� ���� ����ǰ(���� ��� ȹ������ ����)", Material.BASALT, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.BastionRemnant.getOrDefault(p.getUniqueId(), 0) + "/2 ȹ����"),
						51, inv);
			}
	
			if (od.EnderDragon.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "���� �巡�� ����ǰ", ms, 0, 1,
						Arrays.asList(ChatColor.GOLD + "�Ϲݱ������ ���� ���ð��� 10% �����մϴ�", "�ñر��� ���� ���ð��� 10% �����մϴ�", ""),
						52, inv);
			} else {
				itemset(ChatColor.GOLD + "���� �巡�� ����ǰ(���� ��� ȹ������ ����)", Material.DRAGON_HEAD, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.EnderDragon.getOrDefault(p.getUniqueId(), 0) + "/1 ȹ����"),
						52, inv);
			}
	
			if (od.EndCity.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "���� ���� ����ǰ", ed, 0, 1, Arrays.asList("���ݷ°� ������ 11% �����մϴ�"), 53, inv);
			} else {
				itemset(ChatColor.GOLD + "���� ���� ����ǰ(���� ��� ȹ������ ����)", Material.END_STONE_BRICKS, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.EndCity.getOrDefault(p.getUniqueId(), 0) + "/1 ȹ����"), 53,
						inv);
			}
		} else {
			itemset(ChatColor.GOLD + "Dumpster", new ItemStack(Material.COMPOSTER), 0, 1, Arrays.asList("Open Dumpster If You Click"),
					30, inv);
			itemset(ChatColor.GOLD + "Artifact", new ItemStack(Material.AMETHYST_SHARD), 0, 1, Arrays.asList("Open Artifact management GUI If You Click"),
					31, inv);
			itemset(ChatColor.GOLD + "Shulker Bag", new ItemStack(Material.SHULKER_BOX), 0, 1, Arrays.asList("You can use Shulker Box as bag","By [Sneaking + Placing]"),
					32, inv);
			itemset(ChatColor.GOLD + "Backpack", new ItemStack(Material.BARREL), 0, 1, Arrays.asList("Open Backpack If You Click"),
					33, inv);
			
			if (od.Mineshaft.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "Trophy Of MineShaft", ms, 0, 1,
						Arrays.asList("Increase Damage & Armor 2%", "Increase Amount of Obtaining Experience 5%"), 36,
						inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of MineShaft(Not Obtained All Yet)", Material.POWERED_RAIL, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.Mineshaft.getOrDefault(p.getUniqueId(), 0) + "/2 Obtained"),
						36, inv);
			}
	
			if (od.BuriedTreasure.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "Trophy Of BuriedTreasure", mp, 0, 1,
						Arrays.asList("Increase Damage & Armor 3%", "Increase Amount of Obtaining Experience 5%"), 37,
						inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of BuriedTreasure(Not Obtained All Yet)", Material.SPAWNER, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.BuriedTreasure.getOrDefault(p.getUniqueId(), 0) + "/2 ȹ����"),
						37, inv);
			}
	
			if (od.Igloo.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "Trophy Of Igloo", sb, 0, 1, Arrays.asList("Increase Damage 2.5%",
						"Immune To Slow", "Increase Amount of Obtaining Experience 5%"), 38, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of Igloo(Not Obtained All Yet)", Material.POWDER_SNOW_BUCKET, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.Igloo.getOrDefault(p.getUniqueId(), 0) + "/2 Obtained"),
						38, inv);
			}
	
			if (od.OceanRuins.getOrDefault(p.getUniqueId(), 0) >= 3) {
				itemset(ChatColor.GOLD + "Trophy Of OceanRuins", pc, 0, 1,
						Arrays.asList("Increase Armor 5%", "Immune To Drowning"), 39, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of OceanRuins(Not Obtained All Yet)", Material.PRISMARINE_CRYSTALS, 0,
						1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.OceanRuins.getOrDefault(p.getUniqueId(), 0) + "/3 Obtained"),
						39, inv);
			}
	
			if (od.WoodlandMansion.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "Trophy Of WoodlandMansion", ds, 0, 1,
						Arrays.asList("Increase Damage 3%", "Increase Armor 5%"), 40, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of WoodlandMansion(Not Obtained All Yet)", Material.DARK_OAK_SIGN, 0,
						1, Arrays.asList(ChatColor.GOLD + "" + od.WoodlandMansion.getOrDefault(p.getUniqueId(), 0)
								+ "/3 Obtained"),
						40, inv);
			}
	
			if (od.Shipwreck.getOrDefault(p.getUniqueId(), 0) >= 3) {
				itemset(ChatColor.GOLD + "Trophy Of Shipwreck", bb, 0, 1,
						Arrays.asList("Increase Damage & Armor 3%", "Increase Amount of Obtaining Experience 5%"), 41,
						inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of Shipwreck(Not Obtained All Yet)", Material.BIRCH_BOAT, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.Shipwreck.getOrDefault(p.getUniqueId(), 0) + "/3 Obtained"),
						41, inv);
			}
	
			if (od.OceanMonument.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "Trophy Of OceanMonument", hs, 0, 1,
						Arrays.asList("Increase Damage & Armor 3%", "Get Conduit Power When Swim"), 42, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of OceanMonument(Not Obtained All Yet)", Material.HEART_OF_THE_SEA, 0,
						1, Arrays.asList(ChatColor.GOLD + "" + od.OceanMonument.getOrDefault(p.getUniqueId(), 0)
								+ "/1 Obtained"),
						42, inv);
			}
	
			if (od.JungleTemple.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "Trophy Of JungleTemple", jp, 0, 1,
						Arrays.asList("Increase Damage & Armor 4%", "Decrease Ult's Cooldown 10%"), 43, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of JungleTemple(Not Obtained All Yet)", Material.MOSSY_STONE_BRICKS, 0,
						1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.JungleTemple.getOrDefault(p.getUniqueId(), 0) + "/1 Obtained"),
						43, inv);
			}
	
			if (od.PillagerOutpost.getOrDefault(p.getUniqueId(), 0) >= 2) {
				pe.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
				itemset(ChatColor.GOLD + "Trophy Of PillagerOutpost", pe, 0, 1, Arrays.asList("Increase Armor 6%"), 44,
						inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of PillagerOutpost(Not Obtained All Yet)", pe, 0, 1, Arrays.asList(
						ChatColor.GOLD + "" + od.PillagerOutpost.getOrDefault(p.getUniqueId(), 0) + "/2 Obtained"), 44,
						inv);
			}
	
			if (od.DesertPyramid.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "Trophy Of DesertPyramid", cs, 0, 1,
						Arrays.asList("Increase Damage 2%", "Decrease Normal Skills' Cooldown 10%"), 45, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of DesertPyramid(Not Obtained All Yet)", Material.CHISELED_SANDSTONE,
						0, 1, Arrays.asList(ChatColor.GOLD + "" + od.DesertPyramid.getOrDefault(p.getUniqueId(), 0)
								+ "/1 Obtained"),
						45, inv);
			}
	
			if (od.Stronghold.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "Trophy Of Stronghold", ef, 0, 1,
						Arrays.asList("Increase Damage & Armor 3%", "Decrease Normal Skills' Cooldown 10%"), 46, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of Stronghold(Not Obtained All Yet)", Material.END_PORTAL_FRAME, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.Stronghold.getOrDefault(p.getUniqueId(), 0) + "/1 Obtained"),
						46, inv);
			}
	
			if (od.SwampHut.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "Trophy Of SwampHut", my, 0, 1,
						Arrays.asList("Increase Armor 8%", "Immune To Poison"), 47, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of SwampHut(Not Obtained All Yet)", Material.MYCELIUM, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.SwampHut.getOrDefault(p.getUniqueId(), 0) + "/2 Obtained"),
						47, inv);
			}
	
			if (od.Village.getOrDefault(p.getUniqueId(), 0) >= 5) {
				itemset(ChatColor.GOLD + "Trophy Of Village", be, 0, 1, Arrays.asList("Increase Damage & Armor 2.5%",
						"Increase Amount of Obtaining Experience 10%", "Decrease Normal Skills' Cooldown 10%"), 48,
						inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of Village(Not Obtained All Yet)", Material.BELL, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.Village.getOrDefault(p.getUniqueId(), 0) + "/5 Obtained"),
						48, inv);
			}
	
			if (od.RuinedPortal.getOrDefault(p.getUniqueId(), 0) >= 4) {
				itemset(ChatColor.GOLD + "Trophy Of RuinedPortal", co, 0, 1,
						Arrays.asList("Increase Damage & Armor 5%", "Decrease Ult's Cooldown 10%"), 49, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of RuinedPortal(Not Obtained All Yet)", Material.CRYING_OBSIDIAN, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.RuinedPortal.getOrDefault(p.getUniqueId(), 0) + "/4 Obtained"),
						49, inv);
			}
	
			if (od.NetherFortress.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "Trophy Of NetherFortress", nq, 0, 1,
						Arrays.asList("Increase Damage & Armor 8%"), 50, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of NetherFortress(Not Obtained All Yet)", Material.NETHER_QUARTZ_ORE,
						0, 1, Arrays.asList(ChatColor.GOLD + "" + od.NetherFortress.getOrDefault(p.getUniqueId(), 0)
								+ "/2 Obtained"),
						50, inv);
			}
	
			if (od.BastionRemnant.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "Trophy Of BastionRemnant", ba, 0, 1,
						Arrays.asList("Increase Damage & Armor 6%", "Decrease Normal Skills' Cooldown 6%"), 51, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of BastionRemnant(Not Obtained All Yet)", Material.BASALT, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.BastionRemnant.getOrDefault(p.getUniqueId(), 0)
								+ "/2 Obtained"),
						51, inv);
			}
	
			if (od.EnderDragon.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "Trophy Of EnderDragon", ms, 0, 1,
						Arrays.asList(ChatColor.GOLD + "Decrease Normal Skills' Cooldown 10%",
								"Decrease Ult's Cooldown 10%", ""),
						52, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of EnderDragon(Not Obtained All Yet)", Material.DRAGON_HEAD, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.EnderDragon.getOrDefault(p.getUniqueId(), 0) + "/1 ȹ����"),
						52, inv);
			}
	
			if (od.EndCity.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "Trophy Of EndCity", ed, 0, 1, Arrays.asList("Increase Damage & Armor 11%"),
						53, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of EndCity(Not Obtained All Yet)", Material.END_STONE_BRICKS, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.EndCity.getOrDefault(p.getUniqueId(), 0) + "/1 Obtained"),
						53, inv);
			}
	
		}
	}


	public Obtained(HashMap<UUID, Integer> Mineshaft, HashMap<UUID, Integer> BuriedTreasure,
			HashMap<UUID, Integer> Igloo, HashMap<UUID, Integer> OceanRuins, HashMap<UUID, Integer> WoodlandMansion,
			HashMap<UUID, Integer> Shipwreck, HashMap<UUID, Integer> OceanMonument, HashMap<UUID, Integer> JungleTemple,
			HashMap<UUID, Integer> PillagerOutpost, HashMap<UUID, Integer> DesertPyramid,
			HashMap<UUID, Integer> Stronghold, HashMap<UUID, Integer> SwampHut, HashMap<UUID, Integer> Village,
			HashMap<UUID, Integer> RuinedPortal, HashMap<UUID, Integer> NetherFortress,
			HashMap<UUID, Integer> BastionRemnant, HashMap<UUID, Integer> EndCity, HashMap<UUID, Integer> EnderDragon) {
		this.Mineshaft = Mineshaft;
		this.BuriedTreasure = BuriedTreasure;
		this.Igloo = Igloo;
		this.OceanRuins = OceanRuins;
		this.WoodlandMansion = WoodlandMansion;
		this.Shipwreck = Shipwreck;
		this.OceanMonument = OceanMonument;
		this.JungleTemple = JungleTemple;
		this.PillagerOutpost = PillagerOutpost;
		this.DesertPyramid = DesertPyramid;
		this.Stronghold = Stronghold;
		this.SwampHut = SwampHut;
		this.Village = Village;
		this.RuinedPortal = RuinedPortal;
		this.NetherFortress = NetherFortress;
		this.BastionRemnant = BastionRemnant;
		this.EndCity = EndCity;
		this.EnderDragon = EnderDragon;
	}

	public Obtained(Obtained loadedData) {
		this.Mineshaft = loadedData.Mineshaft;
		this.BuriedTreasure = loadedData.BuriedTreasure;
		this.Igloo = loadedData.Igloo;
		this.OceanRuins = loadedData.OceanRuins;
		this.WoodlandMansion = loadedData.WoodlandMansion;
		this.Shipwreck = loadedData.Shipwreck;
		this.OceanMonument = loadedData.OceanMonument;
		this.JungleTemple = loadedData.JungleTemple;
		this.PillagerOutpost = loadedData.PillagerOutpost;
		this.DesertPyramid = loadedData.DesertPyramid;
		this.Stronghold = loadedData.Stronghold;
		this.SwampHut = loadedData.SwampHut;
		this.Village = loadedData.Village;
		this.RuinedPortal = loadedData.RuinedPortal;
		this.NetherFortress = loadedData.NetherFortress;
		this.BastionRemnant = loadedData.BastionRemnant;
		this.EndCity = loadedData.EndCity;
		this.EnderDragon = loadedData.EnderDragon;
	}

	public Obtained saveData(String filePath) {
		try {
			BukkitObjectOutputStream out = new BukkitObjectOutputStream(
					new GZIPOutputStream(new FileOutputStream(filePath)));
			out.writeObject(this);
			out.close();
			return this;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return this;
		}
	}

	public static Obtained loadData(String filePath) {
		try {
			BukkitObjectInputStream in = new BukkitObjectInputStream(
					new GZIPInputStream(new FileInputStream(filePath)));
			Obtained data = (Obtained) in.readObject();
			in.close();
			return data;
		} catch (ClassNotFoundException | IOException | NullPointerException e) {
			// TODO Auto-generated catch block

			String path = new File("").getAbsolutePath();
			Obtained data = new Obtained(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(),
					new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(),
					new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(),
					new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(),
					new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(),
					new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>())
							.saveData(path + "/plugins/RPGskills/Obtained.data");

			e.printStackTrace();
			return data;
		}
	}

	public Obtained getObtained() {
		String path = new File("").getAbsolutePath();
		Obtained data = new Obtained(Obtained.loadData(path + "/plugins/RPGskills/Obtained.data"));
		return data;
	}

}
