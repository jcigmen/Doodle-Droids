package org.nullsys.androidgames.doodledroids.screen;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Back;
import aurelienribon.tweenengine.equations.Linear;

import javax.microedition.khronos.opengles.GL10;

import org.nullsys.androidgames.doodledroids.Assets;
import org.nullsys.androidgames.doodledroids.Settings;
import org.nullsys.androidgames.doodledroids.event.DroidStateCallback;
import org.nullsys.androidgames.doodledroids.event.QuestEventCallback;
import org.nullsys.androidgames.doodledroids.quest.Quest;
import org.nullsys.androidgames.framework.Game;
import org.nullsys.androidgames.framework.Input.TouchEvent;
import org.nullsys.androidgames.framework.Screen;
import org.nullsys.androidgames.framework.display.Animation;
import org.nullsys.androidgames.framework.display.Button;
import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.Sprite;
import org.nullsys.androidgames.framework.display.TextureRegion;
import org.nullsys.androidgames.framework.display.event.TouchEventCallback;
import org.nullsys.androidgames.framework.display.text.Text;
import org.nullsys.androidgames.framework.display.text.Text.TextRegistration;
import org.nullsys.androidgames.framework.impl.GLScreen;
import org.nullsys.androidgames.framework.math.VectorCoords;

public class QuestScreen extends GLScreen implements TouchEventCallback, QuestEventCallback, DroidStateCallback {

    public Screen previousScreen;

    public Quest[] quests;

    public Sprite backPanel;
    public Sprite frontPanel;
    public Sprite stageLogo;
    public Sprite actorDialog;
    public Sprite energyIcon;
    public Sprite healthIcon;
    public Sprite expIcon;
    public Sprite droillarsIcon;
    public Sprite apparel;
    public Sprite chest;
    public Sprite requirementsLabel;
    public Sprite rewardsLabel;
    public Button triggerQuest;
    public Button leftButton;
    public Button rightButton;
    public Button back;
    public Animation actor;
    public Text questTitle;
    public Text questDescription;
    public Text actorDialogText;
    public Text requiredEnergy;
    public Text requiredHealth;
    public Text rewardExp;
    public Text rewardDroills;

    public int questIndex = 0;
    public int actorDialogCounter = 0;

