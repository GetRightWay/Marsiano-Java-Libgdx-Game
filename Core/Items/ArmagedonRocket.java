package com.mygdx.game.Items;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.game.PlayerScreen;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.Sprites.SpaceMan;

/**
 * Created by tim on 4/12/2018.
 */

public class ArmagedonRocket extends Item {
    public static Sound ArmagedSound;
    private boolean IsPLayed;
    float y=0.1f;
    public ArmagedonRocket(PlayerScreen screen, float x, float y) {
        super(screen, x, y);
        velocity=new Vector2(0,0);
        setRegion(screen.getBulletAtlas().findRegion("ArmaGed"),0,0,200,199);
        ArmagedSound=SpaceGame.manager.get("sounds/SoundEffects/Bomb+3.wav", Sound.class);
        IsPLayed=false;

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
        CircleShape shape=new CircleShape();
        shape.setRadius(15/ SpaceMan.PPM);
        Fdef.filter.categoryBits= SpaceGame.ArmaGedBit;
        Fdef.shape=shape;
        Fdef.filter.maskBits=SpaceGame.SpaceManBit|SpaceGame.DefaultBut;
        body.createFixture(Fdef).setUserData(this);
    }
    public void update(float dt){

        velocity=new Vector2(0,y);
        super.update(dt);
        PlayShotSound();
        setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2-25/SpaceMan.PPM);
        if(body.getLinearVelocity().x>2)
       body.setLinearVelocity(velocity);
    }
    public void PlayShotSound(){
        if(!IsPLayed){
           ArmagedSound.play();
            IsPLayed=true;
        }
    }
}
