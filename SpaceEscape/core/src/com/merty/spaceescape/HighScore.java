package com.merty.spaceescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import java.util.ArrayList;
import java.util.Comparator;

public class HighScore extends MyGame implements Screen {
    SpaceEscape spaceEscape;
    SpriteBatch batch;
    int score , highscore;
    FreeTypeFontGenerator generator ;
    BitmapFont font , font1;
    Sound clickSound;
    Texture gameMenuBG  , airship , boom , options,back;

    public HighScore( SpaceEscape spaceEscape , int score) {
        super();
        clickSound = Gdx.audio.newSound(Gdx.files.internal("Click.wav"));

        generator = new FreeTypeFontGenerator(Gdx.files.internal("robotobold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        back = new Texture("Home_icon.png");

        parameter.characters = "0123456789 :  ! a-b-c-d-e-f-g-h-i-j-k-l-m-n-o-p-q-r-s-t-u-v-w-x-y-z A-B-C-D-E-F-G-H-I-J-K-L-M-N-O-P-Q-R-S-T-U-V-W-X-Y-Z  + .  - / *  + =";
        parameter.size=100;
        font1 = generator.generateFont(parameter);

        parameter1.characters = "0123456789 :  ! a-b-c-d-e-f-g-h-i-j-k-l-m-n-o-p-q-r-s-t-u-v-w-x-y-z A-B-C-D-E-F-G-H-I-J-K-L-M-N-O-P-Q-R-S-T-U-V-W-X-Y-Z  + .  - / *  + =";
        parameter1.size=100;
        font = generator.generateFont(parameter1);



        this.score = score;
        this.spaceEscape = spaceEscape;

        //Get highscore from save file
        Preferences preferences = Gdx.app.getPreferences("SpaceEscape");
        this.highscore = preferences.getInteger("highscore",0);

        if(score>highscore){
           // highscore = score;

            preferences.putInteger("highscore", score);
            preferences.flush();
        }

        batch = new SpriteBatch();
        gameMenuBG = new Texture("space.png");
        boom = new Texture("exload01_1.png");
        airship = new Texture("airship.png");
       options  = new Texture("Relpy_icon.png");

    }

    @Override
    public void show() {

        this.spaceEscape = spaceEscape;
    }

    @Override
    public void render(float delta) {
        batch.begin();



        batch.draw(gameMenuBG,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


        batch.draw(options,Gdx.graphics.getWidth()/2f-Gdx.graphics.getWidth()/10f,Gdx.graphics.getHeight()/8f,Gdx.graphics.getWidth()/10f,Gdx.graphics.getWidth()/10f);

        font.setColor(Color.BLACK);
        font.draw(batch,"Score " + score  ,Gdx.graphics.getWidth()/2f-Gdx.graphics.getWidth()/4f,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/4f);

        font1.setColor(Color.BLACK);
        font1.draw(batch,"HighScore " + highscore  ,Gdx.graphics.getWidth()/2f-Gdx.graphics.getWidth()/4f,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/2f);



        batch.draw(airship,airShipX+200,airShipY,Gdx.graphics.getWidth()/12f,Gdx.graphics.getHeight()/10f);
        batch.draw(boom,airShipX+200,airShipY,Gdx.graphics.getWidth()/12f*2,Gdx.graphics.getHeight()/10f*2);

        if (Gdx.input.getX()< Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/10+Gdx.graphics.getWidth()/10
        &&  Gdx.input.getX() > Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/10
           && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight()/8+Gdx.graphics.getWidth()/10
                && Gdx.graphics.getHeight() - Gdx.input.getY() >Gdx.graphics.getHeight()/8){
            if (Gdx.input.justTouched()){
                spaceEscape.setScreen(new MyGame(spaceEscape));



                long id = clickSound.play(3.6f);
                clickSound.setPitch(id , 2);
                clickSound.setLooping(id,false);

            }
        }

        batch.draw(back,Gdx.graphics.getWidth()/2f+Gdx.graphics.getWidth()/3f+Gdx.graphics.getWidth()/18f,Gdx.graphics.getHeight()/6f,Gdx.graphics.getWidth()/10f,Gdx.graphics.getWidth()/10);

        if (Gdx.input.getX()< Gdx.graphics.getWidth()/2+Gdx.graphics.getWidth()/3 +Gdx.graphics.getWidth()/18 +Gdx.graphics.getWidth()/10
                && Gdx.input.getX() > Gdx.graphics.getWidth()/2+Gdx.graphics.getWidth()/3+Gdx.graphics.getWidth()/18
                && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight()/6+ Gdx.graphics.getWidth()/10
                && Gdx.graphics.getHeight() - Gdx.input.getY() >Gdx.graphics.getHeight()/6 ){
            if (Gdx.input.justTouched()){
                spaceEscape.setScreen(new menu(spaceEscape));


                long id = clickSound.play(3.6f);
                clickSound.setPitch(id , 2);
                clickSound.setLooping(id,false);

            }
        }






        batch.end();
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
