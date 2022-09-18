package io.github.chw3021.classes.engineer;

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

public class EngSkillsGui extends SkillsGui {

	public void EngSkillsinv(Player p) {
		String path = new File("").getAbsolutePath();
		EngSkillsData esd = new EngSkillsData(EngSkillsData.loadData(path + "/plugins/RPGskills/EngSkillsData.data"));
		Inventory Engskillsinv = Bukkit.createInventory(null, 54, "Engskills");

		Obtained.itemset(p, Engskillsinv);

		if (p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("�߷���", Material.WOODEN_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.Graviton.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[���� �迭]", "�չٲٱ�", "",
							ChatColor.BOLD + " 10 X "
									+ BigDecimal.valueOf(0.12 *(1 + esd.Graviton.getOrDefault(p.getUniqueId(), 0) * 0.0085))
											.setScale(2, RoundingMode.HALF_EVEN)+"D"
									+ " , "
									+ BigDecimal.valueOf(0.52 * (1 + esd.Graviton.getOrDefault(p.getUniqueId(), 0) * 0.05))
											.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					0, Engskillsinv);
			itemset("������", Material.STONE_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.X_ray.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[���� �迭]", "��ũ���� + ��Ŭ��", "���� ���ݷ��� ����ϴ�", "",
							ChatColor.BOLD + "1 X "
									+ BigDecimal.valueOf(0.32 * (1 +esd.X_ray.getOrDefault(p.getUniqueId(), 0) * 0.024)).setScale(2,
											RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					1, Engskillsinv);
			itemset("�ڼ�", Material.STONE_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.Magnetic.getOrDefault(p.getUniqueId(), 0), "",
							"�������� + ��ũ����", "Master LV.1"),
					2, Engskillsinv);
			itemset("��������", Material.IRON_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.Electrostatic.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[���� �迭]", "��Ŭ��", "",
							ChatColor.BOLD + "8 X "
									+ BigDecimal.valueOf(0.1 * (1+esd.Electrostatic.getOrDefault(p.getUniqueId(), 0) * 0.0078))
											.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					3, Engskillsinv);
			itemset("��Ʈ��", Material.GOLDEN_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.Jetpack.getOrDefault(p.getUniqueId(), 0), "", "��Ŭ�� + ����",
							"���� �������� �鿪�� �ǰ�", "������ �ֺ������� ���ظ� �ݴϴ�", "(���ط��� ���̿� ����մϴ�)", "",
							ChatColor.BOLD + "0.3D X ���� ", "Master LV.1"),
					4, Engskillsinv);
			itemset("�߻��", Material.DIAMOND_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.Dispenser.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[���� �迭]", "�չٲٱ� + ��ũ����", "",
							ChatColor.BOLD + " 20 X "
									+ BigDecimal.valueOf(0.06 * (1+esd.Dispenser.getOrDefault(p.getUniqueId(), 0) * 0.006))
											.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					5, Engskillsinv);
			itemset("������Ʈ", Material.BOOK, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.CombatSuit.getOrDefault(p.getUniqueId(), 0), "",
							"���ݷ��� �����մϴ�", "�ִ�ü���� 20%�� �Ѵ� ���ط��� ���ҽ�ŵ�ϴ�", "",
							ChatColor.BOLD + " X " + BigDecimal
									.valueOf(1.2 * (1 + esd.CombatSuit.getOrDefault(p.getUniqueId(), 0) * 0.03935))
									.setScale(2, RoundingMode.HALF_EVEN)),
					6, Engskillsinv);
			if (Proficiency.getpro(p) < 1) {
				itemset("��������ü(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 9, Engskillsinv);
				itemset("EMP(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 10, Engskillsinv);
				itemset("���ڱ���(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 11, Engskillsinv);
				itemset("����ݷ�(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 12, Engskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 13, Engskillsinv);
				itemset("������(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 14, Engskillsinv);
				itemset("����Ŭ��(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 15, Engskillsinv);
				itemset("������(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/29315"), 17, Engskillsinv);
			} else if (Proficiency.getpro(p) < 2 && Proficiency.getpro(p) >= 1) {
				itemset("��������ü", Material.AZURE_BLUET, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ��������ü�� �߻��մϴ�", "(���ط��� �߷��� ������ ����մϴ�)", "",
								ChatColor.BOLD + "20 X "
										+ BigDecimal.valueOf(0.25*(1+esd.Graviton.getOrDefault(p.getUniqueId(), 0) * 0.025))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						9, Engskillsinv);
				itemset("EMP", Material.SCULK_SENSOR, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� EMP�� �߻��մϴ�", "(���ط��� ������ ������ ����մϴ�)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(0.7 * (1+esd.X_ray.getOrDefault(p.getUniqueId(), 0) * 0.057))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						10, Engskillsinv);
				itemset("���ڱ���", Material.LODESTONE, 0, 1, Arrays.asList("���������� ����ü� �ֽ��ϴ�"), 11, Engskillsinv);
				itemset("����ݷ�", Material.LIGHTNING_ROD, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ����ݷ��� ��ġ�մϴ�", "(���ط��� �������� ������ ����մϴ�)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(2.1*(1+esd.Electrostatic.getOrDefault(p.getUniqueId(), 0) * 0.2))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						12, Engskillsinv);
				itemset("����", Material.BARREL, 0, 1, Arrays.asList("���Է½� �������� ������ �̵��մϴ�"), 13, Engskillsinv);
				itemset("������", Material.OBSERVER, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� �����⸦ ��ġ�մϴ�", "(���ط��� �߻�� ������ ����մϴ�)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.1*(1+esd.Dispenser.getOrDefault(p.getUniqueId(), 0) * 0.006))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						14, Engskillsinv);
				itemset("����Ŭ��", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("���ݷ��� �����մϴ�", "��ų ���� �ñر⸦ ������ ��� ��ų����", "���� ���ð��� 0.5�� �����մϴ�"), 15,
						Engskillsinv);
				itemset("������", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("��ũ���� + �����۴�����", "", ChatColor.BOLD + "5 X 2.2D"), 17, Engskillsinv);

				itemset("���ڱ˵�(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 18, Engskillsinv);
				itemset("�߷°���(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 19, Engskillsinv);
				itemset("���߷���(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 21, Engskillsinv);
				itemset("���丮(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 23, Engskillsinv);
				itemset("���ڷ�(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 24, Engskillsinv);
				itemset("��Ȧ(���)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("�䱸 ���õ�: " + Proficiency.getproexp(p) + "/155015"), 26, Engskillsinv);
			} else {
				itemset("��������ü", Material.AZURE_BLUET, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ��������ü�� �߻��մϴ�", "(���ط��� �߷��� ������ ����մϴ�)", "",
								ChatColor.BOLD + "20 X "
										+ BigDecimal.valueOf(0.25*(1+esd.Graviton.getOrDefault(p.getUniqueId(), 0) * 0.025))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						9, Engskillsinv);
				itemset("EMP", Material.SCULK_SENSOR, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� EMP�� �߻��մϴ�", "(���ط��� ������ ������ ����մϴ�)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(0.7 * (1+esd.X_ray.getOrDefault(p.getUniqueId(), 0) * 0.057))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						10, Engskillsinv);
				itemset("���ڱ���", Material.LODESTONE, 0, 1, Arrays.asList("���������� ����ü� �ֽ��ϴ�"), 11, Engskillsinv);
				itemset("����ݷ�", Material.LIGHTNING_ROD, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ����ݷ��� ��ġ�մϴ�", "(���ط��� �������� ������ ����մϴ�)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(2.1*(1+esd.Electrostatic.getOrDefault(p.getUniqueId(), 0) * 0.2))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						12, Engskillsinv);
				itemset("����", Material.BARREL, 0, 1, Arrays.asList("���Է½� �������� ������ �̵��մϴ�"), 13, Engskillsinv);
				itemset("������", Material.OBSERVER, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� �����⸦ ��ġ�մϴ�", "(���ط��� �߻�� ������ ����մϴ�)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.1*(1+esd.Dispenser.getOrDefault(p.getUniqueId(), 0) * 0.006))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						14, Engskillsinv);
				itemset("����Ŭ��", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("���ݷ��� �����մϴ�", "��ų ���� �ñر⸦ ������ ��� ��ų����", "���� ���ð��� 0.5�� �����մϴ�"), 15,
						Engskillsinv);
				itemset("������", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("��ũ���� + �����۴�����", "", ChatColor.BOLD + "5 X 2.2D"), 17, Engskillsinv);
				

				itemset("���ڱ˵�", Material.BUCKET, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ���ڱ˵��� �����մϴ�", "(���ط��� �߷��� ������ ����մϴ�)", "",
								ChatColor.BOLD + "13 X "
										+ BigDecimal.valueOf(0.2*(1+esd.Graviton.getOrDefault(p.getUniqueId(), 0) * 0.045))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						18, Engskillsinv);
				itemset("�߷°���", Material.QUARTZ, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� �߷°����� ����մϴ�", "(���ط��� ������ ������ ����մϴ�)", "",
								ChatColor.BOLD + "1 X "+ BigDecimal.valueOf(0.76*(1+esd.X_ray.getOrDefault(p.getUniqueId(), 0) * 0.045))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						19, Engskillsinv);
				itemset("���߷���", Material.PISTON, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ���߷����� �����մϴ�", "(���ط��� �������� ������ ����մϴ�)", "",
								ChatColor.BOLD + "1 X + "
										+ BigDecimal.valueOf(1.1 *(1+ esd.Electrostatic.getOrDefault(p.getUniqueId(), 0) * 0.1))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						21, Engskillsinv);
				itemset("���丮", Material.COMMAND_BLOCK_MINECART, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[���� �迭]", "���Է½� ���丮�� ��ġ�մϴ�", "(���ط��� �߻�� ������ ����մϴ�)", "",
								ChatColor.BOLD + "10 X  "
										+ BigDecimal.valueOf(0.34 * (1+ esd.Dispenser.getOrDefault(p.getUniqueId(), 0) * 0.04))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						23, Engskillsinv);
				itemset("���ڷ�", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�", "������ ��Ÿ���� �����մϴ�"), 24,
						Engskillsinv);
				itemset("��Ȧ", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("�޸��� + �����۴�����", "", ChatColor.BOLD + " X 16.4D"), 26, Engskillsinv);
			}

			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("" + Proficiency.getproexp(p)), 27,
					Engskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1,
					Arrays.asList(ChatColor.AQUA + "SP." + esd.SkillPoints.getOrDefault(p.getUniqueId(), 0), "",
							"Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"),
					35, Engskillsinv);

		} else {
			itemset("Graviton", Material.WOODEN_PICKAXE, 0, 1, Arrays.asList(
					ChatColor.AQUA + "LV." + esd.Graviton.getOrDefault(p.getUniqueId(), 0), "",
					ChatColor.UNDERLINE + "[Lightning]", "SwapHand", "",
					ChatColor.BOLD + " 10 X "
							+ BigDecimal.valueOf(0.12 *(1 + esd.Graviton.getOrDefault(p.getUniqueId(), 0) * 0.0085))
									.setScale(2, RoundingMode.HALF_EVEN)+"D"
							+ " , "
							+ BigDecimal.valueOf(0.52 * (1 + esd.Graviton.getOrDefault(p.getUniqueId(), 0) * 0.05))
									.setScale(2, RoundingMode.HALF_EVEN)+"D",
					"Master LV.50"), 0, Engskillsinv);
			itemset("X_ray", Material.STONE_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.X_ray.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[Lightning]", "Sneaking + Rightclick",
							"Decreases Mob's Attack Damage", "",
							ChatColor.BOLD + "1 X "
									+ BigDecimal.valueOf(0.32 * (1 +esd.X_ray.getOrDefault(p.getUniqueId(), 0) * 0.024)).setScale(2,
											RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					1, Engskillsinv);
			itemset("Magnetic", Material.STONE_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.Magnetic.getOrDefault(p.getUniqueId(), 0), "",
							"Hit + Sneaking", "Master LV.1"),
					2, Engskillsinv);
			itemset("Electrostatic", Material.IRON_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.Electrostatic.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[Lightning]", "Rightclick", "",
							ChatColor.BOLD + "8 X "
									+ BigDecimal.valueOf(0.1 * (1+esd.Electrostatic.getOrDefault(p.getUniqueId(), 0) * 0.0078))
											.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					3, Engskillsinv);
			itemset("Jetpack", Material.GOLDEN_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.Jetpack.getOrDefault(p.getUniqueId(), 0), "",
							"LeftClick + Jump", "Immuned to fall damage", "Damage near by mobs when hit ground",
							"(Damage increases by FallDistance)", "", ChatColor.BOLD + "0.3D X FallDistance",
							"Master LV.1"),
					4, Engskillsinv);
			itemset("Dispenser", Material.DIAMOND_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.Dispenser.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[Lightning]", "SwapHand + Sneaking", "",
							ChatColor.BOLD + " 20 X "
									+ BigDecimal.valueOf(0.06 * (1+esd.Dispenser.getOrDefault(p.getUniqueId(), 0) * 0.006))
											.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					5, Engskillsinv);
			itemset("CombatSuit", Material.BOOK, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.CombatSuit.getOrDefault(p.getUniqueId(), 0), "",
							"Increases damage", "Reduce Damage over 20% of MaxHealth", "",
							ChatColor.BOLD + " X " + BigDecimal
									.valueOf(1.2 * (1 + esd.CombatSuit.getOrDefault(p.getUniqueId(), 0) * 0.03935))
									.setScale(2, RoundingMode.HALF_EVEN)),
					6, Engskillsinv);
			if (Proficiency.getpro(p) < 1) {
				itemset("EnergyBall(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 9, Engskillsinv);
				itemset("EMP(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 10,
						Engskillsinv);
				itemset("Strong Magnetic(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 11,
						Engskillsinv);
				itemset("ThunderCaller(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 12,
						Engskillsinv);
				itemset("Propellant(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 13,
						Engskillsinv);
				itemset("Observer(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 14,
						Engskillsinv);
				itemset("Overclock(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 15,
						Engskillsinv);
				itemset("Gamma Ray(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 17,
						Engskillsinv);
			} else if (Proficiency.getpro(p) < 2 && Proficiency.getpro(p) >= 1) {
				itemset("EnergyBall", Material.AZURE_BLUET, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Lightning]", "Throw EnergyBall When Use Once More",
								"(Damage Affected By Graviton)", "",
								ChatColor.BOLD + "20 X "
										+ BigDecimal.valueOf(0.25*(1+esd.Graviton.getOrDefault(p.getUniqueId(), 0) * 0.025))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						9, Engskillsinv);
				itemset("EMP", Material.SCULK_SENSOR, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Lightning]", "Shot EMP When Use Once More",
								"(Damage Affected By X_ray)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(0.7 * (1+esd.X_ray.getOrDefault(p.getUniqueId(), 0) * 0.057))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						10, Engskillsinv);
				itemset("Strong Magnetic", Material.LODESTONE, 0, 1, Arrays.asList("Pull Multiple Enemies"), 11,
						Engskillsinv);
				itemset("Thunder Caller", Material.LIGHTNING_ROD, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Lightning]", "Place Thunder Caller When Use Once More",
								"(Damage Affected By Electrostatic)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(2.1*(1+esd.Electrostatic.getOrDefault(p.getUniqueId(), 0) * 0.2))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						12, Engskillsinv);
				itemset("Propellant", Material.BARREL, 0, 1, Arrays.asList("Move Forward Fastly When Use Once More"),
						13, Engskillsinv);
				itemset("Observer", Material.OBSERVER, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Lightning]", "Place Observer When Use Once More",
								"(Damage Affected By Dispenser)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.1*(1+esd.Dispenser.getOrDefault(p.getUniqueId(), 0) * 0.006))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						14, Engskillsinv);
				itemset("Overclock", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage",
						"Decreases All Skills Cooldown 0.5s", "When Use any Skill (Except Ults)"), 15, Engskillsinv);
				itemset("Gamma Ray", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("Sneaking + ThrowItem", "", ChatColor.BOLD + " X 9.2D"), 17, Engskillsinv);

				itemset("Orbital(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 18,
						Engskillsinv);
				itemset("GravityShift(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 19,
						Engskillsinv);
				itemset("Anti-Gravity(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 21,
						Engskillsinv);
				itemset("Factory(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 23,
						Engskillsinv);
				itemset("NuclearReactor(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 24,
						Engskillsinv);
				itemset("BlackHole(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 26,
						Engskillsinv);
			} else {
				itemset("EnergyBall", Material.AZURE_BLUET, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Lightning]", "Throw EnergyBall When Use Once More",
								"(Damage Affected By Graviton)", "",
								ChatColor.BOLD + "20 X "
										+ BigDecimal.valueOf(0.25*(1+esd.Graviton.getOrDefault(p.getUniqueId(), 0) * 0.025))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						9, Engskillsinv);
				itemset("EMP", Material.SCULK_SENSOR, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Lightning]", "Shot EMP When Use Once More",
								"(Damage Affected By X_ray)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(0.7 * (1+esd.X_ray.getOrDefault(p.getUniqueId(), 0) * 0.057))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						10, Engskillsinv);
				itemset("Strong Magnetic", Material.LODESTONE, 0, 1, Arrays.asList("Pull Multiple Enemies"), 11,
						Engskillsinv);
				itemset("Thunder Caller", Material.LIGHTNING_ROD, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Lightning]", "Place Thunder Caller When Use Once More",
								"(Damage Affected By Electrostatic)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(2.1*(1+esd.Electrostatic.getOrDefault(p.getUniqueId(), 0) * 0.2))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						12, Engskillsinv);
				itemset("Propellant", Material.BARREL, 0, 1, Arrays.asList("Move Forward Fastly When Use Once More"),
						13, Engskillsinv);
				itemset("Observer", Material.OBSERVER, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Lightning]", "Place Observer When Use Once More",
								"(Damage Affected By Dispenser)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.1*(1+esd.Dispenser.getOrDefault(p.getUniqueId(), 0) * 0.006))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						14, Engskillsinv);
				itemset("Overclock", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage",
						"Decreases All Skills Cooldown 0.5s", "When Use any Skill (Except Ults)"), 15, Engskillsinv);
				itemset("Gamma Ray", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("Sneaking + ThrowItem", "", ChatColor.BOLD + " X 9.2D"), 17, Engskillsinv);

				itemset("Orbital", Material.BUCKET, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Lightning]", "Make Orbital When Use Once More",
								"(Damage Affected By Graviton)", "",
								ChatColor.BOLD + "13 X "
										+ BigDecimal.valueOf(0.2*(1+esd.Graviton.getOrDefault(p.getUniqueId(), 0) * 0.045))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						18, Engskillsinv);
				itemset("GravityShift", Material.QUARTZ, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Lightning]", "Use GravityShift When Use Once More",
								"(Damage Affected By X_ray)", "",
								ChatColor.BOLD + "1 X "+ BigDecimal.valueOf(0.76*(1+esd.X_ray.getOrDefault(p.getUniqueId(), 0) * 0.045))
								.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						19, Engskillsinv);
				itemset("Anti-Gravity", Material.PISTON, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Lightning]", "Make Anti-Gravity When Use Once More",
								"(Damage Affected By Electrostatic)", "",
								ChatColor.BOLD + "1 X + "
										+ BigDecimal.valueOf(1.1 *(1+ esd.Electrostatic.getOrDefault(p.getUniqueId(), 0) * 0.1))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						21, Engskillsinv);
				itemset("Factory", Material.COMMAND_BLOCK_MINECART, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[Lightning]", "Place Factory When Use Once More",
								"(Damage Affected By Dispenser)", "",
								ChatColor.BOLD + "10 X  "
										+ BigDecimal.valueOf(0.34 * (1+ esd.Dispenser.getOrDefault(p.getUniqueId(), 0) * 0.04))
												.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						23, Engskillsinv);
				itemset("NuclearReactor", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("Increases Damage & Armor", "Decreases Gamma Ray Cooldown"), 24, Engskillsinv);
				itemset("BlackHole", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("Sprinting + ThrowItem", "", ChatColor.BOLD + " X 16.4D"), 26, Engskillsinv);
			}

			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("" + Proficiency.getproexp(p)),
					27, Engskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1,
					Arrays.asList(ChatColor.AQUA + "SP." + esd.SkillPoints.getOrDefault(p.getUniqueId(), 0), "",
							"Click if you want to reset your skill's levels"),
					35, Engskillsinv);

		}
		p.openInventory(Engskillsinv);
	}

}
