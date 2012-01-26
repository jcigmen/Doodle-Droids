package org.nullsys.androidgames.doodledroids.screen;

import java.util.ArrayList;

import android.util.Log;

import aurelienribon.tweenengine.equations.Linear;

import javax.microedition.khronos.opengles.GL10;

import org.nullsys.androidgames.doodledroids.Assets;
import org.nullsys.androidgames.doodledroids.DoodleDroid;
import org.nullsys.androidgames.doodledroids.Settings;
import org.nullsys.androidgames.doodledroids.item.EquippableItem;
import org.nullsys.androidgames.doodledroids.item.InventoryItem;
import org.nullsys.androidgames.doodledroids.item.ItemFactory;
import org.nullsys.androidgames.framework.Game;
import org.nullsys.androidgames.framework.Input.TouchEvent;
import org.nullsys.androidgames.framework.Screen;
import org.nullsys.androidgames.framework.display.Button;
import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.Sprite;
import org.nullsys.androidgames.framework.display.TextureRegion;
import org.nullsys.androidgames.framework.display.ToggleButton;
import org.nullsys.androidgames.framework.display.text.Text;
import org.nullsys.androidgames.framework.display.text.Text.TextRegistration;
import org.nullsys.androidgames.framework.impl.GLScreen;

/**
 * A Presentable Screen for the Store Module. Here, the users can select three different tabs; Items, wherein items can be purchased, Apparels, wherein apparels can be bought, and
 * an Inventory tab, where the user may sell items or apparels.
 */
public class StoreScreen extends GLScreen {

    /** Constant value representing Items for sale as the selected tab. */
    private static final int ITEMS = 0;

    /** Constant value representing Apparels for sale as the selected tab. */
    private static final int APPAREL = 1;

    /** Constant value representing the Inventory as the selected tab. */
    private static final int INVENTORY = 2;

    /** Collection of DisplayObjects that pertains to the Items tab. */
    private ArrayList<DisplayObject> itemTabDisplays = new ArrayList<DisplayObject>();

    /** Collection of DisplayObjects that pertains to the Apparels tab. */
    private ArrayList<DisplayObject> apparelTabDisplays = new ArrayList<DisplayObject>();

    /** Collection of DisplayObjects that pertains to the Inventory tab. */
    private ArrayList<DisplayObject> inventoryTabDisplays = new ArrayList<DisplayObject>();

    /** List of items for sale. */
    private ArrayList<InventoryItem> itemsForSale = new ArrayList<InventoryItem>();

    /** List of apparels for sale. */
    private ArrayList<EquippableItem> apparelsForSale = new ArrayList<EquippableItem>();

    /** Price label for each item in the box. */
    private ArrayList<Text> itemPrices = new ArrayList<Text>();

    /** Collection for quick manipulation of item boxes. */
    private ArrayList<ToggleButton> itemBoxes = new ArrayList<ToggleButton>();

    /** Collection for quick manipulation of inventory boxes. */
    private ArrayList<ToggleButton> invBoxes = new ArrayList<ToggleButton>();

    /** The player's droid. */
    private DoodleDroid droid;

    /** Numeric representation of the currently selected tab. */
    private int currentTab = ITEMS;

    /** The screen to return to if the back button is pressed. */
    private Screen previousScreen;

    /** The currently selected item box's index. */
    private int currentlySelectedItemBoxIndex = 20;

    /** The currently selected inventory item box's index. */
    private int currentlySelectedInvBoxIndex = 30;

    /** Set index for the items on sale to display. */
    private int itemsIndex = 0;

    /** Set index for the items in inventory to display. */
    private int usablesIndex = 0;

    /** Set index for the equips in inventory to display. */
    private int equipsIndex = 0;

    ////////////////////////
    //    DISPLAYABLES    //
    ////////////////////////

    /** The large panel that holds the tabs and other displayables. */
    private Sprite panel;

    /** Back navigation. */
    private Button back;

    /** Fixed label for the droills. */
    private Text droills;

    /** Actual value of player's droills. */
    private Text droillsValue;

    /** Toggle Button display for the selected Apparels tab. */
    private Sprite apparelsTabSelected;

    /** Toggle Button display for the unselected Apparels tab. */
    private Sprite apparelsTabUnselected;

    /** Toggle Button display for the selected Inventorytab. */
    private Sprite inventoryTabSelected;

    /** Toggle Button display for the unelected Inventorytab. */
    private Sprite inventoryTabUnselected;

    /** Sprite display for the selected Items tab. */
    private Sprite itemsTabSelected;

    /** Sprite display for the unselected Items tab. */
    private Sprite itemsTabUnselected;

    /** The panel for the draggable context menu that describes the selected item. */
    private Sprite contextMenu;

    /** Close button for the context menu. */
    private Button contextMenuClose;

    /** The name of the currently selected item. */
    private Text contextMenuItemName;

    /** Description for the currently selected item. Not used for apparels. */
    private Text contextMenuItemDescription;

    /** Buy button for both Items and Apparels tab context menu. */
    private Button contextMenuBuy;

    /** Sell button for the inventory context menu. */
    private Button contextMenuSell;

    /** Box holder for the items. */
    private ToggleButton itemBox1, itemBox2, itemBox3, itemBox4, itemBox5, itemBox6, itemBox7, itemBox8, itemBox9, itemBox10, itemBox11, itemBox12, itemBox13, itemBox14, itemBox15, itemBox16, itemBox17, itemBox18;

    /** Single recycable Text for the item prices to avoid too much GC. */
    private Text itemPrice;

    /** Left navigation button for the Items tab. */
    private Button itemLeftButton;

    /** Right navigation button for the Items tab. */
    private Button itemRightButton;

    ////////////////////////////
    // INVENTORY TAB DISPLAYS //
    ////////////////////////////

    /** Box holder for the inventory items. */
    private ToggleButton invBox1, invBox2, invBox3, invBox4, invBox5, invBox6, invBox7, invBox8, invBox9, invBox10, invBox11, invBox12, invBox13, invBox14, invBox15, invBox16, invBox17, invBox18, invBox19, invBox20, invBox21, invBox22, invBox23, invBox24;

    /** Left navigation button for usable items. */
    private Button usablesLeftButton;

    /** Right navigation button for usable items. */
    private Button usablesRightButton;

    /** Left navigation button for the equipment items. */
    private Button equipLeftButton;

    /** Right navigation button for the equipment items. */
    private Button equipRightButton;

