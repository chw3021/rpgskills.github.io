package io.github.chw3021.commons;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.chw3021.classes.ClassData;
import io.github.chw3021.items.Backpack;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;


public class CombatMode implements Serializable{

	/**
	 * 
	 */
	private transient static final long serialVersionUID = 1745238346955426477L;

	protected Material CAREFUL = Material.EVOKER_SPAWN_EGG;
	
	private static final CombatMode instance = new CombatMode();
	private HashMap<String, Long> hm = new HashMap<String, Long>();
	
	private Material[] mas = { Material.AXOLOTL_SPAWN_EGG, Material.BEE_SPAWN_EGG,
			Material.CAT_SPAWN_EGG, Material.CHICKEN_SPAWN_EGG, Material.CREEPER_SPAWN_EGG,
			Material.FROG_SPAWN_EGG, Material.PARROT_SPAWN_EGG, Material.GOAT_SPAWN_EGG};

	public static CombatMode getInstance() {
		return instance;
	}

	final public boolean weaponcheck(Player p, ItemStack mi) {
		final int classnum = ClassData.pc.getOrDefault(p.getUniqueId(), -1);

		switch (classnum) {
		case 0: case 1: case 23:
			if (mi.getType().name().contains("SWORD")) {
				return true;
			}
			break;
		case 2:
			if (mi.getType().name().contains("_AXE")
					&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK")) {
				return true;
			}
			break;
		case 3:
			if ((mi.getType().name().contains("_AXE") || mi.getType() == Material.MACE)
					&& !p.getInventory().getItemInMainHand().getType().name().contains("PICK")) {
				return true;
			}
			break;
		case 4: case 61:
			if (mi.getType() == Material.CROSSBOW) {
				return true;
			}
			break;
		case 5: case 6:
			if (mi.getType() == Material.BOW) {
				return true;
			}
			break;
		case 7: case 8: case 9: case 10:
			if (mi.getType().name().contains("BANNER_PATTERN") && mi.hasItemMeta()
					&& mi.getItemMeta().hasCustomModelData()) {
				return true;
			}
			break;
		case 11: case 12: case 26:
			if (mi.getType() == Material.BLAZE_ROD && mi.hasItemMeta() && mi.getItemMeta().hasCustomModelData()) {
				return true;
			}
			break;
		case 13: case 14:
			if (mi.getType().name().contains("HOE")) {
				return true;
			}
			break;
		case 15: case 16: case 17:
			if (mi.getType().name().contains("_PICKAXE")) {
				return true;
			}
			break;
		case 18: case 25:
			if (mi.getType().name().contains("SHOVEL")) {
				return true;
			}
			break;
		case 19: case 20:
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
	static private HashMap<UUID, ItemStack> dis = new HashMap<>();
	

	
	final private void quitCombat(Player p, Integer param) {
		if (mode.containsKey(p.getUniqueId())) {
			mode.remove(p.getUniqueId());
			p.getInventory().clear();
			p.getInventory().setContents(pin.get(p.getUniqueId()));
			if(param==1) {
				p.getInventory().addItem(dis.get(p.getUniqueId()));
			}
			
			pin.remove(p.getUniqueId());
			dis.remove(p.getUniqueId());
			for(Material m : mas) {
				p.setCooldown(m, 0);
			}
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {
				p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
						new ComponentBuilder(ChatColor.BLUE + "비전투 상태").create());
			} else {
				p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
						new ComponentBuilder(ChatColor.BLUE + "Normal Mode").create());
			}
			p.playSound(p.getLocation(), Sound.ENTITY_ITEM_FRAME_REMOVE_ITEM, 1.0f, 0.2f);

		}
	}

	public void useCancel(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();

		if(isCombat(p) && (p.getInventory().getHeldItemSlot()>=0&&p.getInventory().getHeldItemSlot()<=6) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			ev.setCancelled(true);
		}
	}
	public boolean isCombat(Player p) {
		return mode.containsKey(p.getUniqueId());
	}

	public void classinv(InventoryClickEvent d) {
		Player p = (Player) d.getWhoClicked();
		if (mode.containsKey(p.getUniqueId()) && !(d.getView().getTitle().equals(p.getName() + "'s Backpack") || d.getView().getTitle().equals(p.getName() + "의 배낭"))) {
			d.setCancelled(true);
		}
	}

	public void nepreventer(PlayerQuitEvent ev) {
		Player p = ev.getPlayer();
		quitCombat(p,1);
	}
	public void death(PlayerDeathEvent ev) {
		if(isCombat(ev.getEntity())) {
			ev.setKeepInventory(true);
		}
	}


	public void deleter(PluginDisableEvent ev) {
		Bukkit.getServer().getOnlinePlayers().forEach(p -> {
			if (!p.isValid()) {
				return;
			}
			quitCombat(p,1);
		});
	}
	

	public void item(EntityPickupItemEvent ev) {
		if(ev.getEntity() instanceof Player) {
			Player p = (Player) ev.getEntity();
			if(isCombat(p)) {
				ev.setCancelled(true);
				ev.getItem().teleport(p);
				ev.getItem().remove();
				p.playSound(p, Sound.ENTITY_ITEM_PICKUP, 1, 1);
				Backpack.add(p, ev.getItem().getItemStack());
			}
		}
	}


