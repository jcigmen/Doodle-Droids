package org.nullsys.androidgames.framework.display;

import org.nullsys.androidgames.framework.Input.TouchEvent;

public class PercentageBar extends DisplayObjectContainer {

    public float percentageWidth = 1.0f;

    public float percentageHeight = 1.0f;

    public PercentageBar(TextureRegion textureRegion) {
	super.textureRegion = textureRegion;
	super.width = textureRegion.width;
	super.height = textureRegion.height;
    }

    @Override
    public void checkInputs(int eventType, int eventX, int eventY) {
	if (visible) {
	    touchEvent = new TouchEvent();
	    touchEvent.x = eventX;
	    touchEvent.y = eventY;
	    touchEvent.type = eventType;
	    switch (eventType) {
		case TouchEvent.TOUCH_DOWN:
		    if (hitTestPoint(eventX, eventY)) {
			if (draggable) {
			    dragged = true;
			    previousEventX = eventX;
			    previousEventY = 320 - eventY;
			}
			tapped = true;
			if (touchCallback != null)
			    touchCallback.onTouchEvent(this, touchEvent);
		    }
		    break;
		case TouchEvent.TOUCH_UP:
		    if (hitTestPoint(eventX, eventY)) {
			dragged = false;
			if (tapped && touchCallback != null) {
			    touchEvent.type = TouchEvent.TOUCH_TAP;
			    touchCallback.onTouchEvent(this, touchEvent);
			} else if (touchCallback != null)
			    touchCallback.onTouchEvent(this, touchEvent);
		    }
		    break;
		case TouchEvent.TOUCH_DRAGGED:
		    tapped = false;
		    if (!hitTestPoint(eventX, eventY))
			dragged = false;
		    else {
			if (touchCallback != null)
			    touchCallback.onTouchEvent(this, touchEvent);
			if (dragged && draggable) {
			    if (dragX)
				x += eventX - previousEventX;
			    if (dragY)
				y += 320 - eventY - previousEventY;
			    for (int index = 0; index < displayObjects.size(); index++) {
				displayObjects.get(index).x = eventX - previousEventX;
				displayObjects.get(index).y = 320 - eventY - previousEventY;
			    }
			    previousEventX = eventX;
			    previousEventY = 320 - eventY;
			}
		    }
		    break;
	    }
	}
    }

    @Override
    public TextureRegion getTextureRegion() {
	return super.textureRegion;
    }

    @Override
    public void render(SpriteBatcher batcher) {
	batcher.beginBatch(getTextureRegion().texture);
	batcher.drawSprite(x, y, getWidth() * percentageWidth, getHeight() * percentageHeight, scaleX, scaleY, rotation, getTextureRegion());
	batcher.endBatch();
    }

    public void setPercentage(float percentageWidth, float percentageHeight) {
	this.percentageWidth = percentageWidth;
	this.percentageHeight = percentageHeight;
    }

    @Override
    public void update(float deltaTime) {
    }

}
