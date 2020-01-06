package com.javaretards.hairdresserapponintments.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mateusz
 */
@Controller
public class DashboadController {
    
    @RequestMapping(value={"/dashboard","/dashboard/{year}/{month}/{day}"})
    public String dashboardAction(Model model,
            @PathVariable(value = "year",required = false) Integer year,
            @PathVariable(value = "month",required = false) Integer month,
            @PathVariable(value = "day", required = false) Integer day){
        List<LocalDate> week = new ArrayList<>();
        LocalDate date = LocalDate.now();
        if(year != null){
            try{
                date = LocalDate.of(year, month, day);
            }
            catch(java.time.DateTimeException e){
                date = LocalDate.now();
            }
        }
        for(int i=0;i<7;i++){
            LocalDate cache = date.plusDays(i);
            week.add(cache);
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
