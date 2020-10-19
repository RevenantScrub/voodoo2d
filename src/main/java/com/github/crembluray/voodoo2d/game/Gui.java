package com.github.crembluray.voodoo2d.game;

import com.github.crembluray.voodoo2d.engine.MouseInput;
import com.github.crembluray.voodoo2d.engine.Window;
import com.github.crembluray.voodoo2d.engine.gameObject.GameObject;
import com.github.crembluray.voodoo2d.engine.graphic.Mesh;
import com.github.crembluray.voodoo2d.engine.gui.Button;
import com.github.crembluray.voodoo2d.engine.gui.IGui;

import static org.lwjgl.glfw.GLFW.*;

public class Gui implements IGui {

    private Button buttonOne;
    private Button buttonTwo;

    private GameObject[] gameObjects;

    @Override
    public GameObject[] getGameObjects() {
        return gameObjects;
    }

    @Override
    public void init() {
        Mesh mesh1 = Mesh.loadMesh("textures/button.png", 32);
        Mesh mesh2 = Mesh.loadMesh("textures/button.png", 32);
        buttonOne = new Button(mesh1);
        buttonTwo = new Button(mesh2);
        buttonOne.getPosition().x = -1.0f;
        buttonTwo.getPosition().x = 1.0f;
        gameObjects = new GameObject[] {buttonOne, buttonTwo};
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        if(window.isKeyPressed(GLFW_KEY_LEFT)) {
            buttonOne.setState(Button.STATE_SELECTED);
            buttonTwo.setState(Button.STATE_IDLE);
        } else if(window.isKeyPressed(GLFW_KEY_RIGHT)) {
            buttonTwo.setState(Button.STATE_SELECTED);
            buttonOne.setState(Button.STATE_IDLE);
        }
        if(window.isKeyPressed(GLFW_KEY_ENTER)) {
            if(buttonOne.getState() == Button.STATE_SELECTED) {
                buttonOne.setState(Button.STATE_CLICKED);
            } else if(buttonTwo.getState() == Button.STATE_SELECTED) {
                buttonTwo.setState(Button.STATE_CLICKED);
            }
        } else if(buttonOne.getState() == Button.STATE_CLICKED) {
            buttonOne.setState(Button.STATE_SELECTED);
        } else if(buttonTwo.getState() == Button.STATE_CLICKED) {
            buttonTwo.setState(Button.STATE_SELECTED);
        }
    }
}
