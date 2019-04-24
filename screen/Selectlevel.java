package com.mario.GrinningGameMoroi.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by waleed bas on 01/24/18.
 */

public class Selectlevel implements Screen {
    private Stage stage;

    private Screens screens;
    private com.mario.GrinningGameMoroi.MyGdxGame game;
    private  boolean b;
    private boolean relese;
    private float z;
    private float scroll;
    private Preferences pre;
    private Texture texture;
    private Image img,img1,img2,img3,img4,img5,img6,img7,img8,img9,lock,lock2,lock3,lock4,lock5,lock6,lock7,lock8,
    img10,img11,img12,img13,img14,img15,img16,img17,img18,img19,img20,lock9,lock10,lock11,lock12,lock13,lock14,lock15,lock16
            ,lock17,lock18,lock19,lock120;
    private Viewport viewport;
    private Table table1;
    public Selectlevel(com.mario.GrinningGameMoroi.MyGdxGame game) {
        this.game=game;
        viewport=new StretchViewport(400,900);
    }

    @Override
    public void show() {
        pre= Gdx.app.getPreferences("My Preferences");
        stage=new Stage();
        screens=new Screens(game);
        screens.show();

        stage=new Stage(viewport);

        Gdx.input.setInputProcessor(stage);
        texture=game.manager().get("buttons/background.png");
         img1=new Image((Texture)game.manager().get("buttons/level1.png"));
        if (pre.getBoolean(1+"win")){ img2=new Image((Texture) game.manager().get("buttons/level2.png"));}
        if (pre.getBoolean(2+"win")){ img3=new Image((Texture) game.manager().get("buttons/level3.png"));}
        if (pre.getBoolean(3+"win")){ img4=new Image((Texture) game.manager().get("buttons/level4.png"));}
        if (pre.getBoolean(4+"win")){img5=new Image((Texture) game.manager().get("buttons/level5.png"));}
        if (pre.getBoolean(5+"win")){img6=new Image((Texture) game.manager().get("buttons/level6.png"));}
        if (pre.getBoolean(6+"win")){img7=new Image((Texture) game.manager().get("buttons/level7.png"));}
        if (pre.getBoolean(7+"win")){ img8=new Image((Texture) game.manager().get("buttons/level8.png"));}
        if (pre.getBoolean(8+"win")){img9=new Image((Texture) game.manager().get("buttons/level9.png"));}
        if (pre.getBoolean(9+"win")){ img10=new Image((Texture) game.manager().get("buttons/level10.png"));}
        if (pre.getBoolean(10+"win")){ img11=new Image((Texture) game.manager().get("buttons/level11.png"));}
        if (pre.getBoolean(11+"win")){ img12=new Image((Texture) game.manager().get("buttons/level12.png"));}
        if (pre.getBoolean(12+"win")){ img13=new Image((Texture) game.manager().get("buttons/level13.png"));}
        if (pre.getBoolean(13+"win")){ img14=new Image((Texture) game.manager().get("buttons/level14.png"));}
        if (pre.getBoolean(14+"win")){ img15=new Image((Texture) game.manager().get("buttons/level15.png"));}
        if (pre.getBoolean(15+"win")){ img16=new Image((Texture) game.manager().get("buttons/level16.png"));}
        if (pre.getBoolean(16+"win")){ img17=new Image((Texture) game.manager().get("buttons/level17.png"));}
        if (pre.getBoolean(17+"win")){ img18=new Image((Texture) game.manager().get("buttons/level18.png"));}
        if (pre.getBoolean(18+"win")){ img19=new Image((Texture) game.manager().get("buttons/level19.png"));}
        if (pre.getBoolean(19+"win")){ img20=new Image((Texture) game.manager().get("buttons/level20.png"));}

        if (!pre.getBoolean(1+"win")){lock=new Image((Texture) game.manager().get("buttons/lock.png"));}
        if (!pre.getBoolean(3+"win")){lock2=new Image((Texture) game.manager().get("buttons/lock.png"));}
        if (!pre.getBoolean(4+"win")){lock3=new Image((Texture) game.manager().get("buttons/lock.png"));}
        if (!pre.getBoolean(5+"win")){lock4=new Image((Texture) game.manager().get("buttons/lock.png"));}
        if (!pre.getBoolean(6+"win")){lock5=new Image((Texture) game.manager().get("buttons/lock.png"));}
        if (!pre.getBoolean(7+"win")){lock6=new Image((Texture) game.manager().get("buttons/lock.png"));}
        if (!pre.getBoolean(8+"win")){lock7=new Image((Texture) game.manager().get("buttons/lock.png"));}
        if (!pre.getBoolean(9+"win")){lock8=new Image((Texture) game.manager().get("buttons/lock.png"));}
        if (!pre.getBoolean(10+"win")){lock9=new Image((Texture) game.manager().get("buttons/lock.png"));}
        if (!pre.getBoolean(11+"win")){lock10=new Image((Texture) game.manager().get("buttons/lock.png"));}
        if (!pre.getBoolean(12+"win")){lock11=new Image((Texture) game.manager().get("buttons/lock.png"));}
        if (!pre.getBoolean(13+"win")){lock12=new Image((Texture) game.manager().get("buttons/lock.png"));}
        if (!pre.getBoolean(14+"win")){lock13=new Image((Texture) game.manager().get("buttons/lock.png"));}
        if (!pre.getBoolean(15+"win")){lock14=new Image((Texture) game.manager().get("buttons/lock.png"));}
        if (!pre.getBoolean(16+"win")){lock15=new Image((Texture) game.manager().get("buttons/lock.png"));}
        if (!pre.getBoolean(17+"win")){lock16=new Image((Texture) game.manager().get("buttons/lock.png"));}
        if (!pre.getBoolean(18+"win")){lock17=new Image((Texture) game.manager().get("buttons/lock.png"));}
        if (!pre.getBoolean(19+"win")){lock18=new Image((Texture) game.manager().get("buttons/lock.png"));}
        if (!pre.getBoolean(20+"win")){lock19=new Image((Texture) game.manager().get("buttons/lock.png"));}



//        Image lock9=new Image(new Texture("buttons/lock.png"));
//        Image lock10=new Image(new Texture("buttons/lock.png"));
//        Image lock12=new Image(new Texture("buttons/lock.png"));
//        Image lock13=new Image(new Texture("buttons/lock.png"));
//       Image img10=new Image(new Texture("buttons/level10.png"));
        Image back=new Image((Texture)game.manager().get("buttons/button.png"));

        b=true;




        table1=new Table();

        table1.setSize(4,14);
        table1.top().left();
        table1.add();
        table1.row();
        table1.add(back).size(50,50).height(100);
        table1.row();
        table1.add().height(50);
        table1.row();
        table1.add(img1).height(120);
        table1.add().width(10);

         if (pre.getBoolean(1+"win")){table1.add(img2).height(120).expandX();}
         if (!pre.getBoolean(1+"win")){table1.add(lock).width(50).height(120).expandX();}
        table1.add().width(10f);
        if (pre.getBoolean(2+"win")){table1.add(img3).height(120);}
        if (!pre.getBoolean(2+"win")){table1.add(lock2).width(50).height(120);}
//        if (pre.getBoolean(2+"win")){table1.add(img3).height(120);}
//        if (!pre.getBoolean(2+"win")){table1.add(lock1).width(50).height(120);}
        table1.row();
        table1.add().height(50);
        table1.row();
        if (pre.getBoolean(3+"win")){table1.add(img4).height(120);}
        if (!pre.getBoolean(3+"win")){table1.add(lock3).width(50).height(120);}
        table1.add().width(10);
        if (pre.getBoolean(4+"win")){table1.add(img5).height(120).expandX();}
        if (!pre.getBoolean(4+"win")){table1.add(lock4).width(50).height(120).expandX();}
        table1.add().width(10f);
        if (pre.getBoolean(5+"win")){table1.add(img6).height(120);}
        if (!pre.getBoolean(5+"win")){table1.add(lock5).width(50).height(120);}
        table1.row();
        table1.add().height(50);
        table1.row();
        if (pre.getBoolean(6+"win")){table1.add(img7).height(120);}
        if (!pre.getBoolean(6+"win")){table1.add(lock6).width(50).height(120);}
        table1.add().width(10);
        if (pre.getBoolean(7+"win")){table1.add(img8).height(120).expandX();}
        if (!pre.getBoolean(7+"win")){table1.add(lock7).width(50).height(120).expandX();}
        table1.add().width(10f);
        if (!pre.getBoolean(8+"win")){table1.add(lock8).width(50).height(120);}
        if (pre.getBoolean(8+"win")){table1.add(img9).height(120);}
        table1.row();
        table1.add().height(50);
        table1.row();
        if (pre.getBoolean(9+"win")){table1.add(img10).height(120);}
        if (!pre.getBoolean(9+"win")){table1.add(lock9).width(50).height(120);}
        table1.add().width(10);
        if (pre.getBoolean(10+"win")){table1.add(img11).height(120).expandX();}
        if (!pre.getBoolean(10+"win")){table1.add(lock10).width(50).height(120).expandX();}
        table1.add().width(10f);
        if (!pre.getBoolean(11+"win")){table1.add(lock11).width(50).height(120);}
        if (pre.getBoolean(11+"win")){table1.add(img12).height(120);}
        table1.row();
        table1.add().height(50);
        table1.row();
        if (pre.getBoolean(12+"win")){table1.add(img13).height(120);}
        if (!pre.getBoolean(12+"win")){table1.add(lock12).width(50).height(120);}
        table1.add().width(10);
        if (pre.getBoolean(13+"win")){table1.add(img14).height(120).expandX();}
        if (!pre.getBoolean(13+"win")){table1.add(lock13).width(50).height(120).expandX();}
        table1.add().width(10f);
        if (!pre.getBoolean(14+"win")){table1.add(lock14).width(50).height(120);}
        if (pre.getBoolean(14+"win")){table1.add(img15).height(120);}
        table1.row();
        table1.add().height(50);
        table1.row();
        if (pre.getBoolean(15+"win")){table1.add(img16).height(120);}
        if (!pre.getBoolean(15+"win")){table1.add(lock15).width(50).height(120);}
        table1.add().width(10);
        if (pre.getBoolean(16+"win")){table1.add(img17).height(120).expandX();}
        if (!pre.getBoolean(16+"win")){table1.add(lock16).width(50).height(120).expandX();}
        table1.add().width(10f);
        if (!pre.getBoolean(17+"win")){table1.add(lock17).width(50).height(120);}
        if (pre.getBoolean(17+"win")){table1.add(img18).height(120);}
        table1.row();
        table1.add().height(50);
        table1.row();
        if (pre.getBoolean(18+"win")){table1.add(img19).height(120);}
        if (!pre.getBoolean(18+"win")){table1.add(lock18).width(50).height(120);}
        table1.add().width(10);
        if (pre.getBoolean(19+"win")){table1.add(img20).height(120).expandX();}
        if (!pre.getBoolean(19+"win")){table1.add(lock19).width(50).height(120).expandX();}
        table1.add().width(10f);
        if (pre.getBoolean(20+"win")){table1.add().width(50).height(120);}
        if (!pre.getBoolean(20+"win")){table1.add().height(120);}


        table1.setFillParent(true);
        stage.addActor(table1);


        game.setad(true);
        game.fullad();
        img1.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (b){
                    screens.level = 1;
                    game.setScreen(new Screens(game));
                    dispose();}

            }
        });


        if (pre.getBoolean(2+"win")){

        img3.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (b){
                    if (pre.getBoolean(2+"win")){
                    screens.level = 3;

                    game.setScreen(new Screens(game));
                    dispose();}}
            }
        });}

        if (pre.getBoolean(1+"win")) {
            img2.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    if (b) {
                        if (pre.getBoolean(1 + "win")) {
                            screens.level = 2;
                            game.setScreen(new Screens(game));
                            dispose();
                        }
                    }
                }
            });
        }

        if (pre.getBoolean(3+"win")){
        img4.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b){
                    if(pre.getBoolean(3+"win")){
                    screens.level = 4;


                    game.setScreen(new Screens(game));
                    dispose();}}

            }
        });}

        if (pre.getBoolean(4+"win")){
        img5.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b){
                    if (pre.getBoolean(4+"win")){
                    screens.level = 5;

                    game.setScreen(new Screens(game));
                    dispose();}}

            }
        });}


        if (pre.getBoolean(5+"win")){
        img6.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b){
                    if (pre.getBoolean(5+"win")){
                        screens.level = 6;

                        game.setScreen(new Screens(game));
                        dispose();}}

            }
        });}
        if (pre.getBoolean(6+"win")){
        img7.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b){
                    if (pre.getBoolean(6+"win")){
                        screens.level = 7;
                        game.setScreen(new Screens(game));
                        dispose();}}

            }
        });}
        if (pre.getBoolean(7+"win")){
        img8.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b){
                    if (pre.getBoolean(7+"win")){
                        screens.level = 8;
                        game.setScreen(new Screens(game));
                        dispose();}}

            }
        });}if (pre.getBoolean(8+"win")){
        img9.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b){
                    if (pre.getBoolean(8+"win")){
                        screens.level = 9;
                        game.setScreen(new Screens(game));
                        dispose();}}

            }
        });}
        if (pre.getBoolean(9+"win")){
        img10.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b){
                    if (pre.getBoolean(9+"win")){
                        screens.level = 10;
                        game.setScreen(new Screens(game));
                        dispose();}}

            }
        });}
        if (pre.getBoolean(10+"win")){
        img11.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b){
                    if (pre.getBoolean(10+"win")){
                        screens.level = 11;
                        game.setScreen(new Screens(game));
                        dispose();}}

            }
        });}
        if (pre.getBoolean(11+"win")){
        img12.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b){
                    if (pre.getBoolean(11+"win")){
                        screens.level = 12;
                        game.setScreen(new Screens(game));
                        dispose();}}

            }
        });}
        if (pre.getBoolean(12+"win")){
            img13.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                    if (b){
                        if (pre.getBoolean(12+"win")){
                            screens.level = 13;
                            game.setScreen(new Screens(game));
                            dispose();}}

                }
            });}
        if (pre.getBoolean(13+"win")){
            img14.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                    if (b){
                        if (pre.getBoolean(13+"win")){
                            screens.level = 14;
                            game.setScreen(new Screens(game));
                            dispose();}}

                }
            });}
        if (pre.getBoolean(14+"win")){
            img15.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                    if (b){
                        if (pre.getBoolean(14+"win")){
                            screens.level = 15;
                            game.setScreen(new Screens(game));
                            dispose();}}

                }
            });}
        if (pre.getBoolean(15+"win")){
            img16.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                    if (b){
                        if (pre.getBoolean(15+"win")){
                            screens.level = 16;
                            game.setScreen(new Screens(game));
                            dispose();}}

                }
            });}
        if (pre.getBoolean(16+"win")){
            img17.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                    if (b){
                        if (pre.getBoolean(16+"win")){
                            screens.level = 17;
                            game.setScreen(new Screens(game));
                            dispose();}}

                }
            });}

        if (pre.getBoolean(17+"win")){
            img18.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                    if (b){
                        if (pre.getBoolean(17+"win")){
                            screens.level = 18;
                            game.setScreen(new Screens(game));
                            dispose();}}

                }
            });}
        if (pre.getBoolean(18+"win")){
            img19.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                    if (b){
                        if (pre.getBoolean(18+"win")){
                            screens.level = 19;
                            game.setScreen(new Screens(game));
                            dispose();}}

                }
            });}
        if (pre.getBoolean(19+"win")){
            img20.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                    if (b){
                        if (pre.getBoolean(19+"win")){
                            screens.level = 20;
                            game.setScreen(new Screens(game));
                            dispose();}}

                }
            });}

        stage.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                relese=true;
                b=true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                relese=false;
                b=true;
               scroll=y;
                z=y;
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if (y-z<-10||y-z>10){
                b=false;}
                stage.getRoot().moveBy(0f,y-scroll);

            }});

        back.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

            return true;}

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (!b){b=true;}
                game.setScreen(new Start(game));
                dispose();
            }
        });

    }

    @Override
    public void render(float delta) {

        if (relese){
        if (stage.getRoot().getY()<=-17){stage.getRoot().moveBy(0,50f);}
        if (stage.getRoot().getY()>=500){stage.getRoot().moveBy(0,-50);}}
        stage.getBatch().begin();
        stage.getBatch().draw(texture,0,0,400,900);
        stage.getBatch().end();
        stage.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)){game.setScreen(new Start(game));}
    }

    @Override
    public void resize(int width, int height)  {
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

    }

    @Override
    public void dispose() {
        table1.clear();
        stage.dispose();


    }
}
