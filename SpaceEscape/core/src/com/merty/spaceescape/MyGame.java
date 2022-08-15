package com.merty.spaceescape;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;


public class MyGame extends ApplicationAdapter implements Screen{
    SpriteBatch batch;
    Texture space , enemy1 ,enemy2,enemy3, airShip , laser ,spolode ,tapR ,tapL ,tapped,shoot,home;

    float airShipX = 0 ;
    float airShipY = 0;
    int gameState = 0;
    float velocity = 0;
    float gravity =  0.3f;
    float enemyVelocity = 5;
    int score , scoredEnemy, laserX, laserY;
    boolean laserbo;

    FreeTypeFontGenerator generator ;



    private  Sound laserSound , explosionSound ;
    private  Music gameMusic;

    Random random ;
    ShapeRenderer shapeRenderer;
    BitmapFont  font3 , font4;





    int numberOfEnemies = 20;

    float [] enemyX = new float[numberOfEnemies];
    float [] enemyOffSet1 = new  float[numberOfEnemies];
    float [] enemyOffSet2 = new  float[numberOfEnemies];
    float [] enemyOffSet3= new  float[numberOfEnemies];
    float distance = 0;

    Circle laserCircle , airShipCircle ;
    Circle[]  enemyCircle ,enemyCircle2,enemyCircle3 ;

    SpaceEscape spaceEscape;