    public StoreScreen(Game game, Screen previousScreen) {
	super(game);

	droid = game.getDroid();

	this.previousScreen = previousScreen;

	///////////////////////////////////
	// INSTANTIATE GENERAL DISPLAYS  //
	///////////////////////////////////

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

	apparelsTabSelected = new Sprite(new TextureRegion(Assets.dashboardTabs, 165, 285, 147, 59));
	apparelsTabSelected.x = 167;
	apparelsTabSelected.y = 253;
	apparelsTabSelected.touchCallback = this;
	addChild(apparelsTabSelected);

	apparelsTabUnselected = new Sprite(new TextureRegion(Assets.dashboardTabs, 165, 355, 147, 59));
	apparelsTabUnselected.x = 167;
	apparelsTabUnselected.y = 253;
	apparelsTabUnselected.touchCallback = this;
	addChild(apparelsTabUnselected);

	inventoryTabSelected = new Sprite(new TextureRegion(Assets.dashboardTabs, 484, 459, 147, 59));
	inventoryTabSelected.x = 310;
	inventoryTabSelected.y = 253;
	inventoryTabSelected.touchCallback = this;
	addChild(inventoryTabSelected);

	inventoryTabUnselected = new Sprite(new TextureRegion(Assets.dashboardTabs, 484, 525, 147, 59));
	inventoryTabUnselected.x = 310;
	inventoryTabUnselected.y = 253;
	inventoryTabUnselected.touchCallback = this;
	addChild(inventoryTabUnselected);

	panel = new Sprite(new TextureRegion(Assets.dashboardTabs, 0, 0, 475, 270));
	panel.x = 2;
	panel.y = 4;
	addChild(panel);

	droills = new Text(Assets.poohBlackFont, "Droills:");
	droills.x = 272;
	droills.y = 38f;
	droills.scaleX = .5f;
	droills.scaleY = .5f;
	addChild(droills);

	droillsValue = new Text(Assets.droillarsFont, "" + droid.droills);
	droillsValue.x = 330;
	droillsValue.y = 38f;
	addChild(droillsValue);

	itemBox1 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox1.x = 67;
	itemBox1.y = 188;
	itemBox1.downstate.x = 67;
	itemBox1.downstate.y = 188;
	itemBox1.touchCallback = this;
	itemBoxes.add(itemBox1);
	addChild(itemBox1);

	itemBox2 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox2.x = 127;
	itemBox2.y = 188;
	itemBox2.downstate.x = 127;
	itemBox2.downstate.y = 188;
	itemBox2.touchCallback = this;
	itemBoxes.add(itemBox2);
	addChild(itemBox2);

	itemBox3 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox3.x = 187;
	itemBox3.y = 188;
	itemBox3.downstate.x = 187;
	itemBox3.downstate.y = 188;
	itemBox3.touchCallback = this;
	itemBoxes.add(itemBox3);
	addChild(itemBox3);

	itemBox4 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox4.x = 247;
	itemBox4.y = 188;
	itemBox4.downstate.x = 247;
	itemBox4.downstate.y = 188;
	itemBox4.touchCallback = this;
	itemBoxes.add(itemBox4);
	addChild(itemBox4);

	itemBox5 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox5.x = 307;
	itemBox5.y = 188;
	itemBox5.downstate.x = 307;
	itemBox5.downstate.y = 188;
	itemBox5.touchCallback = this;
	itemBoxes.add(itemBox5);
	addChild(itemBox5);

	itemBox6 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox6.x = 367;
	itemBox6.y = 188;
	itemBox6.downstate.x = 367;
	itemBox6.downstate.y = 188;
	itemBox6.touchCallback = this;
	itemBoxes.add(itemBox6);
	addChild(itemBox6);

	itemBox7 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox7.x = 67;
	itemBox7.y = 128;
	itemBox7.downstate.x = 67;
	itemBox7.downstate.y = 128;
	itemBox7.touchCallback = this;
	itemBoxes.add(itemBox7);
	addChild(itemBox7);

	itemBox8 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox8.x = 127;
	itemBox8.y = 128;
	itemBox8.downstate.x = 127;
	itemBox8.downstate.y = 128;
	itemBox8.touchCallback = this;
	itemBoxes.add(itemBox8);
	addChild(itemBox8);

	itemBox9 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox9.x = 187;
	itemBox9.y = 128;
	itemBox9.downstate.x = 187;
	itemBox9.downstate.y = 128;
	itemBox9.touchCallback = this;
	itemBoxes.add(itemBox9);
	addChild(itemBox9);

	itemBox10 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox10.x = 247;
	itemBox10.y = 128;
	itemBox10.downstate.x = 247;
	itemBox10.downstate.y = 128;
	itemBox10.touchCallback = this;
	itemBoxes.add(itemBox10);
	addChild(itemBox10);

	itemBox11 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox11.x = 307;
	itemBox11.y = 128;
	itemBox11.downstate.x = 307;
	itemBox11.downstate.y = 128;
	itemBox11.touchCallback = this;
	itemBoxes.add(itemBox11);
	addChild(itemBox11);

	itemBox12 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox12.x = 367;
	itemBox12.y = 128;
	itemBox12.downstate.x = 367;
	itemBox12.downstate.y = 128;
	itemBox12.touchCallback = this;
	itemBoxes.add(itemBox12);
	addChild(itemBox12);

	itemBox13 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox13.x = 67;
	itemBox13.y = 67;
	itemBox13.downstate.x = 67;
	itemBox13.downstate.y = 67;
	itemBox13.touchCallback = this;
	itemBoxes.add(itemBox13);
	addChild(itemBox13);

	itemBox14 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox14.x = 127;
	itemBox14.y = 67;
	itemBox14.downstate.x = 127;
	itemBox14.downstate.y = 67;
	itemBox14.touchCallback = this;
	itemBoxes.add(itemBox14);
	addChild(itemBox14);

	itemBox15 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox15.x = 187;
	itemBox15.y = 67;
	itemBox15.downstate.x = 187;
	itemBox15.downstate.y = 67;
	itemBox15.touchCallback = this;
	itemBoxes.add(itemBox15);
	addChild(itemBox15);

	itemBox16 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox16.x = 247;
	itemBox16.y = 67;
	itemBox16.downstate.x = 247;
	itemBox16.downstate.y = 67;
	itemBox16.touchCallback = this;
	itemBoxes.add(itemBox16);
	addChild(itemBox16);

	itemBox17 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox17.x = 307;
	itemBox17.y = 67;
	itemBox17.downstate.x = 307;
	itemBox17.downstate.y = 67;
	itemBox17.touchCallback = this;
	itemBoxes.add(itemBox17);
	addChild(itemBox17);

	itemBox18 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	itemBox18.x = 367;
	itemBox18.y = 67;
	itemBox18.downstate.x = 367;
	itemBox18.downstate.y = 67;
	itemBox18.touchCallback = this;
	itemBoxes.add(itemBox18);
	addChild(itemBox18);

	itemLeftButton = new Button(new TextureRegion(Assets.dashboardTabs, 789, 18, 41, 39), new TextureRegion(Assets.dashboardTabs, 833, 22, 35, 33));
	itemLeftButton.x = 19;
	itemLeftButton.y = 131;
	itemLeftButton.downstate.x = 23;
	itemLeftButton.downstate.y = 135;
	itemLeftButton.touchCallback = this;
	itemTabDisplays.add(itemLeftButton);
	addChild(itemLeftButton);

	back = new Button(new TextureRegion(Assets.mainMenu, 896, 0, 55, 53), new TextureRegion(Assets.mainMenu, 903, 54, 41, 39));
	back.x = 7;
	back.y = 6;
	back.downstate.x = 14;
	back.downstate.y = 13;
	back.touchCallback = this;
	back.tapSound = Assets.cancel1;
	addChild(back);

	itemRightButton = new Button(new TextureRegion(Assets.dashboardTabs, 875, 19, 41, 39), new TextureRegion(Assets.dashboardTabs, 921, 22, 35, 33));
	itemRightButton.x = 421;
	itemRightButton.y = 131;
	itemRightButton.downstate.x = 425;
	itemRightButton.downstate.y = 135;
	itemRightButton.touchCallback = this;
	itemTabDisplays.add(itemRightButton);
	addChild(itemRightButton);

	/////////////////////////////////////////
	// INSTANTIATE IINVENTORY TAB DISPLAYS //
	/////////////////////////////////////////

	usablesLeftButton = new Button(new TextureRegion(Assets.dashboardTabs, 789, 18, 41, 39), new TextureRegion(Assets.dashboardTabs, 833, 22, 35, 33));
	usablesLeftButton.x = 19;
	usablesLeftButton.y = 194;
	usablesLeftButton.downstate.x = 23;
	usablesLeftButton.downstate.y = 198;
	usablesLeftButton.touchCallback = this;
	inventoryTabDisplays.add(usablesLeftButton);
	addChild(usablesLeftButton);

	usablesRightButton = new Button(new TextureRegion(Assets.dashboardTabs, 875, 19, 41, 39), new TextureRegion(Assets.dashboardTabs, 921, 22, 35, 33));
	usablesRightButton.x = 421;
	usablesRightButton.y = 194;
	usablesRightButton.downstate.x = 425;
	usablesRightButton.downstate.y = 198;
	usablesRightButton.touchCallback = this;
	inventoryTabDisplays.add(usablesRightButton);
	addChild(usablesRightButton);

	equipLeftButton = new Button(new TextureRegion(Assets.dashboardTabs, 789, 18, 41, 39), new TextureRegion(Assets.dashboardTabs, 833, 22, 35, 33));
	equipLeftButton.x = 19;
	equipLeftButton.y = 91;
	equipLeftButton.downstate.x = 23;
	equipLeftButton.downstate.y = 95;
	equipLeftButton.touchCallback = this;
	inventoryTabDisplays.add(equipLeftButton);
	addChild(equipLeftButton);

	equipRightButton = new Button(new TextureRegion(Assets.dashboardTabs, 875, 19, 41, 39), new TextureRegion(Assets.dashboardTabs, 921, 22, 35, 33));
	equipRightButton.x = 421;
	equipRightButton.y = 91;
	equipRightButton.downstate.x = 425;
	equipRightButton.downstate.y = 95;
	equipRightButton.touchCallback = this;
	inventoryTabDisplays.add(equipRightButton);
	addChild(equipRightButton);

	invBox1 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox1.x = 67;
	invBox1.y = 215;
	invBox1.downstate.x = 67;
	invBox1.downstate.y = 215;
	invBox1.touchCallback = this;
	invBoxes.add(invBox1);
	addChild(invBox1);

	invBox2 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox2.x = 127;
	invBox2.y = 215;
	invBox2.downstate.x = 127;
	invBox2.downstate.y = 215;
	invBox2.touchCallback = this;
	invBoxes.add(invBox2);
	addChild(invBox2);

	invBox3 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox3.x = 187;
	invBox3.y = 215;
	invBox3.downstate.x = 187;
	invBox3.downstate.y = 215;
	invBox3.touchCallback = this;
	invBoxes.add(invBox3);
	addChild(invBox3);

	invBox4 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox4.x = 247;
	invBox4.y = 215;
	invBox4.downstate.x = 247;
	invBox4.downstate.y = 215;
	invBox4.touchCallback = this;
	invBoxes.add(invBox4);
	addChild(invBox4);

	invBox5 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox5.x = 307;
	invBox5.y = 215;
	invBox5.downstate.x = 307;
	invBox5.downstate.y = 215;
	invBox5.touchCallback = this;
	invBoxes.add(invBox5);
	addChild(invBox5);

	invBox6 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox6.x = 367;
	invBox6.y = 215;
	invBox6.downstate.x = 367;
	invBox6.downstate.y = 215;
	invBox6.touchCallback = this;
	invBoxes.add(invBox6);
	addChild(invBox6);

	invBox7 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox7.x = 67;
	invBox7.y = 164;
	invBox7.downstate.x = 67;
	invBox7.downstate.y = 164;
	invBox7.touchCallback = this;
	invBoxes.add(invBox7);
	addChild(invBox7);

	invBox8 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox8.x = 127;
	invBox8.y = 164;
	invBox8.downstate.x = 127;
	invBox8.downstate.y = 164;
	invBox8.touchCallback = this;
	invBoxes.add(invBox8);
	addChild(invBox8);

	invBox9 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox9.x = 187;
	invBox9.y = 164;
	invBox9.downstate.x = 187;
	invBox9.downstate.y = 164;
	invBox9.touchCallback = this;
	invBoxes.add(invBox9);
	addChild(invBox9);

	invBox10 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox10.x = 247;
	invBox10.y = 164;
	invBox10.downstate.x = 247;
	invBox10.downstate.y = 164;
	invBox10.touchCallback = this;
	invBoxes.add(invBox10);
	addChild(invBox10);

	invBox11 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox11.x = 307;
	invBox11.y = 164;
	invBox11.downstate.x = 307;
	invBox11.downstate.y = 164;
	invBox11.touchCallback = this;
	invBoxes.add(invBox11);
	addChild(invBox11);

	invBox12 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox12.x = 367;
	invBox12.y = 164;
	invBox12.downstate.x = 367;
	invBox12.downstate.y = 164;
	invBox12.touchCallback = this;
	invBoxes.add(invBox12);
	addChild(invBox12);

	invBox13 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox13.x = 67;
	invBox13.y = 112;
	invBox13.downstate.x = 67;
	invBox13.downstate.y = 112;
	invBox13.touchCallback = this;
	invBoxes.add(invBox13);
	addChild(invBox13);

	invBox14 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox14.x = 127;
	invBox14.y = 112;
	invBox14.downstate.x = 127;
	invBox14.downstate.y = 112;
	invBox14.touchCallback = this;
	invBoxes.add(invBox14);
	addChild(invBox14);

	invBox15 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox15.x = 187;
	invBox15.y = 112;
	invBox15.downstate.x = 187;
	invBox15.downstate.y = 112;
	invBox15.touchCallback = this;
	invBoxes.add(invBox15);
	addChild(invBox15);

	invBox16 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox16.x = 247;
	invBox16.y = 112;
	invBox16.downstate.x = 247;
	invBox16.downstate.y = 112;
	invBox16.touchCallback = this;
	invBoxes.add(invBox16);
	addChild(invBox16);

	invBox17 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox17.x = 307;
	invBox17.y = 112;
	invBox17.downstate.x = 307;
	invBox17.downstate.y = 112;
	invBox17.touchCallback = this;
	invBoxes.add(invBox17);
	addChild(invBox17);

	invBox18 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox18.x = 367;
	invBox18.y = 112;
	invBox18.downstate.x = 367;
	invBox18.downstate.y = 112;
	invBox18.touchCallback = this;
	invBoxes.add(invBox18);
	addChild(invBox18);

	invBox19 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox19.x = 67;
	invBox19.y = 61;
	invBox19.downstate.x = 67;
	invBox19.downstate.y = 61;
	invBox19.touchCallback = this;
	invBoxes.add(invBox19);
	addChild(invBox19);

	invBox20 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox20.x = 127;
	invBox20.y = 61;
	invBox20.downstate.x = 127;
	invBox20.downstate.y = 61;
	invBox20.touchCallback = this;
	invBoxes.add(invBox20);
	addChild(invBox20);

	invBox21 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox21.x = 187;
	invBox21.y = 61;
	invBox21.downstate.x = 187;
	invBox21.downstate.y = 61;
	invBox21.touchCallback = this;
	invBoxes.add(invBox21);
	addChild(invBox21);

	invBox22 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox22.x = 247;
	invBox22.y = 61;
	invBox22.downstate.x = 247;
	invBox22.downstate.y = 61;
	invBox22.touchCallback = this;
	invBoxes.add(invBox22);
	addChild(invBox22);

	invBox23 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox23.x = 307;
	invBox23.y = 61;
	invBox23.downstate.x = 307;
	invBox23.downstate.y = 61;
	invBox23.touchCallback = this;
	invBoxes.add(invBox23);
	addChild(invBox23);

	invBox24 = new ToggleButton(new TextureRegion(Assets.dashboardTabs, 698, 79, 46, 47), new TextureRegion(Assets.dashboardTabs, 757, 79, 46, 47));
	invBox24.x = 367;
	invBox24.y = 61;
	invBox24.downstate.x = 367;
	invBox24.downstate.y = 61;
	invBox24.touchCallback = this;
	invBoxes.add(invBox24);
	addChild(invBox24);

	// The pop-up window should be added last.
	contextMenu = new Sprite(new TextureRegion(Assets.dashboardTabs, 486, 270, 165, 183));
	contextMenu.draggable = true;
	contextMenu.touchCallback = this;
	addChild(contextMenu);

	contextMenuClose = new Button(new TextureRegion(Assets.dashboardTabs, 797, 148, 12, 13), new TextureRegion(Assets.dashboardTabs, 810, 148, 12, 13));
	contextMenuClose.touchCallback = this;
	addChild(contextMenuClose);

	contextMenuItemName = new Text(Assets.poohBlackFont, "");
	contextMenuItemName.scaleX = .8f;
	contextMenuItemName.scaleY = .8f;
	contextMenuItemName.registration = TextRegistration.CENTER;
	addChild(contextMenuItemName);

	contextMenuItemDescription = new Text(Assets.poohBlackFont, "");
	contextMenuItemDescription.scaleX = .5f;
	contextMenuItemDescription.scaleY = .5f;
	contextMenuItemDescription.registration = TextRegistration.CENTER;
	addChild(contextMenuItemDescription);

	contextMenuBuy = new Button(new TextureRegion(Assets.dashboardTabs, 695, 223, 102, 31), new TextureRegion(Assets.dashboardTabs, 695, 256, 102, 31));
	contextMenuBuy.touchCallback = this;
	addChild(contextMenuBuy);

	contextMenuSell = new Button(new TextureRegion(Assets.dashboardTabs, 695, 288, 102, 31), new TextureRegion(Assets.dashboardTabs, 695, 322, 102, 31));
	contextMenuSell.touchCallback = this;
	addChild(contextMenuSell);

	// Finishing touches.
	initItemsForSale();
	initApparelsForSale();
	showItemsTab();
	setContextMenuVisibility(false);
	setInventoryBoxesVisibility(false);
    }

