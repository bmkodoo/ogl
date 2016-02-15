package KNS;

import KNS.shaders.StaticShader;
import org.lwjgl.opengl.Display;
import KNS.renderEngine.DisplayManager;
import KNS.renderEngine.RawModel;
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

        RawModel model = loader.loadToVAO(vertices, indices);

        while (!Display.isCloseRequested()) {
            renderer.prepare();
            shader.start();
            renderer.render(model);
            shader.stop();

            DisplayManager.updateDisplay();
        }

        loader.cleanUp();
        shader.cleanUp();
        DisplayManager.closeDisplay();
        System.exit(0);
    }
}
