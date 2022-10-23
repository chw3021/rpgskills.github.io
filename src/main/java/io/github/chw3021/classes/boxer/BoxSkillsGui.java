package io.github.chw3021.classes.boxer;



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

public class BoxSkillsGui extends SkillsGui{
	

	public void BoxSkillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		BoxSkillsData bsd = new BoxSkillsData(BoxSkillsData.loadData(path +"/plugins/RPGskills/BoxSkillsData.data"));
		Inventory Boxskillsinv = Bukkit.createInventory(null, 54, "BoxSkills");
		
		Obtained.itemset(p, Boxskillsinv);

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("아드레날린", Material.LEATHER_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Parrying.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[바람 계열]","손바꾸기 사용시 권기를 방출합니다",ChatColor.BOLD+"충전량 X 1.5","피해를 입을때마다 수치가 상승합니다","수치가 최대충전 (200)이되면","공격력, 방어력, 속도가 증가합니다", "Master Lv.1"), 0, Boxskillsinv);
			itemset("위빙", Material.LEATHER_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Weaving.getOrDefault(p.getUniqueId(),0),"","웅크리기","Master LV.1"), 1, Boxskillsinv);
			itemset("붕권", Material.IRON_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Straight.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[바람 계열]","우클릭","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.3*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 2, Boxskillsinv);
			itemset("철산고", Material.GOLDEN_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.BodyBlow.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[바람 계열]","웅크리기 + 우클릭","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.55*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 3, Boxskillsinv);
			itemset("뎀프시롤", Material.DIAMOND_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[바람 계열]","손바꾸기 + 웅크리기","",ChatColor.BOLD+" 8 X "+BigDecimal.valueOf(0.15*(1+bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0)*0.025)).setScale(2, RoundingMode.HALF_EVEN)+"D + "+" "+BigDecimal.valueOf(0.5*(1+bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 4, Boxskillsinv);
			itemset("반격", Material.CHAINMAIL_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Counter.getOrDefault(p.getUniqueId(),0),"","적의 공격을 패리나 위빙으로","회피성공시 발동됩니다","",ChatColor.BOLD+" X"+BigDecimal.valueOf(1.4+bsd.Counter.getOrDefault(p.getUniqueId(),0)*0.025).setScale(2, RoundingMode.HALF_EVEN),"Master LV.50"), 5, Boxskillsinv);
			itemset("심호흡", Material.WRITABLE_BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Rest.getOrDefault(p.getUniqueId(),0),"","웅크리기(유지)","Master LV.1"), 6, Boxskillsinv);
			itemset("단련", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Training.getOrDefault(p.getUniqueId(),0),"","공격력이 증가합니다",ChatColor.BOLD+" X"+BigDecimal.valueOf(1.25+bsd.Training.getOrDefault(p.getUniqueId(),0)*0.02405).setScale(2, RoundingMode.HALF_EVEN),"","적의 공격과 투사체들을", "스킬사용 또는 일반공격으로 패리를 해서", "받는피해를 70% 감소시킵니다","(투사체는 튕겨낼수 있습니다)"
					,"","바람 저항력이 증가합니다"), 7, Boxskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("긴장(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, Boxskillsinv);
				itemset("피커브(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, Boxskillsinv);
				itemset("승천권(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, Boxskillsinv);
				itemset("지열권(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, Boxskillsinv);
				itemset("몰입(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, Boxskillsinv);
				itemset("기회(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, Boxskillsinv);
				itemset("강심장(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 15, Boxskillsinv);
				itemset("인내(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 16, Boxskillsinv);
				itemset("일격권(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, Boxskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("긴장", Material.TORCH, 0, 1, Arrays.asList("수치를 즉시 최대화 시킵니다"), 9, Boxskillsinv);
				itemset("피커브", Material.NETHERITE_HELMET, 0, 1, Arrays.asList("웅크리기중 방어력이 증가합니다"), 10, Boxskillsinv);
				itemset("승천권", Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 승천권을 사용합니다","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.4*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.046)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(피해량은 붕권 레벨에 비례합니다)"), 11, Boxskillsinv);
				itemset("지열권", Material.POPPED_CHORUS_FRUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 지열권을 사용합니다","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.35*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(피해량은 철산고 레벨에 비례합니다)"), 12, Boxskillsinv);
				itemset("몰입", Material.RAIL, 0, 1, Arrays.asList("재입력시 뎀프시롤을 한번더 사용할수 있습니다"), 13, Boxskillsinv);
				itemset("기회", Material.SHIELD, 0, 1, Arrays.asList("적에게 공격 받아도 반격이 활성화 됩니다"), 14, Boxskillsinv);
				itemset("강심장", Material.GOLDEN_APPLE, 0, 1, Arrays.asList("체력 재생효과가 증가합니다"), 15, Boxskillsinv);
				itemset("인내", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("전체 공격력과 방어력이 증가합니다"), 16, Boxskillsinv);
				itemset("일격권", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","웅크리기 + 아이템던지기", "",ChatColor.BOLD+" X 12.1D"), 17, Boxskillsinv);
				
				itemset("더킹(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 19, Boxskillsinv);
				itemset("대지가르기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 20, Boxskillsinv);
				itemset("권풍(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 21, Boxskillsinv);
				itemset("가속(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 22, Boxskillsinv);
				itemset("활력(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 25, Boxskillsinv);
				itemset("철인의 의지(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, Boxskillsinv);
			}
			else {
				itemset("긴장", Material.TORCH, 0, 1, Arrays.asList("수치를 즉시 최대화 시킵니다"), 9, Boxskillsinv);
				itemset("피커브", Material.NETHERITE_HELMET, 0, 1, Arrays.asList("웅크리기중 방어력이 증가합니다"), 10, Boxskillsinv);
				itemset("승천권", Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 승천권을 사용합니다","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.4*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.046)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(피해량은 붕권 레벨에 비례합니다)"), 11, Boxskillsinv);
				itemset("지열권", Material.POPPED_CHORUS_FRUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 지열권을 사용합니다","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.35*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(피해량은 철산고 레벨에 비례합니다)"), 12, Boxskillsinv);
				itemset("몰입", Material.RAIL, 0, 1, Arrays.asList("재입력시 뎀프시롤을 한번더 사용할수 있습니다"), 13, Boxskillsinv);
				itemset("기회", Material.SHIELD, 0, 1, Arrays.asList("적에게 공격 받아도 반격이 활성화 됩니다"), 14, Boxskillsinv);
				itemset("강심장", Material.GOLDEN_APPLE, 0, 1, Arrays.asList("체력 재생효과가 증가합니다"), 15, Boxskillsinv);
				itemset("인내", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("전체 공격력과 방어력이 증가합니다"), 16, Boxskillsinv);
				itemset("일격권", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","웅크리기 + 아이템던지기", "",ChatColor.BOLD+" X 12.1D"), 17, Boxskillsinv);
				
				itemset("더킹", Material.CHICKEN, 0, 1, Arrays.asList("웅크리기 사용시 반격이 활성화 됩니다"), 19, Boxskillsinv);
				itemset("대지가르기", Material.BLACK_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 대지가르기를 사용합니다","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.54*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(피해량은 붕권 레벨에 비례합니다)"), 20, Boxskillsinv);
				itemset("권풍", Material.IRON_BOOTS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 권풍을 사용합니다","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.35*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(피해량은 철산고 레벨에 비례합니다)"), 21, Boxskillsinv);
				itemset("가속", Material.ENCHANTED_GOLDEN_APPLE, 0, 1, Arrays.asList("재입력시 뎀프시롤을 한번더 사용할수 있습니다"), 22, Boxskillsinv);
				itemset("활력", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("전체 공격력과 방어력이 증가합니다","일격권의 재사용 대기시간이 감소합니다"), 25, Boxskillsinv);
				itemset("철인의 의지", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","달리기 + 아이템던지기", "",ChatColor.BOLD+" X 18.1D"), 26, Boxskillsinv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Boxskillsinv);
			itemset("스킬포인트", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+bsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, Boxskillsinv);
		
			
		}
		else {
			itemset("Adrenaline", Material.LEATHER_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Parrying.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","SwapHand to release FistForce","Adrenaline levels will Increases", "When You're Damaged","Increases Ability When Full Charged (200)", "Master Lv.1"), 0, Boxskillsinv);
			itemset("Weaving", Material.LEATHER_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Weaving.getOrDefault(p.getUniqueId(),0),"","Sneaking","Master LV.1"), 1, Boxskillsinv);
			itemset("Straight", Material.IRON_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Straight.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","Rightclick","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.3*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 2, Boxskillsinv);
			itemset("BodyBlow", Material.GOLDEN_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.BodyBlow.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","Sneaking + Rightclick","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.55*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 3, Boxskillsinv);
			itemset("DempseyRoll", Material.DIAMOND_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","SwapHand + Sneaking","",ChatColor.BOLD+" 8 X "+BigDecimal.valueOf(0.15*(1+bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0)*0.025)).setScale(2, RoundingMode.HALF_EVEN)+"D + "+" "+BigDecimal.valueOf(0.5*(1+bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 4, Boxskillsinv);
			itemset("Counter", Material.CHAINMAIL_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Counter.getOrDefault(p.getUniqueId(),0),"","Activate when you evade enemies attack", "using Parrying or Weaving","",ChatColor.BOLD+" X"+BigDecimal.valueOf(1.4+bsd.Counter.getOrDefault(p.getUniqueId(),0)*0.025).setScale(2, RoundingMode.HALF_EVEN) ,"Master LV.50"), 5, Boxskillsinv);
			itemset("Rest", Material.WRITABLE_BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Rest.getOrDefault(p.getUniqueId(),0),"","Sneaking(hold)","Master LV.1"), 6, Boxskillsinv);
			itemset("Training", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Training.getOrDefault(p.getUniqueId(),0),"","Increases damage",ChatColor.BOLD+" X"+BigDecimal.valueOf(1.25+bsd.Training.getOrDefault(p.getUniqueId(),0)*0.02405).setScale(2, RoundingMode.HALF_EVEN),"","If You're Attacked While Punching or Using Skills", "Parry 70% of the Damage", "Bounce if Projectile"
					,"Increases Wind Resistance"), 7, Boxskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("Intension(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Boxskillsinv);
				itemset("Peek-a-boo(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Boxskillsinv);
				itemset("SkyCrasher(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Boxskillsinv);
				itemset("UnderHook(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Boxskillsinv);
				itemset("Immersion(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Boxskillsinv);
				itemset("Chance(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Boxskillsinv);
				itemset("Strong heart(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 15, Boxskillsinv);
				itemset("Patience(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Boxskillsinv);
				itemset("One Punch(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Boxskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Intension", Material.TORCH, 0, 1, Arrays.asList("Maximize FistForce Instantly"), 9, Boxskillsinv);
				itemset("Peek-a-boo", Material.NETHERITE_HELMET, 0, 1, Arrays.asList("Increases Armor While Sneaking"), 10, Boxskillsinv);
				itemset("SkyCrasher", Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use SkyCrasher When use Once more","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.4*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.046)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By Straight)"), 11, Boxskillsinv);
				itemset("UnderHook", Material.POPPED_CHORUS_FRUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use UnderHook When use Once more","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.35*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By Bodyblow)"), 12, Boxskillsinv);
				itemset("Immersion", Material.RAIL, 0, 1, Arrays.asList("Able to use Once more"), 13, Boxskillsinv);
				itemset("Chance", Material.SHIELD, 0, 1, Arrays.asList("Activate Counter When Damaged by Enimies as well"), 14, Boxskillsinv);
				itemset("Strong heart", Material.GOLDEN_APPLE, 0, 1, Arrays.asList("Amplify Regeneration Effect"), 15, Boxskillsinv);
				itemset("Patience", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor"), 16, Boxskillsinv);
				itemset("One Punch", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Sneaking + ThrowItem", "",ChatColor.BOLD+" X 12.1D"), 17, Boxskillsinv);

				itemset("Ducking(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, Boxskillsinv);
				itemset("EarthQuaker(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, Boxskillsinv);
				itemset("FistStorm(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, Boxskillsinv);
				itemset("Acceleration(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, Boxskillsinv);
				itemset("Stamina(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Boxskillsinv);
				itemset("Will Of Ironman(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Boxskillsinv);
			}
			else {
				itemset("Intension", Material.TORCH, 0, 1, Arrays.asList("Maximize FistForce Instantly"), 9, Boxskillsinv);
				itemset("Peek-a-boo", Material.NETHERITE_HELMET, 0, 1, Arrays.asList("Increases Armor While Sneaking"), 10, Boxskillsinv);
				itemset("SkyCrasher", Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use SkyCrasher When use Once more","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.4*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.046)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By Straight)"), 11, Boxskillsinv);
				itemset("UnderHook", Material.POPPED_CHORUS_FRUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use UnderHook When use Once more","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.35*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By Bodyblow)"), 12, Boxskillsinv);
				itemset("Immersion", Material.RAIL, 0, 1, Arrays.asList("Able to use Once more"), 13, Boxskillsinv);
				itemset("Chance", Material.SHIELD, 0, 1, Arrays.asList("Activate Counter When Damaged by Enimies as well"), 14, Boxskillsinv);
				itemset("Strong heart", Material.GOLDEN_APPLE, 0, 1, Arrays.asList("Amplify Regeneration Effect"), 15, Boxskillsinv);
				itemset("Patience", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor"), 16, Boxskillsinv);
				itemset("One Punch", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Sneaking + ThrowItem", "",ChatColor.BOLD+" X 12.1D"), 17, Boxskillsinv);

				itemset("Ducking", Material.CHICKEN, 0, 1, Arrays.asList("Activate Counter When Sneaking"), 19, Boxskillsinv);
				itemset("EarthQuaker", Material.BLACK_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use EarthQuaker When use Once more","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.54*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By Straight)"), 20, Boxskillsinv);
				itemset("FistStorm", Material.IRON_BOOTS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use FistStorm When use Once more","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.35*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By Bodyblow)"), 21, Boxskillsinv);
				itemset("Acceleration", Material.ENCHANTED_GOLDEN_APPLE, 0, 1, Arrays.asList("Able to use Once more"), 22, Boxskillsinv);
				itemset("Stamina", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decreases One Punch Cooldown"), 25, Boxskillsinv);
				itemset("Will Of Ironman", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Sprinting + ThrowItem", "",ChatColor.BOLD+" X 18.1D"), 26, Boxskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Boxskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+bsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, Boxskillsinv);
		
			
		}
		p.openInventory(Boxskillsinv);
	}


}
