package mk.ukim.finki.usersmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.models.User;
import mk.ukim.finki.usersmanagement.domain.repositories.UserRepository;
import mk.ukim.finki.usersmanagement.security.exceptions.InvalidArgumentsException;
import mk.ukim.finki.usersmanagement.security.exceptions.InvalidUserCredentialsException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByEmailAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
