package org.nullsys.androidgames.doodledroids.event;

import org.nullsys.androidgames.doodledroids.screen.PromptScreen;

public interface PromptCallback {

    public void onOkTapped(PromptScreen prompt);

    public void onYesTapped(PromptScreen prompt);

    public void onNoTapped(PromptScreen prompt);
}
