package org.nullsys.androidgames.doodledroids.screen;

import aurelienribon.tweenengine.equations.Bounce;

import javax.microedition.khronos.opengles.GL10;

import org.nullsys.androidgames.doodledroids.Assets;
import org.nullsys.androidgames.doodledroids.Settings;
import org.nullsys.androidgames.doodledroids.screen.LoadingScreen.LoadingScreenCallback;
import org.nullsys.androidgames.doodledroids.screen.mainmenu.MainMenuScreen;
import org.nullsys.androidgames.framework.Game;
import org.nullsys.androidgames.framework.Input.TouchEvent;
import org.nullsys.androidgames.framework.display.Button;
import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.Sprite;
import org.nullsys.androidgames.framework.display.TextureRegion;
import org.nullsys.androidgames.framework.display.text.Text;
import org.nullsys.androidgames.framework.display.text.Text.TextRegistration;
import org.nullsys.androidgames.framework.impl.GLGame;
import org.nullsys.androidgames.framework.impl.GLScreen;
import org.nullsys.androidgames.framework.math.VectorCoords;

public class PauseScreen extends GLScreen implements LoadingScreenCallback {

    StageScreen stageScreen;

    PromptScreen prompt;

    Button returnToMainMenu;

    Button save;

    Sprite musicOn;

    Sprite musicOff;

    Sprite soundOn;

    Sprite soundOff;

    public PauseScreen(Game game, StageScreen stageScreen) {
	super(game);

	if (Settings.musicEnabled && !Assets.stageBGM.isPlaying())
	    Assets.stageBGM.play();

	this.stageScreen = stageScreen;

	for (int index = 0; index < stageScreen.displayObjects.size(); index++)
	    stageScreen.displayObjects.get(index).enabled = false;

	Sprite background = new Sprite(new TextureRegion(Assets.quest, 377, 207, 100, 67));
	background.width = 480;
	background.height = 320;
	addChild(background);

	Sprite pau = new Sprite(new TextureRegion(Assets.mainMenu, 200, 901, 140, 76));
	pau.x = 109;
	pau.y = 164;
	addChild(pau);

	Text t = new Text(Assets.poohWhiteStroked, "PRESS THE BACK\nBUTTON TO RESUME");
	t.registration = TextRegistration.CENTER;
	t.x = 240;
	t.y = 140;
	addChild(t);

	Sprite s = new Sprite(new TextureRegion(Assets.mainMenu, 337, 903, 46, 72));
	s.x = 246;
	s.y = 166;
	addChild(s);

	s.y += 100;
	s.move(new VectorCoords(s.x, s.y - 100), Bounce.OUT, 1000, 0, false);

	Sprite ed = new Sprite(new TextureRegion(Assets.mainMenu, 382, 904, 94, 71));
	ed.x = 291;
	ed.y = 166;
	addChild(ed);

	returnToMainMenu = new Button(new TextureRegion(Assets.mainMenu, 896, 0, 55, 53), new TextureRegion(Assets.mainMenu, 903, 54, 41, 39));
	returnToMainMenu.x = 7;
	returnToMainMenu.y = 6;
	returnToMainMenu.downstate.x = 14;
	returnToMainMenu.downstate.y = 13;
	returnToMainMenu.touchCallback = this;
	returnToMainMenu.tapSound = Assets.cancel1;
	addChild(returnToMainMenu);

	musicOn = new Sprite(new TextureRegion(Assets.mainMenu, 846, 44, 36, 38));
	musicOn.x = 350;
	musicOn.y = 14;
	musicOn.visible = Settings.musicEnabled;
	musicOn.touchCallback = this;
	addChild(musicOn);

	musicOff = new Sprite(new TextureRegion(Assets.mainMenu, 846, 0, 36, 44));
	musicOff.x = 350;
	musicOff.y = 13;
	musicOff.visible = !musicOn.visible;
	musicOff.touchCallback = this;
	addChild(musicOff);

	soundOn = new Sprite(new TextureRegion(Assets.mainMenu, 791, 50, 44, 39));
	soundOn.x = 279;
	soundOn.y = 15;
	soundOn.visible = Settings.soundEnabled;
	soundOn.touchCallback = this;
	addChild(soundOn);

	soundOff = new Sprite(new TextureRegion(Assets.mainMenu, 801, 0, 45, 50));
	soundOff.x = 278;
	soundOff.y = 8;
	soundOff.visible = !soundOn.visible;
	soundOff.touchCallback = this;
	addChild(soundOff);

	save = new Button(new TextureRegion(Assets.mainMenu, 507, 300, 47, 47), new TextureRegion(Assets.mainMenu, 561, 306, 35, 35));
	save.x = 415;
	save.y = 12;
	save.downstate.x = 420;
	save.downstate.y = 17;
	save.touchCallback = this;
	save.tapSound = Assets.confirm;
	addChild(save);
    }

