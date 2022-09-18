package io.github.chw3021.classes.firemage;



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
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class FireSkillsGui{
	



	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new ItemStack(ID,stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void FIreSkillsinv(Player p)
	{
		Inventory FIreskillsinv = Bukkit.createInventory(null, 54, "FIreskills");
		
		Obtained.itemset(p, FIreskillsinv);

	    String path = new File("").getAbsolutePath();
		FireSkillsData fsd = new FireSkillsData(FireSkillsData.loadData(path +"/plugins/RPGskills/FireSkillsData.data"));
		
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("��ȭ��", Material.CRYING_OBSIDIAN, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[ȭ�� �迭]","��Ŭ��","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.032*(1+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 0, FIreskillsinv);
			itemset("���ǰ�", Material.FIRE_CORAL_FAN, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Ring.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[ȭ�� �迭]","��ũ���� + ��Ŭ��","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.2*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 1, FIreskillsinv);
			itemset("ȭ����", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Fireball.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[ȭ�� �迭]","��Ŭ�� + ����","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.25*(1+fsd.Fireball.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 2, FIreskillsinv);
			itemset("Ȯ��", Material.BLAZE_ROD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Spread.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[ȭ�� �迭]","��ũ���� + ��������","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.5*(1+fsd.Spread.getOrDefault(p.getUniqueId(),0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 3, FIreskillsinv);
			itemset("ȭ���Ǽ���", Material.CAMPFIRE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Breath.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[ȭ�� �迭]","�չٲٱ�","������ ������ �ִ�ġ�� �˴ϴ�","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.4*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 4, FIreskillsinv);
			itemset("����ִºҲ�", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[ȭ�� �迭]","�չٲٱ� + ��ũ����","",ChatColor.BOLD+"20 X "+BigDecimal.valueOf(0.07*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.016)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 5, FIreskillsinv);
			itemset("����", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.HotBody.getOrDefault(p.getUniqueId(),0),"","���ݷ��� �����մϴ�",ChatColor.BOLD+" X "+BigDecimal.valueOf(1+fsd.HotBody.getOrDefault(p.getUniqueId(),0)*0.044).setScale(2, RoundingMode.HALF_EVEN),"", "���� �¿�ϴ�", "ȭ���� �鿪�� �ǰ� ȭ���迭 ������ �����մϴ�", "���ط��� 5%�� �ݻ��մϴ�(�÷��̾�� 1%)","","��ų����� 3�ʾȿ� �ٸ���ų�� ����ϸ�","3�ʵ��� ���Ⱑ Ȱ��ȭ�˴ϴ�","���� Ȱ��ȭ �߿��� �޴����ذ� �����ϸ�","�ֺ� ������ ���ظ� �ݴϴ�","�ִ� 6��ø",ChatColor.BOLD+" X 0.03D X ��ø��"), 7, FIreskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("�帣�¿��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 9, FIreskillsinv);
				itemset("�¾��ǿ�����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 10, FIreskillsinv);
				itemset("�ֱ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 11, FIreskillsinv);
				itemset("��ȭ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 12, FIreskillsinv);
				itemset("��ϼҳ���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 13, FIreskillsinv);
				itemset("ȭ����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 14, FIreskillsinv);
				itemset("Ÿ�����½���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 16, FIreskillsinv);
				itemset("�һ����� ������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 17, FIreskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("�帣�¿��", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","����� �帣�¿���� ����մϴ�", "(���ط��� ��ȭ�� ������ ����մϴ�)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.02*(1+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, FIreskillsinv);
				itemset("�¾��ǿ�����", Material.SUNFLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","����� �¾��ǿ������� ����մϴ�","(���ط��� ���ǰ� ������ ����մϴ�)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.6*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.057)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, FIreskillsinv);
				itemset("�ֱ�", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","�ѹ��� �ΰ��� ȭ������ �߻��մϴ�"), 11, FIreskillsinv);
				itemset("��ȭ", Material.FIRE_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","ȭ���� ���� ���� �ֺ���", "�Ǵٸ� ���鿡�� ȭ���� �۶߸��ϴ�"), 12, FIreskillsinv);
				itemset("��ϼҳ���", Material.RED_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","����� ��ϼҳ��⸦ ����մϴ�", "(���ط��� ȭ���Ǽ��� ������ ����մϴ�)","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.3*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, FIreskillsinv);
				itemset("ȭ����", Material.BLAZE_POWDER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","����� ȭ������ ����մϴ�", "(���ط��� ����ִºҲ� ������ ����մϴ�)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.36*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.034)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, FIreskillsinv);
				itemset("Ÿ�����½���", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�"), 16, FIreskillsinv);
				itemset("�һ����� ������", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","��ũ���� + �����۴�����","",ChatColor.BOLD+"5 X 3.5D"), 17, FIreskillsinv);
				
				itemset("ȭ����ǳ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 19, FIreskillsinv);
				itemset("���׸�����ü(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 20, FIreskillsinv);
				itemset("�������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 22, FIreskillsinv);
				itemset("�޺���â(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 23, FIreskillsinv);
				itemset("�¾�����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 25, FIreskillsinv);
				itemset("�ι�° �¾�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 26, FIreskillsinv);
			}
			else {
				itemset("�帣�¿��", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","����� �帣�¿���� ����մϴ�", "(���ط��� ��ȭ�� ������ ����մϴ�)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.02*(1+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, FIreskillsinv);
				itemset("�¾��ǿ�����", Material.SUNFLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","����� �¾��ǿ������� ����մϴ�","(���ط��� ���ǰ� ������ ����մϴ�)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.6*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.057)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, FIreskillsinv);
				itemset("�ֱ�", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","�ѹ��� �ΰ��� ȭ������ �߻��մϴ�"), 11, FIreskillsinv);
				itemset("��ȭ", Material.FIRE_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","ȭ���� ���� ���� �ֺ���", "�Ǵٸ� ���鿡�� ȭ���� �۶߸��ϴ�"), 12, FIreskillsinv);

				itemset("��ϼҳ���", Material.RED_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","����� ��ϼҳ��⸦ ����մϴ�", "(���ط��� ȭ���Ǽ��� ������ ����մϴ�)","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.3*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, FIreskillsinv);
				itemset("ȭ����", Material.BLAZE_POWDER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","����� ȭ������ ����մϴ�", "(���ط��� ����ִºҲ� ������ ����մϴ�)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.36*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.034)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, FIreskillsinv);
				itemset("Ÿ�����½���", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�"), 16, FIreskillsinv);
				itemset("�һ����� ������", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","��ũ���� + �����۴�����","",ChatColor.BOLD+"5 X 3.5D"), 17, FIreskillsinv);
				
				itemset("ȭ����ǳ", Material.GILDED_BLACKSTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","����� ȭ����ǳ�� �ҷ��ɴϴ�", "(���ط��� ���ǰ� ������ ����մϴ�)","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.25*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.047)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, FIreskillsinv);
				itemset("���׸�����ü", Material.MAGMA_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","����� ���׸�����ü�� �߻��մϴ�", "(���ط��� ȭ���� ������ ����մϴ�)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.39*(1+fsd.Fireball.getOrDefault(p.getUniqueId(),0)*0.054)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, FIreskillsinv);
				itemset("�������", Material.MAGMA_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","����� ��������� ����մϴ�","(���ط��� ȭ���Ǽ��� ������ ����մϴ�)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.24*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, FIreskillsinv);
				itemset("�޺���â", Material.SHROOMLIGHT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","����� �޺���â�� ����մϴ�", "(���ط��� ����ִºҲ� ������ ����մϴ�)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.4*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D, 6 X "+BigDecimal.valueOf(0.153*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 23, FIreskillsinv);
				itemset("�¾�����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�","�һ����� ������ ���� ���ð��� �����մϴ�"), 25, FIreskillsinv);
				itemset("�ι�° �¾�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[ȭ�� �迭]","�޸��� + �����۴�����","",ChatColor.BOLD+"10 X 2.5D"), 26, FIreskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, FIreskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"), 35, FIreskillsinv);
		
		}
		else {
			itemset("Eruption", Material.CRYING_OBSIDIAN, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Flame]","RightClick","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.032*(1+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 0, FIreskillsinv);
			itemset("Ring", Material.FIRE_CORAL_FAN, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Ring.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Flame]","Sneaking + RightClick","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.2*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 1, FIreskillsinv);
			itemset("Fireball", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Fireball.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Flame]","LeftClick + Jump","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.25*(1+fsd.Fireball.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 2, FIreskillsinv);
			itemset("Spread", Material.BLAZE_ROD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Spread.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Flame]","Sneaking + Hit","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.5*(1+fsd.Spread.getOrDefault(p.getUniqueId(),0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 3, FIreskillsinv);
			itemset("Breath", Material.CAMPFIRE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Breath.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Flame]","SwapHand","Maximise Armor While casting","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.4*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 4, FIreskillsinv);
			itemset("AliveFlame", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0),"","SwapHand + Sneaking","",ChatColor.BOLD+"20 X "+BigDecimal.valueOf(0.07*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.016)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 5, FIreskillsinv);
			itemset("HotBody", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.HotBody.getOrDefault(p.getUniqueId(),0),"","Increases Damage",ChatColor.BOLD+" X "+BigDecimal.valueOf(1+fsd.HotBody.getOrDefault(p.getUniqueId(),0)*0.044),"", "Burn enime which attacked by you or attack you", "Get Fire Resistance", "Reflect 5% Damage (1% to Player)","","If you use another skill within","3 seconds after using the skill","Hotbody will be activated for 3s","During Hotbody activation, Increases Armor","and Damages your surroundings", "Max 6 stacks",ChatColor.BOLD+" X 0.03D X stacks"), 7, FIreskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("FlowingLava(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, FIreskillsinv);
				itemset("SunClutch(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, FIreskillsinv);
				itemset("DoubleBall(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, FIreskillsinv);
				itemset("FlameChain(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, FIreskillsinv);
				itemset("LavaBoom(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, FIreskillsinv);
				itemset("FireStrike(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, FIreskillsinv);
				itemset("BurningHeart(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, FIreskillsinv);
				itemset("Phoenix Flap(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, FIreskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("FlowingLava", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Use FlowingLava When Use Once More", "(Damage Affected By Eruption)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.02*(1+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, FIreskillsinv);
				itemset("SunClutch", Material.SUNFLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Use SunClutch When Use Once More","(Damage Affected By Ring)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.6*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.057)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, FIreskillsinv);
				itemset("DoubleBall", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Shot Two FireBall"), 11, FIreskillsinv);
				itemset("FlameChain", Material.FIRE_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Hit Enemy Spread FireBall Once More"), 12, FIreskillsinv);
				itemset("LavaShower", Material.RED_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Call LavaShower When Use Once More", "(Damage Affected By Breath)","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.3*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, FIreskillsinv);
				itemset("FireStrike", Material.BLAZE_POWDER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Use FireStrike When Use Once More", "(Damage Affected By AliveFlame)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.36*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.034)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, FIreskillsinv);
				itemset("BurningHeart", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor","Increases Hotbody's Armor Increasement"), 16, FIreskillsinv);
				itemset("Phoenix Flap", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Sneaking + ThrowItem",ChatColor.BOLD+"5 X 3.5D"), 17, FIreskillsinv);
				
				itemset("VolcanicStorm(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, FIreskillsinv);
				itemset("MagmaBlock(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, FIreskillsinv);
				itemset("LavaBoom(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, FIreskillsinv);
				itemset("SunLightSpear(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, FIreskillsinv);
				itemset("SolarForce(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, FIreskillsinv);
				itemset("New SunRise(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, FIreskillsinv);
			}
			else {
				itemset("FlowingLava", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Use FlowingLava When Use Once More", "(Damage Affected By Eruption)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.02*(1+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, FIreskillsinv);
				itemset("SunClutch", Material.SUNFLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Use SunClutch When Use Once More","(Damage Affected By Ring)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.6*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.057)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, FIreskillsinv);
				itemset("DoubleBall", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Shot Two FireBall"), 11, FIreskillsinv);
				itemset("FlameChain", Material.FIRE_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Hit Enemy Spread FireBall Once More"), 12, FIreskillsinv);
				itemset("LavaShower", Material.RED_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Call LavaShower When Use Once More", "(Damage Affected By Breath)","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.3*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, FIreskillsinv);
				itemset("FireStrike", Material.BLAZE_POWDER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Use FireStrike When Use Once More", "(Damage Affected By AliveFlame)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.36*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.034)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, FIreskillsinv);
				itemset("BurningHeart", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor","Increases Hotbody's Armor Increasement"), 16, FIreskillsinv);
				itemset("Phoenix Flap", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Sneaking + ThrowItem",ChatColor.BOLD+"5 X 3.5D"), 17, FIreskillsinv);
				
				itemset("VolcanicStorm", Material.GILDED_BLACKSTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Call VolcanicStorm When Use Once More", "(Damage Affected By Ring)","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.25*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.047)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, FIreskillsinv);
				itemset("MagmaBlock", Material.MAGMA_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Shot MagmaBlock When Use Once More", "(Damage Affected By FireBall)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.39*(1+fsd.Fireball.getOrDefault(p.getUniqueId(),0)*0.054)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, FIreskillsinv);
				itemset("LavaBoom", Material.MAGMA_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Use LavaBoom When Use Once More","(Damage Affected By Breath)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.24*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, FIreskillsinv);
				itemset("SunLightSpear", Material.SHROOMLIGHT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Use SunLightSpear When Use Once More", "(Damage Affected By AliveFlame)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.4*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D, 6 X "+BigDecimal.valueOf(0.153*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 23, FIreskillsinv);
				itemset("SolarForce", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decreases Phoenix Flap Cooldown"), 25, FIreskillsinv);
				itemset("New SunRise", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Sprinting + ThrowItem","",ChatColor.BOLD+"10 X 2.5D"), 26, FIreskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, FIreskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, FIreskillsinv);
		
		}
		
		p.openInventory(FIreskillsinv);
	}


}
