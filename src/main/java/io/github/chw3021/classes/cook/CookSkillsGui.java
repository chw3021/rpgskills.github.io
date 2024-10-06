package io.github.chw3021.classes.cook;



import java.util.List;
import java.util.Arrays;
import java.io.File;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import net.md_5.bungee.api.ChatColor;

public class CookSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void CookSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		CookSkillsData esd = new CookSkillsData(CookSkillsData.loadData(path +"/plugins/RPGskills/CookSkillsData.data"));
		Inventory Cookskillsinv = Bukkit.createInventory(null, 18, "Cookskills");
		itemset("DessertRain", Material.WOODEN_SHOVEL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.DessertRain.getOrDefault(p.getUniqueId(),0),"","SwapHand","Master LV.50"), 0, Cookskillsinv);
		itemset("MushSpa", Material.STONE_SHOVEL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.MushSpa.getOrDefault(p.getUniqueId(),0),"","Sneaking + Rightclick","Master LV.50"), 1, Cookskillsinv);
		itemset("BerrySalad", Material.STONE_SHOVEL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.BerrySalad.getOrDefault(p.getUniqueId(),0),"","RightClick","Party: Remove Hunger,Poison","Wither,Blind,Nausea effect","","Mob: Attack with bush","Master LV.50"), 2, Cookskillsinv);
		itemset("MelonWall", Material.GOLDEN_SHOVEL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.MelonWall.getOrDefault(p.getUniqueId(),0),"","Stun Collided Mobs", "Master LV.1"), 3, Cookskillsinv);
		itemset("GrilledDishes", Material.DIAMOND_SHOVEL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.GrilledDishes.getOrDefault(p.getUniqueId(),0),"","SwapHand + Sneaking","Master LV.50"), 4, Cookskillsinv);
		itemset("Saturation", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.Saturation.getOrDefault(p.getUniqueId(),0),"","Increases damage","Increases Treated PartyPlayer's Damage"), 6, Cookskillsinv);
		itemset("Special Menu", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Damage increases by your explevels."), 9, Cookskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+esd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 17, Cookskillsinv);
	
		
		p.openInventory(Cookskillsinv);
	}


}
