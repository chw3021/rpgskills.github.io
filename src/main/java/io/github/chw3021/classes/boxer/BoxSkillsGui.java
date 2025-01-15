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
		Inventory skillsInv = Bukkit.createInventory(null, 36, "BoxSkills");
		
		

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("아드레날린", Material.LEATHER_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Parrying.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [바람]","🖮🖰 손바꾸기 사용시 권기를 방출합니다",ChatColor.BOLD+"충전량 X 1.5","피해를 입을때마다 수치가 상승합니다","수치가 최대충전 (200)이되면","공격력, 방어력, 속도가 증가합니다", "Master Lv.1"), 0, skillsInv);
			itemset("질풍권", Material.LEATHER_HELMET, 0, 1, Arrays.asList("[바람]","🖮🖰 우클릭"),bsd.Jab.getOrDefault(p.getUniqueId(),0), 1,0.4,0.0431,50,1, skillsInv);
			itemset("붕권", Material.IRON_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Straight.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [바람]","🖮🖰 좌클릭","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.3*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 2, skillsInv);
			itemset("철산고", Material.GOLDEN_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.BodyBlow.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [바람]","🖮🖰 웅크리기 + 우클릭","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.55*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 3, skillsInv);
			itemset("뎀프시롤", Material.DIAMOND_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [바람]","🖮🖰 손바꾸기 + 웅크리기","위빙이 활성화됩니다","",ChatColor.BOLD+" 8 X "+BigDecimal.valueOf(0.15*(1+bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0)*0.025)).setScale(2, RoundingMode.HALF_EVEN)+"D + "+" "+BigDecimal.valueOf(0.5*(1+bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 4, skillsInv);
			itemset("반격", Material.CHAINMAIL_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Counter.getOrDefault(p.getUniqueId(),0),"","적의 공격을 패리나 위빙으로","회피성공시 발동됩니다","",ChatColor.BOLD+" X"+BigDecimal.valueOf(1.4+bsd.Counter.getOrDefault(p.getUniqueId(),0)*0.025).setScale(2, RoundingMode.HALF_EVEN),"Master LV.50"), 5, skillsInv);
			itemset("심호흡", Material.WRITABLE_BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Rest.getOrDefault(p.getUniqueId(),0),"","🖮🖰 웅크리기(유지)","Master LV.1"), 6, skillsInv);
			itemset("단련", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Training.getOrDefault(p.getUniqueId(),0),"","공격력이 증가합니다",ChatColor.BOLD+" X"+BigDecimal.valueOf(1.25+bsd.Training.getOrDefault(p.getUniqueId(),0)*0.02405).setScale(2, RoundingMode.HALF_EVEN),
					"",ChatColor.BLUE +"🖮🖰 [패리]팔을 휘두르는 도중 피격시", ChatColor.BLUE +"공격을 흘려 피해를 70% 감소시킵니다",ChatColor.BLUE +"(투사체는 튕겨낼수 있습니다)",
					"",ChatColor.RED +"🖮🖰 [위빙]웅크리기 도중 시선이 적으로 부터", ChatColor.RED +"사선 아래방향을 향할시 적의 공격을 회피합니다",ChatColor.RED +"(대기시간 0.5초)"
					,"","바람 저항력이 증가합니다"), 7, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("긴장(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("질풍쇄도(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("승천권(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("반달권(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("몰입(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("기회(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("강심장(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 15, skillsInv);
				itemset("인내(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("일격권(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("긴장", Material.TORCH, 0, 1, Arrays.asList("수치를 즉시 최대화 시킵니다"), 9, skillsInv);
				itemset("질풍쇄도", Material.NETHERITE_HELMET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [바람]","🖮🖰 재입력시 질풍쇄도를 사용합니다","",ChatColor.BOLD+"4 X"+BigDecimal.valueOf(0.33*(1+bsd.Jab.getOrDefault(p.getUniqueId(),0)*0.033)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(피해량은 질풍권 레벨에 비례합니다)"), 10, skillsInv);
				itemset("승천권", Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [바람]","🖮🖰 재입력시 승천권을 사용합니다","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.4*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.046)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(피해량은 붕권 레벨에 비례합니다)"), 11, skillsInv);
				itemset("반달권", Material.POPPED_CHORUS_FRUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [바람]","🖮🖰 재입력시 반달권을 사용합니다","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.5*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(피해량은 철산고 레벨에 비례합니다)"), 12, skillsInv);
				itemset("몰입", Material.RAIL, 0, 1, Arrays.asList("🖮🖰 재입력시 뎀프시롤을 한번더 사용할수 있습니다"), 13, skillsInv);
				itemset("기회", Material.SHIELD, 0, 1, Arrays.asList("적에게 공격 받아도 반격이 활성화 됩니다"), 14, skillsInv);
				itemset("강심장", Material.GOLDEN_APPLE, 0, 1, Arrays.asList("체력 재생효과가 증가합니다"), 15, skillsInv);
				itemset("인내", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("전체 공격력과 방어력이 증가합니다"), 16, skillsInv);
				itemset("일격권", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [바람]","🖮🖰 웅크리기 + num4", "",ChatColor.BOLD+" X 17.1D"), 17, skillsInv);

				itemset("촌경(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
				itemset("대지가르기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("권풍(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
				itemset("폭권(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("더킹(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
				itemset("활력(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("철인의 의지(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("긴장", Material.TORCH, 0, 1, Arrays.asList("수치를 즉시 최대화 시킵니다"), 9, skillsInv);
				itemset("질풍쇄도", Material.NETHERITE_HELMET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [바람]","🖮🖰 재입력시 질풍쇄도를 사용합니다","",ChatColor.BOLD+"4 X"+BigDecimal.valueOf(0.33*(1+bsd.Jab.getOrDefault(p.getUniqueId(),0)*0.033)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(피해량은 질풍권 레벨에 비례합니다)"), 10, skillsInv);
				itemset("승천권", Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [바람]","🖮🖰 재입력시 승천권을 사용합니다","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.4*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.046)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(피해량은 붕권 레벨에 비례합니다)"), 11, skillsInv);
				itemset("반달권", Material.POPPED_CHORUS_FRUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [바람]","🖮🖰 재입력시 반달권을 사용합니다","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.5*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(피해량은 철산고 레벨에 비례합니다)"), 12, skillsInv);
				itemset("몰입", Material.RAIL, 0, 1, Arrays.asList("🖮🖰 재입력시 뎀프시롤을 한번더 사용할수 있습니다"), 13, skillsInv);
				itemset("기회", Material.SHIELD, 0, 1, Arrays.asList("적에게 공격 받아도 반격이 활성화 됩니다"), 14, skillsInv);
				itemset("강심장", Material.GOLDEN_APPLE, 0, 1, Arrays.asList("체력 재생효과가 증가합니다"), 15, skillsInv);
				itemset("인내", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("전체 공격력과 방어력이 증가합니다"), 16, skillsInv);
				itemset("일격권", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [바람]","🖮🖰 웅크리기 + num4", "",ChatColor.BOLD+" X 17.1D"), 17, skillsInv);
				
				itemset("촌경", Material.BLACK_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [바람]","🖮🖰 재입력시 촌경를 사용합니다","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.91*(1+bsd.Jab.getOrDefault(p.getUniqueId(),0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(피해량은 질풍권 레벨에 비례합니다)"), 19, skillsInv);
				itemset("대지가르기", Material.BLACK_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [바람]","🖮🖰 재입력시 대지가르기를 사용합니다","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.54*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(피해량은 붕권 레벨에 비례합니다)"), 20, skillsInv);
				itemset("권풍", Material.TNT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [바람]","🖮🖰 재입력시 권풍을 사용합니다","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.35*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(피해량은 철산고 레벨에 비례합니다)"), 21, skillsInv);
				itemset("폭권", Material.ENCHANTED_GOLDEN_APPLE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [바람]","🖮🖰 뎀프시롤 이후 재입력시 폭권을 사용합니다","",ChatColor.BOLD+"1 X "+BigDecimal.valueOf(1.5*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.09)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(피해량은 뎀프시롤 레벨에 비례합니다)"), 22, skillsInv);
				itemset("더킹", Material.CHICKEN, 0, 1, Arrays.asList("🖮🖰 웅크리기 사용시 반격이 활성화 됩니다"), 23, skillsInv);
				itemset("활력", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("전체 공격력과 방어력이 증가합니다","일격권의 재사용 대기시간이 감소합니다"), 25, skillsInv);
				itemset("철인의 의지", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [바람]","🖮🖰 웅크리기 + num5", "",ChatColor.BOLD+"40 X 0.16 + 3 X 2.1D + 17.5D"), 26, skillsInv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("스킬포인트", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+bsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, skillsInv);
		
			
		}
		else {
			itemset("Adrenaline", Material.LEATHER_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Parrying.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Wind]","🖮🖰 SwapHand to release FistForce","Adrenaline levels will Increases", "When You're Damaged","Increases Ability When Full Charged (200)", "Master Lv.1"), 0, skillsInv);
			itemset("FlickerJab", Material.LEATHER_HELMET, 0, 1, Arrays.asList("[Wind]","🖮🖰 RightClick"),bsd.Jab.getOrDefault(p.getUniqueId(),0), 1,0.4,0.0431,50,1, skillsInv);
			itemset("Straight", Material.IRON_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Straight.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Wind]","🖮🖰 LeftClick","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.3*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 2, skillsInv);
			itemset("BodyBlow", Material.GOLDEN_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.BodyBlow.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Wind]","🖮🖰 Sneaking + Rightclick","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.55*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 3, skillsInv);
			itemset("DempseyRoll", Material.DIAMOND_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Wind]","🖮🖰 SwapHand + Sneaking","Activate [Weaving]","🖮🖰 ",ChatColor.BOLD+" 8 X "+BigDecimal.valueOf(0.15*(1+bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0)*0.025)).setScale(2, RoundingMode.HALF_EVEN)+"D + "+" "+BigDecimal.valueOf(0.5*(1+bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 4, skillsInv);
			itemset("Counter", Material.CHAINMAIL_HELMET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Counter.getOrDefault(p.getUniqueId(),0),"","Activate when you evade enemies attack", "using Parrying or Weaving","",ChatColor.BOLD+" X"+BigDecimal.valueOf(1.4+bsd.Counter.getOrDefault(p.getUniqueId(),0)*0.025).setScale(2, RoundingMode.HALF_EVEN) ,"Master LV.50"), 5, skillsInv);
			itemset("Rest", Material.WRITABLE_BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Rest.getOrDefault(p.getUniqueId(),0),"","Sneaking(hold)","Master LV.1"), 6, skillsInv);
			itemset("Training", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+bsd.Training.getOrDefault(p.getUniqueId(),0),"","Increases damage",ChatColor.BOLD+" X"+BigDecimal.valueOf(1.25+bsd.Training.getOrDefault(p.getUniqueId(),0)*0.02405).setScale(2, RoundingMode.HALF_EVEN),"",
					ChatColor.BLUE +"🖮🖰 [Parry]If You're Attacked While Punching or Using Skills", ChatColor.BLUE +"Parry 70% of the Damage", ChatColor.BLUE +"Bounce if Projectile","",
					ChatColor.RED +"🖮🖰 [Weaving]If You're Attacked While Sneaking", ChatColor.RED +"And your eyes are diagonally", ChatColor.RED +"Downward from the enemy", ChatColor.RED +"Avoid enemy attack(Cooldown 0.5s)",""
					,"Increases Wind Resistance"), 7, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("Intension(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("JabRush(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("SkyCrasher(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("MomentumSmash(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("Immersion(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("Chance(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("Strong heart(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 15, skillsInv);
				itemset("Patience(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("One Punch(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Intension", Material.TORCH, 0, 1, Arrays.asList("Maximize FistForce Instantly"), 9, skillsInv);
				itemset("JabRush", Material.NETHERITE_HELMET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Wind]","🖮🖰 Use JabRush When use Once more","",ChatColor.BOLD+"4 X"+BigDecimal.valueOf(0.33*(1+bsd.Jab.getOrDefault(p.getUniqueId(),0)*0.033)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By FlickerJab)"), 10, skillsInv);
				itemset("SkyCrasher", Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Wind]","🖮🖰 Use SkyCrasher When use Once more","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.4*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.046)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By Straight)"), 11, skillsInv);
				itemset("MomentumSmash", Material.POPPED_CHORUS_FRUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Wind]","🖮🖰 Use MomentumSmash When use Once more","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.5*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By Bodyblow)"), 12, skillsInv);
				itemset("Immersion", Material.RAIL, 0, 1, Arrays.asList("🖮🖰 Able to use Once more"), 13, skillsInv);
				itemset("Chance", Material.SHIELD, 0, 1, Arrays.asList("Activate Counter When Damaged by Enimies as well"), 14, skillsInv);
				itemset("Strong heart", Material.GOLDEN_APPLE, 0, 1, Arrays.asList("Amplify Regeneration Effect"), 15, skillsInv);
				itemset("Patience", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor"), 16, skillsInv);
				itemset("One Punch", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Wind]","🖮🖰 Sneaking + num4", "",ChatColor.BOLD+" X 17.1D"), 17, skillsInv);

				itemset("OneInchPunch(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("EarthQuaker(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("FistStorm(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
				itemset("ExplodingFist(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("Ducking(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
				itemset("Stamina(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("Will Of Ironman(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("Intension", Material.TORCH, 0, 1, Arrays.asList("Maximize FistForce Instantly"), 9, skillsInv);
				itemset("JabRush", Material.NETHERITE_HELMET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Wind]","🖮🖰 Use JabRush When use Once more","",ChatColor.BOLD+"4 X"+BigDecimal.valueOf(0.33*(1+bsd.Jab.getOrDefault(p.getUniqueId(),0)*0.033)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By FlickerJab)"), 10, skillsInv);
				itemset("SkyCrasher", Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Wind]","🖮🖰 Use SkyCrasher When use Once more","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.4*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.046)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By Straight)"), 11, skillsInv);
				itemset("MomentumSmash", Material.POPPED_CHORUS_FRUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Wind]","🖮🖰 Use MomentumSmash When use Once more","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.5*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By Bodyblow)"), 12, skillsInv);
				itemset("Immersion", Material.RAIL, 0, 1, Arrays.asList("🖮🖰 Able to use Once more"), 13, skillsInv);
				itemset("Chance", Material.SHIELD, 0, 1, Arrays.asList("Activate Counter When Damaged by Enimies as well"), 14, skillsInv);
				itemset("Strong heart", Material.GOLDEN_APPLE, 0, 1, Arrays.asList("Amplify Regeneration Effect"), 15, skillsInv);
				itemset("Patience", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor"), 16, skillsInv);
				itemset("One Punch", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Wind]","🖮🖰 Sneaking + num4", "",ChatColor.BOLD+" X 17.1D"), 17, skillsInv);

				itemset("OneInchPunch", Material.BLACK_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Wind]","🖮🖰 Use OneInchPunch When use Once more","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.91*(1+bsd.Jab.getOrDefault(p.getUniqueId(),0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By FlickerJab)"), 19, skillsInv);
				itemset("EarthQuaker", Material.BLACK_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Wind]","🖮🖰 Use EarthQuaker When use Once more","",ChatColor.BOLD+" X"+BigDecimal.valueOf(0.54*(1+bsd.Straight.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By Straight)"), 20, skillsInv);
				itemset("FistStorm", Material.IRON_BOOTS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Wind]","🖮🖰 Use FistStorm When use Once more","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.35*(1+ bsd.BodyBlow.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By Bodyblow)"), 21, skillsInv);
				itemset("ExplodingFist", Material.ENCHANTED_GOLDEN_APPLE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Wind]","🖮🖰 Use ExplodingFist When use Once more After DempseyRoll","",ChatColor.BOLD+"1 X "+BigDecimal.valueOf(1.5*(1+ bsd.DempseyRoll.getOrDefault(p.getUniqueId(),0)*0.09)).setScale(2, RoundingMode.HALF_EVEN)+"D", "(Damage Affected By DempseyRoll)"), 22, skillsInv);
				itemset("Ducking", Material.CHICKEN, 0, 1, Arrays.asList("🖮🖰 Activate Counter When Sneaking"), 23, skillsInv);
				itemset("Stamina", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decreases One Punch Cooldown"), 25, skillsInv);
				itemset("Will Of Ironman", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Wind]","🖮🖰 Sneaking + num5", "",ChatColor.BOLD+"40 X 0.16 + 3 X 2.1D + 17.5D"), 26, skillsInv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("SkillPoints", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+bsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, skillsInv);
		
			
		}
		Obtained.itemset(p, skillsInv);
		p.openInventory(skillsInv);
	}


}