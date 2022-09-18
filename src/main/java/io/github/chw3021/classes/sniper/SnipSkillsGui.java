package io.github.chw3021.classes.sniper;



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

public class SnipSkillsGui{
	



	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new ItemStack(ID);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		Lore.forEach(l -> {
			l=ChatColor.stripColor(l);
		});
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	public void itemset(String display, ItemStack is, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = is;
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	
	public void Snipskillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		SnipSkillsData ssd = new SnipSkillsData(SnipSkillsData.loadData(path +"/plugins/RPGskills/SnipskillsData.data"));
		Inventory Snipskillsinv = Bukkit.createInventory(null, 54, "Snipskills");
		Obtained.itemset(p, Snipskillsinv);

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("����Ÿ��", Material.CROSSBOW, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Rope.getOrDefault(p.getUniqueId(),0),"","���� + ��Ŭ��", "Master LV.1"), 0, Snipskillsinv);
			itemset("ö��ȭ��", Material.TIPPED_ARROW, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.ArmourPiercingArrow.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�ٶ� �迭]","��ũ���� + �չٲٱ�", 
					"���� ����ü���� "+ BigDecimal.valueOf(1+0.1*ssd.ArmourPiercingArrow.getOrDefault(p.getUniqueId(),0)).setScale(2, RoundingMode.HALF_EVEN)  +"% �� ���ظ� �ݴϴ�", "Master LV.50"), 1, Snipskillsinv);
			itemset("����ź", Material.SNOWBALL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.FlashBomb.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�ٶ� �迭]","���� + �չٲٱ�","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.5*(1+ssd.FlashBomb.getOrDefault(p.getUniqueId(),0)*0.0687)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 2, Snipskillsinv);
			itemset("��������", Material.GLOWSTONE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Flare.getOrDefault(p.getUniqueId(),0),"","��ũ���� + ��Ŭ��", "Master LV.1"), 3, Snipskillsinv);
			itemset("����", Material.FIREWORK_ROCKET, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.AirStrike.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�ٶ� �迭]","���߽� ���ϵ��� �������ϴ�","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.4*(1+ssd.AirStrike.getOrDefault(p.getUniqueId(),0)*0.0321)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 4, Snipskillsinv);
			itemset("����", Material.SPYGLASS, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Camouflage.getOrDefault(p.getUniqueId(),0),"","��ũ����", "Master LV.1"), 5, Snipskillsinv);
			itemset("����", Material.CROSSBOW, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Remodeling.getOrDefault(p.getUniqueId(),0),"",
					"�� �����ϰ� ���� ȭ���� �����ϴ�",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.123*(1+ssd.Remodeling.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D","", 
					"ȭ���� ���뷹���� �����մϴ�",ChatColor.BOLD+"+ "+ssd.Remodeling.getOrDefault(p.getUniqueId(),0)/2,"", 
					"�����ǰ� ������ȣ���� �����Ҽ� �ֽ��ϴ�","","���߹߻� �����ο���", "ȭ����� �߾����� ���̰�", "���� ȭ���� ���ط��� �������� �����մϴ�", "Master LV.50"), 6, Snipskillsinv);
			itemset("��弦", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.HeadShot.getOrDefault(p.getUniqueId(),0),"","���� �Ӹ��κ��� ���߽� �ߵ��˴ϴ�", "��ų������ �������� ������ �������ϴ�", "ö��ȭ��� �ñر�� ������� �ʽ��ϴ�","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.36*(1+ssd.HeadShot.getOrDefault(p.getUniqueId(),0)*0.036)).setScale(2, RoundingMode.HALF_EVEN)), 7, Snipskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("�������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 9, Snipskillsinv);
				itemset("���ȭ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 10, Snipskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 11, Snipskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 12, Snipskillsinv);
				itemset("�������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 13, Snipskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 14, Snipskillsinv);
				itemset("ȸ�Ǳ⵿(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 15, Snipskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 16, Snipskillsinv);
				itemset("������ �ѹ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 17, Snipskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("�������", Material.LEAD, 0, 1, Arrays.asList("����Ÿ�⿡ �����ϸ� ȭ���ϳ���", "��� �����մϴ�","�Ϲ� ȭ�츸 ���� �����մϴ�"), 9, Snipskillsinv);
				itemset("���ȭ��", Material.TIPPED_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ���ȭ���� �߻��մϴ�","(���ط��� ö��ȭ�� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.3*(1+ssd.ArmourPiercingArrow.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Snipskillsinv);
				itemset("����", Material.SMOKER, 0, 1, Arrays.asList("���Է½� ����ź�� ��ô�մϴ�","�����ȿ��� ��ũ����� �������°� �˴ϴ�","(���ӽð��� ����ź ������ ����մϴ�)","",ChatColor.BOLD+""+BigDecimal.valueOf((40+ssd.FlashBomb.getOrDefault(p.getUniqueId(),0)*0.1)/10d).setScale(1, RoundingMode.HALF_EVEN)+"��"), 11, Snipskillsinv);
				itemset("����", Material.GLOW_INK_SAC, 0, 1, Arrays.asList("���� ��� ������ŵ�ϴ�"), 12, Snipskillsinv);
				itemset("�������", Material.TARGET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���߽� �ļӻ���� �̷�����ϴ�", "(���ط��� ���� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*ssd.AirStrike.getOrDefault(p.getUniqueId(),0)*0.05).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Snipskillsinv);
				itemset("����", Material.COPPER_BLOCK, 0, 1, Arrays.asList("��ũ���� + �����۹ٲٱ�(���콺��)��", "��������� ���������ϴ�", "���� ���ð� 1��"), 14, Snipskillsinv);
				itemset("ȸ�Ǳ⵿", Material.GLASS_PANE, 0, 1, Arrays.asList("����� �̵��ӵ��� �����մϴ�"), 15, Snipskillsinv);
				itemset("����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� �����մϴ�"), 16, Snipskillsinv);
				itemset("������ �ѹ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("��ũ���� + �����۴�����","",ChatColor.BOLD+"+ 10.0D"), 17, Snipskillsinv);

				itemset("��������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 18, Snipskillsinv);
				itemset("����������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 19, Snipskillsinv);
				itemset("�����װ�����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 20, Snipskillsinv);
				itemset("���ϻ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 23, Snipskillsinv);
				itemset("ħ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 25, Snipskillsinv);
				itemset("�������д�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 26, Snipskillsinv);
			}
			else {
				itemset("�������", Material.LEAD, 0, 1, Arrays.asList("����Ÿ�⿡ �����ϸ� ȭ���ϳ���", "��� �����մϴ�","�Ϲ� ȭ�츸 ���� �����մϴ�"), 9, Snipskillsinv);
				itemset("���ȭ��", Material.TIPPED_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ���ȭ���� �߻��մϴ�","(���ط��� ö��ȭ�� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.3*(1+ssd.ArmourPiercingArrow.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Snipskillsinv);
				itemset("����", Material.SMOKER, 0, 1, Arrays.asList("���Է½� ����ź�� ��ô�մϴ�","�����ȿ��� ��ũ����� �������°� �˴ϴ�","(���ӽð��� ����ź ������ ����մϴ�)","",ChatColor.BOLD+""+BigDecimal.valueOf((40+ssd.FlashBomb.getOrDefault(p.getUniqueId(),0)*0.1)/10d).setScale(1, RoundingMode.HALF_EVEN)+"��"), 11, Snipskillsinv);
				itemset("����", Material.GLOW_INK_SAC, 0, 1, Arrays.asList("���� ��� ������ŵ�ϴ�"), 12, Snipskillsinv);
				itemset("�������", Material.TARGET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���߽� �ļӻ���� �̷�����ϴ�", "(���ط��� ���� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*ssd.AirStrike.getOrDefault(p.getUniqueId(),0)*0.05).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Snipskillsinv);
				itemset("����", Material.COPPER_BLOCK, 0, 1, Arrays.asList("��ũ���� + �����۹ٲٱ�(���콺��)��", "��������� ���������ϴ�", "���� ���ð� 1��"), 14, Snipskillsinv);
				itemset("ȸ�Ǳ⵿", Material.GLASS_PANE, 0, 1, Arrays.asList("����� �̵��ӵ��� �����մϴ�"), 15, Snipskillsinv);
				itemset("����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� �����մϴ�"), 16, Snipskillsinv);
				itemset("������ �ѹ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("��ũ���� + �����۴�����","",ChatColor.BOLD+"+ 10.0D"), 17, Snipskillsinv);

				itemset("��������", Material.CROSSBOW, 0, 1, Arrays.asList("�������� ȭ���ϳ��� �����մϴ�","���� �Ʒ��� ������ ���ڸ����� �����մϴ�","�Ϲ� ȭ�츸 ���� �����մϴ�"), 18, Snipskillsinv);
				itemset("����������", Material.BEDROCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ������������ �߻��մϴ�","���� ���¿� ����� ������ ���ظ� �ݴϴ�","(���ط��� ö��ȭ�� ������ ����մϴ�)","",ChatColor.BOLD+
						" X ("+BigDecimal.valueOf(1.3*(1+ssd.ArmourPiercingArrow.getOrDefault(p.getUniqueId(),0)*0.1)).setScale(2, RoundingMode.HALF_EVEN)+"X (1+������)) D"), 19, Snipskillsinv);
				itemset("�����װ�����", Material.TNT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� �����װ������� ��û�մϴ�", "(���ط��� ���� ������ ����մϴ�)",
						"",ChatColor.BOLD+"12 X "+BigDecimal.valueOf(0.34*(1 + ssd.FlashBomb.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, Snipskillsinv);
				itemset("���ϻ�", Material.ELYTRA, 0, 1, Arrays.asList("��ũ���� ���� ��������ȿ���� ����ϴ�"), 23, Snipskillsinv);
				itemset("ħ��", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�","������ �ѹ� ������ð��� �����մϴ�", "��弦�� �׻� �ߵ��˴ϴ�"), 25, Snipskillsinv);
				itemset("�������д�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("�޸��� + �����۴�����","",ChatColor.BOLD+"40 X 0.5D, 6.5D"), 26, Snipskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Snipskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+ssd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"), 35, Snipskillsinv);

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
				itemset("The Last One", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem","",ChatColor.BOLD+"+ 10.0D"), 17, Snipskillsinv);

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
				itemset("The Last One", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem","",ChatColor.BOLD+"+ 10.0D"), 17, Snipskillsinv);

				itemset("CrawlingCharge", Material.CROSSBOW, 0, 1, Arrays.asList("Charge Arrow Instantly When Use Crawl","Charge in position if [Scroll Down]","You Can Only Charge Normal Arrow"), 18, Snipskillsinv);
				itemset("Destroyer", Material.BEDROCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Shot Destroyer When Use Once More","Higher Damage to Armored Enemies","(Damage Affected By ArmourPiercingArrow)","",ChatColor.BOLD+
						" X ("+BigDecimal.valueOf(1.3*(1+ssd.ArmourPiercingArrow.getOrDefault(p.getUniqueId(),0)*0.1)).setScale(2, RoundingMode.HALF_EVEN)+"X (1+Enemy's Armor)) D"), 19, Snipskillsinv);
				itemset("DangerClose", Material.TNT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Request DangerClose When Use Once More", "(Damage Affected By AirStrike)",
						"",ChatColor.BOLD+"12 X "+BigDecimal.valueOf(0.34*(1 + ssd.FlashBomb.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, Snipskillsinv);
				itemset("Parachute", Material.ELYTRA, 0, 1, Arrays.asList("Get Slow Falling Effect While Sneaking"), 23, Snipskillsinv);
				itemset("Composure", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decrease The Last One Cooldown", "HeadShot Always Activate"), 25, Snipskillsinv);
				itemset("Team Eagle", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sprinting + ThrowItem","",ChatColor.BOLD+"40 X 0.5D, 6.5D"), 26, Snipskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Snipskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+ssd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, Snipskillsinv);

		}
		
		
		p.openInventory(Snipskillsinv);
	}


}
