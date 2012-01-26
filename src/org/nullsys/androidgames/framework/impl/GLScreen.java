package org.nullsys.androidgames.framework.impl;

import javax.microedition.khronos.opengles.GL10;

import org.nullsys.androidgames.framework.Game;
import org.nullsys.androidgames.framework.Screen;
import org.nullsys.androidgames.framework.display.Camera2D;
import org.nullsys.androidgames.framework.display.SpriteBatcher;

public abstract class GLScreen extends Screen {

    protected final GLGraphics glGraphics;

    protected final GLGame glGame;

    protected Camera2D camera;

    protected SpriteBatcher batcher;

    public GLScreen(Game game) {
	super(game);
	glGame = (GLGame) game;
	glGraphics = ((GLGame) game).getGLGraphics();
	camera = new Camera2D(glGraphics, 480, 320);
	batcher = new SpriteBatcher(glGraphics, 100);
    }

    @Override
    public void present(float deltatime) {
	GL10 gl = glGraphics.getGL();
	gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	camera.setViewportAndMatrices();

	gl.glEnable(GL10.GL_TEXTURE_2D);
	gl.glEnable(GL10.GL_BLEND);
	gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

	for (int index = 0; index < displayObjects.size(); index++)
	    if (displayObjects.get(index).visible) {
		gl.glColor4f(displayObjects.get(index).r, displayObjects.get(index).g, displayObjects.get(index).b, displayObjects.get(index).alpha);
		displayObjects.get(index).render(batcher);
	    }

	gl.glDisable(GL10.GL_BLEND);
    }

}
