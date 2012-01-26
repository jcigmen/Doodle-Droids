package org.nullsys.androidgames.doodledroids.screen.mainmenu.create;

import android.graphics.Color;

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

public class ChooseColorScreen extends GLScreen implements TouchEventCallback {

    Sprite universalPanel;

    Sprite model;

    Sprite label;

    Button back, proceed;

    Button green, blue, red, yellow, white;

    int modelType, color = Color.BLUE;

    public ChooseColorScreen(Game game, int modelType) {
	super(game);

	if (Settings.musicEnabled && !Assets.mainMenuBGM.isPlaying())
	    Assets.mainMenuBGM.play();

	this.modelType = modelType;

	universalPanel = new Sprite(new TextureRegion(Assets.mainMenu, 0, 0, 480, 320));
	addChild(universalPanel);

	switch (modelType) {
	    case DoodleDroid.YEMA:
		model = new Sprite(new TextureRegion(Assets.models, 0, 82, 86, 84));
		break;
	    case DoodleDroid.SUNDOT_KULANGOT:
		model = new Sprite(new TextureRegion(Assets.models, 0, 0, 92, 82));
		break;
	    case DoodleDroid.PASTILLAS:
		model = new Sprite(new TextureRegion(Assets.pastillasOneBlue, 0, 0, 128, 98));
		break;
	}
	model.x = (480 - model.width) / 2;
	model.y = (320 - model.height) / 2;
	addChild(model);

	label = new Sprite(new TextureRegion(Assets.mainMenu, 30, 552, 209, 23));
	label.x = 135;
	label.y = 274;
	addChild(label);

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

	green = new Button(new TextureRegion(Assets.mainMenu, 13, 746, 42, 41), new TextureRegion(Assets.mainMenu, 18, 793, 31, 31));
	green.x = 108;
	green.y = 46;
	green.downstate.x = 113;
	green.downstate.y = 51;
	green.touchCallback = this;
	green.tapSound = Assets.tap;
	addChild(green);

	blue = new Button(new TextureRegion(Assets.mainMenu, 70, 746, 42, 41), new TextureRegion(Assets.mainMenu, 75, 793, 31, 31));
	blue.x = 165;
	blue.y = 46;
	blue.downstate.x = 170;
	blue.downstate.y = 51;
	blue.touchCallback = this;
	blue.tapSound = Assets.tap;
	addChild(blue);

	red = new Button(new TextureRegion(Assets.mainMenu, 184, 746, 42, 41), new TextureRegion(Assets.mainMenu, 189, 793, 31, 31));
	red.x = 222;
	red.y = 46;
	red.downstate.x = 227;
	red.downstate.y = 51;
	red.touchCallback = this;
	red.tapSound = Assets.tap;
	addChild(red);

	yellow = new Button(new TextureRegion(Assets.mainMenu, 241, 746, 42, 41), new TextureRegion(Assets.mainMenu, 246, 793, 31, 31));
	yellow.x = 279;
	yellow.y = 46;
	yellow.downstate.x = 284;
	yellow.downstate.y = 51;
	yellow.touchCallback = this;
	yellow.tapSound = Assets.tap;
	addChild(yellow);

	white = new Button(new TextureRegion(Assets.mainMenu, 294, 746, 42, 41), new TextureRegion(Assets.mainMenu, 299, 793, 31, 31));
	white.x = 332;
	white.y = 46;
	white.downstate.x = 337;
	white.downstate.y = 51;
	white.touchCallback = this;
	white.tapSound = Assets.tap;
	addChild(white);

	// Tween the displays
	green.scaleX = .2f;
	green.scaleY = .2f;
	green.alpha = 0f;

	blue.scaleX = .2f;
	blue.scaleY = .2f;
	blue.alpha = 0f;

	red.scaleX = .2f;
	red.scaleY = .2f;
	red.alpha = 0f;

	yellow.scaleX = .2f;
	yellow.scaleY = .2f;
	yellow.alpha = 0f;

	white.scaleX = .2f;
	white.scaleY = .2f;
	white.alpha = 0f;

	int delay = 0;
	delay += 100;
	green.x = green.x + (green.width - green.width * 0.2f) / 2;
	green.y = green.y + (green.height - green.height * 0.2f) / 2;
	green.move(108, 46, 1f, 1f, 0, 1f, Back.OUT, 250, delay, false);

	delay += 100;
	blue.x = blue.x + (blue.width - blue.width * 0.2f) / 2;
	blue.y = blue.y + (blue.height - blue.height * 0.2f) / 2;
	blue.move(165, 46, 1f, 1f, 0, 1f, Back.OUT, 250, delay, false);

	red.x = red.x + (red.width - red.width * 0.2f) / 2;
	red.y = red.y + (red.height - red.height * 0.2f) / 2;
	red.move(222, 46, 1f, 1f, 0, 1f, Back.OUT, 250, delay, false);

	delay += 100;
	yellow.x = yellow.x + (yellow.width - yellow.width * 0.2f) / 2;
	yellow.y = yellow.y + (yellow.height - yellow.height * 0.2f) / 2;
	yellow.move(279, 46, 1f, 1f, 0, 1f, Back.OUT, 250, delay, false);

	delay += 100;
	white.x = white.x + (white.width - white.width * 0.2f) / 2;
	white.y = white.y + (white.height - white.height * 0.2f) / 2;
	white.move(332, 46, 1f, 1f, 0, 1f, Back.OUT, 250, delay, false);

	label.alpha = 0f;
	label.move(1f, Linear.INOUT, 250, 0, false);
    }

