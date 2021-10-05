package za.co.discovery.assignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import za.co.discovery.assignment.domain.Edge;
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
        return "traffic/traffic";
    }
    @GetMapping(value="/create")
    public String createTraffic(Model model) {
        model.addAttribute("traffic", new Traffic());
        return "traffic/create";
    }

    @PostMapping(value="/save")
    public String saveTraffic(Model model, @ModelAttribute Traffic traffic) {
        trafficService.createTraffic(traffic);
        return "redirect:/traffic";
    }

    @GetMapping(value="/view/{trafficId}")
    public String showTraffic(@PathVariable int trafficId, Model model) {
        model.addAttribute("traffic", trafficService.getTrafficById(trafficId).get());
        return "traffic/view";
    }

    @GetMapping(value="/edit/{trafficId}")
    public String editTraffic(@PathVariable int trafficId, Model model) {
        model.addAttribute("traffic", trafficService.getTrafficById(trafficId).get());
        return "/traffic/edit";
    }

    @PostMapping(value="/update/{trafficId}")
    public String updateTraffic(@PathVariable int trafficId,@ModelAttribute Traffic traffic, Model model) {
        trafficService.updateTraffic(trafficId,traffic);
        return "redirect:/traffic";
    }

    @RequestMapping(value="/delete/{trafficId}")
    public String deleteTraffic(@PathVariable int trafficId) {
        trafficService.deleteTraffic(trafficId);
        return "redirect:/traffic";
    }
}
