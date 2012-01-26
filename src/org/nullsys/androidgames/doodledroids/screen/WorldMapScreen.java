package org.nullsys.androidgames.doodledroids.screen;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Back;
import aurelienribon.tweenengine.equations.Elastic;
import aurelienribon.tweenengine.equations.Linear;

import org.nullsys.androidgames.doodledroids.Assets;
import org.nullsys.androidgames.doodledroids.Settings;
import org.nullsys.androidgames.doodledroids.quest.Quest;
import org.nullsys.androidgames.doodledroids.quest.QuestFactory;
import org.nullsys.androidgames.framework.Game;
import org.nullsys.androidgames.framework.Input.TouchEvent;
import org.nullsys.androidgames.framework.Screen;
import org.nullsys.androidgames.framework.display.Button;
import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.Sprite;
import org.nullsys.androidgames.framework.display.TextureRegion;
import org.nullsys.androidgames.framework.display.text.Text;
import org.nullsys.androidgames.framework.display.text.Text.TextRegistration;
import org.nullsys.androidgames.framework.impl.GLScreen;
import org.nullsys.androidgames.framework.math.VectorCoords;

public class WorldMapScreen extends GLScreen {

    /** Numeric representation of the stages. */
    public static final int MARKET = 1, BERMUDA_TRIANGLE = 2, HOME = 3, SILICON_VALLEY = 4;

    /** Screen to return to if this screen is canceled. */
    private Screen previousScreen;

    /** The Droido Mondo logo at the bottom center of the screen*/
    private Sprite logo;

    /** Icon for the world time. */
    private Sprite worldTimeIcon;

    /** Stage navigation buttons. */
    private Button proceed, cancel;

    /** The continents in the map. */
    private Sprite islands, east, desert, ice, west, island;

    /** Sprites that represent the stages' icon. */
    private Sprite bermudaTriangle, market, home, siliconValley;

    /** Sprites that represent the stages' logo. */
    private Sprite bermudaTriangleLogo, marketLogo, homeLogo, siliconValleyLogo;

    /** Red pin that represents target stage. */
    private Sprite targetStageIcon;

    /** Icon that points to the current stage. */
    private Sprite currentStageIcon;

    /** Numeric representation of the target stage. */
    private int targetStage = 0;

    /** Numeric representation of the current stage. */
    private int currentStage = 0;

    /** Twenty-four-hour-type world time representation. */
    private Text worldTime;

