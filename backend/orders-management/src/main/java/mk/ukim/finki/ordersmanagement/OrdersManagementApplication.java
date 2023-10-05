package mk.ukim.finki.ordersmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "mk.ukim.finki.ordersmanagement",
        "mk.ukim.finki.usersmanagement",
        "mk.ukim.finki.productsmanagement"
})
public class OrdersManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersManagementApplication.class, args);
    }

}
