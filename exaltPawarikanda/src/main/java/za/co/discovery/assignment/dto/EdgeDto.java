package za.co.discovery.assignment.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Exalt Pawarikanda
 */
@Data
@NoArgsConstructor
@Builder
public class EdgeDto {
    private VertexDto source;
    private VertexDto destination;
    private Float weight;

    public EdgeDto(VertexDto source, VertexDto destination, Float distance) {
        this.source = source;
        this.destination = destination;
        this.weight = distance;
    }

    @Override
    public String toString() {
        return "EdgeDto{" +
                "source=" + source +
                ", destination=" + destination +
                ", weight=" + weight +
                '}';
    }
}
