package org.nullsys.androidgames.doodledroids.item;

import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.TextureRegion;

public abstract class Item extends DisplayObject {

    public final int id;

    public String name = "";

    public String description = "";

    public int storeValue = 0;

    protected Item(TextureRegion textureRegion, int id) {
	super.textureRegion = textureRegion;
	super.width = textureRegion.width;
	super.height = textureRegion.height;
	this.id = id;
    }

}
