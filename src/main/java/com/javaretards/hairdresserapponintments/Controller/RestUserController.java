package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.StringResponse;
import com.javaretards.hairdresserapponintments.Entity.UserLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class RestUserController {
    @Value("${jwtsecurity.secret}")
    private String secret;

    @Value("${userdata.username}")
    private String appusername;

    @Value("${userdata.password}")
    private String apppassword;

    @GetMapping("api/whoami")
    public StringResponse whoami(HttpServletRequest request){
        return new StringResponse(request.getAttribute("subject").toString());
    }

    @PostMapping(value = "api/login")
    public StringResponse loginAction(@RequestBody UserLogin userLogin) throws ServletException {

        String jwtToken;

        if (userLogin.getUsername().isEmpty() || userLogin.getPassword().isEmpty()) {
            throw new ServletException("empty parameters");
        }

        if (!userLogin.getUsername().equals(appusername) || !userLogin.getPassword().equals(apppassword)) {
            throw new ServletException("invalid credentials");
        }

        jwtToken = Jwts.builder().setSubject(userLogin.getUsername()).claim("roles", "ADMIN").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secret).compact();

        return new StringResponse(jwtToken);
    }
}
