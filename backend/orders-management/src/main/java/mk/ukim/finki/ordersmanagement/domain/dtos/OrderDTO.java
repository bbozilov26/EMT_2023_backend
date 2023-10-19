package mk.ukim.finki.ordersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.ordersmanagement.domain.models.OrderedProduct;
import mk.ukim.finki.ordersmanagement.domain.models.enums.OrderStatus;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderId;
import mk.ukim.finki.usersmanagement.domain.dtos.UserDTO;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private final OrderId id;
    private final OffsetDateTime dateCreated;
    private final OffsetDateTime dateModified;
    private final OffsetDateTime dateClosed;
    private final Double totalPrice;
    private final String orderId;
    private final String trackingNumber;
    private final OrderStatus orderStatus;
    private final UserDTO userDTO;
    private final List<OrderedProductDTO> orderedProductDTOs;
    private final String carrier;
    private final LocalDate ETA;
}
