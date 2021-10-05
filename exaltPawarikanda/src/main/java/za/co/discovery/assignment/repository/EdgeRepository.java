package za.co.discovery.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.discovery.assignment.domain.Edge;

import java.util.List;

/**
 * @author Exalt Pawarikanda
 */
@Repository
public interface EdgeRepository extends JpaRepository<Edge, Integer> {
    List<Edge> findRoutesByStartNode(String startNode);
}
