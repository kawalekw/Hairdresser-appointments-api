package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.OpenHours;
import com.javaretards.hairdresserapponintments.Entity.WorkDay;
import com.javaretards.hairdresserapponintments.Repository.OpenHoursRepository;
import com.javaretards.hairdresserapponintments.Repository.WorkDayRepository;
import com.javaretards.hairdresserapponintments.Service.DateUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/day")
public class RestDayController {
    @Autowired
    WorkDayRepository wdr;
    @Autowired
    OpenHoursRepository ohr;
    @Autowired
    DateUtilityService dus;

    @GetMapping(value = "/{date}")
    public WorkDay getDay(@PathVariable("date") String datestr) throws ServletException {
        LocalDate date;
        try{
            date=LocalDate.parse(datestr);
        }
        catch (java.lang.NullPointerException | java.time.format.DateTimeParseException e){
            throw new ServletException("Fucked up date");
        }

        WorkDay wd;
        Optional<WorkDay> owd = wdr.findByDate(date);
        if(owd.isPresent()){
            wd = owd.get();
        }
        else{
            OpenHours ohc = ohr.findFirstByAppliesFromBeforeOrderByAppliesFromDesc(date.plusDays(1)).get();
            wd = new WorkDay(date, ohc.getFrom(date.getDayOfWeek().getValue()),ohc.getTo(date.getDayOfWeek().getValue()));
        }
        return wd;
    }
    @PatchMapping("/{date}")
    public WorkDay getDay(@PathVariable("date") String datestr, @RequestBody WorkDay newDay) throws ServletException {
        WorkDay toEdit = getDay(datestr);
        toEdit.setOpenFrom(newDay.getOpenFrom());
        toEdit.setOpenTo(newDay.getOpenTo());
        return wdr.save(toEdit);
    }
}
