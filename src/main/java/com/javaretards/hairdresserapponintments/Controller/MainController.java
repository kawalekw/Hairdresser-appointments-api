package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Repository.OpenHoursRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author mateusz
 */
@Controller
public class MainController {
    @Autowired OpenHoursRepository ohr;
    
    @GetMapping({"/"})
    public String indexAction(Model model){
        model.addAttribute("open", ohr.findFirstByAppliesFromBeforeOrderByAppliesFromDesc(LocalDate.now().plusDays(1)).get());
        return "main";
    }
}
