package com.mario.GrinningGameMoroi.Score;

import com.mario.GrinningGameMoroi.MyGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * Created by waleed bas on 02/07/18.
 */

public class Hearts extends Sprite {
    private boolean vector;private float f;
    private Body body;
    private Texture texture;
    private TextureRegion textureRegion;
    private boolean take;
    private MyGdxGame game;
    private Preferences pre;
    private com.mario.GrinningGameMoroi.Player.Player player;
    private int i;
    public Hearts(com.mario.GrinningGameMoroi.Player.Player player, com.mario.GrinningGameMoroi.screen.Screens screens, float x, float y, MyGdxGame game) {
        pre= Gdx.app.getPreferences("My Preferences");
        this.game=game;

        soun(game);


        this.player=player;
        take=false;
        if (texture==null){
            texture = new Texture("all.png");
            textureRegion=new TextureRegion(texture,102,290,22,23);}



       BodyDef bdef = new BodyDef();
        bdef.position.set(x, y+0.1f);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = screens.getWorld().createBody(bdef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shap = new CircleShape();
        shap.setRadius(6 / 100f);
        fixtureDef.shape = shap;
        fixtureDef.filter.maskBits = (short) (128|4);
        fixtureDef.filter.categoryBits = (2 |128|64|256);
        body.createFixture(fixtureDef).setUserData(this);

    }
    public void update(){
        setRegion(textureRegion);
        setSize(0.20f, 0.20f);
       setPosition(body.getPosition().x- getWidth() / 2, body.getPosition().y - getHeight() / 2);
        body.setLinearVelocity(vector2());
    }
    public void take(final Fixture fa){
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                Filter filter = new Filter();
                filter.categoryBits = 16;
                fa.setFilterData(filter);
                body.setActive(false);
                player.life=player.life+1;
                if (pre.getInteger("sound")==0){
                    game.manager().get("sounds/smb_powerup.wav",Sound.class).play();}
                take=true;
            }});
    }
    private Vector2 vector2(){
        if (body.getLinearVelocity().y==0){
            f=1f;
        }
        else if (body.getLinearVelocity().y!=0){f=0;}
        if (vector){
            return new Vector2(-f,-1);

        }
        else if (!vector){
            return new Vector2(f,-1);
        }
        else return new Vector2(f,-1);

    }
    public void onhit(Fixture fa, Fixture fb) {

        if (vector){
            vector=false;}
        else vector=true;
    }
public void drawhearts(){

    if (!take){
    game.batch.begin();
    draw(game.batch);
    game.batch.end();
}}

    public void soun(MyGdxGame game){
        if (pre.getInteger("sound")==0){
        game.manager().get("sounds/smb_powerup_appears.wav", Sound.class).play();}}

public void dispose(){
    texture.dispose();
}
}
