package org.nullsys.androidgames.doodledroids;

import org.nullsys.androidgames.framework.Game;
import org.nullsys.androidgames.framework.display.Button;
import org.nullsys.androidgames.framework.display.DisplayObjectContainer;
import org.nullsys.androidgames.framework.display.PercentageBar;
import org.nullsys.androidgames.framework.display.Sprite;
import org.nullsys.androidgames.framework.display.TextureRegion;
import org.nullsys.androidgames.framework.display.event.TouchEventCallback;
import org.nullsys.androidgames.framework.display.text.Text;
import org.nullsys.androidgames.framework.display.text.Text.TextRegistration;

public class HeadsUpDisplay extends DisplayObjectContainer {

    public DoodleDroid droid;

    public Sprite levelIcon, energyIcon, healthIcon, droillarsIcon;

    public Button chest, apparrels, worldMap, stats, pause;

    public Sprite hasWorm, hasVirus, hasHardwareFailure, exclamation;

    public PercentageBar expBarHolder, expBar, energyBarHolder, energyBar, healthBarHolder, healthBar;

    public Text level, droillars;

    public HeadsUpDisplay(Game game) {
	droid = game.getDroid();

	expBarHolder = new PercentageBar(new TextureRegion(Assets.stage, 51, 107, 62, 16));
	expBarHolder.x = 4;
	expBarHolder.y = 3;
	addChild(expBarHolder);

	expBar = new PercentageBar(new TextureRegion(Assets.stage, 89, 81, 58, 10));
	expBar.x = 6;
	expBar.y = 6;
	addChild(expBar);

	levelIcon = new Sprite(new TextureRegion(Assets.stage, 56, 66, 17, 25));
	levelIcon.x = 50;
	levelIcon.y = 25;
	addChild(levelIcon);

	energyBarHolder = new PercentageBar(new TextureRegion(Assets.stage, 93, 151, 81, 14));
	energyBarHolder.x = 87;
	energyBarHolder.y = 9;
	addChild(energyBarHolder);

	energyBar = new PercentageBar(new TextureRegion(Assets.stage, 130, 128, 76, 9));
	energyBar.x = 90;
	energyBar.y = 12;
	addChild(energyBar);

	energyIcon = new Sprite(new TextureRegion(Assets.stage, 46, 134, 22, 25));
	energyIcon.x = 78;
	energyIcon.y = 6;
	addChild(energyIcon);

	healthBarHolder = new PercentageBar(new TextureRegion(Assets.stage, 82, 190, 81, 14));
	healthBarHolder.x = 190;
	healthBarHolder.y = 9;
	addChild(healthBarHolder);

	healthBar = new PercentageBar(new TextureRegion(Assets.stage, 93, 173, 76, 9));
	healthBar.x = 193;
	healthBar.y = 12;
	addChild(healthBar);

	healthIcon = new Sprite(new TextureRegion(Assets.stage, 50, 165, 28, 26));
	healthIcon.x = 177;
	healthIcon.y = 6;
	addChild(healthIcon);

	droillarsIcon = new Sprite(new TextureRegion(Assets.stage, 10, 197, 24, 25));
	droillarsIcon.x = 280;
	droillarsIcon.y = 7;
	addChild(droillarsIcon);

	droillars = new Text(Assets.droillarsFont, "" + game.getDroid().droills);
	droillars.x = 307;
	droillars.y = 18;
	addChild(droillars);

	chest = new Button(new TextureRegion(Assets.stage, 14, 13, 44, 45), new TextureRegion(Assets.stage, 14, 13, 44, 45));
	chest.x = 14;
	chest.y = 262;
	chest.downstate.x = 14;
	chest.downstate.y = 259;
	chest.tapSound = Assets.press;
	addChild(chest);

	apparrels = new Button(new TextureRegion(Assets.stage, 65, 9, 58, 51), new TextureRegion(Assets.stage, 65, 9, 58, 51));
	apparrels.x = 65;
	apparrels.y = 260;
	apparrels.downstate.x = 65;
	apparrels.downstate.y = 257;
	apparrels.tapSound = Assets.tap;
	addChild(apparrels);

	worldMap = new Button(new TextureRegion(Assets.stage, 133, 10, 47, 47), new TextureRegion(Assets.stage, 133, 10, 47, 47));
	worldMap.x = 133;
	worldMap.y = 263;
	worldMap.downstate.x = 133;
	worldMap.downstate.y = 260;
	worldMap.tapSound = Assets.tap;
	addChild(worldMap);

	stats = new Button(new TextureRegion(Assets.stage, 194, 11, 44, 43), new TextureRegion(Assets.stage, 194, 11, 44, 43));
	stats.x = 194;
	stats.y = 266;
	stats.downstate.x = 194;
	stats.downstate.y = 263;
	stats.tapSound = Assets.tap;
	addChild(stats);

	pause = new Button(new TextureRegion(Assets.stage, 168, 70, 29, 31), new TextureRegion(Assets.stage, 211, 70, 27, 28));
	pause.x = 447;
	pause.y = 285;
	pause.downstate.x = 447;
	pause.downstate.y = 288;
	pause.tapSound = Assets.tap;
	addChild(pause);

	level = new Text(Assets.levelFont, "" + game.getDroid().level);
	level.x = 25;
	level.y = 36;
	level.registration = TextRegistration.CENTER;
	addChild(level);

	hasWorm = new Sprite(new TextureRegion(Assets.stage, 5, 116, 30, 32));
	hasWorm.x = 5;
	hasWorm.y = 172;
	addChild(hasWorm);

	hasVirus = new Sprite(new TextureRegion(Assets.stage, 4, 77, 32, 28));
	hasVirus.x = 4;
	hasVirus.y = 215;
	addChild(hasVirus);

	hasHardwareFailure = new Sprite(new TextureRegion(Assets.stage, 6, 155, 32, 32));
	hasHardwareFailure.x = 6;
	hasHardwareFailure.y = 133;
	addChild(hasHardwareFailure);

	exclamation = new Sprite(new TextureRegion(Assets.stage, 187, 158, 20, 33));
	exclamation.x = 220;
	exclamation.y = 261;
	addChild(exclamation);
    }

    @Override
    public void checkInputs(int eventType, int eventX, int eventY) {
	if (enabled)
	    for (int index = 0; index < displayObjects.size(); index++)
		displayObjects.get(index).checkInputs(eventType, eventX, eventY);
    }

    @Override
    public TextureRegion getTextureRegion() {
	// TODO Auto-generated method stub
	return null;
    }

    public void setTouchEventCallback(TouchEventCallback callback) {
	chest.touchCallback = callback;
	apparrels.touchCallback = callback;
	worldMap.touchCallback = callback;
	stats.touchCallback = callback;
	pause.touchCallback = callback;
    }

    @Override
    public void update(float deltaTime) {
	level.text = "" + droid.level;
	expBar.percentageWidth = (float) droid.exp / (float) droid.expNxtLvl;
	energyBar.percentageWidth = droid.getEnergyTotal() > droid.energy ? (float) droid.energy / (float) droid.getEnergyTotal() : (float) droid.getEnergyTotal() / (float) droid.energy;
	healthBar.percentageWidth = droid.getHealthTotal() > droid.health ? (float) droid.health / (float) droid.getHealthTotal() : (float) droid.getHealthTotal() / (float) droid.health;

	hasWorm.visible = droid.hasWorm;
	hasVirus.visible = droid.hasVirus;
	hasHardwareFailure.visible = droid.hasHardwareFailure;

	exclamation.visible = droid.freeStats > 0;

	droillars.text = "" + droid.droills;
    }
}
