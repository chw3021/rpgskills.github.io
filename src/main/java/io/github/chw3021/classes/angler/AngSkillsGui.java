package io.github.chw3021.classes.angler;



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

public class AngSkillsGui extends SkillsGui{
	
	
	public void AngSkillsinv(Player p)
	{
		Inventory Angskillsinv = Bukkit.createInventory(null, 54, "Angskills");
	    String path = new File("").getAbsolutePath();
		AngSkillsData fsd = new AngSkillsData(AngSkillsData.loadData(path +"/plugins/RPGskills/AngSkillsData.data"));

		Obtained.itemset(p, Angskillsinv);
		
		int bait = fsd.Bait.getOrDefault(p.getUniqueId(),0);
		int fishing = fsd.Fishing.getOrDefault(p.getUniqueId(),0);
		int whipping = fsd.Whipping.getOrDefault(p.getUniqueId(),0);
		int coralliquor = fsd.CoralLiquor.getOrDefault(p.getUniqueId(),0);
		int root = fsd.CoralRoots.getOrDefault(p.getUniqueId(),0);
		int dance = fsd.DrunkenDance.getOrDefault(p.getUniqueId(),0);
		int tech = fsd.Technic.getOrDefault(p.getUniqueId(),0);
		
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			
			itemset("미끼", Material.CARROT_ON_A_STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bait,"",ChatColor.UNDERLINE+"[물 계열]","웅크리기 + 낚시대던지기","주변 파티원들의 체력을 회복합니다","",ChatColor.BOLD+"10 X (0.3D + "+BigDecimal.valueOf(bait*0.35).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 0, Angskillsinv);
			itemset("낚시", Material.FISHING_ROD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fishing,"",ChatColor.UNDERLINE+"[바람 계열]","낚시대를 회수할때 주변 적들을 같이 당깁니다", "낚시 찌의 날아가는 속도가 증가합니다", "일반 낚시의 대기시간이 감소합니다","",ChatColor.BOLD+"X 0.46D" ,"Master LV.1"), 1, Angskillsinv);
			itemset("낚시대법", Material.STRING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+whipping,"",ChatColor.UNDERLINE+"[바람 계열]","좌클릭","",ChatColor.BOLD+" (X 0.65D + "+BigDecimal.valueOf(whipping*0.35).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 2, Angskillsinv);
			itemset("산호주", Material.POTION, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+coralliquor,"","웅크리기 + 손바꾸기", 
					"파티원들을 "+ BigDecimal.valueOf(coralliquor*0.2d).setScale(2, RoundingMode.HALF_EVEN) + "초동안 무적상태로 만듭니다"
					, "파티원들의 피해량이 "+demical(1+coralliquor*0.016* (Proficiency.getpro(p)>=2? 2:1))+"만큼 상승합니다"
					, "파티원들이 받는 피해량이 "+demical(coralliquor*0.01525* (Proficiency.getpro(p)>=2? 2:1))+"만큼 감소합니다"
					, "Master LV.20"), 3, Angskillsinv);
			itemset("산호뿌리", Material.BUBBLE_CORAL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+root,"",ChatColor.UNDERLINE+"[물 계열]","웅크리기 + 좌클릭","",ChatColor.BOLD+"4 X (0.4443D + "+BigDecimal.valueOf(root*0.43).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 4, Angskillsinv);
			itemset("음주가무", Material.SEA_PICKLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+dance,"",ChatColor.UNDERLINE+"[바람 계열]","점프 + 손바꾸기","",ChatColor.BOLD+"10 X (0.3D + "+BigDecimal.valueOf(dance*0.33).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 5, Angskillsinv);
			itemset("노련함", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tech,"","공격력이 증가합니다", "수영시 물관련 효과를 얻습니다","",ChatColor.BOLD+"+ "+BigDecimal.valueOf(tech*0.55).setScale(2, RoundingMode.HALF_EVEN)), 7, Angskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("떡밥(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, Angskillsinv);
				itemset("장비개선(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, Angskillsinv);
				itemset("민간요법(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, Angskillsinv);
				itemset("활력제(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, Angskillsinv);
				itemset("산호감옥(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, Angskillsinv);
				itemset("취보(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, Angskillsinv);
				itemset("여유(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 16, Angskillsinv);
				itemset("바다신의 배(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, Angskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("떡밥", Material.WARPED_FUNGUS_ON_A_STICK, 0, 1, Arrays.asList("포만감을 줍니다", "범위가 증가합니다", ChatColor.UNDERLINE+"[바람 계열]","회수시 주변적을 끌어옵니다","",ChatColor.BOLD+"X 0.8D + "+BigDecimal.valueOf(bait*1.1).setScale(2, RoundingMode.HALF_EVEN)), 9, Angskillsinv);
				itemset("장비개선", Material.FISHING_ROD, 0, 1, Arrays.asList("재사용 대기시간이 감소합니다"), 10, Angskillsinv);
				itemset("민간요법", Material.STRING, 0, 1, Arrays.asList("주변 파티원들을 치유합니다"), 11, Angskillsinv);
				itemset("활력제", Material.SPLASH_POTION, 0, 1, Arrays.asList("힘,재생 효과를 줍니다"), 12, Angskillsinv);
				itemset("산호감옥", Material.BRAIN_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 산호감옥을 사용합니다","산호감옥 내의 아군들은 무적상태가 됩니다", "(피해량은 산호뿌리 레벨에 비례합니다)","",ChatColor.BOLD+"X 1.1D + "+ demical(root*1.5)), 13, Angskillsinv);
				itemset("취보", Material.SEAGRASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 취보를 사용합니다", "(피해량은 음주가무 레벨에 비례합니다)","",ChatColor.BOLD+"X 0.8D + "+BigDecimal.valueOf(dance*0.8).setScale(2, RoundingMode.HALF_EVEN)), 14, Angskillsinv);
				itemset("여유", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("전체 공격력이 증가합니다"), 16, Angskillsinv);
				itemset("만선", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","전투모드중 웅크리기 + num4","파티원들의 해로운 효과를 제거하고","5초간 무적상태로 만듭니다",ChatColor.BOLD+"50 X 0.1D + 4.0D", ""
						,"적들이 받는피해량이 증가합니다",ChatColor.BOLD+"10% + " + demical(p.getLevel()*0.2)+" 추가데미지"), 17, Angskillsinv);
				
				itemset("내뿜기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 21, Angskillsinv);
				itemset("복어폭탄(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 22, Angskillsinv);
				itemset("취격(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 23, Angskillsinv);
				itemset("해탈(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 25, Angskillsinv);
				itemset("물아일체(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, Angskillsinv);
			}
			else {
				itemset("떡밥", Material.WARPED_FUNGUS_ON_A_STICK, 0, 1, Arrays.asList("포만감을 줍니다", "범위가 증가합니다", ChatColor.UNDERLINE+"[바람 계열]","회수시 주변적을 끌어옵니다","",ChatColor.BOLD+"X 0.8D + "+BigDecimal.valueOf(bait*1.1).setScale(2, RoundingMode.HALF_EVEN)), 9, Angskillsinv);
				itemset("장비개선", Material.FISHING_ROD, 0, 1, Arrays.asList("재사용 대기시간이 감소합니다"), 10, Angskillsinv);
				itemset("민간요법", Material.STRING, 0, 1, Arrays.asList("주변 파티원들을 치유합니다"), 11, Angskillsinv);
				itemset("활력제", Material.SPLASH_POTION, 0, 1, Arrays.asList("힘,재생 효과를 줍니다"), 12, Angskillsinv);
				itemset("산호감옥", Material.BRAIN_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 산호감옥을 사용합니다","산호감옥 내의 아군들은 무적상태가 됩니다", "(피해량은 산호뿌리 레벨에 비례합니다)","",ChatColor.BOLD+"X 1.1D + "+BigDecimal.valueOf(root*1.5).setScale(2, RoundingMode.HALF_EVEN)), 13, Angskillsinv);
				itemset("취보", Material.SEAGRASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 취보를 사용합니다", "(피해량은 음주가무 레벨에 비례합니다)","",ChatColor.BOLD+"X 0.8D + "+BigDecimal.valueOf(dance*0.8).setScale(2, RoundingMode.HALF_EVEN)), 14, Angskillsinv);
				itemset("여유", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("전체 공격력이 증가합니다"), 16, Angskillsinv);
				itemset("만선", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","전투모드중 웅크리기 + num4","파티원들의 해로운 효과를 제거하고","5초간 무적상태로 만듭니다",ChatColor.BOLD+"50 X 0.1D + 4.0D", ""
						,"적들이 받는피해량이 증가합니다",ChatColor.BOLD+"10% + " + demical(p.getLevel()*0.2)+" 추가데미지"), 17, Angskillsinv);
				
				itemset("내뿜기", Material.FIRE_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","재입력시 내뿜기를 사용합니다", "(피해량은 산호주 레벨에 비례합니다)","",ChatColor.BOLD+"X 2.1D + "+BigDecimal.valueOf(coralliquor*2.5).setScale(2, RoundingMode.HALF_EVEN)), 21, Angskillsinv);
				itemset("복어폭탄", Material.FIRE_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","재입력시 복어폭탄을 사용합니다", "(피해량은 산호뿌리 레벨에 비례합니다)","",ChatColor.BOLD+"X 1.4D + "+BigDecimal.valueOf(root*1.75).setScale(2, RoundingMode.HALF_EVEN)), 22, Angskillsinv);
				itemset("취격", Material.SEAGRASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 취격을 사용합니다", "(피해량은 음주가무 레벨에 비례합니다)","",ChatColor.BOLD+"X 1.3D + "+ BigDecimal.valueOf(dance*1.3).setScale(2, RoundingMode.HALF_EVEN)), 23, Angskillsinv);
				itemset("해탈", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다","만선 쿨타임이 감소합니다"), 25, Angskillsinv);
				itemset("물아일체", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","전투모드중 웅크리기 + num5","파티원들의 해로운 효과를 제거하고","5초간 무적상태로 만듭니다", "",ChatColor.BOLD+"X 40D"), 26, Angskillsinv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Angskillsinv);
			itemset("스킬포인트", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, Angskillsinv);

		
	    }
		else {

			
			itemset("Bait", Material.CARROT_ON_A_STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bait,"",ChatColor.UNDERLINE+"[Water]","Sneaking + Throw","Hear Near by Party","",ChatColor.BOLD+"10 X (0.3D + "+BigDecimal.valueOf(bait*0.35).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 0, Angskillsinv);
			itemset("Fishing", Material.FISHING_ROD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fishing,"",ChatColor.UNDERLINE+"[Wind]","Pull Near By Enemies when retrieve", "Increases Bobber Speed", "Decreases Waiting time when Fishing","",ChatColor.BOLD+"X 0.46D" ,"Master LV.1"), 1, Angskillsinv);
			itemset("Whipping", Material.STRING, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+whipping,"",ChatColor.UNDERLINE+"[Wind]","LeftClick","",ChatColor.BOLD+" (X 0.65D + "+BigDecimal.valueOf(whipping*0.35).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 2, Angskillsinv);
			itemset("CoralLiquor", Material.POTION, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+coralliquor,"","Sneaking + SwapHand", "Set Your Party Invulnerable for "+ BigDecimal.valueOf(coralliquor*0.2d).setScale(2, RoundingMode.HALF_EVEN) +  "s", "Increases Party Damage",
				"",ChatColor.BOLD+"X "+BigDecimal.valueOf(1+coralliquor*0.016* (Proficiency.getpro(p)>=2? 2:1)).setScale(2, RoundingMode.HALF_EVEN)
				, "Decreases Taking Damage"+demical(fsd.CoralLiquor.get(p.getUniqueId())*0.01525* (Proficiency.getpro(p)>=2? 2:1)), "Master LV.20"), 3, Angskillsinv);
			
			itemset("CoralRoots", Material.BUBBLE_CORAL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+root,"",ChatColor.UNDERLINE+"[Water]","Sneaking + Hit","",ChatColor.BOLD+"4 X (0.4443D + "+BigDecimal.valueOf(root*0.43).setScale(2, RoundingMode.HALF_EVEN)+")"
					, "Master Lv.50"), 4, Angskillsinv);
			itemset("DrunkenDance", Material.SEA_PICKLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+dance,"",ChatColor.UNDERLINE+"[Wind]","Jump + SwapHand","",ChatColor.BOLD+"10 X (0.3D + "+BigDecimal.valueOf(dance*0.33).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 5, Angskillsinv);
			itemset("Technic", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tech,"","Increases Damage", "Get Water Ability When Swim","",ChatColor.BOLD+"+ "+BigDecimal.valueOf(tech*0.55).setScale(2, RoundingMode.HALF_EVEN)), 7, Angskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("Paste bait(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Angskillsinv);
				itemset("Fishing tackle(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Angskillsinv);
				itemset("Therapy(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Angskillsinv);
				itemset("Energizer(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Angskillsinv);
				itemset("CoralPrison(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Angskillsinv);
				itemset("DrunkenDash(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Angskillsinv);
				itemset("LaidBack(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Angskillsinv);
				itemset("Boat Of The Sea God(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Angskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Paste bait", Material.WARPED_FUNGUS_ON_A_STICK, 0, 1, Arrays.asList("Give Saturation Effect", "Increases Range", ChatColor.UNDERLINE+"[Wind]","Pull Hit Enemies When Retreive","",ChatColor.BOLD+"X 0.8D + "+BigDecimal.valueOf(bait*1.1).setScale(2, RoundingMode.HALF_EVEN)), 9, Angskillsinv);
				itemset("Fishing tackle", Material.FISHING_ROD, 0, 1, Arrays.asList("Decreases Cooldown"), 10, Angskillsinv);
				itemset("Therapy", Material.STRING, 0, 1, Arrays.asList("Heal Near By Party"), 11, Angskillsinv);
				itemset("Energizer", Material.SPLASH_POTION, 0, 1, Arrays.asList("Give Strenghth & Regen Effect"), 12, Angskillsinv);
				itemset("CoralPrison", Material.BRAIN_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Summon CoralPrison When Use Once More", "(Damage Affected By CoralRoots)","",ChatColor.BOLD+"X 1.1D + "+ demical(root*1.5)), 13, Angskillsinv);
				itemset("DrunkenDash", Material.SEAGRASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Dash When Use Once More", "(Damage Affected By DrunkenDance)","",ChatColor.BOLD+"X 0.8D + "+BigDecimal.valueOf(dance*0.8).setScale(2, RoundingMode.HALF_EVEN)), 14, Angskillsinv);
				itemset("LaidBack", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16, Angskillsinv);
				itemset("Fully Load", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Sneaking + num4 while CombatMode","Removes All Negetive Effects of Party","And Set Invulnerable for 5 seconds","",ChatColor.BOLD+"50 X 0.1D + 4.0D"), 17, Angskillsinv);
				
				itemset("Spout(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, Angskillsinv);
				itemset("DrunkenSmash(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, Angskillsinv);
				itemset("Liberation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Angskillsinv);
				itemset("Self and Other Oneness(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Angskillsinv);
			}
			else {
				itemset("Paste bait", Material.WARPED_FUNGUS_ON_A_STICK, 0, 1, Arrays.asList("Give Saturation Effect", "Increases Range", ChatColor.UNDERLINE+"[Wind]","Pull Hit Enemies When Retreive","",ChatColor.BOLD+"X 0.8D + "+BigDecimal.valueOf(bait*1.1).setScale(2, RoundingMode.HALF_EVEN)), 9, Angskillsinv);
				itemset("Fishing tackle", Material.FISHING_ROD, 0, 1, Arrays.asList("Decreases Cooldown"), 10, Angskillsinv);
				itemset("Therapy", Material.STRING, 0, 1, Arrays.asList("Heal Near By Party"), 11, Angskillsinv);
				itemset("Energizer", Material.SPLASH_POTION, 0, 1, Arrays.asList("Give Strenghth & Regen Effect"), 12, Angskillsinv);
				itemset("CoralPrison", Material.BRAIN_CORAL_BLOCK, 0, 1, Arrays.asList("Increases Range Instantly"), 13, Angskillsinv);
				itemset("DrunkenDash", Material.SEAGRASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Dash When Use Once More", "(Damage Affected By DrunkenDance)","",ChatColor.BOLD+"X 0.8D + "+BigDecimal.valueOf(dance*0.8).setScale(2, RoundingMode.HALF_EVEN)), 14, Angskillsinv);
				itemset("LaidBack", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16, Angskillsinv);
				itemset("Fully Load", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Sneaking + num4 while CombatMode","Removes All Negetive Effects of Party","And Set Invulnerable for 5 seconds","",ChatColor.BOLD+"50 X 0.1D + 4.0D"), 17, Angskillsinv);
				
				itemset("Spout", Material.FIRE_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Spout Liquor When Use Once More", "Amplify CoralLiquor Buff", "(Damage Affected By CoralLiquor)","",ChatColor.BOLD+"X 2.1D + "+BigDecimal.valueOf(coralliquor*2.5).setScale(2, RoundingMode.HALF_EVEN)), 21, Angskillsinv);
				itemset("DrunkenSmash", Material.SEAGRASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Smash When Use Once More", "(Damage Affected By DrunkenDance)","",ChatColor.BOLD+"X 1.3D + "+ BigDecimal.valueOf(dance*1.3).setScale(2, RoundingMode.HALF_EVEN)), 23, Angskillsinv);
				itemset("Liberation", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decreases Fully Load Cooldown"), 25, Angskillsinv);
				itemset("Self and Other Oneness", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Sneaking + num5 while CombatMode","Removes All Negetive Effects of Party","And Set Invulnerable for 5 seconds","",ChatColor.BOLD+"X 40D"), 26, Angskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Angskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, Angskillsinv);

		}
		p.openInventory(Angskillsinv);
	}


}
