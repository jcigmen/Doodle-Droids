package org.nullsys.androidgames.doodledroids;

import org.nullsys.androidgames.framework.Music;
import org.nullsys.androidgames.framework.Sound;
import org.nullsys.androidgames.framework.display.Texture;
import org.nullsys.androidgames.framework.display.text.AlphabetFont;
import org.nullsys.androidgames.framework.display.text.NumberFont;
import org.nullsys.androidgames.framework.impl.GLGame;

public class Assets {

    public static GLGame game;

    public static Texture splash;
    public static Texture mainMenu;
    public static Texture dashboardTabs;
    public static Texture fonts;

    public static Texture sundotKulangotWhite;
    public static Texture sundotKulangotYellow;
    public static Texture sundotKulangotRed;
    public static Texture sundotKulangotBlue;
    public static Texture sundotKulangotGreen;

    public static Texture yemaWhite;
    public static Texture yemaYellow;
    public static Texture yemaRed;
    public static Texture yemaBlue;
    public static Texture yemaGreen;

    public static Texture pastillasWhite;
    public static Texture pastillasYellow;
    public static Texture pastillasRed;
    public static Texture pastillasBlue;
    public static Texture pastillasGreen;

    public static Texture models;
    public static Texture items1;
    public static Texture stage;
    public static Texture quest;
    public static Texture worldMap;

    public static AlphabetFont poohWhiteStroked;
    public static AlphabetFont poohBlackFont;
    public static AlphabetFont poohGreenFont;
    public static AlphabetFont poohRedFont;
    public static AlphabetFont mc10;
    public static NumberFont questEnergyFont;
    public static NumberFont questHealthFont;
    public static NumberFont questExpFont;
    public static NumberFont levelFont;
    public static NumberFont droillarsFont;
    public static NumberFont worldTimeFont;

    public static Sound confirm;
    public static Sound press;
    public static Sound cancel1;
    public static Sound cancel2;
    public static Sound notify;
    public static Sound tap;
    public static Sound buySell;
    public static Sound levelUp;

    public static Music mainMenuBGM;
    public static Music stageBGM;
    public static Music worldBGM;
    public static Music shopBGM;

    public static void load() {
	models = new Texture(game, "gfx/models.png");
	splash = new Texture(game, "gfx/splash.png");
	mainMenu = new Texture(game, "gfx/main_menu.png");
	dashboardTabs = new Texture(game, "gfx/dashboard_tabs.png");

	confirm = game.getAudio().newSound("sfx/ok.ogg");
	press = game.getAudio().newSound("sfx/press.ogg");
	cancel1 = game.getAudio().newSound("sfx/cancel.ogg");
	cancel2 = game.getAudio().newSound("sfx/cancel2.ogg");
	notify = game.getAudio().newSound("sfx/notify.ogg");
	tap = game.getAudio().newSound("sfx/tap.ogg");
	buySell = game.getAudio().newSound("sfx/buysell.ogg");
	levelUp = game.getAudio().newSound("sfx/levelup.ogg");

	mainMenuBGM = game.getAudio().newMusic("sfx/mainmenu.mid");
	mainMenuBGM.setLooping(true);
	mainMenuBGM.setVolume(.5f);
	stageBGM = game.getAudio().newMusic("sfx/stage.mid");
	stageBGM.setLooping(true);
	stageBGM.setVolume(.5f);
	worldBGM = game.getAudio().newMusic("sfx/worldmap.mid");
	worldBGM.setLooping(true);
	worldBGM.setVolume(.5f);
	shopBGM = game.getAudio().newMusic("sfx/shop.mid");
	shopBGM.setLooping(true);
	shopBGM.setVolume(.5f);
    }

