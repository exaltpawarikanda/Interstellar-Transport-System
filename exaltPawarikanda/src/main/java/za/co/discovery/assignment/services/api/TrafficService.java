package za.co.discovery.assignment.services.api;


import za.co.discovery.assignment.domain.Edge;
import za.co.discovery.assignment.domain.Traffic;

import java.util.List;
import java.util.Optional;

/**
 * @author Exalt Pawarikanda
 */
public interface TrafficService {
    List<Traffic> getAllTraffic();
    Traffic createTraffic(Traffic newTraffic);
    Optional<Traffic> getTrafficById(int id);
    Traffic updateTraffic(int id ,Traffic edge);
    void deleteTraffic(int id);
}