    @Override
    public void backPressed() {
	for (DisplayObject element : previousScreen.displayObjects)
	    element.alpha = 1f;
	game.setScreen(previousScreen);
	if (Settings.musicEnabled) {
	    Assets.worldBGM.play();
	    Assets.shopBGM.pause();
	}
    }

    @Override
    public void dispose() {
	itemTabDisplays.clear();
    }

    @Override
    public void menuPressed() {
	// TODO Auto-generated method stub

    }

    @Override
    public void onTouchEvent(DisplayObject source, TouchEvent event) {
	if (event.type == TouchEvent.TOUCH_DOWN)
	    if (source.equals(itemBox1))
		deselectOtherItemBox(1);
	    else if (source.equals(itemBox2))
		deselectOtherItemBox(2);
	    else if (source.equals(itemBox3))
		deselectOtherItemBox(3);
	    else if (source.equals(itemBox4))
		deselectOtherItemBox(4);
	    else if (source.equals(itemBox5))
		deselectOtherItemBox(5);
	    else if (source.equals(itemBox6))
		deselectOtherItemBox(6);
	    else if (source.equals(itemBox7))
		deselectOtherItemBox(7);
	    else if (source.equals(itemBox8))
		deselectOtherItemBox(8);
	    else if (source.equals(itemBox9))
		deselectOtherItemBox(9);
	    else if (source.equals(itemBox10))
		deselectOtherItemBox(10);
	    else if (source.equals(itemBox11))
		deselectOtherItemBox(11);
	    else if (source.equals(itemBox12))
		deselectOtherItemBox(12);
	    else if (source.equals(itemBox13))
		deselectOtherItemBox(13);
	    else if (source.equals(itemBox14))
		deselectOtherItemBox(14);
	    else if (source.equals(itemBox15))
		deselectOtherItemBox(15);
	    else if (source.equals(itemBox16))
		deselectOtherItemBox(16);
	    else if (source.equals(itemBox17))
		deselectOtherItemBox(17);
	    else if (source.equals(itemBox18))
		deselectOtherItemBox(18);
	    else if (source.equals(invBox1))
		deselectOtherInventoryBox(1);
	    else if (source.equals(invBox2))
		deselectOtherInventoryBox(2);
	    else if (source.equals(invBox3))
		deselectOtherInventoryBox(3);
	    else if (source.equals(invBox4))
		deselectOtherInventoryBox(4);
	    else if (source.equals(invBox5))
		deselectOtherInventoryBox(5);
	    else if (source.equals(invBox6))
		deselectOtherInventoryBox(6);
	    else if (source.equals(invBox7))
		deselectOtherInventoryBox(7);
	    else if (source.equals(invBox8))
		deselectOtherInventoryBox(8);
	    else if (source.equals(invBox9))
		deselectOtherInventoryBox(9);
	    else if (source.equals(invBox10))
		deselectOtherInventoryBox(10);
	    else if (source.equals(invBox11))
		deselectOtherInventoryBox(11);
	    else if (source.equals(invBox12))
		deselectOtherInventoryBox(12);
	    else if (source.equals(invBox13))
		deselectOtherInventoryBox(13);
	    else if (source.equals(invBox14))
		deselectOtherInventoryBox(14);
	    else if (source.equals(invBox15))
		deselectOtherInventoryBox(15);
	    else if (source.equals(invBox16))
		deselectOtherInventoryBox(16);
	    else if (source.equals(invBox17))
		deselectOtherInventoryBox(17);
	    else if (source.equals(invBox18))
		deselectOtherInventoryBox(18);
	    else if (source.equals(invBox19))
		deselectOtherInventoryBox(19);
	    else if (source.equals(invBox20))
		deselectOtherInventoryBox(20);
	    else if (source.equals(invBox21))
		deselectOtherInventoryBox(21);
	    else if (source.equals(invBox22))
		deselectOtherInventoryBox(22);
	    else if (source.equals(invBox23))
		deselectOtherInventoryBox(23);
	    else if (source.equals(invBox24))
		deselectOtherInventoryBox(24);
	    else if (source.equals(contextMenu))
		setContextMenuAlpha(1f);
	if (event.type == TouchEvent.TOUCH_TAP)
	    if (source.equals(itemsTabUnselected)) {
		showItemsTab();
		currentTab = ITEMS;
		if (Settings.soundEnabled)
		    Assets.tap.play(1);
	    } else if (source.equals(apparelsTabUnselected)) {
		showApparelsTab();
		currentTab = APPAREL;
		if (Settings.soundEnabled)
		    Assets.tap.play(1);
	    } else if (source.equals(inventoryTabUnselected)) {
		showInventoryTab();
		currentTab = INVENTORY;
		if (Settings.soundEnabled)
		    Assets.tap.play(1);
	    } else if (source.equals(contextMenuClose)) {
		setContextMenuVisibility(!contextMenu.visible);
		deselectOtherItemBox(20);
		deselectOtherInventoryBox(30);
	    } else if (source.equals(usablesLeftButton))
		usablesIndex--;
	    else if (source.equals(usablesRightButton))
		usablesIndex++;
	    else if (source.equals(equipLeftButton))
		equipsIndex--;
	    else if (source.equals(equipRightButton))
		equipsIndex++;
	    else if (source.equals(itemLeftButton))
		itemsIndex--;
	    else if (source.equals(itemRightButton))
		itemsIndex++;
	    else if (source.equals(back))
		backPressed();
	    else if (source.equals(contextMenuBuy)) {
		if (currentTab == ITEMS && droid.droills >= itemsForSale.get(18 * itemsIndex + currentlySelectedItemBoxIndex).storeValue) {
		    droid.usableItems.add(itemsForSale.get(18 * itemsIndex + currentlySelectedItemBoxIndex));
		    droid.droills -= itemsForSale.get(18 * itemsIndex + currentlySelectedItemBoxIndex).storeValue;
		    //game.setScreen(new PromptScreen(game, this, PromptScreen.OK_PROMPT, "Purchase\nSuccessful!"));
		    if (Settings.soundEnabled)
			Assets.buySell.play(1);
		} else if (currentTab == ITEMS && droid.droills < itemsForSale.get(18 * itemsIndex + currentlySelectedItemBoxIndex).storeValue)
		    game.setScreen(new PromptScreen(game, this, PromptScreen.OK_PROMPT, "Insufficient\nDroills!"));
		else if (currentTab == APPAREL && droid.droills >= apparelsForSale.get(18 * itemsIndex + currentlySelectedItemBoxIndex).getStoreValue()) {
		    droid.equipment.add(apparelsForSale.get(18 * itemsIndex + currentlySelectedItemBoxIndex));
		    droid.droills -= apparelsForSale.get(18 * itemsIndex + currentlySelectedItemBoxIndex).getStoreValue();
		    //game.setScreen(new PromptScreen(game, this, PromptScreen.OK_PROMPT, "Purchase\nSuccessful!"));
		    if (Settings.soundEnabled)
			Assets.buySell.play(1);
		} else if (currentTab == APPAREL && droid.droills < apparelsForSale.get(18 * itemsIndex + currentlySelectedItemBoxIndex).getStoreValue())
		    game.setScreen(new PromptScreen(game, this, PromptScreen.OK_PROMPT, "Insufficient\nDroills!"));
	    } else if (source.equals(contextMenuSell))
		droid.droills += currentlySelectedInvBoxIndex < 12 ? droid.usableItems.remove(12 * usablesIndex + currentlySelectedInvBoxIndex).storeValue : droid.equipment.remove(12 * equipsIndex + currentlySelectedInvBoxIndex - 12).getStoreValue();

	if (event.type == TouchEvent.TOUCH_UP)
	    if (source.equals(contextMenu))
		setContextMenuAlpha(.5f);
    }

