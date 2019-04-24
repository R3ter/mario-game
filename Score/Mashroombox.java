package com.mario.GrinningGameMoroi.Score;


import com.mario.GrinningGameMoroi.MyGdxGame;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;

/**
 * Created by waleed bas on 01/26/18.
 */


public class Mashroombox extends Tools {
    private Array<com.mario.GrinningGameMoroi.Score.Hearts> getBulletsArray() {
        return heartsArray;
    }
    private Array<com.mario.GrinningGameMoroi.Score.Hearts> heartsArray;
    static boolean t;static float x,y;
    public Mashroombox(MyGdxGame game, com.mario.GrinningGameMoroi.screen.Screens screen, float x, float y) {
        super(game, screen, x, y);
        heartsArray=new Array<com.mario.GrinningGameMoroi.Score.Hearts>();
        setPosition(x,y);
        t=false;
    }

    @Override
    public void drawcoins() {
//        setPosition(body.getPosition().x,body.getPosition().y);
//        if (t){    heartsArray.add(new Hearts(game, screen,body.getPosition().x,body.getPosition().y));t=false;
//            for (Hearts hearts:heartsArray){hearts.update();}
        }

    @Override
    public void defincoin() {
//        bdef = new BodyDef();
//        bdef.position.set(getX(),getY());
//        bdef.type = BodyDef.BodyType.StaticBody;
//        body = world.createBody(bdef);
//        FixtureDef fixtureDef = new FixtureDef();
//
//        CircleShape shap = new CircleShape();
//
//        fixtureDef.shape = shap;
//        fixtureDef.filter.maskBits = (short) (4);
//        fixtureDef.filter.categoryBits=2|128|8;
//        body.createFixture(fixtureDef).setUserData(this);
    }

    @Override
    public void defi(float x, float y) {

//        System.out.print(true);


    }
public void hit(Fixture fa){
//t=true;
//    fa.getBody().getPosition().x=x;
//    fa.getBody().getPosition().y=y;
//    System.out.print(true);
}
}