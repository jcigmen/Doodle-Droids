package org.nullsys.androidgames.framework.event;

public interface GameSaveCallback {

    public void onSdCardNotFound();

    public void onNoSlotSelected();

    public void onSlotOccupied();
}
