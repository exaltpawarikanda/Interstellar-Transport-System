package za.co.discovery.assignment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Exalt Pawarikanda
 */
@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vertex")
public class Vertex  extends BaseEntity{
    @GeneratedValue
    @Id
    private Long id;
    @NotNull
    @NotBlank
    private String node;
    @NotNull
    @NotBlank
    private String name;
}
