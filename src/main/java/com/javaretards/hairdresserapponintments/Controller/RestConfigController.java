package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.OpenHours;
import com.javaretards.hairdresserapponintments.Entity.ServiceOption;
import com.javaretards.hairdresserapponintments.Entity.WorkDay;
import com.javaretards.hairdresserapponintments.Repository.ServiceRepository;
import com.javaretards.hairdresserapponintments.Repository.WorkDayRepository;
import com.javaretards.hairdresserapponintments.Service.ScheduleService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.javaretards.hairdresserapponintments.Repository.OpenHoursRepository;

/**
 *
 * @author mateusz
 */
@RestController
public class RestConfigController {
    @Autowired ServiceRepository sr;
    @Autowired WorkDayRepository wdr;
    @Autowired OpenHoursRepository ohr;
    @Autowired ScheduleService ss;
    
    @GetMapping("/api/services")
    public Iterable<ServiceOption> getAllServices(){
        return sr.findByDeletedFalse();
    }
    
    @GetMapping(value = {"/api/schedule/{date}","/api/schedule/{date}/{duration}"})
    public Iterable<String> getScehuleOptionsAction(@PathVariable("date") String datestr,@PathVariable(value="duration", required = false) Integer duration){
        LocalDate date;
        try{
            date = LocalDate.parse(datestr);
        }
        catch(java.time.format.DateTimeParseException e){
            return new ArrayList<String>();
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
        return ss.getScheduleoptions(wd, duration);
    }
    @GetMapping(value = {"/api/schedule/id/{date}/{id}"})
    public Iterable<String> getScehuleOptionsByIdAction(@PathVariable("date") String datestr,@PathVariable(value="id") Long id){
        LocalDate date;
        try{
            date = LocalDate.parse(datestr);
        }
        catch(java.time.format.DateTimeParseException e){
            return new ArrayList<String>();
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
        ServiceOption so;
        Optional<ServiceOption> os = sr.findById(id);
        if(os.isPresent()){
            so=os.get();
        }
        else
            return new ArrayList<String>();
        return ss.getScheduleoptions(wd, so.getDuration());   
    }
}
