package org.nullsys.androidgames.framework;

public interface Music {

    public void dispose();

    public boolean isLooping();

    public boolean isPlaying();

    public boolean isStopped();

    public void pause();

    public void play();

    public void setLooping(boolean looping);

    public void setVolume(float volume);

    public void stop();
}
