
package io.github.chw3021.classes.swordman;



import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.classes.SkillsGui;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class SwordSkillsGui extends SkillsGui{
	


	
	public void SwordSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		SwordSkillsData ssd = new SwordSkillsData(SwordSkillsData.loadData(path +"/plugins/RPGskills/SwordSkillsData.data"));
		Inventory skillsInv = Bukkit.createInventory(null, 36, "Swordskills");
		
		

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("올려베기", Material.WOODEN_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Rising.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[대지]","🖮🖰 우클릭 + 웅크리기"
					,"",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.45*(1+ssd.Rising.getOrDefault(p.getUniqueId(),0)*0.036)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 0, skillsInv);
			itemset("일격", Material.STONE_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Strike.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[대지]","🖮🖰 점프 + 좌클릭"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8 *(1+ ssd.Strike.getOrDefault(p.getUniqueId(),0)*0.0669)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 1, skillsInv);
			itemset("지진", Material.IRON_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.SwordDrive.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[대지]","🖮🖰 손바꾸기",
					"흡수효과를 얻습니다 "+ (20+(ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)*2))/20+"초 "+ (ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)/10)+"레벨"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.65*(1 + ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)*0.0536)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 2, skillsInv);
			itemset("섬격", Material.IRON_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.FlashyRush.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[대지]","🖮🖰 우클릭","섬격으로 적처치시 대기시간이 초기화됩니다"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.FlashyRush.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 3, skillsInv);
			itemset("발도", Material.GOLDEN_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[대지]","🖮🖰 손바꾸기 + 웅크리기"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.23*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.065)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 4, skillsInv);
			itemset("급습", Material.DIAMOND_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Swoop.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[대지]"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.87*(1+ssd.Swoop.getOrDefault(p.getUniqueId(),0)*0.067)).setScale(2, RoundingMode.HALF_EVEN)+"D","웅크리기 + 근접공격", "Master LV.50"), 5, skillsInv);
			
			itemset("막기", Material.SHIELD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Guard.getOrDefault(p.getUniqueId(),0),"","🖮🖰 웅크리기", 
					"피해 감소량: "+ ChatColor.BOLD+""+BigDecimal.valueOf(1 - (0.2 - ssd.Guard.getOrDefault(p.getUniqueId(),0)*0.016)).setScale(2, RoundingMode.HALF_EVEN)+"%",
					"막기를 ", 	Math.round(5*(1-p.getAttribute(Attribute.LUCK).getValue()/1024)/(Proficiency.getpro(p)+1))/10.0+"초동안 미사용시","게이지가 서서히 회복됩니다","Master LV.10"), 6, skillsInv);
			itemset("검술", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Dualbladed.getOrDefault(p.getUniqueId(),0),"", "공격력이 상승합니다",
					"",ChatColor.BOLD+" X "+BigDecimal.valueOf((1.7+ssd.Dualbladed.getOrDefault(p.getUniqueId(),0)*0.03896)).setScale(2, RoundingMode.HALF_EVEN)), 7, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("낙엽(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("검풍(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("충검(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("자상(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("검무(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("약점베기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("방어자세(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 15, skillsInv);
				itemset("검기방출(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("비검(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("낙엽", Material.BIG_DRIPLEAF, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 재입력시 낙엽을 사용합니다","(피해량은 올려베기 레벨에 비례합니다)"
						,"",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.45*(1+ssd.Rising.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("검풍", Material.NETHERITE_SWORD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 재입력시 검풍을 사용합니다","(피해량은 일격 레벨에 비례합니다)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.92 *(1+ ssd.Strike.getOrDefault(p.getUniqueId(),0)*0.078)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, skillsInv);
				itemset("충검", Material.POINTED_DRIPSTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 재입력시 충검을 사용합니다","(피해량은 지진 레벨에 비례합니다)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, skillsInv);
				itemset("자상", Material.EXPOSED_CUT_COPPER_STAIRS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 적을 잠시 제압한후, 피해를 한번 더줍니다","(피해량은 자상 레벨에 비례합니다)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.FlashyRush.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, skillsInv);
				itemset("검무", Material.GOLDEN_BOOTS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 재입력시 검무를 사용합니다","(피해량은 발도 레벨에 비례합니다)"
						,"",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.5*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.2*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.0335)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, skillsInv);
				itemset("약점베기", Material.SCAFFOLDING, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","적을 잠시 제압하고 추가피해를 줍니다"), 14, skillsInv);
				itemset("방어자세", Material.SHIELD, 0, 1, Arrays.asList("막기 게이지량이 증가합니다", "회복 대기시간이 감소합니다"), 15, skillsInv);
				itemset("검기방출", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 범위가 증가합니다"), 16, skillsInv);
				itemset("비검", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 웅크리기 + num4","",ChatColor.BOLD+" X 16.9D"), 17, skillsInv);

				itemset("단공참(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("바람베기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
				itemset("순환(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
				itemset("영혼가르기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("회복(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 24, skillsInv);
				itemset("검신일체(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("심검(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("낙엽", Material.BIG_DRIPLEAF, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 재입력시 낙엽을 사용합니다","(피해량은 올려베기 레벨에 비례합니다)"
						,"",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.45*(1+ssd.Rising.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("검풍", Material.NETHERITE_SWORD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 재입력시 검풍을 사용합니다","(피해량은 일격 레벨에 비례합니다)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.92 *(1+ ssd.Strike.getOrDefault(p.getUniqueId(),0)*0.078)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, skillsInv);
				itemset("충검", Material.POINTED_DRIPSTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 재입력시 충검을 사용합니다","(피해량은 지진 레벨에 비례합니다)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, skillsInv);
				itemset("자상", Material.EXPOSED_CUT_COPPER_STAIRS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 적을 잠시 제압한후, 피해를 한번 더줍니다","(피해량은 자상 레벨에 비례합니다)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.FlashyRush.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, skillsInv);
				itemset("검무", Material.GOLDEN_BOOTS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 재입력시 검무를 사용합니다","(피해량은 발도 레벨에 비례합니다)"
						,"",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.5*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.2*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.0335)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, skillsInv);
				itemset("약점베기", Material.SCAFFOLDING, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 적을 잠시 제압하고 추가피해를 줍니다"), 14, skillsInv);
				itemset("방어자세", Material.SHIELD, 0, 1, Arrays.asList("막기 게이지량이 증가합니다", "회복 대기시간이 감소합니다"), 15, skillsInv);
				itemset("검기방출", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 범위가 증가합니다"), 16, skillsInv);
				itemset("비검", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 웅크리기 + num4","",ChatColor.BOLD+" X 16.9D"), 17, skillsInv);

				itemset("단공참", Material.CUT_COPPER_SLAB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 재입력시 단공참을 사용합니다","(피해량은 올려베기 레벨에 비례합니다)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.5*(1+ssd.Rising.getOrDefault(p.getUniqueId(),0)*0.09)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 18, skillsInv);
				itemset("바람베기", Material.NETHERITE_SWORD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 재입력시 바람베기를 사용합니다","(피해량은 일격 레벨에 비례합니다)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.94 *(1+ ssd.Strike.getOrDefault(p.getUniqueId(),0)*0.078)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, skillsInv);
				itemset("순환", Material.ACACIA_LEAVES, 0, 1, Arrays.asList("다른 스킬 사용시","섬격 재사용대기시간이 1초 감소합니다"), 21, skillsInv);
				itemset("영혼가르기", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 재입력시 영혼가르기를 사용합니다","(피해량은 발도 레벨에 비례합니다)"
						,"",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.43*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, skillsInv);
				itemset("회복", Material.NETHERITE_HELMET, 0, 1, Arrays.asList("막기 게이지량이 증가합니다", "회복 대기시간이 감소합니다"), 24, skillsInv);
				itemset("검신일체", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력, 방어력과 범위가 증가합니다","비검 대기시간이 감소합니다"), 25, skillsInv);
				itemset("심검", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 웅크리기 + num5","",ChatColor.BOLD+"45.1D"), 26, skillsInv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("스킬포인트", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+ssd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, skillsInv);

		}
		else {
			itemset("RisingBlades", Material.WOODEN_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Rising.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 RightClick + Sneaking"
					,"",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.45*(1+ssd.Rising.getOrDefault(p.getUniqueId(),0)*0.036)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 0, skillsInv);
			itemset("Strike", Material.STONE_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Strike.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Jump + LeftClick"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8 *(1+ ssd.Strike.getOrDefault(p.getUniqueId(),0)*0.0669)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 1, skillsInv);
			itemset("SwordDrive", Material.IRON_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.SwordDrive.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 SwapHand",
					"Get Absorbtion Effect "+ (20+(ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)*2))/20+"s "+ (ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)/10)+"lv"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.65*(1 + ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)*0.0536)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 2, skillsInv);
			itemset("FlashyRush", Material.IRON_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.FlashyRush.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Rightclick","Reset Cooldown", "If You Kill an Enemy With FlashRush"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.FlashyRush.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 3, skillsInv);
			itemset("CriticalDraw", Material.GOLDEN_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 SwapHand + Sneaking"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.23*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.065)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 4, skillsInv);
			itemset("Swoop", Material.DIAMOND_SWORD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Swoop.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[Earth]"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.87*(1+ssd.Swoop.getOrDefault(p.getUniqueId(),0)*0.067)).setScale(2, RoundingMode.HALF_EVEN)+"D","🖮🖰 Sneaking + Hit", "Master LV.50"), 5, skillsInv);
			
			itemset("Guard", Material.SHIELD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Guard.getOrDefault(p.getUniqueId(),0),"","🖮🖰 Sneaking", 
					"Reduced Damage: "+ ChatColor.BOLD+""+BigDecimal.valueOf(1 - (0.2 - ssd.Guard.getOrDefault(p.getUniqueId(),0)*0.016)).setScale(2, RoundingMode.HALF_EVEN)+"%",
					"Gage Will be Recovered Slowly", "When You Don't Use Guard for ", 
					Math.round(5*(1-p.getAttribute(Attribute.LUCK).getValue()/1024)/(Proficiency.getpro(p)+1))/10.0+"s","Master LV.10"), 6, skillsInv);
			itemset("Swordsmanship", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Dualbladed.getOrDefault(p.getUniqueId(),0),"","Increases Damage"
					,"",ChatColor.BOLD+" X "+BigDecimal.valueOf((1.7+ssd.Dualbladed.getOrDefault(p.getUniqueId(),0)*0.03896)).setScale(2, RoundingMode.HALF_EVEN)), 7, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("FallenLeaves(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("Wind(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("Spike(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("Stab(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("SwordDance(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("Slash(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("Defensive(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 15, skillsInv);
				itemset("SwordAura(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("SwordStorm(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("FallenLeaves", Material.BIG_DRIPLEAF, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Use FallenLeaves When Use Once More","(Damage Affected By RisingBlades)"
						,"",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.45*(1+ssd.Rising.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("Wind", Material.NETHERITE_SWORD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Use Wind When Use Once More","(Damage Affected By Strike)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.92 *(1+ ssd.Strike.getOrDefault(p.getUniqueId(),0)*0.078)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, skillsInv);
				itemset("Spike", Material.POINTED_DRIPSTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Use Spike When Use Once More","(Damage Affected By SwordDrive)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, skillsInv);
				itemset("Stab", Material.EXPOSED_CUT_COPPER_STAIRS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Hold Hit Enemy Shortly, Then Deal Once More","(Damage Affected By FlashyRush)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.FlashyRush.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, skillsInv);
				itemset("SwordDance", Material.GOLDEN_BOOTS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Use SwordDance When Use Once More","(Damage Affected By CriticalDraw)"
						,"",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.5*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.2*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.0335)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, skillsInv);
				itemset("Slash", Material.SCAFFOLDING, 0, 1, Arrays.asList("Hold Hit Enemy Shortly","Then Attack Once More"), 14, skillsInv);
				itemset("Defensive", Material.SHIELD, 0, 1, Arrays.asList("Increases Guard Gage", "Decreases Recovering Cooldown"), 15, skillsInv);
				itemset("SwordAura", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Range"), 16, skillsInv);
				itemset("SwordStorm", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Sneaking + num4","",ChatColor.BOLD+" X 16.9D"), 17, skillsInv);

				itemset("DividingAir(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("WindBrandish(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
				itemset("Circulation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
				itemset("SoulFlourish(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("Recovery(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 24, skillsInv);
				itemset("SteadyBlade(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("MindSword(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("FallenLeaves", Material.BIG_DRIPLEAF, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Use FallenLeaves When Use Once More","(Damage Affected By RisingBlades)"
						,"",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.45*(1+ssd.Rising.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("Wind", Material.NETHERITE_SWORD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Use Wind When Use Once More","(Damage Affected By Strike)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.92 *(1+ ssd.Strike.getOrDefault(p.getUniqueId(),0)*0.078)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, skillsInv);
				itemset("Spike", Material.POINTED_DRIPSTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Use Spike When Use Once More","(Damage Affected By SwordDrive)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.SwordDrive.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, skillsInv);
				itemset("Stab", Material.EXPOSED_CUT_COPPER_STAIRS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Hold Hit Enemy Shortly, Then Deal Once More","(Damage Affected By FlashyRush)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.73 *(1+ ssd.FlashyRush.getOrDefault(p.getUniqueId(),0)*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, skillsInv);
				itemset("SwordDance", Material.GOLDEN_BOOTS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Use SwordDance When Use Once More","(Damage Affected By CriticalDraw)"
						,"",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.5*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.2*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.0335)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, skillsInv);
				itemset("Slash", Material.SCAFFOLDING, 0, 1, Arrays.asList("Hold Hit Enemy Shortly","Then Attack Once More"), 14, skillsInv);
				itemset("Defensive", Material.SHIELD, 0, 1, Arrays.asList("Increases Guard Gage", "Decreases Recovering Cooldown"), 15, skillsInv);
				itemset("SwordAura", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Range"), 16, skillsInv);
				itemset("SwordStorm", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Sneaking + num4","",ChatColor.BOLD+" X 16.9D"), 17, skillsInv);

				itemset("DividingAir", Material.CUT_COPPER_SLAB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Use DividingAir When Use Once More","(Damage Affected By RisingBlades)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.5*(1+ssd.Rising.getOrDefault(p.getUniqueId(),0)*0.09)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 18, skillsInv);
				itemset("WindBrandish", Material.BOW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Use Wind When Use Once More","(Damage Affected By Strike)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.94 *(1+ ssd.Strike.getOrDefault(p.getUniqueId(),0)*0.078)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, skillsInv);
				itemset("Circulation", Material.ACACIA_LEAVES, 0, 1, Arrays.asList("Decrease Cooldown Of FlashRush 1second","When You Use Another skill"), 21, skillsInv);
				itemset("SoulFlourish", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Use SoulFlourish When Use Once More","(Damage Affected By CriticalDraw)"
						,"",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.43*(1+ ssd.CriticalDraw.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, skillsInv);
				itemset("Recovery", Material.NETHERITE_HELMET, 0, 1, Arrays.asList("Increases Guard Gage", "Decreases Recovering Cooldown"), 24, skillsInv);
				itemset("SteadyBlade", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage, Range & Armor","Decreases SwordStorm Cooldown"), 25, skillsInv);
				itemset("MindSword", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Sprinting + num5","",ChatColor.BOLD+"45.1D"), 26, skillsInv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("SkillPoints", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+ssd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, skillsInv);

		}
		Obtained.itemset(p, skillsInv);
		p.openInventory(skillsInv);
	}


}
