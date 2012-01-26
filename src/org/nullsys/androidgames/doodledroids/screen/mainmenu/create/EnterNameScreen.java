package org.nullsys.androidgames.doodledroids.screen.mainmenu.create;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Back;
import aurelienribon.tweenengine.equations.Linear;
import aurelienribon.tweenengine.equations.Quart;

import org.nullsys.androidgames.doodledroids.Assets;
import org.nullsys.androidgames.doodledroids.Settings;
import org.nullsys.androidgames.doodledroids.screen.mainmenu.MainMenuScreen;
import org.nullsys.androidgames.framework.Game;
import org.nullsys.androidgames.framework.Input.TouchEvent;
import org.nullsys.androidgames.framework.display.Button;
import org.nullsys.androidgames.framework.display.DisplayObject;
import org.nullsys.androidgames.framework.display.Sprite;
import org.nullsys.androidgames.framework.display.TextureRegion;
import org.nullsys.androidgames.framework.display.event.TouchEventCallback;
import org.nullsys.androidgames.framework.display.text.Text;
import org.nullsys.androidgames.framework.display.text.Text.TextRegistration;
import org.nullsys.androidgames.framework.impl.GLScreen;
import org.nullsys.androidgames.framework.math.VectorCoords;

public class EnterNameScreen extends GLScreen implements TouchEventCallback {

    public static final int NAME_MAX_COUNT = 10;

    Text name;

    Button a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z;

    Button one, two, three, four, five, six, seven, eight, nine, zero;

    Button capsLock, space, delete;

    Button back, proceed;
    Sprite label, textBox, universalPanel;

    public EnterNameScreen(Game game, boolean tweenNavigation) {
	super(game);
	init(tweenNavigation);

	if (Settings.musicEnabled && !Assets.mainMenuBGM.isPlaying())
	    Assets.mainMenuBGM.play();
    }

    @Override
    public void backPressed() {
	proceed.tweenCallback = new TweenCallback() {

	    @Override
	    public void tweenEventOccured(Types arg0, Tween arg1) {
		game.setScreen(new MainMenuScreen(game));
	    }
	};

	float targetX = back.x + (back.width - back.width * 0.2f) / 2;
	float targetY = back.y + (back.height - back.height * 0.2f) / 2;

	back.move(targetX, targetY, 0.2f, 0.2f, 1, 0f, Back.INOUT, 250, 0, false);

	targetX = proceed.x + (proceed.width - proceed.width * 0.2f) / 2;
	targetY = proceed.y + (proceed.height - proceed.height * 0.2f) / 2;
	proceed.move(targetX, targetY, 0.2f, 0.2f, 1, 0f, Back.INOUT, 250, 0, true);

	label.move(0F, Linear.INOUT, 250, 0, false);

	textBox.move(0f, Linear.INOUT, 250, 0, false);

	int delay = 0;
	for (int index = 5; index < displayObjects.size(); index++) {
	    displayObjects.get(index).move(0f, Back.OUT, 250, delay, false);
	    delay += 5;
	}
    }

    @Override
    public void dispose() {
    }

    @Override
    public void menuPressed() {
    }

