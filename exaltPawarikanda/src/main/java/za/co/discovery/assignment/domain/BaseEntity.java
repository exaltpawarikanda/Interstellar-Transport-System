package za.co.discovery.assignment.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.sql.Timestamp;

/**
 * @author Exalt Pawarikanda
 */
@Data
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    public BaseEntity(Long version,
                      Timestamp createdAt,
                      Timestamp lastUpdatedAt) {
        this.version = version;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @Version
    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(updatable = false)
    private Timestamp lastUpdatedAt;
}
