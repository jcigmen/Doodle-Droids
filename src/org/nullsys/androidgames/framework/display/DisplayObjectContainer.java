package org.nullsys.androidgames.framework.display;

import java.util.ArrayList;

public abstract class DisplayObjectContainer extends DisplayObject {

    public ArrayList<DisplayObject> displayObjects = new ArrayList<DisplayObject>();

    public void addChild(DisplayObject child) {
	displayObjects.add(child);
    }

    public void addChildAt(DisplayObject child, int index) {
	displayObjects.add(index, child);
    }

    public DisplayObject getChild(DisplayObject child) {
	DisplayObject displayObject = null;
	for (int index = 0; index < displayObjects.size(); index++)
	    if (displayObjects.get(index).equals(child)) {
		displayObject = displayObjects.get(index);
		index = displayObjects.size();
	    }
	return displayObject;
    }

    public DisplayObject getChildAt(int index) {
	return displayObjects.get(index);
    }

    public int getNumChildren() {
	return displayObjects.size();
    }

    public void removeChild(DisplayObject child) {
	displayObjects.remove(child);
    }

    public void removeChildAt(int index) {
	displayObjects.remove(index);
    }

    @Override
    public void render(SpriteBatcher batcher) {
	for (int index = 0; index < displayObjects.size(); index++)
	    displayObjects.get(index).render(batcher);
    }

    @Override
    public void update(float deltaTime) {
	manager.update();
	for (int index = 0; index < displayObjects.size(); index++)
	    if (enabled)
		displayObjects.get(index).enabled = false;
    }
}