    @Override
    public void onTouchEvent(DisplayObject source, TouchEvent event) {
	if (event.type == TouchEvent.TOUCH_TAP)
	    if (source.equals(a) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "a";
	    else if (source.equals(b) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "b";
	    else if (source.equals(c) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "c";
	    else if (source.equals(d) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "d";
	    else if (source.equals(e) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "e";
	    else if (source.equals(f) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "f";
	    else if (source.equals(g) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "g";
	    else if (source.equals(h) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "h";
	    else if (source.equals(i) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "i";
	    else if (source.equals(j) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "j";
	    else if (source.equals(k) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "k";
	    else if (source.equals(l) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "l";
	    else if (source.equals(m) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "m";
	    else if (source.equals(n) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "n";
	    else if (source.equals(o) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "o";
	    else if (source.equals(p) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "p";
	    else if (source.equals(q) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "q";
	    else if (source.equals(r) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "r";
	    else if (source.equals(s) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "s";
	    else if (source.equals(t) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "t";
	    else if (source.equals(u) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "u";
	    else if (source.equals(v) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "v";
	    else if (source.equals(w) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "w";
	    else if (source.equals(x) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "x";
	    else if (source.equals(y) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "y";
	    else if (source.equals(z) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "z";
	    else if (source.equals(A) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "A";
	    else if (source.equals(B) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "B";
	    else if (source.equals(C) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "C";
	    else if (source.equals(D) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "D";
	    else if (source.equals(E) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "E";
	    else if (source.equals(F) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "F";
	    else if (source.equals(G) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "G";
	    else if (source.equals(H) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "H";
	    else if (source.equals(I) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "I";
	    else if (source.equals(J) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "J";
	    else if (source.equals(K) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "K";
	    else if (source.equals(L) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "L";
	    else if (source.equals(M) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "M";
	    else if (source.equals(N) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "N";
	    else if (source.equals(O) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "O";
	    else if (source.equals(P) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "P";
	    else if (source.equals(Q) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "Q";
	    else if (source.equals(R) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "R";
	    else if (source.equals(S) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "S";
	    else if (source.equals(T) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "T";
	    else if (source.equals(U) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "U";
	    else if (source.equals(V) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "V";
	    else if (source.equals(W) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "W";
	    else if (source.equals(X) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "X";
	    else if (source.equals(Y) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "Y";
	    else if (source.equals(Z) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "Z";
	    else if (source.equals(one) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "1";
	    else if (source.equals(two) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "2";
	    else if (source.equals(three) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "3";
	    else if (source.equals(four) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "4";
	    else if (source.equals(five) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "5";
	    else if (source.equals(six) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "6";
	    else if (source.equals(seven) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "7";
	    else if (source.equals(eight) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "8";
	    else if (source.equals(nine) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "9";
	    else if (source.equals(zero) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + "0";
	    else if (source.equals(capsLock))
		toggleLetters();
	    else if (source.equals(space) && name.text.length() < NAME_MAX_COUNT)
		name.text = name.text + " ";
	    else if (source.equals(delete) && name.text.length() > 0)
		name.text = name.text.substring(0, name.text.length() - 1);
	    else if (source.equals(back))
		backPressed();
	    else if (source.equals(proceed)) {
		textBox.tweenCallback = new TweenCallback() {

		    @Override
		    public void tweenEventOccured(Types arg0, Tween arg1) {
			game.getDroid().name = name.text;
			game.setScreen(new ChooseModelScreen(game));
		    }
		};
		label.move(0f, Linear.INOUT, 250, 0, false);

		textBox.move(0f, Linear.INOUT, 250, 0, true);

		for (int index = 5; index < displayObjects.size(); index++) {
		    float targetX = displayObjects.get(index).x + (displayObjects.get(index).width - displayObjects.get(index).width * 0.2f) / 2;
		    float targetY = displayObjects.get(index).y + (displayObjects.get(index).height - displayObjects.get(index).height * 0.2f) / 2;
		    displayObjects.get(index).move(targetX, targetY, 0.2f, 0.2f, 1, 0f, Back.INOUT, 250, 0, false);
		}

	    }
    }

    @Override
    public void pause() {
	Assets.mainMenuBGM.pause();
    }

    @Override
    public void resume() {
	if (Settings.musicEnabled && !Assets.mainMenuBGM.isPlaying())
	    Assets.mainMenuBGM.play();
    }

    @Override
    public void update(float deltaTime) {
	if (name.text.length() < 1)
	    proceed.visible = false;
	else
	    proceed.visible = true;
    }

    private void init(boolean tweenNavigation) {

	universalPanel = new Sprite(new TextureRegion(Assets.mainMenu, 0, 0, 480, 320));
	addChild(universalPanel);

	label = new Sprite(new TextureRegion(Assets.mainMenu, 652, 479, 184, 22));
	label.x = 149;
	label.y = 276;
	addChild(label);

	textBox = new Sprite(new TextureRegion(Assets.mainMenu, 534, 390, 425, 83));
	textBox.x = 36;
	textBox.y = 195;
	addChild(textBox);

	back = new Button(new TextureRegion(Assets.mainMenu, 896, 0, 55, 53), new TextureRegion(Assets.mainMenu, 903, 54, 41, 39));
	back.x = 12;
	back.y = 11;
	back.downstate.x = 19;
	back.downstate.y = 18;
	back.touchCallback = this;
	back.tapSound = Assets.cancel1;
	addChild(back);

	proceed = new Button(new TextureRegion(Assets.mainMenu, 966, 2, 55, 52), new TextureRegion(Assets.mainMenu, 973, 56, 41, 39));
	proceed.x = 413;
	proceed.y = 10;
	proceed.downstate.x = 420;
	proceed.downstate.y = 16;
	proceed.touchCallback = this;
	proceed.tapSound = Assets.confirm;
	addChild(proceed);

	name = new Text(Assets.poohWhiteStroked, "");
	name.x = 240;
	name.y = 244;
	name.registration = TextRegistration.CENTER;
	addChild(name);

	A = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 707, 293, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 707, 293, 29, 28)));
	A.x = 94;
	A.y = 96;
	A.downstate.x = 94;
	A.downstate.y = 93;
	A.touchCallback = this;

	a = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 710, 183, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 710, 183, 29, 28)));
	a.x = 94;
	a.y = 96;
	a.downstate.x = 94;
	a.downstate.y = 93;
	a.visible = false;
	a.touchCallback = this;

	B = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 855, 328, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 855, 328, 29, 28)));
	B.x = 242;
	B.y = 61;
	B.downstate.x = 242;
	B.downstate.y = 58;
	B.touchCallback = this;

	b = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 858, 218, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 858, 218, 29, 28)));
	b.x = 242;
	b.y = 61;
	b.downstate.x = 242;
	b.downstate.y = 58;
	b.visible = false;
	b.touchCallback = this;

	C = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 787, 328, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 787, 328, 29, 28)));
	C.x = 174;
	C.y = 61;
	C.downstate.x = 174;
	C.downstate.y = 58;
	C.touchCallback = this;

	c = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 790, 218, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 790, 218, 29, 28)));
	c.x = 174;
	c.y = 61;
	c.downstate.x = 174;
	c.downstate.y = 58;
	c.visible = false;
	c.touchCallback = this;

	D = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 773, 293, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 773, 293, 29, 28)));
	D.x = 160;
	D.y = 96;
	D.downstate.x = 160;
	D.downstate.y = 93;
	D.touchCallback = this;

	d = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 776, 183, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 776, 183, 29, 28)));
	d.x = 160;
	d.y = 96;
	d.downstate.x = 160;
	d.downstate.y = 93;
	d.visible = false;
	d.touchCallback = this;

	E = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 758, 259, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 758, 259, 29, 28)));
	E.x = 145;
	E.y = 130;
	E.downstate.x = 145;
	E.downstate.y = 127;
	E.touchCallback = this;

	e = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 761, 149, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 761, 149, 29, 28)));
	e.x = 145;
	e.y = 130;
	e.downstate.x = 145;
	e.downstate.y = 127;
	e.visible = false;
	e.touchCallback = this;

	F = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 806, 293, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 806, 293, 29, 28)));
	F.x = 193;
	F.y = 96;
	F.downstate.x = 193;
	F.downstate.y = 93;
	F.touchCallback = this;

	f = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 809, 183, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 809, 183, 29, 28)));
	f.x = 193;
	f.y = 96;
	f.downstate.x = 193;
	f.downstate.y = 93;
	f.visible = false;
	f.touchCallback = this;

	G = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 839, 293, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 839, 293, 29, 28)));
	G.x = 226;
	G.y = 96;
	G.downstate.x = 226;
	G.downstate.y = 93;
	G.touchCallback = this;

	g = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 842, 183, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 842, 183, 29, 28)));
	g.x = 226;
	g.y = 96;
	g.downstate.x = 226;
	g.downstate.y = 93;
	g.visible = false;
	g.touchCallback = this;

	H = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 872, 293, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 872, 293, 29, 28)));
	H.x = 259;
	H.y = 96;
	H.downstate.x = 259;
	H.downstate.y = 93;
	H.touchCallback = this;

	h = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 875, 183, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 875, 183, 29, 28)));
	h.x = 259;
	h.y = 96;
	h.downstate.x = 259;
	h.downstate.y = 93;
	h.visible = false;
	h.touchCallback = this;

	I = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 919, 260, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 919, 260, 29, 28)));
	I.x = 306;
	I.y = 129;
	I.downstate.x = 306;
	I.downstate.y = 126;
	I.touchCallback = this;

	i = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 922, 150, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 922, 150, 29, 28)));
	i.x = 306;
	i.y = 129;
	i.downstate.x = 306;
	i.downstate.y = 126;
	i.visible = false;
	i.touchCallback = this;

	J = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 905, 293, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 905, 293, 29, 28)));
	J.x = 292;
	J.y = 96;
	J.downstate.x = 292;
	J.downstate.y = 93;
	J.touchCallback = this;

	j = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 908, 183, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 908, 183, 29, 28)));
	j.x = 292;
	j.y = 96;
	j.downstate.x = 292;
	j.downstate.y = 93;
	j.visible = false;
	j.touchCallback = this;

	K = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 939, 293, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 939, 293, 29, 28)));
	K.x = 326;
	K.y = 96;
	K.downstate.x = 326;
	K.downstate.y = 93;
	K.touchCallback = this;

