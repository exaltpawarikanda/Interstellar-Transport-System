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
import za.co.discovery.assignment.domain.Traffic;
import za.co.discovery.assignment.repository.TrafficRepository;
import za.co.discovery.assignment.services.impl.TrafficServiceImpl;

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
class TrafficServiceTest {


    @Mock
    private TrafficRepository trafficRepository;

    @Autowired
    @InjectMocks
    private TrafficServiceImpl trafficService;

    private Traffic traffic1;
    private Traffic traffic2;
    List<Traffic> trafficList;

    @BeforeEach
    public void setUp() {
        trafficList = new ArrayList<>();
        traffic1 = new Traffic(100, "G'","C",20.0);
        traffic2 = new Traffic(101, "O'","X",10.0);
        trafficList.add(traffic1);
        trafficList.add(traffic2);
    }

    @Test
    void createTraffic() {
        when(trafficRepository.save(any())).thenReturn(traffic1);
        trafficService.createTraffic(traffic1);
        verify(trafficRepository,times(1)).save(any());
    }

    @Test
    void getAllTraffic() {
        trafficRepository.save(traffic1);
        when(trafficRepository.findAll()).thenReturn(trafficList);
        List<Traffic> trafficList1 =trafficService.getAllTraffic();
        assertEquals(trafficList1,trafficList);
        verify(trafficRepository,times(1)).save(traffic1);
        verify(trafficRepository,times(1)).findAll();
    }

  @Test
    void getTrafficById() {
        Mockito.when(trafficRepository.findById(traffic1.getId())).thenReturn(Optional.ofNullable(traffic1));
        Assertions.assertThat(trafficRepository.findById(traffic1.getId()).get()).isEqualTo(traffic1);
    }

    @AfterEach
    public void tearDown() {
        traffic1 = traffic2 = null;
        trafficList = null;
    }
}