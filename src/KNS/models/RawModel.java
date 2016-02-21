package KNS.models;

/**
 * Created by kodoo on 15.02.2016.
 */
public class RawModel {

    private int voaID;
    private int vertexCount;

    public RawModel(int voaID, int vertexCount) {
        this.voaID = voaID;
        this.vertexCount = vertexCount;
    }

    public int getVoaID() {
        return voaID;
    }

    public int getVertexCount() {
        return vertexCount;
    }

}
