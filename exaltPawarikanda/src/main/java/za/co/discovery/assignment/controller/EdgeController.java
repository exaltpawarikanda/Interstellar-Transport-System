package za.co.discovery.assignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return "routes";
    }
}
