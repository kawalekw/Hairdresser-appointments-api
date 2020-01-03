package com.javaretards.hairdresserapponintments.Controller;

import Entity.Client;
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
        Client user = new Client("Mateusz","669324633");
        model.addAttribute("user", user);
        return "hello";
    }
}
