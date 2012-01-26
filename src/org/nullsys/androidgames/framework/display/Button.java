package org.nullsys.androidgames.framework.display;

import org.nullsys.androidgames.doodledroids.Settings;
import org.nullsys.androidgames.framework.Input.TouchEvent;
import org.nullsys.androidgames.framework.Sound;
import org.nullsys.androidgames.framework.display.event.TouchEventCallback;

public class Button extends DisplayObjectContainer {

    public Sprite upstate;

    public Sprite downstate;

    public Sound tapSound;

    public boolean toggled = false;

    public boolean toggleMode = false;

    public Button(Sprite upstate, Sprite downstate) {
	this.upstate = upstate;
	this.downstate = downstate;
	width = upstate.width;
	height = upstate.height;
    }

    public Button(TextureRegion upstate, TextureRegion downstate) {
	this.upstate = new Sprite(upstate);
	this.downstate = new Sprite(downstate);
	width = upstate.width;
	height = upstate.height;
    }

    @Override
    public void checkInputs(int eventType, int eventX, int eventY) {
	if (visible && enabled) {
	    touchEvent = new TouchEvent();
	    touchEvent.x = eventX;
	    touchEvent.y = eventY;
	    touchEvent.type = eventType;
	    switch (eventType) {
		case TouchEvent.TOUCH_DOWN:
		    if (hitTestPoint(eventX, eventY)) {
			if (draggable)
			    dragged = true;
			tapped = true;
			if (touchCallback != null)
			    touchCallback.onTouchEvent(this, touchEvent);
			if (tapSound != null && Settings.soundEnabled)
			    tapSound.play(1);
			toggled = true;
		    } else
			toggled = false;
		    break;
		case TouchEvent.TOUCH_UP:
		    if (hitTestPoint(eventX, eventY)) {
			dragged = false;
			if (tapped && touchCallback != null) {
			    touchEvent.type = TouchEvent.TOUCH_TAP;
			    touchCallback.onTouchEvent(this, touchEvent);
			} else if (touchCallback != null)
			    touchCallback.onTouchEvent(this, touchEvent);
			toggled = false;
		    }
		    break;
		case TouchEvent.TOUCH_DRAGGED:
		    tapped = false;
		    if (!hitTestPoint(eventX, eventY)) {
			toggled = false;
			dragged = false;
		    } else {
			if (touchCallback != null)
			    touchCallback.onTouchEvent(this, touchEvent);
			if (dragged && draggable) {
			    x = eventX;
			    y = eventY;
			    for (int index = 0; index < displayObjects.size(); index++) {
				displayObjects.get(index).x = eventX;
				displayObjects.get(index).y = eventY;
			    }
			}
		    }
		    break;
	    }
	}
    }

    @Override
    public float getHeight() {
	if (toggled)
	    return downstate.getHeight();
	else
	    return upstate.getHeight();
    }

    public Sound getTapSound() {
	return tapSound;
    }

    @Override
    public TextureRegion getTextureRegion() {
	if (toggled)
	    return downstate.getTextureRegion();
	else
	    return upstate.getTextureRegion();
    }

    public TouchEventCallback getTouchListener() {
	return touchCallback;
    }

    @Override
    public float getWidth() {
	if (toggled)
	    return downstate.getWidth();
	else
	    return upstate.getWidth();
    }

    public float getX() {
	if (toggled)
	    return downstate.x;
	else
	    return super.x;
    }

    public float getY() {
	if (toggled)
	    return downstate.y;
	else
	    return super.y;
    }

    @Override
    public void render(SpriteBatcher batcher) {
	if (visible)
	    if (toggled) {
		batcher.beginBatch(getTextureRegion().texture);
		batcher.drawSprite(downstate.x, downstate.y, downstate.getWidth(), downstate.getHeight(), downstate.scaleX, downstate.scaleY, downstate.rotation, downstate.getTextureRegion());
		batcher.endBatch();
	    } else {
		batcher.beginBatch(getTextureRegion().texture);
		batcher.drawSprite(x, y, getWidth(), getHeight(), scaleX, scaleY, rotation, upstate.getTextureRegion());
		batcher.endBatch();
	    }
    }

    public void setTapSound(Sound tapSound) {
	this.tapSound = tapSound;
    }

    @Override
    public void update(float deltaTime) {
	manager.update();
    }

}
