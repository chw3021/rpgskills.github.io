package io.github.chw3021.berserker;



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

public class BerSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void Berskillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		BerSkillsData bsd = new BerSkillsData(BerSkillsData.loadData(path +"/plugins/RPGskills/BerSkillsData.data"));
		Inventory Berskillsinv = Bukkit.createInventory(null, 18, "Berskills");
		itemset("Drain", Material.WOODEN_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Drain.get(p.getUniqueId()),"","Hit + Sneaking", "Holding Hoe", "Master LV.50"), 0, Berskillsinv);
		itemset("Spray", Material.STONE_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Spray.get(p.getUniqueId()),"","Sneaking + SwapHand", "Holding Sword", "Master LV.50"), 1, Berskillsinv);
		itemset("Dread", Material.STONE_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Dread.get(p.getUniqueId()),"","Sneaking + SwapHand", "Holding Hoe", "Master LV.1"), 2, Berskillsinv);
		itemset("Swipe", Material.IRON_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Swipe.get(p.getUniqueId()),"","RightClick + Sneaking", "Holding Hoe", "Master LV.50"), 3, Berskillsinv);
		itemset("Inhale", Material.GOLDEN_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Inhale.get(p.getUniqueId()),"","Jump + RightClick", "Holding Hoe", "Master LV.50"), 4, Berskillsinv);
		itemset("Frenzy", Material.DIAMOND_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Burstout.get(p.getUniqueId()),"","Jump + RightClick", "Holding Sword", "Master LV.50"), 5, Berskillsinv);
		itemset("CrimsonAdvance", Material.DIAMOND_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Undying.get(p.getUniqueId()),"","Sneaking + RightClick", "Holding Sword", "Master LV.50"), 6, Berskillsinv);
		itemset("Lunacy", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Lunacy.get(p.getUniqueId()),"","Increase Damage","Set Attack Speed VeryFast", "Set KnockbackResistance Max" , "Get speed&jump when you hit mob", "Heal dramatically when Hp fall below 5%"), 7, Berskillsinv);
		itemset("Bloodboil", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Ult's damage is only affected by your explevels."), 9, Berskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+bsd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Berskillsinv);
	
		
		p.openInventory(Berskillsinv);
	}


}
