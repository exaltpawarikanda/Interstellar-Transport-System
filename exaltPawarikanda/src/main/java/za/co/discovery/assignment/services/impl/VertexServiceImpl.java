package za.co.discovery.assignment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.domain.Vertex;
import za.co.discovery.assignment.repository.VertexRepository;
import za.co.discovery.assignment.services.api.VertexService;

import java.util.List;

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
}
