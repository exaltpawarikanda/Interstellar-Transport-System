package za.co.discovery.assignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import za.co.discovery.assignment.domain.Vertex;
import za.co.discovery.assignment.model.PathModel;
import za.co.discovery.assignment.services.api.VertexService;

import java.util.List;

/**
 * @author Exalt Pawarikanda
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/nodes")
public class VertexController {

    private final VertexService vertexService;

    @RequestMapping
    public String  listAllNodes(Model model) {
        List<Vertex> planets = vertexService.getAllNodes();
        model.addAttribute("planets",planets);
        return "planets/planets";
    }

    @GetMapping(value="/create")
    public String createPlanet(Model model) {
        model.addAttribute("planet", new Vertex());
        return "planets/create";
    }

    @PostMapping(value="/save")
    public String savePlanet(Model model, @ModelAttribute Vertex vertex) {
        //Vertex newPlanet = new Vertex();
        System.out.println("Passed on Vertex-----------------------------------------" + vertex);
        vertexService.createVertex(vertex);
        return "redirect:/nodes";
    }
}
