package org.nullsys.androidgames.framework.display;

import org.nullsys.androidgames.framework.Input.TouchEvent;

public class Animation extends DisplayObject implements Controllable {

    public final TextureRegion[] keyFrames;

    private final float frameDuration;

    public int frameNumber;

    private float stateTime = 0;

    public boolean isPlaying = true;

    public boolean isRepeating = true;

    public int currentFrame = 0;

    protected long frameTicker = 0;

    protected int frameCount;

    public Animation(float frameDuration, TextureRegion... keyFrames) {
	this.keyFrames = keyFrames;
	this.frameDuration = frameDuration;
	width = keyFrames[0].width;
	height = keyFrames[0].height;
    }

    public Animation(Texture texture, int offsetX, int offsetY, int frameWidth, int frameHeight, int framesPerRow, int frameCount, float frameDuration) {

	keyFrames = new TextureRegion[frameCount];
	this.frameDuration = frameDuration;

	int textureIndex = 0, x = offsetX, y = offsetY;
	int numRows = frameCount % framesPerRow == 0 ? frameCount / framesPerRow : frameCount / framesPerRow + 1;
	for (int rows = 1; rows <= numRows; rows++) {
	    for (int columns = 1; columns <= framesPerRow; columns++)
		if (textureIndex == frameCount)
		    columns = framesPerRow + 1;
		else {
		    keyFrames[textureIndex] = new TextureRegion(texture, x, y, frameWidth, frameHeight);
		    x += frameWidth;
		    textureIndex++;
		}

	    x = offsetX;
	    y += frameHeight;
	}
	textureRegion = keyFrames[0];
	width = frameWidth;
	height = frameHeight;
    }

    @Override
    public void checkInputs(int eventType, int eventX, int eventY) {
	if (visible && enabled) {
	    touchEvent = new TouchEvent();
	    touchEvent.x = eventX;
	    touchEvent.y = eventY;
	    touchEvent.type = eventType;
	    switch (eventType) {
		case TouchEvent.TOUCH_DOWN:
		    if (hitTestPoint(eventX, eventY)) {
			if (draggable) {
			    dragged = true;
			    previousX = eventX;
			    previousY = eventY;
			}
			tapped = true;
			if (touchCallback != null)
			    touchCallback.onTouchEvent(this, touchEvent);
		    }
		    break;
		case TouchEvent.TOUCH_UP:
		    if (hitTestPoint(eventX, eventY)) {
			dragged = false;
			if (tapped && touchCallback != null) {
			    touchEvent.type = TouchEvent.TOUCH_TAP;
			    touchCallback.onTouchEvent(this, touchEvent);
			} else if (touchCallback != null)
			    touchCallback.onTouchEvent(this, touchEvent);
		    }
		    break;
		case TouchEvent.TOUCH_DRAGGED:
		    if (!hitTestPoint(eventX, eventY))
			dragged = false;
		    else {
			if (touchCallback != null)
			    touchCallback.onTouchEvent(this, touchEvent);
			if (dragged && draggable) {
			    x += eventX - previousX;
			    y += 320 - eventY - previousY;
			    previousX = eventX;
			    previousY = 320 - eventY;
			}
		    }
	    }
	}
    }

    @Override
    public TextureRegion getTextureRegion() {
	return keyFrames[frameNumber];
    }

    @Override
    public float getWidth() {
	return textureRegion.width;
    }

    @Override
    public void pause() {
	isPlaying = false;
    }

    @Override
    public void play() {
	isPlaying = true;
	currentFrame = 0;
    }

    @Override
    public void resume() {
	isPlaying = true;
    }

    @Override
    public void stop() {
	isPlaying = false;
	currentFrame = 0;
    }

    @Override
    public void update(float deltaTime) {
	manager.update();
	if (isPlaying && enabled) {
	    stateTime += deltaTime;
	    frameNumber = (int) (stateTime / frameDuration);

	    if (!isRepeating)
		frameNumber = Math.min(keyFrames.length - 1, frameNumber);
	    else
		frameNumber = frameNumber % keyFrames.length;
	}
    }
}
