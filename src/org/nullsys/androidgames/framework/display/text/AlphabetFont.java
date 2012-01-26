package org.nullsys.androidgames.framework.display.text;

import org.nullsys.androidgames.framework.display.Texture;
import org.nullsys.androidgames.framework.display.TextureRegion;

public class AlphabetFont extends Font {

    private static final int CHARS_COUNT = 69;

    private TextureRegion[] glyphs;

    public float A = .8f, B = .8f, C = .8f, D = .8f, E = .8f, F = .8f, G = .8f, H = .8f, J = .8f, I = .25f, K = .8f, L = .8f, M = 1f, N = .8f, O = .8f, P = .8f, Q = .8f, R = .8f, S = .8f, T = .8f, U = .8f, V = .8f, W = 1f, X = .8f, Y = .8f, Z = .8f, a = .5f, b = .5f, c = .5f,
	    d = .5f, e = .5f, f = .5f, g = .5f, h = .5f, i = .5f, j = .5f, k = .5f, l = .5f, m = .5f, n = .5f, o = .5f, p = .5f, q = .5f, r = .5f, s = .5f, t = .5f, u = .5f, v = .5f, w = .5f, x = .5f, y = .5f, z = .5f, one = .25f, two = .5f, three = .5f, four = .5f, five = .5f,
	    six = .5f, seven = .5f, eight = .5f, nine = .5f, zero = .5f, exclamation = .25f, question = .5f, dot = .25f, coma = .25f, dash = .5f, colon = .25f, plus = .75f;

    public AlphabetFont() {

    }

