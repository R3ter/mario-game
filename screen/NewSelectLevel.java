package com.mario.GrinningGameMoroi.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mario.GrinningGameMoroi.MyGdxGame;

/**
 * Created by waleed bas on 05/14/18.
 */

public class NewSelectLevel implements Screen {
    private Preferences pre;
    private Stage stage;
    private Screens screens;
    private Viewport viewport;
    private MyGdxGame game;
    private Array<Label> labels;
    private  boolean b;
    private boolean relese;
    private Table Back;
    private int f,level;
    private float z;
    private Texture texture;
    private float scroll;
    private Image img1;

    public Array<Image> getButtons() {
        return buttons;
    }

    private Array<Image> lock,buttons,buttons1,buttons2,buttons3;
    private Image button1,button2,button3;

    public NewSelectLevel(MyGdxGame game) {
        this.game=game;
        viewport=new StretchViewport(400,900);
    }

    @Override
    public void show() {
        texture=game.manager().get("buttons/background.png");

        pre = Gdx.app.getPreferences("My Preferences");
        stage = new Stage();
        screens = new Screens(game);
        screens.show();

        stage = new Stage(viewport);

        Gdx.input.setInputProcessor(stage);

        BitmapFont font = new BitmapFont(Gdx.files.internal("skin/font.fnt"));

        Image back = new Image((Texture) game.manager().get("buttons/button.png"));
        buttons = new Array<Image>();
        buttons1 = new Array<Image>();
        buttons2 = new Array<Image>();
        buttons3 = new Array<Image>();
        lock = new Array<Image>();


         Back =new Table();
        Back.setPosition(30,800);
        Back.add(back).size(60,90);
        stage.addActor(Back);
        back.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (b){
                    game.setScreen(new Start(game));
                    dispose();}
            }
        });


        for (int i = 1; i < 22; i++) {
            img1 = new Image((Texture) game.manager().get("test.png"));
            button1 = new Image((Texture) (game.manager().get("test1.png")));
            button2 = new Image((Texture) (game.manager().get("test2.png")));
            button3 = new Image((Texture) (game.manager().get("test3.png")));
            Image lockimg = new Image((Texture) (game.manager().get("lock.png")));

            buttons.add(img1);
            buttons1.add(button1);
            buttons2.add(button2);
            buttons3.add(button3);
            lock.add(lockimg);
        }


        labels = new Array<Label>();
        for (int i = 0; i < 20; i++) {

            Label label = (new Label(i + "", new Label.LabelStyle(new BitmapFont(Gdx.files.internal("skin/font.fnt")), Color.WHITE)));
            labels.add(label);
            labels.get(i).setFontScale(1, 2.5f);
        }




        Table table1 = new Table();
        table1.setPosition(0, 800);
        table1.setSize(4, 14);
        table1.top().left();
        table1.add();
        table1.row();
        table1.add().size(50, 50).height(100);
        table1.row();
        table1.add().height(50);
        table1.row();
        level = 0;
        for (int f = 1; f < 21; f++) {

            if (pre.getBoolean((f-1)+"win")||f==1){

                if (pre.getInteger(f + "stars") == 0) {
                    (table1.add(buttons.get(f))).height(300).width(80);
                }
           else if (pre.getInteger(f + "stars") > 2) {
                (table1.add(buttons3.get(f))).height(300).width(80);
            } else if (pre.getInteger(f + "stars") > 1) {
                (table1.add(buttons2.get(f))).height(300).width(80);
            } else if (pre.getInteger(f + "stars") == 1) {
                (table1.add(buttons1.get(f))).height(300).width(80);
            }}
            else if(!pre.getBoolean((1-f)+"win")) {(table1.add(lock.get(f))).height(300).width(80);}

            table1.add().width(5);
        }


