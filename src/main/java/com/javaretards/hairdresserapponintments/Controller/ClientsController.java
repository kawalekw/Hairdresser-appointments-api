package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.Client;
import com.javaretards.hairdresserapponintments.Repository.ClientRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author mateusz
 */
@Controller
public class ClientsController {
    @Autowired ClientRepository cr;
    
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String clientsAction(Model model){
        model.addAttribute("clients", cr.findAll());
        return "clients";
    }
    
    @RequestMapping(value = "/clients/block/{id}", method = RequestMethod.GET)
    public String blockClientAction(RedirectAttributes ratt, @PathVariable("id") Long id){
        Optional<Client> toDelete = cr.findById(id);
        if(toDelete.isPresent()){
            Client cl = toDelete.get();
            cl.block();
            cr.save(cl);
        }else{
            ratt.addFlashAttribute("alert_error", "Nie ma takiego klienta");
            return "redirect:/clients";
        }
        ratt.addFlashAttribute("alert_success", "Zablokowano pomyślnie");
        return "redirect:/clients";
    }
    
    @RequestMapping(value = "/clients/unlock/{id}", method = RequestMethod.GET)
    public String unlockClientAction(RedirectAttributes ratt, @PathVariable("id") Long id){
        Optional<Client> toDelete = cr.findById(id);
        if(toDelete.isPresent()){
            Client cl = toDelete.get();
            cl.unlock();
            cr.save(cl);
        }else{
            ratt.addFlashAttribute("alert_error", "Nie ma takiego klienta");
            return "redirect:/clients";
        }
        ratt.addFlashAttribute("alert_success", "Odblokowano pomyślnie");
        return "redirect:/clients";
    }
}
