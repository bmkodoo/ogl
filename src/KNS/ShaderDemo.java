package KNS;

import KNS.entities.Camera;
import KNS.entities.Entity;
import KNS.models.TexturedModel;
import KNS.renderEngine.SkyBoxRenderer;
import KNS.shaders.GlassShader;
import KNS.shaders.StaticShader;
import KNS.shaders.WaterShader;
import KNS.textures.ModelTexture;
import org.lwjgl.opengl.Display;
import KNS.renderEngine.DisplayManager;
import KNS.models.RawModel;
import KNS.renderEngine.Renderer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;

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
        DisplayManager.createDisplay();
        WaterShader waterShader = new WaterShader();
        GlassShader glassShader = new GlassShader();
        Renderer renderer = new Renderer(waterShader);
        Renderer glassRenderer = new Renderer(glassShader);

        ModelTexture texture = new ModelTexture(loader.loadTexture("texture.png"));

        RawModel glassRawModel = OBJLoader.load("res/cilinder.obj", loader);
        TexturedModel texturedModel = new TexturedModel(glassRawModel, texture);
        Entity glass = new Entity(texturedModel, new Vector3f(0, -4, -7 ), 0, 0, 0, 1);

        RawModel waterRawModel = OBJLoader.load("res/cilinder.obj", loader);
        TexturedModel waterModel = new TexturedModel(waterRawModel, texture);
        Entity water = new Entity(waterModel, new Vector3f(0, -4, -7 ), 0, 0, 0, 0.8f);
        water.increaseRotation(0, 0, 180);
        water.increasePosition(0, 1f, 0);

        RawModel cupRawModel = OBJLoader.load("res/circle.obj", loader);
        TexturedModel capModel = new TexturedModel(cupRawModel, texture);
        Entity cup = new Entity(capModel, new Vector3f(0, -4, -7 ), 0, 0, 0, 0.8f);
        cup.increasePosition(0, 1.33f, 0);

        Camera camera = new Camera();

        SkyBoxRenderer skyBoxRenderer = new SkyBoxRenderer(loader, renderer.getProjectionMatrix());

        while (!Display.isCloseRequested()) {
            //entity.increasePosition(0, 0, -0.1f);
            //glass.increaseRotation(-0.3f, 0, 0);
            //water.increaseRotation(-0.3f, 0, 0);
            camera.move();
            renderer.prepare();

            skyBoxRenderer.render(camera);

            waterShader.start();
            waterShader.loadViewMatrix(camera);
            waterShader.loadCameraPos(camera.getPosition());
            renderer.render(cup, waterShader);
            renderer.render(water, waterShader);
            waterShader.stop();

            glassShader.start();
            glassShader.loadViewMatrix(camera);
            glassShader.loadCameraPos(camera.getPosition());

            glassRenderer.render(glass, waterShader);
            glassShader.stop();

            DisplayManager.updateDisplay();
        }

        loader.cleanUp();
        waterShader.cleanUp();
        DisplayManager.closeDisplay();
        System.exit(0);
    }

    private void glDrawCube() {
        GL11.glBegin(GL11.GL_QUADS);
        // Front Face
        GL11.glNormal3f( 0.0f, 0.0f, 1.0f);
        GL11.glTexCoord2f(0.0f, 0.0f); GL11.glVertex3f(-1.0f, -1.0f,  1.0f);
        GL11.glTexCoord2f(1.0f, 0.0f); GL11.glVertex3f( 1.0f, -1.0f,  1.0f);
        GL11.glTexCoord2f(1.0f, 1.0f); GL11.glVertex3f( 1.0f,  1.0f,  1.0f);
        GL11.glTexCoord2f(0.0f, 1.0f); GL11.glVertex3f(-1.0f,  1.0f,  1.0f);
        // Back Face
        GL11.glNormal3f( 0.0f, 0.0f,-1.0f);
        GL11.glTexCoord2f(1.0f, 0.0f); GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
        GL11.glTexCoord2f(1.0f, 1.0f); GL11.glVertex3f(-1.0f,  1.0f, -1.0f);
        GL11.glTexCoord2f(0.0f, 1.0f); GL11.glVertex3f( 1.0f,  1.0f, -1.0f);
        GL11.glTexCoord2f(0.0f, 0.0f); GL11.glVertex3f( 1.0f, -1.0f, -1.0f);
        // Top Face
        GL11.glNormal3f( 0.0f, 1.0f, 0.0f);
        GL11.glTexCoord2f(0.0f, 1.0f); GL11.glVertex3f(-1.0f,  1.0f, -1.0f);
        GL11.glTexCoord2f(0.0f, 0.0f); GL11.glVertex3f(-1.0f,  1.0f,  1.0f);
        GL11.glTexCoord2f(1.0f, 0.0f); GL11.glVertex3f( 1.0f,  1.0f,  1.0f);
        GL11.glTexCoord2f(1.0f, 1.0f); GL11.glVertex3f( 1.0f,  1.0f, -1.0f);
        // Bottom Face
        GL11.glNormal3f( 0.0f,-1.0f, 0.0f);
        GL11.glTexCoord2f(1.0f, 1.0f); GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
        GL11.glTexCoord2f(0.0f, 1.0f); GL11.glVertex3f( 1.0f, -1.0f, -1.0f);
        GL11.glTexCoord2f(0.0f, 0.0f); GL11.glVertex3f( 1.0f, -1.0f,  1.0f);
        GL11.glTexCoord2f(1.0f, 0.0f); GL11.glVertex3f(-1.0f, -1.0f,  1.0f);
        // Right Face
        GL11.glNormal3f( 1.0f, 0.0f, 0.0f);
        GL11.glTexCoord2f(1.0f, 0.0f); GL11.glVertex3f( 1.0f, -1.0f, -1.0f);
        GL11.glTexCoord2f(1.0f, 1.0f); GL11.glVertex3f( 1.0f,  1.0f, -1.0f);
        GL11.glTexCoord2f(0.0f, 1.0f); GL11.glVertex3f( 1.0f,  1.0f,  1.0f);
        GL11.glTexCoord2f(0.0f, 0.0f); GL11.glVertex3f( 1.0f, -1.0f,  1.0f);
        // Left Face
        GL11.glNormal3f(-1.0f, 0.0f, 0.0f);
        GL11.glTexCoord2f(0.0f, 0.0f); GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
        GL11.glTexCoord2f(1.0f, 0.0f); GL11.glVertex3f(-1.0f, -1.0f,  1.0f);
        GL11.glTexCoord2f(1.0f, 1.0f); GL11.glVertex3f(-1.0f,  1.0f,  1.0f);
        GL11.glTexCoord2f(0.0f, 1.0f); GL11.glVertex3f(-1.0f,  1.0f, -1.0f);
        GL11.glEnd();
    }
}
