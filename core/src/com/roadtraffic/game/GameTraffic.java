package com.roadtraffic.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.roadtraffic.game.screen.Level1;
import com.roadtraffic.game.screen.MainMenuScreen;


public class GameTraffic extends Game {

     public SpriteBatch batch;
     public BitmapFont font;
     public Camera camera;
     public static float GAME_Weight = 1920f;
     public static float GAME_Height = 1080f;
     private static final float ASPECT_RATIO = (float)GAME_Weight/(float)GAME_Height;
     private Rectangle viewport;
     public MainMenuScreen menuScreen;
     public Level1 Level1;
    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        menuScreen =new MainMenuScreen(this);
        Level1 =new Level1(this);
        camera = new OrthographicCamera(GAME_Weight, GAME_Height);
        camera.update();
        setScreen(menuScreen);
    }
    public void render() {
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                (int) viewport.width, (int) viewport.height);
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float)width/(float)height;
        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);
        if(aspectRatio > ASPECT_RATIO)
        {
            scale = (float)height/(float)GAME_Height;
            crop.x = (width - GAME_Weight*scale)/2f;
        }
        else if(aspectRatio < ASPECT_RATIO)
        {
            scale = (float)width/(float)GAME_Weight;
            crop.y = (height - GAME_Height*scale)/2f;
        }
        else
        {
            scale = (float)width/(float)GAME_Weight;
        }

        float w = (float)GAME_Weight*scale;
        float h = (float)GAME_Height*scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
    }
}