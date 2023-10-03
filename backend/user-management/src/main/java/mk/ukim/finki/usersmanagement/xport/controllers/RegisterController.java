package mk.ukim.finki.usersmanagement.xport.controllers;

import lombok.AllArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.converters.UserConverter;
import mk.ukim.finki.usersmanagement.domain.dtos.UserCreationDTO;
import mk.ukim.finki.usersmanagement.domain.dtos.UserDTO;
import mk.ukim.finki.usersmanagement.domain.models.User;
import mk.ukim.finki.usersmanagement.services.impl.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sign-up")
@CrossOrigin("*")
@AllArgsConstructor
public class RegisterController {

    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping
    public UserDTO registerUser(@RequestBody UserCreationDTO userDTO) {
        return userConverter.toUserDTO(this.userService.register(userDTO));
    }
}
