package io.github.chw3021.classes.hunter;



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

public class HunSkillsGui extends SkillsGui{
	

	
	public void Hunskillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		HunSkillsData hsd = new HunSkillsData(HunSkillsData.loadData(path +"/plugins/RPGskills/HunSkillsData.data"));
		Inventory Hunskillsinv = Bukkit.createInventory(null, 54, "Hunskills");
		Obtained.itemset(p, Hunskillsinv);
		final int at = hsd.Atrocity.getOrDefault(p.getUniqueId(),0);

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("암습", Material.BOOK, 0, 1, Arrays.asList("적의 후방을 공격하면 50%추가 피해를 줍니다", "경험치레벨이 높을수록 판정이 좋아집니다"),hsd.Backattack.getOrDefault(p.getUniqueId(), 0),1,1,0.5,1, 0, Hunskillsinv);
			itemset("그물투척", Material.BOOK, 0, 1, Arrays.asList("웅크리기 + 우클릭"),hsd.Webthrow.getOrDefault(p.getUniqueId(), 0),1,0,0.5,1, 1, Hunskillsinv);
			itemset("회피", Material.BOOK, 0, 1, Arrays.asList("손바꾸기","낙하데미지가 감소합니다"),hsd.Dodge.getOrDefault(p.getUniqueId(), 0),1,0,0.5,1, 2, Hunskillsinv);
			itemset("사냥", Material.BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","우클릭", "적에게 피해를 주면 비활성화됩니다", "적 저치시 대기시간이 2초 감소합니다"),hsd.HuntingStart.getOrDefault(p.getUniqueId(), 0),1,1.0,0.055,50, 3, Hunskillsinv);
			itemset("참격", Material.BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","웅크리기 + 공격","적을 0.5초동안 기절시킵니다"),hsd.Daze.getOrDefault(p.getUniqueId(), 0),1,0.8,0.035,50, 4, Hunskillsinv);
			itemset("두개골분쇄", Material.BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","점프 + 공격", "두개골 분쇄를 제외한", "피해량이 50%증가합니다"),hsd.SkullCrusher.getOrDefault(p.getUniqueId(), 0),1,1.1,0.06,50, 5, Hunskillsinv);
			itemset("등반", Material.BOOK, 0, 1, Arrays.asList("웅크리기 + 손바꾸기 로 활성화/비활성화"),hsd.Climb.getOrDefault(p.getUniqueId(), 0),1,0,0.5,1, 6, Hunskillsinv);
			itemset("극악무도", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+at,"","모든 기술은 단한번의 공격을 강화합니다","최대충전 상태가 아닐때는", "절반의 피해만 입힙니다","허기에 면역이 됩니다", "최대충전 상태로 공격시", "적의 최대체력의"+ Math.round(5+0.02*at)  +"% 에 해당하는 추가피해를 줍니다","공격력이 증가합니다",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.15*(1+at*0.0576)).setScale(2, RoundingMode.HALF_EVEN)), 7, Hunskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("그물추가(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, Hunskillsinv);
				itemset("기민함(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, Hunskillsinv);
				itemset("열광(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, Hunskillsinv);
				itemset("충격(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, Hunskillsinv);
				itemset("공포(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, Hunskillsinv);
				itemset("도움닫기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 15, Hunskillsinv);
				itemset("무결(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 16, Hunskillsinv);
				itemset("갈망(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, Hunskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("그물추가", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("그물의 개수가 증가합니다"), 10, Hunskillsinv);
				itemset("기민함", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("적 처치시 대기시간이 초기화 됩니다"), 11, Hunskillsinv);
				itemset("열광", Material.ACTIVATOR_RAIL, 0, 1, Arrays.asList("이제 적에게 공격하는것 만으로도 대기시간이 감소합니다"), 12, Hunskillsinv);
				itemset("충격", Material.CREEPER_HEAD, 0, 1, Arrays.asList("주변 적들도 참격시킵니다"), 13, Hunskillsinv);
				itemset("공포", Material.SKELETON_SKULL, 0, 1, Arrays.asList("적 처치시 주변적들이 참격합니다"), 14, Hunskillsinv);
				itemset("도움닫기", Material.CHAINMAIL_LEGGINGS, 0, 1, Arrays.asList("웅크리기 유지시 점프력이 충전됩니다","최대 10"), 15, Hunskillsinv);
				itemset("무결", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다"), 16, Hunskillsinv);
				itemset("갈망", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("웅크리기 + 아이템던지기", "참격과 두개골분쇄의 대기시간이 초기화됩니다","",ChatColor.BOLD+"X 27.1D"), 17, Hunskillsinv);

				itemset("그물회수(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 19, Hunskillsinv);
				itemset("자세(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 20, Hunskillsinv);
				itemset("무한(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 21, Hunskillsinv);
				itemset("즉결처형(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 22, Hunskillsinv);
				itemset("생존법(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 24, Hunskillsinv);
				itemset("산전수전(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 25, Hunskillsinv);
				itemset("집행(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, Hunskillsinv);
			}
			else {
				itemset("그물추가", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("그물의 개수가 증가합니다"), 10, Hunskillsinv);
				itemset("기민함", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("적 처치시 대기시간이 초기화 됩니다"), 11, Hunskillsinv);
				itemset("열광", Material.ACTIVATOR_RAIL, 0, 1, Arrays.asList("이제 적에게 공격하는것 만으로도 대기시간이 감소합니다"), 12, Hunskillsinv);
				itemset("충격", Material.CREEPER_HEAD, 0, 1, Arrays.asList("주변 적들도 참격시킵니다"), 13, Hunskillsinv);
				itemset("공포", Material.SKELETON_SKULL, 0, 1, Arrays.asList("적 처치시 주변적들이 참격합니다"), 14, Hunskillsinv);
				itemset("도움닫기", Material.CHAINMAIL_LEGGINGS, 0, 1, Arrays.asList("웅크리기 유지시 점프력이 충전됩니다","최대 10"), 15, Hunskillsinv);
				itemset("무결", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다"), 16, Hunskillsinv);
				itemset("갈망", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("웅크리기 + 아이템던지기", "참격과 두개골분쇄의 대기시간이 초기화됩니다","",ChatColor.BOLD+"X 27.1D"), 17, Hunskillsinv);

				itemset("그물회수", Material.FERMENTED_SPIDER_EYE, 0, 1, Arrays.asList("재사용시 그물에 맞은 적들을 끌어옵니다"), 19, Hunskillsinv);
				itemset("자세", Material.NETHERITE_CHESTPLATE, 0, 1, Arrays.asList("회피이후 다음 1회의 공격이 강화됩니다"), 20, Hunskillsinv);
				itemset("무한", Material.NETHERITE_AXE, 0, 1, Arrays.asList("사냥이 활성화/비활성화 기술로 변경됩니다", "적을 공격하면 대기시간이 적용됩니다"), 21, Hunskillsinv);
				itemset("즉결처형", Material.NETHERITE_AXE, 0, 1, Arrays.asList("적 처치시 사냥, 참격, 두개골분쇄의", "재사용대기시간이 초기화 됩니다", "(사냥은 자동으로 활성화됩니다)"), 22, Hunskillsinv);
				itemset("생존법", Material.GOLDEN_CARROT, 0, 1, Arrays.asList("모든 해로운 효과에 면역이 됩니다"), 24, Hunskillsinv);
				itemset("산전수전", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다","갈망의 대기시간이 감소합니다","기습이 항상 발동됩니다"), 25, Hunskillsinv);
				itemset("집행", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","달리기 + 아이템던지기", "참격과 두개골분쇄의 대기시간이 초기화됩니다","",ChatColor.BOLD+"X 33.2D"), 26, Hunskillsinv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Hunskillsinv);
			itemset("스킬포인트", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+hsd.SkillPoints.getOrDefault(p.getUniqueId(), 0),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, Hunskillsinv);
		
		}
		else {
			itemset("Backattack", Material.BOOK, 0, 1, Arrays.asList("+50% Damage when you hit enemy's back", "Higher Explevel gets Better hit detection"),hsd.Backattack.getOrDefault(p.getUniqueId(), 0),1,1,0.5,1, 0, Hunskillsinv);
			itemset("Webthrowing", Material.BOOK, 0, 1, Arrays.asList("Sneaking + RightClick"),hsd.Webthrow.getOrDefault(p.getUniqueId(), 0),1,0,0.5,1, 1, Hunskillsinv);
			itemset("Dodge", Material.BOOK, 0, 1, Arrays.asList("SwapHand","Decreases Falling Damage"),hsd.Dodge.getOrDefault(p.getUniqueId(), 0),1,0,0.5,1, 2, Hunskillsinv);
			itemset("Hunting", Material.BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Rightclick", "Disabled when you damage to enemy", "Reduce cooldown 2s if you kill enemy"),hsd.HuntingStart.getOrDefault(p.getUniqueId(), 0),1,1.0,0.055,50, 3, Hunskillsinv);
			itemset("Daze", Material.BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Sneaking + Hit","Stuns Enemy for 0.5s"),hsd.Daze.getOrDefault(p.getUniqueId(), 0),1,0.8,0.035,50, 4, Hunskillsinv);
			itemset("SkullCrusher", Material.BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Jump + Hit", "Increases Damage 50%","Excepting for SkullCrusher's"),hsd.SkullCrusher.getOrDefault(p.getUniqueId(), 0),1,1.1,0.06,50, 5, Hunskillsinv);
			itemset("Climbing", Material.BOOK, 0, 1, Arrays.asList("Sneaking + SwapHand to on/off"),hsd.Climb.getOrDefault(p.getUniqueId(), 0),1,0,0.5,1, 6, Hunskillsinv);
			itemset("Atrocity", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+at,"","All skills inhance a single attack","Damage half when not fully charged","Immune to Hunger", "When your attack is fully charged", Math.round(5+0.02*hsd.Atrocity.getOrDefault(p.getUniqueId(), 0))  +"% of Enemy's MaxHealth" ,"Increase Power",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.15*(1+at*0.0576)).setScale(2, RoundingMode.HALF_EVEN)), 7, Hunskillsinv);

			if(Proficiency.getpro(p)<1) {
				itemset("ExtraWebs(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Hunskillsinv);
				itemset("Agility(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Hunskillsinv);
				itemset("Infatuation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Hunskillsinv);
				itemset("Impact(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Hunskillsinv);
				itemset("Fear(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Hunskillsinv);
				itemset("SuperJump(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 15, Hunskillsinv);
				itemset("Flawless(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Hunskillsinv);
				itemset("Rage(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Hunskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("ExtraWebs", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("Increases Webs"), 10, Hunskillsinv);
				itemset("Agility", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("Resets Cooldown When you Kill Enemy"), 11, Hunskillsinv);
				itemset("Infatuation", Material.ACTIVATOR_RAIL, 0, 1, Arrays.asList("Just hitting without killing will reduce Cooldown"), 12, Hunskillsinv);
				itemset("Impact", Material.CREEPER_HEAD, 0, 1, Arrays.asList("Stuns Near By Enemies"), 13, Hunskillsinv);
				itemset("Fear", Material.SKELETON_SKULL, 0, 1, Arrays.asList("Stuns Near By Enemies When Enemy is dead"), 14, Hunskillsinv);
				itemset("SuperJump", Material.CHAINMAIL_LEGGINGS, 0, 1, Arrays.asList("Charge SuperJump When you Hold Sneaking","While Climbing is On","Max 10"), 15, Hunskillsinv);
				itemset("Flawless", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16, Hunskillsinv);
				itemset("Rage", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Resets Cooldown of Daze & SkullCrusher","",ChatColor.BOLD+"X 27.1"), 17, Hunskillsinv);
				
				itemset("WebRetrieving(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, Hunskillsinv);
				itemset("Posture(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, Hunskillsinv);
				itemset("Infinite(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, Hunskillsinv);
				itemset("SummaryExecution(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, Hunskillsinv);
				itemset("Survivor(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 24, Hunskillsinv);
				itemset("HardCore(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Hunskillsinv);
				itemset("Execute(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Hunskillsinv);
			}
			else {
				itemset("Agility", Material.CHAINMAIL_BOOTS, 0, 1, Arrays.asList("Resets Cooldown When you Kill Enemy"), 11, Hunskillsinv);
				itemset("Infatuation", Material.ACTIVATOR_RAIL, 0, 1, Arrays.asList("Just hitting without killing will reduce Cooldown"), 12, Hunskillsinv);
				itemset("Impact", Material.CREEPER_HEAD, 0, 1, Arrays.asList("Stuns Near By Enemies"), 13, Hunskillsinv);
				itemset("Fear", Material.SKELETON_SKULL, 0, 1, Arrays.asList("Stuns Near By Enemies When Enemy is dead"), 14, Hunskillsinv);
				itemset("SuperJump", Material.CHAINMAIL_LEGGINGS, 0, 1, Arrays.asList("Charge SuperJump When you Hold Sneaking","While Climbing is On","Max 10"), 15, Hunskillsinv);
				itemset("Flawless", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16, Hunskillsinv);
				itemset("Rage", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Resets Cooldown of Daze & SkullCrusher","",ChatColor.BOLD+"X 27.1"), 17, Hunskillsinv);
				
				itemset("WebRetrieving", Material.FERMENTED_SPIDER_EYE, 0, 1, Arrays.asList("Pull hit enemies when reusing"), 19, Hunskillsinv);
				itemset("Posture", Material.NETHERITE_CHESTPLATE, 0, 1, Arrays.asList("Increases Next Hit Damage After Dodge"), 20, Hunskillsinv);
				itemset("Infinite", Material.NETHERITE_AXE, 0, 1, Arrays.asList("Changes Hunting on/off Skill", "Cooldown will apply when you hit Enemy"), 21, Hunskillsinv);
				itemset("SummaryExecution", Material.NETHERITE_AXE, 0, 1, Arrays.asList("Resets Hunting, Daze, SkullCrasher Cooldown", "When you Success to kill Enemy", "(Hunting will be Automatically Active)"), 22, Hunskillsinv);
				itemset("Survivor", Material.GOLDEN_CARROT, 0, 1, Arrays.asList("Immune to All Negetive Effect"), 24, Hunskillsinv);
				itemset("HardCore", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decreases Rage Cooldown","BackAttack Always Activate"), 25, Hunskillsinv);
				itemset("Execute", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Sprinting + ThrowItem", "Reset Cooldown of Daze & SkullCrusher","",ChatColor.BOLD+"X 33.2D"), 26, Hunskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Hunskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+hsd.SkillPoints.getOrDefault(p.getUniqueId(), 0),"","Click if you want to reset your skill's levels"), 35, Hunskillsinv);
		
		}
		
		p.openInventory(Hunskillsinv);
	}


}
