package za.co.discovery.assignment.ws.api.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import za.co.discovery.assignment.schema.RoutePathRequest;
import za.co.discovery.assignment.schema.RoutePathResponse;
import za.co.discovery.assignment.ws.api.service.InterstellarWebService;

/**
 * @author Exalt Pawarikanda
 */
@RequiredArgsConstructor
@Endpoint
public class InterstellarTransportEndpoint {

    private static final String NAMESPACE = "http://www.discovery.com/spring/soap/api/interstellarService";
    private final InterstellarWebService interstellarWebService;

    @PayloadRoot(namespace = NAMESPACE, localPart = "RoutePathRequest")
    @ResponsePayload
    public RoutePathResponse getShortestRoute(@RequestPayload RoutePathRequest request) {
        return interstellarWebService.findShortestRoute(request);
    }
}
