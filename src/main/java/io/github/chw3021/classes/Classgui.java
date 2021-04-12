package io.github.chw3021.classes;



import java.util.List;
import java.util.Arrays;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

public class Classgui {
	
	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new ItemStack(ID);
		if(ID == Material.TIPPED_ARROW) {
			PotionMeta meta = (PotionMeta) item.getItemMeta();
			meta.setColor(Color.RED);
			item.setItemMeta(meta);
		}
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.setLore(Lore);
		items.setAttributeModifiers(null);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void open(Player p)
	{
		Inventory inv = Bukkit.createInventory(null, 54, "Classes");
		
		itemset("SwordMan", Material.IRON_SWORD, 0, 1, Arrays.asList("Recommended equipment:", "Swords in both hands", "", "Role: Bruiser", "Difficulty: Normal"), 0, inv);
		itemset("Hunter", Material.IRON_AXE, 0, 1, Arrays.asList("Recommended equipment:", "Axe(main), Without Shield", "", "Role: Nuker", "Difficulty: Hard"), 1, inv);
		itemset("Berserker", Material.CRIMSON_ROOTS, 0, 1, Arrays.asList("Recommended equipment:", "Sword & Hoe", "", "Role: Holder", "Difficulty: Easy"), 2, inv);
		itemset("Paladin", Material.SHIELD, 0, 1, Arrays.asList("Recommended equipment:", "Axe(main) & Shield(off)", "", "Role: Supporter", "Difficulty: Normal"), 3, inv);
		
		itemset("Archer", Material.BOW, 0, 1, Arrays.asList("Recommended equipment:", "Bow", "", "Role: Bruiser", "Difficulty: Normal"), 9, inv);
		itemset("Sniper", Material.CROSSBOW, 0, 1, Arrays.asList("Recommended equipment:", "Crossbow", "", "Role: Nuker", "Difficulty: Hard"), 10, inv);
		itemset("Launcher", Material.FIREWORK_ROCKET, 0, 1, Arrays.asList("Recommended equipment:", "Bow", "", "Role: Holder", "Difficulty: Easy"), 11, inv);
		itemset("Medic", Material.TIPPED_ARROW, 0, 1, Arrays.asList("Recommended equipment:", "Crossbow", "", "Role: Supporter", "Difficulty: Hard"), 12, inv);
		
		itemset("Boxer", Material.IRON_HELMET, 0, 1, Arrays.asList("Recommended equipment:", "Nuggets in both hands", "", "Role: Bruiser", "Difficulty: Hard"), 18, inv);
		itemset("Tamer", Material.LEAD, 0, 1, Arrays.asList("Recommended equipment:", "Nugget in both hands", "", "Role: Nuker", "Difficulty: Easy"), 19, inv);
		itemset("Wrestler", Material.GOLDEN_CHESTPLATE, 0, 1, Arrays.asList("Recommended equipment:", "Nugget in both hands", "", "Role: Holder", "Difficulty: Normal"), 20, inv);
		itemset("Taoist", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("Recommended equipment:", "Nugget in both hands", "", "Role: Supporter", "Difficulty: Normal"), 21, inv);
		
		itemset("FireMage", Material.FIRE_CHARGE, 0, 1, Arrays.asList("Recommended equipment:", "Stick or Book or Blazerod", "", "Role: Bruiser", "Difficulty: Normal"), 27, inv);
		itemset("Illusionist", Material.JACK_O_LANTERN, 0, 1, Arrays.asList("Recommended equipment:", "Stick or Book or Blazerod", "", "Role: Nuker", "Difficulty: Normal"), 28, inv);
		itemset("Witherist", Material.WITHER_ROSE, 0, 1, Arrays.asList("Recommended equipment:", "Hoe", "", "Role: Holder", "Difficulty: Normal"), 29, inv);
		itemset("WitchDoctor", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("Recommended equipment:", "Hoe, Totem", "", "Role: Supporter", "Difficulty: Hard"), 30, inv);

		itemset("Chemist", Material.POTION, 0, 1, Arrays.asList("Recommended equipment:", "Pickaxe", "", "Role: Bruiser", "Difficulty: Easy"), 36, inv);
		itemset("Forger", Material.BEACON, 0, 1, Arrays.asList("Recommended equipment:", "Pickaxe", "", "Role: Nuker", "Difficulty: Normal"), 37, inv);
		itemset("Engineer", Material.IRON_PICKAXE, 0, 1, Arrays.asList("Recommended equipment:", "Pickaxe", "", "Role: Holder", "Difficulty: Normal"), 38, inv);
		itemset("Cook", Material.COOKED_BEEF, 0, 1, Arrays.asList("Recommended equipment:", "Shovel, ", "", "Role: Supporter", "Difficulty: Easy"), 39, inv);

		itemset("OceanKnight", Material.TRIDENT, 0, 1, Arrays.asList("Recommended equipment:", "Trident(main) & Shield(off)", "", "Role: Bruiser", "Difficulty: Hard"), 45, inv);
		itemset("Nobility", Material.HEART_OF_THE_SEA, 0, 1, Arrays.asList("Recommended equipment:", "Trident", "", "Role: Nuker", "Difficulty: Normal"), 46, inv);
		itemset("Frostman", Material.PACKED_ICE, 0, 1, Arrays.asList("Recommended equipment:", "Prismarine Shard in both hands", "", "Role: Holder", "Difficulty: Hard"), 47, inv);
		itemset("Angler", Material.FISHING_ROD, 0, 1, Arrays.asList("Recommended equipment:", "FishingRod", "", "Role: Supporter", "Difficulty: Easy"), 48, inv);
		
		itemset("Gambler", Material.FLINT_AND_STEEL, 0, 1, Arrays.asList("Recommended equipment:", "Flint and Steel", "", "Role: Bruiser", "Difficulty: Hard"), 5, inv);
		itemset("Daggerist", Material.SHEARS, 0, 1, Arrays.asList("Recommended equipment:", "Shears in both Hands", "", "Role: Nuker", "Difficulty: Hard"), 6, inv);
		itemset("Broiler", Material.CHAIN, 0, 1, Arrays.asList("Recommended equipment:", "Brick or Ingot", "", "Role: Holder", "Difficulty: Hard"), 7, inv);
		itemset("Musician", Material.JUKEBOX, 0, 1, Arrays.asList("Recommended equipment:", "Disc", "", "Role: Supporter", "Difficulty: Easy"), 8, inv);
		p.openInventory(inv);
	}


}
