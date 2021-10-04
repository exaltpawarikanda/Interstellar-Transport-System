package za.co.discovery.assignment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.domain.Traffic;
import za.co.discovery.assignment.repository.TrafficRepository;
import za.co.discovery.assignment.services.api.TrafficService;

import java.util.List;

/**
 * @author Exalt Pawarikanda
 */
@RequiredArgsConstructor
@Service
public class TrafficServiceImpl implements TrafficService {
    private final TrafficRepository trafficRepository;

    @Override
    public List<Traffic> getAllTraffic() {
        return trafficRepository.findAll();
    }
}