    @Override
    public void pause() {
	Assets.shopBGM.pause();
    }

    @Override
    public void present(float deltaTime) {
	GL10 gl = glGraphics.getGL();
	gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	camera.setViewportAndMatrices();

	gl.glEnable(GL10.GL_TEXTURE_2D);
	gl.glEnable(GL10.GL_BLEND);
	gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

	for (int index = 0; index < displayObjects.size() - 6; index++)
	    if (displayObjects.get(index).visible) {
		gl.glColor4f(displayObjects.get(index).r, displayObjects.get(index).g, displayObjects.get(index).b, displayObjects.get(index).alpha);
		displayObjects.get(index).render(batcher);
	    }

	if (currentTab == ITEMS) {
	    for (int index = 18 * itemsIndex, rowIndex = 0, colIndex = 0; index < itemsForSale.size() && index < 18 * itemsIndex + 18; index++) {
		float widthRatio = itemsForSale.get(index).getWidth() * .5f / 2;
		float heightRatio = itemsForSale.get(index).getHeight() * .5f / 2;
		itemsForSale.get(index).x = 90 + rowIndex * 60 - widthRatio;
		itemsForSale.get(index).y = 177 - colIndex * 60 + heightRatio;
		itemsForSale.get(index).scaleX = .5f;
		itemsForSale.get(index).scaleY = .5f;
		itemsForSale.get(index).render(batcher);
		if (rowIndex > 0 && rowIndex % 5 == 0) {
		    rowIndex = 0;
		    colIndex++;
		} else
		    rowIndex++;
	    }
	    for (int index = 0, rowIndex = 0, colIndex = 0; index < itemPrices.size(); index++) {
		itemPrices.get(index).x = 89 + rowIndex * 60;
		itemPrices.get(index).y = 195 - colIndex * 60;
		itemPrices.get(index).render(batcher);
		if (rowIndex > 0 && rowIndex % 5 == 0) {
		    rowIndex = 0;
		    colIndex++;
		} else
		    rowIndex++;
	    }
	} else if (currentTab == APPAREL) {
	    for (int index = 18 * itemsIndex, rowIndex = 0, colIndex = 0; index < apparelsForSale.size() && index < 18 * itemsIndex + 18; index++) {
		float widthRatio = apparelsForSale.get(index).getWidth() * .5f / 2;
		float heightRatio = apparelsForSale.get(index).getHeight() * .5f / 2;
		apparelsForSale.get(index).setX(90 + rowIndex * 60 - widthRatio);
		apparelsForSale.get(index).setY(187 - colIndex * 60 + heightRatio);
		apparelsForSale.get(index).setScaleX(.5f);
		apparelsForSale.get(index).setScaleY(.5f);
		apparelsForSale.get(index).render(batcher);
		if (rowIndex > 0 && rowIndex % 5 == 0) {
		    rowIndex = 0;
		    colIndex++;
		} else
		    rowIndex++;
	    }
	    for (int index = 0, rowIndex = 0, colIndex = 0; index < itemPrices.size(); index++) {
		itemPrices.get(index).x = 89 + rowIndex * 60;
		itemPrices.get(index).y = 195 - colIndex * 60;
		itemPrices.get(index).render(batcher);
		if (rowIndex > 0 && rowIndex % 5 == 0) {
		    rowIndex = 0;
		    colIndex++;
		} else
		    rowIndex++;
	    }
	} else if (currentTab == INVENTORY) {
	    int priceIndex = 0;
	    // Render the inventory usables
	    for (int index = 12 * usablesIndex, rowIndex = 0, colIndex = 0; index < droid.usableItems.size() && index < 12 * usablesIndex + 12; index++, priceIndex++) {
		float widthRatio = droid.usableItems.get(index).width * .5f / 2;
		float heightRatio = droid.usableItems.get(index).height * .5f / 2;
		droid.usableItems.get(index).x = 90 + rowIndex * 60 - widthRatio;
		droid.usableItems.get(index).y = 201 - colIndex * 51 + heightRatio;
		droid.usableItems.get(index).scaleX = .5f;
		droid.usableItems.get(index).scaleY = .5f;
		droid.usableItems.get(index).render(batcher);
		itemPrices.get(priceIndex).x = 89 + rowIndex * 60;
		itemPrices.get(priceIndex).y = 222 - colIndex * 51;
		itemPrices.get(priceIndex).render(batcher);
		if (rowIndex > 0 && rowIndex % 5 == 0) {
		    rowIndex = 0;
		    colIndex++;
		} else
		    rowIndex++;
	    }
	    // Render the inventory equips
	    for (int index = 12 * equipsIndex, rowIndex = 0, colIndex = 0; index < droid.equipment.size() && index < 12 * equipsIndex + 12; index++) {
		float widthRatio = droid.equipment.get(index).getWidth() * .5f / 2;
		float heightRatio = droid.equipment.get(index).getHeight() * .5f / 2;
		droid.equipment.get(index).setX(90 + rowIndex * 60 - widthRatio);
		droid.equipment.get(index).setY(106 - colIndex * 51 + heightRatio);
		droid.equipment.get(index).setScaleX(.5f);
		droid.equipment.get(index).setScaleY(.5f);
		droid.equipment.get(index).render(batcher);
		itemPrices.get(priceIndex).x = 89 + rowIndex * 60;
		itemPrices.get(priceIndex).y = 120 - colIndex * 51;
		itemPrices.get(priceIndex).render(batcher);
		if (rowIndex > 0 && rowIndex % 5 == 0) {
		    rowIndex = 0;
		    colIndex++;
		} else
		    rowIndex++;
	    }
	    // Render the prices
	    /*int index = 0;
	    for (int rowIndex = 0, colIndex = 0; index < itemPrices.size() && index < droid.usableItems.size() - usablesIndex * 12; index++) {
	    itemPrices.get(index).x = 89 + rowIndex * 60;
	    itemPrices.get(index).y = 222 - colIndex * 51;
	    itemPrices.get(index).render(batcher);
	    if (rowIndex > 0 && rowIndex % 5 == 0) {
	        rowIndex = 0;
	        colIndex++;
	    } else
	        rowIndex++;
	    }
	    for (int rowIndex = 1, colIndex = 0; index < itemPrices.size(); index++) {
	    itemPrices.get(index).x = 89 + rowIndex * 60;
	    itemPrices.get(index).y = 120 - colIndex * 51;
	    itemPrices.get(index).render(batcher);
	    if (rowIndex > 0 && rowIndex % 5 == 0) {
	        rowIndex = 0;
	        colIndex++;
	    } else
	        rowIndex++;
	    }*/
	}

	contextMenu.render(batcher);
	contextMenuClose.render(batcher);
	contextMenuItemName.render(batcher);
	//contextMenuItemDescription.render(batcher);
	contextMenuBuy.render(batcher);
	contextMenuSell.render(batcher);

	gl.glDisable(GL10.GL_BLEND);
    }

