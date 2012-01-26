/**
 * @author MrUseL3tter
 * @version 1.00
 */
package org.nullsys.androidgames.framework.display;

public interface Controllable {

    /**
     * Play this Controllable object from the beginning.
     */
    public void play();

    /**
     * Return playing from the point this Controllable object is paused.
     */
    public void resume();

    /**
     * Stop this controllable object. When played again, this Controllable object starts from the beginning.
     */
    public void stop();

    /**
     * Pause this Controllable object from its activity. To play it again, invoke <code>resume()</code>
     */
    public void pause();
}
