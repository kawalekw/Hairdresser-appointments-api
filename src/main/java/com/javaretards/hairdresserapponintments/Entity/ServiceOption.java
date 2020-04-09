package com.javaretards.hairdresserapponintments.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @NotEmpty
    private String name;
    @Getter
    @NotNull
    @Min(5)
    @Max(300)
    private int duration;
    @Getter
    @Setter
    @JsonIgnore
    private boolean deleted;
    
    public ServiceOption(String name, int duration){
        this.name=name;
        this.duration=duration;
        this.deleted=false;
    }
    
}
