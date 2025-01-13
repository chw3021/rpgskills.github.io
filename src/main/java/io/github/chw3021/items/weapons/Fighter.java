package io.github.chw3021.items.weapons;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.SmithItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import io.github.chw3021.commons.ConfigManager;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.rmain.RMain;

public class Fighter extends Weapons implements Listener {

	
	final private String lang = ConfigManager.getInstance(RMain.getInstance()).getCustomConfig().getString("Language");
	Pak pak = Pak.getInstance();


	@EventHandler
	public void KnuckleEquip(PlayerSwapHandItemsEvent ev) {
		Player p = ev.getPlayer();
		ItemStack pis = p.getInventory().getItemInMainHand();
		ItemStack pos = p.getInventory().getItemInOffHand();
		if (pis.getAmount() >= 1 && pis.hasItemMeta() && pis.getItemMeta().hasCustomModelData()
				&& pis.getType().name().contains("BANNER_PATTERN") && !pis.getItemMeta().getItemName().contains("CopiedKnuckle") && pos.getType() == Material.AIR) {
			ev.setCancelled(true);
			ItemStack swap = pis.clone();
			ItemMeta sm = swap.getItemMeta();
			sm.setItemName("CopiedKnuckle");
			sm.getPersistentDataContainer().remove(nethercore);
			sm.getPersistentDataContainer().remove(endercore);
			sm.getPersistentDataContainer().remove(herocore);
			sm =pak.remelm(sm);
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				sm.setLore(Arrays.asList("보조 너클"));
			}
			else {
				sm.setLore(Arrays.asList("Second Knuckle"));
			}
			swap.setItemMeta(sm);
			swap.setAmount(1);
			p.getInventory().setItemInOffHand(swap);
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_IRON, 1, 1.36f);
		}

	}

	@EventHandler
	public void KnuckleUnEquip(InventoryClickEvent ev) {
		Player p =(Player) ev.getView().getPlayer();
		if(ev.getCurrentItem() != null && ev.getCurrentItem().hasItemMeta()) {
			ItemMeta im = ev.getCurrentItem().getItemMeta();
			if(im.hasItemName() && im.getItemName().equals("CopiedKnuckle")) {
				final ItemStack is = ev.getCurrentItem();
				ev.setResult(Result.DENY);
				ev.setCurrentItem(null);
				is.setAmount(0);
			}
			else {
				for(ItemStack is : p.getInventory().getContents()) {
					if(is != null && is.hasItemMeta()) {
						ItemMeta im1 = is.getItemMeta();
						if(im1.hasItemName() && im1.getItemName().equals("CopiedKnuckle")) {
							is.setAmount(0);
						}
					}
				}
			}
		}

	}

	@EventHandler
	public void KnuckleUnEquip(PlayerDropItemEvent ev) {
		if(ev.getItemDrop().getItemStack() != null && !ev.isCancelled()) {
			Player p =(Player) ev.getPlayer();
			ItemStack pis = ev.getItemDrop().getItemStack();
			if(pis.getItemMeta().hasItemName() && pis.getItemMeta().getItemName().equals("CopiedKnuckle")) {
				pis.setAmount(0);
			}
			for(ItemStack is : p.getInventory().getContents()) {
				if(is != null && is.hasItemMeta()) {
					ItemMeta im = is.getItemMeta();
					if(im.hasItemName() && im.getItemName().equals("CopiedKnuckle")) {
						is.setAmount(0);
					}
				}
			}
		}
	}

	@EventHandler
	public void KnuckleUnEquip(PlayerDeathEvent ev) 
	{
		if(!ev.getKeepInventory()) {

	       	 List<ItemStack> di = ev.getDrops();
	       	 di.forEach(is -> {
	       		 ItemMeta im = is.getItemMeta();
	       		 if(im.hasItemName() && im.getItemName().equals("CopiedKnuckle")) {
	       			 is.setAmount(0);
	       		 }
	       	 });
		}
	}
	
	@EventHandler
	public void WoodenKnuckle(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.GLOBE_BANNER_PATTERN, 1);
		ItemMeta meta = is.getItemMeta();
		meta.removeAttributeModifier(Attribute.ATTACK_DAMAGE);
		meta.removeAttributeModifier(Attribute.ATTACK_SPEED);
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 1, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 1, Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
		meta.addAttributeModifier(Attribute.ATTACK_SPEED,
				new AttributeModifier(getKey(), 8,
						Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		meta.addAttributeModifier(Attribute.ATTACK_SPEED,
				new AttributeModifier(getKey(), 8,
						Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
		meta.setCustomModelData(1000);
		meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		if(lang.contains("kr")){
			meta.setLore(Arrays.asList("손교체시 장착"));
			meta.setDisplayName("나무 너클");
			meta.setItemName("나무 너클");
		}
		else {
			meta.setDisplayName("Wooden Knuckle");
			meta.setItemName("Wooden Knuckle");
			meta.setLore(Arrays.asList("SwapHands To Equip"));
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "wooden_knuckle"), is);
		rc.shape("xax", " x ");
		rc.setIngredient('x', rcc);
		rc.setIngredient('a', Material.STICK);
		Bukkit.getServer().addRecipe(rc);
	}
	
	@EventHandler
	public void StoneKnuckle(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.GLOBE_BANNER_PATTERN, 1);
		ItemMeta meta = is.getItemMeta();
		meta.removeAttributeModifier(Attribute.ATTACK_DAMAGE);
		meta.removeAttributeModifier(Attribute.ATTACK_SPEED);
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 1, Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
		meta.addAttributeModifier(Attribute.ATTACK_SPEED,
				new AttributeModifier(getKey(), 8,
						Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		meta.addAttributeModifier(Attribute.ATTACK_SPEED,
				new AttributeModifier(getKey(), 8,
						Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
		meta.setCustomModelData(1000 +1);
		meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		if(lang.contains("kr")){
			meta.setLore(Arrays.asList("손교체시 장착"));
			meta.setDisplayName("돌 너클");
			meta.setItemName("돌 너클");
		}
		else {
			meta.setDisplayName("Stone Knuckle");
			meta.setItemName("Stone Knuckle");
			meta.setLore(Arrays.asList("SwapHands To Equip"));
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "stone_knuckle"), is);
		rc.shape("xax", " x ");
		rc.setIngredient('x', Material.COBBLESTONE);
		rc.setIngredient('a', Material.STICK);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void GoldKnuckle(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.GLOBE_BANNER_PATTERN, 1);
		ItemMeta meta = is.getItemMeta();
		meta.removeAttributeModifier(Attribute.ATTACK_DAMAGE);
		meta.removeAttributeModifier(Attribute.ATTACK_SPEED);
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 1, Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
		meta.addAttributeModifier(Attribute.ATTACK_SPEED,
				new AttributeModifier(getKey(), 8,
						Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		meta.addAttributeModifier(Attribute.ATTACK_SPEED,
				new AttributeModifier(getKey(), 8,
						Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
		meta.setCustomModelData(1000 + 2);
		meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		if(lang.contains("kr")){
			meta.setLore(Arrays.asList("손교체시 장착"));
			meta.setDisplayName("금 너클");
			meta.setItemName("금 너클");
		}
		else {
			meta.setDisplayName("Gold Knuckle");
			meta.setItemName("Gold Knuckle");
			meta.setLore(Arrays.asList("SwapHands To Equip"));
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "gold_knuckle"), is);
		rc.shape("xax", " x ");
		rc.setIngredient('x', Material.GOLD_INGOT);
		rc.setIngredient('a', Material.STICK);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void IronKnuckle(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.GLOBE_BANNER_PATTERN, 1);
		ItemMeta meta = is.getItemMeta();
		meta.removeAttributeModifier(Attribute.ATTACK_DAMAGE);
		meta.removeAttributeModifier(Attribute.ATTACK_SPEED);
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
		meta.addAttributeModifier(Attribute.ATTACK_SPEED,
				new AttributeModifier(getKey(), 8,
						Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		meta.addAttributeModifier(Attribute.ATTACK_SPEED,
				new AttributeModifier(getKey(), 8,
						Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
		meta.setCustomModelData(1000 + 3);
		meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		if(lang.contains("kr")){
			meta.setLore(Arrays.asList("손교체시 장착"));
			meta.setDisplayName("철 너클");
			meta.setItemName("철 너클");
		}
		else {
			meta.setDisplayName("Iron Knuckle");
			meta.setItemName("Iron Knuckle");
			meta.setLore(Arrays.asList("SwapHands To Equip"));
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "iron_knuckle"), is);
		rc.shape("xax", " x ");
		rc.setIngredient('x', Material.IRON_BLOCK);
		rc.setIngredient('a', Material.STICK);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void DiamondKnuckle(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.GLOBE_BANNER_PATTERN, 1);
		ItemMeta meta = is.getItemMeta();
		meta.removeAttributeModifier(Attribute.ATTACK_DAMAGE);
		meta.removeAttributeModifier(Attribute.ATTACK_SPEED);
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 4, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
		meta.addAttributeModifier(Attribute.ATTACK_SPEED,
				new AttributeModifier(getKey(), 8,
						Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		meta.addAttributeModifier(Attribute.ATTACK_SPEED,
				new AttributeModifier(getKey(), 8,
						Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
		meta.setCustomModelData(1000 + 4);
		meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		if(lang.contains("kr")){
			meta.setLore(Arrays.asList("손교체시 장착"));
			meta.setDisplayName("다이아몬드 너클");
			meta.setItemName("다이아몬드 너클");
		}
		else {
			meta.setDisplayName("Diamond Knuckle");
			meta.setItemName("Diamond Knuckle");
			meta.setLore(Arrays.asList("SwapHands To Equip"));
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "diamond_knuckle"), is);
		rc.shape("xax", " x ");
		rc.setIngredient('x', Material.DIAMOND);
		rc.setIngredient('a', Material.STICK);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void NetheriteKnuckle(PrepareSmithingEvent d) {
		ItemStack r = new ItemStack(Material.GLOBE_BANNER_PATTERN, 1);
		ItemMeta rm = r.getItemMeta();
		rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 5, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 4, Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
		rm.addAttributeModifier(Attribute.ATTACK_SPEED,
				new AttributeModifier(getKey(), 8,
						Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		rm.addAttributeModifier(Attribute.ATTACK_SPEED,
				new AttributeModifier(getKey(), 8,
						Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
		rm.setCustomModelData(1000 + 60);
		rm.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		Player p = (Player) d.getView().getPlayer();
		
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			rm.setLore(Arrays.asList("손교체시 장착"));
			rm.setDisplayName("네더라이트 너클");
			rm.setItemName("네더라이트 너클");
		}
		else {
			rm.setDisplayName("Netherite Knuckle");
			rm.setItemName("Netherite Knuckle");
			rm.setLore(Arrays.asList("SwapHands To Equip"));
		}

		if (d.getInventory().getItem(0) != null && d.getInventory().getItem(0).getAmount() >= 1
				&& d.getInventory().getItem(0).getItemMeta().hasCustomModelData()
				&& d.getInventory().getItem(1) != null
				&& !d.getInventory().getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(nethercore, PersistentDataType.STRING, "none").equals("netherCore")
				&& !d.getInventory().getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(endercore, PersistentDataType.STRING, "none").equals("enderCore")
				&& !d.getInventory().getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(herocore, PersistentDataType.STRING, "none").equals("heroCore")) {
			if ((d.getInventory().getItem(0).getItemMeta().getCustomModelData() == 1000 + 4)
					&& d.getInventory().getItem(1).getType() == Material.NETHERITE_INGOT) {
				ItemMeta rm0 = d.getInventory().getItem(0).getItemMeta();
				if(!rm0.getDisplayName().equals(rm0.getItemName())) {
					rm.setDisplayName(rm0.getDisplayName());
				}
				r.setItemMeta(rm);
				r.addUnsafeEnchantments(d.getInventory().getItem(0).getEnchantments());
				d.setResult(r);
			}
		}

	}

	@EventHandler
	public void NetheriteKnuckle(SmithItemEvent d) {
		if (d.getClickedInventory() == null) {
			return;
		}
		if (d.getClickedInventory().getType() == InventoryType.SMITHING) {
			if (d.getClickedInventory().getItem(2) != null && d.getCurrentItem().getItemMeta().hasCustomModelData()
					&& d.getInventory().getItem(0).getItemMeta().hasCustomModelData()
					&& d.getInventory().getItem(1) != null
					&& !d.getInventory().getItem(2).getItemMeta().getPersistentDataContainer()
							.getOrDefault(nethercore, PersistentDataType.STRING, "none").equals("netherCore")
					&& !d.getInventory().getItem(2).getItemMeta().getPersistentDataContainer()
							.getOrDefault(endercore, PersistentDataType.STRING, "none").equals("enderCore")
					&& !d.getInventory().getItem(2).getItemMeta().getPersistentDataContainer()
							.getOrDefault(herocore, PersistentDataType.STRING, "none").equals("heroCore")) {
				if (d.getCurrentItem().getItemMeta().getCustomModelData() == 1000 + 60) {
					Player p = (Player) d.getWhoClicked();
					ItemStack r = new ItemStack(Material.GLOBE_BANNER_PATTERN, 1);
					ItemMeta rm = r.getItemMeta();
					rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 5, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
					rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 4, Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
					rm.setCustomModelData(1000 + 60);
					rm.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setLore(Arrays.asList("손교체시 장착"));
						rm.setDisplayName("네더라이트 너클");
						rm.setItemName("네더라이트 너클");
					}
					else {
						rm.setDisplayName("Netherite Knuckle");
						rm.setItemName("Netherite Knuckle");
						rm.setLore(Arrays.asList("SwapHands To Equip"));
					}

					ItemMeta rm0 = d.getInventory().getItem(0).getItemMeta();
					if(!rm0.getDisplayName().equals(rm0.getItemName())) {
						rm.setDisplayName(rm0.getDisplayName());
					}
					r.setItemMeta(rm);
					if (p.getInventory().firstEmpty() != -1 && d.getClickedInventory().getItem(2).getItemMeta()
							.getCustomModelData() == d.getCurrentItem().getItemMeta().getCustomModelData()) {
						r.addUnsafeEnchantments(d.getInventory().getItem(0).getEnchantments());
						p.getInventory().addItem(r);
						d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
						d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
						d.getClickedInventory().getItem(2).setAmount(0);
					}
				}
			}
		}

	}

	


}