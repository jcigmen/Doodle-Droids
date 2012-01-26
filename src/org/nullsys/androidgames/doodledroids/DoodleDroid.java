package org.nullsys.androidgames.doodledroids;

import java.util.ArrayList;

import android.graphics.Color;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

import org.nullsys.androidgames.doodledroids.event.DroidStateCallback;
import org.nullsys.androidgames.doodledroids.item.Apparel;
import org.nullsys.androidgames.doodledroids.item.EquippableItem;
import org.nullsys.androidgames.doodledroids.item.Headgear;
import org.nullsys.androidgames.doodledroids.item.InventoryItem;
import org.nullsys.androidgames.framework.Input.TouchEvent;
import org.nullsys.androidgames.framework.display.Animation;
import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.SpriteBatcher;
import org.nullsys.androidgames.framework.display.TextureRegion;

public class DoodleDroid extends DisplayObject implements TweenCallback {

    public static final int YEMA = 1;

    public static final int SUNDOT_KULANGOT = 4;

    public static final int PASTILLAS = 7;

    private static final int ENERGY_RESTORE_RATE = 1;

    public enum Facing {
	LEFT, RIGHT
    }

    public enum State {
	NORMAL_IDLE, NORMAL_WALKING, SICK_IDLE, SICK_WALKING
    }

    public ArrayList<InventoryItem> usableItems = new ArrayList<InventoryItem>();

    public ArrayList<EquippableItem> equipment = new ArrayList<EquippableItem>();

    public DroidStateCallback callback;

    public Apparel apparel;

    public Headgear headgear;

    public Animation normalIdleLeft;

    public Animation normalWalkingLeft;

    public Animation normalIdleRight;

    public Animation normalWalkingRight;

    public Animation sickIdleLeft;

    public Animation sickWalkingRight;

    public Animation sickIdleRight;

    public Animation sickWalkingLeft;

    public String name = "";

    public int color = Color.BLUE;

    public int level = 1;

    public int exp = 0;

    public int expTotal = 0;

    public int expNxtLvl = 10;

    public int energy = 30;

    public int energyTotal = 30;

    public int health = 30;

    public int healthTotal = 30;

    public int freeStats = 10;

    public int luck = 30;

    public int droills = 1000;

    public boolean tapped = false;

    public boolean hasVirus = false;

    public boolean hasWorm = false;

    public boolean hasHardwareFailure = false;

    public int type = YEMA;

    public Facing facing = Facing.LEFT;

    public State state = State.NORMAL_IDLE;

    public DoodleDroid(int type, int color) {
	super.tweenCallback = this;
	this.type = type;
	this.color = color;
	y = 50;
	init();
    }

    public void addExp(int exp) {
	float bonusExp = apparel != null ? exp * headgear.expBonus : 0;
	if (this.exp + exp + bonusExp > expNxtLvl) {
	    level++;
	    this.exp = this.exp + exp - expNxtLvl;
	    expNxtLvl += expNxtLvl * 0.25;
	    energy = energyTotal;
	    health = healthTotal;
	    freeStats += 100;
	    if (callback != null)
		callback.onLevelUp(level);
	} else
	    this.exp += exp + bonusExp;
    }

    public void changeType(int type, int color) {
	this.type = type;
	this.color = color;
	init();
    }

    @Override
    public void checkInputs(int eventType, int eventX, int eventY) {
	if (visible && enabled) {
	    touchEvent = new TouchEvent();
	    touchEvent.x = eventX;
	    touchEvent.y = eventY;
	    touchEvent.type = eventType;
	    switch (eventType) {
		case TouchEvent.TOUCH_DOWN:
		    if (hitTestPoint(eventX, eventY)) {
			if (draggable) {
			    dragged = true;
			    previousEventX = eventX;
			    previousEventY = 320 - eventY;
			}
			tapped = true;
			if (touchCallback != null)
			    touchCallback.onTouchEvent(this, touchEvent);
		    }
		    break;
		case TouchEvent.TOUCH_UP:
		    if (hitTestPoint(eventX, eventY)) {
			tapped = false;
			dragged = false;
			if (tapped && touchCallback != null) {
			    touchEvent.type = TouchEvent.TOUCH_TAP;
			    touchCallback.onTouchEvent(this, touchEvent);
			} else if (touchCallback != null)
			    touchCallback.onTouchEvent(this, touchEvent);
		    }
		    break;
		case TouchEvent.TOUCH_DRAGGED:
		    tapped = false;
		    if (!hitTestPoint(eventX, eventY))
			dragged = false;
		    else {
			if (touchCallback != null)
			    touchCallback.onTouchEvent(this, touchEvent);
			if (dragged && draggable) {
			    if (dragX)
				x += eventX - previousEventX;
			    if (dragY)
				y += 320 - eventY - previousEventY;
			    previousEventX = eventX;
			    previousEventY = 320 - eventY;
			}
		    }
		    break;
	    }
	}
    }

