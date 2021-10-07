package za.co.discovery.assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import za.co.discovery.assignment.domain.Traffic;
import za.co.discovery.assignment.services.api.TrafficService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Exalt Pawarikanda
 */
@WebMvcTest(TrafficController.class)
class TrafficControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    TrafficService trafficService;


    Traffic validTraffic;

    @BeforeEach
    public void setUp() {
        validTraffic = Traffic.builder()
                .id(300)
                .startNode("G'")
                .endNode("X'")
                .trafficDelay(0.44f)
                .build();
    }

    @Test
    void createTraffic() throws Exception {
        Traffic traffic = validTraffic;
        String trafficJson = objectMapper.writeValueAsString(traffic);
        given(trafficService.createTraffic(any())).willReturn(validTraffic);
        mockMvc.perform(post("/traffic/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(trafficJson))
                .andExpect(status().is3xxRedirection());
    }


    @Test
    void getTrafficById() throws Exception {
        given(trafficService.getTrafficById(300)).willReturn(Optional.ofNullable(validTraffic));
        mockMvc.perform(get("/traffic/view/" + 300)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void updateTraffic() throws Exception {
        Traffic traffic = updatedTraffic;
        String edgeDtoJson = objectMapper.writeValueAsString(traffic);

        given(trafficService.updateTraffic(300, updatedTraffic)).willReturn(validTraffic);

        mockMvc.perform(put("/traffic/update/" + 300)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(edgeDtoJson))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void deleteTraffic() throws Exception {
        given(trafficService.updateTraffic(300, updatedTraffic)).willReturn(validTraffic);
        mockMvc.perform(delete("/traffic/delete/" + 200)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
    }

    Traffic updatedTraffic = Traffic.builder()
            .id(300)
            .startNode("G'")
            .endNode("O'")
            .trafficDelay(0.44f)
            .build();

}