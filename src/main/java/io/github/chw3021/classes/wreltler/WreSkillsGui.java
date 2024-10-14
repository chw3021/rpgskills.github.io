package io.github.chw3021.classes.wreltler;



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
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.classes.SkillsGui;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class WreSkillsGui extends SkillsGui{
	


	
	public void WreSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		WreSkillsData wsd = new WreSkillsData(WreSkillsData.loadData(path +"/plugins/RPGskills/WreSkillsData.data"));
		Inventory Wreskillsinv = Bukkit.createInventory(null, 54, "WreSkills");
		Obtained.itemset(p, Wreskillsinv);

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("BodyPress", Material.LEATHER_CHESTPLATE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.BodyPress.get(p.getUniqueId()),"",ChatColor.UNDERLINE+"[Earth]","Jump + RightClick" ,"Master LV.50"), 0, Wreskillsinv);
			itemset("Tackle", Material.IRON_LEGGINGS, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Tackle.get(p.getUniqueId()),"",ChatColor.UNDERLINE+"[Earth]","Sneaking + Rightclick" ,"Master LV.1"), 1, Wreskillsinv);
			itemset("Suplex", Material.LEATHER_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Suplex.get(p.getUniqueId()),"",ChatColor.UNDERLINE+"[Earth]","SwapHand + Sneaking" ,"Master LV.50"), 2, Wreskillsinv);
			itemset("ArmThrow", Material.IRON_CHESTPLATE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.ArmThrow.get(p.getUniqueId()),"",ChatColor.UNDERLINE+"[Earth]","SwapHand" ,"Master LV.50"), 3, Wreskillsinv);
			itemset("GiantSwing", Material.BRICK_WALL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.GiantSwing.get(p.getUniqueId()),"",ChatColor.UNDERLINE+"[Earth]","Sneaking + Hit","LeftClick Or Using Another Skill To Quit Earlier","Master LV.50"), 4, Wreskillsinv);
			itemset("Squall", Material.CHAINMAIL_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.ChokeSlam.get(p.getUniqueId()),"",ChatColor.UNDERLINE+"[Earth]","Get Jump,Speed,Resistance effect", "when you're Sprinting", "Hit Near By Enemies", "Master LV.1"), 5, Wreskillsinv);
			itemset("ForeArmSmash", Material.WRITABLE_BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.ForeArmSmash.get(p.getUniqueId()),"",ChatColor.UNDERLINE+"[Earth]","Jump+Hit",  "Higher Height will give more damage", "Master LV.50"), 6, Wreskillsinv);
			itemset("Tough", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Chopping.get(p.getUniqueId()),"","Increases Damage, KnockBack Resistance"), 7, Wreskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("Somersault(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Wreskillsinv);
				itemset("Pounding(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Wreskillsinv);
				itemset("PileDriver(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Wreskillsinv);
				itemset("BackDrop(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Wreskillsinv);
				itemset("Whirlwind(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Wreskillsinv);
				itemset("Slap(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Wreskillsinv);
				itemset("Submission(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Wreskillsinv);
				itemset("Tornado Drop(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Wreskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Somersault", Material.SLIME_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Somersault When Use Once More","(Damage Affected By BodyPress)"), 9, Wreskillsinv);
				itemset("Pounding", Material.SADDLE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Pounding When Use Once More","(Damage Affected By Tackle)"), 10, Wreskillsinv);
				itemset("BackDrop", Material.CRACKED_DEEPSLATE_BRICKS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use BackDrop When Use Once More","(Damage Affected By Suplex)"), 11, Wreskillsinv);
				itemset("PileDriver", Material.SKELETON_SKULL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use PileDriver When Use Once More","(Damage Affected By ArmThrow)"), 12, Wreskillsinv);
				itemset("Whirlwind", Material.STRING, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Damages And Attracts Near By Enemies","(Damage Affected By GiantSwing)"), 13, Wreskillsinv);
				itemset("Slap", Material.IRON_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Hold Hit Enemies Shortly"), 14, Wreskillsinv);
				itemset("Submission", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Holding Duration"), 16, Wreskillsinv);
				itemset("Tornado Drop", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Sneaking + ThrowItem"), 17, Wreskillsinv);
				
				itemset("MoonSault(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, Wreskillsinv);
				itemset("MuscleBomb(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20,Wreskillsinv);
				itemset("SlamToBlow(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21,Wreskillsinv);
				itemset("Fighter On The Ring(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Wreskillsinv);
				itemset("Cyclone Driver(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Wreskillsinv);
			}
			else {
				itemset("Somersault", Material.SLIME_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Somersault When Use Once More","(Damage Affected By BodyPress)"), 9, Wreskillsinv);
				itemset("Pounding", Material.SADDLE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Pounding When Use Once More","(Damage Affected By Tackle)"), 10, Wreskillsinv);
				itemset("BackDrop", Material.CRACKED_DEEPSLATE_BRICKS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use BackDrop When Use Once More","(Damage Affected By Suplex)"), 11, Wreskillsinv);
				itemset("PileDriver", Material.SKELETON_SKULL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use PileDriver When Use Once More","(Damage Affected By ArmThrow)"), 12, Wreskillsinv);
				itemset("Whirlwind", Material.STRING, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Damages And Attracts Near By Enemies","(Damage Affected By GiantSwing)"), 13, Wreskillsinv);
				itemset("Slap", Material.IRON_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Hold Hit Enemies Shortly"), 14, Wreskillsinv);
				itemset("Submission", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Holding Duration"), 16, Wreskillsinv);
				itemset("Tornado Drop", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Sneaking + ThrowItem"), 17, Wreskillsinv);
				
				itemset("MoonSault", Material.ROOTED_DIRT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Somersault When Use Once More","(Damage Affected By BodyPress)"), 18, Wreskillsinv);
				itemset("MuscleBomb", Material.TNT_MINECART, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Somersault When Use Once More","(Damage Affected By BodyPress)"), 20,Wreskillsinv);
				itemset("SlamToBlow", Material.BONE_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Somersault When Use Once More","(Damage Affected By BodyPress)"), 21,Wreskillsinv);
				itemset("Fighter On The Ring", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increass Damage & Armor","Decreases Tornado Drop Cooldown"), 25, Wreskillsinv);
				itemset("Cyclone Driver", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Sneaking + ThrowItem"), 26, Wreskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Wreskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+wsd.SkillPoints.getOrDefault(p.getUniqueId(), 0),"","Click if you want to reset your skill's levels"), 35, Wreskillsinv);

			
		}
		else {
			itemset("BodyPress", Material.LEATHER_CHESTPLATE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.BodyPress.get(p.getUniqueId()),"",ChatColor.UNDERLINE+"[Earth]","Jump + RightClick" ,"Master LV.50"), 0, Wreskillsinv);
			itemset("Tackle", Material.IRON_LEGGINGS, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Tackle.get(p.getUniqueId()),"",ChatColor.UNDERLINE+"[Earth]","Sneaking + Rightclick" ,"Master LV.1"), 1, Wreskillsinv);
			itemset("Suplex", Material.LEATHER_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Suplex.get(p.getUniqueId()),"",ChatColor.UNDERLINE+"[Earth]","SwapHand + Sneaking" ,"Master LV.50"), 2, Wreskillsinv);
			itemset("ArmThrow", Material.IRON_CHESTPLATE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.ArmThrow.get(p.getUniqueId()),"",ChatColor.UNDERLINE+"[Earth]","SwapHand" ,"Master LV.50"), 3, Wreskillsinv);
			itemset("GiantSwing", Material.TRIPWIRE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.GiantSwing.get(p.getUniqueId()),"",ChatColor.UNDERLINE+"[Earth]","Sneaking + Hit","LeftClick Or Using Another Skill To Quit Earlier","Master LV.50"), 4, Wreskillsinv);
			itemset("Squall", Material.CHAINMAIL_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.ChokeSlam.get(p.getUniqueId()),"",ChatColor.UNDERLINE+"[Earth]","Get Jump,Speed,Resistance effect", "when you're Sprinting", "Hit Near By Enemies", "Master LV.1"), 5, Wreskillsinv);
			itemset("ForeArmSmash", Material.WRITABLE_BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.ForeArmSmash.get(p.getUniqueId()),"",ChatColor.UNDERLINE+"[Earth]","Jump+Hit",  "Higher Height will give more damage", "Master LV.50"), 6, Wreskillsinv);
			itemset("Tough", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Chopping.get(p.getUniqueId()),"","Increases Damage, KnockBack Resistance"), 7, Wreskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("Somersault(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Wreskillsinv);
				itemset("Pounding(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Wreskillsinv);
				itemset("PileDriver(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Wreskillsinv);
				itemset("BackDrop(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Wreskillsinv);
				itemset("Whirlwind(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Wreskillsinv);
				itemset("Slap(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Wreskillsinv);
				itemset("Submission(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Wreskillsinv);
				itemset("Tornado Drop(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Wreskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Somersault", Material.SLIME_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Somersault When Use Once More","(Damage Affected By BodyPress)"), 9, Wreskillsinv);
				itemset("Pounding", Material.SADDLE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Pounding When Use Once More","(Damage Affected By Tackle)"), 10, Wreskillsinv);
				itemset("BackDrop", Material.CRACKED_DEEPSLATE_BRICKS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use BackDrop When Use Once More","(Damage Affected By Suplex)"), 11, Wreskillsinv);
				itemset("PileDriver", Material.SKELETON_SKULL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use PileDriver When Use Once More","(Damage Affected By ArmThrow)"), 12, Wreskillsinv);
				itemset("Whirlwind", Material.STRING, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Damages And Attracts Near By Enemies","(Damage Affected By GiantSwing)"), 13, Wreskillsinv);
				itemset("Slap", Material.IRON_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Hold Hit Enemies Shortly"), 14, Wreskillsinv);
				itemset("Submission", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Holding Duration"), 16, Wreskillsinv);
				itemset("Tornado Drop", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Sneaking + ThrowItem"), 17, Wreskillsinv);
				
				itemset("MoonSault(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, Wreskillsinv);
				itemset("MuscleBomb(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20,Wreskillsinv);
				itemset("SlamToBlow(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21,Wreskillsinv);
				itemset("Fighter On The Ring(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Wreskillsinv);
				itemset("Cyclone Driver(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Wreskillsinv);
			}
			else {
				itemset("Somersault", Material.SLIME_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Somersault When Use Once More","(Damage Affected By BodyPress)"), 9, Wreskillsinv);
				itemset("Pounding", Material.SADDLE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Pounding When Use Once More","(Damage Affected By Tackle)"), 10, Wreskillsinv);
				itemset("BackDrop", Material.CRACKED_DEEPSLATE_BRICKS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use BackDrop When Use Once More","(Damage Affected By Suplex)"), 11, Wreskillsinv);
				itemset("PileDriver", Material.SKELETON_SKULL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use PileDriver When Use Once More","(Damage Affected By ArmThrow)"), 12, Wreskillsinv);
				itemset("Whirlwind", Material.STRING, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Damages And Attracts Near By Enemies","(Damage Affected By GiantSwing)"), 13, Wreskillsinv);
				itemset("Slap", Material.IRON_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Hold Hit Enemies Shortly"), 14, Wreskillsinv);
				itemset("Submission", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Holding Duration"), 16, Wreskillsinv);
				itemset("Tornado Drop", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Sneaking + ThrowItem"), 17, Wreskillsinv);
				
				itemset("MoonSault", Material.ROOTED_DIRT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Somersault When Use Once More","(Damage Affected By BodyPress)"), 18, Wreskillsinv);
				itemset("MuscleBomb", Material.TNT_MINECART, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Somersault When Use Once More","(Damage Affected By BodyPress)"), 20,Wreskillsinv);
				itemset("SlamToBlow", Material.BONE_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Somersault When Use Once More","(Damage Affected By BodyPress)"), 21,Wreskillsinv);
				itemset("Fighter On The Ring", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increass Damage & Armor","Decreases Tornado Drop Cooldown"), 25, Wreskillsinv);
				itemset("Cyclone Driver", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Sneaking + ThrowItem"), 26, Wreskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Wreskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+wsd.SkillPoints.getOrDefault(p.getUniqueId(), 0),"","Click if you want to reset your skill's levels"), 35, Wreskillsinv);

			
		}
		
		p.openInventory(Wreskillsinv);
	}


}
