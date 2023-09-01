package mk.ukim.finki.usersmanagement.security;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.usersmanagement.services.impl.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static mk.ukim.finki.usersmanagement.security.SecurityConstants.*;

//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@AllArgsConstructor
public class WebSecurity {
    private final UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSecurity config = http.cors()
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        LOGIN_URL,
                        LOGOUT,
                        REGISTER,
                        PUBLIC_URLS,
                        SWAGGER_UI,
                        API_DOCS
                )
                .permitAll()
                .anyRequest().authenticated()
                .and();

        config.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return config.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");

        config.addExposedHeader("WWW-Authenticate");
        config.addExposedHeader("Access-Control-Allow-Origin");
        config.addExposedHeader("Access-Control-Allow-Headers");

        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

