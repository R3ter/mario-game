package com.mario.GrinningGameMoroi.screen;
/*created by waleeed waleed.sukhon77@gmail.com*/
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mario.GrinningGameMoroi.Connect.Cenntect;
import com.mario.GrinningGameMoroi.Creater.ObjectCreater;
import com.mario.GrinningGameMoroi.Enemies.King;
import com.mario.GrinningGameMoroi.Move.MoversCreator;
import com.mario.GrinningGameMoroi.MyGdxGame;
import com.mario.GrinningGameMoroi.Score.DestroyedBricks;
import com.mario.GrinningGameMoroi.Score.Hearts;
import com.mario.GrinningGameMoroi.Score.Tools;
import com.mario.GrinningGameMoroi.Enemies.EnemiesCreater;
import com.mario.GrinningGameMoroi.Score.Coins;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
/*created by waleeed Email waleed.sukhon77@gmail.com*/
import com.mario.GrinningGameMoroi.Score.Coinbox;
import com.mario.GrinningGameMoroi.Controller.Controller;
import com.mario.GrinningGameMoroi.Player.Player;
import com.mario.GrinningGameMoroi.ScreenStage.OnScreenStage;
/*created by waleeed Email waleed.sukhon77@gmail.com*/

/**
 * Created by waleed bas on 02/07/18.
 */



public class Screens extends InputAdapter implements Screen {

    private MyGdxGame game;
    private ObjectCreater objectCreater;
    private Player palyer;
//    private Hearts hearts;
    private Coins coins;
    private OrthographicCamera cam;
    private Viewport viewport;
    public static TiledMap map;
    public static int level;
    private Box2DDebugRenderer debugRenderer;
    private  TiledMapTileSet tileSet;
    private static boolean win;
    public static World world;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private static boolean draw;
    private static boolean died,pause;
    private  TmxMapLoader.Parameters params;
    private Preferences pre;
    public static boolean playing;
    private Controller controller;
    private Stage stage;

    BodyDef grpund;
    FixtureDef fixter;
    PolygonShape polygonShape;
    Body Groundbody;
    private OnScreenStage onScreenStage;
    private Coinbox coinbox;
    private King king;
    private Preferences prea;
    private boolean is;
    private DestroyedBricks destroyedBricks;
    private static boolean destroyedbricks;
    private float pos;private Music music;
    private static Sound soundbrick;
    private Stage stage1;
    private static Sound soundcoins;
    private int timeloser;
    private static Sound soundempty;
    private Texture more;
    public int mover;

    private static boolean boxheart;

    public Screens(MyGdxGame game) {
        pre= Gdx.app.getPreferences("My Preferences");
        prea= Gdx.app.getPreferences("level");
        pre.putInteger("Score",0);
        died = false;
        win=false;
        playing=true;
        this.game = game;
        destroyedbricks=false;
        destroyedBricks=new DestroyedBricks(game);
        is=false;
        boxheart=false;
        coinbox=new Coinbox(this,palyer);




    }

    public void lose(MyGdxGame gome) {
        this.game = gome;
        died = true;
        pre.remove("Score");


    }
    public void win(MyGdxGame gome) {
        this.game = gome;
        win = true;
        pre.putInteger("current stars",palyer.life);
        if (pre.getInteger(level+"stars")<palyer.life){
            pre.putInteger(level+"stars",palyer.life).flush();
        }
       if (pre.getInteger("Score")<pre.getInteger(level+""))
       {pre.putInteger(level+"",pre.getInteger("Score"));}
        pre.remove("Score");

    }

