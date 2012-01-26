package org.nullsys.androidgames.framework.display.text;

import org.nullsys.androidgames.framework.display.Texture;
import org.nullsys.androidgames.framework.display.TextureRegion;

public abstract class Font {

    public Texture texture;

    public float glyphWidth;

    public float glyphHeight;

    public static final int TAB_SPACING = 5;

    public abstract TextureRegion getChar(char c);
}
