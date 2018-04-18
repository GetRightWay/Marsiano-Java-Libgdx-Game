package com.mygdx.game.Enemys;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Enemy;
import com.mygdx.game.PlayerScreen;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.Sprites.SpaceMan;

/**
 * Created by interstaller on 4/1/2018.
 */

public class GroundEnemy extends Enemy {
    public float stateTimer;
    public Animation GroundEnemyWalking;
    public Array<TextureRegion> frames;
    public GroundEnemy(PlayerScreen screen, float x, float y) {
        super(screen, x, y);
        frames=new Array<TextureRegion>();
        frames.add(new TextureRegion(screen.GetAtlas().findRegion("GroundEnemy"),0,0,84,96));
        frames.add(new TextureRegion(screen.GetAtlas().findRegion("GroundEnemy"),84,0,84,96));
        GroundEnemyWalking=new Animation(0.2f,frames);
        stateTimer=0;
        IsToDie=false;
        Died=false;
        setBounds(getX(),getY(),38/SpaceMan.PPM,45/SpaceMan.PPM);

    }

    @Override
    public void DefEnem() {
        BodyDef spDef=new BodyDef();
        spDef.position.set(getX(),getY());
        spDef.type=BodyDef.BodyType.DynamicBody;
        b2body=world.createBody(spDef);
        CircleShape shape=new CircleShape();
        shape.setRadius(10/SpaceMan.PPM);
        Fdef=new FixtureDef();
        Fdef.filter.categoryBits=SpaceGame.GroundEnemysBit;
        Fdef.shape=shape;
        Fdef.filter.maskBits=SpaceGame.SpaceManBit|SpaceGame.DefaultBut|SpaceGame.Bullet_Bit|SpaceGame.SwordBulletBit;
        b2body .createFixture(Fdef).setUserData(this);
    }

    @Override
    public void update(float dt) {
        stateTimer += dt;

        if (!Died && IsToDie) {
            world.destroyBody(b2body);
            Died = true;
        } else if (!Died) {
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
            setRegion((TextureRegion) GroundEnemyWalking.getKeyFrame(stateTimer, true));
            if (b2body.getLinearVelocity().x > -1)
                b2body.applyLinearImpulse(new Vector2(-0.2f, 0), b2body.getWorldCenter(), true);
        }
    }

    public void draw(Batch batch){
        if(!Died || stateTimer<1)
            super.draw(batch);
    }
    @Override
    public void DestroyEnemy() {
        IsToDie=true;
    }


}
