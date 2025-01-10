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
		Inventory skillsInv = Bukkit.createInventory(null, 36, "Witskills");
		

		if (p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("위더해골", Material.WITHER_SKELETON_SKULL, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[어둠]", "손바꾸기", "부유중 사용시 강화된 해골을 발사합니다", "",
							ChatColor.BOLD + " X "
									+ BigDecimal.valueOf(0.34 * (1+wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0) * 0.034))
											.setScale(2, RoundingMode.HALF_EVEN)
									+ "D (강화시 두배)",
							"Master LV.50"),
					0, skillsInv);
			itemset("어둠의갈고리", Material.WOODEN_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.ReapingHook.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[어둠]", "웅크리기 + 우클릭", "Master LV.1"),
					1, skillsInv);
			itemset("구속의낫", Material.STONE_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.WitherScythe.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[어둠]", "근접공격 + 웅크리기", "",
							ChatColor.BOLD + "25 X "
									+ BigDecimal.valueOf(1.81 * (1+wsd.WitherScythe.getOrDefault(p.getUniqueId(), 0) * 0.017))
											.setScale(2, RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					2, skillsInv);
			itemset("저주", Material.IRON_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.Curse.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[어둠]", "우클릭", "",
							ChatColor.BOLD + "1 X "
									+ BigDecimal.valueOf(0.68 * (1+wsd.Curse.getOrDefault(p.getUniqueId(), 0) * 0.07)).setScale(2,
											RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					3, skillsInv);
			itemset("부유", Material.ELYTRA, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.Hover.getOrDefault(p.getUniqueId(), 0), "", "좌클릭 + 점프",
							"Master LV.1"),
					4, skillsInv);
			itemset("위더장미", Material.DIAMOND_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.Roses.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[어둠]", "손바꾸기 + 웅크리기", "",
							ChatColor.BOLD + "10 X "
									+ BigDecimal.valueOf(0.06 * (1+wsd.Roses.getOrDefault(p.getUniqueId(), 0) * 0.005)).setScale(2,
											RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					5, skillsInv);
			itemset("위더화", Material.BOOK, 0, 1, Arrays.asList(
					ChatColor.AQUA + "LV." + wsd.Witherize.getOrDefault(p.getUniqueId(), 0), "",
					"공격력이 증가합니다(위더형태에겐 두배)", "",
					ChatColor.BOLD + " X "
							+ BigDecimal.valueOf(1.25 * (1 + wsd.Witherize.getOrDefault(p.getUniqueId(), 0) * 0.0453))
									.setScale(2, RoundingMode.HALF_EVEN),
					"위더형태의 적에게 받는피해가 감소합니다", "위더효과에 면역이됩니다", "어둠 저항력이 증가합니다", "적에게 시듦 효과를 줍니다"), 7, skillsInv);
			if (Proficiency.getpro(p) < 1) {
				itemset("위더용올가미(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("흡수의갈고리(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("휩쓸기(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("밤의고리(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("부유술(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("파괴(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("복수심(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("극복(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			} else if (Proficiency.getpro(p) < 2 && Proficiency.getpro(p) >= 1) {
				itemset("위더용올가미", Material.DARK_OAK_TRAPDOOR, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[어둠]", "재사용시 위더용올가미를 투척합니다", "(피해량은 위더해골 레벨에 비레합니다)",
								"",
								ChatColor.BOLD + " X "
										+ BigDecimal.valueOf(0.3 * (1+wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0) * 0.03))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						9, skillsInv);
				itemset("흡수의갈고리", Material.IRON_HOE, 0, 1, Arrays.asList("주변적들도 끌어옵니다"), 10, skillsInv);
				itemset("휩쓸기", Material.NETHERITE_HOE, 0, 1, Arrays.asList("범위가 증가합니다"), 11, skillsInv);
				itemset("밤의고리", Material.BLACKSTONE, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[어둠]", "재사용시 밤의고리를 사용합니다", "(피해량은 저주 레벨에 비레합니다)", "",
								ChatColor.BOLD + "10 X "
										+ BigDecimal.valueOf(0.168 * (1+wsd.Curse.getOrDefault(p.getUniqueId(), 0) * 0.02))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, skillsInv);
				itemset("부유술", Material.ELYTRA, 0, 1, Arrays.asList("지속시간이 두배로 증가합니다"), 13, skillsInv);
				itemset("파괴", Material.CRACKED_POLISHED_BLACKSTONE_BRICKS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[어둠]", "재사용시 파괴를 사용합니다", "(피해량은 위더장미 레벨에 비레합니다)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.56 * (1 +wsd.Roses.getOrDefault(p.getUniqueId(), 0) * 0.048))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						14, skillsInv);
				itemset("복수심", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다"), 16, skillsInv);
				itemset("극복", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[어둠]",
						"웅크리기 + num4", "",
						ChatColor.BOLD  + "16D"), 17,
						skillsInv);

				itemset("정화광선(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
				itemset("위더방벽(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("수정감옥(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
				itemset("백색석영(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("정혈(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("정복(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			} else {
				itemset("위더용올가미", Material.DARK_OAK_TRAPDOOR, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[어둠]", "재사용시 위더용올가미를 투척합니다", "(피해량은 위더해골 레벨에 비레합니다)",
								"",
								ChatColor.BOLD + " X "
										+ BigDecimal.valueOf(0.3 * (1+wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0) * 0.03))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						9, skillsInv);
				itemset("흡수의갈고리", Material.IRON_HOE, 0, 1, Arrays.asList("주변적들도 끌어옵니다"), 10, skillsInv);
				itemset("휩쓸기", Material.NETHERITE_HOE, 0, 1, Arrays.asList("범위가 증가합니다"), 11, skillsInv);
				itemset("밤의고리", Material.BLACKSTONE, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[어둠]", "재사용시 밤의고리를 사용합니다", "(피해량은 저주 레벨에 비레합니다)", "",
								ChatColor.BOLD + "10 X "
										+ BigDecimal.valueOf(0.168 * (1+wsd.Curse.getOrDefault(p.getUniqueId(), 0) * 0.02))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, skillsInv);
				itemset("부유술", Material.ELYTRA, 0, 1, Arrays.asList("지속시간이 두배로 증가합니다"), 13, skillsInv);
				itemset("파괴", Material.CRACKED_POLISHED_BLACKSTONE_BRICKS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[어둠]", "재사용시 파괴를 사용합니다", "(피해량은 위더장미 레벨에 비레합니다)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.56 * (1 +wsd.Roses.getOrDefault(p.getUniqueId(), 0) * 0.048))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						14, skillsInv);
				itemset("복수심", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다"), 16, skillsInv);
				itemset("극복", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[어둠]",
						"웅크리기 + num4", "",
						ChatColor.BOLD  + "16D"), 17,
						skillsInv);

				itemset("위더방벽", Material.SHIELD, 0, 1, Arrays.asList("사용시 2초동안 무적상태가 됩니다"), 22, skillsInv);
				itemset("정화광선", Material.BEACON, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[어둠]", "재사용시 정화광선을 사용합니다", "(피해량은 위더장미 레벨에 비레합니다)", "",
								ChatColor.BOLD + "3 X "
										+ BigDecimal.valueOf(0.8 * (1+wsd.Roses.getOrDefault(p.getUniqueId(), 0) * 0.09))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						23, skillsInv);
				itemset("수정감옥", Material.IRON_BARS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[어둠]", "재사용시 수정감옥을 사용합니다", "(피해량은 수정감옥 레벨에 비레합니다)", "",
								ChatColor.BOLD + "8 X "
										+ BigDecimal.valueOf(0.4* (1+wsd.Curse.getOrDefault(p.getUniqueId(), 0) * 0.038))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						21, skillsInv);
				itemset("백색석영", Material.QUARTZ, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[어둠]", "재사용시 백색석영을 사용합니다", "(피해량은 위더해골 레벨에 비레합니다)", "",
								ChatColor.BOLD + "6 X "
										+ BigDecimal.valueOf(0.36 * (1+wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0) * 0.04))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						18, skillsInv);
				itemset("정혈", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("공격력이 증가합니다", "극복의 대기시간이 감소합니다", "일부 기술들의 시각효과가 바뀝니다", "위더형태의 적에게 완전면역이 됩니다"), 25,
						skillsInv);
				itemset("정복", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[어둠]",
						"웅크리기 + num5", "",
						ChatColor.BOLD + "26D"), 26,
						skillsInv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("" + Proficiency.getproexp(p)), 27,
					skillsInv);
			itemset("스킬포인트", SKILLPOINT, 0, 1,
					Arrays.asList(ChatColor.AQUA + "SP." + wsd.SkillPoints.getOrDefault(p.getUniqueId(), 0), "",
							"클릭하면 스킬포인트가 초기화 됩니다"),
					35, skillsInv);

		} else {
			itemset("WitherSkull", Material.WITHER_SKELETON_SKULL, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[Dark]", "SwapHand", "Shoot Charged Skull When Hovering", "",
							ChatColor.BOLD + " X "
									+ BigDecimal.valueOf(0.34 * (1+wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0) * 0.034))
											.setScale(2, RoundingMode.HALF_EVEN)
									+  "D (Double If Charged)",
							"Master LV.50"),
					0, skillsInv);
			itemset("ReapingHook", Material.WOODEN_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.ReapingHook.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[Dark]", "Sneaking + Rightclick", "Master LV.1"),
					1, skillsInv);
			itemset("WitherScythe", Material.STONE_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.WitherScythe.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[Dark]", "Hit + Sneaking", "",
							ChatColor.BOLD + "25 X "
									+ BigDecimal.valueOf(1.81 * (1+wsd.WitherScythe.getOrDefault(p.getUniqueId(), 0) * 0.017))
											.setScale(2, RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					2, skillsInv);
			itemset("Curse", Material.IRON_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.Curse.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[Dark]", "Rightclick", "",
							ChatColor.BOLD + "1 X "
									+ BigDecimal.valueOf(0.68 * (1+wsd.Curse.getOrDefault(p.getUniqueId(), 0) * 0.07)).setScale(2,
											RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					3, skillsInv);
			itemset("Hover", Material.GOLDEN_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.Hover.getOrDefault(p.getUniqueId(), 0), "",
							"LeftClick + Jump", "Master LV.1"),
					4, skillsInv);
			itemset("Roses", Material.DIAMOND_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + wsd.Roses.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[Dark]", "SwapHand + Sneaking", "",
							ChatColor.BOLD + "10 X "
									+ BigDecimal.valueOf(0.06 * (1+wsd.Roses.getOrDefault(p.getUniqueId(), 0) * 0.005)).setScale(2,
											RoundingMode.HALF_EVEN)
									+ "D",
							"Master LV.50"),
					5, skillsInv);
			itemset("Witherize", Material.BOOK, 0, 1, Arrays.asList(
					ChatColor.AQUA + "LV." + wsd.Witherize.getOrDefault(p.getUniqueId(), 0), "",
					"Increases damage (Double to WitherType Mobs)", "",
					ChatColor.BOLD + " X "
							+ BigDecimal.valueOf(1.25 * (1 + wsd.Witherize.getOrDefault(p.getUniqueId(), 0) * 0.0453))
									.setScale(2, RoundingMode.HALF_EVEN),
					"Reduce Damage from WitherType", "Immune to Wither Effect", "Increases Dark Resistance",
					"Applys Enemy Wither Effect"), 7, skillsInv);
			if (Proficiency.getpro(p) < 1) {
				itemset("WitherBola(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("AbsorbingHook(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 10,
						skillsInv);
				itemset("Sweeping(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 11,
						skillsInv);
				itemset("CircleOfNight(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 12,
						skillsInv);
				itemset("EnhancedHover(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 13,
						skillsInv);
				itemset("Demolition(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 14,
						skillsInv);
				itemset("Vengeance(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 16,
						skillsInv);
				itemset("Overcome(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 17,
						skillsInv);
			} else if (Proficiency.getpro(p) < 2 && Proficiency.getpro(p) >= 1) {
				itemset("WitherBola", Material.DARK_OAK_TRAPDOOR, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Dark]", "Throw WitherBola When Use Once More",
								"(Damage Affected By WitherSkull)", "",
								ChatColor.BOLD + " X "
										+ BigDecimal.valueOf(0.3 * (1+wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0) * 0.03))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						9, skillsInv);
				itemset("AbsorbingHook", Material.IRON_HOE, 0, 1, Arrays.asList("Pull Near By Enemies"), 10,
						skillsInv);
				itemset("Sweeping", Material.NETHERITE_HOE, 0, 1, Arrays.asList("Increases Range"), 11, skillsInv);
				itemset("CircleOfNight", Material.BLACKSTONE, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Dark]", "Use CircleOfNight When Use Once More",
								"(Damage Affected By Curse)", "",
								ChatColor.BOLD + "10 X "
										+ BigDecimal.valueOf(0.168 * (1+wsd.Curse.getOrDefault(p.getUniqueId(), 0) * 0.02))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, skillsInv);
				itemset("EnhancedHover", Material.ELYTRA, 0, 1, Arrays.asList("Doubles Duration"), 13, skillsInv);
				itemset("Demolition", Material.CRACKED_POLISHED_BLACKSTONE_BRICKS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Dark]", "Use Demolition When Use Once More",
								"(Damage Affected By Roses)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.56 * (1 +wsd.Roses.getOrDefault(p.getUniqueId(), 0) * 0.048))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						14, skillsInv);
				itemset("Vengeance", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16,
						skillsInv);
				itemset("Overcome", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Dark]",
						"Sneaking + num4", "",
						ChatColor.BOLD + " X " + BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_EVEN) + "16D"), 17,
						skillsInv);

				itemset("WitherBarrier(Locked)", Material.WITHER_SKELETON_SKULL, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 22,
						skillsInv);
				itemset("PurifierBeam(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 23,
						skillsInv);
				itemset("CrystalCage(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 21,
						skillsInv);
				itemset("WhiteQuarts(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 18,
						skillsInv);
				itemset("Depuration(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 25,
						skillsInv);
				itemset("Overthrow(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 26,
						skillsInv);
			} else {
				itemset("WitherBola", Material.DARK_OAK_TRAPDOOR, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Dark]", "Throw WitherBola When Use Once More",
								"(Damage Affected By WitherSkull)", "",
								ChatColor.BOLD + " X "
										+ BigDecimal.valueOf(0.3 * (1+wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0) * 0.03))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						9, skillsInv);
				itemset("AbsorbingHook", Material.IRON_HOE, 0, 1, Arrays.asList("Pull Near By Enemies"), 10,
						skillsInv);
				itemset("Sweeping", Material.NETHERITE_HOE, 0, 1, Arrays.asList("Increases Range"), 11, skillsInv);
				itemset("CircleOfNight", Material.BLACKSTONE, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Dark]", "Use CircleOfNight When Use Once More",
								"(Damage Affected By Curse)", "",
								ChatColor.BOLD + "10 X "
										+ BigDecimal.valueOf(0.168 * (1+wsd.Curse.getOrDefault(p.getUniqueId(), 0) * 0.02))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, skillsInv);
				itemset("EnhancedHover", Material.ELYTRA, 0, 1, Arrays.asList("Doubles Duration"), 13, skillsInv);
				itemset("Demolition", Material.CRACKED_POLISHED_BLACKSTONE_BRICKS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Dark]", "Use Demolition When Use Once More",
								"(Damage Affected By Roses)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.56 * (1 +wsd.Roses.getOrDefault(p.getUniqueId(), 0) * 0.048))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						14, skillsInv);
				itemset("Vengeance", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16,
						skillsInv);
				itemset("Overcome", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Dark]",
						"Sneaking + num4", "",
						ChatColor.BOLD + " X " + BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_EVEN) + "16D"), 17,
						skillsInv);

				itemset("WitherBarrier", Material.WITHER_SKELETON_SKULL, 0, 1,
						Arrays.asList("Set Invulnerable For 2s", "Since You Start Hovering"), 22, skillsInv);
				itemset("PurifierBeam", Material.BEACON, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Dark]", "Shot PurifierBeam When Use Once More",
								"(Damage Affected By Roses)", "",
								ChatColor.BOLD + "3 X "
										+ BigDecimal.valueOf(0.8 * (1+wsd.Roses.getOrDefault(p.getUniqueId(), 0) * 0.09))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						23, skillsInv);
				itemset("CrystalCage", Material.IRON_BARS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Dark]", "Use CrystalCage When Use Once More",
								"(Damage Affected By Curse)", "",
								ChatColor.BOLD + "8 X "
										+ BigDecimal.valueOf(0.4* (1+wsd.Curse.getOrDefault(p.getUniqueId(), 0) * 0.038))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						21, skillsInv);
				itemset("WhiteQuarts", Material.QUARTZ, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Dark]", "Use WhiteQuarts When Use Once More",
								"(Damage Affected By WitherSkull)", "",
								ChatColor.BOLD + "6 X "
										+ BigDecimal.valueOf(0.36 * (1+wsd.WitherSkull.getOrDefault(p.getUniqueId(), 0) * 0.04))
												.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						18, skillsInv);
				itemset("Depuration", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("Increases Damage & Armor", "Decreases Overcome Cooldown",
								"Change some of the Skills' Effects", "Totally Immune To WitherType Mobs"),
						25, skillsInv);
				itemset("Overthrow", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Dark]", "Sneaking + num5", "", 
								ChatColor.BOLD + "26D"), 26, skillsInv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("" + Proficiency.getproexp(p)),
					27, skillsInv);
			itemset("SkillPoints", SKILLPOINT, 0, 1,
					Arrays.asList(ChatColor.AQUA + "SP." + wsd.SkillPoints.getOrDefault(p.getUniqueId(), 0), "",
							"Click if you want to reset your skill's levels"),
					35, skillsInv);

		}

		Obtained.itemset(p, skillsInv);
		p.openInventory(skillsInv);
	}

}