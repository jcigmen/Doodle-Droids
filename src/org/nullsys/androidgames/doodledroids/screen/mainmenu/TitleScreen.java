package org.nullsys.androidgames.doodledroids.screen.mainmenu;

import android.util.Log;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Linear;

import org.nullsys.androidgames.doodledroids.Assets;
import org.nullsys.androidgames.doodledroids.Settings;
import org.nullsys.androidgames.framework.Game;
import org.nullsys.androidgames.framework.Input.TouchEvent;
import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.Sprite;
import org.nullsys.androidgames.framework.display.TextureRegion;
import org.nullsys.androidgames.framework.impl.GLScreen;

public class TitleScreen extends GLScreen implements TweenCallback {

    Sprite background;

    Sprite title;

    Sprite tapTheScreen;

    boolean fading = true;

    public TitleScreen(Game game) {
	super(game);

	if (Settings.musicEnabled)
	    Assets.mainMenuBGM.play();

	background = new Sprite(new TextureRegion(Assets.mainMenu, 0, 0, 1, 1));
	background.width = 480;
	background.height = 320;
	background.touchCallback = this;
	background.tweenCallback = this;
	addChild(background);

	title = new Sprite(new TextureRegion(Assets.mainMenu, 528, 521, 387, 225));
	title.x = 46;
	title.y = 56;
	title.touchCallback = this;
	title.tweenCallback = this;
	addChild(title);

	tapTheScreen = new Sprite(new TextureRegion(Assets.mainMenu, 480, 0, 232, 25));
	tapTheScreen.x = (480 - tapTheScreen.width) / 2;
	tapTheScreen.y = 15;
	addChild(tapTheScreen);
    }

    @Override
    public void backPressed() {
	Assets.mainMenuBGM.pause();
	game.exit();
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
	Log.d("TEST", "[TitleScreen] Tapped!");
	background.move(0.0f, Linear.INOUT, 200, 0, true);
	title.move(0.0f, Linear.INOUT, 200, 0, true);
	tapTheScreen.move(0.0f, Linear.INOUT, 200, 0, false);
	fading = false;
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
    public void tweenEventOccured(Types arg0, Tween arg1) {
	if (arg0 == Types.COMPLETE) {
	    Log.d("TEST", "[TitleScreen] Switching to main menu...");
	    game.setScreen(new MainMenuScreen(game));
	}
    }

    @Override
    public void update(float deltaTime) {
	if (fading && tapTheScreen.alpha == 1.0f)
	    tapTheScreen.move(0f, Linear.INOUT, 350, 0, false);
	else if (fading && tapTheScreen.alpha == 0f)
	    tapTheScreen.move(1.0f, Linear.INOUT, 350, 0, false);
    }

}
