package com.mario.GrinningGameMoroi.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.mario.GrinningGameMoroi.MyGdxGame;
import com.mario.GrinningGameMoroi.Player.Player;
import com.mario.GrinningGameMoroi.screen.Screens;

/**
 * Created by waleed bas on 02/07/18.
 */

public class Bat extends EnemiesCreater {
    private float f;
    public Bat(MyGdxGame game, Screens screen, float x, float y) {
        super(game, screen, x, y);
        text= game.manager().get("all.png");
        body.setActive(false);
        setSize(.2f,.2f);
        f=1f;
    }


    @Override
    public void update(Player player, float dt) {
        body.setLinearVelocity(-0.5f,f);
        if (body.getPosition().y<0){
            f=1f;
        }
        else if (body.getPosition().y>2){
            f=-1f;
        }
        time++;
        setRegion(walk().getKeyFrame(time/6, true));
        if (!isFlipX()){
            flip(true,false);}
        if (body.getPosition().x<player.getX()+4){
            body.setActive(true);}
        setPosition(body.getPosition().x - getWidth() / 2, (body.getPosition().y - getHeight() / 2));
//        if (body.getPosition().x<-2){body.setActive(false);}
    }

    @Override
    protected void defineEnemy() {
        bdef = new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type = BodyDef.BodyType.KinematicBody;
        body = world.createBody(bdef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shap = new CircleShape();
        shap.setRadius(12 / 100f);
        fixtureDef.shape = shap;
        fixtureDef.filter.maskBits = (short) (4);
        fixtureDef.filter.categoryBits=2|128;
        fixtureDef.isSensor=true;
        body.createFixture(fixtureDef).setUserData("bat");
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
            fram.add(new TextureRegion(text, i*28,0,29,24));
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

