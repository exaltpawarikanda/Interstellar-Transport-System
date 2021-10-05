package za.co.discovery.assignment.services.api;

import za.co.discovery.assignment.domain.Vertex;

import java.util.List;
import java.util.Optional;

/**
 * @author Exalt Pawarikanda
 */
public interface VertexService {

    List<Vertex> getAllNodes();
    Vertex getVertexByName(String name);
    Vertex createVertex( Vertex newVertex);
    Optional<Vertex> getVertexById(long id);
    Vertex updateVertex(Long id ,Vertex vertex);
    void deleteVertex(Long id);
}
