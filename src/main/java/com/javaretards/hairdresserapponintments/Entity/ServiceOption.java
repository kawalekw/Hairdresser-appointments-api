package com.javaretards.hairdresserapponintments.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author mateusz
 */
@Entity
@NoArgsConstructor
public class ServiceOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    private String name;
    @Getter
    private int duration;
    @Getter
    @Setter
    private boolean deleted;
    
    public ServiceOption(String name, int duration){
        this.name=name;
        this.duration=duration;
        this.deleted=false;
    }
}
