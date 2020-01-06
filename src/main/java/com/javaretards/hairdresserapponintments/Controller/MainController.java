package com.javaretards.hairdresserapponintments.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author mateusz
 */
@Controller
public class MainController {
    
    @GetMapping({"/"})
    public String indexAction(Model model){
        return "main";
    }
}
