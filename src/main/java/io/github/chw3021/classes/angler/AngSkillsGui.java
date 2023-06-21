package io.github.chw3021.classes.angler;



import java.util.Arrays;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.classes.SkillsGui;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class AngSkillsGui extends SkillsGui{
	
	
	public void AngSkillsinv(Player p)
	{
		Inventory Angskillsinv = Bukkit.createInventory(null, 54, "Angskills");
	    String path = new File("").getAbsolutePath();
		AngSkillsData fsd = new AngSkillsData(AngSkillsData.loadData(path +"/plugins/RPGskills/AngSkillsData.data"));

		Obtained.itemset(p, Angskillsinv);
		
		int bait = fsd.Bait.getOrDefault(p.getUniqueId(),0);
		int fishing = fsd.Fishing.getOrDefault(p.getUniqueId(),0);
		int whipping = fsd.Whipping.getOrDefault(p.getUniqueId(),0);
		int coralliquor = fsd.CoralLiquor.getOrDefault(p.getUniqueId(),0);
		int root = fsd.CoralRoots.getOrDefault(p.getUniqueId(),0);
		int dance = fsd.DrunkenDance.getOrDefault(p.getUniqueId(),0);
		int tech = fsd.Technic.getOrDefault(p.getUniqueId(),0);
		
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			
			itemset("�̳�", Material.CARROT_ON_A_STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bait,"",ChatColor.UNDERLINE+"[�� �迭]","��ũ���� + ���ô������","�ֺ� ��Ƽ������ ü���� ȸ���մϴ�","",ChatColor.BOLD+"10 X (0.3D + "+BigDecimal.valueOf(bait*0.35).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 0, Angskillsinv);
			itemset("����", Material.FISHING_ROD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fishing,"",ChatColor.UNDERLINE+"[�ٶ� �迭]","���ô븦 ȸ���Ҷ� �ֺ� ������ ���� ���ϴ�", "���� ���� ���ư��� �ӵ��� �����մϴ�", "�Ϲ� ������ ���ð��� �����մϴ�","",ChatColor.BOLD+"X 0.46D" ,"Master LV.1"), 1, Angskillsinv);
			itemset("���ô��", Material.STRING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+whipping,"",ChatColor.UNDERLINE+"[�ٶ� �迭]","��Ŭ��","",ChatColor.BOLD+" (X 0.65D + "+BigDecimal.valueOf(whipping*0.35).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 2, Angskillsinv);
			itemset("��ȣ��", Material.POTION, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+coralliquor,"","��ũ���� + �չٲٱ�", 
					"��Ƽ������ "+ BigDecimal.valueOf(coralliquor*0.2d).setScale(2, RoundingMode.HALF_EVEN) + "�ʵ��� �������·� ����ϴ�"
					, "��Ƽ������ ���ط��� "+demical(1+coralliquor*0.016* (Proficiency.getpro(p)>=2? 2:1))+"��ŭ ����մϴ�"
					, "��Ƽ������ �޴� ���ط��� "+demical(coralliquor*0.01525* (Proficiency.getpro(p)>=2? 2:1))+"��ŭ �����մϴ�"
					, "Master LV.20"), 3, Angskillsinv);
			itemset("��ȣ�Ѹ�", Material.BUBBLE_CORAL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+root,"",ChatColor.UNDERLINE+"[�� �迭]","��ũ���� + ��Ŭ��","",ChatColor.BOLD+"4 X (0.4443D + "+BigDecimal.valueOf(root*0.43).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 4, Angskillsinv);
			itemset("���ְ���", Material.SEA_PICKLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+dance,"",ChatColor.UNDERLINE+"[�ٶ� �迭]","���� + �չٲٱ�","",ChatColor.BOLD+"10 X (0.3D + "+BigDecimal.valueOf(dance*0.33).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 5, Angskillsinv);
			itemset("�����", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tech,"","���ݷ��� �����մϴ�", "������ ������ ȿ���� ����ϴ�","",ChatColor.BOLD+"+ "+BigDecimal.valueOf(tech*0.55).setScale(2, RoundingMode.HALF_EVEN)), 7, Angskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 9, Angskillsinv);
				itemset("��񰳼�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 10, Angskillsinv);
				itemset("�ΰ����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 11, Angskillsinv);
				itemset("Ȱ����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 12, Angskillsinv);
				itemset("��ȣ����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 13, Angskillsinv);
				itemset("�뺸(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 14, Angskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 16, Angskillsinv);
				itemset("�ٴٽ��� ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 17, Angskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("����", Material.WARPED_FUNGUS_ON_A_STICK, 0, 1, Arrays.asList("�������� �ݴϴ�", "������ �����մϴ�", ChatColor.UNDERLINE+"[�ٶ� �迭]","ȸ���� �ֺ����� ����ɴϴ�","",ChatColor.BOLD+"X 0.8D + "+BigDecimal.valueOf(bait*1.1).setScale(2, RoundingMode.HALF_EVEN)), 9, Angskillsinv);
				itemset("��񰳼�", Material.FISHING_ROD, 0, 1, Arrays.asList("���� ���ð��� �����մϴ�"), 10, Angskillsinv);
				itemset("�ΰ����", Material.STRING, 0, 1, Arrays.asList("�ֺ� ��Ƽ������ ġ���մϴ�"), 11, Angskillsinv);
				itemset("Ȱ����", Material.SPLASH_POTION, 0, 1, Arrays.asList("��,��� ȿ���� �ݴϴ�"), 12, Angskillsinv);
				itemset("��ȣ����", Material.BRAIN_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ��ȣ������ ����մϴ�","��ȣ���� ���� �Ʊ����� �������°� �˴ϴ�", "(���ط��� ��ȣ�Ѹ� ������ ����մϴ�)","",ChatColor.BOLD+"X 1.1D + "+ demical(root*1.5)), 13, Angskillsinv);
				itemset("�뺸", Material.SEAGRASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� �뺸�� ����մϴ�", "(���ط��� ���ְ��� ������ ����մϴ�)","",ChatColor.BOLD+"X 0.8D + "+BigDecimal.valueOf(dance*0.8).setScale(2, RoundingMode.HALF_EVEN)), 14, Angskillsinv);
				itemset("����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("��ü ���ݷ��� �����մϴ�"), 16, Angskillsinv);
				itemset("����", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","��������� ��ũ���� + num4","��Ƽ������ �طο� ȿ���� �����ϰ�","5�ʰ� �������·� ����ϴ�",ChatColor.BOLD+"50 X 0.1D + 4.0D", ""
						,"������ �޴����ط��� �����մϴ�",ChatColor.BOLD+"10% + " + demical(p.getLevel()*0.2)+" �߰�������"), 17, Angskillsinv);
				
				itemset("���ձ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 21, Angskillsinv);
				itemset("������ź(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 22, Angskillsinv);
				itemset("���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 23, Angskillsinv);
				itemset("��Ż(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 25, Angskillsinv);
				itemset("������ü(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 26, Angskillsinv);
			}
			else {
				itemset("����", Material.WARPED_FUNGUS_ON_A_STICK, 0, 1, Arrays.asList("�������� �ݴϴ�", "������ �����մϴ�", ChatColor.UNDERLINE+"[�ٶ� �迭]","ȸ���� �ֺ����� ����ɴϴ�","",ChatColor.BOLD+"X 0.8D + "+BigDecimal.valueOf(bait*1.1).setScale(2, RoundingMode.HALF_EVEN)), 9, Angskillsinv);
				itemset("��񰳼�", Material.FISHING_ROD, 0, 1, Arrays.asList("���� ���ð��� �����մϴ�"), 10, Angskillsinv);
				itemset("�ΰ����", Material.STRING, 0, 1, Arrays.asList("�ֺ� ��Ƽ������ ġ���մϴ�"), 11, Angskillsinv);
				itemset("Ȱ����", Material.SPLASH_POTION, 0, 1, Arrays.asList("��,��� ȿ���� �ݴϴ�"), 12, Angskillsinv);
				itemset("��ȣ����", Material.BRAIN_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ��ȣ������ ����մϴ�","��ȣ���� ���� �Ʊ����� �������°� �˴ϴ�", "(���ط��� ��ȣ�Ѹ� ������ ����մϴ�)","",ChatColor.BOLD+"X 1.1D + "+BigDecimal.valueOf(root*1.5).setScale(2, RoundingMode.HALF_EVEN)), 13, Angskillsinv);
				itemset("�뺸", Material.SEAGRASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� �뺸�� ����մϴ�", "(���ط��� ���ְ��� ������ ����մϴ�)","",ChatColor.BOLD+"X 0.8D + "+BigDecimal.valueOf(dance*0.8).setScale(2, RoundingMode.HALF_EVEN)), 14, Angskillsinv);
				itemset("����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("��ü ���ݷ��� �����մϴ�"), 16, Angskillsinv);
				itemset("����", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","��������� ��ũ���� + num4","��Ƽ������ �طο� ȿ���� �����ϰ�","5�ʰ� �������·� ����ϴ�",ChatColor.BOLD+"50 X 0.1D + 4.0D", ""
						,"������ �޴����ط��� �����մϴ�",ChatColor.BOLD+"10% + " + demical(p.getLevel()*0.2)+" �߰�������"), 17, Angskillsinv);
				
				itemset("���ձ�", Material.FIRE_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","���Է½� ���ձ⸦ ����մϴ�", "(���ط��� ��ȣ�� ������ ����մϴ�)","",ChatColor.BOLD+"X 2.1D + "+BigDecimal.valueOf(coralliquor*2.5).setScale(2, RoundingMode.HALF_EVEN)), 21, Angskillsinv);
				itemset("������ź", Material.FIRE_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","���Է½� ������ź�� ����մϴ�", "(���ط��� ��ȣ�Ѹ� ������ ����մϴ�)","",ChatColor.BOLD+"X 1.4D + "+BigDecimal.valueOf(root*1.75).setScale(2, RoundingMode.HALF_EVEN)), 22, Angskillsinv);
				itemset("���", Material.SEAGRASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ����� ����մϴ�", "(���ط��� ���ְ��� ������ ����մϴ�)","",ChatColor.BOLD+"X 1.3D + "+ BigDecimal.valueOf(dance*1.3).setScale(2, RoundingMode.HALF_EVEN)), 23, Angskillsinv);
				itemset("��Ż", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�","���� ��Ÿ���� �����մϴ�"), 25, Angskillsinv);
				itemset("������ü", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","��������� ��ũ���� + num5","��Ƽ������ �طο� ȿ���� �����ϰ�","5�ʰ� �������·� ����ϴ�", "",ChatColor.BOLD+"X 40D"), 26, Angskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Angskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"), 35, Angskillsinv);

		
	    }
		else {

			
			itemset("Bait", Material.CARROT_ON_A_STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bait,"",ChatColor.UNDERLINE+"[Water]","Sneaking + Throw","Hear Near by Party","",ChatColor.BOLD+"10 X (0.3D + "+BigDecimal.valueOf(bait*0.35).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 0, Angskillsinv);
			itemset("Fishing", Material.FISHING_ROD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fishing,"",ChatColor.UNDERLINE+"[Wind]","Pull Near By Enemies when retrieve", "Increases Bobber Speed", "Decreases Waiting time when Fishing","",ChatColor.BOLD+"X 0.46D" ,"Master LV.1"), 1, Angskillsinv);
			itemset("Whipping", Material.STRING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+whipping,"",ChatColor.UNDERLINE+"[Wind]","LeftClick","",ChatColor.BOLD+" (X 0.65D + "+BigDecimal.valueOf(whipping*0.35).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 2, Angskillsinv);
			itemset("CoralLiquor", Material.POTION, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+coralliquor,"","Sneaking + SwapHand", "Set Your Party Invulnerable for "+ BigDecimal.valueOf(coralliquor*0.2d).setScale(2, RoundingMode.HALF_EVEN) +  "s", "Increases Party Damage",
				"",ChatColor.BOLD+"X "+BigDecimal.valueOf(1+coralliquor*0.016* (Proficiency.getpro(p)>=2? 2:1)).setScale(2, RoundingMode.HALF_EVEN)
				, "Decreases Taking Damage"+demical(fsd.CoralLiquor.get(p.getUniqueId())*0.01525* (Proficiency.getpro(p)>=2? 2:1)), "Master LV.20"), 3, Angskillsinv);
			
			itemset("CoralRoots", Material.BUBBLE_CORAL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+root,"",ChatColor.UNDERLINE+"[Water]","Sneaking + Hit","",ChatColor.BOLD+"4 X (0.4443D + "+BigDecimal.valueOf(root*0.43).setScale(2, RoundingMode.HALF_EVEN)+")"
					, "Master Lv.50"), 4, Angskillsinv);
			itemset("DrunkenDance", Material.SEA_PICKLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+dance,"",ChatColor.UNDERLINE+"[Wind]","Jump + SwapHand","",ChatColor.BOLD+"10 X (0.3D + "+BigDecimal.valueOf(dance*0.33).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 5, Angskillsinv);
			itemset("Technic", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tech,"","Increases Damage", "Get Water Ability When Swim","",ChatColor.BOLD+"+ "+BigDecimal.valueOf(tech*0.55).setScale(2, RoundingMode.HALF_EVEN)), 7, Angskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("Paste bait(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Angskillsinv);
				itemset("Fishing tackle(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Angskillsinv);
				itemset("Therapy(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Angskillsinv);
				itemset("Energizer(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Angskillsinv);
				itemset("CoralPrison(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Angskillsinv);
				itemset("DrunkenDash(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Angskillsinv);
				itemset("LaidBack(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Angskillsinv);
				itemset("Boat Of The Sea God(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Angskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Paste bait", Material.WARPED_FUNGUS_ON_A_STICK, 0, 1, Arrays.asList("Give Saturation Effect", "Increases Range", ChatColor.UNDERLINE+"[Wind]","Pull Hit Enemies When Retreive","",ChatColor.BOLD+"X 0.8D + "+BigDecimal.valueOf(bait*1.1).setScale(2, RoundingMode.HALF_EVEN)), 9, Angskillsinv);
				itemset("Fishing tackle", Material.FISHING_ROD, 0, 1, Arrays.asList("Decreases Cooldown"), 10, Angskillsinv);
				itemset("Therapy", Material.STRING, 0, 1, Arrays.asList("Heal Near By Party"), 11, Angskillsinv);
				itemset("Energizer", Material.SPLASH_POTION, 0, 1, Arrays.asList("Give Strenghth & Regen Effect"), 12, Angskillsinv);
				itemset("CoralPrison", Material.BRAIN_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Summon CoralPrison When Use Once More", "(Damage Affected By CoralRoots)","",ChatColor.BOLD+"X 1.1D + "+ demical(root*1.5)), 13, Angskillsinv);
				itemset("DrunkenDash", Material.SEAGRASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Dash When Use Once More", "(Damage Affected By DrunkenDance)","",ChatColor.BOLD+"X 0.8D + "+BigDecimal.valueOf(dance*0.8).setScale(2, RoundingMode.HALF_EVEN)), 14, Angskillsinv);
				itemset("LaidBack", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16, Angskillsinv);
				itemset("Fully Load", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Sneaking + num4 while CombatMode","Removes All Negetive Effects of Party","And Set Invulnerable for 5 seconds","",ChatColor.BOLD+"50 X 0.1D + 4.0D"), 17, Angskillsinv);
				
				itemset("Spout(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, Angskillsinv);
				itemset("DrunkenSmash(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, Angskillsinv);
				itemset("Liberation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Angskillsinv);
				itemset("Self and Other Oneness(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Angskillsinv);
			}
			else {
				itemset("Paste bait", Material.WARPED_FUNGUS_ON_A_STICK, 0, 1, Arrays.asList("Give Saturation Effect", "Increases Range", ChatColor.UNDERLINE+"[Wind]","Pull Hit Enemies When Retreive","",ChatColor.BOLD+"X 0.8D + "+BigDecimal.valueOf(bait*1.1).setScale(2, RoundingMode.HALF_EVEN)), 9, Angskillsinv);
				itemset("Fishing tackle", Material.FISHING_ROD, 0, 1, Arrays.asList("Decreases Cooldown"), 10, Angskillsinv);
				itemset("Therapy", Material.STRING, 0, 1, Arrays.asList("Heal Near By Party"), 11, Angskillsinv);
				itemset("Energizer", Material.SPLASH_POTION, 0, 1, Arrays.asList("Give Strenghth & Regen Effect"), 12, Angskillsinv);
				itemset("CoralPrison", Material.BRAIN_CORAL_BLOCK, 0, 1, Arrays.asList("Increases Range Instantly"), 13, Angskillsinv);
				itemset("DrunkenDash", Material.SEAGRASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Dash When Use Once More", "(Damage Affected By DrunkenDance)","",ChatColor.BOLD+"X 0.8D + "+BigDecimal.valueOf(dance*0.8).setScale(2, RoundingMode.HALF_EVEN)), 14, Angskillsinv);
				itemset("LaidBack", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16, Angskillsinv);
				itemset("Fully Load", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Sneaking + num4 while CombatMode","Removes All Negetive Effects of Party","And Set Invulnerable for 5 seconds","",ChatColor.BOLD+"50 X 0.1D + 4.0D"), 17, Angskillsinv);
				
				itemset("Spout", Material.FIRE_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Spout Liquor When Use Once More", "Amplify CoralLiquor Buff", "(Damage Affected By CoralLiquor)","",ChatColor.BOLD+"X 2.1D + "+BigDecimal.valueOf(coralliquor*2.5).setScale(2, RoundingMode.HALF_EVEN)), 21, Angskillsinv);
				itemset("DrunkenSmash", Material.SEAGRASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Smash When Use Once More", "(Damage Affected By DrunkenDance)","",ChatColor.BOLD+"X 1.3D + "+ BigDecimal.valueOf(dance*1.3).setScale(2, RoundingMode.HALF_EVEN)), 23, Angskillsinv);
				itemset("Liberation", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decreases Fully Load Cooldown"), 25, Angskillsinv);
				itemset("Self and Other Oneness", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Sneaking + num5 while CombatMode","Removes All Negetive Effects of Party","And Set Invulnerable for 5 seconds","",ChatColor.BOLD+"X 40D"), 26, Angskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Angskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, Angskillsinv);

		}
		p.openInventory(Angskillsinv);
	}


}
