package org.nullsys.androidgames.doodledroids.item;

import org.nullsys.androidgames.doodledroids.DoodleDroid;
import org.nullsys.androidgames.framework.display.TextureRegion;

public class InventoryItem extends Item implements ItemEffectCallback {

    public final boolean useable;

    public InventoryItem(TextureRegion textureRegion, int id, boolean useable) {
	super(textureRegion, id);
	this.useable = useable;
    }

    @Override
    public void checkInputs(int eventType, int eventX, int eventY) {
	// TODO Auto-generated method stub

    }

    @Override
    public TextureRegion getTextureRegion() {
	return textureRegion;
    }

    @Override
    public void update(float deltaTime) {
	manager.update();
    }

    @Override
    public void use(DoodleDroid droid) {

    }

}
