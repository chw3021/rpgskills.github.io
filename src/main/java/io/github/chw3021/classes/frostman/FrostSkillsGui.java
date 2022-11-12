package io.github.chw3021.classes.frostman;

import java.util.Arrays;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.classes.SkillsGui;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class FrostSkillsGui extends SkillsGui {

	public void FrostSkillsinv(Player p) {
		Inventory Frostskillsinv = Bukkit.createInventory(null, 54, "FrostSkills");
		Obtained.itemset(p, Frostskillsinv);

		String path = new File("").getAbsolutePath();
		FrostSkillsData bsd = new FrostSkillsData(
				FrostSkillsData.loadData(path + "/plugins/RPGskills/FrostSkillsData.data"));

		if (p.getLocale().equalsIgnoreCase("ko_kr")) {

			itemset("��������", Material.BLUE_ICE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.FrozenCrystal.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[���� �迭]", "�չٲٱ�", "����� ���� ���߸��� �ֽ��ϴ�", "",
							ChatColor.BOLD + ""
									+ BigDecimal.valueOf(0.765 * (1+bsd.FrozenCrystal.getOrDefault(p.getUniqueId(), 0) * 0.055))
											.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					0, Frostskillsinv);
			itemset("���", Material.PACKED_ICE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.Hailstones.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[���� �迭]", "��ũ���� + �չٲٱ�", "",
							ChatColor.BOLD + "20 X "
									+ BigDecimal.valueOf(0.16 * (1+bsd.Hailstones.getOrDefault(p.getUniqueId(), 0) * 0.018))
											.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					1, Frostskillsinv);
			itemset("��帧ȭ��", Material.ICE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[���� �迭]", "��Ŭ��", "",
							ChatColor.BOLD + "3 X "
									+ BigDecimal.valueOf(0.32 * (1+bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0) * 0.031))
											.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					2, Frostskillsinv);
			itemset("�Ŵ��帧", Material.PRISMARINE_SHARD, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[���� �迭]", "��ũ���� + ��Ŭ��", "",
							ChatColor.BOLD + "25 X "
									+ BigDecimal.valueOf(0.23 * (1 + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0) * 0.025))
											.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					3, Frostskillsinv);
			itemset("�տ�", Material.DIAMOND_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.Crack.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[���� �迭]", "�������� ������ �������� + ��ũ����", "",
							ChatColor.BOLD + " X "
									+ BigDecimal.valueOf(2.5+bsd.Crack.getOrDefault(p.getUniqueId(), 0) * 2.6).setScale(2,
											RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					4, Frostskillsinv);
			itemset("���ٶ�", Material.SNOW_BLOCK, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.SnowBreeze.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[���� �迭]", "���� + ��Ŭ��", "", ChatColor.BOLD + "0.25D",
							"Master LV.1"),
					5, Frostskillsinv);
			itemset("����", Material.BOOK, 0, 1, Arrays.asList(
					ChatColor.AQUA + "LV." + bsd.Frostbite.getOrDefault(p.getUniqueId(), 0), "", "���ݷ��� �����մϴ�",
					"�����迭 ���׷��� �����մϴ�", "3�� ���ݴ��� ���� ���� �ɷ�", "2�ʵ��� �������°� �˴ϴ�(���ð� 5��)", "�������ؿ� ��ȭ�� �鿪�� �˴ϴ�", "",
					ChatColor.BOLD + " X "
							+ BigDecimal.valueOf(1.15 * (1 + bsd.Frostbite.getOrDefault(p.getUniqueId(), 0) * 0.045))
									.setScale(2, RoundingMode.HALF_EVEN)),
					7, Frostskillsinv);
			if (Proficiency.getpro(p) < 1) {
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 9, Frostskillsinv);
				itemset("������(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 10, Frostskillsinv);
				itemset("������(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 11, Frostskillsinv);
				itemset("�ؼҿ뵹��(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 12, Frostskillsinv);
				itemset("ũ���ٽ�(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 13, Frostskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 14, Frostskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 16, Frostskillsinv);
				itemset("������ǳ(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 17, Frostskillsinv);
			} else if (Proficiency.getpro(p) < 2 && Proficiency.getpro(p) >= 1) {
				itemset("�ѱ�", Material.POWDER_SNOW_BUCKET, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "������ ������ �ֺ������� �߰� ���ظ� �ݴϴ�", "�����ð��� �����մϴ�", "",
								ChatColor.BOLD + "4 X "
										+ BigDecimal.valueOf(0.5*(1+bsd.FrozenCrystal.getOrDefault(p.getUniqueId(), 0) * 0.031))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						9, Frostskillsinv);
				itemset("������", Material.SNOW, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� �����¸� ����մϴ�", "(���ط��� ��� ������ ����մϴ�)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(0.76 * (1+bsd.Hailstones.getOrDefault(p.getUniqueId(), 0) * 0.085))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						10, Frostskillsinv);
				itemset("������", Material.SNOWBALL, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� �����̸� ����մϴ�", "(���ط��� ��帧ȭ�� ������ ����մϴ�)", "",
								ChatColor.BOLD + "4 X "
										+ BigDecimal.valueOf(0.54 * (1 + bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0) * 0.0651))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						11, Frostskillsinv);
				itemset("�ؼҿ뵹��", Material.WHITE_TULIP, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� �ؼҿ뵹�̸� ����մϴ�", "(���ط��� �Ŵ��帧 ������ ����մϴ�)",
								"",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.34 * (1 + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0) * 0.03))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, Frostskillsinv);
				itemset("ũ���ٽ�", Material.WHITE_CONCRETE_POWDER, 0, 1, Arrays.asList("�������ظ� �����ϴ�"), 13, Frostskillsinv);
				itemset("����", Material.INFESTED_CHISELED_STONE_BRICKS, 0, 1,
						Arrays.asList("���Է½� ������ ����մϴ�", "������ Ÿ���߿��� �������°� �˴ϴ�"), 14, Frostskillsinv);
				itemset("����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �������ӽð��� �����մϴ�"), 16,
						Frostskillsinv);
				itemset("������ǳ", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]",
						"��ũ���� + �����۴�����", "", ChatColor.BOLD + "60 X 0.32D"), 17, Frostskillsinv);

				itemset("��տ�(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 18, Frostskillsinv);
				itemset("����������(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 19, Frostskillsinv);
				itemset("����Į��(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 20, Frostskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 21, Frostskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 22, Frostskillsinv);
				itemset("���뿵��(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 25, Frostskillsinv);
				itemset("���Ͻô�(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 26, Frostskillsinv);
			} else {
				itemset("�ѱ�", Material.POWDER_SNOW_BUCKET, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "������ ������ �ֺ������� �߰� ���ظ� �ݴϴ�", "�����ð��� �����մϴ�", "",
								ChatColor.BOLD + "4 X "
										+ BigDecimal.valueOf(0.5*(1+bsd.FrozenCrystal.getOrDefault(p.getUniqueId(), 0) * 0.031))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						9, Frostskillsinv);
				itemset("������", Material.SNOW, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� �����¸� ����մϴ�", "(���ط��� ��� ������ ����մϴ�)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(0.76 * (1+bsd.Hailstones.getOrDefault(p.getUniqueId(), 0) * 0.085))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						10, Frostskillsinv);
				itemset("������", Material.SNOWBALL, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� �����̸� ����մϴ�", "(���ط��� ��帧ȭ�� ������ ����մϴ�)", "",
								ChatColor.BOLD + "4 X "
										+ BigDecimal.valueOf(0.54 * (1 + bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0) * 0.0651))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						11, Frostskillsinv);
				itemset("�ؼҿ뵹��", Material.WHITE_TULIP, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� �ؼҿ뵹�̸� ����մϴ�", "(���ط��� �Ŵ��帧 ������ ����մϴ�)",
								"",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.34 * (1 + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0) * 0.03))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, Frostskillsinv);
				itemset("ũ���ٽ�", Material.WHITE_CONCRETE_POWDER, 0, 1, Arrays.asList("�������ظ� �����ϴ�"), 13, Frostskillsinv);
				itemset("����", Material.INFESTED_CHISELED_STONE_BRICKS, 0, 1,
						Arrays.asList("���Է½� ������ ����մϴ�", "������ Ÿ���߿��� �������°� �˴ϴ�"), 14, Frostskillsinv);
				itemset("����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �������ӽð��� �����մϴ�"), 16,
						Frostskillsinv);
				itemset("������ǳ", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]",
						"��ũ���� + �����۴�����", "", ChatColor.BOLD + "60 X 0.32D"), 17, Frostskillsinv);

				itemset("��տ�", Material.ICE, 0, 1, Arrays.asList("Increases Range"), 18, Frostskillsinv);
				itemset("����������", Material.WHITE_SHULKER_BOX, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ������������ ����մϴ�", "(���ط��� ��� ������ ����մϴ�)", "",
								ChatColor.BOLD + "6 X "
										+ BigDecimal.valueOf(0.5 * (1 +bsd.Hailstones.getOrDefault(p.getUniqueId(), 0) * 0.06))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						19, Frostskillsinv);
				ItemStack isw = new ItemStack(Material.NETHERITE_SWORD);
				isw.getItemMeta().setCustomModelData(3006);
				itemset("����Į��", isw, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ����Į���� ����մϴ�", "(���ط��� ��帧ȭ�� ������ ����մϴ�)", "",
								ChatColor.BOLD + "5 X  "
										+ BigDecimal.valueOf(0.42 * (1 + bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0) * 0.0551))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						20, Frostskillsinv);
				itemset("����", Material.PRISMARINE_SHARD, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ������ ����մϴ�", "(���ط��� �Ŵ��帧 ������ ����մϴ�)", "",
								ChatColor.BOLD + "20 X "
										+ BigDecimal.valueOf(0.3 * (1 + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0) * 0.045))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						21, Frostskillsinv);
				itemset("����", Material.WHITE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList("������ ���ð��� �ʱ�ȭ�մϴ�"), 22,
						Frostskillsinv);
				itemset("���뿵��", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("���ݷ°� ������ �����մϴ�", "������ǳ ������ð��� �����մϴ�", "������ �������ӽð��� �����մϴ�"), 25,
						Frostskillsinv);
				itemset("���Ͻô�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]",
						"�޸���+ �����۴�����", "", ChatColor.BOLD + "10 X 1.0D, 10D"), 26, Frostskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("" + Proficiency.getproexp(p)), 27,
					Frostskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1,
					Arrays.asList(ChatColor.AQUA + "SP." + bsd.SkillPoints.getOrDefault(p.getUniqueId(), 0), "",
							"Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"),
					35, Frostskillsinv);

		}

		else {

			itemset("FrozenCrystal", Material.BLUE_ICE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.FrozenCrystal.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[Frost]", "SwapHand", "You can break earlier",
							"by Using one more time", "",
							ChatColor.BOLD + ""
									+ BigDecimal.valueOf(0.765 * (1+bsd.FrozenCrystal.getOrDefault(p.getUniqueId(), 0) * 0.055))
											.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					0, Frostskillsinv);
			itemset("Hailstones", Material.PACKED_ICE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.Hailstones.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[Frost]", "Sneaking + SwapHand", "",
							ChatColor.BOLD + "20 X "
									+ BigDecimal.valueOf(0.16 * (1+bsd.Hailstones.getOrDefault(p.getUniqueId(), 0) * 0.018))
											.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					1, Frostskillsinv);
			itemset("IcicleShot", Material.ICE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[Frost]", "Rightclick", "",
							ChatColor.BOLD + "3 X "
									+ BigDecimal.valueOf(0.32 * (1+bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0) * 0.031))
											.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					2, Frostskillsinv);
			itemset("IceSpikes", Material.PRISMARINE_SHARD, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[Frost]", "Sneaking + Rightclick", "",
							ChatColor.BOLD + "25 X "
									+ BigDecimal.valueOf(0.23 * (1 + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0) * 0.025))
											.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					3, Frostskillsinv);
			itemset("Crack", Material.DIAMOND_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.Crack.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[Frost]", "Hit + Sneaking to Frostbite enemy", "",
							ChatColor.BOLD + " X "
									+ BigDecimal.valueOf(2.5+bsd.Crack.getOrDefault(p.getUniqueId(), 0) * 2.6).setScale(2,
											RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					4, Frostskillsinv);
			itemset("SnowBreeze", Material.SNOW_BLOCK, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.SnowBreeze.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[Frost]", "Jump + LeftClick", "", ChatColor.BOLD + " (0.25D)",
							"Master LV.1"),
					5, Frostskillsinv);
			itemset("Frostbite", Material.BOOK, 0, 1, Arrays.asList(
					ChatColor.AQUA + "LV." + bsd.Frostbite.getOrDefault(p.getUniqueId(), 0), "", "Increases damage",
					"Get Frost Resistance", "Freeze Enemy hit three times for 2s (Cooldown 5s)",
					"Immune to Slow Effect, Freeze", "",
					ChatColor.BOLD + " X "
							+ BigDecimal.valueOf(1.15 * (1 + bsd.Frostbite.getOrDefault(p.getUniqueId(), 0) * 0.045))
									.setScale(2, RoundingMode.HALF_EVEN)),
					7, Frostskillsinv);
			if (Proficiency.getpro(p) < 1) {
				itemset("Chill(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 9,
						Frostskillsinv);
				itemset("Avalanche(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 10,
						Frostskillsinv);
				itemset("SnowBall(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 11,
						Frostskillsinv);
				itemset("PolarVortex(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 12,
						Frostskillsinv);
				itemset("Crevasse(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 13,
						Frostskillsinv);
				itemset("Icefall(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 14,
						Frostskillsinv);
				itemset("ColdBlood(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 16,
						Frostskillsinv);
				itemset("Blizzard(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 17,
						Frostskillsinv);
			} else if (Proficiency.getpro(p) < 2 && Proficiency.getpro(p) >= 1) {
				itemset("Chill", Material.POWDER_SNOW_BUCKET, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Frost]", "Extra Damage After Break",
								"Increases Stun Duration", "",
								ChatColor.BOLD + "4 X "
										+ BigDecimal.valueOf(0.5*(1+bsd.FrozenCrystal.getOrDefault(p.getUniqueId(), 0) * 0.031))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						9, Frostskillsinv);
				itemset("Avalanche", Material.SNOW, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Frost]", "Use Avalanche When Use Once More",
								"(Damage Affected By Hailstones)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(0.76 * (1+bsd.Hailstones.getOrDefault(p.getUniqueId(), 0) * 0.085))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						10, Frostskillsinv);
				itemset("SnowBall", Material.SNOWBALL, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Frost]", "Shot SnowBall When Use Once More",
								"(Damage Affected By IcicleShot)", "",
								ChatColor.BOLD + "4 X "
										+ BigDecimal.valueOf(0.54 * (1 + bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0) * 0.0651))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						11, Frostskillsinv);
				itemset("PolarVortex", Material.WHITE_TULIP, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Frost]", "Use PolarVortex When Use Once More",
								"(Damage Affected By IceSpikes)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.34 * (1 + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0) * 0.03))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, Frostskillsinv);
				itemset("Crevasse", Material.WHITE_CONCRETE_POWDER, 0, 1, Arrays.asList("Inflicts Splash Damage"), 13,
						Frostskillsinv);
				itemset("Icefall", Material.INFESTED_CHISELED_STONE_BRICKS, 0, 1,
						Arrays.asList("Use Icefall When Use Once More", "Set Invulnerable While Riding Icefall"), 14,
						Frostskillsinv);
				itemset("ColdBlood", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("Increases Whole Skills Damage", "Increases Frostbite Duration"), 16,
						Frostskillsinv);
				itemset("Blizzard", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[Frost]",
						"Sneaking + ThrowItem", "", ChatColor.BOLD + "60 X 0.32D"), 17, Frostskillsinv);

				itemset("HugeBreak(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 18,
						Frostskillsinv);
				itemset("Extremecold(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 19,
						Frostskillsinv);
				itemset("IceBlades(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 20,
						Frostskillsinv);
				itemset("GlacialDrift(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 21,
						Frostskillsinv);
				itemset("Regelation(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 22,
						Frostskillsinv);
				itemset("AbsoluteZero(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 25,
						Frostskillsinv);
				itemset("Ice Age(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 26,
						Frostskillsinv);
			} else {
				itemset("Chill", Material.POWDER_SNOW_BUCKET, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Frost]", "Extra Damage After Break",
								"Increases Stun Duration", "",
								ChatColor.BOLD + "4 X "
										+ BigDecimal.valueOf(0.5*(1+bsd.FrozenCrystal.getOrDefault(p.getUniqueId(), 0) * 0.031))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						9, Frostskillsinv);
				itemset("Avalanche", Material.SNOW, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Frost]", "Use Avalanche When Use Once More",
								"(Damage Affected By Hailstones)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(0.76 * (1+bsd.Hailstones.getOrDefault(p.getUniqueId(), 0) * 0.085))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						10, Frostskillsinv);
				itemset("SnowBall", Material.SNOWBALL, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Frost]", "Shot SnowBall When Use Once More",
								"(Damage Affected By IcicleShot)", "",
								ChatColor.BOLD + "4 X "
										+ BigDecimal.valueOf(0.54 * (1 + bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0) * 0.0651))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						11, Frostskillsinv);
				itemset("PolarVortex", Material.WHITE_TULIP, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Frost]", "Use PolarVortex When Use Once More",
								"(Damage Affected By IceSpikes)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.34 * (1 + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0) * 0.03))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, Frostskillsinv);
				itemset("Crevasse", Material.WHITE_CONCRETE_POWDER, 0, 1, Arrays.asList("Inflicts Splash Damage"), 13,
						Frostskillsinv);
				itemset("Icefall", Material.INFESTED_CHISELED_STONE_BRICKS, 0, 1,
						Arrays.asList("Use Icefall When Use Once More", "Set Invulnerable While Riding Icefall"), 14,
						Frostskillsinv);
				itemset("ColdBlood", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("Increases Whole Skills Damage", "Increases Frostbite Duration"), 16,
						Frostskillsinv);
				itemset("Blizzard", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[Frost]",
						"Sneaking + ThrowItem", "", ChatColor.BOLD + "60 X 0.32D"), 17, Frostskillsinv);

				itemset("HugeBreak", Material.ICE, 0, 1, Arrays.asList("Increases Range"), 18, Frostskillsinv);
				itemset("Extremecold", Material.WHITE_SHULKER_BOX, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Frost]", "Use Extremecold When Use Once More",
								"(Damage Affected By Hailstones)", "",
								ChatColor.BOLD + "6 X "
										+ BigDecimal.valueOf(0.5 * (1 +bsd.Hailstones.getOrDefault(p.getUniqueId(), 0) * 0.06))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						19, Frostskillsinv);
				ItemStack isw = new ItemStack(Material.NETHERITE_SWORD);
				isw.getItemMeta().setCustomModelData(3006);
				itemset("IceBlades", isw, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Frost]", "Swing IceBlades When Use Once More",
								"(Damage Affected By IcicleShot)", "",
								ChatColor.BOLD + "5 X  "
										+ BigDecimal.valueOf(0.42 * (1 + bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0) * 0.0551))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						20, Frostskillsinv);
				itemset("GlacialDrift", Material.PRISMARINE_SHARD, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Frost]", "Use GlacialDrift When Use Once More",
								"(Damage Affected By IceSpikes)", "",
								ChatColor.BOLD + "20 X "
										+ BigDecimal.valueOf(0.3 * (1 + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0) * 0.045))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						21, Frostskillsinv);
				itemset("Regelation", Material.WHITE_GLAZED_TERRACOTTA, 0, 1,
						Arrays.asList("Removes Frostbite Cooldown Of Hit Enemies"), 22, Frostskillsinv);
				itemset("AbsoluteZero", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor",
						"Decrease Blizzard Cooldown", "Increases Frostbite Duration"), 25, Frostskillsinv);
				itemset("Ice Age", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE + "[Frost]",
						"Sprinting + ThrowItem", "", ChatColor.BOLD + "10 X 1.0D, 10D"), 26, Frostskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("" + Proficiency.getproexp(p)),
					27, Frostskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1,
					Arrays.asList(ChatColor.AQUA + "SP." + bsd.SkillPoints.getOrDefault(p.getUniqueId(), 0), "",
							"Click if you want to reset your skill's levels"),
					35, Frostskillsinv);

		}

		p.openInventory(Frostskillsinv);
	}

}
