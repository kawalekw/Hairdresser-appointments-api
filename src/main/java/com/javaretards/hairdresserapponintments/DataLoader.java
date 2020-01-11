package com.javaretards.hairdresserapponintments;

import com.javaretards.hairdresserapponintments.Entity.Client;
import com.javaretards.hairdresserapponintments.Entity.OpenHours;
import com.javaretards.hairdresserapponintments.Entity.ServiceOption;
import com.javaretards.hairdresserapponintments.Repository.ClientRepository;
import com.javaretards.hairdresserapponintments.Repository.ServiceRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.javaretards.hairdresserapponintments.Repository.OpenHoursRepository;

/**
 *
 * @author mateusz
 */
@Component
public class DataLoader implements ApplicationRunner{
    @Autowired OpenHoursRepository ohr;
    @Autowired ServiceRepository sr;
    @Autowired ClientRepository cr;

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
        }
    }
}
