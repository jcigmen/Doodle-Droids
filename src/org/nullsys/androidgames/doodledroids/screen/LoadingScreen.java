package org.nullsys.androidgames.doodledroids.screen;

import android.util.Log;

import org.nullsys.androidgames.doodledroids.Assets;
import org.nullsys.androidgames.framework.display.text.Text;
import org.nullsys.androidgames.framework.impl.GLGame;
import org.nullsys.androidgames.framework.impl.GLScreen;

public class LoadingScreen extends GLScreen {

    public interface LoadingScreenCallback {

	public void onLoad();
    }

    private LoadingScreenCallback loadScreenCallback;

    private float elapsedTime = 0f;

    public LoadingScreen(GLGame game, LoadingScreenCallback loadScreenCallback) {
	super(game);
	Log.d("TEST", "[LoadingScreen] STARTING LOADING SCREEN...");

	this.loadScreenCallback = loadScreenCallback;

	Text nowLoading = new Text(Assets.poohWhiteStroked, "Now Loading...");
	nowLoading.x = 0;
	nowLoading.y = 0 + nowLoading.getHeight() / 2;
	addChild(nowLoading);
    }

    @Override
    public void backPressed() {

    }

    @Override
    public void dispose() {
	// TODO Auto-generated method stub

    }

    @Override
    public void menuPressed() {
	// TODO Auto-generated method stub
    }

    @Override
    public void pause() {
	// TODO Auto-generated method stub

    }

    @Override
    public void resume() {

    }

    @Override
    public void update(float deltaTime) {
	if (elapsedTime >= 1)
	    loadScreenCallback.onLoad();
	elapsedTime += deltaTime;
    }

}
