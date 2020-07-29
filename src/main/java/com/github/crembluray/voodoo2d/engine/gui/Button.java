package com.github.crembluray.voodoo2d.engine.gui;

import com.github.crembluray.voodoo2d.engine.animation.Animation;
import com.github.crembluray.voodoo2d.engine.gameObject.AABB;
import com.github.crembluray.voodoo2d.engine.gameObject.GameObject;
import com.github.crembluray.voodoo2d.engine.graphic.Mesh;
import org.joml.Matrix4f;
import org.joml.Vector2f;

public class Button extends GameObject {

    public static final int STATE_IDLE = 0;
    public static final int STATE_SELECTED = 1;
    public static final int STATE_CLICKED = 2;

    private AABB boundingBox;

    private int selectedState;

    private static Matrix4f transform = new Matrix4f();

    public Button(Vector2f position, Vector2f scale, Mesh mesh) {
        super(mesh);
        this.boundingBox = new AABB(position, scale);
        this.setAnimation(new Animation(this, 0, 2, 0));
    }

}
