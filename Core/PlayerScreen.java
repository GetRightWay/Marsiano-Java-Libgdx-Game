package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.mygdx.game.Enemys.GroundEnemy;
import com.mygdx.game.HittingTools.FreezBullet;
import com.mygdx.game.HittingTools.SwordBullet;
import com.mygdx.game.Items.ArmagedonRocket;
import com.mygdx.game.Items.Ice_Cube;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.ItemDefenesions;
import com.mygdx.game.Items.Missle;
import com.mygdx.game.Items.getAccesToBullerss;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Sprites.LuckyBlock;
import com.mygdx.game.Sprites.SpaceMan;
import com.mygdx.game.Tools.Box2DCreat;
import com.mygdx.game.Tools.WorldConctactListener;

import java.util.concurrent.LinkedBlockingQueue;

import static com.mygdx.game.SpaceGame.manager;

/**
 * Created by harut on 08.03.2018.
 */

public class PlayerScreen implements Screen {
Array<SwordBullet> swordBullets=new Array<SwordBullet>();
    Array<FreezBullet> freezBullets=new Array<FreezBullet>();

    SwordBullet swordBullet;
    public static boolean IsFlying;
    public static Sound JetPackStart;
    private Sound DieSound;
    private Music music;
    private float TimerToDestroy=0;
    Controller controller;
    private Array<Item> items;
    private LinkedBlockingQueue<ItemDefenesions> idef;
    SpaceGame game;
    SpaceMan spaceMan;
    private GroundEnemy groundEnemy;
    private TextureAtlas atlasHero;
    private TextureAtlas ModAtlas;
    private TextureAtlas Bull;
    private TextureAtlas ExplodeAtlas;
    private float timerForBUllets=0;
    private int GetNextBullet=0;
    private Sound MissleAlart;
    private TextureRegion SpaceManImg;
    private static OrthographicCamera camera;
    private Viewport GamePort;
    private Hud hud;
    private TmxMapLoader mapLoader;
    private TiledMap currentMap;
    private OrthoCachedTiledMapRenderer renderer;
    private AudioMusic audioMusic;
    //box2d
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
private Box2DCreat creat;
private void update(float dt) {
if(SpaceMan.IsToDie){
    JetPackStart.play();
}
        timerForBUllets += dt;
        if (timerForBUllets >= 1 && GetNextBullet <= 3) {
            GetNextBullet++;
            timerForBUllets = 0;
        }
        hud.update(dt);
        spaceMan.Update(dt);
        HandleInput(dt);
        HandleSpawn();
    for (Enemy enemy : creat.getGroundEnemies()) {
        enemy.update(dt);
    }
    for (Enemy enemy : creat.getGroundVampirs()) {
        enemy.update(dt);
    }
    for (Enemy enemy : creat.getAirVampirses()) {
        enemy.update(dt);
    }
    for(SwordBullet swordBullet:swordBullets)
        swordBullet.update(dt);
    for(FreezBullet freezBullet:freezBullets) {
        freezBullet.update(dt);
    }
    for (Item item : items)
        item.update(dt);
        world.step(1 / 60f, 4, 2);
        camera.position.x = SpaceMan.manBody.getPosition().x;
        camera.update();



        renderer.setView(camera);

    }

public TiledMap getCurrentMap(){
    return currentMap;
}
public World Getwolrd(){
    return  world;
}
public void HandleInput(float dt) {

      if (SpaceMan.state != SpaceMan.StateOfSpaceMan.Die) {
        if (controller.isAttackPressed()) {
            spaceMan.SetAttackregoin();
            TimerToDestroy += dt;
            if( Gdx.input.justTouched()) {
                swordBullet = new SwordBullet(this, SpaceMan.manBody.getPosition().x, SpaceMan.manBody.getPosition().y);
                swordBullets.add(swordBullet);
                swordBullet.DestroyBody(swordBullet.body);
            }
        } else if (controller.isJumpPressed() && SpaceMan.manBody.getLinearVelocity().y < 4 && hud.FUEL > 0) {
            spaceMan.manBody.applyLinearImpulse(new Vector2(0, 0.8f), spaceMan.manBody.getWorldCenter(), true);
            if (SpaceMan.manBody.getLinearVelocity().y > 0) {
                IsFlying = true;
            } else {
                IsFlying = false;
            }

        } else if (controller.isRightPressed() && SpaceMan.manBody.getLinearVelocity().x < 3) {
            spaceMan.manBody.applyLinearImpulse(new Vector2(0.2f, 0), spaceMan.manBody.getWorldCenter(), true);


        } else if (controller.isLeftPressed() && SpaceMan.manBody.getLinearVelocity().x > -2) {
            spaceMan.manBody.applyLinearImpulse(new Vector2(-0.1f, 0), spaceMan.manBody.getWorldCenter(), true);
        } else if(controller.isFreezeAttackedPressed() &&Gdx.input.justTouched() && LuckyBlock.FreezBulletCounts>0 && !Ice_Cube.IsUsed ){
            freezBullets.add(new FreezBullet(this,SpaceMan.manBody.getPosition().x,SpaceMan.manBody.getPosition().y));
            controller.ShootSound(FreezBullet.shootSound);
            LuckyBlock.FreezBulletCounts--;
        }

        }


    }

