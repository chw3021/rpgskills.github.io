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

			itemset("화살변경", Material.ARROW, 1, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.ArrowChange.getOrDefault(p.getUniqueId(), 0), "",
							"웅크리기 + 아이템바꾸기(마우스휠)", ChatColor.UNDERLINE + "[화염 계열]" + "불의화살: 벌레, 수상몹에게 더 높은 피해",
							"적을 태웁니다", "", ChatColor.UNDERLINE + "[물 계열]" + "물의화살: 약탈자, 엔더, 네더 몹들에게 더 높은 피해",
							"둔화효과를 적용합니다", "", ChatColor.UNDERLINE + "[바람 계열]" + "엔더화살: 공중부양 효과를 적용합니다",
							"엔더화살로 블럭을 맞출경우", "해당위치로 순간이동 합니다(대기시간 4초)", "",
							ChatColor.UNDERLINE + "[번개 계열]" + "번개의화살: 언데드에게 더 높은 피해", "발광효과를 적용합니다", "Master LV.1"),
					0, Launskillsinv);
			itemset("화살세례", Material.TIPPED_ARROW, 2, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.ArrowRain.getOrDefault(p.getUniqueId(), 0), "",
							"웅크리기 + 손바꾸기", "",
							ChatColor.BOLD + "10 X "
									+ BigDecimal.valueOf(0.08*(1+lsd.ArrowRain.getOrDefault(p.getUniqueId(), 0) * 0.008))
									.setScale(2, RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					1, Launskillsinv);
			itemset("방출", Material.FIREWORK_ROCKET, 3, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.Discharge.getOrDefault(p.getUniqueId(), 0), "", "좌클릭",
							"",
							ChatColor.BOLD + "3 X "
									+ BigDecimal.valueOf(0.7 * (1 +lsd.Discharge.getOrDefault(p.getUniqueId(), 0) * 0.076))
									.setScale(2, RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					2, Launskillsinv);
			itemset("거대화살", Material.TIPPED_ARROW, 4, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.GiantArrow.getOrDefault(p.getUniqueId(), 0), "",
							"손바꾸기", "",
							ChatColor.BOLD +""+ BigDecimal.valueOf(1.3*(1+lsd.GiantArrow.getOrDefault(p.getUniqueId(), 0) * 0.156))
									.setScale(2, RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					3, Launskillsinv);
			itemset("폭발", Material.GUNPOWDER, 5, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.Explosion.getOrDefault(p.getUniqueId(), 0), "",
							"웅크리기 + 발사", "",
							ChatColor.BOLD + " X "
									+ BigDecimal.valueOf(0.7*(1+lsd.Explosion.getOrDefault(p.getUniqueId(), 0) * 0.076))
									.setScale(2, RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					4, Launskillsinv);
			itemset("응집", Material.FIREWORK_STAR, 6, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.ChargingShot.getOrDefault(p.getUniqueId(), 0), "",
							"웅크리기 + 좌클릭", "",
							ChatColor.BOLD + " X "
									+ BigDecimal.valueOf(1.8 * (1+lsd.ChargingShot.getOrDefault(p.getUniqueId(), 0) * 0.18))
									.setScale(2, RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					5, Launskillsinv);
			itemset("마력강화", Material.BOOK, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + lsd.MagicArrow.getOrDefault(p.getUniqueId(), 0), "",
							"공격력이 증가합니다", "",
							ChatColor.BOLD + "X"
									+ BigDecimal.valueOf(1 + lsd.MagicArrow.getOrDefault(p.getUniqueId(), 0) * 0.0453)
									.setScale(2, RoundingMode.HALF_EVEN)),
					7, Launskillsinv);

			if (Proficiency.getpro(p) < 1) {
				itemset("융합(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 9, Launskillsinv);
				itemset("화살분수(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 10, Launskillsinv);
				itemset("로켓적중(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 11, Launskillsinv);
				itemset("운석(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 12, Launskillsinv);
				itemset("소용돌이(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 13, Launskillsinv);
				itemset("마력충전(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 14, Launskillsinv);
				itemset("원소순환(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 16, Launskillsinv);
				itemset("흡수의화살(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 17, Launskillsinv);
			} else if (Proficiency.getpro(p) < 2 && Proficiency.getpro(p) >= 1) {
				itemset("융합", Material.LAVA_BUCKET, 0, 1,
						Arrays.asList("화살종류를 달의화살과 태양의화살로 변경합니다", "달: 엔더+물, 태양: 불+번개"), 9, Launskillsinv);
				itemset("화살분수", Material.SPORE_BLOSSOM, 0, 1,
						Arrays.asList("재입력시 화살분수를 사용합니다", "(피해량은 화살세례 레벨에 비례합니다)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.28 * (1+ lsd.ArrowRain.getOrDefault(p.getUniqueId(), 0) * 0.034))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						10, Launskillsinv);
				itemset("로켓적중", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList("재입력시 화살로 로켓들을 맞춰 일찍 폭발시킵니다"), 11,
						Launskillsinv);
				itemset("운석", Material.CHISELED_QUARTZ_BLOCK, 0, 1,
						Arrays.asList("재입력시 운석을 사용합니다", "(피해량은 거대화살 레벨에 비례합니다)", "",
								ChatColor.BOLD + "6 X "
										+ BigDecimal.valueOf(0.34 * (1+ lsd.GiantArrow.getOrDefault(p.getUniqueId(), 0) * 0.04))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, Launskillsinv);
				itemset("소용돌이", Material.BUBBLE_CORAL_BLOCK, 0, 1,
						Arrays.asList("화살적중시 소용돌이를 일으킵니다", "(피해량은 폭발 레벨에 비례합니다)", "",
								ChatColor.BOLD + " X "
										+ BigDecimal.valueOf(0.87 * (1+lsd.Explosion.getOrDefault(p.getUniqueId(), 0) * 0.09))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						13, Launskillsinv);
				itemset("마력충전", Material.BOW, 0, 1, Arrays.asList("화살이 관통속성을 갖습니다(최대5)"), 14, Launskillsinv);
				itemset("원소순환", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 상승합니다", "화살변경시 이동속도와 점프효과를 얻습니다"),
						16, Launskillsinv);
				itemset("흡수의화살", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("웅크리기 + num4", "", ChatColor.BOLD + "범위내에 기술을 사용시 힙을 흡수합니다", ChatColor.BOLD + "0.28D X 흡수한 기술들의 타격수"), 17, Launskillsinv);

				itemset("은하(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 19, Launskillsinv);
				itemset("혜성(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 20, Launskillsinv);
				itemset("성운(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 21, Launskillsinv);
				itemset("분산(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 22, Launskillsinv);
				itemset("항성진화(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 25, Launskillsinv);
				itemset("행성화살(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 26, Launskillsinv);
			} else {
				itemset("융합", Material.LAVA_BUCKET, 0, 1,
						Arrays.asList("화살종류를 달의화살과 태양의화살로 변경합니다", "달: 엔더+물, 태양: 불+번개"), 9, Launskillsinv);
				itemset("화살분수", Material.SPORE_BLOSSOM, 0, 1,
						Arrays.asList("재입력시 화살분수를 사용합니다", "(피해량은 화살세례 레벨에 비례합니다)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.28 * (1 + lsd.ArrowRain.getOrDefault(p.getUniqueId(), 0) * 0.034))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						10, Launskillsinv);
				itemset("로켓적중", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList("재입력시 화살로 로켓들을 맞춰 일찍 폭발시킵니다"), 11,
						Launskillsinv);
				itemset("운석", Material.CHISELED_QUARTZ_BLOCK, 0, 1,
						Arrays.asList("재입력시 운석을 사용합니다", "(피해량은 거대화살 레벨에 비례합니다)", "",
								ChatColor.BOLD + "6 X "
										+ BigDecimal.valueOf(0.34 * (1+lsd.GiantArrow.getOrDefault(p.getUniqueId(), 0) * 0.04))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, Launskillsinv);
				itemset("소용돌이", Material.BUBBLE_CORAL_BLOCK, 0, 1,
						Arrays.asList("화살적중시 소용돌이를 일으킵니다", "(피해량은 폭발 레벨에 비례합니다)", "",
								ChatColor.BOLD + " X "
										+ BigDecimal.valueOf(0.87 * (1+lsd.Explosion.getOrDefault(p.getUniqueId(), 0) * 0.09))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						13, Launskillsinv);
				itemset("마력충전", Material.BOW, 0, 1, Arrays.asList("화살이 관통속성을 갖습니다(최대5)"), 14, Launskillsinv);
				itemset("원소순환", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 상승합니다", "화살변경시 이동속도와 점프효과를 얻습니다"),
						16, Launskillsinv);
				itemset("흡수의화살", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("웅크리기 + num4", "", ChatColor.BOLD + "범위내에 기술을 사용시 힙을 흡수합니다", ChatColor.BOLD + "0.28D X 흡수한 기술들의 타격수"), 17, Launskillsinv);

				itemset("은하", Material.END_CRYSTAL, 0, 1,
						Arrays.asList("재입력시 은하를 사용합니다", "(피해량은 화살세례 레벨에 비례합니다)", "",
								ChatColor.BOLD + "3 X "
										+ BigDecimal.valueOf(0.5 * (1+lsd.ArrowRain.getOrDefault(p.getUniqueId(), 0) * 0.05))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						19, Launskillsinv);
				itemset("혜성", Material.END_ROD, 0, 1,
						Arrays.asList("로켓폭발시 혜성을 소환합니다", "(피해량은 방출 레벨에 비례합니다)", "",
								ChatColor.BOLD + "3 X "
										+ BigDecimal.valueOf(0.36 * (1 +lsd.Discharge.getOrDefault(p.getUniqueId(), 0) * 0.046))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						20, Launskillsinv);
				itemset("성운", Material.SPLASH_POTION, 0, 1,
						Arrays.asList("재입력시 성운을 사용합니다", "(피해량은 거대화살 레벨에 비례합니다)", "",
								ChatColor.BOLD + " X "
										+ BigDecimal.valueOf(1.5 * (1+ lsd.GiantArrow.getOrDefault(p.getUniqueId(), 0) * 0.22))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						21, Launskillsinv);
				itemset("분산", Material.BOW, 0, 1, Arrays.asList("여러개의 화살을 발사합니다"), 22, Launskillsinv);
				itemset("항성진화", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("공격력과 방어력이 증가합니다", "흡수의 화살 재사용대기시간이 감소합니다", "이속,점프효과가 증폭되고", "야간투시능력을 추가로 얻습니다"),
						25, Launskillsinv);
				itemset("행성화살", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList("달리기 + num5", "", ChatColor.BOLD + "거리 X 1.0D, 13.0D"), 26, Launskillsinv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("" + Proficiency.getproexp(p)), 27,
					Launskillsinv);
			itemset("스킬포인트", Material.NETHER_STAR, 0, 1,
					Arrays.asList(ChatColor.AQUA + "SP." + lsd.SkillPoints.getOrDefault(p.getUniqueId(), 0), "",
							"클릭하면 스킬포인트가 초기화 됩니다"),
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
							"SwapHand", "",
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
						Arrays.asList("Sneaking + num4", "", ChatColor.BOLD + "Absorbs skills which are within range", ChatColor.BOLD + "0.28D X Hits of skills"), 17, Launskillsinv);

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
						Arrays.asList("Sneaking + num4", "", ChatColor.BOLD + "Absorbs skills which are within range", ChatColor.BOLD + "0.28D X Hits of skills"), 17, Launskillsinv);

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
						Arrays.asList("Sprinting + num5", "", ChatColor.BOLD + "Distance X 1.0D, 13.0D"), 26,
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