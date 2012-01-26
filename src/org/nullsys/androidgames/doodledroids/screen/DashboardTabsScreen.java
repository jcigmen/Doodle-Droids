package org.nullsys.androidgames.doodledroids.screen;

import java.util.ArrayList;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Expo;

import javax.microedition.khronos.opengles.GL10;

import org.nullsys.androidgames.doodledroids.Assets;
import org.nullsys.androidgames.doodledroids.DoodleDroid;
import org.nullsys.androidgames.doodledroids.Settings;
import org.nullsys.androidgames.doodledroids.item.Apparel;
import org.nullsys.androidgames.doodledroids.item.EquippableItem;
import org.nullsys.androidgames.doodledroids.item.Headgear;
import org.nullsys.androidgames.doodledroids.item.ItemFactory;
import org.nullsys.androidgames.framework.Game;
import org.nullsys.androidgames.framework.Input.TouchEvent;
import org.nullsys.androidgames.framework.Screen;
import org.nullsys.androidgames.framework.display.Button;
import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.Sprite;
import org.nullsys.androidgames.framework.display.TextureRegion;
import org.nullsys.androidgames.framework.display.ToggleButton;
import org.nullsys.androidgames.framework.display.event.TouchEventCallback;
import org.nullsys.androidgames.framework.display.text.Text;
import org.nullsys.androidgames.framework.display.text.Text.TextRegistration;
import org.nullsys.androidgames.framework.impl.GLScreen;
import org.nullsys.androidgames.framework.math.VectorCoords;

public class DashboardTabsScreen extends GLScreen implements TouchEventCallback {

    public static final int ITEMS = 0;

    public static final int APPAREL = 1;

    public static final int STATUS = 2;

    public ArrayList<DisplayObject> itemTabDisplays = new ArrayList<DisplayObject>();

    public ArrayList<DisplayObject> apparelTabDisplays = new ArrayList<DisplayObject>();

    public ArrayList<DisplayObject> statusTabDisplays = new ArrayList<DisplayObject>();

    public DoodleDroid droid;

    public int currentTab = 0;

    public Screen stage;

    public Sprite opaqueBackground;
    public Sprite panel;

    public Sprite itemsTabSelected;
    public Sprite itemsTabUnselected;
    public Sprite apparelTabSelected;
    public Sprite apparelTabUnselected;
    public Sprite statusTabSelected;
    public Sprite statusTabUnselected;

    public Sprite energyLabel;
    public Sprite healthLabel;
    public Sprite luckLabel;
    public Sprite remainingPointsLabel;
    public Sprite ailmentsLabel;

    public Button energyButton;
    public Button healthButton;
    public Button luckButton;

    public Text droidEnergy;
    public Text droidHealth;
    public Text droidLuck;

    public Text droidEnergyBonus;
    public Text droidHealthBonus;
    public Text droidLuckBonus;

    public Text remainingPoints;

    public Button apparelsLeftButton;

    public Button apparelsRightButton;

    public Button apparelEquip;

    public ToggleButton apparelBox1, apparelBox2, apparelBox3, apparelBox4, apparelBox5, apparelBox6;

    public Sprite expIcon, apparelEnergyIcon, apparelHealthIcon;

    public Text apparelName, apparelExpBonus, apparelEnergyBonus, apparelHealthBonus;

    public int apparelsIndex = 0;

    public int apparelSelectedIndex = 7;

    public Text itemName;

    public Text itemDescription;

    public Button itemLeftButton, itemRightButton;

    public ToggleButton itemBox1, itemBox2, itemBox3, itemBox4, itemBox5, itemBox6, itemBox7, itemBox8, itemBox9;

    public Button useItem;

    public int itemsIndex = 0, itemSelectedIndex = 10;

    public Button back;

