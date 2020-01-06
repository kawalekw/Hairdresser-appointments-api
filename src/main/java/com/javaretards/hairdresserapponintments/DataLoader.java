package com.javaretards.hairdresserapponintments;

import com.javaretards.hairdresserapponintments.Entity.OpenHours;
import com.javaretards.hairdresserapponintments.Entity.ServiceOption;
import com.javaretards.hairdresserapponintments.Repository.OpenHoursRepositiory;
import com.javaretards.hairdresserapponintments.Repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author mateusz
 */
@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner{
    private OpenHoursRepositiory ohr;
    private ServiceRepository sr;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(ohr.count()==0){
            ohr.save(new OpenHours());
        }
        if(sr.count()==0){
            sr.save(new ServiceOption("Strzyżenie męskie",15));
            sr.save(new ServiceOption("Mycie",10));
            sr.save(new ServiceOption("Farba",75));
        }
    }
    
}
