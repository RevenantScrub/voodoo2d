package com.github.crembluray.voodoo2d.engine.gui;

import com.github.crembluray.voodoo2d.engine.animation.Animation;
import com.github.crembluray.voodoo2d.engine.gameObject.GameObject;
import com.github.crembluray.voodoo2d.engine.graphic.Mesh;

public class Button extends GameObject {

    public static final int STATE_IDLE = 0;
    public static final int STATE_SELECTED = 1;
    public static final int STATE_CLICKED = 2;

    private int selectedState;

    public Button(Mesh mesh) {
        super(mesh);
    }

    public void setState(int selectedState) {
        if(selectedState < 0 || selectedState > 2) {
            try {
                throw new Exception("Button state is invalid.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.selectedState = selectedState;
        this.getMesh().setCurrentFrame(selectedState);
    }

    public int getState() {
        return selectedState;
    }
}
