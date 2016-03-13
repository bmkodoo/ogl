package KNS.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by kodoo on 21.02.2016.
 */
public class Camera {

    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch;
    private float yaw;
    private float roll;

    public void move() {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            position.z -= 0.02f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            position.z += 0.02f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            position.x += 0.02f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            position.x -= 0.02f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            pitch -= 0.2f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            pitch += 0.2f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            yaw -= 0.2f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            yaw += 0.2f;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