    public DashboardTabsScreen(Game game, Screen stage) {
	super(game);

	if (Settings.musicEnabled && !Assets.stageBGM.isPlaying())
	    Assets.stageBGM.play();

	droid = game.getDroid();

	this.stage = stage;

	opaqueBackground = new Sprite(new TextureRegion(Assets.quest, 377, 207, 100, 67));
	opaqueBackground.width = 480;
	opaqueBackground.height = 320;
	addChild(opaqueBackground);

	itemsTabSelected = new Sprite(new TextureRegion(Assets.dashboardTabs, 8, 285, 147, 59));
	itemsTabSelected.x = 24;
	itemsTabSelected.y = 253;
	itemsTabSelected.touchCallback = this;
	addChild(itemsTabSelected);

	itemsTabUnselected = new Sprite(new TextureRegion(Assets.dashboardTabs, 8, 355, 147, 59));
	itemsTabUnselected.x = 24;
	itemsTabUnselected.y = 253;
	itemsTabUnselected.touchCallback = this;
	addChild(itemsTabUnselected);

	apparelTabSelected = new Sprite(new TextureRegion(Assets.dashboardTabs, 165, 285, 147, 59));
	apparelTabSelected.x = 167;
	apparelTabSelected.y = 253;
	apparelTabSelected.touchCallback = this;
	addChild(apparelTabSelected);

	apparelTabUnselected = new Sprite(new TextureRegion(Assets.dashboardTabs, 165, 355, 147, 59));
	apparelTabUnselected.x = 167;
	apparelTabUnselected.y = 253;
	apparelTabUnselected.touchCallback = this;
	addChild(apparelTabUnselected);

	statusTabSelected = new Sprite(new TextureRegion(Assets.dashboardTabs, 319, 285, 147, 59));
	statusTabSelected.x = 310;
	statusTabSelected.y = 253;
	statusTabSelected.touchCallback = this;
	addChild(statusTabSelected);

	statusTabUnselected = new Sprite(new TextureRegion(Assets.dashboardTabs, 319, 355, 147, 59));
	statusTabUnselected.x = 310;
	statusTabUnselected.y = 253;
	statusTabUnselected.touchCallback = this;
	addChild(statusTabUnselected);

	panel = new Sprite(new TextureRegion(Assets.dashboardTabs, 0, 0, 475, 270));
	panel.x = 2;
	panel.y = 4;
	addChild(panel);

	energyLabel = new Sprite(new TextureRegion(Assets.dashboardTabs, 510, 190, 162, 49));
	energyLabel.x = 48;
	energyLabel.y = 193;
	statusTabDisplays.add(energyLabel);
	addChild(energyLabel);

	healthLabel = new Sprite(new TextureRegion(Assets.dashboardTabs, 507, 119, 171, 50));
	healthLabel.x = 42;
	healthLabel.y = 126;
	statusTabDisplays.add(healthLabel);
	addChild(healthLabel);

	luckLabel = new Sprite(new TextureRegion(Assets.dashboardTabs, 498, 49, 176, 60));
	luckLabel.x = 42;
	luckLabel.y = 57;
	statusTabDisplays.add(luckLabel);
	addChild(luckLabel);

	remainingPointsLabel = new Sprite(new TextureRegion(Assets.dashboardTabs, 567, 22, 69, 11));
	remainingPointsLabel.x = 290;
	remainingPointsLabel.y = 23;
	statusTabDisplays.add(remainingPointsLabel);
	addChild(remainingPointsLabel);

	ailmentsLabel = new Sprite(new TextureRegion(Assets.dashboardTabs, 515, 22, 42, 9));
	ailmentsLabel.x = 122;
	ailmentsLabel.y = 25;
	statusTabDisplays.add(ailmentsLabel);
	addChild(ailmentsLabel);

	energyButton = new Button(new TextureRegion(Assets.dashboardTabs, 698, 17, 38, 38), new TextureRegion(Assets.dashboardTabs, 742, 17, 38, 38));
	energyButton.x = 389;
	energyButton.y = 198;
	energyButton.downstate.x = 389;
	energyButton.downstate.y = 198;
	energyButton.touchCallback = this;
	energyButton.tapSound = Assets.confirm;
	statusTabDisplays.add(energyButton);
	addChild(energyButton);

	healthButton = new Button(new TextureRegion(Assets.dashboardTabs, 698, 17, 38, 38), new TextureRegion(Assets.dashboardTabs, 742, 17, 38, 38));
	healthButton.x = 389;
	healthButton.y = 134;
	healthButton.downstate.x = 389;
	healthButton.downstate.y = 134;
	healthButton.touchCallback = this;
	healthButton.tapSound = Assets.confirm;
	statusTabDisplays.add(healthButton);
	addChild(healthButton);

	luckButton = new Button(new TextureRegion(Assets.dashboardTabs, 698, 17, 38, 38), new TextureRegion(Assets.dashboardTabs, 742, 17, 38, 38));
	luckButton.x = 389;
	luckButton.y = 68;
	luckButton.downstate.x = 389;
	luckButton.downstate.y = 68;
	luckButton.touchCallback = this;
	luckButton.tapSound = Assets.confirm;
	statusTabDisplays.add(luckButton);
	addChild(luckButton);

	droidEnergy = new Text(Assets.poohBlackFont, "" + droid.energyTotal);
	droidEnergy.registration = TextRegistration.CENTER;
	droidEnergy.x = 287;
	droidEnergy.y = 214;
	droidEnergy.width += 10;
	droidEnergy.height += 10;
	statusTabDisplays.add(droidEnergy);
	addChild(droidEnergy);

	droidHealth = new Text(Assets.poohBlackFont, "" + droid.healthTotal);
	droidHealth.registration = TextRegistration.CENTER;
	droidHealth.x = 287;
	droidHealth.y = 150;
	droidHealth.width += 10;
	droidHealth.height += 10;
	statusTabDisplays.add(droidHealth);
	addChild(droidHealth);

	droidLuck = new Text(Assets.poohBlackFont, "" + droid.healthTotal);
	droidLuck.registration = TextRegistration.CENTER;
	droidLuck.x = 287;
	droidLuck.y = 83;
	droidLuck.width += 10;
	droidLuck.height += 10;
	statusTabDisplays.add(droidLuck);
	addChild(droidLuck);

	droidEnergyBonus = new Text(Assets.poohGreenFont, "+" + droid.getEnergyBonus());
	droidEnergyBonus.registration = TextRegistration.CENTER;
	droidEnergyBonus.x = 350;
	droidEnergyBonus.y = 213;
	droidEnergyBonus.scaleX = .65f;
	droidEnergyBonus.scaleY = .65f;
	statusTabDisplays.add(droidEnergyBonus);
	addChild(droidEnergyBonus);

	droidHealthBonus = new Text(Assets.poohGreenFont, "+" + droid.getHealthBonus());
	droidHealthBonus.registration = TextRegistration.CENTER;
	droidHealthBonus.x = 350;
	droidHealthBonus.y = 149;
	droidHealthBonus.scaleX = .65f;
	droidHealthBonus.scaleY = .65f;
	statusTabDisplays.add(droidHealthBonus);
	addChild(droidHealthBonus);

	droidLuckBonus = new Text(Assets.poohGreenFont, "+" + droid.getLuckBonus());
	droidLuckBonus.registration = TextRegistration.CENTER;
	droidLuckBonus.x = 350;
	droidLuckBonus.y = 82;
	droidLuckBonus.scaleX = .65f;
	droidLuckBonus.scaleY = .65f;
	statusTabDisplays.add(droidLuckBonus);
	addChild(droidLuckBonus);

	remainingPoints = new Text(Assets.poohBlackFont, "" + droid.freeStats);
	remainingPoints.registration = TextRegistration.CENTER;
	remainingPoints.x = 381;
	remainingPoints.y = 29;
	remainingPoints.scaleX = .8f;
	remainingPoints.scaleY = .8f;
	statusTabDisplays.add(remainingPoints);
	addChild(remainingPoints);

	back = new Button(new TextureRegion(Assets.mainMenu, 896, 0, 55, 53), new TextureRegion(Assets.mainMenu, 903, 54, 41, 39));
	back.x = 7;
	back.y = 6;
	back.downstate.x = 14;
	back.downstate.y = 13;
	back.tapSound = Assets.cancel1;
	back.touchCallback = this;
	addChild(back);

	apparelsLeftButton = new Button(new TextureRegion(Assets.dashboardTabs, 789, 18, 41, 39), new TextureRegion(Assets.dashboardTabs, 833, 22, 35, 33));
	apparelsLeftButton.x = 208;
	apparelsLeftButton.y = 167;
	apparelsLeftButton.downstate.x = 211;
	apparelsLeftButton.downstate.y = 170;
	apparelsLeftButton.touchCallback = this;
	apparelTabDisplays.add(apparelsLeftButton);
	addChild(apparelsLeftButton);

	apparelsRightButton = new Button(new TextureRegion(Assets.dashboardTabs, 875, 19, 41, 39), new TextureRegion(Assets.dashboardTabs, 921, 22, 35, 33));
	apparelsRightButton.x = 422;
	apparelsRightButton.y = 167;
	apparelsRightButton.downstate.x = 425;
	apparelsRightButton.downstate.y = 170;
	apparelsRightButton.touchCallback = this;
	apparelTabDisplays.add(apparelsRightButton);
	addChild(apparelsRightButton);

	apparelBox1 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	apparelBox1.x = 253;
	apparelBox1.y = 190;
	apparelBox1.downstate.x = 253;
	apparelBox1.downstate.y = 190;
	apparelBox1.touchCallback = this;
	apparelTabDisplays.add(apparelBox1);
	addChild(apparelBox1);

	apparelBox2 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	apparelBox2.x = 313;
	apparelBox2.y = 190;
	apparelBox2.downstate.x = 313;
	apparelBox2.downstate.y = 190;
	apparelBox2.touchCallback = this;
	apparelTabDisplays.add(apparelBox2);
	addChild(apparelBox2);

	apparelBox3 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	apparelBox3.x = 373;
	apparelBox3.y = 190;
	apparelBox3.downstate.x = 373;
	apparelBox3.downstate.y = 190;
	apparelBox3.touchCallback = this;
	apparelTabDisplays.add(apparelBox3);
	addChild(apparelBox3);

	apparelBox4 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	apparelBox4.x = 253;
	apparelBox4.y = 131;
	apparelBox4.downstate.x = 253;
	apparelBox4.downstate.y = 131;
	apparelBox4.touchCallback = this;
	apparelTabDisplays.add(apparelBox4);
	addChild(apparelBox4);

	apparelBox5 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	apparelBox5.x = 313;
	apparelBox5.y = 131;
	apparelBox5.downstate.x = 313;
	apparelBox5.downstate.y = 131;
	apparelBox5.touchCallback = this;
	apparelTabDisplays.add(apparelBox5);
	addChild(apparelBox5);

	apparelBox6 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	apparelBox6.x = 373;
	apparelBox6.y = 131;
	apparelBox6.downstate.x = 373;
	apparelBox6.downstate.y = 131;
	apparelBox6.touchCallback = this;
	apparelTabDisplays.add(apparelBox6);
	addChild(apparelBox6);

	expIcon = new Sprite(new TextureRegion(Assets.stage, 56, 66, 17, 25));
	expIcon.x = 249;
	expIcon.y = 48;
	apparelTabDisplays.add(expIcon);
	addChild(expIcon);

	apparelEnergyIcon = new Sprite(new TextureRegion(Assets.stage, 46, 134, 22, 25));
	apparelEnergyIcon.x = 308;
	apparelEnergyIcon.y = 46;
	apparelTabDisplays.add(apparelEnergyIcon);
	addChild(apparelEnergyIcon);

	apparelHealthIcon = new Sprite(new TextureRegion(Assets.stage, 50, 165, 28, 26));
	apparelHealthIcon.x = 370;
	apparelHealthIcon.y = 46;
	apparelTabDisplays.add(apparelHealthIcon);
	addChild(apparelHealthIcon);

	apparelName = new Text(Assets.poohBlackFont, "");
	apparelName.registration = TextRegistration.CENTER;
	apparelName.x = 335;
	apparelName.y = 102;
	apparelTabDisplays.add(apparelName);
	addChild(apparelName);

	apparelExpBonus = new Text(Assets.poohGreenFont, "");
	apparelExpBonus.x = 269;
	apparelExpBonus.y = 60;
	apparelExpBonus.scaleX = .3f;
	apparelExpBonus.scaleY = .3f;
	apparelTabDisplays.add(apparelExpBonus);
	addChild(apparelExpBonus);

	apparelEnergyBonus = new Text(Assets.poohGreenFont, "");
	apparelEnergyBonus.x = 332;
	apparelEnergyBonus.y = 60;
	apparelEnergyBonus.scaleX = .3f;
	apparelEnergyBonus.scaleY = .3f;
	apparelTabDisplays.add(apparelEnergyBonus);
	addChild(apparelEnergyBonus);

	apparelHealthBonus = new Text(Assets.poohGreenFont, "");
	apparelHealthBonus.x = 401;
	apparelHealthBonus.y = 60;
	apparelHealthBonus.scaleX = .3f;
	apparelHealthBonus.scaleY = .3f;
	apparelTabDisplays.add(apparelHealthBonus);
	addChild(apparelHealthBonus);

	apparelEquip = new Button(new TextureRegion(Assets.dashboardTabs, 815, 79, 53, 21), new TextureRegion(Assets.dashboardTabs, 815, 103, 53, 21));
	apparelEquip.x = 249;
	apparelEquip.y = 180;
	apparelEquip.downstate.x = 249;
	apparelEquip.downstate.y = 180;
	apparelEquip.touchCallback = this;
	apparelTabDisplays.add(apparelEquip);
	addChild(apparelEquip);

	itemName = new Text(Assets.poohBlackFont, "");
	itemName.registration = TextRegistration.CENTER;
	itemName.x = 121;
	itemName.y = 222;
	itemTabDisplays.add(itemName);
	addChild(itemName);

	itemDescription = new Text(Assets.poohBlackFont, "");
	itemDescription.registration = TextRegistration.CENTER;
	itemDescription.x = 120;
	itemDescription.y = 185;
	itemDescription.scaleX = .5f;
	itemDescription.scaleY = .5f;
	itemTabDisplays.add(itemDescription);
	addChild(itemDescription);

	itemLeftButton = new Button(new TextureRegion(Assets.dashboardTabs, 789, 18, 41, 39), new TextureRegion(Assets.dashboardTabs, 833, 22, 35, 33));
	itemLeftButton.x = 208;
	itemLeftButton.y = 116;
	itemLeftButton.downstate.x = 212;
	itemLeftButton.downstate.y = 120;
	itemLeftButton.touchCallback = this;
	itemLeftButton.tapSound = Assets.tap;
	itemTabDisplays.add(itemLeftButton);
	addChild(itemLeftButton);

	itemRightButton = new Button(new TextureRegion(Assets.dashboardTabs, 875, 19, 41, 39), new TextureRegion(Assets.dashboardTabs, 921, 22, 35, 33));
	itemRightButton.x = 423;
	itemRightButton.y = 114;
	itemRightButton.downstate.x = 427;
	itemRightButton.downstate.y = 118;
	itemRightButton.touchCallback = this;
	itemRightButton.tapSound = Assets.tap;
	itemTabDisplays.add(itemRightButton);
	addChild(itemRightButton);

	itemBox1 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox1.x = 253;
	itemBox1.y = 174;
	itemBox1.downstate.x = 253;
	itemBox1.downstate.y = 174;
	itemBox1.touchCallback = this;
	itemTabDisplays.add(itemBox1);
	addChild(itemBox1);

	itemBox4 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox4.x = 253;
	itemBox4.y = 115;
	itemBox4.downstate.x = 253;
	itemBox4.downstate.y = 115;
	itemBox4.touchCallback = this;
	itemTabDisplays.add(itemBox4);
	addChild(itemBox4);

	itemBox7 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox7.x = 253;
	itemBox7.y = 54;
	itemBox7.downstate.x = 253;
	itemBox7.downstate.y = 54;
	itemBox7.touchCallback = this;
	itemTabDisplays.add(itemBox7);
	addChild(itemBox7);

	itemBox2 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox2.x = 313;
	itemBox2.y = 174;
	itemBox2.downstate.x = 313;
	itemBox2.downstate.y = 174;
	itemBox2.touchCallback = this;
	itemTabDisplays.add(itemBox2);
	addChild(itemBox2);

	itemBox5 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox5.x = 313;
	itemBox5.y = 115;
	itemBox5.downstate.x = 313;
	itemBox5.downstate.y = 115;
	itemBox5.touchCallback = this;
	itemTabDisplays.add(itemBox5);
	addChild(itemBox5);

	itemBox8 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox8.x = 313;
	itemBox8.y = 54;
	itemBox8.downstate.x = 313;
	itemBox8.downstate.y = 54;
	itemBox8.touchCallback = this;
	itemTabDisplays.add(itemBox8);
	addChild(itemBox8);

	itemBox3 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox3.x = 373;
	itemBox3.y = 174;
	itemBox3.downstate.x = 373;
	itemBox3.downstate.y = 174;
	itemBox3.touchCallback = this;
	itemTabDisplays.add(itemBox3);
	addChild(itemBox3);

	itemBox6 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox6.x = 373;
	itemBox6.y = 115;
	itemBox6.downstate.x = 373;
	itemBox6.downstate.y = 115;
	itemBox6.touchCallback = this;
	itemTabDisplays.add(itemBox6);
	addChild(itemBox6);

	itemBox9 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox9.x = 373;
	itemBox9.y = 54;
	itemBox9.downstate.x = 373;
	itemBox9.downstate.y = 54;
	itemBox9.touchCallback = this;
	itemTabDisplays.add(itemBox9);
	addChild(itemBox9);

	useItem = new Button(new TextureRegion(Assets.dashboardTabs, 695, 144, 94, 35), new TextureRegion(Assets.dashboardTabs, 695, 180, 94, 35));
	useItem.x = 74;
	useItem.y = 65;
	useItem.downstate.x = 74;
	useItem.downstate.y = 65;
	useItem.touchCallback = this;
	itemTabDisplays.add(useItem);
	addChild(useItem);

	for (int index = 1; index < displayObjects.size(); index++)
	    displayObjects.get(index).x -= 480;
    }