    @Override
    public void show() {
        params = new TmxMapLoader.Parameters();
        params.generateMipMaps = true;
        setmap();
        world = new World(new Vector2(0, -10f), true);

        game.load();

        world.setContactListener(new Cenntect());
        cam = new OrthographicCamera(400 / 100, 200 / 100);
        viewport = new StretchViewport(400 / 100, 900 / 100);
        Viewport viewport1 = new StretchViewport(400, 900 );
        cam.position.set(1, 0.55f, 0);
        stage=new Stage(viewport);
        stage1=new Stage(viewport1);



        soundbrick=game.manager().get("sounds/smb_breakblock.wav",Sound.class);
        soundcoins=game.manager().get("sounds/smb_coin.wav",Sound.class);
        soundempty=game.manager().get("sounds/smb_bump.wav",Sound.class);
        music = game.manager().get("sounds/mario.ogg",Music.class);
        if (pre.getInteger("music")==0){
        music.play();}
        music.setVolume(0.5f);
        music.setLooping(true);
            pause=false;






        tileSet = map.getTileSets().getTileSet("SNES - Super Mario World - General Tiles");


        tiledMapRenderer = new OrthogonalTiledMapRenderer(map, 1f / 100f);



        palyer = new Player(game);
        onScreenStage=new OnScreenStage(palyer,game);


        coins = new Coins(game,this, -1, 1);

        objectCreater = new ObjectCreater(palyer,game);

        coins.defincoin();

        controller = new Controller(palyer,this,game);

        game.setad(false);

        debugRenderer = new Box2DDebugRenderer(false,false,false,false,false,false);
//        debugRenderer = new Box2DDebugRenderer();
        if (level==0){level=1;}
        if (level==1){pos=63.39f;}
        if (level==2){pos=37.0f;}
        if (level==3){pos=63.7f;}
        if (level==4){pos=61f;}
        if (level==5){pos=46.61f;}
        if (level==6){pos=53.63f;}
        if (level==7){pos=47.23f;}
        if (level==8){pos=65.00f;}
        if (level==9){pos=67.08f;}
        if (level==10){pos=60.65f;}
        if (level==11){pos=71.20f;}
        if (level==12){pos=109.0014f;}
        if (level==13){pos=86.1f;}
        if (level==14){pos=60.541676f;}
        if (level==15){pos=91.88f;}
        if (level==16){pos=77.313f;}
        if (level==17){pos=68.36f;}
        if (level==18){pos=69f;}
        if (level==19){pos=109.0014f;}

//        hearts=new Hearts(palyer,this,-1,-1,game);
        cam.zoom=1.5f;

        if (pre.getInteger("music") == 0){music.play();}

        if (level>prea.getInteger("level")){
            prea.putInteger("level",level).flush();}


         grpund = new BodyDef();
         fixter = new FixtureDef();
         polygonShape = new PolygonShape();
         Groundbody = getWorld().createBody(grpund);

    }


