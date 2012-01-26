package org.nullsys.androidgames.framework.display;

import org.nullsys.androidgames.framework.Input.TouchEvent;

public class ToggleButton extends Button {

    public ToggleButton(Sprite upstate, Sprite downstate) {
	super(upstate, downstate);
    }

    public ToggleButton(TextureRegion upstate, TextureRegion downstate) {
	super(upstate, downstate);
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
			toggled = !toggled;
			if (touchCallback != null)
			    touchCallback.onTouchEvent(this, touchEvent);
			if (tapSound != null)
			    tapSound.play(1);
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
		    if (!hitTestPoint(eventX, eventY))
			dragged = false;
		    else {
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

}
