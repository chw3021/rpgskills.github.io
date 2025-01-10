package io.github.chw3021.classes.cook;



import java.util.Arrays;
import java.io.File;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import io.github.chw3021.classes.SkillsGui;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class CookSkillsGui extends SkillsGui{
	
	
	public void CookSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		CookSkillsData esd = new CookSkillsData(CookSkillsData.loadData(path +"/plugins/RPGskills/CookSkillsData.data"));
		Inventory skillsInv = Bukkit.createInventory(null, 36, "Cookskills");
		itemset("DessertRain", Material.WOODEN_SHOVEL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.DessertRain.getOrDefault(p.getUniqueId(),0),"","SwapHand","Master LV.50"), 0, skillsInv);
		itemset("MushSpa", Material.STONE_SHOVEL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.MushSpa.getOrDefault(p.getUniqueId(),0),"","Sneaking + Rightclick","Master LV.50"), 1, skillsInv);
		itemset("BerrySalad", Material.STONE_SHOVEL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.BerrySalad.getOrDefault(p.getUniqueId(),0),"","RightClick","Party: Remove Hunger,Poison","Wither,Blind,Nausea effect","","Mob: Attack with bush","Master LV.50"), 2, skillsInv);
		itemset("MelonWall", Material.GOLDEN_SHOVEL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.MelonWall.getOrDefault(p.getUniqueId(),0),"","Stun Collided Mobs", "Master LV.1"), 3, skillsInv);
		itemset("GrilledDishes", Material.DIAMOND_SHOVEL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.GrilledDishes.getOrDefault(p.getUniqueId(),0),"","SwapHand + Sneaking","Master LV.50"), 4, skillsInv);
		itemset("Saturation", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+esd.Saturation.getOrDefault(p.getUniqueId(),0),"","Increases damage","Increases Treated PartyPlayer's Damage"), 6, skillsInv);
		itemset("Special Menu", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Damage increases by your explevels."), 9, skillsInv);
		itemset("SkillPoints", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+esd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 17, skillsInv);
	
		
		Obtained.itemset(p, skillsInv);
		p.openInventory(skillsInv);
	}


}
