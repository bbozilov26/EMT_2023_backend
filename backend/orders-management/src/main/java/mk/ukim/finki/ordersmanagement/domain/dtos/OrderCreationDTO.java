package mk.ukim.finki.ordersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.ordersmanagement.domain.models.enums.OrderStatus;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderedProductId;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class OrderCreationDTO {
    private List<OrderedProductId> orderedProductIds;
    private Double totalPrice;
    private String description;
    private OrderStatus orderStatus;
    private UserId userId;
    private String carrier;
    private Integer eta;
}
