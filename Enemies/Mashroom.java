package com.mario.GrinningGameMoroi.Enemies;

import com.mario.GrinningGameMoroi.MyGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;

import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;


import com.mario.GrinningGameMoroi.Player.Player;

/**
 * Created by waleed bas on 01/14/18.
 */

public class Mashroom extends EnemiesCreater {

private Player player;
private Preferences pre;
    private static Sound sound;
    public Mashroom(MyGdxGame game, com.mario.GrinningGameMoroi.screen.Screens screen, float x, float y) {
        super(game,screen, x, y);
        time=0;

        vector=true;
        sound=game.manager().get("sounds/smb_stomp.wav", Sound.class);
        pre= Gdx.app.getPreferences("My Preferences");

        setSize(.27f,.27f);
        kol=true;
        text= game.manager().get("gom.png");
        textureRegion=new TextureRegion(text,71,0,364,157);


    }

    @Override
    protected void defineEnemy() {

        bdef = new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shap = new CircleShape();
        shap.setRadius(7 / 100f);
        fixtureDef.shape = shap;
        fixtureDef.filter.maskBits = (short) (4|32);
        fixtureDef.filter.categoryBits=2;
        body.createFixture(fixtureDef).setUserData(this);


        PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-7, 15).scl(1 / 100f);
        vertice[1] = new Vector2(7, 15).scl(1 / 100f);
        vertice[2] = new Vector2(-3, 3).scl(1 / 100f);
        vertice[3] = new Vector2(3, 3).scl(1 / 100f);
        head.set(vertice);

        fixtureDef.shape = head;
//        fixtureDef.filter.maskBits=8;
//        fixtureDef.filter.categoryBits=8;
        fixtureDef.isSensor=true;
        fixtureDef.restitution=1f;
        die=body.createFixture(fixtureDef);
        die.setUserData(this);



//        EdgeShape head = new EdgeShape();
//        head.set(new Vector2(-7f / 100f, 11f / 100f), new Vector2(7f / 100f, 11f / 100f));
//        fixtureDef.shape = head;





    }



    @Override
    public void update(Player player, float dt) {

    this.player=player;
        time=time+1;

        if (dead>40){setRegion(new TextureRegion(text, (16*16)+3,12,0,0));return;}

         if (!kol){
             setRegion(new TextureRegion(text, 71,0,364,157));
             setSize(.4f,.15f);
            setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
             dead=dead+1;
          }

        else if (kol) {
            body.setLinearVelocity(vector2());
            setRegion(walk().getKeyFrame(time / 10, true));
            setPosition(body.getPosition().x - getWidth() / 2, (body.getPosition().y - getHeight() / 2)+0.03f);

    }
    if (body.getLinearVelocity().x<0){
        flip(true,false);
    }
    else if (body.getLinearVelocity().x<0){
        flip(false,true);
    }
    }


    private Animation<TextureRegion> walk(){



            Array<TextureRegion> fram = new Array<TextureRegion>();
            for (int i = 0; i < 3; i++) {
                fram.add(new TextureRegion(text, 285*i,216,250,277));
                walk = new Animation<TextureRegion>(0.4f, fram);
        }

        fram.clear();

        return walk;
   }


    @Override
    public void onkill(Fixture fa, Fixture fb) {
        fb.getBody().setLinearVelocity(fb.getBody().getLinearVelocity().x,3);
        Filter filter=new Filter();
        if (pre.getInteger("sound")==0){
        sound.play();}
        filter.categoryBits=16;
        settrue(false);
        fa.getBody().getFixtureList().first().setFilterData(filter);
        fa.setFilterData(filter);

        pre.putInteger("Score",pre.getInteger("Score")+10).flush();


    }

    @Override
    public void onhit(Fixture fa, Fixture fb) {

        if (vector){
        vector=false;}
        else vector=true;
    }

private void settrue(boolean c){
    this.kol=c;

}

    @Override
    public Vector2 vector2() {
body.setActive(true);
    if (body.getPosition().x>player.body.getPosition().x+5||body.getPosition().x<player.body.getPosition().x-5){
        body.setActive(false);}

     if (vector){
        return new Vector2(-0.5f,-1f);

    }
    else if (!vector){
        return new Vector2(0.5f,-1);
    }
    else return new Vector2(0.5f,-1);

}
    @Override
    public boolean stop() {return true;}
    public void dispose(){

    }}

















