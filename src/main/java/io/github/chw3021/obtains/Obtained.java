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
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityToggleSwimEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class Obtained implements Serializable, Listener {

	/**
	 * 
	 */
	private static transient final long serialVersionUID = -8734003197215830656L;
	/**
	 * 
	 */
	private String path = new File("").getAbsolutePath();
	public static HashMap<UUID, Double> ucd = new HashMap<UUID, Double>();
	public static HashMap<UUID, Double> ncd = new HashMap<UUID, Double>();
	public static HashMap<UUID, Double> Qdamage = new HashMap<UUID, Double>();
	public static HashMap<UUID, Double> Qarmor = new HashMap<UUID, Double>();
	
	public static HashMap<UUID, Double> Qexp = new HashMap<UUID, Double>();
	
	public static HashMap<UUID, Boolean> oceanRuined = new HashMap<UUID, Boolean>();
	public static HashMap<UUID, Boolean> monument = new HashMap<UUID, Boolean>();
	public static HashMap<UUID, Boolean> ancient = new HashMap<UUID, Boolean>();
	public static HashMap<UUID, Boolean> igloo = new HashMap<UUID, Boolean>();
	private static Obtained d;
	
	public final HashMap<UUID, Integer> Mineshaft;//
	public final HashMap<UUID, Integer> BuriedTreasure;//
	public final HashMap<UUID, Integer> Igloo;//
	public final HashMap<UUID, Integer> OceanRuins;//
	public final HashMap<UUID, Integer> WoodlandMansion;//
	public final HashMap<UUID, Integer> Shipwreck;//
	public final HashMap<UUID, Integer> OceanMonument;//
	public final HashMap<UUID, Integer> JungleTemple;//
	public final HashMap<UUID, Integer> PillagerOutpost;//
	public final HashMap<UUID, Integer> DesertPyramid;//
	public final HashMap<UUID, Integer> RuinedPortal;//
	public final HashMap<UUID, Integer> Stronghold;//
	public final HashMap<UUID, Integer> AncientCity;//
	public final HashMap<UUID, Integer> Village;//
	public final HashMap<UUID, Integer> NetherFortress;//
	public final HashMap<UUID, Integer> BastionRemnant;//
	public final HashMap<UUID, Integer> EndCity;//
	public final HashMap<UUID, Integer> EnderDragon;
	// Can be used for saving

	private static final Obtained instance = new Obtained();
	public static Obtained getInstance()
	{
		return instance;
	}

    public Obtained() {
        this.Mineshaft = new HashMap<>();
        this.BuriedTreasure = new HashMap<>();
        this.Igloo = new HashMap<>();
        this.OceanRuins = new HashMap<>();
        this.WoodlandMansion = new HashMap<>();
        this.Shipwreck = new HashMap<>();
        this.OceanMonument = new HashMap<>();
        this.JungleTemple = new HashMap<>();
        this.PillagerOutpost = new HashMap<>();
        this.DesertPyramid = new HashMap<>();
        this.RuinedPortal = new HashMap<>();
        this.Stronghold = new HashMap<>();
        this.AncientCity = new HashMap<>();
        this.Village = new HashMap<>();
        this.NetherFortress = new HashMap<>();
        this.BastionRemnant = new HashMap<>();
        this.EndCity = new HashMap<>();
        this.EnderDragon = new HashMap<>();
    }


    @EventHandler
	public void er(PluginEnableEvent ev) 
	{
		d = loadData(path +"/plugins/RPGskills/Obtained.data");
		Bukkit.getOnlinePlayers().forEach(p ->{
			double ultc = 1 * (1 - (d.JungleTemple.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (d.RuinedPortal.getOrDefault(p.getUniqueId(), 0)>=4 ? 0.1:0))* (1 - (d.EnderDragon.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0));
			double norc = 1 * (1 - (d.DesertPyramid.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (d.Stronghold.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (d.Village.getOrDefault(p.getUniqueId(), 0)>=5 ? 0.1:0)) * (1 - (d.BastionRemnant.getOrDefault(p.getUniqueId(), 0)>=2 ? 0.06:0));
			ucd.put(p.getUniqueId(), (ultc));
			ncd.put(p.getUniqueId(), norc);
			damageAndArmor(p);
			
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
				d = loadData(path +"/plugins/RPGskills/Obtained.data");
				double ultc = 1 * (1 - (d.JungleTemple.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (d.RuinedPortal.getOrDefault(p.getUniqueId(), 0)>=4 ? 0.1:0))* (1 - (d.EnderDragon.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0));
				double norc = 1 * (1 - (d.DesertPyramid.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (d.Stronghold.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (d.Village.getOrDefault(p.getUniqueId(), 0)>=5 ? 0.1:0)) * (1 - (d.BastionRemnant.getOrDefault(p.getUniqueId(), 0)>=2 ? 0.06:0));
				ucd.put(p.getUniqueId(), (ultc));
				ncd.put(p.getUniqueId(), norc);
				damageAndArmor(p);
			}
			
		}
		if(ChatColor.stripColor(e.getView().getTitle()).contains("skill"))
		{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			p.setCooldown(Material.STRUCTURE_VOID, 1);
			d = loadData(path +"/plugins/RPGskills/Obtained.data");
			double ultc = 1 * (1 - (d.JungleTemple.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (d.RuinedPortal.getOrDefault(p.getUniqueId(), 0)>=4 ? 0.1:0))* (1 - (d.EnderDragon.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0));
			double norc = 1 * (1 - (d.DesertPyramid.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (d.Stronghold.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (d.Village.getOrDefault(p.getUniqueId(), 0)>=5 ? 0.1:0)) * (1 - (d.BastionRemnant.getOrDefault(p.getUniqueId(), 0)>=2 ? 0.06:0));
			ucd.put(p.getUniqueId(), (ultc));
			ncd.put(p.getUniqueId(), norc);
			damageAndArmor(p);
			
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == null
					|| !e.getCurrentItem().hasItemMeta()) {
				return;
			} else if(e.getCurrentItem().getItemMeta().getItemName().equals("RpgSkillsUtilities")){
				p.closeInventory();
				p.openInventory(itemset(p));
			}
			
		}
	}


    @EventHandler
	public void nepreventer(PlayerJoinEvent ev) 
	{
		Player p = ev.getPlayer();
		d = loadData(path +"/plugins/RPGskills/Obtained.data");
		double ultc = 1 * (1 - (d.JungleTemple.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (d.RuinedPortal.getOrDefault(p.getUniqueId(), 0)>=4 ? 0.1:0))* (1 - (d.EnderDragon.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0));
		double norc = 1 * (1 - (d.DesertPyramid.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (d.Stronghold.getOrDefault(p.getUniqueId(), 0)>=1 ? 0.1:0)) * (1 - (d.Village.getOrDefault(p.getUniqueId(), 0)>=5 ? 0.1:0)) * (1 - (d.BastionRemnant.getOrDefault(p.getUniqueId(), 0)>=2 ? 0.06:0));
		ucd.put(p.getUniqueId(), (ultc));
		ncd.put(p.getUniqueId(), norc);
		damageAndArmor(p);
	}

    
    //{@link io.github.chw3021.monsters.MobDam.PlayerArmor(EntityDamageEvent d)}
    final private static void damageAndArmor(Player p) {
        String path = new File("").getAbsolutePath();
        Obtained d = new Obtained(Obtained.loadData(path + "/plugins/RPGskills/Obtained.data"));
        double attackIncrease = 0;
        double defenseIncrease = 0;
        double expIncrease = 0;

        if (d.Mineshaft.getOrDefault(p.getUniqueId(), 0) >= 2) {  
            attackIncrease += 0.02;
            defenseIncrease += 0.02;
            expIncrease += 0.05;
        }
        if (d.BuriedTreasure.getOrDefault(p.getUniqueId(), 0) >= 2) {
            attackIncrease += 0.03;
            expIncrease += 0.05;
        }
        if (d.Igloo.getOrDefault(p.getUniqueId(), 0) >= 2) {
            attackIncrease += 0.025;
            defenseIncrease += 0.025;
            expIncrease += 0.05;
            igloo.put(p.getUniqueId(), true);
        }
        if (d.OceanRuins.getOrDefault(p.getUniqueId(), 0) >= 3) {
            defenseIncrease += 0.05;
            oceanRuined.put(p.getUniqueId(), true);
        }
        if (d.WoodlandMansion.getOrDefault(p.getUniqueId(), 0) >= 1) {
            attackIncrease += 0.03;
            defenseIncrease += 0.05;
        }
        if (d.Shipwreck.getOrDefault(p.getUniqueId(), 0) >= 3) {
            attackIncrease += 0.03;
            defenseIncrease += 0.03;
            expIncrease += 0.05;
        }
        if (d.OceanMonument.getOrDefault(p.getUniqueId(), 0) >= 1) {
            attackIncrease += 0.03;
            defenseIncrease += 0.03;
            monument.put(p.getUniqueId(), true);
        }
        if (d.JungleTemple.getOrDefault(p.getUniqueId(), 0) >= 1) {
            attackIncrease += 0.04;
            defenseIncrease += 0.04;
        }
        if (d.PillagerOutpost.getOrDefault(p.getUniqueId(), 0) >= 2) {
            defenseIncrease += 0.06;
        }
        if (d.DesertPyramid.getOrDefault(p.getUniqueId(), 0) >= 1) {
            attackIncrease += 0.02;
        }
        if (d.Stronghold.getOrDefault(p.getUniqueId(), 0) >= 1) {
            attackIncrease += 0.05;
        }
        if (d.AncientCity.getOrDefault(p.getUniqueId(), 0) >= 2) {
            defenseIncrease += 0.08;
            ancient.put(p.getUniqueId(), true);
        }
        if (d.Village.getOrDefault(p.getUniqueId(), 0) >= 5) {
            attackIncrease += 0.025;
            defenseIncrease += 0.025;
            expIncrease += 0.1;
        }
        if (d.RuinedPortal.getOrDefault(p.getUniqueId(), 0) >= 4) {
            attackIncrease += 0.05;
            defenseIncrease += 0.05;
        }
        if (d.NetherFortress.getOrDefault(p.getUniqueId(), 0) >= 2) {
            attackIncrease += 0.08;
            defenseIncrease += 0.08;
        }
        if (d.BastionRemnant.getOrDefault(p.getUniqueId(), 0) >= 2) {
            attackIncrease += 0.06;
            defenseIncrease += 0.06;
        }
        if (d.EnderDragon.getOrDefault(p.getUniqueId(), 0) >= 1) {
            attackIncrease += 0.1;
        }
        if (d.EndCity.getOrDefault(p.getUniqueId(), 0) >= 1) {
            attackIncrease += 0.11;
            defenseIncrease += 0.11;
        }

        // 최종 공격력과 방어력에 증가치 적용
        Qdamage.put(p.getUniqueId(), attackIncrease);
        Qarmor.put(p.getUniqueId(), defenseIncrease);
        Qexp.put(p.getUniqueId(), expIncrease);
    }


	@EventHandler
	public void Expup (PlayerExpChangeEvent e)
	{
		Player p = e.getPlayer();
		
		if(Qexp.containsKey(p.getUniqueId())) {
			e.setAmount((int) (e.getAmount()*(1+Qexp.get(p.getUniqueId()))));
		}
		
	}

	@EventHandler
	public void effectRemove(EntityPotionEffectEvent d)
	{
		if(!(d.getEntity() instanceof Player)) {return;
		}

		else if(d.getEntity() instanceof Player) {
			Player p = (Player)d.getEntity();
			if(ancient.containsKey(p.getUniqueId()))
			{
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						if(p.hasPotionEffect(PotionEffectType.BLINDNESS)) {
							p.removePotionEffect(PotionEffectType.BLINDNESS);
						}
						if(p.hasPotionEffect(PotionEffectType.DARKNESS)) {
							p.removePotionEffect(PotionEffectType.DARKNESS);
						}
					}
				}, 5);
			}
			if(igloo.containsKey(p.getUniqueId()))
			{
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						if(p.hasPotionEffect(PotionEffectType.SLOWNESS)) {
							p.removePotionEffect(PotionEffectType.SLOWNESS);
						}
						if(p.hasPotionEffect(PotionEffectType.MINING_FATIGUE)) {
							p.removePotionEffect(PotionEffectType.MINING_FATIGUE);
						}
					}
				}, 5);
			}
		}
	}

	@EventHandler
	public void Swimer(EntityToggleSwimEvent ev) 
	{
		if(ev.getEntity() instanceof Player) {
			Player p = (Player) ev.getEntity();
			if(oceanRuined.containsKey(p.getUniqueId())) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 2000, 30, false, false));
			}
			if(monument.containsKey(p.getUniqueId())) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 2000, 3, false, false));
			}
		}
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
		HashMap<UUID, Integer> AncientCity = d.AncientCity;
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f == 11) {
			if (AncientCity.getOrDefault(p.getUniqueId(), 0) < 2) {
				AncientCity.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				AncientCity.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
		damageAndArmor(p);
		
		
		new Obtained(Mineshaft, BuriedTreasure, Igloo, OceanRuins, WoodlandMansion, Shipwreck, OceanMonument,
				JungleTemple, PillagerOutpost, DesertPyramid, Stronghold, AncientCity, Village, RuinedPortal,
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
		HashMap<UUID, Integer> AncientCity = d.AncientCity;
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("pilla")) {
			if (PillagerOutpost.getOrDefault(p.getUniqueId(), 0) < 2) {
				PillagerOutpost.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				PillagerOutpost.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
	    		}
	    		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Already Obtained All Trophies Of This Structure").create());
	    		}
			}
		} else if (f.contains("ancient")) {
			if (AncientCity.getOrDefault(p.getUniqueId(), 0) < 2) {
				AncientCity.computeIfPresent(p.getUniqueId(), (k, v) -> v + add);
				AncientCity.putIfAbsent(p.getUniqueId(), add);
			}
			else {
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "해당 구조물의 전리품은 모두 획득했습니다").create());
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

		damageAndArmor(p);
		
		new Obtained(Mineshaft, BuriedTreasure, Igloo, OceanRuins, WoodlandMansion, Shipwreck, OceanMonument,
				JungleTemple, PillagerOutpost, DesertPyramid, Stronghold, AncientCity, Village, RuinedPortal,
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
		items.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc+3, item);
	}


	public static void itemsetbasic(String display, ItemStack is, int data, int stack, List<String> Lore, int loc,
			Inventory inv) {
		ItemStack item = is;
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
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
		items.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc+3, item);
	}


	public static void itemset(Player p, Inventory inv) {

		ItemStack is = new ItemStack(Material.VAULT);
		ItemMeta im = is.getItemMeta();
		im.setItemName("RpgSkillsUtilities");
		is.setItemMeta(im);
		
		if (p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemsetbasic(ChatColor.GOLD + "기능들 및 전리품", is, 0, 1, Arrays.asList("여러가지 기능과 전리품들"),
				31, inv);
		}
		else {
			itemsetbasic(ChatColor.GOLD + "Utilites", is, 0, 1,
					Arrays.asList("Functions And Trophies"), 31, inv);
		}
	}

	public Inventory itemset(Player p) {
		String path = new File("").getAbsolutePath();
		Obtained od = new Obtained(loadData(path + "/plugins/RPGskills/Obtained.data"));
		Inventory inv = Bukkit.createInventory(null, 27, "Utilites");
		ItemStack mp = new ItemStack(Material.CHEST);
		mp.addUnsafeEnchantment(Enchantment.SMITE, 1);
	
		ItemStack ms = new ItemStack(Material.POWERED_RAIL);
		ms.addUnsafeEnchantment(Enchantment.SMITE, 1);
		ItemStack zh = new ItemStack(Material.ZOMBIE_HEAD);
		zh.addUnsafeEnchantment(Enchantment.SMITE, 1);
		ItemStack sb = new ItemStack(Material.POWDER_SNOW_BUCKET);
		sb.addUnsafeEnchantment(Enchantment.SMITE, 1);
		ItemStack pc = new ItemStack(Material.PRISMARINE_CRYSTALS);
		pc.addUnsafeEnchantment(Enchantment.SMITE, 1);
		ItemStack ds = new ItemStack(Material.DARK_OAK_SIGN);
		ds.addUnsafeEnchantment(Enchantment.SMITE, 1);
		ItemStack bb = new ItemStack(Material.BIRCH_BOAT);
		ds.addUnsafeEnchantment(Enchantment.SMITE, 1);
		ItemStack hs = new ItemStack(Material.HEART_OF_THE_SEA);
		hs.addUnsafeEnchantment(Enchantment.SMITE, 1);
		ItemStack jp = new ItemStack(Material.MOSSY_STONE_BRICKS);
		jp.addUnsafeEnchantment(Enchantment.SMITE, 1);
		ItemStack pe = new ItemStack(Material.CROSSBOW);
		ItemStack cs = new ItemStack(Material.CHISELED_SANDSTONE);
		cs.addUnsafeEnchantment(Enchantment.SMITE, 1);
		ItemStack ef = new ItemStack(Material.END_PORTAL_FRAME);
		ef.addUnsafeEnchantment(Enchantment.SMITE, 1);
		ItemStack my = new ItemStack(Material.MYCELIUM);
		my.addUnsafeEnchantment(Enchantment.SMITE, 1);
		ItemStack be = new ItemStack(Material.BELL);
		be.addUnsafeEnchantment(Enchantment.SMITE, 1);
		ItemStack co = new ItemStack(Material.CRYING_OBSIDIAN);
		co.addUnsafeEnchantment(Enchantment.SMITE, 1);
		ItemStack nq = new ItemStack(Material.NETHER_QUARTZ_ORE);
		nq.addUnsafeEnchantment(Enchantment.SMITE, 1);
		ItemStack ba = new ItemStack(Material.BASALT);
		ba.addUnsafeEnchantment(Enchantment.SMITE, 1);
		ItemStack ed = new ItemStack(Material.END_STONE_BRICKS);
		ed.addUnsafeEnchantment(Enchantment.SMITE, 1);
		ItemStack ph = new ItemStack(Material.DRAGON_HEAD);
		ph.addUnsafeEnchantment(Enchantment.SMITE, 1);
	
		if (p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemsetbasic(ChatColor.GOLD + "쓰레기통", new ItemStack(Material.COMPOSTER), 0, 1, Arrays.asList("클릭시 쓰레기통을 엽니다"),
				0, inv);
			itemsetbasic(ChatColor.GOLD + "보조장비 관리", new ItemStack(Material.AMETHYST_SHARD), 0, 1, Arrays.asList("클릭시 보조장비 관리 창을 엽니다"),
				1, inv);
			itemsetbasic(ChatColor.GOLD + "셜커 가방", new ItemStack(Material.SHULKER_BOX), 0, 1, Arrays.asList("[웅크리기 + 설치]로 셜커상자를 가방처럼 사용할수 있습니다"),
				2, inv);
			itemsetbasic(ChatColor.GOLD + "배낭", new ItemStack(Material.BARREL), 0, 1, Arrays.asList("클릭시 배낭을 엽니다"),
				3, inv);
			itemsetbasic(ChatColor.GOLD + "기술", new ItemStack(Material.BORDURE_INDENTED_BANNER_PATTERN), 0, 1, Arrays.asList("Open Backpack If You Click"),
					8, inv);
			
			
			if (od.Mineshaft.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "페광 전리품", ms, 0, 1, Arrays.asList("공격력과 방어력이 2% 증가합니다", "경험치 획득량이 5% 증가합니다"),
					6, inv);
			} else {
				itemset(ChatColor.GOLD + "페광 전리품(아직 모두 획득하지 못함)", Material.POWERED_RAIL, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.Mineshaft.getOrDefault(p.getUniqueId(), 0) + "/2 획득함"),
					6, inv);
			}


			if (od.BuriedTreasure.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "파묻힌 보물 전리품", mp, 0, 1,
						Arrays.asList("공격력과 방어력이 3% 증가합니다", "경험치 획득량이 5% 증가합니다"), 7,
						inv);
			} else {
				itemset(ChatColor.GOLD + "파묻힌 보물 전리품(아직 모두 획득하지 못함)", Material.SPAWNER, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.BuriedTreasure.getOrDefault(p.getUniqueId(), 0) + "/2 획득함"),
					7, inv);
			}
	
			if (od.Igloo.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "이글루 전리품", sb, 0, 1,
						Arrays.asList("공격력과 방어력이 2.5% 증가합니다", "경험치 획득량이 5% 증가합니다", "둔화에 면역이 됩니다"), 8, inv);
			} else {
				itemset(ChatColor.GOLD + "이글루 전리품(아직 모두 획득하지 못함)", Material.POWDER_SNOW_BUCKET, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.Igloo.getOrDefault(p.getUniqueId(), 0) + "/2 획득함"), 8,
						inv);
			}
	
			if (od.OceanRuins.getOrDefault(p.getUniqueId(), 0) >= 3) {
				itemset(ChatColor.GOLD + "해저 폐허 전리품", pc, 0, 1, Arrays.asList("방어력이 5% 증가합니다", "익사에 면역이 됩니다"), 9, inv);
			} else {
				itemset(ChatColor.GOLD + "해저 폐허 전리품(아직 모두 획득하지 못함)", Material.PRISMARINE_CRYSTALS, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.OceanRuins.getOrDefault(p.getUniqueId(), 0) + "/3 획득함"),
					9, inv);
			}
	
			if (od.WoodlandMansion.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "삼림 대저택 전리품", ds, 0, 1, Arrays.asList("공격력이 3% 증가합니다", "방어력이 5% 증가합니다"), 10,
						inv);
			} else {
				itemset(ChatColor.GOLD + "삼림 대저택 전리품(아직 모두 획득하지 못함)", Material.DARK_OAK_SIGN, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.WoodlandMansion.getOrDefault(p.getUniqueId(), 0) + "/3 획득함"),
					10, inv);
			}
	
			if (od.Shipwreck.getOrDefault(p.getUniqueId(), 0) >= 3) {
				itemset(ChatColor.GOLD + "난파선 전리품", bb, 0, 1, Arrays.asList("공격력과 방어력이 3% 증가합니다", "경험치 획득량이 5% 증가합니다"),
					11, inv);
			} else {
				itemset(ChatColor.GOLD + "난파선 전리품(아직 모두 획득하지 못함)", Material.BIRCH_BOAT, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.Shipwreck.getOrDefault(p.getUniqueId(), 0) + "/3 획득함"),
					11, inv);
			}
	
			if (od.OceanMonument.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "해저 유적 전리품", hs, 0, 1, Arrays.asList("공격력과 방어력이 3% 증가합니다", "수영시 전도체의 힘을 얻습니다"),
					12, inv);
			} else {
				itemset(ChatColor.GOLD + "해저 유적 전리품(아직 모두 획득하지 못함)", Material.HEART_OF_THE_SEA, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.OceanMonument.getOrDefault(p.getUniqueId(), 0) + "/1 획득함"),
					12, inv);
			}
	
			if (od.JungleTemple.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "정글 사원 전리품", jp, 0, 1,
						Arrays.asList("공격력과 방어력이 4% 증가합니다", "궁극기의 재사용 대기시간이 10% 감소합니다"), 13, inv);
			} else {
				itemset(ChatColor.GOLD + "정글 사원 전리품(아직 모두 획득하지 못함)", Material.MOSSY_STONE_BRICKS, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.JungleTemple.getOrDefault(p.getUniqueId(), 0) + "/1 획득함"),
					13, inv);
			}
	
			if (od.PillagerOutpost.getOrDefault(p.getUniqueId(), 0) >= 2) {
				pe.addUnsafeEnchantment(Enchantment.SMITE, 1);
				itemset(ChatColor.GOLD + "약탈자 전초기지 전리품", pe, 0, 1, Arrays.asList("방어력이 6% 증가합니다"), 14, inv);
			} else {
				itemset(ChatColor.GOLD + "약탈자 전초기지 전리품(아직 모두 획득하지 못함)", pe, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.PillagerOutpost.getOrDefault(p.getUniqueId(), 0) + "/2 획득함"),
					14, inv);
			}
	
			if (od.DesertPyramid.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "사막 피라미드 전리품", cs, 0, 1,
						Arrays.asList("공격력이 2% 증가합니다", "일반기술들의 재사용 대기시간이 10% 감소합니다"), 15, inv);
			} else {
				itemset(ChatColor.GOLD + "사막 피라미드 전리품(아직 모두 획득하지 못함)", Material.CHISELED_SANDSTONE, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.DesertPyramid.getOrDefault(p.getUniqueId(), 0) + "/1 획득함"),
					15, inv);
			}
	
			if (od.Stronghold.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "근거지 전리품", ef, 0, 1,
						Arrays.asList("공격력과 방어력이 3% 증가합니다", "일반기술들의 재사용 대기시간이 10% 감소합니다"), 16, inv);
			} else {
				itemset(ChatColor.GOLD + "근거지 전리품(아직 모두 획득하지 못함)", Material.END_PORTAL_FRAME, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.Stronghold.getOrDefault(p.getUniqueId(), 0) + "/1 획득함"),
					16, inv);
			}
	
			if (od.AncientCity.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "고대 도시", my, 0, 1, Arrays.asList("방어력이 8% 증가합니다", "실명, 어둠에 면역이 됩니다"), 17, inv);
			} else {
				itemset(ChatColor.GOLD + "고대 도시(아직 모두 획득하지 못함)", Material.SCULK_CATALYST, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.AncientCity.getOrDefault(p.getUniqueId(), 0) + "/2 획득함"),
					17, inv);
			}
	
			if (od.Village.getOrDefault(p.getUniqueId(), 0) >= 5) {
				itemset(ChatColor.GOLD + "마을 전리품", be, 0, 1,
						Arrays.asList("공격력과 방어력이 2.5% 증가합니다", "경험치 획득량이 10% 증가합니다", "일반기술들의 재사용 대기시간이 10% 감소합니다"), 18,
						inv);
			} else {
				itemset(ChatColor.GOLD + "마을 전리품(아직 모두 획득하지 못함)", Material.BELL, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.Village.getOrDefault(p.getUniqueId(), 0) + "/5 획득함"), 18,
						inv);
			}
	
			if (od.RuinedPortal.getOrDefault(p.getUniqueId(), 0) >= 4) {
				itemset(ChatColor.GOLD + "무너진 차원문 전리품", co, 0, 1,
						Arrays.asList("공격력과 방어력이 5% 증가합니다", "궁극기의 재사용 대기시간이 10% 감소합니다"), 19, inv);
			} else {
				itemset(ChatColor.GOLD + "무너진 차원문 전리품(아직 모두 획득하지 못함)", Material.CRYING_OBSIDIAN, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.RuinedPortal.getOrDefault(p.getUniqueId(), 0) + "/4 획득함"),
					19, inv);
			}
	
			if (od.NetherFortress.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "네더 요새 전리품", nq, 0, 1, Arrays.asList("공격력과 방어력이 8% 증가합니다"), 20, inv);
			} else {
				itemset(ChatColor.GOLD + "네더 요새 전리품(아직 모두 획득하지 못함)", Material.NETHER_QUARTZ_ORE, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.NetherFortress.getOrDefault(p.getUniqueId(), 0) + "/2 획득함"),
					20, inv);
			}
	
			if (od.BastionRemnant.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "보루 잔해 전리품", ba, 0, 1,
						Arrays.asList("공격력과 방어력이 6% 증가합니다", "일반기술들의 재사용 대기시간이 6% 감소합니다"), 21, inv);
			} else {
				itemset(ChatColor.GOLD + "보루 잔해 전리품(아직 모두 획득하지 못함)", Material.BASALT, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.BastionRemnant.getOrDefault(p.getUniqueId(), 0) + "/2 획득함"),
					21, inv);
			}
	
			if (od.EnderDragon.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "엔더 드래곤 전리품", ms, 0, 1,
						Arrays.asList(ChatColor.GOLD + "일반기술들의 재사용 대기시간이 10% 감소합니다", "궁극기의 재사용 대기시간이 10% 감소합니다", ""),
					22, inv);
			} else {
				itemset(ChatColor.GOLD + "엔더 드래곤 전리품(아직 모두 획득하지 못함)", Material.DRAGON_HEAD, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.EnderDragon.getOrDefault(p.getUniqueId(), 0) + "/1 획득함"),
					22, inv);
			}
	
			if (od.EndCity.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "엔드 도시 전리품", ed, 0, 1, Arrays.asList("공격력과 방어력이 11% 증가합니다"), 23, inv);
			} else {
				itemset(ChatColor.GOLD + "엔드 도시 전리품(아직 모두 획득하지 못함)", Material.END_STONE_BRICKS, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.EndCity.getOrDefault(p.getUniqueId(), 0) + "/1 획득함"), 23,
						inv);
			}
		} else {
			itemsetbasic(ChatColor.GOLD + "Dumpster", new ItemStack(Material.COMPOSTER), 0, 1, Arrays.asList("Open Dumpster If You Click"),
				0, inv);
			itemsetbasic(ChatColor.GOLD + "Artifact", new ItemStack(Material.AMETHYST_SHARD), 0, 1, Arrays.asList("Open Artifact management GUI If You Click"),
				1, inv);
			itemsetbasic(ChatColor.GOLD + "Shulker Bag", new ItemStack(Material.SHULKER_BOX), 0, 1, Arrays.asList("You can use Shulker Box as bag","By [Sneaking + Placing]"),
				2, inv);
			itemsetbasic(ChatColor.GOLD + "Backpack", new ItemStack(Material.BARREL), 0, 1, Arrays.asList("Open Backpack If You Click"),
				3, inv);
			itemsetbasic(ChatColor.GOLD + "Skills", new ItemStack(Material.BORDURE_INDENTED_BANNER_PATTERN), 0, 1, Arrays.asList("Open Backpack If You Click"),
					8, inv);
			
			if (od.Mineshaft.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "Trophy Of MineShaft", ms, 0, 1,
						Arrays.asList("Increase Damage & Armor 2%", "Increase Amount of Obtaining Experience 5%"), 6,
						inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of MineShaft(Not Obtained All Yet)", Material.POWERED_RAIL, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.Mineshaft.getOrDefault(p.getUniqueId(), 0) + "/2 Obtained"),
					6, inv);
			}
	
			if (od.BuriedTreasure.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "Trophy Of BuriedTreasure", mp, 0, 1,
						Arrays.asList("Increase Damage & Armor 3%", "Increase Amount of Obtaining Experience 5%"), 7,
						inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of BuriedTreasure(Not Obtained All Yet)", Material.SPAWNER, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.BuriedTreasure.getOrDefault(p.getUniqueId(), 0) + "/2 Obtained"),
					7, inv);
			}
	
			if (od.Igloo.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "Trophy Of Igloo", sb, 0, 1, Arrays.asList("Increase Damage 2.5%",
						"Immune To Slow", "Increase Amount of Obtaining Experience 5%"), 8, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of Igloo(Not Obtained All Yet)", Material.POWDER_SNOW_BUCKET, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.Igloo.getOrDefault(p.getUniqueId(), 0) + "/2 Obtained"),
					8, inv);
			}
	
			if (od.OceanRuins.getOrDefault(p.getUniqueId(), 0) >= 3) {
				itemset(ChatColor.GOLD + "Trophy Of OceanRuins", pc, 0, 1,
						Arrays.asList("Increase Armor 5%", "Immune To Drowning"), 9, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of OceanRuins(Not Obtained All Yet)", Material.PRISMARINE_CRYSTALS, 0,
						1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.OceanRuins.getOrDefault(p.getUniqueId(), 0) + "/3 Obtained"),
					9, inv);
			}
	
			if (od.WoodlandMansion.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "Trophy Of WoodlandMansion", ds, 0, 1,
						Arrays.asList("Increase Damage 3%", "Increase Armor 5%"), 10, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of WoodlandMansion(Not Obtained All Yet)", Material.DARK_OAK_SIGN, 0,
						1, Arrays.asList(ChatColor.GOLD + "" + od.WoodlandMansion.getOrDefault(p.getUniqueId(), 0)
								+ "/3 Obtained"),
					10, inv);
			}
	
			if (od.Shipwreck.getOrDefault(p.getUniqueId(), 0) >= 3) {
				itemset(ChatColor.GOLD + "Trophy Of Shipwreck", bb, 0, 1,
						Arrays.asList("Increase Damage & Armor 3%", "Increase Amount of Obtaining Experience 5%"), 11,
						inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of Shipwreck(Not Obtained All Yet)", Material.BIRCH_BOAT, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.Shipwreck.getOrDefault(p.getUniqueId(), 0) + "/3 Obtained"),
					11, inv);
			}
	
			if (od.OceanMonument.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "Trophy Of OceanMonument", hs, 0, 1,
						Arrays.asList("Increase Damage & Armor 3%", "Get Conduit Power When Swim"), 12, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of OceanMonument(Not Obtained All Yet)", Material.HEART_OF_THE_SEA, 0,
						1, Arrays.asList(ChatColor.GOLD + "" + od.OceanMonument.getOrDefault(p.getUniqueId(), 0)
								+ "/1 Obtained"),
					12, inv);
			}
	
			if (od.JungleTemple.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "Trophy Of JungleTemple", jp, 0, 1,
						Arrays.asList("Increase Damage & Armor 4%", "Decrease Ult's Cooldown 10%"), 13, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of JungleTemple(Not Obtained All Yet)", Material.MOSSY_STONE_BRICKS, 0,
						1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.JungleTemple.getOrDefault(p.getUniqueId(), 0) + "/1 Obtained"),
					13, inv);
			}
	
			if (od.PillagerOutpost.getOrDefault(p.getUniqueId(), 0) >= 2) {
				pe.addUnsafeEnchantment(Enchantment.SMITE, 1);
				itemset(ChatColor.GOLD + "Trophy Of PillagerOutpost", pe, 0, 1, Arrays.asList("Increase Armor 6%"), 14,
						inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of PillagerOutpost(Not Obtained All Yet)", pe, 0, 1, Arrays.asList(
						ChatColor.GOLD + "" + od.PillagerOutpost.getOrDefault(p.getUniqueId(), 0) + "/2 Obtained"), 14,
						inv);
			}
	
			if (od.DesertPyramid.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "Trophy Of DesertPyramid", cs, 0, 1,
						Arrays.asList("Increase Damage 2%", "Decrease Normal Skills' Cooldown 10%"), 15, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of DesertPyramid(Not Obtained All Yet)", Material.CHISELED_SANDSTONE,
						0, 1, Arrays.asList(ChatColor.GOLD + "" + od.DesertPyramid.getOrDefault(p.getUniqueId(), 0)
								+ "/1 Obtained"),
					15, inv);
			}
	
			if (od.Stronghold.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "Trophy Of Stronghold", ef, 0, 1,
						Arrays.asList("Increase Damage & Armor 3%", "Decrease Normal Skills' Cooldown 10%"), 16, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of Stronghold(Not Obtained All Yet)", Material.END_PORTAL_FRAME, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.Stronghold.getOrDefault(p.getUniqueId(), 0) + "/1 Obtained"),
					16, inv);
			}
	
			if (od.AncientCity.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "Trophy Of AncientCity", my, 0, 1,
						Arrays.asList("Increase Armor 8%", "Immune To Blindness, Darkness"), 17, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of AncientCity(Not Obtained All Yet)", Material.SCULK_CATALYST, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.AncientCity.getOrDefault(p.getUniqueId(), 0) + "/2 Obtained"),
					17, inv);
			}
	
			if (od.Village.getOrDefault(p.getUniqueId(), 0) >= 5) {
				itemset(ChatColor.GOLD + "Trophy Of Village", be, 0, 1, Arrays.asList("Increase Damage & Armor 2.5%",
						"Increase Amount of Obtaining Experience 10%", "Decrease Normal Skills' Cooldown 10%"), 18,
						inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of Village(Not Obtained All Yet)", Material.BELL, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.Village.getOrDefault(p.getUniqueId(), 0) + "/5 Obtained"),
					18, inv);
			}
	
			if (od.RuinedPortal.getOrDefault(p.getUniqueId(), 0) >= 4) {
				itemset(ChatColor.GOLD + "Trophy Of RuinedPortal", co, 0, 1,
						Arrays.asList("Increase Damage & Armor 5%", "Decrease Ult's Cooldown 10%"), 19, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of RuinedPortal(Not Obtained All Yet)", Material.CRYING_OBSIDIAN, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.RuinedPortal.getOrDefault(p.getUniqueId(), 0) + "/4 Obtained"),
					19, inv);
			}
	
			if (od.NetherFortress.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "Trophy Of NetherFortress", nq, 0, 1,
						Arrays.asList("Increase Damage & Armor 8%"), 20, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of NetherFortress(Not Obtained All Yet)", Material.NETHER_QUARTZ_ORE,
						0, 1, Arrays.asList(ChatColor.GOLD + "" + od.NetherFortress.getOrDefault(p.getUniqueId(), 0)
								+ "/2 Obtained"),
					20, inv);
			}
	
			if (od.BastionRemnant.getOrDefault(p.getUniqueId(), 0) >= 2) {
				itemset(ChatColor.GOLD + "Trophy Of BastionRemnant", ba, 0, 1,
						Arrays.asList("Increase Damage & Armor 6%", "Decrease Normal Skills' Cooldown 6%"), 21, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of BastionRemnant(Not Obtained All Yet)", Material.BASALT, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.BastionRemnant.getOrDefault(p.getUniqueId(), 0)
								+ "/2 Obtained"),
					21, inv);
			}
	
			if (od.EnderDragon.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "Trophy Of EnderDragon", ms, 0, 1,
						Arrays.asList(ChatColor.GOLD + "Decrease Normal Skills' Cooldown 10%",
								"Decrease Ult's Cooldown 10%", ""),
					22, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of EnderDragon(Not Obtained All Yet)", Material.DRAGON_HEAD, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + od.EnderDragon.getOrDefault(p.getUniqueId(), 0) + "/1 획득함"),
					22, inv);
			}
	
			if (od.EndCity.getOrDefault(p.getUniqueId(), 0) >= 1) {
				itemset(ChatColor.GOLD + "Trophy Of EndCity", ed, 0, 1, Arrays.asList("Increase Damage & Armor 11%"),
					23, inv);
			} else {
				itemset(ChatColor.GOLD + "Trophy Of EndCity(Not Obtained All Yet)", Material.END_STONE_BRICKS, 0, 1,
						Arrays.asList(
								ChatColor.GOLD + "" + od.EndCity.getOrDefault(p.getUniqueId(), 0) + "/1 Obtained"),
					23, inv);
			}
	
		}
		return inv;
	}


	public Obtained(HashMap<UUID, Integer> Mineshaft, HashMap<UUID, Integer> BuriedTreasure,
			HashMap<UUID, Integer> Igloo, HashMap<UUID, Integer> OceanRuins, HashMap<UUID, Integer> WoodlandMansion,
			HashMap<UUID, Integer> Shipwreck, HashMap<UUID, Integer> OceanMonument, HashMap<UUID, Integer> JungleTemple,
			HashMap<UUID, Integer> PillagerOutpost, HashMap<UUID, Integer> DesertPyramid,
			HashMap<UUID, Integer> Stronghold, HashMap<UUID, Integer> AncientCity, HashMap<UUID, Integer> Village,
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
		this.AncientCity = AncientCity;
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
		this.AncientCity = loadedData.AncientCity;
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