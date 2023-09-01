package mk.ukim.finki.usersmanagement.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.models.User;
import mk.ukim.finki.usersmanagement.security.exceptions.AccessForbiddenException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.IOException;

@Service
@AllArgsConstructor
@Transactional
public class LoginService {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
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

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new AccessForbiddenException();
        }
    }
}
