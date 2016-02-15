import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import java.io.*;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.glGetShaderi;

/**
 * Created by kodoo on 15.02.2016.
 */
public class Loader {

    static int loadShader(String name, int type) {
        StringBuilder shaderText;
        try(BufferedReader reader = new BufferedReader(new FileReader("shaders/" + name))) {
            shaderText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
                shaderText.append(line).append('\n');
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

        int shader = glCreateShader(type);
        glShaderSource(shader, shaderText);
        glCompileShader(shader);

        if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) {
            System.out.println(glGetShaderInfoLog(shader, 1000));
            throw new RuntimeException("Shader \"" + name + "\": compile error!");
        }

        return shader;
    }

    public int loadCubeMap(String[] textureFiles) {
        int texID = GL11.glGenTextures();
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texID);

        for (int i = 0; i < textureFiles.length; i++) {
            TextureData data = decodeTextureFile("rex/" + textureFiles[i] + ".png");
            GL11.glTexImage2D(
                    GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i,
                    0,
                    GL11.GL_RGBA,
                    data.getWidth(),
                    data.getHeight(),
                    0,
                    GL11.GL_RGBA,
                    GL11.GL_UNSIGNED_BYTE,
                    data.getBuffer());
        }
        GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);

        return texID;
    }

    TextureData decodeTextureFile(String fileName) {
        int width = 0;
        int height = 0;
        ByteBuffer buffer = null;
        try(FileInputStream in = new FileInputStream(fileName)) {
            PNGDecoder decoder = new PNGDecoder(in);
            width = decoder.getWidth();
            height = decoder.getHeight();
            buffer = ByteBuffer.allocateDirect(4 * width * height);
            decoder.decode(buffer, width * 4, PNGDecoder.Format.RGBA);
            buffer.flip();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new TextureData(buffer, width, height);
    }
}
