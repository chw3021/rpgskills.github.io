package io.github.chw3021.medic;



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

public class MedSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void Medskillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		MedSkillsData ssd = new MedSkillsData(MedSkillsData.loadData(path +"/plugins/RPGskills/MedskillsData.data"));
		Inventory Medskillsinv = Bukkit.createInventory(null, 18, "Medskills");
		itemset("RemedyingRocket", Material.TIPPED_ARROW, 1, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.RemedyingRocket.get(p.getUniqueId()),"","Jump + LeftClick", "Master LV.50"), 0, Medskillsinv);
		itemset("Decontamination", Material.TIPPED_ARROW, 2, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Decontamination.get(p.getUniqueId()),"","Sneaking + SwapHand", "Master LV.50"), 1, Medskillsinv);
		itemset("SupplyCart", Material.TIPPED_ARROW, 3, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.SupplyCart.get(p.getUniqueId()),"","Jump + SwapHand", "Master LV.5"), 2, Medskillsinv);
		itemset("Hideout", Material.TIPPED_ARROW, 4, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Hideout.get(p.getUniqueId()),"","Sneaking", "Master LV.1"), 3, Medskillsinv);
		itemset("ArrowClinic", Material.TIPPED_ARROW, 5, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.ArrowClinic.get(p.getUniqueId()),"","Heal Hit party member", "Decrease hit enemy's armor(Max 2.5)", "Master LV.30"), 4, Medskillsinv);
		itemset("AED", Material.CROSSBOW, 6, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.AED.get(p.getUniqueId()),"","Sneaking + Hit", "Master LV.50"), 5, Medskillsinv);
		itemset("Rescue", Material.KNOWLEDGE_BOOK, 6, 1, Arrays.asList(ChatColor.AQUA+ "Passive","","When Party Member is at death's door,","The Member will be Groggy for 5secs","You'll get speed and be able to Rescue Member","By using AED skill"), 6, Medskillsinv);
		itemset("Medicine", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Medicine.get(p.getUniqueId()),"","Increse Damage and Healing Rate"), 7, Medskillsinv);
		itemset("Caduceus Tower", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Duration will increase by your explevels."), 9, Medskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+ssd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Medskillsinv);
	
		
		p.openInventory(Medskillsinv);
	}


}