    @Override
    public void resume() {
	if (Settings.musicEnabled && !Assets.shopBGM.isPlaying())
	    Assets.shopBGM.play();
    }

    @Override
    public void update(float deltaTime) {
	// Re-allign the context menu components
	contextMenuClose.x = contextMenu.x + 123;
	contextMenuClose.y = contextMenu.y + 160;
	contextMenuClose.downstate.x = contextMenu.x + 123;
	contextMenuClose.downstate.y = contextMenu.y + 160;
	contextMenuItemName.x = contextMenu.x + 82;
	contextMenuItemName.y = contextMenu.y + 127 - 10;
	contextMenuItemDescription.x = contextMenu.x + 82;
	contextMenuItemDescription.y = contextMenu.y + 103;
	contextMenuBuy.x = contextMenu.x + 32;
	contextMenuBuy.y = contextMenu.y + 14;
	contextMenuBuy.downstate.x = contextMenu.x + 32;
	contextMenuBuy.downstate.y = contextMenu.y + 14;
	contextMenuSell.x = contextMenu.x + 32;
	contextMenuSell.y = contextMenu.y + 14;
	contextMenuSell.downstate.x = contextMenu.x + 32;
	contextMenuSell.downstate.y = contextMenu.y + 14;

	// Droills must be updated too.
	droillsValue.text = "" + droid.droills;

	// Only show the context menu if an item box is selected.
	setContextMenuVisibility(hasOneItemBoxSelected() || hasOneInventoryBoxSelected());

	if (currentTab == ITEMS) {
	    setInventoryBoxesVisibility(false);
	    setItemBoxesVisiblity(true);
	    try {
		contextMenuItemName.text = itemsForSale.get(18 * itemsIndex + currentlySelectedItemBoxIndex).name;
		contextMenuItemDescription.text = itemsForSale.get(18 * itemsIndex + currentlySelectedItemBoxIndex).description;
	    } catch (IndexOutOfBoundsException e) {
		setContextMenuVisibility(false);
	    }
	    itemPrices = new ArrayList<Text>(itemsForSale.size());
	    for (int index = 0; index < itemsForSale.size(); index++) {
		itemPrice = new Text(Assets.droillarsFont, "" + itemsForSale.get(index).storeValue);
		itemPrice.registration = TextRegistration.CENTER;
		itemPrice.scaleX = .65f;
		itemPrice.scaleY = .65f;
		itemPrices.add(index, itemPrice);
	    }

	    // Show/hide the left and right button for the Items.
	    itemLeftButton.visible = itemsIndex > 0;
	    itemRightButton.visible = itemsForSale.size() > 18 * (itemsIndex + 1);
	} else if (currentTab == APPAREL) {

	    setInventoryBoxesVisibility(false);
	    setItemBoxesVisiblity(true);
	    try {
		contextMenuItemName.text = apparelsForSale.get(18 * itemsIndex + currentlySelectedItemBoxIndex).getName();
		contextMenuItemDescription.text = "+" + apparelsForSale.get(18 * itemsIndex + currentlySelectedItemBoxIndex).getExpBonus() + " EXP\n+" + apparelsForSale.get(18 * itemsIndex + currentlySelectedItemBoxIndex).getEnergyBonus() + " Energy\n+"
			+ apparelsForSale.get(18 * itemsIndex + currentlySelectedItemBoxIndex).getHealthBonus() + " Health";
	    } catch (IndexOutOfBoundsException e) {
		setContextMenuVisibility(false);
	    }
	    itemPrices = new ArrayList<Text>(apparelsForSale.size());
	    for (int index = 0; index < apparelsForSale.size(); index++) {
		itemPrice = new Text(Assets.droillarsFont, "" + apparelsForSale.get(index).getStoreValue());
		itemPrice.registration = TextRegistration.CENTER;
		itemPrice.scaleX = .65f;
		itemPrice.scaleY = .65f;
		itemPrices.add(index, itemPrice);
	    }

	    // Show/hide the left and right button for the Items.
	    itemLeftButton.visible = itemsIndex > 0;
	    itemRightButton.visible = apparelsForSale.size() > 18 * (itemsIndex + 1);
	} else if (currentTab == INVENTORY) {
	    setInventoryBoxesVisibility(true);
	    setItemBoxesVisiblity(false);
	    try {
		if (currentlySelectedInvBoxIndex < 12) {
		    contextMenuItemName.text = droid.usableItems.get(12 * usablesIndex + currentlySelectedInvBoxIndex).name;
		    contextMenuItemDescription.text = droid.usableItems.get(18 * usablesIndex + currentlySelectedInvBoxIndex).description;
		} else {
		    contextMenuItemName.text = droid.equipment.get(12 * equipsIndex + currentlySelectedInvBoxIndex - 12).getName();
		    contextMenuItemDescription.text = "+" + droid.equipment.get(12 * equipsIndex + currentlySelectedInvBoxIndex - 12).getExpBonus() + " EXP\n+" + droid.equipment.get(12 * equipsIndex + currentlySelectedInvBoxIndex - 12).getEnergyBonus() + " Energy\n+"
			    + droid.equipment.get(12 * equipsIndex + currentlySelectedInvBoxIndex - 12).getHealthBonus() + " Health";
		}
	    } catch (IndexOutOfBoundsException e) {
		Log.d("TEST", "[StoreScreen] NPE @ update: " + currentlySelectedInvBoxIndex);
		setContextMenuVisibility(false);
	    }
	    itemPrices = new ArrayList<Text>(itemsForSale.size());
	    int itemPriceIndex = 0;
	    for (int index = usablesIndex * 12; index < (usablesIndex + 1) * 12 && index < droid.usableItems.size(); index++, itemPriceIndex++) {
		itemPrice = new Text(Assets.droillarsFont, "" + droid.usableItems.get(index).storeValue / 2);
		itemPrice.registration = TextRegistration.CENTER;
		itemPrice.scaleX = .65f;
		itemPrice.scaleY = .65f;
		itemPrices.add(itemPriceIndex, itemPrice);
	    }
	    for (int index = equipsIndex * 12; index < (equipsIndex + 1) * 12 && index < droid.equipment.size(); index++, itemPriceIndex++) {
		itemPrice = new Text(Assets.droillarsFont, "" + droid.equipment.get(index).getStoreValue() / 2);
		itemPrice.registration = TextRegistration.CENTER;
		itemPrice.scaleX = .65f;
		itemPrice.scaleY = .65f;
		itemPrices.add(itemPriceIndex, itemPrice);
	    }
	    // Show/hide the left and right buttons for Inventory usables
	    usablesLeftButton.visible = usablesIndex > 0;
	    usablesRightButton.visible = droid.usableItems.size() > 12 * (usablesIndex + 1);

	    // Show/hide the left and right buttons for Inventory equips
	    equipLeftButton.visible = equipsIndex > 0;
	    equipRightButton.visible = droid.equipment.size() > 12 * (equipsIndex + 1);
	}
	contextMenuBuy.visible = contextMenu.visible && (currentTab == ITEMS || currentTab == APPAREL);
	contextMenuSell.visible = !contextMenuBuy.visible && contextMenu.visible;
    }

