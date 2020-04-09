package com.javaretards.hairdresserapponintments.Entity;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author mateusz
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique=true)
    private String phone;
    private boolean blocked;
    @JsonIgnore
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Appointment> appointments;

    public Client(String phone) {
        this.phone = phone;
        this.blocked = false;
    }
    
    public void block(){
        this.blocked=true;
    }
    
    public void unlock(){
        this.blocked=false;
    }
    
    public List<Appointment> getAppointments(){
        if(appointments!=null)
            return appointments.stream().sorted(Comparator.comparing(Appointment::getStartsAt)).collect(Collectors.toList());
        return null;
    }
    @JsonIgnore
    public Appointment getLastAppointment(){
        return appointments.stream().reduce((a,b) -> b).orElse(null);
    }
}
