package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.OpenHours;
import com.javaretards.hairdresserapponintments.Entity.StringResponse;
import com.javaretards.hairdresserapponintments.Repository.OpenHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletException;
import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class RestOpenHoursController {
    @Autowired
    OpenHoursRepository ohr;

    @GetMapping(value = "/api/openhours")
    public Iterable<OpenHours> getAllOpenHours(){
        return ohr.findAll();
    }

    @GetMapping(value = "/api/openhours/{id}")
    public OpenHours getAllOpenHoursById(@PathVariable(name = "id") Long id){
        Optional<OpenHours> ooh = ohr.findById(id);
        if(!ooh.isPresent())
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        return ooh.get();
    }

    @GetMapping(value = "/api/openhours/recent")
    public OpenHours getAllOpenHoursById(){
        return ohr.findFirstByAppliesFromBeforeOrderByAppliesFromDesc(LocalDate.now().plusDays(1)).get();
    }
    @PostMapping(value = "/api/openhours")
    public OpenHours addNewOpenHours(@RequestBody OpenHours newOpenHours){
        return ohr.save(newOpenHours);
    }
    @DeleteMapping(value = "/api/openhours/{id}")
    public StringResponse deleteOpenHours(@PathVariable("id") Long id) throws ServletException {
        if(id==1)
            throw new ServletException("Can't delete original definition");
        Optional<OpenHours> ooh = ohr.findById(id);
        if(!ooh.isPresent())
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        OpenHours oh = ooh.get();
        ohr.delete(oh);
        return new StringResponse("deleted");
    }
}