    @Override
    public void backPressed() {
	DisplayObject last = displayObjects.get(displayObjects.size() - 1);
	last.tweenCallback = new TweenCallback() {

	    @Override
	    public void tweenEventOccured(Types arg0, Tween arg1) {
		game.setScreen(stage);
	    }
	};
	unselectOtherApparelBox(7);
	unselectOtherItemBox(10);
	for (int index = 1; index < displayObjects.size() - 1; index++)
	    displayObjects.get(index).move(new VectorCoords(displayObjects.get(index).x - 480, displayObjects.get(index).y), Expo.OUT, 500, 0, false);
	last.move(new VectorCoords(last.x - 480, last.y), Expo.OUT, 500, 0, true);
	for (int index = 0; index < stage.displayObjects.size(); index++)
	    stage.displayObjects.get(index).enabled = true;
    }

    @Override
    public void dispose() {
    }

    @Override
    public void menuPressed() {
    }

    @Override
    public void onTouchEvent(DisplayObject source, TouchEvent event) {
	if (event.type == TouchEvent.TOUCH_DOWN)
	    if (source.equals(apparelBox1) && apparelSelectedIndex == 0)
		unselectOtherApparelBox(7);
	    else if (source.equals(apparelBox1) && apparelSelectedIndex != 0)
		unselectOtherApparelBox(1);
	    else if (source.equals(apparelBox2) && apparelSelectedIndex == 1)
		unselectOtherApparelBox(7);
	    else if (source.equals(apparelBox2) && apparelSelectedIndex != 1)
		unselectOtherApparelBox(2);
	    else if (source.equals(apparelBox3) && apparelSelectedIndex == 2)
		unselectOtherApparelBox(7);
	    else if (source.equals(apparelBox3) && apparelSelectedIndex != 2)
		unselectOtherApparelBox(3);
	    else if (source.equals(apparelBox4) && apparelSelectedIndex == 3)
		unselectOtherApparelBox(7);
	    else if (source.equals(apparelBox4) && apparelSelectedIndex != 3)
		unselectOtherApparelBox(4);
	    else if (source.equals(apparelBox5) && apparelSelectedIndex == 4)
		unselectOtherApparelBox(7);
	    else if (source.equals(apparelBox5) && apparelSelectedIndex != 4)
		unselectOtherApparelBox(5);
	    else if (source.equals(apparelBox6) && apparelSelectedIndex == 5)
		unselectOtherApparelBox(7);
	    else if (source.equals(apparelBox6) && apparelSelectedIndex != 5)
		unselectOtherApparelBox(6);
	    else if (source.equals(itemBox1) && itemSelectedIndex == 0)
		unselectOtherItemBox(10);
	    else if (source.equals(itemBox1) && itemSelectedIndex != 0)
		unselectOtherItemBox(1);
	    else if (source.equals(itemBox2) && itemSelectedIndex == 1)
		unselectOtherItemBox(10);
	    else if (source.equals(itemBox2) && itemSelectedIndex != 1)
		unselectOtherItemBox(2);
	    else if (source.equals(itemBox3) && itemSelectedIndex == 2)
		unselectOtherItemBox(10);
	    else if (source.equals(itemBox3) && itemSelectedIndex != 2)
		unselectOtherItemBox(3);
	    else if (source.equals(itemBox4) && itemSelectedIndex == 3)
		unselectOtherItemBox(10);
	    else if (source.equals(itemBox4) && itemSelectedIndex != 3)
		unselectOtherItemBox(4);
	    else if (source.equals(itemBox5) && itemSelectedIndex == 4)
		unselectOtherItemBox(10);
	    else if (source.equals(itemBox5) && itemSelectedIndex != 4)
		unselectOtherItemBox(5);
	    else if (source.equals(itemBox6) && itemSelectedIndex == 5)
		unselectOtherItemBox(10);
	    else if (source.equals(itemBox6) && itemSelectedIndex != 5)
		unselectOtherItemBox(6);
	    else if (source.equals(itemBox7) && itemSelectedIndex == 6)
		unselectOtherItemBox(10);
	    else if (source.equals(itemBox7) && itemSelectedIndex != 6)
		unselectOtherItemBox(7);
	    else if (source.equals(itemBox8) && itemSelectedIndex == 7)
		unselectOtherItemBox(10);
	    else if (source.equals(itemBox8) && itemSelectedIndex != 7)
		unselectOtherItemBox(8);
	    else if (source.equals(itemBox9) && itemSelectedIndex == 8)
		unselectOtherItemBox(10);
	    else if (source.equals(itemBox9) && itemSelectedIndex != 8)
		unselectOtherItemBox(9);
	if (event.type == TouchEvent.TOUCH_TAP)
	    if (source.equals(itemsTabUnselected)) {
		showItemsTab();
		currentTab = ITEMS;
		if (Settings.soundEnabled)
		    Assets.tap.play(1);
	    } else if (source.equals(apparelTabUnselected)) {
		showApparelsTab();
		currentTab = APPAREL;
		if (Settings.soundEnabled)
		    Assets.tap.play(1);
	    } else if (source.equals(statusTabUnselected)) {
		showStatusTab();
		currentTab = STATUS;
		if (Settings.soundEnabled)
		    Assets.tap.play(1);
	    } else if (source.equals(energyButton)) {
		droid.energyTotal++;
		droid.freeStats--;
	    } else if (source.equals(healthButton)) {
		droid.healthTotal++;
		droid.freeStats--;
	    } else if (source.equals(luckButton)) {
		droid.luck++;
		droid.freeStats--;
	    } else if (source.equals(back))
		backPressed();
	    else if (source.equals(apparelsLeftButton))
		apparelsIndex--;
	    else if (source.equals(apparelsRightButton))
		apparelsIndex++;
	    else if (source.equals(apparelEquip) && droid.equipment.get(6 * apparelsIndex + apparelSelectedIndex) instanceof Apparel) {
		Apparel apparel = droid.apparel;
		droid.apparel = (Apparel) droid.equipment.remove(6 * apparelsIndex + apparelSelectedIndex);
		droid.equipment.add(apparel);
		droid.apparel.scaleX = 1f;
		droid.apparel.scaleY = 1f;
	    } else if (source.equals(apparelEquip) && droid.equipment.get(6 * apparelsIndex + apparelSelectedIndex) instanceof Headgear) {
		if (droid.headgear != null) {
		    Headgear headgear = (Headgear) ItemFactory.getEquippable(droid.headgear.name);
		    droid.equipment.add(headgear);
		}
		droid.headgear = (Headgear) droid.equipment.remove(6 * apparelsIndex + apparelSelectedIndex);
		droid.headgear.scaleX = 1f;
		droid.headgear.scaleY = 1f;
	    } else if (source.equals(itemLeftButton))
		itemsIndex--;
	    else if (source.equals(itemRightButton))
		itemsIndex++;
	    else if (source.equals(useItem))
		droid.usableItems.remove(9 * itemsIndex + itemSelectedIndex).use(droid);
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

	for (int index = 0; index < stage.displayObjects.size(); index++) {
	    gl.glColor4f(stage.displayObjects.get(index).r, stage.displayObjects.get(index).g, stage.displayObjects.get(index).b, stage.displayObjects.get(index).alpha);
	    stage.displayObjects.get(index).render(batcher);
	}

	for (int index = 0; index < displayObjects.size(); index++) {
	    gl.glColor4f(displayObjects.get(index).r, displayObjects.get(index).g, displayObjects.get(index).b, displayObjects.get(index).alpha);
	    displayObjects.get(index).render(batcher);
	}

	if (currentTab == APPAREL) {
	    for (int index = 6 * apparelsIndex, rowIndex = 0, colIndex = 0; index < droid.equipment.size() && index < 6 * apparelsIndex + 6 && apparelsLeftButton.tween.isFinished(); index++) {
		float widthRatio = droid.equipment.get(index).getWidth() * .5f / 2;
		float heightRatio = droid.equipment.get(index).getHeight() * .5f / 2;
		droid.equipment.get(index).setX(276 + rowIndex * 60 - widthRatio);
		droid.equipment.get(index).setY(190 - colIndex * 60 + heightRatio);
		droid.equipment.get(index).setScaleX(.5f);
		droid.equipment.get(index).setScaleY(.5f);
		droid.equipment.get(index).render(batcher);
		if (rowIndex == 2) {
		    rowIndex = 0;
		    colIndex++;
		} else
		    rowIndex++;
	    }

	    if (apparelsLeftButton.tween.isFinished()) {
		droid.x = 64;
		droid.y = 65;
		droid.render(batcher);
	    }
	}

	if (currentTab == ITEMS)
	    for (int index = 9 * itemsIndex, rowIndex = 0, colIndex = 0; index < droid.usableItems.size() && index < 9 * itemsIndex + 9 && back.tween.isFinished(); index++) {
		float widthRatio = droid.usableItems.get(index).getWidth() * .5f / 2;
		float heightRatio = droid.usableItems.get(index).getHeight() * .5f / 2;
		droid.usableItems.get(index).x = 276 + rowIndex * 60 - widthRatio;
		droid.usableItems.get(index).y = 169 - colIndex * 60 + heightRatio;
		droid.usableItems.get(index).scaleX = .5f;
		droid.usableItems.get(index).scaleY = .5f;
		droid.usableItems.get(index).render(batcher);
		if (rowIndex % 2 == 0 && rowIndex > 0) {
		    rowIndex = 0;
		    colIndex++;
		} else
		    rowIndex++;
	    }
	gl.glDisable(GL10.GL_BLEND);
    }

