package com.javaretards.hairdresserapponintments;

import com.javaretards.hairdresserapponintments.Entity.Appointment;
import com.javaretards.hairdresserapponintments.Entity.Client;
import com.javaretards.hairdresserapponintments.Entity.OpenHours;
import com.javaretards.hairdresserapponintments.Entity.ServiceOption;
import com.javaretards.hairdresserapponintments.Entity.WorkDay;
import com.javaretards.hairdresserapponintments.Repository.AppointmentRepository;
import com.javaretards.hairdresserapponintments.Repository.ClientRepository;
import com.javaretards.hairdresserapponintments.Repository.OpenHoursRepositiory;
import com.javaretards.hairdresserapponintments.Repository.ServiceRepository;
import com.javaretards.hairdresserapponintments.Repository.WorkDayRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author mateusz
 */
@Component
public class DataLoader implements ApplicationRunner{
    @Autowired
    OpenHoursRepositiory ohr;
    @Autowired
    ServiceRepository sr;
    @Autowired
    ClientRepository cr;
    @Autowired
    WorkDayRepository wdr;
    @Autowired
    AppointmentRepository ar;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(ohr.count()==0){
            ohr.save(new OpenHours(LocalDate.parse("2000-01-01"),0,0)); //closed
            ohr.save(new OpenHours(LocalDate.parse("2020-01-01"))); //opend since
        }
        if(sr.count()==0){
            sr.save(new ServiceOption("Strzyżenie męskie",15));
            sr.save(new ServiceOption("Mycie",10));
            sr.save(new ServiceOption("Farba",75));
        }
        if(!cr.findByPhone("0").isPresent()){
            cr.save(new Client("0")); //default client
            cr.save(new Client("754815458"));
            cr.save(new Client("684518874"));
            cr.save(new Client("697554187"));
            cr.save(new Client("745156755"));
        }
        if(wdr.count()==0){
            WorkDay dDay = new WorkDay(LocalDate.parse("2020-01-01"),600,1020);
            wdr.save(dDay);
            ar.save(new Appointment(cr.findByPhone("0").get(),sr.findById(1l).get(),640,"Janusz Kowalski",dDay));
            ar.save(new Appointment(cr.findByPhone("0").get(),sr.findById(3l).get(),820,"Marianna Kowalski",dDay));
        }
         
    }
}