    public WorldMapScreen(Game game, Screen previousScreen, int currentStage) {
	super(game);
	this.previousScreen = previousScreen;
	this.currentStage = currentStage;

	try {
	    game.getWorld().start();
	} catch (IllegalThreadStateException e) {

	}

	addChild(new Sprite(new TextureRegion(Assets.worldMap, 0, 347, 480, 320)));

	logo = new Sprite(new TextureRegion(Assets.worldMap, 0, 195, 225, 133));
	logo.x = 131;
	logo.y = -27;
	addChild(logo);

	islands = new Sprite(new TextureRegion(Assets.worldMap, 216, 0, 85, 57));
	islands.x = 12;
	islands.y = 230;
	addChild(islands);

	east = new Sprite(new TextureRegion(Assets.worldMap, 355, 0, 181, 174));
	east.x = 270;
	east.y = 68;
	addChild(east);

	desert = new Sprite(new TextureRegion(Assets.worldMap, 216, 114, 85, 67));
	desert.x = 221;
	desert.y = 125;
	addChild(desert);

	ice = new Sprite(new TextureRegion(Assets.worldMap, 216, 57, 139, 57));
	ice.x = 170;
	ice.y = 216;
	addChild(ice);

	west = new Sprite(new TextureRegion(Assets.worldMap, 0, 0, 216, 195));
	west.x = 24;
	west.y = 27;
	addChild(west);

	island = new Sprite(new TextureRegion(Assets.worldMap, 536, 0, 67, 80));
	island.x = 400;
	island.y = 179;
	addChild(island);

	bermudaTriangle = new Sprite(new TextureRegion(Assets.worldMap, 311, 283, 49, 64));
	bermudaTriangle.x = 414;
	bermudaTriangle.y = 174;
	bermudaTriangle.touchCallback = this;
	//addChild(bermudaTriangle);

	market = new Sprite(new TextureRegion(Assets.worldMap, 317, 195, 38, 46));
	market.x = 290;
	market.y = 186;
	market.touchCallback = this;
	addChild(market);

	home = new Sprite(new TextureRegion(Assets.worldMap, 317, 241, 43, 42));
	home.x = 368;
	home.y = 113;
	home.touchCallback = this;
	addChild(home);

	siliconValley = new Sprite(new TextureRegion(Assets.worldMap, 225, 270, 80, 57));
	siliconValley.x = 74;
	siliconValley.y = 121;
	siliconValley.touchCallback = this;
	addChild(siliconValley);

	bermudaTriangleLogo = new Sprite(new TextureRegion(Assets.worldMap, 360, 829, 333, 155));
	bermudaTriangleLogo.x = 415 - bermudaTriangleLogo.width * .3f;
	bermudaTriangleLogo.y = 74 + bermudaTriangleLogo.height * .3f;
	bermudaTriangleLogo.scaleX = .3f;
	bermudaTriangleLogo.scaleY = .3f;
	bermudaTriangleLogo.visible = false;
	addChild(bermudaTriangleLogo);

	marketLogo = new Sprite(new TextureRegion(Assets.worldMap, 360, 829, 333, 155));
	marketLogo.x = 364;
	marketLogo.y = 50;
	marketLogo.scaleX = .3f;
	marketLogo.scaleY = .3f;
	marketLogo.visible = false;
	addChild(marketLogo);

	homeLogo = new Sprite(new TextureRegion(Assets.worldMap, 0, 667, 356, 198));
	homeLogo.x = 359;
	homeLogo.y = 54;
	homeLogo.scaleX = .3f;
	homeLogo.scaleY = .3f;
	homeLogo.visible = false;
	addChild(homeLogo);

	siliconValleyLogo = new Sprite(new TextureRegion(Assets.worldMap, 360, 667, 310, 162));
	siliconValleyLogo.x = 368;
	siliconValleyLogo.y = 55;
	siliconValleyLogo.scaleX = .3f;
	siliconValleyLogo.scaleY = .3f;
	siliconValleyLogo.visible = false;
	addChild(siliconValleyLogo);

	worldTimeIcon = new Sprite(new TextureRegion(Assets.worldMap, 232, 195, 33, 29));
	worldTimeIcon.x = 190;
	worldTimeIcon.y = 289;
	worldTimeIcon.visible = false;
	addChild(worldTimeIcon);

	targetStageIcon = new Sprite(new TextureRegion(Assets.worldMap, 271, 195, 16, 28));
	targetStageIcon.visible = false;
	addChild(targetStageIcon);

	currentStageIcon = new Sprite(new TextureRegion(Assets.worldMap, 287, 195, 30, 29));
	currentStageIcon.visible = false;
	//addChild(currentStageIcon);

	worldTime = new Text(Assets.worldTimeFont, "");
	worldTime.x = 258;
	worldTime.y = 301;
	worldTime.registration = TextRegistration.CENTER;
	worldTime.visible = false;
	addChild(worldTime);

	proceed = new Button(new TextureRegion(Assets.worldMap, 225, 224, 90, 24), new TextureRegion(Assets.worldMap, 225, 224, 90, 24));
	proceed.x = 569;
	proceed.y = 28;
	proceed.downstate.x = 569;
	proceed.downstate.y = 25;
	proceed.touchCallback = this;
	proceed.tapSound = Assets.confirm;
	addChild(proceed);

	cancel = new Button(new TextureRegion(Assets.worldMap, 227, 248, 75, 22), new TextureRegion(Assets.worldMap, 227, 248, 75, 22));
	cancel.x = 575;
	cancel.y = 3;
	cancel.downstate.x = 575;
	cancel.downstate.y = 0;
	cancel.touchCallback = this;
	cancel.tapSound = Assets.cancel1;
	addChild(cancel);

	tweenDisplays();
    }

