package io.github.chw3021.classes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class SkillsGui {
	
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
			input.add(ChatColor.BOLD+ "" +hit+ " X "+BigDecimal.valueOf(dam1 * (1 +  lv * dam2)).setScale(2, RoundingMode.HALF_EVEN)+"D");
		}
		if(masterlev != 0) {
			input.add(ChatColor.GOLD+"Master LV."+masterlev);
		}

		input.forEach(l -> {
			l=ChatColor.RESET+l;
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
			input.add(ChatColor.BOLD+ "" +hit+ " X "+BigDecimal.valueOf(dam1 * (1 +  lv * dam2)).setScale(2, RoundingMode.HALF_EVEN)+"D");
		}

		input.forEach(l -> {
			l=ChatColor.RESET+l;
		});
		
		items.setLore(input);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}

	public void itemset(String display, ItemStack ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new ItemStack(ID);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		List<String> input = new ArrayList<>();

		if(Lore.stream().anyMatch(s -> s.contains("Master"))) {
			Lore.stream().filter(s -> s.contains("Master")).forEach(l -> {
				Lore.set(Lore.indexOf(l),ChatColor.GOLD+ l);
			});
		}
		input.addAll(Lore);
		input.addAll(des(inv));
		input.forEach(l -> {
			l=ChatColor.RESET+l;
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

		if(Lore.stream().anyMatch(s -> s.contains("Master"))) {
			Lore.stream().filter(s -> s.contains("Master")).forEach(l -> {
				
				Lore.set(Lore.indexOf(l),ChatColor.GOLD+ l);
			});
		}
		input.addAll(Lore);
		input.addAll(des(inv));
		
		input.forEach(l -> {
			l=ChatColor.RESET+l;
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
			l=ChatColor.RESET+l;
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
			input.add(ChatColor.BOLD+ "" +hit+ " X "+BigDecimal.valueOf(dam1 * (1 +  lv * dam2)).setScale(2, RoundingMode.HALF_EVEN)+"D");
		}
		if(masterlev != 0) {
			input.add(ChatColor.GOLD+"Master LV."+masterlev);
		}
		input.addAll(des(inv));

		input.forEach(l -> {
			l=ChatColor.RESET+l;
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
			input.add(ChatColor.BOLD+ " X "+BigDecimal.valueOf(1 +lv * dam).setScale(2, RoundingMode.HALF_EVEN));
		}
		input.addAll(des(inv));

		input.forEach(l -> {
			l=ChatColor.RESET+l;
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
