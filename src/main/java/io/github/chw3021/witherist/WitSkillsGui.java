package io.github.chw3021.witherist;



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

public class WitSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void WitSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		WitSkillsData wsd = new WitSkillsData(WitSkillsData.loadData(path +"/plugins/RPGskills/WitSkillsData.data"));
		Inventory Witskillsinv = Bukkit.createInventory(null, 18, "Witskills");
		itemset("WitherSkull", Material.WITHER_SKELETON_SKULL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.WitherSkull.get(p.getUniqueId()),"","SwapHand","Master LV.50"), 0, Witskillsinv);
		itemset("ReapingHook", Material.WOODEN_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.ReapingHook.get(p.getUniqueId()),"","Sneaking + Rightclick", "Master LV.1"), 1, Witskillsinv);
		itemset("WitherScythe", Material.STONE_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.WitherScythe.get(p.getUniqueId()),"","Hit + Sneaking","Master LV.50"), 2, Witskillsinv);
		itemset("Curse", Material.IRON_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Curse.get(p.getUniqueId()),"","Rightclick","Master LV.50"), 3, Witskillsinv);
		itemset("Hover", Material.GOLDEN_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Hover.get(p.getUniqueId()),"","LeftClick + Jump", "Master LV.1"), 4, Witskillsinv);
		itemset("Roses", Material.DIAMOND_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Roses.get(p.getUniqueId()),"","SwapHand + Sneaking","Master LV.50"), 5, Witskillsinv);
		itemset("Witherize", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Witherize.get(p.getUniqueId()),"","Increase damage especially to WitherType Mobs", "Reduce Damage from WitherType", "Immune to Wither Effect"), 6, Witskillsinv);
		itemset("Overcome", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Cooldown decrease by your explevels."), 9, Witskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+wsd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Witskillsinv);
	
		
		p.openInventory(Witskillsinv);
	}


}
