package io.github.chw3021.witchdoctor;



import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import net.md_5.bungee.api.ChatColor;

public class WdcSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void WdcSkillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		WdcSkillsData wsd = new WdcSkillsData(WdcSkillsData.loadData(path +"/plugins/RPGskills/WdcSkillsData.data"));
		Inventory Wdcskillsinv = Bukkit.createInventory(null, 18, "Wdcskills");
		itemset("Fangs", Material.STONE_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Fangs.get(p.getUniqueId()),"","Sneaking + SwapHand", "Holding Hoe", "Master LV.50"), 0, Wdcskillsinv);
		itemset("Bosou", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Bosou.get(p.getUniqueId()),"","Sneaking + SwapHand", "Holding Totem", "Increase Damage&Armor", "Master LV.10"), 1, Wdcskillsinv);
		itemset("Harvest", Material.IRON_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Harvest.get(p.getUniqueId()),"","RightClick", "Holding Hoe", "Decrease Enemies' Armor", "Master LV.50"), 2, Wdcskillsinv);
		itemset("Wraith", Material.GOLDEN_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Wraith.get(p.getUniqueId()),"","RightClick + Sneaking", "Holding Hoe", "Master LV.50"), 3, Wdcskillsinv);
		itemset("AstralProjection", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.AstralProjection.get(p.getUniqueId()),"","RightClick", "Holding Totem", "Master LV.1"), 4, Wdcskillsinv);
		itemset("Incantation", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Incantation.get(p.getUniqueId()),"","RightClick + Sneaking", "Holding Totem", "Master LV.1"), 5, Wdcskillsinv);
		itemset("Legba", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Legba.get(p.getUniqueId()),"","Increase Damage & Harvest's Armor DecreasingRate"), 7, Wdcskillsinv);
		itemset("Resurrect", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("Totem won't be used when resurrect", "Resurrect effect will be enhanced", "Party Member also can be Resuurect", "Cooldown will be applied individual", "Passive Skill"), 11, Wdcskillsinv);
		itemset("Baron Samedi", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Prevent death during Samedi existing", "Duration will increase by expLevels"), 9, Wdcskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+wsd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Wdcskillsinv);
	
		
		p.openInventory(Wdcskillsinv);
	}


}
