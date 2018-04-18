package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Interstaller on 16.03.2018.
 */

public abstract class Enemy  extends Sprite{
    protected World world;
    protected   PlayerScreen screen;
    protected FixtureDef Fdef;
    public Body b2body;
    protected boolean IsToDie;
    protected   boolean Died;
  public   Enemy(PlayerScreen screen,float x,float y){
      this.screen=screen;
      this.world=screen.Getwolrd();
      setPosition(x,y);
      DefEnem();
    }
    public abstract  void  DefEnem();
    public abstract void update(float dt);
public abstract void DestroyEnemy();

}