    public Animation getDisplayByState() {
	Animation dispObj = null;
	switch (facing) {
	    case LEFT:
		switch (state) {
		    case NORMAL_WALKING:
			dispObj = normalWalkingLeft;
			break;
		    case NORMAL_IDLE:
			dispObj = normalIdleLeft;
			break;
		    case SICK_IDLE:
			dispObj = sickIdleLeft;
			break;
		    case SICK_WALKING:
			dispObj = sickWalkingLeft;
			break;
		}
		break;
	    case RIGHT:
		switch (state) {
		    case NORMAL_WALKING:
			dispObj = normalWalkingRight;
			break;
		    case NORMAL_IDLE:
			dispObj = normalIdleRight;
			break;
		    case SICK_IDLE:
			dispObj = sickIdleRight;
			break;
		    case SICK_WALKING:
			dispObj = sickWalkingRight;
			break;
		}
		break;
	}
	return dispObj;
    }

    public int getEnergyBonus() {
	int energyBonus = 0;
	energyBonus += headgear != null ? headgear.energyBonus : 0;
	energyBonus += apparel != null ? apparel.energyBonus : 0;
	return energyBonus;
    }

    public int getEnergyTotal() {
	int energyTotal = this.energyTotal;
	energyTotal += headgear != null ? headgear.energyBonus : 0;
	energyTotal += apparel != null ? apparel.energyBonus : 0;
	energyTotal *= hasVirus ? .5 : 1;
	energyTotal *= hasWorm ? .75 : 1;
	energyTotal *= hasHardwareFailure ? .9 : 1;
	return energyTotal;
    }

    public int getHealthBonus() {
	int bonus = apparel != null ? apparel.healthBonus : 0;
	return bonus;
    }

    public int getHealthTotal() {
	int healthTotal = this.healthTotal;
	healthTotal += apparel != null ? apparel.healthBonus : 0;
	healthTotal *= hasVirus ? .5 : 1;
	healthTotal *= hasWorm ? .75 : 1;
	healthTotal *= hasHardwareFailure ? .9 : 1;
	return healthTotal;
    }

    @Override
    public float getHeight() {
	return getDisplayByState().height;
    }

    public int getLuckBonus() {
	int bonus = headgear != null ? headgear.luckBonus : 0;
	return bonus;
    }

    @Override
    public TextureRegion getTextureRegion() {
	return getDisplayByState().textureRegion;
    }

    @Override
    public float getWidth() {
	return getDisplayByState().width;
    }

    @Override
    public boolean hitTestPoint(int x, int y) {
	boolean hits = false;
	if (hitArea != null)
	    hits = x >= hitArea.x && x <= hitArea.x + hitArea.width && y >= hitArea.y && y <= hitArea.y + hitArea.height;
	else
	    hits = x >= this.x && x <= this.x + getDisplayByState().width && y >= this.y && y <= this.y + getDisplayByState().height;
	return hits;
    }

    public void inflictAilment() {
	if (state == State.NORMAL_IDLE || state == State.SICK_IDLE) {
	    hasVirus = Math.random() * 101 < 0.1 * (100 - health / getHealthTotal() * 100) - (luck + getLuckBonus() / Math.random() * 6 + 25);
	    hasWorm = Math.random() * 101 < 0.1 * (100 - energy / getEnergyTotal() * 100) - (luck + getLuckBonus() / Math.random() * 6 + 25);
	    hasHardwareFailure = Math.random() * 101 < 10 / luck * equipment.size() * 10;
	    state = state != State.NORMAL_WALKING && state != State.SICK_WALKING && (hasVirus || hasWorm || hasHardwareFailure) ? State.SICK_IDLE : state;
	}
    }

