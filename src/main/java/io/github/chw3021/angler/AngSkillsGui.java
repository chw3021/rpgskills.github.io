package io.github.chw3021.angler;



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

public class AngSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void AngSkillsinv(Player p)
	{
		Inventory Angskillsinv = Bukkit.createInventory(null, 18, "Angskills");
	    String path = new File("").getAbsolutePath();
		AngSkillsData fsd = new AngSkillsData(AngSkillsData.loadData(path +"/plugins/RPGskills/AngSkillsData.data"));
		itemset("Bait", Material.NAUTILUS_SHELL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Bait.get(p.getUniqueId()),"","Sneaking + SwapHand", "Master Lv.50"), 0, Angskillsinv);
		itemset("Fishing", Material.HEART_OF_THE_SEA, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Fishing.get(p.getUniqueId()),"","Trident will belong to you since you Throw it","", "You can call back earlier Trident", "Using [Sneaking + LeftClick with BareHands]" ,"You should Throw One Trident at Once", "Master LV,1"), 1, Angskillsinv);
		itemset("Whipping", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Whipping.get(p.getUniqueId()),"","LeftClick + Jump", "Strike Lightning to Marked Entites", "Master Lv.50"), 2, Angskillsinv);
		itemset("CoralLiquor", Material.GUARDIAN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.CoralLiquor.get(p.getUniqueId()),"","Guardians Support your Attack","Attack Enemies that hit you","", "When you Hit Wall or Entity with Trident", "Guardians will Protect you","Before You get back Trident","Guardians will return", "When you change holding Item", "Master LV.20"), 3, Angskillsinv);
		itemset("CoralRoots", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.CoralRoots.get(p.getUniqueId()),"","Sneaking + Throw", "Master Lv.50"), 4, Angskillsinv);
		itemset("DrunkenDance", Material.DOLPHIN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.DrunkenDance.get(p.getUniqueId()),"","SwapHand", "RightClick Will Quit Surfing", "Damage NearbyEntites When You Quit Surfing", "Master Lv.50"), 5, Angskillsinv);
		itemset("Technic", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Technic.get(p.getUniqueId()),"","Increase Damage", "Mark Attacked Enemies", "Give Loyalty & Channeling Enchant if You Don't Have", "Get Water Ability When Swim"), 7, Angskillsinv);
		itemset("Whirlpool", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Ult's damage is only affected by your explevels."), 9, Angskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Angskillsinv);
	
		
		p.openInventory(Angskillsinv);
	}


}
