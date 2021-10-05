package za.co.discovery.assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import za.co.discovery.assignment.domain.Vertex;
import za.co.discovery.assignment.services.api.VertexService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Exalt Pawarikanda
 */
@WebMvcTest(VertexController.class)
class VertexControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    VertexService vertexService;


    Vertex validVertex;

    @BeforeEach
    public void setUp(){
        validVertex= Vertex.builder()
                .id(300L)
                .node("G'")
                .name("New Planet")
                .build();
    }

    @Test
    void createVertex() throws Exception {
        Vertex vertex = validVertex;
        String vertexJson = objectMapper.writeValueAsString(vertex);
        given(vertexService.createVertex(any())).willReturn(validVertex);
        mockMvc.perform(post("/nodes/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vertexJson))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void getVertexById() throws Exception {
        given(vertexService.getVertexById(300)).willReturn(Optional.ofNullable(validVertex));
        mockMvc.perform(get("/nodes/view/" + 300 )
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void updateVertex() throws Exception {
        Vertex vertex = updatedVertex;
        String edgeDtoJson = objectMapper.writeValueAsString(vertex);

        given(vertexService.updateVertex(300L,updatedVertex)).willReturn(validVertex);

        mockMvc.perform(put("/nodes/update/" + 300)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(edgeDtoJson))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void deleteVertex() throws Exception{
        mockMvc.perform(delete("/nodes/delete/" + 200)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
    }

    Vertex updatedVertex = Vertex.builder()
            .id(300L)
            .node("G'")
            .name("The New Planet")
            .build();

}