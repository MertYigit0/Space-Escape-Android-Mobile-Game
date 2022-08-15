package com.merty.spaceescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import org.graalvm.compiler.lir.amd64.AMD64BinaryConsumer;

public class menu extends MyGame implements Screen {
    SpaceEscape spaceEscape;

    Texture quitButton , playButton  , ScoresButton ,creditsButton , gameMenuBG ;
    Sound clickSound;


    private Music menuMusic;

    float x = Gdx.graphics.getWidth()/2-200;

    public menu(SpaceEscape spaceEscape) {
        this.spaceEscape = spaceEscape;
        playButton = new Texture("StartGame.png");
        quitButton = new Texture("QuitGame.png");
        creditsButton = new Texture("Credits.png");
          ScoresButton = new Texture("HighScores.png");
        gameMenuBG = new Texture("GameMenu.jpg");

        clickSound = Gdx.audio.newSound(Gdx.files.internal("Click.wav"));
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("menuMusic.wav"));

       menuMusic.setVolume(0.5f);
       menuMusic.setLooping(true);
        menuMusic.play();



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        spaceEscape.batch.begin();


        //menu buttons
        spaceEscape.batch.draw(gameMenuBG,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        spaceEscape.batch.draw(playButton,x,Gdx.graphics.getHeight()/2+Gdx.graphics.getHeight()/5,450,150);
        spaceEscape.batch.draw(creditsButton,x,Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/5,450,150);
        spaceEscape.batch.draw(ScoresButton,x,Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/5+Gdx.graphics.getHeight()/5,450,150);
        spaceEscape.batch.draw(quitButton,x,Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/5-Gdx.graphics.getHeight()/5,450,150);


        //start button input
        if (Gdx.input.getX()< x + 450  && Gdx.input.getX() > x
                && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight()/2+Gdx.graphics.getHeight()/5 + 150
                && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight()/2+Gdx.graphics.getHeight()/5 ){
            if (Gdx.input.justTouched()){
                spaceEscape.setScreen(new MyGame(spaceEscape));
                menuMusic.stop();
                long id = clickSound.play(3.6f);
                clickSound.setPitch(id , 2);
                clickSound.setLooping(id,false);

            }
        }
      //credits button input
        if (Gdx.input.getX()< x + 450  && Gdx.input.getX() > x
                && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/5+ 150
                && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/5 ){
            if (Gdx.input.justTouched()){
               spaceEscape.setScreen(new Credits(spaceEscape));
                long id = clickSound.play(3.6f);
                clickSound.setPitch(id , 2);
                clickSound.setLooping(id,false);

            }
        }

        //highScore button input
        if (Gdx.input.getX()< x + 450  && Gdx.input.getX() > x
                && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/5+Gdx.graphics.getHeight()/5+ 150
                && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/5+Gdx.graphics.getHeight()/5 ){
            if (Gdx.input.justTouched()){
                spaceEscape.setScreen(new HighScore(spaceEscape,getScore()));
                long id = clickSound.play(3.6f);
                clickSound.setPitch(id , 2);
                clickSound.setLooping(id,false);
            }
        }

        //exit input
        if (Gdx.input.getX()< x + 450  && Gdx.input.getX() > x
                && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/5-Gdx.graphics.getHeight()/5+ 150
                && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/5-Gdx.graphics.getHeight()/5 ){
            if (Gdx.input.justTouched()){
              Gdx.app.exit();
                long id = clickSound.play(3.6f);
                clickSound.setPitch(id , 2);
                clickSound.setLooping(id,false);
            }
        }




        spaceEscape.batch.end();

    }

    @Override
    public void resize(int width, int height) {

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

    }



}