    public static void loadFonts() {
	fonts = new Texture(game, "gfx/fonts.png");

	poohWhiteStroked = new AlphabetFont();
	poohWhiteStroked.A = .75f;
	poohWhiteStroked.B = .76f;
	poohWhiteStroked.C = .76f;
	poohWhiteStroked.D = .76f;
	poohWhiteStroked.E = .76f;
	poohWhiteStroked.F = .72f;
	poohWhiteStroked.G = .88f;
	poohWhiteStroked.I = .4f;
	poohWhiteStroked.J = .68f;
	poohWhiteStroked.K = .76f;
	poohWhiteStroked.L = .6f;
	poohWhiteStroked.N = .84f;
	poohWhiteStroked.O = .88f;
	poohWhiteStroked.P = .76f;
	poohWhiteStroked.Q = .84f;
	poohWhiteStroked.R = .76f;
	poohWhiteStroked.S = .72f;
	poohWhiteStroked.U = .76f;
	poohWhiteStroked.V = .84f;
	poohWhiteStroked.X = .92f;
	poohWhiteStroked.Y = .84f;
	poohWhiteStroked.Z = .84f;
	poohWhiteStroked.a = .76f;
	poohWhiteStroked.b = .8f;
	poohWhiteStroked.c = .8f;
	poohWhiteStroked.d = .8f;
	poohWhiteStroked.e = .8f;
	poohWhiteStroked.f = .68f;
	poohWhiteStroked.g = .76f;
	poohWhiteStroked.h = .76f;
	poohWhiteStroked.i = .44f;
	poohWhiteStroked.j = .56f;
	poohWhiteStroked.k = .64f;
	poohWhiteStroked.l = .4f;
	poohWhiteStroked.m = 1f;
	poohWhiteStroked.n = .76f;
	poohWhiteStroked.o = .76f;
	poohWhiteStroked.p = .76f;
	poohWhiteStroked.q = .76f;
	poohWhiteStroked.r = .68f;
	poohWhiteStroked.s = .68f;
	poohWhiteStroked.t = .68f;
	poohWhiteStroked.u = .76f;
	poohWhiteStroked.v = .8f;
	poohWhiteStroked.w = 1f;
	poohWhiteStroked.x = .76f;
	poohWhiteStroked.y = .76f;
	poohWhiteStroked.z = .8f;
	poohWhiteStroked.one = .56f;
	poohWhiteStroked.two = .76f;
	poohWhiteStroked.three = .76f;
	poohWhiteStroked.four = .8f;
	poohWhiteStroked.five = .76f;
	poohWhiteStroked.six = .76f;
	poohWhiteStroked.seven = .76f;
	poohWhiteStroked.eight = .8f;
	poohWhiteStroked.nine = .76f;
	poohWhiteStroked.zero = .8f;
	poohWhiteStroked.exclamation = .4f;
	poohWhiteStroked.question = .72f;
	poohWhiteStroked.dot = .44f;
	poohWhiteStroked.coma = .36f;
	poohWhiteStroked.dash = .64f;
	poohWhiteStroked.colon = .44f;
	poohWhiteStroked.set(fonts, 0, 0, 25, 32, 41);

	levelFont = new NumberFont();
	levelFont.one = .7f;
	levelFont.two = .91f;
	levelFont.three = .91f;
	levelFont.four = 1f;
	levelFont.five = .91f;
	levelFont.six = .91f;
	levelFont.seven = .95f;
	levelFont.eight = .95f;
	levelFont.nine = .91f;
	levelFont.zero = .95f;
	levelFont.exclamation = .37f;
	levelFont.question = .91f;
	levelFont.dot = .37f;
	levelFont.coma = .41f;
	levelFont.colon = .37f;
	levelFont.plus = .95f;
	levelFont.dash = .62f;
	levelFont.set(fonts, 0, 64, 24, 37, 17);

	droillarsFont = new NumberFont();
	droillarsFont.one = 1f;
	droillarsFont.two = .88f;
	droillarsFont.three = 1f;
	droillarsFont.four = .77f;
	droillarsFont.five = .83f;
	droillarsFont.six = .83f;
	droillarsFont.seven = .94f;
	droillarsFont.eight = .94f;
	droillarsFont.nine = .83f;
	droillarsFont.zero = .88f;
	droillarsFont.exclamation = .38f;
	droillarsFont.question = .83f;
	droillarsFont.dot = .38f;
	droillarsFont.coma = .5f;
	droillarsFont.dash = .72f;
	droillarsFont.colon = .33f;
	droillarsFont.plus = .88f;
	droillarsFont.set(fonts, 0, 101, 18, 40, 17);

	poohBlackFont = new AlphabetFont();
	poohBlackFont.A = .75f;
	poohBlackFont.B = .76f;
	poohBlackFont.C = .76f;
	poohBlackFont.D = .76f;
	poohBlackFont.E = .76f;
	poohBlackFont.F = .72f;
	poohBlackFont.G = .88f;
	poohBlackFont.I = .4f;
	poohBlackFont.J = .68f;
	poohBlackFont.K = .76f;
	poohBlackFont.L = .6f;
	poohBlackFont.N = .84f;
	poohBlackFont.O = .88f;
	poohBlackFont.P = .76f;
	poohBlackFont.Q = .84f;
	poohBlackFont.R = .76f;
	poohBlackFont.S = .72f;
	poohBlackFont.U = .76f;
	poohBlackFont.V = .84f;
	poohBlackFont.X = .92f;
	poohBlackFont.Y = .84f;
	poohBlackFont.Z = .84f;
	poohBlackFont.a = .76f;
	poohBlackFont.b = .8f;
	poohBlackFont.c = .8f;
	poohBlackFont.d = .8f;
	poohBlackFont.e = .8f;
	poohBlackFont.f = .68f;
	poohBlackFont.g = .76f;
	poohBlackFont.h = .76f;
	poohBlackFont.i = .44f;
	poohBlackFont.j = .56f;
	poohBlackFont.k = .64f;
	poohBlackFont.l = .4f;
	poohBlackFont.m = 1f;
	poohBlackFont.n = .76f;
	poohBlackFont.o = .76f;
	poohBlackFont.p = .76f;
	poohBlackFont.q = .76f;
	poohBlackFont.r = .68f;
	poohBlackFont.s = .68f;
	poohBlackFont.t = .68f;
	poohBlackFont.u = .76f;
	poohBlackFont.v = .8f;
	poohBlackFont.w = 1f;
	poohBlackFont.x = .76f;
	poohBlackFont.y = .76f;
	poohBlackFont.z = .8f;
	poohBlackFont.one = .56f;
	poohBlackFont.two = .76f;
	poohBlackFont.three = .76f;
	poohBlackFont.four = .8f;
	poohBlackFont.five = .76f;
	poohBlackFont.six = .76f;
	poohBlackFont.seven = .76f;
	poohBlackFont.eight = .8f;
	poohBlackFont.nine = .76f;
	poohBlackFont.zero = .8f;
	poohBlackFont.exclamation = .4f;
	poohBlackFont.question = .72f;
	poohBlackFont.dot = .44f;
	poohBlackFont.coma = .36f;
	poohBlackFont.dash = .64f;
	poohBlackFont.colon = .44f;
	poohBlackFont.set(fonts, 0, 141, 25, 32, 41);

	poohGreenFont = new AlphabetFont();
	poohGreenFont.A = .75f;
	poohGreenFont.B = .76f;
	poohGreenFont.C = .76f;
	poohGreenFont.D = .76f;
	poohGreenFont.E = .76f;
	poohGreenFont.F = .72f;
	poohGreenFont.G = .88f;
	poohGreenFont.I = .4f;
	poohGreenFont.J = .68f;
	poohGreenFont.K = .76f;
	poohGreenFont.L = .6f;
	poohGreenFont.N = .84f;
	poohGreenFont.O = .88f;
	poohGreenFont.P = .76f;
	poohGreenFont.Q = .84f;
	poohGreenFont.R = .76f;
	poohGreenFont.S = .72f;
	poohGreenFont.U = .76f;
	poohGreenFont.V = .84f;
	poohGreenFont.X = .92f;
	poohGreenFont.Y = .84f;
	poohGreenFont.Z = .84f;
	poohGreenFont.a = .76f;
	poohGreenFont.b = .8f;
	poohGreenFont.c = .8f;
	poohGreenFont.d = .8f;
	poohGreenFont.e = .8f;
	poohGreenFont.f = .68f;
	poohGreenFont.g = .76f;
	poohGreenFont.h = .76f;
	poohGreenFont.i = .44f;
	poohGreenFont.j = .56f;
	poohGreenFont.k = .64f;
	poohGreenFont.l = .4f;
	poohGreenFont.m = 1f;
	poohGreenFont.n = .76f;
	poohGreenFont.o = .76f;
	poohGreenFont.p = .76f;
	poohGreenFont.q = .76f;
	poohGreenFont.r = .68f;
	poohGreenFont.s = .68f;
	poohGreenFont.t = .68f;
	poohGreenFont.u = .76f;
	poohGreenFont.v = .8f;
	poohGreenFont.w = 1f;
	poohGreenFont.x = .76f;
	poohGreenFont.y = .76f;
	poohGreenFont.z = .8f;
	poohGreenFont.one = .56f;
	poohGreenFont.two = .76f;
	poohGreenFont.three = .76f;
	poohGreenFont.four = .8f;
	poohGreenFont.five = .76f;
	poohGreenFont.six = .76f;
	poohGreenFont.seven = .76f;
	poohGreenFont.eight = .8f;
	poohGreenFont.nine = .76f;
	poohGreenFont.zero = .8f;
	poohGreenFont.exclamation = .4f;
	poohGreenFont.question = .72f;
	poohGreenFont.dot = .44f;
	poohGreenFont.coma = .36f;
	poohGreenFont.dash = .64f;
	poohGreenFont.colon = .44f;
	poohGreenFont.set(fonts, 0, 323, 25, 32, 41);

	poohRedFont = new AlphabetFont();
	poohRedFont.A = .75f;
	poohRedFont.B = .76f;
	poohRedFont.C = .76f;
	poohRedFont.D = .76f;
	poohRedFont.E = .76f;
	poohRedFont.F = .72f;
	poohRedFont.G = .88f;
	poohRedFont.I = .4f;
	poohRedFont.J = .68f;
	poohRedFont.K = .76f;
	poohRedFont.L = .6f;
	poohRedFont.N = .84f;
	poohRedFont.O = .88f;
	poohRedFont.P = .76f;
	poohRedFont.Q = .84f;
	poohRedFont.R = .76f;
	poohRedFont.S = .72f;
	poohRedFont.U = .76f;
	poohRedFont.V = .84f;
	poohRedFont.X = .92f;
	poohRedFont.Y = .84f;
	poohRedFont.Z = .84f;
	poohRedFont.a = .76f;
	poohRedFont.b = .8f;
	poohRedFont.c = .8f;
	poohRedFont.d = .8f;
	poohRedFont.e = .8f;
	poohRedFont.f = .68f;
	poohRedFont.g = .76f;
	poohRedFont.h = .76f;
	poohRedFont.i = .44f;
	poohRedFont.j = .56f;
	poohRedFont.k = .64f;
	poohRedFont.l = .4f;
	poohRedFont.m = 1f;
	poohRedFont.n = .76f;
	poohRedFont.o = .76f;
	poohRedFont.p = .76f;
	poohRedFont.q = .76f;
	poohRedFont.r = .68f;
	poohRedFont.s = .68f;
	poohRedFont.t = .68f;
	poohRedFont.u = .76f;
	poohRedFont.v = .8f;
	poohRedFont.w = 1f;
	poohRedFont.x = .76f;
	poohRedFont.y = .76f;
	poohRedFont.z = .8f;
	poohRedFont.one = .56f;
	poohRedFont.two = .76f;
	poohRedFont.three = .76f;
	poohRedFont.four = .8f;
	poohRedFont.five = .76f;
	poohRedFont.six = .76f;
	poohRedFont.seven = .76f;
	poohRedFont.eight = .8f;
	poohRedFont.nine = .76f;
	poohRedFont.zero = .8f;
	poohRedFont.exclamation = .4f;
	poohRedFont.question = .72f;
	poohRedFont.dot = .44f;
	poohRedFont.coma = .36f;
	poohRedFont.dash = .64f;
	poohRedFont.colon = .44f;
	poohRedFont.set(fonts, 0, 385, 25, 32, 41);

	questExpFont = new NumberFont();
	questExpFont.one = 1f;
	questExpFont.two = .88f;
	questExpFont.three = 1f;
	questExpFont.four = .77f;
	questExpFont.five = .83f;
	questExpFont.six = .83f;
	questExpFont.seven = .94f;
	questExpFont.eight = .94f;
	questExpFont.nine = .83f;
	questExpFont.zero = .88f;
	questExpFont.exclamation = .38f;
	questExpFont.question = .83f;
	questExpFont.dot = .38f;
	questExpFont.coma = .5f;
	questExpFont.dash = .72f;
	questExpFont.colon = .33f;
	questExpFont.plus = .88f;
	questExpFont.set(fonts, 0, 203, 18, 40, 17);

	questHealthFont = new NumberFont();
	questHealthFont.one = 1f;
	questHealthFont.two = .88f;
	questHealthFont.three = 1f;
	questHealthFont.four = .77f;
	questHealthFont.five = .83f;
	questHealthFont.six = .83f;
	questHealthFont.seven = .94f;
	questHealthFont.eight = .94f;
	questHealthFont.nine = .83f;
	questHealthFont.zero = .88f;
	questHealthFont.exclamation = .38f;
	questHealthFont.question = .83f;
	questHealthFont.dot = .38f;
	questHealthFont.coma = .5f;
	questHealthFont.dash = .72f;
	questHealthFont.colon = .33f;
	questHealthFont.plus = .88f;
	questHealthFont.set(fonts, 0, 243, 18, 40, 17);

	questEnergyFont = new NumberFont();
	questEnergyFont.one = 1f;
	questEnergyFont.two = .88f;
	questEnergyFont.three = 1f;
	questEnergyFont.four = .77f;
	questEnergyFont.five = .83f;
	questEnergyFont.six = .83f;
	questEnergyFont.seven = .94f;
	questEnergyFont.eight = .94f;
	questEnergyFont.nine = .83f;
	questEnergyFont.zero = .88f;
	questEnergyFont.exclamation = .38f;
	questEnergyFont.question = .83f;
	questEnergyFont.dot = .38f;
	questEnergyFont.coma = .5f;
	questEnergyFont.dash = .72f;
	questEnergyFont.colon = .33f;
	questEnergyFont.plus = .88f;
	questEnergyFont.set(fonts, 0, 283, 18, 40, 17);

	worldTimeFont = new NumberFont();
	worldTimeFont.one = .68f;
	worldTimeFont.seven = .87f;
	worldTimeFont.nine = .87f;
	worldTimeFont.exclamation = .31f;
	worldTimeFont.question = .81f;
	worldTimeFont.dot = .31f;
	worldTimeFont.coma = .31f;
	worldTimeFont.dash = .5f;
	worldTimeFont.colon = .31f;
	worldTimeFont.plus = .31f;
	worldTimeFont.set(fonts, 0, 447, 16, 15, 17);

	mc10 = new AlphabetFont(fonts, 0, 214, 10, 10, 26);
    }

