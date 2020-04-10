package com.javaretards.hairdresserapponintments.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class JwtFilter implements Filter {

    private String getMatcher, postMatcher, deleteMatcher;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        getMatcher=filterConfig.getInitParameter("GET");
        postMatcher=filterConfig.getInitParameter("POST");
        deleteMatcher=filterConfig.getInitParameter("DELETE");
    }

    @Override
    public void destroy() {

    }

    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("authorization");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
        } else if (("GET".equals(request.getMethod()) && request.getRequestURI().matches("^/api/"+getMatcher)) ||
                ("POST".equals(request.getMethod()) && request.getRequestURI().matches("^/api/"+postMatcher)) ||
                ("DELETE".equals(request.getMethod()) && request.getRequestURI().matches("^/api/"+deleteMatcher))
        ){
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ServletException("Missing or invalid Authorization header");
            }

            final String token = authHeader.substring(7);

            final Claims claims = Jwts.parser().setSigningKey("qJuonFSwzrGzy4BJF8WUAdsmwVDkTopA").parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);
            request.setAttribute("roles", claims.get("roles"));
            request.setAttribute("subject", claims.getSubject());

            chain.doFilter(req, res);
        }
        else {
            chain.doFilter(req, res);
        }

    }
}
