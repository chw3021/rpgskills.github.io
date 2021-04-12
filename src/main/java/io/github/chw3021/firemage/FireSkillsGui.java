package io.github.chw3021.firemage;



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

public class FireSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void FIreSkillsinv(Player p)
	{
		Inventory FIreskillsinv = Bukkit.createInventory(null, 18, "FIreskills");
	    String path = new File("").getAbsolutePath();
		FireSkillsData fsd = new FireSkillsData(FireSkillsData.loadData(path +"/plugins/RPGskills/FireSkillsData.data"));
		itemset("Squrt", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.FlowingLava.get(p.getUniqueId()),"","RightClick", "Master Lv.50"), 0, FIreskillsinv);
		itemset("Ring", Material.FIRE_CORAL_FAN, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Ring.get(p.getUniqueId()),"","Sneaking + RightClick", "Master Lv.50"), 1, FIreskillsinv);
		itemset("Fireball", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Fireball.get(p.getUniqueId()),"","LeftClick + Jump", "Master Lv.50"), 2, FIreskillsinv);
		itemset("Spread", Material.BLAZE_ROD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Spread.get(p.getUniqueId()),"","Sneaking + Hit", "Master Lv.50"), 3, FIreskillsinv);
		itemset("Breath", Material.CAMPFIRE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Breath.get(p.getUniqueId()),"","SwapHand", "Master Lv.50"), 4, FIreskillsinv);
		itemset("AliveFlame", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.AliveFlame.get(p.getUniqueId()),"","SwapHand + Sneaking", "Master Lv.50"), 5, FIreskillsinv);
		itemset("HotBody", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.HotBody.get(p.getUniqueId()),"","Increase Skill Damage", "Burn enime which attacked by you or attack you", "Get Fire Resistance", "Reflect 5% Damage (1% to Player)", "Master Lv.50"), 7, FIreskillsinv);
		itemset("Phoenix Flap", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Ult's damage is only affected by your explevels."), 9, FIreskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, FIreskillsinv);
	
		
		p.openInventory(FIreskillsinv);
	}


}
