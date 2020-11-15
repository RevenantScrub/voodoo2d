package com.github.crembluray.voodoo2d.game;

import com.github.crembluray.voodoo2d.engine.MouseInput;
import com.github.crembluray.voodoo2d.engine.Window;
import com.github.crembluray.voodoo2d.engine.gameObject.GameObject;
import com.github.crembluray.voodoo2d.engine.graphic.Mesh;
import com.github.crembluray.voodoo2d.engine.gui.Button;
import com.github.crembluray.voodoo2d.engine.gui.IGui;
import org.joml.Vector2f;

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
        buttonOne = new Button(mesh1, new Vector2f(0.4f, 0.46f));
        buttonTwo = new Button(mesh2, new Vector2f(0.4f, 0.46f));
        buttonOne.getPosition().x = 0.0f;
        buttonTwo.getPosition().y = 1.0f;
        gameObjects = new GameObject[] {buttonOne, buttonTwo};
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        boolean buttonOneHover = buttonOne.isHovering(window);
        boolean buttonTwoHover = buttonTwo.isHovering(window);
        // If hovering over button, set to selected state
        if(buttonOneHover)
            buttonOne.setState(Button.STATE_SELECTED);
        else
            buttonOne.setState(Button.STATE_IDLE);
        if(buttonTwoHover)
            buttonTwo.setState(Button.STATE_SELECTED);
        else
            buttonTwo.setState(Button.STATE_IDLE);
        // If clicked on the button, set to clicked state
        if(buttonOneHover && mouseInput.isLeftButtonPressed())
            buttonOne.setState(Button.STATE_CLICKED);
        if(buttonTwoHover && mouseInput.isLeftButtonPressed())
            buttonTwo.setState(Button.STATE_CLICKED);
    }
}
