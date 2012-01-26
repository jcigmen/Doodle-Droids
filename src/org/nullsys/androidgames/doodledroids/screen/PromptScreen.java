package org.nullsys.androidgames.doodledroids.screen;

import android.util.Log;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Back;
import aurelienribon.tweenengine.equations.Linear;

import org.nullsys.androidgames.doodledroids.Assets;
import org.nullsys.androidgames.doodledroids.Settings;
import org.nullsys.androidgames.framework.Game;
import org.nullsys.androidgames.framework.Input.TouchEvent;
import org.nullsys.androidgames.framework.Screen;
import org.nullsys.androidgames.framework.display.Button;
import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.Sprite;
import org.nullsys.androidgames.framework.display.TextureRegion;
import org.nullsys.androidgames.framework.display.event.TouchEventCallback;
import org.nullsys.androidgames.framework.display.text.Text;
import org.nullsys.androidgames.framework.display.text.Text.TextRegistration;
import org.nullsys.androidgames.framework.impl.GLScreen;

public class PromptScreen extends GLScreen implements TouchEventCallback {

    public static final int YES_NO_PROMPT = 1;

    public static final int OK_PROMPT = 2;

    public Screen previousScreen;

    public int type;

    public Sprite background = new Sprite(new TextureRegion(Assets.quest, 377, 207, 100, 67));

    public Sprite panel = new Sprite(new TextureRegion(Assets.mainMenu, 640, 767, 380, 252));

    public Text label;

    public Button ok = new Button(new TextureRegion(Assets.mainMenu, 7, 325, 54, 52), new TextureRegion(Assets.mainMenu, 67, 334, 40, 39));

    public Button yes = new Button(new TextureRegion(Assets.mainMenu, 7, 325, 54, 52), new TextureRegion(Assets.mainMenu, 67, 334, 40, 39));

    public Button no = new Button(new TextureRegion(Assets.mainMenu, 754, 2, 47, 46), new TextureRegion(Assets.mainMenu, 754, 49, 37, 37));

    public PromptScreen(Game game, Screen previousScreen, int type, String label) {
	super(game);
	this.previousScreen = previousScreen;
	this.type = type;

	if (Settings.soundEnabled)
	    Assets.notify.play(1);

	for (int index = 0; index < previousScreen.displayObjects.size(); index++)
	    previousScreen.displayObjects.get(index).enabled = false;

	displayObjects.addAll(previousScreen.displayObjects);

	background.width = 480;
	background.height = 320;
	addChild(background);

	panel.scaleX = .2f;
	panel.scaleY = .2f;
	panel.x = 50;
	panel.y = 34;
	addChild(panel);

	this.label = new Text(Assets.poohWhiteStroked, label);
	this.label.registration = TextRegistration.CENTER;
	this.label.x = 240;
	this.label.y = 205;
	this.label.visible = false;
	addChild(this.label);

	panel.x = panel.x + (panel.width - panel.width * 0.2f) / 2;
	panel.y = panel.y + (panel.height - panel.height * 0.2f) / 2;

	if (type == OK_PROMPT) {
	    ok.x = 213;
	    ok.y = 60;
	    ok.downstate.x = 220;
	    ok.downstate.y = 66;
	    ok.scaleX = .2f;
	    ok.scaleY = .2f;
	    ok.touchCallback = this;
	    ok.tapSound = Assets.confirm;
	    addChild(ok);
	    ok.x = ok.x + (ok.width - ok.width * 0.2f) / 2;
	    ok.y = ok.y + (ok.height - ok.height * 0.2f) / 2;
	    ok.move(213, 60, 1f, 1f, 0, 1f, Back.OUT, 250, 0, false);
	} else {
	    yes.x = 176;
	    yes.y = 60;
	    yes.scaleX = .2f;
	    yes.scaleY = .2f;
	    yes.downstate.x = 183;
	    yes.downstate.y = 66;
	    yes.touchCallback = this;
	    yes.tapSound = Assets.confirm;
	    addChild(yes);

	    no.x = 254;
	    no.y = 64;
	    no.scaleX = .2f;
	    no.scaleY = .2f;
	    no.downstate.x = 259;
	    no.downstate.y = 68;
	    no.touchCallback = this;
	    no.tapSound = Assets.cancel1;
	    addChild(no);

	    yes.x = yes.x + (yes.width - yes.width * 0.2f) / 2;
	    yes.y = yes.y + (yes.height - yes.height * 0.2f) / 2;
	    yes.move(176, 60, 1f, 1f, 0, 1f, Back.OUT, 250, 0, false);

	    no.x = no.x + (no.width - no.width * 0.2f) / 2;
	    no.y = no.y + (no.height - no.height * 0.2f) / 2;
	    no.move(254, 64, 1f, 1f, 0, 1f, Back.OUT, 250, 0, false);
	}

	panel.tweenCallback = new TweenCallback() {

	    @Override
	    public void tweenEventOccured(Types arg0, Tween arg1) {
		PromptScreen.this.label.visible = true;
	    }
	};
	panel.move(50, 34, 1f, 1f, 0, 1f, Back.OUT, 250, 0, true);
    }

