package io.github.chw3021.classes.broiler;



import java.util.Arrays;
import java.io.File;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import io.github.chw3021.classes.SkillsGui;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class BroSkillsGui extends SkillsGui{
	
	
	public void BroSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		BroSkillsData wsd = new BroSkillsData(BroSkillsData.loadData(path +"/plugins/RPGskills/BroSkillsData.data"));
		Inventory skillsInv = Bukkit.createInventory(null, 36, "Broskills");
		itemset("TimeBomb", Material.WITHER_SKELETON_SKULL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.TimeBomb.getOrDefault(p.getUniqueId(),0),"","Swaphand + Rightclick","Untarget - Throw TimeBomb","Target - Plant Bomb to Target","Master LV.50"), 0, skillsInv);
		itemset("ChainHook", Material.WOODEN_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.ChainHook.getOrDefault(p.getUniqueId(),0),"","Swaphand","Untarget - Throw ChainHook","Use Once More to Teleport","Target - Pull Target to You","Reset Cooldown when you Kill Target", "Master LV.1"), 1, skillsInv);
		itemset("GlassBreak", Material.STONE_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.GlassBreak.getOrDefault(p.getUniqueId(),0),"","Hit + Sneaking","Target - Increases Bleeding Rate","Master LV.50"), 2, skillsInv);
		itemset("CactusTrap", Material.IRON_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.CactusTrap.getOrDefault(p.getUniqueId(),0),"","Sneaking + Rightclick","Untarget - Place CactusTrap","Target - Plant Trap To Target","Master LV.50"), 3, skillsInv);
		itemset("Crack", Material.GOLDEN_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Crack.getOrDefault(p.getUniqueId(),0),"","LeftClick + Jump","Untarget - Throw Block","Target - Throw Block to Target Directly","Master LV.50"), 4, skillsInv);
		itemset("DustEyes", Material.DIAMOND_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.DustEyes.getOrDefault(p.getUniqueId(),0),"","Rightclick","Untarget - Throw Dust","Target - Throw Dust to Target Directly","Master LV.50"), 5, skillsInv);
		itemset("OneOnly", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.OneOnly.getOrDefault(p.getUniqueId(),0),"","Set Entity to Target that Attacked by you", "Target Will get more damage by You", "Skill Performance get Better to Target", "Decreases Damage from Entity which isn't Target", "Change Item to Untarget"), 6, skillsInv);
		itemset("Unfair Match", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Duration will increases by your explevels."), 9, skillsInv);
		itemset("SkillPoints", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+wsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 17, skillsInv);
	
		
		Obtained.itemset(p, skillsInv);
		p.openInventory(skillsInv);
	}


}
