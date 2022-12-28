package io.github.chw3021.classes.mercenary;

import java.io.File;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.classes.SkillsGui;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class MerSkillsGui extends SkillsGui{


	public void Berskillsinv(Player p) {
		String path = new File("").getAbsolutePath();
		MerSkillsData bsd = new MerSkillsData(MerSkillsData.loadData(path + "/plugins/RPGskills/BerSkillsData.data"));
		Inventory Berskillsinv = Bukkit.createInventory(null, 54, "Berskills");

		Obtained.itemset(p, Berskillsinv);

		if (p.getLocale().equalsIgnoreCase("ko_kr")) {

			basic("����", Material.WOODEN_HOE,0, 1, Arrays.asList(	"�չٲٱ�", "���ð� 0.5��", "",	ChatColor.DARK_RED + "����: �޴����ذ� 20% �����ϰ�", ChatColor.DARK_RED + "���� ���ݽ� ü���� ȸ���մϴ�",
					ChatColor.RED + "����: ���ط��� 20% �����ϴ� ���",ChatColor.RED +  "��� ���� ü���� �߰��� �Ҹ��մϴ�"), bsd.Conversion.getOrDefault(p.getUniqueId(), 0),
					0,0d,0d,1, 0, Berskillsinv);
			basic("�л�", Material.STONE_SWORD, 0, 1,Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "��ũ���� + �չٲٱ�"),bsd.Spray.getOrDefault(p.getUniqueId(), 0),1,0.8 , 0.07, 50,1, Berskillsinv);
			basic("�һ�", Material.APPLE, 0, 1, Arrays.asList("ü���� 5%�̸����� ��������", "�һ簡 �ߵ��˴ϴ�", "�нú� ��ų"), 2, Berskillsinv);
			basic("�۾���", Material.IRON_HOE, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "��Ŭ�� + ��ũ����" ),bsd.Swipe.getOrDefault(p.getUniqueId(), 0),1,0.5,0.08,50, 3, Berskillsinv);
			basic("����", Material.GOLDEN_HOE, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���� + ��Ŭ��"),bsd.Inhale.getOrDefault(p.getUniqueId(), 0),8,0.2,0.02,50, 4, Berskillsinv);
			basic("����", Material.DIAMOND_SWORD, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���� + ��Ŭ��"),bsd.Flurry.getOrDefault(p.getUniqueId(), 0),
					10,0.18,0.02,50,5, Berskillsinv);
			basic("��ȫ������", Material.DIAMOND_SWORD, 0, 1,
					Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "��ũ���� + ��Ŭ��"),bsd.Crimpson.getOrDefault(p.getUniqueId(), 0),3,0.65,0.053,50,
					6, Berskillsinv);
			passive("����", Material.BOOK, 0, 1,
					Arrays.asList(
							"�������� �����մϴ�", "���ݼӵ��� �ſ� �������ϴ�", "��ġ�������� �ִ�ġ�� �˴ϴ�", "���ݽ� �̵��ӵ��� �������� �����մϴ�"
							),bsd.Lunacy.getOrDefault(p.getUniqueId(), 0),0.04435,7, Berskillsinv);
			
			if (Proficiency.getpro(p) < 1) {
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 9, Berskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 10, Berskillsinv);
				itemset("���(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 12, Berskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 13, Berskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 14, Berskillsinv);
				itemset("��Ÿ(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 15, Berskillsinv);
				itemset("���ں�(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 16, Berskillsinv);
				itemset("����� ����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 17, Berskillsinv);
			} else if (Proficiency.getpro(p) < 2 && Proficiency.getpro(p) >= 1) {
				itemset("����", Material.CRIMSON_HYPHAE, 0, 1, Arrays.asList("���� ������ ������ ȿ���� ����ϴ�"), 9, Berskillsinv);
				itemset("����", Material.CRIMSON_FUNGUS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ������ ����մϴ�", "(���ط��� �л� ������ ����մϴ�)"),bsd.Spray.getOrDefault(p.getUniqueId(), 0),
						5,0.31,0.03,10, Berskillsinv);
				itemset("������", Material.NETHER_WART, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� �����⸦ ����մϴ�", "(���ط��� �۾��� ������ ����մϴ�)")
						,bsd.Swipe.getOrDefault(p.getUniqueId(), 0),1,0.7,0.09, 12, Berskillsinv);
				itemset("����", Material.FERMENTED_SPIDER_EYE, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ������ ����մϴ�", "(���ط��� ���� ������ ����մϴ�)"
						),bsd.Inhale.getOrDefault(p.getUniqueId(), 0),1,1.2,0.13, 13, Berskillsinv);
				itemset("���ں�", Material.RED_SAND, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ���ں� ����մϴ�", "(���ط��� ���� ������ ����մϴ�)"),bsd.Flurry.getOrDefault(p.getUniqueId(), 0)
						,8,0.44,0.047,
						14, Berskillsinv);
				itemset("��Ÿ", Material.CRIMSON_PRESSURE_PLATE, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ��Ÿ�� ����մϴ�", "(���ط��� ��ȫ������ ������ ����մϴ�)"),bsd.Crimpson.getOrDefault(p.getUniqueId(), 0)
						,1,1.36,0.147,15, Berskillsinv);
				itemset("������", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("��ü ���ݷ��� �����մϴ�", "���ݽ� ü���� ȸ���մϴ�"), 16,
						Berskillsinv);
				itemset("����� ����", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[ȭ�� �迭]", "��ũ���� + �����۴�����", "15�ʵ��� ���ݷ��� ���� �����մϴ�",
								"���ݽ� ȭ�����ظ� �߰��� �����ϴ�", "", ChatColor.BOLD + " X 1.3 X 0.005D"),
						17, Berskillsinv);

				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 19, Berskillsinv);
				itemset("���ں�(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 23, Berskillsinv);
				itemset("�Ͱ�(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 24, Berskillsinv);
				itemset("��ġ����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 25, Berskillsinv);
				itemset("�л�(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 26, Berskillsinv);
			} else {
				itemset("����", Material.CRIMSON_HYPHAE, 0, 1, Arrays.asList("���� ������ ������ ȿ���� ����ϴ�"), 9, Berskillsinv);
				itemset("����", Material.CRIMSON_FUNGUS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ������ ����մϴ�", "(���ط��� �л� ������ ����մϴ�)"),bsd.Spray.getOrDefault(p.getUniqueId(), 0),
						5,0.31,0.03,10, Berskillsinv);
				itemset("������", Material.NETHER_WART, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� �����⸦ ����մϴ�", "(���ط��� �۾��� ������ ����մϴ�)")
						,bsd.Swipe.getOrDefault(p.getUniqueId(), 0),1,0.7,0.09, 12, Berskillsinv);
				itemset("����", Material.FERMENTED_SPIDER_EYE, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ������ ����մϴ�", "(���ط��� ���� ������ ����մϴ�)"
						),bsd.Inhale.getOrDefault(p.getUniqueId(), 0),1,1.2,0.13, 13, Berskillsinv);
				itemset("���ں�", Material.RED_SAND, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ���ں� ����մϴ�", "(���ط��� ���� ������ ����մϴ�)"),bsd.Flurry.getOrDefault(p.getUniqueId(), 0)
						,8,0.44,0.047,
						14, Berskillsinv);
				itemset("��Ÿ", Material.CRIMSON_PRESSURE_PLATE, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ��Ÿ�� ����մϴ�", "(���ط��� ��ȫ������ ������ ����մϴ�)"),bsd.Crimpson.getOrDefault(p.getUniqueId(), 0)
						,1,1.36,0.147,15, Berskillsinv);
				itemset("������", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("��ü ���ݷ��� �����մϴ�", "���ݽ� ü���� ȸ���մϴ�"), 16,
						Berskillsinv);
				itemset("����� ����", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[ȭ�� �迭]", "��ũ���� + �����۴�����", "15�ʵ��� ���ݷ��� ���� �����մϴ�",
								"���ݽ� ȭ�����ظ� �߰��� �����ϴ�", "", ChatColor.BOLD + " X 1.3 X 0.005D"),
						17, Berskillsinv);
				

				itemset("����", Material.CRYING_OBSIDIAN, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ������ ����մϴ�", "(���ط��� �л� ������ ����մϴ�)"),bsd.Spray.getOrDefault(p.getUniqueId(), 0),
						1,1.1,0.09,19, Berskillsinv);
				itemset("��ȫ��", Material.NETHERITE_SWORD, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ��ȫ���� ����մϴ�", 
						"(���ط��� ���� ������ ����մϴ�)"),bsd.Flurry.getOrDefault(p.getUniqueId(), 0),1,1.533,0.0933, 23, Berskillsinv);
				itemset("�Ͱ�", Material.NETHERITE_SWORD, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� �Ͱ��� ����մϴ�", "(���ط��� ��ȫ������ ������ ����մϴ�)"),
								bsd.Crimpson.getOrDefault(p.getUniqueId(), 0),13, 0.26, 0.028, 24, Berskillsinv);
				itemset("��ġ����", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("���ݷ°� ������ �����մϴ�", "����½���� �һ��� ���ð��� �����մϴ�", "�ǰݽ� ���ȿ���� ����ϴ�(��ø����)"), 25,
						Berskillsinv);
				itemset("�л�", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "�޸��� + �����۴�����",
								"", ChatColor.BOLD + "22 X 0.3D + 11.5D"),
						26, Berskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("" + Proficiency.getproexp(p)), 27,
					Berskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1,
					Arrays.asList(ChatColor.AQUA + "SP." + bsd.SkillPoints.getOrDefault(p.getUniqueId(), 0), "",
							"Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"),
					35, Berskillsinv);

		} else {

			itemset("Conversion", Material.WOODEN_HOE, 0, 1, Arrays.asList("SwapHand", "Cooldown 0.5sec", "",
					ChatColor.DARK_RED + "Eager: Reduces 20% Damage from Enemies", ChatColor.DARK_RED + "Recovers Hp when you attack",
					ChatColor.RED + "Frenzy: Increases 20% Damage",ChatColor.RED +  "but makes consumption of Hp"), bsd.Conversion.getOrDefault(p.getUniqueId(), 0),
					0,0d,0d,1, Berskillsinv);
			itemset("Spray", Material.STONE_SWORD, 0, 1,
					Arrays.asList(ChatColor.UNDERLINE + "[Earth]", "Sneaking + SwapHand"),bsd.Spray.getOrDefault(p.getUniqueId(), 0),1,0.8 , 0.07, 50,
					1, Berskillsinv);
			itemset("Undying", Material.IRON_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "Activate Undying when Hp fall below 5%", "Passive Skill"),
					2, Berskillsinv);
			itemset("Swipe", Material.STONE_HOE, 0, 1,
					Arrays.asList(ChatColor.UNDERLINE + "[Earth]", "Sneaking + LeftClick"),bsd.Swipe.getOrDefault(p.getUniqueId(), 0),1,0.5,0.08,50,
					3, Berskillsinv);
			itemset("Inhale", Material.GOLDEN_HOE, 0, 1,
					Arrays.asList(ChatColor.UNDERLINE + "[Earth]", "Jump + RightClick"),bsd.Inhale.getOrDefault(p.getUniqueId(), 0),1,1.2,0.13,
					4, Berskillsinv);
			itemset("Flurry", Material.DIAMOND_SWORD, 0, 1,
					Arrays.asList(ChatColor.UNDERLINE + "[Earth]", "Jump + RightClick"),bsd.Flurry.getOrDefault(p.getUniqueId(), 0),10,0.18,0.02,50,
					5, Berskillsinv);
			itemset("CrimsonAdvance", Material.DIAMOND_SWORD, 0, 1,
					Arrays.asList(ChatColor.UNDERLINE + "[Earth]", "Sneaking + RightClick"),bsd.Crimpson.getOrDefault(p.getUniqueId(), 0),3,0.65,0.053,50,
					6, Berskillsinv);
			passive("Lunacy", Material.BOOK, 0, 1,
					Arrays.asList("Increases Damage", "Set Attack Speed VeryFast", "Set KnockbackResistance Max",
							"Get speed&jump when you hit mob", "Activate Undying when Hp fall below 5%")
					,bsd.Lunacy.getOrDefault(p.getUniqueId(), 0),0.04435,7, Berskillsinv);
			if (Proficiency.getpro(p) < 1) {
				itemset("Thirst(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 9, Berskillsinv);
				itemset("BloodSquirt(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 10,
						Berskillsinv);
				itemset("Scratch(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 12,
						Berskillsinv);
				itemset("BurstOut(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 13,
						Berskillsinv);
				itemset("Merciless(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 14,
						Berskillsinv);
				itemset("Smite(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 15,
						Berskillsinv);
				itemset("Rampageous(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 16,
						Berskillsinv);
				itemset("Bloodboil(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 17,
						Berskillsinv);
			} else if (Proficiency.getpro(p) < 2 && Proficiency.getpro(p) >= 1) {
				itemset("Thirst", Material.CRIMSON_HYPHAE, 0, 1, Arrays.asList("Get Saturation Effect While Eager"), 9,
						Berskillsinv);
				itemset("BloodSquirt", Material.CRIMSON_FUNGUS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Earth]", "Blood will be Squirt When use Once more",
								"(Damage Affected By Spray)"),bsd.Spray.getOrDefault(p.getUniqueId(), 0),
						5,0.31,0.03,10, Berskillsinv);
				itemset("Scratch", Material.NETHER_WART, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[Earth]", "Scratch Enemies When use Once more",
						"(Damage Affected By Swipe)"),bsd.Swipe.getOrDefault(p.getUniqueId(), 0),1,0.7,0.09, 12, Berskillsinv);
				itemset("BurstOut", Material.FERMENTED_SPIDER_EYE, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[Earth]", "Burst Out When use Once more",
						"(Damage Affected By Inhale)"),bsd.Inhale.getOrDefault(p.getUniqueId(), 0),1,1.2,0.13, 13,
						Berskillsinv);
				itemset("Merciless", Material.RED_SAND, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Earth]", "Use Merciless When use Once more",
								"(Damage Affected By Frenzy)"),bsd.Flurry.getOrDefault(p.getUniqueId(), 0),8,0.44,0.047,
						14, Berskillsinv);
				itemset("Smite", Material.CRIMSON_PRESSURE_PLATE, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[Earth]",
						"Smite When use Once more", "(Damage Affected By CrimsonAdvance)"),bsd.Crimpson.getOrDefault(p.getUniqueId(), 0),1,1.36,0.147, 15, Berskillsinv);
				itemset("Rampageous", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("Increases Whole Skills Damage", "Life Steal From Hit Enemies"), 16,
						Berskillsinv);
				itemset("Bloodboil", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Flame]", "Sneaking + ThrowItem",
								"Add Flame Power To Attack", "Increases Damage Greatly for 15s", "",
								ChatColor.BOLD + " X 1.3 X 0.005D"),
						17, Berskillsinv);

				itemset("Rave(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 19,
						Berskillsinv);
				itemset("CrimpsonSlash(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 23,
						Berskillsinv);
				itemset("Onslaught(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 24,
						Berskillsinv);
				itemset("Over Power(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 25,
						Berskillsinv);
				itemset("Genocide/Vampire(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 26,
						Berskillsinv);
			} else {
				itemset("Thirst", Material.CRIMSON_HYPHAE, 0, 1, Arrays.asList("Get Saturation Effect While Eager"), 9,
						Berskillsinv);
				itemset("BloodSquirt", Material.CRIMSON_FUNGUS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Earth]", "Blood will be Squirt When use Once more",
								"(Damage Affected By Spray)"),bsd.Spray.getOrDefault(p.getUniqueId(), 0),
						5,0.31,0.03,10, Berskillsinv);
				itemset("Scratch", Material.NETHER_WART, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[Earth]", "Scratch Enemies When use Once more",
						"(Damage Affected By Swipe)"),bsd.Swipe.getOrDefault(p.getUniqueId(), 0),1,0.7,0.09, 12, Berskillsinv);
				itemset("BurstOut", Material.FERMENTED_SPIDER_EYE, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[Earth]", "Burst Out When use Once more",
						"(Damage Affected By Inhale)"),bsd.Inhale.getOrDefault(p.getUniqueId(), 0),1,1.2,0.13, 13,
						Berskillsinv);
				itemset("Merciless", Material.RED_SAND, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Earth]", "Use Merciless When use Once more",
								"(Damage Affected By Frenzy)"),bsd.Flurry.getOrDefault(p.getUniqueId(), 0),8,0.44,0.047,
						14, Berskillsinv);
				itemset("Smite", Material.CRIMSON_PRESSURE_PLATE, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[Earth]",
						"Smite When use Once more", "(Damage Affected By CrimsonAdvance)"),bsd.Crimpson.getOrDefault(p.getUniqueId(), 0),1,1.36,0.147, 15, Berskillsinv);
				itemset("Rampageous", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("Increases Whole Skills Damage", "Life Steal From Hit Enemies"), 16,
						Berskillsinv);
				itemset("Bloodboil", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Flame]", "Sneaking + ThrowItem",
								"Add Flame Power To Attack", "Increases Damage Greatly for 15s", "",
								ChatColor.BOLD + " X 1.3 X 0.005D"),
						17, Berskillsinv);
				

				itemset("Rave", Material.CRYING_OBSIDIAN, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Earth]", "Rave When use Once more",
								"(Damage Affected By Spray)"),bsd.Spray.getOrDefault(p.getUniqueId(), 0),
						1,1.1,0.09,
						19, Berskillsinv);
				itemset("CrimpsonSlash", Material.NETHERITE_SWORD, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[Earth]", "Use CrimpsonSlash When use Once more",
						"(Damage Affected By Flurry)"),bsd.Flurry.getOrDefault(p.getUniqueId(), 0),1,1.533,0.0933, 23,
						Berskillsinv);
				itemset("Onslaught", Material.NETHERITE_SWORD, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[Earth]",
						"Use Onslaught When use Once more", "(Damage Affected By CrimsonAdvance)"),
						bsd.Crimpson.getOrDefault(p.getUniqueId(), 0),13, 0.26, 0.028,	24, Berskillsinv);
				itemset("Over Power", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor",
						"Decreases Bloodboil&Undying Cooldown", "Get Regeneration When You Attacked(Stackable)"), 25,
						Berskillsinv);
				itemset("Genocide", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Earth]", "Sprinting + ThrowItem", "",
								ChatColor.BOLD + "22 X 0.3D + 11.5D / " + p.getLevel() * 1.1),
						26, Berskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("" + Proficiency.getproexp(p)),
					27, Berskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1,
					Arrays.asList(ChatColor.AQUA + "SP." + bsd.SkillPoints.getOrDefault(p.getUniqueId(), 0), "",
							"Click if you want to reset your skill's levels"),
					35, Berskillsinv);

		}
		p.openInventory(Berskillsinv);
	}

}
