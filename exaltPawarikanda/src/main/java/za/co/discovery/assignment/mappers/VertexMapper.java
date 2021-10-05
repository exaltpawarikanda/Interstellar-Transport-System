package za.co.discovery.assignment.mappers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import za.co.discovery.assignment.domain.Vertex;
import za.co.discovery.assignment.dto.VertexDto;

/**
 * @author Exalt Pawarikanda
 */
@Slf4j
@Component
public class VertexMapper {
    public VertexDto vertexToVertexDto(Vertex vertex) {
        VertexDto vertexDto = VertexDto.builder()
                .name(vertex.getName())
                .node(vertex.getNode())
                .build();
        return vertexDto;
    }

    public Vertex vertexDtoToVertex(VertexDto vertexDto) {
        Vertex vertex = Vertex.builder()
                .name(vertexDto.getName())
                .node(vertexDto.getNode())
                .build();
        return vertex;
    }

}