    @Override
    public void resume() {
	if (Settings.musicEnabled && !Assets.stageBGM.isPlaying())
	    Assets.stageBGM.play();
    }

    public void show(int tab) {
	for (int index = 1; index < displayObjects.size(); index++)
	    displayObjects.get(index).move(new VectorCoords(displayObjects.get(index).x + 480, displayObjects.get(index).y), Expo.OUT, 1000, 0, false);

	for (int index = 0; index < stage.displayObjects.size(); index++)
	    stage.displayObjects.get(index).enabled = false;

	currentTab = tab;
	switch (tab) {
	    case ITEMS:
		showItemsTab();
		break;
	    case APPAREL:
		showApparelsTab();
		break;
	    case STATUS:
		showStatusTab();
		break;
	}
    }

    @Override
    public void update(float deltaTime) {
	if (currentTab == STATUS) {
	    droidEnergy.text = "" + droid.energyTotal;
	    droidHealth.text = "" + droid.healthTotal;
	    droidLuck.text = "" + droid.luck;
	    droidEnergyBonus.text = "+" + droid.getEnergyBonus();
	    droidEnergyBonus.x = 35 + droidEnergy.x + droidEnergy.getWidth();
	    droidHealthBonus.text = "+" + droid.getHealthBonus();
	    droidHealthBonus.x = 35 + droidHealth.x + droidHealth.getWidth();
	    droidLuckBonus.text = "+" + droid.getLuckBonus();
	    droidLuckBonus.x = 35 + droidLuck.x + droidLuck.getWidth();
	    remainingPoints.text = "" + droid.freeStats;

	    if (droid.getEnergyBonus() > 0)
		droidEnergyBonus.visible = true;
	    else
		droidEnergyBonus.visible = false;

	    if (droid.getHealthBonus() > 0)
		droidHealthBonus.visible = true;
	    else
		droidHealthBonus.visible = false;

	    if (droid.getLuckBonus() > 0)
		droidLuckBonus.visible = true;
	    else
		droidLuckBonus.visible = false;

	    if (droid.freeStats > 0) {
		energyButton.visible = true;
		healthButton.visible = true;
		luckButton.visible = true;
	    } else {
		energyButton.visible = false;
		healthButton.visible = false;
		luckButton.visible = false;
	    }
	}

	else if (currentTab == APPAREL) {
	    if (droid.equipment.size() > (apparelsIndex + 1) * 6)
		apparelsRightButton.visible = true;
	    else
		apparelsRightButton.visible = false;

	    if (apparelsIndex > 0)
		apparelsLeftButton.visible = true;
	    else
		apparelsLeftButton.visible = false;

	    try {
		if (apparelSelectedIndex > 6)
		    throw new IndexOutOfBoundsException();

		EquippableItem currentlySelectedApparel = droid.equipment.get(6 * apparelsIndex + apparelSelectedIndex);
		apparelName.text = currentlySelectedApparel != null ? currentlySelectedApparel.getName() : "";

		if (currentlySelectedApparel != null && currentlySelectedApparel.getExpBonus() > 0)
		    apparelExpBonus.text = "+" + currentlySelectedApparel.getExpBonus() + "%";
		else if (currentlySelectedApparel != null && currentlySelectedApparel.getExpBonus() < 0) {
		    apparelExpBonus.font = Assets.poohRedFont;
		    apparelExpBonus.text = "-" + currentlySelectedApparel.getExpBonus() + "%";
		} else {
		    apparelExpBonus.font = Assets.poohGreenFont;
		    apparelExpBonus.text = "";
		}

		if (currentlySelectedApparel != null && currentlySelectedApparel.getEnergyBonus() > 0)
		    apparelEnergyBonus.text = "+" + currentlySelectedApparel.getEnergyBonus();
		else if (currentlySelectedApparel != null && currentlySelectedApparel.getEnergyBonus() < 0) {
		    apparelEnergyBonus.font = Assets.poohRedFont;
		    apparelEnergyBonus.text = "-" + currentlySelectedApparel.getEnergyBonus();
		} else {
		    apparelEnergyBonus.font = Assets.poohGreenFont;
		    apparelEnergyBonus.text = "";
		}

		if (currentlySelectedApparel != null && currentlySelectedApparel.getHealthBonus() > 0)
		    apparelHealthBonus.text = "+" + currentlySelectedApparel.getHealthBonus();
		else if (currentlySelectedApparel != null && currentlySelectedApparel.getHealthBonus() < 0) {
		    apparelHealthBonus.font = Assets.poohRedFont;
		    apparelHealthBonus.text = "-" + currentlySelectedApparel.getHealthBonus();
		} else {
		    apparelHealthBonus.font = Assets.poohGreenFont;
		    apparelHealthBonus.text = "";
		}

		switch (apparelSelectedIndex) {
		    case 0:
			apparelEquip.x = 249;
			apparelEquip.y = 180;
			apparelEquip.downstate.x = 249;
			apparelEquip.downstate.y = 180;
			apparelEquip.visible = true;
			break;
		    case 1:
			apparelEquip.x = 310;
			apparelEquip.y = 180;
			apparelEquip.downstate.x = 310;
			apparelEquip.downstate.y = 180;
			apparelEquip.visible = true;
			break;
		    case 2:
			apparelEquip.x = 370;
			apparelEquip.y = 180;
			apparelEquip.downstate.x = 370;
			apparelEquip.downstate.y = 180;
			apparelEquip.visible = true;
			break;
		    case 3:
			apparelEquip.x = 249;
			apparelEquip.y = 120;
			apparelEquip.downstate.x = 249;
			apparelEquip.downstate.y = 120;
			apparelEquip.visible = true;
			break;
		    case 4:
			apparelEquip.x = 310;
			apparelEquip.y = 120;
			apparelEquip.downstate.x = 310;
			apparelEquip.downstate.y = 120;
			apparelEquip.visible = true;
			break;
		    case 5:
			apparelEquip.x = 370;
			apparelEquip.y = 120;
			apparelEquip.downstate.x = 370;
			apparelEquip.downstate.y = 120;
			apparelEquip.visible = true;
			break;
		}

	    } catch (IndexOutOfBoundsException e) {
		apparelName.text = "";
		apparelExpBonus.text = "";
		apparelEnergyBonus.text = "";
		apparelHealthBonus.text = "";
		apparelEquip.visible = false;
	    }
	} else if (currentTab == ITEMS) {
	    itemRightButton.visible = droid.usableItems.size() > (itemsIndex + 1) * 9;
	    itemLeftButton.visible = itemsIndex > 0;
	    try {
		useItem.visible = itemSelectedIndex <= 8 && droid.usableItems.get(9 * itemsIndex + itemSelectedIndex) != null;
		itemName.text = droid.usableItems.get(9 * itemsIndex + itemSelectedIndex).name;
		itemDescription.text = droid.usableItems.get(9 * itemsIndex + itemSelectedIndex).description;
	    } catch (IndexOutOfBoundsException e) {
		useItem.visible = false;
		itemName.text = "";
		itemDescription.text = "";
	    }
	}
    }

