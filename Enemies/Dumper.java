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
 * Created by waleed bas on 02/09/18.
 */

public class Dumper extends EnemiesCreater {
    private Player player;
    public Dumper(Player player, MyGdxGame game, com.mario.GrinningGameMoroi.screen.Screens screen, float x, float y) {
        super(game, screen, x, y);
        text= game.manager().get("enemys.png");
        setSize(0.2f,0.3f);
        this.player=player;
        bdef = new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shap = new CircleShape();
        shap.setRadius(7 / 100f);
        fixtureDef.shape = shap;
        fixtureDef.filter.maskBits = (short) (4|32);
        fixtureDef.filter.categoryBits=2|128|256;
        body.createFixture(fixtureDef).setUserData(this);



    }

    @Override
    public void update(Player player, float dt) {
        time++;
        if (time>100){
        findplayer();time=0;}
        setRegion(walk().getKeyFrame(time / 20, true));
        setPosition(body.getPosition().x - getWidth() / 2, (body.getPosition().y - getHeight() / 2)+0.03f);

    }

    @Override
    protected void defineEnemy() {

    }

    @Override
    public void onkill(Fixture fa, Fixture fb) {

    }

    @Override
    public void onhit(Fixture fa, Fixture fb) {

    }
    private Animation<TextureRegion> walk(){



        Array<TextureRegion> fram = new Array<TextureRegion>();
        for (int i = 0; i < 5; i++) {
            fram.add(new TextureRegion(text, (i*30),161,28,38));
            walk = new Animation<TextureRegion>(0.1f, fram);
        }

        fram.clear();

        return walk;
    }

    public void findplayer(){

        if (player.body.getPosition().x>getX()-2&&player.body.getPosition().x<getX()+2){
            body.setLinearVelocity(new Vector2(0.3f,body.getLinearVelocity().y));}
        if (player.body.getPosition().x>getX()+2&&player.body.getPosition().x<getX()-2){
            body.setLinearVelocity(new Vector2(-0.3f,body.getLinearVelocity().y));}
     else    if (player.body.getPosition().x>getX()){
            body.setLinearVelocity(new Vector2(1f,body.getLinearVelocity().y));}
       else if (player.body.getPosition().x<getX()){
            body.setLinearVelocity(new Vector2(-1f,body.getLinearVelocity().y));}
        else if (player.body.getPosition().x==getX()){
            body.applyLinearImpulse(new Vector2(0,body.getLinearVelocity().y),body.getWorldCenter(),true);}

    }
    public void onhitground(){
        body.setLinearVelocity(body.getLinearVelocity().x,5);
    }
    @Override
    public boolean stop() {return true;}
    @Override
    public Vector2 vector2() {return new Vector2();}
}
