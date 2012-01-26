package org.nullsys.androidgames.doodledroids.screen.mainmenu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import android.graphics.Color;
import android.util.Log;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Back;
import aurelienribon.tweenengine.equations.Linear;

import org.nullsys.androidgames.doodledroids.Assets;
import org.nullsys.androidgames.doodledroids.DoodleDroid;
import org.nullsys.androidgames.doodledroids.Settings;
import org.nullsys.androidgames.doodledroids.screen.LoadingScreen;
import org.nullsys.androidgames.doodledroids.screen.LoadingScreen.LoadingScreenCallback;
import org.nullsys.androidgames.doodledroids.screen.PromptScreen;
import org.nullsys.androidgames.doodledroids.screen.StageScreen;
import org.nullsys.androidgames.doodledroids.screen.WorldMapScreen;
import org.nullsys.androidgames.framework.Game;
import org.nullsys.androidgames.framework.Input.TouchEvent;
import org.nullsys.androidgames.framework.display.Button;
import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.Sprite;
import org.nullsys.androidgames.framework.display.TextureRegion;
import org.nullsys.androidgames.framework.display.event.TouchEventCallback;
import org.nullsys.androidgames.framework.display.text.Text;
import org.nullsys.androidgames.framework.display.text.Text.TextRegistration;
import org.nullsys.androidgames.framework.impl.GLGame;
import org.nullsys.androidgames.framework.impl.GLScreen;
import org.nullsys.androidgames.framework.math.VectorCoords;

public class LoadDoodleDroidScreen extends GLScreen implements TouchEventCallback, LoadingScreenCallback {

    DoodleDroid loadedDroid;

    DoodleDroid droidOne, droidTwo, droidThree;

    String droidOneName, droidTwoName, droidThreeName;

    Sprite universalPanel;

    Button back, proceed;

    Sprite shadowOne, shadowTwo, shadowThree;

    Sprite arrow;

    Sprite label;

    Text droidName;

    public LoadDoodleDroidScreen(Game game) {
	super(game);

	if (Settings.musicEnabled && !Assets.mainMenuBGM.isPlaying())
	    Assets.mainMenuBGM.play();

	universalPanel = new Sprite(new TextureRegion(Assets.mainMenu, 0, 0, 480, 320));
	addChild(universalPanel);

	shadowOne = new Sprite(new TextureRegion(Assets.mainMenu, 375, 681, 111, 33));
	shadowOne.x = 43;
	shadowOne.y = 72;
	shadowOne.touchCallback = this;
	addChild(shadowOne);

	shadowTwo = new Sprite(new TextureRegion(Assets.mainMenu, 375, 681, 111, 33));
	shadowTwo.x = 184;
	shadowTwo.y = 72;
	shadowTwo.touchCallback = this;
	addChild(shadowTwo);

	shadowThree = new Sprite(new TextureRegion(Assets.mainMenu, 375, 681, 111, 33));
	shadowThree.x = 326;
	shadowThree.y = 72;
	shadowThree.touchCallback = this;
	addChild(shadowThree);

	arrow = new Sprite(new TextureRegion(Assets.mainMenu, 292, 673, 43, 41));
	arrow.x = (480 - arrow.width) / 2;
	arrow.y = 213;
	addChild(arrow);

	label = new Sprite(new TextureRegion(Assets.mainMenu, 288, 611, 198, 23));
	label.x = 140;
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

	droidName = new Text(Assets.poohBlackFont, "");
	droidName.registration = TextRegistration.CENTER;
	droidName.x = 240;
	droidName.y = 50;
	addChild(droidName);

	// Tween the displays
	back.x = 0 - back.width;
	back.move(new VectorCoords(12, 11), Back.OUT, 250, 0, false);
	proceed.x = 480 + proceed.width;
	proceed.move(new VectorCoords(413, 10), Back.OUT, 250, 0, false);

	label.y = 294;
	label.alpha = 0f;
	label.move(140, 274, 1f, 1f, 0, 1f, Back.OUT, 800, 0, false);

	loadSavedDroids();
	Settings.saveSlot = 2;
    }

    @Override
    public void backPressed() {
	proceed.tweenCallback = new TweenCallback() {

	    @Override
	    public void tweenEventOccured(Types arg0, Tween arg1) {
		game.setScreen(new MainMenuScreen(game));
	    }
	};

	label.move(140, 294, 1f, 1f, 1, 0f, Back.INOUT, 250, 0, false);
	back.move(new VectorCoords(0 - back.width, 11), Back.OUT, 250, 0, false);
	proceed.move(new VectorCoords(480 + proceed.width, 10), Back.OUT, 250, 0, true);

	arrow.move(0f, Linear.INOUT, 250, 100, false);

	shadowOne.move(0f, Linear.INOUT, 250, 0, false);
	shadowTwo.move(0f, Linear.INOUT, 250, 0, false);
	shadowThree.move(0f, Linear.INOUT, 250, 0, false);
    }