    private void showApparelsTab() {
	itemsTabSelected.visible = false;
	itemsTabUnselected.visible = true;
	apparelTabSelected.visible = true;
	apparelTabUnselected.visible = false;
	statusTabSelected.visible = false;
	statusTabUnselected.visible = true;
	panel.visible = true;

	for (int index = 0; index < itemTabDisplays.size(); index++)
	    itemTabDisplays.get(index).visible = false;
	for (int index = 0; index < apparelTabDisplays.size(); index++)
	    apparelTabDisplays.get(index).visible = true;
	for (int index = 0; index < statusTabDisplays.size(); index++)
	    statusTabDisplays.get(index).visible = false;
    }

    private void showItemsTab() {
	itemsTabSelected.visible = true;
	itemsTabUnselected.visible = false;
	apparelTabSelected.visible = false;
	apparelTabUnselected.visible = true;
	statusTabSelected.visible = false;
	statusTabUnselected.visible = true;
	panel.visible = true;

	for (int index = 0; index < itemTabDisplays.size(); index++)
	    itemTabDisplays.get(index).visible = true;
	for (int index = 0; index < apparelTabDisplays.size(); index++)
	    apparelTabDisplays.get(index).visible = false;
	for (int index = 0; index < statusTabDisplays.size(); index++)
	    statusTabDisplays.get(index).visible = false;
    }

