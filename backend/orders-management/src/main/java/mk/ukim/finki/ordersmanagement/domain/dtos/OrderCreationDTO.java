package mk.ukim.finki.ordersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.ordersmanagement.domain.models.enums.OrderStatus;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;

import java.time.OffsetDateTime;

@Data
public class OrderCreationDTO {
    private Double totalPrice;
    private String description;
    private OrderStatus orderStatus;
    private UserId userId;
}
