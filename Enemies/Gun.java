package com.mario.GrinningGameMoroi.Enemies;

import com.mario.GrinningGameMoroi.MyGdxGame;
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

public class Gun extends EnemiesCreater {
    @Override
    public boolean stop() {return true;}

    private Array<Gunnbullets> getBulletsArray() {
        return bulletsArray;
    }
    private Array<Gunnbullets> bulletsArray;
    private Array<Gunnbullets>remove;
    private MyGdxGame game;
    public Gun(MyGdxGame game, com.mario.GrinningGameMoroi.screen.Screens screen, float x, float y) {
        super(game, screen, x, y);
        this.game=game;
        bulletsArray=new Array<Gunnbullets>();


    }

    @Override
    public void update(Player player, float dt) {

        remove=new Array<Gunnbullets>();
        if (time>100){
            bulletsArray.add(new Gunnbullets(screen,getX(),getY()));
            time=0;
        }
        for (Gunnbullets bullet:getBulletsArray()){
            game.batch.begin();
            bullet.draw(game.batch);
            game.batch.end();
            if (bullet.getX()<player.body.getPosition().x-5||bullet.getX()>player.body.getPosition().x+8){
            remove.add(bullet);}
           }

        for (Gunnbullets bullets:bulletsArray){bullets.update();
            if (bullets.getX()<player.body.getPosition().x-5||bullets.getX()>player.body.getPosition().x+8){
            bulletsArray.removeAll(remove,true);bullets.body.setActive(false);
        }}

        if (body.getPosition().x>player.body.getPosition().x+8||body.getPosition().x<player.body.getPosition().x-8){return;}
        else {
            time++;
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
        bulletsArray.removeAll(remove,true);
    }
   public void dispose(){
       bulletsArray.clear(); text.dispose();
   }
    @Override
    public Vector2 vector2() {return new Vector2();}
}
