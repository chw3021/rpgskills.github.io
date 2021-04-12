package io.github.chw3021.oceanknight;



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

public class OceSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void OceSkillsinv(Player p)
	{
		Inventory Oceskillsinv = Bukkit.createInventory(null, 18, "Oceskills");
	    String path = new File("").getAbsolutePath();
		OceSkillsData fsd = new OceSkillsData(OceSkillsData.loadData(path +"/plugins/RPGskills/OceSkillsData.data"));
		itemset("WaterSpear", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WaterSpear.get(p.getUniqueId()),"","Blocking + SwapHand", "Master Lv.50"), 0, Oceskillsinv);
		itemset("WaterBarrior", Material.SHIELD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WaterBarrior.get(p.getUniqueId()),"","Blocking + Sneaking + SwapHand", "Summon Water if you're not in Water", "Increase Armor When You're In Barrior", "Master Lv.50"), 1, Oceskillsinv);
		itemset("Javelin", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Javelin.get(p.getUniqueId()),"","LeftClick + Jump", "Get Back Half of Cooldown", "When you pick up", "Master Lv.50"), 2, Oceskillsinv);
		itemset("RipCurrent", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.RipCurrent.get(p.getUniqueId()),"","SwapHand when RipTiding","Pull the Nearist Entity While Riptiding", "Master LV.1"), 3, Oceskillsinv);
		itemset("TripleHit", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.TripleHit.get(p.getUniqueId()),"","Sneaking + Hit", "Master Lv.50"), 4, Oceskillsinv);
		itemset("WetSwing", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WetSwing.get(p.getUniqueId()),"","SwapHand + Sneaking", "Master Lv.50"), 5, Oceskillsinv);
		itemset("Splash", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Splash.get(p.getUniqueId()),"","Increase Damage", "Apply Splash Damage", "Give Riptide Enchant if You Don't Have", "You can't Enchant Loyalty & Channeling", "Immune to Falling Damage", "Get Water Ability When Swim"), 7, Oceskillsinv);
		itemset("Grand Waves", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Ult's damage is only affected by your explevels."), 9, Oceskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Oceskillsinv);
	
		
		p.openInventory(Oceskillsinv);
	}


}