    @Override
    public void backPressed() {
	game.setScreen(previousScreen);
	if (Settings.musicEnabled) {
	    Assets.worldBGM.pause();
	    Assets.stageBGM.play();
	}
    }

    @Override
    public void dispose() {
	// TODO Auto-generated method stub

    }

    @Override
    public void menuPressed() {
	game.getDroid().restoreEnergy(1000000);
	game.getDroid().restoreHealth(1000000);
	game.getDroid().droills += 10000;
    }

    @Override
    public void onTouchEvent(DisplayObject source, TouchEvent event) {
	if (event.type == TouchEvent.TOUCH_DOWN)
	    if (source.equals(bermudaTriangle)) {
		targetStage = BERMUDA_TRIANGLE;
		proceed.x = 569;
		cancel.x = 575;
		proceed.visible = true;
		cancel.visible = true;
		proceed.move(new VectorCoords(proceed.x - 200, proceed.y), Elastic.OUT, 500, 0, false);
		cancel.move(new VectorCoords(cancel.x - 200, cancel.y), Elastic.OUT, 500, 0, false);
		if (Settings.soundEnabled)
		    Assets.tap.play(1);
	    } else if (source.equals(market)) {
		targetStage = MARKET;
		proceed.x = 569;
		cancel.x = 575;
		proceed.visible = true;
		cancel.visible = true;
		proceed.move(new VectorCoords(proceed.x - 200, proceed.y), Elastic.OUT, 500, 0, false);
		cancel.move(new VectorCoords(cancel.x - 200, cancel.y), Elastic.OUT, 500, 0, false);
		if (Settings.soundEnabled)
		    Assets.tap.play(1);
	    } else if (source.equals(home)) {
		targetStage = HOME;
		proceed.x = 569;
		cancel.x = 575;
		proceed.visible = true;
		cancel.visible = true;
		proceed.move(new VectorCoords(proceed.x - 200, proceed.y), Elastic.OUT, 500, 0, false);
		cancel.move(new VectorCoords(cancel.x - 200, cancel.y), Elastic.OUT, 500, 0, false);
		if (Settings.soundEnabled)
		    Assets.tap.play(1);
	    } else if (source.equals(siliconValley)) {
		targetStage = SILICON_VALLEY;
		proceed.x = 569;
		cancel.x = 575;
		proceed.visible = true;
		cancel.visible = true;
		proceed.move(new VectorCoords(proceed.x - 200, proceed.y), Elastic.OUT, 500, 0, false);
		cancel.move(new VectorCoords(cancel.x - 200, cancel.y), Elastic.OUT, 500, 0, false);
		if (Settings.soundEnabled)
		    Assets.tap.play(1);
	    }
	if (event.type == TouchEvent.TOUCH_TAP)
	    if (source.equals(cancel)) {
		targetStage = 0;
		proceed.visible = false;
		cancel.visible = false;
	    } else if (source.equals(proceed)) {
		displayObjects.get(0).tweenCallback = new TweenCallback() {

		    @Override
		    public void tweenEventOccured(Types arg0, Tween arg1) {
			if (targetStage == MARKET) {
			    game.setScreen(new StoreScreen(game, WorldMapScreen.this));
			    if (Settings.musicEnabled) {
				Assets.shopBGM.play();
				Assets.worldBGM.pause();
			    }
			} else if (targetStage == HOME) {
			    game.setScreen(new QuestScreen(previousScreen, WorldMapScreen.this.game, new Quest[] { QuestFactory.get("Free RAM"), QuestFactory.get("Rearrange Apps"), QuestFactory.get("Defrag Disk"), QuestFactory.get("Clear Cache"),
				    QuestFactory.get("Benchmark Device"), QuestFactory.get("Update Drivers"), QuestFactory.get("Debug Script"), QuestFactory.get("Patch Boot Files"), QuestFactory.get("Adjust Permissions"), QuestFactory.get("Remove Malware") }));
			    if (Settings.musicEnabled) {
				Assets.stageBGM.play();
				Assets.worldBGM.pause();
			    }
			} else if (targetStage == SILICON_VALLEY) {
			    game.setScreen(new QuestScreen(previousScreen, WorldMapScreen.this.game, new Quest[] { QuestFactory.get("Browser Alert"), QuestFactory.get("Freaking Computer Alert"), QuestFactory.get("Network Connection Alert"), QuestFactory.get("Spam Alert"),
				    QuestFactory.get("System Maintenance"), QuestFactory.get("Hacker Alert"), QuestFactory.get("Backup Database"), QuestFactory.get("Hacker Alert 2"), QuestFactory.get("Infiltrate The Box"), QuestFactory.get("Infiltrate The Box 2") }));
			    if (Settings.musicEnabled) {
				Assets.stageBGM.play();
				Assets.worldBGM.pause();
			    }
			}
		    }
		};
		displayObjects.get(0).move(0f, Linear.INOUT, 500, 0, true);
		for (DisplayObject element : displayObjects)
		    element.move(0f, Linear.INOUT, 500, 0, false);
	    }
    }

