package io.github.chw3021.classes.paladin;



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

public class PalSkillsGui extends SkillsGui{
	

	public void PalSkillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		PalSkillsData psd = new PalSkillsData(PalSkillsData.loadData(path +"/plugins/RPGskills/PalSkillsData.data"));
		Inventory skillsInv = Bukkit.createInventory(null, 36, "Palskills");
		
		

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("진압", Material.WOODEN_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Thrust.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 막기 + 손바꾸기","",ChatColor.BOLD+" X (0.5D + "+BigDecimal.valueOf(psd.Thrust.getOrDefault(p.getUniqueId(),0)*0.485).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 0, skillsInv);
			itemset("결박", Material.STONE_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Restraint.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 막기 + 웅크리기 + 손바꾸기","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(psd.Restraint.getOrDefault(p.getUniqueId(),0)*0.69).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 1, skillsInv);
			itemset("심판", Material.IRON_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Judgement.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [번개]","🖮🖰 좌클릭 + 웅크리기",
					"파티원들의 공격력이 증가합니다",ChatColor.BOLD+" X "+BigDecimal.valueOf(1+psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.01).setScale(2, RoundingMode.HALF_EVEN),
					"파티원들에게 성급함,힘 효과를 줍니다 ("+(int)Math.floor(psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.1)+"Lv)"
					,"","적에게 번개를 내리꽂습니다",ChatColor.BOLD+" X (0.8D + "+BigDecimal.valueOf(psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 2, skillsInv);
			itemset("징벌", Material.GOLDEN_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Punish.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [번개]","🖮🖰 점프 + 근접공격","",ChatColor.BOLD+" X (0.5D + "+BigDecimal.valueOf(psd.Punish.getOrDefault(p.getUniqueId(),0)*1.5).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 3, skillsInv);
			itemset("격려", Material.DIAMOND_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Encourge.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 손바꾸기", "Master Lv.50"), 4, skillsInv);
			itemset("기도", Material.BOOKSHELF, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Pray.getOrDefault(p.getUniqueId(),0),"","🖮🖰 손바꾸기 + 웅크리기","Master Lv.10"), 5, skillsInv);
			itemset("보호", Material.BOOK, 0, 1, Arrays.asList("방패를 들면 거대 방패를 소환합니다","전방으로부터의 모든 피해를 막습니다","막기도중 파티원들의 받는피해를 절반 감소시킵니다 (패시브)"), 6, skillsInv);
			itemset("신앙", Material.WRITABLE_BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Protection.getOrDefault(p.getUniqueId(),0),"","공격력이 증가합니다","번개 저항력이 증가합니다","",ChatColor.BOLD+" X 1.3 + "+BigDecimal.valueOf(psd.Protection.getOrDefault(p.getUniqueId(),0)*0.4383).setScale(2, RoundingMode.HALF_EVEN)), 7, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("신성한강타(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("광명(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("성수(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("성화(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("영창(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("은총(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("수호(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("최후의 심판(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("신격", Material.SHIELD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 재입력시 신격을 사용합니다","(피해량은 진압 레벨에 비례합니다)","",ChatColor.BOLD+" X (0.75D + "+BigDecimal.valueOf(psd.Thrust.getOrDefault(p.getUniqueId(),0)*0.485).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, skillsInv);
				itemset("광명", Material.DAYLIGHT_DETECTOR, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [번개]","🖮🖰 재입력시 광명을 사용합니다","(피해량은 결박 레벨에 비례합니다)","",ChatColor.BOLD+"8 X (0.12D + "+BigDecimal.valueOf(psd.Restraint.getOrDefault(p.getUniqueId(),0)*0.12).setScale(2, RoundingMode.HALF_EVEN)+")"), 10, skillsInv);
				itemset("성수", Material.MILK_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [번개]","🖮🖰 재입력시 성수를 사용합니다","(피해량은 심판 레벨에 비례합니다)","",ChatColor.BOLD+" X (0.8D + "+BigDecimal.valueOf(psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, skillsInv);
				itemset("성화", Material.DIAMOND_AXE, 0, 1, Arrays.asList("적을 기절시킵니다"), 12, skillsInv);
				itemset("영창", Material.NOTE_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 재입력시 영창을 사용합니다","(피해량은 격려 레벨에 비례합니다)","",ChatColor.BOLD+"3 X (0.5D + "+BigDecimal.valueOf(psd.Encourge.getOrDefault(p.getUniqueId(),0)*0.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 13, skillsInv);
				itemset("축복", Material.ENCHANTING_TABLE, 0, 1, Arrays.asList("파티원들을 무적상태로 만듭니다","(지속시간은 기도 레벨에 비례합니다)","",ChatColor.BOLD+""+BigDecimal.valueOf(psd.Pray.getOrDefault(p.getUniqueId(),0)*0.2).setScale(1, RoundingMode.HALF_EVEN)+"초"), 14, skillsInv);
				itemset("수호", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다"), 16, skillsInv);
				itemset("최후의 심판", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [번개]","🖮🖰 웅크리기 + 아이템던지기","",ChatColor.BOLD+"10 X 1.5D"), 17, skillsInv);

				itemset("응징(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("운명(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
				itemset("그리폰(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("성역(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("은총(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
				itemset("구원(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("참회(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("신격", Material.SHIELD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 재입력시 신격을 사용합니다","(피해량은 진압 레벨에 비례합니다)","",ChatColor.BOLD+" X (0.75D + "+BigDecimal.valueOf(psd.Thrust.getOrDefault(p.getUniqueId(),0)*0.485).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, skillsInv);
				itemset("광명", Material.DAYLIGHT_DETECTOR, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [번개]","🖮🖰 재입력시 광명을 사용합니다","(피해량은 결박 레벨에 비례합니다)","",ChatColor.BOLD+"8 X (0.12D + "+BigDecimal.valueOf(psd.Restraint.getOrDefault(p.getUniqueId(),0)*0.12).setScale(2, RoundingMode.HALF_EVEN)+")"), 10, skillsInv);
				itemset("성수", Material.MILK_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [번개]","🖮🖰 재입력시 성수를 사용합니다","(피해량은 심판 레벨에 비례합니다)","",ChatColor.BOLD+" X (0.8D + "+BigDecimal.valueOf(psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, skillsInv);
				itemset("성화", Material.DIAMOND_AXE, 0, 1, Arrays.asList("적을 기절시킵니다"), 12, skillsInv);
				itemset("영창", Material.NOTE_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 재입력시 영창을 사용합니다","(피해량은 격려 레벨에 비례합니다)","",ChatColor.BOLD+"3 X (0.5D + "+BigDecimal.valueOf(psd.Encourge.getOrDefault(p.getUniqueId(),0)*0.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 13, skillsInv);
				itemset("축복", Material.ENCHANTING_TABLE, 0, 1, Arrays.asList("파티원들을 무적상태로 만듭니다","(지속시간은 기도 레벨에 비례합니다)","",ChatColor.BOLD+""+BigDecimal.valueOf(psd.Pray.getOrDefault(p.getUniqueId(),0)*0.2).setScale(1, RoundingMode.HALF_EVEN)+"초"), 14, skillsInv);
				itemset("수호", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다"), 16, skillsInv);
				itemset("최후의 심판", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [번개]","🖮🖰 웅크리기 + 아이템던지기","",ChatColor.BOLD+"10 X 1.5D"), 17, skillsInv);

				itemset("응징", Material.LIGHTNING_ROD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 재입력시 응징을 사용합니다","(피해량은 진압 레벨에 비례합니다)","",ChatColor.BOLD+"4 X (0.4D + "+BigDecimal.valueOf(psd.Thrust.getOrDefault(p.getUniqueId(),0)*0.455).setScale(2, RoundingMode.HALF_EVEN)+")"), 18, skillsInv);
				itemset("운명", Material.BUBBLE_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [번개]","🖮🖰 재입력시 운명을 사용합니다","(피해량은 결박 레벨에 비례합니다)","",ChatColor.BOLD+" X (1.5D + "+BigDecimal.valueOf(psd.Restraint.getOrDefault(p.getUniqueId(),0)*1.68).setScale(2, RoundingMode.HALF_EVEN)+")"), 19, skillsInv);
				itemset("그리폰", Material.TRIDENT, 0, 1, Arrays.asList("재입력시 그리폰을 소환합니다", "그리폰에서 내리거나 재소환하면", "기존에 있던 그리폰은 사라집니다","", "그리폰이 존재하는 동안 징벌이 강화됩니다","(🖮🖰 [점프+좌클릭]으로 커맨드 변경)","",
						ChatColor.UNDERLINE+"❈ [번개]","🖮🖰 그리폰 탑승도중 점프시 주변적에게 피해를 줍니다","80% 이상 충전시 공중으로 뜁니다", "(피해량은 심판 레벨에 비례합니다)","",ChatColor.BOLD+" X (1.3D + "+BigDecimal.valueOf(psd.Judgement.getOrDefault(p.getUniqueId(),0)*1.3).setScale(2, RoundingMode.HALF_EVEN)+" X 점프충전량)"), 20, skillsInv);
				itemset("성역", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [대지]","🖮🖰 재입력시 신격을 사용합니다","(피해량은 격려 레벨에 비례합니다)","",ChatColor.BOLD+"10 X (0.55D + "+BigDecimal.valueOf(psd.Encourge.getOrDefault(p.getUniqueId(),0)*0.68).setScale(2, RoundingMode.HALF_EVEN)+")"), 22, skillsInv);
				itemset("은총", Material.BUBBLE_CORAL, 0, 1, Arrays.asList("파티원들의 허기를 채워줍니다"), 23, skillsInv);
				itemset("구원", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다","최후의 심판 대기시간이 감소합니다"), 25, skillsInv);
				itemset("참회", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [번개]","🖮🖰 달리기 + 아이템던지기","",ChatColor.BOLD+"10 X (3D + 적의 최대체력의 1%)"), 26, skillsInv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("스킬포인트", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+psd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, skillsInv);

		}
		else {
			itemset("Thrust", Material.WOODEN_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Thrust.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 Blocking + SwapHand","",ChatColor.BOLD+" X (0.5D + "+BigDecimal.valueOf(psd.Thrust.getOrDefault(p.getUniqueId(),0)*0.485).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 0, skillsInv);
			itemset("Restraint", Material.STONE_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Restraint.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 Blocking + Sneaking + SwapHand","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(psd.Restraint.getOrDefault(p.getUniqueId(),0)*0.69).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 1, skillsInv);
			itemset("Judgement", Material.IRON_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Judgement.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Lightning]","🖮🖰 LeftClick + Sneaking",
					"Increase Party's Damage",ChatColor.BOLD+" X "+BigDecimal.valueOf(1+psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.01).setScale(2, RoundingMode.HALF_EVEN),
					"Give Party Attack Speed, Strength Effects ("+(int)Math.floor(psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.1)+"Lv)"
					,"","Strike Lightning To Enemies",ChatColor.BOLD+" X (0.8D + "+BigDecimal.valueOf(psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 2, skillsInv);
			itemset("Punish", Material.GOLDEN_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Punish.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Lightning]","🖮🖰 Jump + Hit","",ChatColor.BOLD+" X (0.5D + "+BigDecimal.valueOf(psd.Punish.getOrDefault(p.getUniqueId(),0)*1.5).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 3, skillsInv);
			itemset("Encourge", Material.DIAMOND_AXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Encourge.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 SwapHand", "Master Lv.50"), 4, skillsInv);
			itemset("Pray", Material.BOOKSHELF, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Pray.getOrDefault(p.getUniqueId(),0),"","🖮🖰 SwapHand + Sneaking","Master Lv.10"), 5, skillsInv);
			itemset("Protection", Material.BOOK, 0, 1, Arrays.asList("When you raise the shield", "it summons a giant shield.","Blocks all damage from the front.","Reduces damage to Party", "by 50% while Blocking (Passive)"), 6, skillsInv);
			itemset("Faith", Material.WRITABLE_BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+psd.Protection.getOrDefault(p.getUniqueId(),0),"","Increases Damage","Increases Lightning Resistance","",ChatColor.BOLD+" X 1.3 + "+BigDecimal.valueOf(psd.Protection.getOrDefault(p.getUniqueId(),0)*0.4383).setScale(2, RoundingMode.HALF_EVEN)), 7, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("HolySmash(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("Illumination(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("Asperges(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("Sanctification(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("Aria(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("Bless(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("Tutelary(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("Last judgment(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("HolySmash", Material.SHIELD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 Use HolySmash When You Use Once More","(Damage Affected By Thrust)","",ChatColor.BOLD+" X (0.75D + "+BigDecimal.valueOf(psd.Thrust.getOrDefault(p.getUniqueId(),0)*0.485).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, skillsInv);
				itemset("Illumination", Material.DAYLIGHT_DETECTOR, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Lightning]","🖮🖰 Use Illumination When Use Once More","(Damage Affected By Restraint)","",ChatColor.BOLD+"8 X (0.12D + "+BigDecimal.valueOf(psd.Restraint.getOrDefault(p.getUniqueId(),0)*0.12).setScale(2, RoundingMode.HALF_EVEN)+")"), 10, skillsInv);
				itemset("Asperges", Material.MILK_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Lightning]","🖮🖰 Use Asperges When Use Once More","(Damage Affected By Judgement)","",ChatColor.BOLD+" X (0.8D + "+BigDecimal.valueOf(psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, skillsInv);
				itemset("Sanctification", Material.DIAMOND_AXE, 0, 1, Arrays.asList("Stun Hit Enemy"), 12, skillsInv);
				itemset("Aria", Material.NOTE_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 Use Aria When Use Once More","(Damage Affected By Encourge)","",ChatColor.BOLD+"3 X (0.5D + "+BigDecimal.valueOf(psd.Encourge.getOrDefault(p.getUniqueId(),0)*0.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 13, skillsInv);
				itemset("Bless", Material.ENCHANTING_TABLE, 0, 1, Arrays.asList("Sets Party's Armor Max","(Duration Affected By Pray)","",ChatColor.BOLD+""+BigDecimal.valueOf(psd.Pray.getOrDefault(p.getUniqueId(),0)*0.2).setScale(1, RoundingMode.HALF_EVEN)+"s"), 14, skillsInv);
				itemset("Tutelary", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor"), 16, skillsInv);
				itemset("Last judgment", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Lightning]","🖮🖰 Sneaking + ThrowItem","",ChatColor.BOLD+"10 X 1.5D"), 17, skillsInv);

				itemset("HolyPile(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("Doom(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
				itemset("Griffon(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("Sanctuary(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("Grace(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
				itemset("Salvation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("Penance(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("HolySmash", Material.SHIELD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 Use HolySmash When You Use Once More","(Damage Affected By Thrust)","",ChatColor.BOLD+" X (0.75D + "+BigDecimal.valueOf(psd.Thrust.getOrDefault(p.getUniqueId(),0)*0.485).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, skillsInv);
				itemset("Illumination", Material.DAYLIGHT_DETECTOR, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Lightning]","🖮🖰 Use Illumination When Use Once More","(Damage Affected By Restraint)","",ChatColor.BOLD+"8 X (0.12D + "+BigDecimal.valueOf(psd.Restraint.getOrDefault(p.getUniqueId(),0)*0.12).setScale(2, RoundingMode.HALF_EVEN)+")"), 10, skillsInv);
				itemset("Asperges", Material.MILK_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Lightning]","🖮🖰 Use Asperges When Use Once More","(Damage Affected By Judgement)","",ChatColor.BOLD+" X (0.8D + "+BigDecimal.valueOf(psd.Judgement.getOrDefault(p.getUniqueId(),0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, skillsInv);
				itemset("Sanctification", Material.DIAMOND_AXE, 0, 1, Arrays.asList("Stun Hit Enemy"), 12, skillsInv);
				itemset("Aria", Material.NOTE_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 Use Aria When Use Once More","(Damage Affected By Encourge)","",ChatColor.BOLD+"3 X (0.5D + "+BigDecimal.valueOf(psd.Encourge.getOrDefault(p.getUniqueId(),0)*0.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 13, skillsInv);
				itemset("Bless", Material.ENCHANTING_TABLE, 0, 1, Arrays.asList("Sets Party's Armor Max","(Duration Affected By Pray)","",ChatColor.BOLD+""+BigDecimal.valueOf(psd.Pray.getOrDefault(p.getUniqueId(),0)*0.2).setScale(1, RoundingMode.HALF_EVEN)+"s"), 14, skillsInv);
				itemset("Tutelary", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor"), 16, skillsInv);
				itemset("Last judgment", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Lightning]","🖮🖰 Sneaking + ThrowItem","",ChatColor.BOLD+"10 X 1.5D"), 17, skillsInv);

				itemset("HolyPile", Material.LIGHTNING_ROD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 Drive HolyPile When You Use Once More","(Damage Affected By Thrust)","",ChatColor.BOLD+"4 X (0.4D + "+BigDecimal.valueOf(psd.Thrust.getOrDefault(p.getUniqueId(),0)*0.455).setScale(2, RoundingMode.HALF_EVEN)+")"), 18, skillsInv);
				itemset("Doom", Material.BUBBLE_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Lightning]","🖮🖰 Doom To Enemies When Use Once More","(Damage Affected By Restraint)","",ChatColor.BOLD+" X (1.5D + "+BigDecimal.valueOf(psd.Restraint.getOrDefault(p.getUniqueId(),0)*1.68).setScale(2, RoundingMode.HALF_EVEN)+")"), 19, skillsInv);
				itemset("Griffon", Material.TRIDENT, 0, 1, Arrays.asList("Summon Griffon When Use Once More", "Griffon Will disappear When You Dismount", "Or Summon Again","", "Enhance [Punish] While Griffon Exists","(Change Command to 🖮🖰 [Jump+LeftClick])","",
						ChatColor.UNDERLINE+"❈ [Lightning]","🖮🖰 Inflicts Splash Damage When Griffon Jump","Fly shortly when jump strength over 80%","",ChatColor.BOLD+" X (1.3D + "+BigDecimal.valueOf(psd.Judgement.getOrDefault(p.getUniqueId(),0)*1.3).setScale(2, RoundingMode.HALF_EVEN)+" X JumpPower)", "(Damage Affected By Judgement)"), 20, skillsInv);
				itemset("Sanctuary", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Earth]","🖮🖰 Summon Sanctuary When Use Once More","(Damage Affected By Encourge)","",ChatColor.BOLD+"10 X (0.55D + "+BigDecimal.valueOf(psd.Encourge.getOrDefault(p.getUniqueId(),0)*0.68).setScale(2, RoundingMode.HALF_EVEN)+")"), 22, skillsInv);
				itemset("Grace", Material.BUBBLE_CORAL, 0, 1, Arrays.asList("Give Saturation to Party"), 23, skillsInv);
				itemset("Salvation", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decreases Last judgment Cooldown"), 25, skillsInv);
				itemset("Penance", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Lightning]","🖮🖰 Sprinting + ThrowItem","",ChatColor.BOLD+"10 X (3D + 1% of Enemy's MaxHP)"), 26, skillsInv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("SkillPoints", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+psd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, skillsInv);

		}
		
		Obtained.itemset(p, skillsInv);
		p.openInventory(skillsInv);
	}


}