    public static void loadPastillasDroids() {
	pastillasWhite = new Texture(Assets.game, "gfx/droids/pastillas/pastillas1white.png");
	pastillasYellow = new Texture(Assets.game, "gfx/droids/pastillas/pastillas1yellow.png");
	pastillasRed = new Texture(Assets.game, "gfx/droids/pastillas/pastillas1red.png");
	pastillasBlue = new Texture(Assets.game, "gfx/droids/pastillas/pastillas1blue.png");
	pastillasGreen = new Texture(Assets.game, "gfx/droids/pastillas/pastillas1green.png");
    }

    public static void loadSundotKulangotDroids() {
	sundotKulangotBlue = new Texture(Assets.game, "gfx/droids/sundotkulangot/sundotkulangot1blue.png");
	sundotKulangotWhite = new Texture(Assets.game, "gfx/droids/sundotkulangot/sundotkulangot1white.png");
	sundotKulangotYellow = new Texture(Assets.game, "gfx/droids/sundotkulangot/sundotkulangot1yellow.png");
	sundotKulangotRed = new Texture(Assets.game, "gfx/droids/sundotkulangot/sundotkulangot1red.png");
	sundotKulangotGreen = new Texture(Assets.game, "gfx/droids/sundotkulangot/sundotkulangot1green.png");
    }