    @Override
    public void render(SpriteBatcher batcher) {
	try {
	    batcher.beginBatch(getDisplayByState().textureRegion.texture);
	    batcher.drawSprite(x, y, getDisplayByState().getWidth(), getDisplayByState().getHeight(), getDisplayByState().scaleX, getDisplayByState().scaleY, getDisplayByState().rotation, getDisplayByState().getTextureRegion());
	    batcher.endBatch();

	    if (headgear != null) {
		headgear.x = x + getDisplayByState().width / 2 - headgear.width / 2;
		switch (type) {
		    case YEMA:
			if (state == State.SICK_IDLE || state == State.SICK_WALKING && facing == Facing.RIGHT || state == State.NORMAL_WALKING && facing == Facing.RIGHT)
			    switch (getDisplayByState().frameNumber) {
				case 0:
				    headgear.y = y + 70;
				    break;
				case 1:
				    headgear.y = y + 73;
				    break;
				case 2:
				    headgear.y = y + 76;
				    break;
				case 3:
				    headgear.y = y + 79;
				    break;
				case 4:
				    headgear.y = y + 76;
				    break;
				case 5:
				    headgear.y = y + 73;
				    break;
				case 6:
				    headgear.y = y + 70;
				    break;
				case 7:
				    headgear.y = y + 67;
				    break;
			    }
			else if (state == State.NORMAL_IDLE)
			    switch (getDisplayByState().frameNumber) {
				case 0:
				    headgear.y = y + 71;
				    break;
				case 1:
				    headgear.y = y + 74;
				    break;
				case 2:
				    headgear.y = y + 77;
				    break;
				case 3:
				    headgear.y = y + 80;
				    break;
				case 4:
				    headgear.y = y + 77;
				    break;
				case 5:
				    headgear.y = y + 74;
				    break;
				case 6:
				    headgear.y = y + 71;
				    break;
				case 7:
				    headgear.y = y + 68;
				    break;
				case 8:
				    headgear.y = y + 68;
				    break;
				case 9:
				    headgear.y = y + 71;
				    break;
				case 10:
				    headgear.y = y + 74;
				    break;
				case 11:
				    headgear.y = y + 77;
				    break;
				case 12:
				    headgear.y = y + 74;
				    break;
				case 13:
				    headgear.y = y + 71;
				    break;
				case 14:
				    headgear.y = y + 68;
				    break;
				case 15:
				    headgear.y = y + 65;
				    break;
			    }
			else if (state == State.SICK_WALKING && facing == Facing.LEFT || state == State.NORMAL_WALKING && facing == Facing.LEFT)
			    switch (getDisplayByState().frameNumber) {
				case 0:
				    headgear.y = y + 67;
				    break;
				case 1:
				    headgear.y = y + 69;
				    break;
				case 2:
				    headgear.y = y + 72;
				    break;
				case 3:
				    headgear.y = y + 75;
				    break;
				case 4:
				    headgear.y = y + 78;
				    break;
				case 5:
				    headgear.y = y + 75;
				    break;
				case 6:
				    headgear.y = y + 72;
				    break;
				case 7:
				    headgear.y = y + 69;
				    break;
			    }
			break;
		    case SUNDOT_KULANGOT:
			if (state == State.SICK_IDLE)
			    switch (getDisplayByState().frameNumber) {
				case 0:
				    headgear.y = y + 58;
				    break;
				case 1:
				    headgear.y = y + 57;
				    break;
				case 2:
				    headgear.y = y + 56;
				    break;
				case 3:
				    headgear.y = y + 55;
				    break;
				case 4:
				    headgear.y = y + 56;
				    break;
				case 5:
				    headgear.y = y + 57;
				    break;
				case 6:
				    headgear.y = y + 58;
				    break;
				case 7:
				    headgear.y = y + 59;
				    break;
			    }
			else if (state == State.NORMAL_IDLE)
			    switch (getDisplayByState().frameNumber) {
				case 0:
				    headgear.y = y + 58;
				    break;
				case 1:
				    headgear.y = y + 57;
				    break;
				case 2:
				    headgear.y = y + 56;
				    break;
				case 3:
				    headgear.y = y + 55;
				    break;
				case 4:
				    headgear.y = y + 56;
				    break;
				case 5:
				    headgear.y = y + 57;
				    break;
				case 6:
				    headgear.y = y + 58;
				    break;
				case 7:
				    headgear.y = y + 59;
				    break;
				case 8:
				    headgear.y = y + 59;
				    break;
				case 9:
				    headgear.y = y + 58;
				    break;
				case 10:
				    headgear.y = y + 57;
				    break;
				case 11:
				    headgear.y = y + 56;
				    break;
				case 12:
				    headgear.y = y + 57;
				    break;
				case 13:
				    headgear.y = y + 58;
				    break;
				case 14:
				    headgear.y = y + 59;
				    break;
				case 15:
				    headgear.y = y + 59;
				    break;
			    }
			else
			    switch (getDisplayByState().frameNumber) {
				case 0:
				    headgear.y = y + 61;
				    break;
				case 1:
				    headgear.y = y + 69;
				    break;
				case 2:
				    headgear.y = y + 77;
				    break;
				case 3:
				    headgear.y = y + 85;
				    break;
				case 4:
				    headgear.y = y + 85;
				    break;
				case 5:
				    headgear.y = y + 77;
				    break;
				case 6:
				    headgear.y = y + 69;
				    break;
				case 7:
				    headgear.y = y + 61;
				    break;
			    }
			break;
		    case PASTILLAS:
			if (state == State.SICK_IDLE || state == State.SICK_WALKING && facing == Facing.LEFT || state == State.NORMAL_WALKING && facing == Facing.LEFT)
			    switch (getDisplayByState().frameNumber) {
				case 0:
				    headgear.y = y + 77;
				    break;
				case 1:
				    headgear.y = y + 79;
				    break;
				case 2:
				    headgear.y = y + 81;
				    break;
				case 3:
				    headgear.y = y + 79;
				    break;
				case 4:
				    headgear.y = y + 77;
				    break;
				case 5:
				    headgear.y = y + 75;
				    break;
				case 6:
				    headgear.y = y + 73;
				    break;
				case 7:
				    headgear.y = y + 75;
				    break;
			    }
			else if (state == State.NORMAL_IDLE)
			    switch (getDisplayByState().frameNumber) {
				case 0:
				    headgear.y = y + 73;
				    break;
				case 1:
				    headgear.y = y + 74;
				    break;
				case 2:
				    headgear.y = y + 75;
				    break;
				case 3:
				    headgear.y = y + 74;
				    break;
				case 4:
				    headgear.y = y + 72;
				    break;
				case 5:
				    headgear.y = y + 70;
				    break;
				case 6:
				    headgear.y = y + 72;
				    break;
				case 7:
				    headgear.y = y + 73;
				    break;
				case 8:
				    headgear.y = y + 74;
				    break;
				case 9:
				    headgear.y = y + 76;
				    break;
				case 10:
				    headgear.y = y + 74;
				    break;
				case 11:
				    headgear.y = y + 72;
				    break;
				case 12:
				    headgear.y = y + 70;
				    break;
				case 13:
				    headgear.y = y + 68;
				    break;
				case 14:
				    headgear.y = y + 67;
				    break;
				case 15:
				    headgear.y = y + 69;
				    break;
			    }
			else
			    switch (getDisplayByState().frameNumber) {
				case 0:
				    headgear.y = y + 75;
				    break;
				case 1:
				    headgear.y = y + 73;
				    break;
				case 2:
				    headgear.y = y + 75;
				    break;
				case 3:
				    headgear.y = y + 77;
				    break;
				case 4:
				    headgear.y = y + 79;
				    break;
				case 5:
				    headgear.y = y + 81;
				    break;
				case 6:
				    headgear.y = y + 79;
				    break;
				case 7:
				    headgear.y = y + 77;
				    break;
			    }
			break;
		}
		headgear.scaleX = 1f;
		headgear.scaleY = 1f;
		headgear.render(batcher);
	    }

	    if (apparel != null) {
		apparel.x = x + getDisplayByState().width / 2;
		apparel.y = y + getDisplayByState().height - apparel.height;
		apparel.render(batcher);
	    }
	} catch (NullPointerException e) {
	}
    }

