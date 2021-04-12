package io.github.chw3021.taoist;



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

public class TaoSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void TaoSkillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		TaoSkillsData tsd = new TaoSkillsData(TaoSkillsData.loadData(path +"/plugins/RPGskills/TaoSkillsData.data"));
		Inventory Taoskillsinv = Bukkit.createInventory(null, 18, "TaoSkills");
		itemset("StanceChange", Material.LEATHER_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.StanceChange.get(p.getUniqueId()),"","SwapHand", "Master Lv.1"), 0, Taoskillsinv);
		itemset("Wave", Material.IRON_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Wave.get(p.getUniqueId()),"","Rightclick" ,"Master LV.50"), 2, Taoskillsinv);
		itemset("Aura", Material.GOLDEN_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Aura.get(p.getUniqueId()),"","Sneaking + Rightclick" ,"Master LV.50"), 3, Taoskillsinv);
		itemset("Charm", Material.DIAMOND_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Charm.get(p.getUniqueId()),"","SwapHand + Sneaking" ,"Master LV.50"), 4, Taoskillsinv);
		itemset("Flip", Material.CHAINMAIL_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Flip.get(p.getUniqueId()),"","Jump + LeftClick" ,"Master LV.50"), 5, Taoskillsinv);
		itemset("CombustInside", Material.WRITABLE_BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.CombustInside.get(p.getUniqueId()),"","Sneaking + Hit" ,"Master LV.50"), 6, Taoskillsinv);
		itemset("Vibe", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Vibe.get(p.getUniqueId()),"","Increase damage"), 7, Taoskillsinv);
		itemset("Numinous", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Damage will be increase by your explevels."), 9, Taoskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+tsd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Taoskillsinv);
	
		
		p.openInventory(Taoskillsinv);
	}


}
