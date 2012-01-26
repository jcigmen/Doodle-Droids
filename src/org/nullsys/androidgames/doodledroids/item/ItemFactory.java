package org.nullsys.androidgames.doodledroids.item;

import org.nullsys.androidgames.doodledroids.Assets;
import org.nullsys.androidgames.doodledroids.DoodleDroid;
import org.nullsys.androidgames.framework.display.TextureRegion;

public class ItemFactory {

    public static EquippableItem getEquippable(String name) {
	Headgear headgear = null;
	if (name.equals("Poop Hat")) {
	    headgear = new Headgear(new TextureRegion(Assets.items1, 84, 0, 75, 56), 201);
	    headgear.name = name;
	    headgear.description = "Everything is immitated except for the smell.";
	    headgear.expBonus = .10f;
	    headgear.luckBonus = 25;
	    headgear.energyBonus = 10;
	    headgear.storeValue = 4500;
	} else if (name.equals("Boys Cap")) {
	    headgear = new Headgear(new TextureRegion(Assets.items1, 0, 0, 84, 41), 202);
	    headgear.name = name;
	    headgear.description = "Cap for the quite big boys.";
	    headgear.expBonus = .01f;
	    headgear.luckBonus = 2;
	    headgear.energyBonus = 3;
	    headgear.storeValue = 2000;
	} else if (name.equals("Hacker Hat")) {
	    headgear = new Headgear(new TextureRegion(Assets.items1, 159, 0, 79, 44), 203);
	    headgear.name = name;
	    headgear.description = "Does not actually help in the hacking process.";
	    headgear.expBonus = .04f;
	    headgear.energyBonus = 45;
	    headgear.storeValue = 1500;
	} else if (name.equals("Heart Hat")) {
	    headgear = new Headgear(new TextureRegion(Assets.items1, 259, 0, 76, 53), 204);
	    headgear.name = name;
	    headgear.description = "For lovers...";
	    headgear.expBonus = .075f;
	    headgear.energyBonus = 70;
	    headgear.storeValue = 7600;
	} else if (name.equals("King Crown")) {
	    headgear = new Headgear(new TextureRegion(Assets.items1, 335, 0, 84, 57), 205);
	    headgear.name = name;
	    headgear.description = "For the ultimate Comsci!";
	    headgear.expBonus = .1f;
	    headgear.energyBonus = 250;
	    headgear.storeValue = 12000;
	} else if (name.equals("Salakot")) {
	    headgear = new Headgear(new TextureRegion(Assets.items1, 420, 0, 110, 48), 206);
	    headgear.name = name;
	    headgear.description = "For mysterious farmers.";
	    headgear.expBonus = .41f;
	    headgear.energyBonus = 550;
	    headgear.storeValue = 9000;
	} else if (name.equals("Witch Hat")) {
	    headgear = new Headgear(new TextureRegion(Assets.items1, 533, 0, 110, 78), 207);
	    headgear.name = name;
	    headgear.description = "An awkward green hat.";
	    headgear.expBonus = (float) Math.random();
	    headgear.energyBonus = 350;
	    headgear.storeValue = 15000;
	} else if (name.equals("Goggles")) {
	    headgear = new Headgear(new TextureRegion(Assets.items1, 646, 0, 104, 40), 208);
	    headgear.name = name;
	    headgear.description = "Goggles for air diving.";
	    headgear.expBonus = 0.314f;
	    headgear.energyBonus = (int) (Math.random() * 200 + 400);
	    headgear.storeValue = 18000;
	} else if (name.equals("Box Hat")) {
	    headgear = new Headgear(new TextureRegion(Assets.items1, 753, 0, 111, 36), 209);
	    headgear.name = name;
	    headgear.description = "???";
	    headgear.expBonus = (float) (Math.random() * 4);
	    headgear.energyBonus = (int) (Math.random() * 300 + 700);
	    headgear.storeValue = 50000;
	}
	return headgear;
    }

