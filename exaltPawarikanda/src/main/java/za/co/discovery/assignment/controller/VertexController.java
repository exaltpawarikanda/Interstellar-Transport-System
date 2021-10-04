package za.co.discovery.assignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import za.co.discovery.assignment.domain.Vertex;
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
        return "planets";
    }
}
