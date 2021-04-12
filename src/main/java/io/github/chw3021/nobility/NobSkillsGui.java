package io.github.chw3021.nobility;



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

public class NobSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void NobSkillsinv(Player p)
	{
		Inventory Nobskillsinv = Bukkit.createInventory(null, 18, "Nobskills");
	    String path = new File("").getAbsolutePath();
		NobSkillsData fsd = new NobSkillsData(NobSkillsData.loadData(path +"/plugins/RPGskills/NobSkillsData.data"));
		itemset("Assault", Material.NAUTILUS_SHELL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Assault.get(p.getUniqueId()),"","Sneaking + SwapHand", "Master Lv.50"), 0, Nobskillsinv);
		itemset("Owner", Material.HEART_OF_THE_SEA, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Owner.get(p.getUniqueId()),"","Trident will belong to you since you Throw it","", "You can call back earlier Trident", "Using [Sneaking + LeftClick with BareHands]" ,"You should Throw One Trident at Once", "Master LV,1"), 1, Nobskillsinv);
		itemset("Storm", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Storm.get(p.getUniqueId()),"","LeftClick + Jump", "Strike Lightning to Marked Entites", "Master Lv.50"), 2, Nobskillsinv);
		itemset("GuardianSupport", Material.GUARDIAN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.GuardianSupport.get(p.getUniqueId()),"","Guardians Support your Attack","Attack Enemies that hit you","", "When you Hit Wall or Entity with Trident", "Guardians will Protect you","Before You get back Trident","Guardians will return", "When you change holding Item", "Master LV.20"), 3, Nobskillsinv);
		itemset("Transition", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Transition.get(p.getUniqueId()),"","Sneaking + Throw", "Master Lv.50"), 4, Nobskillsinv);
		itemset("DolphinSurf", Material.DOLPHIN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.DolphinSurf.get(p.getUniqueId()),"","SwapHand", "RightClick Will Quit Surfing", "Damage NearbyEntites When You Quit Surfing", "Master Lv.50"), 5, Nobskillsinv);
		itemset("MarkOfSea", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.MarkOfSea.get(p.getUniqueId()),"","Increase Damage", "Mark Attacked Enemies", "Give Loyalty & Channeling Enchant if You Don't Have", "Get Water Ability When Swim"), 7, Nobskillsinv);
		itemset("Whirlpool", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Ult's damage is only affected by your explevels."), 9, Nobskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Nobskillsinv);
	
		
		p.openInventory(Nobskillsinv);
	}


}
