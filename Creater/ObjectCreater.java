package com.mario.GrinningGameMoroi.Creater;


import com.mario.GrinningGameMoroi.Enemies.Fastghost;
import com.mario.GrinningGameMoroi.Enemies.Cloud;
import com.mario.GrinningGameMoroi.Enemies.Gun;
import com.mario.GrinningGameMoroi.Enemies.Mashroom;
import com.mario.GrinningGameMoroi.Enemies.Turtle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.mario.GrinningGameMoroi.Enemies.King;
import com.mario.GrinningGameMoroi.Move.Drop;
import com.mario.GrinningGameMoroi.Move.Floater;
import com.mario.GrinningGameMoroi.Move.LeftRight;
import com.mario.GrinningGameMoroi.Move.MoveObject;
import com.mario.GrinningGameMoroi.MyGdxGame;
import com.mario.GrinningGameMoroi.Score.Hearts;
import com.mario.GrinningGameMoroi.Score.Coins;
import com.mario.GrinningGameMoroi.Enemies.Pippe;
import com.mario.GrinningGameMoroi.Enemies.Rat;
import com.mario.GrinningGameMoroi.Player.Player;


public class ObjectCreater {
     private Body Groundbody;
    private BodyDef grounds;

    private BodyDef grpund;
    private com.mario.GrinningGameMoroi.screen.Screens screens;

    private Array<Turtle> turtles;
    private Array<Mashroom> mashroom;

int mover;
    private Array<Cloud> cloud;
    private Array<Coins> coins;

    public Array<com.mario.GrinningGameMoroi.Enemies.Bat> getBat() {
        return bat;
    }

    public Array<Gun> getGun() {
        return gun;
    }

    private Array<Gun> gun;
    private Array<com.mario.GrinningGameMoroi.Enemies.Bat> bat;
    private Array<com.mario.GrinningGameMoroi.Enemies.Ghost> ghost;
    private Array<King> king;
    public boolean touched;
    public Array<Pippe> getPippe() {
        return pippe;
    }

    public void setPippe(Array<Pippe> pippe) {
        this.pippe = pippe;
    }

    private Array<Pippe> pippe;
    private Array<Fastghost> fastghost;

    public Array<Drop> getDrop() {
        return drop;
    }

    private Array<Drop> drop;

    public Array<Rat> getRat() {
        return rat;
    }
    Body Groundbodymove;
    private Array<Rat> rat;

    public Array<LeftRight> getLeft() {
        return left;
    }

    private Array<LeftRight> left;
    private Array<MoveObject> movr;
    public Array<Hearts> getHeartsArray() {
        return heartsArray;
    }
    private BodyDef bodyDef;
    private Array<Hearts> heartsArray;

    public Array<Floater> getVloat() {
        return vloat;
    }

