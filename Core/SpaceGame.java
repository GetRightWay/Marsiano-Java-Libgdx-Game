package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceGame extends Game {
 public	SpriteBatch batch;
 public static final int Virtual_W=550;
 public static final int Virtual_H=250;
 public static final  float PPM=50;
	public static final short SpaceManBit=2;
	public static  final short DefaultBut=1;
	public static final short LuckyBlockBit=4;
	public static final short DesTroyedBit=8;
    public  final  static  short missleBit =16;
    public final static short Item_Bit=32;
    public final static short GroundEnemysBit=64;
    public final static short Bullet_Bit=128;
    public final static short AirVamPirBit=256;
    public final static short GroundVampir=512;
    public final static short SwordBulletBit=1024;
    public final static short ArmaGedBit=2048;

	public static AssetManager manager;

	@Override
	public void create () {
		manager=new AssetManager();
		batch = new SpriteBatch();
		manager.load("sounds/Musics/Run.mp3", Music.class);
		manager.load("sounds/SoundEffects/firstStep.wav", Sound.class);
		manager.load("sounds/SoundEffects/Step2.wav", Sound.class);
		manager.load("sounds/SoundEffects/BreakSound.wav", Sound.class);
		manager.load("sounds/SoundEffects/flyby.wav", Sound.class);
		manager.load("sounds/SoundEffects/Jetpack_End.wav", Sound.class);
		manager.load("sounds/SoundEffects/Shooting.wav", Sound.class);
		manager.load("sounds/SoundEffects/Sword.wav", Sound.class);
		manager.load("sounds/SoundEffects/Alartnes.wav", Sound.class);
		manager.load("sounds/SoundEffects/Bomb+3.wav", Sound.class);
		manager.load("sounds/SoundEffects/ShootSound.wav", Sound.class);
		manager.load("sounds/SoundEffects/ExplodeShound.wav", Sound.class);
		manager.load("sounds/SoundEffects/Death.mp3", Sound.class);
	//	manager.load("sounds/SoundEffects/Ice.wav", Sound.class);
		manager.finishLoading();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
	super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
