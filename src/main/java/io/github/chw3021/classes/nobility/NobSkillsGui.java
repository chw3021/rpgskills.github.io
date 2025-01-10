package io.github.chw3021.classes.nobility;



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

public class NobSkillsGui extends SkillsGui{
	

	public void NobSkillsinv(Player p)
	{
		Inventory skillsInv = Bukkit.createInventory(null, 36, "Nobskills");
	    String path = new File("").getAbsolutePath();
		NobSkillsData fsd = new NobSkillsData(NobSkillsData.loadData(path +"/plugins/RPGskills/NobSkillsData.data"));
		
		

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("돌격", Material.NAUTILUS_SHELL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Assault.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"✬[물]","🖮🖰 웅크리기 + 손바꾸기","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.35*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.036)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 0, skillsInv);
			itemset("물바퀴", Material.PRISMARINE_CRYSTALS, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"✬[물]","🖮🖰 손바꾸기","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.15*(1+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1)*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 1, skillsInv);
			itemset("폭풍", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Storm.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"✬[물]","🖮🖰 웅크리기 + 좌클릭", "표식이 새겨진 적에게 폭풍을 일으킵니다","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.96*(1+fsd.Storm.getOrDefault(p.getUniqueId(),1)*0.0443)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 2, skillsInv);
			itemset("가디언지원대", Material.GUARDIAN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"✬[물]","🖮🖰 아이템 변경시 가디언들은 사라집니다","공격한 적에게 반격을 합니다",ChatColor.BOLD+""+BigDecimal.valueOf(fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*1).setScale(2, RoundingMode.HALF_EVEN)+" + 0.1D",
					"","가디언이 공격력을 보태줍니다",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.56*(1+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.20"), 3, skillsInv);
			itemset("전이", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Transition.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"✬[물]","🖮🖰 웅크리기 + 투척","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+fsd.Transition.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 4, skillsInv);
			itemset("돌고래타기", Material.DOLPHIN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.DolphinSurf.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"✬[물]","🖮🖰 점프 + 좌클릭", "우클릭시 돌고래에서 내리고 주변적에게 피해를 줍니다","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.36*(1+fsd.DolphinSurf.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 5, skillsInv);
			itemset("삼지창의주인", Material.HEART_OF_THE_SEA, 0, 1, Arrays.asList(ChatColor.AQUA+"패시브","","삼지창을 투척시 해당 삼지창이 당신에게 결속됩니다","", "[맨손으로 웅크리기 + 좌클릭]시 삼지창을 즉시 불러옵니다","" ,"한번에 여러개의 삼지창을 던지면","마지막에 던진 삼지창이 결속됩니다","", "삼지창을 받기전까지 무적상태가 됩니다","", "삼지창이 너무 멀리있거나 공허에 빠지면","즉시 돌아옵니다","","삼지창을 아직 받지않은상태면","공허에 빠지지 않습니다"), 6, skillsInv);
			itemset("바다의표식", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.MarkOfSea.getOrDefault(p.getUniqueId(),1),"","공격력이 증가합니다","가디언의 반사데미지에 면역이됩니다", "적에게 표식을 새깁니다", "[폭풍] 사용시 적의 표식이 사라집니다", "","삼지창에 충절,집전 마법부여를 합니다", "수영시 물에 이로운 효과들을 얻습니다","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.15*(1+fsd.MarkOfSea.getOrDefault(p.getUniqueId(),1)*0.0636)).setScale(2, RoundingMode.HALF_EVEN)), 7, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("위치사수(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("해방(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("거북요원(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("엘더가디언(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("아홀로틀분대(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("발광오징어(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("고결(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("바다소용돌이(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("위치사수", Material.TARGET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 재입력시 돌격을 중지합니다","(피해량은 돌격 레벨에 비례합니다)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.1*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("해방", Material.CONDUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 재입력시 삼지창의 힘을 해방합니다", "(피해량은 물바퀴 레벨에 비례합니다)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.16*(1+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, skillsInv);
				itemset("거북요원", Material.TURTLE_HELMET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 거북요원이 적들을 휩씁니다", "(피해량은 폭풍 레벨에 비례합니다)","",ChatColor.BOLD+"2 X "+BigDecimal.valueOf(0.3*(1+fsd.Storm.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, skillsInv);
				itemset("엘더가디언", Material.ELDER_GUARDIAN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 가디언들을 엘더가디언으로 교체합니다","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, skillsInv);
				itemset("아홀로틀분대", Material.AXOLOTL_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 삼지창의 위치에 아홀로틀분대를 부릅니다","보스,리더몹을 우선적으로 공격합니다","(피해량은 전이 레벨에 비례합니다)","",ChatColor.BOLD+"5 X 6 X "+BigDecimal.valueOf(0.05*(1+fsd.Transition.getOrDefault(p.getUniqueId(),1)*0.016)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, skillsInv);
				itemset("발광오징어", Material.GLOW_INK_SAC, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 종료시 발광오징어를 소환합니다","(피해량은 돌고래타기 레벨에 비례합니다)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.36*(1+fsd.DolphinSurf.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, skillsInv);
				itemset("고결", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다", "[공격 역할군]의 방어력감소 효과를 제거합니다", "삼지창의 속도가 증가합니다"), 16, skillsInv);
				itemset("바다소용돌이", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 웅크리기 + num4","",ChatColor.BOLD+"10 X 0.46D + 14 X 0.8D"), 17, skillsInv);

				itemset("분사공격(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("바다의눈(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
				itemset("전지전능 (잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("가디언저주(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
				itemset("호우(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("통치자(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("바다행진(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("위치사수", Material.TARGET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 재입력시 돌격을 중지합니다","(피해량은 돌격 레벨에 비례합니다)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.1*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("해방", Material.CONDUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 재입력시 삼지창의 힘을 해방합니다", "(피해량은 물바퀴 레벨에 비례합니다)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.16*(1+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, skillsInv);
				itemset("거북요원", Material.TURTLE_HELMET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 거북요원이 적들을 휩씁니다", "(피해량은 폭풍 레벨에 비례합니다)","",ChatColor.BOLD+"2 X "+BigDecimal.valueOf(0.3*(1+fsd.Storm.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, skillsInv);
				itemset("엘더가디언", Material.ELDER_GUARDIAN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 가디언들을 엘더가디언으로 교체합니다","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, skillsInv);
				itemset("아홀로틀분대", Material.AXOLOTL_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 삼지창의 위치에 아홀로틀분대를 부릅니다","보스,리더몹을 우선적으로 공격합니다","(피해량은 전이 레벨에 비례합니다)","",ChatColor.BOLD+"5 X 6 X "+BigDecimal.valueOf(0.05*(1+fsd.Transition.getOrDefault(p.getUniqueId(),1)*0.016)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, skillsInv);
				itemset("발광오징어", Material.GLOW_INK_SAC, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 종료시 발광오징어를 소환합니다","(피해량은 돌고래타기 레벨에 비례합니다)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.36*(1+fsd.DolphinSurf.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, skillsInv);
				itemset("고결", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다", "[공격 역할군]의 방어력감소 효과를 제거합니다", "삼지창의 속도가 증가합니다"), 16, skillsInv);
				itemset("바다소용돌이", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 웅크리기 + num4","",ChatColor.BOLD+"10 X 0.46D + 20 X 0.5D"), 17, skillsInv);

				itemset("분사공격", Material.INK_SAC, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 재입력시 분사공격을 명령합니다","(피해량은 돌격 레벨에 비례합니다)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.2*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 18, skillsInv);
				itemset("바다의눈", Material.SEA_LANTERN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 재입력시 바다의눈을 개안합니다", "(피해량은 물바퀴 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.95*(1+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1)*0.52)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, skillsInv);
				itemset("전지전능", Material.HEART_OF_THE_SEA, 0, 1, Arrays.asList("폭풍의 대기시간이 절반으로 감소합니다"), 20, skillsInv);
				itemset("가디언저주", Material.PRISMARINE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 [웅크리기 + 아이템변경[마우스휠]로","가디언의 저주를 사용할수 있습니다", "(피해량은 가디언지원대 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.33*(1+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*0.056)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 21, skillsInv);
				itemset("호우", Material.POINTED_DRIPSTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 삼지창위치에 호우를 부릅니다","(피해량은 전이 레벨에 비례합니다)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.2*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.026)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, skillsInv);
				itemset("통치자", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다","바다소용돌이 재사용대기시간이 감소합니다"), 25, skillsInv);
				itemset("바다대행진", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[물]","🖮🖰 웅크리기 + num5","",ChatColor.BOLD+"200 X 0.2D"), 26, skillsInv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("스킬포인트", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),1),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, skillsInv);
		
		}
		else {
			itemset("Assault", Material.NAUTILUS_SHELL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Assault.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Sneaking + SwapHand","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.35*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.036)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 0, skillsInv);
			itemset("WaterWheel", Material.PRISMARINE_CRYSTALS, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"✬[Water]","🖮🖰 SwapHand","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.15*(1+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1)*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 1, skillsInv);
			itemset("Storm", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Storm.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Sneaking + LeftClick", "Call Storm to Marked Entites","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.96*(1+fsd.Storm.getOrDefault(p.getUniqueId(),1)*0.0443)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 2, skillsInv);
			itemset("GuardianSupport", Material.GUARDIAN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Guardians will disappear", "When you change holding Item"
					,"Attack Enemies that hit you",ChatColor.BOLD+""+BigDecimal.valueOf(fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*1).setScale(2, RoundingMode.HALF_EVEN)+" + 0.1D",
					"","Guardians Support your Attack",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.56*(1+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.20"), 3, skillsInv);
			itemset("Transition", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Transition.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Sneaking + Throw","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+fsd.Transition.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 4, skillsInv);
			itemset("DolphinSurf", Material.DOLPHIN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.DolphinSurf.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Jump + LeftClick", "RightClick Will Quit Surfing", "Damage NearbyEntites When You Quit Surfing","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.36*(1+fsd.DolphinSurf.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 5, skillsInv);
			itemset("Owner", Material.HEART_OF_THE_SEA, 0, 1, Arrays.asList(ChatColor.AQUA+"Passive","","Trident will belong to you since you Throw it","", "You can call back earlier Trident", "Using [Sneaking + LeftClick with BareHands]","🖮🖰 " ,"You should Throw One Trident at Once","", "Set Armor Max","Before You get back Trident","", "Trident Will Teleport To You If Trident is","Too Far Away From You or Located Void","","Prevent From Falling to Void While Trident Floating"), 6, skillsInv);
			itemset("MarkOfSea", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.MarkOfSea.getOrDefault(p.getUniqueId(),1),"","Increases Damage","Immune to Guardian's Reflecting Damage", "Mark Attacked Enemies", "Remove Mark When Enemy Attacked By [Storm]", "","Give Loyalty & Channeling Enchant if You Don't Have", "Get Water Ability When Swim","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.15*(1+fsd.MarkOfSea.getOrDefault(p.getUniqueId(),1)*0.0636)).setScale(2, RoundingMode.HALF_EVEN)), 7, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("HoldPosition(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("Release(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("AgentTurtle(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("ElderGuardian(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("AxolotlSquad(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("GlowSquid(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("Noble(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("Whirlpool(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("HoldPosition", Material.TARGET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Make Stop Assaulting", "When You Use Once More","(Damage Affected By Assault)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.1*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("Release", Material.CONDUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Release Trident Force When You Use Once More", "(Damage Affected By WaterWheel)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.16*(1+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, skillsInv);
				itemset("AgentTurtle", Material.TURTLE_HELMET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 AgentTurtle will Attack Enemies", "(Damage Affected By Storm)","",ChatColor.BOLD+"2 X "+BigDecimal.valueOf(0.3*(1+fsd.Storm.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, skillsInv);
				itemset("ElderGuardian", Material.ELDER_GUARDIAN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Replace Guardians to an Elder Guardian","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, skillsInv);
				itemset("AxolotlSquad", Material.AXOLOTL_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Summon AxolotlSquad to Hit Position","Target Boss & Leader First","(Damage Affected By Transition)","",ChatColor.BOLD+"5 X 6 X "+BigDecimal.valueOf(0.05*(1+fsd.Transition.getOrDefault(p.getUniqueId(),1)*0.016)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, skillsInv);
				itemset("GlowSquid", Material.GLOW_INK_SAC, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Summon GlowSquids When Surfing end","(Damage Affected By DolphinSurf)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.36*(1+fsd.DolphinSurf.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, skillsInv);
				itemset("Noble", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage", "Remove Nuker's Armor Panalty", "Increases Trident Speed"), 16, skillsInv);
				itemset("Whirlpool", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Sneaking + num4","",ChatColor.BOLD+"10 X 0.46D + 20 X 0.5D"), 17, skillsInv);

				itemset("SprayAttack(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("EyesOfSea(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
				itemset("Omnipotent (Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("GuardianCurse(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
				itemset("Downpour(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("Ruler(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("OceanMarch(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("HoldPosition", Material.TARGET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Make Stop Assaulting", "When You Use Once More","(Damage Affected By Assault)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.1*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("Release", Material.CONDUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Release Trident Force When You Use Once More", "(Damage Affected By WaterWheel)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.16*(1+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, skillsInv);
				itemset("AgentTurtle", Material.TURTLE_HELMET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 AgentTurtle will Attack Enemies", "(Damage Affected By Storm)","",ChatColor.BOLD+"2 X "+BigDecimal.valueOf(0.3*(1+fsd.Storm.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, skillsInv);
				itemset("ElderGuardian", Material.ELDER_GUARDIAN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Replace Guardians to an Elder Guardian","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, skillsInv);
				itemset("AxolotlSquad", Material.AXOLOTL_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Summon AxolotlSquad to Hit Position","Target Boss & Leader First","(Damage Affected By Transition)","",ChatColor.BOLD+"5 X 6 X "+BigDecimal.valueOf(0.05*(1+fsd.Transition.getOrDefault(p.getUniqueId(),1)*0.016)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, skillsInv);
				itemset("GlowSquid", Material.GLOW_INK_SAC, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Summon GlowSquids When Surfing end","(Damage Affected By DolphinSurf)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.36*(1+fsd.DolphinSurf.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, skillsInv);
				itemset("Noble", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage", "Remove Nuker's Armor Panalty", "Increases Trident Speed"), 16, skillsInv);
				itemset("Whirlpool", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Sneaking + num4","",ChatColor.BOLD+"10 X 1.46D"), 17, skillsInv);

				itemset("SprayAttack", Material.INK_SAC, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Order SprayAttack When You Use Once More","(Damage Affected By Assault)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.2*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 18, skillsInv);
				itemset("EyesOfSea", Material.SEA_LANTERN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Rouse EyesOfSea When You Use Once More", "(Damage Affected By WaterWheel)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.95*(1+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1)*0.52)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, skillsInv);
				itemset("Omnipotent", Material.HEART_OF_THE_SEA, 0, 1, Arrays.asList("Halves Storm Cooldown"), 20, skillsInv);
				itemset("GuardianCurse", Material.PRISMARINE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Able To Use GuardianCurse","By [Sneaking + ChangeItem(MouseWheel)]", "(Damage Affected By GuardianSupport)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.33*(1+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*0.056)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 21, skillsInv);
				itemset("Downpour", Material.POINTED_DRIPSTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Summon Downpour to Hit Position","(Damage Affected By Transition)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.2*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.026)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, skillsInv);
				itemset("Ruler", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decrease Whirlpool Cooldown"), 25, skillsInv);
				itemset("OceanMarch", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Water]","🖮🖰 Sneaking + num5","",ChatColor.BOLD+"10 X 0.46D + 20 X 0.5D"), 26, skillsInv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("SkillPoints", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),1),"","Click if you want to reset your skill's levels"), 35, skillsInv);
		
		}
		
		Obtained.itemset(p, skillsInv);
		p.openInventory(skillsInv);
	}


}