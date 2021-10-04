package za.co.discovery.assignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import za.co.discovery.assignment.domain.Vertex;
import za.co.discovery.assignment.services.api.VertexService;
import za.co.discovery.assignment.util.ShortestPathService;

import java.util.List;

/**
 * @author Exalt Pawarikanda
 */
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final VertexService vertexService;
    private final ShortestPathService shortestPathService;

    @GetMapping("/shortestPath")
    public String  shortestPathForm(Model model) {
        List<Vertex> planets = vertexService.getAllNodes();
        model.addAttribute("planets",planets);
        return "home";
    }


    @GetMapping("/shortestPath/{startPlanet}/{destinationPlanet}")
    public String calculateShortestPath(Model model, @PathVariable( "startPlanet") String startPlanet,@PathVariable( "destinationPlanet") String destinationPlanet) {
        shortestPathService.initializeGraph(startPlanet,destinationPlanet);
        return "home";
    }
}
