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
 * Created by interstaller on 3/22/2018.
 */

public class Ice_Cube extends Item {
    public static boolean IsUsed;
    private Sound Ice_Pick_Up;
    public Ice_Cube(PlayerScreen screen, float x, float y) {
        super(screen, x, y);
        IsUsed=false;
       // Ice_Pick_Up=SpaceGame.manager.get("sounds/SoundEffects/IceTransform.wav", Sound.class);
        setRegion(screen.GetModAtlas().findRegion("Ice_modify"),0,0,220,230);
        velocity=new Vector2(2f,0);
    }

    @Override
    public void defItem() {
        BodyDef spDef=new BodyDef();
        spDef.position.set(getX(),getY());
        spDef.type=BodyDef.BodyType.DynamicBody;
        body=world.createBody(spDef);
        FixtureDef Fdef=new FixtureDef();
        CircleShape shape=new CircleShape();
        shape.setRadius(15/SpaceMan.PPM);
        Fdef.filter.categoryBits=SpaceGame.Item_Bit;
        Fdef.shape=shape;
        Fdef.filter.maskBits=SpaceGame.SpaceManBit|SpaceGame.DefaultBut;
        body.createFixture(Fdef).setUserData(this);
    }

    @Override
    public void use() {
        IsUsed=true;
      //  Ice_Pick_Up.play();

        destroy();

    }

    @Override
    public void update(float dt) {

        super.update(dt);
        setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight());
        if (body.getLinearVelocity().x<2f)
        body.setLinearVelocity(velocity);
    }
}
