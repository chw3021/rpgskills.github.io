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
		Inventory skillsInv = Bukkit.createInventory(null, 36, "FrostSkills");
		

		String path = new File("").getAbsolutePath();
		FrostSkillsData bsd = new FrostSkillsData(
				FrostSkillsData.loadData(path + "/plugins/RPGskills/FrostSkillsData.data"));

		if (p.getLocale().equalsIgnoreCase("ko_kr")) {

			itemset("얼음수정", Material.BLUE_ICE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.FrozenCrystal.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[서리]", "손바꾸기", "재사용시 일찍 깨뜨릴수 있습니다", "",
							ChatColor.BOLD + ""
									+ BigDecimal.valueOf(0.765 * (1+bsd.FrozenCrystal.getOrDefault(p.getUniqueId(), 0) * 0.055))
									.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					0, skillsInv);
			itemset("우박", Material.PACKED_ICE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.Hailstones.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[서리]", "웅크리기 + 손바꾸기", "",
							ChatColor.BOLD + "20 X "
									+ BigDecimal.valueOf(0.16 * (1+bsd.Hailstones.getOrDefault(p.getUniqueId(), 0) * 0.018))
									.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					1, skillsInv);
			itemset("고드름화살", Material.ICE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[서리]", "우클릭", "",
							ChatColor.BOLD + "3 X "
									+ BigDecimal.valueOf(0.32 * (1+bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0) * 0.031))
									.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					2, skillsInv);
			itemset("거대고드름", Material.PRISMARINE_SHARD, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[서리]", "웅크리기 + 우클릭", "",
							ChatColor.BOLD + "25 X "
									+ BigDecimal.valueOf(0.23 * (1 + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0) * 0.025))
									.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					3, skillsInv);
			itemset("균열", Material.DIAMOND_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.Crack.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[서리]", "좌클릭 + 웅크리기", "시야 내의 적들에게만 피해를 줍니다", "",
							ChatColor.BOLD + " X "
									+ BigDecimal.valueOf(2.5*(1+ bsd.Crack.getOrDefault(p.getUniqueId(), 0) * 0.26)).setScale(2,
									RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					4, skillsInv);
			itemset("눈바람", Material.SNOW_BLOCK, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.SnowBreeze.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[서리]", "점프 + 좌클릭", "", ChatColor.BOLD + "0.25D",
							"Master LV.1"),
					5, skillsInv);
			itemset("동상", Material.BOOK, 0, 1, Arrays.asList(
							ChatColor.AQUA + "LV." + bsd.Frostbite.getOrDefault(p.getUniqueId(), 0), "", "공격력이 증가합니다",
							"서리계열 저항력이 증가합니다", "3번 공격당한 적은 동상에 걸려", "2초동안 경직상태가 됩니다(대기시간 5초)", "동상피해와 둔화에 면역이 됩니다", "",
							ChatColor.BOLD + " X "
									+ BigDecimal.valueOf(1.15 * (1 + bsd.Frostbite.getOrDefault(p.getUniqueId(), 0) * 0.045))
									.setScale(2, RoundingMode.HALF_EVEN)),
					7, skillsInv);
			if (Proficiency.getpro(p) < 1) {
				itemset("추위(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("눈사태(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("눈덩이(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("극소용돌이(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("크레바스(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("빙폭(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("냉혈(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("서리폭풍(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			} else if (Proficiency.getpro(p) < 2 && Proficiency.getpro(p) >= 1) {
				itemset("한기", Material.POWDER_SNOW_BUCKET, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[서리]", "수정이 깨진후 주변적에게 추가 피해를 줍니다", "기절시간이 증가합니다", "",
								ChatColor.BOLD + "4 X "
										+ BigDecimal.valueOf(0.5*(1+bsd.FrozenCrystal.getOrDefault(p.getUniqueId(), 0) * 0.031))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						9, skillsInv);
				itemset("눈사태", Material.SNOW, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[서리]", "재입력시 눈사태를 사용합니다", "(피해량은 우박 레벨에 비례합니다)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(0.76 * (1+bsd.Hailstones.getOrDefault(p.getUniqueId(), 0) * 0.085))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						10, skillsInv);
				itemset("눈덩이", Material.SNOWBALL, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[서리]", "재입력시 눈덩이를 사용합니다", "(피해량은 고드름화살 레벨에 비례합니다)", "",
								ChatColor.BOLD + "4 X "
										+ BigDecimal.valueOf(0.54 * (1 + bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0) * 0.0651))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						11, skillsInv);
				itemset("극소용돌이", Material.WHITE_TULIP, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[서리]", "재입력시 극소용돌이를 사용합니다", "(피해량은 거대고드름 레벨에 비례합니다)",
								"",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.34 * (1 + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0) * 0.03))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, skillsInv);
				itemset("크레바스", Material.WHITE_CONCRETE_POWDER, 0, 1, Arrays.asList("1초동안 경직상태로 만듭니다"), 13, skillsInv);
				itemset("빙폭", Material.INFESTED_CHISELED_STONE_BRICKS, 0, 1,
						Arrays.asList("재입력시 빙폭을 사용합니다", "2초동안 무적상태가 됩니다"), 14, skillsInv);
				itemset("냉혈", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 동상의 경직지속시간이 증가합니다"), 16,
						skillsInv);
				itemset("서리폭풍", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[서리]",
						"웅크리기 + num4", "", ChatColor.BOLD + "60 X 0.32D"), 17, skillsInv);

				itemset("대균열(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("극한의추위(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
				itemset("서리칼날(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("빙적(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
				itemset("복빙(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("절대영도(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("빙하시대(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			} else {
				itemset("한기", Material.POWDER_SNOW_BUCKET, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[서리]", "수정이 깨진후 주변적에게 추가 피해를 줍니다", "기절시간이 증가합니다", "",
								ChatColor.BOLD + "4 X "
										+ BigDecimal.valueOf(0.5*(1+bsd.FrozenCrystal.getOrDefault(p.getUniqueId(), 0) * 0.031))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						9, skillsInv);
				itemset("눈사태", Material.SNOW, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[서리]", "재입력시 눈사태를 사용합니다", "(피해량은 우박 레벨에 비례합니다)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(0.76 * (1+bsd.Hailstones.getOrDefault(p.getUniqueId(), 0) * 0.085))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						10, skillsInv);
				itemset("눈덩이", Material.SNOWBALL, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[서리]", "재입력시 눈덩이를 사용합니다", "(피해량은 고드름화살 레벨에 비례합니다)", "",
								ChatColor.BOLD + "4 X "
										+ BigDecimal.valueOf(0.54 * (1 + bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0) * 0.0651))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						11, skillsInv);
				itemset("극소용돌이", Material.WHITE_TULIP, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[서리]", "재입력시 극소용돌이를 사용합니다", "(피해량은 거대고드름 레벨에 비례합니다)",
								"",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.34 * (1 + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0) * 0.03))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, skillsInv);
				itemset("크레바스", Material.WHITE_CONCRETE_POWDER, 0, 1, Arrays.asList("1초동안 경직상태로 만듭니다"), 13, skillsInv);
				itemset("빙폭", Material.INFESTED_CHISELED_STONE_BRICKS, 0, 1,
						Arrays.asList("재입력시 빙폭을 사용합니다", "2초동안 무적상태가 됩니다"), 14, skillsInv);
				itemset("냉혈", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 동상의 경직지속시간이 증가합니다"), 16,
						skillsInv);
				itemset("서리폭풍", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[서리]",
						"웅크리기 + num4", "", ChatColor.BOLD + "60 X 0.32D"), 17, skillsInv);

				itemset("대균열", Material.ICE, 0, 1, Arrays.asList("범위가 증가합니다"), 18, skillsInv);
				itemset("극한의추위", Material.WHITE_SHULKER_BOX, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[서리]", "재입력시 극한의추위를 사용합니다", "(피해량은 우박 레벨에 비례합니다)", "",
								ChatColor.BOLD + "6 X "
										+ BigDecimal.valueOf(0.5 * (1 +bsd.Hailstones.getOrDefault(p.getUniqueId(), 0) * 0.06))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						19, skillsInv);
				ItemStack isw = new ItemStack(Material.NETHERITE_SWORD);
				isw.getItemMeta().setCustomModelData(3006);
				itemset("서리칼날", isw, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[서리]", "재입력시 서리칼날을 사용합니다", "(피해량은 고드름화살 레벨에 비례합니다)", "",
								ChatColor.BOLD + "5 X  "
										+ BigDecimal.valueOf(0.42 * (1 + bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0) * 0.0551))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						20, skillsInv);
				itemset("빙적", Material.PRISMARINE_SHARD, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[서리]", "재입력시 빙적을 사용합니다", "(피해량은 거대고드름 레벨에 비례합니다)", "",
								ChatColor.BOLD + "20 X "
										+ BigDecimal.valueOf(0.3 * (1 + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0) * 0.045))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						21, skillsInv);
				itemset("복빙", Material.WHITE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList("동상의 대기시간을 초기화합니다"), 22,
						skillsInv);
				itemset("절대영도", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("공격력과 방어력이 증가합니다", "서리폭풍 재사용대기시간이 감소합니다", "동상의 경직지속시간이 증가합니다"), 25,
						skillsInv);
				itemset("빙하시대", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[서리]",
						"웅크리기+ num5", "타격당한 적은 15초동안","동상 대기시간이 없어집니다", "", ChatColor.BOLD + "28.5D"), 26, skillsInv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("" + Proficiency.getproexp(p)), 27,
					skillsInv);
			itemset("스킬포인트", SKILLPOINT, 0, 1,
					Arrays.asList(ChatColor.AQUA + "SP." + bsd.SkillPoints.getOrDefault(p.getUniqueId(), 0), "",
							"클릭하면 스킬포인트가 초기화 됩니다"),
					35, skillsInv);

		}

		else {

			itemset("FrozenCrystal", Material.BLUE_ICE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.FrozenCrystal.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[Frost]", "SwapHand", "You can break earlier",
							"by Using one more time", "",
							ChatColor.BOLD + ""
									+ BigDecimal.valueOf(0.765 * (1+bsd.FrozenCrystal.getOrDefault(p.getUniqueId(), 0) * 0.055))
									.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					0, skillsInv);
			itemset("Hailstones", Material.PACKED_ICE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.Hailstones.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[Frost]", "Sneaking + SwapHand", "",
							ChatColor.BOLD + "20 X "
									+ BigDecimal.valueOf(0.16 * (1+bsd.Hailstones.getOrDefault(p.getUniqueId(), 0) * 0.018))
									.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					1, skillsInv);
			itemset("IcicleShot", Material.ICE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[Frost]", "Rightclick", "",
							ChatColor.BOLD + "3 X "
									+ BigDecimal.valueOf(0.32 * (1+bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0) * 0.031))
									.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					2, skillsInv);
			itemset("IceSpikes", Material.PRISMARINE_SHARD, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[Frost]", "Sneaking + Rightclick", "",
							ChatColor.BOLD + "25 X "
									+ BigDecimal.valueOf(0.23 * (1 + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0) * 0.025))
									.setScale(2, RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					3, skillsInv);
			itemset("Crack", Material.DIAMOND_PICKAXE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.Crack.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[Frost]", "Sneaking + LeftClick", "Only to enemies within line of sight.", "",
							ChatColor.BOLD + " X "
									+ BigDecimal.valueOf(2.5*(1+ bsd.Crack.getOrDefault(p.getUniqueId(), 0) * 0.26)).setScale(2,
									RoundingMode.HALF_EVEN)+"D",
							"Master LV.50"),
					4, skillsInv);
			itemset("SnowBreeze", Material.SNOW_BLOCK, 0, 1,
					Arrays.asList(ChatColor.AQUA + "LV." + bsd.SnowBreeze.getOrDefault(p.getUniqueId(), 0), "",
							ChatColor.UNDERLINE+"✬[Frost]", "Jump + LeftClick", "", ChatColor.BOLD + " (0.25D)",
							"Master LV.1"),
					5, skillsInv);
			itemset("Frostbite", Material.BOOK, 0, 1, Arrays.asList(
							ChatColor.AQUA + "LV." + bsd.Frostbite.getOrDefault(p.getUniqueId(), 0), "", "Increases damage",
							"Get Frost Resistance", "Freeze Enemy hit three times for 2s (Cooldown 5s)",
							"Immune to Slow Effect, Freeze", "",
							ChatColor.BOLD + " X "
									+ BigDecimal.valueOf(1.15 * (1 + bsd.Frostbite.getOrDefault(p.getUniqueId(), 0) * 0.045))
									.setScale(2, RoundingMode.HALF_EVEN)),
					7, skillsInv);
			if (Proficiency.getpro(p) < 1) {
				itemset("Chill(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 9,
						skillsInv);
				itemset("Avalanche(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 10,
						skillsInv);
				itemset("SnowBall(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 11,
						skillsInv);
				itemset("PolarVortex(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 12,
						skillsInv);
				itemset("Crevasse(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 13,
						skillsInv);
				itemset("Icefall(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 14,
						skillsInv);
				itemset("ColdBlood(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 16,
						skillsInv);
				itemset("Blizzard(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 17,
						skillsInv);
			} else if (Proficiency.getpro(p) < 2 && Proficiency.getpro(p) >= 1) {
				itemset("Chill", Material.POWDER_SNOW_BUCKET, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Frost]", "Extra Damage After Break",
								"Increases Stun Duration", "",
								ChatColor.BOLD + "4 X "
										+ BigDecimal.valueOf(0.5*(1+bsd.FrozenCrystal.getOrDefault(p.getUniqueId(), 0) * 0.031))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						9, skillsInv);
				itemset("Avalanche", Material.SNOW, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Frost]", "Use Avalanche When Use Once More",
								"(Damage Affected By Hailstones)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(0.76 * (1+bsd.Hailstones.getOrDefault(p.getUniqueId(), 0) * 0.085))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						10, skillsInv);
				itemset("SnowBall", Material.SNOWBALL, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Frost]", "Shot SnowBall When Use Once More",
								"(Damage Affected By IcicleShot)", "",
								ChatColor.BOLD + "4 X "
										+ BigDecimal.valueOf(0.54 * (1 + bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0) * 0.0651))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						11, skillsInv);
				itemset("PolarVortex", Material.WHITE_TULIP, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Frost]", "Use PolarVortex When Use Once More",
								"(Damage Affected By IceSpikes)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.34 * (1 + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0) * 0.03))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, skillsInv);
				itemset("Crevasse", Material.WHITE_CONCRETE_POWDER, 0, 1, Arrays.asList("Freezes 1s"), 13,
						skillsInv);
				itemset("Icefall", Material.INFESTED_CHISELED_STONE_BRICKS, 0, 1,
						Arrays.asList("Use Icefall When Use Once More", "Get Invulnerable for 2s"), 14,
						skillsInv);
				itemset("ColdBlood", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("Increases Whole Skills Damage", "Increases Frostbite Duration"), 16,
						skillsInv);
				itemset("Blizzard", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Frost]",
						"Sneaking + num4", "", ChatColor.BOLD + "60 X 0.32D"), 17, skillsInv);

				itemset("HugeBreak(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 18,
						skillsInv);
				itemset("Extremecold(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 19,
						skillsInv);
				itemset("IceBlades(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 20,
						skillsInv);
				itemset("GlacialDrift(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 21,
						skillsInv);
				itemset("Regelation(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 22,
						skillsInv);
				itemset("AbsoluteZero(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 25,
						skillsInv);
				itemset("Ice Age(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 26,
						skillsInv);
			} else {
				itemset("Chill", Material.POWDER_SNOW_BUCKET, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Frost]", "Extra Damage After Break",
								"Increases Stun Duration", "",
								ChatColor.BOLD + "4 X "
										+ BigDecimal.valueOf(0.5*(1+bsd.FrozenCrystal.getOrDefault(p.getUniqueId(), 0) * 0.031))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						9, skillsInv);
				itemset("Avalanche", Material.SNOW, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Frost]", "Use Avalanche When Use Once More",
								"(Damage Affected By Hailstones)", "",
								ChatColor.BOLD + "1 X "
										+ BigDecimal.valueOf(0.76 * (1+bsd.Hailstones.getOrDefault(p.getUniqueId(), 0) * 0.085))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						10, skillsInv);
				itemset("SnowBall", Material.SNOWBALL, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Frost]", "Shot SnowBall When Use Once More",
								"(Damage Affected By IcicleShot)", "",
								ChatColor.BOLD + "4 X "
										+ BigDecimal.valueOf(0.54 * (1 + bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0) * 0.0651))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						11, skillsInv);
				itemset("PolarVortex", Material.WHITE_TULIP, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Frost]", "Use PolarVortex When Use Once More",
								"(Damage Affected By IceSpikes)", "",
								ChatColor.BOLD + "5 X "
										+ BigDecimal.valueOf(0.34 * (1 + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0) * 0.03))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						12, skillsInv);
				itemset("Crevasse", Material.WHITE_CONCRETE_POWDER, 0, 1, Arrays.asList("Freezes 1s"), 13,
						skillsInv);
				itemset("Icefall", Material.INFESTED_CHISELED_STONE_BRICKS, 0, 1,
						Arrays.asList("Use Icefall When Use Once More", "Get Invulnerable for 2s"), 14,
						skillsInv);
				itemset("ColdBlood", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("Increases Whole Skills Damage", "Increases Frostbite Duration"), 16,
						skillsInv);
				itemset("Blizzard", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Frost]",
						"Sneaking + num4", "", ChatColor.BOLD + "60 X 0.32D"), 17, skillsInv);

				itemset("HugeBreak", Material.ICE, 0, 1, Arrays.asList("Increases Range"), 18, skillsInv);
				itemset("Extremecold", Material.WHITE_SHULKER_BOX, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Frost]", "Use Extremecold When Use Once More",
								"(Damage Affected By Hailstones)", "",
								ChatColor.BOLD + "6 X "
										+ BigDecimal.valueOf(0.5 * (1 +bsd.Hailstones.getOrDefault(p.getUniqueId(), 0) * 0.06))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						19, skillsInv);
				ItemStack isw = new ItemStack(Material.NETHERITE_SWORD);
				isw.getItemMeta().setCustomModelData(3006);
				itemset("IceBlades", isw, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Frost]", "Swing IceBlades When Use Once More",
								"(Damage Affected By IcicleShot)", "",
								ChatColor.BOLD + "5 X  "
										+ BigDecimal.valueOf(0.42 * (1 + bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0) * 0.0551))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						20, skillsInv);
				itemset("GlacialDrift", Material.PRISMARINE_SHARD, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Frost]", "Use GlacialDrift When Use Once More",
								"(Damage Affected By IceSpikes)", "",
								ChatColor.BOLD + "20 X "
										+ BigDecimal.valueOf(0.3 * (1 + bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0) * 0.045))
										.setScale(2, RoundingMode.HALF_EVEN)
										+ "D"),
						21, skillsInv);
				itemset("Regelation", Material.WHITE_GLAZED_TERRACOTTA, 0, 1,
						Arrays.asList("Removes Frostbite Cooldown Of Hit Enemies"), 22, skillsInv);
				itemset("AbsoluteZero", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor",
						"Decrease Blizzard Cooldown", "Increases Frostbite Duration"), 25, skillsInv);
				itemset("Ice Age", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Frost]",
						"Sneaking + num5","Removes Frostbite Cd of hit enemies(15s)", "", ChatColor.BOLD + "10 X 1.0D, 10D"), 26, skillsInv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("" + Proficiency.getproexp(p)),
					27, skillsInv);
			itemset("SkillPoints", SKILLPOINT, 0, 1,
					Arrays.asList(ChatColor.AQUA + "SP." + bsd.SkillPoints.getOrDefault(p.getUniqueId(), 0), "",
							"Click if you want to reset your skill's levels"),
					35, skillsInv);

		}

		Obtained.itemset(p, skillsInv);
		p.openInventory(skillsInv);
	}

}