package io.github.chw3021.chemist;



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

public class CheSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void CheSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		CheSkillsData csd = new CheSkillsData(CheSkillsData.loadData(path +"/plugins/RPGskills/CheSkillsData.data"));
		Inventory Cheskillsinv = Bukkit.createInventory(null, 18, "Cheskills");
		itemset("Napalm" , Material.WOODEN_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Coagulation.get(p.getUniqueId()),"","SwapHand","Master LV.50"), 0, Cheskillsinv);
		itemset("Extraction", Material.STONE_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Extraction.get(p.getUniqueId()),"","RightclickToTarget + Sneaking","Be Able to use Injection", "Extraction can be stacked", "Master LV.1"), 2, Cheskillsinv);
		itemset("Injection" , Material.STONE_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Injection.get(p.getUniqueId()),"","Sneaking + Rightclick","Get Positive Effect","Depending on Extracted EntityCategory","Base: Speed, Jump","Arthropod: Nightvision" ,"Undead: Strength, Resistance","Water: WaterBreath, Conduit, DolphinsGrace","Enderman: Invisible","Else: Regeneration, Saturation", "Master LV.1"), 1, Cheskillsinv);
		itemset("AcidCloud" , Material.IRON_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.AcidCloud.get(p.getUniqueId()),"","Rightclick", "On/Off skill","Master LV.50"), 3, Cheskillsinv);
		itemset("MolotovCocktail" , Material.GOLDEN_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.MolotovCocktail.get(p.getUniqueId()),"","LeftClick + Jump","Master LV.50"), 4, Cheskillsinv);
		itemset("SlimeBall" , Material.DIAMOND_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.SlimeBall.get(p.getUniqueId()),"","SwapHand + Sneaking","Master LV.50"), 5, Cheskillsinv);
		itemset("Poisonous" , Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Poisonous.get(p.getUniqueId()),"","Increase Damage","Be Immune to Poison"), 6, Cheskillsinv);
		itemset("VX" , Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Damage increase by your explevels."), 9, Cheskillsinv);
		itemset("SkillPoints" , Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+csd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Cheskillsinv);
	
		
		p.openInventory(Cheskillsinv);
	}


}
