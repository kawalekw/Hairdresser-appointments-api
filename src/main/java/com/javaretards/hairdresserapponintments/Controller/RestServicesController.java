package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.ServiceOption;
import com.javaretards.hairdresserapponintments.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RestServicesController {
    @Autowired
    ServiceRepository sr;

    @GetMapping(value = "/api/serviceoptions")
    public Iterable<ServiceOption> getServiceOptions(){
        return sr.findAll();
    }

    @GetMapping(value = "/api/serviceoptions/{id}")
    public Optional<ServiceOption> getServiceOption(@PathVariable(name = "id") Long id){
        return sr.findById(id);
    }
}
