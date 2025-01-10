package io.github.chw3021.classes;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.chw3021.classes.angler.AngSkillsGui;
import io.github.chw3021.classes.archer.ArchSkillsGui;
import io.github.chw3021.classes.berserker.BerSkillsGui;
import io.github.chw3021.classes.boxer.BoxSkillsGui;
import io.github.chw3021.classes.broiler.BroSkillsGui;
import io.github.chw3021.classes.chemist.CheSkillsGui;
import io.github.chw3021.classes.cook.CookSkillsGui;
import io.github.chw3021.classes.engineer.EngSkillsGui;
import io.github.chw3021.classes.firemage.FireSkillsGui;
import io.github.chw3021.classes.forger.ForSkillsGui;
import io.github.chw3021.classes.frostman.FrostSkillsGui;
import io.github.chw3021.classes.hunter.HunSkillsGui;
import io.github.chw3021.classes.illusionist.IllSkillsGui;
import io.github.chw3021.classes.launcher.LaunSkillsGui;
import io.github.chw3021.classes.medic.MedSkillsGui;
import io.github.chw3021.classes.nobility.NobSkillsGui;
import io.github.chw3021.classes.oceanknight.OceSkillsGui;
import io.github.chw3021.classes.paladin.PalSkillsGui;
import io.github.chw3021.classes.sniper.SnipSkillsGui;
import io.github.chw3021.classes.swordman.SwordSkillsGui;
import io.github.chw3021.classes.tamer.TamSkillsGui;
import io.github.chw3021.classes.taoist.TaoSkillsGui;
import io.github.chw3021.classes.witchdoctor.WdcSkillsGui;
import io.github.chw3021.classes.witherist.WitSkillsGui;
import io.github.chw3021.classes.wreltler.WreSkillsGui;
import net.md_5.bungee.api.ChatColor;

public class SkillsGui {
	
	private ItemStack getSkillIcon() {
		
        ItemStack icon = new ItemStack(Material.NETHER_STAR);
        ItemMeta iconmeta = icon.getItemMeta();
        iconmeta.setDisplayName(ChatColor.GOLD + "Skills");
        iconmeta.setEnchantmentGlintOverride(true);
        icon.setItemMeta(iconmeta);
        icon.addUnsafeEnchantment(Enchantment.AQUA_AFFINITY, 1);
        return icon;
    }
	
	public static void openSkillsGui(Player p) {

		if(ClassData.pc.get(p.getUniqueId()) == 0)
		{
			SwordSkillsGui ssg = new SwordSkillsGui();
			ssg.SwordSkillsinv(p);
			ssg.SwordSkillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 1)
		{
			BerSkillsGui bsg = new BerSkillsGui();
			bsg.Berskillsinv(p);
			bsg.Berskillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 2)
		{
			HunSkillsGui hsg = new HunSkillsGui();
			hsg.Hunskillsinv(p);
			hsg.Hunskillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 3)
		{
			PalSkillsGui psg = new PalSkillsGui();
			psg.PalSkillsinv(p);
			psg.PalSkillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 4)
		{
			SnipSkillsGui l = new SnipSkillsGui();
			l.Snipskillsinv(p);
			l.Snipskillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 5)
		{
			LaunSkillsGui l = new LaunSkillsGui();
			l.Launskillsinv(p);
			l.Launskillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 6)
		{
			ArchSkillsGui l = new ArchSkillsGui();
			l.Archskillsinv(p);
			l.Archskillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 61)
		{
			MedSkillsGui l = new MedSkillsGui();
			l.Medskillsinv(p);
			l.Medskillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 7)
		{
			BoxSkillsGui l = new BoxSkillsGui();
			l.BoxSkillsinv(p);
			l.BoxSkillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 8)
		{
			WreSkillsGui wrg = new WreSkillsGui();
			wrg.WreSkillsinv(p);
			wrg.WreSkillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 9)
		{
			TamSkillsGui tsg = new TamSkillsGui();
			tsg.TamSkillsinv(p);
			tsg.TamSkillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 10)
		{
			TaoSkillsGui tag = new TaoSkillsGui();
			tag.TaoSkillsinv(p);
			tag.TaoSkillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 11)
		{
			IllSkillsGui ilg = new IllSkillsGui();
			ilg.IllSkillsinv(p);
			ilg.IllSkillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 12)
		{
			FireSkillsGui fsg = new FireSkillsGui();
			fsg.FireSkillsinv(p);
			fsg.FireSkillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 13)
		{
			WitSkillsGui wsg = new WitSkillsGui();
			wsg.WitSkillsinv(p);
			wsg.WitSkillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 14)
		{
			WdcSkillsGui wdg = new WdcSkillsGui();
			wdg.WdcSkillsinv(p);
			wdg.WdcSkillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 15)
		{
			CheSkillsGui chg = new CheSkillsGui();
			chg.CheSkillsinv(p);
			chg.CheSkillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 16)
		{
			ForSkillsGui fgg = new ForSkillsGui();
			fgg.ForSkillsinv(p);
			fgg.ForSkillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 17)
		{
			EngSkillsGui egg = new EngSkillsGui();
			egg.EngSkillsinv(p);
			egg.EngSkillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 18)
		{
			CookSkillsGui csg = new CookSkillsGui();
			csg.CookSkillsinv(p);
			csg.CookSkillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 19)
		{
			NobSkillsGui nsg = new NobSkillsGui();
			nsg.NobSkillsinv(p);
			nsg.NobSkillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 20)
		{
			OceSkillsGui osg = new OceSkillsGui();
			osg.OceSkillsinv(p);
			osg.OceSkillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 21)
		{
			FrostSkillsGui fsg = new FrostSkillsGui();
			fsg.FrostSkillsinv(p);
			fsg.FrostSkillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 22)
		{
			AngSkillsGui fsg = new AngSkillsGui();
			fsg.AngSkillsinv(p);
			fsg.AngSkillsinv(p);
		}
		else if(ClassData.pc.get(p.getUniqueId()) == 25)
		{
			BroSkillsGui bsg = new BroSkillsGui();
			bsg.BroSkillsinv(p);
			bsg.BroSkillsinv(p);
		}
	}
	
	
	final protected ItemStack SKILLPOINT = getSkillIcon();
	