      public  PlayerScreen(SpaceGame game){
          IsFlying=false;
          controller=new Controller();
          music=SpaceGame.manager.get("sounds/Musics/Run.mp3", Music.class);
          JetPackStart=SpaceGame.manager.get("sounds/SoundEffects/flyby.wav", Sound.class);
          music.setLooping(true);
          music.setVolume(0.3f);
          music.play();
         atlasHero=new TextureAtlas("Hero_Enemy/Hero_Enemy.pack");
          ModAtlas=new TextureAtlas("SpritesForGame/Mod_Pack.pack");
          ExplodeAtlas=new TextureAtlas("Exp.pack");
          Bull=new TextureAtlas("SpritesForGame/BulletPack.pack");
          DieSound=SpaceGame.manager.get("sounds/SoundEffects/Death.mp3", Sound.class);
     MissleAlart=     SpaceGame.manager.get("sounds/SoundEffects/Alartnes.wav", Sound.class);
      this.game=game;
      audioMusic=new AudioMusic();
     // Backround=new Texture("Icons/Background.png");
      camera=new OrthographicCamera();
      hud=new Hud(game.batch);
      mapLoader=new TmxMapLoader();
      currentMap=mapLoader.load(String.valueOf(Gdx.files.internal("newMaps/Level2.tmx")));
      renderer=new OrthoCachedTiledMapRenderer(currentMap,1/SpaceMan.PPM);
      GamePort=new StretchViewport((SpaceGame.Virtual_W)/SpaceMan.PPM,(SpaceGame.Virtual_H)/SpaceMan.PPM,camera);
      camera.position.set((GamePort.getWorldWidth()/2),(GamePort.getWorldHeight()/2),0);

          //box2d
          world=new World(new Vector2(0,-20),true);
          box2DDebugRenderer=new Box2DDebugRenderer();
          creat= new Box2DCreat(this);
          spaceMan=new SpaceMan(this);
          world.setContactListener(new WorldConctactListener());
          items=new Array<Item>();
          idef=new LinkedBlockingQueue<ItemDefenesions>();

      }
public void SpawnItem(ItemDefenesions idef ){
    this.idef.add(idef);
}

    public void HandleSpawn() {
        if (!idef.isEmpty()) {
            ItemDefenesions itemDefenesions = idef.poll();
            if (itemDefenesions.Type_Of_Item == Ice_Cube.class) {
                items.add(new Ice_Cube(this, itemDefenesions.position.x, itemDefenesions.position.y));
            } else if (itemDefenesions.Type_Of_Item == Missle.class) {
                MissleAlart.play();
                items.add(new Missle(this, SpaceMan.manBody.getPosition().x, itemDefenesions.position.y));


            } else if (itemDefenesions.Type_Of_Item == getAccesToBullerss.class) {
                items.add(new getAccesToBullerss(this, itemDefenesions.position.x, itemDefenesions.position.y));
            }
            else if(itemDefenesions.Type_Of_Item== ArmagedonRocket.class){
                items.add(new ArmagedonRocket(this,itemDefenesions.position.x,itemDefenesions.position.y));
            }
        }
    }

public TextureAtlas GetAtlas(){
    return atlasHero;
}
  public TextureAtlas GetModAtlas(){
      return ModAtlas;
  }
  public TextureAtlas getBulletAtlas(){
      return  Bull;
  }
  public TextureAtlas GetExpAtlas(){
      return ExplodeAtlas;
  }
  public boolean   GameOver(){
     if( SpaceMan.state== SpaceMan.StateOfSpaceMan.Die && spaceMan.getStateTimer()>3)
      return true;
      else return false;
  }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

      update(delta);
      Gdx.gl.glClearColor(0,0,0,1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
     //   box2DDebugRenderer.render(world,camera.combined);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        spaceMan.draw(game.batch);
        for(Enemy enemy:creat.getGroundEnemies()){
            enemy.draw(game.batch);
        }
        for(Enemy enemy:creat.getGroundVampirs()){
            enemy.draw(game.batch);
        }
        for(Enemy enemy:creat.getAirVampirses()){
            enemy.draw(game.batch);
        }
        for(FreezBullet freezBullet:freezBullets) {

                freezBullet.draw(game.batch);
        }

for(Item item:items)
    item.draw(game.batch);
        game.batch.end();
      game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
      hud.stage.draw();
      controller.drawNextStage();
        if(SpaceMan.state == SpaceMan.StateOfSpaceMan.Menu){
            game.setScreen(new PlayScreen(game));
            dispose();

        } else
        if(GameOver()){
            game.setScreen(new GameOverScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
      GamePort.update(width,height);
      controller.resize(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
currentMap.dispose();
renderer.dispose();
world.dispose();
box2DDebugRenderer.dispose();
hud.dispose();
    }
    public static void PlayJetSound(boolean IsFly, Sound JetpackStart){
        if(IsFly){
            JetpackStart.play();
        }
        else{
            JetpackStart.stop();
        }
    }
}
