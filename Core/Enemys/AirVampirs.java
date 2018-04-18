package com.mygdx.game.Enemys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.Enemy;
import com.mygdx.game.PlayerScreen;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.Sprites.SpaceMan;

import java.util.Random;

/**
 * Created by interstaller on 4/3/2018.
 */

public class AirVampirs extends Enemy {
    private Animation Fly;
    private TextureRegion Die;
    private Array<TextureRegion> frames;
    FixtureDef Fdef;
    private float stateTimer;
    Random random=new Random();

    public AirVampirs(PlayerScreen screen, float x, float y) {
        super(screen, x, y);

        frames=new Array<TextureRegion>();
         Die=new TextureRegion(screen.GetAtlas().findRegion("FlyingVampir_Die"),0,0,66,39);
        for (int i=0;i<=3;i++){
            frames.add(new TextureRegion(screen.GetAtlas().findRegion("FlyingVampir"),i*52,0,52,34));}
        Fly=new Animation(0.1f,frames);
        stateTimer=0;
        setBounds(getX(),getY(),38/SpaceMan.PPM,45/SpaceMan.PPM);
        IsToDie=false;
        Died=false;
    }

    @Override
    public void DefEnem() {
        BodyDef spDef=new BodyDef();
        spDef.position.set(getX(),getY());
        spDef.type=BodyDef.BodyType.DynamicBody;
        b2body=world.createBody(spDef);
        CircleShape shape=new CircleShape();
        shape.setRadius(10/ SpaceMan.PPM);
        Fdef=new FixtureDef();
        Fdef.filter.categoryBits= SpaceGame.AirVamPirBit;
        Fdef.shape=shape;
        Fdef.filter.maskBits=SpaceGame.SpaceManBit|SpaceGame.DefaultBut|SpaceGame.Bullet_Bit|SpaceGame.SwordBulletBit;
        b2body .createFixture(Fdef).setUserData(this);
    }

    @Override
    public void update(float dt) {

            stateTimer += dt;
        if(IsToDie && !Died){
            world.destroyBody(b2body);
            setRegion(Die);
            Died=true;
            stateTimer=0;
        } else  if(!Died) {
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
            setRegion((TextureRegion) Fly.getKeyFrame(stateTimer, true));
            if (b2body.getPosition().y < 2) {
                int x = random.nextInt(2);
                b2body.applyLinearImpulse(new Vector2(0, x), b2body.getWorldCenter(), true);
                b2body.applyAngularImpulse(2f, true);
            }
        }
        }

public void draw(Batch batch){
    if(!Died || stateTimer<1){
       super.draw(batch);
    }
}
    @Override
    public void DestroyEnemy() {
        IsToDie=true;
    }


}
