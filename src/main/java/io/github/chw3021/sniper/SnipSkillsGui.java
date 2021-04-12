package io.github.chw3021.sniper;



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

public class SnipSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void Snipskillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		SnipSkillsData ssd = new SnipSkillsData(SnipSkillsData.loadData(path +"/plugins/RPGskills/SnipskillsData.data"));
		Inventory Snipskillsinv = Bukkit.createInventory(null, 18, "Snipskills");
		itemset("Wire", Material.CROSSBOW, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Wire.get(p.getUniqueId()),"","Jump + LeftClick", "Master LV.1"), 0, Snipskillsinv);
		if(ssd.ArmourPiercingArrow.containsKey(p.getUniqueId())) {
			itemset("ArmourPiercingArrow", Material.TIPPED_ARROW, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.ArmourPiercingArrow.get(p.getUniqueId()),"","Sneaking + SwapHand", "Damage "+ Math.round(5+0.02*ssd.ArmourPiercingArrow.get(p.getUniqueId()))  +"% of Enemy's CurrentHealth", "Master LV.50"), 1, Snipskillsinv);
		}
		else {
			itemset("ArmourPiercingArrow", Material.TIPPED_ARROW, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.ArmourPiercingArrow.get(p.getUniqueId()),"","Sneaking + SwapHand", "Damage "+ Math.round(5)  +"% of Enemy's CurrentHealth", "Master LV.50"), 1, Snipskillsinv);
		}
		itemset("FlashBomb", Material.SNOWBALL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.FlashBomb.get(p.getUniqueId()),"","Jump + SwapHand", "Master LV.50"), 2, Snipskillsinv);
		itemset("Flare", Material.FIREWORK_ROCKET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Flare.get(p.getUniqueId()),"","Sneaking + LeftClick", "Master LV.1"), 3, Snipskillsinv);
		itemset("DangerClose", Material.FIREWORK_ROCKET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.DangerClose.get(p.getUniqueId()),"","Rocket will Drop When Hit", "Master LV.50"), 4, Snipskillsinv);
		itemset("Camouflage", Material.TURTLE_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Camouflage.get(p.getUniqueId()),"","Sneaking", "Master LV.1"), 5, Snipskillsinv);
		itemset("Remodeling", Material.CROSSBOW, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Remodeling.get(p.getUniqueId()),"","Replace more faster, stronger arrow", "Be Able to Attack Enderman & WitherBarrior","[MULTISHOT ENCHANTMENT IS NOT RECOMMENDED]", "Master LV.50"), 6, Snipskillsinv);
		itemset("HeadShot", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.HeadShot.get(p.getUniqueId()),"","Hit Target's Head", "Higher level gets Better hit detection", "APA, Ult won't be applied"), 7, Snipskillsinv);
		itemset("TheLastOne", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Ult's damage is only affected by your explevels."), 9, Snipskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+ssd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Snipskillsinv);
	
		
		p.openInventory(Snipskillsinv);
	}


}
