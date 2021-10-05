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
    public String  shortestPathForm(Model model) {
        List<Vertex> planets = vertexService.getAllNodes();
        PathModel pathModel = new PathModel();
        Vertex origin = planets.get(0);
        pathModel.setVertexName(origin.getName());
        model.addAttribute("path", pathModel);
        model.addAttribute("planets", planets);
        return "home";
    }


    @GetMapping("/shortestPath/{startPlanet}/{destinationPlanet}")
    public String calculateShortestPath(Model model, @PathVariable( "startPlanet") String startPlanet,@PathVariable( "destinationPlanet") String destinationPlanet) {
        shortestPathService.initializeGraph(startPlanet,destinationPlanet);
        return "home";
    }
    @PostMapping(value = "/")
    public String shortestSubmit(@ModelAttribute PathModel pathModel, Model model) {
        FinalPathModel finalPathModel = FinalPathModel.builder()
                .sourceVertex(pathModel.getVertexName())
                .destinationVertex(pathModel.getSelectedVertex())
                .thePath(new LinkedList<>())
                .build();
        StringBuilder path = new StringBuilder();
       /* Graph graph = graphService.selectGraph();
        if (pathModel.isTrafficAllowed()) {
            graph.setTrafficAllowed(true);
        }
        if (pathModel.isUndirectedGraph()) {
            graph.setUndirectedGraph(true);
        }*/
        //Vertex source = vertexService.getVertexByName(pathModel.getVertexName());
        //Vertex destination = vertexService.getVertexByName(pathModel.getSelectedVertex());
        //
        LinkedList<String> route =shortestPathService.initializeGraph(pathModel.getVertexName(),pathModel.getSelectedVertex());
        finalPathModel.setThePath(route);
       // shortestPathService.run(source);
   /*     LinkedList<Vertex> paths = shortestPathService.getPath(destination);
        if (paths != null) {
            for (Vertex v : paths) {
                path.append(v.getName() + " (" + v.getVertexId() + ")");
                path.append("\t");
            }
        } else if (source != null && destination != null && source.getVertexId().equals(destination.getVertexId())) {
            path.append(PATH_NOT_NEEDED + source.getName());
        } else {
            path.append(Constants.PATH_NOT_AVAILABLE);
        }
        pathModel.setThePath(path.toString());
        pathModel.setSelectedVertexName(destination.getName());
        model.addAttribute("shortest", pathModel);*/
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
