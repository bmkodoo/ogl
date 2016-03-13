package KNS.shaders;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by kodoo on 13.03.2016.
 */
public class WaterShader extends StaticShader {

    private static final String VERTEX_FILE = "water.vert";
    private static final String FRAGMENT_FILE = "water.frag";

    private int location_cameraPosition;

    public WaterShader() {
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
