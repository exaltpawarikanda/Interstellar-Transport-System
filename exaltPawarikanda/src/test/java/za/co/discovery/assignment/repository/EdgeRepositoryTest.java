package za.co.discovery.assignment.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Exalt Pawarikanda
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class EdgeRepositoryTest {

    @Test
    void findRoutesByStartNode() {
    }
}