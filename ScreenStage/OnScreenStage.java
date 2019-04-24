package com.mario.GrinningGameMoroi.ScreenStage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mario.GrinningGameMoroi.MyGdxGame;
import com.mario.GrinningGameMoroi.Player.Player;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;


/**
 * Created by waleed bas on 01/27/18.
 */

public class OnScreenStage implements Disposable {
   private Stage stage;
   private Label score;
    private Label hart;
    private Player player;
    private Label time;
    private float timer1;
    private int getscore,timer,x;
    private Preferences pre;
    private Texture text;
    public OnScreenStage(Player player, MyGdxGame game) {
        timer=0;
        this.player=player;
       com.mario.GrinningGameMoroi.screen.Screens screens=new com.mario.GrinningGameMoroi.screen.Screens(null);
        text=new Texture("buttons/Black.jpg");
     pre= Gdx.app.getPreferences("My Preferences");
        getscore=pre.getInteger("Score");
        timer=0;
       Viewport viewport = new StretchViewport(400 , 900 );
        BitmapFont font=new BitmapFont();
        font.getData().setScale(0.7f,3);
        stage=new Stage(viewport);
        Label space=new Label("",new Label.LabelStyle(font, Color.BLACK));
        score=new Label("Score: "+(getscore), new Label.LabelStyle(font, Color.BLACK));
        time=new Label("Time: " + timer, new Label.LabelStyle(font, Color.BLACK));
        Label level=new Label("Level: " + screens.level, new Label.LabelStyle(font, Color.BLACK));
         hart=new Label(" X " + player.life, new Label.LabelStyle(font, Color.WHITE));
        Table table=new Table();
        Image heart=new Image(new Texture("buttons/heart.png"));
//        Image heart=new Image(new Texture("buttons/heart.png"));
//        Image heart=new Image(new Texture("buttons/heart.png"));
        table.top();
        table.add().width(5);
        table.add(heart).size(20,70);
        table.add(hart).size(15,40);
//        table.add(heart).size(15,30);
        table.setFillParent(true);
        table.add();
        table.add(level).expandX().padTop(20);
        table.add();
        table.add(score).expandX().padTop(20);
        table.add();
        table.add(time).expandX().padTop(20).spaceLeft(5);
        table.add();
        table.add(space).expandX().padTop(20).spaceLeft(5);
//        stage.setDebugAll(true);
        table.sizeBy(10,10);



//        Table table1=new Table();
//        table1.left().top();
//        table1.add(heart).size(20);



//        score.setPosition(5,5);
        table.add();
        stage.addActor(table);
    }
    public void labeldraw(){

        stage.draw();

    }
public void update(){
    x=x+1;
    hart.setText(" X " + player.life);

    if (x>60){
        x=0;
        time.setText("Time: " +(timer+1));
        timer=timer+1;
    }
    getscore = pre.getInteger("Score");
    score.setText("Score: "+(getscore));
if (player.hited) {
    timer1=timer1+.3f;
    if (timer1 < 2&&timer1!=0) {
        hart.setFontScale( timer1,timer1);
        hart.setColor(Color.RED);
    }
}
    else if (!player.hited){hart.setFontScale(1,2);timer1=0;hart.setColor(Color.BLACK);}

}




    @Override
    public void dispose() {
        stage.clear();
        stage.dispose();


    }
    public void draw(){
        stage.getBatch().begin();
        stage.getBatch().draw(text,0,0,600f,300f);
        stage.getBatch().end();
    }
}




