	final protected BigDecimal demical(Double d) {
		return BigDecimal.valueOf(d).setScale(2, RoundingMode.HALF_EVEN);
	}

	public void itemset(String display, Material ID, int data, int stack, List<String> Lore,int lv, int hit, double dam1, double dam2, int masterlev, int loc, Inventory inv) {
		ItemStack item = new ItemStack(ID, stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		List<String> input = new ArrayList<>();
		input.add(ChatColor.AQUA + "LV." + lv);
		input.add("");
		input.addAll(Lore);
		input.addAll(des(inv));
		input.add("");
		if(dam1 != 0) {
			input.add(ChatColor.BOLD+ "" +ChatColor.LIGHT_PURPLE+hit+ " X "+BigDecimal.valueOf(dam1 * (1 +  lv * dam2)).setScale(2, RoundingMode.HALF_EVEN)+"D");
		}
		if(masterlev != 0) {
			input.add(ChatColor.GOLD+"Master LV."+masterlev);
		}

		input.stream().filter(s -> !s.contains("X") && !s.contains("Master")).forEach(l -> {
			input.set(input.indexOf(l), ChatColor.RESET + (ChatColor.of(Color.LIGHT_GRAY) + l));
		});
		
		items.setLore(input);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}

	public void itemset(String display, Material ID, int data, int stack, List<String> Lore,int lv, int hit, double dam1, double dam2, int loc, Inventory inv) {
		ItemStack item = new ItemStack(ID, stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		List<String> input = new ArrayList<>();
		input.addAll(Lore);
		input.addAll(des(inv));
		input.add("");
		if(dam1 != 0) {
			input.add(ChatColor.BOLD+ "" + ChatColor.LIGHT_PURPLE+hit+ " X "+BigDecimal.valueOf(dam1 * (1 +  lv * dam2)).setScale(2, RoundingMode.HALF_EVEN)+"D");
		}
		
		if(input.stream().anyMatch(s -> s.contains("Master"))) {
			input.stream().filter(s -> s.contains("Master")).forEach(l -> {
				
				input.set(input.indexOf(l),ChatColor.GOLD+ l);
			});
		}
		input.stream().filter(s -> !s.contains("X") && !s.contains("Master")).forEach(l -> {
			input.set(input.indexOf(l), ChatColor.RESET + (ChatColor.of(Color.LIGHT_GRAY) + l));
		});
		
		items.setLore(input);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}

	public void itemset(String display, ItemStack ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = ID;
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		List<String> input = new ArrayList<>();

		input.addAll(Lore);
		input.addAll(des(inv));
		
		if(input.stream().anyMatch(s -> s.contains("X"))) {
			input.stream().filter(s -> s.contains("X")).forEach(l -> {
				
				input.set(input.indexOf(l),ChatColor.LIGHT_PURPLE+ l);
			});
		}
		
		if(input.stream().anyMatch(s -> s.contains("Master"))) {
			input.stream().filter(s -> s.contains("Master")).forEach(l -> {
				
				input.set(input.indexOf(l),ChatColor.GOLD+ l);
			});
		}
		input.stream().filter(s -> !s.contains("X") && !s.contains("Master")).forEach(l -> {
			input.set(input.indexOf(l), ChatColor.RESET + (ChatColor.of(Color.LIGHT_GRAY) + l));
		});
		
		items.setLore(input);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}

	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new ItemStack(ID);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		List<String> input = new ArrayList<>();

		input.addAll(Lore);
		input.addAll(des(inv));
		
		if(input.stream().anyMatch(s -> s.contains("X"))) {
			input.stream().filter(s -> s.contains("X")).forEach(l -> {
				
				input.set(input.indexOf(l),ChatColor.LIGHT_PURPLE+ l);
			});
		}
		
		if(input.stream().anyMatch(s -> s.contains("Master"))) {
			input.stream().filter(s -> s.contains("Master")).forEach(l -> {
				
				input.set(input.indexOf(l),ChatColor.GOLD+ l);
			});
		}
		input.stream().filter(s -> !s.contains("X") && !s.contains("Master")).forEach(l -> {
			input.set(input.indexOf(l), ChatColor.RESET + (ChatColor.of(Color.LIGHT_GRAY) + l));
		});
		
		items.setLore(input);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}


	public void basic(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new ItemStack(ID);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		List<String> input = new ArrayList<>();
		input.addAll(Lore);
		input.addAll(des(inv));
		
		input.forEach(l -> {
			input.set(input.indexOf(l), ChatColor.RESET + (ChatColor.of(Color.LIGHT_GRAY) + l));
		});
		items.setLore(input);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}

	public void basic(String display, Material ID, int data, int stack, List<String> Lore,int lv, int hit, double dam1, double dam2, int masterlev, int loc, Inventory inv) {
		ItemStack item = new ItemStack(ID, stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		List<String> input = new ArrayList<>();
		input.add(ChatColor.AQUA + "LV." + lv);
		input.add("");
		input.addAll(Lore);
		input.add("");
		if(dam1 != 0) {
			input.add(ChatColor.LIGHT_PURPLE+ "" +hit+ " X "+BigDecimal.valueOf(dam1 * (1 +  lv * dam2)).setScale(2, RoundingMode.HALF_EVEN)+"D");
		}
		if(masterlev != 0) {
			input.add(ChatColor.GOLD+"Master LV."+masterlev);
		}
		input.addAll(des(inv));

		input.forEach(l -> {
			input.set(input.indexOf(l), ChatColor.RESET + (ChatColor.of(Color.LIGHT_GRAY) + l));
		});
		
		items.setLore(input);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}

	public void passive(String display, Material ID, int data, int stack, List<String> Lore,int lv, double dam, int loc, Inventory inv) {
		ItemStack item = new ItemStack(ID, stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		List<String> input = new ArrayList<>();
		input.add(ChatColor.AQUA + "LV." + lv);
		input.add("");
		input.addAll(Lore);
		input.add("");
		if(dam != 0) {
			input.add(ChatColor.LIGHT_PURPLE+ " X "+BigDecimal.valueOf(1 +lv * dam).setScale(2, RoundingMode.HALF_EVEN));
		}
		input.addAll(des(inv));

		input.forEach(l -> {
			input.set(input.indexOf(l), ChatColor.RESET + (ChatColor.of(Color.LIGHT_GRAY) + l));
		});
		
		items.setLore(input);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	final private List<String> des(Inventory inv){
		List<String> retL = new ArrayList<>();;
		retL.add("");
		retL.add(ChatColor.YELLOW + "[Shift + Left]: + Max");
		retL.add(ChatColor.YELLOW + "[Shift + Right]: - Max");
		return retL;
	}
}
