/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaretards.hairdresserapponintments.Controller;

import Entity.Service;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author mateusz
 */
@Controller
public class ConfigController {
    
    @GetMapping("/services")
    public String viewServicesAction(Model model){
        ArrayList<Service> services = new ArrayList();
        services.add(new Service("Farba", 60));
        services.add(new Service("Strzyżenie męskie", 15));
        model.addAttribute("services", services);
        return "services";
    }
}
