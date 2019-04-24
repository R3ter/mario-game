package com.mario.GrinningGameMoroi.Move;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mario.GrinningGameMoroi.MyGdxGame;
import com.mario.GrinningGameMoroi.Player.Player;
import com.mario.GrinningGameMoroi.screen.Screens;

/**
 * Created by waleed bas on 05/09/18.
 */

public class MoveObject extends MoversCreator {

    public MoveObject(MyGdxGame game, Screens screens, float x, float y, float width, float hight,float r,float t) {
        super(game, screens, x, y, width, hight,r,t);
        this.hight=hight;this.width=width;this.r=r;this.t=t;
        grpund = new BodyDef();
        FixtureDef fixter = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();
        grpund.type = BodyDef.BodyType.KinematicBody;
        grpund.position.set(((x + width / 2) ), (y + hight / 2) );
        Groundbody = screens.getWorld().createBody(grpund);


        polygonShape.setAsBox((width / 2) , (hight / 2) );
        fixter.shape = polygonShape;
        fixter.filter.categoryBits = 4;
        fixter.filter.maskBits=4;
        Groundbody.createFixture(fixter).setUserData("ground");
        texture= game.manager().get("move.png");

    }

    @Override
    public void update(Player player) {
        setPosition(Groundbody.getPosition().x - getWidth() / 2, (Groundbody.getPosition().y - getHeight() / 2));
        Groundbody.setLinearVelocity(r,t);


        if (getY()>=2){Groundbody.setTransform(getX(),0.001f,0);}
        if (getY()<-.3f){Groundbody.setTransform(getX(),2,0);}

    }


    @Override
    public void defineEnemy() {
        MyGdxGame.batch.begin();
        MyGdxGame.batch.draw(texture,getX()- width/ 2,getY()- hight / 2,width,hight);
        MyGdxGame.batch.end();
    }
}
