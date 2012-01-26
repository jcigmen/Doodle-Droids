package org.nullsys.androidgames.framework.display;

import android.util.FloatMath;

import javax.microedition.khronos.opengles.GL10;

import org.nullsys.androidgames.framework.impl.GLGraphics;
import org.nullsys.androidgames.framework.math.VectorCoords;

public class SpriteBatcher {

    final float[] verticesBuffer;
    int bufferIndex;
    final Vertices vertices;
    int numSprites;

    public SpriteBatcher(GLGraphics glGraphics, int maxSprites) {
	verticesBuffer = new float[maxSprites * 4 * 4];
	vertices = new Vertices(glGraphics, maxSprites * 4, maxSprites * 6, false, true);
	bufferIndex = 0;
	numSprites = 0;

	short[] indices = new short[maxSprites * 6];
	int len = indices.length;
	short j = 0;
	for (int i = 0; i < len; i += 6, j += 4) {
	    indices[i + 0] = (short) (j + 0);
	    indices[i + 1] = (short) (j + 1);
	    indices[i + 2] = (short) (j + 2);
	    indices[i + 3] = (short) (j + 2);
	    indices[i + 4] = (short) (j + 3);
	    indices[i + 5] = (short) (j + 0);
	}
	vertices.setIndices(indices, 0, indices.length);
    }

    public void beginBatch(DisplayObject displayObject) {
	displayObject.getTextureRegion().texture.bind();
	numSprites = 0;
	bufferIndex = 0;
    }

    public void beginBatch(Texture texture) {
	texture.bind();
	numSprites = 0;
	bufferIndex = 0;
    }

    public void endBatch() {
	vertices.setVertices(verticesBuffer, 0, bufferIndex);
	vertices.bind();
	vertices.draw(GL10.GL_TRIANGLES, 0, numSprites * 6);
	vertices.unbind();
    }

    public void drawSprite(float x, float y, float width, float height, TextureRegion region) {
	float x1 = x;
	float y1 = y;
	float x2 = x + width;
	float y2 = y + height;

	verticesBuffer[bufferIndex++] = x1;
	verticesBuffer[bufferIndex++] = y1;
	verticesBuffer[bufferIndex++] = region.u1;
	verticesBuffer[bufferIndex++] = region.v2;

	verticesBuffer[bufferIndex++] = x2;
	verticesBuffer[bufferIndex++] = y1;
	verticesBuffer[bufferIndex++] = region.u2;
	verticesBuffer[bufferIndex++] = region.v2;

	verticesBuffer[bufferIndex++] = x2;
	verticesBuffer[bufferIndex++] = y2;
	verticesBuffer[bufferIndex++] = region.u2;
	verticesBuffer[bufferIndex++] = region.v1;

	verticesBuffer[bufferIndex++] = x1;
	verticesBuffer[bufferIndex++] = y2;
	verticesBuffer[bufferIndex++] = region.u1;
	verticesBuffer[bufferIndex++] = region.v1;

	numSprites++;
    }

    public void drawSprite(DisplayObject displayObject) {
	float x1 = displayObject.x * displayObject.scaleX;
	float y1 = displayObject.y * displayObject.scaleY;
	float x2 = displayObject.x + displayObject.width * displayObject.scaleX;
	float y2 = displayObject.y + displayObject.height * displayObject.scaleY;

	verticesBuffer[bufferIndex++] = x1;
	verticesBuffer[bufferIndex++] = y1;
	verticesBuffer[bufferIndex++] = displayObject.getTextureRegion().u1;
	verticesBuffer[bufferIndex++] = displayObject.getTextureRegion().v2;

	verticesBuffer[bufferIndex++] = x2;
	verticesBuffer[bufferIndex++] = y1;
	verticesBuffer[bufferIndex++] = displayObject.getTextureRegion().u2;
	verticesBuffer[bufferIndex++] = displayObject.getTextureRegion().v2;

	verticesBuffer[bufferIndex++] = x2;
	verticesBuffer[bufferIndex++] = y2;
	verticesBuffer[bufferIndex++] = displayObject.getTextureRegion().u2;
	verticesBuffer[bufferIndex++] = displayObject.getTextureRegion().v1;

	verticesBuffer[bufferIndex++] = x1;
	verticesBuffer[bufferIndex++] = y2;
	verticesBuffer[bufferIndex++] = displayObject.getTextureRegion().u1;
	verticesBuffer[bufferIndex++] = displayObject.getTextureRegion().v1;

	numSprites++;
    }

    public void drawSprite(float x, float y, float width, float height, float scaleX, float scaleY, float angle, TextureRegion region) {
	float halfWidth = width * scaleX / 2;
	float halfHeight = height * scaleY / 2;

	float rad = angle * VectorCoords.TO_RADIANS;
	float cos = FloatMath.cos(rad);
	float sin = FloatMath.sin(rad);

	float x1 = -halfWidth * cos - -halfHeight * sin;
	float y1 = -halfWidth * sin + -halfHeight * cos;
	float x2 = halfWidth * cos - -halfHeight * sin;
	float y2 = halfWidth * sin + -halfHeight * cos;
	float x3 = halfWidth * cos - halfHeight * sin;
	float y3 = halfWidth * sin + halfHeight * cos;
	float x4 = -halfWidth * cos - halfHeight * sin;
	float y4 = -halfWidth * sin + halfHeight * cos;

	x1 += x + halfWidth;
	y1 += y + halfHeight;
	x2 += x + halfWidth;
	y2 += y + halfHeight;
	x3 += x + halfWidth;
	y3 += y + halfHeight;
	x4 += x + halfWidth;
	y4 += y + halfHeight;

	/*Log.d("TEST", "X1: " + x1);
	Log.d("TEST", "Y1: " + y1);
	Log.d("TEST", "X2: " + x2);
	Log.d("TEST", "Y2: " + y2);
	Log.d("TEST", "X3: " + x3);
	Log.d("TEST", "Y3: " + y3);
	Log.d("TEST", "X4: " + x4);
	Log.d("TEST", "Y4: " + y4);*/

	verticesBuffer[bufferIndex++] = x1;
	verticesBuffer[bufferIndex++] = y1;
	verticesBuffer[bufferIndex++] = region.u1;
	verticesBuffer[bufferIndex++] = region.v2;

	verticesBuffer[bufferIndex++] = x2;
	verticesBuffer[bufferIndex++] = y2;
	verticesBuffer[bufferIndex++] = region.u2;
	verticesBuffer[bufferIndex++] = region.v2;

	verticesBuffer[bufferIndex++] = x3;
	verticesBuffer[bufferIndex++] = y3;
	verticesBuffer[bufferIndex++] = region.u2;
	verticesBuffer[bufferIndex++] = region.v1;

	verticesBuffer[bufferIndex++] = x4;
	verticesBuffer[bufferIndex++] = y4;
	verticesBuffer[bufferIndex++] = region.u1;
	verticesBuffer[bufferIndex++] = region.v1;

	numSprites++;
    }
}