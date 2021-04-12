package io.github.chw3021.paladin;



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

public class PalSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void PalSkillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		PalSkillsData psd = new PalSkillsData(PalSkillsData.loadData(path +"/plugins/RPGskills/PalSkillsData.data"));
		Inventory Palskillsinv = Bukkit.createInventory(null, 18, "Palskills");
		itemset("Thrust", Material.WOODEN_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Thrust.get(p.getUniqueId()),"","Blocking + SwapHand", "Master Lv.50"), 0, Palskillsinv);
		itemset("Restraint", Material.STONE_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Restraint.get(p.getUniqueId()),"","Blocking + Sneaking + SwapHand", "Master Lv.50"), 1, Palskillsinv);
		itemset("Judgement", Material.IRON_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Judgement.get(p.getUniqueId()),"","LeftClick + Sneaking", "Master Lv.50"), 2, Palskillsinv);
		itemset("Punish", Material.GOLDEN_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Punish.get(p.getUniqueId()),"","Jump + Hit", "Master Lv.50"), 3, Palskillsinv);
		itemset("Encourge", Material.DIAMOND_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Encourge.get(p.getUniqueId()),"","SwapHand", "Master Lv.50"), 4, Palskillsinv);
		itemset("Pray", Material.WRITABLE_BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Pray.get(p.getUniqueId()),"","SwapHand + Sneaking (Master Lv.10)"), 5, Palskillsinv);
		itemset("Faith", Material.WRITABLE_BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Protection.get(p.getUniqueId()),"","Increase Damage"), 7, Palskillsinv);
		itemset("Protection", Material.BOOK, 0, 1, Arrays.asList("Increase Party's Armor When Blocking (Passive)"), 8, Palskillsinv);
		itemset("Immortal", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Ult's damage is only affected by your explevels."), 9, Palskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+psd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Palskillsinv);
	
		
		p.openInventory(Palskillsinv);
	}


}
