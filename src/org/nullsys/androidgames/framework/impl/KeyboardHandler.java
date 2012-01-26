package org.nullsys.androidgames.framework.impl;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.view.View.OnKeyListener;

import org.nullsys.androidgames.framework.Input.KeyEvent;
import org.nullsys.androidgames.framework.Pool;
import org.nullsys.androidgames.framework.Pool.PoolObjectFactory;

public class KeyboardHandler implements OnKeyListener {

    boolean[] pressedKeys = new boolean[128];
    Pool<KeyEvent> keyEventPool;
    List<KeyEvent> keyEventsBuffer = new ArrayList<KeyEvent>();
    List<KeyEvent> keyEvents = new ArrayList<KeyEvent>();

    public KeyboardHandler(View view) {
	Log.d("TEST", "[KeyboardHandler] Instantiating...");
	PoolObjectFactory<KeyEvent> factory = new PoolObjectFactory<KeyEvent>() {

	    @Override
	    public KeyEvent createObject() {
		return new KeyEvent();
	    }
	};
	keyEventPool = new Pool<KeyEvent>(factory, 100);
	view.setOnKeyListener(this);
	view.setFocusableInTouchMode(true);
	view.requestFocus();
    }

    public List<KeyEvent> getKeyEvents() {
	synchronized (this) {
	    int len = keyEvents.size();
	    for (int i = 0; i < len; i++)
		keyEventPool.free(keyEvents.get(i));
	    keyEvents.clear();
	    keyEvents.addAll(keyEventsBuffer);
	    keyEventsBuffer.clear();
	    return keyEvents;
	}
    }

    public boolean isKeyPressed(int keyCode) {
	if (keyCode < 0 || keyCode > 127)
	    return false;
	return pressedKeys[keyCode];
    }

    @Override
    public boolean onKey(View v, int keyCode, android.view.KeyEvent event) {
	if (event.getAction() == android.view.KeyEvent.ACTION_MULTIPLE)
	    return false;

	synchronized (this) {
	    KeyEvent keyEvent = keyEventPool.newObject();
	    keyEvent.keyCode = keyCode;
	    keyEvent.keyChar = (char) event.getUnicodeChar();
	    if (event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
		keyEvent.type = KeyEvent.KEY_DOWN;
		if (keyCode > 0 && keyCode < 127)
		    pressedKeys[keyCode] = true;
	    }
	    if (event.getAction() == android.view.KeyEvent.ACTION_UP) {
		keyEvent.type = KeyEvent.KEY_UP;
		if (keyCode > 0 && keyCode < 127)
		    pressedKeys[keyCode] = false;
	    }
	    keyEventsBuffer.add(keyEvent);
	}
	return false;
    }
}
