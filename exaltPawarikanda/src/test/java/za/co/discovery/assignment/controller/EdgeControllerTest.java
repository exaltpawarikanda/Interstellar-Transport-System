package za.co.discovery.assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import za.co.discovery.assignment.domain.Edge;
import za.co.discovery.assignment.services.api.EdgeService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Exalt Pawarikanda
 */

@WebMvcTest(EdgeController.class)
class EdgeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EdgeService edgeService;


    Edge validEdgeDto;

    @BeforeEach
    public void setUp() {
        validEdgeDto = Edge.builder()
                .id(300)
                .startNode("G'")
                .endNode("X'")
                .distance(0.44f)
                .build();
    }

    @Test
    void createRoute() throws Exception {
        Edge edgeDto = validEdgeDto;
        String edgeDtoJson = objectMapper.writeValueAsString(edgeDto);
        given(edgeService.createEdge(any())).willReturn(validEdgeDto);
        mockMvc.perform(post("/routes/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(edgeDtoJson))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void getEdgeById() throws Exception {
        given(edgeService.getEdgeById(300)).willReturn(Optional.ofNullable(validEdgeDto));
        mockMvc.perform(get("/routes/view/" + 300)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void updateRoute() throws Exception {
        Edge edgeDto = updatedEdge;
        String edgeDtoJson = objectMapper.writeValueAsString(edgeDto);

        given(edgeService.updateEdge(300, updatedEdge)).willReturn(validEdgeDto);

        mockMvc.perform(put("/routes/update/" + 300)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(edgeDtoJson))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void deleteRoute() throws Exception {
        mockMvc.perform(delete("/routes/delete/" + 200)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
    }

    Edge updatedEdge = Edge.builder()
            .id(300)
            .startNode("G'")
            .endNode("O'")
            .distance(0.44f)
            .build();

}