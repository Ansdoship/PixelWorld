package com.ansdoship.pixelworld.screen;

import com.ansdoship.pixelworld.ui.ZoomRegulator;
import com.ansdoship.pixelworld.util.Constant;
import com.ansdoship.pixelworld.util.TileMap;
import com.ansdoship.pixelworld.util.input.OrthoCamController;
import com.ansdoship.pixelworld.world.Chunk;
import com.ansdoship.pixelworld.world.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisCHLoader;
import com.kotcrab.vis.ui.VisUI;

public class GameScreen extends ScreenAdapter {
    public World world;
    public TileMap tilemap;
    public Chunk chunk;
    OrthographicCamera camera;
	OrthoCamController cameraController;
    Stage uiStage;
    public InputMultiplexer inputMultiplexer;
    float zoom = 1;

    protected static Vector3 tmp_vector3 = new Vector3(0, 0, 0);

    public GameScreen() {
        VisCHLoader.load();
        
        camera = new OrthographicCamera(Constant.screenWidth, Constant.screenHeight);
		camera.update();
        cameraController = new OrthoCamController(camera);
        
        world = new World(2);
        world.createDefaultShader();        

        uiStage = new Stage(new ScreenViewport());
        uiStage.addActor(new ZoomRegulator(camera));

        Texture t = new Texture("images/tiles/ground.png");
        for (int i = 0; i < world.width; i++) {
            for (int j = 0; j < world.height; j++) {
                world.getChunk(i, j).fill(t);
            }
        }
        
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(uiStage);
        inputMultiplexer.addProcessor(cameraController);
		Gdx.input.setInputProcessor(inputMultiplexer);        
        
    }

    @Override
    public void render(float d) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.app.log("Fps", "" + Gdx.graphics.getFramesPerSecond());

        camera.update();
        world.setProjectionMatrix(camera.combined);

        world.begin();
        world.draw();
        world.end();
        
        uiStage.draw();
        uiStage.act();

    }

    @Override
    public void dispose() {
        world.dispose();
        uiStage.dispose();
        VisCHLoader.dispose();
        VisUI.dispose();
	}

}
