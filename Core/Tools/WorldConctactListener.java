package com.mygdx.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.Controller;
import com.mygdx.game.Enemy;
import com.mygdx.game.Enemys.AirVampirs;
import com.mygdx.game.HittingTools.FreezBullet;
import com.mygdx.game.Items.Item;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.Sprites.InteractableTileMapObj;
import com.mygdx.game.Sprites.LuckyBlock;
import com.mygdx.game.Sprites.SpaceMan;

/**
 * Created by harut on 12.03.2018.
 */

public class WorldConctactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture Fixa = contact.getFixtureA();
        Fixture Fixb = contact.getFixtureB();
        int Collisiondef = Fixa.getFilterData().categoryBits | Fixb.getFilterData().categoryBits;
//        if (Fixa.getUserData() == ("edgeShape") || Fixa.getUserData() == ("edgeShape")) ;
//        Fixture Head = Fixa.getUserData() == "edgeShape" ? Fixa : Fixb;
//        Fixture obj = Head == Fixa ? Fixb : Fixa;
//        if (obj.getUserData() != null && InteractableTileMapObj.class.isAssignableFrom(obj.getUserData().getClass())) {
//            ((InteractableTileMapObj) obj.getUserData()).OnHeadHit();
//        }

switch (Collisiondef){
    case SpaceGame.AirVamPirBit|SpaceGame.SwordBulletBit:{
        if(Fixa.getFilterData().categoryBits==SpaceGame.AirVamPirBit){
            ((Enemy) Fixa.getUserData()).DestroyEnemy();
            Controller.ShootSound(FreezBullet.KilligSound);
        }else if(Fixb.getFilterData().categoryBits==SpaceGame.AirVamPirBit){
            ((Enemy) Fixb.getUserData()).DestroyEnemy();
            Controller.ShootSound(FreezBullet.KilligSound);
        }
    }
    break;
    case SpaceGame.GroundEnemysBit|SpaceGame.SwordBulletBit:{
        if(Fixa.getFilterData().categoryBits==SpaceGame.GroundEnemysBit){
            ((Enemy) Fixa.getUserData()).DestroyEnemy();

        }else if(Fixb.getFilterData().categoryBits==SpaceGame.GroundEnemysBit){
            ((Enemy) Fixb.getUserData()).DestroyEnemy();
        }
    }
    break;
    case SpaceGame.Item_Bit| SpaceGame.SpaceManBit: {
        if (Fixa.getFilterData().categoryBits == SpaceGame.Item_Bit) {
            ((Item) Fixa.getUserData()).use();

        } else ((Item) Fixb.getUserData()).use();


        break;
    }
        case SpaceGame.SpaceManBit|SpaceGame.LuckyBlockBit:
        {
            if(Fixa.getFilterData().categoryBits== SpaceGame.LuckyBlockBit){
                ((LuckyBlock) Fixa.getUserData()).OnHeadHit();
            }else ((LuckyBlock) Fixb.getUserData()).OnHeadHit();
        }
break;
    case SpaceGame.AirVamPirBit|SpaceGame.SpaceManBit:{
        if(Fixa.getFilterData().categoryBits==SpaceGame.AirVamPirBit){
            SetDestroyedFilter(Fixa);
SpaceMan.IsToDie=true;
        }else if(Fixb.getFilterData().categoryBits ==SpaceGame.AirVamPirBit){
            SetDestroyedFilter(Fixb);
            SpaceMan.IsToDie=true;

        }
    }
break;
    case SpaceGame.GroundEnemysBit|SpaceGame.SpaceManBit:{
        if(Fixa.getFilterData().categoryBits==SpaceGame.GroundEnemysBit){
            SetDestroyedFilter(Fixa);
            SpaceMan.IsToDie=true;
        }else if(Fixb.getFilterData().categoryBits ==SpaceGame.GroundEnemysBit){
            SetDestroyedFilter(Fixb);
            SpaceMan.IsToDie=true;

        }
    }
    break;
    case SpaceGame.GroundVampir|SpaceGame.SpaceManBit:{
        if(Fixa.getFilterData().categoryBits==SpaceGame.GroundVampir){
            SetDestroyedFilter(Fixa);
            SpaceMan.IsToDie=true;
        }else if(Fixb.getFilterData().categoryBits ==SpaceGame.GroundVampir){
            SetDestroyedFilter(Fixb);
            SpaceMan.IsToDie=true;

        }
    }
    break;
    case SpaceGame.missleBit |SpaceGame.SpaceManBit:
    {
        if(Fixa.getFilterData().categoryBits==SpaceGame.missleBit){
            ((Item) Fixa.getUserData()).use();
        }
      else ((Item) Fixb.getUserData()).use();
    }
    break;


        }

    }










        @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
    private void SetDestroyedFilter(Fixture fixture){
        Filter filter=new Filter();
        filter.categoryBits=SpaceGame.DesTroyedBit;
        fixture.setFilterData(filter);

    }
}
