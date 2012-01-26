package org.nullsys.androidgames.doodledroids.screen;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Linear;

import org.nullsys.androidgames.doodledroids.Assets;
import org.nullsys.androidgames.doodledroids.screen.mainmenu.TitleScreen;
import org.nullsys.androidgames.framework.Game;
import org.nullsys.androidgames.framework.display.Sprite;
import org.nullsys.androidgames.framework.display.TextureRegion;
import org.nullsys.androidgames.framework.impl.GLScreen;

public class SplashScreen extends GLScreen {

    Sprite splash;

    float timeElapsed = 0;

    public SplashScreen(Game game) {
	super(game);

	splash = new Sprite(new TextureRegion(Assets.splash, 0, 0, 326, 54));
	splash.x = (480 - splash.width) / 2;
	splash.y = (320 - splash.height) / 2;
	splash.alpha = 0f;
	addChild(splash);

	splash.move(1f, Linear.INOUT, 500, 0, false);

	splash.tweenCallback = new TweenCallback() {

	    @Override
	    public void tweenEventOccured(Types arg0, Tween arg1) {
		SplashScreen.this.game.setScreen(new TitleScreen(SplashScreen.this.game));
	    }
	};
    }

    @Override
    public void backPressed() {
	splash.move(0f, Linear.INOUT, 500, 0, true);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void menuPressed() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void updateChildren(float deltaTime) {
	for (int displayObjectIndex = 0; displayObjectIndex < displayObjects.size(); displayObjectIndex++)
	    displayObjects.get(displayObjectIndex).update(deltaTime);
	timeElapsed += deltaTime;
	if (game.getInput().getTouchEvents().size() > 0 || timeElapsed >= 10)
	    splash.move(0f, Linear.INOUT, 500, 0, true);
    }

}
