import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;

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

        DisplayManager.createDisplay();
        StaticShader shader = new StaticShader();


        while (!Display.isCloseRequested()) {
            shader.start();
            drawTriangle();
            shader.stop();

            DisplayManager.updateDisplay();
        }

        shader.cleanUp();
        DisplayManager.closeDisplay();
        System.exit(0);
    }
}