	k = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 942, 183, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 942, 183, 29, 28)));
	k.x = 326;
	k.y = 96;
	k.downstate.x = 326;
	k.downstate.y = 93;
	k.visible = false;
	k.touchCallback = this;

	L = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 972, 293, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 972, 293, 29, 28)));
	L.x = 359;
	L.y = 96;
	L.downstate.x = 359;
	L.downstate.y = 93;
	L.touchCallback = this;

	l = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 975, 183, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 975, 183, 29, 28)));
	l.x = 359;
	l.y = 96;
	l.downstate.x = 359;
	l.downstate.y = 93;
	l.visible = false;
	l.touchCallback = this;

	M = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 924, 328, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 924, 328, 29, 28)));
	M.x = 311;
	M.y = 61;
	M.downstate.x = 311;
	M.downstate.y = 58;
	M.touchCallback = this;

	m = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 927, 218, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 927, 218, 29, 28)));
	m.x = 311;
	m.y = 61;
	m.downstate.x = 311;
	m.downstate.y = 58;
	m.visible = false;
	m.touchCallback = this;

	N = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 889, 328, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 889, 328, 29, 28)));
	N.x = 276;
	N.y = 61;
	N.downstate.x = 276;
	N.downstate.y = 58;
	N.touchCallback = this;

	n = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 892, 218, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 892, 218, 29, 28)));
	n.x = 276;
	n.y = 61;
	n.downstate.x = 276;
	n.downstate.y = 58;
	n.visible = false;
	n.touchCallback = this;

	O = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 951, 260, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 951, 260, 29, 28)));
	O.x = 338;
	O.y = 129;
	O.downstate.x = 338;
	O.downstate.y = 126;
	O.touchCallback = this;

	o = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 954, 150, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 954, 150, 29, 28)));
	o.x = 338;
	o.y = 129;
	o.downstate.x = 338;
	o.downstate.y = 126;
	o.visible = false;
	o.touchCallback = this;

	P = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 983, 260, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 983, 260, 29, 28)));
	P.x = 370;
	P.y = 129;
	P.downstate.x = 370;
	P.downstate.y = 126;
	P.touchCallback = this;

	p = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 986, 150, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 986, 150, 29, 28)));
	p.x = 370;
	p.y = 129;
	p.downstate.x = 370;
	p.downstate.y = 126;
	p.visible = false;
	p.touchCallback = this;

	Q = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 694, 259, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 694, 259, 29, 28)));
	Q.x = 81;
	Q.y = 130;
	Q.downstate.x = 81;
	Q.downstate.y = 127;
	Q.touchCallback = this;

	q = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 697, 149, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 697, 149, 29, 28)));
	q.x = 81;
	q.y = 130;
	q.downstate.x = 81;
	q.downstate.y = 127;
	q.visible = false;
	q.touchCallback = this;

	R = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 790, 259, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 790, 259, 29, 28)));
	R.x = 177;
	R.y = 130;
	R.downstate.x = 177;
	R.downstate.y = 127;
	R.touchCallback = this;

	r = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 793, 149, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 793, 149, 29, 28)));
	r.x = 177;
	r.y = 130;
	r.downstate.x = 177;
	r.downstate.y = 127;
	r.visible = false;
	r.touchCallback = this;

	S = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 740, 293, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 740, 293, 29, 28)));
	S.x = 127;
	S.y = 96;
	S.downstate.x = 127;
	S.downstate.y = 93;
	S.touchCallback = this;

	s = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 743, 183, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 743, 183, 29, 28)));
	s.x = 127;
	s.y = 96;
	s.downstate.x = 127;
	s.downstate.y = 93;
	s.visible = false;
	s.touchCallback = this;

	T = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 822, 259, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 822, 259, 29, 28)));
	T.x = 209;
	T.y = 130;
	T.downstate.x = 209;
	T.downstate.y = 127;
	T.touchCallback = this;

	t = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 825, 149, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 825, 149, 29, 28)));
	t.x = 209;
	t.y = 130;
	t.downstate.x = 209;
	t.downstate.y = 127;
	t.visible = false;
	t.touchCallback = this;

	U = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 886, 259, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 889, 259, 29, 28)));
	U.x = 273;
	U.y = 130;
	U.downstate.x = 273;
	U.downstate.y = 127;
	U.touchCallback = this;

	u = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 889, 149, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 889, 149, 29, 28)));
	u.x = 273;
	u.y = 130;
	u.downstate.x = 273;
	u.downstate.y = 127;
	u.visible = false;
	u.touchCallback = this;

	V = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 821, 328, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 821, 328, 29, 28)));
	V.x = 208;
	V.y = 61;
	V.downstate.x = 208;
	V.downstate.y = 58;
	V.touchCallback = this;

	v = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 824, 218, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 824, 218, 29, 28)));
	v.x = 208;
	v.y = 61;
	v.downstate.x = 208;
	v.downstate.y = 58;
	v.visible = false;
	v.touchCallback = this;

	W = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 726, 259, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 726, 259, 29, 28)));
	W.x = 113;
	W.y = 130;
	W.downstate.x = 113;
	W.downstate.y = 127;
	W.touchCallback = this;

	w = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 729, 149, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 729, 149, 29, 28)));
	w.x = 113;
	w.y = 130;
	w.downstate.x = 113;
	w.downstate.y = 127;
	w.visible = false;
	w.touchCallback = this;

	X = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 754, 328, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 754, 328, 29, 28)));
	X.x = 141;
	X.y = 61;
	X.downstate.x = 141;
	X.downstate.y = 58;
	X.touchCallback = this;

	x = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 757, 218, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 757, 218, 29, 28)));
	x.x = 141;
	x.y = 61;
	x.downstate.x = 141;
	x.downstate.y = 58;
	x.visible = false;
	x.touchCallback = this;

	Y = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 854, 259, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 854, 259, 29, 28)));
	Y.x = 241;
	Y.y = 130;
	Y.downstate.x = 241;
	Y.downstate.y = 127;
	Y.touchCallback = this;

	y = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 857, 149, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 857, 149, 29, 28)));
	y.x = 241;
	y.y = 130;
	y.downstate.x = 241;
	y.downstate.y = 127;
	y.visible = false;
	y.touchCallback = this;

	capsLock = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 690, 218, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 690, 218, 29, 28)));
	capsLock.x = 74;
	capsLock.y = 61;
	capsLock.downstate.x = 74;
	capsLock.downstate.y = 58;
	capsLock.touchCallback = this;

	Z = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 721, 328, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 721, 328, 29, 28)));
	Z.x = 108;
	Z.y = 61;
	Z.downstate.x = 108;
	Z.downstate.y = 58;
	Z.touchCallback = this;

	z = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 724, 218, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 724, 218, 29, 28)));
	z.x = 108;
	z.y = 61;
	z.downstate.x = 108;
	z.downstate.y = 58;
	z.visible = false;
	z.touchCallback = this;

	one = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 697, 116, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 697, 116, 29, 28)));
	one.x = 81;
	one.y = 163;
	one.downstate.x = 81;
	one.downstate.y = 160;
	one.touchCallback = this;

	two = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 729, 116, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 729, 116, 29, 28)));
	two.x = 113;
	two.y = 163;
	two.downstate.x = 113;
	two.downstate.y = 160;
	two.touchCallback = this;

	three = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 761, 116, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 761, 116, 29, 28)));
	three.x = 145;
	three.y = 163;
	three.downstate.x = 145;
	three.downstate.y = 160;
	three.touchCallback = this;

	four = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 793, 116, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 793, 116, 29, 28)));
	four.x = 177;
	four.y = 163;
	four.downstate.x = 177;
	four.downstate.y = 160;
	four.touchCallback = this;

	five = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 826, 116, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 826, 116, 29, 28)));
	five.x = 209;
	five.y = 163;
	five.downstate.x = 209;
	five.downstate.y = 160;
	five.touchCallback = this;

	six = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 857, 116, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 857, 116, 29, 28)));
	six.x = 241;
	six.y = 163;
	six.downstate.x = 241;
	six.downstate.y = 160;
	six.touchCallback = this;

	seven = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 889, 116, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 889, 116, 29, 28)));
	seven.x = 273;
	seven.y = 163;
	seven.downstate.x = 273;
	seven.downstate.y = 160;
	seven.touchCallback = this;

	eight = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 921, 116, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 921, 116, 29, 28)));
	eight.x = 305;
	eight.y = 163;
	eight.downstate.x = 305;
	eight.downstate.y = 160;
	eight.touchCallback = this;

	nine = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 953, 116, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 953, 116, 29, 28)));
	nine.x = 337;
	nine.y = 163;
	nine.downstate.x = 337;
	nine.downstate.y = 160;
	nine.touchCallback = this;

	zero = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 985, 116, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 985, 116, 29, 28)));
	zero.x = 369;
	zero.y = 163;
	zero.downstate.x = 369;
	zero.downstate.y = 160;
	zero.touchCallback = this;

	space = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 961, 218, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 961, 218, 29, 28)));
	space.x = 345;
	space.y = 61;
	space.downstate.x = 345;
	space.downstate.y = 58;
	space.touchCallback = this;

	delete = new Button(new Sprite(new TextureRegion(Assets.mainMenu, 995, 218, 29, 28)), new Sprite(new TextureRegion(Assets.mainMenu, 995, 218, 29, 28)));
	delete.x = 379;
	delete.y = 61;
	delete.downstate.x = 379;
	delete.downstate.y = 58;
	delete.touchCallback = this;

	addChild(one);
	addChild(two);
	addChild(three);
	addChild(four);
	addChild(five);
	addChild(six);
	addChild(seven);
	addChild(eight);
	addChild(nine);
	addChild(zero);
	addChild(P);
	addChild(p);
	addChild(O);
	addChild(o);
	addChild(I);
	addChild(i);
	addChild(U);
	addChild(u);
	addChild(Y);
	addChild(y);
	addChild(T);
	addChild(t);
	addChild(R);
	addChild(r);
	addChild(E);
	addChild(e);
	addChild(W);
	addChild(w);
	addChild(Q);
	addChild(q);
	addChild(A);
	addChild(a);
	addChild(S);
	addChild(s);
	addChild(D);
	addChild(d);
	addChild(F);
	addChild(f);
	addChild(G);
	addChild(g);
	addChild(H);
	addChild(h);
	addChild(J);
	addChild(j);
	addChild(K);
	addChild(k);
	addChild(L);
	addChild(l);
	addChild(delete);
	addChild(space);
	addChild(M);
	addChild(m);
	addChild(N);
	addChild(n);
	addChild(B);
	addChild(b);
	addChild(V);
	addChild(v);
	addChild(C);
	addChild(c);
	addChild(X);
	addChild(x);
	addChild(Z);
	addChild(z);
	addChild(capsLock);

	if (tweenNavigation) {
	    back.x = 0 - back.width;
	    back.move(new VectorCoords(12, 11), Back.OUT, 1000, 0, false);
	    proceed.x = 480 + proceed.width;
	    proceed.move(new VectorCoords(413, 10), Back.OUT, 1000, 0, false);
	}

	label.y += 20;
	label.alpha = 0f;
	label.move(149, 276, label.scaleX, label.scaleY, label.rotation, 1f, Quart.OUT, 1000, 0, false);

	textBox.x = textBox.x + (textBox.width - textBox.width * 0.2f) / 2;
	textBox.x = textBox.y + (textBox.height - textBox.height * 0.2f) / 2;
	textBox.alpha = 0f;
	textBox.scaleX = 0.2f;
	textBox.scaleY = 0.2f;
	textBox.move(36, 195, 1.0f, 1.0f, textBox.rotation, 1f, Back.OUT, 1000, 0, false);

	int delay = 0;
	for (int index = 5; index < displayObjects.size(); index++) {
	    float originalX = displayObjects.get(index).x;
	    float originalY = displayObjects.get(index).y;
	    displayObjects.get(index).x = displayObjects.get(index).x + (displayObjects.get(index).width - displayObjects.get(index).width * 0.2f) / 2;
	    displayObjects.get(index).x = displayObjects.get(index).y + (displayObjects.get(index).height - displayObjects.get(index).height * 0.2f) / 2;
	    displayObjects.get(index).alpha = 0f;
	    displayObjects.get(index).scaleX = 0.2f;
	    displayObjects.get(index).scaleY = 0.2f;
	    displayObjects.get(index).move(originalX, originalY, 1.0f, 1.0f, displayObjects.get(index).rotation, 1f, Back.OUT, 250, delay, false);
	    delay += 15;
	}
    }

    private void toggleLetters() {
	a.visible = !a.visible;
	b.visible = !b.visible;
	c.visible = !c.visible;
	d.visible = !d.visible;
	e.visible = !e.visible;
	f.visible = !f.visible;
	g.visible = !g.visible;
	h.visible = !h.visible;
	i.visible = !i.visible;
	j.visible = !j.visible;
	k.visible = !k.visible;
	l.visible = !l.visible;
	m.visible = !m.visible;
	n.visible = !n.visible;
	o.visible = !o.visible;
	p.visible = !p.visible;
	q.visible = !q.visible;
	r.visible = !r.visible;
	s.visible = !s.visible;
	t.visible = !t.visible;
	u.visible = !u.visible;
	v.visible = !v.visible;
	w.visible = !w.visible;
	x.visible = !x.visible;
	y.visible = !y.visible;
	z.visible = !z.visible;

	A.visible = !A.visible;
	B.visible = !B.visible;
	C.visible = !C.visible;
	D.visible = !D.visible;
	E.visible = !E.visible;
	F.visible = !F.visible;
	G.visible = !G.visible;
	H.visible = !H.visible;
	I.visible = !I.visible;
	J.visible = !J.visible;
	K.visible = !K.visible;
	L.visible = !L.visible;
	M.visible = !M.visible;
	N.visible = !N.visible;
	O.visible = !O.visible;
	P.visible = !P.visible;
	Q.visible = !Q.visible;
	R.visible = !R.visible;
	S.visible = !S.visible;
	T.visible = !T.visible;
	U.visible = !U.visible;
	V.visible = !V.visible;
	W.visible = !W.visible;
	X.visible = !X.visible;
	Y.visible = !Y.visible;
	Z.visible = !Z.visible;
    }

}
