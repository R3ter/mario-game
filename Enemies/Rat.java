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

/**
 * Created by waleed bas on 02/09/18.
 */

public class Rat extends EnemiesCreater {
    private boolean is;
    public Rat(MyGdxGame game, com.mario.GrinningGameMoroi.screen.Screens screen, float x, float y) {
        super(game, screen, x, y);
        text=game.manager().get("all.png");
        setSize(0.15f,0.15f);
        is=false;
    }

    @Override
    public boolean stop() {return true;}

    @Override
    public void update(Player player, float dt) {

        time++;
        if (is){
            body.setActive(false);if (time>100){is=false;}
        }

        setPosition(body.getPosition().x - getWidth() / 2, (body.getPosition().y - getHeight() / 2));
        setRegion(walk().getKeyFrame(time/10,true));

         if ((body.getPosition().x>player.body.getPosition().x+1.4||body.getPosition().x<player.body.getPosition().x-1.4))
         {body.setActive(false);}
        else {body.setActive(true);}
        if (player.body.getPosition().x>getX()&&body.getLinearVelocity().x<1.7f&&player.getY()<1.4f-body.getPosition().y){
            body.applyLinearImpulse(new Vector2(0.1f,0),body.getWorldCenter(),true);}
        if (player.body.getPosition().x<getX()&&body.getLinearVelocity().x>-1.7f&&player.getY()<1.4f-body.getPosition().y){
            body.applyLinearImpulse(new Vector2(-0.1f,0),body.getWorldCenter(),true);}
        if (body.getLinearVelocity().x>0&&isFlipX()){
            flip(false,false);
        }
        if (body.getLinearVelocity().x<0&&!isFlipX()){
            flip(true,false);
        }
    }

    @Override
    protected void defineEnemy() {
        bdef = new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shap = new CircleShape();
        shap.setRadius(6 / 100f);
        fixtureDef.shape = shap;
        fixtureDef.filter.maskBits = (short) (4|32|2);
        fixtureDef.filter.categoryBits=2;
        body.createFixture(fixtureDef).setUserData(this);
    }

    @Override
    public void onkill(Fixture fa, Fixture fb) {

    }

    @Override
    public void onhit(Fixture fa, Fixture fb) {
//        body.setActive(false);
        is=true;
        time=0;
    }
    private Animation<TextureRegion> walk(){



        Array<TextureRegion> fram = new Array<TextureRegion>();
        if (body.isActive()){
        for (int i = 0; i < 4; i++) {
            fram.add(new TextureRegion(text,(i*30),59,30,25));
            walk = new Animation<TextureRegion>(0.4f, fram);
        }

        fram.clear();}
        else if (!body.isActive()){
            for (int i = 0; i < 4; i++) {
                fram.add(new TextureRegion(text,(30),59,30,25));
                walk = new Animation<TextureRegion>(0.4f, fram);
            } fram.clear();}

        return walk;
    }
    public void dispose(){text.dispose();}
    @Override
    public Vector2 vector2() {return new Vector2();}
}
