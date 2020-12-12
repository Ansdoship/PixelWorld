package com.ansdoship.pixelworld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.ansdoship.pixelworld.screen.SplashScreen;
import com.ansdoship.pixelworld.screen.GameScreen;

public class Launcher extends Game {
    
    @Override
    public void create () {
//        SplashScreen splashScreen = new SplashScreen();
//        setScreen(splashScreen);
        
        changeScreen();
        Timer timer = new Timer();
        Task task = new Task() {
            @Override
            public void run() {
                
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
    }
}
