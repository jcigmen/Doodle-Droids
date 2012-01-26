package org.nullsys.androidgames.framework.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import aurelienribon.tweenengine.Tween;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.nullsys.androidgames.doodledroids.Assets;
import org.nullsys.androidgames.doodledroids.DoodleDroid;
import org.nullsys.androidgames.doodledroids.DoodleDroid.State;
import org.nullsys.androidgames.doodledroids.Settings;
import org.nullsys.androidgames.doodledroids.World;
import org.nullsys.androidgames.doodledroids.item.Apparel;
import org.nullsys.androidgames.doodledroids.item.Headgear;
import org.nullsys.androidgames.doodledroids.item.ItemFactory;
import org.nullsys.androidgames.framework.Audio;
import org.nullsys.androidgames.framework.FileIO;
import org.nullsys.androidgames.framework.Game;
import org.nullsys.androidgames.framework.Input;
import org.nullsys.androidgames.framework.Screen;
import org.nullsys.androidgames.framework.event.GameSaveCallback;

public abstract class GLGame extends Activity implements Game, Renderer {

    public enum GLGameState {
	Initializing, Initialized, Running, Paused, Finished, Idle
    }

    protected World world;
    protected DoodleDroid droid;
    protected GLSurfaceView glView;
    protected GLGraphics glGraphics;
    protected Audio audio;
    protected Input input;
    protected FileIO fileIO;
    protected Screen screen;
    public GLGameState state = GLGameState.Initialized;
    public Object stateChanged = new Object();
    protected long startTime = System.nanoTime();
    protected WakeLock wakeLock;

    @Override
    public void exit() {
	saveSettings(null);
	finish();
	Assets.unload();
	world.interrupt();
	System.exit(0);
    }

    @Override
    public Audio getAudio() {
	return audio;
    }

    @Override
    public Screen getCurrentScreen() {
	return screen;
    }

    @Override
    public DoodleDroid getDroid() {
	return droid;
    }

    @Override
    public FileIO getFileIO() {
	return fileIO;
    }

    public GLGraphics getGLGraphics() {
	return glGraphics;
    }

    @Override
    public Input getInput() {
	return input;
    }

    @Override
    public World getWorld() {
	return world;
    }

    @Override
    public DoodleDroid load(int saveSlot) {
	DoodleDroid droid = null;
	ObjectInputStream droidStats = null, droidItems = null;
	try {
	    switch (saveSlot) {
		case 1:
		    Log.d("TEST", "[GLGame] Reading slot 1 game save data...");
		    droidItems = new ObjectInputStream(fileIO.readFile(".droid1i"));
		    droidStats = new ObjectInputStream(fileIO.readFile(".droid1"));
		    break;
		case 2:
		    Log.d("TEST", "[GLGame] Reading slot 2 game save data...");
		    droidItems = new ObjectInputStream(fileIO.readFile(".droid2i"));
		    droidStats = new ObjectInputStream(fileIO.readFile(".droid2"));
		    break;
		case 3:
		    Log.d("TEST", "[GLGame] Reading slot 3 game save data...");
		    droidItems = new ObjectInputStream(fileIO.readFile(".droid3i"));
		    droidStats = new ObjectInputStream(fileIO.readFile(".droid3"));
		    break;
		default:

	    }

	    if (droidItems != null && droidStats != null) {
		Log.d("TEST", "[GLGame] Reading game save data...");
		int type = Integer.parseInt(droidStats.readLine());
		int color = Integer.parseInt(droidStats.readLine());

		switch (type) {
		    case DoodleDroid.PASTILLAS:
			Log.d("TEST", "[GLGame] Droid is type PASTILLAS!");
			break;
		    case DoodleDroid.SUNDOT_KULANGOT:
			Log.d("TEST", "[GLGame] Droid is type SUNDOT KULANGOT!");
			break;
		    case DoodleDroid.YEMA:
			Log.d("TEST", "[GLGame] Droid is type YEMA!");
			break;
		}

		droid = new DoodleDroid(type, color);

		// Bind the stats
		droid.name = droidStats.readLine();
		droid.level = Integer.parseInt(droidStats.readLine());
		droid.exp = Integer.parseInt(droidStats.readLine());
		droid.expTotal = Integer.parseInt(droidStats.readLine());
		droid.expNxtLvl = Integer.parseInt(droidStats.readLine());
		droid.energy = Integer.parseInt(droidStats.readLine());
		droid.energyTotal = Integer.parseInt(droidStats.readLine());
		droid.health = Integer.parseInt(droidStats.readLine());
		droid.healthTotal = Integer.parseInt(droidStats.readLine());
		droid.freeStats = Integer.parseInt(droidStats.readLine());
		droid.luck = Integer.parseInt(droidStats.readLine());
		droid.droills = Integer.parseInt(droidStats.readLine());
		droid.hasVirus = Boolean.parseBoolean(droidStats.readLine());
		droid.hasWorm = Boolean.parseBoolean(droidStats.readLine());
		droid.hasHardwareFailure = Boolean.parseBoolean(droidStats.readLine());

		droid.state = droid.hasVirus || droid.hasWorm || droid.hasHardwareFailure ? State.SICK_IDLE : State.NORMAL_IDLE;

		// Load the items
		int size = Integer.parseInt(droidItems.readLine());
		for (int index = 0; index < size; index++)
		    droid.usableItems.add(ItemFactory.getInventoryItem(droidItems.readLine()));
		size = Integer.parseInt(droidItems.readLine());
		for (int index = 0; index < size; index++)
		    droid.equipment.add(ItemFactory.getEquippable(droidItems.readLine()));
		droid.headgear = (Headgear) ItemFactory.getEquippable(droidItems.readLine());
		droid.apparel = (Apparel) ItemFactory.getEquippable(droidItems.readLine());
	    }
	} catch (NumberFormatException e) {
	    Log.d("TEST", "[GLGame] NFE @ load(int)!");
	} catch (NullPointerException e) {
	    Log.d("TEST", "[GLGame] NPE @ load(int)!");
	} catch (FileNotFoundException e) {
	    Log.d("TEST", "[GLGame] FNFE @ load(int)!");
	} catch (IOException e) {
	    Log.d("TEST", "[GLGame] OMG @ load(int)! Error: " + e.getMessage());
	} catch (Exception e) {
	    Log.d("TEST", "[GLGame] OMG @ load(int)! Error: " + e.getMessage());
	} finally {
	    try {
		if (droidItems != null)
		    droidItems.close();
		if (droidStats != null)
		    droidStats.close();
	    } catch (IOException e) {
	    }
	}
	return droid;
    }

