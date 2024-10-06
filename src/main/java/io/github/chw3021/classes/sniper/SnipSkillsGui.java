package io.github.chw3021.classes.sniper;



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

public class SnipSkillsGui extends SkillsGui{
	


	
	
	public void Snipskillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		SnipSkillsData ssd = new SnipSkillsData(SnipSkillsData.loadData(path +"/plugins/RPGskills/SnipskillsData.data"));
		Inventory Snipskillsinv = Bukkit.createInventory(null, 54, "Snipskills");
		Obtained.itemset(p, Snipskillsinv);

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("밧줄타기", Material.CROSSBOW, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Rope.getOrDefault(p.getUniqueId(),0),"","점프 + 좌클릭", "Master LV.1"), 0, Snipskillsinv);
			itemset("철갑화살", Material.TIPPED_ARROW, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.ArmourPiercingArrow.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[바람 계열]","웅크리기 + 손바꾸기", 
					"적의 현재체력의 "+ BigDecimal.valueOf(1+0.1*ssd.ArmourPiercingArrow.getOrDefault(p.getUniqueId(),0)).setScale(2, RoundingMode.HALF_EVEN)  +"% 의 피해를 줍니다", "Master LV.50"), 1, Snipskillsinv);
			itemset("섬광탄", Material.SNOWBALL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.FlashBomb.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[바람 계열]","점프 + 손바꾸기","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.5*(1+ssd.FlashBomb.getOrDefault(p.getUniqueId(),0)*0.0687)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 2, Snipskillsinv);
			itemset("조명지뢰", Material.GLOWSTONE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Flare.getOrDefault(p.getUniqueId(),0),"","웅크리기 + 좌클릭", "Master LV.1"), 3, Snipskillsinv);
			itemset("공습", Material.FIREWORK_ROCKET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.AirStrike.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[바람 계열]","적중시 로켓들이 떨어집니다","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.4*(1+ssd.AirStrike.getOrDefault(p.getUniqueId(),0)*0.0321)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 4, Snipskillsinv);
			itemset("은폐", Material.SPYGLASS, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Camouflage.getOrDefault(p.getUniqueId(),0),"","웅크리기", "Master LV.1"), 5, Snipskillsinv);
			itemset("개조", Material.CROSSBOW, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Remodeling.getOrDefault(p.getUniqueId(),0),"",
					"더 강력하고 빠른 화살이 나갑니다",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.123*(1+ssd.Remodeling.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D","", 
					"화살의 관통레벨이 증가합니다",ChatColor.BOLD+"+ "+ssd.Remodeling.getOrDefault(p.getUniqueId(),0)/2,"", 
					"엔더맨과 위더보호막을 공격할수 있습니다","","다중발사 마법부여시", "화살들이 중앙으로 모이고", "각각 화살은 피해량이 절반으로 감소합니다", "Master LV.50"), 6, Snipskillsinv);
			itemset("헤드샷", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.HeadShot.getOrDefault(p.getUniqueId(),0),"","적의 머리부분을 적중시 발동됩니다", "스킬레벨이 높을수록 판정이 좋아집니다", "철갑화살과 궁극기는 적용되지 않습니다","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.36*(1+ssd.HeadShot.getOrDefault(p.getUniqueId(),0)*0.036)).setScale(2, RoundingMode.HALF_EVEN)), 7, Snipskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("즉시장전(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, Snipskillsinv);
				itemset("충격화살(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, Snipskillsinv);
				itemset("연막(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, Snipskillsinv);
				itemset("교란(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, Snipskillsinv);
				itemset("지원사격(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, Snipskillsinv);
				itemset("포복(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, Snipskillsinv);
				itemset("회피기동(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 15, Snipskillsinv);
				itemset("정밀(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 16, Snipskillsinv);
				itemset("최후의 한발(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, Snipskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("즉시장전", Material.LEAD, 0, 1, Arrays.asList("밧줄타기에 성공하면 화살하나를", "즉시 장전합니다","일반 화살만 장전 가능합니다"), 9, Snipskillsinv);
				itemset("충격화살", Material.TIPPED_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 충격화살을 발사합니다","(피해량은 철갑화살 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.3*(1+ssd.ArmourPiercingArrow.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Snipskillsinv);
				itemset("연막", Material.SMOKER, 0, 1, Arrays.asList("재입력시 연막탄을 투척합니다","연막안에서 웅크리기시 무적상태가 됩니다","(지속시간은 섬광탄 레벨에 비례합니다)","",ChatColor.BOLD+""+BigDecimal.valueOf((40+ssd.FlashBomb.getOrDefault(p.getUniqueId(),0)*0.1)/10d).setScale(1, RoundingMode.HALF_EVEN)+"초"), 11, Snipskillsinv);
				itemset("교란", Material.GLOW_INK_SAC, 0, 1, Arrays.asList("적을 잠시 기절시킵니다"), 12, Snipskillsinv);
				itemset("지원사격", Material.TARGET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","적중시 후속사격이 이루어집니다", "(피해량은 공습 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*ssd.AirStrike.getOrDefault(p.getUniqueId(),0)*0.05).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Snipskillsinv);
				itemset("포복", Material.COPPER_BLOCK, 0, 1, Arrays.asList("웅크리기 + 아이템바꾸기(마우스휠)로", "포복사용이 가능해집니다", "재사용 대기시간 1초"), 14, Snipskillsinv);
				itemset("회피기동", Material.GLASS_PANE, 0, 1, Arrays.asList("사격후 이동속도가 증가합니다"), 15, Snipskillsinv);
				itemset("정밀", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다"), 16, Snipskillsinv);
				itemset("최후의 한발", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("웅크리기 + num4","",ChatColor.BOLD+"+ 10.0D"), 17, Snipskillsinv);

				itemset("포복장전(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 18, Snipskillsinv);
				itemset("대전차로켓(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 19, Snipskillsinv);
				itemset("근접항공지원(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 20, Snipskillsinv);
				itemset("낙하산(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 23, Snipskillsinv);
				itemset("침착(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 25, Snipskillsinv);
				itemset("독수리분대(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, Snipskillsinv);
			}
			else {
				itemset("즉시장전", Material.LEAD, 0, 1, Arrays.asList("밧줄타기에 성공하면 화살하나를", "즉시 장전합니다","일반 화살만 장전 가능합니다"), 9, Snipskillsinv);
				itemset("충격화살", Material.TIPPED_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 충격화살을 발사합니다","(피해량은 철갑화살 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.3*(1+ssd.ArmourPiercingArrow.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Snipskillsinv);
				itemset("연막", Material.SMOKER, 0, 1, Arrays.asList("재입력시 연막탄을 투척합니다","연막안에서 웅크리기시 무적상태가 됩니다","(지속시간은 섬광탄 레벨에 비례합니다)","",ChatColor.BOLD+""+BigDecimal.valueOf((40+ssd.FlashBomb.getOrDefault(p.getUniqueId(),0)*0.1)/10d).setScale(1, RoundingMode.HALF_EVEN)+"초"), 11, Snipskillsinv);
				itemset("교란", Material.GLOW_INK_SAC, 0, 1, Arrays.asList("적을 잠시 기절시킵니다"), 12, Snipskillsinv);
				itemset("지원사격", Material.TARGET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","적중시 후속사격이 이루어집니다", "(피해량은 공습 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*ssd.AirStrike.getOrDefault(p.getUniqueId(),0)*0.05).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Snipskillsinv);
				itemset("포복", Material.COPPER_BLOCK, 0, 1, Arrays.asList("웅크리기 + 아이템바꾸기(마우스휠)로", "포복사용이 가능해집니다", "재사용 대기시간 1초"), 14, Snipskillsinv);
				itemset("회피기동", Material.GLASS_PANE, 0, 1, Arrays.asList("사격후 이동속도가 증가합니다"), 15, Snipskillsinv);
				itemset("정밀", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다"), 16, Snipskillsinv);
				itemset("최후의 한발", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("웅크리기 + num4","",ChatColor.BOLD+"+ 10.0D"), 17, Snipskillsinv);

				itemset("포복장전", Material.CROSSBOW, 0, 1, Arrays.asList("포복사용시 화살하나를 장전합니다","휠을 아래로 내리면 제자리에서 장전합니다","일반 화살만 장전 가능합니다"), 18, Snipskillsinv);
				itemset("대전차로켓", Material.BEDROCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 대전차로켓을 발사합니다","적의 방어력에 비례해 더높은 피해를 줍니다","(피해량은 철갑화살 레벨에 비례합니다)","",ChatColor.BOLD+
						" X ("+BigDecimal.valueOf(1.3*(1+ssd.ArmourPiercingArrow.getOrDefault(p.getUniqueId(),0)*0.1)).setScale(2, RoundingMode.HALF_EVEN)+"X (1+적방어력)) D"), 19, Snipskillsinv);
				itemset("근접항공지원", Material.TNT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 근접항공지원을 요청합니다", "(피해량은 공습 레벨에 비례합니다)",
						"",ChatColor.BOLD+"12 X "+BigDecimal.valueOf(0.34*(1 + ssd.FlashBomb.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, Snipskillsinv);
				itemset("낙하산", Material.ELYTRA, 0, 1, Arrays.asList("웅크리기 도중 느린낙하효과를 얻습니다"), 23, Snipskillsinv);
				itemset("침착", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다","최후의 한발 재사용대기시간이 감소합니다", "헤드샷이 항상 발동됩니다"), 25, Snipskillsinv);
				itemset("독수리분대", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("웅크리기 + num5","",ChatColor.BOLD+"40 X 0.5D, 6.5D"), 26, Snipskillsinv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Snipskillsinv);
			itemset("스킬포인트", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+ssd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, Snipskillsinv);

		}
		else {
			itemset("Rope", Material.CROSSBOW, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Rope.getOrDefault(p.getUniqueId(),0),"","Jump + LeftClick", "Master LV.1"), 0, Snipskillsinv);
			itemset("ArmourPiercingArrow", Material.TIPPED_ARROW, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.ArmourPiercingArrow.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","Sneaking + SwapHand", 
					"Damage "+ BigDecimal.valueOf(1+0.1*ssd.ArmourPiercingArrow.getOrDefault(p.getUniqueId(),0)).setScale(2, RoundingMode.HALF_EVEN)  +"% of Enemy's CurrentHealth", "Master LV.50"), 1, Snipskillsinv);
			itemset("FlashBomb", Material.SNOWBALL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.FlashBomb.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","Jump + SwapHand","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.5*(1+ssd.FlashBomb.getOrDefault(p.getUniqueId(),0)*0.0687)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 2, Snipskillsinv);
			itemset("Flare", Material.GLOWSTONE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Flare.getOrDefault(p.getUniqueId(),0),"","Sneaking + LeftClick", "Master LV.1"), 3, Snipskillsinv);
			itemset("AirStrike", Material.FIREWORK_ROCKET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.AirStrike.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","Rocket will Drop When Hit","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.4*(1+ssd.AirStrike.getOrDefault(p.getUniqueId(),0)*0.0321)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 4, Snipskillsinv);
			itemset("Camouflage", Material.SPYGLASS, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Camouflage.getOrDefault(p.getUniqueId(),0),"","Sneaking", "Master LV.1"), 5, Snipskillsinv);
			itemset("Remodeling", Material.CROSSBOW, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Remodeling.getOrDefault(p.getUniqueId(),0),"",
					"Replace more faster, stronger arrow",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.123*(1+ssd.Remodeling.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D","", 
					"Increases Arrow's Pierce Level",ChatColor.BOLD+"+ "+ssd.Remodeling.getOrDefault(p.getUniqueId(),0)*2,"", 
					"Be Able to Attack Enderman & WitherBarrior","","If You Add MultiShot Enchantment", "Arrows Will be focused", "Halve Each Arrow's Damage", "Master LV.50"), 6, Snipskillsinv);
			itemset("HeadShot", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.HeadShot.getOrDefault(p.getUniqueId(),0),"","Activated when Hit Target's Head", "Higher level gets Better hit detection", "APA, Ult won't be applied","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.36*(1+ssd.HeadShot.getOrDefault(p.getUniqueId(),0)*0.036)).setScale(2, RoundingMode.HALF_EVEN)), 7, Snipskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("InstantCharge(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Snipskillsinv);
				itemset("ShockArrow(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Snipskillsinv);
				itemset("SmokeShell", Material.SMOKER, 0, 1, Arrays.asList("Throw SmokeShell When Use Once More","Set Invulnerable While Sneaking In Smoke","(Duration Affected By FlashBomb)","",ChatColor.BOLD+""+BigDecimal.valueOf((40+ssd.FlashBomb.getOrDefault(p.getUniqueId(),0)*0.1)/10d).setScale(1, RoundingMode.HALF_EVEN)+"s"), 11, Snipskillsinv);
				itemset("Sabotage(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Snipskillsinv);
				itemset("Backup(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Snipskillsinv);
				itemset("Crawl(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Snipskillsinv);
				itemset("Evasion(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 15, Snipskillsinv);
				itemset("Precision(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Snipskillsinv);
				itemset("The Last One(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Snipskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("InstantCharge", Material.LEAD, 0, 1, Arrays.asList("Charge an Arrow Instantly", "When You Success to Ride Rope","You Can Only Charge Normal Arrow"), 9, Snipskillsinv);
				itemset("ShockArrow", Material.TIPPED_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Shot ShockArrow When Use Once More","(Damage Affected By ArmourPiercingArrow)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.3*(1+ssd.ArmourPiercingArrow.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Snipskillsinv);
				itemset("SmokeShell", Material.SMOKER, 0, 1, Arrays.asList("Throw SmokeShell When Use Once More","Set Invulnerable While Sneaking In Smoke"), 11, Snipskillsinv);
				itemset("Sabotage", Material.GLOW_INK_SAC, 0, 1, Arrays.asList("Stun Hit Enemy Shortly"), 12, Snipskillsinv);
				itemset("Backup", Material.TARGET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Backup Shooter will Shot To Hit Target", "(Damage Affected By AirStrike)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*ssd.AirStrike.getOrDefault(p.getUniqueId(),0)*0.05).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Snipskillsinv);
				itemset("Crawl", Material.COPPER_BLOCK, 0, 1, Arrays.asList("Able To Crawl By Using", "Sneaking + ChangingItem(MouseWheel)", "Cooldown 1s"), 14, Snipskillsinv);
				itemset("Evasion", Material.GLASS_PANE, 0, 1, Arrays.asList("Get Speed Effect After Shooting"), 15, Snipskillsinv);
				itemset("Precision", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16, Snipskillsinv);
				itemset("The Last One", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + num4","",ChatColor.BOLD+"+ 10.0D"), 17, Snipskillsinv);

				itemset("CrawlingCharge(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, Snipskillsinv);
				itemset("Destroyer(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, Snipskillsinv);
				itemset("DangerClose(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, Snipskillsinv);
				itemset("Parachute(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, Snipskillsinv);
				itemset("Composure(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Snipskillsinv);
				itemset("Team Eagle(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Snipskillsinv);
			}
			else {
				itemset("InstantCharge", Material.LEAD, 0, 1, Arrays.asList("Charge an Arrow Instantly", "When You Success to Ride Rope","You Can Only Charge Normal Arrow"), 9, Snipskillsinv);
				itemset("ShockArrow", Material.TIPPED_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Shot ShockArrow When Use Once More","(Damage Affected By ArmourPiercingArrow)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.3*(1+ssd.ArmourPiercingArrow.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Snipskillsinv);
				itemset("SmokeShell", Material.SMOKER, 0, 1, Arrays.asList("Throw SmokeShell When Use Once More","Set Invulnerable While Sneaking In Smoke","(Duration Affected By FlashBomb)","",ChatColor.BOLD+""+BigDecimal.valueOf((40+ssd.FlashBomb.getOrDefault(p.getUniqueId(),0)*0.1)/10d).setScale(1, RoundingMode.HALF_EVEN)+"s"), 11, Snipskillsinv);
				itemset("Sabotage", Material.GLOW_INK_SAC, 0, 1, Arrays.asList("Stun Hit Enemy Shortly"), 12, Snipskillsinv);
				itemset("Backup", Material.TARGET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Backup Shooter will Shot To Hit Target", "(Damage Affected By AirStrike)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*ssd.AirStrike.getOrDefault(p.getUniqueId(),0)*0.05).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Snipskillsinv);
				itemset("Crawl", Material.COPPER_BLOCK, 0, 1, Arrays.asList("Able To Crawl By Using", "Sneaking + ChangingItem(MouseWheel)", "Cooldown 1s"), 14, Snipskillsinv);
				itemset("Evasion", Material.GLASS_PANE, 0, 1, Arrays.asList("Get Speed Effect After Shooting"), 15, Snipskillsinv);
				itemset("Precision", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16, Snipskillsinv);
				itemset("The Last One", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + num4","",ChatColor.BOLD+"+ 10.0D"), 17, Snipskillsinv);

				itemset("CrawlingCharge", Material.CROSSBOW, 0, 1, Arrays.asList("Charge Arrow Instantly When Use Crawl","Charge in position if [Scroll Down]","You Can Only Charge Normal Arrow"), 18, Snipskillsinv);
				itemset("Destroyer", Material.BEDROCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Shot Destroyer When Use Once More","Higher Damage to Armored Enemies","(Damage Affected By ArmourPiercingArrow)","",ChatColor.BOLD+
						" X ("+BigDecimal.valueOf(1.3*(1+ssd.ArmourPiercingArrow.getOrDefault(p.getUniqueId(),0)*0.1)).setScale(2, RoundingMode.HALF_EVEN)+"X (1+Enemy's Armor)) D"), 19, Snipskillsinv);
				itemset("DangerClose", Material.TNT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Request DangerClose When Use Once More", "(Damage Affected By AirStrike)",
						"",ChatColor.BOLD+"12 X "+BigDecimal.valueOf(0.34*(1 + ssd.FlashBomb.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, Snipskillsinv);
				itemset("Parachute", Material.ELYTRA, 0, 1, Arrays.asList("Get Slow Falling Effect While Sneaking"), 23, Snipskillsinv);
				itemset("Composure", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decrease The Last One Cooldown", "HeadShot Always Activate"), 25, Snipskillsinv);
				itemset("Team Eagle", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + num5","",ChatColor.BOLD+"40 X 0.5D, 6.5D"), 26, Snipskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Snipskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+ssd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, Snipskillsinv);

		}
		
		
		p.openInventory(Snipskillsinv);
	}


}