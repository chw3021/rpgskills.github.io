package io.github.chw3021.classes;

import java.util.List;
import java.util.UUID;
import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

public class Classgui implements Serializable {

	/**
	 * 
	 */
	private transient static final long serialVersionUID = -4535818641688824406L;

	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv) {
		ItemStack item = new ItemStack(ID);
		if (ID == Material.TIPPED_ARROW) {
			PotionMeta meta = (PotionMeta) item.getItemMeta();
			meta.setColor(Color.RED);
			item.setItemMeta(meta);
		}
		ItemMeta items = item.getItemMeta();
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		items.setDisplayName(display);
		items.setLore(Lore);
		items.setAttributeModifiers(null);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}

	final public void itemset(String display, ItemStack is, int data, int stack, List<String> Lore, int loc,
			Inventory inv) {
		ItemStack item = is;
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}

	final public void LimitBreak(Player p) {
		String path = new File("").getAbsolutePath();
		ClassData cdata = new ClassData(ClassData.loadData(path + "/plugins/RPGskills/ClassData.data"));
		HashMap<UUID, Integer> playerclass = cdata.playerclass;
		p.setDisplayName(p.getName());
		p.setPlayerListName(p.getName());
		switch (playerclass.get(p.getUniqueId())) {
		case 0: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {
				if (Proficiency.getpro(p) == 1) { // 대지
					p.addScoreboardTag(ChatColor.AQUA + "검객");
					p.setDisplayName(ChatColor.AQUA + "[[검객]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[검객]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) == 2) {
					p.addScoreboardTag(ChatColor.AQUA + "검극");
					p.setDisplayName(ChatColor.AQUA + "[[[검극]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[[검극]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.AQUA + "검사");
					p.setDisplayName(ChatColor.AQUA + "[검사]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[검사]" + p.getPlayerListName());
				}
				break;
			} else {
				if (Proficiency.getpro(p) == 1) {
					p.addScoreboardTag(ChatColor.AQUA + "SwordMaster");
					p.setDisplayName(ChatColor.AQUA + "[[SwordMaster]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[SwordMaster]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) == 2) {
					p.addScoreboardTag(ChatColor.AQUA + "GrandMaster");
					p.setDisplayName(ChatColor.AQUA + "[[[GrandMaster]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[[GrandMaster]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.AQUA + "SwordMan");
					p.setDisplayName(ChatColor.AQUA + "[SwordMan]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[SwordMan]" + p.getPlayerListName());
				}
			}
			break;
		}
		case 1: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) { // 대지, 불
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.DARK_RED + "학살자");
					p.setDisplayName(ChatColor.DARK_RED + "[[학살자]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_RED + "[[학살자]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.DARK_RED + "섬멸귀");
					p.setDisplayName(ChatColor.DARK_RED + "[[[섬멸귀]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_RED + "[[[섬멸귀]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.DARK_RED + "광전사");
					p.setDisplayName(ChatColor.DARK_RED + "[광전사]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_RED + "[광전사]" + p.getPlayerListName());
				}
			} else {
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.DARK_RED + "Rampager");
					p.setDisplayName(ChatColor.DARK_RED + "[[Rampager]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_RED + "[[Rampager]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.DARK_RED + "Slayer");
					p.setDisplayName(ChatColor.DARK_RED + "[[[Slayer]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_RED + "[[[Slayer]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.DARK_RED + "Berserker");
					p.setDisplayName(ChatColor.DARK_RED + "[Berserker]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_RED + "[Berserker]" + p.getPlayerListName());
				}
			}
			break;
		}
		case 2: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 바람
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.GREEN + "사냥귀");
					p.setDisplayName(ChatColor.GREEN + "[[사냥귀]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GREEN + "[[사냥귀]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.GREEN + "집행자");
					p.setDisplayName(ChatColor.GREEN + "[[[집행자]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GREEN + "[[[집행자]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.GREEN + "사냥꾼");
					p.setDisplayName(ChatColor.GREEN + "[사냥꾼]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GREEN + "[사냥꾼]" + p.getPlayerListName());
				}
			} else {
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.GREEN + "Jaeger");
					p.setDisplayName(ChatColor.GREEN + "[[Jaeger]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GREEN + "[[Jaeger]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.GREEN + "Executor");
					p.setDisplayName(ChatColor.GREEN + "[[[Executor]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GREEN + "[[[Executor]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.GREEN + "Hunter");
					p.setDisplayName(ChatColor.GREEN + "[Hunter]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GREEN + "[Hunter]" + p.getPlayerListName());
				}
			}
			break;
		}
		case 3: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 번개,불
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.WHITE + "수호자");
					p.setDisplayName(ChatColor.WHITE + "[[수호자]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.WHITE + "[[수호자]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.WHITE + "구원자");
					p.setDisplayName(ChatColor.WHITE + "[[[구원자]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.WHITE + "[[[구원자]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.WHITE + "성기사");
					p.setDisplayName(ChatColor.WHITE + "[성기사]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.WHITE + "[성기사]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.WHITE + "Tutelar");
				p.setDisplayName(ChatColor.WHITE + "[[Tutelar]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.WHITE + "[[Tutelar]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.WHITE + "Saviour");
				p.setDisplayName(ChatColor.WHITE + "[[[Saviour]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.WHITE + "[[[Saviour]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.WHITE + "Paladin");
				p.setDisplayName(ChatColor.WHITE + "[Paladin]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.WHITE + "[Paladin]" + p.getPlayerListName());
			}
			break;
		}
		case 4: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 바람
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.getByChar("336600") + "명사수");
					p.setDisplayName(ChatColor.getByChar("336600") + "[[명사수]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.getByChar("336600") + "[[명사수]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.getByChar("336600") + "독안");
					p.setDisplayName(ChatColor.getByChar("336600") + "[[[독안]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.getByChar("336600") + "[[[독안]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.getByChar("336600") + "저격수");
					p.setDisplayName(ChatColor.getByChar("336600") + "[저격수]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.getByChar("336600") + "[저격수]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.getByChar("336600") + "Ace");
				p.setDisplayName(ChatColor.getByChar("336600") + "[[Ace]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.getByChar("336600") + "[[Ace]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.getByChar("336600") + "EagleEyes");
				p.setDisplayName(ChatColor.getByChar("336600") + "[[[EagleEyes]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.getByChar("336600") + "[[[EagleEyes]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.getByChar("336600") + "Sniper");
				p.setDisplayName(ChatColor.getByChar("336600") + "[Sniper]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.getByChar("336600") + "[Sniper]" + p.getPlayerListName());
			}
			break;
		}
		case 5: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 물,불,바람,전기
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "융합술사");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[융합술사]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[융합술사]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "신비술사");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[[신비술사]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[[신비술사]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "원소술사");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[원소술사]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[원소술사]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "FusionMage");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[FusionMage]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[FusionMage]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Mystic");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[[Mystic]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[[Mystic]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Elementalist");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[Elementalist]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[Elementalist]" + p.getPlayerListName());
			}
			break;
		}
		case 6: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 대지
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.BLUE + "궁장");
					p.setDisplayName(ChatColor.BLUE + "[[궁장]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.BLUE + "[[궁장]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.BLUE + "명궁");
					p.setDisplayName(ChatColor.BLUE + "[[[명궁]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.BLUE + "[[[명궁]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.BLUE + "궁수");
					p.setDisplayName(ChatColor.BLUE + "[궁수]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.BLUE + "[궁수]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.BLUE + "Combatant");
				p.setDisplayName(ChatColor.BLUE + "[[Combatant]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.BLUE + "[[Combatant]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.BLUE + "Legendary");
				p.setDisplayName(ChatColor.BLUE + "[[[Legendary]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.BLUE + "[[[Legendary]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.BLUE + "Archer");
				p.setDisplayName(ChatColor.BLUE + "[Archer]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.BLUE + "[Archer]" + p.getPlayerListName());
			}
			break;
		}
		case 61: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 번개, 독
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "소생자");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[소생자]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[소생자]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "명의");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[[명의]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[[명의]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "의궁");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[의궁]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[의궁]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Restorer");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[Restorer]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[Restorer]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Rescuer");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[[Rescuer]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[[Rescuer]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "ArrowMedic");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[ArrowMedic]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[ArrowMedic]" + p.getPlayerListName());
			}
			break;
		}
		case 7: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 바람
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.GRAY + "철권");
					p.setDisplayName(ChatColor.GRAY + "[[철권]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GRAY + "[[철권]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.GRAY + "금강불괴");
					p.setDisplayName(ChatColor.GRAY + "[[[금강불괴]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GRAY + "[[[금강불괴]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.GRAY + "권사");
					p.setDisplayName(ChatColor.GRAY + "[권사]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GRAY + "[권사]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.GRAY + "IronFist");
				p.setDisplayName(ChatColor.GRAY + "[[IronFist]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.GRAY + "[[IronFist]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.GRAY + "Unbeatable");
				p.setDisplayName(ChatColor.GRAY + "[[[Unbeatable]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.GRAY + "[[[Unbeatable]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.GRAY + "Boxer");
				p.setDisplayName(ChatColor.GRAY + "[Boxer]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.GRAY + "[Boxer]" + p.getPlayerListName());
			}
			break;
		}
		case 8: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 대지
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.GOLD + "고수");
					p.setDisplayName(ChatColor.GOLD + "[[고수]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GOLD + "[[고수]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.GOLD + "패왕");
					p.setDisplayName(ChatColor.GOLD + "[[[패왕]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GOLD + "[[[패왕]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.GOLD + "유술가");
					p.setDisplayName(ChatColor.GOLD + "[유술가]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GOLD + "[유술가]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.GOLD + "Adept");
				p.setDisplayName(ChatColor.GOLD + "[[Adept]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.GOLD + "[[Adept]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.GOLD + "Champion");
				p.setDisplayName(ChatColor.GOLD + "[[[Champion]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.GOLD + "[[[Champion]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.GOLD + "Wrestler");
				p.setDisplayName(ChatColor.GOLD + "[Wrestler]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.GOLD + "[Wrestler]" + p.getPlayerListName());
			}
			break;
		}
		case 9: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 독, 대지
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.DARK_GRAY + "훈육사");
					p.setDisplayName(ChatColor.DARK_GRAY + "[[훈육사]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GRAY + "[[훈육사]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.DARK_GRAY + "교감사");
					p.setDisplayName(ChatColor.DARK_GRAY + "[[[교감사]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GRAY + "[[[교감사]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.DARK_GRAY + "조련사");
					p.setDisplayName(ChatColor.DARK_GRAY + "[조련사]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GRAY + "[조련사]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.DARK_GRAY + "Trainer");
				p.setDisplayName(ChatColor.DARK_GRAY + "[[Trainer]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.DARK_GRAY + "[[Trainer]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.DARK_GRAY + "Communicator");
				p.setDisplayName(ChatColor.DARK_GRAY + "[[[Communicator]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.DARK_GRAY + "[[[Communicator]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.DARK_GRAY + "Tamer");
				p.setDisplayName(ChatColor.DARK_GRAY + "[Tamer]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.DARK_GRAY + "[Tamer]" + p.getPlayerListName());
			}
			break;
		}
		case 10: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 어둠,서리, 불,번개
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.YELLOW + "선인");
					p.setDisplayName(ChatColor.YELLOW + "[[선인]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.YELLOW + "[[선인]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.YELLOW + "신선");
					p.setDisplayName(ChatColor.YELLOW + "[[[신선]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.YELLOW + "[[[신선]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.YELLOW + "도사");
					p.setDisplayName(ChatColor.YELLOW + "[도사]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.YELLOW + "[도사]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.YELLOW + "Xian");
				p.setDisplayName(ChatColor.YELLOW + "[[Xian]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.YELLOW + "[[Xian]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.YELLOW + "Numen");
				p.setDisplayName(ChatColor.YELLOW + "[[[Numen]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.YELLOW + "[[[Numen]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.YELLOW + "Taoist");
				p.setDisplayName(ChatColor.YELLOW + "[Taoist]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.YELLOW + "[Taoist]" + p.getPlayerListName());
			}
			break;
		}
		case 11: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 바람
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.DARK_PURPLE + "소멸령");
					p.setDisplayName(ChatColor.DARK_PURPLE + "[[소멸령]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_PURPLE + "[[소멸령]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.DARK_PURPLE + "환영귀");
					p.setDisplayName(ChatColor.DARK_PURPLE + "[[[환영귀]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_PURPLE + "[[[환영귀]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.DARK_PURPLE + "환술사");
					p.setDisplayName(ChatColor.DARK_PURPLE + "[환술사]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_PURPLE + "[환술사]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.DARK_PURPLE + "Vanisher");
				p.setDisplayName(ChatColor.DARK_PURPLE + "[[Vanisher]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.DARK_PURPLE + "[[Vanisher]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.DARK_PURPLE + "Phantom");
				p.setDisplayName(ChatColor.DARK_PURPLE + "[[[Phantom]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.DARK_PURPLE + "[[[Phantom]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.DARK_PURPLE + "Illusionist");
				p.setDisplayName(ChatColor.DARK_PURPLE + "[Illusionist]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.DARK_PURPLE + "[Illusionist]" + p.getPlayerListName());
			}
			break;
		}
		case 12: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 불
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.RED + "화신");
					p.setDisplayName(ChatColor.RED + "[[화신]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.RED + "[[화신]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.RED + "태양신");
					p.setDisplayName(ChatColor.RED + "[[[태양신]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.RED + "[[[태양신]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.RED + "화염술사");
					p.setDisplayName(ChatColor.RED + "[화염술사]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.RED + "[화염술사]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.RED + "Pyromancer");
				p.setDisplayName(ChatColor.RED + "[[Pyromancer]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.RED + "[[Pyromancer]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.RED + "SolarDeity");
				p.setDisplayName(ChatColor.RED + "[[[SolarDeity]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.RED + "[[[SolarDeity]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.RED + "FireMage");
				p.setDisplayName(ChatColor.RED + "[FireMage]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.RED + "[FireMage]" + p.getPlayerListName());
			}
			break;
		}
		case 13: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 어둠
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.GRAY + "복수자");
					p.setDisplayName(ChatColor.GRAY + "[[복수자]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GRAY + "[[복수자]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.GRAY + "선지자");
					p.setDisplayName(ChatColor.GRAY + "[[[선지자]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GRAY + "[[[선지자]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.GRAY + "위더리스트");
					p.setDisplayName(ChatColor.GRAY + "[위더리스트]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GRAY + "[위더리스트]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.GRAY + "Revenger");
				p.setDisplayName(ChatColor.GRAY + "[[Revenger]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.GRAY + "[[Revenger]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.GRAY + "Prophet");
				p.setDisplayName(ChatColor.GRAY + "[[[Prophet]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.GRAY + "[[[Prophet]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.GRAY + "Witherist");
				p.setDisplayName(ChatColor.GRAY + "[Witherist]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.GRAY + "[Witherist]" + p.getPlayerListName());
			}
			break;
		}
		case 14: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 대지
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.DARK_GREEN + "부활자");
					p.setDisplayName(ChatColor.DARK_GREEN + "[[부활자]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GREEN + "[[부활자]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.DARK_GREEN + "불멸자");
					p.setDisplayName(ChatColor.DARK_GREEN + "[[[불멸자]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GREEN + "[[[불멸자]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.DARK_GREEN + "부두술사");
					p.setDisplayName(ChatColor.DARK_GREEN + "[부두술사]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GREEN + "[부두술사]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.DARK_GREEN + "Reviver");
				p.setDisplayName(ChatColor.DARK_GREEN + "[[Reviver]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.DARK_GREEN + "[[Reviver]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.DARK_GREEN + "Immortal");
				p.setDisplayName(ChatColor.DARK_GREEN + "[[[Immortal]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.DARK_GREEN + "[[[Immortal]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.DARK_GREEN + "WitchDoctor");
				p.setDisplayName(ChatColor.DARK_GREEN + "[WitchDorcor]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.DARK_GREEN + "[WitchDoctor]" + p.getPlayerListName());
			}
			break;
		}
		case 15: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 독,불
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "열광자");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[열광자]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[열광자]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "선구자");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[[선구자]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[[선구자]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "화학자");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[화학자]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[화학자]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Maniac");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[Maniac]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[Maniac]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Pioneer");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[[Pioneer]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[[Pioneer]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Chemist");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[Chemist]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[Chemist]" + p.getPlayerListName());
			}
			break;
		}
		case 16: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 번개,대지
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.BLUE + "장인");
					p.setDisplayName(ChatColor.BLUE + "[[장인]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.BLUE + "[[장인]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.BLUE + "창조자");
					p.setDisplayName(ChatColor.BLUE + "[[[창조자]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.BLUE + "[[[창조자]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.BLUE + "무기공");
					p.setDisplayName(ChatColor.BLUE + "[무기공]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.BLUE + "[무기공]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.BLUE + "Artificer");
				p.setDisplayName(ChatColor.BLUE + "[[Artificer]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.BLUE + "[[Artificer]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.BLUE + "Creator");
				p.setDisplayName(ChatColor.BLUE + "[[[Creator]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.BLUE + "[[[Creator]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.BLUE + "Forger");
				p.setDisplayName(ChatColor.BLUE + "[Forger]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.BLUE + "[Forger]" + p.getPlayerListName());
			}
			break;
		}
		case 17: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 번개, 대지
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.AQUA + "제어자");
					p.setDisplayName(ChatColor.AQUA + "[[제어자]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[제어자]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.AQUA + "중재자");
					p.setDisplayName(ChatColor.AQUA + "[[[중재자]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[[중재자]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.AQUA + "공학자");
					p.setDisplayName(ChatColor.AQUA + "[공학자]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[공학자]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.AQUA + "Controller");
				p.setDisplayName(ChatColor.AQUA + "[[Controller]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.AQUA + "[[Controller]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.AQUA + "Operator");
				p.setDisplayName(ChatColor.AQUA + "[[[Operator]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.AQUA + "[[[Operator]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.AQUA + "Engineer");
				p.setDisplayName(ChatColor.AQUA + "[Engineer]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.AQUA + "[Engineer]" + p.getPlayerListName());
			}
			break;
		}
		case 18: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 불, 대지
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.GOLD + "셰프");
					p.setDisplayName(ChatColor.GOLD + "[[셰프]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GOLD + "[[셰프]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.GOLD + "대가");
					p.setDisplayName(ChatColor.GOLD + "[[[대가]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GOLD + "[[[대가]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.GOLD + "요리사");
					p.setDisplayName(ChatColor.GOLD + "[요리사]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GOLD + "[요리사]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.GOLD + "Chef");
				p.setDisplayName(ChatColor.GOLD + "[[Chef]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.GOLD + "[[Chef]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.GOLD + "Demeter");
				p.setDisplayName(ChatColor.GOLD + "[[[Demeter]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.GOLD + "[[[Demeter]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.GOLD + "Cook");
				p.setDisplayName(ChatColor.GOLD + "[Cook]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.GOLD + "[Cook]" + p.getPlayerListName());
			}
			break;
		}
		case 19: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 물
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.BLUE + "왕족");
					p.setDisplayName(ChatColor.BLUE + "[[왕족]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.BLUE + "[[왕족]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.BLUE + "황제");
					p.setDisplayName(ChatColor.BLUE + "[[[황제]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.BLUE + "[[[황제]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.BLUE + "귀족");
					p.setDisplayName(ChatColor.BLUE + "[귀족]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.BLUE + "[귀족]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.BLUE + "Brilliant");
				p.setDisplayName(ChatColor.BLUE + "[[Brilliant]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.BLUE + "[[Brilliant]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.BLUE + "Emperor");
				p.setDisplayName(ChatColor.BLUE + "[[[Emperor]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.BLUE + "[[[Emperor]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.BLUE + "Nobility");
				p.setDisplayName(ChatColor.BLUE + "[Nobility]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.BLUE + "[Nobility]" + p.getPlayerListName());
			}
			break;
		}
		case 20: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 물, 바람
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.DARK_AQUA + "지휘관");
					p.setDisplayName(ChatColor.DARK_AQUA + "[[지휘관]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_AQUA + "[[지휘관]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.DARK_AQUA + "명장");
					p.setDisplayName(ChatColor.DARK_AQUA + "[[[명장]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_AQUA + "[[[명장]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.DARK_AQUA + "바다기사");
					p.setDisplayName(ChatColor.DARK_AQUA + "[바다기사]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_AQUA + "[바다기사]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.DARK_AQUA + "Captain");
				p.setDisplayName(ChatColor.DARK_AQUA + "[[Captain]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.DARK_AQUA + "[[Captain]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.DARK_AQUA + "General");
				p.setDisplayName(ChatColor.DARK_AQUA + "[[[General]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.DARK_AQUA + "[[[General]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.DARK_AQUA + "OceanKnight");
				p.setDisplayName(ChatColor.DARK_AQUA + "[OceanKnight]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.DARK_AQUA + "[OceanKnight]" + p.getPlayerListName());
			}
			break;
		}
		case 21: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 서리
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.AQUA + "만년설");
					p.setDisplayName(ChatColor.AQUA + "[[만년설]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[만년설]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.AQUA + "설화");
					p.setDisplayName(ChatColor.AQUA + "[[[설화]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[[설화]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.AQUA + "빙술사");
					p.setDisplayName(ChatColor.AQUA + "[빙술사]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[빙술사]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.AQUA + "Everfrost");
				p.setDisplayName(ChatColor.AQUA + "[[Everfrost]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.AQUA + "[[Everfrost]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.AQUA + "Khazhad");
				p.setDisplayName(ChatColor.AQUA + "[[[Khazhad]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.AQUA + "[[[Khazhad]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.AQUA + "Frostman");
				p.setDisplayName(ChatColor.AQUA + "[Frostman]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.AQUA + "[Frostman]" + p.getPlayerListName());
			}
			break;
		}
		case 22: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 독, 물
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.AQUA + "달인");
					p.setDisplayName(ChatColor.AQUA + "[[달인]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[달인]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.AQUA + "한량");
					p.setDisplayName(ChatColor.AQUA + "[[[한량]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[[한량]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.AQUA + "낚시꾼");
					p.setDisplayName(ChatColor.AQUA + "[낚시꾼]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[낚시꾼]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.AQUA + "Savant");
				p.setDisplayName(ChatColor.AQUA + "[[Savant]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.AQUA + "[[Savant]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.AQUA + "Idler");
				p.setDisplayName(ChatColor.AQUA + "[[[Idler]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.AQUA + "[[[Idler]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.AQUA + "Angler");
				p.setDisplayName(ChatColor.AQUA + "[Angler]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.AQUA + "[Angler]" + p.getPlayerListName());
			}
			break;
		}
		case 23: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 대지,물
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.DARK_GREEN + "조물주");
					p.setDisplayName(ChatColor.DARK_GREEN + "[[조물주]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GREEN + "[[조물주]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.DARK_GREEN + "자연신");
					p.setDisplayName(ChatColor.DARK_GREEN + "[[[자연신]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GREEN + "[[[자연신]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.DARK_GREEN + "원예가");
					p.setDisplayName(ChatColor.DARK_GREEN + "[원예가]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GREEN + "[원예가]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.DARK_GREEN + "FlowerMage");
				p.setDisplayName(ChatColor.DARK_GREEN + "[[FlowerMage]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.DARK_GREEN + "[[FlowerMage]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.DARK_GREEN + "Flora");
				p.setDisplayName(ChatColor.DARK_GREEN + "[[[Flora]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.DARK_GREEN + "[[[Flora]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.DARK_GREEN + "Gardener");
				p.setDisplayName(ChatColor.DARK_GREEN + "[Gardener]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.DARK_GREEN + "[Gardener]" + p.getPlayerListName());
			}
			break;
		}
		case 24: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 어둠, 바람
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.DARK_GRAY + "암살자");
					p.setDisplayName(ChatColor.DARK_GRAY + "[[암살자]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GRAY + "[[암살자]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.DARK_GRAY + "암영");
					p.setDisplayName(ChatColor.DARK_GRAY + "[[[암영]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GRAY + "[[[암영]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.DARK_GRAY + "단검사");
					p.setDisplayName(ChatColor.DARK_GRAY + "[단검사]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GRAY + "[단검사]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.DARK_GRAY + "Assassin");
				p.setDisplayName(ChatColor.DARK_GRAY + "[[Assassin]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.DARK_GRAY + "[[Assassin]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.DARK_GRAY + "Shade");
				p.setDisplayName(ChatColor.DARK_GRAY + "[[[Shade]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.DARK_GRAY + "[[[Shade]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.DARK_GRAY + "Daggerist");
				p.setDisplayName(ChatColor.DARK_GRAY + "[Daggerist]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.DARK_GRAY + "[Daggerist]" + p.getPlayerListName());
			}
			break;
		}
		case 25: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 독,대지
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.AQUA + "감내자");
					p.setDisplayName(ChatColor.AQUA + "[[감내자]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[감내자]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.AQUA + "역전가");
					p.setDisplayName(ChatColor.AQUA + "[[[역전가]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[[역전가]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.AQUA + "투사");
					p.setDisplayName(ChatColor.AQUA + "[투사]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[투사]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.AQUA + "RiskTaker");
				p.setDisplayName(ChatColor.AQUA + "[[RiskTaker]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.AQUA + "[[RiskTaker]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.AQUA + "GameChanger");
				p.setDisplayName(ChatColor.AQUA + "[[[GameChanger]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.AQUA + "[[[GameChanger]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.AQUA + "Broiler");
				p.setDisplayName(ChatColor.AQUA + "[Broiler]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.AQUA + "[Broiler]" + p.getPlayerListName());
			}
			break;
		}
		case 26: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// 대지, 번개
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "연출가");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[연출가]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[연출가]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "거장");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[[거장]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[[거장]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "예술가");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[예술가]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[예술가]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Producer");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[Producer]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[Producer]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Maestro");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[[Maestro]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[[Maestro]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Musician");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[Musician]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[Musician]" + p.getPlayerListName());
			}
			break;
		}

		}
		p.setDisplayName(p.getDisplayName() + " <LV." + p.getLevel() + ">");
	}

	final public void open(Player p) {
		
		/*final BlockData fb = p.getLocation().getBlock().getBlockData();
		final Material fm = p.getLocation().getBlock().getType();
		
		p.getWorld().setType(p.getLocation(), Material.CHEST);
		BlockState ch = p.getWorld().getBlockState(p.getLocation());
		Chest chh = (Chest) ch;
		chh.setCustomName("Classes");
		

		chh.setType(fm);
		chh.setBlockData(fb);*/


		Inventory inv = Bukkit.createInventory(null, 54, "Classes");
		if (p.getLocale().equalsIgnoreCase("ko_kr")) {

			itemset(ChatColor.BLUE + "유형: 전사", Material.IRON_ORE, 0, 1, Arrays.asList("근접 또는 광역공격에 특화"), 1, inv);
			itemset(ChatColor.YELLOW + "유형: 사수", Material.EMERALD_ORE, 0, 1, Arrays.asList("원거리 공격에 특화"), 2, inv);
			itemset(ChatColor.GRAY + "유형: 격투가", Material.COAL_ORE, 0, 1, Arrays.asList("높은 체력, 지속공격에 특화"), 3, inv);
			itemset(ChatColor.LIGHT_PURPLE + "유형: 마법사", Material.GOLD_ORE, 0, 1, Arrays.asList("집중공격에 특화"), 4, inv);
			itemset(ChatColor.GOLD + "유형: 전문가", Material.REDSTONE_ORE, 0, 1, Arrays.asList("제압에 특화"), 5, inv);
			itemset(ChatColor.AQUA + "유형: 해상", Material.LAPIS_ORE, 0, 1,
					Arrays.asList("수영시 바다관련 이로운 효과를 얻습니다", "광역 공격에 특화"), 6, inv);
			itemset(ChatColor.DARK_GREEN + "유형: 복수자", Material.COPPER_ORE, 0, 1, Arrays.asList("높은 생존성과 기능성"), 7, inv);

			itemset(ChatColor.BLUE + "역할군: 방어", Material.CREEPER_BANNER_PATTERN, 0, 1,
					Arrays.asList("피해 감소 기술을 소유하고 있습니다"), 9, inv);
			itemset(ChatColor.RED + "역할군: 공격", Material.CREEPER_BANNER_PATTERN, 0, 1,
					Arrays.asList("보스,리더형 몹에게 추가피해를 줍니다", "타역할군보다 방어력이 낮습니다"), 18, inv);
			itemset(ChatColor.GREEN + "역할군: 제압", Material.CREEPER_BANNER_PATTERN, 0, 1,
					Arrays.asList("타역할군보다 제압기술이 많습니다"), 27, inv);
			itemset(ChatColor.DARK_AQUA + "역할군: 지원", Material.CREEPER_BANNER_PATTERN, 0, 1, Arrays
					.asList("버프, 디버프 스킬을 소유하고 있습니다", "타역할군보다 데미지가 낮습니다", "파티가 없을시 데미지가 증가합니다", "(증가치는 경험치 레벨에 비례합니다)"),
					36, inv);

			itemset(ChatColor.BLUE + "검사", Material.IRON_SWORD, 0, 1, Arrays.asList("추천 장비:", "검", "", "공격: 3.5/5",
					"방어: 4/5", "제어: 3/5", "지원: 0/5", "범위: 5/5", "사거리: 2.5/5", "기동: 3.5/5", "난이도: 보통"), 10, inv);
			itemset(ChatColor.BLUE + "사냥꾼", Material.IRON_AXE, 0, 1, Arrays.asList("추천 장비:", "도끼", "", "공격: 5/5",
					"방어: 2.5/5", "제어: 2.5/5", "지원: 1/5", "범위: 1/5", "사거리: 1/5", "기동: 5/5", "난이도: 어려움"), 19, inv);
			itemset(ChatColor.BLUE + "투사[제작중]", Material.MACE, 0, 1, Arrays.asList("추천 장비:", "철퇴", "", "공격: 3/5",
					"방어: 2.5/5", "제어: 4/5", "지원: 1/5", "범위: 4/5", "사거리: 2/5", "기동: 2.5/5", "난이도: 보통"), 28, inv);
			itemset(ChatColor.BLUE + "성기사", Material.SHIELD, 0, 1, Arrays.asList("추천 장비:", "주:도끼 또는 철퇴, 보조:방패", "",
					"공격: 1.5/5", "방어: 5/5", "제어: 3/5", "지원: 4.5/5", "범위: 2/5", "사거리: 2/5", "기동: 2/5", "난이도: 쉬움"), 37,
					inv);

			itemset(ChatColor.BLUE + "궁수", Material.BOW, 0, 1, Arrays.asList("추천 장비:", "활", "", "공격: 4/5", "방어: 3.5/5",
					"제어: 2.5/5", "지원: 0/5", "범위: 2.5/5", "사거리: 3.5/5", "기동: 3.5/5", "난이도: 보통"), 11, inv);
			itemset(ChatColor.BLUE + "저격수", Material.CROSSBOW, 0, 1, Arrays.asList("추천 장비:", "쇠뇌", "", "공격: 5/5",
					"방어: 0/5", "제어: 1.5/5", "지원: 1/5", "범위: 2/5", "사거리: 5/5", "기동: 2.5/5", "난이도: 어려움"), 20, inv);
			itemset(ChatColor.BLUE + "원소술사", Material.FIREWORK_ROCKET, 0, 1, Arrays.asList("추천 장비:", "활", "", "공격: 3/5",
					"방어: 2/5", "제어: 4/5", "지원: 1/5", "범위: 3.5/5", "사거리: 4/5", "기동: 2/5", "난이도: 보통"), 29, inv);
			itemset(ChatColor.BLUE + "의궁", Material.TIPPED_ARROW, 0, 1, Arrays.asList("추천 장비:", "쇠뇌", "", "공격: 2/5",
					"방어: 3.5/5", "제어: 3.5/5", "지원: 5/5", "범위: 2.5/5", "사거리: 3.5/5", "기동: 2/5", "난이도: 어려움"), 38, inv);

			itemset(ChatColor.BLUE + "권사", Material.IRON_HELMET, 0, 1, Arrays.asList("추천 장비:", "양손 너클", "", "공격: 3/5",
					"방어: 5/5", "제어: 2.5/5", "지원: 0/5", "범위: 2/5", "사거리: 1.5/5", "기동: 3/5", "난이도: 어려움"), 12, inv);
			itemset(ChatColor.BLUE + "조련사", Material.LEAD, 0, 1, Arrays.asList("추천 장비:", "양손 너클", "공격: 4/5", "방어: 1/5",
					"제어: 3/5", "지원: 0/5", "범위: 3/5", "사거리: 4/5", "기동: 1/5", "난이도: 보통"), 21, inv);
			itemset(ChatColor.BLUE + "유술가[제작중]", Material.GOLDEN_CHESTPLATE, 0, 1, Arrays.asList("추천 장비:", "양손 너클", "",
					"공격: 3/5", "방어: 4/5", "제어: 4.5/5", "지원: 0/5", "범위: 2.5/5", "사거리: 2.5/5", "기동: 2/5", "난이도: 보통"), 30,
					inv);
			itemset(ChatColor.BLUE + "도사", Material.SOUL_CAMPFIRE, 0, 1,
					Arrays.asList("추천 장비:", "양손 너클", "", "공격: 2.5/5", "방어: 2.5/5", "제어: 3.5/5", "지원: 4/5", "범위: 3.5/5",
							"사거리: 2.5/5", "기동: 2.5/5", "난이도: 보통"),
					39, inv);

			itemset(ChatColor.BLUE + "화염술사", Material.FIRE_CHARGE, 0, 1, Arrays.asList("추천 장비:", "완드", "", "공격: 4/5",
					"방어: 3.5/5", "제어: 2.5/5", "지원: 0.5/5", "범위: 3/5", "사거리: 3.5/5", "기동: 2/5", "난이도: 보통"), 13, inv);
			itemset(ChatColor.BLUE + "환술사", Material.JACK_O_LANTERN, 0, 1, Arrays.asList("추천 장비:", "완드", "",
					"공격: 4.5/5", "방어: 1.5/5", "제어: 2.5/5", "지원: 0/5", "범위: 3/5", "사거리: 2/5", "기동: 4/5", "난이도: 어려움"), 22,
					inv);
			itemset(ChatColor.BLUE + "원예가[제작중]", Material.FLOWERING_AZALEA_LEAVES, 0, 1, Arrays.asList("추천 장비:", "삽", "",
					"공격: 3.5/5", "방어: 2.5/5", "제어: 4.5/5", "지원: 1/5", "범위: 3/5", "사거리: 3/5", "기동: 1/5", "난이도: 쉬움"), 31,
					inv);
			itemset(ChatColor.BLUE + "예술가[제작중]", Material.JUKEBOX, 0, 1, Arrays.asList("추천 장비:", "완드", "", "공격: 3/5",
					"방어: 1/5", "제어: 3/5", "지원: 4/5", "범위: 3.5/5", "사거리: 4/5", "기동: 2/5", "난이도: 보통"), 40, inv);

			itemset(ChatColor.BLUE + "화학자", Material.POTION, 0, 1, Arrays.asList("추천 장비:", "곡괭이", "", "공격: 3/5",
					"방어: 4.5/5", "제어: 2.5/5", "지원: 1/5", "범위: 3.5/5", "사거리: 1.5/5", "기동: 3.5/5", "난이도: 쉬움"), 14, inv);
			itemset(ChatColor.BLUE + "무기공", Material.BEACON, 0, 1, Arrays.asList("추천 장비:", "곡괭이", "", "공격: 4.5/5",
					"방어: 2/5", "제어: 2.5/5", "지원: 1/5", "범위: 3/5", "사거리: 5/5", "기동: 1.5/5", "난이도: 보통"), 23, inv);
			itemset(ChatColor.BLUE + "공학자", Material.IRON_PICKAXE, 0, 1, Arrays.asList("추천 장비:", "곡괭이", "", "공격: 3/5",
					"방어: 4/5", "제어: 5/5", "지원: 1/5", "범위: 2.5/5", "사거리: 2/5", "기동: 2.5/5", "난이도: 보통"), 32, inv);
			itemset(ChatColor.BLUE + "요리사[제작중]", Material.COOKED_BEEF, 0, 1, Arrays.asList("추천 장비:", "삽", "", "공격: 2.5/5",
					"방어: 3/5", "제어: 3/5", "지원: 4/5", "범위: 3.5/5", "사거리: 3/5", "기동: 1/5", "난이도: 쉬움"), 41, inv);

			itemset(ChatColor.BLUE + "바다기사", Material.TRIDENT, 0, 1, Arrays.asList("추천 장비:", "삼지창, 방패 ", "",
					"공격: 3.5/5", "방어: 4/5", "제어: 2/5", "지원: 0/5", "범위: 4/5", "사거리: 3.5/5", "기동: 3.8/5", "난이도: 어려움"), 15,
					inv);
			itemset(ChatColor.BLUE + "귀족", Material.HEART_OF_THE_SEA, 0, 1, Arrays.asList("추천 장비:", "삼지창", "",
					"공격: 4.5/5", "방어: 2.5/5", "제어: 2.5/5", "지원: 1/5", "범위: 3.5/5", "사거리: 4/5", "기동: 2/5", "난이도: 보통"),
					24, inv);
			itemset(ChatColor.BLUE + "빙술사", Material.PACKED_ICE, 0, 1,
					Arrays.asList("추천 장비:", "비늘(프리즈머린 조각)", "", "공격: 3.5/5", "방어: 2.5/5", "제어: 4.5/5", "지원: 0/5",
							"범위: 2.5/5", "사거리: 2.5/5", "기동: 1.5/5", "난이도: 어려움"),
					33, inv);
			itemset(ChatColor.BLUE + "낚시꾼", Material.FISHING_ROD, 0, 1, Arrays.asList("추천 장비:", "낚시대", "", "공격: 2.5/5",
					"방어: 3/5", "제어: 4/5", "지원: 4/5", "범위: 2.5/5", "사거리: 3.5/5", "기동: 2/5", "난이도: 쉬움"), 42, inv);

			itemset(ChatColor.BLUE + "광전사", Material.CRIMSON_ROOTS, 0, 1, Arrays.asList("추천 장비:", "검", "", "공격: 4/5",
					"방어: 3/5", "제어: 3/5", "지원: 0/5", "범위: 3.5/5", "사거리: 3.5/5", "기동: 2.5/5", "난이도: 보통"), 16, inv);
			itemset(ChatColor.BLUE + "단검사[제작중]", Material.SHEARS, 0, 1, Arrays.asList("추천 장비:", "단검", "", "공격: 4.75/5",
					"방어: 1.5/5", "제어: 2/5", "지원: 0/5", "범위: 3/5", "사거리: 1.5/5", "기동: 4.5/5", "난이도: 어려움"), 25, inv);
			itemset(ChatColor.BLUE + "위더리스트", Material.WITHER_ROSE, 0, 1, Arrays.asList("추천 장비:", "괭이", "", "공격: 3/5",
					"방어: 3/5", "제어: 4/5", "지원: 2/5", "범위: 3/5", "사거리: 4/5", "기동: 5/5", "난이도: 보통"), 34, inv);
			itemset(ChatColor.BLUE + "부두술사", Material.TOTEM_OF_UNDYING, 0, 1,
					Arrays.asList("추천 장비:", "주:괭이, 보조:불사의토템 ", "", "공격: 2.5/5", "방어: 3.5/5", "제어: 1.5/5", "지원: 4.5/5", "범위: 2.5/5",
							"사거리: 2.5/5", "기동: 2/5", "난이도: 어려움"),
					43, inv);

		} else {
			itemset(ChatColor.BLUE + "Type: Warrior", Material.IRON_ORE, 0, 1,
					Arrays.asList("Specified To Melee or Wide Attack"), 1, inv);
			itemset(ChatColor.YELLOW + "Type: Shooter", Material.EMERALD_ORE, 0, 1,
					Arrays.asList("Specified To Ranged Attack"), 2, inv);
			itemset(ChatColor.GRAY + "Type: Fighter", Material.COAL_ORE, 0, 1,
					Arrays.asList("More Vitality Than Other Types", "Specified To Sustain Dealing"), 3, inv);
			itemset(ChatColor.LIGHT_PURPLE + "Type: Mage", Material.GOLD_ORE, 0, 1,
					Arrays.asList("Specified To Burst Dealing"), 4, inv);
			itemset(ChatColor.GOLD + "Type: Technician", Material.REDSTONE_ORE, 0, 1,
					Arrays.asList("Specified To Holding"), 5, inv);
			itemset(ChatColor.AQUA + "Type: Marine", Material.LAPIS_ORE, 0, 1,
					Arrays.asList("Get Positive Ocean Effect When Swim", "Specified To Area Attack"), 6, inv);
			itemset(ChatColor.DARK_GREEN + "Type: Avenger", Material.COPPER_ORE, 0, 1,
					Arrays.asList("Higher Utility&Survivability Than Other Types"), 7, inv);

			itemset(ChatColor.BLUE + "Role: Bruiser", Material.CREEPER_BANNER_PATTERN, 0, 1,
					Arrays.asList("Has damage reduction skill"), 9, inv);
			itemset(ChatColor.RED + "Role: Nuker", Material.CREEPER_BANNER_PATTERN, 0, 1,
					Arrays.asList("Damage More to Boss & Leader Mob", "Low Armor Than Other Roles"), 18, inv);
			itemset(ChatColor.GREEN + "Role: Holder", Material.CREEPER_BANNER_PATTERN, 0, 1,
					Arrays.asList("Has More Holding Skills Than Other Roles"),
					27, inv);
			itemset(ChatColor.DARK_AQUA + "Role: Supporter", Material.CREEPER_BANNER_PATTERN, 0, 1,
					Arrays.asList("Has Healing Skills & Buff, Debuff Skills", "Low AttackDamage Than Other Roles",
							"Increase Damage If You Don't Have Party", "(Affect By EXP Levels)"),
					36, inv);

			itemset(ChatColor.BLUE + "SwordMan", Material.IRON_SWORD, 0, 1,
					Arrays.asList("Recommended equipment:", "Sword", "", "Attack: 3.5/5", "Defence: 4/5",
							"Control: 3/5", "Support: 0/5", "Area: 5/5", "Range: 2.5/5", "Mobility: 3.5/5",
							"Difficulty: Normal"),
					10, inv);
			itemset(ChatColor.BLUE + "Hunter", Material.IRON_AXE, 0, 1,
					Arrays.asList("Recommended equipment:", "Axe", "", "Attack: 5/5", "Defence: 2.5/5",
							"Control: 2.5/5", "Support: 1/5", "Area: 1/5", "Range: 1/5", "Mobility: 5/5",
							"Difficulty: Hard"),
					19, inv);
			itemset(ChatColor.BLUE + "Broiler[To be updated]", Material.MACE, 0, 1,
					Arrays.asList("Recommended equipment:", "Mace", "", "Attack: 3/5", "Defence: 2.5/5",
							"Control: 4/5", "Support: 1/5", "Area: 4/5", "Range: 2/5", "Mobility: 2/5",
							"Difficulty: Normal"),
					28, inv);
			itemset(ChatColor.BLUE + "Paladin", Material.SHIELD, 0, 1,
					Arrays.asList("Recommended equipment:", "Axe or Mace(main) & Shield(off)", "", "Attack: 1.5/5",
							"Defence: 5/5", "Control: 3/5", "Support: 4.5/5", "Area: 2/5", "Range: 2/5",
							"Mobility: 2/5", "Difficulty: Easy"),
					37, inv);

			itemset(ChatColor.BLUE + "Archer", Material.BOW, 0, 1,
					Arrays.asList("Recommended equipment:", "Bow", "", "Attack: 4/5", "Defence: 3.5/5",
							"Control: 2.5/5", "Support: 0/5", "Area: 2.5/5", "Range: 3.5/5", "Mobility: 3.5/5",
							"Difficulty: Normal"),
					11, inv);
			itemset(ChatColor.BLUE + "Sniper", Material.CROSSBOW, 0, 1,
					Arrays.asList("Recommended equipment:", "Crossbow", "", "Attack: 5/5", "Defence: 0/5",
							"Control: 1.5/5", "Support: 1/5", "Area: 2/5", "Range: 5/5", "Mobility: 2.5/5",
							"Difficulty: Hard"),
					20, inv);
			itemset(ChatColor.BLUE + "Elementalist", Material.SPECTRAL_ARROW, 0, 1,
					Arrays.asList("Recommended equipment:", "Bow", "", "Attack: 3/5", "Defence: 2/5", "Control: 4/5",
							"Support: 1/5", "Area: 3.5/5", "Range: 4/5", "Mobility: 2/5", "Difficulty: Normal"),
					29, inv);
			itemset(ChatColor.BLUE + "ArrowMedic", Material.TIPPED_ARROW, 0, 1,
					Arrays.asList("Recommended equipment:", "Crossbow", "", "Attack: 2/5", "Defence: 3.5/5",
							"Control: 3.5/5", "Support: 5/5", "Area: 2.5/5", "Range: 3.5/5", "Mobility: 2/5",
							"Difficulty: Hard"),
					38, inv);

			itemset(ChatColor.BLUE + "Boxer", Material.IRON_HELMET, 0, 1,
					Arrays.asList("Recommended equipment:", "Knuckles in both hands", "", "Attack: 3/5", "Defence: 5/5",
							"Control: 2.5/5", "Support: 0/5", "Area: 2/5", "Range: 1.5/5", "Mobility: 3/5",
							"Difficulty: Hard"),
					12, inv);
			itemset(ChatColor.BLUE + "Tamer", Material.LEAD, 0, 1,
					Arrays.asList("Recommended equipment:", "Knuckles in both hands", "Attack: 4/5", "Defence: 1/5",
							"Control: 3/5", "Support: 0/5", "Area: 3/5", "Range: 4/5", "Mobility: 1/5",
							"Difficulty: Normal"),
					21, inv);
			itemset(ChatColor.BLUE + "Wrestler[To be updated]", Material.GOLDEN_CHESTPLATE, 0, 1,
					Arrays.asList("Recommended equipment:", "Knuckles in both hands", "", "Attack: 3/5", "Defence: 4/5",
							"Control: 4.5/5", "Support: 0/5", "Area: 2.5/5", "Range: 2.5/5", "Mobility: 2/5",
							"Difficulty: Normal"),
					30, inv);
			itemset(ChatColor.BLUE + "Taoist", Material.SOUL_CAMPFIRE, 0, 1,
					Arrays.asList("Recommended equipment:", "Knuckles in both hands", "", "Attack: 2.5/5",
							"Defence: 2.5/5", "Control: 3.5/5", "Support: 4/5", "Area: 3.5/5", "Range: 2.5/5",
							"Mobility: 2.5/5", "Difficulty: Normal"),
					39, inv);

			itemset(ChatColor.BLUE + "FireMage", Material.FIRE_CHARGE, 0, 1,
					Arrays.asList("Recommended equipment:", "Rod", "", "Attack: 4/5", "Defence: 3.5/5",
							"Control: 2.5/5", "Support: 0.5/5", "Area: 3/5", "Range: 3.5/5", "Mobility: 2/5",
							"Difficulty: Normal"),
					13, inv);
			itemset(ChatColor.BLUE + "Illusionist", Material.JACK_O_LANTERN, 0, 1,
					Arrays.asList("Recommended equipment:", "Rod", "", "Attack: 4.5/5", "Defence: 1.5/5",
							"Control: 2.5/5", "Support: 0/5", "Area: 3/5", "Range: 2/5", "Mobility: 4/5",
							"Difficulty: Hard"),
					22, inv);
			itemset(ChatColor.BLUE + "Gardener[To be updated]", Material.FLOWERING_AZALEA_LEAVES, 0, 1,
					Arrays.asList("Recommended equipment:", "Shovel", "", "Attack: 3.5/5", "Defence: 2.5/5",
							"Control: 4.5/5", "Support: 1/5", "Area: 3.5/5", "Range: 3/5", "Mobility: 1/5",
							"Difficulty: Easy"),
					31, inv);
			itemset(ChatColor.BLUE + "Musician[To be updated]", Material.JUKEBOX, 0, 1,
					Arrays.asList("Recommended equipment:", "Rod", "", "Attack: 3/5", "Defence: 1.5/5",
							"Control: 3.5/5", "Support: 4/5", "Area: 3.5/5", "Range: 4/5", "Mobility: 2/5",
							"Difficulty: Normal"),
					40, inv);

			itemset(ChatColor.BLUE + "Chemist", Material.POTION, 0, 1,
					Arrays.asList("Recommended equipment:", "Pickaxe", "", "Attack: 3/5", "Defence: 4.5/5",
							"Control: 2.5/5", "Support: 1/5", "Area: 3.5/5", "Range: 1.5/5", "Mobility: 3.5/5",
							"Difficulty: Easy"),
					14, inv);
			itemset(ChatColor.BLUE + "Forger", Material.BEACON, 0, 1,
					Arrays.asList("Recommended equipment:", "Pickaxe", "", "Attack: 4.5/5", "Defence: 2/5",
							"Control: 2.5/5", "Support: 1/5", "Area: 3/5", "Range: 5/5", "Mobility: 1.5/5",
							"Difficulty: Normal"),
					23, inv);
			itemset(ChatColor.BLUE + "Engineer", Material.DISPENSER, 0, 1,
					Arrays.asList("Recommended equipment:", "Pickaxe", "", "Attack: 3/5", "Defence: 4/5",
							"Control: 5/5", "Support: 1/5", "Area: 2.5/5", "Range: 2/5", "Mobility: 2.5/5",
							"Difficulty: Normal"),
					32, inv);
			itemset(ChatColor.BLUE + "Cook[To be updated]", Material.COOKED_BEEF, 0, 1,
					Arrays.asList("Recommended equipment:", "Shovel, ", "", "Attack: 2.5/5", "Defence: 3/5",
							"Control: 3/5", "Support: 4/5", "Area: 3.5/5", "Range: 3/5", "Mobility: 1/5",
							"Difficulty: Easy"),
					41, inv);

			itemset(ChatColor.BLUE + "OceanKnight", Material.TRIDENT, 0, 1,
					Arrays.asList("Recommended equipment:", "Trident & Shield", "", "Attack: 3.5/5", "Defence: 4/5",
							"Control: 2/5", "Support: 0/5", "Area: 4/5", "Range: 3.5/5", "Mobility: 3.8/5",
							"Difficulty: Hard"),
					15, inv);
			itemset(ChatColor.BLUE + "Nobility", Material.HEART_OF_THE_SEA, 0, 1,
					Arrays.asList("Recommended equipment:", "Trident", "", "Attack: 4.5/5", "Defence: 2.5/5",
							"Control: 2.5/5", "Support: 1/5", "Area: 3.5/5", "Range: 4/5", "Mobility: 2/5",
							"Difficulty: Normal"),
					24, inv);
			itemset(ChatColor.BLUE + "Frostman", Material.PACKED_ICE, 0, 1,
					Arrays.asList("Recommended equipment:", "Shard", "", "Attack: 3.5/5", "Defence: 2.5/5",
							"Control: 4.5/5", "Support: 0/5", "Area: 2.5/5", "Range: 2.5/5", "Mobility: 1.5/5",
							"Difficulty: Hard"),
					33, inv);
			itemset(ChatColor.BLUE + "Angler", Material.FISHING_ROD, 0, 1,
					Arrays.asList("Recommended equipment:", "FishingRod", "", "Attack: 2.5/5", "Defence: 3/5",
							"Control: 4/5", "Support: 4/5", "Area: 2.5/5", "Range: 3.5/5", "Mobility: 2/5",
							"Difficulty: Easy"),
					42, inv);

			itemset(ChatColor.BLUE + "Berserker", Material.CRIMSON_ROOTS, 0, 1,
					Arrays.asList("Recommended equipment:", "Sword & Hoe", "", "Attack: 3.5/5", "Defence: 3/5",
							"Control: 4/5", "Support: 0/5", "Area: 3.5/5", "Range: 3.5/5", "Mobility: 2.5/5",
							"Difficulty: Normal"),
					16, inv);
			itemset(ChatColor.BLUE + "Daggerist[To be updated]", Material.SHEARS, 0, 1,
					Arrays.asList("Recommended equipment:", "Dagger in both Hands", "", "Attack: 4.75/5",
							"Defence: 1.5/5", "Control: 2/5", "Support: 0/5", "Area: 3/5", "Range: 1.5/5",
							"Mobility: 4.5/5", "Difficulty: Hard"),
					25, inv);
			itemset(ChatColor.BLUE + "Witherist", Material.WITHER_ROSE, 0, 1,
					Arrays.asList("Recommended equipment:", "Hoe", "", "Attack: 3/5", "Defence: 3/5", "Control: 4/5",
							"Support: 2/5", "Area: 3/5", "Range: 4/5", "Mobility: 5/5", "Difficulty: Normal"),
					34, inv);
			itemset(ChatColor.BLUE + "WitchDoctor", Material.TOTEM_OF_UNDYING, 0, 1,
					Arrays.asList("Recommended equipment:", "Hoe(main) & Totem of undying(off)", "", "Attack: 2.5/5", "Defence: 3.5/5",
							"Control: 1.5/5", "Support: 4.5/5", "Area: 2.5/5", "Range: 2.5/5", "Mobility: 2/5",
							"Difficulty: Hard"),
					43, inv);

		}
		p.openInventory(inv);
	}

}