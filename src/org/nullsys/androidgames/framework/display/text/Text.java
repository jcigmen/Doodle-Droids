package org.nullsys.androidgames.framework.display.text;

import android.util.Log;

import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.SpriteBatcher;
import org.nullsys.androidgames.framework.display.TextureRegion;

public class Text extends DisplayObject {

    public static enum TextRegistration {

	LEFT, CENTER, RIGHT
    }

    public Font font;

    public String text = "";

    public TextRegistration registration = TextRegistration.LEFT;

    public Text(Font font, String text) {
	this.font = font;
	this.text = text;
	width = font.glyphWidth;
	height = font.glyphHeight;
    }

    @Override
    public void checkInputs(int eventType, int eventX, int eventY) {
	// TODO Auto-generated method stub

    }

    @Override
    public float getHeight() {
	return height;
    }

    @Override
    public TextureRegion getTextureRegion() {
	return null;
    }

    @Override
    public float getWidth() {
	float width = 0;
	switch (registration) {
	    case LEFT:
		for (int index = 0; index < text.length(); index++)
		    width += font.getChar(text.charAt(index)).width;
		break;
	    case CENTER:
		int returnCount = 1;
		for (int index = 0; index < text.length(); index++)
		    if (text.charAt(index) == '\n')
			returnCount++;
		String[] lines = new String[returnCount];

		// Clear the string array so it does not start with null
		for (int lineIndex = 0; lineIndex < lines.length; lineIndex++)
		    lines[lineIndex] = "";

		int linesIndex = 0;
		for (int index = 0; index < text.length(); index++)
		    if (text.charAt(index) != '\n')
			lines[linesIndex] += text.charAt(index);
		    else
			linesIndex++;

		// Determine the longest line in the paragraph
		int longestCharLine = 0;
		int longestCharLineIndex = 0;
		for (int index = 0; index < lines.length; index++)
		    if (lines[index].length() > longestCharLine) {
			longestCharLine = lines[index].length();
			longestCharLineIndex = index;
		    }

		for (int index = 0; index < lines[longestCharLineIndex].length() / 2; index++)
		    width += font.getChar(lines[longestCharLineIndex].charAt(index)).width;
		break;
	    case RIGHT:
		returnCount = 1;
		for (int index = 0; index < text.length(); index++)
		    if (text.charAt(index) == '\n')
			returnCount++;
		lines = new String[returnCount];

		// Clear the string array so it does not start with null
		for (int lineIndex = 0; lineIndex < lines.length; lineIndex++)
		    lines[lineIndex] = "";

		linesIndex = 0;
		for (int index = 0; index < text.length(); index++)
		    if (text.charAt(index) != '\n')
			lines[linesIndex] += text.charAt(index);
		    else
			linesIndex++;

		// Determine the longest line in the paragraph
		longestCharLine = 0;
		longestCharLineIndex = 0;
		for (int index = 0; index < lines.length; index++)
		    if (lines[index].length() > longestCharLine) {
			longestCharLine = lines[index].length();
			longestCharLineIndex = index;
		    }

		for (int index = 0; index < lines[longestCharLineIndex].length(); index++)
		    width += font.getChar(lines[longestCharLineIndex].charAt(index)).width;
		break;
	}
	return width;
    }

