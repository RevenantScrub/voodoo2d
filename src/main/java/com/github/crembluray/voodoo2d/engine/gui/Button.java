package com.github.crembluray.voodoo2d.engine.gui;

import com.github.crembluray.voodoo2d.engine.MouseInput;
import com.github.crembluray.voodoo2d.engine.Window;
import com.github.crembluray.voodoo2d.engine.gameObject.AABB;
import com.github.crembluray.voodoo2d.engine.gameObject.GameObject;
import com.github.crembluray.voodoo2d.engine.graphic.Mesh;
import org.joml.Vector2f;

public class Button extends GameObject {

    public static final int STATE_IDLE = 0;
    public static final int STATE_SELECTED = 1;
    public static final int STATE_CLICKED = 2;

    private int selectedState;

    private AABB aabb;

    public Button(Mesh mesh, Vector2f size) {
        super(mesh);
        aabb = new AABB(new Vector2f(this.getPosition().x, this.getPosition().y), size);
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

    public boolean isHovering(Window window) {
        Vector2f worldCoords = MouseInput.calcWorldCoords(window);
        aabb.setCenter(new Vector2f(this.getPosition().x, this.getPosition().y));
        AABB cursor = new AABB(worldCoords, new Vector2f(0, 0));

        return cursor.intersects(aabb);
    }

    public int getState() {
        return selectedState;
    }
}
