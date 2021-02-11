package com.ansdoship.pixelworld.ui;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisProgressBar;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.ansdoship.pixelworld.util.input.OrthoCamController;

public class ZoomRegulator extends VisWindow {
    
    OrthoCamController cameraController;
    
    public ZoomRegulator(OrthoCamController cameraController){
        super("相机缩放调节");
        this.cameraController = cameraController;
        TableUtils.setSpacingDefaults(this);
        columnDefaults(0).left();

        addVisWidgets();        

        setSize(200, 250);
        setResizable(true);
        setPosition(360, 360);
        addCloseButton();
		closeOnEscape();	
    }
    
    private void addVisWidgets () {
        final VisSlider slider = new VisSlider(1, 500, 1, true);
        slider.addListener(new DragListener() {
                public void drag(InputEvent event, float x, float y, int pointer) {
                    cameraController.zoomTo(slider.getValue()/100);
                    cameraController.animationUpdate();
                }
            });
        slider.addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    cameraController.zoomTo(slider.getValue()/100);
                    cameraController.animationUpdate();
                    return false;
                }
            });
        slider.setValue(1);

        VisTable progressbarTable = new VisTable(true);
        progressbarTable.add(slider);

        add(progressbarTable);
	}
    
}
