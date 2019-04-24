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
 * Created by waleed bas on 01/28/18.
 */

public class Cloud extends EnemiesCreater {
    private Player player;
    private int f,o;
    private Bullets bulles;
    private MyGdxGame game;


    private Array<Bullets> getBulletsArray() {
        return bulletsArray;
    }

   private Array<Bullets> bulletsArray;
    public Cloud(MyGdxGame game, com.mario.GrinningGameMoroi.screen.Screens screen, float x, float y) {
        super(game,screen, x, y);
        f=0;
        bulles=new Bullets(screen,x,y);
        text= game.manager().get("all.png");
        walk();
        bulletsArray=new Array<Bullets>();

    }

    @Override
    public void update(Player player, float dt) {
        f=f+1;
        o=o+1;

       if (f>100){bulletsArray.add(new Bullets(screen,body.getPosition().x,body.getPosition().y));
           bulles.removed=false;
       f=0;}
        Array<Bullets>remove=new Array<Bullets>();
for (Bullets bullet:bulletsArray){bullet.update(dt);}
        this.player=player;
        time=time+1;
        findplayer();
        setSize(.2f,.2f);
            setRegion(walk().getKeyFrame(time / 20, true));
        if (!isFlipX()&&player.body.getPosition().x<getX()){flip(true,false);}
        if (isFlipX()&&player.body.getPosition().x>getX()){flip(false,false);}
            setPosition(body.getPosition().x - getWidth() / 2, (body.getPosition().y - getHeight() / 2)+0.03f);

        for (Bullets bullets:getBulletsArray()){
            game.batch.begin();
        bullets.draw(game.batch);
            game.batch.end();
        remove.add(bullets);}
        if (bulles.removed||bulles.getY()<0){
            bulletsArray.removeAll(remove,true);}}



    @Override
    protected void defineEnemy() {
        bdef = new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type = BodyDef.BodyType.KinematicBody;
        body = world.createBody(bdef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shap = new CircleShape();
        shap.setRadius(7 / 100f);
        fixtureDef.shape = shap;
        fixtureDef.filter.maskBits = (short) (4);
        fixtureDef.filter.categoryBits=2|128;
        body.createFixture(fixtureDef).setUserData("ghost");
    }

    @Override
    public void onkill(Fixture fa, Fixture fb) {

    }

    @Override
    public void onhit(Fixture fa, Fixture fb) {

    }

    @Override
    public boolean stop() {
        return true;
    }

    private void findplayer(){


        if (player.body.getPosition().x>getX()+0.5){
            body.setLinearVelocity(new Vector2(1.3f,0));}
      if (player.body.getPosition().x<getX()-0.3){
        body.setLinearVelocity(new Vector2(-1.3f,0));}
     if (player.body.getPosition().x==getX()){bdef.position.set(player.getX(),getY());}
    }

    private Animation<TextureRegion> walk(){



        Array<TextureRegion> fram = new Array<TextureRegion>();
        for (int i = 0; i < 2; i++) {
            fram.add(new TextureRegion(text, 57,125,29,38));
            walk = new Animation<TextureRegion>(0.1f, fram);
        }

        fram.clear();

        return walk;
    }
    public void dispose(){
        bulles.dispose();
        bulletsArray.clear();
        text.dispose();
    }
    @Override
    public Vector2 vector2() {return new Vector2();}
}
