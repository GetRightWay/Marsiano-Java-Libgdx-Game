package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PlayerScreen;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.Sprites.SpaceMan;

import java.util.Random;

/**
 * Created by interstaller on 3/22/2018.
 */

public abstract class Item extends Sprite {
    protected World world;
    protected PlayerScreen screen;
    protected Vector2 velocity;
    protected boolean IsDestronyed;
    protected boolean Destroyable;
    protected Body body;
    public Item(PlayerScreen screen,float x,float y){
        this.screen=screen;
        this.world=screen.Getwolrd();
        setPosition(x,y);
        defItem();
        setBounds(getX(),getY(),40/ SpaceGame.PPM,40/SpaceGame.PPM);

        IsDestronyed=false;
        Destroyable=false;

    }
    public abstract void use();
    public void update(float dt){
        if(Destroyable && !IsDestronyed){
            world.destroyBody(body);
            IsDestronyed=true;
        }
    }
    public void destroy(){
        Destroyable=true;
    }
    public void draw(Batch batch){
        if(!IsDestronyed){
            super.draw(batch);
        }
    }


    public abstract  void defItem();
}
