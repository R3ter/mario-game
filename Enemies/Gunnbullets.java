package com.mario.GrinningGameMoroi.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by waleed bas on 02/09/18.
 */

public class Gunnbullets extends Sprite {
    private Texture texture;
    private TextureRegion textureRegion;
    private BodyDef bdef;
    public Body body;
    private boolean kill;
    public Gunnbullets(com.mario.GrinningGameMoroi.screen.Screens screens, float x, float y) {
        if (texture==null){
            kill=false;
            texture = new Texture("all.png");
            textureRegion=new TextureRegion(texture,36,22,36,39);}

        setRegion(textureRegion);
        setSize(0.15f, 0.15f);
        flip(true,false);

        bdef = new

                BodyDef();
        bdef.position.set(x, y);
        bdef.type = BodyDef.BodyType.KinematicBody;
        body = screens.getWorld().createBody(bdef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shap = new CircleShape();
        shap.setRadius(3 / 100f);
        fixtureDef.shape = shap;
        fixtureDef.filter.maskBits = (short) (2|8);
        fixtureDef.filter.categoryBits = (2 |128);
        fixtureDef.isSensor=true;
        body.setBullet(true);
        body.createFixture(fixtureDef).setUserData(this);

//        EdgeShape foot = new EdgeShape();
//        foot.set(new Vector2(-3f / 100f, 6 / 100f), new Vector2(3f / 100f, 6f / 100f));
//        fixtureDef.shape = foot;
//        fixtureDef.isSensor=true;
//        fixtureDef.filter.categoryBits=2|4|64|96;
//        fixtureDef.filter.maskBits=4|8;
//        fixtureDef.restitution=3;
//        body.createFixture(fixtureDef).setUserData(this);


    }
    public void update(){
       setPosition(body.getPosition().x- getWidth() / 2, body.getPosition().y - getHeight() / 2);
        if (!kill){
        body.setLinearVelocity(-1,0);}
        else{body.setLinearVelocity(0,-1);}
    }
public void dispose(){texture.dispose();}
public void kill(Fixture fa){
    Filter filter=new Filter();
    filter.maskBits=2;
    filter.categoryBits=2;
    fa.setFilterData(filter);
    kill=true;
}}