    @Override
    public void loadSettings() {
	try {
	    ObjectInputStream in = new ObjectInputStream(fileIO.readFile(".settings"));
	    Settings.soundEnabled = Boolean.parseBoolean(in.readLine());
	    Settings.musicEnabled = Boolean.parseBoolean(in.readLine());
	    world.worldSeconds = Integer.parseInt(in.readLine());
	    world.worldMinutes = Integer.parseInt(in.readLine());
	    world.worldHours = Integer.parseInt(in.readLine());
	    in.close();
	} catch (NullPointerException e) {
	    Log.d("TEST", "[GLGame] NPE @ loadSettings()");
	} catch (FileNotFoundException e) {
	    Log.d("TEST", "[GLGame] .settings file not found");
	} catch (IOException e) {
	    Log.d("TEST", "[GLGame] OMG @ loadSettings()" + e);
	} catch (Exception e) {
	    Log.d("TEST", "[GLGame] OMG @ loadSettings()! Error: " + e.getMessage());
	}
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	Log.d("TEST", "[GLGame] Activity created!");

	requestWindowFeature(Window.FEATURE_NO_TITLE);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

	PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
	wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");

	glView = new GLSurfaceView(this);
	glView.setRenderer(this);
	setContentView(glView);
	glGraphics = new GLGraphics(glView);
	fileIO = new AndroidFileIO(getAssets());
	audio = new AndroidAudio(this);
	world = new World();

	float screenWidth = getWindowManager().getDefaultDisplay().getWidth();
	float screenHeight = getWindowManager().getDefaultDisplay().getHeight();
	float scaleX = 480 / screenWidth;
	float scaleY = 320 / screenHeight;
	Log.d("TEST", "[GLGame] Finished instantiating materials!");
	input = new AndroidInput(this, glView, scaleX, scaleY);

	Tween.setPoolEnabled(true);
	loadSettings();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
	GLGameState state = null;

	synchronized (stateChanged) {
	    state = this.state;
	}

	if (state == GLGameState.Running) {
	    float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
	    startTime = System.nanoTime();

	    screen.present(deltaTime);
	    screen.update(deltaTime);
	    screen.updateChildren(deltaTime);
	}

	if (state == GLGameState.Paused) {
	    screen.pause();
	    synchronized (stateChanged) {
		this.state = GLGameState.Idle;
		stateChanged.notifyAll();
	    }
	}

	if (state == GLGameState.Finished) {
	    screen.pause();
	    screen.dispose();
	    synchronized (stateChanged) {
		this.state = GLGameState.Idle;
		stateChanged.notifyAll();
	    }
	}
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	switch (keyCode) {
	    case KeyEvent.KEYCODE_MENU:
		screen.menuPressed();
		break;
	    case KeyEvent.KEYCODE_BACK:
		screen.backPressed();
		break;
	}

	return true;
    }

    @Override
    public void onPause() {
	synchronized (stateChanged) {
	    if (isFinishing())
		state = GLGameState.Finished;
	    else
		state = GLGameState.Paused;
	    while (true)
		try {
		    stateChanged.wait();
		    break;
		} catch (InterruptedException e) {
		}
	}
	Assets.unload();
	world.running = false;
	wakeLock.release();
	glView.onPause();
	super.onPause();
    }

    @Override
    public void onResume() {
	super.onResume();
	world.running = true;
	glView.onResume();
	wakeLock.acquire();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
	glGraphics.setGL(gl);

	synchronized (stateChanged) {
	    if (state == GLGameState.Initialized)
		screen = getStartScreen();
	    state = GLGameState.Running;
	    screen.resume();
	    startTime = System.nanoTime();
	}
    }

