package io.github.chw3021.engineer;



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

public class EngSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void EngSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		EngSkillsData esd = new EngSkillsData(EngSkillsData.loadData(path +"/plugins/RPGskills/EngSkillsData.data"));
		Inventory Engskillsinv = Bukkit.createInventory(null, 18, "Engskills");
		itemset("Graviton", Material.WOODEN_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.Graviton.get(p.getUniqueId()),"","SwapHand","Master LV.50"), 0, Engskillsinv);
		itemset("X_ray", Material.STONE_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.X_ray.get(p.getUniqueId()),"","Sneaking + Rightclick","Decrease Mob's Armor","Master LV.50"), 1, Engskillsinv);
		itemset("Magnetic", Material.STONE_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.Magnetic.get(p.getUniqueId()),"","Hit + Sneaking", "Master LV.1"), 2, Engskillsinv);
		itemset("Electrostatic", Material.IRON_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.Electrostatic.get(p.getUniqueId()),"","Rightclick","Master LV.50"), 3, Engskillsinv);
		itemset("Jetpack", Material.GOLDEN_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.Jetpack.get(p.getUniqueId()),"","LeftClick + Jump", "Immuned to fall damage", "Damage near by mobs when hit ground","(Damage increase by FallDistance)", "Master LV.1"), 4, Engskillsinv);
		itemset("Dispenser", Material.DIAMOND_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.Dispenser.get(p.getUniqueId()),"","SwapHand + Sneaking","Master LV.50"), 5, Engskillsinv);
		itemset("CombatSuit", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.CombatSuit.get(p.getUniqueId()),"","Increase damage","Reduce Damage over 20% of MaxHealth"), 6, Engskillsinv);
		itemset("Gamma Ray", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Damage increase by your explevels."), 9, Engskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+esd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Engskillsinv);
	
		
		p.openInventory(Engskillsinv);
	}


}
