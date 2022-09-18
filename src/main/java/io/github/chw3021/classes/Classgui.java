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
		items.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
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
		items.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
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
				if (Proficiency.getpro(p) == 1) { // ����
					p.addScoreboardTag(ChatColor.AQUA + "�˰�");
					p.setDisplayName(ChatColor.AQUA + "[[�˰�]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[�˰�]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) == 2) {
					p.addScoreboardTag(ChatColor.AQUA + "�˱�");
					p.setDisplayName(ChatColor.AQUA + "[[[�˱�]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[[�˱�]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.AQUA + "�˻�");
					p.setDisplayName(ChatColor.AQUA + "[�˻�]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[�˻�]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) { // ����, ��
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.DARK_RED + "�л���");
					p.setDisplayName(ChatColor.DARK_RED + "[[�л���]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_RED + "[[�л���]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.DARK_RED + "�����");
					p.setDisplayName(ChatColor.DARK_RED + "[[[�����]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_RED + "[[[�����]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.DARK_RED + "������");
					p.setDisplayName(ChatColor.DARK_RED + "[������]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_RED + "[������]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// �ٶ�
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.GREEN + "��ɱ�");
					p.setDisplayName(ChatColor.GREEN + "[[��ɱ�]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GREEN + "[[��ɱ�]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.GREEN + "������");
					p.setDisplayName(ChatColor.GREEN + "[[[������]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GREEN + "[[[������]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.GREEN + "��ɲ�");
					p.setDisplayName(ChatColor.GREEN + "[��ɲ�]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GREEN + "[��ɲ�]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ����,��
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.WHITE + "��ȣ��");
					p.setDisplayName(ChatColor.WHITE + "[[��ȣ��]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.WHITE + "[[��ȣ��]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.WHITE + "������");
					p.setDisplayName(ChatColor.WHITE + "[[[������]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.WHITE + "[[[������]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.WHITE + "�����");
					p.setDisplayName(ChatColor.WHITE + "[�����]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.WHITE + "[�����]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// �ٶ�
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.getByChar("336600") + "����");
					p.setDisplayName(ChatColor.getByChar("336600") + "[[����]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.getByChar("336600") + "[[����]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.getByChar("336600") + "����");
					p.setDisplayName(ChatColor.getByChar("336600") + "[[[����]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.getByChar("336600") + "[[[����]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.getByChar("336600") + "���ݼ�");
					p.setDisplayName(ChatColor.getByChar("336600") + "[���ݼ�]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.getByChar("336600") + "[���ݼ�]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ��,��,�ٶ�,����
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "���ռ���");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[���ռ���]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[���ռ���]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "�ź����");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[[�ź����]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[[�ź����]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "���Ҽ���");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[���Ҽ���]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[���Ҽ���]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Manufacturer");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[Manufacturer]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[Manufacturer]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Mysterious");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[[Mysterious]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[[Mysterious]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Launcher");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[Launcher]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[Launcher]" + p.getPlayerListName());
			}
			break;
		}
		case 6: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ����
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.BLUE + "����");
					p.setDisplayName(ChatColor.BLUE + "[[����]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.BLUE + "[[����]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.BLUE + "���");
					p.setDisplayName(ChatColor.BLUE + "[[[���]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.BLUE + "[[[���]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.BLUE + "�ü�");
					p.setDisplayName(ChatColor.BLUE + "[�ü�]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.BLUE + "[�ü�]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.BLUE + "Combatant");
				p.setDisplayName(ChatColor.BLUE + "[[Combatant]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.BLUE + "[[Combatant]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.BLUE + "BattleMaster");
				p.setDisplayName(ChatColor.BLUE + "[[[BattleMaster]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.BLUE + "[[[BattleMaster]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.BLUE + "Archer");
				p.setDisplayName(ChatColor.BLUE + "[Archer]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.BLUE + "[Archer]" + p.getPlayerListName());
			}
			break;
		}
		case 61: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ����, ��
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "�һ���");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[�һ���]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[�һ���]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "����");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[[����]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[[����]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "�Ǳ�");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[�Ǳ�]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[�Ǳ�]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Surgeon");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[Surgeon]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[Surgeon]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Rescuer");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[[Rescuer]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[[Rescuer]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Medic");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[Medic]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[Medic]" + p.getPlayerListName());
			}
			break;
		}
		case 7: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ����, ����
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.GRAY + "ö��");
					p.setDisplayName(ChatColor.GRAY + "[[ö��]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GRAY + "[[ö��]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.GRAY + "�ݰ��ұ�");
					p.setDisplayName(ChatColor.GRAY + "[[[�ݰ��ұ�]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GRAY + "[[[�ݰ��ұ�]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.GRAY + "�ǻ�");
					p.setDisplayName(ChatColor.GRAY + "[�ǻ�]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GRAY + "[�ǻ�]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// �ٶ�
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.GOLD + "�Ϸ�");
					p.setDisplayName(ChatColor.GOLD + "[[�Ϸ�]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GOLD + "[[�Ϸ�]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.GOLD + "�ػ�");
					p.setDisplayName(ChatColor.GOLD + "[[[�ػ�]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GOLD + "[[[�ػ�]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.GOLD + "������");
					p.setDisplayName(ChatColor.GOLD + "[������]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GOLD + "[������]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.GOLD + "Champion");
				p.setDisplayName(ChatColor.GOLD + "[[Champion]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.GOLD + "[[Champion]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.GOLD + "Greatest");
				p.setDisplayName(ChatColor.GOLD + "[[[Greatest]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.GOLD + "[[[Greatest]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.GOLD + "Wrestler");
				p.setDisplayName(ChatColor.GOLD + "[Wrestler]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.GOLD + "[Wrestler]" + p.getPlayerListName());
			}
			break;
		}
		case 9: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ��, ����
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.DARK_GRAY + "������");
					p.setDisplayName(ChatColor.DARK_GRAY + "[[������]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GRAY + "[[������]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.DARK_GRAY + "������");
					p.setDisplayName(ChatColor.DARK_GRAY + "[[[������]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GRAY + "[[[������]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.DARK_GRAY + "���û�");
					p.setDisplayName(ChatColor.DARK_GRAY + "[���û�]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GRAY + "[���û�]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ���,����, ��,����
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.YELLOW + "����");
					p.setDisplayName(ChatColor.YELLOW + "[[����]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.YELLOW + "[[����]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.YELLOW + "�ż�");
					p.setDisplayName(ChatColor.YELLOW + "[[[�ż�]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.YELLOW + "[[[�ż�]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.YELLOW + "����");
					p.setDisplayName(ChatColor.YELLOW + "[����]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.YELLOW + "[����]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// �ٶ�
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.DARK_PURPLE + "�Ҹ��");
					p.setDisplayName(ChatColor.DARK_PURPLE + "[[�Ҹ��]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_PURPLE + "[[�Ҹ��]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.DARK_PURPLE + "ȯ����");
					p.setDisplayName(ChatColor.DARK_PURPLE + "[[[ȯ����]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_PURPLE + "[[[ȯ����]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.DARK_PURPLE + "ȯ����");
					p.setDisplayName(ChatColor.DARK_PURPLE + "[ȯ����]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_PURPLE + "[ȯ����]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ��
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.RED + "ȭ��");
					p.setDisplayName(ChatColor.RED + "[[ȭ��]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.RED + "[[ȭ��]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.RED + "�¾��");
					p.setDisplayName(ChatColor.RED + "[[[�¾��]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.RED + "[[[�¾��]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.RED + "ȭ������");
					p.setDisplayName(ChatColor.RED + "[ȭ������]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.RED + "[ȭ������]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ���
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.GRAY + "������");
					p.setDisplayName(ChatColor.GRAY + "[[������]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GRAY + "[[������]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.GRAY + "������");
					p.setDisplayName(ChatColor.GRAY + "[[[������]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GRAY + "[[[������]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.GRAY + "��������Ʈ");
					p.setDisplayName(ChatColor.GRAY + "[��������Ʈ]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GRAY + "[��������Ʈ]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ����
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.DARK_GREEN + "��Ȱ��");
					p.setDisplayName(ChatColor.DARK_GREEN + "[[��Ȱ��]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GREEN + "[[��Ȱ��]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.DARK_GREEN + "�Ҹ���");
					p.setDisplayName(ChatColor.DARK_GREEN + "[[[�Ҹ���]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GREEN + "[[[�Ҹ���]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.DARK_GREEN + "�εμ���");
					p.setDisplayName(ChatColor.DARK_GREEN + "[�εμ���]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GREEN + "[�εμ���]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ��,��
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "������");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[������]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[������]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "������");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[[������]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[[������]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "ȭ����");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[ȭ����]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[ȭ����]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ����,����
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.BLUE + "����");
					p.setDisplayName(ChatColor.BLUE + "[[����]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.BLUE + "[[����]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.BLUE + "â����");
					p.setDisplayName(ChatColor.BLUE + "[[[â����]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.BLUE + "[[[â����]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.BLUE + "�����");
					p.setDisplayName(ChatColor.BLUE + "[�����]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.BLUE + "[�����]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ����, ����
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.AQUA + "������");
					p.setDisplayName(ChatColor.AQUA + "[[������]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[������]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.AQUA + "������");
					p.setDisplayName(ChatColor.AQUA + "[[[������]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[[������]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.AQUA + "������");
					p.setDisplayName(ChatColor.AQUA + "[������]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[������]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ��, ����
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.GOLD + "����");
					p.setDisplayName(ChatColor.GOLD + "[[����]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GOLD + "[[����]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.GOLD + "�밡");
					p.setDisplayName(ChatColor.GOLD + "[[[�밡]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GOLD + "[[[�밡]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.GOLD + "�丮��");
					p.setDisplayName(ChatColor.GOLD + "[�丮��]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.GOLD + "[�丮��]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ��
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.BLUE + "����");
					p.setDisplayName(ChatColor.BLUE + "[[����]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.BLUE + "[[����]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.BLUE + "Ȳ��");
					p.setDisplayName(ChatColor.BLUE + "[[[Ȳ��]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.BLUE + "[[[Ȳ��]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.BLUE + "����");
					p.setDisplayName(ChatColor.BLUE + "[����]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.BLUE + "[����]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ��, �ٶ�
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.DARK_AQUA + "���ְ�");
					p.setDisplayName(ChatColor.DARK_AQUA + "[[���ְ�]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_AQUA + "[[���ְ�]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.DARK_AQUA + "����");
					p.setDisplayName(ChatColor.DARK_AQUA + "[[[����]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_AQUA + "[[[����]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.DARK_AQUA + "�ٴٱ��");
					p.setDisplayName(ChatColor.DARK_AQUA + "[�ٴٱ��]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_AQUA + "[�ٴٱ��]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ����
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.AQUA + "���⼳");
					p.setDisplayName(ChatColor.AQUA + "[[���⼳]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[���⼳]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.AQUA + "��ȭ");
					p.setDisplayName(ChatColor.AQUA + "[[[��ȭ]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[[��ȭ]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.AQUA + "������");
					p.setDisplayName(ChatColor.AQUA + "[������]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[������]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// �ٍ�, ��
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.AQUA + "����");
					p.setDisplayName(ChatColor.AQUA + "[[����]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[����]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.AQUA + "�ѷ�");
					p.setDisplayName(ChatColor.AQUA + "[[[�ѷ�]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[[�ѷ�]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.AQUA + "���ò�");
					p.setDisplayName(ChatColor.AQUA + "[���ò�]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[���ò�]" + p.getPlayerListName());
				}
				break;
			}
			if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
				p.addScoreboardTag(ChatColor.AQUA + "Swell");
				p.setDisplayName(ChatColor.AQUA + "[[Swell]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.AQUA + "[[Swell]]" + p.getPlayerListName());
			} else if (Proficiency.getpro(p) >= 2) {
				p.addScoreboardTag(ChatColor.AQUA + "Gatsby");
				p.setDisplayName(ChatColor.AQUA + "[[[Gatsby]]]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.AQUA + "[[[Gatsby]]]" + p.getPlayerListName());
			} else {
				p.addScoreboardTag(ChatColor.AQUA + "Angler");
				p.setDisplayName(ChatColor.AQUA + "[Angler]" + p.getDisplayName());
				p.setPlayerListName(ChatColor.AQUA + "[Angler]" + p.getPlayerListName());
			}
			break;
		}
		case 23: {
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ����,��
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.DARK_GREEN + "������");
					p.setDisplayName(ChatColor.DARK_GREEN + "[[������]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GREEN + "[[������]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.DARK_GREEN + "�ڿ���");
					p.setDisplayName(ChatColor.DARK_GREEN + "[[[�ڿ���]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GREEN + "[[[�ڿ���]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.DARK_GREEN + "������");
					p.setDisplayName(ChatColor.DARK_GREEN + "[������]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GREEN + "[������]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ���, �ٶ�
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.DARK_GRAY + "�ϻ���");
					p.setDisplayName(ChatColor.DARK_GRAY + "[[�ϻ���]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GRAY + "[[�ϻ���]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.DARK_GRAY + "�Ͽ�");
					p.setDisplayName(ChatColor.DARK_GRAY + "[[[�Ͽ�]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GRAY + "[[[�Ͽ�]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.DARK_GRAY + "�ܰ˻�");
					p.setDisplayName(ChatColor.DARK_GRAY + "[�ܰ˻�]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.DARK_GRAY + "[�ܰ˻�]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ��,����
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.AQUA + "������");
					p.setDisplayName(ChatColor.AQUA + "[[������]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[������]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.AQUA + "������");
					p.setDisplayName(ChatColor.AQUA + "[[[������]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[[[������]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.AQUA + "����");
					p.setDisplayName(ChatColor.AQUA + "[����]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.AQUA + "[����]" + p.getPlayerListName());
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
			if (p.getLocale().equalsIgnoreCase("ko_kr")) {// ����, ����
				if (Proficiency.getpro(p) >= 1 && Proficiency.getpro(p) < 2) {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "���Ⱑ");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[���Ⱑ]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[���Ⱑ]]" + p.getPlayerListName());
				} else if (Proficiency.getpro(p) >= 2) {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "����");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[[[����]]]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[[[����]]]" + p.getPlayerListName());
				} else {
					p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "������");
					p.setDisplayName(ChatColor.LIGHT_PURPLE + "[������]" + p.getDisplayName());
					p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[������]" + p.getPlayerListName());
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

			itemset(ChatColor.BLUE + "����: ����", Material.IRON_ORE, 0, 1, Arrays.asList("���� �Ǵ� �������ݿ� Ưȭ"), 1, inv);
			itemset(ChatColor.YELLOW + "����: ���", Material.EMERALD_ORE, 0, 1, Arrays.asList("���Ÿ� ���ݿ� Ưȭ"), 2, inv);
			itemset(ChatColor.GRAY + "����: ������", Material.COAL_ORE, 0, 1, Arrays.asList("���� ü��, ���Ӱ��ݿ� Ưȭ"), 3, inv);
			itemset(ChatColor.LIGHT_PURPLE + "����: ������", Material.GOLD_ORE, 0, 1, Arrays.asList("���߰��ݿ� Ưȭ"), 4, inv);
			itemset(ChatColor.GOLD + "����: ������", Material.REDSTONE_ORE, 0, 1, Arrays.asList("���п� Ưȭ"), 5, inv);
			itemset(ChatColor.AQUA + "����: �ػ�", Material.LAPIS_ORE, 0, 1,
					Arrays.asList("������ �ٴٰ��� �̷ο� ȿ���� ����ϴ�", "���� ���ݿ� Ưȭ"), 6, inv);
			itemset(ChatColor.DARK_GREEN + "����: ������", Material.COPPER_ORE, 0, 1, Arrays.asList("���� �������� ��ɼ�"), 7, inv);

			itemset(ChatColor.BLUE + "���ұ�: ���", Material.CREEPER_BANNER_PATTERN, 0, 1,
					Arrays.asList("���� ���� ����� �����ϰ� �ֽ��ϴ�", "���ݿ��ұ����� �������� �����ϴ�"), 9, inv);
			itemset(ChatColor.RED + "���ұ�: ����", Material.CREEPER_BANNER_PATTERN, 0, 1,
					Arrays.asList("����,������ ������ �߰����ظ� �ݴϴ�", "Ÿ���ұ����� ������ �����ϴ�"), 18, inv);
			itemset(ChatColor.GREEN + "���ұ�: ����", Material.CREEPER_BANNER_PATTERN, 0, 1,
					Arrays.asList("Ÿ���ұ����� ���б���� �����ϴ�", "�������� ������ Ÿ���ұ����� �������� �����ϴ�"), 27, inv);
			itemset(ChatColor.DARK_AQUA + "���ұ�: ����", Material.CREEPER_BANNER_PATTERN, 0, 1, Arrays
					.asList("����, ����� ��ų�� �����ϰ� �ֽ��ϴ�", "Ÿ���ұ����� �������� �����ϴ�", "��Ƽ�� ������, �������� �����մϴ�", "(����ġ�� ����ġ ������ ����մϴ�)"),
					36, inv);

			itemset(ChatColor.BLUE + "�˻�", Material.IRON_SWORD, 0, 1, Arrays.asList("��õ ���:", "��", "", "����: 3.5/5",
					"���: 4/5", "����: 3/5", "����: 0/5", "����: 5/5", "��Ÿ�: 2.5/5", "�⵿: 3.5/5", "���̵�: ����"), 10, inv);
			itemset(ChatColor.BLUE + "��ɲ�", Material.IRON_AXE, 0, 1, Arrays.asList("��õ ���:", "����", "", "����: 5/5",
					"���: 2.5/5", "����: 2.5/5", "����: 1/5", "����: 1/5", "��Ÿ�: 1/5", "�⵿: 5/5", "���̵�: �����"), 19, inv);
			itemset(ChatColor.BLUE + "����", Material.CHAIN, 0, 1, Arrays.asList("��õ ���:", "��", "", "����: 3/5",
					"���: 2.5/5", "����: 4/5", "����: 1/5", "����: 4/5", "��Ÿ�: 2/5", "�⵿: 2.5/5", "���̵�: ����"), 28, inv);
			itemset(ChatColor.BLUE + "�����", Material.SHIELD, 0, 1, Arrays.asList("��õ ���:", "��:����, ����:���� ", "",
					"����: 1.5/5", "���: 5/5", "����: 3/5", "����: 4.5/5", "����: 2/5", "��Ÿ�: 2/5", "�⵿: 2/5", "���̵�: ����"), 37,
					inv);

			itemset(ChatColor.BLUE + "�ü�", Material.BOW, 0, 1, Arrays.asList("��õ ���:", "Ȱ", "", "����: 4/5", "���: 3.5/5",
					"����: 2.5/5", "����: 0/5", "����: 2.5/5", "��Ÿ�: 3.5/5", "�⵿: 3.5/5", "���̵�: ����"), 11, inv);
			itemset(ChatColor.BLUE + "���ݼ�", Material.CROSSBOW, 0, 1, Arrays.asList("��õ ���:", "���", "", "����: 5/5",
					"���: 0/5", "����: 1.5/5", "����: 1/5", "����: 2/5", "��Ÿ�: 5/5", "�⵿: 2.5/5", "���̵�: �����"), 20, inv);
			itemset(ChatColor.BLUE + "���Ҽ���", Material.FIREWORK_ROCKET, 0, 1, Arrays.asList("��õ ���:", "Ȱ", "", "����: 3/5",
					"���: 2/5", "����: 4/5", "����: 1/5", "����: 3.5/5", "��Ÿ�: 4/5", "�⵿: 2/5", "���̵�: ����"), 29, inv);
			itemset(ChatColor.BLUE + "�Ǳ�", Material.TIPPED_ARROW, 0, 1, Arrays.asList("��õ ���:", "���", "", "����: 2/5",
					"���: 3.5/5", "����: 3.5/5", "����: 5/5", "����: 2.5/5", "��Ÿ�: 3.5/5", "�⵿: 2/5", "���̵�: �����"), 38, inv);

			itemset(ChatColor.BLUE + "�ǻ�", Material.IRON_HELMET, 0, 1, Arrays.asList("��õ ���:", "��� ��Ŭ", "", "����: 3/5",
					"���: 5/5", "����: 2.5/5", "����: 0/5", "����: 2/5", "��Ÿ�: 1.5/5", "�⵿: 3/5", "���̵�: �����"), 12, inv);
			itemset(ChatColor.BLUE + "���û�", Material.LEAD, 0, 1, Arrays.asList("��õ ���:", "��� ��Ŭ", "����: 4/5", "���: 1/5",
					"����: 3/5", "����: 0/5", "����: 3/5", "��Ÿ�: 4/5", "�⵿: 1/5", "���̵�: ����"), 21, inv);
			itemset(ChatColor.BLUE + "������", Material.GOLDEN_CHESTPLATE, 0, 1, Arrays.asList("��õ ���:", "��� ��Ŭ", "",
					"����: 3/5", "���: 4/5", "����: 4.5/5", "����: 0/5", "����: 2.5/5", "��Ÿ�: 2.5/5", "�⵿: 2/5", "���̵�: ����"), 30,
					inv);
			itemset(ChatColor.BLUE + "����", Material.SOUL_CAMPFIRE, 0, 1,
					Arrays.asList("��õ ���:", "��� ��Ŭ", "", "����: 2.5/5", "���: 2.5/5", "����: 3.5/5", "����: 4/5", "����: 3.5/5",
							"��Ÿ�: 2.5/5", "�⵿: 2.5/5", "���̵�: ����"),
					39, inv);

			itemset(ChatColor.BLUE + "ȭ������", Material.FIRE_CHARGE, 0, 1, Arrays.asList("��õ ���:", "�ϵ�", "", "����: 4/5",
					"���: 3.5/5", "����: 2.5/5", "����: 0.5/5", "����: 3/5", "��Ÿ�: 3.5/5", "�⵿: 2/5", "���̵�: ����"), 13, inv);
			itemset(ChatColor.BLUE + "ȯ����", Material.JACK_O_LANTERN, 0, 1, Arrays.asList("��õ ���:", "�ϵ�", "",
					"����: 4.5/5", "���: 1.5/5", "����: 2.5/5", "����: 0/5", "����: 3/5", "��Ÿ�: 2/5", "�⵿: 4/5", "���̵�: �����"), 22,
					inv);
			itemset(ChatColor.BLUE + "������", Material.FLOWERING_AZALEA_LEAVES, 0, 1, Arrays.asList("��õ ���:", "��", "",
					"����: 3.5/5", "���: 2.5/5", "����: 4.5/5", "����: 1/5", "����: 3/5", "��Ÿ�: 3/5", "�⵿: 1/5", "���̵�: ����"), 31,
					inv);
			itemset(ChatColor.BLUE + "������", Material.JUKEBOX, 0, 1, Arrays.asList("��õ ���:", "�ϵ�", "", "����: 3/5",
					"���: 1/5", "����: 3/5", "����: 4/5", "����: 3.5/5", "��Ÿ�: 4/5", "�⵿: 2/5", "���̵�: ����"), 40, inv);

			itemset(ChatColor.BLUE + "ȭ����", Material.POTION, 0, 1, Arrays.asList("��õ ���:", "���", "", "����: 3/5",
					"���: 4.5/5", "����: 2.5/5", "����: 1/5", "����: 3.5/5", "��Ÿ�: 1.5/5", "�⵿: 3.5/5", "���̵�: ����"), 14, inv);
			itemset(ChatColor.BLUE + "�����", Material.BEACON, 0, 1, Arrays.asList("��õ ���:", "���", "", "����: 4.5/5",
					"���: 2/5", "����: 2.5/5", "����: 1/5", "����: 3/5", "��Ÿ�: 5/5", "�⵿: 1.5/5", "���̵�: ����"), 23, inv);
			itemset(ChatColor.BLUE + "������", Material.IRON_PICKAXE, 0, 1, Arrays.asList("��õ ���:", "���", "", "����: 3/5",
					"���: 4/5", "����: 5/5", "����: 1/5", "����: 2.5/5", "��Ÿ�: 2/5", "�⵿: 2.5/5", "���̵�: ����"), 32, inv);
			itemset(ChatColor.BLUE + "�丮��", Material.COOKED_BEEF, 0, 1, Arrays.asList("��õ ���:", "��", "", "����: 2.5/5",
					"���: 3/5", "����: 3/5", "����: 4/5", "����: 3.5/5", "��Ÿ�: 3/5", "�⵿: 1/5", "���̵�: ����"), 41, inv);

			itemset(ChatColor.BLUE + "�ٴٱ��", Material.TRIDENT, 0, 1, Arrays.asList("��õ ���:", "����â, ���� ", "",
					"����: 3.5/5", "���: 4/5", "����: 2/5", "����: 0/5", "����: 4/5", "��Ÿ�: 3.5/5", "�⵿: 3.8/5", "���̵�: �����"), 15,
					inv);
			itemset(ChatColor.BLUE + "����", Material.HEART_OF_THE_SEA, 0, 1, Arrays.asList("��õ ���:", "����â", "",
					"����: 4.5/5", "���: 2.5/5", "����: 2.5/5", "����: 1/5", "����: 3.5/5", "��Ÿ�: 4/5", "�⵿: 2/5", "���̵�: ����"),
					24, inv);
			itemset(ChatColor.BLUE + "������", Material.PACKED_ICE, 0, 1,
					Arrays.asList("��õ ���:", "���(������Ӹ� ����)", "", "����: 3.5/5", "���: 2.5/5", "����: 4.5/5", "����: 0/5",
							"����: 2.5/5", "��Ÿ�: 2.5/5", "�⵿: 1.5/5", "���̵�: �����"),
					33, inv);
			itemset(ChatColor.BLUE + "���ò�", Material.FISHING_ROD, 0, 1, Arrays.asList("��õ ���:", "���ô�", "", "����: 2.5/5",
					"���: 3/5", "����: 4/5", "����: 4/5", "����: 2.5/5", "��Ÿ�: 3.5/5", "�⵿: 2/5", "���̵�: ����"), 42, inv);

			itemset(ChatColor.BLUE + "������", Material.CRIMSON_ROOTS, 0, 1, Arrays.asList("��õ ���:", "��", "", "����: 4/5",
					"���: 3/5", "����: 3/5", "����: 0/5", "����: 3.5/5", "��Ÿ�: 3.5/5", "�⵿: 2.5/5", "���̵�: ����"), 16, inv);
			itemset(ChatColor.BLUE + "�ܰ˻�", Material.SHEARS, 0, 1, Arrays.asList("��õ ���:", "�ܰ�", "", "����: 4.75/5",
					"���: 1.5/5", "����: 2/5", "����: 0/5", "����: 3/5", "��Ÿ�: 1.5/5", "�⵿: 4.5/5", "���̵�: �����"), 25, inv);
			itemset(ChatColor.BLUE + "��������Ʈ", Material.WITHER_ROSE, 0, 1, Arrays.asList("��õ ���:", "����", "", "����: 3/5",
					"���: 3/5", "����: 4/5", "����: 2/5", "����: 3/5", "��Ÿ�: 4/5", "�⵿: 5/5", "���̵�: ����"), 34, inv);
			itemset(ChatColor.BLUE + "�εμ���", Material.TOTEM_OF_UNDYING, 0, 1,
					Arrays.asList("��õ ���:", "����", "", "����: 2.5/5", "���: 3.5/5", "����: 1.5/5", "����: 4.5/5", "����: 2.5/5",
							"��Ÿ�: 2.5/5", "�⵿: 2/5", "���̵�: �����"),
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
					Arrays.asList("Has damage reduction skill", "Low AttackDamage Than Nuker"), 9, inv);
			itemset(ChatColor.RED + "Role: Nuker", Material.CREEPER_BANNER_PATTERN, 0, 1,
					Arrays.asList("Damage More to Boss & Leader Mob", "Low Armor Than Other Roles"), 18, inv);
			itemset(ChatColor.GREEN + "Role: Holder", Material.CREEPER_BANNER_PATTERN, 0, 1,
					Arrays.asList("Has More Holding Skills Than Other Roles", "Low AttackDamage Than Other Roles",
							"Except Supporter"),
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
			itemset(ChatColor.BLUE + "Broiler", Material.CHAIN, 0, 1,
					Arrays.asList("Recommended equipment:", "Sword", "", "Attack: 3/5", "Defence: 2.5/5",
							"Control: 4/5", "Support: 1/5", "Area: 4/5", "Range: 2/5", "Mobility: 2/5",
							"Difficulty: Normal"),
					28, inv);
			itemset(ChatColor.BLUE + "Paladin", Material.SHIELD, 0, 1,
					Arrays.asList("Recommended equipment:", "Axe(main) & Shield(off)", "", "Attack: 1.5/5",
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
			itemset(ChatColor.BLUE + "Launcher", Material.FIREWORK_ROCKET, 0, 1,
					Arrays.asList("Recommended equipment:", "Bow", "", "Attack: 3/5", "Defence: 2/5", "Control: 4/5",
							"Support: 1/5", "Area: 3.5/5", "Range: 4/5", "Mobility: 2/5", "Difficulty: Normal"),
					29, inv);
			itemset(ChatColor.BLUE + "Medic", Material.TIPPED_ARROW, 0, 1,
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
			itemset(ChatColor.BLUE + "Wrestler", Material.GOLDEN_CHESTPLATE, 0, 1,
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
			itemset(ChatColor.BLUE + "Gardener", Material.FLOWERING_AZALEA_LEAVES, 0, 1,
					Arrays.asList("Recommended equipment:", "Shovel", "", "Attack: 3.5/5", "Defence: 2.5/5",
							"Control: 4.5/5", "Support: 1/5", "Area: 3.5/5", "Range: 3/5", "Mobility: 1/5",
							"Difficulty: Easy"),
					31, inv);
			itemset(ChatColor.BLUE + "Musician", Material.JUKEBOX, 0, 1,
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
			itemset(ChatColor.BLUE + "Engineer", Material.IRON_PICKAXE, 0, 1,
					Arrays.asList("Recommended equipment:", "Pickaxe", "", "Attack: 3/5", "Defence: 4/5",
							"Control: 5/5", "Support: 1/5", "Area: 2.5/5", "Range: 2/5", "Mobility: 2.5/5",
							"Difficulty: Normal"),
					32, inv);
			itemset(ChatColor.BLUE + "Cook", Material.COOKED_BEEF, 0, 1,
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
			itemset(ChatColor.BLUE + "Daggerist", Material.SHEARS, 0, 1,
					Arrays.asList("Recommended equipment:", "Dagger in both Hands", "", "Attack: 4.75/5",
							"Defence: 1.5/5", "Control: 2/5", "Support: 0/5", "Area: 3/5", "Range: 1.5/5",
							"Mobility: 4.5/5", "Difficulty: Hard"),
					25, inv);
			itemset(ChatColor.BLUE + "Witherist", Material.WITHER_ROSE, 0, 1,
					Arrays.asList("Recommended equipment:", "Hoe", "", "Attack: 3/5", "Defence: 3/5", "Control: 4/5",
							"Support: 2/5", "Area: 3/5", "Range: 4/5", "Mobility: 5/5", "Difficulty: Normal"),
					34, inv);
			itemset(ChatColor.BLUE + "WitchDoctor", Material.TOTEM_OF_UNDYING, 0, 1,
					Arrays.asList("Recommended equipment:", "Hoe", "", "Attack: 2.5/5", "Defence: 3.5/5",
							"Control: 1.5/5", "Support: 4.5/5", "Area: 2.5/5", "Range: 2.5/5", "Mobility: 2/5",
							"Difficulty: Hard"),
					43, inv);

		}
		p.openInventory(inv);
	}

}
