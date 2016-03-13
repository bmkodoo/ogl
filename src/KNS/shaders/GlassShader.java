package KNS.shaders;

import KNS.entities.Camera;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by kodoo on 21.02.2016.
 */
public class GlassShader extends StaticShader {

    private static final String VERTEX_FILE = "reflection.vert";
    private static final String FRAGMENT_FILE = "reflection.frag";

    private int location_cameraPosition;

    public GlassShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        super.getAllUniformLocations();
        location_cameraPosition = super.getUniformLocation("cameraPos");
    }

    public void loadCameraPos(Vector3f pos) {
        super.loadVector(location_cameraPosition, pos);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttributes();
    }
}
