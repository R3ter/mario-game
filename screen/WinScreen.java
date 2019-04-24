package com.mario.GrinningGameMoroi.screen;

import com.mario.GrinningGameMoroi.MyGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class WinScreen implements Screen {
    private MyGdxGame game;
    public Screens screens;
    private Stage stage;
    private Table table1;
    private Preferences pre;
    private Viewport viewport;
    private boolean nextlevel;
    private Label score;
    private Texture textur;
    private boolean start,restart1;
    private int scores;
    private Sprite sprite;
    private Image image1,image2,image3;
    private int i,o,i1,o1,i2,o2;
   private Animation<TextureRegion> win;
    private int time;
    private Sound sound;
    public WinScreen(Screens screens, MyGdxGame game) {
        this.game = game;
        this.screens=screens;

    }

    @Override
    public void show() {

        pre= Gdx.app.getPreferences("My Preferences");
        textur= game.manager().get("buttons/winscreen.png");
//        texture= game.manager().get("buttons/baby.png");
        final Image home=new Image((Texture) game.manager().get("home.png"));
        if (pre.getInteger("music")==0){
       sound = game.manager().get("sounds/smb_stage_clear.wav", Sound.class);
        sound.play();}
        nextlevel=false;
        start=false;restart1=false;

         viewport=new StretchViewport(400,900);
        stage=new Stage(viewport);

        Gdx.input.setInputProcessor(stage);




        BitmapFont font=new BitmapFont();
        font.getData().setScale(0.7f,3);
        scores = 0;
        score=new Label("Scores = "+pre.getInteger("levelscore"), new Label.LabelStyle(font, Color.WHITE));

        sprite=new Sprite();
        sprite.setSize(30f,90f);
        sprite.setPosition(100,330);



        Table table=new Table();
        table.top().left();
        table.add();
        table.add(home).size(90f,200f);
        table.add();
        table.setFillParent(true);

        final Image next=new Image((Texture) game.manager().get("buttons/nextbutton.png"));
        final Image restart=new Image((Texture) game.manager().get("buttons/restartbutton.png"));
         table1=new Table();
        table1.setSize(4,9);
        table1.bottom();
        table1.add(score).size(0.1f,0.1f).width(100).height(100).center().spaceLeft(100);

        table1.row();
        table1.add().height(20);
        table1.row();
        table1.add(next).height(100).center().expandX().spaceBottom(30);
        table1.row();
        table1.add(restart).height(100).center().expandX();
        table1.add().height(170);
        table1.setFillParent(true);
        next.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                nextlevel=true;
                return true;
            }
        });
        restart.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                restart1=true;
                return true;
            }
        });
        home.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                start=true;
                return true;
            }
        });

        stage.addActor(table1);
        stage.addActor(table);


        game.setad(true);
        game.fullad();
         image1=new Image((Texture)game.manager().get("buttons/heart1.png"));
         image2=new Image((Texture)game.manager().get("buttons/heart1.png"));
         image3=new Image((Texture)game.manager().get("buttons/heart1.png"));
        Table hearts=new Table();
        hearts.setPosition(150,500);
           if (pre.getInteger("current stars")>2){
            hearts.add().width(100);
            hearts.add(image1).size(30,80);
            hearts.add().width(30);
            hearts.add(image2).size(30,80);
            hearts.add().width(30);
            hearts.add(image3).size(30,80);}
        else if (pre.getInteger("current stars")>1){
               hearts.add().width(100);
               hearts.add(image1).size(30,80);
               hearts.add().width(30);
               hearts.add(image2).size(30,80);
               hearts.add().width(30);
               hearts.add().size(30,80);}

       else if (pre.getInteger("current stars")>0){
               hearts.add().width(100);
               hearts.add(image1).size(100,100);
               hearts.add().width(30);
               hearts.add().size(30,80);
               hearts.add().width(30);
               hearts.add().size(30,80);}