    @Override
    public void backPressed() {
	no.tweenCallback = new TweenCallback() {

	    @Override
	    public void tweenEventOccured(Types arg0, Tween arg1) {
		game.setScreen(previousScreen);
	    }
	};
	if (type == OK_PROMPT) {
	    previousScreen.onOkTapped(this);
	    tweenExit();
	} else {
	    tweenExit();
	    previousScreen.onNoTapped(this);
	}
    }

    @Override
    public void dispose() {
	Log.d("TEST", "[PromptScreen] Disposing prompt screen...");
	for (int index = 0; index < previousScreen.displayObjects.size(); index++)
	    previousScreen.displayObjects.get(index).enabled = true;
    }

    @Override
    public void menuPressed() {
	// TODO Auto-generated method stub

    }

    @Override
    public void onTouchEvent(DisplayObject source, TouchEvent event) {
	if (event.type == TouchEvent.TOUCH_TAP)
	    if (source.equals(ok)) {
		panel.tweenCallback = new TweenCallback() {

		    @Override
		    public void tweenEventOccured(Types arg0, Tween arg1) {
			game.setScreen(previousScreen);
		    }
		};
		tweenExit();
		previousScreen.onOkTapped(this);
	    } else if (source.equals(yes)) {
		panel.tweenCallback = new TweenCallback() {

		    @Override
		    public void tweenEventOccured(Types arg0, Tween arg1) {
			previousScreen.onYesTapped(PromptScreen.this);
		    }
		};
		tweenExit();
	    } else if (source.equals(no)) {
		panel.tweenCallback = new TweenCallback() {

		    @Override
		    public void tweenEventOccured(Types arg0, Tween arg1) {
			game.setScreen(previousScreen);
		    }
		};
		tweenExit();
		previousScreen.onNoTapped(this);
	    }
    }

    @Override
    public void pause() {
	// TODO Auto-generated method stub

    }

    @Override
    public void resume() {

    }

    /** Tween the displays for an exit animation. */
    private void tweenExit() {
	panel.move(240, 160, 0f, 0f, 1f, 0f, Linear.INOUT, 250, 0, true);
	ok.move(ok.x + ok.width / 2, ok.y + ok.height / 2, 0f, 0f, 1f, 0f, Linear.INOUT, 250, 0, false);
	yes.move(yes.x + yes.width / 2, yes.y + yes.height / 2, 0f, 0f, 1f, 0f, Linear.INOUT, 250, 0, false);
	no.move(no.x + no.width / 2, no.y + no.height / 2, 0f, 0f, 1f, 0f, Linear.INOUT, 250, 0, false);
	label.move(0f, Linear.INOUT, 250, 0, false);
    }
}