    @Override
    public void pause() {
	Assets.worldBGM.pause();
    }

    @Override
    public void resume() {
	if (Settings.musicEnabled && !Assets.worldBGM.isPlaying())
	    Assets.worldBGM.play();
    }

    @Override
    public void update(float deltaTime) {
	if (game.getWorld().worldMinutes < 10)
	    worldTime.text = "0" + game.getWorld().worldMinutes;
	else
	    worldTime.text = "" + game.getWorld().worldMinutes;
	worldTime.text += ":";
	if (game.getWorld().worldSeconds < 10)
	    worldTime.text += "0" + game.getWorld().worldSeconds;
	else
	    worldTime.text += "" + game.getWorld().worldSeconds;

	// Set the position of the current stage icon
	switch (currentStage) {
	    case MARKET:
		currentStageIcon.x = 301;
		currentStageIcon.y = 237;
		break;
	    case BERMUDA_TRIANGLE:
		currentStageIcon.x = 430;
		currentStageIcon.y = 242;
		break;
	    case HOME:
		currentStageIcon.x = 381;
		currentStageIcon.y = 163;
		break;
	    case SILICON_VALLEY:
		currentStageIcon.x = 106;
		currentStageIcon.y = 184;
		break;
	    default:
	}

	// Set the position of the target stage icon
	switch (targetStage) {
	    case MARKET:
		targetStageIcon.x = 301;
		targetStageIcon.y = 237;
		targetStageIcon.visible = true;
		hideOtherStageIcon(targetStage);
		break;
	    case BERMUDA_TRIANGLE:
		targetStageIcon.x = 430;
		targetStageIcon.y = 242;
		targetStageIcon.visible = true;
		hideOtherStageIcon(targetStage);
		break;
	    case HOME:
		targetStageIcon.x = 381;
		targetStageIcon.y = 163;
		targetStageIcon.visible = true;
		hideOtherStageIcon(targetStage);
		break;
	    case SILICON_VALLEY:
		targetStageIcon.x = 106;
		targetStageIcon.y = 184;
		targetStageIcon.visible = true;
		hideOtherStageIcon(targetStage);
		break;
	    default:
		targetStageIcon.visible = false;
		hideOtherStageIcon(targetStage);
		break;
	}

    }

    /** Show only the logo of the selected target stage. */
    private void hideOtherStageIcon(int targetStage) {
	switch (targetStage) {
	    case MARKET:
		bermudaTriangleLogo.visible = false;
		marketLogo.visible = true;
		homeLogo.visible = false;
		siliconValleyLogo.visible = false;
		break;
	    case BERMUDA_TRIANGLE:
		bermudaTriangleLogo.visible = true;
		marketLogo.visible = false;
		homeLogo.visible = false;
		siliconValleyLogo.visible = false;
		break;
	    case HOME:
		bermudaTriangleLogo.visible = false;
		marketLogo.visible = false;
		homeLogo.visible = true;
		siliconValleyLogo.visible = false;
		break;
	    case SILICON_VALLEY:
		bermudaTriangleLogo.visible = false;
		marketLogo.visible = false;
		homeLogo.visible = false;
		siliconValleyLogo.visible = true;
		break;
	    default:
		bermudaTriangleLogo.visible = false;
		marketLogo.visible = false;
		homeLogo.visible = false;
		siliconValleyLogo.visible = false;
		break;

	}
    }

