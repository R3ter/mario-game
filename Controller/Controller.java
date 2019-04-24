package com.mario.GrinningGameMoroi.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mario.GrinningGameMoroi.MyGdxGame;
import com.mario.GrinningGameMoroi.Player.Player;

/**
 * Created by waleed bas on 01/24/18.
 */

public class Controller{
    private Stage stage;
    private Player player;
    private MyGdxGame game;
    private com.mario.GrinningGameMoroi.screen.Screens screens;
    private int time;
    private Image img1,img;
    private Touchpad touchpad;
    private Drawable touchKnob;
//    boolean fire;
    private Preferences pre;
    private Preferences sounds;
//    boolean fast ;
    private Texture more;
    private Sound musicpause;
    private   boolean right,left,up;
    private Viewport viewport;
    public Controller(Player player, com.mario.GrinningGameMoroi.screen.Screens screens, MyGdxGame game) {
        this.screens=screens;
        this.player=player;
        this.game=game;
        musicpause=game.manager().get("sounds/smb_pause.wav",Sound.class);
//        fire = false;
//        fast = false;
        left=false;right=false;up=false;
        viewport=new StretchViewport(400,900);
        stage=new Stage(viewport);

        controller();

    }


    public void controller(){


        if (screens.level==20){
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
            stage.addActor(table12);}


        if (screens.level==20){
            more=game.manager().get("morelevels.png");
        }
        sounds= Gdx.app.getPreferences("My Preferences");
        pre= Gdx.app.getPreferences("settings");
        if (pre.getString("settings").equals("")){pre.putString("settings","arrows").flush();}
        Gdx.input.setInputProcessor(stage);

        if (pre.getString("settings").equals("arrows")) {
         img=new Image(new Texture("buttons/arrow.png"));
        Table table=new Table();
        table.add();
        table.add(img).size(5f,30f);
        table.add();
        table.setPosition(20f,70);
        img.setScale(15f,7f);
            img.setColor(0/255f,255/255f,255/255f,1f);
        stage.addActor(table);
        img.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
               right=true;
                img.setColor(255/255f,108/255f,108/255f,1f);
                return true;}

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                img.setColor(0/255f,255/255f,255/255f,1f);
            right=false;
            }
        });



         img1=new Image(new Texture("buttons/arrow1.png"));
        Table table1=new Table();
        table1.add();
        table1.add(img1).size(5f,30f);
        table1.add();
        table1.setPosition(100f,70);
        img1.setScale(15,7);
            img1.setColor(0/255f,255/255f,255/255f,1f);
            stage.addActor(table1);
        table1.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                left=true;
                img1.setColor(255/255f,108/255f,108/255f,1f);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                img1.setColor(0/255f,255/255f,255/255f,1f);
                left=false;
            }
        });


        }
       if (pre.getString("settings").equals("pad")) {
           final Skin touchpadSkin = new Skin();
           touchpadSkin.add("touchBackground", game.manager().get("buttons/touchbackground.png"));
           Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
           touchpadSkin.add("touchKnob", game.manager().get("buttons/touchknob.png"));
           final Drawable touchBackground = touchpadSkin.getDrawable("touchBackground");
           touchKnob = touchpadSkin.getDrawable("touchKnob");
           touchpadStyle.background = touchBackground;
           touchpadStyle.knob = touchKnob;
           touchKnob.setMinHeight(80);
           touchKnob.setMinWidth(30);
           touchpad = new Touchpad(0.1f, touchpadStyle);
           touchpad.setBounds(10, 100, 150, 140);
           touchpad.getResetOnTouchUp();
           touchpad.setOriginX(200);
//           touchpad.setColor(73/255f,176/255f,255/255f,0.1f);
           stage.addActor(touchpad);
           Gdx.input.setInputProcessor(stage);


           touchpad.getListeners().insert(0, new InputListener() {
               private Vector2 tmpVec = new Vector2();
                       @Override
                       public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                           if (touchpad.isTouched()) return false;
                           restrictAlongX(event);
//                           touchpad.setColor(73/255f,176/255f,255/255f,1f);
                           return true;
                       }

                       @Override
                       public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                           restrictAlongX(event);
//                           touchpad.setColor(73/255f,176/255f,255/255f,0.1f);

                       }

                       @Override
                       public void touchDragged(InputEvent event, float x, float y, int pointer) {
                           restrictAlongX(event);
//                           touchpad.setColor(73/255f,176/255f,255/255f,1f);
                       }
               private void restrictAlongX(InputEvent inputEvent) {
                   tmpVec.set(0f, touchpad.getHeight() / 2);
                   touchpad.localToStageCoordinates(tmpVec);
                   inputEvent.setStageY(tmpVec.y);
               }
                   });}


        Image stop =new Image((Texture) game.manager().get("buttons/pause.png"));
        Table table3=new Table();
        table3.top().right();
        table3.add();
        table3.add(stop).size(10f,27f).spaceTop(100);
        stop.setScale(3f,5f);
        table3.add();
        table3.setPosition(370f,790f);
        stop.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (sounds.getInteger("sound")==0){
                musicpause.play();}
                screens.pause();
                return true;
                }
        });
        stage.addActor(table3);



          final Image jump =new Image((Texture) game.manager().get("buttons/arrowup.png"));
         Table table2=new Table();
        table2.top().right();
        table2.add();
        table2.add(jump).size(13f,9f).spaceTop(100);
        jump.setScale(5f,20f);
        table2.add();
        table2.setPosition(330f,70f);
        table2.setColor(jump.getColor());
        jump.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                jump.setColor(Color.WHITE);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                jump.setColor(73/255f,176/255f,255/255f,1f);
                up=true;
                return true;
            }
        });
        stage.addActor(table2);


    }

