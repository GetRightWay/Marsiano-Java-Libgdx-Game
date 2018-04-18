package com.mygdx.game.HittingTools;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Controller;
import com.mygdx.game.Items.Item;
import com.mygdx.game.PlayerScreen;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.Sprites.SpaceMan;


import java.util.Random;

/**
 * Created by tim on 4/7/2018.
 */

public class FreezBullet extends Item {
    private  boolean IsToDest;
    private  boolean IsDestroyed;
    private float TimerToDestroy=0;
    public static Sound shootSound;
    public static Sound KilligSound;
    private float stateTimer=0;
    private Animation Explode;
    Array<TextureRegion> frames = new Array<TextureRegion>();
    private TextureRegion region;
    Random random=new Random();
    public FreezBullet(PlayerScreen screen, float x, float y) {
        super(screen, x, y);
        frames=new Array<TextureRegion>();
        setRegion( new TextureRegion(screen.getBulletAtlas().findRegion("Bullet"),0,0,37,41));
        for(int i=0;i<=3;i++){
            frames.add(new TextureRegion(screen.GetExpAtlas().findRegion("Bullet"),i*243,0,243,181));
        }
        Explode=new Animation(0,1f ,frames);
        IsToDest=false;
        IsDestroyed=false;
        shootSound=SpaceGame.manager.get("sounds/SoundEffects/ShootSound.wav", Sound.class);
        KilligSound=SpaceGame.manager.get("sounds/SoundEffects/ExplodeShound.wav", Sound.class);
    }

    @Override
    public void use() {

    }


    @Override
    public void defItem() {
        BodyDef spDef=new BodyDef();
        spDef.position.set(getX(),getY());
        spDef.type=BodyDef.BodyType.DynamicBody;
        body=world.createBody(spDef);
        FixtureDef Fdef=new FixtureDef();
        Shape shape=new CircleShape();
        shape.setRadius(12/ SpaceMan.PPM);
        Fdef.filter.categoryBits = SpaceGame.SwordBulletBit;
        Fdef.shape=shape;
        Fdef.filter.maskBits= (short) (SpaceGame.AirVamPirBit|SpaceGame.GroundVampir|SpaceGame.GroundEnemysBit|SpaceGame.DefaultBut);
        body.createFixture(Fdef).setUserData(this);

    }
    public void update(float dt) {
        DestroyBullet();
        stateTimer+=dt;
        TimerToDestroy += dt;
    if(!IsDestroyed && IsToDest){
            world.destroyBody(body);
            IsDestroyed = true;
            TimerToDestroy = 0;

    }else if(!IsDestroyed) {
        setPosition(body.getPosition().x - 15 / SpaceMan.PPM, body.getPosition().y - 24 / SpaceMan.PPM);
        if (body.getLinearVelocity().x < 2) {
            float Vely = random.nextFloat() * 4f;
            body.setLinearVelocity(new Vector2(5f, Vely));

        }
    }
    }
 public void DestroyBullet(){
     if(TimerToDestroy>=1)
     IsToDest=true;
 }
 public void draw(Batch batch){
     if(!IsDestroyed){
         super.draw(batch);
     }
 }
    }
