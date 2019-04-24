package com.mario.GrinningGameMoroi.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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


public class GameOver extends InputAdapter implements Screen {
   private MyGdxGame game;
   private Texture texture;

    private Viewport viewport;
    private Stage stage;
    private Label score;
    private boolean pluse;
    private int time,timer;
    private BitmapFont font;
    private boolean rest;
    private Sound sound;
    private Preferences pre;
    public GameOver(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        pre= Gdx.app.getPreferences("My Preferences");
        timer=0;
         texture=(Texture) game.manager().get("gameover.png");

         sound= game.manager().get("sounds/smb_gameover.wav", Sound.class);
        Preferences pre=Gdx.app.getPreferences("My Preferences");
        if (pre.getInteger("music")==0){
        sound.play();}
        rest=false;
        pluse=true;

        OrthographicCamera cam=new OrthographicCamera(400,200);
        viewport=new StretchViewport(400,900);
        stage=new Stage(viewport);
        cam.position.set(1,1,0);


         font=new BitmapFont();
        font.getData().setScale(0.7f,3);

        stage=new Stage(viewport);
         score=new Label("Touch to restart", new Label.LabelStyle(font, Color.WHITE));
        Table table=new Table();
        table.bottom();

        table.add(score).size(10,10);
        table.add().height(200);
        table.add().width(80);
        table.row();
        table.add().height(100);
        Image img1=new Image((Texture) game.manager().get("home.png"));
        Table table1=new Table();
        table1.top().left();
        table1.add();
        table1.add(img1).size(90f,200f);
        table1.add();
        table.setFillParent(true);
        table1.setFillParent(true);
        img1.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                getGame();
                return true;
            }
        });
        stage.addActor(table1);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);


        game.setad(true);
        game.fullad();

        pre.putInteger("Score",0).flush();
    }

    @Override
    public void render(float delta) {

//        stage.setDebugAll(true);
        stage.act();
        if (time<=255&&pluse){time=time+(5);}
        else if (time>=0){time=time-(5);pluse=false;if(time==0){pluse=true;}}
        score.setColor(time/255f,time/255f,time/255f,1);

        stage.getBatch().begin();
        stage.getBatch().draw(texture,0,0,400,900);
        stage.getBatch().end();

        timer=time+1;
        if (timer>30){
        if (rest){
            game.setScreen(new Start(game));
            dispose();}
        else if (Gdx.input.justTouched()){
        game.setScreen(new Screens(game));
        dispose();
        }}
        stage.draw();


        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)){game.setScreen(new Start(game));}
        if (timer<30){rest=false;}
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
        sound.stop();
        stage.clear();
        stage.dispose();
        font.dispose();
//        pre.clear();



    }

    private void getGame() {
rest=true;
    }
}
