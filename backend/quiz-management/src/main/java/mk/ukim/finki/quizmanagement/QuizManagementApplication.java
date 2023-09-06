package mk.ukim.finki.quizmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "mk.ukim.finki.usersmanagement"
})
public class QuizManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizManagementApplication.class, args);
    }
}
