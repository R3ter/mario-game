package com.mario.GrinningGameMoroi.Move;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mario.GrinningGameMoroi.MyGdxGame;
import com.mario.GrinningGameMoroi.Player.Player;
import com.mario.GrinningGameMoroi.screen.Screens;

/**
 * Created by waleed bas on 05/09/18.
 */

public abstract class MoversCreator extends Sprite {
        Screens screen;
        MyGdxGame game;
        World world;
     BodyDef grpund;
     Body Groundbody;
     Texture texture;
     float width,hight;
     float r,t;
    public MoversCreator(MyGdxGame game, Screens screens, float x, float y, float width, float hight,float r,float t) {
        this.screen=new Screens(game);
        this.world=screen.getWorld();
        this.game=game;

        setPosition(x, y);
    }
    public abstract void update(Player player);
    public abstract void defineEnemy();
}