    @Override
    public void dispose() {

    }

    @SuppressWarnings("unused")
    public Sprite load(int saveSlot) {
	Sprite droid = null;
	try {
	    ObjectInputStream droidStats = null, droidItems = null;
	    switch (saveSlot) {
		case 1:
		    Log.d("TEST", "[LoadDoodleDroidScreen] Reading slot 1 game save data...");
		    droidStats = new ObjectInputStream(game.getFileIO().readFile(".droid1"));
		    break;
		case 2:
		    Log.d("TEST", "[LoadDoodleDroidScreen] Reading slot 2 game save data...");
		    droidStats = new ObjectInputStream(game.getFileIO().readFile(".droid2"));
		    break;
		case 3:
		    Log.d("TEST", "[LoadDoodleDroidScreen] Reading slot 3 game save data...");
		    droidStats = new ObjectInputStream(game.getFileIO().readFile(".droid3"));
		    break;
		default:

	    }

	    if (droidStats != null) {
		int type = Integer.parseInt(droidStats.readLine());
		int color = Integer.parseInt(droidStats.readLine());

		switch (saveSlot) {
		    case 1:
			droidOneName = droidStats.readLine();
			break;
		    case 2:
			droidTwoName = droidStats.readLine();
			break;
		    case 3:
			droidThreeName = droidStats.readLine();
			break;
		    default:
		}
		droidStats.close();

		switch (type) {
		    case DoodleDroid.SUNDOT_KULANGOT:
			Log.d("TEST", "[LoadDoodleDroidScreen] Type is PASTILLAS.");
			switch (color) {
			    case Color.GREEN:
				droid = new Sprite(new TextureRegion(Assets.models, 86, 82, 86, 84));
				break;
			    case Color.BLUE:
				droid = new Sprite(new TextureRegion(Assets.models, 0, 82, 86, 84));
				break;
			    case Color.RED:
				droid = new Sprite(new TextureRegion(Assets.models, 172, 82, 86, 84));
				break;
			    case Color.YELLOW:
				droid = new Sprite(new TextureRegion(Assets.models, 344, 82, 86, 84));
				break;
			    case Color.WHITE:
				droid = new Sprite(new TextureRegion(Assets.models, 258, 82, 86, 84));
				break;
			    default:
			}
			break;
		    case DoodleDroid.PASTILLAS:
			Log.d("TEST", "[LoadDoodleDroidScreen] Type is SK.");
			switch (color) {
			    case Color.GREEN:
				droid = new Sprite(new TextureRegion(Assets.models, 92, 0, 92, 82));
				break;
			    case Color.BLUE:
				droid = new Sprite(new TextureRegion(Assets.models, 0, 0, 92, 82));
				break;
			    case Color.RED:
				droid = new Sprite(new TextureRegion(Assets.models, 184, 0, 92, 82));
				break;
			    case Color.YELLOW:
				droid = new Sprite(new TextureRegion(Assets.models, 276, 0, 92, 82));
				break;
			    case Color.WHITE:
				droid = new Sprite(new TextureRegion(Assets.models, 368, 0, 92, 82));
				break;
			    default:
			}
			break;
		    case DoodleDroid.YEMA:
			Log.d("TEST", "[LoadDoodleDroidScreen] Type is YEMA.");
			switch (color) {
			    case Color.GREEN:
				droid = new Sprite(new TextureRegion(Assets.models, 86, 82, 86, 84));
				break;
			    case Color.BLUE:
				droid = new Sprite(new TextureRegion(Assets.models, 0, 82, 86, 84));
				break;
			    case Color.RED:
				droid = new Sprite(new TextureRegion(Assets.models, 172, 82, 86, 84));
				break;
			    case Color.YELLOW:
				droid = new Sprite(new TextureRegion(Assets.models, 344, 82, 86, 84));
				break;
			    case Color.WHITE:
				droid = new Sprite(new TextureRegion(Assets.models, 258, 82, 86, 84));
				break;
			    default:
			}
			break;
		    default:
			Log.d("TEST", "[LoadDoodleDroidScreen] No Type was selected!.");
		}
	    } else
		Log.d("TEST", "[LoadDoodleDroidSCreen] DroidStats NULL!");
	} catch (NumberFormatException e) {
	    Log.d("TEST", "[LoadDoodleDroidSCreen] NFE @ load(int)!");
	} catch (NullPointerException e) {
	    Log.d("TEST", "[LoadDoodleDroidSCreen] NPE @ load(int)!");
	} catch (FileNotFoundException e) {
	    Log.d("TEST", "[LoadDoodleDroidSCreen] FNFE @ load(int)!");
	} catch (IOException e) {
	    Log.d("TEST", "[LoadDoodleDroidSCreen] OMG @ load(int)! Error: " + e.getMessage());
	} catch (Exception e) {
	    Log.d("TEST", "[LoadDoodleDroidSCreen] OMG @ load(int)! Error: " + e.getMessage());
	}
	return droid;
    }

