package io.github.chw3021.classes.berserker;

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

public class BerSkillsGui extends SkillsGui{


	public void Berskillsinv(Player p) {
		String path = new File("").getAbsolutePath();
		BerSkillsData bsd = new BerSkillsData(BerSkillsData.loadData(path + "/plugins/RPGskills/BerSkillsData.data"));
		Inventory skillsInv = Bukkit.createInventory(null, 36, "Berskills");

		

		if (p.getLocale().equalsIgnoreCase("ko_kr")) {

			basic("분사", Material.STONE_SWORD, 0, 1,Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 웅크리기 + 손바꾸기"),bsd.Spray.getOrDefault(p.getUniqueId(), 0),1,0.8 , 0.07, 50,0, skillsInv);
			basic("불사", Material.APPLE, 0, 1, Arrays.asList("체력이 5%미만으로 떨어질시", "불사가 발동됩니다", "패시브 스킬"), 1, skillsInv);
			basic("휩쓸기", Material.IRON_HOE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 좌클릭 + 웅크리기" ),bsd.Swipe.getOrDefault(p.getUniqueId(), 0),1,0.5,0.08,50, 2, skillsInv);
			basic("흡입", Material.GOLDEN_HOE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 점프 + 우클릭"),bsd.Inhale.getOrDefault(p.getUniqueId(), 0),8,0.2,0.02,50, 3, skillsInv);
			basic("난무", Material.DIAMOND_SWORD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 점프 + 좌클릭"),bsd.Flurry.getOrDefault(p.getUniqueId(), 0),
					10,0.18,0.02,50,4, skillsInv);
			basic("진홍빛전진", Material.DIAMOND_SWORD, 0, 1,
					Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 웅크리기 + 우클릭"),bsd.Crimpson.getOrDefault(p.getUniqueId(), 0),3,0.65,0.053,50,
					5, skillsInv);
			basic("변신", Material.WOODEN_HOE,0, 1, Arrays.asList("🖮🖰 손바꾸기", "", ChatColor.DARK_RED + "갈망: 받는피해가 20% 감소하고", ChatColor.DARK_RED + "적을 공격시 체력을 회복합니다",
					ChatColor.RED + "광란: 피해량이 20% 증가하는 대신",ChatColor.RED +  "기술 사용시 체력을 추가로 소모합니다"), bsd.Conversion.getOrDefault(p.getUniqueId(), 0),
					0,0d,0d,1, 6, skillsInv);
			passive("광기", Material.BOOK, 0, 1,
					Arrays.asList(
							"데미지가 증가합니다", "공격속도가 매우 빨라집니다", "밀치기저항이 최대치가 됩니다", "공격시 이동속도와 점프력이 증가합니다"
							),bsd.Lunacy.getOrDefault(p.getUniqueId(), 0),0.04435,7, skillsInv);
			
			if (Proficiency.getpro(p) < 1) {
				itemset("분출(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("흡수(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("포식(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("폭발(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("강타(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("갈증(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 15, skillsInv);
				itemset("무자비(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("들끓는 심장(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			} else if (Proficiency.getpro(p) < 2 && Proficiency.getpro(p) >= 1) {
				itemset("분출", Material.CRIMSON_FUNGUS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 재입력시 분출을 사용합니다", "(피해량은 분사 레벨에 비례합니다)"),bsd.Spray.getOrDefault(p.getUniqueId(), 0),
						5,0.31,0.03,9, skillsInv);
				itemset("할퀴기", Material.NETHER_WART, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 재입력시 할퀴기를 사용합니다", "(피해량은 휩쓸기 레벨에 비례합니다)")
						,bsd.Swipe.getOrDefault(p.getUniqueId(), 0),1,0.7,0.09, 11, skillsInv);
				itemset("폭발", Material.FERMENTED_SPIDER_EYE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 재입력시 폭발을 사용합니다", "(피해량은 흡입 레벨에 비례합니다)"
						),bsd.Inhale.getOrDefault(p.getUniqueId(), 0),1,1.2,0.13, 12, skillsInv);
				itemset("무자비", Material.RED_SAND, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 재입력시 무자비를 사용합니다", "(피해량은 난무 레벨에 비례합니다)"),bsd.Flurry.getOrDefault(p.getUniqueId(), 0)
						,8,0.44,0.047,
						13, skillsInv);
				itemset("강타", Material.CRIMSON_PRESSURE_PLATE, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 재입력시 강타를 사용합니다", "(피해량은 진홍빛전진 레벨에 비례합니다)"),bsd.Crimpson.getOrDefault(p.getUniqueId(), 0)
						,1,1.36,0.147,14, skillsInv);
				itemset("갈증", Material.CRIMSON_HYPHAE, 0, 1, Arrays.asList("갈망 상태중 포만감 효과도 얻습니다"), 15, skillsInv);
				itemset("난폭함", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("전체 공격력이 증가합니다", "공격시 체력을 회복합니다"), 16,
						skillsInv);
				itemset("들끓는 심장", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[화염]", "🖮🖰 웅크리기 + num4", "15초동안 공격력이 대폭 증가합니다",
								"공격시 화염피해를 추가로 입힙니다", "", ChatColor.BOLD + " X 1.3 X 0.005D"),
						17, skillsInv);

				itemset("광분(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("무자비(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("맹공(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
				itemset("넘치는힘(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("말살(잠김)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("요구 숙련도: " + Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			} else {
				itemset("분출", Material.CRIMSON_FUNGUS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 재입력시 분출을 사용합니다", "(피해량은 분사 레벨에 비례합니다)"),bsd.Spray.getOrDefault(p.getUniqueId(), 0),
						5,0.31,0.03,9, skillsInv);
				itemset("할퀴기", Material.NETHER_WART, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 재입력시 할퀴기를 사용합니다", "(피해량은 휩쓸기 레벨에 비례합니다)")
						,bsd.Swipe.getOrDefault(p.getUniqueId(), 0),1,0.7,0.09, 11, skillsInv);
				itemset("폭발", Material.FERMENTED_SPIDER_EYE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 재입력시 폭발을 사용합니다", "(피해량은 흡입 레벨에 비례합니다)"
						),bsd.Inhale.getOrDefault(p.getUniqueId(), 0),1,1.2,0.13, 12, skillsInv);
				itemset("무자비", Material.RED_SAND, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 재입력시 무자비를 사용합니다", "(피해량은 난무 레벨에 비례합니다)"),bsd.Flurry.getOrDefault(p.getUniqueId(), 0)
						,8,0.44,0.047,
						13, skillsInv);
				itemset("강타", Material.CRIMSON_PRESSURE_PLATE, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 재입력시 강타를 사용합니다", "(피해량은 진홍빛전진 레벨에 비례합니다)"),bsd.Crimpson.getOrDefault(p.getUniqueId(), 0)
						,1,1.36,0.147,14, skillsInv);
				itemset("갈증", Material.CRIMSON_HYPHAE, 0, 1, Arrays.asList("갈망 상태중 포만감 효과도 얻습니다"), 15, skillsInv);
				itemset("난폭함", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("전체 공격력이 증가합니다", "공격시 체력을 회복합니다"), 16,
						skillsInv);
				itemset("들끓는 심장", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[화염]", "🖮🖰 웅크리기 + num4", "15초동안 공격력이 대폭 증가합니다",
								"공격시 화염피해를 추가로 입힙니다", "", ChatColor.BOLD + " X 1.3 X 0.005D"),
						17, skillsInv);
				

				itemset("광분", Material.CRYING_OBSIDIAN, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 재입력시 광분을 사용합니다", "(피해량은 분사 레벨에 비례합니다)"),bsd.Spray.getOrDefault(p.getUniqueId(), 0),
						1,1.1,0.09,18, skillsInv);
				itemset("진홍참", Material.NETHERITE_SWORD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 재입력시 진홍참을 사용합니다", 
						"(피해량은 난무 레벨에 비례합니다)"),bsd.Flurry.getOrDefault(p.getUniqueId(), 0),1,1.533,0.0933, 22, skillsInv);
				itemset("맹공", Material.NETHERITE_SWORD, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 재입력시 맹공을 사용합니다", "(피해량은 진홍빛전진 레벨에 비례합니다)"),
								bsd.Crimpson.getOrDefault(p.getUniqueId(), 0),13, 0.26, 0.028, 23, skillsInv);
				itemset("넘치는힘", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("공격력과 방어력이 증가합니다", "들끓는심장과 불사의 대기시간이 감소합니다", "피격시 재생효과를 얻습니다(중첩가능)"), 25,
						skillsInv);
				itemset("말살", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[대지]", "🖮🖰 웅크리기 + num5",
								"", ChatColor.BOLD + "22 X 0.3D + 11.5D"),
						26, skillsInv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("" + Proficiency.getproexp(p)), 27,
					skillsInv);
			itemset("스킬포인트", SKILLPOINT, 0, 1,
					Arrays.asList(ChatColor.AQUA + "SP." + bsd.SkillPoints.getOrDefault(p.getUniqueId(), 0), "",
							"클릭하면 스킬포인트가 초기화 됩니다"),
					35, skillsInv);

		} else {

			itemset("Spray", Material.STONE_SWORD, 0, 1,
					Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]", "🖮🖰 Sneaking + SwapHand"),bsd.Spray.getOrDefault(p.getUniqueId(), 0),1,0.8 , 0.07, 50,
					0, skillsInv);
			itemset("Undying", Material.IRON_HOE, 0, 1,
					Arrays.asList(ChatColor.AQUA + "Activate Undying when Hp fall below 5%", "Passive Skill"),
					1, skillsInv);
			itemset("Swipe", Material.STONE_HOE, 0, 1,
					Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]", "🖮🖰 Sneaking + LeftClick"),bsd.Swipe.getOrDefault(p.getUniqueId(), 0),1,0.5,0.08,50,
					2, skillsInv);
			itemset("Inhale", Material.GOLDEN_HOE, 0, 1,
					Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]", "🖮🖰 Jump + RightClick"),bsd.Inhale.getOrDefault(p.getUniqueId(), 0),1,1.2,0.13,
					3, skillsInv);
			itemset("Flurry", Material.DIAMOND_SWORD, 0, 1,
					Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]", "🖮🖰 Jump + RightClick"),bsd.Flurry.getOrDefault(p.getUniqueId(), 0),10,0.18,0.02,50,
					4, skillsInv);
			itemset("CrimsonAdvance", Material.DIAMOND_SWORD, 0, 1,
					Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]", "🖮🖰 Sneaking + RightClick"),bsd.Crimpson.getOrDefault(p.getUniqueId(), 0),3,0.65,0.053,50,
					5, skillsInv);
			itemset("Conversion", Material.WOODEN_HOE, 0, 1, Arrays.asList("🖮🖰 SwapHand", "Cooldown 0.5sec", "",
					ChatColor.DARK_RED + "Eager: Reduces 20% Damage from Enemies", ChatColor.DARK_RED + "Recovers Hp when you attack",
					ChatColor.RED + "Frenzy: Increases 20% Damage",ChatColor.RED +  "but makes consumption of Hp"), bsd.Conversion.getOrDefault(p.getUniqueId(), 0),
					0,0d,0d,6, skillsInv);
			passive("Lunacy", Material.BOOK, 0, 1,
					Arrays.asList("Increases Damage", "Set Attack Speed VeryFast", "Set KnockbackResistance Max",
							"Get speed&jump when you hit mob", "Activate Undying when Hp fall below 5%")
					,bsd.Lunacy.getOrDefault(p.getUniqueId(), 0),0.04435,7, skillsInv);
			if (Proficiency.getpro(p) < 1) {
				itemset("BloodSquirt(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 9,
						skillsInv);
				itemset("Scratch(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 11,
						skillsInv);
				itemset("BurstOut(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 12,
						skillsInv);
				itemset("Merciless(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 13,
						skillsInv);
				itemset("Smite(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 14,
						skillsInv);
				itemset("Thirst(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 15, skillsInv);
				itemset("Rampageous(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 16,
						skillsInv);
				itemset("Bloodboil(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/29315"), 17,
						skillsInv);
			} else if (Proficiency.getpro(p) < 2 && Proficiency.getpro(p) >= 1) {
				itemset("BloodSquirt", Material.CRIMSON_FUNGUS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]", "🖮🖰 Blood will be Squirt When use Once more",
								"(Damage Affected By Spray)"),bsd.Spray.getOrDefault(p.getUniqueId(), 0),
						5,0.31,0.03,9, skillsInv);
				itemset("Scratch", Material.NETHER_WART, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]", "🖮🖰 Scratch Enemies When use Once more",
						"(Damage Affected By Swipe)"),bsd.Swipe.getOrDefault(p.getUniqueId(), 0),1,0.7,0.09, 11, skillsInv);
				itemset("BurstOut", Material.FERMENTED_SPIDER_EYE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]", "🖮🖰 Burst Out When use Once more",
						"(Damage Affected By Inhale)"),bsd.Inhale.getOrDefault(p.getUniqueId(), 0),1,1.2,0.13, 12,
						skillsInv);
				itemset("Merciless", Material.RED_SAND, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]", "🖮🖰 Use Merciless When use Once more",
								"(Damage Affected By Frenzy)"),bsd.Flurry.getOrDefault(p.getUniqueId(), 0),8,0.44,0.047,
						13, skillsInv);
				itemset("Smite", Material.CRIMSON_PRESSURE_PLATE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]",
						"Smite When use Once more", "(Damage Affected By CrimsonAdvance)"),bsd.Crimpson.getOrDefault(p.getUniqueId(), 0),1,1.36,0.147, 14, skillsInv);
				itemset("Thirst", Material.CRIMSON_HYPHAE, 0, 1, Arrays.asList("Get Saturation Effect While Eager"), 15,
						skillsInv);
				itemset("Rampageous", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("Increases Whole Skills Damage", "Life Steal From Hit Enemies"), 16,
						skillsInv);
				itemset("Bloodboil", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Flame]", "🖮🖰 Sneaking + num4",
								"Add Flame Power To Attack", "Increases Damage Greatly for 15s", "",
								ChatColor.BOLD + " X 1.3 X 0.005D"),
						17, skillsInv);

				itemset("Rave(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 18,
						skillsInv);
				itemset("CrimpsonSlash(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 22,
						skillsInv);
				itemset("Onslaught(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 23,
						skillsInv);
				itemset("Over Power(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 25,
						skillsInv);
				itemset("Genocide/Vampire(Locked)", Material.STRUCTURE_VOID, 0, 1,
						Arrays.asList("Required Proficiency: " + Proficiency.getproexp(p) + "/155015"), 26,
						skillsInv);
			} else {
				itemset("BloodSquirt", Material.CRIMSON_FUNGUS, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]", "🖮🖰 Blood will be Squirt When use Once more",
								"(Damage Affected By Spray)"),bsd.Spray.getOrDefault(p.getUniqueId(), 0),
						5,0.31,0.03,9, skillsInv);
				itemset("Scratch", Material.NETHER_WART, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]", "🖮🖰 Scratch Enemies When use Once more",
						"(Damage Affected By Swipe)"),bsd.Swipe.getOrDefault(p.getUniqueId(), 0),1,0.7,0.09, 11, skillsInv);
				itemset("BurstOut", Material.FERMENTED_SPIDER_EYE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]", "🖮🖰 Burst Out When use Once more",
						"(Damage Affected By Inhale)"),bsd.Inhale.getOrDefault(p.getUniqueId(), 0),1,1.2,0.13, 12,
						skillsInv);
				itemset("Merciless", Material.RED_SAND, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]", "🖮🖰 Use Merciless When use Once more",
								"(Damage Affected By Frenzy)"),bsd.Flurry.getOrDefault(p.getUniqueId(), 0),8,0.44,0.047,
						13, skillsInv);
				itemset("Smite", Material.CRIMSON_PRESSURE_PLATE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]",
						"Smite When use Once more", "(Damage Affected By CrimsonAdvance)"),bsd.Crimpson.getOrDefault(p.getUniqueId(), 0),1,1.36,0.147, 14, skillsInv);
				itemset("Thirst", Material.CRIMSON_HYPHAE, 0, 1, Arrays.asList("Get Saturation Effect While Eager"), 15,
						skillsInv);
				itemset("Rampageous", Material.ENCHANTED_BOOK, 0, 1,
						Arrays.asList("Increases Whole Skills Damage", "Life Steal From Hit Enemies"), 16,
						skillsInv);
				itemset("Bloodboil", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Flame]", "🖮🖰 Sneaking + num4",
								"Add Flame Power To Attack", "Increases Damage Greatly for 15s", "",
								ChatColor.BOLD + " X 1.3 X 0.005D"),
						17, skillsInv);
				

				itemset("Rave", Material.CRYING_OBSIDIAN, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]", "🖮🖰 Rave When use Once more",
								"(Damage Affected By Spray)"),bsd.Spray.getOrDefault(p.getUniqueId(), 0),
						1,1.1,0.09,
						18, skillsInv);
				itemset("CrimpsonSlash", Material.NETHERITE_SWORD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]", "🖮🖰 Use CrimpsonSlash When use Once more",
						"(Damage Affected By Flurry)"),bsd.Flurry.getOrDefault(p.getUniqueId(), 0),1,1.533,0.0933, 22,
						skillsInv);
				itemset("Onslaught", Material.NETHERITE_SWORD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]",
						"Use Onslaught When use Once more", "(Damage Affected By CrimsonAdvance)"),
						bsd.Crimpson.getOrDefault(p.getUniqueId(), 0),13, 0.26, 0.028,	23, skillsInv);
				itemset("Over Power", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor",
						"Decreases Bloodboil&Undying Cooldown", "Get Regeneration When You Attacked(Stackable)"), 25,
						skillsInv);
				itemset("Genocide", Material.WRITTEN_BOOK, 0, 1,
						Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]", "🖮🖰 Sneaking + num5", "",
								ChatColor.BOLD + "22 X 0.3D + 11.5D / " + p.getLevel() * 1.1),
						26, skillsInv);
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