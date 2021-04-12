package io.github.chw3021.wreltler;



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

public class WreSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void WreSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		WreSkillsData wsd = new WreSkillsData(WreSkillsData.loadData(path +"/plugins/RPGskills/WreSkillsData.data"));
		Inventory Wreskillsinv = Bukkit.createInventory(null, 18, "WreSkills");
		itemset("BodyPress", Material.LEATHER_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.BodyPress.get(p.getUniqueId()),"","Jump + RightClick" ,"Master LV.50"), 0, Wreskillsinv);
		itemset("ChokeSlam", Material.LEATHER_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.ChokeSlam.get(p.getUniqueId()),"","Sneaking + Rightclick to target" ,"Master LV.50"), 1, Wreskillsinv);
		itemset("ChainingSuplex", Material.IRON_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.ChainingSuplex.get(p.getUniqueId()),"","SwapHand + Sneaking" ,"Master LV.50"), 2, Wreskillsinv);
		itemset("Lock", Material.GOLDEN_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.LegDrop.get(p.getUniqueId()),"","SwapHand", "You Can Lock Enemy By Holding Key(Max 3s)" ,"Master LV.50"), 3, Wreskillsinv);
		itemset("GiantSwing", Material.DIAMOND_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.GiantSwing.get(p.getUniqueId()),"","Sneaking + Hit" ,"Master LV.50"), 4, Wreskillsinv);
		itemset("TackleRush", Material.CHAINMAIL_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.TackleRush.get(p.getUniqueId()),"","Get Jump,Speed,Resistance effect", "when you're Sprinting", "Hit Near By Enemies", "Master LV.1"), 5, Wreskillsinv);
		itemset("ForeArmSmash", Material.WRITABLE_BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.ForeArmSmash.get(p.getUniqueId()),"","Jump+Hit",  "Drop from Higher place will damage more", "Master LV.1"), 6, Wreskillsinv);
		itemset("Chopping", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Chopping.get(p.getUniqueId()),"","Increase fist damage, KnockBack Resistance"), 7, Wreskillsinv);
		itemset("FinishMove", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Damage will be increase by your explevels."), 9, Wreskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+wsd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Wreskillsinv);
	
		
		p.openInventory(Wreskillsinv);
	}


}
