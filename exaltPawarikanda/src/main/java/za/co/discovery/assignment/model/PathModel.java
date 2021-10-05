package za.co.discovery.assignment.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Exalt Pawarikanda
 */
@Data
public class PathModel implements Serializable {

    private String selectedVertex;
    private String selectedVertexName;
    private String vertexId;
    private String vertexName;
    private String thePath;
    private String sourceVertex;
    private String destinationVertex;
    private boolean traffic;

}
