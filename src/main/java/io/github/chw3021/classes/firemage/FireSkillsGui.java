package io.github.chw3021.classes.firemage;



import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import io.github.chw3021.classes.SkillsGui;
import io.github.chw3021.obtains.Obtained;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import io.github.chw3021.classes.Proficiency;
import net.md_5.bungee.api.ChatColor;

public class FireSkillsGui extends SkillsGui {



	public void FireSkillsinv(Player p)
	{
		Inventory skillsInv = Bukkit.createInventory(null, 36, "FIreskills");

		

		String path = new File("").getAbsolutePath();
		FireSkillsData fsd = new FireSkillsData(FireSkillsData.loadData(path +"/plugins/RPGskills/FireSkillsData.data"));

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("분화구", Material.CRYING_OBSIDIAN, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 우클릭","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.032*(1+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 0, skillsInv);
			itemset("불의고리", Material.FIRE_CORAL_FAN, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Ring.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 웅크리기 + 우클릭","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.2*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 1, skillsInv);
			itemset("화염구", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Fireball.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 좌클릭 + 점프","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.25*(1+fsd.Fireball.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 2, skillsInv);
			itemset("확산", Material.BLAZE_ROD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Spread.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 웅크리기 + 근접공격","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.5*(1+fsd.Spread.getOrDefault(p.getUniqueId(),0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 3, skillsInv);
			itemset("화염의숨결", Material.CAMPFIRE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Breath.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 손바꾸기","시전중 방어력이 최대치가 됩니다","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.4*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 4, skillsInv);
			itemset("살아있는불꽃", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 손바꾸기 + 웅크리기","",ChatColor.BOLD+"20 X "+BigDecimal.valueOf(0.07*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.016)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 5, skillsInv);
			itemset("열기", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.HotBody.getOrDefault(p.getUniqueId(),0),"","공격력이 증가합니다",ChatColor.BOLD+" X "+BigDecimal.valueOf(1+fsd.HotBody.getOrDefault(p.getUniqueId(),0)*0.044).setScale(2, RoundingMode.HALF_EVEN),"", "적을 태웁니다", "화염에 면역이 되고 화염계열 저항이 증가합니다", "피해량의 5%를 반사합니다(플레이어는 1%)","","스킬사용후 3초안에 다른스킬을 사용하면","3초동안 열기가 활성화됩니다","열기 활성화 중에는 받는피해가 감소하며","주변 적에게 피해를 줍니다","최대 6중첩",ChatColor.BOLD+" X 0.03D X 중첩수"), 7, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("흐르는용암(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("태양의움켜쥠(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("쌍구(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("연화(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("용암소나기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("화염격(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("타오르는심장(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("불사조의 날갯짓(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("흐르는용암", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 재사용시 흐르는용암을 사용합니다", "(피해량은 분화구 레벨에 비례합니다)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.02*(1+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("태양의움켜쥠", Material.SUNFLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 재사용시 태양의움켜쥠을 사용합니다","(피해량은 불의고리 레벨에 비례합니다)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.6*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.057)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, skillsInv);
				itemset("쌍구", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 한번에 두개의 화염구를 발사합니다"), 11, skillsInv);
				itemset("연화", Material.FIRE_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 화염이 퍼진 적이 주변의", "또다른 적들에게 화염을 퍼뜨립니다"), 12, skillsInv);
				itemset("용암소나기", Material.RED_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 재사용시 용암소나기를 사용합니다", "(피해량은 화염의숨결 레벨에 비례합니다)","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.3*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, skillsInv);
				itemset("화염격", Material.BLAZE_POWDER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 재사용시 화염격을 사용합니다", "(피해량은 살아있는불꽃 레벨에 비례합니다)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.36*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.034)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, skillsInv);
				itemset("타오르는심장", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다"), 16, skillsInv);
				itemset("불사조의 날갯짓", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 웅크리기 + num4","",ChatColor.BOLD+"5 X 3.5D"), 17, skillsInv);

				itemset("화산폭풍(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
				itemset("마그마결정체(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("용암폭발(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("햇빛의창(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
				itemset("태양의힘(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("두번째 태양(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("흐르는용암", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 재사용시 흐르는용암을 사용합니다", "(피해량은 분화구 레벨에 비례합니다)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.02*(1+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("태양의움켜쥠", Material.SUNFLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 재사용시 태양의움켜쥠을 사용합니다","(피해량은 불의고리 레벨에 비례합니다)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.6*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.057)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, skillsInv);
				itemset("쌍구", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 한번에 두개의 화염구를 발사합니다"), 11, skillsInv);
				itemset("연화", Material.FIRE_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 화염이 퍼진 적이 주변의", "또다른 적들에게 화염을 퍼뜨립니다"), 12, skillsInv);

				itemset("용암소나기", Material.RED_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 재사용시 용암소나기를 사용합니다", "(피해량은 화염의숨결 레벨에 비례합니다)","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.3*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, skillsInv);
				itemset("화염격", Material.BLAZE_POWDER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 재사용시 화염격을 사용합니다", "(피해량은 살아있는불꽃 레벨에 비례합니다)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.36*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.034)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, skillsInv);
				itemset("타오르는심장", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다"), 16, skillsInv);
				itemset("불사조의 날갯짓", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 웅크리기 + num4","",ChatColor.BOLD+"5 X 3.5D"), 17, skillsInv);

				itemset("화산폭풍", Material.GILDED_BLACKSTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 재사용시 화산폭풍을 불러옵니다", "(피해량은 불의고리 레벨에 비례합니다)","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.25*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.047)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, skillsInv);
				itemset("마그마결정체", Material.MAGMA_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 재사용시 마그마결정체을 발사합니다", "(피해량은 화염구 레벨에 비례합니다)","",ChatColor.BOLD+"8 X "+demical(0.39*(1+fsd.Fireball.getOrDefault(p.getUniqueId(),0)*0.054))), 20, skillsInv);
				itemset("용암폭발", Material.MAGMA_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 재사용시 용암폭발을 사용합니다","(피해량은 화염의숨결 레벨에 비례합니다)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.24*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, skillsInv);
				itemset("햇빛의창", Material.SHROOMLIGHT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 재사용시 햇빛의창을 사용합니다", "(피해량은 살아있는불꽃 레벨에 비례합니다)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.4*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D, 6 X "+BigDecimal.valueOf(0.153*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 23, skillsInv);
				itemset("태양의힘", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다","불사조의 날갯짓 재사용 대기시간이 감소합니다"), 25, skillsInv);
				itemset("두번째 태양", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [화염]","🖮🖰 웅크리기 + num5","폭발 피해량은 마그마결정체와 동일합니다","",ChatColor.BOLD+"10 X 0.1D + 51*"+demical(0.39*(1+fsd.Fireball.getOrDefault(p.getUniqueId(),0)*0.054))), 26, skillsInv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("스킬포인트", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, skillsInv);

		}
		else {
			itemset("Eruption", Material.CRYING_OBSIDIAN, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 RightClick","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.032*(1+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 0, skillsInv);
			itemset("Ring", Material.FIRE_CORAL_FAN, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Ring.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Sneaking + RightClick","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.2*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 1, skillsInv);
			itemset("Fireball", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Fireball.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 LeftClick + Jump","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.25*(1+fsd.Fireball.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 2, skillsInv);
			itemset("Spread", Material.BLAZE_ROD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Spread.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Sneaking + Hit","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.5*(1+fsd.Spread.getOrDefault(p.getUniqueId(),0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 3, skillsInv);
			itemset("Breath", Material.CAMPFIRE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Breath.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 SwapHand","Maximise Armor While casting","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.4*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 4, skillsInv);
			itemset("AliveFlame", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0),"","SwapHand + Sneaking","",ChatColor.BOLD+"20 X "+BigDecimal.valueOf(0.07*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.016)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 5, skillsInv);
			itemset("HotBody", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.HotBody.getOrDefault(p.getUniqueId(),0),"","Increases Damage",ChatColor.BOLD+" X "+BigDecimal.valueOf(1+fsd.HotBody.getOrDefault(p.getUniqueId(),0)*0.044),"", "Burn enime which attacked by you or attack you", "Get Fire Resistance", "Reflect 5% Damage (1% to Player)","","If you use another skill within","3 seconds after using the skill","Hotbody will be activated for 3s","During Hotbody activation, Increases Armor","and Damages your surroundings", "Max 6 stacks",ChatColor.BOLD+" X 0.03D X stacks"), 7, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("FlowingLava(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("SunClutch(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("DoubleBall(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("FlameChain(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("LavaBoom(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("FireStrike(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("BurningHeart(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("Flapping of Phoenix(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("FlowingLava", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Use FlowingLava When Use Once More", "(Damage Affected By Eruption)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.02*(1+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("SunClutch", Material.SUNFLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Use SunClutch When Use Once More","(Damage Affected By Ring)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.6*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.057)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, skillsInv);
				itemset("DoubleBall", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Shot Two FireBall"), 11, skillsInv);
				itemset("FlameChain", Material.FIRE_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Hit Enemy Spread FireBall Once More"), 12, skillsInv);
				itemset("LavaShower", Material.RED_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Call LavaShower When Use Once More", "(Damage Affected By Breath)","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.3*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, skillsInv);
				itemset("FireStrike", Material.BLAZE_POWDER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Use FireStrike When Use Once More", "(Damage Affected By AliveFlame)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.36*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.034)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, skillsInv);
				itemset("BurningHeart", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor","Increases Hotbody's Armor Increasement"), 16, skillsInv);
				itemset("Flapping of Phoenix", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Sneaking + num4",ChatColor.BOLD+"5 X 3.5D"), 17, skillsInv);

				itemset("VolcanicStorm(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
				itemset("MagmaBlock(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("LavaBoom(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("SunLightSpear(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
				itemset("SolarForce(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("New SunRise(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("FlowingLava", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Use FlowingLava When Use Once More", "(Damage Affected By Eruption)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.02*(1+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("SunClutch", Material.SUNFLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Use SunClutch When Use Once More","(Damage Affected By Ring)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.6*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.057)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, skillsInv);
				itemset("DoubleBall", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Shot Two FireBall"), 11, skillsInv);
				itemset("FlameChain", Material.FIRE_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Hit Enemy Spread FireBall Once More"), 12, skillsInv);
				itemset("LavaShower", Material.RED_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Call LavaShower When Use Once More", "(Damage Affected By Breath)","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.3*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, skillsInv);
				itemset("FireStrike", Material.BLAZE_POWDER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Use FireStrike When Use Once More", "(Damage Affected By AliveFlame)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.36*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.034)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, skillsInv);
				itemset("BurningHeart", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor","Increases Hotbody's Armor Increasement"), 16, skillsInv);
				itemset("Flapping of Phoenix", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Sneaking + num4",ChatColor.BOLD+"5 X 3.5D"), 17, skillsInv);

				itemset("VolcanicStorm", Material.GILDED_BLACKSTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Call VolcanicStorm When Use Once More", "(Damage Affected By Ring)","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.25*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.047)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, skillsInv);
				itemset("MagmaBlock", Material.MAGMA_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Shot MagmaBlock When Use Once More", "(Damage Affected By FireBall)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.39*(1+fsd.Fireball.getOrDefault(p.getUniqueId(),0)*0.054)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, skillsInv);
				itemset("LavaBoom", Material.MAGMA_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Use LavaBoom When Use Once More","(Damage Affected By Breath)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.24*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, skillsInv);
				itemset("SunLightSpear", Material.SHROOMLIGHT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Use SunLightSpear When Use Once More", "(Damage Affected By AliveFlame)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.4*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D, 6 X "+BigDecimal.valueOf(0.153*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 23, skillsInv);
				itemset("SolarForce", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decreases Flapping of Phoenix Cooldown"), 25, skillsInv);
				itemset("New SunRise", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"❈ [Flame]","🖮🖰 Sneaking + num5","Explosion Damage is equal to MagmaBlock","",ChatColor.BOLD+"10 X 0.1D + 51*"+demical(0.39*(1+fsd.Fireball.getOrDefault(p.getUniqueId(),0)*0.054))), 26, skillsInv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("SkillPoints", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, skillsInv);

		}

		Obtained.itemset(p, skillsInv);
		p.openInventory(skillsInv);
	}


}