package com.mario.GrinningGameMoroi.Enemies;

import com.mario.GrinningGameMoroi.MyGdxGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.mario.GrinningGameMoroi.Player.Player;
import com.mario.GrinningGameMoroi.screen.Screens;

/**
 * Created by waleed bas on 01/28/18.
 */

public class Ghost extends EnemiesCreater {


    public Ghost(MyGdxGame game, Screens screen, float x, float y) {
        super(game,screen, x, y);
        setSize(.2f,.2f);
        text= game.manager().get("all.png");


    }

    @Override
    public void update(Player player, float dt) {
        walk();
        time=time+1;
        if (player.body.getPosition().y>getY()+1&&player.body.getPosition().y<getY()-1)
        {body.setLinearVelocity(new Vector2(1.3f,0f));}
        if (player.body.getPosition().x>getX()&&player.body.getPosition().y>getY()){
            body.setLinearVelocity(new Vector2(1.3f,1.3f));}
        else if (player.body.getPosition().x<getX()&&player.body.getPosition().y>getY()){
            body.setLinearVelocity(new Vector2(-1.3f,1.3f));}
       else if (player.body.getPosition().x>getX()&&player.body.getPosition().y<getY()){
            body.setLinearVelocity(new Vector2(1.3f,-1.3f));}
        else if (player.body.getPosition().x<getX()&&player.body.getPosition().y<getY()){
            body.setLinearVelocity(new Vector2(-1.3f,-1.3f));}
       else if (player.body.getPosition().y>getY()+0.5){
            body.setLinearVelocity(new Vector2(0,1.3f));}
       else if (player.body.getPosition().y<getY()-0.3){
            body.setLinearVelocity(new Vector2(0,-1.3f));}
       else if (player.body.getPosition().x>getX()+0.5){
            body.setLinearVelocity(new Vector2(1.3f,0));}
       else if (player.body.getPosition().x<getX()-0.3){
            body.setLinearVelocity(new Vector2(-1.3f,0));}
        setRegion(walk().getKeyFrame(time / 20, true));
        if (!isFlipX()&&player.body.getPosition().x<getX()){flip(true,false);}
        if (isFlipX()&&player.body.getPosition().x>getX()){flip(false,false);}
        setPosition(body.getPosition().x - getWidth() / 2, (body.getPosition().y - getHeight() / 2)+0.03f);
    }


    @Override
    protected void defineEnemy() {

        bdef = new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type = BodyDef.BodyType.KinematicBody;
        body = world.createBody(bdef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shap = new CircleShape();
        shap.setRadius(7 / 100f);
        fixtureDef.shape = shap;
        fixtureDef.filter.maskBits = (short) (4);
        fixtureDef.filter.categoryBits=2|128;
        fixtureDef.isSensor=true;
        body.createFixture(fixtureDef).setUserData("ghost");
    }

    @Override
    public void onkill(Fixture fa, Fixture fb) {

    }

    @Override
    public void onhit(Fixture fa, Fixture fb) {

    }
    private Animation<TextureRegion> walk(){



        Array<TextureRegion> fram = new Array<TextureRegion>();
        for (int i = 0; i < 3; i++) {
            fram.add(new TextureRegion(text, 5,(i*37)+95,36,37));
            walk = new Animation<TextureRegion>(0.1f, fram);
        }

        fram.clear();

        return walk;
    }
    public void dispose(){
        text.dispose();

    }
    @Override
    public boolean stop() {return true;}
    @Override
    public Vector2 vector2() {return new Vector2();}
}























