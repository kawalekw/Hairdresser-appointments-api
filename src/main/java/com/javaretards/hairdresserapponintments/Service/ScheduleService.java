package com.javaretards.hairdresserapponintments.Service;

import com.javaretards.hairdresserapponintments.Entity.Appointment;
import com.javaretards.hairdresserapponintments.Entity.WorkDay;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

/**
 *
 * @author mateusz
 */
@Service
public class ScheduleService {
    
    public Iterable<String> getScheduleoptions(WorkDay wd, Integer duration){
        List<String> options = new ArrayList<>();
        if(wd.getOpenFrom()>=wd.getOpenTo())
            return options;
        Stream<Integer> minStream = IntStream.rangeClosed(wd.getOpenFrom(), wd.getOpenTo()).filter(i -> i%5==0).boxed();
        if(wd.getAppointments()!=null){
            for(Appointment ap : wd.getAppointments()){
                if(ap.getStartsAt()==wd.getOpenFrom())
                    minStream=minStream.filter(i -> i>ap.getStartsAt());
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
    
    public String minToHours(int min){
        return String.valueOf((int)(min/60))+":"+String.format("%02d",min%60);
    }
    
    public int hoursToMin(String str){
        if(!str.matches("^\\d{1,2}:\\d{1,2}$"))
            return 0;
        String[] arr=str.split(":");
        return (Integer.parseInt(arr[0])*60)+Integer.parseInt(arr[1]);
    }
}