    public void restoreEnergy() {
	if (energy + ENERGY_RESTORE_RATE <= energyTotal)
	    energy += ENERGY_RESTORE_RATE;
	else
	    energy = energyTotal;
    }

    public void restoreEnergy(int energy) {
	if (this.energy + energy <= getEnergyTotal())
	    this.energy += energy;
	else
	    this.energy = getEnergyTotal();
    }

    public void restoreHealth(int healthPlus) {
	if (health + healthPlus <= getHealthTotal())
	    health += healthPlus;
	else
	    health = getHealthTotal();
    }

    @Override
    public void tweenEventOccured(Types arg0, Tween arg1) {
	if (arg0 == Types.COMPLETE && arg1.isFinished())
	    state = hasVirus || hasWorm || hasHardwareFailure ? State.SICK_IDLE : State.NORMAL_IDLE;
    }

    @Override
    public void update(float deltaTime) {
	if (enabled)
	    manager.update();
	try {
	    if (!tapped && enabled)
		getDisplayByState().update(deltaTime);
	} catch (NullPointerException e) {
	}
    }

    private void init() {
	switch (color) {
	    case Color.BLUE:
		switch (type) {
		    case YEMA:
			normalIdleLeft = new Animation(Assets.yemaBlue, 0, 0, 87, 97, 8, 16, .24f);
			normalIdleRight = new Animation(Assets.yemaBlue, 0, 0, 87, 97, 8, 16, .24f);
			normalWalkingLeft = new Animation(Assets.yemaBlue, 0, 585, 87, 98, 8, 8, 0.125f);
			normalWalkingRight = new Animation(Assets.yemaBlue, 0, 487, 87, 98, 8, 8, 0.125f);
			sickIdleLeft = new Animation(Assets.yemaBlue, 0, 195, 87, 97, 8, 8, 0.125f);
			sickIdleRight = new Animation(Assets.yemaBlue, 0, 195, 87, 97, 8, 8, 0.125f);
			sickWalkingRight = new Animation(Assets.yemaBlue, 0, 293, 87, 98, 8, 8, 0.125f);
			sickWalkingLeft = new Animation(Assets.yemaBlue, 0, 390, 87, 98, 8, 8, 0.125f);
			break;
		    case SUNDOT_KULANGOT:
			normalIdleLeft = new Animation(Assets.sundotKulangotBlue, 0, 0, 92, 82, 8, 16, .24f);
			normalWalkingLeft = new Animation(Assets.sundotKulangotBlue, 0, 474, 92, 114, 8, 8, 0.125f);
			normalIdleRight = new Animation(Assets.sundotKulangotBlue, 0, 0, 92, 82, 8, 16, .24f);
			normalWalkingRight = new Animation(Assets.sundotKulangotBlue, 0, 360, 92, 114, 8, 8, 0.125f);
			sickIdleLeft = new Animation(Assets.sundotKulangotBlue, 0, 164, 92, 82, 8, 8, 0.125f);
			sickWalkingRight = new Animation(Assets.sundotKulangotBlue, 0, 247, 92, 113, 8, 8, 0.125f);
			sickWalkingLeft = new Animation(Assets.sundotKulangotBlue, 0, 589, 92, 114, 8, 8, 0.125f);
			sickIdleRight = new Animation(Assets.sundotKulangotBlue, 0, 164, 92, 82, 8, 8, 0.125f);
			break;
		    case PASTILLAS:
			normalIdleLeft = new Animation(Assets.pastillasBlue, 0, 0, 128, 98, 8, 16, .24f);
			normalWalkingLeft = new Animation(Assets.pastillasBlue, 0, 588, 128, 99, 8, 8, 0.125f);
			normalIdleRight = new Animation(Assets.pastillasBlue, 0, 0, 128, 98, 8, 16, .24f);
			normalWalkingRight = new Animation(Assets.pastillasBlue, 0, 490, 128, 99, 8, 8, 0.125f);
			sickIdleLeft = new Animation(Assets.pastillasBlue, 0, 196, 103, 128, 99, 8, 0.125f);
			sickIdleRight = new Animation(Assets.pastillasBlue, 0, 196, 103, 128, 99, 8, 0.125f);
			sickWalkingRight = new Animation(Assets.pastillasBlue, 0, 294, 128, 99, 8, 8, 0.125f);
			sickWalkingLeft = new Animation(Assets.pastillasBlue, 0, 392, 128, 99, 8, 8, 0.125f);
			break;
		    default:
			break;
		}
		break;
	    case Color.WHITE:
		switch (type) {
		    case YEMA:
			normalIdleLeft = new Animation(Assets.yemaWhite, 0, 0, 87, 97, 8, 16, .24f);
			normalIdleRight = new Animation(Assets.yemaWhite, 0, 0, 87, 97, 8, 16, .24f);
			normalWalkingLeft = new Animation(Assets.yemaWhite, 0, 585, 87, 98, 8, 8, 0.125f);
			normalWalkingRight = new Animation(Assets.yemaWhite, 0, 487, 87, 97, 8, 8, 0.125f);
			sickIdleLeft = new Animation(Assets.yemaWhite, 0, 195, 87, 97, 8, 8, 0.125f);
			sickIdleRight = new Animation(Assets.yemaWhite, 0, 195, 87, 97, 8, 8, 0.125f);
			sickWalkingRight = new Animation(Assets.yemaWhite, 0, 293, 87, 98, 8, 8, 0.125f);
			sickWalkingLeft = new Animation(Assets.yemaWhite, 0, 390, 87, 98, 8, 8, 0.125f);
			break;
		    case SUNDOT_KULANGOT:
			normalIdleLeft = new Animation(Assets.sundotKulangotWhite, 0, 0, 92, 82, 8, 16, .24f);
			normalWalkingLeft = new Animation(Assets.sundotKulangotWhite, 0, 474, 92, 114, 8, 8, 0.125f);
			normalIdleRight = new Animation(Assets.sundotKulangotWhite, 0, 0, 92, 82, 8, 16, .24f);
			normalWalkingRight = new Animation(Assets.sundotKulangotWhite, 0, 360, 92, 114, 8, 8, 0.125f);
			sickIdleLeft = new Animation(Assets.sundotKulangotWhite, 0, 164, 92, 82, 8, 8, 0.125f);
			sickWalkingRight = new Animation(Assets.sundotKulangotWhite, 0, 247, 92, 113, 8, 8, 0.125f);
			sickWalkingLeft = new Animation(Assets.sundotKulangotWhite, 0, 589, 92, 114, 8, 8, 0.125f);
			sickIdleRight = new Animation(Assets.sundotKulangotWhite, 0, 164, 92, 82, 8, 8, 0.125f);
			break;
		    case PASTILLAS:
			normalIdleLeft = new Animation(Assets.pastillasWhite, 0, 0, 128, 98, 8, 16, .24f);
			normalWalkingLeft = new Animation(Assets.pastillasWhite, 0, 588, 128, 99, 8, 8, 0.125f);
			normalIdleRight = new Animation(Assets.pastillasWhite, 0, 0, 128, 98, 8, 16, .24f);
			normalWalkingRight = new Animation(Assets.pastillasWhite, 0, 490, 128, 99, 8, 8, 0.125f);
			sickIdleLeft = new Animation(Assets.pastillasWhite, 0, 196, 103, 128, 99, 8, 0.125f);
			sickIdleRight = new Animation(Assets.pastillasWhite, 0, 196, 103, 128, 99, 8, 0.125f);
			sickWalkingRight = new Animation(Assets.pastillasWhite, 0, 294, 128, 99, 8, 8, 0.125f);
			sickWalkingLeft = new Animation(Assets.pastillasWhite, 0, 392, 128, 99, 8, 8, 0.125f);
			break;
		    default:
			break;
		}
		break;
	    case Color.RED:
		switch (type) {
		    case YEMA:
			normalIdleLeft = new Animation(Assets.yemaRed, 0, 0, 87, 97, 8, 16, .24f);
			normalIdleRight = new Animation(Assets.yemaRed, 0, 0, 87, 97, 8, 16, .24f);
			normalWalkingLeft = new Animation(Assets.yemaRed, 0, 585, 87, 98, 8, 8, 0.125f);
			normalWalkingRight = new Animation(Assets.yemaRed, 0, 487, 87, 98, 8, 8, 0.125f);
			sickIdleLeft = new Animation(Assets.yemaRed, 0, 195, 87, 97, 8, 8, 0.125f);
			sickIdleRight = new Animation(Assets.yemaRed, 0, 195, 87, 97, 8, 8, 0.125f);
			sickWalkingRight = new Animation(Assets.yemaRed, 0, 293, 87, 98, 8, 8, 0.125f);
			sickWalkingLeft = new Animation(Assets.yemaRed, 0, 390, 87, 98, 8, 8, 0.125f);
			break;
		    case SUNDOT_KULANGOT:
			normalIdleLeft = new Animation(Assets.sundotKulangotRed, 0, 0, 92, 82, 8, 16, .24f);
			normalWalkingLeft = new Animation(Assets.sundotKulangotRed, 0, 474, 92, 114, 8, 8, 0.125f);
			normalIdleRight = new Animation(Assets.sundotKulangotRed, 0, 0, 92, 82, 8, 16, .24f);
			normalWalkingRight = new Animation(Assets.sundotKulangotRed, 0, 360, 92, 114, 8, 8, 0.125f);
			sickIdleLeft = new Animation(Assets.sundotKulangotRed, 0, 164, 92, 82, 8, 8, 0.125f);
			sickWalkingRight = new Animation(Assets.sundotKulangotRed, 0, 247, 92, 113, 8, 8, 0.125f);
			sickWalkingLeft = new Animation(Assets.sundotKulangotRed, 0, 589, 92, 114, 8, 8, 0.125f);
			sickIdleRight = new Animation(Assets.sundotKulangotRed, 0, 164, 92, 82, 8, 8, 0.125f);
			break;
		    case PASTILLAS:
			normalIdleLeft = new Animation(Assets.pastillasRed, 0, 0, 128, 98, 8, 16, .24f);
			normalWalkingLeft = new Animation(Assets.pastillasRed, 0, 588, 128, 99, 8, 8, 0.125f);
			normalIdleRight = new Animation(Assets.pastillasRed, 0, 0, 128, 98, 8, 16, .24f);
			normalWalkingRight = new Animation(Assets.pastillasRed, 0, 490, 128, 99, 8, 8, 0.125f);
			sickIdleLeft = new Animation(Assets.pastillasRed, 0, 196, 103, 128, 99, 8, 0.125f);
			sickIdleRight = new Animation(Assets.pastillasRed, 0, 196, 103, 128, 99, 8, 0.125f);
			sickWalkingRight = new Animation(Assets.pastillasRed, 0, 294, 128, 99, 8, 8, 0.125f);
			sickWalkingLeft = new Animation(Assets.pastillasRed, 0, 392, 128, 99, 8, 8, 0.125f);
			break;
		    default:
			break;
		}
		break;
	    case Color.GREEN:
		switch (type) {
		    case YEMA:
			normalIdleLeft = new Animation(Assets.yemaGreen, 0, 0, 87, 97, 8, 16, .24f);
			normalIdleRight = new Animation(Assets.yemaGreen, 0, 0, 87, 97, 8, 16, .24f);
			normalWalkingLeft = new Animation(Assets.yemaGreen, 0, 585, 87, 98, 8, 8, 0.125f);
			normalWalkingRight = new Animation(Assets.yemaGreen, 0, 487, 87, 98, 8, 8, 0.125f);
			sickIdleLeft = new Animation(Assets.yemaGreen, 0, 195, 87, 97, 8, 8, 0.125f);
			sickIdleRight = new Animation(Assets.yemaGreen, 0, 195, 87, 97, 8, 8, 0.125f);
			sickWalkingRight = new Animation(Assets.yemaGreen, 0, 293, 87, 98, 8, 8, 0.125f);
			sickWalkingLeft = new Animation(Assets.yemaGreen, 0, 390, 87, 98, 8, 8, 0.125f);
			break;
		    case SUNDOT_KULANGOT:
			normalIdleLeft = new Animation(Assets.sundotKulangotGreen, 0, 0, 92, 82, 8, 16, .24f);
			normalWalkingLeft = new Animation(Assets.sundotKulangotGreen, 0, 474, 92, 114, 8, 8, 0.125f);
			normalIdleRight = new Animation(Assets.sundotKulangotGreen, 0, 0, 92, 82, 8, 16, .24f);
			normalWalkingRight = new Animation(Assets.sundotKulangotGreen, 0, 360, 92, 114, 8, 8, 0.125f);
			sickIdleLeft = new Animation(Assets.sundotKulangotGreen, 0, 164, 92, 82, 8, 8, 0.125f);
			sickWalkingRight = new Animation(Assets.sundotKulangotGreen, 0, 247, 92, 113, 8, 8, 0.125f);
			sickWalkingLeft = new Animation(Assets.sundotKulangotGreen, 0, 589, 92, 114, 8, 8, 0.125f);
			sickIdleRight = new Animation(Assets.sundotKulangotGreen, 0, 164, 92, 82, 8, 8, 0.125f);
			break;
		    case PASTILLAS:
			normalIdleLeft = new Animation(Assets.pastillasGreen, 0, 0, 128, 98, 8, 16, .24f);
			normalWalkingLeft = new Animation(Assets.pastillasGreen, 0, 588, 128, 99, 8, 8, 0.125f);
			normalIdleRight = new Animation(Assets.pastillasGreen, 0, 0, 128, 98, 8, 16, .24f);
			normalWalkingRight = new Animation(Assets.pastillasGreen, 0, 490, 128, 99, 8, 8, 0.125f);
			sickIdleLeft = new Animation(Assets.pastillasGreen, 0, 196, 103, 128, 99, 8, 0.125f);
			sickIdleRight = new Animation(Assets.pastillasGreen, 0, 196, 103, 128, 99, 8, 0.125f);
			sickWalkingRight = new Animation(Assets.pastillasGreen, 0, 294, 128, 99, 8, 8, 0.125f);
			sickWalkingLeft = new Animation(Assets.pastillasGreen, 0, 392, 128, 99, 8, 8, 0.125f);
			break;
		    default:
			break;
		}
		break;
	    case Color.YELLOW:
		switch (type) {
		    case YEMA:
			normalIdleLeft = new Animation(Assets.yemaYellow, 0, 0, 87, 97, 8, 16, .24f);
			normalIdleRight = new Animation(Assets.yemaYellow, 0, 0, 87, 97, 8, 16, .24f);
			normalWalkingLeft = new Animation(Assets.yemaYellow, 0, 585, 87, 98, 8, 8, 0.125f);
			normalWalkingRight = new Animation(Assets.yemaYellow, 0, 487, 87, 98, 8, 8, 0.125f);
			sickIdleLeft = new Animation(Assets.yemaYellow, 0, 195, 87, 97, 8, 8, 0.125f);
			sickIdleRight = new Animation(Assets.yemaYellow, 0, 195, 87, 97, 8, 8, 0.125f);
			sickWalkingRight = new Animation(Assets.yemaYellow, 0, 293, 87, 98, 8, 8, 0.125f);
			sickWalkingLeft = new Animation(Assets.yemaYellow, 0, 390, 87, 98, 8, 8, 0.125f);
			break;
		    case SUNDOT_KULANGOT:
			normalIdleLeft = new Animation(Assets.sundotKulangotYellow, 0, 0, 92, 82, 8, 16, .24f);
			normalWalkingLeft = new Animation(Assets.sundotKulangotYellow, 0, 474, 92, 114, 8, 8, 0.125f);
			normalIdleRight = new Animation(Assets.sundotKulangotYellow, 0, 0, 92, 82, 8, 16, .24f);
			normalWalkingRight = new Animation(Assets.sundotKulangotYellow, 0, 360, 92, 114, 8, 8, 0.125f);
			sickIdleLeft = new Animation(Assets.sundotKulangotYellow, 0, 164, 92, 82, 8, 8, 0.125f);
			sickWalkingRight = new Animation(Assets.sundotKulangotYellow, 0, 247, 92, 113, 8, 8, 0.125f);
			sickWalkingLeft = new Animation(Assets.sundotKulangotYellow, 0, 589, 92, 114, 8, 8, 0.125f);
			sickIdleRight = new Animation(Assets.sundotKulangotYellow, 0, 164, 92, 82, 8, 8, 0.125f);
			break;
		    case PASTILLAS:
			normalIdleLeft = new Animation(Assets.pastillasYellow, 0, 0, 128, 98, 8, 16, .24f);
			normalWalkingLeft = new Animation(Assets.pastillasYellow, 0, 588, 128, 99, 8, 8, 0.125f);
			normalIdleRight = new Animation(Assets.pastillasYellow, 0, 0, 128, 98, 8, 16, .24f);
			normalWalkingRight = new Animation(Assets.pastillasYellow, 0, 490, 128, 99, 8, 8, 0.125f);
			sickIdleLeft = new Animation(Assets.pastillasYellow, 0, 196, 103, 128, 99, 8, 0.125f);
			sickIdleRight = new Animation(Assets.pastillasYellow, 0, 196, 103, 128, 99, 8, 0.125f);
			sickWalkingRight = new Animation(Assets.pastillasYellow, 0, 294, 128, 99, 8, 8, 0.125f);
			sickWalkingLeft = new Animation(Assets.pastillasYellow, 0, 392, 128, 99, 8, 8, 0.125f);
			break;
		    default:
			break;
		}
		break;
	}
    }
}
