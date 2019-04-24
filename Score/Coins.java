package com.mario.GrinningGameMoroi.Score;

import com.mario.GrinningGameMoroi.MyGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.mario.GrinningGameMoroi.screen.Screens;


/**
 * Created by waleed bas on 01/23/18.
 */

public class Coins extends Tools {

   private Animation<TextureRegion>coin;
   private int time;
   private Texture text;
    private Preferences pre;
   private boolean take;
   private TextureRegion nul;
    private Sound sound;
    public Coins(MyGdxGame game, Screens screen, float x, float y) {
        super(game,screen, x, y);

        pre= Gdx.app.getPreferences("My Preferences");
        take=false;
        text=new Texture("coin.png");
        nul=new TextureRegion(text,1,1,0,0);
        setTexture(text);
        setPosition(x,y);
        sound = game.manager().get("sounds/smw_coin.wav", Sound.class);
        sound.setVolume(1,10);
    }
private Animation<TextureRegion> animation(){

    Array<TextureRegion> fram = new Array<TextureRegion>();
    for (int i = 1; i < 5; i++) {
        fram.add(new TextureRegion(text, 120*i,55,95,95));
        coin = new Animation<TextureRegion>(0.4f, fram);
    }
    fram.clear();
    return coin;
}
    @Override
    public void drawcoins() {
        time=time+1;
        animation();


        if (!take){
        setRegion(animation().getKeyFrame(time/20,true));}
        else if (take)setRegion(nul);



    }

    @Override
    public void defincoin() {

        setSize(1f,1f);
setScale(0.1f,0.1f);

        bdef = new BodyDef();
        bdef.position.set(getX()+0.06f,getY()+0.05f);
        bdef.type = BodyDef.BodyType.StaticBody;
        body = screen.getWorld().createBody(bdef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shap = new CircleShape();
        shap.setRadius(7 / 100f);
        fixtureDef.shape = shap;
        fixtureDef.filter.maskBits = (short) (2 | 6 | 8|32 );
        fixtureDef.filter.categoryBits=4|2|8|32;
        fixtureDef.isSensor=true;
        body.createFixture(fixtureDef).setUserData(this);
    }

    @Override
    public void defi(float x, float y) {

    }


    public void take(Fixture fa,Fixture fb){
        Filter filter=new Filter();
        filter.categoryBits=16;

        fb.setFilterData(filter);
        take=true;
        if (pre.getInteger("sound")==0){
        sound.play();}
        pre.putInteger("Score",pre.getInteger("Score")+7).flush();
        }
    public void dispose(){
        text.dispose();
        pre.flush();


    }
}
