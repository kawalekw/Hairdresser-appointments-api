package com.javaretards.hairdresserapponintments.Entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private int openFrom;
    private int openTo;
    @OneToMany(mappedBy = "day", fetch = FetchType.EAGER)
    private List<Appointment> appointments;

    public WorkDay(LocalDate date, int openFrom, int openTo) {
        this.date = date;
        this.openFrom = openFrom;
        this.openTo = openTo;
    }
    
    public String getFromTo(){
        if(openFrom<openTo)
            return minToHours(openFrom)+" - "+minToHours(openTo);
        return "zamknięte";
    }
    
    private String minToHours(int min){
        return String.valueOf((int)(min/60))+":"+String.format("%02d",min%60);
    }
    
}
