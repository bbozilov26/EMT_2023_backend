package mk.ukim.finki.usersmanagement.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mk.ukim.finki.usersmanagement.domain.dtos.UserDetailsDTO;
import mk.ukim.finki.usersmanagement.domain.models.User;
import mk.ukim.finki.usersmanagement.security.JWTUtils;
import mk.ukim.finki.usersmanagement.security.exceptions.AccessForbiddenException;
import mk.ukim.finki.usersmanagement.security.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.usersmanagement.services.impl.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import static mk.ukim.finki.usersmanagement.security.SecurityConstants.*;

@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTUtils jwtUtils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public org.springframework.security.core.Authentication attemptAuthentication(HttpServletRequest req,
                                                                                  HttpServletResponse res) {
        try {
            String auth = req.getHeader("Authorization");

            if (auth == null || !auth.startsWith("Basic ")) {
                throw new AccessForbiddenException();
            }

            String credentials = Base64Coder.decodeString(auth.substring(6));
            String[] emailPassword = credentials.split(":", 2);

            String email = emailPassword[0];

            if (email != null) {
                email = email.toLowerCase();
            }

            String password = emailPassword[1];
            User user = userService.findByEmail(email);
            if(user==null){
                throw new AccessForbiddenException();
            }

            List<GrantedAuthority> authorities = jwtUtils.addAuthoritiesFromRoles(user, password);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            password,
                            authorities)
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            org.springframework.security.core.Authentication auth) throws IOException {

        jwtUtils.createAndAppendToken(req, res, chain, auth);
    }
}


