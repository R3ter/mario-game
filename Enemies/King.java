package com.mario.GrinningGameMoroi.Enemies;

import com.badlogic.gdx.utils.reflect.Method;
import com.mario.GrinningGameMoroi.MyGdxGame;
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
import com.mario.GrinningGameMoroi.screen.Screens;

import javax.swing.plaf.nimbus.State;


/**
 * Created by waleed bas on 02/23/18.
 */

public class King extends EnemiesCreater {
    MyGdxGame game;
    private int timer;
    private Animation<TextureRegion> attack;
    private float f;
    private boolean killed, hited, flip;
    private int life;
    private Animation<TextureRegion> sheld;
        private int fire;
    private FireUp fireUp;
    private enum State {turule, longfire, fireallover,walk};
    private Player player;

    private int nextmove;
    private TextureRegion textureRegion;

    public Array<FireUp> getShot() {
        return shot;
    }
    public Array<FireUp> getShot2() {
        return shot2;
    }

    private Array<FireUp> shot,shot2;

    public King(MyGdxGame game,Player player, Screens screen, float x, float y) {
        super(game,screen, x, y);
        this.player=player;
        this.game = game;
        text = new Texture("buttons/bowser.png");
        setSize(0.7f, 0.7f);
        fireUp = new FireUp(game, screen, -1, -1);
        life = 10;
        killed = false;
        shot = new Array<FireUp>();
        shot2 = new Array<FireUp>();
        textureRegion = new TextureRegion(text, 181, 369, 66, 66);
    }

    @Override
    public void update(Player player, float dt) {
        setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2);
        setSize(1,1);
        timer++;
        move();
        game.batch.begin();
        this.draw(game.batch);
        game.batch.end();




        if (player.getX()>body.getPosition().x){
        for (FireUp bullets:getShot()){bullets.update(1,0);}}

        else if (player.getX()<body.getPosition().x){
            for (FireUp bullets:getShot2()){bullets.update(-1,0);}}

        for (FireUp bullet:getShot()){
            bullet.setPosition(bullet.body.getPosition().x- bullet.getWidth() / 2, bullet.body.getPosition().y - bullet.getHeight() / 2);
            game.batch.begin();
            bullet.draw(game.batch);
            game.batch.end();}
        for (FireUp bullet:getShot2()){
            bullet.setPosition(bullet.body.getPosition().x- bullet.getWidth() / 2, bullet.body.getPosition().y - bullet.getHeight() / 2);
           game.batch.begin();
            bullet.draw(game.batch);
            game.batch.end();}
    }


    private State move() {

        if (timer<500){
            setRegion(walk().getKeyFrame(timer/10,true));
            if (hited){body.setLinearVelocity(2f,0);}
            else {body.setLinearVelocity(-2f,0);}
            return State.walk;
        }
        else if (timer>500&&timer<1000){
            time=time+1;
            setRegion(attack().getKeyFrame(timer/10,true));
            if (time>100){
                if (player.getX()>body.getPosition().x){
               shot.add(new FireUp(game,screen,body.getPosition().x-0.3f,body.getPosition().y-.1f));}
                else if (player.getX()<body.getPosition().x){shot2.add(new FireUp(game,screen,body.getPosition().x-0.3f,body.getPosition().y-.1f));}
                time=0;
            }body.setLinearVelocity(0,0);if (time>=80||time<15){setRegion(512 ,94,88,82);}
            return State.fireallover;
        }
        else if (timer>1000&&timer<1500){time=time+1;setSize(.65f,.65f);
            if (time>150){
                if (body.getPosition().x<0.27f){flip=false;}
            else if (body.getPosition().x>5.73f){flip=true;}if (flip){body.setLinearVelocity(-5f,-1);}
                if (!flip){body.setLinearVelocity(5f,-1);}}
            setRegion(sheld().getKeyFrame(timer/10,true));}
        else if (timer>1500){body.setLinearVelocity(0,-1);}
        else  setRegion(walk().getKeyFrame(timer/10,true)); return State.walk;
    }


    @Override
    protected void defineEnemy() {
        bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shap = new CircleShape();
        shap.setRadius(25 / 100f);
        fixtureDef.shape = shap;
        fixtureDef.filter.maskBits = (short) (4 | 32 | 512);
        fixtureDef.filter.categoryBits = 2 | 8;
        body.createFixture(fixtureDef).setUserData(this);


        PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-12, 30).scl(1 / 100f);
        vertice[1] = new Vector2(12, 30).scl(1 / 100f);
        vertice[2] = new Vector2(-10, 20).scl(1 / 100f);
        vertice[3] = new Vector2(10, 20).scl(1 / 100f);
        head.set(vertice);

        fixtureDef.shape = head;
        fixtureDef.filter.maskBits = 8;
        fixtureDef.filter.categoryBits = 2 | 4;
        die = body.createFixture(fixtureDef);
        die.setUserData(this);

    }

    @Override
    public void onkill(Fixture fa, Fixture fb) {

    }

    @Override
    public void onhit(Fixture fa, Fixture fb) {
        if (hited){
        hited=false;}
        else {hited=true;}
    }

    @Override
    public boolean stop() {
        return true;
    }

    private Animation<TextureRegion> walk() {
        Array<TextureRegion> fram = new Array<TextureRegion>();
        for (int i = 0; i < 3; i++) {
            fram.add(new TextureRegion(text, 87 * i, 95, 87, 85));
            walk = new Animation<TextureRegion>(0.4f, fram);
        }

        fram.clear();

        return walk;
    }

    private Animation<TextureRegion> attack() {
        Array<TextureRegion> fram = new Array<TextureRegion>();
        for (int i = 1; i < 2; i++) {
            fram.add(new TextureRegion(text, 255 + (85 * i), 95, 85, 76));
            attack = new Animation<TextureRegion>(0.1f, fram);
        }

        fram.clear();

        return attack;
    }

    private Animation<TextureRegion> sheld() {
        Array<TextureRegion> fram = new Array<TextureRegion>();
        for (int i = 0; i < 3; i++) {
            fram.add(new TextureRegion(text, 177, 365, 70, 70));
            sheld = new Animation<TextureRegion>(0.4f, fram);
        }

        fram.clear();

        return sheld;
    }

    @Override
    public Vector2 vector2() {
        return new Vector2();
    }

}