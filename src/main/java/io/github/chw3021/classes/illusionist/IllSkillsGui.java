package io.github.chw3021.classes.illusionist;



import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.classes.SkillsGui;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class IllSkillsGui extends SkillsGui{



	public void IllSkillsinv(Player p)
	{
		String path = new File("").getAbsolutePath();
		IllSkillsData isd = new IllSkillsData(IllSkillsData.loadData(path +"/plugins/RPGskills/IllSkillsData.data"));
		Inventory Illskillsinv = Bukkit.createInventory(null, 54, "Illskills");
		Obtained.itemset(p, Illskillsinv);

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("바꿔치기", Material.STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Switch.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[바람 계열]","근접공격 + 웅크리기", "Master LV.1"), 0, Illskillsinv);
			itemset("속임수", Material.STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Trick.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[바람 계열]","좌클릭 + 점프","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.66*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.0535)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 1, Illskillsinv);
			itemset("잭오랜턴", Material.JACK_O_LANTERN, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.JackoLantern.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[바람 계열]","손바꾸기","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.68*(1+isd.JackoLantern.getOrDefault(p.getUniqueId(),0)*0.062)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 2, Illskillsinv);
			itemset("왜곡", Material.DEBUG_STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Distortion.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[바람 계열]","우클릭","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.626*(1+isd.Distortion.getOrDefault(p.getUniqueId(),0)*0.0835)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 3, Illskillsinv);
			itemset("역설", Material.DEBUG_STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Paradox.getOrDefault(p.getUniqueId(),0),"","웅크리기 + 우클릭","궁극기를 제외한 모든 기술의","재사용 대기시간을 초기화 합니다","해로운 효과를 모두 제거합니다","체력과 허기를 모두 회복합니다", "Master LV.1"), 4, Illskillsinv);
			itemset("허수아비", Material.ARMOR_STAND, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.FakeDoll.getOrDefault(p.getUniqueId(),0),"","손바꾸기 + 웅크리기", "주변 적들을 도발합니다", "0.5초동안 무적상태가 됩니다", "Master LV.1"), 5, Illskillsinv);
			itemset("깜짝쇼", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Surprise.getOrDefault(p.getUniqueId(),0),"","투명화 상태일때 공격력이 증가합니다","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1+isd.Surprise.getOrDefault(p.getUniqueId(),0)*0.056).setScale(2, RoundingMode.HALF_EVEN)), 7, Illskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("혼란(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, Illskillsinv);
				itemset("앙코르(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, Illskillsinv);
				itemset("확대(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, Illskillsinv);
				itemset("뒤섞기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, Illskillsinv);
				itemset("기믹(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, Illskillsinv);
				itemset("위치변경(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, Illskillsinv);
				itemset("조작(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 16, Illskillsinv);
				itemset("공허의 환상(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, Illskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("혼란", Material.END_PORTAL_FRAME, 0, 1, Arrays.asList("적을 짧게 제압합니다"), 9, Illskillsinv);
				itemset("앙코르", Material.CHORUS_FLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 앙코르를 사용합니다","(피해량은 속임수 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.0535)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Illskillsinv);
				itemset("확대", Material.COMPARATOR, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 확대를 사용합니다","(피해량은 잭오랜턴 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+isd.JackoLantern.getOrDefault(p.getUniqueId(),0)*0.062)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Illskillsinv);
				itemset("뒤섞기", Material.SKULL_BANNER_PATTERN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 뒤섞기를 사용합니다","(피해량은 왜곡 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+isd.Distortion.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Illskillsinv);
				itemset("기믹", Material.CREEPER_HEAD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","남아있는 허수아비들을 폭발시킵니다","(피해량은 속임수 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.66*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.0535)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Illskillsinv);
				itemset("위치변경", Material.SKELETON_SKULL, 0, 1, Arrays.asList("재입력시 허수아비와 자신의 위치를 바꿉니다", "0.5초동안 무적상태가 됩니다"), 14, Illskillsinv);
				itemset("조작", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다","스킬사용시 0.3초동안 무적상태가 됩니다"), 16, Illskillsinv);
				itemset("공허의 환상", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","웅크리기 + num4","",ChatColor.BOLD+" X 15.6D"), 17, Illskillsinv);

				itemset("착란(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 18, Illskillsinv);
				itemset("관통(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 19, Illskillsinv);
				itemset("곡예(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 20, Illskillsinv);
				itemset("세뇌(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 21, Illskillsinv);
				itemset("최면(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 22, Illskillsinv);
				itemset("초감각적지각(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 25, Illskillsinv);
				itemset("피날레(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, Illskillsinv);
			}
			else {
				itemset("혼란", Material.END_PORTAL_FRAME, 0, 1, Arrays.asList("적을 짧게 제압합니다"), 9, Illskillsinv);
				itemset("앙코르", Material.CHORUS_FLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 앙코르를 사용합니다","(피해량은 속임수 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.0535)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Illskillsinv);
				itemset("확대", Material.COMPARATOR, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 확대를 사용합니다","(피해량은 잭오랜턴 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+isd.JackoLantern.getOrDefault(p.getUniqueId(),0)*0.062)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Illskillsinv);
				itemset("뒤섞기", Material.SKULL_BANNER_PATTERN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 뒤섞기를 사용합니다","(피해량은 왜곡 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+isd.Distortion.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Illskillsinv);
				itemset("기믹", Material.CREEPER_HEAD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","남아있는 허수아비들을 폭발시킵니다","(피해량은 속임수 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.66*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.0535)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Illskillsinv);
				itemset("위치변경", Material.SKELETON_SKULL, 0, 1, Arrays.asList("재입력시 허수아비와 자신의 위치를 바꿉니다", "0.5초동안 무적상태가 됩니다"), 14, Illskillsinv);
				itemset("조작", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다","스킬사용시 0.3초동안 무적상태가 됩니다"), 16, Illskillsinv);
				itemset("공허의 환상", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","웅크리기 + num4","",ChatColor.BOLD+" X 15.6D"), 17, Illskillsinv);

				itemset("착란", Material.CARROT_ON_A_STICK, 0, 1, Arrays.asList("주변적이 대상을 공격합니다"), 18, Illskillsinv);
				itemset("관통", Material.GLASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 관통을 사용합니다","(피해량은 속임수 레벨에 비례합니다)","",ChatColor.BOLD+"21 X "+BigDecimal.valueOf(0.24*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, Illskillsinv);
				itemset("곡예", Material.CLAY_BALL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 곡예를 사용합니다","(피해량은 잭오랜턴 레벨에 비례합니다)","",ChatColor.BOLD+"21 X "+BigDecimal.valueOf(0.15*(1+isd.JackoLantern.getOrDefault(p.getUniqueId(),0)*0.022)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, Illskillsinv);
				itemset("세뇌", Material.BRAIN_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","재입력시 세뇌를 사용합니다","(피해량은 왜곡 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+isd.Distortion.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 21, Illskillsinv);
				itemset("최면", Material.CANDLE, 0, 1, Arrays.asList("허수아비 주변의 적들을 재웁니다","잠든 적은 공격을 받으면","더 많은 피해를 입고 깨어납니다","", "1초동안 무적상태가 됩니다"), 22, Illskillsinv);
				itemset("초감각적지각", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다","공허의 환상 재사용 대기시간이 감소합니다","","깜짝쇼가 은신이 아니여도 발동됩니다"), 25, Illskillsinv);
				itemset("피날레", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[바람 계열]","웅크리기 + num5", "역설의 대기시간이 80% 감소합니다", "기술사용시 피날레 효과가 중첩됩니다","8중첩 달성시 추가 고정피해를 입힙니다",ChatColor.BOLD+"피해량 X (4+0.01XD)%","10초동안 지속됩니다"), 26, Illskillsinv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Illskillsinv);
			itemset("스킬포인트", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+isd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, Illskillsinv);
		}
		else {
			itemset("Switch", Material.STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Switch.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","Hit + Sneaking", "Master LV.1"), 0, Illskillsinv);
			itemset("Trick", Material.STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Trick.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","LeftClick + Jump","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.66*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.0535)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 1, Illskillsinv);
			itemset("JackoLantern", Material.JACK_O_LANTERN, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.JackoLantern.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","SwapHand","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.68*(1+isd.JackoLantern.getOrDefault(p.getUniqueId(),0)*0.062)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 2, Illskillsinv);
			itemset("Distortion", Material.DEBUG_STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Distortion.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","Rightclick","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.626*(1+isd.Distortion.getOrDefault(p.getUniqueId(),0)*0.0835)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 3, Illskillsinv);
			itemset("Paradox", Material.DEBUG_STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Paradox.getOrDefault(p.getUniqueId(),0),"","Sneaking + Rightclick","Reset All CoolDown","Remove negetive Effect","Reset HP And Hunger", "Master LV.1"), 4, Illskillsinv);
			itemset("FakeDoll", Material.ARMOR_STAND, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.FakeDoll.getOrDefault(p.getUniqueId(),0),"","SwapHand + Sneaking", "Taunts shortly near by enemies", "Get invincible state for 0.5s","", "Master LV.1"), 5, Illskillsinv);
			itemset("Surprise", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Surprise.getOrDefault(p.getUniqueId(),0),"","Increases damage when you're invisible","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1+isd.Surprise.getOrDefault(p.getUniqueId(),0)*0.056).setScale(2, RoundingMode.HALF_EVEN)), 7, Illskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("Confusion(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Illskillsinv);
				itemset("Encore(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Illskillsinv);
				itemset("Magnify(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Illskillsinv);
				itemset("Shuffle(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Illskillsinv);
				itemset("Gimmick(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Illskillsinv);
				itemset("Change(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Illskillsinv);
				itemset("Manipulation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Illskillsinv);
				itemset("The Void(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Illskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Confusion", Material.END_PORTAL_FRAME, 0, 1, Arrays.asList("Holds Hit Enemy Shortly"), 9, Illskillsinv);
				itemset("Encore", Material.CHORUS_FLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use Encore When use Once More","(Damage Affected By Trick)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.0535)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Illskillsinv);
				itemset("Magnify", Material.COMPARATOR, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use Magnify When use Once More","(Damage Affected By JackoLantern)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+isd.JackoLantern.getOrDefault(p.getUniqueId(),0)*0.062)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Illskillsinv);
				itemset("Shuffle", Material.SKULL_BANNER_PATTERN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use Shuffle When use Once More","(Damage Affected By Distortion)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+isd.Distortion.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Illskillsinv);
				itemset("Gimmick", Material.CREEPER_HEAD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Explodes Remaining FakeDoll","(Damage Affected By Trick)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.66*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.0535)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Illskillsinv);
				itemset("Change", Material.SKELETON_SKULL, 0, 1, Arrays.asList("Switchs Pos with FakeDoll When use Once More", "Get invincible state for 0.5s"), 14, Illskillsinv);
				itemset("Manipulation", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage","Get invincible for 0.3s after using any skill"), 16, Illskillsinv);
				itemset("The Void", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Sneaking + num4","",ChatColor.BOLD+" X 15.6D"), 17, Illskillsinv);

				itemset("Misdirection(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, Illskillsinv);
				itemset("Penetration(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, Illskillsinv);
				itemset("Juggling(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, Illskillsinv);
				itemset("MindControl(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, Illskillsinv);
				itemset("Hypnosis(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, Illskillsinv);
				itemset("ESP(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Illskillsinv);
				itemset("Finale(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Illskillsinv);
			}
			else {
				itemset("Confusion", Material.END_PORTAL_FRAME, 0, 1, Arrays.asList("Holds Hit Enemy Shortly"), 9, Illskillsinv);
				itemset("Encore", Material.CHORUS_FLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use Encore When use Once More","(Damage Affected By Trick)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.0535)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Illskillsinv);
				itemset("Magnify", Material.COMPARATOR, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use Magnify When use Once More","(Damage Affected By JackoLantern)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+isd.JackoLantern.getOrDefault(p.getUniqueId(),0)*0.062)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Illskillsinv);
				itemset("Shuffle", Material.SKULL_BANNER_PATTERN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use Shuffle When use Once More","(Damage Affected By Distortion)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+isd.Distortion.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Illskillsinv);
				itemset("Gimmick", Material.CREEPER_HEAD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Explodes Remaining FakeDoll","(Damage Affected By Trick)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.66*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.0535)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Illskillsinv);
				itemset("Change", Material.SKELETON_SKULL, 0, 1, Arrays.asList("Switchs Pos with FakeDoll When use Once More", "Get invincible state for 0.5s"), 14, Illskillsinv);
				itemset("Manipulation", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage","Get invincible for 0.3s after using any skill"), 16, Illskillsinv);
				itemset("The Void", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Sneaking + num4","",ChatColor.BOLD+" X 15.6D"), 17, Illskillsinv);

				itemset("Misdirection", Material.CARROT_ON_A_STICK, 0, 1, Arrays.asList("Near By Enemies will Attack Hit Target"), 18, Illskillsinv);
				itemset("Penetration", Material.GLASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use MindControl When use Once More","(Damage Affected By JackoLantern)","",ChatColor.BOLD+"21 X "+BigDecimal.valueOf(0.24*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, Illskillsinv);
				itemset("Juggling", Material.CLAY_BALL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use Penetration When use Once More","(Damage Affected By Trick)","",ChatColor.BOLD+"21 X "+BigDecimal.valueOf(0.15*(1+isd.JackoLantern.getOrDefault(p.getUniqueId(),0)*0.022)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, Illskillsinv);
				itemset("MindControl", Material.BRAIN_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use Juggling When use Once More","(Damage Affected By Distortion)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+isd.Distortion.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 21, Illskillsinv);
				itemset("Hypnosis", Material.CANDLE, 0, 1, Arrays.asList("Put the enemies surrounding the doll to sleep","Sleeping Enemies Get More Damage From You","Hit Enemies will wake up", "Get invincible state for 1s"), 22, Illskillsinv);
				itemset("ESP", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decrease The Void Cooldown","","Surprise Always Activates", "Even If You Aren't Invisible"), 25, Illskillsinv);
				itemset("Finale", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Sneaking + num5", "Reduces Paradox's Cooldown 80%", "Stacks Finale effect when you use skills", "If 8 stacks are achieved","Additional absolute damage will be inflicted", ChatColor.BOLD+"Damage X (4+0.01XD)%","Lasts for 10 seconds"), 26, Illskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Illskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+isd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, Illskillsinv);
		}


		p.openInventory(Illskillsinv);
	}


}