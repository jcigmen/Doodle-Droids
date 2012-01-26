package org.nullsys.androidgames.framework.math;

import android.util.FloatMath;

public class VectorCoords {

    public static float TO_RADIANS = 1 / 180.0f * (float) Math.PI;
    public static float TO_DEGREES = 1 / (float) Math.PI * 180;
    public float x, y;

    public VectorCoords() {

    }

    public VectorCoords(float x, float y) {
	this.x = x;
	this.y = y;
    }

    public VectorCoords(VectorCoords other) {
	x = other.x;
	y = other.y;
    }

    public VectorCoords add(float x, float y) {
	this.x += x;
	this.y += y;
	return this;
    }

    public VectorCoords add(VectorCoords other) {
	x += other.x;
	y += other.y;
	return this;
    }

    public float angle() {
	float angle = (float) Math.atan2(y, x) * TO_DEGREES;
	if (angle < 0)
	    angle += 360;
	return angle;
    }

    public VectorCoords cpy() {
	return new VectorCoords(x, y);
    }

    public float dist(float x, float y) {
	float distX = this.x - x;
	float distY = this.y - y;
	return FloatMath.sqrt(distX * distX + distY * distY);
    }

    public float dist(VectorCoords other) {
	float distX = x - other.x;
	float distY = y - other.y;
	return FloatMath.sqrt(distX * distX + distY * distY);
    }

    public float distSquared(float x, float y) {
	float distX = this.x - x;
	float distY = this.y - y;
	return distX * distX + distY * distY;
    }

    public float distSquared(VectorCoords other) {
	float distX = x - other.x;
	float distY = y - other.y;
	return distX * distX + distY * distY;
    }

    public float len() {
	return FloatMath.sqrt(x * x + y * y);
    }

    public VectorCoords mul(float scalar) {
	x *= scalar;
	y *= scalar;
	return this;
    }

    public VectorCoords nor() {
	float len = len();
	if (len != 0) {
	    x /= len;
	    y /= len;
	}
	return this;
    }

    public VectorCoords rotate(float angle) {
	float rad = angle * TO_RADIANS;
	float cos = FloatMath.cos(rad);
	float sin = FloatMath.sin(rad);

	float newX = x * cos - y * sin;
	float newY = x * sin + y * cos;

	x = newX;
	y = newY;

	return this;
    }

    public VectorCoords set(float x, float y) {
	this.x = x;
	this.y = y;
	return this;
    }

    public VectorCoords set(VectorCoords other) {
	x = other.x;
	y = other.y;
	return this;
    }

    public VectorCoords sub(float x, float y) {
	this.x -= x;
	this.y -= y;
	return this;
    }

    public VectorCoords sub(VectorCoords other) {
	x -= other.x;
	y -= other.y;
	return this;
    }
}