    public static InventoryItem getInventoryItem(String name) {
	InventoryItem item = null;
	if (name.equals("Battery S")) {
	    item = new InventoryItem(new TextureRegion(Assets.items1, 0, 963, 36, 61), 101, true) {

		@Override
		public void use(DoodleDroid droid) {
		    if (useable)
			droid.restoreEnergy(25);
		}
	    };
	    item.name = name;
	    item.description = "Easily gets empty.\nMade in China.\n \nRestores 25 energy.";
	    item.storeValue = 200;
	} else if (name.equals("Battery M")) {
	    item = new InventoryItem(new TextureRegion(Assets.items1, 37, 955, 43, 69), 102, true) {

		@Override
		public void use(DoodleDroid droid) {
		    if (useable)
			droid.restoreEnergy(75);
		}
	    };
	    item.name = name;
	    item.description = "Quite tougher battery.\n \nRestores 75  Energy.";
	    item.storeValue = 350;
	} else if (name.equals("Battery L")) {
	    item = new InventoryItem(new TextureRegion(Assets.items1, 82, 949, 56, 75), 103, true) {

		@Override
		public void use(DoodleDroid droid) {
		    if (useable)
			droid.restoreEnergy(150);
		}
	    };
	    item.name = name;
	    item.description = "Battery for the\nbig boys.\n \nRestores 150  Energy.";
	    item.storeValue = 500;
	} else if (name.equals("Hyper Batt C")) {
	    item = new InventoryItem(new TextureRegion(Assets.items1, 321, 948, 60, 76), 104, true) {

		@Override
		public void use(DoodleDroid droid) {
		    if (useable)
			droid.restoreEnergy((int) (droid.getEnergyTotal() * .2));
		}
	    };
	    item.name = name;
	    item.description = "Hyper Old Battery.\nRestores 20% Energy.";
	    item.storeValue = 300;
	} else if (name.equals("Hyper Batt B")) {
	    item = new InventoryItem(new TextureRegion(Assets.items1, 260, 948, 60, 76), 105, true) {

		@Override
		public void use(DoodleDroid droid) {
		    if (useable)
			droid.restoreEnergy((int) (droid.getEnergyTotal() * .4));
		}
	    };
	    item.name = name;
	    item.description = "Quite Hyper Battery.\nRestores 40% Energy.";
	    item.storeValue = 500;
	} else if (name.equals("Hyper Batt A")) {
	    item = new InventoryItem(new TextureRegion(Assets.items1, 199, 948, 60, 76), 106, true) {

		@Override
		public void use(DoodleDroid droid) {
		    if (useable)
			droid.restoreEnergy((int) (droid.getEnergyTotal() * .6));
		}
	    };
	    item.name = name;
	    item.description = "Awesomely hyper!\nRestores 60%  Energy.";
	    item.storeValue = 550;
	} else if (name.equals("Hyper Batt S")) {
	    item = new InventoryItem(new TextureRegion(Assets.items1, 138, 948, 60, 76), 107, true) {

		@Override
		public void use(DoodleDroid droid) {
		    if (useable)
			droid.restoreEnergy((int) (droid.getEnergyTotal() * .85));
		}
	    };
	    item.name = name;
	    item.description = "Power of NAPOCOR,\nIn a battery.Restores 85%  Energy.";
	    item.storeValue = 800;
	} else if (name.equals("Plasma")) {
	    item = new InventoryItem(new TextureRegion(Assets.items1, 381, 947, 76, 77), 108, true) {

		@Override
		public void use(DoodleDroid droid) {
		    if (useable)
			droid.restoreEnergy(droid.getEnergyTotal());
		}
	    };
	    item.name = name;
	    item.description = "Power from a Saiyan.\nFully Restores Energy.";
	    item.storeValue = 1000;
	} else if (name.equals("Disk Defrag")) {
	    item = new InventoryItem(new TextureRegion(Assets.items1, 0, 887, 69, 68), 109, true) {

		@Override
		public void use(DoodleDroid droid) {
		    if (useable) {
			droid.restoreHealth((int) (droid.getHealthTotal() * .10));
			droid.hasWorm = Math.random() * 101 < 35;
		    }
		}
	    };
	    item.name = name;
	    item.description = "Cleans any disk.";
	    item.storeValue = 1000;
	} else if (name.equals("Anti-virus")) {
	    item = new InventoryItem(new TextureRegion(Assets.items1, 69, 891, 57, 56), 110, true) {

		@Override
		public void use(DoodleDroid droid) {
		    if (useable) {
			droid.hasVirus = Math.random() * 101 < 51;
			droid.hasWorm = Math.random() * 101 < 51;
		    }
		}
	    };
	    item.name = name;
	    item.description = "Somehow removes\nMalware.";
	    item.storeValue = 850;
	} else if (name.equals("Eiffing Ring")) {
	    item = new InventoryItem(new TextureRegion(Assets.items1, 136, 901, 45, 35), 111, true) {

		@Override
		public void use(DoodleDroid droid) {
		    if (useable) {
			droid.freeStats += Math.random() * 26;
			droid.hasVirus = false;
			droid.hasWorm = false;
			droid.hasHardwareFailure = false;
		    }
		}
	    };
	    item.name = name;
	    item.description = "Free 1 - 25 stat pts.";
	    item.storeValue = 7500;
	} else if (name.equals("X Ring")) {
	    item = new InventoryItem(new TextureRegion(Assets.items1, 184, 901, 45, 35), 112, true) {

		@Override
		public void use(DoodleDroid droid) {
		    if (useable) {
			droid.energyTotal += Math.random() * 8 + 3;
			droid.healthTotal += Math.random() * 8 + 3;
			droid.luck += Math.random() * 8 + 3;
			droid.hasVirus = false;
			droid.hasWorm = false;
			droid.hasHardwareFailure = false;
		    }
		}
	    };
	    item.name = name;
	    item.description = "Permanently increases\nstats.";
	    item.storeValue = 15000;
	}
	return item;
    }
}
