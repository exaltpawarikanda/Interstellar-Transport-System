package za.co.discovery.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.discovery.assignment.domain.Vertex;

import java.util.List;

/**
 * @author Exalt Pawarikanda
 */
@Repository
public interface VertexRepository extends JpaRepository<Vertex,Long> {
    Vertex findByNode(String name);
    Vertex findNodeByNode(String node);
    @Query(value = "SELECT * FROM vertex ORDER BY id ASC",nativeQuery = true)
    List<Vertex> findAll();
}
