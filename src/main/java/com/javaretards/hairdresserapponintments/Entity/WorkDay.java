package com.javaretards.hairdresserapponintments.Entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.NoArgsConstructor;

/**
 *
 * @author mateusz
 */
@Entity
@NoArgsConstructor
public class WorkDay {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private int openFrom;
    private int openTo;
    //private List<Appointment> appointments;
    
}
