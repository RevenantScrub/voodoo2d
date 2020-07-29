package com.github.crembluray.voodoo2d.game;

import com.github.crembluray.voodoo2d.engine.GameEngine;
import com.github.crembluray.voodoo2d.engine.IGameLogic;

public class Main {
 
    public static void main(String[] args) {
        try {
            boolean vSync = true, antiAliasing = true;
            IGameLogic gameLogic = new Game();
            GameEngine gameEng = new GameEngine("V2D", 800, 600, vSync, antiAliasing, gameLogic);
            gameEng.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}