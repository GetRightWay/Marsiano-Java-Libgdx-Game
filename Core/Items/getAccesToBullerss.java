package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.game.PlayerScreen;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.Sprites.LuckyBlock;
import com.mygdx.game.Sprites.SpaceMan;

/**
 * Created by interstaller on 4/3/2018.
 */

public class getAccesToBullerss extends Item {
    public static   boolean IsUsed;
    public getAccesToBullerss(PlayerScreen screen, float x, float y) {
        super(screen, x, y);
        setRegion(new TextureRegion(screen.getBulletAtlas().findRegion("Access_Bullet"),0,0,75,94));
                IsUsed=false;

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
        Fdef.filter.categoryBits= SpaceGame.Item_Bit;
        Fdef.shape=shape;
        Fdef.filter.maskBits=SpaceGame.SpaceManBit|SpaceGame.DefaultBut;
        body.createFixture(Fdef).setUserData(this);
    }

    @Override
    public void use() {
        IsUsed=true;

        destroy();


    }

    @Override
    public void update(float dt) {
        super.update(dt);
        setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight());
      //  body.setLinearVelocity(velocity);
        if(body.getLinearVelocity().x<1.4f && body.getLinearVelocity().y<2f)
        body.setLinearVelocity(1.4f,1f);
    }
}


