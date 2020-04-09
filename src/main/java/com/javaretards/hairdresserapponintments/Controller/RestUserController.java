package com.javaretards.hairdresserapponintments.Controller;

import com.javaretards.hairdresserapponintments.Entity.StringResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public StringResponse loginAction(@RequestParam("username") String username, @RequestParam("password") String password) throws ServletException {

        String jwtToken;

        if (username.isEmpty() || password.isEmpty()) {
            throw new ServletException("empty parameters");
        }

        if (!username.equals(appusername) || !password.equals(apppassword)) {
            throw new ServletException("invalid credentials");
        }

        jwtToken = Jwts.builder().setSubject(username).claim("roles", "ROLE_ADMIN").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secret).compact();

        return new StringResponse(jwtToken);
    }
}
