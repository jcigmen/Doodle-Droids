package org.nullsys.androidgames.doodledroids.quest;

import org.nullsys.androidgames.doodledroids.item.InventoryItem;
import org.nullsys.androidgames.doodledroids.item.ItemFactory;

public class QuestFactory {

    public static Quest get(String name) {
	Quest quest = null;
	if (name.equals("Remove Malware")) {
	    quest = new Quest();
	    quest.description = "Clean your system from\ndirty binaries.";
	    quest.droills = 900;
	    quest.dropRate = 100;
	    quest.dropItems = new InventoryItem[] {};
	    quest.exp = 110;
	    quest.name = "Remove Malware";
	    quest.requiredEnergy = 125;
	    quest.requiredHealth = 45;
	    quest.requiredItems = new InventoryItem[] { ItemFactory.getInventoryItem("Anti-virus") };
	    quest.requiredLevel = 1;
	} else if (name.equals("Free RAM")) {
	    quest = new Quest();
	    quest.description = "Close minimized apps\nto free some memory";
	    quest.droills = 100;
	    quest.dropRate = 100;
	    quest.dropItems = new InventoryItem[] {};
	    quest.exp = 5;
	    quest.name = "Free RAM";
	    quest.requiredEnergy = 25;
	    quest.requiredHealth = 10;
	    quest.requiredItems = new InventoryItem[] {};
	    quest.requiredLevel = 1;
	} else if (name.equals("Rearrange Apps")) {
	    quest = new Quest();
	    quest.description = "Just re-allign your\napps. Nothing important.";
	    quest.droills = 100;
	    quest.dropRate = 100;
	    quest.dropItems = new InventoryItem[] {};
	    quest.exp = 3;
	    quest.name = "Rearrange Apps";
	    quest.requiredEnergy = 15;
	    quest.requiredHealth = 2;
	    quest.requiredItems = new InventoryItem[] {};
	    quest.requiredLevel = 1;
	} else if (name.equals("Defrag Disk")) {
	    quest = new Quest();
	    quest.description = "Reshape the disk\nto acquire more space.";
	    quest.droills = 175;
	    quest.dropRate = 100;
	    quest.dropItems = new InventoryItem[] {};
	    quest.exp = 15;
	    quest.name = name;
	    quest.requiredEnergy = 35;
	    quest.requiredHealth = 5;
	    quest.requiredItems = new InventoryItem[] { ItemFactory.getInventoryItem("Disk Defrag") };
	    quest.requiredLevel = 3;
	} else if (name.equals("Clear Cache")) {
	    quest = new Quest();
	    quest.description = "Delete surfing data,\nincluding porn pages.";
	    quest.droills = 175;
	    quest.dropRate = 100;
	    quest.dropItems = new InventoryItem[] {};
	    quest.exp = 15;
	    quest.name = name;
	    quest.requiredEnergy = 35;
	    quest.requiredHealth = 5;
	    quest.requiredItems = new InventoryItem[] {};
	    quest.requiredLevel = 3;
	} else if (name.equals("Benchmark Device")) {
	    quest = new Quest();
	    quest.description = "Asses the over-all\nperformance via testing.";
	    quest.droills = 350;
	    quest.dropRate = 35;
	    quest.dropItems = new InventoryItem[] { ItemFactory.getInventoryItem("Battery S") };
	    quest.exp = 35;
	    quest.name = name;
	    quest.requiredEnergy = 55;
	    quest.requiredHealth = 10;
	    quest.requiredItems = new InventoryItem[] {};
	    quest.requiredLevel = 3;
	} else if (name.equals("Update Drivers")) {
	    quest = new Quest();
	    quest.description = "Visit the sites of\nyour distributors and\ngrab the latest ones.";
	    quest.droills = 750;
	    quest.dropRate = 25;
	    quest.dropItems = new InventoryItem[] { ItemFactory.getInventoryItem("Battery M") };
	    quest.exp = 45;
	    quest.name = name;
	    quest.requiredEnergy = 55;
	    quest.requiredHealth = 15;
	    quest.requiredItems = new InventoryItem[] {};
	    quest.requiredLevel = 5;
	} else if (name.equals("Debug Script")) {
	    quest = new Quest();
	    quest.description = "A system script seem\nto be wrong. Find and\nfix it.";
	    quest.droills = 800;
	    quest.dropRate = 100;
	    quest.dropItems = new InventoryItem[] {};
	    quest.exp = 90;
	    quest.name = name;
	    quest.requiredEnergy = 100;
	    quest.requiredHealth = 25;
	    quest.requiredItems = new InventoryItem[] {};
	    quest.requiredLevel = 7;
	} else if (name.equals("Patch Boot Files")) {
	    quest = new Quest();
	    quest.description = "Seems like there is a\nproblem with the boot\nfiles. Find and\npatch the errors.";
	    quest.droills = 850;
	    quest.dropRate = 100;
	    quest.dropItems = new InventoryItem[] {};
	    quest.exp = 135;
	    quest.name = name;
	    quest.requiredEnergy = 150;
	    quest.requiredHealth = 65;
	    quest.requiredItems = new InventoryItem[] {};
	    quest.requiredLevel = 7;
	} else if (name.equals("Adjust Permissions")) {
	    quest = new Quest();
	    quest.description = "Switch system permissions\nto limit any malware\nactivity.";
	    quest.droills = 900;
	    quest.dropRate = 100;
	    quest.dropItems = new InventoryItem[] {};
	    quest.exp = 187;
	    quest.name = name;
	    quest.requiredEnergy = 200;
	    quest.requiredHealth = 25;
	    quest.requiredItems = new InventoryItem[] {};
	    quest.requiredLevel = 10;
	} else if (name.equals("Browser Alert")) {
	    quest = new Quest();
	    quest.description = "Someone is using Internet\nExplorer somewhere in your\nnetwork. Kill him.";
	    quest.droills = 450;
	    quest.dropRate = 100;
	    quest.dropItems = new InventoryItem[] {};
	    quest.exp = 475;
	    quest.name = name;
	    quest.requiredEnergy = 450;
	    quest.requiredHealth = 100;
	    quest.requiredItems = new InventoryItem[] {};
	    quest.requiredLevel = 15;
	} else if (name.equals("Freaking Computer Alert")) {
	    quest = new Quest();
	    quest.description = "One of your Windows\ncrashed! Replace it. Install\nLinux.";
	    quest.droills = 550;
	    quest.dropRate = 100;
	    quest.dropItems = new InventoryItem[] {};
	    quest.exp = 605;
	    quest.name = name;
	    quest.requiredEnergy = 550;
	    quest.requiredHealth = 121;
	    quest.requiredItems = new InventoryItem[] {};
	    quest.requiredLevel = 15;
	} else if (name.equals("Network Connection Alert")) {
	    quest = new Quest();
	    quest.description = "One of the routers\nmalfunctioned. Replace it.";
	    quest.droills = 660;
	    quest.dropRate = 100;
	    quest.dropItems = new InventoryItem[] {};
	    quest.exp = 750;
	    quest.name = name;
	    quest.requiredEnergy = 660;
	    quest.requiredHealth = 145;
	    quest.requiredItems = new InventoryItem[] {};
	    quest.requiredLevel = 15;
	} else if (name.equals("Spam Alert")) {
	    quest = new Quest();
	    quest.description = "Someone is spamming Silicon\nValleys System. Block it.";
	    quest.droills = 800;
	    quest.dropRate = 100;
	    quest.dropItems = new InventoryItem[] {};
	    quest.exp = 942;
	    quest.name = name;
	    quest.requiredEnergy = 792;
	    quest.requiredHealth = 175;
	    quest.requiredItems = new InventoryItem[] {};
	    quest.requiredLevel = 15;
	} else if (name.equals("System Maintenance")) {
	    quest = new Quest();
	    quest.description = "Just another boring IT\ntask. Just do it.";
	    quest.droills = 950;
	    quest.dropRate = 100;
	    quest.dropItems = new InventoryItem[] {};
	    quest.exp = 1187;
	    quest.name = name;
	    quest.requiredEnergy = 950;
	    quest.requiredHealth = 210;
	    quest.requiredItems = new InventoryItem[] {};
	    quest.requiredLevel = 15;
	} else if (name.equals("Hacker Alert")) {
	    quest = new Quest();
	    quest.description = "A hacker just entered\nThe System. Block him!";
	    quest.droills = 1150;
	    quest.dropRate = 100;
	    quest.dropItems = new InventoryItem[] {};
	    quest.exp = 1539;
	    quest.name = name;
	    quest.requiredEnergy = 1140;
	    quest.requiredHealth = 252;
	    quest.requiredItems = new InventoryItem[] {};
	    quest.requiredLevel = 15;
	} else if (name.equals("Infiltrate The Box")) {
	    quest = new Quest();
	    quest.description = "Penetrate your The System\nanonymously to find its weakness.";
	    quest.droills = 1700;
	    quest.dropRate = 100;
	    quest.dropItems = new InventoryItem[] {};
	    quest.exp = 3000;
	    quest.name = name;
	    quest.requiredEnergy = 1500;
	    quest.requiredHealth = 400;
	    quest.requiredItems = new InventoryItem[] {};
	    quest.requiredLevel = 80;
	} else if (name.equals("Backup Database")) {
	    quest = new Quest();
	    quest.description = "The Quest title names\nit. That is what this\nquest does.";
	    quest.droills = 1500;
	    quest.dropRate = 100;
	    quest.dropItems = new InventoryItem[] {};
	    quest.exp = 1846;
	    quest.name = name;
	    quest.requiredEnergy = 1368;
	    quest.requiredHealth = 302;
	    quest.requiredItems = new InventoryItem[] {};
	    quest.requiredLevel = 15;
	} else if (name.equals("Hacker Alert 2")) {
	    quest = new Quest();
	    quest.description = "Yet another intrusion.\nJust follow the IT\nAdmins and Sec experts.";
	    quest.droills = 1800;
	    quest.dropRate = 100;
	    quest.dropItems = new InventoryItem[] {};
	    quest.exp = 2610;
	    quest.name = name;
	    quest.requiredEnergy = 1800;
	    quest.requiredHealth = 360;
	    quest.requiredItems = new InventoryItem[] {};
	    quest.requiredLevel = 50;
	} else if (name.equals("Infiltrate The Box 2")) {
	    quest = new Quest();
	    quest.description = "Pwn the system!.";
	    quest.droills = 2500;
	    quest.dropRate = 100;
	    quest.dropItems = new InventoryItem[] {};
	    quest.exp = 5000;
	    quest.name = name;
	    quest.requiredEnergy = 2000;
	    quest.requiredHealth = 1000;
	    quest.requiredItems = new InventoryItem[] {};
	    quest.requiredLevel = 90;
	}
	return quest;
    }

    public static void load() {

    }
}
