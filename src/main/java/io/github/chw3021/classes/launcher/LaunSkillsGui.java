package io.github.chw3021.classes.launcher;

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

public class LaunSkillsGui extends SkillsGui {

	public void Launskillsinv(Player p) {
		String path = new File("").getAbsolutePath();
		LaunSkillsData lsd = new LaunSkillsData(
				LaunSkillsData.loadData(path + "/plugins/RPGskills/LaunskillsData.data"));
		Inventory Launskillsinv = Bukkit.createInventory(null, 54, "Launskills");
		Obtained.itemset(p, Launskillsinv);

		if (p.getLocale().equalsIgnoreCase("ko_kr")) {

			itemset("ȭ�캯��", Material.ARROW, 1, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.ArrowChange.getOrDefault(p.getUniqueId(), 0), "",
							"��ũ���� + �����۹ٲٱ�(���콺��)", ChatColor.UNDERLINE + "[ȭ�� �迭]" + "����ȭ��: ����, ��������� �� ���� ����",
							"���� �¿�ϴ�", "", ChatColor.UNDERLINE + "[�� �迭]" + "����ȭ��: ��Ż��, ����, �״� ���鿡�� �� ���� ����",
							"��ȭȿ���� �����մϴ�", "", ChatColor.UNDERLINE + "[�ٶ� �迭]" + "����ȭ��: ���ߺξ� ȿ���� �����մϴ�",
							"����ȭ��� ���� ������", "�ش���ġ�� �����̵� �մϴ�(���ð� 4��)", "",
							ChatColor.UNDERLINE + "[���� �迭]" + "������ȭ��: �𵥵忡�� �� ���� ����", "�߱�ȿ���� �����մϴ�", "Master LV.1"),
					0, Launskillsinv);
			itemset("ȭ�켼��", Material.TIPPED_ARROW, 2, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.ArrowRain.getOrDefault(p.getUniqueId(), 0), "",
							"��ũ���� + �չٲٱ�", "",
							ChatColor.BOLD + "10 X "
									+ BigDecimal.valueOf(0.08*(1+lsd.ArrowRain.getOrDefault(p.getUniqueId(), 0) * 0.008))
											.setScale(2, RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					1, Launskillsinv);
			itemset("����", Material.FIREWORK_ROCKET, 3, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.Discharge.getOrDefault(p.getUniqueId(), 0), "", "��Ŭ��",
							"",
							ChatColor.BOLD + "3 X "
									+ BigDecimal.valueOf(0.7 * (1 +lsd.Discharge.getOrDefault(p.getUniqueId(), 0) * 0.076))
											.setScale(2, RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					2, Launskillsinv);
			itemset("�Ŵ�ȭ��", Material.TIPPED_ARROW, 4, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.GiantArrow.getOrDefault(p.getUniqueId(), 0), "",
							"���� + �չٲٱ�", "",
							ChatColor.BOLD +""+ BigDecimal.valueOf(1.3*(1+lsd.GiantArrow.getOrDefault(p.getUniqueId(), 0) * 0.156))
											.setScale(2, RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					3, Launskillsinv);
			itemset("����", Material.GUNPOWDER, 5, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.Explosion.getOrDefault(p.getUniqueId(), 0), "",
							"��ũ���� + �߻�", "",
							ChatColor.BOLD + " X "
									+ BigDecimal.valueOf(0.7*(1+lsd.Explosion.getOrDefault(p.getUniqueId(), 0) * 0.076))
											.setScale(2, RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					4, Launskillsinv);
			itemset("����", Material.FIREWORK_STAR, 6, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.ChargingShot.getOrDefault(p.getUniqueId(), 0), "",
							"��ũ���� + ��Ŭ��", "",
							ChatColor.BOLD + " X "
									+ BigDecimal.valueOf(1.8 * (1+lsd.ChargingShot.getOrDefault(p.getUniqueId(), 0) * 0.18))
											.setScale(2, RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					5, Launskillsinv);
			itemset("���°�ȭ", Material.BOOK, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.MagicArrow.getOrDefault(p.getUniqueId(), 0), "",
							"���ݷ��� �����մϴ�", "",
							ChatColor.BOLD + "X"
									+ BigDecimal.valueOf(1 + lsd.MagicArrow.getOrDefault(p.getUniqueId(), 0) * 0.0453)
											.setScale(2, RoundingMode.HALF_EVEN)),
					7, Launskillsinv);

			if (Proficiency.getpro(p) < 1) {
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 9, Launskillsinv);
				itemset("ȭ��м�(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 10, Launskillsinv);
				itemset("��������(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 11, Launskillsinv);
				itemset("�(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 12, Launskillsinv);
				itemset("�ҿ뵹��(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 13, Launskillsinv);
				itemset("��������(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 14, Launskillsinv);
				itemset("���Ҽ�ȯ(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 16, Launskillsinv);
				itemset("�����ȭ��(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 17, Launskillsinv);
			} else if (Proficiency.getpro(p) < 2 && Proficiency.getpro(p) >= 1) {
				itemset("����", Material.LAVA_BUCKET, 0, 1,
						Arrays.asList("ȭ�������� ����ȭ��� �¾���ȭ��� �����մϴ�", "��: ����+��, �¾�: ��+����"), 9, Launskillsinv);
				itemset("ȭ��м�", Material.SPORE_BLOSSOM, 0, 1,
						Arrays.asList("���Է½� ȭ��м��� ����մϴ�", "(���ط��� ȭ�켼�� ������ ����մϴ�)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.28 * (1+ lsd.ArrowRain.getOrDefault(p.getUniqueId(), 0) * 0.034))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						10, Launskillsinv);
				itemset("��������", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList("���Է½� ȭ��� ���ϵ��� ���� ���� ���߽�ŵ�ϴ�"), 11,
						Launskillsinv);
				itemset("�", Material.CHISELED_QUARTZ_BLOCK, 0, 1,
						Arrays.asList("���Է½� ��� ����մϴ�", "(���ط��� �Ŵ�ȭ�� ������ ����մϴ�)", "",
								ChatColor.BOLD + "6 X "
										+ BigDecimal.valueOf(0.34 * (1+ lsd.GiantArrow.getOrDefault(p.getUniqueId(), 0) * 0.04))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, Launskillsinv);
				itemset("�ҿ뵹��", Material.BUBBLE_CORAL_BLOCK, 0, 1,
						Arrays.asList("ȭ�����߽� �ҿ뵹�̸� ����ŵ�ϴ�", "(���ط��� ���� ������ ����մϴ�)", "",
								ChatColor.BOLD + " X "
										+ BigDecimal.valueOf(0.87 * (1+lsd.Explosion.getOrDefault(p.getUniqueId(), 0) * 0.09))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						13, Launskillsinv);
				itemset("��������", Material.BOW, 0, 1, Arrays.asList("ȭ���� ����Ӽ��� �����ϴ�(�ִ�5)"), 14, Launskillsinv);
				itemset("���Ҽ�ȯ", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� ����մϴ�", "ȭ�캯��� �̵��ӵ��� ����ȿ���� ����ϴ�"),
						16, Launskillsinv);
				itemset("�����ȭ��", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("��ũ���� + �����۴�����", "", ChatColor.BOLD + "�������� ����� ���� ���� ����մϴ�", ChatColor.BOLD + "0.28D X ����� ������� Ÿ�ݼ�"), 17, Launskillsinv);

				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 19, Launskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 20, Launskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 21, Launskillsinv);
				itemset("�л�(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 22, Launskillsinv);
				itemset("�׼���ȭ(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 25, Launskillsinv);
				itemset("�༺ȭ��(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 26, Launskillsinv);
			} else {
				itemset("����", Material.LAVA_BUCKET, 0, 1,
						Arrays.asList("ȭ�������� ����ȭ��� �¾���ȭ��� �����մϴ�", "��: ����+��, �¾�: ��+����"), 9, Launskillsinv);
				itemset("ȭ��м�", Material.SPORE_BLOSSOM, 0, 1,
						Arrays.asList("���Է½� ȭ��м��� ����մϴ�", "(���ط��� ȭ�켼�� ������ ����մϴ�)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.28 * (1 + lsd.ArrowRain.getOrDefault(p.getUniqueId(), 0) * 0.034))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						10, Launskillsinv);
				itemset("��������", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList("���Է½� ȭ��� ���ϵ��� ���� ���� ���߽�ŵ�ϴ�"), 11,
						Launskillsinv);
				itemset("�", Material.CHISELED_QUARTZ_BLOCK, 0, 1,
						Arrays.asList("���Է½� ��� ����մϴ�", "(���ط��� �Ŵ�ȭ�� ������ ����մϴ�)", "",
								ChatColor.BOLD + "6 X "
										+ BigDecimal.valueOf(0.34 * (1+lsd.GiantArrow.getOrDefault(p.getUniqueId(), 0) * 0.04))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, Launskillsinv);
				itemset("�ҿ뵹��", Material.BUBBLE_CORAL_BLOCK, 0, 1,
						Arrays.asList("ȭ�����߽� �ҿ뵹�̸� ����ŵ�ϴ�", "(���ط��� ���� ������ ����մϴ�)", "",
								ChatColor.BOLD + " X "
										+ BigDecimal.valueOf(0.87 * (1+lsd.Explosion.getOrDefault(p.getUniqueId(), 0) * 0.09))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						13, Launskillsinv);
				itemset("��������", Material.BOW, 0, 1, Arrays.asList("ȭ���� ����Ӽ��� �����ϴ�(�ִ�5)"), 14, Launskillsinv);
				itemset("���Ҽ�ȯ", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� ����մϴ�", "ȭ�캯��� �̵��ӵ��� ����ȿ���� ����ϴ�"),
						16, Launskillsinv);
				itemset("�����ȭ��", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("��ũ���� + �����۴�����", "", ChatColor.BOLD + "�������� ����� ���� ���� ����մϴ�", ChatColor.BOLD + "0.28D X ����� ������� Ÿ�ݼ�"), 17, Launskillsinv);

				itemset("����", Material.END_CRYSTAL, 0, 1,
						Arrays.asList("���Է½� ���ϸ� ����մϴ�", "(���ط��� ȭ�켼�� ������ ����մϴ�)", "",
								ChatColor.BOLD + "3 X "
										+ BigDecimal.valueOf(0.5 * (1+lsd.ArrowRain.getOrDefault(p.getUniqueId(), 0) * 0.05))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						19, Launskillsinv);
				itemset("����", Material.END_ROD, 0, 1,
						Arrays.asList("�������߽� ������ ��ȯ�մϴ�", "(���ط��� ���� ������ ����մϴ�)", "",
								ChatColor.BOLD + "3 X "
										+ BigDecimal.valueOf(0.36 * (1 +lsd.Discharge.getOrDefault(p.getUniqueId(), 0) * 0.046))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						20, Launskillsinv);
				itemset("����", Material.SPLASH_POTION, 0, 1,
						Arrays.asList("���Է½� ������ ����մϴ�", "(���ط��� �Ŵ�ȭ�� ������ ����մϴ�)", "",
								ChatColor.BOLD + " X "
										+ BigDecimal.valueOf(1.5 * (1+ lsd.GiantArrow.getOrDefault(p.getUniqueId(), 0) * 0.22))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						21, Launskillsinv);
				itemset("�л�", Material.BOW, 0, 1, Arrays.asList("�������� ȭ���� �߻��մϴ�"), 22, Launskillsinv);
				itemset("�׼���ȭ", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("���ݷ°� ������ �����մϴ�", "����� ȭ�� ������ð��� �����մϴ�", "�̼�,����ȿ���� �����ǰ�", "�߰����ôɷ��� �߰��� ����ϴ�"),
						25, Launskillsinv);
				itemset("�༺ȭ��", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("�޸��� + �����۴�����", "", ChatColor.BOLD + "�Ÿ� X 1.0D, 13.0D"), 26, Launskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("" + Proficiency.getproexp(p)), 27,
					Launskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1,
					Arrays.asList(ChatColor.AQUA + "SP." + lsd.SkillPoints.getOrDefault(p.getUniqueId(), 0), "",
							"Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"),
					35, Launskillsinv);

		} else {

			itemset("ArrowChange", Material.ARROW, 1, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.ArrowChange.getOrDefault(p.getUniqueId(), 0), "",
							"Sneaking + ChangeItem(MouseWheel)",
							ChatColor.UNDERLINE + "[Flame]" + "Flame: More Damage To Arthropod & Water Mob", "",
							ChatColor.UNDERLINE + "[Water]" + "Aqua: More Damage To Pilliager, Ender & Nether Mob",
							"Apply Slow Effect", "", ChatColor.UNDERLINE + "[Wind]" + "Ender: Apply Levitation Effect",
							"If you Hit Block When Using EnderArrow", "You'll Teleport Hit Position (Cooldown 4s)", "",
							ChatColor.UNDERLINE + "[Lightning]" + "Lightning: More Damage To Undead", "Master LV.1"),
					0, Launskillsinv);
			itemset("ArrowRain", Material.TIPPED_ARROW, 2, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.ArrowRain.getOrDefault(p.getUniqueId(), 0), "",
							"Sneaking + SwapHand", "",
							ChatColor.BOLD + "10 X "
									+ BigDecimal.valueOf(0.08*(1+lsd.ArrowRain.getOrDefault(p.getUniqueId(), 0) * 0.008))
											.setScale(2, RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					1, Launskillsinv);
			itemset("Discharge", Material.FIREWORK_ROCKET, 3, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.Discharge.getOrDefault(p.getUniqueId(), 0), "",
							"LeftClick", "",
							ChatColor.BOLD + "3 X "
									+ BigDecimal.valueOf(0.7 * (1 +lsd.Discharge.getOrDefault(p.getUniqueId(), 0) * 0.076))
											.setScale(2, RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					2, Launskillsinv);
			itemset("GiantArrow", Material.TIPPED_ARROW, 4, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.GiantArrow.getOrDefault(p.getUniqueId(), 0), "",
							"Jump + SwapHand", "",
							ChatColor.BOLD +""+ BigDecimal.valueOf(1.3*(1+lsd.GiantArrow.getOrDefault(p.getUniqueId(), 0) * 0.156))
							.setScale(2, RoundingMode.HALF_EVEN)+ "D",
							"Master LV.50"),
					3, Launskillsinv);
			itemset("Explosion", Material.GUNPOWDER, 5, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.Explosion.getOrDefault(p.getUniqueId(), 0), "",
							"Sneaking + Shoot", "",
							ChatColor.BOLD + " X "
									+ BigDecimal.valueOf(0.7*(1+lsd.Explosion.getOrDefault(p.getUniqueId(), 0) * 0.076))
											.setScale(2, RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					4, Launskillsinv);
			itemset("ChargingShot", Material.FIREWORK_STAR, 6, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.ChargingShot.getOrDefault(p.getUniqueId(), 0), "",
							"Sneaking + LeftClick", "",
							ChatColor.BOLD + " X "
									+ BigDecimal.valueOf(1.8 * (1+lsd.ChargingShot.getOrDefault(p.getUniqueId(), 0) * 0.18))
											.setScale(2, RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					5, Launskillsinv);
			itemset("MagicArrow", Material.BOOK, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.MagicArrow.getOrDefault(p.getUniqueId(), 0), "",
							"Increases your damage", "",
							ChatColor.BOLD + "X"
									+ BigDecimal.valueOf(1 + lsd.MagicArrow.getOrDefault(p.getUniqueId(), 0) * 0.0453)
											.setScale(2, RoundingMode.HALF_EVEN)),
					7, Launskillsinv);

			if (Proficiency.getpro(p) < 1) {
				itemset("Fusion(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 9,
						Launskillsinv);
				itemset("ArrowFountain(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 10,
						Launskillsinv);
				itemset("RocketHit(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 11,
						Launskillsinv);
				itemset("ShootingStars(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 12,
						Launskillsinv);
				itemset("Tornado(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 13,
						Launskillsinv);
				itemset("ArrowForce(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 14,
						Launskillsinv);
				itemset("ElementalCycle(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 16,
						Launskillsinv);
				itemset("AbsorbingArrow(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 17,
						Launskillsinv);
			} else if (Proficiency.getpro(p) < 2 && Proficiency.getpro(p) >= 1) {
				itemset("Fusion", Material.LAVA_BUCKET, 0, 1, Arrays.asList("Changes ArrowTypes to Lunar & Solar",
						"Lunar: Ender+Aqua, Solar: Flame+Lightning"), 9, Launskillsinv);
				itemset("ArrowFountain", Material.SPORE_BLOSSOM, 0, 1,
						Arrays.asList("Use ArrowFountain When Use Once More", "(Damage Affected By ArrowRain)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.28 * (1 + lsd.ArrowRain.getOrDefault(p.getUniqueId(), 0) * 0.034))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						10, Launskillsinv);
				itemset("RocketHit", Material.SPECTRAL_ARROW, 0, 1,
						Arrays.asList("Hit Rocket with Arrow to Explode Earlier", "When Use Once More"), 11,
						Launskillsinv);
				itemset("Meteor", Material.CHISELED_QUARTZ_BLOCK, 0, 1,
						Arrays.asList("Call ShootingStars When Use Once More", "(Damage Affected By GiantArrow)", "",
								ChatColor.BOLD + "6 X "
										+ BigDecimal.valueOf(0.34 * (1+lsd.GiantArrow.getOrDefault(p.getUniqueId(), 0) * 0.04))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, Launskillsinv);
				itemset("Tornado", Material.BUBBLE_CORAL_BLOCK, 0, 1,
						Arrays.asList("Call Tornado When Hit", "(Damage Affected By Explosion)", "",
								ChatColor.BOLD + " X "
										+ BigDecimal.valueOf(0.87 * (1+lsd.Explosion.getOrDefault(p.getUniqueId(), 0) * 0.09))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						13, Launskillsinv);
				itemset("ArrowForce", Material.BOW, 0, 1, Arrays.asList("Arrow Can Pierce Enemies(Max 5)"), 14,
						Launskillsinv);
				itemset("ElementalCycle", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage",
						"Get Speed&Jump Effect When You Change ArrowType"), 16, Launskillsinv);
				itemset("AbsorbingArrow", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("Sneaking + ThrowItem", "", ChatColor.BOLD + "Absorbs skills which are within range", ChatColor.BOLD + "0.28D X Hits of skills"), 17, Launskillsinv);

				itemset("Galaxy(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 19,
						Launskillsinv);
				itemset("Comet(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 20,
						Launskillsinv);
				itemset("Nebula(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 21,
						Launskillsinv);
				itemset("Dispersion(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 22,
						Launskillsinv);
				itemset("StellarEvolution(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 25,
						Launskillsinv);
				itemset("Planet Arrow(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 26,
						Launskillsinv);
			} else {
				itemset("Fusion", Material.LAVA_BUCKET, 0, 1, Arrays.asList("Changes ArrowTypes to Lunar & Solar",
						"Lunar: Ender+Aqua, Solar: Flame+Lightning"), 9, Launskillsinv);
				itemset("ArrowFountain", Material.SPORE_BLOSSOM, 0, 1,
						Arrays.asList("Use ArrowFountain When Use Once More", "(Damage Affected By ArrowRain)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.28 * (1 + lsd.ArrowRain.getOrDefault(p.getUniqueId(), 0) * 0.034))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						10, Launskillsinv);
				itemset("RocketHit", Material.SPECTRAL_ARROW, 0, 1,
						Arrays.asList("Hit Rocket with Arrow to Explode Earlier", "When Use Once More"), 11,
						Launskillsinv);
				itemset("Meteor", Material.CHISELED_QUARTZ_BLOCK, 0, 1,
						Arrays.asList("Call ShootingStars When Use Once More", "(Damage Affected By GiantArrow)", "",
								ChatColor.BOLD + "6 X "
										+ BigDecimal.valueOf(0.34 * (1+lsd.GiantArrow.getOrDefault(p.getUniqueId(), 0) * 0.04))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, Launskillsinv);
				itemset("Tornado", Material.BUBBLE_CORAL_BLOCK, 0, 1,
						Arrays.asList("Call Tornado When Hit", "(Damage Affected By Explosion)", "",
								ChatColor.BOLD + " X "
										+ BigDecimal.valueOf(0.87 * (1+lsd.Explosion.getOrDefault(p.getUniqueId(), 0) * 0.09))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						13, Launskillsinv);
				itemset("ArrowForce", Material.BOW, 0, 1, Arrays.asList("Arrow Can Pierce Enemies(Max 5)"), 14,
						Launskillsinv);
				itemset("ElementalCycle", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage",
						"Get Speed&Jump Effect When You Change ArrowType"), 16, Launskillsinv);
				itemset("AbsorbingArrow", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("Sneaking + ThrowItem", "", ChatColor.BOLD + "Absorbs skills which are within range", ChatColor.BOLD + "0.28D X Hits of skills"), 17, Launskillsinv);

				itemset("Galaxy", Material.END_CRYSTAL, 0, 1,
						Arrays.asList("Summon Galaxy When Use Once More", "(Damage Affected By ArrowRain)", "",
								ChatColor.BOLD + "3 X "
										+ BigDecimal.valueOf(0.5 * (1+lsd.ArrowRain.getOrDefault(p.getUniqueId(), 0) * 0.05))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						19, Launskillsinv);
				itemset("Comet", Material.END_ROD, 0, 1,
						Arrays.asList("Call Comet When Rocket Explode", "(Damage Affected By Discharge)", "",
								ChatColor.BOLD + "3 X "
										+ BigDecimal.valueOf(0.36 * (1 +lsd.Discharge.getOrDefault(p.getUniqueId(), 0) * 0.046))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						20, Launskillsinv);
				itemset("Nebula", Material.SPLASH_POTION, 0, 1,
						Arrays.asList("Summon Nebula When Use Once More", "(Damage Affected By GiantArrow)", "",
								ChatColor.BOLD + " X "
										+ BigDecimal.valueOf(1.5 * (1+ lsd.GiantArrow.getOrDefault(p.getUniqueId(), 0) * 0.22))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						21, Launskillsinv);
				itemset("Dispersion", Material.BOW, 0, 1, Arrays.asList("Shot Several Arrows"), 22, Launskillsinv);
				itemset("StellarEvolution", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("Increases Damage & Armor", "Decrease AbsorbingArrow Cooldown",
								"Increases Speed & Jump Effect", "Get NightVision Effect When You Change ArrowType"),
						25, Launskillsinv);
				itemset("Planet Arrow", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("Sprinting + ThrowItem", "", ChatColor.BOLD + "Distance X 1.0D, 13.0D"), 26,
						Launskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("" + Proficiency.getproexp(p)),
					27, Launskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1,
					Arrays.asList(ChatColor.AQUA + "SP." + lsd.SkillPoints.getOrDefault(p.getUniqueId(), 0), "",
							"Click if you want to reset your skill's levels"),
					35, Launskillsinv);
		}

		p.openInventory(Launskillsinv);
	}

}