    @Override
    public void backPressed() {
	for (int index = 0; index < stageScreen.displayObjects.size(); index++)
	    stageScreen.displayObjects.get(index).enabled = true;
	game.setScreen(stageScreen);
	Assets.stageBGM.setVolume(.5f);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void menuPressed() {
    }

    @Override
    public void onLoad() {
	game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void onNoTapped(PromptScreen prompt) {
	for (int index = 0; index < stageScreen.displayObjects.size(); index++)
	    stageScreen.displayObjects.get(index).enabled = false;
	game.setScreen(this);
    }

    @Override
    public void onOkTapped(PromptScreen prompt) {
    }

    @Override
    public void onTouchEvent(DisplayObject source, TouchEvent event) {
	if (event.type == TouchEvent.TOUCH_TAP)
	    if (source.equals(returnToMainMenu)) {
		prompt = new PromptScreen(game, stageScreen, PromptScreen.YES_NO_PROMPT, "RETURN TO\nMAIN MENU?");
		game.setScreen(prompt);
	    } else if (source.equals(musicOn)) {
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
	    } else if (source.equals(save)) {
		game.save(null);
		game.setScreen(new PromptScreen(game, stageScreen, PromptScreen.OK_PROMPT, "GAME SAVED!"));
	    }
    }

    @Override
    public void onYesTapped(PromptScreen prompt) {
	game.setScreen(new LoadingScreen((GLGame) game, this));
	if (Settings.musicEnabled) {
	    Assets.stageBGM.pause();
	    Assets.mainMenuBGM.play();
	}
    }

    @Override
    public void pause() {
	Assets.stageBGM.pause();
    }

    @Override
    public void present(float deltatime) {
	GL10 gl = glGraphics.getGL();
	gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	camera.setViewportAndMatrices();

	gl.glEnable(GL10.GL_TEXTURE_2D);
	gl.glEnable(GL10.GL_BLEND);
	gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

	for (int index = 0; index < stageScreen.displayObjects.size(); index++)
	    if (stageScreen.displayObjects.get(index).visible) {
		gl.glColor4f(stageScreen.displayObjects.get(index).r, stageScreen.displayObjects.get(index).g, stageScreen.displayObjects.get(index).b, stageScreen.displayObjects.get(index).alpha);
		stageScreen.displayObjects.get(index).render(batcher);
	    }

	for (int index = 0; index < displayObjects.size(); index++)
	    if (displayObjects.get(index).visible) {
		gl.glColor4f(displayObjects.get(index).r, displayObjects.get(index).g, displayObjects.get(index).b, displayObjects.get(index).alpha);
		displayObjects.get(index).render(batcher);
	    }

	gl.glDisable(GL10.GL_BLEND);
    }

    @Override
    public void resume() {
	if (Settings.musicEnabled && !Assets.stageBGM.isPlaying())
	    Assets.stageBGM.play();
    }

    @Override
    public void update(float deltaTime) {
	musicOn.visible = Settings.musicEnabled;
	musicOff.visible = !Settings.musicEnabled;

	soundOn.visible = Settings.soundEnabled;
	soundOff.visible = !Settings.soundEnabled;
    }

}
