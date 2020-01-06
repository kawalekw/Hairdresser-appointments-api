package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.ServiceOption;
import com.javaretards.hairdresserapponintments.Repository.OpenHoursRepositiory;
import com.javaretards.hairdresserapponintments.Repository.ServiceRepository;
import java.util.Optional;
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
    private final OpenHoursRepositiory ohr;
    
    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public String viewServicesAction(Model model){
        model.addAttribute("services", sr.findByDeletedFalse());
        return "services";
    }
    
    @RequestMapping(value = "/services", method = RequestMethod.POST)
    public String addServiceAction(@RequestParam("name") String name, @RequestParam("duration") int duration){
        sr.save(new ServiceOption(name,duration));
        return "redirect:/config/services";
    }

    @RequestMapping(value = "/services/delete/{id}", method = RequestMethod.GET)
    public String deleteServiceAction(@PathVariable("id") Long id){
        Optional<ServiceOption> toDelete = sr.findById(id);
        if(toDelete.isPresent()){
            ServiceOption so = toDelete.get();
            so.setDeleted(true);
            sr.save(so);
        }
        return "redirect:/config/services";
    }
    
    @RequestMapping(value = "/openhours", method = RequestMethod.GET)
    public String changeopenHoursAction(Model model){
        model.addAttribute("openhours", ohr.findById(1l).get());
        return "openhours";
    }
}