	public static void itemset(String display, ItemStack is, int data, int stack, List<String> Lore, int loc,
			Inventory inv) {
		ItemStack item = is;
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}

	public void modeChange(PlayerDropItemEvent ev) {

		Player p = (Player) ev.getPlayer();
		
		if (mode.containsKey(p.getUniqueId())) {
			ev.setCancelled(true);
		}
		
		if(p.isSneaking()) {
			p.setCooldown(CAREFUL, 1);
		}
		
		Item di = ev.getItemDrop();
		final ItemStack is = di.getItemStack().clone();
		final ItemStack os = p.getInventory().getItemInOffHand().clone();
		final ItemStack[] ar = p.getInventory().getArmorContents().clone();
		final ItemStack[] fc = p.getInventory().getContents().clone();

		if (weaponcheck(p, is) && p.isSneaking() && !p.hasCooldown(Material.WITHER_SKELETON_SPAWN_EGG)) {
			ev.setCancelled(true);
			if (mode.containsKey(p.getUniqueId())) {
				p.setCooldown(Material.WITHER_SKELETON_SPAWN_EGG, 1);
				quitCombat(p,0);
			} else {
				p.setCooldown(Material.WITHER_SKELETON_SPAWN_EGG, 1);
				
				pin.put(p.getUniqueId(), fc);
				dis.put(p.getUniqueId(), is);
				mode.put(p.getUniqueId(), true);
				if (p.getLocale().equalsIgnoreCase("ko_kr")) {
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
							new ComponentBuilder(ChatColor.RED + "전투 상태").create());
				} else {
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
							new ComponentBuilder(ChatColor.RED + "Combat Mode").create());
				}
				p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, 1.0f, 0.2f);

				p.getInventory().clear();
				p.getInventory().setHeldItemSlot(8);
				p.getInventory().setItemInOffHand(os);
				p.getInventory().setArmorContents(ar);

				ItemStack skillcool = new ItemStack(Material.ALLAY_SPAWN_EGG);
				ItemMeta smet = skillcool.getItemMeta();
				smet.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE);

				if (p.getLocale().equalsIgnoreCase("ko_kr")) {

					for (int i = 0; i < 8; i++) {
						skillcool.setItemMeta(smet);
						skillcool.setType(mas[i]);
						itemset(ChatColor.BLUE + "기술 [" + i + "]", skillcool, 0, 1, Arrays.asList(""),
								i, p.getInventory());
						smet.setDisplayName(ChatColor.BLUE + "기술 [" + i + "]");
					}
					itemset(ChatColor.GOLD + "배낭", new ItemStack(Material.BARREL), 0, 1, Arrays.asList("클릭시 배낭을 엽니다","전투상태중 획득한 아이템은 배낭에 저장됩니다"),
							9, p.getInventory());
				}
				else {

					for (int i = 0; i < 8; i++) {
						skillcool.setItemMeta(smet);
						skillcool.setType(mas[i]);
						itemset(ChatColor.BLUE + "Skill [" + i + "]", skillcool, 0, 1, Arrays.asList(""),
								i, p.getInventory());
						smet.setDisplayName(ChatColor.BLUE + "Skill [" + i + "]");
					}
					itemset(ChatColor.GOLD + "Backpack", new ItemStack(Material.BARREL), 0, 1, Arrays.asList("Open Backpack If You Click","Items acquired during combat","Will be stored in the backpack"),
							9, p.getInventory());
				}

				
				if(is.getType().name().contains("BOW")) {
					int i = 0;
					for(ItemStack ars : pin.get(p.getUniqueId())) {
						if(ars==null){
							continue;
						}
						if(ars.getType().name().contains("ARROW")){
							p.getInventory().setItem(18+i++, ars);
						}
					}
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
				sm.setDisplayName(ChatColor.BLUE + ev.getKname());
			} else {
				sm.setDisplayName(ChatColor.BLUE +ev.getEname());
			}
			si.setItemMeta(sm);
			if(ev.getTick()<0) {
				p.setCooldown(si.getType(), 0);
			}
			else {
				p.setCooldown(si.getType(), ev.getTick());
			}
		} 
		else {
	        if(hm.containsKey(p.getName())) {
	            double timer = (hm.get(p.getName())/1000d + 5) - System.currentTimeMillis()/1000d;
	            if(!(timer < 0)) {
	            } else {
	                hm.remove(p.getName());
	    			if (p.getLocale().equalsIgnoreCase("ko_kr")) {
	    				p.spigot().sendMessage(ChatMessageType.CHAT,
	    						new ComponentBuilder(ChatColor.BLUE + "[웅크리기 + 아이템버리기]로 전투모드로 전환할수 있습니다.").create());
	    			} else {
	    				p.spigot().sendMessage(ChatMessageType.CHAT,
	    						new ComponentBuilder(ChatColor.BLUE + "You can use CombatMode by [Sneaking + Dropping Item]").create());
	    			}
	                hm.put(p.getName(), System.currentTimeMillis());
	            }
	        } else {
	            hm.put(p.getName(), System.currentTimeMillis());
	        }
		}
	}
}