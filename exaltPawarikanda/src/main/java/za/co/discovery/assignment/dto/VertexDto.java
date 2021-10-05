package za.co.discovery.assignment.dto;

import lombok.*;

/**
 * @author Exalt Pawarikanda
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VertexDto {
    private int id;
    private String name;
    private String node;
    private String previousVertex;
    private Double shortestDistance;

    public VertexDto(String name, String node) {
        this.name = name;
        this.node = node;
    }

    public VertexDto(String name) {
        this.name = name;
    }
}
