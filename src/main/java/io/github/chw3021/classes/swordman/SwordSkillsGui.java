package io.github.chw3021.classes.swordman;



import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.classes.SkillsGui;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class SwordSkillsGui extends SkillsGui{
	


	
	public void SwordSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		SwordSkillsData ssd = new SwordSkillsData(SwordSkillsData.loadData(path +"/plugins/RPGskills/SwordSkillsData.data"));
		Inventory Swordskillsinv = Bukkit.createInventory(null, 54, "Swordskills");
		Obtained.itemset(p, Swordskillsinv);
		

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("�÷�����", Material.WOODEN_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Rising.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","��Ŭ�� + ��ũ����"
					,"",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.45*(1+ssd.Rising.getOrDefault(p.getUniqueId(),0)*0.036)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 0, Swordskillsinv);
			itemset("�ϰ�", Material.STONE_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Strike.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","���� + ��Ŭ��"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8 *(1+ ssd.Strike.getOrDefault(p.getUniqueId(),0)*0.0669)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 1, Swordskillsinv);
			itemset("����", Material.IRON_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.SwordDrive.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","�չٲٱ�",
					"���ȿ���� ����ϴ� "+ (20+(ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)*2))/20+"�� "+ (ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)/10)+"����"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.65*(1 + ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)*0.0536)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 2, Swordskillsinv);
			itemset("����", Material.IRON_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.FlashyRush.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","��Ŭ��","�������� ��óġ�� ���ð��� �ʱ�ȭ�˴ϴ�"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.FlashyRush.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 3, Swordskillsinv);
			itemset("�ߵ�", Material.GOLDEN_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","�չٲٱ� + ��ũ����"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.23*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.065)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 4, Swordskillsinv);
			itemset("�޽�", Material.DIAMOND_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Swoop.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.87*(1+ssd.Swoop.getOrDefault(p.getUniqueId(),0)*0.067)).setScale(2, RoundingMode.HALF_EVEN)+"D","��ũ���� + ��������", "Master LV.50"), 5, Swordskillsinv);
			
			itemset("����", Material.SHIELD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Guard.getOrDefault(p.getUniqueId(),0),"","��ũ����", 
					"���� ���ҷ�: "+ ChatColor.BOLD+""+BigDecimal.valueOf(1 - (0.2 - ssd.Guard.getOrDefault(p.getUniqueId(),0)*0.016)).setScale(2, RoundingMode.HALF_EVEN)+"%",
					"���⸦ ", 	Math.round(5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024)/(Proficiency.getpro(p)+1))/10.0+"�ʵ��� �̻���","�������� ������ ȸ���˴ϴ�","(Master LV.10)"), 6, Swordskillsinv);
			itemset("�˼�", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Dualbladed.getOrDefault(p.getUniqueId(),0),"", "���ݷ��� ����մϴ�",
					"",ChatColor.BOLD+" X "+BigDecimal.valueOf((1.7+ssd.Dualbladed.getOrDefault(p.getUniqueId(),0)*0.03896)).setScale(2, RoundingMode.HALF_EVEN)), 7, Swordskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 9, Swordskillsinv);
				itemset("��ǳ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 10, Swordskillsinv);
				itemset("���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 11, Swordskillsinv);
				itemset("�ڻ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 12, Swordskillsinv);
				itemset("�˹�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 13, Swordskillsinv);
				itemset("��������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 14, Swordskillsinv);
				itemset("����ڼ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 15, Swordskillsinv);
				itemset("�˱����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 16, Swordskillsinv);
				itemset("���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 17, Swordskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("����", Material.BIG_DRIPLEAF, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ������ ����մϴ�","(���ط��� �÷����� ������ ����մϴ�)"
						,"",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.45*(1+ssd.Rising.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Swordskillsinv);
				itemset("��ǳ", Material.NETHERITE_SWORD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ��ǳ�� ����մϴ�","(���ط��� �ϰ� ������ ����մϴ�)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.92 *(1+ ssd.Strike.getOrDefault(p.getUniqueId(),0)*0.078)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Swordskillsinv);
				itemset("���", Material.POINTED_DRIPSTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ����� ����մϴ�","(���ط��� ���� ������ ����մϴ�)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Swordskillsinv);
				itemset("�ڻ�", Material.EXPOSED_CUT_COPPER_STAIRS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���� ��� ��������, ���ظ� �ѹ� ���ݴϴ�","(���ط��� �ڻ� ������ ����մϴ�)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.FlashyRush.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Swordskillsinv);
				itemset("�˹�", Material.GOLDEN_BOOTS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� �˹��� ����մϴ�","(���ط��� �ߵ� ������ ����մϴ�)"
						,"",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.5*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.2*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.0335)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Swordskillsinv);
				itemset("��������", Material.SCAFFOLDING, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���� ��� �����ϰ� �߰����ظ� �ݴϴ�"), 14, Swordskillsinv);
				itemset("����ڼ�", Material.SHIELD, 0, 1, Arrays.asList("���� ���������� �����մϴ�", "ȸ�� ���ð��� �����մϴ�"), 15, Swordskillsinv);
				itemset("�˱����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�"), 16, Swordskillsinv);
				itemset("���", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","��ũ���� + �����۴�����","",ChatColor.BOLD+" X 16.9D"), 17, Swordskillsinv);

				itemset("�ܰ���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 18, Swordskillsinv);
				itemset("�ٶ�����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 19, Swordskillsinv);
				itemset("��ȯ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 21, Swordskillsinv);
				itemset("��ȥ������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 22, Swordskillsinv);
				itemset("ȸ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 24, Swordskillsinv);
				itemset("�˽���ü(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 25, Swordskillsinv);
				itemset("�ɰ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 26, Swordskillsinv);
			}
			else {
				itemset("����", Material.BIG_DRIPLEAF, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ������ ����մϴ�","(���ط��� �÷����� ������ ����մϴ�)"
						,"",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.45*(1+ssd.Rising.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Swordskillsinv);
				itemset("��ǳ", Material.NETHERITE_SWORD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ��ǳ�� ����մϴ�","(���ط��� �ϰ� ������ ����մϴ�)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.92 *(1+ ssd.Strike.getOrDefault(p.getUniqueId(),0)*0.078)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Swordskillsinv);
				itemset("���", Material.POINTED_DRIPSTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ����� ����մϴ�","(���ط��� ���� ������ ����մϴ�)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Swordskillsinv);
				itemset("�ڻ�", Material.EXPOSED_CUT_COPPER_STAIRS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���� ��� ��������, ���ظ� �ѹ� ���ݴϴ�","(���ط��� �ڻ� ������ ����մϴ�)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.FlashyRush.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Swordskillsinv);
				itemset("�˹�", Material.GOLDEN_BOOTS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� �˹��� ����մϴ�","(���ط��� �ߵ� ������ ����մϴ�)"
						,"",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.5*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.2*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.0335)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Swordskillsinv);
				itemset("��������", Material.SCAFFOLDING, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���� ��� �����ϰ� �߰����ظ� �ݴϴ�"), 14, Swordskillsinv);
				itemset("����ڼ�", Material.SHIELD, 0, 1, Arrays.asList("���� ���������� �����մϴ�", "ȸ�� ���ð��� �����մϴ�"), 15, Swordskillsinv);
				itemset("�˱����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�"), 16, Swordskillsinv);
				itemset("���", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","��ũ���� + �����۴�����","",ChatColor.BOLD+" X 16.9D"), 17, Swordskillsinv);

				itemset("�ܰ���", Material.CUT_COPPER_SLAB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� �ܰ����� ����մϴ�","(���ط��� �÷����� ������ ����մϴ�)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.5*(1+ssd.Rising.getOrDefault(p.getUniqueId(),0)*0.09)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 18, Swordskillsinv);
				itemset("�ٶ�����", Material.NETHERITE_SWORD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� �ٶ����⸦ ����մϴ�","(���ط��� �ϰ� ������ ����մϴ�)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.94 *(1+ ssd.Strike.getOrDefault(p.getUniqueId(),0)*0.078)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, Swordskillsinv);
				itemset("��ȯ", Material.ACACIA_LEAVES, 0, 1, Arrays.asList("�ٸ� ��ų ����","���� ������ð��� 1�� �����մϴ�"), 21, Swordskillsinv);
				itemset("��ȥ������", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ��ȥ�����⸦ ����մϴ�","(���ط��� �ߵ� ������ ����մϴ�)"
						,"",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.43*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, Swordskillsinv);
				itemset("ȸ��", Material.NETHERITE_HELMET, 0, 1, Arrays.asList("���� ���������� �����մϴ�", "ȸ�� ���ð��� �����մϴ�"), 24, Swordskillsinv);
				itemset("�˽���ü", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ�, ���°� ������ �����մϴ�","��� ���ð��� �����մϴ�"), 25, Swordskillsinv);
				itemset("�ɰ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","�޸��� + �����۴�����","",ChatColor.BOLD+"45 X 0.1D, 12 X 3.3D, 12.1D"), 26, Swordskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Swordskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+ssd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"), 35, Swordskillsinv);

		}
		else {
			itemset("RisingBlades", Material.WOODEN_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Rising.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Earth]","RightClick + Sneaking"
					,"",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.45*(1+ssd.Rising.getOrDefault(p.getUniqueId(),0)*0.036)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 0, Swordskillsinv);
			itemset("Strike", Material.STONE_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Strike.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Earth]","Jump + LeftClick"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8 *(1+ ssd.Strike.getOrDefault(p.getUniqueId(),0)*0.0669)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 1, Swordskillsinv);
			itemset("SwordDrive", Material.IRON_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.SwordDrive.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Earth]","SwapHand",
					"Get Absorbtion Effect "+ (20+(ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)*2))/20+"s "+ (ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)/10)+"lv"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.65*(1 + ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)*0.0536)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 2, Swordskillsinv);
			itemset("FlashyRush", Material.IRON_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.FlashyRush.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Earth]","Rightclick","Reset Cooldown", "If You Kill an Enemy With FlashRush"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.FlashyRush.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 3, Swordskillsinv);
			itemset("CriticalDraw", Material.GOLDEN_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Earth]","SwapHand + Sneaking"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.23*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.065)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 4, Swordskillsinv);
			itemset("Swoop", Material.DIAMOND_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Swoop.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Earth]"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.87*(1+ssd.Swoop.getOrDefault(p.getUniqueId(),0)*0.067)).setScale(2, RoundingMode.HALF_EVEN)+"D","Sneaking + Hit", "Master LV.50"), 5, Swordskillsinv);
			
			itemset("Guard", Material.SHIELD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Guard.getOrDefault(p.getUniqueId(),0),"","Sneaking", 
					"Reduced Damage: "+ ChatColor.BOLD+""+BigDecimal.valueOf(1 - (0.2 - ssd.Guard.getOrDefault(p.getUniqueId(),0)*0.016)).setScale(2, RoundingMode.HALF_EVEN)+"%",
					"Gage Will be Recovered Slowly", "When You Don't Use Guard for ", 
					Math.round(5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024)/(Proficiency.getpro(p)+1))/10.0+"s","(Master LV.10)"), 6, Swordskillsinv);
			itemset("Swordsmanship", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Dualbladed.getOrDefault(p.getUniqueId(),0),"","Increases Damage"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf((1.7+ssd.Dualbladed.getOrDefault(p.getUniqueId(),0)*0.03896)).setScale(2, RoundingMode.HALF_EVEN)), 7, Swordskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("FallenLeaves(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Swordskillsinv);
				itemset("Wind(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Swordskillsinv);
				itemset("Spike(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Swordskillsinv);
				itemset("Stab(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Swordskillsinv);
				itemset("SwordDance(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Swordskillsinv);
				itemset("Slash(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Swordskillsinv);
				itemset("Defensive(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 15, Swordskillsinv);
				itemset("SwordAura(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Swordskillsinv);
				itemset("SwordStorm(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Swordskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("FallenLeaves", Material.BIG_DRIPLEAF, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use FallenLeaves When Use Once More","(Damage Affected By RisingBlades)"
						,"",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.45*(1+ssd.Rising.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Swordskillsinv);
				itemset("Wind", Material.NETHERITE_SWORD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Wind When Use Once More","(Damage Affected By Strike)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.92 *(1+ ssd.Strike.getOrDefault(p.getUniqueId(),0)*0.078)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Swordskillsinv);
				itemset("Spike", Material.POINTED_DRIPSTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Spike When Use Once More","(Damage Affected By SwordDrive)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Swordskillsinv);
				itemset("Stab", Material.EXPOSED_CUT_COPPER_STAIRS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Hold Hit Enemy Shortly, Then Deal Once More","(Damage Affected By FlashyRush)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.FlashyRush.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Swordskillsinv);
				itemset("SwordDance", Material.GOLDEN_BOOTS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use SwordDance When Use Once More","(Damage Affected By CriticalDraw)"
						,"",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.5*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.2*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.0335)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Swordskillsinv);
				itemset("Slash", Material.SCAFFOLDING, 0, 1, Arrays.asList("Hold Hit Enemy Shortly","Then Attack Once More"), 14, Swordskillsinv);
				itemset("Defensive", Material.SHIELD, 0, 1, Arrays.asList("Increases Guard Gage", "Decreases Recovering Cooldown"), 15, Swordskillsinv);
				itemset("SwordAura", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Range"), 16, Swordskillsinv);
				itemset("SwordStorm", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Sneaking + ThrowItem","",ChatColor.BOLD+" X 16.9D"), 17, Swordskillsinv);

				itemset("DividingAir(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, Swordskillsinv);
				itemset("WindBrandish(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, Swordskillsinv);
				itemset("Circulation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, Swordskillsinv);
				itemset("SoulFlourish(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, Swordskillsinv);
				itemset("Recovery(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 24, Swordskillsinv);
				itemset("SteadyBlade(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Swordskillsinv);
				itemset("MindSword(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Swordskillsinv);
			}
			else {
				itemset("FallenLeaves", Material.BIG_DRIPLEAF, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use FallenLeaves When Use Once More","(Damage Affected By RisingBlades)"
						,"",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.45*(1+ssd.Rising.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Swordskillsinv);
				itemset("Wind", Material.NETHERITE_SWORD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Wind When Use Once More","(Damage Affected By Strike)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.92 *(1+ ssd.Strike.getOrDefault(p.getUniqueId(),0)*0.078)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Swordskillsinv);
				itemset("Spike", Material.POINTED_DRIPSTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Spike When Use Once More","(Damage Affected By SwordDrive)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Swordskillsinv);
				itemset("Stab", Material.EXPOSED_CUT_COPPER_STAIRS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Hold Hit Enemy Shortly, Then Deal Once More","(Damage Affected By FlashyRush)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.FlashyRush.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Swordskillsinv);
				itemset("SwordDance", Material.GOLDEN_BOOTS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use SwordDance When Use Once More","(Damage Affected By CriticalDraw)"
						,"",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.5*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.2*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.0335)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Swordskillsinv);
				itemset("Slash", Material.SCAFFOLDING, 0, 1, Arrays.asList("Hold Hit Enemy Shortly","Then Attack Once More"), 14, Swordskillsinv);
				itemset("Defensive", Material.SHIELD, 0, 1, Arrays.asList("Increases Guard Gage", "Decreases Recovering Cooldown"), 15, Swordskillsinv);
				itemset("SwordAura", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Range"), 16, Swordskillsinv);
				itemset("SwordStorm", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Sneaking + ThrowItem","",ChatColor.BOLD+" X 16.9D"), 17, Swordskillsinv);

				itemset("DividingAir", Material.CUT_COPPER_SLAB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use DividingAir When Use Once More","(Damage Affected By RisingBlades)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.5*(1+ssd.Rising.getOrDefault(p.getUniqueId(),0)*0.09)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 18, Swordskillsinv);
				itemset("WindBrandish", Material.BOW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Wind When Use Once More","(Damage Affected By Strike)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.94 *(1+ ssd.Strike.getOrDefault(p.getUniqueId(),0)*0.078)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, Swordskillsinv);
				itemset("Circulation", Material.ACACIA_LEAVES, 0, 1, Arrays.asList("Decrease Cooldown Of FlashRush 1second","When You Use Another skill"), 21, Swordskillsinv);
				itemset("SoulFlourish", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use SoulFlourish When Use Once More","(Damage Affected By CriticalDraw)"
						,"",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.43*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, Swordskillsinv);
				itemset("Recovery", Material.NETHERITE_HELMET, 0, 1, Arrays.asList("Increases Guard Gage", "Decreases Recovering Cooldown"), 24, Swordskillsinv);
				itemset("SteadyBlade", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage, Range & Armor","Decreases SwordStorm Cooldown"), 25, Swordskillsinv);
				itemset("MindSword", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Sprinting + ThrowItem","",ChatColor.BOLD+"45 X 0.1D, 12 X 3.3D, 12.1D"), 26, Swordskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Swordskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+ssd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, Swordskillsinv);

		}
		
		p.openInventory(Swordskillsinv);
	}


}
