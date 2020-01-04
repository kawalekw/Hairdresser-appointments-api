package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.ServiceOption;
import com.javaretards.hairdresserapponintments.Repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mateusz
 */
@Controller
@AllArgsConstructor
@RequestMapping("/config")
public class ConfigController {
    private final ServiceRepository sr;
    
    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public String viewServicesAction(Model model){
        model.addAttribute("services", sr.findAll());
        return "services";
    }
    
    @RequestMapping(value = "/services", method = RequestMethod.POST)
    public String addServiceAction(@RequestParam("name") String name, @RequestParam("duration") int duration){
        sr.save(new ServiceOption(name,duration));
        return "redirect:/config/services";
    }

    @RequestMapping(value = "/services/delete/{id}", method = RequestMethod.GET)
    public String deleteServiceAction(@PathVariable("id") Long id){
        sr.deleteById(id);
        return "redirect:/config/services";
    }
}
