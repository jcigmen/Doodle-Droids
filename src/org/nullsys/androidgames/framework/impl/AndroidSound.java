package org.nullsys.androidgames.framework.impl;

import android.media.SoundPool;

import org.nullsys.androidgames.framework.Sound;

public class AndroidSound implements Sound {

    int soundId;
    SoundPool soundPool;

    public AndroidSound(SoundPool soundPool, int soundId) {
	this.soundId = soundId;
	this.soundPool = soundPool;
    }

    @Override
    public void dispose() {
	soundPool.unload(soundId);
    }

    @Override
    public void play(float volume) {
	soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

}
