package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.Appointment;
import com.javaretards.hairdresserapponintments.Entity.OpenHours;
import com.javaretards.hairdresserapponintments.Entity.WorkDay;
import com.javaretards.hairdresserapponintments.Repository.AppointmentRepository;
import com.javaretards.hairdresserapponintments.Repository.ClientRepository;
import com.javaretards.hairdresserapponintments.Repository.OpenHoursRepositiory;
import com.javaretards.hairdresserapponintments.Repository.ServiceRepository;
import com.javaretards.hairdresserapponintments.Repository.WorkDayRepository;
import com.javaretards.hairdresserapponintments.Service.DateUtilityService;
import com.javaretards.hairdresserapponintments.Service.ScheduleService;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mateusz
 */
@Controller
@RequestMapping("/day")
public class DayController {
    @Autowired AppointmentRepository ar;
    @Autowired ServiceRepository sr;
    @Autowired OpenHoursRepositiory ohr;
    @Autowired ClientRepository cr;
    @Autowired WorkDayRepository wdr;
    @Autowired ScheduleService ss;
    @Autowired DateUtilityService dus;
    
    @RequestMapping(value = "/{datestr}", method = RequestMethod.GET)
    public String dayAction(Model model, @PathVariable("datestr") String datestr){
        LocalDate date = dus.parseOrNow(datestr);
        WorkDay wd;
        Optional<WorkDay> owd = wdr.findByDate(date);
        if(owd.isPresent()){
            wd = owd.get();
        }
        else{
            OpenHours ohc = ohr.findFirstByAppliesFromBeforeOrderByAppliesFromDesc(date.plusDays(1)).get();
            wd = new WorkDay(date, ohc.getFrom(date.getDayOfWeek().getValue()),ohc.getTo(date.getDayOfWeek().getValue()));
        }
        model.addAttribute("day", wd);
        return "day";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String dayEditAction(@RequestParam(value = "open", defaultValue = "false") boolean open, @RequestParam("datestr") String datestr, @RequestParam("from") String from, @RequestParam("to") String to){
        LocalDate date = dus.parseOrNow(datestr);
        WorkDay wd;
        Optional<WorkDay> owd = wdr.findByDate(date);
        if(owd.isPresent()){
            wd = owd.get();
        }
        else{
            OpenHours ohc = ohr.findFirstByAppliesFromBeforeOrderByAppliesFromDesc(date.plusDays(1)).get();
            wd = new WorkDay(date, 0, 0);
        }
        if(open){
            wd.setOpenFromStr(from);
            wd.setOpenToStr(to);
        }
        else{
            wd.setOpenFrom(0);
            wd.setOpenTo(0);
        }
        wdr.save(wd);
        return "redirect:/day/"+datestr;
    }
    
    @RequestMapping(value = "/appoint/{datestr}", method = RequestMethod.GET)
    public String appointFormAction(Model model, @PathVariable("datestr") String datestr){
        LocalDate date = dus.parseOrNow(datestr);
        WorkDay wd;
        Optional<WorkDay> owd = wdr.findByDate(date);
        if(owd.isPresent()){
            wd = owd.get();
        }
        else{
            OpenHours ohc = ohr.findFirstByAppliesFromBeforeOrderByAppliesFromDesc(date.plusDays(1)).get();
            wd = new WorkDay(date, ohc.getFrom(date.getDayOfWeek().getValue()),ohc.getTo(date.getDayOfWeek().getValue()));
        }
        model.addAttribute("day", wd);
        model.addAttribute("services", sr.findByDeletedFalse());
        return "appoint";
    }
    
    @RequestMapping(value = "/appoint/{datestr}", method = RequestMethod.POST)
    public String appointAction(@PathVariable("datestr") String datestr, @RequestParam("service") Long service, @RequestParam("hour") String hour, @RequestParam("name") String name){
        LocalDate date = dus.parseOrNow(datestr);
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
        ar.save(new Appointment(cr.findByPhone("0").get(),sr.findById(service).get(),ss.hoursToMin(hour),name,wd));
        return "redirect:/day/"+datestr;
    }
    
    @RequestMapping(value = "/disappoint/{id}", method = RequestMethod.GET)
    public String appointFormAction(Model model, @PathVariable("id") Long id){
        Appointment ap;
        Optional<Appointment> oap = ar.findById(id);
        if(oap.isPresent())
            ap = oap.get();
        else
            return "redirect:/dashboard/";
        ar.delete(ap);
        return "redirect:/day/"+ap.getDay().getDate().toString();
    }
    
}
