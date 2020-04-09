package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.OpenHours;
import com.javaretards.hairdresserapponintments.Entity.WorkDay;
import com.javaretards.hairdresserapponintments.Repository.OpenHoursRepository;
import com.javaretards.hairdresserapponintments.Repository.WorkDayRepository;
import com.javaretards.hairdresserapponintments.Service.DateUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RestDashboardController {
    @Autowired
    WorkDayRepository wdr;
    @Autowired
    OpenHoursRepository ohr;
    @Autowired DateUtilityService dus;

    @GetMapping(value={"api/dashboard","api/dashboard/{datestr}"})
    public Iterable<WorkDay> dashboardAction(@PathVariable(value = "datestr", required = false) String datestr){
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
        return week;
    }
}
