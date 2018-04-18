package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Items.Ice_Cube;
import com.mygdx.game.PlayerScreen;
import com.mygdx.game.SpaceGame;

import static com.mygdx.game.SpaceGame.AirVamPirBit;
import static com.mygdx.game.SpaceGame.ArmaGedBit;
import static com.mygdx.game.SpaceGame.manager;

/**
 * Created by harut on 09.03.2018.
 */

public class SpaceMan extends Sprite {
    public enum StateOfSpaceMan {
        Jummping, running, Standing, falling, Hitting,
        Standing_ice_mod, Die, Menu, Idle;
    }

    public static boolean isFleedByX() {
        return isFleedByX;
    }

    private  static boolean isFleedByX;
    public static StateOfSpaceMan state;
    public static boolean IsToDie;
    public static Animation SpiderRun;
    public static Animation Idle;
    public Animation Die;
    private static Animation ICE_MOD_RUN;
    private TextureRegion flipedReg;
    private static TextureRegion ICE_MOD_MAN;
    private Animation Ice_mod_Jump;
    private boolean IsHittingRight;
    private TextureRegion region;
    private Animation Tranformation;
    private Animation Ice_mod_Attack;
    public static StateOfSpaceMan currentState;
    private StateOfSpaceMan previousState;
    private float Boundx = 40;
    private float Boundy = 32;
    private Animation Jumping;
    private Animation Die_Main_Hero;
    private Animation Running;
    private Animation Hitting;
    private int GetSpritePos = 76;
    private boolean isRight;
    private float timer;
    public World world;
    static public Body manBody;
    private TextureRegion SpaceManImg;
    public static final float PPM = 50;

    public SpaceMan(PlayerScreen screen) {
        super(screen.GetAtlas().findRegion("MainHeroAttack"));
        this.world = screen.Getwolrd();

        state = StateOfSpaceMan.Menu;
        currentState = StateOfSpaceMan.Standing;
        previousState = StateOfSpaceMan.Standing;
        IsToDie = false;

        timer = 0;
        defineSpaceMan();

        //anim
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 0; i <= 6; i++) {
            frames.add(new TextureRegion(screen.GetAtlas().findRegion("MainHeroRun"), i * 80, 0, 79, 119));

        }
        Running = new Animation(0.08f, frames);
        frames.clear();
        for (int i = 0; i <= 3; i++) {
            frames.add(new TextureRegion(screen.GetAtlas().findRegion("HeroDie"), i * 91, 0, 91, 134));
        }
        Die = new Animation(0.2f, frames);
        frames.clear();
        for (int i = 0; i <= 2; i++) {
            frames.add(new TextureRegion(screen.GetAtlas().findRegion("JetPack"), i * 124, 0, 124, 88));
        }
        Jumping = new Animation(0.2f, frames);
        frames.clear();

        for (int i = 0; i <= 5; i++) {
            frames.add(new TextureRegion(screen.GetAtlas().findRegion("MainHeroAttack"), i * 103, 0, 103, 129));
        }
        Hitting = new Animation(0.1f, frames);
        frames.clear();
        for (int i = 0; i < 5; i++) {
            frames.add((new TextureRegion(screen.GetAtlas().findRegion("IdleHero"), i * 87, 0, 87, 126)));
        }
        Idle = new Animation(0.1f, frames);
        frames.clear();
        for (int i = 0; i <= 2; i++) {
            frames.add(new TextureRegion(screen.GetModAtlas().findRegion("Ice_Man_Jump"), i * 75, 0, 75, 76));
        }
        Ice_mod_Jump = new Animation(0.1f, frames);
        frames.clear();
        for (int i = 0; i <= 5; i++) {
            frames.add(new TextureRegion(screen.GetModAtlas().findRegion("Ice_Man_Attack"), i * 74, 0, 74, 94));
        }
        Ice_mod_Attack = new Animation(0.1f, frames);
        frames.clear();
        for (int i = 0; i <= 3; i++) {
            frames.add(new TextureRegion(screen.GetModAtlas().findRegion("Spider_Run"), i * 85, 0, 85, 75));
        }
        SpiderRun = new Animation(0.1f, frames);
        frames.clear();
        for (int i = 0; i <= 6; i++) {
            frames.add(new TextureRegion(screen.GetModAtlas().findRegion("Ice_Man_Run"), i * 55, 0, 52, 93));
        }
        ICE_MOD_RUN = new Animation(0.1f, frames);
        SpaceManImg = new TextureRegion(getTexture(), 0, 442, 98, 122);
        ICE_MOD_MAN = new TextureRegion(screen.GetModAtlas().findRegion("Ice_Man_Standing"), 0, 0, 53, 90);

