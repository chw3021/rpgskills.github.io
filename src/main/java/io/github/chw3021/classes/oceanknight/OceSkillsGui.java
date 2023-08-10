package io.github.chw3021.classes.oceanknight;



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

public class OceSkillsGui{
	



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
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	
	public void OceSkillsinv(Player p)
	{
		Inventory Oceskillsinv = Bukkit.createInventory(null, 54, "Oceskills");
	    String path = new File("").getAbsolutePath();
		OceSkillsData fsd = new OceSkillsData(OceSkillsData.loadData(path +"/plugins/RPGskills/OceSkillsData.data"));
		Obtained.itemset(p, Oceskillsinv);

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("�ٴ�â��", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�� �迭]","���� + �չٲٱ�","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.353*(1+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0)*0.038)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 0, Oceskillsinv);
			itemset("���ǹ溮", Material.SHIELD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WaterBarrier.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�� �迭]","���� + ��ũ���� + �չٲٱ�", "���ۿ� ������� ���� ��ȯ�մϴ�", "��ȣ���ȿ����� ������ظ� �����ϴ�","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.65*(1+fsd.WaterBarrier.getOrDefault(p.getUniqueId(),0)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 1, Oceskillsinv);
			itemset("��â", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Javelin.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�� �迭]","��Ŭ�� + ����", "â�� �ٽ� �ֿ�� ���ð���", "������ �ǵ����޽��ϴ�","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.635*(1+fsd.Javelin.getOrDefault(p.getUniqueId(),0)*0.0453)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 2, Oceskillsinv);
			itemset("����", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.RipCurrent.getOrDefault(p.getUniqueId(),0),"","�޷� ���� �չٲٱ�","����� ���� ����ɴϴ�", "Master LV.1"), 3, Oceskillsinv);
			itemset("��ձ�", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�� �迭]","��ũ���� + ��������","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.7*(1+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0)*0.0453)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 4, Oceskillsinv);
			itemset("��ǳ��", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WetSwing.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�� �迭]","�չٲٱ� + ��ũ����","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.935*(1+fsd.WetSwing.getOrDefault(p.getUniqueId(),0)*0.0433)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 5, Oceskillsinv);
			itemset("������", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Splash.getOrDefault(p.getUniqueId(),0),"","���ݷ��� �����մϴ�",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+fsd.Splash.getOrDefault(p.getUniqueId(),0)*0.03415)).setScale(2, RoundingMode.HALF_EVEN),"", 
					"���� �Ǵ� ���Ͻ� �������ظ� �߻���ŵ�ϴ�", "�������ؿ� �鿪�� �˴ϴ�",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.1*(1+fsd.Splash.getOrDefault(p.getUniqueId(),0)*0.03415)).setScale(2, RoundingMode.HALF_EVEN)+"D",
					"","�� �迭 ���׷��� �����մϴ�", "����â�� �޷� �����ο��� �޽��ϴ�", "������ ���� �̷ο� ȿ������ ����ϴ�"), 7, Oceskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("ȸ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 9, Oceskillsinv);
				itemset("���а�Ÿ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 10, Oceskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 11, Oceskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 12, Oceskillsinv);
				itemset("�䵿ġ���ĵ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 13, Oceskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 14, Oceskillsinv);
				itemset("�ػ�������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 16, Oceskillsinv);
				itemset("�ؽŰ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 17, Oceskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("ȸ��", Material.PRISMARINE_BRICK_STAIRS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","���Է½� ȸ���� ����մϴ�","(���ط��� �ٴ�â�� ������ ����մϴ�)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.353*(1+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Oceskillsinv);
				itemset("���а�Ÿ", Material.DARK_PRISMARINE_SLAB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","���Է½� ���а�Ÿ�� ����մϴ�","(���ط��� ���ǹ溮 ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.65*(1+fsd.WaterBarrier.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Oceskillsinv);
				itemset("����", Material.HORN_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","����â�� ��ġ�� �ֺ� ������ �����ϴ�","(���ط��� ��â ������ ����մϴ�)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.4*(1+fsd.Javelin.getOrDefault(p.getUniqueId(),0)*0.0353)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Oceskillsinv);
				itemset("����", Material.TRIDENT, 0, 1, Arrays.asList("���� ������ ���� ��� �����մϴ�"), 12, Oceskillsinv);
				itemset("�䵿ġ���ĵ�", Material.DARK_PRISMARINE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","��ձ����� ����ݽ� �䵿ġ���ĵ��� ����մϴ�","(���ط��� ��ձ� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Oceskillsinv);
				itemset("����", Material.PRISMARINE_WALL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","���Է½� ������ ����մϴ�","(���ط��� ��ǳ�� ������ ����մϴ�)","",ChatColor.BOLD+"2 X "+BigDecimal.valueOf(0.535*(1+fsd.WetSwing.getOrDefault(p.getUniqueId(),0)*0.0433)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Oceskillsinv);
				itemset("�ػ�������", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�", "���и� ����ִ� ���� �޴����ذ� 90% �����մϴ�"), 16, Oceskillsinv);
				itemset("�ؽŰ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","��ũ���� + �����۴�����","",ChatColor.BOLD+"4 X 2.5D, 9.8D"), 17, Oceskillsinv);

				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 18, Oceskillsinv);
				itemset("�޷�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 20, Oceskillsinv);
				itemset("���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 22, Oceskillsinv);
				itemset("������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 23, Oceskillsinv);
				itemset("���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 25, Oceskillsinv);
				itemset("�ٴ��� �г�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 26, Oceskillsinv);
			}
			else {
				itemset("ȸ��", Material.PRISMARINE_BRICK_STAIRS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","���Է½� ȸ���� ����մϴ�","(���ط��� �ٴ�â�� ������ ����մϴ�)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.353*(1+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Oceskillsinv);
				itemset("���а�Ÿ", Material.DARK_PRISMARINE_SLAB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","���Է½� ���а�Ÿ�� ����մϴ�","(���ط��� ���ǹ溮 ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.65*(1+fsd.WaterBarrier.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Oceskillsinv);
				itemset("����", Material.HORN_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","����â�� ��ġ�� �ֺ� ������ �����ϴ�","(���ط��� ��â ������ ����մϴ�)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.4*(1+fsd.Javelin.getOrDefault(p.getUniqueId(),0)*0.0353)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Oceskillsinv);
				itemset("����", Material.TRIDENT, 0, 1, Arrays.asList("���� ������ ���� ��� �����մϴ�"), 12, Oceskillsinv);
				itemset("�䵿ġ���ĵ�", Material.DARK_PRISMARINE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","��ձ����� ����ݽ� �䵿ġ���ĵ��� ����մϴ�","(���ط��� ��ձ� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Oceskillsinv);
				itemset("����", Material.PRISMARINE_WALL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","���Է½� ������ ����մϴ�","(���ط��� ��ǳ�� ������ ����մϴ�)","",ChatColor.BOLD+"2 X "+BigDecimal.valueOf(0.535*(1+fsd.WetSwing.getOrDefault(p.getUniqueId(),0)*0.0433)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Oceskillsinv);
				itemset("�ػ�������", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�", "���и� ����ִ� ���� �޴����ذ� 90% �����մϴ�"), 16, Oceskillsinv);
				itemset("�ؽŰ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","��ũ���� + �����۴�����","",ChatColor.BOLD+"4 X 2.5D, 9.8D"), 17, Oceskillsinv);

				itemset("����", Material.WATER_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","���Է½� ������ ����մϴ�","(���ط��� �ٴ�â�� ������ ����մϴ�)","",ChatColor.BOLD+"30 X "+BigDecimal.valueOf(0.05*(1+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0)*0.022)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 18, Oceskillsinv);
				itemset("�޷�", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","���Է½� ����â��ġ�� �̵��մϴ�", "(���ط��� ��â ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+fsd.Javelin.getOrDefault(p.getUniqueId(),0)*0.0753)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, Oceskillsinv);
				itemset("���", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","���а�Ÿ�� ����ݽ� ��⸦ ����մϴ�","(���ط��� ��ձ� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0)*0.0834)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, Oceskillsinv);
				itemset("������", Material.BUBBLE_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","���Է½� �����⸦ ����մϴ�","(���ط��� ��ǳ�� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.235*(1+fsd.WetSwing.getOrDefault(p.getUniqueId(),0)*0.123)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 23, Oceskillsinv);
				itemset("���", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�","�ؽŰ� ���ð��� �����մϴ�"), 25, Oceskillsinv);
				itemset("�ٴ��� �г�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","�޸��� + �����۴�����","",ChatColor.BOLD+"20 X 1.2D, 12.5D"), 26, Oceskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Oceskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"), 35, Oceskillsinv);
		
		}
		else {
			itemset("WaterSpear", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Water]","Blocking + SwapHand","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.353*(1+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0)*0.038)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 0, Oceskillsinv);
			itemset("WaterBarrier", Material.SHIELD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WaterBarrier.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Water]","Blocking + Sneaking + SwapHand", "Summon Water if you're not in Water", "Block All Damage When You're In Barrier","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.65*(1+fsd.WaterBarrier.getOrDefault(p.getUniqueId(),0)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 1, Oceskillsinv);
			itemset("Javelin", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Javelin.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Water]","LeftClick + Jump", "Get Back Half of Cooldown", "When you pick up","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.635*(1+fsd.Javelin.getOrDefault(p.getUniqueId(),0)*0.0453)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 2, Oceskillsinv);
			itemset("RipCurrent", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.RipCurrent.getOrDefault(p.getUniqueId(),0),"","SwapHand while RipTiding","Pull the Nearist Entity While Riptiding", "Master LV.1"), 3, Oceskillsinv);
			itemset("OceanCharge", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Water]","Sneaking + Hit","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.7*(1+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0)*0.0453)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 4, Oceskillsinv);
			itemset("WetSwing", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WetSwing.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Water]","SwapHand + Sneaking","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.935*(1+fsd.WetSwing.getOrDefault(p.getUniqueId(),0)*0.0433)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 5, Oceskillsinv);
			itemset("Splash", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Splash.getOrDefault(p.getUniqueId(),0),"","Increases Damage",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+fsd.Splash.getOrDefault(p.getUniqueId(),0)*0.03415)).setScale(2, RoundingMode.HALF_EVEN),"", 
					"Inflicts Splash Damage When Attack Or Fall", "Immune to Falling Damage",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.1*(1+fsd.Splash.getOrDefault(p.getUniqueId(),0)*0.03415)).setScale(2, RoundingMode.HALF_EVEN)+"D",
					"", "Get Riptide Enchant if You Don't Have", "Get Water Ability When Swim"), 7, Oceskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("Diffraction(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Oceskillsinv);
				itemset("ShieldSmite(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Oceskillsinv);
				itemset("Crisp(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Oceskillsinv);
				itemset("Hightide(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Oceskillsinv);
				itemset("Fluctuation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Oceskillsinv);
				itemset("Backwash(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Oceskillsinv);
				itemset("AquaCombat(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Oceskillsinv);
				itemset("Grand Waves(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Oceskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Diffraction", Material.PRISMARINE_BRICK_STAIRS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Use Diffraction When You Use Once More","(Damage Affected By WaterSpear)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.353*(1+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Oceskillsinv);
				itemset("ShieldSmite", Material.DARK_PRISMARINE_SLAB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Use ShieldSmite When Use Once More","(Damage Affected By WaterBarrier)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.65*(1+fsd.WaterBarrier.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Oceskillsinv);
				itemset("Crisp", Material.HORN_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Pull Near By Enemies To Hit Pos","(Damage Affected By Javelin)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.4*(1+fsd.Javelin.getOrDefault(p.getUniqueId(),0)*0.0353)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Oceskillsinv);
				itemset("Hightide", Material.TRIDENT, 0, 1, Arrays.asList("Able to Pull Multiple Entities","Hold Enemies Shortly"), 12, Oceskillsinv);
				itemset("Fluctuation", Material.DARK_PRISMARINE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Smite With Shield After OceanCharge","(Damage Affected By OceanCharge)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Oceskillsinv);
				itemset("Backwash", Material.PRISMARINE_WALL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Use Backwash When Use Once More","(Damage Affected By WetSwing)","",ChatColor.BOLD+"2 X "+BigDecimal.valueOf(0.535*(1+fsd.WetSwing.getOrDefault(p.getUniqueId(),0)*0.0433)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Oceskillsinv);
				itemset("AquaCombat", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor", "Reduces 90% Damage While Raising Shield"), 16, Oceskillsinv);
				itemset("Grand Waves", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Sneaking + ThrowItem","",ChatColor.BOLD+"4 X 2.5D, 9.8D"), 17, Oceskillsinv);

				itemset("Flood(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, Oceskillsinv);
				itemset("Torrent(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, Oceskillsinv);
				itemset("Impale(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, Oceskillsinv);
				itemset("Cleave(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, Oceskillsinv);
				itemset("Prowess(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Oceskillsinv);
				itemset("Wrath Of Sea(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Oceskillsinv);
			}
			else {
				itemset("Diffraction", Material.PRISMARINE_BRICK_STAIRS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Use Diffraction When You Use Once More","(Damage Affected By WaterSpear)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.353*(1+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Oceskillsinv);
				itemset("ShieldSmite", Material.DARK_PRISMARINE_SLAB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Use ShieldSmite When Use Once More","(Damage Affected By WaterBarrier)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.65*(1+fsd.WaterBarrier.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Oceskillsinv);
				itemset("Crisp", Material.HORN_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Pull Near By Enemies To Hit Pos","(Damage Affected By Javelin)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.4*(1+fsd.Javelin.getOrDefault(p.getUniqueId(),0)*0.0353)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Oceskillsinv);
				itemset("Hightide", Material.TRIDENT, 0, 1, Arrays.asList("Able to Pull Multiple Entities","Hold Enemies Shortly"), 12, Oceskillsinv);
				itemset("Fluctuation", Material.DARK_PRISMARINE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Smite With Shield After OceanCharge","(Damage Affected By OceanCharge)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Oceskillsinv);
				itemset("Backwash", Material.PRISMARINE_WALL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Use Backwash When Use Once More","(Damage Affected By WetSwing)","",ChatColor.BOLD+"2 X "+BigDecimal.valueOf(0.535*(1+fsd.WetSwing.getOrDefault(p.getUniqueId(),0)*0.0433)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Oceskillsinv);
				itemset("AquaCombat", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor", "Reduces 90% Damage While Raising Shield"), 16, Oceskillsinv);
				itemset("Grand Waves", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Sneaking + ThrowItem","",ChatColor.BOLD+"4 X 2.5D, 9.8D"), 17, Oceskillsinv);

				itemset("Flood", Material.WATER_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Use Flood When You Use Once More","(Damage Affected By WaterSpear)","",ChatColor.BOLD+"30 X "+BigDecimal.valueOf(0.05*(1+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0)*0.022)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 18, Oceskillsinv);
				itemset("Torrent", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Jump To Trident When Use Once More", "(Damage Affected By Javelin)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+fsd.Javelin.getOrDefault(p.getUniqueId(),0)*0.0753)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, Oceskillsinv);
				itemset("Impale", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Impale After Fluctuation","(Damage Affected By OceanCharge)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0)*0.0834)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, Oceskillsinv);
				itemset("Cleave", Material.BUBBLE_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Use Cleave When Use Once More","(Damage Affected By WetSwing)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.235*(1+fsd.WetSwing.getOrDefault(p.getUniqueId(),0)*0.123)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 23, Oceskillsinv);
				itemset("Prowess", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decreases Waves Cooldown"), 25, Oceskillsinv);
				itemset("Wrath Of Sea", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Sprinting + ThrowItem","",ChatColor.BOLD+"20 X 1.2D, 12.5D"), 26, Oceskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Oceskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, Oceskillsinv);
		
		}
		
		
		p.openInventory(Oceskillsinv);
	}


}
