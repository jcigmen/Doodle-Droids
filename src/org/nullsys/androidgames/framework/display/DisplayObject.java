package org.nullsys.androidgames.framework.display;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquation;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.Tweenable;

import org.nullsys.androidgames.framework.Input.TouchEvent;
import org.nullsys.androidgames.framework.display.event.TouchEventCallback;
import org.nullsys.androidgames.framework.math.VectorCoords;

public abstract class DisplayObject implements Tweenable {

    public static final int TWEEN_XY = 1;

    public static final int TWEEN_SCALE_XY = 2;

    public static final int TWEEN_ROTATION = 3;

    public static final int TWEEN_ALPHA = 4;

    public static final int TWEEN_ALL = 5;

    public enum Registration {
	TOP_LEFT, TOP_CENTER, TOP_RIGHT, CENTER_LEFT, CENTER_CENTER, CENTER_RIGHT, BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT
    }

    public TextureRegion textureRegion;

    public TweenManager manager = new TweenManager();

    public TouchEventCallback touchCallback = null;

    public TweenCallback tweenCallback;

    public Tween tween;

    protected TouchEvent touchEvent = null;

    protected DisplayObject hitArea = null;

    protected int previousX = 0;

    protected int previousY = 0;

    public float x = 0;

    public float y = 0;

    public int previousEventX = 0;

    public int previousEventY = 0;

    public float width = 0;

    public float height = 0;

    public float r = 1.0f;

    public float g = 1.0f;

    public float b = 1.0f;

    public float alpha = 1.0f;

    public float scaleX = 1.0f;

    public float scaleY = 1.0f;

    public float rotation = 0f;

    public boolean enabled = true;

    public boolean visible = true;

    public boolean draggable = false;

    public boolean dragX = true;

    public boolean dragY = true;

    protected boolean tapped = false;

    protected boolean dragged = false;

    public abstract void checkInputs(int eventType, int eventX, int eventY);

    public float getHeight() {
	return height;
    }

    public abstract TextureRegion getTextureRegion();

    @Override
    public int getTweenValues(int tweenType, float[] returnValues) {
	switch (tweenType) {
	    case TWEEN_ALPHA:
		returnValues[0] = alpha;
		return 1;
	    case TWEEN_ALL:
		returnValues[0] = x;
		returnValues[1] = y;
		returnValues[2] = scaleX;
		returnValues[3] = scaleY;
		returnValues[4] = rotation;
		returnValues[5] = alpha;
		return 6;
	    default:
		assert false;
		return -1;
	}
    }

    public float getWidth() {
	return width;
    }

    public boolean hitTestPoint(int x, int y) {
	boolean hits = false;
	if (hitArea != null)
	    hits = x >= hitArea.x && x <= hitArea.x + hitArea.width && y >= hitArea.y && y <= hitArea.y + hitArea.height;
	else
	    hits = x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
	return hits;
    }

    public void move(float targetX, float targetY, float targetScaleX, float targetScaleY, float targetRotation, float targetAlpha, TweenEquation equation, int duration, int delay, boolean useCallbacks) {
	if (useCallbacks)
	    tween = Tween.to(this, TWEEN_ALL, duration, equation).target(targetX, targetY, targetScaleX, targetScaleY, targetRotation, targetAlpha).addCompleteCallback(tweenCallback).delay(delay).addToManager(manager);
	else
	    tween = Tween.to(this, TWEEN_ALL, duration, equation).target(targetX, targetY, targetScaleX, targetScaleY, targetRotation, targetAlpha).delay(delay).addToManager(manager);
    }

    public void move(float targetX, float targetY, float targetScaleX, float targetScaleY, float targetRotation, TweenEquation equation, int duration, int delay, boolean useCallbacks) {
	if (useCallbacks)
	    tween = Tween.to(this, TWEEN_ALL, duration, equation).target(targetX, targetY, targetScaleX, targetScaleY, targetRotation, alpha).addCompleteCallback(tweenCallback).delay(delay).addToManager(manager);
	else
	    tween = Tween.to(this, TWEEN_ALL, duration, equation).target(targetX, targetY, targetScaleX, targetScaleY, targetRotation, alpha).delay(delay).addToManager(manager);
    }

    public void move(float targetScaleX, float targetScaleY, TweenEquation equation, int duration, int delay, boolean useCallbacks) {
	if (useCallbacks)
	    tween = Tween.to(this, TWEEN_ALL, duration, equation).target(x, y, targetScaleX, targetScaleY, rotation, alpha).addCompleteCallback(tweenCallback).delay(delay).addToManager(manager);
	else
	    tween = Tween.to(this, TWEEN_ALL, duration, equation).target(x, y, targetScaleX, targetScaleY, rotation, alpha).delay(delay).addToManager(manager);
    }

    public void move(float targetAlpha, TweenEquation equation, int duration, int delay, boolean useCallbacks) {
	if (useCallbacks)
	    tween = Tween.to(this, TWEEN_ALPHA, duration, equation).target(targetAlpha).addCompleteCallback(tweenCallback).delay(delay).addToManager(manager);
	else
	    tween = Tween.to(this, TWEEN_ALPHA, duration, equation).target(targetAlpha).delay(delay).addToManager(manager);
    }

    public void move(int targetRotation, TweenEquation equation, int duration, int delay, boolean useCallbacks) {
	if (useCallbacks)
	    tween = Tween.to(this, TWEEN_ALL, duration, equation).target(x, y, scaleX, scaleY, targetRotation, alpha).addCompleteCallback(tweenCallback).delay(delay).addToManager(manager);
	else
	    tween = Tween.to(this, TWEEN_ALL, duration, equation).target(x, y, scaleX, scaleY, targetRotation, alpha).delay(delay).addToManager(manager);
    }

    public void move(VectorCoords target, TweenEquation equation, int duration, int delay, boolean useCallbacks) {
	if (useCallbacks)
	    tween = Tween.to(this, TWEEN_ALL, duration, equation).target(target.x, target.y, scaleX, scaleY, rotation, alpha).addCompleteCallback(tweenCallback).delay(delay).addToManager(manager);
	else
	    tween = Tween.to(this, TWEEN_ALL, duration, equation).target(target.x, target.y, scaleX, scaleY, rotation, alpha).delay(delay).addToManager(manager);
    }

    @Override
    public void onTweenUpdated(int tweenType, float[] newValues) {
	if (enabled)
	    switch (tweenType) {
		case TWEEN_ALPHA:
		    alpha = newValues[0];
		    break;
		case TWEEN_ALL:
		    x = newValues[0];
		    y = newValues[1];
		    scaleX = newValues[2];
		    scaleY = newValues[3];
		    rotation = newValues[4];
		    alpha = newValues[5];
		    break;
	    }
	else if (tween != null)
	    tween.kill();
    }

    public void render(SpriteBatcher batcher) {
	if (visible) {
	    batcher.beginBatch(getTextureRegion().texture);
	    batcher.drawSprite(x, y, getWidth(), getHeight(), scaleX, scaleY, rotation, getTextureRegion());
	    batcher.endBatch();
	}
    }

    public void setHitArea(DisplayObject hitArea) {
	this.hitArea = hitArea;
    }

    public void setRGBA(float r, float g, float b, float alpha) {
	this.r = r;
	this.g = g;
	this.b = b;
	this.alpha = alpha;
    }

    public abstract void update(float deltaTime);
}
