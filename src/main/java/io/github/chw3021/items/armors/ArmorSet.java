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
	

	private final static String setFactor = "<✦>";

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
			p.getAttribute(Attribute.ARMOR).setBaseValue(0);
			p.getAttribute(Attribute.ARMOR_TOUGHNESS).setBaseValue(0);
			p.getAttribute(Attribute.LUCK).setBaseValue(0);
			p.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(2);
			p.getAttribute(Attribute.ATTACK_SPEED).setBaseValue(4);
			p.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.1);
			if(b != null) {
        	 ItemMeta bm = b.getItemMeta();
				 if (bm != null && bm.hasLore()) {
				List<String> lore = bm.getLore();
				lore.removeIf(l -> l.contains(setFactor));
				bm.setLore(lore);
			 }
    		 b.setItemMeta(bm);
			}
			if(c != null) {

				ItemMeta cm = c.getItemMeta();
				if (cm != null && cm.hasLore()) {
				List<String> lore = cm.getLore();
				lore.removeIf(l -> l.contains(setFactor));
				cm.setLore(lore);
			}
    		c.setItemMeta(cm);
			}
			if(lg != null) {
        	 ItemMeta lm = lg.getItemMeta();
				if (lm != null && lm.hasLore()) {
				List<String> lore = lm.getLore();
				lore.removeIf(l -> l.contains(setFactor));
				lm.setLore(lore);
			}
    		lg.setItemMeta(lm);
			}
			if(h != null) {
				ItemMeta hm = h.getItemMeta();
				if (hm != null && hm.hasLore()) {
				List<String> lore = hm.getLore();
				lore.removeIf(l -> l.contains(setFactor));
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
					lore.removeIf(l -> l.contains(setFactor));
					hm.setLore(lore);
				}
				ci.setItemMeta(hm);
				helmeth.remove(p.getUniqueId());
				p.getAttribute(Attribute.ARMOR).setBaseValue(0);
				p.getAttribute(Attribute.ARMOR_TOUGHNESS).setBaseValue(0);
				p.getAttribute(Attribute.LUCK).setBaseValue(0);
				p.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(2);
				p.getAttribute(Attribute.ATTACK_SPEED).setBaseValue(4);
				p.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.1);
	       	 }
	       	 if(leggingsh.containsValue(ci)) {
					ItemMeta hm = ci.getItemMeta();
					if (hm != null && hm.hasLore()) {
						List<String> lore = hm.getLore();
						lore.removeIf(l -> l.contains(setFactor));
						hm.setLore(lore);
					}
					ci.setItemMeta(hm);
	       		leggingsh.remove(p.getUniqueId());
				p.getAttribute(Attribute.ARMOR).setBaseValue(0);
				p.getAttribute(Attribute.ARMOR_TOUGHNESS).setBaseValue(0);
				p.getAttribute(Attribute.LUCK).setBaseValue(0);
				p.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(2);
				p.getAttribute(Attribute.ATTACK_SPEED).setBaseValue(4);
				p.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.1);
	       	 }
	       	 if(chestplateh.containsValue(ci)) {
					ItemMeta hm = ci.getItemMeta();
					if (hm != null && hm.hasLore()) {
						List<String> lore = hm.getLore();
						lore.removeIf(l -> l.contains(setFactor));
						hm.setLore(lore);
					}
					ci.setItemMeta(hm);
	       		chestplateh.remove(p.getUniqueId());
				p.getAttribute(Attribute.ARMOR).setBaseValue(0);
				p.getAttribute(Attribute.ARMOR_TOUGHNESS).setBaseValue(0);
				p.getAttribute(Attribute.LUCK).setBaseValue(0);
				p.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(2);
				p.getAttribute(Attribute.ATTACK_SPEED).setBaseValue(4);
				p.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.1);
	       	 }
	       	 if(bootsh.containsValue(ci)) {
					ItemMeta hm = ci.getItemMeta();
					if (hm != null && hm.hasLore()) {
						List<String> lore = hm.getLore();
						lore.removeIf(l -> l.contains(setFactor));
						hm.setLore(lore);
					}
					ci.setItemMeta(hm);
				bootsh.remove(p.getUniqueId());
				p.getAttribute(Attribute.ARMOR).setBaseValue(0);
				p.getAttribute(Attribute.ARMOR_TOUGHNESS).setBaseValue(0);
				p.getAttribute(Attribute.LUCK).setBaseValue(0);
				p.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(2);
				p.getAttribute(Attribute.ATTACK_SPEED).setBaseValue(4);
				p.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.1);
	       	 }
	 
	}

	final private static List<String> setLore(Player p, Integer i, Boolean activated){
		List<String> lore = new ArrayList<String>();
		ChatColor cc = ChatColor.DARK_GRAY;
		
		if(i == 5) {
			if(activated) {
				cc = ChatColor.GREEN;
			}
			if(p.getLocale().contains("ko_kr")) {
				lore.add(cc + setFactor+ ": 밀치기 저항 최대");
				lore.add(cc + setFactor+ ": +5 방어력");
				lore.add(cc + setFactor+ ": +5 방어강도");
				lore.add(cc + setFactor+ ": +12 공격력");
				lore.add(cc + setFactor+ ": 15% 파티원 공격력 증가");
				lore.add(cc + setFactor+ ": +45 행운");
			}
			else {
				lore.add(cc + setFactor+ ": Maximize Knockback Resistance");
				lore.add(cc + setFactor+ ": +5 Armor");
				lore.add(cc + setFactor+ ": +5 Armor Toughness");
				lore.add(cc + setFactor+ ": +12 Attack_Damage");
				lore.add(cc + setFactor+ ": Increases Party Damage 15%");
				lore.add(cc + setFactor+ ": +45 Luck");
			}
		}
		if(i == 6) {
			if(activated) {
				cc = ChatColor.AQUA;
			}
			if(p.getLocale().contains("ko_kr")) {
				lore.add(cc + setFactor + ": +5 방어력");
				lore.add(cc + setFactor + ": +5 방어강도");
				lore.add(cc + setFactor + ": +20 공격력");
				lore.add(cc + setFactor + ": +35% 이동속도");
				lore.add(cc + setFactor + ": 둔화, 동상에 면역");
				lore.add(cc + setFactor + ": 적을 1초동안 기절시킴(대기시간 5초)");
				lore.add(cc + setFactor + ": 적에게 동상시간을 2초 추가");
				lore.add(cc + setFactor + ": +20 행운");
				lore.add(cc + setFactor + ": [빙술사] 동상 대기시간 감소");
			}
			else {
				lore.add(cc + setFactor + ": +5 Armor");
				lore.add(cc + setFactor + ": +5 Armor Toughness");
				lore.add(cc + setFactor + ": +20 Attack_Damage");
				lore.add(cc + setFactor + ": +35% Movement Speed");
				lore.add(cc + setFactor + ": Immune to Slow & Frost");
				lore.add(cc + setFactor + ": Stuns Hit Enemy for 1s(Cooldown 5s)");
				lore.add(cc + setFactor + ": Add Enemy's FreezeTick 2s");
				lore.add(cc + setFactor + ": +20 Luck");
				lore.add(cc + setFactor + ": [FrostMan] Reduces Frostbite Cooldown");
			}
		}
		if(i == 7) {
			if(activated) {
				cc = ChatColor.BLUE;
			}
			if(p.getLocale().contains("ko_kr")) {
				lore.add(ChatColor.BLUE + setFactor + ": +5 방어력");
				lore.add(ChatColor.BLUE + setFactor + ": +5 방어강도");
				lore.add(ChatColor.BLUE + setFactor + ": +14 공격력");
				lore.add(ChatColor.BLUE + setFactor + ": +15% 이동속도");
				lore.add(ChatColor.BLUE + setFactor + ": 수영시 물속에서 이로운 효과 획득");
				lore.add(ChatColor.BLUE + setFactor + ": [해상] 스킬 공격력 35% 증가");
				lore.add(ChatColor.BLUE + setFactor + ": +20 행운");
			}
			else {
				lore.add(ChatColor.BLUE + setFactor + ": +5 Armor");
				lore.add(ChatColor.BLUE + setFactor + ": +5 Armor Toughness");
				lore.add(ChatColor.BLUE + setFactor + ": +14 Attack_Damage");
				lore.add(ChatColor.BLUE + setFactor + ": +15% Movement Speed");
				lore.add(ChatColor.BLUE + setFactor + ": Give Positive Ocean Effect When Swim");
				lore.add(ChatColor.BLUE + setFactor + ": [Marine] Increases Skill Damage 35%");
				lore.add(ChatColor.BLUE + setFactor + ": +20 Luck");
			}
		}
		if(i == 8) {
			if(activated) {
				cc = ChatColor.GRAY;
			}
			if(p.getLocale().contains("ko_kr")) {
				lore.add(cc + setFactor + ": +6 방어력");
				lore.add(cc + setFactor + ": +6 방어강도");
				lore.add(cc + setFactor + ": +16 공격력");
				lore.add(cc + setFactor + ": 어둠, 실명에 면역");
				lore.add(cc + setFactor + ": +20 Luck");
			}
			else {
				lore.add(cc + setFactor + ": +6 Armor");
				lore.add(cc + setFactor + ": +6 Armor Toughness");
				lore.add(cc + setFactor + ": +16 Attack_Damage");
				lore.add(cc + setFactor + ": Immune to Darkness&Blind Effect");
				lore.add(cc + setFactor + ": +20 Luck");
			}
		}
	
		return lore;
	}

	private static void Setter(Player p, Integer boots, ItemStack b, ItemStack c, ItemStack lg, ItemStack h) {
		p.getAttribute(Attribute.ARMOR).setBaseValue(0);
		p.getAttribute(Attribute.ARMOR_TOUGHNESS).setBaseValue(0);
		p.getAttribute(Attribute.LUCK).setBaseValue(0);
		p.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(2);
		p.getAttribute(Attribute.ATTACK_SPEED).setBaseValue(4);
		p.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.1);
		p.getAttribute(Attribute.KNOCKBACK_RESISTANCE).setBaseValue(0);
	   	ItemMeta bm = b.getItemMeta();
	   	ItemMeta cm = c.getItemMeta();
	   	ItemMeta lm = lg.getItemMeta();
	   	ItemMeta hm = h.getItemMeta();
	   	ItemMeta[] imarray = {bm,cm,lm,hm};
		for(ItemMeta im : imarray) {
			if(im.hasLore()) {
				List<String> lore = lm.getLore();
				lore.removeIf(l -> l.contains(setFactor));
			}
			im.setLore(setLore(p,boots,true));
		}
		if(boots == 5) {
			p.getAttribute(Attribute.KNOCKBACK_RESISTANCE).setBaseValue(1);
			p.getAttribute(Attribute.ARMOR).setBaseValue(p.getAttribute(Attribute.ARMOR).getBaseValue()+5);
			p.getAttribute(Attribute.ARMOR_TOUGHNESS).setBaseValue(p.getAttribute(Attribute.ARMOR_TOUGHNESS).getBaseValue()+5);
			p.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.ATTACK_DAMAGE).getBaseValue()+12);
			p.getAttribute(Attribute.LUCK).setBaseValue(p.getAttribute(Attribute.LUCK).getBaseValue()+45);
		 }
		 if(boots == 6) {
			p.getAttribute(Attribute.ARMOR).setBaseValue(p.getAttribute(Attribute.ARMOR).getBaseValue()+5);
			p.getAttribute(Attribute.ARMOR_TOUGHNESS).setBaseValue(p.getAttribute(Attribute.ARMOR_TOUGHNESS).getBaseValue()+5);
			p.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(p.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue()*1.35);
			p.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.ATTACK_DAMAGE).getBaseValue()+20);
			p.getAttribute(Attribute.LUCK).setBaseValue(p.getAttribute(Attribute.LUCK).getBaseValue()+20);
		 }
		 if(boots == 7) {
			p.getAttribute(Attribute.ARMOR).setBaseValue(p.getAttribute(Attribute.ARMOR).getBaseValue()+5);
			p.getAttribute(Attribute.ARMOR_TOUGHNESS).setBaseValue(p.getAttribute(Attribute.ARMOR_TOUGHNESS).getBaseValue()+5);
			p.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.ATTACK_DAMAGE).getBaseValue()+14);
			p.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(p.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue()*1.15);
			p.getAttribute(Attribute.LUCK).setBaseValue(p.getAttribute(Attribute.LUCK).getBaseValue()+20);
		 }
		 if(boots == 8) {
			p.getAttribute(Attribute.ARMOR).setBaseValue(p.getAttribute(Attribute.ARMOR).getBaseValue()+6);
			p.getAttribute(Attribute.ARMOR_TOUGHNESS).setBaseValue(p.getAttribute(Attribute.ARMOR_TOUGHNESS).getBaseValue()+6);
			p.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(p.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue()*1.35);
			p.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.ATTACK_DAMAGE).getBaseValue()+16);
			p.getAttribute(Attribute.LUCK).setBaseValue(p.getAttribute(Attribute.LUCK).getBaseValue()+20);
		 }
		 if(boots == 9) {
				if (bm.hasLore()) {
				List<String> lore = bm.getLore();
				lore.removeIf(l -> l.contains(setFactor));
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +5 Armor");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +5 Armor Toughness");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +40% Movement Speed");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +500% Attack Speed");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +14 Attack_Damage");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +90 Luck");
				bm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +5 Armor");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +5 Armor Toughness");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +40% Movement Speed");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +500% Attack Speed");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +14 Attack_Damage");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +90 Luck");
				bm.setLore(lore);
			}
				if (cm.hasLore()) {
				List<String> lore = cm.getLore();
				lore.removeIf(l -> l.contains(setFactor));
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +5 Armor");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +5 Armor Toughness");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +40% Movement Speed");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +500% Attack Speed");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +14 Attack_Damage");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +90 Luck");
				cm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +5 Armor");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +5 Armor Toughness");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +40% Movement Speed");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +500% Attack Speed");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +14 Attack_Damage");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +90 Luck");
				cm.setLore(lore);
			}
				if (lm.hasLore()) {
				List<String> lore = lm.getLore();
				lore.removeIf(l -> l.contains(setFactor));
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +5 Armor");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +5 Armor Toughness");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +40% Movement Speed");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +500% Attack Speed");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +14 Attack_Damage");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +90 Luck");
				lm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +5 Armor");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +5 Armor Toughness");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +40% Movement Speed");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +500% Attack Speed");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +14 Attack_Damage");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +90 Luck");
				lm.setLore(lore);
			}
				if (hm.hasLore()) {
				List<String> lore = hm.getLore();
				lore.removeIf(l -> l.contains(setFactor));
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +5 Armor");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +5 Armor Toughness");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +40% Movement Speed");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +500% Attack Speed");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +14 Attack_Damage");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +90 Luck");
				hm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +5 Armor");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +5 Armor Toughness");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +40% Movement Speed");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +500% Attack Speed");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +14 Attack_Damage");
				lore.add(ChatColor.LIGHT_PURPLE + setFactor + ": +90 Luck");
				hm.setLore(lore);
			}
				p.getAttribute(Attribute.ARMOR).setBaseValue(p.getAttribute(Attribute.ARMOR).getBaseValue()+5);
				p.getAttribute(Attribute.ARMOR_TOUGHNESS).setBaseValue(p.getAttribute(Attribute.ARMOR_TOUGHNESS).getBaseValue()+5);
				p.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(p.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue()*1.40);
				p.getAttribute(Attribute.ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.ATTACK_SPEED).getBaseValue()*5);
				p.getAttribute(Attribute.LUCK).setBaseValue(p.getAttribute(Attribute.LUCK).getBaseValue()+90);
				p.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.ATTACK_DAMAGE).getBaseValue()+13);
		 }
		 if(boots == 10) {
				if (bm.hasLore()) {
				List<String> lore = bm.getLore();
				lore.removeIf(l -> l.contains(setFactor));
				lore.add(ChatColor.RED + setFactor + ": +5 Armor");
				lore.add(ChatColor.RED + setFactor + ": +5 Armor Toughness");
				lore.add(ChatColor.RED + setFactor + ": +20% Movement Speed");
				lore.add(ChatColor.RED + setFactor + ": +32 Attack_Damage");
				lore.add(ChatColor.RED + setFactor + ": +15% Damage");
				lore.add(ChatColor.RED + setFactor + ": Immune to Fire");
				bm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.RED + setFactor + ": +5 Armor");
				lore.add(ChatColor.RED + setFactor + ": +5 Armor Toughness");
				lore.add(ChatColor.RED + setFactor + ": +20% Movement Speed");
				lore.add(ChatColor.RED + setFactor + ": +32 Attack_Damage");
				lore.add(ChatColor.RED + setFactor + ": +15% Damage");
				lore.add(ChatColor.RED + setFactor + ": Immune to Fire");
				bm.setLore(lore);
			}
				if (cm.hasLore()) {
				List<String> lore = cm.getLore();
				lore.removeIf(l -> l.contains(setFactor));
				lore.add(ChatColor.RED + setFactor + ": +5 Armor");
				lore.add(ChatColor.RED + setFactor + ": +5 Armor Toughness");
				lore.add(ChatColor.RED + setFactor + ": +20% Movement Speed");
				lore.add(ChatColor.RED + setFactor + ": +32 Attack_Damage");
				lore.add(ChatColor.RED + setFactor + ": +15% Damage");
				lore.add(ChatColor.RED + setFactor + ": Immune to Fire");
				cm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.RED + setFactor + ": +5 Armor");
				lore.add(ChatColor.RED + setFactor + ": +5 Armor Toughness");
				lore.add(ChatColor.RED + setFactor + ": +20% Movement Speed");
				lore.add(ChatColor.RED + setFactor + ": +32 Attack_Damage");
				lore.add(ChatColor.RED + setFactor + ": +15% Damage");
				lore.add(ChatColor.RED + setFactor + ": Immune to Fire");
				cm.setLore(lore);
			}
				if (lm.hasLore()) {
				List<String> lore = lm.getLore();
				lore.removeIf(l -> l.contains(setFactor));
				lore.add(ChatColor.RED + setFactor + ": +5 Armor");
				lore.add(ChatColor.RED + setFactor + ": +5 Armor Toughness");
				lore.add(ChatColor.RED + setFactor + ": +20% Movement Speed");
				lore.add(ChatColor.RED + setFactor + ": +32 Attack_Damage");
				lore.add(ChatColor.RED + setFactor + ": +15% Damage");
				lore.add(ChatColor.RED + setFactor + ": Immune to Fire");
				lm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.RED + setFactor + ": +5 Armor");
				lore.add(ChatColor.RED + setFactor + ": +5 Armor Toughness");
				lore.add(ChatColor.RED + setFactor + ": +20% Movement Speed");
				lore.add(ChatColor.RED + setFactor + ": +32 Attack_Damage");
				lore.add(ChatColor.RED + setFactor + ": +15% Damage");
				lore.add(ChatColor.RED + setFactor + ": Immune to Fire");
				lm.setLore(lore);
			}
				if (hm.hasLore()) {
				List<String> lore = hm.getLore();
				lore.removeIf(l -> l.contains(setFactor));
				lore.add(ChatColor.RED + setFactor + ": +5 Armor");
				lore.add(ChatColor.RED + setFactor + ": +5 Armor Toughness");
				lore.add(ChatColor.RED + setFactor + ": +20% Movement Speed");
				lore.add(ChatColor.RED + setFactor + ": +32 Attack_Damage");
				lore.add(ChatColor.RED + setFactor + ": +15% Damage");
				lore.add(ChatColor.RED + setFactor + ": Immune to Fire");
				hm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.RED + setFactor + ": +5 Armor");
				lore.add(ChatColor.RED + setFactor + ": +5 Armor Toughness");
				lore.add(ChatColor.RED + setFactor + ": +20% Movement Speed");
				lore.add(ChatColor.RED + setFactor + ": +32 Attack_Damage");
				lore.add(ChatColor.RED + setFactor + ": +15% Damage");
				lore.add(ChatColor.RED + setFactor + ": Immune to Fire");
				hm.setLore(lore);
			}
				p.getAttribute(Attribute.ARMOR).setBaseValue(p.getAttribute(Attribute.ARMOR).getBaseValue()+5);
				p.getAttribute(Attribute.ARMOR_TOUGHNESS).setBaseValue(p.getAttribute(Attribute.ARMOR_TOUGHNESS).getBaseValue()+5);
				p.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.ATTACK_DAMAGE).getBaseValue()+32);
				p.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(p.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue()*1.2);
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
