package com.github.crembluray.voodoo2d.engine;

import com.github.crembluray.voodoo2d.engine.gameObject.GameObject;
import com.github.crembluray.voodoo2d.engine.graph.ShaderProgram;
import com.github.crembluray.voodoo2d.engine.graph.Transformation;
import com.github.crembluray.voodoo2d.engine.gui.IGui;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    /**
     * Field of View in Radians
     */
    private static final float FOV = (float) Math.toRadians(60.0f);

    private static final float Z_NEAR = 0.01f;

    private static final float Z_FAR = 1000.f;

    private final Transformation transformation;

    private ShaderProgram shaderProgram;

    public Renderer() {
        transformation = new Transformation();
    }

    public void init() throws Exception {
        // Create shader
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Utils.loadResource("/shaders/vertex.vs"));
        shaderProgram.createFragmentShader(Utils.loadResource("/shaders/fragment.fs"));
        shaderProgram.link();

        // Create uniforms for modelView and projection matrices and texture
        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("modelViewMatrix");
        shaderProgram.createUniform("texture_sampler");
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render(Window window, Camera camera, GameObject[] gameObjects, IGui gui) {
        clear();

        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        shaderProgram.bind();

        // Update projection Matrix
        Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);

        // Update view Matrix
        Matrix4f viewMatrix = transformation.getViewMatrix(camera);

        shaderProgram.setUniform("texture_sampler", 0);
        // Render each gameObject
        for (GameObject gameObject : gameObjects) {
            // Set model view matrix for this item
            Matrix4f modelViewMatrix = transformation.getModelViewMatrix(gameObject, viewMatrix);
            shaderProgram.setUniform("modelViewMatrix", modelViewMatrix);
            // Render the mesh for this gameObject
            gameObject.getMesh().render();
        }

        renderGui(window, camera, gui);

        shaderProgram.unbind();
    }

    private void renderGui(Window window, Camera camera, IGui gui) {
        // Update projection Matrix
        Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);

        // Update view Matrix
        Matrix4f viewMatrix = transformation.getViewMatrix(camera);

        shaderProgram.setUniform("texture_sampler", 0);
        // Render each gameObject
        for (GameObject gameObject : gui.getGameObjects()) {
            // Set model view matrix for this item
            Matrix4f modelViewMatrix = transformation.getModelViewMatrix(gameObject, viewMatrix);
            shaderProgram.setUniform("modelViewMatrix", modelViewMatrix);
            // Render the mesh for this gameObject
            gameObject.getMesh().render();
        }
    }

    public Matrix4f getProjectionMatrix(Window window) {
        return transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
    }

    public Matrix4f getViewMatrix(Camera camera) {
        return transformation.getViewMatrix(camera);
    }

    public void cleanup() {
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }
    }
}
