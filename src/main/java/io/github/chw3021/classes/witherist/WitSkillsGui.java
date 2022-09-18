package io.github.chw3021.classes.witherist;

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

public class WitSkillsGui extends SkillsGui {

	public void WitSkillsinv(Player p) {
		String path = new File("").getAbsolutePath();
		WitSkillsData wsd = new WitSkillsData(WitSkillsData.loadData(path + "/plugins/RPGskills/WitSkillsData.data"));
		Inventory Witskillsinv = Bukkit.createInventory(null, 54, "Witskills");
		Obtained.itemset(p, Witskillsinv);

		if (p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("�����ذ�", Material.WITHER_SKELETON_SKULL, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[��� �迭]", "�չٲٱ�", "������ ���� ��ȭ�� �ذ��� �߻��մϴ�", "",
							ChatColor.BOLD + " X "
									+ BigDecimal.valueOf(0.34 * (1+wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0) * 0.034))
											.setScale(2, RoundingMode.HALF_EVEN)
									+ "D (��ȭ�� �ι�)",
							"Master LV.50"),
					0, Witskillsinv);
			itemset("����ǰ���", Material.WOODEN_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.ReapingHook.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[��� �迭]", "��ũ���� + ��Ŭ��", "Master LV.1"),
					1, Witskillsinv);
			itemset("�����ǳ�", Material.STONE_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.WitherScythe.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[��� �迭]", "�������� + ��ũ����", "",
							ChatColor.BOLD + "25 X "
									+ BigDecimal.valueOf(1.81 * (1+wsd.WitherScythe.getOrDefault(p.getUniqueId(), 0) * 0.017))
											.setScale(2, RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					2, Witskillsinv);
			itemset("����", Material.IRON_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.Curse.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[��� �迭]", "��Ŭ��", "",
							ChatColor.BOLD + "1 X "
									+ BigDecimal.valueOf(0.68 * (1+wsd.Curse.getOrDefault(p.getUniqueId(), 0) * 0.07)).setScale(2,
											RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					3, Witskillsinv);
			itemset("����", Material.ELYTRA, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.Hover.getOrDefault(p.getUniqueId(), 0), "", "��Ŭ�� + ����",
							"Master LV.1"),
					4, Witskillsinv);
			itemset("�������", Material.DIAMOND_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.Roses.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[��� �迭]", "�չٲٱ� + ��ũ����", "",
							ChatColor.BOLD + "10 X "
									+ BigDecimal.valueOf(0.06 * (1+wsd.Roses.getOrDefault(p.getUniqueId(), 0) * 0.005)).setScale(2,
											RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					5, Witskillsinv);
			itemset("����ȭ", Material.BOOK, 0, 1, Arrays.asList(
					ChatColor.AQUA + "LV." + wsd.Witherize.getOrDefault(p.getUniqueId(), 0), "",
					"���ݷ��� �����մϴ�(�������¿��� �ι�)", "",
					ChatColor.BOLD + " X "
							+ BigDecimal.valueOf(1.25 * (1 + wsd.Witherize.getOrDefault(p.getUniqueId(), 0) * 0.0453))
									.setScale(2, RoundingMode.HALF_EVEN),
					"���������� ������ �޴����ذ� �����մϴ�", "����ȿ���� �鿪�̵˴ϴ�", "��� �迭 ���׷��� �����մϴ�", "������ �õ� ȿ���� �ݴϴ�"), 7, Witskillsinv);
			if (Proficiency.getpro(p) < 1) {
				itemset("������ð���(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 9, Witskillsinv);
				itemset("����ǰ���(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 10, Witskillsinv);
				itemset("�۾���(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 11, Witskillsinv);
				itemset("���ǰ�(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 12, Witskillsinv);
				itemset("������(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 13, Witskillsinv);
				itemset("�ı�(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 14, Witskillsinv);
				itemset("������(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 16, Witskillsinv);
				itemset("�غ�(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 17, Witskillsinv);
			} else if (Proficiency.getpro(p) < 2 && Proficiency.getpro(p) >= 1) {
				itemset("������ð���", Material.DARK_OAK_TRAPDOOR, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[��� �迭]", "����� ������ð��̸� ��ô�մϴ�", "(���ط��� �����ذ� ������ ���մϴ�)",
								"",
								ChatColor.BOLD + " X "
										+ BigDecimal.valueOf(0.3 * (1+wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0) * 0.03))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						9, Witskillsinv);
				itemset("����ǰ���", Material.IRON_HOE, 0, 1, Arrays.asList("�ֺ����鵵 ����ɴϴ�"), 10, Witskillsinv);
				itemset("�۾���", Material.NETHERITE_HOE, 0, 1, Arrays.asList("������ �����մϴ�"), 11, Witskillsinv);
				itemset("���ǰ�", Material.BLACKSTONE, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[��� �迭]", "����� ���ǰ��� ����մϴ�", "(���ط��� ���� ������ ���մϴ�)", "",
								ChatColor.BOLD + "10 X "
										+ BigDecimal.valueOf(0.168 * (1+wsd.Curse.getOrDefault(p.getUniqueId(), 0) * 0.02))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, Witskillsinv);
				itemset("������", Material.ELYTRA, 0, 1, Arrays.asList("���ӽð��� �ι�� �����մϴ�"), 13, Witskillsinv);
				itemset("�ı�", Material.CRACKED_POLISHED_BLACKSTONE_BRICKS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[��� �迭]", "����� �ı��� ����մϴ�", "(���ط��� ������� ������ ���մϴ�)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.56 * (1 +wsd.Roses.getOrDefault(p.getUniqueId(), 0) * 0.048))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						14, Witskillsinv);
				itemset("������", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� �����մϴ�"), 16, Witskillsinv);
				itemset("�غ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[��� �迭]",
						"��ũ���� + �����۴�����", "",
						ChatColor.BOLD  + "16D"), 17,
						Witskillsinv);

				itemset("��ȭ����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 23, Witskillsinv);
				itemset("�����溮(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 22, Witskillsinv);
				itemset("��������(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 21, Witskillsinv);
				itemset("�������(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 18, Witskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 25, Witskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 26, Witskillsinv);
			} else {
				itemset("������ð���", Material.DARK_OAK_TRAPDOOR, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[��� �迭]", "����� ������ð��̸� ��ô�մϴ�", "(���ط��� �����ذ� ������ ���մϴ�)",
								"",
								ChatColor.BOLD + " X "
										+ BigDecimal.valueOf(0.3 * (1+wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0) * 0.03))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						9, Witskillsinv);
				itemset("����ǰ���", Material.IRON_HOE, 0, 1, Arrays.asList("�ֺ����鵵 ����ɴϴ�"), 10, Witskillsinv);
				itemset("�۾���", Material.NETHERITE_HOE, 0, 1, Arrays.asList("������ �����մϴ�"), 11, Witskillsinv);
				itemset("���ǰ�", Material.BLACKSTONE, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[��� �迭]", "����� ���ǰ��� ����մϴ�", "(���ط��� ���� ������ ���մϴ�)", "",
								ChatColor.BOLD + "10 X "
										+ BigDecimal.valueOf(0.168 * (1+wsd.Curse.getOrDefault(p.getUniqueId(), 0) * 0.02))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, Witskillsinv);
				itemset("������", Material.ELYTRA, 0, 1, Arrays.asList("���ӽð��� �ι�� �����մϴ�"), 13, Witskillsinv);
				itemset("�ı�", Material.CRACKED_POLISHED_BLACKSTONE_BRICKS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[��� �迭]", "����� �ı��� ����մϴ�", "(���ط��� ������� ������ ���մϴ�)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.56 * (1 +wsd.Roses.getOrDefault(p.getUniqueId(), 0) * 0.048))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						14, Witskillsinv);
				itemset("������", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� �����մϴ�"), 16, Witskillsinv);
				itemset("�غ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[��� �迭]",
						"��ũ���� + �����۴�����", "",
						ChatColor.BOLD  + "16D"), 17,
						Witskillsinv);

				itemset("�����溮", Material.SHIELD, 0, 1, Arrays.asList("���� 2�ʵ��� �������°� �˴ϴ�"), 22, Witskillsinv);
				itemset("��ȭ����", Material.BEACON, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[��� �迭]", "����� ��ȭ������ ����մϴ�", "(���ط��� ������� ������ ���մϴ�)", "",
								ChatColor.BOLD + "3 X "
										+ BigDecimal.valueOf(0.8 * (1+wsd.Roses.getOrDefault(p.getUniqueId(), 0) * 0.09))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						23, Witskillsinv);
				itemset("��������", Material.IRON_BARS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[��� �迭]", "����� ���������� ����մϴ�", "(���ط��� �������� ������ ���մϴ�)", "",
								ChatColor.BOLD + "8 X "
										+ BigDecimal.valueOf(0.4* (1+wsd.Curse.getOrDefault(p.getUniqueId(), 0) * 0.038))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						21, Witskillsinv);
				itemset("�������", Material.QUARTZ, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[��� �迭]", "����� ��������� ����մϴ�", "(���ط��� �����ذ� ������ ���մϴ�)", "",
								ChatColor.BOLD + "6 X "
										+ BigDecimal.valueOf(0.36 * (1+wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0) * 0.04))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						18, Witskillsinv);
				itemset("����", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("���ݷ��� �����մϴ�", "�غ��� ���ð��� �����մϴ�", "�Ϻ� ������� �ð�ȿ���� �ٲ�ϴ�", "���������� ������ �����鿪�� �˴ϴ�"), 25,
						Witskillsinv);
				itemset("����", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[��� �迭]",
						"�޸��� + �����۴�����", "",
						ChatColor.BOLD + "26D"), 26,
						Witskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("" + Proficiency.getproexp(p)), 27,
					Witskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1,
					Arrays.asList(ChatColor.AQUA + "SP." + wsd.SkillPoints.getOrDefault(p.getUniqueId(), 0), "",
							"Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"),
					35, Witskillsinv);

		} else {
			itemset("WitherSkull", Material.WITHER_SKELETON_SKULL, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[Dark]", "SwapHand", "Shoot Charged Skull When Hovering", "",
							ChatColor.BOLD + " X "
									+ BigDecimal.valueOf(0.34 * (1+wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0) * 0.034))
											.setScale(2, RoundingMode.HALF_EVEN)
									+  "D (Double If Charged)",
							"Master LV.50"),
					0, Witskillsinv);
			itemset("ReapingHook", Material.WOODEN_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.ReapingHook.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[Dark]", "Sneaking + Rightclick", "Master LV.1"),
					1, Witskillsinv);
			itemset("WitherScythe", Material.STONE_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.WitherScythe.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[Dark]", "Hit + Sneaking", "",
							ChatColor.BOLD + "25 X "
									+ BigDecimal.valueOf(1.81 * (1+wsd.WitherScythe.getOrDefault(p.getUniqueId(), 0) * 0.017))
											.setScale(2, RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					2, Witskillsinv);
			itemset("Curse", Material.IRON_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.Curse.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[Dark]", "Rightclick", "",
							ChatColor.BOLD + "1 X "
									+ BigDecimal.valueOf(0.68 * (1+wsd.Curse.getOrDefault(p.getUniqueId(), 0) * 0.07)).setScale(2,
											RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					3, Witskillsinv);
			itemset("Hover", Material.GOLDEN_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.Hover.getOrDefault(p.getUniqueId(), 0), "",
							"LeftClick + Jump", "Master LV.1"),
					4, Witskillsinv);
			itemset("Roses", Material.DIAMOND_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.Roses.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[Dark]", "SwapHand + Sneaking", "",
							ChatColor.BOLD + "10 X "
									+ BigDecimal.valueOf(0.06 * (1+wsd.Roses.getOrDefault(p.getUniqueId(), 0) * 0.005)).setScale(2,
											RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					5, Witskillsinv);
			itemset("Witherize", Material.BOOK, 0, 1, Arrays.asList(
					ChatColor.AQUA + "LV." + wsd.Witherize.getOrDefault(p.getUniqueId(), 0), "",
					"Increases damage (Double to WitherType Mobs)", "",
					ChatColor.BOLD + " X "
							+ BigDecimal.valueOf(1.25 * (1 + wsd.Witherize.getOrDefault(p.getUniqueId(), 0) * 0.0453))
									.setScale(2, RoundingMode.HALF_EVEN),
					"Reduce Damage from WitherType", "Immune to Wither Effect", "Increases Dark Resistance",
					"Applys Enemy Wither Effect"), 7, Witskillsinv);
			if (Proficiency.getpro(p) < 1) {
				itemset("WitherBola(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 9, Witskillsinv);
				itemset("AbsorbingHook(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 10,
						Witskillsinv);
				itemset("Sweeping(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 11,
						Witskillsinv);
				itemset("CircleOfNight(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 12,
						Witskillsinv);
				itemset("EnhancedHover(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 13,
						Witskillsinv);
				itemset("Demolition(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 14,
						Witskillsinv);
				itemset("Vengeance(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 16,
						Witskillsinv);
				itemset("Overcome(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 17,
						Witskillsinv);
			} else if (Proficiency.getpro(p) < 2 && Proficiency.getpro(p) >= 1) {
				itemset("WitherBola", Material.DARK_OAK_TRAPDOOR, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Dark]", "Throw WitherBola When Use Once More",
								"(Damage Affected By WitherSkull)", "",
								ChatColor.BOLD + " X "
										+ BigDecimal.valueOf(0.3 * (1+wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0) * 0.03))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						9, Witskillsinv);
				itemset("AbsorbingHook", Material.IRON_HOE, 0, 1, Arrays.asList("Pull Near By Enemies"), 10,
						Witskillsinv);
				itemset("Sweeping", Material.NETHERITE_HOE, 0, 1, Arrays.asList("Increases Range"), 11, Witskillsinv);
				itemset("CircleOfNight", Material.BLACKSTONE, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Dark]", "Use CircleOfNight When Use Once More",
								"(Damage Affected By Curse)", "",
								ChatColor.BOLD + "10 X "
										+ BigDecimal.valueOf(0.168 * (1+wsd.Curse.getOrDefault(p.getUniqueId(), 0) * 0.02))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, Witskillsinv);
				itemset("EnhancedHover", Material.ELYTRA, 0, 1, Arrays.asList("Doubles Duration"), 13, Witskillsinv);
				itemset("Demolition", Material.CRACKED_POLISHED_BLACKSTONE_BRICKS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Dark]", "Use Demolition When Use Once More",
								"(Damage Affected By Roses)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.56 * (1 +wsd.Roses.getOrDefault(p.getUniqueId(), 0) * 0.048))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						14, Witskillsinv);
				itemset("Vengeance", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16,
						Witskillsinv);
				itemset("Overcome", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[Dark]",
						"Sneaking + ThrowItem", "",
						ChatColor.BOLD + " X " + BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_EVEN) + "16D"), 17,
						Witskillsinv);

				itemset("WitherBarrier(Locked)", Material.WITHER_SKELETON_SKULL, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 22,
						Witskillsinv);
				itemset("PurifierBeam(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 23,
						Witskillsinv);
				itemset("CrystalCage(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 21,
						Witskillsinv);
				itemset("WhiteQuarts(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 18,
						Witskillsinv);
				itemset("Depuration(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 25,
						Witskillsinv);
				itemset("Overthrow(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 26,
						Witskillsinv);
			} else {
				itemset("WitherBola", Material.DARK_OAK_TRAPDOOR, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Dark]", "Throw WitherBola When Use Once More",
								"(Damage Affected By WitherSkull)", "",
								ChatColor.BOLD + " X "
										+ BigDecimal.valueOf(0.3 * (1+wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0) * 0.03))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						9, Witskillsinv);
				itemset("AbsorbingHook", Material.IRON_HOE, 0, 1, Arrays.asList("Pull Near By Enemies"), 10,
						Witskillsinv);
				itemset("Sweeping", Material.NETHERITE_HOE, 0, 1, Arrays.asList("Increases Range"), 11, Witskillsinv);
				itemset("CircleOfNight", Material.BLACKSTONE, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Dark]", "Use CircleOfNight When Use Once More",
								"(Damage Affected By Curse)", "",
								ChatColor.BOLD + "10 X "
										+ BigDecimal.valueOf(0.168 * (1+wsd.Curse.getOrDefault(p.getUniqueId(), 0) * 0.02))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, Witskillsinv);
				itemset("EnhancedHover", Material.ELYTRA, 0, 1, Arrays.asList("Doubles Duration"), 13, Witskillsinv);
				itemset("Demolition", Material.CRACKED_POLISHED_BLACKSTONE_BRICKS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Dark]", "Use Demolition When Use Once More",
								"(Damage Affected By Roses)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.56 * (1 +wsd.Roses.getOrDefault(p.getUniqueId(), 0) * 0.048))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						14, Witskillsinv);
				itemset("Vengeance", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16,
						Witskillsinv);
				itemset("Overcome", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[Dark]",
						"Sneaking + ThrowItem", "",
						ChatColor.BOLD + " X " + BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_EVEN) + "16D"), 17,
						Witskillsinv);

				itemset("WitherBarrier", Material.WITHER_SKELETON_SKULL, 0, 1,
						Arrays.asList("Set Invulnerable For 2s", "Since You Start Hovering"), 22, Witskillsinv);
				itemset("PurifierBeam", Material.BEACON, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Dark]", "Shot PurifierBeam When Use Once More",
								"(Damage Affected By Roses)", "",
								ChatColor.BOLD + "3 X "
										+ BigDecimal.valueOf(0.8 * (1+wsd.Roses.getOrDefault(p.getUniqueId(), 0) * 0.09))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						23, Witskillsinv);
				itemset("CrystalCage", Material.IRON_BARS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Dark]", "Use CrystalCage When Use Once More",
								"(Damage Affected By Curse)", "",
								ChatColor.BOLD + "8 X "
										+ BigDecimal.valueOf(0.4* (1+wsd.Curse.getOrDefault(p.getUniqueId(), 0) * 0.038))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						21, Witskillsinv);
				itemset("WhiteQuarts", Material.QUARTZ, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Dark]", "Use WhiteQuarts When Use Once More",
								"(Damage Affected By WitherSkull)", "",
								ChatColor.BOLD + "6 X "
										+ BigDecimal.valueOf(0.36 * (1+wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0) * 0.04))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						18, Witskillsinv);
				itemset("Depuration", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("Increases Damage & Armor", "Decreases Overcome Cooldown",
								"Change some of the Skills' Effects", "Totally Immune To WitherType Mobs"),
						25, Witskillsinv);
				itemset("Overthrow", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Dark]", "Sprinting + ThrowItem", "", 
								ChatColor.BOLD + "26D"), 26, Witskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("" + Proficiency.getproexp(p)),
					27, Witskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1,
					Arrays.asList(ChatColor.AQUA + "SP." + wsd.SkillPoints.getOrDefault(p.getUniqueId(), 0), "",
							"Click if you want to reset your skill's levels"),
					35, Witskillsinv);

		}

		p.openInventory(Witskillsinv);
	}

}
