package com.ansdoship.pixelworld.util.input;

import com.ansdoship.pixelworld.util.PositionF;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class OrthoCamController extends BaseInput {
    final OrthographicCamera camera;
    final Vector3 curr = new Vector3();
    final Vector3 last = new Vector3(-1, -1, -1);
    final Vector3 delta = new Vector3();

    protected static final float MOVE_VELOCITY = 5;
    protected static final int INTORNO_CLICK_PRECISION = 50;
    protected static final float INTORNO_ZOOM_PRECISION = 0.1f;
    protected static final float ZOOM_VELOCITY = 3;


    private boolean zoomTo;
    private float zoomPosition;

    private boolean move;

    private Vector3 clickCoords;

    private Vector2 previusPosition;

    private float startSpan;

    public OrthoCamController(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void zoomTo(float zoom) {
        this.zoomPosition = zoom;
        this.zoomTo = true;
    }

    public void animationUpdate() {
        cameraTapMoveAnimation();
        updateZoom();
    }

    protected void cameraTapMoveAnimation() {
        // se ci troviamo in un intorno del punto e siamo in movimento fermo il movimento.
        if ((move && (camera.position.x < clickCoords.x + Gdx.graphics.getWidth() / INTORNO_CLICK_PRECISION
            && camera.position.x > clickCoords.x - Gdx.graphics.getWidth() / INTORNO_CLICK_PRECISION
            && camera.position.y < clickCoords.y + Gdx.graphics.getHeight() / INTORNO_CLICK_PRECISION
            && camera.position.y > clickCoords.y - Gdx.graphics.getHeight() / INTORNO_CLICK_PRECISION)))
            move = false;
        // Se la variabile move è  a true dobbiamo muovere la telecamera nella posizione del tap
        if (move) {
            double distanceX = camera.position.x - clickCoords.x;
            double distanceY = camera.position.y - clickCoords.y;
            camera.translate((float) -(MOVE_VELOCITY * distanceX / 2) * Gdx.graphics.getDeltaTime(),
                             (float) -(MOVE_VELOCITY * distanceY / 2) * Gdx.graphics.getDeltaTime());
        }


    }

    protected void updateZoom() {
        if (camera.zoom > zoomPosition - INTORNO_ZOOM_PRECISION && zoomPosition > camera.zoom)
            zoomTo = false;
        if (camera.zoom < zoomPosition + INTORNO_ZOOM_PRECISION && zoomPosition < camera.zoom)
            zoomTo = false;
        if (zoomTo) {
            double distZoom = zoomPosition - camera.zoom;
            camera.zoom += Gdx.graphics.getDeltaTime() * ZOOM_VELOCITY * (distZoom);
        }
    }

    public void stopMoving() {
        move = false;
    }

    public void savePosition() { previusPosition = new Vector2(camera.position.x, camera.position.y); }

    public void comeBack() { moveCameraTo(previusPosition.x, previusPosition.y);}

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        startSpan = PositionF.distance(new Vector3(screenX, screenY, 0), last);
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        if (pointer == 0) {
            camera.unproject(curr.set(x, y, 0));
            if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
                camera.unproject(delta.set(last.x, last.y, 0));
                delta.sub(curr);
                camera.position.add(delta.x, delta.y, 0);
            }
            last.set(x, y, 0);
        } else if (pointer == 1) {            
            float curSpan = PositionF.distance(curr, last);       
            camera.zoom = 1f * (curSpan / startSpan);
            camera.update();
            Gdx.app.log("zoom", "" + camera.zoom);
        }
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        last.set(-1, -1, -1);
        return false;
    }

    public void moveCameraTo(float x, float y) {
        clickCoords = camera.unproject(new Vector3(x, y, 0));
        move = true;
    }

}

