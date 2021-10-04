package za.co.discovery.assignment.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Exalt Pawarikanda
 */
@Data
@Entity
@Table(name = "traffic")
public class Traffic {

    @Id
    private int id;
    private String startNode;
    private String endNode;
    private Double trafficDelay;
}
