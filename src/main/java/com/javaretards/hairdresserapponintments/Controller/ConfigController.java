package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.Client;
import com.javaretards.hairdresserapponintments.Entity.ServiceOption;
import com.javaretards.hairdresserapponintments.Repository.ClientRepository;
import com.javaretards.hairdresserapponintments.Repository.OpenHoursRepositiory;
import com.javaretards.hairdresserapponintments.Repository.ServiceRepository;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/config")
public class ConfigController {
    
    @Autowired
    ServiceRepository sr;
    @Autowired
    OpenHoursRepositiory ohr;
    @Autowired
    ClientRepository cr;
    
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
        model.addAttribute("openhours", ohr.findFirstByAppliesFromBeforeOrderByAppliesFromDesc(LocalDate.now().plusDays(1)).get());
        return "openhours";
    }
    
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String clientsAction(Model model){
        model.addAttribute("clients", cr.findAll());
        return "clients";
    }
    
    @RequestMapping(value = "/clients/block/{id}", method = RequestMethod.GET)
    public String blockClientAction(@PathVariable("id") Long id){
        Optional<Client> toDelete = cr.findById(id);
        if(toDelete.isPresent()){
            Client cl = toDelete.get();
            cl.block();
            cr.save(cl);
        }
        return "redirect:/config/clients";
    }
    
    @RequestMapping(value = "/clients/unlock/{id}", method = RequestMethod.GET)
    public String unlockClientAction(@PathVariable("id") Long id){
        Optional<Client> toDelete = cr.findById(id);
        if(toDelete.isPresent()){
            Client cl = toDelete.get();
            cl.unlock();
            cr.save(cl);
        }
        return "redirect:/config/clients";
    }
}
