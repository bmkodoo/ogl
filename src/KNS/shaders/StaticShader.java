package KNS.shaders;

import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by kodoo on 15.02.2016.
 */
public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "shader.vert";
    private static final String FRAGMENT_FILE = "shader.frag";
    private int location_transformationMatrix;
    private int location_projectionMatrix;

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformmationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        super.loadMatrix(location_projectionMatrix, matrix);
    }
}
