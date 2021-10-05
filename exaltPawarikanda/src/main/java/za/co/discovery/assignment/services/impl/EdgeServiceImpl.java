package za.co.discovery.assignment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.domain.Edge;
import za.co.discovery.assignment.repository.EdgeRepository;
import za.co.discovery.assignment.services.api.EdgeService;

import java.util.List;
import java.util.Optional;

/**
 * @author Exalt Pawarikanda
 */
@RequiredArgsConstructor
@Service
public class EdgeServiceImpl implements EdgeService {

    private final EdgeRepository edgeRepository;

    @Override
    public List<Edge> getAllRoutes() {
        return edgeRepository.findAll();
    }

    @Override
    public Edge createEdge(Edge newEdge) {
        return edgeRepository.save(newEdge);
    }

    @Override
    public Optional<Edge> getEdgeById(int id) {
        return edgeRepository.findById(id);
    }

    @Override
    public Edge updateEdge(int id, Edge edge) {
        Edge currentEdge = edgeRepository.getById(id);
        currentEdge.setStartNode(edge.getStartNode());
        currentEdge.setEndNode(edge.getEndNode());
        currentEdge.setDistance(edge.getDistance());
        return edgeRepository.save(currentEdge);
    }

    @Override
    public void deleteEdge(int id) {
        Edge currentEdge = edgeRepository.getById(id);
        if (currentEdge != null) edgeRepository.deleteById(id);
    }
}
