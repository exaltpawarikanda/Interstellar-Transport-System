package za.co.discovery.assignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import za.co.discovery.assignment.domain.Vertex;
import za.co.discovery.assignment.model.FinalPathModel;
import za.co.discovery.assignment.model.PathModel;
import za.co.discovery.assignment.services.api.VertexService;
import za.co.discovery.assignment.util.ShortestPathService;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Exalt Pawarikanda
 */
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final VertexService vertexService;
    private final ShortestPathService shortestPathService;

    @GetMapping("/")
    public String shortestPathForm(Model model) {
        List<Vertex> planets = vertexService.getAllNodes();
        PathModel pathModel = new PathModel();
        Vertex origin = planets.get(0);
        pathModel.setVertexName(origin.getName());
        model.addAttribute("path", pathModel);
        model.addAttribute("planets", planets);
        return "home";
    }

    @PostMapping(value = "/")
    public String shortestSubmit(@ModelAttribute PathModel pathModel, Model model) {
        FinalPathModel finalPathModel = FinalPathModel.builder()
                .sourceVertex(pathModel.getVertexName())
                .destinationVertex(pathModel.getSelectedVertex())
                .thePath(new LinkedList<>())
                .build();
        LinkedList<String> route = shortestPathService.initializeGraph(pathModel.getVertexName(), pathModel.getSelectedVertex(), pathModel.isTraffic());
        finalPathModel.setThePath(route);
        List<Vertex> planets = vertexService.getAllNodes();
        pathModel = new PathModel();
        Vertex origin = planets.get(0);
        pathModel.setVertexName(origin.getName());
        model.addAttribute("path", pathModel);
        model.addAttribute("planets", planets);
        model.addAttribute("finalPath", finalPathModel);
        return "home";
    }
}
