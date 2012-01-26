package org.nullsys.androidgames.doodledroids.screen.mainmenu.create;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Back;
import aurelienribon.tweenengine.equations.Linear;

import org.nullsys.androidgames.doodledroids.Assets;
import org.nullsys.androidgames.doodledroids.DoodleDroid;
import org.nullsys.androidgames.doodledroids.Settings;
import org.nullsys.androidgames.framework.Game;
import org.nullsys.androidgames.framework.Input.TouchEvent;
import org.nullsys.androidgames.framework.display.Button;
import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.Sprite;
import org.nullsys.androidgames.framework.display.TextureRegion;
import org.nullsys.androidgames.framework.display.event.TouchEventCallback;
import org.nullsys.androidgames.framework.impl.GLScreen;

public class ChooseModelScreen extends GLScreen implements TouchEventCallback {

    Sprite universalPanel;

    Sprite yemaModel, sundotKulangotModel, pastillasModel;

    Sprite label;

    Button back, proceed;

    Button left, right;

    int currentIndex = DoodleDroid.SUNDOT_KULANGOT;

    public ChooseModelScreen(Game game) {
	super(game);

	if (Settings.musicEnabled && !Assets.mainMenuBGM.isPlaying())
	    Assets.mainMenuBGM.play();

	universalPanel = new Sprite(new TextureRegion(Assets.mainMenu, 0, 0, 480, 320));
	addChild(universalPanel);

	yemaModel = new Sprite(new TextureRegion(Assets.models, 0, 82, 86, 84));
	yemaModel.y = (320 - yemaModel.height) / 2;
	yemaModel.visible = false;
	yemaModel.touchCallback = this;
	addChild(yemaModel);

	sundotKulangotModel = new Sprite(new TextureRegion(Assets.models, 0, 0, 92, 82));
	sundotKulangotModel.x = (480 - sundotKulangotModel.width) / 2;
	sundotKulangotModel.y = (320 - sundotKulangotModel.height) / 2;
	sundotKulangotModel.touchCallback = this;
	addChild(sundotKulangotModel);

	pastillasModel = new Sprite(new TextureRegion(Assets.pastillasBlue, 0, 0, 128, 98));
	pastillasModel.y = (320 - pastillasModel.height) / 2;
	pastillasModel.visible = false;
	pastillasModel.touchCallback = this;
	addChild(pastillasModel);

	label = new Sprite(new TextureRegion(Assets.mainMenu, 263, 501, 249, 23));
	label.x = (480 - label.width) / 2;
	label.y = 274;
	addChild(label);

	left = new Button(new TextureRegion(Assets.mainMenu, 521, 191, 53, 45), new TextureRegion(Assets.mainMenu, 528, 246, 39, 33));
	left.x = 55;
	left.y = 130;
	left.downstate.x = 62;
	left.downstate.y = 136;
	left.touchCallback = this;
	left.tapSound = Assets.confirm;
	addChild(left);

	right = new Button(new TextureRegion(Assets.mainMenu, 586, 191, 53, 45), new TextureRegion(Assets.mainMenu, 593, 245, 39, 33));
	right.x = 373;
	right.y = 130;
	right.downstate.x = 380;
	right.downstate.y = 136;
	right.touchCallback = this;
	right.tapSound = Assets.confirm;
	addChild(right);

	back = new Button(new TextureRegion(Assets.mainMenu, 896, 0, 55, 53), new TextureRegion(Assets.mainMenu, 903, 54, 41, 39));
	back.x = 12;
	back.y = 11;
	back.downstate.x = 19;
	back.downstate.y = 18;
	back.touchCallback = this;
	back.tapSound = Assets.cancel1;
	addChild(back);

	proceed = new Button(new TextureRegion(Assets.mainMenu, 966, 2, 55, 52), new TextureRegion(Assets.mainMenu, 973, 56, 41, 39));
	proceed.x = 413;
	proceed.y = 10;
	proceed.downstate.x = 420;
	proceed.downstate.y = 16;
	proceed.touchCallback = this;
	proceed.tapSound = Assets.confirm;
	addChild(proceed);

	label.alpha = 0f;
	label.move(label.x, label.y, 1f, 1f, 0, 1f, Linear.INOUT, 250, 0, false);

	left.x = left.x + (left.width - left.width * 0.2f) / 2;
	left.x = left.y + (left.height - left.height * 0.2f) / 2;
	left.alpha = 0f;
	left.scaleX = 0.2f;
	left.scaleY = 0.2f;
	left.move(55, 130, 1.0f, 1.0f, left.rotation, 1f, Back.OUT, 250, 0, false);

	right.x = right.x + (right.width - right.width * 0.2f) / 2;
	right.x = right.y + (right.height - right.height * 0.2f) / 2;
	right.alpha = 0f;
	right.scaleX = 0.2f;
	right.scaleY = 0.2f;
	right.move(373, 130, 1.0f, 1.0f, right.rotation, 1f, Back.OUT, 250, 0, false);
    }

