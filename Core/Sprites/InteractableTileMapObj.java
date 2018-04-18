package com.mygdx.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PlayerScreen;
import com.mygdx.game.SpaceGame;

/**
 * Created by harut on 10.03.2018.
 */

public  abstract class InteractableTileMapObj {
    protected World world;
    protected TiledMap map;
    protected Rectangle bounds;
    protected TiledMapTile mapTile;
    protected Fixture fixture;
    protected Body body;
    protected  PlayerScreen screen;
    public InteractableTileMapObj(PlayerScreen screen, Rectangle bounds){
        this.screen=screen;
        this.world=screen.Getwolrd();
        this.map=screen.getCurrentMap();
        this.bounds=bounds;
        BodyDef bodyDef=new BodyDef();
        FixtureDef fixtureDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        bodyDef.type= BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX()+bounds.getWidth()/2)/SpaceMan.PPM,(bounds.getY()+bounds.getHeight()/2)/SpaceMan.PPM);
        body=world.createBody(bodyDef);
        shape.setAsBox(bounds.getWidth()/2/SpaceMan.PPM,bounds.getHeight()/2/SpaceMan.PPM);
        fixtureDef.shape=shape;
        fixture= body.createFixture(fixtureDef);
    }
     public abstract void OnHeadHit();
        public void SetCategoryFilter(short FilterBit){
        Filter filter=new Filter();
        filter.categoryBits=FilterBit;
        fixture.setFilterData(filter);
    }
    public TiledMapTileLayer.Cell getCell(){
            TiledMapTileLayer layer=(TiledMapTileLayer) map.getLayers().get(1);
            return layer.getCell( (int)(body.getPosition().x * SpaceMan.PPM /16), ((int) (body.getPosition().y * SpaceMan.PPM /16)));

    }
}
