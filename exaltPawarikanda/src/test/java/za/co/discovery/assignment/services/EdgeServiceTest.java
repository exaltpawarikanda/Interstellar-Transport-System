package za.co.discovery.assignment.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import za.co.discovery.assignment.domain.Edge;
import za.co.discovery.assignment.repository.EdgeRepository;
import za.co.discovery.assignment.services.impl.EdgeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Exalt Pawarikanda
 */
@ExtendWith(MockitoExtension.class)
class EdgeServiceTest {

    @Mock
    private EdgeRepository edgeRepository;

    @Autowired
    @InjectMocks
    private EdgeServiceImpl edgeService;

    private Edge edge1;
    private Edge edge2;
    List<Edge> edgeList;

    @BeforeEach
    public void setUp() {
        edgeList = new ArrayList<>();
        edge1 = new Edge(100, "G'", "S", 20.0f);
        edge2 = new Edge(101, "O'", "P", 10.0f);
        edgeList.add(edge1);
        edgeList.add(edge2);
    }

    @Test
    void createEdge() {
        when(edgeRepository.save(any())).thenReturn(edge1);
        edgeService.createEdge(edge1);
        verify(edgeRepository, times(1)).save(any());
    }

    @Test
    void getAllEdges() {
        edgeRepository.save(edge1);
        when(edgeRepository.findAll()).thenReturn(edgeList);
        List<Edge> edgeList1 = edgeService.getAllRoutes();
        assertEquals(edgeList1, edgeList);
        verify(edgeRepository, times(1)).save(edge1);
        verify(edgeRepository, times(1)).findAll();
    }

    @Test
    void getEdgesById() {
        Mockito.when(edgeRepository.findById(edge1.getId())).thenReturn(Optional.ofNullable(edge1));
        Assertions.assertThat(edgeRepository.findById(edge1.getId()).get()).isEqualTo(edge1);
    }

    @AfterEach
    public void tearDown() {
        edge1 = edge2 = null;
        edgeList = null;
    }
}