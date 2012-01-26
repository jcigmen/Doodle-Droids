package org.nullsys.androidgames.doodledroids.screen.mainmenu;

import aurelienribon.tweenengine.equations.Linear;

import org.nullsys.androidgames.doodledroids.Assets;
import org.nullsys.androidgames.doodledroids.Settings;
import org.nullsys.androidgames.doodledroids.screen.mainmenu.create.EnterNameScreen;
import org.nullsys.androidgames.framework.Game;
import org.nullsys.androidgames.framework.Input.TouchEvent;
import org.nullsys.androidgames.framework.display.Button;
import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.Sprite;
import org.nullsys.androidgames.framework.display.TextureRegion;
import org.nullsys.androidgames.framework.display.event.TouchEventCallback;
import org.nullsys.androidgames.framework.impl.GLScreen;

public class MainMenuScreen extends GLScreen implements TouchEventCallback {

    Sprite universalPanel;

    Sprite title;

    Sprite musicOn;

    Sprite musicOff;

    Sprite soundOn;

    Sprite soundOff;

    Button create;

    Button load;

    Button exit;

    public MainMenuScreen(Game game) {
	super(game);

	if (Settings.musicEnabled && !Assets.mainMenuBGM.isPlaying())
	    Assets.mainMenuBGM.play();

	universalPanel = new Sprite(new TextureRegion(Assets.mainMenu, 0, 0, 480, 320));
	addChild(universalPanel);

	title = new Sprite(new TextureRegion(Assets.mainMenu, 277, 342, 221, 130));
	title.x = 132;
	title.y = 174;
	addChild(title);

	musicOn = new Sprite(new TextureRegion(Assets.mainMenu, 846, 44, 36, 38));
	musicOn.x = 421;
	musicOn.y = 19;
	musicOn.alpha = 0f;
	musicOn.visible = Settings.musicEnabled;
	musicOn.touchCallback = this;
	addChild(musicOn);

	musicOff = new Sprite(new TextureRegion(Assets.mainMenu, 846, 0, 36, 44));
	musicOff.x = 421;
	musicOff.y = 18;
	musicOff.alpha = 0f;
	musicOff.visible = !musicOn.visible;
	musicOff.touchCallback = this;
	addChild(musicOff);

	soundOn = new Sprite(new TextureRegion(Assets.mainMenu, 791, 50, 44, 39));
	soundOn.x = 362;
	soundOn.y = 21;
	soundOn.alpha = 0f;
	soundOn.visible = Settings.soundEnabled;
	soundOn.touchCallback = this;
	addChild(soundOn);

	soundOff = new Sprite(new TextureRegion(Assets.mainMenu, 801, 0, 45, 50));
	soundOff.x = 361;
	soundOff.y = 14;
	soundOff.alpha = 0f;
	soundOff.visible = !soundOn.visible;
	soundOff.touchCallback = this;
	addChild(soundOff);

	create = new Button(new TextureRegion(Assets.mainMenu, 482, 25, 136, 84), new TextureRegion(Assets.mainMenu, 482, 109, 102, 63));
	create.x = 98;
	create.y = 80;
	create.alpha = 0f;
	create.downstate.x = 115;
	create.downstate.y = 90;
	create.touchCallback = this;
	create.tapSound = Assets.confirm;
	addChild(create);

	load = new Button(new TextureRegion(Assets.mainMenu, 618, 25, 136, 84), new TextureRegion(Assets.mainMenu, 584, 109, 102, 63));
	load.x = 246;
	load.y = 80;
	load.alpha = 0f;
	load.downstate.x = 263;
	load.downstate.y = 90;
	load.touchCallback = this;
	load.tapSound = Assets.confirm;
	addChild(load);

	exit = new Button(new TextureRegion(Assets.mainMenu, 754, 2, 47, 46), new TextureRegion(Assets.mainMenu, 754, 49, 37, 37));
	exit.x = 18;
	exit.y = 18;
	exit.alpha = 0f;
	exit.downstate.x = 23;
	exit.downstate.y = 22;
	exit.touchCallback = this;
	exit.tapSound = Assets.cancel2;
	addChild(exit);

	for (int index = 0; index < displayObjects.size(); index++)
	    displayObjects.get(index).move(1.0f, Linear.INOUT, 200, 0, false);
    }

    @Override
    public void backPressed() {
	game.exit();
    }

    @Override
    public void dispose() {
    }

    @Override
    public void menuPressed() {
    }

    @Override
    public void onTouchEvent(DisplayObject source, TouchEvent event) {
	switch (event.type) {
	    case TouchEvent.TOUCH_DOWN:
		break;
	    case TouchEvent.TOUCH_TAP:
		if (source.equals(musicOn)) {
		    Settings.musicEnabled = false;
		    Assets.mainMenuBGM.pause();
		    if (Settings.soundEnabled)
			Assets.confirm.play(1);
		} else if (source.equals(musicOff)) {
		    Settings.musicEnabled = true;
		    Assets.mainMenuBGM.play();
		    if (Settings.soundEnabled)
			Assets.cancel1.play(1);
		} else if (source.equals(soundOn)) {
		    Settings.soundEnabled = false;
		    Assets.confirm.play(1);
		} else if (source.equals(soundOff)) {
		    Settings.soundEnabled = true;
		    Assets.cancel1.play(1);
		} else if (source.equals(create))
		    game.setScreen(new EnterNameScreen(game, true));
		else if (source.equals(load))
		    game.setScreen(new LoadDoodleDroidScreen(game));
		else if (source.equals(exit)) {
		    game.exit();
		    Assets.mainMenuBGM.pause();
		}
		break;
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
	musicOn.visible = Settings.musicEnabled;
	musicOff.visible = !Settings.musicEnabled;

	soundOn.visible = Settings.soundEnabled;
	soundOff.visible = !Settings.soundEnabled;
    }

}
