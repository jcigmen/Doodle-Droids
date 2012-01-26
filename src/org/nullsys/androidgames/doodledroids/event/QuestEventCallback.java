package org.nullsys.androidgames.doodledroids.event;

public interface QuestEventCallback {

    public void onLevelInsufficient();

    public void onEnergyInsufficient();

    public void onHealthInsufficient();

    public void onItemsInsufficient();

    public void onItemDrop(String itemName);
}
