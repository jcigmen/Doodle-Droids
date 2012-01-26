package org.nullsys.androidgames.doodledroids.screen;

import android.util.Log;

import org.nullsys.androidgames.doodledroids.Assets;
import org.nullsys.androidgames.framework.Game;
import org.nullsys.androidgames.framework.Input.TouchEvent;
import org.nullsys.androidgames.framework.display.Animation;
import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.event.TouchEventCallback;
import org.nullsys.androidgames.framework.impl.GLScreen;

public class TestScreen extends GLScreen implements TouchEventCallback {

    Animation test, walkRight;

    float time = 0f;

    public TestScreen(Game game) {
	super(game);
	Log.d("TEST", "STARTING TEST SCREEN...");

	/*test = new Animation(Assets.animations2, 412, 0, 103, 137, 4, 16, 0.24f);
	test.x = 0f;
	test.y = 91f;
	test.touchCallback = this;
	//addChild(test);*/

	Animation rightFacing = new Animation(Assets.sundotKulangotOneBlue, 0, 0, 92, 82, 8, 16, 1.24f);
	rightFacing.x = 103;
	rightFacing.y = 91;
	//addChild(rightFacing);

	/*Animation sickRight = new Animation(Assets.animations2, 0, 548, 103, 137, 8, 8, 0.24f);
	sickRight.x = 206;
	sickRight.y = 91;
	//addChild(sickRight);
	 * 
	Animation sickLeft = new Animation(Assets.animations2, 0, 685, 103, 137, 8, 8, 0.24f);
	sickLeft.x = 309;
	sickLeft.y = 91;
	//addChild(sickLeft);

	walkRight = new Animation(Assets.animations3, 0, 3, 103, 137, 4, 12, 0.10f);
	walkRight.x = 309;
	walkRight.y = 91;  
	walkRight.touchCallback = this;
	//addChild(walkRight);

	DoodleDroid d = new DoodleDroid(DoodleDroid.ARCUS);
	game.setDroid(d);
	//addChild(d);*/

	//Assets.load(Assets.game);
    }

    @Override
    public void backPressed() {
	game.exit();
    }

    @Override
    public void dispose() {
	// TODO Auto-generated method stub

    }

    @Override
    public void menuPressed() {

    }

    @Override
    public void onTouchEvent(DisplayObject source, TouchEvent event) {
	Log.d("TEST", "EVENT!");
	//walkRight.move(new Vector2((float) (Math.random() * 480), 91), Linear.INOUT, 5000, 0, false);
    }

    @Override
    public void pause() {
	// TODO Auto-generated method stub

    }

    @Override
    public void resume() {
	// TODO Auto-generated method stub

    }

    @Override
    public void update(float deltaTime) {
	time += deltaTime;
	Log.d("TEST", "T: " + time);
    }

}
