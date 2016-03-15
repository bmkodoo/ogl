package KNS;

import KNS.models.RawModel;
import org.lwjgl.util.vector.Vector3f;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kodoo on 15.03.2016.
 */
public class OBJLoader {

    public static RawModel load(String fileName, Loader loader) {
        List<Vector3f> vertices = new ArrayList<>();
        List<Vector3f> normals = new ArrayList<>();

        List<Float> verticesArray = new ArrayList<>();
        List<Float> normalsArray = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] curLine = line.split(" ");
                if (line.startsWith("v ")) {
                    Vector3f vertex = new Vector3f(
                            Float.parseFloat(curLine[1]),
                            Float.parseFloat(curLine[2]),
                            Float.parseFloat(curLine[3])
                    );
                    vertices.add(vertex);
                    System.out.printf("OBJLoader: v: %s\n", vertex);
                } else if (line.startsWith("vn ")) {
                    Vector3f normal = new Vector3f(
                            Float.parseFloat(curLine[1]),
                            Float.parseFloat(curLine[2]),
                            Float.parseFloat(curLine[3])
                    );
                    normals.add(normal);
                    System.out.printf("OBJLoader: vn: %s\n", normal);
                } else if (line.startsWith("f ")) {
                    System.out.printf("OBJLoader: f section.");
                    break;
                }
            }

            do {
                if (!line.startsWith("f "))
                    continue;

                String[] curLine = line.split(" ");
                for (int i = 1; i < curLine.length; i++) {
                    String vert = curLine[i];
                    String[] vertStrData = vert.split("//");

                    int vertPointer = Integer.parseInt(vertStrData[0]) - 1;
                    Vector3f curVert = vertices.get(vertPointer);
                    verticesArray.add(curVert.getX());
                    verticesArray.add(curVert.getY());
                    verticesArray.add(curVert.getZ());
                    System.out.printf("OBJLoader: face: %s\n", curVert);

                    int normPointer = Integer.parseInt(vertStrData[1]) - 1;
                    Vector3f curNormal = vertices.get(normPointer);
                    normalsArray.add(curNormal.getX());
                    normalsArray.add(curNormal.getY());
                    normalsArray.add(curNormal.getZ());
                    System.out.printf("OBJLoader: normal: %s\n", curNormal);
                }
            } while (((line = reader.readLine()) != null));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        float[] vertArray = new float[normalsArray.size()];
        float[] normArray = new float[normalsArray.size()];
        for (int i = 0; i < normalsArray.size(); i++) {
            vertArray[i] = verticesArray.get(i);
            normArray[i] = normalsArray.get(i);
        }

        return loader.loadToVAO(vertArray, null, normArray);
    }
}
