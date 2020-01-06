package com.javaretards.hairdresserapponintments.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mateusz
 */
@Controller
public class DashboadController {
    
    @RequestMapping("/dashboard")
    public String dashboardAction(){
        return "dashboard";
    }
    
}
