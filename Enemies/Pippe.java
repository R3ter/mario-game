package com.mario.GrinningGameMoroi.Enemies;

import com.mario.GrinningGameMoroi.MyGdxGame;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;

/**
 * Created by waleed bas on 02/17/18.
 */

public class Pippe extends EnemiesCreater {

    private Array<FireUp> getBulletsArray() {
        return bulletsArray;
    }
    private Array<FireUp> bulletsArray;
    private MyGdxGame game;

    public Pippe(MyGdxGame game, com.mario.GrinningGameMoroi.screen.Screens screen, float x, float y) {
        super(game, screen, x, y);
        this.game=game;
        bulletsArray=new Array<FireUp>();


    }

    @Override
    public void update(com.mario.GrinningGameMoroi.Player.Player player, float dt) {
        Array<FireUp>remove=new Array<FireUp>();
        if (time>100){
            bulletsArray.add(new FireUp(game,screen,getX(),getY()));
            time=0;
        }
        for (FireUp bullet:getBulletsArray()){
            game.batch.begin();
            bullet.draw(game.batch);
            game.batch.end();
            remove.add(bullet);
        if (bullet.getY()>2){
            bulletsArray.removeAll(remove,true);
        }}
        for (FireUp bullets:bulletsArray){bullets.update(0,1);}

        if (body.getPosition().x>player.body.getPosition().x+8||body.getPosition().x<player.body.getPosition().x-8){return;}
        else {time++;
           }}

    @Override
    protected void defineEnemy() {
        bdef = new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type = BodyDef.BodyType.KinematicBody;
        body = world.createBody(bdef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shap = new CircleShape();
        shap.setRadius(4 / 100f);
        fixtureDef.shape = shap;
        fixtureDef.filter.maskBits = (short) (4);
        fixtureDef.filter.categoryBits=4;
        fixtureDef.isSensor=true;
        body.createFixture(fixtureDef).setUserData("gun");
    }

    @Override
    public void onkill(Fixture fa, Fixture fb) {

    }

    @Override
    public void onhit(Fixture fa, Fixture fb) {

    }
    public void dispose(){
        bulletsArray.clear();text.dispose();
    }

    @Override
    public boolean stop() {return true;}
    @Override
    public Vector2 vector2() {return new Vector2();}
}