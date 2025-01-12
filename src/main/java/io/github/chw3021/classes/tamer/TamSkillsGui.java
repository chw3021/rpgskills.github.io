package io.github.chw3021.classes.tamer;



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

public class TamSkillsGui extends SkillsGui{
	
	
	public void TamSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		TamSkillsData tsd = new TamSkillsData(TamSkillsData.loadData(path +"/plugins/RPGskills/TamSkillsData.data"));
		Inventory skillsInv = Bukkit.createInventory(null, 36, "TamSkills");
		
		Integer taming = tsd.Taming.getOrDefault(p.getUniqueId(), 0);

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("집중공격", Material.ARROW, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.PressTheAttack.getOrDefault(p.getUniqueId(), 0),"","🖮🖰 손바꾸기(화살발사) 또는 근접공격","화살의 공격력은 동물들 공격력 총합의 5%입니다","","스파이디는 해당적을 붙잡습니다","크리퍼폭탄은 즉시 폭발합니다", "Master LV.1"), 0, skillsInv);
			itemset("스파이디", Material.CAVE_SPIDER_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Spidey.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"✬[대지]","🖮🖰 웅크리기 + 우클릭","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.2*(1+tsd.Spidey.getOrDefault(p.getUniqueId(), 0)*0.02)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 1, skillsInv);
			itemset("반려동물", Material.WOLF_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Pets.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"✬[대지]","🖮🖰 손바꾸기 + 웅크리기"
					,"","늑대: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(5*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.02)*(1+taming*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"
					,"","고양이: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(3*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.02)*(1+taming*0.01)).setScale(2, RoundingMode.HALF_EVEN)+"D"
					,"","앵무새: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(3*(1+taming*0.01)).setScale(2, RoundingMode.HALF_EVEN)+"D",
					"Master LV.50"), 2, skillsInv);
			itemset("벌집", Material.BEEHIVE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.BeeHive.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 점프 + 우클릭","",ChatColor.BOLD+"6마리 X "+BigDecimal.valueOf(3*(1+tsd.BeeHive.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.023)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 3, skillsInv);
			itemset("크리퍼폭탄", Material.CREEPER_HEAD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.CreepBomb.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 웅크리기 + 좌클릭","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.08*(1+tsd.CreepBomb.getOrDefault(p.getUniqueId(), 0)*0.07)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 4, skillsInv);
			itemset("판다", Material.PANDA_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"✬[대지]","🖮🖰 점프 + 좌클릭","주변적을 지속적으로 도발합니다(10초)",""+ChatColor.BOLD+" X "+BigDecimal.valueOf(8*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0)*0.04)*(1+taming*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 5, skillsInv);
			itemset("조련", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+taming,"","동물들의 체력, 공격력, 속도가 증가합니다","",
					ChatColor.GOLD+"🖮🖰 웅크리기 + 아이템바꾸기 위(마우스휠 위방향)", ChatColor.GOLD+"동물들을 모으고 집중공격을 해제합니다","", ChatColor.AQUA+"🖮🖰 웅크리기 + 아이템바꾸기 아래(마우스휠 아래방향)", ChatColor.AQUA+"동물들을 되돌려보냅니다"), 6, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("활력(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("거미줄발사(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("도약공격(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("혼란(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("매력(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("판다휩쓸기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("훈육(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 15, skillsInv);
				itemset("철골렘/골렘강타(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("활력", Material.APPLE, 0, 1, Arrays.asList("화살 적중시 체력을 회복합니다"), 9, skillsInv);
				itemset("거미줄발사", Material.COBWEB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 재입력시 거미줄발사를 사용합니다","(피해량은 스파이디 레벨에 비레합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.Spidey.getOrDefault(p.getUniqueId(), 0)*0.04)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, skillsInv);
				itemset("도약공격", Material.SLIME_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 반려동물들이 존재할때 사용시","동물들이 도약공격을 합니다","만약 집중공격대상이 있다면","해당 목표물로 도약합니다",
						"(피해량은 반려동물 레벨에 비레합니다)","",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.3*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, skillsInv);
				itemset("혼란", Material.BEE_NEST, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 재사용시 벌들이 주변적을 공격합니다","(피해량은 벌집 레벨에 비레합니다)","만약 집중공격대상이 있다면","해당 목표물로 이동합니다",""
						,ChatColor.BOLD+"6 X 5 X "+BigDecimal.valueOf(0.2*(1+tsd.BeeHive.getOrDefault(p.getUniqueId(), 0)*0.01)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, skillsInv);
				itemset("매력", Material.CREEPER_HEAD, 0, 1, Arrays.asList("주변적들을 끌어당깁니다"), 13, skillsInv);
				itemset("판다휩쓸기", Material.ANVIL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 판다가 존재할때 사용시","판다가 휩쓸기공격을 합니다","만약 집중공격대상이 있다면","해당 목표물로 이동합니다",""
						+ "(피해량은 판다 레벨에 비레합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, skillsInv);
				itemset("훈육", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage","","반려동물에 여우와 오셀롯이 추가됩니다"
						,"","여우: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(4*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","오셀롯: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(4*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","모든 동물들이 어떠한 피해에도 면역이됩니다","기술을 재사용해도 기존에있던", "동물들이 사라지지 않습니다"), 15, skillsInv);
				itemset("철골렘/골렘강타", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(
						ChatColor.UNDERLINE+"✬[대지]","🖮🖰 웅크리기 + num4","",ChatColor.BOLD+" X "+BigDecimal.valueOf(20*(1+taming*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","골렘강타는 철골렘이 존재할때만 사용 가능합니다",""
						,ChatColor.BOLD+" X "+BigDecimal.valueOf(10*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 17, skillsInv);

				itemset("기절화살(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("할퀴기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("불닭(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
				itemset("크리퍼구름(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("발구르기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
				itemset("교감(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 24, skillsInv);
				itemset("길들여진 드래곤(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("활력", Material.APPLE, 0, 1, Arrays.asList("화살 적중시 체력을 회복합니다"), 9, skillsInv);
				itemset("거미줄발사", Material.COBWEB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 재입력시 거미줄발사를 사용합니다","(피해량은 스파이디 레벨에 비레합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.Spidey.getOrDefault(p.getUniqueId(), 0)*0.04)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, skillsInv);
				itemset("도약공격", Material.SLIME_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 반려동물들이 존재할때 사용시","동물들이 도약공격을 합니다","만약 집중공격대상이 있다면","해당 목표물로 도약합니다",
						"(피해량은 반려동물 레벨에 비레합니다)","",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.3*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, skillsInv);
				itemset("혼란", Material.BEE_NEST, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 재사용시 벌들이 주변적을 공격합니다","만약 집중공격대상이 있다면","해당 목표물로 이동합니다","(피해량은 벌집 레벨에 비레합니다)",""
						,ChatColor.BOLD+"6 X 5 X "+BigDecimal.valueOf(0.2*(1+tsd.BeeHive.getOrDefault(p.getUniqueId(), 0)*0.01)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, skillsInv);
				itemset("매력", Material.CREEPER_HEAD, 0, 1, Arrays.asList("주변적들을 끌어당깁니다"), 13, skillsInv);
				itemset("판다휩쓸기", Material.ANVIL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 판다가 존재할때 사용시","판다가 휩쓸기공격을 합니다","만약 집중공격대상이 있다면","해당 목표물로 이동합니다",""
						+ "(피해량은 판다 레벨에 비레합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, skillsInv);
				itemset("훈육", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다","","반려동물에 여우와 오셀롯이 추가됩니다"
						,"","여우: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(4*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","오셀롯: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(4*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","모든 동물들이 어떠한 피해에도 면역이됩니다","기술을 재사용해도 기존에있던", "동물들이 사라지지 않습니다"), 15, skillsInv);
				itemset("철골렘/골렘강타", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(
						ChatColor.UNDERLINE+"✬[대지]","🖮🖰 웅크리기 + num4","",ChatColor.BOLD+" X "+BigDecimal.valueOf(20*(1+taming*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","골렘강타는 철골렘이 존재할때만 사용 가능합니다",""
						,ChatColor.BOLD+" X "+BigDecimal.valueOf(10*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 17, skillsInv);

				itemset("기절화살", Material.TIPPED_ARROW, 0, 1, Arrays.asList("적을 기절시킵니다"), 18, skillsInv);
				itemset("할퀴기", Material.SHEARS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 재입력시 동물들이 할퀴기공격을 합니다","(피해량은 반려동물 레벨에 비레합니다)"
						,"",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.5*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.035)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, skillsInv);
				itemset("불닭", Material.CHICKEN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[화염]","🖮🖰 재입력시 불닭을 소환하여 위로 날아오르며", "화염구를 발사합니다","만약 집중공격대상이 있다면","해당 목표물쪽으로 공격합니다","(피해량은 벌집 레벨에 비레합니다)"
						,ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.38*(1+tsd.BeeHive.getOrDefault(p.getUniqueId(), 0)*0.036)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 21, skillsInv);
				itemset("크리퍼구름", Material.CREEPER_BANNER_PATTERN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 폭발후 구름이 생성됩니다","(피해량은 크리퍼폭탄 레벨에 비레합니다)"
						,"",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.08*(1+tsd.CreepBomb.getOrDefault(p.getUniqueId(), 0)*0.07)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, skillsInv);
				itemset("발구르기", Material.COBBLESTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 재입력시 판다가 발구르기를 합니다","(피해량은 판다 레벨에 비레합니다)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 23, skillsInv);
				itemset("교감", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다","철골렘 재사용 대기시간이 감소합니다"), 24, skillsInv);
				itemset("길들여진 드래곤", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(
						ChatColor.UNDERLINE+"✬[대지]","🖮🖰 웅크리기 + num5",ChatColor.BOLD+"150 X "+BigDecimal.valueOf(0.1*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 26, skillsInv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("스킬포인트", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+tsd.SkillPoints.getOrDefault(p.getUniqueId(), 0),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, skillsInv);

		}
		else {
			itemset("PressTheAttack", Material.ARROW, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.PressTheAttack.getOrDefault(p.getUniqueId(), 0),"","🖮🖰 SwapHand(Launch) or MeleeAtack","The arrow's attack power is the same", "As 5% of The sum of animals' attack power","","Spidey Will Hold Hit Enemy","CreepBomb will Explode Instantly", "Master LV.1"), 0, skillsInv);
			itemset("Spidey", Material.CAVE_SPIDER_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Spidey.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Sneaking + Rightclick","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.2*(1+tsd.Spidey.getOrDefault(p.getUniqueId(), 0)*0.02)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 1, skillsInv);
			itemset("Pets", Material.WOLF_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Pets.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 SwapHand + Sneaking"
					,"","Wolf: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(5*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.02)*(1+taming*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"
					,"","Cat: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(3*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.02)*(1+taming*0.01)).setScale(2, RoundingMode.HALF_EVEN)+"D"
					,"","Parrot: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(3*(1+taming*0.01)).setScale(2, RoundingMode.HALF_EVEN)+"D",
					"Master LV.50"), 2, skillsInv);
			itemset("BeeHive", Material.BEEHIVE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.BeeHive.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Jump + RightClick","",ChatColor.BOLD+"6 bees X "+BigDecimal.valueOf(3*(1+tsd.BeeHive.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.023)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 3, skillsInv);
			itemset("CreepBomb", Material.CREEPER_HEAD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.CreepBomb.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Sneaking + LeftClick","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.08*(1+tsd.CreepBomb.getOrDefault(p.getUniqueId(), 0)*0.07)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 4, skillsInv);
			itemset("Panda", Material.PANDA_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Jump + LeftClick","Taunt Near By Enemies Continuously(10s)",""+ChatColor.BOLD+" X "+BigDecimal.valueOf(8*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0)*0.04)*(1+taming*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 5, skillsInv);
			itemset("Taming", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+taming,"","Increases Creatures' HP, Damage, Speed","",
					ChatColor.GOLD+"🖮🖰 Sneaking + ItemChangeUp(MouseWheelUp)", ChatColor.GOLD+"To Assemble Animals And Remove Target","", ChatColor.AQUA+"🖮🖰 Sneaking + ItemChangeDown(MouseWheelDown)", ChatColor.AQUA+"To Return Animals"), 6, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("Vitality(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("WebShoot(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("Leap(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("Disruption(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("Allure(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("PandaSweep(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("AnimalTraining(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 15, skillsInv);
				itemset("IronGolem/GolemSmash(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Vitality", Material.APPLE, 0, 1, Arrays.asList("Get Heal When Hit"), 9, skillsInv);
				itemset("WebSpread", Material.COBWEB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Spidey will Spread Webs When Use Once More","(Damage Affected By Spidey)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.Spidey.getOrDefault(p.getUniqueId(), 0)*0.04)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, skillsInv);
				itemset("Leap", Material.SLIME_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Pets will Leap When Use [Pets]","🖮🖰 While Pets Are Existing","If They Have Target, Leap To Target",
						"(Damage Affected By Pets)","",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.3*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, skillsInv);
				itemset("Disruption", Material.BEE_NEST, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Bees will Attack Near By Enemies","When Use Once More","(Damage Affected By BeeHive)"
						,"If Bees Have Target, Teleport To Target","",ChatColor.BOLD+"6 X 5 X "+BigDecimal.valueOf(0.2*(1+tsd.BeeHive.getOrDefault(p.getUniqueId(), 0)*0.01)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, skillsInv);
				itemset("Allure", Material.CREEPER_HEAD, 0, 1, Arrays.asList("Pull near By enemies"), 13, skillsInv);
				itemset("PandaSweep", Material.ANVIL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Pandy will Sweep When Use [Panda]","🖮🖰 While Panda is Existing","If Panda Has Target, Sweep To Target",""
						+ "(Damage Affected By Panda)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, skillsInv);
				itemset("AnimalTraining", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage","","Add Fox & Ocelot To Pets"
						,"","Fox: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(4*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","Ocelot: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(4*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","Set All Animals' Armor Max","Animals Are Immune to All Negetive Effect","Animals Will Not Disappear","If You Use Skills Once More"), 15, skillsInv);
				itemset("IronGolem/GolemSmash", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Sneaking + num4","",ChatColor.BOLD+" X "+BigDecimal.valueOf(20*(1+taming*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"", "You Can Use GolemSmash When IronGolem Existing"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(10*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 17, skillsInv);

				itemset("StunArrow(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("Scratch(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("HotChicken(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
				itemset("CreepCloud(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("Stomp(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
				itemset("Communication(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 24, skillsInv);
				itemset("Tamed Dragon(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("Vitality", Material.APPLE, 0, 1, Arrays.asList("Get Heal When Hit"), 9, skillsInv);
				itemset("WebSpread", Material.COBWEB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Spidey will Spread Webs When Use Once More","(Damage Affected By Spidey)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.Spidey.getOrDefault(p.getUniqueId(), 0)*0.04)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, skillsInv);
				itemset("Leap", Material.SLIME_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Pets will Leap When Use [Pets]","🖮🖰 While Pets Are Existing","If They Have Target, Leap To Target",
						"(Damage Affected By Pets)","",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.3*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, skillsInv);
				itemset("Disruption", Material.BEE_NEST, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Bees will Attack Near By Enemies","When Use Once More","(Damage Affected By BeeHive)"
						,"If Bees Have Target, Teleport To Target",""
						,ChatColor.BOLD+"6 X 5 X "+BigDecimal.valueOf(0.2*(1+tsd.BeeHive.getOrDefault(p.getUniqueId(), 0)*0.01)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, skillsInv);
				itemset("Allure", Material.CREEPER_HEAD, 0, 1, Arrays.asList("Pull near By enemies"), 13, skillsInv);
				itemset("PandaSweep", Material.ANVIL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Pandy will Sweep When Use [Panda]","🖮🖰 While Panda is Existing","If Panda Has Target, Sweep To Target",""
						+ "(Damage Affected By Panda)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, skillsInv);
				itemset("AnimalTraining", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage","","Add Fox & Ocelot To Pets"
						,"","Fox: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(4*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","Ocelot: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(4*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","Set All Animals' Armor Max","Animals Are Immune to All Negetive Effect","Animals Will Not Disappear","If You Use Skills Once More"), 15, skillsInv);
				itemset("IronGolem/GolemSmash", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Sneaking + num4","",ChatColor.BOLD+" X "+BigDecimal.valueOf(20*(1+taming*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"", "You Can Use GolemSmash When IronGolem Existing"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(10*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 17, skillsInv);

				itemset("StunArrow", Material.TIPPED_ARROW, 0, 1, Arrays.asList("Stun Hit Enemy"), 18, skillsInv);
				itemset("Scratch", Material.SHEARS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Pets will Scratch Attack When Use Once More","(Damage Affected By Pets)"
						,"",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.5*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.035)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, skillsInv);
				itemset("HotChicken", Material.CHICKEN_SPAWN_EGG, 0, 1, Arrays.asList("Summon the HotChicken to soar upwards","launch a fireball","If Chicken has target", "Shot to target","(Damage Affected By BeeHive)"
						,ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.38*(1+tsd.BeeHive.getOrDefault(p.getUniqueId(), 0)*0.036)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 21, skillsInv);
				itemset("CreepCloud", Material.CREEPER_BANNER_PATTERN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Summon Cloud After Explosion","(Damage Affected By CreepBomb)"
						,"",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.08*(1+tsd.CreepBomb.getOrDefault(p.getUniqueId(), 0)*0.07)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, skillsInv);
				itemset("Stomp", Material.COBBLESTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Panda will Stomp When Use Once More","(Damage Affected By Panda)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+taming*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 23, skillsInv);
				itemset("Communication", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decrease IronGolem Cooldown"), 24, skillsInv);
				itemset("Tamed Dragon", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(
						ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Sneaking + num5",ChatColor.BOLD+"150 X "+BigDecimal.valueOf(0.1*(1+tsd.Taming.getOrDefault(p.getUniqueId(),0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 26, skillsInv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("SkillPoints", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+tsd.SkillPoints.getOrDefault(p.getUniqueId(), 0),"","Click if you want to reset your skill's levels"), 35, skillsInv);

		}
		
		Obtained.itemset(p, skillsInv);
		p.openInventory(skillsInv);
	}


}