    public QuestScreen(Screen previousScreen, Game game, Quest[] quests) {
	super(game);

	this.previousScreen = previousScreen;

	this.quests = quests;

	game.getDroid().callback = this;

	for (int index = 0; index < this.quests.length; index++)
	    this.quests[index].callback = this;

	for (int index = 0; index < previousScreen.displayObjects.size(); index++)
	    previousScreen.displayObjects.get(index).enabled = false;

	displayObjects.addAll(previousScreen.displayObjects);

	Sprite opaqueBackground = new Sprite(new TextureRegion(Assets.quest, 377, 207, 100, 67));
	opaqueBackground.width = 480;
	opaqueBackground.height = 320;
	addChild(opaqueBackground);

	backPanel = new Sprite(new TextureRegion(Assets.quest, 0, 0, 309, 287));
	backPanel.alpha = 0f;
	addChild(backPanel);

	frontPanel = new Sprite(new TextureRegion(Assets.quest, 0, 0, 309, 287));
	addChild(frontPanel);

	actorDialog = new Sprite(new TextureRegion(Assets.quest, 18, 312, 203, 80));
	actorDialog.x = 141;
	actorDialog.visible = false;
	actorDialog.touchCallback = this;
	addChild(actorDialog);

	energyIcon = new Sprite(new TextureRegion(Assets.quest, 323, 121, 12, 13));
	energyIcon.x = 225;
	energyIcon.y = 137;
	addChild(energyIcon);

	healthIcon = new Sprite(new TextureRegion(Assets.quest, 360, 120, 15, 14));
	healthIcon.x = 280;
	healthIcon.y = 137;
	addChild(healthIcon);

	expIcon = new Sprite(new TextureRegion(Assets.quest, 350, 158, 9, 13));
	expIcon.x = 225;
	expIcon.y = 90;
	addChild(expIcon);

	droillarsIcon = new Sprite(new TextureRegion(Assets.quest, 335, 193, 13, 14));
	droillarsIcon.x = 280;
	droillarsIcon.y = 90;
	addChild(droillarsIcon);

	apparel = new Sprite(new TextureRegion(Assets.quest, 391, 150, 29, 25));
	apparel.x = 373;
	apparel.y = 82;

	chest = new Sprite(new TextureRegion(Assets.quest, 391, 150, 29, 25));
	chest.x = 341;
	chest.y = 83;
	addChild(apparel);

	requirementsLabel = new Sprite(new TextureRegion(Assets.quest, 317, 95, 59, 10));
	requirementsLabel.x = 218;
	requirementsLabel.y = 159;
	addChild(requirementsLabel);

	rewardsLabel = new Sprite(new TextureRegion(Assets.quest, 450, 175, 37, 13));
	rewardsLabel.x = 218;
	rewardsLabel.y = 117;
	addChild(rewardsLabel);

	triggerQuest = new Button(new TextureRegion(Assets.quest, 401, 9, 61, 61), new TextureRegion(Assets.quest, 388, 72, 45, 45));
	triggerQuest.x = 366;
	triggerQuest.y = 7;
	triggerQuest.downstate.x = 374;
	triggerQuest.downstate.y = 15;
	triggerQuest.touchCallback = this;
	triggerQuest.tapSound = Assets.confirm;
	addChild(triggerQuest);

	leftButton = new Button(new TextureRegion(Assets.quest, 314, 8, 39, 37), new TextureRegion(Assets.quest, 320, 49, 29, 27));
	leftButton.x = 160;
	leftButton.y = 151;
	leftButton.downstate.x = 165;
	leftButton.downstate.y = 156;
	leftButton.touchCallback = this;
	leftButton.tapSound = Assets.tap;
	addChild(leftButton);

	rightButton = new Button(new TextureRegion(Assets.quest, 356, 8, 39, 37), new TextureRegion(Assets.quest, 362, 49, 29, 27));
	rightButton.x = 417;
	rightButton.y = 150;
	rightButton.downstate.x = 422;
	rightButton.downstate.y = 155;
	rightButton.touchCallback = this;
	rightButton.tapSound = Assets.tap;
	addChild(rightButton);

	questTitle = new Text(Assets.poohBlackFont, quests[0].name);
	questTitle.x = 210;
	questTitle.y = 270;
	questTitle.scaleX = .6f;
	questTitle.scaleY = .6f;
	addChild(questTitle);

	questDescription = new Text(Assets.poohBlackFont, quests[0].description);
	questDescription.x = 226;
	questDescription.y = 228;
	questDescription.scaleX = .4f;
	questDescription.scaleY = .4f;
	addChild(questDescription);

	actorDialogText = new Text(Assets.poohBlackFont, "");
	actorDialogText.x = 241;
	actorDialogText.y = 34;
	actorDialogText.scaleX = .5f;
	actorDialogText.scaleY = .5f;
	actorDialogText.registration = TextRegistration.CENTER;
	actorDialogText.visible = false;
	addChild(actorDialogText);

	requiredEnergy = new Text(Assets.questEnergyFont, "" + quests[0].requiredEnergy);
	requiredEnergy.x = 245;
	requiredEnergy.y = 142;
	requiredEnergy.scaleX = .5f;
	requiredEnergy.scaleY = .5f;
	addChild(requiredEnergy);

	requiredHealth = new Text(Assets.questHealthFont, "" + quests[0].requiredHealth);
	requiredHealth.x = 303;
	requiredHealth.y = 142;
	requiredHealth.scaleX = .5f;
	requiredHealth.scaleY = .5f;
	addChild(requiredHealth);

	rewardExp = new Text(Assets.questExpFont, "" + quests[0].exp);
	rewardExp.x = 245;
	rewardExp.y = 96;
	rewardExp.scaleX = .5f;
	rewardExp.scaleY = .5f;
	addChild(rewardExp);

	rewardDroills = new Text(Assets.droillarsFont, "" + quests[0].droills);
	rewardDroills.x = 299;
	rewardDroills.y = 96;
	rewardDroills.scaleX = .5f;
	rewardDroills.scaleY = .5f;
	addChild(rewardDroills);

	back = new Button(new TextureRegion(Assets.mainMenu, 896, 0, 55, 53), new TextureRegion(Assets.mainMenu, 903, 54, 41, 39));
	back.x = 7;
	back.y = 6;
	back.downstate.x = 14;
	back.downstate.y = 13;
	back.touchCallback = this;
	back.tapSound = Assets.cancel1;
	addChild(back);

	back.x = 0 - back.width;
	back.move(new VectorCoords(12, 11), Back.OUT, 500, 0, false);

	flip(false);
    }