    private Array<Floater> vloat;
    private MyGdxGame game;
    public Array<MoveObject> getmovr() {return movr;}
    public Array<King> getKing() {return king;}
    public Array<Fastghost> getFastghost() {return fastghost;}
    public Array<com.mario.GrinningGameMoroi.Enemies.Ghost> getGhost() {return ghost;}
    public Array<Cloud> getCloud() {
        return cloud;
    }
    public Array<Mashroom> getMashroom() {
        return mashroom;
    }
    public Array<Coins> getCoins() {return coins;}
    public Array<Turtle> getTurtles() {
        return turtles;
    }
    public void setTurtles(Array<Turtle> turtles) {
        this.turtles = turtles;
    }
    private Player player;
    public ObjectCreater(Player player, MyGdxGame game) {
        this.game=game;
        this.player = player;
        screens = new com.mario.GrinningGameMoroi.screen.Screens(game);

//        touched=false;
        grounds=new BodyDef();
        grpund = new BodyDef();
        FixtureDef fixter = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();



        for (MapObject object : screens.getmap().getLayers().get(19).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();


            grpund.type = BodyDef.BodyType.StaticBody;
            grpund.position.set(((rect.getX() + rect.getWidth() / 2) / 100), (rect.getY() + rect.getHeight() / 2) / 100);
            Groundbody = screens.getWorld().createBody(grpund);


            polygonShape.setAsBox((rect.getWidth() / 2) / 100, (rect.getHeight() / 2) / 100);
            fixter.shape = polygonShape;
            fixter.filter.categoryBits = 4 | 8 | 32;
            fixter.filter.maskBits = 2 | 4 | 8;
            Fixture ter = Groundbody.createFixture(fixter);
            ter.setUserData(this);

        }


        for (MapObject object : screens.getmap().getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();


            grpund.type = BodyDef.BodyType.StaticBody;
            grpund.position.set(((rect.getX() + rect.getWidth() / 2) / 100), (rect.getY() + rect.getHeight() / 2) / 100);
            Groundbody = screens.getWorld().createBody(grpund);

            polygonShape.setAsBox((rect.getWidth() / 2) / 100, (rect.getHeight() / 2) / 100);
            fixter.shape = polygonShape;
            fixter.filter.categoryBits = (2 | 4 | 16);
            fixter.filter.maskBits = 2 | 16;
            Groundbody.createFixture(fixter).setUserData("ground");
        }


        for (MapObject object : screens.getmap().getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();


            grpund.type = BodyDef.BodyType.StaticBody;
            grpund.position.set(((rect.getX() + rect.getWidth() / 2) / 100), (rect.getY() + rect.getHeight() / 2) / 100);
            Groundbody = screens.getWorld().createBody(grpund);

            polygonShape.setAsBox((rect.getWidth() / 2) / 100, (rect.getHeight() / 2) / 100);
            fixter.shape = polygonShape;
            fixter.filter.categoryBits = 2 | 4 | 16 | 8;
            fixter.filter.maskBits = 2 | 16;
            Groundbody.createFixture(fixter).setUserData("coinbox");


        }


        for (MapObject object : screens.getmap().getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();


            grpund.type = BodyDef.BodyType.StaticBody;
            grpund.position.set(((rect.getX() + rect.getWidth() / 2) / 100), (rect.getY() + rect.getHeight() / 2) / 100);
            Groundbody = screens.getWorld().createBody(grpund);


            polygonShape.setAsBox((rect.getWidth() / 2) / 100, (rect.getHeight() / 2) / 100);
            fixter.shape = polygonShape;
            fixter.filter.categoryBits = 8;
            Fixture ter = Groundbody.createFixture(fixter);
            ter.setUserData("brick");

        }


        for (MapObject object : screens.getmap().getLayers().get(10).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();


            grpund.type = BodyDef.BodyType.StaticBody;
            grpund.position.set(((rect.getX() + rect.getWidth() / 2) / 100), (rect.getY() + rect.getHeight() / 2) / 100);
            Groundbody = screens.getWorld().createBody(grpund);


            polygonShape.setAsBox((rect.getWidth() / 2) / 100, (rect.getHeight() / 2) / 100);
            fixter.shape = polygonShape;
            fixter.filter.categoryBits = 8;
            fixter.restitution = 1;
            Fixture ter = Groundbody.createFixture(fixter);
            ter.setUserData("up");

        }


        for (MapObject object : screens.getmap().getLayers().get(14).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();


            grpund.type = BodyDef.BodyType.StaticBody;
            grpund.position.set(((rect.getX() + rect.getWidth() / 2) / 100), (rect.getY() + rect.getHeight() / 2) / 100);
            Groundbody = screens.getWorld().createBody(grpund);


            polygonShape.setAsBox((rect.getWidth() / 2) / 100, (rect.getHeight() / 2) / 100);
            fixter.shape = polygonShape;
            fixter.filter.categoryBits = 8;
            fixter.isSensor = true;
            Fixture ter = Groundbody.createFixture(fixter);
            ter.setUserData("end");

        }
        for (MapObject object : screens.getmap().getLayers().get(13).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();


            grpund.type = BodyDef.BodyType.StaticBody;
            grpund.position.set(((rect.getX() + rect.getWidth() / 2) / 100), (rect.getY() + rect.getHeight() / 2) / 100);
            Groundbody = screens.getWorld().createBody(grpund);

            polygonShape.setAsBox((rect.getWidth() / 2) / 100, (rect.getHeight() / 2) / 100);
            fixter.shape = polygonShape;
            fixter.filter.categoryBits = 2 | 4 | 16;
            fixter.filter.maskBits = 2 | 16;
            fixter.isSensor = true;
            Groundbody.createFixture(fixter).setUserData("died");
        }


        for (MapObject object : screens.getmap().getLayers().get(9).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();


            grpund.type = BodyDef.BodyType.StaticBody;
            grpund.position.set(((rect.getX() + rect.getWidth() / 2) / 100), (rect.getY() + rect.getHeight() / 2) / 100);
            Groundbody = screens.getWorld().createBody(grpund);


            polygonShape.setAsBox((rect.getWidth() / 2) / 100, (rect.getHeight() / 2) / 100);
            fixter.shape = polygonShape;
            fixter.filter.categoryBits = 2 | 4 | 32;
            fixter.filter.maskBits = 2 | 16;
            fixter.isSensor = true;
            Fixture ter = Groundbody.createFixture(fixter);
            ter.setUserData("vector");

        }



        coins = new Array<Coins>();
        for (MapObject object : screens.getmap().getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            coins.add(new Coins(game, screens, rect.getX() / 100f, rect.getY() / 100f));

        }

//        if (screens.level>20){
        movr = new Array<MoveObject>();
        for (MapObject object : screens.getmap().getLayers().get(24).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            movr.add(new MoveObject(game, screens, rect.getX() / 100f, rect.getY() / 100f, rect.getWidth() / 100f, 15 / 100f, 0, 0.7f));

        }

        left = new Array<LeftRight>();
        for (MapObject object : screens.getmap().getLayers().get(26).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            left.add(new LeftRight(game, screens, rect.getX() / 100f, rect.getY() / 100f, rect.getWidth() / 100f, 15 / 100f, -0.7f,0));

        }
        vloat = new Array<Floater>();
        for (MapObject object : screens.getmap().getLayers().get(27).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            vloat.add(new Floater(game, screens, rect.getX() / 100f, rect.getY() / 100f, rect.getWidth() / 100f, 15 / 100f, 0,0));

        }
        for (MapObject object : screens.getmap().getLayers().get(25).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            movr.add(new MoveObject(game, screens, rect.getX() / 100f, rect.getY() / 100f, rect.getWidth() / 100f, 15 / 100f, 0, -0.5f));

        }

        drop = new Array<Drop>();
        for (MapObject object : screens.getmap().getLayers().get(23).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            drop.add(new Drop(game, screens, rect.getX() / 100f, rect.getY() / 100f, rect.getWidth() / 100f, 15 / 100f, 0, 0.5f));

        }


        mashroom = new Array<Mashroom>();
        for (MapObject object : screens.getmap().getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            mashroom.add(new Mashroom(game, screens, rect.getX() / 100f, rect.getY() / 100f));

        }


        turtles = new Array<Turtle>();
        for (MapObject object : screens.getmap().getLayers().get(12).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            turtles.add(new Turtle(game, screens, rect.getX() / 100f, rect.getY() / 100f));

        }
        cloud = new Array<Cloud>();
        for (MapObject object : screens.getmap().getLayers().get(15).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            cloud.add(new Cloud(game, screens, rect.getX() / 100f, rect.getY() / 100f));
        }

        ghost = new Array<com.mario.GrinningGameMoroi.Enemies.Ghost>();
        for (MapObject object : screens.getmap().getLayers().get(16).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            ghost.add(new com.mario.GrinningGameMoroi.Enemies.Ghost(game, screens, rect.getX() / 100f, rect.getY() / 100f));
        }

        fastghost = new Array<Fastghost>();
        for (MapObject object : screens.getmap().getLayers().get(17).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            fastghost.add(new Fastghost(game, screens, rect.getX() / 100f, rect.getY() / 100f));
        }

        bat = new Array<com.mario.GrinningGameMoroi.Enemies.Bat>();
        for (MapObject object : screens.getmap().getLayers().get(18).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bat.add(new com.mario.GrinningGameMoroi.Enemies.Bat(game, screens, rect.getX() / 100f, rect.getY() / 100f));
        }


        rat = new Array<Rat>();
        for (MapObject object : screens.getmap().getLayers().get(20).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            rat.add(new Rat(game, screens, rect.getX() / 100f, rect.getY() / 100f));
        }

        gun = new Array<Gun>();
        for (MapObject object : screens.getmap().getLayers().get(21).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            gun.add(new Gun(game, screens, rect.getX() / 100f, rect.getY() / 100f));
        }

        pippe = new Array<Pippe>();
        for (MapObject object : screens.getmap().getLayers().get(22).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            pippe.add(new Pippe(game, screens, rect.getX() / 100f, rect.getY() / 100f));
        }



        if (screens.level==20){
        king = new Array<King>();
        for (MapObject object : screens.getmap().getLayers().get(23).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            king.add(new King(game,player, screens, rect.getX() / 100f, rect.getY() / 100f));
        }
        }
    }


public void hit(final Fixture fixture, final float x, final float y){
    heartsArray = new Array<Hearts>();
    Gdx.app.postRunnable(new Runnable() {
        @Override
        public void run() {
            heartsArray.add(new Hearts(player,screens,x,y,game));
            Filter filter=new Filter();
            filter.categoryBits=2|4;
            fixture.setFilterData(filter);
             fixture.setUserData("empty");


            TiledMapTileLayer cel = (TiledMapTileLayer) screens.getmap().getLayers().get(3);
            TiledMapTileLayer.Cell waled = cel.getCell((int) (fixture.getBody().getPosition().x * 100 / 16),
                    (int) ((fixture.getBody().getPosition().y * 100 / 16)));

            TiledMapTileLayer cell = (TiledMapTileLayer) screens.getmap().getLayers().get(4);
            TiledMapTileLayer.Cell waleed = cell.getCell((int) (fixture.getBody().getPosition().x * 100 / 16),
                    (int) ((fixture.getBody().getPosition().y * 100 / 16)));
            if (waleed != null) {
                waleed.setTile(null);
        }

            else if (waled != null) {
                waled.setTile(null);}}
    });


}
//public void touch(){
//    touched=true;
//}
//public void notouch(){
//    touched=false;
//}
//
//public void move(){
//    if (touched){
//    bodyDef.position.y=bodyDef.position.y-1;}
//}


    public void dispose(){
        turtles.clear();
        mashroom.clear();
        coins.clear();
        ghost.clear();
        cloud.clear();


    }
}
