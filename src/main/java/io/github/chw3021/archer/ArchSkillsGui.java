package io.github.chw3021.archer;



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

public class ArchSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void Archskillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		ArchSkillsData asd = new ArchSkillsData(ArchSkillsData.loadData(path +"/plugins/RPGskills/ArchSkillsData.data"));
		Inventory Archskillsinv = Bukkit.createInventory(null, 18, "Archskills");
		itemset("SpreadingArrows", Material.TIPPED_ARROW, 1, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.SpreadingArrows.get(p.getUniqueId()),"","Leftclick", "Master LV.1"), 0, Archskillsinv);
		itemset("Retrieve", Material.TIPPED_ARROW, 2, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.Retrieve.get(p.getUniqueId()),"","SwapHand", "Master LV.1"), 1, Archskillsinv);
		itemset("RapidFire", Material.TIPPED_ARROW, 3, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.RapidFire.get(p.getUniqueId()),"","Sneaking + SwapHand", "Master LV.50"), 2, Archskillsinv);
		itemset("MultiShot", Material.TIPPED_ARROW, 4, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.MultiShot.get(p.getUniqueId()),"","Sneaking + Shoot", "Master LV.50"), 3, Archskillsinv);
		itemset("HookAndShot", Material.TIPPED_ARROW, 5, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.HookAndShot.get(p.getUniqueId()),"","Sneaking + MeleeHit", "Hold a mob for 2secs", "Release the mob when you","shot or after 2 seconds", "Master LV.50"), 4, Archskillsinv);
		itemset("TripleShot", Material.BOOK, 6, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.TripleShot.get(p.getUniqueId()),"","Shot three arrows at a time", "Master LV.50"), 5, Archskillsinv);
		itemset("Archery", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.Archery.get(p.getUniqueId()),"","Increase your arrow and melee damage","Increase Armor for 1s since Charging"), 7, Archskillsinv);
		itemset("CrazyArrows", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Ult's damage is only affected by your explevels."), 9, Archskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+asd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Archskillsinv);
	
		
		p.openInventory(Archskillsinv);
	}


}
