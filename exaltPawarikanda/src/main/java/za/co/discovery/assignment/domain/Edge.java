package za.co.discovery.assignment.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Exalt Pawarikanda
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "routes")
public class Edge {
    @Id
    private int id;
    private String startNode;
    private String endNode;
    private Double distance;
}
