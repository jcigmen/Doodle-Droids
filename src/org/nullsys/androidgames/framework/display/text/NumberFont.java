package org.nullsys.androidgames.framework.display.text;

import org.nullsys.androidgames.framework.display.Texture;
import org.nullsys.androidgames.framework.display.TextureRegion;

public class NumberFont extends Font {

    private static final int CHARS_COUNT = 17;

    public TextureRegion[] glyphs;

    public float one = .5f, two = 1f, three = 1f, four = 1f, five = 1f, six = 1f, seven = 1f, eight = 1f, nine = 1f, zero = 1f, exclamation = .5f, question = .5f, dot = .1f, coma = .25f, dash = .5f, colon = 1f, plus = 1f;

    public NumberFont() {
    }

    public NumberFont(Texture texture, int offsetX, int offsetY, int glyphWidth, int glyphHeight, int glyphsPerRow) {
	super.texture = texture;
	glyphs = new TextureRegion[CHARS_COUNT];
	this.texture = texture;
	this.glyphWidth = glyphWidth;
	this.glyphHeight = glyphHeight;

	int glyphIndex = 0, x = offsetX, y = offsetY;
	int numRows = CHARS_COUNT % glyphsPerRow == 0 ? CHARS_COUNT / glyphsPerRow : CHARS_COUNT / glyphsPerRow + 1;
	for (int rows = 1; rows <= numRows; rows++) {
	    for (int columns = 1; columns <= glyphsPerRow; columns++)
		if (glyphIndex == CHARS_COUNT)
		    columns = glyphsPerRow + 1;
		else {
		    glyphs[glyphIndex] = new TextureRegion(texture, x, y, getGlyphWidth(glyphIndex), glyphHeight);
		    x += glyphWidth;
		    glyphIndex++;
		}

	    x = offsetX;
	    y += glyphHeight;
	}
    }

    @Override
    public TextureRegion getChar(char c) {
	TextureRegion region = null;
	switch (c) {
	    case '1':
		region = glyphs[0];
		break;
	    case '2':
		region = glyphs[1];
		break;
	    case '3':
		region = glyphs[2];
		break;
	    case '4':
		region = glyphs[3];
		break;
	    case '5':
		region = glyphs[4];
		break;
	    case '6':
		region = glyphs[5];
		break;
	    case '7':
		region = glyphs[6];
		break;
	    case '8':
		region = glyphs[7];
		break;
	    case '9':
		region = glyphs[8];
		break;
	    case '0':
		region = glyphs[9];
		break;
	    case '!':
		region = glyphs[10];
		break;
	    case '?':
		region = glyphs[11];
		break;
	    case '.':
		region = glyphs[12];
		break;
	    case ',':
		region = glyphs[13];
		break;
	    case '-':
		region = glyphs[14];
		break;
	    case ':':
		region = glyphs[15];
		break;
	    case '+':
		region = glyphs[16];
		break;
	}
	return region;
    }

    public float getGlyphWidth(int index) {
	float glyphWidth = 0;
	switch (index) {
	    case 0:
		glyphWidth = this.glyphWidth * one;
		break;
	    case 1:
		glyphWidth = this.glyphWidth * two;
		break;
	    case 2:
		glyphWidth = this.glyphWidth * three;
		break;
	    case 3:
		glyphWidth = this.glyphWidth * four;
		break;
	    case 4:
		glyphWidth = this.glyphWidth * five;
		break;
	    case 5:
		glyphWidth = this.glyphWidth * six;
		break;
	    case 6:
		glyphWidth = this.glyphWidth * seven;
		break;
	    case 7:
		glyphWidth = this.glyphWidth * eight;
		break;
	    case 8:
		glyphWidth = this.glyphWidth * nine;
		break;
	    case 9:
		glyphWidth = this.glyphWidth * zero;
		break;
	    case 10:
		glyphWidth = this.glyphWidth * exclamation;
		break;
	    case 11:
		glyphWidth = this.glyphWidth * question;
		break;
	    case 12:
		glyphWidth = this.glyphWidth * dot;
		break;
	    case 13:
		glyphWidth = this.glyphWidth * coma;
		break;
	    case 14:
		glyphWidth = this.glyphWidth * dash;
		break;
	    case 15:
		glyphWidth = this.glyphWidth * colon;
		break;
	    case 16:
		glyphWidth = this.glyphWidth * plus;
		break;
	    default:
	}
	return glyphWidth;
    }

    public void set(Texture texture, int offsetX, int offsetY, int glyphWidth, int glyphHeight, int glyphsPerRow) {
	super.texture = texture;
	glyphs = new TextureRegion[CHARS_COUNT];
	this.texture = texture;
	this.glyphWidth = glyphWidth;
	this.glyphHeight = glyphHeight;

	int glyphIndex = 0, x = offsetX, y = offsetY;
	int numRows = CHARS_COUNT % glyphsPerRow == 0 ? CHARS_COUNT / glyphsPerRow : CHARS_COUNT / glyphsPerRow + 1;
	for (int rows = 1; rows <= numRows; rows++) {
	    for (int columns = 1; columns <= glyphsPerRow; columns++)
		if (glyphIndex == CHARS_COUNT)
		    columns = glyphsPerRow + 1;
		else {
		    glyphs[glyphIndex] = new TextureRegion(texture, x, y, getGlyphWidth(glyphIndex), glyphHeight);
		    x += glyphWidth;
		    glyphIndex++;
		}

	    x = offsetX;
	    y += glyphHeight;
	}
    }
}
