package mk.ukim.finki.usersmanagement.security;

import com.auth0.jwt.JWT;
import mk.ukim.finki.usersmanagement.domain.models.Role;
import mk.ukim.finki.usersmanagement.domain.models.User;
import mk.ukim.finki.usersmanagement.security.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.usersmanagement.security.exceptions.UserNotEnabledException;
import mk.ukim.finki.usersmanagement.services.impl.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static mk.ukim.finki.usersmanagement.security.SecurityConstants.*;

@Component
public class JWTUtils {

    private final UserService userService;


    public JWTUtils(UserService userService) {
        this.userService = userService;
    }

    public List<GrantedAuthority> addAuthoritiesFromRoles(User user, String password) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getEnabled()) {
            if (!userService.passwordMatches(user, password)) {
                throw new PasswordsDoNotMatchException();
            }

            authorities.add(new SimpleGrantedAuthority(user.getRole().getLabel()));
        } else {
            throw new UserNotEnabledException();
        }

        return authorities;
    }

    public String createAndAppendToken(UserDetails userDetails, HttpServletResponse res) throws IOException {
        User user = userService.findByEmail(userDetails.getUsername());
        //user.setLastLogin(OffsetDateTime.now());
        this.userService.save(user);
        String token = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withArrayClaim(SecurityConstants.CLAIM_AUTHORITY, (userDetails).getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        res.getWriter().append(token);

        return token;
    }

    public String createAndAppendToken(HttpServletRequest req, HttpServletResponse res, FilterChain chain, org.springframework.security.core.Authentication auth) throws IOException {
        UserDetails userDetails = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        return createAndAppendToken(userDetails, res);
    }
}
