//package mk.ukim.finki.usersmanagement.xport.controllers;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import lombok.AllArgsConstructor;
//import mk.ukim.finki.usersmanagement.domain.models.User;
//import mk.ukim.finki.usersmanagement.security.filters.JWTAuthenticationFilter;
//import mk.ukim.finki.usersmanagement.services.impl.LoginService;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/sign-in")
//@CrossOrigin("*")
//@AllArgsConstructor
//public class LoginController {
//
//    private final LoginService loginService;
//
//    @PostMapping("/login")
//    public User login(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        return loginService.login(request, response);
//    }
//
//    @GetMapping("/authorize")
//    public void authorize(@RequestHeader String token) {
//        loginService.authorize(token);
//    }
//
//}
