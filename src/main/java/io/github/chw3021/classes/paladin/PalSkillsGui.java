package io.github.chw3021.classes.paladin;



import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class PalSkillsGui{
	

	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new ItemStack(ID);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		Lore.forEach(l -> {
			l=ChatColor.RESET+l;
		});
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	public void itemset(String display, ItemStack is, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = is;
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void PalSkillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		PalSkillsData psd = new PalSkillsData(PalSkillsData.loadData(path +"/plugins/RPGskills/PalSkillsData.data"));
		Inventory Palskillsinv = Bukkit.createInventory(null, 54, "Palskills");
		Obtained.itemset(p, Palskillsinv);
		

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("����", Material.WOODEN_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Thrust.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","���� + �չٲٱ�","",ChatColor.BOLD+" X (0.5D + "+BigDecimal.valueOf(psd.Thrust.getOrDefault(p.getUniqueId(),0)*0.485).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 0, Palskillsinv);
			itemset("���", Material.STONE_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Restraint.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","���� + ��ũ���� + �չٲٱ�","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(psd.Restraint.getOrDefault(p.getUniqueId(),0)*0.69).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 1, Palskillsinv);
			itemset("����", Material.IRON_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Judgement.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","��Ŭ�� + ��ũ����",
					"��Ƽ������ ���ݷ��� �����մϴ�",ChatColor.BOLD+" X "+BigDecimal.valueOf(1+psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.01).setScale(2, RoundingMode.HALF_EVEN),
					"��Ƽ���鿡�� ������,�� ȿ���� �ݴϴ� ("+(int)Math.floor(psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.1)+"Lv)"
					,"","������ ������ �����Ƚ��ϴ�",ChatColor.BOLD+" X (0.8D + "+BigDecimal.valueOf(psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 2, Palskillsinv);
			itemset("¡��", Material.GOLDEN_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Punish.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","���� + Hit","",ChatColor.BOLD+" X (0.5D + "+BigDecimal.valueOf(psd.Punish.getOrDefault(p.getUniqueId(),0)*1.5).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 3, Palskillsinv);
			itemset("�ݷ�", Material.DIAMOND_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Encourge.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","�չٲٱ�", "Master Lv.50"), 4, Palskillsinv);
			itemset("�⵵", Material.BOOKSHELF, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Pray.getOrDefault(p.getUniqueId(),0),"","�չٲٱ� + ��ũ���� (Master Lv.10)"), 5, Palskillsinv);
			itemset("��ȣ", Material.BOOK, 0, 1, Arrays.asList("���⵵�� ��Ƽ������ �޴����ظ� ���� ���ҽ�ŵ�ϴ� (�нú�)"), 6, Palskillsinv);
			itemset("�ž�", Material.WRITABLE_BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Protection.getOrDefault(p.getUniqueId(),0),"","���ݷ��� �����մϴ�","���� �迭 ���׷��� �����մϴ�","",ChatColor.BOLD+" X 1.3 + "+BigDecimal.valueOf(psd.Protection.getOrDefault(p.getUniqueId(),0)*0.4383).setScale(2, RoundingMode.HALF_EVEN)), 7, Palskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("�ż��Ѱ�Ÿ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 9, Palskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 10, Palskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 11, Palskillsinv);
				itemset("��ȭ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 12, Palskillsinv);
				itemset("��â(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 13, Palskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 14, Palskillsinv);
				itemset("��ȣ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 16, Palskillsinv);
				itemset("������ ����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 17, Palskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("�Ű�", Material.SHIELD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� �Ű��� ����մϴ�","(���ط��� ���� ������ ����մϴ�)","",ChatColor.BOLD+" X (0.75D + "+BigDecimal.valueOf(psd.Thrust.getOrDefault(p.getUniqueId(),0)*0.485).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, Palskillsinv);
				itemset("����", Material.DAYLIGHT_DETECTOR, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ������ ����մϴ�","(���ط��� ��� ������ ����մϴ�)","",ChatColor.BOLD+" X (0.9D + "+BigDecimal.valueOf(psd.Restraint.getOrDefault(p.getUniqueId(),0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")"), 10, Palskillsinv);
				itemset("����", Material.MILK_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ������ ����մϴ�","(���ط��� ���� ������ ����մϴ�)","",ChatColor.BOLD+" X (0.8D + "+BigDecimal.valueOf(psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, Palskillsinv);
				itemset("��ȭ", Material.DIAMOND_AXE, 0, 1, Arrays.asList("���� ������ŵ�ϴ�"), 12, Palskillsinv);
				itemset("��â", Material.NOTE_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ��â�� ����մϴ�","(���ط��� �ݷ� ������ ����մϴ�)","",ChatColor.BOLD+"3 X (0.5D + "+BigDecimal.valueOf(psd.Encourge.getOrDefault(p.getUniqueId(),0)*0.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 13, Palskillsinv);
				itemset("�ູ", Material.ENCHANTING_TABLE, 0, 1, Arrays.asList("��Ƽ������ �������·� ����ϴ�","(���ӽð��� �⵵ ������ ����մϴ�)","",ChatColor.BOLD+""+BigDecimal.valueOf(psd.Pray.getOrDefault(p.getUniqueId(),0)*0.2).setScale(1, RoundingMode.HALF_EVEN)+"��"), 14, Palskillsinv);
				itemset("��ȣ", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�", "���и� ����ִ� ���� �޴� ��� ���ظ� �����ϴ�", "��ȣ�� ���ذ��ҷ��� 80%�� �����մϴ�"), 16, Palskillsinv);
				itemset("������ ����", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","��ũ���� + �����۴�����","",ChatColor.BOLD+"10 X 1.5D"), 17, Palskillsinv);

				itemset("��¡(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 18, Palskillsinv);
				itemset("���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 19, Palskillsinv);
				itemset("�׸���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 20, Palskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 22, Palskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 23, Palskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 25, Palskillsinv);
				itemset("��ȸ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 26, Palskillsinv);
			}
			else {
				itemset("�Ű�", Material.SHIELD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� �Ű��� ����մϴ�","(���ط��� ���� ������ ����մϴ�)","",ChatColor.BOLD+" X (0.75D + "+BigDecimal.valueOf(psd.Thrust.getOrDefault(p.getUniqueId(),0)*0.485).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, Palskillsinv);
				itemset("����", Material.DAYLIGHT_DETECTOR, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ������ ����մϴ�","(���ط��� ��� ������ ����մϴ�)","",ChatColor.BOLD+" X (0.9D + "+BigDecimal.valueOf(psd.Restraint.getOrDefault(p.getUniqueId(),0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")"), 10, Palskillsinv);
				itemset("����", Material.MILK_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ������ ����մϴ�","(���ط��� ���� ������ ����մϴ�)","",ChatColor.BOLD+" X (0.8D + "+BigDecimal.valueOf(psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, Palskillsinv);
				itemset("��ȭ", Material.DIAMOND_AXE, 0, 1, Arrays.asList("���� ������ŵ�ϴ�"), 12, Palskillsinv);
				itemset("��â", Material.NOTE_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ��â�� ����մϴ�","(���ط��� �ݷ� ������ ����մϴ�)","",ChatColor.BOLD+"3 X (0.5D + "+BigDecimal.valueOf(psd.Encourge.getOrDefault(p.getUniqueId(),0)*0.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 13, Palskillsinv);
				itemset("�ູ", Material.ENCHANTING_TABLE, 0, 1, Arrays.asList("��Ƽ������ �������·� ����ϴ�","(���ӽð��� �⵵ ������ ����մϴ�)","",ChatColor.BOLD+""+BigDecimal.valueOf(psd.Pray.getOrDefault(p.getUniqueId(),0)*0.2).setScale(1, RoundingMode.HALF_EVEN)+"��"), 14, Palskillsinv);
				itemset("��ȣ", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�", "���и� ����ִ� ���� �޴� ��� ���ظ� �����ϴ�", "��ȣ�� ���ذ��ҷ��� 80%�� �����մϴ�"), 16, Palskillsinv);
				itemset("������ ����", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","��ũ���� + �����۴�����","",ChatColor.BOLD+"10 X 1.5D"), 17, Palskillsinv);

				itemset("��¡", Material.LIGHTNING_ROD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ��¡�� ����մϴ�","(���ط��� ���� ������ ����մϴ�)","",ChatColor.BOLD+"4 X (0.4D + "+BigDecimal.valueOf(psd.Thrust.getOrDefault(p.getUniqueId(),0)*0.455).setScale(2, RoundingMode.HALF_EVEN)+")"), 18, Palskillsinv);
				itemset("���", Material.BUBBLE_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ����� ����մϴ�","(���ط��� ��� ������ ����մϴ�)","",ChatColor.BOLD+" X (1.5D + "+BigDecimal.valueOf(psd.Restraint.getOrDefault(p.getUniqueId(),0)*1.68).setScale(2, RoundingMode.HALF_EVEN)+")"), 19, Palskillsinv);
				itemset("�׸���", Material.TRIDENT, 0, 1, Arrays.asList("���Է½� �׸����� ��ȯ�մϴ�", "�׸������� �����ų� ���ȯ�ϸ�", "������ �ִ� �׸����� ������ϴ�","", "�׸��� ž�½� ¡���� ��ȭ�˴ϴ�","([����+��Ŭ��]���� Ŀ�ǵ� ����)","",
						ChatColor.UNDERLINE+"[���� �迭]","�׸��� ž�µ��� ������ �ֺ������� ���ظ� �ݴϴ�", "(���ط��� ���� ������ ����մϴ�)","",ChatColor.BOLD+" X (1.3D + "+BigDecimal.valueOf(psd.Judgement.getOrDefault(p.getUniqueId(),0)*1.3).setScale(2, RoundingMode.HALF_EVEN)+" X ����������)"), 20, Palskillsinv);
				itemset("����", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� �Ű��� ����մϴ�","(���ط��� �ݷ� ������ ����մϴ�)","",ChatColor.BOLD+"10 X (0.55D + "+BigDecimal.valueOf(psd.Encourge.getOrDefault(p.getUniqueId(),0)*0.68).setScale(2, RoundingMode.HALF_EVEN)+")"), 22, Palskillsinv);
				itemset("����", Material.BUBBLE_CORAL, 0, 1, Arrays.asList("��Ƽ������ ��⸦ ä���ݴϴ�"), 23, Palskillsinv);
				itemset("����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�","������ ���� ���ð��� �����մϴ�"), 25, Palskillsinv);
				itemset("��ȸ", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","�޸��� + �����۴�����","",ChatColor.BOLD+" X 15.6D"), 26, Palskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Palskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+psd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"), 35, Palskillsinv);

		}
		else {
			itemset("Thrust", Material.WOODEN_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Thrust.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Earth]","Blocking + SwapHand","",ChatColor.BOLD+" X (0.5D + "+BigDecimal.valueOf(psd.Thrust.getOrDefault(p.getUniqueId(),0)*0.485).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 0, Palskillsinv);
			itemset("Restraint", Material.STONE_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Restraint.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Earth]","Blocking + Sneaking + SwapHand","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(psd.Restraint.getOrDefault(p.getUniqueId(),0)*0.69).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 1, Palskillsinv);
			itemset("Judgement", Material.IRON_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Judgement.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Lightning]","LeftClick + Sneaking",
					"Increase Party's Damage",ChatColor.BOLD+" X "+BigDecimal.valueOf(1+psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.01).setScale(2, RoundingMode.HALF_EVEN),
					"Give Party Attack Speed, Strength Effects ("+(int)Math.floor(psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.1)+"Lv)"
					,"","Strike Lightning To Enemies",ChatColor.BOLD+" X (0.8D + "+BigDecimal.valueOf(psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 2, Palskillsinv);
			itemset("Punish", Material.GOLDEN_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Punish.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Lightning]","Jump + Hit","",ChatColor.BOLD+" X (0.5D + "+BigDecimal.valueOf(psd.Punish.getOrDefault(p.getUniqueId(),0)*1.5).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 3, Palskillsinv);
			itemset("Encourge", Material.DIAMOND_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Encourge.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Earth]","SwapHand", "Master Lv.50"), 4, Palskillsinv);
			itemset("Pray", Material.BOOKSHELF, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Pray.getOrDefault(p.getUniqueId(),0),"","SwapHand + Sneaking (Master Lv.10)"), 5, Palskillsinv);
			itemset("Protection", Material.BOOK, 0, 1, Arrays.asList("Reduces damage to Party by 50% while Blocking (Passive)"), 6, Palskillsinv);
			itemset("Faith", Material.WRITABLE_BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Protection.getOrDefault(p.getUniqueId(),0),"","Increases Damage","Increases Lightning Resistance","",ChatColor.BOLD+" X 1.3 + "+BigDecimal.valueOf(psd.Protection.getOrDefault(p.getUniqueId(),0)*0.4383).setScale(2, RoundingMode.HALF_EVEN)), 7, Palskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("HolySmash(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Palskillsinv);
				itemset("Illumination(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Palskillsinv);
				itemset("Asperges(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Palskillsinv);
				itemset("Sanctification(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Palskillsinv);
				itemset("Aria(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Palskillsinv);
				itemset("Bless(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Palskillsinv);
				itemset("Tutelary(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Palskillsinv);
				itemset("Last judgment(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Palskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("HolySmash", Material.SHIELD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use HolySmash When You Use Once More","(Damage Affected By Thrust)","",ChatColor.BOLD+" X (0.75D + "+BigDecimal.valueOf(psd.Thrust.getOrDefault(p.getUniqueId(),0)*0.485).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, Palskillsinv);
				itemset("Illumination", Material.DAYLIGHT_DETECTOR, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Lightning]","Use Illumination When Use Once More","(Damage Affected By Restraint)","",ChatColor.BOLD+" X (0.9D + "+BigDecimal.valueOf(psd.Restraint.getOrDefault(p.getUniqueId(),0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")"), 10, Palskillsinv);
				itemset("Asperges", Material.MILK_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Lightning]","Use Asperges When Use Once More","(Damage Affected By Judgement)","",ChatColor.BOLD+" X (0.8D + "+BigDecimal.valueOf(psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, Palskillsinv);
				itemset("Sanctification", Material.DIAMOND_AXE, 0, 1, Arrays.asList("Stun Hit Enemy"), 12, Palskillsinv);
				itemset("Aria", Material.NOTE_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Aria When Use Once More","(Damage Affected By Encourge)","",ChatColor.BOLD+"3 X (0.5D + "+BigDecimal.valueOf(psd.Encourge.getOrDefault(p.getUniqueId(),0)*0.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 13, Palskillsinv);
				itemset("Bless", Material.ENCHANTING_TABLE, 0, 1, Arrays.asList("Sets Party's Armor Max","(Duration Affected By Pray)","",ChatColor.BOLD+""+BigDecimal.valueOf(psd.Pray.getOrDefault(p.getUniqueId(),0)*0.2).setScale(1, RoundingMode.HALF_EVEN)+"s"), 14, Palskillsinv);
				itemset("Tutelary", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor", "Reduces 100% Damage While Raising Shield", "Protection will Reduce 80% of Damage"), 16, Palskillsinv);
				itemset("Last judgment", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Lightning]","Sneaking + ThrowItem","",ChatColor.BOLD+"10 X 1.5D"), 17, Palskillsinv);

				itemset("HolyPile(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, Palskillsinv);
				itemset("Doom(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, Palskillsinv);
				itemset("Griffon(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, Palskillsinv);
				itemset("Sanctuary(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, Palskillsinv);
				itemset("Grace(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, Palskillsinv);
				itemset("Salvation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Palskillsinv);
				itemset("Penance(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Palskillsinv);
			}
			else {
				itemset("HolySmash", Material.SHIELD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use HolySmash When You Use Once More","(Damage Affected By Thrust)","",ChatColor.BOLD+" X (0.75D + "+BigDecimal.valueOf(psd.Thrust.getOrDefault(p.getUniqueId(),0)*0.485).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, Palskillsinv);
				itemset("Illumination", Material.DAYLIGHT_DETECTOR, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Lightning]","Use Illumination When Use Once More","(Damage Affected By Restraint)","",ChatColor.BOLD+" X (0.9D + "+BigDecimal.valueOf(psd.Restraint.getOrDefault(p.getUniqueId(),0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")"), 10, Palskillsinv);
				itemset("Asperges", Material.MILK_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Lightning]","Use Asperges When Use Once More","(Damage Affected By Judgement)","",ChatColor.BOLD+" X (0.8D + "+BigDecimal.valueOf(psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, Palskillsinv);
				itemset("Sanctification", Material.DIAMOND_AXE, 0, 1, Arrays.asList("Stun Hit Enemy"), 12, Palskillsinv);
				itemset("Aria", Material.NOTE_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Aria When Use Once More","(Damage Affected By Encourge)","",ChatColor.BOLD+"3 X (0.5D + "+BigDecimal.valueOf(psd.Encourge.getOrDefault(p.getUniqueId(),0)*0.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 13, Palskillsinv);
				itemset("Bless", Material.ENCHANTING_TABLE, 0, 1, Arrays.asList("Sets Party's Armor Max","(Duration Affected By Pray)","",ChatColor.BOLD+""+BigDecimal.valueOf(psd.Pray.getOrDefault(p.getUniqueId(),0)*0.2).setScale(1, RoundingMode.HALF_EVEN)+"s"), 14, Palskillsinv);
				itemset("Tutelary", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor", "Reduces 100% Damage While Raising Shield", "Protection will Reduce 80% of Damage"), 16, Palskillsinv);
				itemset("Last judgment", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Lightning]","Sneaking + ThrowItem","",ChatColor.BOLD+"10 X 1.5D"), 17, Palskillsinv);

				itemset("HolyPile", Material.LIGHTNING_ROD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Drive HolyPile When You Use Once More","(Damage Affected By Thrust)","",ChatColor.BOLD+"4 X (0.4D + "+BigDecimal.valueOf(psd.Thrust.getOrDefault(p.getUniqueId(),0)*0.455).setScale(2, RoundingMode.HALF_EVEN)+")"), 18, Palskillsinv);
				itemset("Doom", Material.BUBBLE_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Lightning]","Doom To Enemies When Use Once More","(Damage Affected By Restraint)","",ChatColor.BOLD+" X (1.5D + "+BigDecimal.valueOf(psd.Restraint.getOrDefault(p.getUniqueId(),0)*1.68).setScale(2, RoundingMode.HALF_EVEN)+")"), 19, Palskillsinv);
				itemset("Griffon", Material.TRIDENT, 0, 1, Arrays.asList("Summon Griffon When Use Once More", "Griffon Will disappear When You Dismount", "Or Summon Again","", "Enhance Punish Skill While Riding Griffon","(Change Command to [Jump+LeftClick])","",
						ChatColor.UNDERLINE+"[Lightning]","Inflicts Splash Damage When Griffon Jump","",ChatColor.BOLD+" X (1.3D + "+BigDecimal.valueOf(psd.Judgement.getOrDefault(p.getUniqueId(),0)*1.3).setScale(2, RoundingMode.HALF_EVEN)+" X JumpPower)", "(Damage Affected By Judgement)"), 20, Palskillsinv);
				itemset("Sanctuary", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Summon Sanctuary When Use Once More","(Damage Affected By Encourge)","",ChatColor.BOLD+"10 X (0.55D + "+BigDecimal.valueOf(psd.Encourge.getOrDefault(p.getUniqueId(),0)*0.68).setScale(2, RoundingMode.HALF_EVEN)+")"), 22, Palskillsinv);
				itemset("Grace", Material.BUBBLE_CORAL, 0, 1, Arrays.asList("Give Saturation to Party"), 23, Palskillsinv);
				itemset("Salvation", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decreases Last judgment Cooldown"), 25, Palskillsinv);
				itemset("Penance", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Lightning]","Sprinting + ThrowItem","",ChatColor.BOLD+" X 15.6D"), 26, Palskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Palskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+psd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, Palskillsinv);

		}
		
		p.openInventory(Palskillsinv);
	}


}
