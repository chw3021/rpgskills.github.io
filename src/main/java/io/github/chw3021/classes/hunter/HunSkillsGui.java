package io.github.chw3021.classes.hunter;



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

public class HunSkillsGui extends SkillsGui{
	

	
	public void Hunskillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		HunSkillsData hsd = new HunSkillsData(HunSkillsData.loadData(path +"/plugins/RPGskills/HunSkillsData.data"));
		Inventory Hunskillsinv = Bukkit.createInventory(null, 54, "Hunskills");
		Obtained.itemset(p, Hunskillsinv);
		final int at = hsd.Atrocity.getOrDefault(p.getUniqueId(),0);

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("�Ͻ�", Material.BOOK, 0, 1, Arrays.asList("���� �Ĺ��� �����ϸ� 50%�߰� ���ظ� �ݴϴ�", "����ġ������ �������� ������ �������ϴ�"),hsd.Backattack.getOrDefault(p.getUniqueId(), 0),1,1,0.5,1, 0, Hunskillsinv);
			itemset("�׹���ô", Material.BOOK, 0, 1, Arrays.asList("��ũ���� + ��Ŭ��"),hsd.Webthrow.getOrDefault(p.getUniqueId(), 0),1,0,0.5,1, 1, Hunskillsinv);
			itemset("ȸ��", Material.BOOK, 0, 1, Arrays.asList("�չٲٱ�","���ϵ������� �����մϴ�"),hsd.Dodge.getOrDefault(p.getUniqueId(), 0),1,0,0.5,1, 2, Hunskillsinv);
			itemset("���", Material.BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","��Ŭ��", "������ ���ظ� �ָ� ��Ȱ��ȭ�˴ϴ�", "�� ��ġ�� ���ð��� 2�� �����մϴ�"),hsd.HuntingStart.getOrDefault(p.getUniqueId(), 0),1,1.0,0.055,50, 3, Hunskillsinv);
			itemset("����", Material.BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","��ũ���� + ����","���� 0.5�ʵ��� ������ŵ�ϴ�"),hsd.Daze.getOrDefault(p.getUniqueId(), 0),1,0.8,0.035,50, 4, Hunskillsinv);
			itemset("�ΰ���м�", Material.BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���� + ����", "�ΰ��� �м⸦ ������", "���ط��� 50%�����մϴ�"),hsd.SkullCrusher.getOrDefault(p.getUniqueId(), 0),1,1.1,0.06,50, 5, Hunskillsinv);
			itemset("���", Material.BOOK, 0, 1, Arrays.asList("��ũ���� + �չٲٱ� �� Ȱ��ȭ/��Ȱ��ȭ"),hsd.Climb.getOrDefault(p.getUniqueId(), 0),1,0,0.5,1, 6, Hunskillsinv);
			itemset("�ؾǹ���", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+at,"","��� ����� ���ѹ��� ������ ��ȭ�մϴ�","�ִ����� ���°� �ƴҶ���", "������ ���ظ� �����ϴ�","��⿡ �鿪�� �˴ϴ�", "�ִ����� ���·� ���ݽ�", "���� �ִ�ü����"+ Math.round(5+0.02*at)  +"% �� �ش��ϴ� �߰����ظ� �ݴϴ�","���ݷ��� �����մϴ�",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.15*(1+at*0.0576)).setScale(2, RoundingMode.HALF_EVEN)), 7, Hunskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("�׹��߰�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 10, Hunskillsinv);
				itemset("�����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 11, Hunskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 12, Hunskillsinv);
				itemset("���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 13, Hunskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 14, Hunskillsinv);
				itemset("����ݱ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 15, Hunskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 16, Hunskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 17, Hunskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("�׹��߰�", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("�׹��� ������ �����մϴ�"), 10, Hunskillsinv);
				itemset("�����", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("�� óġ�� ���ð��� �ʱ�ȭ �˴ϴ�"), 11, Hunskillsinv);
				itemset("����", Material.ACTIVATOR_RAIL, 0, 1, Arrays.asList("���� ������ �����ϴ°� �����ε� ���ð��� �����մϴ�"), 12, Hunskillsinv);
				itemset("���", Material.CREEPER_HEAD, 0, 1, Arrays.asList("�ֺ� ���鵵 ���ݽ�ŵ�ϴ�"), 13, Hunskillsinv);
				itemset("����", Material.SKELETON_SKULL, 0, 1, Arrays.asList("�� óġ�� �ֺ������� �����մϴ�"), 14, Hunskillsinv);
				itemset("����ݱ�", Material.CHAINMAIL_LEGGINGS, 0, 1, Arrays.asList("��ũ���� ������ �������� �����˴ϴ�","�ִ� 10"), 15, Hunskillsinv);
				itemset("����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� �����մϴ�"), 16, Hunskillsinv);
				itemset("����", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("��ũ���� + �����۴�����", "���ݰ� �ΰ���м��� ���ð��� �ʱ�ȭ�˴ϴ�","",ChatColor.BOLD+"X 27.1D"), 17, Hunskillsinv);

				itemset("�׹�ȸ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 19, Hunskillsinv);
				itemset("�ڼ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 20, Hunskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 21, Hunskillsinv);
				itemset("���ó��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 22, Hunskillsinv);
				itemset("������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 24, Hunskillsinv);
				itemset("��������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 25, Hunskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 26, Hunskillsinv);
			}
			else {
				itemset("�׹��߰�", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("�׹��� ������ �����մϴ�"), 10, Hunskillsinv);
				itemset("�����", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("�� óġ�� ���ð��� �ʱ�ȭ �˴ϴ�"), 11, Hunskillsinv);
				itemset("����", Material.ACTIVATOR_RAIL, 0, 1, Arrays.asList("���� ������ �����ϴ°� �����ε� ���ð��� �����մϴ�"), 12, Hunskillsinv);
				itemset("���", Material.CREEPER_HEAD, 0, 1, Arrays.asList("�ֺ� ���鵵 ���ݽ�ŵ�ϴ�"), 13, Hunskillsinv);
				itemset("����", Material.SKELETON_SKULL, 0, 1, Arrays.asList("�� óġ�� �ֺ������� �����մϴ�"), 14, Hunskillsinv);
				itemset("����ݱ�", Material.CHAINMAIL_LEGGINGS, 0, 1, Arrays.asList("��ũ���� ������ �������� �����˴ϴ�","�ִ� 10"), 15, Hunskillsinv);
				itemset("����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� �����մϴ�"), 16, Hunskillsinv);
				itemset("����", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("��ũ���� + �����۴�����", "���ݰ� �ΰ���м��� ���ð��� �ʱ�ȭ�˴ϴ�","",ChatColor.BOLD+"X 27.1D"), 17, Hunskillsinv);

				itemset("�׹�ȸ��", Material.FERMENTED_SPIDER_EYE, 0, 1, Arrays.asList("����� �׹��� ���� ������ ����ɴϴ�"), 19, Hunskillsinv);
				itemset("�ڼ�", Material.NETHERITE_CHESTPLATE, 0, 1, Arrays.asList("ȸ������ ���� 1ȸ�� ������ ��ȭ�˴ϴ�"), 20, Hunskillsinv);
				itemset("����", Material.NETHERITE_AXE, 0, 1, Arrays.asList("����� Ȱ��ȭ/��Ȱ��ȭ ����� ����˴ϴ�", "���� �����ϸ� ���ð��� ����˴ϴ�"), 21, Hunskillsinv);
				itemset("���ó��", Material.NETHERITE_AXE, 0, 1, Arrays.asList("�� óġ�� ���, ����, �ΰ���м���", "������ð��� �ʱ�ȭ �˴ϴ�", "(����� �ڵ����� Ȱ��ȭ�˴ϴ�)"), 22, Hunskillsinv);
				itemset("������", Material.GOLDEN_CARROT, 0, 1, Arrays.asList("��� �طο� ȿ���� �鿪�� �˴ϴ�"), 24, Hunskillsinv);
				itemset("��������", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�","������ ���ð��� �����մϴ�","����� �׻� �ߵ��˴ϴ�"), 25, Hunskillsinv);
				itemset("����", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","�޸��� + �����۴�����", "���ݰ� �ΰ���м��� ���ð��� �ʱ�ȭ�˴ϴ�","",ChatColor.BOLD+"X 33.2D"), 26, Hunskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Hunskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+hsd.SkillPoints.getOrDefault(p.getUniqueId(), 0),"","Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"), 35, Hunskillsinv);
		
		}
		else {
			itemset("Backattack", Material.BOOK, 0, 1, Arrays.asList("+50% Damage when you hit enemy's back", "Higher Explevel gets Better hit detection"),hsd.Backattack.getOrDefault(p.getUniqueId(), 0),1,1,0.5,1, 0, Hunskillsinv);
			itemset("Webthrowing", Material.BOOK, 0, 1, Arrays.asList("Sneaking + RightClick"),hsd.Webthrow.getOrDefault(p.getUniqueId(), 0),1,0,0.5,1, 1, Hunskillsinv);
			itemset("Dodge", Material.BOOK, 0, 1, Arrays.asList("SwapHand","Decreases Falling Damage"),hsd.Dodge.getOrDefault(p.getUniqueId(), 0),1,0,0.5,1, 2, Hunskillsinv);
			itemset("Hunting", Material.BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Rightclick", "Disabled when you damage to enemy", "Reduce cooldown 2s if you kill enemy"),hsd.HuntingStart.getOrDefault(p.getUniqueId(), 0),1,1.0,0.055,50, 3, Hunskillsinv);
			itemset("Daze", Material.BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Sneaking + Hit","Stuns Enemy for 0.5s"),hsd.Daze.getOrDefault(p.getUniqueId(), 0),1,0.8,0.035,50, 4, Hunskillsinv);
			itemset("SkullCrusher", Material.BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Jump + Hit", "Increases Damage 50%","Excepting for SkullCrusher's"),hsd.SkullCrusher.getOrDefault(p.getUniqueId(), 0),1,1.1,0.06,50, 5, Hunskillsinv);
			itemset("Climbing", Material.BOOK, 0, 1, Arrays.asList("Sneaking + SwapHand to on/off"),hsd.Climb.getOrDefault(p.getUniqueId(), 0),1,0,0.5,1, 6, Hunskillsinv);
			itemset("Atrocity", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+at,"","All skills inhance a single attack","Damage half when not fully charged","Immune to Hunger", "When your attack is fully charged", Math.round(5+0.02*hsd.Atrocity.getOrDefault(p.getUniqueId(), 0))  +"% of Enemy's MaxHealth" ,"Increase Power",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.15*(1+at*0.0576)).setScale(2, RoundingMode.HALF_EVEN)), 7, Hunskillsinv);

			if(Proficiency.getpro(p)<1) {
				itemset("ExtraWebs(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Hunskillsinv);
				itemset("Agility(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Hunskillsinv);
				itemset("Infatuation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Hunskillsinv);
				itemset("Impact(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Hunskillsinv);
				itemset("Fear(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Hunskillsinv);
				itemset("SuperJump(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 15, Hunskillsinv);
				itemset("Flawless(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Hunskillsinv);
				itemset("Rage(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Hunskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("ExtraWebs", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("Increases Webs"), 10, Hunskillsinv);
				itemset("Agility", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("Resets Cooldown When you Kill Enemy"), 11, Hunskillsinv);
				itemset("Infatuation", Material.ACTIVATOR_RAIL, 0, 1, Arrays.asList("Just hitting without killing will reduce Cooldown"), 12, Hunskillsinv);
				itemset("Impact", Material.CREEPER_HEAD, 0, 1, Arrays.asList("Stuns Near By Enemies"), 13, Hunskillsinv);
				itemset("Fear", Material.SKELETON_SKULL, 0, 1, Arrays.asList("Stuns Near By Enemies When Enemy is dead"), 14, Hunskillsinv);
				itemset("SuperJump", Material.CHAINMAIL_LEGGINGS, 0, 1, Arrays.asList("Charge SuperJump When you Hold Sneaking","While Climbing is On","Max 10"), 15, Hunskillsinv);
				itemset("Flawless", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16, Hunskillsinv);
				itemset("Rage", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Resets Cooldown of Daze & SkullCrusher","",ChatColor.BOLD+"X 27.1"), 17, Hunskillsinv);
				
				itemset("WebRetrieving(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, Hunskillsinv);
				itemset("Posture(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, Hunskillsinv);
				itemset("Infinite(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, Hunskillsinv);
				itemset("SummaryExecution(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, Hunskillsinv);
				itemset("Survivor(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 24, Hunskillsinv);
				itemset("HardCore(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Hunskillsinv);
				itemset("Execute(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Hunskillsinv);
			}
			else {
				itemset("Agility", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("Resets Cooldown When you Kill Enemy"), 11, Hunskillsinv);
				itemset("Infatuation", Material.ACTIVATOR_RAIL, 0, 1, Arrays.asList("Just hitting without killing will reduce Cooldown"), 12, Hunskillsinv);
				itemset("Impact", Material.CREEPER_HEAD, 0, 1, Arrays.asList("Stuns Near By Enemies"), 13, Hunskillsinv);
				itemset("Fear", Material.SKELETON_SKULL, 0, 1, Arrays.asList("Stuns Near By Enemies When Enemy is dead"), 14, Hunskillsinv);
				itemset("SuperJump", Material.CHAINMAIL_LEGGINGS, 0, 1, Arrays.asList("Charge SuperJump When you Hold Sneaking","While Climbing is On","Max 10"), 15, Hunskillsinv);
				itemset("Flawless", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16, Hunskillsinv);
				itemset("Rage", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Resets Cooldown of Daze & SkullCrusher","",ChatColor.BOLD+"X 27.1"), 17, Hunskillsinv);
				
				itemset("WebRetrieving", Material.FERMENTED_SPIDER_EYE, 0, 1, Arrays.asList("Pull hit enemies when reusing"), 19, Hunskillsinv);
				itemset("Posture", Material.NETHERITE_CHESTPLATE, 0, 1, Arrays.asList("Increases Next Hit Damage After Dodge"), 20, Hunskillsinv);
				itemset("Infinite", Material.NETHERITE_AXE, 0, 1, Arrays.asList("Changes Hunting on/off Skill", "Cooldown will apply when you hit Enemy"), 21, Hunskillsinv);
				itemset("SummaryExecution", Material.NETHERITE_AXE, 0, 1, Arrays.asList("Resets Hunting, Daze, SkullCrasher Cooldown", "When you Success to kill Enemy", "(Hunting will be Automatically Active)"), 22, Hunskillsinv);
				itemset("Survivor", Material.GOLDEN_CARROT, 0, 1, Arrays.asList("Immune to All Negetive Effect"), 24, Hunskillsinv);
				itemset("HardCore", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decreases Rage Cooldown","BackAttack Always Activate"), 25, Hunskillsinv);
				itemset("Execute", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Sprinting + ThrowItem", "Reset Cooldown of Daze & SkullCrusher","",ChatColor.BOLD+"X 33.2D"), 26, Hunskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Hunskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+hsd.SkillPoints.getOrDefault(p.getUniqueId(), 0),"","Click if you want to reset your skill's levels"), 35, Hunskillsinv);
		
		}
		
		p.openInventory(Hunskillsinv);
	}


}
