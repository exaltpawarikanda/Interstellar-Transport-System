package za.co.discovery.assignment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Exalt Pawarikanda
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "traffic")
public class Traffic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String startNode;
    private String endNode;
    private Double trafficDelay;
}
