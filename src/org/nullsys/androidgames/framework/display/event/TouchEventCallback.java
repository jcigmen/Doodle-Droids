package org.nullsys.androidgames.framework.display.event;

import org.nullsys.androidgames.framework.Input.TouchEvent;
import org.nullsys.androidgames.framework.display.DisplayObject;

public interface TouchEventCallback {

    public void onTouchEvent(DisplayObject source, TouchEvent event);
}
