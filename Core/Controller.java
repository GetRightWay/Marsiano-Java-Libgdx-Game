package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Items.ItemDefenesions;
import com.mygdx.game.Sprites.SpaceMan;

/**
 * Created by harut on 09.03.2018.
 */

public class Controller {
    private Sound shoot;
    private Image JumpImg, Leftarrow, RightArrow,Attack,FreezeAttack;
    Viewport viewport;
  static   public boolean JumpPressed;

    public  boolean isFreezeAttackedPressed() {
        return IsFreezeAttackedPressed;
    }

    private   boolean IsFreezeAttackedPressed;
public static ItemDefenesions itemDefenesions;
    public boolean isAttackPressed() {
        return IsAttackPressed;
    }

    public boolean IsAttackPressed;
   static public boolean isJumpPressed() {
        return JumpPressed;
    }

    public boolean isLeftPressed() {
        return LeftPressed;
    }

    public boolean isRightPressed() {
        return RightPressed;
    }

    private boolean LeftPressed;
    private boolean RightPressed;
    OrthographicCamera camera;
    public Stage stage;
   public  Controller() {
  shoot=SpaceGame.manager.get("sounds/SoundEffects/Sword.wav", Sound.class);
        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        Attack=new Image(new Texture(Gdx.files.internal("Attack.png")));
        Attack.setSize(95,95);
        FreezeAttack=new Image((new Texture("Frezze.png")));
        FreezeAttack.setSize(95,95);
        if(SpaceMan.state!= SpaceMan.StateOfSpaceMan.Die) {
            Attack.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    IsAttackPressed = true;
                    return true;
                }

                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    IsAttackPressed = false;
                    Gdx.app.log("Type", " is  nulled");
                }
            });
            FreezeAttack.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    IsFreezeAttackedPressed = true;
                    return true;
                }

                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    IsFreezeAttackedPressed = false;
                    Gdx.app.log("Type", " is  nulled");
                }
            });
            JumpImg = new Image(new Texture(Gdx.files.internal("IconsForControll/Jump.png")));
            JumpImg.setSize(100, 100);
            JumpImg.addListener(new InputListener() {


                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    JumpPressed = true;
                    PlayerScreen.PlayJetSound(JumpPressed, PlayerScreen.JetPackStart);
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    JumpPressed = false;

                }
            });
            Leftarrow = new Image(new Texture(Gdx.files.internal("IconsForControll/ArrowLeft.png")));
            Leftarrow.setSize(95, 95);
            Leftarrow.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    LeftPressed = true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    LeftPressed = false;
                }
            });

            RightArrow = new Image(new Texture(Gdx.files.internal("IconsForControll/ArrowRight.png")));
            RightArrow.setSize(95, 95);
            RightArrow.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    RightPressed = true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    RightPressed = false;
                }
            });
        }
        Table secTable=new Table();
        secTable.add();
     //   secTable.setFillParent(true);
        Table table=new Table();
        table.setPosition(0,10);
        secTable.setPosition(Gdx.graphics.getWidth()-JumpImg.getWidth(),JumpImg.getHeight());
        table.left().bottom();
        table.add(Leftarrow).size(95,95);
        table.add();
        table.add(RightArrow).size(95,95);
        table.row();
        table.add(Attack).size(95,95);
        table.add(FreezeAttack).size(95,95);
        table.add();
        secTable.add(JumpImg).size(95,95);
        table.pack();
        secTable.pack();
        stage.addActor(table);
        stage.addActor(secTable);

    }
   public void drawNextStage(){
        stage.draw();
   }
public void resize(int width, int height){
       viewport.update(width,height);
}
public static void ShootSound(Sound shoot){
    if(SpaceMan.state!= SpaceMan.StateOfSpaceMan.Die) {
            shoot.play();
    }

}


}