    /** Deselect the other inventory item boxes when one is selected. */
    private void deselectOtherInventoryBox(int selectedInvBox) {
	currentlySelectedInvBoxIndex = selectedInvBox - 1;
	for (int invBoxIndex = 0; invBoxIndex < invBoxes.size(); invBoxIndex++)
	    if (selectedInvBox - 1 != invBoxIndex)
		invBoxes.get(invBoxIndex).toggled = false;
	moveContextMenu(selectedInvBox);
    }

    /** Deselect the other item boxes when one is selected. */
    private void deselectOtherItemBox(int selectedItemBoxIndex) {
	currentlySelectedItemBoxIndex = selectedItemBoxIndex - 1;
	for (int itemBoxIndex = 0; itemBoxIndex < itemBoxes.size(); itemBoxIndex++)
	    if (selectedItemBoxIndex - 1 != itemBoxIndex)
		itemBoxes.get(itemBoxIndex).toggled = false;
	moveContextMenu(selectedItemBoxIndex);
    }

    /** Check if an inventory box is selected. */
    private boolean hasOneInventoryBoxSelected() {
	boolean oneSelected = false;
	for (int invBoxIndex = 0; invBoxIndex < invBoxes.size(); invBoxIndex++)
	    if (invBoxes.get(invBoxIndex).toggled) {
		oneSelected = true;
		invBoxIndex = invBoxes.size();
	    }
	return oneSelected;
    }

