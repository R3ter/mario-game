package com.mario.GrinningGameMoroi.Player;

import com.mario.GrinningGameMoroi.MyGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;


/**
 * Created by waleed bas on 01/13/18.
 */

public class Player extends Sprite {

    private MyGdxGame game;
   public  Body body;///////////////////////////////////static kan
   private enum State{STANDING,RUNNING,JUMPING,Winning,LOSING,AFK,hited}
    private boolean win,lose;
    private TextureRegion playeranimation;
   private com.mario.GrinningGameMoroi.screen.Screens screens;
    public static int life;
    private int x,hittime;
   private boolean runningRight;
    private Texture texture1;
   public boolean onground;
    private Texture hit;
    private int f;
    private Texture die;

    public boolean hited;
//    private boolean afk,heisafk;
//    private int afktime;
    int brek;
    private Sound music;
    private Texture texture;
   private Animation<TextureRegion> playerrunning,playerjumping,playerlosing,playerafk,playerhited;
     private int time;
    private Preferences pre;

    public Player(MyGdxGame game){
        pre= Gdx.app.getPreferences("My Preferences");
runningRight=true;
        win=false;
        life=3;
        lose=false;
        f=-3;
        this.game=game;
//        afk=false;
//        heisafk=false;
        screens=new com.mario.GrinningGameMoroi.screen.Screens(game);
        x=0;
        onground=false;
        texture=game.manager().get("karn3.png",Texture.class);
        texture1=game.manager().get("win.png",Texture.class);
         hit=game.manager().get("hited.png",Texture.class);


        die=game.manager().get("die.png",Texture.class);

        createplayer();
        state();
        setSize(3f,3f);

        time=1;
        body.setActive(true);
    }


    private void update(){

        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2+.04f);


        if((body.getLinearVelocity().x < 0 || !runningRight) && !isFlipX()){
            flip(true, false);
            runningRight = false;
        }

        if((body.getLinearVelocity().x > 0 || runningRight) && isFlipX()){
             runningRight=true;
            flip(true, false);

    }}


    private void createplayer() {

        music=game.manager().get("sounds/smb_mariodie.wav",Sound.class);

       BodyDef bdef = new BodyDef();
        bdef.position.set(1, 1);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = screens.getWorld().createBody(bdef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shap = new CircleShape();
        shap.setRadius(7.1f / 100f);
        fixtureDef.shape = shap;
        fixtureDef.filter.maskBits = (short) (2 | 6 | 8 );
        fixtureDef.filter.categoryBits=4|2|8;
        body.createFixture(fixtureDef).setUserData(this);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-3f / 100f, 7f / 100f), new Vector2(3f / 100f, 7f / 100f));
        fixtureDef.shape = head;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData("head");

        EdgeShape foot = new EdgeShape();
        foot.set(new Vector2(-3f / 100f, -12f / 100f), new Vector2(3f / 100f, -12f / 100f));
        fixtureDef.shape = foot;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits=4|2|64;
        body.createFixture(fixtureDef).setUserData(this);


        EdgeShape side1 = new EdgeShape();
        side1.set(new Vector2(9f / 100f, 5f / 100f), new Vector2(9f / 100f, -5f / 100f));
        fixtureDef.shape = side1;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits=4|2|64|144;
        body.createFixture(fixtureDef).setUserData(this);


        EdgeShape side2 = new EdgeShape();
        side2.set(new Vector2(-9f / 100f, 5f / 100f), new Vector2(-9f / 100f, -5f / 100f));
        fixtureDef.shape = side2;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits=4|2|64|8|144;
        body.createFixture(fixtureDef).setUserData(this);




        Array<TextureRegion> frame = new Array<TextureRegion>();
        for (int i = 0; i < 3; i++) {
            frame.add(new TextureRegion(texture, i*105,0,80,80));
            playerrunning = new Animation<TextureRegion>(0.3f, frame);

        }
        frame.clear();

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 3; i++) {
            frames.add(new TextureRegion(texture, 140,95,95,92));
            playerjumping = new Animation<TextureRegion>(0.3f, frames);
        }
        frames.clear();

        Array<TextureRegion> fram = new Array<TextureRegion>();
        for (int i = 0; i < 3; i++) {
            fram.add(new TextureRegion(die, i*229,0,229,196));
            playerlosing = new Animation<TextureRegion>(0.3f, fram);
        }
        fram.clear();

        Array<TextureRegion> fra = new Array<TextureRegion>();
        for (int i = 0; i < 3; i++) {
            fra.add(new TextureRegion(texture, (3*32)+8,0,32,32));
            playerafk = new Animation<TextureRegion>(0.3f, fra);
        }
        fra.clear();

        Array<TextureRegion> fr = new Array<TextureRegion>();
        for (int i = 0; i < 3; i++) {
            fr.add(new TextureRegion(hit, (i*178),0,178 ,166));
            playerhited = new Animation<TextureRegion>(0.3f, fr);
        }
        fr.clear();


    }


