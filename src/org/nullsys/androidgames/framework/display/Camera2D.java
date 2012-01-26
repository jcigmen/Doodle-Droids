package org.nullsys.androidgames.framework.display;

import javax.microedition.khronos.opengles.GL10;

import org.nullsys.androidgames.framework.impl.GLGraphics;
import org.nullsys.androidgames.framework.math.VectorCoords;

public class Camera2D {

    public final VectorCoords position;
    public float zoom;
    public final float frustumWidth;
    public final float frustumHeight;
    final GLGraphics glGraphics;

    public Camera2D(GLGraphics glGraphics, float frustumWidth, float frustumHeight) {
	this.glGraphics = glGraphics;
	this.frustumWidth = frustumWidth;
	this.frustumHeight = frustumHeight;
	position = new VectorCoords(frustumWidth / 2, frustumHeight / 2);
	zoom = 1.0f;
    }

    public void setViewportAndMatrices() {
	GL10 gl = glGraphics.getGL();
	gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
	gl.glMatrixMode(GL10.GL_PROJECTION);
	gl.glLoadIdentity();
	gl.glOrthof(position.x - frustumWidth * zoom / 2, position.x + frustumWidth * zoom / 2, position.y - frustumHeight * zoom / 2, position.y + frustumHeight * zoom / 2, 1, -1);
	gl.glMatrixMode(GL10.GL_MODELVIEW);
	gl.glLoadIdentity();
    }

    public void touchToWorld(VectorCoords touch) {
	touch.x = touch.x / glGraphics.getWidth() * frustumWidth * zoom;
	touch.y = (1 - touch.y / glGraphics.getHeight()) * frustumHeight * zoom;
	touch.add(position).sub(frustumWidth * zoom / 2, frustumHeight * zoom / 2);
    }
}
