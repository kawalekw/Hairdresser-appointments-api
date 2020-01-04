package com.javaretards.hairdresserapponintments.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mateusz
 */
@Controller
public class SecurityController {
    @RequestMapping("/login")
    public String LoginAction(){
        return "login";
    }
}
