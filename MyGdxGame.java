package com.mario.GrinningGameMoroi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mario.GrinningGameMoroi.screen.Start;

public class MyGdxGame extends Game {
	public static SpriteBatch batch;
	private com.mario.GrinningGameMoroi.Score.Coinbox coinbox;
	private AssetManager manager;
//	public Music music;
	public com.mario.GrinningGameMoroi.screen.Adhandler adhandler;
	boolean taggole;
	private int time;
	public MyGdxGame(com.mario.GrinningGameMoroi.screen.Adhandler adhandler){
		this.adhandler=adhandler;
	}
	@Override
	public void create () {
		coinbox=new com.mario.GrinningGameMoroi.Score.Coinbox(null,null);
		batch = new SpriteBatch();
		manager=new AssetManager();
		manager.load("sounds/mario.ogg",Music.class);
		manager.load("sounds/smb_stomp.wav", Sound.class);
		manager.load("sounds/smb_breakblock.wav",Sound.class);
		manager.load("sounds/smb_bump.wav",Sound.class);
		manager.load("sounds/smb_coin.wav",Sound.class);
		manager.load("sounds/smb_gameover.wav",Sound.class);
		manager.load("sounds/smb_jump-small.wav",Sound.class);
		manager.load("sounds/smb_kick.wav",Sound.class);
		manager.load("sounds/smb_pause.wav",Sound.class);
		manager.load("sounds/smw_coin.wav",Sound.class);
		manager.load("sounds/smb_stage_clear.wav",Sound.class);
		manager.load("sounds/Mario_Jumping.wav",Sound.class);
		manager.load("sounds/smb_mariodie.wav",Sound.class);
		manager.load("sounds/smb_powerup.wav",Sound.class);
		manager.load("sounds/smb_powerup_appears.wav",Sound.class);
		manager.load("karn3.png",Texture.class);
		manager.load("home.png",Texture.class);
//		manager.load("hero.png",Texture.class);
		manager.load("gameover.png",Texture.class);
		manager.load("all.png",Texture.class);
		manager.load("coin.png",Texture.class);
		manager.load("move.png",Texture.class);
		manager.load("buttons/arrow1.png",Texture.class);
		manager.load("buttons/arrows.png",Texture.class);
		manager.load("buttons/arrowup.png",Texture.class);
		manager.load("buttons/background.png",Texture.class);
		manager.load("buttons/Black.jpg",Texture.class);
		manager.load("buttons/button.png",Texture.class);
		manager.load("buttons/button_home.png",Texture.class);
		manager.load("buttons/button_restart.png",Texture.class);
		manager.load("buttons/button_resume.png",Texture.class);
		manager.load("buttons/mario.png",Texture.class);
		manager.load("buttons/pause.png",Texture.class);
		manager.load("buttons/settings.png",Texture.class);
		manager.load("buttons/heart.png",Texture.class);
		manager.load("buttons/heart1.png",Texture.class);

		manager.load("buttons/icon.png",Texture.class);
		manager.load("buttons/arrow.png",Texture.class);
		manager.load("buttons/icon2.png",Texture.class);
		manager.load("buttons/level1.png",Texture.class);
//		manager.load("buttons/baby.png",Texture.class);
		manager.load("buttons/level2.png",Texture.class);
		manager.load("rate.png",Texture.class);
		manager.load("buttons/level3.png",Texture.class);
		manager.load("buttons/level4.png",Texture.class);
		manager.load("buttons/level5.png",Texture.class);
		manager.load("selectlevel.png",Texture.class);
		manager.load("buttons/level6.png",Texture.class);
		manager.load("buttons/level7.png",Texture.class);
		manager.load("buttons/level8.png",Texture.class);
		manager.load("buttons/level9.png",Texture.class);
		manager.load("buttons/level10.png",Texture.class);
		manager.load("buttons/level11.png",Texture.class);
		manager.load("buttons/level12.png",Texture.class);
		manager.load("buttons/level13.png",Texture.class);
		manager.load("buttons/level14.png",Texture.class);
		manager.load("buttons/level15.png",Texture.class);
		manager.load("buttons/level16.png",Texture.class);
		manager.load("buttons/level17.png",Texture.class);
		manager.load("buttons/level18.png",Texture.class);
		manager.load("buttons/level19.png",Texture.class);
		manager.load("buttons/level20.png",Texture.class);
		manager.load("buttons/lock.png",Texture.class);
		manager.load("buttons/music.png",Texture.class);
		manager.load("buttons/nextbutton.png",Texture.class);
		manager.load("buttons/nomusic.png",Texture.class);
		manager.load("buttons/pad.png",Texture.class);
		manager.load("buttons/restartbutton.png",Texture.class);
		manager.load("buttons/selected.png",Texture.class);
		manager.load("buttons/settings.png",Texture.class);
		manager.load("buttons/sound.png",Texture.class);
		manager.load("buttons/start.png",Texture.class);
		manager.load("buttons/touchbackground.png",Texture.class);
		manager.load("buttons/touchknob.png",Texture.class);
		manager.load("cloud.png",Texture.class);
		manager.load("buttons/winscreen.png",Texture.class);
		manager.load("startgame.png",Texture.class);
		manager.load("button_continue.png",Texture.class);
		manager.load("gomba.png",Texture.class);
		manager.load("morelevels.png",Texture.class);
		manager.load("gom.png",Texture.class);
		manager.load("float.png",Texture.class);
		manager.load("donate.png",Texture.class);
		manager.load("win.png",Texture.class);
		manager.load("hited.png",Texture.class);
		manager.load("die.png",Texture.class);
		manager.load("buttons/button.png",Texture.class);
		manager.load("buttons/cloud.png",Texture.class);
		manager.load("test.png",Texture.class);
		manager.load("test1.png",Texture.class);
		manager.load("test2.png",Texture.class);
		manager.load("test3.png",Texture.class);
		manager.load("lock.png",Texture.class);




		manager.finishLoading();
		manager.get("sounds/mario.ogg",Music.class);
		Gdx.input.setCatchBackKey(true);
		if (manager.update()){
		setScreen(new Start(this));}

		load();

	}

	@Override
	public void render () {


		Gdx.gl.glClearColor(107/255f, 140/255f, 255/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	super.render();
		time++;



	}
	
	@Override
	public void dispose () {
		batch.dispose();
		screen.dispose();
		coinbox.dispose();
		manager.clear();
		manager.dispose();
	}

public AssetManager manager(){
	return manager;
}


public void setad(boolean visble){

//	adhandler.showads(visble);
}

	public void fullad()
	{
//		adhandler.showfullad();time=0;
	}
public void load(){

//	adhandler.load();
 }
}