    @Override
    public void backPressed() {
	int delay = 0;
	float targetX = white.x + (white.width - white.width * 0.2f) / 2;
	float targetY = white.y + (white.height - white.height * 0.2f) / 2;
	white.move(targetX, targetY, .2f, .2f, 0, 0f, Back.IN, 250, delay, false);

	delay += 100;
	targetX = yellow.x + (yellow.width - yellow.width * 0.2f) / 2;
	targetY = yellow.y + (yellow.height - yellow.height * 0.2f) / 2;
	yellow.move(targetX, targetY, .2f, .2f, 0, 0f, Back.IN, 250, delay, false);

	delay += 100;
	targetX = red.x + (red.width - red.width * 0.2f) / 2;
	targetY = red.y + (red.height - red.height * 0.2f) / 2;
	red.move(targetX, targetY, .2f, .2f, 0, 0f, Back.IN, 250, delay, false);

	delay += 100;
	targetX = blue.x + (blue.width - blue.width * 0.2f) / 2;
	targetY = blue.y + (blue.height - blue.height * 0.2f) / 2;
	blue.move(targetX, targetY, .2f, .2f, 0, 0f, Back.IN, 250, delay, false);

	green.tweenCallback = new TweenCallback() {

	    @Override
	    public void tweenEventOccured(Types arg0, Tween arg1) {
		game.setScreen(new ChooseModelScreen(game));
	    }
	};

	delay += 100;
	targetX = green.x + (green.width - green.width * 0.2f) / 2;
	targetY = green.y + (green.height - green.height * 0.2f) / 2;
	green.move(targetX, targetY, .2f, .2f, 0, 0f, Back.IN, 250, delay, true);

	label.move(0f, Linear.INOUT, 250, 0, false);
    }

