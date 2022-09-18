package io.github.chw3021.classes.chemist;



import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.classes.SkillsGui;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class CheSkillsGui extends SkillsGui{
	
	public void CheSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		CheSkillsData csd = new CheSkillsData(CheSkillsData.loadData(path +"/plugins/RPGskills/CheSkillsData.data"));
		Inventory Cheskillsinv = Bukkit.createInventory(null, 54, "Cheskills");
		
		Obtained.itemset(p, Cheskillsinv);

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("������" , Material.MAGMA_BLOCK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Coagulation.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[ȭ�� �迭]","�չٲٱ�","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.085*(1+csd.Coagulation.getOrDefault(p.getUniqueId(),0)*0.065)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 0, Cheskillsinv);
			itemset("����", Material.GLASS_BOTTLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Extraction.getOrDefault(p.getUniqueId(),0),"��ũ���� + ��Ŭ��","����� ���� ������ ���� �̷ο� ȿ���� ����ϴ�","�⺻: �ӵ�, ������","����: �߰�����" ,"�𵥵�: ��, ����","����: ������ �����, ����ȣ��","����: ����","��Ÿ: ���", "Master LV.1"), 1, Cheskillsinv);
			itemset("����" , Material.POTION, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Charge.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�͵� �迭]","��ũ���� + ��Ŭ��","�����߿��� ���������Դϴ�","����� ���� �����Ҽ� �ֽ��ϴ�","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.79*(1+csd.Charge.getOrDefault(p.getUniqueId(),0)*0.078)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 2, Cheskillsinv);
			itemset("�꼺����" , Material.LINGERING_POTION, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.AcidCloud.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�͵� �迭]","��Ŭ��", "Ȱ��ȭ/��Ȱ��ȭ ��ų","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.31*(1+csd.AcidCloud.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 3, Cheskillsinv);
			itemset("ȭ����" , Material.SPLASH_POTION, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.MolotovCocktail.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[ȭ�� �迭]","��Ŭ�� + ����","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.569*(1+csd.MolotovCocktail.getOrDefault(p.getUniqueId(),0)*0.0435)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 4, Cheskillsinv);
			itemset("�����Ӻ�" , Material.SLIME_BALL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.SlimeBall.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�͵� �迭]","�չٲٱ� + ��ũ����","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.45*(1+csd.SlimeBall.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 5, Cheskillsinv);
			itemset("������" , Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Poisonous.getOrDefault(p.getUniqueId(),0),"","���ݷ��� �����մϴ�","�� ȿ���� �鿪�� �˴ϴ�","�͵� �迭 ������ ����մϴ�","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1+csd.Poisonous.getOrDefault(p.getUniqueId(),0)*0.0336).setScale(2, RoundingMode.HALF_EVEN)), 6, Cheskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("�鸰(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 9, Cheskillsinv);
				itemset("����(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 10, Cheskillsinv);
				itemset("�꼺��ǳ(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 11, Cheskillsinv);
				itemset("�ߵ���(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 12, Cheskillsinv);
				itemset("������(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 13, Cheskillsinv);
				itemset("���׸���(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 14, Cheskillsinv);
				itemset("�͵�(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 15, Cheskillsinv);
				itemset("�Ű浶(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 17, Cheskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("�鸰", Material.WHITE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","���Է½� �鸰ź�� ��ô�մϴ�", "(���ط��� ������ ������ ����մϴ�)","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.29*(1+csd.Coagulation.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Cheskillsinv);
				itemset("����", Material.NETHERITE_PICKAXE, 0, 1, Arrays.asList("ȿ���� �߰��ǰ� �ֹ�ȿ���� ���ŵ˴ϴ�", "�⺻: ȭ������ �߰�","����: ���ݼӵ� �߰�" ,"�𵥵�: ��� �߰�","����: ����ü�� �� �߰�","������: ��� �߰�","��Ÿ: ������ �߰�"), 10, Cheskillsinv);
				itemset("�꼺��ǳ", Material.DRAGON_BREATH, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]", "���� ������ ����� 2�ʵ��� �꼺������ ��ȭ�մϴ�","�꼺������ ������ �߾����� ������","�ڽ��� �̵��ӵ��� �����մϴ�", "(���ط��� ���� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.58*(1+csd.Charge.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Cheskillsinv);
				itemset("�ߵ���", Material.SUSPICIOUS_STEW, 0, 1, Arrays.asList("�ܷ��ð��� ������ �����մϴ�"), 12, Cheskillsinv);
				itemset("������", Material.LIME_GLAZED_TERRACOTTA, 0, 1, Arrays.asList("�ǰݴ��� ���� ª�� �����մϴ�"), 13, Cheskillsinv);
				itemset("���׸���", Material.MAGMA_CREAM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","���Է½� ���׸����� ��ô�մϴ�", "(���ط��� �����Ӻ� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.9*(1+csd.SlimeBall.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Cheskillsinv);
				itemset("�͵�", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("��ü ���ط��� �����մϴ�","",ChatColor.GREEN+ "Ȱ��ȭ�� ���� ȿ�� �������� �����", ChatColor.GREEN+ "�޴����ذ� �����մϴ�", "0.2 + ������("+ p.getActivePotionEffects().size() + ") X 0.05"), 15, Cheskillsinv);
				itemset("�Ű浶", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]","��ũ���� + �����۴�����","�꼺������ �߰����ظ� �����ϴ�", "",ChatColor.BOLD+" X 0.5D"), 17, Cheskillsinv);
				
				itemset("ȯ����(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 18, Cheskillsinv);
				itemset("���(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 19, Cheskillsinv);
				itemset("�꼺��ź(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 20, Cheskillsinv);
				itemset("����(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 21, Cheskillsinv);
				itemset("ȭ��������(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 22, Cheskillsinv);
				itemset("�߱���(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 23, Cheskillsinv);
				itemset("��ü��ȭ(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 24, Cheskillsinv);
				itemset("�Ƹ��ٵ�(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 26, Cheskillsinv);
			}
			else {
				itemset("�鸰", Material.WHITE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","���Է½� �鸰ź�� ��ô�մϴ�", "(���ط��� ������ ������ ����մϴ�)","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.29*(1+csd.Coagulation.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Cheskillsinv);
				itemset("����", Material.NETHERITE_PICKAXE, 0, 1, Arrays.asList("ȿ���� �߰��ǰ� �ֹ�ȿ���� ���ŵ˴ϴ�", "�⺻: ȭ������ �߰�","����: ���ݼӵ� �߰�" ,"�𵥵�: ��� �߰�","����: ����ü�� �� �߰�","������: ��� �߰�","��Ÿ: ������ �߰�"), 10, Cheskillsinv);
				itemset("�꼺��ǳ", Material.DRAGON_BREATH, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]", "���� ������ ����� 2�ʵ��� �꼺������ ��ȭ�մϴ�","�꼺������ ������ �߾����� ������","�ڽ��� �̵��ӵ��� �����մϴ�", "(���ط��� ���� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.58*(1+csd.Charge.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Cheskillsinv);
				itemset("�ߵ���", Material.SUSPICIOUS_STEW, 0, 1, Arrays.asList("�ܷ��ð��� ������ �����մϴ�"), 12, Cheskillsinv);
				itemset("������", Material.LIME_GLAZED_TERRACOTTA, 0, 1, Arrays.asList("�ǰݴ��� ���� ª�� �����մϴ�"), 13, Cheskillsinv);
				itemset("���׸���", Material.MAGMA_CREAM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","���Է½� ���׸����� ��ô�մϴ�", "(���ط��� �����Ӻ� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.9*(1+csd.SlimeBall.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Cheskillsinv);
				itemset("�͵�", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("��ü ���ط��� �����մϴ�","",ChatColor.GREEN+ "Ȱ��ȭ�� ���� ȿ�� �������� �����", ChatColor.GREEN+ "�޴����ذ� �����մϴ�", "0.2 + ������("+ p.getActivePotionEffects().size() + ") X 0.05"), 15, Cheskillsinv);
				itemset("�Ű浶", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]","��ũ���� + �����۴�����","�꼺������ �߰����ظ� �����ϴ�", "",ChatColor.BOLD+" X 0.5D"), 17, Cheskillsinv);
				
				itemset("ȯ����", Material.YELLOW_GLAZED_TERRACOTTA, 0, 1, Arrays.asList("���Է½� ȯ������ ��ô�մϴ�"), 18, Cheskillsinv);
				itemset("���", Material.BEEHIVE, 0, 1, Arrays.asList("������� ���� ª�� �����մϴ�"), 19, Cheskillsinv);
				itemset("�꼺��ź", Material.TNT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]","���Է½� �꼺��ź�� ����մϴ�", "(���ط��� ���� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.5*(1+csd.Charge.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, Cheskillsinv);
				itemset("����", Material.LIME_SHULKER_BOX, 0, 1, Arrays.asList("������ �����մϴ�","������ �����ϴ� �߰� �������ظ� �����ϴ�","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.2*(1+csd.AcidCloud.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 21, Cheskillsinv);
				itemset("ȭ��������", Material.SUNFLOWER, 0, 1, Arrays.asList("8�������� ȭ������ ��ô�մϴ�"), 22, Cheskillsinv);
				itemset("�߱���", Material.GLOW_LICHEN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]","���Է½� �߱����� ��ô�մϴ�", "(���ط��� �����Ӻ� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+csd.SlimeBall.getOrDefault(p.getUniqueId(),0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 23, Cheskillsinv);
				itemset("��ü��ȭ", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�","�Ű浶 ��Ÿ���� �����մϴ�"), 24, Cheskillsinv);
				itemset("�Ƹ��ٵ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]","�޸��� + �����۴�����", "",ChatColor.BOLD+" X 19.0D + 10 X 0.6D"), 26, Cheskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Cheskillsinv);
			itemset("��ų����Ʈ" , Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+csd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"), 35, Cheskillsinv);
		
		}
		else {
			itemset("Napalm" , Material.MAGMA_BLOCK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Coagulation.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Flame]","SwapHand","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.085*(1+csd.Coagulation.getOrDefault(p.getUniqueId(),0)*0.065)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 0, Cheskillsinv);
			itemset("Extraction", Material.GLASS_BOTTLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Extraction.getOrDefault(p.getUniqueId(),0),"Sneaking + LeftClick","Get Positive Effect","Depending on Extracted EntityCategory","Base: Speed, Jump","Arthropod: Nightvision" ,"Undead: Strength, Resistance","Water: WaterBreath, DolphinsGrace","Enderman: Invisible","Else: Regeneration", "Master LV.1"), 1, Cheskillsinv);
			itemset("Charge" , Material.POTION, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Charge.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Poison]","Sneaking + Rightclick","Set Invulnerable While Charging","Quit Earlier If You Use Once More","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.79*(1+csd.Charge.getOrDefault(p.getUniqueId(),0)*0.078)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 2, Cheskillsinv);
			itemset("AcidCloud" , Material.LINGERING_POTION, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.AcidCloud.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Poison]","Rightclick", "On/Off skill","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.31*(1+csd.AcidCloud.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 3, Cheskillsinv);
			itemset("MolotovCocktail" , Material.SPLASH_POTION, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.MolotovCocktail.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Flame]","LeftClick + Jump","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.569*(1+csd.MolotovCocktail.getOrDefault(p.getUniqueId(),0)*0.0435)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 4, Cheskillsinv);
			itemset("SlimeBall" , Material.SLIME_BALL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.SlimeBall.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Poison]","SwapHand + Sneaking","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.45*(1+csd.SlimeBall.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 5, Cheskillsinv);
			itemset("Poisonous" , Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Poisonous.getOrDefault(p.getUniqueId(),0),"","Increases Damage","Be Immune to Poison Effect","Increases Posion Resistance","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1+csd.Poisonous.getOrDefault(p.getUniqueId(),0)*0.0336).setScale(2, RoundingMode.HALF_EVEN)), 6, Cheskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("WP(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Cheskillsinv);
				itemset("Adaptation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Cheskillsinv);
				itemset("AcidStorm(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Cheskillsinv);
				itemset("Addicting(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Cheskillsinv);
				itemset("Lime(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Cheskillsinv);
				itemset("MagmaBall(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Cheskillsinv);
				itemset("Venom(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 15, Cheskillsinv);
				itemset("VX(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Cheskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("WP", Material.WHITE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Throw WP When Use Once More", "(Damage Affected By Napalm)","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.29*(1+csd.Coagulation.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Cheskillsinv);
				itemset("Adaptation", Material.NETHERITE_PICKAXE, 0, 1, Arrays.asList("Give More Effect & Remove Confusion", "Base: +FireResistance","Arthropod: +AttackSpeed" ,"Undead: +Absorbtion","Water: +Conduit Power","Enderman: +Luck","Else: +Saturation"), 10, Cheskillsinv);
				itemset("AcidStorm", Material.DRAGON_BREATH, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Activate For 2s When Use Once More", "After Charge Quited", "AcidCloud Gathers Enemies to Middle of Cloud","Decreases Speed While Storm Activated", "(Damage Affected By Charge)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.58*(1+csd.Charge.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Cheskillsinv);
				itemset("Addicting", Material.SUSPICIOUS_STEW, 0, 1, Arrays.asList("Increases Duration & Range"), 12, Cheskillsinv);
				itemset("Glue", Material.LIME_GLAZED_TERRACOTTA, 0, 1, Arrays.asList("Hold Hit Enemies Shortly"), 13, Cheskillsinv);
				itemset("MagmaBall", Material.MAGMA_CREAM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Throw Magma Ball When Use Once More", "(Damage Affected By SlimeBall)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+csd.SlimeBall.getOrDefault(p.getUniqueId(),0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Cheskillsinv);
				itemset("Venom", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage", "Decreases received damage in", "Propotion to Number of PotionEffectTypes","0.2 + (Number of Types["+p.getActivePotionEffects().size() +"]X 0.05)"), 15, Cheskillsinv);
				itemset("VX", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Sneaking + ThrowItem","Acid clouds will cause additional damage", "",ChatColor.BOLD+" X 0.5D"), 17, Cheskillsinv);
				
				itemset("Hallucinogen(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, Cheskillsinv);
				itemset("Sting(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, Cheskillsinv);
				itemset("AcidBomb(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, Cheskillsinv);
				itemset("Necrosis(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, Cheskillsinv);
				itemset("CocktailFlower(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, Cheskillsinv);
				itemset("GlowingBall(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, Cheskillsinv);
				itemset("Bioreformation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 24, Cheskillsinv);
				itemset("Armageddon(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Cheskillsinv);
			}
			else {
				itemset("WP", Material.WHITE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Throw WP When Use Once More", "(Damage Affected By Napalm)","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.29*(1+csd.Coagulation.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Cheskillsinv);
				itemset("Adaptation", Material.NETHERITE_PICKAXE, 0, 1, Arrays.asList("Give More Effect & Remove Confusion", "Base: +FireResistance","Arthropod: +AttackSpeed" ,"Undead: +Absorbtion","Water: +Conduit Power","Enderman: +Luck","Else: +Saturation"), 10, Cheskillsinv);
				itemset("AcidStorm", Material.DRAGON_BREATH, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Activate For 2s When Use Once More", "After Charge Quited", "AcidCloud Gathers Enemies to Middle of Cloud","Decreases Speed While Storm Activated", "(Damage Affected By Charge)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.58*(1+csd.Charge.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Cheskillsinv);
				itemset("Addicting", Material.SUSPICIOUS_STEW, 0, 1, Arrays.asList("Increases Duration & Range"), 12, Cheskillsinv);
				itemset("Glue", Material.LIME_GLAZED_TERRACOTTA, 0, 1, Arrays.asList("Hold Hit Enemies Shortly"), 13, Cheskillsinv);
				itemset("MagmaBall", Material.MAGMA_CREAM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Throw Magma Ball When Use Once More", "(Damage Affected By SlimeBall)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+csd.SlimeBall.getOrDefault(p.getUniqueId(),0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Cheskillsinv);
				itemset("Venom", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage", "Decreases received damage in", "Propotion to Number of PotionEffectTypes","0.2 + (Number of Types["+p.getActivePotionEffects().size() +"]X 0.05)"), 15, Cheskillsinv);
				itemset("VX", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Sneaking + ThrowItem","Acid clouds will cause additional damage", "",ChatColor.BOLD+" X 0.5D"), 17, Cheskillsinv);
				
				itemset("Hallucinogen", Material.YELLOW_GLAZED_TERRACOTTA, 0, 1, Arrays.asList("Throw Hallucinogen When Use Once More"), 18, Cheskillsinv);
				itemset("Sting", Material.BEEHIVE, 0, 1, Arrays.asList("Hold Hit Enemies Shortly"), 19, Cheskillsinv);
				itemset("AcidBomb", Material.TNT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Use AcidBomb When Use Once More", "(Damage Affected By Charge)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.5*(1+csd.Charge.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, Cheskillsinv);
				itemset("Necrosis", Material.LIME_SHULKER_BOX, 0, 1, Arrays.asList("Increases Range","Extra Damage Ignoring Armor","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.2*(1+csd.AcidCloud.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 21, Cheskillsinv);
				itemset("CocktailFlower", Material.SUNFLOWER, 0, 1, Arrays.asList("Throw Cocktail 8 directions"), 22, Cheskillsinv);
				itemset("GlowingBall", Material.GLOW_LICHEN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Throw Glowing Ball When Use Once More", "(Damage Affected By SlimeBall)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+csd.SlimeBall.getOrDefault(p.getUniqueId(),0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 23, Cheskillsinv);
				itemset("Bioreformation", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decreases VX Cooldown"), 24, Cheskillsinv);
				itemset("Armageddon", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Sprinting + ThrowItem", "",ChatColor.BOLD+" X 19.0D + 10 X 0.6D"), 26, Cheskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Cheskillsinv);
			itemset("SkillPoints" , Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+csd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, Cheskillsinv);
		
		}
		
		p.openInventory(Cheskillsinv);
	}


}
