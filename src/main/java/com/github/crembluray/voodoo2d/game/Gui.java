package com.github.crembluray.voodoo2d.game;

import com.github.crembluray.voodoo2d.engine.gameObject.GameObject;
import com.github.crembluray.voodoo2d.engine.graphic.Mesh;
import com.github.crembluray.voodoo2d.engine.gui.Button;
import com.github.crembluray.voodoo2d.engine.gui.IGui;
import org.joml.Vector2f;

public class Gui implements IGui {

    private GameObject[] gameObjects;

    @Override
    public GameObject[] getGameObjects() {
        return gameObjects;
    }

    @Override
    public void init() {
        Mesh mesh = Mesh.loadMesh("textures/button.png", 32);
        Button button = new Button(new Vector2f(), new Vector2f(1.0f, 1.0f), mesh);
        gameObjects = new GameObject[] {button};
    }
}
