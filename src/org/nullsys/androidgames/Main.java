package org.nullsys.androidgames;

import android.graphics.Color;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.nullsys.androidgames.doodledroids.Assets;
import org.nullsys.androidgames.doodledroids.DoodleDroid;
import org.nullsys.androidgames.doodledroids.screen.LoadingScreen;
import org.nullsys.androidgames.doodledroids.screen.LoadingScreen.LoadingScreenCallback;
import org.nullsys.androidgames.doodledroids.screen.SplashScreen;
import org.nullsys.androidgames.framework.Screen;
import org.nullsys.androidgames.framework.impl.GLGame;

public class Main extends GLGame implements LoadingScreenCallback {

    boolean firstTimeCreate = true;

    @Override
    public Screen getStartScreen() {
	Assets.game = this;
	Assets.loadFonts();
	return new LoadingScreen(this, this);
    }

    @Override
    public void onLoad() {
	Assets.loadWorld();
	Assets.load();
	Assets.loadPastillasDroids();
	Assets.loadSundotKulangotDroids();
	Assets.loadYemaDroids();
	setDroid(new DoodleDroid(Color.BLUE, DoodleDroid.YEMA));
	setScreen(new SplashScreen(this));
    }

    @Override
    public void onPause() {
	super.onPause();
	super.world.running = false;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
	super.onSurfaceCreated(gl, config);
	if (firstTimeCreate)
	    firstTimeCreate = false;
	else {
	    Log.d("TEST", "[Main] Reloaded!");
	    Assets.reload();
	}
    }
}
