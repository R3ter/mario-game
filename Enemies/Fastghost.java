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

/**
 * Created by waleed bas on 01/30/18.
 */

public class Fastghost extends EnemiesCreater {
    public Fastghost(MyGdxGame game, com.mario.GrinningGameMoroi.screen.Screens screen, float x, float y) {
        super(game,screen, x, y);
        text= game.manager().get("all.png");
        body.setActive(false);
        setSize(.4f,.4f);

    }

    @Override
    public void update(Player player, float dt) {

        setRegion(walk().getKeyFrame(time / 20, true));
        if (!isFlipX()){
        flip(true,false);}
        if (body.getPosition().x<player.getX()+4){
            body.setActive(true);}
        setPosition(body.getPosition().x - getWidth() / 2, (body.getPosition().y - getHeight() / 2));
        body.setLinearVelocity(-2f,0);
        if (body.getPosition().x<-1){body.setActive(false);}
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
        body.createFixture(fixtureDef).setUserData("fastghoust");
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
            fram.add(new TextureRegion(text, 0,25,31,31));
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
