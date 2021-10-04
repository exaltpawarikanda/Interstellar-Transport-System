package za.co.discovery.assignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import za.co.discovery.assignment.domain.Traffic;
import za.co.discovery.assignment.services.api.TrafficService;

import java.util.List;

/**
 * @author Exalt Pawarikanda
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/traffic")
public class TrafficController {

    private final TrafficService trafficService;

    @RequestMapping
    public String  listAllTraffic(Model model) {
        List<Traffic> traffic = trafficService.getAllTraffic();
        model.addAttribute("traffic",traffic);
        return "traffic";
    }
}
