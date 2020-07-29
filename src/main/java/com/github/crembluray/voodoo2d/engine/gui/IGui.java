package com.github.crembluray.voodoo2d.engine.gui;

import com.github.crembluray.voodoo2d.engine.gameObject.GameObject;

public interface IGui {

    GameObject[] getGameObjects();

    default void cleanup() {
        GameObject[] gameObjects = getGameObjects();
        for (GameObject gameObject : gameObjects) {
            gameObject.getMesh().cleanUp();
        }
    }

    void init();

}
