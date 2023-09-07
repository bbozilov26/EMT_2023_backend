package mk.ukim.finki.usersmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {
        "mk.ukim.finki.usersmanagement",
        "mk.ukim.finki.dailycheckinsmanagement"
})
@EnableScheduling
public class UsersManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersManagementApplication.class, args);
    }

}
