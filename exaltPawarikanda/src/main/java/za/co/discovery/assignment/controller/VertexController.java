package za.co.discovery.assignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

    @GetMapping
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

    @GetMapping(value="/view/{vertexId}")
    public String showVertex(@PathVariable Long vertexId, Model model) {
        model.addAttribute("vertex", vertexService.getVertexById(vertexId).get());
        return "planets/view";
    }

    @GetMapping(value="/edit/{vertexId}")
    public String editVertex(@PathVariable Long vertexId, Model model) {
        model.addAttribute("planet", vertexService.getVertexById(vertexId).get());
        return "/planets/edit";
    }

    @RequestMapping(value="/update/{vertexId}")
    public String updateVertex(@PathVariable Long vertexId,@ModelAttribute Vertex vertex, Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("vertexId", vertex.getId());
        vertexService.updateVertex(vertexId,vertex);
        return "redirect:/nodes";
    }

    @RequestMapping(value="/delete/{vertexId}")
    public String deleteVertex(@PathVariable Long vertexId) {
        vertexService.deleteVertex(vertexId);
        return "redirect:/nodes";
    }
}