        setBounds(0, 0, Boundx / SpaceMan.PPM, Boundy / SpaceMan.PPM);
        setRegion(SpaceManImg);
    }

    public void Update(float dt) {
        if (IsToDie) {
            state = StateOfSpaceMan.Die;
        }
        setPosition(manBody.getPosition().x - getWidth() / 2, manBody.getPosition().y - getHeight() / 2);
        setRegion(GetFrames(dt));
    }






    private TextureRegion GetFrames(float dt) {

        currentState = Getstate();
        switch (currentState) {
            case Die:
                region = (TextureRegion) Die.getKeyFrame(timer, false);
                break;
            case Idle:
                if(!Ice_Cube.IsUsed){
                    region = (TextureRegion) Idle.getKeyFrame(timer, true);
                }
                break;
            case Standing_ice_mod:
                region = ICE_MOD_MAN;
                ICE_MOD_MAN.flip(true, false);
                break;
            case Jummping:
                region = (TextureRegion) Jumping.getKeyFrame(timer, true);
                Boundx = 100;
                Boundy = 40;
                break;
            case running:
                region = (TextureRegion) Running.getKeyFrame(timer, true);
                break;
            case Hitting:
                region = (TextureRegion) Hitting.getKeyFrame(timer, true);
                break;
            case falling:
                if (Ice_Cube.IsUsed) {
                    region = ICE_MOD_MAN;
                }
            case Standing:
                if (Ice_Cube.IsUsed) {
                    region = ICE_MOD_MAN;
                } else {
                    region = SpaceManImg;
                }
                break;
            default:
                region = SpaceManImg;
                break;
        }
        if ((manBody.getLinearVelocity().x < 0 || !isRight) && !region.isFlipX()) {
            region.flip(true, false);
            isFleedByX=true;
            isRight = false;
        } else if ((manBody.getLinearVelocity().x > 0 || isRight) && region.isFlipX()) {
            region.flip(true, false);
            isFleedByX=false;
            isRight = true;
        }
        timer = currentState == previousState ? timer + dt : 0;
        previousState = currentState;


        return region;
    }

public float getStateTimer(){
    return timer;
}
    public StateOfSpaceMan Getstate(){
        if(IsToDie){

            SetDieState();
        }else
         if(manBody.getLinearVelocity().y>0){
             if(Ice_Cube.IsUsed){
                 Jumping=Ice_mod_Jump;
             }
          state= StateOfSpaceMan.Jummping;
        }else if(manBody.getLinearVelocity().y<0){
            state= StateOfSpaceMan.falling;

        }else if(manBody.getLinearVelocity().x==0){
             state=StateOfSpaceMan.Idle;
         }
        else if(manBody.getLinearVelocity().x!=0) {
            if(Ice_Cube.IsUsed){
                Running=ICE_MOD_RUN;
            }
            state= StateOfSpaceMan.running;
        } else if(IsToDie){
             state=StateOfSpaceMan.Die;
         }

            else if(Ice_Cube.IsUsed && manBody.getLinearVelocity().x==0){
            state= StateOfSpaceMan.Standing_ice_mod;
        }

        else
            state= StateOfSpaceMan.Standing;
        return state;
    }

    public void defineSpaceMan(){
        BodyDef spDef=new BodyDef();
        spDef.position.set(32/SpaceMan.PPM,32/SpaceMan.PPM);
        spDef.type=BodyDef.BodyType.DynamicBody;
        manBody=world.createBody(spDef);
        FixtureDef ManDef=new FixtureDef();
        CircleShape shape=new CircleShape();
        shape.setRadius(6/SpaceMan.PPM);
        ManDef.filter.categoryBits= SpaceGame.SpaceManBit;
        ManDef.filter.maskBits= ArmaGedBit|AirVamPirBit|SpaceGame.GroundVampir|SpaceGame.DefaultBut | SpaceGame.LuckyBlockBit|SpaceGame.Item_Bit|SpaceGame.missleBit|SpaceGame.GroundEnemysBit;
        ManDef.shape=shape;
        manBody.createFixture(ManDef);
        EdgeShape edgeShape=new EdgeShape();
        edgeShape.set(new Vector2(-4/PPM,7/PPM),new Vector2(4/PPM,7/PPM));
        ManDef.shape=edgeShape;
        ManDef.isSensor=true;
        manBody.createFixture(ManDef).setUserData("edgeShape");
    }
    public void SetAttackregoin () {
        if (Ice_Cube.IsUsed) {
setRegion((TextureRegion) Ice_mod_Attack.getKeyFrame(timer,true));
        } else {
            setRegion((TextureRegion) Hitting.getKeyFrame(timer, true));
        }
    }


    private void SetDieState(){
        state=StateOfSpaceMan.Die;
    }


}
