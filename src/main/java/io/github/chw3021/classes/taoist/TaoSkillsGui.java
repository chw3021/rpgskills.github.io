package io.github.chw3021.classes.taoist;



import java.util.Arrays;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.classes.SkillsGui;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class TaoSkillsGui extends SkillsGui{
	
	public void TaoSkillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		TaoSkillsData tsd = new TaoSkillsData(TaoSkillsData.loadData(path +"/plugins/RPGskills/TaoSkillsData.data"));
		Inventory skillsInv = Bukkit.createInventory(null, 36, "TaoSkills");

		int Aura = tsd.Aura.getOrDefault(p.getUniqueId(), 0);
		int Imagery = tsd.Imagery.getOrDefault(p.getUniqueId(), 0);
		int Wave = tsd.Wave.getOrDefault(p.getUniqueId(), 0);
		int Amplify = tsd.Amplify.getOrDefault(p.getUniqueId(), 0);
		int Charm = tsd.Charm.getOrDefault(p.getUniqueId(), 0);
		int Flip = tsd.Flip.getOrDefault(p.getUniqueId(), 0);
		int CombustInside = tsd.CombustInside.getOrDefault(p.getUniqueId(), 0);
		int Vibe = tsd.Vibe.getOrDefault(p.getUniqueId(), 0);
		int SkillPoints = tsd.SkillPoints.getOrDefault(p.getUniqueId(), 0);
		


		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("잠해소비", Material.RED_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+CombustInside,"","🖮🖰 웅크리기 + 근접공격",
					"",ChatColor.BOLD+" X (1.8D + "+BigDecimal.valueOf(CombustInside*1.8).setScale(2, RoundingMode.HALF_EVEN)+")" ,"Master LV.50"), 0, skillsInv);
			itemset("연상", Material.BLACK_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+Imagery,"","🖮🖰 손바꾸기","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(Imagery*0.9).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 1, skillsInv);
			itemset("기공권", Material.YELLOW_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+Wave,"","🖮🖰 우클릭","",ChatColor.BOLD+" X (0.67D + "+BigDecimal.valueOf(Wave*0.64).setScale(2, RoundingMode.HALF_EVEN)+")" ,"Master LV.50"), 2, skillsInv);
			itemset("증폭", Material.BROWN_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+Amplify,"","🖮🖰 웅크리기 + 우클릭","기운의 범위를" + BigDecimal.valueOf(Amplify*0.1).setScale(2, RoundingMode.HALF_EVEN) + "초간 증가시킵니다",
					"양의기운: 주변적을 공격합니다",ChatColor.BOLD+" X (0.2D + "+BigDecimal.valueOf(Amplify*0.2).setScale(2, RoundingMode.HALF_EVEN)+")","",
					"음의기운: 파티원들을 즉시 회복합니다" ,"Master LV.50"), 3, skillsInv);
			itemset("부적", Material.BLACK_BANNER, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+Charm,"","🖮🖰 손바꾸기 + 웅크리기","",
					ChatColor.BOLD+"6 X (0.315D + "+BigDecimal.valueOf(Charm*0.3455).setScale(2, RoundingMode.HALF_EVEN)+"), "+
					ChatColor.BOLD+" X (1.37D + "+BigDecimal.valueOf(Charm*1.1).setScale(2, RoundingMode.HALF_EVEN)+"), ",
							"" ,"Master LV.50"), 4, skillsInv);
			itemset("공중제비", Material.BLUE_BANNER, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+Flip,"","🖮🖰 점프 + 좌클릭",
					"",ChatColor.BOLD+" X (0.3D + "+BigDecimal.valueOf(Flip*0.34).setScale(2, RoundingMode.HALF_EVEN)+")" ,"Master LV.50"), 5, skillsInv);
			itemset("기운", Material.CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+Aura,"","웅크리기 + 아이템바꾸기(마우스휠)"
					,ChatColor.UNDERLINE+"✬[화염, 대지]","🖮🖰 양의기운: 파티원들의 공격력(30%), 속도 증가","",ChatColor.UNDERLINE+"✬[서리, 어둠]","🖮🖰 음의기운: ","파티원들의 방어력 30%증가","적 공격시 파티원 체력 회복", "Master Lv.1"), 6, skillsInv);
			itemset("내공", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+Vibe,"","공격력이 증가합니다","",ChatColor.BOLD+" + "+BigDecimal.valueOf(Vibe*0.6).setScale(2, RoundingMode.HALF_EVEN)), 7, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("공명(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("기폭발(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("기공참(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("평온(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("속박진(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("순보(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("운기조식(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 15, skillsInv);
				itemset("통찰(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("명상(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("공명", Material.BELL, 0, 1, Arrays.asList("광역피해를 적용합니다"), 9, skillsInv);
				itemset("기폭발", Material.FIREWORK_STAR, 0, 1, Arrays.asList("🖮🖰 재입력시 기폭발을 사용합니다","",ChatColor.BOLD+" X (1.4D + "+BigDecimal.valueOf(Imagery*1.5).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 연상 레벨에 비례합니다)"), 10, skillsInv);
				itemset("기공참", Material.DIAMOND_SWORD, 0, 1, Arrays.asList("🖮🖰 재입력시 기공참을 사용합니다","",ChatColor.BOLD+"2 X (0.68D + "+BigDecimal.valueOf(Wave*0.76).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 기공권 레벨에 비례합니다)"), 11, skillsInv);
				itemset("평온", Material.GREEN_CANDLE, 0, 1, Arrays.asList("🖮🖰 재입력시 평온을 사용합니다",
						"양의기운: 주변적을 공격 및 제압합니다","",ChatColor.BOLD+" X (0.6D + "+BigDecimal.valueOf(Amplify*0.8).setScale(2, RoundingMode.HALF_EVEN)+")",
						"음의기운: 파티원들을 무적상태로 만듭니다","공통: 해로운 효과들을 제거합니다","(피해량과 지속시간은 증폭 레벨에 비례합니다)"), 12, skillsInv);
				itemset("속박진", Material.COARSE_DIRT, 0, 1, Arrays.asList("🖮🖰 재입력시 속박진을 사용합니다","",ChatColor.BOLD+"10 X (0.115D + "+BigDecimal.valueOf(Charm*0.2455).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 부적 레벨에 비례합니다)"), 13, skillsInv);
				itemset("순보", Material.NETHERITE_BOOTS, 0, 1, Arrays.asList("🖮🖰 재입력시 순보를 사용합니다","",ChatColor.BOLD+" X (0.9D + "+BigDecimal.valueOf(Flip*0.9).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 공중제비 레벨에 비례합니다)"), 14, skillsInv);
				itemset("운기조식", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("기운의 버프 효과를 두배로 증가시킵니다"), 15, skillsInv);
				itemset("통찰", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다"), 16, skillsInv);
				itemset("명상", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("🖮🖰 웅크리기 + num4","주변 파티원들을 치료합니다","",ChatColor.BOLD+"60 X 0.3D"), 17, skillsInv);

				itemset("승천(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
				itemset("기공장(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("만트라(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
				itemset("기공파(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("초월(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("음양팔괘진(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("공명", Material.BELL, 0, 1, Arrays.asList("광역피해를 적용합니다"), 9, skillsInv);
				itemset("기폭발", Material.FIREWORK_STAR, 0, 1, Arrays.asList("🖮🖰 재입력시 기폭발을 사용합니다","",ChatColor.BOLD+" X (1.4D + "+BigDecimal.valueOf(Imagery*1.5).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 연상 레벨에 비례합니다)"), 10, skillsInv);
				itemset("기공참", Material.DIAMOND_SWORD, 0, 1, Arrays.asList("🖮🖰 재입력시 기공참을 사용합니다","",ChatColor.BOLD+"2 X (0.68D + "+BigDecimal.valueOf(Wave*0.76).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 기공권 레벨에 비례합니다)"), 11, skillsInv);
				itemset("평온", Material.GREEN_CANDLE, 0, 1, Arrays.asList("🖮🖰 재입력시 평온을 사용합니다",
						"양의기운: 주변적을 공격 및 제압합니다","",ChatColor.BOLD+" X (0.6D + "+BigDecimal.valueOf(Amplify*0.8).setScale(2, RoundingMode.HALF_EVEN)+")",
						"음의기운: 파티원들을 무적상태로 만듭니다","공통: 해로운 효과들을 제거합니다","(피해량과 지속시간은 증폭 레벨에 비례합니다)"), 12, skillsInv);
				itemset("속박진", Material.COARSE_DIRT, 0, 1, Arrays.asList("🖮🖰 재입력시 속박진을 사용합니다","",ChatColor.BOLD+"10 X (0.115D + "+BigDecimal.valueOf(Charm*0.2455).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 부적 레벨에 비례합니다)"), 13, skillsInv);
				itemset("순보", Material.NETHERITE_BOOTS, 0, 1, Arrays.asList("🖮🖰 재입력시 순보를 사용합니다","",ChatColor.BOLD+" X (0.9D + "+BigDecimal.valueOf(Flip*0.9).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 공중제비 레벨에 비례합니다)"), 14, skillsInv);
				itemset("운기조식", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("기운의 버프 효과를 두배로 증가시킵니다"), 15, skillsInv);
				itemset("통찰", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다"), 16, skillsInv);
				itemset("명상", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("🖮🖰 웅크리기 + num4","주변 파티원들을 치료합니다","",ChatColor.BOLD+"60 X 0.3D"), 17, skillsInv);

				itemset("승천", Material.CUT_COPPER_SLAB, 0, 1, Arrays.asList("🖮🖰 재입력시 승천을 사용합니다","",ChatColor.BOLD+" X (1.2D + "+BigDecimal.valueOf(Imagery*1.3).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 연상 레벨에 비례합니다)"), 19, skillsInv);
				itemset("기공장", Material.BOW, 0, 1, Arrays.asList("🖮🖰 재입력시 기공장을 사용합니다","",ChatColor.BOLD+" X (0.115D + "+BigDecimal.valueOf(Charm*0.2455).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 기공권 레벨에 비례합니다)"), 20, skillsInv);
				itemset("만트라", Material.ACACIA_LEAVES, 0, 1, Arrays.asList("🖮🖰 재입력시 만트라를 사용합니다","","기운 변경을 해도 5초동안 효과가 지속됩니다","",
						"5초간 파티원들의 허기를 채웁니다","",ChatColor.BOLD+" X (0.8D + "+BigDecimal.valueOf(Amplify*0.9).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 증폭 레벨에 비례합니다)"), 21, skillsInv);
				itemset("기공파", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("🖮🖰 재입력시 기공파를 사용합니다","",ChatColor.BOLD+"12 X (0.1D + "+BigDecimal.valueOf(Charm*0.12).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 부적 레벨에 비례합니다)"), 22, skillsInv);
				itemset("초월", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다","명상의 재사용대기시간이 감소합니다","모든 개체들을 통과합니다", "투사체에 면역이 됩니다"), 25, skillsInv);
				itemset("음양팔괘진", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("🖮🖰 웅크리기 + num5","",ChatColor.BOLD+"8 X 1.5D, 3.5D, 12.5D"), 26, skillsInv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("스킬포인트", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+SkillPoints,"","클릭하면 스킬포인트가 초기화 됩니다"), 35, skillsInv);

		}
		else {
			itemset("CombustInside", Material.RED_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+CombustInside,"","🖮🖰 Sneaking + Hit",
					"",ChatColor.BOLD+" X (1.8D + "+BigDecimal.valueOf(CombustInside*1.8).setScale(2, RoundingMode.HALF_EVEN)+")" ,"Master LV.50"), 0, skillsInv);
			itemset("Imagery", Material.BLACK_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+Imagery,"","🖮🖰 SwapHand","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(Imagery*0.9).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 1, skillsInv);
			itemset("Wave", Material.YELLOW_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+Wave,"","🖮🖰 Rightclick","",ChatColor.BOLD+" X (0.67D + "+BigDecimal.valueOf(Wave*0.64).setScale(2, RoundingMode.HALF_EVEN)+")" ,"Master LV.50"), 2, skillsInv);
			itemset("Amplify", Material.BROWN_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+Amplify,"","🖮🖰 Sneaking + Rightclick","Increases Aura Range For" + BigDecimal.valueOf(Amplify*0.1).setScale(2, RoundingMode.HALF_EVEN) + "secs",
					"Aura Of Positive: Attack Near By Entity",ChatColor.BOLD+" X (0.2D + "+BigDecimal.valueOf(Amplify*0.2).setScale(2, RoundingMode.HALF_EVEN)+")","","Aura Of Negetive: Heal Party Instantly" ,"Master LV.50"), 3, skillsInv);
			itemset("Charm", Material.BLACK_BANNER, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+Charm,"","🖮🖰 SwapHand + Sneaking","",
					ChatColor.BOLD+"6 X (0.315D + "+BigDecimal.valueOf(Charm*0.3455).setScale(2, RoundingMode.HALF_EVEN)+"), "+
					ChatColor.BOLD+" X (1.37D + "+BigDecimal.valueOf(Charm*1.1).setScale(2, RoundingMode.HALF_EVEN)+"), ",
							"" ,"Master LV.50"), 4, skillsInv);
			itemset("Flip", Material.BLUE_BANNER, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+Flip,"","🖮🖰 Jump + LeftClick",
					"",ChatColor.BOLD+" X (0.3D + "+BigDecimal.valueOf(Flip*0.34).setScale(2, RoundingMode.HALF_EVEN)+")" ,"Master LV.50"), 5, skillsInv);
			itemset("Aura", Material.CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+Aura,"","🖮🖰 Sneaking + Item Change(MouseWheel)"
					,ChatColor.UNDERLINE+"✬[Fire, Earth]","🖮🖰 Aura Of Positive: Increases Party Damage(30%), Speed","",ChatColor.UNDERLINE+"✬[Frost, Dark]","🖮🖰 Aura Of Negative: ","Increases Party Armor 30%","Heal Party When You Attack", "Master Lv.1"), 6, skillsInv);
			itemset("Vibe", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+Vibe,"","Increases damage","",ChatColor.BOLD+" + "+BigDecimal.valueOf(Vibe*0.6).setScale(2, RoundingMode.HALF_EVEN)), 7, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("Resonance(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("Blast(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("CharmSlash(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("Serenity(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("BindingFormation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("Shunpo(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("MentalTraining(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 15, skillsInv);
				itemset("Insight(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("Meditation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Resonance", Material.BELL, 0, 1, Arrays.asList("Inflict splash damage"), 9, skillsInv);
				itemset("Blast", Material.FIREWORK_STAR, 0, 1, Arrays.asList("🖮🖰 Use Blast When Use Once More","",ChatColor.BOLD+" X (1.4D + "+BigDecimal.valueOf(Imagery*1.5).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Imagery)"), 10, skillsInv);
				itemset("CharmSlash", Material.DIAMOND_SWORD, 0, 1, Arrays.asList("🖮🖰 Use CharmSlash When Use Once More","",ChatColor.BOLD+"2 X (0.68D + "+BigDecimal.valueOf(Wave*0.76).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Wave)"), 11, skillsInv);
				itemset("Serenity", Material.GREEN_CANDLE, 0, 1, Arrays.asList("🖮🖰 Use Serenity When Use Once More",
						"Aura Of Positive: Attack & Hold Near By Entity","",ChatColor.BOLD+" X (0.6D + "+BigDecimal.valueOf(Amplify*0.8).setScale(2, RoundingMode.HALF_EVEN)+")",
						"Aura Of Negetive: Set Party Invulnerable","Both: Remove Negetive Effect","(Damage & Duration Affected By Amplify)"), 12, skillsInv);
				itemset("BindingFormation", Material.COARSE_DIRT, 0, 1, Arrays.asList("🖮🖰 Use BindingFormation When Use Once More","",ChatColor.BOLD+"10 X (0.115D + "+BigDecimal.valueOf(Charm*0.2455).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Charm)"), 13, skillsInv);
				itemset("Shunpo", Material.NETHERITE_BOOTS, 0, 1, Arrays.asList("🖮🖰 Use Shunpo When Use Once More","",ChatColor.BOLD+" X (0.9D + "+BigDecimal.valueOf(Flip*0.9).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Flip)"), 14, skillsInv);
				itemset("MentalTraining", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("Doubles Aura Buff Effect"), 15, skillsInv);
				itemset("Insight", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16, skillsInv);
				itemset("Meditation", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("🖮🖰 Sneaking + num4","Heal Near Party","",ChatColor.BOLD+"60 X 0.3D"), 17, skillsInv);

				itemset("Ascension(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
				itemset("SpiritStorm(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("Mantra(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
				itemset("EnergyBlast(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("Transcendence(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("Eight Trigrams Formation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("Resonance", Material.BELL, 0, 1, Arrays.asList("Inflict splash damage"), 9, skillsInv);
				itemset("Blast", Material.FIREWORK_STAR, 0, 1, Arrays.asList("🖮🖰 Use Blast When Use Once More","",ChatColor.BOLD+" X (1.4D + "+BigDecimal.valueOf(Imagery*1.5).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Imagery)"), 10, skillsInv);
				itemset("CharmSlash", Material.DIAMOND_SWORD, 0, 1, Arrays.asList("🖮🖰 Use CharmSlash When Use Once More","",ChatColor.BOLD+"2 X (0.68D + "+BigDecimal.valueOf(Wave*0.76).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Wave)"), 11, skillsInv);
				itemset("Serenity", Material.GREEN_CANDLE, 0, 1, Arrays.asList("🖮🖰 Use Serenity When Use Once More",
						"Aura Of Positive: Attack & Hold Near By Entity","",ChatColor.BOLD+" X (0.6D + "+BigDecimal.valueOf(Amplify*0.8).setScale(2, RoundingMode.HALF_EVEN)+")",
						"Aura Of Negetive: Set Party Invulnerable","Both: Remove Negetive Effect","(Damage & Duration Affected By Amplify)"), 12, skillsInv);
				itemset("BindingFormation", Material.COARSE_DIRT, 0, 1, Arrays.asList("🖮🖰 Use BindingFormation When Use Once More","",ChatColor.BOLD+"10 X (0.115D + "+BigDecimal.valueOf(Charm*0.2455).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Charm)"), 13, skillsInv);
				itemset("Shunpo", Material.NETHERITE_BOOTS, 0, 1, Arrays.asList("🖮🖰 Use Shunpo When Use Once More","",ChatColor.BOLD+" X (0.9D + "+BigDecimal.valueOf(Flip*0.9).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Flip)"), 14, skillsInv);
				itemset("MentalTraining", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("Doubles Aura Buff Effect"), 15, skillsInv);
				itemset("Insight", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16, skillsInv);
				itemset("Meditation", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("🖮🖰 Sneaking + num4","Heal Near Party","",ChatColor.BOLD+"60 X 0.3D"), 17, skillsInv);

				itemset("Ascension", Material.CUT_COPPER_SLAB, 0, 1, Arrays.asList("🖮🖰 Use Ascension When Use Once More","",ChatColor.BOLD+" X (1.2D + "+BigDecimal.valueOf(Imagery*1.3).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Imagery)"), 19, skillsInv);
				itemset("SpiritStorm", Material.BOW, 0, 1, Arrays.asList("🖮🖰 Use SpiritStorm When Use Once More","",ChatColor.BOLD+" X (0.115D + "+BigDecimal.valueOf(Charm*0.2455).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Wave)"), 20, skillsInv);
				itemset("Mantra", Material.ACACIA_LEAVES, 0, 1, Arrays.asList("🖮🖰 Use Mantra When Use Once More","","Aura Effects Remain For Amplify Duration","Even if you Change Aura","",
						"Give Saturation Effect to Party","For 5 seconds","",ChatColor.BOLD+" X (0.8D + "+BigDecimal.valueOf(Amplify*0.9).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Amplify)"), 21, skillsInv);
				itemset("EnergyBlast", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("🖮🖰 Use EnergyBlast When Use Once More","",ChatColor.BOLD+"12 X (0.1D + "+BigDecimal.valueOf(Charm*0.12).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Charm)"), 22, skillsInv);
				itemset("Transcendence", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decrease Meditation Cooldown","Able to Penetrate All Entities", "Be Immune To Projectile"), 25, skillsInv);
				itemset("Eight Trigrams Formation", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("🖮🖰 Sneaking + num5","",ChatColor.BOLD+"8 X 1.5D, 3.5D, 12.5D"), 26, skillsInv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("SkillPoints", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+tsd.SkillPoints.getOrDefault(p.getUniqueId(), 0),"","Click if you want to reset your skill's levels"), 35, skillsInv);

		}
		
		Obtained.itemset(p, skillsInv);
		p.openInventory(skillsInv);
	}


}