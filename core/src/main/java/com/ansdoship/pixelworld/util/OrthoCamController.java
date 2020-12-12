package com.ansdoship.pixelworld.util;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Gdx;

public class OrthoCamController extends BaseInput {
    final OrthographicCamera camera;
    final Vector3 curr = new Vector3();
    final Vector3 last = new Vector3(-1, -1, -1);
    final Vector3 delta = new Vector3();

    private float startSpan;

    public OrthoCamController(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        startSpan = PositionF.distance(curr, new Vector3(screenX, screenY, 0));
        return false;
    }
   
    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        if (pointer == 0){
        camera.unproject(curr.set(x, y, 0));
        if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
            camera.unproject(delta.set(last.x, last.y, 0));
            delta.sub(curr);
            camera.position.add(delta.x, delta.y, 0);
        }
        last.set(x, y, 0);
        } else if(pointer == 1){
            
            float curSpan = PositionF.distance(curr, last);       
            camera.zoom = 1f * (curSpan / startSpan);
            camera.update();
            Gdx.app.log("zoom", ""+camera.zoom);
        }
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        last.set(-1, -1, -1);
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }
}

