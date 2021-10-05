package za.co.discovery.assignment.services.api;


import za.co.discovery.assignment.domain.Edge;

import java.util.List;
import java.util.Optional;

/**
 * @author Exalt Pawarikanda
 */
public interface EdgeService {
    List<Edge> getAllRoutes();
    Edge createEdge(Edge newEdge);
    Optional<Edge> getEdgeById(int id);
    Edge updateEdge(int id ,Edge edge);
    void deleteEdge(int id);
}
