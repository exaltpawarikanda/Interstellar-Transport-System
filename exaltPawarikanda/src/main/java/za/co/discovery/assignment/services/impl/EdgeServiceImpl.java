package za.co.discovery.assignment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.domain.Edge;
import za.co.discovery.assignment.repository.EdgeRepository;
import za.co.discovery.assignment.services.api.EdgeService;

import java.util.List;

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
}
