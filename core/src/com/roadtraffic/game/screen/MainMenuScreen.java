package com.roadtraffic.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.roadtraffic.game.GameTraffic;

public class MainMenuScreen implements Screen {

    GameTraffic game;
    Texture texture_logo;
    Texture texture_btn;
    Stage stage;
    public TextureRegion drawableLogo;
    public Skin skin;
    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        texture_logo = new Texture(Gdx.files.internal("images_ui/logo.png"));
        drawableLogo = new TextureRegion(texture_logo,0,0,480,300);
        TextureAtlas mainMenuAtlas = new TextureAtlas("images_ui/btn.atlas");
        Skin buttonsSkin = new Skin(mainMenuAtlas);
        ImageButton.ImageButtonStyle connectToHost = new ImageButton.ImageButtonStyle();
        connectToHost.up = buttonsSkin.getDrawable("btnstart");//кнопка не нажата
        connectToHost.over = buttonsSkin.getDrawable("btnstart");
        connectToHost.down = buttonsSkin.getDrawable("btnstart_prsd"); // кнопка нажата
        ImageButton gameClientButton = new ImageButton(connectToHost);
        gameClientButton.setSize(200, 55);// размер кнопки
        gameClientButton.setPosition(game.GAME_Weight * .43f,350);
        stage.addActor(gameClientButton ); //добавляем кнопку к сцене
        gameClientButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.Level1);
                dispose();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.input.isTouched(Input.Keys.LEFT))
                {
                    return true;
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
    public MainMenuScreen(GameTraffic game){
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(240 / 255f, 241 / 255f, 217 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        stage.act(delta);
        stage.draw();
        game.batch.draw(drawableLogo, game.GAME_Weight * .36f, 620);
        game.batch.end();
    }
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        game.batch.dispose();
    }
}
