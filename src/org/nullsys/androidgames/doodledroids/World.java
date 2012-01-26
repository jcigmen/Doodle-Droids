package org.nullsys.androidgames.doodledroids;


public class World extends Thread {

    public WorldTimeCallback worldCallback;

    public boolean running = false;

    public int worldSeconds = 0;

    public int worldMinutes = 0;

    public int worldHours = 0;

    public World() {
	super.setName("World Thread");
    }

    @Override
    public void run() throws IllegalThreadStateException {
	running = true;
	while (true)
	    while (running)
		try {
		    sleep(1000);
		    if (worldSeconds < 59) {
			worldSeconds++;
			if (worldCallback != null)
			    worldCallback.onSecondPassed(worldSeconds);
		    } else if (worldSeconds >= 59 && worldMinutes < 23) {
			worldSeconds = 0;
			worldMinutes++;
			if (worldCallback != null)
			    worldCallback.onMinutePassed(worldMinutes);
		    } else if (worldMinutes >= 23 && worldHours < 23) {
			worldSeconds = 0;
			worldMinutes = 0;
			worldHours++;
			if (worldCallback != null)
			    worldCallback.onHourPassed(worldHours);
		    } else if (worldHours >= 23) {
			worldSeconds = 0;
			worldMinutes = 0;
			worldHours = 0;
			if (worldCallback != null)
			    worldCallback.onHourPassed(worldHours);
		    }
		} catch (NullPointerException e) {
		} catch (Exception e) {
		}
    }

    public void setWorldTimeCallback(WorldTimeCallback callback) {
	worldCallback = callback;
    }
}
