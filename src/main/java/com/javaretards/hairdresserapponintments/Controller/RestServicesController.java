package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.ServiceOption;
import com.javaretards.hairdresserapponintments.Entity.StringResponse;
import com.javaretards.hairdresserapponintments.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class RestServicesController {
    @Autowired
    ServiceRepository sr;

    @GetMapping(value = "/api/services")
    public Iterable<ServiceOption> getServiceOptions(){
        return sr.findByDeletedFalse();
    }

    @GetMapping(value = "/api/services/{id}")
    public ServiceOption getServiceOption(@PathVariable(name = "id") Long id){
        Optional<ServiceOption> osr=sr.findById(id);
        if(!osr.isPresent())
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        return osr.get();
    }

    @PostMapping(value = "/api/services")
    public ServiceOption addService(@RequestBody ServiceOption newService){
        return sr.save(newService);
    }

    @DeleteMapping(value = "/api/services/{id}")
    public StringResponse deleteService(@PathVariable("id") Long id){
        Optional<ServiceOption> osr=sr.findById(id);
        if(!osr.isPresent())
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        ServiceOption std = osr.get();
        std.setDeleted(true);
        sr.save(std);
        return new StringResponse("deleted");
    }
}
