package za.co.discovery.assignment.util;

import lombok.Data;
import za.co.discovery.assignment.domain.Traffic;
import za.co.discovery.assignment.dto.EdgeDto;
import za.co.discovery.assignment.dto.VertexDto;

import java.util.List;

/**
 * @author Exalt Pawarikanda
 */
@Data
public class Graph {
    private final List<VertexDto> vertexes;
    private final List<EdgeDto> edges;
    private List<Traffic> traffics;
    private boolean undirectedGraph;
    private boolean trafficAllowed;

    public Graph(List<VertexDto> vertexes, List<EdgeDto> edges, List<Traffic> trafficList) {
        this.vertexes = vertexes;
        this.edges = edges;
        this.traffics = trafficList;
    }

    public Graph(List<VertexDto> vertexes, List<EdgeDto> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }
}
