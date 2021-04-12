package io.github.chw3021.forger;



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

public class ForSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new MaterialData(ID, (byte)data).toItemStack(stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void ForSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		ForSkillsData fsd = new ForSkillsData(ForSkillsData.loadData(path +"/plugins/RPGskills/ForSkillsData.data"));
		Inventory Forskillsinv = Bukkit.createInventory(null, 18, "Forskills");
		itemset("TNTLauncher", Material.WOODEN_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.TNTLauncher.get(p.getUniqueId()),"","SwapHand","Master LV.50"), 0, Forskillsinv);
		itemset("RailSMG", Material.STONE_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.RailSMG.get(p.getUniqueId()),"","Rightclick","Master LV.50"), 1, Forskillsinv);
		itemset("Shockwave", Material.STONE_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.DoubleBarrel.get(p.getUniqueId()),"","Hit + Sneaking", "Master LV.1"), 2, Forskillsinv);
		itemset("LightningCannon", Material.IRON_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.LightningCannon.get(p.getUniqueId()),"","Sneaking + Rightclick","Master LV.50"), 3, Forskillsinv);
		itemset("HoneyMissile", Material.GOLDEN_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.HoneyMissile.get(p.getUniqueId()),"","LeftClick + Jump", "Stick to hit enemies","Give additional damage for 20 hits", "Explode after 20 Hits or 15secs","Master LV.50"), 4, Forskillsinv);
		itemset("MachineGun", Material.DIAMOND_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.MachineGun.get(p.getUniqueId()),"","SwapHand + Sneaking","Can Change Projectile Using Mouse Scroll","Arrow: Piercing, Able to Hit EnderDragon","Bullet: More Damage, Able to Hit Wither","Master LV.50"), 5, Forskillsinv);
		itemset("Development", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Development.get(p.getUniqueId()),"","Increase Weapon damage","Reduce MachineGun Reloading Duration"), 6, Forskillsinv);
		itemset("DragonBreather", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Damage increase by your explevels.", "Reload MachineGun Immediately"), 9, Forskillsinv);
		itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.get(p.getUniqueId()),"","Click if you want to reset your skill's levels"), 17, Forskillsinv);
	
		
		p.openInventory(Forskillsinv);
	}


}
