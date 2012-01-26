package org.nullsys.androidgames.doodledroids;

public interface WorldTimeCallback {

    public void onSecondPassed(float second);

    public void onMinutePassed(float minute);

    public void onHourPassed(float hour);
}
