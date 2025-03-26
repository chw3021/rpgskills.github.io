package io.github.chw3021.classes.archer;



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

public class ArchSkillsGui extends SkillsGui{
	
	
	public void Archskillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		ArchSkillsData asd = new ArchSkillsData(ArchSkillsData.loadData(path +"/plugins/RPGskills/ArchSkillsData.data"));
		Inventory skillsInv = Bukkit.createInventory(null, 36, "Archskills");

		
		
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("화살뿌리기", Material.TIPPED_ARROW, 1, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.SpreadingArrows.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 좌클릭","움직이는 방향으로 이동합니다","연속사용시 같은 방향으로 이동합니다","",ChatColor.BOLD+"X 0.65D", "Master LV.1"), 0, skillsInv);
			itemset("회수", Material.TIPPED_ARROW, 2, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.Retrieve.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 손바꾸기","",ChatColor.BOLD+"X 0.5 X (1 + 0.23D X 적에게 박힌 화살수)", "Master LV.1"), 1, skillsInv);
			itemset("속사", Material.TIPPED_ARROW, 3, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.RapidFire.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 웅크리기 + 손바꾸기","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.034*(1+asd.RapidFire.getOrDefault(p.getUniqueId(),0)*0.01)).setScale(3, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 2, skillsInv);
			itemset("다중사격", Material.TIPPED_ARROW, 4, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.MultiShot.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 웅크리기 + 발사","",ChatColor.BOLD+"25 X "+BigDecimal.valueOf(0.01*(1+asd.MultiShot.getOrDefault(p.getUniqueId(),0)*0.0065)).setScale(4, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 3, skillsInv);
			itemset("훅앤샷", Material.TIPPED_ARROW, 5, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.HookAndShot.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 웅크리기 + 근접공격", "대상을 2초동안 붙잡습니다", "발사하거나 2초가 지나면","대상을 놓아줍니다", "Master LV.1"), 4, skillsInv);
			itemset("트리플샷", Material.BOOK, 6, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.TripleShot.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 한번에 화살을 3개를 쏩니다","",ChatColor.BOLD+"첫번째 화살: + (X "+BigDecimal.valueOf(0.05* (1+asd.TripleShot.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"+")",ChatColor.BOLD+"나머지화살들: 첫번째화살X0.62 + (X 0.015)", "Master LV.50"), 5, skillsInv);
			itemset("궁술", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.Archery.getOrDefault(p.getUniqueId(),0),"","공격력이 증가합니다",ChatColor.BOLD+"X "+BigDecimal.valueOf(1+asd.Archery.getOrDefault(p.getUniqueId(),0)*0.035).setScale(2, RoundingMode.HALF_EVEN),"","활시위를 당길때 1초동안 피해를 버팁니다",String.format(ChatColor.BOLD+"-%.2f", BigDecimal.valueOf(1-0.68 + Proficiency.getpro(p)*0.2)) ), 7, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("민첩함(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("상처(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("나선화살(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("흩뿌리기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("꽂기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("쿼드라샷(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("전투술(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("광란의화살(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("민첩함", Material.BOW, 0, 1, Arrays.asList("사용횟수가 증가합니다", "화살로 적을 맞추면 사용횟수가 1회 충전됩니다"), 9, skillsInv);
				itemset("상처", Material.ARROW, 0, 1, Arrays.asList("한번더 공격하고 적을 잠시 제압합니다"), 10, skillsInv);
				itemset("나선화살", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 재입력시 나선화살을 쏩니다", "(피해량은 속사 레벨에 비례합니다)","",ChatColor.BOLD+"10 X "+demical(0.34*(1+asd.RapidFire.getOrDefault(p.getUniqueId(),0)*0.2))+"D"), 11, skillsInv);
				itemset("흩뿌리기", Material.TARGET, 0, 1, Arrays.asList("발사횟수가 3번으로 증가합니다"), 12, skillsInv);
				itemset("꽂기", Material.DIRT, 0, 1, Arrays.asList("맞은 적은 잠시 기절합니다"), 13, skillsInv);
				itemset("쿼드라샷", Material.ARROW, 0, 1, Arrays.asList("발사횟수와 연사력이 증가합니다", "데미지가 증가합니다"), 14, skillsInv);
				itemset("전투술", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("데미지가 증가합니다","버티기 시간이 증가합니다","","화살뿌리기 직후 활시위를 당기면","즉시 사격합니다"), 16, skillsInv);
				itemset("광란의화살", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 웅크리기 + num4", "",ChatColor.BOLD+"(20 x 30) X 0.028D"), 17, skillsInv);
				
				itemset("약점공격(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("곡사(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("세븐즈샷(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
				itemset("백전불태(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("화살의춤(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("민첩함", Material.BOW, 0, 1, Arrays.asList("사용횟수가 증가합니다", "화살로 적을 맞추면 사용횟수가 1회 충전됩니다"), 9, skillsInv);
				itemset("상처", Material.ARROW, 0, 1, Arrays.asList("한번더 공격하고 적을 잠시 제압합니다"), 10, skillsInv);
				itemset("나선화살", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 재입력시 나선화살을 쏩니다", "(피해량은 속사 레벨에 비례합니다)","",ChatColor.BOLD+"10 X "+demical(0.34*(1+asd.RapidFire.getOrDefault(p.getUniqueId(),0)*0.2))+"D"), 11, skillsInv);
				itemset("흩뿌리기", Material.TARGET, 0, 1, Arrays.asList("발사횟수가 3번으로 증가합니다"), 12, skillsInv);
				itemset("꽂기", Material.DIRT, 0, 1, Arrays.asList("맞은 적은 잠시 기절합니다"), 13, skillsInv);
				itemset("쿼드라샷", Material.ARROW, 0, 1, Arrays.asList("발사횟수와 연사력이 증가합니다", "데미지가 증가합니다"), 14, skillsInv);
				itemset("전투술", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("데미지가 증가합니다","버티기 시간이 증가합니다","","화살뿌리기 직후 활시위를 당기면","즉시 사격합니다"), 16, skillsInv);
				itemset("광란의화살", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 웅크리기 + num4", "",ChatColor.BOLD+"(20 x 30) X 0.028D"), 17, skillsInv);
				
				itemset("약점공격", Material.BOW, 0, 1, Arrays.asList("주변적을 기절시킨 후 한번더 공격합니다"), 18, skillsInv);
				itemset("곡사", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 재입력시 곡사을 쏩니다", "(피해량은 속사 레벨에 비례합니다)","",ChatColor.BOLD+"20 X "+demical(0.28*(1+asd.RapidFire.getOrDefault(p.getUniqueId(),0)*0.16))+"D"), 20, skillsInv);
				itemset("세븐즈샷", Material.BOW, 0, 1, Arrays.asList("한번에 7개의 화살들을 발사합니다"), 23, skillsInv);
				itemset("백전불태", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다","광란의화살 대기시간이 감소합니다","위더의 보호막과 엔더맨도 공격이 가능해집니다"), 25, skillsInv);
				itemset("화살의춤", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 웅크리기 + num5", "",ChatColor.BOLD+"60 X 0.35D"), 26, skillsInv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("스킬포인트", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+asd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, skillsInv);

		}
		else {
			itemset("SpreadingArrows", Material.TIPPED_ARROW, 1, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.SpreadingArrows.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 Leftclick","Direction Depends on Your Movement","Move Same Direction", "If You Use in a row","",ChatColor.BOLD+"X 0.65D", "Master LV.1"), 0, skillsInv);
			itemset("Retrieve", Material.TIPPED_ARROW, 2, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.Retrieve.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 SwapHand","",ChatColor.BOLD+" 0.5 X X (1 + 0.23D X number of arrows in enemy)", "Master LV.1"), 1, skillsInv);
			itemset("RapidFire", Material.TIPPED_ARROW, 3, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.RapidFire.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 Sneaking + SwapHand","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.034*(1+asd.RapidFire.getOrDefault(p.getUniqueId(),0)*0.01)).setScale(3, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 2, skillsInv);
			itemset("MultiShot", Material.TIPPED_ARROW, 4, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.MultiShot.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 Sneaking + Shoot","",ChatColor.BOLD+"25 X "+BigDecimal.valueOf(0.01*(1+asd.MultiShot.getOrDefault(p.getUniqueId(),0)*0.0065)).setScale(4, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 3, skillsInv);
			itemset("HookAndShot", Material.TIPPED_ARROW, 5, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.HookAndShot.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 ","Sneaking + MeleeHit", "Hold a mob for 2secs", "Release the mob when you","shot or after 2 seconds", "Master LV.1"), 4, skillsInv);
			itemset("TripleShot", Material.BOOK, 6, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.TripleShot.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 Shot three arrows at a time","",ChatColor.BOLD+"FirstArrow: + (X "+BigDecimal.valueOf(0.05* (1+asd.TripleShot.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"+")",ChatColor.BOLD+"NextArrows: FirstX0.62 + (X 0.015D)", "Master LV.50"), 5, skillsInv);
			itemset("Archery", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+asd.Archery.getOrDefault(p.getUniqueId(),0),"","Increases your arrow and melee damage",ChatColor.BOLD+"X "+BigDecimal.valueOf(1+asd.Archery.getOrDefault(p.getUniqueId(),0)*0.035).setScale(2, RoundingMode.HALF_EVEN),"","Indure All Damage for 1s since Charging",ChatColor.BOLD+"-"+BigDecimal.valueOf(1-0.68 + Proficiency.getpro(p)*0.2)), 7, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("Agility(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("Wound(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("SpinShots(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("ScatterShot(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("Pitch(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("QuadraShot(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("Combat(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("CrazyArrows(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Agility", Material.BOW, 0, 1, Arrays.asList("Increases Use Times", "Charge One Time When You Hit Enemy With Arrow"), 9, skillsInv);
				itemset("Wound", Material.ARROW, 0, 1, Arrays.asList("Attack Once More & Hold Enemy Shortly"), 10, skillsInv);
				itemset("SpinShots", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 Use SpinShots When Use Once More","(Damage Affected By RapidFire)","",ChatColor.BOLD+"10 X "+demical(0.34*(1+asd.RapidFire.getOrDefault(p.getUniqueId(),0)*0.2))+"D"), 11, skillsInv);
				itemset("ScatterShot", Material.TARGET, 0, 1, Arrays.asList("Shoot Three Times"), 12, skillsInv);
				itemset("Pitch", Material.DIRT, 0, 1, Arrays.asList("Pitchs Hit Enemy"), 13, skillsInv);
				itemset("QuadraShot", Material.ARROW, 0, 1, Arrays.asList("Increases Shoot Time & Rate", "Increases Damage"), 14, skillsInv);
				itemset("Combat", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage","Increases Indure Times","","Able to Shot Instantly","After Use SpreadingArrows"), 16, skillsInv);
				itemset("CrazyArrows", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 Sneaking + num4", "Ult's damage is only affected by your explevels.","",ChatColor.BOLD+"(20 x 30) X 0.028D"), 17, skillsInv);
				
				itemset("Blindside(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("ArcShot(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("7Shots(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
				itemset("Advanced Tactics(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("Arrow Arts(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("Agility", Material.BOW, 0, 1, Arrays.asList("Increases Use Times", "Charge One Time When You Hit Enemy With Arrow"), 9, skillsInv);
				itemset("Wound", Material.ARROW, 0, 1, Arrays.asList("Attack Once More & Hold Enemy Shortly"), 10, skillsInv);
				itemset("SpinShots", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 Use SpinShots When Use Once More","(Damage Affected By RapidFire)","",ChatColor.BOLD+"10 X "+demical(0.34*(1+asd.RapidFire.getOrDefault(p.getUniqueId(),0)*0.2))+"D"), 11, skillsInv);
				itemset("ScatterShot", Material.TARGET, 0, 1, Arrays.asList("Shoot Three Times"), 12, skillsInv);
				itemset("Pitch", Material.DIRT, 0, 1, Arrays.asList("Pitchs Hit Enemy"), 13, skillsInv);
				itemset("QuadraShot", Material.ARROW, 0, 1, Arrays.asList("Increases Shoot Time", "Increases Damage"), 14, skillsInv);
				itemset("Combat", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage","Increases Indure Times","","Able to Shot Instantly","After Use SpreadingArrows"), 16, skillsInv);
				itemset("CrazyArrows", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 Sneaking + num4", "Ult's damage is only affected by your explevels.","",ChatColor.BOLD+"(20 x 30) X 0.028D"), 17, skillsInv);
				
				itemset("Blindside ", Material.BOW, 0, 1, Arrays.asList("Stuns Hit Enemies And Shot Once More"), 18, skillsInv);
				itemset("ArcShot", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 Use ArcShot When Use Once More","(Damage Affected By RapidFire)","",ChatColor.BOLD+"20 X "+demical(0.28*(1+asd.RapidFire.getOrDefault(p.getUniqueId(),0)*0.16))+"D"), 20, skillsInv);
				itemset("7Shots", Material.BOW, 0, 1, Arrays.asList("Shoot Seven Times"), 23, skillsInv);
				itemset("Advanced Tactics", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decreases CrazyArrows Cooldown","Able To Hit Enderman & Wither's Barrier"), 25, skillsInv);
				itemset("Arrow Arts", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 Sneaking + num5", "Ult's damage is only affected by your explevels.","",ChatColor.BOLD+"60 X 0.35D"), 26, skillsInv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("SkillPoints", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+asd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, skillsInv);

		}
		
		
		Obtained.itemset(p, skillsInv);
		p.openInventory(skillsInv);
	}


}