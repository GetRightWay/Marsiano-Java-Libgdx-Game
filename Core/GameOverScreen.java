package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Sprites.SpaceMan;

/**
 * Created by interstaller on 3/31/2018.
 */

public class GameOverScreen implements Screen {
    public Viewport viewport;
    public Stage stage;
    public Table table;
    public Table TableForButt;
    private Game game;
    public boolean replay;
    private Controller controller;
    public GameOverScreen(Game game) {
        this.game = game;
        controller=new Controller();
        viewport = new FillViewport(SpaceGame.Virtual_W, SpaceGame.Virtual_H, new OrthographicCamera());
        stage = new Stage(viewport, ((SpaceGame) game).batch);
       Gdx.input.setInputProcessor(stage);
        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.CHARTREUSE);
        table=new Table();
        TableForButt=new Table();
        TableForButt.center();
        table.center();
        replay=false;
        table.setFillParent(true);
        TableForButt.setFillParent(true);
        Image image=new Image(new Texture("GameOverScreen.png"));
        Image Play =new Image(new Texture("Replay.png"));
        Play.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                 replay=true;
                Gdx.app.log("Toched","Ispaley" +replay);
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
replay=false;
            }
        });

        table.add(image);
        TableForButt.add(Play);
        stage.addActor(table);
        stage.addActor(TableForButt);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(replay){
            controller.stage.draw();
           game.setScreen(new PlayerScreen((SpaceGame) game));
      dispose();
}
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
stage.draw();
    }

    @Override
    public void resize(int width, int height) {
viewport.update(width,height);
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
