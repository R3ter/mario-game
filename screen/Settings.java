package com.mario.GrinningGameMoroi.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mario.GrinningGameMoroi.MyGdxGame;

/**
 * Created by waleed bas on 02/03/18.
 */
public class Settings implements Screen {
   private Viewport viewport;
    private Texture texture;
    private Stage stage;
    private Texture ground;
    private Preferences pre;
    private MyGdxGame game;
    public Settings(final MyGdxGame game) {
        this.game=game;
        pre = Gdx.app.getPreferences("settings");
        OrthographicCamera cam = new OrthographicCamera(400, 200);
        viewport = new StretchViewport(400f, 900f, cam);
        BitmapFont font = new BitmapFont();
        font.getData().setScale(1f, 5);
        Label label = new Label("        Controller", new Label.LabelStyle(font, Color.WHITE));
        Label arrows = new Label("Arrows", new Label.LabelStyle(font, Color.WHITE));
        Label draggingpad = new Label("Dragging Pad", new Label.LabelStyle(font, Color.WHITE));
        final Image pad = new Image((Texture) game.manager().get("buttons/pad.png"));
        cam.position.set(0, 0, 1);
        cam.update();
        stage = new Stage(viewport);
        ground=(Texture) game.manager().get("buttons/start.png");
        Gdx.input.setInputProcessor(stage);
        Image image = new Image((Texture) game.manager().get("buttons/arrows.png"));
        Image back = new Image((Texture) game.manager().get("buttons/button.png"));
        texture=(Texture) game.manager().get("buttons/selected.png");
        Table table = new Table();

        table.add(label);
        table.setPosition(180f, 800f);
        Table table1 = new Table();
        table1.add(arrows);
        table1.setPosition(70, 400);
        Table table2 = new Table();
        table2.add(draggingpad);
        table2.setPosition(300, 400);
        Table table3 = new Table();
        table3.add(image).size(100, 200);
        table3.setPosition(70, 200);
        Table table4 = new Table();
        table4.add(pad).size(150, 200);
        table4.setPosition(300, 200);
        Table table5 = new Table();
        table5.add(back).size(50, 90);
        table5.setPosition(40, 800);

        stage.addActor(table5);
        stage.addActor(table2);
        stage.addActor(table4);
        stage.addActor(table1);
        stage.addActor(table3);
        stage.addActor(table);

        back.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Start(game));
                return true;
            }});

        pad.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!(pre.getString("settings")).equals("pad")){
                pre.putString("settings","pad").flush();}
                return true;
            }
        });
        image.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!(pre.getString("settings")).equals("arrows")){
                    pre.putString("settings","arrows").flush();
                }
                return true;
            }
        });
        }

    @Override
    public void show() {
        game.setad(true);
        game.fullad();
    }

    @Override
    public void render(float delta) {
        stage.getBatch().begin();
        stage.getBatch().draw(ground,0,0,400,900);
        stage.getBatch().end();
        if ((pre.getString("settings")).equals("arrows")){
        stage.getBatch().begin();
        stage.getBatch().draw(texture,15,80,110,250);
        stage.getBatch().end();}
        if ((pre.getString("settings")).equals("pad")){
       stage.getBatch().begin();
       stage.getBatch().draw(texture,220,80,160,250);
       stage.getBatch().end();}

        stage.act();
        stage.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)){game.setScreen(new Start(game));}
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
        ground.dispose();
    }
}
