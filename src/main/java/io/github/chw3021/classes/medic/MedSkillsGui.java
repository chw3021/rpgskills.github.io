package io.github.chw3021.classes.medic;



import java.util.Arrays;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import io.github.chw3021.classes.SkillsGui;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class MedSkillsGui extends SkillsGui {
	

	
	public void Medskillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		MedSkillsData ssd = new MedSkillsData(MedSkillsData.loadData(path +"/plugins/RPGskills/MedskillsData.data"));
		Inventory skillsInv = Bukkit.createInventory(null, 36, "Medskills");
		

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {

				itemset("치유로켓", Material.TIPPED_ARROW, 1, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"✬[서리]","🖮🖰 점프 + 좌클릭","",ChatColor.BOLD+" X (0.32D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.316).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 0, skillsInv);
				itemset("제독", Material.TIPPED_ARROW, 2, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Decontamination.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"✬[서리]","🖮🖰 웅크리기 + 손바꾸기","해로운 효과를 제거합니다","",ChatColor.BOLD+"15 X (0.15D + "+BigDecimal.valueOf(ssd.Decontamination.getOrDefault(p.getUniqueId(),1)*0.16).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 1, skillsInv);
				itemset("보급카트", Material.TIPPED_ARROW, 3, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.SupplyCart.getOrDefault(p.getUniqueId(),1),"","점프 + 손바꾸기",
						"파티원들에게 속도,점프,힘 효과를 줍니다("+1+ssd.SupplyCart.getOrDefault(p.getUniqueId(),0)/2+"레벨, "+(50+50*ssd.SupplyCart.getOrDefault(p.getUniqueId(),0))/20+"초", "Master LV.5"), 2, skillsInv);
				itemset("은신처", Material.TIPPED_ARROW, 4, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Hideout.getOrDefault(p.getUniqueId(),1),"","웅크리기", "Master LV.1"), 3, skillsInv);
				itemset("화살치료", Material.TIPPED_ARROW, 5, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.ArrowClinic.getOrDefault(p.getUniqueId(),1),"",
						"적중한 파티원을 치료합니다", "최대체력 X (0.05 + "+BigDecimal.valueOf(0.05+ssd.ArrowClinic.getOrDefault(p.getUniqueId(),1)*0.005+ssd.Medicine.getOrDefault(p.getUniqueId(),1)*0.004).setScale(2, RoundingMode.HALF_EVEN)+")","",
						ChatColor.UNDERLINE+"✬[바람]","🖮🖰 화살에 맞은 적을 부패시켜" ,"받는피해가 "+BigDecimal.valueOf(0.03*ssd.ArrowClinic.getOrDefault(p.getUniqueId(),1)).setScale(2, RoundingMode.HALF_EVEN)+"만큼 증가하게 합니다 (최대 1.9)", "스킬레벨이 오를수록 더 많이 중첩됩니다", "Master LV.30"), 4, skillsInv);
				itemset("자동제세동기", Material.CROSSBOW, 6, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.AED.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"✬[바람]","🖮🖰 웅크리기 + 좌클릭","",ChatColor.BOLD+" X (0.3D + "+BigDecimal.valueOf(ssd.AED.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 5, skillsInv);
				itemset("구조", Material.KNOWLEDGE_BOOK, 6, 1, Arrays.asList(ChatColor.AQUA+ "패시브스킬","","파티원이 죽음에 달하는 피해를 입으면,","5초동안 빈사 상태에 빠집니다","이동속도 효과를 얻고 자동제세동기 스킬로","해당 파티원을 구조할수 있습니다"), 6, skillsInv);
				itemset("의학", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Medicine.getOrDefault(p.getUniqueId(),1),"","공격력과 회복률이 상승합니다","",ChatColor.BOLD+" + "+BigDecimal.valueOf(ssd.Medicine.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)), 7, skillsInv);
				if(Proficiency.getpro(p)<1) {
					itemset("진공(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
					itemset("보호막(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
					itemset("지원사격(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
					itemset("진통제(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
					itemset("각성제(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
					itemset("미세바늘(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
					itemset("후송(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 15, skillsInv);
					itemset("응급처치(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
					itemset("카드세우스 탑(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
				}
				else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
					itemset("진공", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[바람]","🖮🖰 주변 적들을 모읍니다","(공격력은 치유로켓 레벨에 비례합니다)","",ChatColor.BOLD+" X (0.4D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, skillsInv);
					itemset("보호막", Material.SPORE_BLOSSOM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[서리]","🖮🖰 재입력시 보호막을 설치합니다","보호막안에 있는 파티원들은 무적상태가 됩니다","(공격력은 제독 레벨에 비례합니다)","",ChatColor.BOLD+" X (0.4D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 10, skillsInv);
					itemset("지원사격", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[바람]","🖮🖰 재입력시 지원사격을 요청합니다","(공격력은 보급카트 레벨에 비례합니다)","",ChatColor.BOLD+"8 X (1.1D + "+BigDecimal.valueOf(ssd.SupplyCart.getOrDefault(p.getUniqueId(),1)*3.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, skillsInv);
					itemset("진통제", Material.CHISELED_QUARTZ_BLOCK, 0, 1, Arrays.asList("주변 파티원들의 방어력이 상승합니다"), 12, skillsInv);
					itemset("각성제", Material.BUBBLE_CORAL_BLOCK, 0, 1, Arrays.asList("적은 잠시 기절시키고, 파티원에게는 이동속도를 제공합니다"), 13, skillsInv);
					itemset("미세바늘", Material.BOW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[바람]","🖮🖰 재입력시 미세바늘을 뿌립니다","(공격력은 자동제세동기 레벨에 비례합니다)","",ChatColor.BOLD+" X (0.43D + "+BigDecimal.valueOf(ssd.AED.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 14, skillsInv);
					itemset("후송", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("빈사상태에 빠진 파티원을 후송합니다"), 15, skillsInv);
					itemset("응급처치", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다", "제독과 자동제세동기가 파티원을 치료하고, 적은 기절시킵니다"), 16, skillsInv);
					itemset("카드세우스 탑", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("웅크리기 + num4", BigDecimal.valueOf(20-ssd.Medicine.getOrDefault(p.getUniqueId(),1)*0.3).setScale(2, RoundingMode.HALF_EVEN)+"초당 1의 회복과 포만을 제공합니다"), 17, skillsInv);

					itemset("마취(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
					itemset("회복펌프(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
					itemset("집단치료(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
					itemset("확장(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
					itemset("초음파 분무기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
					itemset("환자운반(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 24, skillsInv);
					itemset("생명의은인(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
					itemset("날개슈트(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
				}
				else {
					itemset("진공", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[바람]","🖮🖰 주변 적들을 모읍니다","(공격력은 치유로켓 레벨에 비례합니다)","",ChatColor.BOLD+" X (0.4D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, skillsInv);
					itemset("보호막", Material.SPORE_BLOSSOM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[서리]","🖮🖰 재입력시 보호막을 설치합니다","보호막안에 있는 파티원들은 무적상태가 됩니다","(공격력은 제독 레벨에 비례합니다)","",ChatColor.BOLD+" X (0.4D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 10, skillsInv);
					itemset("지원사격", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[바람]","🖮🖰 재입력시 지원사격을 요청합니다","(공격력은 보급카트 레벨에 비례합니다)","",ChatColor.BOLD+"8 X (1.1D + "+BigDecimal.valueOf(ssd.SupplyCart.getOrDefault(p.getUniqueId(),1)*3.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, skillsInv);
					itemset("진통제", Material.CHISELED_QUARTZ_BLOCK, 0, 1, Arrays.asList("주변 파티원들의 방어력이 상승합니다"), 12, skillsInv);
					itemset("각성제", Material.BUBBLE_CORAL_BLOCK, 0, 1, Arrays.asList("적은 잠시 기절시키고, 파티원에게는 이동속도를 제공합니다"), 13, skillsInv);
					itemset("미세바늘", Material.BOW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[바람]","🖮🖰 재입력시 미세바늘을 뿌립니다","(공격력은 자동제세동기 레벨에 비례합니다)","",ChatColor.BOLD+" X (0.43D + "+BigDecimal.valueOf(ssd.AED.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 14, skillsInv);
					itemset("후송", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("빈사상태에 빠진 파티원을 후송합니다"), 15, skillsInv);
					itemset("응급처치", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다", "제독과 자동제세동기가 파티원을 치료하고, 적은 기절시킵니다"), 16, skillsInv);
					itemset("카드세우스 탑", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("웅크리기 + num4", BigDecimal.valueOf(20-ssd.Medicine.getOrDefault(p.getUniqueId(),1)*0.3).setScale(2, RoundingMode.HALF_EVEN)+"초당 1의 회복과 포만을 제공합니다"), 17, skillsInv);

					itemset("마취", Material.END_CRYSTAL, 0, 1, Arrays.asList("적중한 파티원들을 잠깐 무적상태로 만듭니다","적중한 적들을 잠깐 기절시킵니다"), 18, skillsInv);
					itemset("회복펌프", Material.END_CRYSTAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[서리]","🖮🖰 재입력시 회복펌프를 설치합니다", "(공격력은 제독 레벨에 비례합니다)","",ChatColor.BOLD+"5 X (0.8D + "+BigDecimal.valueOf(ssd.Decontamination.getOrDefault(p.getUniqueId(),1)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")"), 19, skillsInv);
					itemset("집단치료", Material.END_ROD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[바람]","🖮🖰 재입력시 집단치료를 실시합니다", "(공격력은 보급카트 레벨에 비례합니다)","",ChatColor.BOLD+"5 X (1.5D + "+BigDecimal.valueOf(ssd.SupplyCart.getOrDefault(p.getUniqueId(),1)*7.1).setScale(2, RoundingMode.HALF_EVEN)+")"), 20, skillsInv);
					itemset("확장", Material.SPLASH_POTION, 0, 1, Arrays.asList("범위와 보호막량이 증가합니다"), 21, skillsInv);
					itemset("초음파 분무기", Material.BOW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[바람]","🖮🖰 재입력시 초음파 분무기를 사용합니다","부패 중첩수를 증가시킵니다","",ChatColor.BOLD+"3 X (0.35D + "+BigDecimal.valueOf(ssd.AED.getOrDefault(p.getUniqueId(),1)*0.45).setScale(2, RoundingMode.HALF_EVEN)+")","(공격력은 자동제세동기 레벨에 비례합니다)"), 23, skillsInv);
					itemset("환자운반", Material.BOW, 0, 1, Arrays.asList("파티원이 당신에게 웅크리기+우클릭을 하면","해당 파티원을 운반할수 있습니다","파티원은 은신상태가 됩니다", "클릭시 운반이 종료됩니다"), 24, skillsInv);
					itemset("생명의은인", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다","카드세우스 탑 재사용대기시간이 감소합니다"), 25, skillsInv);
					itemset("날개슈트", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("웅크리기 + num5", "날개슈트가 활성화되어 있는동안 공격력이 증가합니다(25초)","치유로켓의 대기시간이 없어집니다", "X (1 + 0.007D)"), 26, skillsInv);
				}
				itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
				itemset("스킬포인트", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+ssd.SkillPoints.getOrDefault(p.getUniqueId(),1),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, skillsInv);

			}
			else {

				itemset("RemedyingRocket", Material.TIPPED_ARROW, 1, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"✬[Frost]","🖮🖰 Jump + LeftClick","",ChatColor.BOLD+" X (0.32D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.316).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 0, skillsInv);
				itemset("Decontamination", Material.TIPPED_ARROW, 2, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Decontamination.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"✬[Frost]","🖮🖰 Sneaking + SwapHand","Removes All Negetive Effects","",ChatColor.BOLD+"15 X (0.15D + "+BigDecimal.valueOf(ssd.Decontamination.getOrDefault(p.getUniqueId(),1)*0.16).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 1, skillsInv);
				itemset("SupplyCart", Material.TIPPED_ARROW, 3, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.SupplyCart.getOrDefault(p.getUniqueId(),1),"","Jump + SwapHand",
						"Give Party Jump, Speed, Strength Effects ("+1+ssd.SupplyCart.getOrDefault(p.getUniqueId(),0)/2+"Lv, "+(50+50*ssd.SupplyCart.getOrDefault(p.getUniqueId(),0))/20+"s", "Master LV.5"), 2, skillsInv);
				itemset("Hideout", Material.TIPPED_ARROW, 4, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Hideout.getOrDefault(p.getUniqueId(),1),"","Sneaking", "Master LV.1"), 3, skillsInv);
				itemset("ArrowClinic", Material.TIPPED_ARROW, 5, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.ArrowClinic.getOrDefault(p.getUniqueId(),1),"",
						"Heal Hit party member", "MaxHealth X (0.05 + "+BigDecimal.valueOf(0.05+ssd.ArrowClinic.getOrDefault(p.getUniqueId(),1)*0.005+ssd.Medicine.getOrDefault(p.getUniqueId(),1)*0.004).setScale(2, RoundingMode.HALF_EVEN)+")","",
						ChatColor.UNDERLINE+"✬[Wind]","🖮🖰 Every time an enemy is hit" ,"The damage the enemy receives increases by "+BigDecimal.valueOf(0.03*ssd.ArrowClinic.getOrDefault(p.getUniqueId(),1)).setScale(2, RoundingMode.HALF_EVEN),"(Max 1.9)", "Higher Skill Levels Can Stack Faster", "Master LV.30"), 4, skillsInv);
				itemset("AED", Material.CROSSBOW, 6, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.AED.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"✬[Wind]","🖮🖰 Sneaking + LeftClick","",ChatColor.BOLD+" X (0.3D + "+BigDecimal.valueOf(ssd.AED.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 5, skillsInv);
				itemset("Rescue", Material.KNOWLEDGE_BOOK, 6, 1, Arrays.asList(ChatColor.AQUA+ "Passive","","When Party Member is at death's door,","The Member will be Groggy for 5secs","You'll get speed and be able to Rescue Member","By using AED skill"), 6, skillsInv);
				itemset("Medicine", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Medicine.getOrDefault(p.getUniqueId(),1),"","Increse Damage and Healing Rate","",ChatColor.BOLD+" + "+BigDecimal.valueOf(ssd.Medicine.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)), 7, skillsInv);
				if(Proficiency.getpro(p)<1) {
					itemset("Vacuum(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
					itemset("Barrier(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
					itemset("SupportFire(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
					itemset("Anodyne(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
					itemset("Excitometabolic(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
					itemset("FineNeedles(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
					itemset("Evacuation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 15, skillsInv);
					itemset("FirstAids(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
					itemset("Caduceus Tower(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
				}
				else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
					itemset("Vacuum", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Wind]","🖮🖰 Pull Entity to Hit Position","(Damage Affected By RemedyingRocket)","",ChatColor.BOLD+" X (0.4D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, skillsInv);
					itemset("Barrier", Material.SPORE_BLOSSOM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Frost]","🖮🖰 Place Barrier When Use Once More","Party inside the Barrier are invulneralbe","(Damage Affected By Decontamination)","",ChatColor.BOLD+" X (0.4D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 10, skillsInv);
					itemset("SupportFire", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Wind]","🖮🖰 Call SupportFire When Use Once More","(Damage Affected By SupplyCart)","",ChatColor.BOLD+"8 X (1.1D + "+BigDecimal.valueOf(ssd.SupplyCart.getOrDefault(p.getUniqueId(),1)*3.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, skillsInv);
					itemset("Anodyne", Material.CHISELED_QUARTZ_BLOCK, 0, 1, Arrays.asList("Increases Near by Party's Armor"), 12, skillsInv);
					itemset("Excitometabolic", Material.BUBBLE_CORAL_BLOCK, 0, 1, Arrays.asList("Hold Hit Enemy, Give Speed Effect To Party"), 13, skillsInv);
					itemset("FineNeedles", Material.BOW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Wind]","🖮🖰 Spread FineNeedles When Use Once More","(Damage Affected By AED)","",ChatColor.BOLD+" X (0.43D + "+BigDecimal.valueOf(ssd.AED.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 14, skillsInv);
					itemset("Evacuation", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Groggy Party Member Will be Evacuated"), 15, skillsInv);
					itemset("FirstAids", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage", "Decontamination & AED will Heal Party, Hold Enemies"), 16, skillsInv);
					itemset("Caduceus Tower", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + num4", "Give 1 Heal & Saturation Effect Per "+BigDecimal.valueOf(20-ssd.Medicine.getOrDefault(p.getUniqueId(),1)*0.3).setScale(2, RoundingMode.HALF_EVEN)+"Seconds"), 17, skillsInv);

					itemset("Anesthetic(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
					itemset("HealingPump(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
					itemset("Mass Treatment(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
					itemset("Expand(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
					itemset("Ultrasonic Nebulizer(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
					itemset("Stretcher(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 24, skillsInv);
					itemset("LifeSaver(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
					itemset("Wing Suit(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
				}
				else {
					itemset("Vacuum", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Wind]","🖮🖰 Pull Entity to Hit Position","(Damage Affected By RemedyingRocket)","",ChatColor.BOLD+" X (0.4D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, skillsInv);
					itemset("Barrier", Material.SPORE_BLOSSOM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Frost]","🖮🖰 Place Barrier When Use Once More","Party inside the Barrier are invulneralbe","(Damage Affected By Decontamination)","",ChatColor.BOLD+" X (0.4D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 10, skillsInv);
					itemset("SupportFire", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Wind]","🖮🖰 Call SupportFire When Use Once More","(Damage Affected By SupplyCart)","",ChatColor.BOLD+"8 X (1.1D + "+BigDecimal.valueOf(ssd.SupplyCart.getOrDefault(p.getUniqueId(),1)*3.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, skillsInv);
					itemset("Anodyne", Material.CHISELED_QUARTZ_BLOCK, 0, 1, Arrays.asList("Increases Near by Party's Armor"), 12, skillsInv);
					itemset("Excitometabolic", Material.BUBBLE_CORAL_BLOCK, 0, 1, Arrays.asList("Hold Hit Enemy, Give Speed Effect To Party"), 13, skillsInv);
					itemset("FineNeedles", Material.BOW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Wind]","🖮🖰 Spread FineNeedles When Use Once More","(Damage Affected By AED)","",ChatColor.BOLD+" X (0.43D + "+BigDecimal.valueOf(ssd.AED.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 14, skillsInv);
					itemset("Evacuation", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Groggy Party Member Will be Evacuated"), 15, skillsInv);
					itemset("FirstAids", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage", "Decontamination & AED will Heal Party, Hold Enemies"), 16, skillsInv);
					itemset("Caduceus Tower", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + num4", "Give 1 Heal & Saturation Effect Per "+BigDecimal.valueOf(20-ssd.Medicine.getOrDefault(p.getUniqueId(),1)*0.3).setScale(2, RoundingMode.HALF_EVEN)+"Seconds"), 17, skillsInv);

					itemset("Anesthetic", Material.END_CRYSTAL, 0, 1, Arrays.asList("Set Hit Party Invulnerable Shortly","Hold Hit Enemies Shortly"), 18, skillsInv);
					itemset("HealingPump", Material.END_CRYSTAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Frost]","🖮🖰 Place HealingPump When Use Once More", "(Damage Affected By Decontamination)"), 19, skillsInv);
					itemset("Mass Treatment", Material.END_ROD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Wind]","🖮🖰 MassTreatment When Use Once More", "(Damage Affected By SupplyCart)","",ChatColor.BOLD+"5 X (0.8D + "+BigDecimal.valueOf(ssd.Decontamination.getOrDefault(p.getUniqueId(),1)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")"), 20, skillsInv);
					itemset("Expand", Material.SPLASH_POTION, 0, 1, Arrays.asList("Increases Range & Absortion Amount"), 21, skillsInv);
					itemset("Ultrasonic Nebulizer", Material.BOW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Wind]","🖮🖰 Use Ultrasonic Nebulizer When Use Once More","Increases Decay Stack","",ChatColor.BOLD+"3 X (0.35D + "+BigDecimal.valueOf(ssd.AED.getOrDefault(p.getUniqueId(),1)*0.45).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By AED)"), 23, skillsInv);
					itemset("Stretcher", Material.BOW, 0, 1, Arrays.asList("Party Can Carried by You","Using Sneaking+RightClick with Bare Hand","Set Carried Invisible", "Quit Carrying By Click"), 24, skillsInv);
					itemset("LifeSaver", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decrease Caduceus Tower Cooldown"), 25, skillsInv);
					itemset("Wing Suit", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + num5", "Increases Damage While Using Wing Suit(25s)", "Removes Cooldown of RemedyingRocket", "X (1 + 0.007D)"), 26, skillsInv);
				}
				itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
				itemset("SkillPoints", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+ssd.SkillPoints.getOrDefault(p.getUniqueId(),1),"","Click if you want to reset your skill's levels"), 35, skillsInv);

			}


			Obtained.itemset(p, skillsInv);
		p.openInventory(skillsInv);
		}

}
