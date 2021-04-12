package io.github.chw3021.boxer;



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

public class BoxSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void BoxSkillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		BoxSkillsData bsd = new BoxSkillsData(BoxSkillsData.loadData(path +"/plugins/RPGskills/BoxSkillsData.data"));
		Inventory Boxskillsinv = Bukkit.createInventory(null, 18, "BoxSkills");
		itemset("FistForce", Material.LEATHER_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Parrying.get(p.getUniqueId()),"","SwapHand to release","FistForce will be Gathered", "When You're Damaged","Increase Armor When Full Charged", "Master Lv.1"), 0, Boxskillsinv);
		itemset("Weaving", Material.LEATHER_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Weaving.get(p.getUniqueId()),"","Sneaking","Master LV.1"), 1, Boxskillsinv);
		itemset("Straight", Material.IRON_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Straight.get(p.getUniqueId()),"","Rightclick","Master LV.50"), 2, Boxskillsinv);
		itemset("BodyBlow", Material.GOLDEN_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.BodyBlow.get(p.getUniqueId()),"","Sneaking + Rightclick to target","Master LV.50"), 3, Boxskillsinv);
		itemset("DempseyRoll", Material.DIAMOND_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.DempseyRoll.get(p.getUniqueId()),"","SwapHand + Sneaking","Master LV.50"), 4, Boxskillsinv);
		itemset("Counter", Material.CHAINMAIL_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Counter.get(p.getUniqueId()),"","Activate when you evade enemies attack", "using Parrying or Weaving or PullBack" ,"Master LV.50"), 5, Boxskillsinv);
		itemset("Rest", Material.WRITABLE_BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Rest.get(p.getUniqueId()),"","Sneaking(hold)","Master LV.1"), 6, Boxskillsinv);
		itemset("Parrying", Material.WRITABLE_BOOK, 0, 1, Arrays.asList("","If You Attacked While Punching or Using Skills", "Reduce Damage, Bounce if Projectile"), 8, Boxskillsinv);
		itemset("Training", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Training.get(p.getUniqueId()),"","Increase fist damage", "Chopper(Jump+Hit)/ UpperCut(Sneaking+Hit)"), 7, Boxskillsinv);
		itemset("Adrenaline", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Duration will be increase by your explevels."), 9, Boxskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+bsd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Boxskillsinv);
	
		
		p.openInventory(Boxskillsinv);
	}


}
