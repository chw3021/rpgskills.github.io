package io.github.chw3021.classes.witchdoctor;



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

public class WdcSkillsGui extends SkillsGui{
	
	
	
	public void WdcSkillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		WdcSkillsData wsd = new WdcSkillsData(WdcSkillsData.loadData(path +"/plugins/RPGskills/WdcSkillsData.data"));
		Inventory skillsInv = Bukkit.createInventory(null, 36, "Wdcskills");
		

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("독사의송곳니", Material.STONE_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Fangs.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 웅크리기 + 손바꾸기","",ChatColor.BOLD+"5 X (0.4D + "+BigDecimal.valueOf(wsd.Fangs.getOrDefault(p.getUniqueId(), 0)*0.3).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 0, skillsInv);
			itemset("수호의로아:보수", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Bosou.getOrDefault(p.getUniqueId(), 0),"","우클릭", 
					"파티원들의 공격력이 증가합니다",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.05*(1+wsd.Bosou.getOrDefault(p.getUniqueId(), 0)*0.04)).setScale(2, RoundingMode.HALF_EVEN),
					"","파티원들의 받는피해가 감소합니다",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.3+(1+wsd.Bosou.getOrDefault(p.getUniqueId(), 0)*0.045)).setScale(2, RoundingMode.HALF_EVEN), "Master LV.10"), 1, skillsInv);
			itemset("수확", Material.IRON_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Harvest.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"✬[대지]","🖮🖰 좌클릭", "영혼이 아직 흡수되지 않은 적에게 재사용시", "체력과 허기를 즉시 회복합니다", "적이 받는 피해를 15%증가시킵니다","",ChatColor.BOLD+" X (0.5D + "+BigDecimal.valueOf(wsd.Harvest.getOrDefault(p.getUniqueId(), 0)*0.56).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 2, skillsInv);
			itemset("망령", Material.GOLDEN_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Wraith.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"✬[화염]","🖮🖰 좌클릭 + 웅크리기","",ChatColor.BOLD+"20 X (0.2D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*0.32).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 3, skillsInv);
			itemset("유체이탈", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.AstralProjection.getOrDefault(p.getUniqueId(), 0),"","손바꾸기", "웅크리기시 종료", "Master LV.1"), 4, skillsInv);
			itemset("주술", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Incantation.getOrDefault(p.getUniqueId(), 0),"","우클릭 + 웅크리기", "Master LV.1"), 5, skillsInv);
			itemset("소생", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("불사의 토템이 사용되지 않는대신", "별도의 대기시간이 적용됩니다", "파티원들도 소생이 가능해지고", "개개인의 대기시간이 적용됩니다(25초)", "패시브"), 6, skillsInv);
			itemset("중재의로아:렉바", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Legba.getOrDefault(p.getUniqueId(), 0),"","대지 저항력이 증가합니다","공격력이 증가합니다","",ChatColor.BOLD+" + "+BigDecimal.valueOf(wsd.Legba.getOrDefault(p.getUniqueId(), 0)*0.585).setScale(2, RoundingMode.HALF_EVEN)), 7, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("조글린돌격(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("금술(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("원혼(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("팬텀슴격(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("가벼운발걸음(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("풍요의토템(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("삶의순환(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("바롱사메디(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("조글린돌격", Material.ZOGLIN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 재입력시 조글린돌격을 사용합니다","(피해량은 독사의송곳니 레벨에 비레합니다)","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(wsd.Fangs.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, skillsInv);
				itemset("금술", Material.NETHER_BRICK, 0, 1, Arrays.asList("재입력시 금술을 사용합니다","파티원 각각의 체력을 20% 소모합니다", "재생효과를 주고 "+ChatColor.BOLD+BigDecimal.valueOf(wsd.Bosou.getOrDefault(p.getUniqueId() ,0)*0.5).setScale(2, RoundingMode.HALF_EVEN)+ "초동안 무적상태로 만듭니다") , 10, skillsInv);
				itemset("원혼", Material.DRAGON_BREATH, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 재입력시 원혼을 사용합니다","(피해량은 수확 레벨에 비레합니다)","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(wsd.Harvest.getOrDefault(p.getUniqueId(), 0)*0.76).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, skillsInv);
				itemset("팬텀슴격", Material.BAT_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 재입력시 팬텀슴격을 사용합니다","(피해량은 망령 레벨에 비레합니다)","",ChatColor.BOLD+"19 X (0.3D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*0.342).setScale(2, RoundingMode.HALF_EVEN)+"), 0.5D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*0.442).setScale(2, RoundingMode.HALF_EVEN)), 12, skillsInv);
				itemset("가벼운발걸음", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("유체이탈 종료후 이동속도와 점프 효과를 얻습니다"), 13, skillsInv);
				itemset("풍요의토템", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("파티원들의 허기를 채웁니다"), 14, skillsInv);
				itemset("삶의순환", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다", ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 수확에 영향을 받은 적이 사망하면" ,"좀비로 변합니다", 
						"좀비는 12초동안 움직입니다",
						"좀비의 능력치들은 렉바 레벨에 비례합니다","(최대 6마리)"
						), 16, skillsInv);
				itemset("바롱사메디", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("웅크리기 + num4","파티원들이 죽는것을 방지해줍니다", "파티원들의 공격력이 증가합니다","",ChatColor.BOLD+"+ "+BigDecimal.valueOf(p.getLevel()*0.5).setScale(2, RoundingMode.HALF_EVEN)), 17, skillsInv);

				itemset("송곳니쇄도(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("공포(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("희생(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
				itemset("용기의토템(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
				itemset("불멸(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 24, skillsInv);
				itemset("영적조율(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("영원의 마법진(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("조글린돌격", Material.ZOGLIN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 재입력시 조글린돌격을 사용합니다","(피해량은 독사의송곳니 레벨에 비레합니다)","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(wsd.Fangs.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, skillsInv);
				itemset("금술", Material.NETHER_BRICK, 0, 1, Arrays.asList("재입력시 금술을 사용합니다","파티원 각각의 체력을 20% 소모합니다", "재생효과를 주고 "+ChatColor.BOLD+BigDecimal.valueOf(wsd.Bosou.getOrDefault(p.getUniqueId() ,0)*0.5).setScale(2, RoundingMode.HALF_EVEN)+ "초동안 무적상태로 만듭니다") , 10, skillsInv);
				itemset("원혼", Material.DRAGON_BREATH, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 재입력시 원혼을 사용합니다","(피해량은 수확 레벨에 비레합니다)","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(wsd.Harvest.getOrDefault(p.getUniqueId(), 0)*0.76).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, skillsInv);
				itemset("팬텀슴격", Material.BAT_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 재입력시 팬텀슴격을 사용합니다","(피해량은 망령 레벨에 비레합니다)","",ChatColor.BOLD+"19 X (0.3D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*0.342).setScale(2, RoundingMode.HALF_EVEN)+"), 0.5D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*0.442).setScale(2, RoundingMode.HALF_EVEN)), 12, skillsInv);
				itemset("가벼운발걸음", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("유체이탈 종료후 이동속도와 점프 효과를 얻습니다"), 13, skillsInv);
				itemset("풍요의토템", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("파티원들의 허기를 채웁니다"), 14, skillsInv);
				itemset("삶의순환", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다", ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 수확에 영향을 받은 적이 사망하면" ,"좀비로 변합니다", 
						"좀비는 12초동안 움직입니다",
						"좀비의 능력치들은 렉바 레벨에 비례합니다","(최대 6마리)"
						), 16, skillsInv);
				itemset("바롱사메디", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("웅크리기 + num4","파티원들이 죽는것을 방지해줍니다", "파티원들의 공격력이 증가합니다","",ChatColor.BOLD+"+ "+BigDecimal.valueOf(p.getLevel()*0.5).setScale(2, RoundingMode.HALF_EVEN)), 17, skillsInv);

				itemset("송곳니쇄도", Material.MYCELIUM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 재입력시 송곳니쇄도를 사용합니다","조글린은 즉시 공격합니다","송곳니들은 조글린이 돌격한곳에 나타납니다","좀비들은 해당 위치로 이동합니다","(피해량은 독사의송곳니 레벨에 비레합니다)","",ChatColor.BOLD+"10 X (0.7D + "+BigDecimal.valueOf(wsd.Fangs.getOrDefault(p.getUniqueId(), 0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")"), 19, skillsInv);
				itemset("공포", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("수확사용시 적들을 제압합니다"), 20, skillsInv);
				itemset("희생", Material.ZOMBIE_HEAD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 재입력시 좀비들을 폭발시킵니다","","만약 좀비가 없다면","6마리를 즉시 소환합니다","","죽음의 기사는 사라지지 않고","두배의 피해량을 줍니다","(피해량은 망령 레벨에 비레합니다)","",ChatColor.BOLD+" X (1.8D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*2.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 21, skillsInv);
				itemset("용기의토템", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("파티원들의 해로운효과를 제거합니다"), 23, skillsInv);
				itemset("불멸", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("유체이탈 사용시 자신의 소생의 대기시간이 초기화됩니다"), 24, skillsInv);
				itemset("영적조율", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다","바롱사메디 대기시간이 감소합니다"), 25, skillsInv);
				itemset("영원의 마법진", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 웅크리기 + num5","파티에게 힘,재생 효과를 제공합니다","적에게 약함,둔화 효과를 줍니다","좀비대신 죽음의 기사가 소환됩니다","마법진은 15초뒤에 폭발합니다","폭발전 마법진 위에 있던 적들에게","자신과 파티원이 입힌 피해량에 비례해"
						,"폭발의 공격력이 상승합니다","",ChatColor.BOLD+"33D + 피해량*0.05"), 26, skillsInv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("스킬포인트", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+wsd.SkillPoints.getOrDefault(p.getUniqueId(), 0),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, skillsInv);

		}
		else {
			itemset("Fangs", Material.STONE_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Fangs.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Sneaking + SwapHand","",ChatColor.BOLD+"5 X (0.4D + "+BigDecimal.valueOf(wsd.Fangs.getOrDefault(p.getUniqueId(), 0)*0.3).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 0, skillsInv);
			itemset("Bosou", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Bosou.getOrDefault(p.getUniqueId(), 0),"","Sneaking + RightClick", 
					"Increases Party Damage",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.05*(1+wsd.Bosou.getOrDefault(p.getUniqueId(), 0)*0.04)).setScale(2, RoundingMode.HALF_EVEN),
					"","Increases Party Armor",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.3+(1+wsd.Bosou.getOrDefault(p.getUniqueId(), 0)*0.045)).setScale(2, RoundingMode.HALF_EVEN), "Master LV.10"), 1, skillsInv);
			itemset("Harvest", Material.IRON_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Harvest.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 LeftClick", "If you reuse To Enemy", "Whose soul has not yet been absorbed", "you will immediately recover hp and hunger", "Increases Damage Enemy take 15%","",ChatColor.BOLD+" X (0.5D + "+BigDecimal.valueOf(wsd.Harvest.getOrDefault(p.getUniqueId(), 0)*0.56).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 2, skillsInv);
			itemset("Wraith", Material.GOLDEN_HOE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Wraith.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"✬[Flame]","🖮🖰 LeftClick + Sneaking","",ChatColor.BOLD+"20 X (0.2D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*0.32).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 3, skillsInv);
			itemset("AstralProjection", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.AstralProjection.getOrDefault(p.getUniqueId(), 0),"","SwapHand", "Sneaking To Quit Earlier", "Master LV.1"), 4, skillsInv);
			itemset("Incantation", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Incantation.getOrDefault(p.getUniqueId(), 0),"","RightClick + Sneaking", "Master LV.1"), 5, skillsInv);
			itemset("Resurrect", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("Totem won't be used when resurrect", "But Cooldown will be Applied", "Party Member also can be Resuurect", "Cooldown will be applied individual(25secs)", "Passive Skill"), 6, skillsInv);
			itemset("Legba", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+wsd.Legba.getOrDefault(p.getUniqueId(), 0),"","Increases Earth Resistance & Damage","",ChatColor.BOLD+" + "+BigDecimal.valueOf(wsd.Legba.getOrDefault(p.getUniqueId(), 0)*0.585).setScale(2, RoundingMode.HALF_EVEN)), 7, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("ZoglinCharge(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("ForbiddenHex(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("VengefulSpirit(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("PhantomSwoop(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("LightFooted(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("TotemOfAbundant(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("CircleOfLife(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("Baron Samedi(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("ZoglinCharge", Material.ZOGLIN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Use ZoglinCharge When Use Once More","(Damage Affected By Fangs)","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(wsd.Fangs.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, skillsInv);
				itemset("ForbiddenHex", Material.NETHER_BRICK, 0, 1, Arrays.asList("Use ForbiddenHex When Use Once More","Consume 20% of Each Party Health", "Give Regeneration Effect", "Set Party Invulnerable for"+ChatColor.BOLD+BigDecimal.valueOf(wsd.Bosou.getOrDefault(p.getUniqueId() ,0)*0.5).setScale(2, RoundingMode.HALF_EVEN)+ "secs") , 10, skillsInv);
				itemset("VengefulSpirit", Material.DRAGON_BREATH, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Use VengefulSpirit When Use Once More","(Damage Affected By Harvest)","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(wsd.Harvest.getOrDefault(p.getUniqueId(), 0)*0.76).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, skillsInv);
				itemset("PhantomSwoop", Material.BAT_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Use PhantomSwoop When Use Once More","(Damage Affected By Wraith)","",ChatColor.BOLD+"19 X (0.3D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*0.342).setScale(2, RoundingMode.HALF_EVEN)+"), 0.5D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*0.442).setScale(2, RoundingMode.HALF_EVEN)), 12, skillsInv);
				itemset("LightFooted", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("Get Speed&Jump Buff After AstralProjection"), 13, skillsInv);
				itemset("TotemOfAbundant", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("Totem Will Saturatify Near By Party"), 14, skillsInv);
				itemset("CircleOfLife", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage", ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Summon a Zombie when Enemy die" ,"Who affected by Harvest", 
						"Zombie will Fight For 12secs",
						"Zombie's Ability Affect By Skill levels","(Max 6 zombies)"
						), 16, skillsInv);
				itemset("Baron Samedi", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + num4","Prevent Party From Death", "Increases Party Damage","",ChatColor.BOLD+"+ "+BigDecimal.valueOf(p.getLevel()*0.5).setScale(2, RoundingMode.HALF_EVEN)), 17, skillsInv);

				itemset("FangsRush(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("Frighten(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("Sacrifice(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
				itemset("TotemOfBravery(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
				itemset("Immortality(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 24, skillsInv);
				itemset("Attunement(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("Eternal MagicCircle(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("ZoglinCharge", Material.ZOGLIN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Use ZoglinCharge When Use Once More","(Damage Affected By Fangs)","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(wsd.Fangs.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, skillsInv);
				itemset("ForbiddenHex", Material.NETHER_BRICK, 0, 1, Arrays.asList("Use ForbiddenHex When Use Once More","Consume 20% of Each Party Health", "Give Regeneration Effect", "Set Party Invulnerable for"+ChatColor.BOLD+BigDecimal.valueOf(wsd.Bosou.getOrDefault(p.getUniqueId() ,0)*0.5).setScale(2, RoundingMode.HALF_EVEN)+ "secs") , 10, skillsInv);
				itemset("VengefulSpirit", Material.DRAGON_BREATH, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Use VengefulSpirit When Use Once More","(Damage Affected By Harvest)","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(wsd.Harvest.getOrDefault(p.getUniqueId(), 0)*0.76).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, skillsInv);
				itemset("PhantomSwoop", Material.BAT_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Use PhantomSwoop When Use Once More","(Damage Affected By Wraith)","",ChatColor.BOLD+"19 X (0.3D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*0.342).setScale(2, RoundingMode.HALF_EVEN)+"), 0.5D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*0.442).setScale(2, RoundingMode.HALF_EVEN)), 12, skillsInv);
				itemset("LightFooted", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("Get Speed&Jump Buff After AstralProjection"), 13, skillsInv);
				itemset("TotemOfAbundant", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("Totem Will Saturatify Near By Party"), 14, skillsInv);
				itemset("CircleOfLife", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage", ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Summon a Zombie when Enemy die" ,"Who affected by Harvest", 
						"Zombie will Fight For 12secs",
						"Zombie's Ability Affect By Skill levels","(Max 6 zombies)"
						), 16, skillsInv);
				itemset("Baron Samedi", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + num4","Prevent Party From Death", "Increases Party Damage","",ChatColor.BOLD+"+ "+BigDecimal.valueOf(p.getLevel()*0.5).setScale(2, RoundingMode.HALF_EVEN)), 17, skillsInv);

				itemset("FangsRush", Material.MYCELIUM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Use FangsRush When Use Once More","Zoglin will Attack Instantly","Fangs Appear Where Zoglin Charged","Zombies Will Teleport to the Location","(Damage Affected By Fangs)","",ChatColor.BOLD+"10 X (0.7D + "+BigDecimal.valueOf(wsd.Fangs.getOrDefault(p.getUniqueId(), 0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")"), 19, skillsInv);
				itemset("Frighten", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("Hold Enemies When Use Harvest"), 20, skillsInv);
				itemset("Sacrifice", Material.ZOMBIE_HEAD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Explode Remaining Zombies","When Use Once More","","If There are No zombies","Summon 6 Zombies Instantly","","Death Knight won't disappear","And damage double","(Damage Affected By Wraith)","",ChatColor.BOLD+" X (1.8D + "+BigDecimal.valueOf(wsd.Wraith.getOrDefault(p.getUniqueId(), 0)*2.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 21, skillsInv);
				itemset("TotemOfBravery", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("Removes negetive effects of party"), 23, skillsInv);
				itemset("Immortality", Material.TOTEM_OF_UNDYING, 0, 1, Arrays.asList("Remove Your Resurrect's Cooldown", "When You Use AstralProjection"), 24, skillsInv);
				itemset("Attunement", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decrease Baron Samedi Cooldown"), 25, skillsInv);
				itemset("Eternal MagicCircle", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Sneaking + num5","Give Strength,Regeneration effect to party","Apply Weakness,Slowness to Enemies","Summons DeathKnight instead of zombie","Circle will explode after 15s", "Exploding power rises in proportion to","the amount of damage your party members", 
						"have done to your enemies on the circle","",ChatColor.BOLD+"33D + Damage*0.05"), 26, skillsInv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("SkillPoints", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+wsd.SkillPoints.getOrDefault(p.getUniqueId(), 0),"","Click if you want to reset your skill's levels"), 35, skillsInv);

		}
		
		
		Obtained.itemset(p, skillsInv);
		p.openInventory(skillsInv);
	}


}