    public static void loadWorld() {
	items1 = new Texture(game, "gfx/items1.png");
	stage = new Texture(game, "gfx/stage.png");
	quest = new Texture(game, "gfx/quest.png");
	worldMap = new Texture(game, "gfx/world_map.png");
    }

    public static void loadYemaDroids() {
	yemaWhite = new Texture(Assets.game, "gfx/droids/yema/yema1white.png");
	yemaYellow = new Texture(Assets.game, "gfx/droids/yema/yema1yellow.png");
	yemaRed = new Texture(Assets.game, "gfx/droids/yema/yema1red.png");
	yemaBlue = new Texture(Assets.game, "gfx/droids/yema/yema1blue.png");
	yemaGreen = new Texture(Assets.game, "gfx/droids/yema/yema1green.png");
    }

    public static void playSound(Sound sound) {
	if (Settings.soundEnabled)
	    sound.play(1);
    }

    public static void reload() {
	splash.reload();
	mainMenu.reload();
	dashboardTabs.reload();
	fonts.reload();
	items1.reload();
	stage.reload();
	quest.reload();
	worldMap.reload();
	models.reload();
	pastillasWhite.reload();
	pastillasYellow.reload();
	pastillasRed.reload();
	pastillasBlue.reload();
	pastillasGreen.reload();
	sundotKulangotBlue.reload();
	sundotKulangotWhite.reload();
	sundotKulangotYellow.reload();
	sundotKulangotRed.reload();
	sundotKulangotGreen.reload();
	yemaWhite.reload();
	yemaYellow.reload();
	yemaRed.reload();
	yemaBlue.reload();
	yemaGreen.reload();
    }

