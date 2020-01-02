package com.javaretards.hairdresserapponintments.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author mateusz
 */
@Controller
public class HelloController {
    
    @GetMapping({"/","/hello"})
    public String helloAction(Model model){
        String name="Janusz";
        model.addAttribute("name", name);
        return "hello";
    }
}
