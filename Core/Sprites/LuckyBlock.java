package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import com.mygdx.game.Items.ArmagedonRocket;
import com.mygdx.game.Items.Ice_Cube;
import com.mygdx.game.Items.ItemDefenesions;
import com.mygdx.game.Items.Missle;
import com.mygdx.game.Items.getAccesToBullerss;
import com.mygdx.game.PlayerScreen;
import com.mygdx.game.SpaceGame;

import java.util.Random;

import static com.mygdx.game.SpaceGame.SpaceManBit;
import static com.mygdx.game.SpaceGame.manager;

/**
 * Created by harut on 10.03.2018.
 */

public class LuckyBlock extends InteractableTileMapObj {
    public static Class<?> type;
    private int GetObj;
    private int RandomPosition;
    public static  int FreezBulletCounts=0;
    Random random=new Random();

private Sound soundBreak;
    public LuckyBlock(PlayerScreen screen, Rectangle bounds) {
        super(screen, bounds);
        soundBreak=manager.get("sounds/SoundEffects/BreakSound.wav", Sound.class);
        fixture.setUserData(this);
        SetCategoryFilter(SpaceGame.LuckyBlockBit);
    }

    @Override
    public void OnHeadHit() {
        GetObj=2;
        Gdx.app.log("Lucky","Hit");
    if(GetObj==1){
        type=Missle.class;
    }else if(GetObj==0) {
        type = Ice_Cube.class;
    }
    else if(GetObj==2){
        type=getAccesToBullerss.class;
        FreezBulletCounts=5;
    } else if(GetObj==3){
        type= ArmagedonRocket.class;
    }
//    }else if(GetObj==2){
//        type= Spider.class;
//    }
        Gdx.app.log("Get","Obj "+GetObj+ "  " +type.getName());
        SetCategoryFilter(SpaceGame.DesTroyedBit);
        if(type==Missle.class){
            screen.SpawnItem(new ItemDefenesions(type,new Vector2(-400/SpaceMan.PPM,random.nextFloat()*3)));
        }
        else if (type==ArmagedonRocket.class){

            screen.SpawnItem(new ItemDefenesions(type,new Vector2(SpaceMan.manBody.getPosition().x+30/SpaceMan.PPM,SpaceMan.manBody.getPosition().x+75/SpaceGame.PPM)));
        }
            else
        screen.SpawnItem(new ItemDefenesions(type,new Vector2(body.getPosition().x,body.getPosition().y-10/SpaceMan.PPM)));
        soundBreak.play();
        //getCell().setTile(null);
    }
}
