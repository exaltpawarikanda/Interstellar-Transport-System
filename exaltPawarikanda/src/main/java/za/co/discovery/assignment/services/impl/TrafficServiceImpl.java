package za.co.discovery.assignment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.domain.Edge;
import za.co.discovery.assignment.domain.Traffic;
import za.co.discovery.assignment.repository.TrafficRepository;
import za.co.discovery.assignment.services.api.TrafficService;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Traffic createTraffic(Traffic newTraffic) {
        return trafficRepository.save(newTraffic);
    }

    @Override
    public Optional<Traffic> getTrafficById(int id) {
        return trafficRepository.findById(id);
    }

    @Override
    public Traffic updateTraffic(int id, Traffic traffic) {
        Traffic currentTraffic = trafficRepository.getById(id);
        currentTraffic.setStartNode(traffic.getStartNode());
        currentTraffic.setEndNode(traffic.getEndNode());
        currentTraffic.setTrafficDelay(traffic.getTrafficDelay());
        return trafficRepository.save(currentTraffic);
    }

    @Override
    public void deleteTraffic(int id) {
        Traffic currentTraffic = trafficRepository.getById(id);
        if(currentTraffic != null)trafficRepository.deleteById(id);
    }
}
