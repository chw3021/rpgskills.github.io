package io.github.chw3021.classes.witchdoctor;



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
import io.github.chw3021.classes.SkillsGui;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class WdcSkillsGui extends SkillsGui{
	
	
	
	public void WdcSkillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		WdcSkillsData wsd = new WdcSkillsData(WdcSkillsData.loadData(path +"/plugins/RPGskills/WdcSkillsData.data"));
		Inventory Wdcskillsinv = Bukkit.createInventory(null, 54, "Wdcskills");
		Obtained.itemset(p, Wdcskillsinv);

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("�����Ǽ۰���", Material.STONE_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Fangs.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"[�͵� �迭]","��ũ���� + �չٲٱ�","",ChatColor.BOLD+"5 X (0.4D + "+BigDecimal.valueOf(wsd.Fangs.getOrDefault(p.getUniqueId(), 0)*0.3).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 0, Wdcskillsinv);
			itemset("��ȣ�Ƿξ�:����", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Bosou.getOrDefault(p.getUniqueId(), 0),"","��Ŭ��", 
					"��Ƽ������ ���ݷ��� �����մϴ�",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.05*(1+wsd.Bosou.getOrDefault(p.getUniqueId(), 0)*0.04)).setScale(2, RoundingMode.HALF_EVEN),
					"","��Ƽ������ �޴����ذ� �����մϴ�",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.3+(1+wsd.Bosou.getOrDefault(p.getUniqueId(), 0)*0.045)).setScale(2, RoundingMode.HALF_EVEN), "Master LV.10"), 1, Wdcskillsinv);
			itemset("��Ȯ", Material.IRON_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Harvest.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"[���� �迭]","��Ŭ��", "��ȥ�� ���� ������� ���� ������ �����", "ü�°� ��⸦ ��� ȸ���մϴ�", "���� �޴� ���ظ� 15%������ŵ�ϴ�","",ChatColor.BOLD+" X (0.5D + "+BigDecimal.valueOf(wsd.Harvest.getOrDefault(p.getUniqueId(), 0)*0.56).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 2, Wdcskillsinv);
			itemset("����", Material.GOLDEN_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Wraith.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"[ȭ�� �迭]","��Ŭ�� + ��ũ����","",ChatColor.BOLD+"20 X (0.2D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*0.32).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 3, Wdcskillsinv);
			itemset("��ü��Ż", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.AstralProjection.getOrDefault(p.getUniqueId(), 0),"","�չٲٱ�", "��ũ����� ����", "Master LV.1"), 4, Wdcskillsinv);
			itemset("�ּ�", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Incantation.getOrDefault(p.getUniqueId(), 0),"","��Ŭ�� + ��ũ����", "Master LV.1"), 5, Wdcskillsinv);
			itemset("�һ�", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("�һ��� ������ ������ �ʴ´��", "������ ���ð��� ����˴ϴ�", "��Ƽ���鵵 �һ��� ����������", "�������� ���ð��� ����˴ϴ�(25��)", "�нú�"), 6, Wdcskillsinv);
			itemset("�����Ƿξ�:����", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Legba.getOrDefault(p.getUniqueId(), 0),"","���� �迭 ���׷��� �����մϴ�","���ݷ��� �����մϴ�","",ChatColor.BOLD+" + "+BigDecimal.valueOf(wsd.Legba.getOrDefault(p.getUniqueId(), 0)*0.585).setScale(2, RoundingMode.HALF_EVEN)), 7, Wdcskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("���۸�����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 9, Wdcskillsinv);
				itemset("�ݼ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 10, Wdcskillsinv);
				itemset("��ȥ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 11, Wdcskillsinv);
				itemset("�����ǽ���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 12, Wdcskillsinv);
				itemset("������߰���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 13, Wdcskillsinv);
				itemset("��ȣ������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 14, Wdcskillsinv);
				itemset("���Ǽ�ȯ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 16, Wdcskillsinv);
				itemset("�ٷջ�޵�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 17, Wdcskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("���۸�����", Material.ZOGLIN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]","���Է½� ���۸������� ����մϴ�","(���ط��� �����Ǽ۰��� ������ ���մϴ�)","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(wsd.Fangs.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, Wdcskillsinv);
				itemset("�ݼ�", Material.NETHER_BRICK, 0, 1, Arrays.asList("���Է½� �ݼ��� ����մϴ�","��Ƽ�� ������ ü���� 20% �Ҹ��մϴ�", "���ȿ���� �ְ� "+ChatColor.BOLD+BigDecimal.valueOf(wsd.Bosou.getOrDefault(p.getUniqueId() ,0)*0.5).setScale(2, RoundingMode.HALF_EVEN)+ "�ʵ��� �������·� ����ϴ�") , 10, Wdcskillsinv);
				itemset("��ȥ", Material.DRAGON_BREATH, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ��ȥ�� ����մϴ�","(���ط��� ��Ȯ ������ ���մϴ�)","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(wsd.Harvest.getOrDefault(p.getUniqueId(), 0)*0.76).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, Wdcskillsinv);
				itemset("�����ǽ���", Material.BAT_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]","���Է½� �����ǽ����� ����մϴ�","(���ط��� ���� ������ ���մϴ�)","",ChatColor.BOLD+"20 X (0.3D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*0.342).setScale(2, RoundingMode.HALF_EVEN)+")"), 12, Wdcskillsinv);
				itemset("������߰���", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("��ü��Ż ������ �̵��ӵ��� ���� ȿ���� ����ϴ�"), 13, Wdcskillsinv);
				itemset("��ȣ������", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("������ �ֺ� ��Ƽ������ ġ���մϴ�"), 14, Wdcskillsinv);
				itemset("���Ǽ�ȯ", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� �����մϴ�", ChatColor.UNDERLINE+"[�͵� �迭]","��Ȯ�� ������ ���� ���� ����ϸ�" ,"����� ���մϴ�", 
						"����� 10�ʵ��� �����Դϴ�",
						"������ �ɷ�ġ���� ���� ������ ����մϴ�","(�ִ� 6����)"
						), 16, Wdcskillsinv);
				itemset("�ٷջ�޵�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("��ũ���� + �����۴�����","��Ƽ������ �״°��� �������ݴϴ�", "��Ƽ������ ���ݷ��� �����մϴ�","",ChatColor.BOLD+"+ "+BigDecimal.valueOf(p.getLevel()*0.5).setScale(2, RoundingMode.HALF_EVEN)), 17, Wdcskillsinv);

				itemset("�۰��ϼ⵵(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 18, Wdcskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 20, Wdcskillsinv);
				itemset("���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 21, Wdcskillsinv);
				itemset("ǳ��������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 23, Wdcskillsinv);
				itemset("�Ҹ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 24, Wdcskillsinv);
				itemset("��������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 25, Wdcskillsinv);
				itemset("������ ������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 26, Wdcskillsinv);
			}
			else {
				itemset("���۸�����", Material.ZOGLIN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]","���Է½� ���۸������� ����մϴ�","(���ط��� �����Ǽ۰��� ������ ���մϴ�)","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(wsd.Fangs.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, Wdcskillsinv);
				itemset("�ݼ�", Material.NETHER_BRICK, 0, 1, Arrays.asList("���Է½� �ݼ��� ����մϴ�","��Ƽ�� ������ ü���� 20% �Ҹ��մϴ�", "���ȿ���� �ְ� "+ChatColor.BOLD+BigDecimal.valueOf(wsd.Bosou.getOrDefault(p.getUniqueId() ,0)*0.5).setScale(2, RoundingMode.HALF_EVEN)+ "�ʵ��� �������·� ����ϴ�") , 10, Wdcskillsinv);
				itemset("��ȥ", Material.DRAGON_BREATH, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ��ȥ�� ����մϴ�","(���ط��� ��Ȯ ������ ���մϴ�)","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(wsd.Harvest.getOrDefault(p.getUniqueId(), 0)*0.76).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, Wdcskillsinv);
				itemset("�����ǽ���", Material.BAT_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]","���Է½� �����ǽ����� ����մϴ�","(���ط��� ���� ������ ���մϴ�)","",ChatColor.BOLD+"20 X (0.3D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*0.342).setScale(2, RoundingMode.HALF_EVEN)+")"), 12, Wdcskillsinv);
				itemset("������߰���", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("��ü��Ż ������ �̵��ӵ��� ���� ȿ���� ����ϴ�"), 13, Wdcskillsinv);
				itemset("��ȣ������", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("������ �ֺ� ��Ƽ������ ġ���մϴ�"), 14, Wdcskillsinv);
				itemset("���Ǽ�ȯ", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� �����մϴ�", ChatColor.UNDERLINE+"[�͵� �迭]","��Ȯ�� ������ ���� ���� ����ϸ�" ,"����� ���մϴ�", 
						"����� 10�ʵ��� �����Դϴ�",
						"������ �ɷ�ġ���� ���� ������ ����մϴ�","(�ִ� 6����)"
						), 16, Wdcskillsinv);
				itemset("�ٷջ�޵�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("��ũ���� + �����۴�����","��Ƽ������ �״°��� �������ݴϴ�", "��Ƽ������ ���ݷ��� �����մϴ�","",ChatColor.BOLD+"+ "+BigDecimal.valueOf(p.getLevel()*0.5).setScale(2, RoundingMode.HALF_EVEN)), 17, Wdcskillsinv);

				itemset("�۰��ϼ⵵", Material.MYCELIUM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]","���Է½� �۰��ϼ⵵�� ����մϴ�","���۸��� ��� �����մϴ�","�۰��ϵ��� ���۸��� �����Ѱ��� ��Ÿ���ϴ�","������� �ش� ��ġ�� �̵��մϴ�","(���ط��� �����Ǽ۰��� ������ ���մϴ�)","",ChatColor.BOLD+"10 X (0.7D + "+BigDecimal.valueOf(wsd.Fangs.getOrDefault(p.getUniqueId(), 0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")"), 19, Wdcskillsinv);
				itemset("����", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("��Ȯ���� ������ �����մϴ�"), 20, Wdcskillsinv);
				itemset("���", Material.ZOMBIE_HEAD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]","���Է½� ������� ���߽�ŵ�ϴ�","","���� ���� ���ٸ�","6������ ��� ��ȯ�մϴ�","(���ط��� ���� ������ ���մϴ�)","",ChatColor.BOLD+" X (1.8D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*2.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 21, Wdcskillsinv);
				itemset("ǳ��������", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("��Ƽ������ ��⸦ ä��ϴ�"), 23, Wdcskillsinv);
				itemset("�Ҹ�", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("��ü��Ż ���� �ڽ��� �һ��� ���ð��� �ʱ�ȭ�˴ϴ�"), 24, Wdcskillsinv);
				itemset("��������", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�","�ٷջ�޵� ���ð��� �����մϴ�"), 25, Wdcskillsinv);
				itemset("������ ������", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]","�޸��� + �����۴�����","��Ƽ���� ��,��� ȿ���� �����մϴ�","������ ����,��ȭ ȿ���� �ݴϴ�","������ ������ ��簡 ��ȯ�˴ϴ�","�������� 15�ʵڿ� �����մϴ�","������ ������ ���� �ִ� ���鿡��","�ڽŰ� ��Ƽ���� ���� ���ط��� �����"
						,"������ ���ݷ��� ����մϴ�","",ChatColor.BOLD+"33D + ���ط�*0.05"), 26, Wdcskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Wdcskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+wsd.SkillPoints.getOrDefault(p.getUniqueId(), 0),"","Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"), 35, Wdcskillsinv);

		}
		else {
			itemset("Fangs", Material.STONE_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Fangs.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"[Poison]","Sneaking + SwapHand","",ChatColor.BOLD+"5 X (0.4D + "+BigDecimal.valueOf(wsd.Fangs.getOrDefault(p.getUniqueId(), 0)*0.3).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 0, Wdcskillsinv);
			itemset("Bosou", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Bosou.getOrDefault(p.getUniqueId(), 0),"","Sneaking + RightClick", 
					"Increases Party Damage",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.05*(1+wsd.Bosou.getOrDefault(p.getUniqueId(), 0)*0.04)).setScale(2, RoundingMode.HALF_EVEN),
					"","Increases Party Armor",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.3+(1+wsd.Bosou.getOrDefault(p.getUniqueId(), 0)*0.045)).setScale(2, RoundingMode.HALF_EVEN), "Master LV.10"), 1, Wdcskillsinv);
			itemset("Harvest", Material.IRON_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Harvest.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"[Earth]","LeftClick", "If you reuse To Enemy", "Whose soul has not yet been absorbed", "you will immediately recover hp and hunger", "Increases Damage Enemy take 15%","",ChatColor.BOLD+" X (0.5D + "+BigDecimal.valueOf(wsd.Harvest.getOrDefault(p.getUniqueId(), 0)*0.56).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 2, Wdcskillsinv);
			itemset("Wraith", Material.GOLDEN_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Wraith.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"[Flame]","LeftClick + Sneaking","",ChatColor.BOLD+"20 X (0.2D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*0.32).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 3, Wdcskillsinv);
			itemset("AstralProjection", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.AstralProjection.getOrDefault(p.getUniqueId(), 0),"","SwapHand", "Sneaking To Quit Earlier", "Master LV.1"), 4, Wdcskillsinv);
			itemset("Incantation", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Incantation.getOrDefault(p.getUniqueId(), 0),"","RightClick + Sneaking", "Master LV.1"), 5, Wdcskillsinv);
			itemset("Resurrect", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("Totem won't be used when resurrect", "But Cooldown will be Applied", "Party Member also can be Resuurect", "Cooldown will be applied individual(25secs)", "Passive Skill"), 6, Wdcskillsinv);
			itemset("Legba", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Legba.getOrDefault(p.getUniqueId(), 0),"","Increases Earth Resistance & Damage","",ChatColor.BOLD+" + "+BigDecimal.valueOf(wsd.Legba.getOrDefault(p.getUniqueId(), 0)*0.585).setScale(2, RoundingMode.HALF_EVEN)), 7, Wdcskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("ZoglinCharge(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Wdcskillsinv);
				itemset("ForbiddenHex(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Wdcskillsinv);
				itemset("VengefulSpirit(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Wdcskillsinv);
				itemset("PhantomSwoop(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Wdcskillsinv);
				itemset("LightFooted(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Wdcskillsinv);
				itemset("TotemOfProtection(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Wdcskillsinv);
				itemset("CircleOfLife(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Wdcskillsinv);
				itemset("Baron Samedi(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Wdcskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("ZoglinCharge", Material.ZOGLIN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Use ZoglinCharge When Use Once More","(Damage Affected By Fangs)","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(wsd.Fangs.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, Wdcskillsinv);
				itemset("ForbiddenHex", Material.NETHER_BRICK, 0, 1, Arrays.asList("Use ForbiddenHex When Use Once More","Consume 20% of Each Party Health", "Give Regeneration Effect", "Set Party Invulnerable for"+ChatColor.BOLD+BigDecimal.valueOf(wsd.Bosou.getOrDefault(p.getUniqueId() ,0)*0.5).setScale(2, RoundingMode.HALF_EVEN)+ "secs") , 10, Wdcskillsinv);
				itemset("VengefulSpirit", Material.DRAGON_BREATH, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use VengefulSpirit When Use Once More","(Damage Affected By Harvest)","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(wsd.Harvest.getOrDefault(p.getUniqueId(), 0)*0.76).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, Wdcskillsinv);
				itemset("PhantomSwoop", Material.BAT_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Use PhantomSwoop When Use Once More","(Damage Affected By Wraith)","",ChatColor.BOLD+"20 X (0.3D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*0.342).setScale(2, RoundingMode.HALF_EVEN)+")"), 12, Wdcskillsinv);
				itemset("LightFooted", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("Get Speed&Jump Buff After AstralProjection"), 13, Wdcskillsinv);
				itemset("TotemOfProtection", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("Totem Will Heal Near By Party"), 14, Wdcskillsinv);
				itemset("CircleOfLife", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage", ChatColor.UNDERLINE+"[Poison]","Summon a Zombie when Enemy die" ,"Who affected by Harvest", 
						"Zombie will Fight For 10secs",
						"Zombie's Ability Affect By Skill levels","(Max 6 zombies)"
						), 16, Wdcskillsinv);
				itemset("Baron Samedi", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem","Prevent Party From Death", "Increases Party Damage","",ChatColor.BOLD+"+ "+BigDecimal.valueOf(p.getLevel()*0.5).setScale(2, RoundingMode.HALF_EVEN)), 17, Wdcskillsinv);

				itemset("FangsRush(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, Wdcskillsinv);
				itemset("Frighten(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, Wdcskillsinv);
				itemset("Sacrifice(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, Wdcskillsinv);
				itemset("TotemOfAbundant(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, Wdcskillsinv);
				itemset("Immortality(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 24, Wdcskillsinv);
				itemset("Attunement(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Wdcskillsinv);
				itemset("Eternal MagicCircle(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Wdcskillsinv);
			}
			else {
				itemset("ZoglinCharge", Material.ZOGLIN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Use ZoglinCharge When Use Once More","(Damage Affected By Fangs)","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(wsd.Fangs.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, Wdcskillsinv);
				itemset("ForbiddenHex", Material.NETHER_BRICK, 0, 1, Arrays.asList("Use ForbiddenHex When Use Once More","Consume 20% of Each Party Health", "Give Regeneration Effect", "Set Party Invulnerable for"+ChatColor.BOLD+BigDecimal.valueOf(wsd.Bosou.getOrDefault(p.getUniqueId() ,0)*0.5).setScale(2, RoundingMode.HALF_EVEN)+ "secs") , 10, Wdcskillsinv);
				itemset("VengefulSpirit", Material.DRAGON_BREATH, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use VengefulSpirit When Use Once More","(Damage Affected By Harvest)","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(wsd.Harvest.getOrDefault(p.getUniqueId(), 0)*0.76).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, Wdcskillsinv);
				itemset("PhantomSwoop", Material.BAT_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Use PhantomSwoop When Use Once More","(Damage Affected By Wraith)","",ChatColor.BOLD+"20 X (0.3D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*0.342).setScale(2, RoundingMode.HALF_EVEN)+")"), 12, Wdcskillsinv);
				itemset("LightFooted", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("Get Speed&Jump Buff After AstralProjection"), 13, Wdcskillsinv);
				itemset("TotemOfProtection", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("Totem Will Heal Near By Party"), 14, Wdcskillsinv);
				itemset("CircleOfLife", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage", ChatColor.UNDERLINE+"[Poison]","Summon a Zombie when Enemy die" ,"Who affected by Harvest", 
						"Zombie will Fight For 10secs",
						"Zombie's Ability Affect By Skill levels","(Max 6 zombies)"
						), 16, Wdcskillsinv);
				itemset("Baron Samedi", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem","Prevent Party From Death", "Increases Party Damage","",ChatColor.BOLD+"+ "+BigDecimal.valueOf(p.getLevel()*0.5).setScale(2, RoundingMode.HALF_EVEN)), 17, Wdcskillsinv);

				itemset("FangsRush", Material.MYCELIUM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Use FangsRush When Use Once More","Zoglin will Attack Instantly","Fangs Appear Where Zoglin Charged","Zombies Will Teleport to the Location","(Damage Affected By Fangs)","",ChatColor.BOLD+"10 X (0.7D + "+BigDecimal.valueOf(wsd.Fangs.getOrDefault(p.getUniqueId(), 0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")"), 19, Wdcskillsinv);
				itemset("Frighten", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("Hold Enemies When Use Harvest"), 20, Wdcskillsinv);
				itemset("Sacrifice", Material.ZOMBIE_HEAD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Explode Remaining Zombies","When Use Once More","","If There are No zombies","Summon 6 Zombies Instantly","(Damage Affected By Wraith)","",ChatColor.BOLD+" X (1.8D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*2.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 21, Wdcskillsinv);
				itemset("TotemOfAbundant", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("Totem Will Saturatify Near By Party"), 23, Wdcskillsinv);
				itemset("Immortality", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("Remove Your Resurrect's Cooldown", "When You Use AstralProjection"), 24, Wdcskillsinv);
				itemset("Attunement", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decrease Baron Samedi Cooldown"), 25, Wdcskillsinv);
				itemset("Eternal MagicCircle", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Sprinting + ThrowItem","Give Strength,Regeneration effect to party","Apply Weakness,Slowness to Enemies","Summons DeathKnight instead of zombie","Circle will explode after 15s", "Exploding power rises in proportion to","the amount of damage your party members", 
						"have done to your enemies on the circle","",ChatColor.BOLD+"33D + Damage*0.05"), 26, Wdcskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Wdcskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+wsd.SkillPoints.getOrDefault(p.getUniqueId(), 0),"","Click if you want to reset your skill's levels"), 35, Wdcskillsinv);

		}
		
		
		p.openInventory(Wdcskillsinv);
	}


}
