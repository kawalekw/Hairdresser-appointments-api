package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.OpenHours;
import com.javaretards.hairdresserapponintments.Entity.WorkDay;
import com.javaretards.hairdresserapponintments.Repository.WorkDayRepository;
import com.javaretards.hairdresserapponintments.Service.DateUtilityService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.javaretards.hairdresserapponintments.Repository.OpenHoursRepository;

/**
 *
 * @author mateusz
 */
@Controller
public class DashboadController {
    @Autowired WorkDayRepository wdr;
    @Autowired OpenHoursRepository ohr;
    @Autowired DateUtilityService dus;
    
    @RequestMapping(value={"/dashboard","/dashboard/{datestr}"})
    public String dashboardAction(Model model, @PathVariable(value = "datestr", required = false) String datestr){
        LocalDate date = dus.parseOrNow(datestr);
        List<WorkDay> week = new ArrayList<>();
        for(int i=0;i<7;i++){
            LocalDate cache = date.plusDays(i);
            Optional<WorkDay> cacheDay = wdr.findByDate(cache);
            if(cacheDay.isPresent())
                week.add(cacheDay.get());
            else {
                OpenHours openHoursCache = ohr.findFirstByAppliesFromBeforeOrderByAppliesFromDesc(cache.plusDays(1)).get();
                WorkDay wd = new WorkDay(cache, openHoursCache.getFrom(cache.getDayOfWeek().getValue()),openHoursCache.getTo(cache.getDayOfWeek().getValue()));
                week.add(wd);
            }
        }
        LocalDate dateMinusWeek = date.minusDays(7);
        LocalDate dateMinusDay = date.minusDays(1);
        LocalDate datePlusDay = date.plusDays(1);
        LocalDate datePlusWeek = date.plusDays(7);
        model.addAttribute("week", week);
        model.addAttribute("dmw", dateMinusWeek);
        model.addAttribute("dmd", dateMinusDay);
        model.addAttribute("dpd", datePlusDay);
        model.addAttribute("dpw", datePlusWeek);
        return "dashboard";
    }
    
}