    @Override
    public void render(float delta) {




        game.batch.setProjectionMatrix(cam.combined);



        cam.update();
        if (!pause) {




        if (playing){
            world.step(1 / 60f, 6, 2);}
        }


        tiledMapRenderer.setView(cam);
        tiledMapRenderer.render();
        debugRenderer.render(world, cam.combined);




        if (!pause) {
//            if (level>20){
            for (MoversCreator enemiesCreater : objectCreater.getLeft()) {
                enemiesCreater.update(palyer);
            }
                for (MoversCreator enemiesCreater : objectCreater.getmovr()) {
                    enemiesCreater.update(palyer);
                }
            for (MoversCreator enemiesCreater : objectCreater.getVloat()) {
                enemiesCreater.update(palyer);
            }
            for (MoversCreator enemiesCreater : objectCreater.getDrop()) {
                enemiesCreater.update(palyer);
            }
            for (EnemiesCreater enemiesCreater : objectCreater.getTurtles()) {
                enemiesCreater.update(palyer, delta);
            }
            for (EnemiesCreater enemiesCreater : objectCreater.getPippe()) {
                enemiesCreater.update(palyer, delta);
            }
            for (EnemiesCreater enemiesCreater : objectCreater.getGun()) {
                enemiesCreater.update(palyer, delta);
            }

            for (EnemiesCreater enemiesCreater : objectCreater.getRat()) {
                enemiesCreater.update(palyer, delta);
            }
            if (objectCreater.getHeartsArray() != null) {
                for (Hearts hearts : objectCreater.getHeartsArray()) {
                    hearts.update();
                }
            }
            if (objectCreater.getHeartsArray() != null) {
                for (Hearts hearts : objectCreater.getHeartsArray()) {
                    hearts.drawhearts();
                }
            }

            for (EnemiesCreater enemiesCreater : objectCreater.getBat()) {
                enemiesCreater.update(palyer, delta);
            }

            for (EnemiesCreater enemiesCreater : objectCreater.getGhost()) {
                enemiesCreater.update(palyer, delta);
            }


            for (EnemiesCreater enemiesCreater : objectCreater.getMashroom()) {
                enemiesCreater.update(palyer, delta);
            }
            for (EnemiesCreater enemiesCreater : objectCreater.getCloud()) {
                enemiesCreater.update(palyer, delta);
            }

            for (EnemiesCreater enemiesCreater : objectCreater.getFastghost()) {
                enemiesCreater.update(palyer, delta);
            }
            if (level == 20) {
                for (EnemiesCreater enemiesCreater : objectCreater.getKing()) {
                    enemiesCreater.update(palyer, delta);

            }}

        }

        for (Tools tools : objectCreater.getCoins()) {
            tools.drawcoins();
        }



        for (MoversCreator enemiesCreater : objectCreater.getmovr()) {
            enemiesCreater.defineEnemy();
        }
        for (MoversCreator enemiesCreater : objectCreater.getDrop()) {
            enemiesCreater.defineEnemy();
        }
        for (MoversCreator enemiesCreater : objectCreater.getLeft()) {
            enemiesCreater.defineEnemy();
        }

        for (MoversCreator enemiesCreater : objectCreater.getVloat()) {
            enemiesCreater.defineEnemy();
        }
                game.batch.begin();


                for (Tools tools : objectCreater.getCoins()) {
                    tools.draw(game.batch);}



        for (EnemiesCreater enemiesCreater : objectCreater.getCloud()) {
            enemiesCreater.draw(game.batch);
        }

        for (EnemiesCreater enemiesCreater : objectCreater.getBat()) {
            enemiesCreater.draw(game.batch);
        }
        for (EnemiesCreater enemiesCreater : objectCreater.getRat()) {
            enemiesCreater.draw(game.batch);
        }
        for (EnemiesCreater enemiesCreater : objectCreater.getFastghost()) {
            enemiesCreater.draw(game.batch);
        }
        for (EnemiesCreater enemiesCreater : objectCreater.getGhost()) {
            enemiesCreater.draw(game.batch);
        }
//        for (EnemiesCreater enemiesCreater : objectCreater.getDumper()) {
//            enemiesCreater.draw(game.batch);
//        }
//            objectCreater.move();

                for (EnemiesCreater enemiesCreater : objectCreater.getMashroom()) {
                    enemiesCreater.draw(game.batch);
                }

                for (EnemiesCreater enemiesCreater : objectCreater.getTurtles()) {
                    enemiesCreater.draw(game.batch);
                }



                game.batch.end();

                if (draw) {
                    coinbox.drawboxcoin();
                }
                coins.drawcoins();
        if (destroyedbricks){
            for (int i=1; i<4;i++){
            destroyedBricks.drawdest();}}
              if (!pause){ palyer.render();}


                game.batch.begin();
                coins.draw(game.batch);

                palyer.draw(game.batch);



                game.batch.end();

if (!pause) {
    if (palyer.getY() < -1) {
        palyer.lose(null,null,game);
        palyer.body.setLinearVelocity(-3,7);
        is=true;
    }
    if (is) {
        timeloser = timeloser + 1;
        if (timeloser < 40) {
            palyer.body.getFixtureList().first().setSensor(true);}
        if (timeloser > 40) {
            is = false;
            timeloser = 0;
            palyer.body.getFixtureList().first().setSensor(false);
        }
    }
    if (died) {
        died(game);
        map.dispose();
    }

    if (win) {
        won(game);
        map.dispose();
    }

}


if (level!=20) {

    if (palyer.body.getPosition().x < 3) {
        cam.position.x = 3;
    }
    if (palyer.body.getPosition().x > pos) {
        cam.position.x = pos;
    } else if (palyer.body.getPosition().x > 3 && palyer.body.getPosition().x <
            pos) {
        cam.position.set(palyer.body.getPosition().x, cam.position.y, 1);

    }

//        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
//            System.out.print(palyer.body.getPosition().x);
//        }
    onScreenStage.labeldraw();
    onScreenStage.draw();
}


        if (!pause) {
                onScreenStage.update();

                controller.draw();
            }

            if (pause) {
                stage.draw();
            }


        if (level==20){
            cam.position.set(3, cam.position.y, 1);

        }
        if (!pause){
            if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)){
                if (pre.getInteger("sound")==0) {
                    game.manager().get("sounds/smb_pause.wav", Sound.class).play();
                }}}
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)){
            if (!pause){pause();}

            }



   }



    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        controller.resize(width,height);
    }

    @Override
    public void pause() {
        pause=true;
        music.pause();
        Gdx.input.setInputProcessor(stage);

        Image img=new Image((Texture) game.manager().get("buttons/button_resume.png"));
        Image home=new Image((Texture) game.manager().get("buttons/button_home.png"));
        Image restart=new Image((Texture) game.manager().get("buttons/button_restart.png"));
        Table tab=new Table();
        tab.add();
        tab.add(img).size(1f,1f);
        Table table=new Table();
        table.add();
        table.add(restart).size(1f,1f);
        Table table1=new Table();
        table1.add(home).size(1f,1f);
//        table.add().width(0.1f);
        tab.setPosition(1.5f,8f);
        table.setPosition(1.5f,5f);
        table1.setPosition(1.5f,2f);
        stage.addActor(table1);
//        img.setScale(0.4f,0.5f);
        stage.addActor(table);
        stage.addActor(tab);
        restart.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Screens(game));
                music.stop();
                if (pre.getInteger("music")==0){ music.play();}
                music.setLooping(true);
                return true;
            }

        });
        home.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Start(game));
                dispose();
                return true;}

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {


            }
        });
        img.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                resumea();
                return true;}

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {


            }
        });

    }

    @Override
    public void resume() {

    }
