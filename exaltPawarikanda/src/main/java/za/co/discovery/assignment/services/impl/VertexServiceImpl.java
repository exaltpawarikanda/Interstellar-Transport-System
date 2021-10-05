package za.co.discovery.assignment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.domain.Vertex;
import za.co.discovery.assignment.repository.VertexRepository;
import za.co.discovery.assignment.services.api.VertexService;

import java.util.List;
import java.util.Optional;

/**
 * @author Exalt Pawarikanda
 */
@RequiredArgsConstructor
@Service
public class VertexServiceImpl implements VertexService {

    private final VertexRepository vertexRepository;

    @Override
    public List<Vertex> getAllNodes() {
        return vertexRepository.findAll();
    }

    @Override
    public Vertex getVertexByName(String name) {
        return vertexRepository.findByName(name);
    }

    @Override
    public Vertex createVertex(Vertex newVertex) {
        return vertexRepository.save(newVertex);
    }

    @Override
    public Optional<Vertex> getVertexById(long id) {
        return vertexRepository.findById(id);
    }

}
