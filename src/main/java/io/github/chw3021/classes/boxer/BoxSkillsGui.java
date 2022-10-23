package io.github.chw3021.classes.boxer;



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

public class BoxSkillsGui extends SkillsGui{
	

	public void BoxSkillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		BoxSkillsData bsd = new BoxSkillsData(BoxSkillsData.loadData(path +"/plugins/RPGskills/BoxSkillsData.data"));
		Inventory Boxskillsinv = Bukkit.createInventory(null, 54, "BoxSkills");
		
		Obtained.itemset(p, Boxskillsinv);

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("�Ƶ巹����", Material.LEATHER_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Parrying.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�ٶ� �迭]","�չٲٱ� ���� �Ǳ⸦ �����մϴ�",ChatColor.BOLD+"������ X 1.5","���ظ� ���������� ��ġ�� ����մϴ�","��ġ�� �ִ����� (200)�̵Ǹ�","���ݷ�, ����, �ӵ��� �����մϴ�", "Master Lv.1"), 0, Boxskillsinv);
			itemset("����", Material.LEATHER_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Weaving.getOrDefault(p.getUniqueId(),0),"","��ũ����","Master LV.1"), 1, Boxskillsinv);
			itemset("�ر�", Material.IRON_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Straight.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�ٶ� �迭]","��Ŭ��","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.3*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 2, Boxskillsinv);
			itemset("ö���", Material.GOLDEN_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.BodyBlow.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�ٶ� �迭]","��ũ���� + ��Ŭ��","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.55*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 3, Boxskillsinv);
			itemset("�����÷�", Material.DIAMOND_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�ٶ� �迭]","�չٲٱ� + ��ũ����","",ChatColor.BOLD+" 8 X "+BigDecimal.valueOf(0.15*(1+bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0)*0.025)).setScale(2, RoundingMode.HALF_EVEN)+"D + "+" "+BigDecimal.valueOf(0.5*(1+bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 4, Boxskillsinv);
			itemset("�ݰ�", Material.CHAINMAIL_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Counter.getOrDefault(p.getUniqueId(),0),"","���� ������ �и��� ��������","ȸ�Ǽ����� �ߵ��˴ϴ�","",ChatColor.BOLD+" X"+BigDecimal.valueOf(1.4+bsd.Counter.getOrDefault(p.getUniqueId(),0)*0.025).setScale(2, RoundingMode.HALF_EVEN),"Master LV.50"), 5, Boxskillsinv);
			itemset("��ȣ��", Material.WRITABLE_BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Rest.getOrDefault(p.getUniqueId(),0),"","��ũ����(����)","Master LV.1"), 6, Boxskillsinv);
			itemset("�ܷ�", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Training.getOrDefault(p.getUniqueId(),0),"","���ݷ��� �����մϴ�",ChatColor.BOLD+" X"+BigDecimal.valueOf(1.25+bsd.Training.getOrDefault(p.getUniqueId(),0)*0.02405).setScale(2, RoundingMode.HALF_EVEN),"","���� ���ݰ� ����ü����", "��ų��� �Ǵ� �Ϲݰ������� �и��� �ؼ�", "�޴����ظ� 70% ���ҽ�ŵ�ϴ�","(����ü�� ƨ�ܳ��� �ֽ��ϴ�)"
					,"","�ٶ� ���׷��� �����մϴ�"), 7, Boxskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 9, Boxskillsinv);
				itemset("��Ŀ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 10, Boxskillsinv);
				itemset("��õ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 11, Boxskillsinv);
				itemset("������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 12, Boxskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 13, Boxskillsinv);
				itemset("��ȸ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 14, Boxskillsinv);
				itemset("������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 15, Boxskillsinv);
				itemset("�γ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 16, Boxskillsinv);
				itemset("�ϰݱ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 17, Boxskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("����", Material.TORCH, 0, 1, Arrays.asList("��ġ�� ��� �ִ�ȭ ��ŵ�ϴ�"), 9, Boxskillsinv);
				itemset("��Ŀ��", Material.NETHERITE_HELMET, 0, 1, Arrays.asList("��ũ������ ������ �����մϴ�"), 10, Boxskillsinv);
				itemset("��õ��", Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ��õ���� ����մϴ�","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.4*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.046)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(���ط��� �ر� ������ ����մϴ�)"), 11, Boxskillsinv);
				itemset("������", Material.POPPED_CHORUS_FRUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� �������� ����մϴ�","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.35*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(���ط��� ö��� ������ ����մϴ�)"), 12, Boxskillsinv);
				itemset("����", Material.RAIL, 0, 1, Arrays.asList("���Է½� �����÷��� �ѹ��� ����Ҽ� �ֽ��ϴ�"), 13, Boxskillsinv);
				itemset("��ȸ", Material.SHIELD, 0, 1, Arrays.asList("������ ���� �޾Ƶ� �ݰ��� Ȱ��ȭ �˴ϴ�"), 14, Boxskillsinv);
				itemset("������", Material.GOLDEN_APPLE, 0, 1, Arrays.asList("ü�� ���ȿ���� �����մϴ�"), 15, Boxskillsinv);
				itemset("�γ�", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("��ü ���ݷ°� ������ �����մϴ�"), 16, Boxskillsinv);
				itemset("�ϰݱ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","��ũ���� + �����۴�����", "",ChatColor.BOLD+" X 12.1D"), 17, Boxskillsinv);
				
				itemset("��ŷ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 19, Boxskillsinv);
				itemset("����������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 20, Boxskillsinv);
				itemset("��ǳ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 21, Boxskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 22, Boxskillsinv);
				itemset("Ȱ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 25, Boxskillsinv);
				itemset("ö���� ����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 26, Boxskillsinv);
			}
			else {
				itemset("����", Material.TORCH, 0, 1, Arrays.asList("��ġ�� ��� �ִ�ȭ ��ŵ�ϴ�"), 9, Boxskillsinv);
				itemset("��Ŀ��", Material.NETHERITE_HELMET, 0, 1, Arrays.asList("��ũ������ ������ �����մϴ�"), 10, Boxskillsinv);
				itemset("��õ��", Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ��õ���� ����մϴ�","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.4*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.046)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(���ط��� �ر� ������ ����մϴ�)"), 11, Boxskillsinv);
				itemset("������", Material.POPPED_CHORUS_FRUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� �������� ����մϴ�","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.35*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(���ط��� ö��� ������ ����մϴ�)"), 12, Boxskillsinv);
				itemset("����", Material.RAIL, 0, 1, Arrays.asList("���Է½� �����÷��� �ѹ��� ����Ҽ� �ֽ��ϴ�"), 13, Boxskillsinv);
				itemset("��ȸ", Material.SHIELD, 0, 1, Arrays.asList("������ ���� �޾Ƶ� �ݰ��� Ȱ��ȭ �˴ϴ�"), 14, Boxskillsinv);
				itemset("������", Material.GOLDEN_APPLE, 0, 1, Arrays.asList("ü�� ���ȿ���� �����մϴ�"), 15, Boxskillsinv);
				itemset("�γ�", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("��ü ���ݷ°� ������ �����մϴ�"), 16, Boxskillsinv);
				itemset("�ϰݱ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","��ũ���� + �����۴�����", "",ChatColor.BOLD+" X 12.1D"), 17, Boxskillsinv);
				
				itemset("��ŷ", Material.CHICKEN, 0, 1, Arrays.asList("��ũ���� ���� �ݰ��� Ȱ��ȭ �˴ϴ�"), 19, Boxskillsinv);
				itemset("����������", Material.BLACK_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ���������⸦ ����մϴ�","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.54*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(���ط��� �ر� ������ ����մϴ�)"), 20, Boxskillsinv);
				itemset("��ǳ", Material.IRON_BOOTS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ��ǳ�� ����մϴ�","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.35*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(���ط��� ö��� ������ ����մϴ�)"), 21, Boxskillsinv);
				itemset("����", Material.ENCHANTED_GOLDEN_APPLE, 0, 1, Arrays.asList("���Է½� �����÷��� �ѹ��� ����Ҽ� �ֽ��ϴ�"), 22, Boxskillsinv);
				itemset("Ȱ��", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("��ü ���ݷ°� ������ �����մϴ�","�ϰݱ��� ���� ���ð��� �����մϴ�"), 25, Boxskillsinv);
				itemset("ö���� ����", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","�޸��� + �����۴�����", "",ChatColor.BOLD+" X 18.1D"), 26, Boxskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Boxskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+bsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"), 35, Boxskillsinv);
		
			
		}
		else {
			itemset("Adrenaline", Material.LEATHER_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Parrying.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","SwapHand to release FistForce","Adrenaline levels will Increases", "When You're Damaged","Increases Ability When Full Charged (200)", "Master Lv.1"), 0, Boxskillsinv);
			itemset("Weaving", Material.LEATHER_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Weaving.getOrDefault(p.getUniqueId(),0),"","Sneaking","Master LV.1"), 1, Boxskillsinv);
			itemset("Straight", Material.IRON_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Straight.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","Rightclick","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.3*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 2, Boxskillsinv);
			itemset("BodyBlow", Material.GOLDEN_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.BodyBlow.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","Sneaking + Rightclick","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.55*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 3, Boxskillsinv);
			itemset("DempseyRoll", Material.DIAMOND_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","SwapHand + Sneaking","",ChatColor.BOLD+" 8 X "+BigDecimal.valueOf(0.15*(1+bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0)*0.025)).setScale(2, RoundingMode.HALF_EVEN)+"D + "+" "+BigDecimal.valueOf(0.5*(1+bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 4, Boxskillsinv);
			itemset("Counter", Material.CHAINMAIL_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Counter.getOrDefault(p.getUniqueId(),0),"","Activate when you evade enemies attack", "using Parrying or Weaving","",ChatColor.BOLD+" X"+BigDecimal.valueOf(1.4+bsd.Counter.getOrDefault(p.getUniqueId(),0)*0.025).setScale(2, RoundingMode.HALF_EVEN) ,"Master LV.50"), 5, Boxskillsinv);
			itemset("Rest", Material.WRITABLE_BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Rest.getOrDefault(p.getUniqueId(),0),"","Sneaking(hold)","Master LV.1"), 6, Boxskillsinv);
			itemset("Training", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Training.getOrDefault(p.getUniqueId(),0),"","Increases damage",ChatColor.BOLD+" X"+BigDecimal.valueOf(1.25+bsd.Training.getOrDefault(p.getUniqueId(),0)*0.02405).setScale(2, RoundingMode.HALF_EVEN),"","If You're Attacked While Punching or Using Skills", "Parry 70% of the Damage", "Bounce if Projectile"
					,"Increases Wind Resistance"), 7, Boxskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("Intension(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Boxskillsinv);
				itemset("Peek-a-boo(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Boxskillsinv);
				itemset("SkyCrasher(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Boxskillsinv);
				itemset("UnderHook(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Boxskillsinv);
				itemset("Immersion(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Boxskillsinv);
				itemset("Chance(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Boxskillsinv);
				itemset("Strong heart(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 15, Boxskillsinv);
				itemset("Patience(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Boxskillsinv);
				itemset("One Punch(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Boxskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Intension", Material.TORCH, 0, 1, Arrays.asList("Maximize FistForce Instantly"), 9, Boxskillsinv);
				itemset("Peek-a-boo", Material.NETHERITE_HELMET, 0, 1, Arrays.asList("Increases Armor While Sneaking"), 10, Boxskillsinv);
				itemset("SkyCrasher", Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use SkyCrasher When use Once more","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.4*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.046)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By Straight)"), 11, Boxskillsinv);
				itemset("UnderHook", Material.POPPED_CHORUS_FRUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use UnderHook When use Once more","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.35*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By Bodyblow)"), 12, Boxskillsinv);
				itemset("Immersion", Material.RAIL, 0, 1, Arrays.asList("Able to use Once more"), 13, Boxskillsinv);
				itemset("Chance", Material.SHIELD, 0, 1, Arrays.asList("Activate Counter When Damaged by Enimies as well"), 14, Boxskillsinv);
				itemset("Strong heart", Material.GOLDEN_APPLE, 0, 1, Arrays.asList("Amplify Regeneration Effect"), 15, Boxskillsinv);
				itemset("Patience", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor"), 16, Boxskillsinv);
				itemset("One Punch", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Sneaking + ThrowItem", "",ChatColor.BOLD+" X 12.1D"), 17, Boxskillsinv);

				itemset("Ducking(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, Boxskillsinv);
				itemset("EarthQuaker(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, Boxskillsinv);
				itemset("FistStorm(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, Boxskillsinv);
				itemset("Acceleration(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, Boxskillsinv);
				itemset("Stamina(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Boxskillsinv);
				itemset("Will Of Ironman(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Boxskillsinv);
			}
			else {
				itemset("Intension", Material.TORCH, 0, 1, Arrays.asList("Maximize FistForce Instantly"), 9, Boxskillsinv);
				itemset("Peek-a-boo", Material.NETHERITE_HELMET, 0, 1, Arrays.asList("Increases Armor While Sneaking"), 10, Boxskillsinv);
				itemset("SkyCrasher", Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use SkyCrasher When use Once more","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.4*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.046)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By Straight)"), 11, Boxskillsinv);
				itemset("UnderHook", Material.POPPED_CHORUS_FRUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use UnderHook When use Once more","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.35*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By Bodyblow)"), 12, Boxskillsinv);
				itemset("Immersion", Material.RAIL, 0, 1, Arrays.asList("Able to use Once more"), 13, Boxskillsinv);
				itemset("Chance", Material.SHIELD, 0, 1, Arrays.asList("Activate Counter When Damaged by Enimies as well"), 14, Boxskillsinv);
				itemset("Strong heart", Material.GOLDEN_APPLE, 0, 1, Arrays.asList("Amplify Regeneration Effect"), 15, Boxskillsinv);
				itemset("Patience", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor"), 16, Boxskillsinv);
				itemset("One Punch", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Sneaking + ThrowItem", "",ChatColor.BOLD+" X 12.1D"), 17, Boxskillsinv);

				itemset("Ducking", Material.CHICKEN, 0, 1, Arrays.asList("Activate Counter When Sneaking"), 19, Boxskillsinv);
				itemset("EarthQuaker", Material.BLACK_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use EarthQuaker When use Once more","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.54*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By Straight)"), 20, Boxskillsinv);
				itemset("FistStorm", Material.IRON_BOOTS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use FistStorm When use Once more","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.35*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By Bodyblow)"), 21, Boxskillsinv);
				itemset("Acceleration", Material.ENCHANTED_GOLDEN_APPLE, 0, 1, Arrays.asList("Able to use Once more"), 22, Boxskillsinv);
				itemset("Stamina", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decreases One Punch Cooldown"), 25, Boxskillsinv);
				itemset("Will Of Ironman", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Sprinting + ThrowItem", "",ChatColor.BOLD+" X 18.1D"), 26, Boxskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Boxskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+bsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, Boxskillsinv);
		
			
		}
		p.openInventory(Boxskillsinv);
	}


}
