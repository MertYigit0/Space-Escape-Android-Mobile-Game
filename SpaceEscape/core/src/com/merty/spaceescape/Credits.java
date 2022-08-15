package com.merty.spaceescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;


public class Credits implements Screen {
    SpriteBatch batch;
    SpaceEscape spaceEscape;
    Texture  creditsbg , back ;
    FreeTypeFontGenerator generator ;
    BitmapFont font , font1;
    Sound clickSound;
    public Credits(SpaceEscape spaceEscape)  {
        this.spaceEscape = spaceEscape;
        creditsbg = new Texture("Creditssbg.jpg");
        back = new Texture("Home_icon.png");
        batch = new SpriteBatch();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("robotobold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();


        parameter.characters = "0123456789 :  ! a-b-c-d-e-f-g-h-i-j-k-l-m-n-o-p-q-r-s-t-u-v-w-x-y-z A-B-C-D-E-F-G-H-I-J-K-L-M-N-O-P-Q-R-S-T-U-V-W-X-Y-Z  + .  - / *  + =";
        parameter.size=70;
        font1 = generator.generateFont(parameter);

        parameter1.characters = "0123456789 :  ! a-b-c-d-e-f-g-h-i-j-k-l-m-n-o-p-q-r-s-t-u-v-w-x-y-z A-B-C-D-E-F-G-H-I-J-K-L-M-N-O-P-Q-R-S-T-U-V-W-X-Y-Z  + .  - / *  + =";
        parameter1.size=150;
        font = generator.generateFont(parameter1);
        clickSound = Gdx.audio.newSound(Gdx.files.internal("Click.wav"));

    }




    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
       batch.begin();

      batch.draw(creditsbg,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


        font.setColor(Color.ORANGE);
        font.draw(batch,"CREDITS "  ,Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/4);

        font1.setColor(Color.FIREBRICK);
        font1.draw(batch,"Game Developer: Mert Yigit"  ,Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/2);
        font1.setColor(Color.ORANGE);
        font1.draw(batch,"Icon Arts: bevouliin"  ,Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/6);
        font1.setColor(Color.ORANGE);
        font1.draw(batch,"Backgroungd: Aetherna"  ,Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/6 -Gdx.graphics.getHeight()/6);



        batch.draw(back,Gdx.graphics.getWidth()/2+Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/22,Gdx.graphics.getWidth()/10,Gdx.graphics.getWidth()/10);

        if (Gdx.input.getX()< Gdx.graphics.getWidth()/2+Gdx.graphics.getWidth()/3 + Gdx.graphics.getWidth()/10
                && Gdx.input.getX() > Gdx.graphics.getWidth()/2+Gdx.graphics.getWidth()/3
                && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight()/22+ Gdx.graphics.getWidth()/10
                && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight()/22 ){
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
        batch.dispose();
        font.dispose();
        generator.dispose();

    }
}