//add listener

        (buttons1.get(1)).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {

                        screens.level = 1;
                        game.setScreen(new Screens(game));
                        dispose();

                }
            }
        });
        (buttons3.get(1)).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {

                    screens.level = 1;
                    game.setScreen(new Screens(game));
                    dispose();

                }
            }
        }); (buttons2.get(1)).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {

                    screens.level = 1;
                    game.setScreen(new Screens(game));
                    dispose();

                }
            }
        });
        (buttons1.get(2)).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(1 + "win")) {
                        screens.level = 2;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }
            }
        });



        buttons1.get(3).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(2 + "win")) {
                        screens.level = 3;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }
            }
        });


        buttons1.get(4).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(3 + "win")) {
                        screens.level = 4;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(5).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(4 + "win")) {
                        screens.level = 5;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(6).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(5 + "win")) {
                        screens.level = 6;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(7).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(6 + "win")) {
                        screens.level = 7;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons1.get(8).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(7 + "win")) {
                        screens.level = 8;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(9).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(8 + "win")) {
                        screens.level = 9;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons1.get(10).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(9 + "win")) {
                        screens.level = 10;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(11).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(10 + "win")) {
                        screens.level = 11;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons1.get(12).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(11 + "win")) {
                        screens.level = 12;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(13).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(12 + "win")) {
                        screens.level = 13;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons1.get(14).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(13 + "win")) {
                        screens.level = 14;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(15).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(15 + "win")) {
                        screens.level = 16;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(16).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(15 + "win")) {
                        screens.level = 16;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(17).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(16 + "win")) {
                        screens.level = 17;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(18).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(17 + "win")) {
                        screens.level = 18;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons1.get(19).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(18 + "win")) {
                        screens.level = 19;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(20).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(19 + "win")) {
                        screens.level = 20;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });

        (buttons1.get(2)).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(1 + "win")) {
                        screens.level = 2;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }
            }
        });
        buttons1.get(3).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(2 + "win")) {
                        screens.level = 3;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }
            }
        });
        buttons1.get(4).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(3 + "win")) {
                        screens.level = 4;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(5).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(4 + "win")) {
                        screens.level = 5;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(6).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(5 + "win")) {
                        screens.level = 6;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(7).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(6 + "win")) {
                        screens.level = 7;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(8).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(7 + "win")) {
                        screens.level = 8;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(9).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(8 + "win")) {
                        screens.level = 9;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(10).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(9 + "win")) {
                        screens.level = 10;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(11).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(10 + "win")) {
                        screens.level = 11;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(12).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(11 + "win")) {
                        screens.level = 12;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(13).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(12 + "win")) {
                        screens.level = 13;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(14).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(13 + "win")) {
                        screens.level = 14;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(15).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(15 + "win")) {
                        screens.level = 16;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(16).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(15 + "win")) {
                        screens.level = 16;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(17).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(16 + "win")) {
                        screens.level = 17;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(18).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(17 + "win")) {
                        screens.level = 18;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(19).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(18 + "win")) {
                        screens.level = 19;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons1.get(20).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(19 + "win")) {
                        screens.level = 20;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        (buttons2.get(2)).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(1 + "win")) {
                        screens.level = 2;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }
            }
        });



        buttons2.get(3).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(2 + "win")) {
                        screens.level = 3;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }
            }
        });
        buttons2.get(4).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(3 + "win")) {
                        screens.level = 4;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons2.get(5).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(4 + "win")) {
                        screens.level = 5;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons2.get(6).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(5 + "win")) {
                        screens.level = 6;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons2.get(7).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(6 + "win")) {
                        screens.level = 7;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons2.get(8).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(7 + "win")) {
                        screens.level = 8;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons2.get(9).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(8 + "win")) {
                        screens.level = 9;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons2.get(10).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(9 + "win")) {
                        screens.level = 10;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons2.get(11).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(10 + "win")) {
                        screens.level = 11;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons2.get(12).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(11 + "win")) {
                        screens.level = 12;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons2.get(13).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(12 + "win")) {
                        screens.level = 13;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons2.get(14).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(13 + "win")) {
                        screens.level = 14;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons2.get(15).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(15 + "win")) {
                        screens.level = 16;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons2.get(16).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(15 + "win")) {
                        screens.level = 16;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons2.get(17).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(16 + "win")) {
                        screens.level = 17;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons2.get(18).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(17 + "win")) {
                        screens.level = 18;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons2.get(19).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(18 + "win")) {
                        screens.level = 19;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons2.get(20).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(19 + "win")) {
                        screens.level = 20;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });

        (buttons3.get(2)).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(1 + "win")) {
                        screens.level = 2;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }
            }
        });


        (buttons3.get(1)).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {

                        screens.level = 1;
                        game.setScreen(new Screens(game));
                        dispose();

                }
            }
        });


        game.setad(true);
        game.fullad();

        buttons3.get(3).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(2 + "win")) {
                        screens.level = 3;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }
            }
        });


        buttons3.get(4).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(3 + "win")) {
                        screens.level = 4;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons3.get(5).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(4 + "win")) {
                        screens.level = 5;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });       buttons3.get(6).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(5 + "win")) {
                        screens.level = 6;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons3.get(7).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(6 + "win")) {
                        screens.level = 7;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons3.get(8).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(7 + "win")) {
                        screens.level = 8;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons3.get(9).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(8 + "win")) {
                        screens.level = 9;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons3.get(10).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(9 + "win")) {
                        screens.level = 10;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons3.get(11).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(10 + "win")) {
                        screens.level = 11;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons3.get(12).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(11 + "win")) {
                        screens.level = 12;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons3.get(13).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(12 + "win")) {
                        screens.level = 13;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons3.get(14).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(13 + "win")) {
                        screens.level = 14;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons3.get(15).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(15 + "win")) {
                        screens.level = 16;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons3.get(16).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(15 + "win")) {
                        screens.level = 16;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons3.get(17).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(16 + "win")) {
                        screens.level = 17;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons3.get(18).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(17 + "win")) {
                        screens.level = 18;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons3.get(19).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(18 + "win")) {
                        screens.level = 19;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons3.get(20).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(19 + "win")) {
                        screens.level = 20;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });


        (buttons.get(1)).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                        screens.level = 1;
                        game.setScreen(new Screens(game));
                        dispose();

                }
            }        });

        (buttons.get(2)).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(1 + "win")) {
                        screens.level = 2;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }
            }
        });



        buttons.get(3).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(2 + "win")) {
                        screens.level = 3;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }
            }
        });


        buttons.get(4).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(3 + "win")) {
                        screens.level = 4;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons.get(5).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(4 + "win")) {
                        screens.level = 5;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });       buttons.get(6).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(5 + "win")) {
                        screens.level = 6;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons.get(7).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(6 + "win")) {
                        screens.level = 7;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons.get(8).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(7 + "win")) {
                        screens.level = 8;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons.get(9).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(8 + "win")) {
                        screens.level = 9;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons.get(10).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(9 + "win")) {
                        screens.level = 10;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons.get(11).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(10 + "win")) {
                        screens.level = 11;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons.get(12).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(11 + "win")) {
                        screens.level = 12;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons.get(13).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(12 + "win")) {
                        screens.level = 13;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons.get(14).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(13 + "win")) {
                        screens.level = 14;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons.get(15).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(15 + "win")) {
                        screens.level = 16;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons.get(16).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(15 + "win")) {
                        screens.level = 16;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons.get(17).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(16 + "win")) {
                        screens.level = 17;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons.get(18).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(17 + "win")) {
                        screens.level = 18;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        }); buttons.get(19).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(18 + "win")) {
                        screens.level = 19;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });
        buttons.get(20).addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (b) {
                    if (pre.getBoolean(19 + "win")) {
                        screens.level = 20;
                        game.setScreen(new Screens(game));
                        dispose();
                    }
                }

            }

        });

