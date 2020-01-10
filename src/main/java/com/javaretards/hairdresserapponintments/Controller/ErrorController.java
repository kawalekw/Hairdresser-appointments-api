package com.javaretards.hairdresserapponintments.Controller;

import org.springframework.stereotype.Controller;

/**
 *
 * @author mateusz
 */
@Controller
public class ErrorController {
    public String errorAction(){
        return "error";
    }
}