    @Override
    public void backPressed() {
	for (int index = 0; index < previousScreen.displayObjects.size(); index++)
	    previousScreen.displayObjects.get(index).enabled = true;
	game.setScreen(previousScreen);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void menuPressed() {
    }

    @Override
    public void onEnergyInsufficient() {
	game.setScreen(new PromptScreen(game, this, PromptScreen.OK_PROMPT, "ENERGY\nINSUFFICIENT!"));
    }

    @Override
    public void onHealthInsufficient() {
	game.setScreen(new PromptScreen(game, this, PromptScreen.OK_PROMPT, "HEALTH\nINSUFFICIENT!"));
    }

    @Override
    public void onItemDrop(String itemName) {
	game.setScreen(new PromptScreen(game, this, PromptScreen.OK_PROMPT, "OBTAINED\n" + itemName));
    }

    @Override
    public void onItemsInsufficient() {
	game.setScreen(new PromptScreen(game, this, PromptScreen.OK_PROMPT, "ITEMS\nINSUFFICIENT!"));
    }

    @Override
    public void onLevelInsufficient() {
	game.setScreen(new PromptScreen(game, this, PromptScreen.OK_PROMPT, "LEVEL\nINSUFFICIENT!"));
    }

    @Override
    public void onLevelUp(int level) {
	if (Settings.soundEnabled)
	    Assets.levelUp.play(1);
	game.setScreen(new PromptScreen(game, this, PromptScreen.OK_PROMPT, "LEVEL UP!\n" + game.getDroid().name + " is now\nLvl " + level + "!"));
    }

    @Override
    public void onTouchEvent(DisplayObject source, TouchEvent event) {
	if (event.type == TouchEvent.TOUCH_TAP)
	    if (source.equals(leftButton)) {
		questIndex--;
		flip(true);
	    } else if (source.equals(rightButton)) {
		questIndex++;
		flip(true);
	    } else if (source.equals(triggerQuest))
		quests[questIndex].trigger(game.getDroid());
	    else if (source.equals(actorDialog)) {
		actorDialog.visible = false;
		actorDialogText.visible = false;
	    } else if (source.equals(back))
		backPressed();
    }

    @Override
    public void pause() {
	Assets.stageBGM.pause();
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

	if (backPanel.tween != null && backPanel.tween.isFinished())
	    for (int index = 0; index < quests[questIndex].requiredItems.length; index++) {
		quests[questIndex].requiredItems[index].x = 341 + 65 * index - quests[questIndex].requiredItems[index].width * .25f / 2;
		quests[questIndex].requiredItems[index].y = 142 - quests[questIndex].requiredItems[index].height * .25f / 2;
		quests[questIndex].requiredItems[index].scaleX = .25f;
		quests[questIndex].requiredItems[index].scaleY = .25f;
		quests[questIndex].requiredItems[index].render(batcher);
	    }

	gl.glDisable(GL10.GL_BLEND);
    }

    @Override
    public void resume() {
	if (Settings.musicEnabled && !Assets.stageBGM.isPlaying())
	    Assets.stageBGM.play();
    }

    @Override
    public void update(float deltaTime) {
	actorDialogCounter++;
	questTitle.text = quests[questIndex].name;
	questDescription.text = quests[questIndex].description;
	requiredEnergy.text = "" + quests[questIndex].requiredEnergy;
	requiredHealth.text = "" + quests[questIndex].requiredHealth;
	rewardExp.text = "" + quests[questIndex].exp;
	rewardDroills.text = "" + quests[questIndex].droills;

	if (questIndex < quests.length - 1) {
	    rightButton.visible = true;
	    rightButton.enabled = true;
	} else if (questIndex >= quests.length - 1) {
	    rightButton.visible = false;
	    rightButton.enabled = false;
	}

	if (questIndex > 0) {
	    leftButton.alpha = 1;
	    leftButton.scaleX = 1f;
	    leftButton.scaleY = 1f;
	    leftButton.x = 160;
	    leftButton.y = 151;
	    leftButton.visible = true;
	    leftButton.enabled = true;
	} else {
	    leftButton.visible = false;
	    leftButton.enabled = false;
	}

	if (actorDialogCounter >= 50) {
	    actorDialog.visible = false;
	    actorDialogText.visible = false;
	}

	apparel.visible = quests[questIndex].dropItems.length > 0 && backPanel.tween != null && backPanel.tween.isFinished();
    }

    private void flip(boolean flipFrontPanel) {
	questTitle.visible = false;
	questDescription.visible = false;
	energyIcon.visible = false;
	healthIcon.visible = false;
	expIcon.visible = false;
	droillarsIcon.visible = false;
	apparel.visible = false;
	chest.visible = false;
	requirementsLabel.visible = false;
	rewardsLabel.visible = false;
	requiredEnergy.visible = false;
	requiredHealth.visible = false;
	rewardExp.visible = false;
	rewardDroills.visible = false;

	frontPanel.tweenCallback = new TweenCallback() {

	    @Override
	    public void tweenEventOccured(Types arg0, Tween arg1) {
		frontPanel.x = 168;
		frontPanel.y = 4;
		frontPanel.alpha = 0f;
		frontPanel.move(153, 23, 1f, 1f, 1, 1f, Linear.INOUT, 250, 0, false);

		backPanel.x = 188;
		backPanel.y = -16;
		backPanel.alpha = 0f;
		backPanel.move(168, 4, 1f, 1f, 1, 1f, Linear.INOUT, 250, 250, true);
	    }
	};

	backPanel.tweenCallback = new TweenCallback() {

	    @Override
	    public void tweenEventOccured(Types arg0, Tween arg1) {
		questTitle.visible = true;
		questDescription.visible = true;
		energyIcon.visible = true;
		healthIcon.visible = true;
		expIcon.visible = true;
		droillarsIcon.visible = true;
		apparel.visible = true;
		chest.visible = true;
		requirementsLabel.visible = true;
		rewardsLabel.visible = true;
		requiredEnergy.visible = true;
		requiredHealth.visible = true;
		rewardExp.visible = true;
		rewardDroills.visible = true;
	    }
	};

	if (flipFrontPanel)
	    frontPanel.move(frontPanel.x - 20, frontPanel.y + 20, 1f, 1f, 1, 0f, Linear.INOUT, 250, 0, true);
	else {
	    leftButton.x = leftButton.x + (leftButton.width - leftButton.width * 0.2f) / 2;
	    leftButton.y = leftButton.y + (leftButton.height - leftButton.height * 0.2f) / 2;
	    leftButton.scaleX = .2f;
	    leftButton.scaleY = .2f;
	    leftButton.alpha = 0f;
	    leftButton.move(160, 151, 1f, 1f, 1, 1f, Back.OUT, 1000, 0, false);

	    rightButton.x = rightButton.x + (rightButton.width - rightButton.width * 0.2f) / 2;
	    rightButton.y = rightButton.y + (rightButton.height - rightButton.height * 0.2f) / 2;
	    rightButton.scaleX = .2f;
	    rightButton.scaleY = .2f;
	    rightButton.alpha = 0f;
	    rightButton.move(417, 150, 1f, 1f, 1, 1f, Back.OUT, 1000, 0, false);

	    triggerQuest.x = triggerQuest.x + (triggerQuest.width - triggerQuest.width * 0.2f) / 2;
	    triggerQuest.y = triggerQuest.y + (triggerQuest.height - triggerQuest.height * 0.2f) / 2;
	    triggerQuest.scaleX = .2f;
	    triggerQuest.scaleY = .2f;
	    triggerQuest.alpha = 0f;
	    triggerQuest.move(366, 7, 1f, 1f, 1, 1f, Back.OUT, 1000, 0, false);

	    frontPanel.x = 168;
	    frontPanel.y = 4;
	    frontPanel.alpha = 0f;
	    frontPanel.move(153, 23, 1f, 1f, 1, 1f, Linear.INOUT, 250, 0, false);

	    backPanel.x = 188;
	    backPanel.y = -16;
	    backPanel.alpha = 0f;
	    backPanel.move(168, 4, 1f, 1f, 1, 1f, Linear.INOUT, 250, 250, true);
	}
    }

}