    /** Check if an item box is selected. */
    private boolean hasOneItemBoxSelected() {
	boolean oneSelected = false;
	for (int itemBoxIndex = 0; itemBoxIndex < itemBoxes.size(); itemBoxIndex++)
	    if (itemBoxes.get(itemBoxIndex).toggled) {
		oneSelected = true;
		itemBoxIndex = itemBoxes.size();
	    }
	return oneSelected;
    }

    /** Add up the apparels for sale into the collection. */
    private void initApparelsForSale() {
	apparelsForSale.add(ItemFactory.getEquippable("Hacker Hat"));
	apparelsForSale.add(ItemFactory.getEquippable("Boys Cap"));
	apparelsForSale.add(ItemFactory.getEquippable("Poop Hat"));
	apparelsForSale.add(ItemFactory.getEquippable("Heart Hat"));
	apparelsForSale.add(ItemFactory.getEquippable("Salakot"));
	apparelsForSale.add(ItemFactory.getEquippable("King Crown"));
	apparelsForSale.add(ItemFactory.getEquippable("Witch Hat"));
	apparelsForSale.add(ItemFactory.getEquippable("Goggles"));
	apparelsForSale.add(ItemFactory.getEquippable("Box Hat"));
    }

    /** Add up the items for sale into the collection. */
    private void initItemsForSale() {
	itemsForSale.add(ItemFactory.getInventoryItem("Battery S"));
	itemsForSale.add(ItemFactory.getInventoryItem("Battery M"));
	itemsForSale.add(ItemFactory.getInventoryItem("Battery L"));
	itemsForSale.add(ItemFactory.getInventoryItem("Hyper Batt C"));
	itemsForSale.add(ItemFactory.getInventoryItem("Hyper Batt B"));
	itemsForSale.add(ItemFactory.getInventoryItem("Hyper Batt A"));
	itemsForSale.add(ItemFactory.getInventoryItem("Hyper Batt S"));
	itemsForSale.add(ItemFactory.getInventoryItem("Plasma"));
	itemsForSale.add(ItemFactory.getInventoryItem("Disk Defrag"));
	itemsForSale.add(ItemFactory.getInventoryItem("Anti-virus"));
	itemsForSale.add(ItemFactory.getInventoryItem("Eiffing Ring"));
	itemsForSale.add(ItemFactory.getInventoryItem("X Ring"));
    }

