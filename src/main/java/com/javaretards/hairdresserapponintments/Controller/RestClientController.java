package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.Client;
import com.javaretards.hairdresserapponintments.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/clients")
public class RestClientController {
    @Autowired
    ClientRepository cr;

    @GetMapping
    public Iterable<Client> getAllClients(){
        return cr.findAll();
    }

    @GetMapping("/{id}")
    public Client getOneClient(@PathVariable("id")Long id){
        Optional<Client> ocl=cr.findById(id);
        if(!ocl.isPresent())
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        return ocl.get();
    }
    @PostMapping("/{id}/block")
    public Client blockOneClient(@PathVariable("id")Long id){
        Optional<Client> ocl=cr.findById(id);
        if(!ocl.isPresent())
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        Client cl=ocl.get();
        cl.block();
        return cr.save(cl);
    }

    @PostMapping("/{id}/unlock")
    public Client unlockOneClient(@PathVariable("id")Long id){
        Optional<Client> ocl=cr.findById(id);
        if(!ocl.isPresent())
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        Client cl=ocl.get();
        cl.unlock();
        return cr.save(cl);
    }
}
