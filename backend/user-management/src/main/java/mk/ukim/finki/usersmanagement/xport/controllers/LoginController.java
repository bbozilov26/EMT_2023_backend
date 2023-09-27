package mk.ukim.finki.usersmanagement.xport.controllers;

import lombok.AllArgsConstructor;
import mk.ukim.finki.usersmanagement.services.impl.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/sign-in")
@CrossOrigin("*")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        loginService.login(request, response);
    }

}
