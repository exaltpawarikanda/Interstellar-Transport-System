package za.co.discovery.assignment.mappers;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import za.co.discovery.assignment.domain.Edge;
import za.co.discovery.assignment.dto.EdgeDto;
import za.co.discovery.assignment.repository.VertexRepository;

/**
 * @author Exalt Pawarikanda
 */
@RequiredArgsConstructor
@Controller
public class EdgeMapper {

    private final VertexMapper vertexMapper;
    private final VertexRepository vertexRepository;

    public EdgeDto edgeToEdgeDto(Edge route){
        EdgeDto edgeDto = EdgeDto.builder()
                .source(vertexMapper.vertexToVertexDto(vertexRepository.findByNode(route.getStartNode())))
                .destination(vertexMapper.vertexToVertexDto(vertexRepository.findByNode(route.getEndNode())))
                .weight(route.getDistance())
                .build();
        return edgeDto;
    }

    public Edge edgeDtoToEdge(EdgeDto route){
        Edge edge = Edge.builder()
                .startNode(route.getSource().getNode())
                .endNode(route.getDestination().getNode())
                .distance(route.getWeight())
                .build();
        return edge;
    }

}
