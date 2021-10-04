package za.co.discovery.assignment.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Exalt Pawarikanda
 */
@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vertex")
public class Vertex {
    @GeneratedValue
    @Id
    private Long id;
    private String node;
    private String name;
}
