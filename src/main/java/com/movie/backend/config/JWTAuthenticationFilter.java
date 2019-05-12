package com.movie.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.backend.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/*
 * @EnableGlobalMethodSecurity(securedEnabled = true) is used to protect any method with @Secured which we add to specify authorized users based on there roles
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User user = null;
        // If data are sent using other format than JSON, we can retrieve data using the request object as we always do
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            // Retrieve SecurityUser object from request json data using Jackson dependency (mapper)
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        /* In case we successflly retrieved data from the request, we will authentocate the user using
         * the AuthenticationManager instance giving it the email (username if not our case) and password
         */
        System.out.println("*******************************************************************");
        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());
        System.out.println("*******************************************************************");
        return this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // Retrieve the user from AuthResult object passed as argument (Spring user and not SecurityUser)
        // Build jwt Token from Jwts class
        // The subject must be the username(The email in our case)
        // which help us to decode it when receiving the token on the Frontend side
        // Security Constants is a created class which contains only constant properties
        // claim method for additions

        System.out.println(authResult.getPrincipal().getClass().getSimpleName());

        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        System.out.println("*****************************************************");
        System.out.println("Email: " + springUser.getUsername());
        System.out.println("Password: " + springUser.getPassword());
        System.out.println("*****************************************************");
        String jwtToken = Jwts.builder()
                .setSubject(springUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
                .claim("roles", springUser.getAuthorities())
                .compact();
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + jwtToken);
    }

}
