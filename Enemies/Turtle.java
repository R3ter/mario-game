package com.mario.GrinningGameMoroi.Enemies;

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
import com.mario.GrinningGameMoroi.MyGdxGame;

/**
 * Created by waleed bas on 01/25/18.
 */

public class Turtle extends EnemiesCreater {
   private com.mario.GrinningGameMoroi.Player.Player player;
   private boolean iskilled;
   private static boolean stop;
    private int timer;
   private Preferences pre;
   private Sound sound;
    public Turtle(MyGdxGame game, com.mario.GrinningGameMoroi.screen.Screens screen, float x, float y) {
        super(game,screen, x, y);
        sound=game.manager().get("sounds/smb_kick.wav",Sound.class);
        pre= Gdx.app.getPreferences("My Preferences");
        stop=false;
        text= game.manager().get("gomba.png");
            textureRegion=new TextureRegion(text,203,553,382 ,357);
            iskilled=false;

        setSize(.25f,.25f);
    }

    @Override
    public void update(com.mario.GrinningGameMoroi.Player.Player player, float dt) {
        timer=timer+1;
        this.player=player;
            time=time+1;

        if (!iskilled) {
            body.setLinearVelocity(vector2());
            setRegion(walk().getKeyFrame(time / 10, true));
            setPosition(body.getPosition().x - getWidth() / 2, (body.getPosition().y - getHeight() / 2)+.02f);
        }

        else if (iskilled){

            if (stop){
                body.setLinearVelocity(vector2());}
            if (!stop){body.setLinearVelocity(new Vector2(0,0));body.setActive(true);}

            setRegion(textureRegion);
            setSize(0.25f,0.25f);
            setPosition(body.getPosition().x - getWidth() / 2, (body.getPosition().y - getHeight() / 2));
        }
        if (body.getLinearVelocity().x<0){
            flip(true,false);
        }
        else if (body.getLinearVelocity().x<0){
            flip(false,true);
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
        shap.setRadius(7 / 100f);
        fixtureDef.shape = shap;
        fixtureDef.filter.maskBits = (short) (6|16);
        fixtureDef.filter.categoryBits=8|2|16;
        body.createFixture(fixtureDef).setUserData(this);




        PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-6, 15).scl(1 / 100f);
        vertice[1] = new Vector2(6, 15).scl(1 / 100f);
        vertice[2] = new Vector2(-3, 3).scl(1 / 100f);
        vertice[3] = new Vector2(3, 3).scl(1 / 100f);
        head.set(vertice);

        fixtureDef.shape = head;
//        fixtureDef.filter.maskBits=2;
//        fixtureDef.filter.categoryBits=2;
//        fixtureDef.isSensor=true;
        fixtureDef.restitution = 1f;
        die=body.createFixture(fixtureDef);
        die.setUserData(this);
    }

    @Override
    public void onkill(Fixture fa, Fixture fb) {
//        fb.getBody().setLinearVelocity(fb.getBody().getLinearVelocity().x,3);
        Filter filter=new Filter();



        if (pre.getInteger("sound")==0){
        sound.play();}
        filter.categoryBits=8|2|64|32;
        filter.maskBits=2|4;
        iskilled=true;
        fa.getBody().getFixtureList().first().setFilterData(filter);
        fa.setFilterData(filter);
        pre.putInteger("Score",pre.getInteger("Score")+13);
        pre.flush();


    }

    @Override
    public void onhit(Fixture fa, Fixture fb) {
            if (timer>5) {
                if (vector) {
                    vector = false;
                } else if (!vector) {
                    vector = true;
                }
            timer=0;}


    }

    private Animation<TextureRegion> walk(){



        Array<TextureRegion> fram = new Array<TextureRegion>();
        for (int i = 0; i < 2; i++) {
            fram.add(new TextureRegion(text, i*365,0,366,536));
            walk = new Animation<TextureRegion>(0.4f, fram);
        }

        fram.clear();

        return walk;
    }
    public Vector2 vector2() {
        body.setActive(true);
        if (body.getPosition().x > player.body.getPosition().x + 5 || body.getPosition().x < player.body.getPosition().x - 5) {
            body.setActive(false);}

        if (iskilled&&vector){
            return new Vector2(3f, -1f);
        }
        if (iskilled&&!vector){
            return new Vector2(-3f, -1f);
        }
        if (vector) {
            return new Vector2(0.5f, -1f);
        } else if (!vector) {

            return new Vector2(-0.5f, -1);
        } else return new Vector2(-0.5f, -1);


    }
    @Override
    public boolean stop(){
        if (pre.getInteger("sound")==0){
            sound.play();}
        if (stop){stop=false;}
        else if (!stop){stop=true;}
        return true;

    }
    public void dispose(){
        text.dispose();
    }
}
