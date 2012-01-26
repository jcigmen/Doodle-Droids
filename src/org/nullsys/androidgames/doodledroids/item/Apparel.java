package org.nullsys.androidgames.doodledroids.item;

import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.TextureRegion;

public class Apparel extends Item implements EquippableItem {

    public float expBonus = 0;

    public int healthBonus = 0;

    public int energyBonus = 0;

    public Apparel(TextureRegion textureRegion, int id) {
	super(textureRegion, id);
    }

    @Override
    public void checkInputs(int eventType, int eventX, int eventY) {
	// TODO Auto-generated method stub

    }

    @Override
    public float getEnergyBonus() {
	return energyBonus;
    }

    @Override
    public float getExpBonus() {
	return expBonus;
    }

    @Override
    public float getHealthBonus() {
	return healthBonus;
    }

    @Override
    public DisplayObject getInstance() {
	return this;
    }

    @Override
    public String getName() {
	return super.name;
    }

    @Override
    public int getStoreValue() {
	return super.storeValue;
    }

    @Override
    public TextureRegion getTextureRegion() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setScaleX(float scaleX) {
	super.scaleX = scaleX;
    }

    @Override
    public void setScaleY(float scaleY) {
	super.scaleY = scaleY;
    }

    @Override
    public void setX(float x) {
	super.x = x;
    }

    @Override
    public void setY(float y) {
	super.y = y;
    }

    @Override
    public void update(float deltaTime) {
	// TODO Auto-generated method stub

    }

}
