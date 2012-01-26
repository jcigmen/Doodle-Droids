package aurelienribon.tweenengine.simpletweenables;

import aurelienribon.tweenengine.SimpleTweenable;

/**
 * Defines an int value on which tweens can be applied.
 *
 * @see SimpleTweenable
 * @author Aurelien Ribon (aurelien.ribon@gmail.com)
 */
public class TweenableInt implements SimpleTweenable {
	public int value;

	@Override
	public int getTweenValues(int tweenType, float[] returnValues) {
		returnValues[0] = value;
		return 1;
	}

	@Override
	public void onTweenUpdated(int tweenType, float[] newValues) {
		value = Math.round(newValues[0]);
	}
}
