package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.game.PlayerScreen;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.Sprites.SpaceMan;

/**
 * Created by interstaller on 3/27/2018.
 */
// Correct Bounds of Missle for collision with SpaceMan ???????
public class Missle extends  Item {
    private Vector2 AngleVel;
    private TextureRegion region;
    public Animation MissleAnimation;
    public Missle(PlayerScreen screen, float x, float y) {
        super(screen, x, y);
        region=(screen.getBulletAtlas().findRegion("Missle"));
        velocity=new Vector2(2f,0.15f);
        AngleVel=new Vector2(2f,3f);
        setRegion(region,0,0,85,65);

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
        Fdef.filter.categoryBits= SpaceGame.missleBit;
        Fdef.shape=shape;
        Fdef.filter.maskBits=SpaceGame.SpaceManBit|SpaceGame.DefaultBut;
        body.createFixture(Fdef).setUserData(this);

    }


    @Override
    public void use() {


    }

    public void update(float dt){
        super.update(dt);
        setPosition(body.getPosition().x-16/SpaceMan.PPM ,body.getPosition().y-20/SpaceMan.PPM);
        body.setLinearVelocity(velocity);
        body.setAngularVelocity(1f);

    }
}
