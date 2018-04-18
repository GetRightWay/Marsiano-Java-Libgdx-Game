package com.mygdx.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Enemys.AirVampirs;
import com.mygdx.game.Enemys.GroundEnemy;
import com.mygdx.game.Enemys.GroundVampir;
import com.mygdx.game.PlayerScreen;
import com.mygdx.game.Sprites.LuckyBlock;
import com.mygdx.game.Sprites.SpaceMan;

/**
 * Created by harut on 10.03.2018.
 */

public class Box2DCreat {
    public Array<GroundEnemy> getGroundEnemies() {
        return groundEnemies;
    }

    private  Array<GroundEnemy>groundEnemies;

    public Array<AirVampirs> getAirVampirses() {
        return airVampirses;
    }

    public void setAirVampirses(Array<AirVampirs> airVampirses) {
        this.airVampirses = airVampirses;
    }

    private  Array<AirVampirs> airVampirses;

    public Array<GroundVampir> getGroundVampirs() {
        return groundVampirs;
    }

    private  Array<GroundVampir> groundVampirs;

    public Box2DCreat (PlayerScreen screen){
        World world=screen.Getwolrd();
        TiledMap currentMap=screen.getCurrentMap();
        BodyDef bodyDef=new BodyDef();
        PolygonShape shape=new PolygonShape();
        FixtureDef fixtureDef=new FixtureDef();
        Body body;
       // spaceMan=new SpaceMan(world);
        for(MapObject obj:currentMap.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle=((RectangleMapObject) obj).getRectangle();
            bodyDef.type= BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX()+rectangle.getWidth()/2)/SpaceMan.PPM,(rectangle.getY()+rectangle.getHeight()/2)/SpaceMan.PPM);
            body=world.createBody(bodyDef);
            shape.setAsBox(rectangle.getWidth()/2/SpaceMan.PPM,rectangle.getHeight()/2/SpaceMan.PPM);
            fixtureDef.shape=shape;
            body.createFixture(fixtureDef);
        }
        for(MapObject obj:currentMap.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle=((RectangleMapObject) obj).getRectangle();
            new LuckyBlock(screen,rectangle);
        }
        groundEnemies=new Array<GroundEnemy>();
        groundVampirs=new Array<GroundVampir>();
        airVampirses=new Array<AirVampirs>();
        for(MapObject obj:currentMap.getLayers().get(6).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle=((RectangleMapObject) obj).getRectangle();
            groundEnemies.add(new GroundEnemy(screen,rectangle.getX()/SpaceMan.PPM,rectangle.getY()/SpaceMan.PPM));
        }

        for(MapObject obj:currentMap.getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle=((RectangleMapObject) obj).getRectangle();
            groundVampirs.add(new GroundVampir(screen,rectangle.getX()/SpaceMan.PPM,rectangle.getY()/SpaceMan.PPM));
        }
for(MapObject obj:currentMap.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
    Rectangle rectangle=((RectangleMapObject) obj).getRectangle();
    airVampirses.add(new AirVampirs(screen,rectangle.getX()/SpaceMan.PPM,rectangle.getY()/SpaceMan.PPM));
}
    }


}
