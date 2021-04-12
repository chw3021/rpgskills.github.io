package io.github.chw3021.launcher;



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

public class LaunSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void Launskillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		LaunSkillsData lsd = new LaunSkillsData(LaunSkillsData.loadData(path +"/plugins/RPGskills/LaunskillsData.data"));
		Inventory Launskillsinv = Bukkit.createInventory(null, 18, "Launskills");
		itemset("ArrowChange", Material.ARROW, 1, 1, Arrays.asList(ChatColor.AQUA+"LV."+lsd.ArrowChange.get(p.getUniqueId()),"","LeftClick","If you Hit SomeThing When Using EnderArrow","You'll Teleport There (Cooldown 3s)", "Master LV.1"), 0, Launskillsinv);
		itemset("ArrowRain", Material.TIPPED_ARROW, 2, 1, Arrays.asList(ChatColor.AQUA+"LV."+lsd.ArrowRain.get(p.getUniqueId()),"","Sneaking + SwapHand", "Master LV.50"), 1, Launskillsinv);
		itemset("Discharge", Material.FIREWORK_ROCKET, 3, 1, Arrays.asList(ChatColor.AQUA+"LV."+lsd.Discharge.get(p.getUniqueId()),"","Sprinting + SwapHand", "Master LV.50"), 2, Launskillsinv);
		itemset("GiantArrow", Material.TIPPED_ARROW, 4, 1, Arrays.asList(ChatColor.AQUA+"LV."+lsd.GiantArrow.get(p.getUniqueId()),"","Jump + SwapHand", "Master LV.50"), 3, Launskillsinv);
		itemset("Explosion", Material.GUNPOWDER, 5, 1, Arrays.asList(ChatColor.AQUA+"LV."+lsd.Explosion.get(p.getUniqueId()),"","Sneaking + Shoot", "Master LV.50"), 4, Launskillsinv);
		itemset("ChargingShot", Material.FIREWORK_STAR, 6, 1, Arrays.asList(ChatColor.AQUA+"LV."+lsd.ChargingShot.get(p.getUniqueId()),"","Sprinting + Shoot", "Master LV.50"), 5, Launskillsinv);
		itemset("MagicArrow", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+lsd.MagicArrow.get(p.getUniqueId()),"","Increase your damage"), 7, Launskillsinv);
		itemset("Fly", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Ult's damage is only affected by your explevels."), 9, Launskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+lsd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Launskillsinv);
	
		
		p.openInventory(Launskillsinv);
	}


}
