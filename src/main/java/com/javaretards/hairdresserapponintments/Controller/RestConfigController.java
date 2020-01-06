package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.ServiceOption;
import com.javaretards.hairdresserapponintments.Repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mateusz
 */
@AllArgsConstructor
@RestController
public class RestConfigController {
    private final ServiceRepository sr;
    
    @GetMapping("/api/services")
    public Iterable<ServiceOption> getAllServices(){
        return sr.findByDeletedFalse();
    }
}