    //constructor
    public MyGame(SpaceEscape spaceEscape) {

        this.spaceEscape = spaceEscape;
        batch = new SpriteBatch();

       laserSound= Gdx.audio.newSound(Gdx.files.internal("laserSound.wav"));
       explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.wav"));
       gameMusic = Gdx.audio.newMusic(Gdx.files.internal("gameMusic.wav"));

       gameMusic.setVolume(0.6f);
       gameMusic.setLooping(true);
       gameMusic.play();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("robotobold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter1.color = Color.RED;
        parameter1.characters = "0123456789 :  ! a-b-c-d-e-f-g-h-i-j-k-l-m-n-o-p-q-r-s-t-u-v-w-x-y-z A-B-C-D-E-F-G-H-I-J-K-L-M-N-O-P-Q-R-S-T-U-V-W-X-Y-Z  + .  - / *  + =";
        parameter1.size=120;

        parameter.size = 50;
        parameter.characters = "0123456789 :  ! a-b-c-d-e-f-g-h-i-j-k-l-m-n-o-p-q-r-s-t-u-v-w-x-y-z A-B-C-D-E-F-G-H-I-J-K-L-M-N-O-P-Q-R-S-T-U-V-W-X-Y-Z  + .  - / *  + =";
        font4 = generator.generateFont(parameter);
        font3 = generator.generateFont(parameter1);

        space= new Texture("space.png");
        enemy1= new Texture("enemy.png");
        enemy2= new Texture("enemy.png");
        enemy3= new Texture("enemy.png");
        airShip= new Texture("airship.png");
        laser = new Texture("red_laser.png");
        spolode = new Texture("exload01_1.png");
        tapR  = new Texture("tapRight.png");
        tapL  = new Texture("tapLeft.png");
        tapped  = new Texture("tapL.png");
        shoot = new Texture("shoott.png");
        home = new Texture("homeB.png");

        distance = Gdx.graphics.getWidth()/2f;
        random = new Random();

        laserX = Gdx.graphics.getWidth()/6 + 110;
        airShipX =Gdx.graphics.getWidth()/6f;
        airShipY = Gdx.graphics.getHeight()/3f;

        shapeRenderer = new ShapeRenderer();

        laserCircle = new Circle();
        airShipCircle =  new Circle();
        enemyCircle   =  new Circle[numberOfEnemies];
        enemyCircle2  =  new Circle[numberOfEnemies];
        enemyCircle3  =  new Circle[numberOfEnemies];






        for(int i = 0 ; i<numberOfEnemies; i++){

            enemyOffSet1[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
            enemyOffSet2[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
            enemyOffSet3[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);

            enemyX[i] = Gdx.graphics.getWidth() - enemy1.getWidth() / 2f + i * distance;

            enemyCircle[i]  = new Circle();
            enemyCircle2[i]  = new Circle();
            enemyCircle3[i]  = new Circle();

        }
    }


    public MyGame() {

    }

    public int getScore() {
        return score;
    }

    public void getLaserY(int a){

        laserY = a;
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        batch.begin();
        //background
        batch.draw(space,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        font4.setColor(Color.WHITE);
        font4.draw(batch,"Score: " + score ,70,140);

        if (gameState == 1) {



            if (enemyX[scoredEnemy] < airShipX ){
                score++;
                if(scoredEnemy< numberOfEnemies -1){
                    scoredEnemy++;
                }else{
                    scoredEnemy = 0;}}

            for(int i = 0 ; i<numberOfEnemies; i++){

                if(enemyX[i] < -Gdx.graphics.getWidth()/12f){
                    enemyX[i] = enemyX[i]+ numberOfEnemies * distance;

                    enemyOffSet1[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight());
                    enemyOffSet2[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight());
                    enemyOffSet3[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight());

                }else{
                    enemyX[i] = enemyX[i]- enemyVelocity*2;
                }
                //////////////////////////////////
                if (!(Intersector.overlaps(laserCircle,enemyCircle[i]))){
                    if (Gdx.input.justTouched()) {
                        if (Gdx.input.getX() > Gdx.graphics.getWidth() / 2) {
                            getLaserY((int) airShipY);
                        }}
                }
                if (!(Intersector.overlaps(laserCircle,enemyCircle2[i]))){
                    if (Gdx.input.justTouched()) {
                        if (Gdx.input.getX() > Gdx.graphics.getWidth() / 2) {
                            getLaserY((int) airShipY);
                        }}
                }
                if (!(Intersector.overlaps(laserCircle,enemyCircle3[i]))){
                    if (Gdx.input.justTouched()) {
                        if (Gdx.input.getX() > Gdx.graphics.getWidth() / 2) {
                            getLaserY((int) airShipY);
                        }}
                }
                ////////////////////////////

                //laser collision
                if ((Intersector.overlaps(laserCircle,enemyCircle[i]))){
                    enemyOffSet1[i] = -777777;
                    score = score +10;
                    laserCircle.set(-5000,-5000,Gdx.graphics.getWidth()/55f);
                }
                if ((Intersector.overlaps(laserCircle,enemyCircle2[i]))){
                    enemyOffSet2[i] =  -888888;
                    score = score +10;
                    laserCircle.set(-5000,-5000,Gdx.graphics.getWidth()/55f);
                }
                if ((Intersector.overlaps(laserCircle,enemyCircle3[i]))){
                    enemyOffSet3[i] = -999999;
                    score = score + 10;
                    laserCircle.set(-5000,-5000,Gdx.graphics.getWidth()/55f);
                }

                //overlap check
                if (   (Gdx.graphics.getHeight()/2f+enemyOffSet2[i] <  Gdx.graphics.getHeight()/2f+enemyOffSet1[i] +Gdx.graphics.getHeight()/10f) && (Gdx.graphics.getHeight()/2f+enemyOffSet2[i] >  Gdx.graphics.getHeight()/2f+enemyOffSet1[i] -Gdx.graphics.getHeight()/10f) ){
                    enemyOffSet2[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
                }
                if (   (Gdx.graphics.getHeight()/2f+enemyOffSet3[i] <  Gdx.graphics.getHeight()/2f+enemyOffSet1[i] +Gdx.graphics.getHeight()/10f) && (Gdx.graphics.getHeight()/2f+enemyOffSet3[i] >  Gdx.graphics.getHeight()/2f+enemyOffSet1[i] -Gdx.graphics.getHeight()/10f) ){
                    enemyOffSet3[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
                }
                if (   (Gdx.graphics.getHeight()/2f+enemyOffSet3[i] <  Gdx.graphics.getHeight()/2f+enemyOffSet2[i] +Gdx.graphics.getHeight()/10f) && (Gdx.graphics.getHeight()/2f+enemyOffSet3[i] >  Gdx.graphics.getHeight()/2f+enemyOffSet2[i] -Gdx.graphics.getHeight()/10f) ){
                    enemyOffSet3[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
                }

                //enemy draw
                batch.draw(enemy1,enemyX[i],Gdx.graphics.getHeight()/2f+enemyOffSet1[i], Gdx.graphics.getWidth()/12f,Gdx.graphics.getHeight()/10f);
                batch.draw(enemy2,enemyX[i],Gdx.graphics.getHeight()/2f+enemyOffSet2[i], Gdx.graphics.getWidth()/12f,Gdx.graphics.getHeight()/10f);
                batch.draw(enemy3,enemyX[i],Gdx.graphics.getHeight()/2f+enemyOffSet3[i], Gdx.graphics.getWidth()/12f,Gdx.graphics.getHeight()/10f);

                //create eneymy circle
                enemyCircle[i] = new Circle(enemyX[i] +Gdx.graphics.getWidth()/24f ,enemyOffSet1[i] + Gdx.graphics.getHeight()/2f+Gdx.graphics.getHeight()/20f ,Gdx.graphics.getHeight()/24f  );
                enemyCircle2[i] = new Circle(enemyX[i] +Gdx.graphics.getWidth()/24f ,enemyOffSet2[i] + Gdx.graphics.getHeight()/2f+Gdx.graphics.getHeight()/20f ,Gdx.graphics.getHeight()/24f  );
                enemyCircle3[i] = new Circle(enemyX[i] +Gdx.graphics.getWidth()/24f ,enemyOffSet3[i] + Gdx.graphics.getHeight()/2f +Gdx.graphics.getHeight()/20f,Gdx.graphics.getHeight()/24f  );

            }

            //if touched left side of screen
           if (Gdx.input.justTouched()) {
                if (Gdx.input.getX() < Gdx.graphics.getWidth() / 2) {
                    velocity = -10;
                }else if (Gdx.input.getX() > Gdx.graphics.getWidth() / 2){
                    laserbo = true;
                    if( laserX == Gdx.graphics.getWidth() / 6 + 110){
                        long id = laserSound.play(0.6f);
                        laserSound.setPitch(id , 2);
                        laserSound.setLooping(id,false);
                    }
                }




           }
/*
            //if touched right side of screen
            if (Gdx.input.justTouched()) {
                if (Gdx.input.getX() > Gdx.graphics.getWidth() / 2) {
                    laserbo = true;
                    if( laserX == Gdx.graphics.getWidth() / 6 + 110){
                        long id = laserSound.play(0.6f);
                        laserSound.setPitch(id , 2);
                        laserSound.setLooping(id,false);
                    }
                }}
*/
            if (laserbo ) {
                batch.draw(laser, laserX, laserY, Gdx.graphics.getWidth() / 36f, Gdx.graphics.getHeight() / 20f);
                laserCircle.set(laserX +40,laserY+Gdx.graphics.getHeight()/15f-50,Gdx.graphics.getWidth()/55f);
                laserX = laserX + 30;
            }

           //aırship fly
            if(airShipY > 0 ) {
                velocity = velocity + gravity;
                airShipY -= velocity;

                //if airship crash to ground
            }else{
                long id2 =explosionSound.play(0.3f);
                explosionSound.setPitch(id2 , 3);
                explosionSound.setLooping(id2,false);
                gameState = 2;





            }
            //if airship crash to ceiling
            if(Gdx.graphics.getHeight() < airShipY+100  ) {
              //  airShipY = airShipY -5;
                velocity = velocity + gravity*4;
                airShipY -= velocity+10;
            }
        }

        //game just open
        else if (gameState == 0){

            batch.draw(tapped,Gdx.graphics.getWidth()/60f,Gdx.graphics.getHeight()/2.3f,Gdx.graphics.getWidth()/6.5f,Gdx.graphics.getHeight()/4.5f);
            batch.draw(tapL,Gdx.graphics.getWidth()/6f,Gdx.graphics.getHeight()/2.3f,Gdx.graphics.getWidth()/8f,Gdx.graphics.getHeight()/6f);
            batch.draw(tapR,Gdx.graphics.getWidth()/1.5f,Gdx.graphics.getHeight()/2.3f,Gdx.graphics.getWidth()/8f,Gdx.graphics.getHeight()/6f);
            batch.draw(shoot,Gdx.graphics.getWidth()/1.2f,Gdx.graphics.getHeight()/2.3f,Gdx.graphics.getWidth()/7f,Gdx.graphics.getHeight()/5f);


           // font3.setColor(Color.RED);
           // font3.draw(batch," Fly  " ,Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/(1.55f));


            if (Gdx.input.justTouched()) {
                if (Gdx.input.getX() < Gdx.graphics.getWidth() / 2){
                    gameState = 1;
                }}

            //game finished
        }else if (gameState == 2 ){
           // spaceEscape.setScreen(new HighScore(spaceEscape,getScore()));

            new HighScore(spaceEscape , getScore() );

            //tap icons
            batch.draw(tapped,Gdx.graphics.getWidth()/60f,Gdx.graphics.getHeight()/2.3f,Gdx.graphics.getWidth()/6.5f,Gdx.graphics.getHeight()/4.5f);
            batch.draw(tapL,Gdx.graphics.getWidth()/6f,Gdx.graphics.getHeight()/2.3f,Gdx.graphics.getWidth()/8f,Gdx.graphics.getHeight()/6f);
            batch.draw(tapR,Gdx.graphics.getWidth()/1.5f,Gdx.graphics.getHeight()/2.3f,Gdx.graphics.getWidth()/8f,Gdx.graphics.getHeight()/6f);
            batch.draw(shoot,Gdx.graphics.getWidth()/1.2f,Gdx.graphics.getHeight()/2.3f,Gdx.graphics.getWidth()/7f,Gdx.graphics.getHeight()/5f);




            //home button
            batch.draw(home,Gdx.graphics.getWidth()/1.2f,Gdx.graphics.getHeight()/12.5f,Gdx.graphics.getWidth()/8f,Gdx.graphics.getHeight()/6f);
            if (Gdx.input.getX()< Gdx.graphics.getWidth()/1.2f+ Gdx.graphics.getWidth()/8f
                    &&  Gdx.input.getX() > Gdx.graphics.getWidth()/1.2f
                    && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight()/12.5f + Gdx.graphics.getHeight()/6f
                    && Gdx.graphics.getHeight() - Gdx.input.getY() >Gdx.graphics.getHeight()/12.5f){
                if (Gdx.input.justTouched()){
                    spaceEscape.setScreen(new menu(spaceEscape));

                }
            }




            if (Gdx.input.justTouched()){

                gameState = 1 ;
                airShipY = Gdx.graphics.getHeight()/3f;

                for(int i = 0 ; i<numberOfEnemies; i++){

                    enemyOffSet1[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
                    enemyOffSet2[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
                    enemyOffSet3[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);

                    enemyX[i] = Gdx.graphics.getWidth() - enemy1.getWidth() / 2f + i * distance;

                    enemyCircle[i]  = new Circle();
                    enemyCircle2[i]  = new Circle();
                    enemyCircle3[i]  = new Circle();

                    laserX = Gdx.graphics.getWidth()/6 + 110;
                    velocity = 0;
                    scoredEnemy = 0;
                    score = 0;
                }
            }
            //dispose();
        }


        batch.draw(airShip,airShipX,airShipY,Gdx.graphics.getWidth()/12f,Gdx.graphics.getHeight()/10f);


        if (airShipY<0){
            batch.draw(spolode,airShipX-50,airShipY,Gdx.graphics.getWidth()/12f*2,Gdx.graphics.getHeight()/10f*2);
        }


        batch.end();

        //airShipCircle.set(airShipX+Gdx.graphics.getWidth()/24,airShipY+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/24);
        airShipCircle.set(airShipX+Gdx.graphics.getWidth()/24f,airShipY+Gdx.graphics.getHeight()/20f,Gdx.graphics.getWidth()/32f);


        //shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
       // shapeRenderer.setColor(Color.BLACK);
       // shapeRenderer.circle(airShipCircle.x,airShipCircle.y ,airShipCircle.radius);




        for(int i = 0 ; i < numberOfEnemies ; i++) {
            //shapeRenderer.circle(enemyX[i] +Gdx.graphics.getWidth()/24 ,enemyOffSet1[i] + Gdx.graphics.getHeight()/2 ,Gdx.graphics.getWidth()/24  );
            // shapeRenderer.circle(enemyX[i] +Gdx.graphics.getWidth()/24 ,enemyOffSet2[i] + Gdx.graphics.getHeight()/2 ,Gdx.graphics.getWidth()/24  );
             //shapeRenderer.circle(enemyX[i] +Gdx.graphics.getWidth()/24 ,enemyOffSet3[i] + Gdx.graphics.getHeight()/2 ,Gdx.graphics.getWidth()/24  );

            //airship collision
            int z  = 0 ;
            if (Intersector.overlaps(airShipCircle, enemyCircle[i]) || Intersector.overlaps(airShipCircle, enemyCircle2[i]) || Intersector.overlaps(airShipCircle, enemyCircle3[i])) {
                gameState = 2;

            }

            //laser collision
            if (Intersector.overlaps(laserCircle, enemyCircle[i]) || Intersector.overlaps(laserCircle, enemyCircle2[i]) || Intersector.overlaps(laserCircle, enemyCircle3[i])) {
                laserbo = false;
                laserX = Gdx.graphics.getWidth() / 6 + 110;
                long id2 =explosionSound.play(0.4f);
                explosionSound.setPitch(id2 , 3);
                explosionSound.setLooping(id2,false);

            }
////////////////////////
            //laser gone outside of the screen
            if (laserX>Gdx.graphics.getWidth()){
                laserbo = false;
                laserX = Gdx.graphics.getWidth() / 6 + 110;
                laserCircle.set(-5000,-5000,Gdx.graphics.getWidth()/55f);
            }
        }
       // shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
       // shapeRenderer.setColor(Color.BLACK);
       // shapeRenderer.circle(laserCircle.x,laserCircle.y ,laserCircle.radius);

        shapeRenderer.end();
    }

    @Override
    public void hide() {
    }
    @Override
    public void create () {
    }
    @Override
    public void render () {
    }
    @Override
    public void dispose () {
        laserSound.dispose();
        explosionSound.dispose();
        font4.dispose();
        font3.dispose();
        generator.dispose();

    }



}
