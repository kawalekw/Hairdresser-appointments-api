package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.*;
import com.javaretards.hairdresserapponintments.Repository.*;
import com.javaretards.hairdresserapponintments.Service.ScheduleService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

/**
 *
 * @author mateusz
 */
@RestController
public class RestScheduleController {
    @Autowired ServiceRepository sr;
    @Autowired WorkDayRepository wdr;
    @Autowired OpenHoursRepository ohr;
    @Autowired ScheduleService ss;
    @Autowired ClientRepository cr;
    @Autowired AppointmentRepository ar;

    @GetMapping(value = {"/api/schedule/{date}/{id}"})
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
    @PostMapping(value = {"/api/schedule/{operator}","/api/schedule"})
    public Appointment register(@PathVariable(value = "operator", required = false) String operator, @RequestBody AppointmentData newApp) throws ServletException {

        LocalDate appDate;
        try {
            appDate = LocalDate.parse(newApp.getDate());
        }
        catch(java.lang.NullPointerException | java.time.format.DateTimeParseException e){
            throw new ServletException("Date Wrong or not present");
        }
        WorkDay appDay;
        Optional<WorkDay> owd = wdr.findByDate(appDate);
        if(owd.isPresent()){
            appDay = owd.get();
        }
        else{
            OpenHours ohc = ohr.findFirstByAppliesFromBeforeOrderByAppliesFromDesc(appDate.plusDays(1)).get();
            appDay = new WorkDay(appDate, ohc.getFrom(appDate.getDayOfWeek().getValue()),ohc.getTo(appDate.getDayOfWeek().getValue()));
            wdr.save(appDay);
        }

        Optional<ServiceOption> osr = sr.findById(newApp.getServiceId());
        if(!osr.isPresent())
            throw new ServletException("Service ID invalid");
        ServiceOption appService=osr.get();


        if(StreamSupport.stream(ss.getScheduleoptions(appDay,appService.getDuration()).spliterator(), false).noneMatch(ss.minToHours(newApp.getStartsAt())::equals))
            throw new ServletException("Invalid startsAt attribute");

        if(newApp.getName()==null)
            throw new ServletException("Name not present");

        Client appClient = null;
        if(!operator.equals("mode")){
            if(newApp.getPhone()==null)
                throw new ServletException("Phone not present");
            Optional<Client> ocl = cr.findByPhone(newApp.getPhone());
            if(ocl.isPresent()){
                appClient=ocl.get();
                if(appClient.isBlocked()){
                    throw new ServletException("Client is blocked");
                }
            }
            else{
                appClient=new Client(newApp.getPhone());
                cr.save(appClient);
            }
        }
        else{
            appClient=cr.findByPhone("0").get();
        }
        Appointment app=new Appointment(appClient,appService,newApp.getStartsAt(),newApp.getName(),appDay);
        
        return ar.save(app);
    }

    //TODO: register by client
    //TODO: register by admin
    //TODO: delete appointment
}