//        }
        stage.addActor(hearts);
        i=1000;
        o=1000;
        i1=1000;
        o1=1000;
        i2=1000;
        o2=1000;

    }

    @Override
    public void render(float delta) {


//            if (i>30){image2.sizeBy(i);}
            if (i>=30){image1.setSize(i,o);i=i-1;i=i-1;i=i-1;i=i-1;i=i-1;i=i-1;
                i=i-1;i=i-1;i=i-1;i=i-1;i=i-1;i=i-1;i=i-1;i=i-1;i=i-1;i=i-1;i=i-1;i=i-1;
                i=i-1;i=i-1;i=i-1;i=i-1;}
            if (o>=95){image1.setSize(i,o);o=o-1;o=o-1;o=o-1;o=o-1;o=o-1;o=o-1;o=o-1;o=o-1;o=o-1;o=o-1;
                o=o-1;o=o-1;o=o-1;o=o-1;o=o-1;o=o-1;o=o-1;o=o-1;o=o-1;o=o-1;o=o-1;o=o-1;o=o-1;
                o=o-1;o=o-1;o=o-1;}
            if (o1>=95){image2.setSize(i1,o1);o1=o1-1;o1=o1-1;o1=o1-1;o1=o1-1;o1=o1-1;o1=o1-1;o1=o1-1;o1=o1-1;o1=o1-1;o1=o1-1;
                o1=o1-1;o1=o1-1;o1=o1-1;o1=o1-1;o1=o1-1;o1=o1-1;o1=o1-1;o1=o1-1;o1=o1-1;o1=o1-1;o1=o1-1;o1=o1-1;o1=o1-1;
                o1=o1-1;o1=o1-1;o1=o1-1;}
            if (i1>=30){image2.setSize(i1,o1);i1=i1-1;i1=i1-1;i1=i1-1;i1=i1-1;i1=i1-1;i1=i1-1;
                i1=i1-1;i1=i1-1;i1=i1-1;i1=i1-1;i1=i1-1;i1=i1-1;i1=i1-1;i1=i1-1;i1=i1-1;i1=i1-1;i1=i1-1;i1=i1-1;
                i1=i1-1;i1=i1-1;i1=i1-1;i1=i1-1;}

             if (i2>=30){image3.setSize(i2,o2);i2=i2-1;i2=i2-1;i2=i2-1;i2=i2-1;i2=i2-1;i2=i2-1;
                i2=i2-1;i2=i2-1;i2=i2-1;i2=i2-1;i2=i2-1;i2=i2-1;i2=i2-1;i2=i2-1;i2=i2-1;i2=i2-1;i2=i2-1;i2=i2-1;
                i2=i2-1;i2=i2-1;i2=i2-1;i2=i2-1;}
            if (o2>=95){image3.setSize(i2,o2);o2=o2-1;o2=o2-1;o2=o2-1;o2=o2-1;o2=o2-1;o2=o2-1;o2=o2-1;o2=o2-1;o2=o2-1;o2=o2-1;
                o2=o2-1;o2=o2-1;o2=o2-1;o2=o2-1;o2=o2-1;o2=o2-1;o2=o2-1;o2=o2-1;o2=o2-1;o2=o2-1;o2=o2-1;o2=o2-1;o2=o2-1;
                o2=o2-1;o2=o2-1;o2=o2-1;}


        stage.act();
        time=time+1;
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (scores<pre.getInteger("Score")){
            scores=scores+1;}
            score.setText("Extra Scores = "+scores);


//        if (Gdx.input.justTouched()){scores=pre.getInteger(screens.level+"Score");}
//        table1.setDebug(true);
//        sprite.setRegion(walk().getKeyFrame(time / 20, false));

        stage.getBatch().begin();
        stage.getBatch().draw(textur,0,200,400,900);
//        sprite.draw(stage.getBatch());
        stage.getBatch().end();
        if (time>20) {
            if (nextlevel) {
                screens.level = screens.level + 1;
                pre.putInteger("level",screens.level).flush();
                game.setScreen(new Screens(game));
                dispose();
            }
            if (restart1) {
                game.setScreen(new Screens(game));
                dispose();
            }
            if (start) {
                game.setScreen(new Start(game));
                dispose();
            }
        }
        if (time<20){start=false;nextlevel=false;restart1=false;}
        stage.draw();
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)){game.setScreen(new Start(game));}

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
        table1.clear();
        stage.dispose();
        score.clear();
        pre.putInteger("Score",0).flush();
        if (pre.getInteger("music")==0){
            sound.stop();}
    }


//    private Animation<TextureRegion> walk() {
//
//
////        Array<TextureRegion> fram = new Array<TextureRegion>();
////        for (int i = 0; i < 9; i++) {
////            fram.add(new TextureRegion(texture, 50*i,10,50,45));
////            win = new Animation<TextureRegion>(0.4f, fram);
////        }
////
////        fram.clear();
////
////        return win;
////    }
    }
