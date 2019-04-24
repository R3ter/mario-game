package com.mario.GrinningGameMoroi.Score;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mario.GrinningGameMoroi.MyGdxGame;

/**
 * Created by waleed bas on 01/23/18.
 */

public abstract class Tools extends Sprite {

    protected World world;
    protected com.mario.GrinningGameMoroi.screen.Screens screen;
    public Body body;
    protected BodyDef bdef;
    public MyGdxGame game;


    public Tools(MyGdxGame game, com.mario.GrinningGameMoroi.screen.Screens screen, float x, float y){
        this.screen=new com.mario.GrinningGameMoroi.screen.Screens(game);
        this.world=screen.getWorld();
        this.game=game;
        setPosition(x,y);
        defincoin();

    }
    public abstract void drawcoins();
    public abstract void defincoin();
    public abstract void defi(float x,float y);
}

