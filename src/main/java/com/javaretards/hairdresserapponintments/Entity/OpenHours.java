package com.javaretards.hairdresserapponintments.Entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author mateusz
 */
@Entity
public class OpenHours {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int[] openFrom = new int[7];
    private int[] openTo = new int[7];
    @Column(unique=true)
    private LocalDate appliesFrom;
    
    public OpenHours(){
        openFrom = new int[]{0,540,540,540,540,540,0};
        openTo = new int[]{0,1020,1020,1020,1020,1020,0};
        appliesFrom = LocalDate.now();
    }
    
    public OpenHours(LocalDate date){
        openFrom = new int[]{0,540,540,540,540,540,0};
        openTo = new int[]{0,1020,1020,1020,1020,1020,0};
        appliesFrom = date;
    }
    
    public OpenHours(LocalDate date, int open, int close){
        openFrom = new int[]{open,open,open,open,open,open,open};
        openTo = new int[]{close,close,close,close,close,close,close};
        appliesFrom = date;
    }
    
    
    public void setDay(int day, int from, int to){
        if(day<7 && day>=0){ 
            openFrom[day]=from;
            openTo[day]=to;
        }
    }
    public int getFrom(int day){
        if(day<7 && day>=0)
            return openFrom[day];
        return -1;
    }
    
    public int getTo(int day){
        if(day<7 && day>=0)
            return openTo[day];
        return -1;
    }
    
    public String getFromTo(int day){
        if(day<7 && day>=0){
            if(openFrom[day]<openTo[day])
                return minToHours(openFrom[day])+" - "+minToHours(openTo[day]);
            return "zamknięte";
        }
        return "no such day exists";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDate getAppliesFrom() {
        return appliesFrom;
    }

    public void setAppliesFrom(LocalDate appliesFrom) {
        this.appliesFrom = appliesFrom;
    }
    
    private String minToHours(int min){
        return String.valueOf((int)(min/60))+":"+String.format("%02d",min%60);
    }
}