private void moveplayer(){
    time=time+1;

    if (win||lose){body.setActive(false);}
    if (!win&&!lose){

        if (body.getLinearVelocity().y==0||onground){
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                body.setLinearVelocity(body.getLinearVelocity().x,4);

            }}
            if ((Gdx.input.isKeyPressed(Input.Keys.LEFT) && body.getLinearVelocity().x > -2)) {
                body.applyLinearImpulse(new Vector2(-0.1f, 0f), body.getWorldCenter(), true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && body.getLinearVelocity().x < 2) {
                body.applyLinearImpulse(new Vector2(0.1f, 0f), body.getWorldCenter(), true);
            }

}}
private State whatsdoing(){
    if (lose){setSize(0.35f,0.25f);return State.LOSING;}
    if (hited){ setSize(0.25f,0.25f);return State.hited;}
    if (win){
        setSize(0.25f,0.35f);
        return State.Winning;
    }
//    if (heisafk){return State.AFK;}
    else if (body.getLinearVelocity().x==0&&body.getLinearVelocity().y==0&&onground){
//        afk=true;
        setSize(.25f,.3f);
        return State.STANDING;}


    else if ((body.getLinearVelocity().y>0||body.getLinearVelocity().y<0)&&!onground){
       setSize(0.3f,0.3f);
        return State.JUMPING;}
    else if (body.getLinearVelocity().x>0||body.getLinearVelocity().x<0){
        setSize(0.25f,0.25f);
        return State.RUNNING;}

    else return State.STANDING;
}


private TextureRegion state(){
    switch (whatsdoing()){
        case STANDING: playeranimation=new TextureRegion(texture,0,92,80,95);
            break;
        case RUNNING:playeranimation=playerrunning.getKeyFrame(time/13,true);
            break;
        case JUMPING:playeranimation=playerjumping.getKeyFrame(time/5,true);
            break;
        case Winning:playeranimation=new TextureRegion(texture1,0,0,texture1.getWidth(),texture1.getHeight());
            break;
        case LOSING:playeranimation=playerlosing.getKeyFrame(time/5,true);
            break;
        case AFK:playeranimation=playerafk.getKeyFrame(time/5,true);
            break;
        case hited:playeranimation=playerhited.getKeyFrame(time/5,true);
            break;
        default: playeranimation=new TextureRegion(texture,459,109,32,40);
    }

    return playeranimation;

}

    public void render(){

//        if (!afk){afktime=0;}
//        if (afk){afktime=afktime+1;
//        if (afktime>10){
//            heisafk=true;
//        }else if(afktime<0){heisafk=false;}}
        time=time+1;
        whatsdoing();
        if (hited){hittime=hittime+1;
        if (hittime==70){hited=false;hittime=0;}}
        setRegion(state());
        update();
        moveplayer();
        if (lose){
            screens.playing=false;
            x=x+1;
            if (x>150){
                screens.lose(game);
                dispose();
            }
        }
        if (win){
            screens.playing=false;
            x=x+1;
            if (x>100){
                screens.win(game);
             dispose();
            }
        }
if (pre.getInteger("Score")>=100){life=life+1;pre.putInteger("Score",pre.getInteger("Score")-100).flush();
    if (pre.getInteger("sound")==0){
        game.manager().get("sounds/smb_powerup.wav",Sound.class).play();}

}
    }
    public void lose(Fixture fa, Fixture fb, MyGdxGame gome){
        if (life!=1){
            if (!hited){
                hited=true;
                life=life-1;hittime=0;
                body.setLinearVelocity(new Vector2(f,3));
            }}
        else if (life==1){
            if (!hited){
                lose=true;
                hited=true;
            life=life-1;} if (pre.getInteger("music")==0){
                music.play();}}


    }
    public void win(MyGdxGame game){
        pre.putBoolean(screens.level+"win",true).flush();
        pre.flush();
        win=true;
    }
public void setOnground(){
    onground=false;
}
public void onground(){
    onground=true;
}
public void dispose(){
    music.stop();music.dispose();

}
public void setSoundjump(){
    if (pre.getInteger("sound")==0){
game.manager().get("sounds/Mario_Jumping.wav",Sound.class).play();}

}
public void side(){f=2;}
public void side2(){f=-2;}
}












