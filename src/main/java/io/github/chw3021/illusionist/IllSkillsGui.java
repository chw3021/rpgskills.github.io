package io.github.chw3021.illusionist;



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

public class IllSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void IllSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		IllSkillsData isd = new IllSkillsData(IllSkillsData.loadData(path +"/plugins/RPGskills/IllSkillsData.data"));
		Inventory Illskillsinv = Bukkit.createInventory(null, 18, "Illskills");
		itemset("Switch", Material.STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Switch.get(p.getUniqueId()),"","Hit + Sneaking", "Master LV.50"), 0, Illskillsinv);
		itemset("Trick", Material.STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Trick.get(p.getUniqueId()),"","LeftClick + Jump", "Master LV.50"), 1, Illskillsinv);
		itemset("JackoLantern", Material.JACK_O_LANTERN, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.JackoLantern.get(p.getUniqueId()),"","SwapHand", "Master LV.50"), 2, Illskillsinv);
		itemset("Distortion", Material.DEBUG_STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Distortion.get(p.getUniqueId()),"","Rightclick", "Master LV.50"), 3, Illskillsinv);
		itemset("Paradox", Material.DEBUG_STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Paradox.get(p.getUniqueId()),"","Sneaking + Rightclick","Reset All CoolDown","Remove negetive Effect","Reset HP And Hunger", "Master LV.1"), 4, Illskillsinv);
		itemset("FakeDoll", Material.ARMOR_STAND, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.FakeDoll.get(p.getUniqueId()),"","SwapHand + Sneaking", "Taunts near by enemies", "Master LV.1"), 5, Illskillsinv);
		itemset("Surprise", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Surprise.get(p.getUniqueId()),"","Increase damage when you're invisible"), 7, Illskillsinv);
		itemset("The Void", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Damage Increase by your explevels."), 9, Illskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+isd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Illskillsinv);
	
		
		p.openInventory(Illskillsinv);
	}


}
