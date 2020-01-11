package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.OpenHours;
import com.javaretards.hairdresserapponintments.Entity.WorkDay;
import com.javaretards.hairdresserapponintments.Repository.OpenHoursRepository;
import com.javaretards.hairdresserapponintments.Repository.ServiceRepository;
import com.javaretards.hairdresserapponintments.Repository.WorkDayRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mateusz
 */ 
@Controller
public class RegisterController {
    @Autowired ServiceRepository sr;
    @Autowired WorkDayRepository wdr;
    @Autowired OpenHoursRepository ohr;
    
    @RequestMapping("/register")
    public String registerAction(Model model){
        LocalDate date = LocalDate.now();
        List<WorkDay> days = new ArrayList<>();
        for(int i=0;i<60;i++){
            LocalDate cache = date.plusDays(i);
            Optional<WorkDay> cacheDay = wdr.findByDate(cache);
            if(cacheDay.isPresent())
                days.add(cacheDay.get());
            else {
                OpenHours openHoursCache = ohr.findFirstByAppliesFromBeforeOrderByAppliesFromDesc(cache.plusDays(1)).get();
                WorkDay wd = new WorkDay(cache, openHoursCache.getFrom(cache.getDayOfWeek().getValue()),openHoursCache.getTo(cache.getDayOfWeek().getValue()));
                days.add(wd);
            }
        }
        model.addAttribute("days",days);
        model.addAttribute("services", sr.findByDeletedFalse());
        return "register";
    }
}