    @Override
    public void save(GameSaveCallback callback) {
	try {
	    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
		// Save the current doodle droid.
		ObjectOutputStream droidStats = null, droidItems = null;
		switch (Settings.saveSlot) {
		    case 1:
			droidItems = new ObjectOutputStream(fileIO.writeFile(".droid1i"));
			droidStats = new ObjectOutputStream(fileIO.writeFile(".droid1"));
			break;
		    case 2:
			droidItems = new ObjectOutputStream(fileIO.writeFile(".droid2i"));
			droidStats = new ObjectOutputStream(fileIO.writeFile(".droid2"));
			break;
		    case 3:
			droidItems = new ObjectOutputStream(fileIO.writeFile(".droid3i"));
			droidStats = new ObjectOutputStream(fileIO.writeFile(".droid3"));
			break;
		    default:
			Settings.saveSlot = 1;
			callback.onNoSlotSelected();
			return;
		}
		Log.d("TEST", "[GLGame] Saving Droid...");

		Log.d("TEST", "[GLGame] Writing Items Data...");
		droidItems.writeBytes("" + droid.usableItems.size() + "\n");
		for (int index = 0; index < droid.usableItems.size(); index++)
		    droidItems.writeBytes(droid.usableItems.get(index).name + "\n");
		droidItems.writeBytes("" + droid.equipment.size() + "\n");
		for (int index = 0; index < droid.equipment.size(); index++)
		    droidItems.writeBytes(droid.equipment.get(index).getName() + "\n");

		try {
		    droidItems.writeBytes(droid.headgear.name + "\n");
		} catch (NullPointerException e) {
		    Log.d("TEST", "[GLGame] Droid Headgear is NULL!");
		}

		try {
		    droidItems.writeBytes(droid.apparel.name + "\n");
		} catch (NullPointerException e) {
		    Log.d("TEST", "[GLGame] Droid Apparel is NULL!");
		}
		droidItems.close();

		Log.d("TEST", "[GLGame] Writing Statistics...");
		droidStats.writeBytes("" + droid.type + "\n");
		droidStats.writeBytes("" + droid.color + "\n");
		droidStats.writeBytes("" + droid.name + "\n");
		droidStats.writeBytes("" + droid.level + "\n");
		droidStats.writeBytes("" + droid.exp + "\n");
		droidStats.writeBytes("" + droid.expTotal + "\n");
		droidStats.writeBytes("" + droid.expNxtLvl + "\n");
		droidStats.writeBytes("" + droid.energy + "\n");
		droidStats.writeBytes("" + droid.energyTotal + "\n");
		droidStats.writeBytes("" + droid.health + "\n");
		droidStats.writeBytes("" + droid.healthTotal + "\n");
		droidStats.writeBytes("" + droid.freeStats + "\n");
		droidStats.writeBytes("" + droid.luck + "\n");
		droidStats.writeBytes("" + droid.droills + "\n");
		droidStats.writeBytes("" + droid.hasVirus + "\n");
		droidStats.writeBytes("" + droid.hasWorm + "\n");
		droidStats.writeBytes("" + droid.hasHardwareFailure + "\n");
		droidStats.close();
	    } else
		callback.onSdCardNotFound();
	} catch (NullPointerException e) {
	    Log.d("TEST", "[GLGame] NPE @ save(GameSaveCallback)");
	} catch (FileNotFoundException e) {
	    Log.d("TEST", "[GLGame] FNE @ save(GameSaveCallback)" + e);
	} catch (IOException e) {
	    Log.d("TEST", "[GLGame] OMG @ save(GameSaveCallback)");
	}
    }

    @Override
    public void saveSettings(GameSaveCallback callback) {
	try {
	    // Write the .settings file
	    ObjectOutputStream out = new ObjectOutputStream(fileIO.writeFile(".settings"));
	    out.writeBytes(Boolean.toString(Settings.soundEnabled) + "\n");
	    out.writeBytes(Boolean.toString(Settings.musicEnabled) + "\n");
	    out.writeBytes("" + world.worldSeconds + "\n");
	    out.writeBytes("" + world.worldMinutes + "\n");
	    out.writeBytes("" + world.worldHours + "\n");
	    out.close();
	} catch (NullPointerException e) {
	    Log.d("TEST", "NPE @ Game Settings Save!");
	} catch (FileNotFoundException e) {
	    Log.d("TEST", "File not found!");
	} catch (IOException e) {
	    Log.d("TEST", "There was some kind of an error saving... " + e);
	}
    }

    @Override
    public void setDroid(DoodleDroid droid) {
	this.droid = droid;
    }

    @Override
    public void setScreen(Screen screen) {
	if (screen == null)
	    throw new IllegalArgumentException("Screen must not be null");

	this.screen.pause();
	this.screen.dispose();
	screen.resume();
	screen.update(0);
	this.screen = screen;
    }
}
