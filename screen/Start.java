package com.mario.GrinningGameMoroi.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mario.GrinningGameMoroi.MyGdxGame;
import com.sun.jndi.toolkit.url.Uri;


/**
 * Created by waleed bas on 01/24/18.
 */

public class Start implements Screen {
    private Stage stage;
    private Screens screens;
    private MyGdxGame game;
//    private boolean start;
    private Viewport viewport;
    private Texture musicicon;
    private Texture background;
    private TextureRegion textureRegion;
    private Array<TextureRegion> array;
    private Preferences pre,prea;
    private Texture nomusic;
    private int time;
    private float[] times;
//    private Image icon;
//    private Table icontab;
    private Texture sounds,nosounds;
    private OrthographicCamera camera;
    public Start(MyGdxGame game){
        this.game=game;

    }
    @Override
    public void show() {
        screens=new Screens(game);
        screens.show();
        camera=new OrthographicCamera(400,200);
        viewport=new StretchViewport(400,900,camera);
        camera.position.set(1,1,1);
        camera.update();
        stage=new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        background=game.manager().get("buttons/start.png");
        Texture cloud=game.manager().get("buttons/cloud.png");
        musicicon=game.manager().get("buttons/music.png");

        pre= Gdx.app.getPreferences("My Preferences");
        prea= Gdx.app.getPreferences("level");

        pre.putInteger("Score",0).flush();



        sounds=game.manager().get("buttons/icon.png");
        nosounds=game.manager().get("buttons/icon2.png");
        Image settings=new Image((Texture)game.manager().get("buttons/settings.png"));
        Image img=new Image((Texture)game.manager().get("startgame.png"));
        Image im=new Image((Texture)game.manager().get("buttons/mario.png"));
        nomusic=game.manager().get("buttons/nomusic.png");
        Table table=new Table();
        table.add();
        table.add(im).size(100f,200f).spaceBottom(200);
        table.row();
        table.add().height(100);
        table.add(img).size(100f,100f).spaceTop(50);
//        table.add().spaceBottom(50);
        table.add().height(100);
        table.setPosition(200.5f,570);
        stage.addActor(table);
        img.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                screens.level=1;
                game.setScreen(new Screens(game));dispose();
                return true;
            }
        });



        Image img12=new Image((Texture)game.manager().get(("donate.png")));
        Image rate=new Image((Texture)game.manager().get(("rate.png")));
        Table table12=new Table();
        table12.add().height(130);
        table12.add(img12).size(70,70f);
        table12.row();
        table12.add();
        table12.add(rate).size(70,70f);
        table12.setPosition(35,810);
        stage.addActor(table12);
        img12.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Gdx.net.openURI("https://www.paypal.me/WaleedSukhon");
            return true;}
        });

        rate.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Gdx.net.openURI("https://play.google.com");
                return true;}
        });

        Image img1=new Image((Texture)game.manager().get(("selectlevel.png")));
        Table table1=new Table();
        table1.add();
        table1.add(img1).size(100f,100f);
        table1.add();
        table1.setPosition(200.5f,220);
        stage.addActor(table1);
        img1.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                game.setScreen(new NewSelectLevel(game));
                dispose();
                return true;
            }
        });

        if (prea.getInteger("level")!=0){
        Image imge=new Image((Texture)game.manager().get("button_continue.png"));
        Table tabl=new Table();
        tabl.add();
        tabl.add(imge).size(100f,100f);
        tabl.add();
        tabl.setPosition(200.5f,500);
        stage.addActor(tabl);
        imge.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                screens.level=prea.getInteger("level");
                game.setScreen(new Screens(game));
                dispose();
                return true;
            }
        });}



        Table table2=new Table();
        table2.add(settings).size(20,60);
        table2.setPosition(170,100);



        Image image=new Image((Texture)game.manager().get("buttons/sound.png"));
        Table sound =new Table();
        sound.add(image).size(100,100);
        image.setScale(0.17f,0.7f);
        sound.setPosition(238.9f,110);


        Image musicimg=new Image((Texture)game.manager().get("buttons/sound.png"));
        Table music =new Table();
        music.add(musicimg).size(100,100);
        musicimg.setScale(0.17f,0.7f);
        music.setPosition(270.9f,110);


        stage.addActor(sound);
        stage.addActor(table2);
        stage.addActor(music);


        settings.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new com.mario.GrinningGameMoroi.screen.Settings(game));dispose();
                return true;
            }
        });
        music.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (pre.getInteger("music")==0){
                    pre.putInteger("music",1).flush();
                }
                else if (pre.getInteger("music")==1){
                    pre.putInteger("music",0).flush();
                }
                screens.stopmusic();
                return true;
            }
        });
        sound.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (pre.getInteger("sound")==0){
                    pre.putInteger("sound",1).flush();
                }
                else if (pre.getInteger("sound")==1){
                    pre.putInteger("sound",0).flush();
                }

                return true;}
        });

        game.setad(true);
        game.fullad();

        textureRegion=new TextureRegion(cloud,0 ,14,459,154);
        TextureRegion textureRegion1=new TextureRegion(cloud,475,168,405,243);
        TextureRegion textureRegion2=new TextureRegion(cloud,1,202,205,156);

            array=new Array<TextureRegion>();
            array.add(textureRegion);
            array.add(textureRegion1);
            array.add(textureRegion2);

//        array.clear();

        times=new float[10];
        times[1]=100;

        times[2]=200;
        times[3]=400;
        times[4]=-100;

    }

    @Override
    public void render(float delta) {

        times[1]=times[1]-1;
        times[2]=times[2]-1;
        times[3]=times[3]-1.5f;
        times[4]=times[4]-1;
        if (times[1]<-75){times[1]=500;}
        if (times[2]<-50){times[2]=550;}
        if (times[3]<-100){times[3]=600;}
        if (times[4]<-300){times[4]=1200;}

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            Gdx.app.exit();
            }

        Gdx.gl20.glClearColor(93/255,158/255,252/255,1);
        stage.act();
        stage.getBatch().setProjectionMatrix(camera.combined);
        stage.getBatch().begin();
//        stage.getBatch().draw(background,0,0,400,900);

        stage.getBatch().draw(array.get(1),times[2],100,50,150);
        stage.getBatch().draw(array.get(0),times[1]-100,400,50,110);
        stage.getBatch().draw(array.get(0),times[1],730,50,110);
        stage.getBatch().draw(array.get(1),times[4]/2,600,50,150);
        stage.getBatch().draw(array.get(2),times[3],230,40,150);

        stage.getBatch().end();
        stage.draw();
        stage.getBatch().begin();
        if (pre.getInteger("music")==1){
            stage.getBatch().draw(nomusic,225,80,10,40);}
        if (pre.getInteger("music")==0){
            stage.getBatch().draw(musicicon,225,80,10,40);}
        if (pre.getInteger("sound")==1){
        stage.getBatch().draw(nosounds,188,70,20,50);}
        if (pre.getInteger("sound")==0){
            stage.getBatch().draw(sounds,188,70,20,50);
        }
        stage.getBatch().end();


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        game.setad(true);
        game.fullad();
    }

    @Override
    public void dispose() {
        stage.dispose();

    }



}
