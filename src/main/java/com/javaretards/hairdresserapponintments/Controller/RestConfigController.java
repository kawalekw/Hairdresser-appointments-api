package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.Appointment;
import com.javaretards.hairdresserapponintments.Entity.OpenHours;
import com.javaretards.hairdresserapponintments.Entity.ServiceOption;
import com.javaretards.hairdresserapponintments.Entity.WorkDay;
import com.javaretards.hairdresserapponintments.Repository.OpenHoursRepositiory;
import com.javaretards.hairdresserapponintments.Repository.ServiceRepository;
import com.javaretards.hairdresserapponintments.Repository.WorkDayRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mateusz
 */
@RestController
public class RestConfigController {
    @Autowired
    ServiceRepository sr;
    @Autowired
    WorkDayRepository wdr;
    @Autowired
    OpenHoursRepositiory ohr;
    
    @GetMapping("/api/services")
    public Iterable<ServiceOption> getAllServices(){
        return sr.findByDeletedFalse();
    }
    
    @GetMapping(value = {"/api/schedule/{date}","/api/schedule/{date}/{duration}"})
    public Iterable<String> getScehuleOptionsAction(@PathVariable("date") String datestr,@PathVariable(value="duration", required = false) Integer duration){
        List<String> options = new ArrayList<>();
        LocalDate date;
        try{
            date = LocalDate.parse(datestr);
        }
        catch(java.time.format.DateTimeParseException e){
            return options;
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
        if(wd.getOpenFrom()>=wd.getOpenTo())
            return options;
        Stream<Integer> minStream = IntStream.rangeClosed(wd.getOpenFrom(), wd.getOpenTo()).filter(i -> i%5==0).boxed();
        if(wd.getAppointments()!=null){
            for(Appointment ap : wd.getAppointments()){
                if(duration==null)
                    minStream=minStream.filter(i -> (i<=ap.getStartsAt() || i>=ap.getEndsAt()));
                else
                    minStream=minStream.filter(i -> (i<=ap.getStartsAt()-duration || i>=ap.getEndsAt()));
            }
        }
        if(duration!=null)
            minStream=minStream.filter(i -> i <= wd.getOpenTo()-duration);
        options = minStream.map(i -> minToHours(i)).collect(Collectors.toList());
        return options;
    }
    
    private String minToHours(int min){
        return String.valueOf((int)(min/60))+":"+String.format("%02d",min%60);
    }
}
