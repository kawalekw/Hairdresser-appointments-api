package com.javaretards.hairdresserapponintments.Controller;


import com.javaretards.hairdresserapponintments.Entity.ServiceOption;
import com.javaretards.hairdresserapponintments.Repository.ServiceRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author mateusz
 */
@Controller
public class ServicesController {
    @Autowired ServiceRepository sr;
    
    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public String viewServicesAction(Model model){
        model.addAttribute("services", sr.findByDeletedFalse());
        return "services";
    }
    
    @RequestMapping(value = "/services", method = RequestMethod.POST)
    public String addServiceAction(RedirectAttributes ratt, @RequestParam("name") String name, @RequestParam("duration") int duration){
        sr.save(new ServiceOption(name,duration));
        ratt.addFlashAttribute("alert_success", "Zapisano pomyślnie");
        return "redirect:/services";
    }

    @RequestMapping(value = "/services/delete/{id}", method = RequestMethod.GET)
    public String deleteServiceAction(RedirectAttributes ratt, @PathVariable("id") Long id){
        Optional<ServiceOption> toDelete = sr.findById(id);
        if(toDelete.isPresent()){
            ServiceOption so = toDelete.get();
            so.setDeleted(true);
            sr.save(so);
        }
        ratt.addFlashAttribute("alert_success", "Usunięto pomyślnie");
        return "redirect:/services";
    }
}
