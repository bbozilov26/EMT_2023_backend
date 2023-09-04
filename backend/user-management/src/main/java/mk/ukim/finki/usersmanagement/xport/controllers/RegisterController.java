package mk.ukim.finki.usersmanagement.xport.controllers;

import lombok.AllArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.dtos.UserCreationDTO;
import mk.ukim.finki.usersmanagement.domain.dtos.UserDTO;
import mk.ukim.finki.usersmanagement.domain.models.User;
import mk.ukim.finki.usersmanagement.security.exceptions.PasswordsNotTheSameException;
import mk.ukim.finki.usersmanagement.services.impl.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/register")
@CrossOrigin("*")
@AllArgsConstructor
public class RegisterController {

    private final UserService userService;

    @PostMapping
    public User registerUser(@RequestBody UserCreationDTO userDTO) {
        return this.userService.register(userDTO);

//        try {
//            this.userService.register(userDTO);
//            return "redirect:/login";
//        } catch (PasswordsNotTheSameException | UserAlreadyExistsException exception) {
//            return "redirect:/register?error="+exception.getMessage();
//        }
    }
}
