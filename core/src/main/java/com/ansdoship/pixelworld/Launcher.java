package com.ansdoship.pixelworld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.ansdoship.pixelworld.screen.SplashScreen;
import com.ansdoship.pixelworld.screen.GameScreen;
import com.badlogic.gdx.assets.AssetManager;

public class Launcher extends Game {
    AssetManager aman;
    
    @Override
    public void create () {
        aman = new AssetManager();
        SplashScreen splashScreen = new SplashScreen();
        setScreen(splashScreen);
        Timer timer = new Timer();
        Task task = new Task() {
            @Override
            public void run() {
                changeScreen();
            }
        };
        timer.scheduleTask(task, 3);
    }

    public void changeScreen(){
        GameScreen gameScreen = new GameScreen();
        setScreen(gameScreen);
    }

    @Override
    public void dispose () {
        aman.dispose();
    }
}
