package com.mario.GrinningGameMoroi.Score;

import com.mario.GrinningGameMoroi.MyGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Created by waleed bas on 01/30/18.
 */

public class DestroyedBricks extends Sprite {
    private MyGdxGame game;
    private static Fixture fixture;
   private static float time,tim;
   private Texture texture;
    private static boolean brea;
    private TextureRegion textureRegion;
    private Preferences pre;
    public DestroyedBricks(MyGdxGame game) {
        time=0;
        if (texture==null){
            pre= Gdx.app.getPreferences("My Preferences");
            texture=new Texture("maps/mario_tileset.png");
        this.game = game;
        textureRegion=new TextureRegion(texture,162,33,9,11);
    }brea=false;}
    public void testroyedBricks(Fixture fixture) {
        this.fixture=fixture;
        brea=true;
        time=0;


    }
    public void drawdest(){
        setRegion(textureRegion,162,33,9,11);
        setPosition(fixture.getBody().getPosition().x,fixture.getBody().getPosition().y);
//        setPosition(1,1);
        setSize(1,1);
        pre.putInteger("Score",pre.getInteger("Score")+3);
        setScale(0.05f,0.05f);
        if (brea){
        time=time+1/90f;
            tim=tim+1/800f;
        game.batch.begin();
        game.batch.draw(textureRegion,fixture.getBody().getPosition().x-0.05f-20/200f,fixture.getBody().getPosition().y-time-0.05f,0.05f,0.05f);
        game.batch.draw(textureRegion,fixture.getBody().getPosition().x-0.05f+20/200f,fixture.getBody().getPosition().y-time-0.05f,0.05f,0.05f);
        game.batch.end();
//        if (time==1){time=0;}
    }}
    public void dispose(){
        texture.dispose();
    }
}
