package io.github.chw3021.items.armors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameEvent;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.GenericGameEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;


public class ArmorSet implements Listener{

	private static HashMap<UUID, ItemStack> helmeth = new HashMap<UUID, ItemStack>();
	private static HashMap<UUID, ItemStack> leggingsh = new HashMap<UUID, ItemStack>();
	private static HashMap<UUID, ItemStack> chestplateh = new HashMap<UUID, ItemStack>();
	private static HashMap<UUID, ItemStack> bootsh = new HashMap<UUID, ItemStack>();
	


	public static Integer setnum(Player p) {
   	 if(p.getEquipment().getBoots() != null && p.getEquipment().getChestplate()!= null && p.getEquipment().getLeggings()!= null && p.getEquipment().getHelmet()!= null) {

		if(p.getEquipment().getBoots().getItemMeta().hasCustomModelData() && p.getEquipment().getChestplate().getItemMeta().hasCustomModelData() && p.getEquipment().getLeggings().getItemMeta().hasCustomModelData() && p.getEquipment().getHelmet().getItemMeta().hasCustomModelData()) {
			int boots = p.getEquipment().getBoots().getItemMeta().getCustomModelData()%1000;
       	 	int chest = p.getEquipment().getChestplate().getItemMeta().getCustomModelData()%1000;
       	 	int legs = p.getEquipment().getLeggings().getItemMeta().getCustomModelData()%1000;
       	 	int hel = p.getEquipment().getHelmet().getItemMeta().getCustomModelData()%1000;
       	 	if(boots == chest && legs == hel && boots == hel) {
       	 		if(boots == 5) {
       	 			return 5;
       	 		}
       	 		else if(boots == 6) {
       	 			return 6;
       	 		}
       	 		else if(boots == 7) {
       	 			return 7;
       	 		}
       	 		else if(boots == 8) {
       	 			return 8;
       	 		}
       	 		else if(boots == 9) {
       	 			return 9;
       	 		}
       	 		else if(boots == 10) {
       	 			return 10;
       	 		}
       			else {
       				return -1;
       			}
       	 	}
    		else {
    			return -1;
    		}
		}
		else {
			return -1;
		}
   	 }
		else {
			return -1;
		}
	}
	