    public void colorTapped(int color) {
	this.color = color;
	switch (modelType) {
	    case DoodleDroid.YEMA:
		switch (color) {
		    case Color.GREEN:
			model.textureRegion = new TextureRegion(Assets.models, 86, 82, 86, 84);
			break;
		    case Color.BLUE:
			model.textureRegion = new TextureRegion(Assets.models, 0, 82, 86, 84);
			break;
		    case Color.RED:
			model.textureRegion = new TextureRegion(Assets.models, 172, 82, 86, 84);
			break;
		    case Color.YELLOW:
			model.textureRegion = new TextureRegion(Assets.models, 344, 82, 86, 84);
			break;
		    case Color.WHITE:
			model.textureRegion = new TextureRegion(Assets.models, 258, 82, 86, 84);
			break;
		    default:
		}
		break;
	    case DoodleDroid.PASTILLAS:
		switch (color) {
		    case Color.GREEN:
			model.textureRegion = new TextureRegion(Assets.pastillasOneGreen, 0, 0, 128, 98);
			break;
		    case Color.BLUE:
			model.textureRegion = new TextureRegion(Assets.pastillasOneBlue, 0, 0, 128, 98);
			break;
		    case Color.RED:
			model.textureRegion = new TextureRegion(Assets.pastillasOneRed, 0, 0, 128, 98);
			break;
		    case Color.YELLOW:
			model.textureRegion = new TextureRegion(Assets.pastillasOneYellow, 0, 0, 128, 98);
			break;
		    case Color.WHITE:
			model.textureRegion = new TextureRegion(Assets.pastillasOneWhite, 0, 0, 128, 98);
			break;
		    default:
		}
		break;
	    case DoodleDroid.SUNDOT_KULANGOT:
		switch (color) {
		    case Color.GREEN:
			model.textureRegion = new TextureRegion(Assets.models, 92, 0, 92, 82);
			break;
		    case Color.BLUE:
			model.textureRegion = new TextureRegion(Assets.models, 0, 0, 92, 82);
			break;
		    case Color.RED:
			model.textureRegion = new TextureRegion(Assets.models, 184, 0, 92, 82);
			break;
		    case Color.YELLOW:
			model.textureRegion = new TextureRegion(Assets.models, 276, 0, 92, 82);
			break;
		    case Color.WHITE:
			model.textureRegion = new TextureRegion(Assets.models, 368, 0, 92, 82);
			break;
		    default:
		}
		break;
	    default:
	}
    }

    @Override
    public void dispose() {

    }

    @Override
    public void menuPressed() {
	// TODO Auto-generated method stub

    }

    @Override
    public void onTouchEvent(DisplayObject source, TouchEvent event) {
	if (event.type == TouchEvent.TOUCH_TAP)
	    if (source.equals(back))
		backPressed();
	    else if (source.equals(green))
		colorTapped(Color.GREEN);
	    else if (source.equals(blue))
		colorTapped(Color.BLUE);
	    else if (source.equals(red))
		colorTapped(Color.RED);
	    else if (source.equals(yellow))
		colorTapped(Color.YELLOW);
	    else if (source.equals(white))
		colorTapped(Color.WHITE);
	    else if (source.equals(proceed)) {
		green.tweenCallback = new TweenCallback() {

		    @Override
		    public void tweenEventOccured(Types arg0, Tween arg1) {
			game.setScreen(new ChooseSaveSlotScreen(game, modelType, color));
		    }
		};

		int delay = 0;
		float targetX = white.x + (white.width - white.width * 0.2f) / 2;
		float targetY = white.y + (white.height - white.height * 0.2f) / 2;
		white.move(targetX, targetY, .2f, .2f, 0, 0f, Back.IN, 250, delay, false);

		delay += 100;
		targetX = yellow.x + (yellow.width - yellow.width * 0.2f) / 2;
		targetY = yellow.y + (yellow.height - yellow.height * 0.2f) / 2;
		yellow.move(targetX, targetY, .2f, .2f, 0, 0f, Back.IN, 250, delay, false);

		delay += 100;
		targetX = red.x + (red.width - red.width * 0.2f) / 2;
		targetY = red.y + (red.height - red.height * 0.2f) / 2;
		red.move(targetX, targetY, .2f, .2f, 0, 0f, Back.IN, 250, delay, false);

		delay += 100;
		targetX = blue.x + (blue.width - blue.width * 0.2f) / 2;
		targetY = blue.y + (blue.height - blue.height * 0.2f) / 2;
		blue.move(targetX, targetY, .2f, .2f, 0, 0f, Back.IN, 250, delay, false);

		delay += 100;
		targetX = green.x + (green.width - green.width * 0.2f) / 2;
		targetY = green.y + (green.height - green.height * 0.2f) / 2;
		green.move(targetX, targetY, .2f, .2f, 0, 0f, Back.IN, 250, delay, true);
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

    @Override
    public void update(float deltaTime) {

    }
}
