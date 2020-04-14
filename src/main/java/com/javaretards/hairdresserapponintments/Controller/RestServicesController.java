package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.ServiceOption;
import com.javaretards.hairdresserapponintments.Entity.StringResponse;
import com.javaretards.hairdresserapponintments.Repository.ServiceRepository;
import org.aspectj.lang.annotation.DeclareWarning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
@RequestMapping("/api/services")
@RestController
public class RestServicesController {
    @Autowired
    ServiceRepository sr;

    @GetMapping
    public Iterable<ServiceOption> getServiceOptions(){
        return sr.findByDeletedFalse();
    }

    @GetMapping(value = "/{id}")
    public ServiceOption getServiceOption(@PathVariable(name = "id") Long id){
        Optional<ServiceOption> osr=sr.findById(id);
        if(!osr.isPresent())
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        return osr.get();
    }

    @PostMapping
    public ServiceOption addService(@RequestBody ServiceOption newService){
        return sr.save(newService);
    }

    @DeleteMapping(value = "/{id}")
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
