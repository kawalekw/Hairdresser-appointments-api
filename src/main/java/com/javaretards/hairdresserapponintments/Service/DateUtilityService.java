package com.javaretards.hairdresserapponintments.Service;

import java.time.LocalDate;
import org.springframework.stereotype.Service;

/**
 *
 * @author mateusz
 */
@Service
public class DateUtilityService {
    public LocalDate parseOrNow(String str){
        LocalDate date;
        try{
            date=LocalDate.parse(str);
        }
        catch(java.lang.NullPointerException | java.time.format.DateTimeParseException e){
            date=LocalDate.now();
        }
        return date;
    }
}
