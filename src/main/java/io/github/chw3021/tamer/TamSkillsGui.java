package io.github.chw3021.tamer;



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

public class TamSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void TamSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		TamSkillsData tsd = new TamSkillsData(TamSkillsData.loadData(path +"/plugins/RPGskills/TamSkillsData.data"));
		Inventory Tamskillsinv = Bukkit.createInventory(null, 18, "TamSkills");
		itemset("PressTheAttack", Material.ARROW, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.PressTheAttack.get(p.getUniqueId()),"","SwapHand","ArrowDamage Will Increase By Creatures", "Master LV.1"), 0, Tamskillsinv);
		itemset("Spidey", Material.CAVE_SPIDER_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Spidey.get(p.getUniqueId()),"","Sneaking + Rightclick to target", "Master LV.50"), 1, Tamskillsinv);
		itemset("Pets", Material.WOLF_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Pets.get(p.getUniqueId()),"","SwapHand + Sneaking", "Master LV.50"), 2, Tamskillsinv);
		itemset("BeeHive", Material.BEEHIVE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.BeeHive.get(p.getUniqueId()),"","Jump + RightClick", "Master LV.50"), 3, Tamskillsinv);
		itemset("CreepBomb", Material.CREEPER_HEAD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.CreepBomb.get(p.getUniqueId()),"","Attack when sneaking", "Master LV.50"), 4, Tamskillsinv);
		itemset("Taming", Material.LEAD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Taming.get(p.getUniqueId()),"","Increase Creatures' HP, Damage, Speed"), 6, Tamskillsinv);
		itemset("PandaSweep", Material.PANDA_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.PandaSweep.get(p.getUniqueId()),"","Jump + LeftClick","Taunt Near By Enemies Continuously", "Master LV.50"), 5, Tamskillsinv);
		itemset("IronGolem", Material.IRON_BLOCK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Damage will be increase by your explevels."), 9, Tamskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+tsd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Tamskillsinv);
	
		
		p.openInventory(Tamskillsinv);
	}


}
