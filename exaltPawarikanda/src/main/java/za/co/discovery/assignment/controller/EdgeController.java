package za.co.discovery.assignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import za.co.discovery.assignment.domain.Edge;
import za.co.discovery.assignment.services.api.EdgeService;

import java.util.List;

/**
 * @author Exalt Pawarikanda
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/routes")
public class EdgeController {

    private final EdgeService edgeService;

    @RequestMapping
    public String  listAllRoutes(Model model) {
        List<Edge> routes = edgeService.getAllRoutes();
        model.addAttribute("routes",routes);
        return "routes/routes";
    }

    @GetMapping(value="/create")
    public String createRoute(Model model) {
        model.addAttribute("route", new Edge());
        return "routes/create";
    }

    @PostMapping(value="/save")
    public String saveRoute(Model model, @ModelAttribute Edge edge) {
        edgeService.createEdge(edge);
        return "redirect:/routes";
    }

    @GetMapping(value="/view/{routeId}")
    public String showRoute(@PathVariable("routeId") int routeId, Model model) {
        model.addAttribute("route", edgeService.getEdgeById(routeId).get());
        return "routes/view";
    }

    @GetMapping(value="/edit/{routeId}")
    public String editRoute(@PathVariable("routeId") int routeId, Model model) {
        model.addAttribute("route", edgeService.getEdgeById(routeId).get());
        return "/routes/edit";
    }

    @RequestMapping(value="/update/{routeId}")
    public String updateRoute(@PathVariable("routeId") int routeId,@ModelAttribute Edge edge, Model model) {
        edgeService.updateEdge(routeId,edge);
        return "redirect:/routes";
    }

    @RequestMapping(value="/delete/{routeId}")
    public String deleteRoute(@PathVariable("routeId") int routeId) {
        edgeService.deleteEdge(routeId);
        return "redirect:/routes";
    }

}
