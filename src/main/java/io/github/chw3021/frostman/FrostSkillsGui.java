package io.github.chw3021.frostman;



import java.util.List;
import java.util.Arrays;
import java.io.File;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import net.md_5.bungee.api.ChatColor;

public class FrostSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void FrostSkillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		FrostSkillsData bsd = new FrostSkillsData(FrostSkillsData.loadData(path +"/plugins/RPGskills/FrostSkillsData.data"));
		Inventory Frostskillsinv = Bukkit.createInventory(null, 18, "FrostSkills");
		itemset("FrozenCrystal", Material.BLUE_ICE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.FrozenCrystal.get(p.getUniqueId()),"","SwapHand","You can break earlier","by Using one more time","Master LV.50"), 0, Frostskillsinv);
		itemset("Hailstones", Material.PACKED_ICE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Hailstones.get(p.getUniqueId()),"","Sneaking + SwapHand","Master LV.50"), 1, Frostskillsinv);
		itemset("IcicleShot", Material.ICE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.IcicleShot.get(p.getUniqueId()),"","Rightclick","Master LV.50"), 2, Frostskillsinv);
		itemset("IceSpikes", Material.PRISMARINE_SHARD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.IceSpikes.get(p.getUniqueId()),"","Sneaking + Rightclick","Master LV.50"), 3, Frostskillsinv);
		itemset("Crack", Material.DIAMOND_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Crack.get(p.getUniqueId()),"","Hit + Sneaking to Frostbite enemy","Master LV.50"), 4, Frostskillsinv);
		itemset("SnowBreeze", Material.SNOW_BLOCK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.SnowBreeze.get(p.getUniqueId()),"","Jump + LeftClick" ,"Master LV.1"), 5, Frostskillsinv);
		itemset("Frostbite", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Frostbite.get(p.getUniqueId()),"","Increase damage", "Freeze Enemy hit three times (Cooldown 2s)"), 7, Frostskillsinv);
		itemset("Blizzard", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Damage will be increase by your explevels."), 9, Frostskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+bsd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Frostskillsinv);
	
		
		p.openInventory(Frostskillsinv);
	}


}
