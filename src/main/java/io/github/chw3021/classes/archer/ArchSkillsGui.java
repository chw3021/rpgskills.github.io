package io.github.chw3021.classes.archer;



import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class ArchSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new ItemStack(ID, stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void Archskillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		ArchSkillsData asd = new ArchSkillsData(ArchSkillsData.loadData(path +"/plugins/RPGskills/ArchSkillsData.data"));
		Inventory Archskillsinv = Bukkit.createInventory(null, 54, "Archskills");

		Obtained.itemset(p, Archskillsinv);
		
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("ȭ��Ѹ���", Material.TIPPED_ARROW, 1, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.SpreadingArrows.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","��Ŭ��","�����̴� �������� �̵��մϴ�","���ӻ��� ���� �������� �̵��մϴ�","",ChatColor.BOLD+"X 0.65D", "Master LV.1"), 0, Archskillsinv);
			itemset("ȸ��", Material.TIPPED_ARROW, 2, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.Retrieve.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","�չٲٱ�","",ChatColor.BOLD+"X 0.5 X (1 + 0.23D X ȭ���)", "Master LV.1"), 1, Archskillsinv);
			itemset("�ӻ�", Material.TIPPED_ARROW, 3, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.RapidFire.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","��ũ���� + �չٲٱ�","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.034*(1+asd.RapidFire.getOrDefault(p.getUniqueId(),0)*0.01)).setScale(3, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 2, Archskillsinv);
			itemset("���߻��", Material.TIPPED_ARROW, 4, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.MultiShot.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","��ũ���� + �߻�","",ChatColor.BOLD+"25 X "+BigDecimal.valueOf(0.01*(1+asd.MultiShot.getOrDefault(p.getUniqueId(),0)*0.0065)).setScale(4, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 3, Archskillsinv);
			itemset("�žؼ�", Material.TIPPED_ARROW, 5, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.HookAndShot.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","��ũ���� + ��������", "����� 2�ʵ��� ������ϴ�", "�߻��ϰų� 2�ʰ� ������","����� �����ݴϴ�", "Master LV.1"), 4, Archskillsinv);
			itemset("Ʈ���ü�", Material.BOOK, 6, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.TripleShot.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","�ѹ��� ȭ���� 3���� ���ϴ�","",ChatColor.BOLD+"ù���� ȭ��: + (X "+BigDecimal.valueOf(0.05* (1+asd.TripleShot.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"+")",ChatColor.BOLD+"������ȭ���: ù��°ȭ��X0.62 + (X 0.015)", "Master LV.50"), 5, Archskillsinv);
			itemset("�ü�", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.Archery.getOrDefault(p.getUniqueId(),0),"","���ݷ��� �����մϴ�",ChatColor.BOLD+"X "+BigDecimal.valueOf(1+asd.Archery.getOrDefault(p.getUniqueId(),0)*0.035).setScale(2, RoundingMode.HALF_EVEN),"","Ȱ������ ��涧 1�ʵ��� ������ظ� �����ϴ�",ChatColor.BOLD+"-"+BigDecimal.valueOf(1-0.68 + Proficiency.getpro(p)*0.2)), 7, Archskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("��ø��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 9, Archskillsinv);
				itemset("��ó(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 10, Archskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 11, Archskillsinv);
				itemset("��Ѹ���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 12, Archskillsinv);
				itemset("��ġ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 13, Archskillsinv);
				itemset("�����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 14, Archskillsinv);
				itemset("������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 16, Archskillsinv);
				itemset("������ȭ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 17, Archskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("��ø��", Material.BOW, 0, 1, Arrays.asList("���Ƚ���� �����մϴ�", "ȭ��� ���� ���߸� ���Ƚ���� 1ȸ �����˴ϴ�"), 9, Archskillsinv);
				itemset("��ó", Material.ARROW, 0, 1, Arrays.asList("�ѹ��� �����ϰ� ���� ��� �����մϴ�"), 10, Archskillsinv);
				itemset("����", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList("ȭ����� ���� �����մϴ�","�߻���� �ι�� �����մϴ�"), 11, Archskillsinv);
				itemset("��Ѹ���", Material.TARGET, 0, 1, Arrays.asList("�߻�Ƚ���� 3������ �����մϴ�"), 12, Archskillsinv);
				itemset("�ȱ�", Material.DIRT, 0, 1, Arrays.asList("���� ���� ��� �����մϴ�"), 13, Archskillsinv);
				itemset("�����", Material.ARROW, 0, 1, Arrays.asList("�߻�Ƚ���� ������� �����մϴ�", "�������� �����մϴ�"), 14, Archskillsinv);
				itemset("������", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("�������� �����մϴ�","��Ƽ�� �ð��� �����մϴ�","","ȭ��Ѹ��� ���� Ȱ������ ����","��� ����մϴ�"), 16, Archskillsinv);
				itemset("������ȭ��", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","��ũ���� + �����۴�����", "",ChatColor.BOLD+"(20 x 30) X 0.028D"), 17, Archskillsinv);
				
				itemset("��������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 18, Archskillsinv);
				itemset("����ȭ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 20, Archskillsinv);
				itemset("�����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 23, Archskillsinv);
				itemset("��������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 25, Archskillsinv);
				itemset("ȭ������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 26, Archskillsinv);
			}
			else {
				itemset("��ø��", Material.BOW, 0, 1, Arrays.asList("���Ƚ���� �����մϴ�", "ȭ��� ���� ���߸� ���Ƚ���� 1ȸ �����˴ϴ�"), 9, Archskillsinv);
				itemset("��ó", Material.ARROW, 0, 1, Arrays.asList("�ѹ��� �����ϰ� ���� ��� �����մϴ�"), 10, Archskillsinv);
				itemset("����", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList("ȭ����� ���� �����մϴ�","�߻���� �ι�� �����մϴ�"), 11, Archskillsinv);
				itemset("��Ѹ���", Material.TARGET, 0, 1, Arrays.asList("�߻�Ƚ���� 3������ �����մϴ�"), 12, Archskillsinv);
				itemset("�ȱ�", Material.DIRT, 0, 1, Arrays.asList("���� ���� ��� �����մϴ�"), 13, Archskillsinv);
				itemset("�����", Material.ARROW, 0, 1, Arrays.asList("�߻�Ƚ���� ������� �����մϴ�", "�������� �����մϴ�"), 14, Archskillsinv);
				itemset("������", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("�������� �����մϴ�","��Ƽ�� �ð��� �����մϴ�","","ȭ��Ѹ��� ���� Ȱ������ ����","��� ����մϴ�"), 16, Archskillsinv);
				itemset("������ȭ��", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","��ũ���� + �����۴�����", "",ChatColor.BOLD+"(20 x 30) X 0.028D"), 17, Archskillsinv);
				
				itemset("��������", Material.BOW, 0, 1, Arrays.asList("�ֺ����� ������Ų �� �ѹ��� �����մϴ�"), 18, Archskillsinv);
				itemset("����ȭ��", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ����ȭ���� ���ϴ�", "(���ط��� �ӻ� ������ ����մϴ�)","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.34*(1+asd.RapidFire.getOrDefault(p.getUniqueId(),0)*0.2)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, Archskillsinv);
				itemset("�����", Material.BOW, 0, 1, Arrays.asList("�ѹ��� 7���� ȭ����� �߻��մϴ�"), 23, Archskillsinv);
				itemset("��������", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�","������ȭ�� ���ð��� �����մϴ�","������ ��ȣ���� �����ǵ� ������ ���������ϴ�"), 25, Archskillsinv);
				itemset("ȭ������", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","�޸��� + �����۴�����", "",ChatColor.BOLD+"60 X 0.35D"), 26, Archskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Archskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+asd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"), 35, Archskillsinv);

		}
		else {
			itemset("SpreadingArrows", Material.TIPPED_ARROW, 1, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.SpreadingArrows.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Earth]","Leftclick","Direction Depends on Your Movement","Move Same Direction", "If You Use in a row","",ChatColor.BOLD+"X 0.65D", "Master LV.1"), 0, Archskillsinv);
			itemset("Retrieve", Material.TIPPED_ARROW, 2, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.Retrieve.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Earth]","SwapHand","",ChatColor.BOLD+" 0.5 X X (1 + 0.23D X Arrows)", "Master LV.1"), 1, Archskillsinv);
			itemset("RapidFire", Material.TIPPED_ARROW, 3, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.RapidFire.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Earth]","Sneaking + SwapHand","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.034*(1+asd.RapidFire.getOrDefault(p.getUniqueId(),0)*0.01)).setScale(3, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 2, Archskillsinv);
			itemset("MultiShot", Material.TIPPED_ARROW, 4, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.MultiShot.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Earth]","Sneaking + Shoot","",ChatColor.BOLD+"25 X "+BigDecimal.valueOf(0.01*(1+asd.MultiShot.getOrDefault(p.getUniqueId(),0)*0.0065)).setScale(4, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 3, Archskillsinv);
			itemset("HookAndShot", Material.TIPPED_ARROW, 5, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.HookAndShot.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Earth]","","Sneaking + MeleeHit", "Hold a mob for 2secs", "Release the mob when you","shot or after 2 seconds", "Master LV.1"), 4, Archskillsinv);
			itemset("TripleShot", Material.BOOK, 6, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.TripleShot.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Earth]","Shot three arrows at a time","",ChatColor.BOLD+"FirstArrow: + (X "+BigDecimal.valueOf(0.05* (1+asd.TripleShot.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"+")",ChatColor.BOLD+"NextArrows: FirstX0.62 + (X 0.015D)", "Master LV.50"), 5, Archskillsinv);
			itemset("Archery", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.Archery.getOrDefault(p.getUniqueId(),0),"","Increases your arrow and melee damage",ChatColor.BOLD+"X "+BigDecimal.valueOf(1+asd.Archery.getOrDefault(p.getUniqueId(),0)*0.035).setScale(2, RoundingMode.HALF_EVEN),"","Indure All Damage for 1s since Charging",ChatColor.BOLD+"-"+BigDecimal.valueOf(1-0.68 + Proficiency.getpro(p)*0.2)), 7, Archskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("Agility(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Archskillsinv);
				itemset("Wound(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Archskillsinv);
				itemset("Piercing(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Archskillsinv);
				itemset("Spree(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Archskillsinv);
				itemset("Pitch(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Archskillsinv);
				itemset("QuadraShot(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Archskillsinv);
				itemset("Combat(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Archskillsinv);
				itemset("CrazyArrows(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Archskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Agility", Material.BOW, 0, 1, Arrays.asList("Increases Use Times", "Charge One Time When You Hit Enemy With Arrow"), 9, Archskillsinv);
				itemset("Wound", Material.ARROW, 0, 1, Arrays.asList("Attack Once More & Hold Enemy Shortly"), 10, Archskillsinv);
				itemset("Piercing", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList("Arrows Can Pierce","Double FireRate & Count"), 11, Archskillsinv);
				itemset("Spree", Material.TARGET, 0, 1, Arrays.asList("Shoot Three Times"), 12, Archskillsinv);
				itemset("Pitch", Material.DIRT, 0, 1, Arrays.asList("Pitchs Hit Enemy"), 13, Archskillsinv);
				itemset("QuadraShot", Material.ARROW, 0, 1, Arrays.asList("Increases Shoot Time & Rate", "Increases Damage"), 14, Archskillsinv);
				itemset("Combat", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage","Increases Indure Times","","Able to Shot Instantly","After Use SpreadingArrows"), 16, Archskillsinv);
				itemset("CrazyArrows", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Sneaking + ThrowItem", "Ult's damage is only affected by your explevels.","",ChatColor.BOLD+"(20 x 30) X 0.028D"), 17, Archskillsinv);
				
				itemset("Blindside(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, Archskillsinv);
				itemset("SpinShots(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, Archskillsinv);
				itemset("7Shots(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, Archskillsinv);
				itemset("Advanced Tactics(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Archskillsinv);
				itemset("Arrow Arts(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Archskillsinv);
			}
			else {
				itemset("Agility", Material.BOW, 0, 1, Arrays.asList("Increases Use Times", "Charge One Time When You Hit Enemy With Arrow"), 9, Archskillsinv);
				itemset("Wound", Material.ARROW, 0, 1, Arrays.asList("Attack Once More & Hold Enemy Shortly"), 10, Archskillsinv);
				itemset("Piercing", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList("Arrows Can Pierce","Double FireRate & Count"), 11, Archskillsinv);
				itemset("Spree", Material.TARGET, 0, 1, Arrays.asList("Shoot Three Times"), 12, Archskillsinv);
				itemset("Pitch", Material.DIRT, 0, 1, Arrays.asList("Pitchs Hit Enemy"), 13, Archskillsinv);
				itemset("QuadraShot", Material.ARROW, 0, 1, Arrays.asList("Increases Shoot Time", "Increases Damage"), 14, Archskillsinv);
				itemset("Combat", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage","Increases Indure Times","","Able to Shot Instantly","After Use SpreadingArrows"), 16, Archskillsinv);
				itemset("CrazyArrows", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Sneaking + ThrowItem", "Ult's damage is only affected by your explevels.","",ChatColor.BOLD+"(20 x 30) X 0.028D"), 17, Archskillsinv);
				
				itemset("Blindside ", Material.BOW, 0, 1, Arrays.asList("Stuns Hit Enemies And Shot Once More"), 18, Archskillsinv);
				itemset("SpinShots", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use SpinShots When Use Once More","(Damage Affected By RapidFire)","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.34*(1+asd.RapidFire.getOrDefault(p.getUniqueId(),0)*0.2)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, Archskillsinv);
				itemset("7Shots", Material.BOW, 0, 1, Arrays.asList("Shoot Seven Times"), 23, Archskillsinv);
				itemset("Advanced Tactics", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decreases CrazyArrows Cooldown","Able To Hit Enderman & Wither's Barrier"), 25, Archskillsinv);
				itemset("Arrow Arts", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Sprinting + ThrowItem", "Ult's damage is only affected by your explevels.","",ChatColor.BOLD+"60 X 0.35D"), 26, Archskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Archskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+asd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, Archskillsinv);

		}
		
		
		p.openInventory(Archskillsinv);
	}


}
