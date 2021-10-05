package za.co.discovery.assignment.services.impl;

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
import za.co.discovery.assignment.domain.Vertex;
import za.co.discovery.assignment.repository.VertexRepository;

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
class VertexServiceTest {

    @Mock
    private VertexRepository vertexRepository;

    @Autowired
    @InjectMocks
    private VertexServiceImpl vertexService;

    private Vertex vertex1;
    private Vertex vertex2;
    List<Vertex> vertexList;

    @BeforeEach
    public void setUp() {
        vertexList = new ArrayList<>();
        vertex1 = new Vertex(100L, "G'","New Planet");
        vertex2 = new Vertex(101L, "O'","Other Planet");
        vertexList.add(vertex1);
        vertexList.add(vertex2);
    }

    @Test
    void createVertex() {
        when(vertexRepository.save(any())).thenReturn(vertex1);
        vertexService.createVertex(vertex1);
        verify(vertexRepository,times(1)).save(any());
    }

    @Test
    void getAllNodes() {
        vertexRepository.save(vertex1);
        when(vertexRepository.findAll()).thenReturn(vertexList);
        List<Vertex> vertexList1 =vertexService.getAllNodes();
        assertEquals(vertexList1,vertexList);
        verify(vertexRepository,times(1)).save(vertex1);
        verify(vertexRepository,times(1)).findAll();
    }

    @Test
    void getVertexById() {
        Mockito.when(vertexRepository.findById(100L)).thenReturn(Optional.ofNullable(vertex1));
        Assertions.assertThat(vertexService.getVertexById(vertex1.getId()).get()).isEqualTo(vertex1);
    }

    @AfterEach
    public void tearDown() {
        vertex1 = vertex2 = null;
        vertexList = null;
    }
}