public void resumea(){  pause=false;
    controller.recontroller();
    if (pre.getInteger("music")==0){ music.play();}}
    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        controller.dispos();
        map.dispose();
        world.dispose();
//        coinbox.dispose();
        destroyedBricks.dispose();
        stage.clear();
        stage.dispose();
        debugRenderer.dispose();
        objectCreater.dispose();
        palyer.dispose();
        onScreenStage.dispose();
        coins.dispose();
        objectCreater.dispose();


    }


    public void destroybrics(Fixture fa, Fixture fb) {

        if (pre.getInteger("sound")==0){
        soundbrick.play();}
        TiledMapTileLayer cell = (TiledMapTileLayer) map.getLayers().get(3);
        TiledMapTileLayer.Cell waleed = cell.getCell((int) (fb.getBody().getPosition().x * 100 / 16),
                (int) ((fb.getBody().getPosition().y * 100 / 16)));
        if (waleed != null) {
            waleed.setTile(null);
            Filter filter = new Filter();
            filter.categoryBits = 16;
            fb.setFilterData(filter);
        }
    }

    public void coinboxs(Fixture fa, Fixture fb) {
       coinbox.coincreater(fa,fb);
        fb.setUserData("empty");
        draw=true;if (pre.getInteger("sound")==0){
        soundcoins.play();}
    }


    public World getWorld() {
        return world;
    }

    public TiledMap getmap() {
        return map;
    }

    public void died(MyGdxGame game) {
        this.game = game;
        music.stop();
        game.setScreen(new com.mario.GrinningGameMoroi.screen.GameOver(game));
//        dispose();
    }
    public void won(MyGdxGame game) {
        music.stop();
        this.game = game;
        game.setScreen(new com.mario.GrinningGameMoroi.screen.WinScreen(this,game));
//        dispose();
    }

    public void setmap() {

        switch (level) {
            case 1:
                map = new TmxMapLoader().load("maps/level1.tmx",params);
                break;
            case 2:
                map = new TmxMapLoader().load("maps/level2.tmx",params);
                break;
            case 3:
                map = new TmxMapLoader().load("maps/level3.tmx",params);
                break;
            case 4:
                map = new TmxMapLoader().load("maps/level4.tmx",params);
                break;
            case 5:
                map = new TmxMapLoader().load("maps/level5.tmx",params);
                break;
            case 6:
                map = new TmxMapLoader().load("maps/level6.tmx",params);
                break;
            case 7:
                map = new TmxMapLoader().load("maps/level7.tmx",params);
                break;
            case 8:
                map = new TmxMapLoader().load("maps/level8.tmx",params);
                break;
            case 9:
                map = new TmxMapLoader().load("maps/level9.tmx",params);
                break;
            case 10:
                map = new TmxMapLoader().load("maps/level10.tmx",params);
                break;
            case 11:
                map = new TmxMapLoader().load("maps/level11.tmx",params);
                break;
            case 12:
                map = new TmxMapLoader().load("maps/level12.tmx",params);
                break;
            case 13:
                map = new TmxMapLoader().load("maps/level13.tmx",params);
                break;
            case 14:
                map = new TmxMapLoader().load("maps/level14.tmx",params);
                break;
            case 15:
                map = new TmxMapLoader().load("maps/level15.tmx",params);
                break;
            case 16:
                map = new TmxMapLoader().load("maps/level16.tmx",params);
                break;
            case 17:
                map = new TmxMapLoader().load("maps/level17.tmx",params);
                break;
            case 18:
                map = new TmxMapLoader().load("maps/level18.tmx",params);
                break;
            case 19:
                map = new TmxMapLoader().load("maps/level19.tmx",params);
                break;
            case 20:
                map = new TmxMapLoader().load("maps/leve20.tmx",params);
                break;

            default:
                map = new TmxMapLoader().load("maps/level1.tmx",params);
        }

    }
    public void startdestroy(Fixture fa){
        destroyedBricks.testroyedBricks(fa);
        destroyedbricks=true;

    }
public void soundempty(){
    if (pre.getInteger("sound")==0){soundempty.play();}
}

public void stopmusic() {
    if (pre.getInteger("music") == 1) {
        music.stop();
    }
    else if (pre.getInteger("music") == 0){music.play();}
}


}
