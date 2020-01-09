package com.javaretards.hairdresserapponintments.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author mateusz
*/
@Entity
@NoArgsConstructor
@Getter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceOption service;
    private int startsAt;
    private String name;
    @ManyToOne
    @JoinColumn(name = "day_id")
    private WorkDay day;

    public Appointment(Client client, ServiceOption service, int startsAt, String name) {
        this.client = client;
        this.service = service;
        this.startsAt = startsAt;
        this.name = name;
    }

    public Appointment(Client client, ServiceOption service, int startsAt, String name, WorkDay day) {
        this.client = client;
        this.service = service;
        this.startsAt = startsAt;
        this.name = name;
        this.day = day;
    }
    
    public String getFromTo(){
        return minToHours(startsAt)+" - "+minToHours(startsAt+service.getDuration());
    }
    
    public int getEndsAt(){
        return this.startsAt+this.service.getDuration();
    }
    
    private String minToHours(int min){
        return String.valueOf((int)(min/60))+":"+String.format("%02d",min%60);
    }
}
