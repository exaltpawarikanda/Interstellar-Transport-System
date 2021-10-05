package za.co.discovery.assignment.ws.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.schema.RoutePathRequest;
import za.co.discovery.assignment.schema.RoutePathResponse;
import za.co.discovery.assignment.util.ShortestPathService;

import java.util.List;

/**
 * @author Exalt Pawarikanda
 */
@AllArgsConstructor
@Service
public class InterstellarWebService {

    private final ShortestPathService shortestPathService;

    public RoutePathResponse findShortestRoute(RoutePathRequest routePathRequest) {
        RoutePathResponse routePathResponse = new RoutePathResponse();
        List<String> path = shortestPathService.initializeGraph(routePathRequest.getStartPlanet(), routePathRequest.getDestinationPlanet(), routePathRequest.isIsTraffic());
        routePathResponse.setStartPlanet(routePathRequest.getStartPlanet());
        routePathResponse.setDestinationPlanet(routePathRequest.getDestinationPlanet());
        path.stream().forEach(p -> routePathResponse.getRoute().add(p));
        return routePathResponse;
    }
}