    @Override
    public void menuPressed() {
	// TODO Auto-generated method stub

    }

    @Override
    public void onLoad() {
	switch (loadedDroid.type) {
	    case DoodleDroid.SUNDOT_KULANGOT:
		Log.d("TEST", "[LoadDoodleDroidSCreen] Loaded Droid is PASTILLAS type!");
		break;
	    case DoodleDroid.PASTILLAS:
		Log.d("TEST", "[LoadDoodleDroidSCreen] Loaded Droid is SK type!");
		break;
	    case DoodleDroid.YEMA:
		Log.d("TEST", "[LoadDoodleDroidSCreen] Loaded Droid is YEMA type!");
		break;
	    default:
		Log.d("TEST", "[LoadDoodleDroidSCreen] Loaded Droid is NULL!");
	}

	game.setDroid(loadedDroid);
	game.setScreen(new StageScreen(game, WorldMapScreen.HOME));
	Assets.mainMenuBGM.pause();

    }

    @Override
    public void onOkTapped(PromptScreen prompt) {
	for (int index = 0; index < displayObjects.size(); index++)
	    displayObjects.get(index).enabled = true;
	game.setScreen(this);
    }

    @Override
    public void onTouchEvent(DisplayObject source, TouchEvent event) {
	if (event.type == TouchEvent.TOUCH_TAP)
	    if (source.equals(back))
		backPressed();
	    else if (source.equals(shadowOne) || source.equals(droidOne)) {
		Settings.saveSlot = 1;
		arrow.move(new VectorCoords(98 - arrow.width / 2, arrow.y), Linear.INOUT, 250, 0, false);
	    } else if (source.equals(shadowTwo) || source.equals(droidTwo)) {
		arrow.move(new VectorCoords(240 - arrow.width / 2, arrow.y), Linear.INOUT, 250, 0, false);
		Settings.saveSlot = 2;
	    } else if (source.equals(shadowThree) || source.equals(droidThree)) {
		arrow.move(new VectorCoords(381 - arrow.width / 2, arrow.y), Linear.INOUT, 250, 0, false);
		Settings.saveSlot = 3;
	    } else if (source.equals(proceed))
		try {
		    Sprite droid = load(Settings.saveSlot);
		    if (droid == null)
			throw new NullPointerException();
		    else {
			loadedDroid = game.load(Settings.saveSlot);
			if (loadedDroid == null)
			    throw new NullPointerException();
			game.setScreen(new LoadingScreen((GLGame) game, this));
		    }
		} catch (NullPointerException e) {
		    game.setScreen(new PromptScreen(game, this, PromptScreen.OK_PROMPT, "NO DROID SELECTED!"));
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
	switch (Settings.saveSlot) {
	    case 1:
		arrow.visible = true;
		proceed.visible = true;
		if (droidOne != null)
		    droidName.text = droidOne.name;
		break;
	    case 2:
		arrow.visible = true;
		proceed.visible = true;
		if (droidTwo != null)
		    droidName.text = droidTwo.name;
		break;
	    case 3:
		arrow.visible = true;
		proceed.visible = true;
		if (droidThree != null)
		    droidName.text = droidThree.name;
		break;
	    default:
		arrow.visible = false;
		proceed.visible = false;
		droidName.text = "";
	}
    }

    /** Load the saved Doodle Droids and show them in the display. */
    private void loadSavedDroids() {

	droidOne = game.load(1);
	if (droidOne != null) {
	    droidOne.x = shadowOne.x + shadowOne.width / 2 - droidOne.getDisplayByState().width / 2;
	    droidOne.y = shadowOne.y + shadowOne.height / 2;
	    droidOne.touchCallback = this;
	    addChild(droidOne);
	}

	droidTwo = game.load(2);
	if (droidTwo != null) {
	    droidTwo.x = shadowTwo.x + shadowTwo.width / 2 - droidTwo.getDisplayByState().width / 2;
	    droidTwo.y = shadowTwo.y + shadowTwo.height / 2;
	    droidTwo.touchCallback = this;
	    addChild(droidTwo);
	}

	droidThree = game.load(3);
	if (droidThree != null) {
	    droidThree.x = shadowThree.x + shadowThree.width / 2 - droidThree.getDisplayByState().width / 2;
	    droidThree.y = shadowThree.y + shadowThree.height / 2;
	    droidThree.touchCallback = this;
	    addChild(droidThree);
	}
    }

}
