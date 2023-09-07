package mk.ukim.finki.usersmanagement.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.AllArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.dtos.UserDetailsDTO;
import mk.ukim.finki.usersmanagement.security.SecurityConstants;
import mk.ukim.finki.usersmanagement.services.impl.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static mk.ukim.finki.usersmanagement.security.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException {
        String header = request.getHeader(SecurityConstants.HEADER_STRING);
        if (header==null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken token = getToken(header);
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
    }

    public UsernamePasswordAuthenticationToken getToken(String header) throws JsonProcessingException {
        // parse the token.
        String user = JWT.require(Algorithm.HMAC256(SecurityConstants.SECRET.getBytes()))
                .build()
                .verify(header.replace(SecurityConstants.TOKEN_PREFIX, ""))
                .getSubject();
        if (user == null) {
            return null;
        }
        UserDetailsDTO userDetails = new ObjectMapper().readValue(user, UserDetailsDTO.class);
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "", Collections.singleton((GrantedAuthority) userDetails.getRoles()));
    }

}

