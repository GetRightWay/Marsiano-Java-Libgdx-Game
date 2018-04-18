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

import java.util.Random;

/**
 * Created by interstaller on 4/2/2018.
 */

public class GroundVampir extends Enemy {
    public float stateTimer;
    public Animation GroundVampir;
    public Array<TextureRegion> frames;
    private TextureRegion region;
    Random random = new Random();

    public GroundVampir(PlayerScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        region = new TextureRegion(screen.GetAtlas().findRegion("SecondEnemy"));

        for (int i = 0; i <= 5; i++) {
            frames.add(new TextureRegion(region, i * 54, 0, 54, 64));
        }
        GroundVampir = new Animation(0.2f, frames);
        stateTimer = 0;
        setBounds(getX(), getY(), 38 / SpaceMan.PPM, 45 / SpaceMan.PPM);

    }

    @Override
    public void DefEnem() {
        BodyDef spDef = new BodyDef();
        spDef.position.set(getX(), getY() + 16 / SpaceMan.PPM);
        spDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(spDef);
        FixtureDef Fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / SpaceMan.PPM);
        Fdef.filter.categoryBits = SpaceGame.AirVamPirBit;
        Fdef.shape = shape;
        Fdef.filter.maskBits = SpaceGame.SpaceManBit | SpaceGame.DefaultBut | SpaceGame.Bullet_Bit | SpaceGame.SwordBulletBit;
        b2body.createFixture(Fdef).setUserData(this);
    }

    @Override
    public void update(float dt) {
        stateTimer += dt;
        if(!Died && IsToDie){
            world.destroyBody(b2body);
            IsToDie=false;
        }else  if(!Died){
        setPosition(b2body.getPosition().x - getWidth() / 2, (b2body.getPosition().y - getHeight() / 2) + 14 / SpaceMan.PPM);
        setRegion((TextureRegion) GroundVampir.getKeyFrame(stateTimer, true));
        int x = random.nextInt(2);
        if (b2body.getLinearVelocity().x < x) {
        }

            b2body.applyLinearImpulse(new Vector2(0.2f, 1.5f), b2body.getWorldCenter(), true);
        }
    }



    @Override
    public void DestroyEnemy() {
        IsToDie = true;
    }
}
