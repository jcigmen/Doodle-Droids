package org.nullsys.androidgames.framework.display;


public class TextureRegion {

    public final float u1, v1;

    public final float u2, v2;

    public final Texture texture;

    public final float width, height;

    public TextureRegion(Texture texture, float x, float y, float width, float height) {
	u1 = x / texture.width;
	v1 = y / texture.height;
	u2 = u1 + width / texture.width;
	v2 = v1 + height / texture.height;
	this.texture = texture;
	this.width = width;
	this.height = height;
    }
}