    public AlphabetFont(Texture texture, int offsetX, int offsetY, int glyphWidth, int glyphHeight, int glyphsPerRow) {
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
	    case 'A':
		region = glyphs[0];
		break;
	    case 'B':
		region = glyphs[1];
		break;
	    case 'C':
		region = glyphs[2];
		break;
	    case 'D':
		region = glyphs[3];
		break;
	    case 'E':
		region = glyphs[4];
		break;
	    case 'F':
		region = glyphs[5];
		break;
	    case 'G':
		region = glyphs[6];
		break;
	    case 'H':
		region = glyphs[7];
		break;
	    case 'I':
		region = glyphs[8];
		break;
	    case 'J':
		region = glyphs[9];
		break;
	    case 'K':
		region = glyphs[10];
		break;
	    case 'L':
		region = glyphs[11];
		break;
	    case 'M':
		region = glyphs[12];
		break;
	    case 'N':
		region = glyphs[13];
		break;
	    case 'O':
		region = glyphs[14];
		break;
	    case 'P':
		region = glyphs[15];
		break;
	    case 'Q':
		region = glyphs[16];
		break;
	    case 'R':
		region = glyphs[17];
		break;
	    case 'S':
		region = glyphs[18];
		break;
	    case 'T':
		region = glyphs[19];
		break;
	    case 'U':
		region = glyphs[20];
		break;
	    case 'V':
		region = glyphs[21];
		break;
	    case 'W':
		region = glyphs[22];
		break;
	    case 'X':
		region = glyphs[23];
		break;
	    case 'Y':
		region = glyphs[24];
		break;
	    case 'Z':
		region = glyphs[25];
		break;
	    case 'a':
		region = glyphs[26];
		break;
	    case 'b':
		region = glyphs[27];
		break;
	    case 'c':
		region = glyphs[28];
		break;
	    case 'd':
		region = glyphs[29];
		break;
	    case 'e':
		region = glyphs[30];
		break;
	    case 'f':
		region = glyphs[31];
		break;
	    case 'g':
		region = glyphs[32];
		break;
	    case 'h':
		region = glyphs[33];
		break;
	    case 'i':
		region = glyphs[34];
		break;
	    case 'j':
		region = glyphs[35];
		break;
	    case 'k':
		region = glyphs[36];
		break;
	    case 'l':
		region = glyphs[37];
		break;
	    case 'm':
		region = glyphs[38];
		break;
	    case 'n':
		region = glyphs[39];
		break;
	    case 'o':
		region = glyphs[40];
		break;
	    case 'p':
		region = glyphs[41];
		break;
	    case 'q':
		region = glyphs[42];
		break;
	    case 'r':
		region = glyphs[43];
		break;
	    case 's':
		region = glyphs[44];
		break;
	    case 't':
		region = glyphs[45];
		break;
	    case 'u':
		region = glyphs[46];
		break;
	    case 'v':
		region = glyphs[47];
		break;
	    case 'w':
		region = glyphs[48];
		break;
	    case 'x':
		region = glyphs[49];
		break;
	    case 'y':
		region = glyphs[50];
		break;
	    case 'z':
		region = glyphs[51];
		break;
	    case '1':
		region = glyphs[52];
		break;
	    case '2':
		region = glyphs[53];
		break;
	    case '3':
		region = glyphs[54];
		break;
	    case '4':
		region = glyphs[55];
		break;
	    case '5':
		region = glyphs[56];
		break;
	    case '6':
		region = glyphs[57];
		break;
	    case '7':
		region = glyphs[58];
		break;
	    case '8':
		region = glyphs[59];
		break;
	    case '9':
		region = glyphs[60];
		break;
	    case '0':
		region = glyphs[61];
		break;
	    case '!':
		region = glyphs[62];
		break;
	    case '?':
		region = glyphs[63];
		break;
	    case '.':
		region = glyphs[64];
		break;
	    case ',':
		region = glyphs[65];
		break;
	    case '-':
		region = glyphs[66];
		break;
	    case ':':
		region = glyphs[67];
		break;
	    case '+':
		region = glyphs[68];
		break;
	    default:
	}
	return region;
    }

    public float getGlyphWidth(int index) {
	float glyphWidth = 0;
	switch (index) {
	    case 0:
		glyphWidth = this.glyphWidth * A;
		break;
	    case 1:
		glyphWidth = this.glyphWidth * B;
		break;
	    case 2:
		glyphWidth = this.glyphWidth * C;
		break;
	    case 3:
		glyphWidth = this.glyphWidth * D;
		break;
	    case 4:
		glyphWidth = this.glyphWidth * E;
		break;
	    case 5:
		glyphWidth = this.glyphWidth * F;
		break;
	    case 6:
		glyphWidth = this.glyphWidth * G;
		break;
	    case 7:
		glyphWidth = this.glyphWidth * H;
		break;
	    case 8:
		glyphWidth = this.glyphWidth * I;
		break;
	    case 9:
		glyphWidth = this.glyphWidth * J;
		break;
	    case 10:
		glyphWidth = this.glyphWidth * K;
		break;
	    case 11:
		glyphWidth = this.glyphWidth * L;
		break;
	    case 12:
		glyphWidth = this.glyphWidth * M;
		break;
	    case 13:
		glyphWidth = this.glyphWidth * N;
		break;
	    case 14:
		glyphWidth = this.glyphWidth * O;
		break;
	    case 15:
		glyphWidth = this.glyphWidth * P;
		break;
	    case 16:
		glyphWidth = this.glyphWidth * Q;
		break;
	    case 17:
		glyphWidth = this.glyphWidth * R;
		break;
	    case 18:
		glyphWidth = this.glyphWidth * S;
		break;
	    case 19:
		glyphWidth = this.glyphWidth * T;
		break;
	    case 20:
		glyphWidth = this.glyphWidth * U;
		break;
	    case 21:
		glyphWidth = this.glyphWidth * V;
		break;
	    case 22:
		glyphWidth = this.glyphWidth * W;
		break;
	    case 23:
		glyphWidth = this.glyphWidth * X;
		break;
	    case 24:
		glyphWidth = this.glyphWidth * Y;
		break;
	    case 25:
		glyphWidth = this.glyphWidth * Z;
		break;
	    case 26:
		glyphWidth = this.glyphWidth * a;
		break;
	    case 27:
		glyphWidth = this.glyphWidth * b;
		break;
	    case 28:
		glyphWidth = this.glyphWidth * c;
		break;
	    case 29:
		glyphWidth = this.glyphWidth * d;
		break;
	    case 30:
		glyphWidth = this.glyphWidth * e;
		break;
	    case 31:
		glyphWidth = this.glyphWidth * f;
		break;
	    case 32:
		glyphWidth = this.glyphWidth * g;
		break;
	    case 33:
		glyphWidth = this.glyphWidth * h;
		break;
	    case 34:
		glyphWidth = this.glyphWidth * i;
		break;
	    case 35:
		glyphWidth = this.glyphWidth * j;
		break;
	    case 36:
		glyphWidth = this.glyphWidth * k;
		break;
	    case 37:
		glyphWidth = this.glyphWidth * l;
		break;
	    case 38:
		glyphWidth = this.glyphWidth * m;
		break;
	    case 39:
		glyphWidth = this.glyphWidth * n;
		break;
	    case 40:
		glyphWidth = this.glyphWidth * o;
		break;
	    case 41:
		glyphWidth = this.glyphWidth * p;
		break;
	    case 42:
		glyphWidth = this.glyphWidth * q;
		break;
	    case 43:
		glyphWidth = this.glyphWidth * r;
		break;
	    case 44:
		glyphWidth = this.glyphWidth * s;
		break;
	    case 45:
		glyphWidth = this.glyphWidth * t;
		break;
	    case 46:
		glyphWidth = this.glyphWidth * u;
		break;
	    case 47:
		glyphWidth = this.glyphWidth * v;
		break;
	    case 48:
		glyphWidth = this.glyphWidth * w;
		break;
	    case 49:
		glyphWidth = this.glyphWidth * x;
		break;
	    case 50:
		glyphWidth = this.glyphWidth * y;
		break;
	    case 51:
		glyphWidth = this.glyphWidth * z;
		break;
	    case 52:
		glyphWidth = this.glyphWidth * one;
		break;
	    case 53:
		glyphWidth = this.glyphWidth * two;
		break;
	    case 54:
		glyphWidth = this.glyphWidth * three;
		break;
	    case 55:
		glyphWidth = this.glyphWidth * four;
		break;
	    case 56:
		glyphWidth = this.glyphWidth * five;
		break;
	    case 57:
		glyphWidth = this.glyphWidth * six;
		break;
	    case 58:
		glyphWidth = this.glyphWidth * seven;
		break;
	    case 59:
		glyphWidth = this.glyphWidth * eight;
		break;
	    case 60:
		glyphWidth = this.glyphWidth * nine;
		break;
	    case 61:
		glyphWidth = this.glyphWidth * zero;
		break;
	    case 62:
		glyphWidth = this.glyphWidth * exclamation;
		break;
	    case 63:
		glyphWidth = this.glyphWidth * question;
		break;
	    case 64:
		glyphWidth = this.glyphWidth * dot;
		break;
	    case 65:
		glyphWidth = this.glyphWidth * coma;
		break;
	    case 66:
		glyphWidth = this.glyphWidth * dash;
		break;
	    case 67:
		glyphWidth = this.glyphWidth * colon;
		break;
	    case 68:
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
