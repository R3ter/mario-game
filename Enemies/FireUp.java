package com.mario.GrinningGameMoroi.Enemies;

import com.mario.GrinningGameMoroi.MyGdxGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;

/**
 * Created by waleed bas on 02/17/18.
 */

public class FireUp extends Sprite {
    private Texture texture;
    float x, y;
    int time;
    public Body body;
    private Animation<TextureRegion> walk;
    private com.mario.GrinningGameMoroi.screen.Screens screens;

    public FireUp(MyGdxGame game, com.mario.GrinningGameMoroi.screen.Screens screen, float x, float y) {
        if (texture==null){
            this.screens=screen;
            texture = game.manager().get("all.png");
            }

        setSize(0.15f, 0.15f);
        flip(true,false);

        BodyDef bdef = new

                BodyDef();
        bdef.position.set(x, y);
        bdef.type = BodyDef.BodyType.KinematicBody;
        body = screens.getWorld().createBody(bdef);
        setRegion(walk().getKeyFrame(time/300,true));
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shap = new CircleShape();
        shap.setRadius(3 / 100f);
        fixtureDef.shape = shap;
        fixtureDef.filter.maskBits = (short) (2|8);
        fixtureDef.filter.categoryBits = (2 |128);
        fixtureDef.isSensor=true;
        body.setBullet(true);
        body.createFixture(fixtureDef).setUserData(this);
    }
    public void update(float x,float y){
        time++;
        setRegion(walk().getKeyFrame(time/10,true));
        setPosition(body.getPosition().x- getWidth() / 2, body.getPosition().y - getHeight() / 2);
        body.setLinearVelocity(x,y);
    }
    private Animation<TextureRegion> walk(){



        Array<TextureRegion> fram = new Array<TextureRegion>();
        for (int i = 0; i < 4; i++) {
            fram.add(new TextureRegion(texture, 12+(8*i),264,9,9));
            walk = new Animation<TextureRegion>(0.4f, fram);
        }

        fram.clear();

        return walk;
    }
    public void dispose(){
        texture.dispose();
    }
}