//        for ( f=0;f<21;f++){
//            buttons2.get(f).addListener(new InputListener(){
//                @Override
//                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                    buttons2.get(f).setColor(Color.BLACK);
//                    return true;
//                }
//
//                @Override
//                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                    buttons2.get(f).setWidth(70);
//                }
//            });
//        }
        table1.add().width(10);

        Table table=new Table();

        table.setPosition(0,800);
        table.setSize(4,14);
        table.top().left();
        table.add().height(290);
        table.row();
        table.add().width(1);


        table.row();
        table.add().height(70);
        table.row();


        Table numbers=new Table();
        numbers.setPosition(0,730);
        numbers.setSize(4,30);
        numbers.top().left();
        numbers.add().height(290);
        numbers.add().width(25);
        for (int i=1;i<20;i++){
            if (pre.getBoolean((i-1)+"win")||i==1){
                numbers.add(labels.get(i)).size(20,50).height(80);
            numbers.add().width(65);
        labels.get(i).setTouchable(Touchable.disabled);}}


//        Table bar=new Table();
//
//        ScrollPane pane=new ScrollPane(numbers);
//        pane.setWidth(20);
//        bar.setPosition(100,730);
//        bar.setSize(4,30);
//        bar.add(pane);

       ScrollPane scrollPane = new ScrollPane(table);
        scrollPane.setBounds(0, 0, 800, 100);
        scrollPane.setSmoothScrolling(false);
        scrollPane.setPosition(800/ 2 - scrollPane.getWidth() / 4,
                800 / 2 - scrollPane.getHeight() / 4);
        scrollPane.setTransform(true);
        scrollPane.setScale(0.5f);
        stage.addActor(scrollPane);


        Table container = new Table();
        ScrollPane pane = new ScrollPane(table);
        container.setPosition(50,50);
        container.add(pane).size(20);
        container.row();


        stage.addActor(table1);
        stage.addActor(numbers);
        stage.addActor(table);
        stage.addActor(container);
        table.setTouchable(Touchable.disabled);

        stage.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                relese=true;
                b=true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                relese=false;
                b=true;
                scroll=x;
                z=x;
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if (x-z<-3||x-z>3){
                    b=false;}
                stage.getRoot().moveBy(x-scroll,0);

            }});
//       TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
//        Skin skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);

        scrollPane = new ScrollPane(table);
        scrollPane.setBounds(0, 0, 200, 300 + 100);
        scrollPane.setSmoothScrolling(false);
        scrollPane.setPosition(200 / 2 - scrollPane.getWidth() / 4,
                300 / 2 - scrollPane.getHeight() / 4);
        scrollPane.setTransform(true);
        scrollPane.setScale(0.5f);
        stage.addActor(scrollPane);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)){game.setScreen(new Start(game));}

        if (relese) {
            if (stage.getRoot().getX() > 5) {
                stage.getRoot().moveBy(-10, 0f);
            }
            if (stage.getRoot().getX() < -1298.5) {
                stage.getRoot().moveBy(10, 0f);
            }
        }

        Back.setPosition((stage.getRoot().getX()*-1)+30,Back.getY());
//            if (Gdx.input.isKeyJustPressed(Input.Keys.R)){System.out.print(stage.getRoot().getX());}
//            if (stage.getRoot().getX()>=500){stage.getRoot().moveBy(-50f,0);}
            stage.getBatch().begin();
            stage.getBatch().draw(texture,0,0,400,900);
            stage.getBatch().end();

stage.draw();
    }

    @Override
    public void resize(int width, int height) {viewport.update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        buttons.clear();
        button1.clear();
        button2.clear();
        button3.clear();
        labels.clear();
//        stage.dispose();

    }

}