    public static void unload() {
	try {
	    splash.dispose();
	    mainMenu.dispose();
	    dashboardTabs.dispose();
	    fonts.dispose();
	    items1.dispose();
	    stage.dispose();
	    quest.dispose();
	    worldMap.dispose();
	    unloadPastillas();
	    unloadSundotKulangotDroids();
	    unloadYemaDroids();
	} catch (NullPointerException e) {
	}
    }

    public static void unloadPastillas() {
	try {
	    pastillasWhite.dispose();
	    pastillasYellow.dispose();
	    pastillasRed.dispose();
	    pastillasBlue.dispose();
	    pastillasGreen.dispose();
	} catch (NullPointerException e) {
	}
    }

    public static void unloadSundotKulangotDroids() {
	try {
	    sundotKulangotBlue.dispose();
	    sundotKulangotWhite.dispose();
	    sundotKulangotYellow.dispose();
	    sundotKulangotRed.dispose();
	    sundotKulangotGreen.dispose();
	} catch (NullPointerException e) {
	}
    }

    public static void unloadYemaDroids() {
	try {
	    yemaWhite.dispose();
	    yemaYellow.dispose();
	    yemaRed.dispose();
	    yemaBlue.dispose();
	    yemaGreen.dispose();
	} catch (NullPointerException e) {
	}
    }
}
