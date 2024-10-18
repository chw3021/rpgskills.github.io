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
			itemset("중력자", Material.WOODEN_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.Graviton.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[번개 계열]", "손바꾸기", "",
							ChatColor.BOLD + " 10 X "
									+ BigDecimal.valueOf(0.12 *(1 + esd.Graviton.getOrDefault(p.getUniqueId(), 0) * 0.0085))
									.setScale(2, RoundingMode.HALF_EVEN)+"D"
									+ " , "
									+ BigDecimal.valueOf(0.52 * (1 + esd.Graviton.getOrDefault(p.getUniqueId(), 0) * 0.05))
									.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					0, Engskillsinv);
			itemset("엑스선", Material.STONE_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.X_ray.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[번개 계열]", "웅크리기 + 우클릭", "적의 공격력을 낮춥니다", "",
							ChatColor.BOLD + "1 X "
									+ BigDecimal.valueOf(0.32 * (1 +esd.X_ray.getOrDefault(p.getUniqueId(), 0) * 0.024)).setScale(2,
									RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					1, Engskillsinv);
			itemset("자성", Material.STONE_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.Magnetic.getOrDefault(p.getUniqueId(), 0), "",
							"근접공격 + 웅크리기", "Master LV.1"),
					2, Engskillsinv);
			itemset("정전기장", Material.IRON_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.Electrostatic.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[번개 계열]", "우클릭", "",
							ChatColor.BOLD + "8 X "
									+ BigDecimal.valueOf(0.1 * (1+esd.Electrostatic.getOrDefault(p.getUniqueId(), 0) * 0.0078))
									.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					3, Engskillsinv);
			itemset("제트팩", Material.GOLDEN_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.Jetpack.getOrDefault(p.getUniqueId(), 0), "", "우클릭 + 점프",
							"낙하 데미지에 면역이 되고", "착지시 주변적에게 피해를 줍니다", "(피해량은 높이에 비례합니다)", "",
							ChatColor.BOLD + "0.3D X 높이 ", "Master LV.1"),
					4, Engskillsinv);
			itemset("발사기", Material.DIAMOND_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.Dispenser.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE + "[번개 계열]", "손바꾸기 + 웅크리기", "",
							ChatColor.BOLD + " 20 X "
									+ BigDecimal.valueOf(0.06 * (1+esd.Dispenser.getOrDefault(p.getUniqueId(), 0) * 0.006))
									.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					5, Engskillsinv);
			itemset("전투슈트", Material.BOOK, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + esd.CombatSuit.getOrDefault(p.getUniqueId(), 0), "",
							"공격력이 증가합니다", "최대체력의 20%가 넘는 피해량을 감소시킵니다", "",
							ChatColor.BOLD + " X " + BigDecimal
									.valueOf(1.2 * (1 + esd.CombatSuit.getOrDefault(p.getUniqueId(), 0) * 0.03935))
									.setScale(2, RoundingMode.HALF_EVEN)),
					6, Engskillsinv);
			if (Proficiency.getpro(p) < 1) {
				itemset("에너지구체(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 9, Engskillsinv);
				itemset("EMP(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 10, Engskillsinv);
				itemset("강자기장(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 11, Engskillsinv);
				itemset("썬더콜러(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 12, Engskillsinv);
				itemset("추진(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 13, Engskillsinv);
				itemset("관측기(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 14, Engskillsinv);
				itemset("오버클럭(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 15, Engskillsinv);
				itemset("전투 순양함(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 17, Engskillsinv);
			} else if (Proficiency.getpro(p) < 2 && Proficiency.getpro(p) >= 1) {
				itemset("에너지구체", Material.AZURE_BLUET, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[번개 계열]", "재입력시 에너지구체를 발사합니다", "(피해량은 중력자 레벨에 비례합니다)", "",
								ChatColor.BOLD + "20 X "
										+ BigDecimal.valueOf(0.25*(1+esd.Graviton.getOrDefault(p.getUniqueId(), 0) * 0.025))
										.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						9, Engskillsinv);
				itemset("EMP", Material.SCULK_SENSOR, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[번개 계열]", "재입력시 EMP를 발사합니다", "(피해량은 엑스선 레벨에 비례합니다)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(0.7 * (1+esd.X_ray.getOrDefault(p.getUniqueId(), 0) * 0.057))
										.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						10, Engskillsinv);
				itemset("강자기장", Material.LODESTONE, 0, 1, Arrays.asList("여러적들을 끌어올수 있습니다"), 11, Engskillsinv);
				itemset("썬더콜러", Material.LIGHTNING_ROD, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[번개 계열]", "재입력시 썬더콜러를 설치합니다", "(피해량은 정전기장 레벨에 비례합니다)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(2.1*(1+esd.Electrostatic.getOrDefault(p.getUniqueId(), 0) * 0.2))
										.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						12, Engskillsinv);
				itemset("추진", Material.BARREL, 0, 1, Arrays.asList("재입력시 전방으로 빠르게 이동합니다"), 13, Engskillsinv);
				itemset("관측기", Material.OBSERVER, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[번개 계열]", "재입력시 관측기를 설치합니다", "(피해량은 발사기 레벨에 비례합니다)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.1*(1+esd.Dispenser.getOrDefault(p.getUniqueId(), 0) * 0.006))
										.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						14, Engskillsinv);
				itemset("오버클럭", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("공격력이 증가합니다", "스킬 사용시 궁극기를 제외한 모든 스킬들의", "재사용 대기시간이 0.5초 감소합니다"), 15,
						Engskillsinv);
				itemset("전투 순양함", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("웅크리기 + num4", "", "스킬들의 공격력과 성능이 증가합니다",ChatColor.BOLD + "+ 0.1D"), 17, Engskillsinv);

				itemset("원자궤도(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 18, Engskillsinv);
				itemset("중력가속(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 19, Engskillsinv);
				itemset("역중력장(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 21, Engskillsinv);
				itemset("팩토리(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 23, Engskillsinv);
				itemset("원자로(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 24, Engskillsinv);
				itemset("블랙홀(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 26, Engskillsinv);
			} else {
				itemset("에너지구체", Material.AZURE_BLUET, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[번개 계열]", "재입력시 에너지구체를 발사합니다", "(피해량은 중력자 레벨에 비례합니다)", "",
								ChatColor.BOLD + "20 X "
										+ BigDecimal.valueOf(0.25*(1+esd.Graviton.getOrDefault(p.getUniqueId(), 0) * 0.025))
										.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						9, Engskillsinv);
				itemset("EMP", Material.SCULK_SENSOR, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[번개 계열]", "재입력시 EMP를 발사합니다", "(피해량은 엑스선 레벨에 비례합니다)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(0.7 * (1+esd.X_ray.getOrDefault(p.getUniqueId(), 0) * 0.057))
										.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						10, Engskillsinv);
				itemset("강자기장", Material.LODESTONE, 0, 1, Arrays.asList("여러적들을 끌어올수 있습니다"), 11, Engskillsinv);
				itemset("썬더콜러", Material.LIGHTNING_ROD, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[번개 계열]", "재입력시 썬더콜러를 설치합니다", "(피해량은 정전기장 레벨에 비례합니다)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(2.1*(1+esd.Electrostatic.getOrDefault(p.getUniqueId(), 0) * 0.2))
										.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						12, Engskillsinv);
				itemset("추진", Material.BARREL, 0, 1, Arrays.asList("재입력시 전방으로 빠르게 이동합니다"), 13, Engskillsinv);
				itemset("관측기", Material.OBSERVER, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[번개 계열]", "재입력시 관측기를 설치합니다", "(피해량은 발사기 레벨에 비례합니다)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.1*(1+esd.Dispenser.getOrDefault(p.getUniqueId(), 0) * 0.006))
										.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						14, Engskillsinv);
				itemset("오버클럭", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("공격력이 증가합니다", "스킬 사용시 궁극기를 제외한 모든 스킬들의", "재사용 대기시간이 0.5초 감소합니다"), 15,
						Engskillsinv);
				itemset("전투 순양함", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("웅크리기 + num4", "", "스킬들의 공격력과 성능이 증가합니다",ChatColor.BOLD + "+ 0.1D"), 17, Engskillsinv);


				itemset("원자궤도", Material.BUCKET, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[번개 계열]", "재입력시 원자궤도를 생성합니다", "(피해량은 중력자 레벨에 비례합니다)", "",
								ChatColor.BOLD + "13 X "
										+ BigDecimal.valueOf(0.2*(1+esd.Graviton.getOrDefault(p.getUniqueId(), 0) * 0.045))
										.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						18, Engskillsinv);
				itemset("중력가속", Material.QUARTZ, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[번개 계열]", "재입력시 중력가속을 사용합니다", "(피해량은 엑스선 레벨에 비례합니다)", "",
								ChatColor.BOLD + "1 X "+ BigDecimal.valueOf(0.76*(1+esd.X_ray.getOrDefault(p.getUniqueId(), 0) * 0.045))
										.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						19, Engskillsinv);
				itemset("역중력장", Material.PISTON, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[번개 계열]", "재입력시 역중력장을 생성합니다", "(피해량은 정전기장 레벨에 비례합니다)", "",
								ChatColor.BOLD + "1 X + "
										+ BigDecimal.valueOf(1.1 *(1+ esd.Electrostatic.getOrDefault(p.getUniqueId(), 0) * 0.1))
										.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						21, Engskillsinv);
				itemset("팩토리", Material.COMMAND_BLOCK_MINECART, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE + "[번개 계열]", "재입력시 팩토리를 설치합니다", "(피해량은 발사기 레벨에 비례합니다)", "",
								ChatColor.BOLD + "10 X  "
										+ BigDecimal.valueOf(0.34 * (1+ esd.Dispenser.getOrDefault(p.getUniqueId(), 0) * 0.04))
										.setScale(2, RoundingMode.HALF_EVEN)+"D"),
						23, Engskillsinv);
				itemset("원자로", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다", "전투 순양함 쿨타임이 감소합니다"), 24,
						Engskillsinv);
				itemset("블랙홀", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("웅크리기 + num5", "", ChatColor.BOLD + " X 16.4D"), 26, Engskillsinv);
			}

			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("" + Proficiency.getproexp(p)), 27,
					Engskillsinv);
			itemset("스킬포인트", Material.NETHER_STAR, 0, 1,
					Arrays.asList(ChatColor.AQUA + "SP." + esd.SkillPoints.getOrDefault(p.getUniqueId(), 0), "",
							"클릭하면 스킬포인트가 초기화 됩니다"),
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
				itemset("Battle Cruiser(Locked)", Material.STRUCTURE_VOID, 0, 1,
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
				itemset("Battle Cruiser", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("Sneaking + num4", "","Increase Damage and Range", ChatColor.BOLD + " + 0.1D"), 17, Engskillsinv);

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
				itemset("Battle Cruiser", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("Sneaking + num4", "","Increase Damage and Range", ChatColor.BOLD + " + 0.1D"), 17, Engskillsinv);

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
						Arrays.asList("Increases Damage & Armor", "Decreases Battle Cruiser Cooldown"), 24, Engskillsinv);
				itemset("BlackHole", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("Sneaking + num5", "", ChatColor.BOLD + " X 16.4D"), 26, Engskillsinv);
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