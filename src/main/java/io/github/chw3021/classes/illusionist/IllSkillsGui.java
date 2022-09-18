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
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class IllSkillsGui{
	



	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new ItemStack(ID);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		Lore.forEach(l -> {
			l=ChatColor.RESET+l;
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
		Lore.forEach(l -> {
			l=ChatColor.RESET+l;
		});
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void IllSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		IllSkillsData isd = new IllSkillsData(IllSkillsData.loadData(path +"/plugins/RPGskills/IllSkillsData.data"));
		Inventory Illskillsinv = Bukkit.createInventory(null, 54, "Illskills");
		Obtained.itemset(p, Illskillsinv);
		
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("�ٲ�ġ��", Material.STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Switch.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�ٶ� �迭]","�������� + ��ũ����", "Master LV.1"), 0, Illskillsinv);
			itemset("���Ӽ�", Material.STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Trick.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�ٶ� �迭]","��Ŭ�� + ����","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.66*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.0535)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 1, Illskillsinv);
			itemset("�������", Material.JACK_O_LANTERN, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.JackoLantern.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�ٶ� �迭]","�չٲٱ�","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.68*(1+isd.JackoLantern.getOrDefault(p.getUniqueId(),0)*0.062)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 2, Illskillsinv);
			itemset("�ְ�", Material.DEBUG_STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Distortion.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[�ٶ� �迭]","��Ŭ��","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.626*(1+isd.Distortion.getOrDefault(p.getUniqueId(),0)*0.0835)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 3, Illskillsinv);
			itemset("����", Material.DEBUG_STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Paradox.getOrDefault(p.getUniqueId(),0),"","��ũ���� + ��Ŭ��","�ñر⸦ ������ ��� �����","���� ���ð��� �ʱ�ȭ �մϴ�","�طο� ȿ���� ��� �����մϴ�","ü�°� ��⸦ ��� ȸ���մϴ�", "Master LV.1"), 4, Illskillsinv);
			itemset("����ƺ�", Material.ARMOR_STAND, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.FakeDoll.getOrDefault(p.getUniqueId(),0),"","�չٲٱ� + ��ũ����", "�ֺ� ������ �����մϴ�", "Master LV.1"), 5, Illskillsinv);
			itemset("��¦��", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Surprise.getOrDefault(p.getUniqueId(),0),"","����ȭ �����϶� ���ݷ��� �����մϴ�","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1+isd.Surprise.getOrDefault(p.getUniqueId(),0)*0.056).setScale(2, RoundingMode.HALF_EVEN)), 7, Illskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("ȥ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 9, Illskillsinv);
				itemset("���ڸ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 10, Illskillsinv);
				itemset("Ȯ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 11, Illskillsinv);
				itemset("�ڼ���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 12, Illskillsinv);
				itemset("���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 13, Illskillsinv);
				itemset("��ġ����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 14, Illskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 16, Illskillsinv);
				itemset("������ ȯ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 17, Illskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("ȥ��", Material.END_PORTAL_FRAME, 0, 1, Arrays.asList("���� ª�� �����մϴ�"), 9, Illskillsinv);
				itemset("���ڸ�", Material.CHORUS_FLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ���ڸ��� ����մϴ�","(���ط��� ���Ӽ� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.0535)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Illskillsinv);
				itemset("Ȯ��", Material.COMPARATOR, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� Ȯ�븦 ����մϴ�","(���ط��� ������� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+isd.JackoLantern.getOrDefault(p.getUniqueId(),0)*0.062)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Illskillsinv);
				itemset("�ڼ���", Material.SKULL_BANNER_PATTERN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� �ڼ��⸦ ����մϴ�","(���ط��� �ְ� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+isd.Distortion.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Illskillsinv);
				itemset("���", Material.CREEPER_HEAD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","�����ִ� ����ƺ���� ���߽�ŵ�ϴ�","(���ط��� ���Ӽ� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.66*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.0535)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Illskillsinv);
				itemset("��ġ����", Material.SKELETON_SKULL, 0, 1, Arrays.asList("���Է½� ����ƺ�� �ڽ��� ��ġ�� �ٲߴϴ�"), 14, Illskillsinv);
				itemset("����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� �����մϴ�"), 16, Illskillsinv);
				itemset("������ ȯ��", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","��ũ���� + �����۴�����","",ChatColor.BOLD+" X 15.6D"), 17, Illskillsinv);

				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 18, Illskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 19, Illskillsinv);
				itemset("�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 20, Illskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 21, Illskillsinv);
				itemset("�ָ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 22, Illskillsinv);
				itemset("�ʰ���������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 25, Illskillsinv);
				itemset("���۵�����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 26, Illskillsinv);
			}
			else {
				itemset("ȥ��", Material.END_PORTAL_FRAME, 0, 1, Arrays.asList("���� ª�� �����մϴ�"), 9, Illskillsinv);
				itemset("���ڸ�", Material.CHORUS_FLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ���ڸ��� ����մϴ�","(���ط��� ���Ӽ� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.0535)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Illskillsinv);
				itemset("Ȯ��", Material.COMPARATOR, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� Ȯ�븦 ����մϴ�","(���ط��� ������� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+isd.JackoLantern.getOrDefault(p.getUniqueId(),0)*0.062)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Illskillsinv);
				itemset("�ڼ���", Material.SKULL_BANNER_PATTERN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� �ڼ��⸦ ����մϴ�","(���ط��� �ְ� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+isd.Distortion.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Illskillsinv);
				itemset("���", Material.CREEPER_HEAD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","�����ִ� ����ƺ���� ���߽�ŵ�ϴ�","(���ط��� ���Ӽ� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.66*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.0535)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Illskillsinv);
				itemset("��ġ����", Material.SKELETON_SKULL, 0, 1, Arrays.asList("���Է½� ����ƺ�� �ڽ��� ��ġ�� �ٲߴϴ�"), 14, Illskillsinv);
				itemset("����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� �����մϴ�"), 16, Illskillsinv);
				itemset("������ ȯ��", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","��ũ���� + �����۴�����","",ChatColor.BOLD+" X 15.6D"), 17, Illskillsinv);

				itemset("����", Material.CARROT_ON_A_STICK, 0, 1, Arrays.asList("�ֺ����� ����� �����մϴ�"), 18, Illskillsinv);
				itemset("����", Material.GLASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ������ ����մϴ�","(���ط��� ���Ӽ� ������ ����մϴ�)","",ChatColor.BOLD+"21 X "+BigDecimal.valueOf(0.24*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, Illskillsinv);
				itemset("�", Material.CLAY_BALL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ��� ����մϴ�","(���ط��� ������� ������ ����մϴ�)","",ChatColor.BOLD+"21 X "+BigDecimal.valueOf(0.15*(1+isd.JackoLantern.getOrDefault(p.getUniqueId(),0)*0.022)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, Illskillsinv);
				itemset("����", Material.BRAIN_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ������ ����մϴ�","(���ط��� �ְ� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+isd.Distortion.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 21, Illskillsinv);
				itemset("�ָ�", Material.CANDLE, 0, 1, Arrays.asList("����ƺ� �ֺ��� ������ ���ϴ�","��� ���� ������ ������","�� ���� ���ظ� �԰� ����ϴ�"), 22, Illskillsinv);
				itemset("�ʰ���������", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�","������ ȯ�� ���� ���ð��� �����մϴ�","","��¦� ������ �ƴϿ��� �ߵ��˴ϴ�"), 25, Illskillsinv);
				itemset("���۵�����", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","�޸��� + �����۴�����", "���۵� ������ ���� �����ϳ��� �ֽ��ϴ�", "������ ��ų�� ���� ���ظ� �������ֽ��ϴ�", "6���� ���Ϳ� ���ÿ� �������� �̵��ϱ���","Ž���Ǿ��� �ֺ� ������ �����մϴ�", "", "���ݷ��� �������� ������ ���ط��� ����մϴ�",ChatColor.BOLD+"(�������� ���� ���ط�) X 0.005D"), 26, Illskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Illskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+isd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"), 35, Illskillsinv);
		}
		else {
			itemset("Switch", Material.STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Switch.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","Hit + Sneaking", "Master LV.1"), 0, Illskillsinv);
			itemset("Trick", Material.STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Trick.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","LeftClick + Jump","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.66*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.0535)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 1, Illskillsinv);
			itemset("JackoLantern", Material.JACK_O_LANTERN, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.JackoLantern.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","SwapHand","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.68*(1+isd.JackoLantern.getOrDefault(p.getUniqueId(),0)*0.062)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 2, Illskillsinv);
			itemset("Distortion", Material.DEBUG_STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Distortion.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Wind]","Rightclick","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.626*(1+isd.Distortion.getOrDefault(p.getUniqueId(),0)*0.0835)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 3, Illskillsinv);
			itemset("Paradox", Material.DEBUG_STICK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.Paradox.getOrDefault(p.getUniqueId(),0),"","Sneaking + Rightclick","Reset All CoolDown","Remove negetive Effect","Reset HP And Hunger", "Master LV.1"), 4, Illskillsinv);
			itemset("FakeDoll", Material.ARMOR_STAND, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+isd.FakeDoll.getOrDefault(p.getUniqueId(),0),"","SwapHand + Sneaking", "Taunts near by enemies", "Master LV.1"), 5, Illskillsinv);
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
				itemset("Change", Material.SKELETON_SKULL, 0, 1, Arrays.asList("Switchs Pos with FakeDoll When use Once More"), 14, Illskillsinv);
				itemset("Manipulation", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16, Illskillsinv);
				itemset("The Void", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Sneaking + ThrowItem","",ChatColor.BOLD+" X 15.6D"), 17, Illskillsinv);

				itemset("Misdirection(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, Illskillsinv);
				itemset("Penetration(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, Illskillsinv);
				itemset("Juggling(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, Illskillsinv);
				itemset("MindControl(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, Illskillsinv);
				itemset("Hypnosis(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, Illskillsinv);
				itemset("ESP(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Illskillsinv);
				itemset("FakeDimension(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Illskillsinv);
			}
			else {
				itemset("Confusion", Material.END_PORTAL_FRAME, 0, 1, Arrays.asList("Holds Hit Enemy Shortly"), 9, Illskillsinv);
				itemset("Encore", Material.CHORUS_FLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use Encore When use Once More","(Damage Affected By Trick)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.0535)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Illskillsinv);
				itemset("Magnify", Material.COMPARATOR, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use Magnify When use Once More","(Damage Affected By JackoLantern)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+isd.JackoLantern.getOrDefault(p.getUniqueId(),0)*0.062)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Illskillsinv);
				itemset("Shuffle", Material.SKULL_BANNER_PATTERN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use Shuffle When use Once More","(Damage Affected By Distortion)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+isd.Distortion.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Illskillsinv);
				itemset("Gimmick", Material.CREEPER_HEAD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Explodes Remaining FakeDoll","(Damage Affected By Trick)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.66*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.0535)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Illskillsinv);
				itemset("Change", Material.SKELETON_SKULL, 0, 1, Arrays.asList("Switchs Pos with FakeDoll When use Once More"), 14, Illskillsinv);
				itemset("Manipulation", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16, Illskillsinv);
				itemset("The Void", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Sneaking + ThrowItem","",ChatColor.BOLD+" X 15.6D"), 17, Illskillsinv);

				itemset("Misdirection", Material.CARROT_ON_A_STICK, 0, 1, Arrays.asList("Near By Enemies will Attack Hit Target"), 18, Illskillsinv);
				itemset("Penetration", Material.GLASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use MindControl When use Once More","(Damage Affected By JackoLantern)","",ChatColor.BOLD+"21 X "+BigDecimal.valueOf(0.24*(1+isd.Trick.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, Illskillsinv);
				itemset("Juggling", Material.CLAY_BALL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use Penetration When use Once More","(Damage Affected By Trick)","",ChatColor.BOLD+"21 X "+BigDecimal.valueOf(0.15*(1+isd.JackoLantern.getOrDefault(p.getUniqueId(),0)*0.022)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, Illskillsinv);
				itemset("MindControl", Material.BRAIN_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use Juggling When use Once More","(Damage Affected By Distortion)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+isd.Distortion.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 21, Illskillsinv);
				itemset("Hypnosis", Material.CANDLE, 0, 1, Arrays.asList("Put the enemies surrounding the doll to sleep","Sleeping Enemies Get More Damage From You","Hit Enemies will wake up"), 22, Illskillsinv);
				itemset("ESP", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decrease The Void Cooldown","","Surprise Always Activates", "Even If You Aren't Invisible"), 25, Illskillsinv);
				itemset("FakeDimension", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Sprinting + ThrowItem", "When You Warp to FakeDimension, There will be a Doll", "You Can Attack the Doll With Skills", "After 6 seconds, You'll Attack Enemies", "that have been Scaned Before Warping","", "Attack power is affected by the amount of", "Damage you inflicted on the doll","",ChatColor.BOLD+"(Damage Inflicted On the Doll) X 0.005D"), 26, Illskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Illskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+isd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, Illskillsinv);
		}
		
	
		p.openInventory(Illskillsinv);
	}


}
