package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.Appointment;
import com.javaretards.hairdresserapponintments.Entity.Client;
import com.javaretards.hairdresserapponintments.Entity.OpenHours;
import com.javaretards.hairdresserapponintments.Entity.ServiceOption;
import com.javaretards.hairdresserapponintments.Entity.WorkDay;
import com.javaretards.hairdresserapponintments.Repository.AppointmentRepository;
import com.javaretards.hairdresserapponintments.Repository.ClientRepository;
import com.javaretards.hairdresserapponintments.Repository.OpenHoursRepository;
import com.javaretards.hairdresserapponintments.Repository.ServiceRepository;
import com.javaretards.hairdresserapponintments.Repository.WorkDayRepository;
import com.javaretards.hairdresserapponintments.Service.ScheduleService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author mateusz
 */ 
@Controller
public class RegisterController {
    @Autowired AppointmentRepository ar;
    @Autowired ClientRepository cr;
    @Autowired ServiceRepository sr;
    @Autowired WorkDayRepository wdr;
    @Autowired OpenHoursRepository ohr;
    @Autowired ScheduleService ss;
    
    @RequestMapping("/register")
    public String registerFormAction(Model model){
        LocalDate date = LocalDate.now();
        List<WorkDay> days = new ArrayList<>();
        List<WorkDay> disabledDays = new ArrayList<>();
        for(int i=0;i<90;i++){
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
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerAction(RedirectAttributes ratt, @RequestParam("datestr") String datestr,
            @RequestParam("service") Long service, @RequestParam(value = "hour", required = false) String hour,
            @RequestParam(value = "name", required = false) String name, 
            @RequestParam(value = "phone", required = false) String phone)
    {
        LocalDate date;
        try{
            date=LocalDate.parse(datestr);
        }
        catch(java.lang.NullPointerException | java.time.format.DateTimeParseException e){
            ratt.addFlashAttribute("alert_error","Niepoprawna data");
            return "redirect:/register";
        }
        WorkDay wd;
        Optional<WorkDay> owd = wdr.findByDate(date);
        if(owd.isPresent()){
            wd = owd.get();
        }
        else{
            OpenHours ohc = ohr.findFirstByAppliesFromBeforeOrderByAppliesFromDesc(date.plusDays(1)).get();
            wd = new WorkDay(date, ohc.getFrom(date.getDayOfWeek().getValue()),ohc.getTo(date.getDayOfWeek().getValue()));
            wdr.save(wd);
        }
        ServiceOption se;
        Optional<ServiceOption> ose = sr.findById(service==null?0:service);
        if(ose.isPresent()){
            se=ose.get();
        }
        else{
            ratt.addFlashAttribute("alert_error","Niepoprawna usługa");
            return "redirect:/register";
        }
        if(hour==null || ss.hoursToMin(hour)==0){
            ratt.addFlashAttribute("alert_error", "Niepoprawna godzina");
            return "redirect:/register";
        }
        int minutes=ss.hoursToMin(hour);
        if(minutes%5!=0 || minutes<wd.getOpenFrom() || minutes>wd.getOpenTo()-se.getDuration()){
            ratt.addFlashAttribute("alert_error", "Niepoprawna godzina");
            return "redirect:/register"; 
        }
        if(name==null || name.length()<=3){
            ratt.addFlashAttribute("alert_error", "Podaj imię i nazwisko");
            return "redirect:/register"; 
        }
        if(phone==null || phone.length()!=9 || !phone.equals(String.valueOf(Integer.parseInt(phone)))){
            ratt.addFlashAttribute("alert_error", "Podaj numer telefonu");
            return "redirect:/register"; 
        }
        Client cl = null;
        Optional<Client> ocl = cr.findByPhone(phone);
        if(ocl.isPresent()){
            cl=ocl.get();
            if(cl.isBlocked()){
                ratt.addFlashAttribute("alert_error", "Użytkownik zablokowany");
                return "redirect:/register"; 
            }
        }
        else{
            cl=new Client(phone);
            cr.save(cl);
        }
        //shit roughly validated \/ add to the db \/
        ar.save(new Appointment(cl,se,minutes,name,wd));
        ratt.addFlashAttribute("alert_success", "Umówiono pomyślnie");
        return "redirect:/registered/"+phone;
    }
    
    @RequestMapping("/registered/{phone}")
    public String registeredAction(Model model, @PathVariable("phone") String phone){
        Client cl;
        Optional<Client> ocl=cr.findByPhone(phone);
        if(ocl.isPresent())
            cl=ocl.get();
        else
            return "redirect:/error";
        Appointment ap;
        Optional<Appointment> oap = ar.findTopByClientOrderByIdDesc(cl);
        if(oap.isPresent())
            ap=oap.get();
        else
            return "redirect:/error";
        model.addAttribute("appointment", ap);
        return "appointmentinfo";
    }
}
