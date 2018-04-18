package com.mygdx.game.HittingTools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.game.Items.Item;
import com.mygdx.game.PlayerScreen;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.Sprites.SpaceMan;

/**
 * Created by tim on 4/6/2018.
 */

public class SwordBullet extends Item{
    public Body body;

    public SwordBullet(PlayerScreen screen, float x, float y) {
        super(screen, x, y);
    }

    @Override
    public void use() {

    }
    public void DestroyBody(Body body){
        world.destroyBody(body);
        Gdx.app.log("Body","Destroyed");
    }

    @Override
    public void defItem() {
        BodyDef spDef=new BodyDef();
        spDef.position.set(getX(),getY());
        spDef.type=BodyDef.BodyType.DynamicBody;
        body=world.createBody(spDef);
        FixtureDef Fdef=new FixtureDef();
        CircleShape shape=new CircleShape();
        shape.setRadius(12/ SpaceMan.PPM);
        Fdef.filter.categoryBits =SpaceGame.SwordBulletBit;
        Fdef.shape=shape;
        Fdef.filter.maskBits= (short) (SpaceGame.AirVamPirBit|SpaceGame.GroundVampir|SpaceGame.GroundEnemysBit|SpaceGame.DefaultBut);
        body.createFixture(Fdef).setUserData(this);
    }
    public void update(float dt){
        super.update(dt);
if(body.getPosition().x<=body.getPosition().x+2)
        body.setLinearVelocity(new Vector2(2f,0));
       // body.applyLinearImpulse(new Vector2(2f,0),body.getWorldCenter(),true);
        body.setAngularVelocity(1f);


    }
}
