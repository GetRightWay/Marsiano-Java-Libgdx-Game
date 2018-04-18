package com.mygdx.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Controller;
import com.mygdx.game.PlayerScreen;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.Sprites.SpaceMan;


import java.util.Random;

/**
 * Created by harut on 08.03.2018.
 */

public class Hud implements Disposable{
public Label.LabelStyle style2;
    public Texture ButtonTexture;
    Random random=new Random();
    public  Stage stage;
    public float FUEL;
    private Viewport viewport;
    private Integer time;
    private Integer Score;
    private float Timer;
    private Integer Money;
    private Integer LifeCount;
    //Labels
    Label JetPack_Int;
    Label Fuel;
    Label CoundDownLabel;
    Label ScoreLabel;
    Label  MoneyLabel;
    Label LifeLable;
    Label LevelLable;
    Label TimeLabel,MoneyText;
    Label arrow;
    Label WorldLb;
    Label ScoreText;
    Label SpaceLabel;
    Label.LabelStyle style;
    BitmapFont bitmapFont;

    public Hud(SpriteBatch SB){

        FUEL=100;
        time=5;
        Score=0;
        Money=0;
        LifeCount=3;
        viewport=new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),new OrthographicCamera());
        stage=new Stage(viewport,SB);

      //  stage.getCamera().position.set(0,0,0);
        //stage.getCamera().update();
        bitmapFont=new BitmapFont(Gdx.files.internal("Font/Font.fnt"));
        style=new Label.LabelStyle(bitmapFont,Color.MAROON);
        style2=new Label.LabelStyle(bitmapFont,Color.BROWN);
         CoundDownLabel=new Label(String.format("%03d",time), style);
         ScoreLabel=new Label(String.format("%06d",Score), style);
          MoneyLabel =new Label(String.format("%04d",Money),style);;
         LifeLable=new Label(String.format("%01d",LifeCount),style);
         JetPack_Int=new Label(String.format("%03d", (int)FUEL),style2);
         LevelLable=new Label("1-1", style);
          Fuel=new Label("FUEl",style2);
         ScoreText=new Label("Score", style);
         TimeLabel=new Label("Time", style);
         WorldLb=new Label("World",style);
         MoneyText=new Label("Money",style);
         SpaceLabel=new Label("Space",style);
        Table table=new Table();
        Table table1=new Table();
       table.top();
       table.setFillParent(true);
       table.add(WorldLb).expandX().padTop(20);
       table.add(MoneyText).expandX().padTop(20);
       table.add(TimeLabel).expandX().padTop(20);
       table.row();
       table.add(LevelLable).expandX();
       table.add(MoneyLabel).expandX();
       table.add(CoundDownLabel).expandX();
        table.row();
        table.add(Fuel);
        table.row();
        table.add(JetPack_Int);
       table1.right().bottom();
       stage.addActor(table);
       stage.addActor(table1);
    //   table.debug();





    }
    public void update(float dt){
        Timer+=dt;
        if(Timer>=1) {
            time--;
            CoundDownLabel.setText(String.format("%03d", time));
            if (Timer >= 1 && SpaceMan.manBody.getLinearVelocity().x > 0) {
                int c = random.nextInt(30);
                Money += c;
                MoneyLabel.setText(String.format("%04d", Money));

            }
            Timer = 0;
        }
        if(Controller.isJumpPressed() &&  FUEL>0){
            FUEL--;
            JetPack_Int.setText(String.format("%03d", (int) FUEL));
        }else if(FUEL<100 && !Controller.JumpPressed && SpaceMan.manBody.getLinearVelocity().y<=0 ) {
            FUEL+=0.2f;
            JetPack_Int.setText(String.format("%03d", (int) FUEL));

        }
        if(time<=0){
            SpaceMan.IsToDie=true;
            time=0;
        }

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
