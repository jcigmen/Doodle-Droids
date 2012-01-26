package org.nullsys.androidgames.doodledroids.item;

import org.nullsys.androidgames.framework.display.TextureRegion;

public class DropItem extends Item {

    public final InventoryItem dropItem;

    public final int dropRate;

    public DropItem(InventoryItem dropItem, int dropRate) {
	super(dropItem.getTextureRegion(), dropItem.id);
	this.dropItem = dropItem;
	this.dropRate = dropRate;
    }

    @Override
    public void checkInputs(int eventType, int eventX, int eventY) {
    }

    @Override
    public TextureRegion getTextureRegion() {
	return null;
    }

    @Override
    public void update(float deltaTime) {
    }
}
