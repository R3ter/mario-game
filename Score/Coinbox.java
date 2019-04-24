package com.mario.GrinningGameMoroi.Score;

import com.mario.GrinningGameMoroi.MyGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import com.mario.GrinningGameMoroi.Player.Player;
import com.mario.GrinningGameMoroi.screen.Screens;

/**
 * Created by waleed bas on 01/26/18.
 */

public class Coinbox extends Sprite {
    private Contact contact;
    private Player player;
    private Screens screens;
    private Texture text;
    private Animation<TextureRegion>coin;
    private static Fixture fa,fb;
    private TiledMapTileLayer.Cell waleed;
    private MyGdxGame game;
    private TiledMapTileLayer cell;
    private static int time;
    private  TiledMapTileSet tileSet;
    private Preferences pre;

    public Coinbox(Screens screens, Player player){
        this.player=player;
        this.screens=screens;
        game=new MyGdxGame(null);
        text=new Texture("coin.png");


    }

        public void coincreater(Fixture fa, Fixture fb) {
            this.fa=fa;
            this.fb=fb;
        time=0;
            pre= Gdx.app.getPreferences("My Preferences");
            tileSet = screens.map.getTileSets().getTileSet("SNES - Super Mario World - General Tiles");

            TiledMapTileLayer cel = (TiledMapTileLayer) screens.map.getLayers().get(3);
            TiledMapTileLayer.Cell waled = cel.getCell((int) (fb.getBody().getPosition().x * 100 / 16),
                    (int) ((fb.getBody().getPosition().y * 100 / 16)));

            cell = (TiledMapTileLayer) screens.map.getLayers().get(4);
            waleed = cell.getCell((int) (fb.getBody().getPosition().x * 100 / 16),
                    (int) ((fb.getBody().getPosition().y * 100 / 16)));
            if (waleed != null) {
                waleed.setTile(null);


                pre.putInteger("Score",pre.getInteger("Score")+10);
                pre.flush();
            } else if (waled != null) {
                waled.setTile(null);
            }


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

    public void drawboxcoin(){
        animation();
        time=time+1;
        setPosition(fb.getBody().getPosition().x-0.05f,fb.getBody().getPosition().y+time*0.01f);
        setRegion(animation().getKeyFrame(time/3,true));
        setSize(1,1);
        setScale(0.1f,0.1f);
        if (time<50){
        game.batch.begin();
        draw(game.batch);
        game.batch.end();}
    }

    public void dispose(){

text.dispose();///////////////////////////////////////////////////////////////////////////////////////////
    }
    }