    private void showStatusTab() {
	itemsTabSelected.visible = false;
	itemsTabUnselected.visible = true;
	apparelTabSelected.visible = false;
	apparelTabUnselected.visible = true;
	statusTabSelected.visible = true;
	statusTabUnselected.visible = false;
	panel.visible = true;

	for (int index = 0; index < itemTabDisplays.size(); index++)
	    itemTabDisplays.get(index).visible = false;
	for (int index = 0; index < apparelTabDisplays.size(); index++)
	    apparelTabDisplays.get(index).visible = false;
	for (int index = 0; index < statusTabDisplays.size(); index++)
	    statusTabDisplays.get(index).visible = true;
    }

    private void unselectOtherApparelBox(int selectedBox) {
	switch (selectedBox) {
	    case 1:
		apparelSelectedIndex = 0;
		apparelBox2.toggled = false;
		apparelBox3.toggled = false;
		apparelBox4.toggled = false;
		apparelBox5.toggled = false;
		apparelBox6.toggled = false;
		break;
	    case 2:
		apparelSelectedIndex = 1;
		apparelBox1.toggled = false;
		apparelBox3.toggled = false;
		apparelBox4.toggled = false;
		apparelBox5.toggled = false;
		apparelBox6.toggled = false;
		break;
	    case 3:
		apparelSelectedIndex = 2;
		apparelBox1.toggled = false;
		apparelBox2.toggled = false;
		apparelBox4.toggled = false;
		apparelBox5.toggled = false;
		apparelBox6.toggled = false;
		break;
	    case 4:
		apparelSelectedIndex = 3;
		apparelBox1.toggled = false;
		apparelBox2.toggled = false;
		apparelBox3.toggled = false;
		apparelBox5.toggled = false;
		apparelBox6.toggled = false;
		break;
	    case 5:
		apparelSelectedIndex = 4;
		apparelBox1.toggled = false;
		apparelBox2.toggled = false;
		apparelBox3.toggled = false;
		apparelBox4.toggled = false;
		apparelBox6.toggled = false;
		break;
	    case 6:
		apparelSelectedIndex = 5;
		apparelBox1.toggled = false;
		apparelBox2.toggled = false;
		apparelBox3.toggled = false;
		apparelBox4.toggled = false;
		apparelBox5.toggled = false;
		break;
	    default:
		apparelSelectedIndex = 7;
		apparelBox1.toggled = false;
		apparelBox2.toggled = false;
		apparelBox3.toggled = false;
		apparelBox4.toggled = false;
		apparelBox5.toggled = false;
		apparelBox6.toggled = false;
	}
    }