    /** Animate the displays. Invoked at the beginning. */
    private void tweenDisplays() {
	logo.tweenCallback = new TweenCallback() {

	    @Override
	    public void tweenEventOccured(Types arg0, Tween arg1) {
		worldTime.visible = true;
		worldTimeIcon.visible = true;
		currentStageIcon.visible = true;
	    }
	};

	logo.y -= 100;
	logo.move(new VectorCoords(logo.x, logo.y + 100), Linear.INOUT, 500, 0, true);

	islands.x = islands.x + (islands.width - islands.width * 0.2f) / 2;
	islands.y = islands.y + (islands.height - islands.height * 0.2f) / 2;
	islands.scaleX = 0f;
	islands.scaleY = 0f;
	islands.move(12, 230, 1f, 1f, 0, 1f, Back.OUT, 1000, 0, false);

	west.x = west.x + (west.width - west.width * 0.2f) / 2;
	west.y = west.y + (west.height - west.height * 0.2f) / 2;
	west.scaleX = 0f;
	west.scaleY = 0f;
	west.move(24, 27, 1f, 1f, 0, 1f, Back.OUT, 1000, 100, false);

	east.x = east.x + (east.width - east.width * 0.2f) / 2;
	east.y = east.y + (east.height - east.height * 0.2f) / 2;
	east.scaleX = 0f;
	east.scaleY = 0f;
	east.move(270, 68, 1f, 1f, 0, 1f, Back.OUT, 1000, 200, false);

	desert.x = desert.x + (desert.width - desert.width * 0.2f) / 2;
	desert.y = desert.y + (desert.height - desert.height * 0.2f) / 2;
	desert.scaleX = 0f;
	desert.scaleY = 0f;
	desert.move(221, 125, 1f, 1f, 0, 1f, Back.OUT, 1000, 300, false);

	ice.x = ice.x + (ice.width - ice.width * 0.2f) / 2;
	ice.y = ice.y + (ice.height - ice.height * 0.2f) / 2;
	ice.scaleX = 0f;
	ice.scaleY = 0f;
	ice.move(170, 216, 1f, 1f, 0, 1f, Back.OUT, 1000, 400, false);

	island.x = island.x + (island.width - island.width * 0.2f) / 2;
	island.y = island.y + (island.height - island.height * 0.2f) / 2;
	island.scaleX = 0f;
	island.scaleY = 0f;
	island.move(400, 179, 1f, 1f, 0, 1f, Back.OUT, 1000, 500, false);

	bermudaTriangle.x = bermudaTriangle.x + (bermudaTriangle.width - bermudaTriangle.width * 0.2f) / 2;
	bermudaTriangle.y = bermudaTriangle.y + (bermudaTriangle.height - bermudaTriangle.height * 0.2f) / 2;
	bermudaTriangle.scaleX = 0f;
	bermudaTriangle.scaleY = 0f;
	bermudaTriangle.move(414, 174, 1f, 1f, 0, 1f, Back.OUT, 1000, 600, false);

	home.x = home.x + (home.width - home.width * 0.2f) / 2;
	home.y = home.y + (home.height - home.height * 0.2f) / 2;
	home.scaleX = 0f;
	home.scaleY = 0f;
	home.move(368, 113, 1f, 1f, 0, 1f, Back.OUT, 1000, 650, false);

	market.x = market.x + (market.width - market.width * 0.2f) / 2;
	market.y = market.y + (market.height - market.height * 0.2f) / 2;
	market.scaleX = 0f;
	market.scaleY = 0f;
	market.move(290, 186, 1f, 1f, 0, 1f, Back.OUT, 1000, 700, false);

	siliconValley.x = siliconValley.x + (siliconValley.width - siliconValley.width * 0.2f) / 2;
	siliconValley.y = siliconValley.y + (siliconValley.height - siliconValley.height * 0.2f) / 2;
	siliconValley.scaleX = 0f;
	siliconValley.scaleY = 0f;
	siliconValley.move(74, 121, 1f, 1f, 0, 1f, Back.OUT, 1000, 700, false);
    }

}
