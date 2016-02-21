package KNS;

import KNS.models.TexturedModel;
import KNS.shaders.StaticShader;
import KNS.textures.ModelTexture;
import org.lwjgl.opengl.Display;
import KNS.renderEngine.DisplayManager;
import KNS.models.RawModel;
import KNS.renderEngine.Renderer;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by kodoo on 14.02.2016.
 */
public class ShaderDemo {

    static void drawTriangle() {
        glBegin(GL_TRIANGLES);
        glColor3f(1, 0, 0);
        glVertex2f(-0.5f, -0.5f);
        glColor3f(0, 1, 0);
        glVertex2f(0.5f, -0.5f);
        glColor3f(0, 0, 1);
        glVertex2f(0, 0.5f);
        glEnd();
    }

    public static void main(String[] args) {

        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        DisplayManager.createDisplay();
        StaticShader shader = new StaticShader();

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f
        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        float[] textureCoords = {
                0,0,
                0,1,
                1,1,
                1,0
        };

        RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("Glitch_Male"));
        TexturedModel texturedModel = new TexturedModel(model, texture);

        while (!Display.isCloseRequested()) {
            renderer.prepare();
            shader.start();
            renderer.render(texturedModel);
            shader.stop();

            DisplayManager.updateDisplay();
        }

        loader.cleanUp();
        shader.cleanUp();
        DisplayManager.closeDisplay();
        System.exit(0);
    }
}
