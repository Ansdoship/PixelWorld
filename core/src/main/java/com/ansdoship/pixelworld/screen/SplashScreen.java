package com.ansdoship.pixelworld.screen;

import com.ansdoship.pixelworld.util.Constant;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen extends ScreenAdapter {
    private SpriteBatch logo;
    private OrthographicCamera camera;
    private Texture texture;

    int screenWidth = Constant.screenWidth;
    int screenHeight = Constant.screenHeight;

    public SplashScreen() {
        logo = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        camera.update();
        texture = new Texture("images/AnsdoShip_logo.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        logo.setProjectionMatrix(camera.combined);
        logo.begin();
        logo.draw(texture, 320/2, screenHeight/4, screenWidth-320, screenHeight/2);
        logo.end();
    }
    
    public void dispose() {
        logo.dispose();
    }
}

