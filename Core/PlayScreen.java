package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Sprites.SpaceMan;

/**
 * Created by interstaller on 4/1/2018.
 */

public class PlayScreen implements Screen {
    private Viewport port;
    private SpaceMan spaceMan;
    private Sprite Anim;
    private Stage stage;
    private Table table,TabelBut;
    private Game game;
    public static   boolean IsPressed;
    public PlayScreen(Game game){
        this.game=game;
        Anim=new Sprite();
        port = new FillViewport(SpaceGame.Virtual_W, SpaceGame.Virtual_H, new OrthographicCamera());
        stage = new Stage(port, ((SpaceGame) game).batch);
        Gdx.input.setInputProcessor(stage);
        table=new Table();
        TabelBut=new Table();
        TabelBut.center();
        TabelBut.setFillParent(true);
        table.center();
        table.setFillParent(true);
        Image image=new Image(new Texture("MainScreen.png"));
        Image Play=new Image(new Texture("Play.png"));

        Image Exit=new Image(new Texture("Exit.png"));
        Play.setSize(90,80);
        TabelBut.add(Play);
        TabelBut.row();

        TabelBut.row();
        TabelBut.add(Exit);
        Play.addListener(new InputListener() {
                             @Override
                             public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                 IsPressed = true;

                                 return true;
                             }

                             @Override
                             public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                                 IsPressed = false;

                             }
                         });

        Exit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                dispose();
                return true;

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {


            }
        });
        table.add(image);
        stage.addActor(table);
        stage.addActor(TabelBut);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
      if(IsPressed){
          game.setScreen(new PlayerScreen((SpaceGame)game));

          dispose();

      }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
      stage.dispose();
    }
}
