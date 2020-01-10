package com.javaretards.hairdresserapponintments.Entity;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author mateusz
 */
@Entity
@NoArgsConstructor
@Getter
public class WorkDay {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private LocalDate date;
    @Setter
    private int openFrom;
    @Setter
    private int openTo;
    @OneToMany(mappedBy = "day", fetch = FetchType.EAGER)
    private List<Appointment> appointments;

    public WorkDay(LocalDate date, int openFrom, int openTo) {
        this.date = date;
        this.openFrom = openFrom;
        this.openTo = openTo;
    }
    
    public boolean isOpen(){
        return (openFrom<openTo);
    }
    
    public String getFromTo(){
        if(openFrom<openTo)
            return minToHours(openFrom)+" - "+minToHours(openTo);
        return "zamknięte";
    }
    
    public String getOpenFromStr(){
        return minToHours(openFrom);
    }
    
    public String getOpenToStr(){
        return minToHours(openTo);
    }
    
    public void setOpenFromStr(String hours){
        openFrom=hoursToMin(hours);
    }
    
    public void setOpenToStr(String hours){
        openTo=hoursToMin(hours);
    }
    
    private String minToHours(int min){
        return String.valueOf((int)(min/60))+":"+String.format("%02d",min%60);
    }
    
    private int hoursToMin(String str){
        if(!str.matches("^\\d{1,2}:\\d{1,2}$"))
            return 0;
        String[] arr=str.split(":");
        return (Integer.parseInt(arr[0])*60)+Integer.parseInt(arr[1]);
    }
    
    public List<Appointment> getAppointments(){
        if(appointments!=null)
            return appointments.stream().sorted(Comparator.comparing(Appointment::getStartsAt)).collect(Collectors.toList());
        return null;
    }
}
