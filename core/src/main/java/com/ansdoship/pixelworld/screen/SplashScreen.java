package com.ansdoship.pixelworld.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ansdoship.pixelworld.util.Constant;

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
        logo.setProjectionMatrix(camera.combined);
        logo.begin();
        logo.draw(texture, 0, 0, screenWidth, screenHeight);
        logo.end();
    }
    
    public void dispose() {
        logo.dispose();
    }
}

