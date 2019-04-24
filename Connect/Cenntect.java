package com.mario.GrinningGameMoroi.Connect;


import com.mario.GrinningGameMoroi.Creater.ObjectCreater;
import com.mario.GrinningGameMoroi.Enemies.Gunnbullets;
import com.mario.GrinningGameMoroi.Enemies.King;
import com.mario.GrinningGameMoroi.Enemies.Mashroom;
import com.mario.GrinningGameMoroi.Enemies.Turtle;
import com.mario.GrinningGameMoroi.MyGdxGame;
import com.mario.GrinningGameMoroi.Score.Coins;
import com.mario.GrinningGameMoroi.Score.Hearts;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mario.GrinningGameMoroi.Enemies.Bullets;
import com.mario.GrinningGameMoroi.Enemies.Rat;
import com.mario.GrinningGameMoroi.Player.Player;

/**
 * Created by waleed bas on 01/24/18.
 */

public class Cenntect implements ContactListener {
   private com.mario.GrinningGameMoroi.screen.Screens screens;
    private MyGdxGame game;
    public Cenntect() {
        screens=new com.mario.GrinningGameMoroi.screen.Screens(game);
        game=new MyGdxGame(null);
    }


    @Override

    public void beginContact(Contact contact) {

        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();






        if (fa.getUserData()=="head"&&fb.getFilterData().categoryBits==(4|8|32)){
            ((ObjectCreater)fb.getUserData()).hit(fb,fb.getBody().getPosition().x,fb.getBody().getPosition().y);
        }

        if (fb.getFilterData().categoryBits==(2 |128|64|256)&&fa.getFilterData().categoryBits==(short) (4|2|8)){
            if (fa.getUserData()!="head"&&fa.getFilterData().categoryBits!=(4|2|64)){
            ((Hearts)fb.getUserData()).take(fb);}
        }
        if (fa.getUserData()=="end"&&fb.getFilterData().categoryBits==(short) (2|4|8)){
            ((Player)fb.getUserData()).win(null);}

        if (fa.getFilterData().categoryBits==(short) (2|4|64)&&fb.getUserData()!="vector"&&fb.getUserData()!="up"
                &&!fb.isSensor()){
            ((Player)fa.getUserData()).onground();}


//        if (fa.getFilterData().categoryBits==(short) (2|4|64)&&fb.getFilterData().maskBits==(2|32)){
//            ((ObjectCreater)fb.getUserData()).touch();
//        }
//
        if (((fb.getFilterData().categoryBits==(8|2|16)||
                fb.getFilterData().categoryBits==(short)(2)||

                fb.getFilterData().categoryBits==(8|2|64|32))
                &&fa.getFilterData().categoryBits==(short) (2|4|8))&&fa.getUserData()!="head"){

                ((Player)fa.getUserData()).lose(fa,fb,null);

        }

        if (((fb.getFilterData().categoryBits==(8|2|16)||
                fb.getFilterData().categoryBits==(short)(2)||
                fb.getFilterData().categoryBits==(8|2|64|32))
                &&fa.getFilterData().categoryBits==(short) (4|2|64|8|144))&&fa.getUserData()!="head"){
            ((Player)fa.getUserData()).side();
        }
        if (((fb.getFilterData().categoryBits==(8|2|16)||
                fb.getFilterData().categoryBits==(short)(2)||
                fb.getFilterData().categoryBits==(8|2|64|32))
                &&fa.getFilterData().categoryBits==(short) (4|2|64|144))&&fa.getUserData()!="head"){
            ((Player)fa.getUserData()).side2();
        }
        if (((fa.getFilterData().categoryBits==(short) (4|2|64|8|144))&&fa.getUserData()!="head")&&
        fb.getBody().isBullet()){((Player)fa.getUserData()).side2();}

        if(fa.getFilterData().categoryBits==(short) (2|4|64|96)&&fb.getFilterData().categoryBits==(short) (2|4|8)){
            ((Gunnbullets)fa.getUserData()).kill(fa);
        }

        if(fa.getUserData()=="died"&&fb.getFilterData().categoryBits==(short) (2|4|8)){
            ((Player)fb.getUserData()).lose(fa,fb,null);
        }
        if(fb.getUserData()=="died"&&fa.getFilterData().categoryBits==(short) (4|2|64|144)){
            ((Player)fa.getUserData()).side2();
        }
        if(fb.getUserData()=="died"&&fa.getFilterData().categoryBits==(short) (4|2|64|8|144)){
            ((Player)fa.getUserData()).side();
        }

        if (fb.getFilterData().categoryBits==(short)( 4|2|8|32)&&fa.getFilterData().categoryBits==(short) (2|4|8)){
            ((Coins)fb.getUserData()).take(fa,fb);
        }

        if (fb.getFilterData().maskBits==(short)(4 | 32 | 512)&&fa.getFilterData().categoryBits==(short) (2 | 4 | 32)){
            ((King)fb.getUserData()).onhit(fa,fb);
            System.out.print(1);
        }


        if (fa.getUserData()=="vector"&&((fb.getFilterData().categoryBits==(8|2|16)))){
//            System.out.print(true);
            ((Turtle) fb.getUserData()).onhit(fa,fb);
        }



        if ( fb.getFilterData().categoryBits==(8|2|64|32)&&fa.getUserData()=="vector"){

//            System.out.print(true);
            ((Turtle) fb.getUserData()).onhit(fa,fb);
        }
        if(fa.getUserData()=="vector"&&fb.getFilterData().categoryBits==(short)(2)){

            ((Mashroom)fb.getUserData()).onhit(fa,fb);
        }
//        if (fb.getFilterData().categoryBits==(2|128|256)&&fa.getUserData()=="ground"){
//            ((Dumper)fb.getUserData()).onhitground();
//        }
        if(fa.getUserData()=="vector"&&fb.getFilterData().categoryBits==(short)(2 |128|64|256)){

            ((Hearts)fb.getUserData()).onhit(fa,fb);
        }



        if (fb.getFilterData().categoryBits==(8|2|4)&&fa.getFilterData().categoryBits==(short) (8|2|64|32)){
            ((Turtle)(fa.getUserData())).stop();

        }

        if(fb.getFilterData().categoryBits==(short) (2|4|8)&&fa.getFilterData().categoryBits==(short)(2)&&
                (fa.getFilterData().categoryBits!=(short) (2|4|8))&&fb.getFilterData().categoryBits!=(short)(2)){
            ((Mashroom)fa.getUserData()).onkill(fa,fb);
        }
//        if (fb.getFilterData().categoryBits==(short) (2|4|8)&&fa.getFilterData().categoryBits==(2|4)){
//            ((King)fa.getUserData()).onhit(fa,fb);
//        }

        if (fa.getFilterData().categoryBits==(8|2|16)&&(fb.getFilterData().categoryBits==(short) (2|4|8)||fb.getFilterData().categoryBits==(short) (2|4|8))
                && (fa.getFilterData().categoryBits!=(short) (2|4|8))&&fb.getFilterData().categoryBits!=(short)(2)){

            ((Turtle)(fa.getUserData())).onkill(fa,fb);
        }

        if (((fa.getFilterData().categoryBits==(short) (2|4|8))
                &&fb.getFilterData().categoryBits==(2|128))
                &&fa.getUserData()!="head"){
            ((Player)fa.getUserData()).lose(fa,fb,null);}

        if (fa.getFilterData().categoryBits==(short) (2|4|8)&&fb.getFilterData().maskBits==(4|32|2)){
            ((Rat)fb.getUserData()).onhit(fa,fb);
        }

        if (fb.getFilterData().categoryBits==(2|128)&&((fa.getUserData()=="ground")||fa.getUserData()=="up"
                ||fa.getUserData()=="bricks"||fa.getUserData()=="coinbox")){
            ((Bullets)(fb.getUserData())).destroy(fb,fa);
        }
    }

    @Override
    public void endContact(Contact contact) {

        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (fa.getFilterData().categoryBits==(2|4|64)&&fb.getUserData()=="up"){
            ((Player)(fa.getUserData())).setSoundjump();
        }

        if (fb.getUserData()=="brick"&&fa.getUserData()=="head") {
            screens.startdestroy(fb);
            screens.destroybrics(fa,fb);}

        if (fb.getUserData()=="coinbox"&&fa.getUserData()=="head"){
            screens.coinboxs(fa,fb);}
        if (fb.getUserData()=="empty"&&fa.getUserData()=="head"){
            screens.soundempty();}

//        if (fa.getFilterData().categoryBits==(short) (2|4|64)&&fb.getFilterData().maskBits==(2|32)){
//            ((ObjectCreater)fb.getUserData()).notouch();
//        }

        if (fa.getFilterData().categoryBits==(short) (2|4|64)&&fb.getUserData()!="vector"
               &&fb.getUserData()!="up"){
            ((Player)fa.getUserData()).setOnground();}

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
    public void dispose(){

    }

}









