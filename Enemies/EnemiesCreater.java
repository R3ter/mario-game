package com.mario.GrinningGameMoroi.Enemies;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.mario.GrinningGameMoroi.MyGdxGame;
import com.mario.GrinningGameMoroi.Player.Player;
import com.mario.GrinningGameMoroi.screen.Screens;

/**
 * Created by waleed bas on 01/15/18.
 */

public abstract class EnemiesCreater extends Sprite {
    protected World world;
    public Texture text;
    TextureRegion textureRegion;
    Animation<TextureRegion> walk;
    Fixture die;
    int time,dead;
    boolean vector;
    protected Screens screen;
    public Body body;
    protected BodyDef bdef;
    public  boolean kol;
    private MyGdxGame game;


    public EnemiesCreater(MyGdxGame game, Screens screen, float x, float y) {
        this.screen=new Screens(game);
        this.world=screen.getWorld();
        this.game=game;

        setPosition(x, y);
        defineEnemy();





    }
    public abstract void update(Player player, float dt);
    protected abstract void defineEnemy();
    public abstract void onkill(Fixture fa,Fixture fb);
    public abstract void onhit(Fixture fa,Fixture fb);
    public abstract boolean stop();
    public abstract Vector2 vector2();


}


