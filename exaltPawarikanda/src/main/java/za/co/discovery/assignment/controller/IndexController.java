package za.co.discovery.assignment.controller;

/**
 * @author Exalt Pawarikanda
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    String index() {
        return "home";
    }

    @RequestMapping("/home")
    String home() {
        return "home";
    }

}