    @Override
    public void render(SpriteBatcher batcher) {
	if (visible) {
	    float offsetX = x, offsetY = y;

	    switch (registration) {
		case CENTER:
		    int returnCount = 1;
		    for (int index = 0; index < text.length(); index++)
			if (text.charAt(index) == '\n')
			    returnCount++;
		    String[] lines = new String[returnCount];

		    // Clear the string array so it does not start with null
		    for (int lineIndex = 0; lineIndex < lines.length; lineIndex++)
			lines[lineIndex] = "";

		    int linesIndex = 0;
		    for (int index = 0; index < text.length(); index++)
			if (text.charAt(index) != '\n')
			    lines[linesIndex] += text.charAt(index);
			else
			    linesIndex++;

		    for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
			offsetX = x;
			for (int charIndex = 0; charIndex < lines[lineIndex].length(); charIndex++)
			    try {
				if (lines[lineIndex].charAt(charIndex) == ' ')
				    offsetX -= font.glyphWidth * scaleX / 4;
				else if (lines[lineIndex].charAt(charIndex) == '\t')
				    offsetX -= Font.TAB_SPACING * width * scaleX / 2;
				else
				    offsetX -= font.getChar(lines[lineIndex].charAt(charIndex)).width * scaleX / 2;
			    } catch (NullPointerException e) {
				offsetX -= font.glyphWidth / 2;
			    }
			for (int charIndex = 0; charIndex < lines[lineIndex].length(); charIndex++)
			    if (lines[lineIndex].charAt(charIndex) == '\t')
				offsetX += Font.TAB_SPACING * width * scaleX;
			    else if (lines[lineIndex].charAt(charIndex) == ' ')
				offsetX += font.glyphWidth * scaleX / 2;
			    else if (font.getChar(lines[lineIndex].charAt(charIndex)) != null && lines[lineIndex].length() > 0) {
				batcher.beginBatch(font.texture);
				batcher.drawSprite(offsetX, offsetY - height * scaleY / 2, font.getChar(lines[lineIndex].charAt(charIndex)).width, font.glyphHeight * scaleY, scaleX, scaleY, rotation, font.getChar(lines[lineIndex].charAt(charIndex)));
				batcher.endBatch();
				offsetX += font.getChar(lines[lineIndex].charAt(charIndex)).width * scaleX;
			    } else
				Log.d("TEST", "No text to render!");
			offsetY -= font.glyphHeight * scaleY;
		    }
		    break;
		case LEFT:
		    for (int index = 0; index < text.length(); index++)
			if (text.charAt(index) == '\t')
			    offsetX += Font.TAB_SPACING * width * scaleX;
			else if (text.charAt(index) == ' ')
			    offsetX += font.glyphWidth / 2;
			else if (text.charAt(index) == '\n') {
			    offsetX = x;
			    offsetY -= font.glyphHeight * scaleY;
			} else if (font.getChar(text.charAt(index)) != null && text.length() > 0) {
			    batcher.beginBatch(font.texture);
			    batcher.drawSprite(offsetX, offsetY - height * scaleY / 2, font.getChar(text.charAt(index)).width, font.glyphHeight, scaleX, scaleY, rotation, font.getChar(text.charAt(index)));
			    batcher.endBatch();
			    offsetX += font.getChar(text.charAt(index)).width * scaleX;
			}
		    break;
		case RIGHT:
		    returnCount = 1;
		    for (int index = 0; index < text.length(); index++)
			if (text.charAt(index) == '\n')
			    returnCount++;
		    lines = new String[returnCount];

		    // Clear the string array so it does not start with null
		    for (int lineIndex = 0; lineIndex < lines.length; lineIndex++)
			lines[lineIndex] = "";

		    linesIndex = 0;
		    for (int index = 0; index < text.length(); index++)
			if (text.charAt(index) != '\n')
			    lines[linesIndex] += text.charAt(index);
			else
			    linesIndex++;

		    // Determine the x-offset
		    int longestCharLine = 0;
		    int longestCharLineIndex = 0;
		    for (int index = 0; index < lines.length; index++)
			if (lines[index].length() > longestCharLine) {
			    longestCharLine = lines[index].length();
			    longestCharLineIndex = index;
			}

		    float startingOffsetX = x;
		    for (int index = 0; index < longestCharLine; index++)
			startingOffsetX -= font.getChar(lines[longestCharLineIndex].charAt(index)).width;

		    offsetX = startingOffsetX;
		    for (int index = 0; index < text.length(); index++)
			if (text.charAt(index) == '\t')
			    offsetX += Font.TAB_SPACING * width * scaleX;
			else if (text.charAt(index) == ' ')
			    offsetX += font.glyphWidth / 2;
			else if (text.charAt(index) == '\n') {
			    offsetX = x;
			    offsetY -= font.glyphHeight * scaleY;
			} else if (font.getChar(text.charAt(index)) != null && text.length() > 0) {
			    batcher.beginBatch(font.texture);
			    batcher.drawSprite(offsetX, offsetY - height * scaleY / 2, font.getChar(text.charAt(index)).width, font.glyphHeight * scaleY, scaleX, scaleY, rotation, font.getChar(text.charAt(index)));
			    batcher.endBatch();
			    offsetX += font.getChar(text.charAt(index)).width * scaleX;
			}
		    break;
	    }
	}
    }

    @Override
    public void update(float deltaTime) {
	manager.update();
    }
}
