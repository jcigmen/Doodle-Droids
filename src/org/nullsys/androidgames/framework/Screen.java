package org.nullsys.androidgames.framework;

import java.util.ArrayList;
import java.util.List;

import org.nullsys.androidgames.doodledroids.event.PromptCallback;
import org.nullsys.androidgames.doodledroids.screen.PromptScreen;
import org.nullsys.androidgames.framework.Input.TouchEvent;
import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.event.TouchEventCallback;

public abstract class Screen implements TouchEventCallback, PromptCallback {

    public ArrayList<DisplayObject> displayObjects = new ArrayList<DisplayObject>();

    public final Game game;

    public Screen(Game game) {
	this.game = game;
    }

    public void addChild(DisplayObject child) {
	displayObjects.add(child);
    }

    public abstract void backPressed();

    public abstract void dispose();

    public abstract void menuPressed();

    @Override
    public void onNoTapped(PromptScreen prompt) {
    }

    @Override
    public void onOkTapped(PromptScreen prompt) {
    }

    @Override
    public void onTouchEvent(DisplayObject source, TouchEvent event) {
    }

    @Override
    public void onYesTapped(PromptScreen prompt) {
    }

    public abstract void pause();

    public abstract void present(float deltaTime);

    public abstract void resume();

    public void update(float deltaTime) {
    }

    public void updateChildren(float deltaTime) {
	for (int displayObjectIndex = 0; displayObjectIndex < displayObjects.size(); displayObjectIndex++)
	    displayObjects.get(displayObjectIndex).update(deltaTime);

	Input i = game.getInput();
	List<TouchEvent> touchEvents = i.getTouchEvents();

	for (int eventIndex = 0; eventIndex < touchEvents.size(); eventIndex++) {
	    TouchEvent event = touchEvents.get(eventIndex);
	    for (int displayObjectIndex = displayObjects.size() - 1; displayObjectIndex >= 0; displayObjectIndex--)
		if (!alreadyHit(displayObjectIndex, event.x, event.y))
		    displayObjects.get(displayObjectIndex).checkInputs(event.type, event.x, event.y);
	}
	update(deltaTime);
    }

    protected boolean alreadyHit(int endIndex, int eventX, int eventY) {
	boolean hits = false;
	for (int index = displayObjects.size() - 1; index > endIndex; index--)
	    if (displayObjects.get(index).hitTestPoint(eventX, eventY) && displayObjects.get(index).visible && displayObjects.get(index).enabled)
		return hits = true;
	return hits;
    }
}