public void recontroller(){Gdx.input.setInputProcessor(stage);}
public void draw(){
    stage.act(Gdx.graphics.getDeltaTime());

    stage.draw();

    time=time +1;
    if (pre.getString("settings").equals("pad")){
        touchKnob.setTopHeight(0);
        touchKnob.setBottomHeight(0);

        if (!touchpad.isTouched()&&!player.hited){
            player.body.applyLinearImpulse(new Vector2(-player.body.getLinearVelocity().x/5f,0)
                    ,player.body.getWorldCenter(),true);}

            if ((  player.body.getLinearVelocity().x > -2)&&touchpad.getKnobPercentX()<0) {
                player.body.applyLinearImpulse(new Vector2(touchpad.getKnobPercentX()/15, 0f), player.body.getWorldCenter(), true);
            }
            if (player.body.getLinearVelocity().x < 2&&touchpad.getKnobPercentX()>0 ) {
              player.body.applyLinearImpulse(new Vector2(touchpad.getKnobPercentX()/15 , 0f), player.body.getWorldCenter(), true);
            }
        }


if (pre.getString("settings").equals("arrows")){
    if (!right&&!left&&!player.hited){
        player.body.applyLinearImpulse(new Vector2(-player.body.getLinearVelocity().x/5f,0)
                ,player.body.getWorldCenter(),true);}

    if ((right && player.body.getLinearVelocity().x > -2)) {
    player.body.applyLinearImpulse(new Vector2(-0.1f,0), player.body.getWorldCenter(), true);
}
    if (player.body.getLinearVelocity().x < 2 &&left) {
        player.body.applyLinearImpulse(new Vector2(0.1f,0), player.body.getWorldCenter(), true);
    }}






    if ((player.body.getLinearVelocity().y==0||player.onground)){
        time=0;
//        if (up&&Gdx.input.getX() < viewport.getScreenWidth() / 2){return;}
            if (up) {if (sounds.getInteger("sound")==0){game.manager().get("sounds/smb_jump-small.wav", Sound.class).play();}
                player.body.setLinearVelocity(player.body.getLinearVelocity().x,4);
                up=false;
            }
        }
        stage.getBatch().begin();
    if (screens.level==20){stage.getBatch().draw(more, 120, 530,200,200);}
    stage.getBatch().end();
}
public void dispos(){
    stage.dispose();
}
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}