	private static boolean setcheck(Player p) {
		if(helmeth.containsKey(p.getUniqueId()) && leggingsh.containsKey(p.getUniqueId()) && chestplateh.containsKey(p.getUniqueId()) && bootsh.containsKey(p.getUniqueId())) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private static void setmap(Player p, Integer i) {
		if(i == 1) {
			helmeth.put(p.getUniqueId(), p.getEquipment().getHelmet());
			bootsh.put(p.getUniqueId(), p.getEquipment().getBoots());
			chestplateh.put(p.getUniqueId(), p.getEquipment().getChestplate());
			leggingsh.put(p.getUniqueId(), p.getEquipment().getLeggings());
		}
		else {
			if(helmeth.containsKey(p.getUniqueId())) {
				helmeth.remove(p.getUniqueId());
			}
			if(leggingsh.containsKey(p.getUniqueId())) {
				leggingsh.remove(p.getUniqueId());
			}
			if(bootsh.containsKey(p.getUniqueId())) {
				bootsh.remove(p.getUniqueId());
			}
			if(chestplateh.containsKey(p.getUniqueId())) {
				chestplateh.remove(p.getUniqueId());
			}
		}
	}
	private static void WholeRemover(Player p) {



		ItemStack b = bootsh.get(p.getUniqueId());
    	ItemStack c = chestplateh.get(p.getUniqueId());
    	ItemStack lg = leggingsh.get(p.getUniqueId());
    	ItemStack h = helmeth.get(p.getUniqueId());
			p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(0);
			p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(0);
			p.getAttribute(Attribute.GENERIC_LUCK).setBaseValue(0);
			p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2);
			p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4);
			p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1);
			if(b != null) {
        	 ItemMeta bm = b.getItemMeta();
				 if (bm != null && bm.hasLore()) {
				List<String> lore = bm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				bm.setLore(lore);
			 }
    		 b.setItemMeta(bm);
			}
			if(c != null) {

				ItemMeta cm = c.getItemMeta();
				if (cm != null && cm.hasLore()) {
				List<String> lore = cm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				cm.setLore(lore);
			}
    		c.setItemMeta(cm);
			}
			if(lg != null) {
        	 ItemMeta lm = lg.getItemMeta();
				if (lm != null && lm.hasLore()) {
				List<String> lore = lm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lm.setLore(lore);
			}
    		lg.setItemMeta(lm);
			}
			if(h != null) {
				ItemMeta hm = h.getItemMeta();
				if (hm != null && hm.hasLore()) {
				List<String> lore = hm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				hm.setLore(lore);
			}
    		h.setItemMeta(hm);
			}
	 	setmap(p,0);
	}
	
	private static void OneRemover(Player p, ItemStack ci) {

      	 if(helmeth.containsValue(ci)) {
				ItemMeta hm = ci.getItemMeta();
				if (hm != null && hm.hasLore()) {
					List<String> lore = hm.getLore();
					lore.removeIf(l -> l.contains("<Set Effect>:"));
					hm.setLore(lore);
				}
				ci.setItemMeta(hm);
				helmeth.remove(p.getUniqueId());
				p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(0);
				p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(0);
				p.getAttribute(Attribute.GENERIC_LUCK).setBaseValue(0);
				p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2);
				p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4);
				p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1);
	       	 }
	       	 if(leggingsh.containsValue(ci)) {
					ItemMeta hm = ci.getItemMeta();
					if (hm != null && hm.hasLore()) {
						List<String> lore = hm.getLore();
						lore.removeIf(l -> l.contains("<Set Effect>:"));
						hm.setLore(lore);
					}
					ci.setItemMeta(hm);
	       		leggingsh.remove(p.getUniqueId());
				p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(0);
				p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(0);
				p.getAttribute(Attribute.GENERIC_LUCK).setBaseValue(0);
				p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2);
				p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4);
				p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1);
	       	 }
	       	 if(chestplateh.containsValue(ci)) {
					ItemMeta hm = ci.getItemMeta();
					if (hm != null && hm.hasLore()) {
						List<String> lore = hm.getLore();
						lore.removeIf(l -> l.contains("<Set Effect>:"));
						hm.setLore(lore);
					}
					ci.setItemMeta(hm);
	       		chestplateh.remove(p.getUniqueId());
				p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(0);
				p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(0);
				p.getAttribute(Attribute.GENERIC_LUCK).setBaseValue(0);
				p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2);
				p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4);
				p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1);
	       	 }
	       	 if(bootsh.containsValue(ci)) {
					ItemMeta hm = ci.getItemMeta();
					if (hm != null && hm.hasLore()) {
						List<String> lore = hm.getLore();
						lore.removeIf(l -> l.contains("<Set Effect>:"));
						hm.setLore(lore);
					}
					ci.setItemMeta(hm);
				bootsh.remove(p.getUniqueId());
				p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(0);
				p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(0);
				p.getAttribute(Attribute.GENERIC_LUCK).setBaseValue(0);
				p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2);
				p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4);
				p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1);
	       	 }
	 
	}

	private static void Setter(Player p, Integer boots, ItemStack b, ItemStack c, ItemStack lg, ItemStack h) {
		p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(0);
		p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(0);
		p.getAttribute(Attribute.GENERIC_LUCK).setBaseValue(0);
		p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2);
		p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4);
		p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1);
	   	 ItemMeta bm = b.getItemMeta();
	   	 ItemMeta cm = c.getItemMeta();
	   	 ItemMeta lm = lg.getItemMeta();
	   	 ItemMeta hm = h.getItemMeta();
		if(boots == 5) {
			if (bm.hasLore()) {
				List<String> lore = bm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.GREEN + "<Set Effect>: Maximize Knockback Resistance");
				lore.add(ChatColor.GREEN + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.GREEN + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.GREEN + "<Set Effect>: +12 Attack_Damage");
				lore.add(ChatColor.GREEN + "<Set Effect>: Increase Party Damage 10%");
				lore.add(ChatColor.GREEN + "<Set Effect>: +45 Luck");
				bm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.GREEN + "<Set Effect>: Maximize Knockback Resistance");
				lore.add(ChatColor.GREEN + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.GREEN + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.GREEN + "<Set Effect>: +12 Attack_Damage");
				lore.add(ChatColor.GREEN + "<Set Effect>: Increase Party Damage 10%");
				lore.add(ChatColor.GREEN + "<Set Effect>: +45 Luck");
				bm.setLore(lore);
			}
				if (cm.hasLore()) {
				List<String> lore = cm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.GREEN + "<Set Effect>: Maximize Knockback Resistance");
				lore.add(ChatColor.GREEN + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.GREEN + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.GREEN + "<Set Effect>: +12 Attack_Damage");
				lore.add(ChatColor.GREEN + "<Set Effect>: Increase Party Damage 10%");
				lore.add(ChatColor.GREEN + "<Set Effect>: +45 Luck");
				cm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.GREEN + "<Set Effect>: Maximize Knockback Resistance");
				lore.add(ChatColor.GREEN + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.GREEN + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.GREEN + "<Set Effect>: +12 Attack_Damage");
				lore.add(ChatColor.GREEN + "<Set Effect>: Increase Party Damage 10%");
				lore.add(ChatColor.GREEN + "<Set Effect>: +45 Luck");
				cm.setLore(lore);
			}
				if (lm.hasLore()) {
				List<String> lore = lm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.GREEN + "<Set Effect>: Maximize Knockback Resistance");
				lore.add(ChatColor.GREEN + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.GREEN + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.GREEN + "<Set Effect>: +12 Attack_Damage");
				lore.add(ChatColor.GREEN + "<Set Effect>: Increase Party Damage 10%");
				lore.add(ChatColor.GREEN + "<Set Effect>: +45 Luck");
				lm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.GREEN + "<Set Effect>: Maximize Knockback Resistance");
				lore.add(ChatColor.GREEN + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.GREEN + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.GREEN + "<Set Effect>: +12 Attack_Damage");
				lore.add(ChatColor.GREEN + "<Set Effect>: Increase Party Damage 10%");
				lore.add(ChatColor.GREEN + "<Set Effect>: +45 Luck");
				lm.setLore(lore);
			}
				if (hm.hasLore()) {
				List<String> lore = hm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.GREEN + "<Set Effect>: Maximize Knockback Resistance");
				lore.add(ChatColor.GREEN + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.GREEN + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.GREEN + "<Set Effect>: +12 Attack_Damage");
				lore.add(ChatColor.GREEN + "<Set Effect>: Increase Party Damage 10%");
				lore.add(ChatColor.GREEN + "<Set Effect>: +45 Luck");
				hm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.GREEN + "<Set Effect>: Maximize Knockback Resistance");
				lore.add(ChatColor.GREEN + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.GREEN + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.GREEN + "<Set Effect>: +12 Attack_Damage");
				lore.add(ChatColor.GREEN + "<Set Effect>: Stun Hit Enemy for 1s(Cooldown 5s)");
				lore.add(ChatColor.GREEN + "<Set Effect>: Increase Party Damage 10%");
				lore.add(ChatColor.GREEN + "<Set Effect>: +45 Luck");
				hm.setLore(lore);
			}
				p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
				p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(p.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue()+5);
				p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getBaseValue()+5);
				p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()+12);
				p.getAttribute(Attribute.GENERIC_LUCK).setBaseValue(p.getAttribute(Attribute.GENERIC_LUCK).getBaseValue()+45);
		 }
		 if(boots == 6) {
				if (bm.hasLore()) {
				List<String> lore = bm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.AQUA + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.AQUA + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.AQUA + "<Set Effect>: +20 Attack_Damage");
				lore.add(ChatColor.AQUA + "<Set Effect>: +35% Movement Speed");
				lore.add(ChatColor.AQUA + "<Set Effect>: Immune to Slow & Frost");
				lore.add(ChatColor.AQUA + "Set Effect[Frostman]: Remove Frostbite Cooldown");
				lore.add(ChatColor.AQUA + "<Set Effect>: Stun Hit Enemy for 1s(Cooldown 5s)");
				lore.add(ChatColor.AQUA + "<Set Effect>: Add Enemy's FreezeTick 2s");
				lore.add(ChatColor.AQUA + "<Set Effect>: +20 Luck");
				bm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.AQUA + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.AQUA + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.AQUA + "<Set Effect>: +20 Attack_Damage");
				lore.add(ChatColor.AQUA + "<Set Effect>: +35% Movement Speed");
				lore.add(ChatColor.AQUA + "<Set Effect>: Immune to Slow & Frost");
				lore.add(ChatColor.AQUA + "Set Effect[Frostman]: Remove Frostbite Cooldown");
				lore.add(ChatColor.AQUA + "<Set Effect>: Stun Hit Enemy for 1s(Cooldown 5s)");
				lore.add(ChatColor.AQUA + "<Set Effect>: Add Enemy's FreezeTick 2s");
				lore.add(ChatColor.AQUA + "<Set Effect>: +20 Luck");
				bm.setLore(lore);
			}
				if (cm.hasLore()) {
				List<String> lore = cm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.AQUA + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.AQUA + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.AQUA + "<Set Effect>: +20 Attack_Damage");
				lore.add(ChatColor.AQUA + "<Set Effect>: +35% Movement Speed");
				lore.add(ChatColor.AQUA + "<Set Effect>: Immune to Slow & Frost");
				lore.add(ChatColor.AQUA + "Set Effect[Frostman]: Remove Frostbite Cooldown");
				lore.add(ChatColor.AQUA + "<Set Effect>: Stun Hit Enemy for 1s(Cooldown 5s)");
				lore.add(ChatColor.AQUA + "<Set Effect>: Add Enemy's FreezeTick 2s");
				lore.add(ChatColor.AQUA + "<Set Effect>: +20 Luck");
				cm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.AQUA + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.AQUA + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.AQUA + "<Set Effect>: +20 Attack_Damage");
				lore.add(ChatColor.AQUA + "<Set Effect>: +35% Movement Speed");
				lore.add(ChatColor.AQUA + "<Set Effect>: Immune to Slow & Frost");
				lore.add(ChatColor.AQUA + "Set Effect[Frostman]: Remove Frostbite Cooldown");
				lore.add(ChatColor.AQUA + "<Set Effect>: Stun Hit Enemy for 1s(Cooldown 5s)");
				lore.add(ChatColor.AQUA + "<Set Effect>: Add Enemy's FreezeTick 2s");
				lore.add(ChatColor.AQUA + "<Set Effect>: +20 Luck");
				cm.setLore(lore);
			}
				if (lm.hasLore()) {
				List<String> lore = lm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.AQUA + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.AQUA + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.AQUA + "<Set Effect>: +20 Attack_Damage");
				lore.add(ChatColor.AQUA + "<Set Effect>: +35% Movement Speed");
				lore.add(ChatColor.AQUA + "<Set Effect>: Immune to Slow & Frost");
				lore.add(ChatColor.AQUA + "Set Effect[Frostman]: Remove Frostbite Cooldown");
				lore.add(ChatColor.AQUA + "<Set Effect>: Stun Hit Enemy for 1s(Cooldown 5s)");
				lore.add(ChatColor.AQUA + "<Set Effect>: Add Enemy's FreezeTick 2s");
				lore.add(ChatColor.AQUA + "<Set Effect>: +20 Luck");
				lm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.AQUA + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.AQUA + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.AQUA + "<Set Effect>: +20 Attack_Damage");
				lore.add(ChatColor.AQUA + "<Set Effect>: +35% Movement Speed");
				lore.add(ChatColor.AQUA + "<Set Effect>: Immune to Slow & Frost");
				lore.add(ChatColor.AQUA + "Set Effect[Frostman]: Remove Frostbite Cooldown");
				lore.add(ChatColor.AQUA + "<Set Effect>: Stun Hit Enemy for 1s(Cooldown 5s)");
				lore.add(ChatColor.AQUA + "<Set Effect>: Add Enemy's FreezeTick 2s");
				lore.add(ChatColor.AQUA + "<Set Effect>: +20 Luck");
				lm.setLore(lore);
			}
				if (hm.hasLore()) {
				List<String> lore = hm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.AQUA + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.AQUA + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.AQUA + "<Set Effect>: +20 Attack_Damage");
				lore.add(ChatColor.AQUA + "<Set Effect>: +35% Movement Speed");
				lore.add(ChatColor.AQUA + "<Set Effect>: Immune to Slow & Frost");
				lore.add(ChatColor.AQUA + "Set Effect[Frostman]: Remove Frostbite Cooldown");
				lore.add(ChatColor.AQUA + "<Set Effect>: Stun Hit Enemy for 1s(Cooldown 5s)");
				lore.add(ChatColor.AQUA + "<Set Effect>: Add Enemy's FreezeTick 2s");
				lore.add(ChatColor.AQUA + "<Set Effect>: +20 Luck");
				hm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.AQUA + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.AQUA + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.AQUA + "<Set Effect>: +20 Attack_Damage");
				lore.add(ChatColor.AQUA + "<Set Effect>: +35% Movement Speed");
				lore.add(ChatColor.AQUA + "<Set Effect>: Immune to Slow & Frost");
				lore.add(ChatColor.AQUA + "Set Effect[Frostman]: Remove Frostbite Cooldown");
				lore.add(ChatColor.AQUA + "<Set Effect>: Stun Hit Enemy for 1s(Cooldown 5s)");
				lore.add(ChatColor.AQUA + "<Set Effect>: Add Enemy's FreezeTick 2s");
				lore.add(ChatColor.AQUA + "<Set Effect>: +20 Luck");
				hm.setLore(lore);
			}
				p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(p.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue()+5);
				p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getBaseValue()+5);
				p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue()*1.35);
				p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()+20);
				p.getAttribute(Attribute.GENERIC_LUCK).setBaseValue(p.getAttribute(Attribute.GENERIC_LUCK).getBaseValue()+20);
		 }
		 if(boots == 7) {
				if (bm.hasLore()) {
				List<String> lore = bm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.BLUE + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.BLUE + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.BLUE + "<Set Effect>: +14 Attack_Damage");
				lore.add(ChatColor.BLUE + "<Set Effect>: +15% Movement Speed");
				lore.add(ChatColor.BLUE + "<Set Effect>: Give Positive Ocean Effect When Swim");
				lore.add(ChatColor.BLUE + "Set Effect[Marine]: Increase Skill Damage 35%");
				lore.add(ChatColor.BLUE + "<Set Effect>: +20 Luck");
				bm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.BLUE + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.BLUE + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.BLUE + "<Set Effect>: +14 Attack_Damage");
				lore.add(ChatColor.BLUE + "<Set Effect>: +15% Movement Speed");
				lore.add(ChatColor.BLUE + "<Set Effect>: Give Positive Ocean Effect When Swim");
				lore.add(ChatColor.BLUE + "Set Effect[Marine]: Increase Skill Damage 35%");
				lore.add(ChatColor.BLUE + "<Set Effect>: +20 Luck");
				bm.setLore(lore);
			}
				if (cm.hasLore()) {
				List<String> lore = cm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.BLUE + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.BLUE + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.BLUE + "<Set Effect>: +14 Attack_Damage");
				lore.add(ChatColor.BLUE + "<Set Effect>: +15% Movement Speed");
				lore.add(ChatColor.BLUE + "<Set Effect>: Give Positive Ocean Effect When Swim");
				lore.add(ChatColor.BLUE + "Set Effect[Marine]: Increase Skill Damage 35%");
				lore.add(ChatColor.BLUE + "<Set Effect>: +20 Luck");
				cm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.BLUE + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.BLUE + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.BLUE + "<Set Effect>: +14 Attack_Damage");
				lore.add(ChatColor.BLUE + "<Set Effect>: +15% Movement Speed");
				lore.add(ChatColor.BLUE + "<Set Effect>: Give Positive Ocean Effect When Swim");
				lore.add(ChatColor.BLUE + "Set Effect[Marine]: Increase Skill Damage 35%");
				lore.add(ChatColor.BLUE + "<Set Effect>: +20 Luck");
				cm.setLore(lore);
			}
				if (lm.hasLore()) {
				List<String> lore = lm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.BLUE + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.BLUE + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.BLUE + "<Set Effect>: +14 Attack_Damage");
				lore.add(ChatColor.BLUE + "<Set Effect>: +15% Movement Speed");
				lore.add(ChatColor.BLUE + "<Set Effect>: Give Positive Ocean Effect When Swim");
				lore.add(ChatColor.BLUE + "Set Effect[Marine]: Increase Skill Damage 35%");
				lore.add(ChatColor.BLUE + "<Set Effect>: +20 Luck");
				lm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.BLUE + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.BLUE + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.BLUE + "<Set Effect>: +14 Attack_Damage");
				lore.add(ChatColor.BLUE + "<Set Effect>: +15% Movement Speed");
				lore.add(ChatColor.BLUE + "<Set Effect>: Give Positive Ocean Effect When Swim");
				lore.add(ChatColor.BLUE + "Set Effect[Marine]: Increase Skill Damage 35%");
				lore.add(ChatColor.BLUE + "<Set Effect>: +20 Luck");
				lm.setLore(lore);
			}
				if (hm.hasLore()) {
				List<String> lore = hm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.BLUE + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.BLUE + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.BLUE + "<Set Effect>: +14 Attack_Damage");
				lore.add(ChatColor.BLUE + "<Set Effect>: +15% Movement Speed");
				lore.add(ChatColor.BLUE + "<Set Effect>: Give Positive Ocean Effect When Swim");
				lore.add(ChatColor.BLUE + "Set Effect[Marine]: Increase Skill Damage 35%");
				lore.add(ChatColor.BLUE + "<Set Effect>: +20 Luck");
				hm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.BLUE + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.BLUE + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.BLUE + "<Set Effect>: +14 Attack_Damage");
				lore.add(ChatColor.BLUE + "<Set Effect>: +15% Movement Speed");
				lore.add(ChatColor.BLUE + "<Set Effect>: Give Positive Ocean Effect When Swim");
				lore.add(ChatColor.BLUE + "Set Effect[Marine]: Increase Skill Damage 35%");
				lore.add(ChatColor.BLUE + "<Set Effect>: +20 Luck");
				hm.setLore(lore);
			}
				p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(p.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue()+5);
				p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getBaseValue()+5);
				p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()+14);
				p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue()*1.15);
				p.getAttribute(Attribute.GENERIC_LUCK).setBaseValue(p.getAttribute(Attribute.GENERIC_LUCK).getBaseValue()+20);
		 }
		 if(boots == 8) {
				if (bm.hasLore()) {
				List<String> lore = bm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +6 Armor");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +6 Armor Toughness");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +16 Attack_Damage");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +35% Movement Speed");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Immune to Poison&Blind Effect");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Get Invisible Effect 1s when Attacked");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Set Invulnerable 1s when Attacked(Cooldown 5s)");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Reduce 50% Projectile Damage");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +20 Luck");
				bm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +6 Armor");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +6 Armor Toughness");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +16 Attack_Damage");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +35% Movement Speed");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Immune to Poison&Blind Effect");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Get Invisible Effect 1s when Attacked");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Set Invulnerable 1s when Attacked(Cooldown 5s)");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Reduce 50% Projectile Damage");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +20 Luck");
				bm.setLore(lore);
			}
				if (cm.hasLore()) {
				List<String> lore = cm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +6 Armor");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +6 Armor Toughness");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +16 Attack_Damage");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +35% Movement Speed");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Immune to Poison&Blind Effect");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Get Invisible Effect 1s when Attacked");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Set Invulnerable 1s when Attacked(Cooldown 5s)");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Reduce 50% Projectile Damage");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +20 Luck");
				cm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +6 Armor");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +6 Armor Toughness");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +16 Attack_Damage");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +35% Movement Speed");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Immune to Poison&Blind Effect");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Get Invisible Effect 1s when Attacked");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Set Invulnerable 1s when Attacked(Cooldown 5s)");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Reduce 50% Projectile Damage");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +20 Luck");
				cm.setLore(lore);
			}
				if (lm.hasLore()) {
				List<String> lore = lm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +6 Armor");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +6 Armor Toughness");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +16 Attack_Damage");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +35% Movement Speed");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Immune to Poison&Blind Effect");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Get Invisible Effect 1s when Attacked");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Set Invulnerable 1s when Attacked(Cooldown 5s)");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Reduce 50% Projectile Damage");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +20 Luck");
				lm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +6 Armor");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +6 Armor Toughness");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +16 Attack_Damage");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +35% Movement Speed");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Immune to Poison&Blind Effect");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Get Invisible Effect 1s when Attacked");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Set Invulnerable 1s when Attacked(Cooldown 5s)");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Reduce 50% Projectile Damage");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +20 Luck");
				lm.setLore(lore);
			}
				if (hm.hasLore()) {
				List<String> lore = hm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +6 Armor");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +6 Armor Toughness");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +16 Attack_Damage");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +35% Movement Speed");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Immune to Poison&Blind Effect");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Get Invisible Effect 1s when Attacked");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Set Invulnerable 1s when Attacked(Cooldown 5s)");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Reduce 50% Projectile Damage");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +20 Luck");
				hm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +6 Armor");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +6 Armor Toughness");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +16 Attack_Damage");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +35% Movement Speed");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Immune to Poison&Blind Effect");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Get Invisible Effect 1s when Attacked");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Set Invulnerable 1s when Attacked(Cooldown 5s)");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: Reduce 50% Projectile Damage");
				lore.add(ChatColor.DARK_GREEN + "<Set Effect>: +20 Luck");
				hm.setLore(lore);
			}
				p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(p.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue()+6);
				p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getBaseValue()+6);
				p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue()*1.35);
				p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()+16);
				p.getAttribute(Attribute.GENERIC_LUCK).setBaseValue(p.getAttribute(Attribute.GENERIC_LUCK).getBaseValue()+20);
		 }
		 if(boots == 9) {
				if (bm.hasLore()) {
				List<String> lore = bm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +40% Movement Speed");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +500% Attack Speed");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +14 Attack_Damage");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +90 Luck");
				bm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +40% Movement Speed");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +500% Attack Speed");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +14 Attack_Damage");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +90 Luck");
				bm.setLore(lore);
			}
				if (cm.hasLore()) {
				List<String> lore = cm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +40% Movement Speed");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +500% Attack Speed");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +14 Attack_Damage");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +90 Luck");
				cm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +40% Movement Speed");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +500% Attack Speed");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +14 Attack_Damage");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +90 Luck");
				cm.setLore(lore);
			}
				if (lm.hasLore()) {
				List<String> lore = lm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +40% Movement Speed");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +500% Attack Speed");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +14 Attack_Damage");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +90 Luck");
				lm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +40% Movement Speed");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +500% Attack Speed");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +14 Attack_Damage");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +90 Luck");
				lm.setLore(lore);
			}
				if (hm.hasLore()) {
				List<String> lore = hm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +40% Movement Speed");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +500% Attack Speed");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +14 Attack_Damage");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +90 Luck");
				hm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +40% Movement Speed");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +500% Attack Speed");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +14 Attack_Damage");
				lore.add(ChatColor.LIGHT_PURPLE + "<Set Effect>: +90 Luck");
				hm.setLore(lore);
			}
				p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(p.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue()+5);
				p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getBaseValue()+5);
				p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue()*1.40);
				p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getBaseValue()*5);
				p.getAttribute(Attribute.GENERIC_LUCK).setBaseValue(p.getAttribute(Attribute.GENERIC_LUCK).getBaseValue()+90);
				p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()+13);
		 }
		 if(boots == 10) {
				if (bm.hasLore()) {
				List<String> lore = bm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.RED + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.RED + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.RED + "<Set Effect>: +20% Movement Speed");
				lore.add(ChatColor.RED + "<Set Effect>: +32 Attack_Damage");
				lore.add(ChatColor.RED + "<Set Effect>: +15% Damage");
				lore.add(ChatColor.RED + "<Set Effect>: Immune to Fire");
				bm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.RED + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.RED + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.RED + "<Set Effect>: +20% Movement Speed");
				lore.add(ChatColor.RED + "<Set Effect>: +32 Attack_Damage");
				lore.add(ChatColor.RED + "<Set Effect>: +15% Damage");
				lore.add(ChatColor.RED + "<Set Effect>: Immune to Fire");
				bm.setLore(lore);
			}
				if (cm.hasLore()) {
				List<String> lore = cm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.RED + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.RED + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.RED + "<Set Effect>: +20% Movement Speed");
				lore.add(ChatColor.RED + "<Set Effect>: +32 Attack_Damage");
				lore.add(ChatColor.RED + "<Set Effect>: +15% Damage");
				lore.add(ChatColor.RED + "<Set Effect>: Immune to Fire");
				cm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.RED + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.RED + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.RED + "<Set Effect>: +20% Movement Speed");
				lore.add(ChatColor.RED + "<Set Effect>: +32 Attack_Damage");
				lore.add(ChatColor.RED + "<Set Effect>: +15% Damage");
				lore.add(ChatColor.RED + "<Set Effect>: Immune to Fire");
				cm.setLore(lore);
			}
				if (lm.hasLore()) {
				List<String> lore = lm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.RED + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.RED + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.RED + "<Set Effect>: +20% Movement Speed");
				lore.add(ChatColor.RED + "<Set Effect>: +32 Attack_Damage");
				lore.add(ChatColor.RED + "<Set Effect>: +15% Damage");
				lore.add(ChatColor.RED + "<Set Effect>: Immune to Fire");
				lm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.RED + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.RED + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.RED + "<Set Effect>: +20% Movement Speed");
				lore.add(ChatColor.RED + "<Set Effect>: +32 Attack_Damage");
				lore.add(ChatColor.RED + "<Set Effect>: +15% Damage");
				lore.add(ChatColor.RED + "<Set Effect>: Immune to Fire");
				lm.setLore(lore);
			}
				if (hm.hasLore()) {
				List<String> lore = hm.getLore();
				lore.removeIf(l -> l.contains("<Set Effect>:"));
				lore.add(ChatColor.RED + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.RED + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.RED + "<Set Effect>: +20% Movement Speed");
				lore.add(ChatColor.RED + "<Set Effect>: +32 Attack_Damage");
				lore.add(ChatColor.RED + "<Set Effect>: +15% Damage");
				lore.add(ChatColor.RED + "<Set Effect>: Immune to Fire");
				hm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.RED + "<Set Effect>: +5 Armor");
				lore.add(ChatColor.RED + "<Set Effect>: +5 Armor Toughness");
				lore.add(ChatColor.RED + "<Set Effect>: +20% Movement Speed");
				lore.add(ChatColor.RED + "<Set Effect>: +32 Attack_Damage");
				lore.add(ChatColor.RED + "<Set Effect>: +15% Damage");
				lore.add(ChatColor.RED + "<Set Effect>: Immune to Fire");
				hm.setLore(lore);
			}
				p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(p.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue()+5);
				p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getBaseValue()+5);
				p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()+32);
				p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue()*1.2);
		 }
		 b.setItemMeta(bm);
		 c.setItemMeta(cm);
		 h.setItemMeta(hm);
		 lg.setItemMeta(lm);
		 setmap(p,1);
	}
	
	@EventHandler
	public void Armorset(InventoryClickEvent ev) 
	{
	 	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             @Override
	             public void run() 
	             {
	            	 Player p = (Player) ev.getWhoClicked();
	            	 if(p.getEquipment().getBoots() != null && p.getEquipment().getChestplate()!= null && p.getEquipment().getLeggings()!= null && p.getEquipment().getHelmet()!= null) {

		            	 if(p.getEquipment().getBoots().getItemMeta().hasCustomModelData() && p.getEquipment().getChestplate().getItemMeta().hasCustomModelData() && p.getEquipment().getLeggings().getItemMeta().hasCustomModelData() && p.getEquipment().getHelmet().getItemMeta().hasCustomModelData()) {
			            	 int boots = p.getEquipment().getBoots().getItemMeta().getCustomModelData()%1000;
			            	 int chest = p.getEquipment().getChestplate().getItemMeta().getCustomModelData()%1000;
			            	 int legs = p.getEquipment().getLeggings().getItemMeta().getCustomModelData()%1000;
			            	 int hel = p.getEquipment().getHelmet().getItemMeta().getCustomModelData()%1000;
			            	 if(boots == chest && legs == hel && boots == hel && setcheck(p)) {
		            		 ItemStack b = p.getEquipment().getBoots();
			            	 ItemStack c = p.getEquipment().getChestplate();
			            	 ItemStack lg = p.getEquipment().getLeggings();
			            	 ItemStack h = p.getEquipment().getHelmet();
			            	 
			            	 Setter(p,boots,b,c,lg,h);
			            	 }
			            	 else {
					            	WholeRemover(p);
			            	 }
		            	 }
		            	 else {
				            	WholeRemover(p);
		            	 }
	            	 }
	            	 else {
			            	WholeRemover(p);
	            	 }
	             }
	 	   }, 1); 
	}

	@EventHandler
	public void Armorset(InventoryCloseEvent ev) 
	{
		if(ev.getInventory() != null) {
	            	 Player p = (Player) ev.getPlayer();
	            	 if(p.getEquipment().getBoots() != null && p.getEquipment().getChestplate()!= null && p.getEquipment().getLeggings()!= null && p.getEquipment().getHelmet()!= null) {
	
		            	 if(p.getEquipment().getBoots().getItemMeta().hasCustomModelData() && p.getEquipment().getChestplate().getItemMeta().hasCustomModelData() && p.getEquipment().getLeggings().getItemMeta().hasCustomModelData() && p.getEquipment().getHelmet().getItemMeta().hasCustomModelData()) {
			            	 int boots = p.getEquipment().getBoots().getItemMeta().getCustomModelData()%1000;
			            	 int chest = p.getEquipment().getChestplate().getItemMeta().getCustomModelData()%1000;
			            	 int legs = p.getEquipment().getLeggings().getItemMeta().getCustomModelData()%1000;
			            	 int hel = p.getEquipment().getHelmet().getItemMeta().getCustomModelData()%1000;
			            	 if(boots == chest && legs == hel && boots == hel && setcheck(p)) {
		            		 ItemStack b = p.getEquipment().getBoots();
			            	 ItemStack c = p.getEquipment().getChestplate();
			            	 ItemStack lg = p.getEquipment().getLeggings();
			            	 ItemStack h = p.getEquipment().getHelmet();
	
			            	 Setter(p,boots,b,c,lg,h);
			            	 }
		            	 }
	            	 }
		}
	}
	
	@EventHandler
	public void Armorset(GenericGameEvent ev) 
	{
		if(ev.getEntity() instanceof Player && ev.getEvent() == GameEvent.EQUIP) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             @Override
	             public void run() 
	             {
	            	 Player p = (Player) ev.getEntity();
	            	 if(p.getEquipment().getBoots() != null && p.getEquipment().getChestplate()!= null && p.getEquipment().getLeggings()!= null && p.getEquipment().getHelmet()!= null) {
	
		            	 if(p.getEquipment().getBoots().getItemMeta().hasCustomModelData() && p.getEquipment().getChestplate().getItemMeta().hasCustomModelData() && p.getEquipment().getLeggings().getItemMeta().hasCustomModelData() && p.getEquipment().getHelmet().getItemMeta().hasCustomModelData()) {
			            	 int boots = p.getEquipment().getBoots().getItemMeta().getCustomModelData()%1000;
			            	 int chest = p.getEquipment().getChestplate().getItemMeta().getCustomModelData()%1000;
			            	 int legs = p.getEquipment().getLeggings().getItemMeta().getCustomModelData()%1000;
			            	 int hel = p.getEquipment().getHelmet().getItemMeta().getCustomModelData()%1000;
			            	 if(boots == chest && legs == hel && boots == hel && setcheck(p)) {
		            		 ItemStack b = p.getEquipment().getBoots();
			            	 ItemStack c = p.getEquipment().getChestplate();
			            	 ItemStack lg = p.getEquipment().getLeggings();
			            	 ItemStack h = p.getEquipment().getHelmet();
	
			            	 Setter(p,boots,b,c,lg,h);
			            	 }
		            	 }
	            	 }
	             }
	 	   }, 1/10); 
		}
	}

	@EventHandler
	public void Armorset(PlayerArmorStandManipulateEvent ev) 
	{
		if(ev.getSlot() == EquipmentSlot.CHEST || ev.getSlot() == EquipmentSlot.HEAD ||ev.getSlot() == EquipmentSlot.FEET ||ev.getSlot() == EquipmentSlot.LEGS) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             @Override
	             public void run() 
	             {
	            	 Player p = (Player) ev.getPlayer();
	            	 if(p.getEquipment().getBoots() != null && p.getEquipment().getChestplate()!= null && p.getEquipment().getLeggings()!= null && p.getEquipment().getHelmet()!= null) {
	
		            	 if(p.getEquipment().getBoots().getItemMeta().hasCustomModelData() && p.getEquipment().getChestplate().getItemMeta().hasCustomModelData() && p.getEquipment().getLeggings().getItemMeta().hasCustomModelData() && p.getEquipment().getHelmet().getItemMeta().hasCustomModelData()) {
			            	 int boots = p.getEquipment().getBoots().getItemMeta().getCustomModelData()%1000;
			            	 int chest = p.getEquipment().getChestplate().getItemMeta().getCustomModelData()%1000;
			            	 int legs = p.getEquipment().getLeggings().getItemMeta().getCustomModelData()%1000;
			            	 int hel = p.getEquipment().getHelmet().getItemMeta().getCustomModelData()%1000;
			            	 if(boots == chest && legs == hel && boots == hel && setcheck(p)) {
		            		 ItemStack b = p.getEquipment().getBoots();
			            	 ItemStack c = p.getEquipment().getChestplate();
			            	 ItemStack lg = p.getEquipment().getLeggings();
			            	 ItemStack h = p.getEquipment().getHelmet();
	
			            	 Setter(p,boots,b,c,lg,h);
			            	 }
		            	 }
		            	 else {
				            	WholeRemover(p);
		            	 }
	            	 }
	            	 else {
			            	WholeRemover(p);
	            	 }
	             }
	 	   }, 1/10); 
		}
	}

	@EventHandler
	public void Armorset(PlayerJoinEvent ev) 
	{
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             @Override
	             public void run() 
	             {
	            	 Player p = (Player) ev.getPlayer();
	            	 if(p.getEquipment().getBoots() != null && p.getEquipment().getChestplate()!= null && p.getEquipment().getLeggings()!= null && p.getEquipment().getHelmet()!= null) {
	
		            	 if(p.getEquipment().getBoots().getItemMeta().hasCustomModelData() && p.getEquipment().getChestplate().getItemMeta().hasCustomModelData() && p.getEquipment().getLeggings().getItemMeta().hasCustomModelData() && p.getEquipment().getHelmet().getItemMeta().hasCustomModelData()) {
			            	 int boots = p.getEquipment().getBoots().getItemMeta().getCustomModelData()%1000;
			            	 int chest = p.getEquipment().getChestplate().getItemMeta().getCustomModelData()%1000;
			            	 int legs = p.getEquipment().getLeggings().getItemMeta().getCustomModelData()%1000;
			            	 int hel = p.getEquipment().getHelmet().getItemMeta().getCustomModelData()%1000;
			            	 if(boots == chest && legs == hel && boots == hel && setcheck(p)) {
		            		 ItemStack b = p.getEquipment().getBoots();
			            	 ItemStack c = p.getEquipment().getChestplate();
			            	 ItemStack lg = p.getEquipment().getLeggings();
			            	 ItemStack h = p.getEquipment().getHelmet();

			            	 	Setter(p,boots,b,c,lg,h);
			            	 }
		            	 }
	            	 }
	             }
	 	   }, 1); 
		}

	@EventHandler
	public void Setremove(InventoryClickEvent ev) 
	{
		if(ev.getClickedInventory() != null && ev.getCurrentItem() != null) {

	       	 Player p = (Player) ev.getWhoClicked();
	       	 ItemStack ci = ev.getCurrentItem();
        	 if(p.getEquipment().getBoots() != null && p.getEquipment().getChestplate()!= null && p.getEquipment().getLeggings()!= null && p.getEquipment().getHelmet()!= null) {

            	 if(p.getEquipment().getBoots().getItemMeta().hasCustomModelData() && p.getEquipment().getChestplate().getItemMeta().hasCustomModelData() && p.getEquipment().getLeggings().getItemMeta().hasCustomModelData() && p.getEquipment().getHelmet().getItemMeta().hasCustomModelData()) 
            		 {
            		 int boots = p.getEquipment().getBoots().getItemMeta().getCustomModelData()%1000;
        	       	 int chest = p.getEquipment().getChestplate().getItemMeta().getCustomModelData()%1000;
        	       	 int legs = p.getEquipment().getLeggings().getItemMeta().getCustomModelData()%1000;
        	       	 int hel = p.getEquipment().getHelmet().getItemMeta().getCustomModelData()%1000;
                	 if(boots == chest && legs == hel && boots == hel && !setcheck(p)) {
                		 
                		 OneRemover(p,ci);
            		 }
                	 
            	 }
        	 }
		}
	}

	@EventHandler
	public void Setremove(PlayerDeathEvent ev) 
	{
		if(!ev.getKeepInventory()) {

	       	 Player p = (Player) ev.getEntity();
	       	 List<ItemStack> di = ev.getDrops();
        	 if(p.getEquipment().getBoots() != null && p.getEquipment().getChestplate()!= null && p.getEquipment().getLeggings()!= null && p.getEquipment().getHelmet()!= null) {

            	 if(p.getEquipment().getBoots().getItemMeta().hasCustomModelData() && p.getEquipment().getChestplate().getItemMeta().hasCustomModelData() && p.getEquipment().getLeggings().getItemMeta().hasCustomModelData() && p.getEquipment().getHelmet().getItemMeta().hasCustomModelData()) 
            		 {
            		 int boots = p.getEquipment().getBoots().getItemMeta().getCustomModelData()%1000;
        	       	 int chest = p.getEquipment().getChestplate().getItemMeta().getCustomModelData()%1000;
        	       	 int legs = p.getEquipment().getLeggings().getItemMeta().getCustomModelData()%1000;
        	       	 int hel = p.getEquipment().getHelmet().getItemMeta().getCustomModelData()%1000;
                	 if(boots == chest && legs == hel && boots == hel && !setcheck(p)) {
                		 for(ItemStack ci : di) {
                    		 
                    		 OneRemover(p,ci);
                		 }
                	 }
            		 }
            	 }
	       	 
		}
	}
	

	@EventHandler
	public void Setremove(PlayerQuitEvent ev) 
	{
	       	 Player p = (Player) ev.getPlayer();
	       	 final ItemStack[] di = ev.getPlayer().getInventory().getArmorContents();
        	 if(p.getEquipment().getBoots() != null && p.getEquipment().getChestplate()!= null && p.getEquipment().getLeggings()!= null && p.getEquipment().getHelmet()!= null) {

            	 if(p.getEquipment().getBoots().getItemMeta().hasCustomModelData() && p.getEquipment().getChestplate().getItemMeta().hasCustomModelData() && p.getEquipment().getLeggings().getItemMeta().hasCustomModelData() && p.getEquipment().getHelmet().getItemMeta().hasCustomModelData()) 
            		 {
            		 int boots = p.getEquipment().getBoots().getItemMeta().getCustomModelData()%1000;
        	       	 int chest = p.getEquipment().getChestplate().getItemMeta().getCustomModelData()%1000;
        	       	 int legs = p.getEquipment().getLeggings().getItemMeta().getCustomModelData()%1000;
        	       	 int hel = p.getEquipment().getHelmet().getItemMeta().getCustomModelData()%1000;
                	 if(boots == chest && legs == hel && boots == hel && !setcheck(p)) {
                		 for(ItemStack ci : di) {
                    		 
                    		 OneRemover(p,ci);
                		 }
                	 }
            		 }
            	 }
	}
}
