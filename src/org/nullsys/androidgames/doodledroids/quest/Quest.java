package org.nullsys.androidgames.doodledroids.quest;

import org.nullsys.androidgames.doodledroids.DoodleDroid;
import org.nullsys.androidgames.doodledroids.event.QuestEventCallback;
import org.nullsys.androidgames.doodledroids.item.InventoryItem;
import org.nullsys.androidgames.doodledroids.item.Item;

public class Quest {

    public String name = "";

    public String description = "";

    public QuestEventCallback callback;

    public int exp = 0;

    public int droills = 0;

    public int dropRate = 100;

    public InventoryItem[] dropItems = new InventoryItem[0];

    public int requiredLevel = 0;

    public InventoryItem[] requiredItems = new InventoryItem[0];

    public int requiredEnergy = 0;

    public int requiredHealth = 0;

    public boolean isAvailable(int level, int energy, int health, Item... items) {
	return items == requiredItems && level >= requiredLevel && energy >= requiredEnergy && health >= requiredHealth;
    }

    public void trigger(DoodleDroid droid) {
	try {
	    if (droid.level < requiredLevel)
		callback.onLevelInsufficient();
	    else if (droid.energy < requiredEnergy)
		callback.onEnergyInsufficient();
	    else if (droid.health < requiredHealth)
		callback.onHealthInsufficient();
	    else if (!droidHasRequiredItems(droid))
		callback.onItemsInsufficient();
	    else {
		droid.addExp(exp);
		droid.energy -= requiredEnergy;
		droid.health -= requiredHealth;
		droid.droills += droills;

		for (int itemIndex = 0; itemIndex < dropItems.length; itemIndex++)
		    if (Math.random() * 101 <= dropRate) {
			droid.usableItems.add(dropItems[itemIndex]);
			callback.onItemDrop(dropItems[itemIndex].name);
		    }

		for (int requiredItemsIndex = 0; requiredItemsIndex < requiredItems.length; requiredItemsIndex++)
		    for (int itemIndex = 0; itemIndex < droid.usableItems.size(); itemIndex++)
			if (droid.usableItems.get(itemIndex).id == requiredItems[requiredItemsIndex].id) {
			    droid.usableItems.remove(itemIndex);
			    itemIndex = droid.usableItems.size();
			}

	    }
	} catch (NullPointerException e) {
	}
    }

    private boolean droidHasRequiredItems(DoodleDroid droid) {
	int quantityInInventory = 0;
	try {
	    for (int requiredItemsIndex = 0; requiredItemsIndex < requiredItems.length; requiredItemsIndex++) {
		for (int itemIndex = 0; itemIndex < droid.usableItems.size(); itemIndex++)
		    if (droid.usableItems.get(itemIndex).id == requiredItems[requiredItemsIndex].id)
			quantityInInventory++;
		if (quantityInInventory == 0)
		    return false;
		quantityInInventory = 0;
	    }
	    return true;
	} catch (NullPointerException e) {
	    return false;
	}
    }
}
