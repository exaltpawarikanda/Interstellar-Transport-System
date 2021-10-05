package za.co.discovery.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.discovery.assignment.domain.Traffic;

/**
 * @author Exalt Pawarikanda
 */
public interface TrafficRepository extends JpaRepository<Traffic,Integer> {
}
