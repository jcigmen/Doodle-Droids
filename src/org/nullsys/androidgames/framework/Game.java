package org.nullsys.androidgames.framework;

import org.nullsys.androidgames.doodledroids.DoodleDroid;
import org.nullsys.androidgames.doodledroids.World;
import org.nullsys.androidgames.framework.event.GameSaveCallback;

public interface Game {

    public void exit();

    public Audio getAudio();

    public Screen getCurrentScreen();

    public DoodleDroid getDroid();

    public FileIO getFileIO();

    public Input getInput();

    public Screen getStartScreen();

    public World getWorld();

    public DoodleDroid load(int saveSlot);

    public void loadSettings();

    public void save(GameSaveCallback callback);

    public void saveSettings(GameSaveCallback callback);

    public void setDroid(DoodleDroid droid);

    public void setScreen(Screen screen);
}