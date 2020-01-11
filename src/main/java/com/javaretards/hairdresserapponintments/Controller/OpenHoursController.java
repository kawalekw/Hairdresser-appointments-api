package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.OpenHours;
import com.javaretards.hairdresserapponintments.Service.DateUtilityService;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.javaretards.hairdresserapponintments.Repository.OpenHoursRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author mateusz
 */
@Controller
public class OpenHoursController {
    @Autowired OpenHoursRepository ohr;
    @Autowired DateUtilityService dus;

    @RequestMapping(value = {"/openhours","/openhours/{datestr}"}, method = RequestMethod.GET)
    public String openHoursAction(Model model, @PathVariable(value = "datestr", required = false) String datestr){
        LocalDate date = dus.parseOrNow(datestr);
        model.addAttribute("openhours", ohr.findFirstByAppliesFromBeforeOrderByAppliesFromDesc(date.plusDays(1)).get());
        model.addAttribute("definitions",ohr.findAllByOrderByAppliesFromAsc());
        return "openhours";
    }
    
    @RequestMapping(value = "/openhours/edit", method = RequestMethod.GET)
    public String editOpenHoursAction(Model model){
        model.addAttribute("openhours", ohr.findFirstByAppliesFromBeforeOrderByAppliesFromDesc(LocalDate.now().plusDays(1)).get());
        model.addAttribute("appliesfrom", LocalDate.now());
        return "openhoursedit";
    }
    
    @RequestMapping(value = "/openhours/edit", method = RequestMethod.POST)
    public String finallyEditOpenHoursAction(RedirectAttributes ratt, @RequestParam("from[]") String[] from, @RequestParam("to[]") String[] to, @RequestParam("appliesfrom") String appliesFrom){
        LocalDate applies = LocalDate.parse(appliesFrom); // TODO: resolve failed parse() exception
        OpenHours oh;
        Optional<OpenHours> ooh = ohr.findByAppliesFrom(applies);
        if(ooh.isPresent())
            oh = ooh.get();
        else
            oh = new OpenHours(applies);
        for(int i=0;i<7;i++){
            oh.setFromStr(i, from[i]);
            oh.setToStr(i, to[i]);
        }
        ohr.save(oh);
        ratt.addFlashAttribute("alert_success", "Zapisano pomyślnie");
        return "redirect:/openhours";
    }
    @RequestMapping(value = "/openhours/del/{id}", method = RequestMethod.GET)
    public String deleteOpenHoursAction(RedirectAttributes ratt, @PathVariable("id") Long id){
        Optional<OpenHours> toDelete = ohr.findById(id);
        if(toDelete.isPresent()){
            OpenHours op = toDelete.get();
            ohr.delete(op);
        }
        else{
            ratt.addFlashAttribute("alert_error", "Nie ma takiej definicji");
            return "redirect:/openhours";
        }
        ratt.addFlashAttribute("alert_success", "Usunięto pomyślnie");
        return "redirect:/openhours";
    }
}
