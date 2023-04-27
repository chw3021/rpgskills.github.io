package io.github.chw3021.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.chw3021.classes.ClassData;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class CombatMode {

	private static final CombatMode instance = new CombatMode();
	
	private Material[] mas = { Material.AXOLOTL_SPAWN_EGG, Material.BEE_SPAWN_EGG,
			Material.CAT_SPAWN_EGG, Material.CHICKEN_SPAWN_EGG, Material.CREEPER_SPAWN_EGG,
			Material.FROG_SPAWN_EGG, Material.PARROT_SPAWN_EGG};

	public static CombatMode getInstance() {
		return instance;
	}

	final public boolean weaponcheck(Player p, ItemStack mi) {
		final int classnum = ClassData.pc.getOrDefault(p.getUniqueId(), -1);

		switch (classnum) {
		case 0,1,23:
			if (mi.getType().name().contains("SWORD")) {
				return true;
			}
			break;
		case 2, 3:
			if (mi.getType().name().contains("_AXE")
					&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK")) {
				return true;
			}
			break;
		case 4, 61:
			if (mi.getType() == Material.CROSSBOW) {
				return true;
			}
			break;
		case 5, 6:
			if (mi.getType() == Material.BOW) {
				return true;
			}
			break;
		case 7, 8, 9, 10:
			if (mi.getType().name().contains("BANNER_PATTERN") && mi.hasItemMeta()
					&& mi.getItemMeta().hasCustomModelData()) {
				return true;
			}
			break;
		case 11, 12, 26:
			if (mi.getType() == Material.BLAZE_ROD && mi.hasItemMeta() && mi.getItemMeta().hasCustomModelData()) {
				return true;
			}
			break;
		case 13, 14:
			if (mi.getType().name().contains("HOE")) {
				return true;
			}
			break;
		case 15, 16, 17:
			if (mi.getType().name().contains("_PICKAXE")) {
				return true;
			}
			break;
		case 18, 25:
			if (mi.getType().name().contains("SHOVEL")) {
				return true;
			}
			break;
		case 19, 20:
			if (mi.getType() == Material.TRIDENT) {
				return true;
			}
			break;
		case 21:
			if (mi.getType() == Material.PRISMARINE_SHARD) {
				return true;
			}
			break;
		case 22:
			if (mi.getType() == Material.FISHING_ROD) {
				return true;
			}
			break;
		case 24:
			if (mi.getType() == Material.SHEARS) {
				return true;
			}
			break;

		}

		return false;
	}

	static private HashMap<UUID, ItemStack[]> pin = new HashMap<>();
	static private HashMap<UUID, Boolean> mode = new HashMap<>();
	
	final private void quitCombat(Player p) {
		if (mode.containsKey(p.getUniqueId())) {
			mode.remove(p.getUniqueId());
			p.getInventory().clear();
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {
				p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
						new ComponentBuilder(ChatColor.BLUE + "비전투 상태").create());
			} else {
				p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
						new ComponentBuilder(ChatColor.BLUE + "Normal Mode").create());
			}
			p.playSound(p.getLocation(), Sound.ENTITY_ITEM_FRAME_REMOVE_ITEM, 1.0f, 0.2f);

			p.getInventory().setContents(pin.get(p.getUniqueId()));
			pin.remove(p.getUniqueId());
			for(Material m : mas) {
				p.setCooldown(m, 0);
			}
		}
	}

	public boolean isCombat(Player p) {
		return mode.containsKey(p.getUniqueId());
	}

	public void classinv(InventoryClickEvent e) {
		if (mode.containsKey(e.getWhoClicked().getUniqueId())) {
			e.setCancelled(false);
		}
	}

	public void nepreventer(PlayerQuitEvent ev) {
		Player p = ev.getPlayer();
		quitCombat(p);
	}

	public void deleter(PluginDisableEvent ev) {
		Bukkit.getServer().getOnlinePlayers().forEach(p -> {
			if (!p.isValid()) {
				return;
			}
			quitCombat(p);
		});
	}

	public void modeChange(PlayerDropItemEvent ev) {

		Player p = (Player) ev.getPlayer();
		Item di = ev.getItemDrop();
		final ItemStack is = di.getItemStack().clone();
		final ItemStack os = p.getInventory().getItemInOffHand().clone();
		final ItemStack[] ar = p.getInventory().getArmorContents().clone();
		final ItemStack[] fc = p.getInventory().getContents().clone();

		if (weaponcheck(p, is) && p.isSneaking()) {
			ev.setCancelled(true);
			if (mode.containsKey(p.getUniqueId())) {
				quitCombat(p);
			} else {
				mode.put(p.getUniqueId(), true);
				if (p.getLocale().equalsIgnoreCase("ko_kr")) {
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
							new ComponentBuilder(ChatColor.RED + "전투 상태").create());
				} else {
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
							new ComponentBuilder(ChatColor.RED + "Combat Mode").create());
				}
				p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, 1.0f, 0.2f);

				pin.put(p.getUniqueId(), fc);
				p.getInventory().clear();
				p.getInventory().setHeldItemSlot(8);
				p.getInventory().setItemInOffHand(os);
				p.getInventory().setArmorContents(ar);

				ItemStack skillcool = new ItemStack(Material.ALLAY_SPAWN_EGG);
				ItemMeta smet = skillcool.getItemMeta();
				smet.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE);


				for (int i = 0; i < 7; i++) {
					smet.setDisplayName(ChatColor.BLUE + "Skill [" + i + "]");
					skillcool.setItemMeta(smet);
					skillcool.setType(mas[i]);
					p.getInventory().setItem(i, skillcool);
				}
			}
		}
	}

	public void modeAlert(SkillUseEvent ev) {
		Player p = (Player) ev.getPlayer();
		if (isCombat(p)) {
			ItemStack si = p.getInventory().getItem(ev.getNum());
			ItemMeta sm = si.getItemMeta();
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {
				sm.setDisplayName(ev.getKname());
			} else {
				sm.setDisplayName(ev.getEname());
			}
			si.setItemMeta(sm);
			p.setCooldown(si.getType(), ev.getTick());
		} 
		else {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {
				p.spigot().sendMessage(ChatMessageType.CHAT,
						new ComponentBuilder(ChatColor.BLUE + "[웅크리기 + 아이템버리기]로 전투모드로 전환할수 있습니다.").create());
			} else {
				p.spigot().sendMessage(ChatMessageType.CHAT,
						new ComponentBuilder(ChatColor.BLUE + "You can use CombatMode by [Sneaking + Dropping Item]").create());
			}
		}
	}
}
