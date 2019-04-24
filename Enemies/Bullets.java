package com.mario.GrinningGameMoroi.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * Created by waleed bas on 01/28/18.
 */

public class Bullets extends Sprite {
    BodyDef bdef;
    Body body;
    Texture texture;
    private TextureRegion textureRegion;
    public static boolean removed;

    public Bullets(com.mario.GrinningGameMoroi.screen.Screens screens, float x, float y) {
        if (texture==null){
        texture = new Texture("all.png");
            textureRegion=new TextureRegion(texture,81,31,22,22);}
        setRegion(textureRegion);
        setSize(0.15f, 0.15f);


        bdef = new

                BodyDef();
        bdef.position.set(x, y);
        bdef.type = BodyDef.BodyType.DynamicBody;
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

    }
    public void update(float delta){
        setPosition(body.getPosition().x- getWidth() / 2, body.getPosition().y - getHeight() / 2);
        body.setLinearVelocity(0,-2);
    }
public void destroy(Fixture fa,Fixture fb){
    Filter filter=new Filter();
    filter.categoryBits=16;
    fa.setFilterData(filter);
    removed=true;

}
public void dispose(){
    texture.dispose();

}}
