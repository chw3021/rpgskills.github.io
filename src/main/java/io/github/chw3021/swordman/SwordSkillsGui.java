package io.github.chw3021.swordman;



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

public class SwordSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void SwordSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		SwordSkillsData ssd = new SwordSkillsData(SwordSkillsData.loadData(path +"/plugins/RPGskills/SwordSkillsData.data"));
		Inventory Swordskillsinv = Bukkit.createInventory(null, 18, "Swordskills");
		itemset("RisingBlades", Material.WOODEN_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Rising.get(p.getUniqueId()),"","RightClick + Sneaking", "Master LV.50"), 0, Swordskillsinv);
		itemset("Strike", Material.STONE_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Strike.get(p.getUniqueId()),"","Jump + LeftClick", "Master LV.50"), 1, Swordskillsinv);
		itemset("SwordDrive", Material.IRON_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.SwordDrive.get(p.getUniqueId()),"","SwapHand", "Master LV.50"), 2, Swordskillsinv);
		itemset("FlashyRush", Material.IRON_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.FlashyRush.get(p.getUniqueId()),"","Rightclick", "Master LV.50"), 3, Swordskillsinv);
		itemset("CriticalDraw", Material.GOLDEN_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.CriticalDraw.get(p.getUniqueId()),"","SwapHand + Sneaking", "Master LV.50"), 4, Swordskillsinv);
		itemset("Swoop", Material.DIAMOND_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Swoop.get(p.getUniqueId()),"","Sneaking + Hit", "Master LV.50"), 5, Swordskillsinv);
		itemset("Guard", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Guard.get(p.getUniqueId()),"","Sneaking (Master LV.10)"), 6, Swordskillsinv);
		itemset("Dualbladed", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Dualbladed.get(p.getUniqueId()),"","Increase Damage When you hold Any Swords", "Both of your hands","Damage will be affected by main sword"), 7, Swordskillsinv);
		itemset("Storm", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Ult's damage is only affected by your explevels."), 9, Swordskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+ssd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Swordskillsinv);
	
		
		p.openInventory(Swordskillsinv);
	}


}