    @Override
    public void backPressed() {
	float targetX = left.x + (left.width - left.width * 0.2f) / 2;
	float targetY = left.y + (left.height - left.height * 0.2f) / 2;
	left.move(targetX, targetY, 0.2f, 0.2f, 0, 0f, Back.INOUT, 250, 0, false);

	targetX = right.x + (right.width - right.width * 0.2f) / 2;
	targetY = right.y + (right.height - right.height * 0.2f) / 2;
	right.move(targetX, targetY, 0.2f, 0.2f, 0, 0f, Back.INOUT, 250, 0, false);

	label.tweenCallback = new TweenCallback() {

	    @Override
	    public void tweenEventOccured(Types arg0, Tween arg1) {
		game.setScreen(new EnterNameScreen(game, false));
	    }
	};
	label.move(0f, Linear.INOUT, 250, 0, true);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void menuPressed() {
    }

    @Override
    public void onTouchEvent(DisplayObject source, TouchEvent event) {
	if (event.type == TouchEvent.TOUCH_TAP)
	    if (source.equals(back))
		backPressed();
	    else if (source.equals(left))
		leftButtonTapped();
	    else if (source.equals(right))
		rightButtonTapped();
	    else if (source.equals(proceed)) {
		float targetX = left.x + (left.width - left.width * 0.2f) / 2;
		float targetY = left.y + (left.height - left.height * 0.2f) / 2;
		left.move(targetX, targetY, 0.2f, 0.2f, 0, 0f, Back.INOUT, 250, 0, false);

		targetX = right.x + (right.width - right.width * 0.2f) / 2;
		targetY = right.y + (right.height - right.height * 0.2f) / 2;
		right.move(targetX, targetY, 0.2f, 0.2f, 0, 0f, Back.INOUT, 250, 0, false);

		label.tweenCallback = new TweenCallback() {

		    @Override
		    public void tweenEventOccured(Types arg0, Tween arg1) {
			game.setScreen(new ChooseColorScreen(game, currentIndex));
		    }
		};

		label.move(0f, Linear.INOUT, 250, 0, true);
	    }
    }

    @Override
    public void pause() {
	Assets.mainMenuBGM.pause();
    }

    @Override
    public void resume() {
	if (Settings.musicEnabled && !Assets.mainMenuBGM.isPlaying())
	    Assets.mainMenuBGM.play();
    }

    private void leftButtonTapped() {
	switch (currentIndex) {
	    case DoodleDroid.YEMA:
		currentIndex = DoodleDroid.SUNDOT_KULANGOT;
		yemaModel.tweenCallback = new TweenCallback() {

		    @Override
		    public void tweenEventOccured(Types arg0, Tween arg1) {
			yemaModel.visible = false;
		    }
		};
		yemaModel.move(0 - yemaModel.width, yemaModel.y, 1f, 1f, 0, 0, Linear.INOUT, 250, 0, true);
		pastillasModel.visible = false;
		sundotKulangotModel.alpha = 0;
		sundotKulangotModel.visible = true;
		sundotKulangotModel.x = 480 - sundotKulangotModel.width;
		sundotKulangotModel.move((480 - sundotKulangotModel.width) / 2, sundotKulangotModel.y, 1f, 1f, 0, 1, Linear.INOUT, 250, 0, false);
		break;
	    case DoodleDroid.SUNDOT_KULANGOT:
		currentIndex = DoodleDroid.PASTILLAS;
		sundotKulangotModel.tweenCallback = new TweenCallback() {

		    @Override
		    public void tweenEventOccured(Types arg0, Tween arg1) {
			sundotKulangotModel.visible = false;
		    }
		};
		sundotKulangotModel.move(0 - sundotKulangotModel.width, sundotKulangotModel.y, 1f, 1f, 0, 0, Linear.INOUT, 250, 0, true);
		yemaModel.visible = false;
		pastillasModel.alpha = 0;
		pastillasModel.visible = true;
		pastillasModel.x = 480 - pastillasModel.width;
		pastillasModel.move((480 - pastillasModel.width) / 2, sundotKulangotModel.y, 1f, 1f, 0, 1, Linear.INOUT, 250, 0, false);
		break;
	    case DoodleDroid.PASTILLAS:
		currentIndex = DoodleDroid.YEMA;
		pastillasModel.tweenCallback = new TweenCallback() {

		    @Override
		    public void tweenEventOccured(Types arg0, Tween arg1) {
			pastillasModel.visible = false;
		    }
		};
		pastillasModel.move(0 - pastillasModel.width, pastillasModel.y, 1f, 1f, 0, 0, Linear.INOUT, 250, 0, true);
		sundotKulangotModel.visible = false;
		yemaModel.alpha = 0;
		yemaModel.visible = true;
		yemaModel.x = 480 - yemaModel.width;
		yemaModel.move((480 - yemaModel.width) / 2, yemaModel.y, 1f, 1f, 0, 1f, Linear.INOUT, 250, 0, false);
		break;
	}
    }

    private void rightButtonTapped() {
	switch (currentIndex) {
	    case DoodleDroid.YEMA:
		currentIndex = DoodleDroid.PASTILLAS;
		yemaModel.tweenCallback = new TweenCallback() {

		    @Override
		    public void tweenEventOccured(Types arg0, Tween arg1) {
			yemaModel.visible = false;
		    }
		};
		yemaModel.move(480 + yemaModel.width, yemaModel.y, 1f, 1f, 0, 0f, Linear.INOUT, 250, 0, true);
		sundotKulangotModel.visible = false;
		pastillasModel.alpha = 0;
		pastillasModel.visible = true;
		pastillasModel.x = 0 - pastillasModel.width;
		pastillasModel.move((480 - pastillasModel.width) / 2, pastillasModel.y, 1f, 1f, 0, 1f, Linear.INOUT, 250, 0, false);
		break;
	    case DoodleDroid.SUNDOT_KULANGOT:
		currentIndex = DoodleDroid.YEMA;
		sundotKulangotModel.tweenCallback = new TweenCallback() {

		    @Override
		    public void tweenEventOccured(Types arg0, Tween arg1) {
			sundotKulangotModel.visible = false;
		    }
		};
		sundotKulangotModel.move(480 + sundotKulangotModel.width, sundotKulangotModel.y, 1f, 1f, 0, 0f, Linear.INOUT, 250, 0, true);
		pastillasModel.visible = false;
		yemaModel.alpha = 0;
		yemaModel.visible = true;
		yemaModel.x = 0 - yemaModel.width;
		yemaModel.move((480 - yemaModel.width) / 2, yemaModel.y, 1f, 1f, 0, 1f, Linear.INOUT, 250, 0, false);
		break;
	    case DoodleDroid.PASTILLAS:
		currentIndex = DoodleDroid.SUNDOT_KULANGOT;
		pastillasModel.tweenCallback = new TweenCallback() {

		    @Override
		    public void tweenEventOccured(Types arg0, Tween arg1) {
			pastillasModel.visible = false;
		    }
		};
		pastillasModel.move(480 + pastillasModel.width, pastillasModel.y, 1f, 1f, 0, 0, Linear.INOUT, 250, 0, true);
		yemaModel.visible = false;
		sundotKulangotModel.alpha = 0;
		sundotKulangotModel.visible = true;
		sundotKulangotModel.x = 0 - yemaModel.width;
		sundotKulangotModel.move((480 - yemaModel.width) / 2, yemaModel.y, 1f, 1f, 0, 1f, Linear.INOUT, 250, 0, false);
		break;
	}
    }

}
