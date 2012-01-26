package org.nullsys.androidgames.doodledroids.screen;

import java.util.List;

import aurelienribon.tweenengine.equations.Linear;

import org.nullsys.androidgames.doodledroids.Assets;
import org.nullsys.androidgames.doodledroids.DoodleDroid;
import org.nullsys.androidgames.doodledroids.DoodleDroid.Facing;
import org.nullsys.androidgames.doodledroids.DoodleDroid.State;
import org.nullsys.androidgames.doodledroids.HeadsUpDisplay;
import org.nullsys.androidgames.doodledroids.Settings;
import org.nullsys.androidgames.doodledroids.WorldTimeCallback;
import org.nullsys.androidgames.framework.Game;
import org.nullsys.androidgames.framework.Input;
import org.nullsys.androidgames.framework.Input.TouchEvent;
import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.Sprite;
import org.nullsys.androidgames.framework.display.TextureRegion;
import org.nullsys.androidgames.framework.display.event.TouchEventCallback;
import org.nullsys.androidgames.framework.impl.GLScreen;
import org.nullsys.androidgames.framework.math.VectorCoords;

public class StageScreen extends GLScreen implements TouchEventCallback, WorldTimeCallback {

    DoodleDroid droid;

    HeadsUpDisplay hud;

    DashboardTabsScreen dashboardTabs;

    PauseScreen pauseScreen;

    WorldMapScreen worldMap;

    private int currentStage = 0;

    private int droidX = 0;

    public StageScreen(Game game, int currentStage) {
	super(game);

	if (Settings.musicEnabled)
	    Assets.stageBGM.play();

	this.currentStage = currentStage;

	addChild(new Sprite(new TextureRegion(Assets.worldMap, 490, 195, 480, 320)));

	droid = game.getDroid();
	droid.x = (480 - droid.width) / 2;
	droid.touchCallback = this;
	addChild(droid);

	droidX = (int) ((480 - droid.width) / 2);

	hud = new HeadsUpDisplay(game);
	hud.setTouchEventCallback(this);
	addChild(hud);

	dashboardTabs = new DashboardTabsScreen(game, this);

	worldMap = new WorldMapScreen(game, this, currentStage);

	try {
	    if (!game.getWorld().running)
		game.getWorld().start();
	    game.getWorld().worldCallback = this;
	} catch (IllegalThreadStateException e) {
	}
    }

    @Override
    public void backPressed() {
	pauseScreen = new PauseScreen(game, this);
	Assets.stageBGM.setVolume(.125f);
	game.setScreen(pauseScreen);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void menuPressed() {
	pauseScreen = new PauseScreen(game, this);
	game.setScreen(pauseScreen);
    }

    @Override
    public void onHourPassed(float hour) {
    }

    @Override
    public void onMinutePassed(float minute) {
	droid.restoreEnergy(3);
	droid.restoreHealth(3);
	if (minute % 5 == 0)
	    droid.inflictAilment();
    }

    @Override
    public void onSecondPassed(float second) {
    }

    @Override
    public void onTouchEvent(DisplayObject source, TouchEvent event) {
	if (event.type == TouchEvent.TOUCH_TAP)
	    if (source.equals(droid))
		droid.tapped = true;
	    else if (source.equals(hud.worldMap)) {
		game.setScreen(new WorldMapScreen(game, this, currentStage));
		if (Settings.musicEnabled) {
		    Assets.stageBGM.pause();
		    Assets.worldBGM.play();
		}
	    } else if (source.equals(hud.stats)) {
		dashboardTabs.show(DashboardTabsScreen.STATUS);
		game.setScreen(dashboardTabs);
		droidX = (int) droid.x;
	    } else if (source.equals(hud.chest)) {
		dashboardTabs.show(DashboardTabsScreen.ITEMS);
		game.setScreen(dashboardTabs);
		droidX = (int) droid.x;
	    } else if (source.equals(hud.apparrels)) {
		dashboardTabs.show(DashboardTabsScreen.APPAREL);
		game.setScreen(dashboardTabs);
		droidX = (int) droid.x;
	    } else if (source.equals(hud.pause)) {
		pauseScreen = new PauseScreen(game, this);
		game.setScreen(pauseScreen);
	    }
    }

    @Override
    public void onYesTapped(PromptScreen prompt) {
	if (prompt.equals(pauseScreen.prompt))
	    pauseScreen.onYesTapped(prompt);
    }

    @Override
    public void pause() {
	Assets.stageBGM.pause();
    }

    @Override
    public void resume() {
	if (Settings.musicEnabled && !Assets.stageBGM.isPlaying())
	    Assets.stageBGM.play();
	droid.x = droidX;
	droid.y = 50;
    }

    @Override
    public void updateChildren(float deltaTime) {
	for (int displayObjectIndex = 0; displayObjectIndex < displayObjects.size(); displayObjectIndex++)
	    displayObjects.get(displayObjectIndex).update(deltaTime);

	Input i = game.getInput();
	List<TouchEvent> touchEvents = i.getTouchEvents();

	for (int eventIndex = 0; eventIndex < touchEvents.size(); eventIndex++) {
	    TouchEvent event = touchEvents.get(eventIndex);

	    for (int displayObjectIndex = 0; displayObjectIndex < displayObjects.size(); displayObjectIndex++)
		displayObjects.get(displayObjectIndex).checkInputs(event.type, event.x, event.y);

	    boolean moveDroid = true;
	    for (int index = 0; index < hud.displayObjects.size(); index++)
		if (hud.displayObjects.get(index).hitTestPoint(event.x, event.y)) {
		    moveDroid = false;
		    index = hud.displayObjects.size();
		}
	    if (moveDroid && event.type == TouchEvent.TOUCH_DOWN) {
		if (event.x < droid.x)
		    droid.facing = Facing.LEFT;
		else if (event.x > droid.x + droid.getWidth())
		    droid.facing = Facing.RIGHT;
		droid.state = droid.hasVirus || droid.hasWorm || droid.hasHardwareFailure ? State.SICK_WALKING : State.NORMAL_WALKING;
		droid.getDisplayByState().frameNumber = 0;
		droid.move(new VectorCoords(event.x, droid.y), Linear.INOUT, (int) Math.abs(droid.x - event.x) * 10, 0, true);
	    }
	}
	update(deltaTime);
    }
}