    private void unselectOtherItemBox(int selectedBox) {
	switch (selectedBox) {
	    case 1:
		itemSelectedIndex = 0;
		itemBox2.toggled = false;
		itemBox3.toggled = false;
		itemBox4.toggled = false;
		itemBox5.toggled = false;
		itemBox6.toggled = false;
		itemBox7.toggled = false;
		itemBox8.toggled = false;
		itemBox9.toggled = false;
		break;
	    case 2:
		itemSelectedIndex = 1;
		itemBox1.toggled = false;
		itemBox3.toggled = false;
		itemBox4.toggled = false;
		itemBox5.toggled = false;
		itemBox6.toggled = false;
		itemBox7.toggled = false;
		itemBox8.toggled = false;
		itemBox9.toggled = false;
		break;
	    case 3:
		itemSelectedIndex = 2;
		itemBox1.toggled = false;
		itemBox2.toggled = false;
		itemBox4.toggled = false;
		itemBox5.toggled = false;
		itemBox6.toggled = false;
		itemBox7.toggled = false;
		itemBox8.toggled = false;
		itemBox9.toggled = false;
		break;
	    case 4:
		itemSelectedIndex = 3;
		itemBox1.toggled = false;
		itemBox2.toggled = false;
		itemBox3.toggled = false;
		itemBox5.toggled = false;
		itemBox6.toggled = false;
		itemBox7.toggled = false;
		itemBox8.toggled = false;
		itemBox9.toggled = false;
		break;
	    case 5:
		itemSelectedIndex = 4;
		itemBox1.toggled = false;
		itemBox2.toggled = false;
		itemBox3.toggled = false;
		itemBox4.toggled = false;
		itemBox6.toggled = false;
		itemBox7.toggled = false;
		itemBox8.toggled = false;
		itemBox9.toggled = false;
		break;
	    case 6:
		itemSelectedIndex = 5;
		itemBox1.toggled = false;
		itemBox2.toggled = false;
		itemBox3.toggled = false;
		itemBox4.toggled = false;
		itemBox5.toggled = false;
		itemBox7.toggled = false;
		itemBox8.toggled = false;
		itemBox9.toggled = false;
		break;
	    case 7:
		itemSelectedIndex = 6;
		itemBox1.toggled = false;
		itemBox2.toggled = false;
		itemBox3.toggled = false;
		itemBox4.toggled = false;
		itemBox5.toggled = false;
		itemBox6.toggled = false;
		itemBox8.toggled = false;
		itemBox9.toggled = false;
		break;
	    case 8:
		itemSelectedIndex = 7;
		itemBox2.toggled = false;
		itemBox3.toggled = false;
		itemBox4.toggled = false;
		itemBox5.toggled = false;
		itemBox6.toggled = false;
		itemBox7.toggled = false;
		itemBox1.toggled = false;
		itemBox9.toggled = false;
		break;
	    case 9:
		itemSelectedIndex = 8;
		itemBox2.toggled = false;
		itemBox3.toggled = false;
		itemBox4.toggled = false;
		itemBox5.toggled = false;
		itemBox6.toggled = false;
		itemBox7.toggled = false;
		itemBox8.toggled = false;
		itemBox1.toggled = false;
		break;
	    default:
		itemSelectedIndex = 10;
		itemBox1.toggled = false;
		itemBox2.toggled = false;
		itemBox3.toggled = false;
		itemBox4.toggled = false;
		itemBox5.toggled = false;
		itemBox6.toggled = false;
		itemBox7.toggled = false;
		itemBox8.toggled = false;
		itemBox9.toggled = false;
	}
    }
}
