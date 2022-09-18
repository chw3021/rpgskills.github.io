package io.github.chw3021.classes.medic;



import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class MedSkillsGui{
	

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
	
	public void Medskillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		MedSkillsData ssd = new MedSkillsData(MedSkillsData.loadData(path +"/plugins/RPGskills/MedskillsData.data"));
		Inventory Medskillsinv = Bukkit.createInventory(null, 54, "Medskills");
		Obtained.itemset(p, Medskillsinv);
		

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			
			itemset("ġ������", Material.TIPPED_ARROW, 1, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"[���� �迭]","���� + ��Ŭ��","",ChatColor.BOLD+" X (0.32D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.316).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 0, Medskillsinv);
			itemset("����", Material.TIPPED_ARROW, 2, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Decontamination.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"[���� �迭]","��ũ���� + �չٲٱ�","�طο� ȿ���� �����մϴ�","",ChatColor.BOLD+"15 X (0.15D + "+BigDecimal.valueOf(ssd.Decontamination.getOrDefault(p.getUniqueId(),1)*0.16).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 1, Medskillsinv);
			itemset("����īƮ", Material.TIPPED_ARROW, 3, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.SupplyCart.getOrDefault(p.getUniqueId(),1),"","���� + �չٲٱ�",
					"��Ƽ���鿡�� �ӵ�,����,�� ȿ���� �ݴϴ�("+1+ssd.SupplyCart.getOrDefault(p.getUniqueId(),0)/2+"����, "+(50+50*ssd.SupplyCart.getOrDefault(p.getUniqueId(),0))/20+"��", "Master LV.5"), 2, Medskillsinv);
			itemset("����ó", Material.TIPPED_ARROW, 4, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Hideout.getOrDefault(p.getUniqueId(),1),"","��ũ����", "Master LV.1"), 3, Medskillsinv);
			itemset("ȭ��ġ��", Material.TIPPED_ARROW, 5, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.ArrowClinic.getOrDefault(p.getUniqueId(),1),"",
					"������ ��Ƽ���� ġ���մϴ�", "�ִ�ü�� X (0.05 + "+BigDecimal.valueOf(0.05+ssd.ArrowClinic.getOrDefault(p.getUniqueId(),1)*0.005+ssd.Medicine.getOrDefault(p.getUniqueId(),1)*0.004).setScale(2, RoundingMode.HALF_EVEN)+")","",
					ChatColor.UNDERLINE+"[�ٶ� �迭]","ȭ�쿡 ���� ���� ���н���" ,"�޴����ذ� "+BigDecimal.valueOf(0.03*ssd.ArrowClinic.getOrDefault(p.getUniqueId(),1)).setScale(2, RoundingMode.HALF_EVEN)+"��ŭ �����ϰ� �մϴ� (�ִ� 1.9)", "��ų������ �������� �� ���� ��ø�˴ϴ�", "Master LV.30"), 4, Medskillsinv);
			itemset("�ڵ���������", Material.CROSSBOW, 6, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.AED.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"[�ٶ� �迭]","��ũ���� + ��Ŭ��","",ChatColor.BOLD+" X (0.3D + "+BigDecimal.valueOf(ssd.AED.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 5, Medskillsinv);
			itemset("����", Material.KNOWLEDGE_BOOK, 6, 1, Arrays.asList(ChatColor.AQUA+ "�нú꽺ų","","��Ƽ���� ������ ���ϴ� ���ظ� ������,","5�ʵ��� ��� ���¿� �����ϴ�","�̵��ӵ� ȿ���� ��� �ڵ��������� ��ų��","�ش� ��Ƽ���� �����Ҽ� �ֽ��ϴ�"), 6, Medskillsinv);
			itemset("����", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Medicine.getOrDefault(p.getUniqueId(),1),"","���ݷ°� ȸ������ ����մϴ�","",ChatColor.BOLD+" + "+BigDecimal.valueOf(ssd.Medicine.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)), 7, Medskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 9, Medskillsinv);
				itemset("��ȣ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 10, Medskillsinv);
				itemset("�������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 11, Medskillsinv);
				itemset("������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 12, Medskillsinv);
				itemset("������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 13, Medskillsinv);
				itemset("�̼��ٴ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 14, Medskillsinv);
				itemset("�ļ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 15, Medskillsinv);
				itemset("����óġ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 16, Medskillsinv);
				itemset("ī�弼�콺 ž(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 17, Medskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("����", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","�ֺ� ������ �����ϴ�","(���ݷ��� ġ������ ������ ����մϴ�)","",ChatColor.BOLD+" X (0.4D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, Medskillsinv);
				itemset("��ȣ��", Material.SPORE_BLOSSOM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ��ȣ���� ��ġ�մϴ�","��ȣ���ȿ� �ִ� ��Ƽ������ �������°� �˴ϴ�","(���ݷ��� ���� ������ ����մϴ�)","",ChatColor.BOLD+" X (0.4D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 10, Medskillsinv);
				itemset("�������", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ��������� ��û�մϴ�","(���ݷ��� ����īƮ ������ ����մϴ�)","",ChatColor.BOLD+"8 X (1.1D + "+BigDecimal.valueOf(ssd.SupplyCart.getOrDefault(p.getUniqueId(),1)*3.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, Medskillsinv);
				itemset("������", Material.CHISELED_QUARTZ_BLOCK, 0, 1, Arrays.asList("�ֺ� ��Ƽ������ ������ ����մϴ�"), 12, Medskillsinv);
				itemset("������", Material.BUBBLE_CORAL_BLOCK, 0, 1, Arrays.asList("���� ��� ������Ű��, ��Ƽ�����Դ� �̵��ӵ��� �����մϴ�"), 13, Medskillsinv);
				itemset("�̼��ٴ�", Material.BOW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� �̼��ٴ��� �Ѹ��ϴ�","(���ݷ��� �ڵ��������� ������ ����մϴ�)","",ChatColor.BOLD+" X (0.43D + "+BigDecimal.valueOf(ssd.AED.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 14, Medskillsinv);
				itemset("�ļ�", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("�����¿� ���� ��Ƽ���� �ļ��մϴ�"), 15, Medskillsinv);
				itemset("����óġ", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� �����մϴ�", "������ �ڵ��������Ⱑ ��Ƽ���� ġ���ϰ�, ���� ������ŵ�ϴ�"), 16, Medskillsinv);
				itemset("ī�弼�콺 ž", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("��ũ���� + �����۴�����", BigDecimal.valueOf(20-ssd.Medicine.getOrDefault(p.getUniqueId(),1)*0.3).setScale(2, RoundingMode.HALF_EVEN)+"�ʴ� 1�� ȸ���� ������ �����մϴ�"), 17, Medskillsinv);

				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 18, Medskillsinv);
				itemset("ȸ������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 19, Medskillsinv);
				itemset("����ġ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 20, Medskillsinv);
				itemset("Ȯ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 21, Medskillsinv);
				itemset("������ �й���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 23, Medskillsinv);
				itemset("ȯ�ڿ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 24, Medskillsinv);
				itemset("����������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 25, Medskillsinv);
				itemset("������Ʈ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 26, Medskillsinv);
			}
			else {
				itemset("����", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","�ֺ� ������ �����ϴ�","(���ݷ��� ġ������ ������ ����մϴ�)","",ChatColor.BOLD+" X (0.4D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, Medskillsinv);
				itemset("��ȣ��", Material.SPORE_BLOSSOM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ��ȣ���� ��ġ�մϴ�","��ȣ���ȿ� �ִ� ��Ƽ������ �������°� �˴ϴ�","(���ݷ��� ���� ������ ����մϴ�)","",ChatColor.BOLD+" X (0.4D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 10, Medskillsinv);
				itemset("�������", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ��������� ��û�մϴ�","(���ݷ��� ����īƮ ������ ����մϴ�)","",ChatColor.BOLD+"8 X (1.1D + "+BigDecimal.valueOf(ssd.SupplyCart.getOrDefault(p.getUniqueId(),1)*3.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, Medskillsinv);
				itemset("������", Material.CHISELED_QUARTZ_BLOCK, 0, 1, Arrays.asList("�ֺ� ��Ƽ������ ������ ����մϴ�"), 12, Medskillsinv);
				itemset("������", Material.BUBBLE_CORAL_BLOCK, 0, 1, Arrays.asList("���� ��� ������Ű��, ��Ƽ�����Դ� �̵��ӵ��� �����մϴ�"), 13, Medskillsinv);
				itemset("�̼��ٴ�", Material.BOW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� �̼��ٴ��� �Ѹ��ϴ�","(���ݷ��� �ڵ��������� ������ ����մϴ�)","",ChatColor.BOLD+" X (0.43D + "+BigDecimal.valueOf(ssd.AED.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 14, Medskillsinv);
				itemset("�ļ�", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("�����¿� ���� ��Ƽ���� �ļ��մϴ�"), 15, Medskillsinv);
				itemset("����óġ", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� �����մϴ�", "������ �ڵ��������Ⱑ ��Ƽ���� ġ���ϰ�, ���� ������ŵ�ϴ�"), 16, Medskillsinv);
				itemset("ī�弼�콺 ž", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("��ũ���� + �����۴�����", BigDecimal.valueOf(20-ssd.Medicine.getOrDefault(p.getUniqueId(),1)*0.3).setScale(2, RoundingMode.HALF_EVEN)+"�ʴ� 1�� ȸ���� ������ �����մϴ�"), 17, Medskillsinv);

				itemset("����", Material.END_CRYSTAL, 0, 1, Arrays.asList("������ ��Ƽ������ ��� �������·� ����ϴ�","������ ������ ��� ������ŵ�ϴ�"), 18, Medskillsinv);
				itemset("ȸ������", Material.END_CRYSTAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� ȸ�������� ��ġ�մϴ�", "(���ݷ��� ���� ������ ����մϴ�)","",ChatColor.BOLD+"5 X (0.8D + "+BigDecimal.valueOf(ssd.Decontamination.getOrDefault(p.getUniqueId(),1)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")"), 19, Medskillsinv);
				itemset("����ġ��", Material.END_ROD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ����ġ�Ḧ �ǽ��մϴ�", "(���ݷ��� ����īƮ ������ ����մϴ�)","",ChatColor.BOLD+"5 X (1.5D + "+BigDecimal.valueOf(ssd.SupplyCart.getOrDefault(p.getUniqueId(),1)*7.1).setScale(2, RoundingMode.HALF_EVEN)+")"), 20, Medskillsinv);
				itemset("Ȯ��", Material.SPLASH_POTION, 0, 1, Arrays.asList("������ ��ȣ������ �����մϴ�"), 21, Medskillsinv);
				itemset("������ �й���", Material.BOW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�ٶ� �迭]","���Է½� ������ �й��⸦ ����մϴ�","���� ��ø���� ������ŵ�ϴ�","",ChatColor.BOLD+"3 X (0.35D + "+BigDecimal.valueOf(ssd.AED.getOrDefault(p.getUniqueId(),1)*0.45).setScale(2, RoundingMode.HALF_EVEN)+")","(���ݷ��� �ڵ��������� ������ ����մϴ�)"), 23, Medskillsinv);
				itemset("ȯ�ڿ��", Material.BOW, 0, 1, Arrays.asList("��Ƽ���� ��ſ��� ��ũ����+��Ŭ���� �ϸ�","�ش� ��Ƽ���� ����Ҽ� �ֽ��ϴ�","��Ƽ���� ���Ż��°� �˴ϴ�", "Ŭ���� ����� ����˴ϴ�"), 24, Medskillsinv);
				itemset("����������", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�","ī�弼�콺 ž ������ð��� �����մϴ�"), 25, Medskillsinv);
				itemset("������Ʈ", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("�޸��� + �����۴�����", "������Ʈ�� Ȱ��ȭ�Ǿ� �ִµ��� ���ݷ��� �����մϴ�(25��)","ġ�������� ���ð��� �������ϴ�", "X (1 + 0.007D)"), 26, Medskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Medskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+ssd.SkillPoints.getOrDefault(p.getUniqueId(),1),"","Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"), 35, Medskillsinv);
		
		}
		else {
			
			itemset("RemedyingRocket", Material.TIPPED_ARROW, 1, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"[Frost]","Jump + LeftClick","",ChatColor.BOLD+" X (0.32D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.316).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 0, Medskillsinv);
			itemset("Decontamination", Material.TIPPED_ARROW, 2, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Decontamination.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"[Frost]","Sneaking + SwapHand","Removes All Negetive Effects","",ChatColor.BOLD+"15 X (0.15D + "+BigDecimal.valueOf(ssd.Decontamination.getOrDefault(p.getUniqueId(),1)*0.16).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 1, Medskillsinv);
			itemset("SupplyCart", Material.TIPPED_ARROW, 3, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.SupplyCart.getOrDefault(p.getUniqueId(),1),"","Jump + SwapHand",
					"Give Party Jump, Speed, Strength Effects ("+1+ssd.SupplyCart.getOrDefault(p.getUniqueId(),0)/2+"Lv, "+(50+50*ssd.SupplyCart.getOrDefault(p.getUniqueId(),0))/20+"s", "Master LV.5"), 2, Medskillsinv);
			itemset("Hideout", Material.TIPPED_ARROW, 4, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Hideout.getOrDefault(p.getUniqueId(),1),"","Sneaking", "Master LV.1"), 3, Medskillsinv);
			itemset("ArrowClinic", Material.TIPPED_ARROW, 5, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.ArrowClinic.getOrDefault(p.getUniqueId(),1),"",
					"Heal Hit party member", "MaxHealth X (0.05 + "+BigDecimal.valueOf(0.05+ssd.ArrowClinic.getOrDefault(p.getUniqueId(),1)*0.005+ssd.Medicine.getOrDefault(p.getUniqueId(),1)*0.004).setScale(2, RoundingMode.HALF_EVEN)+")","",
					ChatColor.UNDERLINE+"[Wind]","Every time an enemy is hit" ,"The damage the enemy receives increases by "+BigDecimal.valueOf(0.03*ssd.ArrowClinic.getOrDefault(p.getUniqueId(),1)).setScale(2, RoundingMode.HALF_EVEN),"(Max 1.9)", "Higher Skill Levels Can Stack Faster", "Master LV.30"), 4, Medskillsinv);
			itemset("AED", Material.CROSSBOW, 6, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.AED.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"[Wind]","Sneaking + LeftClick","",ChatColor.BOLD+" X (0.3D + "+BigDecimal.valueOf(ssd.AED.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")", "Master LV.50"), 5, Medskillsinv);
			itemset("Rescue", Material.KNOWLEDGE_BOOK, 6, 1, Arrays.asList(ChatColor.AQUA+ "Passive","","When Party Member is at death's door,","The Member will be Groggy for 5secs","You'll get speed and be able to Rescue Member","By using AED skill"), 6, Medskillsinv);
			itemset("Medicine", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+ssd.Medicine.getOrDefault(p.getUniqueId(),1),"","Increse Damage and Healing Rate","",ChatColor.BOLD+" + "+BigDecimal.valueOf(ssd.Medicine.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)), 7, Medskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("Vacuum(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Medskillsinv);
				itemset("Barrier(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Medskillsinv);
				itemset("SupportFire(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Medskillsinv);
				itemset("Anodyne(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Medskillsinv);
				itemset("Excitometabolic(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Medskillsinv);
				itemset("FineNeedles(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Medskillsinv);
				itemset("Evacuation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 15, Medskillsinv);
				itemset("FirstAids(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Medskillsinv);
				itemset("Caduceus Tower(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Medskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Vacuum", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Pull Entity to Hit Position","(Damage Affected By RemedyingRocket)","",ChatColor.BOLD+" X (0.4D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, Medskillsinv);
				itemset("Barrier", Material.SPORE_BLOSSOM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Frost]","Place Barrier When Use Once More","Party inside the Barrier are invulneralbe","(Damage Affected By Decontamination)","",ChatColor.BOLD+" X (0.4D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 10, Medskillsinv);
				itemset("SupportFire", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Call SupportFire When Use Once More","(Damage Affected By SupplyCart)","",ChatColor.BOLD+"8 X (1.1D + "+BigDecimal.valueOf(ssd.SupplyCart.getOrDefault(p.getUniqueId(),1)*3.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, Medskillsinv);
				itemset("Anodyne", Material.CHISELED_QUARTZ_BLOCK, 0, 1, Arrays.asList("Increases Near by Party's Armor"), 12, Medskillsinv);
				itemset("Excitometabolic", Material.BUBBLE_CORAL_BLOCK, 0, 1, Arrays.asList("Hold Hit Enemy, Give Speed Effect To Party"), 13, Medskillsinv);
				itemset("FineNeedles", Material.BOW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Spread FineNeedles When Use Once More","(Damage Affected By AED)","",ChatColor.BOLD+" X (0.43D + "+BigDecimal.valueOf(ssd.AED.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 14, Medskillsinv);
				itemset("Evacuation", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Groggy Party Member Will be Evacuated"), 15, Medskillsinv);
				itemset("FirstAids", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage", "Decontamination & AED will Heal Party, Hold Enemies"), 16, Medskillsinv);
				itemset("Caduceus Tower", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Give 1 Heal & Saturation Effect Per "+BigDecimal.valueOf(20-ssd.Medicine.getOrDefault(p.getUniqueId(),1)*0.3).setScale(2, RoundingMode.HALF_EVEN)+"Seconds"), 17, Medskillsinv);

				itemset("Anesthetic(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, Medskillsinv);
				itemset("HealingPump(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, Medskillsinv);
				itemset("Mass Treatment(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, Medskillsinv);
				itemset("Expand(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, Medskillsinv);
				itemset("Ultrasonic Nebulizer(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, Medskillsinv);
				itemset("Stretcher(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 24, Medskillsinv);
				itemset("LifeSaver(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Medskillsinv);
				itemset("Wing Suit(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Medskillsinv);
			}
			else {
				itemset("Vacuum", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Pull Entity to Hit Position","(Damage Affected By RemedyingRocket)","",ChatColor.BOLD+" X (0.4D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 9, Medskillsinv);
				itemset("Barrier", Material.SPORE_BLOSSOM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Frost]","Place Barrier When Use Once More","Party inside the Barrier are invulneralbe","(Damage Affected By Decontamination)","",ChatColor.BOLD+" X (0.4D + "+BigDecimal.valueOf(ssd.RemedyingRocket.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 10, Medskillsinv);
				itemset("SupportFire", Material.SPECTRAL_ARROW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Call SupportFire When Use Once More","(Damage Affected By SupplyCart)","",ChatColor.BOLD+"8 X (1.1D + "+BigDecimal.valueOf(ssd.SupplyCart.getOrDefault(p.getUniqueId(),1)*3.5).setScale(2, RoundingMode.HALF_EVEN)+")"), 11, Medskillsinv);
				itemset("Anodyne", Material.CHISELED_QUARTZ_BLOCK, 0, 1, Arrays.asList("Increases Near by Party's Armor"), 12, Medskillsinv);
				itemset("Excitometabolic", Material.BUBBLE_CORAL_BLOCK, 0, 1, Arrays.asList("Hold Hit Enemy, Give Speed Effect To Party"), 13, Medskillsinv);
				itemset("FineNeedles", Material.BOW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Spread FineNeedles When Use Once More","(Damage Affected By AED)","",ChatColor.BOLD+" X (0.43D + "+BigDecimal.valueOf(ssd.AED.getOrDefault(p.getUniqueId(),1)*0.4).setScale(2, RoundingMode.HALF_EVEN)+")"), 14, Medskillsinv);
				itemset("Evacuation", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Groggy Party Member Will be Evacuated"), 15, Medskillsinv);
				itemset("FirstAids", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage", "Decontamination & AED will Heal Party, Hold Enemies"), 16, Medskillsinv);
				itemset("Caduceus Tower", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem", "Give 1 Heal & Saturation Effect Per "+BigDecimal.valueOf(20-ssd.Medicine.getOrDefault(p.getUniqueId(),1)*0.3).setScale(2, RoundingMode.HALF_EVEN)+"Seconds"), 17, Medskillsinv);

				itemset("Anesthetic", Material.END_CRYSTAL, 0, 1, Arrays.asList("Set Hit Party Invulnerable Shortly","Hold Hit Enemies Shortly"), 18, Medskillsinv);
				itemset("HealingPump", Material.END_CRYSTAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Frost]","Place HealingPump When Use Once More", "(Damage Affected By Decontamination)"), 19, Medskillsinv);
				itemset("Mass Treatment", Material.END_ROD, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","MassTreatment When Use Once More", "(Damage Affected By SupplyCart)","",ChatColor.BOLD+"5 X (0.8D + "+BigDecimal.valueOf(ssd.Decontamination.getOrDefault(p.getUniqueId(),1)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")"), 20, Medskillsinv);
				itemset("Expand", Material.SPLASH_POTION, 0, 1, Arrays.asList("Increases Range & Absortion Amount"), 21, Medskillsinv);
				itemset("Ultrasonic Nebulizer", Material.BOW, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Wind]","Use Ultrasonic Nebulizer When Use Once More","Increases Decay Stack","",ChatColor.BOLD+"3 X (0.35D + "+BigDecimal.valueOf(ssd.AED.getOrDefault(p.getUniqueId(),1)*0.45).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By AED)"), 23, Medskillsinv);
				itemset("Stretcher", Material.BOW, 0, 1, Arrays.asList("Party Can Carried by You","Using Sneaking+RightClick with Bare Hand","Set Carried Invisible", "Quit Carrying By Click"), 24, Medskillsinv);
				itemset("LifeSaver", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decrease Caduceus Tower Cooldown"), 25, Medskillsinv);
				itemset("Wing Suit", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sprinting + ThrowItem", "Increases Damage While Using Wing Suit(25s)", "Removes Cooldown of RemedyingRocket", "X (1 + 0.007D)"), 26, Medskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Medskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+ssd.SkillPoints.getOrDefault(p.getUniqueId(),1),"","Click if you want to reset your skill's levels"), 35, Medskillsinv);
		
		}
		
		
		p.openInventory(Medskillsinv);
	}


}
