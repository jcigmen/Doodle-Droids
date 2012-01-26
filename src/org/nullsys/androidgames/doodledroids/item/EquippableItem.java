package org.nullsys.androidgames.doodledroids.item;

import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.SpriteBatcher;

public interface EquippableItem {

    public float getEnergyBonus();

    public float getExpBonus();

    public float getHealthBonus();

    public float getHeight();

    public DisplayObject getInstance();

    public String getName();

    public int getStoreValue();

    public float getWidth();

    public void render(SpriteBatcher batcher);

    public void setScaleX(float scaleX);

    public void setScaleY(float scaleY);

    public void setX(float x);

    public void setY(float y);
}
