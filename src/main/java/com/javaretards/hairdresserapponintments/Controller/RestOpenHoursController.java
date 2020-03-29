package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.OpenHours;
import com.javaretards.hairdresserapponintments.Repository.OpenHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
public class RestOpenHoursController {
    @Autowired
    OpenHoursRepository ohr;

    @GetMapping(value = "/api/openhours")
    public Iterable<OpenHours> getAllOpenHours(){
        return ohr.findAll();
    }

    @GetMapping(value = "/api/openhours/{id}")
    public Optional<OpenHours> getAllOpenHoursById(@PathVariable(name = "id") Long id){
        return ohr.findById(id);
    }

    @GetMapping(value = "/api/openhours/recent")
    public OpenHours getAllOpenHoursById(){
        return ohr.findFirstByAppliesFromBeforeOrderByAppliesFromDesc(LocalDate.now().plusDays(1)).get();
    }
}
