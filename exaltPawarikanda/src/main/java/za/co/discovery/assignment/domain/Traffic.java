package za.co.discovery.assignment.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Exalt Pawarikanda
 */
@Data
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
