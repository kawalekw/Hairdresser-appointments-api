package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.Client;
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
        Client user = new Client("Mateusz","123456789");
        model.addAttribute("user", user);
        return "hello";
    }
}