    /** Place the context menu in a specific coordinates in respect to the selected item box. */
    private void moveContextMenu(int selectedItemBoxIndex) {
	if (currentTab == INVENTORY)
	    switch (selectedItemBoxIndex) {
		case 1:
		    contextMenu.x = 118;
		    contextMenu.y = 86;
		    break;
		case 2:
		    contextMenu.x = 178;
		    contextMenu.y = 86;
		    break;
		case 3:
		    contextMenu.x = 238;
		    contextMenu.y = 86;
		    break;
		case 4:
		    contextMenu.x = 78;
		    contextMenu.y = 86;
		    break;
		case 5:
		    contextMenu.x = 138;
		    contextMenu.y = 86;
		    break;
		case 6:
		    contextMenu.x = 198;
		    contextMenu.y = 86;
		    break;
		case 7:
		    contextMenu.x = 118;
		    contextMenu.y = 27;
		    break;
		case 8:
		    contextMenu.x = 178;
		    contextMenu.y = 27;
		    break;
		case 9:
		    contextMenu.x = 238;
		    contextMenu.y = 27;
		    break;
		case 10:
		    contextMenu.x = 78;
		    contextMenu.y = 27;
		    break;
		case 11:
		    contextMenu.x = 138;
		    contextMenu.y = 27;
		    break;
		case 12:
		    contextMenu.x = 198;
		    contextMenu.y = 27;
		    break;
		case 13:
		    contextMenu.x = 118;
		    contextMenu.y = 48;
		    break;
		case 14:
		    contextMenu.x = 178;
		    contextMenu.y = 48;
		    break;
		case 15:
		    contextMenu.x = 238;
		    contextMenu.y = 48;
		    break;
		case 16:
		    contextMenu.x = 78;
		    contextMenu.y = 48;
		    break;
		case 17:
		    contextMenu.x = 138;
		    contextMenu.y = 48;
		    break;
		case 18:
		    contextMenu.x = 198;
		    contextMenu.y = 48;
		    break;
		case 19:
		    contextMenu.x = 118;
		    contextMenu.y = 60;
		    break;
		case 20:
		    contextMenu.x = 178;
		    contextMenu.y = 60;
		    break;
		case 21:
		    contextMenu.x = 238;
		    contextMenu.y = 60;
		    break;
		case 22:
		    contextMenu.x = 78;
		    contextMenu.y = 60;
		    break;
		case 23:
		    contextMenu.x = 138;
		    contextMenu.y = 60;
		    break;
		case 24:
		    contextMenu.x = 198;
		    contextMenu.y = 60;
		    break;
	    }
	else
	    switch (selectedItemBoxIndex) {
		case 1:
		    contextMenu.x = 118;
		    contextMenu.y = 55;
		    break;
		case 2:
		    contextMenu.x = 178;
		    contextMenu.y = 55;
		    break;
		case 3:
		    contextMenu.x = 238;
		    contextMenu.y = 55;
		    break;
		case 4:
		    contextMenu.x = 78;
		    contextMenu.y = 55;
		    break;
		case 5:
		    contextMenu.x = 168;
		    contextMenu.y = 55;
		    break;
		case 6:
		    contextMenu.x = 198;
		    contextMenu.y = 55;
		    break;
		case 7:
		    contextMenu.x = 118;
		    contextMenu.y = 115;
		    break;
		case 8:
		    contextMenu.x = 178;
		    contextMenu.y = 115;
		    break;
		case 9:
		    contextMenu.x = 238;
		    contextMenu.y = 115;
		    break;
		case 10:
		    contextMenu.x = 78;
		    contextMenu.y = 115;
		    break;
		case 11:
		    contextMenu.x = 168;
		    contextMenu.y = 115;
		    break;
		case 12:
		    contextMenu.x = 198;
		    contextMenu.y = 115;
		    break;
		case 13:
		    contextMenu.x = 118;
		    contextMenu.y = 85;
		    break;
		case 14:
		    contextMenu.x = 178;
		    contextMenu.y = 85;
		    break;
		case 15:
		    contextMenu.x = 238;
		    contextMenu.y = 85;
		    break;
		case 16:
		    contextMenu.x = 78;
		    contextMenu.y = 85;
		    break;
		case 17:
		    contextMenu.x = 168;
		    contextMenu.y = 85;
		    break;
		case 18:
		    contextMenu.x = 198;
		    contextMenu.y = 85;
		    break;
	    }
	setContextMenuVisibility(true);
    }

    private void setContextMenuAlpha(float alpha) {
	contextMenu.move(alpha, Linear.INOUT, 5000, 0, false);
	contextMenuClose.move(alpha, Linear.INOUT, 5000, 0, false);
	contextMenuItemName.move(alpha, Linear.INOUT, 5000, 0, false);
	contextMenuItemDescription.move(alpha, Linear.INOUT, 5000, 0, false);
	contextMenuBuy.move(alpha, Linear.INOUT, 5000, 0, false);
	contextMenuSell.move(alpha, Linear.INOUT, 5000, 0, false);
    }

    private void setContextMenuVisibility(boolean visible) {
	contextMenu.visible = visible;
	contextMenuClose.visible = visible;
	contextMenuItemName.visible = visible;
	contextMenuItemDescription.visible = visible;
	contextMenuBuy.visible = visible;
	contextMenuSell.visible = visible;
    }

    /** Set the visiblity of the inventory boxes to either true or false. */
    private void setInventoryBoxesVisibility(boolean visible) {
	for (int index = 0; index < invBoxes.size(); index++)
	    invBoxes.get(index).visible = visible;

    }

    /** Set the visibility of the item boxes to either true or false. */
    private void setItemBoxesVisiblity(boolean visible) {
	for (int index = 0; index < itemBoxes.size(); index++)
	    itemBoxes.get(index).visible = visible;
    }

    /** Show all displays related to the Apparel tab. */
    private void showApparelsTab() {
	apparelsTabSelected.visible = true;
	apparelsTabUnselected.visible = false;
	inventoryTabSelected.visible = false;
	inventoryTabUnselected.visible = true;
	itemsTabSelected.visible = false;
	itemsTabUnselected.visible = true;

	for (int index = 0; index < apparelTabDisplays.size(); index++)
	    apparelTabDisplays.get(index).visible = true;
	for (int index = 0; index < inventoryTabDisplays.size(); index++)
	    inventoryTabDisplays.get(index).visible = false;
	for (int index = 0; index < itemTabDisplays.size(); index++)
	    itemTabDisplays.get(index).visible = false;
    }

    /** Show all displays related to the Inventory tab. */
    private void showInventoryTab() {
	apparelsTabSelected.visible = false;
	apparelsTabUnselected.visible = true;
	inventoryTabSelected.visible = true;
	inventoryTabUnselected.visible = false;
	itemsTabSelected.visible = false;
	itemsTabUnselected.visible = true;

	for (int index = 0; index < apparelTabDisplays.size(); index++)
	    apparelTabDisplays.get(index).visible = false;
	for (int index = 0; index < inventoryTabDisplays.size(); index++)
	    inventoryTabDisplays.get(index).visible = true;
	for (int index = 0; index < itemTabDisplays.size(); index++)
	    itemTabDisplays.get(index).visible = false;
    }

    /** Show all displays related to the Items tab. */
    private void showItemsTab() {
	apparelsTabSelected.visible = false;
	apparelsTabUnselected.visible = true;
	inventoryTabSelected.visible = false;
	inventoryTabUnselected.visible = true;
	itemsTabSelected.visible = true;
	itemsTabUnselected.visible = false;

	for (int index = 0; index < apparelTabDisplays.size(); index++)
	    apparelTabDisplays.get(index).visible = false;
	for (int index = 0; index < inventoryTabDisplays.size(); index++)
	    inventoryTabDisplays.get(index).visible = false;
	for (int index = 0; index < itemTabDisplays.size(); index++)
	    itemTabDisplays.get(index).visible = true;
    }
}
