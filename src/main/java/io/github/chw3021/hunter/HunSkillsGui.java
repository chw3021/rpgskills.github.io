package io.github.chw3021.hunter;



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

public class HunSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void Hunskillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		HunSkillsData hsd = new HunSkillsData(HunSkillsData.loadData(path +"/plugins/RPGskills/HunSkillsData.data"));
		Inventory Hunskillsinv = Bukkit.createInventory(null, 18, "Hunskills");
		itemset("Chain", Material.WOODEN_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+hsd.Chain.get(p.getUniqueId()),"","RightClickToTarget (Master LV.1)"), 0, Hunskillsinv);
		itemset("Webthrow", Material.STONE_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+hsd.Webthrow.get(p.getUniqueId()),"","Sneaking + RightClick","Master LV.1"), 1, Hunskillsinv);
		itemset("Dodge", Material.IRON_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+hsd.Dodge.get(p.getUniqueId()),"","SwapHand (Master LV.1)","Decrease Falling Damage"), 2, Hunskillsinv);
		itemset("HuntingStart", Material.IRON_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+hsd.HuntingStart.get(p.getUniqueId()),"","Rightclick + Jump", "Disabled when you damage to enemy", "Reset cooldown if you kill enemy","Master LV.50"), 3, Hunskillsinv);
		itemset("Daze", Material.GOLDEN_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+hsd.Daze.get(p.getUniqueId()),"","Sneaking + Hit","Master LV.50"), 4, Hunskillsinv);
		itemset("SkullCrusher", Material.DIAMOND_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+hsd.SkullCrusher.get(p.getUniqueId()),"","Jump + Hit","Master LV.50"), 5, Hunskillsinv);
		itemset("Climbing", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+hsd.Bleeding.get(p.getUniqueId()),"", "Sneaking + SwapHand to activate/disable","Master LV.1"), 6, Hunskillsinv);

		if(hsd.Atrocity.containsKey(p.getUniqueId())) {
			itemset("Atrocity", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+hsd.Atrocity.get(p.getUniqueId()),"","Increase Damage & Immune to Hunger", "Damage Double + "+ Math.round(5+0.02*hsd.Atrocity.get(p.getUniqueId()))  +"% of Enemy's MaxHealth","When Full Charged Hit"), 7, Hunskillsinv);
		}
		else {
			itemset("Atrocity", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+hsd.Atrocity.get(p.getUniqueId()),"","Increase Damage & Immune to Hunger", "Damage Double + "+ Math.round(5)  +"% of Enemy's MaxHealth","When Full Charged Hit"), 7, Hunskillsinv);
		}
		itemset("Backattack", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+hsd.Backattack.get(p.getUniqueId()),"","Damage more when you hit enemy's back", "Higher Explevel gets Better hit detection", "Master LV.1"), 8, Hunskillsinv);
		itemset("Rage", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Duration will increase by your explevels."), 9, Hunskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+hsd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Hunskillsinv);
	
		
		p.openInventory(Hunskillsinv);
	}


}
